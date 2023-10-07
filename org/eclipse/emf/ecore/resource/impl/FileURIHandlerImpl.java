/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class FileURIHandlerImpl
/*     */   extends URIHandlerImpl
/*     */ {
/*     */   public boolean canHandle(URI uri) {
/*  48 */     return uri.isFile();
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
/*     */   public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
/*  62 */     String filePath = uri.toFileString();
/*  63 */     final File file = new File(filePath);
/*  64 */     String parent = file.getParent();
/*  65 */     if (parent != null)
/*     */     {
/*  67 */       (new File(parent)).mkdirs();
/*     */     }
/*  69 */     final Map<Object, Object> response = getResponse(options);
/*  70 */     OutputStream outputStream = 
/*  71 */       new FileOutputStream(file)
/*     */       {
/*     */ 
/*     */         
/*     */         public void close() throws IOException
/*     */         {
/*     */           try {
/*  78 */             super.close();
/*     */           }
/*     */           finally {
/*     */             
/*  82 */             if (response != null)
/*     */             {
/*  84 */               response.put("TIME_STAMP", Long.valueOf(file.lastModified()));
/*     */             }
/*     */           } 
/*     */         }
/*     */       };
/*  89 */     return outputStream;
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
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/* 103 */     String filePath = uri.toFileString();
/* 104 */     File file = new File(filePath);
/* 105 */     InputStream inputStream = new FileInputStream(file);
/* 106 */     Map<Object, Object> response = getResponse(options);
/* 107 */     if (response != null)
/*     */     {
/* 109 */       response.put("TIME_STAMP", Long.valueOf(file.lastModified()));
/*     */     }
/* 111 */     return inputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(URI uri, Map<?, ?> options) throws IOException {
/* 117 */     String filePath = uri.toFileString();
/* 118 */     File file = new File(filePath);
/* 119 */     file.delete();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exists(URI uri, Map<?, ?> options) {
/* 125 */     String filePath = uri.toFileString();
/* 126 */     File file = new File(filePath);
/* 127 */     return file.exists();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
/* 133 */     Map<String, Object> result = new HashMap<String, Object>();
/* 134 */     String filePath = uri.toFileString();
/* 135 */     File file = new File(filePath);
/* 136 */     if (file.exists()) {
/*     */       
/* 138 */       Set<String> requestedAttributes = getRequestedAttributes(options);
/* 139 */       if (requestedAttributes == null || requestedAttributes.contains("timeStamp"))
/*     */       {
/* 141 */         result.put("timeStamp", Long.valueOf(file.lastModified()));
/*     */       }
/* 143 */       if (requestedAttributes == null || requestedAttributes.contains("length"))
/*     */       {
/* 145 */         result.put("length", Long.valueOf(file.length()));
/*     */       }
/* 147 */       if (requestedAttributes == null || requestedAttributes.contains("readOnly"))
/*     */       {
/* 149 */         result.put("readOnly", Boolean.valueOf(!file.canWrite()));
/*     */       }
/* 151 */       if (requestedAttributes == null || requestedAttributes.contains("hidden"))
/*     */       {
/* 153 */         result.put("hidden", Boolean.valueOf(file.isHidden()));
/*     */       }
/* 155 */       if (requestedAttributes == null || requestedAttributes.contains("directory"))
/*     */       {
/* 157 */         result.put("directory", Boolean.valueOf(file.isDirectory()));
/*     */       }
/*     */     } 
/* 160 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
/* 166 */     String filePath = uri.toFileString();
/* 167 */     File file = new File(filePath);
/* 168 */     if (file.exists()) {
/*     */       
/* 170 */       Long timeStamp = (Long)attributes.get("timeStamp");
/* 171 */       if (timeStamp != null && !file.setLastModified(timeStamp.longValue()))
/*     */       {
/* 173 */         throw new IOException("Could not set the timestamp for the file '" + file + "'");
/*     */       }
/* 175 */       Boolean isReadOnly = (Boolean)attributes.get("readOnly");
/* 176 */       if (Boolean.TRUE.equals(isReadOnly) && !file.setReadOnly())
/*     */       {
/* 178 */         throw new IOException("Could not set the file '" + file + "' to be read only");
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 183 */       throw new FileNotFoundException("The file '" + file + "' does not exist");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\FileURIHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */