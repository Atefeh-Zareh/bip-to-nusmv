/*    */ package org.antlr.runtime.misc;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FastQueue<T>
/*    */ {
/* 46 */   protected List data = new ArrayList();
/*    */   
/* 48 */   protected int p = 0;
/*    */   public void reset() {
/* 50 */     this.p = 0; this.data.clear();
/*    */   }
/*    */   
/*    */   public T remove() {
/* 54 */     T o = get(0);
/* 55 */     this.p++;
/*    */     
/* 57 */     if (this.p == this.data.size())
/*    */     {
/* 59 */       clear();
/*    */     }
/* 61 */     return o;
/*    */   }
/*    */   public void add(T o) {
/* 64 */     this.data.add(o);
/*    */   } public int size() {
/* 66 */     return this.data.size() - this.p;
/*    */   } public T head() {
/* 68 */     return get(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T get(int i) {
/* 75 */     if (this.p + i >= this.data.size()) {
/* 76 */       throw new NoSuchElementException("queue index " + (this.p + i) + " > size " + this.data.size());
/*    */     }
/* 78 */     return this.data.get(this.p + i);
/*    */   }
/*    */   public void clear() {
/* 81 */     this.p = 0; this.data.clear();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 85 */     StringBuffer buf = new StringBuffer();
/* 86 */     int n = size();
/* 87 */     for (int i = 0; i < n; i++) {
/* 88 */       buf.append(get(i));
/* 89 */       if (i + 1 < n) buf.append(" "); 
/*    */     } 
/* 91 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\misc\FastQueue.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */