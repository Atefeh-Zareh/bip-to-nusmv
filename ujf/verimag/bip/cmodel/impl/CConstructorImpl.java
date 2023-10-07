/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CConstructor;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
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
/*     */ public class CConstructorImpl
/*     */   extends CCallableImpl
/*     */   implements CConstructor
/*     */ {
/*     */   protected EList<CInitialization> init;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  66 */     return CmodelPackage.Literals.CCONSTRUCTOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CInitialization> getInit() {
/*  75 */     if (this.init == null) {
/*  76 */       this.init = (EList<CInitialization>)new EObjectContainmentEList(CInitialization.class, (InternalEObject)this, 7);
/*     */     }
/*  78 */     return this.init;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  88 */     switch (featureID) {
/*     */       case 7:
/*  90 */         return ((InternalEList)getInit()).basicRemove(otherEnd, msgs);
/*     */     } 
/*  92 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 102 */     switch (featureID) {
/*     */       case 7:
/* 104 */         return getInit();
/*     */     } 
/* 106 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 117 */     switch (featureID) {
/*     */       case 7:
/* 119 */         getInit().clear();
/* 120 */         getInit().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 123 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 133 */     switch (featureID) {
/*     */       case 7:
/* 135 */         getInit().clear();
/*     */         return;
/*     */     } 
/* 138 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 148 */     switch (featureID) {
/*     */       case 7:
/* 150 */         return (this.init != null && !this.init.isEmpty());
/*     */     } 
/* 152 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CConstructorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */