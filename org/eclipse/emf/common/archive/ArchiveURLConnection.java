/*     */ package org.eclipse.emf.common.archive;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArchiveURLConnection
/*     */   extends URLConnection
/*     */ {
/*     */   protected String urlString;
/*     */   
/*     */   public ArchiveURLConnection(URL url) {
/*  87 */     super(url);
/*  88 */     this.urlString = url.toString();
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
/*     */   protected ArchiveURLConnection(String url) {
/* 101 */     super(null);
/* 102 */     this.urlString = url;
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
/*     */   protected boolean emulateArchiveScheme() {
/* 124 */     return false;
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
/*     */   protected boolean useZipFile() {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void connect() throws IOException {
/* 146 */     this.connected = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getNestedURL() throws IOException {
/* 153 */     int archiveSeparator = this.urlString.indexOf("!/");
/* 154 */     if (archiveSeparator < 0)
/*     */     {
/* 156 */       throw new MalformedURLException("missing archive separators " + this.urlString);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 161 */     int start = this.urlString.indexOf(':') + 1;
/* 162 */     if (start > this.urlString.length() || this.urlString.charAt(start) == '/')
/*     */     {
/*     */       
/* 165 */       throw new IllegalArgumentException(
/* 166 */           "archive protocol must be immediately followed by another URL protocol " + this.urlString);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 171 */     for (int i = start, end = this.urlString.indexOf("/") - 1; (i = this.urlString.indexOf(":", i)) < end; ) {
/*     */       
/* 173 */       if (emulateArchiveScheme()) {
/*     */ 
/*     */ 
/*     */         
/* 177 */         start = ++i;
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 183 */       archiveSeparator = this.urlString.indexOf("!/", archiveSeparator + 2);
/* 184 */       if (archiveSeparator < 0)
/*     */       {
/* 186 */         throw new MalformedURLException("too few archive separators " + this.urlString);
/*     */       }
/* 188 */       i++;
/*     */     } 
/*     */ 
/*     */     
/* 192 */     return this.urlString.substring(start, archiveSeparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() throws IOException {
/*     */     InputStream inputStream;
/* 204 */     String nestedURL = getNestedURL();
/*     */ 
/*     */ 
/*     */     
/* 208 */     int archiveSeparator = this.urlString.indexOf(nestedURL) + nestedURL.length();
/* 209 */     int nextArchiveSeparator = this.urlString.indexOf("!/", archiveSeparator + 2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     ZipEntry inputZipEntry = null;
/* 215 */     if (!useZipFile() || !nestedURL.startsWith("file:")) {
/*     */ 
/*     */ 
/*     */       
/* 219 */       inputStream = createInputStream(nestedURL);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 225 */       String entry = 
/* 226 */         URI.decode((nextArchiveSeparator < 0) ? 
/* 227 */           this.urlString.substring(archiveSeparator + 2) : 
/* 228 */           this.urlString.substring(archiveSeparator + 2, nextArchiveSeparator));
/*     */ 
/*     */ 
/*     */       
/* 232 */       archiveSeparator = nextArchiveSeparator;
/* 233 */       nextArchiveSeparator = this.urlString.indexOf("!/", archiveSeparator + 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 239 */       final ZipFile zipFile = new ZipFile(URI.decode(nestedURL.substring(5)));
/* 240 */       inputZipEntry = zipFile.getEntry(entry);
/* 241 */       InputStream zipEntryInputStream = (inputZipEntry == null) ? null : zipFile.getInputStream(inputZipEntry);
/* 242 */       if (zipEntryInputStream == null) {
/*     */ 
/*     */         
/*     */         try {
/* 246 */           zipFile.close();
/*     */         }
/* 248 */         catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */         
/* 252 */         throw new IOException("Archive entry not found " + this.urlString);
/*     */       } 
/* 254 */       inputStream = 
/* 255 */         new FilterInputStream(zipEntryInputStream)
/*     */         {
/*     */           
/*     */           public void close() throws IOException
/*     */           {
/* 260 */             super.close();
/* 261 */             zipFile.close();
/*     */           }
/*     */         };
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 269 */     while (archiveSeparator > 0) {
/*     */       
/* 271 */       inputZipEntry = null;
/*     */ 
/*     */ 
/*     */       
/* 275 */       String entry = 
/* 276 */         URI.decode((nextArchiveSeparator < 0) ? 
/* 277 */           this.urlString.substring(archiveSeparator + 2) : 
/* 278 */           this.urlString.substring(archiveSeparator + 2, nextArchiveSeparator));
/*     */ 
/*     */ 
/*     */       
/* 282 */       ZipInputStream zipInputStream = new ZipInputStream(inputStream); while (true) {
/* 283 */         if (zipInputStream.available() >= 0) {
/*     */           
/* 285 */           ZipEntry zipEntry = zipInputStream.getNextEntry();
/* 286 */           if (zipEntry != null) {
/*     */ 
/*     */ 
/*     */             
/* 290 */             if (entry.equals(zipEntry.getName())) {
/*     */               
/* 292 */               inputZipEntry = zipEntry;
/* 293 */               inputStream = zipInputStream;
/*     */ 
/*     */ 
/*     */               
/* 297 */               archiveSeparator = nextArchiveSeparator;
/* 298 */               nextArchiveSeparator = this.urlString.indexOf("!/", archiveSeparator + 2); break;
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */         } 
/* 303 */         zipInputStream.close();
/* 304 */         throw new IOException("Archive entry not found " + this.urlString);
/*     */       } 
/*     */     } 
/* 307 */     return yield(inputZipEntry, inputStream);
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream yield(ZipEntry zipEntry, InputStream inputStream) throws IOException {
/* 312 */     return inputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream createInputStream(String nestedURL) throws IOException {
/* 322 */     return (new URL(nestedURL)).openStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() throws IOException {
/* 332 */     return getOutputStream(false, -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete() throws IOException {
/* 337 */     getOutputStream(true, -1L).close();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeStamp(long timeStamp) throws IOException {
/* 342 */     getOutputStream(false, timeStamp).close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private OutputStream getOutputStream(boolean delete, long timeStamp) throws IOException {
/* 349 */     final String nestedURL = getNestedURL();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     final File tempFile = File.createTempFile("Archive", "zip");
/* 355 */     tempFile.deleteOnExit();
/*     */ 
/*     */ 
/*     */     
/* 359 */     InputStream sourceInputStream = null;
/* 360 */     OutputStream tempOutputStream = null;
/*     */     
/*     */     try {
/*     */       ZipOutputStream zipOutputStream;
/*     */       ZipEntry outputZipEntry;
/* 365 */       tempOutputStream = new FileOutputStream(tempFile);
/*     */       
/*     */       try {
/* 368 */         sourceInputStream = createInputStream(nestedURL);
/*     */       }
/* 370 */       catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 377 */       OutputStream outputStream = tempOutputStream;
/* 378 */       InputStream inputStream = sourceInputStream;
/*     */ 
/*     */ 
/*     */       
/* 382 */       int archiveSeparator = this.urlString.indexOf(nestedURL) + nestedURL.length();
/* 383 */       int nextArchiveSeparator = this.urlString.indexOf("!/", archiveSeparator + 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 391 */       final byte[] bytes = new byte[4096];
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 396 */       boolean found = false;
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 401 */         String entry = 
/* 402 */           URI.decode((nextArchiveSeparator < 0) ? 
/* 403 */             this.urlString.substring(archiveSeparator + 2) : 
/* 404 */             this.urlString.substring(archiveSeparator + 2, nextArchiveSeparator));
/*     */ 
/*     */ 
/*     */         
/* 408 */         zipOutputStream = null;
/*     */ 
/*     */ 
/*     */         
/* 412 */         ZipInputStream zipInputStream = (inputStream == null) ? null : new ZipInputStream(inputStream);
/* 413 */         inputStream = zipInputStream;
/*     */ 
/*     */ 
/*     */         
/* 417 */         while (zipInputStream != null && zipInputStream.available() >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 422 */           ZipEntry zipEntry = zipInputStream.getNextEntry();
/* 423 */           if (zipEntry == null) {
/*     */             break;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 429 */           boolean match = entry.equals(zipEntry.getName());
/* 430 */           if (!found)
/*     */           {
/* 432 */             found = (match && nextArchiveSeparator < 0);
/*     */           }
/* 434 */           if (timeStamp != -1L || !match) {
/*     */             
/* 436 */             if (zipOutputStream == null) {
/*     */               
/* 438 */               zipOutputStream = new ZipOutputStream(outputStream);
/* 439 */               outputStream = zipOutputStream;
/*     */             } 
/*     */ 
/*     */             
/* 443 */             if (timeStamp != -1L && match && nextArchiveSeparator < 0)
/*     */             {
/* 445 */               zipEntry.setTime(timeStamp);
/*     */             }
/* 447 */             zipOutputStream.putNextEntry(zipEntry); int size;
/* 448 */             while ((size = zipInputStream.read(bytes, 0, bytes.length)) > -1)
/*     */             {
/* 450 */               zipOutputStream.write(bytes, 0, size);
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 458 */         archiveSeparator = nextArchiveSeparator;
/* 459 */         nextArchiveSeparator = this.urlString.indexOf("!/", archiveSeparator + 2);
/*     */         
/* 461 */         if ((delete || timeStamp != -1L) && archiveSeparator < 0) {
/*     */           
/* 463 */           if (!found)
/*     */           {
/* 465 */             throw new IOException("Archive entry not found " + this.urlString);
/*     */           }
/*     */ 
/*     */           
/* 469 */           ZipEntry zipEntry = null;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */         
/* 476 */         outputZipEntry = new ZipEntry(entry);
/* 477 */         if (zipOutputStream == null) {
/*     */           
/* 479 */           zipOutputStream = new ZipOutputStream(outputStream);
/* 480 */           outputStream = zipOutputStream;
/*     */         } 
/* 482 */         zipOutputStream.putNextEntry(outputZipEntry);
/* 483 */         if (archiveSeparator > 0) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 496 */       tempOutputStream = null;
/*     */ 
/*     */ 
/*     */       
/* 500 */       final boolean deleteRequired = (sourceInputStream != null);
/* 501 */       FilterOutputStream result = 
/* 502 */         new FilterOutputStream((zipOutputStream == null) ? outputStream : zipOutputStream)
/*     */         {
/*     */           protected boolean isClosed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void close() throws IOException {
/* 511 */             if (!this.isClosed) {
/*     */               
/* 513 */               this.isClosed = true;
/*     */ 
/*     */ 
/*     */               
/* 517 */               super.close();
/*     */               
/* 519 */               boolean useRenameTo = nestedURL.startsWith("file:");
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 524 */               if (useRenameTo) {
/*     */                 
/* 526 */                 File targetFile = new File(URI.decode(nestedURL.substring(5)));
/* 527 */                 if (deleteRequired && !targetFile.delete())
/*     */                 {
/* 529 */                   throw new IOException("cannot delete " + targetFile.getPath());
/*     */                 }
/* 531 */                 if (!tempFile.renameTo(targetFile))
/*     */                 {
/* 533 */                   useRenameTo = false;
/*     */                 }
/*     */               } 
/* 536 */               if (!useRenameTo) {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 541 */                 InputStream inputStream = null;
/* 542 */                 OutputStream outputStream = null;
/*     */                 
/*     */                 try {
/* 545 */                   inputStream = new FileInputStream(tempFile);
/* 546 */                   outputStream = ArchiveURLConnection.this.createOutputStream(nestedURL); int size;
/* 547 */                   while ((size = inputStream.read(bytes, 0, bytes.length)) > -1)
/*     */                   {
/* 549 */                     outputStream.write(bytes, 0, size);
/*     */                   
/*     */                   }
/*     */                 
/*     */                 }
/*     */                 finally {
/*     */                   
/* 556 */                   if (inputStream != null)
/*     */                   {
/* 558 */                     inputStream.close();
/*     */                   }
/* 560 */                   if (outputStream != null)
/*     */                   {
/* 562 */                     outputStream.close();
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 568 */               tempFile.delete();
/*     */             } 
/*     */           }
/*     */         };
/* 572 */       return (outputZipEntry == null) ? result : yield(outputZipEntry, result);
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 578 */       if (tempOutputStream != null)
/*     */       {
/* 580 */         tempOutputStream.close();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 585 */       if (sourceInputStream != null)
/*     */       {
/* 587 */         sourceInputStream.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected OutputStream yield(ZipEntry zipEntry, OutputStream outputStream) throws IOException {
/* 594 */     return outputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected OutputStream createOutputStream(String nestedURL) throws IOException {
/* 605 */     URL url = new URL(nestedURL);
/* 606 */     URLConnection urlConnection = url.openConnection();
/* 607 */     urlConnection.setDoOutput(true);
/* 608 */     return urlConnection.getOutputStream();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\archive\ArchiveURLConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */