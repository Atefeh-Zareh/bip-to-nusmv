/*    */ package ujf.verimag.bip.cmodel;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.cmodel.impl.CmodelFactoryImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface CmodelFactory
/*    */   extends EFactory
/*    */ {
/* 26 */   public static final CmodelFactory eINSTANCE = CmodelFactoryImpl.init();
/*    */   
/*    */   CStm createCStm();
/*    */   
/*    */   CConditionalStm createCConditionalStm();
/*    */   
/*    */   CSwitchStm createCSwitchStm();
/*    */   
/*    */   CWhileStm createCWhileStm();
/*    */   
/*    */   CIfStm createCIfStm();
/*    */   
/*    */   CExpression createCExpression();
/*    */   
/*    */   CCaseItem createCCaseItem();
/*    */   
/*    */   CJump createCJump();
/*    */   
/*    */   CCall createCCall();
/*    */   
/*    */   CAssignStm createCAssignStm();
/*    */   
/*    */   CFunctionCall createCFunctionCall();
/*    */   
/*    */   CCreator createCCreator();
/*    */   
/*    */   CTypedElement createCTypedElement();
/*    */   
/*    */   COperation createCOperation();
/*    */   
/*    */   CTypeConvertion createCTypeConvertion();
/*    */   
/*    */   CSimpleName createCSimpleName();
/*    */   
/*    */   CIndexed createCIndexed();
/*    */   
/*    */   CStructured createCStructured();
/*    */   
/*    */   CPointed createCPointed();
/*    */   
/*    */   CLiteral createCLiteral();
/*    */   
/*    */   CModule createCModule();
/*    */   
/*    */   CInclude createCInclude();
/*    */   
/*    */   CEnumType createCEnumType();
/*    */   
/*    */   CClass createCClass();
/*    */   
/*    */   CCallable createCCallable();
/*    */   
/*    */   CData createCData();
/*    */   
/*    */   CArgument createCArgument();
/*    */   
/*    */   CInitParameter createCInitParameter();
/*    */   
/*    */   CFunction createCFunction();
/*    */   
/*    */   CConstructor createCConstructor();
/*    */   
/*    */   CItem createCItem();
/*    */   
/*    */   CText createCText();
/*    */   
/*    */   CBodyItem createCBodyItem();
/*    */   
/*    */   CBlockStm createCBlockStm();
/*    */   
/*    */   CReturn createCReturn();
/*    */   
/*    */   CInitialization createCInitialization();
/*    */   
/*    */   CFor createCFor();
/*    */   
/*    */   CConditionalExpression createCConditionalExpression();
/*    */   
/*    */   CHeaderText createCHeaderText();
/*    */   
/*    */   CmodelPackage getCmodelPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\CmodelFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */