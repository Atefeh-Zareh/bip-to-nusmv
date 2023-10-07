/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.URIConverter;
/*     */ import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLParserPool;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.AttributesImpl;
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
/*     */ 
/*     */ 
/*     */ public class XMLLoadImpl
/*     */   implements XMLLoad
/*     */ {
/*     */   protected static final String SAX_LEXICAL_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
/*     */   protected static final int BUFFER_SIZE = 200;
/*     */   protected XMLResource resource;
/*     */   protected InputStream is;
/*     */   protected XMLHelper helper;
/*     */   protected Map<?, ?> options;
/*     */   protected boolean namespaceAware;
/*     */   
/*     */   public XMLLoadImpl(XMLHelper helper) {
/*  73 */     this.helper = helper;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleErrors() throws IOException {
/*  78 */     if (!this.resource.getErrors().isEmpty()) {
/*     */       
/*  80 */       Resource.Diagnostic error = (Resource.Diagnostic)this.resource.getErrors().get(0);
/*  81 */       if (error instanceof Exception)
/*     */       {
/*  83 */         throw new Resource.IOWrappedException((Exception)error);
/*     */       }
/*     */ 
/*     */       
/*  87 */       throw new IOException(error.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException {
/*  98 */     if (inputStream instanceof URIConverter.Readable) {
/*     */       
/* 100 */       URIConverter.Readable readable = (URIConverter.Readable)inputStream;
/* 101 */       resource.setEncoding(readable.getEncoding());
/*     */       
/* 103 */       InputSource inputSource = new InputSource(readable.asReader());
/* 104 */       if (resource.getURI() != null) {
/*     */         
/* 106 */         String resourceURI = resource.getURI().toString();
/* 107 */         inputSource.setPublicId(resourceURI);
/* 108 */         inputSource.setSystemId(resourceURI);
/* 109 */         inputSource.setEncoding(resource.getEncoding());
/*     */       } 
/* 111 */       load(resource, inputSource, options);
/*     */       
/*     */       return;
/*     */     } 
/* 115 */     this.resource = resource;
/* 116 */     this.is = inputStream;
/* 117 */     this.options = options;
/* 118 */     XMLParserPool pool = (XMLParserPool)options.get("USE_PARSER_POOL");
/* 119 */     Map<String, Boolean> parserFeatures = (Map<String, Boolean>)options.get("PARSER_FEATURES");
/* 120 */     Map<String, ?> parserProperties = (Map<String, ?>)options.get("PARSER_PROPERTIES");
/* 121 */     parserFeatures = (parserFeatures == null) ? Collections.<String, Boolean>emptyMap() : parserFeatures;
/* 122 */     parserProperties = (parserProperties == null) ? Collections.emptyMap() : parserProperties;
/*     */ 
/*     */     
/* 125 */     String encoding = null;
/* 126 */     if (!Boolean.FALSE.equals(options.get("USE_DEPRECATED_METHODS"))) {
/*     */       
/* 128 */       encoding = getEncoding();
/* 129 */       resource.setEncoding(encoding);
/*     */     } 
/*     */     
/*     */     try {
/*     */       SAXParser parser;
/*     */       
/*     */       DefaultHandler handler;
/* 136 */       if (pool != null) {
/*     */ 
/*     */         
/* 139 */         parser = pool.get(parserFeatures, parserProperties, Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")));
/* 140 */         handler = (DefaultHandler)pool.getDefaultHandler(resource, this, this.helper, options);
/*     */       }
/*     */       else {
/*     */         
/* 144 */         parser = makeParser();
/* 145 */         handler = makeDefaultHandler();
/*     */         
/* 147 */         if (parserFeatures != null)
/*     */         {
/* 149 */           for (Map.Entry<String, Boolean> entry : parserFeatures.entrySet())
/*     */           {
/* 151 */             parser.getXMLReader().setFeature(entry.getKey(), ((Boolean)entry.getValue()).booleanValue());
/*     */           }
/*     */         }
/* 154 */         if (parserProperties != null)
/*     */         {
/* 156 */           for (Map.Entry<String, ?> entry : parserProperties.entrySet())
/*     */           {
/* 158 */             parser.getXMLReader().setProperty(entry.getKey(), entry.getValue());
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/* 163 */       InputSource inputSource = new InputSource(this.is);
/* 164 */       if (resource.getURI() != null) {
/*     */         
/* 166 */         String resourceURI = resource.getURI().toString();
/* 167 */         inputSource.setPublicId(resourceURI);
/* 168 */         inputSource.setSystemId(resourceURI);
/* 169 */         inputSource.setEncoding(encoding);
/*     */       } 
/*     */ 
/*     */       
/* 173 */       if (Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")))
/*     */       {
/* 175 */         if (parserProperties == null || parserProperties.get("http://xml.org/sax/properties/lexical-handler") == null)
/*     */         {
/* 177 */           parser.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
/*     */         }
/*     */       }
/*     */       
/* 181 */       parser.parse(inputSource, handler);
/*     */ 
/*     */       
/* 184 */       if (pool != null) {
/*     */         
/* 186 */         pool.release(parser, parserFeatures, parserProperties, Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")));
/* 187 */         pool.releaseDefaultHandler((XMLDefaultHandler)handler, options);
/*     */       } 
/*     */       
/* 190 */       this.helper = null;
/* 191 */       handleErrors();
/*     */     }
/* 193 */     catch (SAXException exception) {
/*     */       
/* 195 */       if (exception.getException() != null)
/*     */       {
/* 197 */         throw new Resource.IOWrappedException(exception.getException());
/*     */       }
/*     */ 
/*     */       
/* 201 */       throw new Resource.IOWrappedException(exception);
/*     */     
/*     */     }
/* 204 */     catch (ParserConfigurationException exception) {
/*     */       
/* 206 */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(XMLResource resource, InputSource inputSource, Map<?, ?> options) throws IOException {
/* 212 */     this.resource = resource;
/*     */     
/* 214 */     this.options = options;
/* 215 */     XMLParserPool pool = (XMLParserPool)options.get("USE_PARSER_POOL");
/* 216 */     Map<String, Boolean> parserFeatures = (Map<String, Boolean>)options.get("PARSER_FEATURES");
/* 217 */     Map<String, ?> parserProperties = (Map<String, ?>)options.get("PARSER_PROPERTIES");
/* 218 */     parserFeatures = (parserFeatures == null) ? Collections.<String, Boolean>emptyMap() : parserFeatures;
/* 219 */     parserProperties = (parserProperties == null) ? Collections.emptyMap() : parserProperties;
/*     */ 
/*     */     
/*     */     try {
/*     */       SAXParser parser;
/*     */ 
/*     */       
/*     */       DefaultHandler handler;
/*     */       
/* 228 */       if (pool != null) {
/*     */ 
/*     */         
/* 231 */         parser = pool.get(parserFeatures, parserProperties, Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")));
/* 232 */         handler = (DefaultHandler)pool.getDefaultHandler(resource, this, this.helper, options);
/*     */       }
/*     */       else {
/*     */         
/* 236 */         parser = makeParser();
/* 237 */         handler = makeDefaultHandler();
/*     */         
/* 239 */         if (parserFeatures != null)
/*     */         {
/* 241 */           for (Map.Entry<String, Boolean> feature : parserFeatures.entrySet())
/*     */           {
/* 243 */             parser.getXMLReader().setFeature(feature.getKey(), ((Boolean)feature.getValue()).booleanValue());
/*     */           }
/*     */         }
/* 246 */         if (parserProperties != null)
/*     */         {
/* 248 */           for (Map.Entry<String, ?> property : parserProperties.entrySet())
/*     */           {
/* 250 */             parser.getXMLReader().setProperty(property.getKey(), property.getValue());
/*     */           }
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 256 */       if (Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")))
/*     */       {
/* 258 */         if (parserProperties == null || parserProperties.get("http://xml.org/sax/properties/lexical-handler") == null)
/*     */         {
/* 260 */           parser.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
/*     */         }
/*     */       }
/*     */       
/* 264 */       parser.parse(inputSource, handler);
/*     */ 
/*     */       
/* 267 */       if (pool != null) {
/*     */         
/* 269 */         pool.release(parser, parserFeatures, parserProperties, Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")));
/* 270 */         pool.releaseDefaultHandler((XMLDefaultHandler)handler, options);
/*     */       } 
/*     */       
/* 273 */       this.helper = null;
/* 274 */       handleErrors();
/*     */     }
/* 276 */     catch (SAXException exception) {
/*     */       
/* 278 */       if (exception.getException() != null)
/*     */       {
/* 280 */         throw new Resource.IOWrappedException(exception.getException());
/*     */       }
/*     */ 
/*     */       
/* 284 */       throw new Resource.IOWrappedException(exception);
/*     */     
/*     */     }
/* 287 */     catch (ParserConfigurationException exception) {
/*     */       
/* 289 */       throw new Resource.IOWrappedException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SAXParser makeParser() throws ParserConfigurationException, SAXException {
/* 299 */     SAXParserFactory f = SAXParserFactory.newInstance();
/* 300 */     return f.newSAXParser();
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLDefaultHandler createDefaultHandler() {
/* 305 */     return (XMLDefaultHandler)makeDefaultHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   protected DefaultHandler makeDefaultHandler() {
/* 310 */     return new SAXXMLHandler(this.resource, this.helper, this.options);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected String getEncoding() throws IOException {
/* 320 */     if (!this.is.markSupported()) {
/* 321 */       this.is = new BufferedInputStream(this.is);
/*     */     }
/* 323 */     byte[] buffer = readBuffer();
/* 324 */     return XMLHandler.getXMLEncoding(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   protected byte[] readBuffer() throws IOException {
/* 329 */     if (this.is.available() == 0)
/*     */     {
/* 331 */       return new byte[0];
/*     */     }
/*     */     
/* 334 */     byte[] buffer = new byte[200];
/* 335 */     this.is.mark(200);
/* 336 */     int bytesRead = this.is.read(buffer, 0, 200);
/* 337 */     int totalBytesRead = bytesRead;
/*     */     
/* 339 */     while (bytesRead != -1 && totalBytesRead < 200) {
/*     */       
/* 341 */       bytesRead = this.is.read(buffer, totalBytesRead, 200 - totalBytesRead);
/*     */       
/* 343 */       if (bytesRead != -1) {
/* 344 */         totalBytesRead += bytesRead;
/*     */       }
/*     */     } 
/* 347 */     if (totalBytesRead < 0) {
/*     */       
/* 349 */       buffer = new byte[0];
/*     */     }
/* 351 */     else if (totalBytesRead < 200) {
/*     */       
/* 353 */       byte[] smallerBuffer = new byte[totalBytesRead];
/* 354 */       System.arraycopy(buffer, 0, smallerBuffer, 0, totalBytesRead);
/* 355 */       buffer = smallerBuffer;
/*     */     } 
/*     */     
/* 358 */     this.is.reset();
/* 359 */     return buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(XMLResource resource, Node node, Map<?, ?> options) throws IOException {
/* 367 */     this.resource = resource;
/* 368 */     this.options = options;
/* 369 */     this.namespaceAware = Boolean.FALSE.equals(options.get("USE_DEPRECATED_METHODS"));
/*     */     
/* 371 */     XMLParserPool pool = (XMLParserPool)options.get("USE_PARSER_POOL");
/* 372 */     if (pool != null) {
/*     */       
/* 374 */       handler = (DefaultHandler)pool.getDefaultHandler(resource, this, this.helper, options);
/*     */     }
/*     */     else {
/*     */       
/* 378 */       handler = makeDefaultHandler();
/*     */     } 
/* 380 */     LexicalHandler lexicalHandler = null;
/*     */     
/* 382 */     if (Boolean.TRUE.equals(options.get("USE_LEXICAL_HANDLER")))
/*     */     {
/* 384 */       lexicalHandler = (LexicalHandler)handler;
/*     */     }
/*     */     
/* 387 */     AttributesProxy attributesProxy = new AttributesProxy();
/*     */     
/*     */     try {
/* 390 */       short type = node.getNodeType();
/* 391 */       if (type == 1)
/*     */       {
/* 393 */         handler.startDocument();
/* 394 */         if (Boolean.TRUE.equals(options.get("DOM_USE_NAMESPACES_IN_SCOPE"))) {
/*     */           
/* 396 */           traverseElement((Element)node, attributesProxy, handler, lexicalHandler);
/*     */         }
/*     */         else {
/*     */           
/* 400 */           traverse(node, attributesProxy, handler, lexicalHandler);
/*     */         } 
/* 402 */         handler.endDocument();
/*     */       }
/*     */       else
/*     */       {
/* 406 */         traverse(node, attributesProxy, handler, lexicalHandler);
/*     */       }
/*     */     
/* 409 */     } catch (SAXException sAXException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 414 */     if (pool != null)
/*     */     {
/* 416 */       pool.releaseDefaultHandler((XMLDefaultHandler)handler, options);
/*     */     }
/*     */     
/* 419 */     attributesProxy = null;
/* 420 */     DefaultHandler handler = null;
/* 421 */     lexicalHandler = null;
/* 422 */     this.helper = null;
/*     */     
/* 424 */     handleErrors();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void traverseElement(Element element, AttributesProxy attributesProxy, DefaultHandler handler, LexicalHandler lexicalHandler) throws SAXException {
/* 433 */     AttributesImpl attrs = new AttributesImpl();
/* 434 */     Set<String> prefixes = new HashSet<String>();
/*     */ 
/*     */     
/* 437 */     if (element.hasAttributes()) {
/*     */       
/* 439 */       NamedNodeMap attributes = element.getAttributes();
/* 440 */       for (int i = 0; i < attributes.getLength(); i++) {
/*     */         
/* 442 */         Node attr = attributes.item(i);
/* 443 */         String str1 = attr.getNamespaceURI();
/* 444 */         if (str1 == null)
/*     */         {
/* 446 */           str1 = "";
/*     */         }
/* 448 */         String nodeName = attr.getNodeName();
/* 449 */         String str2 = attr.getLocalName();
/* 450 */         String nodeValue = attr.getNodeValue();
/* 451 */         if ("http://www.w3.org/2000/xmlns/".equals(str1)) {
/*     */ 
/*     */ 
/*     */           
/* 455 */           if (this.namespaceAware)
/*     */           {
/* 457 */             if (prefixes.add(str2))
/*     */             {
/* 459 */               handler.startPrefixMapping(str2, nodeValue);
/*     */             }
/*     */           }
/* 462 */           else if (attrs.getIndex(nodeName) < 0)
/*     */           {
/* 464 */             attrs.addAttribute(str1, str2, nodeName, "CDATA", nodeValue);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 469 */           attrs.addAttribute(str1, str2, nodeName, "CDATA", nodeValue);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 476 */     for (Node parent = element.getParentNode(); parent != null && parent.getNodeType() != 9; parent = parent.getParentNode()) {
/*     */       
/* 478 */       if (parent.hasAttributes()) {
/*     */         
/* 480 */         NamedNodeMap attributes = parent.getAttributes();
/* 481 */         for (int i = 0; i < attributes.getLength(); i++) {
/*     */           
/* 483 */           Node attr = attributes.item(i);
/* 484 */           String str = attr.getNamespaceURI();
/* 485 */           if ("http://www.w3.org/2000/xmlns/".equals(str)) {
/*     */ 
/*     */ 
/*     */             
/* 489 */             String str1 = attr.getLocalName();
/* 490 */             String nodeValue = attr.getNodeValue();
/* 491 */             if (this.namespaceAware) {
/*     */               
/* 493 */               if (prefixes.add(str1))
/*     */               {
/* 495 */                 handler.startPrefixMapping(str1, nodeValue);
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 500 */               String nodeName = attr.getNodeName();
/* 501 */               if (attrs.getIndex(nodeName) < 0)
/*     */               {
/* 503 */                 attrs.addAttribute(str, str1, nodeName, "CDATA", nodeValue);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 512 */     String namespaceURI = element.getNamespaceURI();
/* 513 */     if (namespaceURI == null)
/*     */     {
/* 515 */       namespaceURI = "";
/*     */     }
/* 517 */     String localName = element.getLocalName();
/* 518 */     String qname = element.getNodeName();
/*     */     
/* 520 */     handler.startElement(namespaceURI, localName, qname, attrs);
/* 521 */     Node child = element.getFirstChild();
/* 522 */     while (child != null) {
/*     */       
/* 524 */       traverse(child, attributesProxy, handler, lexicalHandler);
/* 525 */       child = child.getNextSibling();
/*     */     } 
/* 527 */     handler.endElement(namespaceURI, localName, qname); } protected void traverse(Node node, AttributesProxy attributesProxy, DefaultHandler handler, LexicalHandler lexicalHandler) throws SAXException { Document document; AttributesImpl filteredAttributes; char[] chars; ProcessingInstruction pi;
/*     */     Node root;
/*     */     NamedNodeMap attributes;
/*     */     String namespaceURI, localName, qname;
/*     */     Node child;
/* 532 */     if (node == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 537 */     short type = node.getNodeType();
/* 538 */     switch (type) {
/*     */ 
/*     */       
/*     */       case 9:
/* 542 */         document = (Document)node;
/* 543 */         handler.startDocument();
/* 544 */         root = document.getDocumentElement();
/* 545 */         if (lexicalHandler != null) {
/*     */           
/* 547 */           DocumentType doctype = document.getDoctype();
/* 548 */           if (doctype != null) {
/*     */             
/* 550 */             String publicId = doctype.getPublicId();
/* 551 */             String systemId = doctype.getSystemId();
/* 552 */             lexicalHandler.startDTD(root.getNodeName(), publicId, systemId);
/*     */           } 
/*     */         } 
/* 555 */         traverse(root, attributesProxy, handler, lexicalHandler);
/* 556 */         handler.endDocument();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 561 */         filteredAttributes = null;
/* 562 */         attributes = node.getAttributes();
/* 563 */         if (this.namespaceAware)
/*     */         {
/* 565 */           for (int i = 0, length = attributes.getLength(); i < length; i++) {
/*     */             
/* 567 */             Node attr = attributes.item(i);
/* 568 */             String str = attr.getNamespaceURI();
/* 569 */             if ("http://www.w3.org/2000/xmlns/".equals(str)) {
/*     */               
/* 571 */               handler.startPrefixMapping(attr.getLocalName(), attr.getNodeValue());
/* 572 */               if (filteredAttributes == null) {
/*     */                 
/* 574 */                 filteredAttributes = new AttributesImpl();
/* 575 */                 for (int j = 0; j < i; j++)
/*     */                 {
/* 577 */                   attr = attributes.item(j);
/* 578 */                   str = attr.getNamespaceURI();
/* 579 */                   if (str == null)
/*     */                   {
/* 581 */                     str = "";
/*     */                   }
/* 583 */                   filteredAttributes.addAttribute(str, attr.getLocalName(), attr.getNodeName(), "CDATA", attr.getNodeValue());
/*     */                 }
/*     */               
/*     */               } 
/* 587 */             } else if (filteredAttributes != null) {
/*     */               
/* 589 */               if (str == null)
/*     */               {
/* 591 */                 str = "";
/*     */               }
/* 593 */               filteredAttributes.addAttribute(str, attr.getLocalName(), attr.getNodeName(), "CDATA", attr.getNodeValue());
/*     */             } 
/*     */           } 
/*     */         }
/* 597 */         if (filteredAttributes == null)
/*     */         {
/* 599 */           attributesProxy.setAttributes(attributes);
/*     */         }
/* 601 */         namespaceURI = node.getNamespaceURI();
/* 602 */         if (namespaceURI == null)
/*     */         {
/* 604 */           namespaceURI = "";
/*     */         }
/* 606 */         localName = node.getLocalName();
/* 607 */         qname = node.getNodeName();
/*     */         
/* 609 */         handler.startElement(namespaceURI, localName, qname, (filteredAttributes == null) ? attributesProxy : filteredAttributes);
/*     */         
/* 611 */         child = node.getFirstChild();
/* 612 */         while (child != null) {
/*     */           
/* 614 */           traverse(child, attributesProxy, handler, lexicalHandler);
/* 615 */           child = child.getNextSibling();
/*     */         } 
/* 617 */         handler.endElement(namespaceURI, localName, qname);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 623 */         if (lexicalHandler != null)
/*     */         {
/* 625 */           lexicalHandler.startCDATA();
/*     */         }
/* 627 */         chars = ((CDATASection)node).getData().toCharArray();
/* 628 */         handler.characters(chars, 0, chars.length);
/* 629 */         if (lexicalHandler != null)
/*     */         {
/* 631 */           lexicalHandler.endCDATA();
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 637 */         chars = node.getNodeValue().toCharArray();
/* 638 */         handler.characters(chars, 0, chars.length);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 8:
/* 643 */         if (lexicalHandler != null) {
/*     */           
/* 645 */           chars = ((Comment)node).getData().toCharArray();
/* 646 */           lexicalHandler.comment(chars, 0, chars.length);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 7:
/* 652 */         pi = (ProcessingInstruction)node;
/* 653 */         handler.processingInstruction(pi.getTarget(), pi.getData());
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final class AttributesProxy
/*     */     implements Attributes
/*     */   {
/*     */     protected NamedNodeMap attributes;
/*     */ 
/*     */     
/*     */     public void setAttributes(NamedNodeMap attributes) {
/* 667 */       this.attributes = attributes;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 672 */       return this.attributes.getLength();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getQName(int index) {
/* 677 */       Node node = this.attributes.item(index);
/* 678 */       return (node != null) ? node.getNodeName() : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getURI(int index) {
/* 683 */       Node node = this.attributes.item(index);
/* 684 */       if (node != null) {
/*     */         
/* 686 */         String namespaceURI = node.getNamespaceURI();
/* 687 */         if ("http://www.w3.org/2000/xmlns/".equals(namespaceURI))
/*     */         {
/* 689 */           return "";
/*     */         }
/* 691 */         return namespaceURI;
/*     */       } 
/* 693 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getLocalName(int index) {
/* 698 */       Node node = this.attributes.item(index);
/* 699 */       if (node != null) {
/*     */         
/* 701 */         String prefix = node.getPrefix();
/* 702 */         if ("xmlns".equals(prefix))
/*     */         {
/* 704 */           return "";
/*     */         }
/* 706 */         return node.getLocalName();
/*     */       } 
/* 708 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getType(int i) {
/* 713 */       return "CDATA";
/*     */     }
/*     */ 
/*     */     
/*     */     public String getType(String name) {
/* 718 */       return "CDATA";
/*     */     }
/*     */ 
/*     */     
/*     */     public String getType(String uri, String localName) {
/* 723 */       return "CDATA";
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue(int i) {
/* 728 */       Node node = this.attributes.item(i);
/* 729 */       return (node != null) ? node.getNodeValue() : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue(String name) {
/* 734 */       Node node = this.attributes.getNamedItem(name);
/* 735 */       return (node != null) ? node.getNodeValue() : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue(String uri, String localName) {
/* 740 */       Node node = this.attributes.getNamedItemNS(uri, localName);
/* 741 */       return (node != null) ? node.getNodeValue() : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIndex(String qName) {
/* 746 */       Node node = this.attributes.getNamedItem(qName);
/* 747 */       if (node != null)
/*     */       {
/* 749 */         for (int i = 0; i < this.attributes.getLength(); i++) {
/*     */           
/* 751 */           Node item = this.attributes.item(i);
/* 752 */           if (item == node)
/*     */           {
/* 754 */             return i;
/*     */           }
/*     */         } 
/*     */       }
/* 758 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIndex(String uri, String localPart) {
/* 763 */       Node node = this.attributes.getNamedItemNS(uri, localPart);
/* 764 */       if (node != null)
/*     */       {
/* 766 */         for (int i = 0; i < this.attributes.getLength(); i++) {
/*     */           
/* 768 */           Node item = this.attributes.item(i);
/* 769 */           if (item == node)
/*     */           {
/* 771 */             return i;
/*     */           }
/*     */         } 
/*     */       }
/* 775 */       return -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLLoadImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */