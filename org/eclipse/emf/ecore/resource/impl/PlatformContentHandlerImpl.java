/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.core.runtime.QualifiedName;
/*     */ import org.eclipse.core.runtime.content.IContentDescription;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlatformContentHandlerImpl
/*     */   extends ContentHandlerImpl
/*     */ {
/*     */   protected QualifiedName getQualifiedName(String property) {
/*  39 */     int index = property.lastIndexOf(":");
/*  40 */     if (index == -1)
/*     */     {
/*  42 */       return new QualifiedName(null, property);
/*     */     }
/*     */ 
/*     */     
/*  46 */     return new QualifiedName(property.substring(0, index), property.substring(index + 1));
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
/*     */   protected Object getDescriptionValue(QualifiedName qualifiedName, Object value) {
/*  58 */     if (value == null)
/*     */     {
/*  60 */       return null;
/*     */     }
/*  62 */     if (IContentDescription.BYTE_ORDER_MARK.equals(qualifiedName)) {
/*     */       byte b; int i; ContentHandler.ByteOrderMark[] arrayOfByteOrderMark;
/*  64 */       for (i = (arrayOfByteOrderMark = ContentHandler.ByteOrderMark.values()).length, b = 0; b < i; ) { ContentHandler.ByteOrderMark byteOrderMarker = arrayOfByteOrderMark[b];
/*     */         
/*  66 */         if (value == byteOrderMarker.bytes())
/*     */         {
/*  68 */           return byteOrderMarker; } 
/*     */         b++; }
/*     */       
/*  71 */       return null;
/*     */     } 
/*     */ 
/*     */     
/*  75 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException {
/*     */     IContentDescription contentDescription;
/*  86 */     if (uri.isPlatformResource() && PlatformResourceURIHandlerImpl.workspaceRoot != null) {
/*     */       
/*  88 */       contentDescription = PlatformResourceURIHandlerImpl.WorkbenchHelper.getContentDescription(uri.toPlatformString(true), options);
/*     */     }
/*     */     else {
/*     */       
/*  92 */       contentDescription = Platform.getContentTypeManager().getDescriptionFor(inputStream, uri.lastSegment(), IContentDescription.ALL);
/*     */     } 
/*  94 */     if (contentDescription == null)
/*     */     {
/*  96 */       return INVALID_CONTENT_DESCRIPTION;
/*     */     }
/*     */ 
/*     */     
/* 100 */     Map<String, Object> result = createContentDescription(ContentHandler.Validity.VALID);
/* 101 */     result.put("org.eclipse.emf.ecore:contentType", contentDescription.getContentType().getId());
/* 102 */     Set<String> requestedProperties = getRequestedProperties(options);
/* 103 */     if (requestedProperties != null)
/*     */     {
/* 105 */       for (String property : requestedProperties) {
/*     */         
/* 107 */         QualifiedName qualifiedName = getQualifiedName(property);
/* 108 */         if (qualifiedName != null) {
/*     */           
/* 110 */           Object value = getDescriptionValue(qualifiedName, contentDescription.getProperty(qualifiedName));
/* 111 */           if (value != null)
/*     */           {
/* 113 */             result.put(property, value);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 118 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\PlatformContentHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */