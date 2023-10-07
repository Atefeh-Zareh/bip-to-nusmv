/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.core.resources.IFile;
/*     */ import org.eclipse.core.resources.IWorkspaceRoot;
/*     */ import org.eclipse.core.runtime.IProgressMonitor;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ import org.eclipse.emf.common.archive.ArchiveURLConnection;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class URIConverterImpl
/*     */   extends ExtensibleURIConverterImpl
/*     */ {
/*     */   @Deprecated
/*     */   public static class PlatformResourceOutputStream
/*     */     extends PlatformResourceURIHandlerImpl.PlatformResourceOutputStream
/*     */   {
/*     */     @Deprecated
/*     */     public PlatformResourceOutputStream(IFile file, boolean force, boolean keepHistory, IProgressMonitor progressMonitor) {
/*  71 */       super(file, force, keepHistory, progressMonitor);
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
/*     */   public static class WorkbenchHelper
/*     */     extends PlatformResourceURIHandlerImpl.WorkbenchHelper
/*     */   {
/*     */     @Deprecated
/*     */     public static OutputStream createPlatformResourceOutputStream(String platformResourcePath) throws IOException {
/*  97 */       return PlatformResourceURIHandlerImpl.WorkbenchHelper.createPlatformResourceOutputStream(platformResourcePath, null);
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
/*     */     @Deprecated
/*     */     public static InputStream createPlatformResourceInputStream(String platformResourcePath) throws IOException {
/* 114 */       return PlatformResourceURIHandlerImpl.WorkbenchHelper.createPlatformResourceInputStream(platformResourcePath, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 123 */   protected static IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
/*     */   
/*     */   private static Map<String, Boolean> efsScheme;
/*     */   private static final Method EFS_GET_FILE_SYSTEM_METHOD;
/*     */   private static final Method EFS_GET_STORE_METHOD;
/*     */   private static final Method FILE_STORE_OPEN_INPUT_STREAM_METHOD;
/*     */   private static final Method FILE_STORE_OPEN_OUTPUT_STREAM_METHOD;
/*     */   
/*     */   static {
/* 132 */     Method efsGetStoreMethod = null;
/* 133 */     Method efsGetFileSystemMethod = null;
/* 134 */     Method fileStoreOpenInputStreamMethod = null;
/* 135 */     Method fileStoreOpenOutputStreamMethod = null;
/*     */     
/*     */     try {
/* 138 */       Class<?> efsClass = CommonPlugin.loadClass("org.eclipse.core.filesystem", "org.eclipse.core.filesystem.EFS");
/* 139 */       efsGetStoreMethod = efsClass.getMethod("getStore", new Class[] { URI.class });
/* 140 */       efsGetFileSystemMethod = efsClass.getMethod("getFileSystem", new Class[] { String.class });
/* 141 */       Class<?> fileStoreClass = efsGetStoreMethod.getReturnType();
/* 142 */       fileStoreOpenInputStreamMethod = fileStoreClass.getMethod("openInputStream", new Class[] { int.class, IProgressMonitor.class });
/* 143 */       fileStoreOpenOutputStreamMethod = fileStoreClass.getMethod("openOutputStream", new Class[] { int.class, IProgressMonitor.class });
/*     */     }
/* 145 */     catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/* 149 */     EFS_GET_STORE_METHOD = efsGetStoreMethod;
/* 150 */     EFS_GET_FILE_SYSTEM_METHOD = efsGetFileSystemMethod;
/* 151 */     FILE_STORE_OPEN_INPUT_STREAM_METHOD = fileStoreOpenInputStreamMethod;
/* 152 */     FILE_STORE_OPEN_OUTPUT_STREAM_METHOD = fileStoreOpenOutputStreamMethod;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isArchiveScheme(String scheme) {
/* 184 */     return "archive".equals(scheme);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isEFSScheme(String scheme) {
/* 195 */     if (EFS_GET_FILE_SYSTEM_METHOD == null)
/*     */     {
/* 197 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 201 */     Boolean result = (efsScheme == null) ? null : efsScheme.get(scheme);
/* 202 */     if (result == null) {
/*     */ 
/*     */       
/*     */       try {
/* 206 */         result = Boolean.valueOf((EFS_GET_FILE_SYSTEM_METHOD.invoke(null, new Object[] { scheme }) != null));
/*     */       }
/* 208 */       catch (Throwable exception) {
/*     */         
/* 210 */         result = Boolean.FALSE;
/*     */       } 
/* 212 */       Map<String, Boolean> map = new HashMap<String, Boolean>();
/* 213 */       if (efsScheme != null)
/*     */       {
/* 215 */         map.putAll(efsScheme);
/*     */       }
/* 217 */       map.put(scheme, result);
/* 218 */       efsScheme = map;
/*     */     } 
/* 220 */     return (result == Boolean.TRUE);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream(URI uri) throws IOException {
/* 253 */     URI converted = normalize(uri);
/* 254 */     if (converted.isFile()) {
/*     */       
/* 256 */       String filePath = converted.toFileString();
/* 257 */       return createFileOutputStream(filePath);
/*     */     } 
/*     */ 
/*     */     
/* 261 */     String scheme = converted.scheme();
/* 262 */     if (isArchiveScheme(scheme))
/*     */     {
/* 264 */       return createArchiveOutputStream(converted);
/*     */     }
/* 266 */     if (converted.isPlatformResource())
/*     */     {
/* 268 */       return createPlatformResourceOutputStream(converted.toPlatformString(true));
/*     */     }
/* 270 */     if (isEFSScheme(scheme))
/*     */     {
/* 272 */       return createEFSOutputStream(converted);
/*     */     }
/*     */ 
/*     */     
/* 276 */     return createURLOutputStream(converted);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
/* 284 */     return createOutputStream(uri);
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
/*     */   protected OutputStream createFileOutputStream(String filePath) throws IOException {
/* 297 */     File file = new File(filePath);
/* 298 */     String parent = file.getParent();
/* 299 */     if (parent != null)
/*     */     {
/* 301 */       (new File(parent)).mkdirs();
/*     */     }
/* 303 */     OutputStream outputStream = new FileOutputStream(file);
/* 304 */     return outputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected OutputStream createArchiveOutputStream(URI archiveURI) throws IOException {
/* 315 */     return createArchive(archiveURI).getOutputStream();
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
/*     */   protected OutputStream createPlatformResourceOutputStream(String platformResourcePath) throws IOException {
/* 335 */     if (workspaceRoot != null)
/*     */     {
/* 337 */       return WorkbenchHelper.createPlatformResourceOutputStream(platformResourcePath);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 342 */     URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 343 */     if (resolvedLocation != null)
/*     */     {
/* 345 */       return createOutputStream(resolvedLocation);
/*     */     }
/*     */     
/* 348 */     throw new IOException("The path '" + platformResourcePath + "' is unmapped");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected OutputStream createEFSOutputStream(URI uri) throws IOException {
/* 359 */     if (EFS_GET_STORE_METHOD != null) {
/*     */       
/*     */       try {
/*     */         
/* 363 */         Object store = EFS_GET_STORE_METHOD.invoke(null, new Object[] { new URI(uri.toString()) });
/* 364 */         if (store != null)
/*     */         {
/* 366 */           return (OutputStream)FILE_STORE_OPEN_OUTPUT_STREAM_METHOD.invoke(store, new Object[] { Integer.valueOf(0), null });
/*     */         }
/*     */       }
/* 369 */       catch (Exception exception) {
/*     */         
/* 371 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 374 */     throw new IOException("EFS unavailable");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected OutputStream createURLOutputStream(URI uri) throws IOException {
/*     */     try {
/* 386 */       URL url = new URL(uri.toString());
/* 387 */       URLConnection urlConnection = url.openConnection();
/* 388 */       urlConnection.setDoOutput(true);
/* 389 */       return urlConnection.getOutputStream();
/*     */     }
/* 391 */     catch (RuntimeException exception) {
/*     */       
/* 393 */       throw new Resource.IOWrappedException(exception);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(URI uri) throws IOException {
/* 426 */     URI converted = normalize(uri);
/* 427 */     if (converted.isFile()) {
/*     */       
/* 429 */       String filePath = converted.toFileString();
/* 430 */       return createFileInputStream(filePath);
/*     */     } 
/*     */ 
/*     */     
/* 434 */     String scheme = converted.scheme();
/* 435 */     if (isArchiveScheme(scheme))
/*     */     {
/* 437 */       return createArchiveInputStream(converted);
/*     */     }
/* 439 */     if (converted.isPlatformResource())
/*     */     {
/* 441 */       return createPlatformResourceInputStream(converted.toPlatformString(true));
/*     */     }
/* 443 */     if (isEFSScheme(scheme))
/*     */     {
/* 445 */       return createEFSInputStream(converted);
/*     */     }
/*     */ 
/*     */     
/* 449 */     return createURLInputStream(converted);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/* 457 */     return createInputStream(uri);
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
/*     */   protected InputStream createFileInputStream(String filePath) throws IOException {
/* 470 */     File file = new File(filePath);
/* 471 */     InputStream inputStream = new FileInputStream(file);
/* 472 */     return inputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Archive
/*     */     extends ArchiveURLConnection
/*     */   {
/*     */     public Archive(URI uri) {
/* 482 */       super(uri.toString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean emulateArchiveScheme() {
/* 488 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useZipFile() {
/* 494 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected InputStream createInputStream(String nestedURL) throws IOException {
/* 500 */       return URIConverterImpl.this.createInputStream(URI.createURI(nestedURL));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected OutputStream createOutputStream(String nestedURL) throws IOException {
/* 506 */       return URIConverterImpl.this.createOutputStream(URI.createURI(nestedURL));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Archive createArchive(URI uri) {
/* 512 */     return new Archive(uri);
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
/*     */   protected InputStream createArchiveInputStream(URI archiveURI) throws IOException {
/* 524 */     return createArchive(archiveURI).getInputStream();
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
/*     */   protected InputStream createPlatformResourceInputStream(String platformResourcePath) throws IOException {
/* 544 */     if (workspaceRoot != null)
/*     */     {
/* 546 */       return WorkbenchHelper.createPlatformResourceInputStream(platformResourcePath);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 551 */     URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 552 */     if (resolvedLocation != null)
/*     */     {
/* 554 */       return createInputStream(resolvedLocation);
/*     */     }
/*     */     
/* 557 */     throw new IOException("The path '" + platformResourcePath + "' is unmapped");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream createEFSInputStream(URI uri) throws IOException {
/* 568 */     if (EFS_GET_STORE_METHOD != null) {
/*     */       
/*     */       try {
/*     */         
/* 572 */         Object store = EFS_GET_STORE_METHOD.invoke(null, new Object[] { new URI(uri.toString()) });
/* 573 */         if (store != null)
/*     */         {
/* 575 */           return (InputStream)FILE_STORE_OPEN_INPUT_STREAM_METHOD.invoke(store, new Object[] { Integer.valueOf(0), null });
/*     */         }
/*     */       }
/* 578 */       catch (Exception exception) {
/*     */         
/* 580 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/* 583 */     throw new IOException("EFS unavailable");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream createURLInputStream(URI uri) throws IOException {
/*     */     try {
/* 595 */       URL url = new URL(uri.toString());
/* 596 */       URLConnection urlConnection = url.openConnection();
/* 597 */       return urlConnection.getInputStream();
/*     */     }
/* 599 */     catch (RuntimeException exception) {
/*     */       
/* 601 */       throw new Resource.IOWrappedException(exception);
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
/*     */   @Deprecated
/*     */   protected URIMap getInternalURIMap() {
/* 614 */     return (URIMap)super.getInternalURIMap();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static interface URIMap extends ExtensibleURIConverterImpl.URIMap {}
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\URIConverterImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */