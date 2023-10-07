/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EEnumLiteral;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EParameter;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.ETypeParameter;
/*     */ import org.eclipse.emf.ecore.EcoreFactory;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EcoreFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements EcoreFactory
/*     */ {
/*     */   public static EcoreFactory init() {
/*     */     try {
/*  55 */       EcoreFactory theEcoreFactory = (EcoreFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2002/Ecore");
/*  56 */       if (theEcoreFactory != null)
/*     */       {
/*  58 */         return theEcoreFactory;
/*     */       }
/*     */     }
/*  61 */     catch (Exception exception) {
/*     */       
/*  63 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  65 */     return new EcoreFactoryImpl();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/*  87 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  89 */         return (EObject)createEAttribute();
/*  90 */       case 1: return (EObject)createEAnnotation();
/*  91 */       case 2: return (EObject)createEClass();
/*  92 */       case 4: return (EObject)createEDataType();
/*  93 */       case 5: return (EObject)createEEnum();
/*  94 */       case 6: return (EObject)createEEnumLiteral();
/*  95 */       case 7: return (EObject)createEFactory();
/*  96 */       case 10: return createEObject();
/*  97 */       case 11: return (EObject)createEOperation();
/*  98 */       case 12: return (EObject)createEPackage();
/*  99 */       case 13: return (EObject)createEParameter();
/* 100 */       case 14: return (EObject)createEReference();
/* 101 */       case 17: return (EObject)createEStringToStringMapEntry();
/* 102 */       case 18: return (EObject)createEGenericType();
/* 103 */       case 19: return (EObject)createETypeParameter();
/*     */     } 
/* 105 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/* 117 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 20:
/* 120 */         return createEBigDecimalFromString(eDataType, initialValue);
/*     */       case 21:
/* 122 */         return createEBigIntegerFromString(eDataType, initialValue);
/*     */       case 22:
/* 124 */         return createEBooleanFromString(eDataType, initialValue);
/*     */       case 23:
/* 126 */         return createEBooleanObjectFromString(eDataType, initialValue);
/*     */       case 24:
/* 128 */         return createEByteFromString(eDataType, initialValue);
/*     */       case 25:
/* 130 */         return createEByteArrayFromString(eDataType, initialValue);
/*     */       case 26:
/* 132 */         return createEByteObjectFromString(eDataType, initialValue);
/*     */       case 27:
/* 134 */         return createECharFromString(eDataType, initialValue);
/*     */       case 28:
/* 136 */         return createECharacterObjectFromString(eDataType, initialValue);
/*     */       case 29:
/* 138 */         return createEDateFromString(eDataType, initialValue);
/*     */       case 31:
/* 140 */         return createEDoubleFromString(eDataType, initialValue);
/*     */       case 32:
/* 142 */         return createEDoubleObjectFromString(eDataType, initialValue);
/*     */       case 37:
/* 144 */         return createEFloatFromString(eDataType, initialValue);
/*     */       case 38:
/* 146 */         return createEFloatObjectFromString(eDataType, initialValue);
/*     */       case 39:
/* 148 */         return createEIntFromString(eDataType, initialValue);
/*     */       case 40:
/* 150 */         return createEIntegerObjectFromString(eDataType, initialValue);
/*     */       case 41:
/* 152 */         return createEJavaClassFromString(eDataType, initialValue);
/*     */       case 42:
/* 154 */         return createEJavaObjectFromString(eDataType, initialValue);
/*     */       case 43:
/* 156 */         return createELongFromString(eDataType, initialValue);
/*     */       case 44:
/* 158 */         return createELongObjectFromString(eDataType, initialValue);
/*     */       case 48:
/* 160 */         return createEShortFromString(eDataType, initialValue);
/*     */       case 49:
/* 162 */         return createEShortObjectFromString(eDataType, initialValue);
/*     */       case 50:
/* 164 */         return createEStringFromString(eDataType, initialValue);
/*     */     } 
/* 166 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 178 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 20:
/* 181 */         return convertEBigDecimalToString(eDataType, instanceValue);
/*     */       case 21:
/* 183 */         return convertEBigIntegerToString(eDataType, instanceValue);
/*     */       case 22:
/* 185 */         return convertEBooleanToString(eDataType, instanceValue);
/*     */       case 23:
/* 187 */         return convertEBooleanObjectToString(eDataType, instanceValue);
/*     */       case 24:
/* 189 */         return convertEByteToString(eDataType, instanceValue);
/*     */       case 25:
/* 191 */         return convertEByteArrayToString(eDataType, instanceValue);
/*     */       case 26:
/* 193 */         return convertEByteObjectToString(eDataType, instanceValue);
/*     */       case 27:
/* 195 */         return convertECharToString(eDataType, instanceValue);
/*     */       case 28:
/* 197 */         return convertECharacterObjectToString(eDataType, instanceValue);
/*     */       case 29:
/* 199 */         return convertEDateToString(eDataType, instanceValue);
/*     */       case 31:
/* 201 */         return convertEDoubleToString(eDataType, instanceValue);
/*     */       case 32:
/* 203 */         return convertEDoubleObjectToString(eDataType, instanceValue);
/*     */       case 37:
/* 205 */         return convertEFloatToString(eDataType, instanceValue);
/*     */       case 38:
/* 207 */         return convertEFloatObjectToString(eDataType, instanceValue);
/*     */       case 39:
/* 209 */         return convertEIntToString(eDataType, instanceValue);
/*     */       case 40:
/* 211 */         return convertEIntegerObjectToString(eDataType, instanceValue);
/*     */       case 41:
/* 213 */         return convertEJavaClassToString(eDataType, instanceValue);
/*     */       case 42:
/* 215 */         return convertEJavaObjectToString(eDataType, instanceValue);
/*     */       case 43:
/* 217 */         return convertELongToString(eDataType, instanceValue);
/*     */       case 44:
/* 219 */         return convertELongObjectToString(eDataType, instanceValue);
/*     */       case 48:
/* 221 */         return convertEShortToString(eDataType, instanceValue);
/*     */       case 49:
/* 223 */         return convertEShortObjectToString(eDataType, instanceValue);
/*     */       case 50:
/* 225 */         return convertEStringToString(eDataType, instanceValue);
/*     */     } 
/* 227 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject createEObject() {
/* 238 */     EObjectImpl eObject = new EObjectImpl();
/* 239 */     return eObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EAttribute createEAttribute() {
/* 249 */     EAttributeImpl eAttribute = new EAttributeImpl();
/* 250 */     return eAttribute;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EAnnotation createEAnnotation() {
/* 260 */     EAnnotationImpl eAnnotation = new EAnnotationImpl();
/* 261 */     return eAnnotation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClass createEClass() {
/* 271 */     EClassImpl eClass = new EClassImpl();
/* 272 */     return eClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EDataType createEDataType() {
/* 282 */     EDataTypeImpl eDataType = new EDataTypeImpl();
/* 283 */     return eDataType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EParameter createEParameter() {
/* 293 */     EParameterImpl eParameter = new EParameterImpl();
/* 294 */     return eParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EOperation createEOperation() {
/* 304 */     EOperationImpl eOperation = new EOperationImpl();
/* 305 */     return eOperation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage createEPackage() {
/* 315 */     EPackageImpl ePackage = new EPackageImpl();
/* 316 */     return ePackage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EFactory createEFactory() {
/* 326 */     EFactoryImpl eFactory = new EFactoryImpl();
/* 327 */     return eFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EEnumLiteral createEEnumLiteral() {
/* 337 */     EEnumLiteralImpl eEnumLiteral = new EEnumLiteralImpl();
/* 338 */     return eEnumLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EEnum createEEnum() {
/* 348 */     EEnumImpl eEnum = new EEnumImpl();
/* 349 */     return eEnum;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Boolean booleanValueOf(String initialValue) {
/* 354 */     if ("true".equalsIgnoreCase(initialValue))
/*     */     {
/* 356 */       return Boolean.TRUE;
/*     */     }
/* 358 */     if ("false".equalsIgnoreCase(initialValue))
/*     */     {
/* 360 */       return Boolean.FALSE;
/*     */     }
/*     */ 
/*     */     
/* 364 */     throw new IllegalArgumentException("Expecting true or false");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean createEBooleanObjectFromString(EDataType metaObject, String initialValue) {
/* 375 */     return (initialValue == null) ? null : booleanValueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEBooleanObjectToString(EDataType metaObject, Object instanceValue) {
/* 385 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Character createECharacterObjectFromString(EDataType metaObject, String initialValue) {
/* 395 */     if (initialValue == null)
/*     */     {
/* 397 */       return null;
/*     */     }
/*     */     
/* 400 */     char charValue = Character.MIN_VALUE;
/*     */     
/*     */     try {
/* 403 */       charValue = (char)Integer.parseInt(initialValue);
/*     */     }
/* 405 */     catch (NumberFormatException e) {
/*     */       
/* 407 */       char[] carray = initialValue.toCharArray();
/* 408 */       charValue = carray[0];
/*     */     } 
/* 410 */     return Character.valueOf(charValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertECharacterObjectToString(EDataType metaObject, Object instanceValue) {
/* 420 */     if (instanceValue instanceof Character)
/*     */     {
/* 422 */       return Integer.toString(((Character)instanceValue).charValue());
/*     */     }
/*     */     
/* 425 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date createEDateFromString(EDataType eDataType, String initialValue) {
/* 435 */     if (initialValue == null)
/*     */     {
/* 437 */       return null;
/*     */     }
/*     */     
/* 440 */     Exception exception = null;
/* 441 */     for (int i = 0; i < EDATE_FORMATS.length; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 445 */         return EDATE_FORMATS[i].parse(initialValue);
/*     */       }
/* 447 */       catch (ParseException parseException) {
/*     */         
/* 449 */         exception = parseException;
/*     */       } 
/*     */     } 
/* 452 */     throw new WrappedException(exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEDateToString(EDataType eDataType, Object instanceValue) {
/* 462 */     if (instanceValue == null)
/*     */     {
/* 464 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 468 */     return EDATE_FORMATS[0].format((Date)instanceValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Double createEDoubleObjectFromString(EDataType metaObject, String initialValue) {
/* 479 */     return (initialValue == null) ? null : Double.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEDoubleObjectToString(EDataType metaObject, Object instanceValue) {
/* 489 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float createEFloatObjectFromString(EDataType metaObject, String initialValue) {
/* 499 */     return (initialValue == null) ? null : Float.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEFloatObjectToString(EDataType metaObject, Object instanceValue) {
/* 509 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer createEIntegerObjectFromString(EDataType metaObject, String initialValue) {
/* 519 */     return (initialValue == null) ? null : Integer.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEIntegerObjectToString(EDataType metaObject, Object instanceValue) {
/* 529 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EReference createEReference() {
/* 539 */     EReferenceImpl eReference = new EReferenceImpl();
/* 540 */     return eReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map.Entry<String, String> createEStringToStringMapEntry() {
/* 550 */     EStringToStringMapEntryImpl eStringToStringMapEntry = new EStringToStringMapEntryImpl();
/* 551 */     return (Map.Entry<String, String>)eStringToStringMapEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EGenericType createEGenericType() {
/* 561 */     EGenericTypeImpl eGenericType = new EGenericTypeImpl();
/* 562 */     return eGenericType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ETypeParameter createETypeParameter() {
/* 572 */     ETypeParameterImpl eTypeParameter = new ETypeParameterImpl();
/* 573 */     return eTypeParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal createEBigDecimalFromString(EDataType eDataType, String initialValue) {
/* 583 */     return (initialValue == null) ? null : new BigDecimal(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEBigDecimalToString(EDataType eDataType, Object instanceValue) {
/* 593 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigInteger createEBigIntegerFromString(EDataType eDataType, String initialValue) {
/* 603 */     return (initialValue == null) ? null : new BigInteger(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEBigIntegerToString(EDataType eDataType, Object instanceValue) {
/* 613 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EcorePackage getEcorePackage() {
/* 623 */     return (EcorePackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static EcorePackage getPackage() {
/* 632 */     return EcorePackage.eINSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String createEStringFromString(EDataType metaObject, String initialValue) {
/* 642 */     return initialValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEStringToString(EDataType metaObject, Object instanceValue) {
/* 652 */     return (String)instanceValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer createEIntFromString(EDataType metaObject, String initialValue) {
/* 662 */     return (initialValue == null) ? null : Integer.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEIntToString(EDataType metaObject, Object instanceValue) {
/* 672 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean createEBooleanFromString(EDataType metaObject, String initialValue) {
/* 682 */     return (initialValue == null) ? null : booleanValueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEBooleanToString(EDataType metaObject, Object instanceValue) {
/* 692 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Byte createEByteObjectFromString(EDataType metaObject, String initialValue) {
/* 702 */     return (initialValue == null) ? null : Byte.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEByteObjectToString(EDataType metaObject, Object instanceValue) {
/* 712 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float createEFloatFromString(EDataType metaObject, String initialValue) {
/* 722 */     return (initialValue == null) ? null : Float.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEFloatToString(EDataType metaObject, Object instanceValue) {
/* 732 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Character createECharFromString(EDataType metaObject, String initialValue) {
/* 742 */     if (initialValue == null)
/*     */     {
/* 744 */       return null;
/*     */     }
/* 746 */     char charValue = Character.MIN_VALUE;
/*     */     
/*     */     try {
/* 749 */       charValue = (char)Integer.parseInt(initialValue);
/*     */     }
/* 751 */     catch (NumberFormatException e) {
/*     */       
/* 753 */       char[] carray = initialValue.toCharArray();
/* 754 */       charValue = carray[0];
/*     */     } 
/* 756 */     return Character.valueOf(charValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertECharToString(EDataType metaObject, Object instanceValue) {
/* 766 */     if (instanceValue instanceof Character)
/*     */     {
/* 768 */       return Integer.toString(((Character)instanceValue).charValue());
/*     */     }
/*     */     
/* 771 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long createELongFromString(EDataType metaObject, String initialValue) {
/* 781 */     return (initialValue == null) ? null : Long.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertELongToString(EDataType metaObject, Object instanceValue) {
/* 791 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Double createEDoubleFromString(EDataType metaObject, String initialValue) {
/* 801 */     return (initialValue == null) ? null : Double.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEDoubleToString(EDataType metaObject, Object instanceValue) {
/* 811 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Byte createEByteFromString(EDataType metaObject, String initialValue) {
/* 821 */     return (initialValue == null) ? null : Byte.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEByteToString(EDataType metaObject, Object instanceValue) {
/* 831 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] createEByteArrayFromString(EDataType eDataType, String initialValue) {
/* 842 */     return hexStringToBytes(initialValue);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static byte hexCharToByte(char character) {
/* 847 */     return EFactoryImpl.hexCharToByte(character);
/*     */   }
/*     */   
/* 850 */   protected static final char[] HEX_DIGITS = EFactoryImpl.HEX_DIGITS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEByteArrayToString(EDataType eDataType, Object instanceValue) {
/* 859 */     if (instanceValue == null)
/*     */     {
/* 861 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 865 */     byte[] bytes = (byte[])instanceValue;
/* 866 */     return bytesToHexString(bytes, bytes.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Short createEShortFromString(EDataType metaObject, String initialValue) {
/* 877 */     return (initialValue == null) ? null : Short.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEShortToString(EDataType metaObject, Object instanceValue) {
/* 887 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> createEJavaClassFromString(EDataType metaObject, String initialValue) {
/*     */     try {
/* 899 */       if (initialValue == null) return null; 
/* 900 */       if ("boolean".equals(initialValue)) return boolean.class; 
/* 901 */       if ("byte".equals(initialValue)) return byte.class; 
/* 902 */       if ("char".equals(initialValue)) return char.class; 
/* 903 */       if ("double".equals(initialValue)) return double.class; 
/* 904 */       if ("float".equals(initialValue)) return float.class; 
/* 905 */       if ("int".equals(initialValue)) return int.class; 
/* 906 */       if ("long".equals(initialValue)) return long.class; 
/* 907 */       if ("short".equals(initialValue)) return short.class; 
/* 908 */       return Class.forName(initialValue);
/*     */     }
/* 910 */     catch (ClassNotFoundException e) {
/*     */       
/* 912 */       throw new WrappedException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEJavaClassToString(EDataType metaObject, Object instanceValue) {
/* 923 */     return (instanceValue == null) ? "" : ((Class)instanceValue).getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object createEJavaObjectFromString(EDataType eDataType, String initialValue) {
/* 933 */     return createFromString(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEJavaObjectToString(EDataType eDataType, Object instanceValue) {
/* 943 */     return convertToString(instanceValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long createELongObjectFromString(EDataType metaObject, String initialValue) {
/* 953 */     return (initialValue == null) ? null : Long.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertELongObjectToString(EDataType metaObject, Object instanceValue) {
/* 963 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Short createEShortObjectFromString(EDataType metaObject, String initialValue) {
/* 973 */     return (initialValue == null) ? null : Short.valueOf(initialValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertEShortObjectToString(EDataType metaObject, Object instanceValue) {
/* 983 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EcoreFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */