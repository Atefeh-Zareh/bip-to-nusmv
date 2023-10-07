/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.util.BasicDiagnostic;
/*      */ import org.eclipse.emf.common.util.Diagnostic;
/*      */ import org.eclipse.emf.common.util.DiagnosticChain;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.Enumerator;
/*      */ import org.eclipse.emf.common.util.ResourceLocator;
/*      */ import org.eclipse.emf.common.util.TreeIterator;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.common.util.UniqueEList;
/*      */ import org.eclipse.emf.ecore.EAnnotation;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EEnum;
/*      */ import org.eclipse.emf.ecore.EEnumLiteral;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EGenericType;
/*      */ import org.eclipse.emf.ecore.EModelElement;
/*      */ import org.eclipse.emf.ecore.ENamedElement;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EOperation;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EParameter;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.ETypeParameter;
/*      */ import org.eclipse.emf.ecore.ETypedElement;
/*      */ import org.eclipse.emf.ecore.EcoreFactory;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*      */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EcoreValidator
/*      */   extends EObjectValidator
/*      */ {
/*   69 */   public static final EcoreValidator INSTANCE = new EcoreValidator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String STRICT_NAMED_ELEMENT_NAMES = "org.eclipse.emf.ecore.model.ENamedElement_WellFormedName";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.model";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AT_MOST_ONE_ID = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_ARGUMENTS_INCORRECT_NUMBER = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_ARGUMENTS_NONE = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_ARGUMENTS_NONE_ALLOWED = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_BOUNDS = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_BOUNDS_NOT_ALLOWED = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_BOUNDS_NO_BOUNDS_WITH_TYPE_PARAMETER_OR_CLASSIFIER = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_BOUNDS_NO_LOWER_AND_UPPER = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_KEYS = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_OPPOSITE_BAD_TRANSIENT = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_OPPOSITE_BOTH_CONTAINMENT = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_OPPOSITE_NOT_FROM_TYPE = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_OPPOSITE_NOT_MATCHING = 14;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_SUPER_TYPES_CONFLICT = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_SUPER_TYPES_DUPLICATE = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TRANSIENT = 17;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_CLASS_REQUIRED = 18;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_CLASS_NOT_PERMITTED = 19;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_DATA_TYPE_NOT_PERMITTED = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_NO_TYPE_PARAMETER_AND_CLASSIFIER = 21;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_PRIMITIVE_TYPE_NOT_PERMITTED = 22;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_TYPE_PARAMETER_NOT_IN_SCOPE = 23;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_TYPE_WILDCARD_NOT_PERMITTED = 24;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTERFACE_IS_ABSTRACT = 25;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int NO_CIRCULAR_SUPER_TYPES = 26;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int NO_REPEATING_VOID = 27;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int SINGLE_CONTAINER = 28;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_CLASSIFIER_NAMES = 29;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_ENUMERATOR_LITERALS = 30;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_ENUMERATOR_NAMES = 31;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_FEATURE_NAMES = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_NS_URIS = 33;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_OPERATION_SIGNATURES = 34;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_PARAMETER_NAMES = 35;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_SUBPACKAGE_NAMES = 36;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNIQUE_TYPE_PARAMETER_NAMES = 37;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VALID_DEFAULT_VALUE_LITERAL = 38;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VALID_LOWER_BOUND = 39;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VALID_TYPE = 40;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VALID_UPPER_BOUND = 41;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_INSTANCE_TYPE_NAME = 42;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_MAP_ENTRY_CLASS = 43;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_NAME = 44;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_NS_PREFIX = 45;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_NS_URI = 46;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_SOURCE_URI = 47;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DISJOINT_FEATURE_AND_OPERATION_SIGNATURES = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_MAP_ENTRY_NO_INSTANCE_CLASS_NAME = 49;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_UNIQUE = 50;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CONSISTENT_CONTAINER = 51;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int DIAGNOSTIC_CODE_COUNT = 51;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  381 */   protected XMLTypeValidator xmlTypeValidator = XMLTypeValidator.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EPackage getEPackage() {
/*  393 */     return (EPackage)EcorePackage.eINSTANCE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  405 */     switch (classifierID) {
/*      */       
/*      */       case 0:
/*  408 */         return validateEAttribute((EAttribute)value, diagnostics, context);
/*      */       case 1:
/*  410 */         return validateEAnnotation((EAnnotation)value, diagnostics, context);
/*      */       case 2:
/*  412 */         return validateEClass((EClass)value, diagnostics, context);
/*      */       case 3:
/*  414 */         return validateEClassifier((EClassifier)value, diagnostics, context);
/*      */       case 4:
/*  416 */         return validateEDataType((EDataType)value, diagnostics, context);
/*      */       case 5:
/*  418 */         return validateEEnum((EEnum)value, diagnostics, context);
/*      */       case 6:
/*  420 */         return validateEEnumLiteral((EEnumLiteral)value, diagnostics, context);
/*      */       case 7:
/*  422 */         return validateEFactory((EFactory)value, diagnostics, context);
/*      */       case 8:
/*  424 */         return validateEModelElement((EModelElement)value, diagnostics, context);
/*      */       case 9:
/*  426 */         return validateENamedElement((ENamedElement)value, diagnostics, context);
/*      */       case 10:
/*  428 */         return validateEObject((EObject)value, diagnostics, context);
/*      */       case 11:
/*  430 */         return validateEOperation((EOperation)value, diagnostics, context);
/*      */       case 12:
/*  432 */         return validateEPackage((EPackage)value, diagnostics, context);
/*      */       case 13:
/*  434 */         return validateEParameter((EParameter)value, diagnostics, context);
/*      */       case 14:
/*  436 */         return validateEReference((EReference)value, diagnostics, context);
/*      */       case 15:
/*  438 */         return validateEStructuralFeature((EStructuralFeature)value, diagnostics, context);
/*      */       case 16:
/*  440 */         return validateETypedElement((ETypedElement)value, diagnostics, context);
/*      */       case 17:
/*  442 */         return validateEStringToStringMapEntry((Map.Entry<?, ?>)value, diagnostics, context);
/*      */       case 18:
/*  444 */         return validateEGenericType((EGenericType)value, diagnostics, context);
/*      */       case 19:
/*  446 */         return validateETypeParameter((ETypeParameter)value, diagnostics, context);
/*      */       case 20:
/*  448 */         return validateEBigDecimal((BigDecimal)value, diagnostics, context);
/*      */       case 21:
/*  450 */         return validateEBigInteger((BigInteger)value, diagnostics, context);
/*      */       case 22:
/*  452 */         return validateEBoolean(((Boolean)value).booleanValue(), diagnostics, context);
/*      */       case 23:
/*  454 */         return validateEBooleanObject((Boolean)value, diagnostics, context);
/*      */       case 24:
/*  456 */         return validateEByte(((Byte)value).byteValue(), diagnostics, context);
/*      */       case 25:
/*  458 */         return validateEByteArray((byte[])value, diagnostics, context);
/*      */       case 26:
/*  460 */         return validateEByteObject((Byte)value, diagnostics, context);
/*      */       case 27:
/*  462 */         return validateEChar(((Character)value).charValue(), diagnostics, context);
/*      */       case 28:
/*  464 */         return validateECharacterObject((Character)value, diagnostics, context);
/*      */       case 29:
/*  466 */         return validateEDate((Date)value, diagnostics, context);
/*      */       case 30:
/*  468 */         return validateEDiagnosticChain((DiagnosticChain)value, diagnostics, context);
/*      */       case 31:
/*  470 */         return validateEDouble(((Double)value).doubleValue(), diagnostics, context);
/*      */       case 32:
/*  472 */         return validateEDoubleObject((Double)value, diagnostics, context);
/*      */       case 33:
/*  474 */         return validateEEList((EList)value, diagnostics, context);
/*      */       case 34:
/*  476 */         return validateEEnumerator((Enumerator)value, diagnostics, context);
/*      */       case 35:
/*  478 */         return validateEFeatureMap((FeatureMap)value, diagnostics, context);
/*      */       case 36:
/*  480 */         return validateEFeatureMapEntry((FeatureMap.Entry)value, diagnostics, context);
/*      */       case 37:
/*  482 */         return validateEFloat(((Float)value).floatValue(), diagnostics, context);
/*      */       case 38:
/*  484 */         return validateEFloatObject((Float)value, diagnostics, context);
/*      */       case 39:
/*  486 */         return validateEInt(((Integer)value).intValue(), diagnostics, context);
/*      */       case 40:
/*  488 */         return validateEIntegerObject((Integer)value, diagnostics, context);
/*      */       case 41:
/*  490 */         return validateEJavaClass((Class)value, diagnostics, context);
/*      */       case 42:
/*  492 */         return validateEJavaObject(value, diagnostics, context);
/*      */       case 43:
/*  494 */         return validateELong(((Long)value).longValue(), diagnostics, context);
/*      */       case 44:
/*  496 */         return validateELongObject((Long)value, diagnostics, context);
/*      */       case 45:
/*  498 */         return validateEMap((Map<?, ?>)value, diagnostics, context);
/*      */       case 46:
/*  500 */         return validateEResource((Resource)value, diagnostics, context);
/*      */       case 47:
/*  502 */         return validateEResourceSet((ResourceSet)value, diagnostics, context);
/*      */       case 48:
/*  504 */         return validateEShort(((Short)value).shortValue(), diagnostics, context);
/*      */       case 49:
/*  506 */         return validateEShortObject((Short)value, diagnostics, context);
/*      */       case 50:
/*  508 */         return validateEString((String)value, diagnostics, context);
/*      */       case 51:
/*  510 */         return validateETreeIterator((TreeIterator)value, diagnostics, context);
/*      */       case 52:
/*  512 */         return validateEInvocationTargetException((InvocationTargetException)value, diagnostics, context);
/*      */     } 
/*  514 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEAttribute(EAttribute eAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  525 */     if (!validate_NoCircularContainment((EObject)eAttribute, diagnostics, context)) return false; 
/*  526 */     boolean result = validate_EveryMultiplicityConforms((EObject)eAttribute, diagnostics, context);
/*  527 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eAttribute, diagnostics, context); 
/*  528 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eAttribute, diagnostics, context); 
/*  529 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eAttribute, diagnostics, context); 
/*  530 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eAttribute, diagnostics, context); 
/*  531 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eAttribute, diagnostics, context); 
/*  532 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eAttribute, diagnostics, context); 
/*  533 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eAttribute, diagnostics, context); 
/*  534 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eAttribute, diagnostics, context); 
/*  535 */     if (result || diagnostics != null) result &= validateETypedElement_ValidLowerBound((ETypedElement)eAttribute, diagnostics, context); 
/*  536 */     if (result || diagnostics != null) result &= validateETypedElement_ValidUpperBound((ETypedElement)eAttribute, diagnostics, context); 
/*  537 */     if (result || diagnostics != null) result &= validateETypedElement_ConsistentBounds((ETypedElement)eAttribute, diagnostics, context); 
/*  538 */     if (result || diagnostics != null) result &= validateETypedElement_ValidType((ETypedElement)eAttribute, diagnostics, context); 
/*  539 */     if (result || diagnostics != null) result &= validateEStructuralFeature_ValidDefaultValueLiteral((EStructuralFeature)eAttribute, diagnostics, context); 
/*  540 */     if (result || diagnostics != null) result &= validateEAttribute_ConsistentTransient(eAttribute, diagnostics, context); 
/*  541 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEAttribute_ConsistentTransient(EAttribute eAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  552 */     EDataType eAttributeType = eAttribute.getEAttributeType();
/*  553 */     boolean result = 
/*  554 */       !(!isEffectivelyTransient((EStructuralFeature)eAttribute) && 
/*  555 */       eAttributeType != null && 
/*  556 */       !eAttributeType.isSerializable() && 
/*  557 */       !FeatureMapUtil.isFeatureMapEntry((EClassifier)eAttributeType));
/*  558 */     if (!result && diagnostics != null) {
/*      */       
/*  560 */       String attributeName = eAttribute.getName();
/*  561 */       if (eAttribute.getEContainingClass() != null)
/*      */       {
/*  563 */         attributeName = String.valueOf(eAttribute.getEContainingClass().getName()) + "." + attributeName;
/*      */       }
/*  565 */       diagnostics.add(
/*  566 */           (Diagnostic)createDiagnostic(
/*  567 */             4, 
/*  568 */             "org.eclipse.emf.ecore.model", 
/*  569 */             17, 
/*  570 */             "_UI_EAttributeConsistentTransient_diagnostic", 
/*  571 */             (Object[])new String[] { attributeName
/*  572 */             }, new Object[] { eAttribute
/*  573 */             }, context));
/*      */     } 
/*  575 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isEffectivelyTransient(EStructuralFeature eStructuralFeature) {
/*  580 */     if (eStructuralFeature.isTransient()) {
/*      */       
/*  582 */       EStructuralFeature group = ExtendedMetaData.INSTANCE.getGroup(eStructuralFeature);
/*  583 */       return !(group != null && !isEffectivelyTransient(group));
/*      */     } 
/*      */ 
/*      */     
/*  587 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEAnnotation(EAnnotation eAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  598 */     if (!validate_NoCircularContainment((EObject)eAnnotation, diagnostics, context)) return false; 
/*  599 */     boolean result = validate_EveryMultiplicityConforms((EObject)eAnnotation, diagnostics, context);
/*  600 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eAnnotation, diagnostics, context); 
/*  601 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eAnnotation, diagnostics, context); 
/*  602 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eAnnotation, diagnostics, context); 
/*  603 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eAnnotation, diagnostics, context); 
/*  604 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eAnnotation, diagnostics, context); 
/*  605 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eAnnotation, diagnostics, context); 
/*  606 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eAnnotation, diagnostics, context); 
/*  607 */     if (result || diagnostics != null) result &= validateEAnnotation_WellFormedSourceURI(eAnnotation, diagnostics, context); 
/*  608 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEAnnotation_WellFormedSourceURI(EAnnotation eAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  620 */     String source = eAnnotation.getSource();
/*  621 */     boolean result = !(source != null && !isWellFormedURI(source));
/*  622 */     if (!result && diagnostics != null)
/*      */     {
/*  624 */       diagnostics.add(
/*  625 */           (Diagnostic)createDiagnostic(
/*  626 */             4, 
/*  627 */             "org.eclipse.emf.ecore.model", 
/*  628 */             47, 
/*  629 */             "_UI_EAnnotationSourceURINotWellFormed_diagnostic", 
/*  630 */             new Object[] { source
/*  631 */             }, new Object[] { eAnnotation
/*  632 */             }, context));
/*      */     }
/*  634 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean isWellFormedURI(String uri) {
/*      */     try {
/*  651 */       return (uri != null && uri.length() != 0 && uri.equals(URI.createURI(uri, true).toString()));
/*      */     }
/*  653 */     catch (Throwable exception) {
/*      */       
/*  655 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  666 */     if (!validate_NoCircularContainment((EObject)eClass, diagnostics, context)) return false; 
/*  667 */     boolean result = validate_EveryMultiplicityConforms((EObject)eClass, diagnostics, context);
/*  668 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eClass, diagnostics, context); 
/*  669 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eClass, diagnostics, context); 
/*  670 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eClass, diagnostics, context); 
/*  671 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eClass, diagnostics, context); 
/*  672 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eClass, diagnostics, context); 
/*  673 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eClass, diagnostics, context); 
/*  674 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eClass, diagnostics, context); 
/*  675 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eClass, diagnostics, context); 
/*  676 */     if (result || diagnostics != null) result &= validateEClassifier_WellFormedInstanceTypeName((EClassifier)eClass, diagnostics, context); 
/*  677 */     if (result || diagnostics != null) result &= validateEClassifier_UniqueTypeParameterNames((EClassifier)eClass, diagnostics, context); 
/*  678 */     if (result || diagnostics != null) result &= validateEClass_InterfaceIsAbstract(eClass, diagnostics, context); 
/*  679 */     if (result || diagnostics != null) result &= validateEClass_AtMostOneID(eClass, diagnostics, context); 
/*  680 */     if (result || diagnostics != null) result &= validateEClass_UniqueFeatureNames(eClass, diagnostics, context); 
/*  681 */     if (result || diagnostics != null) result &= validateEClass_UniqueOperationSignatures(eClass, diagnostics, context); 
/*  682 */     if (result || diagnostics != null) result &= validateEClass_NoCircularSuperTypes(eClass, diagnostics, context); 
/*  683 */     if (result || diagnostics != null) result &= validateEClass_WellFormedMapEntryClass(eClass, diagnostics, context); 
/*  684 */     if (result || diagnostics != null) result &= validateEClass_ConsistentSuperTypes(eClass, diagnostics, context); 
/*  685 */     if (result || diagnostics != null) result &= validateEClass_DisjointFeatureAndOperationSignatures(eClass, diagnostics, context); 
/*  686 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_InterfaceIsAbstract(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  698 */     boolean result = !(eClass.isInterface() && !eClass.isAbstract());
/*  699 */     if (!result && diagnostics != null)
/*      */     {
/*  701 */       diagnostics.add(
/*  702 */           (Diagnostic)createDiagnostic(
/*  703 */             4, 
/*  704 */             "org.eclipse.emf.ecore.model", 
/*  705 */             25, 
/*  706 */             "_UI_EClassInterfaceNotAbstract_diagnostic", 
/*  707 */             null, 
/*  708 */             new Object[] { eClass
/*  709 */             }, context));
/*      */     }
/*  711 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_AtMostOneID(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  723 */     boolean result = true;
/*  724 */     EAttribute eIDAttribute = eClass.getEIDAttribute();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  730 */     if (eIDAttribute != null && !ExtendedMetaData.INSTANCE.isDocumentRoot(eClass))
/*      */     {
/*      */       
/*  733 */       label26: for (EAttribute eAttribute : eClass.getEAllAttributes()) {
/*      */         
/*  735 */         if (eAttribute.isID() && eIDAttribute != eAttribute) {
/*      */           
/*  737 */           result = false;
/*  738 */           if (diagnostics == null) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  747 */           for (EClass eSuperType : eClass.getESuperTypes()) {
/*      */             
/*  749 */             EList<EStructuralFeature> eAllStructuralFeatures = eSuperType.getEAllStructuralFeatures();
/*  750 */             if (eAllStructuralFeatures.contains(eIDAttribute) && eAllStructuralFeatures.contains(eAttribute)) {
/*      */               continue label26;
/*      */             }
/*      */           } 
/*      */           
/*  755 */           diagnostics.add(
/*  756 */               (Diagnostic)createDiagnostic(
/*  757 */                 4, 
/*  758 */                 "org.eclipse.emf.ecore.model", 
/*  759 */                 1, 
/*  760 */                 "_UI_EClassAtMostOneID_diagnostic", 
/*  761 */                 new Object[] { getFeatureLabel((EStructuralFeature)eIDAttribute, context), getFeatureLabel((EStructuralFeature)eAttribute, context)
/*  762 */                 }new Object[] { eClass, eAttribute, eIDAttribute
/*  763 */                 }, context));
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  768 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_UniqueFeatureNames(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  782 */     boolean result = true;
/*  783 */     int size = eClass.getFeatureCount();
/*  784 */     if (size > 0) {
/*      */ 
/*      */ 
/*      */       
/*  788 */       int start = 0;
/*  789 */       EList<EClass> eSuperTypes = eClass.getESuperTypes();
/*  790 */       if (!eSuperTypes.isEmpty())
/*      */       {
/*  792 */         start = ((EClass)eSuperTypes.get(0)).getEAllStructuralFeatures().size();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  797 */       ArrayList<String> keys = new ArrayList<String>();
/*      */       
/*  799 */       for (int i = 0; i < size; i++) {
/*      */         
/*  801 */         EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/*  802 */         String name = eStructuralFeature.getName();
/*  803 */         if (name == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  808 */           keys.add(null);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  814 */           String key = name.replace("_", "").toLowerCase();
/*      */ 
/*      */ 
/*      */           
/*  818 */           if (i >= start) {
/*      */             
/*  820 */             int index = keys.indexOf(key);
/*  821 */             if (index != -1) {
/*      */               
/*  823 */               if (diagnostics == null)
/*      */               {
/*  825 */                 return false;
/*      */               }
/*      */ 
/*      */               
/*  829 */               result = false;
/*      */               
/*  831 */               EStructuralFeature otherEStructuralFeature = eClass.getEStructuralFeature(index);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  836 */               for (EClass eSuperType : eSuperTypes) {
/*      */                 
/*  838 */                 EList<EStructuralFeature> eAllStructuralFeatures = eSuperType.getEAllStructuralFeatures();
/*  839 */                 if (eAllStructuralFeatures.contains(eStructuralFeature) && eAllStructuralFeatures.contains(otherEStructuralFeature)) {
/*      */                   // Byte code: goto -> 356
/*      */                 }
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  847 */               String otherName = otherEStructuralFeature.getName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  854 */               (new Object[1])[0] = name; (new Object[2])[0] = name; (new Object[2])[1] = otherName; diagnostics.add((Diagnostic)createDiagnostic(name.equals(otherName) ? 4 : 2, "org.eclipse.emf.ecore.model", 32, name.equals(otherName) ? "_UI_EClassUniqueEStructuralFeatureName_diagnostic" : "_UI_EClassDissimilarEStructuralFeatureName_diagnostic", name.equals(otherName) ? new Object[1] : new Object[2], 
/*  855 */                     new Object[] { eClass, eStructuralFeature, otherEStructuralFeature
/*  856 */                     }, context));
/*      */             } 
/*      */           } 
/*      */           
/*  860 */           keys.add(key);
/*      */         } 
/*      */       } 
/*      */     } 
/*  864 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_UniqueOperationSignatures(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  879 */     return this.uniqueOperationSignaturesValidator.validateEOperationSignatures(eClass, eClass.getEOperations(), (Collection<EOperation>)eClass.getEOperations(), diagnostics, context);
/*      */   }
/*      */ 
/*      */   
/*      */   private class EOperationSignatureValidator
/*      */   {
/*      */     protected String messageKey;
/*      */     protected int messageCode;
/*      */     protected boolean ignoreOperationsWithSuppressedVisibility;
/*      */     
/*      */     public EOperationSignatureValidator(String messageKey, int messageCode) {
/*  890 */       this.messageKey = messageKey;
/*  891 */       this.messageCode = messageCode;
/*      */     }
/*      */ 
/*      */     
/*      */     public EOperationSignatureValidator(String messageKey, int messageCode, boolean ignoreOperationsWithSuppressedVisibility) {
/*  896 */       this.messageKey = messageKey;
/*  897 */       this.messageCode = messageCode;
/*  898 */       this.ignoreOperationsWithSuppressedVisibility = ignoreOperationsWithSuppressedVisibility;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final boolean validateEOperationSignatures(EClass eClass, EList<EOperation> eOperations, Collection<EOperation> otherEOperations, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  904 */       boolean result = true;
/*  905 */       for (EOperation eOperation : eOperations) {
/*      */         
/*  907 */         if (!this.ignoreOperationsWithSuppressedVisibility || !EcoreUtil.isSuppressedVisibility(eOperation)) {
/*      */           
/*  909 */           String name = eOperation.getName();
/*  910 */           if (name != null) {
/*      */             
/*  912 */             EList<EParameter> eParameters = eOperation.getEParameters();
/*  913 */             int eParameterSize = eParameters.size();
/*      */             
/*  915 */             label47: for (EOperation otherEOperation : otherEOperations) {
/*      */ 
/*      */ 
/*      */               
/*  919 */               if (otherEOperation == eOperation) {
/*      */                 break;
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  925 */               String otherName = otherEOperation.getName();
/*  926 */               if (name.equals(otherName)) {
/*      */                 
/*  928 */                 EList<EParameter> otherEParmeters = otherEOperation.getEParameters();
/*  929 */                 if (otherEParmeters.size() == eParameterSize) {
/*      */                   
/*  931 */                   for (int i = 0; i < eParameterSize; i++) {
/*      */                     
/*  933 */                     EParameter eParameter = (EParameter)eParameters.get(i);
/*  934 */                     EParameter otherEParameter = (EParameter)otherEParmeters.get(i);
/*  935 */                     EClassifier eType = eParameter.getEType();
/*  936 */                     EClassifier otherEType = otherEParameter.getEType();
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*  941 */                     if (eType != otherEType) {
/*      */                       
/*  943 */                       if (eType != null) { if (otherEType != null)
/*      */                         
/*  945 */                         { String instanceClassName = eType.getInstanceClassName();
/*  946 */                           String otherInstanceClassName = otherEType.getInstanceClassName();
/*  947 */                           if (instanceClassName == otherInstanceClassName) { if (instanceClassName != null) { if (eParameter.isMany() != otherEParameter.isMany())
/*      */                                 continue label47;  }
/*      */                             else { continue label47; }
/*      */                              }
/*      */                           else { continue label47; }
/*      */                            }
/*      */                         else { continue label47; }
/*      */                          }
/*      */                       else { continue label47; }
/*      */                     
/*  957 */                     } else if (eParameter.isMany() != otherEParameter.isMany()) {
/*      */                       continue label47;
/*      */                     } 
/*      */                   } 
/*      */                   
/*  962 */                   if (diagnostics == null)
/*      */                   {
/*  964 */                     return false;
/*      */                   }
/*      */ 
/*      */                   
/*  968 */                   result = false;
/*      */                   
/*  970 */                   diagnostics.add(
/*  971 */                       (Diagnostic)EcoreValidator.this.createDiagnostic(
/*  972 */                         4, 
/*  973 */                         "org.eclipse.emf.ecore.model", 
/*  974 */                         this.messageCode, 
/*  975 */                         this.messageKey, 
/*  976 */                         new Object[] { EcoreValidator.getObjectLabel((EObject)eOperation, context), EcoreValidator.getObjectLabel((EObject)getTarget(otherEOperation), context)
/*  977 */                         }new Object[] { eClass, eOperation, getTarget(otherEOperation)
/*  978 */                         }, context));
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  987 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     protected EModelElement getTarget(EOperation targetEOperation) {
/*  992 */       return (EModelElement)targetEOperation;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*  997 */   private final EOperationSignatureValidator uniqueOperationSignaturesValidator = new EOperationSignatureValidator("_UI_EClassUniqueEOperationSignatures_diagnostic", 34);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean validateEClass_UniqueOperationSignatures2(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1015 */     boolean result = true;
/* 1016 */     EList<EOperation> eAllOperations = eClass.getEAllOperations();
/* 1017 */     if (!eAllOperations.isEmpty()) {
/*      */       List list;
/*      */ 
/*      */       
/* 1021 */       EList<EOperation> eList = eAllOperations;
/* 1022 */       EList<EClass> eSuperTypes = eClass.getESuperTypes();
/* 1023 */       if (!eSuperTypes.isEmpty())
/*      */       {
/* 1025 */         list = eAllOperations.subList(((EClass)eSuperTypes.get(0)).getEAllOperations().size(), eAllOperations.size());
/*      */       }
/* 1027 */       for (EOperation eOperation : list) {
/*      */         
/* 1029 */         String name = eOperation.getName();
/* 1030 */         if (name != null) {
/*      */           
/* 1032 */           EList<EParameter> eParameters = eOperation.getEParameters();
/* 1033 */           int eParameterSize = eParameters.size();
/*      */           
/* 1035 */           label51: for (EOperation otherEOperation : eAllOperations) {
/*      */ 
/*      */ 
/*      */             
/* 1039 */             if (otherEOperation == eOperation) {
/*      */               break;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1045 */             String otherName = otherEOperation.getName();
/* 1046 */             if (name.equals(otherName)) {
/*      */               
/* 1048 */               EList<EParameter> otherEParmeters = otherEOperation.getEParameters();
/* 1049 */               if (otherEParmeters.size() == eParameterSize) {
/*      */                 
/* 1051 */                 for (int i = 0; i < eParameterSize; i++) {
/*      */                   
/* 1053 */                   EParameter eParameter = (EParameter)eParameters.get(i);
/* 1054 */                   EParameter otherEParameter = (EParameter)otherEParmeters.get(i);
/* 1055 */                   EClassifier eType = eParameter.getEType();
/* 1056 */                   EClassifier otherEType = otherEParameter.getEType();
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1061 */                   if (eType != otherEType)
/*      */                   {
/* 1063 */                     if (eType != null && otherEType != null) {
/*      */                       
/* 1065 */                       String instanceClassName = eType.getInstanceClassName();
/* 1066 */                       String otherInstanceClassName = otherEType.getInstanceClassName();
/* 1067 */                       if (instanceClassName != otherInstanceClassName && instanceClassName != null && otherInstanceClassName != null) {
/*      */                         continue label51;
/*      */                       }
/*      */                     } 
/*      */                   }
/*      */                 } 
/*      */                 
/* 1074 */                 if (diagnostics == null)
/*      */                 {
/* 1076 */                   return false;
/*      */                 }
/*      */ 
/*      */                 
/* 1080 */                 result = false;
/*      */ 
/*      */ 
/*      */                 
/* 1084 */                 for (EClass eSuperType : eClass.getEAllSuperTypes()) {
/*      */                   
/* 1086 */                   EList<EOperation> superTypeEAllOperations = eSuperType.getEAllOperations();
/* 1087 */                   if (superTypeEAllOperations.contains(eOperation) && superTypeEAllOperations.contains(otherEOperation)) {
/*      */                     continue label51;
/*      */                   }
/*      */                 } 
/*      */ 
/*      */                 
/* 1093 */                 diagnostics.add(
/* 1094 */                     (Diagnostic)createDiagnostic(
/* 1095 */                       4, 
/* 1096 */                       "org.eclipse.emf.ecore.model", 
/* 1097 */                       34, 
/* 1098 */                       "_UI_EClassUniqueEOperationSignatures_diagnostic", 
/* 1099 */                       new Object[] { getObjectLabel((EObject)eOperation, context), getObjectLabel((EObject)otherEOperation, context)
/* 1100 */                       }new Object[] { eClass, eOperation, otherEOperation
/* 1101 */                       }, context));
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1110 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_NoCircularSuperTypes(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1122 */     EList<EClass> eAllSuperTypes = eClass.getEAllSuperTypes();
/* 1123 */     boolean result = !eAllSuperTypes.contains(eClass);
/* 1124 */     if (result)
/*      */     {
/* 1126 */       for (EClass otherEClass : eAllSuperTypes) {
/*      */         
/* 1128 */         if (otherEClass.getEAllSuperTypes().contains(eClass)) {
/*      */           
/* 1130 */           result = false;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/* 1135 */     if (!result && diagnostics != null)
/*      */     {
/* 1137 */       diagnostics.add(
/* 1138 */           (Diagnostic)createDiagnostic(
/* 1139 */             4, 
/* 1140 */             "org.eclipse.emf.ecore.model", 
/* 1141 */             26, 
/* 1142 */             "_UI_EClassNoCircularSuperTypes_diagnostic", 
/* 1143 */             null, 
/* 1144 */             new Object[] { eClass
/* 1145 */             }, context));
/*      */     }
/* 1147 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_WellFormedMapEntryClass(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1159 */     boolean result = true;
/* 1160 */     if (eClass.getInstanceClassName() == "java.util.Map$Entry") {
/*      */       
/* 1162 */       EStructuralFeature keyFeature = eClass.getEStructuralFeature("key");
/* 1163 */       if (keyFeature == null) {
/*      */         
/* 1165 */         if (diagnostics == null)
/*      */         {
/* 1167 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 1171 */         result = false;
/* 1172 */         diagnostics.add(
/* 1173 */             (Diagnostic)createDiagnostic(
/* 1174 */               4, 
/* 1175 */               "org.eclipse.emf.ecore.model", 
/* 1176 */               43, 
/* 1177 */               "_UI_EClassNotWellFormedMapEntry_diagnostic", 
/* 1178 */               new Object[] { "key"
/* 1179 */               }, new Object[] { eClass
/* 1180 */               }, context));
/*      */       } 
/*      */       
/* 1183 */       EStructuralFeature valueFeature = eClass.getEStructuralFeature("value");
/* 1184 */       if (valueFeature == null)
/*      */       {
/* 1186 */         if (diagnostics == null)
/*      */         {
/* 1188 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 1192 */         result = false;
/* 1193 */         diagnostics.add(
/* 1194 */             (Diagnostic)createDiagnostic(
/* 1195 */               4, 
/* 1196 */               "org.eclipse.emf.ecore.model", 
/* 1197 */               43, 
/* 1198 */               "_UI_EClassNotWellFormedMapEntry_diagnostic", 
/* 1199 */               new Object[] { "value"
/* 1200 */               }, new Object[] { eClass
/* 1201 */               }, context));
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1207 */       for (EClass eSuperType : eClass.getEAllSuperTypes()) {
/*      */         
/* 1209 */         if (eSuperType.getInstanceClassName() == "java.util.Map$Entry") {
/*      */           
/* 1211 */           if (diagnostics == null)
/*      */           {
/* 1213 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1217 */           result = false;
/* 1218 */           diagnostics.add(
/* 1219 */               (Diagnostic)createDiagnostic(
/* 1220 */                 4, 
/* 1221 */                 "org.eclipse.emf.ecore.model", 
/* 1222 */                 49, 
/* 1223 */                 "_UI_EClassNotWellFormedMapEntryNoInstanceClassName_diagnostic", 
/* 1224 */                 null, 
/* 1225 */                 new Object[] { eClass
/* 1226 */                 }, context));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1231 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_ConsistentSuperTypes(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1245 */     boolean result = true;
/*      */ 
/*      */ 
/*      */     
/* 1249 */     ArrayList<EClassifier> superTypes = new ArrayList<EClassifier>();
/*      */ 
/*      */ 
/*      */     
/* 1253 */     EList<EGenericType> eGenericSuperTypes = eClass.getEGenericSuperTypes();
/* 1254 */     for (EGenericType eGenericSuperType : eGenericSuperTypes) {
/*      */ 
/*      */ 
/*      */       
/* 1258 */       EClassifier eClassifier = eGenericSuperType.getEClassifier();
/* 1259 */       if (eClassifier instanceof EClass) {
/*      */         
/* 1261 */         int index = superTypes.indexOf(eClassifier);
/* 1262 */         if (index != -1) {
/*      */           
/* 1264 */           if (diagnostics == null)
/*      */           {
/* 1266 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1270 */           result = false;
/* 1271 */           diagnostics.add(
/* 1272 */               (Diagnostic)createDiagnostic(
/* 1273 */                 4, 
/* 1274 */                 "org.eclipse.emf.ecore.model", 
/* 1275 */                 16, 
/* 1276 */                 "_UI_EClassNoDuplicateSuperTypes_diagnostic", 
/* 1277 */                 new Object[] { Integer.valueOf(eGenericSuperTypes.indexOf(eGenericSuperType)), Integer.valueOf(index)
/* 1278 */                 }, new Object[] { eClass, eGenericSuperType, eGenericSuperTypes.get(index)
/* 1279 */                 }, context));
/*      */         } 
/*      */       } 
/*      */       
/* 1283 */       superTypes.add(eClassifier);
/*      */     } 
/*      */     
/* 1286 */     if (result) {
/*      */       
/* 1288 */       superTypes.clear();
/* 1289 */       EList<EGenericType> eAllGenericSuperTypes = eClass.getEAllGenericSuperTypes();
/* 1290 */       for (EGenericType eGenericSuperType : eAllGenericSuperTypes) {
/*      */         
/* 1292 */         EClassifier eClassifier = eGenericSuperType.getEClassifier();
/* 1293 */         if (eClassifier instanceof EClass) {
/*      */           
/* 1295 */           int index = superTypes.indexOf(eClassifier);
/* 1296 */           if (index != -1) {
/*      */             
/* 1298 */             if (diagnostics == null)
/*      */             {
/* 1300 */               return false;
/*      */             }
/*      */ 
/*      */             
/* 1304 */             result = false;
/* 1305 */             diagnostics.add(
/* 1306 */                 (Diagnostic)createDiagnostic(
/* 1307 */                   4, 
/* 1308 */                   "org.eclipse.emf.ecore.model", 
/* 1309 */                   15, 
/* 1310 */                   "_UI_EClassConsistentSuperTypes_diagnostic", 
/* 1311 */                   new Object[] { getObjectLabel((EObject)eClassifier, context)
/* 1312 */                   }new Object[] { eClass, eGenericSuperType, eAllGenericSuperTypes.get(index)
/* 1313 */                   }, context));
/*      */           } 
/*      */         } 
/*      */         
/* 1317 */         superTypes.add(eClassifier);
/*      */       } 
/*      */     } 
/* 1320 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClass_DisjointFeatureAndOperationSignatures(EClass eClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1335 */     boolean result = true;
/* 1336 */     EList<EOperation> eOperations = eClass.getEOperations();
/* 1337 */     final Map<EOperation, EStructuralFeature> implicitEOperationToEStructuralFeatureMap = new LinkedHashMap<EOperation, EStructuralFeature>();
/* 1338 */     if (!eOperations.isEmpty()) {
/*      */       
/* 1340 */       for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
/*      */         
/* 1342 */         String featureName = eStructuralFeature.getName();
/* 1343 */         EClassifier eType = eStructuralFeature.getEType();
/* 1344 */         if (featureName != null && featureName.length() != 0 && eType != null) {
/*      */           
/* 1346 */           featureName = String.valueOf(featureName.substring(0, 1).toUpperCase()) + featureName.substring(1);
/* 1347 */           if (!EcoreUtil.isSuppressedVisibility(eStructuralFeature, 0)) {
/*      */             
/* 1349 */             String getAccessor = String.valueOf((eStructuralFeature.isMany() || !"boolean".equals(eType.getInstanceClassName())) ? "get" : "is") + featureName;
/* 1350 */             if ("getClass".equals(getAccessor))
/*      */             {
/* 1352 */               getAccessor = "getClass_";
/*      */             }
/* 1354 */             EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
/* 1355 */             eOperation.setName(getAccessor);
/* 1356 */             eOperation.setUpperBound(eStructuralFeature.getUpperBound());
/* 1357 */             eOperation.setOrdered(eStructuralFeature.isOrdered());
/* 1358 */             eOperation.setUnique(eStructuralFeature.isUnique());
/* 1359 */             eOperation.setEType(eType);
/* 1360 */             implicitEOperationToEStructuralFeatureMap.put(eOperation, eStructuralFeature);
/*      */           } 
/* 1362 */           if (!eStructuralFeature.isMany() && eStructuralFeature.isChangeable() && !EcoreUtil.isSuppressedVisibility(eStructuralFeature, 1)) {
/*      */             
/* 1364 */             String setAccessor = "set" + featureName;
/* 1365 */             EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
/* 1366 */             eOperation.setName(setAccessor);
/* 1367 */             EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
/* 1368 */             eParameter.setName(featureName);
/* 1369 */             eParameter.setEType(eType);
/* 1370 */             eOperation.getEParameters().add(eParameter);
/* 1371 */             implicitEOperationToEStructuralFeatureMap.put(eOperation, eStructuralFeature);
/*      */           } 
/* 1373 */           if (eStructuralFeature.isUnsettable()) {
/*      */             
/* 1375 */             if (!EcoreUtil.isSuppressedVisibility(eStructuralFeature, 2)) {
/*      */               
/* 1377 */               String isSetAccessor = "isSet" + featureName;
/* 1378 */               EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
/* 1379 */               eOperation.setName(isSetAccessor);
/* 1380 */               eOperation.setEType((EClassifier)EcorePackage.Literals.EBOOLEAN);
/* 1381 */               implicitEOperationToEStructuralFeatureMap.put(eOperation, eStructuralFeature);
/*      */             } 
/* 1383 */             if (!EcoreUtil.isSuppressedVisibility(eStructuralFeature, 3)) {
/*      */               
/* 1385 */               String unsetAccessor = "unset" + featureName;
/* 1386 */               EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
/* 1387 */               eOperation.setName(unsetAccessor);
/* 1388 */               implicitEOperationToEStructuralFeatureMap.put(eOperation, eStructuralFeature);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1394 */       result = (
/* 1395 */         new EOperationSignatureValidator("_UI_EClassDisjointFeatureAndOperationSignatures_diagnostic", 48, true)
/*      */         {
/*      */           
/*      */           protected EModelElement getTarget(EOperation otherEOperation)
/*      */           {
/* 1400 */             return (EModelElement)implicitEOperationToEStructuralFeatureMap.get(otherEOperation);
/*      */           }
/* 1402 */         }).validateEOperationSignatures(eClass, eOperations, implicitEOperationToEStructuralFeatureMap.keySet(), diagnostics, context);
/*      */     } 
/* 1404 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClassifier(EClassifier eClassifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1414 */     if (!validate_NoCircularContainment((EObject)eClassifier, diagnostics, context)) return false; 
/* 1415 */     boolean result = validate_EveryMultiplicityConforms((EObject)eClassifier, diagnostics, context);
/* 1416 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eClassifier, diagnostics, context); 
/* 1417 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eClassifier, diagnostics, context); 
/* 1418 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eClassifier, diagnostics, context); 
/* 1419 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eClassifier, diagnostics, context); 
/* 1420 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eClassifier, diagnostics, context); 
/* 1421 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eClassifier, diagnostics, context); 
/* 1422 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eClassifier, diagnostics, context); 
/* 1423 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eClassifier, diagnostics, context); 
/* 1424 */     if (result || diagnostics != null) result &= validateEClassifier_WellFormedInstanceTypeName(eClassifier, diagnostics, context); 
/* 1425 */     if (result || diagnostics != null) result &= validateEClassifier_UniqueTypeParameterNames(eClassifier, diagnostics, context); 
/* 1426 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClassifier_WellFormedInstanceTypeName(EClassifier eClassifier, DiagnosticChain diagnostics, final Map<Object, Object> context) {
/* 1439 */     String instanceTypeName = eClassifier.getInstanceTypeName();
/* 1440 */     Diagnostic typeBuilderDiagnostic = 
/* 1441 */       (instanceTypeName == null) ? 
/* 1442 */       null : (
/* 1443 */       new EGenericTypeBuilder()
/*      */       {
/*      */         
/*      */         protected BasicDiagnostic createDiagnostic(int severity, String source, int code, String messageKey, Object[] messageSubstitutions, Object[] data)
/*      */         {
/* 1448 */           return EcoreValidator.this.createDiagnostic(severity, source, code, messageKey, messageSubstitutions, data, context);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected ResourceLocator getResourceLocator() {
/* 1454 */           return EcoreValidator.this.getResourceLocator();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected String getString(String key, Object[] substitutions) {
/* 1460 */           return EcoreValidator.this.getString(key, substitutions);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         protected void report(DiagnosticChain diagnostics, String key, Object[] substitutions, int index) {
/* 1466 */           EcoreValidator.this.report(diagnostics, key, substitutions, index, context);
/*      */         }
/* 1468 */       }).parseInstanceTypeName(instanceTypeName);
/* 1469 */     String formattedName = null;
/* 1470 */     boolean result = 
/* 1471 */       (instanceTypeName != null) ? (
/* 1472 */       (typeBuilderDiagnostic.getSeverity() == 0 && 
/* 1473 */       instanceTypeName.equals(formattedName = EcoreUtil.toJavaInstanceTypeName(typeBuilderDiagnostic.getData().get(0))))) : (
/* 1474 */       !(!(eClassifier instanceof EClass) && !(eClassifier instanceof EEnum)));
/* 1475 */     if (!result && diagnostics != null) {
/*      */       
/* 1477 */       BasicDiagnostic diagnosic = 
/* 1478 */         createDiagnostic(
/* 1479 */           4, 
/* 1480 */           "org.eclipse.emf.ecore.model", 
/* 1481 */           42, 
/* 1482 */           "_UI_EClassifierInstanceTypeNameNotWellFormed_diagnostic", 
/* 1483 */           new Object[] { getValueLabel(EcorePackage.Literals.ESTRING, instanceTypeName, context)
/* 1484 */           }new Object[] { eClassifier
/* 1485 */           }, context);
/* 1486 */       if (typeBuilderDiagnostic != null)
/*      */       {
/* 1488 */         if (!typeBuilderDiagnostic.getChildren().isEmpty()) {
/*      */           
/* 1490 */           diagnosic.addAll(typeBuilderDiagnostic);
/*      */         }
/* 1492 */         else if (instanceTypeName != null && formattedName != null) {
/*      */ 
/*      */ 
/*      */           
/* 1496 */           int i = 0;
/* 1497 */           int length = Math.min(instanceTypeName.length(), formattedName.length());
/* 1498 */           for (; i < length; 
/* 1499 */             i = Character.offsetByCodePoints(instanceTypeName, i, 1)) {
/*      */             
/* 1501 */             if (instanceTypeName.codePointAt(i) != formattedName.codePointAt(i)) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 1507 */           diagnosic.add(
/* 1508 */               (Diagnostic)createDiagnostic(
/* 1509 */                 4, 
/* 1510 */                 "org.eclipse.emf.ecore.model", 
/* 1511 */                 42, 
/* 1512 */                 (instanceTypeName.codePointAt(i) == 32) ? "_UI_EClassifierInstanceTypeNameUnexpectedSpace_diagnostic" : "_UI_EClassifierInstanceTypeNameExpectedSpace_diagnostic", 
/* 1513 */                 new Object[] { Integer.valueOf(i)
/* 1514 */                 }, new Object[] { Integer.valueOf(i)
/* 1515 */                 }, context));
/*      */         } 
/*      */       }
/* 1518 */       diagnostics.add((Diagnostic)diagnosic);
/*      */     } 
/* 1520 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEClassifier_UniqueTypeParameterNames(EClassifier eClassifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1531 */     boolean result = true;
/* 1532 */     List<String> names = new ArrayList<String>();
/* 1533 */     EList<ETypeParameter> eTypeParameters = eClassifier.getETypeParameters();
/* 1534 */     for (ETypeParameter eTypeParameter : eTypeParameters) {
/*      */       
/* 1536 */       String name = eTypeParameter.getName();
/* 1537 */       if (name != null) {
/*      */         
/* 1539 */         int index = names.indexOf(name);
/* 1540 */         if (index != -1) {
/*      */           
/* 1542 */           if (diagnostics == null)
/*      */           {
/* 1544 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1548 */           result = false;
/* 1549 */           ETypeParameter otherETypeParameter = (ETypeParameter)eTypeParameters.get(index);
/* 1550 */           diagnostics.add(
/* 1551 */               (Diagnostic)createDiagnostic(
/* 1552 */                 4, 
/* 1553 */                 "org.eclipse.emf.ecore.model", 
/* 1554 */                 37, 
/* 1555 */                 "_UI_UniqueTypeParameterNames_diagnostic", 
/* 1556 */                 new Object[] { name
/* 1557 */                 }, new Object[] { eClassifier, eTypeParameter, otherETypeParameter
/* 1558 */                 }, context));
/*      */         } 
/*      */       } 
/*      */       
/* 1562 */       names.add(name);
/*      */     } 
/* 1564 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEDataType(EDataType eDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1574 */     if (!validate_NoCircularContainment((EObject)eDataType, diagnostics, context)) return false; 
/* 1575 */     boolean result = validate_EveryMultiplicityConforms((EObject)eDataType, diagnostics, context);
/* 1576 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eDataType, diagnostics, context); 
/* 1577 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eDataType, diagnostics, context); 
/* 1578 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eDataType, diagnostics, context); 
/* 1579 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eDataType, diagnostics, context); 
/* 1580 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eDataType, diagnostics, context); 
/* 1581 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eDataType, diagnostics, context); 
/* 1582 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eDataType, diagnostics, context); 
/* 1583 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eDataType, diagnostics, context); 
/* 1584 */     if (result || diagnostics != null) result &= validateEClassifier_WellFormedInstanceTypeName((EClassifier)eDataType, diagnostics, context); 
/* 1585 */     if (result || diagnostics != null) result &= validateEClassifier_UniqueTypeParameterNames((EClassifier)eDataType, diagnostics, context); 
/* 1586 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEEnum(EEnum eEnum, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1596 */     if (!validate_NoCircularContainment((EObject)eEnum, diagnostics, context)) return false; 
/* 1597 */     boolean result = validate_EveryMultiplicityConforms((EObject)eEnum, diagnostics, context);
/* 1598 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eEnum, diagnostics, context); 
/* 1599 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eEnum, diagnostics, context); 
/* 1600 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eEnum, diagnostics, context); 
/* 1601 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eEnum, diagnostics, context); 
/* 1602 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eEnum, diagnostics, context); 
/* 1603 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eEnum, diagnostics, context); 
/* 1604 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eEnum, diagnostics, context); 
/* 1605 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eEnum, diagnostics, context); 
/* 1606 */     if (result || diagnostics != null) result &= validateEClassifier_WellFormedInstanceTypeName((EClassifier)eEnum, diagnostics, context); 
/* 1607 */     if (result || diagnostics != null) result &= validateEClassifier_UniqueTypeParameterNames((EClassifier)eEnum, diagnostics, context); 
/* 1608 */     if (result || diagnostics != null) result &= validateEEnum_UniqueEnumeratorNames(eEnum, diagnostics, context); 
/* 1609 */     if (result || diagnostics != null) result &= validateEEnum_UniqueEnumeratorLiterals(eEnum, diagnostics, context); 
/* 1610 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEEnum_UniqueEnumeratorNames(EEnum eEnum, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1624 */     boolean result = true;
/* 1625 */     List<String> names = new ArrayList<String>();
/* 1626 */     EList<EEnumLiteral> eLiterals = eEnum.getELiterals();
/* 1627 */     for (EEnumLiteral eEnumLiteral : eLiterals) {
/*      */       
/* 1629 */       String name = eEnumLiteral.getName();
/* 1630 */       if (name == null) {
/*      */         
/* 1632 */         names.add(null);
/*      */         
/*      */         continue;
/*      */       } 
/* 1636 */       String key = name.replace("_", "").toUpperCase();
/* 1637 */       int index = names.indexOf(key);
/* 1638 */       if (index != -1) {
/*      */         
/* 1640 */         if (diagnostics == null)
/*      */         {
/* 1642 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 1646 */         result = false;
/* 1647 */         EEnumLiteral otherEEnumLiteral = (EEnumLiteral)eLiterals.get(index);
/* 1648 */         String otherName = otherEEnumLiteral.getName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1655 */         (new Object[1])[0] = name; (new Object[2])[0] = name; (new Object[2])[1] = otherName; diagnostics.add((Diagnostic)createDiagnostic(name.equals(otherName) ? 4 : 2, "org.eclipse.emf.ecore.model", 31, name.equals(otherName) ? "_UI_EEnumUniqueEnumeratorNames_diagnostic" : "_UI_EEnumDissimilarEnumeratorNames_diagnostic", name.equals(otherName) ? new Object[1] : new Object[2], 
/* 1656 */               new Object[] { eEnum, eEnumLiteral, otherEEnumLiteral
/* 1657 */               }, context));
/*      */       } 
/*      */       
/* 1660 */       names.add(key);
/*      */     } 
/*      */     
/* 1663 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEEnum_UniqueEnumeratorLiterals(EEnum eEnum, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1675 */     boolean result = true;
/* 1676 */     List<String> literals = new ArrayList<String>();
/* 1677 */     EList<EEnumLiteral> eLiterals = eEnum.getELiterals();
/* 1678 */     for (EEnumLiteral eEnumLiteral : eLiterals) {
/*      */       
/* 1680 */       String literal = eEnumLiteral.getLiteral();
/* 1681 */       if (literal != null) {
/*      */         
/* 1683 */         int index = literals.indexOf(literal);
/* 1684 */         if (index != -1) {
/*      */           
/* 1686 */           if (diagnostics == null)
/*      */           {
/* 1688 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1692 */           result = false;
/* 1693 */           EEnumLiteral otherEEnumLiteral = (EEnumLiteral)eLiterals.get(index);
/*      */ 
/*      */           
/* 1696 */           String name = eEnumLiteral.getName();
/* 1697 */           if (name == null || !name.equals(literal) || !name.equals(otherEEnumLiteral.getName()))
/*      */           {
/* 1699 */             diagnostics.add(
/* 1700 */                 (Diagnostic)createDiagnostic(
/* 1701 */                   4, 
/* 1702 */                   "org.eclipse.emf.ecore.model", 
/* 1703 */                   30, 
/* 1704 */                   "_UI_EEnumUniqueEnumeratorLiterals_diagnostic", 
/* 1705 */                   new Object[] { literal
/* 1706 */                   }, new Object[] { eEnum, eEnumLiteral, otherEEnumLiteral
/* 1707 */                   }, context));
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1712 */       literals.add(literal);
/*      */     } 
/* 1714 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEEnumLiteral(EEnumLiteral eEnumLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1724 */     if (!validate_NoCircularContainment((EObject)eEnumLiteral, diagnostics, context)) return false; 
/* 1725 */     boolean result = validate_EveryMultiplicityConforms((EObject)eEnumLiteral, diagnostics, context);
/* 1726 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eEnumLiteral, diagnostics, context); 
/* 1727 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eEnumLiteral, diagnostics, context); 
/* 1728 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eEnumLiteral, diagnostics, context); 
/* 1729 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eEnumLiteral, diagnostics, context); 
/* 1730 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eEnumLiteral, diagnostics, context); 
/* 1731 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eEnumLiteral, diagnostics, context); 
/* 1732 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eEnumLiteral, diagnostics, context); 
/* 1733 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eEnumLiteral, diagnostics, context); 
/* 1734 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEFactory(EFactory eFactory, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1744 */     return validate_EveryDefaultConstraint((EObject)eFactory, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEModelElement(EModelElement eModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1754 */     return validate_EveryDefaultConstraint((EObject)eModelElement, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENamedElement(ENamedElement eNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1764 */     if (!validate_NoCircularContainment((EObject)eNamedElement, diagnostics, context)) return false; 
/* 1765 */     boolean result = validate_EveryMultiplicityConforms((EObject)eNamedElement, diagnostics, context);
/* 1766 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eNamedElement, diagnostics, context); 
/* 1767 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eNamedElement, diagnostics, context); 
/* 1768 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eNamedElement, diagnostics, context); 
/* 1769 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eNamedElement, diagnostics, context); 
/* 1770 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eNamedElement, diagnostics, context); 
/* 1771 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eNamedElement, diagnostics, context); 
/* 1772 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eNamedElement, diagnostics, context); 
/* 1773 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName(eNamedElement, diagnostics, context); 
/* 1774 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENamedElement_WellFormedName(ENamedElement eNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1789 */     if (context != null && Boolean.FALSE.equals(context.get("org.eclipse.emf.ecore.model.ENamedElement_WellFormedName")))
/*      */     {
/* 1791 */       return true;
/*      */     }
/*      */     
/* 1794 */     boolean result = false;
/* 1795 */     String name = eNamedElement.getName();
/* 1796 */     if (name != null) {
/*      */       
/* 1798 */       int length = name.length();
/* 1799 */       if (length > 0 && Character.isJavaIdentifierStart(name.codePointAt(0))) {
/*      */         
/* 1801 */         result = true;
/* 1802 */         for (int i = Character.offsetByCodePoints(name, 0, 1); i < length; i = Character.offsetByCodePoints(name, i, 1)) {
/*      */           
/* 1804 */           if (!Character.isJavaIdentifierPart(name.codePointAt(i))) {
/*      */             
/* 1806 */             result = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1813 */     if (!result && diagnostics != null)
/*      */     {
/* 1815 */       diagnostics.add(
/* 1816 */           (Diagnostic)createDiagnostic(
/* 1817 */             4, 
/* 1818 */             "org.eclipse.emf.ecore.model", 
/* 1819 */             44, 
/* 1820 */             "_UI_ENamedElementNameNotWellFormed_diagnostic", 
/* 1821 */             new Object[] { getValueLabel(EcorePackage.Literals.ESTRING, name, context)
/* 1822 */             }new Object[] { eNamedElement
/* 1823 */             }, context));
/*      */     }
/* 1825 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEObject(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1835 */     return validate_EveryDefaultConstraint(eObject, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEOperation(EOperation eOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1845 */     if (!validate_NoCircularContainment((EObject)eOperation, diagnostics, context)) return false; 
/* 1846 */     boolean result = validate_EveryMultiplicityConforms((EObject)eOperation, diagnostics, context);
/* 1847 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eOperation, diagnostics, context); 
/* 1848 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eOperation, diagnostics, context); 
/* 1849 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eOperation, diagnostics, context); 
/* 1850 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eOperation, diagnostics, context); 
/* 1851 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eOperation, diagnostics, context); 
/* 1852 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eOperation, diagnostics, context); 
/* 1853 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eOperation, diagnostics, context); 
/* 1854 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eOperation, diagnostics, context); 
/* 1855 */     if (result || diagnostics != null) result &= validateETypedElement_ValidLowerBound((ETypedElement)eOperation, diagnostics, context); 
/* 1856 */     if (result || diagnostics != null) result &= validateETypedElement_ValidUpperBound((ETypedElement)eOperation, diagnostics, context); 
/* 1857 */     if (result || diagnostics != null) result &= validateETypedElement_ConsistentBounds((ETypedElement)eOperation, diagnostics, context); 
/* 1858 */     if (result || diagnostics != null) result &= validateETypedElement_ValidType((ETypedElement)eOperation, diagnostics, context); 
/* 1859 */     if (result || diagnostics != null) result &= validateEOperation_UniqueParameterNames(eOperation, diagnostics, context); 
/* 1860 */     if (result || diagnostics != null) result &= validateEOperation_UniqueTypeParameterNames(eOperation, diagnostics, context); 
/* 1861 */     if (result || diagnostics != null) result &= validateEOperation_NoRepeatingVoid(eOperation, diagnostics, context); 
/* 1862 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEOperation_UniqueParameterNames(EOperation eOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1874 */     boolean result = true;
/* 1875 */     List<String> names = new ArrayList<String>();
/* 1876 */     EList<EParameter> eParameters = eOperation.getEParameters();
/* 1877 */     for (EParameter eParameter : eParameters) {
/*      */       
/* 1879 */       String name = eParameter.getName();
/* 1880 */       if (name != null) {
/*      */         
/* 1882 */         int index = names.indexOf(name);
/* 1883 */         if (index != -1) {
/*      */           
/* 1885 */           if (diagnostics == null)
/*      */           {
/* 1887 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1891 */           result = false;
/* 1892 */           EParameter otherEParameter = (EParameter)eParameters.get(index);
/* 1893 */           diagnostics.add(
/* 1894 */               (Diagnostic)createDiagnostic(
/* 1895 */                 4, 
/* 1896 */                 "org.eclipse.emf.ecore.model", 
/* 1897 */                 35, 
/* 1898 */                 "_UI_EOperationUniqueParameterNames_diagnostic", 
/* 1899 */                 new Object[] { name
/* 1900 */                 }, new Object[] { eOperation, eParameter, otherEParameter
/* 1901 */                 }, context));
/*      */         } 
/*      */       } 
/*      */       
/* 1905 */       names.add(name);
/*      */     } 
/* 1907 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEOperation_UniqueTypeParameterNames(EOperation eOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1918 */     boolean result = true;
/* 1919 */     List<String> names = new ArrayList<String>();
/* 1920 */     EList<ETypeParameter> eTypeParameters = eOperation.getETypeParameters();
/* 1921 */     for (ETypeParameter eTypeParameter : eTypeParameters) {
/*      */       
/* 1923 */       String name = eTypeParameter.getName();
/* 1924 */       if (name != null) {
/*      */         
/* 1926 */         int index = names.indexOf(name);
/* 1927 */         if (index != -1) {
/*      */           
/* 1929 */           if (diagnostics == null)
/*      */           {
/* 1931 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1935 */           result = false;
/* 1936 */           ETypeParameter otherETypeParameter = (ETypeParameter)eTypeParameters.get(index);
/* 1937 */           diagnostics.add(
/* 1938 */               (Diagnostic)createDiagnostic(
/* 1939 */                 4, 
/* 1940 */                 "org.eclipse.emf.ecore.model", 
/* 1941 */                 37, 
/* 1942 */                 "_UI_UniqueTypeParameterNames_diagnostic", 
/* 1943 */                 new Object[] { name
/* 1944 */                 }, new Object[] { eOperation, eTypeParameter, otherETypeParameter
/* 1945 */                 }, context));
/*      */         } 
/*      */       } 
/*      */       
/* 1949 */       names.add(name);
/*      */     } 
/* 1951 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEOperation_NoRepeatingVoid(EOperation eOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1963 */     int upperBound = eOperation.getUpperBound();
/* 1964 */     boolean result = !(upperBound != 1 && eOperation.getEType() == null);
/* 1965 */     if (!result && diagnostics != null)
/*      */     {
/* 1967 */       diagnostics.add(
/* 1968 */           (Diagnostic)createDiagnostic(
/* 1969 */             4, 
/* 1970 */             "org.eclipse.emf.ecore.model", 
/* 1971 */             27, 
/* 1972 */             "_UI_EOperationNoRepeatingVoid_diagnostic", 
/* 1973 */             new Object[] { Integer.valueOf(upperBound)
/* 1974 */             }, new Object[] { eOperation
/* 1975 */             }, context));
/*      */     }
/* 1977 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEPackage(EPackage ePackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1987 */     if (!validate_NoCircularContainment((EObject)ePackage, diagnostics, context)) return false; 
/* 1988 */     boolean result = validate_EveryMultiplicityConforms((EObject)ePackage, diagnostics, context);
/* 1989 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)ePackage, diagnostics, context); 
/* 1990 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)ePackage, diagnostics, context); 
/* 1991 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)ePackage, diagnostics, context); 
/* 1992 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)ePackage, diagnostics, context); 
/* 1993 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)ePackage, diagnostics, context); 
/* 1994 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)ePackage, diagnostics, context); 
/* 1995 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)ePackage, diagnostics, context); 
/* 1996 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)ePackage, diagnostics, context); 
/* 1997 */     if (result || diagnostics != null) result &= validateEPackage_WellFormedNsURI(ePackage, diagnostics, context); 
/* 1998 */     if (result || diagnostics != null) result &= validateEPackage_WellFormedNsPrefix(ePackage, diagnostics, context); 
/* 1999 */     if (result || diagnostics != null) result &= validateEPackage_UniqueSubpackageNames(ePackage, diagnostics, context); 
/* 2000 */     if (result || diagnostics != null) result &= validateEPackage_UniqueClassifierNames(ePackage, diagnostics, context); 
/* 2001 */     if (result || diagnostics != null) result &= validateEPackage_UniqueNsURIs(ePackage, diagnostics, context); 
/* 2002 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEPackage_WellFormedNsURI(EPackage ePackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2014 */     String nsURI = ePackage.getNsURI();
/* 2015 */     boolean result = isWellFormedURI(nsURI);
/* 2016 */     if (!result && diagnostics != null)
/*      */     {
/* 2018 */       diagnostics.add(
/* 2019 */           (Diagnostic)createDiagnostic(
/* 2020 */             4, 
/* 2021 */             "org.eclipse.emf.ecore.model", 
/* 2022 */             46, 
/* 2023 */             "_UI_EPackageNsURINotWellFormed_diagnostic", 
/* 2024 */             new Object[] { nsURI
/* 2025 */             }, new Object[] { ePackage
/* 2026 */             }, context));
/*      */     }
/* 2028 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEPackage_WellFormedNsPrefix(EPackage ePackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2043 */     String nsPrefix = ePackage.getNsPrefix();
/*      */     
/* 2045 */     boolean result = !(!"".equals(nsPrefix) && (
/* 2046 */       nsPrefix == null || 
/* 2047 */       !XMLTypeValidator.INSTANCE.validateNCName(nsPrefix, null, context) || (
/* 2048 */       nsPrefix.toLowerCase().startsWith("xml") && !"http://www.w3.org/XML/1998/namespace".equals(ePackage.getNsURI()))));
/* 2049 */     if (!result && diagnostics != null)
/*      */     {
/* 2051 */       diagnostics.add(
/* 2052 */           (Diagnostic)createDiagnostic(
/* 2053 */             4, 
/* 2054 */             "org.eclipse.emf.ecore.model", 
/* 2055 */             45, 
/* 2056 */             "_UI_EPackageNsPrefixNotWellFormed_diagnostic", 
/* 2057 */             new Object[] { nsPrefix
/* 2058 */             }, new Object[] { ePackage
/* 2059 */             }, context));
/*      */     }
/* 2061 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEPackage_UniqueSubpackageNames(EPackage ePackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2073 */     boolean result = true;
/* 2074 */     List<String> names = new ArrayList<String>();
/* 2075 */     EList<EPackage> eSubpackages = ePackage.getESubpackages();
/* 2076 */     for (EPackage eSubpackage : eSubpackages) {
/*      */       
/* 2078 */       String name = eSubpackage.getName();
/* 2079 */       if (name != null) {
/*      */         
/* 2081 */         int index = names.indexOf(name);
/* 2082 */         if (index != -1) {
/*      */           
/* 2084 */           if (diagnostics == null)
/*      */           {
/* 2086 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 2090 */           result = false;
/* 2091 */           EPackage otherESubpackage = (EPackage)eSubpackages.get(index);
/* 2092 */           diagnostics.add(
/* 2093 */               (Diagnostic)createDiagnostic(
/* 2094 */                 4, 
/* 2095 */                 "org.eclipse.emf.ecore.model", 
/* 2096 */                 36, 
/* 2097 */                 "_UI_EPackageUniqueSubpackageNames_diagnostic", 
/* 2098 */                 new Object[] { name
/* 2099 */                 }, new Object[] { ePackage, eSubpackage, otherESubpackage
/* 2100 */                 }, context));
/*      */         } 
/*      */       } 
/*      */       
/* 2104 */       names.add(name);
/*      */     } 
/* 2106 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEPackage_UniqueClassifierNames(EPackage ePackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2120 */     boolean result = true;
/* 2121 */     List<String> names = new ArrayList<String>();
/* 2122 */     EList<EClassifier> eClassifiers = ePackage.getEClassifiers();
/* 2123 */     for (EClassifier eClassifier : eClassifiers) {
/*      */       
/* 2125 */       String name = eClassifier.getName();
/* 2126 */       if (name != null) {
/*      */         
/* 2128 */         String key = name.replace("_", "").toUpperCase();
/* 2129 */         int index = names.indexOf(key);
/* 2130 */         if (index != -1) {
/*      */           
/* 2132 */           if (diagnostics == null)
/*      */           {
/* 2134 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 2138 */           result = false;
/* 2139 */           EClassifier otherEClassifier = (EClassifier)eClassifiers.get(index);
/* 2140 */           String otherName = otherEClassifier.getName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2147 */           (new Object[1])[0] = name; (new Object[2])[0] = name; (new Object[2])[1] = otherName; diagnostics.add((Diagnostic)createDiagnostic(name.equals(otherName) ? 4 : 2, "org.eclipse.emf.ecore.model", 29, name.equals(otherName) ? "_UI_EPackageUniqueClassifierNames_diagnostic" : "_UI_EPackageDissimilarClassifierNames_diagnostic", name.equals(otherName) ? new Object[1] : new Object[2], 
/* 2148 */                 new Object[] { ePackage, eClassifier, otherEClassifier
/* 2149 */                 }, context));
/*      */         } 
/*      */         
/* 2152 */         names.add(key);
/*      */         
/*      */         continue;
/*      */       } 
/* 2156 */       names.add(null);
/*      */     } 
/*      */     
/* 2159 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEPackage_UniqueNsURIs(EPackage ePackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2170 */     boolean result = true;
/* 2171 */     String nsURI = ePackage.getNsURI();
/* 2172 */     if (nsURI != null) {
/*      */       
/* 2174 */       EPackage rootEPackage = ePackage;
/* 2175 */       for (EPackage eSuperPackage = ePackage.getESuperPackage(); eSuperPackage != null; eSuperPackage = eSuperPackage.getESuperPackage())
/*      */       {
/* 2177 */         rootEPackage = eSuperPackage;
/*      */       }
/*      */       
/* 2180 */       UniqueEList.FastCompare fastCompare = new UniqueEList.FastCompare();
/* 2181 */       fastCompare.add(rootEPackage);
/* 2182 */       for (int i = 0; i < fastCompare.size(); i++)
/*      */       {
/* 2184 */         fastCompare.addAll((Collection)((EPackage)fastCompare.get(i)).getESubpackages());
/*      */       }
/* 2186 */       fastCompare.remove(ePackage);
/*      */       
/* 2188 */       for (EPackage otherEPackage : fastCompare) {
/*      */         
/* 2190 */         if (nsURI.equals(otherEPackage.getNsURI())) {
/*      */           
/* 2192 */           if (diagnostics == null)
/*      */           {
/* 2194 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 2198 */           result = false;
/* 2199 */           diagnostics.add(
/* 2200 */               (Diagnostic)createDiagnostic(
/* 2201 */                 4, 
/* 2202 */                 "org.eclipse.emf.ecore.model", 
/* 2203 */                 33, 
/* 2204 */                 "_UI_EPackageUniqueNsURIs_diagnostic", 
/* 2205 */                 new Object[] { nsURI
/* 2206 */                 }, new Object[] { ePackage, otherEPackage
/* 2207 */                 }, context));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2212 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEParameter(EParameter eParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2222 */     if (!validate_NoCircularContainment((EObject)eParameter, diagnostics, context)) return false; 
/* 2223 */     boolean result = validate_EveryMultiplicityConforms((EObject)eParameter, diagnostics, context);
/* 2224 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eParameter, diagnostics, context); 
/* 2225 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eParameter, diagnostics, context); 
/* 2226 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eParameter, diagnostics, context); 
/* 2227 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eParameter, diagnostics, context); 
/* 2228 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eParameter, diagnostics, context); 
/* 2229 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eParameter, diagnostics, context); 
/* 2230 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eParameter, diagnostics, context); 
/* 2231 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eParameter, diagnostics, context); 
/* 2232 */     if (result || diagnostics != null) result &= validateETypedElement_ValidLowerBound((ETypedElement)eParameter, diagnostics, context); 
/* 2233 */     if (result || diagnostics != null) result &= validateETypedElement_ValidUpperBound((ETypedElement)eParameter, diagnostics, context); 
/* 2234 */     if (result || diagnostics != null) result &= validateETypedElement_ConsistentBounds((ETypedElement)eParameter, diagnostics, context); 
/* 2235 */     if (result || diagnostics != null) result &= validateETypedElement_ValidType((ETypedElement)eParameter, diagnostics, context); 
/* 2236 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEReference(EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2246 */     if (!validate_NoCircularContainment((EObject)eReference, diagnostics, context)) return false; 
/* 2247 */     boolean result = validate_EveryMultiplicityConforms((EObject)eReference, diagnostics, context);
/* 2248 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eReference, diagnostics, context); 
/* 2249 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eReference, diagnostics, context); 
/* 2250 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eReference, diagnostics, context); 
/* 2251 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eReference, diagnostics, context); 
/* 2252 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eReference, diagnostics, context); 
/* 2253 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eReference, diagnostics, context); 
/* 2254 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eReference, diagnostics, context); 
/* 2255 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eReference, diagnostics, context); 
/* 2256 */     if (result || diagnostics != null) result &= validateETypedElement_ValidLowerBound((ETypedElement)eReference, diagnostics, context); 
/* 2257 */     if (result || diagnostics != null) result &= validateETypedElement_ValidUpperBound((ETypedElement)eReference, diagnostics, context); 
/* 2258 */     if (result || diagnostics != null) result &= validateETypedElement_ConsistentBounds((ETypedElement)eReference, diagnostics, context); 
/* 2259 */     if (result || diagnostics != null) result &= validateETypedElement_ValidType((ETypedElement)eReference, diagnostics, context); 
/* 2260 */     if (result || diagnostics != null) result &= validateEStructuralFeature_ValidDefaultValueLiteral((EStructuralFeature)eReference, diagnostics, context); 
/* 2261 */     if (result || diagnostics != null) result &= validateEReference_ConsistentOpposite(eReference, diagnostics, context); 
/* 2262 */     if (result || diagnostics != null) result &= validateEReference_SingleContainer(eReference, diagnostics, context); 
/* 2263 */     if (result || diagnostics != null) result &= validateEReference_ConsistentKeys(eReference, diagnostics, context); 
/* 2264 */     if (result || diagnostics != null) result &= validateEReference_ConsistentUnique(eReference, diagnostics, context); 
/* 2265 */     if (result || diagnostics != null) result &= validateEReference_ConsistentContainer(eReference, diagnostics, context); 
/* 2266 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEReference_ConsistentOpposite(EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2284 */     boolean result = true;
/* 2285 */     EReference eOpposite = eReference.getEOpposite();
/* 2286 */     if (eOpposite != null) {
/*      */       
/* 2288 */       if (eReference.getEContainingClass() != null) {
/*      */         
/* 2290 */         EReference oppositeEOpposite = eOpposite.getEOpposite();
/* 2291 */         if (oppositeEOpposite != eReference) {
/*      */           
/* 2293 */           if (diagnostics == null)
/*      */           {
/* 2295 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 2299 */           result = false;
/* 2300 */           diagnostics.add(
/* 2301 */               (Diagnostic)createDiagnostic(
/* 2302 */                 4, 
/* 2303 */                 "org.eclipse.emf.ecore.model", 
/* 2304 */                 14, 
/* 2305 */                 "_UI_EReferenceOppositeOfOppositeInconsistent_diagnostic", 
/* 2306 */                 null, 
/* 2307 */                 new Object[] { eReference, eOpposite, oppositeEOpposite
/* 2308 */                 }, context));
/*      */         } 
/*      */         
/* 2311 */         EClassifier eType = eReference.getEType();
/* 2312 */         if (eType != null) {
/*      */           
/* 2314 */           EClass oppositeEContainingClass = eOpposite.getEContainingClass();
/* 2315 */           if (oppositeEContainingClass != null && oppositeEContainingClass != eType) {
/*      */             
/* 2317 */             if (diagnostics == null)
/*      */             {
/* 2319 */               return false;
/*      */             }
/*      */ 
/*      */             
/* 2323 */             result = false;
/* 2324 */             diagnostics.add(
/* 2325 */                 (Diagnostic)createDiagnostic(
/* 2326 */                   4, 
/* 2327 */                   "org.eclipse.emf.ecore.model", 
/* 2328 */                   13, 
/* 2329 */                   "_UI_EReferenceOppositeNotFeatureOfType_diagnostic", 
/* 2330 */                   null, 
/* 2331 */                   new Object[] { eReference, eOpposite, eType
/* 2332 */                   }, context));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2337 */       if (result) {
/*      */         
/* 2339 */         result = 
/* 2340 */           !(isEffectivelyTransient((EStructuralFeature)eReference) && 
/* 2341 */           !isEffectivelyTransient((EStructuralFeature)eOpposite) && 
/* 2342 */           eOpposite.isResolveProxies() && 
/* 2343 */           !eOpposite.isContainment());
/* 2344 */         if (diagnostics != null && !result)
/*      */         {
/* 2346 */           diagnostics.add(
/* 2347 */               (Diagnostic)createDiagnostic(
/* 2348 */                 4, 
/* 2349 */                 "org.eclipse.emf.ecore.model", 
/* 2350 */                 11, 
/* 2351 */                 "_UI_EReferenceTransientOppositeNotTransient_diagnostic", 
/* 2352 */                 null, 
/* 2353 */                 new Object[] { eReference, eOpposite
/* 2354 */                 }, context));
/*      */         }
/*      */       } 
/* 2357 */       if (result) {
/*      */         
/* 2359 */         result = !(eReference.isContainment() && eOpposite.isContainment());
/* 2360 */         if (diagnostics != null && !result)
/*      */         {
/* 2362 */           diagnostics.add(
/* 2363 */               (Diagnostic)createDiagnostic(
/* 2364 */                 4, 
/* 2365 */                 "org.eclipse.emf.ecore.model", 
/* 2366 */                 12, 
/* 2367 */                 "_UI_EReferenceOppositeBothContainment_diagnostic", 
/* 2368 */                 null, 
/* 2369 */                 new Object[] { eReference, eOpposite
/* 2370 */                 }, context));
/*      */         }
/*      */       } 
/*      */     } 
/* 2374 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEReference_SingleContainer(EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2386 */     boolean result = !(eReference.isContainer() && eReference.getUpperBound() != 1);
/* 2387 */     if (diagnostics != null && !result)
/*      */     {
/* 2389 */       diagnostics.add(
/* 2390 */           (Diagnostic)createDiagnostic(
/* 2391 */             4, 
/* 2392 */             "org.eclipse.emf.ecore.model", 
/* 2393 */             28, 
/* 2394 */             "_UI_EReferenceSingleContainer_diagnostic", 
/* 2395 */             new Object[] { Integer.valueOf(eReference.getUpperBound())
/* 2396 */             }, new Object[] { eReference
/* 2397 */             }, context));
/*      */     }
/* 2399 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEReference_ConsistentKeys(EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2412 */     boolean result = true;
/* 2413 */     EList<EAttribute> eKeys = eReference.getEKeys();
/* 2414 */     if (!eKeys.isEmpty()) {
/*      */       
/* 2416 */       EClass eClass = eReference.getEReferenceType();
/* 2417 */       if (eClass != null)
/*      */       {
/* 2419 */         for (EAttribute eAttribute : eKeys) {
/*      */           
/* 2421 */           if (eClass.getFeatureID((EStructuralFeature)eAttribute) == -1) {
/*      */             
/* 2423 */             if (diagnostics == null)
/*      */             {
/* 2425 */               return false;
/*      */             }
/*      */ 
/*      */             
/* 2429 */             result = false;
/* 2430 */             diagnostics.add(
/* 2431 */                 (Diagnostic)createDiagnostic(
/* 2432 */                   4, 
/* 2433 */                   "org.eclipse.emf.ecore.model", 
/* 2434 */                   10, 
/* 2435 */                   "_UI_EReferenceConsistentKeys_diagnostic", 
/* 2436 */                   new Object[] { getObjectLabel((EObject)eAttribute, context)
/* 2437 */                   }new Object[] { eReference, eAttribute
/* 2438 */                   }, context));
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2444 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEReference_ConsistentUnique(EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2457 */     boolean result = true;
/* 2458 */     if (eReference.isMany() && (
/* 2459 */       eReference.isContainment() || eReference.getEOpposite() != null) && 
/* 2460 */       !eReference.isUnique()) {
/*      */       
/* 2462 */       result = false;
/* 2463 */       if (diagnostics != null)
/*      */       {
/* 2465 */         diagnostics.add(
/* 2466 */             (Diagnostic)createDiagnostic(
/* 2467 */               4, 
/* 2468 */               "org.eclipse.emf.ecore.model", 
/* 2469 */               50, 
/* 2470 */               "_UI_EReferenceConsistentUnique_diagnostic", 
/* 2471 */               null, 
/* 2472 */               new Object[] { eReference
/* 2473 */               }, context));
/*      */       }
/*      */     } 
/* 2476 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEReference_ConsistentContainer(EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2487 */     if (eReference.isContainment() && eReference.getEContainingClass() != null) {
/*      */       
/* 2489 */       EClass eClass = eReference.getEReferenceType();
/* 2490 */       if (eClass != null)
/*      */       {
/* 2492 */         for (EReference otherEReference : eClass.getEAllReferences()) {
/*      */           
/* 2494 */           if (otherEReference.isRequired() && otherEReference.isContainer() && otherEReference.getEOpposite() != eReference) {
/*      */             
/* 2496 */             if (diagnostics != null)
/*      */             {
/* 2498 */               diagnostics.add(
/* 2499 */                   (Diagnostic)createDiagnostic(
/* 2500 */                     4, 
/* 2501 */                     "org.eclipse.emf.ecore.model", 
/* 2502 */                     51, 
/* 2503 */                     "_UI_EReferenceConsistentContainer_diagnostic", 
/* 2504 */                     new Object[] { getObjectLabel((EObject)otherEReference, context)
/* 2505 */                     }new Object[] { eReference, otherEReference
/* 2506 */                     }, context));
/*      */             }
/* 2508 */             return false;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 2513 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEStructuralFeature(EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2523 */     if (!validate_NoCircularContainment((EObject)eStructuralFeature, diagnostics, context)) return false; 
/* 2524 */     boolean result = validate_EveryMultiplicityConforms((EObject)eStructuralFeature, diagnostics, context);
/* 2525 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eStructuralFeature, diagnostics, context); 
/* 2526 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eStructuralFeature, diagnostics, context); 
/* 2527 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eStructuralFeature, diagnostics, context); 
/* 2528 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eStructuralFeature, diagnostics, context); 
/* 2529 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eStructuralFeature, diagnostics, context); 
/* 2530 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eStructuralFeature, diagnostics, context); 
/* 2531 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eStructuralFeature, diagnostics, context); 
/* 2532 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eStructuralFeature, diagnostics, context); 
/* 2533 */     if (result || diagnostics != null) result &= validateETypedElement_ValidLowerBound((ETypedElement)eStructuralFeature, diagnostics, context); 
/* 2534 */     if (result || diagnostics != null) result &= validateETypedElement_ValidUpperBound((ETypedElement)eStructuralFeature, diagnostics, context); 
/* 2535 */     if (result || diagnostics != null) result &= validateETypedElement_ConsistentBounds((ETypedElement)eStructuralFeature, diagnostics, context); 
/* 2536 */     if (result || diagnostics != null) result &= validateETypedElement_ValidType((ETypedElement)eStructuralFeature, diagnostics, context); 
/* 2537 */     if (result || diagnostics != null) result &= validateEStructuralFeature_ValidDefaultValueLiteral(eStructuralFeature, diagnostics, context); 
/* 2538 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEStructuralFeature_ValidDefaultValueLiteral(EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2549 */     String defaultValueLiteral = eStructuralFeature.getDefaultValueLiteral();
/* 2550 */     Object defaultValue = null;
/* 2551 */     EDataType eDataType = null;
/* 2552 */     boolean result = true;
/* 2553 */     if (defaultValueLiteral != null) {
/*      */       
/* 2555 */       EClassifier eType = eStructuralFeature.getEType();
/* 2556 */       if (eType instanceof EDataType) {
/*      */         
/* 2558 */         eDataType = (EDataType)eType;
/* 2559 */         defaultValue = eStructuralFeature.getDefaultValue();
/* 2560 */         if (defaultValue == null)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 2565 */           result = !isBuiltinEDataType(eDataType);
/*      */         }
/*      */         else
/*      */         {
/* 2569 */           result = getRootEValidator(context).validate(eDataType, defaultValue, null, context);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2574 */         result = false;
/*      */       } 
/*      */     } 
/* 2577 */     if (diagnostics != null && !result) {
/*      */       
/* 2579 */       BasicDiagnostic diagnostic = 
/* 2580 */         createDiagnostic(
/* 2581 */           4, 
/* 2582 */           "org.eclipse.emf.ecore.model", 
/* 2583 */           39, 
/* 2584 */           "_UI_EStructuralFeatureValidDefaultValueLiteral_diagnostic", 
/* 2585 */           new Object[] { defaultValueLiteral
/* 2586 */           }, new Object[] { eStructuralFeature
/* 2587 */           }, context);
/* 2588 */       if (defaultValue != null)
/*      */       {
/* 2590 */         getRootEValidator(context).validate(eDataType, defaultValue, (DiagnosticChain)diagnostic, context);
/*      */       }
/* 2592 */       diagnostics.add((Diagnostic)diagnostic);
/*      */     } 
/* 2594 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isBuiltinEDataType(EDataType eDataType) {
/* 2599 */     EPackage ePackage = eDataType.getEPackage();
/* 2600 */     if (ePackage == EcorePackage.eINSTANCE || ePackage == XMLTypePackage.eINSTANCE || ePackage == XMLNamespacePackage.eINSTANCE)
/*      */     {
/* 2602 */       return true;
/*      */     }
/*      */     
/* 2605 */     EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(eDataType);
/* 2606 */     if (baseType != null)
/*      */     {
/* 2608 */       return isBuiltinEDataType(baseType);
/*      */     }
/*      */     
/* 2611 */     EDataType itemType = ExtendedMetaData.INSTANCE.getItemType(eDataType);
/* 2612 */     if (itemType != null)
/*      */     {
/* 2614 */       return isBuiltinEDataType(itemType);
/*      */     }
/*      */     
/* 2617 */     List<EDataType> memberTypes = ExtendedMetaData.INSTANCE.getMemberTypes(eDataType);
/* 2618 */     if (!memberTypes.isEmpty()) {
/*      */       
/* 2620 */       for (EDataType memberType : memberTypes) {
/*      */         
/* 2622 */         if (!isBuiltinEDataType(memberType))
/*      */         {
/* 2624 */           return false;
/*      */         }
/*      */       } 
/* 2627 */       return true;
/*      */     } 
/*      */     
/* 2630 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETypedElement(ETypedElement eTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2640 */     if (!validate_NoCircularContainment((EObject)eTypedElement, diagnostics, context)) return false; 
/* 2641 */     boolean result = validate_EveryMultiplicityConforms((EObject)eTypedElement, diagnostics, context);
/* 2642 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eTypedElement, diagnostics, context); 
/* 2643 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eTypedElement, diagnostics, context); 
/* 2644 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eTypedElement, diagnostics, context); 
/* 2645 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eTypedElement, diagnostics, context); 
/* 2646 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eTypedElement, diagnostics, context); 
/* 2647 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eTypedElement, diagnostics, context); 
/* 2648 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eTypedElement, diagnostics, context); 
/* 2649 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eTypedElement, diagnostics, context); 
/* 2650 */     if (result || diagnostics != null) result &= validateETypedElement_ValidLowerBound(eTypedElement, diagnostics, context); 
/* 2651 */     if (result || diagnostics != null) result &= validateETypedElement_ValidUpperBound(eTypedElement, diagnostics, context); 
/* 2652 */     if (result || diagnostics != null) result &= validateETypedElement_ConsistentBounds(eTypedElement, diagnostics, context); 
/* 2653 */     if (result || diagnostics != null) result &= validateETypedElement_ValidType(eTypedElement, diagnostics, context); 
/* 2654 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETypedElement_ValidLowerBound(ETypedElement eTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2666 */     int lowerBound = eTypedElement.getLowerBound();
/* 2667 */     boolean result = (lowerBound >= 0);
/* 2668 */     if (diagnostics != null && !result)
/*      */     {
/* 2670 */       diagnostics.add(
/* 2671 */           (Diagnostic)createDiagnostic(
/* 2672 */             4, 
/* 2673 */             "org.eclipse.emf.ecore.model", 
/* 2674 */             39, 
/* 2675 */             "_UI_ETypedElementValidLowerBound_diagnostic", 
/* 2676 */             new Object[] { Integer.valueOf(lowerBound)
/* 2677 */             }, new Object[] { eTypedElement
/* 2678 */             }, context));
/*      */     }
/* 2680 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETypedElement_ValidUpperBound(ETypedElement eTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2695 */     int upperBound = eTypedElement.getUpperBound();
/* 2696 */     boolean result = 
/* 2697 */       !(upperBound <= 0 && 
/* 2698 */       upperBound != -2 && 
/* 2699 */       upperBound != -1);
/* 2700 */     if (diagnostics != null && !result)
/*      */     {
/* 2702 */       diagnostics.add(
/* 2703 */           (Diagnostic)createDiagnostic(
/* 2704 */             4, 
/* 2705 */             "org.eclipse.emf.ecore.model", 
/* 2706 */             41, 
/* 2707 */             "_UI_ETypedElementValidUpperBound_diagnostic", 
/* 2708 */             new Object[] { Integer.valueOf(upperBound)
/* 2709 */             }, new Object[] { eTypedElement
/* 2710 */             }, context));
/*      */     }
/* 2712 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETypedElement_ConsistentBounds(ETypedElement eTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2726 */     int lowerBound = eTypedElement.getLowerBound();
/* 2727 */     int upperBound = eTypedElement.getUpperBound();
/* 2728 */     boolean result = !(upperBound >= 0 && lowerBound > upperBound);
/* 2729 */     if (diagnostics != null && !result)
/*      */     {
/* 2731 */       diagnostics.add(
/* 2732 */           (Diagnostic)createDiagnostic(
/* 2733 */             4, 
/* 2734 */             "org.eclipse.emf.ecore.model", 
/* 2735 */             6, 
/* 2736 */             "_UI_ETypedElementConsistentBounds_diagnostic", 
/* 2737 */             new Object[] { Integer.valueOf(lowerBound), Integer.valueOf(upperBound)
/* 2738 */             }, new Object[] { eTypedElement
/* 2739 */             }, context));
/*      */     }
/* 2741 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETypedElement_ValidType(ETypedElement eTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2753 */     boolean result = true;
/* 2754 */     EGenericType eGenericType = eTypedElement.getEGenericType();
/* 2755 */     if (eGenericType == null && !(eTypedElement instanceof EOperation)) {
/*      */       
/* 2757 */       result = false;
/* 2758 */       if (diagnostics != null)
/*      */       {
/* 2760 */         diagnostics.add(
/* 2761 */             (Diagnostic)createDiagnostic(
/* 2762 */               4, 
/* 2763 */               "org.eclipse.emf.ecore.model", 
/* 2764 */               40, 
/* 2765 */               "_UI_ETypedElementNoType_diagnostic", 
/* 2766 */               null, 
/* 2767 */               new Object[] { eTypedElement
/* 2768 */               }, context));
/*      */       }
/*      */     } 
/* 2771 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEStringToStringMapEntry(Map.Entry<?, ?> eStringToStringMapEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2781 */     return validate_EveryDefaultConstraint((EObject)eStringToStringMapEntry, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEGenericType(EGenericType eGenericType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2791 */     if (!validate_NoCircularContainment((EObject)eGenericType, diagnostics, context)) return false; 
/* 2792 */     boolean result = validate_EveryMultiplicityConforms((EObject)eGenericType, diagnostics, context);
/* 2793 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eGenericType, diagnostics, context); 
/* 2794 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eGenericType, diagnostics, context); 
/* 2795 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eGenericType, diagnostics, context); 
/* 2796 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eGenericType, diagnostics, context); 
/* 2797 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eGenericType, diagnostics, context); 
/* 2798 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eGenericType, diagnostics, context); 
/* 2799 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eGenericType, diagnostics, context); 
/* 2800 */     if (result || diagnostics != null) result &= validateEGenericType_ConsistentType(eGenericType, diagnostics, context); 
/* 2801 */     if (result || diagnostics != null) result &= validateEGenericType_ConsistentBounds(eGenericType, diagnostics, context); 
/* 2802 */     if (result || diagnostics != null) result &= validateEGenericType_ConsistentArguments(eGenericType, diagnostics, context); 
/* 2803 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEGenericType_ConsistentType(EGenericType eGenericType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 2829 */     boolean result = true;
/*      */     
/* 2831 */     ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
/* 2832 */     EClassifier eClassifier = eGenericType.getEClassifier();
/* 2833 */     if (eTypeParameter != null) {
/*      */       
/* 2835 */       if (eClassifier != null) {
/*      */ 
/*      */ 
/*      */         
/* 2839 */         if (diagnostics == null)
/*      */         {
/* 2841 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 2845 */         result = false;
/* 2846 */         diagnostics.add(
/* 2847 */             (Diagnostic)createDiagnostic(
/* 2848 */               4, 
/* 2849 */               "org.eclipse.emf.ecore.model", 
/* 2850 */               21, 
/* 2851 */               "_UI_EGenericTypeNoTypeParameterAndClassifier_diagnostic", 
/* 2852 */               null, 
/* 2853 */               new Object[] { eGenericType
/* 2854 */               }, context));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2860 */       EObject scope = eTypeParameter.eContainer();
/* 2861 */       boolean inScope = EcoreUtil.isAncestor(scope, (EObject)eGenericType);
/* 2862 */       if (inScope) {
/*      */         EObject eObject1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2869 */         List<?> typeParameters = (List)scope.eGet((EStructuralFeature)eTypeParameter.eContainmentFeature());
/* 2870 */         EGenericType eGenericType1 = eGenericType;
/* 2871 */         for (EObject container = eGenericType1.eContainer(); container != scope; container = container.eContainer())
/*      */         {
/* 2873 */           eObject1 = container;
/*      */         }
/* 2875 */         int index = typeParameters.indexOf(eObject1);
/* 2876 */         int eTypeParameterIndex = typeParameters.indexOf(eTypeParameter);
/* 2877 */         inScope = !(index != -1 && 
/* 2878 */           index <= eTypeParameterIndex && (
/* 2879 */           index != eTypeParameterIndex || eGenericType.eContainingFeature() == EcorePackage.Literals.ETYPE_PARAMETER__EBOUNDS));
/*      */       } 
/*      */       
/* 2882 */       if (!inScope) {
/*      */ 
/*      */ 
/*      */         
/* 2886 */         if (diagnostics == null)
/*      */         {
/* 2888 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 2892 */         result = false;
/* 2893 */         diagnostics.add(
/* 2894 */             (Diagnostic)createDiagnostic(
/* 2895 */               4, 
/* 2896 */               "org.eclipse.emf.ecore.model", 
/* 2897 */               23, 
/* 2898 */               "_UI_EGenericTypeOutOfScopeTypeParameter_diagnostic", 
/* 2899 */               null, 
/* 2900 */               new Object[] { eGenericType
/* 2901 */               }, context));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2906 */     EReference eContainmentFeature = eGenericType.eContainmentFeature();
/* 2907 */     if (eContainmentFeature == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES) {
/*      */ 
/*      */ 
/*      */       
/* 2911 */       if (!(eGenericType.getEClassifier() instanceof EClass))
/*      */       {
/* 2913 */         if (diagnostics == null)
/*      */         {
/* 2915 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 2919 */         result = false;
/* 2920 */         diagnostics.add(
/* 2921 */             (Diagnostic)createDiagnostic(
/* 2922 */               4, 
/* 2923 */               "org.eclipse.emf.ecore.model", 
/* 2924 */               18, 
/* 2925 */               "_UI_EGenericTypeNoClass_diagnostic", 
/* 2926 */               null, 
/* 2927 */               new Object[] { eGenericType
/* 2928 */               }, context));
/*      */       }
/*      */     
/*      */     }
/* 2932 */     else if (eContainmentFeature == EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS) {
/*      */       
/* 2934 */       if (eGenericType.eContainer().eContainmentFeature() == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES)
/*      */       {
/*      */ 
/*      */         
/* 2938 */         if (eClassifier == null && eTypeParameter == null)
/*      */         {
/* 2940 */           if (diagnostics == null)
/*      */           {
/* 2942 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 2946 */           result = false;
/* 2947 */           diagnostics.add(
/* 2948 */               (Diagnostic)createDiagnostic(
/* 2949 */                 4, 
/* 2950 */                 "org.eclipse.emf.ecore.model", 
/* 2951 */                 24, 
/* 2952 */                 "_UI_EGenericTypeNoTypeParameterOrClassifier_diagnostic", 
/* 2953 */                 null, 
/* 2954 */                 new Object[] { eGenericType
/* 2955 */                 }, context));
/*      */         }
/*      */       
/*      */       }
/*      */     }
/* 2960 */     else if (eContainmentFeature != null) {
/*      */ 
/*      */ 
/*      */       
/* 2964 */       if (eClassifier == null && eTypeParameter == null) {
/*      */         
/* 2966 */         if (diagnostics == null)
/*      */         {
/* 2968 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 2972 */         result = false;
/* 2973 */         diagnostics.add(
/* 2974 */             (Diagnostic)createDiagnostic(
/* 2975 */               4, 
/* 2976 */               "org.eclipse.emf.ecore.model", 
/* 2977 */               24, 
/* 2978 */               "_UI_EGenericTypeNoTypeParameterOrClassifier_diagnostic", 
/* 2979 */               null, 
/* 2980 */               new Object[] { eGenericType
/* 2981 */               }, context));
/*      */       
/*      */       }
/* 2984 */       else if (eClassifier != null) {
/*      */         
/* 2986 */         EObject eContainer = eGenericType.eContainer();
/* 2987 */         if (eContainer instanceof EStructuralFeature)
/*      */         {
/* 2989 */           if (eClassifier instanceof EClass) {
/*      */             
/* 2991 */             if (eContainer instanceof EAttribute)
/*      */             {
/*      */ 
/*      */               
/* 2995 */               if (diagnostics == null)
/*      */               {
/* 2997 */                 return false;
/*      */               }
/*      */ 
/*      */               
/* 3001 */               result = false;
/* 3002 */               diagnostics.add(
/* 3003 */                   (Diagnostic)createDiagnostic(
/* 3004 */                     4, 
/* 3005 */                     "org.eclipse.emf.ecore.model", 
/* 3006 */                     19, 
/* 3007 */                     "_UI_EAttributeNoDataType_diagnostic", 
/* 3008 */                     null, 
/* 3009 */                     new Object[] { eGenericType
/* 3010 */                     }, context));
/*      */             }
/*      */           
/*      */           }
/* 3014 */           else if (eClassifier instanceof EDataType) {
/*      */             
/* 3016 */             if (eContainer instanceof EReference) {
/*      */ 
/*      */ 
/*      */               
/* 3020 */               if (diagnostics == null)
/*      */               {
/* 3022 */                 return false;
/*      */               }
/*      */ 
/*      */               
/* 3026 */               result = false;
/* 3027 */               diagnostics.add(
/* 3028 */                   (Diagnostic)createDiagnostic(
/* 3029 */                     4, 
/* 3030 */                     "org.eclipse.emf.ecore.model", 
/* 3031 */                     20, 
/* 3032 */                     "_UI_EReferenceNoClass_diagnostic", 
/* 3033 */                     null, 
/* 3034 */                     new Object[] { eGenericType
/* 3035 */                     }, context));
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3043 */     if (eClassifier != null && eContainmentFeature != null && eContainmentFeature != EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE) {
/*      */ 
/*      */ 
/*      */       
/* 3047 */       String instanceClassName = eClassifier.getInstanceClassName();
/* 3048 */       if (instanceClassName == "boolean" || 
/* 3049 */         instanceClassName == "byte" || 
/* 3050 */         instanceClassName == "char" || 
/* 3051 */         instanceClassName == "double" || 
/* 3052 */         instanceClassName == "float" || 
/* 3053 */         instanceClassName == "int" || 
/* 3054 */         instanceClassName == "long" || 
/* 3055 */         instanceClassName == "short") {
/*      */         
/* 3057 */         if (diagnostics == null)
/*      */         {
/* 3059 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 3063 */         result = false;
/* 3064 */         diagnostics.add(
/* 3065 */             (Diagnostic)createDiagnostic(
/* 3066 */               4, 
/* 3067 */               "org.eclipse.emf.ecore.model", 
/* 3068 */               22, 
/* 3069 */               "_UI_EGenericTypeInvalidPrimitiveType_diagnostic", 
/* 3070 */               new Object[] { instanceClassName
/* 3071 */               }, new Object[] { eGenericType
/* 3072 */               }, context));
/*      */       } 
/*      */     } 
/*      */     
/* 3076 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEGenericType_ConsistentBounds(EGenericType eGenericType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3091 */     boolean result = true;
/*      */     
/* 3093 */     EGenericType eLowerBound = eGenericType.getELowerBound();
/* 3094 */     EGenericType eUpperBound = eGenericType.getEUpperBound();
/* 3095 */     if (eLowerBound != null || eUpperBound != null) {
/*      */       
/* 3097 */       EReference eReference = eGenericType.eContainmentFeature();
/* 3098 */       if (eReference == EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS) {
/*      */ 
/*      */ 
/*      */         
/* 3102 */         if (eLowerBound != null && eUpperBound != null) {
/*      */           
/* 3104 */           if (diagnostics == null)
/*      */           {
/* 3106 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 3110 */           result = false;
/* 3111 */           diagnostics.add(
/* 3112 */               (Diagnostic)createDiagnostic(
/* 3113 */                 4, 
/* 3114 */                 "org.eclipse.emf.ecore.model", 
/* 3115 */                 9, 
/* 3116 */                 "_UI_EGenericTypeNoUpperAndLowerBound_diagnostic", 
/* 3117 */                 null, 
/* 3118 */                 new Object[] { eGenericType
/* 3119 */                 }, context));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3125 */         if (eGenericType.getEClassifier() != null || eGenericType.getETypeParameter() != null)
/*      */         {
/* 3127 */           if (diagnostics == null)
/*      */           {
/* 3129 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 3133 */           result = false;
/* 3134 */           diagnostics.add(
/* 3135 */               (Diagnostic)createDiagnostic(
/* 3136 */                 4, 
/* 3137 */                 "org.eclipse.emf.ecore.model", 
/* 3138 */                 8, 
/* 3139 */                 "_UI_EGenericTypeNoTypeParameterOrClassifierAndBound_diagnostic", 
/* 3140 */                 null, 
/* 3141 */                 new Object[] { eGenericType
/* 3142 */                 }, context));
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3150 */         if (diagnostics == null)
/*      */         {
/* 3152 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 3156 */         result = false;
/* 3157 */         diagnostics.add(
/* 3158 */             (Diagnostic)createDiagnostic(
/* 3159 */               4, 
/* 3160 */               "org.eclipse.emf.ecore.model", 
/* 3161 */               7, 
/* 3162 */               "_UI_EGenericTypeBoundsOnlyForTypeArgument_diagnostic", 
/* 3163 */               null, 
/* 3164 */               new Object[] { eGenericType
/* 3165 */               }, context));
/*      */       } 
/*      */     } 
/*      */     
/* 3169 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEGenericType_ConsistentArguments(EGenericType eGenericType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3184 */     boolean result = true;
/* 3185 */     EClassifier eClassifier = eGenericType.getEClassifier();
/* 3186 */     EList<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
/* 3187 */     int eTypeArgumentSize = eTypeArguments.size();
/* 3188 */     if (eClassifier == null) {
/*      */       
/* 3190 */       if (eTypeArgumentSize != 0)
/*      */       {
/*      */ 
/*      */         
/* 3194 */         if (diagnostics == null)
/*      */         {
/* 3196 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 3200 */         result = false;
/* 3201 */         diagnostics.add(
/* 3202 */             (Diagnostic)createDiagnostic(
/* 3203 */               4, 
/* 3204 */               "org.eclipse.emf.ecore.model", 
/* 3205 */               5, 
/* 3206 */               "_UI_EGenericTypeNoArguments_diagnostic", 
/* 3207 */               null, 
/* 3208 */               new Object[] { eGenericType
/* 3209 */               }, context));
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 3215 */       EList<ETypeParameter> eTypeParameters = eClassifier.getETypeParameters();
/* 3216 */       int eTypeParameterSize = eTypeParameters.size();
/* 3217 */       if (eTypeArgumentSize == 0) {
/*      */         
/* 3219 */         if (eTypeParameterSize > 0)
/*      */         {
/*      */ 
/*      */           
/* 3223 */           if (diagnostics == null)
/*      */           {
/* 3225 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 3229 */           result = false;
/* 3230 */           diagnostics.add(
/* 3231 */               (Diagnostic)createDiagnostic(
/* 3232 */                 2, 
/* 3233 */                 "org.eclipse.emf.ecore.model", 
/* 3234 */                 4, 
/* 3235 */                 "_UI_EGenericTypeArgumentsNeeded_diagnostic", 
/* 3236 */                 new Object[] { eClassifier.getName(), Integer.valueOf(eTypeParameterSize)
/* 3237 */                 }, new Object[] { eGenericType
/* 3238 */                 }, context));
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 3243 */       else if (eTypeArgumentSize != eTypeParameters.size()) {
/*      */ 
/*      */ 
/*      */         
/* 3247 */         if (diagnostics == null)
/*      */         {
/* 3249 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 3253 */         result = false;
/* 3254 */         diagnostics.add(
/* 3255 */             (Diagnostic)createDiagnostic(
/* 3256 */               4, 
/* 3257 */               "org.eclipse.emf.ecore.model", 
/* 3258 */               2, 
/* 3259 */               "_UI_EGenericTypeIncorrectArguments_diagnostic", 
/* 3260 */               new Object[] { eClassifier.getName(), Integer.valueOf(eTypeArgumentSize), Integer.valueOf(eTypeParameterSize)
/* 3261 */               }, new Object[] { eGenericType
/* 3262 */               }, context));
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 3268 */         Map<ETypeParameter, EGenericType> substitutions = new HashMap<ETypeParameter, EGenericType>();
/* 3269 */         for (int i = 0; i < eTypeParameterSize; i++) {
/*      */           
/* 3271 */           ETypeParameter eTypeParameter = (ETypeParameter)eTypeParameters.get(i);
/* 3272 */           EGenericType eTypeArgument = (EGenericType)eTypeArguments.get(i);
/* 3273 */           substitutions.put(eTypeParameter, eTypeArgument);
/* 3274 */           if (!isValidSubstitution(eTypeArgument, eTypeParameter, substitutions)) {
/*      */             
/* 3276 */             if (diagnostics == null)
/*      */             {
/* 3278 */               return false;
/*      */             }
/*      */ 
/*      */             
/* 3282 */             result = false;
/* 3283 */             diagnostics.add(
/* 3284 */                 (Diagnostic)createDiagnostic(
/* 3285 */                   4, 
/* 3286 */                   "org.eclipse.emf.ecore.model", 
/* 3287 */                   3, 
/* 3288 */                   "_UI_EGenericTypeArgumentInvalidSubstitution_diagnostic", 
/*      */                   
/* 3290 */                   new Object[] {
/* 3291 */                     getObjectLabel((EObject)eTypeArgument, context), 
/* 3292 */                     getObjectLabel((EObject)eTypeParameter, context)
/*      */                   
/* 3294 */                   }new Object[] { eGenericType, eTypeArgument, eTypeParameter
/* 3295 */                   }, context));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3302 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isValidSubstitution(EGenericType eTypeArgument, ETypeParameter eTypeParameter, Map<ETypeParameter, EGenericType> substitutions) {
/* 3317 */     EList<EGenericType> eBounds = eTypeParameter.getEBounds();
/* 3318 */     if (!eBounds.isEmpty()) {
/*      */       
/* 3320 */       if (eTypeArgument.getEClassifier() == null && 
/* 3321 */         eTypeArgument.getETypeParameter() == null && 
/* 3322 */         eTypeArgument.getEUpperBound() == null && 
/* 3323 */         eTypeArgument.getELowerBound() == null)
/*      */       {
/* 3325 */         return true;
/*      */       }
/* 3327 */       for (EGenericType eBound : eBounds) {
/*      */         
/* 3329 */         if (!isBounded(eTypeArgument, eBound, substitutions))
/*      */         {
/* 3331 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/* 3335 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isBounded(EGenericType eGenericType, EGenericType eBound, Map<? extends ETypeParameter, ? extends EGenericType> substitutions) {
/* 3369 */     if (eGenericType == eBound)
/*      */     {
/* 3371 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3376 */     EClassifier eBoundEClassifier = eBound.getEClassifier();
/* 3377 */     if (eBoundEClassifier != null) {
/*      */ 
/*      */ 
/*      */       
/* 3381 */       EClassifier eClassifier = eGenericType.getEClassifier();
/* 3382 */       if (eClassifier != null) {
/*      */ 
/*      */ 
/*      */         
/* 3386 */         if (eBoundEClassifier != eClassifier) {
/*      */ 
/*      */ 
/*      */           
/* 3390 */           if (eBoundEClassifier instanceof EClass && eClassifier instanceof EClass) {
/*      */             
/* 3392 */             EClass eClass = (EClass)eClassifier;
/*      */ 
/*      */ 
/*      */             
/* 3396 */             if (INSTANCE.validateEClass_NoCircularSuperTypes(eClass, (DiagnosticChain)null, (Map<Object, Object>)null))
/*      */             {
/*      */ 
/*      */               
/* 3400 */               for (EGenericType eGenericSuperType : eClass.getEGenericSuperTypes()) {
/*      */ 
/*      */ 
/*      */                 
/* 3404 */                 Map<? extends ETypeParameter, ? extends EGenericType> localSubstitutions = substitutions;
/*      */ 
/*      */ 
/*      */                 
/* 3408 */                 EList<ETypeParameter> eTypeParameters = eClass.getETypeParameters();
/* 3409 */                 int size = eTypeParameters.size();
/* 3410 */                 if (size > 0) {
/*      */                   
/* 3412 */                   EList<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
/* 3413 */                   if (size == eTypeArguments.size()) {
/*      */                     
/* 3415 */                     HashMap<ETypeParameter, EGenericType> additionalLocalSubstitutions = new HashMap<ETypeParameter, EGenericType>(substitutions);
/* 3416 */                     for (int i = 0; i < size; i++)
/*      */                     {
/* 3418 */                       additionalLocalSubstitutions.put((ETypeParameter)eTypeParameters.get(i), (EGenericType)eTypeArguments.get(i));
/*      */                     }
/* 3420 */                     localSubstitutions = additionalLocalSubstitutions;
/*      */                   } 
/*      */                 } 
/* 3423 */                 if (isBounded(eGenericSuperType, eBound, localSubstitutions))
/*      */                 {
/* 3425 */                   return true;
/*      */                 }
/*      */               } 
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 3432 */             return false;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3438 */           Class<?> eBoundClass = eBoundEClassifier.getInstanceClass();
/* 3439 */           if (eBoundClass != null) {
/*      */             
/* 3441 */             Class<?> eClassifierClass = eClassifier.getInstanceClass();
/* 3442 */             if (eClassifierClass != null && !eBoundClass.isAssignableFrom(eClassifierClass))
/*      */             {
/* 3444 */               return false;
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3452 */         return matchingTypeArguments(eGenericType.getETypeArguments(), eBound.getETypeArguments(), substitutions);
/*      */       } 
/*      */ 
/*      */       
/* 3456 */       ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
/* 3457 */       if (eTypeParameter != null) {
/*      */         
/* 3459 */         EGenericType substitution = substitutions.get(eTypeParameter);
/* 3460 */         if (substitution == eGenericType)
/*      */         {
/* 3462 */           return true;
/*      */         }
/* 3464 */         if (substitution != null && substitution.getEUpperBound() != eGenericType && substitution.getELowerBound() != eGenericType)
/*      */         {
/* 3466 */           return isBounded(substitution, eBound, substitutions);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3472 */         boolean result = false;
/* 3473 */         for (EGenericType eTypeParameterBound : eTypeParameter.getEBounds()) {
/*      */           
/* 3475 */           if (isBounded(eTypeParameterBound, eBound, substitutions)) {
/*      */             
/* 3477 */             result = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 3481 */         return result;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3488 */       EGenericType eUpperBound = eGenericType.getEUpperBound();
/* 3489 */       if (eUpperBound != null)
/*      */       {
/* 3491 */         return isBounded(eUpperBound, eBound, substitutions);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3497 */       EGenericType eLowerBound = eGenericType.getELowerBound();
/* 3498 */       return (eLowerBound != null && isBounded(eLowerBound, eBound, substitutions));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3505 */     ETypeParameter eBoundETypeParameter = eBound.getETypeParameter();
/* 3506 */     if (eBoundETypeParameter != null) {
/*      */       
/* 3508 */       ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
/* 3509 */       if (eTypeParameter == eBoundETypeParameter)
/*      */       {
/* 3511 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 3515 */       EGenericType substitution = substitutions.get(eBoundETypeParameter);
/* 3516 */       if (substitution != null)
/*      */       {
/* 3518 */         return isBounded(eGenericType, substitution, substitutions);
/*      */       }
/* 3520 */       if (eTypeParameter != null) {
/*      */         
/* 3522 */         substitution = substitutions.get(eTypeParameter);
/* 3523 */         if (substitution == eGenericType)
/*      */         {
/* 3525 */           return true;
/*      */         }
/* 3527 */         if (substitution != null && substitution.getEUpperBound() != eGenericType && substitution.getELowerBound() != eGenericType)
/*      */         {
/* 3529 */           return isBounded(substitution, eBound, substitutions);
/*      */         }
/*      */ 
/*      */         
/* 3533 */         boolean result = false;
/* 3534 */         for (EGenericType eTypeParameterEBound : eTypeParameter.getEBounds()) {
/*      */           
/* 3536 */           if (!(result = isBounded(eTypeParameterEBound, eBound, substitutions)))
/*      */           {
/* 3538 */             for (EGenericType eBoundETypeParameterEBound : eBoundETypeParameter.getEBounds()) {
/*      */               
/* 3540 */               if (isBounded(eTypeParameterEBound, eBoundETypeParameterEBound, substitutions)) {
/*      */                 
/* 3542 */                 result = true;
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           }
/* 3547 */           if (!result)
/*      */           {
/* 3549 */             return false;
/*      */           }
/*      */         } 
/* 3552 */         return result;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3557 */       if (eGenericType.getEUpperBound() != null)
/*      */       {
/* 3559 */         return isBounded(eGenericType.getEUpperBound(), eBound, substitutions);
/*      */       }
/*      */ 
/*      */       
/* 3563 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3572 */     if (eGenericType.getETypeParameter() == null && eGenericType.getEClassifier() == null)
/*      */     {
/* 3574 */       return false;
/*      */     }
/* 3576 */     EGenericType eBoundEUpperBound = eBound.getEUpperBound();
/* 3577 */     if (eBoundEUpperBound != null)
/*      */     {
/* 3579 */       return isBounded(eGenericType, eBoundEUpperBound, substitutions);
/*      */     }
/*      */ 
/*      */     
/* 3583 */     EGenericType eBoundELowerBound = eBound.getELowerBound();
/* 3584 */     if (eBoundELowerBound != null)
/*      */     {
/*      */ 
/*      */       
/* 3588 */       return isBounded(eBoundELowerBound, eGenericType, substitutions);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3593 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean matchingTypeArguments(EList<EGenericType> eTypeArguments1, EList<EGenericType> eTypeArguments2, Map<? extends ETypeParameter, ? extends EGenericType> substitutions) {
/* 3602 */     int size = eTypeArguments1.size();
/* 3603 */     if (size != eTypeArguments2.size())
/*      */     {
/* 3605 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 3609 */     for (int i = 0; i < size; i++) {
/*      */       
/* 3611 */       EGenericType eTypeArgument1 = (EGenericType)eTypeArguments1.get(i);
/* 3612 */       EGenericType eTypeArgument2 = (EGenericType)eTypeArguments2.get(i);
/* 3613 */       if (!isMatching(eTypeArgument1, eTypeArgument2, substitutions))
/*      */       {
/* 3615 */         return false;
/*      */       }
/*      */     } 
/* 3618 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isMatching(EGenericType eGenericType, EGenericType eBound, Map<? extends ETypeParameter, ? extends EGenericType> substitutions) {
/* 3624 */     if (eGenericType == eBound)
/*      */     {
/* 3626 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3631 */     EClassifier eBoundEClassifier = eBound.getEClassifier();
/* 3632 */     if (eBoundEClassifier != null) {
/*      */ 
/*      */ 
/*      */       
/* 3636 */       EClassifier eClassifier = eGenericType.getEClassifier();
/* 3637 */       if (eClassifier != null) {
/*      */ 
/*      */ 
/*      */         
/* 3641 */         if (eClassifier != eBoundEClassifier) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3646 */           String instanceTypeName1 = eClassifier.getInstanceTypeName();
/* 3647 */           String instanceTypeName2 = eBoundEClassifier.getInstanceTypeName();
/*      */ 
/*      */ 
/*      */           
/* 3651 */           if (instanceTypeName1 == null || !instanceTypeName1.equals(instanceTypeName2))
/*      */           {
/* 3653 */             return false;
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3660 */         return equalTypeArguments(eGenericType.getETypeArguments(), eBound.getETypeArguments(), substitutions);
/*      */       } 
/*      */ 
/*      */       
/* 3664 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3669 */     ETypeParameter eBoundETypeParameter = eBound.getETypeParameter();
/* 3670 */     if (eBoundETypeParameter != null) {
/*      */       
/* 3672 */       ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
/* 3673 */       if (eTypeParameter == eBoundETypeParameter)
/*      */       {
/* 3675 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 3679 */       EGenericType substitution = substitutions.get(eTypeParameter);
/* 3680 */       if (substitution != null)
/*      */       {
/* 3682 */         return !(substitution != eGenericType && !isMatching(substitution, eBound, substitutions));
/*      */       }
/* 3684 */       if ((substitution = substitutions.get(eBoundETypeParameter)) != null)
/*      */       {
/* 3686 */         return !(substitution != eBound && !isMatching(eGenericType, substitution, substitutions));
/*      */       }
/*      */ 
/*      */       
/* 3690 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3698 */     if (eGenericType.getEClassifier() == null && eGenericType.getETypeParameter() == null) {
/*      */ 
/*      */       
/* 3701 */       if (isMatching(eGenericType.getELowerBound(), eBound.getELowerBound(), substitutions) && 
/* 3702 */         isMatching(eGenericType.getEUpperBound(), eBound.getEUpperBound(), substitutions)) return true;
/*      */       
/*      */       return false;
/*      */     } 
/* 3706 */     EGenericType eBoundEUpperBound = eBound.getEUpperBound();
/* 3707 */     if (eBoundEUpperBound != null)
/*      */     {
/* 3709 */       return isBounded(eGenericType, eBoundEUpperBound, substitutions);
/*      */     }
/*      */ 
/*      */     
/* 3713 */     EGenericType eBoundELowerBound = eBound.getELowerBound();
/* 3714 */     if (eBoundELowerBound != null)
/*      */     {
/*      */ 
/*      */       
/* 3718 */       return isMatching(eBoundELowerBound, eGenericType, substitutions);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3723 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equalTypeArguments(EList<EGenericType> eTypeArguments1, EList<EGenericType> eTypeArguments2, Map<? extends ETypeParameter, ? extends EGenericType> substitutions) {
/* 3733 */     int size = eTypeArguments1.size();
/* 3734 */     if (size != eTypeArguments2.size())
/*      */     {
/* 3736 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 3740 */     for (int i = 0; i < size; i++) {
/*      */       
/* 3742 */       EGenericType eTypeArgument1 = (EGenericType)eTypeArguments1.get(i);
/* 3743 */       EGenericType eTypeArgument2 = (EGenericType)eTypeArguments2.get(i);
/* 3744 */       if (!equalTypeArguments(eTypeArgument1, eTypeArgument2, substitutions))
/*      */       {
/* 3746 */         return false;
/*      */       }
/*      */     } 
/* 3749 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equalTypeArguments(EGenericType eGenericType1, EGenericType eGenericType2, Map<? extends ETypeParameter, ? extends EGenericType> substitutions) {
/* 3758 */     if (eGenericType1 == eGenericType2)
/*      */     {
/* 3760 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 3764 */     if (eGenericType1 == null || eGenericType2 == null)
/*      */     {
/* 3766 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3774 */     EClassifier eClassifier1 = eGenericType1.getEClassifier();
/* 3775 */     EClassifier eClassifier2 = eGenericType2.getEClassifier();
/*      */ 
/*      */ 
/*      */     
/* 3779 */     if (eClassifier1 != eClassifier2)
/*      */     {
/*      */ 
/*      */       
/* 3783 */       if (eClassifier1 != null && eClassifier2 != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3788 */         String instanceTypeName1 = eClassifier1.getInstanceTypeName();
/* 3789 */         String instanceTypeName2 = eClassifier2.getInstanceTypeName();
/*      */ 
/*      */ 
/*      */         
/* 3793 */         if (instanceTypeName1 == null || !instanceTypeName1.equals(instanceTypeName2))
/*      */         {
/* 3795 */           return false;
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 3800 */       else if (eClassifier1 != null || eClassifier2 != null) {
/*      */         
/* 3802 */         return false;
/*      */       } 
/*      */     }
/*      */     
/* 3806 */     ETypeParameter eTypeParameter1 = eGenericType1.getETypeParameter();
/* 3807 */     EGenericType substitution = substitutions.get(eTypeParameter1);
/* 3808 */     if (substitution != null)
/*      */     {
/* 3810 */       return equalTypeArguments(substitution, eGenericType2, substitutions);
/*      */     }
/* 3812 */     ETypeParameter eTypeParameter2 = eGenericType2.getETypeParameter();
/* 3813 */     substitution = substitutions.get(eTypeParameter2);
/* 3814 */     if (substitution != null)
/*      */     {
/* 3816 */       return equalTypeArguments(eGenericType1, substitution, substitutions);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3822 */     if (eTypeParameter1 == eTypeParameter2 && 
/* 3823 */       equalTypeArguments(eGenericType1.getETypeArguments(), eGenericType2.getETypeArguments(), substitutions) && 
/* 3824 */       equalTypeArguments(eGenericType1.getELowerBound(), eGenericType2.getELowerBound(), substitutions) && 
/* 3825 */       equalTypeArguments(eGenericType1.getEUpperBound(), eGenericType2.getEUpperBound(), substitutions)) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETypeParameter(ETypeParameter eTypeParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3836 */     if (!validate_NoCircularContainment((EObject)eTypeParameter, diagnostics, context)) return false; 
/* 3837 */     boolean result = validate_EveryMultiplicityConforms((EObject)eTypeParameter, diagnostics, context);
/* 3838 */     if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)eTypeParameter, diagnostics, context); 
/* 3839 */     if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)eTypeParameter, diagnostics, context); 
/* 3840 */     if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)eTypeParameter, diagnostics, context); 
/* 3841 */     if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)eTypeParameter, diagnostics, context); 
/* 3842 */     if (result || diagnostics != null) result &= validate_UniqueID((EObject)eTypeParameter, diagnostics, context); 
/* 3843 */     if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)eTypeParameter, diagnostics, context); 
/* 3844 */     if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)eTypeParameter, diagnostics, context); 
/* 3845 */     if (result || diagnostics != null) result &= validateENamedElement_WellFormedName((ENamedElement)eTypeParameter, diagnostics, context); 
/* 3846 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEBigDecimal(BigDecimal eBigDecimal, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3856 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEBigInteger(BigInteger eBigInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3866 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEBoolean(boolean eBoolean, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3876 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEBooleanObject(Boolean eBooleanObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3886 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEByte(byte eByte, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3896 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEByteArray(byte[] eByteArray, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3906 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEByteObject(Byte eByteObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3916 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEChar(char eChar, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3926 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateECharacterObject(Character eCharacterObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3936 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEDate(Date eDate, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3946 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEDiagnosticChain(DiagnosticChain eDiagnosticChain, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3956 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEDouble(double eDouble, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3966 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEDoubleObject(Double eDoubleObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3976 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEEList(EList<?> eeList, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3986 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEEnumerator(Enumerator eEnumerator, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 3996 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEFeatureMap(FeatureMap eFeatureMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4006 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEFeatureMapEntry(FeatureMap.Entry eFeatureMapEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4016 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEFloat(float eFloat, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4026 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEFloatObject(Float eFloatObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4036 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEInt(int eInt, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4046 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEIntegerObject(Integer eIntegerObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4056 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEJavaClass(Class<?> eJavaClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4066 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEJavaObject(Object eJavaObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4076 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateELong(long eLong, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4086 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateELongObject(Long eLongObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4096 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEMap(Map<?, ?> eMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4106 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEResource(Resource eResource, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4116 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEResourceSet(ResourceSet eResourceSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4126 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEShort(short eShort, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4136 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEShortObject(Short eShortObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4146 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEString(String eString, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4156 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateETreeIterator(TreeIterator<?> eTreeIterator, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4166 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateEInvocationTargetException(InvocationTargetException eInvocationTargetException, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 4176 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceLocator getResourceLocator() {
/* 4191 */     return super.getResourceLocator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EGenericTypeBuilder
/*      */   {
/* 4203 */     public static final EGenericTypeBuilder INSTANCE = new EGenericTypeBuilder();
/*      */     
/* 4205 */     private static final char[] NO_CHARS = new char[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Diagnostic parseInstanceTypeName(String instanceTypeName) {
/* 4215 */       BasicDiagnostic placeholder = new BasicDiagnostic();
/* 4216 */       char[] instanceTypeNameCharacterArray = (instanceTypeName == null) ? NO_CHARS : instanceTypeName.toCharArray();
/* 4217 */       EGenericType eGenericType = handleInstanceTypeName(instanceTypeNameCharacterArray, 0, instanceTypeNameCharacterArray.length, (DiagnosticChain)placeholder);
/* 4218 */       BasicDiagnostic result = 
/* 4219 */         createDiagnostic(
/* 4220 */           placeholder.getSeverity(), 
/* 4221 */           "org.eclipse.emf.ecore.model", 
/* 4222 */           42, 
/* 4223 */           "_UI_EClassifierInstanceTypeNameAnalysisResult_diagnostic", 
/* 4224 */           new Object[] { instanceTypeName
/* 4225 */           }, new Object[] { eGenericType, instanceTypeName });
/* 4226 */       result.addAll((Diagnostic)placeholder);
/* 4227 */       return (Diagnostic)result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Diagnostic parseTypeParameterList(String typeParameterList) {
/* 4238 */       BasicDiagnostic placeholder = new BasicDiagnostic();
/* 4239 */       char[] instanceTypeNameCharacterArray = (typeParameterList == null) ? NO_CHARS : typeParameterList.toCharArray();
/* 4240 */       List<ETypeParameter> eTypeParameters = handleTypeParameters(instanceTypeNameCharacterArray, 0, instanceTypeNameCharacterArray.length, (DiagnosticChain)placeholder);
/* 4241 */       BasicDiagnostic result = 
/* 4242 */         createDiagnostic(
/* 4243 */           placeholder.getSeverity(), 
/* 4244 */           "org.eclipse.emf.ecore.model", 
/* 4245 */           42, 
/* 4246 */           "_UI_EClassifierInstanceTypeNameAnalysisResult_diagnostic", 
/* 4247 */           new Object[] { typeParameterList
/* 4248 */           }, new Object[] { eTypeParameters, typeParameterList });
/* 4249 */       result.addAll((Diagnostic)placeholder);
/* 4250 */       return (Diagnostic)result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Diagnostic parseTypeArgumentList(String typeArgumentList) {
/* 4262 */       BasicDiagnostic placeholder = new BasicDiagnostic();
/* 4263 */       char[] instanceTypeNameCharacterArray = (typeArgumentList == null) ? NO_CHARS : typeArgumentList.toCharArray();
/* 4264 */       List<EGenericType> eTypeArguments = handleTypeArguments(instanceTypeNameCharacterArray, 0, instanceTypeNameCharacterArray.length, (DiagnosticChain)placeholder);
/* 4265 */       BasicDiagnostic result = 
/* 4266 */         createDiagnostic(
/* 4267 */           placeholder.getSeverity(), 
/* 4268 */           "org.eclipse.emf.ecore.model", 
/* 4269 */           42, 
/* 4270 */           "_UI_EClassifierInstanceTypeNameAnalysisResult_diagnostic", 
/* 4271 */           new Object[] { typeArgumentList
/* 4272 */           }, new Object[] { eTypeArguments, typeArgumentList });
/* 4273 */       result.addAll((Diagnostic)placeholder);
/* 4274 */       return (Diagnostic)result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Diagnostic parseTypeParameter(String typeParameter) {
/* 4285 */       BasicDiagnostic placeholder = new BasicDiagnostic();
/* 4286 */       char[] instanceTypeNameCharacterArray = (typeParameter == null) ? NO_CHARS : typeParameter.toCharArray();
/* 4287 */       ETypeParameter eTypeParameter = handleTypeParameter(instanceTypeNameCharacterArray, 0, instanceTypeNameCharacterArray.length, (DiagnosticChain)placeholder);
/* 4288 */       BasicDiagnostic result = 
/* 4289 */         createDiagnostic(
/* 4290 */           placeholder.getSeverity(), 
/* 4291 */           "org.eclipse.emf.ecore.model", 
/* 4292 */           42, 
/* 4293 */           "_UI_EClassifierInstanceTypeNameAnalysisResult_diagnostic", 
/* 4294 */           new Object[] { typeParameter
/* 4295 */           }, new Object[] { eTypeParameter, typeParameter });
/* 4296 */       result.addAll((Diagnostic)placeholder);
/* 4297 */       return (Diagnostic)result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EClassifier resolveEClassifier(String instanceTypeName) {
/* 4307 */       EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
/* 4308 */       eDataType.setInstanceTypeName(instanceTypeName);
/* 4309 */       return (EClassifier)eDataType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void report(DiagnosticChain diagnostics, String key, Object[] substitutions, int index) {
/* 4321 */       report(diagnostics, getString(key, substitutions), index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void report(DiagnosticChain diagnostics, String message, int index) {
/* 4332 */       if (diagnostics != null)
/*      */       {
/* 4334 */         diagnostics.add(
/* 4335 */             (Diagnostic)new BasicDiagnostic(
/* 4336 */               4, 
/* 4337 */               "org.eclipse.emf.ecore.model", 
/* 4338 */               42, 
/* 4339 */               message, 
/* 4340 */               new Object[] { Integer.valueOf(index) }));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EGenericType handleInstanceTypeName(char[] instanceTypeName, int start, int end, DiagnosticChain diagnostics) {
/* 4362 */       EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
/* 4363 */       StringBuilder qualifiedName = new StringBuilder();
/* 4364 */       int identifierStart = -1;
/* 4365 */       int identifierLast = -1;
/* 4366 */       int brackets = 0;
/* 4367 */       List<EGenericType> typeArguments = null;
/*      */       
/* 4369 */       for (int i = start; i < end; i = Character.offsetByCodePoints(instanceTypeName, 0, instanceTypeName.length, i, 1)) {
/*      */         
/* 4371 */         int codePoint = Character.codePointAt(instanceTypeName, i);
/* 4372 */         if (codePoint == 91) {
/*      */           
/* 4374 */           if (identifierStart == -1 && (qualifiedName.length() == 0 || qualifiedName.charAt(qualifiedName.length() - 1) == '.')) {
/*      */             
/* 4376 */             report(
/* 4377 */                 diagnostics, 
/* 4378 */                 "_UI_EClassifierInstanceTypeNameBracketWithoutPrecedingIdentifier_diagnostic", 
/* 4379 */                 new Object[] { Integer.valueOf(i)
/* 4380 */                 }, i);
/* 4381 */             return eGenericType;
/*      */           } 
/*      */ 
/*      */           
/* 4385 */           for (int j = i + 1; j < end; j = Character.offsetByCodePoints(instanceTypeName, 0, instanceTypeName.length, j, 1)) {
/*      */             
/* 4387 */             codePoint = Character.codePointAt(instanceTypeName, j);
/* 4388 */             if (codePoint == 93) {
/*      */               
/* 4390 */               i = j;
/* 4391 */               brackets++;
/*      */               // Byte code: goto -> 726
/*      */             } 
/* 4394 */             if (!Character.isWhitespace(codePoint)) {
/*      */               
/* 4396 */               report(
/* 4397 */                   diagnostics, 
/* 4398 */                   "_UI_EClassifierInstanceTypeNameNoClosingBracket2_diagnostic", 
/* 4399 */                   new Object[] { Integer.valueOf(j), new String(Character.toChars(codePoint))
/* 4400 */                   }, j);
/* 4401 */               return eGenericType;
/*      */             } 
/*      */           } 
/* 4404 */           report(
/* 4405 */               diagnostics, 
/* 4406 */               "_UI_EClassifierInstanceTypeNameNoClosingBracket_diagnostic", 
/* 4407 */               new Object[] { Integer.valueOf(end)
/* 4408 */               }, end);
/* 4409 */           return eGenericType;
/*      */         } 
/*      */         
/* 4412 */         if (brackets > 0) {
/*      */           
/* 4414 */           if (!Character.isWhitespace(codePoint))
/*      */           {
/* 4416 */             report(
/* 4417 */                 diagnostics, 
/* 4418 */                 "_UI_EClassifierInstanceTypeNameBracketExpected_diagnostic", 
/* 4419 */                 new Object[] { Integer.valueOf(i), new String(Character.toChars(codePoint))
/* 4420 */                 }, i);
/* 4421 */             return eGenericType;
/*      */           }
/*      */         
/* 4424 */         } else if (codePoint == 46) {
/*      */           
/* 4426 */           if (identifierStart == -1)
/*      */           {
/* 4428 */             if (qualifiedName.length() == 0 || qualifiedName.charAt(qualifiedName.length() - 1) == '.') {
/*      */               
/* 4430 */               report(
/* 4431 */                   diagnostics, 
/* 4432 */                   "_UI_EClassifierInstanceTypeNameDotWithoutPrecedingIdentifier_diagnostic", 
/* 4433 */                   new Object[] { Integer.valueOf(i)
/* 4434 */                   }, i);
/* 4435 */               return eGenericType;
/*      */             } 
/*      */ 
/*      */             
/* 4439 */             qualifiedName.append('.');
/*      */           
/*      */           }
/*      */           else
/*      */           {
/* 4444 */             qualifiedName.append(instanceTypeName, identifierStart, identifierLast - identifierStart + 1);
/* 4445 */             qualifiedName.append('.');
/* 4446 */             identifierStart = -1;
/* 4447 */             identifierLast = -1;
/*      */           }
/*      */         
/* 4450 */         } else if ((identifierStart != -1) ? isIdentifierPart(codePoint) : isIdentifierStart(codePoint)) {
/*      */           
/* 4452 */           if (identifierStart == -1) {
/*      */             
/* 4454 */             if (qualifiedName.length() > 0 && qualifiedName.charAt(qualifiedName.length() - 1) != '.') {
/*      */               
/* 4456 */               report(
/* 4457 */                   diagnostics, 
/* 4458 */                   "_UI_EClassifierInstanceTypeNameDotExpectedBeforeIdentifier_diagnostic", 
/* 4459 */                   new Object[] { Integer.valueOf(i)
/* 4460 */                   }, i);
/* 4461 */               return eGenericType;
/*      */             } 
/* 4463 */             identifierStart = i;
/*      */           } 
/* 4465 */           identifierLast = i;
/*      */         }
/* 4467 */         else if (Character.isWhitespace(codePoint)) {
/*      */           
/* 4469 */           if (identifierStart != -1)
/*      */           {
/*      */ 
/*      */             
/* 4473 */             if (qualifiedName.length() == 0 || qualifiedName.charAt(qualifiedName.length() - 1) == '.')
/*      */             {
/* 4475 */               qualifiedName.append(instanceTypeName, identifierStart, identifierLast - identifierStart + 1);
/* 4476 */               identifierStart = -1;
/* 4477 */               identifierLast = -1;
/*      */             }
/*      */           
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 4484 */           if (codePoint == 60) {
/*      */             
/* 4486 */             if (identifierStart == -1 && (qualifiedName.length() == 0 || qualifiedName.charAt(qualifiedName.length() - 1) == '.')) {
/*      */               
/* 4488 */               report(
/* 4489 */                   diagnostics, 
/* 4490 */                   "_UI_EClassifierInstanceTypeNameAngleBracketWithoutPrecedingIdentifier_diagnostic", 
/* 4491 */                   new Object[] { Integer.valueOf(i)
/* 4492 */                   }, i);
/* 4493 */               return eGenericType;
/*      */             } 
/* 4495 */             for (int j = end - 1; j > i; j--) {
/*      */               
/* 4497 */               if (instanceTypeName[j] == '>') {
/*      */                 
/* 4499 */                 typeArguments = handleTypeArguments(instanceTypeName, i + 1, j, diagnostics);
/* 4500 */                 i = j;
/*      */                 // Byte code: goto -> 726
/*      */               } 
/*      */             } 
/* 4504 */             report(
/* 4505 */                 diagnostics, 
/* 4506 */                 "_UI_EClassifierInstanceTypeNameUnterminatedAngleBracket_diagnostic", 
/* 4507 */                 new Object[] { Integer.valueOf(i)
/* 4508 */                 }, i);
/* 4509 */             return eGenericType;
/*      */           } 
/*      */ 
/*      */           
/* 4513 */           report(
/* 4514 */               diagnostics, 
/* 4515 */               "_UI_EClassifierInstanceTypeNameUnexpectedCharacter_diagnostic", 
/* 4516 */               new Object[] { Integer.valueOf(i), new String(Character.toChars(codePoint))
/* 4517 */               }, i);
/* 4518 */           return eGenericType;
/*      */         } 
/*      */       } 
/*      */       
/* 4522 */       if (identifierStart == -1 && (qualifiedName.length() == 0 || qualifiedName.charAt(qualifiedName.length() - 1) == '.')) {
/*      */         
/* 4524 */         report(
/* 4525 */             diagnostics, 
/* 4526 */             "_UI_EClassifierInstanceTypeNameExpectingIdentifier_diagnostic", 
/* 4527 */             new Object[] { Integer.valueOf(end)
/* 4528 */             }, end);
/*      */       }
/*      */       else {
/*      */         
/* 4532 */         if (identifierStart != -1)
/*      */         {
/* 4534 */           qualifiedName.append(instanceTypeName, identifierStart, identifierLast - identifierStart + 1);
/*      */         }
/* 4536 */         while (brackets-- > 0)
/*      */         {
/* 4538 */           qualifiedName.append("[]");
/*      */         }
/* 4540 */         String qualifiedNameString = qualifiedName.toString();
/* 4541 */         eGenericType.setEClassifier(resolveEClassifier(qualifiedNameString));
/* 4542 */         if (typeArguments != null)
/*      */         {
/* 4544 */           eGenericType.getETypeArguments().addAll(typeArguments);
/*      */         }
/*      */       } 
/* 4547 */       return eGenericType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isIdentifierStart(int codePoint) {
/* 4557 */       return Character.isJavaIdentifierStart(codePoint);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isIdentifierPart(int codePoint) {
/* 4567 */       return Character.isJavaIdentifierPart(codePoint);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<EGenericType> handleTypeArguments(char[] instanceTypeName, int start, int end, DiagnosticChain diagnostics) {
/* 4582 */       List<EGenericType> result = new ArrayList<EGenericType>();
/* 4583 */       int depth = 0;
/* 4584 */       int typeArgumentStart = start;
/* 4585 */       for (int i = start; i < end; i = Character.offsetByCodePoints(instanceTypeName, 0, instanceTypeName.length, i, 1)) {
/*      */         
/* 4587 */         int codePoint = Character.codePointAt(instanceTypeName, i);
/* 4588 */         switch (codePoint) {
/*      */ 
/*      */           
/*      */           case 60:
/* 4592 */             depth++;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 62:
/* 4597 */             depth--;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 44:
/* 4602 */             if (depth == 0) {
/*      */               
/* 4604 */               result.add(handleTypeArgument(instanceTypeName, typeArgumentStart, i, diagnostics));
/* 4605 */               typeArgumentStart = i + 1;
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 4611 */             if (typeArgumentStart == -1)
/*      */             {
/* 4613 */               typeArgumentStart = i;
/*      */             }
/*      */             break;
/*      */         } 
/*      */       
/*      */       } 
/* 4619 */       result.add(handleTypeArgument(instanceTypeName, typeArgumentStart, end, diagnostics));
/* 4620 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected EGenericType handleTypeArgument(char[] instanceTypeName, int start, int end, DiagnosticChain diagnostics) {
/* 4639 */       EGenericType eGenericType = null;
/* 4640 */       int firstNonWhiteSpaceIndex = start;
/*      */       
/* 4642 */       for (int i = start; i < end; i = Character.offsetByCodePoints(instanceTypeName, 0, instanceTypeName.length, i, 1)) {
/*      */         
/* 4644 */         int codePoint = Character.codePointAt(instanceTypeName, i);
/* 4645 */         switch (codePoint) {
/*      */ 
/*      */           
/*      */           case 63:
/* 4649 */             if (eGenericType == null) {
/*      */               
/* 4651 */               eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 4656 */             report(
/* 4657 */                 diagnostics, 
/* 4658 */                 "_UI_EClassifierInstanceTypeNameTooManyQuestionMarks_diagnostic", 
/* 4659 */                 new Object[] { Integer.valueOf(i)
/* 4660 */                 }, i);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 101:
/* 4666 */             if (eGenericType != null) {
/*      */               
/* 4668 */               if (i + 7 < end && 
/* 4669 */                 instanceTypeName[i + 1] == 'x' && 
/* 4670 */                 instanceTypeName[i + 2] == 't' && 
/* 4671 */                 instanceTypeName[i + 3] == 'e' && 
/* 4672 */                 instanceTypeName[i + 4] == 'n' && 
/* 4673 */                 instanceTypeName[i + 5] == 'd' && 
/* 4674 */                 instanceTypeName[i + 6] == 's' && 
/* 4675 */                 Character.isWhitespace(Character.codePointAt(instanceTypeName, i + 7))) {
/*      */                 
/* 4677 */                 EGenericType eUpperBound = 
/* 4678 */                   handleInstanceTypeName(
/* 4679 */                     instanceTypeName, Character.offsetByCodePoints(instanceTypeName, 0, instanceTypeName.length, i + 6, 1), end, diagnostics);
/* 4680 */                 eGenericType.setEUpperBound(eUpperBound);
/*      */                 
/*      */                 break;
/*      */               } 
/* 4684 */               report(
/* 4685 */                   diagnostics, 
/* 4686 */                   "_UI_EClassifierInstanceTypeNameExpectingExtends_diagnostic", 
/* 4687 */                   new Object[] { Integer.valueOf(i)
/* 4688 */                   }, i);
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 4693 */             eGenericType = handleInstanceTypeName(instanceTypeName, start, end, diagnostics);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 115:
/* 4699 */             if (eGenericType != null) {
/*      */               
/* 4701 */               if (i + 5 < end && 
/* 4702 */                 instanceTypeName[i + 1] == 'u' && 
/* 4703 */                 instanceTypeName[i + 2] == 'p' && 
/* 4704 */                 instanceTypeName[i + 3] == 'e' && 
/* 4705 */                 instanceTypeName[i + 4] == 'r' && 
/* 4706 */                 Character.isWhitespace(Character.codePointAt(instanceTypeName, i + 5))) {
/*      */                 
/* 4708 */                 EGenericType eLowerBound = 
/* 4709 */                   handleInstanceTypeName(
/* 4710 */                     instanceTypeName, Character.offsetByCodePoints(instanceTypeName, 0, instanceTypeName.length, i + 4, 1), end, diagnostics);
/* 4711 */                 eGenericType.setELowerBound(eLowerBound);
/*      */                 
/*      */                 break;
/*      */               } 
/* 4715 */               report(
/* 4716 */                   diagnostics, 
/* 4717 */                   "_UI_EClassifierInstanceTypeNameExpectingSuper_diagnostic", 
/* 4718 */                   new Object[] { Integer.valueOf(i)
/* 4719 */                   }, i);
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 4724 */             eGenericType = handleInstanceTypeName(instanceTypeName, start, end, diagnostics);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 4730 */             if (Character.isWhitespace(codePoint)) {
/*      */               break;
/*      */             }
/*      */             
/* 4734 */             if (eGenericType != null) {
/*      */               
/* 4736 */               report(
/* 4737 */                   diagnostics, 
/* 4738 */                   "_UI_EClassifierInstanceTypeNameExpectingExtendsOrSuper_diagnostic", 
/* 4739 */                   new Object[] { Integer.valueOf(i)
/* 4740 */                   }, i);
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 4745 */             firstNonWhiteSpaceIndex = i;
/* 4746 */             eGenericType = handleInstanceTypeName(instanceTypeName, i, end, diagnostics);
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 4752 */       if (eGenericType == null) {
/*      */         
/* 4754 */         eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
/* 4755 */         report(
/* 4756 */             diagnostics, 
/* 4757 */             "_UI_EClassifierInstanceTypeNameTypeArgumentExpected_diagnostic", 
/* 4758 */             new Object[] { Integer.valueOf(firstNonWhiteSpaceIndex)
/* 4759 */             }, firstNonWhiteSpaceIndex);
/*      */       } 
/* 4761 */       return eGenericType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected List<ETypeParameter> handleTypeParameters(char[] typeParameters, int start, int end, DiagnosticChain diagnostics) {
/* 4776 */       List<ETypeParameter> result = new ArrayList<ETypeParameter>();
/* 4777 */       int depth = 0;
/* 4778 */       int typeArgumentStart = -1;
/* 4779 */       for (int i = start; i < end; i = Character.offsetByCodePoints(typeParameters, 0, typeParameters.length, i, 1)) {
/*      */         
/* 4781 */         int codePoint = Character.codePointAt(typeParameters, i);
/* 4782 */         switch (codePoint) {
/*      */ 
/*      */           
/*      */           case 60:
/* 4786 */             depth++;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 62:
/* 4791 */             if (--depth == 0)
/*      */             {
/* 4793 */               result.add(handleTypeParameter(typeParameters, typeArgumentStart, i, diagnostics));
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 44:
/* 4799 */             if (depth == 1) {
/*      */               
/* 4801 */               result.add(handleTypeParameter(typeParameters, typeArgumentStart, i, diagnostics));
/* 4802 */               typeArgumentStart = i + 1;
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 4808 */             if (typeArgumentStart == -1)
/*      */             {
/* 4810 */               typeArgumentStart = i;
/*      */             }
/*      */             break;
/*      */         } 
/*      */       
/*      */       } 
/* 4816 */       if (depth != 0)
/*      */       {
/* 4818 */         report(
/* 4819 */             diagnostics, 
/* 4820 */             "_UI_EClassifierInstanceTypeNameUnterminatedAngleBracket_diagnostic", 
/* 4821 */             new Object[] { Integer.valueOf(start)
/* 4822 */             }, start);
/*      */       }
/* 4824 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ETypeParameter handleTypeParameter(char[] typeParameters, int start, int end, DiagnosticChain diagnostics) {
/* 4842 */       ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
/* 4843 */       int identifierStart = -1;
/* 4844 */       int identifierLast = -1;
/* 4845 */       boolean identifierDone = false;
/*      */       
/* 4847 */       for (int i = start; i < end; i = Character.offsetByCodePoints(typeParameters, 0, typeParameters.length, i, 1)) {
/*      */         
/* 4849 */         int codePoint = Character.codePointAt(typeParameters, i);
/* 4850 */         if (Character.isWhitespace(codePoint)) {
/*      */           
/* 4852 */           if (identifierStart != -1)
/*      */           {
/* 4854 */             identifierDone = true;
/*      */           }
/*      */         } else {
/* 4857 */           if (identifierDone) {
/*      */             
/* 4859 */             if (codePoint == 101 && 
/* 4860 */               i + 7 < end && 
/* 4861 */               typeParameters[i + 1] == 'x' && 
/* 4862 */               typeParameters[i + 2] == 't' && 
/* 4863 */               typeParameters[i + 3] == 'e' && 
/* 4864 */               typeParameters[i + 4] == 'n' && 
/* 4865 */               typeParameters[i + 5] == 'd' && 
/* 4866 */               typeParameters[i + 6] == 's' && 
/* 4867 */               Character.isWhitespace(Character.codePointAt(typeParameters, i + 7))) {
/*      */               
/* 4869 */               i += 7;
/* 4870 */               int boundStart = i;
/* 4871 */               while (i < end) {
/*      */                 
/* 4873 */                 char character = typeParameters[i];
/* 4874 */                 if (character == '&') {
/*      */                   
/* 4876 */                   EGenericType eGenericType = handleInstanceTypeName(typeParameters, boundStart, i, diagnostics);
/* 4877 */                   eTypeParameter.getEBounds().add(eGenericType);
/* 4878 */                   boundStart = i + 1;
/*      */                 } 
/* 4880 */                 i++;
/*      */               } 
/* 4882 */               EGenericType eBound = handleInstanceTypeName(typeParameters, boundStart, i, diagnostics);
/* 4883 */               eTypeParameter.getEBounds().add(eBound);
/*      */               
/*      */               break;
/*      */             } 
/* 4887 */             report(
/* 4888 */                 diagnostics, 
/* 4889 */                 "_UI_EClassifierInstanceTypeNameExpectingExtends_diagnostic", 
/* 4890 */                 new Object[] { Integer.valueOf(i)
/* 4891 */                 }, i);
/*      */             
/*      */             break;
/*      */           } 
/* 4895 */           if ((identifierStart != -1) ? isIdentifierPart(codePoint) : isIdentifierStart(codePoint)) {
/*      */             
/* 4897 */             if (identifierStart == -1)
/*      */             {
/* 4899 */               identifierStart = i;
/*      */             }
/* 4901 */             identifierLast = i;
/*      */           }
/*      */           else {
/*      */             
/* 4905 */             report(
/* 4906 */                 diagnostics, 
/* 4907 */                 "_UI_EClassifierInstanceTypeNameUnexpectedCharacter_diagnostic", 
/* 4908 */                 new Object[] { Integer.valueOf(i), new String(Character.toChars(codePoint))
/* 4909 */                 }, i);
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 4914 */       if (identifierLast == -1) {
/*      */         
/* 4916 */         report(
/* 4917 */             diagnostics, 
/* 4918 */             "_UI_EClassifierInstanceTypeNameExpectingIdentifier_diagnostic", 
/* 4919 */             new Object[] { Integer.valueOf(end)
/* 4920 */             }, end);
/*      */       }
/*      */       else {
/*      */         
/* 4924 */         eTypeParameter.setName(new String(typeParameters, identifierStart, identifierLast - identifierStart + 1));
/*      */       } 
/* 4926 */       return eTypeParameter;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected BasicDiagnostic createDiagnostic(int severity, String source, int code, String messageKey, Object[] messageSubstitutions, Object[] data) {
/* 4945 */       String message = getString(messageKey, messageSubstitutions);
/* 4946 */       return new BasicDiagnostic(severity, source, code, message, data);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String getString(String key, Object[] substitutions) {
/* 4959 */       ResourceLocator resourceLocator = getResourceLocator();
/* 4960 */       return (substitutions == null) ? resourceLocator.getString(key) : resourceLocator.getString(key, substitutions);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ResourceLocator getResourceLocator() {
/* 4970 */       return (ResourceLocator)EcorePlugin.INSTANCE;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void report(DiagnosticChain diagnostics, String key, Object[] substitutions, int index, Map<Object, Object> context) {
/* 4985 */     if (diagnostics != null)
/*      */     {
/* 4987 */       diagnostics.add(
/* 4988 */           (Diagnostic)new BasicDiagnostic(
/* 4989 */             4, 
/* 4990 */             "org.eclipse.emf.ecore.model", 
/* 4991 */             42, 
/* 4992 */             getString(key, substitutions), 
/* 4993 */             new Object[] { Integer.valueOf(index) }));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EcoreValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */