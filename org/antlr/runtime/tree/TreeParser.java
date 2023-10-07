/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.regex.Pattern;
/*     */ import org.antlr.runtime.BaseRecognizer;
/*     */ import org.antlr.runtime.BitSet;
/*     */ import org.antlr.runtime.CommonToken;
/*     */ import org.antlr.runtime.IntStream;
/*     */ import org.antlr.runtime.MismatchedTreeNodeException;
/*     */ import org.antlr.runtime.RecognitionException;
/*     */ import org.antlr.runtime.RecognizerSharedState;
/*     */ import org.antlr.runtime.Token;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeParser
/*     */   extends BaseRecognizer
/*     */ {
/*     */   public static final int DOWN = 2;
/*     */   public static final int UP = 3;
/*  44 */   static String dotdot = ".*[^.]\\.\\.[^.].*";
/*  45 */   static String doubleEtc = ".*\\.\\.\\.\\s+\\.\\.\\..*";
/*  46 */   static Pattern dotdotPattern = Pattern.compile(dotdot);
/*  47 */   static Pattern doubleEtcPattern = Pattern.compile(doubleEtc);
/*     */   
/*     */   protected TreeNodeStream input;
/*     */ 
/*     */   
/*     */   public TreeParser(TreeNodeStream input) {
/*  53 */     setTreeNodeStream(input);
/*     */   }
/*     */   
/*     */   public TreeParser(TreeNodeStream input, RecognizerSharedState state) {
/*  57 */     super(state);
/*  58 */     setTreeNodeStream(input);
/*     */   }
/*     */   
/*     */   public void reset() {
/*  62 */     super.reset();
/*  63 */     if (this.input != null) {
/*  64 */       this.input.seek(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTreeNodeStream(TreeNodeStream input) {
/*  70 */     this.input = input;
/*     */   }
/*     */   
/*     */   public TreeNodeStream getTreeNodeStream() {
/*  74 */     return this.input;
/*     */   }
/*     */   
/*     */   public String getSourceName() {
/*  78 */     return this.input.getSourceName();
/*     */   }
/*     */   
/*     */   protected Object getCurrentInputSymbol(IntStream input) {
/*  82 */     return ((TreeNodeStream)input).LT(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getMissingSymbol(IntStream input, RecognitionException e, int expectedTokenType, BitSet follow) {
/*  90 */     String tokenText = "<missing " + getTokenNames()[expectedTokenType] + ">";
/*     */     
/*  92 */     return new CommonTree((Token)new CommonToken(expectedTokenType, tokenText));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void matchAny(IntStream ignore) {
/* 100 */     this.state.errorRecovery = false;
/* 101 */     this.state.failed = false;
/* 102 */     Object look = this.input.LT(1);
/* 103 */     if (this.input.getTreeAdaptor().getChildCount(look) == 0) {
/* 104 */       this.input.consume();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 109 */     int level = 0;
/* 110 */     int tokenType = this.input.getTreeAdaptor().getType(look);
/* 111 */     while (tokenType != -1 && (tokenType != 3 || level != 0)) {
/* 112 */       this.input.consume();
/* 113 */       look = this.input.LT(1);
/* 114 */       tokenType = this.input.getTreeAdaptor().getType(look);
/* 115 */       if (tokenType == 2) {
/* 116 */         level++; continue;
/*     */       } 
/* 118 */       if (tokenType == 3) {
/* 119 */         level--;
/*     */       }
/*     */     } 
/* 122 */     this.input.consume();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
/* 134 */     throw new MismatchedTreeNodeException(ttype, (TreeNodeStream)input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorHeader(RecognitionException e) {
/* 142 */     return getGrammarFileName() + ": node from " + (e.approximateLineInfo ? "after " : "") + "line " + e.line + ":" + e.charPositionInLine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorMessage(RecognitionException e, String[] tokenNames) {
/* 150 */     if (this instanceof TreeParser) {
/* 151 */       TreeAdaptor adaptor = ((TreeNodeStream)e.input).getTreeAdaptor();
/* 152 */       e.token = adaptor.getToken(e.node);
/* 153 */       if (e.token == null) {
/* 154 */         e.token = (Token)new CommonToken(adaptor.getType(e.node), adaptor.getText(e.node));
/*     */       }
/*     */     } 
/*     */     
/* 158 */     return super.getErrorMessage(e, tokenNames);
/*     */   }
/*     */   
/*     */   public void traceIn(String ruleName, int ruleIndex) {
/* 162 */     traceIn(ruleName, ruleIndex, this.input.LT(1));
/*     */   }
/*     */   
/*     */   public void traceOut(String ruleName, int ruleIndex) {
/* 166 */     traceOut(ruleName, ruleIndex, this.input.LT(1));
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */