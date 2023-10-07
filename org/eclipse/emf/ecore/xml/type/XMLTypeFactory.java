/*    */ package org.eclipse.emf.ecore.xml.type;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.BigInteger;
/*    */ import java.util.List;
/*    */ import javax.xml.datatype.Duration;
/*    */ import javax.xml.datatype.XMLGregorianCalendar;
/*    */ import javax.xml.namespace.QName;
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import org.eclipse.emf.ecore.xml.type.impl.XMLTypeFactoryImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface XMLTypeFactory
/*    */   extends EFactory
/*    */ {
/* 46 */   public static final XMLTypeFactory eINSTANCE = XMLTypeFactoryImpl.init();
/*    */   
/*    */   AnyType createAnyType();
/*    */   
/*    */   ProcessingInstruction createProcessingInstruction();
/*    */   
/*    */   SimpleAnyType createSimpleAnyType();
/*    */   
/*    */   XMLTypeDocumentRoot createXMLTypeDocumentRoot();
/*    */   
/*    */   Object createAnySimpleType(String paramString);
/*    */   
/*    */   String convertAnySimpleType(Object paramObject);
/*    */   
/*    */   String createAnyURI(String paramString);
/*    */   
/*    */   String convertAnyURI(String paramString);
/*    */   
/*    */   byte[] createBase64Binary(String paramString);
/*    */   
/*    */   String convertBase64Binary(byte[] paramArrayOfbyte);
/*    */   
/*    */   boolean createBoolean(String paramString);
/*    */   
/*    */   String convertBoolean(boolean paramBoolean);
/*    */   
/*    */   Boolean createBooleanObject(String paramString);
/*    */   
/*    */   String convertBooleanObject(Boolean paramBoolean);
/*    */   
/*    */   byte createByte(String paramString);
/*    */   
/*    */   String convertByte(byte paramByte);
/*    */   
/*    */   Byte createByteObject(String paramString);
/*    */   
/*    */   String convertByteObject(Byte paramByte);
/*    */   
/*    */   XMLGregorianCalendar createDate(String paramString);
/*    */   
/*    */   String convertDate(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertDate(Object paramObject);
/*    */   
/*    */   XMLGregorianCalendar createDateTime(String paramString);
/*    */   
/*    */   String convertDateTime(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertDateTime(Object paramObject);
/*    */   
/*    */   BigDecimal createDecimal(String paramString);
/*    */   
/*    */   String convertDecimal(BigDecimal paramBigDecimal);
/*    */   
/*    */   double createDouble(String paramString);
/*    */   
/*    */   String convertDouble(double paramDouble);
/*    */   
/*    */   Double createDoubleObject(String paramString);
/*    */   
/*    */   String convertDoubleObject(Double paramDouble);
/*    */   
/*    */   Duration createDuration(String paramString);
/*    */   
/*    */   String convertDuration(Duration paramDuration);
/*    */   
/*    */   @Deprecated
/*    */   String convertDuration(Object paramObject);
/*    */   
/*    */   List<String> createENTITIES(String paramString);
/*    */   
/*    */   String convertENTITIES(List<? extends String> paramList);
/*    */   
/*    */   List<String> createENTITIESBase(String paramString);
/*    */   
/*    */   String convertENTITIESBase(List<? extends String> paramList);
/*    */   
/*    */   String createENTITY(String paramString);
/*    */   
/*    */   String convertENTITY(String paramString);
/*    */   
/*    */   float createFloat(String paramString);
/*    */   
/*    */   String convertFloat(float paramFloat);
/*    */   
/*    */   Float createFloatObject(String paramString);
/*    */   
/*    */   String convertFloatObject(Float paramFloat);
/*    */   
/*    */   XMLGregorianCalendar createGDay(String paramString);
/*    */   
/*    */   String convertGDay(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertGDay(Object paramObject);
/*    */   
/*    */   XMLGregorianCalendar createGMonth(String paramString);
/*    */   
/*    */   String convertGMonth(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertGMonth(Object paramObject);
/*    */   
/*    */   XMLGregorianCalendar createGMonthDay(String paramString);
/*    */   
/*    */   String convertGMonthDay(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertGMonthDay(Object paramObject);
/*    */   
/*    */   XMLGregorianCalendar createGYear(String paramString);
/*    */   
/*    */   String convertGYear(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertGYear(Object paramObject);
/*    */   
/*    */   XMLGregorianCalendar createGYearMonth(String paramString);
/*    */   
/*    */   String convertGYearMonth(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertGYearMonth(Object paramObject);
/*    */   
/*    */   byte[] createHexBinary(String paramString);
/*    */   
/*    */   String convertHexBinary(byte[] paramArrayOfbyte);
/*    */   
/*    */   String createID(String paramString);
/*    */   
/*    */   String convertID(String paramString);
/*    */   
/*    */   String createIDREF(String paramString);
/*    */   
/*    */   String convertIDREF(String paramString);
/*    */   
/*    */   List<String> createIDREFS(String paramString);
/*    */   
/*    */   String convertIDREFS(List<? extends String> paramList);
/*    */   
/*    */   List<String> createIDREFSBase(String paramString);
/*    */   
/*    */   String convertIDREFSBase(List<? extends String> paramList);
/*    */   
/*    */   int createInt(String paramString);
/*    */   
/*    */   String convertInt(int paramInt);
/*    */   
/*    */   BigInteger createInteger(String paramString);
/*    */   
/*    */   String convertInteger(BigInteger paramBigInteger);
/*    */   
/*    */   Integer createIntObject(String paramString);
/*    */   
/*    */   String convertIntObject(Integer paramInteger);
/*    */   
/*    */   String createLanguage(String paramString);
/*    */   
/*    */   String convertLanguage(String paramString);
/*    */   
/*    */   long createLong(String paramString);
/*    */   
/*    */   String convertLong(long paramLong);
/*    */   
/*    */   Long createLongObject(String paramString);
/*    */   
/*    */   String convertLongObject(Long paramLong);
/*    */   
/*    */   String createName(String paramString);
/*    */   
/*    */   String convertName(String paramString);
/*    */   
/*    */   String createNCName(String paramString);
/*    */   
/*    */   String convertNCName(String paramString);
/*    */   
/*    */   BigInteger createNegativeInteger(String paramString);
/*    */   
/*    */   String convertNegativeInteger(BigInteger paramBigInteger);
/*    */   
/*    */   String createNMTOKEN(String paramString);
/*    */   
/*    */   String convertNMTOKEN(String paramString);
/*    */   
/*    */   List<String> createNMTOKENS(String paramString);
/*    */   
/*    */   String convertNMTOKENS(List<? extends String> paramList);
/*    */   
/*    */   List<String> createNMTOKENSBase(String paramString);
/*    */   
/*    */   String convertNMTOKENSBase(List<? extends String> paramList);
/*    */   
/*    */   BigInteger createNonNegativeInteger(String paramString);
/*    */   
/*    */   String convertNonNegativeInteger(BigInteger paramBigInteger);
/*    */   
/*    */   BigInteger createNonPositiveInteger(String paramString);
/*    */   
/*    */   String convertNonPositiveInteger(BigInteger paramBigInteger);
/*    */   
/*    */   String createNormalizedString(String paramString);
/*    */   
/*    */   String convertNormalizedString(String paramString);
/*    */   
/*    */   QName createNOTATION(String paramString);
/*    */   
/*    */   String convertNOTATION(QName paramQName);
/*    */   
/*    */   @Deprecated
/*    */   String convertNOTATION(Object paramObject);
/*    */   
/*    */   BigInteger createPositiveInteger(String paramString);
/*    */   
/*    */   String convertPositiveInteger(BigInteger paramBigInteger);
/*    */   
/*    */   QName createQName(String paramString);
/*    */   
/*    */   QName createQName(String paramString1, String paramString2);
/*    */   
/*    */   QName createQName(String paramString1, String paramString2, String paramString3);
/*    */   
/*    */   String convertQName(QName paramQName);
/*    */   
/*    */   @Deprecated
/*    */   String convertQName(Object paramObject);
/*    */   
/*    */   short createShort(String paramString);
/*    */   
/*    */   String convertShort(short paramShort);
/*    */   
/*    */   Short createShortObject(String paramString);
/*    */   
/*    */   String convertShortObject(Short paramShort);
/*    */   
/*    */   String createString(String paramString);
/*    */   
/*    */   String convertString(String paramString);
/*    */   
/*    */   XMLGregorianCalendar createTime(String paramString);
/*    */   
/*    */   String convertTime(XMLGregorianCalendar paramXMLGregorianCalendar);
/*    */   
/*    */   @Deprecated
/*    */   String convertTime(Object paramObject);
/*    */   
/*    */   String createToken(String paramString);
/*    */   
/*    */   String convertToken(String paramString);
/*    */   
/*    */   short createUnsignedByte(String paramString);
/*    */   
/*    */   String convertUnsignedByte(short paramShort);
/*    */   
/*    */   Short createUnsignedByteObject(String paramString);
/*    */   
/*    */   String convertUnsignedByteObject(Short paramShort);
/*    */   
/*    */   long createUnsignedInt(String paramString);
/*    */   
/*    */   String convertUnsignedInt(long paramLong);
/*    */   
/*    */   Long createUnsignedIntObject(String paramString);
/*    */   
/*    */   String convertUnsignedIntObject(Long paramLong);
/*    */   
/*    */   BigInteger createUnsignedLong(String paramString);
/*    */   
/*    */   String convertUnsignedLong(BigInteger paramBigInteger);
/*    */   
/*    */   int createUnsignedShort(String paramString);
/*    */   
/*    */   String convertUnsignedShort(int paramInt);
/*    */   
/*    */   Integer createUnsignedShortObject(String paramString);
/*    */   
/*    */   String convertUnsignedShortObject(Integer paramInteger);
/*    */   
/*    */   XMLTypePackage getXMLTypePackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\XMLTypeFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */