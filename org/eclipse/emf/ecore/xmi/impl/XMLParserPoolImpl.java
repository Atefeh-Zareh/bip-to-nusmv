/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLParserPool;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class XMLParserPoolImpl
/*     */   implements XMLParserPool
/*     */ {
/*  46 */   private final Map<Map<?, ?>, List<SAXParser>> parserCache = new HashMap<Map<?, ?>, List<SAXParser>>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map<Map<?, ?>, List<XMLDefaultHandler>> handlersCache;
/*     */ 
/*     */   
/*     */   private final int size;
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLParserPoolImpl() {
/*  58 */     this(200, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLParserPoolImpl(boolean useHandlerCache) {
/*  68 */     this(200, useHandlerCache);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLParserPoolImpl(int size, boolean useHandlerCache) {
/*  79 */     this.size = size;
/*  80 */     this.handlersCache = useHandlerCache ? new HashMap<Map<?, ?>, List<XMLDefaultHandler>>() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized SAXParser get(Map<String, Boolean> features, Map<String, ?> properties, boolean useLexicalHandler) throws ParserConfigurationException, SAXException {
/*  88 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*  89 */     map.putAll(features);
/*  90 */     map.putAll(properties);
/*  91 */     map.put("USE_LEXICAL_HANDLER", useLexicalHandler ? Boolean.TRUE : Boolean.FALSE);
/*  92 */     if (this.parserCache.size() > this.size)
/*     */     {
/*  94 */       this.parserCache.clear();
/*     */     }
/*  96 */     List<SAXParser> list = this.parserCache.get(map);
/*  97 */     if (list != null) {
/*     */       
/*  99 */       int size = list.size();
/* 100 */       if (size > 0)
/*     */       {
/* 102 */         return list.remove(size - 1);
/*     */       }
/*     */ 
/*     */       
/* 106 */       return makeParser(features, properties);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.parserCache.put(map, new ArrayList<SAXParser>());
/* 112 */     return makeParser(features, properties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void release(SAXParser parser, Map<String, Boolean> features, Map<String, ?> properties, boolean useLexicalHandler) {
/* 121 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 122 */     map.putAll(features);
/* 123 */     map.putAll(properties);
/* 124 */     map.put("USE_LEXICAL_HANDLER", useLexicalHandler ? Boolean.TRUE : Boolean.FALSE);
/* 125 */     List<SAXParser> list = this.parserCache.get(map);
/* 126 */     if (list.size() < this.size)
/*     */     {
/* 128 */       list.add(parser);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SAXParser makeParser(Map<String, Boolean> features, Map<String, ?> properties) throws ParserConfigurationException, SAXException {
/* 134 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/* 135 */     factory.setValidating(false);
/* 136 */     factory.setNamespaceAware(true);
/* 137 */     SAXParser parser = factory.newSAXParser();
/*     */ 
/*     */     
/* 140 */     if (features != null)
/*     */     {
/* 142 */       for (Map.Entry<String, Boolean> entry : features.entrySet())
/*     */       {
/* 144 */         parser.getXMLReader().setFeature(entry.getKey(), ((Boolean)entry.getValue()).booleanValue());
/*     */       }
/*     */     }
/* 147 */     if (properties != null)
/*     */     {
/* 149 */       for (Map.Entry<String, ?> entry : properties.entrySet())
/*     */       {
/* 151 */         parser.getXMLReader().setProperty(entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/* 154 */     return parser;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized XMLDefaultHandler getDefaultHandler(XMLResource resource, XMLLoad xmlLoad, XMLHelper helper, Map<?, ?> options) {
/* 159 */     if (this.handlersCache != null) {
/*     */       
/* 161 */       if (this.handlersCache.size() > this.size)
/*     */       {
/* 163 */         this.handlersCache.clear();
/*     */       }
/* 165 */       List<XMLDefaultHandler> list = this.handlersCache.get(options);
/* 166 */       if (list != null) {
/*     */         
/* 168 */         int size = list.size();
/* 169 */         if (size > 0)
/*     */         {
/* 171 */           XMLDefaultHandler handler = list.remove(size - 1);
/* 172 */           handler.prepare(resource, helper, options);
/* 173 */           return handler;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 178 */         this.handlersCache.put(options, new ArrayList<XMLDefaultHandler>());
/*     */       } 
/*     */     } 
/* 181 */     return xmlLoad.createDefaultHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void releaseDefaultHandler(XMLDefaultHandler handler, Map<?, ?> options) {
/* 186 */     if (this.handlersCache != null) {
/*     */       
/* 188 */       handler.reset();
/* 189 */       List<XMLDefaultHandler> list = this.handlersCache.get(options);
/* 190 */       if (list == null) {
/*     */         
/* 192 */         list = new ArrayList<XMLDefaultHandler>();
/* 193 */         this.handlersCache.put(options, list);
/*     */       }
/* 195 */       else if (list.size() < this.size) {
/*     */         
/* 197 */         list.add(handler);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLParserPoolImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */