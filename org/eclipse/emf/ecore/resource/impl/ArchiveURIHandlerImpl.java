/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipEntry;
/*     */ import org.eclipse.emf.common.archive.ArchiveURLConnection;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArchiveURIHandlerImpl
/*     */   extends URIHandlerImpl
/*     */ {
/*     */   public boolean canHandle(URI uri) {
/*  47 */     return "archive".equals(uri.scheme());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
/*  53 */     return createArchive(uri, options).getOutputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/*  59 */     return createArchive(uri, options).getInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(URI uri, Map<?, ?> options) throws IOException {
/*  65 */     createArchive(uri, options).delete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exists(URI uri, Map<?, ?> options) {
/*     */     try {
/*  73 */       InputStream inputStream = createInputStream(uri, options);
/*  74 */       inputStream.close();
/*  75 */       return true;
/*     */     }
/*  77 */     catch (IOException exception) {
/*     */       
/*  79 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
/*  86 */     return createArchive(uri, options).getAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
/*  92 */     createArchive(uri, options).setAttributes(attributes);
/*     */   }
/*     */ 
/*     */   
/*     */   protected class Archive
/*     */     extends ArchiveURLConnection
/*     */   {
/*     */     protected Map<?, ?> options;
/*     */     
/*     */     protected ZipEntry zipEntry;
/*     */ 
/*     */     
/*     */     public Archive(URI uri, Map<?, ?> options) {
/* 105 */       super(uri.toString());
/* 106 */       this.options = options;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean emulateArchiveScheme() {
/* 112 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useZipFile() {
/* 118 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected InputStream createInputStream(String nestedURL) throws IOException {
/* 124 */       return ArchiveURIHandlerImpl.this.getURIConverter(this.options).createInputStream(URI.createURI(nestedURL), this.options);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected OutputStream createOutputStream(String nestedURL) throws IOException {
/* 130 */       return ArchiveURIHandlerImpl.this.getURIConverter(this.options).createOutputStream(URI.createURI(nestedURL), this.options);
/*     */     }
/*     */ 
/*     */     
/*     */     protected InputStream yield(ZipEntry zipEntry, InputStream inputStream) throws IOException {
/* 135 */       this.zipEntry = zipEntry;
/* 136 */       Map<Object, Object> response = ArchiveURIHandlerImpl.this.getResponse(this.options);
/* 137 */       if (response != null)
/*     */       {
/* 139 */         response.put("TIME_STAMP", Long.valueOf(zipEntry.getTime()));
/*     */       }
/* 141 */       return super.yield(zipEntry, inputStream);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected OutputStream yield(ZipEntry zipEntry, OutputStream outputStream) throws IOException {
/* 147 */       this.zipEntry = zipEntry;
/* 148 */       Map<Object, Object> response = ArchiveURIHandlerImpl.this.getResponse(this.options);
/* 149 */       if (response != null)
/*     */       {
/* 151 */         response.put("TIME_STAMP", Long.valueOf(zipEntry.getTime()));
/*     */       }
/* 153 */       return super.yield(zipEntry, outputStream);
/*     */     }
/*     */ 
/*     */     
/*     */     public Map<String, ?> getAttributes() {
/* 158 */       Map<String, Object> result = new HashMap<String, Object>();
/*     */       
/*     */       try {
/* 161 */         Set<String> requestedAttributes = ArchiveURIHandlerImpl.this.getRequestedAttributes(this.options);
/* 162 */         if (requestedAttributes == null || requestedAttributes.contains("readOnly")) {
/*     */           
/* 164 */           Set<String> requestedSubAttributes = new HashSet<String>();
/* 165 */           requestedSubAttributes.add("readOnly");
/* 166 */           Map<Object, Object> subOptions = new ExtensibleURIConverterImpl.OptionsMap("requestedAttributes", requestedSubAttributes, this.options);
/* 167 */           result.putAll(ArchiveURIHandlerImpl.this.getURIConverter(subOptions).getAttributes(URI.createURI(getNestedURL()), subOptions));
/*     */         } 
/*     */         
/* 170 */         InputStream inputStream = null;
/* 171 */         if (requestedAttributes == null || requestedAttributes.contains("directory")) {
/*     */           
/* 173 */           inputStream = getInputStream();
/* 174 */           inputStream.close();
/* 175 */           result.put("directory", Boolean.valueOf(this.zipEntry.isDirectory()));
/*     */         } 
/* 177 */         if (requestedAttributes == null || requestedAttributes.contains("length")) {
/*     */           
/* 179 */           if (inputStream == null) {
/*     */             
/* 181 */             inputStream = getInputStream();
/* 182 */             inputStream.close();
/*     */           } 
/* 184 */           result.put("length", Long.valueOf(this.zipEntry.getSize()));
/*     */         } 
/* 186 */         if (requestedAttributes == null || requestedAttributes.contains("timeStamp"))
/*     */         {
/* 188 */           if (inputStream == null) {
/*     */             
/* 190 */             inputStream = getInputStream();
/* 191 */             inputStream.close();
/*     */           } 
/* 193 */           result.put("timeStamp", Long.valueOf(this.zipEntry.getTime()));
/*     */         }
/*     */       
/* 196 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/* 200 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setAttributes(Map<String, ?> attributes) throws IOException {
/* 205 */       Long timeStamp = (Long)attributes.get("timeStamp");
/* 206 */       if (timeStamp != null)
/*     */       {
/* 208 */         setTimeStamp(timeStamp.longValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Archive createArchive(URI uri, Map<?, ?> options) {
/* 215 */     return new Archive(uri, options);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ArchiveURIHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */