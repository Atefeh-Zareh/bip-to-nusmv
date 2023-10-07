/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
/*     */ import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
/*     */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
/*     */ import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLOptions;
/*     */ import org.eclipse.emf.ecore.xmi.XMLParserPool;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
/*     */ public class XMLContentHandlerImpl
/*     */   extends ContentHandlerImpl
/*     */ {
/*     */   public static boolean isXMINameAndNamespace(String name, String namespace) {
/*  61 */     return ("XMI".equals(name) && isXMINamespace(namespace));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXMINamespace(String namespace) {
/*  72 */     if (namespace != null && (
/*  73 */       namespace.startsWith("http://schema.omg.org/spec/XMI/") || 
/*  74 */       namespace.equals("http://www.omg.org/XMI"))) return true; 
/*     */     return false;
/*     */   }
/*  77 */   private static final XMLParserPool XML_PARSER_POOL = new XMLParserPoolImpl(1, true);
/*     */   private static final Map<Object, Object> DEFAULT_SAVE_OPTIONS;
/*     */   private static final Map<Object, Object> DEFAULT_LOAD_OPTIONS;
/*     */   
/*     */   static {
/*  82 */     Map<Object, Object> defaultLoadOptions = new HashMap<Object, Object>();
/*  83 */     Map<Object, Object> defaultSaveOptions = new HashMap<Object, Object>();
/*     */     
/*  85 */     defaultLoadOptions.put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/*  86 */     defaultSaveOptions.put("USE_ENCODED_ATTRIBUTE_STYLE", Boolean.TRUE);
/*     */     
/*  88 */     BasicExtendedMetaData basicExtendedMetaData = new BasicExtendedMetaData((EPackage.Registry)new EPackageRegistryImpl());
/*  89 */     defaultLoadOptions.put("EXTENDED_META_DATA", basicExtendedMetaData);
/*  90 */     defaultSaveOptions.put("EXTENDED_META_DATA", basicExtendedMetaData);
/*     */     
/*  92 */     XMLOptions xmlOptions = new XMLOptionsImpl();
/*  93 */     xmlOptions.setProcessAnyXML(true);
/*  94 */     defaultLoadOptions.put("XML_OPTIONS", xmlOptions);
/*     */     
/*  96 */     defaultLoadOptions.put("USE_PARSER_POOL", XML_PARSER_POOL);
/*     */     
/*  98 */     DEFAULT_LOAD_OPTIONS = defaultLoadOptions;
/*  99 */     DEFAULT_SAVE_OPTIONS = defaultSaveOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLResource createXMLResource() {
/* 110 */     return 
/* 111 */       new XMLResourceImpl()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         protected XMLLoad createXMLLoad()
/*     */         {
/* 121 */           return 
/* 122 */             new XMLLoadImpl(createXMLHelper())
/*     */             {
/*     */               
/*     */               public XMLDefaultHandler createDefaultHandler()
/*     */               {
/* 127 */                 return 
/* 128 */                   new SAXXMLHandler(this.resource, this.helper, this.options)
/*     */                   {
/*     */                     
/*     */                     public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
/*     */                     {
/* 133 */                       super.startElement(uri, localName, name, attributes);
/* 134 */                       int depth = this.elements.size();
/* 135 */                       if ((depth == 1 && !XMLContentHandlerImpl.isXMINameAndNamespace(localName, uri)) || (
/* 136 */                         depth == 2 && !XMLContentHandlerImpl.isXMINamespace(uri))) {
/*     */                         
/* 138 */                         endElement(uri, localName, name);
/* 139 */                         if (depth == 2)
/*     */                         {
/* 141 */                           endElement(uri, localName, name);
/*     */                         }
/* 143 */                         endDocument();
/* 144 */                         throw new RuntimeException();
/*     */                       } 
/*     */                     }
/*     */ 
/*     */ 
/*     */                     
/*     */                     protected EPackage handleMissingPackage(String uriString) {
/* 151 */                       EPackage result = super.handleMissingPackage(uriString);
/* 152 */                       return 
/* 153 */                         (result == XMLTypePackage.eINSTANCE) ? 
/* 154 */                         this.extendedMetaData.demandPackage(uriString) : 
/* 155 */                         result;
/*     */                     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     protected void processSchemaLocations(String prefix, String name) {}
/*     */                   };
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getCharset(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 181 */     String result = (String)context.get("org.eclipse.core.runtime:charset");
/* 182 */     if (result == null) {
/*     */       
/* 184 */       result = load(uri, inputStream, options, context).getEncoding();
/* 185 */       context.put("org.eclipse.core.runtime:charset", result);
/*     */     } 
/* 187 */     return result;
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
/*     */ 
/*     */   
/*     */   protected XMLResource load(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 201 */     XMLResource result = (XMLResource)context.get("XMLResource");
/* 202 */     if (result == null) {
/*     */ 
/*     */       
/*     */       try {
/* 206 */         result = createXMLResource();
/* 207 */         result.setURI(uri);
/* 208 */         result.load(
/* 209 */             new BufferedInputStream(inputStream)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void close() throws IOException {}
/* 217 */             }null);
/*     */       }
/* 219 */       catch (Throwable throwable) {
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       finally {
/*     */         
/* 226 */         inputStream.reset();
/*     */       } 
/* 228 */       context.put("XMLResource", result);
/*     */     } 
/* 230 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 239 */     Map<String, Object> result = super.contentDescription(uri, inputStream, options, context);
/* 240 */     if (isRequestedProperty("org.eclipse.core.runtime:charset", options))
/*     */     {
/* 242 */       result.put("org.eclipse.core.runtime:charset", getCharset(uri, inputStream, options, context));
/*     */     }
/* 244 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class XMI
/*     */     extends XMLContentHandlerImpl
/*     */   {
/*     */     public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 255 */       Map<String, Object> result = super.contentDescription(uri, inputStream, options, context);
/*     */       
/* 257 */       XMLResource xmlResource = load(uri, inputStream, options, context);
/* 258 */       EList<EObject> contents = xmlResource.getContents();
/* 259 */       if (!contents.isEmpty()) {
/*     */         
/* 261 */         EObject eObject = (EObject)contents.get(0);
/* 262 */         if (eObject instanceof XMLTypeDocumentRoot) {
/*     */           
/* 264 */           XMLTypeDocumentRoot documentRoot = (XMLTypeDocumentRoot)eObject;
/* 265 */           EList<EObject> rootContents = documentRoot.eContents();
/* 266 */           if (!rootContents.isEmpty()) {
/*     */             
/* 268 */             EObject root = (EObject)rootContents.get(0);
/* 269 */             if (root instanceof AnyType)
/*     */             {
/* 271 */               for (FeatureMap.Entry entry : ((AnyType)root).getAnyAttribute()) {
/*     */                 
/* 273 */                 EStructuralFeature attributeFeature = entry.getEStructuralFeature();
/* 274 */                 if ("version".equals(ExtendedMetaData.INSTANCE.getName(attributeFeature)) && 
/* 275 */                   isXMINamespace(ExtendedMetaData.INSTANCE.getNamespace(attributeFeature))) {
/*     */                   
/* 277 */                   result.put("org.eclipse.emf.ecore:validity", ContentHandler.Validity.VALID);
/* 278 */                   result.put("org.eclipse.emf.ecore:contentType", "org.eclipse.emf.ecore.xmi");
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 286 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class Describer
/*     */       extends ContentHandlerImpl.Describer
/*     */     {
/*     */       protected ContentHandler createContentHandler(Map<String, String> parameters) {
/* 300 */         return (ContentHandler)new XMLContentHandlerImpl.XMI();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLContentHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */