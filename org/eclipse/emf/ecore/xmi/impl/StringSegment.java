/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.ecore.xmi.XMIPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringSegment
/*     */   extends BasicEList<StringSegment.Element>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected static final int LIST_SIZE = 100;
/*     */   protected static final int ELEMENT_SIZE = 1000;
/*     */   protected static final int BUFFER_SIZE = 8192;
/*     */   protected int segmentCapacity;
/*     */   protected byte[] outputbytes;
/*     */   protected char[] outputchars;
/*     */   protected char[] buffer;
/*     */   protected Element cursor;
/*  60 */   protected int cursorIndex = 0;
/*     */   
/*  62 */   protected String lineSeparator = System.getProperty("line.separator");
/*     */   
/*     */   protected String temporaryFileName;
/*     */   
/*     */   protected Writer temporaryFile;
/*     */   
/*     */   protected int bufferPosition;
/*     */ 
/*     */   
/*     */   public StringSegment() {
/*  72 */     this(100);
/*     */   }
/*     */ 
/*     */   
/*     */   public StringSegment(int minimumCapacity) {
/*  77 */     this(minimumCapacity, 1000);
/*     */   }
/*     */ 
/*     */   
/*     */   public StringSegment(int minimumCapacity, int segmentCapacity) {
/*  82 */     super(minimumCapacity);
/*  83 */     add(this.cursor = new Element(this.segmentCapacity = segmentCapacity));
/*  84 */     this.outputchars = new char[8192];
/*     */   }
/*     */ 
/*     */   
/*     */   public StringSegment(String temporaryFileName) {
/*  89 */     this(100, 1000);
/*  90 */     setTemporaryFileName(temporaryFileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTemporaryFileName(String tempFile) {
/*  95 */     this.temporaryFileName = tempFile;
/*  96 */     if (this.temporaryFileName != null) {
/*     */       
/*  98 */       this.buffer = new char[8192];
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.buffer = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTemporaryFileName() {
/* 108 */     return this.temporaryFileName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] newData(int capacity) {
/* 114 */     return (Object[])new Element[capacity];
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 119 */     this.bufferPosition = 0;
/* 120 */     this.cursor = (Element)this.data[0];
/* 121 */     this.cursorIndex = 0;
/* 122 */     for (int i = 0; i < this.size; i++)
/*     */     {
/* 124 */       ((Element)this.data[i]).size = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String newString) {
/* 134 */     if (this.temporaryFile != null) {
/*     */       
/* 136 */       int length = newString.length();
/* 137 */       if (length + this.bufferPosition >= this.buffer.length) {
/*     */ 
/*     */         
/*     */         try {
/* 141 */           this.temporaryFile.write(this.buffer, 0, this.bufferPosition);
/*     */         }
/* 143 */         catch (IOException exception) {
/*     */           
/* 145 */           XMIPlugin.INSTANCE.log(exception);
/*     */         } 
/* 147 */         this.bufferPosition = 0;
/* 148 */         if (length > this.buffer.length)
/*     */         {
/* 150 */           this.buffer = new char[length];
/*     */         }
/*     */       } 
/* 153 */       newString.getChars(0, length, this.buffer, this.bufferPosition);
/* 154 */       this.bufferPosition += length;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 160 */     if (this.cursor.size < this.segmentCapacity) {
/*     */       
/* 162 */       this.cursor.add(newString);
/*     */       
/*     */       return;
/*     */     } 
/* 166 */     Element oldCursor = this.cursor;
/* 167 */     int index = this.size - 1;
/* 168 */     if (this.cursorIndex < index) {
/*     */       
/* 170 */       this.cursor = (Element)this.data[++this.cursorIndex];
/* 171 */       if (this.cursor.size == 0) {
/*     */         
/* 173 */         this.cursor.add(newString);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 178 */     this.cursor = new Element(this.segmentCapacity);
/* 179 */     this.cursor.add(newString);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     if (this.data[index] == oldCursor) {
/*     */       
/* 186 */       add(this.cursor);
/* 187 */       this.cursorIndex = ++index;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 193 */       int counter = 0;
/* 194 */       while (counter < index) {
/*     */         
/* 196 */         if (this.data[counter++] == oldCursor) {
/*     */           
/* 198 */           this.cursorIndex = counter;
/* 199 */           add(this.cursorIndex, this.cursor);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLine() {
/* 208 */     add(this.lineSeparator);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object mark() {
/* 213 */     Element result = this.cursor;
/* 214 */     if (this.cursor.size == 0)
/*     */     {
/* 216 */       result.add("");
/*     */     }
/* 218 */     int i = this.size - 1;
/* 219 */     if (this.cursorIndex < i) {
/*     */       
/* 221 */       this.cursor = (Element)this.data[++this.cursorIndex];
/*     */     }
/*     */     else {
/*     */       
/* 225 */       this.cursorIndex++;
/* 226 */       this.cursor = new Element(this.segmentCapacity);
/* 227 */       add(this.cursor);
/*     */     } 
/* 229 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startFileBuffering() {
/* 234 */     if (this.temporaryFileName != null && this.temporaryFile == null) {
/*     */       
/*     */       try {
/*     */         
/* 238 */         this.temporaryFile = new OutputStreamWriter(new FileOutputStream(this.temporaryFileName), "UTF8");
/*     */       }
/* 240 */       catch (IOException iOException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetToMark(Object mark) {
/* 249 */     if (this.temporaryFile != null) {
/*     */       
/* 251 */       this.cursor.add("");
/*     */       
/*     */       try {
/* 254 */         this.temporaryFile.write(this.buffer, 0, this.bufferPosition);
/* 255 */         this.temporaryFile.close();
/*     */       }
/* 257 */       catch (IOException exception) {
/*     */         
/* 259 */         XMIPlugin.INSTANCE.log(exception);
/*     */       } 
/* 261 */       this.temporaryFile = null;
/*     */     } 
/* 263 */     this.cursor = (Element)mark;
/* 264 */     for (int i = 0; i < this.data.length; i++) {
/*     */       
/* 266 */       if (this.data[i] == this.cursor) {
/*     */         
/* 268 */         this.cursorIndex = i;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 276 */     Element[] elements = (Element[])this.data;
/* 277 */     int length = 0;
/* 278 */     for (int i = 0; i < this.size; i++) {
/*     */       
/* 280 */       Element element = elements[i];
/* 281 */       int segmentSize = element.size;
/* 282 */       for (int j = 0; j < segmentSize; j++) {
/*     */         
/* 284 */         String s = element.data[j];
/* 285 */         length += s.length();
/*     */       } 
/*     */     } 
/* 288 */     return length;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChars(char[] destination, int position) {
/* 293 */     Element[] elements = (Element[])this.data;
/* 294 */     for (int i = 0; i < this.size; i++) {
/*     */       
/* 296 */       Element element = elements[i];
/* 297 */       int segmentSize = element.size;
/* 298 */       for (int j = 0; j < segmentSize; j++) {
/*     */         
/* 300 */         String string = element.data[j];
/* 301 */         int length = string.length();
/* 302 */         string.getChars(0, length, destination, position);
/* 303 */         position += length;
/*     */       } 
/*     */     } 
/* 306 */     return position;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeAscii(OutputStream os, int flushThreshold) throws IOException {
/* 311 */     if (this.outputbytes == null)
/*     */     {
/* 313 */       this.outputbytes = new byte[8192];
/*     */     }
/* 315 */     Element[] elements = (Element[])this.data;
/* 316 */     int position = 0;
/* 317 */     int count = 0;
/*     */     
/* 319 */     for (int i = 0; i < this.size; i++) {
/*     */       
/* 321 */       Element element = elements[i];
/* 322 */       int segmentSize = element.size;
/* 323 */       for (int j = 0; j < segmentSize; j++) {
/*     */         
/* 325 */         String string = element.data[j];
/* 326 */         int length = string.length();
/* 327 */         if (length + position >= this.outputchars.length) {
/*     */           
/* 329 */           for (int k = 0; k < position; k++)
/*     */           {
/* 331 */             this.outputbytes[k] = (byte)(this.outputchars[k] & 0xFF);
/*     */           }
/* 333 */           os.write(this.outputbytes, 0, position);
/* 334 */           position = 0;
/* 335 */           if (length > this.outputchars.length) {
/*     */             
/* 337 */             this.outputchars = new char[length];
/* 338 */             this.outputbytes = new byte[length];
/*     */           } 
/*     */         } 
/* 341 */         string.getChars(0, length, this.outputchars, position);
/* 342 */         position += length;
/* 343 */         count += length;
/* 344 */         if (count > flushThreshold) {
/*     */           
/* 346 */           os.flush();
/* 347 */           count = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/* 351 */     for (int x = 0; x < position; x++)
/*     */     {
/* 353 */       this.outputbytes[x] = (byte)(this.outputchars[x] & 0xFF);
/*     */     }
/*     */     
/* 356 */     os.write(this.outputbytes, 0, position);
/*     */     
/* 358 */     String temporaryFileName = this.temporaryFileName;
/* 359 */     if (temporaryFileName != null) {
/*     */       
/* 361 */       InputStream inputStream = new FileInputStream(temporaryFileName);
/* 362 */       for (int length = inputStream.read(this.outputbytes, 0, this.outputbytes.length); length > 0; length = inputStream.read(
/* 363 */           this.outputbytes, 
/* 364 */           0, 
/* 365 */           this.outputbytes.length)) {
/*     */         
/* 367 */         os.write(this.outputbytes, 0, length);
/* 368 */         count += length;
/* 369 */         if (count > flushThreshold) {
/*     */           
/* 371 */           os.flush();
/* 372 */           count = 0;
/*     */         } 
/*     */       } 
/* 375 */       inputStream.close();
/* 376 */       (new File(temporaryFileName)).delete();
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
/*     */   @Deprecated
/*     */   public void write(OutputStreamWriter os, int flushThreshold) throws IOException {
/* 389 */     write(os, flushThreshold);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(Writer os, int flushThreshold) throws IOException {
/* 394 */     Element[] elements = (Element[])this.data;
/* 395 */     int position = 0;
/* 396 */     int count = 0;
/* 397 */     for (int i = 0; i < this.size; i++) {
/*     */       
/* 399 */       Element element = elements[i];
/* 400 */       int segmentSize = element.size;
/* 401 */       for (int j = 0; j < segmentSize; j++) {
/*     */         
/* 403 */         String string = element.data[j];
/* 404 */         int length = string.length();
/* 405 */         if (length + position >= this.outputchars.length) {
/*     */           
/* 407 */           os.write(this.outputchars, 0, position);
/* 408 */           position = 0;
/* 409 */           if (length > this.outputchars.length)
/*     */           {
/* 411 */             this.outputchars = new char[length];
/*     */           }
/*     */         } 
/* 414 */         string.getChars(0, length, this.outputchars, position);
/* 415 */         position += length;
/* 416 */         count += length;
/* 417 */         if (count > flushThreshold) {
/*     */           
/* 419 */           os.flush();
/* 420 */           count = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/* 424 */     os.write(this.outputchars, 0, position);
/*     */     
/* 426 */     String temporaryFileName = this.temporaryFileName;
/* 427 */     if (temporaryFileName != null) {
/*     */       
/* 429 */       InputStreamReader reader = new InputStreamReader(new FileInputStream(temporaryFileName), "UTF8");
/* 430 */       for (int length = reader.read(this.outputchars, 0, this.outputchars.length); length > 0; length = reader.read(
/* 431 */           this.outputchars, 
/* 432 */           0, 
/* 433 */           this.outputchars.length)) {
/*     */         
/* 435 */         os.write(this.outputchars, 0, length);
/* 436 */         count += length;
/* 437 */         if (count > flushThreshold) {
/*     */           
/* 439 */           os.flush();
/* 440 */           count = 0;
/*     */         } 
/*     */       } 
/* 443 */       reader.close();
/* 444 */       (new File(temporaryFileName)).delete();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class Element
/*     */   {
/*     */     int size;
/*     */     
/*     */     String[] data;
/*     */ 
/*     */     
/*     */     Element(int capacity) {
/* 457 */       this.data = new String[capacity];
/*     */     }
/*     */ 
/*     */     
/*     */     void add(String newString) {
/* 462 */       this.data[this.size++] = newString;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<Element> iterator() {
/* 471 */     return new SegmentIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<Element> listIterator() {
/* 479 */     return new SegmentIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<String> stringIterator() {
/* 484 */     return new SegmentIterator();
/*     */   }
/*     */   
/*     */   protected class SegmentIterator
/*     */     implements ListIterator<String> {
/* 489 */     protected int outerIndex = 0;
/*     */     
/* 491 */     protected int innerIndex = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 500 */       return !(this.outerIndex >= StringSegment.this.size - 1 && (this.outerIndex != StringSegment.this.size - 1 || this.innerIndex >= ((StringSegment.Element)StringSegment.this.data[this.outerIndex]).size));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasPrevious() {
/* 505 */       return !(this.outerIndex <= 0 && this.innerIndex <= 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public String next() {
/* 510 */       StringSegment.Element element = (StringSegment.Element)StringSegment.this.data[this.outerIndex];
/* 511 */       if (this.innerIndex < element.size)
/*     */       {
/* 513 */         return element.data[this.innerIndex++];
/*     */       }
/*     */ 
/*     */       
/* 517 */       this.innerIndex = 1;
/* 518 */       return ((StringSegment.Element)StringSegment.this.data[++this.outerIndex]).data[0];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String previous() {
/* 524 */       if (this.innerIndex > 0)
/*     */       {
/* 526 */         return ((StringSegment.Element)StringSegment.this.data[this.outerIndex]).data[--this.innerIndex];
/*     */       }
/*     */ 
/*     */       
/* 530 */       StringSegment.Element element = (StringSegment.Element)StringSegment.this.data[--this.outerIndex];
/* 531 */       this.innerIndex = element.size - 1;
/* 532 */       return element.data[this.innerIndex];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void add(String newElement) {
/* 538 */       throw new UnsupportedOperationException(SegmentIterator.class.toString());
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 543 */       throw new UnsupportedOperationException(SegmentIterator.class.toString());
/*     */     }
/*     */ 
/*     */     
/*     */     public void set(String newElement) {
/* 548 */       throw new UnsupportedOperationException(SegmentIterator.class.toString());
/*     */     }
/*     */ 
/*     */     
/*     */     public int nextIndex() {
/* 553 */       throw new UnsupportedOperationException(SegmentIterator.class.toString());
/*     */     }
/*     */ 
/*     */     
/*     */     public int previousIndex() {
/* 558 */       throw new UnsupportedOperationException(SegmentIterator.class.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\StringSegment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */