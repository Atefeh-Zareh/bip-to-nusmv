/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import ujf.verimag.bip.cmodel.CArgument;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CAssignType;
/*     */ import ujf.verimag.bip.cmodel.CCall;
/*     */ import ujf.verimag.bip.cmodel.CCallable;
/*     */ import ujf.verimag.bip.cmodel.CClass;
/*     */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*     */ import ujf.verimag.bip.cmodel.CConditionalStm;
/*     */ import ujf.verimag.bip.cmodel.CCreator;
/*     */ import ujf.verimag.bip.cmodel.CEnumType;
/*     */ import ujf.verimag.bip.cmodel.CFor;
/*     */ import ujf.verimag.bip.cmodel.CFunction;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CInclude;
/*     */ import ujf.verimag.bip.cmodel.CInitParameter;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
/*     */ import ujf.verimag.bip.cmodel.CLiteral;
/*     */ import ujf.verimag.bip.cmodel.CModule;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CStructured;
/*     */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*     */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*     */ import ujf.verimag.bip.cmodel.JumpType;
/*     */ 
/*     */ public class CmodelFactoryImpl extends EFactoryImpl implements CmodelFactory {
/*     */   public static CmodelFactory init() {
/*     */     try {
/*  34 */       CmodelFactory theCmodelFactory = (CmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/cmodel.ecore");
/*  35 */       if (theCmodelFactory != null) {
/*  36 */         return theCmodelFactory;
/*     */       }
/*     */     }
/*  39 */     catch (Exception exception) {
/*  40 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  42 */     return new CmodelFactoryImpl();
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
/*     */   public EObject create(EClass eClass) {
/*  62 */     switch (eClass.getClassifierID()) { case 0:
/*  63 */         return (EObject)createCStm();
/*  64 */       case 2: return (EObject)createCConditionalStm();
/*  65 */       case 3: return (EObject)createCSwitchStm();
/*  66 */       case 4: return (EObject)createCWhileStm();
/*  67 */       case 5: return (EObject)createCIfStm();
/*  68 */       case 6: return (EObject)createCExpression();
/*  69 */       case 7: return (EObject)createCCaseItem();
/*  70 */       case 8: return (EObject)createCJump();
/*  71 */       case 9: return (EObject)createCCall();
/*  72 */       case 10: return (EObject)createCAssignStm();
/*  73 */       case 11: return (EObject)createCFunctionCall();
/*  74 */       case 12: return (EObject)createCCreator();
/*  75 */       case 13: return (EObject)createCTypedElement();
/*  76 */       case 14: return (EObject)createCOperation();
/*  77 */       case 15: return (EObject)createCTypeConvertion();
/*  78 */       case 17: return (EObject)createCSimpleName();
/*  79 */       case 18: return (EObject)createCIndexed();
/*  80 */       case 19: return (EObject)createCStructured();
/*  81 */       case 20: return (EObject)createCPointed();
/*  82 */       case 21: return (EObject)createCLiteral();
/*  83 */       case 22: return (EObject)createCModule();
/*  84 */       case 23: return (EObject)createCInclude();
/*  85 */       case 24: return (EObject)createCEnumType();
/*  86 */       case 25: return (EObject)createCClass();
/*  87 */       case 26: return (EObject)createCCallable();
/*  88 */       case 27: return (EObject)createCData();
/*  89 */       case 28: return (EObject)createCArgument();
/*  90 */       case 29: return (EObject)createCInitParameter();
/*  91 */       case 30: return (EObject)createCFunction();
/*  92 */       case 31: return (EObject)createCConstructor();
/*  93 */       case 32: return (EObject)createCItem();
/*  94 */       case 33: return (EObject)createCText();
/*  95 */       case 34: return (EObject)createCBodyItem();
/*  96 */       case 35: return (EObject)createCBlockStm();
/*  97 */       case 37: return (EObject)createCReturn();
/*  98 */       case 38: return (EObject)createCInitialization();
/*  99 */       case 39: return (EObject)createCFor();
/* 100 */       case 40: return (EObject)createCConditionalExpression();
/* 101 */       case 41: return (EObject)createCHeaderText(); }
/*     */     
/* 103 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/* 114 */     switch (eDataType.getClassifierID()) {
/*     */       case 42:
/* 116 */         return createJumpTypeFromString(eDataType, initialValue);
/*     */       case 43:
/* 118 */         return createCAssignTypeFromString(eDataType, initialValue);
/*     */     } 
/* 120 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 131 */     switch (eDataType.getClassifierID()) {
/*     */       case 42:
/* 133 */         return convertJumpTypeToString(eDataType, instanceValue);
/*     */       case 43:
/* 135 */         return convertCAssignTypeToString(eDataType, instanceValue);
/*     */     } 
/* 137 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CStm createCStm() {
/* 147 */     CStmImpl cStm = new CStmImpl();
/* 148 */     return cStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CConditionalStm createCConditionalStm() {
/* 157 */     CConditionalStmImpl cConditionalStm = new CConditionalStmImpl();
/* 158 */     return cConditionalStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSwitchStm createCSwitchStm() {
/* 167 */     CSwitchStmImpl cSwitchStm = new CSwitchStmImpl();
/* 168 */     return cSwitchStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CWhileStm createCWhileStm() {
/* 177 */     CWhileStmImpl cWhileStm = new CWhileStmImpl();
/* 178 */     return cWhileStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CIfStm createCIfStm() {
/* 187 */     CIfStmImpl cIfStm = new CIfStmImpl();
/* 188 */     return cIfStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression createCExpression() {
/* 197 */     CExpressionImpl cExpression = new CExpressionImpl();
/* 198 */     return cExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCaseItem createCCaseItem() {
/* 207 */     CCaseItemImpl cCaseItem = new CCaseItemImpl();
/* 208 */     return cCaseItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CJump createCJump() {
/* 217 */     CJumpImpl cJump = new CJumpImpl();
/* 218 */     return cJump;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCall createCCall() {
/* 227 */     CCallImpl cCall = new CCallImpl();
/* 228 */     return cCall;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CAssignStm createCAssignStm() {
/* 237 */     CAssignStmImpl cAssignStm = new CAssignStmImpl();
/* 238 */     return cAssignStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFunctionCall createCFunctionCall() {
/* 247 */     CFunctionCallImpl cFunctionCall = new CFunctionCallImpl();
/* 248 */     return cFunctionCall;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCreator createCCreator() {
/* 257 */     CCreatorImpl cCreator = new CCreatorImpl();
/* 258 */     return cCreator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CTypedElement createCTypedElement() {
/* 267 */     CTypedElementImpl cTypedElement = new CTypedElementImpl();
/* 268 */     return cTypedElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COperation createCOperation() {
/* 277 */     COperationImpl cOperation = new COperationImpl();
/* 278 */     return cOperation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CTypeConvertion createCTypeConvertion() {
/* 287 */     CTypeConvertionImpl cTypeConvertion = new CTypeConvertionImpl();
/* 288 */     return cTypeConvertion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSimpleName createCSimpleName() {
/* 297 */     CSimpleNameImpl cSimpleName = new CSimpleNameImpl();
/* 298 */     return cSimpleName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CIndexed createCIndexed() {
/* 307 */     CIndexedImpl cIndexed = new CIndexedImpl();
/* 308 */     return cIndexed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CStructured createCStructured() {
/* 317 */     CStructuredImpl cStructured = new CStructuredImpl();
/* 318 */     return cStructured;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CPointed createCPointed() {
/* 327 */     CPointedImpl cPointed = new CPointedImpl();
/* 328 */     return cPointed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CLiteral createCLiteral() {
/* 337 */     CLiteralImpl cLiteral = new CLiteralImpl();
/* 338 */     return cLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CModule createCModule() {
/* 347 */     CModuleImpl cModule = new CModuleImpl();
/* 348 */     return cModule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CInclude createCInclude() {
/* 357 */     CIncludeImpl cInclude = new CIncludeImpl();
/* 358 */     return cInclude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CEnumType createCEnumType() {
/* 367 */     CEnumTypeImpl cEnumType = new CEnumTypeImpl();
/* 368 */     return cEnumType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CClass createCClass() {
/* 377 */     CClassImpl cClass = new CClassImpl();
/* 378 */     return cClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCallable createCCallable() {
/* 387 */     CCallableImpl cCallable = new CCallableImpl();
/* 388 */     return cCallable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CData createCData() {
/* 397 */     CDataImpl cData = new CDataImpl();
/* 398 */     return cData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CArgument createCArgument() {
/* 407 */     CArgumentImpl cArgument = new CArgumentImpl();
/* 408 */     return cArgument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CInitParameter createCInitParameter() {
/* 417 */     CInitParameterImpl cInitParameter = new CInitParameterImpl();
/* 418 */     return cInitParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFunction createCFunction() {
/* 427 */     CFunctionImpl cFunction = new CFunctionImpl();
/* 428 */     return cFunction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CConstructor createCConstructor() {
/* 437 */     CConstructorImpl cConstructor = new CConstructorImpl();
/* 438 */     return cConstructor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CItem createCItem() {
/* 447 */     CItemImpl cItem = new CItemImpl();
/* 448 */     return cItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CText createCText() {
/* 457 */     CTextImpl cText = new CTextImpl();
/* 458 */     return cText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CBodyItem createCBodyItem() {
/* 467 */     CBodyItemImpl cBodyItem = new CBodyItemImpl();
/* 468 */     return cBodyItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CBlockStm createCBlockStm() {
/* 477 */     CBlockStmImpl cBlockStm = new CBlockStmImpl();
/* 478 */     return cBlockStm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CReturn createCReturn() {
/* 487 */     CReturnImpl cReturn = new CReturnImpl();
/* 488 */     return cReturn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CInitialization createCInitialization() {
/* 497 */     CInitializationImpl cInitialization = new CInitializationImpl();
/* 498 */     return cInitialization;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFor createCFor() {
/* 507 */     CForImpl cFor = new CForImpl();
/* 508 */     return cFor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CConditionalExpression createCConditionalExpression() {
/* 517 */     CConditionalExpressionImpl cConditionalExpression = new CConditionalExpressionImpl();
/* 518 */     return cConditionalExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CHeaderText createCHeaderText() {
/* 527 */     CHeaderTextImpl cHeaderText = new CHeaderTextImpl();
/* 528 */     return cHeaderText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JumpType createJumpTypeFromString(EDataType eDataType, String initialValue) {
/* 537 */     JumpType result = JumpType.get(initialValue);
/* 538 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 539 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertJumpTypeToString(EDataType eDataType, Object instanceValue) {
/* 548 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CAssignType createCAssignTypeFromString(EDataType eDataType, String initialValue) {
/* 557 */     CAssignType result = CAssignType.get(initialValue);
/* 558 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 559 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertCAssignTypeToString(EDataType eDataType, Object instanceValue) {
/* 568 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CmodelPackage getCmodelPackage() {
/* 577 */     return (CmodelPackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static CmodelPackage getPackage() {
/* 588 */     return CmodelPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CmodelFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */