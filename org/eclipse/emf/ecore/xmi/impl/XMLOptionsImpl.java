/*    */ package org.eclipse.emf.ecore.xmi.impl;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.xmi.EcoreBuilder;
/*    */ import org.eclipse.emf.ecore.xmi.XMLOptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLOptionsImpl
/*    */   implements XMLOptions
/*    */ {
/*    */   protected EcoreBuilder ecoreBuilder;
/*    */   protected Map<String, URI> externalSchemaLocation;
/*    */   protected boolean anyXML;
/*    */   protected boolean processSchemaLocations;
/*    */   
/*    */   public EcoreBuilder getEcoreBuilder() {
/* 39 */     return this.ecoreBuilder;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, URI> getExternalSchemaLocations() {
/* 44 */     return this.externalSchemaLocation;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProcessAnyXML() {
/* 49 */     return this.anyXML;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProcessSchemaLocations() {
/* 54 */     return this.processSchemaLocations;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEcoreBuilder(EcoreBuilder ecoreBuilder) {
/* 59 */     this.ecoreBuilder = ecoreBuilder;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExternalSchemaLocations(Map<String, URI> schemaLocations) {
/* 64 */     this.externalSchemaLocation = schemaLocations;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProcessAnyXML(boolean anyXML) {
/* 69 */     this.anyXML = anyXML;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProcessSchemaLocations(boolean processSchemaLocations) {
/* 74 */     this.processSchemaLocations = processSchemaLocations;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 80 */     int hashCode = (this.externalSchemaLocation != null) ? this.externalSchemaLocation.hashCode() : 0;
/* 81 */     hashCode ^= (this.ecoreBuilder != null) ? this.ecoreBuilder.hashCode() : 0;
/* 82 */     return hashCode + (this.anyXML ? 1 : 0) + (this.processSchemaLocations ? 2 : 0);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\XMLOptionsImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */