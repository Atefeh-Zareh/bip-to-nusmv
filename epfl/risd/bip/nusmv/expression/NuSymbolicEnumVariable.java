/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.api.NuEnumType;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NuSymbolicEnumVariable
/*    */   extends NuVariable
/*    */ {
/*    */   private List<String> enumerations;
/*    */   
/*    */   public NuSymbolicEnumVariable() {
/* 15 */     this.type = NuEnumType.SYMBOLIC_ENUM;
/* 16 */     this.enumerations = new LinkedList<String>();
/*    */   }
/*    */ 
/*    */   
/*    */   public NuSymbolicEnumVariable(String n, List<String> enums) {
/* 21 */     super(n, NuEnumType.SYMBOLIC_ENUM);
/* 22 */     this.enumerations = new LinkedList<String>();
/* 23 */     if (enums != null) this.enumerations.addAll(enums);
/*    */   
/*    */   }
/*    */   
/*    */   public NuSymbolicEnumVariable(NuSymbolicEnumVariable n) {
/* 28 */     super(n.name, NuEnumType.SYMBOLIC_ENUM);
/* 29 */     this.enumerations = new LinkedList<String>();
/* 30 */     if (n.enumerations != null) this.enumerations.addAll(n.enumerations);
/*    */   
/*    */   }
/*    */   
/*    */   public void setEnumerations(List<String> enums) {
/* 35 */     this.enumerations = enums;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getEnumerations() {
/* 40 */     return this.enumerations;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addEnumeration(String en) {
/* 45 */     if (en != null) this.enumerations.add(en);
/*    */   
/*    */   }
/*    */   
/*    */   public void addEnumerations(List<String> enums) {
/* 50 */     if (enums != null) this.enumerations.addAll(enums);
/*    */   
/*    */   }
/*    */   
/*    */   public String toString() {
/* 55 */     String s = "";
/* 56 */     s = String.valueOf(s) + this.name;
/* 57 */     s = String.valueOf(s) + "\t\t";
/* 58 */     s = String.valueOf(s) + ":";
/* 59 */     s = String.valueOf(s) + "\t\t";
/* 60 */     s = String.valueOf(s) + "{";
/* 61 */     boolean first = true;
/*    */     
/* 63 */     for (String en : this.enumerations) {
/*    */       
/* 65 */       if (!first) s = String.valueOf(s) + ", "; 
/* 66 */       s = String.valueOf(s) + en;
/* 67 */       first = false;
/*    */     } 
/*    */     
/* 70 */     s = String.valueOf(s) + "}";
/* 71 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuSymbolicEnumVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */