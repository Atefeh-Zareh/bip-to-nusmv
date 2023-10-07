/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.XMIPlugin;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenericXMLResourceImpl
/*     */   extends XMLResourceImpl
/*     */ {
/*     */   protected static final Class<?> xsdEcoreBuilderClass;
/*     */   protected static final Constructor<?> xsdEcoreBuilderConstructor;
/*     */   protected static final Method xsdEcoreBuilderGenerateResourcesMethod;
/*     */   protected static final Map<?, ?> xsdEcoreBuilderOptions;
/*     */   
/*     */   static {
/*  51 */     Class<?> theXSDEcoreBuilderClass = null;
/*  52 */     Constructor<?> theXSDEcoreBuilderConstructor = null;
/*  53 */     Method theXSDEcoreBuilderGenerateResourcesMethod = null;
/*     */ 
/*     */     
/*     */     try {
/*  57 */       theXSDEcoreBuilderClass = CommonPlugin.loadClass("org.eclipse.xsd", "org.eclipse.xsd.ecore.XSDEcoreBuilder");
/*  58 */       theXSDEcoreBuilderConstructor = theXSDEcoreBuilderClass.getConstructor(new Class[] { ExtendedMetaData.class, Map.class });
/*  59 */       theXSDEcoreBuilderGenerateResourcesMethod = theXSDEcoreBuilderClass.getMethod("generateResources", new Class[] { Collection.class });
/*     */     }
/*  61 */     catch (Exception exception) {
/*     */       
/*  63 */       XMIPlugin.INSTANCE.log(exception);
/*  64 */       exception.printStackTrace();
/*     */     } 
/*     */     
/*  67 */     xsdEcoreBuilderClass = theXSDEcoreBuilderClass;
/*  68 */     xsdEcoreBuilderConstructor = theXSDEcoreBuilderConstructor;
/*  69 */     xsdEcoreBuilderGenerateResourcesMethod = theXSDEcoreBuilderGenerateResourcesMethod;
/*     */     
/*  71 */     Map<Object, Object> theXSDEcoreBuilderOptions = new HashMap<Object, Object>();
/*  72 */     theXSDEcoreBuilderOptions.put("REUSE_REGISTERED_PACKAGES", Boolean.TRUE);
/*  73 */     xsdEcoreBuilderOptions = Collections.unmodifiableMap(theXSDEcoreBuilderOptions);
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericXMLResourceImpl(URI uri) {
/*  78 */     super(uri);
/*     */   }
/*     */   
/*     */   protected static class GenericXMLLoadImpl
/*     */     extends XMLLoadImpl
/*     */   {
/*     */     protected GenericXMLLoadImpl(XMLHelper helper) {
/*  85 */       super(helper);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected DefaultHandler makeDefaultHandler() {
/*  91 */       return new GenericXMLResourceImpl.GenericSAXXMLHandler(this.resource, this.helper, this.options);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class GenericSAXXMLHandler
/*     */     extends SAXXMLHandler
/*     */   {
/*     */     protected Object xsdEcoreBuilder;
/*     */     protected Collection<? extends Resource> generatedResources;
/*     */     
/*     */     protected GenericSAXXMLHandler(XMLResource xmlResource, XMLHelper helper, Map<?, ?> options) {
/* 103 */       super(xmlResource, helper, options);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleTopLocations(String prefix, String name) {
/* 109 */       if (this.urisToLocations != null && GenericXMLResourceImpl.xsdEcoreBuilderConstructor != null && GenericXMLResourceImpl.xsdEcoreBuilderGenerateResourcesMethod != null) {
/*     */         
/*     */         try {
/*     */           
/* 113 */           this.xsdEcoreBuilder = GenericXMLResourceImpl.xsdEcoreBuilderConstructor.newInstance(new Object[] { this.extendedMetaData, GenericXMLResourceImpl.xsdEcoreBuilderOptions });
/* 114 */           Collection<? extends Resource> newGeneratedResources = 
/* 115 */             (Collection<? extends Resource>)GenericXMLResourceImpl.xsdEcoreBuilderGenerateResourcesMethod.invoke(
/* 116 */               this.xsdEcoreBuilder, new Object[] { this.urisToLocations.values() });
/* 117 */           this.generatedResources = newGeneratedResources;
/*     */         
/*     */         }
/* 120 */         catch (Exception exception) {
/*     */           
/* 122 */           XMIPlugin.INSTANCE.log(exception);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 128 */       String namespaceURI = this.helper.getURI(prefix);
/* 129 */       if (this.extendedMetaData.getPackage(namespaceURI) == null)
/*     */       {
/* 131 */         this.extendedMetaData.demandFeature(namespaceURI, name, true);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected EPackage handleMissingPackage(String uriString) {
/* 138 */       return this.objects.isEmpty() ? this.extendedMetaData.demandPackage(uriString) : super.handleMissingPackage(uriString);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLLoad createXMLLoad() {
/* 145 */     return new GenericXMLLoadImpl(createXMLHelper());
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\GenericXMLResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */