/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import org.antlr.runtime.CommonToken;
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
/*     */ public class TreePatternParser
/*     */ {
/*     */   protected TreePatternLexer tokenizer;
/*     */   protected int ttype;
/*     */   protected TreeWizard wizard;
/*     */   protected TreeAdaptor adaptor;
/*     */   
/*     */   public TreePatternParser(TreePatternLexer tokenizer, TreeWizard wizard, TreeAdaptor adaptor) {
/*  40 */     this.tokenizer = tokenizer;
/*  41 */     this.wizard = wizard;
/*  42 */     this.adaptor = adaptor;
/*  43 */     this.ttype = tokenizer.nextToken();
/*     */   }
/*     */   
/*     */   public Object pattern() {
/*  47 */     if (this.ttype == 1) {
/*  48 */       return parseTree();
/*     */     }
/*  50 */     if (this.ttype == 3) {
/*  51 */       Object node = parseNode();
/*  52 */       if (this.ttype == -1) {
/*  53 */         return node;
/*     */       }
/*  55 */       return null;
/*     */     } 
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public Object parseTree() {
/*  61 */     if (this.ttype != 1) {
/*  62 */       System.out.println("no BEGIN");
/*  63 */       return null;
/*     */     } 
/*  65 */     this.ttype = this.tokenizer.nextToken();
/*  66 */     Object root = parseNode();
/*  67 */     if (root == null) {
/*  68 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  73 */     while (this.ttype == 1 || this.ttype == 3 || this.ttype == 5 || this.ttype == 7) {
/*     */       
/*  75 */       if (this.ttype == 1) {
/*  76 */         Object subtree = parseTree();
/*  77 */         this.adaptor.addChild(root, subtree);
/*     */         continue;
/*     */       } 
/*  80 */       Object child = parseNode();
/*  81 */       if (child == null) {
/*  82 */         return null;
/*     */       }
/*  84 */       this.adaptor.addChild(root, child);
/*     */     } 
/*     */     
/*  87 */     if (this.ttype != 2) {
/*  88 */       System.out.println("no END");
/*  89 */       return null;
/*     */     } 
/*  91 */     this.ttype = this.tokenizer.nextToken();
/*  92 */     return root;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object parseNode() {
/*  97 */     String label = null;
/*  98 */     if (this.ttype == 5) {
/*  99 */       this.ttype = this.tokenizer.nextToken();
/* 100 */       if (this.ttype != 3) {
/* 101 */         return null;
/*     */       }
/* 103 */       label = this.tokenizer.sval.toString();
/* 104 */       this.ttype = this.tokenizer.nextToken();
/* 105 */       if (this.ttype != 6) {
/* 106 */         return null;
/*     */       }
/* 108 */       this.ttype = this.tokenizer.nextToken();
/*     */     } 
/*     */ 
/*     */     
/* 112 */     if (this.ttype == 7) {
/* 113 */       this.ttype = this.tokenizer.nextToken();
/* 114 */       CommonToken commonToken = new CommonToken(0, ".");
/* 115 */       TreeWizard.TreePattern treePattern = new TreeWizard.WildcardTreePattern((Token)commonToken);
/*     */       
/* 117 */       if (label != null) {
/* 118 */         treePattern.label = label;
/*     */       }
/* 120 */       return treePattern;
/*     */     } 
/*     */ 
/*     */     
/* 124 */     if (this.ttype != 3) {
/* 125 */       return null;
/*     */     }
/* 127 */     String tokenName = this.tokenizer.sval.toString();
/* 128 */     this.ttype = this.tokenizer.nextToken();
/* 129 */     if (tokenName.equals("nil")) {
/* 130 */       return this.adaptor.nil();
/*     */     }
/* 132 */     String text = tokenName;
/*     */     
/* 134 */     String arg = null;
/* 135 */     if (this.ttype == 4) {
/* 136 */       arg = this.tokenizer.sval.toString();
/* 137 */       text = arg;
/* 138 */       this.ttype = this.tokenizer.nextToken();
/*     */     } 
/*     */ 
/*     */     
/* 142 */     int treeNodeType = this.wizard.getTokenType(tokenName);
/* 143 */     if (treeNodeType == 0) {
/* 144 */       return null;
/*     */     }
/*     */     
/* 147 */     Object node = this.adaptor.create(treeNodeType, text);
/* 148 */     if (label != null && node.getClass() == TreeWizard.TreePattern.class) {
/* 149 */       ((TreeWizard.TreePattern)node).label = label;
/*     */     }
/* 151 */     if (arg != null && node.getClass() == TreeWizard.TreePattern.class) {
/* 152 */       ((TreeWizard.TreePattern)node).hasTextArg = true;
/*     */     }
/* 154 */     return node;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreePatternParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */