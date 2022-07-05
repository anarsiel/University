package com.company.main;

import com.company.*;

import java.util.*;
import java.util.regex.Pattern;

public class MyGrammarListener extends GrammarBaseListener {
    public final String EPS = "EPS";
    public final String EOF = "EOF";
    private int tokenSize = 0;
    public LinkedHashMap<String, Integer> tokenTable = new LinkedHashMap<>();
    public LinkedHashMap<Integer, Pattern> patternHashMap = new LinkedHashMap<>();
    public LinkedHashMap<Integer, String> literals = new LinkedHashMap<>();
    public LinkedHashSet<Integer> skippedTokens = new LinkedHashSet<>();
    public boolean hasEps = false;

    public LinkedHashMap<String, Rule> rules = new LinkedHashMap<>();

    public Set<String> getTerms() {
        HashSet<String> terms = new HashSet<>(tokenTable.keySet());
        if (hasEps) {
            terms.add(EPS);
        }
        return terms;
    }

    public Set<String> getNonTerminals() {
        return rules.keySet();
    }

    public String pckg;
    public String startRule;

    @Override
    public void exitPckg(GrammarParser.PckgContext ctx) {
        pckg = "package " + ctx.PCKG_NAME().getText();
        super.exitPckg(ctx);
    }

    @Override
    public void exitStart(GrammarParser.StartContext ctx) {
        startRule = ctx.NON_TERM().getText();
        super.exitStart(ctx);
    }

    @Override
    public void exitNonTermRule(GrammarParser.NonTermRuleContext ctx) {
        Rule cur;
        if (rules.containsKey(ctx.NON_TERM().getText())) {
            cur = rules.get(ctx.NON_TERM().getText());
        } else {
            cur = new Rule(ctx.NON_TERM().getText());
            rules.put(ctx.NON_TERM().getText(), cur);
        }

        if (ctx.returnAttr() != null) {
            assert cur != null;
            cur.returnType = ctx.returnAttr().getText();
        }

        if (ctx.inAttrs() != null) {
            for (int i = 0; i < ctx.inAttrs().param().size(); ++i) {
                GrammarParser.ParamContext p = ctx.inAttrs().param().get(i);
                assert cur != null;
                cur.args.add(new Arg(p.paramName().getText(), p.paramType().getText()));
            }
        }

        for (int i = 0; i < ctx.productions().size(); ++i) {
            GrammarParser.ProductionsContext curProds = ctx.productions(i);
            ArrayList<CodeOrProd> prod = new ArrayList<>();
            if (curProds.product().isEmpty()) {
                hasEps = true;
                prod.add(new Term(EPS));
            }


            ArrayList<ProdElem> nonCodeProds = new ArrayList<>();
            for (int j = 0; j < curProds.product().size(); ++j) {
                GrammarParser.ProductContext curProd = curProds.product(j);
                if (curProd.NON_TERM() != null) {
                    List<String> args = new ArrayList<>();
                    if (curProd.args() != null) {
                        for (int z = 0; z < curProd.args().arg().size(); ++z) {
                            if (curProd.args().arg(z).CODE() != null) {
                                args.add(curProd.args().arg(z).CODE().getText().substring(1, curProd.args().arg(z).CODE().getText().length() - 1));
                            } else {
                                args.add(curProd.args().arg(z).getText());
                            }
                        }
                    }
                    NonTerm t = new NonTerm(curProd.NON_TERM().getText(), args);
                    prod.add(t);
                    nonCodeProds.add(t);
                } else if (curProd.TERM() != null) {
                    Term t = new Term(curProd.TERM().getText());
                    prod.add(t);
                    nonCodeProds.add(t);
                } else {
                    prod.add(new Code(curProd.CODE().getText().substring(1, curProd.CODE().getText().length() - 1)));
                }
            }

            if (nonCodeProds.isEmpty()) {
                hasEps = true;
                prod.add(new Term(EPS));
                cur.ways.add(new Way(List.of(new Term(EPS)), prod));
            } else {
               cur.ways.add(new Way(nonCodeProds, prod));
            }
        }
        super.exitNonTermRule(ctx);
    }

    @Override
    public void exitCommonTermRule(GrammarParser.CommonTermRuleContext ctx) {
        AddToTokenTable(ctx.TERM().getText(), ctx.term_value());
        super.exitCommonTermRule(ctx);
    }

    @Override
    public void exitSkipRule(GrammarParser.SkipRuleContext ctx) {
        AddToTokenTable(ctx.TERM().getText(), ctx.term_value());
        skippedTokens.add(tokenTable.get(ctx.TERM().getText()));
        super.exitSkipRule(ctx);
    }

    @Override
    public void exitFile(GrammarParser.FileContext ctx) {
        tokenTable.put(EOF, -1);
        super.exitFile(ctx);
    }

    private void AddToTokenTable(String tokenName, GrammarParser.Term_valueContext ctx) {
        if (tokenTable.containsKey(tokenName)) {
            return;
        }

        Integer id = tokenSize;
        tokenTable.put(tokenName, tokenSize++);

        if (ctx.STRING() != null) {
            literals.put(id, ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1));
        } else {
            patternHashMap.put(id, Pattern.compile(ctx.REGEX().getText().substring(1, ctx.REGEX().getText().length() - 1)));
        }
    }
}
