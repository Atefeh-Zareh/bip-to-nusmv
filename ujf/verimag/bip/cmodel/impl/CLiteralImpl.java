/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CLiteral;
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
/*     */ public class CLiteralImpl
/*     */   extends CExpressionImpl
/*     */   implements CLiteral
/*     */ {
/*  41 */   protected static final String VALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected String value = VALUE_EDEFAULT;
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
/*  69 */     return CmodelPackage.Literals.CLITERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  78 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(String newValue) {
/*  87 */     String oldValue = this.value;
/*  88 */     this.value = newValue;
/*  89 */     if (eNotificationRequired()) {
/*  90 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldValue, this.value));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 100 */     switch (featureID) {
/*     */       case 0:
/* 102 */         return getValue();
/*     */     } 
/* 104 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 114 */     switch (featureID) {
/*     */       case 0:
/* 116 */         setValue((String)newValue);
/*     */         return;
/*     */     } 
/* 119 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 129 */     switch (featureID) {
/*     */       case 0:
/* 131 */         setValue(VALUE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 134 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 144 */     switch (featureID) {
/*     */       case 0:
/* 146 */         return (VALUE_EDEFAULT == null) ? ((this.value != null)) : (!VALUE_EDEFAULT.equals(this.value));
/*     */     } 
/* 148 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 158 */     if (eIsProxy()) return super.toString();
/*     */     
/* 160 */     StringBuffer result = new StringBuffer(super.toString());
/* 161 */     result.append(" (value: ");
/* 162 */     result.append(this.value);
/* 163 */     result.append(')');
/* 164 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CLiteralImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */