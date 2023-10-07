/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class TokenRewriteStream
/*     */   extends CommonTokenStream
/*     */ {
/*     */   public static final String DEFAULT_PROGRAM_NAME = "default";
/*     */   public static final int PROGRAM_INIT_SIZE = 100;
/*     */   public static final int MIN_TOKEN_INDEX = 0;
/*     */   
/*     */   class RewriteOperation
/*     */   {
/*     */     protected int instructionIndex;
/*     */     protected int index;
/*     */     protected Object text;
/*     */     private final TokenRewriteStream this$0;
/*     */     
/*     */     protected RewriteOperation(int index, Object text) {
/*  97 */       this.index = index;
/*  98 */       this.text = text;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int execute(StringBuffer buf) {
/* 104 */       return this.index;
/*     */     }
/*     */     public String toString() {
/* 107 */       String opName = getClass().getName();
/* 108 */       int $index = opName.indexOf('$');
/* 109 */       opName = opName.substring($index + 1, opName.length());
/* 110 */       return "<" + opName + "@" + this.index + ":\"" + this.text + "\">";
/*     */     }
/*     */   }
/*     */   
/*     */   class InsertBeforeOp extends RewriteOperation {
/*     */     public InsertBeforeOp(int index, Object text) {
/* 116 */       super(index, text);
/*     */     } private final TokenRewriteStream this$0;
/*     */     public int execute(StringBuffer buf) {
/* 119 */       buf.append(this.text);
/* 120 */       buf.append(((Token)TokenRewriteStream.this.tokens.get(this.index)).getText());
/* 121 */       return this.index + 1;
/*     */     }
/*     */   }
/*     */   
/*     */   class ReplaceOp
/*     */     extends RewriteOperation {
/*     */     protected int lastIndex;
/*     */     private final TokenRewriteStream this$0;
/*     */     
/*     */     public ReplaceOp(int from, int to, Object text) {
/* 131 */       super(from, text);
/* 132 */       this.lastIndex = to;
/*     */     }
/*     */     public int execute(StringBuffer buf) {
/* 135 */       if (this.text != null) {
/* 136 */         buf.append(this.text);
/*     */       }
/* 138 */       return this.lastIndex + 1;
/*     */     }
/*     */     public String toString() {
/* 141 */       return "<ReplaceOp@" + this.index + ".." + this.lastIndex + ":\"" + this.text + "\">";
/*     */     } }
/*     */   
/*     */   class DeleteOp extends ReplaceOp { private final TokenRewriteStream this$0;
/*     */     
/*     */     public DeleteOp(int from, int to) {
/* 147 */       super(from, to, (Object)null);
/*     */     }
/*     */     public String toString() {
/* 150 */       return "<DeleteOp@" + this.index + ".." + this.lastIndex + ">";
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   protected Map programs = null;
/*     */ 
/*     */   
/* 161 */   protected Map lastRewriteTokenIndexes = null;
/*     */   
/*     */   public TokenRewriteStream() {
/* 164 */     init();
/*     */   }
/*     */   
/*     */   protected void init() {
/* 168 */     this.programs = new HashMap();
/* 169 */     this.programs.put("default", new ArrayList(100));
/* 170 */     this.lastRewriteTokenIndexes = new HashMap();
/*     */   }
/*     */   
/*     */   public TokenRewriteStream(TokenSource tokenSource) {
/* 174 */     super(tokenSource);
/* 175 */     init();
/*     */   }
/*     */   
/*     */   public TokenRewriteStream(TokenSource tokenSource, int channel) {
/* 179 */     super(tokenSource, channel);
/* 180 */     init();
/*     */   }
/*     */   
/*     */   public void rollback(int instructionIndex) {
/* 184 */     rollback("default", instructionIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rollback(String programName, int instructionIndex) {
/* 192 */     List is = (List)this.programs.get(programName);
/* 193 */     if (is != null) {
/* 194 */       this.programs.put(programName, is.subList(0, instructionIndex));
/*     */     }
/*     */   }
/*     */   
/*     */   public void deleteProgram() {
/* 199 */     deleteProgram("default");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteProgram(String programName) {
/* 204 */     rollback(programName, 0);
/*     */   }
/*     */   
/*     */   public void insertAfter(Token t, Object text) {
/* 208 */     insertAfter("default", t, text);
/*     */   }
/*     */   
/*     */   public void insertAfter(int index, Object text) {
/* 212 */     insertAfter("default", index, text);
/*     */   }
/*     */   
/*     */   public void insertAfter(String programName, Token t, Object text) {
/* 216 */     insertAfter(programName, t.getTokenIndex(), text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertAfter(String programName, int index, Object text) {
/* 221 */     insertBefore(programName, index + 1, text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertBefore(Token t, Object text) {
/* 226 */     insertBefore("default", t, text);
/*     */   }
/*     */   
/*     */   public void insertBefore(int index, Object text) {
/* 230 */     insertBefore("default", index, text);
/*     */   }
/*     */   
/*     */   public void insertBefore(String programName, Token t, Object text) {
/* 234 */     insertBefore(programName, t.getTokenIndex(), text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertBefore(String programName, int index, Object text) {
/* 239 */     RewriteOperation op = new InsertBeforeOp(index, text);
/* 240 */     List rewrites = getProgram(programName);
/* 241 */     op.instructionIndex = rewrites.size();
/* 242 */     rewrites.add(op);
/*     */   }
/*     */   
/*     */   public void replace(int index, Object text) {
/* 246 */     replace("default", index, index, text);
/*     */   }
/*     */   
/*     */   public void replace(int from, int to, Object text) {
/* 250 */     replace("default", from, to, text);
/*     */   }
/*     */   
/*     */   public void replace(Token indexT, Object text) {
/* 254 */     replace("default", indexT, indexT, text);
/*     */   }
/*     */   
/*     */   public void replace(Token from, Token to, Object text) {
/* 258 */     replace("default", from, to, text);
/*     */   }
/*     */   
/*     */   public void replace(String programName, int from, int to, Object text) {
/* 262 */     if (from > to || from < 0 || to < 0 || to >= this.tokens.size()) {
/* 263 */       throw new IllegalArgumentException("replace: range invalid: " + from + ".." + to + "(size=" + this.tokens.size() + ")");
/*     */     }
/* 265 */     RewriteOperation op = new ReplaceOp(from, to, text);
/* 266 */     List rewrites = getProgram(programName);
/* 267 */     op.instructionIndex = rewrites.size();
/* 268 */     rewrites.add(op);
/*     */   }
/*     */   
/*     */   public void replace(String programName, Token from, Token to, Object text) {
/* 272 */     replace(programName, from.getTokenIndex(), to.getTokenIndex(), text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(int index) {
/* 279 */     delete("default", index, index);
/*     */   }
/*     */   
/*     */   public void delete(int from, int to) {
/* 283 */     delete("default", from, to);
/*     */   }
/*     */   
/*     */   public void delete(Token indexT) {
/* 287 */     delete("default", indexT, indexT);
/*     */   }
/*     */   
/*     */   public void delete(Token from, Token to) {
/* 291 */     delete("default", from, to);
/*     */   }
/*     */   
/*     */   public void delete(String programName, int from, int to) {
/* 295 */     replace(programName, from, to, (Object)null);
/*     */   }
/*     */   
/*     */   public void delete(String programName, Token from, Token to) {
/* 299 */     replace(programName, from, to, (Object)null);
/*     */   }
/*     */   
/*     */   public int getLastRewriteTokenIndex() {
/* 303 */     return getLastRewriteTokenIndex("default");
/*     */   }
/*     */   
/*     */   protected int getLastRewriteTokenIndex(String programName) {
/* 307 */     Integer I = (Integer)this.lastRewriteTokenIndexes.get(programName);
/* 308 */     if (I == null) {
/* 309 */       return -1;
/*     */     }
/* 311 */     return I.intValue();
/*     */   }
/*     */   
/*     */   protected void setLastRewriteTokenIndex(String programName, int i) {
/* 315 */     this.lastRewriteTokenIndexes.put(programName, new Integer(i));
/*     */   }
/*     */   
/*     */   protected List getProgram(String name) {
/* 319 */     List is = (List)this.programs.get(name);
/* 320 */     if (is == null) {
/* 321 */       is = initializeProgram(name);
/*     */     }
/* 323 */     return is;
/*     */   }
/*     */   
/*     */   private List initializeProgram(String name) {
/* 327 */     List is = new ArrayList(100);
/* 328 */     this.programs.put(name, is);
/* 329 */     return is;
/*     */   }
/*     */   
/*     */   public String toOriginalString() {
/* 333 */     return toOriginalString(0, size() - 1);
/*     */   }
/*     */   
/*     */   public String toOriginalString(int start, int end) {
/* 337 */     StringBuffer buf = new StringBuffer();
/* 338 */     for (int i = start; i >= 0 && i <= end && i < this.tokens.size(); i++) {
/* 339 */       buf.append(get(i).getText());
/*     */     }
/* 341 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 345 */     return toString(0, size() - 1);
/*     */   }
/*     */   
/*     */   public String toString(String programName) {
/* 349 */     return toString(programName, 0, size() - 1);
/*     */   }
/*     */   
/*     */   public String toString(int start, int end) {
/* 353 */     return toString("default", start, end);
/*     */   }
/*     */   
/*     */   public String toString(String programName, int start, int end) {
/* 357 */     List rewrites = (List)this.programs.get(programName);
/*     */ 
/*     */     
/* 360 */     if (end > this.tokens.size() - 1) end = this.tokens.size() - 1; 
/* 361 */     if (start < 0) start = 0;
/*     */     
/* 363 */     if (rewrites == null || rewrites.size() == 0) {
/* 364 */       return toOriginalString(start, end);
/*     */     }
/* 366 */     StringBuffer buf = new StringBuffer();
/*     */ 
/*     */     
/* 369 */     Map indexToOp = reduceToSingleOperationPerIndex(rewrites);
/*     */ 
/*     */     
/* 372 */     int i = start;
/* 373 */     while (i <= end && i < this.tokens.size()) {
/* 374 */       RewriteOperation op = (RewriteOperation)indexToOp.get(new Integer(i));
/* 375 */       indexToOp.remove(new Integer(i));
/* 376 */       Token t = this.tokens.get(i);
/* 377 */       if (op == null) {
/*     */         
/* 379 */         buf.append(t.getText());
/* 380 */         i++;
/*     */         continue;
/*     */       } 
/* 383 */       i = op.execute(buf);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     if (end == this.tokens.size() - 1) {
/*     */ 
/*     */       
/* 393 */       Iterator it = indexToOp.values().iterator();
/* 394 */       while (it.hasNext()) {
/* 395 */         RewriteOperation op = it.next();
/* 396 */         if (op.index >= this.tokens.size() - 1) buf.append(op.text); 
/*     */       } 
/*     */     } 
/* 399 */     return buf.toString();
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
/*     */   protected Map reduceToSingleOperationPerIndex(List rewrites) {
/*     */     int i;
/* 451 */     for (i = 0; i < rewrites.size(); i++) {
/* 452 */       RewriteOperation op = rewrites.get(i);
/* 453 */       if (op != null && 
/* 454 */         op instanceof ReplaceOp) {
/* 455 */         ReplaceOp rop = (ReplaceOp)rewrites.get(i);
/*     */         
/* 457 */         List inserts = getKindOfOps(rewrites, InsertBeforeOp.class, i);
/* 458 */         for (int k = 0; k < inserts.size(); k++) {
/* 459 */           InsertBeforeOp iop = inserts.get(k);
/* 460 */           if (iop.index >= rop.index && iop.index <= rop.lastIndex)
/*     */           {
/* 462 */             rewrites.set(iop.instructionIndex, null);
/*     */           }
/*     */         } 
/*     */         
/* 466 */         List prevReplaces = getKindOfOps(rewrites, ReplaceOp.class, i);
/* 467 */         for (int n = 0; n < prevReplaces.size(); n++) {
/* 468 */           ReplaceOp prevRop = prevReplaces.get(n);
/* 469 */           if (prevRop.index >= rop.index && prevRop.lastIndex <= rop.lastIndex) {
/*     */             
/* 471 */             rewrites.set(prevRop.instructionIndex, null);
/*     */           }
/*     */           else {
/*     */             
/* 475 */             boolean disjoint = (prevRop.lastIndex < rop.index || prevRop.index > rop.lastIndex);
/*     */             
/* 477 */             boolean same = (prevRop.index == rop.index && prevRop.lastIndex == rop.lastIndex);
/*     */             
/* 479 */             if (!disjoint && !same) {
/* 480 */               throw new IllegalArgumentException("replace op boundaries of " + rop + " overlap with previous " + prevRop);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 487 */     for (i = 0; i < rewrites.size(); i++) {
/* 488 */       RewriteOperation op = rewrites.get(i);
/* 489 */       if (op != null && 
/* 490 */         op instanceof InsertBeforeOp) {
/* 491 */         InsertBeforeOp iop = (InsertBeforeOp)rewrites.get(i);
/*     */         
/* 493 */         List prevInserts = getKindOfOps(rewrites, InsertBeforeOp.class, i);
/* 494 */         for (int k = 0; k < prevInserts.size(); k++) {
/* 495 */           InsertBeforeOp prevIop = prevInserts.get(k);
/* 496 */           if (prevIop.index == iop.index) {
/*     */ 
/*     */             
/* 499 */             iop.text = catOpText(iop.text, prevIop.text);
/*     */             
/* 501 */             rewrites.set(prevIop.instructionIndex, null);
/*     */           } 
/*     */         } 
/*     */         
/* 505 */         List prevReplaces = getKindOfOps(rewrites, ReplaceOp.class, i);
/* 506 */         for (int n = 0; n < prevReplaces.size(); n++) {
/* 507 */           ReplaceOp rop = prevReplaces.get(n);
/* 508 */           if (iop.index == rop.index) {
/* 509 */             rop.text = catOpText(iop.text, rop.text);
/* 510 */             rewrites.set(i, null);
/*     */           
/*     */           }
/* 513 */           else if (iop.index >= rop.index && iop.index <= rop.lastIndex) {
/* 514 */             throw new IllegalArgumentException("insert op " + iop + " within boundaries of previous " + rop);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 520 */     Map m = new HashMap();
/* 521 */     for (int j = 0; j < rewrites.size(); j++) {
/* 522 */       RewriteOperation op = rewrites.get(j);
/* 523 */       if (op != null) {
/* 524 */         if (m.get(new Integer(op.index)) != null) {
/* 525 */           throw new Error("should only be one op per index");
/*     */         }
/* 527 */         m.put(new Integer(op.index), op);
/*     */       } 
/*     */     } 
/* 530 */     return m;
/*     */   }
/*     */   
/*     */   protected String catOpText(Object a, Object b) {
/* 534 */     String x = "";
/* 535 */     String y = "";
/* 536 */     if (a != null) x = a.toString(); 
/* 537 */     if (b != null) y = b.toString(); 
/* 538 */     return x + y;
/*     */   }
/*     */   protected List getKindOfOps(List rewrites, Class kind) {
/* 541 */     return getKindOfOps(rewrites, kind, rewrites.size());
/*     */   }
/*     */ 
/*     */   
/*     */   protected List getKindOfOps(List rewrites, Class kind, int before) {
/* 546 */     List ops = new ArrayList();
/* 547 */     for (int i = 0; i < before && i < rewrites.size(); i++) {
/* 548 */       RewriteOperation op = rewrites.get(i);
/* 549 */       if (op != null && 
/* 550 */         op.getClass() == kind) ops.add(op); 
/*     */     } 
/* 552 */     return ops;
/*     */   }
/*     */   
/*     */   public String toDebugString() {
/* 556 */     return toDebugString(0, size() - 1);
/*     */   }
/*     */   
/*     */   public String toDebugString(int start, int end) {
/* 560 */     StringBuffer buf = new StringBuffer();
/* 561 */     for (int i = start; i >= 0 && i <= end && i < this.tokens.size(); i++) {
/* 562 */       buf.append(get(i));
/*     */     }
/* 564 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\TokenRewriteStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */