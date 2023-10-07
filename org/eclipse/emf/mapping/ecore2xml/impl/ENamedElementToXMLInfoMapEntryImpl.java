/*     */ package org.eclipse.emf.mapping.ecore2xml.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ENamedElementToXMLInfoMapEntryImpl
/*     */   extends EObjectImpl
/*     */   implements BasicEMap.Entry<ENamedElement, XMLInfo>
/*     */ {
/*     */   protected ENamedElement key;
/*     */   protected XMLInfo value;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  90 */     return Ecore2XMLPackage.Literals.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ENamedElement getTypedKey() {
/* 100 */     if (this.key != null && this.key.eIsProxy()) {
/*     */       
/* 102 */       InternalEObject oldKey = (InternalEObject)this.key;
/* 103 */       this.key = (ENamedElement)eResolveProxy(oldKey);
/* 104 */       if (this.key != oldKey)
/*     */       {
/* 106 */         if (eNotificationRequired())
/* 107 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldKey, this.key)); 
/*     */       }
/*     */     } 
/* 110 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ENamedElement basicGetTypedKey() {
/* 120 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypedKey(ENamedElement newKey) {
/* 130 */     ENamedElement oldKey = this.key;
/* 131 */     this.key = newKey;
/* 132 */     if (eNotificationRequired()) {
/* 133 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldKey, this.key));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLInfo getTypedValue() {
/* 143 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTypedValue(XMLInfo newValue, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 153 */     XMLInfo oldValue = this.value;
/* 154 */     this.value = newValue;
/* 155 */     if (eNotificationRequired()) {
/*     */       
/* 157 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldValue, newValue);
/* 158 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 160 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypedValue(XMLInfo newValue) {
/* 170 */     if (newValue != this.value) {
/*     */       
/* 172 */       NotificationChain msgs = null;
/* 173 */       if (this.value != null)
/* 174 */         msgs = ((InternalEObject)this.value).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 175 */       if (newValue != null)
/* 176 */         msgs = ((InternalEObject)newValue).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 177 */       msgs = basicSetTypedValue(newValue, msgs);
/* 178 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 180 */     } else if (eNotificationRequired()) {
/* 181 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newValue, newValue));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 192 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 195 */         return basicSetTypedValue((XMLInfo)null, msgs);
/*     */     } 
/* 197 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 208 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 211 */         if (resolve) return getTypedKey(); 
/* 212 */         return basicGetTypedKey();
/*     */       case 1:
/* 214 */         return getTypedValue();
/*     */     } 
/* 216 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 227 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 230 */         setTypedKey((ENamedElement)newValue);
/*     */         return;
/*     */       case 1:
/* 233 */         setTypedValue((XMLInfo)newValue);
/*     */         return;
/*     */     } 
/* 236 */     super.eSet(featureID, newValue);
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
/* 247 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 250 */         setTypedKey((ENamedElement)null);
/*     */         return;
/*     */       case 1:
/* 253 */         setTypedValue((XMLInfo)null);
/*     */         return;
/*     */     } 
/* 256 */     super.eUnset(featureID);
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
/* 267 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 270 */         return (this.key != null);
/*     */       case 1:
/* 272 */         return (this.value != null);
/*     */     } 
/* 274 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   protected int hash = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHash() {
/* 291 */     if (this.hash == -1) {
/*     */       
/* 293 */       Object theKey = getKey();
/* 294 */       this.hash = (theKey == null) ? 0 : theKey.hashCode();
/*     */     } 
/* 296 */     return this.hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHash(int hash) {
/* 306 */     this.hash = hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ENamedElement getKey() {
/* 316 */     return getTypedKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKey(ENamedElement key) {
/* 326 */     setTypedKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLInfo getValue() {
/* 336 */     return getTypedValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLInfo setValue(XMLInfo value) {
/* 346 */     XMLInfo oldValue = getValue();
/* 347 */     setTypedValue(value);
/* 348 */     return oldValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<ENamedElement, XMLInfo> getEMap() {
/* 359 */     EObject container = eContainer();
/* 360 */     return (container == null) ? null : (EMap<ENamedElement, XMLInfo>)container.eGet((EStructuralFeature)eContainmentFeature());
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\impl\ENamedElementToXMLInfoMapEntryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */