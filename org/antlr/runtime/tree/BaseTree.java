/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseTree
/*     */   implements Tree
/*     */ {
/*     */   protected List children;
/*     */   
/*     */   public BaseTree() {}
/*     */   
/*     */   public BaseTree(Tree node) {}
/*     */   
/*     */   public Tree getChild(int i) {
/*  53 */     if (this.children == null || i >= this.children.size()) {
/*  54 */       return null;
/*     */     }
/*  56 */     return this.children.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getChildren() {
/*  63 */     return this.children;
/*     */   }
/*     */   
/*     */   public Tree getFirstChildWithType(int type) {
/*  67 */     for (int i = 0; this.children != null && i < this.children.size(); i++) {
/*  68 */       Tree t = this.children.get(i);
/*  69 */       if (t.getType() == type) {
/*  70 */         return t;
/*     */       }
/*     */     } 
/*  73 */     return null;
/*     */   }
/*     */   
/*     */   public int getChildCount() {
/*  77 */     if (this.children == null) {
/*  78 */       return 0;
/*     */     }
/*  80 */     return this.children.size();
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
/*     */   public void addChild(Tree t) {
/*  92 */     if (t == null) {
/*     */       return;
/*     */     }
/*  95 */     BaseTree childTree = (BaseTree)t;
/*  96 */     if (childTree.isNil()) {
/*  97 */       if (this.children != null && this.children == childTree.children) {
/*  98 */         throw new RuntimeException("attempt to add child list to itself");
/*     */       }
/*     */       
/* 101 */       if (childTree.children != null) {
/* 102 */         if (this.children != null) {
/* 103 */           int n = childTree.children.size();
/* 104 */           for (int i = 0; i < n; i++) {
/* 105 */             Tree c = childTree.children.get(i);
/* 106 */             this.children.add(c);
/*     */             
/* 108 */             c.setParent(this);
/* 109 */             c.setChildIndex(this.children.size() - 1);
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 115 */           this.children = childTree.children;
/* 116 */           freshenParentAndChildIndexes();
/*     */         } 
/*     */       }
/*     */     } else {
/*     */       
/* 121 */       if (this.children == null) {
/* 122 */         this.children = createChildrenList();
/*     */       }
/* 124 */       this.children.add(t);
/* 125 */       childTree.setParent(this);
/* 126 */       childTree.setChildIndex(this.children.size() - 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChildren(List kids) {
/* 133 */     for (int i = 0; i < kids.size(); i++) {
/* 134 */       Tree t = kids.get(i);
/* 135 */       addChild(t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setChild(int i, Tree t) {
/* 140 */     if (t == null) {
/*     */       return;
/*     */     }
/* 143 */     if (t.isNil()) {
/* 144 */       throw new IllegalArgumentException("Can't set single child to a list");
/*     */     }
/* 146 */     if (this.children == null) {
/* 147 */       this.children = createChildrenList();
/*     */     }
/* 149 */     this.children.set(i, t);
/* 150 */     t.setParent(this);
/* 151 */     t.setChildIndex(i);
/*     */   }
/*     */   
/*     */   public Object deleteChild(int i) {
/* 155 */     if (this.children == null) {
/* 156 */       return null;
/*     */     }
/* 158 */     Tree killed = this.children.remove(i);
/*     */     
/* 160 */     freshenParentAndChildIndexes(i);
/* 161 */     return killed;
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
/*     */   public void replaceChildren(int startChildIndex, int stopChildIndex, Object t) {
/* 175 */     if (this.children == null) {
/* 176 */       throw new IllegalArgumentException("indexes invalid; no children in list");
/*     */     }
/* 178 */     int replacingHowMany = stopChildIndex - startChildIndex + 1;
/*     */     
/* 180 */     BaseTree newTree = (BaseTree)t;
/* 181 */     List newChildren = null;
/*     */     
/* 183 */     if (newTree.isNil()) {
/* 184 */       newChildren = newTree.children;
/*     */     } else {
/*     */       
/* 187 */       newChildren = new ArrayList(1);
/* 188 */       newChildren.add(newTree);
/*     */     } 
/* 190 */     int replacingWithHowMany = newChildren.size();
/* 191 */     int numNewChildren = newChildren.size();
/* 192 */     int delta = replacingHowMany - replacingWithHowMany;
/*     */     
/* 194 */     if (delta == 0) {
/* 195 */       int j = 0;
/* 196 */       for (int i = startChildIndex; i <= stopChildIndex; i++) {
/* 197 */         BaseTree child = newChildren.get(j);
/* 198 */         this.children.set(i, child);
/* 199 */         child.setParent(this);
/* 200 */         child.setChildIndex(i);
/* 201 */         j++;
/*     */       }
/*     */     
/* 204 */     } else if (delta > 0) {
/*     */       
/* 206 */       for (int j = 0; j < numNewChildren; j++) {
/* 207 */         this.children.set(startChildIndex + j, newChildren.get(j));
/*     */       }
/* 209 */       int indexToDelete = startChildIndex + numNewChildren;
/* 210 */       for (int c = indexToDelete; c <= stopChildIndex; c++)
/*     */       {
/* 212 */         this.children.remove(indexToDelete);
/*     */       }
/* 214 */       freshenParentAndChildIndexes(startChildIndex);
/*     */     }
/*     */     else {
/*     */       
/* 218 */       for (int j = 0; j < replacingHowMany; j++) {
/* 219 */         this.children.set(startChildIndex + j, newChildren.get(j));
/*     */       }
/* 221 */       int numToInsert = replacingWithHowMany - replacingHowMany;
/* 222 */       for (int i = replacingHowMany; i < replacingWithHowMany; i++) {
/* 223 */         this.children.add(startChildIndex + i, newChildren.get(i));
/*     */       }
/* 225 */       freshenParentAndChildIndexes(startChildIndex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List createChildrenList() {
/* 232 */     return new ArrayList();
/*     */   }
/*     */   
/*     */   public boolean isNil() {
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void freshenParentAndChildIndexes() {
/* 241 */     freshenParentAndChildIndexes(0);
/*     */   }
/*     */   
/*     */   public void freshenParentAndChildIndexes(int offset) {
/* 245 */     int n = getChildCount();
/* 246 */     for (int c = offset; c < n; c++) {
/* 247 */       Tree child = getChild(c);
/* 248 */       child.setChildIndex(c);
/* 249 */       child.setParent(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sanityCheckParentAndChildIndexes() {
/* 254 */     sanityCheckParentAndChildIndexes(null, -1);
/*     */   }
/*     */   
/*     */   public void sanityCheckParentAndChildIndexes(Tree parent, int i) {
/* 258 */     if (parent != getParent()) {
/* 259 */       throw new IllegalStateException("parents don't match; expected " + parent + " found " + getParent());
/*     */     }
/* 261 */     if (i != getChildIndex()) {
/* 262 */       throw new IllegalStateException("child indexes don't match; expected " + i + " found " + getChildIndex());
/*     */     }
/* 264 */     int n = getChildCount();
/* 265 */     for (int c = 0; c < n; c++) {
/* 266 */       CommonTree child = (CommonTree)getChild(c);
/* 267 */       child.sanityCheckParentAndChildIndexes(this, c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChildIndex() {
/* 273 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildIndex(int index) {}
/*     */   
/*     */   public Tree getParent() {
/* 280 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParent(Tree t) {}
/*     */   
/*     */   public boolean hasAncestor(int ttype) {
/* 287 */     return (getAncestor(ttype) != null);
/*     */   }
/*     */   
/*     */   public Tree getAncestor(int ttype) {
/* 291 */     Tree t = this;
/* 292 */     t = t.getParent();
/* 293 */     while (t != null) {
/* 294 */       if (t.getType() == ttype) return t; 
/* 295 */       t = t.getParent();
/*     */     } 
/* 297 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAncestors() {
/* 304 */     if (getParent() == null) return null; 
/* 305 */     List ancestors = new ArrayList();
/* 306 */     Tree t = this;
/* 307 */     t = t.getParent();
/* 308 */     while (t != null) {
/* 309 */       ancestors.add(0, t);
/* 310 */       t = t.getParent();
/*     */     } 
/* 312 */     return ancestors;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toStringTree() {
/* 317 */     if (this.children == null || this.children.size() == 0) {
/* 318 */       return toString();
/*     */     }
/* 320 */     StringBuffer buf = new StringBuffer();
/* 321 */     if (!isNil()) {
/* 322 */       buf.append("(");
/* 323 */       buf.append(toString());
/* 324 */       buf.append(' ');
/*     */     } 
/* 326 */     for (int i = 0; this.children != null && i < this.children.size(); i++) {
/* 327 */       Tree t = this.children.get(i);
/* 328 */       if (i > 0) {
/* 329 */         buf.append(' ');
/*     */       }
/* 331 */       buf.append(t.toStringTree());
/*     */     } 
/* 333 */     if (!isNil()) {
/* 334 */       buf.append(")");
/*     */     }
/* 336 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public int getLine() {
/* 340 */     return 0;
/*     */   }
/*     */   
/*     */   public int getCharPositionInLine() {
/* 344 */     return 0;
/*     */   }
/*     */   
/*     */   public abstract String toString();
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\BaseTree.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */