/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EDataTypeImpl
/*     */   extends EClassifierImpl
/*     */   implements EDataType
/*     */ {
/*     */   protected static final boolean SERIALIZABLE_EDEFAULT = true;
/*     */   protected static final int SERIALIZABLE_EFLAG = 256;
/*     */   protected Object defaultValue;
/*     */   protected boolean defaultValueIsSet;
/*     */   
/*     */   protected EDataTypeImpl() {
/*  76 */     this.defaultValue = null;
/*  77 */     this.defaultValueIsSet = false;
/*     */     this.eFlags |= 0x100;
/*     */   }
/*     */   
/*     */   public Object getDefaultValue() {
/*  82 */     if (!this.defaultValueIsSet) {
/*     */       
/*  84 */       Class<?> instanceClass = null;
/*     */       
/*     */       try {
/*  87 */         instanceClass = getInstanceClass();
/*     */       }
/*  89 */       catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       this.defaultValue = null;
/*  95 */       if (instanceClass != null && instanceClass.isPrimitive())
/*     */       {
/*  97 */         if (instanceClass == boolean.class) {
/*  98 */           this.defaultValue = Boolean.FALSE;
/*  99 */         } else if (instanceClass == int.class) {
/* 100 */           this.defaultValue = Integer.valueOf(0);
/* 101 */         } else if (instanceClass == float.class) {
/* 102 */           this.defaultValue = Float.valueOf(0.0F);
/* 103 */         } else if (instanceClass == double.class) {
/* 104 */           this.defaultValue = Double.valueOf(0.0D);
/* 105 */         } else if (instanceClass == long.class) {
/* 106 */           this.defaultValue = Long.valueOf(0L);
/* 107 */         } else if (instanceClass == short.class) {
/* 108 */           this.defaultValue = Short.valueOf((short)0);
/* 109 */         } else if (instanceClass == byte.class) {
/* 110 */           this.defaultValue = Byte.valueOf((byte)0);
/*     */         } else {
/* 112 */           this.defaultValue = Character.valueOf(false);
/*     */         }  } 
/* 114 */       this.defaultValueIsSet = true;
/*     */     } 
/* 116 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstanceClassGen(Class<?> instanceClass) {
/* 122 */     super.setInstanceClassGen(instanceClass);
/* 123 */     this.defaultValueIsSet = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGeneratedInstanceClass(boolean isGenerated) {
/* 129 */     super.setGeneratedInstanceClass(isGenerated);
/*     */     
/* 131 */     setDataTypeGeneratedInstanceClass(isGenerated);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDataTypeGeneratedInstanceClass(boolean isGenerated) {
/* 140 */     if (isGenerated)
/*     */     {
/* 142 */       this.instanceClassName = "org.eclipse.emf.common.util.AbstractEnumerator";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 154 */     return EcorePackage.Literals.EDATA_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSerializable() {
/* 164 */     return ((this.eFlags & 0x100) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSerializable(boolean newSerializable) {
/* 174 */     boolean oldSerializable = ((this.eFlags & 0x100) != 0);
/* 175 */     if (newSerializable) { this.eFlags |= 0x100; } else { this.eFlags &= 0xFFFFFEFF; }
/* 176 */      if (eNotificationRequired()) {
/* 177 */       eNotify((Notification)new ENotificationImpl(this, 1, 8, oldSerializable, newSerializable));
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
/* 188 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 191 */         return getEAnnotations();
/*     */       case 1:
/* 193 */         return getName();
/*     */       case 2:
/* 195 */         return getInstanceClassName();
/*     */       case 3:
/* 197 */         return getInstanceClass();
/*     */       case 4:
/* 199 */         return getDefaultValue();
/*     */       case 5:
/* 201 */         return getInstanceTypeName();
/*     */       case 6:
/* 203 */         if (resolve) return getEPackage(); 
/* 204 */         return basicGetEPackage();
/*     */       case 7:
/* 206 */         return getETypeParameters();
/*     */       case 8:
/* 208 */         return Boolean.valueOf(isSerializable());
/*     */     } 
/* 210 */     return eDynamicGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 222 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 225 */         getEAnnotations().clear();
/* 226 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 229 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 232 */         setInstanceClassName((String)newValue);
/*     */         return;
/*     */       case 5:
/* 235 */         setInstanceTypeName((String)newValue);
/*     */         return;
/*     */       case 7:
/* 238 */         getETypeParameters().clear();
/* 239 */         getETypeParameters().addAll((Collection)newValue);
/*     */         return;
/*     */       case 8:
/* 242 */         setSerializable(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 245 */     eDynamicSet(featureID, newValue);
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
/* 256 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 259 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 262 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 265 */         unsetInstanceClassName();
/*     */         return;
/*     */       case 5:
/* 268 */         unsetInstanceTypeName();
/*     */         return;
/*     */       case 7:
/* 271 */         getETypeParameters().clear();
/*     */         return;
/*     */       case 8:
/* 274 */         setSerializable(true);
/*     */         return;
/*     */     } 
/* 277 */     eDynamicUnset(featureID);
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
/* 288 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 291 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 293 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 295 */         return isSetInstanceClassName();
/*     */       case 3:
/* 297 */         return (getInstanceClass() != null);
/*     */       case 4:
/* 299 */         return (DEFAULT_VALUE_EDEFAULT == null) ? ((getDefaultValue() != null)) : (!DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue()));
/*     */       case 5:
/* 301 */         return isSetInstanceTypeName();
/*     */       case 6:
/* 303 */         return (basicGetEPackage() != null);
/*     */       case 7:
/* 305 */         return (this.eTypeParameters != null && !this.eTypeParameters.isEmpty());
/*     */       case 8:
/* 307 */         return !((this.eFlags & 0x100) != 0);
/*     */     } 
/* 309 */     return eDynamicIsSet(featureID);
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
/* 320 */     if (eIsProxy()) return super.toString();
/*     */     
/* 322 */     StringBuffer result = new StringBuffer(super.toString());
/* 323 */     result.append(" (serializable: ");
/* 324 */     result.append(((this.eFlags & 0x100) != 0));
/* 325 */     result.append(')');
/* 326 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EDataTypeImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */