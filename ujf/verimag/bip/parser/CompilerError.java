/*    */ package ujf.verimag.bip.parser;
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
/*    */ public class CompilerError
/*    */ {
/*    */   public static final int NO_ERROR = 0;
/*    */   public static final int OTHER_ERROR = 1;
/*    */   public static final int ILLEGAL_USE_WAITING = 2;
/*    */   public static final int ILLEGAL_USE_WAITING2 = 3;
/*    */   public static final int UNKNOWN_ELEMENT = 4;
/*    */   public static final int UNKNOWNPORT_IN_CONNECTOR = 5;
/*    */   public static final int NO_INSTANCE_PORT = 6;
/*    */   public static final int ILLEGAL_EXP = 7;
/*    */   public static final int UNKNOWN_DATA_PARAM = 8;
/*    */   public static final int CONNECTOR_WITH_NO_PORT = 9;
/*    */   public static final int BAD_PORT_TYPE = 10;
/*    */   public static final int TOO_MANY_PORT_IN_CNX = 11;
/*    */   public static final int NOT_ENOUGTH_PORT_IN_CNX = 12;
/*    */   public static final int NOT_ENOUGTH_PARAM = 13;
/*    */   public static final int TOO_MANY_PARAM = 14;
/*    */   public static final int BAD_DATA_NUMBER = 15;
/*    */   public static final int BAD_EXPORT_PORT_TYPE = 16;
/*    */   public static final int LIB_NOT_FOUND = 17;
/*    */   public static final int DUPLICATE_DECLARATION = 18;
/*    */   public static final int NO_PORT_EXPORTED = 19;
/*    */   public static final int BAD_ACTION = 20;
/*    */   public static final int OLD_INITIAL_SYNTAXE = 21;
/*    */   public static final int NO_INITIAL_STATE = 22;
/*    */   public static final int ROOT_SHOULD_NO_BE_ATOM = 23;
/*    */   public static final int DUPLICATE_PORT_IN_CONNECTOR = 24;
/*    */   public static final int PORT_INST_NOT_CONNECTED = 25;
/*    */   public static final int PACKAGE_NAME_MISMATCH = 26;
/* 46 */   static final String[][] messages = new String[][] { { "compilation complete" }, { "compilation error" }, { "Illegal reference to the object ", " should be a " }, { "Illegal reference to the object ", " should be an " }, { "", " ", " is not declared in " }, { "port ", " is not an element of connector " }, { "no port specified for instance " }, { "illegal expression, waiting for " }, { "data parameter ", " is not defined for port ", " of type " }, { "trying to connect connector ", " of type ", " which do not export port" }, { "incorrect port type for port ", " of connection ", " it should be of type " }, { "too many port in connection ", " of type " }, { "port missing in connection ", " of type " }, { "parameter missing in instance ", " of type " }, { "too many parameters in instance ", " of type " }, { "port ", " of type ", " should have ", " associated data(s)" }, { "port ", " is not of type " }, { "library ", " not found in the include directory list" }, { "object ", " is already declared" }, { "error on export port ", " the instance ", " do not export any port" }, { "incorrect instruction" }, { "old initial syntaxe, use \"initial to <place list> [do action]\" instead" }, { "atom type ", " has no initial state" }, { "the root component should be a compound component" }, { "port ", " appear more than one time in connector " }, { "port ", " of instance ", " is not connected" }, { "package name (", ") should match file name root (", ")" } };
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String buildErrorMessage(int messageNumber, String[] messageParameters) {
/* 84 */     String res = "";
/* 85 */     String[] errMsg = messages[messageNumber];
/* 86 */     int i = 0;
/* 87 */     while (i < errMsg.length || i < messageParameters.length) {
/* 88 */       if (i < errMsg.length) {
/* 89 */         res = res + errMsg[i];
/*    */       }
/* 91 */       if (i < messageParameters.length) {
/* 92 */         res = res + messageParameters[i];
/*    */       }
/* 94 */       i++;
/*    */     } 
/*    */     
/* 97 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\CompilerError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */