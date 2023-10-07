/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
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
/*     */ public class CReturnImpl
/*     */   extends CStmImpl
/*     */   implements CReturn
/*     */ {
/*     */   protected CExpression returnExpression;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  62 */     return CmodelPackage.Literals.CRETURN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getReturnExpression() {
/*  71 */     return this.returnExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetReturnExpression(CExpression newReturnExpression, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  80 */     CExpression oldReturnExpression = this.returnExpression;
/*  81 */     this.returnExpression = newReturnExpression;
/*  82 */     if (eNotificationRequired()) {
/*  83 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldReturnExpression, newReturnExpression);
/*  84 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  86 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnExpression(CExpression newReturnExpression) {
/*  95 */     if (newReturnExpression != this.returnExpression) {
/*  96 */       NotificationChain msgs = null;
/*  97 */       if (this.returnExpression != null)
/*  98 */         msgs = ((InternalEObject)this.returnExpression).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/*  99 */       if (newReturnExpression != null)
/* 100 */         msgs = ((InternalEObject)newReturnExpression).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 101 */       msgs = basicSetReturnExpression(newReturnExpression, msgs);
/* 102 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 104 */     } else if (eNotificationRequired()) {
/* 105 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newReturnExpression, newReturnExpression));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 115 */     switch (featureID) {
/*     */       case 0:
/* 117 */         return basicSetReturnExpression((CExpression)null, msgs);
/*     */     } 
/* 119 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 129 */     switch (featureID) {
/*     */       case 0:
/* 131 */         return getReturnExpression();
/*     */     } 
/* 133 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 143 */     switch (featureID) {
/*     */       case 0:
/* 145 */         setReturnExpression((CExpression)newValue);
/*     */         return;
/*     */     } 
/* 148 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 158 */     switch (featureID) {
/*     */       case 0:
/* 160 */         setReturnExpression((CExpression)null);
/*     */         return;
/*     */     } 
/* 163 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 173 */     switch (featureID) {
/*     */       case 0:
/* 175 */         return (this.returnExpression != null);
/*     */     } 
/* 177 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CReturnImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */