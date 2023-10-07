/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntegerLiteralImpl
/*     */   extends ExpressionImpl
/*     */   implements IntegerLiteral
/*     */ {
/*     */   protected static final int IVALUE_EDEFAULT = 0;
/*  53 */   protected int iValue = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  73 */     return ExpressionsPackage.Literals.INTEGER_LITERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIValue() {
/*  83 */     return this.iValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIValue(int newIValue) {
/*  93 */     int oldIValue = this.iValue;
/*  94 */     this.iValue = newIValue;
/*  95 */     if (eNotificationRequired()) {
/*  96 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldIValue, this.iValue));
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
/* 110 */         return Integer.valueOf(getIValue());
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
/* 126 */         setIValue(((Integer)newValue).intValue());
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
/* 143 */         setIValue(0);
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
/* 160 */         return (this.iValue != 0);
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
/* 176 */     result.append(" (iValue: ");
/* 177 */     result.append(this.iValue);
/* 178 */     result.append(')');
/* 179 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\IntegerLiteralImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */