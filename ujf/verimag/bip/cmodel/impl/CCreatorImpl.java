/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CCreator;
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
/*     */ public class CCreatorImpl
/*     */   extends CCallImpl
/*     */   implements CCreator
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
/*     */ 
/*     */   
/*     */   protected static final boolean ARRAY_ALLOCATOR_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean arrayAllocator = false;
/*     */ 
/*     */ 
/*     */ 
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
/*  97 */     return CmodelPackage.Literals.CCREATOR;
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
/*     */   public boolean isArrayAllocator() {
/* 127 */     return this.arrayAllocator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArrayAllocator(boolean newArrayAllocator) {
/* 136 */     boolean oldArrayAllocator = this.arrayAllocator;
/* 137 */     this.arrayAllocator = newArrayAllocator;
/* 138 */     if (eNotificationRequired()) {
/* 139 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldArrayAllocator, this.arrayAllocator));
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
/* 153 */         return Boolean.valueOf(isArrayAllocator());
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
/* 170 */         setArrayAllocator(((Boolean)newValue).booleanValue());
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
/* 188 */         setArrayAllocator(false);
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
/* 205 */         return this.arrayAllocator;
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
/* 254 */     result.append(", arrayAllocator: ");
/* 255 */     result.append(this.arrayAllocator);
/* 256 */     result.append(')');
/* 257 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CCreatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */