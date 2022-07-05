// Generated from /Users/admin/Documents/University/#GitHub/mt/lab03/src/main/java/Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BASE_TYPE=1, INTEGER=2, DOUBLE=3, BOOL=4, WORD=5, ANY_WORD=6, WHITESPACE=7, 
		COMMA=8, OPENBRACE=9, CLOSEBRACE=10, COLON=11, DCOLON=12, SCOLON=13, PIPE=14, 
		ARROW=15, ASSGM=16, ADD=17, SUB=18, MUL=19, FAC=20, DIV=21, MOD=22, EQ=23, 
		NEQ=24, LES=25, GRE=26, LE=27, GE=28, AND=29, OR=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BASE_TYPE", "INTEGER", "DOUBLE", "BOOL", "WORD", "ANY_WORD", "WHITESPACE", 
			"COMMA", "OPENBRACE", "CLOSEBRACE", "COLON", "DCOLON", "SCOLON", "PIPE", 
			"ARROW", "ASSGM", "ADD", "SUB", "MUL", "FAC", "DIV", "MOD", "EQ", "NEQ", 
			"LES", "GRE", "LE", "GE", "AND", "OR"
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


	public HelloLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hello.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00b8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2Q\n\2\3"+
		"\3\5\3T\n\3\3\3\6\3W\n\3\r\3\16\3X\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13\4"+
		"\5\4c\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5n\n\5\3\6\3\6\7\6r\n"+
		"\6\f\6\16\6u\13\6\3\7\6\7x\n\7\r\7\16\7y\3\b\6\b}\n\b\r\b\16\b~\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3"+
		"\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\2\2 \3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3\2\6\3\2"+
		"\62;\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00c1\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\3P\3\2\2\2\5S\3\2\2\2\7Z\3\2\2\2\tm\3\2\2\2\13o\3\2\2\2\rw\3\2\2\2\17"+
		"|\3\2\2\2\21\u0082\3\2\2\2\23\u0084\3\2\2\2\25\u0086\3\2\2\2\27\u0088"+
		"\3\2\2\2\31\u008a\3\2\2\2\33\u008d\3\2\2\2\35\u008f\3\2\2\2\37\u0091\3"+
		"\2\2\2!\u0094\3\2\2\2#\u0096\3\2\2\2%\u0098\3\2\2\2\'\u009a\3\2\2\2)\u009c"+
		"\3\2\2\2+\u009e\3\2\2\2-\u00a0\3\2\2\2/\u00a2\3\2\2\2\61\u00a5\3\2\2\2"+
		"\63\u00a8\3\2\2\2\65\u00aa\3\2\2\2\67\u00ac\3\2\2\29\u00af\3\2\2\2;\u00b2"+
		"\3\2\2\2=\u00b5\3\2\2\2?@\7K\2\2@A\7p\2\2AB\7v\2\2BC\7g\2\2CD\7i\2\2D"+
		"E\7g\2\2EQ\7t\2\2FG\7F\2\2GH\7q\2\2HI\7w\2\2IJ\7d\2\2JK\7n\2\2KQ\7g\2"+
		"\2LM\7X\2\2MN\7q\2\2NO\7k\2\2OQ\7f\2\2P?\3\2\2\2PF\3\2\2\2PL\3\2\2\2Q"+
		"\4\3\2\2\2RT\7/\2\2SR\3\2\2\2ST\3\2\2\2TV\3\2\2\2UW\t\2\2\2VU\3\2\2\2"+
		"WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\6\3\2\2\2Zb\5\5\3\2[_\7\60\2\2\\^\t\2"+
		"\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3\2\2\2a_\3\2\2\2b[\3"+
		"\2\2\2bc\3\2\2\2c\b\3\2\2\2de\7V\2\2ef\7t\2\2fg\7w\2\2gn\7g\2\2hi\7H\2"+
		"\2ij\7c\2\2jk\7n\2\2kl\7u\2\2ln\7g\2\2md\3\2\2\2mh\3\2\2\2n\n\3\2\2\2"+
		"os\t\3\2\2pr\t\4\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\f\3\2\2"+
		"\2us\3\2\2\2vx\t\4\2\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\16\3\2"+
		"\2\2{}\t\5\2\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3"+
		"\2\2\2\u0080\u0081\b\b\2\2\u0081\20\3\2\2\2\u0082\u0083\7.\2\2\u0083\22"+
		"\3\2\2\2\u0084\u0085\7*\2\2\u0085\24\3\2\2\2\u0086\u0087\7+\2\2\u0087"+
		"\26\3\2\2\2\u0088\u0089\7<\2\2\u0089\30\3\2\2\2\u008a\u008b\7<\2\2\u008b"+
		"\u008c\7<\2\2\u008c\32\3\2\2\2\u008d\u008e\7=\2\2\u008e\34\3\2\2\2\u008f"+
		"\u0090\7~\2\2\u0090\36\3\2\2\2\u0091\u0092\7/\2\2\u0092\u0093\7@\2\2\u0093"+
		" \3\2\2\2\u0094\u0095\7?\2\2\u0095\"\3\2\2\2\u0096\u0097\7-\2\2\u0097"+
		"$\3\2\2\2\u0098\u0099\7/\2\2\u0099&\3\2\2\2\u009a\u009b\7,\2\2\u009b("+
		"\3\2\2\2\u009c\u009d\7#\2\2\u009d*\3\2\2\2\u009e\u009f\7\61\2\2\u009f"+
		",\3\2\2\2\u00a0\u00a1\7\'\2\2\u00a1.\3\2\2\2\u00a2\u00a3\7?\2\2\u00a3"+
		"\u00a4\7?\2\2\u00a4\60\3\2\2\2\u00a5\u00a6\7#\2\2\u00a6\u00a7\7?\2\2\u00a7"+
		"\62\3\2\2\2\u00a8\u00a9\7>\2\2\u00a9\64\3\2\2\2\u00aa\u00ab\7@\2\2\u00ab"+
		"\66\3\2\2\2\u00ac\u00ad\7>\2\2\u00ad\u00ae\7?\2\2\u00ae8\3\2\2\2\u00af"+
		"\u00b0\7@\2\2\u00b0\u00b1\7?\2\2\u00b1:\3\2\2\2\u00b2\u00b3\7(\2\2\u00b3"+
		"\u00b4\7(\2\2\u00b4<\3\2\2\2\u00b5\u00b6\7~\2\2\u00b6\u00b7\7~\2\2\u00b7"+
		">\3\2\2\2\f\2PSX_bmsy~\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}