/*     */ package org.eclipse.emf.mapping.ecore2xml.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
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
/*     */ public class XMLInfoImpl
/*     */   extends EObjectImpl
/*     */   implements XMLInfo
/*     */ {
/*  53 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected static final String TARGET_NAMESPACE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int XML_REPRESENTATION_EDEFAULT = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLResource.XMLInfo delegateXMLInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLInfoImpl()
/*     */   {
/* 253 */     this.delegateXMLInfo = null; this.delegateXMLInfo = (XMLResource.XMLInfo)new org.eclipse.emf.ecore.xmi.impl.XMLInfoImpl(); } protected XMLInfoImpl(XMLResource.XMLInfo delegateXMLInfo) { this.delegateXMLInfo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     this.delegateXMLInfo = delegateXMLInfo; }
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/*     */     return Ecore2XMLPackage.Literals.XML_INFO;
/*     */   }
/*     */   
/*     */   public String getName() {
/*     */     return this.delegateXMLInfo.getName();
/*     */   }
/*     */   
/*     */   public void setName(String newName) {
/*     */     String oldName = this.delegateXMLInfo.getName();
/*     */     this.delegateXMLInfo.setName(newName);
/*     */     if (eNotificationRequired())
/*     */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldName, newName)); 
/*     */   }
/*     */   
/*     */   public String getTargetNamespace() {
/*     */     return this.delegateXMLInfo.getTargetNamespace();
/*     */   }
/*     */   
/*     */   public void setTargetNamespace(String newTargetNamespace) {
/*     */     String oldTargetNamespace = this.delegateXMLInfo.getTargetNamespace();
/*     */     this.delegateXMLInfo.setTargetNamespace(newTargetNamespace);
/*     */     if (eNotificationRequired())
/*     */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldTargetNamespace, newTargetNamespace)); 
/*     */   }
/*     */   
/*     */   public int getXMLRepresentation() {
/*     */     return this.delegateXMLInfo.getXMLRepresentation();
/*     */   }
/*     */   
/*     */   public void setXMLRepresentation(int newXMLRepresentation) {
/*     */     int oldXMLRepresentation = this.delegateXMLInfo.getXMLRepresentation();
/*     */     this.delegateXMLInfo.setXMLRepresentation(newXMLRepresentation);
/*     */     if (eNotificationRequired())
/*     */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldXMLRepresentation, newXMLRepresentation)); 
/*     */   }
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/*     */     switch (featureID) {
/*     */       case 0:
/*     */         return getName();
/*     */       case 1:
/*     */         return getTargetNamespace();
/*     */       case 2:
/*     */         return Integer.valueOf(getXMLRepresentation());
/*     */     } 
/*     */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/*     */     switch (featureID) {
/*     */       case 0:
/*     */         setName((String)newValue);
/*     */         return;
/*     */       case 1:
/*     */         setTargetNamespace((String)newValue);
/*     */         return;
/*     */       case 2:
/*     */         setXMLRepresentation(((Integer)newValue).intValue());
/*     */         return;
/*     */     } 
/*     */     super.eSet(featureID, newValue);
/*     */   }
/*     */   
/*     */   public void eUnset(int featureID) {
/*     */     switch (featureID) {
/*     */       case 0:
/*     */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 1:
/*     */         setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
/*     */         return;
/*     */       case 2:
/*     */         setXMLRepresentation(-1);
/*     */         return;
/*     */     } 
/*     */     super.eUnset(featureID);
/*     */   }
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/*     */     switch (featureID) {
/*     */       case 0:
/*     */         return (NAME_EDEFAULT == null) ? ((getName() != null)) : (!NAME_EDEFAULT.equals(getName()));
/*     */       case 1:
/*     */         return (TARGET_NAMESPACE_EDEFAULT == null) ? ((getTargetNamespace() != null)) : (!TARGET_NAMESPACE_EDEFAULT.equals(getTargetNamespace()));
/*     */       case 2:
/*     */         return (getXMLRepresentation() != -1);
/*     */     } 
/*     */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\impl\XMLInfoImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */