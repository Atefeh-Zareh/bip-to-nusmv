/*    */ package org.eclipse.emf.ecore.xml.type.internal;
/*    */ 
/*    */ import javax.xml.namespace.QName;
/*    */ import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class QName
/*    */   extends QName
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String prefix;
/*    */   
/*    */   public QName(String qname) {
/* 43 */     super(null, (qname.indexOf(':') != -1) ? qname.substring(qname.indexOf(':') + 1) : qname, (qname.indexOf(':') != -1) ? qname.substring(0, qname.indexOf(':')) : "");
/* 44 */     setPrefix(super.getPrefix());
/*    */ 
/*    */     
/* 47 */     if (this.prefix.length() > 0 && !DataValue.XMLChar.isValidNCName(this.prefix)) {
/* 48 */       throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: " + qname);
/*    */     }
/* 50 */     if (!DataValue.XMLChar.isValidNCName(getLocalPart())) {
/* 51 */       throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: " + qname);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public QName(String namespaceURI, String localPart, String prefix) {
/* 59 */     super(namespaceURI, localPart, prefix = (prefix == null) ? "" : prefix);
/* 60 */     setPrefix(prefix);
/*    */     
/* 62 */     if (prefix.length() > 0 && !DataValue.XMLChar.isValidNCName(prefix)) {
/* 63 */       throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: " + prefix);
/*    */     }
/* 65 */     if (!DataValue.XMLChar.isValidNCName(localPart)) {
/* 66 */       throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: " + localPart);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPrefix() {
/* 75 */     return this.prefix;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPrefix(String prefix) {
/* 83 */     if (prefix == null) {
/*    */       
/* 85 */       this.prefix = "";
/*    */     }
/*    */     else {
/*    */       
/* 89 */       this.prefix = prefix;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\internal\QName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */