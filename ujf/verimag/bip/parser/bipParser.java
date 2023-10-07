/*       */ package ujf.verimag.bip.parser;
/*       */ import org.antlr.runtime.BitSet;
/*       */ import org.antlr.runtime.EarlyExitException;
/*       */ import org.antlr.runtime.IntStream;
/*       */ import org.antlr.runtime.MismatchedSetException;
/*       */ import org.antlr.runtime.NoViableAltException;
/*       */ import org.antlr.runtime.ParserRuleReturnScope;
/*       */ import org.antlr.runtime.RecognitionException;
/*       */ import org.antlr.runtime.Token;
/*       */ import org.antlr.runtime.TokenStream;
/*       */ import org.antlr.runtime.tree.CommonTree;
/*       */ import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
/*       */ import org.antlr.runtime.tree.RewriteRuleTokenStream;
/*       */ import org.antlr.runtime.tree.TreeAdaptor;
/*       */ 
/*       */ public class bipParser extends Parser {
/*    17 */   public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "UNARY_PLUS", "UNARY_MINUS", "UNARY_DEREFERENCE", "UNARY_REF", "UNARY_PREFIX_INCREMENT", "UNARY_PREFIX_DECREMENT", "UNARY_POSTFIX_INCREMENT", "UNARY_POSTFIX_DECREMENT", "UNION", "FUSION", "TRIGGER", "PORT_PARAMETER", "DATA_PARAMETER", "ACTUAL_PORT_PARAMETER", "ACTUAL_DATA_PARAMETER", "LOW_INTERACTION", "HIGH_INTERACTION", "EXPORT_PORT", "EXPORT_DATA", "PACKAGE", "IDENTIFIER", "END", "MODEL", "HEADER", "CODE", "USE", "PORT", "TYPE", "LPAR", "COMMA", "RPAR", "DOT", "CONNECTOR", "EXPORT", "DEFINE", "ON", "PROVIDED", "BIPUP", "BIPDOWN", "ATOMIC", "COMPOUND", "SINGLESHOT", "MULTISHOT", "IS", "DATA", "COMPONENT", "QMARK", "COLON", "LBRACKET", "RBRACKET", "PRIORITY", "DELAY", "LT", "MULT", "ASSIGN", "PLACE", "INITIAL", "FROM", "TO", "EAGER", "DELAYABLE", "LAZY", "AND", "IN", "INTEGER", "DASH", "SECOND", "MILLISECOND", "NANOSECOND", "RESET", "DO", "CLOCK", "EXTERN", "TIMED", "PLUS", "QUOTE", "OR", "BITWISEOR", "BITWISEXOR", "REF", "EQUALS", "DIFF", "LTE", "GTE", "GT", "LEFTSHIFT", "RIGHTSHIFT", "MINUS", "DIV", "MOD", "NOT", "BITWISENOT", "INCREMENT", "DECREMENT", "FIELD", "FLOAT", "STRING", "INDEX", "IF", "ELSE", "LCURLY", "RCURLY", "PLUSASSIGN", "MINUSASSIGN", "MULTASSIGN", "DIVASSIGN", "MODASSIGN", "DIGIT", "LETTER", "WHITESPACE", "COMMENT", "LINE_COMMENT", "';'" };
/*       */   
/*       */   public static final int PACKAGE = 23;
/*       */   
/*       */   public static final int LT = 56;
/*       */   public static final int LETTER = 112;
/*       */   public static final int MOD = 93;
/*       */   public static final int RESET = 73;
/*       */   public static final int CONNECTOR = 36;
/*       */   public static final int TRIGGER = 14;
/*       */   public static final int BITWISEXOR = 82;
/*       */   public static final int DO = 74;
/*       */   public static final int EXTERN = 76;
/*       */   public static final int EQUALS = 84;
/*       */   public static final int NOT = 94;
/*       */   public static final int EOF = -1;
/*       */   public static final int LOW_INTERACTION = 19;
/*       */   public static final int UNARY_PLUS = 4;
/*       */   public static final int TYPE = 31;
/*       */   public static final int CODE = 28;
/*       */   public static final int LBRACKET = 52;
/*       */   public static final int QUOTE = 79;
/*       */   public static final int LPAR = 32;
/*       */   public static final int UNARY_PREFIX_DECREMENT = 9;
/*       */   public static final int EXPORT = 37;
/*       */   public static final int UNARY_REF = 7;
/*       */   public static final int COMMENT = 114;
/*       */   public static final int PLUSASSIGN = 106;
/*       */   public static final int BIPUP = 41;
/*       */   public static final int SECOND = 70;
/*       */   public static final int LINE_COMMENT = 115;
/*       */   public static final int INITIAL = 60;
/*       */   public static final int ELSE = 103;
/*       */   public static final int ON = 39;
/*       */   public static final int WHITESPACE = 113;
/*       */   public static final int LCURLY = 104;
/*       */   public static final int UNARY_MINUS = 5;
/*       */   public static final int MULT = 57;
/*       */   public static final int SINGLESHOT = 45;
/*       */   public static final int RIGHTSHIFT = 90;
/*       */   public static final int DECREMENT = 97;
/*       */   public static final int PRIORITY = 54;
/*       */   public static final int DIFF = 85;
/*       */   public static final int OR = 80;
/*       */   public static final int PORT_PARAMETER = 15;
/*       */   public static final int GT = 88;
/*       */   public static final int FIELD = 98;
/*       */   public static final int MULTASSIGN = 108;
/*       */   public static final int USE = 29;
/*       */   public static final int END = 25;
/*       */   public static final int FROM = 61;
/*       */   public static final int QMARK = 50;
/*       */   public static final int ATOMIC = 43;
/*       */   public static final int DELAY = 55;
/*       */   public static final int DELAYABLE = 64;
/*       */   public static final int MILLISECOND = 71;
/*       */   public static final int T__116 = 116;
/*       */   public static final int GTE = 87;
/*       */   public static final int FLOAT = 99;
/*       */   public static final int ACTUAL_PORT_PARAMETER = 17;
/*       */   public static final int DATA_PARAMETER = 16;
/*       */   public static final int AND = 66;
/*       */   public static final int DEFINE = 38;
/*       */   public static final int LTE = 86;
/*       */   public static final int COMPOUND = 44;
/*       */   public static final int IF = 102;
/*       */   public static final int INDEX = 101;
/*       */   public static final int HIGH_INTERACTION = 20;
/*       */   public static final int EXPORT_DATA = 22;
/*       */   public static final int IN = 67;
/*       */   public static final int EAGER = 63;
/*       */   public static final int PROVIDED = 40;
/*       */   public static final int COMMA = 33;
/*       */   public static final int UNARY_POSTFIX_DECREMENT = 11;
/*       */   public static final int IS = 47;
/*       */   public static final int IDENTIFIER = 24;
/*       */   public static final int EXPORT_PORT = 21;
/*       */   public static final int UNARY_POSTFIX_INCREMENT = 10;
/*       */   public static final int MODEL = 26;
/*       */   public static final int PLUS = 78;
/*       */   public static final int LEFTSHIFT = 89;
/*       */   public static final int DIGIT = 111;
/*       */   public static final int HEADER = 27;
/*       */   public static final int RBRACKET = 53;
/*       */   public static final int DOT = 35;
/*       */   public static final int COMPONENT = 49;
/*       */   public static final int CLOCK = 75;
/*       */   public static final int INTEGER = 68;
/*       */   public static final int FUSION = 13;
/*       */   public static final int DIVASSIGN = 109;
/*       */   public static final int MODASSIGN = 110;
/*       */   public static final int DASH = 69;
/*       */   public static final int TO = 62;
/*       */   public static final int LAZY = 65;
/*       */   public static final int TIMED = 77;
/*       */   public static final int BIPDOWN = 42;
/*       */   public static final int MULTISHOT = 46;
/*       */   public static final int PLACE = 59;
/*       */   public static final int PORT = 30;
/*       */   public static final int MINUSASSIGN = 107;
/*       */   public static final int MINUS = 91;
/*       */   public static final int BITWISEOR = 81;
/*       */   public static final int UNION = 12;
/*       */   public static final int UNARY_PREFIX_INCREMENT = 8;
/*       */   public static final int REF = 83;
/*       */   public static final int COLON = 51;
/*       */   public static final int INCREMENT = 96;
/*       */   public static final int NANOSECOND = 72;
/*       */   public static final int UNARY_DEREFERENCE = 6;
/*       */   public static final int RCURLY = 105;
/*       */   public static final int ASSIGN = 58;
/*       */   public static final int RPAR = 34;
/*       */   public static final int ACTUAL_DATA_PARAMETER = 18;
/*       */   public static final int DIV = 92;
/*       */   public static final int DATA = 48;
/*       */   public static final int STRING = 100;
/*       */   public static final int BITWISENOT = 95;
/*       */   protected TreeAdaptor adaptor;
/*       */   private int nbErr;
/*       */   private ErrorMessage errorMsg;
/*       */   private String fileName;
/*       */   
/*       */   public bipParser(TokenStream input) {
/*   140 */     this(input, new RecognizerSharedState());
/*       */   }
/*       */   
/*   143 */   public bipParser(TokenStream input, RecognizerSharedState state) { super(input, state);
/*       */ 
/*       */ 
/*       */     
/*   147 */     this.adaptor = (TreeAdaptor)new CommonTreeAdaptor();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   160 */     this.nbErr = 0; }
/*       */   public void setTreeAdaptor(TreeAdaptor adaptor) {
/*       */     this.adaptor = adaptor;
/*       */   } public TreeAdaptor getTreeAdaptor() {
/*       */     return this.adaptor;
/*   165 */   } public int getNbErr() { return this.nbErr; }
/*       */   public String[] getTokenNames() { return tokenNames; }
/*       */   public String getGrammarFileName() { return "/local/poulhies/scratch/bip2/Compiler/Frontend/ujf.verimag.bip.parser/grammar/bip.g"; } public void reportError(RecognitionException arg0) {
/*   168 */     this.nbErr++;
/*   169 */     int l = arg0.line;
/*   170 */     int c = arg0.charPositionInLine;
/*   171 */     String msg = getErrorMessage(arg0, getTokenNames());
/*   172 */     this.errorMsg.sendErrorMessage(3, msg, l, c, this.fileName);
/*       */   }
/*       */   
/*       */   public void setErrorMessage(ErrorMessage errorMsg) {
/*   176 */     this.errorMsg = errorMsg;
/*       */   }
/*       */   public void setFileName(String fileName) {
/*   179 */     this.fileName = fileName;
/*       */   }
/*       */   
/*       */   public static class translation_unit_return extends ParserRuleReturnScope {
/*       */     CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   186 */       return this.tree;
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   public final translation_unit_return translation_unit() throws RecognitionException {
/*   192 */     translation_unit_return retval = new translation_unit_return();
/*   193 */     retval.start = this.input.LT(1);
/*       */     
/*   195 */     CommonTree root_0 = null;
/*       */     
/*   197 */     package_definition_return package_definition1 = null;
/*       */     
/*   199 */     model_definition_return model_definition2 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   205 */       int alt1 = 2;
/*   206 */       int LA1_0 = this.input.LA(1);
/*       */       
/*   208 */       if (LA1_0 == 23) {
/*   209 */         alt1 = 1;
/*       */       }
/*   211 */       else if (LA1_0 == 26) {
/*   212 */         alt1 = 2;
/*       */       } else {
/*       */         
/*   215 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*   216 */          NoViableAltException nvae = new NoViableAltException("", 1, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*   219 */         throw nvae;
/*       */       } 
/*   221 */       switch (alt1) {
/*       */ 
/*       */         
/*       */         case 1:
/*   225 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*   227 */           pushFollow(FOLLOW_package_definition_in_translation_unit196);
/*   228 */           package_definition1 = package_definition();
/*       */           
/*   230 */           this.state._fsp--;
/*   231 */           if (this.state.failed) return retval; 
/*   232 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, package_definition1.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*   239 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*   241 */           pushFollow(FOLLOW_model_definition_in_translation_unit202);
/*   242 */           model_definition2 = model_definition();
/*       */           
/*   244 */           this.state._fsp--;
/*   245 */           if (this.state.failed) return retval; 
/*   246 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, model_definition2.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*   252 */       retval.stop = this.input.LT(-1);
/*       */       
/*   254 */       if (this.state.backtracking == 0)
/*       */       {
/*   256 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   257 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   260 */     } catch (RecognitionException re) {
/*   261 */       reportError(re);
/*   262 */       recover((IntStream)this.input, re);
/*   263 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   268 */     return retval;
/*       */   }
/*       */   
/*       */   public static class package_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   274 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final package_definition_return package_definition() throws RecognitionException {
/*   280 */     package_definition_return retval = new package_definition_return();
/*   281 */     retval.start = this.input.LT(1);
/*       */     
/*   283 */     CommonTree root_0 = null;
/*       */     
/*   285 */     Token PACKAGE3 = null;
/*   286 */     Token IDENTIFIER4 = null;
/*   287 */     Token END8 = null;
/*   288 */     use_package_return use_package5 = null;
/*       */     
/*   290 */     bip_definition_return bip_definition6 = null;
/*       */     
/*   292 */     opaque_code_return opaque_code7 = null;
/*       */ 
/*       */     
/*   295 */     CommonTree PACKAGE3_tree = null;
/*   296 */     CommonTree IDENTIFIER4_tree = null;
/*   297 */     CommonTree END8_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   303 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*   305 */       PACKAGE3 = (Token)match((IntStream)this.input, 23, FOLLOW_PACKAGE_in_package_definition215); if (this.state.failed) return retval; 
/*   306 */       if (this.state.backtracking == 0) {
/*   307 */         PACKAGE3_tree = (CommonTree)this.adaptor.create(PACKAGE3);
/*   308 */         root_0 = (CommonTree)this.adaptor.becomeRoot(PACKAGE3_tree, root_0);
/*       */       } 
/*   310 */       IDENTIFIER4 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_package_definition218); if (this.state.failed) return retval; 
/*   311 */       if (this.state.backtracking == 0) {
/*   312 */         IDENTIFIER4_tree = (CommonTree)this.adaptor.create(IDENTIFIER4);
/*   313 */         this.adaptor.addChild(root_0, IDENTIFIER4_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*   318 */         int alt2 = 2;
/*   319 */         int LA2_0 = this.input.LA(1);
/*       */         
/*   321 */         if (LA2_0 == 29) {
/*   322 */           alt2 = 1;
/*       */         }
/*       */ 
/*       */         
/*   326 */         switch (alt2) {
/*       */ 
/*       */           
/*       */           case 1:
/*   330 */             pushFollow(FOLLOW_use_package_in_package_definition227);
/*   331 */             use_package5 = use_package();
/*       */             
/*   333 */             this.state._fsp--;
/*   334 */             if (this.state.failed) return retval; 
/*   335 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, use_package5.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*   346 */       int cnt3 = 0;
/*       */       while (true) {
/*       */         EarlyExitException eee;
/*   349 */         int alt3 = 3;
/*   350 */         int LA3_0 = this.input.LA(1);
/*       */         
/*   352 */         if (LA3_0 == 30 || LA3_0 == 36 || (LA3_0 >= 43 && LA3_0 <= 44)) {
/*   353 */           alt3 = 1;
/*       */         }
/*   355 */         else if (LA3_0 >= 27 && LA3_0 <= 28) {
/*   356 */           alt3 = 2;
/*       */         } 
/*       */ 
/*       */         
/*   360 */         switch (alt3) {
/*       */ 
/*       */           
/*       */           case 1:
/*   364 */             pushFollow(FOLLOW_bip_definition_in_package_definition239);
/*   365 */             bip_definition6 = bip_definition();
/*       */             
/*   367 */             this.state._fsp--;
/*   368 */             if (this.state.failed) return retval; 
/*   369 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bip_definition6.getTree());
/*       */             
/*       */             break;
/*       */ 
/*       */ 
/*       */           
/*       */           case 2:
/*   376 */             pushFollow(FOLLOW_opaque_code_in_package_definition243);
/*   377 */             opaque_code7 = opaque_code();
/*       */             
/*   379 */             this.state._fsp--;
/*   380 */             if (this.state.failed) return retval; 
/*   381 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, opaque_code7.getTree());
/*       */             
/*       */             break;
/*       */ 
/*       */           
/*       */           default:
/*   387 */             if (cnt3 >= 1)
/*   388 */               break;  if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*   389 */              eee = new EarlyExitException(3, (IntStream)this.input);
/*       */             
/*   391 */             throw eee;
/*       */         } 
/*   393 */         cnt3++;
/*       */       } 
/*       */       
/*   396 */       END8 = (Token)match((IntStream)this.input, 25, FOLLOW_END_in_package_definition252); if (this.state.failed) return retval; 
/*   397 */       if (this.state.backtracking == 0) {
/*   398 */         END8_tree = (CommonTree)this.adaptor.create(END8);
/*   399 */         this.adaptor.addChild(root_0, END8_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*   404 */       retval.stop = this.input.LT(-1);
/*       */       
/*   406 */       if (this.state.backtracking == 0)
/*       */       {
/*   408 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   409 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   412 */     } catch (RecognitionException re) {
/*   413 */       reportError(re);
/*   414 */       recover((IntStream)this.input, re);
/*   415 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   420 */     return retval;
/*       */   }
/*       */   
/*       */   public static class model_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   426 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final model_definition_return model_definition() throws RecognitionException {
/*   432 */     model_definition_return retval = new model_definition_return();
/*   433 */     retval.start = this.input.LT(1);
/*       */     
/*   435 */     CommonTree root_0 = null;
/*       */     
/*   437 */     Token MODEL9 = null;
/*   438 */     Token IDENTIFIER10 = null;
/*   439 */     Token END15 = null;
/*   440 */     use_package_return use_package11 = null;
/*       */     
/*   442 */     bip_definition_return bip_definition12 = null;
/*       */     
/*   444 */     opaque_code_return opaque_code13 = null;
/*       */     
/*   446 */     component_definition_return component_definition14 = null;
/*       */ 
/*       */     
/*   449 */     CommonTree MODEL9_tree = null;
/*   450 */     CommonTree IDENTIFIER10_tree = null;
/*   451 */     CommonTree END15_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   457 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*   459 */       MODEL9 = (Token)match((IntStream)this.input, 26, FOLLOW_MODEL_in_model_definition265); if (this.state.failed) return retval; 
/*   460 */       if (this.state.backtracking == 0) {
/*   461 */         MODEL9_tree = (CommonTree)this.adaptor.create(MODEL9);
/*   462 */         root_0 = (CommonTree)this.adaptor.becomeRoot(MODEL9_tree, root_0);
/*       */       } 
/*   464 */       IDENTIFIER10 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_model_definition268); if (this.state.failed) return retval; 
/*   465 */       if (this.state.backtracking == 0) {
/*   466 */         IDENTIFIER10_tree = (CommonTree)this.adaptor.create(IDENTIFIER10);
/*   467 */         this.adaptor.addChild(root_0, IDENTIFIER10_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*   472 */         int alt4 = 2;
/*   473 */         int LA4_0 = this.input.LA(1);
/*       */         
/*   475 */         if (LA4_0 == 29) {
/*   476 */           alt4 = 1;
/*       */         }
/*       */ 
/*       */         
/*   480 */         switch (alt4) {
/*       */ 
/*       */           
/*       */           case 1:
/*   484 */             pushFollow(FOLLOW_use_package_in_model_definition284);
/*   485 */             use_package11 = use_package();
/*       */             
/*   487 */             this.state._fsp--;
/*   488 */             if (this.state.failed) return retval; 
/*   489 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, use_package11.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*   500 */       int cnt5 = 0;
/*       */       while (true) {
/*       */         EarlyExitException eee;
/*   503 */         int alt5 = 3;
/*   504 */         int LA5_0 = this.input.LA(1);
/*       */         
/*   506 */         if (LA5_0 == 30 || LA5_0 == 36 || (LA5_0 >= 43 && LA5_0 <= 44)) {
/*   507 */           alt5 = 1;
/*       */         }
/*   509 */         else if (LA5_0 >= 27 && LA5_0 <= 28) {
/*   510 */           alt5 = 2;
/*       */         } 
/*       */ 
/*       */         
/*   514 */         switch (alt5) {
/*       */ 
/*       */           
/*       */           case 1:
/*   518 */             pushFollow(FOLLOW_bip_definition_in_model_definition296);
/*   519 */             bip_definition12 = bip_definition();
/*       */             
/*   521 */             this.state._fsp--;
/*   522 */             if (this.state.failed) return retval; 
/*   523 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bip_definition12.getTree());
/*       */             
/*       */             break;
/*       */ 
/*       */ 
/*       */           
/*       */           case 2:
/*   530 */             pushFollow(FOLLOW_opaque_code_in_model_definition300);
/*   531 */             opaque_code13 = opaque_code();
/*       */             
/*   533 */             this.state._fsp--;
/*   534 */             if (this.state.failed) return retval; 
/*   535 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, opaque_code13.getTree());
/*       */             
/*       */             break;
/*       */ 
/*       */           
/*       */           default:
/*   541 */             if (cnt5 >= 1)
/*   542 */               break;  if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*   543 */              eee = new EarlyExitException(5, (IntStream)this.input);
/*       */             
/*   545 */             throw eee;
/*       */         } 
/*   547 */         cnt5++;
/*       */       } 
/*       */       
/*   550 */       pushFollow(FOLLOW_component_definition_in_model_definition310);
/*   551 */       component_definition14 = component_definition();
/*       */       
/*   553 */       this.state._fsp--;
/*   554 */       if (this.state.failed) return retval; 
/*   555 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_definition14.getTree()); 
/*   556 */       END15 = (Token)match((IntStream)this.input, 25, FOLLOW_END_in_model_definition317); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*   560 */       retval.stop = this.input.LT(-1);
/*       */       
/*   562 */       if (this.state.backtracking == 0)
/*       */       {
/*   564 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   565 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   568 */     } catch (RecognitionException re) {
/*   569 */       reportError(re);
/*   570 */       recover((IntStream)this.input, re);
/*   571 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   576 */     return retval;
/*       */   }
/*       */   
/*       */   public static class opaque_code_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   582 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final opaque_code_return opaque_code() throws RecognitionException {
/*   588 */     opaque_code_return retval = new opaque_code_return();
/*   589 */     retval.start = this.input.LT(1);
/*       */     
/*   591 */     CommonTree root_0 = null;
/*       */     
/*   593 */     Token HEADER16 = null;
/*   594 */     Token CODE17 = null;
/*       */     
/*   596 */     CommonTree HEADER16_tree = null;
/*   597 */     CommonTree CODE17_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   603 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */       
/*   606 */       int alt6 = 2;
/*   607 */       int LA6_0 = this.input.LA(1);
/*       */       
/*   609 */       if (LA6_0 == 27) {
/*   610 */         alt6 = 1;
/*       */       }
/*   612 */       switch (alt6) {
/*       */ 
/*       */         
/*       */         case 1:
/*   616 */           HEADER16 = (Token)match((IntStream)this.input, 27, FOLLOW_HEADER_in_opaque_code331); if (this.state.failed) return retval; 
/*   617 */           if (this.state.backtracking == 0) {
/*   618 */             HEADER16_tree = (CommonTree)this.adaptor.create(HEADER16);
/*   619 */             this.adaptor.addChild(root_0, HEADER16_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   627 */       CODE17 = (Token)match((IntStream)this.input, 28, FOLLOW_CODE_in_opaque_code334); if (this.state.failed) return retval; 
/*   628 */       if (this.state.backtracking == 0) {
/*   629 */         CODE17_tree = (CommonTree)this.adaptor.create(CODE17);
/*   630 */         root_0 = (CommonTree)this.adaptor.becomeRoot(CODE17_tree, root_0);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*   635 */       retval.stop = this.input.LT(-1);
/*       */       
/*   637 */       if (this.state.backtracking == 0)
/*       */       {
/*   639 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   640 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   643 */     } catch (RecognitionException re) {
/*   644 */       reportError(re);
/*   645 */       recover((IntStream)this.input, re);
/*   646 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   651 */     return retval;
/*       */   }
/*       */   
/*       */   public static class use_package_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   657 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final use_package_return use_package() throws RecognitionException {
/*   663 */     use_package_return retval = new use_package_return();
/*   664 */     retval.start = this.input.LT(1);
/*       */     
/*   666 */     CommonTree root_0 = null;
/*       */     
/*   668 */     Token USE18 = null;
/*   669 */     bip_package_return bip_package19 = null;
/*       */ 
/*       */     
/*   672 */     CommonTree USE18_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   678 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*   680 */       USE18 = (Token)match((IntStream)this.input, 29, FOLLOW_USE_in_use_package348); if (this.state.failed) return retval; 
/*   681 */       if (this.state.backtracking == 0) {
/*   682 */         USE18_tree = (CommonTree)this.adaptor.create(USE18);
/*   683 */         root_0 = (CommonTree)this.adaptor.becomeRoot(USE18_tree, root_0);
/*       */       } 
/*   685 */       pushFollow(FOLLOW_bip_package_in_use_package351);
/*   686 */       bip_package19 = bip_package();
/*       */       
/*   688 */       this.state._fsp--;
/*   689 */       if (this.state.failed) return retval; 
/*   690 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bip_package19.getTree());
/*       */ 
/*       */ 
/*       */       
/*   694 */       retval.stop = this.input.LT(-1);
/*       */       
/*   696 */       if (this.state.backtracking == 0)
/*       */       {
/*   698 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   699 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   702 */     } catch (RecognitionException re) {
/*   703 */       reportError(re);
/*   704 */       recover((IntStream)this.input, re);
/*   705 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   710 */     return retval;
/*       */   }
/*       */   
/*       */   public static class bip_package_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   716 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final bip_package_return bip_package() throws RecognitionException {
/*   722 */     bip_package_return retval = new bip_package_return();
/*   723 */     retval.start = this.input.LT(1);
/*       */     
/*   725 */     CommonTree root_0 = null;
/*       */     
/*   727 */     Token IDENTIFIER20 = null;
/*       */     
/*   729 */     CommonTree IDENTIFIER20_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   735 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*   737 */       IDENTIFIER20 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_bip_package364); if (this.state.failed) return retval; 
/*   738 */       if (this.state.backtracking == 0) {
/*   739 */         IDENTIFIER20_tree = (CommonTree)this.adaptor.create(IDENTIFIER20);
/*   740 */         this.adaptor.addChild(root_0, IDENTIFIER20_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*   745 */       retval.stop = this.input.LT(-1);
/*       */       
/*   747 */       if (this.state.backtracking == 0)
/*       */       {
/*   749 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   750 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   753 */     } catch (RecognitionException re) {
/*   754 */       reportError(re);
/*   755 */       recover((IntStream)this.input, re);
/*   756 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   761 */     return retval;
/*       */   }
/*       */   
/*       */   public static class bip_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   767 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final bip_definition_return bip_definition() throws RecognitionException {
/*   773 */     bip_definition_return retval = new bip_definition_return();
/*   774 */     retval.start = this.input.LT(1);
/*       */     
/*   776 */     CommonTree root_0 = null;
/*       */     
/*   778 */     port_type_definition_return port_type_definition21 = null;
/*       */     
/*   780 */     connector_type_definition_return connector_type_definition22 = null;
/*       */     
/*   782 */     component_type_definition_return component_type_definition23 = null;
/*       */ 
/*       */     
/*       */     try {
/*       */       NoViableAltException nvae;
/*       */       
/*   788 */       int alt7 = 3;
/*   789 */       switch (this.input.LA(1)) {
/*       */         
/*       */         case 30:
/*   792 */           alt7 = 1;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 36:
/*   797 */           alt7 = 2;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 43:
/*       */         case 44:
/*   803 */           alt7 = 3;
/*       */           break;
/*       */         
/*       */         default:
/*   807 */           if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*   808 */            nvae = new NoViableAltException("", 7, 0, (IntStream)this.input);
/*       */ 
/*       */           
/*   811 */           throw nvae;
/*       */       } 
/*       */       
/*   814 */       switch (alt7) {
/*       */ 
/*       */         
/*       */         case 1:
/*   818 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*   820 */           pushFollow(FOLLOW_port_type_definition_in_bip_definition379);
/*   821 */           port_type_definition21 = port_type_definition();
/*       */           
/*   823 */           this.state._fsp--;
/*   824 */           if (this.state.failed) return retval; 
/*   825 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_type_definition21.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*   832 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*   834 */           pushFollow(FOLLOW_connector_type_definition_in_bip_definition384);
/*   835 */           connector_type_definition22 = connector_type_definition();
/*       */           
/*   837 */           this.state._fsp--;
/*   838 */           if (this.state.failed) return retval; 
/*   839 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, connector_type_definition22.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 3:
/*   846 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*   848 */           pushFollow(FOLLOW_component_type_definition_in_bip_definition389);
/*   849 */           component_type_definition23 = component_type_definition();
/*       */           
/*   851 */           this.state._fsp--;
/*   852 */           if (this.state.failed) return retval; 
/*   853 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_type_definition23.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*   859 */       retval.stop = this.input.LT(-1);
/*       */       
/*   861 */       if (this.state.backtracking == 0)
/*       */       {
/*   863 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   864 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   867 */     } catch (RecognitionException re) {
/*   868 */       reportError(re);
/*   869 */       recover((IntStream)this.input, re);
/*   870 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   875 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_type_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   881 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_type_definition_return port_type_definition() throws RecognitionException {
/*   887 */     port_type_definition_return retval = new port_type_definition_return();
/*   888 */     retval.start = this.input.LT(1);
/*       */     
/*   890 */     CommonTree root_0 = null;
/*       */     
/*   892 */     Token PORT24 = null;
/*   893 */     Token TYPE25 = null;
/*   894 */     Token IDENTIFIER26 = null;
/*   895 */     data_type_profile_return data_type_profile27 = null;
/*       */ 
/*       */     
/*   898 */     CommonTree PORT24_tree = null;
/*   899 */     CommonTree TYPE25_tree = null;
/*   900 */     CommonTree IDENTIFIER26_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   906 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*   908 */       PORT24 = (Token)match((IntStream)this.input, 30, FOLLOW_PORT_in_port_type_definition404); if (this.state.failed) return retval; 
/*   909 */       if (this.state.backtracking == 0) {
/*   910 */         PORT24_tree = (CommonTree)this.adaptor.create(PORT24);
/*   911 */         root_0 = (CommonTree)this.adaptor.becomeRoot(PORT24_tree, root_0);
/*       */       } 
/*   913 */       TYPE25 = (Token)match((IntStream)this.input, 31, FOLLOW_TYPE_in_port_type_definition407); if (this.state.failed) return retval; 
/*   914 */       if (this.state.backtracking == 0) {
/*   915 */         TYPE25_tree = (CommonTree)this.adaptor.create(TYPE25);
/*   916 */         this.adaptor.addChild(root_0, TYPE25_tree);
/*       */       } 
/*   918 */       IDENTIFIER26 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port_type_definition409); if (this.state.failed) return retval; 
/*   919 */       if (this.state.backtracking == 0) {
/*   920 */         IDENTIFIER26_tree = (CommonTree)this.adaptor.create(IDENTIFIER26);
/*   921 */         this.adaptor.addChild(root_0, IDENTIFIER26_tree);
/*       */       } 
/*       */       
/*   924 */       int alt8 = 2;
/*   925 */       int LA8_0 = this.input.LA(1);
/*       */       
/*   927 */       if (LA8_0 == 32) {
/*   928 */         alt8 = 1;
/*       */       }
/*   930 */       switch (alt8) {
/*       */ 
/*       */         
/*       */         case 1:
/*   934 */           pushFollow(FOLLOW_data_type_profile_in_port_type_definition413);
/*   935 */           data_type_profile27 = data_type_profile();
/*       */           
/*   937 */           this.state._fsp--;
/*   938 */           if (this.state.failed) return retval; 
/*   939 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, data_type_profile27.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   949 */       retval.stop = this.input.LT(-1);
/*       */       
/*   951 */       if (this.state.backtracking == 0)
/*       */       {
/*   953 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*   954 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*   957 */     } catch (RecognitionException re) {
/*   958 */       reportError(re);
/*   959 */       recover((IntStream)this.input, re);
/*   960 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*   965 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_type_profile_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*   971 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_type_profile_return port_type_profile() throws RecognitionException {
/*   977 */     port_type_profile_return retval = new port_type_profile_return();
/*   978 */     retval.start = this.input.LT(1);
/*       */     
/*   980 */     CommonTree root_0 = null;
/*       */     
/*   982 */     Token LPAR28 = null;
/*   983 */     Token COMMA30 = null;
/*   984 */     Token RPAR32 = null;
/*   985 */     port_fpar_definition_return port_fpar_definition29 = null;
/*       */     
/*   987 */     port_fpar_definition_return port_fpar_definition31 = null;
/*       */ 
/*       */     
/*   990 */     CommonTree LPAR28_tree = null;
/*   991 */     CommonTree COMMA30_tree = null;
/*   992 */     CommonTree RPAR32_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   998 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1000 */       LPAR28 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_port_type_profile431); if (this.state.failed) return retval; 
/*  1001 */       pushFollow(FOLLOW_port_fpar_definition_in_port_type_profile434);
/*  1002 */       port_fpar_definition29 = port_fpar_definition();
/*       */       
/*  1004 */       this.state._fsp--;
/*  1005 */       if (this.state.failed) return retval; 
/*  1006 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_fpar_definition29.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  1010 */         int alt9 = 2;
/*  1011 */         int LA9_0 = this.input.LA(1);
/*       */         
/*  1013 */         if (LA9_0 == 33) {
/*  1014 */           alt9 = 1;
/*       */         }
/*       */ 
/*       */         
/*  1018 */         switch (alt9) {
/*       */ 
/*       */           
/*       */           case 1:
/*  1022 */             COMMA30 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_port_type_profile437); if (this.state.failed) return retval; 
/*  1023 */             pushFollow(FOLLOW_port_fpar_definition_in_port_type_profile440);
/*  1024 */             port_fpar_definition31 = port_fpar_definition();
/*       */             
/*  1026 */             this.state._fsp--;
/*  1027 */             if (this.state.failed) return retval; 
/*  1028 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_fpar_definition31.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  1038 */       RPAR32 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_port_type_profile444); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*  1042 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1044 */       if (this.state.backtracking == 0)
/*       */       {
/*  1046 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1047 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1050 */     } catch (RecognitionException re) {
/*  1051 */       reportError(re);
/*  1052 */       recover((IntStream)this.input, re);
/*  1053 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1058 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_type_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1064 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_type_return port_type() throws RecognitionException {
/*  1070 */     port_type_return retval = new port_type_return();
/*  1071 */     retval.start = this.input.LT(1);
/*       */     
/*  1073 */     CommonTree root_0 = null;
/*       */     
/*  1075 */     Token IDENTIFIER33 = null;
/*  1076 */     Token DOT34 = null;
/*  1077 */     Token IDENTIFIER35 = null;
/*       */     
/*  1079 */     CommonTree IDENTIFIER33_tree = null;
/*  1080 */     CommonTree DOT34_tree = null;
/*  1081 */     CommonTree IDENTIFIER35_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1087 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1089 */       IDENTIFIER33 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port_type459); if (this.state.failed) return retval; 
/*  1090 */       if (this.state.backtracking == 0) {
/*  1091 */         IDENTIFIER33_tree = (CommonTree)this.adaptor.create(IDENTIFIER33);
/*  1092 */         this.adaptor.addChild(root_0, IDENTIFIER33_tree);
/*       */       } 
/*       */       
/*  1095 */       int alt10 = 2;
/*  1096 */       int LA10_0 = this.input.LA(1);
/*       */       
/*  1098 */       if (LA10_0 == 35) {
/*  1099 */         alt10 = 1;
/*       */       }
/*  1101 */       switch (alt10) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1105 */           DOT34 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_port_type462); if (this.state.failed) return retval; 
/*  1106 */           if (this.state.backtracking == 0) {
/*  1107 */             DOT34_tree = (CommonTree)this.adaptor.create(DOT34);
/*  1108 */             root_0 = (CommonTree)this.adaptor.becomeRoot(DOT34_tree, root_0);
/*       */           } 
/*  1110 */           IDENTIFIER35 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port_type465); if (this.state.failed) return retval; 
/*  1111 */           if (this.state.backtracking == 0) {
/*  1112 */             IDENTIFIER35_tree = (CommonTree)this.adaptor.create(IDENTIFIER35);
/*  1113 */             this.adaptor.addChild(root_0, IDENTIFIER35_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1124 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1126 */       if (this.state.backtracking == 0)
/*       */       {
/*  1128 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1129 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1132 */     } catch (RecognitionException re) {
/*  1133 */       reportError(re);
/*  1134 */       recover((IntStream)this.input, re);
/*  1135 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1140 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_fpar_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1146 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_fpar_definition_return port_fpar_definition() throws RecognitionException {
/*  1152 */     port_fpar_definition_return retval = new port_fpar_definition_return();
/*  1153 */     retval.start = this.input.LT(1);
/*       */     
/*  1155 */     CommonTree root_0 = null;
/*       */     
/*  1157 */     Token IDENTIFIER37 = null;
/*  1158 */     port_type_return port_type36 = null;
/*       */ 
/*       */     
/*  1161 */     CommonTree IDENTIFIER37_tree = null;
/*  1162 */     RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFIER");
/*  1163 */     RewriteRuleSubtreeStream stream_port_type = new RewriteRuleSubtreeStream(this.adaptor, "rule port_type");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1168 */       pushFollow(FOLLOW_port_type_in_port_fpar_definition480);
/*  1169 */       port_type36 = port_type();
/*       */       
/*  1171 */       this.state._fsp--;
/*  1172 */       if (this.state.failed) return retval; 
/*  1173 */       if (this.state.backtracking == 0) stream_port_type.add(port_type36.getTree()); 
/*  1174 */       IDENTIFIER37 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port_fpar_definition482); if (this.state.failed) return retval; 
/*  1175 */       if (this.state.backtracking == 0) stream_IDENTIFIER.add(IDENTIFIER37);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1186 */       if (this.state.backtracking == 0) {
/*  1187 */         retval.tree = root_0;
/*  1188 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  1190 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1195 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  1196 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(15, "PORT_PARAMETER"), root_1);
/*       */         
/*  1198 */         this.adaptor.addChild(root_1, stream_port_type.nextTree());
/*  1199 */         this.adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());
/*       */         
/*  1201 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1206 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  1209 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1211 */       if (this.state.backtracking == 0)
/*       */       {
/*  1213 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1214 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1217 */     } catch (RecognitionException re) {
/*  1218 */       reportError(re);
/*  1219 */       recover((IntStream)this.input, re);
/*  1220 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1225 */     return retval;
/*       */   }
/*       */   
/*       */   public static class connector_type_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1231 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final connector_type_definition_return connector_type_definition() throws RecognitionException {
/*  1237 */     connector_type_definition_return retval = new connector_type_definition_return();
/*  1238 */     retval.start = this.input.LT(1);
/*       */     
/*  1240 */     CommonTree root_0 = null;
/*       */     
/*  1242 */     Token CONNECTOR38 = null;
/*  1243 */     Token TYPE39 = null;
/*  1244 */     Token IDENTIFIER40 = null;
/*  1245 */     Token CODE44 = null;
/*  1246 */     Token END47 = null;
/*  1247 */     port_type_profile_return port_type_profile41 = null;
/*       */     
/*  1249 */     data_type_profile_return data_type_profile42 = null;
/*       */     
/*  1251 */     header_code_return header_code43 = null;
/*       */     
/*  1253 */     connector_type_define_return connector_type_define45 = null;
/*       */     
/*  1255 */     export_port_connector_return export_port_connector46 = null;
/*       */ 
/*       */     
/*  1258 */     CommonTree CONNECTOR38_tree = null;
/*  1259 */     CommonTree TYPE39_tree = null;
/*  1260 */     CommonTree IDENTIFIER40_tree = null;
/*  1261 */     CommonTree CODE44_tree = null;
/*  1262 */     CommonTree END47_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1268 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1270 */       CONNECTOR38 = (Token)match((IntStream)this.input, 36, FOLLOW_CONNECTOR_in_connector_type_definition509); if (this.state.failed) return retval; 
/*  1271 */       if (this.state.backtracking == 0) {
/*  1272 */         CONNECTOR38_tree = (CommonTree)this.adaptor.create(CONNECTOR38);
/*  1273 */         root_0 = (CommonTree)this.adaptor.becomeRoot(CONNECTOR38_tree, root_0);
/*       */       } 
/*  1275 */       TYPE39 = (Token)match((IntStream)this.input, 31, FOLLOW_TYPE_in_connector_type_definition512); if (this.state.failed) return retval; 
/*  1276 */       if (this.state.backtracking == 0) {
/*  1277 */         TYPE39_tree = (CommonTree)this.adaptor.create(TYPE39);
/*  1278 */         this.adaptor.addChild(root_0, TYPE39_tree);
/*       */       } 
/*  1280 */       IDENTIFIER40 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_connector_type_definition514); if (this.state.failed) return retval; 
/*  1281 */       if (this.state.backtracking == 0) {
/*  1282 */         IDENTIFIER40_tree = (CommonTree)this.adaptor.create(IDENTIFIER40);
/*  1283 */         this.adaptor.addChild(root_0, IDENTIFIER40_tree);
/*       */       } 
/*  1285 */       pushFollow(FOLLOW_port_type_profile_in_connector_type_definition521);
/*  1286 */       port_type_profile41 = port_type_profile();
/*       */       
/*  1288 */       this.state._fsp--;
/*  1289 */       if (this.state.failed) return retval; 
/*  1290 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_type_profile41.getTree());
/*       */       
/*  1292 */       int alt11 = 2;
/*  1293 */       int LA11_0 = this.input.LA(1);
/*       */       
/*  1295 */       if (LA11_0 == 32) {
/*  1296 */         alt11 = 1;
/*       */       }
/*  1298 */       switch (alt11) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1302 */           pushFollow(FOLLOW_data_type_profile_in_connector_type_definition535);
/*  1303 */           data_type_profile42 = data_type_profile();
/*       */           
/*  1305 */           this.state._fsp--;
/*  1306 */           if (this.state.failed) return retval; 
/*  1307 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, data_type_profile42.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1315 */       int alt12 = 2;
/*  1316 */       int LA12_0 = this.input.LA(1);
/*       */       
/*  1318 */       if (LA12_0 == 27) {
/*  1319 */         alt12 = 1;
/*       */       }
/*  1321 */       switch (alt12) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1325 */           pushFollow(FOLLOW_header_code_in_connector_type_definition544);
/*  1326 */           header_code43 = header_code();
/*       */           
/*  1328 */           this.state._fsp--;
/*  1329 */           if (this.state.failed) return retval; 
/*  1330 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, header_code43.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1338 */       int alt13 = 2;
/*  1339 */       int LA13_0 = this.input.LA(1);
/*       */       
/*  1341 */       if (LA13_0 == 28) {
/*  1342 */         alt13 = 1;
/*       */       }
/*  1344 */       switch (alt13) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1348 */           CODE44 = (Token)match((IntStream)this.input, 28, FOLLOW_CODE_in_connector_type_definition551); if (this.state.failed) return retval; 
/*  1349 */           if (this.state.backtracking == 0) {
/*  1350 */             CODE44_tree = (CommonTree)this.adaptor.create(CODE44);
/*  1351 */             this.adaptor.addChild(root_0, CODE44_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1359 */       pushFollow(FOLLOW_connector_type_define_in_connector_type_definition557);
/*  1360 */       connector_type_define45 = connector_type_define();
/*       */       
/*  1362 */       this.state._fsp--;
/*  1363 */       if (this.state.failed) return retval; 
/*  1364 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, connector_type_define45.getTree());
/*       */       
/*  1366 */       int alt14 = 2;
/*  1367 */       int LA14_0 = this.input.LA(1);
/*       */       
/*  1369 */       if (LA14_0 == 37) {
/*  1370 */         alt14 = 1;
/*       */       }
/*  1372 */       switch (alt14) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1376 */           pushFollow(FOLLOW_export_port_connector_in_connector_type_definition564);
/*  1377 */           export_port_connector46 = export_port_connector();
/*       */           
/*  1379 */           this.state._fsp--;
/*  1380 */           if (this.state.failed) return retval; 
/*  1381 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, export_port_connector46.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  1388 */       END47 = (Token)match((IntStream)this.input, 25, FOLLOW_END_in_connector_type_definition571); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*  1392 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1394 */       if (this.state.backtracking == 0)
/*       */       {
/*  1396 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1397 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1400 */     } catch (RecognitionException re) {
/*  1401 */       reportError(re);
/*  1402 */       recover((IntStream)this.input, re);
/*  1403 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1408 */     return retval;
/*       */   }
/*       */   
/*       */   public static class header_code_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1414 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final header_code_return header_code() throws RecognitionException {
/*  1420 */     header_code_return retval = new header_code_return();
/*  1421 */     retval.start = this.input.LT(1);
/*       */     
/*  1423 */     CommonTree root_0 = null;
/*       */     
/*  1425 */     Token HEADER48 = null;
/*  1426 */     Token CODE49 = null;
/*       */     
/*  1428 */     CommonTree HEADER48_tree = null;
/*  1429 */     CommonTree CODE49_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1435 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1437 */       HEADER48 = (Token)match((IntStream)this.input, 27, FOLLOW_HEADER_in_header_code586); if (this.state.failed) return retval; 
/*  1438 */       if (this.state.backtracking == 0) {
/*  1439 */         HEADER48_tree = (CommonTree)this.adaptor.create(HEADER48);
/*  1440 */         this.adaptor.addChild(root_0, HEADER48_tree);
/*       */       } 
/*  1442 */       CODE49 = (Token)match((IntStream)this.input, 28, FOLLOW_CODE_in_header_code588); if (this.state.failed) return retval; 
/*  1443 */       if (this.state.backtracking == 0) {
/*  1444 */         CODE49_tree = (CommonTree)this.adaptor.create(CODE49);
/*  1445 */         root_0 = (CommonTree)this.adaptor.becomeRoot(CODE49_tree, root_0);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  1450 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1452 */       if (this.state.backtracking == 0)
/*       */       {
/*  1454 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1455 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1458 */     } catch (RecognitionException re) {
/*  1459 */       reportError(re);
/*  1460 */       recover((IntStream)this.input, re);
/*  1461 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1466 */     return retval;
/*       */   }
/*       */   
/*       */   public static class export_port_connector_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1472 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final export_port_connector_return export_port_connector() throws RecognitionException {
/*  1478 */     export_port_connector_return retval = new export_port_connector_return();
/*  1479 */     retval.start = this.input.LT(1);
/*       */     
/*  1481 */     CommonTree root_0 = null;
/*       */     
/*  1483 */     Token EXPORT50 = null;
/*  1484 */     Token PORT51 = null;
/*  1485 */     port_type_return port_type52 = null;
/*       */     
/*  1487 */     port_return port53 = null;
/*       */     
/*  1489 */     data_port_return data_port54 = null;
/*       */ 
/*       */     
/*  1492 */     CommonTree EXPORT50_tree = null;
/*  1493 */     CommonTree PORT51_tree = null;
/*  1494 */     RewriteRuleTokenStream stream_PORT = new RewriteRuleTokenStream(this.adaptor, "token PORT");
/*  1495 */     RewriteRuleTokenStream stream_EXPORT = new RewriteRuleTokenStream(this.adaptor, "token EXPORT");
/*  1496 */     RewriteRuleSubtreeStream stream_port = new RewriteRuleSubtreeStream(this.adaptor, "rule port");
/*  1497 */     RewriteRuleSubtreeStream stream_port_type = new RewriteRuleSubtreeStream(this.adaptor, "rule port_type");
/*  1498 */     RewriteRuleSubtreeStream stream_data_port = new RewriteRuleSubtreeStream(this.adaptor, "rule data_port");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1503 */       EXPORT50 = (Token)match((IntStream)this.input, 37, FOLLOW_EXPORT_in_export_port_connector602); if (this.state.failed) return retval; 
/*  1504 */       if (this.state.backtracking == 0) stream_EXPORT.add(EXPORT50);
/*       */       
/*  1506 */       PORT51 = (Token)match((IntStream)this.input, 30, FOLLOW_PORT_in_export_port_connector604); if (this.state.failed) return retval; 
/*  1507 */       if (this.state.backtracking == 0) stream_PORT.add(PORT51);
/*       */       
/*  1509 */       pushFollow(FOLLOW_port_type_in_export_port_connector606);
/*  1510 */       port_type52 = port_type();
/*       */       
/*  1512 */       this.state._fsp--;
/*  1513 */       if (this.state.failed) return retval; 
/*  1514 */       if (this.state.backtracking == 0) stream_port_type.add(port_type52.getTree()); 
/*  1515 */       pushFollow(FOLLOW_port_in_export_port_connector608);
/*  1516 */       port53 = port();
/*       */       
/*  1518 */       this.state._fsp--;
/*  1519 */       if (this.state.failed) return retval; 
/*  1520 */       if (this.state.backtracking == 0) stream_port.add(port53.getTree());
/*       */       
/*  1522 */       int alt15 = 2;
/*  1523 */       int LA15_0 = this.input.LA(1);
/*       */       
/*  1525 */       if (LA15_0 == 32) {
/*  1526 */         alt15 = 1;
/*       */       }
/*  1528 */       switch (alt15) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1532 */           pushFollow(FOLLOW_data_port_in_export_port_connector611);
/*  1533 */           data_port54 = data_port();
/*       */           
/*  1535 */           this.state._fsp--;
/*  1536 */           if (this.state.failed) return retval; 
/*  1537 */           if (this.state.backtracking == 0) stream_data_port.add(data_port54.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1553 */       if (this.state.backtracking == 0) {
/*  1554 */         retval.tree = root_0;
/*  1555 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  1557 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1562 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  1563 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(21, "EXPORT_PORT"), root_1);
/*       */         
/*  1565 */         this.adaptor.addChild(root_1, stream_port_type.nextTree());
/*  1566 */         this.adaptor.addChild(root_1, stream_port.nextTree());
/*       */         
/*  1568 */         if (stream_data_port.hasNext()) {
/*  1569 */           this.adaptor.addChild(root_1, stream_data_port.nextTree());
/*       */         }
/*       */         
/*  1572 */         stream_data_port.reset();
/*       */         
/*  1574 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1579 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  1582 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1584 */       if (this.state.backtracking == 0)
/*       */       {
/*  1586 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1587 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1590 */     } catch (RecognitionException re) {
/*  1591 */       reportError(re);
/*  1592 */       recover((IntStream)this.input, re);
/*  1593 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1598 */     return retval;
/*       */   }
/*       */   
/*       */   public static class connector_type_define_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1604 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final connector_type_define_return connector_type_define() throws RecognitionException {
/*  1610 */     connector_type_define_return retval = new connector_type_define_return();
/*  1611 */     retval.start = this.input.LT(1);
/*       */     
/*  1613 */     CommonTree root_0 = null;
/*       */     
/*  1615 */     Token DEFINE55 = null;
/*  1616 */     port_expression_return port_expression56 = null;
/*       */     
/*  1618 */     data_definition_return data_definition57 = null;
/*       */     
/*  1620 */     interaction_definition_return interaction_definition58 = null;
/*       */ 
/*       */     
/*  1623 */     CommonTree DEFINE55_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1629 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1631 */       DEFINE55 = (Token)match((IntStream)this.input, 38, FOLLOW_DEFINE_in_connector_type_define641); if (this.state.failed) return retval; 
/*  1632 */       if (this.state.backtracking == 0) {
/*  1633 */         DEFINE55_tree = (CommonTree)this.adaptor.create(DEFINE55);
/*  1634 */         root_0 = (CommonTree)this.adaptor.becomeRoot(DEFINE55_tree, root_0);
/*       */       } 
/*  1636 */       pushFollow(FOLLOW_port_expression_in_connector_type_define644);
/*  1637 */       port_expression56 = port_expression();
/*       */       
/*  1639 */       this.state._fsp--;
/*  1640 */       if (this.state.failed) return retval; 
/*  1641 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_expression56.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  1645 */         int LA16_1, alt16 = 3;
/*  1646 */         switch (this.input.LA(1)) {
/*       */           
/*       */           case 37:
/*  1649 */             LA16_1 = this.input.LA(2);
/*       */             
/*  1651 */             if (LA16_1 == 48 || (LA16_1 >= 76 && LA16_1 <= 77)) {
/*  1652 */               alt16 = 1;
/*       */             }
/*       */             break;
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*       */           case 48:
/*       */           case 76:
/*       */           case 77:
/*  1662 */             alt16 = 1;
/*       */             break;
/*       */ 
/*       */           
/*       */           case 39:
/*  1667 */             alt16 = 2;
/*       */             break;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  1673 */         switch (alt16) {
/*       */ 
/*       */           
/*       */           case 1:
/*  1677 */             pushFollow(FOLLOW_data_definition_in_connector_type_define652);
/*  1678 */             data_definition57 = data_definition();
/*       */             
/*  1680 */             this.state._fsp--;
/*  1681 */             if (this.state.failed) return retval; 
/*  1682 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, data_definition57.getTree());
/*       */             
/*       */             continue;
/*       */ 
/*       */ 
/*       */           
/*       */           case 2:
/*  1689 */             pushFollow(FOLLOW_interaction_definition_in_connector_type_define656);
/*  1690 */             interaction_definition58 = interaction_definition();
/*       */             
/*  1692 */             this.state._fsp--;
/*  1693 */             if (this.state.failed) return retval; 
/*  1694 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, interaction_definition58.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  1707 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1709 */       if (this.state.backtracking == 0)
/*       */       {
/*  1711 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1712 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1715 */     } catch (RecognitionException re) {
/*  1716 */       reportError(re);
/*  1717 */       recover((IntStream)this.input, re);
/*  1718 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1723 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_type_profile_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1729 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_type_profile_return data_type_profile() throws RecognitionException {
/*  1735 */     data_type_profile_return retval = new data_type_profile_return();
/*  1736 */     retval.start = this.input.LT(1);
/*       */     
/*  1738 */     CommonTree root_0 = null;
/*       */     
/*  1740 */     Token LPAR59 = null;
/*  1741 */     Token COMMA61 = null;
/*  1742 */     Token RPAR63 = null;
/*  1743 */     data_fpar_definition_return data_fpar_definition60 = null;
/*       */     
/*  1745 */     data_fpar_definition_return data_fpar_definition62 = null;
/*       */ 
/*       */     
/*  1748 */     CommonTree LPAR59_tree = null;
/*  1749 */     CommonTree COMMA61_tree = null;
/*  1750 */     CommonTree RPAR63_tree = null;
/*  1751 */     RewriteRuleTokenStream stream_RPAR = new RewriteRuleTokenStream(this.adaptor, "token RPAR");
/*  1752 */     RewriteRuleTokenStream stream_LPAR = new RewriteRuleTokenStream(this.adaptor, "token LPAR");
/*  1753 */     RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
/*  1754 */     RewriteRuleSubtreeStream stream_data_fpar_definition = new RewriteRuleSubtreeStream(this.adaptor, "rule data_fpar_definition");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1759 */       LPAR59 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_data_type_profile672); if (this.state.failed) return retval; 
/*  1760 */       if (this.state.backtracking == 0) stream_LPAR.add(LPAR59);
/*       */       
/*  1762 */       pushFollow(FOLLOW_data_fpar_definition_in_data_type_profile674);
/*  1763 */       data_fpar_definition60 = data_fpar_definition();
/*       */       
/*  1765 */       this.state._fsp--;
/*  1766 */       if (this.state.failed) return retval; 
/*  1767 */       if (this.state.backtracking == 0) stream_data_fpar_definition.add(data_fpar_definition60.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  1771 */         int alt17 = 2;
/*  1772 */         int LA17_0 = this.input.LA(1);
/*       */         
/*  1774 */         if (LA17_0 == 33) {
/*  1775 */           alt17 = 1;
/*       */         }
/*       */ 
/*       */         
/*  1779 */         switch (alt17) {
/*       */ 
/*       */           
/*       */           case 1:
/*  1783 */             COMMA61 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_data_type_profile677); if (this.state.failed) return retval; 
/*  1784 */             if (this.state.backtracking == 0) stream_COMMA.add(COMMA61);
/*       */             
/*  1786 */             pushFollow(FOLLOW_data_fpar_definition_in_data_type_profile679);
/*  1787 */             data_fpar_definition62 = data_fpar_definition();
/*       */             
/*  1789 */             this.state._fsp--;
/*  1790 */             if (this.state.failed) return retval; 
/*  1791 */             if (this.state.backtracking == 0) stream_data_fpar_definition.add(data_fpar_definition62.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  1801 */       RPAR63 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_data_type_profile683); if (this.state.failed) return retval; 
/*  1802 */       if (this.state.backtracking == 0) stream_RPAR.add(RPAR63);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1813 */       if (this.state.backtracking == 0) {
/*  1814 */         retval.tree = root_0;
/*  1815 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  1817 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1822 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  1823 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(16, "DATA_PARAMETER"), root_1);
/*       */         
/*  1825 */         this.adaptor.addChild(root_1, stream_data_fpar_definition.nextTree());
/*       */         
/*  1827 */         while (stream_data_fpar_definition.hasNext()) {
/*  1828 */           this.adaptor.addChild(root_1, stream_data_fpar_definition.nextTree());
/*       */         }
/*       */         
/*  1831 */         stream_data_fpar_definition.reset();
/*       */         
/*  1833 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1838 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  1841 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1843 */       if (this.state.backtracking == 0)
/*       */       {
/*  1845 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1846 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1849 */     } catch (RecognitionException re) {
/*  1850 */       reportError(re);
/*  1851 */       recover((IntStream)this.input, re);
/*  1852 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1857 */     return retval;
/*       */   }
/*       */   
/*       */   public static class connector_type_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1863 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final connector_type_return connector_type() throws RecognitionException {
/*  1869 */     connector_type_return retval = new connector_type_return();
/*  1870 */     retval.start = this.input.LT(1);
/*       */     
/*  1872 */     CommonTree root_0 = null;
/*       */     
/*  1874 */     Token IDENTIFIER64 = null;
/*  1875 */     Token DOT65 = null;
/*  1876 */     Token IDENTIFIER66 = null;
/*       */     
/*  1878 */     CommonTree IDENTIFIER64_tree = null;
/*  1879 */     CommonTree DOT65_tree = null;
/*  1880 */     CommonTree IDENTIFIER66_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1886 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1888 */       IDENTIFIER64 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_connector_type709); if (this.state.failed) return retval; 
/*  1889 */       if (this.state.backtracking == 0) {
/*  1890 */         IDENTIFIER64_tree = (CommonTree)this.adaptor.create(IDENTIFIER64);
/*  1891 */         this.adaptor.addChild(root_0, IDENTIFIER64_tree);
/*       */       } 
/*       */       
/*  1894 */       int alt18 = 2;
/*  1895 */       int LA18_0 = this.input.LA(1);
/*       */       
/*  1897 */       if (LA18_0 == 35) {
/*  1898 */         alt18 = 1;
/*       */       }
/*  1900 */       switch (alt18) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1904 */           DOT65 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_connector_type712); if (this.state.failed) return retval; 
/*  1905 */           if (this.state.backtracking == 0) {
/*  1906 */             DOT65_tree = (CommonTree)this.adaptor.create(DOT65);
/*  1907 */             root_0 = (CommonTree)this.adaptor.becomeRoot(DOT65_tree, root_0);
/*       */           } 
/*  1909 */           IDENTIFIER66 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_connector_type715); if (this.state.failed) return retval; 
/*  1910 */           if (this.state.backtracking == 0) {
/*  1911 */             IDENTIFIER66_tree = (CommonTree)this.adaptor.create(IDENTIFIER66);
/*  1912 */             this.adaptor.addChild(root_0, IDENTIFIER66_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1923 */       retval.stop = this.input.LT(-1);
/*       */       
/*  1925 */       if (this.state.backtracking == 0)
/*       */       {
/*  1927 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  1928 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  1931 */     } catch (RecognitionException re) {
/*  1932 */       reportError(re);
/*  1933 */       recover((IntStream)this.input, re);
/*  1934 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  1939 */     return retval;
/*       */   }
/*       */   
/*       */   public static class interaction_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  1945 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final interaction_definition_return interaction_definition() throws RecognitionException {
/*  1951 */     interaction_definition_return retval = new interaction_definition_return();
/*  1952 */     retval.start = this.input.LT(1);
/*       */     
/*  1954 */     CommonTree root_0 = null;
/*       */     
/*  1956 */     Token ON67 = null;
/*  1957 */     port_interaction_return port_interaction68 = null;
/*       */     
/*  1959 */     provided_expression_return provided_expression69 = null;
/*       */     
/*  1961 */     up_action_return up_action70 = null;
/*       */     
/*  1963 */     down_action_return down_action71 = null;
/*       */ 
/*       */     
/*  1966 */     CommonTree ON67_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  1972 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  1974 */       ON67 = (Token)match((IntStream)this.input, 39, FOLLOW_ON_in_interaction_definition729); if (this.state.failed) return retval; 
/*  1975 */       if (this.state.backtracking == 0) {
/*  1976 */         ON67_tree = (CommonTree)this.adaptor.create(ON67);
/*  1977 */         root_0 = (CommonTree)this.adaptor.becomeRoot(ON67_tree, root_0);
/*       */       } 
/*  1979 */       pushFollow(FOLLOW_port_interaction_in_interaction_definition732);
/*  1980 */       port_interaction68 = port_interaction();
/*       */       
/*  1982 */       this.state._fsp--;
/*  1983 */       if (this.state.failed) return retval; 
/*  1984 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_interaction68.getTree());
/*       */       
/*  1986 */       int alt19 = 2;
/*  1987 */       int LA19_0 = this.input.LA(1);
/*       */       
/*  1989 */       if (LA19_0 == 40) {
/*  1990 */         alt19 = 1;
/*       */       }
/*  1992 */       switch (alt19) {
/*       */ 
/*       */         
/*       */         case 1:
/*  1996 */           pushFollow(FOLLOW_provided_expression_in_interaction_definition738);
/*  1997 */           provided_expression69 = provided_expression();
/*       */           
/*  1999 */           this.state._fsp--;
/*  2000 */           if (this.state.failed) return retval; 
/*  2001 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, provided_expression69.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2009 */       int alt20 = 2;
/*  2010 */       int LA20_0 = this.input.LA(1);
/*       */       
/*  2012 */       if (LA20_0 == 41) {
/*  2013 */         alt20 = 1;
/*       */       }
/*  2015 */       switch (alt20) {
/*       */ 
/*       */         
/*       */         case 1:
/*  2019 */           pushFollow(FOLLOW_up_action_in_interaction_definition745);
/*  2020 */           up_action70 = up_action();
/*       */           
/*  2022 */           this.state._fsp--;
/*  2023 */           if (this.state.failed) return retval; 
/*  2024 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, up_action70.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2032 */       int alt21 = 2;
/*  2033 */       int LA21_0 = this.input.LA(1);
/*       */       
/*  2035 */       if (LA21_0 == 42) {
/*  2036 */         alt21 = 1;
/*       */       }
/*  2038 */       switch (alt21) {
/*       */ 
/*       */         
/*       */         case 1:
/*  2042 */           pushFollow(FOLLOW_down_action_in_interaction_definition752);
/*  2043 */           down_action71 = down_action();
/*       */           
/*  2045 */           this.state._fsp--;
/*  2046 */           if (this.state.failed) return retval; 
/*  2047 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, down_action71.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2057 */       retval.stop = this.input.LT(-1);
/*       */       
/*  2059 */       if (this.state.backtracking == 0)
/*       */       {
/*  2061 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  2062 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  2065 */     } catch (RecognitionException re) {
/*  2066 */       reportError(re);
/*  2067 */       recover((IntStream)this.input, re);
/*  2068 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  2073 */     return retval;
/*       */   }
/*       */   
/*       */   public static class provided_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  2079 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final provided_expression_return provided_expression() throws RecognitionException {
/*  2085 */     provided_expression_return retval = new provided_expression_return();
/*  2086 */     retval.start = this.input.LT(1);
/*       */     
/*  2088 */     CommonTree root_0 = null;
/*       */     
/*  2090 */     Token PROVIDED72 = null;
/*  2091 */     expression_return expression73 = null;
/*       */ 
/*       */     
/*  2094 */     CommonTree PROVIDED72_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  2100 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  2102 */       PROVIDED72 = (Token)match((IntStream)this.input, 40, FOLLOW_PROVIDED_in_provided_expression768); if (this.state.failed) return retval; 
/*  2103 */       if (this.state.backtracking == 0) {
/*  2104 */         PROVIDED72_tree = (CommonTree)this.adaptor.create(PROVIDED72);
/*  2105 */         root_0 = (CommonTree)this.adaptor.becomeRoot(PROVIDED72_tree, root_0);
/*       */       } 
/*  2107 */       pushFollow(FOLLOW_expression_in_provided_expression771);
/*  2108 */       expression73 = expression();
/*       */       
/*  2110 */       this.state._fsp--;
/*  2111 */       if (this.state.failed) return retval; 
/*  2112 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression73.getTree());
/*       */ 
/*       */ 
/*       */       
/*  2116 */       retval.stop = this.input.LT(-1);
/*       */       
/*  2118 */       if (this.state.backtracking == 0)
/*       */       {
/*  2120 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  2121 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  2124 */     } catch (RecognitionException re) {
/*  2125 */       reportError(re);
/*  2126 */       recover((IntStream)this.input, re);
/*  2127 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  2132 */     return retval;
/*       */   }
/*       */   
/*       */   public static class up_action_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  2138 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final up_action_return up_action() throws RecognitionException {
/*  2144 */     up_action_return retval = new up_action_return();
/*  2145 */     retval.start = this.input.LT(1);
/*       */     
/*  2147 */     CommonTree root_0 = null;
/*       */     
/*  2149 */     Token BIPUP74 = null;
/*  2150 */     action_return action75 = null;
/*       */ 
/*       */     
/*  2153 */     CommonTree BIPUP74_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  2159 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  2161 */       BIPUP74 = (Token)match((IntStream)this.input, 41, FOLLOW_BIPUP_in_up_action784); if (this.state.failed) return retval; 
/*  2162 */       if (this.state.backtracking == 0) {
/*  2163 */         BIPUP74_tree = (CommonTree)this.adaptor.create(BIPUP74);
/*  2164 */         root_0 = (CommonTree)this.adaptor.becomeRoot(BIPUP74_tree, root_0);
/*       */       } 
/*  2166 */       pushFollow(FOLLOW_action_in_up_action787);
/*  2167 */       action75 = action();
/*       */       
/*  2169 */       this.state._fsp--;
/*  2170 */       if (this.state.failed) return retval; 
/*  2171 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, action75.getTree());
/*       */ 
/*       */ 
/*       */       
/*  2175 */       retval.stop = this.input.LT(-1);
/*       */       
/*  2177 */       if (this.state.backtracking == 0)
/*       */       {
/*  2179 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  2180 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  2183 */     } catch (RecognitionException re) {
/*  2184 */       reportError(re);
/*  2185 */       recover((IntStream)this.input, re);
/*  2186 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  2191 */     return retval;
/*       */   }
/*       */   
/*       */   public static class down_action_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  2197 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final down_action_return down_action() throws RecognitionException {
/*  2203 */     down_action_return retval = new down_action_return();
/*  2204 */     retval.start = this.input.LT(1);
/*       */     
/*  2206 */     CommonTree root_0 = null;
/*       */     
/*  2208 */     Token BIPDOWN76 = null;
/*  2209 */     action_return action77 = null;
/*       */ 
/*       */     
/*  2212 */     CommonTree BIPDOWN76_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  2218 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  2220 */       BIPDOWN76 = (Token)match((IntStream)this.input, 42, FOLLOW_BIPDOWN_in_down_action802); if (this.state.failed) return retval; 
/*  2221 */       if (this.state.backtracking == 0) {
/*  2222 */         BIPDOWN76_tree = (CommonTree)this.adaptor.create(BIPDOWN76);
/*  2223 */         root_0 = (CommonTree)this.adaptor.becomeRoot(BIPDOWN76_tree, root_0);
/*       */       } 
/*  2225 */       pushFollow(FOLLOW_action_in_down_action805);
/*  2226 */       action77 = action();
/*       */       
/*  2228 */       this.state._fsp--;
/*  2229 */       if (this.state.failed) return retval; 
/*  2230 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, action77.getTree());
/*       */ 
/*       */ 
/*       */       
/*  2234 */       retval.stop = this.input.LT(-1);
/*       */       
/*  2236 */       if (this.state.backtracking == 0)
/*       */       {
/*  2238 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  2239 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  2242 */     } catch (RecognitionException re) {
/*  2243 */       reportError(re);
/*  2244 */       recover((IntStream)this.input, re);
/*  2245 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  2250 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_interaction_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  2256 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_interaction_return port_interaction() throws RecognitionException {
/*  2262 */     port_interaction_return retval = new port_interaction_return();
/*  2263 */     retval.start = this.input.LT(1);
/*       */     
/*  2265 */     CommonTree root_0 = null;
/*       */     
/*  2267 */     port_return port78 = null;
/*       */     
/*  2269 */     port_return port79 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  2277 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  2279 */       pushFollow(FOLLOW_port_in_port_interaction819);
/*  2280 */       port78 = port();
/*       */       
/*  2282 */       this.state._fsp--;
/*  2283 */       if (this.state.failed) return retval; 
/*  2284 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port78.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  2288 */         int alt22 = 2;
/*  2289 */         int LA22_0 = this.input.LA(1);
/*       */         
/*  2291 */         if (LA22_0 == 24) {
/*  2292 */           alt22 = 1;
/*       */         }
/*       */ 
/*       */         
/*  2296 */         switch (alt22) {
/*       */ 
/*       */           
/*       */           case 1:
/*  2300 */             pushFollow(FOLLOW_port_in_port_interaction822);
/*  2301 */             port79 = port();
/*       */             
/*  2303 */             this.state._fsp--;
/*  2304 */             if (this.state.failed) return retval; 
/*  2305 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port79.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  2318 */       retval.stop = this.input.LT(-1);
/*       */       
/*  2320 */       if (this.state.backtracking == 0)
/*       */       {
/*  2322 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  2323 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  2326 */     } catch (RecognitionException re) {
/*  2327 */       reportError(re);
/*  2328 */       recover((IntStream)this.input, re);
/*  2329 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  2334 */     return retval;
/*       */   }
/*       */   
/*       */   public static class component_type_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  2340 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final component_type_definition_return component_type_definition() throws RecognitionException {
/*  2346 */     component_type_definition_return retval = new component_type_definition_return();
/*  2347 */     retval.start = this.input.LT(1);
/*       */     
/*  2349 */     CommonTree root_0 = null;
/*       */     
/*  2351 */     Token ATOMIC80 = null;
/*  2352 */     Token TYPE81 = null;
/*  2353 */     Token IDENTIFIER82 = null;
/*  2354 */     Token CODE86 = null;
/*  2355 */     Token END96 = null;
/*  2356 */     Token COMPOUND97 = null;
/*  2357 */     Token TYPE98 = null;
/*  2358 */     Token IDENTIFIER99 = null;
/*  2359 */     Token CODE103 = null;
/*  2360 */     Token END109 = null;
/*  2361 */     component_parameters_return component_parameters83 = null;
/*       */     
/*  2363 */     component_activation_return component_activation84 = null;
/*       */     
/*  2365 */     header_code_return header_code85 = null;
/*       */     
/*  2367 */     data_definition_return data_definition87 = null;
/*       */     
/*  2369 */     clock_definition_return clock_definition88 = null;
/*       */     
/*  2371 */     port_definition_return port_definition89 = null;
/*       */     
/*  2373 */     place_definition_return place_definition90 = null;
/*       */     
/*  2375 */     initialization_return initialization91 = null;
/*       */     
/*  2377 */     transition_definition_return transition_definition92 = null;
/*       */     
/*  2379 */     priority_definition_return priority_definition93 = null;
/*       */     
/*  2381 */     export_port_definition_return export_port_definition94 = null;
/*       */     
/*  2383 */     export_data_definition_return export_data_definition95 = null;
/*       */     
/*  2385 */     component_parameters_return component_parameters100 = null;
/*       */     
/*  2387 */     component_activation_return component_activation101 = null;
/*       */     
/*  2389 */     header_code_return header_code102 = null;
/*       */     
/*  2391 */     component_definition_return component_definition104 = null;
/*       */     
/*  2393 */     connector_definition_return connector_definition105 = null;
/*       */     
/*  2395 */     priority_definition_return priority_definition106 = null;
/*       */     
/*  2397 */     export_port_definition_return export_port_definition107 = null;
/*       */     
/*  2399 */     export_data_definition_return export_data_definition108 = null;
/*       */ 
/*       */     
/*  2402 */     CommonTree ATOMIC80_tree = null;
/*  2403 */     CommonTree TYPE81_tree = null;
/*  2404 */     CommonTree IDENTIFIER82_tree = null;
/*  2405 */     CommonTree CODE86_tree = null;
/*  2406 */     CommonTree END96_tree = null;
/*  2407 */     CommonTree COMPOUND97_tree = null;
/*  2408 */     CommonTree TYPE98_tree = null;
/*  2409 */     CommonTree IDENTIFIER99_tree = null;
/*  2410 */     CommonTree CODE103_tree = null;
/*  2411 */     CommonTree END109_tree = null;
/*       */ 
/*       */     
/*       */     try {
/*  2415 */       int alt23, alt31, LA23_0, LA31_0, alt24, alt32, LA24_0, LA32_0, alt25, alt33, LA25_0, LA33_0, alt26, alt34, LA26_0, LA34_0, cnt27, cnt35, alt28, LA28_0, cnt29, alt37 = 2;
/*  2416 */       int LA37_0 = this.input.LA(1);
/*       */       
/*  2418 */       if (LA37_0 == 43) {
/*  2419 */         alt37 = 1;
/*       */       }
/*  2421 */       else if (LA37_0 == 44) {
/*  2422 */         alt37 = 2;
/*       */       } else {
/*       */         
/*  2425 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  2426 */          NoViableAltException nvae = new NoViableAltException("", 37, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  2429 */         throw nvae;
/*       */       } 
/*  2431 */       switch (alt37) {
/*       */ 
/*       */         
/*       */         case 1:
/*  2435 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  2437 */           ATOMIC80 = (Token)match((IntStream)this.input, 43, FOLLOW_ATOMIC_in_component_type_definition845); if (this.state.failed) return retval; 
/*  2438 */           if (this.state.backtracking == 0) {
/*  2439 */             ATOMIC80_tree = (CommonTree)this.adaptor.create(ATOMIC80);
/*  2440 */             root_0 = (CommonTree)this.adaptor.becomeRoot(ATOMIC80_tree, root_0);
/*       */           } 
/*  2442 */           TYPE81 = (Token)match((IntStream)this.input, 31, FOLLOW_TYPE_in_component_type_definition848); if (this.state.failed) return retval; 
/*  2443 */           if (this.state.backtracking == 0) {
/*  2444 */             TYPE81_tree = (CommonTree)this.adaptor.create(TYPE81);
/*  2445 */             this.adaptor.addChild(root_0, TYPE81_tree);
/*       */           } 
/*  2447 */           IDENTIFIER82 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_component_type_definition850); if (this.state.failed) return retval; 
/*  2448 */           if (this.state.backtracking == 0) {
/*  2449 */             IDENTIFIER82_tree = (CommonTree)this.adaptor.create(IDENTIFIER82);
/*  2450 */             this.adaptor.addChild(root_0, IDENTIFIER82_tree);
/*       */           } 
/*       */           
/*  2453 */           alt23 = 2;
/*  2454 */           LA23_0 = this.input.LA(1);
/*       */           
/*  2456 */           if (LA23_0 == 32) {
/*  2457 */             alt23 = 1;
/*       */           }
/*  2459 */           switch (alt23) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2463 */               pushFollow(FOLLOW_component_parameters_in_component_type_definition855);
/*  2464 */               component_parameters83 = component_parameters();
/*       */               
/*  2466 */               this.state._fsp--;
/*  2467 */               if (this.state.failed) return retval; 
/*  2468 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_parameters83.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2476 */           alt24 = 2;
/*  2477 */           LA24_0 = this.input.LA(1);
/*       */           
/*  2479 */           if (LA24_0 >= 45 && LA24_0 <= 46) {
/*  2480 */             alt24 = 1;
/*       */           }
/*  2482 */           switch (alt24) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2486 */               pushFollow(FOLLOW_component_activation_in_component_type_definition864);
/*  2487 */               component_activation84 = component_activation();
/*       */               
/*  2489 */               this.state._fsp--;
/*  2490 */               if (this.state.failed) return retval; 
/*  2491 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_activation84.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2499 */           alt25 = 2;
/*  2500 */           LA25_0 = this.input.LA(1);
/*       */           
/*  2502 */           if (LA25_0 == 27) {
/*  2503 */             alt25 = 1;
/*       */           }
/*  2505 */           switch (alt25) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2509 */               pushFollow(FOLLOW_header_code_in_component_type_definition873);
/*  2510 */               header_code85 = header_code();
/*       */               
/*  2512 */               this.state._fsp--;
/*  2513 */               if (this.state.failed) return retval; 
/*  2514 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, header_code85.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2522 */           alt26 = 2;
/*  2523 */           LA26_0 = this.input.LA(1);
/*       */           
/*  2525 */           if (LA26_0 == 28) {
/*  2526 */             alt26 = 1;
/*       */           }
/*  2528 */           switch (alt26) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2532 */               CODE86 = (Token)match((IntStream)this.input, 28, FOLLOW_CODE_in_component_type_definition880); if (this.state.failed) return retval; 
/*  2533 */               if (this.state.backtracking == 0) {
/*  2534 */                 CODE86_tree = (CommonTree)this.adaptor.create(CODE86);
/*  2535 */                 this.adaptor.addChild(root_0, CODE86_tree);
/*       */               } 
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2544 */           cnt27 = 0; while (true) {
/*       */             int LA27_2;
/*       */             EarlyExitException eee;
/*  2547 */             int alt27 = 5;
/*  2548 */             switch (this.input.LA(1)) {
/*       */               
/*       */               case 37:
/*  2551 */                 LA27_2 = this.input.LA(2);
/*       */                 
/*  2553 */                 if (LA27_2 == 30) {
/*  2554 */                   alt27 = 3; break;
/*       */                 } 
/*  2556 */                 if (LA27_2 == 48 || (LA27_2 >= 76 && LA27_2 <= 77)) {
/*  2557 */                   alt27 = 1;
/*       */                 }
/*       */                 break;
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*       */               case 48:
/*       */               case 76:
/*       */               case 77:
/*  2567 */                 alt27 = 1;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 75:
/*  2572 */                 alt27 = 2;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 30:
/*  2577 */                 alt27 = 3;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 59:
/*  2582 */                 alt27 = 4;
/*       */                 break;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/*  2588 */             switch (alt27) {
/*       */ 
/*       */               
/*       */               case 1:
/*  2592 */                 pushFollow(FOLLOW_data_definition_in_component_type_definition888);
/*  2593 */                 data_definition87 = data_definition();
/*       */                 
/*  2595 */                 this.state._fsp--;
/*  2596 */                 if (this.state.failed) return retval; 
/*  2597 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, data_definition87.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  2604 */                 pushFollow(FOLLOW_clock_definition_in_component_type_definition892);
/*  2605 */                 clock_definition88 = clock_definition();
/*       */                 
/*  2607 */                 this.state._fsp--;
/*  2608 */                 if (this.state.failed) return retval; 
/*  2609 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, clock_definition88.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */ 
/*       */               
/*       */               case 3:
/*  2616 */                 pushFollow(FOLLOW_port_definition_in_component_type_definition896);
/*  2617 */                 port_definition89 = port_definition();
/*       */                 
/*  2619 */                 this.state._fsp--;
/*  2620 */                 if (this.state.failed) return retval; 
/*  2621 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_definition89.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */ 
/*       */               
/*       */               case 4:
/*  2628 */                 pushFollow(FOLLOW_place_definition_in_component_type_definition900);
/*  2629 */                 place_definition90 = place_definition();
/*       */                 
/*  2631 */                 this.state._fsp--;
/*  2632 */                 if (this.state.failed) return retval; 
/*  2633 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place_definition90.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */               
/*       */               default:
/*  2639 */                 if (cnt27 >= 1)
/*  2640 */                   break;  if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  2641 */                  eee = new EarlyExitException(27, (IntStream)this.input);
/*       */                 
/*  2643 */                 throw eee;
/*       */             } 
/*  2645 */             cnt27++;
/*       */           } 
/*       */ 
/*       */           
/*  2649 */           alt28 = 2;
/*  2650 */           LA28_0 = this.input.LA(1);
/*       */           
/*  2652 */           if (LA28_0 == 60) {
/*  2653 */             alt28 = 1;
/*       */           }
/*  2655 */           switch (alt28) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2659 */               pushFollow(FOLLOW_initialization_in_component_type_definition909);
/*  2660 */               initialization91 = initialization();
/*       */               
/*  2662 */               this.state._fsp--;
/*  2663 */               if (this.state.failed) return retval; 
/*  2664 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, initialization91.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2672 */           cnt29 = 0;
/*       */           while (true) {
/*       */             EarlyExitException eee;
/*  2675 */             int alt29 = 3;
/*  2676 */             int LA29_0 = this.input.LA(1);
/*       */             
/*  2678 */             if (LA29_0 == 39) {
/*  2679 */               alt29 = 1;
/*       */             }
/*  2681 */             else if (LA29_0 == 54) {
/*  2682 */               alt29 = 2;
/*       */             } 
/*       */ 
/*       */             
/*  2686 */             switch (alt29) {
/*       */ 
/*       */               
/*       */               case 1:
/*  2690 */                 pushFollow(FOLLOW_transition_definition_in_component_type_definition917);
/*  2691 */                 transition_definition92 = transition_definition();
/*       */                 
/*  2693 */                 this.state._fsp--;
/*  2694 */                 if (this.state.failed) return retval; 
/*  2695 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, transition_definition92.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  2702 */                 pushFollow(FOLLOW_priority_definition_in_component_type_definition921);
/*  2703 */                 priority_definition93 = priority_definition();
/*       */                 
/*  2705 */                 this.state._fsp--;
/*  2706 */                 if (this.state.failed) return retval; 
/*  2707 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, priority_definition93.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */               
/*       */               default:
/*  2713 */                 if (cnt29 >= 1)
/*  2714 */                   break;  if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  2715 */                  eee = new EarlyExitException(29, (IntStream)this.input);
/*       */                 
/*  2717 */                 throw eee;
/*       */             } 
/*  2719 */             cnt29++;
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/*       */           while (true) {
/*  2725 */             int alt30 = 3;
/*  2726 */             int LA30_0 = this.input.LA(1);
/*       */             
/*  2728 */             if (LA30_0 == 37) {
/*  2729 */               int LA30_2 = this.input.LA(2);
/*       */               
/*  2731 */               if (LA30_2 == 30) {
/*  2732 */                 alt30 = 1;
/*       */               }
/*  2734 */               else if (LA30_2 == 48) {
/*  2735 */                 alt30 = 2;
/*       */               } 
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  2742 */             switch (alt30) {
/*       */ 
/*       */               
/*       */               case 1:
/*  2746 */                 pushFollow(FOLLOW_export_port_definition_in_component_type_definition931);
/*  2747 */                 export_port_definition94 = export_port_definition();
/*       */                 
/*  2749 */                 this.state._fsp--;
/*  2750 */                 if (this.state.failed) return retval; 
/*  2751 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, export_port_definition94.getTree());
/*       */                 
/*       */                 continue;
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  2758 */                 pushFollow(FOLLOW_export_data_definition_in_component_type_definition935);
/*  2759 */                 export_data_definition95 = export_data_definition();
/*       */                 
/*  2761 */                 this.state._fsp--;
/*  2762 */                 if (this.state.failed) return retval; 
/*  2763 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, export_data_definition95.getTree());
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*  2773 */           END96 = (Token)match((IntStream)this.input, 25, FOLLOW_END_in_component_type_definition941); if (this.state.failed) return retval;
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  2780 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  2782 */           COMPOUND97 = (Token)match((IntStream)this.input, 44, FOLLOW_COMPOUND_in_component_type_definition948); if (this.state.failed) return retval; 
/*  2783 */           if (this.state.backtracking == 0) {
/*  2784 */             COMPOUND97_tree = (CommonTree)this.adaptor.create(COMPOUND97);
/*  2785 */             root_0 = (CommonTree)this.adaptor.becomeRoot(COMPOUND97_tree, root_0);
/*       */           } 
/*  2787 */           TYPE98 = (Token)match((IntStream)this.input, 31, FOLLOW_TYPE_in_component_type_definition951); if (this.state.failed) return retval; 
/*  2788 */           if (this.state.backtracking == 0) {
/*  2789 */             TYPE98_tree = (CommonTree)this.adaptor.create(TYPE98);
/*  2790 */             this.adaptor.addChild(root_0, TYPE98_tree);
/*       */           } 
/*  2792 */           IDENTIFIER99 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_component_type_definition953); if (this.state.failed) return retval; 
/*  2793 */           if (this.state.backtracking == 0) {
/*  2794 */             IDENTIFIER99_tree = (CommonTree)this.adaptor.create(IDENTIFIER99);
/*  2795 */             this.adaptor.addChild(root_0, IDENTIFIER99_tree);
/*       */           } 
/*       */           
/*  2798 */           alt31 = 2;
/*  2799 */           LA31_0 = this.input.LA(1);
/*       */           
/*  2801 */           if (LA31_0 == 32) {
/*  2802 */             alt31 = 1;
/*       */           }
/*  2804 */           switch (alt31) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2808 */               pushFollow(FOLLOW_component_parameters_in_component_type_definition957);
/*  2809 */               component_parameters100 = component_parameters();
/*       */               
/*  2811 */               this.state._fsp--;
/*  2812 */               if (this.state.failed) return retval; 
/*  2813 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_parameters100.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2821 */           alt32 = 2;
/*  2822 */           LA32_0 = this.input.LA(1);
/*       */           
/*  2824 */           if (LA32_0 >= 45 && LA32_0 <= 46) {
/*  2825 */             alt32 = 1;
/*       */           }
/*  2827 */           switch (alt32) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2831 */               pushFollow(FOLLOW_component_activation_in_component_type_definition967);
/*  2832 */               component_activation101 = component_activation();
/*       */               
/*  2834 */               this.state._fsp--;
/*  2835 */               if (this.state.failed) return retval; 
/*  2836 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_activation101.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2844 */           alt33 = 2;
/*  2845 */           LA33_0 = this.input.LA(1);
/*       */           
/*  2847 */           if (LA33_0 == 27) {
/*  2848 */             alt33 = 1;
/*       */           }
/*  2850 */           switch (alt33) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2854 */               pushFollow(FOLLOW_header_code_in_component_type_definition976);
/*  2855 */               header_code102 = header_code();
/*       */               
/*  2857 */               this.state._fsp--;
/*  2858 */               if (this.state.failed) return retval; 
/*  2859 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, header_code102.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2867 */           alt34 = 2;
/*  2868 */           LA34_0 = this.input.LA(1);
/*       */           
/*  2870 */           if (LA34_0 == 28) {
/*  2871 */             alt34 = 1;
/*       */           }
/*  2873 */           switch (alt34) {
/*       */ 
/*       */             
/*       */             case 1:
/*  2877 */               CODE103 = (Token)match((IntStream)this.input, 28, FOLLOW_CODE_in_component_type_definition983); if (this.state.failed) return retval; 
/*  2878 */               if (this.state.backtracking == 0) {
/*  2879 */                 CODE103_tree = (CommonTree)this.adaptor.create(CODE103);
/*  2880 */                 this.adaptor.addChild(root_0, CODE103_tree);
/*       */               } 
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2889 */           cnt35 = 0;
/*       */           while (true) {
/*       */             EarlyExitException eee;
/*  2892 */             int alt35 = 4;
/*  2893 */             switch (this.input.LA(1)) {
/*       */               
/*       */               case 49:
/*  2896 */                 alt35 = 1;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 36:
/*  2901 */                 alt35 = 2;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 54:
/*  2906 */                 alt35 = 3;
/*       */                 break;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/*  2912 */             switch (alt35) {
/*       */ 
/*       */               
/*       */               case 1:
/*  2916 */                 pushFollow(FOLLOW_component_definition_in_component_type_definition991);
/*  2917 */                 component_definition104 = component_definition();
/*       */                 
/*  2919 */                 this.state._fsp--;
/*  2920 */                 if (this.state.failed) return retval; 
/*  2921 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_definition104.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  2928 */                 pushFollow(FOLLOW_connector_definition_in_component_type_definition995);
/*  2929 */                 connector_definition105 = connector_definition();
/*       */                 
/*  2931 */                 this.state._fsp--;
/*  2932 */                 if (this.state.failed) return retval; 
/*  2933 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, connector_definition105.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */ 
/*       */               
/*       */               case 3:
/*  2940 */                 pushFollow(FOLLOW_priority_definition_in_component_type_definition999);
/*  2941 */                 priority_definition106 = priority_definition();
/*       */                 
/*  2943 */                 this.state._fsp--;
/*  2944 */                 if (this.state.failed) return retval; 
/*  2945 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, priority_definition106.getTree());
/*       */                 
/*       */                 break;
/*       */ 
/*       */               
/*       */               default:
/*  2951 */                 if (cnt35 >= 1)
/*  2952 */                   break;  if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  2953 */                  eee = new EarlyExitException(35, (IntStream)this.input);
/*       */                 
/*  2955 */                 throw eee;
/*       */             } 
/*  2957 */             cnt35++;
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/*       */           while (true) {
/*  2963 */             int alt36 = 3;
/*  2964 */             int LA36_0 = this.input.LA(1);
/*       */             
/*  2966 */             if (LA36_0 == 37) {
/*  2967 */               int LA36_2 = this.input.LA(2);
/*       */               
/*  2969 */               if (LA36_2 == 30) {
/*  2970 */                 alt36 = 1;
/*       */               }
/*  2972 */               else if (LA36_2 == 48) {
/*  2973 */                 alt36 = 2;
/*       */               } 
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  2980 */             switch (alt36) {
/*       */ 
/*       */               
/*       */               case 1:
/*  2984 */                 pushFollow(FOLLOW_export_port_definition_in_component_type_definition1008);
/*  2985 */                 export_port_definition107 = export_port_definition();
/*       */                 
/*  2987 */                 this.state._fsp--;
/*  2988 */                 if (this.state.failed) return retval; 
/*  2989 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, export_port_definition107.getTree());
/*       */                 
/*       */                 continue;
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  2996 */                 pushFollow(FOLLOW_export_data_definition_in_component_type_definition1012);
/*  2997 */                 export_data_definition108 = export_data_definition();
/*       */                 
/*  2999 */                 this.state._fsp--;
/*  3000 */                 if (this.state.failed) return retval; 
/*  3001 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, export_data_definition108.getTree());
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*  3011 */           END109 = (Token)match((IntStream)this.input, 25, FOLLOW_END_in_component_type_definition1018); if (this.state.failed) return retval;
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*  3017 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3019 */       if (this.state.backtracking == 0)
/*       */       {
/*  3021 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3022 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3025 */     } catch (RecognitionException re) {
/*  3026 */       reportError(re);
/*  3027 */       recover((IntStream)this.input, re);
/*  3028 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3033 */     return retval;
/*       */   }
/*       */   
/*       */   public static class component_parameters_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3039 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final component_parameters_return component_parameters() throws RecognitionException {
/*  3045 */     component_parameters_return retval = new component_parameters_return();
/*  3046 */     retval.start = this.input.LT(1);
/*       */     
/*  3048 */     CommonTree root_0 = null;
/*       */     
/*  3050 */     Token LPAR110 = null;
/*  3051 */     Token COMMA112 = null;
/*  3052 */     Token RPAR114 = null;
/*  3053 */     data_fpar_definition_return data_fpar_definition111 = null;
/*       */     
/*  3055 */     data_fpar_definition_return data_fpar_definition113 = null;
/*       */ 
/*       */     
/*  3058 */     CommonTree LPAR110_tree = null;
/*  3059 */     CommonTree COMMA112_tree = null;
/*  3060 */     CommonTree RPAR114_tree = null;
/*  3061 */     RewriteRuleTokenStream stream_RPAR = new RewriteRuleTokenStream(this.adaptor, "token RPAR");
/*  3062 */     RewriteRuleTokenStream stream_LPAR = new RewriteRuleTokenStream(this.adaptor, "token LPAR");
/*  3063 */     RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
/*  3064 */     RewriteRuleSubtreeStream stream_data_fpar_definition = new RewriteRuleSubtreeStream(this.adaptor, "rule data_fpar_definition");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3069 */       LPAR110 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_component_parameters1035); if (this.state.failed) return retval; 
/*  3070 */       if (this.state.backtracking == 0) stream_LPAR.add(LPAR110);
/*       */       
/*  3072 */       pushFollow(FOLLOW_data_fpar_definition_in_component_parameters1037);
/*  3073 */       data_fpar_definition111 = data_fpar_definition();
/*       */       
/*  3075 */       this.state._fsp--;
/*  3076 */       if (this.state.failed) return retval; 
/*  3077 */       if (this.state.backtracking == 0) stream_data_fpar_definition.add(data_fpar_definition111.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  3081 */         int alt38 = 2;
/*  3082 */         int LA38_0 = this.input.LA(1);
/*       */         
/*  3084 */         if (LA38_0 == 33) {
/*  3085 */           alt38 = 1;
/*       */         }
/*       */ 
/*       */         
/*  3089 */         switch (alt38) {
/*       */ 
/*       */           
/*       */           case 1:
/*  3093 */             COMMA112 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_component_parameters1040); if (this.state.failed) return retval; 
/*  3094 */             if (this.state.backtracking == 0) stream_COMMA.add(COMMA112);
/*       */             
/*  3096 */             pushFollow(FOLLOW_data_fpar_definition_in_component_parameters1042);
/*  3097 */             data_fpar_definition113 = data_fpar_definition();
/*       */             
/*  3099 */             this.state._fsp--;
/*  3100 */             if (this.state.failed) return retval; 
/*  3101 */             if (this.state.backtracking == 0) stream_data_fpar_definition.add(data_fpar_definition113.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  3111 */       RPAR114 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_component_parameters1047); if (this.state.failed) return retval; 
/*  3112 */       if (this.state.backtracking == 0) stream_RPAR.add(RPAR114);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3123 */       if (this.state.backtracking == 0) {
/*  3124 */         retval.tree = root_0;
/*  3125 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  3127 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3132 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  3133 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(16, "DATA_PARAMETER"), root_1);
/*       */         
/*  3135 */         this.adaptor.addChild(root_1, stream_data_fpar_definition.nextTree());
/*       */         
/*  3137 */         while (stream_data_fpar_definition.hasNext()) {
/*  3138 */           this.adaptor.addChild(root_1, stream_data_fpar_definition.nextTree());
/*       */         }
/*       */         
/*  3141 */         stream_data_fpar_definition.reset();
/*       */         
/*  3143 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3148 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  3151 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3153 */       if (this.state.backtracking == 0)
/*       */       {
/*  3155 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3156 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3159 */     } catch (RecognitionException re) {
/*  3160 */       reportError(re);
/*  3161 */       recover((IntStream)this.input, re);
/*  3162 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3167 */     return retval;
/*       */   }
/*       */   
/*       */   public static class component_type_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3173 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final component_type_return component_type() throws RecognitionException {
/*  3179 */     component_type_return retval = new component_type_return();
/*  3180 */     retval.start = this.input.LT(1);
/*       */     
/*  3182 */     CommonTree root_0 = null;
/*       */     
/*  3184 */     Token IDENTIFIER115 = null;
/*  3185 */     Token DOT116 = null;
/*  3186 */     Token IDENTIFIER117 = null;
/*       */     
/*  3188 */     CommonTree IDENTIFIER115_tree = null;
/*  3189 */     CommonTree DOT116_tree = null;
/*  3190 */     CommonTree IDENTIFIER117_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3196 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3198 */       IDENTIFIER115 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_component_type1071); if (this.state.failed) return retval; 
/*  3199 */       if (this.state.backtracking == 0) {
/*  3200 */         IDENTIFIER115_tree = (CommonTree)this.adaptor.create(IDENTIFIER115);
/*  3201 */         this.adaptor.addChild(root_0, IDENTIFIER115_tree);
/*       */       } 
/*       */       
/*  3204 */       int alt39 = 2;
/*  3205 */       int LA39_0 = this.input.LA(1);
/*       */       
/*  3207 */       if (LA39_0 == 35) {
/*  3208 */         alt39 = 1;
/*       */       }
/*  3210 */       switch (alt39) {
/*       */ 
/*       */         
/*       */         case 1:
/*  3214 */           DOT116 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_component_type1074); if (this.state.failed) return retval; 
/*  3215 */           if (this.state.backtracking == 0) {
/*  3216 */             DOT116_tree = (CommonTree)this.adaptor.create(DOT116);
/*  3217 */             root_0 = (CommonTree)this.adaptor.becomeRoot(DOT116_tree, root_0);
/*       */           } 
/*  3219 */           IDENTIFIER117 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_component_type1077); if (this.state.failed) return retval; 
/*  3220 */           if (this.state.backtracking == 0) {
/*  3221 */             IDENTIFIER117_tree = (CommonTree)this.adaptor.create(IDENTIFIER117);
/*  3222 */             this.adaptor.addChild(root_0, IDENTIFIER117_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3233 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3235 */       if (this.state.backtracking == 0)
/*       */       {
/*  3237 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3238 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3241 */     } catch (RecognitionException re) {
/*  3242 */       reportError(re);
/*  3243 */       recover((IntStream)this.input, re);
/*  3244 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3249 */     return retval;
/*       */   }
/*       */   
/*       */   public static class component_activation_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3255 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final component_activation_return component_activation() throws RecognitionException {
/*  3261 */     component_activation_return retval = new component_activation_return();
/*  3262 */     retval.start = this.input.LT(1);
/*       */     
/*  3264 */     CommonTree root_0 = null;
/*       */     
/*  3266 */     Token set118 = null;
/*       */     
/*  3268 */     CommonTree set118_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3274 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3276 */       set118 = this.input.LT(1);
/*  3277 */       if (this.input.LA(1) >= 45 && this.input.LA(1) <= 46) {
/*  3278 */         this.input.consume();
/*  3279 */         if (this.state.backtracking == 0) this.adaptor.addChild(root_0, this.adaptor.create(set118)); 
/*  3280 */         this.state.errorRecovery = false; this.state.failed = false;
/*       */       } else {
/*       */         
/*  3283 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  3284 */          MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  3285 */         throw mse;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3291 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3293 */       if (this.state.backtracking == 0)
/*       */       {
/*  3295 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3296 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3299 */     } catch (RecognitionException re) {
/*  3300 */       reportError(re);
/*  3301 */       recover((IntStream)this.input, re);
/*  3302 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3307 */     return retval;
/*       */   }
/*       */   
/*       */   public static class export_port_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3313 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final export_port_definition_return export_port_definition() throws RecognitionException {
/*  3319 */     export_port_definition_return retval = new export_port_definition_return();
/*  3320 */     retval.start = this.input.LT(1);
/*       */     
/*  3322 */     CommonTree root_0 = null;
/*       */     
/*  3324 */     Token EXPORT119 = null;
/*  3325 */     Token PORT120 = null;
/*  3326 */     Token IDENTIFIER122 = null;
/*  3327 */     port_type_return port_type121 = null;
/*       */     
/*  3329 */     export_list_return export_list123 = null;
/*       */ 
/*       */     
/*  3332 */     CommonTree EXPORT119_tree = null;
/*  3333 */     CommonTree PORT120_tree = null;
/*  3334 */     CommonTree IDENTIFIER122_tree = null;
/*  3335 */     RewriteRuleTokenStream stream_PORT = new RewriteRuleTokenStream(this.adaptor, "token PORT");
/*  3336 */     RewriteRuleTokenStream stream_EXPORT = new RewriteRuleTokenStream(this.adaptor, "token EXPORT");
/*  3337 */     RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFIER");
/*  3338 */     RewriteRuleSubtreeStream stream_port_type = new RewriteRuleSubtreeStream(this.adaptor, "rule port_type");
/*  3339 */     RewriteRuleSubtreeStream stream_export_list = new RewriteRuleSubtreeStream(this.adaptor, "rule export_list");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3344 */       EXPORT119 = (Token)match((IntStream)this.input, 37, FOLLOW_EXPORT_in_export_port_definition1112); if (this.state.failed) return retval; 
/*  3345 */       if (this.state.backtracking == 0) stream_EXPORT.add(EXPORT119);
/*       */       
/*  3347 */       PORT120 = (Token)match((IntStream)this.input, 30, FOLLOW_PORT_in_export_port_definition1114); if (this.state.failed) return retval; 
/*  3348 */       if (this.state.backtracking == 0) stream_PORT.add(PORT120);
/*       */       
/*  3350 */       pushFollow(FOLLOW_port_type_in_export_port_definition1116);
/*  3351 */       port_type121 = port_type();
/*       */       
/*  3353 */       this.state._fsp--;
/*  3354 */       if (this.state.failed) return retval; 
/*  3355 */       if (this.state.backtracking == 0) stream_port_type.add(port_type121.getTree()); 
/*  3356 */       IDENTIFIER122 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_export_port_definition1118); if (this.state.failed) return retval; 
/*  3357 */       if (this.state.backtracking == 0) stream_IDENTIFIER.add(IDENTIFIER122);
/*       */       
/*  3359 */       pushFollow(FOLLOW_export_list_in_export_port_definition1121);
/*  3360 */       export_list123 = export_list();
/*       */       
/*  3362 */       this.state._fsp--;
/*  3363 */       if (this.state.failed) return retval; 
/*  3364 */       if (this.state.backtracking == 0) stream_export_list.add(export_list123.getTree());
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3374 */       if (this.state.backtracking == 0) {
/*  3375 */         retval.tree = root_0;
/*  3376 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  3378 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3383 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  3384 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(21, "EXPORT_PORT"), root_1);
/*       */         
/*  3386 */         this.adaptor.addChild(root_1, stream_port_type.nextTree());
/*  3387 */         this.adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());
/*  3388 */         this.adaptor.addChild(root_1, stream_export_list.nextTree());
/*       */         
/*  3390 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3395 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  3398 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3400 */       if (this.state.backtracking == 0)
/*       */       {
/*  3402 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3403 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3406 */     } catch (RecognitionException re) {
/*  3407 */       reportError(re);
/*  3408 */       recover((IntStream)this.input, re);
/*  3409 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3414 */     return retval;
/*       */   }
/*       */   
/*       */   public static class export_list_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3420 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final export_list_return export_list() throws RecognitionException {
/*  3426 */     export_list_return retval = new export_list_return();
/*  3427 */     retval.start = this.input.LT(1);
/*       */     
/*  3429 */     CommonTree root_0 = null;
/*       */     
/*  3431 */     Token IS124 = null;
/*  3432 */     Token COMMA126 = null;
/*  3433 */     port_reference_return port_reference125 = null;
/*       */     
/*  3435 */     port_reference_return port_reference127 = null;
/*       */ 
/*       */     
/*  3438 */     CommonTree IS124_tree = null;
/*  3439 */     CommonTree COMMA126_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3445 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3447 */       IS124 = (Token)match((IntStream)this.input, 47, FOLLOW_IS_in_export_list1147); if (this.state.failed) return retval; 
/*  3448 */       if (this.state.backtracking == 0) {
/*  3449 */         IS124_tree = (CommonTree)this.adaptor.create(IS124);
/*  3450 */         root_0 = (CommonTree)this.adaptor.becomeRoot(IS124_tree, root_0);
/*       */       } 
/*  3452 */       pushFollow(FOLLOW_port_reference_in_export_list1150);
/*  3453 */       port_reference125 = port_reference();
/*       */       
/*  3455 */       this.state._fsp--;
/*  3456 */       if (this.state.failed) return retval; 
/*  3457 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference125.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  3461 */         int alt40 = 2;
/*  3462 */         int LA40_0 = this.input.LA(1);
/*       */         
/*  3464 */         if (LA40_0 == 33) {
/*  3465 */           alt40 = 1;
/*       */         }
/*       */ 
/*       */         
/*  3469 */         switch (alt40) {
/*       */ 
/*       */           
/*       */           case 1:
/*  3473 */             COMMA126 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_export_list1153); if (this.state.failed) return retval; 
/*  3474 */             pushFollow(FOLLOW_port_reference_in_export_list1156);
/*  3475 */             port_reference127 = port_reference();
/*       */             
/*  3477 */             this.state._fsp--;
/*  3478 */             if (this.state.failed) return retval; 
/*  3479 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference127.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  3492 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3494 */       if (this.state.backtracking == 0)
/*       */       {
/*  3496 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3497 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3500 */     } catch (RecognitionException re) {
/*  3501 */       reportError(re);
/*  3502 */       recover((IntStream)this.input, re);
/*  3503 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3508 */     return retval;
/*       */   }
/*       */   
/*       */   public static class export_data_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3514 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final export_data_definition_return export_data_definition() throws RecognitionException {
/*  3520 */     export_data_definition_return retval = new export_data_definition_return();
/*  3521 */     retval.start = this.input.LT(1);
/*       */     
/*  3523 */     CommonTree root_0 = null;
/*       */     
/*  3525 */     Token EXPORT128 = null;
/*  3526 */     Token DATA129 = null;
/*  3527 */     Token IDENTIFIER131 = null;
/*  3528 */     data_type_return data_type130 = null;
/*       */     
/*  3530 */     data_reference_return data_reference132 = null;
/*       */ 
/*       */     
/*  3533 */     CommonTree EXPORT128_tree = null;
/*  3534 */     CommonTree DATA129_tree = null;
/*  3535 */     CommonTree IDENTIFIER131_tree = null;
/*  3536 */     RewriteRuleTokenStream stream_EXPORT = new RewriteRuleTokenStream(this.adaptor, "token EXPORT");
/*  3537 */     RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFIER");
/*  3538 */     RewriteRuleTokenStream stream_DATA = new RewriteRuleTokenStream(this.adaptor, "token DATA");
/*  3539 */     RewriteRuleSubtreeStream stream_data_type = new RewriteRuleSubtreeStream(this.adaptor, "rule data_type");
/*  3540 */     RewriteRuleSubtreeStream stream_data_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule data_reference");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3545 */       EXPORT128 = (Token)match((IntStream)this.input, 37, FOLLOW_EXPORT_in_export_data_definition1171); if (this.state.failed) return retval; 
/*  3546 */       if (this.state.backtracking == 0) stream_EXPORT.add(EXPORT128);
/*       */       
/*  3548 */       DATA129 = (Token)match((IntStream)this.input, 48, FOLLOW_DATA_in_export_data_definition1173); if (this.state.failed) return retval; 
/*  3549 */       if (this.state.backtracking == 0) stream_DATA.add(DATA129);
/*       */       
/*  3551 */       pushFollow(FOLLOW_data_type_in_export_data_definition1175);
/*  3552 */       data_type130 = data_type();
/*       */       
/*  3554 */       this.state._fsp--;
/*  3555 */       if (this.state.failed) return retval; 
/*  3556 */       if (this.state.backtracking == 0) stream_data_type.add(data_type130.getTree()); 
/*  3557 */       IDENTIFIER131 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_export_data_definition1177); if (this.state.failed) return retval; 
/*  3558 */       if (this.state.backtracking == 0) stream_IDENTIFIER.add(IDENTIFIER131);
/*       */       
/*  3560 */       pushFollow(FOLLOW_data_reference_in_export_data_definition1179);
/*  3561 */       data_reference132 = data_reference();
/*       */       
/*  3563 */       this.state._fsp--;
/*  3564 */       if (this.state.failed) return retval; 
/*  3565 */       if (this.state.backtracking == 0) stream_data_reference.add(data_reference132.getTree());
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3575 */       if (this.state.backtracking == 0) {
/*  3576 */         retval.tree = root_0;
/*  3577 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  3579 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3584 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  3585 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(22, "EXPORT_DATA"), root_1);
/*       */         
/*  3587 */         this.adaptor.addChild(root_1, stream_data_type.nextTree());
/*  3588 */         this.adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());
/*  3589 */         this.adaptor.addChild(root_1, stream_data_reference.nextTree());
/*       */         
/*  3591 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3596 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  3599 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3601 */       if (this.state.backtracking == 0)
/*       */       {
/*  3603 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3604 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3607 */     } catch (RecognitionException re) {
/*  3608 */       reportError(re);
/*  3609 */       recover((IntStream)this.input, re);
/*  3610 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3615 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_reference_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3621 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_reference_return data_reference() throws RecognitionException {
/*  3627 */     data_reference_return retval = new data_reference_return();
/*  3628 */     retval.start = this.input.LT(1);
/*       */     
/*  3630 */     CommonTree root_0 = null;
/*       */     
/*  3632 */     Token IS133 = null;
/*  3633 */     Token IDENTIFIER134 = null;
/*  3634 */     index_return index135 = null;
/*       */     
/*  3636 */     data_ref_extension_return data_ref_extension136 = null;
/*       */ 
/*       */     
/*  3639 */     CommonTree IS133_tree = null;
/*  3640 */     CommonTree IDENTIFIER134_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3646 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3648 */       IS133 = (Token)match((IntStream)this.input, 47, FOLLOW_IS_in_data_reference1204); if (this.state.failed) return retval; 
/*  3649 */       IDENTIFIER134 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_data_reference1207); if (this.state.failed) return retval; 
/*  3650 */       if (this.state.backtracking == 0) {
/*  3651 */         IDENTIFIER134_tree = (CommonTree)this.adaptor.create(IDENTIFIER134);
/*  3652 */         root_0 = (CommonTree)this.adaptor.becomeRoot(IDENTIFIER134_tree, root_0);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  3657 */         int alt41 = 2;
/*  3658 */         int LA41_0 = this.input.LA(1);
/*       */         
/*  3660 */         if (LA41_0 == 52) {
/*  3661 */           alt41 = 1;
/*       */         }
/*       */ 
/*       */         
/*  3665 */         switch (alt41) {
/*       */ 
/*       */           
/*       */           case 1:
/*  3669 */             pushFollow(FOLLOW_index_in_data_reference1211);
/*  3670 */             index135 = index();
/*       */             
/*  3672 */             this.state._fsp--;
/*  3673 */             if (this.state.failed) return retval; 
/*  3674 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, index135.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  3685 */       int alt42 = 2;
/*  3686 */       int LA42_0 = this.input.LA(1);
/*       */       
/*  3688 */       if (LA42_0 == 35) {
/*  3689 */         alt42 = 1;
/*       */       }
/*  3691 */       switch (alt42) {
/*       */ 
/*       */         
/*       */         case 1:
/*  3695 */           pushFollow(FOLLOW_data_ref_extension_in_data_reference1216);
/*  3696 */           data_ref_extension136 = data_ref_extension();
/*       */           
/*  3698 */           this.state._fsp--;
/*  3699 */           if (this.state.failed) return retval; 
/*  3700 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, data_ref_extension136.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3710 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3712 */       if (this.state.backtracking == 0)
/*       */       {
/*  3714 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3715 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3718 */     } catch (RecognitionException re) {
/*  3719 */       reportError(re);
/*  3720 */       recover((IntStream)this.input, re);
/*  3721 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3726 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_ref_extension_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3732 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_ref_extension_return data_ref_extension() throws RecognitionException {
/*  3738 */     data_ref_extension_return retval = new data_ref_extension_return();
/*  3739 */     retval.start = this.input.LT(1);
/*       */     
/*  3741 */     CommonTree root_0 = null;
/*       */     
/*  3743 */     Token DOT137 = null;
/*  3744 */     Token IDENTIFIER138 = null;
/*       */     
/*  3746 */     CommonTree DOT137_tree = null;
/*  3747 */     CommonTree IDENTIFIER138_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3753 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3755 */       DOT137 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_data_ref_extension1230); if (this.state.failed) return retval; 
/*  3756 */       if (this.state.backtracking == 0) {
/*  3757 */         DOT137_tree = (CommonTree)this.adaptor.create(DOT137);
/*  3758 */         root_0 = (CommonTree)this.adaptor.becomeRoot(DOT137_tree, root_0);
/*       */       } 
/*  3760 */       IDENTIFIER138 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_data_ref_extension1233); if (this.state.failed) return retval; 
/*  3761 */       if (this.state.backtracking == 0) {
/*  3762 */         IDENTIFIER138_tree = (CommonTree)this.adaptor.create(IDENTIFIER138);
/*  3763 */         this.adaptor.addChild(root_0, IDENTIFIER138_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  3768 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3770 */       if (this.state.backtracking == 0)
/*       */       {
/*  3772 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3773 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3776 */     } catch (RecognitionException re) {
/*  3777 */       reportError(re);
/*  3778 */       recover((IntStream)this.input, re);
/*  3779 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3784 */     return retval;
/*       */   }
/*       */   
/*       */   public static class component_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3790 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final component_definition_return component_definition() throws RecognitionException {
/*  3796 */     component_definition_return retval = new component_definition_return();
/*  3797 */     retval.start = this.input.LT(1);
/*       */     
/*  3799 */     CommonTree root_0 = null;
/*       */     
/*  3801 */     Token COMPONENT139 = null;
/*  3802 */     Token IDENTIFIER141 = null;
/*  3803 */     component_type_return component_type140 = null;
/*       */     
/*  3805 */     index_return index142 = null;
/*       */     
/*  3807 */     parameters_return parameters143 = null;
/*       */ 
/*       */     
/*  3810 */     CommonTree COMPONENT139_tree = null;
/*  3811 */     CommonTree IDENTIFIER141_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3817 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3819 */       COMPONENT139 = (Token)match((IntStream)this.input, 49, FOLLOW_COMPONENT_in_component_definition1253); if (this.state.failed) return retval; 
/*  3820 */       if (this.state.backtracking == 0) {
/*  3821 */         COMPONENT139_tree = (CommonTree)this.adaptor.create(COMPONENT139);
/*  3822 */         root_0 = (CommonTree)this.adaptor.becomeRoot(COMPONENT139_tree, root_0);
/*       */       } 
/*  3824 */       pushFollow(FOLLOW_component_type_in_component_definition1256);
/*  3825 */       component_type140 = component_type();
/*       */       
/*  3827 */       this.state._fsp--;
/*  3828 */       if (this.state.failed) return retval; 
/*  3829 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, component_type140.getTree()); 
/*  3830 */       IDENTIFIER141 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_component_definition1258); if (this.state.failed) return retval; 
/*  3831 */       if (this.state.backtracking == 0) {
/*  3832 */         IDENTIFIER141_tree = (CommonTree)this.adaptor.create(IDENTIFIER141);
/*  3833 */         this.adaptor.addChild(root_0, IDENTIFIER141_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  3838 */         int alt43 = 2;
/*  3839 */         int LA43_0 = this.input.LA(1);
/*       */         
/*  3841 */         if (LA43_0 == 52) {
/*  3842 */           alt43 = 1;
/*       */         }
/*       */ 
/*       */         
/*  3846 */         switch (alt43) {
/*       */ 
/*       */           
/*       */           case 1:
/*  3850 */             pushFollow(FOLLOW_index_in_component_definition1262);
/*  3851 */             index142 = index();
/*       */             
/*  3853 */             this.state._fsp--;
/*  3854 */             if (this.state.failed) return retval; 
/*  3855 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, index142.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  3866 */       int alt44 = 2;
/*  3867 */       int LA44_0 = this.input.LA(1);
/*       */       
/*  3869 */       if (LA44_0 == 32) {
/*  3870 */         alt44 = 1;
/*       */       }
/*  3872 */       switch (alt44) {
/*       */ 
/*       */         
/*       */         case 1:
/*  3876 */           pushFollow(FOLLOW_parameters_in_component_definition1277);
/*  3877 */           parameters143 = parameters();
/*       */           
/*  3879 */           this.state._fsp--;
/*  3880 */           if (this.state.failed) return retval; 
/*  3881 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, parameters143.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3891 */       retval.stop = this.input.LT(-1);
/*       */       
/*  3893 */       if (this.state.backtracking == 0)
/*       */       {
/*  3895 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  3896 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  3899 */     } catch (RecognitionException re) {
/*  3900 */       reportError(re);
/*  3901 */       recover((IntStream)this.input, re);
/*  3902 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  3907 */     return retval;
/*       */   }
/*       */   
/*       */   public static class connector_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  3913 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final connector_definition_return connector_definition() throws RecognitionException {
/*  3919 */     connector_definition_return retval = new connector_definition_return();
/*  3920 */     retval.start = this.input.LT(1);
/*       */     
/*  3922 */     CommonTree root_0 = null;
/*       */     
/*  3924 */     Token CONNECTOR144 = null;
/*  3925 */     Token IDENTIFIER146 = null;
/*  3926 */     connector_type_return connector_type145 = null;
/*       */     
/*  3928 */     index_return index147 = null;
/*       */     
/*  3930 */     connector_port_ref_return connector_port_ref148 = null;
/*       */     
/*  3932 */     parameters_return parameters149 = null;
/*       */ 
/*       */     
/*  3935 */     CommonTree CONNECTOR144_tree = null;
/*  3936 */     CommonTree IDENTIFIER146_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  3942 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  3944 */       CONNECTOR144 = (Token)match((IntStream)this.input, 36, FOLLOW_CONNECTOR_in_connector_definition1293); if (this.state.failed) return retval; 
/*  3945 */       if (this.state.backtracking == 0) {
/*  3946 */         CONNECTOR144_tree = (CommonTree)this.adaptor.create(CONNECTOR144);
/*  3947 */         root_0 = (CommonTree)this.adaptor.becomeRoot(CONNECTOR144_tree, root_0);
/*       */       } 
/*  3949 */       pushFollow(FOLLOW_connector_type_in_connector_definition1296);
/*  3950 */       connector_type145 = connector_type();
/*       */       
/*  3952 */       this.state._fsp--;
/*  3953 */       if (this.state.failed) return retval; 
/*  3954 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, connector_type145.getTree()); 
/*  3955 */       IDENTIFIER146 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_connector_definition1298); if (this.state.failed) return retval; 
/*  3956 */       if (this.state.backtracking == 0) {
/*  3957 */         IDENTIFIER146_tree = (CommonTree)this.adaptor.create(IDENTIFIER146);
/*  3958 */         this.adaptor.addChild(root_0, IDENTIFIER146_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  3963 */         int alt45 = 2;
/*  3964 */         int LA45_0 = this.input.LA(1);
/*       */         
/*  3966 */         if (LA45_0 == 52) {
/*  3967 */           alt45 = 1;
/*       */         }
/*       */ 
/*       */         
/*  3971 */         switch (alt45) {
/*       */ 
/*       */           
/*       */           case 1:
/*  3975 */             pushFollow(FOLLOW_index_in_connector_definition1302);
/*  3976 */             index147 = index();
/*       */             
/*  3978 */             this.state._fsp--;
/*  3979 */             if (this.state.failed) return retval; 
/*  3980 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, index147.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  3990 */       pushFollow(FOLLOW_connector_port_ref_in_connector_definition1311);
/*  3991 */       connector_port_ref148 = connector_port_ref();
/*       */       
/*  3993 */       this.state._fsp--;
/*  3994 */       if (this.state.failed) return retval; 
/*  3995 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, connector_port_ref148.getTree());
/*       */       
/*  3997 */       int alt46 = 2;
/*  3998 */       int LA46_0 = this.input.LA(1);
/*       */       
/*  4000 */       if (LA46_0 == 32) {
/*  4001 */         alt46 = 1;
/*       */       }
/*  4003 */       switch (alt46) {
/*       */ 
/*       */         
/*       */         case 1:
/*  4007 */           pushFollow(FOLLOW_parameters_in_connector_definition1323);
/*  4008 */           parameters149 = parameters();
/*       */           
/*  4010 */           this.state._fsp--;
/*  4011 */           if (this.state.failed) return retval; 
/*  4012 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, parameters149.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4022 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4024 */       if (this.state.backtracking == 0)
/*       */       {
/*  4026 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4027 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4030 */     } catch (RecognitionException re) {
/*  4031 */       reportError(re);
/*  4032 */       recover((IntStream)this.input, re);
/*  4033 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4038 */     return retval;
/*       */   }
/*       */   
/*       */   public static class connector_port_ref_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4044 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final connector_port_ref_return connector_port_ref() throws RecognitionException {
/*  4050 */     connector_port_ref_return retval = new connector_port_ref_return();
/*  4051 */     retval.start = this.input.LT(1);
/*       */     
/*  4053 */     CommonTree root_0 = null;
/*       */     
/*  4055 */     Token LPAR150 = null;
/*  4056 */     Token COMMA152 = null;
/*  4057 */     Token RPAR154 = null;
/*  4058 */     port_reference_return port_reference151 = null;
/*       */     
/*  4060 */     port_reference_return port_reference153 = null;
/*       */ 
/*       */     
/*  4063 */     CommonTree LPAR150_tree = null;
/*  4064 */     CommonTree COMMA152_tree = null;
/*  4065 */     CommonTree RPAR154_tree = null;
/*  4066 */     RewriteRuleTokenStream stream_RPAR = new RewriteRuleTokenStream(this.adaptor, "token RPAR");
/*  4067 */     RewriteRuleTokenStream stream_LPAR = new RewriteRuleTokenStream(this.adaptor, "token LPAR");
/*  4068 */     RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
/*  4069 */     RewriteRuleSubtreeStream stream_port_reference = new RewriteRuleSubtreeStream(this.adaptor, "rule port_reference");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4074 */       LPAR150 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_connector_port_ref1340); if (this.state.failed) return retval; 
/*  4075 */       if (this.state.backtracking == 0) stream_LPAR.add(LPAR150);
/*       */       
/*  4077 */       pushFollow(FOLLOW_port_reference_in_connector_port_ref1342);
/*  4078 */       port_reference151 = port_reference();
/*       */       
/*  4080 */       this.state._fsp--;
/*  4081 */       if (this.state.failed) return retval; 
/*  4082 */       if (this.state.backtracking == 0) stream_port_reference.add(port_reference151.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  4086 */         int alt47 = 2;
/*  4087 */         int LA47_0 = this.input.LA(1);
/*       */         
/*  4089 */         if (LA47_0 == 33) {
/*  4090 */           alt47 = 1;
/*       */         }
/*       */ 
/*       */         
/*  4094 */         switch (alt47) {
/*       */ 
/*       */           
/*       */           case 1:
/*  4098 */             COMMA152 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_connector_port_ref1345); if (this.state.failed) return retval; 
/*  4099 */             if (this.state.backtracking == 0) stream_COMMA.add(COMMA152);
/*       */             
/*  4101 */             pushFollow(FOLLOW_port_reference_in_connector_port_ref1347);
/*  4102 */             port_reference153 = port_reference();
/*       */             
/*  4104 */             this.state._fsp--;
/*  4105 */             if (this.state.failed) return retval; 
/*  4106 */             if (this.state.backtracking == 0) stream_port_reference.add(port_reference153.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  4116 */       RPAR154 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_connector_port_ref1351); if (this.state.failed) return retval; 
/*  4117 */       if (this.state.backtracking == 0) stream_RPAR.add(RPAR154);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4128 */       if (this.state.backtracking == 0) {
/*  4129 */         retval.tree = root_0;
/*  4130 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  4132 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4137 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  4138 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(17, "ACTUAL_PORT_PARAMETER"), root_1);
/*       */         
/*  4140 */         this.adaptor.addChild(root_1, stream_port_reference.nextTree());
/*       */         
/*  4142 */         while (stream_port_reference.hasNext()) {
/*  4143 */           this.adaptor.addChild(root_1, stream_port_reference.nextTree());
/*       */         }
/*       */         
/*  4146 */         stream_port_reference.reset();
/*       */         
/*  4148 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4153 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  4156 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4158 */       if (this.state.backtracking == 0)
/*       */       {
/*  4160 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4161 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4164 */     } catch (RecognitionException re) {
/*  4165 */       reportError(re);
/*  4166 */       recover((IntStream)this.input, re);
/*  4167 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4172 */     return retval;
/*       */   }
/*       */   
/*       */   public static class parameters_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4178 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final parameters_return parameters() throws RecognitionException {
/*  4184 */     parameters_return retval = new parameters_return();
/*  4185 */     retval.start = this.input.LT(1);
/*       */     
/*  4187 */     CommonTree root_0 = null;
/*       */     
/*  4189 */     Token LPAR155 = null;
/*  4190 */     Token COMMA157 = null;
/*  4191 */     Token RPAR159 = null;
/*  4192 */     expression_return expression156 = null;
/*       */     
/*  4194 */     expression_return expression158 = null;
/*       */ 
/*       */     
/*  4197 */     CommonTree LPAR155_tree = null;
/*  4198 */     CommonTree COMMA157_tree = null;
/*  4199 */     CommonTree RPAR159_tree = null;
/*  4200 */     RewriteRuleTokenStream stream_RPAR = new RewriteRuleTokenStream(this.adaptor, "token RPAR");
/*  4201 */     RewriteRuleTokenStream stream_LPAR = new RewriteRuleTokenStream(this.adaptor, "token LPAR");
/*  4202 */     RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
/*  4203 */     RewriteRuleSubtreeStream stream_expression = new RewriteRuleSubtreeStream(this.adaptor, "rule expression");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4208 */       LPAR155 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_parameters1393); if (this.state.failed) return retval; 
/*  4209 */       if (this.state.backtracking == 0) stream_LPAR.add(LPAR155);
/*       */       
/*  4211 */       pushFollow(FOLLOW_expression_in_parameters1395);
/*  4212 */       expression156 = expression();
/*       */       
/*  4214 */       this.state._fsp--;
/*  4215 */       if (this.state.failed) return retval; 
/*  4216 */       if (this.state.backtracking == 0) stream_expression.add(expression156.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  4220 */         int alt48 = 2;
/*  4221 */         int LA48_0 = this.input.LA(1);
/*       */         
/*  4223 */         if (LA48_0 == 33) {
/*  4224 */           alt48 = 1;
/*       */         }
/*       */ 
/*       */         
/*  4228 */         switch (alt48) {
/*       */ 
/*       */           
/*       */           case 1:
/*  4232 */             COMMA157 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_parameters1398); if (this.state.failed) return retval; 
/*  4233 */             if (this.state.backtracking == 0) stream_COMMA.add(COMMA157);
/*       */             
/*  4235 */             pushFollow(FOLLOW_expression_in_parameters1400);
/*  4236 */             expression158 = expression();
/*       */             
/*  4238 */             this.state._fsp--;
/*  4239 */             if (this.state.failed) return retval; 
/*  4240 */             if (this.state.backtracking == 0) stream_expression.add(expression158.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  4250 */       RPAR159 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_parameters1404); if (this.state.failed) return retval; 
/*  4251 */       if (this.state.backtracking == 0) stream_RPAR.add(RPAR159);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4262 */       if (this.state.backtracking == 0) {
/*  4263 */         retval.tree = root_0;
/*  4264 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  4266 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4271 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  4272 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(18, "ACTUAL_DATA_PARAMETER"), root_1);
/*       */         
/*  4274 */         this.adaptor.addChild(root_1, stream_expression.nextTree());
/*       */         
/*  4276 */         while (stream_expression.hasNext()) {
/*  4277 */           this.adaptor.addChild(root_1, stream_expression.nextTree());
/*       */         }
/*       */         
/*  4280 */         stream_expression.reset();
/*       */         
/*  4282 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4287 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  4290 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4292 */       if (this.state.backtracking == 0)
/*       */       {
/*  4294 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4295 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4298 */     } catch (RecognitionException re) {
/*  4299 */       reportError(re);
/*  4300 */       recover((IntStream)this.input, re);
/*  4301 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4306 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_reference_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4312 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_reference_return port_reference() throws RecognitionException {
/*  4318 */     port_reference_return retval = new port_reference_return();
/*  4319 */     retval.start = this.input.LT(1);
/*       */     
/*  4321 */     CommonTree root_0 = null;
/*       */     
/*  4323 */     simple_port_reference_return simple_port_reference160 = null;
/*       */     
/*  4325 */     conditional_port_ref_return conditional_port_ref161 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4331 */       int alt49 = 2;
/*  4332 */       int LA49_0 = this.input.LA(1);
/*       */       
/*  4334 */       if (LA49_0 == 24) {
/*  4335 */         alt49 = 1;
/*       */       }
/*  4337 */       else if (LA49_0 == 32) {
/*  4338 */         alt49 = 2;
/*       */       } else {
/*       */         
/*  4341 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  4342 */          NoViableAltException nvae = new NoViableAltException("", 49, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  4345 */         throw nvae;
/*       */       } 
/*  4347 */       switch (alt49) {
/*       */ 
/*       */         
/*       */         case 1:
/*  4351 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  4353 */           pushFollow(FOLLOW_simple_port_reference_in_port_reference1431);
/*  4354 */           simple_port_reference160 = simple_port_reference();
/*       */           
/*  4356 */           this.state._fsp--;
/*  4357 */           if (this.state.failed) return retval; 
/*  4358 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, simple_port_reference160.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  4365 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  4367 */           pushFollow(FOLLOW_conditional_port_ref_in_port_reference1435);
/*  4368 */           conditional_port_ref161 = conditional_port_ref();
/*       */           
/*  4370 */           this.state._fsp--;
/*  4371 */           if (this.state.failed) return retval; 
/*  4372 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, conditional_port_ref161.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*  4378 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4380 */       if (this.state.backtracking == 0)
/*       */       {
/*  4382 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4383 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4386 */     } catch (RecognitionException re) {
/*  4387 */       reportError(re);
/*  4388 */       recover((IntStream)this.input, re);
/*  4389 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4394 */     return retval;
/*       */   }
/*       */   
/*       */   public static class simple_port_reference_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4400 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final simple_port_reference_return simple_port_reference() throws RecognitionException {
/*  4406 */     simple_port_reference_return retval = new simple_port_reference_return();
/*  4407 */     retval.start = this.input.LT(1);
/*       */     
/*  4409 */     CommonTree root_0 = null;
/*       */     
/*  4411 */     Token IDENTIFIER162 = null;
/*  4412 */     index_return index163 = null;
/*       */     
/*  4414 */     port_ref_extension_return port_ref_extension164 = null;
/*       */ 
/*       */     
/*  4417 */     CommonTree IDENTIFIER162_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4423 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  4425 */       IDENTIFIER162 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_simple_port_reference1445); if (this.state.failed) return retval; 
/*  4426 */       if (this.state.backtracking == 0) {
/*  4427 */         IDENTIFIER162_tree = (CommonTree)this.adaptor.create(IDENTIFIER162);
/*  4428 */         root_0 = (CommonTree)this.adaptor.becomeRoot(IDENTIFIER162_tree, root_0);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  4433 */         int alt50 = 2;
/*  4434 */         int LA50_0 = this.input.LA(1);
/*       */         
/*  4436 */         if (LA50_0 == 52) {
/*  4437 */           alt50 = 1;
/*       */         }
/*       */ 
/*       */         
/*  4441 */         switch (alt50) {
/*       */ 
/*       */           
/*       */           case 1:
/*  4445 */             pushFollow(FOLLOW_index_in_simple_port_reference1449);
/*  4446 */             index163 = index();
/*       */             
/*  4448 */             this.state._fsp--;
/*  4449 */             if (this.state.failed) return retval; 
/*  4450 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, index163.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  4461 */       int alt51 = 2;
/*  4462 */       int LA51_0 = this.input.LA(1);
/*       */       
/*  4464 */       if (LA51_0 == 35) {
/*  4465 */         alt51 = 1;
/*       */       }
/*  4467 */       switch (alt51) {
/*       */ 
/*       */         
/*       */         case 1:
/*  4471 */           pushFollow(FOLLOW_port_ref_extension_in_simple_port_reference1454);
/*  4472 */           port_ref_extension164 = port_ref_extension();
/*       */           
/*  4474 */           this.state._fsp--;
/*  4475 */           if (this.state.failed) return retval; 
/*  4476 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_ref_extension164.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4486 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4488 */       if (this.state.backtracking == 0)
/*       */       {
/*  4490 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4491 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4494 */     } catch (RecognitionException re) {
/*  4495 */       reportError(re);
/*  4496 */       recover((IntStream)this.input, re);
/*  4497 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4502 */     return retval;
/*       */   }
/*       */   
/*       */   public static class conditional_port_ref_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4508 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final conditional_port_ref_return conditional_port_ref() throws RecognitionException {
/*  4514 */     conditional_port_ref_return retval = new conditional_port_ref_return();
/*  4515 */     retval.start = this.input.LT(1);
/*       */     
/*  4517 */     CommonTree root_0 = null;
/*       */     
/*  4519 */     Token LPAR165 = null;
/*  4520 */     Token QMARK167 = null;
/*  4521 */     Token COLON169 = null;
/*  4522 */     Token RPAR171 = null;
/*  4523 */     expression_return expression166 = null;
/*       */     
/*  4525 */     port_reference_return port_reference168 = null;
/*       */     
/*  4527 */     port_reference_return port_reference170 = null;
/*       */ 
/*       */     
/*  4530 */     CommonTree LPAR165_tree = null;
/*  4531 */     CommonTree QMARK167_tree = null;
/*  4532 */     CommonTree COLON169_tree = null;
/*  4533 */     CommonTree RPAR171_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4539 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  4541 */       LPAR165 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_conditional_port_ref1469); if (this.state.failed) return retval; 
/*  4542 */       pushFollow(FOLLOW_expression_in_conditional_port_ref1472);
/*  4543 */       expression166 = expression();
/*       */       
/*  4545 */       this.state._fsp--;
/*  4546 */       if (this.state.failed) return retval; 
/*  4547 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression166.getTree()); 
/*  4548 */       QMARK167 = (Token)match((IntStream)this.input, 50, FOLLOW_QMARK_in_conditional_port_ref1474); if (this.state.failed) return retval; 
/*  4549 */       if (this.state.backtracking == 0) {
/*  4550 */         QMARK167_tree = (CommonTree)this.adaptor.create(QMARK167);
/*  4551 */         root_0 = (CommonTree)this.adaptor.becomeRoot(QMARK167_tree, root_0);
/*       */       } 
/*  4553 */       pushFollow(FOLLOW_port_reference_in_conditional_port_ref1477);
/*  4554 */       port_reference168 = port_reference();
/*       */       
/*  4556 */       this.state._fsp--;
/*  4557 */       if (this.state.failed) return retval; 
/*  4558 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference168.getTree()); 
/*  4559 */       COLON169 = (Token)match((IntStream)this.input, 51, FOLLOW_COLON_in_conditional_port_ref1479); if (this.state.failed) return retval; 
/*  4560 */       pushFollow(FOLLOW_port_reference_in_conditional_port_ref1482);
/*  4561 */       port_reference170 = port_reference();
/*       */       
/*  4563 */       this.state._fsp--;
/*  4564 */       if (this.state.failed) return retval; 
/*  4565 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference170.getTree()); 
/*  4566 */       RPAR171 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_conditional_port_ref1484); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*  4570 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4572 */       if (this.state.backtracking == 0)
/*       */       {
/*  4574 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4575 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4578 */     } catch (RecognitionException re) {
/*  4579 */       reportError(re);
/*  4580 */       recover((IntStream)this.input, re);
/*  4581 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4586 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_ref_extension_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4592 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_ref_extension_return port_ref_extension() throws RecognitionException {
/*  4598 */     port_ref_extension_return retval = new port_ref_extension_return();
/*  4599 */     retval.start = this.input.LT(1);
/*       */     
/*  4601 */     CommonTree root_0 = null;
/*       */     
/*  4603 */     Token DOT172 = null;
/*  4604 */     Token IDENTIFIER173 = null;
/*       */     
/*  4606 */     CommonTree DOT172_tree = null;
/*  4607 */     CommonTree IDENTIFIER173_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4613 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  4615 */       DOT172 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_port_ref_extension1496); if (this.state.failed) return retval; 
/*  4616 */       if (this.state.backtracking == 0) {
/*  4617 */         DOT172_tree = (CommonTree)this.adaptor.create(DOT172);
/*  4618 */         root_0 = (CommonTree)this.adaptor.becomeRoot(DOT172_tree, root_0);
/*       */       } 
/*  4620 */       IDENTIFIER173 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port_ref_extension1499); if (this.state.failed) return retval; 
/*  4621 */       if (this.state.backtracking == 0) {
/*  4622 */         IDENTIFIER173_tree = (CommonTree)this.adaptor.create(IDENTIFIER173);
/*  4623 */         this.adaptor.addChild(root_0, IDENTIFIER173_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  4628 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4630 */       if (this.state.backtracking == 0)
/*       */       {
/*  4632 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4633 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4636 */     } catch (RecognitionException re) {
/*  4637 */       reportError(re);
/*  4638 */       recover((IntStream)this.input, re);
/*  4639 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4644 */     return retval;
/*       */   }
/*       */   
/*       */   public static class index_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4650 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final index_return index() throws RecognitionException {
/*  4656 */     index_return retval = new index_return();
/*  4657 */     retval.start = this.input.LT(1);
/*       */     
/*  4659 */     CommonTree root_0 = null;
/*       */     
/*  4661 */     Token LBRACKET174 = null;
/*  4662 */     Token RBRACKET176 = null;
/*  4663 */     expression_return expression175 = null;
/*       */ 
/*       */     
/*  4666 */     CommonTree LBRACKET174_tree = null;
/*  4667 */     CommonTree RBRACKET176_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4673 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  4675 */       LBRACKET174 = (Token)match((IntStream)this.input, 52, FOLLOW_LBRACKET_in_index1510); if (this.state.failed) return retval; 
/*  4676 */       if (this.state.backtracking == 0) {
/*  4677 */         LBRACKET174_tree = (CommonTree)this.adaptor.create(LBRACKET174);
/*  4678 */         root_0 = (CommonTree)this.adaptor.becomeRoot(LBRACKET174_tree, root_0);
/*       */       } 
/*  4680 */       pushFollow(FOLLOW_expression_in_index1513);
/*  4681 */       expression175 = expression();
/*       */       
/*  4683 */       this.state._fsp--;
/*  4684 */       if (this.state.failed) return retval; 
/*  4685 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression175.getTree()); 
/*  4686 */       RBRACKET176 = (Token)match((IntStream)this.input, 53, FOLLOW_RBRACKET_in_index1515); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*  4690 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4692 */       if (this.state.backtracking == 0)
/*       */       {
/*  4694 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4695 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4698 */     } catch (RecognitionException re) {
/*  4699 */       reportError(re);
/*  4700 */       recover((IntStream)this.input, re);
/*  4701 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4706 */     return retval;
/*       */   }
/*       */   
/*       */   public static class priority_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4712 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final priority_definition_return priority_definition() throws RecognitionException {
/*  4718 */     priority_definition_return retval = new priority_definition_return();
/*  4719 */     retval.start = this.input.LT(1);
/*       */     
/*  4721 */     CommonTree root_0 = null;
/*       */     
/*  4723 */     Token PRIORITY177 = null;
/*  4724 */     Token IDENTIFIER178 = null;
/*  4725 */     index_return index179 = null;
/*       */     
/*  4727 */     priority_rule_return priority_rule180 = null;
/*       */     
/*  4729 */     provided_expression_return provided_expression181 = null;
/*       */     
/*  4731 */     delay_expression_return delay_expression182 = null;
/*       */ 
/*       */     
/*  4734 */     CommonTree PRIORITY177_tree = null;
/*  4735 */     CommonTree IDENTIFIER178_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4741 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  4743 */       PRIORITY177 = (Token)match((IntStream)this.input, 54, FOLLOW_PRIORITY_in_priority_definition1527); if (this.state.failed) return retval; 
/*  4744 */       if (this.state.backtracking == 0) {
/*  4745 */         PRIORITY177_tree = (CommonTree)this.adaptor.create(PRIORITY177);
/*  4746 */         root_0 = (CommonTree)this.adaptor.becomeRoot(PRIORITY177_tree, root_0);
/*       */       } 
/*  4748 */       IDENTIFIER178 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_priority_definition1530); if (this.state.failed) return retval; 
/*  4749 */       if (this.state.backtracking == 0) {
/*  4750 */         IDENTIFIER178_tree = (CommonTree)this.adaptor.create(IDENTIFIER178);
/*  4751 */         this.adaptor.addChild(root_0, IDENTIFIER178_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  4756 */         int alt52 = 2;
/*  4757 */         int LA52_0 = this.input.LA(1);
/*       */         
/*  4759 */         if (LA52_0 == 52) {
/*  4760 */           alt52 = 1;
/*       */         }
/*       */ 
/*       */         
/*  4764 */         switch (alt52) {
/*       */ 
/*       */           
/*       */           case 1:
/*  4768 */             pushFollow(FOLLOW_index_in_priority_definition1533);
/*  4769 */             index179 = index();
/*       */             
/*  4771 */             this.state._fsp--;
/*  4772 */             if (this.state.failed) return retval; 
/*  4773 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, index179.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  4783 */       pushFollow(FOLLOW_priority_rule_in_priority_definition1540);
/*  4784 */       priority_rule180 = priority_rule();
/*       */       
/*  4786 */       this.state._fsp--;
/*  4787 */       if (this.state.failed) return retval; 
/*  4788 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, priority_rule180.getTree());
/*       */       
/*  4790 */       int alt53 = 2;
/*  4791 */       int LA53_0 = this.input.LA(1);
/*       */       
/*  4793 */       if (LA53_0 == 40) {
/*  4794 */         alt53 = 1;
/*       */       }
/*  4796 */       switch (alt53) {
/*       */ 
/*       */         
/*       */         case 1:
/*  4800 */           pushFollow(FOLLOW_provided_expression_in_priority_definition1546);
/*  4801 */           provided_expression181 = provided_expression();
/*       */           
/*  4803 */           this.state._fsp--;
/*  4804 */           if (this.state.failed) return retval; 
/*  4805 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, provided_expression181.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4813 */       int alt54 = 2;
/*  4814 */       int LA54_0 = this.input.LA(1);
/*       */       
/*  4816 */       if (LA54_0 == 55) {
/*  4817 */         alt54 = 1;
/*       */       }
/*  4819 */       switch (alt54) {
/*       */ 
/*       */         
/*       */         case 1:
/*  4823 */           pushFollow(FOLLOW_delay_expression_in_priority_definition1555);
/*  4824 */           delay_expression182 = delay_expression();
/*       */           
/*  4826 */           this.state._fsp--;
/*  4827 */           if (this.state.failed) return retval; 
/*  4828 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, delay_expression182.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4838 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4840 */       if (this.state.backtracking == 0)
/*       */       {
/*  4842 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4843 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4846 */     } catch (RecognitionException re) {
/*  4847 */       reportError(re);
/*  4848 */       recover((IntStream)this.input, re);
/*  4849 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4854 */     return retval;
/*       */   }
/*       */   
/*       */   public static class delay_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4860 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final delay_expression_return delay_expression() throws RecognitionException {
/*  4866 */     delay_expression_return retval = new delay_expression_return();
/*  4867 */     retval.start = this.input.LT(1);
/*       */     
/*  4869 */     CommonTree root_0 = null;
/*       */     
/*  4871 */     Token DELAY183 = null;
/*  4872 */     time_value_return time_value184 = null;
/*       */ 
/*       */     
/*  4875 */     CommonTree DELAY183_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4881 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  4883 */       DELAY183 = (Token)match((IntStream)this.input, 55, FOLLOW_DELAY_in_delay_expression1570); if (this.state.failed) return retval; 
/*  4884 */       if (this.state.backtracking == 0) {
/*  4885 */         DELAY183_tree = (CommonTree)this.adaptor.create(DELAY183);
/*  4886 */         this.adaptor.addChild(root_0, DELAY183_tree);
/*       */       } 
/*  4888 */       pushFollow(FOLLOW_time_value_in_delay_expression1572);
/*  4889 */       time_value184 = time_value();
/*       */       
/*  4891 */       this.state._fsp--;
/*  4892 */       if (this.state.failed) return retval; 
/*  4893 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_value184.getTree());
/*       */ 
/*       */ 
/*       */       
/*  4897 */       retval.stop = this.input.LT(-1);
/*       */       
/*  4899 */       if (this.state.backtracking == 0)
/*       */       {
/*  4901 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  4902 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  4905 */     } catch (RecognitionException re) {
/*  4906 */       reportError(re);
/*  4907 */       recover((IntStream)this.input, re);
/*  4908 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  4913 */     return retval;
/*       */   }
/*       */   
/*       */   public static class priority_rule_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  4919 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final priority_rule_return priority_rule() throws RecognitionException {
/*  4925 */     priority_rule_return retval = new priority_rule_return();
/*  4926 */     retval.start = this.input.LT(1);
/*       */     
/*  4928 */     CommonTree root_0 = null;
/*       */     
/*  4930 */     Token LT186 = null;
/*  4931 */     priority_inter_low_return priority_inter_low185 = null;
/*       */     
/*  4933 */     priority_inter_high_return priority_inter_high187 = null;
/*       */ 
/*       */     
/*  4936 */     CommonTree LT186_tree = null;
/*  4937 */     RewriteRuleTokenStream stream_LT = new RewriteRuleTokenStream(this.adaptor, "token LT");
/*  4938 */     RewriteRuleSubtreeStream stream_priority_inter_high = new RewriteRuleSubtreeStream(this.adaptor, "rule priority_inter_high");
/*  4939 */     RewriteRuleSubtreeStream stream_priority_inter_low = new RewriteRuleSubtreeStream(this.adaptor, "rule priority_inter_low");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  4944 */       pushFollow(FOLLOW_priority_inter_low_in_priority_rule1582);
/*  4945 */       priority_inter_low185 = priority_inter_low();
/*       */       
/*  4947 */       this.state._fsp--;
/*  4948 */       if (this.state.failed) return retval; 
/*  4949 */       if (this.state.backtracking == 0) stream_priority_inter_low.add(priority_inter_low185.getTree()); 
/*  4950 */       LT186 = (Token)match((IntStream)this.input, 56, FOLLOW_LT_in_priority_rule1584); if (this.state.failed) return retval; 
/*  4951 */       if (this.state.backtracking == 0) stream_LT.add(LT186);
/*       */       
/*  4953 */       pushFollow(FOLLOW_priority_inter_high_in_priority_rule1586);
/*  4954 */       priority_inter_high187 = priority_inter_high();
/*       */       
/*  4956 */       this.state._fsp--;
/*  4957 */       if (this.state.failed) return retval; 
/*  4958 */       if (this.state.backtracking == 0) stream_priority_inter_high.add(priority_inter_high187.getTree());
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4968 */       if (this.state.backtracking == 0) {
/*  4969 */         retval.tree = root_0;
/*  4970 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  4972 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4977 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  4978 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(19, "LOW_INTERACTION"), root_1);
/*       */         
/*  4980 */         this.adaptor.addChild(root_1, stream_priority_inter_low.nextTree());
/*       */         
/*  4982 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */         
/*  4986 */         root_1 = (CommonTree)this.adaptor.nil();
/*  4987 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(20, "HIGH_INTERACTION"), root_1);
/*       */         
/*  4989 */         this.adaptor.addChild(root_1, stream_priority_inter_high.nextTree());
/*       */         
/*  4991 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4996 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  4999 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5001 */       if (this.state.backtracking == 0)
/*       */       {
/*  5003 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5004 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5007 */     } catch (RecognitionException re) {
/*  5008 */       reportError(re);
/*  5009 */       recover((IntStream)this.input, re);
/*  5010 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5015 */     return retval;
/*       */   }
/*       */   
/*       */   public static class priority_inter_low_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5021 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final priority_inter_low_return priority_inter_low() throws RecognitionException {
/*  5027 */     priority_inter_low_return retval = new priority_inter_low_return();
/*  5028 */     retval.start = this.input.LT(1);
/*       */     
/*  5030 */     CommonTree root_0 = null;
/*       */     
/*  5032 */     port_reference_interaction_return port_reference_interaction188 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5040 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5042 */       pushFollow(FOLLOW_port_reference_interaction_in_priority_inter_low1619);
/*  5043 */       port_reference_interaction188 = port_reference_interaction();
/*       */       
/*  5045 */       this.state._fsp--;
/*  5046 */       if (this.state.failed) return retval; 
/*  5047 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference_interaction188.getTree());
/*       */ 
/*       */ 
/*       */       
/*  5051 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5053 */       if (this.state.backtracking == 0)
/*       */       {
/*  5055 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5056 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5059 */     } catch (RecognitionException re) {
/*  5060 */       reportError(re);
/*  5061 */       recover((IntStream)this.input, re);
/*  5062 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5067 */     return retval;
/*       */   }
/*       */   
/*       */   public static class priority_inter_high_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5073 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final priority_inter_high_return priority_inter_high() throws RecognitionException {
/*  5079 */     priority_inter_high_return retval = new priority_inter_high_return();
/*  5080 */     retval.start = this.input.LT(1);
/*       */     
/*  5082 */     CommonTree root_0 = null;
/*       */     
/*  5084 */     Token MULT190 = null;
/*  5085 */     port_reference_interaction_return port_reference_interaction189 = null;
/*       */ 
/*       */     
/*  5088 */     CommonTree MULT190_tree = null;
/*       */ 
/*       */     
/*       */     try {
/*  5092 */       int alt55 = 2;
/*  5093 */       int LA55_0 = this.input.LA(1);
/*       */       
/*  5095 */       if (LA55_0 == 24) {
/*  5096 */         alt55 = 1;
/*       */       }
/*  5098 */       else if (LA55_0 == 57) {
/*  5099 */         alt55 = 2;
/*       */       } else {
/*       */         
/*  5102 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  5103 */          NoViableAltException nvae = new NoViableAltException("", 55, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  5106 */         throw nvae;
/*       */       } 
/*  5108 */       switch (alt55) {
/*       */ 
/*       */         
/*       */         case 1:
/*  5112 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  5114 */           pushFollow(FOLLOW_port_reference_interaction_in_priority_inter_high1629);
/*  5115 */           port_reference_interaction189 = port_reference_interaction();
/*       */           
/*  5117 */           this.state._fsp--;
/*  5118 */           if (this.state.failed) return retval; 
/*  5119 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference_interaction189.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  5126 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  5128 */           MULT190 = (Token)match((IntStream)this.input, 57, FOLLOW_MULT_in_priority_inter_high1633); if (this.state.failed) return retval; 
/*  5129 */           if (this.state.backtracking == 0) {
/*  5130 */             MULT190_tree = (CommonTree)this.adaptor.create(MULT190);
/*  5131 */             this.adaptor.addChild(root_0, MULT190_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  5138 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5140 */       if (this.state.backtracking == 0)
/*       */       {
/*  5142 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5143 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5146 */     } catch (RecognitionException re) {
/*  5147 */       reportError(re);
/*  5148 */       recover((IntStream)this.input, re);
/*  5149 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5154 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_reference_interaction_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5160 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_reference_interaction_return port_reference_interaction() throws RecognitionException {
/*  5166 */     port_reference_interaction_return retval = new port_reference_interaction_return();
/*  5167 */     retval.start = this.input.LT(1);
/*       */     
/*  5169 */     CommonTree root_0 = null;
/*       */     
/*  5171 */     Token COLON193 = null;
/*  5172 */     priority_connector_return priority_connector191 = null;
/*       */     
/*  5174 */     index_return index192 = null;
/*       */     
/*  5176 */     port_reference_return port_reference194 = null;
/*       */     
/*  5178 */     port_reference_return port_reference195 = null;
/*       */ 
/*       */     
/*  5181 */     CommonTree COLON193_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5187 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5189 */       pushFollow(FOLLOW_priority_connector_in_port_reference_interaction1648);
/*  5190 */       priority_connector191 = priority_connector();
/*       */       
/*  5192 */       this.state._fsp--;
/*  5193 */       if (this.state.failed) return retval; 
/*  5194 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, priority_connector191.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  5198 */         int alt56 = 2;
/*  5199 */         int LA56_0 = this.input.LA(1);
/*       */         
/*  5201 */         if (LA56_0 == 52) {
/*  5202 */           alt56 = 1;
/*       */         }
/*       */ 
/*       */         
/*  5206 */         switch (alt56) {
/*       */ 
/*       */           
/*       */           case 1:
/*  5210 */             pushFollow(FOLLOW_index_in_port_reference_interaction1651);
/*  5211 */             index192 = index();
/*       */             
/*  5213 */             this.state._fsp--;
/*  5214 */             if (this.state.failed) return retval; 
/*  5215 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, index192.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  5226 */       int alt58 = 2;
/*  5227 */       int LA58_0 = this.input.LA(1);
/*       */       
/*  5229 */       if (LA58_0 == 51) {
/*  5230 */         alt58 = 1;
/*       */       }
/*  5232 */       switch (alt58) {
/*       */ 
/*       */         
/*       */         case 1:
/*  5236 */           COLON193 = (Token)match((IntStream)this.input, 51, FOLLOW_COLON_in_port_reference_interaction1657); if (this.state.failed) return retval; 
/*  5237 */           pushFollow(FOLLOW_port_reference_in_port_reference_interaction1660);
/*  5238 */           port_reference194 = port_reference();
/*       */           
/*  5240 */           this.state._fsp--;
/*  5241 */           if (this.state.failed) return retval; 
/*  5242 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference194.getTree());
/*       */ 
/*       */           
/*       */           while (true) {
/*  5246 */             int alt57 = 2;
/*  5247 */             int LA57_0 = this.input.LA(1);
/*       */             
/*  5249 */             if (LA57_0 == 24 || LA57_0 == 32) {
/*  5250 */               alt57 = 1;
/*       */             }
/*       */ 
/*       */             
/*  5254 */             switch (alt57) {
/*       */ 
/*       */               
/*       */               case 1:
/*  5258 */                 pushFollow(FOLLOW_port_reference_in_port_reference_interaction1663);
/*  5259 */                 port_reference195 = port_reference();
/*       */                 
/*  5261 */                 this.state._fsp--;
/*  5262 */                 if (this.state.failed) return retval; 
/*  5263 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_reference195.getTree());
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5282 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5284 */       if (this.state.backtracking == 0)
/*       */       {
/*  5286 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5287 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5290 */     } catch (RecognitionException re) {
/*  5291 */       reportError(re);
/*  5292 */       recover((IntStream)this.input, re);
/*  5293 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5298 */     return retval;
/*       */   }
/*       */   
/*       */   public static class priority_connector_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5304 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final priority_connector_return priority_connector() throws RecognitionException {
/*  5310 */     priority_connector_return retval = new priority_connector_return();
/*  5311 */     retval.start = this.input.LT(1);
/*       */     
/*  5313 */     CommonTree root_0 = null;
/*       */     
/*  5315 */     Token IDENTIFIER196 = null;
/*  5316 */     Token DOT197 = null;
/*  5317 */     Token IDENTIFIER198 = null;
/*       */     
/*  5319 */     CommonTree IDENTIFIER196_tree = null;
/*  5320 */     CommonTree DOT197_tree = null;
/*  5321 */     CommonTree IDENTIFIER198_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5327 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5329 */       IDENTIFIER196 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_priority_connector1679); if (this.state.failed) return retval; 
/*  5330 */       if (this.state.backtracking == 0) {
/*  5331 */         IDENTIFIER196_tree = (CommonTree)this.adaptor.create(IDENTIFIER196);
/*  5332 */         this.adaptor.addChild(root_0, IDENTIFIER196_tree);
/*       */       } 
/*       */       
/*  5335 */       int alt59 = 2;
/*  5336 */       int LA59_0 = this.input.LA(1);
/*       */       
/*  5338 */       if (LA59_0 == 35) {
/*  5339 */         alt59 = 1;
/*       */       }
/*  5341 */       switch (alt59) {
/*       */ 
/*       */         
/*       */         case 1:
/*  5345 */           DOT197 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_priority_connector1682); if (this.state.failed) return retval; 
/*  5346 */           if (this.state.backtracking == 0) {
/*  5347 */             DOT197_tree = (CommonTree)this.adaptor.create(DOT197);
/*  5348 */             root_0 = (CommonTree)this.adaptor.becomeRoot(DOT197_tree, root_0);
/*       */           } 
/*  5350 */           IDENTIFIER198 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_priority_connector1685); if (this.state.failed) return retval; 
/*  5351 */           if (this.state.backtracking == 0) {
/*  5352 */             IDENTIFIER198_tree = (CommonTree)this.adaptor.create(IDENTIFIER198);
/*  5353 */             this.adaptor.addChild(root_0, IDENTIFIER198_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5364 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5366 */       if (this.state.backtracking == 0)
/*       */       {
/*  5368 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5369 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5372 */     } catch (RecognitionException re) {
/*  5373 */       reportError(re);
/*  5374 */       recover((IntStream)this.input, re);
/*  5375 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5380 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5386 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_definition_return port_definition() throws RecognitionException {
/*  5392 */     port_definition_return retval = new port_definition_return();
/*  5393 */     retval.start = this.input.LT(1);
/*       */     
/*  5395 */     CommonTree root_0 = null;
/*       */     
/*  5397 */     Token EXPORT199 = null;
/*  5398 */     Token PORT200 = null;
/*  5399 */     Token ASSIGN204 = null;
/*  5400 */     Token IDENTIFIER205 = null;
/*  5401 */     Token PORT206 = null;
/*  5402 */     port_type_return port_type201 = null;
/*       */     
/*  5404 */     port_return port202 = null;
/*       */     
/*  5406 */     data_port_return data_port203 = null;
/*       */     
/*  5408 */     port_type_return port_type207 = null;
/*       */     
/*  5410 */     port_return port208 = null;
/*       */     
/*  5412 */     data_port_return data_port209 = null;
/*       */ 
/*       */     
/*  5415 */     CommonTree EXPORT199_tree = null;
/*  5416 */     CommonTree PORT200_tree = null;
/*  5417 */     CommonTree ASSIGN204_tree = null;
/*  5418 */     CommonTree IDENTIFIER205_tree = null;
/*  5419 */     CommonTree PORT206_tree = null;
/*  5420 */     RewriteRuleTokenStream stream_PORT = new RewriteRuleTokenStream(this.adaptor, "token PORT");
/*  5421 */     RewriteRuleTokenStream stream_EXPORT = new RewriteRuleTokenStream(this.adaptor, "token EXPORT");
/*  5422 */     RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFIER");
/*  5423 */     RewriteRuleTokenStream stream_ASSIGN = new RewriteRuleTokenStream(this.adaptor, "token ASSIGN");
/*  5424 */     RewriteRuleSubtreeStream stream_port = new RewriteRuleSubtreeStream(this.adaptor, "rule port");
/*  5425 */     RewriteRuleSubtreeStream stream_port_type = new RewriteRuleSubtreeStream(this.adaptor, "rule port_type");
/*  5426 */     RewriteRuleSubtreeStream stream_data_port = new RewriteRuleSubtreeStream(this.adaptor, "rule data_port");
/*       */     
/*       */     try {
/*  5429 */       int alt60, alt62, LA60_0, LA62_0, alt61, LA61_0, alt63 = 2;
/*  5430 */       int LA63_0 = this.input.LA(1);
/*       */       
/*  5432 */       if (LA63_0 == 37) {
/*  5433 */         alt63 = 1;
/*       */       }
/*  5435 */       else if (LA63_0 == 30) {
/*  5436 */         alt63 = 2;
/*       */       } else {
/*       */         
/*  5439 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  5440 */          NoViableAltException nvae = new NoViableAltException("", 63, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  5443 */         throw nvae;
/*       */       } 
/*  5445 */       switch (alt63) {
/*       */ 
/*       */         
/*       */         case 1:
/*  5449 */           EXPORT199 = (Token)match((IntStream)this.input, 37, FOLLOW_EXPORT_in_port_definition1706); if (this.state.failed) return retval; 
/*  5450 */           if (this.state.backtracking == 0) stream_EXPORT.add(EXPORT199);
/*       */           
/*  5452 */           PORT200 = (Token)match((IntStream)this.input, 30, FOLLOW_PORT_in_port_definition1708); if (this.state.failed) return retval; 
/*  5453 */           if (this.state.backtracking == 0) stream_PORT.add(PORT200);
/*       */           
/*  5455 */           pushFollow(FOLLOW_port_type_in_port_definition1710);
/*  5456 */           port_type201 = port_type();
/*       */           
/*  5458 */           this.state._fsp--;
/*  5459 */           if (this.state.failed) return retval; 
/*  5460 */           if (this.state.backtracking == 0) stream_port_type.add(port_type201.getTree()); 
/*  5461 */           pushFollow(FOLLOW_port_in_port_definition1712);
/*  5462 */           port202 = port();
/*       */           
/*  5464 */           this.state._fsp--;
/*  5465 */           if (this.state.failed) return retval; 
/*  5466 */           if (this.state.backtracking == 0) stream_port.add(port202.getTree());
/*       */           
/*  5468 */           alt60 = 2;
/*  5469 */           LA60_0 = this.input.LA(1);
/*       */           
/*  5471 */           if (LA60_0 == 32) {
/*  5472 */             alt60 = 1;
/*       */           }
/*  5474 */           switch (alt60) {
/*       */ 
/*       */             
/*       */             case 1:
/*  5478 */               pushFollow(FOLLOW_data_port_in_port_definition1714);
/*  5479 */               data_port203 = data_port();
/*       */               
/*  5481 */               this.state._fsp--;
/*  5482 */               if (this.state.failed) return retval; 
/*  5483 */               if (this.state.backtracking == 0) stream_data_port.add(data_port203.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5491 */           alt61 = 2;
/*  5492 */           LA61_0 = this.input.LA(1);
/*       */           
/*  5494 */           if (LA61_0 == 58) {
/*  5495 */             alt61 = 1;
/*       */           }
/*  5497 */           switch (alt61) {
/*       */ 
/*       */             
/*       */             case 1:
/*  5501 */               ASSIGN204 = (Token)match((IntStream)this.input, 58, FOLLOW_ASSIGN_in_port_definition1718); if (this.state.failed) return retval; 
/*  5502 */               if (this.state.backtracking == 0) stream_ASSIGN.add(ASSIGN204);
/*       */               
/*  5504 */               IDENTIFIER205 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port_definition1720); if (this.state.failed) return retval; 
/*  5505 */               if (this.state.backtracking == 0) stream_IDENTIFIER.add(IDENTIFIER205);
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5522 */           if (this.state.backtracking == 0) {
/*  5523 */             retval.tree = root_0;
/*  5524 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  5526 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5531 */             CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  5532 */             root_1 = (CommonTree)this.adaptor.becomeRoot(stream_PORT.nextNode(), root_1);
/*       */             
/*  5534 */             this.adaptor.addChild(root_1, stream_port_type.nextTree());
/*  5535 */             this.adaptor.addChild(root_1, stream_port.nextTree());
/*       */             
/*  5537 */             if (stream_data_port.hasNext()) {
/*  5538 */               this.adaptor.addChild(root_1, stream_data_port.nextTree());
/*       */             }
/*       */             
/*  5541 */             stream_data_port.reset();
/*       */             
/*  5543 */             if (stream_IDENTIFIER.hasNext() || stream_EXPORT.hasNext()) {
/*       */ 
/*       */               
/*  5546 */               CommonTree root_2 = (CommonTree)this.adaptor.nil();
/*  5547 */               root_2 = (CommonTree)this.adaptor.becomeRoot(stream_EXPORT.nextNode(), root_2);
/*       */ 
/*       */               
/*  5550 */               if (stream_IDENTIFIER.hasNext()) {
/*  5551 */                 this.adaptor.addChild(root_2, stream_IDENTIFIER.nextNode());
/*       */               }
/*       */               
/*  5554 */               stream_IDENTIFIER.reset();
/*       */               
/*  5556 */               this.adaptor.addChild(root_1, root_2);
/*       */             } 
/*       */ 
/*       */             
/*  5560 */             stream_IDENTIFIER.reset();
/*  5561 */             stream_EXPORT.reset();
/*       */             
/*  5563 */             this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5568 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 2:
/*  5574 */           PORT206 = (Token)match((IntStream)this.input, 30, FOLLOW_PORT_in_port_definition1753); if (this.state.failed) return retval; 
/*  5575 */           if (this.state.backtracking == 0) stream_PORT.add(PORT206);
/*       */           
/*  5577 */           pushFollow(FOLLOW_port_type_in_port_definition1755);
/*  5578 */           port_type207 = port_type();
/*       */           
/*  5580 */           this.state._fsp--;
/*  5581 */           if (this.state.failed) return retval; 
/*  5582 */           if (this.state.backtracking == 0) stream_port_type.add(port_type207.getTree()); 
/*  5583 */           pushFollow(FOLLOW_port_in_port_definition1757);
/*  5584 */           port208 = port();
/*       */           
/*  5586 */           this.state._fsp--;
/*  5587 */           if (this.state.failed) return retval; 
/*  5588 */           if (this.state.backtracking == 0) stream_port.add(port208.getTree());
/*       */           
/*  5590 */           alt62 = 2;
/*  5591 */           LA62_0 = this.input.LA(1);
/*       */           
/*  5593 */           if (LA62_0 == 32) {
/*  5594 */             alt62 = 1;
/*       */           }
/*  5596 */           switch (alt62) {
/*       */ 
/*       */             
/*       */             case 1:
/*  5600 */               pushFollow(FOLLOW_data_port_in_port_definition1759);
/*  5601 */               data_port209 = data_port();
/*       */               
/*  5603 */               this.state._fsp--;
/*  5604 */               if (this.state.failed) return retval; 
/*  5605 */               if (this.state.backtracking == 0) stream_data_port.add(data_port209.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5621 */           if (this.state.backtracking == 0) {
/*  5622 */             retval.tree = root_0;
/*  5623 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  5625 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5630 */             CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  5631 */             root_1 = (CommonTree)this.adaptor.becomeRoot(stream_PORT.nextNode(), root_1);
/*       */             
/*  5633 */             this.adaptor.addChild(root_1, stream_port_type.nextTree());
/*  5634 */             this.adaptor.addChild(root_1, stream_port.nextTree());
/*       */             
/*  5636 */             if (stream_data_port.hasNext()) {
/*  5637 */               this.adaptor.addChild(root_1, stream_data_port.nextTree());
/*       */             }
/*       */             
/*  5640 */             stream_data_port.reset();
/*       */             
/*  5642 */             this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5647 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */       
/*  5652 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5654 */       if (this.state.backtracking == 0)
/*       */       {
/*  5656 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5657 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5660 */     } catch (RecognitionException re) {
/*  5661 */       reportError(re);
/*  5662 */       recover((IntStream)this.input, re);
/*  5663 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5668 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_port_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5674 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_port_return data_port() throws RecognitionException {
/*  5680 */     data_port_return retval = new data_port_return();
/*  5681 */     retval.start = this.input.LT(1);
/*       */     
/*  5683 */     CommonTree root_0 = null;
/*       */     
/*  5685 */     Token LPAR210 = null;
/*  5686 */     Token COMMA212 = null;
/*  5687 */     Token RPAR214 = null;
/*  5688 */     expression_return expression211 = null;
/*       */     
/*  5690 */     expression_return expression213 = null;
/*       */ 
/*       */     
/*  5693 */     CommonTree LPAR210_tree = null;
/*  5694 */     CommonTree COMMA212_tree = null;
/*  5695 */     CommonTree RPAR214_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5701 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5703 */       LPAR210 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_data_port1791); if (this.state.failed) return retval; 
/*  5704 */       if (this.state.backtracking == 0) {
/*  5705 */         LPAR210_tree = (CommonTree)this.adaptor.create(LPAR210);
/*  5706 */         root_0 = (CommonTree)this.adaptor.becomeRoot(LPAR210_tree, root_0);
/*       */       } 
/*       */       
/*  5709 */       int alt65 = 2;
/*  5710 */       int LA65_0 = this.input.LA(1);
/*       */       
/*  5712 */       if (LA65_0 == 24 || LA65_0 == 32 || LA65_0 == 57 || LA65_0 == 68 || LA65_0 == 78 || LA65_0 == 83 || LA65_0 == 91 || (LA65_0 >= 94 && LA65_0 <= 97) || (LA65_0 >= 99 && LA65_0 <= 101)) {
/*  5713 */         alt65 = 1;
/*       */       }
/*  5715 */       switch (alt65) {
/*       */ 
/*       */         
/*       */         case 1:
/*  5719 */           pushFollow(FOLLOW_expression_in_data_port1796);
/*  5720 */           expression211 = expression();
/*       */           
/*  5722 */           this.state._fsp--;
/*  5723 */           if (this.state.failed) return retval; 
/*  5724 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression211.getTree());
/*       */ 
/*       */           
/*       */           while (true) {
/*  5728 */             int alt64 = 2;
/*  5729 */             int LA64_0 = this.input.LA(1);
/*       */             
/*  5731 */             if (LA64_0 == 33) {
/*  5732 */               alt64 = 1;
/*       */             }
/*       */ 
/*       */             
/*  5736 */             switch (alt64) {
/*       */ 
/*       */               
/*       */               case 1:
/*  5740 */                 COMMA212 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_data_port1799); if (this.state.failed) return retval; 
/*  5741 */                 pushFollow(FOLLOW_expression_in_data_port1802);
/*  5742 */                 expression213 = expression();
/*       */                 
/*  5744 */                 this.state._fsp--;
/*  5745 */                 if (this.state.failed) return retval; 
/*  5746 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression213.getTree());
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5762 */       RPAR214 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_data_port1809); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*  5766 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5768 */       if (this.state.backtracking == 0)
/*       */       {
/*  5770 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5771 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5774 */     } catch (RecognitionException re) {
/*  5775 */       reportError(re);
/*  5776 */       recover((IntStream)this.input, re);
/*  5777 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5782 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5788 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_return port() throws RecognitionException {
/*  5794 */     port_return retval = new port_return();
/*  5795 */     retval.start = this.input.LT(1);
/*       */     
/*  5797 */     CommonTree root_0 = null;
/*       */     
/*  5799 */     Token IDENTIFIER215 = null;
/*       */     
/*  5801 */     CommonTree IDENTIFIER215_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5807 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5809 */       IDENTIFIER215 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_port1821); if (this.state.failed) return retval; 
/*  5810 */       if (this.state.backtracking == 0) {
/*  5811 */         IDENTIFIER215_tree = (CommonTree)this.adaptor.create(IDENTIFIER215);
/*  5812 */         this.adaptor.addChild(root_0, IDENTIFIER215_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  5817 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5819 */       if (this.state.backtracking == 0)
/*       */       {
/*  5821 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5822 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5825 */     } catch (RecognitionException re) {
/*  5826 */       reportError(re);
/*  5827 */       recover((IntStream)this.input, re);
/*  5828 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5833 */     return retval;
/*       */   }
/*       */   
/*       */   public static class place_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5839 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final place_definition_return place_definition() throws RecognitionException {
/*  5845 */     place_definition_return retval = new place_definition_return();
/*  5846 */     retval.start = this.input.LT(1);
/*       */     
/*  5848 */     CommonTree root_0 = null;
/*       */     
/*  5850 */     Token PLACE216 = null;
/*  5851 */     Token COMMA218 = null;
/*  5852 */     place_declaration_return place_declaration217 = null;
/*       */     
/*  5854 */     place_declaration_return place_declaration219 = null;
/*       */ 
/*       */     
/*  5857 */     CommonTree PLACE216_tree = null;
/*  5858 */     CommonTree COMMA218_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5864 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5866 */       PLACE216 = (Token)match((IntStream)this.input, 59, FOLLOW_PLACE_in_place_definition1835); if (this.state.failed) return retval; 
/*  5867 */       if (this.state.backtracking == 0) {
/*  5868 */         PLACE216_tree = (CommonTree)this.adaptor.create(PLACE216);
/*  5869 */         root_0 = (CommonTree)this.adaptor.becomeRoot(PLACE216_tree, root_0);
/*       */       } 
/*  5871 */       pushFollow(FOLLOW_place_declaration_in_place_definition1839);
/*  5872 */       place_declaration217 = place_declaration();
/*       */       
/*  5874 */       this.state._fsp--;
/*  5875 */       if (this.state.failed) return retval; 
/*  5876 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place_declaration217.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  5880 */         int alt66 = 2;
/*  5881 */         int LA66_0 = this.input.LA(1);
/*       */         
/*  5883 */         if (LA66_0 == 33) {
/*  5884 */           alt66 = 1;
/*       */         }
/*       */ 
/*       */         
/*  5888 */         switch (alt66) {
/*       */ 
/*       */           
/*       */           case 1:
/*  5892 */             COMMA218 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_place_definition1842); if (this.state.failed) return retval; 
/*  5893 */             pushFollow(FOLLOW_place_declaration_in_place_definition1845);
/*  5894 */             place_declaration219 = place_declaration();
/*       */             
/*  5896 */             this.state._fsp--;
/*  5897 */             if (this.state.failed) return retval; 
/*  5898 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place_declaration219.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  5911 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5913 */       if (this.state.backtracking == 0)
/*       */       {
/*  5915 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5916 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  5919 */     } catch (RecognitionException re) {
/*  5920 */       reportError(re);
/*  5921 */       recover((IntStream)this.input, re);
/*  5922 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  5927 */     return retval;
/*       */   }
/*       */   
/*       */   public static class place_declaration_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  5933 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final place_declaration_return place_declaration() throws RecognitionException {
/*  5939 */     place_declaration_return retval = new place_declaration_return();
/*  5940 */     retval.start = this.input.LT(1);
/*       */     
/*  5942 */     CommonTree root_0 = null;
/*       */     
/*  5944 */     Token IDENTIFIER220 = null;
/*  5945 */     Token ASSIGN221 = null;
/*  5946 */     Token INITIAL222 = null;
/*       */     
/*  5948 */     CommonTree IDENTIFIER220_tree = null;
/*  5949 */     CommonTree ASSIGN221_tree = null;
/*  5950 */     CommonTree INITIAL222_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  5956 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  5958 */       IDENTIFIER220 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_place_declaration1861); if (this.state.failed) return retval; 
/*  5959 */       if (this.state.backtracking == 0) {
/*  5960 */         IDENTIFIER220_tree = (CommonTree)this.adaptor.create(IDENTIFIER220);
/*  5961 */         root_0 = (CommonTree)this.adaptor.becomeRoot(IDENTIFIER220_tree, root_0);
/*       */       } 
/*       */       
/*  5964 */       int alt67 = 2;
/*  5965 */       int LA67_0 = this.input.LA(1);
/*       */       
/*  5967 */       if (LA67_0 == 58) {
/*  5968 */         alt67 = 1;
/*       */       }
/*  5970 */       switch (alt67) {
/*       */ 
/*       */         
/*       */         case 1:
/*  5974 */           ASSIGN221 = (Token)match((IntStream)this.input, 58, FOLLOW_ASSIGN_in_place_declaration1865); if (this.state.failed) return retval; 
/*  5975 */           if (this.state.backtracking == 0) {
/*  5976 */             ASSIGN221_tree = (CommonTree)this.adaptor.create(ASSIGN221);
/*  5977 */             this.adaptor.addChild(root_0, ASSIGN221_tree);
/*       */           } 
/*  5979 */           INITIAL222 = (Token)match((IntStream)this.input, 60, FOLLOW_INITIAL_in_place_declaration1867); if (this.state.failed) return retval; 
/*  5980 */           if (this.state.backtracking == 0) {
/*  5981 */             INITIAL222_tree = (CommonTree)this.adaptor.create(INITIAL222);
/*  5982 */             this.adaptor.addChild(root_0, INITIAL222_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5993 */       retval.stop = this.input.LT(-1);
/*       */       
/*  5995 */       if (this.state.backtracking == 0)
/*       */       {
/*  5997 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  5998 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6001 */     } catch (RecognitionException re) {
/*  6002 */       reportError(re);
/*  6003 */       recover((IntStream)this.input, re);
/*  6004 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6009 */     return retval;
/*       */   }
/*       */   
/*       */   public static class place_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6015 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final place_return place() throws RecognitionException {
/*  6021 */     place_return retval = new place_return();
/*  6022 */     retval.start = this.input.LT(1);
/*       */     
/*  6024 */     CommonTree root_0 = null;
/*       */     
/*  6026 */     Token IDENTIFIER223 = null;
/*       */     
/*  6028 */     CommonTree IDENTIFIER223_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6034 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6036 */       IDENTIFIER223 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_place1883); if (this.state.failed) return retval; 
/*  6037 */       if (this.state.backtracking == 0) {
/*  6038 */         IDENTIFIER223_tree = (CommonTree)this.adaptor.create(IDENTIFIER223);
/*  6039 */         this.adaptor.addChild(root_0, IDENTIFIER223_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  6044 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6046 */       if (this.state.backtracking == 0)
/*       */       {
/*  6048 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6049 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6052 */     } catch (RecognitionException re) {
/*  6053 */       reportError(re);
/*  6054 */       recover((IntStream)this.input, re);
/*  6055 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6060 */     return retval;
/*       */   }
/*       */   
/*       */   public static class initialization_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6066 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final initialization_return initialization() throws RecognitionException {
/*  6072 */     initialization_return retval = new initialization_return();
/*  6073 */     retval.start = this.input.LT(1);
/*       */     
/*  6075 */     CommonTree root_0 = null;
/*       */     
/*  6077 */     Token INITIAL224 = null;
/*  6078 */     to_places_return to_places225 = null;
/*       */     
/*  6080 */     do_action_return do_action226 = null;
/*       */ 
/*       */     
/*  6083 */     CommonTree INITIAL224_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6089 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6091 */       INITIAL224 = (Token)match((IntStream)this.input, 60, FOLLOW_INITIAL_in_initialization1894); if (this.state.failed) return retval; 
/*  6092 */       if (this.state.backtracking == 0) {
/*  6093 */         INITIAL224_tree = (CommonTree)this.adaptor.create(INITIAL224);
/*  6094 */         root_0 = (CommonTree)this.adaptor.becomeRoot(INITIAL224_tree, root_0);
/*       */       } 
/*  6096 */       pushFollow(FOLLOW_to_places_in_initialization1897);
/*  6097 */       to_places225 = to_places();
/*       */       
/*  6099 */       this.state._fsp--;
/*  6100 */       if (this.state.failed) return retval; 
/*  6101 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, to_places225.getTree());
/*       */       
/*  6103 */       int alt68 = 2;
/*  6104 */       int LA68_0 = this.input.LA(1);
/*       */       
/*  6106 */       if (LA68_0 == 74) {
/*  6107 */         alt68 = 1;
/*       */       }
/*  6109 */       switch (alt68) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6113 */           pushFollow(FOLLOW_do_action_in_initialization1901);
/*  6114 */           do_action226 = do_action();
/*       */           
/*  6116 */           this.state._fsp--;
/*  6117 */           if (this.state.failed) return retval; 
/*  6118 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, do_action226.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6128 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6130 */       if (this.state.backtracking == 0)
/*       */       {
/*  6132 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6133 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6136 */     } catch (RecognitionException re) {
/*  6137 */       reportError(re);
/*  6138 */       recover((IntStream)this.input, re);
/*  6139 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6144 */     return retval;
/*       */   }
/*       */   
/*       */   public static class transition_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6150 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final transition_definition_return transition_definition() throws RecognitionException {
/*  6156 */     transition_definition_return retval = new transition_definition_return();
/*  6157 */     retval.start = this.input.LT(1);
/*       */     
/*  6159 */     CommonTree root_0 = null;
/*       */     
/*  6161 */     Token ON227 = null;
/*  6162 */     port_expression_return port_expression228 = null;
/*       */     
/*  6164 */     from_places_return from_places229 = null;
/*       */     
/*  6166 */     to_places_return to_places230 = null;
/*       */     
/*  6168 */     provided_expression_return provided_expression231 = null;
/*       */     
/*  6170 */     timed_condition_return timed_condition232 = null;
/*       */     
/*  6172 */     time_reset_return time_reset233 = null;
/*       */     
/*  6174 */     do_action_return do_action234 = null;
/*       */ 
/*       */     
/*  6177 */     CommonTree ON227_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6183 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6185 */       ON227 = (Token)match((IntStream)this.input, 39, FOLLOW_ON_in_transition_definition1915); if (this.state.failed) return retval; 
/*  6186 */       if (this.state.backtracking == 0) {
/*  6187 */         ON227_tree = (CommonTree)this.adaptor.create(ON227);
/*  6188 */         root_0 = (CommonTree)this.adaptor.becomeRoot(ON227_tree, root_0);
/*       */       } 
/*  6190 */       pushFollow(FOLLOW_port_expression_in_transition_definition1918);
/*  6191 */       port_expression228 = port_expression();
/*       */       
/*  6193 */       this.state._fsp--;
/*  6194 */       if (this.state.failed) return retval; 
/*  6195 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_expression228.getTree()); 
/*  6196 */       pushFollow(FOLLOW_from_places_in_transition_definition1923);
/*  6197 */       from_places229 = from_places();
/*       */       
/*  6199 */       this.state._fsp--;
/*  6200 */       if (this.state.failed) return retval; 
/*  6201 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, from_places229.getTree()); 
/*  6202 */       pushFollow(FOLLOW_to_places_in_transition_definition1928);
/*  6203 */       to_places230 = to_places();
/*       */       
/*  6205 */       this.state._fsp--;
/*  6206 */       if (this.state.failed) return retval; 
/*  6207 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, to_places230.getTree());
/*       */       
/*  6209 */       int alt69 = 2;
/*  6210 */       int LA69_0 = this.input.LA(1);
/*       */       
/*  6212 */       if (LA69_0 == 40) {
/*  6213 */         alt69 = 1;
/*       */       }
/*  6215 */       switch (alt69) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6219 */           pushFollow(FOLLOW_provided_expression_in_transition_definition1934);
/*  6220 */           provided_expression231 = provided_expression();
/*       */           
/*  6222 */           this.state._fsp--;
/*  6223 */           if (this.state.failed) return retval; 
/*  6224 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, provided_expression231.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6232 */       int alt70 = 2;
/*  6233 */       int LA70_0 = this.input.LA(1);
/*       */       
/*  6235 */       if (LA70_0 >= 63 && LA70_0 <= 65) {
/*  6236 */         alt70 = 1;
/*       */       }
/*  6238 */       switch (alt70) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6242 */           pushFollow(FOLLOW_timed_condition_in_transition_definition1943);
/*  6243 */           timed_condition232 = timed_condition();
/*       */           
/*  6245 */           this.state._fsp--;
/*  6246 */           if (this.state.failed) return retval; 
/*  6247 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, timed_condition232.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6255 */       int alt71 = 2;
/*  6256 */       int LA71_0 = this.input.LA(1);
/*       */       
/*  6258 */       if (LA71_0 == 73) {
/*  6259 */         alt71 = 1;
/*       */       }
/*  6261 */       switch (alt71) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6265 */           pushFollow(FOLLOW_time_reset_in_transition_definition1952);
/*  6266 */           time_reset233 = time_reset();
/*       */           
/*  6268 */           this.state._fsp--;
/*  6269 */           if (this.state.failed) return retval; 
/*  6270 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_reset233.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6278 */       int alt72 = 2;
/*  6279 */       int LA72_0 = this.input.LA(1);
/*       */       
/*  6281 */       if (LA72_0 == 74) {
/*  6282 */         alt72 = 1;
/*       */       }
/*  6284 */       switch (alt72) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6288 */           pushFollow(FOLLOW_do_action_in_transition_definition1961);
/*  6289 */           do_action234 = do_action();
/*       */           
/*  6291 */           this.state._fsp--;
/*  6292 */           if (this.state.failed) return retval; 
/*  6293 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, do_action234.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6303 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6305 */       if (this.state.backtracking == 0)
/*       */       {
/*  6307 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6308 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6311 */     } catch (RecognitionException re) {
/*  6312 */       reportError(re);
/*  6313 */       recover((IntStream)this.input, re);
/*  6314 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6319 */     return retval;
/*       */   }
/*       */   
/*       */   public static class from_places_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6325 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final from_places_return from_places() throws RecognitionException {
/*  6331 */     from_places_return retval = new from_places_return();
/*  6332 */     retval.start = this.input.LT(1);
/*       */     
/*  6334 */     CommonTree root_0 = null;
/*       */     
/*  6336 */     Token FROM235 = null;
/*  6337 */     Token COMMA237 = null;
/*  6338 */     place_return place236 = null;
/*       */     
/*  6340 */     place_return place238 = null;
/*       */ 
/*       */     
/*  6343 */     CommonTree FROM235_tree = null;
/*  6344 */     CommonTree COMMA237_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6350 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6352 */       FROM235 = (Token)match((IntStream)this.input, 61, FOLLOW_FROM_in_from_places1975); if (this.state.failed) return retval; 
/*  6353 */       if (this.state.backtracking == 0) {
/*  6354 */         FROM235_tree = (CommonTree)this.adaptor.create(FROM235);
/*  6355 */         root_0 = (CommonTree)this.adaptor.becomeRoot(FROM235_tree, root_0);
/*       */       } 
/*  6357 */       pushFollow(FOLLOW_place_in_from_places1978);
/*  6358 */       place236 = place();
/*       */       
/*  6360 */       this.state._fsp--;
/*  6361 */       if (this.state.failed) return retval; 
/*  6362 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place236.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  6366 */         int alt73 = 2;
/*  6367 */         int LA73_0 = this.input.LA(1);
/*       */         
/*  6369 */         if (LA73_0 == 33) {
/*  6370 */           alt73 = 1;
/*       */         }
/*       */ 
/*       */         
/*  6374 */         switch (alt73) {
/*       */ 
/*       */           
/*       */           case 1:
/*  6378 */             COMMA237 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_from_places1981); if (this.state.failed) return retval; 
/*  6379 */             pushFollow(FOLLOW_place_in_from_places1984);
/*  6380 */             place238 = place();
/*       */             
/*  6382 */             this.state._fsp--;
/*  6383 */             if (this.state.failed) return retval; 
/*  6384 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place238.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  6397 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6399 */       if (this.state.backtracking == 0)
/*       */       {
/*  6401 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6402 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6405 */     } catch (RecognitionException re) {
/*  6406 */       reportError(re);
/*  6407 */       recover((IntStream)this.input, re);
/*  6408 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6413 */     return retval;
/*       */   }
/*       */   
/*       */   public static class to_places_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6419 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final to_places_return to_places() throws RecognitionException {
/*  6425 */     to_places_return retval = new to_places_return();
/*  6426 */     retval.start = this.input.LT(1);
/*       */     
/*  6428 */     CommonTree root_0 = null;
/*       */     
/*  6430 */     Token TO239 = null;
/*  6431 */     Token COMMA241 = null;
/*  6432 */     place_return place240 = null;
/*       */     
/*  6434 */     place_return place242 = null;
/*       */ 
/*       */     
/*  6437 */     CommonTree TO239_tree = null;
/*  6438 */     CommonTree COMMA241_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6444 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6446 */       TO239 = (Token)match((IntStream)this.input, 62, FOLLOW_TO_in_to_places2001); if (this.state.failed) return retval; 
/*  6447 */       if (this.state.backtracking == 0) {
/*  6448 */         TO239_tree = (CommonTree)this.adaptor.create(TO239);
/*  6449 */         root_0 = (CommonTree)this.adaptor.becomeRoot(TO239_tree, root_0);
/*       */       } 
/*  6451 */       pushFollow(FOLLOW_place_in_to_places2004);
/*  6452 */       place240 = place();
/*       */       
/*  6454 */       this.state._fsp--;
/*  6455 */       if (this.state.failed) return retval; 
/*  6456 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place240.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  6460 */         int alt74 = 2;
/*  6461 */         int LA74_0 = this.input.LA(1);
/*       */         
/*  6463 */         if (LA74_0 == 33) {
/*  6464 */           alt74 = 1;
/*       */         }
/*       */ 
/*       */         
/*  6468 */         switch (alt74) {
/*       */ 
/*       */           
/*       */           case 1:
/*  6472 */             COMMA241 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_to_places2007); if (this.state.failed) return retval; 
/*  6473 */             pushFollow(FOLLOW_place_in_to_places2010);
/*  6474 */             place242 = place();
/*       */             
/*  6476 */             this.state._fsp--;
/*  6477 */             if (this.state.failed) return retval; 
/*  6478 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, place242.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  6491 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6493 */       if (this.state.backtracking == 0)
/*       */       {
/*  6495 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6496 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6499 */     } catch (RecognitionException re) {
/*  6500 */       reportError(re);
/*  6501 */       recover((IntStream)this.input, re);
/*  6502 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6507 */     return retval;
/*       */   }
/*       */   
/*       */   public static class timed_condition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6513 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final timed_condition_return timed_condition() throws RecognitionException {
/*  6519 */     timed_condition_return retval = new timed_condition_return();
/*  6520 */     retval.start = this.input.LT(1);
/*       */     
/*  6522 */     CommonTree root_0 = null;
/*       */     
/*  6524 */     urgency_return urgency243 = null;
/*       */     
/*  6526 */     timed_guards_return timed_guards244 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6534 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6536 */       pushFollow(FOLLOW_urgency_in_timed_condition2027);
/*  6537 */       urgency243 = urgency();
/*       */       
/*  6539 */       this.state._fsp--;
/*  6540 */       if (this.state.failed) return retval; 
/*  6541 */       if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(urgency243.getTree(), root_0);
/*       */       
/*  6543 */       int alt75 = 2;
/*  6544 */       int LA75_0 = this.input.LA(1);
/*       */       
/*  6546 */       if (LA75_0 == 24) {
/*  6547 */         alt75 = 1;
/*       */       }
/*  6549 */       switch (alt75) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6553 */           pushFollow(FOLLOW_timed_guards_in_timed_condition2031);
/*  6554 */           timed_guards244 = timed_guards();
/*       */           
/*  6556 */           this.state._fsp--;
/*  6557 */           if (this.state.failed) return retval; 
/*  6558 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, timed_guards244.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6568 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6570 */       if (this.state.backtracking == 0)
/*       */       {
/*  6572 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6573 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6576 */     } catch (RecognitionException re) {
/*  6577 */       reportError(re);
/*  6578 */       recover((IntStream)this.input, re);
/*  6579 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6584 */     return retval;
/*       */   }
/*       */   
/*       */   public static class urgency_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6590 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final urgency_return urgency() throws RecognitionException {
/*  6596 */     urgency_return retval = new urgency_return();
/*  6597 */     retval.start = this.input.LT(1);
/*       */     
/*  6599 */     CommonTree root_0 = null;
/*       */     
/*  6601 */     Token set245 = null;
/*       */     
/*  6603 */     CommonTree set245_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6609 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6611 */       set245 = this.input.LT(1);
/*  6612 */       if (this.input.LA(1) >= 63 && this.input.LA(1) <= 65) {
/*  6613 */         this.input.consume();
/*  6614 */         if (this.state.backtracking == 0) this.adaptor.addChild(root_0, this.adaptor.create(set245)); 
/*  6615 */         this.state.errorRecovery = false; this.state.failed = false;
/*       */       } else {
/*       */         
/*  6618 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  6619 */          MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  6620 */         throw mse;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6626 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6628 */       if (this.state.backtracking == 0)
/*       */       {
/*  6630 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6631 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6634 */     } catch (RecognitionException re) {
/*  6635 */       reportError(re);
/*  6636 */       recover((IntStream)this.input, re);
/*  6637 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6642 */     return retval;
/*       */   }
/*       */   
/*       */   public static class timed_guards_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6648 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final timed_guards_return timed_guards() throws RecognitionException {
/*  6654 */     timed_guards_return retval = new timed_guards_return();
/*  6655 */     retval.start = this.input.LT(1);
/*       */     
/*  6657 */     CommonTree root_0 = null;
/*       */     
/*  6659 */     Token AND247 = null;
/*  6660 */     time_constraint_return time_constraint246 = null;
/*       */     
/*  6662 */     time_constraint_return time_constraint248 = null;
/*       */ 
/*       */     
/*  6665 */     CommonTree AND247_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6671 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6673 */       pushFollow(FOLLOW_time_constraint_in_timed_guards2067);
/*  6674 */       time_constraint246 = time_constraint();
/*       */       
/*  6676 */       this.state._fsp--;
/*  6677 */       if (this.state.failed) return retval; 
/*  6678 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_constraint246.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  6682 */         int alt76 = 2;
/*  6683 */         int LA76_0 = this.input.LA(1);
/*       */         
/*  6685 */         if (LA76_0 == 66) {
/*  6686 */           alt76 = 1;
/*       */         }
/*       */ 
/*       */         
/*  6690 */         switch (alt76) {
/*       */ 
/*       */           
/*       */           case 1:
/*  6694 */             AND247 = (Token)match((IntStream)this.input, 66, FOLLOW_AND_in_timed_guards2070); if (this.state.failed) return retval; 
/*  6695 */             pushFollow(FOLLOW_time_constraint_in_timed_guards2073);
/*  6696 */             time_constraint248 = time_constraint();
/*       */             
/*  6698 */             this.state._fsp--;
/*  6699 */             if (this.state.failed) return retval; 
/*  6700 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_constraint248.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  6713 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6715 */       if (this.state.backtracking == 0)
/*       */       {
/*  6717 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6718 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6721 */     } catch (RecognitionException re) {
/*  6722 */       reportError(re);
/*  6723 */       recover((IntStream)this.input, re);
/*  6724 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6729 */     return retval;
/*       */   }
/*       */   
/*       */   public static class time_constraint_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6735 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final time_constraint_return time_constraint() throws RecognitionException {
/*  6741 */     time_constraint_return retval = new time_constraint_return();
/*  6742 */     retval.start = this.input.LT(1);
/*       */     
/*  6744 */     CommonTree root_0 = null;
/*       */     
/*  6746 */     Token IDENTIFIER249 = null;
/*  6747 */     Token IN250 = null;
/*  6748 */     Token LPAR251 = null;
/*  6749 */     Token COMMA253 = null;
/*  6750 */     Token RPAR255 = null;
/*  6751 */     time_value_return time_value252 = null;
/*       */     
/*  6753 */     time_value_return time_value254 = null;
/*       */ 
/*       */     
/*  6756 */     CommonTree IDENTIFIER249_tree = null;
/*  6757 */     CommonTree IN250_tree = null;
/*  6758 */     CommonTree LPAR251_tree = null;
/*  6759 */     CommonTree COMMA253_tree = null;
/*  6760 */     CommonTree RPAR255_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6766 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6768 */       IDENTIFIER249 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_time_constraint2088); if (this.state.failed) return retval; 
/*  6769 */       if (this.state.backtracking == 0) {
/*  6770 */         IDENTIFIER249_tree = (CommonTree)this.adaptor.create(IDENTIFIER249);
/*  6771 */         this.adaptor.addChild(root_0, IDENTIFIER249_tree);
/*       */       } 
/*  6773 */       IN250 = (Token)match((IntStream)this.input, 67, FOLLOW_IN_in_time_constraint2090); if (this.state.failed) return retval; 
/*  6774 */       if (this.state.backtracking == 0) {
/*  6775 */         IN250_tree = (CommonTree)this.adaptor.create(IN250);
/*  6776 */         root_0 = (CommonTree)this.adaptor.becomeRoot(IN250_tree, root_0);
/*       */       } 
/*  6778 */       LPAR251 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_time_constraint2093); if (this.state.failed) return retval; 
/*  6779 */       pushFollow(FOLLOW_time_value_in_time_constraint2096);
/*  6780 */       time_value252 = time_value();
/*       */       
/*  6782 */       this.state._fsp--;
/*  6783 */       if (this.state.failed) return retval; 
/*  6784 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_value252.getTree()); 
/*  6785 */       COMMA253 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_time_constraint2098); if (this.state.failed) return retval; 
/*  6786 */       pushFollow(FOLLOW_time_value_in_time_constraint2101);
/*  6787 */       time_value254 = time_value();
/*       */       
/*  6789 */       this.state._fsp--;
/*  6790 */       if (this.state.failed) return retval; 
/*  6791 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_value254.getTree()); 
/*  6792 */       RPAR255 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_time_constraint2103); if (this.state.failed) return retval;
/*       */ 
/*       */ 
/*       */       
/*  6796 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6798 */       if (this.state.backtracking == 0)
/*       */       {
/*  6800 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6801 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6804 */     } catch (RecognitionException re) {
/*  6805 */       reportError(re);
/*  6806 */       recover((IntStream)this.input, re);
/*  6807 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6812 */     return retval;
/*       */   }
/*       */   
/*       */   public static class time_value_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6818 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final time_value_return time_value() throws RecognitionException {
/*  6824 */     time_value_return retval = new time_value_return();
/*  6825 */     retval.start = this.input.LT(1);
/*       */     
/*  6827 */     CommonTree root_0 = null;
/*       */     
/*  6829 */     Token set256 = null;
/*  6830 */     time_unit_return time_unit257 = null;
/*       */ 
/*       */     
/*  6833 */     CommonTree set256_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6839 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6841 */       set256 = this.input.LT(1);
/*  6842 */       set256 = this.input.LT(1);
/*  6843 */       if (this.input.LA(1) >= 68 && this.input.LA(1) <= 69) {
/*  6844 */         this.input.consume();
/*  6845 */         if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(set256), root_0); 
/*  6846 */         this.state.errorRecovery = false; this.state.failed = false;
/*       */       } else {
/*       */         
/*  6849 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  6850 */          MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  6851 */         throw mse;
/*       */       } 
/*       */ 
/*       */       
/*  6855 */       int alt77 = 2;
/*  6856 */       int LA77_0 = this.input.LA(1);
/*       */       
/*  6858 */       if (LA77_0 >= 70 && LA77_0 <= 72) {
/*  6859 */         alt77 = 1;
/*       */       }
/*  6861 */       switch (alt77) {
/*       */ 
/*       */         
/*       */         case 1:
/*  6865 */           pushFollow(FOLLOW_time_unit_in_time_value2124);
/*  6866 */           time_unit257 = time_unit();
/*       */           
/*  6868 */           this.state._fsp--;
/*  6869 */           if (this.state.failed) return retval; 
/*  6870 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, time_unit257.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6880 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6882 */       if (this.state.backtracking == 0)
/*       */       {
/*  6884 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6885 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6888 */     } catch (RecognitionException re) {
/*  6889 */       reportError(re);
/*  6890 */       recover((IntStream)this.input, re);
/*  6891 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6896 */     return retval;
/*       */   }
/*       */   
/*       */   public static class time_unit_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6902 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final time_unit_return time_unit() throws RecognitionException {
/*  6908 */     time_unit_return retval = new time_unit_return();
/*  6909 */     retval.start = this.input.LT(1);
/*       */     
/*  6911 */     CommonTree root_0 = null;
/*       */     
/*  6913 */     Token set258 = null;
/*       */     
/*  6915 */     CommonTree set258_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6921 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6923 */       set258 = this.input.LT(1);
/*  6924 */       if (this.input.LA(1) >= 70 && this.input.LA(1) <= 72) {
/*  6925 */         this.input.consume();
/*  6926 */         if (this.state.backtracking == 0) this.adaptor.addChild(root_0, this.adaptor.create(set258)); 
/*  6927 */         this.state.errorRecovery = false; this.state.failed = false;
/*       */       } else {
/*       */         
/*  6930 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  6931 */          MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  6932 */         throw mse;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6938 */       retval.stop = this.input.LT(-1);
/*       */       
/*  6940 */       if (this.state.backtracking == 0)
/*       */       {
/*  6942 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  6943 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  6946 */     } catch (RecognitionException re) {
/*  6947 */       reportError(re);
/*  6948 */       recover((IntStream)this.input, re);
/*  6949 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  6954 */     return retval;
/*       */   }
/*       */   
/*       */   public static class time_reset_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  6960 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final time_reset_return time_reset() throws RecognitionException {
/*  6966 */     time_reset_return retval = new time_reset_return();
/*  6967 */     retval.start = this.input.LT(1);
/*       */     
/*  6969 */     CommonTree root_0 = null;
/*       */     
/*  6971 */     Token RESET259 = null;
/*  6972 */     Token IDENTIFIER260 = null;
/*  6973 */     Token COMMA261 = null;
/*  6974 */     Token IDENTIFIER262 = null;
/*       */     
/*  6976 */     CommonTree RESET259_tree = null;
/*  6977 */     CommonTree IDENTIFIER260_tree = null;
/*  6978 */     CommonTree COMMA261_tree = null;
/*  6979 */     CommonTree IDENTIFIER262_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  6985 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  6987 */       RESET259 = (Token)match((IntStream)this.input, 73, FOLLOW_RESET_in_time_reset2173); if (this.state.failed) return retval; 
/*  6988 */       if (this.state.backtracking == 0) {
/*  6989 */         RESET259_tree = (CommonTree)this.adaptor.create(RESET259);
/*  6990 */         root_0 = (CommonTree)this.adaptor.becomeRoot(RESET259_tree, root_0);
/*       */       } 
/*  6992 */       IDENTIFIER260 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_time_reset2176); if (this.state.failed) return retval; 
/*  6993 */       if (this.state.backtracking == 0) {
/*  6994 */         IDENTIFIER260_tree = (CommonTree)this.adaptor.create(IDENTIFIER260);
/*  6995 */         this.adaptor.addChild(root_0, IDENTIFIER260_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  7000 */         int alt78 = 2;
/*  7001 */         int LA78_0 = this.input.LA(1);
/*       */         
/*  7003 */         if (LA78_0 == 33) {
/*  7004 */           alt78 = 1;
/*       */         }
/*       */ 
/*       */         
/*  7008 */         switch (alt78) {
/*       */ 
/*       */           
/*       */           case 1:
/*  7012 */             COMMA261 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_time_reset2179); if (this.state.failed) return retval; 
/*  7013 */             IDENTIFIER262 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_time_reset2182); if (this.state.failed) return retval; 
/*  7014 */             if (this.state.backtracking == 0) {
/*  7015 */               IDENTIFIER262_tree = (CommonTree)this.adaptor.create(IDENTIFIER262);
/*  7016 */               this.adaptor.addChild(root_0, IDENTIFIER262_tree);
/*       */             } 
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  7030 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7032 */       if (this.state.backtracking == 0)
/*       */       {
/*  7034 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7035 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7038 */     } catch (RecognitionException re) {
/*  7039 */       reportError(re);
/*  7040 */       recover((IntStream)this.input, re);
/*  7041 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7046 */     return retval;
/*       */   }
/*       */   
/*       */   public static class do_action_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7052 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final do_action_return do_action() throws RecognitionException {
/*  7058 */     do_action_return retval = new do_action_return();
/*  7059 */     retval.start = this.input.LT(1);
/*       */     
/*  7061 */     CommonTree root_0 = null;
/*       */     
/*  7063 */     Token DO263 = null;
/*  7064 */     action_return action264 = null;
/*       */ 
/*       */     
/*  7067 */     CommonTree DO263_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7073 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7075 */       DO263 = (Token)match((IntStream)this.input, 74, FOLLOW_DO_in_do_action2214); if (this.state.failed) return retval; 
/*  7076 */       if (this.state.backtracking == 0) {
/*  7077 */         DO263_tree = (CommonTree)this.adaptor.create(DO263);
/*  7078 */         root_0 = (CommonTree)this.adaptor.becomeRoot(DO263_tree, root_0);
/*       */       } 
/*  7080 */       pushFollow(FOLLOW_action_in_do_action2217);
/*  7081 */       action264 = action();
/*       */       
/*  7083 */       this.state._fsp--;
/*  7084 */       if (this.state.failed) return retval; 
/*  7085 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, action264.getTree());
/*       */ 
/*       */ 
/*       */       
/*  7089 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7091 */       if (this.state.backtracking == 0)
/*       */       {
/*  7093 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7094 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7097 */     } catch (RecognitionException re) {
/*  7098 */       reportError(re);
/*  7099 */       recover((IntStream)this.input, re);
/*  7100 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7105 */     return retval;
/*       */   }
/*       */   
/*       */   public static class clock_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7111 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final clock_definition_return clock_definition() throws RecognitionException {
/*  7117 */     clock_definition_return retval = new clock_definition_return();
/*  7118 */     retval.start = this.input.LT(1);
/*       */     
/*  7120 */     CommonTree root_0 = null;
/*       */     
/*  7122 */     Token CLOCK265 = null;
/*  7123 */     Token IDENTIFIER266 = null;
/*  7124 */     Token COMMA267 = null;
/*  7125 */     Token IDENTIFIER268 = null;
/*       */     
/*  7127 */     CommonTree CLOCK265_tree = null;
/*  7128 */     CommonTree IDENTIFIER266_tree = null;
/*  7129 */     CommonTree COMMA267_tree = null;
/*  7130 */     CommonTree IDENTIFIER268_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7136 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7138 */       CLOCK265 = (Token)match((IntStream)this.input, 75, FOLLOW_CLOCK_in_clock_definition2241); if (this.state.failed) return retval; 
/*  7139 */       if (this.state.backtracking == 0) {
/*  7140 */         CLOCK265_tree = (CommonTree)this.adaptor.create(CLOCK265);
/*  7141 */         root_0 = (CommonTree)this.adaptor.becomeRoot(CLOCK265_tree, root_0);
/*       */       } 
/*  7143 */       IDENTIFIER266 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_clock_definition2244); if (this.state.failed) return retval; 
/*  7144 */       if (this.state.backtracking == 0) {
/*  7145 */         IDENTIFIER266_tree = (CommonTree)this.adaptor.create(IDENTIFIER266);
/*  7146 */         this.adaptor.addChild(root_0, IDENTIFIER266_tree);
/*       */       } 
/*       */ 
/*       */       
/*       */       while (true) {
/*  7151 */         int alt79 = 2;
/*  7152 */         int LA79_0 = this.input.LA(1);
/*       */         
/*  7154 */         if (LA79_0 == 33) {
/*  7155 */           alt79 = 1;
/*       */         }
/*       */ 
/*       */         
/*  7159 */         switch (alt79) {
/*       */ 
/*       */           
/*       */           case 1:
/*  7163 */             COMMA267 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_clock_definition2247); if (this.state.failed) return retval; 
/*  7164 */             IDENTIFIER268 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_clock_definition2250); if (this.state.failed) return retval; 
/*  7165 */             if (this.state.backtracking == 0) {
/*  7166 */               IDENTIFIER268_tree = (CommonTree)this.adaptor.create(IDENTIFIER268);
/*  7167 */               this.adaptor.addChild(root_0, IDENTIFIER268_tree);
/*       */             } 
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  7181 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7183 */       if (this.state.backtracking == 0)
/*       */       {
/*  7185 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7186 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7189 */     } catch (RecognitionException re) {
/*  7190 */       reportError(re);
/*  7191 */       recover((IntStream)this.input, re);
/*  7192 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7197 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7203 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_definition_return data_definition() throws RecognitionException {
/*  7209 */     data_definition_return retval = new data_definition_return();
/*  7210 */     retval.start = this.input.LT(1);
/*       */     
/*  7212 */     CommonTree root_0 = null;
/*       */     
/*  7214 */     Token EXPORT269 = null;
/*  7215 */     Token EXTERN270 = null;
/*  7216 */     Token TIMED271 = null;
/*  7217 */     Token DATA272 = null;
/*  7218 */     Token COMMA275 = null;
/*  7219 */     data_type_return data_type273 = null;
/*       */     
/*  7221 */     one_data_return one_data274 = null;
/*       */     
/*  7223 */     one_data_return one_data276 = null;
/*       */ 
/*       */     
/*  7226 */     CommonTree EXPORT269_tree = null;
/*  7227 */     CommonTree EXTERN270_tree = null;
/*  7228 */     CommonTree TIMED271_tree = null;
/*  7229 */     CommonTree DATA272_tree = null;
/*  7230 */     CommonTree COMMA275_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7236 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */       
/*  7239 */       int alt80 = 2;
/*  7240 */       int LA80_0 = this.input.LA(1);
/*       */       
/*  7242 */       if (LA80_0 == 37) {
/*  7243 */         alt80 = 1;
/*       */       }
/*  7245 */       switch (alt80) {
/*       */ 
/*       */         
/*       */         case 1:
/*  7249 */           EXPORT269 = (Token)match((IntStream)this.input, 37, FOLLOW_EXPORT_in_data_definition2276); if (this.state.failed) return retval; 
/*  7250 */           if (this.state.backtracking == 0) {
/*  7251 */             EXPORT269_tree = (CommonTree)this.adaptor.create(EXPORT269);
/*  7252 */             this.adaptor.addChild(root_0, EXPORT269_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7261 */       int alt81 = 2;
/*  7262 */       int LA81_0 = this.input.LA(1);
/*       */       
/*  7264 */       if (LA81_0 == 76) {
/*  7265 */         alt81 = 1;
/*       */       }
/*  7267 */       switch (alt81) {
/*       */ 
/*       */         
/*       */         case 1:
/*  7271 */           EXTERN270 = (Token)match((IntStream)this.input, 76, FOLLOW_EXTERN_in_data_definition2281); if (this.state.failed) return retval; 
/*  7272 */           if (this.state.backtracking == 0) {
/*  7273 */             EXTERN270_tree = (CommonTree)this.adaptor.create(EXTERN270);
/*  7274 */             this.adaptor.addChild(root_0, EXTERN270_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7283 */       int alt82 = 2;
/*  7284 */       int LA82_0 = this.input.LA(1);
/*       */       
/*  7286 */       if (LA82_0 == 77) {
/*  7287 */         alt82 = 1;
/*       */       }
/*  7289 */       switch (alt82) {
/*       */ 
/*       */         
/*       */         case 1:
/*  7293 */           TIMED271 = (Token)match((IntStream)this.input, 77, FOLLOW_TIMED_in_data_definition2286); if (this.state.failed) return retval; 
/*  7294 */           if (this.state.backtracking == 0) {
/*  7295 */             TIMED271_tree = (CommonTree)this.adaptor.create(TIMED271);
/*  7296 */             this.adaptor.addChild(root_0, TIMED271_tree);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7304 */       DATA272 = (Token)match((IntStream)this.input, 48, FOLLOW_DATA_in_data_definition2292); if (this.state.failed) return retval; 
/*  7305 */       if (this.state.backtracking == 0) {
/*  7306 */         DATA272_tree = (CommonTree)this.adaptor.create(DATA272);
/*  7307 */         root_0 = (CommonTree)this.adaptor.becomeRoot(DATA272_tree, root_0);
/*       */       } 
/*  7309 */       pushFollow(FOLLOW_data_type_in_data_definition2295);
/*  7310 */       data_type273 = data_type();
/*       */       
/*  7312 */       this.state._fsp--;
/*  7313 */       if (this.state.failed) return retval; 
/*  7314 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, data_type273.getTree()); 
/*  7315 */       pushFollow(FOLLOW_one_data_in_data_definition2297);
/*  7316 */       one_data274 = one_data();
/*       */       
/*  7318 */       this.state._fsp--;
/*  7319 */       if (this.state.failed) return retval; 
/*  7320 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, one_data274.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  7324 */         int alt83 = 2;
/*  7325 */         int LA83_0 = this.input.LA(1);
/*       */         
/*  7327 */         if (LA83_0 == 33) {
/*  7328 */           alt83 = 1;
/*       */         }
/*       */ 
/*       */         
/*  7332 */         switch (alt83) {
/*       */ 
/*       */           
/*       */           case 1:
/*  7336 */             COMMA275 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_data_definition2300); if (this.state.failed) return retval; 
/*  7337 */             pushFollow(FOLLOW_one_data_in_data_definition2304);
/*  7338 */             one_data276 = one_data();
/*       */             
/*  7340 */             this.state._fsp--;
/*  7341 */             if (this.state.failed) return retval; 
/*  7342 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, one_data276.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  7355 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7357 */       if (this.state.backtracking == 0)
/*       */       {
/*  7359 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7360 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7363 */     } catch (RecognitionException re) {
/*  7364 */       reportError(re);
/*  7365 */       recover((IntStream)this.input, re);
/*  7366 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7371 */     return retval;
/*       */   }
/*       */   
/*       */   public static class one_data_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7377 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final one_data_return one_data() throws RecognitionException {
/*  7383 */     one_data_return retval = new one_data_return();
/*  7384 */     retval.start = this.input.LT(1);
/*       */     
/*  7386 */     CommonTree root_0 = null;
/*       */     
/*  7388 */     Token IDENTIFIER277 = null;
/*  7389 */     Token ASSIGN278 = null;
/*  7390 */     expression_return expression279 = null;
/*       */ 
/*       */     
/*  7393 */     CommonTree IDENTIFIER277_tree = null;
/*  7394 */     CommonTree ASSIGN278_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7400 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7402 */       IDENTIFIER277 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_one_data2319); if (this.state.failed) return retval; 
/*  7403 */       if (this.state.backtracking == 0) {
/*  7404 */         IDENTIFIER277_tree = (CommonTree)this.adaptor.create(IDENTIFIER277);
/*  7405 */         root_0 = (CommonTree)this.adaptor.becomeRoot(IDENTIFIER277_tree, root_0);
/*       */       } 
/*       */       
/*  7408 */       int alt84 = 2;
/*  7409 */       int LA84_0 = this.input.LA(1);
/*       */       
/*  7411 */       if (LA84_0 == 58) {
/*  7412 */         alt84 = 1;
/*       */       }
/*  7414 */       switch (alt84) {
/*       */ 
/*       */         
/*       */         case 1:
/*  7418 */           ASSIGN278 = (Token)match((IntStream)this.input, 58, FOLLOW_ASSIGN_in_one_data2323); if (this.state.failed) return retval; 
/*  7419 */           pushFollow(FOLLOW_expression_in_one_data2326);
/*  7420 */           expression279 = expression();
/*       */           
/*  7422 */           this.state._fsp--;
/*  7423 */           if (this.state.failed) return retval; 
/*  7424 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression279.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7434 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7436 */       if (this.state.backtracking == 0)
/*       */       {
/*  7438 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7439 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7442 */     } catch (RecognitionException re) {
/*  7443 */       reportError(re);
/*  7444 */       recover((IntStream)this.input, re);
/*  7445 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7450 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_type_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7456 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_type_return data_type() throws RecognitionException {
/*  7462 */     data_type_return retval = new data_type_return();
/*  7463 */     retval.start = this.input.LT(1);
/*       */     
/*  7465 */     CommonTree root_0 = null;
/*       */     
/*  7467 */     Token IDENTIFIER280 = null;
/*       */     
/*  7469 */     CommonTree IDENTIFIER280_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7475 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7477 */       IDENTIFIER280 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_data_type2340); if (this.state.failed) return retval; 
/*  7478 */       if (this.state.backtracking == 0) {
/*  7479 */         IDENTIFIER280_tree = (CommonTree)this.adaptor.create(IDENTIFIER280);
/*  7480 */         this.adaptor.addChild(root_0, IDENTIFIER280_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  7485 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7487 */       if (this.state.backtracking == 0)
/*       */       {
/*  7489 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7490 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7493 */     } catch (RecognitionException re) {
/*  7494 */       reportError(re);
/*  7495 */       recover((IntStream)this.input, re);
/*  7496 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7501 */     return retval;
/*       */   }
/*       */   
/*       */   public static class data_fpar_definition_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7507 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final data_fpar_definition_return data_fpar_definition() throws RecognitionException {
/*  7513 */     data_fpar_definition_return retval = new data_fpar_definition_return();
/*  7514 */     retval.start = this.input.LT(1);
/*       */     
/*  7516 */     CommonTree root_0 = null;
/*       */     
/*  7518 */     Token IDENTIFIER282 = null;
/*  7519 */     data_type_return data_type281 = null;
/*       */ 
/*       */     
/*  7522 */     CommonTree IDENTIFIER282_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7528 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7530 */       pushFollow(FOLLOW_data_type_in_data_fpar_definition2352);
/*  7531 */       data_type281 = data_type();
/*       */       
/*  7533 */       this.state._fsp--;
/*  7534 */       if (this.state.failed) return retval; 
/*  7535 */       if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(data_type281.getTree(), root_0); 
/*  7536 */       IDENTIFIER282 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_data_fpar_definition2355); if (this.state.failed) return retval; 
/*  7537 */       if (this.state.backtracking == 0) {
/*  7538 */         IDENTIFIER282_tree = (CommonTree)this.adaptor.create(IDENTIFIER282);
/*  7539 */         this.adaptor.addChild(root_0, IDENTIFIER282_tree);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  7544 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7546 */       if (this.state.backtracking == 0)
/*       */       {
/*  7548 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7549 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7552 */     } catch (RecognitionException re) {
/*  7553 */       reportError(re);
/*  7554 */       recover((IntStream)this.input, re);
/*  7555 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7560 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7566 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_expression_return port_expression() throws RecognitionException {
/*  7572 */     port_expression_return retval = new port_expression_return();
/*  7573 */     retval.start = this.input.LT(1);
/*       */     
/*  7575 */     CommonTree root_0 = null;
/*       */     
/*  7577 */     port_additive_expression_return port_additive_expression283 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7585 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7587 */       pushFollow(FOLLOW_port_additive_expression_in_port_expression2370);
/*  7588 */       port_additive_expression283 = port_additive_expression();
/*       */       
/*  7590 */       this.state._fsp--;
/*  7591 */       if (this.state.failed) return retval; 
/*  7592 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_additive_expression283.getTree());
/*       */ 
/*       */ 
/*       */       
/*  7596 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7598 */       if (this.state.backtracking == 0)
/*       */       {
/*  7600 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7601 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7604 */     } catch (RecognitionException re) {
/*  7605 */       reportError(re);
/*  7606 */       recover((IntStream)this.input, re);
/*  7607 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7612 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_additive_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7618 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_additive_expression_return port_additive_expression() throws RecognitionException {
/*  7624 */     port_additive_expression_return retval = new port_additive_expression_return();
/*  7625 */     retval.start = this.input.LT(1);
/*       */     
/*  7627 */     CommonTree root_0 = null;
/*       */     
/*  7629 */     Token PLUS285 = null;
/*  7630 */     port_multiplicative_expression_return port_multiplicative_expression284 = null;
/*       */     
/*  7632 */     port_multiplicative_expression_return port_multiplicative_expression286 = null;
/*       */ 
/*       */     
/*  7635 */     CommonTree PLUS285_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7641 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7643 */       pushFollow(FOLLOW_port_multiplicative_expression_in_port_additive_expression2382);
/*  7644 */       port_multiplicative_expression284 = port_multiplicative_expression();
/*       */       
/*  7646 */       this.state._fsp--;
/*  7647 */       if (this.state.failed) return retval; 
/*  7648 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_multiplicative_expression284.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  7652 */         int alt85 = 2;
/*  7653 */         int LA85_0 = this.input.LA(1);
/*       */         
/*  7655 */         if (LA85_0 == 78) {
/*  7656 */           alt85 = 1;
/*       */         }
/*       */ 
/*       */         
/*  7660 */         switch (alt85) {
/*       */ 
/*       */           
/*       */           case 1:
/*  7664 */             PLUS285 = (Token)match((IntStream)this.input, 78, FOLLOW_PLUS_in_port_additive_expression2386); if (this.state.failed) return retval; 
/*  7665 */             if (this.state.backtracking == 0) {
/*  7666 */               PLUS285_tree = (CommonTree)this.adaptor.create(PLUS285);
/*  7667 */               root_0 = (CommonTree)this.adaptor.becomeRoot(PLUS285_tree, root_0);
/*       */             } 
/*  7669 */             pushFollow(FOLLOW_port_multiplicative_expression_in_port_additive_expression2389);
/*  7670 */             port_multiplicative_expression286 = port_multiplicative_expression();
/*       */             
/*  7672 */             this.state._fsp--;
/*  7673 */             if (this.state.failed) return retval; 
/*  7674 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_multiplicative_expression286.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  7687 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7689 */       if (this.state.backtracking == 0)
/*       */       {
/*  7691 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7692 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7695 */     } catch (RecognitionException re) {
/*  7696 */       reportError(re);
/*  7697 */       recover((IntStream)this.input, re);
/*  7698 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7703 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_multiplicative_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7709 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_multiplicative_expression_return port_multiplicative_expression() throws RecognitionException {
/*  7715 */     port_multiplicative_expression_return retval = new port_multiplicative_expression_return();
/*  7716 */     retval.start = this.input.LT(1);
/*       */     
/*  7718 */     CommonTree root_0 = null;
/*       */     
/*  7720 */     port_list_expression_return port_list_expression287 = null;
/*       */ 
/*       */     
/*  7723 */     RewriteRuleSubtreeStream stream_port_list_expression = new RewriteRuleSubtreeStream(this.adaptor, "rule port_list_expression");
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7728 */       pushFollow(FOLLOW_port_list_expression_in_port_multiplicative_expression2405);
/*  7729 */       port_list_expression287 = port_list_expression();
/*       */       
/*  7731 */       this.state._fsp--;
/*  7732 */       if (this.state.failed) return retval; 
/*  7733 */       if (this.state.backtracking == 0) stream_port_list_expression.add(port_list_expression287.getTree());
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7743 */       if (this.state.backtracking == 0) {
/*  7744 */         retval.tree = root_0;
/*  7745 */         RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */         
/*  7747 */         root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7752 */         CommonTree root_1 = (CommonTree)this.adaptor.nil();
/*  7753 */         root_1 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(13, "FUSION"), root_1);
/*       */         
/*  7755 */         this.adaptor.addChild(root_1, stream_port_list_expression.nextTree());
/*       */         
/*  7757 */         this.adaptor.addChild(root_0, root_1);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7762 */         retval.tree = root_0;
/*       */       } 
/*       */       
/*  7765 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7767 */       if (this.state.backtracking == 0)
/*       */       {
/*  7769 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7770 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7773 */     } catch (RecognitionException re) {
/*  7774 */       reportError(re);
/*  7775 */       recover((IntStream)this.input, re);
/*  7776 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7781 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_list_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7787 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_list_expression_return port_list_expression() throws RecognitionException {
/*  7793 */     port_list_expression_return retval = new port_list_expression_return();
/*  7794 */     retval.start = this.input.LT(1);
/*       */     
/*  7796 */     CommonTree root_0 = null;
/*       */     
/*  7798 */     port_postfix_expression_return port_postfix_expression288 = null;
/*       */     
/*  7800 */     port_postfix_expression_return port_postfix_expression289 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7808 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7810 */       pushFollow(FOLLOW_port_postfix_expression_in_port_list_expression2426);
/*  7811 */       port_postfix_expression288 = port_postfix_expression();
/*       */       
/*  7813 */       this.state._fsp--;
/*  7814 */       if (this.state.failed) return retval; 
/*  7815 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_postfix_expression288.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  7819 */         int alt86 = 2;
/*  7820 */         int LA86_0 = this.input.LA(1);
/*       */         
/*  7822 */         if (LA86_0 == 24 || LA86_0 == 52) {
/*  7823 */           alt86 = 1;
/*       */         }
/*       */ 
/*       */         
/*  7827 */         switch (alt86) {
/*       */ 
/*       */           
/*       */           case 1:
/*  7831 */             pushFollow(FOLLOW_port_postfix_expression_in_port_list_expression2430);
/*  7832 */             port_postfix_expression289 = port_postfix_expression();
/*       */             
/*  7834 */             this.state._fsp--;
/*  7835 */             if (this.state.failed) return retval; 
/*  7836 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_postfix_expression289.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  7849 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7851 */       if (this.state.backtracking == 0)
/*       */       {
/*  7853 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7854 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7857 */     } catch (RecognitionException re) {
/*  7858 */       reportError(re);
/*  7859 */       recover((IntStream)this.input, re);
/*  7860 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7865 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_postfix_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7871 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_postfix_expression_return port_postfix_expression() throws RecognitionException {
/*  7877 */     port_postfix_expression_return retval = new port_postfix_expression_return();
/*  7878 */     retval.start = this.input.LT(1);
/*       */     
/*  7880 */     CommonTree root_0 = null;
/*       */     
/*  7882 */     Token QUOTE291 = null;
/*  7883 */     port_primary_expression_return port_primary_expression290 = null;
/*       */ 
/*       */     
/*  7886 */     CommonTree QUOTE291_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  7892 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  7894 */       pushFollow(FOLLOW_port_primary_expression_in_port_postfix_expression2446);
/*  7895 */       port_primary_expression290 = port_primary_expression();
/*       */       
/*  7897 */       this.state._fsp--;
/*  7898 */       if (this.state.failed) return retval; 
/*  7899 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_primary_expression290.getTree());
/*       */       
/*  7901 */       int alt87 = 2;
/*  7902 */       int LA87_0 = this.input.LA(1);
/*       */       
/*  7904 */       if (LA87_0 == 79) {
/*  7905 */         alt87 = 1;
/*       */       }
/*  7907 */       switch (alt87) {
/*       */ 
/*       */         
/*       */         case 1:
/*  7911 */           QUOTE291 = (Token)match((IntStream)this.input, 79, FOLLOW_QUOTE_in_port_postfix_expression2449); if (this.state.failed) return retval; 
/*  7912 */           if (this.state.backtracking == 0) {
/*  7913 */             QUOTE291_tree = (CommonTree)this.adaptor.create(QUOTE291);
/*  7914 */             root_0 = (CommonTree)this.adaptor.becomeRoot(QUOTE291_tree, root_0);
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7925 */       retval.stop = this.input.LT(-1);
/*       */       
/*  7927 */       if (this.state.backtracking == 0)
/*       */       {
/*  7929 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  7930 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  7933 */     } catch (RecognitionException re) {
/*  7934 */       reportError(re);
/*  7935 */       recover((IntStream)this.input, re);
/*  7936 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  7941 */     return retval;
/*       */   }
/*       */   
/*       */   public static class port_primary_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  7947 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final port_primary_expression_return port_primary_expression() throws RecognitionException {
/*  7953 */     port_primary_expression_return retval = new port_primary_expression_return();
/*  7954 */     retval.start = this.input.LT(1);
/*       */     
/*  7956 */     CommonTree root_0 = null;
/*       */     
/*  7958 */     Token LBRACKET293 = null;
/*  7959 */     Token RBRACKET295 = null;
/*  7960 */     port_return port292 = null;
/*       */     
/*  7962 */     port_expression_return port_expression294 = null;
/*       */ 
/*       */     
/*  7965 */     CommonTree LBRACKET293_tree = null;
/*  7966 */     CommonTree RBRACKET295_tree = null;
/*       */ 
/*       */     
/*       */     try {
/*  7970 */       int alt88 = 2;
/*  7971 */       int LA88_0 = this.input.LA(1);
/*       */       
/*  7973 */       if (LA88_0 == 24) {
/*  7974 */         alt88 = 1;
/*       */       }
/*  7976 */       else if (LA88_0 == 52) {
/*  7977 */         alt88 = 2;
/*       */       } else {
/*       */         
/*  7980 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  7981 */          NoViableAltException nvae = new NoViableAltException("", 88, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  7984 */         throw nvae;
/*       */       } 
/*  7986 */       switch (alt88) {
/*       */ 
/*       */         
/*       */         case 1:
/*  7990 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  7992 */           pushFollow(FOLLOW_port_in_port_primary_expression2466);
/*  7993 */           port292 = port();
/*       */           
/*  7995 */           this.state._fsp--;
/*  7996 */           if (this.state.failed) return retval; 
/*  7997 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port292.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  8004 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  8006 */           LBRACKET293 = (Token)match((IntStream)this.input, 52, FOLLOW_LBRACKET_in_port_primary_expression2471); if (this.state.failed) return retval; 
/*  8007 */           pushFollow(FOLLOW_port_expression_in_port_primary_expression2474);
/*  8008 */           port_expression294 = port_expression();
/*       */           
/*  8010 */           this.state._fsp--;
/*  8011 */           if (this.state.failed) return retval; 
/*  8012 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, port_expression294.getTree()); 
/*  8013 */           RBRACKET295 = (Token)match((IntStream)this.input, 53, FOLLOW_RBRACKET_in_port_primary_expression2476); if (this.state.failed) return retval;
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*  8019 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8021 */       if (this.state.backtracking == 0)
/*       */       {
/*  8023 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8024 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8027 */     } catch (RecognitionException re) {
/*  8028 */       reportError(re);
/*  8029 */       recover((IntStream)this.input, re);
/*  8030 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8035 */     return retval;
/*       */   }
/*       */   
/*       */   public static class expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8041 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final expression_return expression() throws RecognitionException {
/*  8047 */     expression_return retval = new expression_return();
/*  8048 */     retval.start = this.input.LT(1);
/*       */     
/*  8050 */     CommonTree root_0 = null;
/*       */     
/*  8052 */     logical_or_expression_return logical_or_expression296 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8060 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8062 */       pushFollow(FOLLOW_logical_or_expression_in_expression2493);
/*  8063 */       logical_or_expression296 = logical_or_expression();
/*       */       
/*  8065 */       this.state._fsp--;
/*  8066 */       if (this.state.failed) return retval; 
/*  8067 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, logical_or_expression296.getTree());
/*       */ 
/*       */ 
/*       */       
/*  8071 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8073 */       if (this.state.backtracking == 0)
/*       */       {
/*  8075 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8076 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8079 */     } catch (RecognitionException re) {
/*  8080 */       reportError(re);
/*  8081 */       recover((IntStream)this.input, re);
/*  8082 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8087 */     return retval;
/*       */   }
/*       */   
/*       */   public static class logical_or_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8093 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final logical_or_expression_return logical_or_expression() throws RecognitionException {
/*  8099 */     logical_or_expression_return retval = new logical_or_expression_return();
/*  8100 */     retval.start = this.input.LT(1);
/*       */     
/*  8102 */     CommonTree root_0 = null;
/*       */     
/*  8104 */     Token OR298 = null;
/*  8105 */     logical_and_expression_return logical_and_expression297 = null;
/*       */     
/*  8107 */     logical_and_expression_return logical_and_expression299 = null;
/*       */ 
/*       */     
/*  8110 */     CommonTree OR298_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8116 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8118 */       pushFollow(FOLLOW_logical_and_expression_in_logical_or_expression2503);
/*  8119 */       logical_and_expression297 = logical_and_expression();
/*       */       
/*  8121 */       this.state._fsp--;
/*  8122 */       if (this.state.failed) return retval; 
/*  8123 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, logical_and_expression297.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8127 */         int alt89 = 2;
/*  8128 */         int LA89_0 = this.input.LA(1);
/*       */         
/*  8130 */         if (LA89_0 == 80) {
/*  8131 */           alt89 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8135 */         switch (alt89) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8139 */             OR298 = (Token)match((IntStream)this.input, 80, FOLLOW_OR_in_logical_or_expression2506); if (this.state.failed) return retval; 
/*  8140 */             if (this.state.backtracking == 0) {
/*  8141 */               OR298_tree = (CommonTree)this.adaptor.create(OR298);
/*  8142 */               root_0 = (CommonTree)this.adaptor.becomeRoot(OR298_tree, root_0);
/*       */             } 
/*  8144 */             pushFollow(FOLLOW_logical_and_expression_in_logical_or_expression2509);
/*  8145 */             logical_and_expression299 = logical_and_expression();
/*       */             
/*  8147 */             this.state._fsp--;
/*  8148 */             if (this.state.failed) return retval; 
/*  8149 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, logical_and_expression299.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8162 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8164 */       if (this.state.backtracking == 0)
/*       */       {
/*  8166 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8167 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8170 */     } catch (RecognitionException re) {
/*  8171 */       reportError(re);
/*  8172 */       recover((IntStream)this.input, re);
/*  8173 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8178 */     return retval;
/*       */   }
/*       */   
/*       */   public static class logical_and_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8184 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final logical_and_expression_return logical_and_expression() throws RecognitionException {
/*  8190 */     logical_and_expression_return retval = new logical_and_expression_return();
/*  8191 */     retval.start = this.input.LT(1);
/*       */     
/*  8193 */     CommonTree root_0 = null;
/*       */     
/*  8195 */     Token AND301 = null;
/*  8196 */     bitwise_inclusive_or_expression_return bitwise_inclusive_or_expression300 = null;
/*       */     
/*  8198 */     bitwise_inclusive_or_expression_return bitwise_inclusive_or_expression302 = null;
/*       */ 
/*       */     
/*  8201 */     CommonTree AND301_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8207 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8209 */       pushFollow(FOLLOW_bitwise_inclusive_or_expression_in_logical_and_expression2523);
/*  8210 */       bitwise_inclusive_or_expression300 = bitwise_inclusive_or_expression();
/*       */       
/*  8212 */       this.state._fsp--;
/*  8213 */       if (this.state.failed) return retval; 
/*  8214 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_inclusive_or_expression300.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8218 */         int alt90 = 2;
/*  8219 */         int LA90_0 = this.input.LA(1);
/*       */         
/*  8221 */         if (LA90_0 == 66) {
/*  8222 */           alt90 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8226 */         switch (alt90) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8230 */             AND301 = (Token)match((IntStream)this.input, 66, FOLLOW_AND_in_logical_and_expression2526); if (this.state.failed) return retval; 
/*  8231 */             if (this.state.backtracking == 0) {
/*  8232 */               AND301_tree = (CommonTree)this.adaptor.create(AND301);
/*  8233 */               root_0 = (CommonTree)this.adaptor.becomeRoot(AND301_tree, root_0);
/*       */             } 
/*  8235 */             pushFollow(FOLLOW_bitwise_inclusive_or_expression_in_logical_and_expression2529);
/*  8236 */             bitwise_inclusive_or_expression302 = bitwise_inclusive_or_expression();
/*       */             
/*  8238 */             this.state._fsp--;
/*  8239 */             if (this.state.failed) return retval; 
/*  8240 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_inclusive_or_expression302.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8253 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8255 */       if (this.state.backtracking == 0)
/*       */       {
/*  8257 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8258 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8261 */     } catch (RecognitionException re) {
/*  8262 */       reportError(re);
/*  8263 */       recover((IntStream)this.input, re);
/*  8264 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8269 */     return retval;
/*       */   }
/*       */   
/*       */   public static class bitwise_inclusive_or_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8275 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final bitwise_inclusive_or_expression_return bitwise_inclusive_or_expression() throws RecognitionException {
/*  8281 */     bitwise_inclusive_or_expression_return retval = new bitwise_inclusive_or_expression_return();
/*  8282 */     retval.start = this.input.LT(1);
/*       */     
/*  8284 */     CommonTree root_0 = null;
/*       */     
/*  8286 */     Token BITWISEOR304 = null;
/*  8287 */     bitwise_exclusive_or_expression_return bitwise_exclusive_or_expression303 = null;
/*       */     
/*  8289 */     bitwise_exclusive_or_expression_return bitwise_exclusive_or_expression305 = null;
/*       */ 
/*       */     
/*  8292 */     CommonTree BITWISEOR304_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8298 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8300 */       pushFollow(FOLLOW_bitwise_exclusive_or_expression_in_bitwise_inclusive_or_expression2543);
/*  8301 */       bitwise_exclusive_or_expression303 = bitwise_exclusive_or_expression();
/*       */       
/*  8303 */       this.state._fsp--;
/*  8304 */       if (this.state.failed) return retval; 
/*  8305 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_exclusive_or_expression303.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8309 */         int alt91 = 2;
/*  8310 */         int LA91_0 = this.input.LA(1);
/*       */         
/*  8312 */         if (LA91_0 == 81) {
/*  8313 */           alt91 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8317 */         switch (alt91) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8321 */             BITWISEOR304 = (Token)match((IntStream)this.input, 81, FOLLOW_BITWISEOR_in_bitwise_inclusive_or_expression2546); if (this.state.failed) return retval; 
/*  8322 */             if (this.state.backtracking == 0) {
/*  8323 */               BITWISEOR304_tree = (CommonTree)this.adaptor.create(BITWISEOR304);
/*  8324 */               root_0 = (CommonTree)this.adaptor.becomeRoot(BITWISEOR304_tree, root_0);
/*       */             } 
/*  8326 */             pushFollow(FOLLOW_bitwise_exclusive_or_expression_in_bitwise_inclusive_or_expression2549);
/*  8327 */             bitwise_exclusive_or_expression305 = bitwise_exclusive_or_expression();
/*       */             
/*  8329 */             this.state._fsp--;
/*  8330 */             if (this.state.failed) return retval; 
/*  8331 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_exclusive_or_expression305.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8344 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8346 */       if (this.state.backtracking == 0)
/*       */       {
/*  8348 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8349 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8352 */     } catch (RecognitionException re) {
/*  8353 */       reportError(re);
/*  8354 */       recover((IntStream)this.input, re);
/*  8355 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8360 */     return retval;
/*       */   }
/*       */   
/*       */   public static class bitwise_exclusive_or_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8366 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final bitwise_exclusive_or_expression_return bitwise_exclusive_or_expression() throws RecognitionException {
/*  8372 */     bitwise_exclusive_or_expression_return retval = new bitwise_exclusive_or_expression_return();
/*  8373 */     retval.start = this.input.LT(1);
/*       */     
/*  8375 */     CommonTree root_0 = null;
/*       */     
/*  8377 */     Token BITWISEXOR307 = null;
/*  8378 */     bitwise_and_expression_return bitwise_and_expression306 = null;
/*       */     
/*  8380 */     bitwise_and_expression_return bitwise_and_expression308 = null;
/*       */ 
/*       */     
/*  8383 */     CommonTree BITWISEXOR307_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8389 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8391 */       pushFollow(FOLLOW_bitwise_and_expression_in_bitwise_exclusive_or_expression2564);
/*  8392 */       bitwise_and_expression306 = bitwise_and_expression();
/*       */       
/*  8394 */       this.state._fsp--;
/*  8395 */       if (this.state.failed) return retval; 
/*  8396 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_and_expression306.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8400 */         int alt92 = 2;
/*  8401 */         int LA92_0 = this.input.LA(1);
/*       */         
/*  8403 */         if (LA92_0 == 82) {
/*  8404 */           alt92 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8408 */         switch (alt92) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8412 */             BITWISEXOR307 = (Token)match((IntStream)this.input, 82, FOLLOW_BITWISEXOR_in_bitwise_exclusive_or_expression2567); if (this.state.failed) return retval; 
/*  8413 */             if (this.state.backtracking == 0) {
/*  8414 */               BITWISEXOR307_tree = (CommonTree)this.adaptor.create(BITWISEXOR307);
/*  8415 */               root_0 = (CommonTree)this.adaptor.becomeRoot(BITWISEXOR307_tree, root_0);
/*       */             } 
/*  8417 */             pushFollow(FOLLOW_bitwise_and_expression_in_bitwise_exclusive_or_expression2570);
/*  8418 */             bitwise_and_expression308 = bitwise_and_expression();
/*       */             
/*  8420 */             this.state._fsp--;
/*  8421 */             if (this.state.failed) return retval; 
/*  8422 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_and_expression308.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8435 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8437 */       if (this.state.backtracking == 0)
/*       */       {
/*  8439 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8440 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8443 */     } catch (RecognitionException re) {
/*  8444 */       reportError(re);
/*  8445 */       recover((IntStream)this.input, re);
/*  8446 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8451 */     return retval;
/*       */   }
/*       */   
/*       */   public static class bitwise_and_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8457 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final bitwise_and_expression_return bitwise_and_expression() throws RecognitionException {
/*  8463 */     bitwise_and_expression_return retval = new bitwise_and_expression_return();
/*  8464 */     retval.start = this.input.LT(1);
/*       */     
/*  8466 */     CommonTree root_0 = null;
/*       */     
/*  8468 */     Token REF310 = null;
/*  8469 */     equality_expression_return equality_expression309 = null;
/*       */     
/*  8471 */     equality_expression_return equality_expression311 = null;
/*       */ 
/*       */     
/*  8474 */     CommonTree REF310_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8480 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8482 */       pushFollow(FOLLOW_equality_expression_in_bitwise_and_expression2584);
/*  8483 */       equality_expression309 = equality_expression();
/*       */       
/*  8485 */       this.state._fsp--;
/*  8486 */       if (this.state.failed) return retval; 
/*  8487 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, equality_expression309.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8491 */         int alt93 = 2;
/*  8492 */         int LA93_0 = this.input.LA(1);
/*       */         
/*  8494 */         if (LA93_0 == 83) {
/*  8495 */           alt93 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8499 */         switch (alt93) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8503 */             REF310 = (Token)match((IntStream)this.input, 83, FOLLOW_REF_in_bitwise_and_expression2587); if (this.state.failed) return retval; 
/*  8504 */             if (this.state.backtracking == 0) {
/*  8505 */               REF310_tree = (CommonTree)this.adaptor.create(REF310);
/*  8506 */               root_0 = (CommonTree)this.adaptor.becomeRoot(REF310_tree, root_0);
/*       */             } 
/*  8508 */             pushFollow(FOLLOW_equality_expression_in_bitwise_and_expression2590);
/*  8509 */             equality_expression311 = equality_expression();
/*       */             
/*  8511 */             this.state._fsp--;
/*  8512 */             if (this.state.failed) return retval; 
/*  8513 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, equality_expression311.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8526 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8528 */       if (this.state.backtracking == 0)
/*       */       {
/*  8530 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8531 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8534 */     } catch (RecognitionException re) {
/*  8535 */       reportError(re);
/*  8536 */       recover((IntStream)this.input, re);
/*  8537 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8542 */     return retval;
/*       */   }
/*       */   
/*       */   public static class equality_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8548 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final equality_expression_return equality_expression() throws RecognitionException {
/*  8554 */     equality_expression_return retval = new equality_expression_return();
/*  8555 */     retval.start = this.input.LT(1);
/*       */     
/*  8557 */     CommonTree root_0 = null;
/*       */     
/*  8559 */     Token set313 = null;
/*  8560 */     relational_expression_return relational_expression312 = null;
/*       */     
/*  8562 */     relational_expression_return relational_expression314 = null;
/*       */ 
/*       */     
/*  8565 */     CommonTree set313_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8571 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8573 */       pushFollow(FOLLOW_relational_expression_in_equality_expression2606);
/*  8574 */       relational_expression312 = relational_expression();
/*       */       
/*  8576 */       this.state._fsp--;
/*  8577 */       if (this.state.failed) return retval; 
/*  8578 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, relational_expression312.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8582 */         int alt94 = 2;
/*  8583 */         int LA94_0 = this.input.LA(1);
/*       */         
/*  8585 */         if (LA94_0 >= 84 && LA94_0 <= 85) {
/*  8586 */           alt94 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8590 */         switch (alt94) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8594 */             set313 = this.input.LT(1);
/*  8595 */             set313 = this.input.LT(1);
/*  8596 */             if (this.input.LA(1) >= 84 && this.input.LA(1) <= 85) {
/*  8597 */               this.input.consume();
/*  8598 */               if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(set313), root_0); 
/*  8599 */               this.state.errorRecovery = false; this.state.failed = false;
/*       */             } else {
/*       */               
/*  8602 */               if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  8603 */                MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  8604 */               throw mse;
/*       */             } 
/*       */             
/*  8607 */             pushFollow(FOLLOW_relational_expression_in_equality_expression2616);
/*  8608 */             relational_expression314 = relational_expression();
/*       */             
/*  8610 */             this.state._fsp--;
/*  8611 */             if (this.state.failed) return retval; 
/*  8612 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, relational_expression314.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8625 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8627 */       if (this.state.backtracking == 0)
/*       */       {
/*  8629 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8630 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8633 */     } catch (RecognitionException re) {
/*  8634 */       reportError(re);
/*  8635 */       recover((IntStream)this.input, re);
/*  8636 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8641 */     return retval;
/*       */   }
/*       */   
/*       */   public static class relational_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8647 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final relational_expression_return relational_expression() throws RecognitionException {
/*  8653 */     relational_expression_return retval = new relational_expression_return();
/*  8654 */     retval.start = this.input.LT(1);
/*       */     
/*  8656 */     CommonTree root_0 = null;
/*       */     
/*  8658 */     Token set316 = null;
/*  8659 */     bitwise_shift_expression_return bitwise_shift_expression315 = null;
/*       */     
/*  8661 */     bitwise_shift_expression_return bitwise_shift_expression317 = null;
/*       */ 
/*       */     
/*  8664 */     CommonTree set316_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8670 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8672 */       pushFollow(FOLLOW_bitwise_shift_expression_in_relational_expression2630);
/*  8673 */       bitwise_shift_expression315 = bitwise_shift_expression();
/*       */       
/*  8675 */       this.state._fsp--;
/*  8676 */       if (this.state.failed) return retval; 
/*  8677 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_shift_expression315.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8681 */         int alt95 = 2;
/*  8682 */         int LA95_0 = this.input.LA(1);
/*       */         
/*  8684 */         if (LA95_0 == 56 || (LA95_0 >= 86 && LA95_0 <= 88)) {
/*  8685 */           alt95 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8689 */         switch (alt95) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8693 */             set316 = this.input.LT(1);
/*  8694 */             set316 = this.input.LT(1);
/*  8695 */             if (this.input.LA(1) == 56 || (this.input.LA(1) >= 86 && this.input.LA(1) <= 88)) {
/*  8696 */               this.input.consume();
/*  8697 */               if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(set316), root_0); 
/*  8698 */               this.state.errorRecovery = false; this.state.failed = false;
/*       */             } else {
/*       */               
/*  8701 */               if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  8702 */                MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  8703 */               throw mse;
/*       */             } 
/*       */             
/*  8706 */             pushFollow(FOLLOW_bitwise_shift_expression_in_relational_expression2644);
/*  8707 */             bitwise_shift_expression317 = bitwise_shift_expression();
/*       */             
/*  8709 */             this.state._fsp--;
/*  8710 */             if (this.state.failed) return retval; 
/*  8711 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, bitwise_shift_expression317.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8724 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8726 */       if (this.state.backtracking == 0)
/*       */       {
/*  8728 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8729 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8732 */     } catch (RecognitionException re) {
/*  8733 */       reportError(re);
/*  8734 */       recover((IntStream)this.input, re);
/*  8735 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8740 */     return retval;
/*       */   }
/*       */   
/*       */   public static class bitwise_shift_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8746 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final bitwise_shift_expression_return bitwise_shift_expression() throws RecognitionException {
/*  8752 */     bitwise_shift_expression_return retval = new bitwise_shift_expression_return();
/*  8753 */     retval.start = this.input.LT(1);
/*       */     
/*  8755 */     CommonTree root_0 = null;
/*       */     
/*  8757 */     Token set319 = null;
/*  8758 */     additive_expression_return additive_expression318 = null;
/*       */     
/*  8760 */     additive_expression_return additive_expression320 = null;
/*       */ 
/*       */     
/*  8763 */     CommonTree set319_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8769 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8771 */       pushFollow(FOLLOW_additive_expression_in_bitwise_shift_expression2670);
/*  8772 */       additive_expression318 = additive_expression();
/*       */       
/*  8774 */       this.state._fsp--;
/*  8775 */       if (this.state.failed) return retval; 
/*  8776 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, additive_expression318.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8780 */         int alt96 = 2;
/*  8781 */         int LA96_0 = this.input.LA(1);
/*       */         
/*  8783 */         if (LA96_0 >= 89 && LA96_0 <= 90) {
/*  8784 */           alt96 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8788 */         switch (alt96) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8792 */             set319 = this.input.LT(1);
/*  8793 */             set319 = this.input.LT(1);
/*  8794 */             if (this.input.LA(1) >= 89 && this.input.LA(1) <= 90) {
/*  8795 */               this.input.consume();
/*  8796 */               if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(set319), root_0); 
/*  8797 */               this.state.errorRecovery = false; this.state.failed = false;
/*       */             } else {
/*       */               
/*  8800 */               if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  8801 */                MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  8802 */               throw mse;
/*       */             } 
/*       */             
/*  8805 */             pushFollow(FOLLOW_additive_expression_in_bitwise_shift_expression2680);
/*  8806 */             additive_expression320 = additive_expression();
/*       */             
/*  8808 */             this.state._fsp--;
/*  8809 */             if (this.state.failed) return retval; 
/*  8810 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, additive_expression320.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8823 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8825 */       if (this.state.backtracking == 0)
/*       */       {
/*  8827 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8828 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8831 */     } catch (RecognitionException re) {
/*  8832 */       reportError(re);
/*  8833 */       recover((IntStream)this.input, re);
/*  8834 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8839 */     return retval;
/*       */   }
/*       */   
/*       */   public static class additive_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8845 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final additive_expression_return additive_expression() throws RecognitionException {
/*  8851 */     additive_expression_return retval = new additive_expression_return();
/*  8852 */     retval.start = this.input.LT(1);
/*       */     
/*  8854 */     CommonTree root_0 = null;
/*       */     
/*  8856 */     Token set322 = null;
/*  8857 */     multiplicative_expression_return multiplicative_expression321 = null;
/*       */     
/*  8859 */     multiplicative_expression_return multiplicative_expression323 = null;
/*       */ 
/*       */     
/*  8862 */     CommonTree set322_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8868 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8870 */       pushFollow(FOLLOW_multiplicative_expression_in_additive_expression2702);
/*  8871 */       multiplicative_expression321 = multiplicative_expression();
/*       */       
/*  8873 */       this.state._fsp--;
/*  8874 */       if (this.state.failed) return retval; 
/*  8875 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, multiplicative_expression321.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8879 */         int alt97 = 2;
/*  8880 */         int LA97_0 = this.input.LA(1);
/*       */         
/*  8882 */         if (LA97_0 == 78 || LA97_0 == 91) {
/*  8883 */           alt97 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8887 */         switch (alt97) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8891 */             set322 = this.input.LT(1);
/*  8892 */             set322 = this.input.LT(1);
/*  8893 */             if (this.input.LA(1) == 78 || this.input.LA(1) == 91) {
/*  8894 */               this.input.consume();
/*  8895 */               if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(set322), root_0); 
/*  8896 */               this.state.errorRecovery = false; this.state.failed = false;
/*       */             } else {
/*       */               
/*  8899 */               if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  8900 */                MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  8901 */               throw mse;
/*       */             } 
/*       */             
/*  8904 */             pushFollow(FOLLOW_multiplicative_expression_in_additive_expression2712);
/*  8905 */             multiplicative_expression323 = multiplicative_expression();
/*       */             
/*  8907 */             this.state._fsp--;
/*  8908 */             if (this.state.failed) return retval; 
/*  8909 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, multiplicative_expression323.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  8922 */       retval.stop = this.input.LT(-1);
/*       */       
/*  8924 */       if (this.state.backtracking == 0)
/*       */       {
/*  8926 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  8927 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  8930 */     } catch (RecognitionException re) {
/*  8931 */       reportError(re);
/*  8932 */       recover((IntStream)this.input, re);
/*  8933 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  8938 */     return retval;
/*       */   }
/*       */   
/*       */   public static class multiplicative_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  8944 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final multiplicative_expression_return multiplicative_expression() throws RecognitionException {
/*  8950 */     multiplicative_expression_return retval = new multiplicative_expression_return();
/*  8951 */     retval.start = this.input.LT(1);
/*       */     
/*  8953 */     CommonTree root_0 = null;
/*       */     
/*  8955 */     Token set325 = null;
/*  8956 */     infix_expression_return infix_expression324 = null;
/*       */     
/*  8958 */     infix_expression_return infix_expression326 = null;
/*       */ 
/*       */     
/*  8961 */     CommonTree set325_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  8967 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  8969 */       pushFollow(FOLLOW_infix_expression_in_multiplicative_expression2726);
/*  8970 */       infix_expression324 = infix_expression();
/*       */       
/*  8972 */       this.state._fsp--;
/*  8973 */       if (this.state.failed) return retval; 
/*  8974 */       if (this.state.backtracking == 0) this.adaptor.addChild(root_0, infix_expression324.getTree());
/*       */ 
/*       */       
/*       */       while (true) {
/*  8978 */         int alt98 = 2;
/*  8979 */         int LA98_0 = this.input.LA(1);
/*       */         
/*  8981 */         if (LA98_0 == 57 || (LA98_0 >= 92 && LA98_0 <= 93)) {
/*  8982 */           alt98 = 1;
/*       */         }
/*       */ 
/*       */         
/*  8986 */         switch (alt98) {
/*       */ 
/*       */           
/*       */           case 1:
/*  8990 */             set325 = this.input.LT(1);
/*  8991 */             set325 = this.input.LT(1);
/*  8992 */             if (this.input.LA(1) == 57 || (this.input.LA(1) >= 92 && this.input.LA(1) <= 93)) {
/*  8993 */               this.input.consume();
/*  8994 */               if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(this.adaptor.create(set325), root_0); 
/*  8995 */               this.state.errorRecovery = false; this.state.failed = false;
/*       */             } else {
/*       */               
/*  8998 */               if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  8999 */                MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  9000 */               throw mse;
/*       */             } 
/*       */             
/*  9003 */             pushFollow(FOLLOW_infix_expression_in_multiplicative_expression2738);
/*  9004 */             infix_expression326 = infix_expression();
/*       */             
/*  9006 */             this.state._fsp--;
/*  9007 */             if (this.state.failed) return retval; 
/*  9008 */             if (this.state.backtracking == 0) this.adaptor.addChild(root_0, infix_expression326.getTree());
/*       */             
/*       */             continue;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         break;
/*       */       } 
/*  9021 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9023 */       if (this.state.backtracking == 0)
/*       */       {
/*  9025 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9026 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9029 */     } catch (RecognitionException re) {
/*  9030 */       reportError(re);
/*  9031 */       recover((IntStream)this.input, re);
/*  9032 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9037 */     return retval;
/*       */   }
/*       */   
/*       */   public static class infix_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  9043 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final infix_expression_return infix_expression() throws RecognitionException {
/*  9049 */     infix_expression_return retval = new infix_expression_return();
/*  9050 */     retval.start = this.input.LT(1);
/*       */     
/*  9052 */     CommonTree root_0 = null;
/*       */     
/*  9054 */     unary_op_return unary_op327 = null;
/*       */     
/*  9056 */     infix_expression_return infix_expression328 = null;
/*       */     
/*  9058 */     postfix_expression_return postfix_expression329 = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  9064 */       int alt99 = 2;
/*  9065 */       int LA99_0 = this.input.LA(1);
/*       */       
/*  9067 */       if (LA99_0 == 57 || LA99_0 == 78 || LA99_0 == 83 || LA99_0 == 91 || (LA99_0 >= 94 && LA99_0 <= 97)) {
/*  9068 */         alt99 = 1;
/*       */       }
/*  9070 */       else if (LA99_0 == 24 || LA99_0 == 32 || LA99_0 == 68 || (LA99_0 >= 99 && LA99_0 <= 101)) {
/*  9071 */         alt99 = 2;
/*       */       } else {
/*       */         
/*  9074 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  9075 */          NoViableAltException nvae = new NoViableAltException("", 99, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  9078 */         throw nvae;
/*       */       } 
/*  9080 */       switch (alt99) {
/*       */ 
/*       */         
/*       */         case 1:
/*  9084 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9086 */           pushFollow(FOLLOW_unary_op_in_infix_expression2751);
/*  9087 */           unary_op327 = unary_op();
/*       */           
/*  9089 */           this.state._fsp--;
/*  9090 */           if (this.state.failed) return retval; 
/*  9091 */           if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(unary_op327.getTree(), root_0); 
/*  9092 */           pushFollow(FOLLOW_infix_expression_in_infix_expression2754);
/*  9093 */           infix_expression328 = infix_expression();
/*       */           
/*  9095 */           this.state._fsp--;
/*  9096 */           if (this.state.failed) return retval; 
/*  9097 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, infix_expression328.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  9104 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9106 */           pushFollow(FOLLOW_postfix_expression_in_infix_expression2767);
/*  9107 */           postfix_expression329 = postfix_expression();
/*       */           
/*  9109 */           this.state._fsp--;
/*  9110 */           if (this.state.failed) return retval; 
/*  9111 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, postfix_expression329.getTree());
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*  9117 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9119 */       if (this.state.backtracking == 0)
/*       */       {
/*  9121 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9122 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9125 */     } catch (RecognitionException re) {
/*  9126 */       reportError(re);
/*  9127 */       recover((IntStream)this.input, re);
/*  9128 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9133 */     return retval;
/*       */   }
/*       */   
/*       */   public static class unary_op_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  9139 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final unary_op_return unary_op() throws RecognitionException {
/*  9145 */     unary_op_return retval = new unary_op_return();
/*  9146 */     retval.start = this.input.LT(1);
/*       */     
/*  9148 */     CommonTree root_0 = null;
/*       */     
/*  9150 */     Token PLUS330 = null;
/*  9151 */     Token MINUS331 = null;
/*  9152 */     Token NOT332 = null;
/*  9153 */     Token BITWISENOT333 = null;
/*  9154 */     Token MULT334 = null;
/*  9155 */     Token REF335 = null;
/*  9156 */     Token INCREMENT336 = null;
/*  9157 */     Token DECREMENT337 = null;
/*       */     
/*  9159 */     CommonTree PLUS330_tree = null;
/*  9160 */     CommonTree MINUS331_tree = null;
/*  9161 */     CommonTree NOT332_tree = null;
/*  9162 */     CommonTree BITWISENOT333_tree = null;
/*  9163 */     CommonTree MULT334_tree = null;
/*  9164 */     CommonTree REF335_tree = null;
/*  9165 */     CommonTree INCREMENT336_tree = null;
/*  9166 */     CommonTree DECREMENT337_tree = null;
/*  9167 */     RewriteRuleTokenStream stream_INCREMENT = new RewriteRuleTokenStream(this.adaptor, "token INCREMENT");
/*  9168 */     RewriteRuleTokenStream stream_PLUS = new RewriteRuleTokenStream(this.adaptor, "token PLUS");
/*  9169 */     RewriteRuleTokenStream stream_MULT = new RewriteRuleTokenStream(this.adaptor, "token MULT");
/*  9170 */     RewriteRuleTokenStream stream_MINUS = new RewriteRuleTokenStream(this.adaptor, "token MINUS");
/*  9171 */     RewriteRuleTokenStream stream_DECREMENT = new RewriteRuleTokenStream(this.adaptor, "token DECREMENT");
/*  9172 */     RewriteRuleTokenStream stream_REF = new RewriteRuleTokenStream(this.adaptor, "token REF");
/*       */     
/*       */     try {
/*       */       NoViableAltException nvae;
/*  9176 */       int alt100 = 8;
/*  9177 */       switch (this.input.LA(1)) {
/*       */         
/*       */         case 78:
/*  9180 */           alt100 = 1;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 91:
/*  9185 */           alt100 = 2;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 94:
/*  9190 */           alt100 = 3;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 95:
/*  9195 */           alt100 = 4;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 57:
/*  9200 */           alt100 = 5;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 83:
/*  9205 */           alt100 = 6;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 96:
/*  9210 */           alt100 = 7;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 97:
/*  9215 */           alt100 = 8;
/*       */           break;
/*       */         
/*       */         default:
/*  9219 */           if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  9220 */            nvae = new NoViableAltException("", 100, 0, (IntStream)this.input);
/*       */ 
/*       */           
/*  9223 */           throw nvae;
/*       */       } 
/*       */       
/*  9226 */       switch (alt100) {
/*       */ 
/*       */         
/*       */         case 1:
/*  9230 */           PLUS330 = (Token)match((IntStream)this.input, 78, FOLLOW_PLUS_in_unary_op2781); if (this.state.failed) return retval; 
/*  9231 */           if (this.state.backtracking == 0) stream_PLUS.add(PLUS330);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9242 */           if (this.state.backtracking == 0) {
/*  9243 */             retval.tree = root_0;
/*  9244 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9246 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9249 */             this.adaptor.addChild(root_0, this.adaptor.create(4, "UNARY_PLUS"));
/*       */ 
/*       */ 
/*       */             
/*  9253 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 2:
/*  9259 */           MINUS331 = (Token)match((IntStream)this.input, 91, FOLLOW_MINUS_in_unary_op2791); if (this.state.failed) return retval; 
/*  9260 */           if (this.state.backtracking == 0) stream_MINUS.add(MINUS331);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9271 */           if (this.state.backtracking == 0) {
/*  9272 */             retval.tree = root_0;
/*  9273 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9275 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9278 */             this.adaptor.addChild(root_0, this.adaptor.create(5, "UNARY_MINUS"));
/*       */ 
/*       */ 
/*       */             
/*  9282 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 3:
/*  9288 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9290 */           NOT332 = (Token)match((IntStream)this.input, 94, FOLLOW_NOT_in_unary_op2801); if (this.state.failed) return retval; 
/*  9291 */           if (this.state.backtracking == 0) {
/*  9292 */             NOT332_tree = (CommonTree)this.adaptor.create(NOT332);
/*  9293 */             this.adaptor.addChild(root_0, NOT332_tree);
/*       */           } 
/*       */           break;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         case 4:
/*  9301 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9303 */           BITWISENOT333 = (Token)match((IntStream)this.input, 95, FOLLOW_BITWISENOT_in_unary_op2806); if (this.state.failed) return retval; 
/*  9304 */           if (this.state.backtracking == 0) {
/*  9305 */             BITWISENOT333_tree = (CommonTree)this.adaptor.create(BITWISENOT333);
/*  9306 */             this.adaptor.addChild(root_0, BITWISENOT333_tree);
/*       */           } 
/*       */           break;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         case 5:
/*  9314 */           MULT334 = (Token)match((IntStream)this.input, 57, FOLLOW_MULT_in_unary_op2812); if (this.state.failed) return retval; 
/*  9315 */           if (this.state.backtracking == 0) stream_MULT.add(MULT334);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9326 */           if (this.state.backtracking == 0) {
/*  9327 */             retval.tree = root_0;
/*  9328 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9330 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9333 */             this.adaptor.addChild(root_0, this.adaptor.create(6, "UNARY_DEREFERENCE"));
/*       */ 
/*       */ 
/*       */             
/*  9337 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 6:
/*  9343 */           REF335 = (Token)match((IntStream)this.input, 83, FOLLOW_REF_in_unary_op2822); if (this.state.failed) return retval; 
/*  9344 */           if (this.state.backtracking == 0) stream_REF.add(REF335);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9355 */           if (this.state.backtracking == 0) {
/*  9356 */             retval.tree = root_0;
/*  9357 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9359 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9362 */             this.adaptor.addChild(root_0, this.adaptor.create(7, "UNARY_REF"));
/*       */ 
/*       */ 
/*       */             
/*  9366 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 7:
/*  9372 */           INCREMENT336 = (Token)match((IntStream)this.input, 96, FOLLOW_INCREMENT_in_unary_op2833); if (this.state.failed) return retval; 
/*  9373 */           if (this.state.backtracking == 0) stream_INCREMENT.add(INCREMENT336);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9384 */           if (this.state.backtracking == 0) {
/*  9385 */             retval.tree = root_0;
/*  9386 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9388 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9391 */             this.adaptor.addChild(root_0, this.adaptor.create(8, "UNARY_PREFIX_INCREMENT"));
/*       */ 
/*       */ 
/*       */             
/*  9395 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 8:
/*  9401 */           DECREMENT337 = (Token)match((IntStream)this.input, 97, FOLLOW_DECREMENT_in_unary_op2843); if (this.state.failed) return retval; 
/*  9402 */           if (this.state.backtracking == 0) stream_DECREMENT.add(DECREMENT337);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9413 */           if (this.state.backtracking == 0) {
/*  9414 */             retval.tree = root_0;
/*  9415 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9417 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9420 */             this.adaptor.addChild(root_0, this.adaptor.create(9, "UNARY_PREFIX_DECREMENT"));
/*       */ 
/*       */ 
/*       */             
/*  9424 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */       
/*  9429 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9431 */       if (this.state.backtracking == 0)
/*       */       {
/*  9433 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9434 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9437 */     } catch (RecognitionException re) {
/*  9438 */       reportError(re);
/*  9439 */       recover((IntStream)this.input, re);
/*  9440 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9445 */     return retval;
/*       */   }
/*       */   
/*       */   public static class postfix_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  9451 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final postfix_expression_return postfix_expression() throws RecognitionException {
/*  9457 */     postfix_expression_return retval = new postfix_expression_return();
/*  9458 */     retval.start = this.input.LT(1);
/*       */     
/*  9460 */     CommonTree root_0 = null;
/*       */     
/*  9462 */     Token LBRACKET340 = null;
/*  9463 */     Token RBRACKET342 = null;
/*  9464 */     Token DOT343 = null;
/*  9465 */     Token IDENTIFIER344 = null;
/*  9466 */     Token FIELD345 = null;
/*  9467 */     Token IDENTIFIER346 = null;
/*  9468 */     Token LPAR347 = null;
/*  9469 */     Token COMMA349 = null;
/*  9470 */     Token RPAR351 = null;
/*  9471 */     literal_expression_return literal_expression338 = null;
/*       */     
/*  9473 */     primary_expression_return primary_expression339 = null;
/*       */     
/*  9475 */     expression_return expression341 = null;
/*       */     
/*  9477 */     expression_return expression348 = null;
/*       */     
/*  9479 */     expression_return expression350 = null;
/*       */     
/*  9481 */     postfix_increment_decrement_return postfix_increment_decrement352 = null;
/*       */ 
/*       */     
/*  9484 */     CommonTree LBRACKET340_tree = null;
/*  9485 */     CommonTree RBRACKET342_tree = null;
/*  9486 */     CommonTree DOT343_tree = null;
/*  9487 */     CommonTree IDENTIFIER344_tree = null;
/*  9488 */     CommonTree FIELD345_tree = null;
/*  9489 */     CommonTree IDENTIFIER346_tree = null;
/*  9490 */     CommonTree LPAR347_tree = null;
/*  9491 */     CommonTree COMMA349_tree = null;
/*  9492 */     CommonTree RPAR351_tree = null;
/*       */ 
/*       */     
/*       */     try {
/*  9496 */       int alt104 = 2;
/*  9497 */       int LA104_0 = this.input.LA(1);
/*       */       
/*  9499 */       if (LA104_0 == 68 || (LA104_0 >= 99 && LA104_0 <= 101)) {
/*  9500 */         alt104 = 1;
/*       */       }
/*  9502 */       else if (LA104_0 == 24 || LA104_0 == 32) {
/*  9503 */         alt104 = 2;
/*       */       } else {
/*       */         
/*  9506 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  9507 */          NoViableAltException nvae = new NoViableAltException("", 104, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  9510 */         throw nvae;
/*       */       } 
/*  9512 */       switch (alt104) {
/*       */ 
/*       */         
/*       */         case 1:
/*  9516 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9518 */           pushFollow(FOLLOW_literal_expression_in_postfix_expression2858);
/*  9519 */           literal_expression338 = literal_expression();
/*       */           
/*  9521 */           this.state._fsp--;
/*  9522 */           if (this.state.failed) return retval; 
/*  9523 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, literal_expression338.getTree());
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  9530 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9532 */           pushFollow(FOLLOW_primary_expression_in_postfix_expression2870);
/*  9533 */           primary_expression339 = primary_expression();
/*       */           
/*  9535 */           this.state._fsp--;
/*  9536 */           if (this.state.failed) return retval; 
/*  9537 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, primary_expression339.getTree());
/*       */ 
/*       */           
/*       */           while (true) {
/*  9541 */             int alt102, LA102_0, alt103 = 6;
/*  9542 */             switch (this.input.LA(1)) {
/*       */               
/*       */               case 52:
/*  9545 */                 alt103 = 1;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 35:
/*  9550 */                 alt103 = 2;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 98:
/*  9555 */                 alt103 = 3;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 32:
/*  9560 */                 alt103 = 4;
/*       */                 break;
/*       */ 
/*       */               
/*       */               case 96:
/*       */               case 97:
/*  9566 */                 alt103 = 5;
/*       */                 break;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/*  9572 */             switch (alt103) {
/*       */ 
/*       */               
/*       */               case 1:
/*  9576 */                 LBRACKET340 = (Token)match((IntStream)this.input, 52, FOLLOW_LBRACKET_in_postfix_expression2874); if (this.state.failed) return retval; 
/*  9577 */                 if (this.state.backtracking == 0) {
/*  9578 */                   LBRACKET340_tree = (CommonTree)this.adaptor.create(LBRACKET340);
/*  9579 */                   root_0 = (CommonTree)this.adaptor.becomeRoot(LBRACKET340_tree, root_0);
/*       */                 } 
/*  9581 */                 pushFollow(FOLLOW_expression_in_postfix_expression2877);
/*  9582 */                 expression341 = expression();
/*       */                 
/*  9584 */                 this.state._fsp--;
/*  9585 */                 if (this.state.failed) return retval; 
/*  9586 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression341.getTree()); 
/*  9587 */                 RBRACKET342 = (Token)match((IntStream)this.input, 53, FOLLOW_RBRACKET_in_postfix_expression2879); if (this.state.failed) return retval;
/*       */                 
/*       */                 continue;
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  9594 */                 DOT343 = (Token)match((IntStream)this.input, 35, FOLLOW_DOT_in_postfix_expression2891); if (this.state.failed) return retval; 
/*  9595 */                 if (this.state.backtracking == 0) {
/*  9596 */                   DOT343_tree = (CommonTree)this.adaptor.create(DOT343);
/*  9597 */                   root_0 = (CommonTree)this.adaptor.becomeRoot(DOT343_tree, root_0);
/*       */                 } 
/*  9599 */                 IDENTIFIER344 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_postfix_expression2894); if (this.state.failed) return retval; 
/*  9600 */                 if (this.state.backtracking == 0) {
/*  9601 */                   IDENTIFIER344_tree = (CommonTree)this.adaptor.create(IDENTIFIER344);
/*  9602 */                   this.adaptor.addChild(root_0, IDENTIFIER344_tree);
/*       */                 } 
/*       */                 continue;
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*       */               case 3:
/*  9610 */                 FIELD345 = (Token)match((IntStream)this.input, 98, FOLLOW_FIELD_in_postfix_expression2906); if (this.state.failed) return retval; 
/*  9611 */                 if (this.state.backtracking == 0) {
/*  9612 */                   FIELD345_tree = (CommonTree)this.adaptor.create(FIELD345);
/*  9613 */                   root_0 = (CommonTree)this.adaptor.becomeRoot(FIELD345_tree, root_0);
/*       */                 } 
/*  9615 */                 IDENTIFIER346 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_postfix_expression2909); if (this.state.failed) return retval; 
/*  9616 */                 if (this.state.backtracking == 0) {
/*  9617 */                   IDENTIFIER346_tree = (CommonTree)this.adaptor.create(IDENTIFIER346);
/*  9618 */                   this.adaptor.addChild(root_0, IDENTIFIER346_tree);
/*       */                 } 
/*       */                 continue;
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*       */               case 4:
/*  9626 */                 LPAR347 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_postfix_expression2942); if (this.state.failed) return retval; 
/*  9627 */                 if (this.state.backtracking == 0) {
/*  9628 */                   LPAR347_tree = (CommonTree)this.adaptor.create(LPAR347);
/*  9629 */                   root_0 = (CommonTree)this.adaptor.becomeRoot(LPAR347_tree, root_0);
/*       */                 } 
/*       */                 
/*  9632 */                 alt102 = 2;
/*  9633 */                 LA102_0 = this.input.LA(1);
/*       */                 
/*  9635 */                 if (LA102_0 == 24 || LA102_0 == 32 || LA102_0 == 57 || LA102_0 == 68 || LA102_0 == 78 || LA102_0 == 83 || LA102_0 == 91 || (LA102_0 >= 94 && LA102_0 <= 97) || (LA102_0 >= 99 && LA102_0 <= 101)) {
/*  9636 */                   alt102 = 1;
/*       */                 }
/*  9638 */                 switch (alt102) {
/*       */ 
/*       */                   
/*       */                   case 1:
/*  9642 */                     pushFollow(FOLLOW_expression_in_postfix_expression2947);
/*  9643 */                     expression348 = expression();
/*       */                     
/*  9645 */                     this.state._fsp--;
/*  9646 */                     if (this.state.failed) return retval; 
/*  9647 */                     if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression348.getTree());
/*       */ 
/*       */                     
/*       */                     while (true) {
/*  9651 */                       int alt101 = 2;
/*  9652 */                       int LA101_0 = this.input.LA(1);
/*       */                       
/*  9654 */                       if (LA101_0 == 33) {
/*  9655 */                         alt101 = 1;
/*       */                       }
/*       */ 
/*       */                       
/*  9659 */                       switch (alt101) {
/*       */ 
/*       */                         
/*       */                         case 1:
/*  9663 */                           COMMA349 = (Token)match((IntStream)this.input, 33, FOLLOW_COMMA_in_postfix_expression2950); if (this.state.failed) return retval; 
/*  9664 */                           pushFollow(FOLLOW_expression_in_postfix_expression2953);
/*  9665 */                           expression350 = expression();
/*       */                           
/*  9667 */                           this.state._fsp--;
/*  9668 */                           if (this.state.failed) return retval; 
/*  9669 */                           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression350.getTree());
/*       */                           
/*       */                           continue;
/*       */                       } 
/*       */ 
/*       */ 
/*       */ 
/*       */                       
/*       */                       break;
/*       */                     } 
/*       */                     break;
/*       */                 } 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*  9685 */                 RPAR351 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_postfix_expression2960); if (this.state.failed) return retval;
/*       */                 
/*       */                 continue;
/*       */ 
/*       */ 
/*       */               
/*       */               case 5:
/*  9692 */                 pushFollow(FOLLOW_postfix_increment_decrement_in_postfix_expression2993);
/*  9693 */                 postfix_increment_decrement352 = postfix_increment_decrement();
/*       */                 
/*  9695 */                 this.state._fsp--;
/*  9696 */                 if (this.state.failed) return retval; 
/*  9697 */                 if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(postfix_increment_decrement352.getTree(), root_0);
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  9712 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9714 */       if (this.state.backtracking == 0)
/*       */       {
/*  9716 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9717 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9720 */     } catch (RecognitionException re) {
/*  9721 */       reportError(re);
/*  9722 */       recover((IntStream)this.input, re);
/*  9723 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9728 */     return retval;
/*       */   }
/*       */   
/*       */   public static class postfix_increment_decrement_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  9734 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final postfix_increment_decrement_return postfix_increment_decrement() throws RecognitionException {
/*  9740 */     postfix_increment_decrement_return retval = new postfix_increment_decrement_return();
/*  9741 */     retval.start = this.input.LT(1);
/*       */     
/*  9743 */     CommonTree root_0 = null;
/*       */     
/*  9745 */     Token INCREMENT353 = null;
/*  9746 */     Token DECREMENT354 = null;
/*       */     
/*  9748 */     CommonTree INCREMENT353_tree = null;
/*  9749 */     CommonTree DECREMENT354_tree = null;
/*  9750 */     RewriteRuleTokenStream stream_INCREMENT = new RewriteRuleTokenStream(this.adaptor, "token INCREMENT");
/*  9751 */     RewriteRuleTokenStream stream_DECREMENT = new RewriteRuleTokenStream(this.adaptor, "token DECREMENT");
/*       */ 
/*       */     
/*       */     try {
/*  9755 */       int alt105 = 2;
/*  9756 */       int LA105_0 = this.input.LA(1);
/*       */       
/*  9758 */       if (LA105_0 == 96) {
/*  9759 */         alt105 = 1;
/*       */       }
/*  9761 */       else if (LA105_0 == 97) {
/*  9762 */         alt105 = 2;
/*       */       } else {
/*       */         
/*  9765 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  9766 */          NoViableAltException nvae = new NoViableAltException("", 105, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  9769 */         throw nvae;
/*       */       } 
/*  9771 */       switch (alt105) {
/*       */ 
/*       */         
/*       */         case 1:
/*  9775 */           INCREMENT353 = (Token)match((IntStream)this.input, 96, FOLLOW_INCREMENT_in_postfix_increment_decrement3008); if (this.state.failed) return retval; 
/*  9776 */           if (this.state.backtracking == 0) stream_INCREMENT.add(INCREMENT353);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9787 */           if (this.state.backtracking == 0) {
/*  9788 */             retval.tree = root_0;
/*  9789 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9791 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9794 */             this.adaptor.addChild(root_0, this.adaptor.create(10, "UNARY_POSTFIX_INCREMENT"));
/*       */ 
/*       */ 
/*       */             
/*  9798 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */ 
/*       */         
/*       */         case 2:
/*  9804 */           DECREMENT354 = (Token)match((IntStream)this.input, 97, FOLLOW_DECREMENT_in_postfix_increment_decrement3023); if (this.state.failed) return retval; 
/*  9805 */           if (this.state.backtracking == 0) stream_DECREMENT.add(DECREMENT354);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9816 */           if (this.state.backtracking == 0) {
/*  9817 */             retval.tree = root_0;
/*  9818 */             RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", (retval != null) ? retval.tree : null);
/*       */             
/*  9820 */             root_0 = (CommonTree)this.adaptor.nil();
/*       */ 
/*       */             
/*  9823 */             this.adaptor.addChild(root_0, this.adaptor.create(11, "UNARY_POSTFIX_DECREMENT"));
/*       */ 
/*       */ 
/*       */             
/*  9827 */             retval.tree = root_0;
/*       */           } 
/*       */           break;
/*       */       } 
/*       */       
/*  9832 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9834 */       if (this.state.backtracking == 0)
/*       */       {
/*  9836 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9837 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9840 */     } catch (RecognitionException re) {
/*  9841 */       reportError(re);
/*  9842 */       recover((IntStream)this.input, re);
/*  9843 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9848 */     return retval;
/*       */   }
/*       */   
/*       */   public static class literal_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  9854 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final literal_expression_return literal_expression() throws RecognitionException {
/*  9860 */     literal_expression_return retval = new literal_expression_return();
/*  9861 */     retval.start = this.input.LT(1);
/*       */     
/*  9863 */     CommonTree root_0 = null;
/*       */     
/*  9865 */     Token set355 = null;
/*       */     
/*  9867 */     CommonTree set355_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  9873 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/*  9875 */       set355 = this.input.LT(1);
/*  9876 */       if (this.input.LA(1) == 68 || (this.input.LA(1) >= 99 && this.input.LA(1) <= 101)) {
/*  9877 */         this.input.consume();
/*  9878 */         if (this.state.backtracking == 0) this.adaptor.addChild(root_0, this.adaptor.create(set355)); 
/*  9879 */         this.state.errorRecovery = false; this.state.failed = false;
/*       */       } else {
/*       */         
/*  9882 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  9883 */          MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/*  9884 */         throw mse;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  9890 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9892 */       if (this.state.backtracking == 0)
/*       */       {
/*  9894 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9895 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9898 */     } catch (RecognitionException re) {
/*  9899 */       reportError(re);
/*  9900 */       recover((IntStream)this.input, re);
/*  9901 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9906 */     return retval;
/*       */   }
/*       */   
/*       */   public static class primary_expression_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/*  9912 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final primary_expression_return primary_expression() throws RecognitionException {
/*  9918 */     primary_expression_return retval = new primary_expression_return();
/*  9919 */     retval.start = this.input.LT(1);
/*       */     
/*  9921 */     CommonTree root_0 = null;
/*       */     
/*  9923 */     Token IDENTIFIER356 = null;
/*  9924 */     Token LPAR357 = null;
/*  9925 */     Token RPAR359 = null;
/*  9926 */     expression_return expression358 = null;
/*       */ 
/*       */     
/*  9929 */     CommonTree IDENTIFIER356_tree = null;
/*  9930 */     CommonTree LPAR357_tree = null;
/*  9931 */     CommonTree RPAR359_tree = null;
/*       */ 
/*       */     
/*       */     try {
/*  9935 */       int alt106 = 2;
/*  9936 */       int LA106_0 = this.input.LA(1);
/*       */       
/*  9938 */       if (LA106_0 == 24) {
/*  9939 */         alt106 = 1;
/*       */       }
/*  9941 */       else if (LA106_0 == 32) {
/*  9942 */         alt106 = 2;
/*       */       } else {
/*       */         
/*  9945 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/*  9946 */          NoViableAltException nvae = new NoViableAltException("", 106, 0, (IntStream)this.input);
/*       */ 
/*       */         
/*  9949 */         throw nvae;
/*       */       } 
/*  9951 */       switch (alt106) {
/*       */ 
/*       */         
/*       */         case 1:
/*  9955 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9957 */           IDENTIFIER356 = (Token)match((IntStream)this.input, 24, FOLLOW_IDENTIFIER_in_primary_expression3065); if (this.state.failed) return retval; 
/*  9958 */           if (this.state.backtracking == 0) {
/*  9959 */             IDENTIFIER356_tree = (CommonTree)this.adaptor.create(IDENTIFIER356);
/*  9960 */             this.adaptor.addChild(root_0, IDENTIFIER356_tree);
/*       */           } 
/*       */           break;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/*  9968 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/*  9970 */           LPAR357 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_primary_expression3070); if (this.state.failed) return retval; 
/*  9971 */           pushFollow(FOLLOW_expression_in_primary_expression3073);
/*  9972 */           expression358 = expression();
/*       */           
/*  9974 */           this.state._fsp--;
/*  9975 */           if (this.state.failed) return retval; 
/*  9976 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression358.getTree()); 
/*  9977 */           RPAR359 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_primary_expression3075); if (this.state.failed) return retval;
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/*  9983 */       retval.stop = this.input.LT(-1);
/*       */       
/*  9985 */       if (this.state.backtracking == 0)
/*       */       {
/*  9987 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/*  9988 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/*  9991 */     } catch (RecognitionException re) {
/*  9992 */       reportError(re);
/*  9993 */       recover((IntStream)this.input, re);
/*  9994 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/*  9999 */     return retval;
/*       */   }
/*       */   
/*       */   public static class action_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/* 10005 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final action_return action() throws RecognitionException {
/* 10011 */     action_return retval = new action_return();
/* 10012 */     retval.start = this.input.LT(1);
/*       */     
/* 10014 */     CommonTree root_0 = null;
/*       */     
/* 10016 */     Token char_literal363 = null;
/* 10017 */     Token IF364 = null;
/* 10018 */     Token LPAR365 = null;
/* 10019 */     Token RPAR367 = null;
/* 10020 */     Token ELSE369 = null;
/* 10021 */     Token CODE371 = null;
/* 10022 */     Token LCURLY372 = null;
/* 10023 */     Token RCURLY374 = null;
/* 10024 */     expression_return expression360 = null;
/*       */     
/* 10026 */     assign_op_return assign_op361 = null;
/*       */     
/* 10028 */     expression_return expression362 = null;
/*       */     
/* 10030 */     expression_return expression366 = null;
/*       */     
/* 10032 */     action_return action368 = null;
/*       */     
/* 10034 */     action_return action370 = null;
/*       */     
/* 10036 */     action_return action373 = null;
/*       */ 
/*       */     
/* 10039 */     CommonTree char_literal363_tree = null;
/* 10040 */     CommonTree IF364_tree = null;
/* 10041 */     CommonTree LPAR365_tree = null;
/* 10042 */     CommonTree RPAR367_tree = null;
/* 10043 */     CommonTree ELSE369_tree = null;
/* 10044 */     CommonTree CODE371_tree = null;
/* 10045 */     CommonTree LCURLY372_tree = null;
/* 10046 */     CommonTree RCURLY374_tree = null;
/*       */     
/*       */     try {
/*       */       NoViableAltException nvae;
/* 10050 */       int alt108, LA108_0, alt110 = 4;
/* 10051 */       switch (this.input.LA(1)) {
/*       */         
/*       */         case 24:
/*       */         case 32:
/*       */         case 57:
/*       */         case 68:
/*       */         case 78:
/*       */         case 83:
/*       */         case 91:
/*       */         case 94:
/*       */         case 95:
/*       */         case 96:
/*       */         case 97:
/*       */         case 99:
/*       */         case 100:
/*       */         case 101:
/* 10067 */           alt110 = 1;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 102:
/* 10072 */           alt110 = 2;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 28:
/* 10077 */           alt110 = 3;
/*       */           break;
/*       */ 
/*       */         
/*       */         case 104:
/* 10082 */           alt110 = 4;
/*       */           break;
/*       */         
/*       */         default:
/* 10086 */           if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/* 10087 */            nvae = new NoViableAltException("", 110, 0, (IntStream)this.input);
/*       */ 
/*       */           
/* 10090 */           throw nvae;
/*       */       } 
/*       */       
/* 10093 */       switch (alt110) {
/*       */ 
/*       */         
/*       */         case 1:
/* 10097 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/* 10099 */           pushFollow(FOLLOW_expression_in_action3091);
/* 10100 */           expression360 = expression();
/*       */           
/* 10102 */           this.state._fsp--;
/* 10103 */           if (this.state.failed) return retval; 
/* 10104 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression360.getTree());
/*       */ 
/*       */           
/*       */           while (true) {
/* 10108 */             int alt107 = 2;
/* 10109 */             int LA107_0 = this.input.LA(1);
/*       */             
/* 10111 */             if (LA107_0 == 58 || (LA107_0 >= 106 && LA107_0 <= 110)) {
/* 10112 */               alt107 = 1;
/*       */             }
/*       */ 
/*       */             
/* 10116 */             switch (alt107) {
/*       */ 
/*       */               
/*       */               case 1:
/* 10120 */                 pushFollow(FOLLOW_assign_op_in_action3094);
/* 10121 */                 assign_op361 = assign_op();
/*       */                 
/* 10123 */                 this.state._fsp--;
/* 10124 */                 if (this.state.failed) return retval; 
/* 10125 */                 if (this.state.backtracking == 0) root_0 = (CommonTree)this.adaptor.becomeRoot(assign_op361.getTree(), root_0); 
/* 10126 */                 pushFollow(FOLLOW_expression_in_action3097);
/* 10127 */                 expression362 = expression();
/*       */                 
/* 10129 */                 this.state._fsp--;
/* 10130 */                 if (this.state.failed) return retval; 
/* 10131 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression362.getTree());
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/* 10141 */           char_literal363 = (Token)match((IntStream)this.input, 116, FOLLOW_116_in_action3101); if (this.state.failed) return retval;
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 2:
/* 10148 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/* 10150 */           IF364 = (Token)match((IntStream)this.input, 102, FOLLOW_IF_in_action3107); if (this.state.failed) return retval; 
/* 10151 */           if (this.state.backtracking == 0) {
/* 10152 */             IF364_tree = (CommonTree)this.adaptor.create(IF364);
/* 10153 */             root_0 = (CommonTree)this.adaptor.becomeRoot(IF364_tree, root_0);
/*       */           } 
/* 10155 */           LPAR365 = (Token)match((IntStream)this.input, 32, FOLLOW_LPAR_in_action3110); if (this.state.failed) return retval; 
/* 10156 */           pushFollow(FOLLOW_expression_in_action3113);
/* 10157 */           expression366 = expression();
/*       */           
/* 10159 */           this.state._fsp--;
/* 10160 */           if (this.state.failed) return retval; 
/* 10161 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, expression366.getTree()); 
/* 10162 */           RPAR367 = (Token)match((IntStream)this.input, 34, FOLLOW_RPAR_in_action3115); if (this.state.failed) return retval; 
/* 10163 */           pushFollow(FOLLOW_action_in_action3118);
/* 10164 */           action368 = action();
/*       */           
/* 10166 */           this.state._fsp--;
/* 10167 */           if (this.state.failed) return retval; 
/* 10168 */           if (this.state.backtracking == 0) this.adaptor.addChild(root_0, action368.getTree());
/*       */           
/* 10170 */           alt108 = 2;
/* 10171 */           LA108_0 = this.input.LA(1);
/*       */           
/* 10173 */           if (LA108_0 == 103) {
/* 10174 */             int LA108_1 = this.input.LA(2);
/*       */             
/* 10176 */             if (synpred1_bip()) {
/* 10177 */               alt108 = 1;
/*       */             }
/*       */           } 
/* 10180 */           switch (alt108) {
/*       */ 
/*       */             
/*       */             case 1:
/* 10184 */               ELSE369 = (Token)match((IntStream)this.input, 103, FOLLOW_ELSE_in_action3125); if (this.state.failed) return retval; 
/* 10185 */               if (this.state.backtracking == 0) {
/* 10186 */                 ELSE369_tree = (CommonTree)this.adaptor.create(ELSE369);
/* 10187 */                 this.adaptor.addChild(root_0, ELSE369_tree);
/*       */               } 
/* 10189 */               pushFollow(FOLLOW_action_in_action3127);
/* 10190 */               action370 = action();
/*       */               
/* 10192 */               this.state._fsp--;
/* 10193 */               if (this.state.failed) return retval; 
/* 10194 */               if (this.state.backtracking == 0) this.adaptor.addChild(root_0, action370.getTree());
/*       */               
/*       */               break;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*       */           break;
/*       */ 
/*       */ 
/*       */         
/*       */         case 3:
/* 10207 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/* 10209 */           CODE371 = (Token)match((IntStream)this.input, 28, FOLLOW_CODE_in_action3134); if (this.state.failed) return retval; 
/* 10210 */           if (this.state.backtracking == 0) {
/* 10211 */             CODE371_tree = (CommonTree)this.adaptor.create(CODE371);
/* 10212 */             this.adaptor.addChild(root_0, CODE371_tree);
/*       */           } 
/*       */           break;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         case 4:
/* 10220 */           root_0 = (CommonTree)this.adaptor.nil();
/*       */           
/* 10222 */           LCURLY372 = (Token)match((IntStream)this.input, 104, FOLLOW_LCURLY_in_action3139); if (this.state.failed) return retval; 
/* 10223 */           if (this.state.backtracking == 0) {
/* 10224 */             LCURLY372_tree = (CommonTree)this.adaptor.create(LCURLY372);
/* 10225 */             root_0 = (CommonTree)this.adaptor.becomeRoot(LCURLY372_tree, root_0);
/*       */           } 
/*       */ 
/*       */           
/*       */           while (true) {
/* 10230 */             int alt109 = 2;
/* 10231 */             int LA109_0 = this.input.LA(1);
/*       */             
/* 10233 */             if (LA109_0 == 24 || LA109_0 == 28 || LA109_0 == 32 || LA109_0 == 57 || LA109_0 == 68 || LA109_0 == 78 || LA109_0 == 83 || LA109_0 == 91 || (LA109_0 >= 94 && LA109_0 <= 97) || (LA109_0 >= 99 && LA109_0 <= 102) || LA109_0 == 104) {
/* 10234 */               alt109 = 1;
/*       */             }
/*       */ 
/*       */             
/* 10238 */             switch (alt109) {
/*       */ 
/*       */               
/*       */               case 1:
/* 10242 */                 pushFollow(FOLLOW_action_in_action3144);
/* 10243 */                 action373 = action();
/*       */                 
/* 10245 */                 this.state._fsp--;
/* 10246 */                 if (this.state.failed) return retval; 
/* 10247 */                 if (this.state.backtracking == 0) this.adaptor.addChild(root_0, action373.getTree());
/*       */                 
/*       */                 continue;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             break;
/*       */           } 
/* 10257 */           RCURLY374 = (Token)match((IntStream)this.input, 105, FOLLOW_RCURLY_in_action3149); if (this.state.failed) return retval;
/*       */           
/*       */           break;
/*       */       } 
/*       */ 
/*       */       
/* 10263 */       retval.stop = this.input.LT(-1);
/*       */       
/* 10265 */       if (this.state.backtracking == 0)
/*       */       {
/* 10267 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/* 10268 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/* 10271 */     } catch (RecognitionException re) {
/* 10272 */       reportError(re);
/* 10273 */       recover((IntStream)this.input, re);
/* 10274 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/* 10279 */     return retval;
/*       */   }
/*       */   
/*       */   public static class assign_op_return extends ParserRuleReturnScope { CommonTree tree;
/*       */     
/*       */     public Object getTree() {
/* 10285 */       return this.tree;
/*       */     } }
/*       */ 
/*       */ 
/*       */   
/*       */   public final assign_op_return assign_op() throws RecognitionException {
/* 10291 */     assign_op_return retval = new assign_op_return();
/* 10292 */     retval.start = this.input.LT(1);
/*       */     
/* 10294 */     CommonTree root_0 = null;
/*       */     
/* 10296 */     Token set375 = null;
/*       */     
/* 10298 */     CommonTree set375_tree = null;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/* 10304 */       root_0 = (CommonTree)this.adaptor.nil();
/*       */       
/* 10306 */       set375 = this.input.LT(1);
/* 10307 */       if (this.input.LA(1) == 58 || (this.input.LA(1) >= 106 && this.input.LA(1) <= 110)) {
/* 10308 */         this.input.consume();
/* 10309 */         if (this.state.backtracking == 0) this.adaptor.addChild(root_0, this.adaptor.create(set375)); 
/* 10310 */         this.state.errorRecovery = false; this.state.failed = false;
/*       */       } else {
/*       */         
/* 10313 */         if (this.state.backtracking > 0) { this.state.failed = true; return retval; }
/* 10314 */          MismatchedSetException mse = new MismatchedSetException(null, (IntStream)this.input);
/* 10315 */         throw mse;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 10321 */       retval.stop = this.input.LT(-1);
/*       */       
/* 10323 */       if (this.state.backtracking == 0)
/*       */       {
/* 10325 */         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
/* 10326 */         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
/*       */       }
/*       */     
/* 10329 */     } catch (RecognitionException re) {
/* 10330 */       reportError(re);
/* 10331 */       recover((IntStream)this.input, re);
/* 10332 */       retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
/*       */     } finally {}
/*       */ 
/*       */ 
/*       */     
/* 10337 */     return retval;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final void synpred1_bip_fragment() throws RecognitionException {
/* 10346 */     match((IntStream)this.input, 103, FOLLOW_ELSE_in_synpred1_bip3122); if (this.state.failed) {
/*       */       return;
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public final boolean synpred1_bip() {
/* 10355 */     this.state.backtracking++;
/* 10356 */     int start = this.input.mark();
/*       */     try {
/* 10358 */       synpred1_bip_fragment();
/* 10359 */     } catch (RecognitionException re) {
/* 10360 */       System.err.println("impossible: " + re);
/*       */     } 
/* 10362 */     boolean success = !this.state.failed;
/* 10363 */     this.input.rewind(start);
/* 10364 */     this.state.backtracking--;
/* 10365 */     this.state.failed = false;
/* 10366 */     return success;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10372 */   public static final BitSet FOLLOW_package_definition_in_translation_unit196 = new BitSet(new long[] { 2L });
/* 10373 */   public static final BitSet FOLLOW_model_definition_in_translation_unit202 = new BitSet(new long[] { 2L });
/* 10374 */   public static final BitSet FOLLOW_PACKAGE_in_package_definition215 = new BitSet(new long[] { 16777216L });
/* 10375 */   public static final BitSet FOLLOW_IDENTIFIER_in_package_definition218 = new BitSet(new long[] { 26459011809280L });
/* 10376 */   public static final BitSet FOLLOW_use_package_in_package_definition227 = new BitSet(new long[] { 26459011809280L });
/* 10377 */   public static final BitSet FOLLOW_bip_definition_in_package_definition239 = new BitSet(new long[] { 26459045363712L });
/* 10378 */   public static final BitSet FOLLOW_opaque_code_in_package_definition243 = new BitSet(new long[] { 26459045363712L });
/* 10379 */   public static final BitSet FOLLOW_END_in_package_definition252 = new BitSet(new long[] { 2L });
/* 10380 */   public static final BitSet FOLLOW_MODEL_in_model_definition265 = new BitSet(new long[] { 16777216L });
/* 10381 */   public static final BitSet FOLLOW_IDENTIFIER_in_model_definition268 = new BitSet(new long[] { 26459011809280L });
/* 10382 */   public static final BitSet FOLLOW_use_package_in_model_definition284 = new BitSet(new long[] { 26459011809280L });
/* 10383 */   public static final BitSet FOLLOW_bip_definition_in_model_definition296 = new BitSet(new long[] { 589408965230592L });
/* 10384 */   public static final BitSet FOLLOW_opaque_code_in_model_definition300 = new BitSet(new long[] { 589408965230592L });
/* 10385 */   public static final BitSet FOLLOW_component_definition_in_model_definition310 = new BitSet(new long[] { 33554432L });
/* 10386 */   public static final BitSet FOLLOW_END_in_model_definition317 = new BitSet(new long[] { 2L });
/* 10387 */   public static final BitSet FOLLOW_HEADER_in_opaque_code331 = new BitSet(new long[] { 268435456L });
/* 10388 */   public static final BitSet FOLLOW_CODE_in_opaque_code334 = new BitSet(new long[] { 2L });
/* 10389 */   public static final BitSet FOLLOW_USE_in_use_package348 = new BitSet(new long[] { 16777216L });
/* 10390 */   public static final BitSet FOLLOW_bip_package_in_use_package351 = new BitSet(new long[] { 2L });
/* 10391 */   public static final BitSet FOLLOW_IDENTIFIER_in_bip_package364 = new BitSet(new long[] { 2L });
/* 10392 */   public static final BitSet FOLLOW_port_type_definition_in_bip_definition379 = new BitSet(new long[] { 2L });
/* 10393 */   public static final BitSet FOLLOW_connector_type_definition_in_bip_definition384 = new BitSet(new long[] { 2L });
/* 10394 */   public static final BitSet FOLLOW_component_type_definition_in_bip_definition389 = new BitSet(new long[] { 2L });
/* 10395 */   public static final BitSet FOLLOW_PORT_in_port_type_definition404 = new BitSet(new long[] { 2147483648L });
/* 10396 */   public static final BitSet FOLLOW_TYPE_in_port_type_definition407 = new BitSet(new long[] { 16777216L });
/* 10397 */   public static final BitSet FOLLOW_IDENTIFIER_in_port_type_definition409 = new BitSet(new long[] { 4294967298L });
/* 10398 */   public static final BitSet FOLLOW_data_type_profile_in_port_type_definition413 = new BitSet(new long[] { 2L });
/* 10399 */   public static final BitSet FOLLOW_LPAR_in_port_type_profile431 = new BitSet(new long[] { 16777216L });
/* 10400 */   public static final BitSet FOLLOW_port_fpar_definition_in_port_type_profile434 = new BitSet(new long[] { 25769803776L });
/* 10401 */   public static final BitSet FOLLOW_COMMA_in_port_type_profile437 = new BitSet(new long[] { 16777216L });
/* 10402 */   public static final BitSet FOLLOW_port_fpar_definition_in_port_type_profile440 = new BitSet(new long[] { 25769803776L });
/* 10403 */   public static final BitSet FOLLOW_RPAR_in_port_type_profile444 = new BitSet(new long[] { 2L });
/* 10404 */   public static final BitSet FOLLOW_IDENTIFIER_in_port_type459 = new BitSet(new long[] { 34359738370L });
/* 10405 */   public static final BitSet FOLLOW_DOT_in_port_type462 = new BitSet(new long[] { 16777216L });
/* 10406 */   public static final BitSet FOLLOW_IDENTIFIER_in_port_type465 = new BitSet(new long[] { 2L });
/* 10407 */   public static final BitSet FOLLOW_port_type_in_port_fpar_definition480 = new BitSet(new long[] { 16777216L });
/* 10408 */   public static final BitSet FOLLOW_IDENTIFIER_in_port_fpar_definition482 = new BitSet(new long[] { 2L });
/* 10409 */   public static final BitSet FOLLOW_CONNECTOR_in_connector_type_definition509 = new BitSet(new long[] { 2147483648L });
/* 10410 */   public static final BitSet FOLLOW_TYPE_in_connector_type_definition512 = new BitSet(new long[] { 16777216L });
/* 10411 */   public static final BitSet FOLLOW_IDENTIFIER_in_connector_type_definition514 = new BitSet(new long[] { 4294967296L });
/* 10412 */   public static final BitSet FOLLOW_port_type_profile_in_connector_type_definition521 = new BitSet(new long[] { 279575527424L });
/* 10413 */   public static final BitSet FOLLOW_data_type_profile_in_connector_type_definition535 = new BitSet(new long[] { 279575527424L });
/* 10414 */   public static final BitSet FOLLOW_header_code_in_connector_type_definition544 = new BitSet(new long[] { 279575527424L });
/* 10415 */   public static final BitSet FOLLOW_CODE_in_connector_type_definition551 = new BitSet(new long[] { 279575527424L });
/* 10416 */   public static final BitSet FOLLOW_connector_type_define_in_connector_type_definition557 = new BitSet(new long[] { 137472507904L });
/* 10417 */   public static final BitSet FOLLOW_export_port_connector_in_connector_type_definition564 = new BitSet(new long[] { 33554432L });
/* 10418 */   public static final BitSet FOLLOW_END_in_connector_type_definition571 = new BitSet(new long[] { 2L });
/* 10419 */   public static final BitSet FOLLOW_HEADER_in_header_code586 = new BitSet(new long[] { 268435456L });
/* 10420 */   public static final BitSet FOLLOW_CODE_in_header_code588 = new BitSet(new long[] { 2L });
/* 10421 */   public static final BitSet FOLLOW_EXPORT_in_export_port_connector602 = new BitSet(new long[] { 1073741824L });
/* 10422 */   public static final BitSet FOLLOW_PORT_in_export_port_connector604 = new BitSet(new long[] { 16777216L });
/* 10423 */   public static final BitSet FOLLOW_port_type_in_export_port_connector606 = new BitSet(new long[] { 16777216L });
/* 10424 */   public static final BitSet FOLLOW_port_in_export_port_connector608 = new BitSet(new long[] { 4294967298L });
/* 10425 */   public static final BitSet FOLLOW_data_port_in_export_port_connector611 = new BitSet(new long[] { 2L });
/* 10426 */   public static final BitSet FOLLOW_DEFINE_in_connector_type_define641 = new BitSet(new long[] { 4503599644147712L });
/* 10427 */   public static final BitSet FOLLOW_port_expression_in_connector_type_define644 = new BitSet(new long[] { 282162171478018L, 12288L });
/* 10428 */   public static final BitSet FOLLOW_data_definition_in_connector_type_define652 = new BitSet(new long[] { 282162171478018L, 12288L });
/* 10429 */   public static final BitSet FOLLOW_interaction_definition_in_connector_type_define656 = new BitSet(new long[] { 282162171478018L, 12288L });
/* 10430 */   public static final BitSet FOLLOW_LPAR_in_data_type_profile672 = new BitSet(new long[] { 16777216L });
/* 10431 */   public static final BitSet FOLLOW_data_fpar_definition_in_data_type_profile674 = new BitSet(new long[] { 25769803776L });
/* 10432 */   public static final BitSet FOLLOW_COMMA_in_data_type_profile677 = new BitSet(new long[] { 16777216L });
/* 10433 */   public static final BitSet FOLLOW_data_fpar_definition_in_data_type_profile679 = new BitSet(new long[] { 25769803776L });
/* 10434 */   public static final BitSet FOLLOW_RPAR_in_data_type_profile683 = new BitSet(new long[] { 2L });
/* 10435 */   public static final BitSet FOLLOW_IDENTIFIER_in_connector_type709 = new BitSet(new long[] { 34359738370L });
/* 10436 */   public static final BitSet FOLLOW_DOT_in_connector_type712 = new BitSet(new long[] { 16777216L });
/* 10437 */   public static final BitSet FOLLOW_IDENTIFIER_in_connector_type715 = new BitSet(new long[] { 2L });
/* 10438 */   public static final BitSet FOLLOW_ON_in_interaction_definition729 = new BitSet(new long[] { 16777216L });
/* 10439 */   public static final BitSet FOLLOW_port_interaction_in_interaction_definition732 = new BitSet(new long[] { 7696581394434L });
/* 10440 */   public static final BitSet FOLLOW_provided_expression_in_interaction_definition738 = new BitSet(new long[] { 6597069766658L });
/* 10441 */   public static final BitSet FOLLOW_up_action_in_interaction_definition745 = new BitSet(new long[] { 4398046511106L });
/* 10442 */   public static final BitSet FOLLOW_down_action_in_interaction_definition752 = new BitSet(new long[] { 2L });
/* 10443 */   public static final BitSet FOLLOW_PROVIDED_in_provided_expression768 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10444 */   public static final BitSet FOLLOW_expression_in_provided_expression771 = new BitSet(new long[] { 2L });
/* 10445 */   public static final BitSet FOLLOW_BIPUP_in_up_action784 = new BitSet(new long[] { 144115192656035840L, 1631148589072L });
/* 10446 */   public static final BitSet FOLLOW_action_in_up_action787 = new BitSet(new long[] { 2L });
/* 10447 */   public static final BitSet FOLLOW_BIPDOWN_in_down_action802 = new BitSet(new long[] { 144115192656035840L, 1631148589072L });
/* 10448 */   public static final BitSet FOLLOW_action_in_down_action805 = new BitSet(new long[] { 2L });
/* 10449 */   public static final BitSet FOLLOW_port_in_port_interaction819 = new BitSet(new long[] { 16777218L });
/* 10450 */   public static final BitSet FOLLOW_port_in_port_interaction822 = new BitSet(new long[] { 16777218L });
/* 10451 */   public static final BitSet FOLLOW_ATOMIC_in_component_type_definition845 = new BitSet(new long[] { 2147483648L });
/* 10452 */   public static final BitSet FOLLOW_TYPE_in_component_type_definition848 = new BitSet(new long[] { 16777216L });
/* 10453 */   public static final BitSet FOLLOW_IDENTIFIER_in_component_type_definition850 = new BitSet(new long[] { 576847923606716416L, 14336L });
/* 10454 */   public static final BitSet FOLLOW_component_parameters_in_component_type_definition855 = new BitSet(new long[] { 576847923606716416L, 14336L });
/* 10455 */   public static final BitSet FOLLOW_component_activation_in_component_type_definition864 = new BitSet(new long[] { 576847923606716416L, 14336L });
/* 10456 */   public static final BitSet FOLLOW_header_code_in_component_type_definition873 = new BitSet(new long[] { 576847923606716416L, 14336L });
/* 10457 */   public static final BitSet FOLLOW_CODE_in_component_type_definition880 = new BitSet(new long[] { 576847923606716416L, 14336L });
/* 10458 */   public static final BitSet FOLLOW_data_definition_in_component_type_definition888 = new BitSet(new long[] { 1747784376478859264L, 14336L });
/* 10459 */   public static final BitSet FOLLOW_clock_definition_in_component_type_definition892 = new BitSet(new long[] { 1747784376478859264L, 14336L });
/* 10460 */   public static final BitSet FOLLOW_port_definition_in_component_type_definition896 = new BitSet(new long[] { 1747784376478859264L, 14336L });
/* 10461 */   public static final BitSet FOLLOW_place_definition_in_component_type_definition900 = new BitSet(new long[] { 1747784376478859264L, 14336L });
/* 10462 */   public static final BitSet FOLLOW_initialization_in_component_type_definition909 = new BitSet(new long[] { 1170936452872142848L });
/* 10463 */   public static final BitSet FOLLOW_transition_definition_in_component_type_definition917 = new BitSet(new long[] { 1170936590344650752L });
/* 10464 */   public static final BitSet FOLLOW_priority_definition_in_component_type_definition921 = new BitSet(new long[] { 1170936590344650752L });
/* 10465 */   public static final BitSet FOLLOW_export_port_definition_in_component_type_definition931 = new BitSet(new long[] { 137472507904L });
/* 10466 */   public static final BitSet FOLLOW_export_data_definition_in_component_type_definition935 = new BitSet(new long[] { 137472507904L });
/* 10467 */   public static final BitSet FOLLOW_END_in_component_type_definition941 = new BitSet(new long[] { 2L });
/* 10468 */   public static final BitSet FOLLOW_COMPOUND_in_component_type_definition948 = new BitSet(new long[] { 2147483648L });
/* 10469 */   public static final BitSet FOLLOW_TYPE_in_component_type_definition951 = new BitSet(new long[] { 16777216L });
/* 10470 */   public static final BitSet FOLLOW_IDENTIFIER_in_component_type_definition953 = new BitSet(new long[] { 1171605029358927872L });
/* 10471 */   public static final BitSet FOLLOW_component_parameters_in_component_type_definition957 = new BitSet(new long[] { 1171605025063960576L });
/* 10472 */   public static final BitSet FOLLOW_component_activation_in_component_type_definition967 = new BitSet(new long[] { 1171499471947694080L });
/* 10473 */   public static final BitSet FOLLOW_header_code_in_component_type_definition976 = new BitSet(new long[] { 1171499471813476352L });
/* 10474 */   public static final BitSet FOLLOW_CODE_in_component_type_definition983 = new BitSet(new long[] { 1171499471545040896L });
/* 10475 */   public static final BitSet FOLLOW_component_definition_in_component_type_definition991 = new BitSet(new long[] { 1171499609017548800L });
/* 10476 */   public static final BitSet FOLLOW_connector_definition_in_component_type_definition995 = new BitSet(new long[] { 1171499609017548800L });
/* 10477 */   public static final BitSet FOLLOW_priority_definition_in_component_type_definition999 = new BitSet(new long[] { 1171499609017548800L });
/* 10478 */   public static final BitSet FOLLOW_export_port_definition_in_component_type_definition1008 = new BitSet(new long[] { 137472507904L });
/* 10479 */   public static final BitSet FOLLOW_export_data_definition_in_component_type_definition1012 = new BitSet(new long[] { 137472507904L });
/* 10480 */   public static final BitSet FOLLOW_END_in_component_type_definition1018 = new BitSet(new long[] { 2L });
/* 10481 */   public static final BitSet FOLLOW_LPAR_in_component_parameters1035 = new BitSet(new long[] { 16777216L });
/* 10482 */   public static final BitSet FOLLOW_data_fpar_definition_in_component_parameters1037 = new BitSet(new long[] { 25769803776L });
/* 10483 */   public static final BitSet FOLLOW_COMMA_in_component_parameters1040 = new BitSet(new long[] { 16777216L });
/* 10484 */   public static final BitSet FOLLOW_data_fpar_definition_in_component_parameters1042 = new BitSet(new long[] { 25769803776L });
/* 10485 */   public static final BitSet FOLLOW_RPAR_in_component_parameters1047 = new BitSet(new long[] { 2L });
/* 10486 */   public static final BitSet FOLLOW_IDENTIFIER_in_component_type1071 = new BitSet(new long[] { 34359738370L });
/* 10487 */   public static final BitSet FOLLOW_DOT_in_component_type1074 = new BitSet(new long[] { 16777216L });
/* 10488 */   public static final BitSet FOLLOW_IDENTIFIER_in_component_type1077 = new BitSet(new long[] { 2L });
/* 10489 */   public static final BitSet FOLLOW_set_in_component_activation0 = new BitSet(new long[] { 2L });
/* 10490 */   public static final BitSet FOLLOW_EXPORT_in_export_port_definition1112 = new BitSet(new long[] { 1073741824L });
/* 10491 */   public static final BitSet FOLLOW_PORT_in_export_port_definition1114 = new BitSet(new long[] { 16777216L });
/* 10492 */   public static final BitSet FOLLOW_port_type_in_export_port_definition1116 = new BitSet(new long[] { 16777216L });
/* 10493 */   public static final BitSet FOLLOW_IDENTIFIER_in_export_port_definition1118 = new BitSet(new long[] { 140737488355328L });
/* 10494 */   public static final BitSet FOLLOW_export_list_in_export_port_definition1121 = new BitSet(new long[] { 2L });
/* 10495 */   public static final BitSet FOLLOW_IS_in_export_list1147 = new BitSet(new long[] { 4311744512L });
/* 10496 */   public static final BitSet FOLLOW_port_reference_in_export_list1150 = new BitSet(new long[] { 8589934594L });
/* 10497 */   public static final BitSet FOLLOW_COMMA_in_export_list1153 = new BitSet(new long[] { 4311744512L });
/* 10498 */   public static final BitSet FOLLOW_port_reference_in_export_list1156 = new BitSet(new long[] { 8589934594L });
/* 10499 */   public static final BitSet FOLLOW_EXPORT_in_export_data_definition1171 = new BitSet(new long[] { 281474976710656L });
/* 10500 */   public static final BitSet FOLLOW_DATA_in_export_data_definition1173 = new BitSet(new long[] { 16777216L });
/* 10501 */   public static final BitSet FOLLOW_data_type_in_export_data_definition1175 = new BitSet(new long[] { 16777216L });
/* 10502 */   public static final BitSet FOLLOW_IDENTIFIER_in_export_data_definition1177 = new BitSet(new long[] { 140737488355328L });
/* 10503 */   public static final BitSet FOLLOW_data_reference_in_export_data_definition1179 = new BitSet(new long[] { 2L });
/* 10504 */   public static final BitSet FOLLOW_IS_in_data_reference1204 = new BitSet(new long[] { 16777216L });
/* 10505 */   public static final BitSet FOLLOW_IDENTIFIER_in_data_reference1207 = new BitSet(new long[] { 4503633987108866L });
/* 10506 */   public static final BitSet FOLLOW_index_in_data_reference1211 = new BitSet(new long[] { 4503633987108866L });
/* 10507 */   public static final BitSet FOLLOW_data_ref_extension_in_data_reference1216 = new BitSet(new long[] { 2L });
/* 10508 */   public static final BitSet FOLLOW_DOT_in_data_ref_extension1230 = new BitSet(new long[] { 16777216L });
/* 10509 */   public static final BitSet FOLLOW_IDENTIFIER_in_data_ref_extension1233 = new BitSet(new long[] { 2L });
/* 10510 */   public static final BitSet FOLLOW_COMPONENT_in_component_definition1253 = new BitSet(new long[] { 16777216L });
/* 10511 */   public static final BitSet FOLLOW_component_type_in_component_definition1256 = new BitSet(new long[] { 16777216L });
/* 10512 */   public static final BitSet FOLLOW_IDENTIFIER_in_component_definition1258 = new BitSet(new long[] { 4503603922337794L });
/* 10513 */   public static final BitSet FOLLOW_index_in_component_definition1262 = new BitSet(new long[] { 4503603922337794L });
/* 10514 */   public static final BitSet FOLLOW_parameters_in_component_definition1277 = new BitSet(new long[] { 2L });
/* 10515 */   public static final BitSet FOLLOW_CONNECTOR_in_connector_definition1293 = new BitSet(new long[] { 16777216L });
/* 10516 */   public static final BitSet FOLLOW_connector_type_in_connector_definition1296 = new BitSet(new long[] { 16777216L });
/* 10517 */   public static final BitSet FOLLOW_IDENTIFIER_in_connector_definition1298 = new BitSet(new long[] { 4503603922337792L });
/* 10518 */   public static final BitSet FOLLOW_index_in_connector_definition1302 = new BitSet(new long[] { 4503603922337792L });
/* 10519 */   public static final BitSet FOLLOW_connector_port_ref_in_connector_definition1311 = new BitSet(new long[] { 4294967298L });
/* 10520 */   public static final BitSet FOLLOW_parameters_in_connector_definition1323 = new BitSet(new long[] { 2L });
/* 10521 */   public static final BitSet FOLLOW_LPAR_in_connector_port_ref1340 = new BitSet(new long[] { 4311744512L });
/* 10522 */   public static final BitSet FOLLOW_port_reference_in_connector_port_ref1342 = new BitSet(new long[] { 25769803776L });
/* 10523 */   public static final BitSet FOLLOW_COMMA_in_connector_port_ref1345 = new BitSet(new long[] { 4311744512L });
/* 10524 */   public static final BitSet FOLLOW_port_reference_in_connector_port_ref1347 = new BitSet(new long[] { 25769803776L });
/* 10525 */   public static final BitSet FOLLOW_RPAR_in_connector_port_ref1351 = new BitSet(new long[] { 2L });
/* 10526 */   public static final BitSet FOLLOW_LPAR_in_parameters1393 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10527 */   public static final BitSet FOLLOW_expression_in_parameters1395 = new BitSet(new long[] { 25769803776L });
/* 10528 */   public static final BitSet FOLLOW_COMMA_in_parameters1398 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10529 */   public static final BitSet FOLLOW_expression_in_parameters1400 = new BitSet(new long[] { 25769803776L });
/* 10530 */   public static final BitSet FOLLOW_RPAR_in_parameters1404 = new BitSet(new long[] { 2L });
/* 10531 */   public static final BitSet FOLLOW_simple_port_reference_in_port_reference1431 = new BitSet(new long[] { 2L });
/* 10532 */   public static final BitSet FOLLOW_conditional_port_ref_in_port_reference1435 = new BitSet(new long[] { 2L });
/* 10533 */   public static final BitSet FOLLOW_IDENTIFIER_in_simple_port_reference1445 = new BitSet(new long[] { 4503633987108866L });
/* 10534 */   public static final BitSet FOLLOW_index_in_simple_port_reference1449 = new BitSet(new long[] { 4503633987108866L });
/* 10535 */   public static final BitSet FOLLOW_port_ref_extension_in_simple_port_reference1454 = new BitSet(new long[] { 2L });
/* 10536 */   public static final BitSet FOLLOW_LPAR_in_conditional_port_ref1469 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10537 */   public static final BitSet FOLLOW_expression_in_conditional_port_ref1472 = new BitSet(new long[] { 1125899906842624L });
/* 10538 */   public static final BitSet FOLLOW_QMARK_in_conditional_port_ref1474 = new BitSet(new long[] { 4311744512L });
/* 10539 */   public static final BitSet FOLLOW_port_reference_in_conditional_port_ref1477 = new BitSet(new long[] { 2251799813685248L });
/* 10540 */   public static final BitSet FOLLOW_COLON_in_conditional_port_ref1479 = new BitSet(new long[] { 4311744512L });
/* 10541 */   public static final BitSet FOLLOW_port_reference_in_conditional_port_ref1482 = new BitSet(new long[] { 17179869184L });
/* 10542 */   public static final BitSet FOLLOW_RPAR_in_conditional_port_ref1484 = new BitSet(new long[] { 2L });
/* 10543 */   public static final BitSet FOLLOW_DOT_in_port_ref_extension1496 = new BitSet(new long[] { 16777216L });
/* 10544 */   public static final BitSet FOLLOW_IDENTIFIER_in_port_ref_extension1499 = new BitSet(new long[] { 2L });
/* 10545 */   public static final BitSet FOLLOW_LBRACKET_in_index1510 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10546 */   public static final BitSet FOLLOW_expression_in_index1513 = new BitSet(new long[] { 9007199254740992L });
/* 10547 */   public static final BitSet FOLLOW_RBRACKET_in_index1515 = new BitSet(new long[] { 2L });
/* 10548 */   public static final BitSet FOLLOW_PRIORITY_in_priority_definition1527 = new BitSet(new long[] { 16777216L });
/* 10549 */   public static final BitSet FOLLOW_IDENTIFIER_in_priority_definition1530 = new BitSet(new long[] { 4503599644147712L });
/* 10550 */   public static final BitSet FOLLOW_index_in_priority_definition1533 = new BitSet(new long[] { 4503599644147712L });
/* 10551 */   public static final BitSet FOLLOW_priority_rule_in_priority_definition1540 = new BitSet(new long[] { 36029896530591746L });
/* 10552 */   public static final BitSet FOLLOW_provided_expression_in_priority_definition1546 = new BitSet(new long[] { 36028797018963970L });
/* 10553 */   public static final BitSet FOLLOW_delay_expression_in_priority_definition1555 = new BitSet(new long[] { 2L });
/* 10554 */   public static final BitSet FOLLOW_DELAY_in_delay_expression1570 = new BitSet(new long[] { 0L, 48L });
/* 10555 */   public static final BitSet FOLLOW_time_value_in_delay_expression1572 = new BitSet(new long[] { 2L });
/* 10556 */   public static final BitSet FOLLOW_priority_inter_low_in_priority_rule1582 = new BitSet(new long[] { 72057594037927936L });
/* 10557 */   public static final BitSet FOLLOW_LT_in_priority_rule1584 = new BitSet(new long[] { 148618787720003584L });
/* 10558 */   public static final BitSet FOLLOW_priority_inter_high_in_priority_rule1586 = new BitSet(new long[] { 2L });
/* 10559 */   public static final BitSet FOLLOW_port_reference_interaction_in_priority_inter_low1619 = new BitSet(new long[] { 2L });
/* 10560 */   public static final BitSet FOLLOW_port_reference_interaction_in_priority_inter_high1629 = new BitSet(new long[] { 2L });
/* 10561 */   public static final BitSet FOLLOW_MULT_in_priority_inter_high1633 = new BitSet(new long[] { 2L });
/* 10562 */   public static final BitSet FOLLOW_priority_connector_in_port_reference_interaction1648 = new BitSet(new long[] { 6755399441055746L });
/* 10563 */   public static final BitSet FOLLOW_index_in_port_reference_interaction1651 = new BitSet(new long[] { 6755399441055746L });
/* 10564 */   public static final BitSet FOLLOW_COLON_in_port_reference_interaction1657 = new BitSet(new long[] { 4311744512L });
/* 10565 */   public static final BitSet FOLLOW_port_reference_in_port_reference_interaction1660 = new BitSet(new long[] { 4311744514L });
/* 10566 */   public static final BitSet FOLLOW_port_reference_in_port_reference_interaction1663 = new BitSet(new long[] { 4311744514L });
/* 10567 */   public static final BitSet FOLLOW_IDENTIFIER_in_priority_connector1679 = new BitSet(new long[] { 34359738370L });
/* 10568 */   public static final BitSet FOLLOW_DOT_in_priority_connector1682 = new BitSet(new long[] { 16777216L });
/* 10569 */   public static final BitSet FOLLOW_IDENTIFIER_in_priority_connector1685 = new BitSet(new long[] { 2L });
/* 10570 */   public static final BitSet FOLLOW_EXPORT_in_port_definition1706 = new BitSet(new long[] { 1073741824L });
/* 10571 */   public static final BitSet FOLLOW_PORT_in_port_definition1708 = new BitSet(new long[] { 16777216L });
/* 10572 */   public static final BitSet FOLLOW_port_type_in_port_definition1710 = new BitSet(new long[] { 16777216L });
/* 10573 */   public static final BitSet FOLLOW_port_in_port_definition1712 = new BitSet(new long[] { 288230380446679042L });
/* 10574 */   public static final BitSet FOLLOW_data_port_in_port_definition1714 = new BitSet(new long[] { 288230376151711746L });
/* 10575 */   public static final BitSet FOLLOW_ASSIGN_in_port_definition1718 = new BitSet(new long[] { 16777216L });
/* 10576 */   public static final BitSet FOLLOW_IDENTIFIER_in_port_definition1720 = new BitSet(new long[] { 2L });
/* 10577 */   public static final BitSet FOLLOW_PORT_in_port_definition1753 = new BitSet(new long[] { 16777216L });
/* 10578 */   public static final BitSet FOLLOW_port_type_in_port_definition1755 = new BitSet(new long[] { 16777216L });
/* 10579 */   public static final BitSet FOLLOW_port_in_port_definition1757 = new BitSet(new long[] { 4294967298L });
/* 10580 */   public static final BitSet FOLLOW_data_port_in_port_definition1759 = new BitSet(new long[] { 2L });
/* 10581 */   public static final BitSet FOLLOW_LPAR_in_data_port1791 = new BitSet(new long[] { 144115209567469568L, 256759054352L });
/* 10582 */   public static final BitSet FOLLOW_expression_in_data_port1796 = new BitSet(new long[] { 25769803776L });
/* 10583 */   public static final BitSet FOLLOW_COMMA_in_data_port1799 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10584 */   public static final BitSet FOLLOW_expression_in_data_port1802 = new BitSet(new long[] { 25769803776L });
/* 10585 */   public static final BitSet FOLLOW_RPAR_in_data_port1809 = new BitSet(new long[] { 2L });
/* 10586 */   public static final BitSet FOLLOW_IDENTIFIER_in_port1821 = new BitSet(new long[] { 2L });
/* 10587 */   public static final BitSet FOLLOW_PLACE_in_place_definition1835 = new BitSet(new long[] { 16777216L });
/* 10588 */   public static final BitSet FOLLOW_place_declaration_in_place_definition1839 = new BitSet(new long[] { 8589934594L });
/* 10589 */   public static final BitSet FOLLOW_COMMA_in_place_definition1842 = new BitSet(new long[] { 16777216L });
/* 10590 */   public static final BitSet FOLLOW_place_declaration_in_place_definition1845 = new BitSet(new long[] { 8589934594L });
/* 10591 */   public static final BitSet FOLLOW_IDENTIFIER_in_place_declaration1861 = new BitSet(new long[] { 288230376151711746L });
/* 10592 */   public static final BitSet FOLLOW_ASSIGN_in_place_declaration1865 = new BitSet(new long[] { 1152921504606846976L });
/* 10593 */   public static final BitSet FOLLOW_INITIAL_in_place_declaration1867 = new BitSet(new long[] { 2L });
/* 10594 */   public static final BitSet FOLLOW_IDENTIFIER_in_place1883 = new BitSet(new long[] { 2L });
/* 10595 */   public static final BitSet FOLLOW_INITIAL_in_initialization1894 = new BitSet(new long[] { 4611686018427387904L });
/* 10596 */   public static final BitSet FOLLOW_to_places_in_initialization1897 = new BitSet(new long[] { 2L, 1024L });
/* 10597 */   public static final BitSet FOLLOW_do_action_in_initialization1901 = new BitSet(new long[] { 2L });
/* 10598 */   public static final BitSet FOLLOW_ON_in_transition_definition1915 = new BitSet(new long[] { 4503599644147712L });
/* 10599 */   public static final BitSet FOLLOW_port_expression_in_transition_definition1918 = new BitSet(new long[] { 2305843009213693952L });
/* 10600 */   public static final BitSet FOLLOW_from_places_in_transition_definition1923 = new BitSet(new long[] { 4611686018427387904L });
/* 10601 */   public static final BitSet FOLLOW_to_places_in_transition_definition1928 = new BitSet(new long[] { -9223370937343148030L, 1539L });
/* 10602 */   public static final BitSet FOLLOW_provided_expression_in_transition_definition1934 = new BitSet(new long[] { -9223372036854775806L, 1539L });
/* 10603 */   public static final BitSet FOLLOW_timed_condition_in_transition_definition1943 = new BitSet(new long[] { 2L, 1536L });
/* 10604 */   public static final BitSet FOLLOW_time_reset_in_transition_definition1952 = new BitSet(new long[] { 2L, 1024L });
/* 10605 */   public static final BitSet FOLLOW_do_action_in_transition_definition1961 = new BitSet(new long[] { 2L });
/* 10606 */   public static final BitSet FOLLOW_FROM_in_from_places1975 = new BitSet(new long[] { 16777216L });
/* 10607 */   public static final BitSet FOLLOW_place_in_from_places1978 = new BitSet(new long[] { 8589934594L });
/* 10608 */   public static final BitSet FOLLOW_COMMA_in_from_places1981 = new BitSet(new long[] { 16777216L });
/* 10609 */   public static final BitSet FOLLOW_place_in_from_places1984 = new BitSet(new long[] { 8589934594L });
/* 10610 */   public static final BitSet FOLLOW_TO_in_to_places2001 = new BitSet(new long[] { 16777216L });
/* 10611 */   public static final BitSet FOLLOW_place_in_to_places2004 = new BitSet(new long[] { 8589934594L });
/* 10612 */   public static final BitSet FOLLOW_COMMA_in_to_places2007 = new BitSet(new long[] { 16777216L });
/* 10613 */   public static final BitSet FOLLOW_place_in_to_places2010 = new BitSet(new long[] { 8589934594L });
/* 10614 */   public static final BitSet FOLLOW_urgency_in_timed_condition2027 = new BitSet(new long[] { 16777218L });
/* 10615 */   public static final BitSet FOLLOW_timed_guards_in_timed_condition2031 = new BitSet(new long[] { 2L });
/* 10616 */   public static final BitSet FOLLOW_set_in_urgency0 = new BitSet(new long[] { 2L });
/* 10617 */   public static final BitSet FOLLOW_time_constraint_in_timed_guards2067 = new BitSet(new long[] { 2L, 4L });
/* 10618 */   public static final BitSet FOLLOW_AND_in_timed_guards2070 = new BitSet(new long[] { 16777216L });
/* 10619 */   public static final BitSet FOLLOW_time_constraint_in_timed_guards2073 = new BitSet(new long[] { 2L, 4L });
/* 10620 */   public static final BitSet FOLLOW_IDENTIFIER_in_time_constraint2088 = new BitSet(new long[] { 0L, 8L });
/* 10621 */   public static final BitSet FOLLOW_IN_in_time_constraint2090 = new BitSet(new long[] { 4294967296L });
/* 10622 */   public static final BitSet FOLLOW_LPAR_in_time_constraint2093 = new BitSet(new long[] { 0L, 48L });
/* 10623 */   public static final BitSet FOLLOW_time_value_in_time_constraint2096 = new BitSet(new long[] { 8589934592L });
/* 10624 */   public static final BitSet FOLLOW_COMMA_in_time_constraint2098 = new BitSet(new long[] { 0L, 48L });
/* 10625 */   public static final BitSet FOLLOW_time_value_in_time_constraint2101 = new BitSet(new long[] { 17179869184L });
/* 10626 */   public static final BitSet FOLLOW_RPAR_in_time_constraint2103 = new BitSet(new long[] { 2L });
/* 10627 */   public static final BitSet FOLLOW_set_in_time_value2115 = new BitSet(new long[] { 2L, 448L });
/* 10628 */   public static final BitSet FOLLOW_time_unit_in_time_value2124 = new BitSet(new long[] { 2L });
/* 10629 */   public static final BitSet FOLLOW_set_in_time_unit0 = new BitSet(new long[] { 2L });
/* 10630 */   public static final BitSet FOLLOW_RESET_in_time_reset2173 = new BitSet(new long[] { 16777216L });
/* 10631 */   public static final BitSet FOLLOW_IDENTIFIER_in_time_reset2176 = new BitSet(new long[] { 8589934594L });
/* 10632 */   public static final BitSet FOLLOW_COMMA_in_time_reset2179 = new BitSet(new long[] { 16777216L });
/* 10633 */   public static final BitSet FOLLOW_IDENTIFIER_in_time_reset2182 = new BitSet(new long[] { 8589934594L });
/* 10634 */   public static final BitSet FOLLOW_DO_in_do_action2214 = new BitSet(new long[] { 144115192656035840L, 1631148589072L });
/* 10635 */   public static final BitSet FOLLOW_action_in_do_action2217 = new BitSet(new long[] { 2L });
/* 10636 */   public static final BitSet FOLLOW_CLOCK_in_clock_definition2241 = new BitSet(new long[] { 16777216L });
/* 10637 */   public static final BitSet FOLLOW_IDENTIFIER_in_clock_definition2244 = new BitSet(new long[] { 8589934594L });
/* 10638 */   public static final BitSet FOLLOW_COMMA_in_clock_definition2247 = new BitSet(new long[] { 16777216L });
/* 10639 */   public static final BitSet FOLLOW_IDENTIFIER_in_clock_definition2250 = new BitSet(new long[] { 8589934594L });
/* 10640 */   public static final BitSet FOLLOW_EXPORT_in_data_definition2276 = new BitSet(new long[] { 281474976710656L, 12288L });
/* 10641 */   public static final BitSet FOLLOW_EXTERN_in_data_definition2281 = new BitSet(new long[] { 281474976710656L, 8192L });
/* 10642 */   public static final BitSet FOLLOW_TIMED_in_data_definition2286 = new BitSet(new long[] { 281474976710656L });
/* 10643 */   public static final BitSet FOLLOW_DATA_in_data_definition2292 = new BitSet(new long[] { 16777216L });
/* 10644 */   public static final BitSet FOLLOW_data_type_in_data_definition2295 = new BitSet(new long[] { 16777216L });
/* 10645 */   public static final BitSet FOLLOW_one_data_in_data_definition2297 = new BitSet(new long[] { 8589934594L });
/* 10646 */   public static final BitSet FOLLOW_COMMA_in_data_definition2300 = new BitSet(new long[] { 16777216L });
/* 10647 */   public static final BitSet FOLLOW_one_data_in_data_definition2304 = new BitSet(new long[] { 8589934594L });
/* 10648 */   public static final BitSet FOLLOW_IDENTIFIER_in_one_data2319 = new BitSet(new long[] { 288230376151711746L });
/* 10649 */   public static final BitSet FOLLOW_ASSIGN_in_one_data2323 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10650 */   public static final BitSet FOLLOW_expression_in_one_data2326 = new BitSet(new long[] { 2L });
/* 10651 */   public static final BitSet FOLLOW_IDENTIFIER_in_data_type2340 = new BitSet(new long[] { 2L });
/* 10652 */   public static final BitSet FOLLOW_data_type_in_data_fpar_definition2352 = new BitSet(new long[] { 16777216L });
/* 10653 */   public static final BitSet FOLLOW_IDENTIFIER_in_data_fpar_definition2355 = new BitSet(new long[] { 2L });
/* 10654 */   public static final BitSet FOLLOW_port_additive_expression_in_port_expression2370 = new BitSet(new long[] { 2L });
/* 10655 */   public static final BitSet FOLLOW_port_multiplicative_expression_in_port_additive_expression2382 = new BitSet(new long[] { 2L, 16384L });
/* 10656 */   public static final BitSet FOLLOW_PLUS_in_port_additive_expression2386 = new BitSet(new long[] { 4503599644147712L });
/* 10657 */   public static final BitSet FOLLOW_port_multiplicative_expression_in_port_additive_expression2389 = new BitSet(new long[] { 2L, 16384L });
/* 10658 */   public static final BitSet FOLLOW_port_list_expression_in_port_multiplicative_expression2405 = new BitSet(new long[] { 2L });
/* 10659 */   public static final BitSet FOLLOW_port_postfix_expression_in_port_list_expression2426 = new BitSet(new long[] { 4503599644147714L });
/* 10660 */   public static final BitSet FOLLOW_port_postfix_expression_in_port_list_expression2430 = new BitSet(new long[] { 4503599644147714L });
/* 10661 */   public static final BitSet FOLLOW_port_primary_expression_in_port_postfix_expression2446 = new BitSet(new long[] { 2L, 32768L });
/* 10662 */   public static final BitSet FOLLOW_QUOTE_in_port_postfix_expression2449 = new BitSet(new long[] { 2L });
/* 10663 */   public static final BitSet FOLLOW_port_in_port_primary_expression2466 = new BitSet(new long[] { 2L });
/* 10664 */   public static final BitSet FOLLOW_LBRACKET_in_port_primary_expression2471 = new BitSet(new long[] { 4503599644147712L });
/* 10665 */   public static final BitSet FOLLOW_port_expression_in_port_primary_expression2474 = new BitSet(new long[] { 9007199254740992L });
/* 10666 */   public static final BitSet FOLLOW_RBRACKET_in_port_primary_expression2476 = new BitSet(new long[] { 2L });
/* 10667 */   public static final BitSet FOLLOW_logical_or_expression_in_expression2493 = new BitSet(new long[] { 2L });
/* 10668 */   public static final BitSet FOLLOW_logical_and_expression_in_logical_or_expression2503 = new BitSet(new long[] { 2L, 65536L });
/* 10669 */   public static final BitSet FOLLOW_OR_in_logical_or_expression2506 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10670 */   public static final BitSet FOLLOW_logical_and_expression_in_logical_or_expression2509 = new BitSet(new long[] { 2L, 65536L });
/* 10671 */   public static final BitSet FOLLOW_bitwise_inclusive_or_expression_in_logical_and_expression2523 = new BitSet(new long[] { 2L, 4L });
/* 10672 */   public static final BitSet FOLLOW_AND_in_logical_and_expression2526 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10673 */   public static final BitSet FOLLOW_bitwise_inclusive_or_expression_in_logical_and_expression2529 = new BitSet(new long[] { 2L, 4L });
/* 10674 */   public static final BitSet FOLLOW_bitwise_exclusive_or_expression_in_bitwise_inclusive_or_expression2543 = new BitSet(new long[] { 2L, 131072L });
/* 10675 */   public static final BitSet FOLLOW_BITWISEOR_in_bitwise_inclusive_or_expression2546 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10676 */   public static final BitSet FOLLOW_bitwise_exclusive_or_expression_in_bitwise_inclusive_or_expression2549 = new BitSet(new long[] { 2L, 131072L });
/* 10677 */   public static final BitSet FOLLOW_bitwise_and_expression_in_bitwise_exclusive_or_expression2564 = new BitSet(new long[] { 2L, 262144L });
/* 10678 */   public static final BitSet FOLLOW_BITWISEXOR_in_bitwise_exclusive_or_expression2567 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10679 */   public static final BitSet FOLLOW_bitwise_and_expression_in_bitwise_exclusive_or_expression2570 = new BitSet(new long[] { 2L, 262144L });
/* 10680 */   public static final BitSet FOLLOW_equality_expression_in_bitwise_and_expression2584 = new BitSet(new long[] { 2L, 524288L });
/* 10681 */   public static final BitSet FOLLOW_REF_in_bitwise_and_expression2587 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10682 */   public static final BitSet FOLLOW_equality_expression_in_bitwise_and_expression2590 = new BitSet(new long[] { 2L, 524288L });
/* 10683 */   public static final BitSet FOLLOW_relational_expression_in_equality_expression2606 = new BitSet(new long[] { 2L, 3145728L });
/* 10684 */   public static final BitSet FOLLOW_set_in_equality_expression2609 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10685 */   public static final BitSet FOLLOW_relational_expression_in_equality_expression2616 = new BitSet(new long[] { 2L, 3145728L });
/* 10686 */   public static final BitSet FOLLOW_bitwise_shift_expression_in_relational_expression2630 = new BitSet(new long[] { 72057594037927938L, 29360128L });
/* 10687 */   public static final BitSet FOLLOW_set_in_relational_expression2633 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10688 */   public static final BitSet FOLLOW_bitwise_shift_expression_in_relational_expression2644 = new BitSet(new long[] { 72057594037927938L, 29360128L });
/* 10689 */   public static final BitSet FOLLOW_additive_expression_in_bitwise_shift_expression2670 = new BitSet(new long[] { 2L, 100663296L });
/* 10690 */   public static final BitSet FOLLOW_set_in_bitwise_shift_expression2673 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10691 */   public static final BitSet FOLLOW_additive_expression_in_bitwise_shift_expression2680 = new BitSet(new long[] { 2L, 100663296L });
/* 10692 */   public static final BitSet FOLLOW_multiplicative_expression_in_additive_expression2702 = new BitSet(new long[] { 2L, 134234112L });
/* 10693 */   public static final BitSet FOLLOW_set_in_additive_expression2705 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10694 */   public static final BitSet FOLLOW_multiplicative_expression_in_additive_expression2712 = new BitSet(new long[] { 2L, 134234112L });
/* 10695 */   public static final BitSet FOLLOW_infix_expression_in_multiplicative_expression2726 = new BitSet(new long[] { 144115188075855874L, 805306368L });
/* 10696 */   public static final BitSet FOLLOW_set_in_multiplicative_expression2729 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10697 */   public static final BitSet FOLLOW_infix_expression_in_multiplicative_expression2738 = new BitSet(new long[] { 144115188075855874L, 805306368L });
/* 10698 */   public static final BitSet FOLLOW_unary_op_in_infix_expression2751 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10699 */   public static final BitSet FOLLOW_infix_expression_in_infix_expression2754 = new BitSet(new long[] { 2L });
/* 10700 */   public static final BitSet FOLLOW_postfix_expression_in_infix_expression2767 = new BitSet(new long[] { 2L });
/* 10701 */   public static final BitSet FOLLOW_PLUS_in_unary_op2781 = new BitSet(new long[] { 2L });
/* 10702 */   public static final BitSet FOLLOW_MINUS_in_unary_op2791 = new BitSet(new long[] { 2L });
/* 10703 */   public static final BitSet FOLLOW_NOT_in_unary_op2801 = new BitSet(new long[] { 2L });
/* 10704 */   public static final BitSet FOLLOW_BITWISENOT_in_unary_op2806 = new BitSet(new long[] { 2L });
/* 10705 */   public static final BitSet FOLLOW_MULT_in_unary_op2812 = new BitSet(new long[] { 2L });
/* 10706 */   public static final BitSet FOLLOW_REF_in_unary_op2822 = new BitSet(new long[] { 2L });
/* 10707 */   public static final BitSet FOLLOW_INCREMENT_in_unary_op2833 = new BitSet(new long[] { 2L });
/* 10708 */   public static final BitSet FOLLOW_DECREMENT_in_unary_op2843 = new BitSet(new long[] { 2L });
/* 10709 */   public static final BitSet FOLLOW_literal_expression_in_postfix_expression2858 = new BitSet(new long[] { 2L });
/* 10710 */   public static final BitSet FOLLOW_primary_expression_in_postfix_expression2870 = new BitSet(new long[] { 4503638282076162L, 30064771072L });
/* 10711 */   public static final BitSet FOLLOW_LBRACKET_in_postfix_expression2874 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10712 */   public static final BitSet FOLLOW_expression_in_postfix_expression2877 = new BitSet(new long[] { 9007199254740992L });
/* 10713 */   public static final BitSet FOLLOW_RBRACKET_in_postfix_expression2879 = new BitSet(new long[] { 4503638282076162L, 30064771072L });
/* 10714 */   public static final BitSet FOLLOW_DOT_in_postfix_expression2891 = new BitSet(new long[] { 16777216L });
/* 10715 */   public static final BitSet FOLLOW_IDENTIFIER_in_postfix_expression2894 = new BitSet(new long[] { 4503638282076162L, 30064771072L });
/* 10716 */   public static final BitSet FOLLOW_FIELD_in_postfix_expression2906 = new BitSet(new long[] { 16777216L });
/* 10717 */   public static final BitSet FOLLOW_IDENTIFIER_in_postfix_expression2909 = new BitSet(new long[] { 4503638282076162L, 30064771072L });
/* 10718 */   public static final BitSet FOLLOW_LPAR_in_postfix_expression2942 = new BitSet(new long[] { 144115209567469568L, 256759054352L });
/* 10719 */   public static final BitSet FOLLOW_expression_in_postfix_expression2947 = new BitSet(new long[] { 25769803776L });
/* 10720 */   public static final BitSet FOLLOW_COMMA_in_postfix_expression2950 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10721 */   public static final BitSet FOLLOW_expression_in_postfix_expression2953 = new BitSet(new long[] { 25769803776L });
/* 10722 */   public static final BitSet FOLLOW_RPAR_in_postfix_expression2960 = new BitSet(new long[] { 4503638282076162L, 30064771072L });
/* 10723 */   public static final BitSet FOLLOW_postfix_increment_decrement_in_postfix_expression2993 = new BitSet(new long[] { 4503638282076162L, 30064771072L });
/* 10724 */   public static final BitSet FOLLOW_INCREMENT_in_postfix_increment_decrement3008 = new BitSet(new long[] { 2L });
/* 10725 */   public static final BitSet FOLLOW_DECREMENT_in_postfix_increment_decrement3023 = new BitSet(new long[] { 2L });
/* 10726 */   public static final BitSet FOLLOW_set_in_literal_expression0 = new BitSet(new long[] { 2L });
/* 10727 */   public static final BitSet FOLLOW_IDENTIFIER_in_primary_expression3065 = new BitSet(new long[] { 2L });
/* 10728 */   public static final BitSet FOLLOW_LPAR_in_primary_expression3070 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10729 */   public static final BitSet FOLLOW_expression_in_primary_expression3073 = new BitSet(new long[] { 17179869184L });
/* 10730 */   public static final BitSet FOLLOW_RPAR_in_primary_expression3075 = new BitSet(new long[] { 2L });
/* 10731 */   public static final BitSet FOLLOW_expression_in_action3091 = new BitSet(new long[] { 288230376151711744L, 4639939069214720L });
/* 10732 */   public static final BitSet FOLLOW_assign_op_in_action3094 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10733 */   public static final BitSet FOLLOW_expression_in_action3097 = new BitSet(new long[] { 288230376151711744L, 4639939069214720L });
/* 10734 */   public static final BitSet FOLLOW_116_in_action3101 = new BitSet(new long[] { 2L });
/* 10735 */   public static final BitSet FOLLOW_IF_in_action3107 = new BitSet(new long[] { 4294967296L });
/* 10736 */   public static final BitSet FOLLOW_LPAR_in_action3110 = new BitSet(new long[] { 144115192387600384L, 256759054352L });
/* 10737 */   public static final BitSet FOLLOW_expression_in_action3113 = new BitSet(new long[] { 17179869184L });
/* 10738 */   public static final BitSet FOLLOW_RPAR_in_action3115 = new BitSet(new long[] { 144115192656035840L, 1631148589072L });
/* 10739 */   public static final BitSet FOLLOW_action_in_action3118 = new BitSet(new long[] { 2L, 549755813888L });
/* 10740 */   public static final BitSet FOLLOW_ELSE_in_action3125 = new BitSet(new long[] { 144115192656035840L, 1631148589072L });
/* 10741 */   public static final BitSet FOLLOW_action_in_action3127 = new BitSet(new long[] { 2L });
/* 10742 */   public static final BitSet FOLLOW_CODE_in_action3134 = new BitSet(new long[] { 2L });
/* 10743 */   public static final BitSet FOLLOW_LCURLY_in_action3139 = new BitSet(new long[] { 144115192656035840L, 3830171844624L });
/* 10744 */   public static final BitSet FOLLOW_action_in_action3144 = new BitSet(new long[] { 144115192656035840L, 3830171844624L });
/* 10745 */   public static final BitSet FOLLOW_RCURLY_in_action3149 = new BitSet(new long[] { 2L });
/* 10746 */   public static final BitSet FOLLOW_set_in_assign_op0 = new BitSet(new long[] { 2L });
/* 10747 */   public static final BitSet FOLLOW_ELSE_in_synpred1_bip3122 = new BitSet(new long[] { 2L });
/*       */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\bipParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */