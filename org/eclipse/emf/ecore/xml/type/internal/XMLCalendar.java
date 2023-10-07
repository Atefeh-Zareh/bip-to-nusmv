/*     */ package org.eclipse.emf.ecore.xml.type.internal;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import javax.xml.datatype.DatatypeConfigurationException;
/*     */ import javax.xml.datatype.DatatypeFactory;
/*     */ import javax.xml.datatype.Duration;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ import javax.xml.namespace.QName;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
/*     */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
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
/*     */ public final class XMLCalendar
/*     */   extends XMLGregorianCalendar
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final short DATETIME = 0;
/*     */   public static final short TIME = 1;
/*     */   public static final short DATE = 2;
/*     */   public static final short GYEARMONTH = 3;
/*     */   public static final short GYEAR = 4;
/*     */   public static final short GMONTHDAY = 5;
/*     */   public static final short GDAY = 6;
/*     */   public static final short GMONTH = 7;
/* 124 */   protected static final String[] XML_SCHEMA_TYPES = new String[] {
/* 125 */       "dateTime", 
/* 126 */       "time", 
/* 127 */       "date", 
/* 128 */       "gYearMonth", 
/* 129 */       "gYear", 
/* 130 */       "gMonthDay", 
/* 131 */       "gDay", 
/* 132 */       "gMonth"
/*     */     };
/*     */   
/*     */   public static final int EQUALS = 0;
/*     */   
/*     */   public static final int LESS_THAN = -1;
/*     */   
/*     */   public static final int GREATER_THAN = 1;
/*     */   
/*     */   public static final int INDETERMINATE = 2;
/*     */   short dataType;
/*     */   private XMLGregorianCalendar xmlGregorianCalendar;
/*     */   private Date date;
/*     */   static final DatatypeFactory datatypeFactory;
/*     */   
/*     */   static {
/*     */     try {
/* 149 */       datatypeFactory = DatatypeFactory.newInstance();
/*     */     }
/* 151 */     catch (DatatypeConfigurationException exception) {
/*     */       
/* 153 */       throw new RuntimeException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 158 */   protected static final DateFormat[] EDATE_FORMATS = new DateFormat[] {
/* 159 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'S'Z'"), 
/* 160 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"), 
/* 161 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'S"), 
/* 162 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), 
/* 163 */       new SafeSimpleDateFormat("yyyy-MM-dd'Z'"), 
/* 164 */       new SafeSimpleDateFormat("yyyy-MM-dd") };
/*     */   private static final boolean FIX_GMONTH_PARSE;
/*     */   private static final boolean FIX_GMONTH_PRINT;
/*     */   
/*     */   static {
/* 169 */     EDATE_FORMATS[0].setTimeZone(TimeZone.getTimeZone("GMT"));
/* 170 */     EDATE_FORMATS[1].setTimeZone(TimeZone.getTimeZone("GMT"));
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
/* 181 */     XMLGregorianCalendar test = null;
/*     */     
/*     */     try {
/* 184 */       test = datatypeFactory.newXMLGregorianCalendar("--12");
/*     */     }
/* 186 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 190 */     FIX_GMONTH_PARSE = (test == null);
/* 191 */     FIX_GMONTH_PRINT = !(test != null && test.toString().length() <= 4);
/*     */   }
/*     */ 
/*     */   
/*     */   private XMLCalendar(XMLGregorianCalendar xmlGregorianCalendar, Date date, short dataType) {
/* 196 */     this.xmlGregorianCalendar = xmlGregorianCalendar;
/* 197 */     this.date = date;
/* 198 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLCalendar(String value, short datatype) {
/* 203 */     value = XMLTypeUtil.normalize(value, true);
/* 204 */     if (value.length() == 0)
/*     */     {
/* 206 */       throw new InvalidDatatypeValueException("Incomplete value");
/*     */     }
/* 208 */     if (datatype < 0 || datatype > 7)
/*     */     {
/* 210 */       throw new IllegalArgumentException("Illegal datatype value " + datatype);
/*     */     }
/*     */     
/* 213 */     if (datatype == 7 && FIX_GMONTH_PARSE)
/*     */     {
/* 215 */       if (value.length() < 6 || value.charAt(4) != '-' || value.charAt(5) != '-') {
/*     */         
/* 217 */         StringBuilder v = new StringBuilder(value);
/* 218 */         v.insert(4, "--");
/* 219 */         value = v.toString();
/*     */       } 
/*     */     }
/*     */     
/* 223 */     this.date = null;
/* 224 */     this.dataType = datatype;
/* 225 */     this.xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLCalendar(Date date, short dataType) {
/* 230 */     this.xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(EDATE_FORMATS[0].format(date));
/* 231 */     this.dataType = dataType;
/* 232 */     this.date = date;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int compare(XMLCalendar value1, XMLCalendar value2) {
/* 237 */     switch (value1.xmlGregorianCalendar.compare(value2.xmlGregorianCalendar)) {
/*     */ 
/*     */       
/*     */       case 0:
/* 241 */         return 0;
/*     */ 
/*     */       
/*     */       case -1:
/* 245 */         return -1;
/*     */ 
/*     */       
/*     */       case 1:
/* 249 */         return 1;
/*     */     } 
/*     */ 
/*     */     
/* 253 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 261 */     return 
/* 262 */       (object instanceof XMLCalendar) ? 
/* 263 */       this.xmlGregorianCalendar.equals(((XMLCalendar)object).xmlGregorianCalendar) : (
/* 264 */       (object instanceof XMLGregorianCalendar && this.xmlGregorianCalendar.equals(object)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 270 */     return this.xmlGregorianCalendar.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 276 */     return toXMLFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDate() {
/* 281 */     if (this.date == null) {
/*     */       
/*     */       try {
/*     */         
/* 285 */         if (this.dataType == 0) {
/*     */           
/* 287 */           String xmlFormat = toXMLFormat();
/*     */           
/*     */           try {
/* 290 */             this.date = EDATE_FORMATS[0].parse(xmlFormat);
/*     */           }
/* 292 */           catch (Exception e) {
/*     */ 
/*     */             
/*     */             try {
/* 296 */               this.date = EDATE_FORMATS[1].parse(xmlFormat);
/*     */             }
/* 298 */             catch (Exception e2) {
/*     */               
/*     */               try
/*     */               {
/* 302 */                 this.date = EDATE_FORMATS[2].parse(xmlFormat);
/*     */               }
/* 304 */               catch (Exception e3)
/*     */               {
/* 306 */                 this.date = EDATE_FORMATS[3].parse(xmlFormat);
/*     */               }
/*     */             
/*     */             } 
/*     */           } 
/* 311 */         } else if (this.dataType == 2) {
/*     */           
/* 313 */           String xmlFormat = toXMLFormat();
/*     */           
/*     */           try {
/* 316 */             this.date = EDATE_FORMATS[4].parse(xmlFormat);
/*     */           }
/* 318 */           catch (Exception e) {
/*     */             
/* 320 */             this.date = EDATE_FORMATS[5].parse(xmlFormat);
/*     */           }
/*     */         
/*     */         } 
/* 324 */       } catch (Exception e) {
/*     */         
/* 326 */         throw new WrappedException(e);
/*     */       } 
/*     */     }
/* 329 */     return this.date;
/*     */   }
/*     */   
/*     */   private static class SafeSimpleDateFormat
/*     */     extends SimpleDateFormat
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public SafeSimpleDateFormat(String pattern) {
/* 338 */       super(pattern, Locale.ENGLISH);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized Date parse(String source) throws ParseException {
/* 344 */       return super.parse(source);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Duration duration) {
/* 351 */     this.xmlGregorianCalendar.add(duration);
/* 352 */     this.date = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 358 */     this.xmlGregorianCalendar.clear();
/* 359 */     this.date = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 365 */     return new XMLCalendar(this.xmlGregorianCalendar, this.date, this.dataType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compare(XMLGregorianCalendar xmlGregorianCalendar) {
/* 371 */     return 
/* 372 */       this.xmlGregorianCalendar.compare(
/* 373 */         (xmlGregorianCalendar instanceof XMLCalendar) ? ((XMLCalendar)xmlGregorianCalendar).xmlGregorianCalendar : xmlGregorianCalendar);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDay() {
/* 379 */     return this.xmlGregorianCalendar.getDay();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigInteger getEon() {
/* 385 */     return this.xmlGregorianCalendar.getEon();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigInteger getEonAndYear() {
/* 391 */     return this.xmlGregorianCalendar.getEonAndYear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getFractionalSecond() {
/* 397 */     return this.xmlGregorianCalendar.getFractionalSecond();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHour() {
/* 403 */     return this.xmlGregorianCalendar.getHour();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinute() {
/* 409 */     return this.xmlGregorianCalendar.getMinute();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMonth() {
/* 415 */     return this.xmlGregorianCalendar.getMonth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSecond() {
/* 421 */     return this.xmlGregorianCalendar.getSecond();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeZone getTimeZone(int defaultTimeZone) {
/* 427 */     return this.xmlGregorianCalendar.getTimeZone(defaultTimeZone);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimezone() {
/* 433 */     return this.xmlGregorianCalendar.getTimezone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getXMLSchemaType() {
/* 439 */     return this.xmlGregorianCalendar.getXMLSchemaType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYear() {
/* 445 */     return this.xmlGregorianCalendar.getYear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/* 451 */     return this.xmlGregorianCalendar.isValid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLGregorianCalendar normalize() {
/* 457 */     return this.xmlGregorianCalendar.normalize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 463 */     this.date = null;
/* 464 */     this.xmlGregorianCalendar.reset();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDay(int day) {
/* 470 */     this.xmlGregorianCalendar.setDay(day);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFractionalSecond(BigDecimal fractionalSecond) {
/* 476 */     this.xmlGregorianCalendar.setFractionalSecond(fractionalSecond);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHour(int hour) {
/* 482 */     this.xmlGregorianCalendar.setHour(hour);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMillisecond(int millisecond) {
/* 488 */     this.xmlGregorianCalendar.setMillisecond(millisecond);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinute(int minute) {
/* 494 */     this.xmlGregorianCalendar.setMinute(minute);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMonth(int month) {
/* 500 */     this.xmlGregorianCalendar.setMonth(month);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecond(int second) {
/* 506 */     this.xmlGregorianCalendar.setSecond(second);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimezone(int offset) {
/* 512 */     this.xmlGregorianCalendar.setTimezone(offset);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYear(BigInteger year) {
/* 518 */     this.xmlGregorianCalendar.setYear(year);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYear(int year) {
/* 524 */     this.xmlGregorianCalendar.setYear(year);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GregorianCalendar toGregorianCalendar() {
/* 530 */     return this.xmlGregorianCalendar.toGregorianCalendar();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GregorianCalendar toGregorianCalendar(TimeZone timeZone, Locale locale, XMLGregorianCalendar defaults) {
/* 536 */     return this.xmlGregorianCalendar.toGregorianCalendar(timeZone, locale, defaults);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXMLFormat() {
/* 542 */     if (this.dataType == 7 && FIX_GMONTH_PRINT) {
/*     */       
/* 544 */       String value = this.xmlGregorianCalendar.toXMLFormat();
/* 545 */       if (value.length() > 5 && value.charAt(4) == '-' && value.charAt(5) == '-') {
/*     */         
/* 547 */         StringBuilder v = new StringBuilder(value);
/* 548 */         v.delete(4, 6);
/* 549 */         value = v.toString();
/*     */       } 
/* 551 */       return value;
/*     */     } 
/* 553 */     return this.xmlGregorianCalendar.toXMLFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 558 */     out.writeShort(this.dataType);
/* 559 */     out.writeUTF(toString());
/* 560 */     out.writeObject(this.date);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 565 */     this.dataType = in.readShort();
/* 566 */     this.xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(in.readUTF());
/* 567 */     this.date = (Date)in.readObject();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\internal\XMLCalendar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */