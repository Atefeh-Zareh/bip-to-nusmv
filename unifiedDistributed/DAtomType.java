/*     */ package unifiedDistributed;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import BipInfo.ComponentInfo;
/*     */ import distributed.DList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DAtomType
/*     */ {
/*     */   private Component component;
/*     */   private ComponentInfo componentInfo;
/*     */   private AtomType DAT;
/*     */   private AtomType CAT;
/*  42 */   private List<Port> LPort = new LinkedList<Port>();
/*  43 */   private Map<Port, Port> Map_PortCent_PortDist = new HashMap<Port, Port>();
/*  44 */   private List<String> LNamePort = new LinkedList<String>();
/*  45 */   private List<PortDefinition> LPortDefinition = new LinkedList<PortDefinition>();
/*  46 */   private List<List<Port>> LLDataPort = new LinkedList<List<Port>>();
/*  47 */   private List<List<String>> LLIPName = new LinkedList<List<String>>();
/*     */   private PortDefinition InternSynPort;
/*  49 */   private Map<Port, String> Map_OfferPort_IPName = new HashMap<Port, String>();
/*  50 */   private Map<String, Port> Map_IPName_OfferPort = new HashMap<String, Port>();
/*  51 */   private List<String> LIPName = new LinkedList<String>();
/*  52 */   private List<List<Variable>> LLVariable = new LinkedList<List<Variable>>();
/*  53 */   private Map<Port, Variable> Map_Port_GuardVar = new HashMap<Port, Variable>();
/*  54 */   private List<Variable> LGuardVar = new LinkedList<Variable>();
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable n_Variable;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer ComponentID;
/*     */ 
/*     */   
/*     */   private Component DATInstance;
/*     */ 
/*     */   
/*     */   private static String BlockTimeStart;
/*     */ 
/*     */   
/*     */   private static String BlockTimeEnd;
/*     */ 
/*     */   
/*     */   private static String BlockTimeSeconds;
/*     */ 
/*     */   
/*     */   private static String NbOfBlock;
/*     */ 
/*     */ 
/*     */   
/*     */   DAtomType(Component component, Integer ComponentID) {
/*  82 */     this.component = component;
/*  83 */     this.ComponentID = ComponentID;
/*  84 */     this.componentInfo = new ComponentInfo(this.component);
/*     */     
/*  86 */     if (!(component.getType() instanceof AtomType)) {
/*  87 */       System.out.println("System should be flat. \n Component " + component.getName() + " is not atomic");
/*  88 */       System.exit(0);
/*     */     } 
/*     */     
/*  91 */     this.CAT = (AtomType)component.getType();
/*  92 */     this.DAT = BehaviorsFactory.eINSTANCE.createAtomType();
/*  93 */     this.DAT = (AtomType)EcoreUtil.copy((EObject)this.CAT);
/*  94 */     this.DAT.setName(String.valueOf(component.getName()) + "_Distributed");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     setDAT();
/* 102 */     this.CAT.getModule().getBipType().add(this.DAT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void InitializeDebugInformation(String Name) {
/* 109 */     BlockTimeStart = "COMP_BTStart_" + Name;
/* 110 */     BlockTimeEnd = "COMP_BTEnd_" + Name;
/* 111 */     BlockTimeSeconds = "COMP_BTSeconds_" + Name;
/* 112 */     NbOfBlock = "COMP_NumborOfBlock_" + Name;
/* 113 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n struct timespec " + BlockTimeStart + ";");
/* 114 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n struct timespec " + BlockTimeEnd + ";");
/* 115 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n double " + BlockTimeSeconds + " = 0 ;");
/* 116 */     Test.Header.setBody(String.valueOf(Test.Header.getBody()) + "\n int " + NbOfBlock + " = 0 ;\n");
/* 117 */     Test.PrintDebug = String.valueOf(Test.PrintDebug) + "\n\t if(" + Test.AtomTypeName + " == \"" + Name + "\") {\n";
/* 118 */     Test.PrintDebug = String.valueOf(Test.PrintDebug) + "\t \t fprintf( " + Test.FileName + " , \"Component " + Name + " : \\n " + BlockTimeSeconds + " = %f \\n " + NbOfBlock + " = %d \\n\" , " + BlockTimeSeconds + "," + NbOfBlock + ") ;";
/* 119 */     Test.PrintDebug = String.valueOf(Test.PrintDebug) + "\n\t }";
/*     */   }
/*     */   
/*     */   private void setDAT() {
/* 123 */     setLIPName();
/* 124 */     ResetLLVariable();
/* 125 */     setInternSynPort();
/* 126 */     setn_Variable();
/* 127 */     setLPort();
/* 128 */     setMap_Port_GuardVar();
/* 129 */     setMap_OfferPort_IPName();
/* 130 */     setMap_Port_DataPort();
/* 131 */     setBehavior();
/*     */   }
/*     */ 
/*     */   
/*     */   private void ResetLLVariable() {
/* 136 */     for (int i = 0; i < this.LIPName.size(); i++) {
/* 137 */       this.LLVariable.add(new LinkedList<Variable>());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBehavior() {
/* 143 */     PetriNet DistributedPN = (PetriNet)this.DAT.getBehavior();
/* 144 */     List<State> LState = new LinkedList<State>((Collection<? extends State>)DistributedPN.getState());
/* 145 */     for (State o : LState) {
/* 146 */       State s = o;
/* 147 */       if (s.getOutgoing().size() != 0) {
/* 148 */         setDistributeBehavior(s, DistributedPN);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDistributeBehavior(State s, PetriNet distributedPN) {
/* 155 */     List<Transition> LTransitionExpPort = TransformationFunction.getTransitionExpPort(s, this.LNamePort);
/* 156 */     List<Transition> LTransitionIntPort = TransformationFunction.getTransitionIntPort(s, this.LNamePort);
/* 157 */     List<String> LOutPort = TransformationFunction.getOutStringExpPort(s, this.LNamePort);
/* 158 */     List<Expression> LGuardExpression = TransformationFunction.CorrespondingGuard(LTransitionExpPort, this.LNamePort);
/* 159 */     CompositeAction CAInitializeGuard = TransformationFunction.CreateInitializeCAGuard(this.LGuardVar, LGuardExpression);
/* 160 */     AssignmentAction AA = TransformationFunction.CreateAddAssignmentAction(this.n_Variable, Integer.valueOf(1));
/* 161 */     CAInitializeGuard.getContent().add(AA);
/* 162 */     State busystate1 = TransformationFunction.CreateState("BusyState1_" + s.getName(), distributedPN);
/* 163 */     State busystate2 = TransformationFunction.CreateState("BusyState2_" + s.getName(), distributedPN);
/* 164 */     TransformationFunction.CreateTransition(this.InternSynPort, null, (Action)CAInitializeGuard, s, busystate1, distributedPN);
/* 165 */     TransformationFunction.CreateTransition(this.InternSynPort, TransformationFunction.getOrExpression(this.LGuardVar), null, busystate1, busystate2, distributedPN);
/*     */ 
/*     */     
/* 168 */     int i = 3;
/* 169 */     State busystatefrom = busystate2;
/* 170 */     for (Object<Port> o : this.LLDataPort) {
/* 171 */       List<Port> LDataPort = (List<Port>)o;
/* 172 */       for (Port o1 : LDataPort) {
/* 173 */         Port DataPort = o1;
/* 174 */         Port port = this.LPort.get(this.LLDataPort.indexOf(o));
/* 175 */         State busystate = TransformationFunction.CreateState("BusyState" + i++ + "_" + s.getName(), distributedPN);
/* 176 */         DefinitionBinding db = (DefinitionBinding)DataPort.getBinding();
/* 177 */         VariableReference variableReference = TransformationFunction.CreateVariableReference(this.Map_Port_GuardVar.get(port));
/* 178 */         TransformationFunction.CreateTransition(db.getDefinition(), (Expression)variableReference, null, busystatefrom, busystate, distributedPN);
/*     */         
/* 180 */         Expression InverseGuardPort = TransformationFunction.getInverseGuard(this.Map_Port_GuardVar.get(port));
/* 181 */         TransformationFunction.CreateTransition(this.InternSynPort, InverseGuardPort, null, busystatefrom, busystate, distributedPN);
/* 182 */         busystatefrom = busystate;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 187 */     List<String> LIPName = getLIPName(LOutPort);
/* 188 */     for (String o : LIPName) {
/* 189 */       String IPName = o;
/* 190 */       Port OfferPort = this.Map_IPName_OfferPort.get(IPName);
/* 191 */       State busystate = TransformationFunction.CreateState("BusyState" + i++ + "_" + s.getName(), distributedPN);
/* 192 */       DefinitionBinding db = (DefinitionBinding)OfferPort.getBinding();
/* 193 */       TransformationFunction.CreateTransition(db.getDefinition(), null, null, busystatefrom, busystate, distributedPN);
/* 194 */       busystatefrom = busystate;
/*     */     } 
/*     */ 
/*     */     
/* 198 */     TransformationFunction.RemoveGuardTransition(LTransitionExpPort);
/* 199 */     TransformationFunction.CopyTransitionFromState(LTransitionExpPort, s, busystatefrom);
/* 200 */     TransformationFunction.CopyTransitionFromState(LTransitionIntPort, s, busystate1);
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
/*     */   private List<String> getLIPName(List<String> LnamePort) {
/* 231 */     List<String> LIPName = new LinkedList<String>();
/* 232 */     for (String o : LnamePort) {
/* 233 */       String NamePort = o;
/* 234 */       int indexofPort = this.LNamePort.indexOf(NamePort);
/* 235 */       this.LLIPName.get(indexofPort);
/* 236 */       DList.AddListUnique(this.LLIPName.get(indexofPort), LIPName);
/*     */     } 
/* 238 */     return LIPName;
/*     */   }
/*     */   
/*     */   private void setLIPName() {
/* 242 */     List<Connector> LConnector = this.componentInfo.getLInteractionOfComponent();
/* 243 */     this.LIPName = TOP.getInteractionProtocolName(LConnector);
/*     */   }
/*     */   
/*     */   private void setInternSynPort() {
/* 247 */     PortType SynPortType = TOP.getInternPortType();
/* 248 */     this.InternSynPort = TransformationFunction.CreatePortDefinition("InternPortSyn", SynPortType, this.DAT);
/*     */   }
/*     */   
/*     */   private void setn_Variable() {
/* 252 */     this.n_Variable = TransformationFunction.CreateIntVariable("n_Variable", 0);
/* 253 */     this.DAT.getVariable().add(this.n_Variable);
/*     */   }
/*     */   
/*     */   private void setLPort() {
/* 257 */     this.LPort.addAll((Collection<? extends Port>)this.DAT.getPort());
/* 258 */     this.LPortDefinition.addAll((Collection<? extends PortDefinition>)this.DAT.getPortDefinition());
/* 259 */     for (Object o : this.DAT.getPort()) {
/* 260 */       Port p = (Port)o;
/* 261 */       this.LNamePort.add(p.getName());
/* 262 */       int index = this.LPort.indexOf(o);
/* 263 */       this.Map_PortCent_PortDist.put((Port)this.CAT.getPort().get(index), p);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMap_Port_DataPort() {
/* 268 */     for (Port o : this.LPort) {
/* 269 */       Port port = o;
/* 270 */       Port portCentrilized = this.componentInfo.getPort(port.getName());
/* 271 */       List<Connector> LConnector = this.componentInfo.getLInteractionOfPort(portCentrilized);
/* 272 */       List<String> LIPNameForPort = TOP.getInteractionProtocolName(LConnector);
/* 273 */       List<Port> LcopyPort = new LinkedList<Port>();
/* 274 */       for (String o1 : LIPNameForPort) {
/* 275 */         String IPName = o1;
/* 276 */         String NewName = "DataPort_" + port.getName() + "_" + IPName;
/* 277 */         Port copyport = TransformationFunction.getCopy(port, NewName);
/* 278 */         LcopyPort.add(copyport);
/* 279 */         PortDefinition copypd = ((DefinitionBinding)copyport.getBinding()).getDefinition();
/* 280 */         this.DAT.getPort().add(copyport);
/* 281 */         this.DAT.getPortDefinition().add(copypd);
/* 282 */         setLLVariable(this.LIPName.indexOf(o1), port);
/*     */       } 
/* 284 */       this.LLIPName.add(LIPNameForPort);
/* 285 */       this.LLDataPort.add(LcopyPort);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLLVariable(int IndexIPName, Port p) {
/* 292 */     List<Variable> LVariable = this.LLVariable.get(IndexIPName);
/* 293 */     PortDefinition pd = ((DefinitionBinding)p.getBinding()).getDefinition();
/* 294 */     DList.AddListUnique((List)pd.getExposedVariable(), LVariable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Port_GuardVar() {
/* 302 */     for (Port o : this.LPort) {
/*     */       
/* 304 */       Port port = o;
/* 305 */       Variable GuardVar = TransformationFunction.CreateGuardVariable("GuardPort_" + port.getName(), false);
/* 306 */       this.Map_Port_GuardVar.put(port, GuardVar);
/* 307 */       this.DAT.getVariable().add(GuardVar);
/* 308 */       this.LGuardVar.add(GuardVar);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMap_OfferPort_IPName() {
/* 313 */     PortType OfferPortType = TOP.getOfferPortType(Integer.valueOf(this.LPort.size()));
/* 314 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 315 */     LVariable.add(this.n_Variable);
/* 316 */     LVariable.addAll(this.LGuardVar);
/* 317 */     for (String o : this.LIPName) {
/* 318 */       String IPName = o;
/* 319 */       Port OfferPort = TransformationFunction.CreatePort("Offer_Port_" + IPName, OfferPortType, this.DAT, LVariable);
/* 320 */       this.Map_OfferPort_IPName.put(OfferPort, IPName);
/* 321 */       this.Map_IPName_OfferPort.put(IPName, OfferPort);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getDAT() {
/* 327 */     return this.DAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 332 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public Variable getn_Variable() {
/* 337 */     return this.n_Variable;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> LExportPort() {
/* 342 */     return this.LPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getPortDist(Port PortCent) {
/* 347 */     return this.Map_PortCent_PortDist.get(PortCent);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getVariableForIP(String IPName) {
/* 352 */     return this.LLVariable.get(this.LIPName.indexOf(IPName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getDataPort(String IPName, Port portdist) {
/* 362 */     int indexPortDist = this.LPort.indexOf(portdist);
/* 363 */     List<Port> LDataPort = this.LLDataPort.get(indexPortDist);
/* 364 */     for (int i = 0; i < LDataPort.size(); i++) {
/*     */       
/* 366 */       List<String> LIPName = this.LLIPName.get(indexPortDist);
/* 367 */       for (String o1 : LIPName) {
/* 368 */         String IPNametoCompare = o1;
/* 369 */         if (IPNametoCompare.equals(IPName))
/* 370 */           return LDataPort.get(LIPName.indexOf(o1)); 
/*     */       } 
/*     */     } 
/* 373 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getOfferPort(String IPName) {
/* 383 */     return this.Map_IPName_OfferPort.get(IPName);
/*     */   }
/*     */   
/*     */   public Variable getGuardVar(Port p) {
/* 387 */     return this.Map_Port_GuardVar.get(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getComponentID() {
/* 396 */     return this.ComponentID;
/*     */   }
/*     */   
/*     */   public void setComponentInstance(CompoundType compoundType) {
/* 400 */     this.DATInstance = TransformationFunction.CreateComponent(String.valueOf(this.DAT.getName()) + "_Instance", (ComponentType)this.DAT, compoundType, (List)this.component.getActualData());
/*     */   }
/*     */   
/*     */   public Component getDATInstance() {
/* 404 */     return this.DATInstance;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\DAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */