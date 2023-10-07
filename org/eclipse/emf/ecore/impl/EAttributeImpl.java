/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.util.FeatureMapUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EAttributeImpl
/*     */   extends EStructuralFeatureImpl
/*     */   implements EAttribute
/*     */ {
/*     */   protected static final boolean ID_EDEFAULT = false;
/*     */   protected static final int ID_EFLAG = 32768;
/*     */   protected byte effectiveIsMany;
/*     */   protected EDataType eAttributeType;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  90 */     return EcorePackage.Literals.EATTRIBUTE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isID() {
/* 101 */     return ((this.eFlags & 0x8000) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMany() {
/*     */     int upper;
/*     */     EClassifier eType;
/* 109 */     switch (this.effectiveIsMany) {
/*     */ 
/*     */       
/*     */       case -1:
/* 113 */         return true;
/*     */ 
/*     */       
/*     */       case 0:
/* 117 */         upper = getUpperBound();
/* 118 */         if (upper > 1 || upper == -1) {
/*     */           
/* 120 */           this.effectiveIsMany = -1;
/* 121 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 125 */         eType = getEType();
/* 126 */         if (eType != null && FeatureMapUtil.isFeatureMapEntry(eType)) {
/*     */           
/* 128 */           this.effectiveIsMany = -1;
/* 129 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 133 */         this.effectiveIsMany = 1;
/* 134 */         return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpperBound(int upperBound) {
/* 149 */     this.effectiveIsMany = 0;
/* 150 */     super.setUpperBound(upperBound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain setEType(EClassifier newEType, NotificationChain msgs) {
/* 156 */     this.effectiveIsMany = 0;
/* 157 */     this.eAttributeType = null;
/* 158 */     return super.setEType(newEType, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setID(boolean newID) {
/* 168 */     boolean oldID = ((this.eFlags & 0x8000) != 0);
/* 169 */     if (newID) { this.eFlags |= 0x8000; } else { this.eFlags &= 0xFFFF7FFF; }
/* 170 */      if (eNotificationRequired()) {
/* 171 */       eNotify((Notification)new ENotificationImpl(this, 1, 18, oldID, newID));
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
/*     */   public EDataType getEAttributeType() {
/* 183 */     if (this.eAttributeType == null || (!isFrozen() && this.eAttributeType.eIsProxy())) {
/*     */       
/* 185 */       EClassifier eType = getEType();
/* 186 */       if (eType instanceof EDataType)
/*     */       {
/* 188 */         this.eAttributeType = (EDataType)eType;
/*     */       }
/*     */     } 
/* 191 */     return this.eAttributeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EDataType basicGetEAttributeType() {
/* 201 */     if (this.eAttributeType == null) {
/*     */       
/* 203 */       EClassifier eType = basicGetEType();
/* 204 */       if (eType instanceof EDataType)
/*     */       {
/* 206 */         this.eAttributeType = (EDataType)eType;
/*     */       }
/*     */     } 
/* 209 */     return this.eAttributeType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void freeze() {
/* 215 */     getEAttributeType();
/* 216 */     super.freeze();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 227 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 230 */         return getEAnnotations();
/*     */       case 1:
/* 232 */         return getName();
/*     */       case 2:
/* 234 */         return Boolean.valueOf(isOrdered());
/*     */       case 3:
/* 236 */         return Boolean.valueOf(isUnique());
/*     */       case 4:
/* 238 */         return Integer.valueOf(getLowerBound());
/*     */       case 5:
/* 240 */         return Integer.valueOf(getUpperBound());
/*     */       case 6:
/* 242 */         return Boolean.valueOf(isMany());
/*     */       case 7:
/* 244 */         return Boolean.valueOf(isRequired());
/*     */       case 8:
/* 246 */         if (resolve) return getEType(); 
/* 247 */         return basicGetEType();
/*     */       case 9:
/* 249 */         return getEGenericType();
/*     */       case 10:
/* 251 */         return Boolean.valueOf(isChangeable());
/*     */       case 11:
/* 253 */         return Boolean.valueOf(isVolatile());
/*     */       case 12:
/* 255 */         return Boolean.valueOf(isTransient());
/*     */       case 13:
/* 257 */         return getDefaultValueLiteral();
/*     */       case 14:
/* 259 */         return getDefaultValue();
/*     */       case 15:
/* 261 */         return Boolean.valueOf(isUnsettable());
/*     */       case 16:
/* 263 */         return Boolean.valueOf(isDerived());
/*     */       case 17:
/* 265 */         return getEContainingClass();
/*     */       case 18:
/* 267 */         return Boolean.valueOf(isID());
/*     */       case 19:
/* 269 */         if (resolve) return getEAttributeType(); 
/* 270 */         return basicGetEAttributeType();
/*     */     } 
/* 272 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 284 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 287 */         getEAnnotations().clear();
/* 288 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 291 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 294 */         setOrdered(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 3:
/* 297 */         setUnique(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 4:
/* 300 */         setLowerBound(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 5:
/* 303 */         setUpperBound(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 8:
/* 306 */         setEType((EClassifier)newValue);
/*     */         return;
/*     */       case 9:
/* 309 */         setEGenericType((EGenericType)newValue);
/*     */         return;
/*     */       case 10:
/* 312 */         setChangeable(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 11:
/* 315 */         setVolatile(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 12:
/* 318 */         setTransient(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 13:
/* 321 */         setDefaultValueLiteral((String)newValue);
/*     */         return;
/*     */       case 15:
/* 324 */         setUnsettable(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 16:
/* 327 */         setDerived(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 18:
/* 330 */         setID(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 333 */     eDynamicSet(featureID, newValue);
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
/* 344 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 347 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 350 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 353 */         setOrdered(true);
/*     */         return;
/*     */       case 3:
/* 356 */         setUnique(true);
/*     */         return;
/*     */       case 4:
/* 359 */         setLowerBound(0);
/*     */         return;
/*     */       case 5:
/* 362 */         setUpperBound(1);
/*     */         return;
/*     */       case 8:
/* 365 */         unsetEType();
/*     */         return;
/*     */       case 9:
/* 368 */         unsetEGenericType();
/*     */         return;
/*     */       case 10:
/* 371 */         setChangeable(true);
/*     */         return;
/*     */       case 11:
/* 374 */         setVolatile(false);
/*     */         return;
/*     */       case 12:
/* 377 */         setTransient(false);
/*     */         return;
/*     */       case 13:
/* 380 */         setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
/*     */         return;
/*     */       case 15:
/* 383 */         setUnsettable(false);
/*     */         return;
/*     */       case 16:
/* 386 */         setDerived(false);
/*     */         return;
/*     */       case 18:
/* 389 */         setID(false);
/*     */         return;
/*     */     } 
/* 392 */     eDynamicUnset(featureID);
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
/* 403 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 406 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 408 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 410 */         return !((this.eFlags & 0x100) != 0);
/*     */       case 3:
/* 412 */         return !((this.eFlags & 0x200) != 0);
/*     */       case 4:
/* 414 */         return (this.lowerBound != 0);
/*     */       case 5:
/* 416 */         return (this.upperBound != 1);
/*     */       case 6:
/* 418 */         return isMany();
/*     */       case 7:
/* 420 */         return isRequired();
/*     */       case 8:
/* 422 */         return isSetEType();
/*     */       case 9:
/* 424 */         return isSetEGenericType();
/*     */       case 10:
/* 426 */         return !((this.eFlags & 0x400) != 0);
/*     */       case 11:
/* 428 */         return ((this.eFlags & 0x800) != 0);
/*     */       case 12:
/* 430 */         return ((this.eFlags & 0x1000) != 0);
/*     */       case 13:
/* 432 */         return (DEFAULT_VALUE_LITERAL_EDEFAULT == null) ? ((this.defaultValueLiteral != null)) : (!DEFAULT_VALUE_LITERAL_EDEFAULT.equals(this.defaultValueLiteral));
/*     */       case 14:
/* 434 */         return (DEFAULT_VALUE_EDEFAULT == null) ? ((getDefaultValue() != null)) : (!DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue()));
/*     */       case 15:
/* 436 */         return ((this.eFlags & 0x2000) != 0);
/*     */       case 16:
/* 438 */         return ((this.eFlags & 0x4000) != 0);
/*     */       case 17:
/* 440 */         return (getEContainingClass() != null);
/*     */       case 18:
/* 442 */         return ((this.eFlags & 0x8000) != 0);
/*     */       case 19:
/* 444 */         return (basicGetEAttributeType() != null);
/*     */     } 
/* 446 */     return eDynamicIsSet(featureID);
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
/* 457 */     if (eIsProxy()) return super.toString();
/*     */     
/* 459 */     StringBuffer result = new StringBuffer(super.toString());
/* 460 */     result.append(" (iD: ");
/* 461 */     result.append(((this.eFlags & 0x8000) != 0));
/* 462 */     result.append(')');
/* 463 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EAttributeImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */