/*     */ package org.eclipse.emf.ecore.resource;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.Writer;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
/*     */ import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
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
/*     */ public interface URIConverter
/*     */ {
/*     */   public static final String OPTION_URI_CONVERTER = "URI_CONVERTER";
/*     */   public static final String OPTION_RESPONSE = "RESPONSE";
/*     */   public static final String RESPONSE_TIME_STAMP_PROPERTY = "TIME_STAMP";
/* 155 */   public static final Map<URI, URI> URI_MAP = URIMappingRegistryImpl.INSTANCE.map();
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_TIME_STAMP = "timeStamp";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long NULL_TIME_STAMP = -1L;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_LENGTH = "length";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_READ_ONLY = "readOnly";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_EXECUTABLE = "executable";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_ARCHIVE = "archive";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_HIDDEN = "hidden";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ATTRIBUTE_DIRECTORY = "directory";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String OPTION_REQUESTED_ATTRIBUTES = "requestedAttributes";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Cipher
/*     */   {
/*     */     OutputStream encrypt(OutputStream param1OutputStream) throws Exception;
/*     */ 
/*     */ 
/*     */     
/*     */     void finish(OutputStream param1OutputStream) throws Exception;
/*     */ 
/*     */ 
/*     */     
/*     */     InputStream decrypt(InputStream param1InputStream) throws Exception;
/*     */ 
/*     */ 
/*     */     
/*     */     void finish(InputStream param1InputStream) throws Exception;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Readable
/*     */   {
/*     */     Reader asReader();
/*     */ 
/*     */ 
/*     */     
/*     */     String getEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ReadableInputStream
/*     */     extends InputStream
/*     */     implements Readable
/*     */   {
/* 230 */     private static final Pattern XML_HEADER = Pattern.compile("<\\?xml\\s+(?:version\\s*=\\s*\"[^\"]*\"\\s+)encoding\\s*=\\s*\"\\s*([^\\s\"]*)\"\\s*\\?>");
/*     */     protected String encoding;
/*     */     
/*     */     public static String getEncoding(String xmlString) {
/* 234 */       Matcher matcher = XML_HEADER.matcher(xmlString);
/* 235 */       return 
/* 236 */         matcher.lookingAt() ? 
/* 237 */         matcher.group(1) : 
/* 238 */         null;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Reader reader;
/*     */     
/*     */     protected Buffer buffer;
/*     */     
/*     */     public static String getEncoding(Reader xmlReader) {
/*     */       try {
/* 248 */         xmlReader.mark(100);
/* 249 */         char[] buffer = new char[100];
/* 250 */         int length = xmlReader.read(buffer);
/* 251 */         if (length > -1) {
/*     */           
/* 253 */           Matcher matcher = XML_HEADER.matcher(new String(buffer, 0, length));
/* 254 */           return 
/* 255 */             matcher.lookingAt() ? 
/* 256 */             matcher.group(1) : 
/* 257 */             null;
/*     */         } 
/*     */ 
/*     */         
/* 261 */         return null;
/*     */       
/*     */       }
/* 264 */       catch (IOException exception) {
/*     */         char[] buffer;
/* 266 */         return null;
/*     */       } finally {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 272 */           xmlReader.reset();
/*     */         }
/* 274 */         catch (IOException iOException) {}
/*     */       } 
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
/*     */     public ReadableInputStream(Reader reader, String encoding) {
/* 288 */       this.reader = reader;
/* 289 */       this.encoding = encoding;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ReadableInputStream(Reader xmlReader) {
/* 298 */       this.reader = xmlReader.markSupported() ? xmlReader : new BufferedReader(xmlReader);
/* 299 */       this.encoding = getEncoding(this.reader);
/*     */     }
/*     */ 
/*     */     
/*     */     public ReadableInputStream(String string, String encoding) {
/* 304 */       this(new StringReader(string), encoding);
/*     */     }
/*     */ 
/*     */     
/*     */     public ReadableInputStream(String xmlString) {
/* 309 */       this(new StringReader(xmlString), getEncoding(xmlString));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int read() throws IOException {
/* 315 */       if (this.buffer == null)
/*     */       {
/* 317 */         this.buffer = new Buffer(100);
/*     */       }
/*     */       
/* 320 */       return this.buffer.read();
/*     */     }
/*     */ 
/*     */     
/*     */     public Reader asReader() {
/* 325 */       return this.reader;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getEncoding() {
/* 330 */       return this.encoding;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 336 */       super.close();
/* 337 */       this.reader.close();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void reset() throws IOException {
/* 343 */       super.reset();
/* 344 */       this.reader.reset();
/*     */     }
/*     */     
/*     */     protected class Buffer
/*     */       extends ByteArrayOutputStream
/*     */     {
/*     */       protected int index;
/*     */       protected char[] characters;
/*     */       protected OutputStreamWriter writer;
/*     */       
/*     */       public Buffer(int size) throws IOException {
/* 355 */         super(size);
/* 356 */         this.characters = new char[size];
/* 357 */         this.writer = new OutputStreamWriter(this, URIConverter.ReadableInputStream.this.encoding);
/*     */       }
/*     */ 
/*     */       
/*     */       public int read() throws IOException {
/* 362 */         if (this.index < this.count)
/*     */         {
/* 364 */           return this.buf[this.index++];
/*     */         }
/*     */ 
/*     */         
/* 368 */         this.index = 0;
/* 369 */         reset();
/*     */         
/* 371 */         int readCount = URIConverter.ReadableInputStream.this.reader.read(this.characters);
/* 372 */         if (readCount < 0)
/*     */         {
/* 374 */           return -1;
/*     */         }
/*     */ 
/*     */         
/* 378 */         this.writer.write(this.characters, 0, readCount);
/* 379 */         this.writer.flush();
/* 380 */         return this.buf[this.index++];
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Writeable
/*     */   {
/*     */     Writer asWriter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String getEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class WriteableOutputStream
/*     */     extends OutputStream
/*     */     implements Writeable
/*     */   {
/*     */     protected String encoding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Writer writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Buffer buffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WriteableOutputStream(Writer writer, String encoding) {
/* 443 */       this.writer = writer;
/* 444 */       this.encoding = encoding;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void write(int b) throws IOException {
/* 450 */       if (this.buffer == null)
/*     */       {
/* 452 */         this.buffer = new Buffer(100);
/*     */       }
/*     */       
/* 455 */       this.buffer.write(b);
/*     */     }
/*     */ 
/*     */     
/*     */     public Writer asWriter() {
/* 460 */       return this.writer;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getEncoding() {
/* 465 */       return this.encoding;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 471 */       super.close();
/* 472 */       this.writer.close();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void flush() throws IOException {
/* 478 */       super.flush();
/* 479 */       this.buffer.flush();
/* 480 */       this.writer.flush();
/*     */     }
/*     */     
/*     */     protected class Buffer
/*     */       extends ByteArrayInputStream
/*     */     {
/*     */       protected int index;
/*     */       protected char[] characters;
/*     */       protected InputStreamReader reader;
/*     */       
/*     */       public Buffer(int size) throws IOException {
/* 491 */         super(new byte[size], 0, 0);
/* 492 */         this.characters = new char[size];
/* 493 */         this.reader = new InputStreamReader(this, URIConverter.WriteableOutputStream.this.encoding);
/*     */       }
/*     */ 
/*     */       
/*     */       public void write(int b) throws IOException {
/* 498 */         if (this.count < this.buf.length) {
/*     */           
/* 500 */           this.buf[this.count++] = (byte)b;
/*     */         }
/*     */         else {
/*     */           
/* 504 */           int readCount = this.reader.read(this.characters);
/* 505 */           if (readCount > 0)
/*     */           {
/* 507 */             URIConverter.WriteableOutputStream.this.writer.write(this.characters, 0, readCount);
/*     */           }
/* 509 */           this.count = 0;
/* 510 */           this.index = 0;
/* 511 */           this.pos = 0;
/* 512 */           write(b);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public void flush() throws IOException {
/* 518 */         int readCount = this.reader.read(this.characters);
/* 519 */         if (readCount > 0)
/*     */         {
/* 521 */           URIConverter.WriteableOutputStream.this.writer.write(this.characters, 0, readCount);
/*     */         }
/* 523 */         this.count = 0;
/* 524 */         this.index = 0;
/* 525 */         this.pos = 0;
/*     */       }
/*     */     }
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
/* 689 */   public static final URIConverter INSTANCE = (URIConverter)new ExtensibleURIConverterImpl();
/*     */   
/*     */   URI normalize(URI paramURI);
/*     */   
/*     */   Map<URI, URI> getURIMap();
/*     */   
/*     */   EList<URIHandler> getURIHandlers();
/*     */   
/*     */   URIHandler getURIHandler(URI paramURI);
/*     */   
/*     */   EList<ContentHandler> getContentHandlers();
/*     */   
/*     */   InputStream createInputStream(URI paramURI) throws IOException;
/*     */   
/*     */   InputStream createInputStream(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   OutputStream createOutputStream(URI paramURI) throws IOException;
/*     */   
/*     */   OutputStream createOutputStream(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   void delete(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   Map<String, ?> contentDescription(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*     */   
/*     */   boolean exists(URI paramURI, Map<?, ?> paramMap);
/*     */   
/*     */   Map<String, ?> getAttributes(URI paramURI, Map<?, ?> paramMap);
/*     */   
/*     */   void setAttributes(URI paramURI, Map<String, ?> paramMap, Map<?, ?> paramMap1) throws IOException;
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\URIConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */