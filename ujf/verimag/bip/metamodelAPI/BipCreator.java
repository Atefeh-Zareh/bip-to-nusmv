/*      */ package ujf.verimag.bip.metamodelAPI;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*      */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*      */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
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
/*      */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
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
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.Priorities.PrioritiesFactory;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*      */ import ujf.verimag.bip.Extra.Time.TimeFactory;
/*      */ import ujf.verimag.bip.Extra.Time.TimedVariable;
/*      */ 
/*      */ public abstract class BipCreator
/*      */ {
/*   78 */   protected static BehaviorsFactory behavFactory = BehaviorsFactory.eINSTANCE;
/*   79 */   protected static InteractionsFactory interFactory = InteractionsFactory.eINSTANCE;
/*   80 */   protected static ModulesFactory modulesFactory = ModulesFactory.eINSTANCE;
/*   81 */   protected static TimeFactory timeFactory = TimeFactory.eINSTANCE;
/*   82 */   protected static ExpressionsFactory expressionsFactory = ExpressionsFactory.eINSTANCE;
/*   83 */   protected static ActionsFactory actionsFactory = ActionsFactory.eINSTANCE;
/*   84 */   protected static PortExpressionsFactory portExpressionsFactory = PortExpressionsFactory.eINSTANCE;
/*   85 */   protected static PrioritiesFactory prioritiesFactory = PrioritiesFactory.eINSTANCE;
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
/*      */   public static ConnectorType createConnectorType(Module module, String name) {
/*   99 */     ConnectorType ct = interFactory.createConnectorType();
/*  100 */     ct.setModule(module);
/*  101 */     ct.setName(name);
/*  102 */     module.getBipType().add(ct);
/*  103 */     ct.setPort(null);
/*      */     
/*  105 */     return ct;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType createRDVConnectorType(Module module, String name, List<PortType> port_types) {
/*  116 */     ConnectorType ct = createConnectorType(module, name, port_types, null);
/*      */     
/*  118 */     return ct;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ConnectorType createConnectorType(Module module, String name, List<PortType> synchrons, List<PortType> triggers) {
/*  129 */     ConnectorType ct = createConnectorType(module, name);
/*      */     
/*  131 */     List<PortParameter> syncpplist = new ArrayList<PortParameter>();
/*  132 */     int i = 1;
/*  133 */     if (synchrons != null) {
/*  134 */       for (PortType pt : synchrons) {
/*  135 */         PortParameter pp = createPortParameter("p" + i, pt, ct);
/*  136 */         syncpplist.add(pp);
/*  137 */         i++;
/*      */       } 
/*      */     }
/*      */     
/*  141 */     List<PortParameter> triggpplist = new ArrayList<PortParameter>();
/*  142 */     if (triggers != null) {
/*  143 */       for (PortType pt : triggers) {
/*  144 */         PortParameter pp = createPortParameter("p" + i, pt, ct);
/*  145 */         triggpplist.add(pp);
/*  146 */         i++;
/*      */       } 
/*      */     }
/*      */     
/*  150 */     ACFusion define = createPortExpressionFusion(triggpplist, syncpplist);
/*      */     
/*  152 */     ct.setDefinition((PortExpression)define);
/*  153 */     return ct;
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
/*      */   public static Connector createConnector(ConnectorType connectorType, CompoundType component, String name) {
/*  167 */     assert connectorType.getPortParameter().isEmpty();
/*      */ 
/*      */     
/*  170 */     assert connectorType.getDataParameter().isEmpty();
/*      */     
/*  172 */     Connector c = interFactory.createConnector();
/*  173 */     c.setName(name);
/*  174 */     c.setCompoundType(component);
/*  175 */     c.setType(connectorType);
/*  176 */     component.getConnector().add(c);
/*  177 */     return c;
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
/*      */   public static Connector createConnector(ConnectorType connectorType, CompoundType component, String name, ActualPortParameter[] actualPortParameters) {
/*  192 */     assert connectorType.getPortParameter().size() == actualPortParameters.length;
/*      */ 
/*      */     
/*  195 */     assert connectorType.getDataParameter().isEmpty();
/*      */     
/*  197 */     Connector c = interFactory.createConnector();
/*  198 */     c.setName(name);
/*  199 */     c.setType(connectorType);
/*  200 */     c.setCompoundType(component);
/*  201 */     c.setType(connectorType);
/*  202 */     for (ActualPortParameter app : actualPortParameters) {
/*  203 */       c.getActualPort().add(app);
/*      */     }
/*      */     
/*  206 */     component.getConnector().add(c);
/*      */     
/*  208 */     return c;
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
/*      */   public static InnerPortReference createInnerPortReference(Port targetPort, Part value, Connector connector) {
/*  221 */     InnerPortReference ipr = createInnerPortReferenceUnbounded(targetPort, value);
/*  222 */     connector.getActualPort().add(ipr);
/*  223 */     return ipr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InnerPortReference createInnerPortReferenceUnbounded(Port targetPort, Part value) {
/*  234 */     InnerPortReference ipr = interFactory.createInnerPortReference();
/*      */     
/*  236 */     ipr.setTargetPort(targetPort);
/*  237 */     PartElementReference PE = interFactory.createPartElementReference();
/*  238 */     PE.setTargetPart(value);
/*  239 */     ipr.setTargetInstance(PE);
/*  240 */     return ipr;
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
/*      */   public static Connector copyConnector(Connector connector, CompoundType component, String name) {
/*  253 */     Connector c = (Connector)EcoreUtil.copy((EObject)connector);
/*  254 */     c.setName(name);
/*  255 */     c.setCompoundType(component);
/*  256 */     component.getConnector().add(c);
/*  257 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static OpaqueElement createOpaqueElementFromCCode(String ccode) {
/*  266 */     return createOpaqueElementFromCCode(ccode, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static OpaqueElement createOpaqueElementFromCCode(String ccode, boolean isHeader) {
/*  276 */     OpaqueElement dt = modulesFactory.createOpaqueElement();
/*  277 */     dt.setBody(ccode);
/*  278 */     dt.setIsHeader(isHeader);
/*  279 */     return dt;
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
/*      */   public static PortParameter createPortParameter(String name, PortType portType, ConnectorType connectorType) {
/*  291 */     PortParameter pp = interFactory.createPortParameter();
/*  292 */     pp.setName(name);
/*  293 */     pp.setType(portType);
/*  294 */     pp.setConnectorType(connectorType);
/*  295 */     connectorType.getPortParameter().add(pp);
/*  296 */     return pp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortExpression createPortExpressionFusion(PortParameter singleport) {
/*  307 */     List<PortParameter> param = new ArrayList<PortParameter>();
/*  308 */     param.add(singleport);
/*      */ 
/*      */     
/*  311 */     return (PortExpression)createPortExpressionFusion(null, param);
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
/*      */   public static ACFusion createPortExpressionFusion(List<PortParameter> triggers, List<PortParameter> synchrons) {
/*  326 */     assert synchrons != null && synchrons.size() != 0;
/*      */ 
/*      */     
/*  329 */     ACFusion afu = portExpressionsFactory.createACFusion();
/*  330 */     if (synchrons != null) {
/*  331 */       for (PortParameter pp : synchrons) {
/*  332 */         PortParameterReference ppr = interFactory.createPortParameterReference();
/*  333 */         ppr.setTarget(pp);
/*  334 */         afu.getOperand().add(ppr);
/*      */       } 
/*      */     }
/*      */     
/*  338 */     if (triggers != null) {
/*  339 */       for (PortParameter pp : triggers) {
/*  340 */         PortParameterReference ppr = interFactory.createPortParameterReference();
/*  341 */         ppr.setTarget(pp);
/*  342 */         ACTyping actk = portExpressionsFactory.createACTyping();
/*  343 */         actk.setType(ACTypingKind.TRIG);
/*  344 */         actk.setOperand((ACExpression)ppr);
/*      */         
/*  346 */         afu.getOperand().add(actk);
/*      */       } 
/*      */     }
/*      */     
/*  350 */     return afu;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction createAssignmentAction(DataReference lo, Expression ro) {
/*  361 */     AssignmentAction aa = actionsFactory.createAssignmentAction();
/*  362 */     aa.setAssignedTarget(lo);
/*  363 */     aa.setAssignedValue(ro);
/*      */     
/*  365 */     return aa;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignmentAction createAssignmentAction(DataReference lo, Expression ro, String assignType) {
/*  376 */     AssignmentAction aa = actionsFactory.createAssignmentAction();
/*  377 */     aa.setAssignedTarget(lo);
/*  378 */     aa.setAssignedValue(ro);
/*  379 */     AssignType type = assignTypeC2BIP(assignType);
/*  380 */     aa.setType(type);
/*  381 */     return aa;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompositeAction createCompositeAction() {
/*  389 */     CompositeAction ca = actionsFactory.createCompositeAction();
/*  390 */     return ca;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompositeAction createCompositeAction(Action action) {
/*  399 */     CompositeAction ca = createCompositeAction();
/*  400 */     ca.getContent().add(action);
/*  401 */     return ca;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static VariableReference createVariableReference(Variable target) {
/*  409 */     assert target != null;
/*  410 */     VariableReference vref = expressionsFactory.createVariableReference();
/*  411 */     vref.setTargetVariable(target);
/*      */     
/*  413 */     return vref;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static IntegerLiteral createIntegerLiteral(Integer value) {
/*  422 */     IntegerLiteral int_lit = expressionsFactory.createIntegerLiteral();
/*  423 */     int_lit.setIValue(value.intValue());
/*  424 */     return int_lit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static RealLiteral createRealLiteral(String value) {
/*  433 */     RealLiteral int_lit = expressionsFactory.createRealLiteral();
/*  434 */     int_lit.setRValue(value);
/*  435 */     return int_lit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static StringLiteral createStringLiteral(String value) {
/*  444 */     StringLiteral str_lit = expressionsFactory.createStringLiteral();
/*  445 */     str_lit.setSValue(value);
/*  446 */     return str_lit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PointerLiteral createPointerLiteral() {
/*  454 */     PointerLiteral p_lit = expressionsFactory.createPointerLiteral();
/*  455 */     return p_lit;
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
/*      */   public static BinaryExpression createBinaryExpression(Expression leftoperand, Expression rightoperand, BinaryOperator operator) {
/*  467 */     BinaryExpression bexpr = expressionsFactory.createBinaryExpression();
/*  468 */     bexpr.setLeftOperand(leftoperand);
/*  469 */     bexpr.setRightOperand(rightoperand);
/*  470 */     bexpr.setOperator(operator);
/*  471 */     return bexpr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BinaryOperator comparisonC2BIP(String cCompOperator) {
/*  480 */     BinaryOperator bop = null;
/*      */     
/*  482 */     if (cCompOperator.equals("==")) {
/*  483 */       bop = BinaryOperator.EQUALITY;
/*  484 */     } else if (cCompOperator.equals("!=")) {
/*  485 */       bop = BinaryOperator.INEQUALITY;
/*  486 */     } else if (cCompOperator.equals(">=")) {
/*  487 */       bop = BinaryOperator.GREATER_THAN_OR_EQUAL;
/*  488 */     } else if (cCompOperator.equals("<=")) {
/*  489 */       bop = BinaryOperator.LESS_THAN_OR_EQUAL;
/*  490 */     } else if (cCompOperator.equals(">")) {
/*  491 */       bop = BinaryOperator.GREATER_THAN;
/*  492 */     } else if (cCompOperator.equals("<")) {
/*  493 */       bop = BinaryOperator.LESS_THAN;
/*  494 */     } else if (cCompOperator.equals("+")) {
/*  495 */       bop = BinaryOperator.ADDITION;
/*  496 */     } else if (cCompOperator.equals("-")) {
/*  497 */       bop = BinaryOperator.SUBSTRACTION;
/*  498 */     } else if (cCompOperator.equals("*")) {
/*  499 */       bop = BinaryOperator.MULTIPLICATION;
/*  500 */     } else if (cCompOperator.equals("/")) {
/*  501 */       bop = BinaryOperator.DIVISION;
/*  502 */     } else if (cCompOperator.equals("%")) {
/*  503 */       bop = BinaryOperator.MODULUS;
/*  504 */     } else if (cCompOperator.equals("&&")) {
/*  505 */       bop = BinaryOperator.LOGICAL_AND;
/*  506 */     } else if (cCompOperator.equals("||")) {
/*  507 */       bop = BinaryOperator.LOGICAL_OR;
/*  508 */     } else if (cCompOperator.equals("&")) {
/*  509 */       bop = BinaryOperator.BITWISE_AND;
/*  510 */     } else if (cCompOperator.equals("|")) {
/*  511 */       bop = BinaryOperator.BITWISE_OR;
/*  512 */     } else if (cCompOperator.equals("^")) {
/*  513 */       bop = BinaryOperator.BITWISE_XOR;
/*  514 */     } else if (cCompOperator.equals("<<")) {
/*  515 */       bop = BinaryOperator.LEFT_SHIFT;
/*  516 */     } else if (cCompOperator.equals(">>")) {
/*  517 */       bop = BinaryOperator.RIGHT_SHIFT;
/*      */     } 
/*  519 */     return bop;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static UnaryOperator unaryC2BIP(String cUnaryOperator) {
/*  528 */     UnaryOperator uop = null;
/*      */     
/*  530 */     if (cUnaryOperator.equals("!")) {
/*  531 */       uop = UnaryOperator.LOGICAL_NOT;
/*      */     }
/*  533 */     else if (cUnaryOperator.equals("-")) {
/*  534 */       uop = UnaryOperator.NEGATIVE;
/*      */     }
/*  536 */     else if (cUnaryOperator.equals("+")) {
/*  537 */       uop = UnaryOperator.POSITIVE;
/*      */     }
/*  539 */     else if (cUnaryOperator.equals("++")) {
/*  540 */       uop = UnaryOperator.INCREMENT;
/*      */     }
/*  542 */     else if (cUnaryOperator.equals("--")) {
/*  543 */       uop = UnaryOperator.DECREMENT;
/*      */     }
/*  545 */     else if (cUnaryOperator.equals("&")) {
/*  546 */       uop = UnaryOperator.REFERENCE;
/*      */     }
/*  548 */     else if (cUnaryOperator.equals("*")) {
/*  549 */       uop = UnaryOperator.DEREFERENCE;
/*      */     }
/*  551 */     else if (cUnaryOperator.equals("~")) {
/*  552 */       uop = UnaryOperator.BITWISE_NOT;
/*      */     } 
/*  554 */     return uop;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AssignType assignTypeC2BIP(String cAssignOperator) {
/*  564 */     AssignType type = null;
/*      */     
/*  566 */     if (cAssignOperator.equals("+=")) {
/*  567 */       type = AssignType.PLUS_ASSIGN;
/*      */     }
/*  569 */     else if (cAssignOperator.equals("-=")) {
/*  570 */       type = AssignType.MINUS_ASSIGN;
/*      */     }
/*  572 */     else if (cAssignOperator.equals("*=")) {
/*  573 */       type = AssignType.MULT_ASSIGN;
/*      */     }
/*  575 */     else if (cAssignOperator.equals("/=")) {
/*  576 */       type = AssignType.DIV_ASSIGN;
/*      */     }
/*  578 */     else if (cAssignOperator.equals("%=")) {
/*  579 */       type = AssignType.MOD_ASSIGN;
/*      */     } 
/*      */     
/*  582 */     return type;
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
/*      */   public static BinaryExpression createBinaryExpression(Expression leftoperand, Expression rightoperand, String operator) {
/*  594 */     BinaryOperator bop = comparisonC2BIP(operator);
/*  595 */     assert bop != null;
/*      */     
/*  597 */     return createBinaryExpression(leftoperand, rightoperand, bop);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static UnaryExpression createUnaryExpression(Expression operand, String operator) {
/*  607 */     UnaryOperator uop = unaryC2BIP(operator);
/*  608 */     assert uop != null;
/*      */     
/*  610 */     return createUnaryExpression(operand, uop);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static UnaryExpression createUnaryExpression(Expression operand, UnaryOperator operator) {
/*  620 */     UnaryExpression uexpr = expressionsFactory.createUnaryExpression();
/*  621 */     uexpr.setOperand(operand);
/*  622 */     uexpr.setOperator(operator);
/*  623 */     return uexpr;
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
/*      */   public static PortDefinition createPortDefinition(PortType pt, String portName, AtomType inAtom) {
/*  636 */     PortDefinition pd = behavFactory.createPortDefinition();
/*  637 */     pd.setName(portName);
/*  638 */     pd.setType(pt);
/*  639 */     inAtom.getPortDefinition().add(pd);
/*  640 */     return pd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortDefinition createPortDefinitionAndExport(PortType pt, String portName, AtomType inAtom) {
/*  651 */     PortDefinition pd = createPortDefinition(pt, portName, inAtom);
/*  652 */     exportPortDefinition(pd);
/*      */     
/*  654 */     return pd;
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
/*      */   public static PortDefinition createPortDefinition(PortType pt, String portName, List<Variable> variables, AtomType inAtom) {
/*  667 */     PortDefinition pd = createPortDefinition(pt, portName, inAtom);
/*  668 */     pd.getExposedVariable().addAll(variables);
/*  669 */     return pd;
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
/*      */   public static PortDefinition createPortDefinitionAndExport(PortType pt, String portName, List<Variable> variables, AtomType inAtom) {
/*  682 */     PortDefinition pd = createPortDefinition(pt, portName, inAtom);
/*  683 */     exportPortDefinition(pd);
/*      */     
/*  685 */     pd.getExposedVariable().addAll(variables);
/*  686 */     return pd;
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
/*      */   public static PortDefinition createPortDefinition(PortType pt, String portName, Variable[] variables, AtomType inAtom) {
/*  699 */     PortDefinition pd = createPortDefinition(pt, portName, inAtom);
/*  700 */     for (Variable v : variables) {
/*  701 */       pd.getExposedVariable().add(v);
/*      */     }
/*  703 */     return pd;
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
/*      */   public static PortDefinition createPortDefinitionAndExport(PortType pt, String portName, Variable[] variables, AtomType inAtom) {
/*  716 */     PortDefinition pd = createPortDefinition(pt, portName, inAtom);
/*  717 */     exportPortDefinition(pd);
/*      */     
/*  719 */     for (Variable v : variables) {
/*  720 */       pd.getExposedVariable().add(v);
/*      */     }
/*  722 */     return pd;
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
/*      */   public static PortDefinition copyPortDefinition(PortDefinition portDef, String portName, AtomType inAtom) {
/*  735 */     PortDefinition pdCopy = (PortDefinition)EcoreUtil.copy((EObject)portDef);
/*  736 */     pdCopy.setName(portName);
/*  737 */     inAtom.getPortDefinition().add(pdCopy);
/*  738 */     return pdCopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port exportPortDefinition(PortDefinition pd, String exportName) {
/*  748 */     assert exportName != null;
/*      */     
/*  750 */     Port p = behavFactory.createPort();
/*  751 */     DefinitionBinding bd = behavFactory.createDefinitionBinding();
/*  752 */     bd.setDefinition(pd);
/*  753 */     p.setName(pd.getName());
/*      */     
/*  755 */     p.setType(pd.getType());
/*  756 */     p.setBinding((Binding)bd);
/*  757 */     pd.getAtomType().getPort().add(p);
/*  758 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port exportPortDefinition(PortDefinition pd) {
/*  767 */     return exportPortDefinition(pd, pd.getName());
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
/*      */   public static Port exportInternalPort(ComponentType ct, Part p, Port internalPort, String exportName) {
/*  779 */     String name = exportName;
/*  780 */     if (name == null) name = internalPort.getName(); 
/*  781 */     for (Port port1 : ct.getPort()) {
/*  782 */       if (port1.getName().equals(name)) return port1; 
/*      */     } 
/*  784 */     Port port = behavFactory.createPort();
/*  785 */     ct.getPort().add(port);
/*  786 */     ExportBinding eb = interFactory.createExportBinding();
/*  787 */     PartElementReference per = interFactory.createPartElementReference();
/*  788 */     per.setTargetPart(p);
/*  789 */     eb.setTargetInstance(per);
/*  790 */     eb.setTargetPort(internalPort);
/*  791 */     port.setBinding((Binding)eb);
/*  792 */     port.setName(name);
/*  793 */     port.setType(internalPort.getType());
/*  794 */     return port;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Port getPort(ComponentType ct, String portName) {
/*  804 */     for (Port p : ct.getPort()) {
/*  805 */       if (p.getName().equals(portName)) return p; 
/*      */     } 
/*  807 */     return null;
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
/*      */   public static Transition createUnclosedTransition(PortDefinition trigger, Expression condition, State source, AtomType inAtom) {
/*  822 */     Transition trans = behavFactory.createTransition();
/*      */     
/*  824 */     trans.getOrigin().add(source);
/*  825 */     PortDefinitionReference pr = behavFactory.createPortDefinitionReference();
/*  826 */     pr.setTarget(trigger);
/*      */ 
/*      */ 
/*      */     
/*  830 */     trans.setTrigger((PortExpression)pr);
/*      */     
/*  832 */     if (condition != null) {
/*  833 */       trans.setGuard(condition);
/*      */     }
/*  835 */     PetriNet behav = (PetriNet)inAtom.getBehavior();
/*  836 */     behav.getTransition().add(trans);
/*  837 */     return trans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void closeUnclosedTransition(Transition trans, State destination) {
/*  847 */     assert trans.getOrigin().size() == 1;
/*      */     
/*  849 */     State source = (State)trans.getOrigin().get(0);
/*  850 */     trans.setName(source.getName() + "2" + destination.getName());
/*  851 */     trans.getDestination().add(destination);
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
/*      */   public static Transition createTransition(PortDefinition trigger, Expression condition, State source, State destination, AtomType inAtom) {
/*  864 */     Transition trans = behavFactory.createTransition();
/*  865 */     trans.setName(source.getName() + "2" + destination.getName());
/*  866 */     trans.getDestination().add(destination);
/*  867 */     trans.getOrigin().add(source);
/*  868 */     PortDefinitionReference pr = behavFactory.createPortDefinitionReference();
/*  869 */     pr.setTarget(trigger);
/*      */ 
/*      */ 
/*      */     
/*  873 */     trans.setTrigger((PortExpression)pr);
/*      */     
/*  875 */     if (condition != null) {
/*  876 */       trans.setGuard(condition);
/*      */     }
/*  878 */     PetriNet behav = (PetriNet)inAtom.getBehavior();
/*  879 */     behav.getTransition().add(trans);
/*  880 */     return trans;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Transition createTransition(PortDefinition trigger, Expression condition, List<State> sources, List<State> destinations, AtomType inAtom) {
/*  885 */     Transition trans = behavFactory.createTransition();
/*  886 */     trans.setName("newTransition");
/*  887 */     trans.getDestination().addAll(destinations);
/*  888 */     trans.getOrigin().addAll(sources);
/*  889 */     PortDefinitionReference pr = behavFactory.createPortDefinitionReference();
/*  890 */     pr.setTarget(trigger);
/*  891 */     trans.setTrigger((PortExpression)pr);
/*      */     
/*  893 */     if (condition != null) {
/*  894 */       trans.setGuard(condition);
/*      */     }
/*      */ 
/*      */     
/*  898 */     Behavior behav = inAtom.getBehavior();
/*  899 */     assert behav instanceof PetriNet;
/*      */     
/*  901 */     PetriNet pnet = (PetriNet)behav;
/*  902 */     pnet.getTransition().add(trans);
/*  903 */     return trans;
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
/*      */   public static Variable createVariable(DataType type, String name, AtomType inAtom, boolean timed, boolean extern) {
/*      */     TimedVariable timedVariable;
/*  929 */     if (!timed) {
/*  930 */       Variable v = behavFactory.createVariable();
/*      */     } else {
/*  932 */       timedVariable = timeFactory.createTimedVariable();
/*      */     } 
/*  934 */     timedVariable.setName(name);
/*  935 */     timedVariable.setType(type);
/*  936 */     inAtom.getVariable().add(timedVariable);
/*  937 */     timedVariable.setIsExternal(extern);
/*  938 */     return (Variable)timedVariable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InterfaceVariable exportVariable(AtomType inAtom, Variable v) {
/*  948 */     InterfaceVariable iv = behavFactory.createInterfaceVariable();
/*  949 */     iv.setName(v.getName());
/*  950 */     iv.setType(v.getType());
/*  951 */     inAtom.getInterfaceVariable().add(iv);
/*  952 */     VariableDefinitionBinding vdb = behavFactory.createVariableDefinitionBinding();
/*  953 */     iv.getVariableBinding().add(vdb);
/*  954 */     vdb.setVariable(v);
/*  955 */     return iv;
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
/*      */   public static InterfaceVariable exportInternalVariable(CompoundType ct, Part p, InterfaceVariable internalVariable, String exportName) {
/*  967 */     InterfaceVariable iv = behavFactory.createInterfaceVariable();
/*  968 */     iv.setName(internalVariable.getName());
/*  969 */     iv.setType(internalVariable.getType());
/*  970 */     ct.getInterfaceVariable().add(iv);
/*  971 */     VariableExportBinding veb = interFactory.createVariableExportBinding();
/*  972 */     iv.getVariableBinding().add(veb);
/*  973 */     veb.setTargetInterfaceVariable(internalVariable);
/*  974 */     PartElementReference per = interFactory.createPartElementReference();
/*  975 */     per.setTargetPart(p);
/*  976 */     if (exportName == null) {
/*  977 */       iv.setName(internalVariable.getName());
/*      */     } else {
/*  979 */       iv.setName(exportName);
/*      */     } 
/*      */     
/*  982 */     return iv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static State createState(PetriNet behav, String name) {
/*  993 */     State s = behavFactory.createState();
/*  994 */     s.setName(name);
/*  995 */     behav.getState().add(s);
/*  996 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static State createState(PetriNet behav, String name, Boolean isInit) {
/* 1007 */     State s = createState(behav, name);
/* 1008 */     if (isInit.booleanValue())
/* 1009 */       behav.getInitialState().add(s); 
/* 1010 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static State createState(AtomType inAtom, String name) {
/* 1021 */     PetriNet behav = (PetriNet)inAtom.getBehavior();
/* 1022 */     if (behav == null) {
/* 1023 */       behav = behavFactory.createPetriNet();
/* 1024 */       inAtom.setBehavior((Behavior)behav);
/*      */     } 
/* 1026 */     State s = behavFactory.createState();
/* 1027 */     s.setName(name);
/* 1028 */     behav.getState().add(s);
/* 1029 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FunctionCallExpression createFunctionCallExpression(String functionName, Expression[] data) {
/* 1039 */     FunctionCallExpression fce = expressionsFactory.createFunctionCallExpression();
/* 1040 */     fce.setFunctionName(functionName);
/* 1041 */     for (Expression e : data) {
/* 1042 */       fce.getActualData().add(e);
/*      */     }
/*      */     
/* 1045 */     fce.setIsOnRef(false);
/* 1046 */     fce.setNavigated(null);
/* 1047 */     return fce;
/*      */   }
/*      */   
/*      */   public static FunctionCallExpression createMethodCall(Variable v, String methodName, boolean ref) {
/* 1051 */     FunctionCallExpression fce = expressionsFactory.createFunctionCallExpression();
/* 1052 */     fce.setFunctionName(methodName);
/* 1053 */     fce.setIsOnRef(ref);
/* 1054 */     VariableReference vr = expressionsFactory.createVariableReference();
/* 1055 */     vr.setTargetVariable(v);
/* 1056 */     fce.setNavigated((Expression)vr);
/* 1057 */     return fce;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FieldNavigationExpression createFieldNavigationExpression(DataReference navExpr, String fieldName) {
/* 1067 */     FieldNavigationExpression fne = expressionsFactory.createFieldNavigationExpression();
/* 1068 */     fne.setNavigated(navExpr);
/* 1069 */     fne.setFieldName(fieldName);
/* 1070 */     return fne;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static System createSystem(String name, Root root) {
/* 1080 */     System s = createSystem(name);
/* 1081 */     s.setRoot(root);
/* 1082 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static System createSystem(String name) {
/* 1091 */     System s = modulesFactory.createSystem();
/* 1092 */     s.setName(name);
/* 1093 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Package createPackage(String name) {
/* 1102 */     Package s = modulesFactory.createPackage();
/* 1103 */     s.setName(name);
/* 1104 */     return s;
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
/*      */   public static Root createRoot(ComponentType type, String name, System system) {
/* 1126 */     Root r = modulesFactory.createRoot();
/* 1127 */     r.setName(name);
/* 1128 */     r.setType(type);
/* 1129 */     system.setRoot(r);
/* 1130 */     r.setSystem(system);
/* 1131 */     return r;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AtomType createAtomType(Behavior behav, String name, Module module) {
/* 1142 */     AtomType at = behavFactory.createAtomType();
/* 1143 */     at.setName(name);
/* 1144 */     at.setBehavior(behav);
/* 1145 */     at.setModule(module);
/* 1146 */     module.getBipType().add(at);
/* 1147 */     return at;
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
/*      */   public static AtomType copyAtomType(AtomType at, String copyName, Module module) {
/* 1159 */     AtomType atCopy = (AtomType)EcoreUtil.copy((EObject)at);
/* 1160 */     atCopy.setName(copyName);
/* 1161 */     module.getBipType().add(atCopy);
/* 1162 */     return atCopy;
/*      */   }
/*      */   
/*      */   public static CompoundType createCompoundType(String name, Module module) {
/* 1166 */     CompoundType ct = interFactory.createCompoundType();
/* 1167 */     ct.setName(name);
/* 1168 */     ct.setModule(module);
/* 1169 */     module.getBipType().add(ct);
/* 1170 */     return ct;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static CompoundType copyCompoundType(CompoundType comp, String copyName, Module module) {
/* 1176 */     CompoundType ct = (CompoundType)EcoreUtil.copy((EObject)comp);
/* 1177 */     ct.setName(copyName);
/* 1178 */     ct.setModule(module);
/* 1179 */     module.getBipType().add(ct);
/* 1180 */     return ct;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Component createComponentInstance(String name, CompoundType parent, ComponentType instanceType) {
/* 1185 */     Component c = interFactory.createComponent();
/* 1186 */     c.setName(name);
/* 1187 */     c.setCompoundType(parent);
/* 1188 */     c.setType(instanceType);
/*      */     
/* 1190 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PetriNet createPetriNet() {
/* 1198 */     PetriNet pn = behavFactory.createPetriNet();
/* 1199 */     return pn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static DataType createDataType(String name, Module module) {
/*      */     OpaqueElement opaqueElement;
/* 1211 */     DataType dt = BipUtil.getDataType(name, module);
/*      */     
/* 1213 */     if (dt == null) {
/* 1214 */       OpaqueElement oe = modulesFactory.createOpaqueElement();
/* 1215 */       module.getDataType().add(oe);
/* 1216 */       oe.setBody(name);
/* 1217 */       opaqueElement = oe;
/*      */     } 
/* 1219 */     return (DataType)opaqueElement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static DataParameter createDataParameter(String name, DataType type) {
/* 1230 */     DataParameter dp = behavFactory.createDataParameter();
/* 1231 */     dp.setName(name);
/* 1232 */     dp.setType(type);
/* 1233 */     return dp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType createPortType(String name, Module module, List<DataParameter> parameters) {
/* 1244 */     PortType pt = BipUtil.getPortType(module, name);
/* 1245 */     if (pt != null) {
/* 1246 */       return pt;
/*      */     }
/*      */     
/* 1249 */     pt = createPortType(name, module);
/* 1250 */     pt.getDataParameter().addAll(parameters);
/* 1251 */     return pt;
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
/*      */   public static PortType createPortType(String name, Module module, DataParameter[] parameters) {
/* 1263 */     PortType pt = BipUtil.getPortType(module, name);
/* 1264 */     if (pt != null) {
/* 1265 */       return pt;
/*      */     }
/*      */     
/* 1268 */     pt = createPortType(name, module);
/* 1269 */     for (DataParameter dp : parameters) {
/* 1270 */       pt.getDataParameter().add(dp);
/*      */     }
/* 1272 */     return pt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PortType createPortType(String name, Module module) {
/* 1282 */     PortType pt = BipUtil.getPortType(module, name);
/* 1283 */     if (pt != null) {
/* 1284 */       return pt;
/*      */     }
/*      */     
/* 1287 */     pt = behavFactory.createPortType();
/* 1288 */     pt.setName(name);
/* 1289 */     pt.setModule(module);
/* 1290 */     module.getBipType().add(pt);
/*      */     
/* 1292 */     return pt;
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
/*      */   public static PriorityRule createPriorityRule(String name, Connector lowerC, Connector greaterC, CompoundType ct) {
/* 1304 */     PriorityRule npl = prioritiesFactory.createPriorityRule();
/* 1305 */     npl.setName(name);
/*      */     
/* 1307 */     Interaction lower = interFactory.createInteraction();
/* 1308 */     Interaction greater = interFactory.createInteraction();
/*      */     
/* 1310 */     PartElementReference vl = interFactory.createPartElementReference();
/* 1311 */     vl.setTargetPart((Part)lowerC);
/* 1312 */     lower.setConnector(vl);
/* 1313 */     npl.setLower((PriorityElement)lower);
/*      */     
/* 1315 */     PartElementReference vg = interFactory.createPartElementReference();
/* 1316 */     vg.setTargetPart((Part)greaterC);
/* 1317 */     greater.setConnector(vg);
/*      */     
/* 1319 */     npl.setGreater((PriorityElement)greater);
/*      */     
/* 1321 */     ct.getPriorityRule().add(npl);
/* 1322 */     return npl;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\metamodelAPI\BipCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */