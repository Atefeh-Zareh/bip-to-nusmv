/*    */ package ujf.verimag.bip.cxxcodegen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CxxNames
/*    */ {
/*    */   static final String ATOMCLASS = "Atom";
/*    */   static final String PORTCLASS = "Port";
/*    */   static final String PARAMPREFIX = "BIPParam";
/*    */   static final String GETPREFIX = "get_";
/*    */   static final String STATETYPE = "STATE";
/*    */   static final String STATEVARNAME = "BIP_STATE";
/*    */   static final String PLACETYPE = "Place";
/*    */   static final String THIS = "this";
/*    */   static final String ADDPORT = "mPorts.add";
/*    */   static final String ADDPRIO = "mPriorities.add";
/*    */   static final String ADDPRIOMULTI = "mPriorities.push_back";
/*    */   static final String ADDCOMP = "mComponents.add";
/*    */   static final String ADDCOMPMULTI = "mComponents.push_back";
/*    */   static final String ADDCONNECTOR = "mConnectors.add";
/*    */   static final String ADDCONNECTORMULTI = "mConnectors.push_back";
/*    */   public static final String INITIALIZE = "initialize";
/*    */   public static final String EXECUTE = "execute";
/*    */   public static final String CHOICE = "choice";
/*    */   public static final String SYNC = "sync";
/*    */   public static final String RT_SYNC = "rt_sync";
/*    */   public static final String SETSYNC = "setSynced";
/*    */   public static final String SETDOMINATED = "setDominated";
/*    */   public static final String CONNECTORCLASS = "Connector";
/*    */   public static final String COMPOUNDCLASS = "Compound";
/*    */   public static final String GUARDFCTNAME = "guard";
/*    */   public static final String BOOL = "bool";
/*    */   public static final String TOP = "top";
/*    */   public static final String COMPLETEFCTNAME = "complete";
/*    */   public static final String SYNCED = "synced";
/*    */   public static final String GETSTATE = "getState";
/*    */   public static final String GETSTATETYPE = "Node *";
/*    */   public static final String SETSTATE = "setState";
/*    */   public static final String INDICE_NAME = "BIPind";
/*    */   public static final String SIZE_NAME = "BIPdimSize";
/*    */   public static final String LOW = "low";
/*    */   public static final String HIGH = "high";
/*    */   public static final String INTERACTION_TYPE = "Interaction";
/*    */   public static final String INTER_CREATE = "Interaction::Create";
/*    */   public static final String PRIORITYCLASS = "Priority";
/*    */   public static final String SETXPORT = "setxPort";
/*    */   public static final String GETXPORT = "xPort";
/*    */   public static final String UP = "up";
/*    */   public static final String DOWN = "dn";
/*    */   public static final String GETPLACE = "getToken";
/*    */   public static final String SETPLACE = "putToken";
/*    */   public static final String TOKEN = "token";
/*    */   public static final String PREPARE_NEXT = "prepareNextStep";
/*    */   public static final String TMP_GUARD = "_guard";
/*    */   public static final String TMP_CONSTRAINT = "_constraint";
/*    */   public static final String PRIO = "_prio";
/*    */   public static final String SETINTERNAL = "setInternal";
/*    */   public static final String DEBUGCLASS = "BipDebugElmt";
/*    */   public static final String GETSUBELEMENT = "getSubElement";
/*    */   public static final String GETVALUE = "getValue";
/*    */   public static final String GETELEMENTSNAMES = "getValuedElementNames";
/*    */   public static final String GETSUBELEMENTSNAMES = "getSubElementsNames";
/*    */   public static final String GETPARENT = "getParent";
/*    */   public static final String PORTPARENT = "mAtomHolder";
/*    */   public static final String ATOMPARENT = "mHolder";
/*    */   public static final String COMPOUNDPARENT = "mHolder";
/*    */   public static final String CONNECTORPARENT = "mHolder";
/*    */   public static final String SETERROR = "BIP_ReachErrorState";
/*    */   public static final String SETPRUNE = "BIP_ReachPruneState";
/*    */   public static final String TRANS_GUARD = "BIP_TransGuard";
/*    */   public static final String CONSTRAINT = "BIP_Constraint";
/*    */   public static final String HOLDER = "mHolder";
/*    */   public static final String RUN = "run";
/*    */   public static final String NEW_CONSTRAINT = "new Constraint";
/*    */   public static final String NEW_CLOCK = "new Clock";
/*    */   public static final String COMPUTE_PORT_CONSTRAINT = "computePortConstraint";
/*    */   public static final String APPLYPRIORITY = "applyPriority";
/*    */   
/*    */   public static String portTypeClassName(String portTypeName) {
/* 84 */     return portTypeName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String componentTypeClassName(String name) {
/* 90 */     return name;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String connectorTypeClassName(String name) {
/* 95 */     return name;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxNames.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */