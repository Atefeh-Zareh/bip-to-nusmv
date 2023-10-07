/*    */ package ujf.verimag.bip.Extra.Contracts;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Extra.Contracts.impl.ContractsFactoryImpl;
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
/*    */ public interface ContractsFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final ContractsFactory eINSTANCE = ContractsFactoryImpl.init();
/*    */   
/*    */   Contract createContract();
/*    */   
/*    */   ContractState createContractState();
/*    */   
/*    */   ContractBinding createContractBinding();
/*    */   
/*    */   ContractsPackage getContractsPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\ContractsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */