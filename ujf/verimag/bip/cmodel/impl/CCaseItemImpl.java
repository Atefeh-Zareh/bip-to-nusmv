/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CCaseItem;
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
/*     */ public class CCaseItemImpl
/*     */   extends CBlockImpl
/*     */   implements CCaseItem
/*     */ {
/*  47 */   protected static final String CASE_VALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected String caseValue = CASE_VALUE_EDEFAULT;
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
/*     */   protected EClass eStaticClass() {
/*  75 */     return CmodelPackage.Literals.CCASE_ITEM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCaseValue() {
/*  84 */     return this.caseValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaseValue(String newCaseValue) {
/*  93 */     String oldCaseValue = this.caseValue;
/*  94 */     this.caseValue = newCaseValue;
/*  95 */     if (eNotificationRequired()) {
/*  96 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldCaseValue, this.caseValue));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 106 */     switch (featureID) {
/*     */       case 1:
/* 108 */         return getCaseValue();
/*     */     } 
/* 110 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 120 */     switch (featureID) {
/*     */       case 1:
/* 122 */         setCaseValue((String)newValue);
/*     */         return;
/*     */     } 
/* 125 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 135 */     switch (featureID) {
/*     */       case 1:
/* 137 */         setCaseValue(CASE_VALUE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 140 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 150 */     switch (featureID) {
/*     */       case 1:
/* 152 */         return (CASE_VALUE_EDEFAULT == null) ? ((this.caseValue != null)) : (!CASE_VALUE_EDEFAULT.equals(this.caseValue));
/*     */     } 
/* 154 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 164 */     if (eIsProxy()) return super.toString();
/*     */     
/* 166 */     StringBuffer result = new StringBuffer(super.toString());
/* 167 */     result.append(" (caseValue: ");
/* 168 */     result.append(this.caseValue);
/* 169 */     result.append(')');
/* 170 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CCaseItemImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */