/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EObject;
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
/*     */ public class EMOFHandler
/*     */   extends SAXXMIHandler
/*     */ {
/*     */   protected Helper emofHelper;
/*     */   protected static final String ECORE_EXTENSION_TYPE = "ecoreExtension";
/*     */   
/*     */   public EMOFHandler(XMLResource xmiResource, Helper helper, Map<?, ?> options) {
/*  37 */     super(xmiResource, helper, options);
/*  38 */     this.emofHelper = helper;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleProxy(InternalEObject proxy, String uriLiteral) {
/*  44 */     if (uriLiteral.startsWith("http://schema.omg.org/spec/mof/2.0/emof.xmi#") || uriLiteral.startsWith("http://schema.omg.org/spec/MOF/2.0/emof.xml#")) {
/*     */       
/*  46 */       String dataType = uriLiteral.substring(uriLiteral.indexOf("#") + 1);
/*  47 */       for (int i = 0; i < EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPES.length; i++) {
/*     */         
/*  49 */         if (dataType.equals(EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPES[i])) {
/*     */           
/*  51 */           uriLiteral = "http://www.eclipse.org/emf/2002/Ecore#//" + EMOFExtendedMetaData.MAPPED_ECORE_EDATATYPES[i];
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  56 */     } else if (uriLiteral.startsWith("http://www.eclipse.org/emf/2002/Ecore.emof#ecore.")) {
/*     */       
/*  58 */       String dataType = uriLiteral.substring("http://www.eclipse.org/emf/2002/Ecore.emof#ecore.".length());
/*  59 */       uriLiteral = "http://www.eclipse.org/emf/2002/Ecore#//" + dataType;
/*     */     } 
/*     */     
/*  62 */     super.handleProxy(proxy, uriLiteral);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleForwardReferences(boolean isEndDocument) {
/*  68 */     super.handleForwardReferences(isEndDocument);
/*     */     
/*  70 */     if (isEndDocument)
/*     */     {
/*  72 */       this.emofHelper.convertPropertyFeatures();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processElement(String name, String prefix, String localName) {
/*  79 */     if ("Extension".equals(localName) && "http://www.omg.org/XMI".equals(this.helper.getURI(prefix))) {
/*     */       
/*  81 */       if (this.attribs != null && "http://www.eclipse.org/emf/2002/Ecore".equals(this.attribs.getValue("extender")))
/*     */       {
/*  83 */         this.types.push("ecoreExtension");
/*     */       }
/*     */       else
/*     */       {
/*  87 */         this.types.push("error");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  92 */       super.processElement(name, prefix, localName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String localName, String name) {
/*  99 */     if (this.types.peek() == "ecoreExtension") {
/*     */       
/* 101 */       this.elements.pop();
/* 102 */       this.types.pop();
/* 103 */       this.helper.popContext();
/* 104 */       this.mixedTargets.pop();
/*     */     }
/*     */     else {
/*     */       
/* 108 */       super.endElement(uri, localName, name);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setAttribValue(EObject object, String name, String value) {
/* 115 */     if (object instanceof EAnnotation) {
/*     */       
/* 117 */       EAnnotation annotation = (EAnnotation)object;
/* 118 */       String source = annotation.getSource();
/* 119 */       if ("http://schema.omg.org/spec/mof/2.0/emof.xmi".equals(source) || "http://schema.omg.org/spec/MOF/2.0/emof.xml".equals(source)) {
/*     */         
/* 121 */         if ("name".equals(name) || "value".equals(name)) {
/*     */           
/* 123 */           annotation.getDetails().put(name, value);
/*     */           return;
/*     */         } 
/* 126 */         if ("body".equals(name)) {
/*     */           
/* 128 */           annotation.setSource("http://schema.omg.org/spec/MOF/2.0/emof.xml#Comment");
/* 129 */           annotation.getDetails().put(name, value);
/*     */           
/*     */           return;
/*     */         } 
/* 133 */       } else if (source.startsWith("http://schema.omg.org/spec/MOF/2.0/emof.xml") && "body".equals(name)) {
/*     */         
/* 135 */         annotation.getDetails().put(name, value);
/*     */         return;
/*     */       } 
/*     */     } 
/* 139 */     super.setAttribValue(object, name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleFeature(String prefix, String name) {
/* 145 */     super.handleFeature(prefix, name);
/*     */ 
/*     */ 
/*     */     
/* 149 */     if ("ownedComment".equals(name) && this.objects.peekEObject() instanceof EAnnotation) {
/*     */       
/* 151 */       EAnnotation annotation = (EAnnotation)this.objects.peekEObject();
/* 152 */       EObject container = annotation.eContainer();
/* 153 */       if (container instanceof EAnnotation) {
/*     */         
/* 155 */         EAnnotation parentAnnotation = (EAnnotation)container;
/* 156 */         parentAnnotation.setSource((String)annotation.getDetails().get("body"));
/* 157 */         parentAnnotation.getEAnnotations().remove(annotation);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Helper extends XMLHelper {
/*     */     void convertPropertyFeatures();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */