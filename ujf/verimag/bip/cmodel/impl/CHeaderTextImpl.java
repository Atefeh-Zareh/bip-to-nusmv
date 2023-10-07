/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CHeaderText;
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
/*     */ public class CHeaderTextImpl
/*     */   extends CItemImpl
/*     */   implements CHeaderText
/*     */ {
/*  40 */   protected static final String CCODE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected String cCode = CCODE_EDEFAULT;
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
/*  68 */     return CmodelPackage.Literals.CHEADER_TEXT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCCode() {
/*  77 */     return this.cCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCCode(String newCCode) {
/*  86 */     String oldCCode = this.cCode;
/*  87 */     this.cCode = newCCode;
/*  88 */     if (eNotificationRequired()) {
/*  89 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldCCode, this.cCode));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/*  99 */     switch (featureID) {
/*     */       case 1:
/* 101 */         return getCCode();
/*     */     } 
/* 103 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 113 */     switch (featureID) {
/*     */       case 1:
/* 115 */         setCCode((String)newValue);
/*     */         return;
/*     */     } 
/* 118 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 128 */     switch (featureID) {
/*     */       case 1:
/* 130 */         setCCode(CCODE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 133 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 143 */     switch (featureID) {
/*     */       case 1:
/* 145 */         return (CCODE_EDEFAULT == null) ? ((this.cCode != null)) : (!CCODE_EDEFAULT.equals(this.cCode));
/*     */     } 
/* 147 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 157 */     if (eIsProxy()) return super.toString();
/*     */     
/* 159 */     StringBuffer result = new StringBuffer(super.toString());
/* 160 */     result.append(" (cCode: ");
/* 161 */     result.append(this.cCode);
/* 162 */     result.append(')');
/* 163 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CHeaderTextImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */