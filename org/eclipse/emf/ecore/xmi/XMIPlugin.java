/*    */ package org.eclipse.emf.ecore.xmi;
/*    */ 
/*    */ import org.eclipse.emf.common.EMFPlugin;
/*    */ import org.eclipse.emf.common.util.ResourceLocator;
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
/*    */ public final class XMIPlugin
/*    */   extends EMFPlugin
/*    */ {
/* 32 */   public static final XMIPlugin INSTANCE = new XMIPlugin();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Implementation plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private XMIPlugin() {
/* 44 */     super(new ResourceLocator[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocator getPluginResourceLocator() {
/* 53 */     return (ResourceLocator)plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Implementation getPlugin() {
/* 62 */     return plugin;
/*    */   }
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
/*    */   public static class Implementation
/*    */     extends EMFPlugin.EclipsePlugin
/*    */   {
/*    */     public Implementation() {
/* 79 */       XMIPlugin.plugin = this;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\XMIPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */