/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterImpl;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EContentAdapter
/*     */   extends AdapterImpl
/*     */ {
/*     */   public void notifyChanged(Notification notification) {
/*  46 */     selfAdapt(notification);
/*     */     
/*  48 */     super.notifyChanged(notification);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void selfAdapt(Notification notification) {
/*  57 */     Object notifier = notification.getNotifier();
/*  58 */     if (notifier instanceof ResourceSet) {
/*     */       
/*  60 */       if (notification.getFeatureID(ResourceSet.class) == 0)
/*     */       {
/*  62 */         handleContainment(notification);
/*     */       }
/*     */     }
/*  65 */     else if (notifier instanceof Resource) {
/*     */       
/*  67 */       if (notification.getFeatureID(Resource.class) == 2)
/*     */       {
/*  69 */         handleContainment(notification);
/*     */       }
/*     */     }
/*  72 */     else if (notifier instanceof EObject) {
/*     */       
/*  74 */       Object feature = notification.getFeature();
/*  75 */       if (feature instanceof EReference) {
/*     */         
/*  77 */         EReference eReference = (EReference)feature;
/*  78 */         if (eReference.isContainment())
/*     */         {
/*  80 */           handleContainment(notification); } 
/*     */       } 
/*     */     } 
/*     */   } protected void handleContainment(Notification notification) {
/*     */     Notifier notifier2;
/*     */     Object object;
/*     */     Notifier notifier1;
/*     */     Notifier newValue;
/*     */     Collection<Notifier> newValues;
/*     */     Notifier oldValue;
/*     */     Collection<Notifier> oldValues;
/*  91 */     switch (notification.getEventType()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 100 */         notifier2 = (Notifier)notification.getOldValue();
/* 101 */         if (notifier2.eAdapters().contains(this)) {
/*     */           
/* 103 */           removeAdapter(notifier2);
/* 104 */           Notifier notifier = (Notifier)notification.getNewValue();
/* 105 */           addAdapter(notifier);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 111 */         object = notification.getOldValue();
/* 112 */         if (object != Boolean.TRUE && object != Boolean.FALSE) {
/*     */           
/* 114 */           if (object != null)
/*     */           {
/* 116 */             removeAdapter((Notifier)object);
/*     */           }
/* 118 */           Notifier notifier = (Notifier)notification.getNewValue();
/* 119 */           if (notifier != null)
/*     */           {
/* 121 */             addAdapter(notifier);
/*     */           }
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 128 */         notifier1 = (Notifier)notification.getOldValue();
/* 129 */         if (notifier1 != null)
/*     */         {
/* 131 */           removeAdapter(notifier1);
/*     */         }
/* 133 */         notifier3 = (Notifier)notification.getNewValue();
/* 134 */         if (notifier3 != null)
/*     */         {
/* 136 */           addAdapter(notifier3);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 142 */         newValue = (Notifier)notification.getNewValue();
/* 143 */         if (newValue != null)
/*     */         {
/* 145 */           addAdapter(newValue);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 151 */         newValues = (Collection<Notifier>)notification.getNewValue();
/* 152 */         for (Notifier notifier3 : newValues)
/*     */         {
/* 154 */           addAdapter(notifier3);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 160 */         oldValue = (Notifier)notification.getOldValue();
/* 161 */         if (oldValue != null)
/*     */         {
/* 163 */           removeAdapter(oldValue);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 169 */         oldValues = (Collection<Notifier>)notification.getOldValue();
/* 170 */         for (Notifier oldContentValue : oldValues)
/*     */         {
/* 172 */           removeAdapter(oldContentValue);
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(Notifier target) {
/* 186 */     if (target instanceof EObject) {
/*     */       
/* 188 */       setTarget((EObject)target);
/*     */     }
/* 190 */     else if (target instanceof Resource) {
/*     */       
/* 192 */       setTarget((Resource)target);
/*     */     }
/* 194 */     else if (target instanceof ResourceSet) {
/*     */       
/* 196 */       setTarget((ResourceSet)target);
/*     */     }
/*     */     else {
/*     */       
/* 200 */       basicSetTarget(target);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void basicSetTarget(Notifier target) {
/* 209 */     super.setTarget(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(EObject target) {
/* 218 */     basicSetTarget((Notifier)target);
/* 219 */     Iterator<? extends Notifier> i = resolve() ? 
/* 220 */       target.eContents().iterator() : (
/* 221 */       (InternalEList<? extends Notifier>)target.eContents()).basicIterator();
/* 222 */     while (i.hasNext()) {
/*     */       
/* 224 */       Notifier notifier = i.next();
/* 225 */       addAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(Resource target) {
/* 235 */     basicSetTarget((Notifier)target);
/* 236 */     EList<Notifier> eList = target.getContents();
/* 237 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*     */       
/* 239 */       Notifier notifier = eList.get(i);
/* 240 */       addAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(ResourceSet target) {
/* 250 */     basicSetTarget((Notifier)target);
/* 251 */     EList<Notifier> eList = target.getResources();
/* 252 */     for (int i = 0; i < eList.size(); i++) {
/*     */       
/* 254 */       Notifier notifier = eList.get(i);
/* 255 */       addAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetTarget(Notifier target) {
/* 266 */     unsetTarget(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void basicUnsetTarget(Notifier target) {
/* 274 */     super.unsetTarget(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected void unsetTarget(Object target) {
/* 285 */     if (target instanceof EObject) {
/*     */       
/* 287 */       unsetTarget((EObject)target);
/*     */     }
/* 289 */     else if (target instanceof Resource) {
/*     */       
/* 291 */       unsetTarget((Resource)target);
/*     */     }
/* 293 */     else if (target instanceof ResourceSet) {
/*     */       
/* 295 */       unsetTarget((ResourceSet)target);
/*     */     }
/*     */     else {
/*     */       
/* 299 */       basicUnsetTarget((Notifier)target);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unsetTarget(EObject target) {
/* 309 */     basicUnsetTarget((Notifier)target);
/* 310 */     Iterator<? extends Notifier> i = resolve() ? 
/* 311 */       target.eContents().iterator() : (
/* 312 */       (InternalEList<? extends Notifier>)target.eContents()).basicIterator();
/* 313 */     while (i.hasNext()) {
/*     */       
/* 315 */       Notifier notifier = i.next();
/* 316 */       removeAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unsetTarget(Resource target) {
/* 326 */     basicUnsetTarget((Notifier)target);
/* 327 */     EList<Notifier> eList = target.getContents();
/* 328 */     for (int i = 0, size = eList.size(); i < size; i++) {
/*     */       
/* 330 */       Notifier notifier = eList.get(i);
/* 331 */       removeAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unsetTarget(ResourceSet target) {
/* 341 */     basicUnsetTarget((Notifier)target);
/* 342 */     EList<Notifier> eList = target.getResources();
/* 343 */     for (int i = 0; i < eList.size(); i++) {
/*     */       
/* 345 */       Notifier notifier = eList.get(i);
/* 346 */       removeAdapter(notifier);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addAdapter(Notifier notifier) {
/* 352 */     notifier.eAdapters().add(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeAdapter(Notifier notifier) {
/* 357 */     notifier.eAdapters().remove(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean resolve() {
/* 362 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EContentAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */