/*    */ package distributed;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import ujf.verimag.bip.Core.Behaviors.Port;
/*    */ import ujf.verimag.bip.Core.Interactions.Component;
/*    */ import ujf.verimag.bip.Core.Interactions.Connector;
/*    */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*    */ 
/*    */ public class DConnector {
/* 11 */   private List<String> LStringcomp = new LinkedList<String>(); private Connector Conn;
/* 12 */   private List<Component> Lcomp = new LinkedList<Component>();
/* 13 */   private List<String> LStringPort = new LinkedList<String>();
/* 14 */   private List<Port> LPort = new LinkedList<Port>();
/*    */   
/*    */   private int NumPort;
/*    */   
/*    */   public DConnector(Connector Conn) {
/* 19 */     this.Conn = Conn;
/* 20 */     this.NumPort = Conn.getActualPort().size();
/* 21 */     setLcompPort();
/*    */   }
/*    */ 
/*    */   
/*    */   private void setLcompPort() {
/* 26 */     for (Object ipr_i : this.Conn.getActualPort()) {
/*    */       
/* 28 */       if (ipr_i instanceof InnerPortReference) {
/*    */         
/* 30 */         InnerPortReference ipr = (InnerPortReference)ipr_i;
/* 31 */         if (ipr.getTargetInstance().getTargetPart() instanceof Component) {
/*    */           
/* 33 */           this.Lcomp.add((Component)ipr.getTargetInstance().getTargetPart());
/* 34 */           this.LStringcomp.add(((Component)ipr.getTargetInstance().getTargetPart()).getName());
/* 35 */           this.LPort.add(ipr.getTargetPort());
/* 36 */           this.LStringPort.add(ipr.getTargetPort().getName());
/*    */           
/*    */           continue;
/*    */         } 
/* 40 */         System.out.println("Error in the Example:");
/* 41 */         System.out.println("Connector must be connected to Component, else we do not consider this case");
/* 42 */         System.exit(0);
/*    */         
/*    */         continue;
/*    */       } 
/*    */       
/* 47 */       System.out.println("Error in the Example:");
/* 48 */       System.out.println("Connector must be connected to Component, else we do not consider this case");
/* 49 */       System.exit(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Component> getLCompenent() {
/* 57 */     return this.Lcomp;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getLStringComponent() {
/* 62 */     return this.LStringcomp;
/*    */   }
/*    */ 
/*    */   
/*    */   public Connector getConnector() {
/* 67 */     return this.Conn;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Port> getPort() {
/* 72 */     return this.LPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getStringPort() {
/* 77 */     return this.LStringPort;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumPort() {
/* 82 */     return this.NumPort;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\DConnector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */