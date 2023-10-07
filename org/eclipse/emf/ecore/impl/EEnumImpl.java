/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EEnumLiteral;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EEnumImpl
/*     */   extends EDataTypeImpl
/*     */   implements EEnum
/*     */ {
/*     */   protected EList<EEnumLiteral> eLiterals;
/*     */   
/*     */   protected void freeze() {
/*  74 */     if (this.eLiterals != null)
/*     */     {
/*  76 */       for (int i = 0, size = this.eLiterals.size(); i < size; i++)
/*     */       {
/*  78 */         freeze(this.eLiterals.get(i));
/*     */       }
/*     */     }
/*  81 */     super.freeze();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDataTypeGeneratedInstanceClass(boolean isGenerated) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getDefaultValue() {
/*  93 */     EList<EEnumLiteral> eLiterals = getELiterals();
/*  94 */     if (!eLiterals.isEmpty())
/*     */     {
/*  96 */       return ((EEnumLiteral)eLiterals.get(0)).getInstance();
/*     */     }
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInstance(Object object) {
/* 107 */     if (object != null) {
/*     */       
/* 109 */       Class<?> instanceClass = getInstanceClass();
/* 110 */       if (instanceClass != null)
/*     */       {
/* 112 */         return instanceClass.isInstance(object);
/*     */       }
/*     */ 
/*     */       
/* 116 */       return getELiterals().contains(object);
/*     */     } 
/*     */     
/* 119 */     return false;
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
/* 130 */     return EcorePackage.Literals.EENUM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EEnumLiteral> getELiterals() {
/* 140 */     if (this.eLiterals == null)
/*     */     {
/* 142 */       this.eLiterals = (EList<EEnumLiteral>)new EObjectContainmentWithInverseEList(EEnumLiteral.class, this, 9, 5);
/*     */     }
/* 144 */     return this.eLiterals;
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
/* 155 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 158 */         return getEAnnotations();
/*     */       case 1:
/* 160 */         return getName();
/*     */       case 2:
/* 162 */         return getInstanceClassName();
/*     */       case 3:
/* 164 */         return getInstanceClass();
/*     */       case 4:
/* 166 */         return getDefaultValue();
/*     */       case 5:
/* 168 */         return getInstanceTypeName();
/*     */       case 6:
/* 170 */         if (resolve) return getEPackage(); 
/* 171 */         return basicGetEPackage();
/*     */       case 7:
/* 173 */         return getETypeParameters();
/*     */       case 8:
/* 175 */         return Boolean.valueOf(isSerializable());
/*     */       case 9:
/* 177 */         return getELiterals();
/*     */     } 
/* 179 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 191 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 194 */         getEAnnotations().clear();
/* 195 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 198 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 201 */         setInstanceClassName((String)newValue);
/*     */         return;
/*     */       case 5:
/* 204 */         setInstanceTypeName((String)newValue);
/*     */         return;
/*     */       case 7:
/* 207 */         getETypeParameters().clear();
/* 208 */         getETypeParameters().addAll((Collection)newValue);
/*     */         return;
/*     */       case 8:
/* 211 */         setSerializable(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 9:
/* 214 */         getELiterals().clear();
/* 215 */         getELiterals().addAll((Collection)newValue);
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
/* 232 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 235 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 238 */         unsetInstanceClassName();
/*     */         return;
/*     */       case 5:
/* 241 */         unsetInstanceTypeName();
/*     */         return;
/*     */       case 7:
/* 244 */         getETypeParameters().clear();
/*     */         return;
/*     */       case 8:
/* 247 */         setSerializable(true);
/*     */         return;
/*     */       case 9:
/* 250 */         getELiterals().clear();
/*     */         return;
/*     */     } 
/* 253 */     eDynamicUnset(featureID);
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
/* 264 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 267 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 269 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 271 */         return isSetInstanceClassName();
/*     */       case 3:
/* 273 */         return (getInstanceClass() != null);
/*     */       case 4:
/* 275 */         return (DEFAULT_VALUE_EDEFAULT == null) ? ((getDefaultValue() != null)) : (!DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue()));
/*     */       case 5:
/* 277 */         return isSetInstanceTypeName();
/*     */       case 6:
/* 279 */         return (basicGetEPackage() != null);
/*     */       case 7:
/* 281 */         return (this.eTypeParameters != null && !this.eTypeParameters.isEmpty());
/*     */       case 8:
/* 283 */         return !((this.eFlags & 0x100) != 0);
/*     */       case 9:
/* 285 */         return (this.eLiterals != null && !this.eLiterals.isEmpty());
/*     */     } 
/* 287 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 298 */     switch (operationID) {
/*     */       
/*     */       case 0:
/* 301 */         return getEAnnotation((String)arguments.get(0));
/*     */       case 1:
/* 303 */         return Boolean.valueOf(isInstance(arguments.get(0)));
/*     */       case 2:
/* 305 */         return Integer.valueOf(getClassifierID());
/*     */       case 3:
/* 307 */         return getEEnumLiteral((String)arguments.get(0));
/*     */       case 4:
/* 309 */         return getEEnumLiteral(((Integer)arguments.get(0)).intValue());
/*     */       case 5:
/* 311 */         return getEEnumLiteralByLiteral((String)arguments.get(0));
/*     */     } 
/* 313 */     return eDynamicInvoke(operationID, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EEnumLiteral getEEnumLiteral(String name) {
/* 321 */     if (name == null) {
/*     */       
/* 323 */       for (EEnumLiteral eEnumLiteral : getELiterals())
/*     */       {
/* 325 */         if (eEnumLiteral.getName() == null)
/*     */         {
/* 327 */           return eEnumLiteral;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 333 */       for (EEnumLiteral eEnumLiteral : getELiterals()) {
/*     */         
/* 335 */         if (name.equals(eEnumLiteral.getName()))
/*     */         {
/* 337 */           return eEnumLiteral;
/*     */         }
/*     */       } 
/*     */     } 
/* 341 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EEnumLiteral getEEnumLiteral(int intValue) {
/* 349 */     for (EEnumLiteral eEnumLiteral : getELiterals()) {
/*     */       
/* 351 */       if (eEnumLiteral.getValue() == intValue)
/*     */       {
/* 353 */         return eEnumLiteral;
/*     */       }
/*     */     } 
/* 356 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EEnumLiteral getEEnumLiteralByLiteral(String literal) {
/* 364 */     if (literal == null) {
/*     */       
/* 366 */       for (EEnumLiteral eEnumLiteral : getELiterals())
/*     */       {
/* 368 */         if (eEnumLiteral.getLiteral() == null)
/*     */         {
/* 370 */           return eEnumLiteral;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 376 */       for (EEnumLiteral eEnumLiteral : getELiterals()) {
/*     */         
/* 378 */         if (literal.equals(eEnumLiteral.getLiteral()))
/*     */         {
/* 380 */           return eEnumLiteral;
/*     */         }
/*     */       } 
/*     */     } 
/* 384 */     return null;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 396 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 399 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */       case 6:
/* 401 */         if (eInternalContainer() != null)
/* 402 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 403 */         return eBasicSetContainer(otherEnd, 6, msgs);
/*     */       case 9:
/* 405 */         return ((InternalEList)getELiterals()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 407 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 418 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 421 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 6:
/* 423 */         return eBasicSetContainer(null, 6, msgs);
/*     */       case 7:
/* 425 */         return ((InternalEList)getETypeParameters()).basicRemove(otherEnd, msgs);
/*     */       case 9:
/* 427 */         return ((InternalEList)getELiterals()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 429 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EEnumImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */