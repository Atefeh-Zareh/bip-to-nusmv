/*     */ package org.eclipse.emf.ecore.plugin;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.core.runtime.IConfigurationElement;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ContentHandlerRegistryReader
/*     */   extends RegistryReader
/*     */ {
/*     */   static final String TAG_HANDLER = "contentHandler";
/*     */   static final String ATT_CLASS = "class";
/*     */   static final String ATT_PRIORITY = "priority";
/*     */   
/*     */   public ContentHandlerRegistryReader() {
/*  48 */     super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), "content_handler");
/*     */   }
/*     */   
/*  51 */   private static final Map<String, List<ContentHandler>> CONTRIBUTION_MAP = new HashMap<String, List<ContentHandler>>();
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean readElement(IConfigurationElement element, boolean add) {
/*  56 */     if (element.getName().equals("contentHandler")) {
/*     */       
/*  58 */       int priority = 0;
/*  59 */       if (element.getAttribute("priority") != null)
/*     */       {
/*  61 */         priority = Integer.parseInt(element.getAttribute("priority"));
/*     */       }
/*  63 */       String contributorClassName = element.getAttribute("class");
/*  64 */       if (contributorClassName == null)
/*     */       {
/*  66 */         logMissingAttribute(element, "class");
/*     */       }
/*     */       else
/*     */       {
/*  70 */         String contributorName = element.getContributor().getName();
/*  71 */         if (add) {
/*     */ 
/*     */           
/*     */           try {
/*     */             
/*  76 */             Class<ContentHandler> contributorHandlerClass = Platform.getBundle(element.getNamespaceIdentifier()).loadClass(contributorClassName);
/*  77 */             Map<String, String> parameters = new HashMap<String, String>(); byte b; int i; IConfigurationElement[] arrayOfIConfigurationElement;
/*  78 */             for (i = (arrayOfIConfigurationElement = element.getChildren("parameter")).length, b = 0; b < i; ) { IConfigurationElement parameter = arrayOfIConfigurationElement[b];
/*     */               
/*  80 */               parameters.put(parameter.getAttribute("name"), parameter.getAttribute("value")); b++; }
/*     */             
/*  82 */             ContentHandler contentHandler = 
/*  83 */               parameters.isEmpty() ? 
/*  84 */               contributorHandlerClass.newInstance() : 
/*  85 */               contributorHandlerClass.getConstructor(new Class[] { Map.class }).newInstance(new Object[] { parameters });
/*  86 */             ContentHandler.Registry.INSTANCE.put(priority, contentHandler);
/*  87 */             List<ContentHandler> list = CONTRIBUTION_MAP.get(contributorName);
/*  88 */             if (list == null)
/*     */             {
/*  90 */               CONTRIBUTION_MAP.put(contributorName, list = new ArrayList<ContentHandler>());
/*     */             }
/*  92 */             list.add(contentHandler);
/*     */           }
/*  94 */           catch (Exception exception) {
/*     */             
/*  96 */             EcorePlugin.INSTANCE.log(exception);
/*     */           } 
/*  98 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 102 */         List<ContentHandler> contributions = CONTRIBUTION_MAP.get(contributorName);
/* 103 */         if (contributions != null)
/*     */         {
/* 105 */           for (List<ContentHandler> values : (Iterable<List<ContentHandler>>)ContentHandler.Registry.INSTANCE.values())
/*     */           {
/* 107 */             values.removeAll(contributions);
/*     */           }
/*     */         }
/* 110 */         CONTRIBUTION_MAP.remove(contributorName);
/* 111 */         return true;
/*     */       }
/*     */     
/*     */     }
/* 115 */     else if (element.getName().equals("parameter")) {
/*     */       
/* 117 */       return true;
/*     */     } 
/*     */     
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\ContentHandlerRegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */