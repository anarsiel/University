// Generated from D:/tmp/hw4/src/com/company\Grammar.g4 by ANTLR 4.9
package com.company;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PACKAGE=9, 
		PIPE=10, SEMICOLON=11, ARROW=12, START=13, NON_TERM=14, TERM=15, REGEX=16, 
		STRING=17, CODE=18, PCKG_NAME=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "PACKAGE", 
			"PIPE", "SEMICOLON", "ARROW", "START", "NON_TERM", "TERM", "REGEX", "STRING", 
			"CODE", "PCKG_NAME", "WS"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0091\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\7\17"+
		"T\n\17\f\17\16\17W\13\17\3\20\3\20\7\20[\n\20\f\20\16\20^\13\20\3\21\3"+
		"\21\3\21\3\21\7\21d\n\21\f\21\16\21g\13\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\7\22o\n\22\f\22\16\22r\13\22\3\22\3\22\3\23\3\23\6\23x\n\23\r\23\16"+
		"\23y\3\23\5\23}\n\23\7\23\177\n\23\f\23\16\23\u0082\13\23\3\23\3\23\3"+
		"\24\6\24\u0087\n\24\r\24\16\24\u0088\3\25\6\25\u008c\n\25\r\25\16\25\u008d"+
		"\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\n\3\2c|\5\2\62;C\\c|\3"+
		"\2C\\\5\2\f\f\17\17))\3\2$$\4\2}}\177\177\5\2\60\60\62;c|\5\2\13\f\17"+
		"\17\"\"\2\u009b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2"+
		"\2\2\7\60\3\2\2\2\t\62\3\2\2\2\13\64\3\2\2\2\r\66\3\2\2\2\178\3\2\2\2"+
		"\21:\3\2\2\2\23<\3\2\2\2\25D\3\2\2\2\27F\3\2\2\2\31H\3\2\2\2\33K\3\2\2"+
		"\2\35Q\3\2\2\2\37X\3\2\2\2!_\3\2\2\2#j\3\2\2\2%u\3\2\2\2\'\u0086\3\2\2"+
		"\2)\u008b\3\2\2\2+,\7<\2\2,\4\3\2\2\2-.\7<\2\2./\7?\2\2/\6\3\2\2\2\60"+
		"\61\7>\2\2\61\b\3\2\2\2\62\63\7.\2\2\63\n\3\2\2\2\64\65\7@\2\2\65\f\3"+
		"\2\2\2\66\67\7*\2\2\67\16\3\2\2\289\7+\2\29\20\3\2\2\2:;\7?\2\2;\22\3"+
		"\2\2\2<=\7r\2\2=>\7c\2\2>?\7e\2\2?@\7m\2\2@A\7c\2\2AB\7i\2\2BC\7g\2\2"+
		"C\24\3\2\2\2DE\7~\2\2E\26\3\2\2\2FG\7=\2\2G\30\3\2\2\2HI\7/\2\2IJ\7@\2"+
		"\2J\32\3\2\2\2KL\7u\2\2LM\7v\2\2MN\7c\2\2NO\7t\2\2OP\7v\2\2P\34\3\2\2"+
		"\2QU\t\2\2\2RT\t\3\2\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\36\3\2"+
		"\2\2WU\3\2\2\2X\\\t\4\2\2Y[\t\3\2\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]"+
		"\3\2\2\2] \3\2\2\2^\\\3\2\2\2_e\7)\2\2`d\n\5\2\2ab\7^\2\2bd\7)\2\2c`\3"+
		"\2\2\2ca\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7"+
		")\2\2i\"\3\2\2\2jp\7$\2\2ko\n\6\2\2lm\7^\2\2mo\7$\2\2nk\3\2\2\2nl\3\2"+
		"\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\7$\2\2t$\3\2"+
		"\2\2u\u0080\7}\2\2vx\n\7\2\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z"+
		"|\3\2\2\2{}\5%\23\2|{\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~w\3\2\2\2\177\u0082"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0083\u0084\7\177\2\2\u0084&\3\2\2\2\u0085\u0087\t\b\2"+
		"\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089(\3\2\2\2\u008a\u008c\t\t\2\2\u008b\u008a\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\b\25\2\2\u0090*\3\2\2\2\17\2U\\cenpy|\u0080\u0086\u0088"+
		"\u008d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}