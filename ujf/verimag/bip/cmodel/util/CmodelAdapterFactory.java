/*     */ package ujf.verimag.bip.cmodel.util;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CBlock;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
/*     */ import ujf.verimag.bip.cmodel.CCall;
/*     */ import ujf.verimag.bip.cmodel.CCallable;
/*     */ import ujf.verimag.bip.cmodel.CCaseItem;
/*     */ import ujf.verimag.bip.cmodel.CClass;
/*     */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*     */ import ujf.verimag.bip.cmodel.CConditionalStm;
/*     */ import ujf.verimag.bip.cmodel.CConstructor;
/*     */ import ujf.verimag.bip.cmodel.CCreator;
/*     */ import ujf.verimag.bip.cmodel.CData;
/*     */ import ujf.verimag.bip.cmodel.CEnumType;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFunction;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CHeaderText;
/*     */ import ujf.verimag.bip.cmodel.CHierarchy;
/*     */ import ujf.verimag.bip.cmodel.CIfStm;
/*     */ import ujf.verimag.bip.cmodel.CInclude;
/*     */ import ujf.verimag.bip.cmodel.CInitParameter;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
/*     */ import ujf.verimag.bip.cmodel.CItem;
/*     */ import ujf.verimag.bip.cmodel.CJump;
/*     */ import ujf.verimag.bip.cmodel.CLiteral;
/*     */ import ujf.verimag.bip.cmodel.CModule;
/*     */ import ujf.verimag.bip.cmodel.CNavigation;
/*     */ import ujf.verimag.bip.cmodel.COperation;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CSimpleName;
/*     */ import ujf.verimag.bip.cmodel.CStm;
/*     */ import ujf.verimag.bip.cmodel.CStructured;
/*     */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*     */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*     */ import ujf.verimag.bip.cmodel.CTypedElement;
/*     */ 
/*     */ public class CmodelAdapterFactory extends AdapterFactoryImpl {
/*     */   public CmodelAdapterFactory() {
/*  42 */     if (modelPackage == null) {
/*  43 */       modelPackage = CmodelPackage.eINSTANCE;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static CmodelPackage modelPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFactoryForType(Object object) {
/*  57 */     if (object == modelPackage) {
/*  58 */       return true;
/*     */     }
/*  60 */     if (object instanceof EObject) {
/*  61 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected CmodelSwitch<Adapter> modelSwitch = new CmodelSwitch<Adapter>()
/*     */     {
/*     */       public Adapter caseCStm(CStm object)
/*     */       {
/*  76 */         return CmodelAdapterFactory.this.createCStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCBlock(CBlock object) {
/*  80 */         return CmodelAdapterFactory.this.createCBlockAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCConditionalStm(CConditionalStm object) {
/*  84 */         return CmodelAdapterFactory.this.createCConditionalStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCSwitchStm(CSwitchStm object) {
/*  88 */         return CmodelAdapterFactory.this.createCSwitchStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCWhileStm(CWhileStm object) {
/*  92 */         return CmodelAdapterFactory.this.createCWhileStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCIfStm(CIfStm object) {
/*  96 */         return CmodelAdapterFactory.this.createCIfStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCExpression(CExpression object) {
/* 100 */         return CmodelAdapterFactory.this.createCExpressionAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCCaseItem(CCaseItem object) {
/* 104 */         return CmodelAdapterFactory.this.createCCaseItemAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCJump(CJump object) {
/* 108 */         return CmodelAdapterFactory.this.createCJumpAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCCall(CCall object) {
/* 112 */         return CmodelAdapterFactory.this.createCCallAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCAssignStm(CAssignStm object) {
/* 116 */         return CmodelAdapterFactory.this.createCAssignStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCFunctionCall(CFunctionCall object) {
/* 120 */         return CmodelAdapterFactory.this.createCFunctionCallAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCCreator(CCreator object) {
/* 124 */         return CmodelAdapterFactory.this.createCCreatorAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCTypedElement(CTypedElement object) {
/* 128 */         return CmodelAdapterFactory.this.createCTypedElementAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCOperation(COperation object) {
/* 132 */         return CmodelAdapterFactory.this.createCOperationAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCTypeConvertion(CTypeConvertion object) {
/* 136 */         return CmodelAdapterFactory.this.createCTypeConvertionAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCHierarchy(CHierarchy object) {
/* 140 */         return CmodelAdapterFactory.this.createCHierarchyAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCSimpleName(CSimpleName object) {
/* 144 */         return CmodelAdapterFactory.this.createCSimpleNameAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCIndexed(CIndexed object) {
/* 148 */         return CmodelAdapterFactory.this.createCIndexedAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCStructured(CStructured object) {
/* 152 */         return CmodelAdapterFactory.this.createCStructuredAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCPointed(CPointed object) {
/* 156 */         return CmodelAdapterFactory.this.createCPointedAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCLiteral(CLiteral object) {
/* 160 */         return CmodelAdapterFactory.this.createCLiteralAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCModule(CModule object) {
/* 164 */         return CmodelAdapterFactory.this.createCModuleAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCInclude(CInclude object) {
/* 168 */         return CmodelAdapterFactory.this.createCIncludeAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCEnumType(CEnumType object) {
/* 172 */         return CmodelAdapterFactory.this.createCEnumTypeAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCClass(CClass object) {
/* 176 */         return CmodelAdapterFactory.this.createCClassAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCCallable(CCallable object) {
/* 180 */         return CmodelAdapterFactory.this.createCCallableAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCData(CData object) {
/* 184 */         return CmodelAdapterFactory.this.createCDataAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCArgument(CArgument object) {
/* 188 */         return CmodelAdapterFactory.this.createCArgumentAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCInitParameter(CInitParameter object) {
/* 192 */         return CmodelAdapterFactory.this.createCInitParameterAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCFunction(CFunction object) {
/* 196 */         return CmodelAdapterFactory.this.createCFunctionAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCConstructor(CConstructor object) {
/* 200 */         return CmodelAdapterFactory.this.createCConstructorAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCItem(CItem object) {
/* 204 */         return CmodelAdapterFactory.this.createCItemAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCText(CText object) {
/* 208 */         return CmodelAdapterFactory.this.createCTextAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCBodyItem(CBodyItem object) {
/* 212 */         return CmodelAdapterFactory.this.createCBodyItemAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCBlockStm(CBlockStm object) {
/* 216 */         return CmodelAdapterFactory.this.createCBlockStmAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCNavigation(CNavigation object) {
/* 220 */         return CmodelAdapterFactory.this.createCNavigationAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCReturn(CReturn object) {
/* 224 */         return CmodelAdapterFactory.this.createCReturnAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCInitialization(CInitialization object) {
/* 228 */         return CmodelAdapterFactory.this.createCInitializationAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCFor(CFor object) {
/* 232 */         return CmodelAdapterFactory.this.createCForAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCConditionalExpression(CConditionalExpression object) {
/* 236 */         return CmodelAdapterFactory.this.createCConditionalExpressionAdapter();
/*     */       }
/*     */       
/*     */       public Adapter caseCHeaderText(CHeaderText object) {
/* 240 */         return CmodelAdapterFactory.this.createCHeaderTextAdapter();
/*     */       }
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 244 */         return CmodelAdapterFactory.this.createEObjectAdapter();
/*     */       }
/*     */     };
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
/*     */   public Adapter createAdapter(Notifier target) {
/* 258 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createCStmAdapter() {
/* 273 */     return null;
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
/*     */   public Adapter createCBlockAdapter() {
/* 287 */     return null;
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
/*     */   public Adapter createCConditionalStmAdapter() {
/* 301 */     return null;
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
/*     */   public Adapter createCSwitchStmAdapter() {
/* 315 */     return null;
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
/*     */   public Adapter createCWhileStmAdapter() {
/* 329 */     return null;
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
/*     */   public Adapter createCIfStmAdapter() {
/* 343 */     return null;
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
/*     */   public Adapter createCExpressionAdapter() {
/* 357 */     return null;
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
/*     */   public Adapter createCCaseItemAdapter() {
/* 371 */     return null;
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
/*     */   public Adapter createCJumpAdapter() {
/* 385 */     return null;
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
/*     */   public Adapter createCCallAdapter() {
/* 399 */     return null;
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
/*     */   public Adapter createCAssignStmAdapter() {
/* 413 */     return null;
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
/*     */   public Adapter createCFunctionCallAdapter() {
/* 427 */     return null;
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
/*     */   public Adapter createCCreatorAdapter() {
/* 441 */     return null;
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
/*     */   public Adapter createCTypedElementAdapter() {
/* 455 */     return null;
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
/*     */   public Adapter createCOperationAdapter() {
/* 469 */     return null;
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
/*     */   public Adapter createCTypeConvertionAdapter() {
/* 483 */     return null;
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
/*     */   public Adapter createCHierarchyAdapter() {
/* 497 */     return null;
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
/*     */   public Adapter createCSimpleNameAdapter() {
/* 511 */     return null;
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
/*     */   public Adapter createCIndexedAdapter() {
/* 525 */     return null;
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
/*     */   public Adapter createCStructuredAdapter() {
/* 539 */     return null;
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
/*     */   public Adapter createCPointedAdapter() {
/* 553 */     return null;
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
/*     */   public Adapter createCLiteralAdapter() {
/* 567 */     return null;
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
/*     */   public Adapter createCModuleAdapter() {
/* 581 */     return null;
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
/*     */   public Adapter createCIncludeAdapter() {
/* 595 */     return null;
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
/*     */   public Adapter createCEnumTypeAdapter() {
/* 609 */     return null;
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
/*     */   public Adapter createCClassAdapter() {
/* 623 */     return null;
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
/*     */   public Adapter createCCallableAdapter() {
/* 637 */     return null;
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
/*     */   public Adapter createCDataAdapter() {
/* 651 */     return null;
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
/*     */   public Adapter createCArgumentAdapter() {
/* 665 */     return null;
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
/*     */   public Adapter createCInitParameterAdapter() {
/* 679 */     return null;
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
/*     */   public Adapter createCFunctionAdapter() {
/* 693 */     return null;
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
/*     */   public Adapter createCConstructorAdapter() {
/* 707 */     return null;
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
/*     */   public Adapter createCItemAdapter() {
/* 721 */     return null;
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
/*     */   public Adapter createCTextAdapter() {
/* 735 */     return null;
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
/*     */   public Adapter createCBodyItemAdapter() {
/* 749 */     return null;
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
/*     */   public Adapter createCBlockStmAdapter() {
/* 763 */     return null;
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
/*     */   public Adapter createCNavigationAdapter() {
/* 777 */     return null;
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
/*     */   public Adapter createCReturnAdapter() {
/* 791 */     return null;
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
/*     */   public Adapter createCInitializationAdapter() {
/* 805 */     return null;
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
/*     */   public Adapter createCForAdapter() {
/* 819 */     return null;
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
/*     */   public Adapter createCConditionalExpressionAdapter() {
/* 833 */     return null;
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
/*     */   public Adapter createCHeaderTextAdapter() {
/* 847 */     return null;
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
/*     */   public Adapter createEObjectAdapter() {
/* 859 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmode\\util\CmodelAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */