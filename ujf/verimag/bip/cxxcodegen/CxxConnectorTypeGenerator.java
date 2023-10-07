/*     */ package ujf.verimag.bip.cxxcodegen;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.cgeneration.CConstruct;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CBlockStm;
/*     */ import ujf.verimag.bip.cmodel.CClass;
/*     */ import ujf.verimag.bip.cmodel.CConstructor;
/*     */ import ujf.verimag.bip.cmodel.CCreator;
/*     */ import ujf.verimag.bip.cmodel.CData;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFunction;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CIfStm;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
/*     */ import ujf.verimag.bip.cmodel.CModule;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CStm;
/*     */ import ujf.verimag.bip.cmodel.CText;
/*     */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*     */ 
/*     */ public class CxxConnectorTypeGenerator
/*     */ {
/*     */   protected CConstruct cBuilder;
/*  45 */   protected CmodelFactory cFactory = CmodelFactory.eINSTANCE;
/*     */   
/*     */   private CxxExpressionGenerator expGen;
/*     */   private CxxStatementGenerator stmGen;
/*     */   private static final int UP = 1;
/*     */   private static final int DOWN = 2;
/*     */   boolean bip_debug;
/*     */   
/*     */   public void setDebug(boolean debug) {
/*  54 */     this.bip_debug = debug;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CxxConnectorTypeGenerator(CConstruct builder, CxxExpressionGenerator expGen, CxxStatementGenerator stmGen) {
/*  61 */     this.cBuilder = builder;
/*  62 */     this.expGen = expGen;
/*  63 */     this.stmGen = stmGen;
/*     */   }
/*     */   
/*     */   private boolean findPort(PortParameter pp, List portRefs) {
/*  67 */     for (Iterator<PortParameterReference> i = portRefs.iterator(); i.hasNext(); ) {
/*  68 */       PortParameterReference ppr = i.next();
/*  69 */       if (ppr.getTarget() == pp) {
/*  70 */         return true;
/*     */       }
/*     */     } 
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   private boolean allPorts(PortExpression exp, List portRefs) {
/*  77 */     if (exp instanceof PortParameter) {
/*  78 */       PortParameter pp = (PortParameter)exp;
/*  79 */       return findPort(pp, portRefs);
/*     */     } 
/*  81 */     return true;
/*     */   }
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
/*     */   private void generateDebugFunctions(ConnectorType ct, CClass elementClass) {
/*  94 */     CFunction f = this.cFactory.createCFunction();
/*  95 */     elementClass.getContent().add(f);
/*  96 */     f.setName("getSubElement");
/*  97 */     f.setType("BipDebugElmt *");
/*  98 */     f.setVisibility("public");
/*  99 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/* 100 */     CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/* 101 */     f.getContent().add(cReturn);
/*     */     
/* 103 */     CFunction f2 = this.cFactory.createCFunction();
/* 104 */     elementClass.getContent().add(f2);
/* 105 */     f2.setName("getSubElementsNames");
/* 106 */     f2.setType("char **");
/* 107 */     f2.setVisibility("public");
/* 108 */     cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/* 109 */     f2.getContent().add(cReturn);
/*     */ 
/*     */     
/* 112 */     f = this.cFactory.createCFunction();
/* 113 */     elementClass.getContent().add(f);
/* 114 */     f.setName("getValue");
/* 115 */     f.setType("char *");
/* 116 */     f.setVisibility("public");
/* 117 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/* 118 */     cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/* 119 */     f.getContent().add(cReturn);
/*     */     
/* 121 */     f2 = this.cFactory.createCFunction();
/* 122 */     elementClass.getContent().add(f2);
/* 123 */     f2.setName("getValuedElementNames");
/* 124 */     f2.setType("char **");
/* 125 */     f2.setVisibility("public");
/* 126 */     cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/* 127 */     f2.getContent().add(cReturn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     f = this.cFactory.createCFunction();
/* 136 */     elementClass.getContent().add(f);
/* 137 */     f.setName("getParent");
/* 138 */     f.setType("BipDebugElmt *");
/* 139 */     f.setVisibility("public");
/* 140 */     f.getContent().add(this.cBuilder.createCCode("return dynamic_cast<BipDebugElmt*>( mHolder);"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateType(ConnectorType ct, CModule cmodule) {
/* 148 */     CClass connectorTypeClass = this.cBuilder.createClass(CxxNames.portTypeClassName(ct.getName()), "public", "Connector");
/*     */     
/* 150 */     cmodule.getContent().add(connectorTypeClass);
/* 151 */     if (this.bip_debug) {
/* 152 */       connectorTypeClass.getSuperClasses().add("BipDebugElmt");
/* 153 */       generateDebugFunctions(ct, connectorTypeClass);
/*     */     } 
/*     */ 
/*     */     
/* 157 */     InteractionSpecification fullInter = null;
/* 158 */     if (ct.getInteractionSpecification().size() == 1) {
/*     */ 
/*     */       
/* 161 */       InteractionSpecification inter = (InteractionSpecification)ct.getInteractionSpecification().get(0);
/* 162 */       if (allPorts(ct.getDefinition(), (List)inter.getInteraction().getPort())) {
/* 163 */         fullInter = inter;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 169 */     CConstructor cons = connectorTypeConstructor(ct);
/* 170 */     connectorTypeClass.getContent().add(cons);
/*     */     
/* 172 */     CInitialization init = (CInitialization)cons.getInit().get(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     init.getParameter().add(this.cBuilder.createInitParameter(null, this.cBuilder.createLiteral(ct.getPortParameter().size())));
/*     */ 
/*     */     
/* 181 */     for (Iterator<PortParameter> iterator2 = ct.getPortParameter().iterator(); iterator2.hasNext(); ) {
/* 182 */       PortParameter pp = iterator2.next();
/* 183 */       String portName = pp.getName();
/* 184 */       String paramName = "BIPParam" + portName;
/* 185 */       PortType pt = pp.getType();
/* 186 */       String scopeName = pt.getScope();
/* 187 */       String typeName = (scopeName != null) ? (scopeName + "::" + pt.getName()) : pt.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       cons.getArgument().add(this.cBuilder.createArgument(paramName, typeName + "*"));
/*     */ 
/*     */       
/* 195 */       init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(paramName)));
/*     */ 
/*     */ 
/*     */       
/* 199 */       CData d = this.cBuilder.createData(portName, typeName + "*", "public", null);
/* 200 */       connectorTypeClass.getContent().add(d);
/*     */       
/* 202 */       CInitialization portInit = this.cFactory.createCInitialization();
/* 203 */       cons.getInit().add(portInit);
/* 204 */       portInit.setField(portName);
/* 205 */       portInit.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(paramName)));
/*     */     } 
/*     */ 
/*     */     
/* 209 */     for (Iterator<DataParameter> iterator1 = ct.getDataParameter().iterator(); iterator1.hasNext(); ) {
/* 210 */       DataParameter dp = iterator1.next();
/* 211 */       String bipParamName = dp.getName();
/* 212 */       String paramName = "BIPParam" + bipParamName;
/* 213 */       String typeName = ((OpaqueElement)dp.getType()).getBody();
/*     */       
/* 215 */       cons.getArgument().add(this.cBuilder.createArgument(paramName, typeName));
/*     */       
/* 217 */       init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(paramName)));
/*     */ 
/*     */       
/* 220 */       CData d = this.cBuilder.createData(bipParamName, typeName, "public", null);
/* 221 */       connectorTypeClass.getContent().add(d);
/*     */       
/* 223 */       CInitialization portInit = this.cFactory.createCInitialization();
/* 224 */       cons.getInit().add(portInit);
/* 225 */       portInit.setField(bipParamName);
/* 226 */       portInit.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(paramName)));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     for (Iterator<Declaration> iterator = ct.getDeclaration().iterator(); iterator.hasNext(); ) {
/* 233 */       Declaration decl = iterator.next();
/* 234 */       if (decl instanceof OpaqueElement) {
/* 235 */         OpaqueElement op = (OpaqueElement)decl;
/* 236 */         CText cCode = this.cBuilder.createCCode(op.getBody());
/* 237 */         if (!op.isIsHeader()) {
/* 238 */           cCode.setInBodyFile(true);
/*     */         }
/* 240 */         connectorTypeClass.getContent().add(cCode);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 246 */     for (Iterator<Variable> i = ct.getVariable().iterator(); i.hasNext(); ) {
/* 247 */       Variable v = i.next();
/* 248 */       if (!v.isIsExternal()) {
/* 249 */         String varName = v.getName();
/* 250 */         String dataTypeName = ((OpaqueElement)v.getType()).getBody();
/*     */         
/* 252 */         CData d = this.cBuilder.createData(varName, dataTypeName, "public", null);
/* 253 */         connectorTypeClass.getContent().add(d);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 259 */     Port p = ct.getPort();
/* 260 */     if (p != null) {
/* 261 */       String portName = p.getName();
/* 262 */       PortType pType = p.getType();
/* 263 */       String scopeName = pType.getScope();
/* 264 */       String portTypeName = (scopeName != null) ? (scopeName + "::" + pType.getName()) : pType.getName();
/*     */ 
/*     */ 
/*     */       
/* 268 */       PortDefinition pd = ((DefinitionBinding)p.getBinding()).getDefinition();
/*     */       
/* 270 */       CData d = this.cBuilder.createData(portName, portTypeName + "*", "private", null);
/* 271 */       connectorTypeClass.getContent().add(d);
/*     */ 
/*     */       
/* 274 */       CCreator portCreate = this.cFactory.createCCreator();
/* 275 */       portCreate.setType(portTypeName);
/* 276 */       portCreate.getArgument().add(this.cBuilder.createLiteral("\"" + portName + "\""));
/* 277 */       portCreate.getArgument().add(this.cBuilder.createLiteral("this"));
/* 278 */       for (Iterator<Variable> iData = pd.getExposedVariable().iterator(); iData.hasNext(); ) {
/* 279 */         Variable v = iData.next();
/* 280 */         portCreate.getArgument().add(this.cBuilder.createSimpleName(v.getName()));
/*     */       } 
/* 282 */       CAssignStm ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName(portName), (CExpression)portCreate);
/*     */ 
/*     */       
/* 285 */       cons.getContent().add(ass);
/* 286 */       CFunctionCall fc = this.cBuilder.createFuncCall("setxPort");
/* 287 */       fc.getArgument().add(this.cBuilder.createSimpleName(portName));
/* 288 */       cons.getContent().add(fc);
/*     */       
/* 290 */       CFunction f = this.cFactory.createCFunction();
/* 291 */       connectorTypeClass.getContent().add(f);
/* 292 */       f.setName("get_" + portName);
/* 293 */       f.setType(portTypeName + "*");
/* 294 */       f.setQualifier("const");
/* 295 */       f.setSpecifier("inline");
/* 296 */       f.setVisibility("public");
/* 297 */       f.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName(portName)));
/* 298 */       f.setBodyInDecl(true);
/*     */     } 
/*     */     
/* 301 */     connectorTypeClass.getContent().add(generateTransfertFunction(ct, "up", 1, fullInter));
/* 302 */     connectorTypeClass.getContent().add(generateTransfertFunction(ct, "dn", 2, fullInter));
/*     */     
/* 304 */     connectorTypeClass.getContent().add(generateGuardFunction(ct, fullInter));
/* 305 */     connectorTypeClass.getContent().add(generateCompleteFunction(ct));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CFunction generateGuardFunction(ConnectorType ct, InteractionSpecification fullInter) {
/* 311 */     CFunction guard = this.cFactory.createCFunction();
/* 312 */     guard.setType("bool");
/* 313 */     guard.setName("guard");
/* 314 */     guard.setVisibility("public");
/*     */     
/* 316 */     if (!ct.getInteractionSpecification().isEmpty()) {
/*     */       
/* 318 */       if (fullInter != null) {
/* 319 */         Expression guardExp = fullInter.getGuard();
/* 320 */         if (guardExp == null) {
/* 321 */           guard.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("true")));
/*     */         } else {
/* 323 */           guard.getContent().add(this.cBuilder.createReturn(this.expGen.generateExpr(guardExp)));
/*     */         } 
/*     */       } else {
/* 326 */         guard.getContent().add(this.cBuilder.createData("inter", "Interaction *", null, (CExpression)this.cBuilder.createLiteral("NULL")));
/* 327 */         guard.getContent().add(this.cBuilder.createData("eq", "bool", null, (CExpression)this.cBuilder.createLiteral("false")));
/* 328 */         CIfStm interEqual = null;
/* 329 */         for (Iterator<InteractionSpecification> iInter = ct.getInteractionSpecification().iterator(); iInter.hasNext(); ) {
/* 330 */           CBlockStm cBlockStm; InteractionSpecification inter = iInter.next();
/* 331 */           EList eList = inter.getInteraction().getPort();
/* 332 */           if (!eList.isEmpty()) {
/* 333 */             CFunctionCall newInter = this.cBuilder.createFuncCall("Interaction::Create");
/* 334 */             guard.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("inter"), (CExpression)newInter));
/* 335 */             newInter.getArgument().add(this.cBuilder.createLiteral("this"));
/* 336 */             newInter.getArgument().add(this.cBuilder.createLiteral(eList.size()));
/*     */             
/* 338 */             for (Iterator<PortParameterReference> iPortRef = eList.iterator(); iPortRef.hasNext(); ) {
/* 339 */               PortParameterReference ref = iPortRef.next();
/* 340 */               newInter.getArgument().add(this.cBuilder.createSimpleName(ref.getTarget().getName()));
/*     */             } 
/* 342 */             interEqual = this.cFactory.createCIfStm();
/* 343 */             CFunctionCall callEqual = this.cBuilder.createFuncCall("isSubset");
/* 344 */             interEqual.setCondition(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("inter"), (CExpression)callEqual));
/* 345 */             callEqual.getArgument().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("this"), (CExpression)this.cBuilder.createFuncCall("feasibleInter")));
/*     */             
/* 347 */             callEqual.getArgument().add(this.cBuilder.createSimpleName("eq"));
/*     */           } 
/* 349 */           CStm stm = null;
/* 350 */           CReturn ret = this.cFactory.createCReturn();
/* 351 */           ret.setReturnExpression((CExpression)this.cBuilder.createLiteral("true"));
/* 352 */           Expression guardCond = inter.getGuard();
/* 353 */           if (guardCond != null) {
/* 354 */             CBlockStm block = this.cFactory.createCBlockStm();
/* 355 */             CIfStm cIfStm = this.cBuilder.createIf(this.expGen.generateExpr(guardCond), (CStm)block, null);
/* 356 */             if (eList != null && eList.size() > 0)
/*     */             {
/*     */ 
/*     */               
/* 360 */               block.getContent().add(this.cBuilder.createCCode("if (!eq) {\n          this->setFeasibleInter(inter);\n        } \n"));
/*     */             }
/*     */ 
/*     */             
/* 364 */             block.getContent().add(ret);
/*     */           } else {
/* 366 */             CBlockStm block = this.cFactory.createCBlockStm();
/* 367 */             block.getContent().add(this.cBuilder.createCCode("if (!eq) {\n        this->setFeasibleInter(inter);\n      }\n"));
/*     */ 
/*     */             
/* 370 */             block.getContent().add(ret);
/* 371 */             cBlockStm = block;
/*     */           } 
/* 373 */           if (interEqual != null) {
/* 374 */             interEqual.setIfCase((CStm)cBlockStm);
/* 375 */             guard.getContent().add(interEqual); continue;
/*     */           } 
/* 377 */           guard.getContent().add(ret);
/*     */         } 
/*     */ 
/*     */         
/* 381 */         CReturn defaultReturn = this.cFactory.createCReturn();
/* 382 */         defaultReturn.setReturnExpression((CExpression)this.cBuilder.createLiteral("false"));
/* 383 */         guard.getContent().add(defaultReturn);
/*     */       } 
/*     */     } else {
/* 386 */       guard.setBodyInDecl(true);
/* 387 */       guard.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("true")));
/*     */     } 
/*     */ 
/*     */     
/* 391 */     return guard;
/*     */   }
/*     */ 
/*     */   
/*     */   private CFunction generateCompleteFunction(ConnectorType ct) {
/* 396 */     CFunction complete = this.cFactory.createCFunction();
/* 397 */     complete.setType("bool");
/* 398 */     complete.setName("complete");
/* 399 */     complete.setVisibility("public");
/* 400 */     complete.setBodyInDecl(true);
/* 401 */     complete.setQualifier("const");
/*     */     
/* 403 */     PortExpression pe = ct.getDefinition();
/* 404 */     complete.getContent().add(this.cBuilder.createReturn(generateCompleteExp(pe)));
/*     */     
/* 406 */     return complete;
/*     */   }
/*     */   
/*     */   private CExpression generateCompleteExp(PortExpression pe) {
/* 410 */     CExpression resExp = null;
/*     */     
/* 412 */     if (pe instanceof ACFusion) {
/*     */ 
/*     */ 
/*     */       
/* 416 */       ACFusion fusion = (ACFusion)pe;
/* 417 */       boolean findTrigger = false;
/* 418 */       for (Iterator<PortExpression> iterator1 = fusion.getOperand().iterator(); iterator1.hasNext() && !findTrigger; ) {
/* 419 */         PortExpression operand = iterator1.next();
/* 420 */         if (operand instanceof ACTyping) {
/* 421 */           ACTyping trig = (ACTyping)operand;
/* 422 */           if (trig.getType() == ACTypingKind.TRIG) {
/* 423 */             findTrigger = true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 429 */       for (Iterator<PortExpression> i = fusion.getOperand().iterator(); i.hasNext(); ) {
/* 430 */         PortExpression operand = i.next();
/* 431 */         if (!findTrigger) {
/* 432 */           CExpression operExp = generateCompleteExp(operand);
/* 433 */           if (resExp != null) {
/* 434 */             resExp = this.cBuilder.createOperation(resExp, " && ", operExp); continue;
/*     */           } 
/* 436 */           resExp = operExp; continue;
/*     */         } 
/* 438 */         if (operand instanceof ACTyping) {
/* 439 */           ACTyping trig = (ACTyping)operand;
/* 440 */           if (trig.getType() == ACTypingKind.TRIG) {
/* 441 */             CExpression operExp = generateCompleteExp((PortExpression)trig.getOperand());
/* 442 */             if (resExp != null) {
/* 443 */               resExp = this.cBuilder.createOperation(resExp, " || ", operExp); continue;
/*     */             } 
/* 445 */             resExp = operExp;
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 453 */     else if (pe instanceof ACTyping) {
/* 454 */       ACTyping trig = (ACTyping)pe;
/* 455 */       resExp = generateCompleteExp((PortExpression)trig.getOperand());
/*     */     }
/* 457 */     else if (pe instanceof PortParameterReference) {
/* 458 */       PortParameterReference ppr = (PortParameterReference)pe;
/*     */       
/* 460 */       resExp = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(ppr.getTarget().getName()), (CExpression)this.cBuilder.createFuncCall("synced"));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 466 */     return resExp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CFunction generateTransfertFunction(ConnectorType ct, String name, int dir, InteractionSpecification fullInter) {
/* 472 */     CFunction fct = this.cFactory.createCFunction();
/* 473 */     fct.setType("void");
/* 474 */     fct.setName(name);
/* 475 */     fct.setVisibility("public");
/* 476 */     if (!ct.getInteractionSpecification().isEmpty()) {
/* 477 */       fct.getContent().add(this.cBuilder.createData("inter", "Interaction *", null, (CExpression)this.cBuilder.createLiteral("NULL")));
/*     */ 
/*     */       
/* 480 */       if (fullInter != null) {
/*     */         Action act;
/* 482 */         if (dir == 1) { act = fullInter.getUpAction(); }
/* 483 */         else { act = fullInter.getDownAction(); }
/* 484 */          CStm stm = this.stmGen.generateStm(act);
/* 485 */         if (stm != null) fct.getContent().add(stm);
/*     */       
/*     */       } else {
/*     */         
/* 489 */         CIfStm interEqual = null;
/* 490 */         for (Iterator<InteractionSpecification> iInter = ct.getInteractionSpecification().iterator(); iInter.hasNext(); ) {
/* 491 */           Action act; InteractionSpecification inter = iInter.next();
/*     */           
/* 493 */           if (dir == 1) { act = inter.getUpAction(); }
/* 494 */           else { act = inter.getDownAction(); }
/* 495 */            CStm cDoAction = this.stmGen.generateStm(act);
/* 496 */           if (cDoAction != null) {
/* 497 */             CBlockStm cBlockStm; EList eList = inter.getInteraction().getPort();
/* 498 */             if (!eList.isEmpty()) {
/* 499 */               CFunctionCall newInter = this.cBuilder.createFuncCall("Interaction::Create");
/* 500 */               fct.getContent().add(this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("inter"), (CExpression)newInter));
/* 501 */               newInter.getArgument().add(this.cBuilder.createLiteral("this"));
/* 502 */               newInter.getArgument().add(this.cBuilder.createLiteral(eList.size()));
/*     */               
/* 504 */               for (Iterator<PortParameterReference> iPortRef = eList.iterator(); iPortRef.hasNext(); ) {
/* 505 */                 PortParameterReference ref = iPortRef.next();
/* 506 */                 newInter.getArgument().add(this.cBuilder.createSimpleName(ref.getTarget().getName()));
/*     */               } 
/* 508 */               interEqual = this.cFactory.createCIfStm();
/* 509 */               CFunctionCall callEqual = this.cBuilder.createFuncCall("equal");
/* 510 */               interEqual.setCondition(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("inter"), (CExpression)callEqual));
/* 511 */               callEqual.getArgument().add(this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("this"), (CExpression)this.cBuilder.createFuncCall("feasibleInter")));
/*     */             } 
/*     */             
/* 514 */             if (interEqual == null) {
/*     */               
/* 516 */               fct.getContent().add(cDoAction);
/*     */               
/*     */               continue;
/*     */             } 
/* 520 */             if (!(cDoAction instanceof CBlockStm)) {
/* 521 */               CBlockStm blockInterEqual = this.cFactory.createCBlockStm();
/* 522 */               blockInterEqual.getContent().add(cDoAction);
/* 523 */               cBlockStm = blockInterEqual;
/*     */             } 
/* 525 */             cBlockStm.getContent().add(this.cFactory.createCReturn());
/* 526 */             interEqual.setIfCase((CStm)cBlockStm);
/* 527 */             fct.getContent().add(interEqual);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 536 */     return fct;
/*     */   }
/*     */ 
/*     */   
/*     */   private CConstructor connectorTypeConstructor(ConnectorType ct) {
/* 541 */     CConstructor cons = this.cFactory.createCConstructor();
/* 542 */     cons.setBodyInDecl(true);
/* 543 */     cons.setVisibility("public");
/*     */     
/* 545 */     String name = "name";
/* 546 */     String holder = "holder";
/* 547 */     String isHier = "isSubconn";
/*     */     
/* 549 */     CInitialization init = this.cFactory.createCInitialization();
/* 550 */     cons.getInit().add(init);
/* 551 */     init.setField("Connector");
/*     */     
/* 553 */     cons.getArgument().add(this.cBuilder.createArgument(name, "const char*"));
/* 554 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(name)));
/*     */     
/* 556 */     cons.getArgument().add(this.cBuilder.createArgument(holder, "Compound*"));
/* 557 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(holder)));
/*     */     
/* 559 */     cons.getArgument().add(this.cBuilder.createArgument(isHier, "bool"));
/* 560 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(isHier)));
/*     */ 
/*     */ 
/*     */     
/* 564 */     return cons;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxConnectorTypeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */