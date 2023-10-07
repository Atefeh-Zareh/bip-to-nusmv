/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EModelElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreEMap;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EAnnotationImpl
/*     */   extends EModelElementImpl
/*     */   implements EAnnotation
/*     */ {
/*  66 */   protected static final String SOURCE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   protected String source = SOURCE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EMap<String, String> details;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<EObject> contents;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<EObject> references;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 126 */     return EcorePackage.Literals.EANNOTATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSource() {
/* 136 */     return this.source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceGen(String newSource) {
/* 146 */     String oldSource = this.source;
/* 147 */     this.source = newSource;
/* 148 */     if (eNotificationRequired()) {
/* 149 */       eNotify((Notification)new ENotificationImpl(this, 1, 1, oldSource, this.source));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSource(String newSource) {
/* 154 */     setSourceGen((newSource == null) ? null : newSource.intern());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<String, String> getDetails() {
/* 164 */     if (this.details == null)
/*     */     {
/* 166 */       this.details = 
/* 167 */         (EMap<String, String>)new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, 2)
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           protected void ensureEntryDataExists() {
/* 174 */             if (this.entryData == null) {
/*     */ 
/*     */ 
/*     */               
/* 178 */               BasicEList[] result = newEntryData(2 * this.size + 1);
/* 179 */               for (BasicEMap.Entry<String, String> entry : (Iterable<BasicEMap.Entry<String, String>>)this.delegateEList) {
/*     */                 
/* 181 */                 int hash = entry.getHash();
/* 182 */                 int index = (hash & Integer.MAX_VALUE) % result.length;
/* 183 */                 BasicEList<BasicEMap.Entry<String, String>> eList = result[index];
/* 184 */                 if (eList == null)
/*     */                 {
/* 186 */                   eList = result[index] = newList();
/*     */                 }
/* 188 */                 eList.add(entry);
/*     */               } 
/* 190 */               this.entryData = result;
/*     */             } 
/*     */           }
/*     */         };
/*     */     }
/* 195 */     return this.details;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EModelElement getEModelElement() {
/* 205 */     if (eContainerFeatureID() != 3) return null; 
/* 206 */     return (EModelElement)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetEModelElement(EModelElement newEModelElement, NotificationChain msgs) {
/* 216 */     msgs = eBasicSetContainer((InternalEObject)newEModelElement, 3, msgs);
/* 217 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEModelElement(EModelElement newEModelElement) {
/* 227 */     if (newEModelElement != eInternalContainer() || (eContainerFeatureID() != 3 && newEModelElement != null)) {
/*     */       
/* 229 */       if (EcoreUtil.isAncestor(this, (EObject)newEModelElement))
/* 230 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 231 */       NotificationChain msgs = null;
/* 232 */       if (eInternalContainer() != null)
/* 233 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 234 */       if (newEModelElement != null)
/* 235 */         msgs = ((InternalEObject)newEModelElement).eInverseAdd(this, 0, EModelElement.class, msgs); 
/* 236 */       msgs = basicSetEModelElement(newEModelElement, msgs);
/* 237 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 239 */     } else if (eNotificationRequired()) {
/* 240 */       eNotify((Notification)new ENotificationImpl(this, 1, 3, newEModelElement, newEModelElement));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EObject> getContents() {
/* 250 */     if (this.contents == null)
/*     */     {
/* 252 */       this.contents = (EList<EObject>)new EObjectContainmentEList(EObject.class, this, 4);
/*     */     }
/* 254 */     return this.contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EObject> getReferences() {
/* 264 */     if (this.references == null)
/*     */     {
/* 266 */       this.references = (EList<EObject>)new EObjectResolvingEList(EObject.class, this, 5);
/*     */     }
/* 268 */     return this.references;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 280 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 283 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */       case 3:
/* 285 */         if (eInternalContainer() != null)
/* 286 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 287 */         return basicSetEModelElement((EModelElement)otherEnd, msgs);
/*     */     } 
/* 289 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 300 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 303 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 2:
/* 305 */         return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
/*     */       case 3:
/* 307 */         return basicSetEModelElement((EModelElement)null, msgs);
/*     */       case 4:
/* 309 */         return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 311 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 322 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 3:
/* 325 */         return eInternalContainer().eInverseRemove(this, 0, EModelElement.class, msgs);
/*     */     } 
/* 327 */     return eDynamicBasicRemoveFromContainer(msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 338 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 341 */         return getEAnnotations();
/*     */       case 1:
/* 343 */         return getSource();
/*     */       case 2:
/* 345 */         if (coreType) return getDetails(); 
/* 346 */         return getDetails().map();
/*     */       case 3:
/* 348 */         return getEModelElement();
/*     */       case 4:
/* 350 */         return getContents();
/*     */       case 5:
/* 352 */         return getReferences();
/*     */     } 
/* 354 */     return eDynamicGet(featureID, resolve, coreType);
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
/*     */   public void eSet(int featureID, Object newValue) {
/* 366 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 369 */         getEAnnotations().clear();
/* 370 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 373 */         setSource((String)newValue);
/*     */         return;
/*     */       case 2:
/* 376 */         ((EStructuralFeature.Setting)getDetails()).set(newValue);
/*     */         return;
/*     */       case 3:
/* 379 */         setEModelElement((EModelElement)newValue);
/*     */         return;
/*     */       case 4:
/* 382 */         getContents().clear();
/* 383 */         getContents().addAll((Collection)newValue);
/*     */         return;
/*     */       case 5:
/* 386 */         getReferences().clear();
/* 387 */         getReferences().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 390 */     eDynamicSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 401 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 404 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 407 */         setSource(SOURCE_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 410 */         getDetails().clear();
/*     */         return;
/*     */       case 3:
/* 413 */         setEModelElement((EModelElement)null);
/*     */         return;
/*     */       case 4:
/* 416 */         getContents().clear();
/*     */         return;
/*     */       case 5:
/* 419 */         getReferences().clear();
/*     */         return;
/*     */     } 
/* 422 */     eDynamicUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 433 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 436 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 438 */         return (SOURCE_EDEFAULT == null) ? ((this.source != null)) : (!SOURCE_EDEFAULT.equals(this.source));
/*     */       case 2:
/* 440 */         return (this.details != null && !this.details.isEmpty());
/*     */       case 3:
/* 442 */         return (getEModelElement() != null);
/*     */       case 4:
/* 444 */         return (this.contents != null && !this.contents.isEmpty());
/*     */       case 5:
/* 446 */         return (this.references != null && !this.references.isEmpty());
/*     */     } 
/* 448 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 459 */     if (eIsProxy()) return super.toString();
/*     */     
/* 461 */     StringBuffer result = new StringBuffer(super.toString());
/* 462 */     result.append(" (source: ");
/* 463 */     result.append(this.source);
/* 464 */     result.append(')');
/* 465 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EAnnotationImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */