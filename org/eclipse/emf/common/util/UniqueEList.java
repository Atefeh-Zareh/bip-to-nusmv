/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UniqueEList<E>
/*     */   extends BasicEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public UniqueEList() {}
/*     */   
/*     */   public UniqueEList(int initialCapacity) {
/*  45 */     super(initialCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UniqueEList(Collection<? extends E> collection) {
/*  54 */     super(collection.size());
/*  55 */     addAll(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isUnique() {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class FastCompare<E>
/*     */     extends UniqueEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FastCompare() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FastCompare(int initialCapacity) {
/*  90 */       super(initialCapacity);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FastCompare(Collection<? extends E> collection) {
/*  99 */       super(collection.size());
/* 100 */       addAll(collection);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useEquals() {
/* 110 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\UniqueEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */