/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.xmi.XMIResource;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class XMIHandler
/*     */   extends XMLHandler
/*     */ {
/*     */   protected static final String XMI_ELEMENT_TYPE = "xmi";
/*     */   protected static final String XMI_UUID = "uuid";
/*     */   protected static final String XMI_EXTENSION = "Extension";
/*     */   protected static final String XMI_TYPE_ATTRIB = "xmi:type";
/*     */   protected static final String ID_ATTRIB = "xmi:id";
/*     */   protected static final String VERSION_ATTRIB = "xmi:version";
/*     */   protected static final String UUID_ATTRIB = "xmi:uuid";
/*     */   protected static final String XMI_ELEMENT_NAME = "xmi:XMI";
/*     */   
/*     */   public XMIHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
/*  55 */     super(xmiResource, helper, options);
/*     */     
/*  57 */     this.hrefAttribute = "href";
/*  58 */     this.notFeatures.add("xmi:version");
/*  59 */     this.notFeatures.add("xmi:type");
/*  60 */     this.notFeatures.add("xmi:uuid");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processElement(String name, String prefix, String localName) {
/*  66 */     if (localName.equals("XMI")) {
/*     */       
/*  68 */       this.types.push("xmi");
/*  69 */       String namespace = this.helper.getURI("xmi");
/*  70 */       if (namespace != null && namespace.startsWith("http://schema.omg.org/spec/XMI/"))
/*     */       {
/*  72 */         ((XMIResource)this.xmlResource).setXMIVersion(namespace.substring("http://schema.omg.org/spec/XMI/".length()));
/*     */       }
/*  74 */       this.isRoot = false;
/*     */     }
/*  76 */     else if (this.isRoot) {
/*     */       
/*  78 */       String namespace = this.helper.getURI("xmi");
/*  79 */       if (namespace != null && namespace.startsWith("http://schema.omg.org/spec/XMI/"))
/*     */       {
/*  81 */         ((XMIResource)this.xmlResource).setXMIVersion(namespace.substring("http://schema.omg.org/spec/XMI/".length()));
/*     */       }
/*  83 */       super.processElement(name, prefix, localName);
/*     */     }
/*     */     else {
/*     */       
/*  87 */       super.processElement(name, prefix, localName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isTextFeatureValue(Object type) {
/*  94 */     return (super.isTextFeatureValue(type) && type != "xmi");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EObject createDocumentRoot(String prefix, String uri, String name, EFactory eFactory, boolean top) {
/* 100 */     if (this.extendedMetaData != null && eFactory != null && this.extendedMetaData.demandedPackages().contains(eFactory.getEPackage())) {
/*     */       
/* 102 */       EClass eClass = (EClass)this.extendedMetaData.demandType(uri, name);
/* 103 */       EObject newObject = this.useNewMethods ? createObject(eFactory, (EClassifier)eClass, true) : this.helper.createObject(eFactory, name);
/* 104 */       validateCreateObjectFromFactory(eFactory, name, newObject);
/* 105 */       handleObjectAttribs(newObject);
/* 106 */       if (top)
/*     */       {
/* 108 */         processTopObject(newObject);
/*     */       }
/* 110 */       return newObject;
/*     */     } 
/*     */ 
/*     */     
/* 114 */     return super.createDocumentRoot(prefix, uri, name, eFactory, top);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createObject(EObject peekObject, EStructuralFeature feature) {
/* 121 */     String id = this.attribs.getValue("xmi:idref");
/* 122 */     if (id != null) {
/*     */       
/* 124 */       EReference eReference = (EReference)feature;
/* 125 */       if (!eReference.isContainment()) {
/*     */         
/* 127 */         setValueFromId(peekObject, eReference, id);
/* 128 */         this.objects.push(null);
/* 129 */         this.mixedTargets.push(null);
/* 130 */         this.types.push("object");
/*     */         return;
/*     */       } 
/*     */     } 
/* 134 */     super.createObject(peekObject, feature);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value) {
/* 140 */     if ("Extension".equals(name) && "http://www.omg.org/XMI".equals(this.helper.getURI(prefix))) {
/*     */       
/* 142 */       if (this.extendedMetaData == null)
/*     */       {
/* 144 */         setExtendedMetaDataOption(Boolean.TRUE);
/*     */       }
/*     */       
/* 147 */       recordUnknownFeature(prefix, name, isElement, peekObject, value);
/*     */     }
/*     */     else {
/*     */       
/* 151 */       super.handleUnknownFeature(prefix, name, isElement, peekObject, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
/* 158 */     if (this.documentRoot != null) {
/*     */       
/* 160 */       EObject eObject = this.objects.peekEObject();
/* 161 */       if (eObject == this.documentRoot && (this.extendedMetaData == null || this.extendedMetaData.isDocumentRoot(eObject.eClass()))) {
/*     */         
/* 163 */         this.types.pop();
/* 164 */         this.objects.pop();
/* 165 */         this.mixedTargets.pop();
/* 166 */         this.documentRoot = null;
/*     */       } 
/*     */     } 
/* 169 */     super.startElement(uri, localName, name, attributes);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMIHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */