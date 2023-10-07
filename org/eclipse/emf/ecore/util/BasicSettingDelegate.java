/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BasicSettingDelegate
/*     */   implements EStructuralFeature.Internal.SettingDelegate
/*     */ {
/*     */   protected EStructuralFeature eStructuralFeature;
/*     */   
/*     */   public static abstract class Stateless
/*     */     extends BasicSettingDelegate
/*     */   {
/*     */     public Stateless(EStructuralFeature eStructuralFeature) {
/* 137 */       super(eStructuralFeature);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final EStructuralFeature.Setting dynamicSetting(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID) {
/* 143 */       return setting(owner);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected EStructuralFeature.Setting setting(final InternalEObject owner) {
/* 153 */       return 
/* 154 */         new EStructuralFeature.Setting()
/*     */         {
/*     */           public EObject getEObject()
/*     */           {
/* 158 */             return (EObject)owner;
/*     */           }
/*     */ 
/*     */           
/*     */           public EStructuralFeature getEStructuralFeature() {
/* 163 */             return BasicSettingDelegate.Stateless.this.eStructuralFeature;
/*     */           }
/*     */ 
/*     */           
/*     */           public Object get(boolean resolve) {
/* 168 */             return BasicSettingDelegate.Stateless.this.get(owner, resolve, true);
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean isSet() {
/* 173 */             return BasicSettingDelegate.Stateless.this.isSet(owner);
/*     */           }
/*     */ 
/*     */           
/*     */           public void set(Object newValue) {
/* 178 */             BasicSettingDelegate.Stateless.this.set(owner, newValue);
/*     */           }
/*     */ 
/*     */           
/*     */           public void unset() {
/* 183 */             BasicSettingDelegate.Stateless.this.unset(owner);
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Object dynamicGet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, boolean resolve, boolean coreType) {
/* 191 */       return get(owner, resolve, coreType);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract Object get(InternalEObject param1InternalEObject, boolean param1Boolean1, boolean param1Boolean2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID) {
/* 207 */       return isSet(owner);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract boolean isSet(InternalEObject param1InternalEObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, Object newValue) {
/* 221 */       set(owner, newValue);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void set(InternalEObject owner, Object newValue) {
/* 232 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID) {
/* 238 */       unset(owner);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void unset(InternalEObject owner) {
/* 248 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, InternalEObject otherEnd, NotificationChain notifications) {
/* 259 */       return inverseAdd(owner, otherEnd, notifications);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected NotificationChain inverseAdd(InternalEObject owner, InternalEObject otherEnd, NotificationChain notifications) {
/* 275 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, InternalEObject otherEnd, NotificationChain notifications) {
/* 286 */       return inverseRemove(owner, otherEnd, notifications);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected NotificationChain inverseRemove(InternalEObject owner, InternalEObject otherEnd, NotificationChain notifications) {
/* 302 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public BasicSettingDelegate(EStructuralFeature eStructuralFeature) {
/*     */     this.eStructuralFeature = eStructuralFeature;
/*     */   }
/*     */   
/*     */   public EStructuralFeature.Setting dynamicSetting(final InternalEObject owner, final EStructuralFeature.Internal.DynamicValueHolder settings, final int dynamicFeatureID) {
/*     */     return new EStructuralFeature.Setting() {
/*     */         public EObject getEObject() {
/*     */           return (EObject)owner;
/*     */         }
/*     */         
/*     */         public EStructuralFeature getEStructuralFeature() {
/*     */           return BasicSettingDelegate.this.eStructuralFeature;
/*     */         }
/*     */         
/*     */         public Object get(boolean resolve) {
/*     */           return BasicSettingDelegate.this.dynamicGet(owner, settings, dynamicFeatureID, resolve, true);
/*     */         }
/*     */         
/*     */         public boolean isSet() {
/*     */           return BasicSettingDelegate.this.dynamicIsSet(owner, settings, dynamicFeatureID);
/*     */         }
/*     */         
/*     */         public void set(Object newValue) {
/*     */           BasicSettingDelegate.this.dynamicSet(owner, settings, dynamicFeatureID, newValue);
/*     */         }
/*     */         
/*     */         public void unset() {
/*     */           BasicSettingDelegate.this.dynamicUnset(owner, settings, dynamicFeatureID);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public abstract Object dynamicGet(InternalEObject paramInternalEObject, EStructuralFeature.Internal.DynamicValueHolder paramDynamicValueHolder, int paramInt, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   public abstract boolean dynamicIsSet(InternalEObject paramInternalEObject, EStructuralFeature.Internal.DynamicValueHolder paramDynamicValueHolder, int paramInt);
/*     */   
/*     */   public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, Object newValue) {
/*     */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID) {
/*     */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, InternalEObject otherEnd, NotificationChain notifications) {
/*     */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID, InternalEObject otherEnd, NotificationChain notifications) {
/*     */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\BasicSettingDelegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */