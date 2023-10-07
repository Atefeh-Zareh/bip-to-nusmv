/*     */ package org.eclipse.emf.ecore.resource;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
/*     */ import org.eclipse.core.runtime.content.IContentDescription;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
/*     */ import org.eclipse.emf.ecore.resource.impl.ContentHandlerRegistryImpl;
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
/*     */ public interface ContentHandler
/*     */ {
/*     */   public static final String OPTION_REQUESTED_PROPERTIES = "REQUESTED_PROPERTIES";
/*     */   public static final String VALIDITY_PROPERTY = "org.eclipse.emf.ecore:validity";
/*     */   public static final String CONTENT_TYPE_PROPERTY = "org.eclipse.emf.ecore:contentType";
/*     */   public static final String UNSPECIFIED_CONTENT_TYPE = "";
/*     */   public static final String CHARSET_PROPERTY = "org.eclipse.core.runtime:charset";
/*     */   public static final String BYTE_ORDER_MARK_PROPERTY = "org.eclipse.core.runtime:bom";
/*     */   
/*     */   public static interface Registry
/*     */     extends SortedMap<Integer, List<ContentHandler>>
/*     */   {
/*     */     public static final int VERY_HIGH_PRIORITY = -10000;
/*     */     public static final int HIGH_PRIORITY = -1000;
/*     */     public static final int NORMAL_PRIORITY = 0;
/*     */     public static final int LOW_PRIORITY = 1000;
/*     */     public static final int VERY_LOW_PRIORITY = 10000;
/*  96 */     public static final Registry INSTANCE = (Registry)new ContentHandlerRegistryImpl();
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
/*     */     void put(int param1Int, ContentHandler param1ContentHandler);
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
/*     */     List<ContentHandler> contentHandlers();
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
/*     */ 
/*     */   
/*     */   public enum Validity
/*     */   {
/* 139 */     INVALID,
/*     */ 
/*     */ 
/*     */     
/* 143 */     INDETERMINATE,
/*     */ 
/*     */ 
/*     */     
/* 147 */     VALID;
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
/*     */   public enum ByteOrderMark
/*     */   {
/* 197 */     UTF_8
/*     */     {
/*     */       
/*     */       public byte[] bytes()
/*     */       {
/* 202 */         return ByteOrderMark.UTF_8_BYTES;
/*     */       }
/*     */     },
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     UTF_16BE
/*     */     {
/*     */       
/*     */       public byte[] bytes()
/*     */       {
/* 214 */         return ByteOrderMark.UTF_16BE_BYTES;
/*     */       }
/*     */     },
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     UTF_16LE
/*     */     {
/*     */       
/*     */       public byte[] bytes()
/*     */       {
/* 226 */         return ByteOrderMark.UTF_16LE_BYTES;
/*     */       }
/*     */     };
/*     */ 
/*     */     
/*     */     static {
/*     */       byte[] utf8Bytes, utf16BEBytes, utf16LEBytes;
/*     */     }
/*     */     
/*     */     private static final byte[] UTF_8_BYTES;
/*     */     private static final byte[] UTF_16BE_BYTES;
/*     */     private static final byte[] UTF_16LE_BYTES;
/*     */     
/*     */     static {
/*     */       try {
/* 241 */         utf8Bytes = IContentDescription.BOM_UTF_8;
/* 242 */         utf16BEBytes = IContentDescription.BOM_UTF_16BE;
/* 243 */         utf16LEBytes = IContentDescription.BOM_UTF_16LE;
/*     */       }
/* 245 */       catch (Throwable throwable) {
/*     */         
/* 247 */         utf8Bytes = new byte[] { -17, -69, -65 };
/* 248 */         utf16BEBytes = new byte[] { -2, -1 };
/* 249 */         utf16LEBytes = new byte[] { -1, -2 };
/*     */       } 
/* 251 */       UTF_8_BYTES = utf8Bytes;
/* 252 */       UTF_16BE_BYTES = utf16BEBytes;
/* 253 */       UTF_16LE_BYTES = utf16LEBytes;
/*     */     }
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
/*     */     public static ByteOrderMark read(InputStream inputStream) throws IOException {
/* 275 */       int first = inputStream.read();
/* 276 */       if (first == 239) {
/*     */         
/* 278 */         if (inputStream.read() == 187 && inputStream.read() == 191)
/*     */         {
/* 280 */           return UTF_8;
/*     */         }
/*     */       }
/* 283 */       else if (first == 254) {
/*     */         
/* 285 */         if (inputStream.read() == 255)
/*     */         {
/* 287 */           return UTF_16BE;
/*     */         }
/*     */       }
/* 290 */       else if (first == 255) {
/*     */         
/* 292 */         if (inputStream.read() == 254)
/*     */         {
/* 294 */           return UTF_16LE;
/*     */         }
/*     */       } 
/*     */       
/* 298 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract byte[] bytes();
/*     */   }
/*     */   
/* 306 */   public static final Map<String, Object> INVALID_CONTENT_DESCRIPTION = Collections.unmodifiableMap(ContentHandlerImpl.createContentDescription(Validity.INVALID));
/*     */   
/*     */   boolean canHandle(URI paramURI);
/*     */   
/*     */   Map<String, ?> contentDescription(URI paramURI, InputStream paramInputStream, Map<?, ?> paramMap, Map<Object, Object> paramMap1) throws IOException;
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\ContentHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */