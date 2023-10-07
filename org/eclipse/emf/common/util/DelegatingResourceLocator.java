/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.PropertyResourceBundle;
/*     */ import java.util.ResourceBundle;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DelegatingResourceLocator
/*     */   implements ResourceLocator
/*     */ {
/*     */   protected URL baseURL;
/*     */   protected ResourceBundle untranslatedResourceBundle;
/*     */   protected ResourceBundle resourceBundle;
/*  61 */   protected Map<String, String> strings = new HashMap<String, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected Map<String, String> untranslatedStrings = new HashMap<String, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   protected Map<String, Object> images = new HashMap<String, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldTranslate = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   private static final URI DOT = URI.createURI(".");
/*     */   
/*     */   protected abstract ResourceLocator getPrimaryResourceLocator();
/*     */   
/*     */   protected abstract ResourceLocator[] getDelegateResourceLocators();
/*     */   
/*     */   public URL getBaseURL() {
/* 105 */     if (this.baseURL == null)
/*     */     {
/* 107 */       if (getPrimaryResourceLocator() == null) {
/*     */ 
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 113 */           Class<? extends DelegatingResourceLocator> theClass = (Class)getClass();
/* 114 */           URL pluginPropertiesURL = theClass.getResource("plugin.properties");
/* 115 */           if (pluginPropertiesURL == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 136 */             String className = theClass.getName();
/* 137 */             int index = className.lastIndexOf(".");
/* 138 */             URL classURL = theClass.getResource(String.valueOf((index == -1) ? className : className.substring(index + 1)) + ".class");
/* 139 */             URI uri = URI.createURI(classURL.toString());
/*     */ 
/*     */ 
/*     */             
/* 143 */             int count = 1;
/* 144 */             for (int i = 0; (i = className.indexOf('.', i)) != -1; i++)
/*     */             {
/* 146 */               count++;
/*     */             }
/* 148 */             uri = uri.trimSegments(count);
/*     */ 
/*     */ 
/*     */             
/* 152 */             if (URI.isArchiveScheme(uri.scheme())) {
/*     */               
/*     */               try {
/*     */ 
/*     */ 
/*     */                 
/* 158 */                 InputStream inputStream = (new URL(uri.appendSegment("plugin.properties").toString())).openStream();
/* 159 */                 inputStream.close();
/* 160 */                 this.baseURL = new URL(uri.toString());
/*     */               }
/* 162 */               catch (IOException exception) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 168 */                 uri = URI.createURI(uri.authority()).trimSegments(1);
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 174 */             if (this.baseURL == null) {
/*     */ 
/*     */ 
/*     */               
/* 178 */               String lastSegment = uri.lastSegment();
/* 179 */               if ("bin".equals(lastSegment) || "runtime".equals(lastSegment))
/*     */               {
/* 181 */                 uri = uri.trimSegments(1);
/*     */               }
/* 183 */               uri = uri.appendSegment("plugin.properties");
/*     */ 
/*     */ 
/*     */               
/*     */               try {
/* 188 */                 InputStream inputStream = (new URL(uri.toString())).openStream();
/* 189 */                 inputStream.close();
/* 190 */                 this.baseURL = new URL(DOT.resolve(uri).toString());
/*     */               }
/* 192 */               catch (IOException iOException) {}
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 200 */             if (this.baseURL == null)
/*     */             {
/* 202 */               String resourceName = 
/* 203 */                 (index == -1) ? 
/* 204 */                 "plugin.properties" : (
/* 205 */                 String.valueOf(className.substring(0, index + 1).replace('.', '/')) + "plugin.properties");
/* 206 */               throw new MissingResourceException("Missing properties: " + resourceName, theClass.getName(), "plugin.properties");
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 211 */             this.baseURL = new URL(DOT.resolve(URI.createURI(pluginPropertiesURL.toString())).toString());
/*     */           }
/*     */         
/* 214 */         } catch (IOException exception) {
/*     */           
/* 216 */           throw new WrappedException(exception);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 221 */         this.baseURL = getPrimaryResourceLocator().getBaseURL();
/*     */       } 
/*     */     }
/*     */     
/* 225 */     return this.baseURL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getImage(String key) {
/* 233 */     Object result = this.images.get(key);
/* 234 */     if (result == null) {
/*     */       
/* 236 */       ResourceLocator pluginResourceLocator = getPrimaryResourceLocator();
/* 237 */       if (pluginResourceLocator == null) {
/*     */ 
/*     */         
/*     */         try {
/* 241 */           result = doGetImage(key);
/*     */         }
/* 243 */         catch (MalformedURLException exception) {
/*     */           
/* 245 */           throw new WrappedException(exception);
/*     */         }
/* 247 */         catch (IOException exception) {
/*     */           
/* 249 */           result = delegatedGetImage(key);
/*     */         } 
/*     */       } else {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 256 */           result = pluginResourceLocator.getImage(key);
/*     */         }
/* 258 */         catch (MissingResourceException exception) {
/*     */           
/* 260 */           result = delegatedGetImage(key);
/*     */         } 
/*     */       } 
/*     */       
/* 264 */       this.images.put(key, result);
/*     */     } 
/*     */     
/* 267 */     return result;
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
/*     */   protected Object doGetImage(String key) throws IOException {
/* 279 */     URL url = new URL(getBaseURL() + "icons/" + key + extensionFor(key));
/* 280 */     InputStream inputStream = url.openStream();
/* 281 */     inputStream.close();
/* 282 */     return url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String extensionFor(String key) {
/* 292 */     String result = ".gif";
/* 293 */     int index = key.lastIndexOf('.');
/* 294 */     if (index != -1) {
/*     */       
/* 296 */       String extension = key.substring(index + 1);
/* 297 */       if ("png".equalsIgnoreCase(extension) || 
/* 298 */         "gif".equalsIgnoreCase(extension) || 
/* 299 */         "bmp".equalsIgnoreCase(extension) || 
/* 300 */         "ico".equalsIgnoreCase(extension) || 
/* 301 */         "jpg".equalsIgnoreCase(extension) || 
/* 302 */         "jpeg".equalsIgnoreCase(extension) || 
/* 303 */         "tif".equalsIgnoreCase(extension) || 
/* 304 */         "tiff".equalsIgnoreCase(extension))
/*     */       {
/* 306 */         result = "";
/*     */       }
/*     */     } 
/* 309 */     return result;
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
/*     */   protected Object delegatedGetImage(String key) throws MissingResourceException {
/* 321 */     ResourceLocator[] delegateResourceLocators = getDelegateResourceLocators();
/* 322 */     for (int i = 0; i < delegateResourceLocators.length; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 326 */         return delegateResourceLocators[i].getImage(key);
/*     */       }
/* 328 */       catch (MissingResourceException missingResourceException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 335 */     throw new MissingResourceException(
/* 336 */         CommonPlugin.INSTANCE.getString("_UI_ImageResourceNotFound_exception", new Object[] { key
/* 337 */           }), getClass().getName(), 
/* 338 */         key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldTranslate() {
/* 348 */     return this.shouldTranslate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShouldTranslate(boolean shouldTranslate) {
/* 358 */     this.shouldTranslate = shouldTranslate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key) {
/* 366 */     return getString(key, shouldTranslate());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key, boolean translate) {
/* 374 */     Map<String, String> stringMap = translate ? this.strings : this.untranslatedStrings;
/* 375 */     String result = stringMap.get(key);
/* 376 */     if (result == null) {
/*     */ 
/*     */       
/*     */       try {
/* 380 */         ResourceLocator pluginResourceLocator = getPrimaryResourceLocator();
/* 381 */         if (pluginResourceLocator == null)
/*     */         {
/* 383 */           result = doGetString(key, translate);
/*     */         }
/*     */         else
/*     */         {
/* 387 */           result = pluginResourceLocator.getString(key, translate);
/*     */         }
/*     */       
/* 390 */       } catch (MissingResourceException exception) {
/*     */         
/* 392 */         result = delegatedGetString(key, translate);
/*     */       } 
/*     */       
/* 395 */       stringMap.put(key, result);
/*     */     } 
/*     */     
/* 398 */     return result;
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
/*     */   protected String doGetString(String key, boolean translate) throws MissingResourceException {
/* 410 */     ResourceBundle bundle = translate ? this.resourceBundle : this.untranslatedResourceBundle;
/* 411 */     if (bundle == null) {
/*     */       
/* 413 */       String packageName = getClass().getName();
/* 414 */       int index = packageName.lastIndexOf(".");
/* 415 */       if (index != -1)
/*     */       {
/* 417 */         packageName = packageName.substring(0, index);
/*     */       }
/* 419 */       if (translate) {
/*     */         
/*     */         try
/*     */         {
/* 423 */           bundle = this.resourceBundle = ResourceBundle.getBundle(String.valueOf(packageName) + ".plugin");
/*     */         }
/* 425 */         catch (MissingResourceException exception)
/*     */         {
/*     */           
/*     */           try {
/*     */ 
/*     */ 
/*     */             
/* 432 */             InputStream inputStream = (new URL(String.valueOf(getBaseURL().toString()) + "plugin.properties")).openStream();
/* 433 */             bundle = this.untranslatedResourceBundle = this.resourceBundle = new PropertyResourceBundle(inputStream);
/* 434 */             inputStream.close();
/*     */           }
/* 436 */           catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */           
/* 440 */           if (bundle == null)
/*     */           {
/* 442 */             throw exception;
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 448 */         String resourceName = String.valueOf(getBaseURL().toString()) + "plugin.properties";
/*     */         
/*     */         try {
/* 451 */           InputStream inputStream = (new URL(resourceName)).openStream();
/* 452 */           bundle = this.untranslatedResourceBundle = new PropertyResourceBundle(inputStream);
/* 453 */           inputStream.close();
/*     */         }
/* 455 */         catch (IOException ioException) {
/*     */           
/* 457 */           throw new MissingResourceException("Missing properties: " + resourceName, getClass().getName(), "plugin.properties");
/*     */         } 
/*     */       } 
/*     */     } 
/* 461 */     return bundle.getString(key);
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
/*     */   protected String delegatedGetString(String key, boolean translate) {
/* 473 */     ResourceLocator[] delegateResourceLocators = getDelegateResourceLocators();
/* 474 */     for (int i = 0; i < delegateResourceLocators.length; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 478 */         return delegateResourceLocators[i].getString(key, translate);
/*     */       }
/* 480 */       catch (MissingResourceException missingResourceException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 487 */     throw new MissingResourceException(
/* 488 */         MessageFormat.format("The string resource ''{0}'' could not be located", new Object[] { key
/* 489 */           }), getClass().getName(), 
/* 490 */         key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key, Object[] substitutions) {
/* 498 */     return getString(key, substitutions, shouldTranslate());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key, Object[] substitutions, boolean translate) {
/* 506 */     return MessageFormat.format(getString(key, translate), substitutions);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\DelegatingResourceLocator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */