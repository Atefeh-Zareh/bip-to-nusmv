/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
/*     */ import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RootXMLContentHandlerImpl
/*     */   extends XMLContentHandlerImpl
/*     */ {
/*     */   protected String contentTypeID;
/*     */   protected String[] extensions;
/*     */   protected String kind;
/*     */   protected String namespace;
/*     */   protected Pattern namespacePattern;
/*     */   protected String[] elementNames;
/*     */   public static final String CONTENT_TYPE_ID = "contentTypeID";
/*     */   public static final String EXTENSIONS = "extensions";
/*     */   public static final String KIND = "kind";
/*     */   public static final String XMI_KIND = "xmi";
/*     */   public static final String NAMESPACE = "namespace";
/*     */   public static final String NAMESPACE_PATTERN = "namespacePattern";
/*     */   public static final String ELEMENT_NAMES = "elementNames";
/*     */   
/*     */   public RootXMLContentHandlerImpl(Map<String, String> parameters) {
/* 131 */     this(parameters.get("contentTypeID"), parameters.containsKey("extensions") ? ((String)parameters.get("extensions")).split(" ") : new String[0], parameters.get("kind"), parameters.get("namespace"), (parameters.get("namespacePattern") != null) ? Pattern.compile(parameters.get("namespacePattern")) : null, parameters.containsKey("elementNames") ? ((String)parameters.get("elementNames")).split(" ") : new String[0]);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RootXMLContentHandlerImpl(String contentTypeID, String[] extensions, String kind, String namespace, String[] elementNames) {
/* 151 */     this(contentTypeID, extensions, kind, namespace, (Pattern)null, elementNames);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RootXMLContentHandlerImpl(String contentTypeID, String[] extensions, String kind, Pattern namespacePattern, String[] elementNames) {
/* 171 */     this(contentTypeID, extensions, kind, (String)null, namespacePattern, elementNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RootXMLContentHandlerImpl(String contentTypeID, String[] extensions, String kind, String namespace, Pattern namespacePattern, String[] elementNames) {
/* 182 */     assert namespacePattern == null || namespace == null;
/*     */     
/* 184 */     this.contentTypeID = contentTypeID;
/* 185 */     this.extensions = extensions;
/* 186 */     this.kind = kind;
/* 187 */     this.namespace = namespace;
/* 188 */     this.namespacePattern = namespacePattern;
/* 189 */     this.elementNames = elementNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHandle(URI uri) {
/* 198 */     if (this.extensions == null || this.extensions.length == 0)
/*     */     {
/* 200 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 204 */     String fileExtension = uri.fileExtension();
/* 205 */     if (fileExtension != null) {
/*     */       byte b; int i; String[] arrayOfString;
/* 207 */       for (i = (arrayOfString = this.extensions).length, b = 0; b < i; ) { String extension = arrayOfString[b];
/*     */         
/* 209 */         if (fileExtension.equals(extension))
/*     */         {
/* 211 */           return true; } 
/*     */         b++; }
/*     */     
/*     */     } 
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 225 */     Map<String, Object> result = super.contentDescription(uri, inputStream, options, context);
/*     */     
/* 227 */     XMLResource xmlResource = load(uri, inputStream, options, context);
/* 228 */     EList<EObject> contents = xmlResource.getContents();
/* 229 */     if (!contents.isEmpty()) {
/*     */       
/* 231 */       EObject eObject = (EObject)contents.get(0);
/* 232 */       if (eObject instanceof XMLTypeDocumentRoot) {
/*     */         
/* 234 */         XMLTypeDocumentRoot documentRoot = (XMLTypeDocumentRoot)eObject;
/* 235 */         EList<EObject> rootContents = documentRoot.eContents();
/* 236 */         String rootElementName = null;
/* 237 */         String rootElementNamespace = null;
/* 238 */         if (!rootContents.isEmpty()) {
/*     */           
/* 240 */           EObject root = (EObject)rootContents.get(0);
/* 241 */           EReference eContainmentFeature = root.eContainmentFeature();
/* 242 */           rootElementName = eContainmentFeature.getName();
/* 243 */           rootElementNamespace = ExtendedMetaData.INSTANCE.getNamespace((EStructuralFeature)eContainmentFeature);
/* 244 */           if ("xmi".equals(this.kind) && isXMINameAndNamespace(rootElementName, rootElementNamespace))
/*     */           {
/*     */ 
/*     */             
/* 248 */             for (EObject candidate : root.eContents()) {
/*     */               
/* 250 */               eContainmentFeature = candidate.eContainmentFeature();
/* 251 */               rootElementNamespace = ExtendedMetaData.INSTANCE.getNamespace((EStructuralFeature)eContainmentFeature);
/* 252 */               if (!isXMINamespace(rootElementNamespace)) {
/*     */                 
/* 254 */                 rootElementName = eContainmentFeature.getName();
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/* 261 */         if (rootElementName != null && isMatchingNamespace(rootElementNamespace)) {
/*     */           
/* 263 */           boolean elementNameMatched = false;
/* 264 */           if (this.elementNames == null || this.elementNames.length == 0) {
/*     */             
/* 266 */             elementNameMatched = true;
/*     */           } else {
/*     */             byte b; int i;
/*     */             String[] arrayOfString;
/* 270 */             for (i = (arrayOfString = this.elementNames).length, b = 0; b < i; ) { String elementName = arrayOfString[b];
/*     */               
/* 272 */               if (rootElementName.equals(elementName)) {
/*     */                 
/* 274 */                 elementNameMatched = true; break;
/*     */               } 
/*     */               b++; }
/*     */           
/*     */           } 
/* 279 */           if (elementNameMatched)
/*     */           {
/* 281 */             result.put("org.eclipse.emf.ecore:validity", ContentHandler.Validity.VALID);
/*     */           }
/*     */         } 
/*     */         
/* 285 */         result.put("org.eclipse.emf.ecore:contentType", (this.contentTypeID == null) ? ((rootElementNamespace == null) ? "" : rootElementNamespace) : this.contentTypeID);
/* 286 */         return result;
/*     */       } 
/*     */     } 
/* 289 */     result.put("org.eclipse.emf.ecore:contentType", (this.contentTypeID == null) ? "" : this.contentTypeID);
/* 290 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isMatchingNamespace(String rootElementNamespace) {
/* 298 */     if (this.namespacePattern != null)
/*     */     {
/* 300 */       return this.namespacePattern.matcher((rootElementNamespace == null) ? "" : rootElementNamespace).matches();
/*     */     }
/*     */ 
/*     */     
/* 304 */     return (this.namespace == null) ? ((rootElementNamespace == null)) : this.namespace.equals(rootElementNamespace);
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
/*     */   public static class Describer
/*     */     extends ContentHandlerImpl.Describer
/*     */   {
/*     */     protected ContentHandler createContentHandler(Map<String, String> parameters) {
/* 319 */       return (ContentHandler)new RootXMLContentHandlerImpl(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\RootXMLContentHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */