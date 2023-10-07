/*     */ package org.eclipse.emf.ecore;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface EOperation
/*     */   extends ETypedElement
/*     */ {
/*     */   EClass getEContainingClass();
/*     */   
/*     */   EList<EParameter> getEParameters();
/*     */   
/*     */   EList<EClassifier> getEExceptions();
/*     */   
/*     */   EList<EGenericType> getEGenericExceptions();
/*     */   
/*     */   int getOperationID();
/*     */   
/*     */   boolean isOverrideOf(EOperation paramEOperation);
/*     */   
/*     */   EList<ETypeParameter> getETypeParameters();
/*     */   
/*     */   public static interface Internal
/*     */     extends EOperation, InternalEObject
/*     */   {
/*     */     InvocationDelegate getInvocationDelegate();
/*     */     
/*     */     void setInvocationDelegate(InvocationDelegate param1InvocationDelegate);
/*     */     
/*     */     public static interface InvocationDelegate
/*     */     {
/*     */       Object dynamicInvoke(InternalEObject param2InternalEObject, EList<?> param2EList) throws InvocationTargetException;
/*     */       
/*     */       public static interface Factory
/*     */       {
/*     */         EOperation.Internal.InvocationDelegate createInvocationDelegate(EOperation param3EOperation);
/*     */         
/*     */         public static interface Descriptor
/*     */         {
/*     */           EOperation.Internal.InvocationDelegate.Factory getFactory();
/*     */         }
/*     */         
/*     */         public static interface Registry
/*     */           extends Map<String, Object>
/*     */         {
/* 195 */           public static final Registry INSTANCE = new Impl();
/*     */           
/*     */           EOperation.Internal.InvocationDelegate.Factory getFactory(String param4String);
/*     */           
/*     */           public static class Impl
/*     */             extends HashMap<String, Object>
/*     */             implements Registry
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Object get(Object key) {
/* 206 */               Object factory = super.get(key);
/* 207 */               if (factory instanceof EOperation.Internal.InvocationDelegate.Factory.Descriptor) {
/*     */                 
/* 209 */                 EOperation.Internal.InvocationDelegate.Factory.Descriptor factoryDescriptor = (EOperation.Internal.InvocationDelegate.Factory.Descriptor)factory;
/* 210 */                 factory = factoryDescriptor.getFactory();
/* 211 */                 put((String)key, factory);
/* 212 */                 return factory;
/*     */               } 
/*     */ 
/*     */               
/* 216 */               return factory;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public EOperation.Internal.InvocationDelegate.Factory getFactory(String uri) {
/* 222 */               return (EOperation.Internal.InvocationDelegate.Factory)get(uri);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EOperation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */