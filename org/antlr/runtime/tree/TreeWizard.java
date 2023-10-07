/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class TreeWizard
/*     */ {
/*     */   protected TreeAdaptor adaptor;
/*     */   protected Map tokenNameToTypeMap;
/*     */   
/*     */   public static abstract class Visitor
/*     */     implements ContextVisitor
/*     */   {
/*     */     public void visit(Object t, Object parent, int childIndex, Map labels) {
/*  61 */       visit(t);
/*     */     }
/*     */     
/*     */     public abstract void visit(Object param1Object);
/*     */   }
/*     */   
/*     */   public static class TreePattern
/*     */     extends CommonTree {
/*     */     public String label;
/*     */     public boolean hasTextArg;
/*     */     
/*     */     public TreePattern(Token payload) {
/*  73 */       super(payload);
/*     */     }
/*     */     public String toString() {
/*  76 */       if (this.label != null) {
/*  77 */         return "%" + this.label + ":" + super.toString();
/*     */       }
/*     */       
/*  80 */       return super.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WildcardTreePattern
/*     */     extends TreePattern {
/*     */     public WildcardTreePattern(Token payload) {
/*  87 */       super(payload);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TreePatternTreeAdaptor
/*     */     extends CommonTreeAdaptor {
/*     */     public Object create(Token payload) {
/*  94 */       return new TreeWizard.TreePattern(payload);
/*     */     }
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
/*     */   public TreeWizard(TreeAdaptor adaptor) {
/* 120 */     this.adaptor = adaptor;
/*     */   }
/*     */   
/*     */   public TreeWizard(TreeAdaptor adaptor, Map tokenNameToTypeMap) {
/* 124 */     this.adaptor = adaptor;
/* 125 */     this.tokenNameToTypeMap = tokenNameToTypeMap;
/*     */   }
/*     */   
/*     */   public TreeWizard(TreeAdaptor adaptor, String[] tokenNames) {
/* 129 */     this.adaptor = adaptor;
/* 130 */     this.tokenNameToTypeMap = computeTokenTypes(tokenNames);
/*     */   }
/*     */   
/*     */   public TreeWizard(String[] tokenNames) {
/* 134 */     this((TreeAdaptor)null, tokenNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map computeTokenTypes(String[] tokenNames) {
/* 141 */     Map m = new HashMap();
/* 142 */     if (tokenNames == null) {
/* 143 */       return m;
/*     */     }
/* 145 */     for (int ttype = 4; ttype < tokenNames.length; ttype++) {
/* 146 */       String name = tokenNames[ttype];
/* 147 */       m.put(name, new Integer(ttype));
/*     */     } 
/* 149 */     return m;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTokenType(String tokenName) {
/* 154 */     if (this.tokenNameToTypeMap == null) {
/* 155 */       return 0;
/*     */     }
/* 157 */     Integer ttypeI = (Integer)this.tokenNameToTypeMap.get(tokenName);
/* 158 */     if (ttypeI != null) {
/* 159 */       return ttypeI.intValue();
/*     */     }
/* 161 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map index(Object t) {
/* 172 */     Map m = new HashMap();
/* 173 */     _index(t, m);
/* 174 */     return m;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void _index(Object t, Map m) {
/* 179 */     if (t == null) {
/*     */       return;
/*     */     }
/* 182 */     int ttype = this.adaptor.getType(t);
/* 183 */     List elements = (List)m.get(new Integer(ttype));
/* 184 */     if (elements == null) {
/* 185 */       elements = new ArrayList();
/* 186 */       m.put(new Integer(ttype), elements);
/*     */     } 
/* 188 */     elements.add(t);
/* 189 */     int n = this.adaptor.getChildCount(t);
/* 190 */     for (int i = 0; i < n; i++) {
/* 191 */       Object child = this.adaptor.getChild(t, i);
/* 192 */       _index(child, m);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List find(Object t, int ttype) {
/* 198 */     final List nodes = new ArrayList();
/* 199 */     visit(t, ttype, new Visitor() { private final List val$nodes; private final TreeWizard this$0;
/*     */           public void visit(Object t) {
/* 201 */             nodes.add(t);
/*     */           } }
/*     */       );
/* 204 */     return nodes;
/*     */   }
/*     */ 
/*     */   
/*     */   public List find(Object t, String pattern) {
/* 209 */     final List subtrees = new ArrayList();
/*     */     
/* 211 */     TreePatternLexer tokenizer = new TreePatternLexer(pattern);
/* 212 */     TreePatternParser parser = new TreePatternParser(tokenizer, this, new TreePatternTreeAdaptor());
/*     */     
/* 214 */     final TreePattern tpattern = (TreePattern)parser.pattern();
/*     */     
/* 216 */     if (tpattern == null || tpattern.isNil() || tpattern.getClass() == WildcardTreePattern.class)
/*     */     {
/*     */ 
/*     */       
/* 220 */       return null;
/*     */     }
/* 222 */     int rootTokenType = tpattern.getType();
/* 223 */     visit(t, rootTokenType, new ContextVisitor() { private final TreeWizard.TreePattern val$tpattern;
/*     */           public void visit(Object t, Object parent, int childIndex, Map labels) {
/* 225 */             if (TreeWizard.this._parse(t, tpattern, null))
/* 226 */               subtrees.add(t); 
/*     */           }
/*     */           private final List val$subtrees; private final TreeWizard this$0; }
/*     */       );
/* 230 */     return subtrees;
/*     */   }
/*     */   
/*     */   public Object findFirst(Object t, int ttype) {
/* 234 */     return null;
/*     */   }
/*     */   
/*     */   public Object findFirst(Object t, String pattern) {
/* 238 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(Object t, int ttype, ContextVisitor visitor) {
/* 247 */     _visit(t, null, 0, ttype, visitor);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void _visit(Object t, Object parent, int childIndex, int ttype, ContextVisitor visitor) {
/* 252 */     if (t == null) {
/*     */       return;
/*     */     }
/* 255 */     if (this.adaptor.getType(t) == ttype) {
/* 256 */       visitor.visit(t, parent, childIndex, null);
/*     */     }
/* 258 */     int n = this.adaptor.getChildCount(t);
/* 259 */     for (int i = 0; i < n; i++) {
/* 260 */       Object child = this.adaptor.getChild(t, i);
/* 261 */       _visit(child, t, i, ttype, visitor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(Object t, String pattern, final ContextVisitor visitor) {
/* 272 */     TreePatternLexer tokenizer = new TreePatternLexer(pattern);
/* 273 */     TreePatternParser parser = new TreePatternParser(tokenizer, this, new TreePatternTreeAdaptor());
/*     */     
/* 275 */     final TreePattern tpattern = (TreePattern)parser.pattern();
/*     */     
/* 277 */     if (tpattern == null || tpattern.isNil() || tpattern.getClass() == WildcardTreePattern.class) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 283 */     final Map labels = new HashMap();
/* 284 */     int rootTokenType = tpattern.getType();
/* 285 */     visit(t, rootTokenType, new ContextVisitor() { private final Map val$labels; private final TreeWizard.TreePattern val$tpattern; private final TreeWizard.ContextVisitor val$visitor; private final TreeWizard this$0;
/*     */           
/*     */           public void visit(Object t, Object parent, int childIndex, Map unusedlabels) {
/* 288 */             labels.clear();
/* 289 */             if (TreeWizard.this._parse(t, tpattern, labels)) {
/* 290 */               visitor.visit(t, parent, childIndex, labels);
/*     */             }
/*     */           } }
/*     */       );
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
/*     */   public boolean parse(Object t, String pattern, Map labels) {
/* 308 */     TreePatternLexer tokenizer = new TreePatternLexer(pattern);
/* 309 */     TreePatternParser parser = new TreePatternParser(tokenizer, this, new TreePatternTreeAdaptor());
/*     */     
/* 311 */     TreePattern tpattern = (TreePattern)parser.pattern();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     boolean matched = _parse(t, tpattern, labels);
/* 317 */     return matched;
/*     */   }
/*     */   
/*     */   public boolean parse(Object t, String pattern) {
/* 321 */     return parse(t, pattern, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _parse(Object t1, TreePattern tpattern, Map labels) {
/* 331 */     if (t1 == null || tpattern == null) {
/* 332 */       return false;
/*     */     }
/*     */     
/* 335 */     if (tpattern.getClass() != WildcardTreePattern.class) {
/* 336 */       if (this.adaptor.getType(t1) != tpattern.getType()) return false;
/*     */       
/* 338 */       if (tpattern.hasTextArg && !this.adaptor.getText(t1).equals(tpattern.getText())) {
/* 339 */         return false;
/*     */       }
/*     */     } 
/* 342 */     if (tpattern.label != null && labels != null)
/*     */     {
/* 344 */       labels.put(tpattern.label, t1);
/*     */     }
/*     */     
/* 347 */     int n1 = this.adaptor.getChildCount(t1);
/* 348 */     int n2 = tpattern.getChildCount();
/* 349 */     if (n1 != n2) {
/* 350 */       return false;
/*     */     }
/* 352 */     for (int i = 0; i < n1; i++) {
/* 353 */       Object child1 = this.adaptor.getChild(t1, i);
/* 354 */       TreePattern child2 = (TreePattern)tpattern.getChild(i);
/* 355 */       if (!_parse(child1, child2, labels)) {
/* 356 */         return false;
/*     */       }
/*     */     } 
/* 359 */     return true;
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
/*     */   public Object create(String pattern) {
/* 376 */     TreePatternLexer tokenizer = new TreePatternLexer(pattern);
/* 377 */     TreePatternParser parser = new TreePatternParser(tokenizer, this, this.adaptor);
/* 378 */     Object t = parser.pattern();
/* 379 */     return t;
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
/*     */   public static boolean equals(Object t1, Object t2, TreeAdaptor adaptor) {
/* 392 */     return _equals(t1, t2, adaptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object t1, Object t2) {
/* 399 */     return _equals(t1, t2, this.adaptor);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean _equals(Object t1, Object t2, TreeAdaptor adaptor) {
/* 404 */     if (t1 == null || t2 == null) {
/* 405 */       return false;
/*     */     }
/*     */     
/* 408 */     if (adaptor.getType(t1) != adaptor.getType(t2)) {
/* 409 */       return false;
/*     */     }
/* 411 */     if (!adaptor.getText(t1).equals(adaptor.getText(t2))) {
/* 412 */       return false;
/*     */     }
/*     */     
/* 415 */     int n1 = adaptor.getChildCount(t1);
/* 416 */     int n2 = adaptor.getChildCount(t2);
/* 417 */     if (n1 != n2) {
/* 418 */       return false;
/*     */     }
/* 420 */     for (int i = 0; i < n1; i++) {
/* 421 */       Object child1 = adaptor.getChild(t1, i);
/* 422 */       Object child2 = adaptor.getChild(t2, i);
/* 423 */       if (!_equals(child1, child2, adaptor)) {
/* 424 */         return false;
/*     */       }
/*     */     } 
/* 427 */     return true;
/*     */   }
/*     */   
/*     */   public static interface ContextVisitor {
/*     */     void visit(Object param1Object1, Object param1Object2, int param1Int, Map param1Map);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\TreeWizard.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */