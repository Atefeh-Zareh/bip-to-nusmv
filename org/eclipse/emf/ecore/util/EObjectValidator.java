/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.util.BasicDiagnostic;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.Diagnostic;
/*      */ import org.eclipse.emf.common.util.DiagnosticChain;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.common.util.ResourceLocator;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EModelElement;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EOperation;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EValidator;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
/*      */ public class EObjectValidator
/*      */   implements EValidator
/*      */ {
/*   65 */   public static final EObjectValidator INSTANCE = new EObjectValidator();
/*      */   
/*      */   public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore";
/*      */   
/*      */   public static final int EOBJECT__EVERY_MULTIPCITY_CONFORMS = 1;
/*      */   
/*      */   public static final int EOBJECT__EVERY_DATA_VALUE_CONFORMS = 2;
/*      */   
/*      */   public static final int EOBJECT__EVERY_REFERENCE_IS_CONTAINED = 3;
/*      */   
/*      */   public static final int EOBJECT__EVERY_PROXY_RESOLVES = 4;
/*      */   
/*      */   public static final int DATA_VALUE__VALUE_IN_RANGE = 5;
/*      */   
/*      */   public static final int DATA_VALUE__LENGTH_IN_RANGE = 6;
/*      */   
/*      */   public static final int DATA_VALUE__TYPE_CORRECT = 7;
/*      */   
/*      */   public static final int DATA_VALUE__VALUE_IN_ENUMERATION = 8;
/*      */   
/*      */   public static final int DATA_VALUE__MATCHES_PATTERN = 9;
/*      */   
/*      */   public static final int DATA_VALUE__TOTAL_DIGITS_IN_RANGE = 10;
/*      */   
/*      */   public static final int DATA_VALUE__FRACTION_DIGITS_IN_RANGE = 11;
/*      */   
/*      */   public static final int EOBJECT__UNIQUE_ID = 12;
/*      */   
/*      */   public static final int EOBJECT__EVERY_KEY_UNIQUE = 13;
/*      */   
/*      */   public static final int EOBJECT__EVERY_MAP_ENTRY_UNIQUE = 14;
/*      */   
/*      */   public static final int EOBJECT__NO_CIRCULAR_CONTAINMENT = 15;
/*      */   
/*      */   public static final int EOBJECT__EVERY_BIDIRECTIONAL_REFERENCE_IS_PAIRED = 16;
/*      */   static final int EOBJECT_DIAGNOSTIC_CODE_COUNT = 15;
/*      */   public static final String ROOT_OBJECT = "org.eclipse.emf.ecore.EObject_NoCircularContainment";
/*      */   
/*      */   public static String getObjectLabel(EObject eObject, Map<Object, Object> context) {
/*  104 */     if (context != null) {
/*      */       
/*  106 */       EValidator.SubstitutionLabelProvider substitutionlabelProvider = (EValidator.SubstitutionLabelProvider)context.get(EValidator.SubstitutionLabelProvider.class);
/*  107 */       if (substitutionlabelProvider != null)
/*      */       {
/*  109 */         return substitutionlabelProvider.getObjectLabel(eObject);
/*      */       }
/*      */     } 
/*  112 */     return EcoreUtil.getIdentification(eObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFeatureLabel(EStructuralFeature eStructuralFeature, Map<Object, Object> context) {
/*  120 */     if (context != null) {
/*      */       
/*  122 */       EValidator.SubstitutionLabelProvider substitutionlabelProvider = (EValidator.SubstitutionLabelProvider)context.get(EValidator.SubstitutionLabelProvider.class);
/*  123 */       if (substitutionlabelProvider != null)
/*      */       {
/*  125 */         return substitutionlabelProvider.getFeatureLabel(eStructuralFeature);
/*      */       }
/*      */     } 
/*  128 */     return eStructuralFeature.getName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getValueLabel(EDataType eDataType, Object value, Map<Object, Object> context) {
/*  136 */     if (context != null) {
/*      */       
/*  138 */       EValidator.SubstitutionLabelProvider substitutionlabelProvider = (EValidator.SubstitutionLabelProvider)context.get(EValidator.SubstitutionLabelProvider.class);
/*  139 */       if (substitutionlabelProvider != null)
/*      */       {
/*  141 */         return substitutionlabelProvider.getValueLabel(eDataType, value);
/*      */       }
/*      */     } 
/*  144 */     return EcoreUtil.convertToString(eDataType, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EPackage getEPackage() {
/*  154 */     return (EPackage)EcorePackage.eINSTANCE;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EValidator getRootEValidator(Map<Object, Object> context) {
/*  159 */     if (context != null) {
/*      */       
/*  161 */       EValidator result = (EValidator)context.get(EValidator.class);
/*  162 */       if (result != null)
/*      */       {
/*  164 */         return result;
/*      */       }
/*      */     } 
/*      */     
/*  168 */     return Diagnostician.INSTANCE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static EValidator.ValidationDelegate.Registry getValidationDelegateRegistry(Map<Object, Object> context) {
/*  176 */     if (context != null) {
/*      */       
/*  178 */       EValidator.ValidationDelegate.Registry result = (EValidator.ValidationDelegate.Registry)context.get(EValidator.ValidationDelegate.Registry.class);
/*  179 */       if (result != null)
/*      */       {
/*  181 */         return result;
/*      */       }
/*      */     } 
/*      */     
/*  185 */     return EValidator.ValidationDelegate.Registry.INSTANCE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, String validationDelegate, EOperation invariant, String expression, int severity, String source, int code) {
/*  195 */     EValidator.ValidationDelegate delegate = getValidationDelegateRegistry(context).getValidationDelegate(validationDelegate);
/*  196 */     if (delegate != null) {
/*      */ 
/*      */       
/*      */       try {
/*  200 */         if (!delegate.validate(eClass, eObject, context, invariant, expression))
/*      */         {
/*  202 */           if (diagnostics != null)
/*  203 */             reportInvariantDelegateViolation(eClass, eObject, diagnostics, context, invariant, severity, source, code); 
/*  204 */           return false;
/*      */         }
/*      */       
/*  207 */       } catch (Throwable throwable) {
/*      */         
/*  209 */         if (diagnostics != null) {
/*  210 */           reportInvariantDelegateException(eClass, eObject, diagnostics, context, invariant, severity, source, code, throwable);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  215 */     else if (diagnostics != null) {
/*  216 */       reportInvariantDelegateNotFound(eClass, eObject, diagnostics, context, invariant, severity, source, code, validationDelegate);
/*      */     } 
/*  218 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, String validationDelegate, String constraint, String expression, int severity, String source, int code) {
/*  228 */     EValidator.ValidationDelegate delegate = getValidationDelegateRegistry(context).getValidationDelegate(validationDelegate);
/*  229 */     if (delegate != null) {
/*      */ 
/*      */       
/*      */       try {
/*  233 */         if (!delegate.validate(eClass, eObject, context, constraint, expression))
/*      */         {
/*  235 */           if (diagnostics != null)
/*  236 */             reportConstraintDelegateViolation(eClass, eObject, diagnostics, context, constraint, severity, source, code); 
/*  237 */           return false;
/*      */         }
/*      */       
/*  240 */       } catch (Throwable throwable) {
/*      */         
/*  242 */         if (diagnostics != null) {
/*  243 */           reportConstraintDelegateException(eClass, eObject, diagnostics, context, constraint, severity, source, code, throwable);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  248 */     else if (diagnostics != null) {
/*  249 */       reportConstraintDelegateNotFound(eClass, eObject, diagnostics, context, constraint, severity, source, code, validationDelegate);
/*      */     } 
/*  251 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context, String validationDelegate, String constraint, String expression, int severity, String source, int code) {
/*  261 */     EValidator.ValidationDelegate delegate = getValidationDelegateRegistry(context).getValidationDelegate(validationDelegate);
/*  262 */     if (delegate != null) {
/*      */ 
/*      */       
/*      */       try {
/*  266 */         if (!delegate.validate(eDataType, value, context, constraint, expression))
/*      */         {
/*  268 */           if (diagnostics != null)
/*  269 */             reportConstraintDelegateViolation(eDataType, value, diagnostics, context, constraint, severity, source, code); 
/*  270 */           return false;
/*      */         }
/*      */       
/*  273 */       } catch (Throwable throwable) {
/*      */         
/*  275 */         if (diagnostics != null) {
/*  276 */           reportConstraintDelegateException(eDataType, value, diagnostics, context, constraint, severity, source, code, throwable);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  281 */     else if (diagnostics != null) {
/*  282 */       reportConstraintDelegateNotFound(eDataType, value, diagnostics, context, constraint, severity, source, code, validationDelegate);
/*      */     } 
/*  284 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  295 */     return validate(eObject.eClass(), eObject, diagnostics, context);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  300 */     if (eObject.eIsProxy()) {
/*      */       
/*  302 */       if (context != null && context.get("org.eclipse.emf.ecore.EObject_NoCircularContainment") != null) {
/*      */         
/*  304 */         if (diagnostics != null)
/*      */         {
/*  306 */           diagnostics.add(
/*  307 */               (Diagnostic)createDiagnostic(
/*  308 */                 4, 
/*  309 */                 "org.eclipse.emf.ecore", 
/*  310 */                 4, 
/*  311 */                 "_UI_UnresolvedProxy_diagnostic", 
/*      */                 
/*  313 */                 new Object[] {
/*  314 */                   getFeatureLabel((EStructuralFeature)eObject.eContainmentFeature(), context), 
/*  315 */                   getObjectLabel(eObject.eContainer(), context), 
/*  316 */                   getObjectLabel(eObject, context)
/*      */                 
/*  318 */                 }new Object[] { eObject.eContainer(), eObject.eContainmentFeature(), eObject
/*  319 */                 }, context));
/*      */         }
/*  321 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  325 */       return true;
/*      */     } 
/*      */     
/*  328 */     if (eClass.eContainer() == getEPackage())
/*      */     {
/*  330 */       return validate(eClass.getClassifierID(), eObject, diagnostics, context);
/*      */     }
/*      */ 
/*      */     
/*  334 */     return (
/*  335 */       new DynamicEClassValidator()
/*      */       {
/*      */ 
/*      */       
/*  339 */       }).validate(eClass, eObject, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean validate(int classifierID, Object object, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  345 */     return !(classifierID == 10 && !validate_EveryDefaultConstraint((EObject)object, diagnostics, context));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_EveryDefaultConstraint(EObject object, DiagnosticChain theDiagnostics, Map<Object, Object> context) {
/*  350 */     if (!validate_NoCircularContainment(object, theDiagnostics, context))
/*      */     {
/*  352 */       return false;
/*      */     }
/*  354 */     boolean result = validate_EveryMultiplicityConforms(object, theDiagnostics, context);
/*  355 */     if (result || theDiagnostics != null)
/*      */     {
/*  357 */       result &= validate_EveryProxyResolves(object, theDiagnostics, context);
/*      */     }
/*  359 */     if (result || theDiagnostics != null)
/*      */     {
/*  361 */       result &= validate_EveryReferenceIsContained(object, theDiagnostics, context);
/*      */     }
/*  363 */     if (result || theDiagnostics != null)
/*      */     {
/*  365 */       result &= validate_EveryBidirectionalReferenceIsPaired(object, theDiagnostics, context);
/*      */     }
/*  367 */     if (result || theDiagnostics != null)
/*      */     {
/*  369 */       result &= validate_EveryDataValueConforms(object, theDiagnostics, context);
/*      */     }
/*  371 */     if (result || theDiagnostics != null)
/*      */     {
/*  373 */       result &= validate_UniqueID(object, theDiagnostics, context);
/*      */     }
/*  375 */     if (result || theDiagnostics != null)
/*      */     {
/*  377 */       result &= validate_EveryKeyUnique(object, theDiagnostics, context);
/*      */     }
/*  379 */     if (result || theDiagnostics != null)
/*      */     {
/*  381 */       result &= validate_EveryMapEntryUnique(object, theDiagnostics, context);
/*      */     }
/*  383 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_NoCircularContainment(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  388 */     if (context != null) {
/*      */       
/*  390 */       Object root = context.get("org.eclipse.emf.ecore.EObject_NoCircularContainment");
/*  391 */       if (root == null) {
/*      */         
/*  393 */         context.put("org.eclipse.emf.ecore.EObject_NoCircularContainment", eObject);
/*      */       }
/*  395 */       else if (root == eObject) {
/*      */         
/*  397 */         if (diagnostics != null)
/*      */         {
/*  399 */           diagnostics.add(
/*  400 */               (Diagnostic)createDiagnostic(
/*  401 */                 4, 
/*  402 */                 "org.eclipse.emf.ecore", 
/*  403 */                 15, 
/*  404 */                 "_UI_CircularContainment_diagnostic", 
/*      */                 
/*  406 */                 new Object[] {
/*  407 */                   getObjectLabel(eObject, context)
/*      */                 
/*  409 */                 }new Object[] { eObject
/*  410 */                 }, context));
/*      */         }
/*  412 */         return false;
/*      */       } 
/*      */     } 
/*  415 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_EveryBidirectionalReferenceIsPaired(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  420 */     boolean result = true;
/*  421 */     for (EReference eReference : eObject.eClass().getEAllReferences()) {
/*      */       
/*  423 */       if (eReference.isResolveProxies()) {
/*      */         
/*  425 */         EReference eOpposite = eReference.getEOpposite();
/*  426 */         if (eOpposite != null) {
/*      */           
/*  428 */           result &= validate_BidirectionalReferenceIsPaired(eObject, eReference, eOpposite, diagnostics, context);
/*  429 */           if (!result && diagnostics == null)
/*      */           {
/*  431 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  436 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_BidirectionalReferenceIsPaired(EObject eObject, EReference eReference, EReference eOpposite, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  441 */     boolean result = true;
/*  442 */     Object value = eObject.eGet((EStructuralFeature)eReference);
/*  443 */     if (eReference.isMany()) {
/*      */ 
/*      */       
/*  446 */       List<EObject> values = (List<EObject>)value;
/*  447 */       if (eOpposite.isMany()) {
/*      */         
/*  449 */         for (EObject oppositeEObject : values) {
/*      */ 
/*      */           
/*  452 */           List<EObject> oppositeValues = (List<EObject>)oppositeEObject.eGet((EStructuralFeature)eOpposite);
/*  453 */           if (!oppositeValues.contains(eObject)) {
/*      */             
/*  455 */             result = false;
/*  456 */             if (diagnostics != null) {
/*      */ 
/*      */               
/*  459 */               diagnostics.add(
/*  460 */                   (Diagnostic)createDiagnostic(
/*  461 */                     4, 
/*  462 */                     "org.eclipse.emf.ecore", 
/*  463 */                     16, 
/*  464 */                     "_UI_UnpairedBidirectionalReference_diagnostic", 
/*      */                     
/*  466 */                     new Object[] {
/*  467 */                       getFeatureLabel((EStructuralFeature)eReference, context), 
/*  468 */                       getObjectLabel(eObject, context), 
/*  469 */                       getFeatureLabel((EStructuralFeature)eOpposite, context), 
/*  470 */                       getObjectLabel(oppositeEObject, context)
/*      */                     
/*  472 */                     }new Object[] { eObject, eReference, oppositeEObject, eOpposite
/*  473 */                     }, context));
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } else {
/*  484 */         for (EObject oppositeEObject : values) {
/*      */           
/*  486 */           if (oppositeEObject.eGet((EStructuralFeature)eOpposite) != eObject) {
/*      */             
/*  488 */             result = false;
/*  489 */             if (diagnostics != null) {
/*      */ 
/*      */               
/*  492 */               diagnostics.add(
/*  493 */                   (Diagnostic)createDiagnostic(
/*  494 */                     4, 
/*  495 */                     "org.eclipse.emf.ecore", 
/*  496 */                     16, 
/*  497 */                     "_UI_UnpairedBidirectionalReference_diagnostic", 
/*      */                     
/*  499 */                     new Object[] {
/*  500 */                       getFeatureLabel((EStructuralFeature)eReference, context), 
/*  501 */                       getObjectLabel(eObject, context), 
/*  502 */                       getFeatureLabel((EStructuralFeature)eOpposite, context), 
/*  503 */                       getObjectLabel(oppositeEObject, context)
/*      */                     
/*  505 */                     }new Object[] { eObject, eReference, oppositeEObject, eOpposite
/*  506 */                     }, context));
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  518 */       EObject oppositeEObject = (EObject)value;
/*  519 */       if (oppositeEObject != null)
/*      */       {
/*  521 */         if (eOpposite.isMany()) {
/*      */ 
/*      */           
/*  524 */           List<EObject> oppositeValues = (List<EObject>)oppositeEObject.eGet((EStructuralFeature)eOpposite);
/*  525 */           if (!oppositeValues.contains(eObject))
/*      */           {
/*  527 */             result = false;
/*  528 */             if (diagnostics != null)
/*      */             {
/*      */               
/*  531 */               diagnostics.add(
/*  532 */                   (Diagnostic)createDiagnostic(
/*  533 */                     4, 
/*  534 */                     "org.eclipse.emf.ecore", 
/*  535 */                     16, 
/*  536 */                     "_UI_UnpairedBidirectionalReference_diagnostic", 
/*      */                     
/*  538 */                     new Object[] {
/*  539 */                       getFeatureLabel((EStructuralFeature)eReference, context), 
/*  540 */                       getObjectLabel(eObject, context), 
/*  541 */                       getFeatureLabel((EStructuralFeature)eOpposite, context), 
/*  542 */                       getObjectLabel(oppositeEObject, context)
/*      */                     
/*  544 */                     }new Object[] { eObject, eReference, oppositeEObject, eOpposite
/*  545 */                     }, context));
/*      */             
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*  551 */         else if (oppositeEObject.eGet((EStructuralFeature)eOpposite) != eObject) {
/*      */           
/*  553 */           result = false;
/*  554 */           if (diagnostics != null)
/*      */           {
/*  556 */             diagnostics.add(
/*  557 */                 (Diagnostic)createDiagnostic(
/*  558 */                   4, 
/*  559 */                   "org.eclipse.emf.ecore", 
/*  560 */                   16, 
/*  561 */                   "_UI_UnpairedBidirectionalReference_diagnostic", 
/*      */                   
/*  563 */                   new Object[] {
/*  564 */                     getFeatureLabel((EStructuralFeature)eReference, context), 
/*  565 */                     getObjectLabel(eObject, context), 
/*  566 */                     getFeatureLabel((EStructuralFeature)eOpposite, context), 
/*  567 */                     getObjectLabel(oppositeEObject, context)
/*      */                   
/*  569 */                   }new Object[] { eObject, eReference, oppositeEObject, eOpposite
/*  570 */                   }, context));
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  576 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_EveryMultiplicityConforms(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  581 */     boolean result = true;
/*  582 */     EClass eClass = eObject.eClass();
/*  583 */     for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */       
/*  585 */       result &= validate_MultiplicityConforms(eObject, eClass.getEStructuralFeature(i), diagnostics, context);
/*  586 */       if (!result && diagnostics == null)
/*      */       {
/*  588 */         return false;
/*      */       }
/*      */     } 
/*  591 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean validate_MultiplicityConforms(EObject eObject, EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  597 */     boolean result = true;
/*  598 */     if (eStructuralFeature.isMany()) {
/*      */       
/*  600 */       if (FeatureMapUtil.isFeatureMap(eStructuralFeature) && ExtendedMetaData.INSTANCE.isDocumentRoot(eObject.eClass())) {
/*      */         
/*  602 */         FeatureMap featureMap = (FeatureMap)eObject.eGet(eStructuralFeature);
/*  603 */         int count = 0;
/*  604 */         for (int i = 0, size = featureMap.size(); i < size; i++) {
/*      */           
/*  606 */           EStructuralFeature feature = featureMap.getEStructuralFeature(i);
/*  607 */           int kind = ExtendedMetaData.INSTANCE.getFeatureKind(feature);
/*  608 */           if (kind == 4 && 
/*  609 */             feature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA && 
/*  610 */             feature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT && 
/*  611 */             feature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT && 
/*  612 */             feature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION && 
/*  613 */             ++count > 1) {
/*      */             
/*  615 */             result = false;
/*      */             break;
/*      */           } 
/*      */         } 
/*  619 */         if (count != 1)
/*      */         {
/*  621 */           result = false;
/*  622 */           if (diagnostics != null)
/*      */           {
/*  624 */             diagnostics.add(
/*  625 */                 (Diagnostic)createDiagnostic(
/*  626 */                   4, 
/*  627 */                   "org.eclipse.emf.ecore", 
/*  628 */                   1, 
/*  629 */                   "_UI_DocumentRootMustHaveOneElement_diagnostic", 
/*      */                   
/*  631 */                   new Object[] {
/*  632 */                     getFeatureLabel(eStructuralFeature, context), 
/*  633 */                     getObjectLabel(eObject, context), 
/*  634 */                     Integer.valueOf(count)
/*      */                   
/*  636 */                   }new Object[] { eObject, eStructuralFeature
/*  637 */                   }, context));
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  643 */         int lowerBound = eStructuralFeature.getLowerBound();
/*  644 */         if (lowerBound > 0) {
/*      */           
/*  646 */           int size = ((List)eObject.eGet(eStructuralFeature)).size();
/*  647 */           if (size < lowerBound) {
/*      */             
/*  649 */             result = false;
/*  650 */             if (diagnostics != null)
/*      */             {
/*  652 */               diagnostics.add(
/*  653 */                   (Diagnostic)createDiagnostic(
/*  654 */                     4, 
/*  655 */                     "org.eclipse.emf.ecore", 
/*  656 */                     1, 
/*  657 */                     "_UI_FeatureHasTooFewValues_diagnostic", 
/*      */                     
/*  659 */                     new Object[] {
/*  660 */                       getFeatureLabel(eStructuralFeature, context), 
/*  661 */                       getObjectLabel(eObject, context), 
/*  662 */                       Integer.valueOf(size), 
/*  663 */                       Integer.valueOf(lowerBound)
/*      */                     
/*  665 */                     }new Object[] { eObject, eStructuralFeature
/*  666 */                     }, context));
/*      */             }
/*      */           } 
/*  669 */           int upperBound = eStructuralFeature.getUpperBound();
/*  670 */           if (upperBound > 0 && size > upperBound)
/*      */           {
/*  672 */             result = false;
/*  673 */             if (diagnostics != null)
/*      */             {
/*  675 */               diagnostics.add(
/*  676 */                   (Diagnostic)createDiagnostic(
/*  677 */                     4, 
/*  678 */                     "org.eclipse.emf.ecore", 
/*  679 */                     1, 
/*  680 */                     "_UI_FeatureHasTooManyValues_diagnostic", 
/*      */                     
/*  682 */                     new Object[] {
/*  683 */                       getFeatureLabel(eStructuralFeature, context), 
/*  684 */                       getObjectLabel(eObject, context), 
/*  685 */                       Integer.valueOf(size), 
/*  686 */                       Integer.valueOf(upperBound)
/*      */                     
/*  688 */                     }new Object[] { eObject, eStructuralFeature
/*  689 */                     }, context));
/*      */             }
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  695 */           int upperBound = eStructuralFeature.getUpperBound();
/*  696 */           if (upperBound > 0) {
/*      */             
/*  698 */             int size = ((List)eObject.eGet(eStructuralFeature)).size();
/*  699 */             if (size > upperBound)
/*      */             {
/*  701 */               result = false;
/*  702 */               if (diagnostics != null)
/*      */               {
/*  704 */                 diagnostics.add(
/*  705 */                     (Diagnostic)createDiagnostic(
/*  706 */                       4, 
/*  707 */                       "org.eclipse.emf.ecore", 
/*  708 */                       1, 
/*  709 */                       "_UI_FeatureHasTooManyValues_diagnostic", 
/*      */                       
/*  711 */                       new Object[] {
/*  712 */                         getFeatureLabel(eStructuralFeature, context), 
/*  713 */                         getObjectLabel(eObject, context), 
/*  714 */                         Integer.valueOf(size), 
/*  715 */                         Integer.valueOf(upperBound)
/*      */                       
/*  717 */                       }new Object[] { eObject, eStructuralFeature
/*  718 */                       }, context));
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       } 
/*  725 */     } else if (eStructuralFeature.isRequired()) {
/*      */       
/*  727 */       if (eStructuralFeature.isUnsettable() ? !eObject.eIsSet(eStructuralFeature) : (eObject.eGet(eStructuralFeature, false) == null)) {
/*      */         
/*  729 */         result = false;
/*  730 */         if (diagnostics != null)
/*      */         {
/*  732 */           diagnostics.add(
/*  733 */               (Diagnostic)createDiagnostic(
/*  734 */                 4, 
/*  735 */                 "org.eclipse.emf.ecore", 
/*  736 */                 1, 
/*  737 */                 "_UI_RequiredFeatureMustBeSet_diagnostic", 
/*  738 */                 new Object[] { getFeatureLabel(eStructuralFeature, context), getObjectLabel(eObject, context)
/*  739 */                 }new Object[] { eObject, eStructuralFeature
/*  740 */                 }, context));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  745 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_EveryProxyResolves(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  750 */     boolean result = true;
/*  751 */     for (EContentsEList.FeatureIterator<EObject> i = (EContentsEList.FeatureIterator<EObject>)eObject.eCrossReferences().iterator(); i.hasNext(); ) {
/*      */       
/*  753 */       EObject eCrossReferenceObject = i.next();
/*  754 */       if (eCrossReferenceObject.eIsProxy()) {
/*      */         
/*  756 */         result = false;
/*  757 */         if (diagnostics != null) {
/*      */           
/*  759 */           diagnostics.add(
/*  760 */               (Diagnostic)createDiagnostic(
/*  761 */                 4, 
/*  762 */                 "org.eclipse.emf.ecore", 
/*  763 */                 4, 
/*  764 */                 "_UI_UnresolvedProxy_diagnostic", 
/*      */                 
/*  766 */                 new Object[] {
/*  767 */                   getFeatureLabel(i.feature(), context), 
/*  768 */                   getObjectLabel(eObject, context), 
/*  769 */                   getObjectLabel(eCrossReferenceObject, context)
/*      */                 
/*  771 */                 }new Object[] { eObject, i.feature(), eCrossReferenceObject
/*  772 */                 }, context));
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  780 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_EveryReferenceIsContained(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  785 */     boolean result = true;
/*  786 */     if (eObject.eResource() != null)
/*      */     {
/*  788 */       for (EContentsEList.FeatureIterator<EObject> i = (EContentsEList.FeatureIterator<EObject>)eObject.eCrossReferences().iterator(); i.hasNext(); ) {
/*      */         
/*  790 */         EObject eCrossReferenceObject = i.next();
/*  791 */         if (eCrossReferenceObject.eResource() == null && !eCrossReferenceObject.eIsProxy() && !i.feature().isTransient()) {
/*      */           
/*  793 */           result = false;
/*  794 */           if (diagnostics != null) {
/*      */             
/*  796 */             diagnostics.add(
/*  797 */                 (Diagnostic)createDiagnostic(
/*  798 */                   4, 
/*  799 */                   "org.eclipse.emf.ecore", 
/*  800 */                   3, 
/*  801 */                   "_UI_DanglingReference_diagnostic", 
/*      */                   
/*  803 */                   new Object[] {
/*  804 */                     getFeatureLabel(i.feature(), context), 
/*  805 */                     getObjectLabel(eObject, context), 
/*  806 */                     getObjectLabel(eCrossReferenceObject, context)
/*      */                   
/*  808 */                   }new Object[] { eObject, i.feature(), eCrossReferenceObject
/*  809 */                   }, context));
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*  818 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_EveryDataValueConforms(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  823 */     boolean result = true;
/*  824 */     for (EAttribute eAttribute : eObject.eClass().getEAllAttributes()) {
/*      */       
/*  826 */       result &= validate_DataValueConforms(eObject, eAttribute, diagnostics, context);
/*  827 */       if (!result && diagnostics == null)
/*      */       {
/*  829 */         return false;
/*      */       }
/*      */     } 
/*  832 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean validate_DataValueConforms(EObject eObject, EAttribute eAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  838 */     if (!eObject.eIsSet((EStructuralFeature)eAttribute))
/*      */     {
/*  840 */       return true;
/*      */     }
/*  842 */     boolean result = true;
/*  843 */     EDataType eDataType = eAttribute.getEAttributeType();
/*  844 */     EValidator rootValidator = getRootEValidator(context);
/*  845 */     Object value = eObject.eGet((EStructuralFeature)eAttribute);
/*  846 */     if (FeatureMapUtil.isFeatureMap((EStructuralFeature)eAttribute)) {
/*      */       
/*  848 */       Collection<FeatureMap.Entry> featureMap = (Collection<FeatureMap.Entry>)value;
/*  849 */       EClass eClass = eObject.eClass();
/*  850 */       Map<EStructuralFeature, DiagnosticChain> entryFeatureToDiagnosticChainMap = null;
/*  851 */       for (Iterator<FeatureMap.Entry> i = featureMap.iterator(); i.hasNext() && (result || diagnostics != null); ) {
/*      */         
/*  853 */         FeatureMap.Entry entry = i.next();
/*  854 */         EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*  855 */         if (entryFeature instanceof EAttribute && 
/*  856 */           ExtendedMetaData.INSTANCE.getAffiliation(eClass, entryFeature) == eAttribute) {
/*      */           
/*  858 */           EDataType entryType = (EDataType)entryFeature.getEType();
/*  859 */           Object entryValue = entry.getValue();
/*  860 */           boolean entryIsValid = rootValidator.validate(entryType, entryValue, null, context);
/*  861 */           if (!entryIsValid) {
/*      */             
/*  863 */             result = false;
/*  864 */             if (diagnostics != null)
/*      */             {
/*  866 */               if (entryFeatureToDiagnosticChainMap == null)
/*      */               {
/*  868 */                 entryFeatureToDiagnosticChainMap = new HashMap<EStructuralFeature, DiagnosticChain>();
/*      */               }
/*  870 */               DiagnosticChain entryFeatureDiagnostic = entryFeatureToDiagnosticChainMap.get(entryFeature);
/*  871 */               if (entryFeatureDiagnostic == null) {
/*      */                 
/*  873 */                 entryFeatureDiagnostic = createBadDataValueDiagnostic(eObject, (EAttribute)entryFeature, diagnostics, context);
/*  874 */                 entryFeatureToDiagnosticChainMap.put(entryFeature, entryFeatureDiagnostic);
/*      */               } 
/*  876 */               rootValidator.validate(entryType, entryValue, entryFeatureDiagnostic, context);
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       } 
/*  882 */     } else if (eAttribute.isMany()) {
/*      */       
/*  884 */       for (Iterator<?> i = ((List)value).iterator(); i.hasNext() && result;)
/*      */       {
/*  886 */         result &= rootValidator.validate(eDataType, i.next(), null, context);
/*      */       }
/*      */       
/*  889 */       if (!result && diagnostics != null)
/*      */       {
/*  891 */         DiagnosticChain diagnostic = createBadDataValueDiagnostic(eObject, eAttribute, diagnostics, context);
/*  892 */         for (Iterator<?> iterator = ((List)value).iterator(); iterator.hasNext();)
/*      */         {
/*  894 */           rootValidator.validate(eDataType, iterator.next(), diagnostic, context);
/*      */         }
/*      */       }
/*      */     
/*  898 */     } else if (value != null) {
/*      */       
/*  900 */       result = rootValidator.validate(eDataType, value, null, context);
/*  901 */       if (!result && diagnostics != null) {
/*      */         
/*  903 */         DiagnosticChain diagnostic = createBadDataValueDiagnostic(eObject, eAttribute, diagnostics, context);
/*  904 */         rootValidator.validate(eDataType, value, diagnostic, context);
/*      */       } 
/*      */     } 
/*      */     
/*  908 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected DiagnosticChain createBadDataValueDiagnostic(EObject eObject, EAttribute eAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  914 */     BasicDiagnostic diagnostic = 
/*  915 */       createDiagnostic(
/*  916 */         4, 
/*  917 */         "org.eclipse.emf.ecore", 
/*  918 */         2, 
/*  919 */         "_UI_BadDataValue_diagnostic", 
/*      */         
/*  921 */         new Object[] {
/*  922 */           getFeatureLabel((EStructuralFeature)eAttribute, context), 
/*  923 */           getObjectLabel(eObject, context)
/*      */         
/*  925 */         }new Object[] { eObject, eAttribute
/*  926 */         }, context);
/*  927 */     diagnostics.add((Diagnostic)diagnostic);
/*  928 */     return (DiagnosticChain)diagnostic;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean validatePattern(EDataType eDataType, Object value, EValidator.PatternMatcher[][] patterns, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  934 */     String literal = EcoreUtil.convertToString(eDataType, value);
/*  935 */     for (int i = 0; i < patterns.length; i++) {
/*      */       
/*  937 */       EValidator.PatternMatcher[] children = patterns[i];
/*  938 */       boolean matches = false;
/*  939 */       for (int j = 0; j < children.length; j++) {
/*      */         
/*  941 */         if (children[j].matches(literal)) {
/*      */           
/*  943 */           matches = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  947 */       if (!matches) {
/*      */         
/*  949 */         if (diagnostics != null)
/*      */         {
/*  951 */           reportDataValuePatternViolation(eDataType, value, children, diagnostics, context);
/*      */         }
/*  953 */         return false;
/*      */       } 
/*      */     } 
/*  956 */     return true;
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
/*      */   public class DynamicEDataTypeValidator
/*      */   {
/*      */     protected List<Object> effectiveEnumeration;
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
/*      */     protected EValidator.PatternMatcher[][] effectivePattern;
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
/*      */     protected int effectiveTotalDigits;
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
/*      */     protected int effectiveFractionDigits;
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
/*      */     protected int effectiveMinLength;
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
/*      */     protected int effectiveMaxLength;
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
/*      */     protected Object effectiveMin;
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
/*      */     protected boolean effectiveMinIsInclusive;
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
/*      */     protected int effectiveTotalDigitsMin;
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
/*      */     protected Object effectiveMax;
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
/*      */     protected boolean effectiveMaxIsInclusive;
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
/*      */     protected int effectiveTotalDigitsMax;
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
/*      */     protected EDataType builtinType;
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
/*      */     protected EDataType itemType;
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
/*      */     protected List<EDataType> memberTypes;
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
/*      */     public DynamicEDataTypeValidator(EObjectValidator this$0, EDataType eDataType) {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: putfield this$0 : Lorg/eclipse/emf/ecore/util/EObjectValidator;
/*      */       //   5: aload_0
/*      */       //   6: invokespecial <init> : ()V
/*      */       //   9: aload_0
/*      */       //   10: iconst_m1
/*      */       //   11: putfield effectiveTotalDigits : I
/*      */       //   14: aload_0
/*      */       //   15: iconst_m1
/*      */       //   16: putfield effectiveFractionDigits : I
/*      */       //   19: aload_0
/*      */       //   20: iconst_m1
/*      */       //   21: putfield effectiveMinLength : I
/*      */       //   24: aload_0
/*      */       //   25: iconst_m1
/*      */       //   26: putfield effectiveMaxLength : I
/*      */       //   29: aload_0
/*      */       //   30: iconst_m1
/*      */       //   31: putfield effectiveTotalDigitsMin : I
/*      */       //   34: aload_0
/*      */       //   35: iconst_m1
/*      */       //   36: putfield effectiveTotalDigitsMax : I
/*      */       //   39: getstatic org/eclipse/emf/ecore/util/ExtendedMetaData.INSTANCE : Lorg/eclipse/emf/ecore/util/ExtendedMetaData;
/*      */       //   42: astore_3
/*      */       //   43: aload_2
/*      */       //   44: invokeinterface eResource : ()Lorg/eclipse/emf/ecore/resource/Resource;
/*      */       //   49: astore #4
/*      */       //   51: aload #4
/*      */       //   53: ifnull -> 85
/*      */       //   56: aload #4
/*      */       //   58: invokeinterface getResourceSet : ()Lorg/eclipse/emf/ecore/resource/ResourceSet;
/*      */       //   63: astore #5
/*      */       //   65: aload #5
/*      */       //   67: ifnull -> 85
/*      */       //   70: new org/eclipse/emf/ecore/util/BasicExtendedMetaData
/*      */       //   73: dup
/*      */       //   74: aload #5
/*      */       //   76: invokeinterface getPackageRegistry : ()Lorg/eclipse/emf/ecore/EPackage$Registry;
/*      */       //   81: invokespecial <init> : (Lorg/eclipse/emf/ecore/EPackage$Registry;)V
/*      */       //   84: astore_3
/*      */       //   85: aconst_null
/*      */       //   86: astore #5
/*      */       //   88: aload_0
/*      */       //   89: getfield effectiveEnumeration : Ljava/util/List;
/*      */       //   92: ifnonnull -> 175
/*      */       //   95: aload_3
/*      */       //   96: aload_2
/*      */       //   97: invokeinterface getEnumerationFacet : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/util/List;
/*      */       //   102: astore #6
/*      */       //   104: aload #6
/*      */       //   106: invokeinterface isEmpty : ()Z
/*      */       //   111: ifne -> 175
/*      */       //   114: aload_0
/*      */       //   115: new java/util/ArrayList
/*      */       //   118: dup
/*      */       //   119: invokespecial <init> : ()V
/*      */       //   122: putfield effectiveEnumeration : Ljava/util/List;
/*      */       //   125: aload #6
/*      */       //   127: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */       //   132: astore #8
/*      */       //   134: goto -> 165
/*      */       //   137: aload #8
/*      */       //   139: invokeinterface next : ()Ljava/lang/Object;
/*      */       //   144: checkcast java/lang/String
/*      */       //   147: astore #7
/*      */       //   149: aload_0
/*      */       //   150: getfield effectiveEnumeration : Ljava/util/List;
/*      */       //   153: aload_2
/*      */       //   154: aload #7
/*      */       //   156: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   159: invokeinterface add : (Ljava/lang/Object;)Z
/*      */       //   164: pop
/*      */       //   165: aload #8
/*      */       //   167: invokeinterface hasNext : ()Z
/*      */       //   172: ifne -> 137
/*      */       //   175: aload_3
/*      */       //   176: aload_2
/*      */       //   177: invokeinterface getPatternFacet : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/util/List;
/*      */       //   182: astore #6
/*      */       //   184: aload #6
/*      */       //   186: invokeinterface isEmpty : ()Z
/*      */       //   191: ifne -> 279
/*      */       //   194: aload #5
/*      */       //   196: ifnonnull -> 208
/*      */       //   199: new java/util/ArrayList
/*      */       //   202: dup
/*      */       //   203: invokespecial <init> : ()V
/*      */       //   206: astore #5
/*      */       //   208: aload #6
/*      */       //   210: invokeinterface size : ()I
/*      */       //   215: anewarray org/eclipse/emf/ecore/EValidator$PatternMatcher
/*      */       //   218: astore #7
/*      */       //   220: aload #5
/*      */       //   222: aload #7
/*      */       //   224: invokeinterface add : (Ljava/lang/Object;)Z
/*      */       //   229: pop
/*      */       //   230: aload #6
/*      */       //   232: invokeinterface listIterator : ()Ljava/util/ListIterator;
/*      */       //   237: astore #8
/*      */       //   239: goto -> 269
/*      */       //   242: aload #8
/*      */       //   244: invokeinterface next : ()Ljava/lang/Object;
/*      */       //   249: checkcast java/lang/String
/*      */       //   252: invokestatic createPatternMatcher : (Ljava/lang/String;)Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;
/*      */       //   255: astore #9
/*      */       //   257: aload #7
/*      */       //   259: aload #8
/*      */       //   261: invokeinterface previousIndex : ()I
/*      */       //   266: aload #9
/*      */       //   268: aastore
/*      */       //   269: aload #8
/*      */       //   271: invokeinterface hasNext : ()Z
/*      */       //   276: ifne -> 242
/*      */       //   279: aload_0
/*      */       //   280: getfield effectiveTotalDigits : I
/*      */       //   283: iconst_m1
/*      */       //   284: if_icmpne -> 298
/*      */       //   287: aload_0
/*      */       //   288: aload_3
/*      */       //   289: aload_2
/*      */       //   290: invokeinterface getTotalDigitsFacet : (Lorg/eclipse/emf/ecore/EDataType;)I
/*      */       //   295: putfield effectiveTotalDigits : I
/*      */       //   298: aload_0
/*      */       //   299: getfield effectiveFractionDigits : I
/*      */       //   302: iconst_m1
/*      */       //   303: if_icmpne -> 317
/*      */       //   306: aload_0
/*      */       //   307: aload_3
/*      */       //   308: aload_2
/*      */       //   309: invokeinterface getFractionDigitsFacet : (Lorg/eclipse/emf/ecore/EDataType;)I
/*      */       //   314: putfield effectiveFractionDigits : I
/*      */       //   317: aload_0
/*      */       //   318: getfield effectiveMinLength : I
/*      */       //   321: iconst_m1
/*      */       //   322: if_icmpne -> 355
/*      */       //   325: aload_0
/*      */       //   326: aload_3
/*      */       //   327: aload_2
/*      */       //   328: invokeinterface getLengthFacet : (Lorg/eclipse/emf/ecore/EDataType;)I
/*      */       //   333: putfield effectiveMinLength : I
/*      */       //   336: aload_0
/*      */       //   337: getfield effectiveMinLength : I
/*      */       //   340: iconst_m1
/*      */       //   341: if_icmpne -> 355
/*      */       //   344: aload_0
/*      */       //   345: aload_3
/*      */       //   346: aload_2
/*      */       //   347: invokeinterface getMinLengthFacet : (Lorg/eclipse/emf/ecore/EDataType;)I
/*      */       //   352: putfield effectiveMinLength : I
/*      */       //   355: aload_0
/*      */       //   356: getfield effectiveMaxLength : I
/*      */       //   359: iconst_m1
/*      */       //   360: if_icmpne -> 393
/*      */       //   363: aload_0
/*      */       //   364: aload_3
/*      */       //   365: aload_2
/*      */       //   366: invokeinterface getLengthFacet : (Lorg/eclipse/emf/ecore/EDataType;)I
/*      */       //   371: putfield effectiveMaxLength : I
/*      */       //   374: aload_0
/*      */       //   375: getfield effectiveMaxLength : I
/*      */       //   378: iconst_m1
/*      */       //   379: if_icmpne -> 393
/*      */       //   382: aload_0
/*      */       //   383: aload_3
/*      */       //   384: aload_2
/*      */       //   385: invokeinterface getMaxLengthFacet : (Lorg/eclipse/emf/ecore/EDataType;)I
/*      */       //   390: putfield effectiveMaxLength : I
/*      */       //   393: aload_0
/*      */       //   394: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   397: ifnonnull -> 479
/*      */       //   400: aload_0
/*      */       //   401: aload_3
/*      */       //   402: aload_2
/*      */       //   403: invokeinterface getMinExclusiveFacet : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/lang/String;
/*      */       //   408: putfield effectiveMin : Ljava/lang/Object;
/*      */       //   411: aload_0
/*      */       //   412: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   415: ifnonnull -> 459
/*      */       //   418: aload_0
/*      */       //   419: aload_3
/*      */       //   420: aload_2
/*      */       //   421: invokeinterface getMinInclusiveFacet : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/lang/String;
/*      */       //   426: putfield effectiveMin : Ljava/lang/Object;
/*      */       //   429: aload_0
/*      */       //   430: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   433: ifnull -> 479
/*      */       //   436: aload_0
/*      */       //   437: aload_2
/*      */       //   438: aload_0
/*      */       //   439: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   442: checkcast java/lang/String
/*      */       //   445: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   448: putfield effectiveMin : Ljava/lang/Object;
/*      */       //   451: aload_0
/*      */       //   452: iconst_1
/*      */       //   453: putfield effectiveMinIsInclusive : Z
/*      */       //   456: goto -> 479
/*      */       //   459: aload_0
/*      */       //   460: aload_2
/*      */       //   461: aload_0
/*      */       //   462: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   465: checkcast java/lang/String
/*      */       //   468: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   471: putfield effectiveMin : Ljava/lang/Object;
/*      */       //   474: aload_0
/*      */       //   475: iconst_0
/*      */       //   476: putfield effectiveMinIsInclusive : Z
/*      */       //   479: aload_0
/*      */       //   480: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   483: ifnonnull -> 565
/*      */       //   486: aload_0
/*      */       //   487: aload_3
/*      */       //   488: aload_2
/*      */       //   489: invokeinterface getMaxExclusiveFacet : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/lang/String;
/*      */       //   494: putfield effectiveMax : Ljava/lang/Object;
/*      */       //   497: aload_0
/*      */       //   498: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   501: ifnonnull -> 545
/*      */       //   504: aload_0
/*      */       //   505: aload_3
/*      */       //   506: aload_2
/*      */       //   507: invokeinterface getMaxInclusiveFacet : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/lang/String;
/*      */       //   512: putfield effectiveMax : Ljava/lang/Object;
/*      */       //   515: aload_0
/*      */       //   516: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   519: ifnull -> 565
/*      */       //   522: aload_0
/*      */       //   523: aload_2
/*      */       //   524: aload_0
/*      */       //   525: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   528: checkcast java/lang/String
/*      */       //   531: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   534: putfield effectiveMax : Ljava/lang/Object;
/*      */       //   537: aload_0
/*      */       //   538: iconst_1
/*      */       //   539: putfield effectiveMaxIsInclusive : Z
/*      */       //   542: goto -> 565
/*      */       //   545: aload_0
/*      */       //   546: aload_2
/*      */       //   547: aload_0
/*      */       //   548: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   551: checkcast java/lang/String
/*      */       //   554: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   557: putfield effectiveMax : Ljava/lang/Object;
/*      */       //   560: aload_0
/*      */       //   561: iconst_0
/*      */       //   562: putfield effectiveMaxIsInclusive : Z
/*      */       //   565: aload_3
/*      */       //   566: aload_2
/*      */       //   567: invokeinterface getBaseType : (Lorg/eclipse/emf/ecore/EDataType;)Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   572: astore #7
/*      */       //   574: aload #7
/*      */       //   576: ifnull -> 625
/*      */       //   579: aload #7
/*      */       //   581: astore_2
/*      */       //   582: aload_2
/*      */       //   583: invokeinterface getEPackage : ()Lorg/eclipse/emf/ecore/EPackage;
/*      */       //   588: getstatic org/eclipse/emf/ecore/xml/type/XMLTypePackage.eINSTANCE : Lorg/eclipse/emf/ecore/xml/type/XMLTypePackage;
/*      */       //   591: if_acmpne -> 88
/*      */       //   594: aload_2
/*      */       //   595: invokeinterface getInstanceClassName : ()Ljava/lang/String;
/*      */       //   600: ldc 'javax.xml.datatype.XMLGregorianCalendar'
/*      */       //   602: if_acmpne -> 88
/*      */       //   605: aload_0
/*      */       //   606: aload_2
/*      */       //   607: putfield builtinType : Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   610: aload_0
/*      */       //   611: aconst_null
/*      */       //   612: putfield itemType : Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   615: aload_0
/*      */       //   616: invokestatic emptyList : ()Ljava/util/List;
/*      */       //   619: putfield memberTypes : Ljava/util/List;
/*      */       //   622: goto -> 647
/*      */       //   625: aload_0
/*      */       //   626: aload_3
/*      */       //   627: aload_2
/*      */       //   628: invokeinterface getItemType : (Lorg/eclipse/emf/ecore/EDataType;)Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   633: putfield itemType : Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   636: aload_0
/*      */       //   637: aload_3
/*      */       //   638: aload_2
/*      */       //   639: invokeinterface getMemberTypes : (Lorg/eclipse/emf/ecore/EDataType;)Ljava/util/List;
/*      */       //   644: putfield memberTypes : Ljava/util/List;
/*      */       //   647: aload #5
/*      */       //   649: ifnull -> 678
/*      */       //   652: aload_0
/*      */       //   653: aload #5
/*      */       //   655: invokeinterface size : ()I
/*      */       //   660: anewarray [Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;
/*      */       //   663: putfield effectivePattern : [[Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;
/*      */       //   666: aload #5
/*      */       //   668: aload_0
/*      */       //   669: getfield effectivePattern : [[Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;
/*      */       //   672: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
/*      */       //   677: pop
/*      */       //   678: aload_0
/*      */       //   679: getfield effectiveTotalDigits : I
/*      */       //   682: iconst_m1
/*      */       //   683: if_icmpeq -> 949
/*      */       //   686: aload_2
/*      */       //   687: invokeinterface getInstanceClassName : ()Ljava/lang/String;
/*      */       //   692: ldc 'java.math.BigDecimal'
/*      */       //   694: if_acmpeq -> 949
/*      */       //   697: new java/lang/StringBuffer
/*      */       //   700: dup
/*      */       //   701: ldc '1'
/*      */       //   703: invokespecial <init> : (Ljava/lang/String;)V
/*      */       //   706: astore #6
/*      */       //   708: aload_0
/*      */       //   709: getfield effectiveTotalDigits : I
/*      */       //   712: istore #7
/*      */       //   714: goto -> 728
/*      */       //   717: aload #6
/*      */       //   719: ldc '0'
/*      */       //   721: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */       //   724: pop
/*      */       //   725: iinc #7, -1
/*      */       //   728: iload #7
/*      */       //   730: ifgt -> 717
/*      */       //   733: aload_2
/*      */       //   734: new java/lang/StringBuilder
/*      */       //   737: dup
/*      */       //   738: ldc '-'
/*      */       //   740: invokespecial <init> : (Ljava/lang/String;)V
/*      */       //   743: aload #6
/*      */       //   745: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   748: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   751: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   754: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   757: astore #7
/*      */       //   759: aload_0
/*      */       //   760: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   763: ifnull -> 814
/*      */       //   766: aload_0
/*      */       //   767: getfield effectiveMinIsInclusive : Z
/*      */       //   770: ifeq -> 793
/*      */       //   773: aload_0
/*      */       //   774: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   777: checkcast java/lang/Comparable
/*      */       //   780: aload #7
/*      */       //   782: invokeinterface compareTo : (Ljava/lang/Object;)I
/*      */       //   787: ifle -> 814
/*      */       //   790: goto -> 810
/*      */       //   793: aload_0
/*      */       //   794: getfield effectiveMin : Ljava/lang/Object;
/*      */       //   797: checkcast java/lang/Comparable
/*      */       //   800: aload #7
/*      */       //   802: invokeinterface compareTo : (Ljava/lang/Object;)I
/*      */       //   807: iflt -> 814
/*      */       //   810: iconst_0
/*      */       //   811: goto -> 815
/*      */       //   814: iconst_1
/*      */       //   815: istore #8
/*      */       //   817: iload #8
/*      */       //   819: ifeq -> 846
/*      */       //   822: aload_0
/*      */       //   823: iconst_0
/*      */       //   824: putfield effectiveMinIsInclusive : Z
/*      */       //   827: aload_0
/*      */       //   828: aload #7
/*      */       //   830: putfield effectiveMin : Ljava/lang/Object;
/*      */       //   833: aload_0
/*      */       //   834: aload_0
/*      */       //   835: getfield effectiveTotalDigits : I
/*      */       //   838: putfield effectiveTotalDigitsMin : I
/*      */       //   841: goto -> 846
/*      */       //   844: astore #7
/*      */       //   846: aload_2
/*      */       //   847: aload #6
/*      */       //   849: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   852: invokestatic createFromString : (Lorg/eclipse/emf/ecore/EDataType;Ljava/lang/String;)Ljava/lang/Object;
/*      */       //   855: astore #7
/*      */       //   857: aload_0
/*      */       //   858: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   861: ifnull -> 912
/*      */       //   864: aload_0
/*      */       //   865: getfield effectiveMaxIsInclusive : Z
/*      */       //   868: ifeq -> 891
/*      */       //   871: aload_0
/*      */       //   872: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   875: checkcast java/lang/Comparable
/*      */       //   878: aload #7
/*      */       //   880: invokeinterface compareTo : (Ljava/lang/Object;)I
/*      */       //   885: ifge -> 912
/*      */       //   888: goto -> 908
/*      */       //   891: aload_0
/*      */       //   892: getfield effectiveMax : Ljava/lang/Object;
/*      */       //   895: checkcast java/lang/Comparable
/*      */       //   898: aload #7
/*      */       //   900: invokeinterface compareTo : (Ljava/lang/Object;)I
/*      */       //   905: ifgt -> 912
/*      */       //   908: iconst_0
/*      */       //   909: goto -> 913
/*      */       //   912: iconst_1
/*      */       //   913: istore #8
/*      */       //   915: iload #8
/*      */       //   917: ifeq -> 944
/*      */       //   920: aload_0
/*      */       //   921: iconst_0
/*      */       //   922: putfield effectiveMaxIsInclusive : Z
/*      */       //   925: aload_0
/*      */       //   926: aload #7
/*      */       //   928: putfield effectiveMax : Ljava/lang/Object;
/*      */       //   931: aload_0
/*      */       //   932: aload_0
/*      */       //   933: getfield effectiveTotalDigits : I
/*      */       //   936: putfield effectiveTotalDigitsMax : I
/*      */       //   939: goto -> 944
/*      */       //   942: astore #7
/*      */       //   944: aload_0
/*      */       //   945: iconst_m1
/*      */       //   946: putfield effectiveTotalDigits : I
/*      */       //   949: aload_0
/*      */       //   950: getfield effectiveFractionDigits : I
/*      */       //   953: iconst_m1
/*      */       //   954: if_icmpeq -> 973
/*      */       //   957: aload_2
/*      */       //   958: invokeinterface getInstanceClassName : ()Ljava/lang/String;
/*      */       //   963: ldc 'java.math.BigDecimal'
/*      */       //   965: if_acmpeq -> 973
/*      */       //   968: aload_0
/*      */       //   969: iconst_m1
/*      */       //   970: putfield effectiveFractionDigits : I
/*      */       //   973: return
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1167	-> 0
/*      */       //   #977	-> 5
/*      */       //   #963	-> 9
/*      */       //   #964	-> 14
/*      */       //   #965	-> 19
/*      */       //   #966	-> 24
/*      */       //   #969	-> 29
/*      */       //   #972	-> 34
/*      */       //   #979	-> 39
/*      */       //   #980	-> 43
/*      */       //   #981	-> 51
/*      */       //   #983	-> 56
/*      */       //   #984	-> 65
/*      */       //   #986	-> 70
/*      */       //   #990	-> 85
/*      */       //   #994	-> 88
/*      */       //   #996	-> 95
/*      */       //   #997	-> 104
/*      */       //   #999	-> 114
/*      */       //   #1000	-> 125
/*      */       //   #1002	-> 149
/*      */       //   #1000	-> 165
/*      */       //   #1007	-> 175
/*      */       //   #1008	-> 184
/*      */       //   #1010	-> 194
/*      */       //   #1012	-> 199
/*      */       //   #1014	-> 208
/*      */       //   #1015	-> 220
/*      */       //   #1016	-> 230
/*      */       //   #1018	-> 242
/*      */       //   #1019	-> 257
/*      */       //   #1016	-> 269
/*      */       //   #1023	-> 279
/*      */       //   #1025	-> 287
/*      */       //   #1027	-> 298
/*      */       //   #1029	-> 306
/*      */       //   #1031	-> 317
/*      */       //   #1033	-> 325
/*      */       //   #1034	-> 336
/*      */       //   #1036	-> 344
/*      */       //   #1039	-> 355
/*      */       //   #1041	-> 363
/*      */       //   #1042	-> 374
/*      */       //   #1044	-> 382
/*      */       //   #1047	-> 393
/*      */       //   #1049	-> 400
/*      */       //   #1050	-> 411
/*      */       //   #1052	-> 418
/*      */       //   #1053	-> 429
/*      */       //   #1055	-> 436
/*      */       //   #1056	-> 451
/*      */       //   #1061	-> 459
/*      */       //   #1062	-> 474
/*      */       //   #1065	-> 479
/*      */       //   #1067	-> 486
/*      */       //   #1068	-> 497
/*      */       //   #1070	-> 504
/*      */       //   #1071	-> 515
/*      */       //   #1073	-> 522
/*      */       //   #1074	-> 537
/*      */       //   #1079	-> 545
/*      */       //   #1080	-> 560
/*      */       //   #1084	-> 565
/*      */       //   #1085	-> 574
/*      */       //   #1087	-> 579
/*      */       //   #1088	-> 582
/*      */       //   #1090	-> 605
/*      */       //   #1091	-> 610
/*      */       //   #1092	-> 615
/*      */       //   #1093	-> 622
/*      */       //   #1102	-> 625
/*      */       //   #1103	-> 636
/*      */       //   #1108	-> 647
/*      */       //   #1110	-> 652
/*      */       //   #1111	-> 666
/*      */       //   #1114	-> 678
/*      */       //   #1116	-> 697
/*      */       //   #1117	-> 708
/*      */       //   #1119	-> 717
/*      */       //   #1117	-> 725
/*      */       //   #1124	-> 733
/*      */       //   #1125	-> 759
/*      */       //   #1126	-> 766
/*      */       //   #1127	-> 773
/*      */       //   #1128	-> 793
/*      */       //   #1125	-> 815
/*      */       //   #1129	-> 817
/*      */       //   #1131	-> 822
/*      */       //   #1132	-> 827
/*      */       //   #1133	-> 833
/*      */       //   #1136	-> 844
/*      */       //   #1143	-> 846
/*      */       //   #1144	-> 857
/*      */       //   #1145	-> 864
/*      */       //   #1146	-> 871
/*      */       //   #1147	-> 891
/*      */       //   #1144	-> 913
/*      */       //   #1148	-> 915
/*      */       //   #1150	-> 920
/*      */       //   #1151	-> 925
/*      */       //   #1152	-> 931
/*      */       //   #1155	-> 942
/*      */       //   #1160	-> 944
/*      */       //   #1163	-> 949
/*      */       //   #1165	-> 968
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	974	0	this	Lorg/eclipse/emf/ecore/util/EObjectValidator$DynamicEDataTypeValidator;
/*      */       //   0	974	2	eDataType	Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   43	931	3	extendedMetaData	Lorg/eclipse/emf/ecore/util/ExtendedMetaData;
/*      */       //   51	923	4	resource	Lorg/eclipse/emf/ecore/resource/Resource;
/*      */       //   65	20	5	resourceSet	Lorg/eclipse/emf/ecore/resource/ResourceSet;
/*      */       //   88	886	5	patterns	Ljava/util/List;
/*      */       //   104	71	6	enumeration	Ljava/util/List;
/*      */       //   149	16	7	enumerator	Ljava/lang/String;
/*      */       //   184	463	6	pattern	Ljava/util/List;
/*      */       //   220	59	7	children	[Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;
/*      */       //   239	40	8	i	Ljava/util/ListIterator;
/*      */       //   257	12	9	patternMatcher	Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;
/*      */       //   574	73	7	baseType	Lorg/eclipse/emf/ecore/EDataType;
/*      */       //   708	241	6	digits	Ljava/lang/StringBuffer;
/*      */       //   714	19	7	i	I
/*      */       //   759	85	7	lowerBound	Ljava/lang/Object;
/*      */       //   817	27	8	lowerBounded	Z
/*      */       //   857	85	7	upperBound	Ljava/lang/Object;
/*      */       //   915	27	8	upperBounded	Z
/*      */       // Local variable type table:
/*      */       //   start	length	slot	name	signature
/*      */       //   88	886	5	patterns	Ljava/util/List<[Lorg/eclipse/emf/ecore/EValidator$PatternMatcher;>;
/*      */       //   104	71	6	enumeration	Ljava/util/List<Ljava/lang/String;>;
/*      */       //   184	463	6	pattern	Ljava/util/List<Ljava/lang/String;>;
/*      */       //   239	40	8	i	Ljava/util/ListIterator<Ljava/lang/String;>;
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   733	841	844	java/lang/NumberFormatException
/*      */       //   846	939	942	java/lang/NumberFormatException
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
/*      */     protected boolean validateDelegatedConstraints(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1171 */       boolean result = true;
/* 1172 */       List<String> validationDelegates = EcoreUtil.getValidationDelegates(eDataType.getEPackage());
/*      */       
/* 1174 */       if (!validationDelegates.isEmpty())
/*      */       {
/* 1176 */         label18: for (String constraint : EcoreUtil.getConstraints((EModelElement)eDataType)) {
/*      */           
/* 1178 */           for (String validationDelegate : validationDelegates) {
/*      */             
/* 1180 */             String expression = EcoreUtil.getAnnotation((EModelElement)eDataType, validationDelegate, constraint);
/* 1181 */             if (expression != null) {
/*      */               
/* 1183 */               result &= this.this$0.validate(eDataType, value, diagnostics, context, validationDelegate, constraint, expression, 4, "org.eclipse.emf.ecore", 0);
/* 1184 */               if (!result && diagnostics == null) {
/*      */                 break label18;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/* 1191 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean validateSchemaConstraints(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1196 */       boolean result = true;
/*      */       
/* 1198 */       if (this.effectiveEnumeration != null)
/*      */       {
/* 1200 */         if (!this.effectiveEnumeration.contains(value)) {
/*      */           
/* 1202 */           if (diagnostics != null)
/* 1203 */             this.this$0.reportEnumerationViolation(eDataType, value, this.effectiveEnumeration, diagnostics, context); 
/* 1204 */           result = false;
/*      */         } 
/*      */       }
/*      */       
/* 1208 */       if (this.effectivePattern != null)
/*      */       {
/* 1210 */         result = this.this$0.validatePattern(eDataType, value, this.effectivePattern, diagnostics, context);
/*      */       }
/*      */       
/* 1213 */       if (this.effectiveMin != null) {
/*      */ 
/*      */         
/* 1216 */         Comparable<Object> comparableObject = (Comparable<Object>)this.effectiveMin;
/* 1217 */         if (this.effectiveMinIsInclusive ? (comparableObject.compareTo(value) > 0) : (comparableObject.compareTo(value) >= 0)) {
/*      */           
/* 1219 */           if (diagnostics != null)
/*      */           {
/* 1221 */             if (this.effectiveTotalDigitsMin != -1) {
/*      */               
/* 1223 */               this.this$0.reportTotalDigitsViolation(eDataType, value, this.effectiveTotalDigitsMin, diagnostics, context);
/*      */             }
/*      */             else {
/*      */               
/* 1227 */               this.this$0.reportMinViolation(eDataType, value, this.effectiveMin, this.effectiveMinIsInclusive, diagnostics, context);
/*      */             } 
/*      */           }
/* 1230 */           result = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1234 */       if (this.effectiveMax != null) {
/*      */ 
/*      */         
/* 1237 */         Comparable<Object> comparableObject = (Comparable<Object>)this.effectiveMax;
/* 1238 */         if (this.effectiveMaxIsInclusive ? (comparableObject.compareTo(value) < 0) : (comparableObject.compareTo(value) <= 0)) {
/*      */           
/* 1240 */           if (diagnostics != null)
/*      */           {
/* 1242 */             if (this.effectiveTotalDigitsMax != -1) {
/*      */               
/* 1244 */               this.this$0.reportTotalDigitsViolation(eDataType, value, this.effectiveTotalDigitsMax, diagnostics, context);
/*      */             }
/*      */             else {
/*      */               
/* 1248 */               this.this$0.reportMaxViolation(eDataType, value, this.effectiveMax, this.effectiveMaxIsInclusive, diagnostics, context);
/*      */             } 
/*      */           }
/* 1251 */           result = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1255 */       if (this.effectiveMinLength != -1) {
/*      */         
/* 1257 */         int length = 
/* 1258 */           (value instanceof String) ? (
/* 1259 */           (String)value).length() : (
/* 1260 */           (value instanceof Collection) ? (
/* 1261 */           (Collection)value).size() : 
/* 1262 */           Array.getLength(value));
/* 1263 */         if (length < this.effectiveMinLength) {
/*      */           
/* 1265 */           if (diagnostics != null) this.this$0.reportMinLengthViolation(eDataType, value, length, this.effectiveMinLength, diagnostics, context); 
/* 1266 */           result = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1270 */       if (this.effectiveMaxLength != -1) {
/*      */         
/* 1272 */         int length = 
/* 1273 */           (value instanceof String) ? (
/* 1274 */           (String)value).length() : (
/* 1275 */           (value instanceof Collection) ? (
/* 1276 */           (Collection)value).size() : 
/* 1277 */           Array.getLength(value));
/* 1278 */         if (length > this.effectiveMaxLength) {
/*      */           
/* 1280 */           if (diagnostics != null) this.this$0.reportMaxLengthViolation(eDataType, value, length, this.effectiveMaxLength, diagnostics, context); 
/* 1281 */           result = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1285 */       if (this.effectiveTotalDigits != -1)
/*      */       {
/* 1287 */         if (value instanceof BigDecimal && ((BigDecimal)value).unscaledValue().abs().toString().length() > this.effectiveTotalDigits) {
/*      */           
/* 1289 */           if (diagnostics != null) this.this$0.reportTotalDigitsViolation(eDataType, value, this.effectiveTotalDigits, diagnostics, context); 
/* 1290 */           result = false;
/*      */         } 
/*      */       }
/*      */       
/* 1294 */       if (this.effectiveFractionDigits != -1)
/*      */       {
/* 1296 */         if (value instanceof BigDecimal && ((BigDecimal)value).scale() > this.effectiveFractionDigits) {
/*      */           
/* 1298 */           if (diagnostics != null) this.this$0.reportFractionDigitsViolation(eDataType, value, this.effectiveFractionDigits, diagnostics, context); 
/* 1299 */           result = false;
/*      */         } 
/*      */       }
/*      */       
/* 1303 */       if (this.builtinType != null) {
/*      */         
/* 1305 */         EValidator rootValidator = this.this$0.getRootEValidator(context);
/* 1306 */         result &= rootValidator.validate(this.builtinType, value, diagnostics, context);
/*      */       } 
/*      */       
/* 1309 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1314 */       boolean result = validateDelegatedConstraints(eDataType, value, diagnostics, context);
/*      */       
/* 1316 */       if (result || diagnostics != null) {
/*      */         
/* 1318 */         result &= validateSchemaConstraints(eDataType, value, diagnostics, context);
/*      */         
/* 1320 */         if (this.itemType != null) {
/*      */           
/* 1322 */           EValidator rootValidator = this.this$0.getRootEValidator(context);
/* 1323 */           for (Iterator<?> i = ((List)value).iterator(); i.hasNext() && (result || diagnostics != null);)
/*      */           {
/* 1325 */             result &= rootValidator.validate(this.itemType, i.next(), diagnostics, context);
/*      */           }
/* 1327 */           return result;
/*      */         } 
/* 1329 */         if (!this.memberTypes.isEmpty()) {
/*      */           
/* 1331 */           EValidator rootValidator = this.this$0.getRootEValidator(context);
/*      */           
/* 1333 */           for (EDataType memberType : this.memberTypes) {
/*      */             
/* 1335 */             if (rootValidator.validate(memberType, value, null, context))
/*      */             {
/* 1337 */               return true;
/*      */             }
/*      */           } 
/* 1340 */           for (EDataType memberType : this.memberTypes) {
/*      */             
/* 1342 */             if (memberType.isInstance(value))
/*      */             {
/* 1344 */               return rootValidator.validate(memberType, value, diagnostics, context);
/*      */             }
/*      */           } 
/* 1347 */           return false;
/*      */         } 
/*      */ 
/*      */         
/* 1351 */         return result;
/*      */       } 
/*      */ 
/*      */       
/* 1355 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class DynamicEClassValidator
/*      */   {
/*      */     protected boolean validateDelegatedInvariants(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1363 */       boolean result = true;
/* 1364 */       List<String> validationDelegates = EcoreUtil.getValidationDelegates(eClass.getEPackage());
/*      */       
/* 1366 */       if (!validationDelegates.isEmpty())
/*      */       {
/* 1368 */         label20: for (EOperation eOperation : eClass.getEOperations()) {
/*      */           
/* 1370 */           if (EcoreUtil.isInvariant(eOperation))
/*      */           {
/* 1372 */             for (String validationDelegate : validationDelegates) {
/*      */               
/* 1374 */               String expression = EcoreUtil.getAnnotation((EModelElement)eOperation, validationDelegate, "body");
/* 1375 */               if (expression != null) {
/*      */                 
/* 1377 */                 result &= EObjectValidator.validate(eClass, eObject, diagnostics, context, validationDelegate, eOperation, expression, 4, "org.eclipse.emf.ecore", 0);
/* 1378 */                 if (!result && diagnostics == null) {
/*      */                   break label20;
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       }
/* 1386 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean validateDelegatedConstraints(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1391 */       boolean result = true;
/* 1392 */       List<String> validationDelegates = EcoreUtil.getValidationDelegates(eClass.getEPackage());
/*      */       
/* 1394 */       if (!validationDelegates.isEmpty())
/*      */       {
/* 1396 */         label18: for (String constraint : EcoreUtil.getConstraints((EModelElement)eClass)) {
/*      */           
/* 1398 */           for (String validationDelegate : validationDelegates) {
/*      */             
/* 1400 */             String expression = EcoreUtil.getAnnotation((EModelElement)eClass, validationDelegate, constraint);
/* 1401 */             if (expression != null) {
/*      */               
/* 1403 */               result &= EObjectValidator.this.validate(eClass, eObject, diagnostics, context, validationDelegate, constraint, expression, 4, "org.eclipse.emf.ecore", 0);
/* 1404 */               if (!result && diagnostics == null) {
/*      */                 break label18;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/* 1411 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1416 */       boolean result = validateDelegatedInvariants(eClass, eObject, diagnostics, context);
/*      */       
/* 1418 */       if (result || diagnostics != null) {
/*      */         
/* 1420 */         result &= validateDelegatedConstraints(eClass, eObject, diagnostics, context);
/*      */         
/* 1422 */         if (result || diagnostics != null) {
/*      */           
/* 1424 */           EList<EClass> eList = eClass.getESuperTypes();
/* 1425 */           result &= eList.isEmpty() ? 
/* 1426 */             EObjectValidator.this.validate_EveryDefaultConstraint(eObject, diagnostics, context) : (
/* 1427 */             (eClass.eContainer() == EObjectValidator.this.getEPackage()) ? 
/* 1428 */             EObjectValidator.this.validate(eClass.getClassifierID(), eObject, diagnostics, context) : 
/* 1429 */             validate(eList.get(0), eObject, diagnostics, context));
/*      */         } 
/*      */       } 
/*      */       
/* 1433 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1439 */     if (!eDataType.isInstance(value)) {
/*      */       
/* 1441 */       if (value == null)
/*      */       {
/* 1443 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 1447 */       if (diagnostics != null) reportDataValueTypeViolation(eDataType, value, diagnostics, context); 
/* 1448 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1452 */     if (eDataType.eContainer() == getEPackage())
/*      */     {
/* 1454 */       return validate(eDataType.getClassifierID(), value, diagnostics, context);
/*      */     }
/*      */ 
/*      */     
/* 1458 */     return (
/* 1459 */       new DynamicEDataTypeValidator(eDataType)
/*      */       {
/*      */ 
/*      */       
/* 1463 */       }).validate(eDataType, value, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportMinViolation(EDataType eDataType, Object value, Object bound, boolean isInclusive, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1470 */     diagnostics.add(
/* 1471 */         (Diagnostic)createDiagnostic(
/* 1472 */           4, 
/* 1473 */           "org.eclipse.emf.ecore", 
/* 1474 */           5, 
/* 1475 */           isInclusive ? "_UI_MinInclusiveConstraint_diagnostic" : "_UI_MinExclusiveConstraint_diagnostic", 
/*      */           
/* 1477 */           new Object[] {
/* 1478 */             getValueLabel(eDataType, value, context), 
/* 1479 */             isInclusive ? ">=" : ">", 
/* 1480 */             getValueLabel(eDataType, bound, context)
/*      */           
/* 1482 */           }new Object[] { value, bound, isInclusive ? Boolean.TRUE : Boolean.FALSE
/* 1483 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportMaxViolation(EDataType eDataType, Object value, Object bound, boolean isInclusive, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1489 */     diagnostics.add(
/* 1490 */         (Diagnostic)createDiagnostic(
/* 1491 */           4, 
/* 1492 */           "org.eclipse.emf.ecore", 
/* 1493 */           5, 
/* 1494 */           isInclusive ? "_UI_MaxInclusiveConstraint_diagnostic" : "_UI_MaxExclusiveConstraint_diagnostic", 
/*      */           
/* 1496 */           new Object[] {
/* 1497 */             getValueLabel(eDataType, value, context), 
/* 1498 */             "<", 
/* 1499 */             getValueLabel(eDataType, bound, context)
/*      */           
/* 1501 */           }new Object[] { value, bound, isInclusive ? Boolean.TRUE : Boolean.FALSE
/* 1502 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportMinLengthViolation(EDataType eDataType, Object value, int length, int bound, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1508 */     diagnostics.add(
/* 1509 */         (Diagnostic)createDiagnostic(
/* 1510 */           4, 
/* 1511 */           "org.eclipse.emf.ecore", 
/* 1512 */           6, 
/* 1513 */           "_UI_MinLengthConstraint_diagnostic", 
/*      */           
/* 1515 */           new Object[] {
/* 1516 */             getValueLabel(eDataType, value, context), 
/* 1517 */             Integer.toString(length), 
/* 1518 */             Integer.toString(bound)
/*      */           
/* 1520 */           }new Object[] { value, eDataType, Integer.valueOf(length), Integer.valueOf(bound)
/* 1521 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportMaxLengthViolation(EDataType eDataType, Object value, int length, int bound, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1527 */     diagnostics.add(
/* 1528 */         (Diagnostic)createDiagnostic(
/* 1529 */           4, 
/* 1530 */           "org.eclipse.emf.ecore", 
/* 1531 */           6, 
/* 1532 */           "_UI_MaxLengthConstraint_diagnostic", 
/*      */           
/* 1534 */           new Object[] {
/* 1535 */             getValueLabel(eDataType, value, context), 
/* 1536 */             Integer.toString(length), 
/* 1537 */             Integer.toString(bound)
/*      */           
/* 1539 */           }new Object[] { value, eDataType, Integer.valueOf(length), Integer.valueOf(bound)
/* 1540 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportTotalDigitsViolation(EDataType eDataType, Object value, int totalDigits, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1546 */     diagnostics.add(
/* 1547 */         (Diagnostic)createDiagnostic(
/* 1548 */           4, 
/* 1549 */           "org.eclipse.emf.ecore", 
/* 1550 */           10, 
/* 1551 */           "_UI_TotalDigitsConstraint_diagnostic", 
/*      */           
/* 1553 */           new Object[] {
/* 1554 */             getValueLabel(eDataType, value, context), 
/* 1555 */             Integer.valueOf(totalDigits)
/*      */           
/* 1557 */           }new Object[] { value, eDataType, Integer.valueOf(totalDigits)
/* 1558 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportFractionDigitsViolation(EDataType eDataType, Object value, int fractionDigits, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1564 */     diagnostics.add(
/* 1565 */         (Diagnostic)createDiagnostic(
/* 1566 */           4, 
/* 1567 */           "org.eclipse.emf.ecore", 
/* 1568 */           10, 
/* 1569 */           "_UI_FractionDigitsConstraint_diagnostic", 
/*      */           
/* 1571 */           new Object[] {
/* 1572 */             getValueLabel(eDataType, value, context), 
/* 1573 */             Integer.valueOf(fractionDigits)
/*      */           
/* 1575 */           }new Object[] { value, eDataType, Integer.valueOf(fractionDigits)
/* 1576 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportEnumerationViolation(EDataType eDataType, Object value, Collection<?> values, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1582 */     String valueLiterals = "";
/* 1583 */     Iterator<?> i = values.iterator();
/* 1584 */     if (i.hasNext()) {
/*      */       
/* 1586 */       valueLiterals = 
/* 1587 */         getEcoreResourceLocator().getString("_UI_ListHead_composition", new Object[] { getValueLabel(eDataType, i.next(), context) });
/* 1588 */       while (i.hasNext()) {
/*      */         
/* 1590 */         valueLiterals = 
/* 1591 */           getEcoreResourceLocator().getString(
/* 1592 */             "_UI_ListTail_composition", 
/* 1593 */             new Object[] { valueLiterals, getValueLabel(eDataType, i.next(), context) });
/*      */       } 
/*      */     } 
/* 1596 */     diagnostics.add(
/* 1597 */         (Diagnostic)createDiagnostic(
/* 1598 */           4, 
/* 1599 */           "org.eclipse.emf.ecore", 
/* 1600 */           8, 
/* 1601 */           "_UI_EnumerationConstraint_diagnostic", 
/*      */           
/* 1603 */           new Object[] {
/* 1604 */             getValueLabel(eDataType, value, context), 
/* 1605 */             valueLiterals
/*      */           
/* 1607 */           }new Object[] { value, eDataType, values
/* 1608 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportDataValuePatternViolation(EDataType eDataType, Object value, EValidator.PatternMatcher[] patterns, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1614 */     String patternLiterals = "";
/* 1615 */     if (patterns.length > 0) {
/*      */       
/* 1617 */       patternLiterals = getEcoreResourceLocator().getString("_UI_ListHead_composition", new Object[] { patterns[0] });
/* 1618 */       for (int i = 1; i < patterns.length; i++) {
/*      */         
/* 1620 */         patternLiterals = getEcoreResourceLocator().getString("_UI_ListTail_composition", new Object[] { patternLiterals, patterns[i] });
/*      */       } 
/*      */     } 
/*      */     
/* 1624 */     diagnostics.add(
/* 1625 */         (Diagnostic)createDiagnostic(
/* 1626 */           4, 
/* 1627 */           "org.eclipse.emf.ecore", 
/* 1628 */           9, 
/* 1629 */           "_UI_PatternConstraint_diagnostic", 
/*      */           
/* 1631 */           new Object[] {
/* 1632 */             getValueLabel(eDataType, value, context), 
/* 1633 */             patternLiterals
/*      */           
/* 1635 */           }new Object[] { value, eDataType, patterns
/* 1636 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportDataValueTypeViolation(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1642 */     diagnostics.add(
/* 1643 */         (Diagnostic)createDiagnostic(
/* 1644 */           4, 
/* 1645 */           "org.eclipse.emf.ecore", 
/* 1646 */           7, 
/* 1647 */           "_UI_BadDataValueType_diagnostic", 
/*      */           
/* 1649 */           new Object[] {
/* 1650 */             getValueLabel(eDataType, value, context), 
/* 1651 */             (value == null) ? "<null>" : value.getClass().getName(), 
/* 1652 */             eDataType.getInstanceClass().getName()
/*      */           
/* 1654 */           }new Object[] { value, eDataType
/* 1655 */           }, context));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportConstraintDelegateViolation(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context, String constraint, int severity, String source, int code) {
/* 1663 */     diagnostics.add(
/* 1664 */         (Diagnostic)new BasicDiagnostic(
/* 1665 */           severity, 
/* 1666 */           source, 
/* 1667 */           code, 
/* 1668 */           getString("_UI_GenericConstraint_diagnostic", new Object[] { constraint, getValueLabel(eDataType, value, context)
/* 1669 */             }), new Object[] { value }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportConstraintDelegateException(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context, String constraint, int severity, String source, int code, Throwable throwable) {
/* 1677 */     diagnostics.add(
/* 1678 */         (Diagnostic)new BasicDiagnostic(
/* 1679 */           severity, 
/* 1680 */           source, 
/* 1681 */           code, 
/* 1682 */           getString("_UI_ConstraintDelegateException_diagnostic", new Object[] { constraint, getValueLabel(eDataType, value, context), throwable.getLocalizedMessage()
/* 1683 */             }), new Object[] { value }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportConstraintDelegateNotFound(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context, String constraint, int severity, String source, int code, String validationDelegate) {
/* 1691 */     diagnostics.add(
/* 1692 */         (Diagnostic)new BasicDiagnostic(
/* 1693 */           severity, 
/* 1694 */           source, 
/* 1695 */           code, 
/* 1696 */           getString("_UI_ConstraintDelegateNotFound_diagnostic", new Object[] { constraint, getValueLabel(eDataType, value, context), validationDelegate
/* 1697 */             }), new Object[] { value }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportConstraintDelegateViolation(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, String constraint, int severity, String source, int code) {
/* 1705 */     diagnostics.add(
/* 1706 */         (Diagnostic)new BasicDiagnostic(
/* 1707 */           severity, 
/* 1708 */           source, 
/* 1709 */           code, 
/* 1710 */           getString("_UI_GenericConstraint_diagnostic", new Object[] { constraint, getObjectLabel(eObject, context)
/* 1711 */             }), new Object[] { eObject }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportConstraintDelegateException(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, String constraint, int severity, String source, int code, Throwable throwable) {
/* 1719 */     diagnostics.add(
/* 1720 */         (Diagnostic)new BasicDiagnostic(
/* 1721 */           severity, 
/* 1722 */           source, 
/* 1723 */           code, 
/* 1724 */           getString("_UI_ConstraintDelegateException_diagnostic", new Object[] { constraint, getObjectLabel(eObject, context), throwable.getLocalizedMessage()
/* 1725 */             }), new Object[] { eObject }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportConstraintDelegateNotFound(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, String constraint, int severity, String source, int code, String validationDelegate) {
/* 1733 */     diagnostics.add(
/* 1734 */         (Diagnostic)new BasicDiagnostic(
/* 1735 */           severity, 
/* 1736 */           source, 
/* 1737 */           code, 
/* 1738 */           getString("_UI_ConstraintDelegateNotFound_diagnostic", new Object[] { constraint, getObjectLabel(eObject, context), validationDelegate
/* 1739 */             }), new Object[] { eObject }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void reportInvariantDelegateViolation(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, EOperation invariant, int severity, String source, int code) {
/* 1747 */     diagnostics.add(
/* 1748 */         (Diagnostic)new BasicDiagnostic(
/* 1749 */           severity, 
/* 1750 */           source, 
/* 1751 */           code, 
/* 1752 */           EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { invariant.getName(), getObjectLabel(eObject, context)
/* 1753 */             }), new Object[] { eObject }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void reportInvariantDelegateException(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, EOperation invariant, int severity, String source, int code, Throwable throwable) {
/* 1761 */     diagnostics.add(
/* 1762 */         (Diagnostic)new BasicDiagnostic(
/* 1763 */           severity, 
/* 1764 */           source, 
/* 1765 */           code, 
/* 1766 */           EcorePlugin.INSTANCE.getString("_UI_InvariantDelegateException_diagnostic", new Object[] { invariant.getName(), getObjectLabel(eObject, context), throwable.getLocalizedMessage()
/* 1767 */             }), new Object[] { eObject }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void reportInvariantDelegateNotFound(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, EOperation invariant, int severity, String source, int code, String validationDelegate) {
/* 1775 */     diagnostics.add(
/* 1776 */         (Diagnostic)new BasicDiagnostic(
/* 1777 */           severity, 
/* 1778 */           source, 
/* 1779 */           code, 
/* 1780 */           EcorePlugin.INSTANCE.getString("_UI_InvariantDelegateNotFound_diagnostic", new Object[] { invariant.getName(), getObjectLabel(eObject, context), validationDelegate
/* 1781 */             }), new Object[] { eObject }));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Collection<Object> wrapEnumerationValues(Object[] values) {
/* 1786 */     return Arrays.asList(values);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validate_UniqueID(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1794 */     boolean result = true;
/* 1795 */     String id = EcoreUtil.getID(eObject);
/* 1796 */     if (id != null) {
/*      */       
/* 1798 */       Resource resource = eObject.eResource();
/* 1799 */       if (resource != null) {
/*      */         
/* 1801 */         EObject otherEObject = resource.getEObject(id);
/* 1802 */         if (eObject != otherEObject && otherEObject != null)
/*      */         {
/*      */           
/* 1805 */           diagnostics.add(
/* 1806 */               (Diagnostic)createDiagnostic(
/* 1807 */                 4, 
/* 1808 */                 "org.eclipse.emf.ecore", 
/* 1809 */                 12, 
/* 1810 */                 "_UI_DuplicateID_diagnostic", 
/*      */                 
/* 1812 */                 new Object[] {
/* 1813 */                   id, 
/* 1814 */                   getObjectLabel(eObject, context), 
/* 1815 */                   getObjectLabel(otherEObject, context)
/*      */                 
/* 1817 */                 }new Object[] { eObject, otherEObject, id
/* 1818 */                 }, context));
/*      */         }
/*      */       } 
/*      */     } 
/* 1822 */     return result;
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
/*      */   public boolean validate_EveryKeyUnique(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1834 */     boolean result = true;
/* 1835 */     EClass eClass = eObject.eClass();
/* 1836 */     for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */       
/* 1838 */       EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/* 1839 */       if (eStructuralFeature instanceof EReference) {
/*      */         
/* 1841 */         EReference eReference = (EReference)eStructuralFeature;
/* 1842 */         if (eReference.isMany() && !eReference.getEKeys().isEmpty()) {
/*      */           
/* 1844 */           result &= validate_KeyUnique(eObject, eReference, diagnostics, context);
/* 1845 */           if (!result && diagnostics == null)
/*      */           {
/* 1847 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 1852 */     return result;
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
/*      */   protected boolean validate_KeyUnique(EObject eObject, EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1866 */     boolean result = true;
/* 1867 */     Map<List<Object>, EObject> keys = new HashMap<List<Object>, EObject>();
/* 1868 */     EAttribute[] eAttributes = (EAttribute[])((BasicEList)eReference.getEKeys()).data();
/*      */     
/* 1870 */     List<EObject> values = (List<EObject>)eObject.eGet((EStructuralFeature)eReference);
/* 1871 */     for (EObject value : values) {
/*      */       
/* 1873 */       ArrayList<Object> key = new ArrayList();
/* 1874 */       for (int i = 0, size = eAttributes.length; i < size; i++) {
/*      */         
/* 1876 */         EAttribute eAttribute = eAttributes[i];
/* 1877 */         if (eAttribute == null) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1883 */         key.add(value.eGet((EStructuralFeature)eAttribute));
/*      */       } 
/*      */       
/* 1886 */       EObject otherValue = keys.put(key, value);
/* 1887 */       if (otherValue != null) {
/*      */         
/* 1889 */         result = false;
/* 1890 */         if (diagnostics == null) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1896 */         String uriFragmentSegment = ((InternalEObject)eObject).eURIFragmentSegment((EStructuralFeature)eReference, value);
/* 1897 */         int index = uriFragmentSegment.indexOf('[', 0);
/* 1898 */         if (index != -1)
/*      */         {
/* 1900 */           uriFragmentSegment = uriFragmentSegment.substring(index);
/*      */         }
/* 1902 */         diagnostics.add(
/* 1903 */             (Diagnostic)createDiagnostic(
/* 1904 */               4, 
/* 1905 */               "org.eclipse.emf.ecore", 
/* 1906 */               13, 
/* 1907 */               "_UI_DuplicateKey_diagnostic", 
/*      */               
/* 1909 */               new Object[] {
/* 1910 */                 getFeatureLabel((EStructuralFeature)eReference, context), 
/* 1911 */                 uriFragmentSegment, 
/* 1912 */                 getObjectLabel(value, context), 
/* 1913 */                 getObjectLabel(otherValue, context)
/*      */               
/* 1915 */               }new Object[] { eObject, eReference, value, otherValue
/* 1916 */               }, context));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1921 */     return result;
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
/*      */   public boolean validate_EveryMapEntryUnique(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1933 */     boolean result = true;
/* 1934 */     EClass eClass = eObject.eClass();
/* 1935 */     for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*      */       
/* 1937 */       EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/* 1938 */       if (eStructuralFeature.getEType().getInstanceClassName() == "java.util.Map$Entry" && eStructuralFeature instanceof EReference) {
/*      */         
/* 1940 */         EReference eReference = (EReference)eStructuralFeature;
/* 1941 */         result &= validate_MapEntryUnique(eObject, eReference, diagnostics, context);
/* 1942 */         if (!result && diagnostics == null)
/*      */         {
/* 1944 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/* 1948 */     return result;
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
/*      */   protected boolean validate_MapEntryUnique(EObject eObject, EReference eReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1962 */     boolean result = true;
/* 1963 */     Object value = eObject.eGet((EStructuralFeature)eReference);
/* 1964 */     if (value instanceof EMap) {
/*      */       
/* 1966 */       EMap<?, ?> eMap = (EMap<?, ?>)value;
/* 1967 */       for (int i = 0, size = eMap.size(); i < size; i++) {
/*      */         
/* 1969 */         Map.Entry<?, ?> entry = (Map.Entry<?, ?>)eMap.get(i);
/* 1970 */         Object key = entry.getKey();
/* 1971 */         int index = eMap.indexOfKey(key);
/* 1972 */         if (index != i) {
/*      */           
/* 1974 */           result = false;
/* 1975 */           if (diagnostics == null) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1981 */           diagnostics.add(
/* 1982 */               (Diagnostic)createDiagnostic(
/* 1983 */                 4, 
/* 1984 */                 "org.eclipse.emf.ecore", 
/* 1985 */                 14, 
/* 1986 */                 "_UI_DuplicateMapEntry_diagnostic", 
/*      */                 
/* 1988 */                 new Object[] {
/* 1989 */                   getFeatureLabel((EStructuralFeature)eReference, context), 
/* 1990 */                   Integer.valueOf(i), 
/* 1991 */                   Integer.valueOf(index)
/*      */                 
/* 1993 */                 }new Object[] { eObject, eReference, entry, eMap.get(index)
/* 1994 */                 }, context));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2000 */     return result;
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
/*      */   protected BasicDiagnostic createDiagnostic(int severity, String source, int code, String messageKey, Object[] messageSubstitutions, Object[] data, Map<Object, Object> context) {
/* 2022 */     String message = 
/* 2023 */       "org.eclipse.emf.ecore".equals(source) ? 
/* 2024 */       getEcoreString(messageKey, messageSubstitutions) : 
/* 2025 */       getString(messageKey, messageSubstitutions);
/* 2026 */     return new BasicDiagnostic(severity, source, code, message, data);
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
/*      */   protected String getEcoreString(String key, Object[] substitutions) {
/* 2039 */     return getString(getEcoreResourceLocator(), key, substitutions);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ResourceLocator getEcoreResourceLocator() {
/* 2049 */     return (ResourceLocator)EcorePlugin.INSTANCE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isEcoreString(String key) {
/* 2057 */     if (!"_UI_GenericConstraint_diagnostic".equals(key) && !"_UI_GenericInvariant_diagnostic".equals(key) && 
/* 2058 */       !"_UI_ConstraintDelegateException_diagnostic".equals(key) && !"_UI_InvariantDelegateException_diagnostic".equals(key) && 
/* 2059 */       !"_UI_ConstraintDelegateNotFound_diagnostic".equals(key) && !"_UI_InvariantDelegateNotFound_diagnostic".equals(key)) return false;
/*      */     
/*      */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getString(String key, Object[] substitutions) {
/* 2072 */     return getString(isEcoreString(key) ? getEcoreResourceLocator() : getResourceLocator(), key, substitutions);
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
/*      */   protected ResourceLocator getResourceLocator() {
/* 2084 */     return getEcoreResourceLocator();
/*      */   }
/*      */ 
/*      */   
/*      */   private String getString(ResourceLocator resourceLocator, String key, Object[] substitutions) {
/* 2089 */     return (substitutions == null) ? resourceLocator.getString(key) : resourceLocator.getString(key, substitutions);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EObjectValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */