// Generated from D:/tmp/hw4/src/com/company\Grammar.g4 by ANTLR 4.9
package com.company;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(GrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(GrammarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#pckg}.
	 * @param ctx the parse tree
	 */
	void enterPckg(GrammarParser.PckgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#pckg}.
	 * @param ctx the parse tree
	 */
	void exitPckg(GrammarParser.PckgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(GrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(GrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rule1}.
	 * @param ctx the parse tree
	 */
	void enterRule1(GrammarParser.Rule1Context ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rule1}.
	 * @param ctx the parse tree
	 */
	void exitRule1(GrammarParser.Rule1Context ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#nonTermRule}.
	 * @param ctx the parse tree
	 */
	void enterNonTermRule(GrammarParser.NonTermRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#nonTermRule}.
	 * @param ctx the parse tree
	 */
	void exitNonTermRule(GrammarParser.NonTermRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#inAttrs}.
	 * @param ctx the parse tree
	 */
	void enterInAttrs(GrammarParser.InAttrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#inAttrs}.
	 * @param ctx the parse tree
	 */
	void exitInAttrs(GrammarParser.InAttrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(GrammarParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(GrammarParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#paramType}.
	 * @param ctx the parse tree
	 */
	void enterParamType(GrammarParser.ParamTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#paramType}.
	 * @param ctx the parse tree
	 */
	void exitParamType(GrammarParser.ParamTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#paramName}.
	 * @param ctx the parse tree
	 */
	void enterParamName(GrammarParser.ParamNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#paramName}.
	 * @param ctx the parse tree
	 */
	void exitParamName(GrammarParser.ParamNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#returnAttr}.
	 * @param ctx the parse tree
	 */
	void enterReturnAttr(GrammarParser.ReturnAttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#returnAttr}.
	 * @param ctx the parse tree
	 */
	void exitReturnAttr(GrammarParser.ReturnAttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#productions}.
	 * @param ctx the parse tree
	 */
	void enterProductions(GrammarParser.ProductionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#productions}.
	 * @param ctx the parse tree
	 */
	void exitProductions(GrammarParser.ProductionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(GrammarParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(GrammarParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(GrammarParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(GrammarParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#termRule}.
	 * @param ctx the parse tree
	 */
	void enterTermRule(GrammarParser.TermRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#termRule}.
	 * @param ctx the parse tree
	 */
	void exitTermRule(GrammarParser.TermRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#commonTermRule}.
	 * @param ctx the parse tree
	 */
	void enterCommonTermRule(GrammarParser.CommonTermRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#commonTermRule}.
	 * @param ctx the parse tree
	 */
	void exitCommonTermRule(GrammarParser.CommonTermRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#skipRule}.
	 * @param ctx the parse tree
	 */
	void enterSkipRule(GrammarParser.SkipRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#skipRule}.
	 * @param ctx the parse tree
	 */
	void exitSkipRule(GrammarParser.SkipRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#term_value}.
	 * @param ctx the parse tree
	 */
	void enterTerm_value(GrammarParser.Term_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#term_value}.
	 * @param ctx the parse tree
	 */
	void exitTerm_value(GrammarParser.Term_valueContext ctx);
}