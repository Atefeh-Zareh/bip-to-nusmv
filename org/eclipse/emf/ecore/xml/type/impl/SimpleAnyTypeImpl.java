/*     */ package org.eclipse.emf.ecore.xml.type.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
/*     */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleAnyTypeImpl
/*     */   extends AnyTypeImpl
/*     */   implements SimpleAnyType
/*     */ {
/*  53 */   protected static final String RAW_VALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected static final Object VALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EDataType instanceType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  93 */     return XMLTypePackage.Literals.SIMPLE_ANY_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRawValue() {
/* 104 */     return (String)getMixed().get((EStructuralFeature)XMLTypePackage.Literals.SIMPLE_ANY_TYPE__RAW_VALUE, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRawValue(String newRawValue) {
/* 114 */     ((FeatureMap.Internal)getMixed()).set((EStructuralFeature)XMLTypePackage.Literals.SIMPLE_ANY_TYPE__RAW_VALUE, newRawValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 124 */     return EcoreUtil.createFromString(this.instanceType, getRawValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object newValue) {
/* 134 */     setRawValue(EcoreUtil.convertToString(this.instanceType, newValue));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EDataType getInstanceType() {
/* 144 */     return this.instanceType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstanceType(EDataType newInstanceType) {
/* 154 */     EDataType oldInstanceType = this.instanceType;
/* 155 */     this.instanceType = newInstanceType;
/* 156 */     if (eNotificationRequired()) {
/* 157 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, oldInstanceType, this.instanceType));
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
/* 168 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 171 */         if (coreType) return getMixed(); 
/* 172 */         return ((FeatureMap.Internal)getMixed()).getWrapper();
/*     */       case 1:
/* 174 */         if (coreType) return getAny(); 
/* 175 */         return ((FeatureMap.Internal)getAny()).getWrapper();
/*     */       case 2:
/* 177 */         if (coreType) return getAnyAttribute(); 
/* 178 */         return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
/*     */       case 3:
/* 180 */         return getRawValue();
/*     */       case 4:
/* 182 */         return getValue();
/*     */       case 5:
/* 184 */         return getInstanceType();
/*     */     } 
/* 186 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 197 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 200 */         ((FeatureMap.Internal)getMixed()).set(newValue);
/*     */         return;
/*     */       case 1:
/* 203 */         ((FeatureMap.Internal)getAny()).set(newValue);
/*     */         return;
/*     */       case 2:
/* 206 */         ((FeatureMap.Internal)getAnyAttribute()).set(newValue);
/*     */         return;
/*     */       case 3:
/* 209 */         setRawValue((String)newValue);
/*     */         return;
/*     */       case 4:
/* 212 */         setValue(newValue);
/*     */         return;
/*     */       case 5:
/* 215 */         setInstanceType((EDataType)newValue);
/*     */         return;
/*     */     } 
/* 218 */     eDynamicSet(featureID, newValue);
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
/* 229 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 232 */         getMixed().clear();
/*     */         return;
/*     */       case 1:
/* 235 */         getAny().clear();
/*     */         return;
/*     */       case 2:
/* 238 */         getAnyAttribute().clear();
/*     */         return;
/*     */       case 3:
/* 241 */         setRawValue(RAW_VALUE_EDEFAULT);
/*     */         return;
/*     */       case 4:
/* 244 */         setValue(VALUE_EDEFAULT);
/*     */         return;
/*     */       case 5:
/* 247 */         setInstanceType((EDataType)null);
/*     */         return;
/*     */     } 
/* 250 */     eDynamicUnset(featureID);
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
/* 261 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 264 */         return (this.mixed != null && !this.mixed.isEmpty());
/*     */       case 1:
/* 266 */         return !getAny().isEmpty();
/*     */       case 2:
/* 268 */         return (this.anyAttribute != null && !this.anyAttribute.isEmpty());
/*     */       case 3:
/* 270 */         return (RAW_VALUE_EDEFAULT == null) ? ((getRawValue() != null)) : (!RAW_VALUE_EDEFAULT.equals(getRawValue()));
/*     */       case 4:
/* 272 */         return (VALUE_EDEFAULT == null) ? ((getValue() != null)) : (!VALUE_EDEFAULT.equals(getValue()));
/*     */       case 5:
/* 274 */         return (this.instanceType != null);
/*     */     } 
/* 276 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\impl\SimpleAnyTypeImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */