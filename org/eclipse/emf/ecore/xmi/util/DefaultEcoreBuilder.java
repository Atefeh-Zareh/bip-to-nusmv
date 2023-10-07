/*     */ package org.eclipse.emf.ecore.xmi.util;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.EcoreBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultEcoreBuilder
/*     */   implements EcoreBuilder
/*     */ {
/*     */   protected static final Class<?> XSD_ECORE_BUILDER_CLASS;
/*     */   protected static final Constructor<?> XSD_ECORE_BUILDER_CONSTRUCTOR;
/*     */   protected static final Method XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD;
/*     */   protected static final Map<?, ?> XSD_ECORE_BUILDER_OPTIONS;
/*     */   protected ExtendedMetaData extendedMetaData;
/*     */   
/*     */   static {
/*  49 */     Class<?> theXSDEcoreBuilderClass = null;
/*  50 */     Constructor<?> theXSDEcoreBuilderConstructor = null;
/*  51 */     Method theXSDEcoreBuilderGenerateResourcesMethod = null;
/*     */ 
/*     */     
/*     */     try {
/*  55 */       theXSDEcoreBuilderClass = CommonPlugin.loadClass("org.eclipse.xsd", "org.eclipse.xsd.ecore.XSDEcoreBuilder");
/*  56 */       theXSDEcoreBuilderConstructor = theXSDEcoreBuilderClass.getConstructor(new Class[] { ExtendedMetaData.class, Map.class });
/*  57 */       theXSDEcoreBuilderGenerateResourcesMethod = theXSDEcoreBuilderClass.getMethod("generateResources", new Class[] { Collection.class });
/*     */     }
/*  59 */     catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     XSD_ECORE_BUILDER_CLASS = theXSDEcoreBuilderClass;
/*  65 */     XSD_ECORE_BUILDER_CONSTRUCTOR = theXSDEcoreBuilderConstructor;
/*  66 */     XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD = theXSDEcoreBuilderGenerateResourcesMethod;
/*     */     
/*  68 */     Map<Object, Object> theXSDEcoreBuilderOptions = new HashMap<Object, Object>();
/*  69 */     theXSDEcoreBuilderOptions.put("REUSE_REGISTERED_PACKAGES", Boolean.TRUE);
/*  70 */     XSD_ECORE_BUILDER_OPTIONS = Collections.unmodifiableMap(theXSDEcoreBuilderOptions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultEcoreBuilder(ExtendedMetaData extendedMetaData) {
/*  77 */     this.extendedMetaData = extendedMetaData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtendedMetaData(ExtendedMetaData extendedMetaData) {
/*  82 */     this.extendedMetaData = extendedMetaData;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<? extends Resource> generate(URI uri) throws Exception {
/*  87 */     return generate(Collections.singleton(uri));
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<? extends Resource> generate(Map<String, URI> targetNamespaceToURI) throws Exception {
/*  92 */     if (targetNamespaceToURI != null && XSD_ECORE_BUILDER_CONSTRUCTOR != null && XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD != null) {
/*     */       
/*  94 */       Object ecoreBuilder = XSD_ECORE_BUILDER_CONSTRUCTOR.newInstance(new Object[] { this.extendedMetaData, XSD_ECORE_BUILDER_OPTIONS });
/*  95 */       Collection<? extends Resource> result = 
/*  96 */         (Collection<? extends Resource>)XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD.invoke(ecoreBuilder, new Object[] { targetNamespaceToURI.values() });
/*  97 */       return result;
/*     */     } 
/*  99 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<? extends Resource> generate(Collection<URI> uris) throws Exception {
/* 104 */     if (uris != null && XSD_ECORE_BUILDER_CONSTRUCTOR != null && XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD != null) {
/*     */       
/* 106 */       Object ecoreBuilder = XSD_ECORE_BUILDER_CONSTRUCTOR.newInstance(new Object[] { this.extendedMetaData, XSD_ECORE_BUILDER_OPTIONS });
/* 107 */       Collection<? extends Resource> result = 
/* 108 */         (Collection<? extends Resource>)XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD.invoke(ecoreBuilder, new Object[] { uris });
/* 109 */       return result;
/*     */     } 
/* 111 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xm\\util\DefaultEcoreBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */