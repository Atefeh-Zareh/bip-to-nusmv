/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldNavigationExpressionImpl
/*     */   extends DataNavigationExpressionImpl
/*     */   implements FieldNavigationExpression
/*     */ {
/*     */   protected static final boolean IS_ON_REF_EDEFAULT = false;
/*     */   protected boolean isOnRef = false;
/*  62 */   protected static final String FIELD_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected String fieldName = FIELD_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  92 */     return ExpressionsPackage.Literals.FIELD_NAVIGATION_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsOnRef() {
/* 102 */     return this.isOnRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsOnRef(boolean newIsOnRef) {
/* 112 */     boolean oldIsOnRef = this.isOnRef;
/* 113 */     this.isOnRef = newIsOnRef;
/* 114 */     if (eNotificationRequired()) {
/* 115 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldIsOnRef, this.isOnRef));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFieldName() {
/* 125 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFieldName(String newFieldName) {
/* 135 */     String oldFieldName = this.fieldName;
/* 136 */     this.fieldName = newFieldName;
/* 137 */     if (eNotificationRequired()) {
/* 138 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldFieldName, this.fieldName));
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
/* 149 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 152 */         return Boolean.valueOf(isIsOnRef());
/*     */       case 2:
/* 154 */         return getFieldName();
/*     */     } 
/* 156 */     return super.eGet(featureID, resolve, coreType);
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
/* 167 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 170 */         setIsOnRef(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 2:
/* 173 */         setFieldName((String)newValue);
/*     */         return;
/*     */     } 
/* 176 */     super.eSet(featureID, newValue);
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
/* 187 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 190 */         setIsOnRef(false);
/*     */         return;
/*     */       case 2:
/* 193 */         setFieldName(FIELD_NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 196 */     super.eUnset(featureID);
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
/* 207 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 210 */         return this.isOnRef;
/*     */       case 2:
/* 212 */         return (FIELD_NAME_EDEFAULT == null) ? ((this.fieldName != null)) : (!FIELD_NAME_EDEFAULT.equals(this.fieldName));
/*     */     } 
/* 214 */     return super.eIsSet(featureID);
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
/* 225 */     if (eIsProxy()) return super.toString();
/*     */     
/* 227 */     StringBuffer result = new StringBuffer(super.toString());
/* 228 */     result.append(" (isOnRef: ");
/* 229 */     result.append(this.isOnRef);
/* 230 */     result.append(", fieldName: ");
/* 231 */     result.append(this.fieldName);
/* 232 */     result.append(')');
/* 233 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\FieldNavigationExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */