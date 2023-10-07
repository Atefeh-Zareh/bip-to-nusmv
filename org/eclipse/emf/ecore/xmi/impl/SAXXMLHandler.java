/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SAXXMLHandler
/*     */   extends XMLHandler
/*     */ {
/*     */   public SAXXMLHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
/*  44 */     super(xmiResource, helper, options);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getXSIType() {
/*  50 */     return this.isNamespaceAware ? this.attribs.getValue("http://www.w3.org/2001/XMLSchema-instance", "type") : this.attribs.getValue("xsi:type");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleObjectAttribs(EObject obj) {
/*  59 */     if (this.attribs != null) {
/*     */       
/*  61 */       InternalEObject internalEObject = (InternalEObject)obj;
/*  62 */       for (int i = 0, size = this.attribs.getLength(); i < size; i++) {
/*     */         
/*  64 */         String name = this.attribs.getQName(i);
/*  65 */         if (name.equals(this.idAttribute)) {
/*     */           
/*  67 */           this.xmlResource.setID((EObject)internalEObject, this.attribs.getValue(i));
/*     */         }
/*  69 */         else if (name.equals(this.hrefAttribute) && (!this.recordUnknownFeature || this.types.peek() != "unknownFeature" || obj.eClass() != this.anyType)) {
/*     */           
/*  71 */           handleProxy(internalEObject, this.attribs.getValue(i));
/*     */         }
/*  73 */         else if (this.isNamespaceAware) {
/*     */           
/*  75 */           String namespace = this.attribs.getURI(i);
/*  76 */           if (!"http://www.w3.org/2001/XMLSchema-instance".equals(namespace))
/*     */           {
/*  78 */             setAttribValue(obj, name, this.attribs.getValue(i));
/*     */           }
/*     */         }
/*  81 */         else if (!name.startsWith("xmlns") && !this.notFeatures.contains(name)) {
/*     */           
/*  83 */           setAttribValue(obj, name, this.attribs.getValue(i));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processObject(EObject object) {
/*  92 */     if (object != null) {
/*     */       
/*  94 */       EStructuralFeature valueFeature = getContentFeature(object);
/*     */       
/*  96 */       if (valueFeature != null) {
/*     */         
/*  98 */         this.text = new StringBuffer();
/*  99 */         this.objects.push(object);
/* 100 */         this.types.push(valueFeature);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 105 */     super.processObject(object);
/*     */   }
/*     */ 
/*     */   
/*     */   protected EStructuralFeature getContentFeature(EObject object) {
/* 110 */     if (this.xmlMap != null) {
/*     */       
/* 112 */       EList<EAttribute> eList = object.eClass().getEAllAttributes();
/* 113 */       if (eList.size() >= 1) {
/*     */         
/* 115 */         EAttribute eAttribute = eList.get(0);
/* 116 */         XMLResource.XMLInfo info = this.xmlMap.getInfo((ENamedElement)eAttribute);
/* 117 */         if (info != null && info.getXMLRepresentation() == 2)
/*     */         {
/* 119 */           return (EStructuralFeature)eAttribute;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\SAXXMLHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */