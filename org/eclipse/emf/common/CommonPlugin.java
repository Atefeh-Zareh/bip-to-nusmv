/*     */ package org.eclipse.emf.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.text.Collator;
/*     */ import java.util.Comparator;
/*     */ import java.util.Locale;
/*     */ import org.eclipse.core.runtime.FileLocator;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.emf.common.util.ResourceLocator;
/*     */ import org.eclipse.emf.common.util.URI;
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
/*     */ public final class CommonPlugin
/*     */   extends EMFPlugin
/*     */ {
/*  51 */   public static final CommonPlugin INSTANCE = new CommonPlugin();
/*     */ 
/*     */ 
/*     */   
/*     */   private static Implementation plugin;
/*     */ 
/*     */   
/*     */   private static final Method COLLATOR_GET_INSTANCE_METHOD;
/*     */ 
/*     */ 
/*     */   
/*     */   private CommonPlugin() {
/*  63 */     super(new ResourceLocator[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocator getPluginResourceLocator() {
/*  69 */     return plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Implementation getPlugin() {
/*  78 */     return plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static URI asLocalURI(URI uri) {
/*  86 */     return (plugin == null) ? uri : Implementation.asLocalURI(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static URI resolve(URI uri) {
/*  94 */     return (plugin == null) ? uri : Implementation.resolve(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class<?> loadClass(String pluginID, String className) throws ClassNotFoundException {
/* 102 */     return (plugin == null) ? Class.forName(className) : Implementation.loadClass(pluginID, className);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 108 */     Method collatorGetInstanceMethod = null;
/*     */     
/*     */     try {
/* 111 */       Class<?> collatorClass = loadClass("com.ibm.icu", "com.ibm.icu.text.Collator");
/* 112 */       collatorGetInstanceMethod = collatorClass.getMethod("getInstance", new Class[] { Locale.class });
/*     */     }
/* 114 */     catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/* 118 */     COLLATOR_GET_INSTANCE_METHOD = collatorGetInstanceMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comparator<String> getComparator() {
/* 127 */     return getComparator(Locale.getDefault());
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
/*     */   public Comparator<String> getComparator(Locale locale) {
/* 139 */     if (COLLATOR_GET_INSTANCE_METHOD != null) {
/*     */       
/*     */       try {
/*     */         
/* 143 */         return (Comparator<String>)COLLATOR_GET_INSTANCE_METHOD.invoke(null, new Object[] { locale });
/*     */       }
/* 145 */       catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 150 */     return Collator.getInstance(locale);
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
/*     */   public static class Implementation
/*     */     extends EMFPlugin.EclipsePlugin
/*     */   {
/*     */     public Implementation() {
/* 167 */       CommonPlugin.plugin = this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static URI asLocalURI(URI uri) {
/*     */       try {
/* 177 */         String fragment = uri.fragment();
/* 178 */         URL url = FileLocator.toFileURL(new URL(uri.trimFragment().toString()));
/* 179 */         return fix(url, fragment);
/*     */       }
/* 181 */       catch (IOException iOException) {
/*     */ 
/*     */ 
/*     */         
/* 185 */         return uri;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static URI resolve(URI uri) {
/* 193 */       String fragment = uri.fragment();
/* 194 */       URI uriWithoutFragment = uri.trimFragment();
/* 195 */       String uriWithoutFragmentToString = uriWithoutFragment.toString();
/*     */       
/* 197 */       URL url = null;
/*     */       
/*     */       try {
/* 200 */         url = FileLocator.resolve(new URL(uriWithoutFragmentToString));
/*     */       }
/* 202 */       catch (IOException exception1) {
/*     */ 
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 208 */           uriWithoutFragmentToString = URI.decode(uriWithoutFragmentToString);
/* 209 */           url = FileLocator.resolve(new URL(uriWithoutFragmentToString));
/*     */         }
/* 211 */         catch (IOException iOException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 216 */       if (url != null) {
/*     */         
/*     */         try {
/*     */           
/* 220 */           return fix(url, fragment);
/*     */         }
/* 222 */         catch (IOException iOException) {}
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       return uri;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static URI fix(URL url, String fragment) throws IOException {
/* 237 */       URI result = 
/* 238 */         "file".equalsIgnoreCase(url.getProtocol()) ? 
/* 239 */         URI.createFileURI(URI.decode(url.getFile())) : 
/* 240 */         URI.createURI(url.toString());
/* 241 */       if (fragment != null)
/*     */       {
/* 243 */         result = result.appendFragment(fragment);
/*     */       }
/* 245 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static Class<?> loadClass(String pluginID, String className) throws ClassNotFoundException {
/* 253 */       Bundle bundle = Platform.getBundle(pluginID);
/* 254 */       if (bundle == null)
/*     */       {
/* 256 */         throw new ClassNotFoundException(String.valueOf(className) + " cannot be loaded because because bundle " + pluginID + " cannot be resolved");
/*     */       }
/*     */ 
/*     */       
/* 260 */       return bundle.loadClass(className);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\CommonPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */