/*     */ package org.eclipse.emf.ecore.resource.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.core.resources.IWorkspaceRoot;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import org.eclipse.emf.ecore.resource.ContentHandler;
/*     */ import org.eclipse.emf.ecore.resource.URIConverter;
/*     */ import org.eclipse.emf.ecore.resource.URIHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtensibleURIConverterImpl
/*     */   implements URIConverter
/*     */ {
/*     */   protected URIHandlerList uriHandlers;
/*     */   protected ContentHandlerList contentHandlers;
/*     */   protected URIMap uriMap;
/*     */   
/*     */   protected static class URIHandlerList
/*     */     extends BasicEList<URIHandler>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     protected boolean canContainNull() {
/*  79 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object[] newData(int capacity) {
/*  85 */       return (Object[])new URIHandler[capacity];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public URIHandler[] data() {
/*  91 */       return (URIHandler[])this.data;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class ContentHandlerList
/*     */     extends BasicEList<ContentHandler>
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canContainNull() {
/* 109 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object[] newData(int capacity) {
/* 115 */       return (Object[])new ContentHandler[capacity];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ContentHandler[] data() {
/* 121 */       return (ContentHandler[])this.data;
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
/*     */ 
/*     */   
/*     */   public ExtensibleURIConverterImpl() {
/* 137 */     this(URIHandler.DEFAULT_HANDLERS, ContentHandler.Registry.INSTANCE.contentHandlers());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensibleURIConverterImpl(Collection<URIHandler> uriHandlers, Collection<ContentHandler> contentHandlers) {
/* 145 */     getURIHandlers().addAll(uriHandlers);
/* 146 */     getContentHandlers().addAll(contentHandlers);
/*     */   }
/*     */ 
/*     */   
/*     */   public EList<URIHandler> getURIHandlers() {
/* 151 */     if (this.uriHandlers == null)
/*     */     {
/* 153 */       this.uriHandlers = new URIHandlerList();
/*     */     }
/* 155 */     return (EList<URIHandler>)this.uriHandlers;
/*     */   }
/*     */ 
/*     */   
/*     */   public URIHandler getURIHandler(URI uri) {
/* 160 */     int size = this.uriHandlers.size();
/* 161 */     if (size > 0) {
/*     */       
/* 163 */       URIHandler[] data = this.uriHandlers.data();
/* 164 */       for (int i = 0; i < size; i++) {
/*     */         
/* 166 */         URIHandler uriHandler = data[i];
/* 167 */         if (uriHandler.canHandle(uri))
/*     */         {
/* 169 */           return uriHandler;
/*     */         }
/*     */       } 
/*     */     } 
/* 173 */     throw new RuntimeException("There is no URIHandler to handle " + uri);
/*     */   }
/*     */ 
/*     */   
/*     */   public EList<ContentHandler> getContentHandlers() {
/* 178 */     if (this.contentHandlers == null)
/*     */     {
/* 180 */       this.contentHandlers = new ContentHandlerList();
/*     */     }
/* 182 */     return (EList<ContentHandler>)this.contentHandlers;
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream(URI uri) throws IOException {
/* 187 */     return createOutputStream(uri, null);
/*     */   }
/*     */   
/*     */   static class OptionsMap
/*     */     implements Map<Object, Object>
/*     */   {
/*     */     protected Object key;
/*     */     protected Object value;
/*     */     protected Map<?, ?> options;
/*     */     protected Map<Object, Object> mergedMap;
/*     */     
/*     */     public OptionsMap(Object key, Object value, Map<?, ?> options) {
/* 199 */       this.options = (options == null) ? Collections.EMPTY_MAP : options;
/* 200 */       this.key = key;
/* 201 */       this.value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Map<Object, Object> mergedMap() {
/* 206 */       if (this.mergedMap == null) {
/*     */         
/* 208 */         this.mergedMap = new LinkedHashMap<Object, Object>(this.options);
/* 209 */         this.mergedMap.put(this.key, this.value);
/*     */       } 
/* 211 */       return this.mergedMap;
/*     */     }
/*     */ 
/*     */     
/*     */     public void clear() {
/* 216 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsKey(Object key) {
/* 221 */       return !(this.key != key && !this.key.equals(key) && !this.options.containsKey(key));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsValue(Object value) {
/* 226 */       return !(this.value != value && !this.options.containsValue(value));
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<Map.Entry<Object, Object>> entrySet() {
/* 231 */       return mergedMap().entrySet();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object get(Object key) {
/* 236 */       return (this.key == key || this.key.equals(key)) ? this.value : this.options.get(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 241 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<Object> keySet() {
/* 246 */       return mergedMap().keySet();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object put(Object key, Object value) {
/* 251 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putAll(Map<? extends Object, ? extends Object> t) {
/* 256 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object remove(Object key) {
/* 261 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 266 */       return mergedMap().size();
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<Object> values() {
/* 271 */       return mergedMap().values();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 277 */       return mergedMap().hashCode();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 283 */       return mergedMap().equals(o);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
/* 289 */     URI normalizedURI = normalize(uri);
/* 290 */     return getURIHandler(normalizedURI).createOutputStream(normalizedURI, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(URI uri) throws IOException {
/* 295 */     return createInputStream(uri, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
/* 300 */     URI normalizedURI = normalize(uri);
/* 301 */     return getURIHandler(normalizedURI).createInputStream(normalizedURI, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete(URI uri, Map<?, ?> options) throws IOException {
/* 306 */     URI normalizedURI = normalize(uri);
/* 307 */     getURIHandler(normalizedURI).delete(normalizedURI, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
/* 312 */     URI normalizedURI = normalize(uri);
/* 313 */     return getURIHandler(normalizedURI).contentDescription(normalizedURI, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean exists(URI uri, Map<?, ?> options) {
/* 318 */     URI normalizedURI = normalize(uri);
/* 319 */     return getURIHandler(normalizedURI).exists(normalizedURI, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
/* 324 */     URI normalizedURI = normalize(uri);
/* 325 */     return getURIHandler(normalizedURI).getAttributes(normalizedURI, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
/* 330 */     URI normalizedURI = normalize(uri);
/* 331 */     getURIHandler(normalizedURI).setAttributes(normalizedURI, attributes, new OptionsMap("URI_CONVERTER", this, options));
/*     */   }
/*     */   
/* 334 */   private static IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
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
/*     */   public URI normalize(URI uri) {
/* 348 */     String fragment = uri.fragment();
/* 349 */     URI result = 
/* 350 */       (fragment == null) ? 
/* 351 */       getInternalURIMap().getURI(uri) : 
/* 352 */       getInternalURIMap().getURI(uri.trimFragment()).appendFragment(fragment);
/* 353 */     String scheme = result.scheme();
/* 354 */     if (scheme == null)
/*     */     {
/* 356 */       if (workspaceRoot != null) {
/*     */         
/* 358 */         if (result.hasAbsolutePath())
/*     */         {
/* 360 */           result = URI.createPlatformResourceURI(result.trimFragment().toString(), false);
/* 361 */           if (fragment != null)
/*     */           {
/* 363 */             result = result.appendFragment(fragment);
/*     */           
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 369 */       else if (result.hasAbsolutePath()) {
/*     */         
/* 371 */         result = URI.createURI("file:" + result);
/*     */       }
/*     */       else {
/*     */         
/* 375 */         result = URI.createFileURI((new File(result.trimFragment().toString())).getAbsolutePath());
/* 376 */         if (fragment != null)
/*     */         {
/* 378 */           result = result.appendFragment(fragment);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 384 */     if (result.equals(uri))
/*     */     {
/* 386 */       return uri;
/*     */     }
/*     */ 
/*     */     
/* 390 */     return normalize(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<URI, URI> getURIMap() {
/* 399 */     return getInternalURIMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected URIMap getInternalURIMap() {
/* 408 */     if (this.uriMap == null) {
/*     */       
/* 410 */       URIMappingRegistryImpl mappingRegistryImpl = 
/* 411 */         new URIMappingRegistryImpl()
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           protected URI delegatedGetURI(URI uri) {
/* 418 */             return URIMappingRegistryImpl.INSTANCE.getURI(uri);
/*     */           }
/*     */         };
/*     */       
/* 422 */       this.uriMap = (URIMap)mappingRegistryImpl.map();
/*     */     } 
/*     */     
/* 425 */     return this.uriMap;
/*     */   }
/*     */   
/*     */   public static interface URIMap extends Map<URI, URI> {
/*     */     URI getURI(URI param1URI);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\impl\ExtensibleURIConverterImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */