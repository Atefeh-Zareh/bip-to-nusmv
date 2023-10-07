/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.eclipse.emf.ecore.EObject;
/*    */ import org.eclipse.emf.ecore.InternalEObject;
/*    */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*    */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SAXXMIHandler
/*    */   extends XMIHandler
/*    */ {
/*    */   public SAXXMIHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
/* 39 */     super(xmiResource, helper, options);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getXSIType() {
/* 45 */     String xsiType = this.isNamespaceAware ? this.attribs.getValue("http://www.w3.org/2001/XMLSchema-instance", "type") : this.attribs.getValue("xsi:type");
/*    */     
/* 47 */     if (xsiType == null)
/*    */     {
/* 49 */       xsiType = this.attribs.getValue("xmi:type");
/*    */     }
/*    */     
/* 52 */     return xsiType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void handleObjectAttribs(EObject obj) {
/* 61 */     if (this.attribs != null) {
/*    */       
/* 63 */       InternalEObject internalEObject = (InternalEObject)obj;
/* 64 */       for (int i = 0, size = this.attribs.getLength(); i < size; i++) {
/*    */         
/* 66 */         String name = this.attribs.getQName(i);
/* 67 */         if (name.equals("xmi:id")) {
/*    */           
/* 69 */           this.xmlResource.setID((EObject)internalEObject, this.attribs.getValue(i));
/*    */         }
/* 71 */         else if (name.equals(this.hrefAttribute) && (!this.recordUnknownFeature || this.types.peek() != "unknownFeature" || obj.eClass() != this.anyType)) {
/*    */           
/* 73 */           handleProxy(internalEObject, this.attribs.getValue(i));
/*    */         }
/* 75 */         else if (this.isNamespaceAware) {
/*    */           
/* 77 */           String namespace = this.attribs.getURI(i);
/* 78 */           if (!"http://www.w3.org/2001/XMLSchema-instance".equals(namespace) && !this.notFeatures.contains(name))
/*    */           {
/* 80 */             setAttribValue(obj, name, this.attribs.getValue(i));
/*    */           }
/*    */         }
/* 83 */         else if (!name.startsWith("xmlns") && !this.notFeatures.contains(name)) {
/*    */           
/* 85 */           setAttribValue(obj, name, this.attribs.getValue(i));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\SAXXMIHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */