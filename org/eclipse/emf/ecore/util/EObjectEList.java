/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EObjectEList<E>
/*     */   extends EcoreEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final int featureID;
/*     */   
/*     */   public static class Unsettable<E>
/*     */     extends EObjectEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected boolean isSet;
/*     */     
/*     */     public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID) {
/*  36 */       super(dataClass, owner, featureID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didChange() {
/*  42 */       this.isSet = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/*  48 */       return this.isSet;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void unset() {
/*  54 */       super.unset();
/*  55 */       if (isNotificationRequired()) {
/*     */         
/*  57 */         boolean oldIsSet = this.isSet;
/*  58 */         this.isSet = false;
/*  59 */         this.owner.eNotify((Notification)createNotification(2, oldIsSet, false));
/*     */       }
/*     */       else {
/*     */         
/*  63 */         this.isSet = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObjectEList(Class<?> dataClass, InternalEObject owner, int featureID) {
/*  72 */     super(dataClass, owner);
/*  73 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureID() {
/*  79 */     return this.featureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean useEquals() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isUnique() {
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasInverse() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEObject() {
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canContainNull() {
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected E resolve(int index, E object) {
/* 115 */     return object;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EObjectEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */