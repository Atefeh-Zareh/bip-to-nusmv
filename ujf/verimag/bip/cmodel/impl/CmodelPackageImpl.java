/*      */ package ujf.verimag.bip.cmodel.impl;
/*      */ 
/*      */ import org.eclipse.emf.common.util.Enumerator;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EEnum;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.ENamedElement;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*      */ import ujf.verimag.bip.cmodel.CArgument;
/*      */ import ujf.verimag.bip.cmodel.CAssignStm;
/*      */ import ujf.verimag.bip.cmodel.CAssignType;
/*      */ import ujf.verimag.bip.cmodel.CBlock;
/*      */ import ujf.verimag.bip.cmodel.CBlockStm;
/*      */ import ujf.verimag.bip.cmodel.CBodyItem;
/*      */ import ujf.verimag.bip.cmodel.CCall;
/*      */ import ujf.verimag.bip.cmodel.CCallable;
/*      */ import ujf.verimag.bip.cmodel.CCaseItem;
/*      */ import ujf.verimag.bip.cmodel.CClass;
/*      */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*      */ import ujf.verimag.bip.cmodel.CConditionalStm;
/*      */ import ujf.verimag.bip.cmodel.CConstructor;
/*      */ import ujf.verimag.bip.cmodel.CCreator;
/*      */ import ujf.verimag.bip.cmodel.CData;
/*      */ import ujf.verimag.bip.cmodel.CEnumType;
/*      */ import ujf.verimag.bip.cmodel.CExpression;
/*      */ import ujf.verimag.bip.cmodel.CFor;
/*      */ import ujf.verimag.bip.cmodel.CFunction;
/*      */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*      */ import ujf.verimag.bip.cmodel.CHeaderText;
/*      */ import ujf.verimag.bip.cmodel.CHierarchy;
/*      */ import ujf.verimag.bip.cmodel.CIfStm;
/*      */ import ujf.verimag.bip.cmodel.CInclude;
/*      */ import ujf.verimag.bip.cmodel.CIndexed;
/*      */ import ujf.verimag.bip.cmodel.CInitParameter;
/*      */ import ujf.verimag.bip.cmodel.CInitialization;
/*      */ import ujf.verimag.bip.cmodel.CItem;
/*      */ import ujf.verimag.bip.cmodel.CJump;
/*      */ import ujf.verimag.bip.cmodel.CLiteral;
/*      */ import ujf.verimag.bip.cmodel.CModule;
/*      */ import ujf.verimag.bip.cmodel.CNavigation;
/*      */ import ujf.verimag.bip.cmodel.COperation;
/*      */ import ujf.verimag.bip.cmodel.CPointed;
/*      */ import ujf.verimag.bip.cmodel.CReturn;
/*      */ import ujf.verimag.bip.cmodel.CSimpleName;
/*      */ import ujf.verimag.bip.cmodel.CStm;
/*      */ import ujf.verimag.bip.cmodel.CStructured;
/*      */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*      */ import ujf.verimag.bip.cmodel.CText;
/*      */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*      */ import ujf.verimag.bip.cmodel.CTypedElement;
/*      */ import ujf.verimag.bip.cmodel.CWhileStm;
/*      */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*      */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*      */ import ujf.verimag.bip.cmodel.JumpType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CmodelPackageImpl
/*      */   extends EPackageImpl
/*      */   implements CmodelPackage
/*      */ {
/*   76 */   private EClass cStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   83 */   private EClass cBlockEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   90 */   private EClass cConditionalStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   97 */   private EClass cSwitchStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  104 */   private EClass cWhileStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  111 */   private EClass cIfStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  118 */   private EClass cExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  125 */   private EClass cCaseItemEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  132 */   private EClass cJumpEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   private EClass cCallEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   private EClass cAssignStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  153 */   private EClass cFunctionCallEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  160 */   private EClass cCreatorEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  167 */   private EClass cTypedElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  174 */   private EClass cOperationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  181 */   private EClass cTypeConvertionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  188 */   private EClass cHierarchyEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  195 */   private EClass cSimpleNameEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  202 */   private EClass cIndexedEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  209 */   private EClass cStructuredEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   private EClass cPointedEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  223 */   private EClass cLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  230 */   private EClass cModuleEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  237 */   private EClass cIncludeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  244 */   private EClass cEnumTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  251 */   private EClass cClassEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  258 */   private EClass cCallableEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  265 */   private EClass cDataEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  272 */   private EClass cArgumentEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  279 */   private EClass cInitParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  286 */   private EClass cFunctionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  293 */   private EClass cConstructorEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  300 */   private EClass cItemEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  307 */   private EClass cTextEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  314 */   private EClass cBodyItemEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  321 */   private EClass cBlockStmEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  328 */   private EClass cNavigationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  335 */   private EClass cReturnEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  342 */   private EClass cInitializationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  349 */   private EClass cForEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  356 */   private EClass cConditionalExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  363 */   private EClass cHeaderTextEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  370 */   private EEnum jumpTypeEEnum = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  377 */   private EEnum cAssignTypeEEnum = null;
/*      */   private static boolean isInited = false;
/*      */   private boolean isCreated;
/*      */   private boolean isInitialized;
/*      */   public static CmodelPackage init() { if (isInited) return (CmodelPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/cmodel.ecore");  CmodelPackageImpl theCmodelPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/cmodel.ecore") instanceof CmodelPackageImpl) ? (CmodelPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/cmodel.ecore") : new CmodelPackageImpl(); isInited = true; theCmodelPackage.createPackageContents(); theCmodelPackage.initializePackageContents(); theCmodelPackage.freeze(); EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/cmodel.ecore", theCmodelPackage); return theCmodelPackage; }
/*      */   public EClass getCStm() { return this.cStmEClass; }
/*      */   public EClass getCBlock() { return this.cBlockEClass; }
/*      */   public EReference getCBlock_Content() { return (EReference)this.cBlockEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCConditionalStm() { return this.cConditionalStmEClass; }
/*      */   public EReference getCConditionalStm_Condition() { return (EReference)this.cConditionalStmEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCSwitchStm() { return this.cSwitchStmEClass; }
/*      */   public EReference getCSwitchStm_CaseAction() { return (EReference)this.cSwitchStmEClass.getEStructuralFeatures().get(0); }
/*      */   public EAttribute getCSwitchStm_Selector() { return (EAttribute)this.cSwitchStmEClass.getEStructuralFeatures().get(1); }
/*      */   public EClass getCWhileStm() { return this.cWhileStmEClass; }
/*      */   public EClass getCIfStm() { return this.cIfStmEClass; }
/*      */   public EReference getCIfStm_IfCase() { return (EReference)this.cIfStmEClass.getEStructuralFeatures().get(0); }
/*      */   public EReference getCIfStm_ElseCase() { return (EReference)this.cIfStmEClass.getEStructuralFeatures().get(1); }
/*      */   public EClass getCExpression() { return this.cExpressionEClass; }
/*  395 */   public EClass getCCaseItem() { return this.cCaseItemEClass; } public EAttribute getCCaseItem_CaseValue() { return (EAttribute)this.cCaseItemEClass.getEStructuralFeatures().get(0); } public EClass getCJump() { return this.cJumpEClass; } public EAttribute getCJump_Type() { return (EAttribute)this.cJumpEClass.getEStructuralFeatures().get(0); } public EClass getCCall() { return this.cCallEClass; } public EReference getCCall_Argument() { return (EReference)this.cCallEClass.getEStructuralFeatures().get(0); } public EClass getCAssignStm() { return this.cAssignStmEClass; } public EReference getCAssignStm_Source() { return (EReference)this.cAssignStmEClass.getEStructuralFeatures().get(0); } public EReference getCAssignStm_Target() { return (EReference)this.cAssignStmEClass.getEStructuralFeatures().get(1); } public EAttribute getCAssignStm_Type() { return (EAttribute)this.cAssignStmEClass.getEStructuralFeatures().get(2); } public EClass getCFunctionCall() { return this.cFunctionCallEClass; } public EAttribute getCFunctionCall_FunctionName() { return (EAttribute)this.cFunctionCallEClass.getEStructuralFeatures().get(0); } public EClass getCCreator() { return this.cCreatorEClass; } public EAttribute getCCreator_ArrayAllocator() { return (EAttribute)this.cCreatorEClass.getEStructuralFeatures().get(0); } public EClass getCTypedElement() { return this.cTypedElementEClass; } public EAttribute getCTypedElement_Type() { return (EAttribute)this.cTypedElementEClass.getEStructuralFeatures().get(0); } public EClass getCOperation() { return this.cOperationEClass; } public EReference getCOperation_LeftOperand() { return (EReference)this.cOperationEClass.getEStructuralFeatures().get(0); } public EReference getCOperation_RightOperand() { return (EReference)this.cOperationEClass.getEStructuralFeatures().get(1); } public EAttribute getCOperation_Operator() { return (EAttribute)this.cOperationEClass.getEStructuralFeatures().get(2); } public EClass getCTypeConvertion() { return this.cTypeConvertionEClass; } public EReference getCTypeConvertion_ConvertedExpression() { return (EReference)this.cTypeConvertionEClass.getEStructuralFeatures().get(0); } public EClass getCHierarchy() { return this.cHierarchyEClass; } private CmodelPackageImpl() { super("http:///ujf/verimag/bip/cmodel.ecore", (EFactory)CmodelFactory.eINSTANCE);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1354 */     this.isCreated = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1518 */     this.isInitialized = false; }
/*      */   public EReference getCHierarchy_Field() { return (EReference)this.cHierarchyEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCSimpleName() { return this.cSimpleNameEClass; }
/*      */   public EAttribute getCSimpleName_Name() { return (EAttribute)this.cSimpleNameEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCIndexed() { return this.cIndexedEClass; }
/*      */   public EReference getCIndexed_Index() { return (EReference)this.cIndexedEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCStructured() { return this.cStructuredEClass; }
/*      */   public EClass getCPointed() { return this.cPointedEClass; }
/*      */   public EClass getCLiteral() { return this.cLiteralEClass; }
/*      */   public EAttribute getCLiteral_Value() { return (EAttribute)this.cLiteralEClass.getEStructuralFeatures().get(0); }
/* 1528 */   public EClass getCModule() { return this.cModuleEClass; } public EReference getCModule_CImport() { return (EReference)this.cModuleEClass.getEStructuralFeatures().get(0); } public EReference getCModule_Content() { return (EReference)this.cModuleEClass.getEStructuralFeatures().get(1); } public EAttribute getCModule_Namespace() { return (EAttribute)this.cModuleEClass.getEStructuralFeatures().get(2); } public void initializePackageContents() { if (this.isInitialized)
/* 1529 */       return;  this.isInitialized = true;
/*      */ 
/*      */     
/* 1532 */     setName("cmodel");
/* 1533 */     setNsPrefix("ujf.verimag.bip.cmodel");
/* 1534 */     setNsURI("http:///ujf/verimag/bip/cmodel.ecore");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1541 */     this.cStmEClass.getESuperTypes().add(getCBodyItem());
/* 1542 */     this.cConditionalStmEClass.getESuperTypes().add(getCStm());
/* 1543 */     this.cSwitchStmEClass.getESuperTypes().add(getCStm());
/* 1544 */     this.cWhileStmEClass.getESuperTypes().add(getCBlock());
/* 1545 */     this.cWhileStmEClass.getESuperTypes().add(getCConditionalStm());
/* 1546 */     this.cIfStmEClass.getESuperTypes().add(getCConditionalStm());
/* 1547 */     this.cExpressionEClass.getESuperTypes().add(getCStm());
/* 1548 */     this.cCaseItemEClass.getESuperTypes().add(getCBlock());
/* 1549 */     this.cJumpEClass.getESuperTypes().add(getCStm());
/* 1550 */     this.cCallEClass.getESuperTypes().add(getCExpression());
/* 1551 */     this.cAssignStmEClass.getESuperTypes().add(getCStm());
/* 1552 */     this.cFunctionCallEClass.getESuperTypes().add(getCCall());
/* 1553 */     this.cFunctionCallEClass.getESuperTypes().add(getCTypedElement());
/* 1554 */     this.cCreatorEClass.getESuperTypes().add(getCCall());
/* 1555 */     this.cCreatorEClass.getESuperTypes().add(getCTypedElement());
/* 1556 */     this.cOperationEClass.getESuperTypes().add(getCExpression());
/* 1557 */     this.cTypeConvertionEClass.getESuperTypes().add(getCExpression());
/* 1558 */     this.cTypeConvertionEClass.getESuperTypes().add(getCTypedElement());
/* 1559 */     this.cHierarchyEClass.getESuperTypes().add(getCNavigation());
/* 1560 */     this.cSimpleNameEClass.getESuperTypes().add(getCExpression());
/* 1561 */     this.cIndexedEClass.getESuperTypes().add(getCNavigation());
/* 1562 */     this.cStructuredEClass.getESuperTypes().add(getCHierarchy());
/* 1563 */     this.cPointedEClass.getESuperTypes().add(getCHierarchy());
/* 1564 */     this.cLiteralEClass.getESuperTypes().add(getCExpression());
/* 1565 */     this.cEnumTypeEClass.getESuperTypes().add(getCTypedElement());
/* 1566 */     this.cEnumTypeEClass.getESuperTypes().add(getCItem());
/* 1567 */     this.cClassEClass.getESuperTypes().add(getCTypedElement());
/* 1568 */     this.cClassEClass.getESuperTypes().add(getCItem());
/* 1569 */     this.cCallableEClass.getESuperTypes().add(getCItem());
/* 1570 */     this.cCallableEClass.getESuperTypes().add(getCTypedElement());
/* 1571 */     this.cCallableEClass.getESuperTypes().add(getCBlock());
/* 1572 */     this.cDataEClass.getESuperTypes().add(getCTypedElement());
/* 1573 */     this.cDataEClass.getESuperTypes().add(getCItem());
/* 1574 */     this.cDataEClass.getESuperTypes().add(getCBodyItem());
/* 1575 */     this.cArgumentEClass.getESuperTypes().add(getCTypedElement());
/* 1576 */     this.cFunctionEClass.getESuperTypes().add(getCCallable());
/* 1577 */     this.cConstructorEClass.getESuperTypes().add(getCCallable());
/* 1578 */     this.cTextEClass.getESuperTypes().add(getCStm());
/* 1579 */     this.cTextEClass.getESuperTypes().add(getCItem());
/* 1580 */     this.cBlockStmEClass.getESuperTypes().add(getCBlock());
/* 1581 */     this.cBlockStmEClass.getESuperTypes().add(getCStm());
/* 1582 */     this.cNavigationEClass.getESuperTypes().add(getCExpression());
/* 1583 */     this.cReturnEClass.getESuperTypes().add(getCStm());
/* 1584 */     this.cForEClass.getESuperTypes().add(getCBlock());
/* 1585 */     this.cForEClass.getESuperTypes().add(getCStm());
/* 1586 */     this.cConditionalExpressionEClass.getESuperTypes().add(getCExpression());
/* 1587 */     this.cConditionalExpressionEClass.getESuperTypes().add(getCConditionalStm());
/* 1588 */     this.cHeaderTextEClass.getESuperTypes().add(getCItem());
/*      */ 
/*      */     
/* 1591 */     initEClass(this.cStmEClass, CStm.class, "CStm", false, false, true);
/*      */     
/* 1593 */     initEClass(this.cBlockEClass, CBlock.class, "CBlock", true, false, true);
/* 1594 */     initEReference(getCBlock_Content(), (EClassifier)getCBodyItem(), null, "content", null, 0, -1, CBlock.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1596 */     initEClass(this.cConditionalStmEClass, CConditionalStm.class, "CConditionalStm", false, false, true);
/* 1597 */     initEReference(getCConditionalStm_Condition(), (EClassifier)getCExpression(), null, "condition", null, 1, 1, CConditionalStm.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1599 */     initEClass(this.cSwitchStmEClass, CSwitchStm.class, "CSwitchStm", false, false, true);
/* 1600 */     initEReference(getCSwitchStm_CaseAction(), (EClassifier)getCCaseItem(), null, "caseAction", null, 0, -1, CSwitchStm.class, false, false, true, true, false, false, true, false, true);
/* 1601 */     initEAttribute(getCSwitchStm_Selector(), (EClassifier)this.ecorePackage.getEString(), "selector", null, 0, 1, CSwitchStm.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1603 */     initEClass(this.cWhileStmEClass, CWhileStm.class, "CWhileStm", false, false, true);
/*      */     
/* 1605 */     initEClass(this.cIfStmEClass, CIfStm.class, "CIfStm", false, false, true);
/* 1606 */     initEReference(getCIfStm_IfCase(), (EClassifier)getCStm(), null, "ifCase", null, 1, 1, CIfStm.class, false, false, true, true, false, false, true, false, true);
/* 1607 */     initEReference(getCIfStm_ElseCase(), (EClassifier)getCStm(), null, "elseCase", null, 0, 1, CIfStm.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1609 */     initEClass(this.cExpressionEClass, CExpression.class, "CExpression", false, false, true);
/*      */     
/* 1611 */     initEClass(this.cCaseItemEClass, CCaseItem.class, "CCaseItem", false, false, true);
/* 1612 */     initEAttribute(getCCaseItem_CaseValue(), (EClassifier)this.ecorePackage.getEString(), "caseValue", null, 0, 1, CCaseItem.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1614 */     initEClass(this.cJumpEClass, CJump.class, "CJump", false, false, true);
/* 1615 */     initEAttribute(getCJump_Type(), (EClassifier)getJumpType(), "type", null, 0, 1, CJump.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1617 */     initEClass(this.cCallEClass, CCall.class, "CCall", false, false, true);
/* 1618 */     initEReference(getCCall_Argument(), (EClassifier)getCExpression(), null, "argument", null, 0, -1, CCall.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1620 */     initEClass(this.cAssignStmEClass, CAssignStm.class, "CAssignStm", false, false, true);
/* 1621 */     initEReference(getCAssignStm_Source(), (EClassifier)getCExpression(), null, "source", null, 1, 1, CAssignStm.class, false, false, true, true, false, false, true, false, true);
/* 1622 */     initEReference(getCAssignStm_Target(), (EClassifier)getCExpression(), null, "target", null, 1, 1, CAssignStm.class, false, false, true, true, false, false, true, false, true);
/* 1623 */     initEAttribute(getCAssignStm_Type(), (EClassifier)getCAssignType(), "type", null, 1, 1, CAssignStm.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1625 */     initEClass(this.cFunctionCallEClass, CFunctionCall.class, "CFunctionCall", false, false, true);
/* 1626 */     initEAttribute(getCFunctionCall_FunctionName(), (EClassifier)this.ecorePackage.getEString(), "functionName", null, 0, 1, CFunctionCall.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1628 */     initEClass(this.cCreatorEClass, CCreator.class, "CCreator", false, false, true);
/* 1629 */     initEAttribute(getCCreator_ArrayAllocator(), (EClassifier)this.ecorePackage.getEBoolean(), "arrayAllocator", null, 0, 1, CCreator.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1631 */     initEClass(this.cTypedElementEClass, CTypedElement.class, "CTypedElement", false, false, true);
/* 1632 */     initEAttribute(getCTypedElement_Type(), (EClassifier)this.ecorePackage.getEString(), "type", null, 0, 1, CTypedElement.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1634 */     initEClass(this.cOperationEClass, COperation.class, "COperation", false, false, true);
/* 1635 */     initEReference(getCOperation_LeftOperand(), (EClassifier)getCExpression(), null, "leftOperand", null, 0, 1, COperation.class, false, false, true, true, false, false, true, false, true);
/* 1636 */     initEReference(getCOperation_RightOperand(), (EClassifier)getCExpression(), null, "rightOperand", null, 1, 1, COperation.class, false, false, true, true, false, false, true, false, true);
/* 1637 */     initEAttribute(getCOperation_Operator(), (EClassifier)this.ecorePackage.getEString(), "operator", null, 0, 1, COperation.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1639 */     initEClass(this.cTypeConvertionEClass, CTypeConvertion.class, "CTypeConvertion", false, false, true);
/* 1640 */     initEReference(getCTypeConvertion_ConvertedExpression(), (EClassifier)getCExpression(), null, "convertedExpression", null, 0, 1, CTypeConvertion.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1642 */     initEClass(this.cHierarchyEClass, CHierarchy.class, "CHierarchy", true, false, true);
/* 1643 */     initEReference(getCHierarchy_Field(), (EClassifier)getCExpression(), null, "field", null, 1, 1, CHierarchy.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1645 */     initEClass(this.cSimpleNameEClass, CSimpleName.class, "CSimpleName", false, false, true);
/* 1646 */     initEAttribute(getCSimpleName_Name(), (EClassifier)this.ecorePackage.getEString(), "name", null, 0, 1, CSimpleName.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1648 */     initEClass(this.cIndexedEClass, CIndexed.class, "CIndexed", false, false, true);
/* 1649 */     initEReference(getCIndexed_Index(), (EClassifier)getCExpression(), null, "index", null, 0, 1, CIndexed.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1651 */     initEClass(this.cStructuredEClass, CStructured.class, "CStructured", false, false, true);
/*      */     
/* 1653 */     initEClass(this.cPointedEClass, CPointed.class, "CPointed", false, false, true);
/*      */     
/* 1655 */     initEClass(this.cLiteralEClass, CLiteral.class, "CLiteral", false, false, true);
/* 1656 */     initEAttribute(getCLiteral_Value(), (EClassifier)this.ecorePackage.getEString(), "value", null, 0, 1, CLiteral.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1658 */     initEClass(this.cModuleEClass, CModule.class, "CModule", false, false, true);
/* 1659 */     initEReference(getCModule_CImport(), (EClassifier)getCInclude(), null, "cImport", null, 0, -1, CModule.class, false, false, true, true, false, false, true, false, true);
/* 1660 */     initEReference(getCModule_Content(), (EClassifier)getCItem(), null, "content", null, 0, -1, CModule.class, false, false, true, true, false, false, true, false, true);
/* 1661 */     initEAttribute(getCModule_Namespace(), (EClassifier)this.ecorePackage.getEString(), "namespace", "", 0, 1, CModule.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1663 */     initEClass(this.cIncludeEClass, CInclude.class, "CInclude", false, false, true);
/* 1664 */     initEAttribute(getCInclude_FileName(), (EClassifier)this.ecorePackage.getEString(), "fileName", null, 0, 1, CInclude.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1666 */     initEClass(this.cEnumTypeEClass, CEnumType.class, "CEnumType", false, false, true);
/* 1667 */     initEReference(getCEnumType_Enumeration(), (EClassifier)getCLiteral(), null, "enumeration", null, 1, -1, CEnumType.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1669 */     initEClass(this.cClassEClass, CClass.class, "CClass", false, false, true);
/* 1670 */     initEReference(getCClass_Content(), (EClassifier)getCItem(), null, "content", null, 0, -1, CClass.class, false, false, true, true, false, false, true, false, true);
/* 1671 */     initEAttribute(getCClass_Name(), (EClassifier)this.ecorePackage.getEString(), "name", null, 0, 1, CClass.class, false, false, true, false, false, true, false, true);
/* 1672 */     initEAttribute(getCClass_SuperClasses(), (EClassifier)this.ecorePackage.getEString(), "superClasses", null, 0, -1, CClass.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1674 */     initEClass(this.cCallableEClass, CCallable.class, "CCallable", false, false, true);
/* 1675 */     initEReference(getCCallable_Argument(), (EClassifier)getCArgument(), null, "argument", null, 0, -1, CCallable.class, false, false, true, true, false, false, true, false, true);
/* 1676 */     initEAttribute(getCCallable_Specifier(), (EClassifier)this.ecorePackage.getEString(), "specifier", null, 0, 1, CCallable.class, false, false, true, false, false, true, false, true);
/* 1677 */     initEAttribute(getCCallable_Qualifier(), (EClassifier)this.ecorePackage.getEString(), "qualifier", null, 0, 1, CCallable.class, false, false, true, false, false, true, false, true);
/* 1678 */     initEAttribute(getCCallable_BodyInDecl(), (EClassifier)this.ecorePackage.getEBoolean(), "bodyInDecl", null, 0, 1, CCallable.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1680 */     initEClass(this.cDataEClass, CData.class, "CData", false, false, true);
/* 1681 */     initEAttribute(getCData_Name(), (EClassifier)this.ecorePackage.getEString(), "name", null, 0, 1, CData.class, false, false, true, false, false, true, false, true);
/* 1682 */     initEReference(getCData_InitialValue(), (EClassifier)getCExpression(), null, "initialValue", null, 0, 1, CData.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1684 */     initEClass(this.cArgumentEClass, CArgument.class, "CArgument", false, false, true);
/* 1685 */     initEAttribute(getCArgument_Name(), (EClassifier)this.ecorePackage.getEString(), "name", null, 0, 1, CArgument.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1687 */     initEClass(this.cInitParameterEClass, CInitParameter.class, "CInitParameter", false, false, true);
/* 1688 */     initEAttribute(getCInitParameter_FieldName(), (EClassifier)this.ecorePackage.getEString(), "fieldName", null, 0, 1, CInitParameter.class, false, false, true, false, false, true, false, true);
/* 1689 */     initEReference(getCInitParameter_Value(), (EClassifier)getCExpression(), null, "value", null, 1, 1, CInitParameter.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1691 */     initEClass(this.cFunctionEClass, CFunction.class, "CFunction", false, false, true);
/* 1692 */     initEAttribute(getCFunction_Name(), (EClassifier)this.ecorePackage.getEString(), "name", null, 0, 1, CFunction.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1694 */     initEClass(this.cConstructorEClass, CConstructor.class, "CConstructor", false, false, true);
/* 1695 */     initEReference(getCConstructor_Init(), (EClassifier)getCInitialization(), null, "init", null, 0, -1, CConstructor.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1697 */     initEClass(this.cItemEClass, CItem.class, "CItem", false, false, true);
/* 1698 */     initEAttribute(getCItem_Visibility(), (EClassifier)this.ecorePackage.getEString(), "visibility", null, 0, 1, CItem.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1700 */     initEClass(this.cTextEClass, CText.class, "CText", false, false, true);
/* 1701 */     initEAttribute(getCText_CCode(), (EClassifier)this.ecorePackage.getEString(), "cCode", null, 0, 1, CText.class, false, false, true, false, false, true, false, true);
/* 1702 */     initEAttribute(getCText_Pragma(), (EClassifier)this.ecorePackage.getEString(), "pragma", null, 0, 1, CText.class, false, false, true, false, false, true, false, true);
/* 1703 */     initEAttribute(getCText_InBodyFile(), (EClassifier)this.ecorePackage.getEBoolean(), "inBodyFile", null, 0, 1, CText.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1705 */     initEClass(this.cBodyItemEClass, CBodyItem.class, "CBodyItem", false, false, true);
/*      */     
/* 1707 */     initEClass(this.cBlockStmEClass, CBlockStm.class, "CBlockStm", false, false, true);
/*      */     
/* 1709 */     initEClass(this.cNavigationEClass, CNavigation.class, "CNavigation", true, false, true);
/* 1710 */     initEReference(getCNavigation_Prefix(), (EClassifier)getCExpression(), null, "prefix", null, 0, 1, CNavigation.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1712 */     initEClass(this.cReturnEClass, CReturn.class, "CReturn", false, false, true);
/* 1713 */     initEReference(getCReturn_ReturnExpression(), (EClassifier)getCExpression(), null, "returnExpression", null, 0, 1, CReturn.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1715 */     initEClass(this.cInitializationEClass, CInitialization.class, "CInitialization", false, false, true);
/* 1716 */     initEAttribute(getCInitialization_Field(), (EClassifier)this.ecorePackage.getEString(), "Field", null, 0, 1, CInitialization.class, false, false, true, false, false, true, false, true);
/* 1717 */     initEReference(getCInitialization_Parameter(), (EClassifier)getCInitParameter(), null, "parameter", null, 1, -1, CInitialization.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1719 */     initEClass(this.cForEClass, CFor.class, "CFor", false, false, true);
/* 1720 */     initEReference(getCFor_Initialization(), (EClassifier)getCBodyItem(), null, "initialization", null, 0, 1, CFor.class, false, false, true, true, false, false, true, false, true);
/* 1721 */     initEReference(getCFor_Codition(), (EClassifier)getCExpression(), null, "codition", null, 0, 1, CFor.class, false, false, true, true, false, false, true, false, true);
/* 1722 */     initEReference(getCFor_Iteration(), (EClassifier)getCStm(), null, "iteration", null, 0, 1, CFor.class, false, false, true, false, true, false, true, false, true);
/*      */     
/* 1724 */     initEClass(this.cConditionalExpressionEClass, CConditionalExpression.class, "CConditionalExpression", false, false, true);
/* 1725 */     initEReference(getCConditionalExpression_TrueCase(), (EClassifier)getCExpression(), null, "trueCase", null, 1, 1, CConditionalExpression.class, false, false, true, true, false, false, true, false, true);
/* 1726 */     initEReference(getCConditionalExpression_FalseCase(), (EClassifier)getCExpression(), null, "falseCase", null, 1, 1, CConditionalExpression.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1728 */     initEClass(this.cHeaderTextEClass, CHeaderText.class, "CHeaderText", false, false, true);
/* 1729 */     initEAttribute(getCHeaderText_CCode(), (EClassifier)this.ecorePackage.getEString(), "cCode", null, 0, 1, CHeaderText.class, false, false, true, false, false, true, false, true);
/*      */ 
/*      */     
/* 1732 */     initEEnum(this.jumpTypeEEnum, JumpType.class, "JumpType");
/* 1733 */     addEEnumLiteral(this.jumpTypeEEnum, (Enumerator)JumpType.CBREAK);
/* 1734 */     addEEnumLiteral(this.jumpTypeEEnum, (Enumerator)JumpType.CCONTINUE);
/*      */     
/* 1736 */     initEEnum(this.cAssignTypeEEnum, CAssignType.class, "CAssignType");
/* 1737 */     addEEnumLiteral(this.cAssignTypeEEnum, (Enumerator)CAssignType.ASSIGN);
/* 1738 */     addEEnumLiteral(this.cAssignTypeEEnum, (Enumerator)CAssignType.PLUS_ASSIGN);
/* 1739 */     addEEnumLiteral(this.cAssignTypeEEnum, (Enumerator)CAssignType.MINUS_ASSIGN);
/* 1740 */     addEEnumLiteral(this.cAssignTypeEEnum, (Enumerator)CAssignType.MULT_ASSIGN);
/* 1741 */     addEEnumLiteral(this.cAssignTypeEEnum, (Enumerator)CAssignType.DIV_ASSIGN);
/* 1742 */     addEEnumLiteral(this.cAssignTypeEEnum, (Enumerator)CAssignType.MOD_ASSIGN);
/*      */ 
/*      */     
/* 1745 */     createResource("http:///ujf/verimag/bip/cmodel.ecore");
/*      */ 
/*      */ 
/*      */     
/* 1749 */     createOrderedAnnotations(); } public EClass getCInclude() { return this.cIncludeEClass; } public EAttribute getCInclude_FileName() { return (EAttribute)this.cIncludeEClass.getEStructuralFeatures().get(0); } public EClass getCEnumType() { return this.cEnumTypeEClass; } public EReference getCEnumType_Enumeration() { return (EReference)this.cEnumTypeEClass.getEStructuralFeatures().get(0); } public EClass getCClass() { return this.cClassEClass; } public EReference getCClass_Content() { return (EReference)this.cClassEClass.getEStructuralFeatures().get(0); } public EAttribute getCClass_Name() { return (EAttribute)this.cClassEClass.getEStructuralFeatures().get(1); } public EAttribute getCClass_SuperClasses() { return (EAttribute)this.cClassEClass.getEStructuralFeatures().get(2); } public EClass getCCallable() { return this.cCallableEClass; } public EReference getCCallable_Argument() { return (EReference)this.cCallableEClass.getEStructuralFeatures().get(0); } public EAttribute getCCallable_Specifier() { return (EAttribute)this.cCallableEClass.getEStructuralFeatures().get(1); } public EAttribute getCCallable_Qualifier() { return (EAttribute)this.cCallableEClass.getEStructuralFeatures().get(2); } public EAttribute getCCallable_BodyInDecl() { return (EAttribute)this.cCallableEClass.getEStructuralFeatures().get(3); } public EClass getCData() { return this.cDataEClass; } public EAttribute getCData_Name() { return (EAttribute)this.cDataEClass.getEStructuralFeatures().get(0); } public EReference getCData_InitialValue() { return (EReference)this.cDataEClass.getEStructuralFeatures().get(1); } public EClass getCArgument() { return this.cArgumentEClass; }
/*      */   public EAttribute getCArgument_Name() { return (EAttribute)this.cArgumentEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCInitParameter() { return this.cInitParameterEClass; }
/*      */   public EAttribute getCInitParameter_FieldName() { return (EAttribute)this.cInitParameterEClass.getEStructuralFeatures().get(0); }
/*      */   public EReference getCInitParameter_Value() { return (EReference)this.cInitParameterEClass.getEStructuralFeatures().get(1); }
/*      */   public EClass getCFunction() { return this.cFunctionEClass; }
/*      */   public EAttribute getCFunction_Name() { return (EAttribute)this.cFunctionEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCConstructor() { return this.cConstructorEClass; }
/*      */   public EReference getCConstructor_Init() { return (EReference)this.cConstructorEClass.getEStructuralFeatures().get(0); }
/*      */   public EClass getCItem() { return this.cItemEClass; }
/* 1759 */   protected void createOrderedAnnotations() { String source = "ordered";
/* 1760 */     addAnnotation((ENamedElement)getCCall_Argument(), source, new String[0]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1765 */     addAnnotation((ENamedElement)getCModule_Content(), source, new String[0]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1770 */     addAnnotation((ENamedElement)getCCallable_Argument(), source, new String[0]); }
/*      */ 
/*      */   
/*      */   public EAttribute getCItem_Visibility() {
/*      */     return (EAttribute)this.cItemEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getCText() {
/*      */     return this.cTextEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getCText_CCode() {
/*      */     return (EAttribute)this.cTextEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EAttribute getCText_Pragma() {
/*      */     return (EAttribute)this.cTextEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EAttribute getCText_InBodyFile() {
/*      */     return (EAttribute)this.cTextEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getCBodyItem() {
/*      */     return this.cBodyItemEClass;
/*      */   }
/*      */   
/*      */   public EClass getCBlockStm() {
/*      */     return this.cBlockStmEClass;
/*      */   }
/*      */   
/*      */   public EClass getCNavigation() {
/*      */     return this.cNavigationEClass;
/*      */   }
/*      */   
/*      */   public EReference getCNavigation_Prefix() {
/*      */     return (EReference)this.cNavigationEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getCReturn() {
/*      */     return this.cReturnEClass;
/*      */   }
/*      */   
/*      */   public EReference getCReturn_ReturnExpression() {
/*      */     return (EReference)this.cReturnEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getCInitialization() {
/*      */     return this.cInitializationEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getCInitialization_Field() {
/*      */     return (EAttribute)this.cInitializationEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getCInitialization_Parameter() {
/*      */     return (EReference)this.cInitializationEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getCFor() {
/*      */     return this.cForEClass;
/*      */   }
/*      */   
/*      */   public EReference getCFor_Initialization() {
/*      */     return (EReference)this.cForEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getCFor_Codition() {
/*      */     return (EReference)this.cForEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getCFor_Iteration() {
/*      */     return (EReference)this.cForEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getCConditionalExpression() {
/*      */     return this.cConditionalExpressionEClass;
/*      */   }
/*      */   
/*      */   public EReference getCConditionalExpression_TrueCase() {
/*      */     return (EReference)this.cConditionalExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getCConditionalExpression_FalseCase() {
/*      */     return (EReference)this.cConditionalExpressionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getCHeaderText() {
/*      */     return this.cHeaderTextEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getCHeaderText_CCode() {
/*      */     return (EAttribute)this.cHeaderTextEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EEnum getJumpType() {
/*      */     return this.jumpTypeEEnum;
/*      */   }
/*      */   
/*      */   public EEnum getCAssignType() {
/*      */     return this.cAssignTypeEEnum;
/*      */   }
/*      */   
/*      */   public CmodelFactory getCmodelFactory() {
/*      */     return (CmodelFactory)getEFactoryInstance();
/*      */   }
/*      */   
/*      */   public void createPackageContents() {
/*      */     if (this.isCreated)
/*      */       return; 
/*      */     this.isCreated = true;
/*      */     this.cStmEClass = createEClass(0);
/*      */     this.cBlockEClass = createEClass(1);
/*      */     createEReference(this.cBlockEClass, 0);
/*      */     this.cConditionalStmEClass = createEClass(2);
/*      */     createEReference(this.cConditionalStmEClass, 0);
/*      */     this.cSwitchStmEClass = createEClass(3);
/*      */     createEReference(this.cSwitchStmEClass, 0);
/*      */     createEAttribute(this.cSwitchStmEClass, 1);
/*      */     this.cWhileStmEClass = createEClass(4);
/*      */     this.cIfStmEClass = createEClass(5);
/*      */     createEReference(this.cIfStmEClass, 1);
/*      */     createEReference(this.cIfStmEClass, 2);
/*      */     this.cExpressionEClass = createEClass(6);
/*      */     this.cCaseItemEClass = createEClass(7);
/*      */     createEAttribute(this.cCaseItemEClass, 1);
/*      */     this.cJumpEClass = createEClass(8);
/*      */     createEAttribute(this.cJumpEClass, 0);
/*      */     this.cCallEClass = createEClass(9);
/*      */     createEReference(this.cCallEClass, 0);
/*      */     this.cAssignStmEClass = createEClass(10);
/*      */     createEReference(this.cAssignStmEClass, 0);
/*      */     createEReference(this.cAssignStmEClass, 1);
/*      */     createEAttribute(this.cAssignStmEClass, 2);
/*      */     this.cFunctionCallEClass = createEClass(11);
/*      */     createEAttribute(this.cFunctionCallEClass, 2);
/*      */     this.cCreatorEClass = createEClass(12);
/*      */     createEAttribute(this.cCreatorEClass, 2);
/*      */     this.cTypedElementEClass = createEClass(13);
/*      */     createEAttribute(this.cTypedElementEClass, 0);
/*      */     this.cOperationEClass = createEClass(14);
/*      */     createEReference(this.cOperationEClass, 0);
/*      */     createEReference(this.cOperationEClass, 1);
/*      */     createEAttribute(this.cOperationEClass, 2);
/*      */     this.cTypeConvertionEClass = createEClass(15);
/*      */     createEReference(this.cTypeConvertionEClass, 1);
/*      */     this.cHierarchyEClass = createEClass(16);
/*      */     createEReference(this.cHierarchyEClass, 1);
/*      */     this.cSimpleNameEClass = createEClass(17);
/*      */     createEAttribute(this.cSimpleNameEClass, 0);
/*      */     this.cIndexedEClass = createEClass(18);
/*      */     createEReference(this.cIndexedEClass, 1);
/*      */     this.cStructuredEClass = createEClass(19);
/*      */     this.cPointedEClass = createEClass(20);
/*      */     this.cLiteralEClass = createEClass(21);
/*      */     createEAttribute(this.cLiteralEClass, 0);
/*      */     this.cModuleEClass = createEClass(22);
/*      */     createEReference(this.cModuleEClass, 0);
/*      */     createEReference(this.cModuleEClass, 1);
/*      */     createEAttribute(this.cModuleEClass, 2);
/*      */     this.cIncludeEClass = createEClass(23);
/*      */     createEAttribute(this.cIncludeEClass, 0);
/*      */     this.cEnumTypeEClass = createEClass(24);
/*      */     createEReference(this.cEnumTypeEClass, 2);
/*      */     this.cClassEClass = createEClass(25);
/*      */     createEReference(this.cClassEClass, 2);
/*      */     createEAttribute(this.cClassEClass, 3);
/*      */     createEAttribute(this.cClassEClass, 4);
/*      */     this.cCallableEClass = createEClass(26);
/*      */     createEReference(this.cCallableEClass, 3);
/*      */     createEAttribute(this.cCallableEClass, 4);
/*      */     createEAttribute(this.cCallableEClass, 5);
/*      */     createEAttribute(this.cCallableEClass, 6);
/*      */     this.cDataEClass = createEClass(27);
/*      */     createEAttribute(this.cDataEClass, 2);
/*      */     createEReference(this.cDataEClass, 3);
/*      */     this.cArgumentEClass = createEClass(28);
/*      */     createEAttribute(this.cArgumentEClass, 1);
/*      */     this.cInitParameterEClass = createEClass(29);
/*      */     createEAttribute(this.cInitParameterEClass, 0);
/*      */     createEReference(this.cInitParameterEClass, 1);
/*      */     this.cFunctionEClass = createEClass(30);
/*      */     createEAttribute(this.cFunctionEClass, 7);
/*      */     this.cConstructorEClass = createEClass(31);
/*      */     createEReference(this.cConstructorEClass, 7);
/*      */     this.cItemEClass = createEClass(32);
/*      */     createEAttribute(this.cItemEClass, 0);
/*      */     this.cTextEClass = createEClass(33);
/*      */     createEAttribute(this.cTextEClass, 1);
/*      */     createEAttribute(this.cTextEClass, 2);
/*      */     createEAttribute(this.cTextEClass, 3);
/*      */     this.cBodyItemEClass = createEClass(34);
/*      */     this.cBlockStmEClass = createEClass(35);
/*      */     this.cNavigationEClass = createEClass(36);
/*      */     createEReference(this.cNavigationEClass, 0);
/*      */     this.cReturnEClass = createEClass(37);
/*      */     createEReference(this.cReturnEClass, 0);
/*      */     this.cInitializationEClass = createEClass(38);
/*      */     createEAttribute(this.cInitializationEClass, 0);
/*      */     createEReference(this.cInitializationEClass, 1);
/*      */     this.cForEClass = createEClass(39);
/*      */     createEReference(this.cForEClass, 1);
/*      */     createEReference(this.cForEClass, 2);
/*      */     createEReference(this.cForEClass, 3);
/*      */     this.cConditionalExpressionEClass = createEClass(40);
/*      */     createEReference(this.cConditionalExpressionEClass, 1);
/*      */     createEReference(this.cConditionalExpressionEClass, 2);
/*      */     this.cHeaderTextEClass = createEClass(41);
/*      */     createEAttribute(this.cHeaderTextEClass, 1);
/*      */     this.jumpTypeEEnum = createEEnum(42);
/*      */     this.cAssignTypeEEnum = createEEnum(43);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CmodelPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */