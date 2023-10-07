/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
/*     */ import org.eclipse.emf.ecore.util.FeatureMapUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DynamicEStoreEObjectImpl
/*     */   extends DynamicEObjectImpl
/*     */ {
/*  34 */   protected static final InternalEObject EUNINITIALIZED_CONTAINER = new EObjectImpl();
/*     */ 
/*     */ 
/*     */   
/*     */   protected InternalEObject.EStore eStore;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicEStoreEObjectImpl() {
/*  44 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicEStoreEObjectImpl(InternalEObject.EStore eStore) {
/*  53 */     eSetStore(eStore);
/*  54 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicEStoreEObjectImpl(EClass eClass) {
/*  62 */     super(eClass);
/*  63 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicEStoreEObjectImpl(EClass eClass, InternalEObject.EStore eStore) {
/*  71 */     super(eClass);
/*  72 */     eSetStore(eStore);
/*  73 */     this.eContainer = EUNINITIALIZED_CONTAINER;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eIsCaching() {
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object dynamicGet(int dynamicFeatureID) {
/*  84 */     Object<?> result = (Object<?>)this.eSettings[dynamicFeatureID];
/*  85 */     if (result == null) {
/*     */       
/*  87 */       EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
/*  88 */       if (!eStructuralFeature.isTransient())
/*     */       {
/*  90 */         if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
/*     */           
/*  92 */           this.eSettings[dynamicFeatureID] = result = (Object<?>)createFeatureMap(eStructuralFeature);
/*     */         }
/*  94 */         else if (eStructuralFeature.isMany()) {
/*     */           
/*  96 */           this.eSettings[dynamicFeatureID] = result = createList(eStructuralFeature);
/*     */         }
/*     */         else {
/*     */           
/* 100 */           result = (Object<?>)eStore().get(this, eStructuralFeature, -1);
/* 101 */           if (eIsCaching())
/*     */           {
/* 103 */             this.eSettings[dynamicFeatureID] = result;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dynamicSet(int dynamicFeatureID, Object value) {
/* 114 */     EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
/* 115 */     if (eStructuralFeature.isTransient()) {
/*     */       
/* 117 */       this.eSettings[dynamicFeatureID] = value;
/*     */     }
/*     */     else {
/*     */       
/* 121 */       eStore().set(this, eStructuralFeature, -1, (value == NIL) ? null : value);
/* 122 */       if (eIsCaching())
/*     */       {
/* 124 */         this.eSettings[dynamicFeatureID] = value;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dynamicUnset(int dynamicFeatureID) {
/* 132 */     eStore().unset(this, eDynamicFeature(dynamicFeatureID));
/* 133 */     this.eSettings[dynamicFeatureID] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eDynamicIsSet(EStructuralFeature eStructuralFeature) {
/* 139 */     return 
/* 140 */       eStructuralFeature.isTransient() ? 
/* 141 */       super.eDynamicIsSet(eStructuralFeature) : 
/* 142 */       eStore().isSet(this, eStructuralFeature);
/*     */   }
/*     */ 
/*     */   
/*     */   protected <T> EList<T> createList(EStructuralFeature eStructuralFeature) {
/* 147 */     return (EList<T>)new EStoreEObjectImpl.EStoreEList(this, eStructuralFeature, eStore());
/*     */   }
/*     */ 
/*     */   
/*     */   protected FeatureMap createFeatureMap(EStructuralFeature eStructuralFeature) {
/* 152 */     return (FeatureMap)new EStoreEObjectImpl.EStoreFeatureMap(this, eStructuralFeature, eStore());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalEObject eInternalContainer() {
/* 158 */     if (this.eContainer == EUNINITIALIZED_CONTAINER)
/*     */     {
/* 160 */       eInitializeContainer();
/*     */     }
/*     */     
/* 163 */     return this.eContainer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int eContainerFeatureID() {
/* 169 */     if (this.eContainer == EUNINITIALIZED_CONTAINER)
/*     */     {
/* 171 */       eInitializeContainer();
/*     */     }
/*     */     
/* 174 */     return this.eContainerFeatureID;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eInitializeContainer() {
/* 179 */     this.eContainer = eStore().getContainer(this);
/* 180 */     if (this.eContainer != null) {
/*     */       
/* 182 */       EStructuralFeature eContainingFeature = eStore().getContainingFeature(this);
/* 183 */       if (eContainingFeature instanceof EReference) {
/*     */         
/* 185 */         EReference eContainingReference = (EReference)eContainingFeature;
/* 186 */         EReference eOpposite = eContainingReference.getEOpposite();
/* 187 */         if (eOpposite != null) {
/*     */           
/* 189 */           this.eContainerFeatureID = eClass().getFeatureID((EStructuralFeature)eOpposite);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 194 */       this.eContainerFeatureID = -1 - this.eContainer.eClass().getFeatureID(eContainingFeature);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalEObject.EStore eStore() {
/* 201 */     return this.eStore;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetStore(InternalEObject.EStore store) {
/* 207 */     this.eStore = store;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\DynamicEStoreEObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */