/*    */ package BIP2BIP;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*    */ import ujf.verimag.bip.Core.Interactions.Component;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ 
/*    */ 
/*    */ public class TComponentType
/*    */ {
/*    */   private ComponentType CT;
/*    */   
/*    */   public TComponentType(ComponentType CT) {
/* 14 */     this.CT = CT;
/*    */   }
/*    */ 
/*    */   
/*    */   private int getHierarchicalLevel(ComponentType c) {
/* 19 */     if (c instanceof ujf.verimag.bip.Core.Behaviors.AtomType) return 1; 
/* 20 */     int max = 0;
/* 21 */     CompoundType ct = (CompoundType)c;
/* 22 */     ComponentType cc = null;
/* 23 */     for (Iterator<?> is = ct.getSubcomponent().iterator(); is.hasNext(); ) {
/*    */       
/* 25 */       Component comp = (Component)is.next();
/* 26 */       int x = getHierarchicalLevel(comp.getType());
/* 27 */       if (max < x) {
/*    */         
/* 29 */         max = x;
/* 30 */         cc = comp.getType();
/*    */       } 
/*    */     } 
/* 33 */     return 1 + getHierarchicalLevel(cc);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHierarchical_Level() {
/* 38 */     return getHierarchicalLevel(this.CT);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\TComponentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */