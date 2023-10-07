/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.URIConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceFactoryRegistryImpl
/*     */   implements Resource.Factory.Registry
/*     */ {
/*  41 */   protected Map<String, Object> protocolToFactoryMap = new HashMap<String, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected Map<String, Object> extensionToFactoryMap = new HashMap<String, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Map<String, Object> contentTypeIdentifierToFactoryMap = new HashMap<String, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final Map<?, ?> CONTENT_DESCRIPTION_OPTIONS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource.Factory getFactory(URI uri) {
/*  75 */     return convert(getFactory(uri, this.protocolToFactoryMap, this.extensionToFactoryMap, this.contentTypeIdentifierToFactoryMap, "", true));
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
/*     */   public Resource.Factory getFactory(URI uri, String contentType) {
/*  92 */     return convert(getFactory(uri, this.protocolToFactoryMap, this.extensionToFactoryMap, this.contentTypeIdentifierToFactoryMap, contentType, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Resource.Factory convert(Object resourceFactory) {
/*  97 */     return 
/*  98 */       (resourceFactory instanceof Resource.Factory.Descriptor) ? (
/*  99 */       (Resource.Factory.Descriptor)resourceFactory).createFactory() : 
/* 100 */       (Resource.Factory)resourceFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getFactory(URI uri, Map<String, Object> protocolToFactoryMap, Map<String, Object> extensionToFactoryMap, Map<String, Object> contentTypeIdentifierToFactoryMap, String contentTypeIdentifier, boolean delegate) {
/* 111 */     Object resourceFactory = null;
/* 112 */     if (!protocolToFactoryMap.isEmpty())
/*     */     {
/* 114 */       resourceFactory = protocolToFactoryMap.get(uri.scheme());
/*     */     }
/* 116 */     if (resourceFactory == null) {
/*     */       
/* 118 */       boolean extensionToFactoryMapIsEmpty = extensionToFactoryMap.isEmpty();
/* 119 */       if (!extensionToFactoryMapIsEmpty)
/*     */       {
/* 121 */         resourceFactory = extensionToFactoryMap.get(uri.fileExtension());
/*     */       }
/* 123 */       if (resourceFactory == null) {
/*     */         
/* 125 */         boolean contentTypeIdentifierToFactoryMapIsEmpty = contentTypeIdentifierToFactoryMap.isEmpty();
/* 126 */         if (!contentTypeIdentifierToFactoryMapIsEmpty) {
/*     */           
/* 128 */           if ("".equals(contentTypeIdentifier))
/*     */           {
/* 130 */             contentTypeIdentifier = getContentTypeIdentifier(uri);
/*     */           }
/* 132 */           if (contentTypeIdentifier != null)
/*     */           {
/* 134 */             resourceFactory = contentTypeIdentifierToFactoryMap.get(contentTypeIdentifier);
/*     */           }
/*     */         } 
/* 137 */         if (resourceFactory == null) {
/*     */           
/* 139 */           if (!extensionToFactoryMapIsEmpty)
/*     */           {
/* 141 */             resourceFactory = extensionToFactoryMap.get("*");
/*     */           }
/* 143 */           if (resourceFactory == null) {
/*     */             
/* 145 */             if (!contentTypeIdentifierToFactoryMapIsEmpty)
/*     */             {
/* 147 */               resourceFactory = contentTypeIdentifierToFactoryMap.get("*");
/*     */             }
/* 149 */             if (resourceFactory == null && delegate)
/*     */             {
/* 151 */               resourceFactory = delegatedGetFactory(uri, contentTypeIdentifier);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     return resourceFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getContentTypeIdentifier(URI uri) {
/*     */     try {
/* 164 */       Map<String, ?> contentDescription = getURIConverter().contentDescription(uri, getContentDescriptionOptions());
/* 165 */       return (String)contentDescription.get("org.eclipse.emf.ecore:contentType");
/*     */     }
/* 167 */     catch (IOException e) {
/*     */       
/* 169 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URIConverter getURIConverter() {
/* 179 */     return URIConverter.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 188 */     Map<Object, Object> contentDescriptionOptions = new HashMap<Object, Object>();
/* 189 */     Set<String> requestedProperties = new HashSet<String>();
/* 190 */     requestedProperties.add("org.eclipse.emf.ecore:contentType");
/* 191 */     contentDescriptionOptions.put("REQUESTED_PROPERTIES", requestedProperties);
/* 192 */     CONTENT_DESCRIPTION_OPTIONS = Collections.unmodifiableMap(contentDescriptionOptions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<?, ?> getContentDescriptionOptions() {
/* 201 */     return CONTENT_DESCRIPTION_OPTIONS;
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
/*     */   protected Resource.Factory delegatedGetFactory(URI uri, String contentTypeIdentifier) {
/* 217 */     return delegatedGetFactory(uri);
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
/*     */   @Deprecated
/*     */   protected Resource.Factory delegatedGetFactory(URI uri) {
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getExtensionToFactoryMap() {
/* 242 */     return this.extensionToFactoryMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getProtocolToFactoryMap() {
/* 250 */     return this.protocolToFactoryMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getContentTypeToFactoryMap() {
/* 258 */     return this.contentTypeIdentifierToFactoryMap;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ResourceFactoryRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */