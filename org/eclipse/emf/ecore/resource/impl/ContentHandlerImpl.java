/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.core.runtime.IConfigurationElement;
/*     */ import org.eclipse.core.runtime.IExecutableExtension;
/*     */ import org.eclipse.core.runtime.QualifiedName;
/*     */ import org.eclipse.core.runtime.content.IContentDescriber;
/*     */ import org.eclipse.core.runtime.content.IContentDescription;
/*     */ import org.eclipse.core.runtime.content.ITextContentDescriber;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
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
/*     */ 
/*     */ 
/*     */ public class ContentHandlerImpl
/*     */   implements ContentHandler
/*     */ {
/*     */   public static Map<String, Object> createContentDescription(ContentHandler.Validity validity) {
/*  53 */     Map<String, Object> result = new HashMap<String, Object>();
/*  54 */     result.put("org.eclipse.emf.ecore:validity", validity);
/*  55 */     return result;
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
/*     */   protected Set<String> getRequestedProperties(Map<?, ?> options) {
/*  74 */     return (Set<String>)options.get("REQUESTED_PROPERTIES");
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
/*     */   protected boolean isRequestedProperty(String property, Map<?, ?> options) {
/*  86 */     if ("org.eclipse.emf.ecore:validity".equals(property) || "org.eclipse.emf.ecore:contentType".equals(property))
/*     */     {
/*  88 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  92 */     Set<String> requestedProperties = getRequestedProperties(options);
/*  93 */     if (requestedProperties == null)
/*     */     {
/*  95 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  99 */     return requestedProperties.contains(property);
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
/*     */   public boolean canHandle(URI uri) {
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 119 */     Map<String, Object> result = createContentDescription(ContentHandler.Validity.INDETERMINATE);
/* 120 */     if (isRequestedProperty("org.eclipse.core.runtime:bom", options))
/*     */     {
/* 122 */       result.put("org.eclipse.core.runtime:bom", getByteOrderMark(uri, inputStream, options, context));
/*     */     }
/* 124 */     return result;
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
/*     */   protected ContentHandler.ByteOrderMark getByteOrderMark(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/* 138 */     ContentHandler.ByteOrderMark result = (ContentHandler.ByteOrderMark)context.get("org.eclipse.core.runtime:bom");
/* 139 */     if (result == null) {
/*     */       
/* 141 */       result = ContentHandler.ByteOrderMark.read(inputStream);
/* 142 */       inputStream.reset();
/* 143 */       context.put("org.eclipse.core.runtime:bom", result);
/*     */     } 
/* 145 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Describer
/*     */     implements IContentDescriber, ITextContentDescriber, IExecutableExtension
/*     */   {
/*     */     protected ContentHandler contentHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public QualifiedName[] getSupportedOptions() {
/* 165 */       return SUPPORTED_OPTIONS;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     private static final QualifiedName[] SUPPORTED_OPTIONS = new QualifiedName[] { IContentDescription.CHARSET, IContentDescription.BYTE_ORDER_MARK };
/*     */ 
/*     */     
/*     */     protected static final String CONTENT_TYPE_ID = "contentTypeID";
/*     */     
/*     */     protected static final String EXTENSIONS = "extensions";
/*     */ 
/*     */     
/*     */     protected String getProperty(QualifiedName qualifiedName) {
/* 180 */       return qualifiedName.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object getDescriptionValue(QualifiedName qualifiedName, Object value) {
/* 191 */       if (value == null)
/*     */       {
/* 193 */         return null;
/*     */       }
/* 195 */       if (IContentDescription.BYTE_ORDER_MARK.equals(qualifiedName))
/*     */       {
/* 197 */         return ((ContentHandler.ByteOrderMark)value).bytes();
/*     */       }
/*     */ 
/*     */       
/* 201 */       return value;
/*     */     }
/*     */ 
/*     */     
/*     */     public int describe(InputStream inputStream, IContentDescription description) throws IOException {
/*     */       Map<String, ?> result;
/* 207 */       Map<Object, Object> options = new HashMap<Object, Object>();
/*     */       
/* 209 */       if (description != null) {
/*     */         
/* 211 */         Map<String, QualifiedName> requestedPropertyToQualifiedNameMap = new HashMap<String, QualifiedName>();
/* 212 */         Set<String> requestedProperties = new HashSet<String>(); byte b; int i; QualifiedName[] arrayOfQualifiedName;
/* 213 */         for (i = (arrayOfQualifiedName = getSupportedOptions()).length, b = 0; b < i; ) { QualifiedName qualifiedName = arrayOfQualifiedName[b];
/*     */           
/* 215 */           if (description.isRequested(qualifiedName)) {
/*     */             
/* 217 */             String property = getProperty(qualifiedName);
/* 218 */             if (property != null) {
/*     */               
/* 220 */               requestedPropertyToQualifiedNameMap.put(property, qualifiedName);
/* 221 */               requestedProperties.add(property);
/*     */             } 
/*     */           }  b++; }
/*     */         
/* 225 */         options.put("REQUESTED_PROPERTIES", requestedProperties);
/* 226 */         result = this.contentHandler.contentDescription(URI.createURI("*"), inputStream, options, new HashMap<Object, Object>());
/* 227 */         for (Map.Entry<String, ?> property : result.entrySet())
/*     */         {
/* 229 */           QualifiedName qualifiedName = requestedPropertyToQualifiedNameMap.get(property.getKey());
/* 230 */           if (qualifiedName != null)
/*     */           {
/* 232 */             description.setProperty(qualifiedName, getDescriptionValue(qualifiedName, property.getValue()));
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 238 */         options.put("REQUESTED_PROPERTIES", Collections.emptySet());
/* 239 */         result = this.contentHandler.contentDescription(URI.createURI("*"), inputStream, options, new HashMap<Object, Object>());
/*     */       } 
/* 241 */       return ((ContentHandler.Validity)result.get("org.eclipse.emf.ecore:validity")).ordinal();
/*     */     }
/*     */ 
/*     */     
/*     */     public int describe(Reader reader, IContentDescription description) throws IOException {
/* 246 */       return describe((InputStream)new URIConverter.ReadableInputStream(reader), description);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setInitializationData(IConfigurationElement configurationElement, String propertyName, Object data) throws CoreException {
/* 251 */       Map<String, String> parameters = getParameters(configurationElement, propertyName, data);
/* 252 */       this.contentHandler = createContentHandler(parameters);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ContentHandler createContentHandler(Map<String, String> parameters) {
/* 262 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Map<String, String> getParameters(IConfigurationElement configurationElement, String propertyName, Object data) {
/* 286 */       Map<String, String> parameters = new HashMap<String, String>();
/* 287 */       if (data != null) {
/*     */ 
/*     */         
/* 290 */         Map<String, String> dataMap = (Map<String, String>)data;
/* 291 */         parameters.putAll(dataMap);
/* 292 */         parameters.put("contentTypeID", configurationElement.getAttribute("id"));
/* 293 */         String fileExtensions = configurationElement.getAttribute("file-extensions");
/* 294 */         if (fileExtensions != null)
/*     */         {
/* 296 */           parameters.put("extensions", fileExtensions.replace(',', ' '));
/*     */         }
/*     */       } 
/* 299 */       return parameters;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ContentHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */