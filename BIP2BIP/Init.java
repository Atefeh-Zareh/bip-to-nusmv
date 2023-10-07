/*    */ package BIP2BIP;
/*    */ 
/*    */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ 
/*    */ public class Init
/*    */ {
/*    */   public static PortType PTSyn;
/*    */   public static Module M;
/*    */   
/*    */   public static void Initialize(Module m, PortType pt) {
/* 12 */     PTSyn = pt;
/* 13 */     M = m;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\Init.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */