package expression.exceptions;

public class ParsingException extends Exception {
    public ParsingException(final String message) {
        super(message);
    }

    static protected String getPlace(final int begin, final int end, final String s) {
        StringBuilder res = new StringBuilder();
        res.append(s, 0, begin);
        res.append("---->");
        if (end < s.length()) res.append(s, begin, end + 1);
        res.append("<----");
        if (end + 1 < s.length()) res.append(s.substring(end + 1));
        return res.toString();
    }

}