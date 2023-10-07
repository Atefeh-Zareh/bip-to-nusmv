/*    */ package epfl.risd.bip.nusmv.api;
/*    */ 
/*    */ public enum NuEnumType
/*    */ {
/*  5 */   BOOLEAN(0, "boolean"),
/*  6 */   SYMBOLIC_ENUM(1, "symbolic enum"),
/*  7 */   UNSIGNED_WORD(2, "unsigned word"),
/*  8 */   SIGNED_WORD(3, "signed word"),
/*  9 */   MODULE_INSTANT(4, "module");
/*    */   
/*    */   private int value;
/*    */   
/*    */   private String type;
/*    */   
/*    */   public static final int BOOLEAN_VALUE = 0;
/*    */   public static final int SYMBOLIC_ENUM_VALUE = 1;
/*    */   public static final int UNSIGNED_WORD_VALUE = 2;
/*    */   public static final int SIGNED_WORD_VALUE = 3;
/*    */   public static final int MODULE_INSTANT_VALUE = 4;
/*    */   
/*    */   NuEnumType(int v, String t) {
/* 22 */     this.value = v;
/* 23 */     this.type = t;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(int v) {
/* 28 */     this.value = v;
/*    */   }
/*    */   
/*    */   public void setType(String t) {
/* 32 */     this.type = t;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getValue() {
/* 37 */     return this.value;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 41 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return this.type;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\api\NuEnumType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */