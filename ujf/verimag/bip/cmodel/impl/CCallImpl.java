/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CCall;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
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
/*     */ public class CCallImpl
/*     */   extends CExpressionImpl
/*     */   implements CCall
/*     */ {
/*     */   protected EList<CExpression> argument;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  66 */     return CmodelPackage.Literals.CCALL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CExpression> getArgument() {
/*  75 */     if (this.argument == null) {
/*  76 */       this.argument = (EList<CExpression>)new EObjectContainmentEList(CExpression.class, (InternalEObject)this, 0);
/*     */     }
/*  78 */     return this.argument;
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
/*     */       case 0:
/*  90 */         return ((InternalEList)getArgument()).basicRemove(otherEnd, msgs);
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
/*     */       case 0:
/* 104 */         return getArgument();
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
/*     */       case 0:
/* 119 */         getArgument().clear();
/* 120 */         getArgument().addAll((Collection)newValue);
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
/*     */       case 0:
/* 135 */         getArgument().clear();
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
/*     */       case 0:
/* 150 */         return (this.argument != null && !this.argument.isEmpty());
/*     */     } 
/* 152 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CCallImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */