/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigurationCache
/*     */ {
/*  37 */   public static final ConfigurationCache INSTANCE = new ConfigurationCache();
/*     */   
/*     */   protected static final int SIZE = 100;
/*     */   
/*  41 */   protected Map<EPackage, EObject> documentRoots = new HashMap<EPackage, EObject>();
/*     */   
/*  43 */   protected XMLString[] printers = new XMLString[100];
/*     */   
/*  45 */   protected XMLSaveImpl.Escape[] escapes = new XMLSaveImpl.Escape[100];
/*     */   
/*  47 */   protected int freePrinterIndex = -1;
/*     */   
/*  49 */   protected int freeEscapeIndex = -1;
/*     */   
/*  51 */   protected int currentSize = 100;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized XMLString getPrinter() {
/*  60 */     if (this.freePrinterIndex < 0)
/*     */     {
/*  62 */       return new XMLString();
/*     */     }
/*  64 */     XMLString printer = this.printers[this.freePrinterIndex];
/*  65 */     this.printers[this.freePrinterIndex--] = null;
/*  66 */     return printer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected synchronized void releasePrinter(XMLString printer) {
/*  71 */     this.freePrinterIndex++;
/*  72 */     if (this.printers.length == this.freePrinterIndex) {
/*     */       
/*  74 */       this.currentSize += 100;
/*  75 */       XMLString[] newarray = new XMLString[this.currentSize];
/*  76 */       System.arraycopy(this.printers, 0, newarray, 0, this.printers.length);
/*  77 */       this.printers = newarray;
/*     */     } 
/*  79 */     this.printers[this.freePrinterIndex] = printer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected synchronized XMLSaveImpl.Escape getEscape() {
/*  84 */     if (this.freeEscapeIndex < 0)
/*     */     {
/*  86 */       return new XMLSaveImpl.Escape();
/*     */     }
/*  88 */     XMLSaveImpl.Escape escape = this.escapes[this.freeEscapeIndex];
/*  89 */     this.escapes[this.freeEscapeIndex--] = null;
/*  90 */     return escape;
/*     */   }
/*     */ 
/*     */   
/*     */   protected synchronized void releaseEscape(XMLSaveImpl.Escape escape) {
/*  95 */     this.freeEscapeIndex++;
/*  96 */     if (this.escapes.length == this.freeEscapeIndex) {
/*     */       
/*  98 */       this.currentSize += 100;
/*  99 */       XMLSaveImpl.Escape[] newarray = new XMLSaveImpl.Escape[this.currentSize];
/* 100 */       System.arraycopy(this.escapes, 0, newarray, 0, this.escapes.length);
/* 101 */       this.escapes = newarray;
/*     */     } 
/* 103 */     this.escapes[this.freeEscapeIndex] = escape;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void release() {
/* 108 */     this.freeEscapeIndex = -1;
/* 109 */     this.freePrinterIndex = -1; int i;
/* 110 */     for (i = 0; i < this.printers.length; i++)
/*     */     {
/* 112 */       this.printers[i] = null;
/*     */     }
/* 114 */     for (i = 0; i < this.escapes.length; i++)
/*     */     {
/* 116 */       this.escapes[i] = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized EClass getDocumentRoot(EPackage ePackage) {
/* 122 */     return (EClass)this.documentRoots.get(ePackage);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void putDocumentRoot(EPackage ePackage, EClass documentRoot) {
/* 127 */     this.documentRoots.put(ePackage, documentRoot);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\ConfigurationCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */