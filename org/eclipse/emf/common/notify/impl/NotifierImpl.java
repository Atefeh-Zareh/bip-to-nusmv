/*    */ package org.eclipse.emf.common.notify.impl;
/*    */ 
/*    */ import org.eclipse.emf.common.notify.Adapter;
/*    */ import org.eclipse.emf.common.util.BasicEList;
/*    */ import org.eclipse.emf.common.util.EList;
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
/*    */ public class NotifierImpl
/*    */   extends BasicNotifierImpl
/*    */ {
/*    */   protected static final int EDELIVER = 1;
/*    */   protected static final int ELAST_NOTIFIER_FLAG = 1;
/* 43 */   protected int eFlags = 1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected BasicEList<Adapter> eAdapters;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EList<Adapter> eAdapters() {
/* 61 */     if (this.eAdapters == null)
/*    */     {
/* 63 */       this.eAdapters = new BasicNotifierImpl.EAdapterList<Adapter>(this);
/*    */     }
/* 65 */     return (EList<Adapter>)this.eAdapters;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected BasicEList<Adapter> eBasicAdapters() {
/* 71 */     return this.eAdapters;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean eDeliver() {
/* 77 */     return ((this.eFlags & 0x1) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void eSetDeliver(boolean deliver) {
/* 83 */     if (deliver) {
/*    */       
/* 85 */       this.eFlags |= 0x1;
/*    */     }
/*    */     else {
/*    */       
/* 89 */       this.eFlags &= 0xFFFFFFFE;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\NotifierImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */