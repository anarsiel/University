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
		BASE_TYPE=1, INTEGER=2, DOUBLE=3, BOOL=4, WORD=5, WHITESPACE=6, COMMA=7, 
		LP=8, RP=9, COLON=10, DCOLON=11, SCOLON=12, EQ=13, STICK=14, ARROW=15, 
		PLUS=16, MINUS=17, DIV=18, MUL=19, OST=20, OR=21, AND=22, LESS=23, GREATER=24, 
		DEQ=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BASE_TYPE", "INTEGER", "DOUBLE", "BOOL", "WORD", "WHITESPACE", "COMMA", 
			"LP", "RP", "COLON", "DCOLON", "SCOLON", "EQ", "STICK", "ARROW", "PLUS", 
			"MINUS", "DIV", "MUL", "OST", "OR", "AND", "LESS", "GREATER", "DEQ"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u0098\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2C\n\2\3\3\5\3F\n\3\3\3\6\3I\n\3\r\3\16\3J\3\4\3\4\3\4\7\4P\n\4\f\4\16"+
		"\4S\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5^\n\5\3\6\3\6\7\6b\n\6"+
		"\f\6\16\6e\13\6\3\7\6\7h\n\7\r\7\16\7i\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\3\2\6\3\2\62;\4\2C\\c|\5\2\62;C\\c|\5\2\13"+
		"\f\17\17\"\"\2\u009e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3B\3\2\2\2\5E\3\2\2\2"+
		"\7L\3\2\2\2\t]\3\2\2\2\13_\3\2\2\2\rg\3\2\2\2\17m\3\2\2\2\21o\3\2\2\2"+
		"\23q\3\2\2\2\25s\3\2\2\2\27u\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2\35|\3\2\2"+
		"\2\37~\3\2\2\2!\u0081\3\2\2\2#\u0083\3\2\2\2%\u0085\3\2\2\2\'\u0087\3"+
		"\2\2\2)\u0089\3\2\2\2+\u008b\3\2\2\2-\u008e\3\2\2\2/\u0091\3\2\2\2\61"+
		"\u0093\3\2\2\2\63\u0095\3\2\2\2\65\66\7K\2\2\66\67\7p\2\2\678\7v\2\28"+
		"9\7g\2\29:\7i\2\2:;\7g\2\2;C\7t\2\2<=\7F\2\2=>\7q\2\2>?\7w\2\2?@\7d\2"+
		"\2@A\7n\2\2AC\7g\2\2B\65\3\2\2\2B<\3\2\2\2C\4\3\2\2\2DF\7/\2\2ED\3\2\2"+
		"\2EF\3\2\2\2FH\3\2\2\2GI\t\2\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2"+
		"\2K\6\3\2\2\2LM\5\5\3\2MQ\7\60\2\2NP\t\2\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2"+
		"\2\2QR\3\2\2\2R\b\3\2\2\2SQ\3\2\2\2TU\7V\2\2UV\7t\2\2VW\7w\2\2W^\7g\2"+
		"\2XY\7H\2\2YZ\7c\2\2Z[\7n\2\2[\\\7u\2\2\\^\7g\2\2]T\3\2\2\2]X\3\2\2\2"+
		"^\n\3\2\2\2_c\t\3\2\2`b\t\4\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2"+
		"\2d\f\3\2\2\2ec\3\2\2\2fh\t\5\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2"+
		"\2\2jk\3\2\2\2kl\b\7\2\2l\16\3\2\2\2mn\7.\2\2n\20\3\2\2\2op\7*\2\2p\22"+
		"\3\2\2\2qr\7+\2\2r\24\3\2\2\2st\7<\2\2t\26\3\2\2\2uv\7<\2\2vw\7<\2\2w"+
		"\30\3\2\2\2xy\7=\2\2y\32\3\2\2\2z{\7?\2\2{\34\3\2\2\2|}\7~\2\2}\36\3\2"+
		"\2\2~\177\7/\2\2\177\u0080\7@\2\2\u0080 \3\2\2\2\u0081\u0082\7-\2\2\u0082"+
		"\"\3\2\2\2\u0083\u0084\7/\2\2\u0084$\3\2\2\2\u0085\u0086\7\61\2\2\u0086"+
		"&\3\2\2\2\u0087\u0088\7,\2\2\u0088(\3\2\2\2\u0089\u008a\7\'\2\2\u008a"+
		"*\3\2\2\2\u008b\u008c\7~\2\2\u008c\u008d\7~\2\2\u008d,\3\2\2\2\u008e\u008f"+
		"\7(\2\2\u008f\u0090\7(\2\2\u0090.\3\2\2\2\u0091\u0092\7>\2\2\u0092\60"+
		"\3\2\2\2\u0093\u0094\7@\2\2\u0094\62\3\2\2\2\u0095\u0096\7?\2\2\u0096"+
		"\u0097\7?\2\2\u0097\64\3\2\2\2\n\2BEJQ]ci\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}