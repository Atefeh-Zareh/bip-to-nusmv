/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.xmi.XMIResource;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMISaveImpl
/*     */   extends XMLSaveImpl
/*     */ {
/*     */   protected boolean xmiType;
/*  36 */   protected String xmiURI = "http://www.omg.org/XMI";
/*     */   
/*     */   protected static final String XMI_ID_NS = "xmi:id";
/*     */   
/*     */   protected static final String XMI_TAG_NS = "xmi:XMI";
/*     */   protected static final String XMI_TYPE_NS = "xmi:type";
/*     */   protected static final String XMI_VER_NS = "xmi:version";
/*     */   protected static final String XMI_XMLNS = "xmlns:xmi";
/*     */   
/*     */   public XMISaveImpl(XMLHelper helper) {
/*  46 */     super(helper);
/*  47 */     this.idAttributeName = "xmi:id";
/*  48 */     this.idAttributeNS = "xmi";
/*     */   }
/*     */ 
/*     */   
/*     */   public XMISaveImpl(Map<?, ?> options, XMLHelper helper, String encoding) {
/*  53 */     this(options, helper, encoding, "1.0");
/*     */   }
/*     */ 
/*     */   
/*     */   public XMISaveImpl(Map<?, ?> options, XMLHelper helper, String encoding, String xmlVersion) {
/*  58 */     super(options, helper, encoding, xmlVersion);
/*  59 */     this.xmiType = Boolean.TRUE.equals(options.get("USE_XMI_TYPE"));
/*  60 */     this.idAttributeName = "xmi:id";
/*  61 */     this.idAttributeNS = "xmi";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(XMLResource resource, Map<?, ?> options) {
/*  67 */     super.init(resource, options);
/*  68 */     this.xmiType = Boolean.TRUE.equals(options.get("USE_XMI_TYPE"));
/*  69 */     this.xmiURI = (this.xmlResource == null) ? "http://www.omg.org/XMI" : ((XMIResource)this.xmlResource).getXMINamespace();
/*  70 */     this.helper.getPrefixToNamespaceMap().put("xmi", this.xmiURI);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object writeTopObjects(List<? extends EObject> contents) {
/*  76 */     if (!this.toDOM) {
/*     */       
/*  78 */       this.doc.startElement("xmi:XMI");
/*  79 */       Object mark = this.doc.mark();
/*     */       
/*  81 */       for (int j = 0, k = contents.size(); j < k; j++) {
/*     */         
/*  83 */         EObject top = contents.get(j);
/*  84 */         EClass eClass = top.eClass();
/*  85 */         if (this.extendedMetaData == null || this.featureTable.getDocumentRoot(eClass.getEPackage()) != eClass) {
/*     */           
/*  87 */           String name = this.helper.getQName(eClass);
/*  88 */           this.doc.startElement(name);
/*  89 */           this.root = top;
/*  90 */           saveElementID(top);
/*     */         }
/*     */         else {
/*     */           
/*  94 */           this.doc.startElement(null);
/*  95 */           this.root = top;
/*  96 */           saveFeatures(top);
/*  97 */           this.doc.addLine();
/*     */         } 
/*     */       } 
/*     */       
/* 101 */       this.doc.endElement();
/* 102 */       return mark;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 107 */     Element xmiRootElement = this.document.createElementNS("http://www.omg.org/XMI", "xmi:XMI");
/* 108 */     this.currentNode = this.document.appendChild(xmiRootElement);
/* 109 */     for (int i = 0, size = contents.size(); i < size; i++) {
/*     */       
/* 111 */       EObject top = contents.get(i);
/* 112 */       EClass eClass = top.eClass();
/* 113 */       this.helper.populateNameInfo(this.nameInfo, eClass);
/* 114 */       if (this.extendedMetaData == null || this.extendedMetaData.getDocumentRoot(eClass.getEPackage()) != eClass) {
/*     */         
/* 116 */         this.currentNode = xmiRootElement.appendChild(this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName()));
/* 117 */         this.handler.recordValues(this.currentNode, null, null, top);
/* 118 */         this.root = top;
/* 119 */         saveElementID(top);
/*     */       }
/*     */       else {
/*     */         
/* 123 */         this.root = top;
/* 124 */         this.currentNode = xmiRootElement.appendChild(this.document.createElementNS(this.nameInfo.getNamespaceURI(), this.nameInfo.getQualifiedName()));
/* 125 */         saveFeatures(top);
/*     */       } 
/*     */     } 
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveTypeAttribute(EClass eClass) {
/* 135 */     if (this.xmiType) {
/*     */       
/* 137 */       if (!this.toDOM)
/*     */       {
/* 139 */         this.doc.addAttribute("xmi:type", this.helper.getQName(eClass));
/*     */       }
/*     */       else
/*     */       {
/* 143 */         ((Element)this.currentNode).setAttributeNS("http://www.omg.org/XMI", "xmi:type", this.helper.getQName(eClass));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 148 */       super.saveTypeAttribute(eClass);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNamespaceDeclarations() {
/* 155 */     String version = "2.0";
/* 156 */     if (this.xmlResource != null)
/*     */     {
/* 158 */       version = ((XMIResource)this.xmlResource).getXMIVersion();
/*     */     }
/* 160 */     if (!this.toDOM) {
/*     */       
/* 162 */       this.doc.addAttribute("xmi:version", version);
/* 163 */       this.doc.addAttribute("xmlns:xmi", this.xmiURI);
/*     */     }
/*     */     else {
/*     */       
/* 167 */       ((Element)this.currentNode).setAttributeNS("http://www.omg.org/XMI", "xmi:version", version);
/* 168 */       ((Element)this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xmi", this.xmiURI);
/*     */     } 
/* 170 */     super.addNamespaceDeclarations();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDuplicateURI(String nsURI) {
/* 176 */     return this.xmiURI.equals(nsURI);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveFeatureMapElementReference(EObject o, EReference f) {
/* 182 */     if (this.extendedMetaData == null || this.extendedMetaData.getFeatureKind((EStructuralFeature)f) != 4) {
/*     */       
/* 184 */       saveHref(o, (EStructuralFeature)f);
/*     */     }
/*     */     else {
/*     */       
/* 188 */       saveElementReference(o, (EStructuralFeature)f);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMISaveImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */