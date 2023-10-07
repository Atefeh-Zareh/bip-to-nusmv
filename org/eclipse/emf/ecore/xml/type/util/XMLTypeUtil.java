/*     */ package org.eclipse.emf.ecore.xml.type.util;
/*     */ 
/*     */ import javax.xml.datatype.Duration;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ import javax.xml.namespace.QName;
/*     */ import org.eclipse.emf.ecore.EValidator;
/*     */ import org.eclipse.emf.ecore.xml.type.internal.DataValue;
/*     */ import org.eclipse.emf.ecore.xml.type.internal.QName;
/*     */ import org.eclipse.emf.ecore.xml.type.internal.RegEx;
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
/*     */ public final class XMLTypeUtil
/*     */ {
/*     */   public static final int EQUALS = 0;
/*     */   public static final int LESS_THAN = -1;
/*     */   public static final int GREATER_THAN = 1;
/*     */   public static final int INDETERMINATE = 2;
/*     */   
/*     */   public static int compareCalendar(Object calendar1, Object calendar2) {
/*  43 */     switch (((XMLGregorianCalendar)calendar1).compare((XMLGregorianCalendar)calendar2)) {
/*     */ 
/*     */       
/*     */       case 0:
/*  47 */         return 0;
/*     */ 
/*     */       
/*     */       case -1:
/*  51 */         return -1;
/*     */ 
/*     */       
/*     */       case 1:
/*  55 */         return 1;
/*     */     } 
/*     */ 
/*     */     
/*  59 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int compareDuration(Object duration1, Object duration2) {
/*  66 */     switch (((Duration)duration1).compare((Duration)duration2)) {
/*     */ 
/*     */       
/*     */       case 0:
/*  70 */         return 0;
/*     */ 
/*     */       
/*     */       case -1:
/*  74 */         return -1;
/*     */ 
/*     */       
/*     */       case 1:
/*  78 */         return 1;
/*     */     } 
/*     */ 
/*     */     
/*  82 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSpace(char value) {
/*  89 */     return DataValue.XMLChar.isSpace(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class CharArrayThreadLocal
/*     */     extends ThreadLocal<char[]>
/*     */   {
/*     */     private static final int MAX_CACHE_CAPACITY;
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 102 */       int result = 10000;
/*     */       
/*     */       try {
/* 105 */         String property = System.getProperty("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil.CharArrayThreadLocal.MAX_CACHE_CAPACITY");
/* 106 */         if (property != null)
/*     */         {
/* 108 */           result = Integer.valueOf(property).intValue();
/*     */         }
/*     */       }
/* 111 */       catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */       
/* 115 */       MAX_CACHE_CAPACITY = result;
/*     */     }
/*     */     
/* 118 */     private long cachedThread = -1L;
/*     */     
/*     */     private char[] cachedResult;
/*     */     
/*     */     public final char[] get(int capacity) {
/* 123 */       if (capacity > MAX_CACHE_CAPACITY)
/*     */       {
/* 125 */         return new char[capacity];
/*     */       }
/* 127 */       long currentThread = Thread.currentThread().getId();
/* 128 */       char[] result = this.cachedResult;
/* 129 */       if (this.cachedThread != currentThread) {
/*     */         
/* 131 */         this.cachedThread = currentThread;
/* 132 */         result = get();
/*     */       } 
/* 134 */       if (result.length < capacity) {
/*     */         
/* 136 */         result = new char[capacity];
/* 137 */         set(result);
/*     */       } 
/* 139 */       return this.cachedResult = result;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected char[] initialValue() {
/* 145 */       return new char[20];
/*     */     }
/*     */     
/*     */     private CharArrayThreadLocal() {} }
/* 149 */   private static final CharArrayThreadLocal VALUE = new CharArrayThreadLocal(null);
/*     */ 
/*     */   
/*     */   public static String normalize(String value, boolean collapse) {
/* 153 */     if (value == null)
/*     */     {
/* 155 */       return null;
/*     */     }
/*     */     
/* 158 */     int length = value.length();
/* 159 */     if (length == 0)
/*     */     {
/* 161 */       return "";
/*     */     }
/*     */     
/* 164 */     char[] valueArray = VALUE.get(length);
/* 165 */     value.getChars(0, length, valueArray, 0);
/* 166 */     StringBuffer buffer = null;
/* 167 */     boolean skipSpace = collapse;
/* 168 */     for (int i = 0, offset = 0; i < length; i++) {
/*     */       
/* 170 */       char c = valueArray[i];
/* 171 */       if (isSpace(c)) {
/*     */         
/* 173 */         if (skipSpace) {
/*     */           
/* 175 */           if (buffer == null)
/*     */           {
/* 177 */             buffer = new StringBuffer(value);
/*     */           }
/* 179 */           buffer.deleteCharAt(i - offset++);
/*     */         }
/*     */         else {
/*     */           
/* 183 */           skipSpace = collapse;
/* 184 */           if (c != ' ')
/*     */           {
/* 186 */             if (buffer == null)
/*     */             {
/* 188 */               buffer = new StringBuffer(value);
/*     */             }
/* 190 */             buffer.setCharAt(i - offset, ' ');
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 196 */         skipSpace = false;
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     if (skipSpace) {
/*     */       
/* 202 */       if (buffer == null)
/*     */       {
/* 204 */         return value.substring(0, length - 1);
/*     */       }
/*     */ 
/*     */       
/* 208 */       length = buffer.length();
/* 209 */       if (length > 0)
/*     */       {
/* 211 */         return buffer.substring(0, length - 1);
/*     */       }
/*     */ 
/*     */       
/* 215 */       return "";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     if (buffer == null)
/*     */     {
/* 223 */       return value;
/*     */     }
/*     */ 
/*     */     
/* 227 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EValidator.PatternMatcher createPatternMatcher(String pattern) {
/* 234 */     return new PatternMatcherImpl(pattern);
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
/*     */   public static Object createQName(String namespaceUri, String localPart, String prefix) {
/* 247 */     return new QName(namespaceUri, localPart, prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void setQNameValues(Object qName, String namespaceUri, String localPart, String prefix) {
/* 259 */     if (!(qName instanceof QName))
/*     */     {
/* 261 */       throw new UnsupportedOperationException("QNames are immutable, so this can't be supported");
/*     */     }
/* 263 */     if (namespaceUri == null)
/*     */     {
/* 265 */       namespaceUri = "";
/*     */     }
/* 267 */     QName qn = (QName)qName;
/* 268 */     if (!qn.getLocalPart().equals(localPart) || qn.getNamespaceURI().equals(namespaceUri))
/*     */     {
/* 270 */       throw new UnsupportedOperationException("QNames are immutable, so this can't be supported");
/*     */     }
/* 272 */     qn.setPrefix(prefix);
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
/*     */   public static QName setPrefix(QName qName, String prefix) {
/* 284 */     if (qName instanceof QName) {
/*     */       
/* 286 */       QName result = (QName)qName;
/* 287 */       result.setPrefix(prefix);
/* 288 */       return (QName)result;
/*     */     } 
/*     */ 
/*     */     
/* 292 */     return (QName)new QName(qName.getNamespaceURI(), qName.getLocalPart(), prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getQNameNamespaceURI(Object qName) {
/* 301 */     return ((QName)qName).getNamespaceURI();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getQNameLocalPart(Object qName) {
/* 308 */     return ((QName)qName).getLocalPart();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getQNamePrefix(Object qName) {
/* 316 */     return ((QName)qName).getPrefix();
/*     */   }
/*     */   
/*     */   private static class PatternMatcherImpl
/*     */     implements EValidator.PatternMatcher
/*     */   {
/*     */     protected RegEx.RegularExpression regularExpression;
/*     */     
/*     */     public PatternMatcherImpl(String pattern) {
/* 325 */       this.regularExpression = new RegEx.RegularExpression(pattern, "X");
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean matches(String value) {
/* 330 */       return this.regularExpression.matches(value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 336 */       return this.regularExpression.getPattern();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNameStart(int codePoint) {
/* 345 */     return DataValue.XMLChar.isNameStart(codePoint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNamePart(int codePoint) {
/* 353 */     return DataValue.XMLChar.isName(codePoint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNCNameStart(int codePoint) {
/* 361 */     return DataValue.XMLChar.isNCNameStart(codePoint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNCNamePart(int codePoint) {
/* 369 */     return DataValue.XMLChar.isNCName(codePoint);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\typ\\util\XMLTypeUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */