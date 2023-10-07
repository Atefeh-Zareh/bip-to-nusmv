/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ExpressionImpl;
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
/*     */ public class StringLiteralImpl
/*     */   extends ExpressionImpl
/*     */   implements StringLiteral
/*     */ {
/*  43 */   protected static final String SVALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected String sValue = SVALUE_EDEFAULT;
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
/*     */   protected EClass eStaticClass() {
/*  73 */     return ExpressionsPackage.Literals.STRING_LITERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSValue() {
/*  83 */     return this.sValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSValue(String newSValue) {
/*  93 */     String oldSValue = this.sValue;
/*  94 */     this.sValue = newSValue;
/*  95 */     if (eNotificationRequired()) {
/*  96 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldSValue, this.sValue));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 107 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 110 */         return getSValue();
/*     */     } 
/* 112 */     return super.eGet(featureID, resolve, coreType);
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
/* 123 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 126 */         setSValue((String)newValue);
/*     */         return;
/*     */     } 
/* 129 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 140 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 143 */         setSValue(SVALUE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 146 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 157 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 160 */         return (SVALUE_EDEFAULT == null) ? ((this.sValue != null)) : (!SVALUE_EDEFAULT.equals(this.sValue));
/*     */     } 
/* 162 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 173 */     if (eIsProxy()) return super.toString();
/*     */     
/* 175 */     StringBuffer result = new StringBuffer(super.toString());
/* 176 */     result.append(" (sValue: ");
/* 177 */     result.append(this.sValue);
/* 178 */     result.append(')');
/* 179 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\StringLiteralImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */