/*     */ package org.eclipse.emf.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.PropertyResourceBundle;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.jar.Manifest;
/*     */ import org.eclipse.core.runtime.ILog;
/*     */ import org.eclipse.core.runtime.IPluginDescriptor;
/*     */ import org.eclipse.core.runtime.IStatus;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.core.runtime.Plugin;
/*     */ import org.eclipse.core.runtime.Status;
/*     */ import org.eclipse.emf.common.util.DelegatingResourceLocator;
/*     */ import org.eclipse.emf.common.util.Logger;
/*     */ import org.eclipse.emf.common.util.ResourceLocator;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.osgi.framework.Bundle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EMFPlugin
/*     */   extends DelegatingResourceLocator
/*     */   implements ResourceLocator, Logger
/*     */ {
/*     */   public static final boolean IS_ECLIPSE_RUNNING;
/*     */   public static final boolean IS_RESOURCES_BUNDLE_AVAILABLE;
/*     */   protected ResourceLocator[] delegateResourceLocators;
/*     */   
/*     */   static {
/*  67 */     boolean result = false;
/*     */     
/*     */     try {
/*  70 */       result = Platform.isRunning();
/*     */     }
/*  72 */     catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/*  76 */     IS_ECLIPSE_RUNNING = result;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     result = false;
/*  83 */     if (IS_ECLIPSE_RUNNING) {
/*     */       
/*     */       try {
/*     */         
/*  87 */         Bundle resourcesBundle = Platform.getBundle("org.eclipse.core.resources");
/*  88 */         result = (resourcesBundle != null && (resourcesBundle.getState() & 0x2C) != 0);
/*     */       }
/*  90 */       catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  95 */     IS_RESOURCES_BUNDLE_AVAILABLE = result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMFPlugin(ResourceLocator[] delegateResourceLocators) {
/* 102 */     this.delegateResourceLocators = delegateResourceLocators;
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
/*     */   protected final ResourceLocator getPrimaryResourceLocator() {
/* 114 */     return getPluginResourceLocator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocator[] getDelegateResourceLocators() {
/* 120 */     return this.delegateResourceLocators;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Logger getPluginLogger() {
/* 129 */     return (Logger)getPluginResourceLocator();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSymbolicName() {
/* 134 */     ResourceLocator resourceLocator = getPluginResourceLocator();
/* 135 */     if (resourceLocator instanceof InternalEclipsePlugin)
/*     */     {
/* 137 */       return ((InternalEclipsePlugin)resourceLocator).getSymbolicName();
/*     */     }
/*     */ 
/*     */     
/* 141 */     String result = getClass().getName();
/* 142 */     return result.substring(0, result.lastIndexOf('.'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(Object logEntry) {
/* 151 */     Logger logger = getPluginLogger();
/* 152 */     if (logger == null) {
/*     */       
/* 154 */       if (logEntry instanceof Throwable)
/*     */       {
/* 156 */         ((Throwable)logEntry).printStackTrace(System.err);
/*     */       }
/*     */       else
/*     */       {
/* 160 */         System.err.println(logEntry);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 165 */       logger.log(logEntry);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class EclipsePlugin
/*     */     extends Plugin
/*     */     implements ResourceLocator, Logger, InternalEclipsePlugin
/*     */   {
/*     */     protected EMFPlugin.InternalHelper helper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EclipsePlugin() {
/* 186 */       this.helper = new EMFPlugin.InternalHelper(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public EclipsePlugin(IPluginDescriptor descriptor) {
/* 197 */       super(descriptor);
/* 198 */       this.helper = new EMFPlugin.InternalHelper(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getSymbolicName() {
/* 206 */       return this.helper.getSymbolicName();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public URL getBaseURL() {
/* 214 */       return this.helper.getBaseURL();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getImage(String key) {
/*     */       try {
/* 224 */         return doGetImage(key);
/*     */       }
/* 226 */       catch (MalformedURLException exception) {
/*     */         
/* 228 */         throw new WrappedException(exception);
/*     */       }
/* 230 */       catch (IOException exception) {
/*     */ 
/*     */         
/* 233 */         throw new MissingResourceException(
/* 234 */             CommonPlugin.INSTANCE.getString("_UI_StringResourceNotFound_exception", new Object[] { key
/* 235 */               }), getClass().getName(), 
/* 236 */             key);
/*     */       } 
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
/*     */     protected Object doGetImage(String key) throws IOException {
/* 249 */       return this.helper.getImage(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String key) {
/* 254 */       return this.helper.getString(key, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String key, boolean translate) {
/* 259 */       return this.helper.getString(key, translate);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String key, Object[] substitutions) {
/* 264 */       return this.helper.getString(key, substitutions, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String key, Object[] substitutions, boolean translate) {
/* 269 */       return this.helper.getString(key, substitutions, translate);
/*     */     }
/*     */ 
/*     */     
/*     */     public void log(Object logEntry) {
/* 274 */       this.helper.log(logEntry);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface InternalEclipsePlugin
/*     */   {
/*     */     String getSymbolicName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class InternalHelper
/*     */   {
/*     */     protected Plugin plugin;
/*     */ 
/*     */     
/*     */     protected ResourceBundle resourceBundle;
/*     */ 
/*     */     
/*     */     protected ResourceBundle untranslatedResourceBundle;
/*     */ 
/*     */     
/*     */     public InternalHelper(Plugin plugin) {
/* 299 */       this.plugin = plugin;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Bundle getBundle() {
/* 304 */       return this.plugin.getBundle();
/*     */     }
/*     */ 
/*     */     
/*     */     protected ILog getLog() {
/* 309 */       return this.plugin.getLog();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getSymbolicName() {
/* 317 */       return getBundle().getSymbolicName();
/*     */     }
/*     */ 
/*     */     
/*     */     public URL getBaseURL() {
/* 322 */       return getBundle().getEntry("/");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getImage(String key) throws IOException {
/* 333 */       URL url = new URL(getBaseURL() + "icons/" + key + EMFPlugin.extensionFor(key));
/* 334 */       InputStream inputStream = url.openStream();
/* 335 */       inputStream.close();
/* 336 */       return url;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String key, boolean translate) {
/* 341 */       ResourceBundle bundle = translate ? this.resourceBundle : this.untranslatedResourceBundle;
/* 342 */       if (bundle == null)
/*     */       {
/* 344 */         if (translate) {
/*     */           
/* 346 */           bundle = this.resourceBundle = Platform.getResourceBundle(getBundle());
/*     */         }
/*     */         else {
/*     */           
/* 350 */           String resourceName = String.valueOf(getBaseURL().toString()) + "plugin.properties";
/*     */           
/*     */           try {
/* 353 */             InputStream inputStream = (new URL(resourceName)).openStream();
/* 354 */             bundle = this.untranslatedResourceBundle = new PropertyResourceBundle(inputStream);
/* 355 */             inputStream.close();
/*     */           }
/* 357 */           catch (IOException ioException) {
/*     */             
/* 359 */             throw new MissingResourceException("Missing properties: " + resourceName, getClass().getName(), "plugin.properties");
/*     */           } 
/*     */         } 
/*     */       }
/* 363 */       return bundle.getString(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String key, Object[] substitutions, boolean translate) {
/* 368 */       return MessageFormat.format(getString(key, translate), substitutions);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void log(Object logEntry) {
/* 374 */       if (logEntry instanceof IStatus) {
/*     */         
/* 376 */         IStatus status = (IStatus)logEntry;
/* 377 */         getLog().log(status);
/*     */       }
/*     */       else {
/*     */         
/* 381 */         if (logEntry == null)
/*     */         {
/* 383 */           logEntry = (new RuntimeException(getString("_UI_NullLogEntry_exception", true))).fillInStackTrace();
/*     */         }
/*     */         
/* 386 */         if (logEntry instanceof Throwable) {
/*     */           
/* 388 */           Throwable throwable = (Throwable)logEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 393 */           String message = throwable.getLocalizedMessage();
/* 394 */           if (message == null)
/*     */           {
/* 396 */             message = "";
/*     */           }
/*     */           
/* 399 */           getLog().log((IStatus)new Status(2, getBundle().getSymbolicName(), 0, message, throwable));
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 406 */           getLog().log((IStatus)new Status(2, getBundle().getSymbolicName(), 0, logEntry.toString(), null));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 416 */       String[] relativePath = { "META-INF", "MANIFEST.MF" };
/* 417 */       Class<?> theClass = (args.length > 0) ? Class.forName(args[0]) : EMFPlugin.class;
/*     */       
/* 419 */       String className = theClass.getName();
/* 420 */       int index = className.lastIndexOf(".");
/* 421 */       URL classURL = theClass.getResource(String.valueOf((index == -1) ? className : className.substring(index + 1)) + ".class");
/* 422 */       URI uri = URI.createURI(classURL.toString());
/*     */ 
/*     */ 
/*     */       
/* 426 */       int count = 1;
/* 427 */       for (int i = 0; (i = className.indexOf('.', i)) != -1; i++)
/*     */       {
/* 429 */         count++;
/*     */       }
/* 431 */       uri = uri.trimSegments(count);
/*     */       
/* 433 */       URL manifestURL = null;
/*     */ 
/*     */ 
/*     */       
/* 437 */       if (URI.isArchiveScheme(uri.scheme())) {
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */           
/* 443 */           String manifestURI = uri.appendSegments(relativePath).toString();
/* 444 */           InputStream inputStream = (new URL(manifestURI)).openStream();
/* 445 */           inputStream.close();
/* 446 */           manifestURL = new URL(manifestURI);
/*     */         }
/* 448 */         catch (IOException exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 454 */           uri = URI.createURI(uri.authority()).trimSegments(1);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 460 */       if (manifestURL == null) {
/*     */ 
/*     */ 
/*     */         
/* 464 */         String lastSegment = uri.lastSegment();
/* 465 */         if ("bin".equals(lastSegment) || "runtime".equals(lastSegment))
/*     */         {
/* 467 */           uri = uri.trimSegments(1);
/*     */         }
/* 469 */         uri = uri.appendSegments(relativePath);
/* 470 */         manifestURL = new URL(uri.toString());
/*     */       } 
/*     */       
/* 473 */       Manifest manifest = new Manifest(manifestURL.openStream());
/* 474 */       String symbolicName = manifest.getMainAttributes().getValue("Bundle-SymbolicName");
/* 475 */       if (symbolicName != null) {
/*     */         
/* 477 */         int end = symbolicName.indexOf(";");
/* 478 */         if (end != -1)
/*     */         {
/* 480 */           symbolicName = symbolicName.substring(0, end);
/*     */         }
/* 482 */         System.out.println("Bundle-SymbolicName=" + symbolicName + " Bundle-Version=" + manifest.getMainAttributes().getValue("Bundle-Version"));
/*     */         
/*     */         return;
/*     */       } 
/* 486 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     System.err.println("No Bundle information found");
/*     */   }
/*     */   
/*     */   public abstract ResourceLocator getPluginResourceLocator();
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\EMFPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */