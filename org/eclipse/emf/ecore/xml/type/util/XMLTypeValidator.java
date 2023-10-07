/*      */ package org.eclipse.emf.ecore.xml.type.util;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.xml.datatype.Duration;
/*      */ import javax.xml.datatype.XMLGregorianCalendar;
/*      */ import javax.xml.namespace.QName;
/*      */ import org.eclipse.emf.common.util.DiagnosticChain;
/*      */ import org.eclipse.emf.common.util.ResourceLocator;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EValidator;
/*      */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*      */ import org.eclipse.emf.ecore.util.EObjectValidator;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
/*      */ public class XMLTypeValidator
/*      */   extends EObjectValidator
/*      */ {
/*   58 */   public static final XMLTypeValidator INSTANCE = new XMLTypeValidator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.xml.type";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int WELL_FORMED_XML_GREGORIAN_CALENDAR = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int DIAGNOSTIC_CODE_COUNT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EPackage getEPackage() {
/*  108 */     return (EPackage)XMLTypePackage.eINSTANCE;
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
/*      */   protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  120 */     switch (classifierID) {
/*      */       
/*      */       case 0:
/*  123 */         return validateAnyType((AnyType)value, diagnostics, context);
/*      */       case 1:
/*  125 */         return validateProcessingInstruction((ProcessingInstruction)value, diagnostics, context);
/*      */       case 2:
/*  127 */         return validateSimpleAnyType((SimpleAnyType)value, diagnostics, context);
/*      */       case 3:
/*  129 */         return validateXMLTypeDocumentRoot((XMLTypeDocumentRoot)value, diagnostics, context);
/*      */       case 4:
/*  131 */         return validateAnySimpleType(value, diagnostics, context);
/*      */       case 5:
/*  133 */         return validateAnyURI((String)value, diagnostics, context);
/*      */       case 6:
/*  135 */         return validateBase64Binary((byte[])value, diagnostics, context);
/*      */       case 7:
/*  137 */         return validateBoolean(((Boolean)value).booleanValue(), diagnostics, context);
/*      */       case 8:
/*  139 */         return validateBooleanObject((Boolean)value, diagnostics, context);
/*      */       case 9:
/*  141 */         return validateByte(((Byte)value).byteValue(), diagnostics, context);
/*      */       case 10:
/*  143 */         return validateByteObject((Byte)value, diagnostics, context);
/*      */       case 11:
/*  145 */         return validateDate((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 12:
/*  147 */         return validateDateTime((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 13:
/*  149 */         return validateDecimal((BigDecimal)value, diagnostics, context);
/*      */       case 14:
/*  151 */         return validateDouble(((Double)value).doubleValue(), diagnostics, context);
/*      */       case 15:
/*  153 */         return validateDoubleObject((Double)value, diagnostics, context);
/*      */       case 16:
/*  155 */         return validateDuration((Duration)value, diagnostics, context);
/*      */       case 17:
/*  157 */         return validateENTITIES((List)value, diagnostics, context);
/*      */       case 18:
/*  159 */         return validateENTITIESBase((List)value, diagnostics, context);
/*      */       case 19:
/*  161 */         return validateENTITY((String)value, diagnostics, context);
/*      */       case 20:
/*  163 */         return validateFloat(((Float)value).floatValue(), diagnostics, context);
/*      */       case 21:
/*  165 */         return validateFloatObject((Float)value, diagnostics, context);
/*      */       case 22:
/*  167 */         return validateGDay((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 23:
/*  169 */         return validateGMonth((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 24:
/*  171 */         return validateGMonthDay((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 25:
/*  173 */         return validateGYear((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 26:
/*  175 */         return validateGYearMonth((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 27:
/*  177 */         return validateHexBinary((byte[])value, diagnostics, context);
/*      */       case 28:
/*  179 */         return validateID((String)value, diagnostics, context);
/*      */       case 29:
/*  181 */         return validateIDREF((String)value, diagnostics, context);
/*      */       case 30:
/*  183 */         return validateIDREFS((List)value, diagnostics, context);
/*      */       case 31:
/*  185 */         return validateIDREFSBase((List)value, diagnostics, context);
/*      */       case 32:
/*  187 */         return validateInt(((Integer)value).intValue(), diagnostics, context);
/*      */       case 33:
/*  189 */         return validateInteger((BigInteger)value, diagnostics, context);
/*      */       case 34:
/*  191 */         return validateIntObject((Integer)value, diagnostics, context);
/*      */       case 35:
/*  193 */         return validateLanguage((String)value, diagnostics, context);
/*      */       case 36:
/*  195 */         return validateLong(((Long)value).longValue(), diagnostics, context);
/*      */       case 37:
/*  197 */         return validateLongObject((Long)value, diagnostics, context);
/*      */       case 38:
/*  199 */         return validateName((String)value, diagnostics, context);
/*      */       case 39:
/*  201 */         return validateNCName((String)value, diagnostics, context);
/*      */       case 40:
/*  203 */         return validateNegativeInteger((BigInteger)value, diagnostics, context);
/*      */       case 41:
/*  205 */         return validateNMTOKEN((String)value, diagnostics, context);
/*      */       case 42:
/*  207 */         return validateNMTOKENS((List)value, diagnostics, context);
/*      */       case 43:
/*  209 */         return validateNMTOKENSBase((List)value, diagnostics, context);
/*      */       case 44:
/*  211 */         return validateNonNegativeInteger((BigInteger)value, diagnostics, context);
/*      */       case 45:
/*  213 */         return validateNonPositiveInteger((BigInteger)value, diagnostics, context);
/*      */       case 46:
/*  215 */         return validateNormalizedString((String)value, diagnostics, context);
/*      */       case 47:
/*  217 */         return validateNOTATION((QName)value, diagnostics, context);
/*      */       case 48:
/*  219 */         return validatePositiveInteger((BigInteger)value, diagnostics, context);
/*      */       case 49:
/*  221 */         return validateQName((QName)value, diagnostics, context);
/*      */       case 50:
/*  223 */         return validateShort(((Short)value).shortValue(), diagnostics, context);
/*      */       case 51:
/*  225 */         return validateShortObject((Short)value, diagnostics, context);
/*      */       case 52:
/*  227 */         return validateString((String)value, diagnostics, context);
/*      */       case 53:
/*  229 */         return validateTime((XMLGregorianCalendar)value, diagnostics, context);
/*      */       case 54:
/*  231 */         return validateToken((String)value, diagnostics, context);
/*      */       case 55:
/*  233 */         return validateUnsignedByte(((Short)value).shortValue(), diagnostics, context);
/*      */       case 56:
/*  235 */         return validateUnsignedByteObject((Short)value, diagnostics, context);
/*      */       case 57:
/*  237 */         return validateUnsignedInt(((Long)value).longValue(), diagnostics, context);
/*      */       case 58:
/*  239 */         return validateUnsignedIntObject((Long)value, diagnostics, context);
/*      */       case 59:
/*  241 */         return validateUnsignedLong((BigInteger)value, diagnostics, context);
/*      */       case 60:
/*  243 */         return validateUnsignedShort(((Integer)value).intValue(), diagnostics, context);
/*      */       case 61:
/*  245 */         return validateUnsignedShortObject((Integer)value, diagnostics, context);
/*      */     } 
/*  247 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateAnyType(AnyType anyType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  258 */     return validate_EveryDefaultConstraint((EObject)anyType, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateProcessingInstruction(ProcessingInstruction processingInstruction, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  268 */     return validate_EveryDefaultConstraint((EObject)processingInstruction, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateSimpleAnyType(SimpleAnyType simpleAnyType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  278 */     return validate_EveryDefaultConstraint((EObject)simpleAnyType, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateXMLTypeDocumentRoot(XMLTypeDocumentRoot xmlTypeDocumentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  288 */     return validate_EveryDefaultConstraint((EObject)xmlTypeDocumentRoot, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateAnySimpleType(Object anySimpleType, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  298 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateAnyURI(String anyURI, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  308 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateBase64Binary(byte[] base64Binary, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  318 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateBoolean(boolean boolean_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  328 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateBooleanObject(Boolean booleanObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  338 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateByte(byte byte_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  348 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateByteObject(Byte byteObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  358 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateDate(XMLGregorianCalendar date, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  368 */     boolean result = (date.isValid() && "date".equals(date.getXMLSchemaType().getLocalPart()));
/*  369 */     if (!result && diagnostics != null)
/*      */     {
/*  371 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.DATE, date, diagnostics, context);
/*      */     }
/*  373 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateDate(Object date, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  379 */     return validateDate((XMLGregorianCalendar)date, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateDateTime(XMLGregorianCalendar dateTime, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  389 */     boolean result = (dateTime.isValid() && "dateTime".equals(dateTime.getXMLSchemaType().getLocalPart()));
/*  390 */     if (!result && diagnostics != null)
/*      */     {
/*  392 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.DATE_TIME, dateTime, diagnostics, context);
/*      */     }
/*  394 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateDateTime(Object dateTime, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  400 */     return validateDateTime((XMLGregorianCalendar)dateTime, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateDecimal(BigDecimal decimal, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  410 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateDouble(double double_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  420 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateDoubleObject(Double doubleObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  430 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateDuration(Duration duration, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  440 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateDuration(Object duration, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  446 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENTITIES(List<?> entities, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  456 */     boolean result = validateENTITIESBase_ItemType(entities, diagnostics, context);
/*  457 */     if (result || diagnostics != null) result &= validateENTITIES_MinLength(entities, diagnostics, context); 
/*  458 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENTITIES_MinLength(List<?> entities, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  469 */     int length = entities.size();
/*  470 */     boolean result = (length >= 1);
/*  471 */     if (!result && diagnostics != null)
/*  472 */       reportMinLengthViolation(XMLTypePackage.Literals.ENTITIES, entities, length, 1, diagnostics, context); 
/*  473 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENTITIESBase(List<?> entitiesBase, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  483 */     boolean result = validateENTITIESBase_ItemType(entitiesBase, diagnostics, context);
/*  484 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENTITIESBase_ItemType(List<?> entitiesBase, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  495 */     boolean result = true;
/*  496 */     for (Iterator<?> i = entitiesBase.iterator(); i.hasNext() && (result || diagnostics != null); ) {
/*      */       
/*  498 */       Object item = i.next();
/*  499 */       if (XMLTypePackage.Literals.ENTITY.isInstance(item)) {
/*      */         
/*  501 */         result &= validateENTITY((String)item, diagnostics, context);
/*      */         
/*      */         continue;
/*      */       } 
/*  505 */       result = false;
/*  506 */       reportDataValueTypeViolation(XMLTypePackage.Literals.ENTITY, item, diagnostics, context);
/*      */     } 
/*      */     
/*  509 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateENTITY(String entity, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  519 */     boolean result = validateNCName_Pattern(entity, diagnostics, context);
/*  520 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateFloat(float float_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  530 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateFloatObject(Float floatObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  540 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateGDay(XMLGregorianCalendar gDay, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  550 */     boolean result = (gDay.isValid() && "gDay".equals(gDay.getXMLSchemaType().getLocalPart()));
/*  551 */     if (!result && diagnostics != null)
/*      */     {
/*  553 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.GDAY, gDay, diagnostics, context);
/*      */     }
/*  555 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateGDay(Object gDay, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  561 */     return validateGDay((XMLGregorianCalendar)gDay, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateGMonth(XMLGregorianCalendar gMonth, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  571 */     boolean result = (gMonth.isValid() && "gMonth".equals(gMonth.getXMLSchemaType().getLocalPart()));
/*  572 */     if (!result && diagnostics != null)
/*      */     {
/*  574 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.GMONTH, gMonth, diagnostics, context);
/*      */     }
/*  576 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateGMonth(Object gMonth, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  582 */     return validateGMonth((XMLGregorianCalendar)gMonth, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateGMonthDay(XMLGregorianCalendar gMonthDay, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  592 */     boolean result = (gMonthDay.isValid() && "gMonthDay".equals(gMonthDay.getXMLSchemaType().getLocalPart()));
/*  593 */     if (!result && diagnostics != null)
/*      */     {
/*  595 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.GMONTH_DAY, gMonthDay, diagnostics, context);
/*      */     }
/*  597 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateGMonthDay(Object gMonthDay, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  603 */     return validateGMonthDay((XMLGregorianCalendar)gMonthDay, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateGYear(XMLGregorianCalendar gYear, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  613 */     boolean result = (gYear.isValid() && "gYear".equals(gYear.getXMLSchemaType().getLocalPart()));
/*  614 */     if (!result && diagnostics != null)
/*      */     {
/*  616 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.GYEAR, gYear, diagnostics, context);
/*      */     }
/*  618 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateGYear(Object gYear, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  624 */     return validateGYear((XMLGregorianCalendar)gYear, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateGYearMonth(XMLGregorianCalendar gYearMonth, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  634 */     boolean result = (gYearMonth.isValid() && "gYearMonth".equals(gYearMonth.getXMLSchemaType().getLocalPart()));
/*  635 */     if (!result && diagnostics != null)
/*      */     {
/*  637 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.GYEAR_MONTH, gYearMonth, diagnostics, context);
/*      */     }
/*  639 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateGYearMonth(Object gYearMonth, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  645 */     return validateGYearMonth((XMLGregorianCalendar)gYearMonth, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateHexBinary(byte[] hexBinary, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  655 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateID(String id, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  665 */     boolean result = validateNCName_Pattern(id, diagnostics, context);
/*  666 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateIDREF(String idref, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  676 */     boolean result = validateNCName_Pattern(idref, diagnostics, context);
/*  677 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateIDREFS(List<?> idrefs, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  687 */     boolean result = validateIDREFSBase_ItemType(idrefs, diagnostics, context);
/*  688 */     if (result || diagnostics != null) result &= validateIDREFS_MinLength(idrefs, diagnostics, context); 
/*  689 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateIDREFS_MinLength(List<?> idrefs, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  700 */     int length = idrefs.size();
/*  701 */     boolean result = (length >= 1);
/*  702 */     if (!result && diagnostics != null)
/*  703 */       reportMinLengthViolation(XMLTypePackage.Literals.IDREFS, idrefs, length, 1, diagnostics, context); 
/*  704 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateIDREFSBase(List<?> idrefsBase, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  714 */     boolean result = validateIDREFSBase_ItemType(idrefsBase, diagnostics, context);
/*  715 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateIDREFSBase_ItemType(List<?> idrefsBase, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  726 */     boolean result = true;
/*  727 */     for (Iterator<?> i = idrefsBase.iterator(); i.hasNext() && (result || diagnostics != null); ) {
/*      */       
/*  729 */       Object item = i.next();
/*  730 */       if (XMLTypePackage.Literals.IDREF.isInstance(item)) {
/*      */         
/*  732 */         result &= validateIDREF((String)item, diagnostics, context);
/*      */         
/*      */         continue;
/*      */       } 
/*  736 */       result = false;
/*  737 */       reportDataValueTypeViolation(XMLTypePackage.Literals.IDREF, item, diagnostics, context);
/*      */     } 
/*      */     
/*  740 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateInt(int int_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  750 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateInteger(BigInteger integer, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  760 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateIntObject(Integer intObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  770 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateLanguage(String language, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  780 */     boolean result = validateLanguage_Pattern(language, diagnostics, context);
/*  781 */     return result;
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
/*  792 */   public static final EValidator.PatternMatcher[][] LANGUAGE__PATTERN__VALUES = new EValidator.PatternMatcher[][] {
/*      */       
/*      */       {
/*  795 */         XMLTypeUtil.createPatternMatcher("[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*")
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateLanguage_Pattern(String language, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  807 */     return validatePattern(XMLTypePackage.Literals.LANGUAGE, language, LANGUAGE__PATTERN__VALUES, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateLong(long long_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  817 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateLongObject(Long longObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  827 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateName(String name, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  837 */     boolean result = validateName_Pattern(name, diagnostics, context);
/*  838 */     return result;
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
/*  849 */   public static final EValidator.PatternMatcher[][] NAME__PATTERN__VALUES = new EValidator.PatternMatcher[][] {
/*      */       
/*      */       {
/*  852 */         XMLTypeUtil.createPatternMatcher("\\i\\c*")
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateName_Pattern(String name, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  864 */     return validatePattern(XMLTypePackage.Literals.NAME, name, NAME__PATTERN__VALUES, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNCName(String ncName, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  874 */     boolean result = validateNCName_Pattern(ncName, diagnostics, context);
/*  875 */     return result;
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
/*  886 */   public static final EValidator.PatternMatcher[][] NC_NAME__PATTERN__VALUES = new EValidator.PatternMatcher[][] {
/*      */       
/*      */       {
/*  889 */         XMLTypeUtil.createPatternMatcher("[\\i-[:]][\\c-[:]]*")
/*      */       
/*      */       },
/*      */       {
/*  893 */         XMLTypeUtil.createPatternMatcher("\\i\\c*")
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNCName_Pattern(String ncName, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  905 */     return validatePattern(XMLTypePackage.Literals.NC_NAME, ncName, NC_NAME__PATTERN__VALUES, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNegativeInteger(BigInteger negativeInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  915 */     boolean result = validateNegativeInteger_Max(negativeInteger, diagnostics, context);
/*  916 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  925 */   public static final BigInteger NEGATIVE_INTEGER__MAX__VALUE = new BigInteger("-1");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNegativeInteger_Max(BigInteger negativeInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  935 */     boolean result = (negativeInteger.compareTo(NEGATIVE_INTEGER__MAX__VALUE) <= 0);
/*  936 */     if (!result && diagnostics != null)
/*  937 */       reportMaxViolation(XMLTypePackage.Literals.NEGATIVE_INTEGER, negativeInteger, NEGATIVE_INTEGER__MAX__VALUE, true, diagnostics, context); 
/*  938 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNMTOKEN(String nmtoken, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  948 */     boolean result = validateNMTOKEN_Pattern(nmtoken, diagnostics, context);
/*  949 */     return result;
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
/*  960 */   public static final EValidator.PatternMatcher[][] NMTOKEN__PATTERN__VALUES = new EValidator.PatternMatcher[][] {
/*      */       
/*      */       {
/*  963 */         XMLTypeUtil.createPatternMatcher("\\c+")
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNMTOKEN_Pattern(String nmtoken, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  975 */     return validatePattern(XMLTypePackage.Literals.NMTOKEN, nmtoken, NMTOKEN__PATTERN__VALUES, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNMTOKENS(List<?> nmtokens, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  985 */     boolean result = validateNMTOKENSBase_ItemType(nmtokens, diagnostics, context);
/*  986 */     if (result || diagnostics != null) result &= validateNMTOKENS_MinLength(nmtokens, diagnostics, context); 
/*  987 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNMTOKENS_MinLength(List<?> nmtokens, DiagnosticChain diagnostics, Map<Object, Object> context) {
/*  998 */     int length = nmtokens.size();
/*  999 */     boolean result = (length >= 1);
/* 1000 */     if (!result && diagnostics != null)
/* 1001 */       reportMinLengthViolation(XMLTypePackage.Literals.NMTOKENS, nmtokens, length, 1, diagnostics, context); 
/* 1002 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNMTOKENSBase(List<?> nmtokensBase, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1012 */     boolean result = validateNMTOKENSBase_ItemType(nmtokensBase, diagnostics, context);
/* 1013 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNMTOKENSBase_ItemType(List<?> nmtokensBase, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1024 */     boolean result = true;
/* 1025 */     for (Iterator<?> i = nmtokensBase.iterator(); i.hasNext() && (result || diagnostics != null); ) {
/*      */       
/* 1027 */       Object item = i.next();
/* 1028 */       if (XMLTypePackage.Literals.NMTOKEN.isInstance(item)) {
/*      */         
/* 1030 */         result &= validateNMTOKEN((String)item, diagnostics, context);
/*      */         
/*      */         continue;
/*      */       } 
/* 1034 */       result = false;
/* 1035 */       reportDataValueTypeViolation(XMLTypePackage.Literals.NMTOKEN, item, diagnostics, context);
/*      */     } 
/*      */     
/* 1038 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNonNegativeInteger(BigInteger nonNegativeInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1048 */     boolean result = validateNonNegativeInteger_Min(nonNegativeInteger, diagnostics, context);
/* 1049 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1058 */   public static final BigInteger NON_NEGATIVE_INTEGER__MIN__VALUE = new BigInteger("0");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNonNegativeInteger_Min(BigInteger nonNegativeInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1068 */     boolean result = (nonNegativeInteger.compareTo(NON_NEGATIVE_INTEGER__MIN__VALUE) >= 0);
/* 1069 */     if (!result && diagnostics != null)
/* 1070 */       reportMinViolation(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, nonNegativeInteger, NON_NEGATIVE_INTEGER__MIN__VALUE, true, diagnostics, context); 
/* 1071 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNonPositiveInteger(BigInteger nonPositiveInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1081 */     boolean result = validateNonPositiveInteger_Max(nonPositiveInteger, diagnostics, context);
/* 1082 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1091 */   public static final BigInteger NON_POSITIVE_INTEGER__MAX__VALUE = new BigInteger("0");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNonPositiveInteger_Max(BigInteger nonPositiveInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1101 */     boolean result = (nonPositiveInteger.compareTo(NON_POSITIVE_INTEGER__MAX__VALUE) <= 0);
/* 1102 */     if (!result && diagnostics != null)
/* 1103 */       reportMaxViolation(XMLTypePackage.Literals.NON_POSITIVE_INTEGER, nonPositiveInteger, NON_POSITIVE_INTEGER__MAX__VALUE, true, diagnostics, context); 
/* 1104 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNormalizedString(String normalizedString, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1114 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateNOTATION(QName notation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1124 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateNOTATION(Object notation, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1130 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validatePositiveInteger(BigInteger positiveInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1140 */     boolean result = validatePositiveInteger_Min(positiveInteger, diagnostics, context);
/* 1141 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1150 */   public static final BigInteger POSITIVE_INTEGER__MIN__VALUE = new BigInteger("1");
/*      */   
/*      */   public static final short UNSIGNED_BYTE__MIN__VALUE = 0;
/*      */   
/*      */   public static final short UNSIGNED_BYTE__MAX__VALUE = 255;
/*      */   
/*      */   public static final long UNSIGNED_INT__MIN__VALUE = 0L;
/*      */   public static final long UNSIGNED_INT__MAX__VALUE = 4294967295L;
/*      */   
/*      */   public boolean validatePositiveInteger_Min(BigInteger positiveInteger, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1160 */     boolean result = (positiveInteger.compareTo(POSITIVE_INTEGER__MIN__VALUE) >= 0);
/* 1161 */     if (!result && diagnostics != null)
/* 1162 */       reportMinViolation(XMLTypePackage.Literals.POSITIVE_INTEGER, positiveInteger, POSITIVE_INTEGER__MIN__VALUE, true, diagnostics, context); 
/* 1163 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateQName(QName qName, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1173 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateQName(Object qName, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1179 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateShort(short short_, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1189 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateShortObject(Short shortObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1199 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateString(String string, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1209 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateTime(XMLGregorianCalendar time, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1219 */     boolean result = (time.isValid() && "time".equals(time.getXMLSchemaType().getLocalPart()));
/* 1220 */     if (!result && diagnostics != null)
/*      */     {
/* 1222 */       reportXMLGregorianCalendarViolation(XMLTypePackage.Literals.TIME, time, diagnostics, context);
/*      */     }
/* 1224 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean validateTime(Object time, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1230 */     return validateTime((XMLGregorianCalendar)time, diagnostics, context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateToken(String token, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1240 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedByte(short unsignedByte, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1250 */     boolean result = validateUnsignedByte_Min(unsignedByte, diagnostics, context);
/* 1251 */     if (result || diagnostics != null) result &= validateUnsignedByte_Max(unsignedByte, diagnostics, context); 
/* 1252 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedByte_Min(short unsignedByte, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1271 */     boolean result = (unsignedByte >= 0);
/* 1272 */     if (!result && diagnostics != null)
/* 1273 */       reportMinViolation(XMLTypePackage.Literals.UNSIGNED_BYTE, Short.valueOf(unsignedByte), Short.valueOf((short)0), true, diagnostics, context); 
/* 1274 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedByte_Max(short unsignedByte, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1293 */     boolean result = (unsignedByte <= 255);
/* 1294 */     if (!result && diagnostics != null)
/* 1295 */       reportMaxViolation(XMLTypePackage.Literals.UNSIGNED_BYTE, Short.valueOf(unsignedByte), Short.valueOf((short)255), true, diagnostics, context); 
/* 1296 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedByteObject(Short unsignedByteObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1306 */     boolean result = validateUnsignedByte_Min(unsignedByteObject.shortValue(), diagnostics, context);
/* 1307 */     if (result || diagnostics != null) result &= validateUnsignedByte_Max(unsignedByteObject.shortValue(), diagnostics, context); 
/* 1308 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedInt(long unsignedInt, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1318 */     boolean result = validateUnsignedInt_Min(unsignedInt, diagnostics, context);
/* 1319 */     if (result || diagnostics != null) result &= validateUnsignedInt_Max(unsignedInt, diagnostics, context); 
/* 1320 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedInt_Min(long unsignedInt, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1339 */     boolean result = (unsignedInt >= 0L);
/* 1340 */     if (!result && diagnostics != null)
/* 1341 */       reportMinViolation(XMLTypePackage.Literals.UNSIGNED_INT, Long.valueOf(unsignedInt), Long.valueOf(0L), true, diagnostics, context); 
/* 1342 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedInt_Max(long unsignedInt, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1361 */     boolean result = (unsignedInt <= 4294967295L);
/* 1362 */     if (!result && diagnostics != null)
/* 1363 */       reportMaxViolation(XMLTypePackage.Literals.UNSIGNED_INT, Long.valueOf(unsignedInt), Long.valueOf(4294967295L), true, diagnostics, context); 
/* 1364 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedIntObject(Long unsignedIntObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1374 */     boolean result = validateUnsignedInt_Min(unsignedIntObject.longValue(), diagnostics, context);
/* 1375 */     if (result || diagnostics != null) result &= validateUnsignedInt_Max(unsignedIntObject.longValue(), diagnostics, context); 
/* 1376 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedLong(BigInteger unsignedLong, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1386 */     boolean result = validateUnsignedLong_Min(unsignedLong, diagnostics, context);
/* 1387 */     if (result || diagnostics != null) result &= validateUnsignedLong_Max(unsignedLong, diagnostics, context); 
/* 1388 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1397 */   public static final BigInteger UNSIGNED_LONG__MIN__VALUE = new BigInteger("0");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedLong_Min(BigInteger unsignedLong, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1407 */     boolean result = (unsignedLong.compareTo(UNSIGNED_LONG__MIN__VALUE) >= 0);
/* 1408 */     if (!result && diagnostics != null)
/* 1409 */       reportMinViolation(XMLTypePackage.Literals.UNSIGNED_LONG, unsignedLong, UNSIGNED_LONG__MIN__VALUE, true, diagnostics, context); 
/* 1410 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1419 */   public static final BigInteger UNSIGNED_LONG__MAX__VALUE = new BigInteger("18446744073709551615");
/*      */ 
/*      */   
/*      */   public static final int UNSIGNED_SHORT__MIN__VALUE = 0;
/*      */ 
/*      */   
/*      */   public static final int UNSIGNED_SHORT__MAX__VALUE = 65535;
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedLong_Max(BigInteger unsignedLong, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1429 */     boolean result = (unsignedLong.compareTo(UNSIGNED_LONG__MAX__VALUE) <= 0);
/* 1430 */     if (!result && diagnostics != null)
/* 1431 */       reportMaxViolation(XMLTypePackage.Literals.UNSIGNED_LONG, unsignedLong, UNSIGNED_LONG__MAX__VALUE, true, diagnostics, context); 
/* 1432 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedShort(int unsignedShort, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1442 */     boolean result = validateUnsignedShort_Min(unsignedShort, diagnostics, context);
/* 1443 */     if (result || diagnostics != null) result &= validateUnsignedShort_Max(unsignedShort, diagnostics, context); 
/* 1444 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedShort_Min(int unsignedShort, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1463 */     boolean result = (unsignedShort >= 0);
/* 1464 */     if (!result && diagnostics != null)
/* 1465 */       reportMinViolation(XMLTypePackage.Literals.UNSIGNED_SHORT, Integer.valueOf(unsignedShort), Integer.valueOf(0), true, diagnostics, context); 
/* 1466 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedShort_Max(int unsignedShort, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1485 */     boolean result = (unsignedShort <= 65535);
/* 1486 */     if (!result && diagnostics != null)
/* 1487 */       reportMaxViolation(XMLTypePackage.Literals.UNSIGNED_SHORT, Integer.valueOf(unsignedShort), Integer.valueOf(65535), true, diagnostics, context); 
/* 1488 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validateUnsignedShortObject(Integer unsignedShortObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1498 */     boolean result = validateUnsignedShort_Min(unsignedShortObject.intValue(), diagnostics, context);
/* 1499 */     if (result || diagnostics != null) result &= validateUnsignedShort_Max(unsignedShortObject.intValue(), diagnostics, context); 
/* 1500 */     return result;
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
/*      */   public ResourceLocator getResourceLocator() {
/* 1512 */     return (ResourceLocator)EcorePlugin.INSTANCE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportXMLGregorianCalendarViolation(EDataType eDataType, XMLGregorianCalendar value, DiagnosticChain diagnostics, Map<Object, Object> context) {
/* 1518 */     createDiagnostic(
/* 1519 */         4, 
/* 1520 */         "org.eclipse.emf.ecore.xml.type", 
/* 1521 */         0, 
/* 1522 */         "_UI_BadXMLGregorianCalendar_diagnostic", 
/*      */         
/* 1524 */         new Object[] {
/* 1525 */           getValueLabel(eDataType, value, context), 
/* 1526 */           ExtendedMetaData.INSTANCE.getName((EClassifier)eDataType)
/*      */         
/* 1528 */         }new Object[] { value
/* 1529 */         }, context);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\typ\\util\XMLTypeValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */