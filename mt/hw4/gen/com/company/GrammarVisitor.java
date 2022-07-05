// Generated from D:/tmp/hw4/src/com/company\Grammar.g4 by ANTLR 4.9
package com.company;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(GrammarParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#pckg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPckg(GrammarParser.PckgContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(GrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rule1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule1(GrammarParser.Rule1Context ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#nonTermRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonTermRule(GrammarParser.NonTermRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#inAttrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInAttrs(GrammarParser.InAttrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(GrammarParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#paramType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamType(GrammarParser.ParamTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#paramName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamName(GrammarParser.ParamNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#returnAttr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnAttr(GrammarParser.ReturnAttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#productions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProductions(GrammarParser.ProductionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#product}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduct(GrammarParser.ProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(GrammarParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#termRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermRule(GrammarParser.TermRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#commonTermRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommonTermRule(GrammarParser.CommonTermRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#skipRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipRule(GrammarParser.SkipRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#term_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_value(GrammarParser.Term_valueContext ctx);
}