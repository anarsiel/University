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
	 * Visit a parse tree produced by {@link HelloParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(HelloParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#global_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_statement(HelloParser.Global_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_definition(HelloParser.Function_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_header(HelloParser.Function_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_body(HelloParser.Function_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#args_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs_definition(HelloParser.Args_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#one_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOne_arg(HelloParser.One_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_calling}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_calling(HelloParser.Function_callingContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HelloParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(HelloParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#local_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocal_statement(HelloParser.Local_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(HelloParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(HelloParser.ConditionContext ctx);
}