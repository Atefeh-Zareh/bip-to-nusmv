/*    */ package trans;
/*    */ 
/*    */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*    */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Init
/*    */ {
/*    */   static PortType PTSyn;
/*    */   static Module M;
/*    */   
/*    */   public Init(Module M, boolean createintport) {
/* 16 */     Init.M = M;
/* 17 */     if (createintport) {
/*    */       
/* 19 */       PTSyn = BehaviorsFactory.eINSTANCE.createPortType();
/* 20 */       PTSyn.setName("SynEPort");
/* 21 */       PTSyn.setModule(M);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\Init.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */