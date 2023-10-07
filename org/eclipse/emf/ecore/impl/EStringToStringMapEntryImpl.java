/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EStringToStringMapEntryImpl
/*     */   extends MinimalEObjectImpl.Container
/*     */   implements BasicEMap.Entry<String, String>
/*     */ {
/*  52 */   protected static final String KEY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   protected String key = KEY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected static final String VALUE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   protected String value = VALUE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int hash;
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
/* 102 */     return EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypedKey() {
/* 112 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypedKeyGen(String newKey) {
/* 122 */     String oldKey = this.key;
/* 123 */     this.key = newKey;
/* 124 */     if (eNotificationRequired()) {
/* 125 */       eNotify((Notification)new ENotificationImpl(this, 1, 0, oldKey, this.key));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTypedKey(String newKey) {
/* 130 */     setTypedKeyGen((newKey == null) ? null : newKey.intern());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypedValue() {
/* 140 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypedValue(String newValue) {
/* 150 */     String oldValue = this.value;
/* 151 */     this.value = newValue;
/* 152 */     if (eNotificationRequired()) {
/* 153 */       eNotify((Notification)new ENotificationImpl(this, 1, 1, oldValue, this.value));
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
/* 164 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 167 */         return getTypedKey();
/*     */       case 1:
/* 169 */         return getTypedValue();
/*     */     } 
/* 171 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 182 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 185 */         setTypedKey((String)newValue);
/*     */         return;
/*     */       case 1:
/* 188 */         setTypedValue((String)newValue);
/*     */         return;
/*     */     } 
/* 191 */     eDynamicSet(featureID, newValue);
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
/* 202 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 205 */         setTypedKey(KEY_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 208 */         setTypedValue(VALUE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 211 */     eDynamicUnset(featureID);
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
/* 222 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 225 */         return (KEY_EDEFAULT == null) ? ((this.key != null)) : (!KEY_EDEFAULT.equals(this.key));
/*     */       case 1:
/* 227 */         return (VALUE_EDEFAULT == null) ? ((this.value != null)) : (!VALUE_EDEFAULT.equals(this.value));
/*     */     } 
/* 229 */     return eDynamicIsSet(featureID);
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
/* 240 */     if (eIsProxy()) return super.toString();
/*     */     
/* 242 */     StringBuffer result = new StringBuffer(super.toString());
/* 243 */     result.append(" (key: ");
/* 244 */     result.append(this.key);
/* 245 */     result.append(", value: ");
/* 246 */     result.append(this.value);
/* 247 */     result.append(')');
/* 248 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EStringToStringMapEntryImpl() {
/* 256 */     this.hash = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHash() {
/* 265 */     if (this.hash == -1) {
/*     */       
/* 267 */       Object theKey = getKey();
/* 268 */       this.hash = (theKey == null) ? 0 : theKey.hashCode();
/*     */     } 
/* 270 */     return this.hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHash(int hash) {
/* 280 */     this.hash = hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKey() {
/* 290 */     return getTypedKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKey(String key) {
/* 300 */     setTypedKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 310 */     return getTypedValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String setValue(String value) {
/* 320 */     String oldValue = getValue();
/* 321 */     setTypedValue(value);
/* 322 */     return oldValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<String, String> getEMap() {
/* 333 */     EObject container = eContainer();
/* 334 */     return (container == null) ? null : (EMap<String, String>)container.eGet((EStructuralFeature)eContainmentFeature());
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EStringToStringMapEntryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */