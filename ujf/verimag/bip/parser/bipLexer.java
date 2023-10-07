/*      */ package ujf.verimag.bip.parser;
/*      */ 
/*      */ import org.antlr.runtime.BaseRecognizer;
/*      */ import org.antlr.runtime.CharStream;
/*      */ import org.antlr.runtime.DFA;
/*      */ import org.antlr.runtime.EarlyExitException;
/*      */ import org.antlr.runtime.IntStream;
/*      */ import org.antlr.runtime.Lexer;
/*      */ import org.antlr.runtime.MismatchedSetException;
/*      */ import org.antlr.runtime.RecognitionException;
/*      */ import org.antlr.runtime.RecognizerSharedState;
/*      */ 
/*      */ public class bipLexer extends Lexer {
/*      */   public static final int PACKAGE = 23;
/*      */   public static final int LT = 56;
/*      */   public static final int MOD = 93;
/*      */   public static final int LETTER = 112;
/*      */   public static final int RESET = 73;
/*      */   public static final int CONNECTOR = 36;
/*      */   public static final int TRIGGER = 14;
/*      */   public static final int BITWISEXOR = 82;
/*      */   public static final int DO = 74;
/*      */   public static final int EXTERN = 76;
/*      */   public static final int EQUALS = 84;
/*      */   public static final int NOT = 94;
/*      */   public static final int EOF = -1;
/*      */   public static final int LOW_INTERACTION = 19;
/*      */   public static final int UNARY_PLUS = 4;
/*      */   public static final int TYPE = 31;
/*      */   public static final int CODE = 28;
/*      */   public static final int LBRACKET = 52;
/*      */   public static final int QUOTE = 79;
/*      */   public static final int LPAR = 32;
/*      */   public static final int UNARY_PREFIX_DECREMENT = 9;
/*      */   public static final int UNARY_REF = 7;
/*      */   public static final int EXPORT = 37;
/*      */   public static final int PLUSASSIGN = 106;
/*      */   public static final int COMMENT = 114;
/*      */   public static final int BIPUP = 41;
/*      */   public static final int SECOND = 70;
/*      */   public static final int LINE_COMMENT = 115;
/*      */   public static final int INITIAL = 60;
/*      */   public static final int ELSE = 103;
/*      */   public static final int ON = 39;
/*      */   public static final int WHITESPACE = 113;
/*      */   public static final int UNARY_MINUS = 5;
/*      */   public static final int LCURLY = 104;
/*      */   public static final int MULT = 57;
/*      */   public static final int RIGHTSHIFT = 90;
/*      */   public static final int SINGLESHOT = 45;
/*      */   public static final int DECREMENT = 97;
/*      */   public static final int PRIORITY = 54;
/*      */   public static final int DIFF = 85;
/*      */   public static final int OR = 80;
/*      */   public static final int PORT_PARAMETER = 15;
/*      */   public static final int GT = 88;
/*      */   public static final int FIELD = 98;
/*      */   public static final int MULTASSIGN = 108;
/*      */   public static final int USE = 29;
/*      */   public static final int END = 25;
/*      */   public static final int FROM = 61;
/*      */   public static final int QMARK = 50;
/*      */   public static final int ATOMIC = 43;
/*      */   public static final int DELAY = 55;
/*      */   public static final int DELAYABLE = 64;
/*      */   public static final int MILLISECOND = 71;
/*      */   public static final int T__116 = 116;
/*      */   public static final int GTE = 87;
/*      */   public static final int FLOAT = 99;
/*      */   public static final int ACTUAL_PORT_PARAMETER = 17;
/*      */   public static final int DATA_PARAMETER = 16;
/*      */   public static final int AND = 66;
/*      */   public static final int DEFINE = 38;
/*      */   public static final int LTE = 86;
/*      */   public static final int COMPOUND = 44;
/*      */   public static final int IF = 102;
/*      */   public static final int INDEX = 101;
/*      */   public static final int HIGH_INTERACTION = 20;
/*      */   public static final int EXPORT_DATA = 22;
/*      */   public static final int IN = 67;
/*      */   public static final int EAGER = 63;
/*      */   public static final int PROVIDED = 40;
/*      */   public static final int COMMA = 33;
/*      */   public static final int UNARY_POSTFIX_DECREMENT = 11;
/*      */   public static final int IS = 47;
/*      */   public static final int IDENTIFIER = 24;
/*      */   public static final int EXPORT_PORT = 21;
/*      */   public static final int UNARY_POSTFIX_INCREMENT = 10;
/*      */   public static final int MODEL = 26;
/*      */   public static final int PLUS = 78;
/*      */   public static final int LEFTSHIFT = 89;
/*      */   public static final int DIGIT = 111;
/*      */   public static final int HEADER = 27;
/*      */   public static final int RBRACKET = 53;
/*      */   public static final int DOT = 35;
/*      */   public static final int COMPONENT = 49;
/*      */   public static final int CLOCK = 75;
/*      */   public static final int INTEGER = 68;
/*      */   public static final int FUSION = 13;
/*      */   public static final int DIVASSIGN = 109;
/*      */   public static final int MODASSIGN = 110;
/*      */   public static final int DASH = 69;
/*      */   public static final int LAZY = 65;
/*      */   public static final int TO = 62;
/*      */   public static final int TIMED = 77;
/*      */   public static final int BIPDOWN = 42;
/*      */   public static final int MULTISHOT = 46;
/*      */   public static final int PLACE = 59;
/*      */   public static final int PORT = 30;
/*      */   public static final int MINUS = 91;
/*      */   public static final int MINUSASSIGN = 107;
/*      */   public static final int BITWISEOR = 81;
/*      */   public static final int UNION = 12;
/*      */   public static final int REF = 83;
/*      */   public static final int UNARY_PREFIX_INCREMENT = 8;
/*      */   public static final int COLON = 51;
/*      */   public static final int INCREMENT = 96;
/*      */   public static final int NANOSECOND = 72;
/*      */   public static final int UNARY_DEREFERENCE = 6;
/*      */   public static final int RCURLY = 105;
/*      */   public static final int ASSIGN = 58;
/*      */   public static final int ACTUAL_DATA_PARAMETER = 18;
/*      */   public static final int RPAR = 34;
/*      */   public static final int DIV = 92;
/*      */   public static final int DATA = 48;
/*      */   public static final int BITWISENOT = 95;
/*      */   public static final int STRING = 100;
/*      */   
/*      */   public bipLexer() {}
/*      */   
/*      */   public bipLexer(CharStream input) {
/*  132 */     this(input, new RecognizerSharedState());
/*      */   }
/*      */   public bipLexer(CharStream input, RecognizerSharedState state) {
/*  135 */     super(input, state);
/*      */   }
/*      */   public String getGrammarFileName() {
/*  138 */     return "/local/poulhies/scratch/bip2/Compiler/Frontend/ujf.verimag.bip.parser/grammar/bip.g";
/*      */   }
/*      */   
/*      */   public final void mT__116() throws RecognitionException {
/*      */     try {
/*  143 */       int _type = 116;
/*  144 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  148 */       match(59);
/*      */ 
/*      */ 
/*      */       
/*  152 */       this.state.type = _type;
/*  153 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mIS() throws RecognitionException {
/*      */     try {
/*  163 */       int _type = 47;
/*  164 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  168 */       match("is");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  173 */       this.state.type = _type;
/*  174 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mATOMIC() throws RecognitionException {
/*      */     try {
/*  184 */       int _type = 43;
/*  185 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  189 */       match("atomic");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  194 */       this.state.type = _type;
/*  195 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCOMPONENT() throws RecognitionException {
/*      */     try {
/*  205 */       int _type = 49;
/*  206 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  210 */       match("component");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  215 */       this.state.type = _type;
/*  216 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCOMPOUND() throws RecognitionException {
/*      */     try {
/*  226 */       int _type = 44;
/*  227 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  231 */       match("compound");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  236 */       this.state.type = _type;
/*  237 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCONNECTOR() throws RecognitionException {
/*      */     try {
/*  247 */       int _type = 36;
/*  248 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  252 */       match("connector");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  257 */       this.state.type = _type;
/*  258 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDATA() throws RecognitionException {
/*      */     try {
/*  268 */       int _type = 48;
/*  269 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  273 */       match("data");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  278 */       this.state.type = _type;
/*  279 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDEFINE() throws RecognitionException {
/*      */     try {
/*  289 */       int _type = 38;
/*  290 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  294 */       match("define");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  299 */       this.state.type = _type;
/*  300 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDELAY() throws RecognitionException {
/*      */     try {
/*  310 */       int _type = 55;
/*  311 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  315 */       match("delay");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  320 */       this.state.type = _type;
/*  321 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDELAYABLE() throws RecognitionException {
/*      */     try {
/*  331 */       int _type = 64;
/*  332 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  336 */       match("delayable");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  341 */       this.state.type = _type;
/*  342 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDO() throws RecognitionException {
/*      */     try {
/*  352 */       int _type = 74;
/*  353 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  357 */       match("do");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  362 */       this.state.type = _type;
/*  363 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mBIPDOWN() throws RecognitionException {
/*      */     try {
/*  373 */       int _type = 42;
/*  374 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  378 */       match("down");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  383 */       this.state.type = _type;
/*  384 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mEAGER() throws RecognitionException {
/*      */     try {
/*  394 */       int _type = 63;
/*  395 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  399 */       match("eager");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  404 */       this.state.type = _type;
/*  405 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mELSE() throws RecognitionException {
/*      */     try {
/*  415 */       int _type = 103;
/*  416 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  420 */       match("else");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  425 */       this.state.type = _type;
/*  426 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mEND() throws RecognitionException {
/*      */     try {
/*  436 */       int _type = 25;
/*  437 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  441 */       match("end");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  446 */       this.state.type = _type;
/*  447 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mEXPORT() throws RecognitionException {
/*      */     try {
/*  457 */       int _type = 37;
/*  458 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  462 */       match("export");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  467 */       this.state.type = _type;
/*  468 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mEXTERN() throws RecognitionException {
/*      */     try {
/*  478 */       int _type = 76;
/*  479 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  483 */       match("extern");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  488 */       this.state.type = _type;
/*  489 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mFROM() throws RecognitionException {
/*      */     try {
/*  499 */       int _type = 61;
/*  500 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  504 */       match("from");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  509 */       this.state.type = _type;
/*  510 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mHEADER() throws RecognitionException {
/*      */     try {
/*  520 */       int _type = 27;
/*  521 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  525 */       match("header");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  530 */       this.state.type = _type;
/*  531 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mIF() throws RecognitionException {
/*      */     try {
/*  541 */       int _type = 102;
/*  542 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  546 */       match("if");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  551 */       this.state.type = _type;
/*  552 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mIN() throws RecognitionException {
/*      */     try {
/*  562 */       int _type = 67;
/*  563 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  567 */       match("in");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  572 */       this.state.type = _type;
/*  573 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mINITIAL() throws RecognitionException {
/*      */     try {
/*  583 */       int _type = 60;
/*  584 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  588 */       match("initial");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  593 */       this.state.type = _type;
/*  594 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLAZY() throws RecognitionException {
/*      */     try {
/*  604 */       int _type = 65;
/*  605 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  609 */       match("lazy");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  614 */       this.state.type = _type;
/*  615 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMODEL() throws RecognitionException {
/*      */     try {
/*  625 */       int _type = 26;
/*  626 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  630 */       match("model");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  635 */       this.state.type = _type;
/*  636 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMILLISECOND() throws RecognitionException {
/*      */     try {
/*  646 */       int _type = 71;
/*  647 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  651 */       match("millisecond");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  656 */       this.state.type = _type;
/*  657 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMULTISHOT() throws RecognitionException {
/*      */     try {
/*  667 */       int _type = 46;
/*  668 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  672 */       match("multishot");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  677 */       this.state.type = _type;
/*  678 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mNANOSECOND() throws RecognitionException {
/*      */     try {
/*  688 */       int _type = 72;
/*  689 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  693 */       match("nanosecond");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  698 */       this.state.type = _type;
/*  699 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPACKAGE() throws RecognitionException {
/*      */     try {
/*  709 */       int _type = 23;
/*  710 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  714 */       match("package");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  719 */       this.state.type = _type;
/*  720 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPLACE() throws RecognitionException {
/*      */     try {
/*  730 */       int _type = 59;
/*  731 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  735 */       match("place");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  740 */       this.state.type = _type;
/*  741 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mON() throws RecognitionException {
/*      */     try {
/*  751 */       int _type = 39;
/*  752 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  756 */       match("on");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  761 */       this.state.type = _type;
/*  762 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPORT() throws RecognitionException {
/*      */     try {
/*  772 */       int _type = 30;
/*  773 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  777 */       match("port");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  782 */       this.state.type = _type;
/*  783 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPRIORITY() throws RecognitionException {
/*      */     try {
/*  793 */       int _type = 54;
/*  794 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  798 */       match("priority");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  803 */       this.state.type = _type;
/*  804 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPROVIDED() throws RecognitionException {
/*      */     try {
/*  814 */       int _type = 40;
/*  815 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  819 */       match("provided");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  824 */       this.state.type = _type;
/*  825 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mSECOND() throws RecognitionException {
/*      */     try {
/*  835 */       int _type = 70;
/*  836 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  840 */       match("second");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  845 */       this.state.type = _type;
/*  846 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mSINGLESHOT() throws RecognitionException {
/*      */     try {
/*  856 */       int _type = 45;
/*  857 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  861 */       match("singleshot");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  866 */       this.state.type = _type;
/*  867 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mTIMED() throws RecognitionException {
/*      */     try {
/*  877 */       int _type = 77;
/*  878 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  882 */       match("timed");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  887 */       this.state.type = _type;
/*  888 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mTO() throws RecognitionException {
/*      */     try {
/*  898 */       int _type = 62;
/*  899 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  903 */       match("to");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  908 */       this.state.type = _type;
/*  909 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mTYPE() throws RecognitionException {
/*      */     try {
/*  919 */       int _type = 31;
/*  920 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  924 */       match("type");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  929 */       this.state.type = _type;
/*  930 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mUSE() throws RecognitionException {
/*      */     try {
/*  940 */       int _type = 29;
/*  941 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  945 */       match("use");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  950 */       this.state.type = _type;
/*  951 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mBIPUP() throws RecognitionException {
/*      */     try {
/*  961 */       int _type = 41;
/*  962 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  966 */       match("up");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  971 */       this.state.type = _type;
/*  972 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mRESET() throws RecognitionException {
/*      */     try {
/*  982 */       int _type = 73;
/*  983 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/*  987 */       match("reset");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  992 */       this.state.type = _type;
/*  993 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCLOCK() throws RecognitionException {
/*      */     try {
/* 1003 */       int _type = 75;
/* 1004 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1008 */       match("clock");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1013 */       this.state.type = _type;
/* 1014 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCOMMA() throws RecognitionException {
/*      */     try {
/* 1024 */       int _type = 33;
/* 1025 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1029 */       match(44);
/*      */ 
/*      */ 
/*      */       
/* 1033 */       this.state.type = _type;
/* 1034 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLPAR() throws RecognitionException {
/*      */     try {
/* 1044 */       int _type = 32;
/* 1045 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1049 */       match(40);
/*      */ 
/*      */ 
/*      */       
/* 1053 */       this.state.type = _type;
/* 1054 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mRPAR() throws RecognitionException {
/*      */     try {
/* 1064 */       int _type = 34;
/* 1065 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1069 */       match(41);
/*      */ 
/*      */ 
/*      */       
/* 1073 */       this.state.type = _type;
/* 1074 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLBRACKET() throws RecognitionException {
/*      */     try {
/* 1084 */       int _type = 52;
/* 1085 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1089 */       match(91);
/*      */ 
/*      */ 
/*      */       
/* 1093 */       this.state.type = _type;
/* 1094 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mRBRACKET() throws RecognitionException {
/*      */     try {
/* 1104 */       int _type = 53;
/* 1105 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1109 */       match(93);
/*      */ 
/*      */ 
/*      */       
/* 1113 */       this.state.type = _type;
/* 1114 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLCURLY() throws RecognitionException {
/*      */     try {
/* 1124 */       int _type = 104;
/* 1125 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1129 */       match(123);
/*      */ 
/*      */ 
/*      */       
/* 1133 */       this.state.type = _type;
/* 1134 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mRCURLY() throws RecognitionException {
/*      */     try {
/* 1144 */       int _type = 105;
/* 1145 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1149 */       match(125);
/*      */ 
/*      */ 
/*      */       
/* 1153 */       this.state.type = _type;
/* 1154 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDOT() throws RecognitionException {
/*      */     try {
/* 1164 */       int _type = 35;
/* 1165 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1169 */       match(46);
/*      */ 
/*      */ 
/*      */       
/* 1173 */       this.state.type = _type;
/* 1174 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mFIELD() throws RecognitionException {
/*      */     try {
/* 1184 */       int _type = 98;
/* 1185 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1189 */       match("->");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1194 */       this.state.type = _type;
/* 1195 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mQUOTE() throws RecognitionException {
/*      */     try {
/* 1205 */       int _type = 79;
/* 1206 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1210 */       match(39);
/*      */ 
/*      */ 
/*      */       
/* 1214 */       this.state.type = _type;
/* 1215 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPLUS() throws RecognitionException {
/*      */     try {
/* 1225 */       int _type = 78;
/* 1226 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1230 */       match(43);
/*      */ 
/*      */ 
/*      */       
/* 1234 */       this.state.type = _type;
/* 1235 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMINUS() throws RecognitionException {
/*      */     try {
/* 1245 */       int _type = 91;
/* 1246 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1250 */       match(45);
/*      */ 
/*      */ 
/*      */       
/* 1254 */       this.state.type = _type;
/* 1255 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mREF() throws RecognitionException {
/*      */     try {
/* 1265 */       int _type = 83;
/* 1266 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1270 */       match(38);
/*      */ 
/*      */ 
/*      */       
/* 1274 */       this.state.type = _type;
/* 1275 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMULT() throws RecognitionException {
/*      */     try {
/* 1285 */       int _type = 57;
/* 1286 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1290 */       match(42);
/*      */ 
/*      */ 
/*      */       
/* 1294 */       this.state.type = _type;
/* 1295 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDIV() throws RecognitionException {
/*      */     try {
/* 1305 */       int _type = 92;
/* 1306 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1310 */       match(47);
/*      */ 
/*      */ 
/*      */       
/* 1314 */       this.state.type = _type;
/* 1315 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMOD() throws RecognitionException {
/*      */     try {
/* 1325 */       int _type = 93;
/* 1326 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1330 */       match(37);
/*      */ 
/*      */ 
/*      */       
/* 1334 */       this.state.type = _type;
/* 1335 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mASSIGN() throws RecognitionException {
/*      */     try {
/* 1345 */       int _type = 58;
/* 1346 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1350 */       match(61);
/*      */ 
/*      */ 
/*      */       
/* 1354 */       this.state.type = _type;
/* 1355 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mNOT() throws RecognitionException {
/*      */     try {
/* 1365 */       int _type = 94;
/* 1366 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1370 */       match(33);
/*      */ 
/*      */ 
/*      */       
/* 1374 */       this.state.type = _type;
/* 1375 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mOR() throws RecognitionException {
/*      */     try {
/* 1385 */       int _type = 80;
/* 1386 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1390 */       match("||");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1395 */       this.state.type = _type;
/* 1396 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mAND() throws RecognitionException {
/*      */     try {
/* 1406 */       int _type = 66;
/* 1407 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1411 */       match("&&");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1416 */       this.state.type = _type;
/* 1417 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mBITWISEOR() throws RecognitionException {
/*      */     try {
/* 1427 */       int _type = 81;
/* 1428 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1432 */       match(124);
/*      */ 
/*      */ 
/*      */       
/* 1436 */       this.state.type = _type;
/* 1437 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mBITWISEXOR() throws RecognitionException {
/*      */     try {
/* 1447 */       int _type = 82;
/* 1448 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1452 */       match(94);
/*      */ 
/*      */ 
/*      */       
/* 1456 */       this.state.type = _type;
/* 1457 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mBITWISENOT() throws RecognitionException {
/*      */     try {
/* 1467 */       int _type = 95;
/* 1468 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1472 */       match(126);
/*      */ 
/*      */ 
/*      */       
/* 1476 */       this.state.type = _type;
/* 1477 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLT() throws RecognitionException {
/*      */     try {
/* 1487 */       int _type = 56;
/* 1488 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1492 */       match(60);
/*      */ 
/*      */ 
/*      */       
/* 1496 */       this.state.type = _type;
/* 1497 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLTE() throws RecognitionException {
/*      */     try {
/* 1507 */       int _type = 86;
/* 1508 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1512 */       match("<=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1517 */       this.state.type = _type;
/* 1518 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mGT() throws RecognitionException {
/*      */     try {
/* 1528 */       int _type = 88;
/* 1529 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1533 */       match(62);
/*      */ 
/*      */ 
/*      */       
/* 1537 */       this.state.type = _type;
/* 1538 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mGTE() throws RecognitionException {
/*      */     try {
/* 1548 */       int _type = 87;
/* 1549 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1553 */       match(">=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1558 */       this.state.type = _type;
/* 1559 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mEQUALS() throws RecognitionException {
/*      */     try {
/* 1569 */       int _type = 84;
/* 1570 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1574 */       match("==");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1579 */       this.state.type = _type;
/* 1580 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDIFF() throws RecognitionException {
/*      */     try {
/* 1590 */       int _type = 85;
/* 1591 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1595 */       match("!=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1600 */       this.state.type = _type;
/* 1601 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLEFTSHIFT() throws RecognitionException {
/*      */     try {
/* 1611 */       int _type = 89;
/* 1612 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1616 */       match("<<");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1621 */       this.state.type = _type;
/* 1622 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mRIGHTSHIFT() throws RecognitionException {
/*      */     try {
/* 1632 */       int _type = 90;
/* 1633 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1637 */       match(">>");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1642 */       this.state.type = _type;
/* 1643 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mINCREMENT() throws RecognitionException {
/*      */     try {
/* 1653 */       int _type = 96;
/* 1654 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1658 */       match("++");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1663 */       this.state.type = _type;
/* 1664 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDECREMENT() throws RecognitionException {
/*      */     try {
/* 1674 */       int _type = 97;
/* 1675 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1679 */       match("--");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1684 */       this.state.type = _type;
/* 1685 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mPLUSASSIGN() throws RecognitionException {
/*      */     try {
/* 1695 */       int _type = 106;
/* 1696 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1700 */       match("+=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1705 */       this.state.type = _type;
/* 1706 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMINUSASSIGN() throws RecognitionException {
/*      */     try {
/* 1716 */       int _type = 107;
/* 1717 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1721 */       match("-=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1726 */       this.state.type = _type;
/* 1727 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMULTASSIGN() throws RecognitionException {
/*      */     try {
/* 1737 */       int _type = 108;
/* 1738 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1742 */       match("*=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1747 */       this.state.type = _type;
/* 1748 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDIVASSIGN() throws RecognitionException {
/*      */     try {
/* 1758 */       int _type = 109;
/* 1759 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1763 */       match("/=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1768 */       this.state.type = _type;
/* 1769 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mMODASSIGN() throws RecognitionException {
/*      */     try {
/* 1779 */       int _type = 110;
/* 1780 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1784 */       match("%=");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1789 */       this.state.type = _type;
/* 1790 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCOLON() throws RecognitionException {
/*      */     try {
/* 1800 */       int _type = 51;
/* 1801 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1805 */       match(58);
/*      */ 
/*      */ 
/*      */       
/* 1809 */       this.state.type = _type;
/* 1810 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDASH() throws RecognitionException {
/*      */     try {
/* 1820 */       int _type = 69;
/* 1821 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1825 */       match(95);
/*      */ 
/*      */ 
/*      */       
/* 1829 */       this.state.type = _type;
/* 1830 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mQMARK() throws RecognitionException {
/*      */     try {
/* 1840 */       int _type = 50;
/* 1841 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1845 */       match(63);
/*      */ 
/*      */ 
/*      */       
/* 1849 */       this.state.type = _type;
/* 1850 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mFLOAT() throws RecognitionException {
/*      */     try {
/* 1860 */       int _type = 99;
/* 1861 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1866 */       int cnt1 = 0;
/*      */       while (true) {
/*      */         EarlyExitException eee;
/* 1869 */         int alt1 = 2;
/* 1870 */         int LA1_0 = this.input.LA(1);
/*      */         
/* 1872 */         if (LA1_0 >= 48 && LA1_0 <= 57) {
/* 1873 */           alt1 = 1;
/*      */         }
/*      */ 
/*      */         
/* 1877 */         switch (alt1) {
/*      */ 
/*      */           
/*      */           case 1:
/* 1881 */             mDIGIT();
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1887 */             if (cnt1 >= 1)
/* 1888 */               break;  eee = new EarlyExitException(1, (IntStream)this.input);
/*      */             
/* 1890 */             throw eee;
/*      */         } 
/* 1892 */         cnt1++;
/*      */       } 
/*      */       
/* 1895 */       match(46);
/*      */       
/* 1897 */       int cnt2 = 0;
/*      */       while (true) {
/*      */         EarlyExitException eee;
/* 1900 */         int alt2 = 2;
/* 1901 */         int LA2_0 = this.input.LA(1);
/*      */         
/* 1903 */         if (LA2_0 >= 48 && LA2_0 <= 57) {
/* 1904 */           alt2 = 1;
/*      */         }
/*      */ 
/*      */         
/* 1908 */         switch (alt2) {
/*      */ 
/*      */           
/*      */           case 1:
/* 1912 */             mDIGIT();
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1918 */             if (cnt2 >= 1)
/* 1919 */               break;  eee = new EarlyExitException(2, (IntStream)this.input);
/*      */             
/* 1921 */             throw eee;
/*      */         } 
/* 1923 */         cnt2++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1929 */       this.state.type = _type;
/* 1930 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mINTEGER() throws RecognitionException {
/*      */     try {
/* 1940 */       int _type = 68;
/* 1941 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1946 */       int cnt3 = 0;
/*      */       while (true) {
/*      */         EarlyExitException eee;
/* 1949 */         int alt3 = 2;
/* 1950 */         int LA3_0 = this.input.LA(1);
/*      */         
/* 1952 */         if (LA3_0 >= 48 && LA3_0 <= 57) {
/* 1953 */           alt3 = 1;
/*      */         }
/*      */ 
/*      */         
/* 1957 */         switch (alt3) {
/*      */ 
/*      */           
/*      */           case 1:
/* 1961 */             mDIGIT();
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1967 */             if (cnt3 >= 1)
/* 1968 */               break;  eee = new EarlyExitException(3, (IntStream)this.input);
/*      */             
/* 1970 */             throw eee;
/*      */         } 
/* 1972 */         cnt3++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1978 */       this.state.type = _type;
/* 1979 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mSTRING() throws RecognitionException {
/*      */     try {
/* 1989 */       int _type = 100;
/* 1990 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 1994 */       match(34);
/*      */       
/*      */       while (true) {
/*      */         MismatchedSetException mse;
/* 1998 */         int alt4 = 2;
/* 1999 */         int LA4_0 = this.input.LA(1);
/*      */         
/* 2001 */         if ((LA4_0 >= 0 && LA4_0 <= 33) || (LA4_0 >= 35 && LA4_0 <= 65535)) {
/* 2002 */           alt4 = 1;
/*      */         }
/*      */ 
/*      */         
/* 2006 */         switch (alt4) {
/*      */ 
/*      */           
/*      */           case 1:
/* 2010 */             if ((this.input.LA(1) >= 0 && this.input.LA(1) <= 33) || (this.input.LA(1) >= 35 && this.input.LA(1) <= 65535)) {
/* 2011 */               this.input.consume();
/*      */               
/*      */               continue;
/*      */             } 
/* 2015 */             mse = new MismatchedSetException(null, (IntStream)this.input);
/* 2016 */             recover((RecognitionException)mse);
/* 2017 */             throw mse;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/* 2028 */       match(34);
/*      */ 
/*      */ 
/*      */       
/* 2032 */       this.state.type = _type;
/* 2033 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mIDENTIFIER() throws RecognitionException {
/*      */     try {
/* 2043 */       int _type = 24;
/* 2044 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 2048 */       if ((this.input.LA(1) >= 65 && this.input.LA(1) <= 90) || this.input.LA(1) == 95 || (this.input.LA(1) >= 97 && this.input.LA(1) <= 122)) {
/* 2049 */         this.input.consume();
/*      */       }
/*      */       else {
/*      */         
/* 2053 */         MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/* 2054 */         recover((RecognitionException)mse);
/* 2055 */         throw mse;
/*      */       } 
/*      */       
/*      */       while (true) {
/*      */         MismatchedSetException mse;
/* 2060 */         int alt5 = 2;
/* 2061 */         int LA5_0 = this.input.LA(1);
/*      */         
/* 2063 */         if ((LA5_0 >= 48 && LA5_0 <= 57) || (LA5_0 >= 65 && LA5_0 <= 90) || LA5_0 == 95 || (LA5_0 >= 97 && LA5_0 <= 122)) {
/* 2064 */           alt5 = 1;
/*      */         }
/*      */ 
/*      */         
/* 2068 */         switch (alt5) {
/*      */ 
/*      */           
/*      */           case 1:
/* 2072 */             if ((this.input.LA(1) >= 48 && this.input.LA(1) <= 57) || (this.input.LA(1) >= 65 && this.input.LA(1) <= 90) || this.input.LA(1) == 95 || (this.input.LA(1) >= 97 && this.input.LA(1) <= 122)) {
/* 2073 */               this.input.consume();
/*      */               
/*      */               continue;
/*      */             } 
/* 2077 */             mse = new MismatchedSetException(null, (IntStream)this.input);
/* 2078 */             recover((RecognitionException)mse);
/* 2079 */             throw mse;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/* 2093 */       this.state.type = _type;
/* 2094 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mINDEX() throws RecognitionException {
/*      */     try {
/* 2104 */       int _type = 101;
/* 2105 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 2109 */       match(36);
/*      */ 
/*      */ 
/*      */       
/* 2113 */       matchRange(49, 57);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2120 */       this.state.type = _type;
/* 2121 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCODE() throws RecognitionException {
/*      */     try {
/* 2131 */       int _type = 28;
/* 2132 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 2136 */       match("{#");
/*      */ 
/*      */ 
/*      */       
/*      */       while (true) {
/* 2141 */         int alt6 = 2;
/* 2142 */         int LA6_0 = this.input.LA(1);
/*      */         
/* 2144 */         if (LA6_0 == 35) {
/* 2145 */           int LA6_1 = this.input.LA(2);
/*      */           
/* 2147 */           if (LA6_1 == 125) {
/* 2148 */             alt6 = 2;
/*      */           }
/* 2150 */           else if ((LA6_1 >= 0 && LA6_1 <= 124) || (LA6_1 >= 126 && LA6_1 <= 65535)) {
/* 2151 */             alt6 = 1;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 2156 */         else if ((LA6_0 >= 0 && LA6_0 <= 34) || (LA6_0 >= 36 && LA6_0 <= 65535)) {
/* 2157 */           alt6 = 1;
/*      */         } 
/*      */ 
/*      */         
/* 2161 */         switch (alt6) {
/*      */ 
/*      */           
/*      */           case 1:
/* 2165 */             matchAny();
/*      */             continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/* 2175 */       match("#}");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2180 */       this.state.type = _type;
/* 2181 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mDIGIT() throws RecognitionException {
/*      */     try {
/* 2194 */       matchRange(48, 57);
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLETTER() throws RecognitionException {
/*      */     try {
/* 2210 */       if ((this.input.LA(1) >= 65 && this.input.LA(1) <= 90) || (this.input.LA(1) >= 97 && this.input.LA(1) <= 122)) {
/* 2211 */         this.input.consume();
/*      */       }
/*      */       else {
/*      */         
/* 2215 */         MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/* 2216 */         recover((RecognitionException)mse);
/* 2217 */         throw mse;
/*      */       } 
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mWHITESPACE() throws RecognitionException {
/*      */     try {
/* 2231 */       int _type = 113;
/* 2232 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 2236 */       if ((this.input.LA(1) >= 9 && this.input.LA(1) <= 10) || (this.input.LA(1) >= 12 && this.input.LA(1) <= 13) || this.input.LA(1) == 32) {
/* 2237 */         this.input.consume();
/*      */       }
/*      */       else {
/*      */         
/* 2241 */         MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/* 2242 */         recover((RecognitionException)mse);
/* 2243 */         throw mse;
/*      */       } 
/* 2245 */       _channel = 99;
/*      */ 
/*      */ 
/*      */       
/* 2249 */       this.state.type = _type;
/* 2250 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mCOMMENT() throws RecognitionException {
/*      */     try {
/* 2260 */       int _type = 114;
/* 2261 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 2265 */       match("/*");
/*      */ 
/*      */ 
/*      */       
/*      */       while (true) {
/* 2270 */         int alt7 = 2;
/* 2271 */         int LA7_0 = this.input.LA(1);
/*      */         
/* 2273 */         if (LA7_0 == 42) {
/* 2274 */           int LA7_1 = this.input.LA(2);
/*      */           
/* 2276 */           if (LA7_1 == 47) {
/* 2277 */             alt7 = 2;
/*      */           }
/* 2279 */           else if ((LA7_1 >= 0 && LA7_1 <= 46) || (LA7_1 >= 48 && LA7_1 <= 65535)) {
/* 2280 */             alt7 = 1;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 2285 */         else if ((LA7_0 >= 0 && LA7_0 <= 41) || (LA7_0 >= 43 && LA7_0 <= 65535)) {
/* 2286 */           alt7 = 1;
/*      */         } 
/*      */ 
/*      */         
/* 2290 */         switch (alt7) {
/*      */ 
/*      */           
/*      */           case 1:
/* 2294 */             matchAny();
/*      */             continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/* 2304 */       match("*/");
/*      */       
/* 2306 */       _channel = 99;
/*      */ 
/*      */ 
/*      */       
/* 2310 */       this.state.type = _type;
/* 2311 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void mLINE_COMMENT() throws RecognitionException {
/*      */     try {
/* 2321 */       int _type = 115;
/* 2322 */       int _channel = 0;
/*      */ 
/*      */ 
/*      */       
/* 2326 */       match("//");
/*      */       
/*      */       while (true) {
/*      */         MismatchedSetException mse;
/*      */         
/* 2331 */         int alt8 = 2;
/* 2332 */         int LA8_0 = this.input.LA(1);
/*      */         
/* 2334 */         if ((LA8_0 >= 0 && LA8_0 <= 9) || (LA8_0 >= 11 && LA8_0 <= 12) || (LA8_0 >= 14 && LA8_0 <= 65535)) {
/* 2335 */           alt8 = 1;
/*      */         }
/*      */ 
/*      */         
/* 2339 */         switch (alt8) {
/*      */ 
/*      */           
/*      */           case 1:
/* 2343 */             if ((this.input.LA(1) >= 0 && this.input.LA(1) <= 9) || (this.input.LA(1) >= 11 && this.input.LA(1) <= 12) || (this.input.LA(1) >= 14 && this.input.LA(1) <= 65535)) {
/* 2344 */               this.input.consume();
/*      */               
/*      */               continue;
/*      */             } 
/* 2348 */             mse = new MismatchedSetException(null, (IntStream)this.input);
/* 2349 */             recover((RecognitionException)mse);
/* 2350 */             throw mse;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/* 2362 */       int alt9 = 2;
/* 2363 */       int LA9_0 = this.input.LA(1);
/*      */       
/* 2365 */       if (LA9_0 == 13) {
/* 2366 */         alt9 = 1;
/*      */       }
/* 2368 */       switch (alt9) {
/*      */ 
/*      */         
/*      */         case 1:
/* 2372 */           match(13);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2379 */       match(10);
/* 2380 */       _channel = 99;
/*      */ 
/*      */ 
/*      */       
/* 2384 */       this.state.type = _type;
/* 2385 */       this.state.channel = _channel;
/*      */     } finally {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mTokens() throws RecognitionException {
/* 2394 */     int alt10 = 92;
/* 2395 */     alt10 = this.dfa10.predict((IntStream)this.input);
/* 2396 */     switch (alt10) {
/*      */ 
/*      */       
/*      */       case 1:
/* 2400 */         mT__116();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/* 2407 */         mIS();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/* 2414 */         mATOMIC();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/* 2421 */         mCOMPONENT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/* 2428 */         mCOMPOUND();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/* 2435 */         mCONNECTOR();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/* 2442 */         mDATA();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 8:
/* 2449 */         mDEFINE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 9:
/* 2456 */         mDELAY();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/* 2463 */         mDELAYABLE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/* 2470 */         mDO();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 12:
/* 2477 */         mBIPDOWN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 13:
/* 2484 */         mEAGER();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 14:
/* 2491 */         mELSE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 15:
/* 2498 */         mEND();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 16:
/* 2505 */         mEXPORT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 17:
/* 2512 */         mEXTERN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 18:
/* 2519 */         mFROM();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 19:
/* 2526 */         mHEADER();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 20:
/* 2533 */         mIF();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 21:
/* 2540 */         mIN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 22:
/* 2547 */         mINITIAL();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 23:
/* 2554 */         mLAZY();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 24:
/* 2561 */         mMODEL();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 25:
/* 2568 */         mMILLISECOND();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 26:
/* 2575 */         mMULTISHOT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 27:
/* 2582 */         mNANOSECOND();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 28:
/* 2589 */         mPACKAGE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 29:
/* 2596 */         mPLACE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 30:
/* 2603 */         mON();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 31:
/* 2610 */         mPORT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 32:
/* 2617 */         mPRIORITY();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 33:
/* 2624 */         mPROVIDED();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 34:
/* 2631 */         mSECOND();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 35:
/* 2638 */         mSINGLESHOT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 36:
/* 2645 */         mTIMED();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 37:
/* 2652 */         mTO();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 38:
/* 2659 */         mTYPE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 39:
/* 2666 */         mUSE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 40:
/* 2673 */         mBIPUP();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 41:
/* 2680 */         mRESET();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 42:
/* 2687 */         mCLOCK();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 43:
/* 2694 */         mCOMMA();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 44:
/* 2701 */         mLPAR();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 45:
/* 2708 */         mRPAR();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 46:
/* 2715 */         mLBRACKET();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 47:
/* 2722 */         mRBRACKET();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 48:
/* 2729 */         mLCURLY();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 49:
/* 2736 */         mRCURLY();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 50:
/* 2743 */         mDOT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 51:
/* 2750 */         mFIELD();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 52:
/* 2757 */         mQUOTE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 53:
/* 2764 */         mPLUS();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 54:
/* 2771 */         mMINUS();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 55:
/* 2778 */         mREF();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 56:
/* 2785 */         mMULT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 57:
/* 2792 */         mDIV();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 58:
/* 2799 */         mMOD();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 59:
/* 2806 */         mASSIGN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 60:
/* 2813 */         mNOT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 61:
/* 2820 */         mOR();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 62:
/* 2827 */         mAND();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 63:
/* 2834 */         mBITWISEOR();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 64:
/* 2841 */         mBITWISEXOR();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 65:
/* 2848 */         mBITWISENOT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 66:
/* 2855 */         mLT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 67:
/* 2862 */         mLTE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 68:
/* 2869 */         mGT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 69:
/* 2876 */         mGTE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 70:
/* 2883 */         mEQUALS();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 71:
/* 2890 */         mDIFF();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 72:
/* 2897 */         mLEFTSHIFT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 73:
/* 2904 */         mRIGHTSHIFT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 74:
/* 2911 */         mINCREMENT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 75:
/* 2918 */         mDECREMENT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 76:
/* 2925 */         mPLUSASSIGN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 77:
/* 2932 */         mMINUSASSIGN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 78:
/* 2939 */         mMULTASSIGN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 79:
/* 2946 */         mDIVASSIGN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 80:
/* 2953 */         mMODASSIGN();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 81:
/* 2960 */         mCOLON();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 82:
/* 2967 */         mDASH();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 83:
/* 2974 */         mQMARK();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 84:
/* 2981 */         mFLOAT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 85:
/* 2988 */         mINTEGER();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 86:
/* 2995 */         mSTRING();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 87:
/* 3002 */         mIDENTIFIER();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 88:
/* 3009 */         mINDEX();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 89:
/* 3016 */         mCODE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 90:
/* 3023 */         mWHITESPACE();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 91:
/* 3030 */         mCOMMENT();
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 92:
/* 3037 */         mLINE_COMMENT();
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3047 */   protected DFA10 dfa10 = new DFA10((BaseRecognizer)this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String DFA10_eotS = "\002￿\020-\005￿\001R\002￿\001V\001￿\001Y\001[\001]\001a\001c\001e\001g\001i\002￿\001l\001o\001￿\001p\001￿\001q\004￿\001s\001t\001v\005-\001\017-\001\003-\001\002-\001\001-$￿\001-\001￿\b-\001￿\002-\001¥\016-\001￿\003-\001￿\001-\001¸\001￿\006-\001¿\002-\001Â\001-\001Ä\001￿\002-\001Ç\001-\001É\006-\001Ð\005-\001Ö\001￿\005-\001Ý\001￿\001-\001à\001￿\001á\001￿\002-\001￿\001-\001￿\001å\004-\001ê\001￿\004-\001ï\001￿\001ð\001-\001ò\003-\001￿\001ö\001-\002￿\001ø\001ù\001ú\001￿\004-\001￿\002-\001ā\001-\002￿\001ă\001￿\003-\001￿\001-\003￿\003-\001ċ\002-\001￿\001-\001￿\001-\001Đ\005-\001￿\001Ė\001ė\001-\001ę\001￿\001Ě\001ě\001-\001ĝ\001-\002￿\001-\003￿\001-\001￿\001ġ\001Ģ\001ģ\003￿";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String DFA10_eofS = "Ĥ￿";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String DFA10_minS = "\001\t\001￿\001f\001t\001l\002a\001r\001e\001a\001i\002a\001n\001e\001i\001p\001e\005￿\001#\002￿\001-\001￿\001+\001&\001=\001*\003=\001|\002￿\001<\001=\001￿\0010\001￿\001.\004￿\0030\001o\001m\001o\001t\001f\0010\001g\001s\001d\001p\001o\001a\001z\001d\002l\001n\001c\001a\001r\001i\0010\001c\001n\001m\0010\001p\001e\0010\001s$￿\001t\001￿\001m\001p\001n\001c\001a\001i\001a\001n\001￿\002e\0010\001o\001e\001m\001d\001y\001e\001l\001t\001o\001k\001c\001t\001o\001v\001￿\001o\001g\001e\001￿\001e\0010\001￿\001e\002i\001o\001e\001k\0010\001n\001y\0010\001r\0010\001￿\002r\0010\001e\0010\001l\002i\001s\001a\001e\0010\001r\001i\001n\001l\001d\0010\001￿\001t\001a\001c\001n\001c\0010\001￿\001e\0010\001￿\0010\001￿\001t\001n\001￿\001r\001￿\0010\002s\001e\001g\0010\001￿\001i\002d\001e\0010\001￿\0010\001l\0010\001e\001n\001t\001￿\0010\001b\002￿\0030\001￿\001e\001h\001c\001e\001￿\001t\001e\0010\001s\002￿\0010\001￿\001n\001d\001o\001￿\001l\003￿\001c\002o\0010\001y\001d\001￿\001h\001￿\001t\0010\001r\001e\001o\001t\001n\001￿\0020\001o\0010\001￿\0020\001n\0010\001d\002￿\001t\003￿\001d\001￿\0030\003￿";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String DFA10_maxS = "\001~\001￿\001s\001t\002o\001x\001r\001e\001a\001u\001a\001r\001n\001i\001y\001s\001e\005￿\001#\002￿\001>\001￿\001=\001&\005=\001|\002￿\001=\001>\001￿\001z\001￿\0019\004￿\003z\001o\001n\001o\001t\001l\001z\001g\001s\001d\001t\001o\001a\001z\001d\002l\001n\001c\001a\001r\001o\001z\001c\001n\001m\001z\001p\001e\001z\001s$￿\001t\001￿\001m\001p\001n\001c\001a\001i\001a\001n\001￿\002e\001z\001o\001e\001m\001d\001y\001e\001l\001t\001o\001k\001c\001t\001o\001v\001￿\001o\001g\001e\001￿\001e\001z\001￿\001e\002i\001o\001e\001k\001z\001n\001y\001z\001r\001z\001￿\002r\001z\001e\001z\001l\002i\001s\001a\001e\001z\001r\001i\001n\001l\001d\001z\001￿\001t\001a\001c\001u\001c\001z\001￿\001e\001z\001￿\001z\001￿\001t\001n\001￿\001r\001￿\001z\002s\001e\001g\001z\001￿\001i\002d\001e\001z\001￿\001z\001l\001z\001e\001n\001t\001￿\001z\001b\002￿\003z\001￿\001e\001h\001c\001e\001￿\001t\001e\001z\001s\002￿\001z\001￿\001n\001d\001o\001￿\001l\003￿\001c\002o\001z\001y\001d\001￿\001h\001￿\001t\001z\001r\001e\001o\001t\001n\001￿\002z\001o\001z\001￿\002z\001n\001z\001d\002￿\001t\003￿\001d\001￿\003z\003￿";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String DFA10_acceptS = "\001￿\001\001\020￿\001+\001,\001-\001.\001/\001￿\0011\0012\001￿\0014\b￿\001@\001A\002￿\001Q\001￿\001S\001￿\001V\001W\001X\001Z!￿\001Y\0010\0013\001K\001M\0016\001J\001L\0015\001>\0017\001N\0018\001O\001[\001\\\0019\001P\001:\001F\001;\001G\001<\001=\001?\001C\001H\001B\001E\001I\001D\001R\001U\001T\001\002\001\024\001￿\001\025\b￿\001\013\021￿\001\036\003￿\001%\002￿\001(\f￿\001\017\022￿\001'\006￿\001\007\002￿\001\f\001￿\001\016\002￿\001\022\001￿\001\027\006￿\001\037\005￿\001&\006￿\001*\002￿\001\t\001\r\003￿\001\030\004￿\001\035\004￿\001$\001)\001￿\001\003\003￿\001\b\001￿\001\020\001\021\001\023\006￿\001\"\001￿\001\026\007￿\001\034\004￿\001\005\005￿\001 \001!\001￿\001\004\001\006\001\n\001￿\001\032\003￿\001\033\001#\001\031";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String DFA10_specialS = "Ĥ￿}>";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3127 */   static final String[] DFA10_transitionS = new String[] { "\002/\001￿\002/\022￿\001/\001\"\001,\001￿\001.\001 \001\035\001\033\001\023\001\024\001\036\001\034\001\022\001\032\001\031\001\037\n+\001(\001\001\001&\001!\001'\001*\001￿\032-\001\025\001￿\001\026\001$\001)\001￿\001\003\001-\001\004\001\005\001\006\001\007\001-\001\b\001\002\002-\001\t\001\n\001\013\001\r\001\f\001-\001\021\001\016\001\017\001\020\005-\001\027\001#\001\030\001%", "", "\0011\007￿\0012\004￿\0010", "\0013", "\0015\002￿\0014", "\0016\003￿\0017\t￿\0018", "\0019\n￿\001:\001￿\001;\t￿\001<", "\001=", "\001>", "\001?", "\001A\005￿\001@\005￿\001B", "\001C", "\001D\n￿\001E\002￿\001F\002￿\001G", "\001H", "\001I\003￿\001J", "\001K\005￿\001L\t￿\001M", "\001O\002￿\001N", "\001P", "", "", "", "", "", "\001Q", "", "", "\001T\017￿\001U\001S", "", "\001W\021￿\001X", "\001Z", "\001\\", "\001_\004￿\001`\r￿\001^", "\001b", "\001d", "\001f", "\001h", "", "", "\001k\001j", "\001m\001n", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001r\001￿\n+", "", "", "", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\b-\001u\021-", "\001w", "\001x\001y", "\001z", "\001{", "\001|\005￿\001}", "\n-\007￿\032-\004￿\001-\001￿\026-\001~\003-", "\001", "\001", "\001", "\001\003￿\001", "\001", "\001", "\001", "\001", "\001", "\001", "\001", "\001", "\001", "\001", "\001\005￿\001", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001", "\001", "\001", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001", "\001", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "\001", "", "\001", "\001", "\001", "\001", "\001", "\001 ", "\001¡", "\001¢", "", "\001£", "\001¤", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001¦", "\001§", "\001¨", "\001©", "\001ª", "\001«", "\001¬", "\001­", "\001®", "\001¯", "\001°", "\001±", "\001²", "\001³", "", "\001´", "\001µ", "\001¶", "", "\001·", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001¹", "\001º", "\001»", "\001¼", "\001½", "\001¾", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001À", "\001Á", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ã", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001Å", "\001Æ", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001È", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ê", "\001Ë", "\001Ì", "\001Í", "\001Î", "\001Ï", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ñ", "\001Ò", "\001Ó", "\001Ô", "\001Õ", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001×", "\001Ø", "\001Ù", "\001Ú\006￿\001Û", "\001Ü", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001Þ", "\n-\007￿\032-\004￿\001-\001￿\001ß\031-", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001â", "\001ã", "", "\001ä", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001æ", "\001ç", "\001è", "\001é", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001ë", "\001ì", "\001í", "\001î", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001ñ", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001ó", "\001ô", "\001õ", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001÷", "", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001û", "\001ü", "\001ý", "\001þ", "", "\001ÿ", "\001Ā", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ă", "", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\001Ą", "\001ą", "\001Ć", "", "\001ć", "", "", "", "\001Ĉ", "\001ĉ", "\001Ċ", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Č", "\001č", "", "\001Ď", "", "\001ď", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001đ", "\001Ē", "\001ē", "\001Ĕ", "\001ĕ", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ę", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ĝ", "\n-\007￿\032-\004￿\001-\001￿\032-", "\001Ğ", "", "", "\001ğ", "", "", "", "\001Ġ", "", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "\n-\007￿\032-\004￿\001-\001￿\032-", "", "", "" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3427 */   static final short[] DFA10_eot = DFA.unpackEncodedString("\002￿\020-\005￿\001R\002￿\001V\001￿\001Y\001[\001]\001a\001c\001e\001g\001i\002￿\001l\001o\001￿\001p\001￿\001q\004￿\001s\001t\001v\005-\001\017-\001\003-\001\002-\001\001-$￿\001-\001￿\b-\001￿\002-\001¥\016-\001￿\003-\001￿\001-\001¸\001￿\006-\001¿\002-\001Â\001-\001Ä\001￿\002-\001Ç\001-\001É\006-\001Ð\005-\001Ö\001￿\005-\001Ý\001￿\001-\001à\001￿\001á\001￿\002-\001￿\001-\001￿\001å\004-\001ê\001￿\004-\001ï\001￿\001ð\001-\001ò\003-\001￿\001ö\001-\002￿\001ø\001ù\001ú\001￿\004-\001￿\002-\001ā\001-\002￿\001ă\001￿\003-\001￿\001-\003￿\003-\001ċ\002-\001￿\001-\001￿\001-\001Đ\005-\001￿\001Ė\001ė\001-\001ę\001￿\001Ě\001ě\001-\001ĝ\001-\002￿\001-\003￿\001-\001￿\001ġ\001Ģ\001ģ\003￿");
/* 3428 */   static final short[] DFA10_eof = DFA.unpackEncodedString("Ĥ￿");
/* 3429 */   static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars("\001\t\001￿\001f\001t\001l\002a\001r\001e\001a\001i\002a\001n\001e\001i\001p\001e\005￿\001#\002￿\001-\001￿\001+\001&\001=\001*\003=\001|\002￿\001<\001=\001￿\0010\001￿\001.\004￿\0030\001o\001m\001o\001t\001f\0010\001g\001s\001d\001p\001o\001a\001z\001d\002l\001n\001c\001a\001r\001i\0010\001c\001n\001m\0010\001p\001e\0010\001s$￿\001t\001￿\001m\001p\001n\001c\001a\001i\001a\001n\001￿\002e\0010\001o\001e\001m\001d\001y\001e\001l\001t\001o\001k\001c\001t\001o\001v\001￿\001o\001g\001e\001￿\001e\0010\001￿\001e\002i\001o\001e\001k\0010\001n\001y\0010\001r\0010\001￿\002r\0010\001e\0010\001l\002i\001s\001a\001e\0010\001r\001i\001n\001l\001d\0010\001￿\001t\001a\001c\001n\001c\0010\001￿\001e\0010\001￿\0010\001￿\001t\001n\001￿\001r\001￿\0010\002s\001e\001g\0010\001￿\001i\002d\001e\0010\001￿\0010\001l\0010\001e\001n\001t\001￿\0010\001b\002￿\0030\001￿\001e\001h\001c\001e\001￿\001t\001e\0010\001s\002￿\0010\001￿\001n\001d\001o\001￿\001l\003￿\001c\002o\0010\001y\001d\001￿\001h\001￿\001t\0010\001r\001e\001o\001t\001n\001￿\0020\001o\0010\001￿\0020\001n\0010\001d\002￿\001t\003￿\001d\001￿\0030\003￿");
/* 3430 */   static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars("\001~\001￿\001s\001t\002o\001x\001r\001e\001a\001u\001a\001r\001n\001i\001y\001s\001e\005￿\001#\002￿\001>\001￿\001=\001&\005=\001|\002￿\001=\001>\001￿\001z\001￿\0019\004￿\003z\001o\001n\001o\001t\001l\001z\001g\001s\001d\001t\001o\001a\001z\001d\002l\001n\001c\001a\001r\001o\001z\001c\001n\001m\001z\001p\001e\001z\001s$￿\001t\001￿\001m\001p\001n\001c\001a\001i\001a\001n\001￿\002e\001z\001o\001e\001m\001d\001y\001e\001l\001t\001o\001k\001c\001t\001o\001v\001￿\001o\001g\001e\001￿\001e\001z\001￿\001e\002i\001o\001e\001k\001z\001n\001y\001z\001r\001z\001￿\002r\001z\001e\001z\001l\002i\001s\001a\001e\001z\001r\001i\001n\001l\001d\001z\001￿\001t\001a\001c\001u\001c\001z\001￿\001e\001z\001￿\001z\001￿\001t\001n\001￿\001r\001￿\001z\002s\001e\001g\001z\001￿\001i\002d\001e\001z\001￿\001z\001l\001z\001e\001n\001t\001￿\001z\001b\002￿\003z\001￿\001e\001h\001c\001e\001￿\001t\001e\001z\001s\002￿\001z\001￿\001n\001d\001o\001￿\001l\003￿\001c\002o\001z\001y\001d\001￿\001h\001￿\001t\001z\001r\001e\001o\001t\001n\001￿\002z\001o\001z\001￿\002z\001n\001z\001d\002￿\001t\003￿\001d\001￿\003z\003￿");
/* 3431 */   static final short[] DFA10_accept = DFA.unpackEncodedString("\001￿\001\001\020￿\001+\001,\001-\001.\001/\001￿\0011\0012\001￿\0014\b￿\001@\001A\002￿\001Q\001￿\001S\001￿\001V\001W\001X\001Z!￿\001Y\0010\0013\001K\001M\0016\001J\001L\0015\001>\0017\001N\0018\001O\001[\001\\\0019\001P\001:\001F\001;\001G\001<\001=\001?\001C\001H\001B\001E\001I\001D\001R\001U\001T\001\002\001\024\001￿\001\025\b￿\001\013\021￿\001\036\003￿\001%\002￿\001(\f￿\001\017\022￿\001'\006￿\001\007\002￿\001\f\001￿\001\016\002￿\001\022\001￿\001\027\006￿\001\037\005￿\001&\006￿\001*\002￿\001\t\001\r\003￿\001\030\004￿\001\035\004￿\001$\001)\001￿\001\003\003￿\001\b\001￿\001\020\001\021\001\023\006￿\001\"\001￿\001\026\007￿\001\034\004￿\001\005\005￿\001 \001!\001￿\001\004\001\006\001\n\001￿\001\032\003￿\001\033\001#\001\031");
/* 3432 */   static final short[] DFA10_special = DFA.unpackEncodedString("Ĥ￿}>");
/*      */   static final short[][] DFA10_transition;
/*      */   
/*      */   static {
/* 3436 */     int numStates = DFA10_transitionS.length;
/* 3437 */     DFA10_transition = new short[numStates][];
/* 3438 */     for (int i = 0; i < numStates; i++)
/* 3439 */       DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]); 
/*      */   }
/*      */   
/*      */   class DFA10
/*      */     extends DFA
/*      */   {
/*      */     public DFA10(BaseRecognizer recognizer) {
/* 3446 */       this.recognizer = recognizer;
/* 3447 */       this.decisionNumber = 10;
/* 3448 */       this.eot = bipLexer.DFA10_eot;
/* 3449 */       this.eof = bipLexer.DFA10_eof;
/* 3450 */       this.min = bipLexer.DFA10_min;
/* 3451 */       this.max = bipLexer.DFA10_max;
/* 3452 */       this.accept = bipLexer.DFA10_accept;
/* 3453 */       this.special = bipLexer.DFA10_special;
/* 3454 */       this.transition = bipLexer.DFA10_transition;
/*      */     }
/*      */     public String getDescription() {
/* 3457 */       return "1:1: Tokens : ( T__116 | IS | ATOMIC | COMPONENT | COMPOUND | CONNECTOR | DATA | DEFINE | DELAY | DELAYABLE | DO | BIPDOWN | EAGER | ELSE | END | EXPORT | EXTERN | FROM | HEADER | IF | IN | INITIAL | LAZY | MODEL | MILLISECOND | MULTISHOT | NANOSECOND | PACKAGE | PLACE | ON | PORT | PRIORITY | PROVIDED | SECOND | SINGLESHOT | TIMED | TO | TYPE | USE | BIPUP | RESET | CLOCK | COMMA | LPAR | RPAR | LBRACKET | RBRACKET | LCURLY | RCURLY | DOT | FIELD | QUOTE | PLUS | MINUS | REF | MULT | DIV | MOD | ASSIGN | NOT | OR | AND | BITWISEOR | BITWISEXOR | BITWISENOT | LT | LTE | GT | GTE | EQUALS | DIFF | LEFTSHIFT | RIGHTSHIFT | INCREMENT | DECREMENT | PLUSASSIGN | MINUSASSIGN | MULTASSIGN | DIVASSIGN | MODASSIGN | COLON | DASH | QMARK | FLOAT | INTEGER | STRING | IDENTIFIER | INDEX | CODE | WHITESPACE | COMMENT | LINE_COMMENT );";
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\bipLexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */