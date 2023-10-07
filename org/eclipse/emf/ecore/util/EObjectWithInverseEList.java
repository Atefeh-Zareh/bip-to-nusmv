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
/*     */ public class EObjectWithInverseEList<E>
/*     */   extends EObjectEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected final int inverseFeatureID;
/*     */   
/*     */   public static class Unsettable<E>
/*     */     extends EObjectWithInverseEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected boolean isSet;
/*     */     
/*     */     public static class ManyInverse<E>
/*     */       extends Unsettable<E>
/*     */     {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public ManyInverse(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/*  38 */         super(dataClass, owner, featureID, inverseFeatureID);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean hasManyInverse() {
/*  44 */         return true;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/*  52 */       super(dataClass, owner, featureID, inverseFeatureID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didChange() {
/*  58 */       this.isSet = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/*  64 */       return this.isSet;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void unset() {
/*  70 */       super.unset();
/*  71 */       if (isNotificationRequired()) {
/*     */         
/*  73 */         boolean oldIsSet = this.isSet;
/*  74 */         this.isSet = false;
/*  75 */         this.owner.eNotify((Notification)createNotification(2, oldIsSet, false));
/*     */       }
/*     */       else {
/*     */         
/*  79 */         this.isSet = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ManyInverse<E>
/*     */     extends EObjectWithInverseEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public ManyInverse(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/*  90 */       super(dataClass, owner, featureID, inverseFeatureID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasManyInverse() {
/*  96 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObjectWithInverseEList(Class<?> dataClass, InternalEObject owner, int featureID, int inverseFeatureID) {
/* 105 */     super(dataClass, owner, featureID);
/* 106 */     this.inverseFeatureID = inverseFeatureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasInverse() {
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasNavigableInverse() {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInverseFeatureID() {
/* 124 */     return this.inverseFeatureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getInverseFeatureClass() {
/* 130 */     return this.dataClass;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EObjectWithInverseEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */