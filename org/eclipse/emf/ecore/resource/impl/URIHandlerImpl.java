/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.URIConverter;
/*     */ import org.eclipse.emf.ecore.resource.URIHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class URIHandlerImpl
/*     */   implements URIHandler
/*     */ {
/*     */   public boolean canHandle(URI uri) {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URIConverter getURIConverter(Map<?, ?> options) {
/*  69 */     return (URIConverter)options.get("URI_CONVERTER");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<Object, Object> getResponse(Map<?, ?> options) {
/*  80 */     return (Map<Object, Object>)options.get("RESPONSE");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Set<String> getRequestedAttributes(Map<?, ?> options) {
/*  91 */     return (Set<String>)options.get("requestedAttributes");
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
/*     */   public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
/*     */     try {
/* 104 */       URL url = new URL(uri.toString());
/* 105 */       final URLConnection urlConnection = url.openConnection();
/* 106 */       urlConnection.setDoOutput(true);
/* 107 */       if (urlConnection instanceof HttpURLConnection) {
/*     */         
/* 109 */         final HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
/* 110 */         httpURLConnection.setRequestMethod("PUT");
/* 111 */         return 
/* 112 */           new FilterOutputStream(urlConnection.getOutputStream())
/*     */           {
/*     */             
/*     */             public void close() throws IOException
/*     */             {
/* 117 */               super.close();
/* 118 */               int responseCode = httpURLConnection.getResponseCode();
/* 119 */               switch (responseCode) {
/*     */                 case 200:
/*     */                 case 201:
/*     */                 case 204:
/*     */                   return;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 129 */               throw new IOException("PUT failed with HTTP response code " + responseCode);
/*     */             }
/*     */           };
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       OutputStream result = urlConnection.getOutputStream();
/* 138 */       final Map<Object, Object> response = getResponse(options);
/* 139 */       if (response != null)
/*     */       {
/* 141 */         result = 
/* 142 */           new FilterOutputStream(result)
/*     */           {
/*     */ 
/*     */             
/*     */             public void close() throws IOException
/*     */             {
/*     */               try {
/* 149 */                 super.close();
/*     */               }
/*     */               finally {
/*     */                 
/* 153 */                 response.put("TIME_STAMP", Long.valueOf(urlConnection.getLastModified()));
/*     */               } 
/*     */             }
/*     */           };
/*     */       }
/* 158 */       return result;
/*     */     
/*     */     }
/* 161 */     catch (RuntimeException exception) {
/*     */       
/* 163 */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/*     */     try {
/* 176 */       URL url = new URL(uri.toString());
/* 177 */       URLConnection urlConnection = url.openConnection();
/* 178 */       InputStream result = urlConnection.getInputStream();
/* 179 */       Map<Object, Object> response = getResponse(options);
/* 180 */       if (response != null)
/*     */       {
/* 182 */         response.put("TIME_STAMP", Long.valueOf(urlConnection.getLastModified()));
/*     */       }
/* 184 */       return result;
/*     */     }
/* 186 */     catch (RuntimeException exception) {
/*     */       
/* 188 */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(URI uri, Map<?, ?> options) throws IOException {
/*     */     try {
/* 199 */       URL url = new URL(uri.toString());
/* 200 */       URLConnection urlConnection = url.openConnection();
/* 201 */       urlConnection.setDoOutput(true);
/* 202 */       if (urlConnection instanceof HttpURLConnection) {
/*     */         
/* 204 */         HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
/* 205 */         httpURLConnection.setRequestMethod("DELETE");
/* 206 */         int responseCode = httpURLConnection.getResponseCode();
/* 207 */         switch (responseCode) {
/*     */           case 200:
/*     */           case 202:
/*     */           case 204:
/*     */             return;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 217 */         throw new IOException("DELETE failed with HTTP response code " + responseCode);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 223 */       throw new IOException("Delete is not supported for " + uri);
/*     */     
/*     */     }
/* 226 */     catch (RuntimeException exception) {
/*     */       
/* 228 */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
/* 237 */     URIConverter uriConverter = (URIConverter)options.get("URI_CONVERTER");
/* 238 */     InputStream inputStream = null;
/* 239 */     Map<String, ?> result = null;
/* 240 */     Map<Object, Object> context = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {  }
/*     */     finally
/* 292 */     { if (inputStream != null)
/*     */       {
/* 294 */         inputStream.close(); }  }  if (inputStream != null) inputStream.close();
/*     */ 
/*     */ 
/*     */     
/* 298 */     return (result == null) ? ContentHandler.INVALID_CONTENT_DESCRIPTION : result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exists(URI uri, Map<?, ?> options) {
/*     */     try {
/* 309 */       URL url = new URL(uri.toString());
/* 310 */       URLConnection urlConnection = url.openConnection();
/* 311 */       if (urlConnection instanceof HttpURLConnection) {
/*     */         
/* 313 */         HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
/* 314 */         httpURLConnection.setRequestMethod("HEAD");
/* 315 */         int responseCode = httpURLConnection.getResponseCode();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 320 */         return (responseCode == 200);
/*     */       } 
/*     */ 
/*     */       
/* 324 */       InputStream inputStream = urlConnection.getInputStream();
/* 325 */       inputStream.close();
/* 326 */       return true;
/*     */     
/*     */     }
/* 329 */     catch (Throwable exception) {
/*     */       
/* 331 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
/* 337 */     Map<String, Object> result = new HashMap<String, Object>();
/* 338 */     Set<String> requestedAttributes = getRequestedAttributes(options);
/*     */     
/*     */     try {
/* 341 */       URL url = new URL(uri.toString());
/* 342 */       URLConnection urlConnection = null;
/* 343 */       if (requestedAttributes == null || requestedAttributes.contains("readOnly")) {
/*     */         
/* 345 */         urlConnection = url.openConnection();
/* 346 */         if (urlConnection instanceof HttpURLConnection) {
/*     */           
/* 348 */           HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
/* 349 */           httpURLConnection.setRequestMethod("OPTIONS");
/* 350 */           int responseCode = httpURLConnection.getResponseCode();
/* 351 */           if (responseCode == 200) {
/*     */             
/* 353 */             String allow = httpURLConnection.getHeaderField("Allow");
/* 354 */             result.put("readOnly", Boolean.valueOf(!(allow != null && allow.contains("PUT"))));
/*     */           } 
/* 356 */           urlConnection = null;
/*     */         }
/*     */         else {
/*     */           
/* 360 */           result.put("readOnly", Boolean.valueOf(true));
/*     */         } 
/*     */       } 
/*     */       
/* 364 */       if (requestedAttributes == null || requestedAttributes.contains("timeStamp")) {
/*     */         
/* 366 */         if (urlConnection == null) {
/*     */           
/* 368 */           urlConnection = url.openConnection();
/* 369 */           if (urlConnection instanceof HttpURLConnection) {
/*     */             
/* 371 */             HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
/* 372 */             httpURLConnection.setRequestMethod("HEAD");
/* 373 */             httpURLConnection.getResponseCode();
/*     */           } 
/*     */         } 
/* 376 */         if (urlConnection.getHeaderField("last-modified") != null)
/*     */         {
/* 378 */           result.put("timeStamp", Long.valueOf(urlConnection.getLastModified()));
/*     */         }
/*     */       } 
/*     */       
/* 382 */       if (requestedAttributes == null || requestedAttributes.contains("length"))
/*     */       {
/* 384 */         if (urlConnection == null) {
/*     */           
/* 386 */           urlConnection = url.openConnection();
/* 387 */           if (urlConnection instanceof HttpURLConnection) {
/*     */             
/* 389 */             HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
/* 390 */             httpURLConnection.setRequestMethod("HEAD");
/* 391 */             httpURLConnection.getResponseCode();
/*     */           } 
/*     */         } 
/* 394 */         if (urlConnection.getHeaderField("content-length") != null)
/*     */         {
/* 396 */           result.put("length", Integer.valueOf(urlConnection.getContentLength()));
/*     */         }
/*     */       }
/*     */     
/* 400 */     } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */     
/* 404 */     return result;
/*     */   }
/*     */   
/*     */   public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {}
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\URIHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */