/*     */ package org.eclipse.emf.ecore.resource;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.TreeIterator;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Resource
/*     */   extends Notifier
/*     */ {
/*     */   public static final int RESOURCE__RESOURCE_SET = 0;
/*     */   public static final int RESOURCE__URI = 1;
/*     */   public static final int RESOURCE__CONTENTS = 2;
/*     */   public static final int RESOURCE__IS_MODIFIED = 3;
/*     */   public static final int RESOURCE__IS_LOADED = 4;
/*     */   public static final int RESOURCE__IS_TRACKING_MODIFICATION = 5;
/*     */   public static final int RESOURCE__ERRORS = 6;
/*     */   public static final int RESOURCE__WARNINGS = 7;
/*     */   public static final int RESOURCE__TIME_STAMP = 8;
/*     */   public static final String OPTION_CIPHER = "CIPHER";
/*     */   public static final String OPTION_ZIP = "ZIP";
/*     */   public static final String OPTION_SAVE_ONLY_IF_CHANGED = "SAVE_ONLY_IF_CHANGED";
/*     */   public static final String OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER = "MEMORY_BUFFER";
/*     */   public static final String OPTION_SAVE_ONLY_IF_CHANGED_FILE_BUFFER = "FILE_BUFFER";
/*     */   
/*     */   ResourceSet getResourceSet();
/*     */   
/*     */   URI getURI();
/*     */   
/*     */   void setURI(URI paramURI);
/*     */   
/*     */   long getTimeStamp();
/*     */   
/*     */   void setTimeStamp(long paramLong);
/*     */   
/*     */   EList<EObject> getContents();
/*     */   
/*     */   TreeIterator<EObject> getAllContents();
/*     */   
/*     */   String getURIFragment(EObject paramEObject);
/*     */   
/*     */   EObject getEObject(String paramString);
/*     */   
/*     */   void save(Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   void load(Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   void save(OutputStream paramOutputStream, Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   void load(InputStream paramInputStream, Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   boolean isTrackingModification();
/*     */   
/*     */   void setTrackingModification(boolean paramBoolean);
/*     */   
/*     */   boolean isModified();
/*     */   
/*     */   void setModified(boolean paramBoolean);
/*     */   
/*     */   boolean isLoaded();
/*     */   
/*     */   void unload();
/*     */   
/*     */   void delete(Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   EList<Diagnostic> getErrors();
/*     */   
/*     */   EList<Diagnostic> getWarnings();
/*     */   
/*     */   public static interface Diagnostic
/*     */   {
/*     */     String getMessage();
/*     */     
/*     */     String getLocation();
/*     */     
/*     */     int getLine();
/*     */     
/*     */     int getColumn();
/*     */   }
/*     */   
/*     */   public static interface Factory
/*     */   {
/*     */     Resource createResource(URI param1URI);
/*     */     
/*     */     public static interface Descriptor
/*     */     {
/*     */       Resource.Factory createFactory();
/*     */     }
/*     */     
/*     */     public static interface Registry
/*     */     {
/*     */       public static final String DEFAULT_EXTENSION = "*";
/*     */       public static final String DEFAULT_CONTENT_TYPE_IDENTIFIER = "*";
/* 659 */       public static final Registry INSTANCE = (Registry)new ResourceFactoryRegistryImpl();
/*     */ 
/*     */       
/*     */       Resource.Factory getFactory(URI param2URI);
/*     */ 
/*     */       
/*     */       Resource.Factory getFactory(URI param2URI, String param2String);
/*     */       
/*     */       Map<String, Object> getProtocolToFactoryMap();
/*     */       
/*     */       Map<String, Object> getExtensionToFactoryMap();
/*     */       
/*     */       Map<String, Object> getContentTypeToFactoryMap();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IOWrappedException
/*     */     extends IOException
/*     */   {
/*     */     static final long serialVersionUID = 1L;
/*     */     
/*     */     public IOWrappedException(Exception exception) {
/* 681 */       super(exception.getLocalizedMessage());
/* 682 */       initCause(exception);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public IOWrappedException(Throwable throwable) {
/* 692 */       super(throwable.getLocalizedMessage());
/* 693 */       initCause(throwable);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Exception getWrappedException() {
/* 704 */       return (Exception)getCause();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Internal extends Resource {
/*     */     void attached(EObject param1EObject);
/*     */     
/*     */     void detached(EObject param1EObject);
/*     */     
/*     */     NotificationChain basicSetResourceSet(ResourceSet param1ResourceSet, NotificationChain param1NotificationChain);
/*     */     
/*     */     boolean isLoading();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\Resource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */