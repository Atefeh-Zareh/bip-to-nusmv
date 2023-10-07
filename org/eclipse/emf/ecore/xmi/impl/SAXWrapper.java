/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.xmi.XMIException;
/*     */ import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class SAXWrapper
/*     */   extends DefaultHandler
/*     */   implements XMLDefaultHandler
/*     */ {
/*     */   protected XMLHandler handler;
/*     */   
/*     */   public void prepare(XMLResource resource, XMLHelper helper, Map<?, ?> options) {
/*  47 */     this.handler.prepare(resource, helper, options);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  52 */     this.handler.reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SAXWrapper(XMLHandler handler) {
/*  61 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/*  67 */     this.handler.setLocator(locator);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {
/*  73 */     this.handler.startDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/*  79 */     this.handler.endDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  85 */     this.handler.startPrefixMapping(prefix, uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*  97 */     this.handler.setAttributes(attributes);
/*  98 */     this.handler.startElement(uri, localName, qName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String localName, String qName) throws SAXException {
/* 104 */     this.handler.endElement(uri, localName, qName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(SAXParseException e) throws SAXException {
/* 110 */     XMIException xmi = new XMIException((e.getException() == null) ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
/* 111 */     this.handler.warning(xmi);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(SAXParseException e) throws SAXException {
/* 117 */     XMIException xmi = new XMIException((e.getException() == null) ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
/* 118 */     this.handler.error(xmi);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(SAXParseException e) throws SAXException {
/* 124 */     XMIException xmi = new XMIException((e.getException() == null) ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
/* 125 */     this.handler.fatalError(xmi);
/* 126 */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 132 */     this.handler.characters(ch, start, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 144 */     this.handler.processingInstruction(target, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/* 157 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notationDecl(String name, String publicId, String systemId) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) {
/* 174 */     this.handler.startDTD(name, publicId, systemId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDTD() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startEntity(String name) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void endEntity(String name) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCDATA() {
/* 194 */     this.handler.startCDATA();
/*     */   }
/*     */ 
/*     */   
/*     */   public void endCDATA() {
/* 199 */     this.handler.endCDATA();
/*     */   }
/*     */ 
/*     */   
/*     */   public void comment(char[] characters, int start, int length) throws SAXException {
/* 204 */     this.handler.comment(characters, start, length);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\SAXWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */