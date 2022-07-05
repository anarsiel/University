// Generated from /Users/admin/Documents/University/#GitHub/mt/lab03/src/main/java/Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BASE_TYPE=1, INTEGER=2, DOUBLE=3, BOOL=4, WORD=5, WHITESPACE=6, COMMA=7, 
		LP=8, RP=9, COLON=10, DCOLON=11, SCOLON=12, EQ=13, STICK=14, ARROW=15, 
		PLUS=16, MINUS=17, DIV=18, MUL=19, OST=20, OR=21, AND=22, LESS=23, GREATER=24, 
		DEQ=25;
	public static final int
		RULE_code = 0, RULE_global_statement = 1, RULE_function_definition = 2, 
		RULE_function_header = 3, RULE_function_body = 4, RULE_args_definition = 5, 
		RULE_one_arg = 6, RULE_function_calling = 7, RULE_type = 8, RULE_data = 9, 
		RULE_local_statement = 10, RULE_expr = 11, RULE_condition = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"code", "global_statement", "function_definition", "function_header", 
			"function_body", "args_definition", "one_arg", "function_calling", "type", 
			"data", "local_statement", "expr", "condition"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "','", "'('", "')'", "':'", 
			"'::'", "';'", "'='", "'|'", "'->'", "'+'", "'-'", "'/'", "'*'", "'%'", 
			"'||'", "'&&'", "'<'", "'>'", "'=='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BASE_TYPE", "INTEGER", "DOUBLE", "BOOL", "WORD", "WHITESPACE", 
			"COMMA", "LP", "RP", "COLON", "DCOLON", "SCOLON", "EQ", "STICK", "ARROW", 
			"PLUS", "MINUS", "DIV", "MUL", "OST", "OR", "AND", "LESS", "GREATER", 
			"DEQ"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HelloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CodeContext extends ParserRuleContext {
		public StringBuilder val;
		public Global_statementContext gs;
		public TerminalNode EOF() { return getToken(HelloParser.EOF, 0); }
		public List<Global_statementContext> global_statement() {
			return getRuleContexts(Global_statementContext.class);
		}
		public Global_statementContext global_statement(int i) {
			return getRuleContext(Global_statementContext.class,i);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((CodeContext)_localctx).val =  new StringBuilder(); 
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				((CodeContext)_localctx).gs = global_statement();
				 _localctx.val.append("\n" + ((CodeContext)_localctx).gs.val); 
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DOUBLE) | (1L << BOOL) | (1L << WORD))) != 0) );
			setState(34);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Global_statementContext extends ParserRuleContext {
		public StringBuilder val;
		public Function_definitionContext fd;
		public Local_statementContext local_statement;
		public Function_definitionContext function_definition() {
			return getRuleContext(Function_definitionContext.class,0);
		}
		public Local_statementContext local_statement() {
			return getRuleContext(Local_statementContext.class,0);
		}
		public Global_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterGlobal_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitGlobal_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitGlobal_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_statementContext global_statement() throws RecognitionException {
		Global_statementContext _localctx = new Global_statementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_global_statement);
		try {
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				((Global_statementContext)_localctx).fd = function_definition();
				 ((Global_statementContext)_localctx).val =  ((Global_statementContext)_localctx).fd.val; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				((Global_statementContext)_localctx).local_statement = local_statement();
				 ((Global_statementContext)_localctx).val =  new StringBuilder((((Global_statementContext)_localctx).local_statement!=null?_input.getText(((Global_statementContext)_localctx).local_statement.start,((Global_statementContext)_localctx).local_statement.stop):null));
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_definitionContext extends ParserRuleContext {
		public StringBuilder val;
		public Function_headerContext fh;
		public Function_bodyContext fb;
		public Function_headerContext function_header() {
			return getRuleContext(Function_headerContext.class,0);
		}
		public List<Function_bodyContext> function_body() {
			return getRuleContexts(Function_bodyContext.class);
		}
		public Function_bodyContext function_body(int i) {
			return getRuleContext(Function_bodyContext.class,i);
		}
		public Function_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_definitionContext function_definition() throws RecognitionException {
		Function_definitionContext _localctx = new Function_definitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_function_definition);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			 ((Function_definitionContext)_localctx).val =  new StringBuilder(); 
			{
			setState(45);
			((Function_definitionContext)_localctx).fh = function_header();
			 if (((Function_definitionContext)_localctx).fh.arg_types.size() > 1) {_localctx.val.append(((Function_definitionContext)_localctx).fh.val);}
			}
			setState(51); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(48);
					((Function_definitionContext)_localctx).fb = function_body();

					        if (((Function_definitionContext)_localctx).fh.arg_types.size() > 1) {
					            _localctx.val.append("{\n  ");
					            for (int i = ((Function_definitionContext)_localctx).fh.arg_types.size() - 1; i > 0; i--) {
					                _localctx.val.append(((Function_definitionContext)_localctx).fh.arg_types.get(i) + " " + ((Function_definitionContext)_localctx).fb.args_names.get(i - 1) + " = a" + (((Function_definitionContext)_localctx).fh.arg_types.size() - i) + ";\n  ");
					            }
					            _localctx.val.append("if (");

					            for (int j = 0; j < ((Function_definitionContext)_localctx).fb.args_conds.size(); j++) {
					                _localctx.val.append("(" + ((Function_definitionContext)_localctx).fb.args_conds.get(j) + ")" + (j != ((Function_definitionContext)_localctx).fb.args_conds.size() - 1 ? " && " : ")"));
					            }
					            _localctx.val.append(" {\n    return " + ((Function_definitionContext)_localctx).fb.body + ";\n  }\n}\n");
					        } else {
					            _localctx.val.append(((Function_definitionContext)_localctx).fh.arg_types.get(0) + " " + ((Function_definitionContext)_localctx).fb.args_names.get(0) + " = " + ((Function_definitionContext)_localctx).fb.body);
					        }
					    
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(53); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			_localctx.val.append("}\n");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_headerContext extends ParserRuleContext {
		public String val;
		public ArrayList<String> arg_types;
		public Token WORD;
		public TypeContext type;
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode DCOLON() { return getToken(HelloParser.DCOLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Function_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_header(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_header(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_headerContext function_header() throws RecognitionException {
		Function_headerContext _localctx = new Function_headerContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			((Function_headerContext)_localctx).WORD = match(WORD);
			setState(58);
			match(DCOLON);
			setState(59);
			((Function_headerContext)_localctx).type = type();

			        ((Function_headerContext)_localctx).arg_types =  ((Function_headerContext)_localctx).type.val;
			        ((Function_headerContext)_localctx).val =  _localctx.arg_types.get(0) + ' ' + (((Function_headerContext)_localctx).WORD!=null?((Function_headerContext)_localctx).WORD.getText():null) + "(";

			        for (int i = 1; i < _localctx.arg_types.size(); i++) {
			            _localctx.val += _localctx.arg_types.get(_localctx.arg_types.size() - i - 1) + " a" + i + (i == _localctx.arg_types.size() - 1 ? ") {\n" : ", ");
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_bodyContext extends ParserRuleContext {
		public String body;
		public ArrayList<String> args_names;
		public ArrayList<String> args_conds;
		public Args_definitionContext ad;
		public Local_statementContext local_statement;
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode EQ() { return getToken(HelloParser.EQ, 0); }
		public Local_statementContext local_statement() {
			return getRuleContext(Local_statementContext.class,0);
		}
		public Args_definitionContext args_definition() {
			return getRuleContext(Args_definitionContext.class,0);
		}
		public Function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_bodyContext function_body() throws RecognitionException {
		Function_bodyContext _localctx = new Function_bodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(WORD);
			setState(63);
			((Function_bodyContext)_localctx).ad = args_definition();
			setState(64);
			match(EQ);
			setState(65);
			((Function_bodyContext)_localctx).local_statement = local_statement();

			    ((Function_bodyContext)_localctx).body =  (((Function_bodyContext)_localctx).local_statement!=null?_input.getText(((Function_bodyContext)_localctx).local_statement.start,((Function_bodyContext)_localctx).local_statement.stop):null);
			    ((Function_bodyContext)_localctx).args_names =  ((Function_bodyContext)_localctx).ad.args_names;
			    ((Function_bodyContext)_localctx).args_conds =  ((Function_bodyContext)_localctx).ad.args_conds;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Args_definitionContext extends ParserRuleContext {
		public ArrayList<String> args_names;
		public ArrayList<String> args_conds;
		public One_argContext one_arg;
		public Args_definitionContext ad;
		public One_argContext one_arg() {
			return getRuleContext(One_argContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(HelloParser.COMMA, 0); }
		public Args_definitionContext args_definition() {
			return getRuleContext(Args_definitionContext.class,0);
		}
		public Args_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterArgs_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitArgs_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitArgs_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Args_definitionContext args_definition() throws RecognitionException {
		Args_definitionContext _localctx = new Args_definitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_args_definition);
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				((Args_definitionContext)_localctx).one_arg = one_arg();
				setState(69);
				match(COMMA);
				setState(70);
				((Args_definitionContext)_localctx).ad = args_definition();

				        ((Args_definitionContext)_localctx).args_names =  ((Args_definitionContext)_localctx).ad.args_names;
				        _localctx.args_names.add(((Args_definitionContext)_localctx).one_arg.arg_name);
				        ((Args_definitionContext)_localctx).args_conds =  ((Args_definitionContext)_localctx).ad.args_conds;
				        if (((Args_definitionContext)_localctx).one_arg.arg_cond != null)
				            _localctx.args_conds.add(((Args_definitionContext)_localctx).one_arg.arg_cond);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				((Args_definitionContext)_localctx).one_arg = one_arg();

				        ((Args_definitionContext)_localctx).args_names =  new ArrayList<String>();
				        _localctx.args_names.add(((Args_definitionContext)_localctx).one_arg.arg_name);
				        ((Args_definitionContext)_localctx).args_conds =  new ArrayList<String>();
				        if (((Args_definitionContext)_localctx).one_arg.arg_cond != null)
				            _localctx.args_conds.add(((Args_definitionContext)_localctx).one_arg.arg_cond);
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				((Args_definitionContext)_localctx).args_names =  null; ((Args_definitionContext)_localctx).args_conds =  null;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class One_argContext extends ParserRuleContext {
		public String arg_name;
		public String arg_cond;
		public One_argContext oa;
		public Token WORD;
		public ConditionContext condition;
		public TerminalNode LP() { return getToken(HelloParser.LP, 0); }
		public TerminalNode RP() { return getToken(HelloParser.RP, 0); }
		public One_argContext one_arg() {
			return getRuleContext(One_argContext.class,0);
		}
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode STICK() { return getToken(HelloParser.STICK, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public One_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_one_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterOne_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitOne_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitOne_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final One_argContext one_arg() throws RecognitionException {
		One_argContext _localctx = new One_argContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_one_arg);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(LP);
				setState(80);
				((One_argContext)_localctx).oa = one_arg();
				setState(81);
				match(RP);
				 ((One_argContext)_localctx).arg_name =  ((One_argContext)_localctx).oa.arg_name; ((One_argContext)_localctx).arg_cond =  ((One_argContext)_localctx).oa.arg_cond; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((One_argContext)_localctx).WORD = match(WORD);
				setState(85);
				match(STICK);
				setState(86);
				((One_argContext)_localctx).condition = condition();
				 ((One_argContext)_localctx).arg_name =  (((One_argContext)_localctx).WORD!=null?((One_argContext)_localctx).WORD.getText():null); ((One_argContext)_localctx).arg_cond =  (((One_argContext)_localctx).condition!=null?_input.getText(((One_argContext)_localctx).condition.start,((One_argContext)_localctx).condition.stop):null); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				((One_argContext)_localctx).WORD = match(WORD);
				 ((One_argContext)_localctx).arg_name =  (((One_argContext)_localctx).WORD!=null?((One_argContext)_localctx).WORD.getText():null); ((One_argContext)_localctx).arg_cond =  "True"; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_callingContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode LP() { return getToken(HelloParser.LP, 0); }
		public TerminalNode RP() { return getToken(HelloParser.RP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HelloParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HelloParser.COMMA, i);
		}
		public Function_callingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_calling; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_calling(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_calling(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_calling(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callingContext function_calling() throws RecognitionException {
		Function_callingContext _localctx = new Function_callingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_calling);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(WORD);
			setState(94);
			match(LP);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DOUBLE) | (1L << BOOL) | (1L << WORD))) != 0)) {
				{
				setState(95);
				expr();
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(96);
					match(COMMA);
					setState(97);
					expr();
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(105);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ArrayList<String> val;
		public Token BASE_TYPE;
		public TypeContext tp;
		public TerminalNode BASE_TYPE() { return getToken(HelloParser.BASE_TYPE, 0); }
		public TerminalNode ARROW() { return getToken(HelloParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				((TypeContext)_localctx).BASE_TYPE = match(BASE_TYPE);
				((TypeContext)_localctx).val =  new ArrayList<String>(); _localctx.val.add((((TypeContext)_localctx).BASE_TYPE!=null?((TypeContext)_localctx).BASE_TYPE.getText():null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				((TypeContext)_localctx).BASE_TYPE = match(BASE_TYPE);
				setState(110);
				match(ARROW);
				setState(111);
				((TypeContext)_localctx).tp = type();
				((TypeContext)_localctx).val =  ((TypeContext)_localctx).tp.val; _localctx.val.add((((TypeContext)_localctx).BASE_TYPE!=null?((TypeContext)_localctx).BASE_TYPE.getText():null));
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(HelloParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(HelloParser.DOUBLE, 0); }
		public TerminalNode BOOL() { return getToken(HelloParser.BOOL, 0); }
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public Function_callingContext function_calling() {
			return getRuleContext(Function_callingContext.class,0);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_data);
		try {
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(DOUBLE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(BOOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				match(WORD);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				function_calling();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_statementContext extends ParserRuleContext {
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public TerminalNode EQ() { return getToken(HelloParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Local_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterLocal_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitLocal_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitLocal_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_statementContext local_statement() throws RecognitionException {
		Local_statementContext _localctx = new Local_statementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_local_statement);
		try {
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				data();
				setState(124);
				match(EQ);
				setState(125);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(HelloParser.PLUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(HelloParser.MINUS, 0); }
		public TerminalNode DIV() { return getToken(HelloParser.DIV, 0); }
		public TerminalNode MUL() { return getToken(HelloParser.MUL, 0); }
		public TerminalNode OST() { return getToken(HelloParser.OST, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				data();
				setState(131);
				match(PLUS);
				setState(132);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				data();
				setState(135);
				match(MINUS);
				setState(136);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				data();
				setState(139);
				match(DIV);
				setState(140);
				expr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				data();
				setState(143);
				match(MUL);
				setState(144);
				expr();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				data();
				setState(147);
				match(OST);
				setState(148);
				expr();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(150);
				data();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LESS() { return getToken(HelloParser.LESS, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(HelloParser.GREATER, 0); }
		public TerminalNode AND() { return getToken(HelloParser.AND, 0); }
		public TerminalNode OR() { return getToken(HelloParser.OR, 0); }
		public TerminalNode DEQ() { return getToken(HelloParser.DEQ, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_condition);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				expr();
				setState(154);
				match(LESS);
				setState(155);
				condition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				expr();
				setState(158);
				match(GREATER);
				setState(159);
				condition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(161);
				expr();
				setState(162);
				match(AND);
				setState(163);
				condition();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				expr();
				setState(166);
				match(OR);
				setState(167);
				condition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(169);
				expr();
				setState(170);
				match(DEQ);
				setState(171);
				condition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(173);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00b3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\6\2!\n\2\r\2\16\2\"\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3-\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6"+
		"\4\66\n\4\r\4\16\4\67\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7P\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b^\n\b\3\t\3\t\3\t\3\t\3\t\7\te\n\t\f\t"+
		"\16\th\13\t\5\tj\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nu\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\5\13|\n\13\3\f\3\f\3\f\3\f\3\f\5\f\u0083\n\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\5\r\u009a\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00b1"+
		"\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2\u00be\2\34\3"+
		"\2\2\2\4,\3\2\2\2\6.\3\2\2\2\b;\3\2\2\2\n@\3\2\2\2\fO\3\2\2\2\16]\3\2"+
		"\2\2\20_\3\2\2\2\22t\3\2\2\2\24{\3\2\2\2\26\u0082\3\2\2\2\30\u0099\3\2"+
		"\2\2\32\u00b0\3\2\2\2\34 \b\2\1\2\35\36\5\4\3\2\36\37\b\2\1\2\37!\3\2"+
		"\2\2 \35\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#$\3\2\2\2$%\7\2\2\3"+
		"%\3\3\2\2\2&\'\5\6\4\2\'(\b\3\1\2(-\3\2\2\2)*\5\26\f\2*+\b\3\1\2+-\3\2"+
		"\2\2,&\3\2\2\2,)\3\2\2\2-\5\3\2\2\2./\b\4\1\2/\60\5\b\5\2\60\61\b\4\1"+
		"\2\61\65\3\2\2\2\62\63\5\n\6\2\63\64\b\4\1\2\64\66\3\2\2\2\65\62\3\2\2"+
		"\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\289\3\2\2\29:\b\4\1\2:\7\3\2"+
		"\2\2;<\7\7\2\2<=\7\r\2\2=>\5\22\n\2>?\b\5\1\2?\t\3\2\2\2@A\7\7\2\2AB\5"+
		"\f\7\2BC\7\17\2\2CD\5\26\f\2DE\b\6\1\2E\13\3\2\2\2FG\5\16\b\2GH\7\t\2"+
		"\2HI\5\f\7\2IJ\b\7\1\2JP\3\2\2\2KL\5\16\b\2LM\b\7\1\2MP\3\2\2\2NP\b\7"+
		"\1\2OF\3\2\2\2OK\3\2\2\2ON\3\2\2\2P\r\3\2\2\2QR\7\n\2\2RS\5\16\b\2ST\7"+
		"\13\2\2TU\b\b\1\2U^\3\2\2\2VW\7\7\2\2WX\7\20\2\2XY\5\32\16\2YZ\b\b\1\2"+
		"Z^\3\2\2\2[\\\7\7\2\2\\^\b\b\1\2]Q\3\2\2\2]V\3\2\2\2][\3\2\2\2^\17\3\2"+
		"\2\2_`\7\7\2\2`i\7\n\2\2af\5\30\r\2bc\7\t\2\2ce\5\30\r\2db\3\2\2\2eh\3"+
		"\2\2\2fd\3\2\2\2fg\3\2\2\2gj\3\2\2\2hf\3\2\2\2ia\3\2\2\2ij\3\2\2\2jk\3"+
		"\2\2\2kl\7\13\2\2l\21\3\2\2\2mn\7\3\2\2nu\b\n\1\2op\7\3\2\2pq\7\21\2\2"+
		"qr\5\22\n\2rs\b\n\1\2su\3\2\2\2tm\3\2\2\2to\3\2\2\2u\23\3\2\2\2v|\7\4"+
		"\2\2w|\7\5\2\2x|\7\6\2\2y|\7\7\2\2z|\5\20\t\2{v\3\2\2\2{w\3\2\2\2{x\3"+
		"\2\2\2{y\3\2\2\2{z\3\2\2\2|\25\3\2\2\2}~\5\24\13\2~\177\7\17\2\2\177\u0080"+
		"\5\30\r\2\u0080\u0083\3\2\2\2\u0081\u0083\5\30\r\2\u0082}\3\2\2\2\u0082"+
		"\u0081\3\2\2\2\u0083\27\3\2\2\2\u0084\u0085\5\24\13\2\u0085\u0086\7\22"+
		"\2\2\u0086\u0087\5\30\r\2\u0087\u009a\3\2\2\2\u0088\u0089\5\24\13\2\u0089"+
		"\u008a\7\23\2\2\u008a\u008b\5\30\r\2\u008b\u009a\3\2\2\2\u008c\u008d\5"+
		"\24\13\2\u008d\u008e\7\24\2\2\u008e\u008f\5\30\r\2\u008f\u009a\3\2\2\2"+
		"\u0090\u0091\5\24\13\2\u0091\u0092\7\25\2\2\u0092\u0093\5\30\r\2\u0093"+
		"\u009a\3\2\2\2\u0094\u0095\5\24\13\2\u0095\u0096\7\26\2\2\u0096\u0097"+
		"\5\30\r\2\u0097\u009a\3\2\2\2\u0098\u009a\5\24\13\2\u0099\u0084\3\2\2"+
		"\2\u0099\u0088\3\2\2\2\u0099\u008c\3\2\2\2\u0099\u0090\3\2\2\2\u0099\u0094"+
		"\3\2\2\2\u0099\u0098\3\2\2\2\u009a\31\3\2\2\2\u009b\u009c\5\30\r\2\u009c"+
		"\u009d\7\31\2\2\u009d\u009e\5\32\16\2\u009e\u00b1\3\2\2\2\u009f\u00a0"+
		"\5\30\r\2\u00a0\u00a1\7\32\2\2\u00a1\u00a2\5\32\16\2\u00a2\u00b1\3\2\2"+
		"\2\u00a3\u00a4\5\30\r\2\u00a4\u00a5\7\30\2\2\u00a5\u00a6\5\32\16\2\u00a6"+
		"\u00b1\3\2\2\2\u00a7\u00a8\5\30\r\2\u00a8\u00a9\7\27\2\2\u00a9\u00aa\5"+
		"\32\16\2\u00aa\u00b1\3\2\2\2\u00ab\u00ac\5\30\r\2\u00ac\u00ad\7\33\2\2"+
		"\u00ad\u00ae\5\32\16\2\u00ae\u00b1\3\2\2\2\u00af\u00b1\5\30\r\2\u00b0"+
		"\u009b\3\2\2\2\u00b0\u009f\3\2\2\2\u00b0\u00a3\3\2\2\2\u00b0\u00a7\3\2"+
		"\2\2\u00b0\u00ab\3\2\2\2\u00b0\u00af\3\2\2\2\u00b1\33\3\2\2\2\16\",\67"+
		"O]fit{\u0082\u0099\u00b0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}