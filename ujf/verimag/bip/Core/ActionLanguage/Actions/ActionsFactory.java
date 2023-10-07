/*    */ package ujf.verimag.bip.Core.ActionLanguage.Actions;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Actions.impl.ActionsFactoryImpl;
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
/*    */ 
/*    */ public interface ActionsFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final ActionsFactory eINSTANCE = ActionsFactoryImpl.init();
/*    */   
/*    */   CompositeAction createCompositeAction();
/*    */   
/*    */   IfAction createIfAction();
/*    */   
/*    */   AssignmentAction createAssignmentAction();
/*    */   
/*    */   ActionsPackage getActionsPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\ActionsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */