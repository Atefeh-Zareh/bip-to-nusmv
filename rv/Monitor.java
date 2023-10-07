/*     */ package rv;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import distributed.DList;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jveto.model.automata.AutomatonHelper;
/*     */ import jveto.model.automata.State;
/*     */ import jveto.model.automata.Transition;
/*     */ import jveto.model.automata.VerificationMonitor;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ 
/*     */ public class Monitor {
/*     */   private AtomType MAT;
/*     */   private Port PM;
/*     */   private PortDefinition PMDefinition;
/*     */   private PortType PMType;
/*     */   private PortType InternPortType;
/*     */   private PortDefinition InterPort;
/*  39 */   private Map<Component, List<Variable>> Map_Component_VariableToMonitor = new HashMap<Component, List<Variable>>();
/*     */   private static final String MATName = "MonitorType";
/*     */   private VerificationMonitor monitorXML;
/*  42 */   private Map<State, State> Map_State_BusyState = new HashMap<State, State>();
/*  43 */   private Map<State, State> Map_MonitorState_BIPState = new HashMap<State, State>();
/*  44 */   private Map<String, Variable> Map_Event_GuardVar = new HashMap<String, Variable>();
/*  45 */   private Map<String, String> Map_Event_Guard = new HashMap<String, String>();
/*     */   private List<Component> LComponent;
/*     */   private PetriNet PN;
/*     */   
/*     */   public Monitor(String MonitorPath, String MapEventGuardPath, Map<Component, List<Variable>> Map_Component_VariableToMonitor, List<Component> LComponent) throws FileNotFoundException {
/*  50 */     this.MAT = TransformationFunction.CreateAtomType("MonitorType");
/*  51 */     this.Map_Component_VariableToMonitor = Map_Component_VariableToMonitor;
/*  52 */     this.LComponent = LComponent;
/*  53 */     setPM();
/*  54 */     setInternPortType();
/*  55 */     File file = new File(MonitorPath);
/*  56 */     this.monitorXML = (VerificationMonitor)AutomatonHelper.load(file);
/*  57 */     this.PN = (PetriNet)this.MAT.getBehavior();
/*  58 */     setMap_Event_Guard(MapEventGuardPath);
/*  59 */     setBehaviorMonitor();
/*  60 */     this.MAT.setModule(BIPMonitor.BIPmodule);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_Event_Guard(String mapEventGuardPath) {
/*     */     try {
/*  69 */       File file = new File(mapEventGuardPath);
/*  70 */       FileInputStream fis = new FileInputStream(file);
/*     */       
/*  72 */       BufferedInputStream bis = new BufferedInputStream(fis);
/*  73 */       DataInputStream dis = new DataInputStream(bis);
/*  74 */       String Line = new String();
/*  75 */       String[] EventGuard = (String[])null;
/*     */       
/*  77 */       while ((Line = dis.readLine()) != null) {
/*  78 */         if (!Line.equals(" ") && !Line.equals("")) {
/*  79 */           EventGuard = Line.split(" : ");
/*  80 */           this.Map_Event_Guard.put(EventGuard[0], EventGuard[1]);
/*  81 */           Variable EventGuardVar = TransformationFunction.CreateGuardVariable("GuardVar_" + EventGuard[0], false);
/*  82 */           this.Map_Event_GuardVar.put(EventGuard[0], EventGuardVar);
/*  83 */           this.MAT.getVariable().add(EventGuardVar);
/*     */         }
/*     */       
/*     */       } 
/*  87 */     } catch (Exception E) {
/*  88 */       System.err.println("Error Reading File for Mapping Events and Guards : " + E);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBehaviorMonitor() {
/*  95 */     setMap_State_BusyState();
/*  96 */     setTransitions();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setTransitions() {
/* 101 */     for (Object o : this.monitorXML.getStates()) {
/* 102 */       State FromMonitorState = (State)o;
/* 103 */       State FromBIPState = this.Map_MonitorState_BIPState.get(FromMonitorState);
/* 104 */       State FromBIPBusyState = this.Map_State_BusyState.get(FromBIPState);
/* 105 */       List<Action> LActionAssignEvent = new LinkedList<Action>();
/* 106 */       for (Object o1 : FromMonitorState.getTransitions()) {
/* 107 */         Transition MonitorTransition = (Transition)o1;
/* 108 */         Variable GuardBoolVar = this.Map_Event_GuardVar.get(MonitorTransition.getEvent());
/* 109 */         OpaqueElement OEAction = TransformationFunction.CreateOpaqueElement(String.valueOf(GuardBoolVar.getName()) + "=" + (String)this.Map_Event_Guard.get(MonitorTransition.getEvent()) + " ;");
/* 110 */         LActionAssignEvent.add(OEAction);
/* 111 */         State ToBIPState = this.Map_MonitorState_BIPState.get(MonitorTransition.getNextState());
/* 112 */         Action OutputAction = TransformationFunction.CreateFucntionPrint("\\n Monitor Output : " + MonitorTransition.getOutput() + " \\n");
/* 113 */         VariableReference Guard = TransformationFunction.CreateVariableReference(this.Map_Event_GuardVar.get(MonitorTransition.getEvent()));
/* 114 */         TransformationFunction.CreateTransition(this.InterPort, (Expression)Guard, OutputAction, FromBIPBusyState, ToBIPState, this.PN);
/*     */       } 
/*     */       
/* 117 */       CompositeAction ActionsAssignEvent = TransformationFunction.CreateCompositeAction(LActionAssignEvent);
/* 118 */       TransformationFunction.CreateTransition(this.PMDefinition, null, (Action)ActionsAssignEvent, FromBIPState, FromBIPBusyState, this.PN);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMap_State_BusyState() {
/* 123 */     for (Object o : this.monitorXML.getStates()) {
/* 124 */       State MonitorState = (State)o;
/* 125 */       State BIPState = TransformationFunction.CreateState(MonitorState.getId(), this.PN);
/* 126 */       State BIPBusyState = TransformationFunction.CreateState(String.valueOf(MonitorState.getId()) + "_Busy", this.PN);
/* 127 */       if (this.monitorXML.getInitialState().equals(MonitorState))
/* 128 */         this.PN.getInitialState().add(BIPState); 
/* 129 */       this.Map_State_BusyState.put(BIPState, BIPBusyState);
/* 130 */       this.Map_MonitorState_BIPState.put(MonitorState, BIPState);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setPM() {
/* 135 */     List<Variable> LAllVariableToMonitor = new LinkedList<Variable>();
/* 136 */     for (Component o : this.LComponent) {
/* 137 */       Component component = o;
/* 138 */       List<Variable> LVariableToMonitor = this.Map_Component_VariableToMonitor.get(component);
/* 139 */       List<String> LVariableToMonitorName = TransformationFunction.getLVariableName(LVariableToMonitor);
/* 140 */       List<String> LVariableToMonitorNewName = DList.ChangeNameAddString(LVariableToMonitorName, "_" + component.getName());
/* 141 */       LAllVariableToMonitor.addAll(TransformationFunction.CreateCopyVariables(LVariableToMonitorNewName, LVariableToMonitor));
/*     */     } 
/* 143 */     this.PMType = TransformationFunction.CreatePortTypeFromVar(LAllVariableToMonitor, "PMType", BIPMonitor.BIPmodule);
/* 144 */     this.PM = TransformationFunction.CreatePort("PM", this.PMType, this.MAT, LAllVariableToMonitor);
/* 145 */     this.PMDefinition = TransformationFunction.getPortDefinition(this.PM);
/* 146 */     this.MAT.getVariable().addAll(LAllVariableToMonitor);
/*     */   }
/*     */   
/*     */   private void setInternPortType() {
/* 150 */     for (Object o : BIPMonitor.BIPmodule.getBipType()) {
/* 151 */       if (o instanceof PortType) {
/* 152 */         PortType pt = (PortType)o;
/* 153 */         if (pt.getName().equals("Port")) {
/* 154 */           this.InternPortType = pt;
/* 155 */           this.InterPort = TransformationFunction.CreatePortDefinition("InternPort", this.InternPortType, this.MAT);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Port getPM() {
/* 163 */     return this.PM;
/*     */   }
/*     */   
/*     */   public AtomType getMAT() {
/* 167 */     return this.MAT;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\rv\Monitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */