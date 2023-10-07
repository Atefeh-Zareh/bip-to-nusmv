/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLString
/*     */   extends StringSegment
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected List<String> elementNames;
/*     */   protected List<Boolean> mixed;
/*     */   protected boolean isUnformatted;
/*     */   protected boolean isMixed;
/*     */   protected List<String> indents;
/*     */   protected int depth;
/*     */   protected int lineWidth;
/*     */   protected int markedLineWidth;
/*     */   protected int currentLineWidth;
/*     */   protected boolean lastElementIsStart;
/*     */   protected Object firstElementMark;
/*     */   protected boolean seenRoot;
/*     */   protected boolean saveDoctype;
/*     */   protected Object docTypeMark;
/*     */   protected String docTypeName;
/*     */   protected String publicId;
/*     */   protected String systemId;
/*     */   
/*     */   public XMLString() {
/*  74 */     this(80);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLString(int lineWidth) {
/*  79 */     this(lineWidth, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLString(int lineWidth, String temporaryFileName) {
/*  84 */     super(temporaryFileName);
/*     */     
/*  86 */     this.lineWidth = lineWidth;
/*  87 */     this.elementNames = (List<String>)new BasicEList();
/*  88 */     this.mixed = (List<Boolean>)new BasicEList();
/*  89 */     this.indents = (List<String>)new BasicEList();
/*  90 */     this.indents.add("");
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLString(int lineWidth, String publicId, String systemId) {
/*  95 */     this(lineWidth, publicId, systemId, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLString(int lineWidth, String publicId, String systemId, String temporaryFileName) {
/* 100 */     this(lineWidth, temporaryFileName);
/* 101 */     if (publicId != null || systemId != null) {
/*     */       
/* 103 */       this.saveDoctype = true;
/* 104 */       this.publicId = publicId;
/* 105 */       this.systemId = systemId;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLineWidth(int lineWidth) {
/* 111 */     this.lineWidth = lineWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset(String publicId, String systemId, int lineWidth, String temporaryFileName) {
/* 116 */     reset();
/* 117 */     setTemporaryFileName(temporaryFileName);
/* 118 */     this.elementNames.clear();
/* 119 */     this.mixed.clear();
/* 120 */     this.indents.clear();
/* 121 */     this.indents.add("");
/* 122 */     if (publicId != null || systemId != null) {
/*     */       
/* 124 */       this.saveDoctype = true;
/* 125 */       this.publicId = publicId;
/* 126 */       this.systemId = systemId;
/*     */     }
/*     */     else {
/*     */       
/* 130 */       this.saveDoctype = false;
/*     */     } 
/* 132 */     this.seenRoot = false;
/* 133 */     this.lastElementIsStart = false;
/* 134 */     this.isMixed = false;
/* 135 */     this.isUnformatted = false;
/* 136 */     this.depth = 0;
/* 137 */     this.markedLineWidth = 0;
/* 138 */     this.lineWidth = lineWidth;
/* 139 */     this.currentLineWidth = 0;
/* 140 */     this.firstElementMark = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startElement(String name) {
/* 145 */     if (this.lastElementIsStart)
/*     */     {
/* 147 */       closeStartElement();
/*     */     }
/* 149 */     this.elementNames.add(name);
/* 150 */     if (name != null) {
/*     */       
/* 152 */       saveDoctype(name);
/* 153 */       this.depth++;
/* 154 */       if (!this.isMixed)
/*     */       {
/* 156 */         add(getElementIndent());
/*     */       }
/* 158 */       add("<");
/* 159 */       add(name);
/* 160 */       if (this.firstElementMark == null) {
/*     */         
/* 162 */         this.firstElementMark = mark();
/* 163 */         startFileBuffering();
/*     */       } 
/* 165 */       this.lastElementIsStart = true;
/*     */     }
/* 167 */     else if (!this.isMixed) {
/*     */       
/* 169 */       add(getElementIndent(1));
/*     */     } 
/*     */     
/* 172 */     this.mixed.add(this.isMixed ? Boolean.TRUE : Boolean.FALSE);
/* 173 */     this.isMixed = this.isUnformatted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveNilElement(String name) {
/* 178 */     if (this.lastElementIsStart)
/*     */     {
/* 180 */       closeStartElement();
/*     */     }
/* 182 */     saveDoctype(name);
/*     */     
/* 184 */     this.depth++;
/* 185 */     if (!this.isMixed)
/*     */     {
/* 187 */       add(getElementIndent());
/*     */     }
/* 189 */     add("<");
/* 190 */     add(name);
/* 191 */     if (this.firstElementMark == null) {
/*     */       
/* 193 */       this.firstElementMark = mark();
/* 194 */       startFileBuffering();
/*     */     } 
/* 196 */     if (this.currentLineWidth > this.lineWidth) {
/*     */       
/* 198 */       addLine();
/* 199 */       add(getAttributeIndent());
/*     */     }
/*     */     else {
/*     */       
/* 203 */       add(" ");
/*     */     } 
/* 205 */     add("xsi:nil=\"true\"/>");
/*     */     
/* 207 */     this.depth--;
/*     */     
/* 209 */     if (!this.isUnformatted && !this.isMixed)
/*     */     {
/* 211 */       addLine();
/*     */     }
/* 213 */     this.lastElementIsStart = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveDataValueElement(String name, String content) {
/* 218 */     if (this.lastElementIsStart)
/*     */     {
/* 220 */       closeStartElement();
/*     */     }
/* 222 */     saveDoctype(name);
/*     */     
/* 224 */     this.depth++;
/* 225 */     if (!this.isMixed)
/*     */     {
/* 227 */       add(getElementIndent());
/*     */     }
/* 229 */     add("<");
/* 230 */     add(name);
/* 231 */     if (this.firstElementMark == null) {
/*     */       
/* 233 */       this.firstElementMark = mark();
/* 234 */       startFileBuffering();
/*     */     } 
/*     */     
/* 237 */     add(">");
/* 238 */     add(content);
/* 239 */     add("</");
/*     */     
/* 241 */     this.depth--;
/* 242 */     add(name);
/* 243 */     add(">");
/* 244 */     if (!this.isUnformatted && !this.isMixed)
/*     */     {
/* 246 */       addLine();
/*     */     }
/* 248 */     this.lastElementIsStart = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntity(String name, String value) {
/* 253 */     if (this.docTypeMark != null) {
/*     */       
/* 255 */       resetToMark(this.docTypeMark);
/* 256 */       if (this.saveDoctype) {
/*     */         
/* 258 */         add(" [");
/* 259 */         addLine();
/* 260 */         this.docTypeMark = mark();
/* 261 */         add("]");
/*     */       }
/*     */       else {
/*     */         
/* 265 */         add("<!DOCTYPE ");
/* 266 */         add(this.docTypeName);
/* 267 */         add(" [");
/* 268 */         addLine();
/* 269 */         this.docTypeMark = mark();
/* 270 */         add("]>");
/* 271 */         addLine();
/*     */       } 
/* 273 */       resetToMark(this.docTypeMark);
/* 274 */       this.docTypeMark = null;
/*     */     } 
/*     */     
/* 277 */     add("<!ENTITY ");
/* 278 */     add(name);
/* 279 */     add(" \"");
/* 280 */     add(value);
/* 281 */     add("\">");
/* 282 */     addLine();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void saveDoctype(String name) {
/* 287 */     if (!this.seenRoot) {
/*     */       
/* 289 */       this.seenRoot = true;
/*     */       
/* 291 */       if (this.saveDoctype) {
/*     */         
/* 293 */         add("<!DOCTYPE ");
/* 294 */         add(name);
/* 295 */         if (this.publicId != null) {
/*     */           
/* 297 */           add(" PUBLIC \"");
/* 298 */           add(this.publicId);
/* 299 */           add("\" ");
/* 300 */           add("\"");
/* 301 */           add(this.systemId);
/* 302 */           add("\"");
/* 303 */           this.docTypeMark = mark();
/* 304 */           mark();
/* 305 */           add(">");
/*     */         }
/* 307 */         else if (this.systemId != null) {
/*     */           
/* 309 */           add(" SYSTEM \"");
/* 310 */           add(this.systemId);
/* 311 */           add("\"");
/* 312 */           this.docTypeMark = mark();
/* 313 */           mark();
/* 314 */           add(">");
/*     */         }
/*     */         else {
/*     */           
/* 318 */           this.docTypeMark = mark();
/* 319 */           mark();
/* 320 */           add(">");
/*     */         } 
/*     */         
/* 323 */         addLine();
/*     */       }
/*     */       else {
/*     */         
/* 327 */         this.docTypeMark = mark();
/* 328 */         this.docTypeName = name;
/* 329 */         mark();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMixed(boolean isMixed) {
/* 336 */     this.isMixed = isMixed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnformatted(boolean isUnformatted) {
/* 341 */     this.isUnformatted = isUnformatted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAttribute(String name, String value) {
/* 346 */     if (this.currentLineWidth > this.lineWidth) {
/*     */       
/* 348 */       addLine();
/* 349 */       add(getAttributeIndent());
/*     */     }
/*     */     else {
/*     */       
/* 353 */       add(" ");
/*     */     } 
/* 355 */     add(name);
/* 356 */     add("=\"");
/* 357 */     add(value);
/* 358 */     add("\"");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAttributeNS(String prefix, String localName, String value) {
/* 363 */     if (this.currentLineWidth > this.lineWidth) {
/*     */       
/* 365 */       addLine();
/* 366 */       add(getAttributeIndent());
/*     */     }
/*     */     else {
/*     */       
/* 370 */       add(" ");
/*     */     } 
/* 372 */     add(prefix);
/* 373 */     add(":");
/* 374 */     add(localName);
/* 375 */     add("=\"");
/* 376 */     add(value);
/* 377 */     add("\"");
/*     */   }
/*     */ 
/*     */   
/*     */   public void startAttribute(String name) {
/* 382 */     if (this.currentLineWidth > this.lineWidth) {
/*     */       
/* 384 */       addLine();
/* 385 */       add(getAttributeIndent());
/*     */     }
/*     */     else {
/*     */       
/* 389 */       add(" ");
/*     */     } 
/* 391 */     add(name);
/* 392 */     add("=\"");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAttributeContent(String content) {
/* 397 */     add(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endAttribute() {
/* 402 */     add("\"");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void closeStartElement() {
/* 407 */     add(">");
/* 408 */     if (!this.isMixed)
/*     */     {
/* 410 */       addLine();
/*     */     }
/* 412 */     this.lastElementIsStart = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void endEmptyElement() {
/* 417 */     removeLast();
/* 418 */     add("/>");
/* 419 */     if (!this.isMixed)
/*     */     {
/* 421 */       addLine();
/*     */     }
/* 423 */     this.lastElementIsStart = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void endContentElement(String content) {
/* 428 */     add(">");
/* 429 */     add(content);
/* 430 */     add("</");
/* 431 */     String name = removeLast();
/* 432 */     add(name);
/* 433 */     add(">");
/* 434 */     if (!this.isMixed)
/*     */     {
/* 436 */       addLine();
/*     */     }
/* 438 */     this.lastElementIsStart = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void endElement() {
/* 443 */     if (this.lastElementIsStart) {
/*     */       
/* 445 */       endEmptyElement();
/*     */     }
/*     */     else {
/*     */       
/* 449 */       boolean wasMixed = this.isMixed;
/* 450 */       String name = removeLast();
/* 451 */       if (name != null) {
/*     */         
/* 453 */         if (!wasMixed)
/*     */         {
/* 455 */           add(getElementIndent(1));
/*     */         }
/* 457 */         add("</");
/* 458 */         add(name);
/* 459 */         add(">");
/*     */         
/* 461 */         if (!this.isMixed)
/*     */         {
/* 463 */           addLine();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String removeLast() {
/* 471 */     int end = this.elementNames.size();
/* 472 */     this.isMixed = ((Boolean)this.mixed.remove(end - 1)).booleanValue();
/* 473 */     String result = this.elementNames.remove(end - 1);
/* 474 */     if (result != null)
/*     */     {
/* 476 */       this.depth--;
/*     */     }
/* 478 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getElementIndent() {
/* 483 */     return getElementIndent(0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getElementIndent(int extra) {
/* 488 */     int nesting = this.depth + extra - 1;
/* 489 */     for (int i = this.indents.size() - 1; i < nesting; i++)
/*     */     {
/* 491 */       this.indents.add(String.valueOf(this.indents.get(i)) + "  ");
/*     */     }
/* 493 */     return this.indents.get(nesting);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getAttributeIndent() {
/* 498 */     int nesting = this.depth + 1;
/* 499 */     for (int i = this.indents.size() - 1; i < nesting; i++)
/*     */     {
/* 501 */       this.indents.add(String.valueOf(this.indents.get(i)) + "  ");
/*     */     }
/* 503 */     return this.indents.get(nesting);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addText(String newString) {
/* 508 */     if (this.lastElementIsStart)
/*     */     {
/* 510 */       closeStartElement();
/*     */     }
/*     */     
/* 513 */     if (this.lineWidth != Integer.MAX_VALUE) {
/*     */       
/* 515 */       this.currentLineWidth += newString.length();
/* 516 */       for (int i = newString.length() - 1; i >= 0; i--) {
/*     */         
/* 518 */         switch (newString.charAt(i)) {
/*     */ 
/*     */           
/*     */           case '\n':
/*     */           case '\r':
/* 523 */             this.currentLineWidth = newString.length() - i;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 530 */     super.add(newString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCDATA(String newString) {
/* 535 */     if (this.lastElementIsStart)
/*     */     {
/* 537 */       closeStartElement();
/*     */     }
/*     */     
/* 540 */     add("<![CDATA[");
/* 541 */     if (this.lineWidth != Integer.MAX_VALUE) {
/*     */       
/* 543 */       this.currentLineWidth += newString.length();
/* 544 */       for (int i = newString.length() - 1; i >= 0; i--) {
/*     */         
/* 546 */         switch (newString.charAt(i)) {
/*     */ 
/*     */           
/*     */           case '\n':
/*     */           case '\r':
/* 551 */             this.currentLineWidth = newString.length() - i;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 558 */     super.add(newString);
/* 559 */     add("]]>");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addComment(String newString) {
/* 564 */     if (this.lastElementIsStart)
/*     */     {
/* 566 */       closeStartElement();
/*     */     }
/*     */     
/* 569 */     if (this.firstElementMark != null && (this.elementNames.isEmpty() || (this.elementNames.size() == 1 && this.elementNames.get(0) == null)))
/*     */     {
/* 571 */       addLine();
/*     */     }
/* 573 */     add("<!--");
/* 574 */     if (this.lineWidth != Integer.MAX_VALUE) {
/*     */       
/* 576 */       this.currentLineWidth += newString.length();
/* 577 */       for (int i = newString.length() - 1; i >= 0; i--) {
/*     */         
/* 579 */         switch (newString.charAt(i)) {
/*     */ 
/*     */           
/*     */           case '\n':
/*     */           case '\r':
/* 584 */             this.currentLineWidth = newString.length() - i;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 591 */     super.add(newString);
/* 592 */     add("-->");
/* 593 */     if (this.firstElementMark == null)
/*     */     {
/* 595 */       addLine();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addProcessingInstruction(String target, String data) {
/* 601 */     if (this.lastElementIsStart)
/*     */     {
/* 603 */       closeStartElement();
/*     */     }
/*     */     
/* 606 */     if (this.firstElementMark != null && (this.elementNames.isEmpty() || (this.elementNames.size() == 1 && this.elementNames.get(0) == null)))
/*     */     {
/* 608 */       addLine();
/*     */     }
/* 610 */     add("<?");
/* 611 */     add((target == null) ? "_" : target);
/*     */     
/* 613 */     if (data != null) {
/*     */       
/* 615 */       add(" ");
/* 616 */       if (this.lineWidth != Integer.MAX_VALUE) {
/*     */         
/* 618 */         this.currentLineWidth += data.length();
/*     */         
/* 620 */         for (int i = data.length() - 1; i >= 0; i--) {
/*     */           
/* 622 */           switch (data.charAt(i)) {
/*     */ 
/*     */             
/*     */             case '\n':
/*     */             case '\r':
/* 627 */               this.currentLineWidth = data.length() - i;
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/*     */       } 
/* 633 */       super.add(data);
/*     */     } 
/* 635 */     add("?>");
/* 636 */     if (this.firstElementMark == null)
/*     */     {
/* 638 */       addLine();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String newString) {
/* 647 */     if (this.lineWidth != Integer.MAX_VALUE)
/*     */     {
/* 649 */       this.currentLineWidth += newString.length();
/*     */     }
/* 651 */     super.add(newString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLine() {
/* 657 */     super.addLine();
/* 658 */     this.currentLineWidth = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mark() {
/* 664 */     this.markedLineWidth = this.currentLineWidth;
/* 665 */     this.currentLineWidth = this.lineWidth - 2;
/* 666 */     return super.mark();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetToMark(Object mark) {
/* 672 */     if (mark == null)
/*     */     {
/* 674 */       mark = this.firstElementMark;
/*     */     }
/*     */     
/* 677 */     super.resetToMark(mark);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 682 */     this.depth++;
/*     */     
/* 684 */     this.currentLineWidth = this.markedLineWidth;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */