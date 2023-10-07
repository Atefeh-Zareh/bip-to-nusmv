/*     */ package org.eclipse.emf.mapping.ecore2xml.impl;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreEMap;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLMapImpl
/*     */   extends EObjectImpl
/*     */   implements XMLMap
/*     */ {
/*  70 */   protected static final String ID_ATTRIBUTE_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EMap<ENamedElement, XMLInfo> ecoreToXMLInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 100 */     return Ecore2XMLPackage.Literals.XML_MAP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIDAttributeName() {
/* 110 */     return this.delegateXMLMap.getIDAttributeName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIDAttributeName(String newIDAttributeName) {
/* 120 */     String oldIDAttributeName = this.delegateXMLMap.getIDAttributeName();
/* 121 */     this.delegateXMLMap.setIDAttributeName(newIDAttributeName);
/* 122 */     if (eNotificationRequired()) {
/* 123 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldIDAttributeName, newIDAttributeName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<ENamedElement, XMLInfo> getEcoreToXMLInfo() {
/* 133 */     if (this.ecoreToXMLInfo == null)
/*     */     {
/* 135 */       this.ecoreToXMLInfo = (EMap<ENamedElement, XMLInfo>)new EcoreEMap(Ecore2XMLPackage.Literals.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY, ENamedElementToXMLInfoMapEntryImpl.class, (InternalEObject)this, 1);
/*     */     }
/* 137 */     return this.ecoreToXMLInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage getNoNamespacePackage() {
/* 147 */     EPackage noNamespacePackage = basicGetNoNamespacePackage();
/* 148 */     return (noNamespacePackage != null && noNamespacePackage.eIsProxy()) ? (EPackage)eResolveProxy((InternalEObject)noNamespacePackage) : noNamespacePackage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage basicGetNoNamespacePackage() {
/* 158 */     return this.delegateXMLMap.getNoNamespacePackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoNamespacePackage(EPackage newNoNamespacePackage) {
/* 168 */     EPackage oldNoNamespacePackage = this.delegateXMLMap.getNoNamespacePackage();
/* 169 */     this.delegateXMLMap.setNoNamespacePackage(newNoNamespacePackage);
/* 170 */     if (eNotificationRequired()) {
/* 171 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldNoNamespacePackage, newNoNamespacePackage));
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
/* 182 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 185 */         return ((InternalEList)getEcoreToXMLInfo()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 187 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 198 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 201 */         return getIDAttributeName();
/*     */       case 1:
/* 203 */         if (coreType) return getEcoreToXMLInfo(); 
/* 204 */         return getEcoreToXMLInfo().map();
/*     */       case 2:
/* 206 */         if (resolve) return getNoNamespacePackage(); 
/* 207 */         return basicGetNoNamespacePackage();
/*     */     } 
/* 209 */     return super.eGet(featureID, resolve, coreType);
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
/* 220 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 223 */         setIDAttributeName((String)newValue);
/*     */         return;
/*     */       case 1:
/* 226 */         ((EStructuralFeature.Setting)getEcoreToXMLInfo()).set(newValue);
/*     */         return;
/*     */       case 2:
/* 229 */         setNoNamespacePackage((EPackage)newValue);
/*     */         return;
/*     */     } 
/* 232 */     super.eSet(featureID, newValue);
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
/* 243 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 246 */         setIDAttributeName(ID_ATTRIBUTE_NAME_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 249 */         getEcoreToXMLInfo().clear();
/*     */         return;
/*     */       case 2:
/* 252 */         setNoNamespacePackage((EPackage)null);
/*     */         return;
/*     */     } 
/* 255 */     super.eUnset(featureID);
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
/* 266 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 269 */         return (ID_ATTRIBUTE_NAME_EDEFAULT == null) ? ((getIDAttributeName() != null)) : (!ID_ATTRIBUTE_NAME_EDEFAULT.equals(getIDAttributeName()));
/*     */       case 1:
/* 271 */         return (this.ecoreToXMLInfo != null && !this.ecoreToXMLInfo.isEmpty());
/*     */       case 2:
/* 273 */         return (basicGetNoNamespacePackage() != null);
/*     */     } 
/* 275 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DelegateXMLMapImpl
/*     */     extends org.eclipse.emf.ecore.xmi.impl.XMLMapImpl {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   protected XMLResource.XMLMap delegateXMLMap = (XMLResource.XMLMap)new DelegateXMLMapImpl();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(ENamedElement element, XMLResource.XMLInfo info) {
/* 295 */     this.delegateXMLMap.add(element, XMLInfo.class.isInstance(info) ? info : (XMLResource.XMLInfo)new XMLInfoImpl(info));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier getClassifier(String namespaceURI, String name) {
/* 303 */     return this.delegateXMLMap.getClassifier(namespaceURI, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name) {
/* 311 */     return this.delegateXMLMap.getFeature(eClass, namespaceURI, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<EStructuralFeature> getFeatures(EClass eClass) {
/* 319 */     return this.delegateXMLMap.getFeatures(eClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLResource.XMLInfo getInfo(ENamedElement element) {
/* 327 */     return this.delegateXMLMap.getInfo(element);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\impl\XMLMapImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */