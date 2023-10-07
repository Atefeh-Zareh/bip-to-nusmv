/*      */ package ujf.verimag.bip.cxxcodegen;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.AbstractTransition;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*      */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*      */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*      */ import ujf.verimag.bip.Core.Behaviors.Port;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*      */ import ujf.verimag.bip.Core.Behaviors.State;
/*      */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*      */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*      */ import ujf.verimag.bip.Core.Behaviors.VariableDefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*      */ import ujf.verimag.bip.Core.Modules.Declaration;
/*      */ import ujf.verimag.bip.Core.Modules.Module;
/*      */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*      */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*      */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*      */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*      */ import ujf.verimag.bip.Extra.Time.UrgencyKind;
/*      */ import ujf.verimag.bip.cgeneration.CConstruct;
/*      */ import ujf.verimag.bip.cmodel.CAssignStm;
/*      */ import ujf.verimag.bip.cmodel.CBlock;
/*      */ import ujf.verimag.bip.cmodel.CBlockStm;
/*      */ import ujf.verimag.bip.cmodel.CCaseItem;
/*      */ import ujf.verimag.bip.cmodel.CClass;
/*      */ import ujf.verimag.bip.cmodel.CConstructor;
/*      */ import ujf.verimag.bip.cmodel.CCreator;
/*      */ import ujf.verimag.bip.cmodel.CData;
/*      */ import ujf.verimag.bip.cmodel.CEnumType;
/*      */ import ujf.verimag.bip.cmodel.CExpression;
/*      */ import ujf.verimag.bip.cmodel.CFunction;
/*      */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*      */ import ujf.verimag.bip.cmodel.CIfStm;
/*      */ import ujf.verimag.bip.cmodel.CIndexed;
/*      */ import ujf.verimag.bip.cmodel.CInitialization;
/*      */ import ujf.verimag.bip.cmodel.CJump;
/*      */ import ujf.verimag.bip.cmodel.CModule;
/*      */ import ujf.verimag.bip.cmodel.CReturn;
/*      */ import ujf.verimag.bip.cmodel.CSimpleName;
/*      */ import ujf.verimag.bip.cmodel.CStm;
/*      */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*      */ import ujf.verimag.bip.cmodel.CText;
/*      */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*      */ import ujf.verimag.bip.cmodel.CWhileStm;
/*      */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*      */ import ujf.verimag.bip.cmodel.JumpType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CxxAtomTypeGenerator
/*      */ {
/*      */   protected CConstruct cBuilder;
/*   72 */   protected CmodelFactory cFactory = CmodelFactory.eINSTANCE; private CxxExpressionGenerator expGen; private CxxStatementGenerator stmGen; private boolean generatingMultiThreadedCode = false;
/*      */   private boolean forVerification = false;
/*      */   boolean bip_debug;
/*      */   int maxNuberOfOutgoingTransition;
/*      */   Set computedStates;
/*      */   Set computedTrans;
/*      */   
/*      */   public void setDebug(boolean debug) {
/*   80 */     this.bip_debug = debug;
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
/*      */   private void generateDebugFunctions(AtomType at, CClass elementClass, PetriNet behav, boolean isPetri) {
/*      */     CIfStm cIfStm2, cIfStm3;
/*   97 */     int nbSubElements = 0;
/*   98 */     int nbValuedElements = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  107 */     CFunction f = this.cFactory.createCFunction();
/*  108 */     elementClass.getContent().add(f);
/*  109 */     f.setName("getSubElement");
/*  110 */     f.setType("BipDebugElmt *");
/*  111 */     f.setVisibility("public");
/*  112 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/*  113 */     CReturn cReturn2 = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/*      */     
/*  115 */     CFunction f2 = this.cFactory.createCFunction();
/*  116 */     elementClass.getContent().add(f2);
/*  117 */     f2.setName("getSubElementsNames");
/*  118 */     f2.setType("char **");
/*  119 */     f2.setVisibility("public");
/*  120 */     CCreator newExp = this.cFactory.createCCreator();
/*  121 */     newExp.setArrayAllocator(true);
/*  122 */     newExp.setType("char *");
/*  123 */     f2.getContent().add(this.cBuilder.createData("res", "char **", null, (CExpression)newExp));
/*  124 */     f2.getContent().add(this.cBuilder.createData("i", "int", null, this.cBuilder.createLiteral(0)));
/*      */ 
/*      */     
/*  127 */     for (Iterator<PortDefinition> i = at.getPortDefinition().iterator(); i.hasNext(); ) {
/*  128 */       PortDefinition port = i.next();
/*  129 */       boolean isDebugable = false;
/*  130 */       Module m = port.getType().getModule();
/*      */       
/*  132 */       if (m == at.getModule()) {
/*  133 */         isDebugable = true;
/*      */       }
/*      */ 
/*      */       
/*  137 */       if (port.getType().getName().equals("Port")) isDebugable = false; 
/*  138 */       if (isDebugable) {
/*      */         
/*  140 */         CFunctionCall cFunctionCall = this.cBuilder.createFuncCall("strcmp");
/*  141 */         cFunctionCall.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  142 */         cFunctionCall.getArgument().add(this.cBuilder.createLiteral("\"" + port.getName() + "\""));
/*      */         
/*  144 */         CExpression cExpression = this.cBuilder.createOperation((CExpression)cFunctionCall, "==", this.cBuilder.createLiteral(0));
/*      */         
/*  146 */         CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(port.getName()));
/*      */         
/*  148 */         CIfStm cIfStm = this.cBuilder.createIf(cExpression, (CStm)cReturn, (CStm)cReturn2);
/*  149 */         cIfStm2 = cIfStm;
/*      */ 
/*      */         
/*  152 */         nbSubElements++;
/*  153 */         CIndexed cIndexed = this.cFactory.createCIndexed();
/*  154 */         cIndexed.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  155 */         cIndexed.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  156 */         CAssignStm cAssignStm = this.cBuilder.createAssign((CExpression)cIndexed, (CExpression)this.cBuilder.createLiteral("\"" + port.getName() + "\""));
/*  157 */         f2.getContent().add(cAssignStm);
/*  158 */         cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */         
/*  161 */         f2.getContent().add(cAssignStm);
/*      */       } 
/*      */     } 
/*  164 */     f.getContent().add(cIfStm2);
/*  165 */     nbSubElements++;
/*  166 */     CIndexed ires = this.cFactory.createCIndexed();
/*  167 */     ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  168 */     ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  169 */     CAssignStm ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("NULL"));
/*  170 */     f2.getContent().add(ass);
/*  171 */     ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */     
/*  173 */     newExp.getArgument().add(this.cBuilder.createLiteral(nbSubElements));
/*  174 */     f2.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("res")));
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
/*  185 */     f = this.cFactory.createCFunction();
/*  186 */     elementClass.getContent().add(f);
/*  187 */     f.setName("getValue");
/*  188 */     f.setType("char *");
/*  189 */     f.setVisibility("public");
/*  190 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/*  191 */     f.getContent().add(this.cBuilder.createCCode("char* _numericData= new char[100];"));
/*      */ 
/*      */     
/*  194 */     f2 = this.cFactory.createCFunction();
/*  195 */     elementClass.getContent().add(f2);
/*  196 */     f2.setName("getValuedElementNames");
/*  197 */     f2.setType("char **");
/*  198 */     f2.setVisibility("public");
/*  199 */     newExp = this.cFactory.createCCreator();
/*  200 */     newExp.setArrayAllocator(true);
/*  201 */     newExp.setType("char *");
/*  202 */     f2.getContent().add(this.cBuilder.createData("res", "char **", null, (CExpression)newExp));
/*  203 */     f2.getContent().add(this.cBuilder.createData("i", "int", null, this.cBuilder.createLiteral(0)));
/*      */     
/*  205 */     CReturn cReturn1 = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/*  206 */     CFunctionCall strcmp = this.cBuilder.createFuncCall("strcmp");
/*  207 */     strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  208 */     strcmp.getArgument().add(this.cBuilder.createLiteral("\"BIP_STATE\""));
/*  209 */     CExpression cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */ 
/*      */     
/*  212 */     if (isPetri) {
/*  213 */       CBlockStm block = this.cFactory.createCBlockStm();
/*      */       
/*  215 */       for (Iterator<State> iState = behav.getState().listIterator(); iState.hasNext(); ) {
/*  216 */         State state = iState.next();
/*  217 */         CIfStm currentIf = this.cFactory.createCIfStm();
/*      */ 
/*      */         
/*  220 */         CExpression cond2 = this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)this.cBuilder.createFuncCall("token"));
/*      */ 
/*      */         
/*  223 */         CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"" + state.getName() + "\""));
/*      */         
/*  225 */         block.getContent().add(this.cBuilder.createIf(cond2, (CStm)cReturn, null));
/*      */       } 
/*  227 */       CBlockStm cBlockStm1 = block;
/*      */     } else {
/*  229 */       CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"\""));
/*      */       
/*  231 */       for (Iterator<State> iState = behav.getState().listIterator(); iState.hasNext(); ) {
/*  232 */         State state = iState.next();
/*  233 */         CIfStm currentIf = this.cFactory.createCIfStm();
/*      */ 
/*      */         
/*  236 */         CExpression cond2 = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), "==", (CExpression)this.cBuilder.createSimpleName(state.getName()));
/*      */ 
/*      */         
/*  239 */         CReturn cReturn3 = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"" + state.getName() + "\""));
/*      */         
/*  241 */         cIfStm3 = this.cBuilder.createIf(cond2, (CStm)cReturn3, (CStm)cReturn);
/*      */       } 
/*      */     } 
/*      */     
/*  245 */     CIfStm is = this.cBuilder.createIf(cond, (CStm)cIfStm3, (CStm)cReturn1);
/*  246 */     CIfStm cIfStm1 = is;
/*      */     
/*  248 */     nbValuedElements++;
/*  249 */     ires = this.cFactory.createCIndexed();
/*  250 */     ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  251 */     ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  252 */     ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("\"BIP_STATE\""));
/*  253 */     f2.getContent().add(ass);
/*  254 */     ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */     
/*  257 */     f2.getContent().add(ass);
/*      */     
/*  259 */     for (Iterator<PortDefinition> iterator2 = at.getPortDefinition().iterator(); iterator2.hasNext(); ) {
/*  260 */       PortDefinition pd = iterator2.next();
/*  261 */       strcmp = this.cBuilder.createFuncCall("strcmp");
/*  262 */       strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  263 */       strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + pd.getName() + "\""));
/*      */       
/*  265 */       cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */ 
/*      */       
/*  268 */       cIfStm3 = this.cBuilder.createIf(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(pd.getName()), (CExpression)this.cBuilder.createFuncCall("synced")), (CStm)this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"true\"")), (CStm)this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"false\"")));
/*      */ 
/*      */ 
/*      */       
/*  272 */       is = this.cBuilder.createIf(cond, (CStm)cIfStm3, (CStm)cIfStm1);
/*  273 */       cIfStm1 = is;
/*      */       
/*  275 */       nbValuedElements++;
/*  276 */       ires = this.cFactory.createCIndexed();
/*  277 */       ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  278 */       ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  279 */       ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("\"" + pd.getName() + "\""));
/*  280 */       f2.getContent().add(ass);
/*  281 */       ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */       
/*  284 */       f2.getContent().add(ass);
/*      */     } 
/*      */ 
/*      */     
/*  288 */     for (Iterator<State> iterator1 = behav.getState().iterator(); iterator1.hasNext(); ) {
/*  289 */       State st = iterator1.next();
/*  290 */       nbValuedElements++;
/*  291 */       strcmp = this.cBuilder.createFuncCall("strcmp");
/*  292 */       strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  293 */       strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + st.getName() + "\""));
/*      */       
/*  295 */       cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */       
/*  297 */       if (isPetri) {
/*  298 */         CExpression cond2 = this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(st.getName()), (CExpression)this.cBuilder.createFuncCall("token"));
/*      */ 
/*      */ 
/*      */         
/*  302 */         cIfStm3 = this.cBuilder.createIf(cond2, (CStm)this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"true\"")), (CStm)this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"false\"")));
/*      */       }
/*      */       else {
/*      */         
/*  306 */         CExpression cond2 = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), "==", (CExpression)this.cBuilder.createSimpleName(st.getName()));
/*      */ 
/*      */ 
/*      */         
/*  310 */         cIfStm3 = this.cBuilder.createIf(cond2, (CStm)this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"true\"")), (CStm)this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("\"false\"")));
/*      */       } 
/*      */ 
/*      */       
/*  314 */       is = this.cBuilder.createIf(cond, (CStm)cIfStm3, (CStm)cIfStm1);
/*  315 */       cIfStm1 = is;
/*      */       
/*  317 */       nbValuedElements++;
/*  318 */       ires = this.cFactory.createCIndexed();
/*  319 */       ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  320 */       ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  321 */       ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("\"" + st.getName() + "\""));
/*  322 */       f2.getContent().add(ass);
/*  323 */       ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */       
/*  326 */       f2.getContent().add(ass);
/*      */     } 
/*  328 */     for (Iterator<Variable> iterator = at.getVariable().iterator(); iterator.hasNext(); ) {
/*      */       
/*  330 */       Variable var = iterator.next();
/*  331 */       nbValuedElements++;
/*  332 */       strcmp = this.cBuilder.createFuncCall("strcmp");
/*  333 */       strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  334 */       strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + var.getName() + "\""));
/*      */       
/*  336 */       cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */       
/*  338 */       DataType dt = var.getType();
/*  339 */       CBlockStm b = this.cFactory.createCBlockStm();
/*  340 */       String dataType = "";
/*  341 */       if (dt instanceof OpaqueElement) {
/*  342 */         OpaqueElement oe = (OpaqueElement)dt;
/*  343 */         dataType = oe.getBody();
/*      */       } 
/*  345 */       if (dataType.equals("int")) {
/*  346 */         CFunctionCall sprintf = this.cBuilder.createFuncCall("sprintf");
/*  347 */         sprintf.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  348 */         sprintf.getArgument().add(this.cBuilder.createLiteral("\"%d\""));
/*  349 */         sprintf.getArgument().add(this.cBuilder.createSimpleName(var.getName()));
/*  350 */         b.getContent().add(sprintf);
/*  351 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*  352 */       } else if (dataType.equals("float")) {
/*  353 */         CFunctionCall sprintf = this.cBuilder.createFuncCall("sprintf");
/*  354 */         sprintf.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  355 */         sprintf.getArgument().add(this.cBuilder.createLiteral("\"%f\""));
/*  356 */         sprintf.getArgument().add(this.cBuilder.createSimpleName(var.getName()));
/*  357 */         b.getContent().add(sprintf);
/*  358 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*  359 */       } else if (dataType.equals("bool")) {
/*  360 */         CFunctionCall printTrue = this.cBuilder.createFuncCall("sprintf");
/*  361 */         printTrue.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  362 */         printTrue.getArgument().add(this.cBuilder.createLiteral("\"true\""));
/*  363 */         CFunctionCall printFalse = this.cBuilder.createFuncCall("sprintf");
/*  364 */         printFalse.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  365 */         printFalse.getArgument().add(this.cBuilder.createLiteral("\"false\""));
/*  366 */         CIfStm ifPrint = this.cBuilder.createIf((CExpression)this.cBuilder.createSimpleName(var.getName()), (CStm)printTrue, (CStm)printFalse);
/*      */         
/*  368 */         b.getContent().add(ifPrint);
/*  369 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*      */       } else {
/*  371 */         CFunctionCall strrep = this.cBuilder.createFuncCall("strrep_" + dataType);
/*  372 */         strrep.getArgument().add(this.cBuilder.createSimpleName(var.getName()));
/*  373 */         strrep.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*      */         
/*  375 */         b.getContent().add(strrep);
/*      */         
/*  377 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*      */       } 
/*      */       
/*  380 */       is = this.cBuilder.createIf(cond, (CStm)b, (CStm)cIfStm1);
/*  381 */       cIfStm1 = is;
/*      */       
/*  383 */       nbValuedElements++;
/*  384 */       ires = this.cFactory.createCIndexed();
/*  385 */       ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  386 */       ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  387 */       ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("\"" + var.getName() + "\""));
/*  388 */       f2.getContent().add(ass);
/*  389 */       ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */       
/*  392 */       f2.getContent().add(ass);
/*      */     } 
/*      */     
/*  395 */     f.getContent().add(cIfStm1);
/*  396 */     nbValuedElements++;
/*  397 */     ires = this.cFactory.createCIndexed();
/*  398 */     ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  399 */     ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  400 */     ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("NULL"));
/*  401 */     f2.getContent().add(ass);
/*  402 */     ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */     
/*  404 */     newExp.getArgument().add(this.cBuilder.createLiteral(nbValuedElements));
/*  405 */     f2.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("res")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  412 */     f = this.cFactory.createCFunction();
/*  413 */     elementClass.getContent().add(f);
/*  414 */     f.setName("getParent");
/*  415 */     f.setType("BipDebugElmt *");
/*  416 */     f.setVisibility("public");
/*  417 */     f.getContent().add(this.cBuilder.createCCode("return dynamic_cast<BipDebugElmt*>( mHolder);"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void generateType(AtomType at, CModule cmodule, boolean multi) {
/*  425 */     this.generatingMultiThreadedCode = multi;
/*  426 */     PetriNet behav = (PetriNet)at.getBehavior();
/*  427 */     boolean isPetri = behavIsPetri(behav);
/*  428 */     this.expGen.setIsPetriNet(isPetri);
/*  429 */     CClass atomTypeClass = this.cBuilder.createClass(CxxNames.componentTypeClassName(at.getName()), "public", "Atom");
/*      */     
/*  431 */     cmodule.getContent().add(atomTypeClass);
/*  432 */     if (this.bip_debug) {
/*  433 */       atomTypeClass.getSuperClasses().add("BipDebugElmt");
/*  434 */       generateDebugFunctions(at, atomTypeClass, behav, isPetri);
/*      */     } 
/*      */ 
/*      */     
/*  438 */     CConstructor cons = atomTypeConstructor(at);
/*  439 */     atomTypeClass.getContent().add(cons);
/*      */     
/*  441 */     for (Iterator<Declaration> iterator3 = at.getDeclaration().iterator(); iterator3.hasNext(); ) {
/*  442 */       Declaration decl = iterator3.next();
/*  443 */       if (decl instanceof OpaqueElement) {
/*  444 */         OpaqueElement op = (OpaqueElement)decl;
/*  445 */         CText cCode = this.cBuilder.createCCode(op.getBody());
/*  446 */         if (!op.isIsHeader()) {
/*  447 */           cCode.setInBodyFile(true);
/*      */         }
/*  449 */         atomTypeClass.getContent().add(cCode);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  456 */     if (isPetri) {
/*      */       
/*  458 */       for (Iterator<State> iterator5 = behav.getState().iterator(); iterator5.hasNext(); ) {
/*  459 */         State state = iterator5.next();
/*  460 */         atomTypeClass.getContent().add(this.cBuilder.createData(state.getName(), "Place", "public", null));
/*      */       } 
/*      */     } else {
/*      */       
/*  464 */       atomTypeClass.getContent().add(this.cBuilder.createComment("    // States for behavior automata"));
/*  465 */       CEnumType enumType = this.cFactory.createCEnumType();
/*  466 */       atomTypeClass.getContent().add(enumType);
/*  467 */       enumType.setType("STATE");
/*  468 */       enumType.setVisibility("public");
/*      */       
/*  470 */       for (Iterator<State> iterator5 = behav.getState().iterator(); iterator5.hasNext(); ) {
/*  471 */         State state = iterator5.next();
/*  472 */         enumType.getEnumeration().add(this.cBuilder.createLiteral(state.getName()));
/*      */       } 
/*  474 */       atomTypeClass.getContent().add(this.cBuilder.createComment("    // State variable declaration"));
/*  475 */       atomTypeClass.getContent().add(this.cBuilder.createData("BIP_STATE", "STATE", "public", null));
/*      */ 
/*      */       
/*  478 */       State initialState = (State)behav.getInitialState().get(0);
/*  479 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), (CExpression)this.cBuilder.createSimpleName(initialState.getName()));
/*      */       
/*  481 */       cons.getContent().add(ass);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  489 */     for (Iterator<DataParameter> iterator2 = at.getDataParameter().iterator(); iterator2.hasNext(); ) {
/*  490 */       DataParameter dp = iterator2.next();
/*  491 */       String name = dp.getName();
/*  492 */       OpaqueElement dt = (OpaqueElement)dp.getType();
/*  493 */       String paramName = "BIPParam" + dp.getName();
/*  494 */       String typeName = dt.getBody();
/*  495 */       CData d = this.cBuilder.createData(name, typeName, "public", null);
/*  496 */       atomTypeClass.getContent().add(d);
/*  497 */       cons.getArgument().add(this.cBuilder.createArgument(paramName, typeName));
/*  498 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(name), (CExpression)this.cBuilder.createSimpleName(paramName));
/*      */       
/*  500 */       cons.getContent().add(ass);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     for (Iterator<PortDefinition> iterator1 = at.getPortDefinition().iterator(); iterator1.hasNext(); ) {
/*  507 */       PortDefinition pd = iterator1.next();
/*      */       
/*  509 */       String portName = pd.getName();
/*  510 */       String typeName = pd.getType().getName();
/*  511 */       String scopeName = pd.getType().getScope();
/*  512 */       String portTypeName = (scopeName != null) ? (scopeName + "::" + typeName) : typeName;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  517 */       CData d = this.cBuilder.createData(portName, portTypeName + "*", "private", null);
/*  518 */       atomTypeClass.getContent().add(d);
/*      */ 
/*      */       
/*  521 */       CCreator portCreate = this.cFactory.createCCreator();
/*  522 */       portCreate.setType(portTypeName);
/*  523 */       portCreate.getArgument().add(this.cBuilder.createLiteral("\"" + portName + "\""));
/*  524 */       portCreate.getArgument().add(this.cBuilder.createLiteral("this"));
/*      */ 
/*      */       
/*  527 */       for (Iterator<Variable> iData = pd.getExposedVariable().iterator(); iData.hasNext(); ) {
/*  528 */         Variable v = iData.next();
/*  529 */         portCreate.getArgument().add(this.cBuilder.createSimpleName(v.getName()));
/*      */       } 
/*  531 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(portName), (CExpression)portCreate);
/*      */       
/*  533 */       cons.getContent().add(ass);
/*      */       
/*  535 */       if (!isExported(pd, at)) {
/*  536 */         CFunctionCall fc = this.cBuilder.createFuncCall("setInternal");
/*  537 */         cons.getContent().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(portName), (CExpression)fc));
/*      */       } 
/*      */ 
/*      */       
/*  541 */       if (!this.generatingMultiThreadedCode) {
/*  542 */         CFunctionCall fc = this.cBuilder.createFuncCall("mPorts.add");
/*  543 */         fc.getArgument().add(this.cBuilder.createSimpleName(portName));
/*  544 */         cons.getContent().add(fc);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  550 */     for (Iterator<Variable> iterator = at.getVariable().iterator(); iterator.hasNext(); ) {
/*      */       
/*  552 */       Variable v = iterator.next();
/*      */       
/*  554 */       if (v instanceof ujf.verimag.bip.Extra.Time.TimedVariable) {
/*  555 */         String clock = v.getName();
/*  556 */         atomTypeClass.getContent().add(this.cBuilder.createData(clock, "Clock*", "private", null));
/*      */         continue;
/*      */       } 
/*  559 */       if (!v.isIsExternal()) {
/*  560 */         String dataName = v.getName();
/*  561 */         String dataTypeName = ((OpaqueElement)v.getType()).getBody();
/*  562 */         CExpression initExp = this.expGen.generateExpr(v.getInitialValue());
/*  563 */         CData d = this.cBuilder.createData(dataName, dataTypeName, "private", null);
/*  564 */         atomTypeClass.getContent().add(d);
/*  565 */         if (initExp != null) {
/*  566 */           CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(dataName), initExp);
/*  567 */           cons.getContent().add(ass);
/*      */         } 
/*      */       } 
/*      */     } 
/*  571 */     for (Iterator<InterfaceVariable> i = at.getInterfaceVariable().iterator(); i.hasNext(); ) {
/*  572 */       InterfaceVariable iv = i.next();
/*  573 */       VariableDefinitionBinding vdb = (VariableDefinitionBinding)iv.getVariableBinding().get(0);
/*  574 */       Variable v = vdb.getVariable();
/*  575 */       String dataTypeName = ((OpaqueElement)v.getType()).getBody();
/*  576 */       CFunction f = this.cFactory.createCFunction();
/*  577 */       atomTypeClass.getContent().add(f);
/*  578 */       f.setName("get_" + iv.getName());
/*  579 */       f.setType(dataTypeName);
/*  580 */       f.setQualifier("const");
/*  581 */       f.setSpecifier("inline");
/*  582 */       f.setVisibility("public");
/*  583 */       f.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(v.getName())));
/*  584 */       f.setBodyInDecl(true);
/*      */     } 
/*      */ 
/*      */     
/*  588 */     Action initAct = behav.getInitialization();
/*  589 */     if (initAct != null) {
/*  590 */       CStm ca = this.stmGen.generateStm(initAct);
/*  591 */       if (ca != null) cons.getContent().add(ca);
/*      */     
/*      */     } 
/*      */ 
/*      */     
/*  596 */     for (Iterator<Port> iterator4 = at.getPort().iterator(); iterator4.hasNext(); ) {
/*  597 */       Port p = iterator4.next();
/*  598 */       String portName = p.getName();
/*  599 */       String portDefName = ((DefinitionBinding)p.getBinding()).getDefinition().getName();
/*      */       
/*  601 */       PortType pt = p.getType();
/*  602 */       String scopeName = pt.getScope();
/*  603 */       String typeName = (scopeName != null) ? (scopeName + "::" + pt.getName()) : pt.getName();
/*      */ 
/*      */ 
/*      */       
/*  607 */       CFunction f = this.cFactory.createCFunction();
/*  608 */       atomTypeClass.getContent().add(f);
/*  609 */       f.setName("get_" + portName);
/*  610 */       f.setType(CxxNames.portTypeClassName(typeName) + "*");
/*  611 */       f.setQualifier("const");
/*  612 */       f.setSpecifier("inline");
/*  613 */       f.setVisibility("public");
/*  614 */       f.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(portDefName)));
/*  615 */       f.setBodyInDecl(true);
/*      */     } 
/*      */     
/*  618 */     if (multi) {
/*  619 */       generateRun(at, behav, atomTypeClass, isPetri);
/*      */     } else {
/*  621 */       generateInitialize(at, behav, atomTypeClass, isPetri);
/*  622 */       generateExecute(at, behav, atomTypeClass, isPetri);
/*  623 */       atomTypeClass.getContent().add(generateGetState(at, isPetri));
/*  624 */       atomTypeClass.getContent().add(generateSetState(at, isPetri));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isExported(PortDefinition pd, AtomType at) {
/*  632 */     for (Iterator<Port> i = at.getPort().iterator(); i.hasNext(); ) {
/*  633 */       Port p = i.next();
/*  634 */       DefinitionBinding eb = (DefinitionBinding)p.getBinding();
/*  635 */       if (eb.getDefinition() == pd) return true; 
/*      */     } 
/*  637 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean behavIsPetri(PetriNet behav) {
/*  643 */     if (behav.getInitialState().size() > 1) return true; 
/*  644 */     for (Iterator<Transition> i = behav.getTransition().iterator(); i.hasNext(); ) {
/*  645 */       Transition trans = i.next();
/*  646 */       if (trans.getOrigin().size() > 1) return true; 
/*  647 */       if (trans.getDestination().size() > 1) return true;
/*      */     
/*      */     } 
/*  650 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void genSetStateMethod(CFunction setState, AtomType at, boolean isPetri) {
/*  661 */     setState.getContent().add(this.cBuilder.createCCode("Leaf* _l = (Leaf*)node;"));
/*  662 */     setState.getContent().add(this.cBuilder.createCCode("char* _stateStr = new char[strlen(_l->getAleaf()->getVal()) + 1];"));
/*  663 */     setState.getContent().add(this.cBuilder.createCCode("strcpy(_stateStr, _l->getAleaf()->getVal());"));
/*  664 */     setState.getContent().add(this.cBuilder.createCCode("char* _token = NULL;"));
/*  665 */     setState.getContent().add(this.cBuilder.createCCode("char* _saveptr = NULL;"));
/*      */ 
/*      */     
/*  668 */     PetriNet behav = (PetriNet)at.getBehavior();
/*  669 */     if (behav == null)
/*  670 */       return;  EList eList1 = behav.getState();
/*  671 */     CIfStm lastIfStm = null;
/*      */     
/*  673 */     if (isPetri) {
/*  674 */       setState.getContent().add(this.cBuilder.createComment("// first reset the places"));
/*      */       
/*  676 */       for (Iterator<State> iterator1 = eList1.listIterator(); iterator1.hasNext(); ) {
/*  677 */         State state = iterator1.next();
/*  678 */         CExpression getToken = this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)this.cBuilder.createFuncCall("getToken"));
/*  679 */         CExpression token = this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)this.cBuilder.createFuncCall("token"));
/*  680 */         CIfStm ifPlace = this.cBuilder.createIf(token, (CStm)getToken, null);
/*  681 */         setState.getContent().add(ifPlace);
/*      */       } 
/*      */       
/*  684 */       for (Iterator<State> iState = eList1.listIterator(); iState.hasNext(); ) {
/*  685 */         State state = iState.next();
/*  686 */         CFunctionCall getBoolVal = this.cFactory.createCFunctionCall();
/*  687 */         getBoolVal.setFunctionName("getBoolVariableState");
/*  688 */         getBoolVal.getArgument().add(this.cBuilder.createSimpleName("_start"));
/*  689 */         CExpression getVal = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("node"), (CExpression)getBoolVal);
/*  690 */         CExpression putToken = this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)this.cBuilder.createFuncCall("putToken"));
/*  691 */         CIfStm ifPlace = this.cBuilder.createIf(getVal, (CStm)putToken, null);
/*  692 */         setState.getContent().add(ifPlace);
/*      */       } 
/*      */     } else {
/*      */       
/*  696 */       setState.getContent().add(this.cBuilder.createCCode("_token =  node->string2bipState(_stateStr,  _saveptr);"));
/*      */ 
/*      */       
/*  699 */       for (Iterator<State> iState = eList1.listIterator(); iState.hasNext(); ) {
/*  700 */         State state = iState.next();
/*  701 */         CIfStm currentIf = this.cFactory.createCIfStm();
/*  702 */         if (lastIfStm == null) {
/*      */           
/*  704 */           setState.getContent().add(currentIf);
/*      */         } else {
/*      */           
/*  707 */           lastIfStm.setElseCase((CStm)currentIf);
/*      */         } 
/*  709 */         lastIfStm = currentIf;
/*      */         
/*  711 */         CFunctionCall strcmp = this.cFactory.createCFunctionCall();
/*  712 */         strcmp.setFunctionName("strcmp");
/*  713 */         strcmp.getArgument().add(this.cBuilder.createSimpleName("_token"));
/*  714 */         strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + state.getName() + "\""));
/*      */         
/*  716 */         currentIf.setCondition(this.cBuilder.createOperation(null, "!", (CExpression)strcmp));
/*      */         
/*  718 */         CAssignStm stateAssign = this.cFactory.createCAssignStm();
/*  719 */         stateAssign.setTarget((CExpression)this.cBuilder.createSimpleName("BIP_STATE"));
/*  720 */         stateAssign.setSource((CExpression)this.cBuilder.createSimpleName(state.getName()));
/*      */         
/*  722 */         currentIf.setIfCase((CStm)stateAssign);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  730 */     EList eList2 = at.getPortDefinition();
/*  731 */     if (eList2 != null) {
/*  732 */       for (Iterator<PortDefinition> iPort = eList2.listIterator(); iPort.hasNext(); ) {
/*  733 */         PortDefinition port = iPort.next();
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
/*  747 */         CFunctionCall setSync = this.cFactory.createCFunctionCall();
/*  748 */         setSync.setFunctionName(port.getName() + "->setSynced");
/*  749 */         setSync.getArgument().add(this.cBuilder.createLiteral("node->string2bool(_saveptr)"));
/*      */         
/*  751 */         setState.getContent().add(setSync);
/*      */       } 
/*      */     }
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
/*  766 */     EList eList3 = at.getVariable();
/*  767 */     if (eList3 != null) {
/*  768 */       for (Iterator<Variable> iData = eList3.listIterator(); iData.hasNext(); ) {
/*  769 */         Variable data = iData.next();
/*  770 */         DataType dt = data.getType();
/*  771 */         String dataType = "";
/*  772 */         if (dt instanceof OpaqueElement) {
/*  773 */           OpaqueElement oe = (OpaqueElement)dt;
/*  774 */           dataType = oe.getBody();
/*      */         } 
/*  776 */         if (dataType.equals("int")) {
/*  777 */           CFunctionCall cFunctionCall = this.cFactory.createCFunctionCall();
/*      */           
/*  779 */           cFunctionCall.setFunctionName("string2int");
/*  780 */           cFunctionCall.getArgument().add(this.cBuilder.createSimpleName("_saveptr"));
/*  781 */           CExpression cExpression = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("node"), (CExpression)cFunctionCall);
/*  782 */           setState.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(data.getName()), cExpression)); continue;
/*      */         } 
/*  784 */         if (dataType.equals("float")) {
/*  785 */           CFunctionCall cFunctionCall = this.cFactory.createCFunctionCall();
/*      */           
/*  787 */           cFunctionCall.setFunctionName("string2float");
/*  788 */           cFunctionCall.getArgument().add(this.cBuilder.createSimpleName("_saveptr"));
/*  789 */           CExpression cExpression = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("node"), (CExpression)cFunctionCall);
/*  790 */           setState.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(data.getName()), cExpression));
/*      */           continue;
/*      */         } 
/*  793 */         if (dataType.equals("bool")) {
/*  794 */           CFunctionCall cFunctionCall = this.cFactory.createCFunctionCall();
/*      */           
/*  796 */           cFunctionCall.setFunctionName("string2bool");
/*  797 */           cFunctionCall.getArgument().add(this.cBuilder.createSimpleName("_saveptr"));
/*  798 */           CExpression cExpression = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("node"), (CExpression)cFunctionCall);
/*  799 */           setState.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(data.getName()), cExpression));
/*      */           
/*      */           continue;
/*      */         } 
/*  803 */         CFunctionCall getValCall = this.cFactory.createCFunctionCall();
/*      */         
/*  805 */         getValCall.setFunctionName("string2objState");
/*  806 */         getValCall.getArgument().add(this.cBuilder.createSimpleName("_saveptr"));
/*  807 */         CExpression getVal = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("node"), (CExpression)getValCall);
/*  808 */         setState.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("_token"), getVal));
/*      */         
/*  810 */         CFunctionCall fromstrrep = this.cBuilder.createFuncCall("fromstrrep_" + dataType);
/*  811 */         fromstrrep.getArgument().add(this.cBuilder.createSimpleName("&" + data.getName()));
/*  812 */         fromstrrep.getArgument().add(this.cBuilder.createSimpleName("_token"));
/*  813 */         setState.getContent().add(fromstrrep);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  820 */     setState.getContent().add(this.cBuilder.createCCode("delete[] _stateStr;"));
/*      */ 
/*      */     
/*  823 */     int iTrans = 0;
/*  824 */     for (Iterator<Transition> i = behav.getTransition().iterator(); i.hasNext(); iTrans++) {
/*  825 */       Transition trans = i.next();
/*  826 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), generateGuardExpr(trans.getGuard()));
/*      */       
/*  828 */       setState.getContent().add(ass);
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
/*      */   private void genGetStateMethod(CFunction getState, AtomType at, boolean isPetri) {
/*  841 */     getState.getContent().add(this.cBuilder.createCCode("char _state[500];"));
/*  842 */     getState.getContent().add(this.cBuilder.createCCode("_state[0]= '\\0';"));
/*  843 */     getState.getContent().add(this.cBuilder.createCCode("char _numericData[100];"));
/*  844 */     PetriNet behav = (PetriNet)at.getBehavior();
/*  845 */     if (behav == null)
/*      */       return; 
/*  847 */     if (isPetri) {
/*  848 */       boolean first = true;
/*  849 */       for (Iterator<State> iState = behav.getState().listIterator(); iState.hasNext(); ) {
/*  850 */         State state = iState.next();
/*  851 */         if (first) {
/*  852 */           first = false;
/*      */         } else {
/*  854 */           getState.getContent().add(this.cBuilder.createCCode("strcat(_state, \",\");"));
/*      */         } 
/*  856 */         CFunctionCall strcatT = this.cBuilder.createFuncCall("strcat");
/*  857 */         strcatT.getArgument().add(this.cBuilder.createSimpleName("_state"));
/*  858 */         strcatT.getArgument().add(this.cBuilder.createLiteral("\"T\""));
/*  859 */         CFunctionCall strcatF = this.cBuilder.createFuncCall("strcat");
/*  860 */         strcatF.getArgument().add(this.cBuilder.createSimpleName("_state"));
/*  861 */         strcatF.getArgument().add(this.cBuilder.createLiteral("\"F\""));
/*  862 */         CIfStm ifPlace = this.cBuilder.createIf(this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)this.cBuilder.createFuncCall("token")), (CStm)strcatT, (CStm)strcatF);
/*      */         
/*  864 */         getState.getContent().add(ifPlace);
/*      */       } 
/*      */     } else {
/*      */       
/*  868 */       CIfStm lastIfStm = null;
/*  869 */       for (Iterator<State> iState = behav.getState().listIterator(); iState.hasNext(); ) {
/*  870 */         State state = iState.next();
/*  871 */         CIfStm currentIf = this.cFactory.createCIfStm();
/*  872 */         if (lastIfStm == null) {
/*      */           
/*  874 */           getState.getContent().add(currentIf);
/*      */         } else {
/*      */           
/*  877 */           lastIfStm.setElseCase((CStm)currentIf);
/*      */         } 
/*  879 */         lastIfStm = currentIf;
/*      */ 
/*      */ 
/*      */         
/*  883 */         currentIf.setCondition(this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), "==", (CExpression)this.cBuilder.createSimpleName(state.getName())));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  889 */         CFunctionCall strcpy = this.cFactory.createCFunctionCall();
/*  890 */         strcpy.setFunctionName("strcpy");
/*  891 */         strcpy.getArgument().add(this.cBuilder.createSimpleName("_state"));
/*  892 */         strcpy.getArgument().add(this.cBuilder.createLiteral("\"" + state.getName() + "\""));
/*      */         
/*  894 */         currentIf.setIfCase((CStm)strcpy);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  900 */     EList eList1 = at.getPortDefinition();
/*  901 */     if (eList1 != null) {
/*  902 */       for (Iterator<PortDefinition> iPort = eList1.listIterator(); iPort.hasNext(); ) {
/*  903 */         PortDefinition port = iPort.next();
/*      */ 
/*      */         
/*  906 */         CFunctionCall sprintf = this.cBuilder.createFuncCall("setVariableState");
/*  907 */         sprintf.getArgument().add(this.cBuilder.createSimpleName("_state"));
/*  908 */         sprintf.getArgument().add(this.cBuilder.createLiteral("\"" + port.getName() + "\""));
/*      */         
/*  910 */         sprintf.getArgument().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(port.getName()), (CExpression)this.cBuilder.createFuncCall("synced")));
/*      */         
/*  912 */         CTypeConvertion NodeConv = this.cFactory.createCTypeConvertion();
/*  913 */         NodeConv.setConvertedExpression((CExpression)this.cBuilder.createSimpleName("this"));
/*  914 */         NodeConv.setType("Node*");
/*  915 */         CExpression p = this.cBuilder.createPointed((CExpression)NodeConv, (CExpression)sprintf);
/*  916 */         getState.getContent().add(p);
/*      */       } 
/*      */     }
/*      */     
/*  920 */     EList eList2 = at.getVariable();
/*  921 */     if (eList2 != null) {
/*  922 */       for (Iterator<Variable> iData = eList2.listIterator(); iData.hasNext(); ) {
/*  923 */         Variable data = iData.next();
/*  924 */         DataType dt = data.getType();
/*  925 */         String dataType = "";
/*  926 */         if (dt instanceof OpaqueElement) {
/*  927 */           OpaqueElement oe = (OpaqueElement)dt;
/*  928 */           dataType = oe.getBody();
/*      */         } 
/*  930 */         if (dataType.equals("int") || dataType.equals("float") || dataType.equals("bool")) {
/*  931 */           CFunctionCall sprintf = this.cBuilder.createFuncCall("setVariableState");
/*  932 */           sprintf.getArgument().add(this.cBuilder.createSimpleName("_state"));
/*  933 */           sprintf.getArgument().add(this.cBuilder.createLiteral("\"" + data.getName() + "\""));
/*      */           
/*  935 */           sprintf.getArgument().add(this.cBuilder.createSimpleName(data.getName()));
/*  936 */           CTypeConvertion NodeConv = this.cFactory.createCTypeConvertion();
/*  937 */           NodeConv.setConvertedExpression((CExpression)this.cBuilder.createSimpleName("this"));
/*  938 */           NodeConv.setType("Node*");
/*  939 */           CExpression p = this.cBuilder.createPointed((CExpression)NodeConv, (CExpression)sprintf);
/*  940 */           getState.getContent().add(p);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  960 */         CFunctionCall strrep = this.cBuilder.createFuncCall("strrep_" + dataType);
/*  961 */         strrep.getArgument().add(this.cBuilder.createSimpleName(data.getName()));
/*  962 */         strrep.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*      */         
/*  964 */         getState.getContent().add(strrep);
/*  965 */         getState.getContent().add(this.cBuilder.createCCode("strcat(_state, \",\");"));
/*      */         
/*  967 */         getState.getContent().add(this.cBuilder.createCCode("strcat(_state, _numericData);"));
/*      */       } 
/*      */     }
/*  970 */     getState.getContent().add(this.cBuilder.createCCode("strcat(_state, \",\");"));
/*      */     
/*  972 */     getState.getContent().add(this.cBuilder.createCCode("return (new Leaf(new Atomleaf(_state)));"));
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
/*      */   private CFunction generateGetState(AtomType at, boolean isPetri) {
/*  984 */     CFunction get = this.cFactory.createCFunction();
/*  985 */     get.setVisibility("public");
/*  986 */     get.setName("getState");
/*  987 */     get.setType("Node *");
/*      */     
/*  989 */     if (this.forVerification) {
/*  990 */       genGetStateMethod(get, at, isPetri);
/*      */     } else {
/*  992 */       get.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL")));
/*  993 */       get.setBodyInDecl(true);
/*      */     } 
/*      */ 
/*      */     
/*  997 */     return get;
/*      */   }
/*      */   
/*      */   private CFunction generateSetState(AtomType at, boolean isPetri) {
/* 1001 */     CFunction set = this.cFactory.createCFunction();
/* 1002 */     set.setVisibility("public");
/* 1003 */     set.setName("setState");
/* 1004 */     set.setType("void");
/* 1005 */     set.getArgument().add(this.cBuilder.createArgument("node", "Node *"));
/*      */     
/* 1007 */     if (this.forVerification) {
/*      */       
/* 1009 */       genSetStateMethod(set, at, isPetri);
/*      */     } else {
/* 1011 */       set.setBodyInDecl(true);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1016 */     return set;
/*      */   }
/*      */   
/*      */   private CFunctionCall generateSync(AtomType at, PetriNet behav, CBlock b, State s) {
/*      */     CFunctionCall sync;
/* 1021 */     if (this.generatingMultiThreadedCode) {
/* 1022 */       sync = this.cBuilder.createFuncCall("sync");
/*      */     } else {
/*      */       
/* 1025 */       sync = this.cBuilder.createFuncCall("rt_sync");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1034 */     EList eList = s.getOutgoing();
/*      */ 
/*      */     
/* 1037 */     sync.getArgument().add(this.cBuilder.createLiteral(eList.size()));
/*      */     
/* 1039 */     List prios = statePriority(at, (List)eList);
/*      */ 
/*      */     
/* 1042 */     for (Iterator<AbstractTransition> iterator1 = eList.iterator(); iterator1.hasNext(); ) {
/* 1043 */       AbstractTransition trans = iterator1.next();
/* 1044 */       int iTrans = behav.getTransition().indexOf(trans);
/* 1045 */       CAssignStm assGuard = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), generateGuardExpr(trans.getGuard()));
/*      */       
/* 1047 */       b.getContent().add(assGuard);
/*      */     } 
/*      */ 
/*      */     
/* 1051 */     if (!this.generatingMultiThreadedCode) {
/* 1052 */       for (Iterator<AbstractTransition> iterator = eList.iterator(); iterator.hasNext(); ) {
/* 1053 */         AbstractTransition trans = iterator.next();
/* 1054 */         int iTrans = behav.getTransition().indexOf(trans);
/*      */         
/* 1056 */         CFunctionCall computeCons = this.cBuilder.createFuncCall("computePortConstraint");
/* 1057 */         computeCons.getArgument().add(this.cBuilder.createSimpleName("BIP_Constraint" + iTrans));
/*      */         
/* 1059 */         b.getContent().add(this.cBuilder.createIf((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), (CStm)computeCons, null));
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1065 */     if (prios != null) {
/*      */       
/* 1067 */       for (Iterator<AbstractTransition> iterator3 = eList.iterator(); iterator3.hasNext(); ) {
/* 1068 */         AbstractTransition trans = iterator3.next();
/* 1069 */         int iTrans = behav.getTransition().indexOf(trans);
/* 1070 */         PortExpression pe = trans.getTrigger();
/* 1071 */         if (pe instanceof PortDefinitionReference) {
/* 1072 */           PortDefinitionReference pdr = (PortDefinitionReference)pe;
/* 1073 */           PortDefinition pd = pdr.getTarget();
/*      */           
/* 1075 */           String guardName = pd.getName() + "_guard";
/* 1076 */           String guardNameNew = pd.getName() + "_guard" + "_new";
/*      */           
/* 1078 */           CData guard = this.cBuilder.createData(guardName, "bool", null, (CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans));
/*      */           
/* 1080 */           b.getContent().add(guard);
/*      */ 
/*      */           
/* 1083 */           if (this.generatingMultiThreadedCode) {
/* 1084 */             CData guardNew = this.cBuilder.createData(guardNameNew, "bool", null, (CExpression)this.cBuilder.createSimpleName(guardName));
/*      */             
/* 1086 */             b.getContent().add(guardNew);
/*      */           } 
/*      */ 
/*      */           
/* 1090 */           if (!this.generatingMultiThreadedCode) {
/* 1091 */             String constraintName = pd.getName() + "_constraint";
/*      */             
/* 1093 */             CData constraint = this.cBuilder.createData(constraintName, "Constraint*", null, (CExpression)this.cBuilder.createSimpleName("BIP_Constraint" + iTrans + "[0]"));
/*      */             
/* 1095 */             b.getContent().add(constraint);
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/* 1100 */         System.err.println("Port expression with priority Not yet implemented");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1105 */       for (Iterator<PriorityRule> iPrio = prios.iterator(); iPrio.hasNext(); ) {
/* 1106 */         CSimpleName cSimpleName; PriorityRule p = iPrio.next();
/*      */         
/* 1108 */         Interaction lowerInter = (Interaction)p.getLower();
/* 1109 */         Interaction greaterInter = (Interaction)p.getGreater();
/* 1110 */         PortDefinition lpd = ((PortDefinitionReference)lowerInter.getPort().get(0)).getTarget();
/* 1111 */         PortDefinition hpd = ((PortDefinitionReference)greaterInter.getPort().get(0)).getTarget();
/*      */         
/* 1113 */         String lp_guard = lpd.getName() + "_guard";
/* 1114 */         String lp_guard_new = lpd.getName() + "_guard" + "_new";
/* 1115 */         String hp_guard = hpd.getName() + "_guard";
/*      */         
/* 1117 */         String lpConstraintName = lpd.getName() + "_constraint";
/* 1118 */         String hpConstraintName = hpd.getName() + "_constraint";
/*      */         
/* 1120 */         if (!this.generatingMultiThreadedCode) {
/* 1121 */           CExpression guard; CFunctionCall applyPrio = this.cBuilder.createFuncCall("applyPriority");
/* 1122 */           applyPrio.getArgument().add(this.cBuilder.createSimpleName(lpd.getName()));
/* 1123 */           applyPrio.getArgument().add(this.cBuilder.createSimpleName(lpConstraintName));
/* 1124 */           applyPrio.getArgument().add(this.cBuilder.createSimpleName(hpd.getName()));
/* 1125 */           applyPrio.getArgument().add(this.cBuilder.createSimpleName(hpConstraintName));
/*      */           
/* 1127 */           CExpression cExpression1 = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(lp_guard), "&&", (CExpression)this.cBuilder.createSimpleName(hp_guard));
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1132 */           if (p.getGuard() != null) {
/* 1133 */             guard = this.cBuilder.createOperation(this.expGen.generateExpr(p.getGuard()), "&&", cExpression1);
/*      */           } else {
/*      */             
/* 1136 */             guard = cExpression1;
/*      */           } 
/* 1138 */           b.getContent().add(this.cBuilder.createIf(guard, (CStm)applyPrio, null));
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 1144 */         if (p.getGuard() != null) {
/* 1145 */           CExpression highGuard = this.cBuilder.createOperation(this.expGen.generateExpr(p.getGuard()), "&&", (CExpression)this.cBuilder.createSimpleName(hp_guard));
/*      */         } else {
/*      */           
/* 1148 */           cSimpleName = this.cBuilder.createSimpleName(hp_guard);
/*      */         } 
/*      */         
/* 1151 */         CExpression portsGuard = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(lp_guard_new), "&&", this.cBuilder.createOperation(null, "!", (CExpression)cSimpleName));
/*      */ 
/*      */ 
/*      */         
/* 1155 */         b.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(lp_guard_new), portsGuard));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1162 */       for (Iterator<AbstractTransition> iterator2 = eList.iterator(); iterator2.hasNext(); ) {
/* 1163 */         AbstractTransition trans = iterator2.next();
/* 1164 */         int iTrans = behav.getTransition().indexOf(trans);
/* 1165 */         PortExpression pe = trans.getTrigger();
/* 1166 */         if (pe instanceof PortDefinitionReference) {
/* 1167 */           PortDefinitionReference pdr = (PortDefinitionReference)pe;
/* 1168 */           PortDefinition pd = pdr.getTarget();
/*      */           
/* 1170 */           String guardNameNew = pd.getName() + "_guard" + "_new";
/*      */ 
/*      */           
/* 1173 */           if (this.generatingMultiThreadedCode) {
/* 1174 */             CAssignStm guardNew = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), (CExpression)this.cBuilder.createSimpleName(guardNameNew));
/*      */ 
/*      */             
/* 1177 */             b.getContent().add(guardNew);
/*      */           } 
/*      */           continue;
/*      */         } 
/* 1181 */         System.err.println("Port expression with priority Not yet implemented");
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1187 */     for (Iterator<AbstractTransition> iT = eList.iterator(); iT.hasNext(); ) {
/* 1188 */       AbstractTransition trans = iT.next();
/* 1189 */       int iTrans = behav.getTransition().indexOf(trans);
/* 1190 */       sync.getArgument().add(generatePortExpr(trans.getTrigger()));
/* 1191 */       sync.getArgument().add(this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans));
/*      */       
/* 1193 */       if (!this.generatingMultiThreadedCode) {
/* 1194 */         sync.getArgument().add(this.cBuilder.createSimpleName("BIP_Constraint" + iTrans + "[0]"));
/*      */       }
/*      */     } 
/*      */     
/* 1198 */     return sync;
/*      */   }
/*      */ 
/*      */   
/*      */   private List statePriority(AtomType at, List dests) {
/* 1203 */     List<PriorityRule> l = null;
/* 1204 */     for (Iterator<PriorityRule> i = at.getPriorityRule().iterator(); i.hasNext(); ) {
/* 1205 */       PriorityRule p = i.next();
/*      */       
/* 1207 */       Interaction lowerInter = (Interaction)p.getLower();
/* 1208 */       Interaction greaterInter = (Interaction)p.getGreater();
/* 1209 */       PortDefinition lpd = ((PortDefinitionReference)lowerInter.getPort().get(0)).getTarget();
/* 1210 */       PortDefinition hpd = ((PortDefinitionReference)greaterInter.getPort().get(0)).getTarget();
/* 1211 */       boolean lfound = false;
/* 1212 */       boolean hfound = false;
/* 1213 */       for (Iterator<Transition> j = dests.iterator(); j.hasNext() && (!lfound || !hfound); ) {
/* 1214 */         Transition t = j.next();
/* 1215 */         if (lfound || isInPortExp(lpd, t.getTrigger())) {
/* 1216 */           lfound = true;
/*      */         }
/* 1218 */         if (hfound || isInPortExp(hpd, t.getTrigger())) {
/* 1219 */           hfound = true;
/*      */         }
/*      */       } 
/* 1222 */       if (lfound && hfound) {
/* 1223 */         if (l == null) l = new ArrayList(); 
/* 1224 */         l.add(p);
/*      */       } 
/*      */     } 
/* 1227 */     return l;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isInPortExp(PortDefinition pd, PortExpression trigger) {
/* 1233 */     if (trigger instanceof PortDefinitionReference) {
/* 1234 */       PortDefinitionReference ppr = (PortDefinitionReference)trigger;
/* 1235 */       if (ppr.getTarget() == pd) return true; 
/*      */     } 
/* 1237 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generateInitialize(AtomType at, PetriNet behav, CClass atomTypeClass, boolean isPetri) {
/* 1246 */     CFunction f = this.cFactory.createCFunction();
/* 1247 */     atomTypeClass.getContent().add(f);
/* 1248 */     f.setType("void");
/*      */     
/* 1250 */     f.getArgument().add(this.cBuilder.createArgument("clock", "LogicalClock*"));
/* 1251 */     f.setName("initialize");
/* 1252 */     f.setVisibility("public");
/*      */ 
/*      */     
/* 1255 */     for (Iterator<Variable> i = at.getVariable().iterator(); i.hasNext(); ) {
/* 1256 */       Variable v = i.next();
/* 1257 */       if (v instanceof ujf.verimag.bip.Extra.Time.TimedVariable) {
/* 1258 */         String clock = v.getName();
/*      */         
/* 1260 */         CFunctionCall new_clk = this.cBuilder.createFuncCall("new Clock");
/* 1261 */         String local = clock;
/* 1262 */         new_clk.getArgument().add(this.cBuilder.createSimpleName("\"" + local + "\""));
/* 1263 */         new_clk.getArgument().add(this.cBuilder.createLiteral("clock"));
/*      */         
/* 1265 */         CAssignStm new_clock = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(clock), (CExpression)new_clk);
/* 1266 */         f.getContent().add(new_clock);
/*      */         
/* 1268 */         f.getContent().add(this.cBuilder.createSimpleName("mClocks.add(" + clock + ")"));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1273 */     int num = 0;
/* 1274 */     for (Iterator<Transition> iT = behav.getTransition().iterator(); iT.hasNext(); ) {
/* 1275 */       Transition transi = iT.next();
/*      */ 
/*      */       
/* 1278 */       TimeSpecification ts = transi.getTimeSpecification();
/*      */       
/* 1280 */       int k = 0;
/* 1281 */       if (ts != null) {
/* 1282 */         UrgencyKind u = ts.getUrgency();
/* 1283 */         EList c = ts.getTimedConstraint();
/*      */ 
/*      */         
/* 1286 */         if (c.size() > 0) {
/*      */           
/* 1288 */           if (c.size() > 1) {
/* 1289 */             CFunctionCall new_cons = this.cBuilder.createFuncCall("new Constraint");
/* 1290 */             new_cons.getArgument().add(this.cBuilder.createLiteral("clock"));
/*      */             
/* 1292 */             CAssignStm constraint = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_Constraint" + num + "[" + k + "]"), (CExpression)new_cons);
/*      */ 
/*      */             
/* 1295 */             f.getContent().add(constraint);
/*      */             
/* 1297 */             k++;
/*      */           } 
/*      */ 
/*      */           
/* 1301 */           for (Iterator<TimedConstraint> iTconst = c.iterator(); iTconst.hasNext(); ) {
/* 1302 */             TimedConstraint cons = iTconst.next();
/* 1303 */             Expression Low = cons.getLowBound();
/* 1304 */             Expression High = cons.getHighBound();
/*      */ 
/*      */             
/* 1307 */             CFunctionCall new_cons = this.cBuilder.createFuncCall("new Constraint");
/*      */ 
/*      */             
/* 1310 */             CSimpleName clk = this.cBuilder.createSimpleName(cons.getClock().getTargetVariable().getName());
/* 1311 */             new_cons.getArgument().add(clk);
/*      */ 
/*      */             
/* 1314 */             if (Low != null) { new_cons.getArgument().add(this.cBuilder.createLiteral("BIP::Time(" + ((IntegerLiteral)Low).getIValue() + ")")); }
/* 1315 */             else { new_cons.getArgument().add(this.cBuilder.createLiteral("BIP::Time(0)")); }
/*      */             
/* 1317 */             if (High != null) { new_cons.getArgument().add(this.cBuilder.createLiteral("BIP::Time(" + ((IntegerLiteral)High).getIValue() + ")")); }
/* 1318 */             else { new_cons.getArgument().add(this.cBuilder.createLiteral("BIP::Time::MAX")); }
/*      */ 
/*      */             
/* 1321 */             new_cons.getArgument().add(this.cBuilder.createSimpleName(u.toString()));
/*      */             
/* 1323 */             CAssignStm constraint = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_Constraint" + num + "[" + k + "]"), (CExpression)new_cons);
/*      */ 
/*      */             
/* 1326 */             f.getContent().add(constraint);
/*      */             
/* 1328 */             k++;
/*      */           }
/*      */         
/*      */         }
/* 1332 */         else if (u.getValue() != 2) {
/* 1333 */           CFunctionCall new_cons = this.cBuilder.createFuncCall("new Constraint");
/* 1334 */           new_cons.getArgument().add(this.cBuilder.createLiteral("clock"));
/* 1335 */           new_cons.getArgument().add(this.cBuilder.createLiteral("BIP::Time(0)"));
/* 1336 */           new_cons.getArgument().add(this.cBuilder.createLiteral("BIP::Time::MAX"));
/* 1337 */           new_cons.getArgument().add(this.cBuilder.createSimpleName(u.toString()));
/*      */           
/* 1339 */           CAssignStm constraint = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_Constraint" + num + "[" + k + "]"), (CExpression)new_cons);
/*      */ 
/*      */           
/* 1342 */           f.getContent().add(constraint);
/*      */           
/* 1344 */           k++;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1349 */       CAssignStm constraintEnd = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_Constraint" + num + "[" + k + "]"), (CExpression)this.cBuilder.createLiteral("NULL"));
/*      */ 
/*      */       
/* 1352 */       f.getContent().add(constraintEnd);
/*      */ 
/*      */       
/* 1355 */       for (int m = 0; m < k; m++) {
/* 1356 */         f.getContent().add(this.cBuilder.createSimpleName("mConstraints.add(BIP_Constraint" + num + "[" + m + "]" + ")"));
/*      */       }
/*      */       
/* 1359 */       num++;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1365 */     for (int j = 0; j < behav.getTransition().size(); j++) {
/* 1366 */       CAssignStm stm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + j), (CExpression)this.cBuilder.createLiteral("false"));
/*      */       
/* 1368 */       f.getContent().add(stm);
/*      */     } 
/*      */ 
/*      */     
/* 1372 */     if (isPetri) {
/*      */       
/* 1374 */       for (Iterator<State> iterator = behav.getInitialState().iterator(); iterator.hasNext(); ) {
/* 1375 */         State state = iterator.next();
/*      */         
/* 1377 */         CFunctionCall getP = this.cBuilder.createFuncCall("putToken");
/* 1378 */         f.getContent().add(this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)getP));
/*      */       } 
/*      */       
/* 1381 */       CFunctionCall exec = this.cBuilder.createFuncCall("execute");
/* 1382 */       exec.getArgument().add(this.cBuilder.createFuncCall("prepareNextStep"));
/* 1383 */       f.getContent().add(exec);
/*      */ 
/*      */       
/* 1386 */       f = this.cFactory.createCFunction();
/* 1387 */       atomTypeClass.getContent().add(f);
/* 1388 */       f.setType("Port*");
/* 1389 */       f.setName("prepareNextStep");
/* 1390 */       f.setVisibility("private");
/*      */ 
/*      */       
/* 1393 */       for (Iterator<PortDefinition> iterator3 = at.getPortDefinition().iterator(); iterator3.hasNext(); ) {
/* 1394 */         PortDefinition pd = iterator3.next();
/*      */         
/* 1396 */         CFunctionCall setSync = this.cBuilder.createFuncCall("setSynced");
/* 1397 */         setSync.getArgument().add(this.cBuilder.createLiteral("false"));
/* 1398 */         f.getContent().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(pd.getName()), (CExpression)setSync));
/*      */       } 
/*      */ 
/*      */       
/* 1402 */       for (Iterator<PortDefinition> iterator2 = at.getPortDefinition().iterator(); iterator2.hasNext(); ) {
/* 1403 */         PortDefinition pd = iterator2.next();
/*      */         
/* 1405 */         CFunctionCall setDominated = this.cBuilder.createFuncCall("setDominated");
/* 1406 */         setDominated.getArgument().add(this.cBuilder.createLiteral("false"));
/* 1407 */         f.getContent().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(pd.getName()), (CExpression)setDominated));
/*      */       } 
/*      */ 
/*      */       
/* 1411 */       for (Iterator<PortDefinition> iterator1 = at.getPortDefinition().iterator(); iterator1.hasNext(); ) {
/* 1412 */         PortDefinition pd = iterator1.next();
/*      */         
/* 1414 */         CData guard = this.cBuilder.createData(pd.getName() + "_guard", "bool", null, (CExpression)this.cBuilder.createLiteral("false"));
/*      */         
/* 1416 */         f.getContent().add(guard);
/*      */       } 
/*      */ 
/*      */       
/* 1420 */       initPNgeneration();
/* 1421 */       State s = null;
/* 1422 */       while ((s = chooseNextState(behav)) != null) {
/* 1423 */         CExpression token = tokenState(s);
/* 1424 */         CBlockStm b = this.cFactory.createCBlockStm();
/* 1425 */         CIfStm ifState = this.cBuilder.createIf(token, (CStm)b, null);
/* 1426 */         f.getContent().add(ifState);
/*      */ 
/*      */         
/* 1429 */         for (Iterator<Transition> iterator6 = s.getOutgoing().iterator(); iterator6.hasNext(); ) {
/* 1430 */           Transition trans = iterator6.next();
/* 1431 */           int iTrans = behav.getTransition().indexOf(trans);
/* 1432 */           if (!this.computedTrans.contains(trans)) {
/* 1433 */             CIfStm cIfStm; CStm stm = generatePortExpAssign(trans.getTrigger(), iTrans, trans.getGuard());
/* 1434 */             if (trans.getOrigin().size() > 1) {
/* 1435 */               CExpression cond = null;
/* 1436 */               for (Iterator<State> iterator7 = trans.getOrigin().iterator(); iterator7.hasNext(); ) {
/* 1437 */                 State s2 = iterator7.next();
/* 1438 */                 if (s2 != s) {
/* 1439 */                   if (cond == null) {
/* 1440 */                     cond = tokenState(s2); continue;
/*      */                   } 
/* 1442 */                   cond = this.cBuilder.createOperation(cond, "&&", tokenState(s2));
/*      */                 } 
/*      */               } 
/*      */               
/* 1446 */               cIfStm = this.cBuilder.createIf(cond, stm, null);
/*      */             } 
/* 1448 */             b.getContent().add(cIfStm);
/* 1449 */             this.computedTrans.add(trans);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1455 */       for (Iterator<PortDefinition> iterator4 = at.getPortDefinition().iterator(); iterator4.hasNext(); ) {
/* 1456 */         PortDefinition pd = iterator4.next();
/*      */         
/* 1458 */         CData guardprio = this.cBuilder.createData(pd.getName() + "_guard" + "_prio", "bool", null, (CExpression)this.cBuilder.createSimpleName(pd.getName() + "_guard"));
/*      */         
/* 1460 */         f.getContent().add(guardprio);
/*      */       } 
/*      */       
/* 1463 */       EList eList = at.getPriorityRule();
/* 1464 */       if (eList != null) {
/* 1465 */         for (Iterator<PriorityRule> iPrio = eList.iterator(); iPrio.hasNext(); ) {
/* 1466 */           PriorityRule p = iPrio.next();
/*      */           
/* 1468 */           Interaction lowerInter = (Interaction)p.getLower();
/* 1469 */           Interaction greaterInter = (Interaction)p.getGreater();
/* 1470 */           PortDefinition lpd = ((PortDefinitionReference)lowerInter.getPort().get(0)).getTarget();
/* 1471 */           PortDefinition hpd = ((PortDefinitionReference)greaterInter.getPort().get(0)).getTarget();
/*      */           
/* 1473 */           String lp_guard = lpd.getName() + "_guard";
/* 1474 */           String hp_guard = hpd.getName() + "_guard";
/* 1475 */           String lp_guard_prio = lp_guard + "_prio";
/*      */           
/* 1477 */           CExpression hPrioritary = null;
/* 1478 */           if (p.getGuard() == null) {
/* 1479 */             CSimpleName cSimpleName = this.cBuilder.createSimpleName(hp_guard);
/*      */           } else {
/* 1481 */             hPrioritary = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(hp_guard), "&&", this.expGen.generateExpr(p.getGuard()));
/*      */           } 
/*      */           
/* 1484 */           CAssignStm assPrio = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(lp_guard_prio), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(lp_guard_prio), "&&", this.cBuilder.createOperation(null, "!", hPrioritary)));
/*      */ 
/*      */           
/* 1487 */           f.getContent().add(assPrio);
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1493 */       CFunctionCall sync = this.cBuilder.createFuncCall("sync");
/* 1494 */       sync.getArgument().add(this.cBuilder.createLiteral(at.getPortDefinition().size()));
/* 1495 */       for (Iterator<PortDefinition> iterator5 = at.getPortDefinition().iterator(); iterator5.hasNext(); ) {
/* 1496 */         PortDefinition pd = iterator5.next();
/*      */         
/* 1498 */         sync.getArgument().add(this.cBuilder.createSimpleName(pd.getName()));
/* 1499 */         sync.getArgument().add(this.cBuilder.createSimpleName(pd.getName() + "_guard" + "_prio"));
/*      */       } 
/*      */ 
/*      */       
/* 1503 */       f.getContent().add(this.cBuilder.createReturn((CExpression)sync));
/*      */     } else {
/*      */       
/* 1506 */       CData choice = this.cBuilder.createData("choice", "Port*", null, (CExpression)this.cBuilder.createLiteral("NULL"));
/*      */       
/* 1508 */       f.getContent().add(choice);
/* 1509 */       CSwitchStm swtStm = this.cBuilder.createSwitch("BIP_STATE");
/* 1510 */       f.getContent().add(swtStm);
/*      */       
/* 1512 */       for (Iterator<State> iterator = behav.getState().iterator(); iterator.hasNext(); ) {
/* 1513 */         State s = iterator.next();
/* 1514 */         CCaseItem ci = this.cFactory.createCCaseItem();
/* 1515 */         swtStm.getCaseAction().add(ci);
/* 1516 */         ci.setCaseValue(s.getName());
/*      */ 
/*      */         
/* 1519 */         CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("choice"), (CExpression)generateSync(at, behav, (CBlock)ci, s));
/*      */ 
/*      */         
/* 1522 */         ci.getContent().add(ass);
/*      */ 
/*      */ 
/*      */         
/* 1526 */         CFunctionCall execute = this.cBuilder.createFuncCall("execute");
/* 1527 */         execute.getArgument().add(this.cBuilder.createSimpleName("choice"));
/* 1528 */         CIfStm ifStm = this.cBuilder.createIf((CExpression)this.cBuilder.createSimpleName("choice"), (CStm)execute, null);
/*      */ 
/*      */ 
/*      */         
/* 1532 */         ci.getContent().add(ifStm);
/*      */         
/* 1534 */         CJump cJump = this.cFactory.createCJump();
/* 1535 */         cJump.setType(JumpType.CBREAK);
/* 1536 */         ci.getContent().add(cJump);
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
/*      */   private CExpression generateGuardExpr(Expression expr) {
/* 1549 */     if (expr == null) {
/* 1550 */       return (CExpression)this.cBuilder.createLiteral("true");
/*      */     }
/* 1552 */     return this.expGen.generateExpr(expr);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CExpression generatePortExpr(PortExpression trigger) {
/* 1560 */     if (trigger instanceof PortDefinitionReference) {
/* 1561 */       PortDefinitionReference pdr = (PortDefinitionReference)trigger;
/*      */       
/* 1563 */       return (CExpression)this.cBuilder.createSimpleName(pdr.getTarget().getName());
/*      */     } 
/*      */ 
/*      */     
/* 1567 */     System.out.println("port expression not implemented in transition");
/*      */     
/* 1569 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private CStm generatePortExpAssign(PortExpression trigger, int iTrans, Expression guard) {
/* 1574 */     CBlockStm b = this.cFactory.createCBlockStm();
/* 1575 */     if (trigger instanceof PortDefinitionReference) {
/* 1576 */       PortDefinitionReference pdr = (PortDefinitionReference)trigger;
/* 1577 */       PortDefinition pd = pdr.getTarget();
/* 1578 */       CAssignStm assGuard = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), generateGuardExpr(guard));
/*      */       
/* 1580 */       b.getContent().add(assGuard);
/* 1581 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(pd.getName() + "_guard"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(pd.getName() + "_guard"), "||", (CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans)));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1586 */       b.getContent().add(ass);
/* 1587 */       return (CStm)b;
/*      */     } 
/*      */ 
/*      */     
/* 1591 */     System.out.println("port expression not implemented in transition");
/*      */     
/* 1593 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private CExpression generateBoolPortExpr(PortExpression trigger, int iTrans, String localVar) {
/* 1599 */     if (trigger instanceof PortDefinitionReference) {
/* 1600 */       PortDefinitionReference pdr = (PortDefinitionReference)trigger;
/* 1601 */       CExpression exp = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(pdr.getTarget().getName()), "==", (CExpression)this.cBuilder.createSimpleName(localVar));
/*      */       
/* 1603 */       exp = this.cBuilder.createOperation(exp, "&&", (CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans));
/* 1604 */       return exp;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1609 */     return null;
/*      */   }
/*      */   
/*      */   private CStm PNTransition(Transition trans, int iTrans, String localCh, boolean multi) {
/* 1613 */     CBlockStm transBlock = this.cFactory.createCBlockStm();
/* 1614 */     CIfStm ifStm = this.cBuilder.createIf(generateBoolPortExpr(trans.getTrigger(), iTrans, localCh), (CStm)transBlock, null);
/* 1615 */     Action a = trans.getAction();
/* 1616 */     if (a != null) {
/* 1617 */       CStm ca = this.stmGen.generateStm(a);
/* 1618 */       if (ca != null) transBlock.getContent().add(ca); 
/*      */     } 
/* 1620 */     for (Iterator<State> iterator1 = trans.getOrigin().iterator(); iterator1.hasNext(); ) {
/* 1621 */       State s = iterator1.next();
/* 1622 */       CFunctionCall getP = this.cBuilder.createFuncCall("getToken");
/* 1623 */       transBlock.getContent().add(this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(s.getName()), (CExpression)getP));
/*      */     } 
/* 1625 */     for (Iterator<State> i = trans.getDestination().iterator(); i.hasNext(); ) {
/* 1626 */       State s = i.next();
/* 1627 */       CFunctionCall getP = this.cBuilder.createFuncCall("putToken");
/* 1628 */       transBlock.getContent().add(this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(s.getName()), (CExpression)getP));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1636 */     if (!multi) {
/* 1637 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(localCh), (CExpression)this.cBuilder.createFuncCall("prepareNextStep"));
/*      */       
/* 1639 */       transBlock.getContent().add(ass);
/*      */     } 
/* 1641 */     CJump jmp = this.cFactory.createCJump();
/* 1642 */     jmp.setType(JumpType.CCONTINUE);
/* 1643 */     transBlock.getContent().add(jmp);
/* 1644 */     return (CStm)ifStm;
/*      */   }
/*      */   
/*      */   public CxxAtomTypeGenerator(CConstruct builder, CxxExpressionGenerator expGen, CxxStatementGenerator stmGen) {
/* 1648 */     this.maxNuberOfOutgoingTransition = 0;
/* 1649 */     this.computedStates = null;
/* 1650 */     this.computedTrans = null; this.cBuilder = builder;
/*      */     this.expGen = expGen;
/* 1652 */     this.stmGen = stmGen; } private void initPNgeneration() { this.maxNuberOfOutgoingTransition = 0;
/* 1653 */     this.computedStates = new HashSet();
/* 1654 */     this.computedTrans = new HashSet(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private State chooseNextState(PetriNet behav) {
/* 1660 */     int max = 0;
/* 1661 */     State choice = null;
/* 1662 */     State secondChoice = null;
/* 1663 */     for (Iterator<State> i = behav.getState().iterator(); i.hasNext(); ) {
/* 1664 */       State s = i.next();
/* 1665 */       if (!this.computedStates.contains(s)) {
/* 1666 */         int nbOut = s.getOutgoing().size();
/* 1667 */         if (this.maxNuberOfOutgoingTransition <= nbOut) {
/* 1668 */           choice = s;
/* 1669 */           this.maxNuberOfOutgoingTransition = nbOut; continue;
/* 1670 */         }  if (max <= nbOut) {
/* 1671 */           secondChoice = s;
/* 1672 */           max = nbOut;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1676 */     if (choice == null) {
/*      */ 
/*      */       
/* 1679 */       choice = secondChoice;
/* 1680 */       this.maxNuberOfOutgoingTransition = max;
/*      */     } 
/* 1682 */     if (choice != null) {
/* 1683 */       this.computedStates.add(choice);
/*      */     }
/* 1685 */     return choice;
/*      */   }
/*      */ 
/*      */   
/*      */   private CExpression tokenState(State s) {
/* 1690 */     return this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(s.getName()), (CExpression)this.cBuilder.createFuncCall("token"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generatePNExecutionStep(AtomType at, PetriNet behav, CWhileStm whileStm, String localCh) {
/* 1700 */     initPNgeneration();
/* 1701 */     State s = null;
/* 1702 */     while ((s = chooseNextState(behav)) != null) {
/* 1703 */       CExpression token = tokenState(s);
/* 1704 */       CBlockStm b = this.cFactory.createCBlockStm();
/* 1705 */       CIfStm ifState = this.cBuilder.createIf(token, (CStm)b, null);
/* 1706 */       whileStm.getContent().add(ifState);
/*      */ 
/*      */       
/* 1709 */       for (Iterator<Transition> i = s.getOutgoing().iterator(); i.hasNext(); ) {
/* 1710 */         Transition trans = i.next();
/* 1711 */         int iTrans = behav.getTransition().indexOf(trans);
/* 1712 */         for (Iterator<Transition> j = s.getOutgoing().iterator(); j.hasNext(); ) {
/* 1713 */           Transition trans2 = j.next();
/* 1714 */           if (trans != trans2 && 
/* 1715 */             sameTrigger(trans.getTrigger(), trans2.getTrigger()));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1722 */         if (!this.computedTrans.contains(trans)) {
/* 1723 */           CIfStm cIfStm; CStm stm = PNTransition(trans, iTrans, localCh, false);
/* 1724 */           if (trans.getOrigin().size() > 1) {
/* 1725 */             CExpression cond = null;
/* 1726 */             for (Iterator<State> iterator = trans.getOrigin().iterator(); iterator.hasNext(); ) {
/* 1727 */               State s2 = iterator.next();
/* 1728 */               if (s2 != s) {
/* 1729 */                 if (cond == null) {
/* 1730 */                   cond = tokenState(s2); continue;
/*      */                 } 
/* 1732 */                 cond = this.cBuilder.createOperation(cond, "&&", tokenState(s2));
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/* 1737 */             cond = this.cBuilder.createOperation(cond, "&&", (CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans));
/*      */             
/* 1739 */             cIfStm = this.cBuilder.createIf(cond, stm, null);
/*      */           } 
/* 1741 */           b.getContent().add(cIfStm);
/* 1742 */           this.computedTrans.add(trans);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean sameTrigger(PortExpression trigger, PortExpression trigger2) {
/* 1752 */     if (trigger instanceof PortDefinitionReference && trigger2 instanceof PortDefinitionReference) {
/*      */       
/* 1754 */       PortDefinitionReference pdr1 = (PortDefinitionReference)trigger;
/* 1755 */       PortDefinition pd1 = pdr1.getTarget();
/* 1756 */       PortDefinitionReference pdr2 = (PortDefinitionReference)trigger2;
/* 1757 */       PortDefinition pd2 = pdr2.getTarget();
/* 1758 */       return (pd1 == pd2);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1763 */     return false;
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
/*      */   private void generateExecutionStep(AtomType at, PetriNet behav, CWhileStm whileStm, String localCh) {
/* 1781 */     for (Iterator<PortDefinition> iterator1 = at.getPortDefinition().iterator(); iterator1.hasNext(); ) {
/* 1782 */       PortDefinition pd = iterator1.next();
/*      */       
/* 1784 */       CFunctionCall setSync = this.cBuilder.createFuncCall("setSynced");
/* 1785 */       setSync.getArgument().add(this.cBuilder.createLiteral("false"));
/* 1786 */       whileStm.getContent().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(pd.getName()), (CExpression)setSync));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1791 */     for (Iterator<PortDefinition> i = at.getPortDefinition().iterator(); i.hasNext(); ) {
/* 1792 */       PortDefinition pd = i.next();
/*      */       
/* 1794 */       CFunctionCall setDominated = this.cBuilder.createFuncCall("setDominated");
/* 1795 */       setDominated.getArgument().add(this.cBuilder.createLiteral("false"));
/* 1796 */       whileStm.getContent().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(pd.getName()), (CExpression)setDominated));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1801 */     CSwitchStm swtStm = this.cBuilder.createSwitch("BIP_STATE");
/* 1802 */     whileStm.getContent().add(swtStm);
/*      */ 
/*      */     
/* 1805 */     for (Iterator<State> iterator = behav.getState().iterator(); iterator.hasNext(); ) {
/* 1806 */       State s = iterator.next();
/* 1807 */       CCaseItem ci = this.cFactory.createCCaseItem();
/* 1808 */       swtStm.getCaseAction().add(ci);
/* 1809 */       ci.setCaseValue(s.getName());
/*      */ 
/*      */       
/* 1812 */       CIfStm LastIf = null;
/* 1813 */       EList eList = s.getOutgoing();
/* 1814 */       for (Iterator<Transition> iT = eList.iterator(); iT.hasNext(); ) {
/* 1815 */         Transition trans = iT.next();
/*      */ 
/*      */         
/* 1818 */         CBlockStm transBlock = this.cFactory.createCBlockStm();
/* 1819 */         int iTrans = behav.getTransition().indexOf(trans);
/* 1820 */         CExpression exp = generateBoolPortExpr(trans.getTrigger(), iTrans, localCh);
/* 1821 */         CIfStm ifStm = this.cBuilder.createIf(exp, (CStm)transBlock, null);
/*      */         
/* 1823 */         State destSt = (State)trans.getDestination().get(0);
/*      */         
/* 1825 */         CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), (CExpression)this.cBuilder.createSimpleName(destSt.getName()));
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
/* 1837 */         if (this.forVerification) {
/* 1838 */           if (destSt.getName().equals("ERROR")) {
/* 1839 */             CFunctionCall setErr = this.cBuilder.createFuncCall("BIP_ReachErrorState");
/* 1840 */             transBlock.getContent().add(setErr);
/*      */           }
/* 1842 */           else if (destSt.getName().equals("PRUNE")) {
/* 1843 */             CFunctionCall setPrune = this.cBuilder.createFuncCall("BIP_ReachPruneState");
/* 1844 */             transBlock.getContent().add(setPrune);
/*      */           } 
/*      */         }
/*      */         
/* 1848 */         transBlock.getContent().add(ass);
/*      */ 
/*      */ 
/*      */         
/* 1852 */         TimeReset r = trans.getTimeReset();
/* 1853 */         if (r != null) {
/* 1854 */           EList reset = r.getClock();
/* 1855 */           Iterator<VariableReference> iTreset = reset.iterator();
/* 1856 */           for (int n = 1; n <= reset.size(); n++) {
/* 1857 */             VariableReference clock = iTreset.next();
/* 1858 */             String clk = clock.getTargetVariable().getName();
/* 1859 */             transBlock.getContent().add(this.cBuilder.createSimpleName(clk + "->reset()"));
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1864 */         Action a = trans.getAction();
/* 1865 */         if (a != null) {
/* 1866 */           CStm ca = this.stmGen.generateStm(a);
/* 1867 */           if (ca != null) transBlock.getContent().add(ca);
/*      */         
/*      */         } 
/*      */ 
/*      */         
/* 1872 */         ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(localCh), (CExpression)generateSync(at, behav, (CBlock)transBlock, destSt));
/*      */ 
/*      */         
/* 1875 */         transBlock.getContent().add(ass);
/*      */         
/* 1877 */         if (LastIf == null) {
/* 1878 */           ci.getContent().add(ifStm);
/*      */         } else {
/* 1880 */           LastIf.setElseCase((CStm)ifStm);
/*      */         } 
/* 1882 */         LastIf = ifStm;
/*      */       } 
/* 1884 */       CJump j = this.cFactory.createCJump();
/* 1885 */       j.setType(JumpType.CBREAK);
/* 1886 */       ci.getContent().add(j);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generateExecute(AtomType at, PetriNet behav, CClass atomTypeClass, boolean isPetri) {
/* 1894 */     CFunction f = this.cFactory.createCFunction();
/* 1895 */     atomTypeClass.getContent().add(f);
/* 1896 */     f.setType("void");
/* 1897 */     f.setName("execute");
/* 1898 */     f.setVisibility("public");
/*      */ 
/*      */     
/* 1901 */     String argType = "Port*";
/* 1902 */     String localCh = "ch";
/*      */     
/* 1904 */     f.getArgument().add(this.cBuilder.createArgument("choice", argType));
/*      */ 
/*      */     
/* 1907 */     CData choice = this.cBuilder.createData(localCh, argType, null, (CExpression)this.cBuilder.createSimpleName("choice"));
/*      */     
/* 1909 */     f.getContent().add(choice);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1916 */     int i = 0;
/* 1917 */     for (Iterator<Transition> iT = behav.getTransition().iterator(); iT.hasNext(); ) {
/* 1918 */       int arraySize; Transition transi = iT.next();
/* 1919 */       TimeSpecification ts = transi.getTimeSpecification();
/* 1920 */       int nbTimedConstraints = 0;
/*      */       
/* 1922 */       if (ts != null) {
/* 1923 */         EList c = ts.getTimedConstraint();
/* 1924 */         nbTimedConstraints = c.size();
/*      */       } 
/*      */       
/* 1927 */       CData transGuard = this.cBuilder.createData("BIP_TransGuard" + i, "bool", "private", null);
/*      */       
/* 1929 */       atomTypeClass.getContent().add(transGuard);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1934 */       if (nbTimedConstraints <= 1) { arraySize = nbTimedConstraints + 1; }
/* 1935 */       else { arraySize = nbTimedConstraints + 2; }
/*      */       
/* 1937 */       CData constraint = this.cBuilder.createData("BIP_Constraint" + i + "[" + arraySize + "]", "Constraint*", "private", null);
/*      */       
/* 1939 */       atomTypeClass.getContent().add(constraint);
/*      */       
/* 1941 */       i++;
/*      */     } 
/*      */ 
/*      */     
/* 1945 */     CWhileStm whileStm = this.cFactory.createCWhileStm();
/* 1946 */     f.getContent().add(whileStm);
/* 1947 */     whileStm.setCondition((CExpression)this.cBuilder.createSimpleName(localCh));
/*      */     
/* 1949 */     if (isPetri) {
/* 1950 */       generatePNExecutionStep(at, behav, whileStm, localCh);
/*      */     } else {
/*      */       
/* 1953 */       generateExecutionStep(at, behav, whileStm, localCh);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generateRun(AtomType at, PetriNet behav, CClass atomTypeClass, boolean isPetri) {
/* 1962 */     CFunction f = this.cFactory.createCFunction();
/* 1963 */     atomTypeClass.getContent().add(f);
/* 1964 */     f.setType("void");
/* 1965 */     f.setName("run");
/* 1966 */     f.setVisibility("public");
/*      */     
/* 1968 */     if (isPetri)
/*      */     {
/* 1970 */       for (Iterator<State> iterator = behav.getInitialState().iterator(); iterator.hasNext(); ) {
/* 1971 */         State s = iterator.next();
/*      */         
/* 1973 */         CFunctionCall getP = this.cBuilder.createFuncCall("putToken");
/* 1974 */         f.getContent().add(this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(s.getName()), (CExpression)getP));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1979 */     String argType = "Port*";
/* 1980 */     String localCh = "ch";
/*      */ 
/*      */ 
/*      */     
/* 1984 */     CData choice = this.cBuilder.createData(localCh, argType, null, (CExpression)this.cBuilder.createLiteral("NULL"));
/*      */     
/* 1986 */     f.getContent().add(choice);
/*      */ 
/*      */     
/* 1989 */     for (int i = 0; i < behav.getTransition().size(); i++) {
/* 1990 */       CData transGuard = this.cBuilder.createData("BIP_TransGuard" + i, "bool", "private", null);
/*      */       
/* 1992 */       atomTypeClass.getContent().add(transGuard);
/*      */     } 
/*      */     
/* 1995 */     CWhileStm whileStm = this.cFactory.createCWhileStm();
/* 1996 */     f.getContent().add(whileStm);
/* 1997 */     whileStm.setCondition(this.cBuilder.createLiteral(1));
/*      */     
/* 1999 */     if (isPetri) {
/* 2000 */       generatePNRunStep(at, behav, whileStm, localCh);
/*      */     } else {
/*      */       
/* 2003 */       generateRunStep(at, behav, whileStm, localCh);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generatePNRunStep(AtomType at, PetriNet behav, CWhileStm whileStm, String localCh) {
/* 2014 */     for (Iterator<PortDefinition> i = at.getPortDefinition().iterator(); i.hasNext(); ) {
/* 2015 */       PortDefinition pd = i.next();
/* 2016 */       CData portGuard = this.cBuilder.createData(pd.getName() + "_guard", "bool", null, (CExpression)this.cBuilder.createLiteral("false"));
/*      */       
/* 2018 */       whileStm.getContent().add(portGuard);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2024 */     int iTrans = 0; Iterator<Transition> iterator;
/* 2025 */     for (iterator = behav.getTransition().iterator(); iterator.hasNext(); iTrans++, iterator.next()) {
/* 2026 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), (CExpression)this.cBuilder.createLiteral("false"));
/*      */     }
/*      */ 
/*      */     
/* 2030 */     iTrans = 0;
/* 2031 */     for (iterator = behav.getTransition().iterator(); iterator.hasNext(); iTrans++) {
/* 2032 */       Transition trans = iterator.next();
/* 2033 */       CExpression transCond = generateGuardExpr(trans.getGuard());
/* 2034 */       for (Iterator<State> iS = trans.getOrigin().iterator(); iS.hasNext(); ) {
/* 2035 */         State state = iS.next();
/*      */         
/* 2037 */         CExpression stateCond = this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(state.getName()), (CExpression)this.cBuilder.createFuncCall("token"));
/*      */         
/* 2039 */         if (transCond == null) {
/* 2040 */           transCond = stateCond; continue;
/*      */         } 
/* 2042 */         transCond = this.cBuilder.createOperation(transCond, "&&", stateCond);
/*      */       } 
/*      */ 
/*      */       
/* 2046 */       CBlockStm transValidation = this.cFactory.createCBlockStm();
/* 2047 */       CIfStm ifTrans = this.cBuilder.createIf(transCond, (CStm)transValidation, null);
/* 2048 */       whileStm.getContent().add(ifTrans);
/*      */       
/* 2050 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans), generateGuardExpr(trans.getGuard()));
/*      */       
/* 2052 */       transValidation.getContent().add(ass);
/*      */       
/* 2054 */       PortExpression trigger = trans.getTrigger();
/* 2055 */       if (trigger instanceof PortDefinitionReference) {
/* 2056 */         PortDefinitionReference pdr = (PortDefinitionReference)trigger;
/* 2057 */         PortDefinition pd = pdr.getTarget();
/* 2058 */         String portGuardName = pd.getName() + "_guard";
/* 2059 */         ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(portGuardName), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(portGuardName), "||", (CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans)));
/*      */ 
/*      */         
/* 2062 */         transValidation.getContent().add(ass);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2069 */     for (Iterator<PortDefinition> iterator1 = at.getPortDefinition().iterator(); iterator1.hasNext(); ) {
/* 2070 */       PortDefinition pd = iterator1.next();
/*      */       
/* 2072 */       CData guardprio = this.cBuilder.createData(pd.getName() + "_guard" + "_prio", "bool", null, (CExpression)this.cBuilder.createSimpleName(pd.getName() + "_guard"));
/*      */ 
/*      */       
/* 2075 */       whileStm.getContent().add(guardprio);
/*      */     } 
/* 2077 */     EList eList = at.getPriorityRule();
/* 2078 */     if (eList != null) {
/* 2079 */       for (Iterator<PriorityRule> iPrio = eList.iterator(); iPrio.hasNext(); ) {
/* 2080 */         PriorityRule p = iPrio.next();
/*      */         
/* 2082 */         Interaction lowerInter = (Interaction)p.getLower();
/* 2083 */         Interaction greaterInter = (Interaction)p.getGreater();
/* 2084 */         PortDefinition lpd = ((PortDefinitionReference)lowerInter.getPort().get(0)).getTarget();
/* 2085 */         PortDefinition hpd = ((PortDefinitionReference)greaterInter.getPort().get(0)).getTarget();
/*      */         
/* 2087 */         String lp_guard = lpd.getName() + "_guard";
/* 2088 */         String hp_guard = hpd.getName() + "_guard";
/* 2089 */         String lp_guard_prio = lp_guard + "_prio";
/*      */         
/* 2091 */         CExpression hPrioritary = null;
/* 2092 */         if (p.getGuard() == null) {
/* 2093 */           CSimpleName cSimpleName = this.cBuilder.createSimpleName(hp_guard);
/*      */         } else {
/* 2095 */           hPrioritary = this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(hp_guard), "&&", this.expGen.generateExpr(p.getGuard()));
/*      */         } 
/*      */         
/* 2098 */         CAssignStm assPrio = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(lp_guard_prio), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName(lp_guard_prio), "&&", this.cBuilder.createOperation(null, "!", hPrioritary)));
/*      */ 
/*      */         
/* 2101 */         whileStm.getContent().add(assPrio);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2108 */     CFunctionCall sync = this.cBuilder.createFuncCall("sync");
/* 2109 */     sync.getArgument().add(this.cBuilder.createLiteral(at.getPortDefinition().size()));
/* 2110 */     for (Iterator<PortDefinition> iterator2 = at.getPortDefinition().iterator(); iterator2.hasNext(); ) {
/* 2111 */       PortDefinition pd = iterator2.next();
/*      */       
/* 2113 */       sync.getArgument().add(this.cBuilder.createSimpleName(pd.getName()));
/* 2114 */       sync.getArgument().add(this.cBuilder.createSimpleName(pd.getName() + "_guard" + "_prio"));
/*      */     } 
/*      */     
/* 2117 */     whileStm.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(localCh), (CExpression)sync));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2122 */     initPNgeneration();
/* 2123 */     State s = null;
/* 2124 */     while ((s = chooseNextState(behav)) != null) {
/* 2125 */       CExpression token = tokenState(s);
/* 2126 */       CBlockStm b = this.cFactory.createCBlockStm();
/* 2127 */       CIfStm ifState = this.cBuilder.createIf(token, (CStm)b, null);
/* 2128 */       whileStm.getContent().add(ifState);
/*      */ 
/*      */       
/* 2131 */       for (Iterator<Transition> iterator3 = s.getOutgoing().iterator(); iterator3.hasNext(); ) {
/* 2132 */         Transition trans = iterator3.next();
/* 2133 */         iTrans = behav.getTransition().indexOf(trans);
/* 2134 */         for (Iterator<Transition> j = s.getOutgoing().iterator(); j.hasNext(); ) {
/* 2135 */           Transition trans2 = j.next();
/* 2136 */           if (trans != trans2 && 
/* 2137 */             sameTrigger(trans.getTrigger(), trans2.getTrigger()));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2144 */         if (!this.computedTrans.contains(trans)) {
/* 2145 */           CIfStm cIfStm; CStm stm = PNTransition(trans, iTrans, localCh, true);
/* 2146 */           if (trans.getOrigin().size() > 1) {
/* 2147 */             CExpression cond = null;
/* 2148 */             for (Iterator<State> iterator4 = trans.getOrigin().iterator(); iterator4.hasNext(); ) {
/* 2149 */               State s2 = iterator4.next();
/* 2150 */               if (s2 != s) {
/* 2151 */                 if (cond == null) {
/* 2152 */                   cond = tokenState(s2); continue;
/*      */                 } 
/* 2154 */                 cond = this.cBuilder.createOperation(cond, "&&", tokenState(s2));
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/* 2159 */             cond = this.cBuilder.createOperation(cond, "&&", (CExpression)this.cBuilder.createSimpleName("BIP_TransGuard" + iTrans));
/*      */             
/* 2161 */             cIfStm = this.cBuilder.createIf(cond, stm, null);
/*      */           } 
/* 2163 */           b.getContent().add(cIfStm);
/* 2164 */           this.computedTrans.add(trans);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void generateRunStep(AtomType at, PetriNet behav, CWhileStm whileStm, String localCh) {
/* 2189 */     CSwitchStm swtStm = this.cBuilder.createSwitch("BIP_STATE");
/* 2190 */     whileStm.getContent().add(swtStm);
/*      */     
/* 2192 */     for (Iterator<State> i = behav.getState().iterator(); i.hasNext(); ) {
/* 2193 */       State s = i.next();
/* 2194 */       CCaseItem ci = this.cFactory.createCCaseItem();
/* 2195 */       swtStm.getCaseAction().add(ci);
/* 2196 */       ci.setCaseValue(s.getName());
/*      */ 
/*      */       
/* 2199 */       CIfStm LastIf = null;
/* 2200 */       CIfStm firstIfStm = null;
/* 2201 */       EList eList = s.getOutgoing();
/* 2202 */       for (Iterator<Transition> iT = eList.iterator(); iT.hasNext(); ) {
/* 2203 */         Transition trans = iT.next();
/* 2204 */         CBlockStm transBlock = this.cFactory.createCBlockStm();
/* 2205 */         int iTrans = behav.getTransition().indexOf(trans);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2211 */         CExpression exp = generateBoolPortExpr(trans.getTrigger(), iTrans, localCh);
/* 2212 */         CIfStm ifStm = this.cBuilder.createIf(exp, (CStm)transBlock, null);
/*      */         
/* 2214 */         State destSt = (State)trans.getDestination().get(0);
/*      */         
/* 2216 */         CAssignStm cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), (CExpression)this.cBuilder.createSimpleName(destSt.getName()));
/*      */ 
/*      */ 
/*      */         
/* 2220 */         transBlock.getContent().add(cAssignStm);
/*      */ 
/*      */ 
/*      */         
/* 2224 */         Action a = trans.getAction();
/* 2225 */         if (a != null) {
/* 2226 */           CStm ca = this.stmGen.generateStm(a);
/* 2227 */           if (ca != null) transBlock.getContent().add(ca);
/*      */         
/*      */         } 
/* 2230 */         if (LastIf == null) {
/* 2231 */           firstIfStm = ifStm;
/*      */         } else {
/* 2233 */           LastIf.setElseCase((CStm)ifStm);
/*      */         } 
/* 2235 */         LastIf = ifStm;
/*      */       } 
/*      */       
/* 2238 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(localCh), (CExpression)generateSync(at, behav, (CBlock)ci, s));
/*      */ 
/*      */       
/* 2241 */       ci.getContent().add(ass);
/*      */       
/* 2243 */       if (firstIfStm != null) ci.getContent().add(firstIfStm);
/*      */       
/* 2245 */       CJump j = this.cFactory.createCJump();
/* 2246 */       j.setType(JumpType.CBREAK);
/* 2247 */       ci.getContent().add(j);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CConstructor atomTypeConstructor(AtomType at) {
/* 2255 */     CConstructor cons = this.cFactory.createCConstructor();
/* 2256 */     cons.setBodyInDecl(false);
/*      */     
/* 2258 */     String name = "name";
/* 2259 */     String holder = "holder";
/* 2260 */     cons.getArgument().add(this.cBuilder.createArgument(name, "const char*"));
/* 2261 */     cons.getArgument().add(this.cBuilder.createArgument(holder, "Compound*"));
/* 2262 */     cons.setVisibility("public");
/*      */ 
/*      */ 
/*      */     
/* 2266 */     CInitialization init = this.cFactory.createCInitialization();
/* 2267 */     cons.getInit().add(init);
/* 2268 */     init.setField("Atom");
/* 2269 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(name)));
/*      */     
/* 2271 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(holder)));
/*      */ 
/*      */     
/* 2274 */     return cons;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setForVerification(boolean forVerification) {
/* 2280 */     this.forVerification = forVerification;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxAtomTypeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */