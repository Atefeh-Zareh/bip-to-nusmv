/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceEntityHandlerImpl
/*     */   extends URIHandlerImpl
/*     */   implements XMLResource.ResourceEntityHandler
/*     */ {
/*     */   protected String entityName;
/*  35 */   protected int count = 1;
/*  36 */   protected Map<String, String> nameToValueMap = new LinkedHashMap<String, String>();
/*  37 */   protected Map<String, String> valueToNameMap = new LinkedHashMap<String, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceEntityHandlerImpl(String entityName) {
/*  46 */     this.entityName = entityName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI deresolve(URI uri) {
/*  56 */     return uri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URI doDeresolve(URI uri) {
/*  65 */     return super.deresolve(uri);
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
/*     */   public void setBaseURI(URI uri) {
/*  77 */     boolean unchanged = (uri == null) ? ((this.baseURI == null)) : uri.equals(this.baseURI);
/*  78 */     if (!unchanged)
/*     */     {
/*  80 */       for (Map.Entry<String, String> entry : this.nameToValueMap.entrySet())
/*     */       {
/*  82 */         entry.setValue(resolve(URI.createURI(entry.getValue())).toString());
/*     */       }
/*     */     }
/*  85 */     doSetBaseURI(uri);
/*  86 */     if (!unchanged) {
/*     */       
/*  88 */       this.valueToNameMap.clear();
/*  89 */       for (Map.Entry<String, String> entry : this.nameToValueMap.entrySet()) {
/*     */         
/*  91 */         this.valueToNameMap.put(entry.getValue(), entry.getKey());
/*  92 */         entry.setValue(doDeresolve(URI.createURI(entry.getValue())).toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doSetBaseURI(URI uri) {
/* 103 */     super.setBaseURI(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 111 */     this.valueToNameMap.clear();
/* 112 */     this.nameToValueMap.clear();
/* 113 */     this.count = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEntityName(String entityValue) {
/* 118 */     String result = this.valueToNameMap.get(entityValue);
/* 119 */     if (result == null) {
/*     */       
/* 121 */       result = generateEntityName(entityValue);
/* 122 */       if (result != null) {
/*     */         
/* 124 */         this.valueToNameMap.put(entityValue, result);
/* 125 */         this.nameToValueMap.put(result, entityValue);
/*     */       } 
/*     */     } 
/* 128 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String generateEntityName(String entityValue) {
/* 138 */     for (String result = String.valueOf(this.entityName) + this.count;; result = String.valueOf(this.entityName) + ++this.count) {
/*     */       
/* 140 */       if (!this.nameToValueMap.containsKey(result))
/*     */       {
/* 142 */         return result;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEntity(String entityName, String entityValue) {
/* 149 */     this.nameToValueMap.put(entityName, entityValue);
/* 150 */     this.valueToNameMap.put(entityValue, entityName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getNameToValueMap() {
/* 157 */     Map<String, String> result = new LinkedHashMap<String, String>();
/* 158 */     for (Map.Entry<String, String> entry : this.nameToValueMap.entrySet())
/*     */     {
/* 160 */       result.put(entry.getKey(), doDeresolve(URI.createURI(entry.getValue())).toString());
/*     */     }
/* 162 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\ResourceEntityHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */