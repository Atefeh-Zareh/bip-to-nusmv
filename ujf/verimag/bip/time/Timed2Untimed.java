/*     */ package ujf.verimag.bip.time;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesFactory;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*     */ import ujf.verimag.bip.Extra.Time.TimedVariable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Timed2Untimed
/*     */ {
/*  80 */   private Map<ComponentType, Port> timedComp = null;
/*  81 */   private Set<ComponentType> untimedComp = null;
/*  82 */   private Map<ComponentType, Port> userTimedComp = null;
/*  83 */   private ComponentType top = null;
/*     */   
/*     */   private static final int MAX_CONNECTED_TICK = 10;
/*  86 */   private ConnectorType[] tickConnectorType = new ConnectorType[10];
/*     */   
/*  88 */   private PortType eventPortType = null;
/*     */   
/*     */   public static final String TICKNAME = "tick";
/*     */   
/*     */   public static final String TIMESTATE = "time";
/*     */   
/*     */   private void untimed(Transition trans, AtomType at, PortDefinition tick) {
/*  95 */     TimeSpecification ts = trans.getTimeSpecification();
/*  96 */     if (ts == null)
/*     */       return; 
/*  98 */     Expression guard = trans.getGuard();
/*     */     
/* 100 */     for (TimedConstraint cs : ts.getTimedConstraint()) {
/* 101 */       Expression low = cs.getLowBound();
/* 102 */       Expression high = cs.getHighBound();
/* 103 */       Variable clock = cs.getClock().getTargetVariable();
/* 104 */       if (low != null) {
/* 105 */         guard = generateAndExp(guard, generateCompare(clock, BinaryOperator.GREATER_THAN_OR_EQUAL, expCopy(low)));
/*     */       }
/* 107 */       if (high != null) {
/* 108 */         guard = generateAndExp(guard, generateCompare(clock, BinaryOperator.LESS_THAN_OR_EQUAL, expCopy(high)));
/*     */       }
/*     */     } 
/* 111 */     trans.setGuard(guard);
/* 112 */     if (ts != null) {
/* 113 */       PriorityRule prio; Expression maxCond, prioGuard = null;
/*     */       
/* 115 */       switch (ts.getUrgency().getValue()) {
/*     */         
/*     */         case 0:
/* 118 */           prio = createPriority(trans, tick, at);
/*     */           break;
/*     */         
/*     */         case 1:
/* 122 */           prio = createPriority(trans, tick, at);
/* 123 */           prioGuard = prio.getGuard();
/*     */           
/* 125 */           maxCond = null;
/* 126 */           for (TimedConstraint cs : ts.getTimedConstraint()) {
/* 127 */             Expression high = cs.getHighBound();
/* 128 */             Variable clock = cs.getClock().getTargetVariable();
/* 129 */             if (high != null) {
/* 130 */               maxCond = generateOrExp(maxCond, generateCompare(clock, BinaryOperator.EQUALITY, expCopy(high)));
/*     */             }
/*     */           } 
/* 133 */           if (maxCond != null) prioGuard = generateAndExp(prioGuard, maxCond); 
/* 134 */           prio.setGuard(prioGuard);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Expression expCopy(Expression exp) {
/* 144 */     return (Expression)EcoreUtil.copy((EObject)exp);
/*     */   }
/*     */   
/*     */   private void addTriggerInInteraction(Interaction inter, PortExpression pe) {
/* 148 */     if (pe instanceof PortDefinitionReference) {
/* 149 */       PortDefinitionReference pdr = (PortDefinitionReference)pe;
/* 150 */       PortDefinitionReference pdr2 = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 151 */       pdr2.setTarget(pdr.getTarget());
/* 152 */       inter.getPort().add(pdr2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private PriorityRule createPriority(Transition trans, PortDefinition tick, AtomType at) {
/* 159 */     PriorityRule prio = PrioritiesFactory.eINSTANCE.createPriorityRule();
/* 160 */     prio.setName("tick" + triggerName(trans.getTrigger()));
/* 161 */     PortDefinitionReference tickRef = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 162 */     tickRef.setTarget(tick);
/* 163 */     Interaction lower = InteractionsFactory.eINSTANCE.createInteraction();
/* 164 */     lower.getPort().add(tickRef);
/* 165 */     prio.setLower((PriorityElement)lower);
/* 166 */     Interaction greater = InteractionsFactory.eINSTANCE.createInteraction();
/* 167 */     addTriggerInInteraction(greater, trans.getTrigger());
/* 168 */     prio.setGreater((PriorityElement)greater);
/* 169 */     at.getPriorityRule().add(prio);
/* 170 */     Expression prioGuard = null;
/* 171 */     for (State s : trans.getOrigin()) {
/* 172 */       StateReference sr = ExpressionsFactory.eINSTANCE.createStateReference();
/* 173 */       sr.setTargetState(s);
/* 174 */       prioGuard = generateAndExp(prioGuard, (Expression)sr);
/*     */     } 
/* 176 */     prio.setGuard(prioGuard);
/* 177 */     return prio;
/*     */   }
/*     */ 
/*     */   
/*     */   private String triggerName(PortExpression pe) {
/* 182 */     if (pe instanceof PortDefinitionReference) {
/* 183 */       PortDefinitionReference pdr = (PortDefinitionReference)pe;
/* 184 */       return pdr.getTarget().getName();
/*     */     } 
/* 186 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private Expression generateAndExp(Expression guard, Expression newGuard) {
/* 191 */     if (guard == null) return newGuard; 
/* 192 */     BinaryExpression be = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 193 */     be.setLeftOperand(guard);
/* 194 */     be.setRightOperand(newGuard);
/* 195 */     be.setOperator(BinaryOperator.LOGICAL_AND);
/* 196 */     return (Expression)be;
/*     */   }
/*     */   private Expression generateOrExp(Expression guard, Expression newGuard) {
/* 199 */     if (guard == null) return newGuard; 
/* 200 */     BinaryExpression be = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 201 */     be.setLeftOperand(guard);
/* 202 */     be.setRightOperand(newGuard);
/* 203 */     be.setOperator(BinaryOperator.LOGICAL_OR);
/* 204 */     return (Expression)be;
/*     */   }
/*     */   
/*     */   private Expression generateCompare(Variable clock, BinaryOperator op, Expression exp) {
/* 208 */     BinaryExpression be = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 209 */     VariableReference vr = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 210 */     vr.setTargetVariable(clock);
/* 211 */     be.setLeftOperand((Expression)vr);
/* 212 */     be.setRightOperand(exp);
/* 213 */     be.setOperator(op);
/* 214 */     return (Expression)be;
/*     */   }
/*     */ 
/*     */   
/*     */   private void untimed(AtomType at) {
/* 219 */     Port tick = this.timedComp.get(at);
/*     */ 
/*     */     
/* 222 */     if (tick == null)
/* 223 */       return;  PortDefinition tickDef = ((DefinitionBinding)tick.getBinding()).getDefinition();
/*     */     
/* 225 */     State timeState = BehaviorsFactory.eINSTANCE.createState();
/* 226 */     timeState.setName("time");
/*     */ 
/*     */     
/* 229 */     PetriNet behav = (PetriNet)at.getBehavior();
/* 230 */     behav.getInitialState().add(timeState);
/* 231 */     behav.getState().add(timeState);
/*     */ 
/*     */     
/* 234 */     for (Transition trans : behav.getTransition()) {
/* 235 */       untimed(trans, at, tickDef);
/*     */     }
/*     */     
/* 238 */     Transition timeTrans = BehaviorsFactory.eINSTANCE.createTransition();
/* 239 */     PortDefinitionReference pdr = BehaviorsFactory.eINSTANCE.createPortDefinitionReference();
/* 240 */     pdr.setTarget(tickDef);
/* 241 */     timeTrans.setTrigger((PortExpression)pdr);
/* 242 */     timeTrans.setName("tick");
/* 243 */     timeTrans.getOrigin().add(timeState);
/* 244 */     timeTrans.getDestination().add(timeState);
/* 245 */     CompositeAction timeIncr = ActionsFactory.eINSTANCE.createCompositeAction();
/* 246 */     timeTrans.setAction((Action)timeIncr);
/* 247 */     behav.getTransition().add(timeTrans);
/*     */     
/* 249 */     for (Variable v : at.getVariable()) {
/* 250 */       if (v instanceof TimedVariable) {
/* 251 */         TimedVariable clock = (TimedVariable)v;
/*     */         
/* 253 */         AssignmentAction ass = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 254 */         VariableReference vr = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 255 */         vr.setTargetVariable((Variable)clock);
/* 256 */         ass.setAssignedTarget((DataReference)vr);
/* 257 */         vr = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 258 */         vr.setTargetVariable((Variable)clock);
/* 259 */         BinaryExpression be = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 260 */         be.setLeftOperand((Expression)vr);
/* 261 */         be.setOperator(BinaryOperator.ADDITION);
/* 262 */         IntegerLiteral il = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/* 263 */         il.setIValue(1);
/* 264 */         be.setRightOperand((Expression)il);
/* 265 */         ass.setAssignedValue((Expression)be);
/* 266 */         timeIncr.getContent().add(ass);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void untimed(CompoundType ct) {
/* 273 */     Port tick = this.timedComp.get(ct);
/*     */ 
/*     */     
/* 276 */     if (tick == null && !this.timedComp.containsKey(ct))
/*     */       return; 
/* 278 */     int nbTick = 0;
/* 279 */     int nbCnx = 1;
/*     */     
/* 281 */     Connector cnxTick = InteractionsFactory.eINSTANCE.createConnector();
/* 282 */     cnxTick.setName("tick" + nbCnx);
/*     */     
/* 284 */     for (Component comp : ct.getSubcomponent()) {
/* 285 */       ComponentType compType = comp.getType();
/* 286 */       Port subTick = this.timedComp.get(compType);
/* 287 */       if (subTick == null) {
/* 288 */         subTick = this.userTimedComp.get(compType);
/*     */       }
/* 290 */       if (subTick != null) {
/* 291 */         nbTick++;
/*     */ 
/*     */ 
/*     */         
/* 295 */         if (nbTick > 10) {
/* 296 */           if (this.tickConnectorType[nbTick - 2] == null) {
/* 297 */             this.tickConnectorType[nbTick - 2] = addTickConnectorType(nbTick - 1);
/*     */           }
/* 299 */           cnxTick.setType(this.tickConnectorType[nbTick - 2]);
/* 300 */           ct.getConnector().add(cnxTick);
/* 301 */           nbCnx++;
/* 302 */           Connector newCnxTick = InteractionsFactory.eINSTANCE.createConnector();
/* 303 */           newCnxTick.setName("tick" + nbCnx);
/* 304 */           InnerPortReference innerPortReference = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 305 */           PartElementReference partElementReference = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 306 */           partElementReference.setTargetPart((Part)cnxTick);
/* 307 */           innerPortReference.setTargetInstance(partElementReference);
/* 308 */           innerPortReference.setTargetPort(this.tickConnectorType[nbTick - 2].getPort());
/* 309 */           newCnxTick.getActualPort().add(innerPortReference);
/* 310 */           nbTick = 2;
/* 311 */           cnxTick = newCnxTick;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 317 */         InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 318 */         PartElementReference subPer = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 319 */         subPer.setTargetPart((Part)comp);
/* 320 */         ipr.setTargetInstance(subPer);
/* 321 */         ipr.setTargetPort(subTick);
/* 322 */         cnxTick.getActualPort().add(ipr);
/*     */       } 
/*     */     } 
/*     */     
/* 326 */     if (this.tickConnectorType[nbTick - 1] == null) {
/* 327 */       this.tickConnectorType[nbTick - 1] = addTickConnectorType(nbTick);
/*     */     }
/*     */     
/* 330 */     cnxTick.setType(this.tickConnectorType[nbTick - 1]);
/* 331 */     ct.getConnector().add(cnxTick);
/* 332 */     if (ct != this.top) {
/* 333 */       ExportBinding eb = InteractionsFactory.eINSTANCE.createExportBinding();
/* 334 */       PartElementReference per = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 335 */       eb.setTargetInstance(per);
/* 336 */       tick.setBinding((Binding)eb);
/* 337 */       per.setTargetPart((Part)cnxTick);
/* 338 */       eb.setTargetPort(this.tickConnectorType[nbTick - 1].getPort());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ConnectorType addTickConnectorType(int nbTick) {
/* 345 */     ConnectorType cnxType = InteractionsFactory.eINSTANCE.createConnectorType();
/* 346 */     cnxType.setName("tickType" + nbTick);
/* 347 */     ACFusion pe = PortExpressionsFactory.eINSTANCE.createACFusion();
/* 348 */     cnxType.setDefinition((PortExpression)pe);
/* 349 */     for (int i = 0; i < nbTick; i++) {
/* 350 */       PortParameter portParameter = InteractionsFactory.eINSTANCE.createPortParameter();
/* 351 */       portParameter.setName("tick" + i);
/* 352 */       portParameter.setType(this.eventPortType);
/* 353 */       cnxType.getPortParameter().add(portParameter);
/* 354 */       PortParameterReference pr = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 355 */       pr.setTarget(portParameter);
/* 356 */       pe.getOperand().add(pr);
/*     */     } 
/*     */     
/* 359 */     Port tickPort = BehaviorsFactory.eINSTANCE.createPort();
/* 360 */     PortDefinition pd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 361 */     pd.setName("tick");
/* 362 */     tickPort.setName("tick");
/* 363 */     pd.setType(this.eventPortType);
/* 364 */     tickPort.setType(this.eventPortType);
/* 365 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 366 */     tickPort.setBinding((Binding)db);
/* 367 */     db.setDefinition(pd);
/* 368 */     cnxType.setPort(tickPort);
/* 369 */     cnxType.setPortDefinition(pd);
/* 370 */     return cnxType;
/*     */   }
/*     */   
/*     */   public boolean isTimed(ComponentType ct) {
/* 374 */     if (this.timedComp.get(ct) != null) return true; 
/* 375 */     if (this.untimedComp.contains(ct)) return false; 
/* 376 */     if (this.userTimedComp.get(ct) != null) return false;
/*     */ 
/*     */     
/* 379 */     for (Port p : ct.getPort()) {
/* 380 */       if (p.getName().equals("tick")) {
/* 381 */         this.userTimedComp.put(ct, p);
/* 382 */         return false;
/*     */       } 
/*     */     } 
/* 385 */     if (ct instanceof AtomType) {
/* 386 */       return isTimed((AtomType)ct);
/*     */     }
/* 388 */     return isTimed((CompoundType)ct);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTimed(AtomType at) {
/* 397 */     for (PortDefinition pd : at.getPortDefinition()) {
/* 398 */       if (pd.getName().equals("tick")) {
/* 399 */         Port tick = null;
/* 400 */         for (Port p : at.getPort()) {
/* 401 */           DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 402 */           if (db.getDefinition() == p) {
/* 403 */             tick = p;
/*     */             break;
/*     */           } 
/*     */         } 
/* 407 */         if (tick == null)
/*     */         {
/* 409 */           if (at != this.top) {
/* 410 */             tick = BehaviorsFactory.eINSTANCE.createPort();
/* 411 */             DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 412 */             tick.setBinding((Binding)db);
/* 413 */             tick.setType(pd.getType());
/* 414 */             tick.setName("tick");
/* 415 */             db.setDefinition(pd);
/* 416 */             at.getPort().add(tick);
/*     */           } 
/*     */         }
/* 419 */         this.userTimedComp.put(at, tick);
/* 420 */         return false;
/*     */       } 
/*     */     } 
/* 423 */     PetriNet pn = (PetriNet)at.getBehavior();
/* 424 */     for (Transition trans : pn.getTransition()) {
/* 425 */       if (trans.getTimeSpecification() != null) {
/*     */         
/* 427 */         PortDefinition pd = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 428 */         at.getPortDefinition().add(pd);
/* 429 */         pd.setName("tick");
/* 430 */         pd.setType(this.eventPortType);
/*     */         
/* 432 */         if (at != this.top) {
/* 433 */           Port tick = BehaviorsFactory.eINSTANCE.createPort();
/* 434 */           DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 435 */           tick.setBinding((Binding)db);
/* 436 */           tick.setType(pd.getType());
/* 437 */           tick.setName("tick");
/* 438 */           db.setDefinition(pd);
/* 439 */           at.getPort().add(tick);
/* 440 */           this.timedComp.put(at, tick);
/*     */         } else {
/* 442 */           this.timedComp.put(at, null);
/*     */         } 
/* 444 */         return true;
/*     */       } 
/*     */     } 
/* 447 */     this.untimedComp.add(at);
/* 448 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isTimed(CompoundType ct) {
/* 452 */     boolean containsTimed = false;
/* 453 */     for (Component comp : ct.getSubcomponent()) {
/* 454 */       ComponentType subcompType = comp.getType();
/* 455 */       containsTimed |= isTimed(subcompType);
/*     */     } 
/* 457 */     if (containsTimed) {
/* 458 */       if (ct != this.top) {
/*     */         
/* 460 */         Port tick = BehaviorsFactory.eINSTANCE.createPort();
/* 461 */         tick.setType(this.eventPortType);
/* 462 */         tick.setName("tick");
/* 463 */         ct.getPort().add(tick);
/* 464 */         this.timedComp.put(ct, tick);
/*     */       } else {
/* 466 */         this.timedComp.put(ct, null);
/*     */       } 
/* 468 */       return true;
/*     */     } 
/* 470 */     this.untimedComp.add(ct);
/* 471 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markTimedComp(Module m) {
/* 477 */     this.timedComp = new HashMap<ComponentType, Port>();
/* 478 */     this.userTimedComp = new HashMap<ComponentType, Port>();
/* 479 */     this.untimedComp = new HashSet<ComponentType>();
/* 480 */     boolean containsTimed = false;
/* 481 */     for (BipType bt : m.getBipType()) {
/* 482 */       if (bt instanceof ComponentType) {
/* 483 */         ComponentType ct = (ComponentType)bt;
/* 484 */         containsTimed |= isTimed(ct);
/*     */       } 
/*     */     } 
/* 487 */     return containsTimed;
/*     */   }
/*     */ 
/*     */   
/*     */   private PortType getSimplePortType(Module m) {
/* 492 */     PortType simplePT = null;
/*     */ 
/*     */     
/* 495 */     for (Package package_ : m.getUsedPackage()) {
/* 496 */       simplePT = getSimplePortType((Module)package_);
/* 497 */       if (simplePT != null) return simplePT; 
/*     */     } 
/* 499 */     for (BipType bt : m.getBipType()) {
/* 500 */       if (bt instanceof PortType) {
/* 501 */         PortType pt = (PortType)bt;
/* 502 */         if (pt.getName().equals("Port")) {
/* 503 */           return pt;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 510 */     return simplePT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void untimed(Module m) {
/* 521 */     this.eventPortType = getSimplePortType(m); int i;
/* 522 */     for (i = 0; i < this.tickConnectorType.length; i++) {
/* 523 */       this.tickConnectorType[i] = null;
/*     */     }
/*     */     
/* 526 */     if (m instanceof System) {
/* 527 */       System sys = (System)m;
/*     */       
/* 529 */       this.top = sys.getRoot().getType();
/*     */     } 
/* 531 */     this.timedComp = new HashMap<ComponentType, Port>();
/* 532 */     this.userTimedComp = new HashMap<ComponentType, Port>();
/* 533 */     this.untimedComp = new HashSet<ComponentType>();
/*     */ 
/*     */     
/* 536 */     for (BipType bt : m.getBipType()) {
/* 537 */       if (bt instanceof ComponentType) {
/* 538 */         ComponentType ct = (ComponentType)bt;
/* 539 */         isTimed(ct);
/*     */       } 
/*     */     } 
/* 542 */     for (BipType bt : m.getBipType()) {
/* 543 */       if (bt instanceof AtomType) {
/* 544 */         AtomType at = (AtomType)bt;
/* 545 */         untimed(at); continue;
/* 546 */       }  if (bt instanceof CompoundType) {
/* 547 */         CompoundType ct = (CompoundType)bt;
/* 548 */         untimed(ct);
/*     */       } 
/*     */     } 
/* 551 */     for (i = 0; i < this.tickConnectorType.length; i++) {
/* 552 */       if (this.tickConnectorType[i] != null)
/* 553 */         m.getBipType().add(0, this.tickConnectorType[i]); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\time\Timed2Untimed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */