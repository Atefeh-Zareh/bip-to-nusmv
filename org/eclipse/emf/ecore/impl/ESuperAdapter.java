/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterImpl;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.UniqueEList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ESuperAdapter
/*     */   extends AdapterImpl
/*     */ {
/*     */   protected EList<EClass> subclasses;
/*     */   protected static final int ATTRIBUTES_MODIFIED = 1;
/*     */   protected static final int REFERENCES_MODIFIED = 2;
/*     */   protected static final int STRUCTURAL_FEATURES_MODIFIED = 4;
/*     */   protected static final int CONTAINMENTS_MODIFIED = 8;
/*     */   protected static final int OPERATIONS_MODIFIED = 16;
/*     */   protected static final int SUPERS_MODIFIED = 32;
/*     */   protected static final int LAST_ESUPER_ADAPTER_MODIFIED = 32;
/*     */   
/*     */   public static ESuperAdapter getESuperAdapter(EClass eClass) {
/*  47 */     return ((Holder)eClass).getESuperAdapter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected int modifiedState = 63;
/*     */   
/*     */   protected static final int SUPERS = 0;
/*     */   
/*     */   protected static final int ATTRIBUTES = 1;
/*     */   protected static final int REFERENCES = 2;
/*     */   protected static final int OPERATIONS = 3;
/*     */   protected static final int STRUCTURAL_FEATURES = 4;
/*     */   
/*     */   protected static int getFeatureID(Notification notification) {
/*  70 */     int featureID = notification.getFeatureID(null);
/*  71 */     switch (featureID) {
/*     */       case 10:
/*  73 */         return 0;
/*  74 */       case 15: return 1;
/*  75 */       case 14: return 2;
/*  76 */       case 11: return 3;
/*  77 */       case 21: return 4;
/*     */     } 
/*  79 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isModified() {
/*  89 */     return (this.modifiedState != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdapterForType(Object type) {
/*  95 */     return (type == ESuperAdapter.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyChanged(Notification notification) {
/* 101 */     int eventType = notification.getEventType();
/* 102 */     if (eventType != 8) {
/*     */       
/* 104 */       int featureID = getFeatureID(notification);
/* 105 */       if (featureID == 0) {
/*     */         Object object1; Object newValue; Object oldValue; Object object2;
/* 107 */         switch (eventType) {
/*     */ 
/*     */           
/*     */           case 1:
/* 111 */             object1 = notification.getOldValue();
/* 112 */             if (object1 != null) {
/*     */               
/* 114 */               ESuperAdapter eSuperAdapter = ((Holder)object1).getESuperAdapter();
/* 115 */               eSuperAdapter.getSubclasses().remove(notification.getNotifier());
/*     */             } 
/* 117 */             object2 = notification.getNewValue();
/* 118 */             if (object2 != null) {
/*     */               
/* 120 */               Holder holder = (Holder)object2;
/* 121 */               if (!holder.isFrozen()) {
/*     */                 
/* 123 */                 ESuperAdapter eSuperAdapter = holder.getESuperAdapter();
/* 124 */                 eSuperAdapter.getSubclasses().add(notification.getNotifier());
/*     */               } 
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/*     */           case 9:
/* 132 */             newValue = notification.getNewValue();
/* 133 */             if (newValue != null) {
/*     */               
/* 135 */               Holder holder = (Holder)newValue;
/* 136 */               if (!holder.isFrozen()) {
/*     */                 
/* 138 */                 ESuperAdapter eSuperAdapter = holder.getESuperAdapter();
/* 139 */                 eSuperAdapter.getSubclasses().add(notification.getNotifier());
/*     */               } 
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/* 146 */             newValue = notification.getNewValue();
/* 147 */             if (newValue != null)
/*     */             {
/* 149 */               for (Iterator<?> i = ((Collection)newValue).iterator(); i.hasNext(); ) {
/*     */                 
/* 151 */                 Holder holder = (Holder)i.next();
/* 152 */                 if (!holder.isFrozen()) {
/*     */                   
/* 154 */                   ESuperAdapter eSuperAdapter = holder.getESuperAdapter();
/* 155 */                   eSuperAdapter.getSubclasses().add(notification.getNotifier());
/*     */                 } 
/*     */               } 
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 163 */             oldValue = notification.getOldValue();
/* 164 */             if (oldValue != null) {
/*     */               
/* 166 */               Holder holder = (Holder)oldValue;
/* 167 */               if (!holder.isFrozen()) {
/*     */                 
/* 169 */                 ESuperAdapter eSuperAdapter = holder.getESuperAdapter();
/* 170 */                 eSuperAdapter.getSubclasses().remove(notification.getNotifier());
/*     */               } 
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 6:
/* 177 */             oldValue = notification.getOldValue();
/* 178 */             if (oldValue != null)
/*     */             {
/* 180 */               for (Iterator<?> i = ((Collection)oldValue).iterator(); i.hasNext(); ) {
/*     */                 
/* 182 */                 Holder holder = (Holder)i.next();
/* 183 */                 if (!holder.isFrozen()) {
/*     */                   
/* 185 */                   ESuperAdapter eSuperAdapter = holder.getESuperAdapter();
/* 186 */                   eSuperAdapter.getSubclasses().remove(notification.getNotifier());
/*     */                 } 
/*     */               } 
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 195 */       setFlags(featureID);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllAttributesCollectionModified() {
/* 201 */     return ((this.modifiedState & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllAttributesCollectionModified(boolean set) {
/* 206 */     if (set) {
/*     */       
/* 208 */       this.modifiedState |= 0x1;
/*     */     }
/*     */     else {
/*     */       
/* 212 */       this.modifiedState &= 0xFFFFFFFE;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllContainmentsCollectionModified() {
/* 218 */     return ((this.modifiedState & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllContainmentsCollectionModified(boolean set) {
/* 223 */     if (set) {
/*     */       
/* 225 */       this.modifiedState |= 0x8;
/*     */     }
/*     */     else {
/*     */       
/* 229 */       this.modifiedState &= 0xFFFFFFF7;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllReferencesCollectionModified() {
/* 235 */     return ((this.modifiedState & 0x2) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllReferencesCollectionModified(boolean set) {
/* 240 */     if (set) {
/*     */       
/* 242 */       this.modifiedState |= 0x2;
/*     */     }
/*     */     else {
/*     */       
/* 246 */       this.modifiedState &= 0xFFFFFFFD;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllOperationsCollectionModified() {
/* 252 */     return ((this.modifiedState & 0x10) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllOperationsCollectionModified(boolean set) {
/* 257 */     if (set) {
/*     */       
/* 259 */       this.modifiedState |= 0x10;
/*     */     }
/*     */     else {
/*     */       
/* 263 */       this.modifiedState &= 0xFFFFFFEF;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllStructuralFeaturesCollectionModified() {
/* 269 */     return ((this.modifiedState & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllStructuralFeaturesCollectionModified(boolean set) {
/* 274 */     if (set) {
/*     */       
/* 276 */       this.modifiedState |= 0x4;
/*     */     }
/*     */     else {
/*     */       
/* 280 */       this.modifiedState &= 0xFFFFFFFB;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllSuperCollectionModified() {
/* 286 */     return ((this.modifiedState & 0x20) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllSuperCollectionModified(boolean set) {
/* 291 */     if (set) {
/*     */       
/* 293 */       this.modifiedState |= 0x20;
/*     */     }
/*     */     else {
/*     */       
/* 297 */       this.modifiedState &= 0xFFFFFFDF;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EList<EClass> getSubclasses() {
/* 303 */     if (this.subclasses == null)
/*     */     {
/* 305 */       this.subclasses = 
/* 306 */         (EList<EClass>)new UniqueEList<EClass>()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           protected Object[] newData(int capacity) {
/* 313 */             return (Object[])new EClass[capacity];
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           protected boolean useEquals() {
/* 319 */             return false;
/*     */           }
/*     */         };
/*     */     }
/* 323 */     return this.subclasses;
/*     */   }
/*     */ 
/*     */   
/*     */   void setFlags(int featureId) {
/* 328 */     int oldModifiedState = this.modifiedState;
/*     */     
/* 330 */     switch (featureId) {
/*     */ 
/*     */       
/*     */       case 1:
/* 334 */         setAllAttributesCollectionModified(true);
/* 335 */         setAllStructuralFeaturesCollectionModified(true);
/* 336 */         setAllContainmentsCollectionModified(true);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 341 */         setAllReferencesCollectionModified(true);
/* 342 */         setAllStructuralFeaturesCollectionModified(true);
/* 343 */         setAllContainmentsCollectionModified(true);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 348 */         setAllAttributesCollectionModified(true);
/* 349 */         setAllReferencesCollectionModified(true);
/* 350 */         setAllStructuralFeaturesCollectionModified(true);
/* 351 */         setAllContainmentsCollectionModified(true);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 356 */         setAllOperationsCollectionModified(true);
/* 357 */         setAllContainmentsCollectionModified(true);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 0:
/* 362 */         setAllSuperCollectionModified(true);
/* 363 */         setAllOperationsCollectionModified(true);
/* 364 */         setAllContainmentsCollectionModified(true);
/* 365 */         setAllAttributesCollectionModified(true);
/* 366 */         setAllReferencesCollectionModified(true);
/* 367 */         setAllStructuralFeaturesCollectionModified(true);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 372 */     if (this.modifiedState != oldModifiedState && this.subclasses != null)
/*     */     {
/* 374 */       for (Iterator<?> i = this.subclasses.iterator(); i.hasNext(); ) {
/*     */         
/* 376 */         Holder subclass = (Holder)i.next();
/* 377 */         ESuperAdapter eSuperAdapter = subclass.getESuperAdapter();
/* 378 */         eSuperAdapter.setFlags(featureId);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Holder {
/*     */     ESuperAdapter getESuperAdapter();
/*     */     
/*     */     boolean isFrozen();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\ESuperAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */