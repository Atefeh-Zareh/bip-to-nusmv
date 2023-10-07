/*      */ package org.eclipse.emf.ecore.impl;
/*      */ public class EcorePackageImpl extends EPackageImpl implements EcorePackage { private static boolean isInited = false; private EClass eAttributeEClass; private EClass eAnnotationEClass; private EClass eClassEClass; private EClass eDataTypeEClass; private EClass eEnumEClass; private EClass eEnumLiteralEClass; private EClass eFactoryEClass; private EClass eClassifierEClass; private EClass eModelElementEClass; private EClass eNamedElementEClass; private EClass eObjectEClass; private EClass eOperationEClass; private EClass ePackageEClass; private EClass eParameterEClass; private EClass eReferenceEClass; private EClass eStructuralFeatureEClass; private EClass eTypedElementEClass; private EClass eStringToStringMapEntryEClass; private EClass eGenericTypeEClass; private EClass eTypeParameterEClass; private EDataType eBigDecimalEDataType; private EDataType eBigIntegerEDataType; private EDataType eBooleanObjectEDataType; private EDataType eCharacterObjectEDataType; private EDataType eDateEDataType; private EDataType eDiagnosticChainEDataType; private EDataType eDoubleObjectEDataType; private EDataType eFloatObjectEDataType; private EDataType eIntegerObjectEDataType; private EDataType eBooleanEDataType; private EDataType eByteObjectEDataType; private EDataType eByteEDataType; private EDataType eByteArrayEDataType; private EDataType eCharEDataType; private EDataType eDoubleEDataType; private EDataType eFloatEDataType; private EDataType eIntEDataType; private EDataType eJavaClassEDataType; private EDataType eJavaObjectEDataType; private EDataType eLongObjectEDataType; private EDataType eMapEDataType; private EDataType eShortObjectEDataType; private EDataType eLongEDataType; private EDataType eShortEDataType; private EDataType eTreeIteratorEDataType; private EDataType eInvocationTargetExceptionEDataType; private EDataType eFeatureMapEntryEDataType; private EDataType eEnumeratorEDataType; private EDataType eFeatureMapEDataType; private EDataType eStringEDataType; private EDataType eeListEDataType; private EDataType eResourceEDataType; private EDataType eResourceSetEDataType; public static EcorePackage init() {
/*      */     if (isInited)
/*      */       return (EcorePackage)EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2002/Ecore"); 
/*      */     EcorePackageImpl theEcorePackage = (EPackage.Registry.INSTANCE.get("http://www.eclipse.org/emf/2002/Ecore") instanceof EcorePackageImpl) ? (EcorePackageImpl)EPackage.Registry.INSTANCE.get("http://www.eclipse.org/emf/2002/Ecore") : new EcorePackageImpl();
/*      */     isInited = true;
/*      */     theEcorePackage.createPackageContents();
/*      */     theEcorePackage.initializePackageContents();
/*      */     EValidator.Registry.INSTANCE.put(theEcorePackage, new EValidator.Descriptor() { public EValidator getEValidator() {
/*      */             return (EValidator)EcoreValidator.INSTANCE;
/*      */           } }
/*      */       );
/*      */     EPackage.Registry.INSTANCE.put("http://www.eclipse.org/emf/2002/Ecore", theEcorePackage);
/*      */     return theEcorePackage;
/*      */   } private static ArrayList<EGenericTypeImpl> eGenericTypes = new ArrayList<EGenericTypeImpl>(); private boolean isCreated; private boolean isInitialized; protected EGenericType createEGenericType() {
/*      */     EGenericTypeImpl eGenericType = (EGenericTypeImpl)super.createEGenericType();
/*      */     eGenericTypes.add(eGenericType);
/*      */     return eGenericType;
/*      */   } public static boolean internalBootstrap() {
/*      */     ((EcorePackageImpl)EcorePackage.eINSTANCE).createExtendedMetaDataAnnotations();
/*      */     ((EPackageImpl)EcorePackage.eINSTANCE).freeze();
/*      */     EGenericTypeImpl.eJavaObject = EcorePackage.Literals.EJAVA_OBJECT;
/*      */     for (EGenericTypeImpl eGenericType : eGenericTypes)
/*      */       eGenericType.setERawType((EClassifier)EcorePackage.Literals.EJAVA_OBJECT, null); 
/*      */     return true;
/*      */   } public EClass getEClass() {
/*      */     return this.eClassEClass;
/*      */   } public EAttribute getEClass_Abstract() {
/*      */     return (EAttribute)this.eClassEClass.getEStructuralFeatures().get(0);
/*      */   } public EAttribute getEClass_Interface() {
/*      */     return (EAttribute)this.eClassEClass.getEStructuralFeatures().get(1);
/*      */   } public EReference getEClass_ESuperTypes() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(2);
/*      */   } public EReference getEClass_EOperations() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(3);
/*      */   } public EReference getEClass_EAllAttributes() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(4);
/*      */   } public EReference getEClass_EAllReferences() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(5);
/*      */   } public EReference getEClass_EReferences() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(6);
/*      */   } public EReference getEClass_EAttributes() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(7);
/*      */   } public EReference getEClass_EAllContainments() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(8);
/*      */   } public EReference getEClass_EAllOperations() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(9);
/*      */   } public EReference getEClass_EAllStructuralFeatures() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(10);
/*      */   } public EReference getEClass_EAllSuperTypes() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(11);
/*      */   } public EReference getEClass_EIDAttribute() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(12);
/*      */   } public EReference getEClass_EStructuralFeatures() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(13);
/*      */   } public EReference getEClass_EGenericSuperTypes() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(14);
/*      */   } public EReference getEClass_EAllGenericSuperTypes() {
/*      */     return (EReference)this.eClassEClass.getEStructuralFeatures().get(15);
/*      */   } public EOperation getEClass__IsSuperTypeOf__EClass() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(0);
/*      */   } public EOperation getEClass__GetFeatureCount() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(1);
/*      */   } public EOperation getEClass__GetEStructuralFeature__int() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(2);
/*      */   } public EOperation getEClass__GetFeatureID__EStructuralFeature() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(3);
/*      */   } public EOperation getEClass__GetEStructuralFeature__String() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(4);
/*      */   } public EOperation getEClass__GetOperationCount() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(5);
/*      */   } public EOperation getEClass__GetEOperation__int() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(6);
/*      */   } public EOperation getEClass__GetOperationID__EOperation() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(7);
/*      */   } public EOperation getEClass__GetOverride__EOperation() {
/*      */     return (EOperation)this.eClassEClass.getEOperations().get(8);
/*      */   }
/*      */   public EClass getEDataType() {
/*      */     return this.eDataTypeEClass;
/*      */   }
/*      */   public EAttribute getEDataType_Serializable() {
/*      */     return (EAttribute)this.eDataTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*   85 */   private EcorePackageImpl() { super("http://www.eclipse.org/emf/2002/Ecore", (EFactory)EcoreFactory.eINSTANCE);
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
/*  100 */     this.eAttributeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  107 */     this.eAnnotationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  114 */     this.eClassEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  121 */     this.eDataTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  128 */     this.eEnumEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  135 */     this.eEnumLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  142 */     this.eFactoryEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  149 */     this.eClassifierEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  156 */     this.eModelElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  163 */     this.eNamedElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  170 */     this.eObjectEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  177 */     this.eOperationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  184 */     this.ePackageEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  191 */     this.eParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  198 */     this.eReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  205 */     this.eStructuralFeatureEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  212 */     this.eTypedElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  219 */     this.eStringToStringMapEntryEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  226 */     this.eGenericTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  233 */     this.eTypeParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  240 */     this.eBigDecimalEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  247 */     this.eBigIntegerEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  254 */     this.eBooleanObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  261 */     this.eCharacterObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  268 */     this.eDateEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  275 */     this.eDiagnosticChainEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  282 */     this.eDoubleObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  289 */     this.eFloatObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  296 */     this.eIntegerObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  303 */     this.eBooleanEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  310 */     this.eByteObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  317 */     this.eByteEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  324 */     this.eByteArrayEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  331 */     this.eCharEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  338 */     this.eDoubleEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  345 */     this.eFloatEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  352 */     this.eIntEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  359 */     this.eJavaClassEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  366 */     this.eJavaObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  373 */     this.eLongObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  380 */     this.eMapEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  387 */     this.eShortObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  394 */     this.eLongEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  401 */     this.eShortEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  408 */     this.eTreeIteratorEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  415 */     this.eInvocationTargetExceptionEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  422 */     this.eFeatureMapEntryEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  429 */     this.eEnumeratorEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  436 */     this.eFeatureMapEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  443 */     this.eStringEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  450 */     this.eeListEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  457 */     this.eResourceEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  464 */     this.eResourceSetEDataType = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2270 */     this.isCreated = false;
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
/* 2485 */     this.isInitialized = false; }
/*      */   public EClass getEClassifier() { return this.eClassifierEClass; }
/*      */   public EAttribute getEClassifier_InstanceClassName() { return (EAttribute)this.eClassifierEClass.getEStructuralFeatures().get(0); }
/*      */   public EAttribute getEClassifier_InstanceClass() { return (EAttribute)this.eClassifierEClass.getEStructuralFeatures().get(1); }
/*      */   public EAttribute getEClassifier_DefaultValue() { return (EAttribute)this.eClassifierEClass.getEStructuralFeatures().get(2); }
/*      */   public EAttribute getEClassifier_InstanceTypeName() { return (EAttribute)this.eClassifierEClass.getEStructuralFeatures().get(3); }
/*      */   public EReference getEClassifier_EPackage() { return (EReference)this.eClassifierEClass.getEStructuralFeatures().get(4); }
/*      */   public EReference getEClassifier_ETypeParameters() { return (EReference)this.eClassifierEClass.getEStructuralFeatures().get(5); }
/*      */   public EOperation getEClassifier__IsInstance__Object() { return (EOperation)this.eClassifierEClass.getEOperations().get(0); }
/*      */   public EOperation getEClassifier__GetClassifierID() { return (EOperation)this.eClassifierEClass.getEOperations().get(1); }
/*      */   public EClass getENamedElement() { return this.eNamedElementEClass; }
/* 2496 */   public EAttribute getENamedElement_Name() { return (EAttribute)this.eNamedElementEClass.getEStructuralFeatures().get(0); } public EClass getEOperation() { return this.eOperationEClass; } public EReference getEOperation_EContainingClass() { return (EReference)this.eOperationEClass.getEStructuralFeatures().get(0); } public EReference getEOperation_EParameters() { return (EReference)this.eOperationEClass.getEStructuralFeatures().get(2); } public EReference getEOperation_EExceptions() { return (EReference)this.eOperationEClass.getEStructuralFeatures().get(3); } public EReference getEOperation_EGenericExceptions() { return (EReference)this.eOperationEClass.getEStructuralFeatures().get(4); } public EOperation getEOperation__GetOperationID() { return (EOperation)this.eOperationEClass.getEOperations().get(0); } public EOperation getEOperation__IsOverrideOf__EOperation() { return (EOperation)this.eOperationEClass.getEOperations().get(1); } public EReference getEOperation_ETypeParameters() { return (EReference)this.eOperationEClass.getEStructuralFeatures().get(1); } public EClass getEModelElement() { return this.eModelElementEClass; } public EReference getEModelElement_EAnnotations() { return (EReference)this.eModelElementEClass.getEStructuralFeatures().get(0); } public EOperation getEModelElement__GetEAnnotation__String() { return (EOperation)this.eModelElementEClass.getEOperations().get(0); } public EClass getEStructuralFeature() { return this.eStructuralFeatureEClass; } public EAttribute getEStructuralFeature_Transient() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(2); } public EAttribute getEStructuralFeature_Volatile() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(1); } public EAttribute getEStructuralFeature_Changeable() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(0); } public EAttribute getEStructuralFeature_DefaultValueLiteral() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(3); } public EAttribute getEStructuralFeature_DefaultValue() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(4); } public EAttribute getEStructuralFeature_Unsettable() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(5); } public void initializePackageContents() { if (this.isInitialized)
/* 2497 */       return;  this.isInitialized = true;
/*      */ 
/*      */     
/* 2500 */     setName("ecore");
/* 2501 */     setNsPrefix("ecore");
/* 2502 */     setNsURI("http://www.eclipse.org/emf/2002/Ecore");
/*      */ 
/*      */     
/* 2505 */     addETypeParameter((EClassifier)this.eeListEDataType, "E");
/* 2506 */     addETypeParameter((EClassifier)this.eJavaClassEDataType, "T");
/* 2507 */     addETypeParameter((EClassifier)this.eMapEDataType, "K");
/* 2508 */     addETypeParameter((EClassifier)this.eMapEDataType, "V");
/* 2509 */     addETypeParameter((EClassifier)this.eTreeIteratorEDataType, "E");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2514 */     this.eAttributeEClass.getESuperTypes().add(getEStructuralFeature());
/* 2515 */     this.eAnnotationEClass.getESuperTypes().add(getEModelElement());
/* 2516 */     this.eClassEClass.getESuperTypes().add(getEClassifier());
/* 2517 */     this.eClassifierEClass.getESuperTypes().add(getENamedElement());
/* 2518 */     this.eDataTypeEClass.getESuperTypes().add(getEClassifier());
/* 2519 */     this.eEnumEClass.getESuperTypes().add(getEDataType());
/* 2520 */     this.eEnumLiteralEClass.getESuperTypes().add(getENamedElement());
/* 2521 */     this.eFactoryEClass.getESuperTypes().add(getEModelElement());
/* 2522 */     this.eNamedElementEClass.getESuperTypes().add(getEModelElement());
/* 2523 */     this.eOperationEClass.getESuperTypes().add(getETypedElement());
/* 2524 */     this.ePackageEClass.getESuperTypes().add(getENamedElement());
/* 2525 */     this.eParameterEClass.getESuperTypes().add(getETypedElement());
/* 2526 */     this.eReferenceEClass.getESuperTypes().add(getEStructuralFeature());
/* 2527 */     this.eStructuralFeatureEClass.getESuperTypes().add(getETypedElement());
/* 2528 */     this.eTypedElementEClass.getESuperTypes().add(getENamedElement());
/* 2529 */     this.eTypeParameterEClass.getESuperTypes().add(getENamedElement());
/*      */ 
/*      */     
/* 2532 */     initEClass(this.eAttributeEClass, EAttribute.class, "EAttribute", false, false, true);
/* 2533 */     initEAttribute(getEAttribute_ID(), (EClassifier)getEBoolean(), "iD", (String)null, 0, 1, EAttribute.class, false, false, true, false, false, true, false, true);
/* 2534 */     initEReference(getEAttribute_EAttributeType(), (EClassifier)getEDataType(), (EReference)null, "eAttributeType", (String)null, 1, 1, EAttribute.class, true, true, false, false, true, false, true, true, true);
/*      */     
/* 2536 */     initEClass(this.eAnnotationEClass, EAnnotation.class, "EAnnotation", false, false, true);
/* 2537 */     initEAttribute(getEAnnotation_Source(), (EClassifier)getEString(), "source", (String)null, 0, 1, EAnnotation.class, false, false, true, false, false, true, false, true);
/* 2538 */     initEReference(getEAnnotation_Details(), (EClassifier)getEStringToStringMapEntry(), (EReference)null, "details", (String)null, 0, -1, EAnnotation.class, false, false, true, true, false, false, true, false, true);
/* 2539 */     initEReference(getEAnnotation_EModelElement(), (EClassifier)getEModelElement(), getEModelElement_EAnnotations(), "eModelElement", (String)null, 0, 1, EAnnotation.class, true, false, true, false, false, false, true, false, true);
/* 2540 */     initEReference(getEAnnotation_Contents(), (EClassifier)getEObject(), (EReference)null, "contents", (String)null, 0, -1, EAnnotation.class, false, false, true, true, false, false, true, false, true);
/* 2541 */     initEReference(getEAnnotation_References(), (EClassifier)getEObject(), (EReference)null, "references", (String)null, 0, -1, EAnnotation.class, false, false, true, false, true, false, true, false, true);
/*      */     
/* 2543 */     initEClass(this.eClassEClass, EClass.class, "EClass", false, false, true);
/* 2544 */     initEAttribute(getEClass_Abstract(), (EClassifier)getEBoolean(), "abstract", (String)null, 0, 1, EClass.class, false, false, true, false, false, true, false, true);
/* 2545 */     initEAttribute(getEClass_Interface(), (EClassifier)getEBoolean(), "interface", (String)null, 0, 1, EClass.class, false, false, true, false, false, true, false, true);
/* 2546 */     initEReference(getEClass_ESuperTypes(), (EClassifier)getEClass(), (EReference)null, "eSuperTypes", (String)null, 0, -1, EClass.class, false, false, true, false, true, true, true, false, true);
/* 2547 */     initEReference(getEClass_EOperations(), (EClassifier)getEOperation(), getEOperation_EContainingClass(), "eOperations", (String)null, 0, -1, EClass.class, false, false, true, true, false, false, true, false, true);
/* 2548 */     initEReference(getEClass_EAllAttributes(), (EClassifier)getEAttribute(), (EReference)null, "eAllAttributes", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2549 */     initEReference(getEClass_EAllReferences(), (EClassifier)getEReference(), (EReference)null, "eAllReferences", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2550 */     initEReference(getEClass_EReferences(), (EClassifier)getEReference(), (EReference)null, "eReferences", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2551 */     initEReference(getEClass_EAttributes(), (EClassifier)getEAttribute(), (EReference)null, "eAttributes", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2552 */     initEReference(getEClass_EAllContainments(), (EClassifier)getEReference(), (EReference)null, "eAllContainments", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2553 */     initEReference(getEClass_EAllOperations(), (EClassifier)getEOperation(), (EReference)null, "eAllOperations", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2554 */     initEReference(getEClass_EAllStructuralFeatures(), (EClassifier)getEStructuralFeature(), (EReference)null, "eAllStructuralFeatures", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2555 */     initEReference(getEClass_EAllSuperTypes(), (EClassifier)getEClass(), (EReference)null, "eAllSuperTypes", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/* 2556 */     initEReference(getEClass_EIDAttribute(), (EClassifier)getEAttribute(), (EReference)null, "eIDAttribute", (String)null, 0, 1, EClass.class, true, true, false, false, false, false, true, true, true);
/* 2557 */     initEReference(getEClass_EStructuralFeatures(), (EClassifier)getEStructuralFeature(), getEStructuralFeature_EContainingClass(), "eStructuralFeatures", (String)null, 0, -1, EClass.class, false, false, true, true, false, false, true, false, true);
/* 2558 */     initEReference(getEClass_EGenericSuperTypes(), (EClassifier)getEGenericType(), (EReference)null, "eGenericSuperTypes", (String)null, 0, -1, EClass.class, false, false, true, true, false, true, true, false, true);
/* 2559 */     initEReference(getEClass_EAllGenericSuperTypes(), (EClassifier)getEGenericType(), (EReference)null, "eAllGenericSuperTypes", (String)null, 0, -1, EClass.class, true, true, false, false, true, false, true, true, true);
/*      */     
/* 2561 */     EOperation op = initEOperation(getEClass__IsSuperTypeOf__EClass(), (EClassifier)getEBoolean(), "isSuperTypeOf", 0, 1, true, true);
/* 2562 */     addEParameter(op, (EClassifier)getEClass(), "someClass", 0, 1, true, true);
/*      */     
/* 2564 */     initEOperation(getEClass__GetFeatureCount(), (EClassifier)getEInt(), "getFeatureCount", 0, 1, true, true);
/*      */     
/* 2566 */     op = initEOperation(getEClass__GetEStructuralFeature__int(), (EClassifier)getEStructuralFeature(), "getEStructuralFeature", 0, 1, true, true);
/* 2567 */     addEParameter(op, (EClassifier)getEInt(), "featureID", 0, 1, true, true);
/*      */     
/* 2569 */     op = initEOperation(getEClass__GetFeatureID__EStructuralFeature(), (EClassifier)getEInt(), "getFeatureID", 0, 1, true, true);
/* 2570 */     addEParameter(op, (EClassifier)getEStructuralFeature(), "feature", 0, 1, true, true);
/*      */     
/* 2572 */     op = initEOperation(getEClass__GetEStructuralFeature__String(), (EClassifier)getEStructuralFeature(), "getEStructuralFeature", 0, 1, true, true);
/* 2573 */     addEParameter(op, (EClassifier)getEString(), "featureName", 0, 1, true, true);
/*      */     
/* 2575 */     initEOperation(getEClass__GetOperationCount(), (EClassifier)getEInt(), "getOperationCount", 0, 1, true, true);
/*      */     
/* 2577 */     op = initEOperation(getEClass__GetEOperation__int(), (EClassifier)getEOperation(), "getEOperation", 0, 1, true, true);
/* 2578 */     addEParameter(op, (EClassifier)getEInt(), "operationID", 0, 1, true, true);
/*      */     
/* 2580 */     op = initEOperation(getEClass__GetOperationID__EOperation(), (EClassifier)getEInt(), "getOperationID", 0, 1, true, true);
/* 2581 */     addEParameter(op, (EClassifier)getEOperation(), "operation", 0, 1, true, true);
/*      */     
/* 2583 */     op = initEOperation(getEClass__GetOverride__EOperation(), (EClassifier)getEOperation(), "getOverride", 0, 1, true, true);
/* 2584 */     addEParameter(op, (EClassifier)getEOperation(), "operation", 0, 1, true, true);
/*      */     
/* 2586 */     initEClass(this.eClassifierEClass, EClassifier.class, "EClassifier", true, false, true);
/* 2587 */     initEAttribute(getEClassifier_InstanceClassName(), (EClassifier)getEString(), "instanceClassName", (String)null, 0, 1, EClassifier.class, false, true, true, true, false, true, false, true);
/* 2588 */     EGenericType g1 = createEGenericType((EClassifier)getEJavaClass());
/* 2589 */     EGenericType g2 = createEGenericType();
/* 2590 */     g1.getETypeArguments().add(g2);
/* 2591 */     initEAttribute(getEClassifier_InstanceClass(), g1, "instanceClass", (String)null, 0, 1, EClassifier.class, true, true, false, false, false, true, true, true);
/* 2592 */     initEAttribute(getEClassifier_DefaultValue(), (EClassifier)getEJavaObject(), "defaultValue", (String)null, 0, 1, EClassifier.class, true, true, false, false, false, true, true, true);
/* 2593 */     initEAttribute(getEClassifier_InstanceTypeName(), (EClassifier)getEString(), "instanceTypeName", (String)null, 0, 1, EClassifier.class, false, true, true, true, false, true, false, true);
/* 2594 */     initEReference(getEClassifier_EPackage(), (EClassifier)getEPackage(), getEPackage_EClassifiers(), "ePackage", (String)null, 0, 1, EClassifier.class, true, false, false, false, true, false, true, false, true);
/* 2595 */     initEReference(getEClassifier_ETypeParameters(), (EClassifier)getETypeParameter(), (EReference)null, "eTypeParameters", (String)null, 0, -1, EClassifier.class, false, false, true, true, true, false, true, false, true);
/*      */     
/* 2597 */     op = initEOperation(getEClassifier__IsInstance__Object(), (EClassifier)getEBoolean(), "isInstance", 0, 1, true, true);
/* 2598 */     addEParameter(op, (EClassifier)getEJavaObject(), "object", 0, 1, true, true);
/*      */     
/* 2600 */     initEOperation(getEClassifier__GetClassifierID(), (EClassifier)getEInt(), "getClassifierID", 0, 1, true, true);
/*      */     
/* 2602 */     initEClass(this.eDataTypeEClass, EDataType.class, "EDataType", false, false, true);
/* 2603 */     initEAttribute(getEDataType_Serializable(), (EClassifier)getEBoolean(), "serializable", "true", 0, 1, EDataType.class, false, false, true, false, false, true, false, true);
/*      */     
/* 2605 */     initEClass(this.eEnumEClass, EEnum.class, "EEnum", false, false, true);
/* 2606 */     initEReference(getEEnum_ELiterals(), (EClassifier)getEEnumLiteral(), getEEnumLiteral_EEnum(), "eLiterals", (String)null, 0, -1, EEnum.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 2608 */     op = initEOperation(getEEnum__GetEEnumLiteral__String(), (EClassifier)getEEnumLiteral(), "getEEnumLiteral", 0, 1, true, true);
/* 2609 */     addEParameter(op, (EClassifier)getEString(), "name", 0, 1, true, true);
/*      */     
/* 2611 */     op = initEOperation(getEEnum__GetEEnumLiteral__int(), (EClassifier)getEEnumLiteral(), "getEEnumLiteral", 0, 1, true, true);
/* 2612 */     addEParameter(op, (EClassifier)getEInt(), "value", 0, 1, true, true);
/*      */     
/* 2614 */     op = initEOperation(getEEnum__GetEEnumLiteralByLiteral__String(), (EClassifier)getEEnumLiteral(), "getEEnumLiteralByLiteral", 0, 1, true, true);
/* 2615 */     addEParameter(op, (EClassifier)getEString(), "literal", 0, 1, true, true);
/*      */     
/* 2617 */     initEClass(this.eEnumLiteralEClass, EEnumLiteral.class, "EEnumLiteral", false, false, true);
/* 2618 */     initEAttribute(getEEnumLiteral_Value(), (EClassifier)getEInt(), "value", (String)null, 0, 1, EEnumLiteral.class, false, false, true, false, false, true, false, true);
/* 2619 */     initEAttribute(getEEnumLiteral_Instance(), (EClassifier)getEEnumerator(), "instance", (String)null, 0, 1, EEnumLiteral.class, true, false, true, false, false, true, false, true);
/* 2620 */     initEAttribute(getEEnumLiteral_Literal(), (EClassifier)getEString(), "literal", (String)null, 0, 1, EEnumLiteral.class, false, false, true, false, false, true, false, true);
/* 2621 */     initEReference(getEEnumLiteral_EEnum(), (EClassifier)getEEnum(), getEEnum_ELiterals(), "eEnum", (String)null, 0, 1, EEnumLiteral.class, true, false, false, false, false, false, true, false, true);
/*      */     
/* 2623 */     initEClass(this.eFactoryEClass, EFactory.class, "EFactory", false, false, true);
/* 2624 */     initEReference(getEFactory_EPackage(), (EClassifier)getEPackage(), getEPackage_EFactoryInstance(), "ePackage", (String)null, 1, 1, EFactory.class, true, false, true, false, false, false, true, false, true);
/*      */     
/* 2626 */     op = initEOperation(getEFactory__Create__EClass(), (EClassifier)getEObject(), "create", 0, 1, true, true);
/* 2627 */     addEParameter(op, (EClassifier)getEClass(), "eClass", 0, 1, true, true);
/*      */     
/* 2629 */     op = initEOperation(getEFactory__CreateFromString__EDataType_String(), (EClassifier)getEJavaObject(), "createFromString", 0, 1, true, true);
/* 2630 */     addEParameter(op, (EClassifier)getEDataType(), "eDataType", 0, 1, true, true);
/* 2631 */     addEParameter(op, (EClassifier)getEString(), "literalValue", 0, 1, true, true);
/*      */     
/* 2633 */     op = initEOperation(getEFactory__ConvertToString__EDataType_Object(), (EClassifier)getEString(), "convertToString", 0, 1, true, true);
/* 2634 */     addEParameter(op, (EClassifier)getEDataType(), "eDataType", 0, 1, true, true);
/* 2635 */     addEParameter(op, (EClassifier)getEJavaObject(), "instanceValue", 0, 1, true, true);
/*      */     
/* 2637 */     initEClass(this.eModelElementEClass, EModelElement.class, "EModelElement", true, false, true);
/* 2638 */     initEReference(getEModelElement_EAnnotations(), (EClassifier)getEAnnotation(), getEAnnotation_EModelElement(), "eAnnotations", (String)null, 0, -1, EModelElement.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 2640 */     op = initEOperation(getEModelElement__GetEAnnotation__String(), (EClassifier)getEAnnotation(), "getEAnnotation", 0, 1, true, true);
/* 2641 */     addEParameter(op, (EClassifier)getEString(), "source", 0, 1, true, true);
/*      */     
/* 2643 */     initEClass(this.eNamedElementEClass, ENamedElement.class, "ENamedElement", true, false, true);
/* 2644 */     initEAttribute(getENamedElement_Name(), (EClassifier)getEString(), "name", (String)null, 0, 1, ENamedElement.class, false, false, true, false, false, true, false, true);
/*      */     
/* 2646 */     initEClass(this.eObjectEClass, EObject.class, "EObject", false, false, true);
/*      */     
/* 2648 */     initEOperation(getEObject__EClass(), (EClassifier)getEClass(), "eClass", 0, 1, true, true);
/*      */     
/* 2650 */     initEOperation(getEObject__EIsProxy(), (EClassifier)getEBoolean(), "eIsProxy", 0, 1, true, true);
/*      */     
/* 2652 */     initEOperation(getEObject__EResource(), (EClassifier)getEResource(), "eResource", 0, 1, true, true);
/*      */     
/* 2654 */     initEOperation(getEObject__EContainer(), (EClassifier)getEObject(), "eContainer", 0, 1, true, true);
/*      */     
/* 2656 */     initEOperation(getEObject__EContainingFeature(), (EClassifier)getEStructuralFeature(), "eContainingFeature", 0, 1, true, true);
/*      */     
/* 2658 */     initEOperation(getEObject__EContainmentFeature(), (EClassifier)getEReference(), "eContainmentFeature", 0, 1, true, true);
/*      */     
/* 2660 */     op = initEOperation(getEObject__EContents(), (EClassifier)null, "eContents", 0, 1, true, true);
/* 2661 */     g1 = createEGenericType((EClassifier)getEEList());
/* 2662 */     g2 = createEGenericType((EClassifier)getEObject());
/* 2663 */     g1.getETypeArguments().add(g2);
/* 2664 */     initEOperation(op, g1);
/*      */     
/* 2666 */     op = initEOperation(getEObject__EAllContents(), (EClassifier)null, "eAllContents", 0, 1, true, true);
/* 2667 */     g1 = createEGenericType((EClassifier)getETreeIterator());
/* 2668 */     g2 = createEGenericType((EClassifier)getEObject());
/* 2669 */     g1.getETypeArguments().add(g2);
/* 2670 */     initEOperation(op, g1);
/*      */     
/* 2672 */     op = initEOperation(getEObject__ECrossReferences(), (EClassifier)null, "eCrossReferences", 0, 1, true, true);
/* 2673 */     g1 = createEGenericType((EClassifier)getEEList());
/* 2674 */     g2 = createEGenericType((EClassifier)getEObject());
/* 2675 */     g1.getETypeArguments().add(g2);
/* 2676 */     initEOperation(op, g1);
/*      */     
/* 2678 */     op = initEOperation(getEObject__EGet__EStructuralFeature(), (EClassifier)getEJavaObject(), "eGet", 0, 1, true, true);
/* 2679 */     addEParameter(op, (EClassifier)getEStructuralFeature(), "feature", 0, 1, true, true);
/*      */     
/* 2681 */     op = initEOperation(getEObject__EGet__EStructuralFeature_boolean(), (EClassifier)getEJavaObject(), "eGet", 0, 1, true, true);
/* 2682 */     addEParameter(op, (EClassifier)getEStructuralFeature(), "feature", 0, 1, true, true);
/* 2683 */     addEParameter(op, (EClassifier)getEBoolean(), "resolve", 0, 1, true, true);
/*      */     
/* 2685 */     op = initEOperation(getEObject__ESet__EStructuralFeature_Object(), (EClassifier)null, "eSet", 0, 1, true, true);
/* 2686 */     addEParameter(op, (EClassifier)getEStructuralFeature(), "feature", 0, 1, true, true);
/* 2687 */     addEParameter(op, (EClassifier)getEJavaObject(), "newValue", 0, 1, true, true);
/*      */     
/* 2689 */     op = initEOperation(getEObject__EIsSet__EStructuralFeature(), (EClassifier)getEBoolean(), "eIsSet", 0, 1, true, true);
/* 2690 */     addEParameter(op, (EClassifier)getEStructuralFeature(), "feature", 0, 1, true, true);
/*      */     
/* 2692 */     op = initEOperation(getEObject__EUnset__EStructuralFeature(), (EClassifier)null, "eUnset", 0, 1, true, true);
/* 2693 */     addEParameter(op, (EClassifier)getEStructuralFeature(), "feature", 0, 1, true, true);
/*      */     
/* 2695 */     op = initEOperation(getEObject__EInvoke__EOperation_EList(), (EClassifier)getEJavaObject(), "eInvoke", 0, 1, true, true);
/* 2696 */     addEParameter(op, (EClassifier)getEOperation(), "operation", 0, 1, true, true);
/* 2697 */     g1 = createEGenericType((EClassifier)this.ecorePackage.getEEList());
/* 2698 */     g2 = createEGenericType();
/* 2699 */     g1.getETypeArguments().add(g2);
/* 2700 */     addEParameter(op, g1, "arguments", 0, 1, true, true);
/* 2701 */     addEException(op, (EClassifier)getEInvocationTargetException());
/*      */     
/* 2703 */     initEClass(this.eOperationEClass, EOperation.class, "EOperation", false, false, true);
/* 2704 */     initEReference(getEOperation_EContainingClass(), (EClassifier)getEClass(), getEClass_EOperations(), "eContainingClass", (String)null, 0, 1, EOperation.class, true, false, false, false, false, false, true, false, true);
/* 2705 */     initEReference(getEOperation_ETypeParameters(), (EClassifier)getETypeParameter(), (EReference)null, "eTypeParameters", (String)null, 0, -1, EOperation.class, false, false, true, true, true, false, true, false, true);
/* 2706 */     initEReference(getEOperation_EParameters(), (EClassifier)getEParameter(), getEParameter_EOperation(), "eParameters", (String)null, 0, -1, EOperation.class, false, false, true, true, false, false, true, false, true);
/* 2707 */     initEReference(getEOperation_EExceptions(), (EClassifier)getEClassifier(), (EReference)null, "eExceptions", (String)null, 0, -1, EOperation.class, false, false, true, false, true, true, true, false, true);
/* 2708 */     initEReference(getEOperation_EGenericExceptions(), (EClassifier)getEGenericType(), (EReference)null, "eGenericExceptions", (String)null, 0, -1, EOperation.class, false, false, true, true, false, true, true, false, true);
/*      */     
/* 2710 */     initEOperation(getEOperation__GetOperationID(), (EClassifier)getEInt(), "getOperationID", 0, 1, true, true);
/*      */     
/* 2712 */     op = initEOperation(getEOperation__IsOverrideOf__EOperation(), (EClassifier)getEBoolean(), "isOverrideOf", 0, 1, true, true);
/* 2713 */     addEParameter(op, (EClassifier)getEOperation(), "someOperation", 0, 1, true, true);
/*      */     
/* 2715 */     initEClass(this.ePackageEClass, EPackage.class, "EPackage", false, false, true);
/* 2716 */     initEAttribute(getEPackage_NsURI(), (EClassifier)getEString(), "nsURI", (String)null, 0, 1, EPackage.class, false, false, true, false, false, true, false, true);
/* 2717 */     initEAttribute(getEPackage_NsPrefix(), (EClassifier)getEString(), "nsPrefix", (String)null, 0, 1, EPackage.class, false, false, true, false, false, true, false, true);
/* 2718 */     initEReference(getEPackage_EFactoryInstance(), (EClassifier)getEFactory(), getEFactory_EPackage(), "eFactoryInstance", (String)null, 1, 1, EPackage.class, true, false, true, false, false, false, true, false, true);
/* 2719 */     initEReference(getEPackage_EClassifiers(), (EClassifier)getEClassifier(), getEClassifier_EPackage(), "eClassifiers", (String)null, 0, -1, EPackage.class, false, false, true, true, true, false, true, false, true);
/* 2720 */     initEReference(getEPackage_ESubpackages(), (EClassifier)getEPackage(), getEPackage_ESuperPackage(), "eSubpackages", (String)null, 0, -1, EPackage.class, false, false, true, true, true, false, true, false, true);
/* 2721 */     initEReference(getEPackage_ESuperPackage(), (EClassifier)getEPackage(), getEPackage_ESubpackages(), "eSuperPackage", (String)null, 0, 1, EPackage.class, true, false, false, false, true, false, true, false, true);
/*      */     
/* 2723 */     op = initEOperation(getEPackage__GetEClassifier__String(), (EClassifier)getEClassifier(), "getEClassifier", 0, 1, true, true);
/* 2724 */     addEParameter(op, (EClassifier)getEString(), "name", 0, 1, true, true);
/*      */     
/* 2726 */     initEClass(this.eParameterEClass, EParameter.class, "EParameter", false, false, true);
/* 2727 */     initEReference(getEParameter_EOperation(), (EClassifier)getEOperation(), getEOperation_EParameters(), "eOperation", (String)null, 0, 1, EParameter.class, true, false, false, false, false, false, true, false, true);
/*      */     
/* 2729 */     initEClass(this.eReferenceEClass, EReference.class, "EReference", false, false, true);
/* 2730 */     initEAttribute(getEReference_Containment(), (EClassifier)getEBoolean(), "containment", (String)null, 0, 1, EReference.class, false, false, true, false, false, true, false, true);
/* 2731 */     initEAttribute(getEReference_Container(), (EClassifier)getEBoolean(), "container", (String)null, 0, 1, EReference.class, true, true, false, false, false, true, true, true);
/* 2732 */     initEAttribute(getEReference_ResolveProxies(), (EClassifier)getEBoolean(), "resolveProxies", "true", 0, 1, EReference.class, false, false, true, false, false, true, false, true);
/* 2733 */     initEReference(getEReference_EOpposite(), (EClassifier)getEReference(), (EReference)null, "eOpposite", (String)null, 0, 1, EReference.class, false, false, true, false, true, false, true, false, true);
/* 2734 */     initEReference(getEReference_EReferenceType(), (EClassifier)getEClass(), (EReference)null, "eReferenceType", (String)null, 1, 1, EReference.class, true, true, false, false, true, false, true, true, true);
/* 2735 */     initEReference(getEReference_EKeys(), (EClassifier)getEAttribute(), (EReference)null, "eKeys", (String)null, 0, -1, EReference.class, false, false, true, false, true, false, true, false, true);
/*      */     
/* 2737 */     initEClass(this.eStructuralFeatureEClass, EStructuralFeature.class, "EStructuralFeature", true, false, true);
/* 2738 */     initEAttribute(getEStructuralFeature_Changeable(), (EClassifier)getEBoolean(), "changeable", "true", 0, 1, EStructuralFeature.class, false, false, true, false, false, true, false, true);
/* 2739 */     initEAttribute(getEStructuralFeature_Volatile(), (EClassifier)getEBoolean(), "volatile", (String)null, 0, 1, EStructuralFeature.class, false, false, true, false, false, true, false, true);
/* 2740 */     initEAttribute(getEStructuralFeature_Transient(), (EClassifier)getEBoolean(), "transient", (String)null, 0, 1, EStructuralFeature.class, false, false, true, false, false, true, false, true);
/* 2741 */     initEAttribute(getEStructuralFeature_DefaultValueLiteral(), (EClassifier)getEString(), "defaultValueLiteral", (String)null, 0, 1, EStructuralFeature.class, false, false, true, false, false, true, false, true);
/* 2742 */     initEAttribute(getEStructuralFeature_DefaultValue(), (EClassifier)getEJavaObject(), "defaultValue", (String)null, 0, 1, EStructuralFeature.class, true, true, false, false, false, true, true, true);
/* 2743 */     initEAttribute(getEStructuralFeature_Unsettable(), (EClassifier)getEBoolean(), "unsettable", (String)null, 0, 1, EStructuralFeature.class, false, false, true, false, false, true, false, true);
/* 2744 */     initEAttribute(getEStructuralFeature_Derived(), (EClassifier)getEBoolean(), "derived", (String)null, 0, 1, EStructuralFeature.class, false, false, true, false, false, true, false, true);
/* 2745 */     initEReference(getEStructuralFeature_EContainingClass(), (EClassifier)getEClass(), getEClass_EStructuralFeatures(), "eContainingClass", (String)null, 0, 1, EStructuralFeature.class, true, false, false, false, false, false, true, false, true);
/*      */     
/* 2747 */     initEOperation(getEStructuralFeature__GetFeatureID(), (EClassifier)getEInt(), "getFeatureID", 0, 1, true, true);
/*      */     
/* 2749 */     op = initEOperation(getEStructuralFeature__GetContainerClass(), (EClassifier)null, "getContainerClass", 0, 1, true, true);
/* 2750 */     g1 = createEGenericType((EClassifier)getEJavaClass());
/* 2751 */     g2 = createEGenericType();
/* 2752 */     g1.getETypeArguments().add(g2);
/* 2753 */     initEOperation(op, g1);
/*      */     
/* 2755 */     initEClass(this.eTypedElementEClass, ETypedElement.class, "ETypedElement", true, false, true);
/* 2756 */     initEAttribute(getETypedElement_Ordered(), (EClassifier)getEBoolean(), "ordered", "true", 0, 1, ETypedElement.class, false, false, true, false, false, true, false, true);
/* 2757 */     initEAttribute(getETypedElement_Unique(), (EClassifier)getEBoolean(), "unique", "true", 0, 1, ETypedElement.class, false, false, true, false, false, true, false, true);
/* 2758 */     initEAttribute(getETypedElement_LowerBound(), (EClassifier)getEInt(), "lowerBound", (String)null, 0, 1, ETypedElement.class, false, false, true, false, false, true, false, true);
/* 2759 */     initEAttribute(getETypedElement_UpperBound(), (EClassifier)getEInt(), "upperBound", "1", 0, 1, ETypedElement.class, false, false, true, false, false, true, false, true);
/* 2760 */     initEAttribute(getETypedElement_Many(), (EClassifier)getEBoolean(), "many", (String)null, 0, 1, ETypedElement.class, true, true, false, false, false, true, true, true);
/* 2761 */     initEAttribute(getETypedElement_Required(), (EClassifier)getEBoolean(), "required", (String)null, 0, 1, ETypedElement.class, true, true, false, false, false, true, true, true);
/* 2762 */     initEReference(getETypedElement_EType(), (EClassifier)getEClassifier(), (EReference)null, "eType", (String)null, 0, 1, ETypedElement.class, false, true, true, false, true, true, true, false, true);
/* 2763 */     initEReference(getETypedElement_EGenericType(), (EClassifier)getEGenericType(), (EReference)null, "eGenericType", (String)null, 0, 1, ETypedElement.class, false, true, true, true, false, true, true, false, true);
/*      */     
/* 2765 */     initEClass(this.eStringToStringMapEntryEClass, Map.Entry.class, "EStringToStringMapEntry", false, false, false);
/* 2766 */     initEAttribute(getEStringToStringMapEntry_Key(), (EClassifier)getEString(), "key", (String)null, 0, 1, Map.Entry.class, false, false, true, false, false, true, false, true);
/* 2767 */     initEAttribute(getEStringToStringMapEntry_Value(), (EClassifier)getEString(), "value", (String)null, 0, 1, Map.Entry.class, false, false, true, false, false, true, false, true);
/*      */     
/* 2769 */     initEClass(this.eGenericTypeEClass, EGenericType.class, "EGenericType", false, false, true);
/* 2770 */     initEReference(getEGenericType_EUpperBound(), (EClassifier)getEGenericType(), (EReference)null, "eUpperBound", (String)null, 0, 1, EGenericType.class, false, false, true, true, false, false, true, false, true);
/* 2771 */     initEReference(getEGenericType_ETypeArguments(), (EClassifier)getEGenericType(), (EReference)null, "eTypeArguments", (String)null, 0, -1, EGenericType.class, false, false, true, true, false, false, true, false, true);
/* 2772 */     initEReference(getEGenericType_ERawType(), (EClassifier)getEClassifier(), (EReference)null, "eRawType", (String)null, 1, 1, EGenericType.class, true, false, false, false, true, false, true, true, true);
/* 2773 */     initEReference(getEGenericType_ELowerBound(), (EClassifier)getEGenericType(), (EReference)null, "eLowerBound", (String)null, 0, 1, EGenericType.class, false, false, true, true, false, false, true, false, true);
/* 2774 */     initEReference(getEGenericType_ETypeParameter(), (EClassifier)getETypeParameter(), (EReference)null, "eTypeParameter", (String)null, 0, 1, EGenericType.class, false, false, true, false, false, false, true, false, true);
/* 2775 */     initEReference(getEGenericType_EClassifier(), (EClassifier)getEClassifier(), (EReference)null, "eClassifier", (String)null, 0, 1, EGenericType.class, false, false, true, false, true, false, true, false, true);
/*      */     
/* 2777 */     initEClass(this.eTypeParameterEClass, ETypeParameter.class, "ETypeParameter", false, false, true);
/* 2778 */     initEReference(getETypeParameter_EBounds(), (EClassifier)getEGenericType(), (EReference)null, "eBounds", (String)null, 0, -1, ETypeParameter.class, false, false, true, true, false, false, true, false, true);
/*      */ 
/*      */     
/* 2781 */     initEDataType(this.eBigDecimalEDataType, BigDecimal.class, "EBigDecimal", true, false);
/* 2782 */     initEDataType(this.eBigIntegerEDataType, BigInteger.class, "EBigInteger", true, false);
/* 2783 */     initEDataType(this.eBooleanEDataType, boolean.class, "EBoolean", true, false);
/* 2784 */     initEDataType(this.eBooleanObjectEDataType, Boolean.class, "EBooleanObject", true, false);
/* 2785 */     initEDataType(this.eByteEDataType, byte.class, "EByte", true, false);
/* 2786 */     initEDataType(this.eByteArrayEDataType, byte[].class, "EByteArray", true, false);
/* 2787 */     initEDataType(this.eByteObjectEDataType, Byte.class, "EByteObject", true, false);
/* 2788 */     initEDataType(this.eCharEDataType, char.class, "EChar", true, false);
/* 2789 */     initEDataType(this.eCharacterObjectEDataType, Character.class, "ECharacterObject", true, false);
/* 2790 */     initEDataType(this.eDateEDataType, Date.class, "EDate", true, false);
/* 2791 */     initEDataType(this.eDiagnosticChainEDataType, DiagnosticChain.class, "EDiagnosticChain", false, false);
/* 2792 */     initEDataType(this.eDoubleEDataType, double.class, "EDouble", true, false);
/* 2793 */     initEDataType(this.eDoubleObjectEDataType, Double.class, "EDoubleObject", true, false);
/* 2794 */     initEDataType(this.eeListEDataType, EList.class, "EEList", false, false);
/* 2795 */     initEDataType(this.eEnumeratorEDataType, Enumerator.class, "EEnumerator", false, false);
/* 2796 */     initEDataType(this.eFeatureMapEDataType, FeatureMap.class, "EFeatureMap", false, false);
/* 2797 */     initEDataType(this.eFeatureMapEntryEDataType, FeatureMap.Entry.class, "EFeatureMapEntry", false, false);
/* 2798 */     initEDataType(this.eFloatEDataType, float.class, "EFloat", true, false);
/* 2799 */     initEDataType(this.eFloatObjectEDataType, Float.class, "EFloatObject", true, false);
/* 2800 */     initEDataType(this.eIntEDataType, int.class, "EInt", true, false);
/* 2801 */     initEDataType(this.eIntegerObjectEDataType, Integer.class, "EIntegerObject", true, false);
/* 2802 */     initEDataType(this.eJavaClassEDataType, Class.class, "EJavaClass", true, false);
/* 2803 */     initEDataType(this.eJavaObjectEDataType, Object.class, "EJavaObject", true, false);
/* 2804 */     initEDataType(this.eLongEDataType, long.class, "ELong", true, false);
/* 2805 */     initEDataType(this.eLongObjectEDataType, Long.class, "ELongObject", true, false);
/* 2806 */     initEDataType(this.eMapEDataType, Map.class, "EMap", false, false);
/* 2807 */     initEDataType(this.eResourceEDataType, Resource.class, "EResource", false, false);
/* 2808 */     initEDataType(this.eResourceSetEDataType, ResourceSet.class, "EResourceSet", false, false);
/* 2809 */     initEDataType(this.eShortEDataType, short.class, "EShort", true, false);
/* 2810 */     initEDataType(this.eShortObjectEDataType, Short.class, "EShortObject", true, false);
/* 2811 */     initEDataType(this.eStringEDataType, String.class, "EString", true, false);
/* 2812 */     initEDataType(this.eTreeIteratorEDataType, TreeIterator.class, "ETreeIterator", false, false);
/* 2813 */     initEDataType(this.eInvocationTargetExceptionEDataType, InvocationTargetException.class, "EInvocationTargetException", false, false);
/*      */ 
/*      */     
/* 2816 */     createResource("http://www.eclipse.org/emf/2002/Ecore"); } public EAttribute getEStructuralFeature_Derived() { return (EAttribute)this.eStructuralFeatureEClass.getEStructuralFeatures().get(6); } public EReference getEStructuralFeature_EContainingClass() { return (EReference)this.eStructuralFeatureEClass.getEStructuralFeatures().get(7); } public EOperation getEStructuralFeature__GetFeatureID() { return (EOperation)this.eStructuralFeatureEClass.getEOperations().get(0); } public EOperation getEStructuralFeature__GetContainerClass() { return (EOperation)this.eStructuralFeatureEClass.getEOperations().get(1); } @Deprecated public EAttribute getEStructuralFeature_Unique() { return getETypedElement_Unique(); } @Deprecated public EAttribute getEStructuralFeature_LowerBound() { return getETypedElement_LowerBound(); } @Deprecated public EAttribute getEStructuralFeature_UpperBound() { return getETypedElement_UpperBound(); } @Deprecated public EAttribute getEStructuralFeature_Many() { return getETypedElement_Many(); } @Deprecated public EAttribute getEStructuralFeature_Required() { return getETypedElement_Required(); } public EClass getEAttribute() { return this.eAttributeEClass; } public EAttribute getEAttribute_ID() { return (EAttribute)this.eAttributeEClass.getEStructuralFeatures().get(0); } public EReference getEAttribute_EAttributeType() { return (EReference)this.eAttributeEClass.getEStructuralFeatures().get(1); } public EClass getEAnnotation() { return this.eAnnotationEClass; } public EAttribute getEAnnotation_Source() { return (EAttribute)this.eAnnotationEClass.getEStructuralFeatures().get(0); } public EReference getEAnnotation_Details() { return (EReference)this.eAnnotationEClass.getEStructuralFeatures().get(1); } public EReference getEAnnotation_EModelElement() { return (EReference)this.eAnnotationEClass.getEStructuralFeatures().get(2); } public EReference getEAnnotation_Contents() { return (EReference)this.eAnnotationEClass.getEStructuralFeatures().get(3); } public EReference getEAnnotation_References() { return (EReference)this.eAnnotationEClass.getEStructuralFeatures().get(4); } public EClass getEReference() { return this.eReferenceEClass; } public EAttribute getEReference_Containment() { return (EAttribute)this.eReferenceEClass.getEStructuralFeatures().get(0); } public EAttribute getEReference_Container() { return (EAttribute)this.eReferenceEClass.getEStructuralFeatures().get(1); } public EAttribute getEReference_ResolveProxies() { return (EAttribute)this.eReferenceEClass.getEStructuralFeatures().get(2); } public EReference getEReference_EOpposite() { return (EReference)this.eReferenceEClass.getEStructuralFeatures().get(3); } public EReference getEReference_EReferenceType() { return (EReference)this.eReferenceEClass.getEStructuralFeatures().get(4); } public EReference getEReference_EKeys() { return (EReference)this.eReferenceEClass.getEStructuralFeatures().get(5); } public EDataType getEEList() { return this.eeListEDataType; } public EDataType getEResource() { return this.eResourceEDataType; } public EDataType getEResourceSet() { return this.eResourceSetEDataType; } public EDataType getEBooleanObject() { return this.eBooleanObjectEDataType; } public EDataType getECharacterObject() { return this.eCharacterObjectEDataType; } public EDataType getEDate() { return this.eDateEDataType; } public EDataType getEDiagnosticChain() { return this.eDiagnosticChainEDataType; } public EDataType getEDoubleObject() { return this.eDoubleObjectEDataType; } public EDataType getEFloatObject() { return this.eFloatObjectEDataType; } public EDataType getEIntegerObject() { return this.eIntegerObjectEDataType; } public EClass getETypedElement() { return this.eTypedElementEClass; } public EAttribute getETypedElement_Ordered() { return (EAttribute)this.eTypedElementEClass.getEStructuralFeatures().get(0); } public EAttribute getETypedElement_Unique() { return (EAttribute)this.eTypedElementEClass.getEStructuralFeatures().get(1); } public EAttribute getETypedElement_LowerBound() { return (EAttribute)this.eTypedElementEClass.getEStructuralFeatures().get(2); } public EAttribute getETypedElement_UpperBound() { return (EAttribute)this.eTypedElementEClass.getEStructuralFeatures().get(3); } public EAttribute getETypedElement_Many() { return (EAttribute)this.eTypedElementEClass.getEStructuralFeatures().get(4); } public EAttribute getETypedElement_Required() { return (EAttribute)this.eTypedElementEClass.getEStructuralFeatures().get(5); } public EReference getETypedElement_EType() { return (EReference)this.eTypedElementEClass.getEStructuralFeatures().get(6); } public EReference getETypedElement_EGenericType() { return (EReference)this.eTypedElementEClass.getEStructuralFeatures().get(7); } public EClass getEStringToStringMapEntry() { return this.eStringToStringMapEntryEClass; } public EAttribute getEStringToStringMapEntry_Key() { return (EAttribute)this.eStringToStringMapEntryEClass.getEStructuralFeatures().get(0); } public EAttribute getEStringToStringMapEntry_Value() { return (EAttribute)this.eStringToStringMapEntryEClass.getEStructuralFeatures().get(1); } public EClass getEGenericType() { return this.eGenericTypeEClass; }
/*      */   public EReference getEGenericType_EUpperBound() { return (EReference)this.eGenericTypeEClass.getEStructuralFeatures().get(0); }
/*      */   public EReference getEGenericType_ETypeArguments() { return (EReference)this.eGenericTypeEClass.getEStructuralFeatures().get(1); }
/*      */   public EReference getEGenericType_ERawType() { return (EReference)this.eGenericTypeEClass.getEStructuralFeatures().get(2); }
/*      */   public EReference getEGenericType_ELowerBound() { return (EReference)this.eGenericTypeEClass.getEStructuralFeatures().get(3); }
/*      */   public EReference getEGenericType_ETypeParameter() { return (EReference)this.eGenericTypeEClass.getEStructuralFeatures().get(4); }
/*      */   public EReference getEGenericType_EClassifier() { return (EReference)this.eGenericTypeEClass.getEStructuralFeatures().get(5); }
/*      */   public EClass getETypeParameter() { return this.eTypeParameterEClass; }
/*      */   public EReference getETypeParameter_EBounds() { return (EReference)this.eTypeParameterEClass.getEStructuralFeatures().get(0); }
/*      */   public EDataType getEBigDecimal() { return this.eBigDecimalEDataType; }
/*      */   public EDataType getEBigInteger() { return this.eBigIntegerEDataType; }
/* 2827 */   protected void createEcoreAnnotations() { String source = "http://www.eclipse.org/emf/2002/Ecore";
/* 2828 */     addAnnotation(
/* 2829 */         (ENamedElement)this.eAttributeEClass, 
/* 2830 */         source, 
/*      */         
/* 2832 */         new String[] {
/* 2833 */           "constraints", "ConsistentTransient"
/*      */         });
/* 2835 */     addAnnotation(
/* 2836 */         (ENamedElement)this.eAnnotationEClass, 
/* 2837 */         source, 
/*      */         
/* 2839 */         new String[] {
/* 2840 */           "constraints", "WellFormedSourceURI"
/*      */         });
/* 2842 */     addAnnotation(
/* 2843 */         (ENamedElement)this.eClassEClass, 
/* 2844 */         source, 
/*      */         
/* 2846 */         new String[] {
/* 2847 */           "constraints", "InterfaceIsAbstract AtMostOneID UniqueFeatureNames UniqueOperationSignatures NoCircularSuperTypes WellFormedMapEntryClass ConsistentSuperTypes DisjointFeatureAndOperationSignatures"
/*      */         });
/* 2849 */     addAnnotation(
/* 2850 */         (ENamedElement)this.eClassifierEClass, 
/* 2851 */         source, 
/*      */         
/* 2853 */         new String[] {
/* 2854 */           "constraints", "WellFormedInstanceTypeName UniqueTypeParameterNames"
/*      */         });
/* 2856 */     addAnnotation(
/* 2857 */         (ENamedElement)this.eEnumEClass, 
/* 2858 */         source, 
/*      */         
/* 2860 */         new String[] {
/* 2861 */           "constraints", "UniqueEnumeratorNames UniqueEnumeratorLiterals"
/*      */         });
/* 2863 */     addAnnotation(
/* 2864 */         (ENamedElement)this.eNamedElementEClass, 
/* 2865 */         source, 
/*      */         
/* 2867 */         new String[] {
/* 2868 */           "constraints", "WellFormedName"
/*      */         });
/* 2870 */     addAnnotation(
/* 2871 */         (ENamedElement)this.eOperationEClass, 
/* 2872 */         source, 
/*      */         
/* 2874 */         new String[] {
/* 2875 */           "constraints", "UniqueParameterNames UniqueTypeParameterNames NoRepeatingVoid"
/*      */         });
/* 2877 */     addAnnotation(
/* 2878 */         (ENamedElement)this.ePackageEClass, 
/* 2879 */         source, 
/*      */         
/* 2881 */         new String[] {
/* 2882 */           "constraints", "WellFormedNsURI WellFormedNsPrefix UniqueSubpackageNames UniqueClassifierNames UniqueNsURIs"
/*      */         });
/* 2884 */     addAnnotation(
/* 2885 */         (ENamedElement)this.eReferenceEClass, 
/* 2886 */         source, 
/*      */         
/* 2888 */         new String[] {
/* 2889 */           "constraints", "ConsistentOpposite SingleContainer ConsistentKeys ConsistentUnique ConsistentContainer"
/*      */         });
/* 2891 */     addAnnotation(
/* 2892 */         (ENamedElement)this.eStructuralFeatureEClass, 
/* 2893 */         source, 
/*      */         
/* 2895 */         new String[] {
/* 2896 */           "constraints", "ValidDefaultValueLiteral"
/*      */         });
/* 2898 */     addAnnotation(
/* 2899 */         (ENamedElement)this.eTypedElementEClass, 
/* 2900 */         source, 
/*      */         
/* 2902 */         new String[] {
/* 2903 */           "constraints", "ValidLowerBound ValidUpperBound ConsistentBounds ValidType"
/*      */         });
/* 2905 */     addAnnotation(
/* 2906 */         (ENamedElement)this.eGenericTypeEClass, 
/* 2907 */         source, 
/*      */         
/* 2909 */         new String[] {
/* 2910 */           "constraints", "ConsistentType ConsistentBounds ConsistentArguments" }); } public EClass getEParameter() { return this.eParameterEClass; } public EReference getEParameter_EOperation() { return (EReference)this.eParameterEClass.getEStructuralFeatures().get(0); } public EClass getEObject() { return this.eObjectEClass; } public EOperation getEObject__EClass() { return (EOperation)this.eObjectEClass.getEOperations().get(0); } public EOperation getEObject__EIsProxy() { return (EOperation)this.eObjectEClass.getEOperations().get(1); } public EOperation getEObject__EResource() { return (EOperation)this.eObjectEClass.getEOperations().get(2); } public EOperation getEObject__EContainer() { return (EOperation)this.eObjectEClass.getEOperations().get(3); } public EOperation getEObject__EContainingFeature() { return (EOperation)this.eObjectEClass.getEOperations().get(4); } public EOperation getEObject__EContainmentFeature() { return (EOperation)this.eObjectEClass.getEOperations().get(5); } public EOperation getEObject__EContents() { return (EOperation)this.eObjectEClass.getEOperations().get(6); } public EOperation getEObject__EAllContents() { return (EOperation)this.eObjectEClass.getEOperations().get(7); } public EOperation getEObject__ECrossReferences() { return (EOperation)this.eObjectEClass.getEOperations().get(8); } public EOperation getEObject__EGet__EStructuralFeature() { return (EOperation)this.eObjectEClass.getEOperations().get(9); } public EOperation getEObject__EGet__EStructuralFeature_boolean() { return (EOperation)this.eObjectEClass.getEOperations().get(10); } public EOperation getEObject__ESet__EStructuralFeature_Object() { return (EOperation)this.eObjectEClass.getEOperations().get(11); } public EOperation getEObject__EIsSet__EStructuralFeature() { return (EOperation)this.eObjectEClass.getEOperations().get(12); } public EOperation getEObject__EUnset__EStructuralFeature() { return (EOperation)this.eObjectEClass.getEOperations().get(13); } public EOperation getEObject__EInvoke__EOperation_EList() { return (EOperation)this.eObjectEClass.getEOperations().get(14); }
/*      */   public EClass getEPackage() { return this.ePackageEClass; }
/*      */   public EAttribute getEPackage_NsURI() { return (EAttribute)this.ePackageEClass.getEStructuralFeatures().get(0); }
/*      */   public EAttribute getEPackage_NsPrefix() { return (EAttribute)this.ePackageEClass.getEStructuralFeatures().get(1); }
/*      */   public EReference getEPackage_EFactoryInstance() { return (EReference)this.ePackageEClass.getEStructuralFeatures().get(2); }
/*      */   public EReference getEPackage_EClassifiers() { return (EReference)this.ePackageEClass.getEStructuralFeatures().get(3); }
/*      */   public EReference getEPackage_ESubpackages() { return (EReference)this.ePackageEClass.getEStructuralFeatures().get(4); }
/*      */   public EReference getEPackage_ESuperPackage() { return (EReference)this.ePackageEClass.getEStructuralFeatures().get(5); }
/*      */   public EOperation getEPackage__GetEClassifier__String() { return (EOperation)this.ePackageEClass.getEOperations().get(0); }
/*      */   public EClass getEEnum() { return this.eEnumEClass; }
/*      */   public EReference getEEnum_ELiterals() { return (EReference)this.eEnumEClass.getEStructuralFeatures().get(0); }
/*      */   public EOperation getEEnum__GetEEnumLiteral__String() { return (EOperation)this.eEnumEClass.getEOperations().get(0); }
/* 2922 */   protected void createExtendedMetaDataAnnotations() { String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
/* 2923 */     addAnnotation(
/* 2924 */         (ENamedElement)this.eBigDecimalEDataType, 
/* 2925 */         source, 
/*      */         
/* 2927 */         new String[] {
/* 2928 */           "baseType", "http://www.w3.org/2001/XMLSchema#decimal"
/*      */         });
/* 2930 */     addAnnotation(
/* 2931 */         (ENamedElement)this.eBigIntegerEDataType, 
/* 2932 */         source, 
/*      */         
/* 2934 */         new String[] {
/* 2935 */           "baseType", "http://www.w3.org/2001/XMLSchema#integer"
/*      */         });
/* 2937 */     addAnnotation(
/* 2938 */         (ENamedElement)this.eBooleanEDataType, 
/* 2939 */         source, 
/*      */         
/* 2941 */         new String[] {
/* 2942 */           "baseType", "http://www.w3.org/2001/XMLSchema#boolean"
/*      */         });
/* 2944 */     addAnnotation(
/* 2945 */         (ENamedElement)this.eBooleanObjectEDataType, 
/* 2946 */         source, 
/*      */         
/* 2948 */         new String[] {
/* 2949 */           "baseType", "EBoolean", 
/* 2950 */           "name", "EBoolean:Object"
/*      */         });
/* 2952 */     addAnnotation(
/* 2953 */         (ENamedElement)this.eByteEDataType, 
/* 2954 */         source, 
/*      */         
/* 2956 */         new String[] {
/* 2957 */           "baseType", "http://www.w3.org/2001/XMLSchema#byte"
/*      */         });
/* 2959 */     addAnnotation(
/* 2960 */         (ENamedElement)this.eByteArrayEDataType, 
/* 2961 */         source, 
/*      */         
/* 2963 */         new String[] {
/* 2964 */           "baseType", "http://www.w3.org/2001/XMLSchema#hexBinary"
/*      */         });
/* 2966 */     addAnnotation(
/* 2967 */         (ENamedElement)this.eByteObjectEDataType, 
/* 2968 */         source, 
/*      */         
/* 2970 */         new String[] {
/* 2971 */           "baseType", "EByte", 
/* 2972 */           "name", "EByte:Object"
/*      */         });
/* 2974 */     addAnnotation(
/* 2975 */         (ENamedElement)this.eCharacterObjectEDataType, 
/* 2976 */         source, 
/*      */         
/* 2978 */         new String[] {
/* 2979 */           "baseType", "EChar", 
/* 2980 */           "name", "EChar:Object"
/*      */         });
/* 2982 */     addAnnotation(
/* 2983 */         (ENamedElement)this.eDoubleEDataType, 
/* 2984 */         source, 
/*      */         
/* 2986 */         new String[] {
/* 2987 */           "baseType", "http://www.w3.org/2001/XMLSchema#double"
/*      */         });
/* 2989 */     addAnnotation(
/* 2990 */         (ENamedElement)this.eDoubleObjectEDataType, 
/* 2991 */         source, 
/*      */         
/* 2993 */         new String[] {
/* 2994 */           "baseType", "EDouble", 
/* 2995 */           "name", "EDouble:Object"
/*      */         });
/* 2997 */     addAnnotation(
/* 2998 */         (ENamedElement)this.eFloatEDataType, 
/* 2999 */         source, 
/*      */         
/* 3001 */         new String[] {
/* 3002 */           "baseType", "http://www.w3.org/2001/XMLSchema#float"
/*      */         });
/* 3004 */     addAnnotation(
/* 3005 */         (ENamedElement)this.eFloatObjectEDataType, 
/* 3006 */         source, 
/*      */         
/* 3008 */         new String[] {
/* 3009 */           "baseType", "EFloat", 
/* 3010 */           "name", "EFloat:Object"
/*      */         });
/* 3012 */     addAnnotation(
/* 3013 */         (ENamedElement)this.eIntEDataType, 
/* 3014 */         source, 
/*      */         
/* 3016 */         new String[] {
/* 3017 */           "baseType", "http://www.w3.org/2001/XMLSchema#int"
/*      */         });
/* 3019 */     addAnnotation(
/* 3020 */         (ENamedElement)this.eIntegerObjectEDataType, 
/* 3021 */         source, 
/*      */         
/* 3023 */         new String[] {
/* 3024 */           "baseType", "EInt", 
/* 3025 */           "name", "EInt:Object"
/*      */         });
/* 3027 */     addAnnotation(
/* 3028 */         (ENamedElement)this.eLongEDataType, 
/* 3029 */         source, 
/*      */         
/* 3031 */         new String[] {
/* 3032 */           "baseType", "http://www.w3.org/2001/XMLSchema#long"
/*      */         });
/* 3034 */     addAnnotation(
/* 3035 */         (ENamedElement)this.eLongObjectEDataType, 
/* 3036 */         source, 
/*      */         
/* 3038 */         new String[] {
/* 3039 */           "baseType", "ELong", 
/* 3040 */           "name", "ELong:Object"
/*      */         });
/* 3042 */     addAnnotation(
/* 3043 */         (ENamedElement)this.eShortEDataType, 
/* 3044 */         source, 
/*      */         
/* 3046 */         new String[] {
/* 3047 */           "baseType", "http://www.w3.org/2001/XMLSchema#short"
/*      */         });
/* 3049 */     addAnnotation(
/* 3050 */         (ENamedElement)this.eShortObjectEDataType, 
/* 3051 */         source, 
/*      */         
/* 3053 */         new String[] {
/* 3054 */           "baseType", "EShort", 
/* 3055 */           "name", "EShort:Object"
/*      */         });
/* 3057 */     addAnnotation(
/* 3058 */         (ENamedElement)this.eStringEDataType, 
/* 3059 */         source, 
/*      */         
/* 3061 */         new String[] {
/* 3062 */           "baseType", "http://www.w3.org/2001/XMLSchema#string" }); } public EOperation getEEnum__GetEEnumLiteral__int() { return (EOperation)this.eEnumEClass.getEOperations().get(1); } public EOperation getEEnum__GetEEnumLiteralByLiteral__String() { return (EOperation)this.eEnumEClass.getEOperations().get(2); } public EClass getEEnumLiteral() { return this.eEnumLiteralEClass; } public EAttribute getEEnumLiteral_Value() { return (EAttribute)this.eEnumLiteralEClass.getEStructuralFeatures().get(0); } public EAttribute getEEnumLiteral_Instance() { return (EAttribute)this.eEnumLiteralEClass.getEStructuralFeatures().get(1); }
/*      */   public EAttribute getEEnumLiteral_Literal() { return (EAttribute)this.eEnumLiteralEClass.getEStructuralFeatures().get(2); }
/*      */   public EReference getEEnumLiteral_EEnum() { return (EReference)this.eEnumLiteralEClass.getEStructuralFeatures().get(3); }
/*      */   public EDataType getEBoolean() { return this.eBooleanEDataType; }
/*      */   public EDataType getEByteObject() { return this.eByteObjectEDataType; }
/*      */   public EDataType getEJavaClass() { return this.eJavaClassEDataType; }
/*      */   public EDataType getEJavaObject() { return this.eJavaObjectEDataType; }
/*      */   public EDataType getELongObject() { return this.eLongObjectEDataType; }
/*      */   public EDataType getEMap() { return this.eMapEDataType; }
/*      */   public EDataType getEShortObject() { return this.eShortObjectEDataType; }
/*      */   public EDataType getEString() { return this.eStringEDataType; }
/* 3073 */   public EClass getEFactory() { return this.eFactoryEClass; } public EDataType getEInt() { return this.eIntEDataType; } public EDataType getEFloat() { return this.eFloatEDataType; } public EDataType getELong() { return this.eLongEDataType; } public EDataType getEDouble() { return this.eDoubleEDataType; } public EDataType getEShort() { return this.eShortEDataType; } public EDataType getETreeIterator() { return this.eTreeIteratorEDataType; }
/*      */   public EDataType getEInvocationTargetException() { return this.eInvocationTargetExceptionEDataType; }
/*      */   public EDataType getEFeatureMapEntry() { return this.eFeatureMapEntryEDataType; }
/*      */   public EDataType getEEnumerator() { return this.eEnumeratorEDataType; }
/*      */   public EDataType getEFeatureMap() { return this.eFeatureMapEDataType; }
/*      */   public EDataType getEChar() { return this.eCharEDataType; }
/*      */   public EDataType getEByte() { return this.eByteEDataType; }
/*      */   public EDataType getEByteArray() { return this.eByteArrayEDataType; }
/*      */   public EcoreFactory getEcoreFactory() { return (EcoreFactory)getEFactoryInstance(); }
/*      */   public void createPackageContents() { if (this.isCreated) return;  this.isCreated = true; this.eAttributeEClass = createEClass(0); createEAttribute(this.eAttributeEClass, 18); createEReference(this.eAttributeEClass, 19); this.eAnnotationEClass = createEClass(1); createEAttribute(this.eAnnotationEClass, 1); createEReference(this.eAnnotationEClass, 2); createEReference(this.eAnnotationEClass, 3); createEReference(this.eAnnotationEClass, 4); createEReference(this.eAnnotationEClass, 5); this.eClassEClass = createEClass(2); createEAttribute(this.eClassEClass, 8); createEAttribute(this.eClassEClass, 9); createEReference(this.eClassEClass, 10); createEReference(this.eClassEClass, 11); createEReference(this.eClassEClass, 12); createEReference(this.eClassEClass, 13); createEReference(this.eClassEClass, 14); createEReference(this.eClassEClass, 15); createEReference(this.eClassEClass, 16); createEReference(this.eClassEClass, 17); createEReference(this.eClassEClass, 18); createEReference(this.eClassEClass, 19); createEReference(this.eClassEClass, 20); createEReference(this.eClassEClass, 21); createEReference(this.eClassEClass, 22); createEReference(this.eClassEClass, 23); createEOperation(this.eClassEClass, 3); createEOperation(this.eClassEClass, 4); createEOperation(this.eClassEClass, 5); createEOperation(this.eClassEClass, 6); createEOperation(this.eClassEClass, 7); createEOperation(this.eClassEClass, 8); createEOperation(this.eClassEClass, 9); createEOperation(this.eClassEClass, 10); createEOperation(this.eClassEClass, 11); this.eClassifierEClass = createEClass(3); createEAttribute(this.eClassifierEClass, 2); createEAttribute(this.eClassifierEClass, 3); createEAttribute(this.eClassifierEClass, 4); createEAttribute(this.eClassifierEClass, 5); createEReference(this.eClassifierEClass, 6); createEReference(this.eClassifierEClass, 7); createEOperation(this.eClassifierEClass, 1); createEOperation(this.eClassifierEClass, 2); this.eDataTypeEClass = createEClass(4); createEAttribute(this.eDataTypeEClass, 8); this.eEnumEClass = createEClass(5); createEReference(this.eEnumEClass, 9); createEOperation(this.eEnumEClass, 3); createEOperation(this.eEnumEClass, 4); createEOperation(this.eEnumEClass, 5); this.eEnumLiteralEClass = createEClass(6); createEAttribute(this.eEnumLiteralEClass, 2); createEAttribute(this.eEnumLiteralEClass, 3); createEAttribute(this.eEnumLiteralEClass, 4); createEReference(this.eEnumLiteralEClass, 5); this.eFactoryEClass = createEClass(7); createEReference(this.eFactoryEClass, 1); createEOperation(this.eFactoryEClass, 1); createEOperation(this.eFactoryEClass, 2); createEOperation(this.eFactoryEClass, 3); this.eModelElementEClass = createEClass(8); createEReference(this.eModelElementEClass, 0); createEOperation(this.eModelElementEClass, 0); this.eNamedElementEClass = createEClass(9); createEAttribute(this.eNamedElementEClass, 1); this.eObjectEClass = createEClass(10); createEOperation(this.eObjectEClass, 0); createEOperation(this.eObjectEClass, 1); createEOperation(this.eObjectEClass, 2); createEOperation(this.eObjectEClass, 3); createEOperation(this.eObjectEClass, 4); createEOperation(this.eObjectEClass, 5); createEOperation(this.eObjectEClass, 6); createEOperation(this.eObjectEClass, 7); createEOperation(this.eObjectEClass, 8); createEOperation(this.eObjectEClass, 9); createEOperation(this.eObjectEClass, 10); createEOperation(this.eObjectEClass, 11); createEOperation(this.eObjectEClass, 12); createEOperation(this.eObjectEClass, 13); createEOperation(this.eObjectEClass, 14); this.eOperationEClass = createEClass(11); createEReference(this.eOperationEClass, 10); createEReference(this.eOperationEClass, 11); createEReference(this.eOperationEClass, 12); createEReference(this.eOperationEClass, 13); createEReference(this.eOperationEClass, 14); createEOperation(this.eOperationEClass, 1); createEOperation(this.eOperationEClass, 2); this.ePackageEClass = createEClass(12); createEAttribute(this.ePackageEClass, 2); createEAttribute(this.ePackageEClass, 3); createEReference(this.ePackageEClass, 4); createEReference(this.ePackageEClass, 5); createEReference(this.ePackageEClass, 6); createEReference(this.ePackageEClass, 7); createEOperation(this.ePackageEClass, 1); this.eParameterEClass = createEClass(13); createEReference(this.eParameterEClass, 10); this.eReferenceEClass = createEClass(14); createEAttribute(this.eReferenceEClass, 18); createEAttribute(this.eReferenceEClass, 19); createEAttribute(this.eReferenceEClass, 20); createEReference(this.eReferenceEClass, 21); createEReference(this.eReferenceEClass, 22); createEReference(this.eReferenceEClass, 23); this.eStructuralFeatureEClass = createEClass(15); createEAttribute(this.eStructuralFeatureEClass, 10); createEAttribute(this.eStructuralFeatureEClass, 11); createEAttribute(this.eStructuralFeatureEClass, 12); createEAttribute(this.eStructuralFeatureEClass, 13); createEAttribute(this.eStructuralFeatureEClass, 14); createEAttribute(this.eStructuralFeatureEClass, 15); createEAttribute(this.eStructuralFeatureEClass, 16); createEReference(this.eStructuralFeatureEClass, 17); createEOperation(this.eStructuralFeatureEClass, 1); createEOperation(this.eStructuralFeatureEClass, 2); this.eTypedElementEClass = createEClass(16); createEAttribute(this.eTypedElementEClass, 2); createEAttribute(this.eTypedElementEClass, 3); createEAttribute(this.eTypedElementEClass, 4); createEAttribute(this.eTypedElementEClass, 5); createEAttribute(this.eTypedElementEClass, 6); createEAttribute(this.eTypedElementEClass, 7); createEReference(this.eTypedElementEClass, 8); createEReference(this.eTypedElementEClass, 9); this.eStringToStringMapEntryEClass = createEClass(17); createEAttribute(this.eStringToStringMapEntryEClass, 0); createEAttribute(this.eStringToStringMapEntryEClass, 1); this.eGenericTypeEClass = createEClass(18); createEReference(this.eGenericTypeEClass, 0); createEReference(this.eGenericTypeEClass, 1); createEReference(this.eGenericTypeEClass, 2); createEReference(this.eGenericTypeEClass, 3); createEReference(this.eGenericTypeEClass, 4); createEReference(this.eGenericTypeEClass, 5); this.eTypeParameterEClass = createEClass(19); createEReference(this.eTypeParameterEClass, 2); this.eBigDecimalEDataType = createEDataType(20); this.eBigIntegerEDataType = createEDataType(21); this.eBooleanEDataType = createEDataType(22); this.eBooleanObjectEDataType = createEDataType(23); this.eByteEDataType = createEDataType(24); this.eByteArrayEDataType = createEDataType(25); this.eByteObjectEDataType = createEDataType(26); this.eCharEDataType = createEDataType(27); this.eCharacterObjectEDataType = createEDataType(28); this.eDateEDataType = createEDataType(29); this.eDiagnosticChainEDataType = createEDataType(30); this.eDoubleEDataType = createEDataType(31); this.eDoubleObjectEDataType = createEDataType(32); this.eeListEDataType = createEDataType(33); this.eEnumeratorEDataType = createEDataType(34); this.eFeatureMapEDataType = createEDataType(35); this.eFeatureMapEntryEDataType = createEDataType(36); this.eFloatEDataType = createEDataType(37); this.eFloatObjectEDataType = createEDataType(38); this.eIntEDataType = createEDataType(39); this.eIntegerObjectEDataType = createEDataType(40); this.eJavaClassEDataType = createEDataType(41); this.eJavaObjectEDataType = createEDataType(42); this.eLongEDataType = createEDataType(43); this.eLongObjectEDataType = createEDataType(44); this.eMapEDataType = createEDataType(45); this.eResourceEDataType = createEDataType(46); this.eResourceSetEDataType = createEDataType(47); this.eShortEDataType = createEDataType(48); this.eShortObjectEDataType = createEDataType(49); this.eStringEDataType = createEDataType(50); this.eTreeIteratorEDataType = createEDataType(51); this.eInvocationTargetExceptionEDataType = createEDataType(52); }
/* 3083 */   public EReference getEFactory_EPackage() { return (EReference)this.eFactoryEClass.getEStructuralFeatures().get(0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EOperation getEFactory__Create__EClass() {
/* 3093 */     return (EOperation)this.eFactoryEClass.getEOperations().get(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EOperation getEFactory__CreateFromString__EDataType_String() {
/* 3103 */     return (EOperation)this.eFactoryEClass.getEOperations().get(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EOperation getEFactory__ConvertToString__EDataType_Object() {
/* 3113 */     return (EOperation)this.eFactoryEClass.getEOperations().get(2);
/*      */   } }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EcorePackageImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */