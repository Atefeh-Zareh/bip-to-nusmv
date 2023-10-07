/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class URIMappingRegistryImpl
/*     */   extends BasicEMap<URI, URI>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  39 */   public static final URIMappingRegistryImpl INSTANCE = new URIMappingRegistryImpl();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected BasicEList<List<BasicEMap.Entry<URI, URI>>> prefixMaps = new BasicEList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEMap.Entry<URI, URI> newEntry(int hash, URI key, URI value) {
/*  61 */     validateKey(key);
/*  62 */     validateValue(value);
/*  63 */     return (BasicEMap.Entry<URI, URI>)new MappingEntryImpl(hash, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MappingEntryImpl
/*     */     extends BasicEMap<URI, URI>.EntryImpl
/*     */   {
/*     */     public boolean isPrefixMapEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MappingEntryImpl(int hash, URI key, URI value) {
/*  82 */       super(URIMappingRegistryImpl.this, hash, key, value);
/*  83 */       determineEntryType();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void determineEntryType() {
/*  91 */       this.isPrefixMapEntry = (((URI)this.key).isPrefix() && ((URI)this.value).isPrefix());
/*     */     }
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
/*     */   public URI getURI(URI uri) {
/* 105 */     URI result = (URI)get(uri);
/* 106 */     if (result == null) {
/*     */       
/* 108 */       if (this.prefixMaps != null)
/*     */       {
/* 110 */         for (int i = Math.min(this.prefixMaps.size() - 1, uri.segmentCount()); i >= 0; i--) {
/*     */           
/* 112 */           List<BasicEMap.Entry<URI, URI>> prefixes = (List<BasicEMap.Entry<URI, URI>>)this.prefixMaps.get(i);
/* 113 */           for (int j = prefixes.size() - 1; j >= 0; j--) {
/*     */             
/* 115 */             BasicEMap.Entry<URI, URI> entry = prefixes.get(j);
/* 116 */             result = uri.replacePrefix((URI)entry.getKey(), (URI)entry.getValue());
/*     */             
/* 118 */             if (result != null)
/*     */             {
/* 120 */               return result;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 126 */       result = delegatedGetURI(uri);
/*     */     } 
/*     */     
/* 129 */     return result;
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
/*     */   protected URI delegatedGetURI(URI uri) {
/* 143 */     return uri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class URIMapImpl
/*     */     extends BasicEMap<URI, URI>.DelegatingMap
/*     */     implements URIConverterImpl.URIMap
/*     */   {
/*     */     public URIMapImpl() {
/* 157 */       super(URIMappingRegistryImpl.this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public URI getURI(URI uri) {
/* 168 */       return URIMappingRegistryImpl.this.getURI(uri);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<URI, URI> map() {
/* 178 */     if (this.view == null)
/*     */     {
/* 180 */       this.view = new BasicEMap.View();
/*     */     }
/* 182 */     if (this.view.map == null)
/*     */     {
/* 184 */       this.view.map = new URIMapImpl();
/*     */     }
/*     */     
/* 187 */     return this.view.map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateKey(URI key) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateValue(URI value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void didAdd(BasicEMap.Entry<URI, URI> entry) {
/* 214 */     if (((MappingEntryImpl)entry).isPrefixMapEntry) {
/*     */       
/* 216 */       int length = ((URI)entry.getKey()).segmentCount();
/* 217 */       if (this.prefixMaps == null)
/*     */       {
/* 219 */         this.prefixMaps = new BasicEList();
/*     */       }
/*     */       
/* 222 */       for (int i = this.prefixMaps.size() - 1; i <= length; i++)
/*     */       {
/* 224 */         this.prefixMaps.add(new BasicEList());
/*     */       }
/*     */       
/* 227 */       ((List<BasicEMap.Entry<URI, URI>>)this.prefixMaps.get(length)).add(entry);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void didModify(BasicEMap.Entry<URI, URI> entry, URI oldValue) {
/* 237 */     didRemove(entry);
/* 238 */     ((MappingEntryImpl)entry).determineEntryType();
/* 239 */     didAdd(entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void didRemove(BasicEMap.Entry<URI, URI> entry) {
/* 248 */     if (((MappingEntryImpl)entry).isPrefixMapEntry) {
/*     */       
/* 250 */       int length = ((URI)entry.getKey()).segmentCount();
/* 251 */       ((List)this.prefixMaps.get(length)).remove(entry);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void didClear(BasicEList[] oldEntryData) {
/* 261 */     this.prefixMaps = null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\URIMappingRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */