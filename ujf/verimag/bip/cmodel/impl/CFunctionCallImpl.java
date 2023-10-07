/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CTypedElement;
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
/*     */ public class CFunctionCallImpl
/*     */   extends CCallImpl
/*     */   implements CFunctionCall
/*     */ {
/*  49 */   protected static final String TYPE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   protected String type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected static final String FUNCTION_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   protected String functionName = FUNCTION_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  97 */     return CmodelPackage.Literals.CFUNCTION_CALL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 106 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String newType) {
/* 115 */     String oldType = this.type;
/* 116 */     this.type = newType;
/* 117 */     if (eNotificationRequired()) {
/* 118 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFunctionName() {
/* 127 */     return this.functionName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionName(String newFunctionName) {
/* 136 */     String oldFunctionName = this.functionName;
/* 137 */     this.functionName = newFunctionName;
/* 138 */     if (eNotificationRequired()) {
/* 139 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldFunctionName, this.functionName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 149 */     switch (featureID) {
/*     */       case 1:
/* 151 */         return getType();
/*     */       case 2:
/* 153 */         return getFunctionName();
/*     */     } 
/* 155 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 165 */     switch (featureID) {
/*     */       case 1:
/* 167 */         setType((String)newValue);
/*     */         return;
/*     */       case 2:
/* 170 */         setFunctionName((String)newValue);
/*     */         return;
/*     */     } 
/* 173 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 183 */     switch (featureID) {
/*     */       case 1:
/* 185 */         setType(TYPE_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 188 */         setFunctionName(FUNCTION_NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 191 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 201 */     switch (featureID) {
/*     */       case 1:
/* 203 */         return (TYPE_EDEFAULT == null) ? ((this.type != null)) : (!TYPE_EDEFAULT.equals(this.type));
/*     */       case 2:
/* 205 */         return (FUNCTION_NAME_EDEFAULT == null) ? ((this.functionName != null)) : (!FUNCTION_NAME_EDEFAULT.equals(this.functionName));
/*     */     } 
/* 207 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 217 */     if (baseClass == CTypedElement.class) {
/* 218 */       switch (derivedFeatureID) { case 1:
/* 219 */           return 0; }
/* 220 */        return -1;
/*     */     } 
/*     */     
/* 223 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 233 */     if (baseClass == CTypedElement.class) {
/* 234 */       switch (baseFeatureID) { case 0:
/* 235 */           return 1; }
/* 236 */        return -1;
/*     */     } 
/*     */     
/* 239 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 249 */     if (eIsProxy()) return super.toString();
/*     */     
/* 251 */     StringBuffer result = new StringBuffer(super.toString());
/* 252 */     result.append(" (type: ");
/* 253 */     result.append(this.type);
/* 254 */     result.append(", functionName: ");
/* 255 */     result.append(this.functionName);
/* 256 */     result.append(')');
/* 257 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CFunctionCallImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */