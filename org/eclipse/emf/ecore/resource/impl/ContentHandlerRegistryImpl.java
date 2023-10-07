/*    */ package org.eclipse.emf.ecore.resource.impl;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.TreeMap;
/*    */ import org.eclipse.emf.ecore.resource.ContentHandler;
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
/*    */ public class ContentHandlerRegistryImpl
/*    */   extends TreeMap<Integer, List<ContentHandler>>
/*    */   implements ContentHandler.Registry
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public void put(int priority, ContentHandler contentHandler) {
/* 31 */     Integer integerPriority = Integer.valueOf(priority);
/* 32 */     List<ContentHandler> contentHandlers = get(integerPriority);
/* 33 */     if (contentHandlers == null)
/*    */     {
/* 35 */       put(integerPriority, contentHandlers = new ArrayList<ContentHandler>());
/*    */     }
/* 37 */     contentHandlers.add(contentHandler);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ContentHandler> contentHandlers() {
/* 42 */     ArrayList<ContentHandler> result = new ArrayList<ContentHandler>();
/* 43 */     for (List<ContentHandler> contentHandlers : values())
/*    */     {
/* 45 */       result.addAll(contentHandlers);
/*    */     }
/* 47 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ContentHandlerRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */