/*      */ package ujf.verimag.bip.bip2src;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.util.List;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterSpecification;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IndexLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*      */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
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
/*      */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.MultiplicityElement;
/*      */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*      */ import ujf.verimag.bip.Core.Modules.Declaration;
/*      */ import ujf.verimag.bip.Core.Modules.Module;
/*      */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*      */ import ujf.verimag.bip.Core.Modules.Package;
/*      */ import ujf.verimag.bip.Core.Modules.Root;
/*      */ import ujf.verimag.bip.Core.Modules.System;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
/*      */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*      */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*      */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*      */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*      */ 
/*      */ 
/*      */ public class Reverse
/*      */ {
/*      */   private PrintStream dest;
/*   83 */   private String indent = "";
/*      */   
/*   85 */   private Module currentModule = null;
/*      */   
/*   87 */   private final Logger logger = Logger.getLogger(getClass().getName());
/*      */   
/*      */   private void indent() {
/*   90 */     this.indent += "  ";
/*      */   }
/*      */   
/*      */   private void deindent() {
/*   94 */     this.indent = this.indent.substring(2);
/*      */   }
/*      */   
/*      */   public Reverse(PrintStream dest) {
/*   98 */     this.dest = dest;
/*      */   }
/*      */   
/*      */   public void decompile(Module m) {
/*  102 */     this.currentModule = m;
/*  103 */     this.logger.log(Level.INFO, "generate source");
/*  104 */     if (m instanceof Package) {
/*      */       
/*  106 */       this.dest.print("package ");
/*      */     } else {
/*  108 */       this.dest.print("model ");
/*      */     } 
/*      */     
/*  111 */     this.dest.println(moduleName(m.getName()));
/*      */ 
/*      */     
/*  114 */     indent();
/*      */     
/*  116 */     for (Package p : m.getUsedPackage()) {
/*  117 */       this.dest.println(this.indent + "use " + moduleName(p.getName()));
/*      */     }
/*  119 */     for (Declaration d : m.getDeclaration()) {
/*  120 */       decompile(d);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  127 */     for (BipType bt : m.getBipType()) {
/*  128 */       decompile(bt);
/*      */     }
/*      */     
/*  131 */     if (m instanceof System) {
/*  132 */       System sys = (System)m;
/*  133 */       Root r = sys.getRoot();
/*      */       
/*  135 */       assert r != null : "System does not have a root instance.";
/*      */       
/*  137 */       this.dest.print(this.indent + "component " + bipTypeName((BipType)r.getType()) + " " + r.getName());
/*  138 */       decompileParam((List<Expression>)r.getActualData(), false);
/*  139 */       this.dest.println();
/*      */     } 
/*      */     
/*  142 */     deindent();
/*  143 */     this.dest.println("end");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String moduleName(String mName) {
/*  149 */     int pos = mName.lastIndexOf('/');
/*  150 */     if (pos > 0) mName = mName.substring(pos + 1); 
/*  151 */     pos = mName.lastIndexOf('.');
/*  152 */     if (pos > 0) mName = mName.substring(0, pos); 
/*  153 */     return mName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(Declaration d) {
/*  163 */     if (d instanceof OpaqueElement) {
/*  164 */       OpaqueElement oe = (OpaqueElement)d;
/*  165 */       String header = "";
/*  166 */       if (oe.isIsHeader()) {
/*  167 */         header = "header ";
/*      */       }
/*  169 */       this.dest.println(this.indent + header + "{#" + oe.getBody() + "#}");
/*      */     } else {
/*  171 */       throw new Error("Unimplemented");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String name(NamedElement ne) {
/*  177 */     if (ne == null) {
/*  178 */       throw new Error("Unimplemented");
/*      */     }
/*  180 */     String n = ne.getName();
/*  181 */     if (n == null) {
/*  182 */       throw new Error("Unimplemented");
/*      */     }
/*  184 */     return n;
/*      */   }
/*      */   
/*      */   private String dataTypeName(DataType dt) {
/*  188 */     if (dt == null)
/*  189 */       throw new Error("Unimplemented"); 
/*  190 */     if (dt instanceof OpaqueElement) {
/*  191 */       OpaqueElement oe = (OpaqueElement)dt;
/*  192 */       return oe.getBody();
/*      */     } 
/*  194 */     throw new Error("Unimplemented");
/*      */   }
/*      */ 
/*      */   
/*      */   private String bipTypeName(BipType bt) {
/*  199 */     if (bt == null) {
/*  200 */       throw new Error("Unimplemented");
/*      */     }
/*  202 */     if (bt.getModule() == this.currentModule) {
/*  203 */       return bt.getName();
/*      */     }
/*  205 */     return bt.getModule().getName() + "." + bt.getName();
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
/*      */   public void decompile(BipType bt) {
/*  217 */     if (bt instanceof PortType) {
/*  218 */       PortType pt = (PortType)bt;
/*  219 */       decompile(pt);
/*  220 */     } else if (bt instanceof ConnectorType) {
/*  221 */       ConnectorType ct = (ConnectorType)bt;
/*  222 */       decompile(ct);
/*  223 */     } else if (bt instanceof CompoundType) {
/*  224 */       CompoundType ct = (CompoundType)bt;
/*  225 */       decompile(ct);
/*  226 */     } else if (bt instanceof AtomType) {
/*  227 */       AtomType at = (AtomType)bt;
/*  228 */       decompile(at);
/*      */     } else {
/*  230 */       throw new Error("Unimplemented");
/*      */     } 
/*      */   }
/*      */   
/*      */   void decompileParamDecl(ParameterizedElement pe) {
/*  235 */     EList eList = pe.getDataParameter();
/*  236 */     if (eList.size() > 0) {
/*  237 */       this.dest.print("(");
/*  238 */       boolean first = true;
/*  239 */       for (DataParameter dp : eList) {
/*  240 */         if (first) {
/*  241 */           first = false;
/*      */         } else {
/*  243 */           this.dest.print(", ");
/*      */         } 
/*  245 */         this.dest.print(dataTypeName(dp.getType()) + " " + name((NamedElement)dp));
/*      */       } 
/*  247 */       this.dest.print(")");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompileDecl(PartType pt) {
/*  254 */     for (Declaration d : pt.getDeclaration()) {
/*  255 */       decompile(d);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(Variable v) {
/*  261 */     String decor = "";
/*  262 */     if (v instanceof ujf.verimag.bip.Extra.Time.TimedVariable) {
/*  263 */       decor = decor + "timed ";
/*      */     }
/*  265 */     this.dest.print(this.indent + decor + "data " + dataTypeName(v.getType()) + " " + name((NamedElement)v));
/*  266 */     Expression e = v.getInitialValue();
/*  267 */     if (e != null) {
/*  268 */       this.dest.print("=");
/*  269 */       decompile(e, false);
/*      */     } 
/*  271 */     this.dest.println();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(PortType pt) {
/*  280 */     if (pt.getName().equals("Port"))
/*  281 */       return;  this.dest.print(this.indent + "port type " + pt.getName());
/*      */     
/*  283 */     decompileParamDecl((ParameterizedElement)pt);
/*      */     
/*  285 */     this.dest.println();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(PortExpression pe, boolean inExp) {
/*  294 */     if (pe instanceof ACFusion) {
/*  295 */       ACFusion acf = (ACFusion)pe;
/*  296 */       if (inExp) this.dest.print("["); 
/*  297 */       for (ACExpression aCExpression : acf.getOperand()) {
/*  298 */         this.dest.print(" ");
/*  299 */         decompile((PortExpression)aCExpression, true);
/*      */       } 
/*  301 */       if (inExp) this.dest.print("]"); 
/*  302 */     } else if (pe instanceof PortParameterReference) {
/*  303 */       PortParameterReference ppr = (PortParameterReference)pe;
/*  304 */       this.dest.print(ppr.getTarget().getName());
/*  305 */     } else if (pe instanceof PortDefinitionReference) {
/*  306 */       PortDefinitionReference pdr = (PortDefinitionReference)pe;
/*  307 */       this.dest.print(pdr.getTarget().getName());
/*  308 */     } else if (pe instanceof ACTyping) {
/*  309 */       ACTyping act = (ACTyping)pe;
/*  310 */       decompile((PortExpression)act.getOperand(), true);
/*  311 */       if (act.getType() == ACTypingKind.TRIG) this.dest.print("'"); 
/*      */     } else {
/*  313 */       throw new Error("Unimplemented");
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
/*      */   public void decompile(ConnectorType ct) {
/*  326 */     this.dest.print(this.indent + "connector type " + ct.getName() + "(");
/*      */     
/*  328 */     boolean first = true;
/*  329 */     for (PortParameter pp : ct.getPortParameter()) {
/*  330 */       if (first) {
/*  331 */         first = false;
/*      */       } else {
/*  333 */         this.dest.print(", ");
/*      */       } 
/*  335 */       this.dest.print(bipTypeName((BipType)pp.getType()) + " " + name((NamedElement)pp));
/*      */     } 
/*  337 */     this.dest.print(")");
/*  338 */     decompileParamDecl((ParameterizedElement)ct);
/*  339 */     this.dest.println();
/*  340 */     indent();
/*      */ 
/*      */ 
/*      */     
/*  344 */     this.dest.print(this.indent + "define ");
/*  345 */     decompile(ct.getDefinition(), false);
/*  346 */     this.dest.println();
/*      */ 
/*      */     
/*  349 */     decompileDecl((PartType)ct);
/*      */     
/*  351 */     for (Variable v : ct.getVariable()) {
/*  352 */       decompile(v);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  357 */     for (InteractionSpecification is : ct.getInteractionSpecification()) {
/*  358 */       this.dest.print(this.indent + "on");
/*  359 */       Interaction inter = is.getInteraction();
/*  360 */       for (PortReference ppr : inter.getPort()) {
/*      */ 
/*      */ 
/*      */         
/*  364 */         assert ppr instanceof PortParameterReference : "This code is broken and makes false asumption";
/*  365 */         if (ppr instanceof PortParameterReference) {
/*  366 */           PortParameterReference ppr_b = (PortParameterReference)ppr;
/*  367 */           this.dest.print(" " + name((NamedElement)ppr_b.getTarget()));
/*      */         } 
/*      */       } 
/*  370 */       this.dest.println();
/*  371 */       indent();
/*  372 */       Expression guard = is.getGuard();
/*  373 */       if (guard != null) {
/*  374 */         this.dest.print(this.indent + "provided ");
/*  375 */         decompile(guard, false);
/*  376 */         this.dest.println();
/*      */       } 
/*  378 */       Action act = is.getUpAction();
/*  379 */       if (act != null) {
/*  380 */         this.dest.print(this.indent + "up ");
/*  381 */         decompile(act);
/*  382 */         this.dest.println();
/*      */       } 
/*  384 */       deindent();
/*  385 */       act = is.getDownAction();
/*  386 */       if (act != null) {
/*  387 */         this.dest.print(this.indent + "down ");
/*  388 */         decompile(act);
/*  389 */         this.dest.println();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  394 */     Port p = ct.getPort();
/*  395 */     if (p != null) {
/*  396 */       DefinitionBinding bd = (DefinitionBinding)p.getBinding();
/*  397 */       PortDefinition pd = null;
/*  398 */       if (bd != null) pd = bd.getDefinition(); 
/*  399 */       this.dest.print(this.indent + "export port ");
/*  400 */       decompile(pd, false);
/*      */     } 
/*      */     
/*  403 */     deindent();
/*  404 */     this.dest.println(this.indent + "end");
/*  405 */     this.dest.println();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(CompoundType ct) {
/*  415 */     this.dest.print(this.indent + "compound type " + ct.getName());
/*  416 */     decompileParamDecl((ParameterizedElement)ct);
/*  417 */     this.dest.println();
/*      */     
/*  419 */     indent();
/*      */ 
/*      */     
/*  422 */     decompileDecl((PartType)ct);
/*      */ 
/*      */     
/*  425 */     for (Component comp : ct.getSubcomponent()) {
/*  426 */       decompile(comp);
/*      */     }
/*      */ 
/*      */     
/*  430 */     for (Connector cnx : ct.getConnector()) {
/*  431 */       decompile(cnx);
/*      */     }
/*      */ 
/*      */     
/*  435 */     for (PriorityRule prioRule : ct.getPriorityRule()) {
/*  436 */       decompile(prioRule);
/*      */     }
/*      */ 
/*      */     
/*  440 */     for (Port p : ct.getPort()) {
/*  441 */       decompile(p);
/*  442 */       this.dest.println();
/*      */     } 
/*      */     
/*  445 */     deindent();
/*  446 */     this.dest.println(this.indent + "end");
/*  447 */     this.dest.println();
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(PriorityRule prioRule) {
/*  452 */     this.dest.print(this.indent + "priority " + name((NamedElement)prioRule) + " ");
/*  453 */     decompile(prioRule.getLower());
/*  454 */     this.dest.print(" < ");
/*  455 */     decompile(prioRule.getGreater());
/*  456 */     Expression guard = prioRule.getGuard();
/*  457 */     if (guard != null) {
/*  458 */       this.dest.print(" provided ");
/*  459 */       decompile(guard, false);
/*      */     } 
/*  461 */     this.dest.println();
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(PriorityElement pe) {
/*  466 */     if (pe instanceof Interaction) {
/*  467 */       Interaction inter = (Interaction)pe;
/*  468 */       decompile(inter);
/*  469 */     } else if (pe instanceof ConnectorTypeReference) {
/*  470 */       ConnectorTypeReference ctr = (ConnectorTypeReference)pe;
/*  471 */       this.dest.print(" " + ctr.getTarget().getName() + " ");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void decompile(Interaction inter) {
/*  476 */     if (inter.getConnector() != null) decompile(inter.getConnector()); 
/*  477 */     EList eList = inter.getPort();
/*  478 */     if (eList.size() > 0) {
/*  479 */       if (inter.getConnector() != null) this.dest.print(":"); 
/*  480 */       for (PortReference pr : eList) {
/*  481 */         this.dest.print(" ");
/*  482 */         if (pr instanceof InnerPortReference) {
/*  483 */           InnerPortReference ipr = (InnerPortReference)pr;
/*  484 */           decompile(ipr.getTargetInstance());
/*  485 */           this.dest.print("." + name((NamedElement)ipr.getTargetPort())); continue;
/*  486 */         }  if (pr instanceof PortDefinitionReference) {
/*  487 */           PortDefinitionReference pd = (PortDefinitionReference)pr;
/*  488 */           this.dest.print(name((NamedElement)pd.getTarget())); continue;
/*      */         } 
/*  490 */         throw new Error("Unimplemented");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(Connector cnx) {
/*  497 */     this.dest.print(this.indent + "connector ");
/*  498 */     this.dest.print(bipTypeName((BipType)cnx.getType()) + " " + name((NamedElement)cnx));
/*      */     
/*  500 */     decompileIndex((MultiplicityElement)cnx);
/*      */ 
/*      */ 
/*      */     
/*  504 */     this.dest.print("(");
/*  505 */     boolean first = true;
/*  506 */     for (ActualPortParameter app : cnx.getActualPort()) {
/*  507 */       if (first) {
/*  508 */         first = false;
/*      */       } else {
/*  510 */         this.dest.print(", ");
/*      */       } 
/*  512 */       decompile(app);
/*      */     } 
/*  514 */     this.dest.print(")");
/*      */     
/*  516 */     decompileParam((List<Expression>)cnx.getActualData(), false);
/*  517 */     this.dest.println();
/*      */   }
/*      */   
/*      */   public void decompileIndex(MultiplicityElement me) {
/*  521 */     for (Expression exp : me.getMultiplicitySpecification()) {
/*  522 */       this.dest.print("[");
/*  523 */       decompile(exp, false);
/*  524 */       this.dest.print("]");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(ActualPortParameter app) {
/*  530 */     if (app instanceof ConditionalActualPortParameter) {
/*  531 */       ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/*  532 */       this.dest.print("(");
/*  533 */       decompile(capp.getExpression(), true);
/*  534 */       this.dest.print("?");
/*  535 */       decompile(capp.getTrueCase());
/*  536 */       this.dest.print(":");
/*  537 */       decompile(capp.getFalseCase());
/*  538 */       this.dest.print(")");
/*  539 */     } else if (app instanceof InnerPortReference) {
/*  540 */       InnerPortReference ipr = (InnerPortReference)app;
/*  541 */       decompile(ipr.getTargetInstance());
/*  542 */       this.dest.print(".");
/*  543 */       this.dest.print(name((NamedElement)ipr.getTargetPort()));
/*      */     } else {
/*  545 */       throw new Error("Unimplemented");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(Component comp) {
/*  551 */     this.dest.print(this.indent + "component ");
/*  552 */     this.dest.print(bipTypeName((BipType)comp.getType()) + " " + name((NamedElement)comp));
/*      */     
/*  554 */     decompileIndex((MultiplicityElement)comp);
/*      */     
/*  556 */     decompileParam((List<Expression>)comp.getActualData(), false);
/*  557 */     this.dest.println();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void decompileParam(List<Expression> actualDatas, boolean alwayPar) {
/*  563 */     if (alwayPar || actualDatas.size() > 0) this.dest.print("("); 
/*  564 */     boolean first = true;
/*  565 */     for (Expression exp : actualDatas) {
/*  566 */       if (first) {
/*  567 */         first = false;
/*      */       } else {
/*  569 */         this.dest.print(", ");
/*      */       } 
/*  571 */       decompile(exp, false);
/*      */     } 
/*  573 */     if (alwayPar || actualDatas.size() > 0) this.dest.print(")");
/*      */   
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
/*      */   public void decompile(AtomType at) {
/*  586 */     this.dest.print(this.indent + "atomic type " + at.getName());
/*      */     
/*  588 */     decompileParamDecl((ParameterizedElement)at);
/*  589 */     this.dest.println();
/*      */     
/*  591 */     indent();
/*      */     
/*  593 */     decompileDecl((PartType)at);
/*      */     
/*  595 */     for (Variable v : at.getVariable()) {
/*  596 */       decompile(v);
/*      */     }
/*      */     
/*  599 */     for (PortDefinition pd : at.getPortDefinition()) {
/*  600 */       decompile(pd, true);
/*      */     }
/*      */     
/*  603 */     PetriNet pn = (PetriNet)at.getBehavior();
/*      */     
/*  605 */     for (State s : pn.getState()) {
/*  606 */       this.dest.print(this.indent + "place " + name((NamedElement)s));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  614 */       this.dest.println();
/*      */     } 
/*      */ 
/*      */     
/*  618 */     this.dest.print(this.indent + "initial to ");
/*  619 */     boolean first = true;
/*  620 */     for (State s : pn.getInitialState()) {
/*  621 */       if (first) {
/*  622 */         first = false;
/*  623 */         this.dest.print(s.getName()); continue;
/*      */       } 
/*  625 */       this.dest.print("," + s.getName());
/*      */     } 
/*      */     
/*  628 */     this.dest.println();
/*  629 */     Action initAct = pn.getInitialization();
/*  630 */     if (initAct != null) {
/*  631 */       this.dest.print("do ");
/*  632 */       indent();
/*  633 */       decompile(initAct);
/*  634 */       deindent();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  640 */     for (Transition trans : pn.getTransition()) {
/*  641 */       decompile(trans);
/*      */     }
/*      */ 
/*      */     
/*  645 */     for (PriorityRule prioRule : at.getPriorityRule()) {
/*  646 */       decompile(prioRule);
/*      */     }
/*      */ 
/*      */     
/*  650 */     for (Port p : at.getPort()) {
/*  651 */       decompile(p);
/*  652 */       this.dest.println();
/*      */     } 
/*      */     
/*  655 */     deindent();
/*  656 */     this.dest.println(this.indent + "end");
/*  657 */     this.dest.println();
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(Port p) {
/*  662 */     Binding b = p.getBinding();
/*  663 */     if (b instanceof DefinitionBinding) {
/*  664 */       DefinitionBinding db = (DefinitionBinding)b;
/*  665 */       String name = name((NamedElement)db.getDefinition());
/*  666 */       if (!name.equals(p.getName())) {
/*  667 */         this.dest.print(this.indent + "export port " + bipTypeName((BipType)p.getType()) + " " + name((NamedElement)p) + " is ");
/*  668 */         this.dest.print(name);
/*      */       } 
/*  670 */     } else if (b instanceof ExportBinding) {
/*  671 */       this.dest.print(this.indent + "export port " + bipTypeName((BipType)p.getType()) + " " + name((NamedElement)p) + " is ");
/*  672 */       ExportBinding eb = (ExportBinding)b;
/*  673 */       decompile(eb.getTargetInstance());
/*  674 */       this.dest.print("." + name((NamedElement)eb.getTargetPort()));
/*      */     } else {
/*      */       
/*  677 */       throw new Error("Unimplemented");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(Transition trans) {
/*  685 */     this.dest.print(this.indent + "on ");
/*  686 */     decompile(trans.getTrigger(), false);
/*  687 */     this.dest.println();
/*  688 */     indent();
/*      */ 
/*      */     
/*  691 */     this.dest.print(this.indent + "from");
/*  692 */     boolean first = true;
/*  693 */     for (State s : trans.getOrigin()) {
/*  694 */       if (first) { first = false; }
/*  695 */       else { this.dest.print(","); }
/*  696 */        this.dest.print(" " + name((NamedElement)s));
/*      */     } 
/*  698 */     this.dest.print(" to");
/*  699 */     first = true;
/*  700 */     for (State s : trans.getDestination()) {
/*  701 */       if (first) { first = false; }
/*  702 */       else { this.dest.print(","); }
/*  703 */        this.dest.print(" " + name((NamedElement)s));
/*      */     } 
/*  705 */     this.dest.println();
/*      */ 
/*      */     
/*  708 */     Expression guard = trans.getGuard();
/*  709 */     if (guard != null) {
/*  710 */       this.dest.print(this.indent + "provided ");
/*  711 */       decompile(guard, false);
/*  712 */       this.dest.println();
/*      */     } 
/*      */ 
/*      */     
/*  716 */     TimeSpecification tGuard = trans.getTimeSpecification();
/*  717 */     if (tGuard != null) {
/*  718 */       this.dest.print(this.indent);
/*  719 */       decompile(tGuard);
/*  720 */       this.dest.println();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  725 */     Action act = trans.getAction();
/*  726 */     if (act != null) {
/*  727 */       this.dest.print(this.indent + "do ");
/*  728 */       decompile(act);
/*  729 */       this.dest.println();
/*      */     } 
/*      */     
/*  732 */     deindent();
/*      */   }
/*      */ 
/*      */   
/*      */   public void decompile(TimeSpecification ts) {
/*  737 */     switch (ts.getUrgency().getValue()) {
/*      */       case 1:
/*  739 */         this.dest.print("delayable ");
/*      */         break;
/*      */       case 0:
/*  742 */         this.dest.print("eager ");
/*      */         break;
/*      */       case 2:
/*  745 */         this.dest.print("lazy ");
/*      */         break;
/*      */     } 
/*  748 */     boolean first = true;
/*  749 */     for (TimedConstraint tc : ts.getTimedConstraint()) {
/*  750 */       if (first) { first = false; }
/*  751 */       else { this.dest.print(" and "); }
/*  752 */        this.dest.print(tc.getClock().getTargetVariable().getName());
/*  753 */       this.dest.print(" in (");
/*  754 */       Expression e = tc.getLowBound();
/*  755 */       if (e == null) { this.dest.print("_"); }
/*  756 */       else { decompile(e, false); }
/*  757 */        this.dest.print(",");
/*  758 */       e = tc.getHighBound();
/*  759 */       if (e == null) { this.dest.print("_"); }
/*  760 */       else { decompile(e, false); }
/*  761 */        this.dest.print(")");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(PortDefinition pd, boolean declar) {
/*  769 */     Port p = getPort(pd.getAtomType(), pd.getName());
/*  770 */     if (declar)
/*  771 */       if (p != null) { this.dest.print(this.indent + "export port "); }
/*  772 */       else { this.dest.print(this.indent + "port "); }
/*      */        
/*  774 */     this.dest.print(bipTypeName((BipType)pd.getType()) + " " + name((NamedElement)pd) + "(");
/*  775 */     boolean first = true;
/*  776 */     for (Variable v : pd.getExposedVariable()) {
/*  777 */       if (first) {
/*  778 */         first = false;
/*      */       } else {
/*  780 */         this.dest.print(", ");
/*      */       } 
/*  782 */       this.dest.print(v.getName());
/*      */     } 
/*      */     
/*  785 */     this.dest.println(")");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Port getPort(AtomType at, String name) {
/*  791 */     if (at != null)
/*  792 */       for (Port p : at.getPort()) {
/*  793 */         if (p.getName().equals(name)) return p;
/*      */       
/*      */       }  
/*  796 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(Expression exp, boolean inExp) {
/*  806 */     if (exp == null)
/*  807 */       throw new Error("Unimplemented"); 
/*  808 */     if (exp instanceof IntegerLiteral) {
/*  809 */       IntegerLiteral il = (IntegerLiteral)exp;
/*  810 */       this.dest.print(il.getIValue());
/*  811 */     } else if (exp instanceof IndexLiteral) {
/*  812 */       IndexLiteral il = (IndexLiteral)exp;
/*  813 */       this.dest.print("$" + il.getId());
/*  814 */     } else if (exp instanceof BooleanLiteral) {
/*  815 */       BooleanLiteral bl = (BooleanLiteral)exp;
/*  816 */       if (bl.isBValue()) {
/*  817 */         this.dest.print("true");
/*      */       } else {
/*  819 */         this.dest.print("false");
/*      */       } 
/*  821 */     } else if (exp instanceof StringLiteral) {
/*  822 */       StringLiteral sl = (StringLiteral)exp;
/*  823 */       this.dest.print(sl.getSValue());
/*  824 */     } else if (exp instanceof RealLiteral) {
/*  825 */       RealLiteral rl = (RealLiteral)exp;
/*  826 */       this.dest.print(rl.getRValue());
/*  827 */     } else if (exp instanceof RealLiteral) {
/*  828 */       RealLiteral rl = (RealLiteral)exp;
/*  829 */       this.dest.print(rl.getRValue());
/*  830 */     } else if (exp instanceof ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral) {
/*      */       
/*  832 */       this.dest.print("NULL");
/*  833 */     } else if (exp instanceof VariableReference) {
/*  834 */       VariableReference vr = (VariableReference)exp;
/*  835 */       this.dest.print(name((NamedElement)vr.getTargetVariable()));
/*  836 */     } else if (exp instanceof StateReference) {
/*  837 */       StateReference sr = (StateReference)exp;
/*  838 */       this.dest.print(name((NamedElement)sr.getTargetState()));
/*  839 */     } else if (exp instanceof DataParameterReference) {
/*  840 */       DataParameterReference dpr = (DataParameterReference)exp;
/*  841 */       this.dest.print(name((NamedElement)dpr.getTargetParameter()));
/*  842 */     } else if (exp instanceof RequiredDataParameterReference) {
/*  843 */       RequiredDataParameterReference rdpr = (RequiredDataParameterReference)exp;
/*  844 */       PortParameterReference ppr = rdpr.getPortReference();
/*  845 */       if (ppr == null)
/*  846 */         throw new Error("Unimplemented"); 
/*  847 */       this.dest.print(name((NamedElement)ppr.getTarget()));
/*  848 */       this.dest.print(".");
/*  849 */       this.dest.print(name((NamedElement)rdpr.getTargetParameter()));
/*  850 */     } else if (exp instanceof InnerDataParameterReference) {
/*  851 */       InnerDataParameterReference idpr = (InnerDataParameterReference)exp;
/*  852 */       InnerPortReference ipr = idpr.getPortReference();
/*  853 */       if (ipr == null) {
/*  854 */         throw new Error("Unimplemented");
/*      */       }
/*  856 */       decompile(ipr.getTargetInstance());
/*      */       
/*  858 */       this.dest.print("." + name((NamedElement)ipr.getTargetPort()));
/*  859 */     } else if (exp instanceof DataParameterSpecification) {
/*  860 */       DataParameterSpecification dps = (DataParameterSpecification)exp;
/*  861 */       this.dest.print(name((NamedElement)dps.getTargetParameter()));
/*  862 */     } else if (exp instanceof FunctionCallExpression) {
/*  863 */       FunctionCallExpression fce = (FunctionCallExpression)exp;
/*  864 */       String name = fce.getFunctionName();
/*  865 */       Expression dr = fce.getNavigated();
/*  866 */       if (dr != null) {
/*  867 */         decompile(dr, true);
/*  868 */         if (fce.isIsOnRef()) { this.dest.print("->"); }
/*  869 */         else { this.dest.print("."); }
/*      */       
/*  871 */       }  if (name == null)
/*  872 */         throw new Error("Unimplemented"); 
/*  873 */       this.dest.print(name);
/*  874 */       decompileParam((List<Expression>)fce.getActualData(), true);
/*  875 */     } else if (exp instanceof FieldNavigationExpression) {
/*  876 */       FieldNavigationExpression fne = (FieldNavigationExpression)exp;
/*  877 */       decompile((Expression)fne.getNavigated(), true);
/*  878 */       String field = fne.getFieldName();
/*  879 */       if (field == null)
/*  880 */         throw new Error("Unimplemented"); 
/*  881 */       if (fne.isIsOnRef()) {
/*  882 */         this.dest.print("->" + field);
/*      */       } else {
/*      */         
/*  885 */         this.dest.print("." + field);
/*      */       } 
/*  887 */     } else if (exp instanceof ArrayNavigationExpression) {
/*  888 */       ArrayNavigationExpression ane = (ArrayNavigationExpression)exp;
/*  889 */       decompile((Expression)ane.getNavigated(), true);
/*  890 */       this.dest.print("[");
/*  891 */       decompile(ane.getIndex(), false);
/*  892 */       this.dest.print("]");
/*  893 */     } else if (exp instanceof BinaryExpression) {
/*  894 */       BinaryExpression be = (BinaryExpression)exp;
/*  895 */       if (inExp) this.dest.print("("); 
/*  896 */       decompile(be.getLeftOperand(), true);
/*      */       
/*  898 */       switch (be.getOperator().getValue()) {
/*      */         case 0:
/*  900 */           this.dest.print("+");
/*      */           break;
/*      */         case 3:
/*  903 */           this.dest.print("/");
/*      */           break;
/*      */         case 5:
/*  906 */           this.dest.print("==");
/*      */           break;
/*      */         case 8:
/*  909 */           this.dest.print(">");
/*      */           break;
/*      */         case 10:
/*  912 */           this.dest.print(">=");
/*      */           break;
/*      */         case 6:
/*  915 */           this.dest.print("!=");
/*      */           break;
/*      */         case 7:
/*  918 */           this.dest.print("<");
/*      */           break;
/*      */         case 9:
/*  921 */           this.dest.print("<=");
/*      */           break;
/*      */         case 12:
/*  924 */           this.dest.print("&&");
/*      */           break;
/*      */         case 11:
/*  927 */           this.dest.print("||");
/*      */           break;
/*      */         case 15:
/*  930 */           this.dest.print("&");
/*      */           break;
/*      */         case 13:
/*  933 */           this.dest.print("|");
/*      */           break;
/*      */         case 14:
/*  936 */           this.dest.print("^");
/*      */           break;
/*      */         case 16:
/*  939 */           this.dest.print("<<");
/*      */           break;
/*      */         case 17:
/*  942 */           this.dest.print(">>");
/*      */           break;
/*      */         case 4:
/*  945 */           this.dest.print("%");
/*      */           break;
/*      */         case 2:
/*  948 */           this.dest.print("*");
/*      */           break;
/*      */         case 1:
/*  951 */           this.dest.print("-");
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  956 */       decompile(be.getRightOperand(), true);
/*  957 */       if (inExp) this.dest.print(")");
/*      */     
/*  959 */     } else if (exp instanceof UnaryExpression) {
/*  960 */       UnaryExpression ue = (UnaryExpression)exp;
/*  961 */       if (inExp) this.dest.print("("); 
/*  962 */       switch (ue.getOperator().getValue()) {
/*      */         case 0:
/*  964 */           this.dest.print("+");
/*      */           break;
/*      */         case 1:
/*  967 */           this.dest.print("-");
/*      */           break;
/*      */         case 2:
/*  970 */           this.dest.print("!");
/*      */           break;
/*      */         case 5:
/*  973 */           this.dest.print("~");
/*      */           break;
/*      */         case 3:
/*  976 */           this.dest.print("*");
/*      */           break;
/*      */         case 4:
/*  979 */           this.dest.print("&");
/*      */           break;
/*      */         case 7:
/*  982 */           this.dest.print("--");
/*      */           break;
/*      */         case 6:
/*  985 */           this.dest.print("++");
/*      */           break;
/*      */       } 
/*  988 */       decompile(ue.getOperand(), true);
/*  989 */       if (inExp) this.dest.print(")"); 
/*  990 */     } else if (exp instanceof OpaqueElement) {
/*  991 */       OpaqueElement oe = (OpaqueElement)exp;
/*  992 */       this.dest.println("{#" + oe.getBody() + "#}");
/*      */     } else {
/*  994 */       throw new Error("Unimplemented");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void decompile(PartElementReference per) {
/*  999 */     this.dest.print(name((NamedElement)per.getTargetPart()));
/*      */     
/* 1001 */     for (Expression exp : per.getIndex()) {
/* 1002 */       this.dest.print("[");
/* 1003 */       decompile(exp, false);
/* 1004 */       this.dest.print("]");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decompile(Action act) {
/* 1014 */     if (act == null)
/* 1015 */       throw new Error("Unimplemented"); 
/* 1016 */     if (act instanceof OpaqueElement) {
/* 1017 */       OpaqueElement oe = (OpaqueElement)act;
/* 1018 */       this.dest.println("{#" + oe.getBody() + "#}");
/* 1019 */     } else if (act instanceof Expression) {
/* 1020 */       Expression exp = (Expression)act;
/* 1021 */       decompile(exp, false);
/* 1022 */       this.dest.println(";");
/* 1023 */     } else if (act instanceof AssignmentAction) {
/* 1024 */       AssignmentAction aa = (AssignmentAction)act;
/* 1025 */       decompile((Expression)aa.getAssignedTarget(), false);
/* 1026 */       switch (aa.getType()) { case ASSIGN:
/* 1027 */           this.dest.print(" = "); break;
/* 1028 */         case PLUS_ASSIGN: this.dest.print(" += "); break;
/* 1029 */         case MINUS_ASSIGN: this.dest.print(" -= "); break;
/* 1030 */         case DIV_ASSIGN: this.dest.print(" /= "); break;
/* 1031 */         case MULT_ASSIGN: this.dest.print(" *= "); break;
/* 1032 */         case MOD_ASSIGN: this.dest.print(" %= "); break; }
/*      */       
/* 1034 */       decompile(aa.getAssignedValue(), false);
/* 1035 */       this.dest.println(";");
/* 1036 */     } else if (act instanceof CompositeAction) {
/* 1037 */       CompositeAction ca = (CompositeAction)act;
/* 1038 */       this.dest.println("{");
/* 1039 */       indent();
/* 1040 */       for (Action a : ca.getContent()) {
/* 1041 */         this.dest.print(this.indent);
/* 1042 */         decompile(a);
/*      */       } 
/* 1044 */       deindent();
/* 1045 */       this.dest.println(this.indent + "}");
/*      */     }
/* 1047 */     else if (act instanceof IfAction) {
/* 1048 */       IfAction ia = (IfAction)act;
/* 1049 */       this.dest.print("if (");
/* 1050 */       decompile(ia.getCondition(), false);
/* 1051 */       this.dest.print(") ");
/* 1052 */       decompile(ia.getIfCase());
/* 1053 */       this.dest.println();
/* 1054 */       Action a = ia.getElseCase();
/* 1055 */       if (a != null) {
/* 1056 */         this.dest.print(this.indent);
/* 1057 */         decompile(a);
/* 1058 */         this.dest.println();
/*      */       } 
/*      */     } else {
/* 1061 */       throw new Error("Unimplemented");
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\bip2src\Reverse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */