/*    */ package epfl.risd.bip.nusmv.api;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.expression.NuPort;
/*    */ 
/*    */ 
/*    */ public class NuPair
/*    */ {
/*    */   private NuPort port;
/*    */   private String variable;
/*    */   private boolean old;
/*    */   
/*    */   public NuPair() {
/* 13 */     this.port = new NuPort();
/* 14 */     this.old = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuPair(NuPort p, String v) {
/* 19 */     this.port = new NuPort(p);
/* 20 */     this.variable = v;
/* 21 */     this.old = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuPair(NuPair n) {
/* 26 */     this.port = new NuPort(n.port);
/* 27 */     this.variable = n.variable;
/* 28 */     this.old = n.old;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPort(NuPort p) {
/* 33 */     this.port = new NuPort(p);
/*    */   }
/*    */   
/*    */   public void setVariable(String v) {
/* 37 */     this.variable = v;
/*    */   }
/*    */   
/*    */   public void setOld(boolean o) {
/* 41 */     this.old = o;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuPort getPort() {
/* 46 */     return this.port;
/*    */   }
/*    */   
/*    */   public String getVariable() {
/* 50 */     return this.variable;
/*    */   }
/*    */   
/*    */   public boolean getOld() {
/* 54 */     return this.old;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return String.valueOf(this.port.getComponent()) + "." + this.variable;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\api\NuPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */