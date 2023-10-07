/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
/*     */ import ujf.verimag.bip.cmodel.CConditionalStm;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CStm;
/*     */ import ujf.verimag.bip.cmodel.CWhileStm;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CWhileStmImpl
/*     */   extends CBlockImpl
/*     */   implements CWhileStm
/*     */ {
/*     */   protected CExpression condition;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  69 */     return CmodelPackage.Literals.CWHILE_STM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getCondition() {
/*  78 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCondition(CExpression newCondition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  87 */     CExpression oldCondition = this.condition;
/*  88 */     this.condition = newCondition;
/*  89 */     if (eNotificationRequired()) {
/*  90 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldCondition, newCondition);
/*  91 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  93 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCondition(CExpression newCondition) {
/* 102 */     if (newCondition != this.condition) {
/* 103 */       NotificationChain msgs = null;
/* 104 */       if (this.condition != null)
/* 105 */         msgs = ((InternalEObject)this.condition).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 106 */       if (newCondition != null)
/* 107 */         msgs = ((InternalEObject)newCondition).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 108 */       msgs = basicSetCondition(newCondition, msgs);
/* 109 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 111 */     } else if (eNotificationRequired()) {
/* 112 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newCondition, newCondition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 122 */     switch (featureID) {
/*     */       case 1:
/* 124 */         return basicSetCondition((CExpression)null, msgs);
/*     */     } 
/* 126 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 136 */     switch (featureID) {
/*     */       case 1:
/* 138 */         return getCondition();
/*     */     } 
/* 140 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 150 */     switch (featureID) {
/*     */       case 1:
/* 152 */         setCondition((CExpression)newValue);
/*     */         return;
/*     */     } 
/* 155 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 165 */     switch (featureID) {
/*     */       case 1:
/* 167 */         setCondition((CExpression)null);
/*     */         return;
/*     */     } 
/* 170 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 180 */     switch (featureID) {
/*     */       case 1:
/* 182 */         return (this.condition != null);
/*     */     } 
/* 184 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 194 */     if (baseClass == CBodyItem.class) {
/* 195 */       switch (derivedFeatureID) {  }
/* 196 */        return -1;
/*     */     } 
/*     */     
/* 199 */     if (baseClass == CStm.class) {
/* 200 */       switch (derivedFeatureID) {  }
/* 201 */        return -1;
/*     */     } 
/*     */     
/* 204 */     if (baseClass == CConditionalStm.class) {
/* 205 */       switch (derivedFeatureID) { case 1:
/* 206 */           return 0; }
/* 207 */        return -1;
/*     */     } 
/*     */     
/* 210 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 220 */     if (baseClass == CBodyItem.class) {
/* 221 */       switch (baseFeatureID) {  }
/* 222 */        return -1;
/*     */     } 
/*     */     
/* 225 */     if (baseClass == CStm.class) {
/* 226 */       switch (baseFeatureID) {  }
/* 227 */        return -1;
/*     */     } 
/*     */     
/* 230 */     if (baseClass == CConditionalStm.class) {
/* 231 */       switch (baseFeatureID) { case 0:
/* 232 */           return 1; }
/* 233 */        return -1;
/*     */     } 
/*     */     
/* 236 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CWhileStmImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */