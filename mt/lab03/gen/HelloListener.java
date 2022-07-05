// Generated from /Users/admin/Documents/University/#GitHub/mt/lab03/src/main/java/Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(HelloParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(HelloParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#global_statement}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_statement(HelloParser.Global_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#global_statement}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_statement(HelloParser.Global_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(HelloParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(HelloParser.Function_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_header}.
	 * @param ctx the parse tree
	 */
	void enterFunction_header(HelloParser.Function_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_header}.
	 * @param ctx the parse tree
	 */
	void exitFunction_header(HelloParser.Function_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_body}.
	 * @param ctx the parse tree
	 */
	void enterFunction_body(HelloParser.Function_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_body}.
	 * @param ctx the parse tree
	 */
	void exitFunction_body(HelloParser.Function_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#args_definition}.
	 * @param ctx the parse tree
	 */
	void enterArgs_definition(HelloParser.Args_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#args_definition}.
	 * @param ctx the parse tree
	 */
	void exitArgs_definition(HelloParser.Args_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#one_arg}.
	 * @param ctx the parse tree
	 */
	void enterOne_arg(HelloParser.One_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#one_arg}.
	 * @param ctx the parse tree
	 */
	void exitOne_arg(HelloParser.One_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_calling}.
	 * @param ctx the parse tree
	 */
	void enterFunction_calling(HelloParser.Function_callingContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_calling}.
	 * @param ctx the parse tree
	 */
	void exitFunction_calling(HelloParser.Function_callingContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(HelloParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(HelloParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(HelloParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(HelloParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#local_statement}.
	 * @param ctx the parse tree
	 */
	void enterLocal_statement(HelloParser.Local_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#local_statement}.
	 * @param ctx the parse tree
	 */
	void exitLocal_statement(HelloParser.Local_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(HelloParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(HelloParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(HelloParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(HelloParser.ConditionContext ctx);
}