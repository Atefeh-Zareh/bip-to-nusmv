/*     */ package org.eclipse.emf.ecore.xml.type.internal;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ import javax.xml.datatype.DatatypeConstants;
/*     */ import javax.xml.datatype.Duration;
/*     */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class XMLDuration
/*     */   extends Duration
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   Duration duration;
/*     */   
/*     */   public XMLDuration(String value) {
/* 105 */     this.duration = XMLCalendar.datatypeFactory.newDuration(XMLTypeUtil.normalize(value, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int compare(XMLDuration value1, XMLDuration value2) {
/* 110 */     switch (value1.duration.compare(value2.duration)) {
/*     */ 
/*     */       
/*     */       case 0:
/* 114 */         return 0;
/*     */ 
/*     */       
/*     */       case -1:
/* 118 */         return -1;
/*     */ 
/*     */       
/*     */       case 1:
/* 122 */         return 1;
/*     */     } 
/*     */ 
/*     */     
/* 126 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 134 */     return (object != null && this.duration.equals(object));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 140 */     return this.duration.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 146 */     return this.duration.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Duration add(Duration rhs) {
/* 152 */     return this.duration.add(rhs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTo(Calendar calendar) {
/* 158 */     this.duration.addTo(calendar);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compare(Duration duration) {
/* 164 */     return this.duration.compare(duration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Number getField(DatatypeConstants.Field field) {
/* 170 */     return this.duration.getField(field);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSign() {
/* 176 */     return this.duration.getSign();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYears() {
/* 182 */     return this.duration.getYears();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMonths() {
/* 188 */     return this.duration.getMonths();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDays() {
/* 194 */     return this.duration.getDays();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHours() {
/* 200 */     return this.duration.getHours();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinutes() {
/* 206 */     return this.duration.getMinutes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeconds() {
/* 212 */     return this.duration.getSeconds();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSet(DatatypeConstants.Field field) {
/* 218 */     return this.duration.isSet(field);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Duration multiply(BigDecimal factor) {
/* 224 */     return this.duration.multiply(factor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Duration negate() {
/* 230 */     return this.duration.negate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Duration normalizeWith(Calendar startTimeInstant) {
/* 236 */     return this.duration.normalizeWith(startTimeInstant);
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 241 */     out.writeUTF(toString());
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 246 */     this.duration = XMLCalendar.datatypeFactory.newDuration(in.readUTF());
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\internal\XMLDuration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */