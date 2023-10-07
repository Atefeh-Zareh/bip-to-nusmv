/*    */ package epfl.risd.bip.nusmv.expression;
/*    */ 
/*    */ import epfl.risd.bip.nusmv.api.NuEnumType;
/*    */ 
/*    */ 
/*    */ public class NuSignedWordVariable
/*    */   extends NuVariable
/*    */ {
/*    */   private int size;
/*    */   private char notation;
/*    */   
/*    */   public NuSignedWordVariable() {
/* 13 */     this.type = NuEnumType.SIGNED_WORD;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuSignedWordVariable(String n, int s, char not) {
/* 18 */     super(n, NuEnumType.SIGNED_WORD);
/* 19 */     this.size = s;
/* 20 */     this.notation = not;
/*    */   }
/*    */ 
/*    */   
/*    */   public NuSignedWordVariable(NuSignedWordVariable n) {
/* 25 */     super(n.name, NuEnumType.SIGNED_WORD);
/* 26 */     this.size = n.size;
/* 27 */     this.notation = n.notation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSize(int s) {
/* 32 */     this.size = s;
/*    */   }
/*    */   
/*    */   public void setNotation(char not) {
/* 36 */     this.notation = not;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 41 */     return this.size;
/*    */   }
/*    */   
/*    */   public char getNotation() {
/* 45 */     return this.notation;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String s = "";
/*    */     
/* 52 */     s = String.valueOf(s) + this.name;
/* 53 */     s = String.valueOf(s) + "\t\t";
/* 54 */     s = String.valueOf(s) + ":";
/* 55 */     s = String.valueOf(s) + "\t\t";
/* 56 */     s = String.valueOf(s) + this.type.toString();
/* 57 */     s = String.valueOf(s) + "[";
/* 58 */     s = String.valueOf(s) + this.size;
/* 59 */     s = String.valueOf(s) + "]";
/*    */     
/* 61 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuSignedWordVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */