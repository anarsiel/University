// Generated from /Users/admin/Documents/University/#GitHub/mt/lab03/src/main/java/Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#all_file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll_file(HelloParser.All_fileContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#global_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_statement(HelloParser.Global_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(HelloParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_h}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_h(HelloParser.Function_hContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#main_function_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain_function_part(HelloParser.Main_function_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(HelloParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(HelloParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_usage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_usage(HelloParser.Function_usageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_type(HelloParser.Function_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(HelloParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#assignation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignation(HelloParser.AssignationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(HelloParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#condition_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_operation(HelloParser.Condition_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(HelloParser.ConditionContext ctx);
}