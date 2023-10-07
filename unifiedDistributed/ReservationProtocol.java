/*     */ package unifiedDistributed;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ 
/*     */ public class ReservationProtocol {
/*  21 */   protected List<Connector> LInteraction = new LinkedList<Connector>();
/*     */   protected String IPName;
/*  23 */   protected List<List<Component>> LLComponentReserve = new LinkedList<List<Component>>();
/*  24 */   protected List<List<Variable>> LLComponent_nVar = new LinkedList<List<Variable>>();
/*     */ 
/*     */   
/*  27 */   protected Map<Component, Variable> Map_Comp_NVar = new HashMap<Component, Variable>();
/*  28 */   protected Map<Connector, Port> Map_Conn_Reserve = new HashMap<Connector, Port>();
/*  29 */   protected Map<Connector, Variable> Map_Conn_IndexCompFail = new HashMap<Connector, Variable>();
/*     */   
/*  31 */   protected Map<Connector, Port> Map_Conn_PortOk = new HashMap<Connector, Port>();
/*  32 */   protected Map<Connector, Port> Map_Conn_PortFail = new HashMap<Connector, Port>();
/*     */   protected AtomType AtomTypeRP;
/*     */   protected PetriNet PetriNetRP;
/*  35 */   protected static Integer RPName = Integer.valueOf(0);
/*     */ 
/*     */   
/*     */   protected PortDefinition InterPort;
/*     */   
/*     */   protected Component ComponentRPInstance;
/*     */ 
/*     */   
/*     */   public ReservationProtocol(List<Connector> LInteraction, String IPName) {
/*  44 */     this.LInteraction = LInteraction;
/*  45 */     this.IPName = IPName;
/*  46 */     setAtomTypeRP();
/*  47 */     setInternSynPort();
/*  48 */     setLLComponentReserve();
/*  49 */     setMap_Comp_Var();
/*  50 */     setOKFAILPorts();
/*  51 */     setMap_ConnReserve();
/*  52 */     TOP.module.getBipType().add(this.AtomTypeRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setInternSynPort() {
/*  59 */     PortType SynPortType = TOP.getInternPortType();
/*  60 */     this.InterPort = TransformationFunction.CreatePortDefinition("InternPortSyn", SynPortType, this.AtomTypeRP);
/*     */   }
/*     */   
/*     */   private void setMap_ConnReserve() {
/*  64 */     for (Connector o : this.LInteraction) {
/*  65 */       Connector connector = o;
/*  66 */       List<Variable> LnVar = new LinkedList<Variable>();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  71 */       int indexOfInteraction = this.LInteraction.indexOf(connector);
/*  72 */       for (Variable o1 : this.LLComponent_nVar.get(indexOfInteraction)) {
/*  73 */         LnVar.add(o1);
/*     */       }
/*  75 */       PortType ReservePT = TOP.getReservePortType(Integer.valueOf(LnVar.size()));
/*  76 */       Port ReserveP = TransformationFunction.CreatePort("Reserve" + connector.getName(), ReservePT, this.AtomTypeRP, LnVar);
/*  77 */       this.Map_Conn_Reserve.put(connector, ReserveP);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setAtomTypeRP() {
/*  82 */     this.AtomTypeRP = BehaviorsFactory.eINSTANCE.createAtomType();
/*  83 */     this.PetriNetRP = BehaviorsFactory.eINSTANCE.createPetriNet();
/*  84 */     RPName = Integer.valueOf(RPName.intValue() + 1); this.AtomTypeRP.setName("Reserve" + RPName);
/*  85 */     this.AtomTypeRP.setBehavior((Behavior)this.PetriNetRP);
/*  86 */     this.PetriNetRP.setAtomType(this.AtomTypeRP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setOKFAILPorts() {
/*  93 */     for (Connector o : this.LInteraction) {
/*  94 */       Connector connector = o;
/*  95 */       Variable IndexCompFail = TransformationFunction.CreateIntVariable("IndexCompFail" + connector.getName(), -1);
/*  96 */       Port OK = TransformationFunction.CreatePort("OK_" + connector.getName(), TOP.getInternPortType(), this.AtomTypeRP);
/*  97 */       PortType FailPT = TOP.getReservePortType(Integer.valueOf(1));
/*  98 */       List<Variable> LVariable = new LinkedList<Variable>();
/*  99 */       LVariable.add(IndexCompFail);
/* 100 */       Port FAIL = TransformationFunction.CreatePort("FAIL_" + connector.getName(), FailPT, this.AtomTypeRP, LVariable);
/* 101 */       this.AtomTypeRP.getVariable().add(IndexCompFail);
/* 102 */       this.Map_Conn_PortOk.put(connector, OK);
/* 103 */       this.Map_Conn_PortFail.put(connector, FAIL);
/* 104 */       this.Map_Conn_IndexCompFail.put(connector, IndexCompFail);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLLComponentReserve() {
/* 114 */     for (Connector o : this.LInteraction) {
/* 115 */       Connector connector = o;
/* 116 */       List<Component> LComponent = new LinkedList<Component>();
/* 117 */       List<Connector> LInteractionIP = TOP.getGroupInteraction(connector);
/* 118 */       LComponent = TOP.compoundTypeInfo.getConnectorExternalConflict(connector, LInteractionIP);
/* 119 */       this.LLComponentReserve.add(LComponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Comp_Var() {
/* 129 */     int i = 0;
/* 130 */     for (Object<Component> o : this.LLComponentReserve) {
/* 131 */       List<Component> LComponent = (List<Component>)o;
/* 132 */       List<Variable> LnVar = new LinkedList<Variable>();
/* 133 */       String ConnectorName = ((Connector)this.LInteraction.get(i++)).getName();
/* 134 */       for (Component o1 : LComponent) {
/* 135 */         Component component = o1;
/* 136 */         if (!this.Map_Comp_NVar.containsKey(component)) {
/* 137 */           Variable N_Var = TransformationFunction.CreateIntVariable("N_" + component.getName(), 0);
/* 138 */           this.Map_Comp_NVar.put(component, N_Var);
/* 139 */           this.AtomTypeRP.getVariable().add(N_Var);
/*     */         } 
/* 141 */         Variable n_Var = TransformationFunction.CreateIntVariable("n_" + ConnectorName + "_" + component.getName(), -1);
/* 142 */         LnVar.add(n_Var);
/* 143 */         this.AtomTypeRP.getVariable().add(n_Var);
/*     */       } 
/* 145 */       this.LLComponent_nVar.add(LnVar);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Component> getComponentToReserve(Connector connector) {
/* 155 */     int index = this.LInteraction.indexOf(connector);
/* 156 */     return this.LLComponentReserve.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Variable> getNVariableToComponent(List<Component> LComponent) {
/* 165 */     List<Variable> LVariable = new LinkedList<Variable>();
/* 166 */     for (Component o : LComponent) {
/* 167 */       Component component = o;
/* 168 */       if (this.Map_Comp_NVar.containsKey(component)) {
/* 169 */         LVariable.add(this.Map_Comp_NVar.get(component));
/*     */       }
/*     */     } 
/* 172 */     return LVariable;
/*     */   }
/*     */   
/*     */   public AtomType getAtomTypeRP() {
/* 176 */     return this.AtomTypeRP;
/*     */   }
/*     */   
/*     */   public void setComponentInstance(CompoundType compoundType) {
/* 180 */     this.ComponentRPInstance = TransformationFunction.CreateComponent(String.valueOf(this.AtomTypeRP.getName()) + "_Instance", (ComponentType)this.AtomTypeRP, compoundType, null);
/*     */   }
/*     */   
/*     */   public Component getComponentRPInstance() {
/* 184 */     return this.ComponentRPInstance;
/*     */   }
/*     */   
/*     */   protected Variable getn_Var(Connector connector, Component component) {
/* 188 */     int indexOfConnector = this.LInteraction.indexOf(connector);
/* 189 */     List<Component> LComponent = this.LLComponentReserve.get(indexOfConnector);
/* 190 */     int indexOfComponent = LComponent.indexOf(component);
/* 191 */     return ((List<Variable>)this.LLComponent_nVar.get(indexOfConnector)).get(indexOfComponent);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\unifiedDistributed\ReservationProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */