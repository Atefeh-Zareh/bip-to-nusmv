/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import org.antlr.runtime.tree.CommonTree;
/*     */ import org.antlr.runtime.tree.Tree;
/*     */ import org.antlr.runtime.tree.TreeAdaptor;
/*     */ import org.antlr.runtime.tree.TreeNodeStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecognitionException
/*     */   extends Exception
/*     */ {
/*     */   public transient IntStream input;
/*     */   public int index;
/*     */   public Token token;
/*     */   public Object node;
/*     */   public int c;
/*     */   public int line;
/*     */   public int charPositionInLine;
/*     */   public boolean approximateLineInfo;
/*     */   
/*     */   public RecognitionException() {}
/*     */   
/*     */   public RecognitionException(IntStream input) {
/* 103 */     this.input = input;
/* 104 */     this.index = input.index();
/* 105 */     if (input instanceof TokenStream) {
/* 106 */       this.token = ((TokenStream)input).LT(1);
/* 107 */       this.line = this.token.getLine();
/* 108 */       this.charPositionInLine = this.token.getCharPositionInLine();
/*     */     } 
/* 110 */     if (input instanceof TreeNodeStream) {
/* 111 */       extractInformationFromTreeNodeStream(input);
/*     */     }
/* 113 */     else if (input instanceof CharStream) {
/* 114 */       this.c = input.LA(1);
/* 115 */       this.line = ((CharStream)input).getLine();
/* 116 */       this.charPositionInLine = ((CharStream)input).getCharPositionInLine();
/*     */     } else {
/*     */       
/* 119 */       this.c = input.LA(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void extractInformationFromTreeNodeStream(IntStream input) {
/* 124 */     TreeNodeStream nodes = (TreeNodeStream)input;
/* 125 */     this.node = nodes.LT(1);
/* 126 */     TreeAdaptor adaptor = nodes.getTreeAdaptor();
/* 127 */     Token payload = adaptor.getToken(this.node);
/* 128 */     if (payload != null) {
/* 129 */       this.token = payload;
/* 130 */       if (payload.getLine() <= 0) {
/*     */         
/* 132 */         int i = -1;
/* 133 */         Object priorNode = nodes.LT(i);
/* 134 */         while (priorNode != null) {
/* 135 */           Token priorPayload = adaptor.getToken(priorNode);
/* 136 */           if (priorPayload != null && priorPayload.getLine() > 0) {
/*     */             
/* 138 */             this.line = priorPayload.getLine();
/* 139 */             this.charPositionInLine = priorPayload.getCharPositionInLine();
/* 140 */             this.approximateLineInfo = true;
/*     */             break;
/*     */           } 
/* 143 */           i--;
/* 144 */           priorNode = nodes.LT(i);
/*     */         } 
/*     */       } else {
/*     */         
/* 148 */         this.line = payload.getLine();
/* 149 */         this.charPositionInLine = payload.getCharPositionInLine();
/*     */       }
/*     */     
/* 152 */     } else if (this.node instanceof Tree) {
/* 153 */       this.line = ((Tree)this.node).getLine();
/* 154 */       this.charPositionInLine = ((Tree)this.node).getCharPositionInLine();
/* 155 */       if (this.node instanceof CommonTree) {
/* 156 */         this.token = ((CommonTree)this.node).token;
/*     */       }
/*     */     } else {
/*     */       
/* 160 */       int type = adaptor.getType(this.node);
/* 161 */       String text = adaptor.getText(this.node);
/* 162 */       this.token = new CommonToken(type, text);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUnexpectedType() {
/* 168 */     if (this.input instanceof TokenStream) {
/* 169 */       return this.token.getType();
/*     */     }
/* 171 */     if (this.input instanceof TreeNodeStream) {
/* 172 */       TreeNodeStream nodes = (TreeNodeStream)this.input;
/* 173 */       TreeAdaptor adaptor = nodes.getTreeAdaptor();
/* 174 */       return adaptor.getType(this.node);
/*     */     } 
/*     */     
/* 177 */     return this.c;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\RecognitionException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */