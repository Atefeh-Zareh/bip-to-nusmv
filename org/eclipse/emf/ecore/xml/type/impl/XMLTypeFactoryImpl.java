/*      */ package org.eclipse.emf.ecore.xml.type.impl;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.text.DateFormat;
/*      */ import java.text.FieldPosition;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.xml.datatype.Duration;
/*      */ import javax.xml.datatype.XMLGregorianCalendar;
/*      */ import javax.xml.namespace.QName;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*      */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*      */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
/*      */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.internal.DataValue;
/*      */ import org.eclipse.emf.ecore.xml.type.internal.QName;
/*      */ import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;
/*      */ import org.eclipse.emf.ecore.xml.type.internal.XMLDuration;
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
/*      */ public class XMLTypeFactoryImpl
/*      */   extends EFactoryImpl
/*      */   implements XMLTypeFactory
/*      */ {
/*      */   public Object createAnySimpleType(String literal) {
/*   65 */     return literal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertAnySimpleType(Object instanceValue) {
/*   75 */     return (instanceValue == null) ? null : instanceValue.toString();
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
/*      */   public String createAnyURI(String literal) {
/*  107 */     return literal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertAnyURI(String instanceValue) {
/*  117 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] createBase64Binary(String literal) {
/*  127 */     if (literal == null) return null; 
/*  128 */     byte[] value = DataValue.Base64.decode(collapseWhiteSpace(literal));
/*  129 */     if (value == null)
/*      */     {
/*  131 */       throw new InvalidDatatypeValueException("Invalid base64Binary value: '" + literal + "'");
/*      */     }
/*  133 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertBase64Binary(byte[] instanceValue) {
/*  143 */     return (instanceValue == null) ? null : DataValue.Base64.encode(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createBoolean(String initialValue) {
/*  153 */     return (initialValue == null) ? false : primitiveBooleanValueOf(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertBoolean(boolean instanceValue) {
/*  163 */     return instanceValue ? "true" : "false";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean createBooleanObject(String literal) {
/*  173 */     return (literal == null) ? null : booleanValueOf(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertBooleanObject(Boolean instanceValue) {
/*  183 */     return (instanceValue == null) ? null : convertBoolean(instanceValue.booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte createByte(String literal) {
/*  193 */     return (literal == null) ? 0 : Byte.parseByte(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertByte(byte instanceValue) {
/*  203 */     return Byte.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Byte createByteObject(String literal) {
/*  213 */     return (literal == null) ? null : Byte.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertByteObject(Byte instanceValue) {
/*  223 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createDate(String literal) {
/*  233 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDate(XMLGregorianCalendar instanceValue) {
/*  243 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertDate(Object instanceValue) {
/*  249 */     if (instanceValue == null)
/*      */     {
/*  251 */       return null;
/*      */     }
/*  253 */     if (instanceValue instanceof Date) {
/*      */ 
/*      */ 
/*      */       
/*  257 */       XMLGregorianCalendar value = (new XMLCalendar((Date)instanceValue, (short)2)).normalize();
/*  258 */       value.setHour(-2147483648);
/*  259 */       value.setMinute(-2147483648);
/*  260 */       value.setSecond(-2147483648);
/*  261 */       value.setMillisecond(-2147483648);
/*  262 */       return value.toString();
/*      */     } 
/*  264 */     return instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createDateTime(String literal) {
/*  274 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDateTime(XMLGregorianCalendar instanceValue) {
/*  284 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertDateTime(Object instanceValue) {
/*  290 */     if (instanceValue == null)
/*      */     {
/*  292 */       return null;
/*      */     }
/*  294 */     if (instanceValue instanceof Date)
/*      */     {
/*  296 */       return EDATE_FORMATS[0].format((Date)instanceValue);
/*      */     }
/*  298 */     return instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigDecimal createDecimal(String literal) {
/*  308 */     return (literal == null) ? null : new BigDecimal(collapseWhiteSpace(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDecimal(BigDecimal instanceValue) {
/*  318 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double createDouble(String literal) {
/*  328 */     return (literal == null) ? 0.0D : Double.parseDouble(collapseWhiteSpace(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDouble(double instanceValue) {
/*  338 */     return Double.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double createDoubleObject(String literal) {
/*  348 */     return (literal == null) ? null : Double.valueOf(collapseWhiteSpace(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDoubleObject(Double instanceValue) {
/*  358 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Duration createDuration(String literal) {
/*  368 */     return (literal == null) ? null : (Duration)new XMLDuration(collapseWhiteSpace(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDuration(Duration instanceValue) {
/*  378 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertDuration(Object instanceValue) {
/*  384 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createENTITIES(String literal) {
/*  394 */     return createENTITIESBase(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createENTITIESFromString(EDataType eDataType, String initialValue) {
/*  404 */     return createENTITIESBaseFromString(XMLTypePackage.Literals.ENTITIES_BASE, initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertENTITIES(List<? extends String> instanceValue) {
/*  414 */     return convertENTITIESBase(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertENTITIESToString(EDataType eDataType, Object instanceValue) {
/*  424 */     return convertENTITIESBaseToString(XMLTypePackage.Literals.ENTITIES_BASE, instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createENTITIESBase(String literal) {
/*  434 */     if (literal == null) return null; 
/*  435 */     List<String> result = new ArrayList<String>();
/*  436 */     for (StringTokenizer stringTokenizer = new StringTokenizer(literal); stringTokenizer.hasMoreTokens(); ) {
/*      */       
/*  438 */       String item = stringTokenizer.nextToken();
/*  439 */       result.add(createENTITY(item));
/*      */     } 
/*  441 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createENTITIESBaseFromString(EDataType eDataType, String initialValue) {
/*  451 */     return createENTITIESBase(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertENTITIESBase(List<? extends String> instanceValue) {
/*  461 */     if (instanceValue == null) return null; 
/*  462 */     if (instanceValue.isEmpty()) return ""; 
/*  463 */     StringBuffer result = new StringBuffer();
/*  464 */     for (String item : instanceValue) {
/*      */       
/*  466 */       result.append(convertENTITY(item));
/*  467 */       result.append(' ');
/*      */     } 
/*  469 */     return result.substring(0, result.length() - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertENTITIESBaseToString(EDataType eDataType, Object instanceValue) {
/*  479 */     if (instanceValue == null) return null; 
/*  480 */     List<?> list = (List)instanceValue;
/*  481 */     if (list.isEmpty()) return ""; 
/*  482 */     StringBuffer result = new StringBuffer();
/*  483 */     for (Iterator<?> i = list.iterator(); i.hasNext(); ) {
/*      */       
/*  485 */       result.append(convertENTITYToString(XMLTypePackage.Literals.ENTITY, i.next()));
/*  486 */       result.append(' ');
/*      */     } 
/*  488 */     return result.substring(0, result.length() - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createENTITY(String literal) {
/*  498 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertENTITY(String instanceValue) {
/*  508 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float createFloat(String literal) {
/*  518 */     return (literal == null) ? 0.0F : Float.parseFloat(collapseWhiteSpace(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertFloat(float instanceValue) {
/*  528 */     return Float.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Float createFloatObject(String literal) {
/*  538 */     return (literal == null) ? null : Float.valueOf(collapseWhiteSpace(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertFloatObject(Float instanceValue) {
/*  548 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createGDay(String literal) {
/*  558 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGDay(XMLGregorianCalendar instanceValue) {
/*  568 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public String convertGDay(Object instanceValue) {
/*  573 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createGMonth(String literal) {
/*  583 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGMonth(XMLGregorianCalendar instanceValue) {
/*  593 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertGMonth(Object instanceValue) {
/*  599 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createGMonthDay(String literal) {
/*  609 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGMonthDay(XMLGregorianCalendar instanceValue) {
/*  619 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertGMonthDay(Object instanceValue) {
/*  625 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createGYear(String literal) {
/*  635 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGYear(XMLGregorianCalendar instanceValue) {
/*  645 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertGYear(Object instanceValue) {
/*  651 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createGYearMonth(String literal) {
/*  661 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGYearMonth(XMLGregorianCalendar instanceValue) {
/*  671 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertGYearMonth(Object instanceValue) {
/*  677 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] createHexBinary(String literal) {
/*  687 */     if (literal == null) return null; 
/*  688 */     byte[] value = DataValue.HexBin.decode(collapseWhiteSpace(literal));
/*  689 */     if (value == null)
/*      */     {
/*  691 */       throw new InvalidDatatypeValueException("Invalid hexBinary value: '" + literal + "'");
/*      */     }
/*  693 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertHexBinary(byte[] instanceValue) {
/*  703 */     return (instanceValue == null) ? null : DataValue.HexBin.encode(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createID(String literal) {
/*  713 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createIDFromString(EDataType eDataType, String initialValue) {
/*  723 */     return createID(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertID(String instanceValue) {
/*  733 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createIDREF(String literal) {
/*  743 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createIDREFFromString(EDataType eDataType, String initialValue) {
/*  753 */     return createIDREF(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDREF(String instanceValue) {
/*  763 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createIDREFS(String literal) {
/*  773 */     return createIDREFSBase(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createIDREFSFromString(EDataType eDataType, String initialValue) {
/*  783 */     return createIDREFSBaseFromString(XMLTypePackage.Literals.IDREFS_BASE, initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDREFS(List<? extends String> instanceValue) {
/*  793 */     return convertIDREFSBase(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDREFSToString(EDataType eDataType, Object instanceValue) {
/*  803 */     return convertIDREFSBaseToString(XMLTypePackage.Literals.IDREFS_BASE, instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createIDREFSBase(String literal) {
/*  813 */     if (literal == null) return null; 
/*  814 */     List<String> result = new ArrayList<String>();
/*  815 */     for (StringTokenizer stringTokenizer = new StringTokenizer(literal); stringTokenizer.hasMoreTokens(); ) {
/*      */       
/*  817 */       String item = stringTokenizer.nextToken();
/*  818 */       result.add(createIDREF(item));
/*      */     } 
/*  820 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createIDREFSBaseFromString(EDataType eDataType, String initialValue) {
/*  830 */     return createIDREFSBase(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDREFSBase(List<? extends String> instanceValue) {
/*  840 */     if (instanceValue == null) return null; 
/*  841 */     if (instanceValue.isEmpty()) return ""; 
/*  842 */     StringBuffer result = new StringBuffer();
/*  843 */     for (String item : instanceValue) {
/*      */       
/*  845 */       result.append(convertIDREF(item));
/*  846 */       result.append(' ');
/*      */     } 
/*  848 */     return result.substring(0, result.length() - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDREFSBaseToString(EDataType eDataType, Object instanceValue) {
/*  859 */     return convertIDREFSBase((List<? extends String>)instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int createInt(String initialValue) {
/*  869 */     return (initialValue == null) ? 0 : Integer.parseInt(collapseWhiteSpaceAndLeadingPlus(initialValue));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertInt(int instanceValue) {
/*  879 */     return Integer.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createInteger(String literal) {
/*  889 */     return (literal == null) ? null : new BigInteger(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertInteger(BigInteger instanceValue) {
/*  899 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer createIntObject(String literal) {
/*  909 */     return (literal == null) ? null : Integer.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIntObject(Integer instanceValue) {
/*  919 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createLanguage(String literal) {
/*  929 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertLanguage(String instanceValue) {
/*  939 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long createLong(String literal) {
/*  949 */     return (literal == null) ? 0L : Long.parseLong(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertLong(long instanceValue) {
/*  959 */     return Long.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long createLongObject(String literal) {
/*  969 */     return (literal == null) ? null : Long.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertLongObject(Long instanceValue) {
/*  979 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createName(String literal) {
/*  989 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertName(String instanceValue) {
/*  999 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNCName(String literal) {
/* 1009 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNCName(String instanceValue) {
/* 1019 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createNegativeInteger(String literal) {
/* 1029 */     return createNonPositiveIntegerFromString(XMLTypePackage.Literals.NON_POSITIVE_INTEGER, literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createNegativeIntegerFromString(EDataType eDataType, String initialValue) {
/* 1039 */     return createNonPositiveIntegerFromString(XMLTypePackage.Literals.NON_POSITIVE_INTEGER, initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNegativeInteger(BigInteger instanceValue) {
/* 1049 */     return convertNonPositiveInteger(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNegativeIntegerToString(EDataType eDataType, Object instanceValue) {
/* 1059 */     return convertNonPositiveIntegerToString(XMLTypePackage.Literals.NON_POSITIVE_INTEGER, instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNMTOKEN(String literal) {
/* 1069 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNMTOKEN(String instanceValue) {
/* 1079 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createNMTOKENS(String literal) {
/* 1089 */     return createNMTOKENSBase(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createNMTOKENSFromString(EDataType eDataType, String initialValue) {
/* 1099 */     return createNMTOKENSBaseFromString(XMLTypePackage.Literals.NMTOKENS_BASE, initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNMTOKENS(List<? extends String> instanceValue) {
/* 1109 */     return convertNMTOKENSBase(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNMTOKENSToString(EDataType eDataType, Object instanceValue) {
/* 1119 */     return convertNMTOKENSBaseToString(XMLTypePackage.Literals.NMTOKENS_BASE, instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createNMTOKENSBase(String literal) {
/* 1129 */     if (literal == null) return null; 
/* 1130 */     List<String> result = new ArrayList<String>();
/* 1131 */     for (StringTokenizer stringTokenizer = new StringTokenizer(literal); stringTokenizer.hasMoreTokens(); ) {
/*      */       
/* 1133 */       String item = stringTokenizer.nextToken();
/* 1134 */       result.add(createNMTOKEN(item));
/*      */     } 
/* 1136 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> createNMTOKENSBaseFromString(EDataType eDataType, String initialValue) {
/* 1146 */     return createNMTOKENSBase(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNMTOKENSBase(List<? extends String> instanceValue) {
/* 1156 */     if (instanceValue == null) return null; 
/* 1157 */     if (instanceValue.isEmpty()) return ""; 
/* 1158 */     StringBuffer result = new StringBuffer();
/* 1159 */     for (String item : instanceValue) {
/*      */       
/* 1161 */       result.append(convertNMTOKEN(item));
/* 1162 */       result.append(' ');
/*      */     } 
/* 1164 */     return result.substring(0, result.length() - 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public String convertNMTOKENSBaseToString(EDataType eDataType, Object instanceValue) {
/* 1169 */     if (instanceValue == null) return null; 
/* 1170 */     List<?> list = (List)instanceValue;
/* 1171 */     if (list.isEmpty()) return ""; 
/* 1172 */     StringBuffer result = new StringBuffer();
/* 1173 */     for (Iterator<?> i = list.iterator(); i.hasNext(); ) {
/*      */       
/* 1175 */       result.append(convertNMTOKENToString(XMLTypePackage.Literals.NMTOKEN, i.next()));
/* 1176 */       result.append(' ');
/*      */     } 
/* 1178 */     return result.substring(0, result.length() - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createNonNegativeInteger(String literal) {
/* 1188 */     return (literal == null) ? null : new BigInteger(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNonNegativeInteger(BigInteger instanceValue) {
/* 1198 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createNonPositiveInteger(String literal) {
/* 1208 */     return (literal == null) ? null : new BigInteger(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNonPositiveInteger(BigInteger instanceValue) {
/* 1218 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNormalizedString(String literal) {
/* 1228 */     return replaceWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNormalizedString(String instanceValue) {
/* 1238 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QName createNOTATION(String literal) {
/* 1248 */     return createQName(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNOTATION(QName instanceValue) {
/* 1258 */     return convertQName(instanceValue);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertNOTATION(Object instanceValue) {
/* 1264 */     return convertNOTATION((QName)instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createPositiveInteger(String literal) {
/* 1274 */     return createNonNegativeInteger(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createPositiveIntegerFromString(EDataType eDataType, String initialValue) {
/* 1284 */     return createNonNegativeIntegerFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertPositiveInteger(BigInteger instanceValue) {
/* 1294 */     return convertNonNegativeInteger(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertPositiveIntegerToString(EDataType eDataType, Object instanceValue) {
/* 1304 */     return convertNonNegativeIntegerToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QName createQName(String literal) {
/* 1314 */     String normalizedLiteral = collapseWhiteSpace(literal);
/* 1315 */     if (literal == null)
/*      */     {
/* 1317 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1321 */     if (normalizedLiteral.startsWith("{")) {
/*      */ 
/*      */       
/* 1324 */       int index = normalizedLiteral.lastIndexOf('}');
/* 1325 */       if (index != -1)
/*      */       {
/* 1327 */         return (QName)new QName(normalizedLiteral.substring(1, index), normalizedLiteral.substring(index + 1), "");
/*      */       }
/*      */     } 
/* 1330 */     return (QName)new QName(normalizedLiteral);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public QName createQName(String namespaceURI, String localPart) {
/* 1336 */     return (QName)new QName(namespaceURI, localPart, "");
/*      */   }
/*      */ 
/*      */   
/*      */   public QName createQName(String namespaceURI, String localPart, String prefix) {
/* 1341 */     return (QName)new QName(namespaceURI, localPart, prefix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertQName(QName instanceValue) {
/* 1351 */     if (instanceValue == null)
/*      */     {
/* 1353 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1357 */     String prefix = instanceValue.getPrefix();
/* 1358 */     return (prefix.length() == 0) ? instanceValue.getLocalPart() : (String.valueOf(prefix) + ':' + instanceValue.getLocalPart());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertQName(Object instanceValue) {
/* 1365 */     return convertQName((QName)instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short createShort(String literal) {
/* 1375 */     return (literal == null) ? 0 : Short.parseShort(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertShort(short instanceValue) {
/* 1385 */     return Short.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Short createShortObject(String literal) {
/* 1395 */     return (literal == null) ? null : Short.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertShortObject(Short instanceValue) {
/* 1405 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createString(String initialValue) {
/* 1415 */     return initialValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertString(String instanceValue) {
/* 1425 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLGregorianCalendar createTime(String literal) {
/* 1435 */     return (literal == null) ? null : (XMLGregorianCalendar)new XMLCalendar(collapseWhiteSpace(literal), (short)1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertTime(XMLGregorianCalendar instanceValue) {
/* 1445 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String convertTime(Object instanceValue) {
/* 1451 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createToken(String literal) {
/* 1461 */     return collapseWhiteSpace(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertToken(String instanceValue) {
/* 1471 */     return instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short createUnsignedByte(String literal) {
/* 1481 */     return (literal == null) ? 0 : Short.parseShort(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedByte(short instanceValue) {
/* 1491 */     return Short.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Short createUnsignedByteObject(String literal) {
/* 1501 */     return (literal == null) ? null : Short.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedByteObject(Short instanceValue) {
/* 1511 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long createUnsignedInt(String literal) {
/* 1521 */     return (literal == null) ? 0L : Long.parseLong(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedInt(long instanceValue) {
/* 1531 */     return Long.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long createUnsignedIntObject(String literal) {
/* 1541 */     return (literal == null) ? null : Long.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedIntObject(Long instanceValue) {
/* 1551 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createUnsignedLong(String literal) {
/* 1561 */     return createNonNegativeInteger(literal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createUnsignedLongFromString(EDataType eDataType, String initialValue) {
/* 1571 */     return createNonNegativeIntegerFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedLong(BigInteger instanceValue) {
/* 1581 */     return convertNonNegativeInteger(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedLongToString(EDataType eDataType, Object instanceValue) {
/* 1591 */     return convertNonNegativeIntegerToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int createUnsignedShort(String literal) {
/* 1601 */     return (literal == null) ? 0 : Integer.parseInt(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedShort(int instanceValue) {
/* 1611 */     return Integer.toString(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer createUnsignedShortObject(String literal) {
/* 1621 */     return (literal == null) ? null : Integer.valueOf(collapseWhiteSpaceAndLeadingPlus(literal));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedShortObject(Integer instanceValue) {
/* 1631 */     return (instanceValue == null) ? null : instanceValue.toString();
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
/*      */   public static XMLTypeFactory init() {
/*      */     try {
/* 1644 */       XMLTypeFactory theXMLTypeFactory = (XMLTypeFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2003/XMLType");
/* 1645 */       if (theXMLTypeFactory != null)
/*      */       {
/* 1647 */         return theXMLTypeFactory;
/*      */       }
/*      */     }
/* 1650 */     catch (Exception exception) {
/*      */       
/* 1652 */       EcorePlugin.INSTANCE.log(exception);
/*      */     } 
/* 1654 */     return new XMLTypeFactoryImpl();
/*      */   }
/*      */ 
/*      */   
/* 1658 */   protected static final DateFormat[] EDATE_FORMATS = new DateFormat[] {
/* 1659 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), 
/* 1660 */       new SafeSimpleDateFormat("yyyy-MM-ddZ")
/*      */     };
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
/*      */   public EObject create(EClass eClass) {
/* 1682 */     switch (eClass.getClassifierID()) {
/*      */       case 0:
/* 1684 */         return (EObject)createAnyType();
/* 1685 */       case 1: return (EObject)createProcessingInstruction();
/* 1686 */       case 2: return (EObject)createSimpleAnyType();
/* 1687 */       case 3: return (EObject)createXMLTypeDocumentRoot();
/*      */     } 
/* 1689 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
/*      */   public Object createFromString(EDataType eDataType, String initialValue) {
/* 1701 */     switch (eDataType.getClassifierID()) {
/*      */       
/*      */       case 4:
/* 1704 */         return createAnySimpleTypeFromString(eDataType, initialValue);
/*      */       case 5:
/* 1706 */         return createAnyURIFromString(eDataType, initialValue);
/*      */       case 6:
/* 1708 */         return createBase64BinaryFromString(eDataType, initialValue);
/*      */       case 7:
/* 1710 */         return createBooleanFromString(eDataType, initialValue);
/*      */       case 8:
/* 1712 */         return createBooleanObjectFromString(eDataType, initialValue);
/*      */       case 9:
/* 1714 */         return createByteFromString(eDataType, initialValue);
/*      */       case 10:
/* 1716 */         return createByteObjectFromString(eDataType, initialValue);
/*      */       case 11:
/* 1718 */         return createDateFromString(eDataType, initialValue);
/*      */       case 12:
/* 1720 */         return createDateTimeFromString(eDataType, initialValue);
/*      */       case 13:
/* 1722 */         return createDecimalFromString(eDataType, initialValue);
/*      */       case 14:
/* 1724 */         return createDoubleFromString(eDataType, initialValue);
/*      */       case 15:
/* 1726 */         return createDoubleObjectFromString(eDataType, initialValue);
/*      */       case 16:
/* 1728 */         return createDurationFromString(eDataType, initialValue);
/*      */       case 17:
/* 1730 */         return createENTITIESFromString(eDataType, initialValue);
/*      */       case 18:
/* 1732 */         return createENTITIESBaseFromString(eDataType, initialValue);
/*      */       case 19:
/* 1734 */         return createENTITYFromString(eDataType, initialValue);
/*      */       case 20:
/* 1736 */         return createFloatFromString(eDataType, initialValue);
/*      */       case 21:
/* 1738 */         return createFloatObjectFromString(eDataType, initialValue);
/*      */       case 22:
/* 1740 */         return createGDayFromString(eDataType, initialValue);
/*      */       case 23:
/* 1742 */         return createGMonthFromString(eDataType, initialValue);
/*      */       case 24:
/* 1744 */         return createGMonthDayFromString(eDataType, initialValue);
/*      */       case 25:
/* 1746 */         return createGYearFromString(eDataType, initialValue);
/*      */       case 26:
/* 1748 */         return createGYearMonthFromString(eDataType, initialValue);
/*      */       case 27:
/* 1750 */         return createHexBinaryFromString(eDataType, initialValue);
/*      */       case 28:
/* 1752 */         return createIDFromString(eDataType, initialValue);
/*      */       case 29:
/* 1754 */         return createIDREFFromString(eDataType, initialValue);
/*      */       case 30:
/* 1756 */         return createIDREFSFromString(eDataType, initialValue);
/*      */       case 31:
/* 1758 */         return createIDREFSBaseFromString(eDataType, initialValue);
/*      */       case 32:
/* 1760 */         return createIntFromString(eDataType, initialValue);
/*      */       case 33:
/* 1762 */         return createIntegerFromString(eDataType, initialValue);
/*      */       case 34:
/* 1764 */         return createIntObjectFromString(eDataType, initialValue);
/*      */       case 35:
/* 1766 */         return createLanguageFromString(eDataType, initialValue);
/*      */       case 36:
/* 1768 */         return createLongFromString(eDataType, initialValue);
/*      */       case 37:
/* 1770 */         return createLongObjectFromString(eDataType, initialValue);
/*      */       case 38:
/* 1772 */         return createNameFromString(eDataType, initialValue);
/*      */       case 39:
/* 1774 */         return createNCNameFromString(eDataType, initialValue);
/*      */       case 40:
/* 1776 */         return createNegativeIntegerFromString(eDataType, initialValue);
/*      */       case 41:
/* 1778 */         return createNMTOKENFromString(eDataType, initialValue);
/*      */       case 42:
/* 1780 */         return createNMTOKENSFromString(eDataType, initialValue);
/*      */       case 43:
/* 1782 */         return createNMTOKENSBaseFromString(eDataType, initialValue);
/*      */       case 44:
/* 1784 */         return createNonNegativeIntegerFromString(eDataType, initialValue);
/*      */       case 45:
/* 1786 */         return createNonPositiveIntegerFromString(eDataType, initialValue);
/*      */       case 46:
/* 1788 */         return createNormalizedStringFromString(eDataType, initialValue);
/*      */       case 47:
/* 1790 */         return createNOTATIONFromString(eDataType, initialValue);
/*      */       case 48:
/* 1792 */         return createPositiveIntegerFromString(eDataType, initialValue);
/*      */       case 49:
/* 1794 */         return createQNameFromString(eDataType, initialValue);
/*      */       case 50:
/* 1796 */         return createShortFromString(eDataType, initialValue);
/*      */       case 51:
/* 1798 */         return createShortObjectFromString(eDataType, initialValue);
/*      */       case 52:
/* 1800 */         return createStringFromString(eDataType, initialValue);
/*      */       case 53:
/* 1802 */         return createTimeFromString(eDataType, initialValue);
/*      */       case 54:
/* 1804 */         return createTokenFromString(eDataType, initialValue);
/*      */       case 55:
/* 1806 */         return createUnsignedByteFromString(eDataType, initialValue);
/*      */       case 56:
/* 1808 */         return createUnsignedByteObjectFromString(eDataType, initialValue);
/*      */       case 57:
/* 1810 */         return createUnsignedIntFromString(eDataType, initialValue);
/*      */       case 58:
/* 1812 */         return createUnsignedIntObjectFromString(eDataType, initialValue);
/*      */       case 59:
/* 1814 */         return createUnsignedLongFromString(eDataType, initialValue);
/*      */       case 60:
/* 1816 */         return createUnsignedShortFromString(eDataType, initialValue);
/*      */       case 61:
/* 1818 */         return createUnsignedShortObjectFromString(eDataType, initialValue);
/*      */     } 
/* 1820 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
/*      */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 1832 */     switch (eDataType.getClassifierID()) {
/*      */       
/*      */       case 4:
/* 1835 */         return convertAnySimpleTypeToString(eDataType, instanceValue);
/*      */       case 5:
/* 1837 */         return convertAnyURIToString(eDataType, instanceValue);
/*      */       case 6:
/* 1839 */         return convertBase64BinaryToString(eDataType, instanceValue);
/*      */       case 7:
/* 1841 */         return convertBooleanToString(eDataType, instanceValue);
/*      */       case 8:
/* 1843 */         return convertBooleanObjectToString(eDataType, instanceValue);
/*      */       case 9:
/* 1845 */         return convertByteToString(eDataType, instanceValue);
/*      */       case 10:
/* 1847 */         return convertByteObjectToString(eDataType, instanceValue);
/*      */       case 11:
/* 1849 */         return convertDateToString(eDataType, instanceValue);
/*      */       case 12:
/* 1851 */         return convertDateTimeToString(eDataType, instanceValue);
/*      */       case 13:
/* 1853 */         return convertDecimalToString(eDataType, instanceValue);
/*      */       case 14:
/* 1855 */         return convertDoubleToString(eDataType, instanceValue);
/*      */       case 15:
/* 1857 */         return convertDoubleObjectToString(eDataType, instanceValue);
/*      */       case 16:
/* 1859 */         return convertDurationToString(eDataType, instanceValue);
/*      */       case 17:
/* 1861 */         return convertENTITIESToString(eDataType, instanceValue);
/*      */       case 18:
/* 1863 */         return convertENTITIESBaseToString(eDataType, instanceValue);
/*      */       case 19:
/* 1865 */         return convertENTITYToString(eDataType, instanceValue);
/*      */       case 20:
/* 1867 */         return convertFloatToString(eDataType, instanceValue);
/*      */       case 21:
/* 1869 */         return convertFloatObjectToString(eDataType, instanceValue);
/*      */       case 22:
/* 1871 */         return convertGDayToString(eDataType, instanceValue);
/*      */       case 23:
/* 1873 */         return convertGMonthToString(eDataType, instanceValue);
/*      */       case 24:
/* 1875 */         return convertGMonthDayToString(eDataType, instanceValue);
/*      */       case 25:
/* 1877 */         return convertGYearToString(eDataType, instanceValue);
/*      */       case 26:
/* 1879 */         return convertGYearMonthToString(eDataType, instanceValue);
/*      */       case 27:
/* 1881 */         return convertHexBinaryToString(eDataType, instanceValue);
/*      */       case 28:
/* 1883 */         return convertIDToString(eDataType, instanceValue);
/*      */       case 29:
/* 1885 */         return convertIDREFToString(eDataType, instanceValue);
/*      */       case 30:
/* 1887 */         return convertIDREFSToString(eDataType, instanceValue);
/*      */       case 31:
/* 1889 */         return convertIDREFSBaseToString(eDataType, instanceValue);
/*      */       case 32:
/* 1891 */         return convertIntToString(eDataType, instanceValue);
/*      */       case 33:
/* 1893 */         return convertIntegerToString(eDataType, instanceValue);
/*      */       case 34:
/* 1895 */         return convertIntObjectToString(eDataType, instanceValue);
/*      */       case 35:
/* 1897 */         return convertLanguageToString(eDataType, instanceValue);
/*      */       case 36:
/* 1899 */         return convertLongToString(eDataType, instanceValue);
/*      */       case 37:
/* 1901 */         return convertLongObjectToString(eDataType, instanceValue);
/*      */       case 38:
/* 1903 */         return convertNameToString(eDataType, instanceValue);
/*      */       case 39:
/* 1905 */         return convertNCNameToString(eDataType, instanceValue);
/*      */       case 40:
/* 1907 */         return convertNegativeIntegerToString(eDataType, instanceValue);
/*      */       case 41:
/* 1909 */         return convertNMTOKENToString(eDataType, instanceValue);
/*      */       case 42:
/* 1911 */         return convertNMTOKENSToString(eDataType, instanceValue);
/*      */       case 43:
/* 1913 */         return convertNMTOKENSBaseToString(eDataType, instanceValue);
/*      */       case 44:
/* 1915 */         return convertNonNegativeIntegerToString(eDataType, instanceValue);
/*      */       case 45:
/* 1917 */         return convertNonPositiveIntegerToString(eDataType, instanceValue);
/*      */       case 46:
/* 1919 */         return convertNormalizedStringToString(eDataType, instanceValue);
/*      */       case 47:
/* 1921 */         return convertNOTATIONToString(eDataType, instanceValue);
/*      */       case 48:
/* 1923 */         return convertPositiveIntegerToString(eDataType, instanceValue);
/*      */       case 49:
/* 1925 */         return convertQNameToString(eDataType, instanceValue);
/*      */       case 50:
/* 1927 */         return convertShortToString(eDataType, instanceValue);
/*      */       case 51:
/* 1929 */         return convertShortObjectToString(eDataType, instanceValue);
/*      */       case 52:
/* 1931 */         return convertStringToString(eDataType, instanceValue);
/*      */       case 53:
/* 1933 */         return convertTimeToString(eDataType, instanceValue);
/*      */       case 54:
/* 1935 */         return convertTokenToString(eDataType, instanceValue);
/*      */       case 55:
/* 1937 */         return convertUnsignedByteToString(eDataType, instanceValue);
/*      */       case 56:
/* 1939 */         return convertUnsignedByteObjectToString(eDataType, instanceValue);
/*      */       case 57:
/* 1941 */         return convertUnsignedIntToString(eDataType, instanceValue);
/*      */       case 58:
/* 1943 */         return convertUnsignedIntObjectToString(eDataType, instanceValue);
/*      */       case 59:
/* 1945 */         return convertUnsignedLongToString(eDataType, instanceValue);
/*      */       case 60:
/* 1947 */         return convertUnsignedShortToString(eDataType, instanceValue);
/*      */       case 61:
/* 1949 */         return convertUnsignedShortObjectToString(eDataType, instanceValue);
/*      */     } 
/* 1951 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnyType createAnyType() {
/* 1962 */     AnyTypeImpl anyType = new AnyTypeImpl();
/* 1963 */     return anyType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProcessingInstruction createProcessingInstruction() {
/* 1973 */     ProcessingInstructionImpl processingInstruction = new ProcessingInstructionImpl();
/* 1974 */     return processingInstruction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SimpleAnyType createSimpleAnyType() {
/* 1984 */     SimpleAnyTypeImpl simpleAnyType = new SimpleAnyTypeImpl();
/* 1985 */     return simpleAnyType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLTypeDocumentRoot createXMLTypeDocumentRoot() {
/* 1995 */     XMLTypeDocumentRootImpl xmlTypeDocumentRoot = new XMLTypeDocumentRootImpl();
/* 1996 */     return xmlTypeDocumentRoot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createAnySimpleTypeFromString(EDataType eDataType, String initialValue) {
/* 2006 */     return createAnySimpleType(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertAnySimpleTypeToString(EDataType eDataType, Object instanceValue) {
/* 2016 */     return convertAnySimpleType(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createAnyURIFromString(EDataType eDataType, String initialValue) {
/* 2026 */     return createAnyURI(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertAnyURIToString(EDataType eDataType, Object instanceValue) {
/* 2036 */     return convertAnyURI((String)instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] createBase64BinaryFromString(EDataType eDataType, String initialValue) {
/* 2046 */     return createBase64Binary(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertBase64BinaryToString(EDataType eDataType, Object instanceValue) {
/* 2056 */     return convertBase64Binary((byte[])instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean createBooleanFromString(EDataType eDataType, String initialValue) {
/* 2066 */     return createBooleanObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertBooleanToString(EDataType eDataType, Object instanceValue) {
/* 2076 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean createBooleanObjectFromString(EDataType eDataType, String initialValue) {
/* 2086 */     return (initialValue == null) ? null : booleanValueOf(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertBooleanObjectToString(EDataType eDataType, Object instanceValue) {
/* 2096 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigDecimal createDecimalFromString(EDataType eDataType, String initialValue) {
/* 2106 */     return createDecimal(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDecimalToString(EDataType eDataType, Object instanceValue) {
/* 2116 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createIntegerFromString(EDataType eDataType, String initialValue) {
/* 2126 */     return createInteger(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIntegerToString(EDataType eDataType, Object instanceValue) {
/* 2136 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer createIntObjectFromString(EDataType eDataType, String initialValue) {
/* 2146 */     return createIntObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIntObjectToString(EDataType eDataType, Object instanceValue) {
/* 2156 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long createLongFromString(EDataType eDataType, String initialValue) {
/* 2166 */     return createLongObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertLongToString(EDataType eDataType, Object instanceValue) {
/* 2176 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long createLongObjectFromString(EDataType eDataType, String initialValue) {
/* 2186 */     return createLongObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertLongObjectToString(EDataType eDataType, Object instanceValue) {
/* 2196 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer createIntFromString(EDataType eDataType, String initialValue) {
/* 2206 */     return createIntObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIntToString(EDataType eDataType, Object instanceValue) {
/* 2216 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Short createShortFromString(EDataType eDataType, String initialValue) {
/* 2226 */     return createShortObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertShortToString(EDataType eDataType, Object instanceValue) {
/* 2236 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Short createShortObjectFromString(EDataType eDataType, String initialValue) {
/* 2246 */     return createShortObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertShortObjectToString(EDataType eDataType, Object instanceValue) {
/* 2256 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Byte createByteFromString(EDataType eDataType, String initialValue) {
/* 2266 */     return createByteObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertByteToString(EDataType eDataType, Object instanceValue) {
/* 2276 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Byte createByteObjectFromString(EDataType eDataType, String initialValue) {
/* 2286 */     return createByteObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertByteObjectToString(EDataType eDataType, Object instanceValue) {
/* 2296 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createDateFromString(EDataType eDataType, String initialValue) {
/* 2306 */     return createDate(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDateToString(EDataType eDataType, Object instanceValue) {
/* 2316 */     return convertDate(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createDateTimeFromString(EDataType eDataType, String initialValue) {
/* 2326 */     return createDateTime(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDateTimeToString(EDataType eDataType, Object instanceValue) {
/* 2336 */     return convertDateTime(instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createStringFromString(EDataType eDataType, String initialValue) {
/* 2346 */     return initialValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertStringToString(EDataType eDataType, Object instanceValue) {
/* 2356 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double createDoubleFromString(EDataType eDataType, String initialValue) {
/* 2366 */     return createDoubleObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDoubleToString(EDataType eDataType, Object instanceValue) {
/* 2376 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Double createDoubleObjectFromString(EDataType eDataType, String initialValue) {
/* 2386 */     return createDoubleObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDoubleObjectToString(EDataType eDataType, Object instanceValue) {
/* 2396 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createDurationFromString(EDataType eDataType, String initialValue) {
/* 2406 */     return createDuration(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertDurationToString(EDataType eDataType, Object instanceValue) {
/* 2416 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNormalizedStringFromString(EDataType eDataType, String initialValue) {
/* 2426 */     return createNormalizedString(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNormalizedStringToString(EDataType eDataType, Object instanceValue) {
/* 2436 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createTokenFromString(EDataType eDataType, String initialValue) {
/* 2446 */     return createToken(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertTokenToString(EDataType eDataType, Object instanceValue) {
/* 2456 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNameFromString(EDataType eDataType, String initialValue) {
/* 2467 */     return createName(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNameToString(EDataType eDataType, Object instanceValue) {
/* 2477 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNCNameFromString(EDataType eDataType, String initialValue) {
/* 2488 */     return createNCName(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNCNameToString(EDataType eDataType, Object instanceValue) {
/* 2498 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createENTITYFromString(EDataType eDataType, String initialValue) {
/* 2508 */     return createENTITY(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertENTITYToString(EDataType eDataType, Object instanceValue) {
/* 2518 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Float createFloatFromString(EDataType eDataType, String initialValue) {
/* 2528 */     return createFloatObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertFloatToString(EDataType eDataType, Object instanceValue) {
/* 2538 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Float createFloatObjectFromString(EDataType eDataType, String initialValue) {
/* 2548 */     return createFloatObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertFloatObjectToString(EDataType eDataType, Object instanceValue) {
/* 2558 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createGDayFromString(EDataType eDataType, String initialValue) {
/* 2568 */     return createGDay(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGDayToString(EDataType eDataType, Object instanceValue) {
/* 2578 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createGMonthFromString(EDataType eDataType, String initialValue) {
/* 2588 */     return createGMonth(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGMonthToString(EDataType eDataType, Object instanceValue) {
/* 2598 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createGMonthDayFromString(EDataType eDataType, String initialValue) {
/* 2608 */     return createGMonthDay(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGMonthDayToString(EDataType eDataType, Object instanceValue) {
/* 2618 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createGYearFromString(EDataType eDataType, String initialValue) {
/* 2628 */     return createGYear(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGYearToString(EDataType eDataType, Object instanceValue) {
/* 2638 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createGYearMonthFromString(EDataType eDataType, String initialValue) {
/* 2648 */     return createGYearMonth(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertGYearMonthToString(EDataType eDataType, Object instanceValue) {
/* 2658 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] createHexBinaryFromString(EDataType eDataType, String initialValue) {
/* 2668 */     return createHexBinary(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertHexBinaryToString(EDataType eDataType, Object instanceValue) {
/* 2678 */     return convertHexBinary((byte[])instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDToString(EDataType eDataType, Object instanceValue) {
/* 2688 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertIDREFToString(EDataType eDataType, Object instanceValue) {
/* 2698 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createLanguageFromString(EDataType eDataType, String initialValue) {
/* 2708 */     return createLanguage(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertLanguageToString(EDataType eDataType, Object instanceValue) {
/* 2718 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createNonPositiveIntegerFromString(EDataType eDataType, String initialValue) {
/* 2728 */     return createNonPositiveInteger(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNonPositiveIntegerToString(EDataType eDataType, Object instanceValue) {
/* 2738 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createNMTOKENFromString(EDataType eDataType, String initialValue) {
/* 2748 */     return createNMTOKEN(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNMTOKENToString(EDataType eDataType, Object instanceValue) {
/* 2758 */     return (String)instanceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger createNonNegativeIntegerFromString(EDataType eDataType, String initialValue) {
/* 2768 */     return createNonNegativeInteger(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNonNegativeIntegerToString(EDataType eDataType, Object instanceValue) {
/* 2778 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createNOTATIONFromString(EDataType eDataType, String initialValue) {
/* 2788 */     return createNOTATION(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertNOTATIONToString(EDataType eDataType, Object instanceValue) {
/* 2798 */     return convertNOTATION((QName)instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createQNameFromString(EDataType eDataType, String initialValue) {
/* 2808 */     return createQName(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertQNameToString(EDataType eDataType, Object instanceValue) {
/* 2818 */     return convertQName((QName)instanceValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object createTimeFromString(EDataType eDataType, String initialValue) {
/* 2828 */     return createTime(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertTimeToString(EDataType eDataType, Object instanceValue) {
/* 2838 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long createUnsignedIntFromString(EDataType eDataType, String initialValue) {
/* 2848 */     return createUnsignedIntObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedIntToString(EDataType eDataType, Object instanceValue) {
/* 2858 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long createUnsignedIntObjectFromString(EDataType eDataType, String initialValue) {
/* 2868 */     return createUnsignedIntObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedIntObjectToString(EDataType eDataType, Object instanceValue) {
/* 2878 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer createUnsignedShortFromString(EDataType eDataType, String initialValue) {
/* 2888 */     return createUnsignedShortObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedShortToString(EDataType eDataType, Object instanceValue) {
/* 2898 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer createUnsignedShortObjectFromString(EDataType eDataType, String initialValue) {
/* 2908 */     return (initialValue == null) ? null : Integer.valueOf(collapseWhiteSpace(initialValue));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedShortObjectToString(EDataType eDataType, Object instanceValue) {
/* 2918 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Short createUnsignedByteFromString(EDataType eDataType, String initialValue) {
/* 2928 */     return createUnsignedByteObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedByteToString(EDataType eDataType, Object instanceValue) {
/* 2938 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Short createUnsignedByteObjectFromString(EDataType eDataType, String initialValue) {
/* 2948 */     return createUnsignedByteObject(initialValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String convertUnsignedByteObjectToString(EDataType eDataType, Object instanceValue) {
/* 2958 */     return (instanceValue == null) ? null : instanceValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLTypePackage getXMLTypePackage() {
/* 2968 */     return (XMLTypePackage)getEPackage();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static XMLTypePackage getPackage() {
/* 2980 */     return XMLTypePackage.eINSTANCE;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Boolean booleanValueOf(String initialValue) {
/* 2985 */     initialValue = collapseWhiteSpace(initialValue);
/* 2986 */     if ("true".equals(initialValue) || "1".equals(initialValue))
/*      */     {
/* 2988 */       return Boolean.TRUE;
/*      */     }
/* 2990 */     if ("false".equals(initialValue) || "0".equals(initialValue))
/*      */     {
/* 2992 */       return Boolean.FALSE;
/*      */     }
/* 2994 */     throw new InvalidDatatypeValueException("Invalid boolean value: '" + initialValue + "'");
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean primitiveBooleanValueOf(String initialValue) {
/* 2999 */     initialValue = collapseWhiteSpace(initialValue);
/* 3000 */     if ("true".equals(initialValue) || "1".equals(initialValue))
/*      */     {
/* 3002 */       return true;
/*      */     }
/* 3004 */     if ("false".equals(initialValue) || "0".equals(initialValue))
/*      */     {
/* 3006 */       return false;
/*      */     }
/* 3008 */     throw new InvalidDatatypeValueException("Invalid boolean value: '" + initialValue + "'");
/*      */   }
/*      */   
/*      */   private static class SafeSimpleDateFormat
/*      */     extends SimpleDateFormat
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     public SafeSimpleDateFormat(String pattern) {
/* 3017 */       super(pattern, Locale.ENGLISH);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized Date parse(String source) throws ParseException {
/* 3023 */       return super.parse(source);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
/* 3029 */       StringBuffer result = super.format(date, toAppendTo, pos);
/* 3030 */       result.insert(result.length() - 2, ":");
/* 3031 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String collapseWhiteSpaceAndLeadingPlus(String value) {
/* 3039 */     String result = collapseWhiteSpace(value);
/* 3040 */     return (result.length() > 0 && result.charAt(0) == '+') ? result.substring(1) : result;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\impl\XMLTypeFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */