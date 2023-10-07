/*    */ package org.antlr.runtime.misc;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntArray
/*    */ {
/*    */   public static final int INITIAL_SIZE = 10;
/*    */   public int[] data;
/* 47 */   protected int p = -1;
/*    */   
/*    */   public void add(int v) {
/* 50 */     ensureCapacity(this.p + 1);
/* 51 */     this.data[++this.p] = v;
/*    */   }
/*    */   
/*    */   public void push(int v) {
/* 55 */     add(v);
/*    */   }
/*    */   
/*    */   public int pop() {
/* 59 */     int v = this.data[this.p];
/* 60 */     this.p--;
/* 61 */     return v;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 66 */     return this.p;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 70 */     this.p = -1;
/*    */   }
/*    */   
/*    */   public void ensureCapacity(int index) {
/* 74 */     if (this.data == null) {
/* 75 */       this.data = new int[10];
/*    */     }
/* 77 */     else if (index + 1 >= this.data.length) {
/* 78 */       int newSize = this.data.length * 2;
/* 79 */       if (index > newSize) {
/* 80 */         newSize = index + 1;
/*    */       }
/* 82 */       int[] newData = new int[newSize];
/* 83 */       System.arraycopy(this.data, 0, newData, 0, this.data.length);
/* 84 */       this.data = newData;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\misc\IntArray.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */