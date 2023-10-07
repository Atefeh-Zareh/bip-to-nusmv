/*     */ package org.eclipse.emf.ecore.plugin;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.core.runtime.IConfigurationElement;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GeneratedPackageRegistryReader
/*     */   extends RegistryReader
/*     */ {
/*     */   static final String TAG_PACKAGE = "package";
/*     */   static final String ATT_URI = "uri";
/*     */   static final String ATT_CLASS = "class";
/*     */   static final String ATT_GEN_MODEL = "genModel";
/*     */   protected Map<String, URI> ePackageNsURIToGenModelLocationMap;
/*     */   
/*     */   public GeneratedPackageRegistryReader() {
/*  47 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "generated_package");
/*     */   }
/*     */ 
/*     */   
/*     */   public GeneratedPackageRegistryReader(Map<String, URI> ePackageNsURIToGenModelLocationMap) {
/*  52 */     this();
/*  53 */     this.ePackageNsURIToGenModelLocationMap = ePackageNsURIToGenModelLocationMap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean readElement(IConfigurationElement element, boolean add) {
/*  59 */     if (element.getName().equals("package")) {
/*     */       
/*  61 */       String packageURI = element.getAttribute("uri");
/*  62 */       if (packageURI == null) {
/*     */         
/*  64 */         logMissingAttribute(element, "uri");
/*     */       }
/*  66 */       else if (element.getAttribute("class") == null) {
/*     */         
/*  68 */         logMissingAttribute(element, "class");
/*     */       } else {
/*  70 */         if (add) {
/*     */           
/*  72 */           Object previous = EPackage.Registry.INSTANCE.put(packageURI, new RegistryReader.EPackageDescriptor(element, "class"));
/*  73 */           if (previous instanceof RegistryReader.PluginClassDescriptor) {
/*     */             
/*  75 */             RegistryReader.PluginClassDescriptor descriptor = (RegistryReader.PluginClassDescriptor)previous;
/*  76 */             EcorePlugin.INSTANCE.log(
/*  77 */                 "Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package for '" + packageURI + "'");
/*     */           } 
/*     */           
/*  80 */           if (this.ePackageNsURIToGenModelLocationMap != null) {
/*     */             
/*  82 */             String genModel = element.getAttribute("genModel");
/*  83 */             if (genModel != null) {
/*     */               
/*  85 */               URI genModelURI = URI.createURI(genModel);
/*  86 */               if (genModelURI.isRelative())
/*     */               {
/*  88 */                 genModelURI = URI.createPlatformPluginURI(String.valueOf(element.getDeclaringExtension().getContributor().getName()) + "/" + genModel, true);
/*     */               }
/*  90 */               this.ePackageNsURIToGenModelLocationMap.put(packageURI, genModelURI);
/*     */             } 
/*     */           } 
/*  93 */           return true;
/*     */         } 
/*     */ 
/*     */         
/*  97 */         EPackage.Registry.INSTANCE.remove(packageURI);
/*  98 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\GeneratedPackageRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */