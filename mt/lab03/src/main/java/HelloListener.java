// Generated from /Users/admin/Documents/University/#GitHub/mt/lab03/src/main/java/Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#all_file}.
	 * @param ctx the parse tree
	 */
	void enterAll_file(HelloParser.All_fileContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#all_file}.
	 * @param ctx the parse tree
	 */
	void exitAll_file(HelloParser.All_fileContext ctx);
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
	 * Enter a parse tree produced by {@link HelloParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(HelloParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(HelloParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_h}.
	 * @param ctx the parse tree
	 */
	void enterFunction_h(HelloParser.Function_hContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_h}.
	 * @param ctx the parse tree
	 */
	void exitFunction_h(HelloParser.Function_hContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#main_function_part}.
	 * @param ctx the parse tree
	 */
	void enterMain_function_part(HelloParser.Main_function_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#main_function_part}.
	 * @param ctx the parse tree
	 */
	void exitMain_function_part(HelloParser.Main_function_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(HelloParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(HelloParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(HelloParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(HelloParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_usage}.
	 * @param ctx the parse tree
	 */
	void enterFunction_usage(HelloParser.Function_usageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_usage}.
	 * @param ctx the parse tree
	 */
	void exitFunction_usage(HelloParser.Function_usageContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_type}.
	 * @param ctx the parse tree
	 */
	void enterFunction_type(HelloParser.Function_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_type}.
	 * @param ctx the parse tree
	 */
	void exitFunction_type(HelloParser.Function_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(HelloParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(HelloParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#assignation}.
	 * @param ctx the parse tree
	 */
	void enterAssignation(HelloParser.AssignationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#assignation}.
	 * @param ctx the parse tree
	 */
	void exitAssignation(HelloParser.AssignationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(HelloParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(HelloParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#condition_operation}.
	 * @param ctx the parse tree
	 */
	void enterCondition_operation(HelloParser.Condition_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#condition_operation}.
	 * @param ctx the parse tree
	 */
	void exitCondition_operation(HelloParser.Condition_operationContext ctx);
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