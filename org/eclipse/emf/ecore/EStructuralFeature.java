/*     */ package org.eclipse.emf.ecore;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
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
/*     */ public interface EStructuralFeature
/*     */   extends ETypedElement
/*     */ {
/*     */   boolean isTransient();
/*     */   
/*     */   void setTransient(boolean paramBoolean);
/*     */   
/*     */   boolean isVolatile();
/*     */   
/*     */   void setVolatile(boolean paramBoolean);
/*     */   
/*     */   boolean isChangeable();
/*     */   
/*     */   void setChangeable(boolean paramBoolean);
/*     */   
/*     */   String getDefaultValueLiteral();
/*     */   
/*     */   void setDefaultValueLiteral(String paramString);
/*     */   
/*     */   Object getDefaultValue();
/*     */   
/*     */   void setDefaultValue(Object paramObject);
/*     */   
/*     */   boolean isUnsettable();
/*     */   
/*     */   void setUnsettable(boolean paramBoolean);
/*     */   
/*     */   boolean isDerived();
/*     */   
/*     */   void setDerived(boolean paramBoolean);
/*     */   
/*     */   EClass getEContainingClass();
/*     */   
/*     */   int getFeatureID();
/*     */   
/*     */   Class<?> getContainerClass();
/*     */   
/*     */   public static interface Internal
/*     */     extends EStructuralFeature, InternalEObject
/*     */   {
/*     */     SettingDelegate getSettingDelegate();
/*     */     
/*     */     void setSettingDelegate(SettingDelegate param1SettingDelegate);
/*     */     
/*     */     boolean isFeatureMap();
/*     */     
/*     */     FeatureMap.Entry.Internal getFeatureMapEntryPrototype();
/*     */     
/*     */     void setFeatureMapEntryPrototype(FeatureMap.Entry.Internal param1Internal);
/*     */     
/*     */     boolean isID();
/*     */     
/*     */     boolean isResolveProxies();
/*     */     
/*     */     boolean isContainer();
/*     */     
/*     */     boolean isContainment();
/*     */     
/*     */     EReference getEOpposite();
/*     */     
/*     */     public static interface DynamicValueHolder
/*     */     {
/* 342 */       public static final Object NIL = new Object();
/*     */ 
/*     */ 
/*     */       
/*     */       Object dynamicGet(int param2Int);
/*     */ 
/*     */ 
/*     */       
/*     */       void dynamicSet(int param2Int, Object param2Object);
/*     */ 
/*     */ 
/*     */       
/*     */       void dynamicUnset(int param2Int);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static interface SettingDelegate
/*     */     {
/*     */       EStructuralFeature.Setting dynamicSetting(InternalEObject param2InternalEObject, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int);
/*     */ 
/*     */       
/*     */       Object dynamicGet(InternalEObject param2InternalEObject, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int, boolean param2Boolean1, boolean param2Boolean2);
/*     */ 
/*     */       
/*     */       void dynamicSet(InternalEObject param2InternalEObject, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int, Object param2Object);
/*     */ 
/*     */       
/*     */       boolean dynamicIsSet(InternalEObject param2InternalEObject, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int);
/*     */ 
/*     */       
/*     */       void dynamicUnset(InternalEObject param2InternalEObject, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int);
/*     */ 
/*     */       
/*     */       NotificationChain dynamicInverseAdd(InternalEObject param2InternalEObject1, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int, InternalEObject param2InternalEObject2, NotificationChain param2NotificationChain);
/*     */ 
/*     */       
/*     */       NotificationChain dynamicInverseRemove(InternalEObject param2InternalEObject1, EStructuralFeature.Internal.DynamicValueHolder param2DynamicValueHolder, int param2Int, InternalEObject param2InternalEObject2, NotificationChain param2NotificationChain);
/*     */ 
/*     */       
/*     */       public static interface Factory
/*     */       {
/*     */         EStructuralFeature.Internal.SettingDelegate createSettingDelegate(EStructuralFeature param3EStructuralFeature);
/*     */ 
/*     */         
/*     */         public static interface Descriptor
/*     */         {
/*     */           EStructuralFeature.Internal.SettingDelegate.Factory getFactory();
/*     */         }
/*     */ 
/*     */         
/*     */         public static interface Registry
/*     */           extends Map<String, Object>
/*     */         {
/* 396 */           public static final Registry INSTANCE = new Impl();
/*     */           
/*     */           EStructuralFeature.Internal.SettingDelegate.Factory getFactory(String param4String);
/*     */           
/*     */           public static class Impl
/*     */             extends HashMap<String, Object>
/*     */             implements Registry
/*     */           {
/*     */             private static final long serialVersionUID = 1L;
/*     */             
/*     */             public Object get(Object key) {
/* 407 */               Object factory = super.get(key);
/* 408 */               if (factory instanceof EStructuralFeature.Internal.SettingDelegate.Factory.Descriptor) {
/*     */                 
/* 410 */                 EStructuralFeature.Internal.SettingDelegate.Factory.Descriptor factoryDescriptor = (EStructuralFeature.Internal.SettingDelegate.Factory.Descriptor)factory;
/* 411 */                 factory = factoryDescriptor.getFactory();
/* 412 */                 put((String)key, factory);
/* 413 */                 return factory;
/*     */               } 
/*     */ 
/*     */               
/* 417 */               return factory;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public EStructuralFeature.Internal.SettingDelegate.Factory getFactory(String uri) {
/* 423 */               return (EStructuralFeature.Internal.SettingDelegate.Factory)get(uri);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Setting {
/*     */     EObject getEObject();
/*     */     
/*     */     EStructuralFeature getEStructuralFeature();
/*     */     
/*     */     Object get(boolean param1Boolean);
/*     */     
/*     */     void set(Object param1Object);
/*     */     
/*     */     boolean isSet();
/*     */     
/*     */     void unset();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\EStructuralFeature.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */