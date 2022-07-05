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
		BASE_TYPE=1, INTEGER=2, DOUBLE=3, BOOL=4, WORD=5, ANY_WORD=6, WHITESPACE=7, 
		COMMA=8, OPENBRACE=9, CLOSEBRACE=10, COLON=11, DCOLON=12, SCOLON=13, PIPE=14, 
		ARROW=15, ASSGM=16, ADD=17, SUB=18, MUL=19, FAC=20, DIV=21, MOD=22, EQ=23, 
		NEQ=24, LES=25, GRE=26, LE=27, GE=28, AND=29, OR=30;
	public static final int
		RULE_all_file = 0, RULE_global_statement = 1, RULE_function = 2, RULE_function_h = 3, 
		RULE_main_function_part = 4, RULE_arguments = 5, RULE_argument = 6, RULE_function_usage = 7, 
		RULE_function_type = 8, RULE_element = 9, RULE_assignation = 10, RULE_expression = 11, 
		RULE_condition_operation = 12, RULE_condition = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"all_file", "global_statement", "function", "function_h", "main_function_part", 
			"arguments", "argument", "function_usage", "function_type", "element", 
			"assignation", "expression", "condition_operation", "condition"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "','", "'('", "')'", 
			"':'", "'::'", "';'", "'|'", "'->'", "'='", "'+'", "'-'", "'*'", "'!'", 
			"'/'", "'%'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BASE_TYPE", "INTEGER", "DOUBLE", "BOOL", "WORD", "ANY_WORD", "WHITESPACE", 
			"COMMA", "OPENBRACE", "CLOSEBRACE", "COLON", "DCOLON", "SCOLON", "PIPE", 
			"ARROW", "ASSGM", "ADD", "SUB", "MUL", "FAC", "DIV", "MOD", "EQ", "NEQ", 
			"LES", "GRE", "LE", "GE", "AND", "OR"
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

	public static class All_fileContext extends ParserRuleContext {
		public StringBuilder val;
		public Global_statementContext gs;
		public TerminalNode EOF() { return getToken(HelloParser.EOF, 0); }
		public List<Global_statementContext> global_statement() {
			return getRuleContexts(Global_statementContext.class);
		}
		public Global_statementContext global_statement(int i) {
			return getRuleContext(Global_statementContext.class,i);
		}
		public All_fileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAll_file(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAll_file(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAll_file(this);
			else return visitor.visitChildren(this);
		}
	}

	public final All_fileContext all_file() throws RecognitionException {
		All_fileContext _localctx = new All_fileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_all_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((All_fileContext)_localctx).val =  new StringBuilder(); 
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				((All_fileContext)_localctx).gs = global_statement();
				 _localctx.val.append("\n" + ((All_fileContext)_localctx).gs.val); 
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DOUBLE) | (1L << BOOL) | (1L << WORD))) != 0) );
			setState(36);
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
		public FunctionContext fd;
		public AssignationContext assignation;
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
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
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				((Global_statementContext)_localctx).fd = function();
				 ((Global_statementContext)_localctx).val =  ((Global_statementContext)_localctx).fd.val; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				((Global_statementContext)_localctx).assignation = assignation();
				 ((Global_statementContext)_localctx).val =  new StringBuilder((((Global_statementContext)_localctx).assignation!=null?_input.getText(((Global_statementContext)_localctx).assignation.start,((Global_statementContext)_localctx).assignation.stop):null));
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

	public static class FunctionContext extends ParserRuleContext {
		public StringBuilder val;
		public Function_hContext fh;
		public Main_function_partContext fb;
		public Function_hContext function_h() {
			return getRuleContext(Function_hContext.class,0);
		}
		public List<Main_function_partContext> main_function_part() {
			return getRuleContexts(Main_function_partContext.class);
		}
		public Main_function_partContext main_function_part(int i) {
			return getRuleContext(Main_function_partContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_function);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			 ((FunctionContext)_localctx).val =  new StringBuilder();
			{
			setState(47);
			((FunctionContext)_localctx).fh = function_h();

			        if (((FunctionContext)_localctx).fh.arg_types.size() >= 1) {
			            _localctx.val.append(((FunctionContext)_localctx).fh.val);
			        }
			    
			}
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(50);
				((FunctionContext)_localctx).fb = main_function_part();

				        if (((FunctionContext)_localctx).fh.arg_types.size() > 1) {
				            for (int i = ((FunctionContext)_localctx).fh.arg_types.size() - 1; i > 0; i--) {
				                _localctx.val.append("  ");
				                _localctx.val.append(((FunctionContext)_localctx).fh.arg_types.get(i) + " " + ((FunctionContext)_localctx).fb.args_names.get(i - 1) + " = arg" + (((FunctionContext)_localctx).fh.arg_types.size() - i - 1) + ";\n");
				            }
				            Boolean started = false;
				            int cntEQ = 0;
				            for (int j = 0; j < ((FunctionContext)_localctx).fb.args_conds.size(); j++) {
				                if (((FunctionContext)_localctx).fb.args_conds.get(j) != "True") {
				                    cntEQ += 1;
				                }
				            }

				            if (cntEQ > 0) {
				                _localctx.val.append("\n  if ");
				                if (cntEQ > 1) {
				                    _localctx.val.append("(");
				                }
				                for (int j = 0; j < ((FunctionContext)_localctx).fb.args_conds.size(); j++) {
				                    if (((FunctionContext)_localctx).fb.args_conds.get(j) == "True") continue;
				                    if (started) {
				                        _localctx.val.append(" && ");
				                    }
				                    started = true;
				                    _localctx.val.append("(" + ((FunctionContext)_localctx).fb.args_conds.get(j) + ")");
				                }
				                if (cntEQ > 1) {
				                    _localctx.val.append(")");
				                }
				                _localctx.val.append(" {\n    return " + ((FunctionContext)_localctx).fb.body + ";\n  }\n");
				            }
				        } else {
				            _localctx.val.append(") {\n    " + ((FunctionContext)_localctx).fb.body + ";\n");
				        }
				    
				}
				break;
			}
			setState(58); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(55);
					((FunctionContext)_localctx).fb = main_function_part();

					            if (((FunctionContext)_localctx).fh.arg_types.size() > 1) {
					                Boolean started = false;
					                    int cntEQ = 0;
					                    for (int j = 0; j < ((FunctionContext)_localctx).fb.args_conds.size(); j++) {
					                        if (((FunctionContext)_localctx).fb.args_conds.get(j) != "True") {
					                            cntEQ += 1;
					                        }
					                    }

					                    if (cntEQ > 0) {
					                        _localctx.val.append("\n  if ");
					                        if (cntEQ > 1) {
					                            _localctx.val.append("(");
					                        }
					                        for (int j = 0; j < ((FunctionContext)_localctx).fb.args_conds.size(); j++) {
					                            if (((FunctionContext)_localctx).fb.args_conds.get(j) == "True") continue;
					                            if (started) {
					                                _localctx.val.append(" && ");
					                            }
					                            started = true;
					                            _localctx.val.append("(" + ((FunctionContext)_localctx).fb.args_conds.get(j) + ")");
					                        }
					                        if (cntEQ > 1) {
					                            _localctx.val.append(")");
					                        }
					                        _localctx.val.append(" {\n    return " + ((FunctionContext)_localctx).fb.body + ";\n  }\n");
					                    } else {
					                        _localctx.val.append("\n  return " + ((FunctionContext)_localctx).fb.body + ";\n");
					                    }
					            } else {
					                _localctx.val.append(") {\n    " + ((FunctionContext)_localctx).fb.body + ";\n");
					            }
					        
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(60); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class Function_hContext extends ParserRuleContext {
		public String val;
		public ArrayList<String> arg_types;
		public Token WORD;
		public Function_typeContext function_type;
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode DCOLON() { return getToken(HelloParser.DCOLON, 0); }
		public Function_typeContext function_type() {
			return getRuleContext(Function_typeContext.class,0);
		}
		public Function_hContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_h; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_h(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_h(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_h(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_hContext function_h() throws RecognitionException {
		Function_hContext _localctx = new Function_hContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_h);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			((Function_hContext)_localctx).WORD = match(WORD);
			setState(65);
			match(DCOLON);
			setState(66);
			((Function_hContext)_localctx).function_type = function_type();

			        ((Function_hContext)_localctx).arg_types =  ((Function_hContext)_localctx).function_type.val;
			        ((Function_hContext)_localctx).val =  _localctx.arg_types.get(0) + ' ' + (((Function_hContext)_localctx).WORD!=null?((Function_hContext)_localctx).WORD.getText():null) + "(";

			        for (int i = 0; i < _localctx.arg_types.size() - 1; i++) {
			            _localctx.val += _localctx.arg_types.get(_localctx.arg_types.size() - i - 1) + " arg" + i + (i == _localctx.arg_types.size() - 2? ") {\n" : ", ");
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

	public static class Main_function_partContext extends ParserRuleContext {
		public String body;
		public ArrayList<String> args_names;
		public ArrayList<String> args_conds;
		public ArgumentsContext ad;
		public AssignationContext assignation;
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode ASSGM() { return getToken(HelloParser.ASSGM, 0); }
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Main_function_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main_function_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterMain_function_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitMain_function_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitMain_function_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Main_function_partContext main_function_part() throws RecognitionException {
		Main_function_partContext _localctx = new Main_function_partContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_main_function_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(WORD);
			setState(70);
			((Main_function_partContext)_localctx).ad = arguments();
			setState(71);
			match(ASSGM);
			setState(72);
			((Main_function_partContext)_localctx).assignation = assignation();

			    ((Main_function_partContext)_localctx).body =  ((Main_function_partContext)_localctx).assignation.view;
			    ((Main_function_partContext)_localctx).args_names =  ((Main_function_partContext)_localctx).ad.args_names;
			    ((Main_function_partContext)_localctx).args_conds =  ((Main_function_partContext)_localctx).ad.args_conds;

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

	public static class ArgumentsContext extends ParserRuleContext {
		public ArrayList<String> args_names;
		public ArrayList<String> args_conds;
		public ArgumentContext argument;
		public ArgumentsContext ad;
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(HelloParser.COMMA, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arguments);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				((ArgumentsContext)_localctx).argument = argument();
				setState(76);
				match(COMMA);
				setState(77);
				((ArgumentsContext)_localctx).ad = arguments();

				        ((ArgumentsContext)_localctx).args_names =  ((ArgumentsContext)_localctx).ad.args_names;
				        _localctx.args_names.add(((ArgumentsContext)_localctx).argument.arg_name);
				        ((ArgumentsContext)_localctx).args_conds =  ((ArgumentsContext)_localctx).ad.args_conds;
				        if (((ArgumentsContext)_localctx).argument.arg_cond != null)
				            _localctx.args_conds.add(((ArgumentsContext)_localctx).argument.arg_cond);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				((ArgumentsContext)_localctx).argument = argument();

				        ((ArgumentsContext)_localctx).args_names =  new ArrayList<String>();
				        _localctx.args_names.add(((ArgumentsContext)_localctx).argument.arg_name);
				        ((ArgumentsContext)_localctx).args_conds =  new ArrayList<String>();
				        if (((ArgumentsContext)_localctx).argument.arg_cond != null)
				            _localctx.args_conds.add(((ArgumentsContext)_localctx).argument.arg_cond);
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				((ArgumentsContext)_localctx).args_names =  null; ((ArgumentsContext)_localctx).args_conds =  null;
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

	public static class ArgumentContext extends ParserRuleContext {
		public String arg_name;
		public String arg_cond;
		public ArgumentContext oa;
		public Token WORD;
		public ConditionContext condition;
		public TerminalNode OPENBRACE() { return getToken(HelloParser.OPENBRACE, 0); }
		public TerminalNode CLOSEBRACE() { return getToken(HelloParser.CLOSEBRACE, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode PIPE() { return getToken(HelloParser.PIPE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_argument);
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				match(OPENBRACE);
				setState(87);
				((ArgumentContext)_localctx).oa = argument();
				setState(88);
				match(CLOSEBRACE);
				 ((ArgumentContext)_localctx).arg_name =  ((ArgumentContext)_localctx).oa.arg_name; ((ArgumentContext)_localctx).arg_cond =  ((ArgumentContext)_localctx).oa.arg_cond; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				((ArgumentContext)_localctx).WORD = match(WORD);
				setState(92);
				match(PIPE);
				setState(93);
				((ArgumentContext)_localctx).condition = condition();
				 ((ArgumentContext)_localctx).arg_name =  (((ArgumentContext)_localctx).WORD!=null?((ArgumentContext)_localctx).WORD.getText():null); ((ArgumentContext)_localctx).arg_cond =  ((ArgumentContext)_localctx).condition.view; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				((ArgumentContext)_localctx).WORD = match(WORD);
				 ((ArgumentContext)_localctx).arg_name =  (((ArgumentContext)_localctx).WORD!=null?((ArgumentContext)_localctx).WORD.getText():null); ((ArgumentContext)_localctx).arg_cond =  "True"; 
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

	public static class Function_usageContext extends ParserRuleContext {
		public String view;
		public Token WORD;
		public Token OPENBRACE;
		public ExpressionContext expression;
		public Token COMMA;
		public Token CLOSEBRACE;
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public TerminalNode OPENBRACE() { return getToken(HelloParser.OPENBRACE, 0); }
		public TerminalNode CLOSEBRACE() { return getToken(HelloParser.CLOSEBRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HelloParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HelloParser.COMMA, i);
		}
		public Function_usageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_usage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_usage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_usage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_usage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_usageContext function_usage() throws RecognitionException {
		Function_usageContext _localctx = new Function_usageContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_usage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			            ((Function_usageContext)_localctx).view =  "";
			        
			setState(101);
			((Function_usageContext)_localctx).WORD = match(WORD);
			setState(102);
			((Function_usageContext)_localctx).OPENBRACE = match(OPENBRACE);

			            _localctx.view += (((Function_usageContext)_localctx).WORD!=null?((Function_usageContext)_localctx).WORD.getText():null) + (((Function_usageContext)_localctx).OPENBRACE!=null?((Function_usageContext)_localctx).OPENBRACE.getText():null);
			        
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DOUBLE) | (1L << BOOL) | (1L << WORD))) != 0)) {
				{
				setState(104);
				((Function_usageContext)_localctx).expression = expression(0);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(105);
					((Function_usageContext)_localctx).COMMA = match(COMMA);
					setState(106);
					((Function_usageContext)_localctx).expression = expression(0);
					_localctx.view += (((Function_usageContext)_localctx).COMMA!=null?((Function_usageContext)_localctx).COMMA.getText():null) + ((Function_usageContext)_localctx).expression.view;
					}
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				_localctx.view += ((Function_usageContext)_localctx).expression.view;
				}
			}

			setState(118);
			((Function_usageContext)_localctx).CLOSEBRACE = match(CLOSEBRACE);
			_localctx.view += (((Function_usageContext)_localctx).CLOSEBRACE!=null?((Function_usageContext)_localctx).CLOSEBRACE.getText():null);
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

	public static class Function_typeContext extends ParserRuleContext {
		public ArrayList<String> val;
		public Token BASE_TYPE;
		public Function_typeContext tp;
		public TerminalNode BASE_TYPE() { return getToken(HelloParser.BASE_TYPE, 0); }
		public TerminalNode ARROW() { return getToken(HelloParser.ARROW, 0); }
		public Function_typeContext function_type() {
			return getRuleContext(Function_typeContext.class,0);
		}
		public Function_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFunction_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFunction_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFunction_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_typeContext function_type() throws RecognitionException {
		Function_typeContext _localctx = new Function_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_function_type);
		try {
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				((Function_typeContext)_localctx).BASE_TYPE = match(BASE_TYPE);
				((Function_typeContext)_localctx).val =  new ArrayList<String>(); _localctx.val.add((((Function_typeContext)_localctx).BASE_TYPE!=null?((Function_typeContext)_localctx).BASE_TYPE.getText():null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				((Function_typeContext)_localctx).BASE_TYPE = match(BASE_TYPE);
				setState(124);
				match(ARROW);
				setState(125);
				((Function_typeContext)_localctx).tp = function_type();
				((Function_typeContext)_localctx).val =  ((Function_typeContext)_localctx).tp.val; _localctx.val.add((((Function_typeContext)_localctx).BASE_TYPE!=null?((Function_typeContext)_localctx).BASE_TYPE.getText():null));
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

	public static class ElementContext extends ParserRuleContext {
		public String view;
		public Token INTEGER;
		public Token DOUBLE;
		public Token BOOL;
		public Token WORD;
		public Function_usageContext function_usage;
		public TerminalNode INTEGER() { return getToken(HelloParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(HelloParser.DOUBLE, 0); }
		public TerminalNode BOOL() { return getToken(HelloParser.BOOL, 0); }
		public TerminalNode WORD() { return getToken(HelloParser.WORD, 0); }
		public Function_usageContext function_usage() {
			return getRuleContext(Function_usageContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_element);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				((ElementContext)_localctx).INTEGER = match(INTEGER);
				((ElementContext)_localctx).view =  (((ElementContext)_localctx).INTEGER!=null?((ElementContext)_localctx).INTEGER.getText():null);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				((ElementContext)_localctx).DOUBLE = match(DOUBLE);
				((ElementContext)_localctx).view =  (((ElementContext)_localctx).DOUBLE!=null?((ElementContext)_localctx).DOUBLE.getText():null);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				((ElementContext)_localctx).BOOL = match(BOOL);
				((ElementContext)_localctx).view =  (((ElementContext)_localctx).BOOL!=null?((ElementContext)_localctx).BOOL.getText():null);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				((ElementContext)_localctx).WORD = match(WORD);
				((ElementContext)_localctx).view =  (((ElementContext)_localctx).WORD!=null?((ElementContext)_localctx).WORD.getText():null);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				((ElementContext)_localctx).function_usage = function_usage();
				((ElementContext)_localctx).view =  ((ElementContext)_localctx).function_usage.view;
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

	public static class AssignationContext extends ParserRuleContext {
		public String view;
		public ElementContext element;
		public Token ASSGM;
		public ExpressionContext expression;
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public TerminalNode ASSGM() { return getToken(HelloParser.ASSGM, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAssignation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAssignation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignation);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				((AssignationContext)_localctx).element = element();
				setState(144);
				((AssignationContext)_localctx).ASSGM = match(ASSGM);
				setState(145);
				((AssignationContext)_localctx).expression = expression(0);
				((AssignationContext)_localctx).view =  ((AssignationContext)_localctx).element.view + ((AssignationContext)_localctx).ASSGM + ((AssignationContext)_localctx).expression.view;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				((AssignationContext)_localctx).expression = expression(0);
				((AssignationContext)_localctx).view =  ((AssignationContext)_localctx).expression.view;
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

	public static class ExpressionContext extends ParserRuleContext {
		public String view;
		public ExpressionContext e1;
		public ElementContext element;
		public Token op;
		public ExpressionContext e2;
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(HelloParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(HelloParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(HelloParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(HelloParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(HelloParser.SUB, 0); }
		public TerminalNode FAC() { return getToken(HelloParser.FAC, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(154);
			((ExpressionContext)_localctx).element = element();
			((ExpressionContext)_localctx).view =  ((ExpressionContext)_localctx).element.view;
			}
			_ctx.stop = _input.LT(-1);
			setState(172);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(170);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(157);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(158);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(159);
						((ExpressionContext)_localctx).e2 = expression(3);
						((ExpressionContext)_localctx).view =  ((ExpressionContext)_localctx).e1.view + " " + (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null) + " " + ((ExpressionContext)_localctx).e2.view;
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(162);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(163);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(164);
						((ExpressionContext)_localctx).e2 = expression(2);
						((ExpressionContext)_localctx).view =  ((ExpressionContext)_localctx).e1.view + " " + (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null) + " " + ((ExpressionContext)_localctx).e2.view;
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(167);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(168);
						match(FAC);
						((ExpressionContext)_localctx).view =  "fact(" + ((ExpressionContext)_localctx).e1.view + ") ";
						}
						break;
					}
					} 
				}
				setState(174);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Condition_operationContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HelloParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(HelloParser.NEQ, 0); }
		public TerminalNode LES() { return getToken(HelloParser.LES, 0); }
		public TerminalNode GRE() { return getToken(HelloParser.GRE, 0); }
		public TerminalNode LE() { return getToken(HelloParser.LE, 0); }
		public TerminalNode GE() { return getToken(HelloParser.GE, 0); }
		public TerminalNode AND() { return getToken(HelloParser.AND, 0); }
		public TerminalNode OR() { return getToken(HelloParser.OR, 0); }
		public Condition_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterCondition_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitCondition_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitCondition_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Condition_operationContext condition_operation() throws RecognitionException {
		Condition_operationContext _localctx = new Condition_operationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_condition_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LES) | (1L << GRE) | (1L << LE) | (1L << GE) | (1L << AND) | (1L << OR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ConditionContext extends ParserRuleContext {
		public String view;
		public ExpressionContext expression;
		public Condition_operationContext condition_operation;
		public ConditionContext condition;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Condition_operationContext condition_operation() {
			return getRuleContext(Condition_operationContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
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
		enterRule(_localctx, 26, RULE_condition);
		try {
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				((ConditionContext)_localctx).expression = expression(0);
				setState(178);
				((ConditionContext)_localctx).condition_operation = condition_operation();
				setState(179);
				((ConditionContext)_localctx).condition = condition();
				((ConditionContext)_localctx).view =  ((ConditionContext)_localctx).expression.view + (((ConditionContext)_localctx).condition_operation!=null?_input.getText(((ConditionContext)_localctx).condition_operation.start,((ConditionContext)_localctx).condition_operation.stop):null) + ((ConditionContext)_localctx).condition.view;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				((ConditionContext)_localctx).expression = expression(0);
				((ConditionContext)_localctx).view =  ((ConditionContext)_localctx).expression.view;
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\6\2#\n\2\r\2\16"+
		"\2$\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\48\n\4\3\4\3\4\3\4\6\4=\n\4\r\4\16\4>\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7W\n\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\be\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\7\tp\n\t\f\t\16\ts\13\t\3\t\3\t\5\tw\n\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0083\n\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0090\n\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u009a\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00ad\n\r\f\r\16\r\u00b0\13\r\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00bc\n\17\3\17\2\3\30\20"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\5\4\2\25\25\27\30\3\2\23\24\3\2"+
		"\31 \2\u00c3\2\36\3\2\2\2\4.\3\2\2\2\6\60\3\2\2\2\bB\3\2\2\2\nG\3\2\2"+
		"\2\fV\3\2\2\2\16d\3\2\2\2\20f\3\2\2\2\22\u0082\3\2\2\2\24\u008f\3\2\2"+
		"\2\26\u0099\3\2\2\2\30\u009b\3\2\2\2\32\u00b1\3\2\2\2\34\u00bb\3\2\2\2"+
		"\36\"\b\2\1\2\37 \5\4\3\2 !\b\2\1\2!#\3\2\2\2\"\37\3\2\2\2#$\3\2\2\2$"+
		"\"\3\2\2\2$%\3\2\2\2%&\3\2\2\2&\'\7\2\2\3\'\3\3\2\2\2()\5\6\4\2)*\b\3"+
		"\1\2*/\3\2\2\2+,\5\26\f\2,-\b\3\1\2-/\3\2\2\2.(\3\2\2\2.+\3\2\2\2/\5\3"+
		"\2\2\2\60\61\b\4\1\2\61\62\5\b\5\2\62\63\b\4\1\2\63\67\3\2\2\2\64\65\5"+
		"\n\6\2\65\66\b\4\1\2\668\3\2\2\2\67\64\3\2\2\2\678\3\2\2\28<\3\2\2\29"+
		":\5\n\6\2:;\b\4\1\2;=\3\2\2\2<9\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2"+
		"?@\3\2\2\2@A\b\4\1\2A\7\3\2\2\2BC\7\7\2\2CD\7\16\2\2DE\5\22\n\2EF\b\5"+
		"\1\2F\t\3\2\2\2GH\7\7\2\2HI\5\f\7\2IJ\7\22\2\2JK\5\26\f\2KL\b\6\1\2L\13"+
		"\3\2\2\2MN\5\16\b\2NO\7\n\2\2OP\5\f\7\2PQ\b\7\1\2QW\3\2\2\2RS\5\16\b\2"+
		"ST\b\7\1\2TW\3\2\2\2UW\b\7\1\2VM\3\2\2\2VR\3\2\2\2VU\3\2\2\2W\r\3\2\2"+
		"\2XY\7\13\2\2YZ\5\16\b\2Z[\7\f\2\2[\\\b\b\1\2\\e\3\2\2\2]^\7\7\2\2^_\7"+
		"\20\2\2_`\5\34\17\2`a\b\b\1\2ae\3\2\2\2bc\7\7\2\2ce\b\b\1\2dX\3\2\2\2"+
		"d]\3\2\2\2db\3\2\2\2e\17\3\2\2\2fg\b\t\1\2gh\7\7\2\2hi\7\13\2\2iv\b\t"+
		"\1\2jq\5\30\r\2kl\7\n\2\2lm\5\30\r\2mn\b\t\1\2np\3\2\2\2ok\3\2\2\2ps\3"+
		"\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\b\t\1\2uw\3\2\2\2vj\3"+
		"\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\f\2\2yz\b\t\1\2z\21\3\2\2\2{|\7\3\2\2|"+
		"\u0083\b\n\1\2}~\7\3\2\2~\177\7\21\2\2\177\u0080\5\22\n\2\u0080\u0081"+
		"\b\n\1\2\u0081\u0083\3\2\2\2\u0082{\3\2\2\2\u0082}\3\2\2\2\u0083\23\3"+
		"\2\2\2\u0084\u0085\7\4\2\2\u0085\u0090\b\13\1\2\u0086\u0087\7\5\2\2\u0087"+
		"\u0090\b\13\1\2\u0088\u0089\7\6\2\2\u0089\u0090\b\13\1\2\u008a\u008b\7"+
		"\7\2\2\u008b\u0090\b\13\1\2\u008c\u008d\5\20\t\2\u008d\u008e\b\13\1\2"+
		"\u008e\u0090\3\2\2\2\u008f\u0084\3\2\2\2\u008f\u0086\3\2\2\2\u008f\u0088"+
		"\3\2\2\2\u008f\u008a\3\2\2\2\u008f\u008c\3\2\2\2\u0090\25\3\2\2\2\u0091"+
		"\u0092\5\24\13\2\u0092\u0093\7\22\2\2\u0093\u0094\5\30\r\2\u0094\u0095"+
		"\b\f\1\2\u0095\u009a\3\2\2\2\u0096\u0097\5\30\r\2\u0097\u0098\b\f\1\2"+
		"\u0098\u009a\3\2\2\2\u0099\u0091\3\2\2\2\u0099\u0096\3\2\2\2\u009a\27"+
		"\3\2\2\2\u009b\u009c\b\r\1\2\u009c\u009d\5\24\13\2\u009d\u009e\b\r\1\2"+
		"\u009e\u00ae\3\2\2\2\u009f\u00a0\f\4\2\2\u00a0\u00a1\t\2\2\2\u00a1\u00a2"+
		"\5\30\r\5\u00a2\u00a3\b\r\1\2\u00a3\u00ad\3\2\2\2\u00a4\u00a5\f\3\2\2"+
		"\u00a5\u00a6\t\3\2\2\u00a6\u00a7\5\30\r\4\u00a7\u00a8\b\r\1\2\u00a8\u00ad"+
		"\3\2\2\2\u00a9\u00aa\f\5\2\2\u00aa\u00ab\7\26\2\2\u00ab\u00ad\b\r\1\2"+
		"\u00ac\u009f\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ad\u00b0"+
		"\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\31\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b1\u00b2\t\4\2\2\u00b2\33\3\2\2\2\u00b3\u00b4\5\30\r"+
		"\2\u00b4\u00b5\5\32\16\2\u00b5\u00b6\5\34\17\2\u00b6\u00b7\b\17\1\2\u00b7"+
		"\u00bc\3\2\2\2\u00b8\u00b9\5\30\r\2\u00b9\u00ba\b\17\1\2\u00ba\u00bc\3"+
		"\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bc\35\3\2\2\2\20$.\67"+
		">Vdqv\u0082\u008f\u0099\u00ac\u00ae\u00bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}