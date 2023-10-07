/*      */ package ujf.verimag.bip.cxxcodegen;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*      */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*      */ import ujf.verimag.bip.Core.Behaviors.Port;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*      */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*      */ import ujf.verimag.bip.Core.Interactions.Part;
/*      */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*      */ import ujf.verimag.bip.Core.Interactions.VariableExportBinding;
/*      */ import ujf.verimag.bip.Core.Modules.Declaration;
/*      */ import ujf.verimag.bip.Core.Modules.Module;
/*      */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*      */ import ujf.verimag.bip.Core.Modules.Root;
/*      */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*      */ import ujf.verimag.bip.cgeneration.CConstruct;
/*      */ import ujf.verimag.bip.cmodel.CAssignStm;
/*      */ import ujf.verimag.bip.cmodel.CBlockStm;
/*      */ import ujf.verimag.bip.cmodel.CClass;
/*      */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*      */ import ujf.verimag.bip.cmodel.CConstructor;
/*      */ import ujf.verimag.bip.cmodel.CCreator;
/*      */ import ujf.verimag.bip.cmodel.CData;
/*      */ import ujf.verimag.bip.cmodel.CExpression;
/*      */ import ujf.verimag.bip.cmodel.CFunction;
/*      */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*      */ import ujf.verimag.bip.cmodel.CIfStm;
/*      */ import ujf.verimag.bip.cmodel.CIndexed;
/*      */ import ujf.verimag.bip.cmodel.CInitialization;
/*      */ import ujf.verimag.bip.cmodel.CModule;
/*      */ import ujf.verimag.bip.cmodel.CReturn;
/*      */ import ujf.verimag.bip.cmodel.CSimpleName;
/*      */ import ujf.verimag.bip.cmodel.CStm;
/*      */ import ujf.verimag.bip.cmodel.CText;
/*      */ import ujf.verimag.bip.cmodel.CWhileStm;
/*      */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*      */ 
/*      */ public class CxxCompoundTypeGenerator {
/*      */   protected CConstruct cBuilder;
/*   57 */   protected CmodelFactory cFactory = CmodelFactory.eINSTANCE;
/*      */   
/*      */   private CxxExpressionGenerator expGen;
/*      */   private CxxStatementGenerator stmGen;
/*      */   private boolean generatingMultiThreadedCode = false;
/*   62 */   private int LastMaxIndice = 0;
/*      */   
/*      */   Set subConnectors;
/*      */   boolean bip_debug;
/*      */   
/*      */   public void setDebug(boolean debug) {
/*   68 */     this.bip_debug = debug;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CxxCompoundTypeGenerator(CConstruct builder, CxxExpressionGenerator expGen, CxxStatementGenerator stmGen) {
/*   75 */     this.cBuilder = builder;
/*   76 */     this.expGen = expGen;
/*   77 */     this.stmGen = stmGen;
/*      */   }
/*      */   private void generateDebugFunctions(CompoundType ct, CClass elementClass) {
/*      */     CIfStm cIfStm2, cIfStm1;
/*   81 */     int nbSubElements = 0;
/*   82 */     int nbValuedElements = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   91 */     CFunction f = this.cFactory.createCFunction();
/*   92 */     elementClass.getContent().add(f);
/*   93 */     f.setName("getSubElement");
/*   94 */     f.setType("BipDebugElmt *");
/*   95 */     f.setVisibility("public");
/*   96 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/*      */     
/*   98 */     CFunction f2 = this.cFactory.createCFunction();
/*   99 */     elementClass.getContent().add(f2);
/*  100 */     f2.setName("getSubElementsNames");
/*  101 */     f2.setType("char **");
/*  102 */     f2.setVisibility("public");
/*  103 */     CCreator newExp = this.cFactory.createCCreator();
/*  104 */     newExp.setArrayAllocator(true);
/*  105 */     newExp.setType("char *");
/*  106 */     f2.getContent().add(this.cBuilder.createData("res", "char **", null, (CExpression)newExp));
/*  107 */     f2.getContent().add(this.cBuilder.createData("i", "int", null, this.cBuilder.createLiteral(0)));
/*      */     
/*  109 */     CReturn cReturn2 = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/*  110 */     for (Iterator<Connector> iterator = ct.getConnector().iterator(); iterator.hasNext(); ) {
/*  111 */       Connector cnx = iterator.next();
/*  112 */       boolean isDebugable = false;
/*  113 */       Module m = cnx.getType().getModule();
/*      */       
/*  115 */       if (m == ct.getModule()) {
/*  116 */         isDebugable = true;
/*      */       }
/*      */ 
/*      */       
/*  120 */       if (isDebugable) {
/*      */         
/*  122 */         CFunctionCall strcmp = this.cBuilder.createFuncCall("strcmp");
/*  123 */         strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  124 */         strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + cnx.getName() + "\""));
/*      */         
/*  126 */         CExpression cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */         
/*  128 */         CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(cnx.getName()));
/*      */         
/*  130 */         CIfStm is = this.cBuilder.createIf(cond, (CStm)cReturn, (CStm)cReturn2);
/*  131 */         cIfStm2 = is;
/*      */ 
/*      */         
/*  134 */         nbSubElements++;
/*  135 */         CIndexed cIndexed = this.cFactory.createCIndexed();
/*  136 */         cIndexed.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  137 */         cIndexed.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  138 */         CAssignStm cAssignStm = this.cBuilder.createAssign((CExpression)cIndexed, (CExpression)this.cBuilder.createLiteral("\"" + cnx.getName() + "\""));
/*  139 */         f2.getContent().add(cAssignStm);
/*  140 */         cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */         
/*  143 */         f2.getContent().add(cAssignStm);
/*      */       } 
/*      */     } 
/*  146 */     for (Iterator<Component> i = ct.getSubcomponent().iterator(); i.hasNext(); ) {
/*  147 */       Component comp = i.next();
/*  148 */       boolean isDebugable = false;
/*  149 */       Module m = comp.getType().getModule();
/*      */       
/*  151 */       if (m == ct.getModule()) {
/*  152 */         isDebugable = true;
/*      */       } else {
/*      */         
/*  155 */         isDebugable = true;
/*      */       } 
/*  157 */       if (isDebugable) {
/*      */         
/*  159 */         CFunctionCall strcmp = this.cBuilder.createFuncCall("strcmp");
/*  160 */         strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  161 */         strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + comp.getName() + "\""));
/*      */         
/*  163 */         CExpression cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */         
/*  165 */         CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(comp.getName()));
/*      */         
/*  167 */         CIfStm is = this.cBuilder.createIf(cond, (CStm)cReturn, (CStm)cIfStm2);
/*  168 */         cIfStm2 = is;
/*      */         
/*  170 */         nbSubElements++;
/*  171 */         CIndexed cIndexed = this.cFactory.createCIndexed();
/*  172 */         cIndexed.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  173 */         cIndexed.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  174 */         CAssignStm cAssignStm = this.cBuilder.createAssign((CExpression)cIndexed, (CExpression)this.cBuilder.createLiteral("\"" + comp.getName() + "\""));
/*  175 */         f2.getContent().add(cAssignStm);
/*  176 */         cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */         
/*  179 */         f2.getContent().add(cAssignStm);
/*      */       } 
/*      */     } 
/*  182 */     f.getContent().add(cIfStm2);
/*  183 */     nbSubElements++;
/*  184 */     CIndexed ires = this.cFactory.createCIndexed();
/*  185 */     ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  186 */     ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  187 */     CAssignStm ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("NULL"));
/*  188 */     f2.getContent().add(ass);
/*  189 */     ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */     
/*  191 */     newExp.getArgument().add(this.cBuilder.createLiteral(nbSubElements));
/*  192 */     f2.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("res")));
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
/*  203 */     f = this.cFactory.createCFunction();
/*  204 */     elementClass.getContent().add(f);
/*  205 */     f.setName("getValue");
/*  206 */     f.setType("char *");
/*  207 */     f.setVisibility("public");
/*  208 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/*  209 */     f.getContent().add(this.cBuilder.createCCode("char* _numericData= new char[100];"));
/*      */     
/*  211 */     f2 = this.cFactory.createCFunction();
/*  212 */     elementClass.getContent().add(f2);
/*  213 */     f2.setName("getValuedElementNames");
/*  214 */     f2.setType("char **");
/*  215 */     f2.setVisibility("public");
/*  216 */     newExp = this.cFactory.createCCreator();
/*  217 */     newExp.setArrayAllocator(true);
/*  218 */     newExp.setType("char *");
/*  219 */     f2.getContent().add(this.cBuilder.createData("res", "char **", null, (CExpression)newExp));
/*  220 */     f2.getContent().add(this.cBuilder.createData("i", "int", null, this.cBuilder.createLiteral(0)));
/*      */     
/*  222 */     CReturn cReturn1 = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/*  223 */     for (Iterator<DataParameter> iterator1 = ct.getDataParameter().iterator(); iterator1.hasNext(); ) {
/*  224 */       DataParameter dp = iterator1.next();
/*  225 */       CFunctionCall strcmp = this.cBuilder.createFuncCall("strcmp");
/*  226 */       strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/*  227 */       strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + dp.getName() + "\""));
/*  228 */       DataType dt = dp.getType();
/*  229 */       CBlockStm b = this.cFactory.createCBlockStm();
/*  230 */       String dataType = "";
/*  231 */       if (dt instanceof OpaqueElement) {
/*  232 */         OpaqueElement oe = (OpaqueElement)dt;
/*  233 */         dataType = oe.getBody();
/*      */       } 
/*  235 */       if (dataType.equals("int")) {
/*  236 */         CFunctionCall sprintf = this.cBuilder.createFuncCall("sprintf");
/*  237 */         sprintf.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  238 */         sprintf.getArgument().add(this.cBuilder.createLiteral("\"%d\""));
/*  239 */         sprintf.getArgument().add(this.cBuilder.createSimpleName(dp.getName()));
/*  240 */         b.getContent().add(sprintf);
/*  241 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*  242 */       } else if (dataType.equals("bool")) {
/*  243 */         CFunctionCall printTrue = this.cBuilder.createFuncCall("sprintf");
/*  244 */         printTrue.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  245 */         printTrue.getArgument().add(this.cBuilder.createLiteral("\"true\""));
/*  246 */         CFunctionCall printFalse = this.cBuilder.createFuncCall("sprintf");
/*  247 */         printFalse.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*  248 */         printFalse.getArgument().add(this.cBuilder.createLiteral("\"false\""));
/*  249 */         CIfStm ifPrint = this.cBuilder.createIf((CExpression)this.cBuilder.createSimpleName(dp.getName()), (CStm)printTrue, (CStm)printFalse);
/*      */         
/*  251 */         b.getContent().add(ifPrint);
/*  252 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*      */       } else {
/*  254 */         CFunctionCall strrep = this.cBuilder.createFuncCall("strrep_" + dataType);
/*  255 */         strrep.getArgument().add(this.cBuilder.createSimpleName(dp.getName()));
/*  256 */         strrep.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/*      */         
/*  258 */         b.getContent().add(strrep);
/*      */         
/*  260 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*      */       } 
/*      */       
/*  263 */       CExpression cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*      */       
/*  265 */       CIfStm is = this.cBuilder.createIf(cond, (CStm)b, (CStm)cReturn1);
/*  266 */       cIfStm1 = is;
/*      */ 
/*      */       
/*  269 */       nbValuedElements++;
/*  270 */       ires = this.cFactory.createCIndexed();
/*  271 */       ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  272 */       ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  273 */       ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("\"" + dp.getName() + "\""));
/*  274 */       f2.getContent().add(ass);
/*  275 */       ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */       
/*  278 */       f2.getContent().add(ass);
/*      */     } 
/*      */     
/*  281 */     f.getContent().add(cIfStm1);
/*  282 */     nbValuedElements++;
/*  283 */     ires = this.cFactory.createCIndexed();
/*  284 */     ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/*  285 */     ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/*  286 */     ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("NULL"));
/*  287 */     f2.getContent().add(ass);
/*  288 */     ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*      */     
/*  290 */     newExp.getArgument().add(this.cBuilder.createLiteral(nbValuedElements));
/*  291 */     f2.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("res")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  298 */     f = this.cFactory.createCFunction();
/*  299 */     elementClass.getContent().add(f);
/*  300 */     f.setName("getParent");
/*  301 */     f.setType("BipDebugElmt *");
/*  302 */     f.setVisibility("public");
/*  303 */     f.getContent().add(this.cBuilder.createCCode("return dynamic_cast<BipDebugElmt*>( mHolder);"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void generateRoot(Root root, CModule cmodule) {
/*  312 */     String name = root.getName();
/*  313 */     String typeName = CxxNames.componentTypeClassName(root.getType().getName());
/*      */     
/*  315 */     CFunction create = this.cFactory.createCFunction();
/*  316 */     create.setName("Compound::Create");
/*      */     
/*  318 */     create.setType("Compound*");
/*  319 */     create.getArgument().add(this.cBuilder.createArgument("argc", "int"));
/*  320 */     create.getArgument().add(this.cBuilder.createArgument("argv", "char**"));
/*  321 */     cmodule.getContent().add(create);
/*      */ 
/*      */     
/*  324 */     CCreator newExp = this.cFactory.createCCreator();
/*  325 */     create.getContent().add(this.cBuilder.createReturn((CExpression)newExp));
/*  326 */     newExp.setType(typeName);
/*  327 */     newExp.getArgument().add(this.cBuilder.createLiteral("\"BIP_Top\""));
/*  328 */     newExp.getArgument().add(this.cBuilder.createLiteral("NULL"));
/*      */     
/*  330 */     for (Iterator<Expression> iActual = root.getActualData().iterator(); iActual.hasNext(); ) {
/*  331 */       Expression exp = iActual.next();
/*  332 */       newExp.getArgument().add(this.expGen.generateExpr(exp));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isSubconnector(Connector cnx) {
/*  341 */     return this.subConnectors.contains(cnx);
/*      */   }
/*      */   private void addSubConnectors(ActualPortParameter app) {
/*  344 */     if (app instanceof InnerPortReference) {
/*  345 */       InnerPortReference ipr = (InnerPortReference)app;
/*  346 */       Part part = ipr.getTargetInstance().getTargetPart();
/*  347 */       if (part instanceof Connector) {
/*  348 */         this.subConnectors.add(part);
/*      */       }
/*  350 */     } else if (app instanceof ConditionalActualPortParameter) {
/*  351 */       ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/*  352 */       addSubConnectors(capp.getFalseCase());
/*  353 */       addSubConnectors(capp.getTrueCase());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void initSubconnectorSet(CompoundType ct) {
/*  359 */     this.subConnectors = new HashSet();
/*  360 */     for (Iterator<Port> iterator = ct.getPort().iterator(); iterator.hasNext(); ) {
/*  361 */       Port p = iterator.next();
/*  362 */       Binding b = p.getBinding();
/*  363 */       if (b instanceof ExportBinding) {
/*  364 */         ExportBinding eb = (ExportBinding)b;
/*  365 */         Part part = eb.getTargetInstance().getTargetPart();
/*  366 */         if (part instanceof Connector) {
/*  367 */           this.subConnectors.add(part);
/*      */         }
/*      */       } 
/*      */     } 
/*  371 */     for (Iterator<Connector> i = ct.getConnector().iterator(); i.hasNext(); ) {
/*  372 */       Connector cnx = i.next();
/*  373 */       for (Iterator<ActualPortParameter> j = cnx.getActualPort().iterator(); j.hasNext(); ) {
/*  374 */         ActualPortParameter app = j.next();
/*  375 */         addSubConnectors(app);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void generateType(CompoundType ct, CModule cmodule, boolean multi) {
/*  381 */     this.generatingMultiThreadedCode = multi;
/*      */     
/*  383 */     initSubconnectorSet(ct);
/*  384 */     this.LastMaxIndice = 0;
/*  385 */     CClass compoundTypeClass = this.cBuilder.createClass(CxxNames.componentTypeClassName(ct.getName()), "public", "Compound");
/*      */     
/*  387 */     cmodule.getContent().add(compoundTypeClass);
/*      */     
/*  389 */     if (this.bip_debug) {
/*  390 */       compoundTypeClass.getSuperClasses().add("BipDebugElmt");
/*  391 */       generateDebugFunctions(ct, compoundTypeClass);
/*      */     } 
/*      */     
/*  394 */     CConstructor cons = compoundTypeConstructor();
/*  395 */     compoundTypeClass.getContent().add(cons);
/*      */     
/*  397 */     for (Iterator<Declaration> iterator4 = ct.getDeclaration().iterator(); iterator4.hasNext(); ) {
/*  398 */       Declaration decl = iterator4.next();
/*  399 */       if (decl instanceof OpaqueElement) {
/*  400 */         OpaqueElement op = (OpaqueElement)decl;
/*  401 */         CText cCode = this.cBuilder.createCCode(op.getBody());
/*  402 */         if (!op.isIsHeader()) {
/*  403 */           cCode.setInBodyFile(true);
/*  404 */           cons.getContent().add(0, cCode); continue;
/*      */         } 
/*  406 */         compoundTypeClass.getContent().add(cCode);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     for (Iterator<DataParameter> iterator3 = ct.getDataParameter().iterator(); iterator3.hasNext(); ) {
/*  417 */       DataParameter dp = iterator3.next();
/*  418 */       String name = dp.getName();
/*  419 */       OpaqueElement dt = (OpaqueElement)dp.getType();
/*  420 */       String paramName = "BIPParam" + dp.getName();
/*  421 */       String typeName = dt.getBody();
/*  422 */       CData d = this.cBuilder.createData(name, typeName, "public", null);
/*  423 */       compoundTypeClass.getContent().add(d);
/*  424 */       cons.getArgument().add(this.cBuilder.createArgument(paramName, typeName));
/*  425 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(name), (CExpression)this.cBuilder.createSimpleName(paramName));
/*      */       
/*  427 */       cons.getContent().add(ass);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  433 */     for (Iterator<Port> iterator2 = ct.getPort().iterator(); iterator2.hasNext(); ) {
/*  434 */       Port p = iterator2.next();
/*  435 */       String portName = p.getName();
/*  436 */       PortType pt = p.getType();
/*  437 */       String scopeName = pt.getScope();
/*  438 */       String typeName = (scopeName != null) ? (scopeName + "::" + pt.getName()) : pt.getName();
/*      */ 
/*      */       
/*  441 */       CFunction f = this.cFactory.createCFunction();
/*  442 */       compoundTypeClass.getContent().add(f);
/*  443 */       f.setName("get_" + portName);
/*  444 */       f.setType(CxxNames.portTypeClassName(typeName) + "*");
/*  445 */       f.setQualifier("const");
/*  446 */       f.setVisibility("public");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  451 */       Binding b = p.getBinding();
/*  452 */       CExpression cExpression = null;
/*  453 */       if (b instanceof ExportBinding) {
/*  454 */         ExportBinding eb = (ExportBinding)b;
/*  455 */         PartElementReference per = eb.getTargetInstance();
/*  456 */         Part part = per.getTargetPart();
/*  457 */         String partName = part.getName();
/*  458 */         CFunctionCall cFunctionCall = this.cBuilder.createFuncCall("get_" + partName);
/*  459 */         for (Iterator<Expression> j = per.getIndex().iterator(); j.hasNext(); ) {
/*  460 */           Expression ind = j.next();
/*  461 */           cExpression = this.cBuilder.createIndexed((CExpression)cFunctionCall, this.expGen.generateExpr(ind));
/*      */         } 
/*  463 */         String exportPortName = eb.getTargetPort().getName();
/*  464 */         cExpression = this.cBuilder.createPointed(cExpression, (CExpression)this.cBuilder.createFuncCall("get_" + exportPortName));
/*      */       } 
/*      */       
/*  467 */       f.getContent().add(this.cBuilder.createReturn(cExpression));
/*      */     } 
/*      */     
/*  470 */     for (Iterator<InterfaceVariable> iterator1 = ct.getInterfaceVariable().iterator(); iterator1.hasNext(); ) {
/*  471 */       InterfaceVariable iv = iterator1.next();
/*  472 */       VariableExportBinding veb = (VariableExportBinding)iv.getVariableBinding().get(0);
/*  473 */       String varName = iv.getName();
/*  474 */       OpaqueElement dt = (OpaqueElement)veb.getTargetInterfaceVariable().getType();
/*  475 */       CFunction f = this.cFactory.createCFunction();
/*  476 */       compoundTypeClass.getContent().add(f);
/*  477 */       f.setName("get_" + varName);
/*  478 */       f.setType(dt.getBody());
/*  479 */       f.setQualifier("const");
/*  480 */       f.setSpecifier("inline");
/*  481 */       f.setVisibility("public");
/*  482 */       f.setBodyInDecl(true);
/*      */       
/*  484 */       PartElementReference per = veb.getTargetInstance();
/*  485 */       Part part = per.getTargetPart();
/*  486 */       String partName = part.getName();
/*  487 */       CFunctionCall cFunctionCall = this.cBuilder.createFuncCall("get_" + partName);
/*  488 */       for (Iterator<Expression> j = per.getIndex().iterator(); j.hasNext(); ) {
/*  489 */         Expression ind = j.next();
/*  490 */         cExpression = this.cBuilder.createIndexed((CExpression)cFunctionCall, this.expGen.generateExpr(ind));
/*      */       } 
/*  492 */       String exportPortName = veb.getTargetInterfaceVariable().getName();
/*  493 */       CExpression cExpression = this.cBuilder.createPointed(cExpression, (CExpression)this.cBuilder.createFuncCall("get_" + exportPortName));
/*      */ 
/*      */       
/*  496 */       f.getContent().add(this.cBuilder.createReturn(cExpression));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  502 */     for (Iterator<Component> iterator = ct.getSubcomponent().iterator(); iterator.hasNext(); ) {
/*  503 */       Component comp = iterator.next();
/*  504 */       String name = comp.getName();
/*  505 */       String typeName = CxxNames.componentTypeClassName(comp.getType().getName());
/*  506 */       String scopeName = comp.getType().getScope();
/*  507 */       if (scopeName != null) {
/*  508 */         typeName = scopeName + "::" + typeName;
/*      */       }
/*  510 */       CCreator create = addInstance(compoundTypeClass, cons, name, typeName, (List)comp.getMultiplicitySpecification(), (List)comp.getActualData());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  515 */     for (Iterator<Connector> i = ct.getConnector().iterator(); i.hasNext(); ) {
/*  516 */       Connector cnx = i.next();
/*  517 */       addConnector(compoundTypeClass, cons, cnx);
/*      */     } 
/*      */ 
/*      */     
/*  521 */     boolean first = true;
/*  522 */     for (Iterator<PriorityRule> iterator5 = ct.getPriorityRule().iterator(); iterator5.hasNext(); ) {
/*  523 */       PriorityRule pr = iterator5.next();
/*  524 */       addPriority(compoundTypeClass, cons, pr, first, cmodule);
/*  525 */       first = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private CExpression createInteraction(Interaction inter) {
/*      */     CExpression cExpression;
/*  532 */     CFunctionCall createInter = this.cBuilder.createFuncCall("Interaction::Create");
/*      */     
/*  534 */     PartElementReference per = inter.getConnector();
/*  535 */     Connector cnx = (Connector)per.getTargetPart();
/*  536 */     CFunctionCall cFunctionCall1 = this.cBuilder.createFuncCall("get_" + cnx.getName());
/*  537 */     for (Iterator<Expression> iInd = per.getIndex().iterator(); iInd.hasNext(); ) {
/*  538 */       Expression ind = iInd.next();
/*  539 */       cExpression = this.cBuilder.createIndexed((CExpression)cFunctionCall1, this.expGen.generateExpr(ind));
/*      */     } 
/*  541 */     createInter.getArgument().add(cExpression);
/*  542 */     createInter.getArgument().add(this.cBuilder.createLiteral(inter.getPort().size()));
/*  543 */     for (Iterator<ActualPortParameter> i = inter.getPort().iterator(); i.hasNext(); ) {
/*  544 */       ActualPortParameter app = i.next();
/*  545 */       createInter.getArgument().add(createGetPortFct(app, per));
/*      */     } 
/*  547 */     return (CExpression)createInter;
/*      */   }
/*      */   
/*      */   private CExpression createInteraction(Connector cnx, String IndiceExt) {
/*      */     CExpression cExpression;
/*  552 */     CFunctionCall createInter = this.cBuilder.createFuncCall("Interaction::Create");
/*      */     
/*  554 */     CFunctionCall cFunctionCall1 = this.cBuilder.createFuncCall("get_" + cnx.getName());
/*  555 */     for (int b = 0; b < cnx.getMultiplicitySpecification().size(); b++) {
/*  556 */       cExpression = this.cBuilder.createIndexed((CExpression)cFunctionCall1, (CExpression)this.cBuilder.createSimpleName("BIPind" + IndiceExt + (b + 1)));
/*      */     }
/*      */     
/*  559 */     createInter.getArgument().add(cExpression);
/*  560 */     createInter.getArgument().add(this.cBuilder.createLiteral(0));
/*  561 */     return (CExpression)createInter;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private CExpression createGetPortFct(ActualPortParameter app, PartElementReference per) {
/*  567 */     if (app instanceof InnerPortReference) {
/*  568 */       InnerPortReference ipr = (InnerPortReference)app;
/*  569 */       String compName = ipr.getTargetInstance().getTargetPart().getName();
/*  570 */       CFunctionCall cFunctionCall = this.cBuilder.createFuncCall("get_" + compName);
/*  571 */       for (Iterator<Expression> iInd = per.getIndex().iterator(); iInd.hasNext(); ) {
/*  572 */         Expression ind = iInd.next();
/*  573 */         cExpression = this.cBuilder.createIndexed((CExpression)cFunctionCall, this.expGen.generateExpr(ind));
/*      */       } 
/*  575 */       CExpression cExpression = this.cBuilder.createPointed(cExpression, (CExpression)this.cBuilder.createFuncCall("get_" + ipr.getTargetPort().getName()));
/*  576 */       return cExpression;
/*  577 */     }  if (app instanceof ConditionalActualPortParameter) {
/*  578 */       ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/*  579 */       CConditionalExpression ce = this.cFactory.createCConditionalExpression();
/*  580 */       ce.setFalseCase(createGetPortFct(capp.getFalseCase(), per));
/*  581 */       ce.setTrueCase(createGetPortFct(capp.getTrueCase(), per));
/*  582 */       ce.setCondition(this.expGen.generateExpr(capp.getExpression()));
/*  583 */       return (CExpression)ce;
/*      */     } 
/*  585 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addPriority(CClass compoundTypeClass, CConstructor cons, PriorityRule pr, boolean first, CModule cmodule) {
/*  593 */     String prioTypeName = "Priority";
/*  594 */     if (pr.getGuard() != null) {
/*      */       
/*  596 */       prioTypeName = "Priority" + compoundTypeClass.getName() + pr.getName();
/*      */       
/*  598 */       CClass prioTypeClass = this.cBuilder.createClass(prioTypeName, "public", "Priority");
/*      */       
/*  600 */       cmodule.getContent().add(prioTypeClass);
/*      */       
/*  602 */       prioTypeClass.getContent().add(this.cBuilder.createData("mHolder", pr.getCompoundType().getName() + "*", "private", null));
/*      */ 
/*      */       
/*  605 */       CConstructor consPrio = this.cFactory.createCConstructor();
/*  606 */       prioTypeClass.getContent().add(consPrio);
/*      */       
/*  608 */       consPrio.setBodyInDecl(true);
/*  609 */       consPrio.setVisibility("public");
/*      */       
/*  611 */       String comp = "comp";
/*  612 */       String low = "low";
/*  613 */       String high = "high";
/*  614 */       consPrio.getArgument().add(this.cBuilder.createArgument(comp, pr.getCompoundType().getName() + "*"));
/*  615 */       consPrio.getArgument().add(this.cBuilder.createArgument(low, "Interaction*"));
/*  616 */       consPrio.getArgument().add(this.cBuilder.createArgument(high, "Interaction*"));
/*  617 */       CInitialization init = this.cFactory.createCInitialization();
/*  618 */       consPrio.getInit().add(init);
/*  619 */       init.setField("Priority");
/*  620 */       init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(comp)));
/*      */       
/*  622 */       init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(low)));
/*      */       
/*  624 */       init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(high)));
/*      */ 
/*      */       
/*  627 */       consPrio.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("mHolder"), (CExpression)this.cBuilder.createSimpleName(comp)));
/*      */ 
/*      */ 
/*      */       
/*  631 */       CFunction guard = this.cFactory.createCFunction();
/*  632 */       prioTypeClass.getContent().add(guard);
/*  633 */       guard.setType("bool");
/*  634 */       guard.setName("guard");
/*  635 */       guard.setVisibility("public");
/*  636 */       guard.setQualifier("const");
/*  637 */       guard.getContent().add(this.cBuilder.createReturn(this.expGen.generateExpr(pr.getGuard())));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  644 */     if (first) {
/*      */       
/*  646 */       cons.getContent().add(this.cBuilder.createData("low", "Interaction*", null, (CExpression)this.cBuilder.createLiteral("NULL")));
/*      */       
/*  648 */       cons.getContent().add(this.cBuilder.createData("high", "Interaction*", null, (CExpression)this.cBuilder.createLiteral("NULL")));
/*      */     } 
/*      */ 
/*      */     
/*  652 */     PriorityElement lowerElmt = pr.getLower();
/*  653 */     PriorityElement greaterElmt = pr.getGreater();
/*      */     
/*  655 */     if (greaterElmt instanceof Interaction && lowerElmt instanceof Interaction) {
/*      */       
/*  657 */       Interaction lowerInter = (Interaction)lowerElmt;
/*  658 */       Interaction greaterInter = (Interaction)greaterElmt;
/*  659 */       CExpression newInter = createInteraction(lowerInter);
/*  660 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("low"), newInter);
/*      */       
/*  662 */       cons.getContent().add(ass);
/*  663 */       newInter = createInteraction(greaterInter);
/*  664 */       ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("high"), newInter);
/*      */       
/*  666 */       cons.getContent().add(ass);
/*  667 */       cons.getContent().add(addCreatePrio(prioTypeName));
/*      */     }
/*  669 */     else if (greaterElmt == null) {
/*  670 */       Interaction lowerInter = (Interaction)lowerElmt;
/*  671 */       CExpression newInter = createInteraction(lowerInter);
/*  672 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("low"), newInter);
/*      */       
/*  674 */       cons.getContent().add(ass);
/*      */       
/*  676 */       ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("high"), (CExpression)this.cBuilder.createLiteral("NULL"));
/*      */       
/*  678 */       cons.getContent().add(ass);
/*  679 */       cons.getContent().add(addCreatePrio(prioTypeName));
/*      */     } else {
/*      */       
/*  682 */       CompoundType ct = (CompoundType)pr.getCompoundType();
/*      */       
/*  684 */       if (lowerElmt instanceof ConnectorTypeReference) {
/*  685 */         ConnectorType lowCnxType = ((ConnectorTypeReference)lowerElmt).getTarget();
/*  686 */         for (Iterator<Connector> i = ct.getConnector().iterator(); i.hasNext(); ) {
/*  687 */           Connector cnxLow = i.next();
/*  688 */           if (cnxLow.getType() == lowCnxType) {
/*  689 */             CWhileStm w = null;
/*      */             
/*  691 */             EList eList = cnxLow.getMultiplicitySpecification();
/*  692 */             int arraySize = cnxLow.getMultiplicitySpecification().size();
/*  693 */             if (arraySize > 0) {
/*  694 */               for (int iMult = 0; iMult < arraySize; iMult++) {
/*      */                 
/*  696 */                 CAssignStm init = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), this.cBuilder.createLiteral(0));
/*      */                 
/*  698 */                 if (w == null) {
/*  699 */                   cons.getContent().add(init);
/*      */                 } else {
/*  701 */                   w.getContent().add(w.getContent().size() - 1, init);
/*      */                 } 
/*      */                 
/*  704 */                 CWhileStm lastW = w;
/*  705 */                 w = this.cFactory.createCWhileStm();
/*  706 */                 w.setCondition(this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), "<", (CExpression)this.cBuilder.createSimpleName(cnxLow.getName() + "BIPdimSize" + (iMult + 1))));
/*      */                 
/*  708 */                 CAssignStm increment = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), "+", this.cBuilder.createLiteral(1)));
/*      */                 
/*  710 */                 w.getContent().add(increment);
/*  711 */                 if (lastW == null) {
/*  712 */                   cons.getContent().add(w);
/*      */                 } else {
/*  714 */                   lastW.getContent().add(lastW.getContent().size() - 1, w);
/*      */                 } 
/*      */               } 
/*      */             }
/*      */             
/*  719 */             if (greaterElmt instanceof ConnectorTypeReference) {
/*  720 */               ConnectorType greaterCnxType = ((ConnectorTypeReference)greaterElmt).getTarget();
/*  721 */               CWhileStm declWhile = w;
/*  722 */               int maxInd2 = 0;
/*  723 */               for (Iterator<Connector> j = ct.getConnector().iterator(); j.hasNext(); ) {
/*  724 */                 Connector cnxHigh = j.next();
/*  725 */                 String indiceExt = "";
/*  726 */                 if (cnxHigh.getType() == greaterCnxType) {
/*  727 */                   w = declWhile;
/*  728 */                   EList eList1 = cnxHigh.getMultiplicitySpecification();
/*  729 */                   if (eList1.size() > 0) {
/*  730 */                     int arraySize2 = cnxHigh.getMultiplicitySpecification().size();
/*  731 */                     for (int iMult = 0; iMult < arraySize2; iMult++) {
/*  732 */                       if (arraySize > 0 && arraySize2 > 0) {
/*  733 */                         indiceExt = "2_";
/*  734 */                         if (iMult >= maxInd2) {
/*  735 */                           CData di = this.cBuilder.createData("BIPind" + indiceExt + (iMult + 1), "int", null, null);
/*  736 */                           declWhile.getContent().add(0, di);
/*  737 */                           maxInd2++;
/*      */                         } 
/*      */                       } 
/*      */                       
/*  741 */                       CAssignStm init = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + indiceExt + (iMult + 1)), this.cBuilder.createLiteral(0));
/*      */                       
/*  743 */                       if (w == null) {
/*  744 */                         cons.getContent().add(init);
/*      */                       } else {
/*  746 */                         w.getContent().add(w.getContent().size() - 1, init);
/*      */                       } 
/*      */ 
/*      */                       
/*  750 */                       CWhileStm lastW = w;
/*  751 */                       w = this.cFactory.createCWhileStm();
/*  752 */                       w.setCondition(this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + indiceExt + (iMult + 1)), "<", (CExpression)this.cBuilder.createSimpleName(cnxHigh.getName() + "BIPdimSize" + (iMult + 1))));
/*      */                       
/*  754 */                       CAssignStm increment = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + indiceExt + (iMult + 1)), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + indiceExt + (iMult + 1)), "+", this.cBuilder.createLiteral(1)));
/*      */                       
/*  756 */                       w.getContent().add(increment);
/*  757 */                       if (lastW == null) {
/*  758 */                         cons.getContent().add(w);
/*      */                       } else {
/*  760 */                         lastW.getContent().add(lastW.getContent().size() - 1, w);
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                   
/*  765 */                   CExpression cExpression = createInteraction(cnxLow, "");
/*  766 */                   CAssignStm cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("low"), cExpression);
/*      */                   
/*  768 */                   if (w == null) { cons.getContent().add(cAssignStm); }
/*  769 */                   else { w.getContent().add(w.getContent().size() - 1, cAssignStm); }
/*  770 */                    cExpression = createInteraction(cnxHigh, indiceExt);
/*  771 */                   cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("high"), cExpression);
/*      */                   
/*  773 */                   if (w == null) { cons.getContent().add(cAssignStm); }
/*  774 */                   else { w.getContent().add(w.getContent().size() - 1, cAssignStm); }
/*  775 */                    if (w == null) { cons.getContent().add(addCreatePrio(prioTypeName)); continue; }
/*  776 */                    w.getContent().add(w.getContent().size() - 1, addCreatePrio(prioTypeName));
/*      */                 } 
/*      */               }  continue;
/*      */             } 
/*  780 */             CExpression newInter = createInteraction(cnxLow, "");
/*  781 */             CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("low"), newInter);
/*      */             
/*  783 */             if (w == null) { cons.getContent().add(ass); }
/*  784 */             else { w.getContent().add(w.getContent().size() - 1, ass); }
/*  785 */              newInter = createInteraction((Interaction)greaterElmt);
/*  786 */             ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("high"), newInter);
/*      */             
/*  788 */             if (w == null) { cons.getContent().add(ass); }
/*  789 */             else { w.getContent().add(w.getContent().size() - 1, ass); }
/*  790 */              if (w == null) { cons.getContent().add(addCreatePrio(prioTypeName)); continue; }
/*  791 */              w.getContent().add(w.getContent().size() - 1, addCreatePrio(prioTypeName));
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  800 */         ConnectorType greaterCnxType = ((ConnectorTypeReference)greaterElmt).getTarget();
/*  801 */         for (Iterator<Connector> j = ct.getConnector().iterator(); j.hasNext(); ) {
/*  802 */           Connector cnxHigh = j.next();
/*  803 */           if (cnxHigh.getType() == greaterCnxType) {
/*  804 */             CWhileStm w = null;
/*  805 */             EList eList = cnxHigh.getMultiplicitySpecification();
/*  806 */             int arraySize = cnxHigh.getMultiplicitySpecification().size();
/*  807 */             if (arraySize > 0) {
/*  808 */               for (int iMult = 0; iMult < arraySize; iMult++) {
/*      */                 
/*  810 */                 CAssignStm init = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), this.cBuilder.createLiteral(0));
/*      */ 
/*      */ 
/*      */                 
/*  814 */                 CWhileStm lastW = w;
/*  815 */                 w = this.cFactory.createCWhileStm();
/*  816 */                 w.setCondition(this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), "<", (CExpression)this.cBuilder.createSimpleName(cnxHigh.getName() + "BIPdimSize" + (iMult + 1))));
/*      */                 
/*  818 */                 CAssignStm increment = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (iMult + 1)), "+", this.cBuilder.createLiteral(1)));
/*      */                 
/*  820 */                 w.getContent().add(increment);
/*  821 */                 if (lastW == null) {
/*  822 */                   cons.getContent().add(w);
/*      */                 } else {
/*  824 */                   lastW.getContent().add(w);
/*      */                 } 
/*      */               } 
/*      */             }
/*      */             
/*  829 */             CExpression newInter = createInteraction((Interaction)lowerElmt);
/*  830 */             CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("low"), newInter);
/*      */             
/*  832 */             cons.getContent().add(ass);
/*  833 */             newInter = createInteraction(cnxHigh, "");
/*  834 */             ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("high"), newInter);
/*      */             
/*  836 */             if (w == null) { cons.getContent().add(ass); }
/*  837 */             else { w.getContent().add(ass); }
/*  838 */              if (w == null) { cons.getContent().add(addCreatePrio(prioTypeName)); continue; }
/*  839 */              w.getContent().add(addCreatePrio(prioTypeName));
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
/*      */   private CStm addCreatePrio(String prioTypeName) {
/*      */     CFunctionCall addPrio;
/*  852 */     CCreator newPrio = this.cFactory.createCCreator();
/*  853 */     newPrio.setType(prioTypeName);
/*  854 */     newPrio.getArgument().add(this.cBuilder.createLiteral("this"));
/*  855 */     newPrio.getArgument().add(this.cBuilder.createLiteral("low"));
/*  856 */     newPrio.getArgument().add(this.cBuilder.createLiteral("high"));
/*      */     
/*  858 */     if (this.generatingMultiThreadedCode) {
/*  859 */       addPrio = this.cBuilder.createFuncCall("mPriorities.push_back");
/*      */     } else {
/*  861 */       addPrio = this.cBuilder.createFuncCall("mPriorities.add");
/*      */     } 
/*  863 */     addPrio.getArgument().add(newPrio);
/*      */     
/*  865 */     return (CStm)addPrio;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConnector(CClass compoundTypeClass, CConstructor cons, Connector cnx) {
/*      */     CExpression cExpression1, cExpression2;
/*  872 */     String functionName, name = cnx.getName();
/*  873 */     ConnectorType ct = cnx.getType();
/*  874 */     String typeName = ct.getName();
/*  875 */     String scopeName = ct.getScope();
/*  876 */     if (scopeName != null) {
/*  877 */       typeName = scopeName + "::" + typeName;
/*      */     }
/*      */     
/*  880 */     String fieldTypeName = typeName;
/*      */     
/*  882 */     boolean isSubConnector = false;
/*  883 */     Port p = ct.getPort();
/*  884 */     if (p != null)
/*      */     {
/*      */       
/*  887 */       isSubConnector = isSubconnector(cnx);
/*      */     }
/*      */     
/*  890 */     EList<Expression> eList = cnx.getMultiplicitySpecification();
/*  891 */     int arraySize = eList.size();
/*      */ 
/*      */     
/*  894 */     for (int i = 0; i < arraySize; i++) {
/*  895 */       fieldTypeName = fieldTypeName + "*";
/*      */     }
/*  897 */     CData d = this.cBuilder.createData(name, fieldTypeName + "*", "public", null);
/*  898 */     compoundTypeClass.getContent().add(d);
/*      */ 
/*      */     
/*  901 */     for (int j = this.LastMaxIndice; j < arraySize; j++) {
/*  902 */       CData di = this.cBuilder.createData("BIPind" + (j + 1), "int", null, null);
/*  903 */       cons.getContent().add(di);
/*      */     } 
/*  905 */     if (this.LastMaxIndice < arraySize) this.LastMaxIndice = arraySize;
/*      */     
/*  907 */     CWhileStm lastWhile = null;
/*  908 */     CWhileStm firstWhile = null;
/*  909 */     CAssignStm firstAlloc = null;
/*  910 */     CAssignStm firstInit = null;
/*      */     
/*  912 */     CAssignStm alloc = null;
/*  913 */     CAssignStm incr = null;
/*  914 */     CSimpleName cSimpleName1 = this.cBuilder.createSimpleName(name);
/*  915 */     CSimpleName cSimpleName2 = this.cBuilder.createSimpleName(name);
/*  916 */     for (int k = 0; k < arraySize; k++) {
/*      */       
/*  918 */       CAssignStm init = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), this.cBuilder.createLiteral(0));
/*      */ 
/*      */       
/*  921 */       CData di = this.cBuilder.createData(name + "BIPdimSize" + (k + 1), "int", null, this.expGen.generateExpr(eList.get(k)));
/*      */       
/*  923 */       cons.getContent().add(di);
/*      */ 
/*      */       
/*  926 */       CWhileStm w = this.cFactory.createCWhileStm();
/*  927 */       w.setCondition(this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), "<", (CExpression)this.cBuilder.createSimpleName(name + "BIPdimSize" + (k + 1))));
/*      */ 
/*      */ 
/*      */       
/*  931 */       CCreator creator = this.cFactory.createCCreator();
/*  932 */       fieldTypeName = typeName;
/*  933 */       for (int e = k; e < arraySize; e++) {
/*  934 */         fieldTypeName = fieldTypeName + "*";
/*      */       }
/*  936 */       creator.setType(fieldTypeName);
/*  937 */       creator.setArrayAllocator(true);
/*  938 */       creator.getArgument().add(this.cBuilder.createSimpleName(name + "BIPdimSize" + (k + 1)));
/*      */ 
/*      */ 
/*      */       
/*  942 */       alloc = this.cBuilder.createAssign((CExpression)cSimpleName1, (CExpression)creator);
/*  943 */       cSimpleName1 = this.cBuilder.createSimpleName(name);
/*  944 */       cExpression2 = this.cBuilder.createIndexed((CExpression)cSimpleName2, (CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)));
/*      */ 
/*      */       
/*  947 */       for (int b = 0; b <= k; b++) {
/*  948 */         cExpression1 = this.cBuilder.createIndexed((CExpression)cSimpleName1, (CExpression)this.cBuilder.createSimpleName("BIPind" + (b + 1)));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  953 */       cons.getContent().add(di);
/*  954 */       if (lastWhile == null) {
/*  955 */         firstInit = init;
/*  956 */         firstAlloc = alloc;
/*  957 */         firstWhile = w;
/*      */       } else {
/*      */         
/*  960 */         lastWhile.getContent().add(init);
/*  961 */         lastWhile.getContent().add(alloc);
/*  962 */         lastWhile.getContent().add(w);
/*      */         
/*  964 */         lastWhile.getContent().add(incr);
/*      */       } 
/*      */ 
/*      */       
/*  968 */       incr = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */ 
/*      */       
/*  972 */       lastWhile = w;
/*      */     } 
/*      */     
/*  975 */     if (firstWhile != null) {
/*  976 */       cons.getContent().add(firstInit);
/*  977 */       cons.getContent().add(firstAlloc);
/*  978 */       cons.getContent().add(firstWhile);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  984 */     CFunction f = this.cFactory.createCFunction();
/*  985 */     compoundTypeClass.getContent().add(f);
/*  986 */     f.setName("get_" + name);
/*  987 */     fieldTypeName = typeName + "*";
/*      */     
/*  989 */     for (int m = 0; m < arraySize; m++) {
/*  990 */       fieldTypeName = fieldTypeName + "*";
/*      */     }
/*  992 */     f.setType(fieldTypeName);
/*  993 */     f.setQualifier("const");
/*  994 */     f.setSpecifier("inline");
/*  995 */     f.setVisibility("public");
/*  996 */     f.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(name)));
/*  997 */     f.setBodyInDecl(true);
/*      */ 
/*      */     
/* 1000 */     CCreator cnxCreate = this.cFactory.createCCreator();
/* 1001 */     cnxCreate.setType(typeName);
/* 1002 */     cnxCreate.getArgument().add(this.cBuilder.createLiteral("\"" + name + "\""));
/* 1003 */     cnxCreate.getArgument().add(this.cBuilder.createLiteral("this"));
/*      */     
/* 1005 */     if (isSubConnector) {
/* 1006 */       cnxCreate.getArgument().add(this.cBuilder.createLiteral("true"));
/*      */     } else {
/* 1008 */       cnxCreate.getArgument().add(this.cBuilder.createLiteral("false"));
/*      */     } 
/*      */     
/* 1011 */     for (Iterator<ActualPortParameter> iterator1 = cnx.getActualPort().iterator(); iterator1.hasNext(); ) {
/* 1012 */       ActualPortParameter app = iterator1.next();
/* 1013 */       cnxCreate.getArgument().add(actualPortParameterEval(app));
/*      */     } 
/*      */     
/* 1016 */     for (Iterator<Expression> iterator = cnx.getActualData().iterator(); iterator.hasNext(); ) {
/* 1017 */       Expression exp = iterator.next();
/* 1018 */       cnxCreate.getArgument().add(this.expGen.generateExpr(exp));
/*      */     } 
/*      */     
/* 1021 */     CAssignStm ass = this.cBuilder.createAssign(cExpression1, (CExpression)cnxCreate);
/*      */     
/* 1023 */     if (this.generatingMultiThreadedCode) {
/* 1024 */       functionName = "mConnectors.push_back";
/*      */     } else {
/* 1026 */       functionName = "mConnectors.add";
/*      */     } 
/* 1028 */     CFunctionCall fc = this.cBuilder.createFuncCall(functionName);
/* 1029 */     fc.getArgument().add(cExpression2);
/* 1030 */     if (lastWhile == null) {
/* 1031 */       cons.getContent().add(ass);
/* 1032 */       cons.getContent().add(fc);
/*      */     } else {
/* 1034 */       lastWhile.getContent().add(ass);
/* 1035 */       lastWhile.getContent().add(fc);
/* 1036 */       lastWhile.getContent().add(incr);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private CExpression actualPortParameterEval(ActualPortParameter app) {
/* 1042 */     if (app instanceof InnerPortReference) {
/* 1043 */       CExpression cExpression1; InnerPortReference ipr = (InnerPortReference)app;
/* 1044 */       PartElementReference per = ipr.getTargetInstance();
/* 1045 */       String compName = per.getTargetPart().getName();
/*      */       
/* 1047 */       String portName = per.getExportBinding().getTargetPort().getName();
/* 1048 */       CFunctionCall cFunctionCall1 = this.cBuilder.createFuncCall("get_" + compName);
/* 1049 */       for (Iterator<Expression> ind = per.getIndex().iterator(); ind.hasNext(); ) {
/* 1050 */         Expression expIndex = ind.next();
/* 1051 */         cExpression1 = this.cBuilder.createIndexed((CExpression)cFunctionCall1, this.expGen.generateExpr(expIndex));
/*      */       } 
/* 1053 */       CFunctionCall getPort = this.cBuilder.createFuncCall("get_" + portName);
/* 1054 */       CExpression point = this.cBuilder.createPointed(cExpression1, (CExpression)getPort);
/* 1055 */       return point;
/*      */     } 
/* 1057 */     ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/* 1058 */     CConditionalExpression ce = this.cFactory.createCConditionalExpression();
/* 1059 */     ce.setFalseCase(actualPortParameterEval(capp.getFalseCase()));
/* 1060 */     ce.setTrueCase(actualPortParameterEval(capp.getTrueCase()));
/* 1061 */     ce.setCondition(this.expGen.generateExpr(capp.getExpression()));
/* 1062 */     return (CExpression)ce;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CCreator addInstance(CClass compoundTypeClass, CConstructor cons, String name, String typeName, List<Expression> multiplicity, List actuals) {
/*      */     CExpression cExpression1, cExpression2;
/* 1074 */     String functionName, fieldTypeName = typeName;
/*      */     
/* 1076 */     int arraySize = multiplicity.size();
/*      */ 
/*      */     
/* 1079 */     for (int i = 0; i < arraySize; i++) {
/* 1080 */       fieldTypeName = fieldTypeName + "*";
/*      */     }
/*      */     
/* 1083 */     CData d = this.cBuilder.createData(name, fieldTypeName + "*", "public", null);
/* 1084 */     compoundTypeClass.getContent().add(d);
/*      */ 
/*      */     
/* 1087 */     for (int j = this.LastMaxIndice; j < arraySize; j++) {
/* 1088 */       CData di = this.cBuilder.createData("BIPind" + (j + 1), "int", null, null);
/* 1089 */       cons.getContent().add(di);
/*      */     } 
/* 1091 */     if (this.LastMaxIndice < arraySize) {
/* 1092 */       this.LastMaxIndice = arraySize;
/*      */     }
/*      */     
/* 1095 */     CWhileStm lastWhile = null;
/* 1096 */     CWhileStm firstWhile = null;
/* 1097 */     CAssignStm firstAlloc = null;
/* 1098 */     CAssignStm firstInit = null;
/*      */     
/* 1100 */     CAssignStm alloc = null;
/* 1101 */     CAssignStm incr = null;
/* 1102 */     CSimpleName cSimpleName1 = this.cBuilder.createSimpleName(name);
/* 1103 */     CSimpleName cSimpleName2 = this.cBuilder.createSimpleName(name);
/* 1104 */     for (int k = 0; k < arraySize; k++) {
/*      */       
/* 1106 */       CAssignStm init = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), this.cBuilder.createLiteral(0));
/*      */ 
/*      */       
/* 1109 */       CData di = this.cBuilder.createData(name + "BIPdimSize" + (k + 1), "int", null, this.expGen.generateExpr(multiplicity.get(k)));
/*      */       
/* 1111 */       compoundTypeClass.getContent().add(di);
/*      */ 
/*      */       
/* 1114 */       CWhileStm w = this.cFactory.createCWhileStm();
/* 1115 */       w.setCondition(this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), "<", (CExpression)this.cBuilder.createSimpleName(name + "BIPdimSize" + (k + 1))));
/*      */ 
/*      */ 
/*      */       
/* 1119 */       CCreator creator = this.cFactory.createCCreator();
/* 1120 */       fieldTypeName = typeName;
/* 1121 */       for (int e = k; e < arraySize; e++) {
/* 1122 */         fieldTypeName = fieldTypeName + "*";
/*      */       }
/* 1124 */       creator.setType(fieldTypeName);
/* 1125 */       creator.setArrayAllocator(true);
/* 1126 */       creator.getArgument().add(this.cBuilder.createSimpleName(name + "BIPdimSize" + (k + 1)));
/*      */ 
/*      */ 
/*      */       
/* 1130 */       alloc = this.cBuilder.createAssign((CExpression)cSimpleName1, (CExpression)creator);
/* 1131 */       cSimpleName1 = this.cBuilder.createSimpleName(name);
/* 1132 */       cExpression2 = this.cBuilder.createIndexed((CExpression)cSimpleName2, (CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)));
/*      */ 
/*      */       
/* 1135 */       for (int b = 0; b <= k; b++) {
/* 1136 */         cExpression1 = this.cBuilder.createIndexed((CExpression)cSimpleName1, (CExpression)this.cBuilder.createSimpleName("BIPind" + (b + 1)));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1141 */       cons.getContent().add(di);
/* 1142 */       if (lastWhile == null) {
/* 1143 */         firstInit = init;
/* 1144 */         firstAlloc = alloc;
/* 1145 */         firstWhile = w;
/*      */       } else {
/* 1147 */         lastWhile.getContent().add(init);
/* 1148 */         lastWhile.getContent().add(alloc);
/* 1149 */         lastWhile.getContent().add(w);
/*      */         
/* 1151 */         lastWhile.getContent().add(incr);
/*      */       } 
/*      */ 
/*      */       
/* 1155 */       incr = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIPind" + (k + 1)), "+", this.cBuilder.createLiteral(1)));
/*      */ 
/*      */ 
/*      */       
/* 1159 */       lastWhile = w;
/*      */     } 
/*      */     
/* 1162 */     if (firstWhile != null) {
/* 1163 */       cons.getContent().add(firstInit);
/* 1164 */       cons.getContent().add(firstAlloc);
/* 1165 */       cons.getContent().add(firstWhile);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1170 */     CFunction f = this.cFactory.createCFunction();
/* 1171 */     compoundTypeClass.getContent().add(f);
/* 1172 */     f.setName("get_" + name);
/* 1173 */     fieldTypeName = typeName + "*";
/* 1174 */     for (int m = 0; m < arraySize; m++) {
/* 1175 */       fieldTypeName = fieldTypeName + "*";
/*      */     }
/* 1177 */     f.setType(fieldTypeName);
/* 1178 */     f.setQualifier("const");
/* 1179 */     f.setSpecifier("inline");
/* 1180 */     f.setVisibility("public");
/* 1181 */     f.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(name)));
/* 1182 */     f.setBodyInDecl(true);
/*      */ 
/*      */     
/* 1185 */     CCreator instCreate = this.cFactory.createCCreator();
/* 1186 */     instCreate.setType(typeName);
/* 1187 */     instCreate.getArgument().add(this.cBuilder.createLiteral("\"" + name + "\""));
/* 1188 */     instCreate.getArgument().add(this.cBuilder.createLiteral("this"));
/*      */ 
/*      */     
/* 1191 */     for (Iterator<Expression> iActual = actuals.iterator(); iActual.hasNext(); ) {
/* 1192 */       Expression exp = iActual.next();
/* 1193 */       instCreate.getArgument().add(this.expGen.generateExpr(exp));
/*      */     } 
/*      */ 
/*      */     
/* 1197 */     CAssignStm ass = this.cBuilder.createAssign(cExpression1, (CExpression)instCreate);
/*      */     
/* 1199 */     cons.getContent().add(ass);
/*      */     
/* 1201 */     if (this.generatingMultiThreadedCode) {
/* 1202 */       functionName = "mComponents.push_back";
/*      */     } else {
/* 1204 */       functionName = "mComponents.add";
/*      */     } 
/* 1206 */     CFunctionCall fc = this.cBuilder.createFuncCall(functionName);
/* 1207 */     fc.getArgument().add(cExpression2);
/* 1208 */     if (lastWhile == null) {
/* 1209 */       cons.getContent().add(ass);
/* 1210 */       cons.getContent().add(fc);
/*      */     } else {
/* 1212 */       lastWhile.getContent().add(ass);
/* 1213 */       lastWhile.getContent().add(fc);
/* 1214 */       lastWhile.getContent().add(incr);
/*      */     } 
/* 1216 */     return instCreate;
/*      */   }
/*      */ 
/*      */   
/*      */   private CConstructor compoundTypeConstructor() {
/* 1221 */     CConstructor cons = this.cFactory.createCConstructor();
/* 1222 */     cons.setBodyInDecl(false);
/*      */     
/* 1224 */     String name = "name";
/* 1225 */     String holder = "holder";
/* 1226 */     cons.getArgument().add(this.cBuilder.createArgument(name, "const char*"));
/* 1227 */     cons.getArgument().add(this.cBuilder.createArgument(holder, "Compound*"));
/* 1228 */     cons.setVisibility("public");
/*      */ 
/*      */ 
/*      */     
/* 1232 */     CInitialization init = this.cFactory.createCInitialization();
/* 1233 */     cons.getInit().add(init);
/* 1234 */     init.setField("Compound");
/* 1235 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(name)));
/*      */     
/* 1237 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(holder)));
/*      */ 
/*      */     
/* 1240 */     return cons;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxCompoundTypeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */