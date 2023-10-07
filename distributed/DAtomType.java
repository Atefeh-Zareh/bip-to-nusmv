/*     */ package distributed;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
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
/*     */ 
/*     */ public class DAtomType
/*     */ {
/*     */   private AtomType CAT;
/*     */   private AtomType DAT;
/*  39 */   private List<List<String>> ConflictPort = new LinkedList<List<String>>();
/*  40 */   private List<String> LStringPort = new LinkedList<String>();
/*  41 */   private List<Port> LCentPort = new LinkedList<Port>();
/*     */   private boolean[][] ConflictPortbyPort;
/*     */   private int NumExpPort;
/*  44 */   private List<Variable> LPortGuardVar = new LinkedList<Variable>();
/*  45 */   private List<Port> ClassPort = new LinkedList<Port>();
/*     */   private Module module;
/*  47 */   static int porttypeitt = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DAtomType(AtomType CAT) {
/*  54 */     this.CAT = CAT;
/*  55 */     this.NumExpPort = CAT.getPort().size();
/*  56 */     this.ConflictPortbyPort = new boolean[this.NumExpPort][this.NumExpPort];
/*  57 */     this.module = CAT.getModule();
/*  58 */     setLStringPort();
/*  59 */     resetConflictPortbyPort();
/*  60 */     setConflictPortbyPort();
/*  61 */     TransitiveClosureConflictPort();
/*  62 */     setConflictPort();
/*  63 */     this.DAT = BehaviorsFactory.eINSTANCE.createAtomType();
/*  64 */     this.DAT = (AtomType)EcoreUtil.copy((EObject)CAT);
/*  65 */     this.DAT.setName(String.valueOf(CAT.getName()) + "_Distributed");
/*  66 */     setDAT();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetConflictPortbyPort() {
/*  72 */     for (int i = 0; i < this.NumExpPort; i++) {
/*  73 */       this.ConflictPortbyPort[i][i] = true;
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
/* 100 */     for (Object port_i : eList) {
/*     */       
/* 102 */       Port p = (Port)port_i;
/* 103 */       this.LStringPort.add(p.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConflictPortbyPort() {
/* 115 */     PetriNet petri_net = (PetriNet)this.CAT.getBehavior();
/* 116 */     EList LState = petri_net.getState();
/* 117 */     for (Object state_i : LState) {
/*     */       
/* 119 */       State state = (State)state_i;
/* 120 */       EList LTransition = state.getOutgoing();
/* 121 */       if (LTransition.size() != 0)
/*     */       {
/* 123 */         for (int i = 0; i < LTransition.size() - 1; i++) {
/*     */           
/* 125 */           Transition transition1 = (Transition)LTransition.get(i);
/* 126 */           PortDefinitionReference PDR1 = (PortDefinitionReference)transition1.getTrigger();
/* 127 */           PortDefinition PD1 = PDR1.getTarget();
/* 128 */           int indexofStringPort1 = this.LStringPort.indexOf(PD1.getName());
/* 129 */           if (indexofStringPort1 != -1)
/*     */           {
/* 131 */             for (int j = i + 1; j < LTransition.size(); j++) {
/*     */               
/* 133 */               Transition transition2 = (Transition)LTransition.get(j);
/* 134 */               PortDefinitionReference PDR2 = (PortDefinitionReference)transition2.getTrigger();
/* 135 */               PortDefinition PD2 = PDR2.getTarget();
/* 136 */               int indexofStringPort2 = this.LStringPort.indexOf(PD2.getName());
/* 137 */               if (indexofStringPort2 != -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 144 */                 this.ConflictPortbyPort[indexofStringPort1][indexofStringPort2] = true;
/* 145 */                 this.ConflictPortbyPort[indexofStringPort2][indexofStringPort1] = true;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void TransitiveClosureConflictPort() {
/* 161 */     for (int k = 0; k < this.NumExpPort; k++) {
/* 162 */       for (int i = 0; i < this.NumExpPort; i++) {
/* 163 */         for (int j = 0; j < this.NumExpPort; j++)
/* 164 */           this.ConflictPortbyPort[i][j] = this.ConflictPortbyPort[i][j] | this.ConflictPortbyPort[i][k] & this.ConflictPortbyPort[k][j]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setConflictPort() {
/* 170 */     for (int i = 0; i < this.NumExpPort; i++) {
/*     */       
/* 172 */       List<String> LConflictPort = new LinkedList<String>();
/* 173 */       for (int j = 0; j < this.NumExpPort; j++) {
/* 174 */         if (this.ConflictPortbyPort[i][j])
/* 175 */           LConflictPort.add(this.LStringPort.get(j)); 
/* 176 */       }  this.ConflictPort.add(LConflictPort);
/*     */     } 
/* 178 */     this.ConflictPort = DList.MakeListofListUnique1(this.ConflictPort);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void affiche(boolean[][] conflictPortbyPort2) {
/* 184 */     for (int i = 0; i < this.NumExpPort; i++) {
/*     */       
/* 186 */       System.out.println("\n");
/* 187 */       for (int j = 0; j < this.NumExpPort; j++) {
/* 188 */         System.out.print(String.valueOf(this.ConflictPortbyPort[i][j]) + "  ");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean IsConflict(String p1, String p2) {
/* 197 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 199 */       List<String> l = (List<String>)o;
/* 200 */       if (l.contains(p1) && l.contains(p2))
/* 201 */         return true; 
/*     */     } 
/* 203 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDAT() {
/* 209 */     List<String> LPortGuardVarName = DList.ChangeNameAddString(this.LStringPort, "guardVar");
/* 210 */     this.LPortGuardVar = TransformationFunction.CreateGuardVariable(LPortGuardVarName);
/* 211 */     this.DAT.getVariable().addAll(this.LPortGuardVar);
/* 212 */     CreateConflictPortType();
/* 213 */     PetriNet PNCAT = (PetriNet)this.CAT.getBehavior();
/* 214 */     PetriNet PNDAT = (PetriNet)this.DAT.getBehavior();
/* 215 */     PortDefinition PDInternInit = TransformationFunction.CreatePortDefinition("Intern_Initialize", DCompoundType.InternPortType, this.DAT);
/* 216 */     for (Object o : PNCAT.getState()) {
/*     */       
/* 218 */       State sc = (State)o;
/* 219 */       State sd = getCorrespondingState(sc.getName());
/* 220 */       setDistributed(sd, PNDAT, PDInternInit);
/*     */     } 
/*     */     
/* 223 */     Module m = this.CAT.getModule();
/* 224 */     m.getBipType().add(this.DAT);
/*     */   }
/*     */ 
/*     */   
/*     */   private void CreateConflictPortType() {
/* 229 */     String nameport = new String();
/* 230 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 232 */       List<String> SetLStringPort = (List<String>)o;
/* 233 */       List<Variable> LVarPort = new LinkedList<Variable>();
/*     */       
/* 235 */       for (String o1 : SetLStringPort) {
/*     */         
/* 237 */         String portname = o1;
/* 238 */         LVarPort.add(getBooleanVarPort(portname));
/*     */       } 
/* 240 */       nameport = "";
/* 241 */       for (String o1 : SetLStringPort) {
/*     */         
/* 243 */         String portname = o1;
/* 244 */         Port p = getDPort(portname);
/*     */         
/* 246 */         DefinitionBinding definitionBinding = (DefinitionBinding)p.getBinding();
/* 247 */         PortDefinition portDefinition = definitionBinding.getDefinition();
/* 248 */         for (Object o3 : portDefinition.getExposedVariable()) {
/*     */           
/* 250 */           Variable v = (Variable)o3;
/* 251 */           DList.AddUnique(v, LVarPort);
/*     */         } 
/* 253 */         nameport = String.valueOf(nameport) + portname;
/*     */       } 
/* 255 */       List<DataParameter> Ldp = TransformationFunction.CreateDataParameter(LVarPort);
/* 256 */       porttypeitt++;
/* 257 */       PortType porttype = TransformationFunction.CreatePortType(Ldp, "PSType" + porttypeitt, this.module);
/* 258 */       Port port = TransformationFunction.CreatePort("S_" + nameport, porttype, this.DAT);
/* 259 */       DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 260 */       PortDefinition pd = db.getDefinition();
/* 261 */       pd.getExposedVariable().addAll(LVarPort);
/* 262 */       this.ClassPort.add(port);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDistributed(State sd, PetriNet PN, PortDefinition PDInternInit) {
/* 270 */     List<String> LOutPort = TransformationFunction.getOutStringExpPort(sd, this.LStringPort);
/* 271 */     List<List<String>> SetConflictPort = getSetofClassofPorts(LOutPort);
/* 272 */     List<String> setLOutPort = DList.SplitListofList(SetConflictPort);
/*     */     
/* 274 */     List<Transition> LTransitionExpPort = TransformationFunction.getTransitionExpPort(sd, this.LStringPort);
/* 275 */     List<Transition> LTransitionIntPort = TransformationFunction.getTransitionIntPort(sd, this.LStringPort);
/*     */     
/* 277 */     List<Expression> LGuardExpression = TransformationFunction.CorrespondingGuard(LTransitionExpPort, setLOutPort);
/* 278 */     List<Variable> LVariable = CorrespondingGuardVariable(setLOutPort);
/*     */     
/* 280 */     CompositeAction CAInitializeGuard = TransformationFunction.CreateInitializeCAGuard(LVariable, LGuardExpression);
/* 281 */     State busystate1 = TransformationFunction.CreateState("BusyState1_" + sd.getName(), PN);
/* 282 */     State busystate2 = TransformationFunction.CreateState("BusyState2_" + sd.getName(), PN);
/*     */     
/* 284 */     TransformationFunction.CreateTransition(PDInternInit, null, (Action)CAInitializeGuard, sd, busystate1, PN);
/* 285 */     for (Object<String> o : SetConflictPort) {
/*     */       
/* 287 */       List<String> lport = (List<String>)o;
/* 288 */       Expression EguradOr = getOrExpression(lport, sd);
/* 289 */       String portnametmp = lport.get(0);
/* 290 */       PortDefinition pd = getClassPortDefofPort(portnametmp);
/* 291 */       TransformationFunction.CreateTransition(pd, EguradOr, null, busystate1, busystate2, PN);
/*     */     } 
/* 293 */     TransformationFunction.RemoveGuardTransition(LTransitionExpPort);
/* 294 */     TransformationFunction.CopyTransitionFromState(LTransitionExpPort, sd, busystate2);
/* 295 */     TransformationFunction.CopyTransitionFromState(LTransitionIntPort, sd, busystate1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Expression getOrExpression(List<String> LPort, State s) {
/* 301 */     boolean firststep = true;
/* 302 */     boolean test = false;
/* 303 */     BinaryExpression binaryExpression1 = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 304 */     BinaryExpression BE = null;
/* 305 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 307 */       Transition t = (Transition)o;
/* 308 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 309 */       if (LPort.contains(pdr.getTarget().getName())) {
/* 310 */         VariableReference variableReference1; Variable v = getBooleanVarPort(pdr.getTarget().getName());
/* 311 */         VariableReference VarRef = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 312 */         VarRef.setTargetVariable(v);
/* 313 */         if (firststep) {
/*     */           
/* 315 */           variableReference1 = VarRef;
/* 316 */           firststep = false;
/*     */           
/*     */           continue;
/*     */         } 
/* 320 */         test = true;
/* 321 */         BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 322 */         BE.setLeftOperand((Expression)variableReference1);
/* 323 */         BE.setRightOperand((Expression)VarRef);
/* 324 */         BinaryOperator BO = BinaryOperator.LOGICAL_OR;
/* 325 */         BE.setOperator(BO);
/* 326 */         binaryExpression1 = BE;
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     if (!test) return (Expression)binaryExpression1; 
/* 331 */     return (Expression)BE;
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
/* 345 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 347 */       List<String> l = (List<String>)o;
/* 348 */       for (String o1 : l) {
/*     */         
/* 350 */         String s = o1;
/* 351 */         if (s.equals(port))
/* 352 */           return l; 
/*     */       } 
/*     */     } 
/* 355 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Port getClassPortofPort(String port) {
/* 360 */     int index = this.ConflictPort.indexOf(getClassofPort(port));
/* 361 */     if (index != -1)
/* 362 */       return this.ClassPort.get(index); 
/* 363 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private PortDefinition getClassPortDefofPort(String port) {
/* 368 */     int index = this.ConflictPort.indexOf(getClassofPort(port));
/* 369 */     if (index != -1) {
/*     */       
/* 371 */       Port p = this.ClassPort.get(index);
/* 372 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 373 */       return db.getDefinition();
/*     */     } 
/* 375 */     return null;
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
/* 388 */     List<List<String>> SubsetConflictPort = new LinkedList<List<String>>();
/* 389 */     for (String o : LPort) {
/*     */       
/* 391 */       String port = o;
/* 392 */       List<String> ClassPort = getClassofPort(port);
/* 393 */       if (ClassPort != null)
/* 394 */         DList.AddUnique(ClassPort, SubsetConflictPort); 
/*     */     } 
/* 396 */     return SubsetConflictPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getClassPort(String port) {
/* 404 */     for (Object<String> o : this.ConflictPort) {
/*     */       
/* 406 */       List<String> l = (List<String>)o;
/* 407 */       for (String o1 : l) {
/*     */         
/* 409 */         String s = o1;
/* 410 */         if (s.equals(port))
/* 411 */           return StringToPort(l); 
/*     */       } 
/*     */     } 
/* 414 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<List<Port>> getSetClassofPorts(List<Port> LPort) {
/* 421 */     List<List<Port>> SubsetConflictPort = new LinkedList<List<Port>>();
/* 422 */     for (Port o : LPort) {
/*     */       
/* 424 */       Port port = o;
/* 425 */       List<Port> ClassPort = getClassPort(port.getName());
/* 426 */       if (ClassPort != null)
/* 427 */         DList.AddUnique(ClassPort, SubsetConflictPort); 
/*     */     } 
/* 429 */     return SubsetConflictPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private State getCorrespondingState(String StateName) {
/* 435 */     PetriNet PN = (PetriNet)this.DAT.getBehavior();
/* 436 */     for (Object o : PN.getState()) {
/*     */       
/* 438 */       State s = (State)o;
/* 439 */       if (s.getName().equals(StateName))
/* 440 */         return s; 
/*     */     } 
/* 442 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getDPort(String PortName) {
/* 450 */     for (Object o : this.DAT.getPort()) {
/*     */       
/* 452 */       Port p = (Port)o;
/* 453 */       if (p.getName().equals(PortName))
/* 454 */         return p; 
/*     */     } 
/* 456 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getCPort(String PortName) {
/* 461 */     for (Object o : this.CAT.getPort()) {
/*     */       
/* 463 */       Port p = (Port)o;
/* 464 */       if (p.getName().equals(PortName))
/* 465 */         return p; 
/*     */     } 
/* 467 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Port> StringToPort(List<String> l) {
/* 472 */     List<Port> LPort = new LinkedList();
/* 473 */     for (String o : l) {
/*     */       
/* 475 */       String nameport = o;
/* 476 */       LPort.add(getCPort(nameport));
/*     */     } 
/* 478 */     return LPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getBooleanVarPort(String port) {
/* 484 */     return this.LPortGuardVar.get(this.LStringPort.indexOf(port));
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Variable> CorrespondingGuardVariable(List<String> outPort) {
/* 489 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 490 */     for (String o : outPort) {
/*     */       
/* 492 */       String s = o;
/* 493 */       Variable v = getBooleanVarPort(s);
/* 494 */       LVariable.add(v);
/*     */     } 
/* 496 */     return LVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getClassPort() {
/* 502 */     return this.ClassPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getCentPort() {
/* 507 */     return this.LCentPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getClassSetPort(List<List<Port>> LPort) {
/* 514 */     List<Port> LClassPort = new LinkedList<Port>();
/* 515 */     for (Object<Port> o : LPort) {
/*     */       
/* 517 */       List<Port> lport = (List<Port>)o;
/* 518 */       LClassPort.add(getClassPortofPort(((Port)lport.get(0)).getName()));
/*     */     } 
/* 520 */     return LClassPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Variable> getVariable(List<List<Port>> LConfPort) {
/* 526 */     List<Variable> LVar = new LinkedList<Variable>();
/* 527 */     for (Object<Port> o : LConfPort) {
/*     */       
/* 529 */       List<Port> LPort = (List<Port>)o;
/* 530 */       for (Port o1 : LPort) {
/*     */         
/* 532 */         Port p = o1;
/* 533 */         DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 534 */         EList eList = db.getDefinition().getExposedVariable();
/* 535 */         DList.AddListUnique((List)eList, LVar);
/*     */       } 
/*     */     } 
/* 538 */     return LVar;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\DAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */