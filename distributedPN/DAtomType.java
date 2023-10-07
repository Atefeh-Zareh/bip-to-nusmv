/*     */ package distributedPN;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
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
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DAtomType
/*     */ {
/*     */   private AtomType CAT;
/*     */   private AtomType DAT;
/*  37 */   private List<List<String>> ConflictPort = new LinkedList<List<String>>();
/*  38 */   private List<String> LStringPort = new LinkedList<String>();
/*  39 */   private List<Port> LCentPort = new LinkedList<Port>();
/*     */   private boolean[][] ConflictPortbyPort;
/*     */   private int NumExpPort;
/*  42 */   private List<Variable> LPortGuardVar = new LinkedList<Variable>();
/*  43 */   private List<Port> ClassPort = new LinkedList<Port>();
/*  44 */   static int porttypeitt = 0;
/*     */ 
/*     */   
/*     */   public DAtomType(AtomType CAT) {
/*  48 */     this.CAT = CAT;
/*     */     
/*  50 */     this.NumExpPort = CAT.getPort().size();
/*  51 */     this.ConflictPortbyPort = new boolean[this.NumExpPort][this.NumExpPort];
/*  52 */     setLStringPort();
/*  53 */     resetConflictPortbyPort();
/*  54 */     setConflictPortbyPort();
/*  55 */     TransitiveClosureConflictPort();
/*  56 */     setConflictPort();
/*  57 */     this.DAT = BehaviorsFactory.eINSTANCE.createAtomType();
/*  58 */     this.DAT = (AtomType)EcoreUtil.copy((EObject)CAT);
/*  59 */     this.DAT.setName(String.valueOf(CAT.getName()) + "_Distributed");
/*  60 */     setDAT();
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetConflictPortbyPort() {
/*  65 */     for (int i = 0; i < this.NumExpPort; i++) {
/*  66 */       for (int j = 0; j < this.NumExpPort; j++) {
/*     */         
/*  68 */         if (i != j) {
/*  69 */           this.ConflictPortbyPort[i][j] = false;
/*     */         } else {
/*  71 */           this.ConflictPortbyPort[i][j] = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AtomType getCAT() {
/*  78 */     return this.CAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getDAT() {
/*  83 */     return this.DAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<String>> getConflictPort() {
/*  88 */     return this.ConflictPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[][] getConflictPortbyPort() {
/*  94 */     return this.ConflictPortbyPort;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLStringPort() {
/*  99 */     EList eList = this.CAT.getPort();
/* 100 */     for (Port port_i : eList) {
/* 101 */       this.LStringPort.add(port_i.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConflictPortbyPorttmp() {
/* 112 */     PetriNet petri_net = (PetriNet)this.CAT.getBehavior();
/* 113 */     EList LState = petri_net.getState();
/* 114 */     for (Object state_i : LState) {
/*     */       
/* 116 */       State state = (State)state_i;
/* 117 */       EList LTransition = state.getOutgoing();
/* 118 */       if (LTransition.size() != 0) {
/*     */         
/* 120 */         Transition transition1 = (Transition)LTransition.get(0);
/* 121 */         PortDefinitionReference PDR1 = (PortDefinitionReference)transition1.getTrigger();
/* 122 */         PortDefinition PD1 = PDR1.getTarget();
/* 123 */         int indexofStringPort1 = this.LStringPort.indexOf(PD1.getName());
/* 124 */         for (Object transition_i : LTransition) {
/*     */           
/* 126 */           Transition transition2 = (Transition)transition_i;
/* 127 */           PortDefinitionReference PDR2 = (PortDefinitionReference)transition2.getTrigger();
/* 128 */           PortDefinition PD2 = PDR2.getTarget();
/* 129 */           int indexofStringPort2 = this.LStringPort.indexOf(PD2.getName());
/* 130 */           if (indexofStringPort2 != -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 136 */             this.ConflictPortbyPort[indexofStringPort1][indexofStringPort2] = true;
/* 137 */             this.ConflictPortbyPort[indexofStringPort2][indexofStringPort1] = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 142 */     addCommutativeConflict();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConflictPortbyPort() {
/* 152 */     PetriNet petri_net = (PetriNet)this.CAT.getBehavior();
/* 153 */     EList LState = petri_net.getState();
/* 154 */     for (Object state_i : LState) {
/*     */       
/* 156 */       State state = (State)state_i;
/* 157 */       EList LTransition = state.getOutgoing();
/* 158 */       if (LTransition.size() != 0)
/*     */       {
/* 160 */         for (int i = 0; i < LTransition.size() - 1; i++) {
/*     */           
/* 162 */           Transition transition1 = (Transition)LTransition.get(i);
/* 163 */           PortDefinitionReference PDR1 = (PortDefinitionReference)transition1.getTrigger();
/* 164 */           PortDefinition PD1 = PDR1.getTarget();
/* 165 */           int indexofStringPort1 = this.LStringPort.indexOf(PD1.getName());
/* 166 */           if (indexofStringPort1 != -1)
/*     */           {
/* 168 */             for (int j = i + 1; j < LTransition.size(); j++) {
/*     */               
/* 170 */               Transition transition2 = (Transition)LTransition.get(j);
/* 171 */               PortDefinitionReference PDR2 = (PortDefinitionReference)transition2.getTrigger();
/* 172 */               PortDefinition PD2 = PDR2.getTarget();
/* 173 */               int indexofStringPort2 = this.LStringPort.indexOf(PD2.getName());
/* 174 */               if (indexofStringPort2 != -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 181 */                 this.ConflictPortbyPort[indexofStringPort1][indexofStringPort2] = true;
/* 182 */                 this.ConflictPortbyPort[indexofStringPort2][indexofStringPort1] = true;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addCommutativeConflict() {
/* 193 */     for (int i = 0; i < this.NumExpPort; i++) {
/* 194 */       for (int j = 0; j < this.NumExpPort; j++) {
/* 195 */         if (this.ConflictPortbyPort[i][j]) {
/* 196 */           this.ConflictPortbyPort[j][i] = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void TransitiveClosureConflictPort() {
/* 204 */     for (int k = 0; k < this.NumExpPort; k++) {
/* 205 */       for (int i = 0; i < this.NumExpPort; i++) {
/* 206 */         for (int j = 0; j < this.NumExpPort; j++)
/* 207 */           this.ConflictPortbyPort[i][j] = this.ConflictPortbyPort[i][j] | this.ConflictPortbyPort[i][k] & this.ConflictPortbyPort[k][j]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setConflictPort() {
/* 213 */     for (int i = 0; i < this.NumExpPort; i++) {
/*     */       
/* 215 */       List<String> LConflictPort = new LinkedList<String>();
/* 216 */       for (int j = 0; j < this.NumExpPort; j++) {
/* 217 */         if (this.ConflictPortbyPort[i][j])
/* 218 */           LConflictPort.add(this.LStringPort.get(j)); 
/* 219 */       }  this.ConflictPort.add(LConflictPort);
/*     */     } 
/* 221 */     this.ConflictPort = DList.MakeListofListUnique1(this.ConflictPort);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void affiche(boolean[][] conflictPortbyPort2) {
/* 227 */     for (int i = 0; i < this.NumExpPort; i++) {
/*     */       
/* 229 */       System.out.println("\n");
/* 230 */       for (int j = 0; j < this.NumExpPort; j++) {
/* 231 */         System.out.print(String.valueOf(this.ConflictPortbyPort[i][j]) + "  ");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean IsConflict(String p1, String p2) {
/* 240 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 242 */       List<String> l = (List<String>)o;
/* 243 */       if (l.contains(p1) && l.contains(p2))
/* 244 */         return true; 
/*     */     } 
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDAT() {
/* 252 */     List<String> LPortGuardVarName = DList.ChangeNameAddString(this.LStringPort, "guardVar");
/* 253 */     this.LPortGuardVar = TransformationFunction.CreateGuardVariable(LPortGuardVarName);
/* 254 */     this.DAT.getVariable().addAll(this.LPortGuardVar);
/* 255 */     CreateConflictPortType();
/* 256 */     PetriNet PNCAT = (PetriNet)this.CAT.getBehavior();
/* 257 */     PetriNet PNDAT = (PetriNet)this.DAT.getBehavior();
/* 258 */     PortDefinition PDInternInit = TransformationFunction.CreatePortDefinition("Intern_Initialize", TransformationFunction.PTSyn, this.DAT);
/* 259 */     List<State> StatesCentralizedParsed = new LinkedList<State>();
/* 260 */     for (Object o : PNCAT.getTransition()) {
/*     */       
/* 262 */       Transition t = (Transition)o;
/* 263 */       EList<State> eList = t.getOrigin();
/* 264 */       State State1 = eList.get(0);
/* 265 */       if (!StatesCentralizedParsed.contains(State1)) {
/*     */         
/* 267 */         List<State> LSD = getCorrespondingState((List<State>)eList);
/* 268 */         setDistributed(LSD, PNDAT, PDInternInit);
/* 269 */         StatesCentralizedParsed.add(State1);
/*     */       } 
/*     */     } 
/*     */     
/* 273 */     Module m = this.CAT.getModule();
/* 274 */     m.getBipType().add(this.DAT);
/*     */   }
/*     */ 
/*     */   
/*     */   private void CreateConflictPortType() {
/* 279 */     String nameport = new String();
/* 280 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 282 */       List<String> SetLStringPort = (List<String>)o;
/* 283 */       List<Variable> LVarPort = new LinkedList<Variable>();
/*     */       
/* 285 */       for (String o1 : SetLStringPort) {
/*     */         
/* 287 */         String portname = o1;
/* 288 */         LVarPort.add(getBooleanVarPort(portname));
/*     */       } 
/* 290 */       nameport = "";
/* 291 */       for (String o1 : SetLStringPort) {
/*     */         
/* 293 */         String portname = o1;
/* 294 */         Port p = getDPort(portname);
/*     */         
/* 296 */         DefinitionBinding definitionBinding = (DefinitionBinding)p.getBinding();
/* 297 */         PortDefinition portDefinition = definitionBinding.getDefinition();
/* 298 */         for (Object o3 : portDefinition.getExposedVariable()) {
/*     */           
/* 300 */           Variable v = (Variable)o3;
/* 301 */           DList.AddUnique(v, LVarPort);
/*     */         } 
/* 303 */         nameport = String.valueOf(nameport) + portname;
/*     */       } 
/* 305 */       List<DataParameter> Ldp = TransformationFunction.CreateDataParamter(LVarPort);
/* 306 */       porttypeitt++;
/* 307 */       PortType porttype = TransformationFunction.CreatePortType(Ldp, "PSType" + porttypeitt);
/* 308 */       Port port = TransformationFunction.CreatePort("S_" + nameport, porttype, this.DAT);
/* 309 */       DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 310 */       PortDefinition pd = db.getDefinition();
/* 311 */       pd.getExposedVariable().addAll(LVarPort);
/* 312 */       this.ClassPort.add(port);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDistributed(List<State> LSD, PetriNet PN, PortDefinition PDInternInit) {
/* 320 */     State FirstState = LSD.get(0);
/* 321 */     List<String> LOutPort = getOutStringExpPort(FirstState);
/* 322 */     List<List<String>> SetConflictPort = getSetofClassofPorts(LOutPort);
/* 323 */     List<String> setLOutPort = DList.SplitListofList(SetConflictPort);
/*     */     
/* 325 */     List<Transition> LTransitionExpPort = getTransitionExpPort(FirstState);
/* 326 */     List<Transition> LTransitionIntPort = getTransitionIntPort(FirstState);
/*     */     
/* 328 */     List<Expression> LGuardExpression = TransformationFunction.CorrespondingGuard(LTransitionExpPort, setLOutPort);
/* 329 */     List<Variable> LVariable = CorrespondingGuardVariable(setLOutPort);
/*     */     
/* 331 */     CompositeAction CAInitializeGuard = TransformationFunction.CreateInitializeCAGuard(LVariable, LGuardExpression);
/* 332 */     State busystate1 = TransformationFunction.CreateState("BusyState1_" + FirstState.getName(), PN);
/* 333 */     State busystate2 = TransformationFunction.CreateState("BusyState2_" + FirstState.getName(), PN);
/* 334 */     List<State> Lbusystate1 = new LinkedList<State>();
/* 335 */     Lbusystate1.add(busystate1);
/* 336 */     TransformationFunction.CreateTransitionLState(PDInternInit, null, (Action)CAInitializeGuard, LSD, Lbusystate1, PN);
/* 337 */     for (Object<String> o : SetConflictPort) {
/*     */       
/* 339 */       List<String> lport = (List<String>)o;
/* 340 */       Expression EguradOr = getOrExpression(lport, FirstState);
/* 341 */       String portnametmp = lport.get(0);
/* 342 */       PortDefinition pd = getClassPortDefofPort(portnametmp);
/* 343 */       TransformationFunction.CreateTransition(pd, EguradOr, null, busystate1, busystate2, PN);
/*     */     } 
/* 345 */     TransformationFunction.RemoveGuardTransition(LTransitionExpPort);
/* 346 */     TransformationFunction.SetTransitionInitialState(LTransitionExpPort, busystate2);
/* 347 */     TransformationFunction.SetTransitionInitialState(LTransitionIntPort, busystate1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Expression getOrExpression(List<String> LPort, State s) {
/* 353 */     boolean firststep = true;
/* 354 */     boolean test = false;
/* 355 */     BinaryExpression binaryExpression1 = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 356 */     BinaryExpression BE = null;
/* 357 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 359 */       Transition t = (Transition)o;
/* 360 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 361 */       if (LPort.contains(pdr.getTarget().getName())) {
/* 362 */         VariableReference variableReference1; Variable v = getBooleanVarPort(pdr.getTarget().getName());
/* 363 */         VariableReference VarRef = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 364 */         VarRef.setTargetVariable(v);
/* 365 */         if (firststep) {
/*     */           
/* 367 */           variableReference1 = VarRef;
/* 368 */           firststep = false;
/*     */           
/*     */           continue;
/*     */         } 
/* 372 */         test = true;
/* 373 */         BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 374 */         BE.setLeftOperand((Expression)variableReference1);
/* 375 */         BE.setRightOperand((Expression)VarRef);
/* 376 */         BinaryOperator BO = BinaryOperator.LOGICAL_OR;
/* 377 */         BE.setOperator(BO);
/* 378 */         binaryExpression1 = BE;
/*     */       } 
/*     */     } 
/*     */     
/* 382 */     if (!test) return (Expression)binaryExpression1; 
/* 383 */     return (Expression)BE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Transition> getTransitionExpPort(State s) {
/* 389 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 390 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 392 */       Transition t = (Transition)o;
/* 393 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 394 */       if (this.LStringPort.indexOf(pdr.getTarget().getName()) != -1)
/* 395 */         LTransition.add(t); 
/*     */     } 
/* 397 */     return LTransition;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Transition> getTransitionIntPort(State s) {
/* 402 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 403 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 405 */       Transition t = (Transition)o;
/* 406 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 407 */       if (this.LStringPort.indexOf(pdr.getTarget().getName()) == -1)
/* 408 */         LTransition.add(t); 
/*     */     } 
/* 410 */     return LTransition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> getOutStringExpPort(State s) {
/* 419 */     List<String> SubLStringPort = new LinkedList<String>();
/* 420 */     for (Object transition_i : s.getOutgoing()) {
/*     */       
/* 422 */       Transition transition = (Transition)transition_i;
/* 423 */       PortDefinitionReference PDR = (PortDefinitionReference)transition.getTrigger();
/* 424 */       String NameofPDR = PDR.getTarget().getName();
/* 425 */       if (this.LStringPort.contains(NameofPDR))
/* 426 */         SubLStringPort.add(NameofPDR); 
/*     */     } 
/* 428 */     return SubLStringPort;
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
/*     */   
/*     */   public List<String> getClassofPort(String port) {
/* 442 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 444 */       List<String> l = (List<String>)o;
/* 445 */       for (String o1 : l) {
/*     */         
/* 447 */         String s = o1;
/* 448 */         if (s.equals(port))
/* 449 */           return l; 
/*     */       } 
/*     */     } 
/* 452 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Port getClassPortofPort(String port) {
/* 457 */     int index = this.ConflictPort.indexOf(getClassofPort(port));
/* 458 */     if (index != -1)
/* 459 */       return this.ClassPort.get(index); 
/* 460 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private PortDefinition getClassPortDefofPort(String port) {
/* 465 */     int index = this.ConflictPort.indexOf(getClassofPort(port));
/* 466 */     if (index != -1) {
/*     */       
/* 468 */       Port p = this.ClassPort.get(index);
/* 469 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 470 */       return db.getDefinition();
/*     */     } 
/* 472 */     return null;
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
/*     */   private List<List<String>> getSetofClassofPorts(List<String> LPort) {
/* 485 */     List<List<String>> SubsetConflictPort = new LinkedList<List<String>>();
/* 486 */     for (String o : LPort) {
/*     */       
/* 488 */       String port = o;
/* 489 */       List<String> ClassPort = getClassofPort(port);
/* 490 */       if (ClassPort != null)
/* 491 */         DList.AddUnique(ClassPort, SubsetConflictPort); 
/*     */     } 
/* 493 */     return SubsetConflictPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getClassPort(String port) {
/* 501 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 503 */       List<String> l = (List<String>)o;
/* 504 */       for (String o1 : l) {
/*     */         
/* 506 */         String s = o1;
/* 507 */         if (s.equals(port))
/* 508 */           return StringToPort(l); 
/*     */       } 
/*     */     } 
/* 511 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<List<Port>> getSetClassofPorts(List<Port> LPort) {
/* 518 */     List<List<Port>> SubsetConflictPort = new LinkedList<List<Port>>();
/* 519 */     for (Port o : LPort) {
/*     */       
/* 521 */       Port port = o;
/* 522 */       List<Port> ClassPort = getClassPort(port.getName());
/* 523 */       if (ClassPort != null)
/* 524 */         DList.AddUnique(ClassPort, SubsetConflictPort); 
/*     */     } 
/* 526 */     return SubsetConflictPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private State getCorrespondingState(String StateName) {
/* 533 */     PetriNet PN = (PetriNet)this.DAT.getBehavior();
/* 534 */     for (Object o : PN.getState()) {
/*     */       
/* 536 */       State s = (State)o;
/* 537 */       if (s.getName().equals(StateName))
/* 538 */         return s; 
/*     */     } 
/* 540 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<State> getCorrespondingState(List<State> LState) {
/* 545 */     PetriNet PN = (PetriNet)this.DAT.getBehavior();
/* 546 */     List<State> LStateOutput = new LinkedList<State>();
/* 547 */     for (State o1 : LState) {
/*     */       
/* 549 */       State state1 = o1;
/* 550 */       for (Object o2 : PN.getState()) {
/*     */         
/* 552 */         State state2 = (State)o2;
/* 553 */         if (state2.getName().equals(state1.getName())) {
/*     */           
/* 555 */           LStateOutput.add(state2);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 560 */     return LStateOutput;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getDPort(String PortName) {
/* 568 */     for (Object o : this.DAT.getPort()) {
/*     */       
/* 570 */       Port p = (Port)o;
/* 571 */       if (p.getName().equals(PortName))
/* 572 */         return p; 
/*     */     } 
/* 574 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getCPort(String PortName) {
/* 579 */     for (Object o : this.CAT.getPort()) {
/*     */       
/* 581 */       Port p = (Port)o;
/* 582 */       if (p.getName().equals(PortName))
/* 583 */         return p; 
/*     */     } 
/* 585 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Port> StringToPort(List<String> l) {
/* 590 */     List<Port> LPort = new LinkedList();
/* 591 */     for (String o : l) {
/*     */       
/* 593 */       String nameport = o;
/* 594 */       LPort.add(getCPort(nameport));
/*     */     } 
/* 596 */     return LPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getBooleanVarPort(String port) {
/* 602 */     return this.LPortGuardVar.get(this.LStringPort.indexOf(port));
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Variable> CorrespondingGuardVariable(List<String> outPort) {
/* 607 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 608 */     for (String o : outPort) {
/*     */       
/* 610 */       String s = o;
/* 611 */       Variable v = getBooleanVarPort(s);
/* 612 */       LVariable.add(v);
/*     */     } 
/* 614 */     return LVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getClassPort() {
/* 620 */     return this.ClassPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getCentPort() {
/* 625 */     return this.LCentPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getClassSetPort(List<List<Port>> LPort) {
/* 632 */     List<Port> LClassPort = new LinkedList<Port>();
/* 633 */     for (Object<Port> o : LPort) {
/*     */       
/* 635 */       List<Port> lport = (List<Port>)o;
/* 636 */       LClassPort.add(getClassPortofPort(((Port)lport.get(0)).getName()));
/*     */     } 
/* 638 */     return LClassPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Variable> getVariable(List<List<Port>> LConfPort) {
/* 644 */     List<Variable> LVar = new LinkedList<Variable>();
/* 645 */     for (Object<Port> o : LConfPort) {
/*     */       
/* 647 */       List<Port> LPort = (List<Port>)o;
/* 648 */       for (Port o1 : LPort) {
/*     */         
/* 650 */         Port p = o1;
/* 651 */         DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 652 */         EList eList = db.getDefinition().getExposedVariable();
/* 653 */         DList.AddListUnique((List)eList, LVar);
/*     */       } 
/*     */     } 
/* 656 */     return LVar;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\DAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */