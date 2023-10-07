/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.core.resources.IContainer;
/*     */ import org.eclipse.core.resources.IFile;
/*     */ import org.eclipse.core.resources.IFolder;
/*     */ import org.eclipse.core.resources.IResource;
/*     */ import org.eclipse.core.resources.IWorkspaceRoot;
/*     */ import org.eclipse.core.resources.ResourceAttributes;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.core.runtime.IPath;
/*     */ import org.eclipse.core.runtime.IProgressMonitor;
/*     */ import org.eclipse.core.runtime.Path;
/*     */ import org.eclipse.core.runtime.content.IContentDescription;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlatformResourceURIHandlerImpl
/*     */   extends URIHandlerImpl
/*     */ {
/*     */   public static class WorkbenchHelper
/*     */   {
/*     */     public static OutputStream createPlatformResourceOutputStream(String platformResourcePath, Map<?, ?> options) throws IOException {
/* 161 */       IFile file = PlatformResourceURIHandlerImpl.workspaceRoot.getFile((IPath)new Path(platformResourcePath));
/*     */       
/* 163 */       final Map<Object, Object> response = (options == null) ? null : (Map<Object, Object>)options.get("RESPONSE");
/* 164 */       return 
/* 165 */         new PlatformResourceURIHandlerImpl.PlatformResourceOutputStream(file, false, true, null)
/*     */         {
/*     */ 
/*     */           
/*     */           public void close() throws IOException
/*     */           {
/*     */             try {
/* 172 */               super.close();
/*     */             }
/*     */             finally {
/*     */               
/* 176 */               if (response != null)
/*     */               {
/* 178 */                 response.put("TIME_STAMP", Long.valueOf(this.file.getLocalTimeStamp()));
/*     */               }
/*     */             } 
/*     */           }
/*     */         };
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
/*     */     public static InputStream createPlatformResourceInputStream(String platformResourcePath, Map<?, ?> options) throws IOException {
/* 197 */       IFile file = PlatformResourceURIHandlerImpl.workspaceRoot.getFile((IPath)new Path(platformResourcePath));
/*     */       
/*     */       try {
/* 200 */         if (!file.isSynchronized(1))
/*     */         {
/* 202 */           file.refreshLocal(1, null);
/*     */         }
/* 204 */         InputStream result = file.getContents();
/* 205 */         if (options != null) {
/*     */ 
/*     */           
/* 208 */           Map<Object, Object> response = (Map<Object, Object>)options.get("RESPONSE");
/* 209 */           if (response != null)
/*     */           {
/* 211 */             response.put("TIME_STAMP", Long.valueOf(file.getLocalTimeStamp()));
/*     */           }
/*     */         } 
/* 214 */         return result;
/*     */       }
/* 216 */       catch (CoreException exception) {
/*     */         
/* 218 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public static void delete(String platformResourcePath, Map<?, ?> options) throws IOException {
/* 224 */       IFile file = PlatformResourceURIHandlerImpl.workspaceRoot.getFile((IPath)new Path(platformResourcePath));
/*     */       
/*     */       try {
/* 227 */         file.delete(true, null);
/*     */       }
/* 229 */       catch (CoreException exception) {
/*     */         
/* 231 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public static boolean exists(String platformResourcePath, Map<?, ?> options) {
/* 237 */       IResource resource = PlatformResourceURIHandlerImpl.workspaceRoot.findMember((IPath)new Path(platformResourcePath));
/* 238 */       return (resource != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public static Map<String, ?> attributes(String platformResourcePath, Map<?, ?> options) {
/* 243 */       IResource resource = PlatformResourceURIHandlerImpl.workspaceRoot.findMember((IPath)new Path(platformResourcePath));
/* 244 */       Map<String, Object> result = new HashMap<String, Object>();
/* 245 */       if (resource != null) {
/*     */ 
/*     */         
/* 248 */         Set<String> requestedAttributes = (options == null) ? null : (Set<String>)options.get("requestedAttributes");
/*     */         
/* 250 */         if (requestedAttributes == null || requestedAttributes.contains("timeStamp"))
/*     */         {
/* 252 */           result.put("timeStamp", Long.valueOf(resource.getLocalTimeStamp()));
/*     */         }
/* 254 */         ResourceAttributes resourceAttributes = null;
/* 255 */         if (requestedAttributes == null || requestedAttributes.contains("readOnly")) {
/*     */           
/* 257 */           resourceAttributes = resource.getResourceAttributes();
/* 258 */           result.put("readOnly", Boolean.valueOf(resourceAttributes.isReadOnly()));
/*     */         } 
/* 260 */         if (requestedAttributes == null || requestedAttributes.contains("archive")) {
/*     */           
/* 262 */           if (resourceAttributes == null)
/*     */           {
/* 264 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 266 */           result.put("archive", Boolean.valueOf(resourceAttributes.isArchive()));
/*     */         } 
/* 268 */         if (requestedAttributes == null || requestedAttributes.contains("executable")) {
/*     */           
/* 270 */           if (resourceAttributes == null)
/*     */           {
/* 272 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 274 */           result.put("executable", Boolean.valueOf(resourceAttributes.isExecutable()));
/*     */         } 
/* 276 */         if (requestedAttributes == null || requestedAttributes.contains("hidden")) {
/*     */           
/* 278 */           if (resourceAttributes == null)
/*     */           {
/* 280 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 282 */           result.put("hidden", Boolean.valueOf(resourceAttributes.isHidden()));
/*     */         } 
/* 284 */         if (requestedAttributes == null || requestedAttributes.contains("directory")) {
/*     */           
/* 286 */           if (resourceAttributes == null)
/*     */           {
/* 288 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 290 */           result.put("directory", Boolean.valueOf(resource instanceof IContainer));
/*     */         } 
/*     */       } 
/* 293 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public static void updateAttributes(String platformResourcePath, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
/* 298 */       IResource resource = PlatformResourceURIHandlerImpl.workspaceRoot.findMember((IPath)new Path(platformResourcePath));
/* 299 */       if (resource == null)
/*     */       {
/* 301 */         throw new FileNotFoundException("The resource " + platformResourcePath + " does not exist");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 307 */         Long timeStamp = (Long)attributes.get("timeStamp");
/* 308 */         if (timeStamp != null)
/*     */         {
/* 310 */           resource.setLocalTimeStamp(timeStamp.longValue());
/*     */         }
/*     */         
/* 313 */         ResourceAttributes resourceAttributes = null;
/* 314 */         Boolean readOnly = (Boolean)attributes.get("readOnly");
/* 315 */         if (readOnly != null) {
/*     */           
/* 317 */           resourceAttributes = resource.getResourceAttributes();
/* 318 */           resourceAttributes.setReadOnly(readOnly.booleanValue());
/*     */         } 
/* 320 */         Boolean archive = (Boolean)attributes.get("archive");
/* 321 */         if (archive != null) {
/*     */           
/* 323 */           if (resourceAttributes == null)
/*     */           {
/* 325 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 327 */           resourceAttributes.setArchive(archive.booleanValue());
/*     */         } 
/* 329 */         Boolean executable = (Boolean)attributes.get("executable");
/* 330 */         if (executable != null) {
/*     */           
/* 332 */           if (resourceAttributes == null)
/*     */           {
/* 334 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 336 */           resourceAttributes.setExecutable(executable.booleanValue());
/*     */         } 
/* 338 */         Boolean hidden = (Boolean)attributes.get("hidden");
/* 339 */         if (hidden != null) {
/*     */           
/* 341 */           if (resourceAttributes == null)
/*     */           {
/* 343 */             resourceAttributes = resource.getResourceAttributes();
/*     */           }
/* 345 */           resourceAttributes.setHidden(hidden.booleanValue());
/*     */         } 
/*     */         
/* 348 */         if (resourceAttributes != null)
/*     */         {
/* 350 */           resource.setResourceAttributes(resourceAttributes);
/*     */         }
/*     */       }
/* 353 */       catch (CoreException exception) {
/*     */         
/* 355 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static IContentDescription getContentDescription(String platformResourcePath, Map<?, ?> options) throws IOException {
/* 362 */       IFile file = PlatformResourceURIHandlerImpl.workspaceRoot.getFile((IPath)new Path(platformResourcePath));
/*     */       
/*     */       try {
/* 365 */         return file.getContentDescription();
/*     */       }
/* 367 */       catch (CoreException exception) {
/*     */         
/* 369 */         throw new Resource.IOWrappedException(exception);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   protected static IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
/*     */ 
/*     */ 
/*     */ 
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
/* 391 */     return uri.isPlatformResource();
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
/*     */   public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
/* 411 */     String platformResourcePath = uri.toPlatformString(true);
/* 412 */     if (workspaceRoot != null)
/*     */     {
/* 414 */       return WorkbenchHelper.createPlatformResourceOutputStream(platformResourcePath, options);
/*     */     }
/*     */ 
/*     */     
/* 418 */     URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 419 */     if (resolvedLocation != null)
/*     */     {
/* 421 */       return ((URIConverter)options.get("URI_CONVERTER")).createOutputStream(resolvedLocation, options);
/*     */     }
/*     */     
/* 424 */     throw new IOException("The path '" + platformResourcePath + "' is unmapped");
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
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/* 445 */     String platformResourcePath = uri.toPlatformString(true);
/* 446 */     if (workspaceRoot != null)
/*     */     {
/* 448 */       return WorkbenchHelper.createPlatformResourceInputStream(platformResourcePath, options);
/*     */     }
/*     */ 
/*     */     
/* 452 */     URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 453 */     if (resolvedLocation != null)
/*     */     {
/* 455 */       return getURIConverter(options).createInputStream(resolvedLocation, options);
/*     */     }
/*     */     
/* 458 */     throw new IOException("The path '" + platformResourcePath + "' is unmapped");
/*     */   }
/*     */   public static class PlatformResourceOutputStream extends ByteArrayOutputStream {
/*     */     protected IFile file;
/*     */     protected boolean force;
/*     */     protected boolean keepHistory; protected IProgressMonitor progressMonitor; protected boolean previouslyFlushed; public PlatformResourceOutputStream(IFile file, boolean force, boolean keepHistory, IProgressMonitor progressMonitor) { this.file = file; this.force = force; this.keepHistory = keepHistory; this.progressMonitor = progressMonitor; } protected void createContainer(IContainer container) throws IOException { if (!container.exists()) if (container.getType() == 2) { createContainer(container.getParent()); try { ((IFolder)container).create(this.force, this.keepHistory, this.progressMonitor); } catch (CoreException exception) { throw new Resource.IOWrappedException(exception); }  }   } public void close() throws IOException { flush(); super.close(); } public void flush() throws IOException { super.flush(); if (this.previouslyFlushed) { if (this.count == 0)
/*     */           return;  } else { createContainer(this.file.getParent()); }  byte[] contents = toByteArray(); InputStream inputStream = new ByteArrayInputStream(contents, 0, contents.length); try { if (this.previouslyFlushed) { this.file.appendContents(inputStream, this.force, false, this.progressMonitor); } else if (!this.file.exists()) { this.file.create(inputStream, false, null); this.previouslyFlushed = true; } else { if (!this.file.isSynchronized(1))
/* 465 */             this.file.refreshLocal(1, this.progressMonitor);  this.file.setContents(inputStream, this.force, this.keepHistory, this.progressMonitor); this.previouslyFlushed = true; }  reset(); } catch (CoreException exception) { throw new Resource.IOWrappedException(exception); }  } } public void delete(URI uri, Map<?, ?> options) throws IOException { String platformResourcePath = uri.toPlatformString(true);
/* 466 */     if (workspaceRoot != null) {
/*     */       
/* 468 */       WorkbenchHelper.delete(platformResourcePath, options);
/*     */     }
/*     */     else {
/*     */       
/* 472 */       URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 473 */       if (resolvedLocation != null) {
/*     */         
/* 475 */         getURIConverter(options).delete(resolvedLocation, options);
/*     */       }
/*     */       else {
/*     */         
/* 479 */         throw new IOException("The path '" + platformResourcePath + "' is unmapped");
/*     */       } 
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exists(URI uri, Map<?, ?> options) {
/* 487 */     String platformResourcePath = uri.toPlatformString(true);
/* 488 */     if (workspaceRoot != null)
/*     */     {
/* 490 */       return WorkbenchHelper.exists(platformResourcePath, options);
/*     */     }
/*     */ 
/*     */     
/* 494 */     URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 495 */     return (resolvedLocation != null && getURIConverter(options).exists(resolvedLocation, options));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
/* 502 */     String platformResourcePath = uri.toPlatformString(true);
/* 503 */     if (workspaceRoot != null)
/*     */     {
/* 505 */       return WorkbenchHelper.attributes(platformResourcePath, options);
/*     */     }
/*     */ 
/*     */     
/* 509 */     URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 510 */     return (resolvedLocation == null) ? Collections.emptyMap() : getURIConverter(options).getAttributes(resolvedLocation, options);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
/* 517 */     String platformResourcePath = uri.toPlatformString(true);
/* 518 */     if (workspaceRoot != null) {
/*     */       
/* 520 */       WorkbenchHelper.updateAttributes(platformResourcePath, attributes, options);
/*     */     }
/*     */     else {
/*     */       
/* 524 */       URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
/* 525 */       if (resolvedLocation != null) {
/*     */         
/* 527 */         getURIConverter(options).setAttributes(resolvedLocation, attributes, options);
/*     */       }
/*     */       else {
/*     */         
/* 531 */         throw new IOException("The platform resource path '" + platformResourcePath + "' does not resolve");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\PlatformResourceURIHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */