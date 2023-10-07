/*      */ package org.eclipse.emf.ecore.xml.type.util;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.List;
/*      */ import javax.xml.datatype.Duration;
/*      */ import javax.xml.datatype.XMLGregorianCalendar;
/*      */ import javax.xml.namespace.QName;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
/*      */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XMLTypeResourceImpl
/*      */   extends ResourceImpl
/*      */ {
/*      */   public XMLTypeResourceImpl(URI uri) {
/*   60 */     super(uri);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class FrameFactory
/*      */   {
/*   75 */     public static final FrameFactory INSTANCE = new FrameFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected AnyTypeStackFrame anyType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ProcessingInstructionStackFrame processingInstruction;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected SimpleAnyTypeStackFrame simpleAnyType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeDocumentRootStackFrame xmlTypeDocumentRoot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame anySimpleType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame anyURI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame base64Binary;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame boolean_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame booleanObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame byte_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame byteObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame date;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame dateTime;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame decimal;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame double_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame doubleObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame duration;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame entities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame entitiesBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame entity;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame float_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame floatObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame gDay;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame gMonth;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame gMonthDay;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame gYear;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame gYearMonth;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame hexBinary;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame id;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame idref;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame idrefs;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame idrefsBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame int_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame integer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame intObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame language;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame long_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame longObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame name;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame ncName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame negativeInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame nmtoken;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame nmtokens;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame nmtokensBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame nonNegativeInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame nonPositiveInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame normalizedString;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame notation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame positiveInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame qName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame short_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame shortObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame string;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame time;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame token;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedByte;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedByteObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedInt;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedIntObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedLong;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedShort;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTypeResourceImpl.DataFrame unsignedShortObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnyTypeStackFrame pushAnyType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/*  518 */       AnyTypeStackFrame resultAnyType = (this.anyType == null) ? new AnyTypeStackFrame() : this.anyType;
/*  519 */       this.anyType = null;
/*  520 */       resultAnyType.pushOnto(previous);
/*  521 */       resultAnyType.handleAttributes(attributes);
/*  522 */       return resultAnyType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AnyType popAnyType(AnyTypeStackFrame anyType) {
/*  532 */       AnyType resultAnyTypeValue = anyType.popAnyType();
/*  533 */       this.anyType = anyType;
/*  534 */       return resultAnyTypeValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class AnyTypeStackFrame
/*      */       extends XMLTypeResourceImpl.StackFrame
/*      */     {
/*      */       protected AnyType theAnyType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void handleAttributes(Attributes attributes) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
/*  570 */         return super.startElement(namespace, localName, qName, attributes);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
/*  581 */         super.endElement(child);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void create() {
/*  592 */         this.theAnyType = XMLTypeFactory.eINSTANCE.createAnyType();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected AnyType popAnyType() {
/*  602 */         pop();
/*  603 */         AnyType resultAnyTypeValue = this.theAnyType;
/*  604 */         this.theAnyType = null;
/*  605 */         return resultAnyTypeValue;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ProcessingInstructionStackFrame pushProcessingInstruction(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/*  617 */       ProcessingInstructionStackFrame resultProcessingInstruction = (this.processingInstruction == null) ? new ProcessingInstructionStackFrame() : this.processingInstruction;
/*  618 */       this.processingInstruction = null;
/*  619 */       resultProcessingInstruction.pushOnto(previous);
/*  620 */       resultProcessingInstruction.handleAttributes(attributes);
/*  621 */       return resultProcessingInstruction;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ProcessingInstruction popProcessingInstruction(ProcessingInstructionStackFrame processingInstruction) {
/*  631 */       ProcessingInstruction resultProcessingInstructionValue = processingInstruction.popProcessingInstruction();
/*  632 */       this.processingInstruction = processingInstruction;
/*  633 */       return resultProcessingInstructionValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ProcessingInstructionStackFrame
/*      */       extends XMLTypeResourceImpl.StackFrame
/*      */     {
/*      */       protected ProcessingInstruction theProcessingInstruction;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeResourceImpl.DataFrame data;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeResourceImpl.DataFrame target;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void handleAttributes(Attributes attributes) {
/*  672 */         String theValue = attributes.getValue("", "data");
/*  673 */         if (theValue != null)
/*      */         {
/*  675 */           this.theProcessingInstruction.setData(XMLTypeFactory.eINSTANCE.createString(theValue));
/*      */         }
/*  677 */         theValue = attributes.getValue("", "target");
/*  678 */         if (theValue != null)
/*      */         {
/*  680 */           this.theProcessingInstruction.setTarget(XMLTypeFactory.eINSTANCE.createString(theValue));
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
/*  692 */         return super.startElement(namespace, localName, qName, attributes);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
/*  703 */         super.endElement(child);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void create() {
/*  714 */         this.theProcessingInstruction = XMLTypeFactory.eINSTANCE.createProcessingInstruction();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected ProcessingInstruction popProcessingInstruction() {
/*  724 */         pop();
/*  725 */         ProcessingInstruction resultProcessingInstructionValue = this.theProcessingInstruction;
/*  726 */         this.theProcessingInstruction = null;
/*  727 */         return resultProcessingInstructionValue;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SimpleAnyTypeStackFrame pushSimpleAnyType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/*  739 */       SimpleAnyTypeStackFrame resultSimpleAnyType = (this.simpleAnyType == null) ? new SimpleAnyTypeStackFrame() : this.simpleAnyType;
/*  740 */       this.simpleAnyType = null;
/*  741 */       resultSimpleAnyType.pushOnto(previous);
/*  742 */       resultSimpleAnyType.handleAttributes(attributes);
/*  743 */       return resultSimpleAnyType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SimpleAnyType popSimpleAnyType(SimpleAnyTypeStackFrame simpleAnyType) {
/*  753 */       SimpleAnyType resultSimpleAnyTypeValue = simpleAnyType.popSimpleAnyType();
/*  754 */       this.simpleAnyType = simpleAnyType;
/*  755 */       return resultSimpleAnyTypeValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class SimpleAnyTypeStackFrame
/*      */       extends XMLTypeResourceImpl.StackFrame
/*      */     {
/*      */       protected SimpleAnyType theSimpleAnyType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void handleAttributes(Attributes attributes) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
/*  791 */         return super.startElement(namespace, localName, qName, attributes);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
/*  802 */         super.endElement(child);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void create() {
/*  813 */         this.theSimpleAnyType = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected SimpleAnyType popSimpleAnyType() {
/*  823 */         pop();
/*  824 */         SimpleAnyType resultSimpleAnyTypeValue = this.theSimpleAnyType;
/*  825 */         this.theSimpleAnyType = null;
/*  826 */         return resultSimpleAnyTypeValue;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeDocumentRootStackFrame pushXMLTypeDocumentRoot(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/*  838 */       XMLTypeDocumentRootStackFrame resultXMLTypeDocumentRoot = (this.xmlTypeDocumentRoot == null) ? new XMLTypeDocumentRootStackFrame() : this.xmlTypeDocumentRoot;
/*  839 */       this.xmlTypeDocumentRoot = null;
/*  840 */       resultXMLTypeDocumentRoot.pushOnto(previous);
/*  841 */       resultXMLTypeDocumentRoot.handleAttributes(attributes);
/*  842 */       return resultXMLTypeDocumentRoot;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeDocumentRoot popXMLTypeDocumentRoot(XMLTypeDocumentRootStackFrame xmlTypeDocumentRoot) {
/*  852 */       XMLTypeDocumentRoot resultXMLTypeDocumentRootValue = xmlTypeDocumentRoot.popXMLTypeDocumentRoot();
/*  853 */       this.xmlTypeDocumentRoot = xmlTypeDocumentRoot;
/*  854 */       return resultXMLTypeDocumentRootValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class XMLTypeDocumentRootStackFrame
/*      */       extends XMLTypeResourceImpl.StackFrame
/*      */     {
/*      */       protected XMLTypeDocumentRoot theXMLTypeDocumentRoot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeResourceImpl.DataFrame cDATA;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeResourceImpl.DataFrame comment;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeResourceImpl.FrameFactory.ProcessingInstructionStackFrame processingInstruction;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeResourceImpl.DataFrame text;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void handleAttributes(Attributes attributes) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
/*  918 */         throw new UnsupportedOperationException();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException {
/*  929 */         throw new UnsupportedOperationException();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void create() {
/*  940 */         this.theXMLTypeDocumentRoot = XMLTypeFactory.eINSTANCE.createXMLTypeDocumentRoot();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected XMLTypeDocumentRoot popXMLTypeDocumentRoot() {
/*  950 */         pop();
/*  951 */         XMLTypeDocumentRoot resultXMLTypeDocumentRootValue = this.theXMLTypeDocumentRoot;
/*  952 */         this.theXMLTypeDocumentRoot = null;
/*  953 */         return resultXMLTypeDocumentRootValue;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushAnySimpleType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/*  965 */       XMLTypeResourceImpl.DataFrame resultAnySimpleType = (this.anySimpleType == null) ? new XMLTypeResourceImpl.DataFrame() : this.anySimpleType;
/*  966 */       this.anySimpleType = null;
/*  967 */       resultAnySimpleType.pushOnto(previous);
/*  968 */       resultAnySimpleType.handleAttributes(attributes);
/*  969 */       return resultAnySimpleType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object popAnySimpleType(XMLTypeResourceImpl.DataFrame anySimpleType) {
/*  979 */       Object resultAnySimpleTypeValue = XMLTypeFactory.eINSTANCE.createAnySimpleType(anySimpleType.popValue());
/*  980 */       this.anySimpleType = anySimpleType;
/*  981 */       return resultAnySimpleTypeValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushAnyURI(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/*  991 */       XMLTypeResourceImpl.DataFrame resultAnyURI = (this.anyURI == null) ? new XMLTypeResourceImpl.DataFrame() : this.anyURI;
/*  992 */       this.anyURI = null;
/*  993 */       resultAnyURI.pushOnto(previous);
/*  994 */       resultAnyURI.handleAttributes(attributes);
/*  995 */       return resultAnyURI;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popAnyURI(XMLTypeResourceImpl.DataFrame anyURI) {
/* 1005 */       String resultAnyURIValue = XMLTypeFactory.eINSTANCE.createAnyURI(anyURI.popValue());
/* 1006 */       this.anyURI = anyURI;
/* 1007 */       return resultAnyURIValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushBase64Binary(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1017 */       XMLTypeResourceImpl.DataFrame resultBase64Binary = (this.base64Binary == null) ? new XMLTypeResourceImpl.DataFrame() : this.base64Binary;
/* 1018 */       this.base64Binary = null;
/* 1019 */       resultBase64Binary.pushOnto(previous);
/* 1020 */       resultBase64Binary.handleAttributes(attributes);
/* 1021 */       return resultBase64Binary;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public byte[] popBase64Binary(XMLTypeResourceImpl.DataFrame base64Binary) {
/* 1031 */       byte[] resultBase64BinaryValue = XMLTypeFactory.eINSTANCE.createBase64Binary(base64Binary.popValue());
/* 1032 */       this.base64Binary = base64Binary;
/* 1033 */       return resultBase64BinaryValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushBoolean(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1043 */       XMLTypeResourceImpl.DataFrame resultBoolean = (this.boolean_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.boolean_;
/* 1044 */       this.boolean_ = null;
/* 1045 */       resultBoolean.pushOnto(previous);
/* 1046 */       resultBoolean.handleAttributes(attributes);
/* 1047 */       return resultBoolean;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean popBoolean(XMLTypeResourceImpl.DataFrame boolean_) {
/* 1057 */       boolean resultBooleanValue = XMLTypeFactory.eINSTANCE.createBoolean(boolean_.popValue());
/* 1058 */       this.boolean_ = boolean_;
/* 1059 */       return resultBooleanValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushBooleanObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1069 */       XMLTypeResourceImpl.DataFrame resultBooleanObject = (this.booleanObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.booleanObject;
/* 1070 */       this.booleanObject = null;
/* 1071 */       resultBooleanObject.pushOnto(previous);
/* 1072 */       resultBooleanObject.handleAttributes(attributes);
/* 1073 */       return resultBooleanObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Boolean popBooleanObject(XMLTypeResourceImpl.DataFrame booleanObject) {
/* 1083 */       Boolean resultBooleanObjectValue = XMLTypeFactory.eINSTANCE.createBooleanObject(booleanObject.popValue());
/* 1084 */       this.booleanObject = booleanObject;
/* 1085 */       return resultBooleanObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushByte(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1095 */       XMLTypeResourceImpl.DataFrame resultByte = (this.byte_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.byte_;
/* 1096 */       this.byte_ = null;
/* 1097 */       resultByte.pushOnto(previous);
/* 1098 */       resultByte.handleAttributes(attributes);
/* 1099 */       return resultByte;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public byte popByte(XMLTypeResourceImpl.DataFrame byte_) {
/* 1109 */       byte resultByteValue = XMLTypeFactory.eINSTANCE.createByte(byte_.popValue());
/* 1110 */       this.byte_ = byte_;
/* 1111 */       return resultByteValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushByteObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1121 */       XMLTypeResourceImpl.DataFrame resultByteObject = (this.byteObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.byteObject;
/* 1122 */       this.byteObject = null;
/* 1123 */       resultByteObject.pushOnto(previous);
/* 1124 */       resultByteObject.handleAttributes(attributes);
/* 1125 */       return resultByteObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Byte popByteObject(XMLTypeResourceImpl.DataFrame byteObject) {
/* 1135 */       Byte resultByteObjectValue = XMLTypeFactory.eINSTANCE.createByteObject(byteObject.popValue());
/* 1136 */       this.byteObject = byteObject;
/* 1137 */       return resultByteObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushDate(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1147 */       XMLTypeResourceImpl.DataFrame resultDate = (this.date == null) ? new XMLTypeResourceImpl.DataFrame() : this.date;
/* 1148 */       this.date = null;
/* 1149 */       resultDate.pushOnto(previous);
/* 1150 */       resultDate.handleAttributes(attributes);
/* 1151 */       return resultDate;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popDate(XMLTypeResourceImpl.DataFrame date) {
/* 1161 */       XMLGregorianCalendar resultDateValue = XMLTypeFactory.eINSTANCE.createDate(date.popValue());
/* 1162 */       this.date = date;
/* 1163 */       return resultDateValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushDateTime(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1173 */       XMLTypeResourceImpl.DataFrame resultDateTime = (this.dateTime == null) ? new XMLTypeResourceImpl.DataFrame() : this.dateTime;
/* 1174 */       this.dateTime = null;
/* 1175 */       resultDateTime.pushOnto(previous);
/* 1176 */       resultDateTime.handleAttributes(attributes);
/* 1177 */       return resultDateTime;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popDateTime(XMLTypeResourceImpl.DataFrame dateTime) {
/* 1187 */       XMLGregorianCalendar resultDateTimeValue = XMLTypeFactory.eINSTANCE.createDateTime(dateTime.popValue());
/* 1188 */       this.dateTime = dateTime;
/* 1189 */       return resultDateTimeValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushDecimal(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1199 */       XMLTypeResourceImpl.DataFrame resultDecimal = (this.decimal == null) ? new XMLTypeResourceImpl.DataFrame() : this.decimal;
/* 1200 */       this.decimal = null;
/* 1201 */       resultDecimal.pushOnto(previous);
/* 1202 */       resultDecimal.handleAttributes(attributes);
/* 1203 */       return resultDecimal;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigDecimal popDecimal(XMLTypeResourceImpl.DataFrame decimal) {
/* 1213 */       BigDecimal resultDecimalValue = XMLTypeFactory.eINSTANCE.createDecimal(decimal.popValue());
/* 1214 */       this.decimal = decimal;
/* 1215 */       return resultDecimalValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushDouble(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1225 */       XMLTypeResourceImpl.DataFrame resultDouble = (this.double_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.double_;
/* 1226 */       this.double_ = null;
/* 1227 */       resultDouble.pushOnto(previous);
/* 1228 */       resultDouble.handleAttributes(attributes);
/* 1229 */       return resultDouble;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double popDouble(XMLTypeResourceImpl.DataFrame double_) {
/* 1239 */       double resultDoubleValue = XMLTypeFactory.eINSTANCE.createDouble(double_.popValue());
/* 1240 */       this.double_ = double_;
/* 1241 */       return resultDoubleValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushDoubleObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1251 */       XMLTypeResourceImpl.DataFrame resultDoubleObject = (this.doubleObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.doubleObject;
/* 1252 */       this.doubleObject = null;
/* 1253 */       resultDoubleObject.pushOnto(previous);
/* 1254 */       resultDoubleObject.handleAttributes(attributes);
/* 1255 */       return resultDoubleObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Double popDoubleObject(XMLTypeResourceImpl.DataFrame doubleObject) {
/* 1265 */       Double resultDoubleObjectValue = XMLTypeFactory.eINSTANCE.createDoubleObject(doubleObject.popValue());
/* 1266 */       this.doubleObject = doubleObject;
/* 1267 */       return resultDoubleObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushDuration(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1277 */       XMLTypeResourceImpl.DataFrame resultDuration = (this.duration == null) ? new XMLTypeResourceImpl.DataFrame() : this.duration;
/* 1278 */       this.duration = null;
/* 1279 */       resultDuration.pushOnto(previous);
/* 1280 */       resultDuration.handleAttributes(attributes);
/* 1281 */       return resultDuration;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Duration popDuration(XMLTypeResourceImpl.DataFrame duration) {
/* 1291 */       Duration resultDurationValue = XMLTypeFactory.eINSTANCE.createDuration(duration.popValue());
/* 1292 */       this.duration = duration;
/* 1293 */       return resultDurationValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushENTITIES(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1303 */       XMLTypeResourceImpl.DataFrame resultENTITIES = (this.entities == null) ? new XMLTypeResourceImpl.DataFrame() : this.entities;
/* 1304 */       this.entities = null;
/* 1305 */       resultENTITIES.pushOnto(previous);
/* 1306 */       resultENTITIES.handleAttributes(attributes);
/* 1307 */       return resultENTITIES;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> popENTITIES(XMLTypeResourceImpl.DataFrame entities) {
/* 1317 */       List<String> resultENTITIESValue = XMLTypeFactory.eINSTANCE.createENTITIES(entities.popValue());
/* 1318 */       this.entities = entities;
/* 1319 */       return resultENTITIESValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushENTITIESBase(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1329 */       XMLTypeResourceImpl.DataFrame resultENTITIESBase = (this.entitiesBase == null) ? new XMLTypeResourceImpl.DataFrame() : this.entitiesBase;
/* 1330 */       this.entitiesBase = null;
/* 1331 */       resultENTITIESBase.pushOnto(previous);
/* 1332 */       resultENTITIESBase.handleAttributes(attributes);
/* 1333 */       return resultENTITIESBase;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> popENTITIESBase(XMLTypeResourceImpl.DataFrame entitiesBase) {
/* 1343 */       List<String> resultENTITIESBaseValue = XMLTypeFactory.eINSTANCE.createENTITIESBase(entitiesBase.popValue());
/* 1344 */       this.entitiesBase = entitiesBase;
/* 1345 */       return resultENTITIESBaseValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushENTITY(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1355 */       XMLTypeResourceImpl.DataFrame resultENTITY = (this.entity == null) ? new XMLTypeResourceImpl.DataFrame() : this.entity;
/* 1356 */       this.entity = null;
/* 1357 */       resultENTITY.pushOnto(previous);
/* 1358 */       resultENTITY.handleAttributes(attributes);
/* 1359 */       return resultENTITY;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popENTITY(XMLTypeResourceImpl.DataFrame entity) {
/* 1369 */       String resultENTITYValue = XMLTypeFactory.eINSTANCE.createENTITY(entity.popValue());
/* 1370 */       this.entity = entity;
/* 1371 */       return resultENTITYValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushFloat(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1381 */       XMLTypeResourceImpl.DataFrame resultFloat = (this.float_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.float_;
/* 1382 */       this.float_ = null;
/* 1383 */       resultFloat.pushOnto(previous);
/* 1384 */       resultFloat.handleAttributes(attributes);
/* 1385 */       return resultFloat;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float popFloat(XMLTypeResourceImpl.DataFrame float_) {
/* 1395 */       float resultFloatValue = XMLTypeFactory.eINSTANCE.createFloat(float_.popValue());
/* 1396 */       this.float_ = float_;
/* 1397 */       return resultFloatValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushFloatObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1407 */       XMLTypeResourceImpl.DataFrame resultFloatObject = (this.floatObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.floatObject;
/* 1408 */       this.floatObject = null;
/* 1409 */       resultFloatObject.pushOnto(previous);
/* 1410 */       resultFloatObject.handleAttributes(attributes);
/* 1411 */       return resultFloatObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Float popFloatObject(XMLTypeResourceImpl.DataFrame floatObject) {
/* 1421 */       Float resultFloatObjectValue = XMLTypeFactory.eINSTANCE.createFloatObject(floatObject.popValue());
/* 1422 */       this.floatObject = floatObject;
/* 1423 */       return resultFloatObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushGDay(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1433 */       XMLTypeResourceImpl.DataFrame resultGDay = (this.gDay == null) ? new XMLTypeResourceImpl.DataFrame() : this.gDay;
/* 1434 */       this.gDay = null;
/* 1435 */       resultGDay.pushOnto(previous);
/* 1436 */       resultGDay.handleAttributes(attributes);
/* 1437 */       return resultGDay;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popGDay(XMLTypeResourceImpl.DataFrame gDay) {
/* 1447 */       XMLGregorianCalendar resultGDayValue = XMLTypeFactory.eINSTANCE.createGDay(gDay.popValue());
/* 1448 */       this.gDay = gDay;
/* 1449 */       return resultGDayValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushGMonth(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1459 */       XMLTypeResourceImpl.DataFrame resultGMonth = (this.gMonth == null) ? new XMLTypeResourceImpl.DataFrame() : this.gMonth;
/* 1460 */       this.gMonth = null;
/* 1461 */       resultGMonth.pushOnto(previous);
/* 1462 */       resultGMonth.handleAttributes(attributes);
/* 1463 */       return resultGMonth;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popGMonth(XMLTypeResourceImpl.DataFrame gMonth) {
/* 1473 */       XMLGregorianCalendar resultGMonthValue = XMLTypeFactory.eINSTANCE.createGMonth(gMonth.popValue());
/* 1474 */       this.gMonth = gMonth;
/* 1475 */       return resultGMonthValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushGMonthDay(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1485 */       XMLTypeResourceImpl.DataFrame resultGMonthDay = (this.gMonthDay == null) ? new XMLTypeResourceImpl.DataFrame() : this.gMonthDay;
/* 1486 */       this.gMonthDay = null;
/* 1487 */       resultGMonthDay.pushOnto(previous);
/* 1488 */       resultGMonthDay.handleAttributes(attributes);
/* 1489 */       return resultGMonthDay;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popGMonthDay(XMLTypeResourceImpl.DataFrame gMonthDay) {
/* 1499 */       XMLGregorianCalendar resultGMonthDayValue = XMLTypeFactory.eINSTANCE.createGMonthDay(gMonthDay.popValue());
/* 1500 */       this.gMonthDay = gMonthDay;
/* 1501 */       return resultGMonthDayValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushGYear(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1511 */       XMLTypeResourceImpl.DataFrame resultGYear = (this.gYear == null) ? new XMLTypeResourceImpl.DataFrame() : this.gYear;
/* 1512 */       this.gYear = null;
/* 1513 */       resultGYear.pushOnto(previous);
/* 1514 */       resultGYear.handleAttributes(attributes);
/* 1515 */       return resultGYear;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popGYear(XMLTypeResourceImpl.DataFrame gYear) {
/* 1525 */       XMLGregorianCalendar resultGYearValue = XMLTypeFactory.eINSTANCE.createGYear(gYear.popValue());
/* 1526 */       this.gYear = gYear;
/* 1527 */       return resultGYearValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushGYearMonth(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1537 */       XMLTypeResourceImpl.DataFrame resultGYearMonth = (this.gYearMonth == null) ? new XMLTypeResourceImpl.DataFrame() : this.gYearMonth;
/* 1538 */       this.gYearMonth = null;
/* 1539 */       resultGYearMonth.pushOnto(previous);
/* 1540 */       resultGYearMonth.handleAttributes(attributes);
/* 1541 */       return resultGYearMonth;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popGYearMonth(XMLTypeResourceImpl.DataFrame gYearMonth) {
/* 1551 */       XMLGregorianCalendar resultGYearMonthValue = XMLTypeFactory.eINSTANCE.createGYearMonth(gYearMonth.popValue());
/* 1552 */       this.gYearMonth = gYearMonth;
/* 1553 */       return resultGYearMonthValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushHexBinary(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1563 */       XMLTypeResourceImpl.DataFrame resultHexBinary = (this.hexBinary == null) ? new XMLTypeResourceImpl.DataFrame() : this.hexBinary;
/* 1564 */       this.hexBinary = null;
/* 1565 */       resultHexBinary.pushOnto(previous);
/* 1566 */       resultHexBinary.handleAttributes(attributes);
/* 1567 */       return resultHexBinary;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public byte[] popHexBinary(XMLTypeResourceImpl.DataFrame hexBinary) {
/* 1577 */       byte[] resultHexBinaryValue = XMLTypeFactory.eINSTANCE.createHexBinary(hexBinary.popValue());
/* 1578 */       this.hexBinary = hexBinary;
/* 1579 */       return resultHexBinaryValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushID(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1589 */       XMLTypeResourceImpl.DataFrame resultID = (this.id == null) ? new XMLTypeResourceImpl.DataFrame() : this.id;
/* 1590 */       this.id = null;
/* 1591 */       resultID.pushOnto(previous);
/* 1592 */       resultID.handleAttributes(attributes);
/* 1593 */       return resultID;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popID(XMLTypeResourceImpl.DataFrame id) {
/* 1603 */       String resultIDValue = XMLTypeFactory.eINSTANCE.createID(id.popValue());
/* 1604 */       this.id = id;
/* 1605 */       return resultIDValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushIDREF(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1615 */       XMLTypeResourceImpl.DataFrame resultIDREF = (this.idref == null) ? new XMLTypeResourceImpl.DataFrame() : this.idref;
/* 1616 */       this.idref = null;
/* 1617 */       resultIDREF.pushOnto(previous);
/* 1618 */       resultIDREF.handleAttributes(attributes);
/* 1619 */       return resultIDREF;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popIDREF(XMLTypeResourceImpl.DataFrame idref) {
/* 1629 */       String resultIDREFValue = XMLTypeFactory.eINSTANCE.createIDREF(idref.popValue());
/* 1630 */       this.idref = idref;
/* 1631 */       return resultIDREFValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushIDREFS(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1641 */       XMLTypeResourceImpl.DataFrame resultIDREFS = (this.idrefs == null) ? new XMLTypeResourceImpl.DataFrame() : this.idrefs;
/* 1642 */       this.idrefs = null;
/* 1643 */       resultIDREFS.pushOnto(previous);
/* 1644 */       resultIDREFS.handleAttributes(attributes);
/* 1645 */       return resultIDREFS;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> popIDREFS(XMLTypeResourceImpl.DataFrame idrefs) {
/* 1655 */       List<String> resultIDREFSValue = XMLTypeFactory.eINSTANCE.createIDREFS(idrefs.popValue());
/* 1656 */       this.idrefs = idrefs;
/* 1657 */       return resultIDREFSValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushIDREFSBase(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1667 */       XMLTypeResourceImpl.DataFrame resultIDREFSBase = (this.idrefsBase == null) ? new XMLTypeResourceImpl.DataFrame() : this.idrefsBase;
/* 1668 */       this.idrefsBase = null;
/* 1669 */       resultIDREFSBase.pushOnto(previous);
/* 1670 */       resultIDREFSBase.handleAttributes(attributes);
/* 1671 */       return resultIDREFSBase;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> popIDREFSBase(XMLTypeResourceImpl.DataFrame idrefsBase) {
/* 1681 */       List<String> resultIDREFSBaseValue = XMLTypeFactory.eINSTANCE.createIDREFSBase(idrefsBase.popValue());
/* 1682 */       this.idrefsBase = idrefsBase;
/* 1683 */       return resultIDREFSBaseValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushInt(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1693 */       XMLTypeResourceImpl.DataFrame resultInt = (this.int_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.int_;
/* 1694 */       this.int_ = null;
/* 1695 */       resultInt.pushOnto(previous);
/* 1696 */       resultInt.handleAttributes(attributes);
/* 1697 */       return resultInt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int popInt(XMLTypeResourceImpl.DataFrame int_) {
/* 1707 */       int resultIntValue = XMLTypeFactory.eINSTANCE.createInt(int_.popValue());
/* 1708 */       this.int_ = int_;
/* 1709 */       return resultIntValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1719 */       XMLTypeResourceImpl.DataFrame resultInteger = (this.integer == null) ? new XMLTypeResourceImpl.DataFrame() : this.integer;
/* 1720 */       this.integer = null;
/* 1721 */       resultInteger.pushOnto(previous);
/* 1722 */       resultInteger.handleAttributes(attributes);
/* 1723 */       return resultInteger;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigInteger popInteger(XMLTypeResourceImpl.DataFrame integer) {
/* 1733 */       BigInteger resultIntegerValue = XMLTypeFactory.eINSTANCE.createInteger(integer.popValue());
/* 1734 */       this.integer = integer;
/* 1735 */       return resultIntegerValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushIntObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1745 */       XMLTypeResourceImpl.DataFrame resultIntObject = (this.intObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.intObject;
/* 1746 */       this.intObject = null;
/* 1747 */       resultIntObject.pushOnto(previous);
/* 1748 */       resultIntObject.handleAttributes(attributes);
/* 1749 */       return resultIntObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Integer popIntObject(XMLTypeResourceImpl.DataFrame intObject) {
/* 1759 */       Integer resultIntObjectValue = XMLTypeFactory.eINSTANCE.createIntObject(intObject.popValue());
/* 1760 */       this.intObject = intObject;
/* 1761 */       return resultIntObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushLanguage(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1771 */       XMLTypeResourceImpl.DataFrame resultLanguage = (this.language == null) ? new XMLTypeResourceImpl.DataFrame() : this.language;
/* 1772 */       this.language = null;
/* 1773 */       resultLanguage.pushOnto(previous);
/* 1774 */       resultLanguage.handleAttributes(attributes);
/* 1775 */       return resultLanguage;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popLanguage(XMLTypeResourceImpl.DataFrame language) {
/* 1785 */       String resultLanguageValue = XMLTypeFactory.eINSTANCE.createLanguage(language.popValue());
/* 1786 */       this.language = language;
/* 1787 */       return resultLanguageValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushLong(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1797 */       XMLTypeResourceImpl.DataFrame resultLong = (this.long_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.long_;
/* 1798 */       this.long_ = null;
/* 1799 */       resultLong.pushOnto(previous);
/* 1800 */       resultLong.handleAttributes(attributes);
/* 1801 */       return resultLong;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long popLong(XMLTypeResourceImpl.DataFrame long_) {
/* 1811 */       long resultLongValue = XMLTypeFactory.eINSTANCE.createLong(long_.popValue());
/* 1812 */       this.long_ = long_;
/* 1813 */       return resultLongValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushLongObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1823 */       XMLTypeResourceImpl.DataFrame resultLongObject = (this.longObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.longObject;
/* 1824 */       this.longObject = null;
/* 1825 */       resultLongObject.pushOnto(previous);
/* 1826 */       resultLongObject.handleAttributes(attributes);
/* 1827 */       return resultLongObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Long popLongObject(XMLTypeResourceImpl.DataFrame longObject) {
/* 1837 */       Long resultLongObjectValue = XMLTypeFactory.eINSTANCE.createLongObject(longObject.popValue());
/* 1838 */       this.longObject = longObject;
/* 1839 */       return resultLongObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushName(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1849 */       XMLTypeResourceImpl.DataFrame resultName = (this.name == null) ? new XMLTypeResourceImpl.DataFrame() : this.name;
/* 1850 */       this.name = null;
/* 1851 */       resultName.pushOnto(previous);
/* 1852 */       resultName.handleAttributes(attributes);
/* 1853 */       return resultName;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popName(XMLTypeResourceImpl.DataFrame name) {
/* 1863 */       String resultNameValue = XMLTypeFactory.eINSTANCE.createName(name.popValue());
/* 1864 */       this.name = name;
/* 1865 */       return resultNameValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNCName(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1875 */       XMLTypeResourceImpl.DataFrame resultNCName = (this.ncName == null) ? new XMLTypeResourceImpl.DataFrame() : this.ncName;
/* 1876 */       this.ncName = null;
/* 1877 */       resultNCName.pushOnto(previous);
/* 1878 */       resultNCName.handleAttributes(attributes);
/* 1879 */       return resultNCName;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popNCName(XMLTypeResourceImpl.DataFrame ncName) {
/* 1889 */       String resultNCNameValue = XMLTypeFactory.eINSTANCE.createNCName(ncName.popValue());
/* 1890 */       this.ncName = ncName;
/* 1891 */       return resultNCNameValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNegativeInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1901 */       XMLTypeResourceImpl.DataFrame resultNegativeInteger = (this.negativeInteger == null) ? new XMLTypeResourceImpl.DataFrame() : this.negativeInteger;
/* 1902 */       this.negativeInteger = null;
/* 1903 */       resultNegativeInteger.pushOnto(previous);
/* 1904 */       resultNegativeInteger.handleAttributes(attributes);
/* 1905 */       return resultNegativeInteger;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigInteger popNegativeInteger(XMLTypeResourceImpl.DataFrame negativeInteger) {
/* 1915 */       BigInteger resultNegativeIntegerValue = XMLTypeFactory.eINSTANCE.createNegativeInteger(negativeInteger.popValue());
/* 1916 */       this.negativeInteger = negativeInteger;
/* 1917 */       return resultNegativeIntegerValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNMTOKEN(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1927 */       XMLTypeResourceImpl.DataFrame resultNMTOKEN = (this.nmtoken == null) ? new XMLTypeResourceImpl.DataFrame() : this.nmtoken;
/* 1928 */       this.nmtoken = null;
/* 1929 */       resultNMTOKEN.pushOnto(previous);
/* 1930 */       resultNMTOKEN.handleAttributes(attributes);
/* 1931 */       return resultNMTOKEN;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popNMTOKEN(XMLTypeResourceImpl.DataFrame nmtoken) {
/* 1941 */       String resultNMTOKENValue = XMLTypeFactory.eINSTANCE.createNMTOKEN(nmtoken.popValue());
/* 1942 */       this.nmtoken = nmtoken;
/* 1943 */       return resultNMTOKENValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNMTOKENS(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1953 */       XMLTypeResourceImpl.DataFrame resultNMTOKENS = (this.nmtokens == null) ? new XMLTypeResourceImpl.DataFrame() : this.nmtokens;
/* 1954 */       this.nmtokens = null;
/* 1955 */       resultNMTOKENS.pushOnto(previous);
/* 1956 */       resultNMTOKENS.handleAttributes(attributes);
/* 1957 */       return resultNMTOKENS;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> popNMTOKENS(XMLTypeResourceImpl.DataFrame nmtokens) {
/* 1967 */       List<String> resultNMTOKENSValue = XMLTypeFactory.eINSTANCE.createNMTOKENS(nmtokens.popValue());
/* 1968 */       this.nmtokens = nmtokens;
/* 1969 */       return resultNMTOKENSValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNMTOKENSBase(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 1979 */       XMLTypeResourceImpl.DataFrame resultNMTOKENSBase = (this.nmtokensBase == null) ? new XMLTypeResourceImpl.DataFrame() : this.nmtokensBase;
/* 1980 */       this.nmtokensBase = null;
/* 1981 */       resultNMTOKENSBase.pushOnto(previous);
/* 1982 */       resultNMTOKENSBase.handleAttributes(attributes);
/* 1983 */       return resultNMTOKENSBase;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> popNMTOKENSBase(XMLTypeResourceImpl.DataFrame nmtokensBase) {
/* 1993 */       List<String> resultNMTOKENSBaseValue = XMLTypeFactory.eINSTANCE.createNMTOKENSBase(nmtokensBase.popValue());
/* 1994 */       this.nmtokensBase = nmtokensBase;
/* 1995 */       return resultNMTOKENSBaseValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNonNegativeInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2005 */       XMLTypeResourceImpl.DataFrame resultNonNegativeInteger = (this.nonNegativeInteger == null) ? new XMLTypeResourceImpl.DataFrame() : this.nonNegativeInteger;
/* 2006 */       this.nonNegativeInteger = null;
/* 2007 */       resultNonNegativeInteger.pushOnto(previous);
/* 2008 */       resultNonNegativeInteger.handleAttributes(attributes);
/* 2009 */       return resultNonNegativeInteger;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigInteger popNonNegativeInteger(XMLTypeResourceImpl.DataFrame nonNegativeInteger) {
/* 2019 */       BigInteger resultNonNegativeIntegerValue = XMLTypeFactory.eINSTANCE.createNonNegativeInteger(nonNegativeInteger.popValue());
/* 2020 */       this.nonNegativeInteger = nonNegativeInteger;
/* 2021 */       return resultNonNegativeIntegerValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNonPositiveInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2031 */       XMLTypeResourceImpl.DataFrame resultNonPositiveInteger = (this.nonPositiveInteger == null) ? new XMLTypeResourceImpl.DataFrame() : this.nonPositiveInteger;
/* 2032 */       this.nonPositiveInteger = null;
/* 2033 */       resultNonPositiveInteger.pushOnto(previous);
/* 2034 */       resultNonPositiveInteger.handleAttributes(attributes);
/* 2035 */       return resultNonPositiveInteger;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigInteger popNonPositiveInteger(XMLTypeResourceImpl.DataFrame nonPositiveInteger) {
/* 2045 */       BigInteger resultNonPositiveIntegerValue = XMLTypeFactory.eINSTANCE.createNonPositiveInteger(nonPositiveInteger.popValue());
/* 2046 */       this.nonPositiveInteger = nonPositiveInteger;
/* 2047 */       return resultNonPositiveIntegerValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNormalizedString(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2057 */       XMLTypeResourceImpl.DataFrame resultNormalizedString = (this.normalizedString == null) ? new XMLTypeResourceImpl.DataFrame() : this.normalizedString;
/* 2058 */       this.normalizedString = null;
/* 2059 */       resultNormalizedString.pushOnto(previous);
/* 2060 */       resultNormalizedString.handleAttributes(attributes);
/* 2061 */       return resultNormalizedString;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popNormalizedString(XMLTypeResourceImpl.DataFrame normalizedString) {
/* 2071 */       String resultNormalizedStringValue = XMLTypeFactory.eINSTANCE.createNormalizedString(normalizedString.popValue());
/* 2072 */       this.normalizedString = normalizedString;
/* 2073 */       return resultNormalizedStringValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushNOTATION(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2083 */       XMLTypeResourceImpl.DataFrame resultNOTATION = (this.notation == null) ? new XMLTypeResourceImpl.DataFrame() : this.notation;
/* 2084 */       this.notation = null;
/* 2085 */       resultNOTATION.pushOnto(previous);
/* 2086 */       resultNOTATION.handleAttributes(attributes);
/* 2087 */       return resultNOTATION;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public QName popNOTATION(XMLTypeResourceImpl.DataFrame notation) {
/* 2097 */       QName resultNOTATIONValue = XMLTypeFactory.eINSTANCE.createNOTATION(notation.popValue());
/* 2098 */       this.notation = notation;
/* 2099 */       return resultNOTATIONValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushPositiveInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2109 */       XMLTypeResourceImpl.DataFrame resultPositiveInteger = (this.positiveInteger == null) ? new XMLTypeResourceImpl.DataFrame() : this.positiveInteger;
/* 2110 */       this.positiveInteger = null;
/* 2111 */       resultPositiveInteger.pushOnto(previous);
/* 2112 */       resultPositiveInteger.handleAttributes(attributes);
/* 2113 */       return resultPositiveInteger;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigInteger popPositiveInteger(XMLTypeResourceImpl.DataFrame positiveInteger) {
/* 2123 */       BigInteger resultPositiveIntegerValue = XMLTypeFactory.eINSTANCE.createPositiveInteger(positiveInteger.popValue());
/* 2124 */       this.positiveInteger = positiveInteger;
/* 2125 */       return resultPositiveIntegerValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushQName(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2135 */       XMLTypeResourceImpl.DataFrame resultQName = (this.qName == null) ? new XMLTypeResourceImpl.DataFrame() : this.qName;
/* 2136 */       this.qName = null;
/* 2137 */       resultQName.pushOnto(previous);
/* 2138 */       resultQName.handleAttributes(attributes);
/* 2139 */       return resultQName;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public QName popQName(XMLTypeResourceImpl.DataFrame qName) {
/* 2149 */       QName resultQNameValue = XMLTypeFactory.eINSTANCE.createQName(qName.popValue());
/* 2150 */       this.qName = qName;
/* 2151 */       return resultQNameValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushShort(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2161 */       XMLTypeResourceImpl.DataFrame resultShort = (this.short_ == null) ? new XMLTypeResourceImpl.DataFrame() : this.short_;
/* 2162 */       this.short_ = null;
/* 2163 */       resultShort.pushOnto(previous);
/* 2164 */       resultShort.handleAttributes(attributes);
/* 2165 */       return resultShort;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short popShort(XMLTypeResourceImpl.DataFrame short_) {
/* 2175 */       short resultShortValue = XMLTypeFactory.eINSTANCE.createShort(short_.popValue());
/* 2176 */       this.short_ = short_;
/* 2177 */       return resultShortValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushShortObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2187 */       XMLTypeResourceImpl.DataFrame resultShortObject = (this.shortObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.shortObject;
/* 2188 */       this.shortObject = null;
/* 2189 */       resultShortObject.pushOnto(previous);
/* 2190 */       resultShortObject.handleAttributes(attributes);
/* 2191 */       return resultShortObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Short popShortObject(XMLTypeResourceImpl.DataFrame shortObject) {
/* 2201 */       Short resultShortObjectValue = XMLTypeFactory.eINSTANCE.createShortObject(shortObject.popValue());
/* 2202 */       this.shortObject = shortObject;
/* 2203 */       return resultShortObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushString(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2213 */       XMLTypeResourceImpl.DataFrame resultString = (this.string == null) ? new XMLTypeResourceImpl.DataFrame() : this.string;
/* 2214 */       this.string = null;
/* 2215 */       resultString.pushOnto(previous);
/* 2216 */       resultString.handleAttributes(attributes);
/* 2217 */       return resultString;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popString(XMLTypeResourceImpl.DataFrame string) {
/* 2227 */       String resultStringValue = string.popValue();
/* 2228 */       this.string = string;
/* 2229 */       return resultStringValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushTime(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2239 */       XMLTypeResourceImpl.DataFrame resultTime = (this.time == null) ? new XMLTypeResourceImpl.DataFrame() : this.time;
/* 2240 */       this.time = null;
/* 2241 */       resultTime.pushOnto(previous);
/* 2242 */       resultTime.handleAttributes(attributes);
/* 2243 */       return resultTime;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLGregorianCalendar popTime(XMLTypeResourceImpl.DataFrame time) {
/* 2253 */       XMLGregorianCalendar resultTimeValue = XMLTypeFactory.eINSTANCE.createTime(time.popValue());
/* 2254 */       this.time = time;
/* 2255 */       return resultTimeValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushToken(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2265 */       XMLTypeResourceImpl.DataFrame resultToken = (this.token == null) ? new XMLTypeResourceImpl.DataFrame() : this.token;
/* 2266 */       this.token = null;
/* 2267 */       resultToken.pushOnto(previous);
/* 2268 */       resultToken.handleAttributes(attributes);
/* 2269 */       return resultToken;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String popToken(XMLTypeResourceImpl.DataFrame token) {
/* 2279 */       String resultTokenValue = XMLTypeFactory.eINSTANCE.createToken(token.popValue());
/* 2280 */       this.token = token;
/* 2281 */       return resultTokenValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedByte(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2291 */       XMLTypeResourceImpl.DataFrame resultUnsignedByte = (this.unsignedByte == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedByte;
/* 2292 */       this.unsignedByte = null;
/* 2293 */       resultUnsignedByte.pushOnto(previous);
/* 2294 */       resultUnsignedByte.handleAttributes(attributes);
/* 2295 */       return resultUnsignedByte;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short popUnsignedByte(XMLTypeResourceImpl.DataFrame unsignedByte) {
/* 2305 */       short resultUnsignedByteValue = XMLTypeFactory.eINSTANCE.createUnsignedByte(unsignedByte.popValue());
/* 2306 */       this.unsignedByte = unsignedByte;
/* 2307 */       return resultUnsignedByteValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedByteObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2317 */       XMLTypeResourceImpl.DataFrame resultUnsignedByteObject = (this.unsignedByteObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedByteObject;
/* 2318 */       this.unsignedByteObject = null;
/* 2319 */       resultUnsignedByteObject.pushOnto(previous);
/* 2320 */       resultUnsignedByteObject.handleAttributes(attributes);
/* 2321 */       return resultUnsignedByteObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Short popUnsignedByteObject(XMLTypeResourceImpl.DataFrame unsignedByteObject) {
/* 2331 */       Short resultUnsignedByteObjectValue = XMLTypeFactory.eINSTANCE.createUnsignedByteObject(unsignedByteObject.popValue());
/* 2332 */       this.unsignedByteObject = unsignedByteObject;
/* 2333 */       return resultUnsignedByteObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedInt(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2343 */       XMLTypeResourceImpl.DataFrame resultUnsignedInt = (this.unsignedInt == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedInt;
/* 2344 */       this.unsignedInt = null;
/* 2345 */       resultUnsignedInt.pushOnto(previous);
/* 2346 */       resultUnsignedInt.handleAttributes(attributes);
/* 2347 */       return resultUnsignedInt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long popUnsignedInt(XMLTypeResourceImpl.DataFrame unsignedInt) {
/* 2357 */       long resultUnsignedIntValue = XMLTypeFactory.eINSTANCE.createUnsignedInt(unsignedInt.popValue());
/* 2358 */       this.unsignedInt = unsignedInt;
/* 2359 */       return resultUnsignedIntValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedIntObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2369 */       XMLTypeResourceImpl.DataFrame resultUnsignedIntObject = (this.unsignedIntObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedIntObject;
/* 2370 */       this.unsignedIntObject = null;
/* 2371 */       resultUnsignedIntObject.pushOnto(previous);
/* 2372 */       resultUnsignedIntObject.handleAttributes(attributes);
/* 2373 */       return resultUnsignedIntObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Long popUnsignedIntObject(XMLTypeResourceImpl.DataFrame unsignedIntObject) {
/* 2383 */       Long resultUnsignedIntObjectValue = XMLTypeFactory.eINSTANCE.createUnsignedIntObject(unsignedIntObject.popValue());
/* 2384 */       this.unsignedIntObject = unsignedIntObject;
/* 2385 */       return resultUnsignedIntObjectValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedLong(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2395 */       XMLTypeResourceImpl.DataFrame resultUnsignedLong = (this.unsignedLong == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedLong;
/* 2396 */       this.unsignedLong = null;
/* 2397 */       resultUnsignedLong.pushOnto(previous);
/* 2398 */       resultUnsignedLong.handleAttributes(attributes);
/* 2399 */       return resultUnsignedLong;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BigInteger popUnsignedLong(XMLTypeResourceImpl.DataFrame unsignedLong) {
/* 2409 */       BigInteger resultUnsignedLongValue = XMLTypeFactory.eINSTANCE.createUnsignedLong(unsignedLong.popValue());
/* 2410 */       this.unsignedLong = unsignedLong;
/* 2411 */       return resultUnsignedLongValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedShort(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2421 */       XMLTypeResourceImpl.DataFrame resultUnsignedShort = (this.unsignedShort == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedShort;
/* 2422 */       this.unsignedShort = null;
/* 2423 */       resultUnsignedShort.pushOnto(previous);
/* 2424 */       resultUnsignedShort.handleAttributes(attributes);
/* 2425 */       return resultUnsignedShort;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int popUnsignedShort(XMLTypeResourceImpl.DataFrame unsignedShort) {
/* 2435 */       int resultUnsignedShortValue = XMLTypeFactory.eINSTANCE.createUnsignedShort(unsignedShort.popValue());
/* 2436 */       this.unsignedShort = unsignedShort;
/* 2437 */       return resultUnsignedShortValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLTypeResourceImpl.DataFrame pushUnsignedShortObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes) {
/* 2447 */       XMLTypeResourceImpl.DataFrame resultUnsignedShortObject = (this.unsignedShortObject == null) ? new XMLTypeResourceImpl.DataFrame() : this.unsignedShortObject;
/* 2448 */       this.unsignedShortObject = null;
/* 2449 */       resultUnsignedShortObject.pushOnto(previous);
/* 2450 */       resultUnsignedShortObject.handleAttributes(attributes);
/* 2451 */       return resultUnsignedShortObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Integer popUnsignedShortObject(XMLTypeResourceImpl.DataFrame unsignedShortObject) {
/* 2461 */       Integer resultUnsignedShortObjectValue = XMLTypeFactory.eINSTANCE.createUnsignedShortObject(unsignedShortObject.popValue());
/* 2462 */       this.unsignedShortObject = unsignedShortObject;
/* 2463 */       return resultUnsignedShortObjectValue;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class StackFrame
/*      */   {
/*      */     private StackFrame previous;
/*      */ 
/*      */     
/*      */     public final void pushOnto(StackFrame previous) {
/* 2474 */       this.previous = previous;
/* 2475 */       create();
/*      */     }
/*      */ 
/*      */     
/*      */     public final void pop() {
/* 2480 */       this.previous = null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void handleAttributes(Attributes attributes) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public StackFrame startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/* 2490 */       throw new SAXException("Unexpected start element");
/*      */     }
/*      */ 
/*      */     
/*      */     public void endElement(StackFrame child) throws SAXException {
/* 2495 */       throw new SAXException("Unexpected end element");
/*      */     }
/*      */ 
/*      */     
/*      */     public final StackFrame endElement() throws SAXException {
/* 2500 */       StackFrame result = this.previous;
/* 2501 */       this.previous.endElement(this);
/* 2502 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void characters(char[] text, int start, int length) throws SAXException {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void create() {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DataFrame
/*      */     extends StackFrame
/*      */   {
/*      */     protected StringBuffer stringBuffer;
/*      */ 
/*      */     
/*      */     public void characters(char[] text, int start, int length) throws SAXException {
/* 2523 */       if (this.stringBuffer == null)
/*      */       {
/* 2525 */         this.stringBuffer = new StringBuffer();
/*      */       }
/* 2527 */       this.stringBuffer.append(text, start, length);
/*      */     }
/*      */ 
/*      */     
/*      */     public String popValue() {
/* 2532 */       if (this.stringBuffer == null) {
/*      */         
/* 2534 */         pop();
/* 2535 */         return null;
/*      */       } 
/*      */ 
/*      */       
/* 2539 */       String result = this.stringBuffer.toString();
/* 2540 */       this.stringBuffer.setLength(0);
/* 2541 */       pop();
/* 2542 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Handler
/*      */     extends DefaultHandler
/*      */   {
/* 2549 */     protected XMLTypeResourceImpl.StackFrame stackFrame = null;
/*      */ 
/*      */     
/*      */     public Handler(XMLTypeResourceImpl.StackFrame stackFrame) {
/* 2553 */       this.stackFrame = stackFrame;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/* 2559 */       this.stackFrame = this.stackFrame.startElement(uri, localName, qName, attributes);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void endElement(String uri, String localName, String qName) throws SAXException {
/* 2565 */       this.stackFrame = this.stackFrame.endElement();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void characters(char[] text, int start, int length) throws SAXException {
/* 2571 */       this.stackFrame.characters(text, start, length);
/*      */     }
/*      */     
/*      */     public void error(SAXParseException exception) throws SAXException {}
/*      */     
/*      */     public void fatalError(SAXParseException exception) throws SAXException {}
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\typ\\util\XMLTypeResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */