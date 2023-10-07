/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EObject;
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
/*     */ 
/*     */ public class EObjectContainmentEList<E>
/*     */   extends EObjectEList<E>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static class Unsettable<E>
/*     */     extends EObjectContainmentEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected boolean isSet;
/*     */     
/*     */     public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID) {
/*  37 */       super(dataClass, owner, featureID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didChange() {
/*  43 */       this.isSet = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSet() {
/*  49 */       return this.isSet;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void unset() {
/*  55 */       super.unset();
/*  56 */       if (isNotificationRequired()) {
/*     */         
/*  58 */         boolean oldIsSet = this.isSet;
/*  59 */         this.isSet = false;
/*  60 */         this.owner.eNotify((Notification)createNotification(2, oldIsSet, false));
/*     */       }
/*     */       else {
/*     */         
/*  64 */         this.isSet = false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public static class Resolving<E>
/*     */       extends Unsettable<E>
/*     */     {
/*     */       private static final long serialVersionUID = 1L;
/*     */       
/*     */       public Resolving(Class<?> dataClass, InternalEObject owner, int featureID) {
/*  74 */         super(dataClass, owner, featureID);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean hasProxies() {
/*  80 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected E resolve(int index, E object) {
/*  87 */         return (E)resolve(index, (EObject)object);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Resolving<E>
/*     */     extends EObjectContainmentEList<E>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public Resolving(Class<?> dataClass, InternalEObject owner, int featureID) {
/*  98 */       super(dataClass, owner, featureID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean hasProxies() {
/* 104 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected E resolve(int index, E object) {
/* 111 */       return (E)resolve(index, (EObject)object);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EObjectContainmentEList(Class<?> dataClass, InternalEObject owner, int featureID) {
/* 117 */     super(dataClass, owner, featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasInverse() {
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasNavigableInverse() {
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isContainment() {
/* 135 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EObjectContainmentEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */