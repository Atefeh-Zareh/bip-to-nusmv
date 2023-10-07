/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.xmi.XMIResource;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLSave;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMIResourceImpl
/*     */   extends XMLResourceImpl
/*     */   implements XMIResource
/*     */ {
/*  31 */   String version = "2.0";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMIResourceImpl() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMIResourceImpl(URI uri) {
/*  47 */     super(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLHelper createXMLHelper() {
/*  53 */     return new XMIHelperImpl(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLLoad createXMLLoad() {
/*  59 */     return new XMILoadImpl(createXMLHelper());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLSave createXMLSave() {
/*  65 */     return new XMISaveImpl(createXMLHelper());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean useIDs() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXMIVersion() {
/*  76 */     return this.version;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXMIVersion(String version) {
/*  81 */     this.version = version;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXMINamespace() {
/*  86 */     if (this.version.equals("2.0"))
/*     */     {
/*  88 */       return "http://www.omg.org/XMI";
/*     */     }
/*     */ 
/*     */     
/*  92 */     return "http://schema.omg.org/spec/XMI/" + this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMINamespace(String namespace) {
/*  98 */     if (namespace.startsWith("http://schema.omg.org/spec/XMI/")) {
/*     */       
/* 100 */       setXMIVersion(namespace.substring("http://schema.omg.org/spec/XMI/".length()));
/*     */     }
/* 102 */     else if (namespace.equals("http://www.omg.org/XMI")) {
/*     */       
/* 104 */       setXMIVersion("2.0");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 109 */       throw new IllegalArgumentException("Invalid XMI namespace: '" + namespace + "'");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMIResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */