// Generated from D:/tmp/hw4/src/com/company\Grammar.g4 by ANTLR 4.9
package com.company;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PACKAGE=9, 
		PIPE=10, SEMICOLON=11, ARROW=12, START=13, NON_TERM=14, TERM=15, REGEX=16, 
		STRING=17, CODE=18, PCKG_NAME=19, WS=20;
	public static final int
		RULE_file = 0, RULE_pckg = 1, RULE_start = 2, RULE_rule1 = 3, RULE_nonTermRule = 4, 
		RULE_inAttrs = 5, RULE_param = 6, RULE_paramType = 7, RULE_paramName = 8, 
		RULE_returnAttr = 9, RULE_productions = 10, RULE_product = 11, RULE_args = 12, 
		RULE_arg = 13, RULE_termRule = 14, RULE_commonTermRule = 15, RULE_skipRule = 16, 
		RULE_term_value = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "pckg", "start", "rule1", "nonTermRule", "inAttrs", "param", 
			"paramType", "paramName", "returnAttr", "productions", "product", "args", 
			"arg", "termRule", "commonTermRule", "skipRule", "term_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "':='", "'<'", "','", "'>'", "'('", "')'", "'='", "'package'", 
			"'|'", "';'", "'->'", "'start'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "PACKAGE", "PIPE", 
			"SEMICOLON", "ARROW", "START", "NON_TERM", "TERM", "REGEX", "STRING", 
			"CODE", "PCKG_NAME", "WS"
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
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public PckgContext pckg() {
			return getRuleContext(PckgContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public StartContext start() {
			return getRuleContext(StartContext.class,0);
		}
		public List<Rule1Context> rule1() {
			return getRuleContexts(Rule1Context.class);
		}
		public Rule1Context rule1(int i) {
			return getRuleContext(Rule1Context.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			pckg();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==START) {
				{
				setState(37);
				start();
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(38);
					rule1();
					}
					}
					setState(41); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NON_TERM || _la==TERM );
				}
			}

			setState(45);
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

	public static class PckgContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(GrammarParser.PACKAGE, 0); }
		public TerminalNode PCKG_NAME() { return getToken(GrammarParser.PCKG_NAME, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public PckgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pckg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPckg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPckg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitPckg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PckgContext pckg() throws RecognitionException {
		PckgContext _localctx = new PckgContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pckg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(PACKAGE);
			setState(48);
			match(PCKG_NAME);
			setState(49);
			match(SEMICOLON);
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

	public static class StartContext extends ParserRuleContext {
		public TerminalNode START() { return getToken(GrammarParser.START, 0); }
		public TerminalNode NON_TERM() { return getToken(GrammarParser.NON_TERM, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(START);
			setState(52);
			match(NON_TERM);
			setState(53);
			match(SEMICOLON);
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

	public static class Rule1Context extends ParserRuleContext {
		public NonTermRuleContext nonTermRule() {
			return getRuleContext(NonTermRuleContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public TermRuleContext termRule() {
			return getRuleContext(TermRuleContext.class,0);
		}
		public Rule1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRule1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRule1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRule1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rule1Context rule1() throws RecognitionException {
		Rule1Context _localctx = new Rule1Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_rule1);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NON_TERM:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				nonTermRule();
				setState(56);
				match(SEMICOLON);
				}
				break;
			case TERM:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				termRule();
				setState(59);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NonTermRuleContext extends ParserRuleContext {
		public TerminalNode NON_TERM() { return getToken(GrammarParser.NON_TERM, 0); }
		public List<ProductionsContext> productions() {
			return getRuleContexts(ProductionsContext.class);
		}
		public ProductionsContext productions(int i) {
			return getRuleContext(ProductionsContext.class,i);
		}
		public InAttrsContext inAttrs() {
			return getRuleContext(InAttrsContext.class,0);
		}
		public ReturnAttrContext returnAttr() {
			return getRuleContext(ReturnAttrContext.class,0);
		}
		public List<TerminalNode> PIPE() { return getTokens(GrammarParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(GrammarParser.PIPE, i);
		}
		public NonTermRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonTermRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNonTermRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNonTermRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNonTermRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonTermRuleContext nonTermRule() throws RecognitionException {
		NonTermRuleContext _localctx = new NonTermRuleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nonTermRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(NON_TERM);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(64);
				inAttrs();
				}
			}

			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(67);
				match(T__0);
				setState(68);
				returnAttr();
				}
			}

			setState(71);
			match(T__1);
			setState(72);
			productions();
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PIPE) {
				{
				{
				setState(73);
				match(PIPE);
				setState(74);
				productions();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class InAttrsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public InAttrsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inAttrs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterInAttrs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitInAttrs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitInAttrs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InAttrsContext inAttrs() throws RecognitionException {
		InAttrsContext _localctx = new InAttrsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inAttrs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__2);
			setState(81);
			param();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(82);
				match(T__3);
				setState(83);
				param();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(T__4);
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

	public static class ParamContext extends ParserRuleContext {
		public ParamNameContext paramName() {
			return getRuleContext(ParamNameContext.class,0);
		}
		public ParamTypeContext paramType() {
			return getRuleContext(ParamTypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			paramName();
			setState(92);
			match(T__0);
			setState(93);
			paramType();
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

	public static class ParamTypeContext extends ParserRuleContext {
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public ParamTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParamType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParamType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParamType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamTypeContext paramType() throws RecognitionException {
		ParamTypeContext _localctx = new ParamTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_paramType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(TERM);
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

	public static class ParamNameContext extends ParserRuleContext {
		public TerminalNode NON_TERM() { return getToken(GrammarParser.NON_TERM, 0); }
		public ParamNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParamName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParamName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParamName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamNameContext paramName() throws RecognitionException {
		ParamNameContext _localctx = new ParamNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(NON_TERM);
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

	public static class ReturnAttrContext extends ParserRuleContext {
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public ReturnAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterReturnAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitReturnAttr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitReturnAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnAttrContext returnAttr() throws RecognitionException {
		ReturnAttrContext _localctx = new ReturnAttrContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_returnAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(TERM);
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

	public static class ProductionsContext extends ParserRuleContext {
		public List<ProductContext> product() {
			return getRuleContexts(ProductContext.class);
		}
		public ProductContext product(int i) {
			return getRuleContext(ProductContext.class,i);
		}
		public ProductionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_productions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProductions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProductions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProductions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductionsContext productions() throws RecognitionException {
		ProductionsContext _localctx = new ProductionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_productions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NON_TERM) | (1L << TERM) | (1L << CODE))) != 0)) {
				{
				{
				setState(101);
				product();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ProductContext extends ParserRuleContext {
		public TerminalNode NON_TERM() { return getToken(GrammarParser.NON_TERM, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public TerminalNode CODE() { return getToken(GrammarParser.CODE, 0); }
		public ProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_product; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProduct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProduct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductContext product() throws RecognitionException {
		ProductContext _localctx = new ProductContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_product);
		int _la;
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NON_TERM:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(NON_TERM);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(108);
					args();
					}
				}

				}
				break;
			case TERM:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(TERM);
				}
				break;
			case CODE:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				match(CODE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ArgsContext extends ParserRuleContext {
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__5);
			setState(116);
			arg();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(117);
				match(T__3);
				setState(118);
				arg();
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
			match(T__6);
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

	public static class ArgContext extends ParserRuleContext {
		public TerminalNode NON_TERM() { return getToken(GrammarParser.NON_TERM, 0); }
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public TerminalNode CODE() { return getToken(GrammarParser.CODE, 0); }
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NON_TERM) | (1L << TERM) | (1L << CODE))) != 0)) ) {
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

	public static class TermRuleContext extends ParserRuleContext {
		public CommonTermRuleContext commonTermRule() {
			return getRuleContext(CommonTermRuleContext.class,0);
		}
		public SkipRuleContext skipRule() {
			return getRuleContext(SkipRuleContext.class,0);
		}
		public TermRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTermRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTermRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTermRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermRuleContext termRule() throws RecognitionException {
		TermRuleContext _localctx = new TermRuleContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termRule);
		try {
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				commonTermRule();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				skipRule();
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

	public static class CommonTermRuleContext extends ParserRuleContext {
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public Term_valueContext term_value() {
			return getRuleContext(Term_valueContext.class,0);
		}
		public CommonTermRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commonTermRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCommonTermRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCommonTermRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCommonTermRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommonTermRuleContext commonTermRule() throws RecognitionException {
		CommonTermRuleContext _localctx = new CommonTermRuleContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_commonTermRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(TERM);
			setState(133);
			match(T__7);
			setState(134);
			term_value();
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

	public static class SkipRuleContext extends ParserRuleContext {
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public TerminalNode ARROW() { return getToken(GrammarParser.ARROW, 0); }
		public Term_valueContext term_value() {
			return getRuleContext(Term_valueContext.class,0);
		}
		public SkipRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skipRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSkipRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSkipRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSkipRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkipRuleContext skipRule() throws RecognitionException {
		SkipRuleContext _localctx = new SkipRuleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_skipRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(TERM);
			setState(137);
			match(ARROW);
			setState(138);
			term_value();
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

	public static class Term_valueContext extends ParserRuleContext {
		public TerminalNode REGEX() { return getToken(GrammarParser.REGEX, 0); }
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public Term_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTerm_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTerm_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTerm_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term_valueContext term_value() throws RecognitionException {
		Term_valueContext _localctx = new Term_valueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_term_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_la = _input.LA(1);
			if ( !(_la==REGEX || _la==STRING) ) {
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u0091\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\6\2*\n\2\r\2\16\2+\5\2.\n\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\5\6D\n\6"+
		"\3\6\3\6\5\6H\n\6\3\6\3\6\3\6\3\6\7\6N\n\6\f\6\16\6Q\13\6\3\7\3\7\3\7"+
		"\3\7\7\7W\n\7\f\7\16\7Z\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\7\fi\n\f\f\f\16\fl\13\f\3\r\3\r\5\rp\n\r\3\r\3\r\5\rt\n\r"+
		"\3\16\3\16\3\16\3\16\7\16z\n\16\f\16\16\16}\13\16\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\5\20\u0085\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\4\4\2\20"+
		"\21\24\24\3\2\22\23\2\u008b\2&\3\2\2\2\4\61\3\2\2\2\6\65\3\2\2\2\b?\3"+
		"\2\2\2\nA\3\2\2\2\fR\3\2\2\2\16]\3\2\2\2\20a\3\2\2\2\22c\3\2\2\2\24e\3"+
		"\2\2\2\26j\3\2\2\2\30s\3\2\2\2\32u\3\2\2\2\34\u0080\3\2\2\2\36\u0084\3"+
		"\2\2\2 \u0086\3\2\2\2\"\u008a\3\2\2\2$\u008e\3\2\2\2&-\5\4\3\2\')\5\6"+
		"\4\2(*\5\b\5\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2-\'\3"+
		"\2\2\2-.\3\2\2\2./\3\2\2\2/\60\7\2\2\3\60\3\3\2\2\2\61\62\7\13\2\2\62"+
		"\63\7\25\2\2\63\64\7\r\2\2\64\5\3\2\2\2\65\66\7\17\2\2\66\67\7\20\2\2"+
		"\678\7\r\2\28\7\3\2\2\29:\5\n\6\2:;\7\r\2\2;@\3\2\2\2<=\5\36\20\2=>\7"+
		"\r\2\2>@\3\2\2\2?9\3\2\2\2?<\3\2\2\2@\t\3\2\2\2AC\7\20\2\2BD\5\f\7\2C"+
		"B\3\2\2\2CD\3\2\2\2DG\3\2\2\2EF\7\3\2\2FH\5\24\13\2GE\3\2\2\2GH\3\2\2"+
		"\2HI\3\2\2\2IJ\7\4\2\2JO\5\26\f\2KL\7\f\2\2LN\5\26\f\2MK\3\2\2\2NQ\3\2"+
		"\2\2OM\3\2\2\2OP\3\2\2\2P\13\3\2\2\2QO\3\2\2\2RS\7\5\2\2SX\5\16\b\2TU"+
		"\7\6\2\2UW\5\16\b\2VT\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2"+
		"ZX\3\2\2\2[\\\7\7\2\2\\\r\3\2\2\2]^\5\22\n\2^_\7\3\2\2_`\5\20\t\2`\17"+
		"\3\2\2\2ab\7\21\2\2b\21\3\2\2\2cd\7\20\2\2d\23\3\2\2\2ef\7\21\2\2f\25"+
		"\3\2\2\2gi\5\30\r\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\27\3\2\2"+
		"\2lj\3\2\2\2mo\7\20\2\2np\5\32\16\2on\3\2\2\2op\3\2\2\2pt\3\2\2\2qt\7"+
		"\21\2\2rt\7\24\2\2sm\3\2\2\2sq\3\2\2\2sr\3\2\2\2t\31\3\2\2\2uv\7\b\2\2"+
		"v{\5\34\17\2wx\7\6\2\2xz\5\34\17\2yw\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2"+
		"\2\2|~\3\2\2\2}{\3\2\2\2~\177\7\t\2\2\177\33\3\2\2\2\u0080\u0081\t\2\2"+
		"\2\u0081\35\3\2\2\2\u0082\u0085\5 \21\2\u0083\u0085\5\"\22\2\u0084\u0082"+
		"\3\2\2\2\u0084\u0083\3\2\2\2\u0085\37\3\2\2\2\u0086\u0087\7\21\2\2\u0087"+
		"\u0088\7\n\2\2\u0088\u0089\5$\23\2\u0089!\3\2\2\2\u008a\u008b\7\21\2\2"+
		"\u008b\u008c\7\16\2\2\u008c\u008d\5$\23\2\u008d#\3\2\2\2\u008e\u008f\t"+
		"\3\2\2\u008f%\3\2\2\2\16+-?CGOXjos{\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}