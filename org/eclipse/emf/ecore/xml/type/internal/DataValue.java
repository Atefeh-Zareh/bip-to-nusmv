/*      */ package org.eclipse.emf.ecore.xml.type.internal;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.Arrays;
/*      */ import java.util.Hashtable;
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
/*      */ public final class DataValue
/*      */ {
/*      */   static class ValidationContext {}
/*      */   
/*      */   static class XSSimpleType {}
/*      */   
/*      */   public static final class Base64
/*      */   {
/*      */     private static final int BASELENGTH = 255;
/*      */     private static final int LOOKUPLENGTH = 64;
/*      */     private static final int TWENTYFOURBITGROUP = 24;
/*      */     private static final int EIGHTBIT = 8;
/*      */     private static final int SIXTEENBIT = 16;
/*      */     private static final int FOURBYTE = 4;
/*      */     private static final int SIGN = -128;
/*      */     private static final char PAD = '=';
/*      */     private static final boolean fDebug = false;
/*  124 */     private static final byte[] base64Alphabet = new byte[255];
/*  125 */     private static final char[] lookUpBase64Alphabet = new char[64];
/*      */     
/*      */     static {
/*      */       int i;
/*  129 */       for (i = 0; i < 255; i++) {
/*  130 */         base64Alphabet[i] = -1;
/*      */       }
/*  132 */       for (i = 90; i >= 65; i--) {
/*  133 */         base64Alphabet[i] = (byte)(i - 65);
/*      */       }
/*  135 */       for (i = 122; i >= 97; i--) {
/*  136 */         base64Alphabet[i] = (byte)(i - 97 + 26);
/*      */       }
/*      */       
/*  139 */       for (i = 57; i >= 48; i--) {
/*  140 */         base64Alphabet[i] = (byte)(i - 48 + 52);
/*      */       }
/*      */       
/*  143 */       base64Alphabet[43] = 62;
/*  144 */       base64Alphabet[47] = 63;
/*      */       
/*  146 */       for (i = 0; i <= 25; i++)
/*  147 */         lookUpBase64Alphabet[i] = (char)(65 + i); 
/*      */       int j;
/*  149 */       for (i = 26, j = 0; i <= 51; i++, j++) {
/*  150 */         lookUpBase64Alphabet[i] = (char)(97 + j);
/*      */       }
/*  152 */       for (i = 52, j = 0; i <= 61; i++, j++)
/*  153 */         lookUpBase64Alphabet[i] = (char)(48 + j); 
/*  154 */       lookUpBase64Alphabet[62] = '+';
/*  155 */       lookUpBase64Alphabet[63] = '/';
/*      */     }
/*      */ 
/*      */     
/*      */     protected static boolean isWhiteSpace(char octect) {
/*  160 */       return !(octect != ' ' && octect != '\r' && octect != '\n' && octect != '\t');
/*      */     }
/*      */     
/*      */     protected static boolean isPad(char octect) {
/*  164 */       return (octect == '=');
/*      */     }
/*      */     
/*      */     protected static boolean isData(char octect) {
/*  168 */       return (base64Alphabet[octect] != -1);
/*      */     }
/*      */     
/*      */     protected static boolean isBase64(char octect) {
/*  172 */       return !(!isWhiteSpace(octect) && !isPad(octect) && !isData(octect));
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
/*      */     public static String encode(byte[] binaryData) {
/*  185 */       if (binaryData == null) {
/*  186 */         return null;
/*      */       }
/*  188 */       int lengthDataBits = binaryData.length * 8;
/*  189 */       if (lengthDataBits == 0) {
/*  190 */         return "";
/*      */       }
/*      */       
/*  193 */       int fewerThan24bits = lengthDataBits % 24;
/*  194 */       int numberTriplets = lengthDataBits / 24;
/*  195 */       int numberQuartet = (fewerThan24bits != 0) ? (numberTriplets + 1) : numberTriplets;
/*  196 */       char[] encodedData = (char[])null;
/*      */       
/*  198 */       encodedData = new char[numberQuartet * 4];
/*      */       
/*  200 */       byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;
/*      */       
/*  202 */       int encodedIndex = 0;
/*  203 */       int dataIndex = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  208 */       for (int i = 0; i < numberTriplets; i++) {
/*  209 */         b1 = binaryData[dataIndex++];
/*  210 */         b2 = binaryData[dataIndex++];
/*  211 */         b3 = binaryData[dataIndex++];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  217 */         l = (byte)(b2 & 0xF);
/*  218 */         k = (byte)(b1 & 0x3);
/*      */         
/*  220 */         byte val1 = ((b1 & Byte.MIN_VALUE) == 0) ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/*      */         
/*  222 */         byte val2 = ((b2 & Byte.MIN_VALUE) == 0) ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/*  223 */         byte val3 = ((b3 & Byte.MIN_VALUE) == 0) ? (byte)(b3 >> 6) : (byte)(b3 >> 6 ^ 0xFC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  231 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
/*  232 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | k << 4];
/*  233 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2 | val3];
/*  234 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[b3 & 0x3F];
/*      */       } 
/*      */ 
/*      */       
/*  238 */       if (fewerThan24bits == 8) {
/*  239 */         b1 = binaryData[dataIndex];
/*  240 */         k = (byte)(b1 & 0x3);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  245 */         byte val1 = ((b1 & Byte.MIN_VALUE) == 0) ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/*  246 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
/*  247 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[k << 4];
/*  248 */         encodedData[encodedIndex++] = '=';
/*  249 */         encodedData[encodedIndex++] = '=';
/*  250 */       } else if (fewerThan24bits == 16) {
/*  251 */         b1 = binaryData[dataIndex];
/*  252 */         b2 = binaryData[dataIndex + 1];
/*  253 */         l = (byte)(b2 & 0xF);
/*  254 */         k = (byte)(b1 & 0x3);
/*      */         
/*  256 */         byte val1 = ((b1 & Byte.MIN_VALUE) == 0) ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/*  257 */         byte val2 = ((b2 & Byte.MIN_VALUE) == 0) ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/*      */         
/*  259 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
/*  260 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | k << 4];
/*  261 */         encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2];
/*  262 */         encodedData[encodedIndex++] = '=';
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  267 */       return new String(encodedData);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static byte[] decode(String encoded) {
/*  278 */       if (encoded == null) {
/*  279 */         return null;
/*      */       }
/*  281 */       char[] base64Data = encoded.toCharArray();
/*      */       
/*  283 */       int len = removeWhiteSpace(base64Data);
/*      */       
/*  285 */       if (len % 4 != 0) {
/*  286 */         return null;
/*      */       }
/*      */       
/*  289 */       int numberQuadruple = len / 4;
/*      */       
/*  291 */       if (numberQuadruple == 0) {
/*  292 */         return new byte[0];
/*      */       }
/*  294 */       byte[] decodedData = (byte[])null;
/*  295 */       byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;
/*  296 */       char d1 = Character.MIN_VALUE, d2 = Character.MIN_VALUE, d3 = Character.MIN_VALUE, d4 = Character.MIN_VALUE;
/*      */       
/*  298 */       int i = 0;
/*  299 */       int encodedIndex = 0;
/*  300 */       int dataIndex = 0;
/*  301 */       decodedData = new byte[numberQuadruple * 3];
/*      */       
/*  303 */       for (; i < numberQuadruple - 1; i++) {
/*      */         
/*  305 */         if (!isData(d1 = base64Data[dataIndex++]) || 
/*  306 */           !isData(d2 = base64Data[dataIndex++]) || 
/*  307 */           !isData(d3 = base64Data[dataIndex++]) || 
/*  308 */           !isData(d4 = base64Data[dataIndex++])) {
/*  309 */           return null;
/*      */         }
/*  311 */         b1 = base64Alphabet[d1];
/*  312 */         b2 = base64Alphabet[d2];
/*  313 */         b3 = base64Alphabet[d3];
/*  314 */         b4 = base64Alphabet[d4];
/*      */         
/*  316 */         decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
/*  317 */         decodedData[encodedIndex++] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
/*  318 */         decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
/*      */       } 
/*      */       
/*  321 */       if (!isData(d1 = base64Data[dataIndex++]) || 
/*  322 */         !isData(d2 = base64Data[dataIndex++])) {
/*  323 */         return null;
/*      */       }
/*      */       
/*  326 */       b1 = base64Alphabet[d1];
/*  327 */       b2 = base64Alphabet[d2];
/*      */       
/*  329 */       d3 = base64Data[dataIndex++];
/*  330 */       d4 = base64Data[dataIndex++];
/*  331 */       if (!isData(d3) || 
/*  332 */         !isData(d4)) {
/*  333 */         if (isPad(d3) && isPad(d4)) {
/*  334 */           if ((b2 & 0xF) != 0)
/*  335 */             return null; 
/*  336 */           byte[] tmp = new byte[i * 3 + 1];
/*  337 */           System.arraycopy(decodedData, 0, tmp, 0, i * 3);
/*  338 */           tmp[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
/*  339 */           return tmp;
/*  340 */         }  if (!isPad(d3) && isPad(d4)) {
/*  341 */           b3 = base64Alphabet[d3];
/*  342 */           if ((b3 & 0x3) != 0)
/*  343 */             return null; 
/*  344 */           byte[] tmp = new byte[i * 3 + 2];
/*  345 */           System.arraycopy(decodedData, 0, tmp, 0, i * 3);
/*  346 */           tmp[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
/*  347 */           tmp[encodedIndex] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
/*  348 */           return tmp;
/*      */         } 
/*  350 */         return null;
/*      */       } 
/*      */       
/*  353 */       b3 = base64Alphabet[d3];
/*  354 */       b4 = base64Alphabet[d4];
/*  355 */       decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
/*  356 */       decodedData[encodedIndex++] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
/*  357 */       decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
/*      */ 
/*      */ 
/*      */       
/*  361 */       return decodedData;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected static int removeWhiteSpace(char[] data) {
/*  371 */       if (data == null) {
/*  372 */         return 0;
/*      */       }
/*      */       
/*  375 */       int newSize = 0;
/*  376 */       int len = data.length;
/*  377 */       for (int i = 0; i < len; i++) {
/*  378 */         if (!isWhiteSpace(data[i]))
/*  379 */           data[newSize++] = data[i]; 
/*      */       } 
/*  381 */       return newSize;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class HexBin
/*      */   {
/*      */     private static final int BASELENGTH = 255;
/*      */ 
/*      */     
/*      */     private static final int LOOKUPLENGTH = 16;
/*      */ 
/*      */     
/*  395 */     private static final byte[] hexNumberTable = new byte[255];
/*  396 */     private static final char[] lookUpHexAlphabet = new char[16];
/*      */     
/*      */     static {
/*      */       int i;
/*  400 */       for (i = 0; i < 255; i++) {
/*  401 */         hexNumberTable[i] = -1;
/*      */       }
/*  403 */       for (i = 57; i >= 48; i--) {
/*  404 */         hexNumberTable[i] = (byte)(i - 48);
/*      */       }
/*  406 */       for (i = 70; i >= 65; i--) {
/*  407 */         hexNumberTable[i] = (byte)(i - 65 + 10);
/*      */       }
/*  409 */       for (i = 102; i >= 97; i--) {
/*  410 */         hexNumberTable[i] = (byte)(i - 97 + 10);
/*      */       }
/*      */       
/*  413 */       for (i = 0; i < 10; i++)
/*  414 */         lookUpHexAlphabet[i] = (char)(48 + i); 
/*  415 */       for (i = 10; i <= 15; i++) {
/*  416 */         lookUpHexAlphabet[i] = (char)(65 + i - 10);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String encode(byte[] binaryData) {
/*  426 */       if (binaryData == null)
/*  427 */         return null; 
/*  428 */       int lengthData = binaryData.length;
/*  429 */       int lengthEncode = lengthData * 2;
/*  430 */       char[] encodedData = new char[lengthEncode];
/*      */       
/*  432 */       for (int i = 0; i < lengthData; i++) {
/*  433 */         int temp = binaryData[i];
/*  434 */         if (temp < 0)
/*  435 */           temp += 256; 
/*  436 */         encodedData[i * 2] = lookUpHexAlphabet[temp >> 4];
/*  437 */         encodedData[i * 2 + 1] = lookUpHexAlphabet[temp & 0xF];
/*      */       } 
/*  439 */       return new String(encodedData);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static byte[] decode(String encoded) {
/*  449 */       if (encoded == null)
/*  450 */         return null; 
/*  451 */       int lengthData = encoded.length();
/*  452 */       if (lengthData % 2 != 0) {
/*  453 */         return null;
/*      */       }
/*  455 */       char[] binaryData = encoded.toCharArray();
/*  456 */       int lengthDecode = lengthData / 2;
/*  457 */       byte[] decodedData = new byte[lengthDecode];
/*      */       
/*  459 */       for (int i = 0; i < lengthDecode; i++) {
/*  460 */         byte temp1 = hexNumberTable[binaryData[i * 2]];
/*  461 */         if (temp1 == -1)
/*  462 */           return null; 
/*  463 */         byte temp2 = hexNumberTable[binaryData[i * 2 + 1]];
/*  464 */         if (temp2 == -1)
/*  465 */           return null; 
/*  466 */         decodedData[i] = (byte)(temp1 << 4 | temp2);
/*      */       } 
/*  468 */       return decodedData;
/*      */     }
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
/*      */   public static class EncodingMap
/*      */   {
/*  926 */     protected static final Hashtable<String, String> fIANA2JavaMap = new Hashtable<String, String>();
/*      */ 
/*      */     
/*  929 */     protected static final Hashtable<String, String> fJava2IANAMap = new Hashtable<String, String>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*  938 */       fIANA2JavaMap.put("BIG5", "Big5");
/*  939 */       fIANA2JavaMap.put("CSBIG5", "Big5");
/*  940 */       fIANA2JavaMap.put("CP037", "CP037");
/*  941 */       fIANA2JavaMap.put("IBM037", "CP037");
/*  942 */       fIANA2JavaMap.put("CSIBM037", "CP037");
/*  943 */       fIANA2JavaMap.put("EBCDIC-CP-US", "CP037");
/*  944 */       fIANA2JavaMap.put("EBCDIC-CP-CA", "CP037");
/*  945 */       fIANA2JavaMap.put("EBCDIC-CP-NL", "CP037");
/*  946 */       fIANA2JavaMap.put("EBCDIC-CP-WT", "CP037");
/*  947 */       fIANA2JavaMap.put("IBM273", "CP273");
/*  948 */       fIANA2JavaMap.put("CP273", "CP273");
/*  949 */       fIANA2JavaMap.put("CSIBM273", "CP273");
/*  950 */       fIANA2JavaMap.put("IBM277", "CP277");
/*  951 */       fIANA2JavaMap.put("CP277", "CP277");
/*  952 */       fIANA2JavaMap.put("CSIBM277", "CP277");
/*  953 */       fIANA2JavaMap.put("EBCDIC-CP-DK", "CP277");
/*  954 */       fIANA2JavaMap.put("EBCDIC-CP-NO", "CP277");
/*  955 */       fIANA2JavaMap.put("IBM278", "CP278");
/*  956 */       fIANA2JavaMap.put("CP278", "CP278");
/*  957 */       fIANA2JavaMap.put("CSIBM278", "CP278");
/*  958 */       fIANA2JavaMap.put("EBCDIC-CP-FI", "CP278");
/*  959 */       fIANA2JavaMap.put("EBCDIC-CP-SE", "CP278");
/*  960 */       fIANA2JavaMap.put("IBM280", "CP280");
/*  961 */       fIANA2JavaMap.put("CP280", "CP280");
/*  962 */       fIANA2JavaMap.put("CSIBM280", "CP280");
/*  963 */       fIANA2JavaMap.put("EBCDIC-CP-IT", "CP280");
/*  964 */       fIANA2JavaMap.put("IBM284", "CP284");
/*  965 */       fIANA2JavaMap.put("CP284", "CP284");
/*  966 */       fIANA2JavaMap.put("CSIBM284", "CP284");
/*  967 */       fIANA2JavaMap.put("EBCDIC-CP-ES", "CP284");
/*  968 */       fIANA2JavaMap.put("EBCDIC-CP-GB", "CP285");
/*  969 */       fIANA2JavaMap.put("IBM285", "CP285");
/*  970 */       fIANA2JavaMap.put("CP285", "CP285");
/*  971 */       fIANA2JavaMap.put("CSIBM285", "CP285");
/*  972 */       fIANA2JavaMap.put("EBCDIC-JP-KANA", "CP290");
/*  973 */       fIANA2JavaMap.put("IBM290", "CP290");
/*  974 */       fIANA2JavaMap.put("CP290", "CP290");
/*  975 */       fIANA2JavaMap.put("CSIBM290", "CP290");
/*  976 */       fIANA2JavaMap.put("EBCDIC-CP-FR", "CP297");
/*  977 */       fIANA2JavaMap.put("IBM297", "CP297");
/*  978 */       fIANA2JavaMap.put("CP297", "CP297");
/*  979 */       fIANA2JavaMap.put("CSIBM297", "CP297");
/*  980 */       fIANA2JavaMap.put("EBCDIC-CP-AR1", "CP420");
/*  981 */       fIANA2JavaMap.put("IBM420", "CP420");
/*  982 */       fIANA2JavaMap.put("CP420", "CP420");
/*  983 */       fIANA2JavaMap.put("CSIBM420", "CP420");
/*  984 */       fIANA2JavaMap.put("EBCDIC-CP-HE", "CP424");
/*  985 */       fIANA2JavaMap.put("IBM424", "CP424");
/*  986 */       fIANA2JavaMap.put("CP424", "CP424");
/*  987 */       fIANA2JavaMap.put("CSIBM424", "CP424");
/*  988 */       fIANA2JavaMap.put("IBM437", "CP437");
/*  989 */       fIANA2JavaMap.put("437", "CP437");
/*  990 */       fIANA2JavaMap.put("CP437", "CP437");
/*  991 */       fIANA2JavaMap.put("CSPC8CODEPAGE437", "CP437");
/*  992 */       fIANA2JavaMap.put("EBCDIC-CP-CH", "CP500");
/*  993 */       fIANA2JavaMap.put("IBM500", "CP500");
/*  994 */       fIANA2JavaMap.put("CP500", "CP500");
/*  995 */       fIANA2JavaMap.put("CSIBM500", "CP500");
/*  996 */       fIANA2JavaMap.put("EBCDIC-CP-CH", "CP500");
/*  997 */       fIANA2JavaMap.put("EBCDIC-CP-BE", "CP500");
/*  998 */       fIANA2JavaMap.put("IBM775", "CP775");
/*  999 */       fIANA2JavaMap.put("CP775", "CP775");
/* 1000 */       fIANA2JavaMap.put("CSPC775BALTIC", "CP775");
/* 1001 */       fIANA2JavaMap.put("IBM850", "CP850");
/* 1002 */       fIANA2JavaMap.put("850", "CP850");
/* 1003 */       fIANA2JavaMap.put("CP850", "CP850");
/* 1004 */       fIANA2JavaMap.put("CSPC850MULTILINGUAL", "CP850");
/* 1005 */       fIANA2JavaMap.put("IBM852", "CP852");
/* 1006 */       fIANA2JavaMap.put("852", "CP852");
/* 1007 */       fIANA2JavaMap.put("CP852", "CP852");
/* 1008 */       fIANA2JavaMap.put("CSPCP852", "CP852");
/* 1009 */       fIANA2JavaMap.put("IBM855", "CP855");
/* 1010 */       fIANA2JavaMap.put("855", "CP855");
/* 1011 */       fIANA2JavaMap.put("CP855", "CP855");
/* 1012 */       fIANA2JavaMap.put("CSIBM855", "CP855");
/* 1013 */       fIANA2JavaMap.put("IBM857", "CP857");
/* 1014 */       fIANA2JavaMap.put("857", "CP857");
/* 1015 */       fIANA2JavaMap.put("CP857", "CP857");
/* 1016 */       fIANA2JavaMap.put("CSIBM857", "CP857");
/* 1017 */       fIANA2JavaMap.put("IBM00858", "CP858");
/* 1018 */       fIANA2JavaMap.put("CP00858", "CP858");
/* 1019 */       fIANA2JavaMap.put("CCSID00858", "CP858");
/* 1020 */       fIANA2JavaMap.put("IBM860", "CP860");
/* 1021 */       fIANA2JavaMap.put("860", "CP860");
/* 1022 */       fIANA2JavaMap.put("CP860", "CP860");
/* 1023 */       fIANA2JavaMap.put("CSIBM860", "CP860");
/* 1024 */       fIANA2JavaMap.put("IBM861", "CP861");
/* 1025 */       fIANA2JavaMap.put("861", "CP861");
/* 1026 */       fIANA2JavaMap.put("CP861", "CP861");
/* 1027 */       fIANA2JavaMap.put("CP-IS", "CP861");
/* 1028 */       fIANA2JavaMap.put("CSIBM861", "CP861");
/* 1029 */       fIANA2JavaMap.put("IBM862", "CP862");
/* 1030 */       fIANA2JavaMap.put("862", "CP862");
/* 1031 */       fIANA2JavaMap.put("CP862", "CP862");
/* 1032 */       fIANA2JavaMap.put("CSPC862LATINHEBREW", "CP862");
/* 1033 */       fIANA2JavaMap.put("IBM863", "CP863");
/* 1034 */       fIANA2JavaMap.put("863", "CP863");
/* 1035 */       fIANA2JavaMap.put("CP863", "CP863");
/* 1036 */       fIANA2JavaMap.put("CSIBM863", "CP863");
/* 1037 */       fIANA2JavaMap.put("IBM864", "CP864");
/* 1038 */       fIANA2JavaMap.put("CP864", "CP864");
/* 1039 */       fIANA2JavaMap.put("CSIBM864", "CP864");
/* 1040 */       fIANA2JavaMap.put("IBM865", "CP865");
/* 1041 */       fIANA2JavaMap.put("865", "CP865");
/* 1042 */       fIANA2JavaMap.put("CP865", "CP865");
/* 1043 */       fIANA2JavaMap.put("CSIBM865", "CP865");
/* 1044 */       fIANA2JavaMap.put("IBM866", "CP866");
/* 1045 */       fIANA2JavaMap.put("866", "CP866");
/* 1046 */       fIANA2JavaMap.put("CP866", "CP866");
/* 1047 */       fIANA2JavaMap.put("CSIBM866", "CP866");
/* 1048 */       fIANA2JavaMap.put("IBM868", "CP868");
/* 1049 */       fIANA2JavaMap.put("CP868", "CP868");
/* 1050 */       fIANA2JavaMap.put("CSIBM868", "CP868");
/* 1051 */       fIANA2JavaMap.put("CP-AR", "CP868");
/* 1052 */       fIANA2JavaMap.put("IBM869", "CP869");
/* 1053 */       fIANA2JavaMap.put("CP869", "CP869");
/* 1054 */       fIANA2JavaMap.put("CSIBM869", "CP869");
/* 1055 */       fIANA2JavaMap.put("CP-GR", "CP869");
/* 1056 */       fIANA2JavaMap.put("IBM870", "CP870");
/* 1057 */       fIANA2JavaMap.put("CP870", "CP870");
/* 1058 */       fIANA2JavaMap.put("CSIBM870", "CP870");
/* 1059 */       fIANA2JavaMap.put("EBCDIC-CP-ROECE", "CP870");
/* 1060 */       fIANA2JavaMap.put("EBCDIC-CP-YU", "CP870");
/* 1061 */       fIANA2JavaMap.put("IBM871", "CP871");
/* 1062 */       fIANA2JavaMap.put("CP871", "CP871");
/* 1063 */       fIANA2JavaMap.put("CSIBM871", "CP871");
/* 1064 */       fIANA2JavaMap.put("EBCDIC-CP-IS", "CP871");
/* 1065 */       fIANA2JavaMap.put("IBM918", "CP918");
/* 1066 */       fIANA2JavaMap.put("CP918", "CP918");
/* 1067 */       fIANA2JavaMap.put("CSIBM918", "CP918");
/* 1068 */       fIANA2JavaMap.put("EBCDIC-CP-AR2", "CP918");
/* 1069 */       fIANA2JavaMap.put("IBM00924", "CP924");
/* 1070 */       fIANA2JavaMap.put("CP00924", "CP924");
/* 1071 */       fIANA2JavaMap.put("CCSID00924", "CP924");
/*      */       
/* 1073 */       fIANA2JavaMap.put("EBCDIC-LATIN9--EURO", "CP924");
/* 1074 */       fIANA2JavaMap.put("IBM1026", "CP1026");
/* 1075 */       fIANA2JavaMap.put("CP1026", "CP1026");
/* 1076 */       fIANA2JavaMap.put("CSIBM1026", "CP1026");
/* 1077 */       fIANA2JavaMap.put("IBM01140", "Cp1140");
/* 1078 */       fIANA2JavaMap.put("CP01140", "Cp1140");
/* 1079 */       fIANA2JavaMap.put("CCSID01140", "Cp1140");
/* 1080 */       fIANA2JavaMap.put("IBM01141", "Cp1141");
/* 1081 */       fIANA2JavaMap.put("CP01141", "Cp1141");
/* 1082 */       fIANA2JavaMap.put("CCSID01141", "Cp1141");
/* 1083 */       fIANA2JavaMap.put("IBM01142", "Cp1142");
/* 1084 */       fIANA2JavaMap.put("CP01142", "Cp1142");
/* 1085 */       fIANA2JavaMap.put("CCSID01142", "Cp1142");
/* 1086 */       fIANA2JavaMap.put("IBM01143", "Cp1143");
/* 1087 */       fIANA2JavaMap.put("CP01143", "Cp1143");
/* 1088 */       fIANA2JavaMap.put("CCSID01143", "Cp1143");
/* 1089 */       fIANA2JavaMap.put("IBM01144", "Cp1144");
/* 1090 */       fIANA2JavaMap.put("CP01144", "Cp1144");
/* 1091 */       fIANA2JavaMap.put("CCSID01144", "Cp1144");
/* 1092 */       fIANA2JavaMap.put("IBM01145", "Cp1145");
/* 1093 */       fIANA2JavaMap.put("CP01145", "Cp1145");
/* 1094 */       fIANA2JavaMap.put("CCSID01145", "Cp1145");
/* 1095 */       fIANA2JavaMap.put("IBM01146", "Cp1146");
/* 1096 */       fIANA2JavaMap.put("CP01146", "Cp1146");
/* 1097 */       fIANA2JavaMap.put("CCSID01146", "Cp1146");
/* 1098 */       fIANA2JavaMap.put("IBM01147", "Cp1147");
/* 1099 */       fIANA2JavaMap.put("CP01147", "Cp1147");
/* 1100 */       fIANA2JavaMap.put("CCSID01147", "Cp1147");
/* 1101 */       fIANA2JavaMap.put("IBM01148", "Cp1148");
/* 1102 */       fIANA2JavaMap.put("CP01148", "Cp1148");
/* 1103 */       fIANA2JavaMap.put("CCSID01148", "Cp1148");
/* 1104 */       fIANA2JavaMap.put("IBM01149", "Cp1149");
/* 1105 */       fIANA2JavaMap.put("CP01149", "Cp1149");
/* 1106 */       fIANA2JavaMap.put("CCSID01149", "Cp1149");
/* 1107 */       fIANA2JavaMap.put("EUC-JP", "EUCJIS");
/* 1108 */       fIANA2JavaMap.put("CSEUCPKDFMTJAPANESE", "EUCJIS");
/* 1109 */       fIANA2JavaMap.put("EXTENDED_UNIX_CODE_PACKED_FORMAT_FOR_JAPANESE", "EUCJIS");
/* 1110 */       fIANA2JavaMap.put("EUC-KR", "KSC5601");
/* 1111 */       fIANA2JavaMap.put("GB2312", "GB2312");
/* 1112 */       fIANA2JavaMap.put("CSGB2312", "GB2312");
/* 1113 */       fIANA2JavaMap.put("ISO-2022-JP", "JIS");
/* 1114 */       fIANA2JavaMap.put("CSISO2022JP", "JIS");
/* 1115 */       fIANA2JavaMap.put("ISO-2022-KR", "ISO2022KR");
/* 1116 */       fIANA2JavaMap.put("CSISO2022KR", "ISO2022KR");
/* 1117 */       fIANA2JavaMap.put("ISO-2022-CN", "ISO2022CN");
/*      */       
/* 1119 */       fIANA2JavaMap.put("X0201", "JIS0201");
/* 1120 */       fIANA2JavaMap.put("CSISO13JISC6220JP", "JIS0201");
/* 1121 */       fIANA2JavaMap.put("X0208", "JIS0208");
/* 1122 */       fIANA2JavaMap.put("ISO-IR-87", "JIS0208");
/* 1123 */       fIANA2JavaMap.put("X0208dbiJIS_X0208-1983", "JIS0208");
/* 1124 */       fIANA2JavaMap.put("CSISO87JISX0208", "JIS0208");
/* 1125 */       fIANA2JavaMap.put("X0212", "JIS0212");
/* 1126 */       fIANA2JavaMap.put("ISO-IR-159", "JIS0212");
/* 1127 */       fIANA2JavaMap.put("CSISO159JISX02121990", "JIS0212");
/* 1128 */       fIANA2JavaMap.put("GB18030", "GB18030");
/* 1129 */       fIANA2JavaMap.put("SHIFT_JIS", "SJIS");
/* 1130 */       fIANA2JavaMap.put("CSSHIFTJIS", "SJIS");
/* 1131 */       fIANA2JavaMap.put("MS_KANJI", "SJIS");
/* 1132 */       fIANA2JavaMap.put("WINDOWS-31J", "MS932");
/* 1133 */       fIANA2JavaMap.put("CSWINDOWS31J", "MS932");
/*      */ 
/*      */       
/* 1136 */       fIANA2JavaMap.put("WINDOWS-1250", "Cp1250");
/* 1137 */       fIANA2JavaMap.put("WINDOWS-1251", "Cp1251");
/* 1138 */       fIANA2JavaMap.put("WINDOWS-1252", "Cp1252");
/* 1139 */       fIANA2JavaMap.put("WINDOWS-1253", "Cp1253");
/* 1140 */       fIANA2JavaMap.put("WINDOWS-1254", "Cp1254");
/* 1141 */       fIANA2JavaMap.put("WINDOWS-1255", "Cp1255");
/* 1142 */       fIANA2JavaMap.put("WINDOWS-1256", "Cp1256");
/* 1143 */       fIANA2JavaMap.put("WINDOWS-1257", "Cp1257");
/* 1144 */       fIANA2JavaMap.put("WINDOWS-1258", "Cp1258");
/* 1145 */       fIANA2JavaMap.put("TIS-620", "TIS620");
/*      */       
/* 1147 */       fIANA2JavaMap.put("ISO-8859-1", "ISO8859_1");
/* 1148 */       fIANA2JavaMap.put("ISO-IR-100", "ISO8859_1");
/* 1149 */       fIANA2JavaMap.put("ISO_8859-1", "ISO8859_1");
/* 1150 */       fIANA2JavaMap.put("LATIN1", "ISO8859_1");
/* 1151 */       fIANA2JavaMap.put("CSISOLATIN1", "ISO8859_1");
/* 1152 */       fIANA2JavaMap.put("L1", "ISO8859_1");
/* 1153 */       fIANA2JavaMap.put("IBM819", "ISO8859_1");
/* 1154 */       fIANA2JavaMap.put("CP819", "ISO8859_1");
/*      */       
/* 1156 */       fIANA2JavaMap.put("ISO-8859-2", "ISO8859_2");
/* 1157 */       fIANA2JavaMap.put("ISO-IR-101", "ISO8859_2");
/* 1158 */       fIANA2JavaMap.put("ISO_8859-2", "ISO8859_2");
/* 1159 */       fIANA2JavaMap.put("LATIN2", "ISO8859_2");
/* 1160 */       fIANA2JavaMap.put("CSISOLATIN2", "ISO8859_2");
/* 1161 */       fIANA2JavaMap.put("L2", "ISO8859_2");
/*      */       
/* 1163 */       fIANA2JavaMap.put("ISO-8859-3", "ISO8859_3");
/* 1164 */       fIANA2JavaMap.put("ISO-IR-109", "ISO8859_3");
/* 1165 */       fIANA2JavaMap.put("ISO_8859-3", "ISO8859_3");
/* 1166 */       fIANA2JavaMap.put("LATIN3", "ISO8859_3");
/* 1167 */       fIANA2JavaMap.put("CSISOLATIN3", "ISO8859_3");
/* 1168 */       fIANA2JavaMap.put("L3", "ISO8859_3");
/*      */       
/* 1170 */       fIANA2JavaMap.put("ISO-8859-4", "ISO8859_4");
/* 1171 */       fIANA2JavaMap.put("ISO-IR-110", "ISO8859_4");
/* 1172 */       fIANA2JavaMap.put("ISO_8859-4", "ISO8859_4");
/* 1173 */       fIANA2JavaMap.put("LATIN4", "ISO8859_4");
/* 1174 */       fIANA2JavaMap.put("CSISOLATIN4", "ISO8859_4");
/* 1175 */       fIANA2JavaMap.put("L4", "ISO8859_4");
/*      */       
/* 1177 */       fIANA2JavaMap.put("ISO-8859-5", "ISO8859_5");
/* 1178 */       fIANA2JavaMap.put("ISO-IR-144", "ISO8859_5");
/* 1179 */       fIANA2JavaMap.put("ISO_8859-5", "ISO8859_5");
/* 1180 */       fIANA2JavaMap.put("CYRILLIC", "ISO8859_5");
/* 1181 */       fIANA2JavaMap.put("CSISOLATINCYRILLIC", "ISO8859_5");
/*      */       
/* 1183 */       fIANA2JavaMap.put("ISO-8859-6", "ISO8859_6");
/* 1184 */       fIANA2JavaMap.put("ISO-IR-127", "ISO8859_6");
/* 1185 */       fIANA2JavaMap.put("ISO_8859-6", "ISO8859_6");
/* 1186 */       fIANA2JavaMap.put("ECMA-114", "ISO8859_6");
/* 1187 */       fIANA2JavaMap.put("ASMO-708", "ISO8859_6");
/* 1188 */       fIANA2JavaMap.put("ARABIC", "ISO8859_6");
/* 1189 */       fIANA2JavaMap.put("CSISOLATINARABIC", "ISO8859_6");
/*      */       
/* 1191 */       fIANA2JavaMap.put("ISO-8859-7", "ISO8859_7");
/* 1192 */       fIANA2JavaMap.put("ISO-IR-126", "ISO8859_7");
/* 1193 */       fIANA2JavaMap.put("ISO_8859-7", "ISO8859_7");
/* 1194 */       fIANA2JavaMap.put("ELOT_928", "ISO8859_7");
/* 1195 */       fIANA2JavaMap.put("ECMA-118", "ISO8859_7");
/* 1196 */       fIANA2JavaMap.put("GREEK", "ISO8859_7");
/* 1197 */       fIANA2JavaMap.put("CSISOLATINGREEK", "ISO8859_7");
/* 1198 */       fIANA2JavaMap.put("GREEK8", "ISO8859_7");
/*      */       
/* 1200 */       fIANA2JavaMap.put("ISO-8859-8", "ISO8859_8");
/* 1201 */       fIANA2JavaMap.put("ISO-8859-8-I", "ISO8859_8");
/* 1202 */       fIANA2JavaMap.put("ISO-IR-138", "ISO8859_8");
/* 1203 */       fIANA2JavaMap.put("ISO_8859-8", "ISO8859_8");
/* 1204 */       fIANA2JavaMap.put("HEBREW", "ISO8859_8");
/* 1205 */       fIANA2JavaMap.put("CSISOLATINHEBREW", "ISO8859_8");
/*      */       
/* 1207 */       fIANA2JavaMap.put("ISO-8859-9", "ISO8859_9");
/* 1208 */       fIANA2JavaMap.put("ISO-IR-148", "ISO8859_9");
/* 1209 */       fIANA2JavaMap.put("ISO_8859-9", "ISO8859_9");
/* 1210 */       fIANA2JavaMap.put("LATIN5", "ISO8859_9");
/* 1211 */       fIANA2JavaMap.put("CSISOLATIN5", "ISO8859_9");
/* 1212 */       fIANA2JavaMap.put("L5", "ISO8859_9");
/*      */       
/* 1214 */       fIANA2JavaMap.put("KOI8-R", "KOI8_R");
/* 1215 */       fIANA2JavaMap.put("CSKOI8R", "KOI8_R");
/* 1216 */       fIANA2JavaMap.put("US-ASCII", "ASCII");
/* 1217 */       fIANA2JavaMap.put("ISO-IR-6", "ASCII");
/* 1218 */       fIANA2JavaMap.put("ANSI_X3.4-1986", "ASCII");
/* 1219 */       fIANA2JavaMap.put("ISO_646.IRV:1991", "ASCII");
/* 1220 */       fIANA2JavaMap.put("ASCII", "ASCII");
/* 1221 */       fIANA2JavaMap.put("CSASCII", "ASCII");
/* 1222 */       fIANA2JavaMap.put("ISO646-US", "ASCII");
/* 1223 */       fIANA2JavaMap.put("US", "ASCII");
/* 1224 */       fIANA2JavaMap.put("IBM367", "ASCII");
/* 1225 */       fIANA2JavaMap.put("CP367", "ASCII");
/* 1226 */       fIANA2JavaMap.put("UTF-8", "UTF8");
/* 1227 */       fIANA2JavaMap.put("UTF-16", "Unicode");
/* 1228 */       fIANA2JavaMap.put("UTF-16BE", "UnicodeBig");
/* 1229 */       fIANA2JavaMap.put("UTF-16LE", "UnicodeLittle");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1234 */       fIANA2JavaMap.put("IBM-1047", "Cp1047");
/* 1235 */       fIANA2JavaMap.put("IBM1047", "Cp1047");
/* 1236 */       fIANA2JavaMap.put("CP1047", "Cp1047");
/*      */ 
/*      */ 
/*      */       
/* 1240 */       fIANA2JavaMap.put("IBM-37", "CP037");
/* 1241 */       fIANA2JavaMap.put("IBM-273", "CP273");
/* 1242 */       fIANA2JavaMap.put("IBM-277", "CP277");
/* 1243 */       fIANA2JavaMap.put("IBM-278", "CP278");
/* 1244 */       fIANA2JavaMap.put("IBM-280", "CP280");
/* 1245 */       fIANA2JavaMap.put("IBM-284", "CP284");
/* 1246 */       fIANA2JavaMap.put("IBM-285", "CP285");
/* 1247 */       fIANA2JavaMap.put("IBM-290", "CP290");
/* 1248 */       fIANA2JavaMap.put("IBM-297", "CP297");
/* 1249 */       fIANA2JavaMap.put("IBM-420", "CP420");
/* 1250 */       fIANA2JavaMap.put("IBM-424", "CP424");
/* 1251 */       fIANA2JavaMap.put("IBM-437", "CP437");
/* 1252 */       fIANA2JavaMap.put("IBM-500", "CP500");
/* 1253 */       fIANA2JavaMap.put("IBM-775", "CP775");
/* 1254 */       fIANA2JavaMap.put("IBM-850", "CP850");
/* 1255 */       fIANA2JavaMap.put("IBM-852", "CP852");
/* 1256 */       fIANA2JavaMap.put("IBM-855", "CP855");
/* 1257 */       fIANA2JavaMap.put("IBM-857", "CP857");
/* 1258 */       fIANA2JavaMap.put("IBM-858", "CP858");
/* 1259 */       fIANA2JavaMap.put("IBM-860", "CP860");
/* 1260 */       fIANA2JavaMap.put("IBM-861", "CP861");
/* 1261 */       fIANA2JavaMap.put("IBM-862", "CP862");
/* 1262 */       fIANA2JavaMap.put("IBM-863", "CP863");
/* 1263 */       fIANA2JavaMap.put("IBM-864", "CP864");
/* 1264 */       fIANA2JavaMap.put("IBM-865", "CP865");
/* 1265 */       fIANA2JavaMap.put("IBM-866", "CP866");
/* 1266 */       fIANA2JavaMap.put("IBM-868", "CP868");
/* 1267 */       fIANA2JavaMap.put("IBM-869", "CP869");
/* 1268 */       fIANA2JavaMap.put("IBM-870", "CP870");
/* 1269 */       fIANA2JavaMap.put("IBM-871", "CP871");
/* 1270 */       fIANA2JavaMap.put("IBM-918", "CP918");
/* 1271 */       fIANA2JavaMap.put("IBM-924", "CP924");
/* 1272 */       fIANA2JavaMap.put("IBM-1026", "CP1026");
/* 1273 */       fIANA2JavaMap.put("IBM-1140", "Cp1140");
/* 1274 */       fIANA2JavaMap.put("IBM-1141", "Cp1141");
/* 1275 */       fIANA2JavaMap.put("IBM-1142", "Cp1142");
/* 1276 */       fIANA2JavaMap.put("IBM-1143", "Cp1143");
/* 1277 */       fIANA2JavaMap.put("IBM-1144", "Cp1144");
/* 1278 */       fIANA2JavaMap.put("IBM-1145", "Cp1145");
/* 1279 */       fIANA2JavaMap.put("IBM-1146", "Cp1146");
/* 1280 */       fIANA2JavaMap.put("IBM-1147", "Cp1147");
/* 1281 */       fIANA2JavaMap.put("IBM-1148", "Cp1148");
/* 1282 */       fIANA2JavaMap.put("IBM-1149", "Cp1149");
/* 1283 */       fIANA2JavaMap.put("IBM-819", "ISO8859_1");
/* 1284 */       fIANA2JavaMap.put("IBM-367", "ASCII");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1292 */       fJava2IANAMap.put("ISO8859_1", "ISO-8859-1");
/* 1293 */       fJava2IANAMap.put("ISO8859_2", "ISO-8859-2");
/* 1294 */       fJava2IANAMap.put("ISO8859_3", "ISO-8859-3");
/* 1295 */       fJava2IANAMap.put("ISO8859_4", "ISO-8859-4");
/* 1296 */       fJava2IANAMap.put("ISO8859_5", "ISO-8859-5");
/* 1297 */       fJava2IANAMap.put("ISO8859_6", "ISO-8859-6");
/* 1298 */       fJava2IANAMap.put("ISO8859_7", "ISO-8859-7");
/* 1299 */       fJava2IANAMap.put("ISO8859_8", "ISO-8859-8");
/* 1300 */       fJava2IANAMap.put("ISO8859_9", "ISO-8859-9");
/* 1301 */       fJava2IANAMap.put("Big5", "BIG5");
/* 1302 */       fJava2IANAMap.put("CP037", "EBCDIC-CP-US");
/* 1303 */       fJava2IANAMap.put("CP273", "IBM273");
/* 1304 */       fJava2IANAMap.put("CP277", "EBCDIC-CP-DK");
/* 1305 */       fJava2IANAMap.put("CP278", "EBCDIC-CP-FI");
/* 1306 */       fJava2IANAMap.put("CP280", "EBCDIC-CP-IT");
/* 1307 */       fJava2IANAMap.put("CP284", "EBCDIC-CP-ES");
/* 1308 */       fJava2IANAMap.put("CP285", "EBCDIC-CP-GB");
/* 1309 */       fJava2IANAMap.put("CP290", "EBCDIC-JP-KANA");
/* 1310 */       fJava2IANAMap.put("CP297", "EBCDIC-CP-FR");
/* 1311 */       fJava2IANAMap.put("CP420", "EBCDIC-CP-AR1");
/* 1312 */       fJava2IANAMap.put("CP424", "EBCDIC-CP-HE");
/* 1313 */       fJava2IANAMap.put("CP437", "IBM437");
/* 1314 */       fJava2IANAMap.put("CP500", "EBCDIC-CP-CH");
/* 1315 */       fJava2IANAMap.put("CP775", "IBM775");
/* 1316 */       fJava2IANAMap.put("CP850", "IBM850");
/* 1317 */       fJava2IANAMap.put("CP852", "IBM852");
/* 1318 */       fJava2IANAMap.put("CP855", "IBM855");
/* 1319 */       fJava2IANAMap.put("CP857", "IBM857");
/* 1320 */       fJava2IANAMap.put("CP858", "IBM00858");
/* 1321 */       fJava2IANAMap.put("CP860", "IBM860");
/* 1322 */       fJava2IANAMap.put("CP861", "IBM861");
/* 1323 */       fJava2IANAMap.put("CP862", "IBM862");
/* 1324 */       fJava2IANAMap.put("CP863", "IBM863");
/* 1325 */       fJava2IANAMap.put("CP864", "IBM864");
/* 1326 */       fJava2IANAMap.put("CP865", "IBM865");
/* 1327 */       fJava2IANAMap.put("CP866", "IBM866");
/* 1328 */       fJava2IANAMap.put("CP868", "IBM868");
/* 1329 */       fJava2IANAMap.put("CP869", "IBM869");
/* 1330 */       fJava2IANAMap.put("CP870", "EBCDIC-CP-ROECE");
/* 1331 */       fJava2IANAMap.put("CP871", "EBCDIC-CP-IS");
/* 1332 */       fJava2IANAMap.put("CP918", "EBCDIC-CP-AR2");
/* 1333 */       fJava2IANAMap.put("CP924", "IBM00924");
/* 1334 */       fJava2IANAMap.put("CP1026", "IBM1026");
/* 1335 */       fJava2IANAMap.put("Cp01140", "IBM01140");
/* 1336 */       fJava2IANAMap.put("Cp01141", "IBM01141");
/* 1337 */       fJava2IANAMap.put("Cp01142", "IBM01142");
/* 1338 */       fJava2IANAMap.put("Cp01143", "IBM01143");
/* 1339 */       fJava2IANAMap.put("Cp01144", "IBM01144");
/* 1340 */       fJava2IANAMap.put("Cp01145", "IBM01145");
/* 1341 */       fJava2IANAMap.put("Cp01146", "IBM01146");
/* 1342 */       fJava2IANAMap.put("Cp01147", "IBM01147");
/* 1343 */       fJava2IANAMap.put("Cp01148", "IBM01148");
/* 1344 */       fJava2IANAMap.put("Cp01149", "IBM01149");
/* 1345 */       fJava2IANAMap.put("EUCJIS", "EUC-JP");
/* 1346 */       fJava2IANAMap.put("GB2312", "GB2312");
/* 1347 */       fJava2IANAMap.put("ISO2022KR", "ISO-2022-KR");
/* 1348 */       fJava2IANAMap.put("ISO2022CN", "ISO-2022-CN");
/* 1349 */       fJava2IANAMap.put("JIS", "ISO-2022-JP");
/* 1350 */       fJava2IANAMap.put("KOI8_R", "KOI8-R");
/* 1351 */       fJava2IANAMap.put("KSC5601", "EUC-KR");
/* 1352 */       fJava2IANAMap.put("GB18030", "GB18030");
/* 1353 */       fJava2IANAMap.put("SJIS", "SHIFT_JIS");
/* 1354 */       fJava2IANAMap.put("MS932", "WINDOWS-31J");
/* 1355 */       fJava2IANAMap.put("UTF8", "UTF-8");
/* 1356 */       fJava2IANAMap.put("Unicode", "UTF-16");
/* 1357 */       fJava2IANAMap.put("UnicodeBig", "UTF-16BE");
/* 1358 */       fJava2IANAMap.put("UnicodeLittle", "UTF-16LE");
/* 1359 */       fJava2IANAMap.put("JIS0201", "X0201");
/* 1360 */       fJava2IANAMap.put("JIS0208", "X0208");
/* 1361 */       fJava2IANAMap.put("JIS0212", "ISO-IR-159");
/*      */ 
/*      */       
/* 1364 */       fJava2IANAMap.put("CP1047", "IBM1047");
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
/*      */     public static void putIANA2JavaMapping(String ianaEncoding, String javaEncoding) {
/* 1390 */       fIANA2JavaMap.put(ianaEncoding, javaEncoding);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String getIANA2JavaMapping(String ianaEncoding) {
/* 1399 */       return fIANA2JavaMap.get(ianaEncoding);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String removeIANA2JavaMapping(String ianaEncoding) {
/* 1408 */       return fIANA2JavaMap.remove(ianaEncoding);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static void putJava2IANAMapping(String javaEncoding, String ianaEncoding) {
/* 1419 */       fJava2IANAMap.put(javaEncoding, ianaEncoding);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String getJava2IANAMapping(String javaEncoding) {
/* 1428 */       return fJava2IANAMap.get(javaEncoding);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String removeJava2IANAMapping(String javaEncoding) {
/* 1437 */       return fJava2IANAMap.remove(javaEncoding);
/*      */     }
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
/*      */   public static final class URI
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
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
/*      */     public static class MalformedURIException
/*      */       extends IOException
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
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
/*      */       public MalformedURIException() {}
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
/*      */       public MalformedURIException(String p_msg) {
/* 1507 */         super(p_msg);
/*      */       }
/*      */     }
/*      */     
/* 1511 */     private static final byte[] fgLookupTable = new byte[128];
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int RESERVED_CHARACTERS = 1;
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int MARK_CHARACTERS = 2;
/*      */ 
/*      */     
/*      */     private static final int SCHEME_CHARACTERS = 4;
/*      */ 
/*      */     
/*      */     private static final int USERINFO_CHARACTERS = 8;
/*      */ 
/*      */     
/*      */     private static final int ASCII_ALPHA_CHARACTERS = 16;
/*      */ 
/*      */     
/*      */     private static final int ASCII_DIGIT_CHARACTERS = 32;
/*      */ 
/*      */     
/*      */     private static final int ASCII_HEX_CHARACTERS = 64;
/*      */ 
/*      */     
/*      */     private static final int PATH_CHARACTERS = 128;
/*      */ 
/*      */     
/*      */     private static final int MASK_ALPHA_NUMERIC = 48;
/*      */ 
/*      */     
/*      */     private static final int MASK_UNRESERVED_MASK = 50;
/*      */ 
/*      */     
/*      */     private static final int MASK_URI_CHARACTER = 51;
/*      */ 
/*      */     
/*      */     private static final int MASK_SCHEME_CHARACTER = 52;
/*      */ 
/*      */     
/*      */     private static final int MASK_USERINFO_CHARACTER = 58;
/*      */ 
/*      */     
/*      */     private static final int MASK_PATH_CHARACTER = 178;
/*      */ 
/*      */     
/*      */     public static final URI BASE_URI;
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*      */       int j;
/* 1564 */       for (j = 48; j <= 57; j++) {
/* 1565 */         fgLookupTable[j] = (byte)(fgLookupTable[j] | 0x60);
/*      */       }
/*      */ 
/*      */       
/* 1569 */       for (j = 65; j <= 70; j++) {
/* 1570 */         fgLookupTable[j] = (byte)(fgLookupTable[j] | 0x50);
/* 1571 */         fgLookupTable[j + 32] = (byte)(fgLookupTable[j + 32] | 0x50);
/*      */       } 
/*      */ 
/*      */       
/* 1575 */       for (j = 71; j <= 90; j++) {
/* 1576 */         fgLookupTable[j] = (byte)(fgLookupTable[j] | 0x10);
/* 1577 */         fgLookupTable[j + 32] = (byte)(fgLookupTable[j + 32] | 0x10);
/*      */       } 
/*      */ 
/*      */       
/* 1581 */       fgLookupTable[59] = (byte)(fgLookupTable[59] | 0x1);
/* 1582 */       fgLookupTable[47] = (byte)(fgLookupTable[47] | 0x1);
/* 1583 */       fgLookupTable[63] = (byte)(fgLookupTable[63] | 0x1);
/* 1584 */       fgLookupTable[58] = (byte)(fgLookupTable[58] | 0x1);
/* 1585 */       fgLookupTable[64] = (byte)(fgLookupTable[64] | 0x1);
/* 1586 */       fgLookupTable[38] = (byte)(fgLookupTable[38] | 0x1);
/* 1587 */       fgLookupTable[61] = (byte)(fgLookupTable[61] | 0x1);
/* 1588 */       fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x1);
/* 1589 */       fgLookupTable[36] = (byte)(fgLookupTable[36] | 0x1);
/* 1590 */       fgLookupTable[44] = (byte)(fgLookupTable[44] | 0x1);
/* 1591 */       fgLookupTable[91] = (byte)(fgLookupTable[91] | 0x1);
/* 1592 */       fgLookupTable[93] = (byte)(fgLookupTable[93] | 0x1);
/*      */ 
/*      */       
/* 1595 */       fgLookupTable[45] = (byte)(fgLookupTable[45] | 0x2);
/* 1596 */       fgLookupTable[95] = (byte)(fgLookupTable[95] | 0x2);
/* 1597 */       fgLookupTable[46] = (byte)(fgLookupTable[46] | 0x2);
/* 1598 */       fgLookupTable[33] = (byte)(fgLookupTable[33] | 0x2);
/* 1599 */       fgLookupTable[126] = (byte)(fgLookupTable[126] | 0x2);
/* 1600 */       fgLookupTable[42] = (byte)(fgLookupTable[42] | 0x2);
/* 1601 */       fgLookupTable[39] = (byte)(fgLookupTable[39] | 0x2);
/* 1602 */       fgLookupTable[40] = (byte)(fgLookupTable[40] | 0x2);
/* 1603 */       fgLookupTable[41] = (byte)(fgLookupTable[41] | 0x2);
/*      */ 
/*      */       
/* 1606 */       fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x4);
/* 1607 */       fgLookupTable[45] = (byte)(fgLookupTable[45] | 0x4);
/* 1608 */       fgLookupTable[46] = (byte)(fgLookupTable[46] | 0x4);
/*      */ 
/*      */       
/* 1611 */       fgLookupTable[59] = (byte)(fgLookupTable[59] | 0x8);
/* 1612 */       fgLookupTable[58] = (byte)(fgLookupTable[58] | 0x8);
/* 1613 */       fgLookupTable[38] = (byte)(fgLookupTable[38] | 0x8);
/* 1614 */       fgLookupTable[61] = (byte)(fgLookupTable[61] | 0x8);
/* 1615 */       fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x8);
/* 1616 */       fgLookupTable[36] = (byte)(fgLookupTable[36] | 0x8);
/* 1617 */       fgLookupTable[44] = (byte)(fgLookupTable[44] | 0x8);
/*      */ 
/*      */       
/* 1620 */       fgLookupTable[59] = (byte)(fgLookupTable[59] | 0x80);
/* 1621 */       fgLookupTable[47] = (byte)(fgLookupTable[47] | 0x80);
/* 1622 */       fgLookupTable[58] = (byte)(fgLookupTable[58] | 0x80);
/* 1623 */       fgLookupTable[64] = (byte)(fgLookupTable[64] | 0x80);
/* 1624 */       fgLookupTable[38] = (byte)(fgLookupTable[38] | 0x80);
/* 1625 */       fgLookupTable[61] = (byte)(fgLookupTable[61] | 0x80);
/* 1626 */       fgLookupTable[43] = (byte)(fgLookupTable[43] | 0x80);
/* 1627 */       fgLookupTable[36] = (byte)(fgLookupTable[36] | 0x80);
/* 1628 */       fgLookupTable[44] = (byte)(fgLookupTable[44] | 0x80);
/*      */ 
/*      */ 
/*      */       
/* 1632 */       URI uri = null;
/*      */       try {
/* 1634 */         uri = new URI("abc://def.ghi.jkl");
/* 1635 */       } catch (MalformedURIException malformedURIException) {}
/*      */ 
/*      */       
/* 1638 */       BASE_URI = uri;
/*      */     }
/*      */     
/* 1641 */     private String m_scheme = null;
/*      */ 
/*      */     
/* 1644 */     private String m_userinfo = null;
/*      */ 
/*      */     
/* 1647 */     private String m_host = null;
/*      */ 
/*      */     
/* 1650 */     private int m_port = -1;
/*      */ 
/*      */     
/* 1653 */     private String m_regAuthority = null;
/*      */ 
/*      */     
/* 1656 */     private String m_path = null;
/*      */ 
/*      */ 
/*      */     
/* 1660 */     private String m_queryString = null;
/*      */ 
/*      */     
/* 1663 */     private String m_fragment = null;
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
/*      */     public URI(URI p_other) {
/* 1679 */       initialize(p_other);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public URI(String p_uriSpec) throws MalformedURIException {
/* 1698 */       this((URI)null, p_uriSpec);
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
/*      */ 
/*      */ 
/*      */     
/*      */     public URI(URI p_base, String p_uriSpec) throws MalformedURIException {
/* 1714 */       initialize(p_base, p_uriSpec);
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public URI(String p_scheme, String p_schemeSpecificPart) throws MalformedURIException {
/* 1731 */       if (p_scheme == null || p_scheme.trim().length() == 0) {
/* 1732 */         throw new MalformedURIException(
/* 1733 */             "Cannot construct URI with null/empty scheme!");
/*      */       }
/* 1735 */       if (p_schemeSpecificPart == null || 
/* 1736 */         p_schemeSpecificPart.trim().length() == 0) {
/* 1737 */         throw new MalformedURIException(
/* 1738 */             "Cannot construct URI with null/empty scheme-specific part!");
/*      */       }
/* 1740 */       setScheme(p_scheme);
/* 1741 */       setPath(p_schemeSpecificPart);
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
/*      */     public URI(String p_scheme, String p_host, String p_path, String p_queryString, String p_fragment) throws MalformedURIException {
/* 1768 */       this(p_scheme, null, p_host, -1, p_path, p_queryString, p_fragment);
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
/*      */     public URI(String p_scheme, String p_userinfo, String p_host, int p_port, String p_path, String p_queryString, String p_fragment) throws MalformedURIException {
/* 1800 */       if (p_scheme == null || p_scheme.trim().length() == 0) {
/* 1801 */         throw new MalformedURIException("Scheme is required!");
/*      */       }
/*      */       
/* 1804 */       if (p_host == null) {
/* 1805 */         if (p_userinfo != null) {
/* 1806 */           throw new MalformedURIException(
/* 1807 */               "Userinfo may not be specified if host is not specified!");
/*      */         }
/* 1809 */         if (p_port != -1) {
/* 1810 */           throw new MalformedURIException(
/* 1811 */               "Port may not be specified if host is not specified!");
/*      */         }
/*      */       } 
/*      */       
/* 1815 */       if (p_path != null) {
/* 1816 */         if (p_path.indexOf('?') != -1 && p_queryString != null) {
/* 1817 */           throw new MalformedURIException(
/* 1818 */               "Query string cannot be specified in path and query string!");
/*      */         }
/*      */         
/* 1821 */         if (p_path.indexOf('#') != -1 && p_fragment != null) {
/* 1822 */           throw new MalformedURIException(
/* 1823 */               "Fragment cannot be specified in both the path and fragment!");
/*      */         }
/*      */       } 
/*      */       
/* 1827 */       setScheme(p_scheme);
/* 1828 */       setHost(p_host);
/* 1829 */       setPort(p_port);
/* 1830 */       setUserinfo(p_userinfo);
/* 1831 */       setPath(p_path);
/* 1832 */       setQueryString(p_queryString);
/* 1833 */       setFragment(p_fragment);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void initialize(URI p_other) {
/* 1842 */       this.m_scheme = p_other.getScheme();
/* 1843 */       this.m_userinfo = p_other.getUserinfo();
/* 1844 */       this.m_host = p_other.getHost();
/* 1845 */       this.m_port = p_other.getPort();
/* 1846 */       this.m_regAuthority = p_other.getRegBasedAuthority();
/* 1847 */       this.m_path = p_other.getPath();
/* 1848 */       this.m_queryString = p_other.getQueryString();
/* 1849 */       this.m_fragment = p_other.getFragment();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void initialize(URI p_base, String p_uriSpec) throws MalformedURIException {
/* 1871 */       String uriSpec = p_uriSpec;
/* 1872 */       int uriSpecLen = (uriSpec != null) ? uriSpec.length() : 0;
/*      */       
/* 1874 */       if (p_base == null && uriSpecLen == 0) {
/* 1875 */         throw new MalformedURIException(
/* 1876 */             "Cannot initialize URI with empty parameters.");
/*      */       }
/*      */ 
/*      */       
/* 1880 */       if (uriSpecLen == 0) {
/* 1881 */         initialize(p_base);
/*      */         
/*      */         return;
/*      */       } 
/* 1885 */       int index = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1891 */       int colonIdx = uriSpec.indexOf(':');
/* 1892 */       if (colonIdx != -1) {
/* 1893 */         int searchFrom = colonIdx - 1;
/*      */         
/* 1895 */         int slashIdx = uriSpec.lastIndexOf('/', searchFrom);
/* 1896 */         int queryIdx = uriSpec.lastIndexOf('?', searchFrom);
/* 1897 */         int fragmentIdx = uriSpec.lastIndexOf('#', searchFrom);
/*      */         
/* 1899 */         if (colonIdx < 2 || slashIdx != -1 || 
/* 1900 */           queryIdx != -1 || fragmentIdx != -1) {
/*      */           
/* 1902 */           if (colonIdx == 0 || (p_base == null && fragmentIdx != 0)) {
/* 1903 */             throw new MalformedURIException("No scheme found in URI.");
/*      */           }
/*      */         } else {
/*      */           
/* 1907 */           initializeScheme(uriSpec);
/* 1908 */           index = this.m_scheme.length() + 1;
/*      */ 
/*      */           
/* 1911 */           if (colonIdx == uriSpecLen - 1 || uriSpec.charAt(colonIdx + 1) == '#') {
/* 1912 */             throw new MalformedURIException("Scheme specific part cannot be empty.");
/*      */           }
/*      */         }
/*      */       
/* 1916 */       } else if (p_base == null && uriSpec.indexOf('#') != 0) {
/* 1917 */         throw new MalformedURIException("No scheme found in URI.");
/*      */       } 
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
/* 1929 */       if (index + 1 < uriSpecLen && 
/* 1930 */         uriSpec.charAt(index) == '/' && uriSpec.charAt(index + 1) == '/') {
/* 1931 */         index += 2;
/* 1932 */         int startPos = index;
/*      */ 
/*      */         
/* 1935 */         char testChar = Character.MIN_VALUE;
/* 1936 */         while (index < uriSpecLen) {
/* 1937 */           testChar = uriSpec.charAt(index);
/* 1938 */           if (testChar == '/' || testChar == '?' || testChar == '#') {
/*      */             break;
/*      */           }
/* 1941 */           index++;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1947 */         if (index > startPos) {
/*      */ 
/*      */           
/* 1950 */           if (!initializeAuthority(uriSpec.substring(startPos, index))) {
/* 1951 */             index = startPos - 2;
/*      */           }
/*      */         } else {
/*      */           
/* 1955 */           this.m_host = "";
/*      */         } 
/*      */       } 
/*      */       
/* 1959 */       initializePath(uriSpec, index);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1966 */       if (p_base != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1975 */         if (this.m_path.length() == 0 && this.m_scheme == null && 
/* 1976 */           this.m_host == null && this.m_regAuthority == null) {
/* 1977 */           this.m_scheme = p_base.getScheme();
/* 1978 */           this.m_userinfo = p_base.getUserinfo();
/* 1979 */           this.m_host = p_base.getHost();
/* 1980 */           this.m_port = p_base.getPort();
/* 1981 */           this.m_regAuthority = p_base.getRegBasedAuthority();
/* 1982 */           this.m_path = p_base.getPath();
/*      */           
/* 1984 */           if (this.m_queryString == null) {
/* 1985 */             this.m_queryString = p_base.getQueryString();
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1992 */         if (this.m_scheme == null) {
/* 1993 */           this.m_scheme = p_base.getScheme();
/*      */         } else {
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2001 */         if (this.m_host == null && this.m_regAuthority == null) {
/* 2002 */           this.m_userinfo = p_base.getUserinfo();
/* 2003 */           this.m_host = p_base.getHost();
/* 2004 */           this.m_port = p_base.getPort();
/* 2005 */           this.m_regAuthority = p_base.getRegBasedAuthority();
/*      */         } else {
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2012 */         if (this.m_path.length() > 0 && 
/* 2013 */           this.m_path.startsWith("/")) {
/*      */           return;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2019 */         String path = "";
/* 2020 */         String basePath = p_base.getPath();
/*      */ 
/*      */         
/* 2023 */         if (basePath != null && basePath.length() > 0) {
/* 2024 */           int lastSlash = basePath.lastIndexOf('/');
/* 2025 */           if (lastSlash != -1) {
/* 2026 */             path = basePath.substring(0, lastSlash + 1);
/*      */           }
/*      */         }
/* 2029 */         else if (this.m_path.length() > 0) {
/* 2030 */           path = "/";
/*      */         } 
/*      */ 
/*      */         
/* 2034 */         path = path.concat(this.m_path);
/*      */ 
/*      */         
/* 2037 */         index = -1;
/* 2038 */         while ((index = path.indexOf("/./")) != -1) {
/* 2039 */           path = path.substring(0, index + 1).concat(path.substring(index + 3));
/*      */         }
/*      */ 
/*      */         
/* 2043 */         if (path.endsWith("/.")) {
/* 2044 */           path = path.substring(0, path.length() - 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2049 */         index = 1;
/* 2050 */         int segIndex = -1;
/* 2051 */         String tempString = null;
/*      */         
/* 2053 */         while ((index = path.indexOf("/../", index)) > 0) {
/* 2054 */           tempString = path.substring(0, path.indexOf("/../"));
/* 2055 */           segIndex = tempString.lastIndexOf('/');
/* 2056 */           if (segIndex != -1) {
/* 2057 */             if (!tempString.substring(segIndex).equals("..")) {
/* 2058 */               path = path.substring(0, segIndex + 1).concat(path.substring(index + 4));
/* 2059 */               index = segIndex;
/*      */               continue;
/*      */             } 
/* 2062 */             index += 4;
/*      */             continue;
/*      */           } 
/* 2065 */           index += 4;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2070 */         if (path.endsWith("/..")) {
/* 2071 */           tempString = path.substring(0, path.length() - 3);
/* 2072 */           segIndex = tempString.lastIndexOf('/');
/* 2073 */           if (segIndex != -1) {
/* 2074 */             path = path.substring(0, segIndex + 1);
/*      */           }
/*      */         } 
/* 2077 */         this.m_path = path;
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
/*      */ 
/*      */     
/*      */     private void initializeScheme(String p_uriSpec) throws MalformedURIException {
/* 2091 */       int uriSpecLen = p_uriSpec.length();
/* 2092 */       int index = 0;
/* 2093 */       String scheme = null;
/* 2094 */       char testChar = Character.MIN_VALUE;
/*      */       
/* 2096 */       while (index < uriSpecLen) {
/* 2097 */         testChar = p_uriSpec.charAt(index);
/* 2098 */         if (testChar == ':' || testChar == '/' || 
/* 2099 */           testChar == '?' || testChar == '#') {
/*      */           break;
/*      */         }
/* 2102 */         index++;
/*      */       } 
/* 2104 */       scheme = p_uriSpec.substring(0, index);
/*      */       
/* 2106 */       if (scheme.length() == 0) {
/* 2107 */         throw new MalformedURIException("No scheme found in URI.");
/*      */       }
/*      */       
/* 2110 */       setScheme(scheme);
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
/*      */ 
/*      */     
/*      */     private boolean initializeAuthority(String p_uriSpec) {
/* 2125 */       int index = 0;
/* 2126 */       int start = 0;
/* 2127 */       int end = p_uriSpec.length();
/*      */       
/* 2129 */       char testChar = Character.MIN_VALUE;
/* 2130 */       String userinfo = null;
/*      */ 
/*      */       
/* 2133 */       if (p_uriSpec.indexOf('@', start) != -1) {
/* 2134 */         while (index < end) {
/* 2135 */           testChar = p_uriSpec.charAt(index);
/* 2136 */           if (testChar == '@') {
/*      */             break;
/*      */           }
/* 2139 */           index++;
/*      */         } 
/* 2141 */         userinfo = p_uriSpec.substring(start, index);
/* 2142 */         index++;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2147 */       String host = null;
/* 2148 */       start = index;
/* 2149 */       boolean hasPort = false;
/* 2150 */       if (index < end) {
/* 2151 */         if (p_uriSpec.charAt(start) == '[') {
/* 2152 */           int bracketIndex = p_uriSpec.indexOf(']', start);
/* 2153 */           index = (bracketIndex != -1) ? bracketIndex : end;
/* 2154 */           if (index + 1 < end && p_uriSpec.charAt(index + 1) == ':') {
/* 2155 */             index++;
/* 2156 */             hasPort = true;
/*      */           } else {
/*      */             
/* 2159 */             index = end;
/*      */           } 
/*      */         } else {
/*      */           
/* 2163 */           int colonIndex = p_uriSpec.lastIndexOf(':', end);
/* 2164 */           index = (colonIndex > start) ? colonIndex : end;
/* 2165 */           hasPort = (index != end);
/*      */         } 
/*      */       }
/* 2168 */       host = p_uriSpec.substring(start, index);
/* 2169 */       int port = -1;
/* 2170 */       if (host.length() > 0)
/*      */       {
/* 2172 */         if (hasPort) {
/*      */           
/* 2174 */           start = ++index;
/* 2175 */           while (index < end) {
/* 2176 */             index++;
/*      */           }
/* 2178 */           String portStr = p_uriSpec.substring(start, index);
/* 2179 */           if (portStr.length() > 0) {
/*      */             
/*      */             try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2191 */               port = Integer.parseInt(portStr);
/* 2192 */               if (port == -1) port--;
/*      */             
/* 2194 */             } catch (NumberFormatException nfe) {
/* 2195 */               port = -2;
/*      */             } 
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 2201 */       if (isValidServerBasedAuthority(host, port, userinfo)) {
/* 2202 */         this.m_host = host;
/* 2203 */         this.m_port = port;
/* 2204 */         this.m_userinfo = userinfo;
/* 2205 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2211 */       if (isValidRegistryBasedAuthority(p_uriSpec)) {
/* 2212 */         this.m_regAuthority = p_uriSpec;
/* 2213 */         return true;
/*      */       } 
/* 2215 */       return false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isValidServerBasedAuthority(String host, int port, String userinfo) {
/* 2232 */       if (!isWellFormedAddress(host)) {
/* 2233 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2240 */       if (port < -1 || port > 65535) {
/* 2241 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2245 */       if (userinfo != null) {
/*      */ 
/*      */         
/* 2248 */         int index = 0;
/* 2249 */         int end = userinfo.length();
/* 2250 */         char testChar = Character.MIN_VALUE;
/* 2251 */         while (index < end) {
/* 2252 */           testChar = userinfo.charAt(index);
/* 2253 */           if (testChar == '%') {
/* 2254 */             if (index + 2 >= end || 
/* 2255 */               !isHex(userinfo.charAt(index + 1)) || 
/* 2256 */               !isHex(userinfo.charAt(index + 2))) {
/* 2257 */               return false;
/*      */             }
/* 2259 */             index += 2;
/*      */           }
/* 2261 */           else if (!isUserinfoCharacter(testChar)) {
/* 2262 */             return false;
/*      */           } 
/* 2264 */           index++;
/*      */         } 
/*      */       } 
/* 2267 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isValidRegistryBasedAuthority(String authority) {
/* 2278 */       int index = 0;
/* 2279 */       int end = authority.length();
/*      */ 
/*      */       
/* 2282 */       while (index < end) {
/* 2283 */         char testChar = authority.charAt(index);
/*      */ 
/*      */         
/* 2286 */         if (testChar == '%') {
/* 2287 */           if (index + 2 >= end || 
/* 2288 */             !isHex(authority.charAt(index + 1)) || 
/* 2289 */             !isHex(authority.charAt(index + 2))) {
/* 2290 */             return false;
/*      */           }
/* 2292 */           index += 2;
/*      */ 
/*      */         
/*      */         }
/* 2296 */         else if (!isPathCharacter(testChar)) {
/* 2297 */           return false;
/*      */         } 
/* 2299 */         index++;
/*      */       } 
/* 2301 */       return true;
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
/*      */     private void initializePath(String p_uriSpec, int p_nStartIndex) throws MalformedURIException {
/* 2314 */       if (p_uriSpec == null) {
/* 2315 */         throw new MalformedURIException(
/* 2316 */             "Cannot initialize path from null string!");
/*      */       }
/*      */       
/* 2319 */       int index = p_nStartIndex;
/* 2320 */       int start = p_nStartIndex;
/* 2321 */       int end = p_uriSpec.length();
/* 2322 */       char testChar = Character.MIN_VALUE;
/*      */ 
/*      */       
/* 2325 */       if (start < end)
/*      */       {
/* 2327 */         if (getScheme() == null || p_uriSpec.charAt(start) == '/') {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2332 */           while (index < end) {
/* 2333 */             testChar = p_uriSpec.charAt(index);
/*      */ 
/*      */             
/* 2336 */             if (testChar == '%') {
/* 2337 */               if (index + 2 >= end || 
/* 2338 */                 !isHex(p_uriSpec.charAt(index + 1)) || 
/* 2339 */                 !isHex(p_uriSpec.charAt(index + 2))) {
/* 2340 */                 throw new MalformedURIException(
/* 2341 */                     "Path contains invalid escape sequence!");
/*      */               }
/* 2343 */               index += 2;
/*      */ 
/*      */             
/*      */             }
/* 2347 */             else if (!isPathCharacter(testChar)) {
/* 2348 */               if (testChar == '?' || testChar == '#') {
/*      */                 break;
/*      */               }
/* 2351 */               throw new MalformedURIException(
/* 2352 */                   "Path contains invalid character: " + testChar);
/*      */             } 
/* 2354 */             index++;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 2361 */           while (index < end) {
/* 2362 */             testChar = p_uriSpec.charAt(index);
/*      */             
/* 2364 */             if (testChar == '?' || testChar == '#') {
/*      */               break;
/*      */             }
/*      */ 
/*      */             
/* 2369 */             if (testChar == '%') {
/* 2370 */               if (index + 2 >= end || 
/* 2371 */                 !isHex(p_uriSpec.charAt(index + 1)) || 
/* 2372 */                 !isHex(p_uriSpec.charAt(index + 2))) {
/* 2373 */                 throw new MalformedURIException(
/* 2374 */                     "Opaque part contains invalid escape sequence!");
/*      */               }
/* 2376 */               index += 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             }
/* 2383 */             else if (!isURICharacter(testChar)) {
/* 2384 */               throw new MalformedURIException(
/* 2385 */                   "Opaque part contains invalid character: " + testChar);
/*      */             } 
/* 2387 */             index++;
/*      */           } 
/*      */         } 
/*      */       }
/* 2391 */       this.m_path = p_uriSpec.substring(start, index);
/*      */ 
/*      */       
/* 2394 */       if (testChar == '?') {
/*      */         
/* 2396 */         start = ++index;
/* 2397 */         while (index < end) {
/* 2398 */           testChar = p_uriSpec.charAt(index);
/* 2399 */           if (testChar == '#') {
/*      */             break;
/*      */           }
/* 2402 */           if (testChar == '%') {
/* 2403 */             if (index + 2 >= end || 
/* 2404 */               !isHex(p_uriSpec.charAt(index + 1)) || 
/* 2405 */               !isHex(p_uriSpec.charAt(index + 2))) {
/* 2406 */               throw new MalformedURIException(
/* 2407 */                   "Query string contains invalid escape sequence!");
/*      */             }
/* 2409 */             index += 2;
/*      */           }
/* 2411 */           else if (!isURICharacter(testChar)) {
/* 2412 */             throw new MalformedURIException(
/* 2413 */                 "Query string contains invalid character: " + testChar);
/*      */           } 
/* 2415 */           index++;
/*      */         } 
/* 2417 */         this.m_queryString = p_uriSpec.substring(start, index);
/*      */       } 
/*      */ 
/*      */       
/* 2421 */       if (testChar == '#') {
/*      */         
/* 2423 */         start = ++index;
/* 2424 */         while (index < end) {
/* 2425 */           testChar = p_uriSpec.charAt(index);
/*      */           
/* 2427 */           if (testChar == '%') {
/* 2428 */             if (index + 2 >= end || 
/* 2429 */               !isHex(p_uriSpec.charAt(index + 1)) || 
/* 2430 */               !isHex(p_uriSpec.charAt(index + 2))) {
/* 2431 */               throw new MalformedURIException(
/* 2432 */                   "Fragment contains invalid escape sequence!");
/*      */             }
/* 2434 */             index += 2;
/*      */           }
/* 2436 */           else if (!isURICharacter(testChar)) {
/* 2437 */             throw new MalformedURIException(
/* 2438 */                 "Fragment contains invalid character: " + testChar);
/*      */           } 
/* 2440 */           index++;
/*      */         } 
/* 2442 */         this.m_fragment = p_uriSpec.substring(start, index);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getScheme() {
/* 2452 */       return this.m_scheme;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getSchemeSpecificPart() {
/* 2462 */       StringBuffer schemespec = new StringBuffer();
/*      */       
/* 2464 */       if (this.m_host != null || this.m_regAuthority != null) {
/* 2465 */         schemespec.append("//");
/*      */ 
/*      */         
/* 2468 */         if (this.m_host != null) {
/*      */           
/* 2470 */           if (this.m_userinfo != null) {
/* 2471 */             schemespec.append(this.m_userinfo);
/* 2472 */             schemespec.append('@');
/*      */           } 
/*      */           
/* 2475 */           schemespec.append(this.m_host);
/*      */           
/* 2477 */           if (this.m_port != -1) {
/* 2478 */             schemespec.append(':');
/* 2479 */             schemespec.append(this.m_port);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2484 */           schemespec.append(this.m_regAuthority);
/*      */         } 
/*      */       } 
/*      */       
/* 2488 */       if (this.m_path != null) {
/* 2489 */         schemespec.append(this.m_path);
/*      */       }
/*      */       
/* 2492 */       if (this.m_queryString != null) {
/* 2493 */         schemespec.append('?');
/* 2494 */         schemespec.append(this.m_queryString);
/*      */       } 
/*      */       
/* 2497 */       if (this.m_fragment != null) {
/* 2498 */         schemespec.append('#');
/* 2499 */         schemespec.append(this.m_fragment);
/*      */       } 
/*      */       
/* 2502 */       return schemespec.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getUserinfo() {
/* 2511 */       return this.m_userinfo;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getHost() {
/* 2520 */       return this.m_host;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getPort() {
/* 2529 */       return this.m_port;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getRegBasedAuthority() {
/* 2538 */       return this.m_regAuthority;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPath(boolean p_includeQueryString, boolean p_includeFragment) {
/* 2557 */       StringBuffer pathString = new StringBuffer(this.m_path);
/*      */       
/* 2559 */       if (p_includeQueryString && this.m_queryString != null) {
/* 2560 */         pathString.append('?');
/* 2561 */         pathString.append(this.m_queryString);
/*      */       } 
/*      */       
/* 2564 */       if (p_includeFragment && this.m_fragment != null) {
/* 2565 */         pathString.append('#');
/* 2566 */         pathString.append(this.m_fragment);
/*      */       } 
/* 2568 */       return pathString.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPath() {
/* 2578 */       return this.m_path;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getQueryString() {
/* 2589 */       return this.m_queryString;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFragment() {
/* 2600 */       return this.m_fragment;
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
/*      */     public void setScheme(String p_scheme) throws MalformedURIException {
/* 2613 */       if (p_scheme == null) {
/* 2614 */         throw new MalformedURIException(
/* 2615 */             "Cannot set scheme from null string!");
/*      */       }
/* 2617 */       if (!isConformantSchemeName(p_scheme)) {
/* 2618 */         throw new MalformedURIException("The scheme is not conformant.");
/*      */       }
/*      */       
/* 2621 */       this.m_scheme = p_scheme.toLowerCase();
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
/*      */     public void setUserinfo(String p_userinfo) throws MalformedURIException {
/* 2634 */       if (p_userinfo == null) {
/* 2635 */         this.m_userinfo = null;
/*      */         
/*      */         return;
/*      */       } 
/* 2639 */       if (this.m_host == null) {
/* 2640 */         throw new MalformedURIException(
/* 2641 */             "Userinfo cannot be set when host is null!");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2646 */       int index = 0;
/* 2647 */       int end = p_userinfo.length();
/* 2648 */       char testChar = Character.MIN_VALUE;
/* 2649 */       while (index < end) {
/* 2650 */         testChar = p_userinfo.charAt(index);
/* 2651 */         if (testChar == '%') {
/* 2652 */           if (index + 2 >= end || 
/* 2653 */             !isHex(p_userinfo.charAt(index + 1)) || 
/* 2654 */             !isHex(p_userinfo.charAt(index + 2))) {
/* 2655 */             throw new MalformedURIException(
/* 2656 */                 "Userinfo contains invalid escape sequence!");
/*      */           }
/*      */         }
/* 2659 */         else if (!isUserinfoCharacter(testChar)) {
/* 2660 */           throw new MalformedURIException(
/* 2661 */               "Userinfo contains invalid character:" + testChar);
/*      */         } 
/* 2663 */         index++;
/*      */       } 
/*      */       
/* 2666 */       this.m_userinfo = p_userinfo;
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
/*      */ 
/*      */ 
/*      */     
/*      */     public void setHost(String p_host) throws MalformedURIException {
/* 2682 */       if (p_host == null || p_host.length() == 0) {
/* 2683 */         if (p_host != null) {
/* 2684 */           this.m_regAuthority = null;
/*      */         }
/* 2686 */         this.m_host = p_host;
/* 2687 */         this.m_userinfo = null;
/* 2688 */         this.m_port = -1;
/*      */         return;
/*      */       } 
/* 2691 */       if (!isWellFormedAddress(p_host)) {
/* 2692 */         throw new MalformedURIException("Host is not a well formed address!");
/*      */       }
/* 2694 */       this.m_host = p_host;
/* 2695 */       this.m_regAuthority = null;
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
/*      */ 
/*      */     
/*      */     public void setPort(int p_port) throws MalformedURIException {
/* 2710 */       if (p_port >= 0 && p_port <= 65535) {
/* 2711 */         if (this.m_host == null) {
/* 2712 */           throw new MalformedURIException(
/* 2713 */               "Port cannot be set when host is null!");
/*      */         }
/*      */       }
/* 2716 */       else if (p_port != -1) {
/* 2717 */         throw new MalformedURIException("Invalid port number!");
/*      */       } 
/* 2719 */       this.m_port = p_port;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setRegBasedAuthority(String authority) throws MalformedURIException {
/* 2736 */       if (authority == null) {
/* 2737 */         this.m_regAuthority = null;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2742 */       if (authority.length() < 1 || 
/* 2743 */         !isValidRegistryBasedAuthority(authority) || 
/* 2744 */         authority.indexOf('/') != -1) {
/* 2745 */         throw new MalformedURIException("Registry based authority is not well formed.");
/*      */       }
/* 2747 */       this.m_regAuthority = authority;
/* 2748 */       this.m_host = null;
/* 2749 */       this.m_userinfo = null;
/* 2750 */       this.m_port = -1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setPath(String p_path) throws MalformedURIException {
/* 2768 */       if (p_path == null) {
/* 2769 */         this.m_path = null;
/* 2770 */         this.m_queryString = null;
/* 2771 */         this.m_fragment = null;
/*      */       } else {
/*      */         
/* 2774 */         initializePath(p_path, 0);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void appendPath(String p_addToPath) throws MalformedURIException {
/* 2793 */       if (p_addToPath == null || p_addToPath.trim().length() == 0) {
/*      */         return;
/*      */       }
/*      */       
/* 2797 */       if (!isURIString(p_addToPath)) {
/* 2798 */         throw new MalformedURIException(
/* 2799 */             "Path contains invalid character!");
/*      */       }
/*      */       
/* 2802 */       if (this.m_path == null || this.m_path.trim().length() == 0) {
/* 2803 */         if (p_addToPath.startsWith("/")) {
/* 2804 */           this.m_path = p_addToPath;
/*      */         } else {
/*      */           
/* 2807 */           this.m_path = "/" + p_addToPath;
/*      */         }
/*      */       
/* 2810 */       } else if (this.m_path.endsWith("/")) {
/* 2811 */         if (p_addToPath.startsWith("/")) {
/* 2812 */           this.m_path = this.m_path.concat(p_addToPath.substring(1));
/*      */         } else {
/*      */           
/* 2815 */           this.m_path = this.m_path.concat(p_addToPath);
/*      */         }
/*      */       
/*      */       }
/* 2819 */       else if (p_addToPath.startsWith("/")) {
/* 2820 */         this.m_path = this.m_path.concat(p_addToPath);
/*      */       } else {
/*      */         
/* 2823 */         this.m_path = this.m_path.concat("/" + p_addToPath);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setQueryString(String p_queryString) throws MalformedURIException {
/* 2840 */       if (p_queryString == null) {
/* 2841 */         this.m_queryString = null;
/*      */       } else {
/* 2843 */         if (!isGenericURI()) {
/* 2844 */           throw new MalformedURIException(
/* 2845 */               "Query string can only be set for a generic URI!");
/*      */         }
/* 2847 */         if (getPath() == null) {
/* 2848 */           throw new MalformedURIException(
/* 2849 */               "Query string cannot be set when path is null!");
/*      */         }
/* 2851 */         if (!isURIString(p_queryString)) {
/* 2852 */           throw new MalformedURIException(
/* 2853 */               "Query string contains invalid character!");
/*      */         }
/*      */         
/* 2856 */         this.m_queryString = p_queryString;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFragment(String p_fragment) throws MalformedURIException {
/* 2872 */       if (p_fragment == null) {
/* 2873 */         this.m_fragment = null;
/*      */       } else {
/* 2875 */         if (!isGenericURI()) {
/* 2876 */           throw new MalformedURIException(
/* 2877 */               "Fragment can only be set for a generic URI!");
/*      */         }
/* 2879 */         if (getPath() == null) {
/* 2880 */           throw new MalformedURIException(
/* 2881 */               "Fragment cannot be set when path is null!");
/*      */         }
/* 2883 */         if (!isURIString(p_fragment)) {
/* 2884 */           throw new MalformedURIException(
/* 2885 */               "Fragment contains invalid character!");
/*      */         }
/*      */         
/* 2888 */         this.m_fragment = p_fragment;
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
/*      */ 
/*      */     
/*      */     public boolean equals(Object p_test) {
/* 2902 */       if (p_test instanceof URI) {
/* 2903 */         URI testURI = (URI)p_test;
/* 2904 */         if (((this.m_scheme == null && testURI.m_scheme == null) || (
/* 2905 */           this.m_scheme != null && testURI.m_scheme != null && 
/* 2906 */           this.m_scheme.equals(testURI.m_scheme))) && ((
/* 2907 */           this.m_userinfo == null && testURI.m_userinfo == null) || (
/* 2908 */           this.m_userinfo != null && testURI.m_userinfo != null && 
/* 2909 */           this.m_userinfo.equals(testURI.m_userinfo))) && ((
/* 2910 */           this.m_host == null && testURI.m_host == null) || (
/* 2911 */           this.m_host != null && testURI.m_host != null && 
/* 2912 */           this.m_host.equals(testURI.m_host))) && 
/* 2913 */           this.m_port == testURI.m_port && ((
/* 2914 */           this.m_path == null && testURI.m_path == null) || (
/* 2915 */           this.m_path != null && testURI.m_path != null && 
/* 2916 */           this.m_path.equals(testURI.m_path))) && ((
/* 2917 */           this.m_queryString == null && testURI.m_queryString == null) || (
/* 2918 */           this.m_queryString != null && testURI.m_queryString != null && 
/* 2919 */           this.m_queryString.equals(testURI.m_queryString))) && ((
/* 2920 */           this.m_fragment == null && testURI.m_fragment == null) || (
/* 2921 */           this.m_fragment != null && testURI.m_fragment != null && 
/* 2922 */           this.m_fragment.equals(testURI.m_fragment)))) {
/* 2923 */           return true;
/*      */         }
/*      */       } 
/* 2926 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 2931 */       int prime = 31;
/* 2932 */       int result = 1;
/* 2933 */       result = 31 * result + ((this.m_fragment == null) ? 0 : this.m_fragment.hashCode());
/* 2934 */       result = 31 * result + ((this.m_host == null) ? 0 : this.m_host.hashCode());
/* 2935 */       result = 31 * result + ((this.m_path == null) ? 0 : this.m_path.hashCode());
/* 2936 */       result = 31 * result + this.m_port;
/* 2937 */       result = 31 * result + ((this.m_queryString == null) ? 0 : this.m_queryString.hashCode());
/* 2938 */       result = 31 * result + ((this.m_scheme == null) ? 0 : this.m_scheme.hashCode());
/* 2939 */       result = 31 * result + ((this.m_userinfo == null) ? 0 : this.m_userinfo.hashCode());
/* 2940 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2950 */       StringBuffer uriSpecString = new StringBuffer();
/*      */       
/* 2952 */       if (this.m_scheme != null) {
/* 2953 */         uriSpecString.append(this.m_scheme);
/* 2954 */         uriSpecString.append(':');
/*      */       } 
/* 2956 */       uriSpecString.append(getSchemeSpecificPart());
/* 2957 */       return uriSpecString.toString();
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
/*      */     public boolean isGenericURI() {
/* 2970 */       return (this.m_host != null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isConformantSchemeName(String p_scheme) {
/* 2981 */       if (p_scheme == null || p_scheme.trim().length() == 0) {
/* 2982 */         return false;
/*      */       }
/*      */       
/* 2985 */       if (!isAlpha(p_scheme.charAt(0))) {
/* 2986 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2990 */       int schemeLength = p_scheme.length();
/* 2991 */       for (int i = 1; i < schemeLength; i++) {
/* 2992 */         char testChar = p_scheme.charAt(i);
/* 2993 */         if (!isSchemeCharacter(testChar)) {
/* 2994 */           return false;
/*      */         }
/*      */       } 
/*      */       
/* 2998 */       return true;
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
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isWellFormedAddress(String address) {
/* 3014 */       if (address == null) {
/* 3015 */         return false;
/*      */       }
/*      */       
/* 3018 */       int addrLength = address.length();
/* 3019 */       if (addrLength == 0) {
/* 3020 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 3024 */       if (address.startsWith("[")) {
/* 3025 */         return isWellFormedIPv6Reference(address);
/*      */       }
/*      */ 
/*      */       
/* 3029 */       if (address.startsWith(".") || 
/* 3030 */         address.startsWith("-") || 
/* 3031 */         address.endsWith("-")) {
/* 3032 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3038 */       int index = address.lastIndexOf('.');
/* 3039 */       if (address.endsWith(".")) {
/* 3040 */         index = address.substring(0, index).lastIndexOf('.');
/*      */       }
/*      */       
/* 3043 */       if (index + 1 < addrLength && isDigit(address.charAt(index + 1))) {
/* 3044 */         return isWellFormedIPv4Address(address);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3054 */       if (addrLength > 255) {
/* 3055 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3061 */       int labelCharCount = 0;
/*      */       
/* 3063 */       for (int i = 0; i < addrLength; i++) {
/* 3064 */         char testChar = address.charAt(i);
/* 3065 */         if (testChar == '.') {
/* 3066 */           if (!isAlphanum(address.charAt(i - 1))) {
/* 3067 */             return false;
/*      */           }
/* 3069 */           if (i + 1 < addrLength && !isAlphanum(address.charAt(i + 1))) {
/* 3070 */             return false;
/*      */           }
/* 3072 */           labelCharCount = 0;
/*      */         } else {
/* 3074 */           if (!isAlphanum(testChar) && testChar != '-') {
/* 3075 */             return false;
/*      */           }
/*      */           
/* 3078 */           if (++labelCharCount > 63) {
/* 3079 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/* 3083 */       return true;
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
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isWellFormedIPv4Address(String address) {
/* 3099 */       int addrLength = address.length();
/*      */       
/* 3101 */       int numDots = 0;
/* 3102 */       int numDigits = 0;
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
/* 3114 */       for (int i = 0; i < addrLength; i++) {
/* 3115 */         char testChar = address.charAt(i);
/* 3116 */         if (testChar == '.') {
/* 3117 */           if ((i > 0 && !isDigit(address.charAt(i - 1))) || (
/* 3118 */             i + 1 < addrLength && !isDigit(address.charAt(i + 1)))) {
/* 3119 */             return false;
/*      */           }
/* 3121 */           numDigits = 0;
/* 3122 */           if (++numDots > 3) {
/* 3123 */             return false;
/*      */           }
/*      */         } else {
/* 3126 */           if (!isDigit(testChar)) {
/* 3127 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 3131 */           if (++numDigits > 3) {
/* 3132 */             return false;
/*      */           }
/*      */           
/* 3135 */           if (numDigits == 3) {
/* 3136 */             char first = address.charAt(i - 2);
/* 3137 */             char second = address.charAt(i - 1);
/* 3138 */             if (first >= '2' && (
/* 3139 */               first != '2' || (
/* 3140 */               second >= '5' && (
/* 3141 */               second != '5' || testChar > '5'))))
/* 3142 */               return false; 
/*      */           } 
/*      */         } 
/*      */       } 
/* 3146 */       return (numDots == 3);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isWellFormedIPv6Reference(String address) {
/* 3166 */       int addrLength = address.length();
/* 3167 */       int index = 1;
/* 3168 */       int end = addrLength - 1;
/*      */ 
/*      */       
/* 3171 */       if (addrLength <= 2 || address.charAt(0) != '[' || 
/* 3172 */         address.charAt(end) != ']') {
/* 3173 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 3177 */       int[] counter = new int[1];
/*      */ 
/*      */       
/* 3180 */       index = scanHexSequence(address, index, end, counter);
/* 3181 */       if (index == -1) {
/* 3182 */         return false;
/*      */       }
/*      */       
/* 3185 */       if (index == end) {
/* 3186 */         return (counter[0] == 8);
/*      */       }
/*      */       
/* 3189 */       if (index + 1 < end && address.charAt(index) == ':') {
/* 3190 */         if (address.charAt(index + 1) == ':') {
/*      */           
/* 3192 */           counter[0] = counter[0] + 1; if (counter[0] + 1 > 8) {
/* 3193 */             return false;
/*      */           }
/* 3195 */           index += 2;
/*      */           
/* 3197 */           if (index == end) {
/* 3198 */             return true;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 3205 */           if (counter[0] == 6 && 
/* 3206 */             isWellFormedIPv4Address(address.substring(index + 1, end))) return true; 
/*      */           return false;
/*      */         } 
/*      */       } else {
/* 3210 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 3214 */       int prevCount = counter[0];
/* 3215 */       index = scanHexSequence(address, index, end, counter);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3220 */       if (index != end) {
/* 3221 */         if (index != -1) { if (!isWellFormedIPv4Address(
/* 3222 */               address.substring((counter[0] > prevCount) ? (index + 1) : index, end))) {
/*      */             return false;
/*      */           } }
/*      */         else
/*      */         { return false; }
/*      */       
/*      */       }
/*      */       return true;
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
/*      */     
/*      */     private static int scanHexSequence(String address, int index, int end, int[] counter) {
/* 3243 */       int numDigits = 0;
/* 3244 */       int start = index;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3249 */       for (; index < end; index++) {
/* 3250 */         char testChar = address.charAt(index);
/* 3251 */         if (testChar == ':') {
/*      */           
/* 3253 */           counter[0] = counter[0] + 1; if (numDigits > 0 && counter[0] + 1 > 8) {
/* 3254 */             return -1;
/*      */           }
/*      */           
/* 3257 */           if (numDigits == 0 || (index + 1 < end && address.charAt(index + 1) == ':')) {
/* 3258 */             return index;
/*      */           }
/* 3260 */           numDigits = 0;
/*      */         }
/*      */         else {
/*      */           
/* 3264 */           if (!isHex(testChar)) {
/* 3265 */             if (testChar == '.' && numDigits < 4 && numDigits > 0 && counter[0] <= 6) {
/* 3266 */               int back = index - numDigits - 1;
/* 3267 */               return (back >= start) ? back : (back + 1);
/*      */             } 
/* 3269 */             return -1;
/*      */           } 
/*      */           
/* 3272 */           if (++numDigits > 4)
/* 3273 */             return -1; 
/*      */         } 
/*      */       } 
/* 3276 */       counter[0] = counter[0] + 1; return (numDigits > 0 && counter[0] + 1 <= 8) ? end : -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isDigit(char p_char) {
/* 3286 */       return (p_char >= '0' && p_char <= '9');
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isHex(char p_char) {
/* 3296 */       return (p_char <= 'f' && (fgLookupTable[p_char] & 0x40) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isAlpha(char p_char) {
/* 3305 */       return !((p_char < 'a' || p_char > 'z') && (p_char < 'A' || p_char > 'Z'));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isAlphanum(char p_char) {
/* 3314 */       return (p_char <= 'z' && (fgLookupTable[p_char] & 0x30) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isURICharacter(char p_char) {
/* 3324 */       return (p_char <= '~' && (fgLookupTable[p_char] & 0x33) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isSchemeCharacter(char p_char) {
/* 3333 */       return (p_char <= 'z' && (fgLookupTable[p_char] & 0x34) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isUserinfoCharacter(char p_char) {
/* 3342 */       return (p_char <= 'z' && (fgLookupTable[p_char] & 0x3A) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static boolean isPathCharacter(char p_char) {
/* 3351 */       return (p_char <= '~' && (fgLookupTable[p_char] & 0xB2) != 0);
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
/*      */     private static boolean isURIString(String p_uric) {
/* 3363 */       if (p_uric == null) {
/* 3364 */         return false;
/*      */       }
/* 3366 */       int end = p_uric.length();
/* 3367 */       char testChar = Character.MIN_VALUE;
/* 3368 */       for (int i = 0; i < end; i++) {
/* 3369 */         testChar = p_uric.charAt(i);
/* 3370 */         if (testChar == '%') {
/* 3371 */           if (i + 2 >= end || 
/* 3372 */             !isHex(p_uric.charAt(i + 1)) || 
/* 3373 */             !isHex(p_uric.charAt(i + 2))) {
/* 3374 */             return false;
/*      */           }
/*      */           
/* 3377 */           i += 2;
/*      */ 
/*      */         
/*      */         }
/* 3381 */         else if (!isURICharacter(testChar)) {
/*      */ 
/*      */ 
/*      */           
/* 3385 */           return false;
/*      */         } 
/*      */       } 
/* 3388 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3395 */     private static boolean[] gNeedEscaping = new boolean[128];
/*      */     
/* 3397 */     private static char[] gAfterEscaping1 = new char[128];
/*      */     
/* 3399 */     private static char[] gAfterEscaping2 = new char[128];
/* 3400 */     private static char[] gHexChs = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', 
/* 3401 */         '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*      */     
/*      */     static {
/* 3404 */       for (int i = 0; i <= 31; i++) {
/* 3405 */         gNeedEscaping[i] = true;
/* 3406 */         gAfterEscaping1[i] = gHexChs[i >> 4];
/* 3407 */         gAfterEscaping2[i] = gHexChs[i & 0xF];
/*      */       } 
/* 3409 */       gNeedEscaping[127] = true;
/* 3410 */       gAfterEscaping1[127] = '7';
/* 3411 */       gAfterEscaping2[127] = 'F';
/* 3412 */       char[] escChs = { ' ', '<', '>', '"', '{', '}', 
/* 3413 */           '|', '\\', '^', '~', '`' };
/* 3414 */       int len = escChs.length;
/*      */       
/* 3416 */       for (int k = 0; k < len; k++) {
/* 3417 */         char ch = escChs[k];
/* 3418 */         gNeedEscaping[ch] = true;
/* 3419 */         gAfterEscaping1[ch] = gHexChs[ch >> 4];
/* 3420 */         gAfterEscaping2[ch] = gHexChs[ch & 0xF];
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String encode(String anyURI) {
/* 3428 */       int len = anyURI.length();
/* 3429 */       StringBuffer buffer = new StringBuffer(len * 3);
/*      */ 
/*      */       
/* 3432 */       int i = 0;
/* 3433 */       for (; i < len; i++) {
/* 3434 */         int ch = anyURI.charAt(i);
/*      */         
/* 3436 */         if (ch >= 128)
/*      */           break; 
/* 3438 */         if (gNeedEscaping[ch]) {
/* 3439 */           buffer.append('%');
/* 3440 */           buffer.append(gAfterEscaping1[ch]);
/* 3441 */           buffer.append(gAfterEscaping2[ch]);
/*      */         } else {
/*      */           
/* 3444 */           buffer.append((char)ch);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3449 */       if (i < len) {
/*      */         
/* 3451 */         byte[] bytes = (byte[])null;
/*      */         
/*      */         try {
/* 3454 */           bytes = anyURI.substring(i).getBytes("UTF-8");
/* 3455 */         } catch (UnsupportedEncodingException e) {
/*      */           
/* 3457 */           return anyURI;
/*      */         } 
/* 3459 */         len = bytes.length;
/*      */ 
/*      */         
/* 3462 */         for (i = 0; i < len; i++) {
/* 3463 */           byte b = bytes[i];
/*      */           
/* 3465 */           if (b < 0) {
/* 3466 */             int ch = b + 256;
/* 3467 */             buffer.append('%');
/* 3468 */             buffer.append(gHexChs[ch >> 4]);
/* 3469 */             buffer.append(gHexChs[ch & 0xF]);
/*      */           }
/* 3471 */           else if (gNeedEscaping[b]) {
/* 3472 */             buffer.append('%');
/* 3473 */             buffer.append(gAfterEscaping1[b]);
/* 3474 */             buffer.append(gAfterEscaping2[b]);
/*      */           } else {
/*      */             
/* 3477 */             buffer.append((char)b);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3484 */       if (buffer.length() != len) {
/* 3485 */         return buffer.toString();
/*      */       }
/* 3487 */       return anyURI;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public URI() {}
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
/*      */   public static final class XMLChar
/*      */   {
/* 3523 */     private static final byte[] CHARS = new byte[65536];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_VALID = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_SPACE = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_NAME_START = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_NAME = 8;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_PUBID = 16;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_CONTENT = 32;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_NCNAME_START = 64;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int MASK_NCNAME = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/* 3565 */       CHARS[9] = 35;
/* 3566 */       CHARS[10] = 19;
/* 3567 */       CHARS[13] = 19;
/* 3568 */       CHARS[32] = 51;
/* 3569 */       CHARS[33] = 49;
/* 3570 */       CHARS[34] = 33;
/* 3571 */       Arrays.fill(CHARS, 35, 38, (byte)49);
/* 3572 */       CHARS[38] = 1;
/* 3573 */       Arrays.fill(CHARS, 39, 45, (byte)49);
/* 3574 */       Arrays.fill(CHARS, 45, 47, (byte)-71);
/* 3575 */       CHARS[47] = 49;
/* 3576 */       Arrays.fill(CHARS, 48, 58, (byte)-71);
/* 3577 */       CHARS[58] = 61;
/* 3578 */       CHARS[59] = 49;
/* 3579 */       CHARS[60] = 1;
/* 3580 */       CHARS[61] = 49;
/* 3581 */       CHARS[62] = 33;
/* 3582 */       Arrays.fill(CHARS, 63, 65, (byte)49);
/* 3583 */       Arrays.fill(CHARS, 65, 91, (byte)-3);
/* 3584 */       Arrays.fill(CHARS, 91, 93, (byte)33);
/* 3585 */       CHARS[93] = 1;
/* 3586 */       CHARS[94] = 33;
/* 3587 */       CHARS[95] = -3;
/* 3588 */       CHARS[96] = 33;
/* 3589 */       Arrays.fill(CHARS, 97, 123, (byte)-3);
/* 3590 */       Arrays.fill(CHARS, 123, 183, (byte)33);
/* 3591 */       CHARS[183] = -87;
/* 3592 */       Arrays.fill(CHARS, 184, 192, (byte)33);
/* 3593 */       Arrays.fill(CHARS, 192, 215, (byte)-19);
/* 3594 */       CHARS[215] = 33;
/* 3595 */       Arrays.fill(CHARS, 216, 247, (byte)-19);
/* 3596 */       CHARS[247] = 33;
/* 3597 */       Arrays.fill(CHARS, 248, 306, (byte)-19);
/* 3598 */       Arrays.fill(CHARS, 306, 308, (byte)33);
/* 3599 */       Arrays.fill(CHARS, 308, 319, (byte)-19);
/* 3600 */       Arrays.fill(CHARS, 319, 321, (byte)33);
/* 3601 */       Arrays.fill(CHARS, 321, 329, (byte)-19);
/* 3602 */       CHARS[329] = 33;
/* 3603 */       Arrays.fill(CHARS, 330, 383, (byte)-19);
/* 3604 */       CHARS[383] = 33;
/* 3605 */       Arrays.fill(CHARS, 384, 452, (byte)-19);
/* 3606 */       Arrays.fill(CHARS, 452, 461, (byte)33);
/* 3607 */       Arrays.fill(CHARS, 461, 497, (byte)-19);
/* 3608 */       Arrays.fill(CHARS, 497, 500, (byte)33);
/* 3609 */       Arrays.fill(CHARS, 500, 502, (byte)-19);
/* 3610 */       Arrays.fill(CHARS, 502, 506, (byte)33);
/* 3611 */       Arrays.fill(CHARS, 506, 536, (byte)-19);
/* 3612 */       Arrays.fill(CHARS, 536, 592, (byte)33);
/* 3613 */       Arrays.fill(CHARS, 592, 681, (byte)-19);
/* 3614 */       Arrays.fill(CHARS, 681, 699, (byte)33);
/* 3615 */       Arrays.fill(CHARS, 699, 706, (byte)-19);
/* 3616 */       Arrays.fill(CHARS, 706, 720, (byte)33);
/* 3617 */       Arrays.fill(CHARS, 720, 722, (byte)-87);
/* 3618 */       Arrays.fill(CHARS, 722, 768, (byte)33);
/* 3619 */       Arrays.fill(CHARS, 768, 838, (byte)-87);
/* 3620 */       Arrays.fill(CHARS, 838, 864, (byte)33);
/* 3621 */       Arrays.fill(CHARS, 864, 866, (byte)-87);
/* 3622 */       Arrays.fill(CHARS, 866, 902, (byte)33);
/* 3623 */       CHARS[902] = -19;
/* 3624 */       CHARS[903] = -87;
/* 3625 */       Arrays.fill(CHARS, 904, 907, (byte)-19);
/* 3626 */       CHARS[907] = 33;
/* 3627 */       CHARS[908] = -19;
/* 3628 */       CHARS[909] = 33;
/* 3629 */       Arrays.fill(CHARS, 910, 930, (byte)-19);
/* 3630 */       CHARS[930] = 33;
/* 3631 */       Arrays.fill(CHARS, 931, 975, (byte)-19);
/* 3632 */       CHARS[975] = 33;
/* 3633 */       Arrays.fill(CHARS, 976, 983, (byte)-19);
/* 3634 */       Arrays.fill(CHARS, 983, 986, (byte)33);
/* 3635 */       CHARS[986] = -19;
/* 3636 */       CHARS[987] = 33;
/* 3637 */       CHARS[988] = -19;
/* 3638 */       CHARS[989] = 33;
/* 3639 */       CHARS[990] = -19;
/* 3640 */       CHARS[991] = 33;
/* 3641 */       CHARS[992] = -19;
/* 3642 */       CHARS[993] = 33;
/* 3643 */       Arrays.fill(CHARS, 994, 1012, (byte)-19);
/* 3644 */       Arrays.fill(CHARS, 1012, 1025, (byte)33);
/* 3645 */       Arrays.fill(CHARS, 1025, 1037, (byte)-19);
/* 3646 */       CHARS[1037] = 33;
/* 3647 */       Arrays.fill(CHARS, 1038, 1104, (byte)-19);
/* 3648 */       CHARS[1104] = 33;
/* 3649 */       Arrays.fill(CHARS, 1105, 1117, (byte)-19);
/* 3650 */       CHARS[1117] = 33;
/* 3651 */       Arrays.fill(CHARS, 1118, 1154, (byte)-19);
/* 3652 */       CHARS[1154] = 33;
/* 3653 */       Arrays.fill(CHARS, 1155, 1159, (byte)-87);
/* 3654 */       Arrays.fill(CHARS, 1159, 1168, (byte)33);
/* 3655 */       Arrays.fill(CHARS, 1168, 1221, (byte)-19);
/* 3656 */       Arrays.fill(CHARS, 1221, 1223, (byte)33);
/* 3657 */       Arrays.fill(CHARS, 1223, 1225, (byte)-19);
/* 3658 */       Arrays.fill(CHARS, 1225, 1227, (byte)33);
/* 3659 */       Arrays.fill(CHARS, 1227, 1229, (byte)-19);
/* 3660 */       Arrays.fill(CHARS, 1229, 1232, (byte)33);
/* 3661 */       Arrays.fill(CHARS, 1232, 1260, (byte)-19);
/* 3662 */       Arrays.fill(CHARS, 1260, 1262, (byte)33);
/* 3663 */       Arrays.fill(CHARS, 1262, 1270, (byte)-19);
/* 3664 */       Arrays.fill(CHARS, 1270, 1272, (byte)33);
/* 3665 */       Arrays.fill(CHARS, 1272, 1274, (byte)-19);
/* 3666 */       Arrays.fill(CHARS, 1274, 1329, (byte)33);
/* 3667 */       Arrays.fill(CHARS, 1329, 1367, (byte)-19);
/* 3668 */       Arrays.fill(CHARS, 1367, 1369, (byte)33);
/* 3669 */       CHARS[1369] = -19;
/* 3670 */       Arrays.fill(CHARS, 1370, 1377, (byte)33);
/* 3671 */       Arrays.fill(CHARS, 1377, 1415, (byte)-19);
/* 3672 */       Arrays.fill(CHARS, 1415, 1425, (byte)33);
/* 3673 */       Arrays.fill(CHARS, 1425, 1442, (byte)-87);
/* 3674 */       CHARS[1442] = 33;
/* 3675 */       Arrays.fill(CHARS, 1443, 1466, (byte)-87);
/* 3676 */       CHARS[1466] = 33;
/* 3677 */       Arrays.fill(CHARS, 1467, 1470, (byte)-87);
/* 3678 */       CHARS[1470] = 33;
/* 3679 */       CHARS[1471] = -87;
/* 3680 */       CHARS[1472] = 33;
/* 3681 */       Arrays.fill(CHARS, 1473, 1475, (byte)-87);
/* 3682 */       CHARS[1475] = 33;
/* 3683 */       CHARS[1476] = -87;
/* 3684 */       Arrays.fill(CHARS, 1477, 1488, (byte)33);
/* 3685 */       Arrays.fill(CHARS, 1488, 1515, (byte)-19);
/* 3686 */       Arrays.fill(CHARS, 1515, 1520, (byte)33);
/* 3687 */       Arrays.fill(CHARS, 1520, 1523, (byte)-19);
/* 3688 */       Arrays.fill(CHARS, 1523, 1569, (byte)33);
/* 3689 */       Arrays.fill(CHARS, 1569, 1595, (byte)-19);
/* 3690 */       Arrays.fill(CHARS, 1595, 1600, (byte)33);
/* 3691 */       CHARS[1600] = -87;
/* 3692 */       Arrays.fill(CHARS, 1601, 1611, (byte)-19);
/* 3693 */       Arrays.fill(CHARS, 1611, 1619, (byte)-87);
/* 3694 */       Arrays.fill(CHARS, 1619, 1632, (byte)33);
/* 3695 */       Arrays.fill(CHARS, 1632, 1642, (byte)-87);
/* 3696 */       Arrays.fill(CHARS, 1642, 1648, (byte)33);
/* 3697 */       CHARS[1648] = -87;
/* 3698 */       Arrays.fill(CHARS, 1649, 1720, (byte)-19);
/* 3699 */       Arrays.fill(CHARS, 1720, 1722, (byte)33);
/* 3700 */       Arrays.fill(CHARS, 1722, 1727, (byte)-19);
/* 3701 */       CHARS[1727] = 33;
/* 3702 */       Arrays.fill(CHARS, 1728, 1743, (byte)-19);
/* 3703 */       CHARS[1743] = 33;
/* 3704 */       Arrays.fill(CHARS, 1744, 1748, (byte)-19);
/* 3705 */       CHARS[1748] = 33;
/* 3706 */       CHARS[1749] = -19;
/* 3707 */       Arrays.fill(CHARS, 1750, 1765, (byte)-87);
/* 3708 */       Arrays.fill(CHARS, 1765, 1767, (byte)-19);
/* 3709 */       Arrays.fill(CHARS, 1767, 1769, (byte)-87);
/* 3710 */       CHARS[1769] = 33;
/* 3711 */       Arrays.fill(CHARS, 1770, 1774, (byte)-87);
/* 3712 */       Arrays.fill(CHARS, 1774, 1776, (byte)33);
/* 3713 */       Arrays.fill(CHARS, 1776, 1786, (byte)-87);
/* 3714 */       Arrays.fill(CHARS, 1786, 2305, (byte)33);
/* 3715 */       Arrays.fill(CHARS, 2305, 2308, (byte)-87);
/* 3716 */       CHARS[2308] = 33;
/* 3717 */       Arrays.fill(CHARS, 2309, 2362, (byte)-19);
/* 3718 */       Arrays.fill(CHARS, 2362, 2364, (byte)33);
/* 3719 */       CHARS[2364] = -87;
/* 3720 */       CHARS[2365] = -19;
/* 3721 */       Arrays.fill(CHARS, 2366, 2382, (byte)-87);
/* 3722 */       Arrays.fill(CHARS, 2382, 2385, (byte)33);
/* 3723 */       Arrays.fill(CHARS, 2385, 2389, (byte)-87);
/* 3724 */       Arrays.fill(CHARS, 2389, 2392, (byte)33);
/* 3725 */       Arrays.fill(CHARS, 2392, 2402, (byte)-19);
/* 3726 */       Arrays.fill(CHARS, 2402, 2404, (byte)-87);
/* 3727 */       Arrays.fill(CHARS, 2404, 2406, (byte)33);
/* 3728 */       Arrays.fill(CHARS, 2406, 2416, (byte)-87);
/* 3729 */       Arrays.fill(CHARS, 2416, 2433, (byte)33);
/* 3730 */       Arrays.fill(CHARS, 2433, 2436, (byte)-87);
/* 3731 */       CHARS[2436] = 33;
/* 3732 */       Arrays.fill(CHARS, 2437, 2445, (byte)-19);
/* 3733 */       Arrays.fill(CHARS, 2445, 2447, (byte)33);
/* 3734 */       Arrays.fill(CHARS, 2447, 2449, (byte)-19);
/* 3735 */       Arrays.fill(CHARS, 2449, 2451, (byte)33);
/* 3736 */       Arrays.fill(CHARS, 2451, 2473, (byte)-19);
/* 3737 */       CHARS[2473] = 33;
/* 3738 */       Arrays.fill(CHARS, 2474, 2481, (byte)-19);
/* 3739 */       CHARS[2481] = 33;
/* 3740 */       CHARS[2482] = -19;
/* 3741 */       Arrays.fill(CHARS, 2483, 2486, (byte)33);
/* 3742 */       Arrays.fill(CHARS, 2486, 2490, (byte)-19);
/* 3743 */       Arrays.fill(CHARS, 2490, 2492, (byte)33);
/* 3744 */       CHARS[2492] = -87;
/* 3745 */       CHARS[2493] = 33;
/* 3746 */       Arrays.fill(CHARS, 2494, 2501, (byte)-87);
/* 3747 */       Arrays.fill(CHARS, 2501, 2503, (byte)33);
/* 3748 */       Arrays.fill(CHARS, 2503, 2505, (byte)-87);
/* 3749 */       Arrays.fill(CHARS, 2505, 2507, (byte)33);
/* 3750 */       Arrays.fill(CHARS, 2507, 2510, (byte)-87);
/* 3751 */       Arrays.fill(CHARS, 2510, 2519, (byte)33);
/* 3752 */       CHARS[2519] = -87;
/* 3753 */       Arrays.fill(CHARS, 2520, 2524, (byte)33);
/* 3754 */       Arrays.fill(CHARS, 2524, 2526, (byte)-19);
/* 3755 */       CHARS[2526] = 33;
/* 3756 */       Arrays.fill(CHARS, 2527, 2530, (byte)-19);
/* 3757 */       Arrays.fill(CHARS, 2530, 2532, (byte)-87);
/* 3758 */       Arrays.fill(CHARS, 2532, 2534, (byte)33);
/* 3759 */       Arrays.fill(CHARS, 2534, 2544, (byte)-87);
/* 3760 */       Arrays.fill(CHARS, 2544, 2546, (byte)-19);
/* 3761 */       Arrays.fill(CHARS, 2546, 2562, (byte)33);
/* 3762 */       CHARS[2562] = -87;
/* 3763 */       Arrays.fill(CHARS, 2563, 2565, (byte)33);
/* 3764 */       Arrays.fill(CHARS, 2565, 2571, (byte)-19);
/* 3765 */       Arrays.fill(CHARS, 2571, 2575, (byte)33);
/* 3766 */       Arrays.fill(CHARS, 2575, 2577, (byte)-19);
/* 3767 */       Arrays.fill(CHARS, 2577, 2579, (byte)33);
/* 3768 */       Arrays.fill(CHARS, 2579, 2601, (byte)-19);
/* 3769 */       CHARS[2601] = 33;
/* 3770 */       Arrays.fill(CHARS, 2602, 2609, (byte)-19);
/* 3771 */       CHARS[2609] = 33;
/* 3772 */       Arrays.fill(CHARS, 2610, 2612, (byte)-19);
/* 3773 */       CHARS[2612] = 33;
/* 3774 */       Arrays.fill(CHARS, 2613, 2615, (byte)-19);
/* 3775 */       CHARS[2615] = 33;
/* 3776 */       Arrays.fill(CHARS, 2616, 2618, (byte)-19);
/* 3777 */       Arrays.fill(CHARS, 2618, 2620, (byte)33);
/* 3778 */       CHARS[2620] = -87;
/* 3779 */       CHARS[2621] = 33;
/* 3780 */       Arrays.fill(CHARS, 2622, 2627, (byte)-87);
/* 3781 */       Arrays.fill(CHARS, 2627, 2631, (byte)33);
/* 3782 */       Arrays.fill(CHARS, 2631, 2633, (byte)-87);
/* 3783 */       Arrays.fill(CHARS, 2633, 2635, (byte)33);
/* 3784 */       Arrays.fill(CHARS, 2635, 2638, (byte)-87);
/* 3785 */       Arrays.fill(CHARS, 2638, 2649, (byte)33);
/* 3786 */       Arrays.fill(CHARS, 2649, 2653, (byte)-19);
/* 3787 */       CHARS[2653] = 33;
/* 3788 */       CHARS[2654] = -19;
/* 3789 */       Arrays.fill(CHARS, 2655, 2662, (byte)33);
/* 3790 */       Arrays.fill(CHARS, 2662, 2674, (byte)-87);
/* 3791 */       Arrays.fill(CHARS, 2674, 2677, (byte)-19);
/* 3792 */       Arrays.fill(CHARS, 2677, 2689, (byte)33);
/* 3793 */       Arrays.fill(CHARS, 2689, 2692, (byte)-87);
/* 3794 */       CHARS[2692] = 33;
/* 3795 */       Arrays.fill(CHARS, 2693, 2700, (byte)-19);
/* 3796 */       CHARS[2700] = 33;
/* 3797 */       CHARS[2701] = -19;
/* 3798 */       CHARS[2702] = 33;
/* 3799 */       Arrays.fill(CHARS, 2703, 2706, (byte)-19);
/* 3800 */       CHARS[2706] = 33;
/* 3801 */       Arrays.fill(CHARS, 2707, 2729, (byte)-19);
/* 3802 */       CHARS[2729] = 33;
/* 3803 */       Arrays.fill(CHARS, 2730, 2737, (byte)-19);
/* 3804 */       CHARS[2737] = 33;
/* 3805 */       Arrays.fill(CHARS, 2738, 2740, (byte)-19);
/* 3806 */       CHARS[2740] = 33;
/* 3807 */       Arrays.fill(CHARS, 2741, 2746, (byte)-19);
/* 3808 */       Arrays.fill(CHARS, 2746, 2748, (byte)33);
/* 3809 */       CHARS[2748] = -87;
/* 3810 */       CHARS[2749] = -19;
/* 3811 */       Arrays.fill(CHARS, 2750, 2758, (byte)-87);
/* 3812 */       CHARS[2758] = 33;
/* 3813 */       Arrays.fill(CHARS, 2759, 2762, (byte)-87);
/* 3814 */       CHARS[2762] = 33;
/* 3815 */       Arrays.fill(CHARS, 2763, 2766, (byte)-87);
/* 3816 */       Arrays.fill(CHARS, 2766, 2784, (byte)33);
/* 3817 */       CHARS[2784] = -19;
/* 3818 */       Arrays.fill(CHARS, 2785, 2790, (byte)33);
/* 3819 */       Arrays.fill(CHARS, 2790, 2800, (byte)-87);
/* 3820 */       Arrays.fill(CHARS, 2800, 2817, (byte)33);
/* 3821 */       Arrays.fill(CHARS, 2817, 2820, (byte)-87);
/* 3822 */       CHARS[2820] = 33;
/* 3823 */       Arrays.fill(CHARS, 2821, 2829, (byte)-19);
/* 3824 */       Arrays.fill(CHARS, 2829, 2831, (byte)33);
/* 3825 */       Arrays.fill(CHARS, 2831, 2833, (byte)-19);
/* 3826 */       Arrays.fill(CHARS, 2833, 2835, (byte)33);
/* 3827 */       Arrays.fill(CHARS, 2835, 2857, (byte)-19);
/* 3828 */       CHARS[2857] = 33;
/* 3829 */       Arrays.fill(CHARS, 2858, 2865, (byte)-19);
/* 3830 */       CHARS[2865] = 33;
/* 3831 */       Arrays.fill(CHARS, 2866, 2868, (byte)-19);
/* 3832 */       Arrays.fill(CHARS, 2868, 2870, (byte)33);
/* 3833 */       Arrays.fill(CHARS, 2870, 2874, (byte)-19);
/* 3834 */       Arrays.fill(CHARS, 2874, 2876, (byte)33);
/* 3835 */       CHARS[2876] = -87;
/* 3836 */       CHARS[2877] = -19;
/* 3837 */       Arrays.fill(CHARS, 2878, 2884, (byte)-87);
/* 3838 */       Arrays.fill(CHARS, 2884, 2887, (byte)33);
/* 3839 */       Arrays.fill(CHARS, 2887, 2889, (byte)-87);
/* 3840 */       Arrays.fill(CHARS, 2889, 2891, (byte)33);
/* 3841 */       Arrays.fill(CHARS, 2891, 2894, (byte)-87);
/* 3842 */       Arrays.fill(CHARS, 2894, 2902, (byte)33);
/* 3843 */       Arrays.fill(CHARS, 2902, 2904, (byte)-87);
/* 3844 */       Arrays.fill(CHARS, 2904, 2908, (byte)33);
/* 3845 */       Arrays.fill(CHARS, 2908, 2910, (byte)-19);
/* 3846 */       CHARS[2910] = 33;
/* 3847 */       Arrays.fill(CHARS, 2911, 2914, (byte)-19);
/* 3848 */       Arrays.fill(CHARS, 2914, 2918, (byte)33);
/* 3849 */       Arrays.fill(CHARS, 2918, 2928, (byte)-87);
/* 3850 */       Arrays.fill(CHARS, 2928, 2946, (byte)33);
/* 3851 */       Arrays.fill(CHARS, 2946, 2948, (byte)-87);
/* 3852 */       CHARS[2948] = 33;
/* 3853 */       Arrays.fill(CHARS, 2949, 2955, (byte)-19);
/* 3854 */       Arrays.fill(CHARS, 2955, 2958, (byte)33);
/* 3855 */       Arrays.fill(CHARS, 2958, 2961, (byte)-19);
/* 3856 */       CHARS[2961] = 33;
/* 3857 */       Arrays.fill(CHARS, 2962, 2966, (byte)-19);
/* 3858 */       Arrays.fill(CHARS, 2966, 2969, (byte)33);
/* 3859 */       Arrays.fill(CHARS, 2969, 2971, (byte)-19);
/* 3860 */       CHARS[2971] = 33;
/* 3861 */       CHARS[2972] = -19;
/* 3862 */       CHARS[2973] = 33;
/* 3863 */       Arrays.fill(CHARS, 2974, 2976, (byte)-19);
/* 3864 */       Arrays.fill(CHARS, 2976, 2979, (byte)33);
/* 3865 */       Arrays.fill(CHARS, 2979, 2981, (byte)-19);
/* 3866 */       Arrays.fill(CHARS, 2981, 2984, (byte)33);
/* 3867 */       Arrays.fill(CHARS, 2984, 2987, (byte)-19);
/* 3868 */       Arrays.fill(CHARS, 2987, 2990, (byte)33);
/* 3869 */       Arrays.fill(CHARS, 2990, 2998, (byte)-19);
/* 3870 */       CHARS[2998] = 33;
/* 3871 */       Arrays.fill(CHARS, 2999, 3002, (byte)-19);
/* 3872 */       Arrays.fill(CHARS, 3002, 3006, (byte)33);
/* 3873 */       Arrays.fill(CHARS, 3006, 3011, (byte)-87);
/* 3874 */       Arrays.fill(CHARS, 3011, 3014, (byte)33);
/* 3875 */       Arrays.fill(CHARS, 3014, 3017, (byte)-87);
/* 3876 */       CHARS[3017] = 33;
/* 3877 */       Arrays.fill(CHARS, 3018, 3022, (byte)-87);
/* 3878 */       Arrays.fill(CHARS, 3022, 3031, (byte)33);
/* 3879 */       CHARS[3031] = -87;
/* 3880 */       Arrays.fill(CHARS, 3032, 3047, (byte)33);
/* 3881 */       Arrays.fill(CHARS, 3047, 3056, (byte)-87);
/* 3882 */       Arrays.fill(CHARS, 3056, 3073, (byte)33);
/* 3883 */       Arrays.fill(CHARS, 3073, 3076, (byte)-87);
/* 3884 */       CHARS[3076] = 33;
/* 3885 */       Arrays.fill(CHARS, 3077, 3085, (byte)-19);
/* 3886 */       CHARS[3085] = 33;
/* 3887 */       Arrays.fill(CHARS, 3086, 3089, (byte)-19);
/* 3888 */       CHARS[3089] = 33;
/* 3889 */       Arrays.fill(CHARS, 3090, 3113, (byte)-19);
/* 3890 */       CHARS[3113] = 33;
/* 3891 */       Arrays.fill(CHARS, 3114, 3124, (byte)-19);
/* 3892 */       CHARS[3124] = 33;
/* 3893 */       Arrays.fill(CHARS, 3125, 3130, (byte)-19);
/* 3894 */       Arrays.fill(CHARS, 3130, 3134, (byte)33);
/* 3895 */       Arrays.fill(CHARS, 3134, 3141, (byte)-87);
/* 3896 */       CHARS[3141] = 33;
/* 3897 */       Arrays.fill(CHARS, 3142, 3145, (byte)-87);
/* 3898 */       CHARS[3145] = 33;
/* 3899 */       Arrays.fill(CHARS, 3146, 3150, (byte)-87);
/* 3900 */       Arrays.fill(CHARS, 3150, 3157, (byte)33);
/* 3901 */       Arrays.fill(CHARS, 3157, 3159, (byte)-87);
/* 3902 */       Arrays.fill(CHARS, 3159, 3168, (byte)33);
/* 3903 */       Arrays.fill(CHARS, 3168, 3170, (byte)-19);
/* 3904 */       Arrays.fill(CHARS, 3170, 3174, (byte)33);
/* 3905 */       Arrays.fill(CHARS, 3174, 3184, (byte)-87);
/* 3906 */       Arrays.fill(CHARS, 3184, 3202, (byte)33);
/* 3907 */       Arrays.fill(CHARS, 3202, 3204, (byte)-87);
/* 3908 */       CHARS[3204] = 33;
/* 3909 */       Arrays.fill(CHARS, 3205, 3213, (byte)-19);
/* 3910 */       CHARS[3213] = 33;
/* 3911 */       Arrays.fill(CHARS, 3214, 3217, (byte)-19);
/* 3912 */       CHARS[3217] = 33;
/* 3913 */       Arrays.fill(CHARS, 3218, 3241, (byte)-19);
/* 3914 */       CHARS[3241] = 33;
/* 3915 */       Arrays.fill(CHARS, 3242, 3252, (byte)-19);
/* 3916 */       CHARS[3252] = 33;
/* 3917 */       Arrays.fill(CHARS, 3253, 3258, (byte)-19);
/* 3918 */       Arrays.fill(CHARS, 3258, 3262, (byte)33);
/* 3919 */       Arrays.fill(CHARS, 3262, 3269, (byte)-87);
/* 3920 */       CHARS[3269] = 33;
/* 3921 */       Arrays.fill(CHARS, 3270, 3273, (byte)-87);
/* 3922 */       CHARS[3273] = 33;
/* 3923 */       Arrays.fill(CHARS, 3274, 3278, (byte)-87);
/* 3924 */       Arrays.fill(CHARS, 3278, 3285, (byte)33);
/* 3925 */       Arrays.fill(CHARS, 3285, 3287, (byte)-87);
/* 3926 */       Arrays.fill(CHARS, 3287, 3294, (byte)33);
/* 3927 */       CHARS[3294] = -19;
/* 3928 */       CHARS[3295] = 33;
/* 3929 */       Arrays.fill(CHARS, 3296, 3298, (byte)-19);
/* 3930 */       Arrays.fill(CHARS, 3298, 3302, (byte)33);
/* 3931 */       Arrays.fill(CHARS, 3302, 3312, (byte)-87);
/* 3932 */       Arrays.fill(CHARS, 3312, 3330, (byte)33);
/* 3933 */       Arrays.fill(CHARS, 3330, 3332, (byte)-87);
/* 3934 */       CHARS[3332] = 33;
/* 3935 */       Arrays.fill(CHARS, 3333, 3341, (byte)-19);
/* 3936 */       CHARS[3341] = 33;
/* 3937 */       Arrays.fill(CHARS, 3342, 3345, (byte)-19);
/* 3938 */       CHARS[3345] = 33;
/* 3939 */       Arrays.fill(CHARS, 3346, 3369, (byte)-19);
/* 3940 */       CHARS[3369] = 33;
/* 3941 */       Arrays.fill(CHARS, 3370, 3386, (byte)-19);
/* 3942 */       Arrays.fill(CHARS, 3386, 3390, (byte)33);
/* 3943 */       Arrays.fill(CHARS, 3390, 3396, (byte)-87);
/* 3944 */       Arrays.fill(CHARS, 3396, 3398, (byte)33);
/* 3945 */       Arrays.fill(CHARS, 3398, 3401, (byte)-87);
/* 3946 */       CHARS[3401] = 33;
/* 3947 */       Arrays.fill(CHARS, 3402, 3406, (byte)-87);
/* 3948 */       Arrays.fill(CHARS, 3406, 3415, (byte)33);
/* 3949 */       CHARS[3415] = -87;
/* 3950 */       Arrays.fill(CHARS, 3416, 3424, (byte)33);
/* 3951 */       Arrays.fill(CHARS, 3424, 3426, (byte)-19);
/* 3952 */       Arrays.fill(CHARS, 3426, 3430, (byte)33);
/* 3953 */       Arrays.fill(CHARS, 3430, 3440, (byte)-87);
/* 3954 */       Arrays.fill(CHARS, 3440, 3585, (byte)33);
/* 3955 */       Arrays.fill(CHARS, 3585, 3631, (byte)-19);
/* 3956 */       CHARS[3631] = 33;
/* 3957 */       CHARS[3632] = -19;
/* 3958 */       CHARS[3633] = -87;
/* 3959 */       Arrays.fill(CHARS, 3634, 3636, (byte)-19);
/* 3960 */       Arrays.fill(CHARS, 3636, 3643, (byte)-87);
/* 3961 */       Arrays.fill(CHARS, 3643, 3648, (byte)33);
/* 3962 */       Arrays.fill(CHARS, 3648, 3654, (byte)-19);
/* 3963 */       Arrays.fill(CHARS, 3654, 3663, (byte)-87);
/* 3964 */       CHARS[3663] = 33;
/* 3965 */       Arrays.fill(CHARS, 3664, 3674, (byte)-87);
/* 3966 */       Arrays.fill(CHARS, 3674, 3713, (byte)33);
/* 3967 */       Arrays.fill(CHARS, 3713, 3715, (byte)-19);
/* 3968 */       CHARS[3715] = 33;
/* 3969 */       CHARS[3716] = -19;
/* 3970 */       Arrays.fill(CHARS, 3717, 3719, (byte)33);
/* 3971 */       Arrays.fill(CHARS, 3719, 3721, (byte)-19);
/* 3972 */       CHARS[3721] = 33;
/* 3973 */       CHARS[3722] = -19;
/* 3974 */       Arrays.fill(CHARS, 3723, 3725, (byte)33);
/* 3975 */       CHARS[3725] = -19;
/* 3976 */       Arrays.fill(CHARS, 3726, 3732, (byte)33);
/* 3977 */       Arrays.fill(CHARS, 3732, 3736, (byte)-19);
/* 3978 */       CHARS[3736] = 33;
/* 3979 */       Arrays.fill(CHARS, 3737, 3744, (byte)-19);
/* 3980 */       CHARS[3744] = 33;
/* 3981 */       Arrays.fill(CHARS, 3745, 3748, (byte)-19);
/* 3982 */       CHARS[3748] = 33;
/* 3983 */       CHARS[3749] = -19;
/* 3984 */       CHARS[3750] = 33;
/* 3985 */       CHARS[3751] = -19;
/* 3986 */       Arrays.fill(CHARS, 3752, 3754, (byte)33);
/* 3987 */       Arrays.fill(CHARS, 3754, 3756, (byte)-19);
/* 3988 */       CHARS[3756] = 33;
/* 3989 */       Arrays.fill(CHARS, 3757, 3759, (byte)-19);
/* 3990 */       CHARS[3759] = 33;
/* 3991 */       CHARS[3760] = -19;
/* 3992 */       CHARS[3761] = -87;
/* 3993 */       Arrays.fill(CHARS, 3762, 3764, (byte)-19);
/* 3994 */       Arrays.fill(CHARS, 3764, 3770, (byte)-87);
/* 3995 */       CHARS[3770] = 33;
/* 3996 */       Arrays.fill(CHARS, 3771, 3773, (byte)-87);
/* 3997 */       CHARS[3773] = -19;
/* 3998 */       Arrays.fill(CHARS, 3774, 3776, (byte)33);
/* 3999 */       Arrays.fill(CHARS, 3776, 3781, (byte)-19);
/* 4000 */       CHARS[3781] = 33;
/* 4001 */       CHARS[3782] = -87;
/* 4002 */       CHARS[3783] = 33;
/* 4003 */       Arrays.fill(CHARS, 3784, 3790, (byte)-87);
/* 4004 */       Arrays.fill(CHARS, 3790, 3792, (byte)33);
/* 4005 */       Arrays.fill(CHARS, 3792, 3802, (byte)-87);
/* 4006 */       Arrays.fill(CHARS, 3802, 3864, (byte)33);
/* 4007 */       Arrays.fill(CHARS, 3864, 3866, (byte)-87);
/* 4008 */       Arrays.fill(CHARS, 3866, 3872, (byte)33);
/* 4009 */       Arrays.fill(CHARS, 3872, 3882, (byte)-87);
/* 4010 */       Arrays.fill(CHARS, 3882, 3893, (byte)33);
/* 4011 */       CHARS[3893] = -87;
/* 4012 */       CHARS[3894] = 33;
/* 4013 */       CHARS[3895] = -87;
/* 4014 */       CHARS[3896] = 33;
/* 4015 */       CHARS[3897] = -87;
/* 4016 */       Arrays.fill(CHARS, 3898, 3902, (byte)33);
/* 4017 */       Arrays.fill(CHARS, 3902, 3904, (byte)-87);
/* 4018 */       Arrays.fill(CHARS, 3904, 3912, (byte)-19);
/* 4019 */       CHARS[3912] = 33;
/* 4020 */       Arrays.fill(CHARS, 3913, 3946, (byte)-19);
/* 4021 */       Arrays.fill(CHARS, 3946, 3953, (byte)33);
/* 4022 */       Arrays.fill(CHARS, 3953, 3973, (byte)-87);
/* 4023 */       CHARS[3973] = 33;
/* 4024 */       Arrays.fill(CHARS, 3974, 3980, (byte)-87);
/* 4025 */       Arrays.fill(CHARS, 3980, 3984, (byte)33);
/* 4026 */       Arrays.fill(CHARS, 3984, 3990, (byte)-87);
/* 4027 */       CHARS[3990] = 33;
/* 4028 */       CHARS[3991] = -87;
/* 4029 */       CHARS[3992] = 33;
/* 4030 */       Arrays.fill(CHARS, 3993, 4014, (byte)-87);
/* 4031 */       Arrays.fill(CHARS, 4014, 4017, (byte)33);
/* 4032 */       Arrays.fill(CHARS, 4017, 4024, (byte)-87);
/* 4033 */       CHARS[4024] = 33;
/* 4034 */       CHARS[4025] = -87;
/* 4035 */       Arrays.fill(CHARS, 4026, 4256, (byte)33);
/* 4036 */       Arrays.fill(CHARS, 4256, 4294, (byte)-19);
/* 4037 */       Arrays.fill(CHARS, 4294, 4304, (byte)33);
/* 4038 */       Arrays.fill(CHARS, 4304, 4343, (byte)-19);
/* 4039 */       Arrays.fill(CHARS, 4343, 4352, (byte)33);
/* 4040 */       CHARS[4352] = -19;
/* 4041 */       CHARS[4353] = 33;
/* 4042 */       Arrays.fill(CHARS, 4354, 4356, (byte)-19);
/* 4043 */       CHARS[4356] = 33;
/* 4044 */       Arrays.fill(CHARS, 4357, 4360, (byte)-19);
/* 4045 */       CHARS[4360] = 33;
/* 4046 */       CHARS[4361] = -19;
/* 4047 */       CHARS[4362] = 33;
/* 4048 */       Arrays.fill(CHARS, 4363, 4365, (byte)-19);
/* 4049 */       CHARS[4365] = 33;
/* 4050 */       Arrays.fill(CHARS, 4366, 4371, (byte)-19);
/* 4051 */       Arrays.fill(CHARS, 4371, 4412, (byte)33);
/* 4052 */       CHARS[4412] = -19;
/* 4053 */       CHARS[4413] = 33;
/* 4054 */       CHARS[4414] = -19;
/* 4055 */       CHARS[4415] = 33;
/* 4056 */       CHARS[4416] = -19;
/* 4057 */       Arrays.fill(CHARS, 4417, 4428, (byte)33);
/* 4058 */       CHARS[4428] = -19;
/* 4059 */       CHARS[4429] = 33;
/* 4060 */       CHARS[4430] = -19;
/* 4061 */       CHARS[4431] = 33;
/* 4062 */       CHARS[4432] = -19;
/* 4063 */       Arrays.fill(CHARS, 4433, 4436, (byte)33);
/* 4064 */       Arrays.fill(CHARS, 4436, 4438, (byte)-19);
/* 4065 */       Arrays.fill(CHARS, 4438, 4441, (byte)33);
/* 4066 */       CHARS[4441] = -19;
/* 4067 */       Arrays.fill(CHARS, 4442, 4447, (byte)33);
/* 4068 */       Arrays.fill(CHARS, 4447, 4450, (byte)-19);
/* 4069 */       CHARS[4450] = 33;
/* 4070 */       CHARS[4451] = -19;
/* 4071 */       CHARS[4452] = 33;
/* 4072 */       CHARS[4453] = -19;
/* 4073 */       CHARS[4454] = 33;
/* 4074 */       CHARS[4455] = -19;
/* 4075 */       CHARS[4456] = 33;
/* 4076 */       CHARS[4457] = -19;
/* 4077 */       Arrays.fill(CHARS, 4458, 4461, (byte)33);
/* 4078 */       Arrays.fill(CHARS, 4461, 4463, (byte)-19);
/* 4079 */       Arrays.fill(CHARS, 4463, 4466, (byte)33);
/* 4080 */       Arrays.fill(CHARS, 4466, 4468, (byte)-19);
/* 4081 */       CHARS[4468] = 33;
/* 4082 */       CHARS[4469] = -19;
/* 4083 */       Arrays.fill(CHARS, 4470, 4510, (byte)33);
/* 4084 */       CHARS[4510] = -19;
/* 4085 */       Arrays.fill(CHARS, 4511, 4520, (byte)33);
/* 4086 */       CHARS[4520] = -19;
/* 4087 */       Arrays.fill(CHARS, 4521, 4523, (byte)33);
/* 4088 */       CHARS[4523] = -19;
/* 4089 */       Arrays.fill(CHARS, 4524, 4526, (byte)33);
/* 4090 */       Arrays.fill(CHARS, 4526, 4528, (byte)-19);
/* 4091 */       Arrays.fill(CHARS, 4528, 4535, (byte)33);
/* 4092 */       Arrays.fill(CHARS, 4535, 4537, (byte)-19);
/* 4093 */       CHARS[4537] = 33;
/* 4094 */       CHARS[4538] = -19;
/* 4095 */       CHARS[4539] = 33;
/* 4096 */       Arrays.fill(CHARS, 4540, 4547, (byte)-19);
/* 4097 */       Arrays.fill(CHARS, 4547, 4587, (byte)33);
/* 4098 */       CHARS[4587] = -19;
/* 4099 */       Arrays.fill(CHARS, 4588, 4592, (byte)33);
/* 4100 */       CHARS[4592] = -19;
/* 4101 */       Arrays.fill(CHARS, 4593, 4601, (byte)33);
/* 4102 */       CHARS[4601] = -19;
/* 4103 */       Arrays.fill(CHARS, 4602, 7680, (byte)33);
/* 4104 */       Arrays.fill(CHARS, 7680, 7836, (byte)-19);
/* 4105 */       Arrays.fill(CHARS, 7836, 7840, (byte)33);
/* 4106 */       Arrays.fill(CHARS, 7840, 7930, (byte)-19);
/* 4107 */       Arrays.fill(CHARS, 7930, 7936, (byte)33);
/* 4108 */       Arrays.fill(CHARS, 7936, 7958, (byte)-19);
/* 4109 */       Arrays.fill(CHARS, 7958, 7960, (byte)33);
/* 4110 */       Arrays.fill(CHARS, 7960, 7966, (byte)-19);
/* 4111 */       Arrays.fill(CHARS, 7966, 7968, (byte)33);
/* 4112 */       Arrays.fill(CHARS, 7968, 8006, (byte)-19);
/* 4113 */       Arrays.fill(CHARS, 8006, 8008, (byte)33);
/* 4114 */       Arrays.fill(CHARS, 8008, 8014, (byte)-19);
/* 4115 */       Arrays.fill(CHARS, 8014, 8016, (byte)33);
/* 4116 */       Arrays.fill(CHARS, 8016, 8024, (byte)-19);
/* 4117 */       CHARS[8024] = 33;
/* 4118 */       CHARS[8025] = -19;
/* 4119 */       CHARS[8026] = 33;
/* 4120 */       CHARS[8027] = -19;
/* 4121 */       CHARS[8028] = 33;
/* 4122 */       CHARS[8029] = -19;
/* 4123 */       CHARS[8030] = 33;
/* 4124 */       Arrays.fill(CHARS, 8031, 8062, (byte)-19);
/* 4125 */       Arrays.fill(CHARS, 8062, 8064, (byte)33);
/* 4126 */       Arrays.fill(CHARS, 8064, 8117, (byte)-19);
/* 4127 */       CHARS[8117] = 33;
/* 4128 */       Arrays.fill(CHARS, 8118, 8125, (byte)-19);
/* 4129 */       CHARS[8125] = 33;
/* 4130 */       CHARS[8126] = -19;
/* 4131 */       Arrays.fill(CHARS, 8127, 8130, (byte)33);
/* 4132 */       Arrays.fill(CHARS, 8130, 8133, (byte)-19);
/* 4133 */       CHARS[8133] = 33;
/* 4134 */       Arrays.fill(CHARS, 8134, 8141, (byte)-19);
/* 4135 */       Arrays.fill(CHARS, 8141, 8144, (byte)33);
/* 4136 */       Arrays.fill(CHARS, 8144, 8148, (byte)-19);
/* 4137 */       Arrays.fill(CHARS, 8148, 8150, (byte)33);
/* 4138 */       Arrays.fill(CHARS, 8150, 8156, (byte)-19);
/* 4139 */       Arrays.fill(CHARS, 8156, 8160, (byte)33);
/* 4140 */       Arrays.fill(CHARS, 8160, 8173, (byte)-19);
/* 4141 */       Arrays.fill(CHARS, 8173, 8178, (byte)33);
/* 4142 */       Arrays.fill(CHARS, 8178, 8181, (byte)-19);
/* 4143 */       CHARS[8181] = 33;
/* 4144 */       Arrays.fill(CHARS, 8182, 8189, (byte)-19);
/* 4145 */       Arrays.fill(CHARS, 8189, 8400, (byte)33);
/* 4146 */       Arrays.fill(CHARS, 8400, 8413, (byte)-87);
/* 4147 */       Arrays.fill(CHARS, 8413, 8417, (byte)33);
/* 4148 */       CHARS[8417] = -87;
/* 4149 */       Arrays.fill(CHARS, 8418, 8486, (byte)33);
/* 4150 */       CHARS[8486] = -19;
/* 4151 */       Arrays.fill(CHARS, 8487, 8490, (byte)33);
/* 4152 */       Arrays.fill(CHARS, 8490, 8492, (byte)-19);
/* 4153 */       Arrays.fill(CHARS, 8492, 8494, (byte)33);
/* 4154 */       CHARS[8494] = -19;
/* 4155 */       Arrays.fill(CHARS, 8495, 8576, (byte)33);
/* 4156 */       Arrays.fill(CHARS, 8576, 8579, (byte)-19);
/* 4157 */       Arrays.fill(CHARS, 8579, 12293, (byte)33);
/* 4158 */       CHARS[12293] = -87;
/* 4159 */       CHARS[12294] = 33;
/* 4160 */       CHARS[12295] = -19;
/* 4161 */       Arrays.fill(CHARS, 12296, 12321, (byte)33);
/* 4162 */       Arrays.fill(CHARS, 12321, 12330, (byte)-19);
/* 4163 */       Arrays.fill(CHARS, 12330, 12336, (byte)-87);
/* 4164 */       CHARS[12336] = 33;
/* 4165 */       Arrays.fill(CHARS, 12337, 12342, (byte)-87);
/* 4166 */       Arrays.fill(CHARS, 12342, 12353, (byte)33);
/* 4167 */       Arrays.fill(CHARS, 12353, 12437, (byte)-19);
/* 4168 */       Arrays.fill(CHARS, 12437, 12441, (byte)33);
/* 4169 */       Arrays.fill(CHARS, 12441, 12443, (byte)-87);
/* 4170 */       Arrays.fill(CHARS, 12443, 12445, (byte)33);
/* 4171 */       Arrays.fill(CHARS, 12445, 12447, (byte)-87);
/* 4172 */       Arrays.fill(CHARS, 12447, 12449, (byte)33);
/* 4173 */       Arrays.fill(CHARS, 12449, 12539, (byte)-19);
/* 4174 */       CHARS[12539] = 33;
/* 4175 */       Arrays.fill(CHARS, 12540, 12543, (byte)-87);
/* 4176 */       Arrays.fill(CHARS, 12543, 12549, (byte)33);
/* 4177 */       Arrays.fill(CHARS, 12549, 12589, (byte)-19);
/* 4178 */       Arrays.fill(CHARS, 12589, 19968, (byte)33);
/* 4179 */       Arrays.fill(CHARS, 19968, 40870, (byte)-19);
/* 4180 */       Arrays.fill(CHARS, 40870, 44032, (byte)33);
/* 4181 */       Arrays.fill(CHARS, 44032, 55204, (byte)-19);
/* 4182 */       Arrays.fill(CHARS, 55204, 55296, (byte)33);
/* 4183 */       Arrays.fill(CHARS, 57344, 65534, (byte)33);
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
/*      */     
/*      */     public static boolean isSupplemental(int c) {
/* 4197 */       return (c >= 65536 && c <= 1114111);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static int supplemental(char h, char l) {
/* 4208 */       return (h - 55296) * 1024 + l - 56320 + 65536;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static char highSurrogate(int c) {
/* 4217 */       return (char)((c - 65536 >> 10) + 55296);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static char lowSurrogate(int c) {
/* 4226 */       return (char)((c - 65536 & 0x3FF) + 56320);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isHighSurrogate(int c) {
/* 4235 */       return (55296 <= c && c <= 56319);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isLowSurrogate(int c) {
/* 4244 */       return (56320 <= c && c <= 57343);
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
/*      */ 
/*      */     
/*      */     public static boolean isValid(int c) {
/* 4259 */       if ((c >= 65536 || (CHARS[c] & 0x1) == 0) && (
/* 4260 */         65536 > c || c > 1114111)) return false;
/*      */       
/*      */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isInvalid(int c) {
/* 4269 */       return !isValid(c);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isContent(int c) {
/* 4278 */       if ((c >= 65536 || (CHARS[c] & 0x20) == 0) && (
/* 4279 */         65536 > c || c > 1114111)) return false;
/*      */       
/*      */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isMarkup(int c) {
/* 4289 */       return !(c != 60 && c != 38 && c != 37);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isSpace(int c) {
/* 4299 */       return (c <= 32 && (CHARS[c] & 0x2) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isNameStart(int c) {
/* 4310 */       return (c < 65536 && (CHARS[c] & 0x4) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isName(int c) {
/* 4321 */       return (c < 65536 && (CHARS[c] & 0x8) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isNCNameStart(int c) {
/* 4332 */       return (c < 65536 && (CHARS[c] & 0x40) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isNCName(int c) {
/* 4343 */       return (c < 65536 && (CHARS[c] & 0x80) != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isPubid(int c) {
/* 4354 */       return (c < 65536 && (CHARS[c] & 0x10) != 0);
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
/*      */     
/*      */     public static boolean isValidName(String name) {
/* 4368 */       if (name.length() == 0)
/* 4369 */         return false; 
/* 4370 */       char ch = name.charAt(0);
/* 4371 */       if (!isNameStart(ch))
/* 4372 */         return false; 
/* 4373 */       for (int i = 1; i < name.length(); i++) {
/* 4374 */         ch = name.charAt(i);
/* 4375 */         if (!isName(ch)) {
/* 4376 */           return false;
/*      */         }
/*      */       } 
/* 4379 */       return true;
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
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isValidNCName(String ncName) {
/* 4395 */       if (ncName.length() == 0)
/* 4396 */         return false; 
/* 4397 */       char ch = ncName.charAt(0);
/* 4398 */       if (!isNCNameStart(ch))
/* 4399 */         return false; 
/* 4400 */       for (int i = 1; i < ncName.length(); i++) {
/* 4401 */         ch = ncName.charAt(i);
/* 4402 */         if (!isNCName(ch)) {
/* 4403 */           return false;
/*      */         }
/*      */       } 
/* 4406 */       return true;
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
/*      */     
/*      */     public static boolean isValidNmtoken(String nmtoken) {
/* 4420 */       if (nmtoken.length() == 0)
/* 4421 */         return false; 
/* 4422 */       for (int i = 0; i < nmtoken.length(); i++) {
/* 4423 */         char ch = nmtoken.charAt(i);
/* 4424 */         if (!isName(ch)) {
/* 4425 */           return false;
/*      */         }
/*      */       } 
/* 4428 */       return true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean isValidIANAEncoding(String ianaEncoding) {
/* 4446 */       if (ianaEncoding != null) {
/* 4447 */         int length = ianaEncoding.length();
/* 4448 */         if (length > 0) {
/* 4449 */           char c = ianaEncoding.charAt(0);
/* 4450 */           if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
/* 4451 */             for (int i = 1; i < length; i++) {
/* 4452 */               c = ianaEncoding.charAt(i);
/* 4453 */               if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (
/* 4454 */                 c < '0' || c > '9') && c != '.' && c != '_' && 
/* 4455 */                 c != '-') {
/* 4456 */                 return false;
/*      */               }
/*      */             } 
/* 4459 */             return true;
/*      */           } 
/*      */         } 
/*      */       } 
/* 4463 */       return false;
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
/*      */     public static boolean isValidJavaEncoding(String javaEncoding) {
/* 4475 */       if (javaEncoding != null) {
/* 4476 */         int length = javaEncoding.length();
/* 4477 */         if (length > 0) {
/* 4478 */           for (int i = 1; i < length; i++) {
/* 4479 */             char c = javaEncoding.charAt(i);
/* 4480 */             if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (
/* 4481 */               c < '0' || c > '9') && c != '.' && c != '_' && 
/* 4482 */               c != '-') {
/* 4483 */               return false;
/*      */             }
/*      */           } 
/* 4486 */           return true;
/*      */         } 
/*      */       } 
/* 4489 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class TypeValidator
/*      */   {
/*      */     public static final short LESS_THAN = -1;
/*      */     
/*      */     public static final short EQUAL = 0;
/*      */     
/*      */     public static final short GREATER_THAN = 1;
/*      */     
/*      */     public static final short INDETERMINATE = 2;
/*      */ 
/*      */     
/*      */     public static final boolean isDigit(char ch) {
/* 4506 */       return (ch >= '0' && ch <= '9');
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int getDigit(char ch) {
/* 4512 */       return isDigit(ch) ? (ch - 48) : -1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\internal\DataValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */