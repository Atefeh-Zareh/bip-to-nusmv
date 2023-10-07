/*      */ package ujf.verimag.bip.parser;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.antlr.runtime.tree.Tree;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IndexLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerInterfaceVariableReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InterfaceVariableReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*      */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*      */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*      */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*      */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*      */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*      */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*      */ import ujf.verimag.bip.Core.Behaviors.PartType;
/*      */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*      */ import ujf.verimag.bip.Core.Behaviors.Port;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*      */ import ujf.verimag.bip.Core.Behaviors.State;
/*      */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*      */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*      */ import ujf.verimag.bip.Core.Behaviors.VariableDefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*      */ import ujf.verimag.bip.Core.Interactions.Part;
/*      */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*      */ import ujf.verimag.bip.Core.Interactions.VariableExportBinding;
/*      */ import ujf.verimag.bip.Core.Modules.Module;
/*      */ import ujf.verimag.bip.Core.Modules.ModulesFactory;
/*      */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*      */ import ujf.verimag.bip.Core.Modules.Package;
/*      */ import ujf.verimag.bip.Core.Modules.Root;
/*      */ import ujf.verimag.bip.Core.Modules.System;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACUnion;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
/*      */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*      */ import ujf.verimag.bip.Core.Priorities.PrioritiesFactory;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*      */ import ujf.verimag.bip.Extra.Time.TimeFactory;
/*      */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*      */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*      */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*      */ import ujf.verimag.bip.Extra.Time.TimedVariable;
/*      */ import ujf.verimag.bip.Extra.Time.UrgencyKind;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BipScannSyntaxe
/*      */ {
/*      */   static final int PACKAGE = 23;
/*      */   static final int IS = 47;
/*      */   static final int MODEL = 26;
/*      */   static final int USE = 29;
/*      */   static final int PORT = 30;
/*      */   static final int PORT_PARAMETER = 15;
/*      */   static final int DATA_PARAMETER = 16;
/*      */   static final int ACTUAL_PORT_PARAMETER = 17;
/*      */   static final int ACTUAL_DATA_PARAMETER = 18;
/*      */   static final int TYPE = 31;
/*      */   static final int CONNECTOR = 36;
/*      */   static final int EXPORT = 37;
/*      */   static final int EXPORT_PORT = 21;
/*      */   static final int EXPORT_DATA = 22;
/*      */   static final int DEFINE = 38;
/*      */   static final int ON = 39;
/*      */   static final int PROVIDED = 40;
/*      */   static final int UP = 41;
/*      */   static final int DOWN = 42;
/*      */   static final int ATOMIC = 43;
/*      */   static final int COMPOUND = 44;
/*      */   static final int COMPONENT = 49;
/*      */   static final int PRIORITY = 54;
/*      */   static final int PLACE = 59;
/*      */   static final int INITIAL = 60;
/*      */   static final int FROM = 61;
/*      */   static final int TO = 62;
/*      */   static final int EAGER = 63;
/*      */   static final int DELAYABLE = 64;
/*      */   static final int LAZY = 65;
/*      */   static final int RESET = 73;
/*      */   static final int DO = 74;
/*      */   static final int EXTERN = 76;
/*      */   static final int TIMED = 77;
/*      */   static final int DATA = 48;
/*      */   static final int CLOCK = 75;
/*      */   static final int IF = 102;
/*      */   static final int ELSE = 103;
/*      */   static final int SECOND = 70;
/*      */   static final int MILLISECOND = 71;
/*      */   static final int NANOSECOND = 72;
/*      */   static final int LOW_INTERACTION = 19;
/*      */   static final int High_INTERACTION = 20;
/*      */   static final int UNARY_PLUS = 4;
/*      */   static final int UNARY_MINUS = 5;
/*      */   static final int NOT = 94;
/*      */   static final int BITWISENOT = 95;
/*      */   static final int DEREFERENCE = 6;
/*      */   static final int REFERENCE = 7;
/*      */   static final int UNARY_PREFIX_INCREMENT = 8;
/*      */   static final int UNARY_PREFIX_DECREMENT = 9;
/*      */   static final int UNARY_POSTFIX_INCREMENT = 10;
/*      */   static final int UNARY_POSTFIX_DECREMENT = 11;
/*      */   static final int PLUS = 78;
/*      */   static final int MINUS = 91;
/*      */   static final int MULT = 57;
/*      */   static final int DIV = 92;
/*      */   static final int MOD = 93;
/*      */   static final int EQUALS = 84;
/*      */   static final int DIFF = 85;
/*      */   static final int LT = 56;
/*      */   static final int LTE = 86;
/*      */   static final int GT = 88;
/*      */   static final int GTE = 87;
/*      */   static final int OR = 80;
/*      */   static final int AND = 66;
/*      */   static final int BITWISEOR = 81;
/*      */   static final int BITWISEXOR = 82;
/*      */   static final int BITWISEAND = 83;
/*      */   static final int LEFTSHIFT = 89;
/*      */   static final int RIGHTSHIFT = 90;
/*      */   static final int DASH = 69;
/*      */   static final int QMARK = 50;
/*      */   static final int DOT = 35;
/*      */   static final int FIELD = 98;
/*      */   static final int LPAR = 32;
/*      */   static final int LBRACKET = 52;
/*      */   static final int ASSIGN = 58;
/*      */   static final int PLUSASSIGN = 106;
/*      */   static final int MINUSASSIGN = 107;
/*      */   static final int MULTASSIGN = 108;
/*      */   static final int DIVASSIGN = 109;
/*      */   static final int MODASSIGN = 110;
/*      */   static final int LCURLY = 104;
/*      */   static final int CODE = 28;
/*      */   static final int FUSION = 13;
/*      */   static final int UNION = 12;
/*      */   static final int TRIGGER = 14;
/*      */   static final int QUOTE = 79;
/*      */   static final int IDENTIFIER = 24;
/*      */   static final int STRING = 100;
/*      */   static final int INTEGER = 68;
/*      */   static final int FLOAT = 99;
/*      */   static final int INDEX = 101;
/*  222 */   private Module bipModule = null;
/*  223 */   private Map<Tree, EObject> elementDictionnary = null;
/*  224 */   private Map<String, NamedElement> namedDictionnary = null;
/*  225 */   private Map<String, EObject> localDictionnary = null;
/*  226 */   private Map<String, OpaqueElement> typeDictionnary = null;
/*      */   
/*  228 */   private String globalScopeName = null;
/*      */   
/*      */   private BipTreeError error;
/*      */   
/*      */   private BipLibraryReader libReader;
/*  233 */   private PrintStream out = System.out;
/*      */   
/*      */   public BipScannSyntaxe(BipTreeError msg, BipLibraryReader libReader) {
/*  236 */     this.error = msg;
/*  237 */     this.libReader = libReader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Module scannModuleDefinition(Tree tree, String fileName) throws IOException {
/*  248 */     this.elementDictionnary = new HashMap<Tree, EObject>();
/*  249 */     this.namedDictionnary = new HashMap<String, NamedElement>();
/*  250 */     this.localDictionnary = new HashMap<String, EObject>();
/*  251 */     this.typeDictionnary = new HashMap<String, OpaqueElement>();
/*      */ 
/*      */     
/*  254 */     createModel(tree, fileName);
/*      */ 
/*      */     
/*  257 */     scannImports(tree);
/*      */ 
/*      */     
/*  260 */     predefineDeclarations();
/*      */ 
/*      */ 
/*      */     
/*  264 */     scannDeclarations(tree, (NamedElement)this.bipModule, "");
/*      */ 
/*      */ 
/*      */     
/*  268 */     completeModel(tree, null, "");
/*      */     
/*  270 */     return this.bipModule;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createModel(Tree tree, String fileName) throws IOException {
/*      */     String name;
/*  278 */     switch (tree.getType()) {
/*      */       case 26:
/*  280 */         this.bipModule = (Module)ModulesFactory.eINSTANCE.createSystem();
/*  281 */         this.bipModule.setName(tree.getChild(0).getText());
/*  282 */         addNamedElement(tree, "", (NamedElement)this.bipModule, true);
/*      */         break;
/*      */       case 23:
/*  285 */         this.bipModule = (Module)ModulesFactory.eINSTANCE.createPackage();
/*      */         
/*  287 */         this.globalScopeName = tree.getChild(0).getText();
/*  288 */         this.bipModule.setName(this.globalScopeName);
/*  289 */         addNamedElement(tree, "", (NamedElement)this.bipModule, true);
/*      */         
/*  291 */         name = (new File(fileName)).getName();
/*  292 */         name = name.substring(0, name.length() - 4);
/*  293 */         if (!name.equals(this.globalScopeName)) {
/*  294 */           String[] params = { this.globalScopeName, name };
/*  295 */           this.error.sendError(26, params, tree);
/*      */         } 
/*      */         break;
/*      */     } 
/*  299 */     this.bipModule.setSrcFileName((new File(fileName)).getName());
/*      */   }
/*      */ 
/*      */   
/*      */   private void addPackageElementsInSymbolTable(Package p, String name) {
/*  304 */     for (BipType bt : p.getBipType()) {
/*  305 */       String fullName = name + "." + bt.getName();
/*  306 */       addNamedElement(null, fullName, (NamedElement)bt, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void scannImports(Tree t) {
/*  317 */     for (int i = 0; i < t.getChildCount(); i++) {
/*  318 */       Tree uT = t.getChild(i);
/*  319 */       if (uT.getType() == 29) {
/*      */         
/*  321 */         String libName = uT.getChild(0).getText();
/*  322 */         Package p = this.libReader.SearchForImportedLibrary(libName);
/*  323 */         if (p != null) {
/*  324 */           this.bipModule.getUsedPackage().add(p);
/*      */           
/*  326 */           addPackageElementsInSymbolTable(p, libName);
/*      */         } else {
/*  328 */           String[] params = { libName };
/*  329 */           this.error.sendError(17, params, uT.getChild(0));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void predefineDeclarations() {
/*  342 */     PortType simplePortType = (PortType)this.namedDictionnary.get("Port");
/*  343 */     if (simplePortType == null) {
/*      */       
/*  345 */       simplePortType = BehaviorsFactory.eINSTANCE.createPortType();
/*  346 */       simplePortType.setName("Port");
/*  347 */       this.bipModule.getBipType().add(simplePortType);
/*  348 */       this.namedDictionnary.put("Port", simplePortType);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addNamedElement(Tree tree, String fullName, NamedElement elmt, boolean exported) {
/*  364 */     if (tree != null) this.elementDictionnary.put(tree, elmt);
/*      */ 
/*      */ 
/*      */     
/*  368 */     if (exported) {
/*      */       
/*  370 */       if (exported && this.namedDictionnary.containsKey(fullName)) {
/*  371 */         String[] params = { fullName };
/*  372 */         this.error.sendError(18, params, tree);
/*      */       } 
/*      */       
/*  375 */       this.namedDictionnary.put(fullName, elmt);
/*      */     } else {
/*      */       
/*  378 */       if (this.localDictionnary.containsKey(fullName)) {
/*  379 */         String[] params = { fullName };
/*  380 */         this.error.sendError(18, params, tree);
/*      */       } 
/*      */       
/*  383 */       this.localDictionnary.put(fullName, elmt);
/*      */     } 
/*      */ 
/*      */     
/*  387 */     this.error.addElement((EObject)elmt, tree);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object getElement(Tree tree) {
/*  398 */     return this.elementDictionnary.get(tree); } public void scannDeclarations(Tree tree, NamedElement currentObj, String contextName) {
/*      */     AtomType atomType;
/*      */     CompoundType compoundType1;
/*      */     ConnectorType connectorType;
/*      */     Tree portDeclT;
/*      */     String exportName;
/*      */     boolean extern;
/*      */     int iName;
/*      */     int j;
/*      */     String name;
/*      */     String str1;
/*      */     Port p;
/*      */     InterfaceVariable iv;
/*      */     boolean exported;
/*      */     Connector cnx;
/*      */     PortParameter pp;
/*      */     boolean timed;
/*      */     CompoundType compound;
/*      */     int firstType;
/*      */     int typeInd;
/*      */     int k;
/*  419 */     switch (tree.getType()) {
/*      */       
/*      */       case 30:
/*  422 */         if (tree.getChild(0).getType() == 31) {
/*      */           
/*  424 */           PortType pt = BehaviorsFactory.eINSTANCE.createPortType();
/*  425 */           this.bipModule.getBipType().add(pt);
/*  426 */           String str = tree.getChild(1).getText();
/*  427 */           pt.setName(str);
/*  428 */           pt.setScope(this.globalScopeName);
/*  429 */           addNamedElement(tree, str, (NamedElement)pt, true);
/*      */           
/*  431 */           if (tree.getChildCount() > 2) {
/*      */             
/*  433 */             Tree portsTree = tree.getChild(2);
/*  434 */             for (int m = 0; m < portsTree.getChildCount(); m++)
/*      */             {
/*      */               
/*  437 */               Tree dataTree = portsTree.getChild(m);
/*  438 */               DataParameter dp = BehaviorsFactory.eINSTANCE.createDataParameter();
/*  439 */               pt.getDataParameter().add(dp);
/*  440 */               String dataTypeName = dataTree.getText();
/*  441 */               String dataName = dataTree.getChild(0).getText();
/*  442 */               dp.setName(dataName);
/*  443 */               DataType dt = addDataType(dataTypeName);
/*  444 */               dp.setType(dt);
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/*  450 */           PortDefinition portDefinition = BehaviorsFactory.eINSTANCE.createPortDefinition();
/*  451 */           addPortDefinition(currentObj, portDefinition);
/*  452 */           this.error.addElement((EObject)portDefinition, tree);
/*  453 */           String str = tree.getChild(1).getText();
/*  454 */           portDefinition.setName(str);
/*  455 */           int treeIndex = 2;
/*      */           
/*  457 */           addNamedElement(tree, contextName + "." + str, (NamedElement)portDefinition, false);
/*  458 */           if (tree.getChildCount() > 2) {
/*      */             
/*  460 */             Tree portsTree = tree.getChild(2);
/*  461 */             if (portsTree.getType() == 32) {
/*  462 */               treeIndex++;
/*      */             }
/*      */           } 
/*      */           
/*  466 */           if (tree.getChildCount() > treeIndex) {
/*  467 */             Tree exportT = tree.getChild(treeIndex);
/*      */             
/*  469 */             String str2 = str;
/*  470 */             if (exportT.getChildCount() > 0) {
/*  471 */               str2 = exportT.getChild(0).getText();
/*      */             }
/*      */             
/*  474 */             declareExport(currentObj, exportT.getChild(0), contextName, str2, portDefinition, null);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 15:
/*  483 */         portDeclT = tree;
/*  484 */         str1 = portDeclT.getChild(1).getText();
/*  485 */         pp = InteractionsFactory.eINSTANCE.createPortParameter();
/*  486 */         pp.setName(str1);
/*  487 */         addNamedElement(portDeclT, contextName + "." + str1, (NamedElement)pp, false);
/*  488 */         ((ConnectorType)currentObj).getPortParameter().add(pp);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 21:
/*  499 */         exportName = tree.getChild(1).getText();
/*  500 */         p = BehaviorsFactory.eINSTANCE.createPort();
/*  501 */         p.setName(exportName);
/*  502 */         addNamedElement(tree, contextName + "." + exportName, (NamedElement)p, true);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 22:
/*  513 */         exportName = tree.getChild(1).getText();
/*  514 */         iv = BehaviorsFactory.eINSTANCE.createInterfaceVariable();
/*  515 */         iv.setName(exportName);
/*  516 */         addNamedElement(tree, contextName + "." + exportName, (NamedElement)iv, true);
/*  517 */         if (currentObj instanceof CompoundType) {
/*  518 */           CompoundType comp = (CompoundType)currentObj;
/*  519 */           comp.getInterfaceVariable().add(iv);
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 43:
/*  526 */         if (tree.getChild(0).getType() == 31) {
/*      */           
/*  528 */           AtomType at = BehaviorsFactory.eINSTANCE.createAtomType();
/*  529 */           this.bipModule.getBipType().add(at);
/*  530 */           String str = tree.getChild(1).getText();
/*  531 */           at.setName(str);
/*  532 */           at.setScope(this.globalScopeName);
/*      */           
/*  534 */           PetriNet pn = BehaviorsFactory.eINSTANCE.createPetriNet();
/*  535 */           at.setBehavior((Behavior)pn);
/*  536 */           atomType = at;
/*  537 */           contextName = str;
/*  538 */           addNamedElement(tree, str, (NamedElement)at, true);
/*      */           
/*  540 */           addParameters(tree, 2, (NamedElement)atomType, contextName);
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 48:
/*  546 */         extern = false;
/*  547 */         exported = false;
/*  548 */         timed = false;
/*  549 */         firstType = tree.getChild(0).getType();
/*  550 */         typeInd = 0;
/*      */         
/*  552 */         if (firstType == 37) {
/*  553 */           exported = true;
/*  554 */           typeInd++;
/*      */         } 
/*  556 */         if (firstType == 76) {
/*  557 */           extern = true;
/*  558 */           typeInd++;
/*      */         } 
/*  560 */         if (tree.getChild(typeInd).getType() == 77) {
/*  561 */           timed = true;
/*  562 */           typeInd++;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  567 */         for (k = typeInd + 1; k < tree.getChildCount(); k++) {
/*  568 */           Variable v; Tree tData = tree.getChild(k);
/*      */           
/*  570 */           if (timed) {
/*      */             
/*  572 */             v = BehaviorsFactory.eINSTANCE.createVariable();
/*      */           } else {
/*  574 */             v = BehaviorsFactory.eINSTANCE.createVariable();
/*      */           } 
/*  576 */           addData((NamedElement)atomType, v);
/*  577 */           if (extern) {
/*  578 */             v.setIsExternal(true);
/*      */           }
/*      */           
/*  581 */           String str = tData.getText();
/*  582 */           v.setName(str);
/*  583 */           DataType dt = addDataType(tree.getChild(typeInd).getText());
/*  584 */           v.setType(dt);
/*  585 */           addNamedElement(tData, contextName + "." + str, (NamedElement)v, false);
/*  586 */           if (exported) {
/*      */             
/*  588 */             InterfaceVariable interfaceVariable = BehaviorsFactory.eINSTANCE.createInterfaceVariable();
/*  589 */             if (atomType instanceof AtomType) {
/*  590 */               AtomType at = atomType;
/*  591 */               at.getInterfaceVariable().add(interfaceVariable);
/*      */             } 
/*      */             
/*  594 */             VariableDefinitionBinding vdb = BehaviorsFactory.eINSTANCE.createVariableDefinitionBinding();
/*  595 */             interfaceVariable.setName(str);
/*  596 */             interfaceVariable.setType(dt);
/*  597 */             interfaceVariable.getVariableBinding().add(vdb);
/*  598 */             vdb.setVariable(v);
/*  599 */             addNamedElement(tData, contextName + "." + str, (NamedElement)interfaceVariable, true);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 75:
/*  608 */         for (iName = 0; iName < tree.getChildCount(); iName++) {
/*  609 */           Tree tData = tree.getChild(iName);
/*  610 */           TimedVariable v = TimeFactory.eINSTANCE.createTimedVariable();
/*  611 */           String str = tData.getText();
/*  612 */           v.setName(str);
/*  613 */           addData((NamedElement)atomType, (Variable)v);
/*  614 */           addNamedElement(tData, contextName + "." + str, (NamedElement)v, false);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 59:
/*  623 */         for (j = 0; j < tree.getChildCount(); j++) {
/*  624 */           Tree placeT = tree.getChild(j);
/*  625 */           State s = BehaviorsFactory.eINSTANCE.createState();
/*  626 */           PetriNet pn = (PetriNet)atomType.getBehavior();
/*  627 */           pn.getState().add(s);
/*  628 */           String str = placeT.getText();
/*  629 */           s.setName(str);
/*  630 */           addNamedElement(tree, contextName + "." + str, (NamedElement)s, false);
/*  631 */           if (placeT.getChildCount() > 0) {
/*      */             
/*  633 */             pn.getInitialState().add(s);
/*  634 */             String[] params = new String[0];
/*  635 */             this.error.sendWarning(21, params, placeT);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 44:
/*  643 */         if (tree.getChild(0).getType() == 31) {
/*  644 */           CompoundType ct = InteractionsFactory.eINSTANCE.createCompoundType();
/*  645 */           this.bipModule.getBipType().add(ct);
/*  646 */           String str = tree.getChild(1).getText();
/*  647 */           ct.setName(str);
/*  648 */           ct.setScope(this.globalScopeName);
/*  649 */           compoundType1 = ct;
/*  650 */           contextName = str;
/*  651 */           addNamedElement(tree, str, (NamedElement)ct, true);
/*  652 */           addParameters(tree, 2, (NamedElement)compoundType1, contextName);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 49:
/*  657 */         if (compoundType1 instanceof CompoundType) {
/*  658 */           String str = tree.getChild(1).getText();
/*  659 */           Component comp = InteractionsFactory.eINSTANCE.createComponent();
/*  660 */           this.error.addElement((EObject)comp, tree);
/*  661 */           comp.setName(str);
/*  662 */           addNamedElement(tree, contextName + "." + str, (NamedElement)comp, false);
/*  663 */           CompoundType compoundType = compoundType1;
/*  664 */           compoundType.getSubcomponent().add(comp);
/*      */         } else {
/*      */           
/*  667 */           String str = tree.getChild(1).getText();
/*  668 */           Root root = ModulesFactory.eINSTANCE.createRoot();
/*  669 */           this.error.addElement((EObject)root, tree);
/*  670 */           ((System)this.bipModule).setRoot(root);
/*  671 */           root.setName(str);
/*  672 */           addNamedElement(tree, contextName + "." + str, (NamedElement)root, false);
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 36:
/*  677 */         if (tree.getChild(0).getType() == 31) {
/*      */           
/*  679 */           ConnectorType ct = InteractionsFactory.eINSTANCE.createConnectorType();
/*  680 */           this.bipModule.getBipType().add(ct);
/*  681 */           String str = tree.getChild(1).getText();
/*  682 */           ct.setName(str);
/*  683 */           ct.setScope(this.globalScopeName);
/*  684 */           connectorType = ct;
/*  685 */           contextName = str;
/*  686 */           addNamedElement(tree, str, (NamedElement)ct, true);
/*      */           
/*  688 */           addParameters(tree, 3, (NamedElement)connectorType, contextName);
/*      */           
/*      */           break;
/*      */         } 
/*  692 */         name = tree.getChild(1).getText();
/*  693 */         cnx = InteractionsFactory.eINSTANCE.createConnector();
/*  694 */         cnx.setName(name);
/*  695 */         compound = (CompoundType)connectorType;
/*  696 */         compound.getConnector().add(cnx);
/*  697 */         addNamedElement(tree, contextName + "." + name, (NamedElement)cnx, false);
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  706 */     for (int i = 0; i < tree.getChildCount(); i++) {
/*  707 */       scannDeclarations(tree.getChild(i), (NamedElement)connectorType, contextName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addParameters(Tree tree, int index, NamedElement ne, String contextName) {
/*  720 */     ParameterizedElement pe = (ParameterizedElement)ne;
/*  721 */     if (tree.getChildCount() > index) {
/*  722 */       Tree dataParamT = tree.getChild(index);
/*  723 */       if (dataParamT.getType() == 16) {
/*  724 */         for (int i = 0; i < dataParamT.getChildCount(); i++) {
/*  725 */           String typeName = dataParamT.getChild(i).getText();
/*  726 */           String paramName = dataParamT.getChild(i).getChild(0).getText();
/*  727 */           DataType dt = addDataType(typeName);
/*  728 */           DataParameter dp = BehaviorsFactory.eINSTANCE.createDataParameter();
/*  729 */           pe.getDataParameter().add(dp);
/*  730 */           dp.setName(paramName);
/*  731 */           dp.setType(dt);
/*  732 */           addNamedElement(null, contextName + "." + paramName, (NamedElement)dp, false);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void declareExport(NamedElement currentObj, Tree tree, String contextName, String exportName, PortDefinition pDef, Port p) {
/*  749 */     if (p == null) {
/*  750 */       p = BehaviorsFactory.eINSTANCE.createPort();
/*  751 */       addNamedElement(tree, contextName + "." + exportName, (NamedElement)p, true);
/*      */     } 
/*  753 */     p.setName(exportName);
/*  754 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  755 */     p.setBinding((Binding)db);
/*  756 */     db.setDefinition(pDef);
/*  757 */     p.setType(pDef.getType());
/*  758 */     if (currentObj instanceof ComponentType) {
/*  759 */       ComponentType comp = (ComponentType)currentObj;
/*  760 */       comp.getPort().add(p);
/*  761 */     } else if (currentObj instanceof ConnectorType) {
/*  762 */       ConnectorType cnx = (ConnectorType)currentObj;
/*  763 */       cnx.setPort(p);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private DataType addDataType(String dataTypeName) {
/*  774 */     OpaqueElement dt = this.typeDictionnary.get(dataTypeName);
/*  775 */     if (dt == null) {
/*  776 */       dt = ModulesFactory.eINSTANCE.createOpaqueElement();
/*  777 */       this.bipModule.getDataType().add(dt);
/*  778 */       dt.setBody(dataTypeName);
/*  779 */       this.typeDictionnary.put(dataTypeName, dt);
/*      */     } 
/*  781 */     return (DataType)dt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addPortDefinition(NamedElement currentObj, PortDefinition p) {
/*  791 */     if (currentObj instanceof AtomType) {
/*  792 */       AtomType at = (AtomType)currentObj;
/*  793 */       at.getPortDefinition().add(p);
/*      */     }
/*  795 */     else if (currentObj instanceof ConnectorType) {
/*  796 */       ConnectorType ct = (ConnectorType)currentObj;
/*  797 */       ct.setPortDefinition(p);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addData(NamedElement currentObj, Variable v) {
/*  809 */     if (currentObj instanceof AtomType) {
/*  810 */       AtomType at = (AtomType)currentObj;
/*  811 */       at.getVariable().add(v);
/*      */     }
/*  813 */     else if (currentObj instanceof ConnectorType) {
/*  814 */       ConnectorType ct = (ConnectorType)currentObj;
/*  815 */       ct.getVariable().add(v);
/*      */     }  } public void completeModel(Tree tree, NamedElement currentObj, String contextName) { Module module; ConnectorType connectorType; CompoundType compoundType; Port port; InterfaceVariable export; Tree portDeclT; ComponentType ct; AtomType atomType1; PriorityRule pr; int firstType; AtomType at; String exportName; Tree innerTree; BipType type; PetriNet behav; ComponentType componentType1; int typeInd; PetriNet pn; Tree tree1;
/*      */     PortParameter pp;
/*      */     Tree toTree;
/*      */     String prioName;
/*      */     OpaqueElement op;
/*      */     int k;
/*      */     PortType portType;
/*      */     int j;
/*      */     int iLow;
/*      */     int iName;
/*      */     String text;
/*      */     Tree lowTree;
/*      */     int textLength;
/*      */     Tree highTree;
/*      */     String code;
/*      */     PriorityElement low;
/*      */     PriorityElement high;
/*      */     int iGuard;
/*  834 */     switch (tree.getType()) {
/*      */       case 26:
/*  836 */         module = this.bipModule;
/*      */         break;
/*      */       case 23:
/*  839 */         module = this.bipModule;
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 21:
/*  847 */         port = (Port)getElement(tree);
/*  848 */         exportName = port.getName();
/*      */         
/*  850 */         tree1 = null;
/*  851 */         for (k = 2; k < tree.getChildCount(); k++) {
/*  852 */           Tree t = tree.getChild(k);
/*  853 */           if (t.getType() == 47) {
/*  854 */             tree1 = t;
/*      */           }
/*      */         } 
/*  857 */         portType = (PortType)resolveType(tree.getChild(0), PortType.class, "port type");
/*      */ 
/*      */         
/*  860 */         port.setType(portType);
/*      */         
/*  862 */         if (module instanceof CompoundType) {
/*      */           
/*  864 */           ExportBinding eb = InteractionsFactory.eINSTANCE.createExportBinding();
/*  865 */           port.setBinding((Binding)eb);
/*  866 */           resolveInnerPortReference(tree1.getChild(0), (BipType)module, (InnerPortSpecification)eb);
/*  867 */           ComponentType comp = (ComponentType)module;
/*  868 */           comp.getPort().add(port); break;
/*      */         } 
/*  870 */         if (module instanceof AtomType) {
/*      */           
/*  872 */           NamedElement ne = getNamedElement(tree1.getChild(0).getText(), module, contextName);
/*      */           
/*  874 */           if (ne == null) {
/*      */             
/*  876 */             String[] arrayOfString = { "data", tree1.getChild(0).getText(), contextName };
/*  877 */             this.error.sendError(4, arrayOfString, tree1.getChild(0));
/*      */             break;
/*      */           } 
/*  880 */           if (ne instanceof PortDefinition) {
/*  881 */             PortDefinition pd = (PortDefinition)ne;
/*  882 */             declareExport((NamedElement)module, tree, contextName, exportName, pd, port);
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  887 */           String[] params = { tree1.getChild(0).getText(), "port" };
/*  888 */           this.error.sendError(2, params, tree1.getChild(0));
/*      */           break;
/*      */         } 
/*  891 */         if (module instanceof ConnectorType) {
/*      */           
/*  893 */           DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  894 */           port.setBinding((Binding)db);
/*  895 */           PortDefinition pd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/*  896 */           pd.setType(port.getType());
/*  897 */           db.setDefinition(pd);
/*      */           
/*      */           Tree t;
/*  900 */           if (tree.getChildCount() > 2 && (t = tree.getChild(2)).getType() == 32)
/*      */           {
/*  902 */             for (int m = 0; m < t.getChildCount(); m++) {
/*  903 */               Tree dataT = t.getChild(m);
/*  904 */               NamedElement ne = getNamedElement(dataT.getText(), module, contextName);
/*  905 */               if (ne instanceof Variable) {
/*  906 */                 pd.getExposedVariable().add(ne);
/*      */               } else {
/*  908 */                 String[] params = { dataT.getText(), "data" };
/*  909 */                 this.error.sendError(2, params, dataT);
/*      */ 
/*      */                 
/*      */                 return;
/*      */               } 
/*      */             } 
/*      */           }
/*      */           
/*  917 */           pd.setName(exportName);
/*  918 */           ConnectorType cnx = (ConnectorType)module;
/*  919 */           cnx.setPortDefinition(pd);
/*  920 */           cnx.setPort(port);
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 22:
/*  929 */         export = (InterfaceVariable)getElement(tree);
/*      */         
/*  931 */         innerTree = tree.getChild(2);
/*      */         
/*  933 */         if (module instanceof CompoundType) {
/*  934 */           VariableExportBinding eb = InteractionsFactory.eINSTANCE.createVariableExportBinding();
/*  935 */           export.getVariableBinding().add(eb);
/*  936 */           resolveInnerVariableReference(eb, innerTree, (BipType)module);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 30:
/*  941 */         if (tree.getChild(0).getType() != 31) {
/*      */ 
/*      */ 
/*      */           
/*  945 */           PortDefinition p = (PortDefinition)getElement(tree);
/*  946 */           PortType portType1 = (PortType)resolveType(tree.getChild(0), PortType.class, "port type");
/*      */ 
/*      */           
/*  949 */           p.setType(portType1);
/*      */           
/*      */           Tree t;
/*  952 */           if (tree.getChildCount() > 2 && (t = tree.getChild(2)).getType() == 32)
/*      */           {
/*  954 */             for (int m = 0; m < t.getChildCount(); m++) {
/*  955 */               Tree dataT = t.getChild(m);
/*  956 */               NamedElement ne = getNamedElement(dataT.getText(), module, contextName);
/*  957 */               if (ne == null) {
/*  958 */                 String[] params = { "data", dataT.getText(), contextName };
/*  959 */                 this.error.sendError(4, params, dataT);
/*  960 */               } else if (ne instanceof Variable) {
/*  961 */                 p.getExposedVariable().add(ne);
/*      */               } else {
/*  963 */                 String[] params = { dataT.getText(), "data" };
/*  964 */                 this.error.sendError(2, params, dataT);
/*      */                 return;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 15:
/*  977 */         portDeclT = tree.getChild(0);
/*      */         
/*  979 */         type = resolveType(portDeclT, PortType.class, "port Type");
/*      */ 
/*      */         
/*  982 */         pp = (PortParameter)getElement(portDeclT);
/*  983 */         if (type != null) {
/*  984 */           pp.setType((PortType)type);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 36:
/*  989 */         if (tree.getChild(0).getType() == 31) {
/*  990 */           ConnectorType connectorType1 = (ConnectorType)getElement(tree);
/*  991 */           contextName = connectorType1.getName();
/*  992 */           int iChild = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1001 */           Tree portTree = tree.getChild(iChild);
/*      */           
/* 1003 */           while (iChild < tree.getChildCount() && portTree.getType() == 15) {
/*      */             
/* 1005 */             BipType bipType = resolveType(portTree.getChild(0), PortType.class, "port Type");
/*      */ 
/*      */             
/* 1008 */             if (bipType != null) {
/* 1009 */               PortParameter portParameter = (PortParameter)getElement(portTree);
/* 1010 */               portParameter.setType((PortType)bipType);
/*      */             } 
/*      */             
/* 1013 */             iChild++;
/* 1014 */             portTree = tree.getChild(iChild);
/*      */           } 
/*      */ 
/*      */           
/* 1018 */           addParameters(tree, iChild, (NamedElement)connectorType1, contextName);
/*      */ 
/*      */           
/* 1021 */           if (tree.getChild(iChild).getType() == 16) {
/* 1022 */             iChild++;
/*      */           }
/*      */           
/* 1025 */           while (tree.getChild(iChild).getType() == 28) {
/* 1026 */             iChild++;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1031 */           connectorType = connectorType1;
/* 1032 */           defineConnectorType(tree.getChild(iChild), connectorType1, contextName);
/* 1033 */           Port p = connectorType1.getPort();
/* 1034 */           if (p != null) {
/* 1035 */             p.setType(((DefinitionBinding)p.getBinding()).getDefinition().getType());
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1040 */           for (int m = iChild + 1; m < tree.getChildCount(); m++) {
/* 1041 */             completeModel(tree.getChild(m), (NamedElement)connectorType, contextName);
/*      */           
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 1047 */           CompoundType compoundType1 = (CompoundType)connectorType;
/* 1048 */           addConnector(tree, compoundType1, contextName);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 43:
/* 1054 */         if (tree.getChild(0).getType() == 31) {
/*      */           
/* 1056 */           AtomType atomType3 = (AtomType)getElement(tree);
/* 1057 */           AtomType atomType2 = atomType3;
/* 1058 */           contextName = atomType3.getName();
/*      */         } 
/*      */         break;
/*      */       case 44:
/* 1062 */         if (tree.getChild(0).getType() == 31) {
/*      */           
/* 1064 */           CompoundType compoundType1 = (CompoundType)getElement(tree);
/* 1065 */           compoundType = compoundType1;
/* 1066 */           contextName = compoundType1.getName();
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 49:
/* 1072 */         ct = (ComponentType)resolveType(tree.getChild(0), ComponentType.class, "component type");
/*      */ 
/*      */ 
/*      */         
/* 1076 */         if (compoundType instanceof CompoundType) {
/* 1077 */           Component comp = (Component)getElement(tree);
/* 1078 */           comp.setType(ct);
/*      */           
/* 1080 */           int index = 2;
/*      */           
/* 1082 */           Tree portsTree = null;
/* 1083 */           if (index < tree.getChildCount()) portsTree = tree.getChild(index); 
/* 1084 */           while (portsTree != null && portsTree.getType() == 52) {
/* 1085 */             Tree multiple = portsTree;
/* 1086 */             for (int iMult = 0; iMult < multiple.getChildCount(); iMult++) {
/* 1087 */               Tree t = multiple.getChild(iMult);
/* 1088 */               Expression iexp = scannExpr(t, compoundType, contextName);
/*      */               
/* 1090 */               comp.getMultiplicitySpecification().add(iexp);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1095 */             index++;
/* 1096 */             if (index < tree.getChildCount()) { portsTree = tree.getChild(index); continue; }
/* 1097 */              portsTree = null;
/*      */           } 
/*      */ 
/*      */           
/* 1101 */           addActualParameters(tree, index, (NamedElement)compoundType, contextName, (List<Expression>)comp.getActualData(), (ParameterizedElement)ct);
/*      */         } else {
/*      */           
/* 1104 */           Root r = (Root)getElement(tree);
/* 1105 */           r.setType(ct);
/* 1106 */           addActualParameters(tree, 2, (NamedElement)compoundType, contextName, (List<Expression>)r.getActualData(), (ParameterizedElement)ct);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 60:
/* 1115 */         atomType1 = (AtomType)compoundType;
/* 1116 */         behav = (PetriNet)atomType1.getBehavior();
/* 1117 */         toTree = tree.getChild(0);
/* 1118 */         for (j = 0; j < toTree.getChildCount(); j++) {
/* 1119 */           NamedElement place = getNamedElement(toTree.getChild(j).getText(), compoundType, contextName);
/*      */           
/* 1121 */           if (place instanceof State) {
/* 1122 */             State s = (State)place;
/* 1123 */             behav.getInitialState().add(s);
/*      */           } else {
/* 1125 */             String[] params = { "place", tree.getChild(j).getText(), contextName };
/* 1126 */             this.error.sendError(4, params, tree.getChild(0));
/*      */           } 
/*      */         } 
/* 1129 */         if (tree.getChildCount() > 1) {
/* 1130 */           Tree doTree = tree.getChild(1);
/* 1131 */           behav.setInitialization(scannAction(doTree.getChild(0), atomType1, contextName));
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 54:
/* 1139 */         pr = PrioritiesFactory.eINSTANCE.createPriorityRule();
/* 1140 */         componentType1 = (ComponentType)compoundType;
/* 1141 */         componentType1.getPriorityRule().add(pr);
/* 1142 */         prioName = tree.getChild(0).getText();
/* 1143 */         pr.setName(prioName);
/* 1144 */         iLow = 1;
/* 1145 */         while (tree.getChild(iLow).getType() != 19) {
/* 1146 */           iLow++;
/*      */         }
/* 1148 */         lowTree = tree.getChild(iLow);
/* 1149 */         highTree = tree.getChild(iLow + 1);
/* 1150 */         low = scannInteraction(lowTree, componentType1, contextName);
/* 1151 */         high = scannInteraction(highTree, componentType1, contextName);
/* 1152 */         if (componentType1 instanceof AtomType) {
/* 1153 */           if (high instanceof ConnectorTypeReference) {
/* 1154 */             String[] params = { highTree.getText(), "port" };
/* 1155 */             this.error.sendError(2, params, highTree);
/*      */           } 
/* 1157 */           if (low instanceof ConnectorTypeReference) {
/* 1158 */             String[] params = { lowTree.getText(), "port" };
/* 1159 */             this.error.sendError(2, params, lowTree);
/*      */           } 
/*      */         } 
/* 1162 */         pr.setLower(low);
/* 1163 */         pr.setGreater(high);
/* 1164 */         iGuard = iLow + 2;
/* 1165 */         if (tree.getChildCount() > iGuard) {
/* 1166 */           Tree guardCondTree = tree.getChild(iGuard).getChild(0);
/* 1167 */           Expression iexp = scannExpr(guardCondTree, compoundType, contextName);
/* 1168 */           pr.setGuard(iexp);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 48:
/* 1175 */         firstType = tree.getChild(0).getType();
/* 1176 */         typeInd = 0;
/*      */         
/* 1178 */         if (firstType == 76) {
/* 1179 */           typeInd++;
/*      */         }
/* 1181 */         if (tree.getChild(typeInd).getType() == 77) {
/* 1182 */           typeInd++;
/*      */         }
/*      */         
/* 1185 */         for (iName = typeInd + 1; iName < tree.getChildCount(); iName++) {
/* 1186 */           Tree tData = tree.getChild(iName);
/* 1187 */           Object o = getElement(tData);
/* 1188 */           Variable v = null;
/* 1189 */           if (o instanceof Variable) {
/* 1190 */             v = (Variable)o;
/* 1191 */           } else if (o instanceof InterfaceVariable) {
/* 1192 */             InterfaceVariable iv = (InterfaceVariable)o;
/*      */             
/* 1194 */             v = ((VariableDefinitionBinding)iv.getVariableBinding().get(0)).getVariable();
/*      */           } 
/* 1196 */           if (v != null && tData.getChildCount() > 0) {
/*      */             
/* 1198 */             Tree t = tData.getChild(0);
/* 1199 */             v.setInitialValue(scannExpr(t, compoundType, contextName));
/*      */           } 
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 59:
/*      */         return;
/*      */       
/*      */       case 39:
/* 1209 */         at = (AtomType)compoundType;
/* 1210 */         pn = (PetriNet)at.getBehavior();
/* 1211 */         pn.getTransition().add(scannTransition(tree, at, contextName));
/*      */         return;
/*      */       case 28:
/* 1214 */         op = ModulesFactory.eINSTANCE.createOpaqueElement();
/* 1215 */         text = tree.getText();
/* 1216 */         textLength = text.length();
/* 1217 */         code = text.substring(2, textLength - 2);
/* 1218 */         op.setBody(code);
/*      */         
/* 1220 */         if (tree.getChildCount() > 0)
/*      */         {
/* 1222 */           op.setIsHeader(true);
/*      */         }
/*      */         
/* 1225 */         if (compoundType instanceof Module) {
/* 1226 */           Module sys = (Module)compoundType;
/* 1227 */           sys.getDeclaration().add(op); break;
/*      */         } 
/* 1229 */         if (compoundType instanceof PartType) {
/* 1230 */           PartType pt = (PartType)compoundType;
/* 1231 */           pt.getDeclaration().add(op);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1240 */     for (int i = 0; i < tree.getChildCount(); i++) {
/* 1241 */       completeModel(tree.getChild(i), (NamedElement)compoundType, contextName);
/*      */     }
/*      */ 
/*      */     
/* 1245 */     if (compoundType instanceof ComponentType) {
/*      */       
/* 1247 */       ComponentType comp = (ComponentType)compoundType;
/* 1248 */       for (Port p : comp.getPort()) {
/* 1249 */         Binding b = p.getBinding();
/* 1250 */         if (b instanceof DefinitionBinding) {
/*      */ 
/*      */           
/* 1253 */           DefinitionBinding db = (DefinitionBinding)b;
/* 1254 */           p.setType(db.getDefinition().getType());
/*      */         } 
/*      */       } 
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private BipType resolveType(Tree tree, Class c, String typeName) {
/* 1271 */     String libName = null;
/* 1272 */     String simpleTypeName = null;
/* 1273 */     String fullTypeName = null;
/*      */     
/* 1275 */     if (tree.getType() == 35) {
/*      */       
/* 1277 */       libName = tree.getChild(0).getText();
/* 1278 */       simpleTypeName = tree.getChild(1).getText();
/* 1279 */       fullTypeName = libName + "." + simpleTypeName;
/*      */     } else {
/* 1281 */       fullTypeName = tree.getText();
/* 1282 */       simpleTypeName = fullTypeName;
/*      */     } 
/*      */     
/* 1285 */     NamedElement ne = getNamedElement(fullTypeName, null, null);
/*      */     
/* 1287 */     if (ne == null) {
/* 1288 */       if (libName == null) {
/* 1289 */         libName = "global context";
/*      */       }
/* 1291 */       String[] params = { typeName, simpleTypeName, libName };
/* 1292 */       this.error.sendError(4, params, tree);
/*      */     } else {
/* 1294 */       if (c.isInstance(ne)) {
/* 1295 */         return (BipType)ne;
/*      */       }
/* 1297 */       String[] params = { ne.getName(), typeName };
/* 1298 */       this.error.sendError(2, params, tree);
/*      */     } 
/* 1300 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addActualParameters(Tree tree, int index, NamedElement currentObj, String contextName, List<Expression> actual, ParameterizedElement type) {
/* 1306 */     if (tree.getChildCount() > index) {
/* 1307 */       Tree dataParamT = tree.getChild(index);
/* 1308 */       if (dataParamT.getType() == 18) {
/* 1309 */         for (int i = 0; i < dataParamT.getChildCount(); i++) {
/* 1310 */           Expression paramVal = scannExpr(dataParamT.getChild(i), currentObj, contextName);
/*      */           
/* 1312 */           if (paramVal != null)
/*      */           {
/*      */ 
/*      */             
/* 1316 */             actual.add(paramVal);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PriorityElement scannInteraction(Tree tree, ComponentType comp, String contextName) {
/* 1329 */     Tree interT = tree.getChild(0);
/*      */     
/* 1331 */     NamedElement part = null;
/* 1332 */     String cnxName = null;
/* 1333 */     if (interT.getType() == 35) {
/* 1334 */       String libName = interT.getChild(0).getText();
/* 1335 */       cnxName = interT.getChild(1).getText();
/* 1336 */       part = getNamedElement(cnxName, null, libName);
/*      */     } else {
/* 1338 */       if (interT.getType() == 57) {
/* 1339 */         return null;
/*      */       }
/*      */       
/* 1342 */       cnxName = interT.getText();
/* 1343 */       part = getNamedElement(cnxName, null, comp.getName());
/*      */     } 
/*      */     
/* 1346 */     if (part instanceof ConnectorType) {
/* 1347 */       ConnectorTypeReference ctr = PrioritiesFactory.eINSTANCE.createConnectorTypeReference();
/* 1348 */       ctr.setTarget((ConnectorType)part);
/* 1349 */       return (PriorityElement)ctr;
/*      */     } 
/*      */     
/* 1352 */     Interaction inter = InteractionsFactory.eINSTANCE.createInteraction();
/*      */     
/* 1354 */     if (part instanceof Connector) {
/* 1355 */       PartElementReference per = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 1356 */       inter.setConnector(per);
/* 1357 */       per.setTargetPart((Part)part);
/* 1358 */       inter.setConnector(per);
/*      */     }
/* 1360 */     else if (part instanceof PortDefinition) {
/* 1361 */       PortDefinitionReference pdr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 1362 */       pdr.setTarget((PortDefinition)part);
/* 1363 */       inter.getPort().add(pdr);
/*      */     } else {
/*      */       
/* 1366 */       String[] params = { "component or connector", cnxName, comp.getName() };
/* 1367 */       this.error.sendError(4, params, tree);
/*      */     } 
/* 1369 */     int i = 1;
/* 1370 */     while (i < tree.getChildCount() && tree.getChild(i).getType() == 52)
/*      */     {
/* 1372 */       i++;
/*      */     }
/*      */     
/* 1375 */     for (; i < tree.getChildCount(); i++) {
/* 1376 */       Tree pT = tree.getChild(i);
/* 1377 */       InnerPortReference ipr = (InnerPortReference)resolveInnerPortReference(pT, (BipType)comp, null);
/* 1378 */       if (ipr != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1384 */         inter.getPort().add(ipr);
/*      */       }
/*      */     } 
/* 1387 */     return (PriorityElement)inter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConnector(Tree tree, CompoundType compound, String contextName) {
/* 1394 */     ConnectorType ct = (ConnectorType)resolveType(tree.getChild(0), ConnectorType.class, "connector type");
/*      */ 
/*      */ 
/*      */     
/* 1398 */     Connector cnx = (Connector)getElement(tree);
/* 1399 */     cnx.setType(ct);
/*      */ 
/*      */     
/* 1402 */     String name = tree.getChild(1).getText();
/* 1403 */     cnx.setName(name);
/*      */ 
/*      */     
/* 1406 */     int index = 2;
/*      */     
/* 1408 */     Tree portsTree = tree.getChild(index);
/*      */     
/* 1410 */     while (portsTree.getType() == 52) {
/* 1411 */       Tree multiple = portsTree;
/* 1412 */       for (int iMult = 0; iMult < multiple.getChildCount(); iMult++) {
/* 1413 */         Tree t = multiple.getChild(iMult);
/* 1414 */         Expression iexp = scannExpr(t, compound, contextName);
/*      */         
/* 1416 */         cnx.getMultiplicitySpecification().add(iexp);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1421 */       index++;
/* 1422 */       portsTree = tree.getChild(index);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1427 */     for (int i = 0; i < portsTree.getChildCount(); i++) {
/* 1428 */       Tree pT = portsTree.getChild(i);
/* 1429 */       ActualPortParameter ipr = resolveInnerPortReference(pT, (BipType)compound, null);
/* 1430 */       if (ipr != null)
/*      */       {
/*      */ 
/*      */         
/* 1434 */         cnx.getActualPort().add(ipr);
/*      */       }
/*      */     } 
/* 1437 */     index++;
/*      */ 
/*      */     
/* 1440 */     addActualParameters(tree, index, (NamedElement)compound, contextName, (List<Expression>)cnx.getActualData(), (ParameterizedElement)ct);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void resolveInnerVariableReference(VariableExportBinding eb, Tree tree, BipType compound) {
/* 1446 */     InterfaceVariable iv = null;
/* 1447 */     PartElementReference per = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 1448 */     ComponentType ct = null;
/*      */ 
/*      */     
/* 1451 */     String instName = tree.getText();
/*      */ 
/*      */     
/* 1454 */     NamedElement part = getNamedElement(instName, null, compound.getName());
/* 1455 */     if (part instanceof Component) {
/* 1456 */       Component comp = (Component)part;
/* 1457 */       per.setTargetPart((Part)comp);
/* 1458 */       ct = comp.getType();
/*      */     } else {
/* 1460 */       String[] params = { "instance", instName, compound.getName() };
/* 1461 */       this.error.sendError(4, params, tree.getChild(0));
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1466 */     if (tree.getChildCount() > 0) {
/* 1467 */       for (int i = 0; i < tree.getChildCount(); ) {
/* 1468 */         Tree t = tree.getChild(i);
/* 1469 */         if (t.getType() == 52) {
/* 1470 */           Expression exp = scannExpr(t.getChild(0), compound, compound.getName());
/* 1471 */           per.getIndex().add(exp);
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*      */       
/* 1477 */       Tree lastTree = tree.getChild(tree.getChildCount() - 1);
/* 1478 */       if (lastTree.getType() == 35) {
/* 1479 */         String varName = lastTree.getChild(0).getText();
/*      */ 
/*      */         
/* 1482 */         if (ct != null) iv = getComponentInterfaceVariable(ct, varName); 
/* 1483 */         if (iv == null) {
/* 1484 */           String[] params = { "interface variable", varName, ct.getName() };
/* 1485 */           this.error.sendError(4, params, tree.getChild(0));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1490 */     eb.setTargetInterfaceVariable(iv);
/* 1491 */     eb.setTargetInstance(per);
/*      */   }
/*      */ 
/*      */   
/*      */   private ActualPortParameter resolveInnerPortReference(Tree tree, BipType compound, InnerPortSpecification ips) {
/*      */     InnerPortSpecification local_ips;
/* 1497 */     if (tree.getType() == 50) {
/* 1498 */       ConditionalActualPortParameter capp = InteractionsFactory.eINSTANCE.createConditionalActualPortParameter();
/* 1499 */       capp.setExpression(scannExpr(tree.getChild(0), compound, compound.getName()));
/* 1500 */       capp.setTrueCase(resolveInnerPortReference(tree.getChild(1), compound, ips));
/* 1501 */       capp.setFalseCase(resolveInnerPortReference(tree.getChild(2), compound, ips));
/* 1502 */       return (ActualPortParameter)capp;
/*      */     } 
/* 1504 */     InnerPortReference ipr = null;
/*      */     
/* 1506 */     if (ips == null)
/* 1507 */     { ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 1508 */       InnerPortReference innerPortReference = ipr; }
/* 1509 */     else { local_ips = ips; }
/* 1510 */      PartElementReference per = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 1511 */     ComponentType ct = null;
/* 1512 */     ConnectorType cnxt = null;
/* 1513 */     local_ips.setTargetInstance(per);
/*      */     
/* 1515 */     boolean isInstancePort = false;
/*      */     
/* 1517 */     String instOrCnxName = tree.getText();
/*      */ 
/*      */     
/* 1520 */     NamedElement part = getNamedElement(instOrCnxName, null, compound.getName());
/* 1521 */     if (part instanceof Component) {
/* 1522 */       Component comp = (Component)part;
/* 1523 */       per.setTargetPart((Part)comp);
/* 1524 */       isInstancePort = true;
/* 1525 */       ct = comp.getType();
/* 1526 */     } else if (part instanceof Connector) {
/* 1527 */       Connector cnx = (Connector)part;
/* 1528 */       per.setTargetPart((Part)cnx);
/* 1529 */       cnxt = cnx.getType();
/* 1530 */       if (cnxt != null)
/*      */       {
/*      */ 
/*      */         
/* 1534 */         local_ips.setTargetPort(cnxt.getPort());
/*      */       }
/* 1536 */       isInstancePort = false;
/*      */     } else {
/* 1538 */       String[] params = { "instance", instOrCnxName, compound.getName() };
/* 1539 */       this.error.sendError(4, params, tree.getChild(0));
/* 1540 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1544 */     if (tree.getChildCount() > 0) {
/* 1545 */       for (int i = 0; i < tree.getChildCount(); ) {
/* 1546 */         Tree t = tree.getChild(i);
/* 1547 */         if (t.getType() == 52) {
/* 1548 */           Expression exp = scannExpr(t.getChild(0), compound, compound.getName());
/* 1549 */           per.getIndex().add(exp);
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*      */       
/* 1555 */       Tree lastTree = tree.getChild(tree.getChildCount() - 1);
/* 1556 */       if (lastTree.getType() == 35) {
/* 1557 */         String portName = lastTree.getChild(0).getText();
/* 1558 */         if (isInstancePort) {
/*      */           
/* 1560 */           if (ct != null) {
/* 1561 */             Port p = getComponentPort(ct, portName);
/* 1562 */             if (p != null) {
/* 1563 */               local_ips.setTargetPort(p);
/*      */             } else {
/* 1565 */               String[] params = { "port", portName, ct.getName() };
/* 1566 */               this.error.sendError(4, params, tree.getChild(0));
/*      */             } 
/*      */           } 
/* 1569 */         } else if (cnxt != null) {
/*      */           
/* 1571 */           if (cnxt.getPort() == null || !cnxt.getPort().getName().equals(portName))
/*      */           {
/* 1573 */             String[] params = { portName, cnxt.getName() };
/* 1574 */             this.error.sendError(5, params, tree.getChild(0));
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1579 */       } else if (isInstancePort) {
/*      */         
/* 1581 */         String[] params = { instOrCnxName };
/* 1582 */         this.error.sendError(6, params, tree.getChild(0));
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1590 */     return (ActualPortParameter)ipr;
/*      */   }
/*      */ 
/*      */   
/*      */   private Port getComponentPort(ComponentType ct, String portName) {
/* 1595 */     if (ct != null) {
/* 1596 */       for (Port p : ct.getPort()) {
/* 1597 */         if (p.getName().equals(portName)) {
/* 1598 */           return p;
/*      */         }
/*      */       } 
/*      */     }
/* 1602 */     NamedElement ne = getNamedElement(portName, ct, ct.getName());
/* 1603 */     if (ne instanceof Port) {
/* 1604 */       return (Port)ne;
/*      */     }
/* 1606 */     return null;
/*      */   }
/*      */   
/*      */   private InterfaceVariable getComponentInterfaceVariable(ComponentType ct, String varName) {
/* 1610 */     if (ct != null) {
/* 1611 */       for (InterfaceVariable iv : ct.getInterfaceVariable()) {
/* 1612 */         if (iv.getName().equals(varName)) {
/* 1613 */           return iv;
/*      */         }
/*      */       } 
/*      */     }
/* 1617 */     NamedElement ne = getNamedElement(varName, ct, ct.getName());
/* 1618 */     if (ne instanceof InterfaceVariable) {
/* 1619 */       return (InterfaceVariable)ne;
/*      */     }
/* 1621 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Transition scannTransition(Tree tree, AtomType at, String contextName) {
/* 1630 */     Transition t = BehaviorsFactory.eINSTANCE.createTransition();
/*      */ 
/*      */     
/* 1633 */     Tree portExpTree = tree.getChild(0);
/* 1634 */     t.setTrigger((PortExpression)scannPortExpr(portExpTree, at, contextName));
/*      */     
/* 1636 */     Tree fromTree = tree.getChild(1);
/* 1637 */     addPlaces(fromTree, (List<State>)t.getOrigin(), at, contextName);
/*      */     
/* 1639 */     Tree toTree = tree.getChild(2);
/* 1640 */     addPlaces(toTree, (List<State>)t.getDestination(), at, contextName);
/*      */     
/* 1642 */     int count = tree.getChildCount();
/* 1643 */     for (int i = 3; i < count; i++) {
/* 1644 */       Tree tr = tree.getChild(i);
/* 1645 */       if (tr.getType() == 40) {
/* 1646 */         t.setGuard(scannExpr(tr.getChild(0), at, contextName));
/* 1647 */       } else if (tr.getType() == 63) {
/* 1648 */         TimeSpecification ts = scannTimedGuard(tr, at, contextName);
/* 1649 */         ts.setUrgency(UrgencyKind.EAGER);
/* 1650 */         t.setTimeSpecification(ts);
/* 1651 */       } else if (tr.getType() == 65) {
/* 1652 */         TimeSpecification ts = scannTimedGuard(tr, at, contextName);
/* 1653 */         ts.setUrgency(UrgencyKind.LAZY);
/* 1654 */         t.setTimeSpecification(ts);
/* 1655 */       } else if (tr.getType() == 64) {
/* 1656 */         TimeSpecification ts = scannTimedGuard(tr, at, contextName);
/* 1657 */         ts.setUrgency(UrgencyKind.DELAYABLE);
/* 1658 */         t.setTimeSpecification(ts);
/* 1659 */       } else if (tr.getType() == 73) {
/* 1660 */         TimeReset reset = scannTimeReset(tr, at, contextName);
/* 1661 */         t.setTimeReset(reset);
/* 1662 */       } else if (tr.getType() == 74) {
/* 1663 */         t.setAction(scannAction(tr.getChild(0), at, contextName));
/*      */       } 
/*      */     } 
/* 1666 */     return t;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public TimeReset scannTimeReset(Tree tree, Object currentObj, String contextName) {
/* 1672 */     TimeReset reset = TimeFactory.eINSTANCE.createTimeReset();
/*      */     
/* 1674 */     for (int i = 0; i < tree.getChildCount(); i++) {
/* 1675 */       Tree interTree = tree.getChild(i);
/* 1676 */       String name = interTree.getText();
/* 1677 */       NamedElement ne = getNamedElement(name, currentObj, contextName);
/*      */       
/* 1679 */       if (ne == null) {
/* 1680 */         String[] params = { "clock", name, contextName };
/* 1681 */         this.error.sendError(4, params, interTree.getChild(0));
/* 1682 */       } else if (ne instanceof TimedVariable) {
/* 1683 */         VariableReference vr = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 1684 */         vr.setTargetVariable((Variable)ne);
/* 1685 */         reset.getClock().add(vr);
/*      */       } else {
/* 1687 */         String[] params = { name, "clock" };
/* 1688 */         this.error.sendError(2, params, interTree.getChild(0));
/*      */       } 
/*      */     } 
/*      */     
/* 1692 */     return reset;
/*      */   }
/*      */   
/*      */   private void addPlaces(Tree tree, List<State> l, AtomType at, String contextName) {
/* 1696 */     for (int i = 0; i < tree.getChildCount(); i++) {
/* 1697 */       NamedElement place = getNamedElement(tree.getChild(i).getText(), at, contextName);
/* 1698 */       if (place instanceof State) {
/* 1699 */         l.add((State)place);
/*      */       } else {
/* 1701 */         String[] params = { "place", tree.getChild(i).getText(), contextName };
/* 1702 */         this.error.sendError(4, params, tree.getChild(0));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void defineConnectorType(Tree define, ConnectorType ct, String contextName) {
/* 1717 */     ACExpression aCExpression = scannPortExpr(define.getChild(0), ct, contextName);
/* 1718 */     ct.setDefinition((PortExpression)aCExpression);
/* 1719 */     for (int i = 1; i < define.getChildCount(); i++) {
/* 1720 */       Tree interTree = define.getChild(i);
/* 1721 */       if (interTree.getType() == 39) {
/* 1722 */         InteractionSpecification is = scannInteractionDef(interTree, ct, contextName);
/* 1723 */         ct.getInteractionSpecification().add(is);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private InteractionSpecification scannInteractionDef(Tree interTree, ConnectorType ct, String contextName) {
/* 1731 */     InteractionSpecification is = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/* 1732 */     Interaction inter = InteractionsFactory.eINSTANCE.createInteraction();
/* 1733 */     is.setInteraction(inter);
/* 1734 */     for (int i = 0; i < interTree.getChildCount(); i++) {
/* 1735 */       PortReference p; Tree child = interTree.getChild(i);
/* 1736 */       switch (child.getType()) {
/*      */         case 40:
/* 1738 */           is.setGuard(scannExpr(child.getChild(0), ct, contextName));
/*      */           break;
/*      */         case 41:
/* 1741 */           is.setUpAction(scannAction(child.getChild(0), ct, contextName));
/*      */           break;
/*      */         case 42:
/* 1744 */           is.setDownAction(scannAction(child.getChild(0), ct, contextName));
/*      */           break;
/*      */         
/*      */         default:
/* 1748 */           p = resolvePortReference(child.getText(), ct, contextName, child);
/* 1749 */           if (p == null) {
/* 1750 */             String[] params = { "port", child.getText(), contextName };
/* 1751 */             this.error.sendError(4, params, child); break;
/*      */           } 
/* 1753 */           inter.getPort().add(p);
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1759 */     return is; } public ACExpression scannPortExpr(Tree tree, Object currentObj, String contextName) { ACTyping aCTyping1; ACUnion aCUnion1; ACFusion aCFusion1; PortReference portReference; ACTyping at; ACUnion au; int i;
/*      */     ACFusion af;
/*      */     String name;
/*      */     int j;
/* 1763 */     ACExpression pe = null;
/* 1764 */     switch (tree.getType())
/*      */     { case 14:
/*      */       case 79:
/* 1767 */         at = PortExpressionsFactory.eINSTANCE.createACTyping();
/* 1768 */         at.setType(ACTypingKind.TRIG);
/* 1769 */         at.setOperand(scannPortExpr(tree.getChild(0), currentObj, contextName));
/*      */         
/* 1771 */         return (ACExpression)at;
/*      */       
/*      */       case 12:
/* 1774 */         au = PortExpressionsFactory.eINSTANCE.createACUnion();
/* 1775 */         for (i = 0; i < tree.getChildCount(); i++) {
/* 1776 */           ACExpression op = scannPortExpr(tree.getChild(i), currentObj, contextName);
/*      */           
/* 1778 */           au.getOperand().add(op);
/*      */         } 
/* 1780 */         aCUnion1 = au;
/*      */       case 13:
/* 1782 */         if (tree.getChildCount() == 1)
/*      */         {
/*      */           
/* 1785 */           return scannPortExpr(tree.getChild(0), currentObj, contextName);
/*      */         }
/* 1787 */         af = PortExpressionsFactory.eINSTANCE.createACFusion();
/* 1788 */         for (j = 0; j < tree.getChildCount(); j++) {
/* 1789 */           ACExpression op = scannPortExpr(tree.getChild(j), currentObj, contextName);
/*      */           
/* 1791 */           if (op != null)
/*      */           {
/*      */ 
/*      */             
/* 1795 */             af.getOperand().add(op);
/*      */           }
/*      */         } 
/* 1798 */         return (ACExpression)af;
/*      */ 
/*      */       
/*      */       case 24:
/* 1802 */         name = tree.getText();
/* 1803 */         portReference = resolvePortReference(name, currentObj, contextName, tree);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1810 */         return (ACExpression)portReference; }  this.out.println("should not append"); return (ACExpression)portReference; } public Action scannAction(Tree tree, Object currentObj, String contextName) { UnaryExpression unaryExp;
/*      */     AssignmentAction aa, plusAssign, minusAssign, divAssign, multAssign, modAssign;
/*      */     IfAction ia;
/*      */     OpaqueElement oe;
/*      */     String text;
/*      */     int textLength;
/*      */     String code;
/*      */     CompositeAction ca;
/*      */     int i;
/*      */     FunctionCallExpression fce;
/*      */     int j;
/* 1821 */     switch (tree.getType()) {
/*      */       case 8:
/* 1823 */         unaryExp = ExpressionsFactory.eINSTANCE.createUnaryExpression();
/* 1824 */         unaryExp.setOperand(scannExpr(tree.getChild(0), currentObj, contextName));
/* 1825 */         unaryExp.setOperator(UnaryOperator.INCREMENT);
/* 1826 */         return (Action)unaryExp;
/*      */       
/*      */       case 10:
/* 1829 */         unaryExp = ExpressionsFactory.eINSTANCE.createUnaryExpression();
/* 1830 */         unaryExp.setOperand(scannExpr(tree.getChild(0), currentObj, contextName));
/* 1831 */         unaryExp.setOperator(UnaryOperator.INCREMENT);
/* 1832 */         unaryExp.setPostfix(true);
/* 1833 */         return (Action)unaryExp;
/*      */       
/*      */       case 9:
/* 1836 */         unaryExp = ExpressionsFactory.eINSTANCE.createUnaryExpression();
/* 1837 */         unaryExp.setOperand(scannExpr(tree.getChild(0), currentObj, contextName));
/* 1838 */         unaryExp.setOperator(UnaryOperator.DECREMENT);
/* 1839 */         return (Action)unaryExp;
/*      */       
/*      */       case 11:
/* 1842 */         unaryExp = ExpressionsFactory.eINSTANCE.createUnaryExpression();
/* 1843 */         unaryExp.setOperand(scannExpr(tree.getChild(0), currentObj, contextName));
/* 1844 */         unaryExp.setOperator(UnaryOperator.DECREMENT);
/* 1845 */         unaryExp.setPostfix(true);
/* 1846 */         return (Action)unaryExp;
/*      */       
/*      */       case 58:
/* 1849 */         aa = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1850 */         aa.setAssignedTarget(scannTarget(tree.getChild(0), currentObj, contextName));
/* 1851 */         aa.setAssignedValue(scannExpr(tree.getChild(1), currentObj, contextName));
/* 1852 */         aa.setType(AssignType.ASSIGN);
/* 1853 */         return (Action)aa;
/*      */       
/*      */       case 106:
/* 1856 */         plusAssign = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1857 */         plusAssign.setAssignedTarget(scannTarget(tree.getChild(0), currentObj, contextName));
/* 1858 */         plusAssign.setAssignedValue(scannExpr(tree.getChild(1), currentObj, contextName));
/* 1859 */         plusAssign.setType(AssignType.PLUS_ASSIGN);
/* 1860 */         return (Action)plusAssign;
/*      */       
/*      */       case 107:
/* 1863 */         minusAssign = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1864 */         minusAssign.setAssignedTarget(scannTarget(tree.getChild(0), currentObj, contextName));
/* 1865 */         minusAssign.setAssignedValue(scannExpr(tree.getChild(1), currentObj, contextName));
/* 1866 */         minusAssign.setType(AssignType.MINUS_ASSIGN);
/* 1867 */         return (Action)minusAssign;
/*      */       
/*      */       case 109:
/* 1870 */         divAssign = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1871 */         divAssign.setAssignedTarget(scannTarget(tree.getChild(0), currentObj, contextName));
/* 1872 */         divAssign.setAssignedValue(scannExpr(tree.getChild(1), currentObj, contextName));
/* 1873 */         divAssign.setType(AssignType.DIV_ASSIGN);
/* 1874 */         return (Action)divAssign;
/*      */       
/*      */       case 108:
/* 1877 */         multAssign = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1878 */         multAssign.setAssignedTarget(scannTarget(tree.getChild(0), currentObj, contextName));
/* 1879 */         multAssign.setAssignedValue(scannExpr(tree.getChild(1), currentObj, contextName));
/* 1880 */         multAssign.setType(AssignType.MULT_ASSIGN);
/* 1881 */         return (Action)multAssign;
/*      */       
/*      */       case 110:
/* 1884 */         modAssign = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 1885 */         modAssign.setAssignedTarget(scannTarget(tree.getChild(0), currentObj, contextName));
/* 1886 */         modAssign.setAssignedValue(scannExpr(tree.getChild(1), currentObj, contextName));
/* 1887 */         modAssign.setType(AssignType.MOD_ASSIGN);
/* 1888 */         return (Action)modAssign;
/*      */       
/*      */       case 102:
/* 1891 */         ia = ActionsFactory.eINSTANCE.createIfAction();
/* 1892 */         ia.setCondition(scannExpr(tree.getChild(0), currentObj, contextName));
/* 1893 */         ia.setIfCase(scannAction(tree.getChild(1), currentObj, contextName));
/* 1894 */         if (tree.getChildCount() > 2) {
/* 1895 */           ia.setElseCase(scannAction(tree.getChild(3), currentObj, contextName));
/*      */         }
/* 1897 */         return (Action)ia;
/*      */       
/*      */       case 28:
/* 1900 */         oe = ModulesFactory.eINSTANCE.createOpaqueElement();
/* 1901 */         text = tree.getText();
/* 1902 */         textLength = text.length();
/* 1903 */         code = text.substring(2, textLength - 2);
/* 1904 */         oe.setBody(code);
/* 1905 */         return (Action)oe;
/*      */       
/*      */       case 104:
/* 1908 */         ca = ActionsFactory.eINSTANCE.createCompositeAction();
/* 1909 */         for (i = 0; i < tree.getChildCount(); i++) {
/* 1910 */           Action act = scannAction(tree.getChild(i), currentObj, contextName);
/* 1911 */           if (act != null) ca.getContent().add(act); 
/*      */         } 
/* 1913 */         return (Action)ca;
/*      */ 
/*      */       
/*      */       case 32:
/* 1917 */         fce = ExpressionsFactory.eINSTANCE.createFunctionCallExpression();
/* 1918 */         scannFunctionCall(tree.getChild(0), fce, currentObj, contextName);
/*      */ 
/*      */         
/* 1921 */         for (j = 1; j < tree.getChildCount(); j++) {
/* 1922 */           Expression exp = scannExpr(tree.getChild(j), currentObj, contextName);
/* 1923 */           if (exp != null) fce.getActualData().add(exp); 
/*      */         } 
/* 1925 */         return (Action)fce;
/*      */     } 
/*      */ 
/*      */     
/* 1929 */     String[] params = new String[0];
/* 1930 */     this.error.sendError(20, params, tree);
/*      */ 
/*      */ 
/*      */     
/* 1934 */     return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   public IntegerLiteral scannTimeValue(Tree tree, Object currentObj, String contextName) {
/* 1939 */     IntegerLiteral timeValue = null;
/* 1940 */     int unitFactor = 1000;
/*      */ 
/*      */     
/* 1943 */     if (tree.getType() != 69) {
/*      */       
/* 1945 */       if (tree.getChildCount() != 0)
/*      */       {
/*      */ 
/*      */         
/* 1949 */         if (tree.getChildCount() == 1) {
/* 1950 */           switch (tree.getChild(0).getType()) {
/*      */             case 70:
/* 1952 */               unitFactor = 1000000;
/*      */               break;
/*      */             
/*      */             case 71:
/* 1956 */               unitFactor = 1000;
/*      */               break;
/*      */             
/*      */             case 72:
/* 1960 */               unitFactor = 1;
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */       }
/* 1970 */       timeValue = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/* 1971 */       timeValue.setIValue((new Integer(tree.getText())).intValue() * unitFactor);
/*      */     } 
/*      */     
/* 1974 */     return timeValue;
/*      */   }
/*      */   
/*      */   public TimeSpecification scannTimedGuard(Tree tree, Object currentObj, String contextName) {
/* 1978 */     TimeSpecification ts = TimeFactory.eINSTANCE.createTimeSpecification();
/*      */     
/* 1980 */     for (int i = 0; i < tree.getChildCount(); i++) {
/*      */       
/* 1982 */       TimedConstraint tc = TimeFactory.eINSTANCE.createTimedConstraint();
/* 1983 */       Tree interTree = tree.getChild(i);
/* 1984 */       String clockName = interTree.getChild(0).getText();
/*      */       
/* 1986 */       NamedElement ne = getNamedElement(clockName, currentObj, contextName);
/* 1987 */       if (ne == null) {
/* 1988 */         String[] params = { "clock", clockName, contextName };
/* 1989 */         this.error.sendError(4, params, interTree.getChild(0));
/* 1990 */       } else if (ne instanceof TimedVariable) {
/* 1991 */         VariableReference vr = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 1992 */         vr.setTargetVariable((Variable)ne);
/* 1993 */         tc.setClock(vr);
/*      */       } else {
/* 1995 */         String[] params = { clockName, "clock" };
/* 1996 */         this.error.sendError(2, params, interTree.getChild(0));
/*      */       } 
/* 1998 */       ts.getTimedConstraint().add(tc);
/*      */ 
/*      */       
/* 2001 */       IntegerLiteral lowValue = scannTimeValue(interTree.getChild(1), currentObj, contextName);
/* 2002 */       if (lowValue != null) tc.setLowBound((Expression)lowValue);
/*      */ 
/*      */       
/* 2005 */       IntegerLiteral highValue = scannTimeValue(interTree.getChild(2), currentObj, contextName);
/* 2006 */       if (highValue != null) tc.setHighBound((Expression)highValue);
/*      */     
/*      */     } 
/* 2009 */     return ts;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DataReference scannTarget(Tree tree, Object currentObj, String contextName) {
/* 2019 */     Expression expr = scannExpr(tree, currentObj, contextName, true);
/* 2020 */     if (expr instanceof DataReference) {
/* 2021 */       return (DataReference)expr;
/*      */     }
/*      */ 
/*      */     
/* 2025 */     String[] params = { "data reference" };
/* 2026 */     this.error.sendError(7, params, tree);
/* 2027 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private DataParameter findDataPortParam(PortType pt, String dataName) {
/* 2032 */     if (pt != null) {
/* 2033 */       for (DataParameter dp : pt.getDataParameter()) {
/* 2034 */         if (dp.getName().equals(dataName)) {
/* 2035 */           return dp;
/*      */         }
/*      */       } 
/*      */     }
/* 2039 */     return null;
/*      */   }
/*      */   
/*      */   public Expression scannExpr(Tree tree, Object currentObj, String contextName) {
/* 2043 */     return scannExpr(tree, currentObj, contextName, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Expression scannExpr(Tree tree, Object currentObj, String contextName, boolean isTarget) {
/* 2049 */     Object o = scannExprOrRef(tree, currentObj, contextName, isTarget);
/* 2050 */     if (o != null) {
/*      */ 
/*      */ 
/*      */       
/* 2054 */       if (o instanceof Expression) {
/* 2055 */         return (Expression)o;
/*      */       }
/* 2057 */       String[] params = { tree.toString(), "expression" };
/* 2058 */       this.error.sendError(3, params, tree);
/*      */     } 
/* 2060 */     return null; } public Object scannExprOrRef(Tree tree, Object currentObj, String contextName, boolean isTarget) { UnaryExpression uExpr; Tree leftT, rightT; Object exp; FunctionCallExpression fce; int i; ArrayNavigationExpression ane; Object obj; String txt; Object o; IntegerLiteral il;
/*      */     RealLiteral ir;
/*      */     IndexLiteral indL;
/*      */     String indS;
/*      */     StringLiteral sl;
/* 2065 */     Expression expr = null;
/* 2066 */     switch (tree.getType()) {
/*      */       case 24:
/*      */       case 32:
/*      */       case 35:
/*      */       case 52:
/*      */       case 68:
/*      */       case 98:
/*      */       case 99:
/*      */       case 101:
/*      */         break;
/*      */       default:
/* 2077 */         if (isTarget) {
/*      */           
/* 2079 */           String[] params = { "target expression" };
/* 2080 */           this.error.sendError(7, params, tree);
/*      */         }  break;
/*      */     } 
/* 2083 */     switch (tree.getType()) {
/*      */       case 4:
/* 2085 */         return generateUnary(tree, UnaryOperator.POSITIVE, currentObj, contextName);
/*      */       case 5:
/* 2087 */         return generateUnary(tree, UnaryOperator.NEGATIVE, currentObj, contextName);
/*      */       case 94:
/* 2089 */         return generateUnary(tree, UnaryOperator.LOGICAL_NOT, currentObj, contextName);
/*      */       case 95:
/* 2091 */         return generateUnary(tree, UnaryOperator.BITWISE_NOT, currentObj, contextName);
/*      */       case 6:
/* 2093 */         return generateUnary(tree, UnaryOperator.DEREFERENCE, currentObj, contextName);
/*      */       case 7:
/* 2095 */         return generateUnary(tree, UnaryOperator.REFERENCE, currentObj, contextName);
/*      */       case 8:
/* 2097 */         return generateUnary(tree, UnaryOperator.INCREMENT, currentObj, contextName);
/*      */       case 9:
/* 2099 */         return generateUnary(tree, UnaryOperator.DECREMENT, currentObj, contextName);
/*      */       case 10:
/* 2101 */         uExpr = (UnaryExpression)generateUnary(tree, UnaryOperator.INCREMENT, currentObj, contextName);
/*      */         
/* 2103 */         uExpr.setPostfix(true);
/* 2104 */         return uExpr;
/*      */       case 11:
/* 2106 */         uExpr = (UnaryExpression)generateUnary(tree, UnaryOperator.DECREMENT, currentObj, contextName);
/*      */         
/* 2108 */         uExpr.setPostfix(true);
/* 2109 */         return uExpr;
/*      */       case 78:
/* 2111 */         return generateBinary(tree, BinaryOperator.ADDITION, currentObj, contextName);
/*      */       case 91:
/* 2113 */         return generateBinary(tree, BinaryOperator.SUBSTRACTION, currentObj, contextName);
/*      */       case 57:
/* 2115 */         return generateBinary(tree, BinaryOperator.MULTIPLICATION, currentObj, contextName);
/*      */       case 92:
/* 2117 */         return generateBinary(tree, BinaryOperator.DIVISION, currentObj, contextName);
/*      */       case 93:
/* 2119 */         return generateBinary(tree, BinaryOperator.MODULUS, currentObj, contextName);
/*      */       case 84:
/* 2121 */         return generateBinary(tree, BinaryOperator.EQUALITY, currentObj, contextName);
/*      */       case 85:
/* 2123 */         return generateBinary(tree, BinaryOperator.INEQUALITY, currentObj, contextName);
/*      */       case 56:
/* 2125 */         return generateBinary(tree, BinaryOperator.LESS_THAN, currentObj, contextName);
/*      */       case 86:
/* 2127 */         return generateBinary(tree, BinaryOperator.LESS_THAN_OR_EQUAL, currentObj, contextName);
/*      */       case 88:
/* 2129 */         return generateBinary(tree, BinaryOperator.GREATER_THAN, currentObj, contextName);
/*      */       case 87:
/* 2131 */         return generateBinary(tree, BinaryOperator.GREATER_THAN_OR_EQUAL, currentObj, contextName);
/*      */       case 80:
/* 2133 */         return generateBinary(tree, BinaryOperator.LOGICAL_OR, currentObj, contextName);
/*      */       case 66:
/* 2135 */         return generateBinary(tree, BinaryOperator.LOGICAL_AND, currentObj, contextName);
/*      */       case 81:
/* 2137 */         return generateBinary(tree, BinaryOperator.BITWISE_OR, currentObj, contextName);
/*      */       case 82:
/* 2139 */         return generateBinary(tree, BinaryOperator.BITWISE_XOR, currentObj, contextName);
/*      */       case 83:
/* 2141 */         return generateBinary(tree, BinaryOperator.BITWISE_AND, currentObj, contextName);
/*      */       case 89:
/* 2143 */         return generateBinary(tree, BinaryOperator.LEFT_SHIFT, currentObj, contextName);
/*      */       case 90:
/* 2145 */         return generateBinary(tree, BinaryOperator.RIGHT_SHIFT, currentObj, contextName);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 35:
/*      */       case 98:
/* 2153 */         leftT = tree.getChild(0);
/* 2154 */         rightT = tree.getChild(1);
/*      */         
/* 2156 */         exp = scannExprOrRef(leftT, currentObj, contextName, isTarget);
/* 2157 */         if (!(exp instanceof DataReference) && tree.getType() == 98) {
/*      */           
/* 2159 */           String[] params = { "data" };
/* 2160 */           this.error.sendError(7, params, leftT);
/*      */         } 
/* 2162 */         if (exp instanceof PortParameterReference) {
/* 2163 */           PortParameterReference ppr = (PortParameterReference)exp;
/*      */           
/* 2165 */           if (ppr != null) {
/* 2166 */             RequiredDataParameterReference rdpr = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/* 2167 */             rdpr.setPortReference(ppr);
/* 2168 */             String dataName = rightT.getText();
/* 2169 */             if (ppr.getTarget().getType() != null) {
/* 2170 */               DataParameter dp = findDataPortParam(ppr.getTarget().getType(), dataName);
/* 2171 */               if (dp == null) {
/* 2172 */                 String[] params = { dataName, ppr.getTarget().getName(), ppr.getTarget().getType().getName() };
/* 2173 */                 this.error.sendError(8, params, rightT);
/*      */               } else {
/* 2175 */                 rdpr.setTargetParameter(dp);
/*      */               } 
/* 2177 */               return rdpr;
/*      */             } 
/*      */           }  break;
/* 2180 */         }  if (exp instanceof InnerPortReference) {
/* 2181 */           InnerPortReference ipr = (InnerPortReference)exp;
/*      */           
/* 2183 */           if (ipr != null) {
/* 2184 */             InnerDataParameterReference idpr = ExpressionsFactory.eINSTANCE.createInnerDataParameterReference();
/* 2185 */             idpr.setPortReference(ipr);
/* 2186 */             String dataName = rightT.getText();
/* 2187 */             if (ipr.getTargetPort().getType() != null) {
/* 2188 */               DataParameter dp = findDataPortParam(ipr.getTargetPort().getType(), dataName);
/* 2189 */               if (dp == null) {
/* 2190 */                 String[] params = { dataName, ipr.getTargetPort().getName(), ipr.getTargetPort().getType().getName() };
/* 2191 */                 this.error.sendError(8, params, rightT);
/*      */               } else {
/* 2193 */                 idpr.setTargetParameter(dp);
/*      */               } 
/* 2195 */               return idpr;
/*      */             } 
/*      */           }  break;
/* 2198 */         }  if (exp instanceof DataReference) {
/* 2199 */           FieldNavigationExpression fne = ExpressionsFactory.eINSTANCE.createFieldNavigationExpression();
/* 2200 */           fne.setNavigated((DataReference)exp);
/* 2201 */           fne.setFieldName(rightT.getText());
/* 2202 */           if (tree.getType() == 98) {
/* 2203 */             fne.setIsOnRef(true);
/*      */           } else {
/* 2205 */             fne.setIsOnRef(false);
/*      */           } 
/* 2207 */           return fne;
/* 2208 */         }  if (exp instanceof Component) {
/* 2209 */           Component comp = (Component)exp;
/* 2210 */           PartElementReference per = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 2211 */           per.setTargetPart((Part)exp);
/* 2212 */           Port port = getComponentPort(comp.getType(), rightT.getText());
/* 2213 */           if (port == null) {
/* 2214 */             InterfaceVariable iv = getComponentInterfaceVariable(comp.getType(), rightT.getText());
/* 2215 */             if (iv != null) {
/*      */               
/* 2217 */               InnerInterfaceVariableReference iivr = ExpressionsFactory.eINSTANCE.createInnerInterfaceVariableReference();
/* 2218 */               iivr.setPartElementReference(per);
/* 2219 */               iivr.setTargetInterfaceVariable(iv);
/* 2220 */               return iivr;
/*      */             } 
/*      */             
/* 2223 */             String[] params = { "port or interface variable", rightT.getText(), comp.getType().getName() };
/* 2224 */             this.error.sendError(4, params, rightT);
/*      */             
/*      */             break;
/*      */           } 
/* 2228 */           InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 2229 */           ipr.setTargetInstance(per);
/* 2230 */           ipr.setTargetPort(port);
/* 2231 */           return ipr;
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 32:
/* 2238 */         fce = ExpressionsFactory.eINSTANCE.createFunctionCallExpression();
/* 2239 */         scannFunctionCall(tree.getChild(0), fce, currentObj, contextName);
/*      */ 
/*      */         
/* 2242 */         for (i = 1; i < tree.getChildCount(); i++) {
/* 2243 */           fce.getActualData().add(scannExpr(tree.getChild(i), currentObj, contextName));
/*      */         }
/* 2245 */         return fce;
/*      */       
/*      */       case 52:
/* 2248 */         ane = ExpressionsFactory.eINSTANCE.createArrayNavigationExpression();
/* 2249 */         obj = scannExprOrRef(tree.getChild(0), currentObj, contextName, isTarget);
/* 2250 */         if (obj != null && 
/* 2251 */           obj instanceof DataReference) {
/* 2252 */           DataReference dr = (DataReference)obj;
/* 2253 */           ane.setNavigated(dr);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2258 */         ane.setIndex(scannExpr(tree.getChild(1), currentObj, contextName));
/*      */ 
/*      */         
/* 2261 */         return ane;
/*      */       
/*      */       case 24:
/* 2264 */         txt = tree.getText();
/* 2265 */         o = resolveIdentExpression(txt, currentObj, contextName, tree);
/* 2266 */         return o;
/*      */       case 68:
/* 2268 */         il = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/* 2269 */         il.setIValue((new Integer(tree.getText())).intValue());
/* 2270 */         return il;
/*      */       case 99:
/* 2272 */         ir = ExpressionsFactory.eINSTANCE.createRealLiteral();
/* 2273 */         ir.setRValue(tree.getText());
/* 2274 */         return ir;
/*      */       case 101:
/* 2276 */         indL = ExpressionsFactory.eINSTANCE.createIndexLiteral();
/* 2277 */         indS = tree.getText();
/* 2278 */         indS = indS.substring(1);
/* 2279 */         indL.setId((new Integer(indS)).intValue());
/* 2280 */         return indL;
/*      */       
/*      */       case 100:
/* 2283 */         sl = ExpressionsFactory.eINSTANCE.createStringLiteral();
/* 2284 */         sl.setSValue(tree.getText());
/* 2285 */         return sl;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2290 */     return expr; }
/*      */   
/*      */   private void scannFunctionCall(Tree fctTree, FunctionCallExpression fce, Object currentObj, String contextName) {
/*      */     Tree leftT;
/*      */     Tree rightT;
/*      */     Object exp;
/* 2296 */     switch (fctTree.getType()) {
/*      */       case 24:
/* 2298 */         fce.setFunctionName(fctTree.getText());
/*      */         return;
/*      */       case 35:
/*      */       case 98:
/* 2302 */         leftT = fctTree.getChild(0);
/* 2303 */         rightT = fctTree.getChild(1);
/*      */         
/* 2305 */         exp = scannExprOrRef(leftT, currentObj, contextName, false);
/* 2306 */         if (exp instanceof Expression) {
/* 2307 */           fce.setNavigated((Expression)exp);
/* 2308 */           fce.setFunctionName(rightT.getText());
/* 2309 */           if (fctTree.getType() == 35) {
/* 2310 */             fce.setIsOnRef(false); break;
/*      */           } 
/* 2312 */           fce.setIsOnRef(true);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Expression generateUnary(Tree tree, UnaryOperator op, Object currentObj, String contextName) {
/* 2322 */     UnaryExpression expr = ExpressionsFactory.eINSTANCE.createUnaryExpression();
/* 2323 */     expr.setOperator(op);
/* 2324 */     expr.setOperand(scannExpr(tree.getChild(0), currentObj, contextName));
/* 2325 */     return (Expression)expr;
/*      */   }
/*      */   
/*      */   public Expression generateBinary(Tree tree, BinaryOperator op, Object currentObj, String contextName) {
/* 2329 */     Expression left = null;
/* 2330 */     BinaryExpression expr = null;
/* 2331 */     int nChild = tree.getChildCount();
/* 2332 */     int iChild = 1;
/* 2333 */     left = scannExpr(tree.getChild(0), currentObj, contextName);
/* 2334 */     while (iChild < nChild) {
/* 2335 */       expr = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 2336 */       expr.setOperator(op);
/* 2337 */       expr.setLeftOperand(left);
/* 2338 */       expr.setRightOperand(scannExpr(tree.getChild(iChild), currentObj, contextName));
/* 2339 */       BinaryExpression binaryExpression = expr;
/* 2340 */       iChild++;
/*      */     } 
/* 2342 */     return (Expression)expr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object resolveReference(String name, Object currentObj, String contextName) {
/* 2378 */     NamedElement obj = getNamedElement(name, currentObj, contextName);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2383 */     if (obj instanceof Variable) {
/* 2384 */       Variable var = (Variable)obj;
/* 2385 */       VariableReference vr = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 2386 */       vr.setTargetVariable(var);
/* 2387 */       return vr;
/* 2388 */     }  if (obj instanceof InterfaceVariable) {
/* 2389 */       InterfaceVariable var = (InterfaceVariable)obj;
/* 2390 */       InterfaceVariableReference ivr = ExpressionsFactory.eINSTANCE.createInterfaceVariableReference();
/* 2391 */       ivr.setTargetInterfaceVariable(var);
/* 2392 */       return ivr;
/* 2393 */     }  if (obj instanceof DataParameter) {
/* 2394 */       DataParameter param = (DataParameter)obj;
/* 2395 */       DataParameterReference pr = ExpressionsFactory.eINSTANCE.createDataParameterReference();
/* 2396 */       pr.setTargetParameter(param);
/* 2397 */       return pr;
/* 2398 */     }  if (obj instanceof PortParameter) {
/* 2399 */       PortParameter port = (PortParameter)obj;
/* 2400 */       PortParameterReference pr = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 2401 */       pr.setTarget(port);
/* 2402 */       return pr;
/* 2403 */     }  if (obj instanceof PortDefinition) {
/* 2404 */       PortDefinition port = (PortDefinition)obj;
/* 2405 */       PortDefinitionReference pr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 2406 */       pr.setTarget(port);
/* 2407 */       return pr;
/* 2408 */     }  if (obj instanceof State) {
/* 2409 */       State st = (State)obj;
/* 2410 */       StateReference sr = ExpressionsFactory.eINSTANCE.createStateReference();
/* 2411 */       sr.setTargetState(st);
/* 2412 */       return sr;
/*      */     } 
/* 2414 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PortReference resolvePortReference(String name, Object currentObj, String contextName, Tree t) {
/* 2421 */     NamedElement obj = getNamedElement(name, currentObj, contextName);
/* 2422 */     if (obj == null) {
/* 2423 */       String[] arrayOfString = { "port", name, contextName };
/* 2424 */       this.error.sendError(4, arrayOfString, t);
/*      */       
/* 2426 */       return null;
/*      */     } 
/* 2428 */     if (obj instanceof PortParameter) {
/* 2429 */       PortParameter port = (PortParameter)obj;
/* 2430 */       PortParameterReference pr = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 2431 */       pr.setTarget(port);
/* 2432 */       return (PortReference)pr;
/* 2433 */     }  if (obj instanceof PortDefinition) {
/* 2434 */       PortDefinition port = (PortDefinition)obj;
/* 2435 */       PortDefinitionReference pr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 2436 */       pr.setTarget(port);
/* 2437 */       return (PortReference)pr;
/*      */     } 
/*      */     
/* 2440 */     String[] params = { name, "port" };
/* 2441 */     this.error.sendError(2, params, t);
/*      */     
/* 2443 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Object resolveIdentExpression(String name, Object currentObj, String contextName, Tree t) {
/* 2449 */     Object exp = resolveReference(name, currentObj, contextName);
/*      */     
/* 2451 */     if (exp == null) {
/* 2452 */       if (name.equals("true")) {
/* 2453 */         BooleanLiteral bl = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/* 2454 */         bl.setBValue(true);
/* 2455 */         exp = bl;
/* 2456 */       } else if (name.equals("false")) {
/* 2457 */         BooleanLiteral bl = ExpressionsFactory.eINSTANCE.createBooleanLiteral();
/* 2458 */         bl.setBValue(false);
/* 2459 */         exp = bl;
/* 2460 */       } else if (!name.equals("null")) {
/* 2461 */         if (name.equals("NULL")) {
/* 2462 */           PointerLiteral pl = ExpressionsFactory.eINSTANCE.createPointerLiteral();
/* 2463 */           exp = pl;
/*      */         } else {
/*      */           
/* 2466 */           String[] params = { "data", name, contextName };
/* 2467 */           this.error.sendError(4, params, t);
/* 2468 */           return null;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 2473 */     return exp;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private NamedElement getNamedElement(String name, Object currentObj, String contextName) {
/* 2479 */     NamedElement obj = (NamedElement)this.localDictionnary.get(contextName + "." + name);
/* 2480 */     if (obj == null) {
/* 2481 */       obj = this.namedDictionnary.get(contextName + "." + name);
/*      */     }
/* 2483 */     if (obj == null) {
/* 2484 */       obj = this.namedDictionnary.get(name);
/*      */     }
/*      */     
/* 2487 */     return obj;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\BipScannSyntaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */