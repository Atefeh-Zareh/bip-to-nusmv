/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
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
/*     */ public class RealLiteralImpl
/*     */   extends ExpressionImpl
/*     */   implements RealLiteral
/*     */ {
/*  43 */   protected static final String RVALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected String rValue = RVALUE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  73 */     return ExpressionsPackage.Literals.REAL_LITERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRValue() {
/*  83 */     return this.rValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRValue(String newRValue) {
/*  93 */     String oldRValue = this.rValue;
/*  94 */     this.rValue = newRValue;
/*  95 */     if (eNotificationRequired()) {
/*  96 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldRValue, this.rValue));
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
/* 110 */         return getRValue();
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
/* 126 */         setRValue((String)newValue);
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
/* 143 */         setRValue(RVALUE_EDEFAULT);
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
/* 160 */         return (RVALUE_EDEFAULT == null) ? ((this.rValue != null)) : (!RVALUE_EDEFAULT.equals(this.rValue));
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
/* 176 */     result.append(" (rValue: ");
/* 177 */     result.append(this.rValue);
/* 178 */     result.append(')');
/* 179 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\RealLiteralImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */