/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.core.runtime.IProgressMonitor;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EFSURIHandlerImpl
/*     */   extends URIHandlerImpl
/*     */ {
/*     */   private static Map<String, Boolean> efsScheme;
/*     */   private static final Method EFS_GET_FILE_SYSTEM_METHOD;
/*     */   private static final Method EFS_GET_STORE_METHOD;
/*     */   private static final Method FILE_STORE_OPEN_INPUT_STREAM_METHOD;
/*     */   private static final Method FILE_STORE_OPEN_OUTPUT_STREAM_METHOD;
/*     */   private static final Method FILE_STORE_DELETE_METHOD;
/*     */   private static final Method FILE_STORE_FETCH_INFO_METHOD;
/*     */   private static final Method FILE_STORE_PUT_INFO_METHOD;
/*     */   private static final Method FILE_INFO_EXISTS_METHOD;
/*     */   private static final Method FILE_INFO_GET_LENGTH_METHOD;
/*     */   private static final Method FILE_INFO_IS_DIRECOTRY_METHOD;
/*     */   
/*     */   static {
/*  57 */     Method efsGetStoreMethod = null;
/*  58 */     Method efsGetFileSystemMethod = null;
/*  59 */     Method fileStoreOpenInputStreamMethod = null;
/*  60 */     Method fileStoreOpenOutputStreamMethod = null;
/*  61 */     Method fileStoreDeleteMethod = null;
/*  62 */     Method fileStoreFetchInfoMethod = null;
/*  63 */     Method fileStorePutInfoMethod = null;
/*  64 */     Method fileInfoExistsMethod = null;
/*  65 */     Method fileInfoIsDirectoryMethod = null;
/*  66 */     Method fileInfoGetLengthMethod = null;
/*  67 */     Method fileInfoGetAttributeMethod = null;
/*  68 */     Method fileInfoSetAttributeMethod = null;
/*  69 */     Method fileInfoGetLastModifiedMethod = null;
/*  70 */     Method fileInfoSetLastModifiedMethod = null;
/*     */     
/*     */     try {
/*  73 */       Class<?> efsClass = CommonPlugin.loadClass("org.eclipse.core.filesystem", "org.eclipse.core.filesystem.EFS");
/*  74 */       efsGetStoreMethod = efsClass.getMethod("getStore", new Class[] { URI.class });
/*  75 */       efsGetFileSystemMethod = efsClass.getMethod("getFileSystem", new Class[] { String.class });
/*  76 */       Class<?> fileStoreClass = efsGetStoreMethod.getReturnType();
/*  77 */       fileStoreOpenInputStreamMethod = fileStoreClass.getMethod("openInputStream", new Class[] { int.class, IProgressMonitor.class });
/*  78 */       fileStoreOpenOutputStreamMethod = fileStoreClass.getMethod("openOutputStream", new Class[] { int.class, IProgressMonitor.class });
/*  79 */       fileStoreDeleteMethod = fileStoreClass.getMethod("delete", new Class[] { int.class, IProgressMonitor.class });
/*  80 */       fileStoreFetchInfoMethod = fileStoreClass.getMethod("fetchInfo", new Class[0]);
/*  81 */       Class<?> fileInfoClass = fileStoreFetchInfoMethod.getReturnType();
/*  82 */       fileStorePutInfoMethod = fileStoreClass.getMethod("putInfo", new Class[] { fileInfoClass, int.class, IProgressMonitor.class });
/*  83 */       fileInfoExistsMethod = fileInfoClass.getMethod("exists", new Class[0]);
/*  84 */       fileInfoIsDirectoryMethod = fileInfoClass.getMethod("isDirectory", new Class[0]);
/*  85 */       fileInfoGetLengthMethod = fileInfoClass.getMethod("getLength", new Class[0]);
/*  86 */       fileInfoGetAttributeMethod = fileInfoClass.getMethod("getAttribute", new Class[] { int.class });
/*  87 */       fileInfoSetAttributeMethod = fileInfoClass.getMethod("setAttribute", new Class[] { int.class, boolean.class });
/*  88 */       fileInfoGetLastModifiedMethod = fileInfoClass.getMethod("getLastModified", new Class[0]);
/*  89 */       fileInfoSetLastModifiedMethod = fileInfoClass.getMethod("setLastModified", new Class[] { long.class });
/*     */     }
/*  91 */     catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/*  95 */     EFS_GET_STORE_METHOD = efsGetStoreMethod;
/*  96 */     EFS_GET_FILE_SYSTEM_METHOD = efsGetFileSystemMethod;
/*  97 */     FILE_STORE_OPEN_INPUT_STREAM_METHOD = fileStoreOpenInputStreamMethod;
/*  98 */     FILE_STORE_OPEN_OUTPUT_STREAM_METHOD = fileStoreOpenOutputStreamMethod;
/*  99 */     FILE_STORE_DELETE_METHOD = fileStoreDeleteMethod;
/* 100 */     FILE_STORE_FETCH_INFO_METHOD = fileStoreFetchInfoMethod;
/* 101 */     FILE_STORE_PUT_INFO_METHOD = fileStorePutInfoMethod;
/* 102 */     FILE_INFO_EXISTS_METHOD = fileInfoExistsMethod;
/* 103 */     FILE_INFO_IS_DIRECOTRY_METHOD = fileInfoIsDirectoryMethod;
/* 104 */     FILE_INFO_GET_ATTRIBUTE_METHOD = fileInfoGetAttributeMethod;
/* 105 */     FILE_INFO_SET_ATTRIBUTE_METHOD = fileInfoSetAttributeMethod;
/* 106 */     FILE_INFO_GET_LENGTH_METHOD = fileInfoGetLengthMethod;
/* 107 */     FILE_INFO_GET_LAST_MODIFIED = fileInfoGetLastModifiedMethod;
/* 108 */     FILE_INFO_SET_LAST_MODIFIED = fileInfoSetLastModifiedMethod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     ATTRIBUTE_READ_ONLY = Integer.valueOf(2);
/* 317 */     ATTRIBUTE_EXECUTABLE = Integer.valueOf(4);
/* 318 */     ATTRIBUTE_ARCHIVE = Integer.valueOf(8);
/* 319 */     ATTRIBUTE_HIDDEN = Integer.valueOf(16);
/*     */   }
/*     */   private static final Method FILE_INFO_GET_ATTRIBUTE_METHOD;
/*     */   private static final Method FILE_INFO_SET_ATTRIBUTE_METHOD;
/*     */   private static final Method FILE_INFO_GET_LAST_MODIFIED; private static final Method FILE_INFO_SET_LAST_MODIFIED; private static final Integer ATTRIBUTE_READ_ONLY; private static final Integer ATTRIBUTE_EXECUTABLE; private static final Integer ATTRIBUTE_ARCHIVE; private static final Integer ATTRIBUTE_HIDDEN; private static final int SET_ATTRIBUTES = 1024; private static final int SET_LAST_MODIFIED = 2048; public boolean canHandle(URI uri) { String scheme = uri.scheme(); if (scheme == null || EFS_GET_FILE_SYSTEM_METHOD == null)
/*     */       return false;  Boolean result = (efsScheme == null) ? null : efsScheme.get(scheme); if (result == null) { try { result = Boolean.valueOf((EFS_GET_FILE_SYSTEM_METHOD.invoke(null, new Object[] { scheme }) != null)); } catch (Throwable exception) { result = Boolean.FALSE; }  Map<String, Boolean> map = new HashMap<String, Boolean>(); if (efsScheme != null)
/*     */         map.putAll(efsScheme);  map.put(scheme, result); efsScheme = map; }  return (result == Boolean.TRUE); } protected Object getStore(URI uri, Map<?, ?> options) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException { if (EFS_GET_STORE_METHOD != null) { Object store = EFS_GET_STORE_METHOD.invoke(null, new Object[] { new URI(uri.toString()) }); if (store != null)
/*     */         return store;  }
/* 327 */      throw new IOException("EFS unavailable"); } protected Object getInfo(URI uri, Object store, Map<?, ?> options) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException { return FILE_STORE_FETCH_INFO_METHOD.invoke(store, new Object[0]); } protected Object getInfo(URI uri, Map<?, ?> options) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException { return getInfo(uri, getStore(uri, options), options); } public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) { Object info; Map<String, Object> result = new HashMap<String, Object>();
/* 328 */     Set<String> requestedAttributes = getRequestedAttributes(options);
/*     */ 
/*     */     
/*     */     try {
/* 332 */       info = getInfo(uri, options);
/*     */     }
/* 334 */     catch (Exception exception) {
/*     */       
/* 336 */       return result;
/*     */     } 
/* 338 */     if (requestedAttributes == null || requestedAttributes.contains("timeStamp")) {
/*     */       
/*     */       try {
/*     */         
/* 342 */         Object timeStamp = FILE_INFO_GET_LAST_MODIFIED.invoke(info, new Object[0]);
/* 343 */         result.put("timeStamp", timeStamp);
/*     */       }
/* 345 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 350 */     if (requestedAttributes == null || requestedAttributes.contains("readOnly")) {
/*     */       
/*     */       try {
/*     */         
/* 354 */         Object isReadOnly = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_READ_ONLY });
/* 355 */         result.put("readOnly", isReadOnly);
/*     */       }
/* 357 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 362 */     if (requestedAttributes == null || requestedAttributes.contains("archive")) {
/*     */       
/*     */       try {
/*     */         
/* 366 */         Object isArchive = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_ARCHIVE });
/* 367 */         result.put("archive", isArchive);
/*     */       }
/* 369 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 374 */     if (requestedAttributes == null || requestedAttributes.contains("executable")) {
/*     */       
/*     */       try {
/*     */         
/* 378 */         Object isExecutable = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_EXECUTABLE });
/* 379 */         result.put("executable", isExecutable);
/*     */       }
/* 381 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 386 */     if (requestedAttributes == null || requestedAttributes.contains("hidden")) {
/*     */       
/*     */       try {
/*     */         
/* 390 */         Object isHidden = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_HIDDEN });
/* 391 */         result.put("hidden", isHidden);
/*     */       }
/* 393 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 398 */     if (requestedAttributes == null || requestedAttributes.contains("directory")) {
/*     */       
/*     */       try {
/*     */         
/* 402 */         Object isDirectory = FILE_INFO_IS_DIRECOTRY_METHOD.invoke(info, new Object[0]);
/* 403 */         result.put("directory", isDirectory);
/*     */       }
/* 405 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 410 */     if (requestedAttributes == null || requestedAttributes.contains("length")) {
/*     */       
/*     */       try {
/*     */         
/* 414 */         Object length = FILE_INFO_GET_LENGTH_METHOD.invoke(info, new Object[0]);
/* 415 */         result.put("length", length);
/*     */       }
/* 417 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 422 */     return result; }
/*     */    protected void setInfo(URI uri, Object store, Object info, int set, Map<?, ?> options) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException {
/*     */     FILE_STORE_PUT_INFO_METHOD.invoke(store, new Object[] { info, Integer.valueOf(set), null });
/*     */   }
/*     */   public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
/*     */     Object store, info;
/* 428 */     int set = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 433 */       store = getStore(uri, options);
/* 434 */       info = getInfo(uri, store, options);
/*     */     }
/* 436 */     catch (Exception exception) {
/*     */       
/* 438 */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/* 440 */     Object timeStamp = attributes.get("timeStamp");
/* 441 */     if (timeStamp != null) {
/*     */       
/*     */       try {
/*     */         
/* 445 */         FILE_INFO_SET_LAST_MODIFIED.invoke(info, new Object[] { timeStamp });
/* 446 */         set = 2048;
/*     */       }
/* 448 */       catch (Exception exception) {
/*     */         
/* 450 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 453 */     Object isReadOnly = attributes.get("readOnly");
/* 454 */     if (isReadOnly != null) {
/*     */       
/*     */       try {
/*     */         
/* 458 */         FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_READ_ONLY, isReadOnly });
/* 459 */         set |= 0x400;
/*     */       }
/* 461 */       catch (Exception exception) {
/*     */         
/* 463 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 466 */     Object isArchive = attributes.get("archive");
/* 467 */     if (isArchive != null) {
/*     */       
/*     */       try {
/*     */         
/* 471 */         FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_ARCHIVE, isArchive });
/* 472 */         set |= 0x400;
/*     */       }
/* 474 */       catch (Exception exception) {
/*     */         
/* 476 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 479 */     Object isExecutable = attributes.get("archive");
/* 480 */     if (isExecutable != null) {
/*     */       
/*     */       try {
/*     */         
/* 484 */         FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_EXECUTABLE, isExecutable });
/* 485 */         set |= 0x400;
/*     */       }
/* 487 */       catch (Exception exception) {
/*     */         
/* 489 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 492 */     Object isHidden = attributes.get("hidden");
/* 493 */     if (isHidden != null) {
/*     */       
/*     */       try {
/*     */         
/* 497 */         FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, new Object[] { ATTRIBUTE_HIDDEN, isHidden });
/* 498 */         set |= 0x400;
/*     */       }
/* 500 */       catch (Exception exception) {
/*     */         
/* 502 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 505 */     if (set != 0)
/*     */       
/*     */       try {
/*     */         
/* 509 */         setInfo(uri, store, info, set, options);
/*     */       }
/* 511 */       catch (Exception exception) {
/*     */         
/* 513 */         throw new Resource.IOWrappedException(exception);
/*     */       }  
/*     */   }
/*     */   
/*     */   public OutputStream createOutputStream(final URI uri, final Map<?, ?> options) throws IOException {
/*     */     try {
/*     */       OutputStream result = (OutputStream)FILE_STORE_OPEN_OUTPUT_STREAM_METHOD.invoke(getStore(uri, options), new Object[] { Integer.valueOf(0), null });
/*     */       final Map<Object, Object> response = getResponse(options);
/*     */       if (response != null)
/*     */         result = new BufferedOutputStream(result) {
/*     */             public void close() throws IOException {
/*     */               try {
/*     */                 super.close();
/*     */               } finally {
/*     */                 response.put("TIME_STAMP", EFSURIHandlerImpl.this.getAttributes(uri, options).get("timeStamp"));
/*     */               } 
/*     */             }
/*     */           }; 
/*     */       return result;
/*     */     } catch (IllegalArgumentException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } catch (IllegalAccessException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } catch (InvocationTargetException exception) {
/*     */       throw new Resource.IOWrappedException(exception.getCause());
/*     */     } catch (URISyntaxException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/*     */     try {
/*     */       InputStream result = (InputStream)FILE_STORE_OPEN_INPUT_STREAM_METHOD.invoke(getStore(uri, options), new Object[] { Integer.valueOf(0), null });
/*     */       Map<Object, Object> response = getResponse(options);
/*     */       if (response != null)
/*     */         response.put("TIME_STAMP", getAttributes(uri, options).get("timeStamp")); 
/*     */       return result;
/*     */     } catch (IllegalArgumentException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } catch (IllegalAccessException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } catch (InvocationTargetException exception) {
/*     */       throw new Resource.IOWrappedException(exception.getCause());
/*     */     } catch (URISyntaxException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void delete(URI uri, Map<?, ?> options) throws IOException {
/*     */     try {
/*     */       FILE_STORE_DELETE_METHOD.invoke(getStore(uri, options), new Object[] { Integer.valueOf(0), null });
/*     */     } catch (IllegalArgumentException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } catch (IllegalAccessException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } catch (InvocationTargetException exception) {
/*     */       throw new Resource.IOWrappedException(exception.getCause());
/*     */     } catch (URISyntaxException exception) {
/*     */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean exists(URI uri, Map<?, ?> options) {
/*     */     try {
/*     */       return Boolean.TRUE.equals(FILE_INFO_EXISTS_METHOD.invoke(getInfo(uri, options), new Object[0]));
/*     */     } catch (Exception exception) {
/*     */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\EFSURIHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */