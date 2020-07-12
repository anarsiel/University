package md2html;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Md2HtmlParser implements AutoCloseable{
    private MarkdownSource markdownSource;
    private int currentIndexInParsingData, previousIndexInParsingData;

    @Override
    public void close() throws Exception {
        markdownSource.closeInputOutput();
    }

    private enum Style {HEADING1, HEADING2, HEADING3, HEADING4, HEADING5, HEADING6,
        BOLD_STAR, BOLD_UNDERSCORE, ITALIC_STAR, ITALIC_UNDERSCORE, CROSSOUT, CODE,
        PARAGRAPH, UNDERLINE, MARK, LINK, IMAGE}

    private Stack<Style> styleStack = new Stack<>();
    private String data;

    private static Map<Style, String> htmlCodes = new HashMap<>();
    static {
        htmlCodes.put(Style.HEADING1, "h1");
        htmlCodes.put(Style.HEADING2, "h2");
        htmlCodes.put(Style.HEADING3, "h3");
        htmlCodes.put(Style.HEADING4, "h4");
        htmlCodes.put(Style.HEADING5, "h5");
        htmlCodes.put(Style.HEADING6, "h6");

        htmlCodes.put(Style.ITALIC_STAR, "em");
        htmlCodes.put(Style.ITALIC_UNDERSCORE, "em");

        htmlCodes.put(Style.BOLD_STAR, "strong");
        htmlCodes.put(Style.BOLD_UNDERSCORE, "strong");

        htmlCodes.put(Style.UNDERLINE, "u");

        htmlCodes.put(Style.CROSSOUT, "s");

        htmlCodes.put(Style.CODE,"code");

        htmlCodes.put(Style.PARAGRAPH,"p");

        htmlCodes.put(Style.MARK,"mark");
    }

    void parse() throws IOException {
        while (!(data = markdownSource.readParagraph()).isEmpty()) {
            parseParagraph();

            markdownSource.addToOutputBuffer("\n");
            markdownSource.writeBufferToFile();
        }

        markdownSource.closeInputOutput();
    }

    private void pushToStack(Style style) {
        if (!styleStack.empty() && styleStack.peek().equals(style)) {
            styleStack.pop();
            markdownSource.addToOutputBuffer("</" + htmlCodes.get(style) + ">");
        } else {
            styleStack.push(style);
            markdownSource.addToOutputBuffer("<" + htmlCodes.get(style) + ">");
        }
    }

    private void searchForHeading() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex != 0) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        int lvl = 0;
        while (currentIndex < data.length() && data.charAt(currentIndex) == '#' && currentIndex < 8) {
            lvl++;
            currentIndex++;
        }

        if (currentIndex < data.length() && data.charAt(currentIndex) != ' ' || lvl > 6 || lvl == 0)
            return;

        switch (lvl) {
            case 1:
                pushToStack(Style.HEADING1);
                break;
            case 2:
                pushToStack(Style.HEADING2);
                break;
            case 3:
                pushToStack(Style.HEADING3);
                break;
            case 4:
                pushToStack(Style.HEADING4);
                break;
            case 5:
                pushToStack(Style.HEADING5);
                break;
            case 6:
                pushToStack(Style.HEADING6);
                break;
        }

        currentIndexInParsingData += lvl + 1;
    }

    private void searchForParagraph() {
        int currentIndex = currentIndexInParsingData;

        if (currentIndex != 0 || currentIndex >= data.length()) return;

        if (currentIndex == previousIndexInParsingData) {
            pushToStack(Style.PARAGRAPH);
        }
    }

    private void searchForBold() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        if (currentIndex <= data.length() - 3 && data.charAt(currentIndex) == '*'
                && data.charAt(currentIndex + 1) == '*' && data.charAt(currentIndex + 2) != ' ') {
            pushToStack(Style.BOLD_STAR);
            currentIndexInParsingData += 2;
        } else if (currentIndex <= data.length() - 3 && data.charAt(currentIndex) == '_'
                && data.charAt(currentIndex + 1) == '_' && data.charAt(currentIndex + 2) != ' ') {
            pushToStack(Style.BOLD_UNDERSCORE);

            currentIndexInParsingData += 2;
        } else if (currentIndex <= data.length() - 2 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' ' && data.charAt(currentIndex) == '*'
                && data.charAt(currentIndex + 1) == '*') {
            pushToStack(Style.BOLD_STAR);
            currentIndexInParsingData += 2;
        } else if (currentIndex <= data.length() - 2 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' ' && data.charAt(currentIndex) == '_'
                && data.charAt(currentIndex + 1) == '_') {
            pushToStack(Style.BOLD_UNDERSCORE);
            currentIndexInParsingData += 2;
        }
    }

    private void searchForItalic() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        if (currentIndex <= data.length() - 2 && data.charAt(currentIndex) == '*' && data.charAt(currentIndex + 1) != ' ' && data.charAt(currentIndex + 1) != '\n') {
            pushToStack(Style.ITALIC_STAR);
            currentIndexInParsingData += 1;
        } else if (currentIndex <= data.length() - 2 && data.charAt(currentIndex) == '_' && data.charAt(currentIndex + 1) != ' ' && data.charAt(currentIndex + 1) != '\n') { // !!!!!!!!!!!!!!!!!!!!!!!
            pushToStack(Style.ITALIC_UNDERSCORE);
            currentIndexInParsingData += 1;
        } else if (currentIndex <= data.length() - 1 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' '  && data.charAt(currentIndex - 1) != '\n' && data.charAt(currentIndex) == '*') {
            pushToStack(Style.ITALIC_STAR);
            currentIndexInParsingData += 1;
        } else if (currentIndex <= data.length() - 1 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' '  && data.charAt(currentIndex - 1) != '\n' && data.charAt(currentIndex) == '_') {
            pushToStack(Style.ITALIC_UNDERSCORE);
            currentIndexInParsingData += 1;
        }
    }

    private void searchForCrossOut() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        if (currentIndex <= data.length() - 3 && data.charAt(currentIndex) == '-'
                && data.charAt(currentIndex + 1) == '-' && data.charAt(currentIndex + 2) != ' ') {
            pushToStack(Style.CROSSOUT);
            currentIndexInParsingData += 2;
        } else if (currentIndex <= data.length() - 2 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' ' && data.charAt(currentIndex) == '-'
                && data.charAt(currentIndex + 1) == '-') {
            pushToStack(Style.CROSSOUT);
            currentIndexInParsingData += 2;
        }
    }

    private void searchForUnderline() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        if (currentIndex <= data.length() - 3 && data.charAt(currentIndex) == '+'
                && data.charAt(currentIndex + 1) == '+' && data.charAt(currentIndex + 2) != ' ') {
            pushToStack(Style.UNDERLINE);
            currentIndexInParsingData += 2;
        } else if (currentIndex <= data.length() - 2 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' ' && data.charAt(currentIndex) == '+'
                && data.charAt(currentIndex + 1) == '+') {
            pushToStack(Style.UNDERLINE);
            currentIndexInParsingData += 2;
        }
    }

    private void searchForColorSelection() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        if (currentIndex <= data.length() - 2 && data.charAt(currentIndex) == '~' && data.charAt(currentIndex + 1) != ' ' && data.charAt(currentIndex + 1) != '\n') {
            pushToStack(Style.MARK);
            currentIndexInParsingData += 1;
        }else if (currentIndex <= data.length() - 1 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' '  && data.charAt(currentIndex - 1) != '\n' && data.charAt(currentIndex) == '~') {
            pushToStack(Style.MARK);
            currentIndexInParsingData += 1;
        }
    }
    
    private void searchForCode() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        searchForSpecialSymbols();
        currentIndex = currentIndexInParsingData;

        if (currentIndex <= data.length() - 2 && data.charAt(currentIndex) == '`' && data.charAt(currentIndex + 1) != ' ') {
            pushToStack(Style.CODE);
            currentIndexInParsingData += 1;
        } else if (currentIndex <= data.length() - 1 && currentIndex != 0 && data.charAt(currentIndex - 1) != ' ' && data.charAt(currentIndex) == '`') {
            pushToStack(Style.CODE);
            currentIndexInParsingData += 1;
        }
    }

    private void searchForSpecialSymbols() {
        int currentIndex = currentIndexInParsingData;
        if (currentIndex >= data.length()) return;

        char c = data.charAt(currentIndex);

        if (currentIndex < data.length() - 3 && data.substring(currentIndex, currentIndex + 4).equals("</a>")) {
            markdownSource.addToOutputBuffer("</a>");
            currentIndexInParsingData += 4;
        } else if (c == '<') {
            markdownSource.addToOutputBuffer("&lt;");
            currentIndexInParsingData++;
        } else if (c == '>') {
            markdownSource.addToOutputBuffer("&gt;");
            currentIndexInParsingData++;
        } else if (c == '&') {
            markdownSource.addToOutputBuffer("&amp;");
            currentIndexInParsingData++;
        }

        if (data.charAt(currentIndex) == '\\' && currentIndex < data.length() - 1 && (data.charAt(currentIndex + 1) == '*' || data.charAt(currentIndex + 1) == '_')) {
            markdownSource.addToOutputBuffer(data.charAt(currentIndex + 1) + "");
            currentIndexInParsingData += 2;
        }
    }

    private void searchForText() {
        int currentIndex = currentIndexInParsingData;
        searchForSpecialSymbols();

        if (currentIndex >= data.length()) return;

        StringBuilder textStringBuilder = new StringBuilder();
        textStringBuilder.append(data.charAt(currentIndex));
        currentIndexInParsingData++;

        for (int i = currentIndex + 1; i < data.length(); ++i) {
            char c = data.charAt(i);

            if (!Character.isLetter(c) && !Character.isDigit(c) && c != ' ') {
                currentIndexInParsingData = i;
                break;
            }

            textStringBuilder.append(c);
            if (i == data.length() - 1) {
                currentIndexInParsingData = data.length();
            }
        }

        markdownSource.addToOutputBuffer(textStringBuilder.toString());
    }

    private void searchForImage() {
        int currentIndex = currentIndexInParsingData;
        searchForSpecialSymbols();

        if (currentIndex >= data.length() - 1) return;

        boolean correctDescriptionBeginning = data.charAt(currentIndex) == '!' && data.charAt(currentIndex + 1) == '[';
        if (!correctDescriptionBeginning) return;

        boolean correctDescriptionEnding = false;

        currentIndex += 2;
        StringBuilder imageDescription = new StringBuilder();
        for (int i = currentIndex; i < data.length(); ++i) {
            char c = data.charAt(i);
            if (c != ']') {
                imageDescription.append(c);
            } else {
                correctDescriptionEnding = imageDescription.length() > 0;
                currentIndex = i + 1;
                break;
            }
        }

        if (!correctDescriptionEnding || currentIndex >= data.length()) return;

        boolean correctLinkBeginning = data.charAt(currentIndex++) == '(';
        if (!correctLinkBeginning) return;

        boolean correctLinkEnding = false;
        StringBuilder imageLink = new StringBuilder();
        for (int i = currentIndex; i < data.length(); ++i) {
            char c = data.charAt(i);
            if (c != ')') {
                imageLink.append(c);
            } else {
                correctLinkEnding = imageLink.length() > 0;
                currentIndex = i + 1;
                break;
            }
        }

        if (!correctLinkEnding) return;

        currentIndexInParsingData = currentIndex;
        markdownSource.addToOutputBuffer("<img alt='" + imageDescription.toString() +
                                            "' src='" + imageLink.toString() + "'>");
    }

    private void searchForLink() {
        int currentIndex = currentIndexInParsingData;
        searchForSpecialSymbols();

        if (currentIndex >= data.length()) return;

        boolean correctDescriptionBeginning = data.charAt(currentIndex) == '[';
        if (!correctDescriptionBeginning) return;

        boolean correctDescriptionEnding = false;

        currentIndex += 1;
        StringBuilder linkDescription = new StringBuilder();
        for (int i = currentIndex; i < data.length(); ++i) {
            char c = data.charAt(i);
            if (c != ']') {
                linkDescription.append(c);
            } else {
                correctDescriptionEnding = linkDescription.length() > 0;
                currentIndex = i + 1;
                break;
            }
        }

        if (!correctDescriptionEnding || currentIndex >= data.length()) return;

        boolean correctLinkBeginning = data.charAt(currentIndex++) == '(';
        if (!correctLinkBeginning) return;

        boolean correctLinkEnding = false;
        StringBuilder linkLink = new StringBuilder();
        for (int i = currentIndex; i < data.length(); ++i) {
            char c = data.charAt(i);
            if (c != ')') {
                linkLink.append(c);
            } else {
                correctLinkEnding = linkLink.length() > 0;
                currentIndex = i + 1;
                break;
            }
        }

        if (!correctLinkEnding) return;

        String s1 = data.substring(0, currentIndexInParsingData);
        String s2 = "<a href='" + linkLink.toString() + "'>";
        String s3 = linkDescription.toString() + "</a>";
        String s4 = data.substring(currentIndex);
        markdownSource.addToOutputBuffer("<a href='" + linkLink.toString() + "'>");
        data = s1 + s2 + s3 + s4;
        currentIndexInParsingData = s1.length() + s2.length();
    }

    private void parseParagraph() {
        styleStack.clear();

        for (currentIndexInParsingData = 0; currentIndexInParsingData < data.length();) {
            previousIndexInParsingData = currentIndexInParsingData;

            while (currentIndexInParsingData < data.length()) {
                searchForHeading();
                searchForParagraph();
                searchForBold();
                searchForItalic();
                searchForUnderline();
                searchForColorSelection();
                searchForCrossOut();
                searchForCode();
                searchForImage();
                searchForLink();

                if (currentIndexInParsingData == previousIndexInParsingData) {
                    break;
                }
                previousIndexInParsingData = currentIndexInParsingData;
            }
            searchForSpecialSymbols();
            searchForText();
        }

        if (styleStack.size() != 0) { // added closing of the heading
            pushToStack(styleStack.peek());
        }
    }

    Md2HtmlParser(final String inputFileName, String outputFileName) throws IOException {
        markdownSource = new MarkdownSource(inputFileName, outputFileName);
        currentIndexInParsingData = 0;
    }
}
