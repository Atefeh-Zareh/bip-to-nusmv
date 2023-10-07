/*     */ package org.eclipse.emf.mapping.ecore2xml;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import org.eclipse.core.runtime.IConfigurationElement;
/*     */ import org.eclipse.core.runtime.IExtensionPoint;
/*     */ import org.eclipse.core.runtime.IExtensionRegistry;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLRegistryImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Ecore2XMLRegistry
/*     */   extends Map<String, Object>
/*     */ {
/*     */   public static interface Descriptor
/*     */   {
/*     */     XMLResource.XMLMap getXMLMap();
/*     */   }
/*     */   
/*     */   public static class Reader
/*     */   {
/*     */     protected static final String TAG_ECORE2XML = "ecore2xml";
/*     */     protected static final String ATT_URI = "uri";
/*     */     protected static final String ATT_XMLMAP = "xmlmap";
/*     */     private final IExtensionRegistry extensionRegistry;
/*     */     private final String namespace;
/*     */     private final String extensionPointID;
/*     */     
/*     */     protected static class Ecore2XMLDescriptor
/*     */       implements Ecore2XMLRegistry.Descriptor
/*     */     {
/*     */       private final IConfigurationElement element;
/*  55 */       private XMLResource.XMLMap xmlMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected Ecore2XMLDescriptor(IConfigurationElement element) {
/*  61 */         this.element = element;
/*     */       }
/*     */ 
/*     */       
/*     */       public XMLResource.XMLMap getXMLMap() {
/*  66 */         if (this.xmlMap == null) {
/*     */           
/*     */           try {
/*     */             
/*  70 */             this.xmlMap = 
/*  71 */               (XMLResource.XMLMap)EcoreUtil.getObjectByType(
/*  72 */                 (Collection)(new ResourceSetImpl()).getResource(URI.createURI(this.element.getAttribute("xmlmap")), true).getContents(), 
/*  73 */                 (EClassifier)Ecore2XMLPackage.eINSTANCE.getXMLMap());
/*     */           }
/*  75 */           catch (Exception e) {
/*     */             
/*  77 */             throw new WrappedException(e);
/*     */           } 
/*     */         }
/*     */         
/*  81 */         return this.xmlMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Reader(IExtensionRegistry extensionRegistry, String namespace, String extensionPointID) {
/* 101 */       this.extensionRegistry = extensionRegistry;
/* 102 */       this.namespace = namespace;
/* 103 */       this.extensionPointID = extensionPointID;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readElement(IConfigurationElement element) {
/* 108 */       if ("ecore2xml".equals(element.getName())) {
/*     */         
/* 110 */         String uri = element.getAttribute("uri");
/*     */         
/* 112 */         if (uri != null && element.getAttribute("xmlmap") != null)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 118 */           Ecore2XMLRegistry.INSTANCE.put(uri, new Ecore2XMLDescriptor(element));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void readRegistry() {
/* 129 */       IExtensionPoint extensionPoint = this.extensionRegistry.getExtensionPoint(this.namespace, this.extensionPointID);
/*     */       
/* 131 */       if (extensionPoint != null) {
/*     */         
/* 133 */         IConfigurationElement[] elements = extensionPoint.getConfigurationElements();
/*     */         
/* 135 */         for (int i = 0; i < elements.length; i++)
/*     */         {
/* 137 */           readElement(elements[i]);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 144 */   public static final Ecore2XMLRegistry INSTANCE = (Ecore2XMLRegistry)new Ecore2XMLRegistryImpl();
/*     */   
/*     */   XMLResource.XMLMap getXMLMap(String paramString);
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\Ecore2XMLRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */