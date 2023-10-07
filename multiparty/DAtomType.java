/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import distributed.DList;
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
/*     */ public class DAtomType {
/*     */   private AtomType CAT;
/*     */   private AtomType DAT;
/*  34 */   private List<Variable> LPortGuardVar = new LinkedList<Variable>();
/*     */   private Port SendPortGuard;
/*     */   private Port SendPortGuardVar;
/*     */   private Port FinishSendData;
/*  38 */   private List<String> LStringPort = new LinkedList<String>();
/*     */   private Module module;
/*  40 */   static int porttypeitt = 0;
/*     */ 
/*     */   
/*     */   public DAtomType(AtomType CAT) {
/*  44 */     this.CAT = CAT;
/*  45 */     this.module = CAT.getModule();
/*  46 */     setLStringPort();
/*  47 */     setDAT();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLStringPort() {
/*  53 */     EList eList = this.CAT.getPort();
/*  54 */     for (Port port_i : eList) {
/*  55 */       this.LStringPort.add(port_i.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private State getCorrespondingState(String StateName) {
/*  62 */     PetriNet PN = (PetriNet)this.DAT.getBehavior();
/*  63 */     for (Object o : PN.getState()) {
/*     */       
/*  65 */       State s = (State)o;
/*  66 */       if (s.getName().equals(StateName))
/*  67 */         return s; 
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDAT() {
/*  75 */     this.DAT = BehaviorsFactory.eINSTANCE.createAtomType();
/*  76 */     this.DAT = (AtomType)EcoreUtil.copy((EObject)this.CAT);
/*  77 */     this.DAT.setName(String.valueOf(this.CAT.getName()) + "_Distributed");
/*  78 */     List LPortGuardVarName = DList.ChangeNameAddString(this.LStringPort, "guardVar");
/*  79 */     this.LPortGuardVar = TransformationFunction.CreateGuardVariable(LPortGuardVarName);
/*  80 */     this.DAT.getVariable().addAll(this.LPortGuardVar);
/*  81 */     CreateSendPortType();
/*     */     
/*  83 */     PetriNet PNCAT = (PetriNet)this.CAT.getBehavior();
/*  84 */     PetriNet PNDAT = (PetriNet)this.DAT.getBehavior();
/*  85 */     PortDefinition PDInternInit = TransformationFunction.CreatePortDefinition("Intern_Initialize", TransformationFunction.PTSyn, this.DAT);
/*  86 */     this.FinishSendData = TransformationFunction.CreatePort("FinishSendData", Initialize.Sync, this.DAT);
/*  87 */     for (Object o : PNCAT.getState()) {
/*     */       
/*  89 */       State sc = (State)o;
/*  90 */       State sd = getCorrespondingState(sc.getName());
/*  91 */       if (ExistOutgointExport(sc)) {
/*  92 */         setDistributed(sd, PNDAT, PDInternInit);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean ExistOutgointExport(State sd) {
/* 100 */     for (Object o : sd.getOutgoing()) {
/*     */       
/* 102 */       Transition t = (Transition)o;
/* 103 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 104 */       if (this.LStringPort.contains(pdr.getTarget().getName()))
/* 105 */         return true; 
/*     */     } 
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Transition> getTransitionExpPort(State s) {
/* 112 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 113 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 115 */       Transition t = (Transition)o;
/* 116 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 117 */       if (this.LStringPort.indexOf(pdr.getTarget().getName()) != -1)
/* 118 */         LTransition.add(t); 
/*     */     } 
/* 120 */     return LTransition;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Transition> getTransitionIntPort(State s) {
/* 125 */     List<Transition> LTransition = new LinkedList<Transition>();
/* 126 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 128 */       Transition t = (Transition)o;
/* 129 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 130 */       if (this.LStringPort.indexOf(pdr.getTarget().getName()) == -1)
/* 131 */         LTransition.add(t); 
/*     */     } 
/* 133 */     return LTransition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDistributed(State sd, PetriNet PN, PortDefinition PDInternInit) {
/* 141 */     List<Transition> LTransitionExpPort = getTransitionExpPort(sd);
/* 142 */     List<Transition> LTransitionIntPort = getTransitionIntPort(sd);
/*     */     
/* 144 */     List<Expression> LGuardExpression = TransformationFunction.CorrespondingGuard(LTransitionExpPort, this.LStringPort);
/*     */     
/* 146 */     CompositeAction CAInitializeGuard = TransformationFunction.CreateInitializeCAGuard(this.LPortGuardVar, LGuardExpression);
/*     */     
/* 148 */     State busystate1 = TransformationFunction.CreateState("BusyState1_" + sd.getName(), PN);
/* 149 */     State busystate2 = TransformationFunction.CreateState("BusyState2_" + sd.getName(), PN);
/*     */ 
/*     */     
/* 152 */     State busystate21 = TransformationFunction.CreateState("BusyState21_" + sd.getName(), PN);
/*     */     
/* 154 */     State busystate3 = TransformationFunction.CreateState("BusyState3_" + sd.getName(), PN);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     TransformationFunction.CreateTransition(PDInternInit, null, (Action)CAInitializeGuard, sd, busystate1, PN);
/* 163 */     TransformationFunction.CreateTransition(((DefinitionBinding)this.SendPortGuardVar.getBinding()).getDefinition(), null, null, busystate1, busystate2, PN);
/* 164 */     TransformationFunction.CreateTransition(((DefinitionBinding)this.FinishSendData.getBinding()).getDefinition(), null, null, busystate2, busystate21, PN);
/* 165 */     TransformationFunction.CreateTransition(((DefinitionBinding)this.SendPortGuard.getBinding()).getDefinition(), getOrExpression(this.LStringPort, sd), null, busystate21, busystate3, PN);
/*     */ 
/*     */     
/* 168 */     TransformationFunction.RemoveGuardTransition(LTransitionExpPort);
/* 169 */     TransformationFunction.CopyTransitionFromState(LTransitionExpPort, sd, busystate3);
/* 170 */     TransformationFunction.CopyTransitionFromState(LTransitionIntPort, sd, busystate1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getBooleanVarPort(String port) {
/* 176 */     return this.LPortGuardVar.get(this.LStringPort.indexOf(port));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Expression getOrExpression(List<String> LPort, State s) {
/* 187 */     boolean firststep = true;
/* 188 */     boolean test = false;
/* 189 */     BinaryExpression binaryExpression1 = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 190 */     BinaryExpression BE = null;
/* 191 */     for (Object o : s.getOutgoing()) {
/*     */       
/* 193 */       Transition t = (Transition)o;
/*     */       
/* 195 */       PortDefinitionReference pdr = (PortDefinitionReference)t.getTrigger();
/* 196 */       if (LPort.contains(pdr.getTarget().getName())) {
/* 197 */         VariableReference variableReference1; Variable v = getBooleanVarPort(pdr.getTarget().getName());
/* 198 */         VariableReference VarRef = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 199 */         VarRef.setTargetVariable(v);
/* 200 */         if (firststep) {
/*     */           
/* 202 */           variableReference1 = VarRef;
/* 203 */           firststep = false;
/*     */           
/*     */           continue;
/*     */         } 
/* 207 */         test = true;
/* 208 */         BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/* 209 */         BE.setLeftOperand((Expression)variableReference1);
/* 210 */         BE.setRightOperand((Expression)VarRef);
/* 211 */         BinaryOperator BO = BinaryOperator.LOGICAL_OR;
/* 212 */         BE.setOperator(BO);
/* 213 */         binaryExpression1 = BE;
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     if (!test) return (Expression)binaryExpression1; 
/* 218 */     return (Expression)BE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getDPort(String PortName) {
/* 227 */     for (Object o : this.DAT.getPort()) {
/*     */       
/* 229 */       Port p = (Port)o;
/* 230 */       if (p.getName().equals(PortName))
/* 231 */         return p; 
/*     */     } 
/* 233 */     return null;
/*     */   }
/*     */   
/*     */   private void CreateSendPortType() {
/* 237 */     List<Variable> LVarPort = new LinkedList<Variable>();
/* 238 */     LVarPort.addAll(this.LPortGuardVar);
/*     */     
/* 240 */     for (String o : this.LStringPort) {
/*     */       
/* 242 */       String portname = o;
/* 243 */       Port p = getDPort(portname);
/*     */       
/* 245 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 246 */       PortDefinition pd = db.getDefinition();
/* 247 */       for (Object o1 : pd.getExposedVariable()) {
/*     */         
/* 249 */         Variable v = (Variable)o1;
/* 250 */         DList.AddUnique(v, LVarPort);
/*     */       } 
/*     */     } 
/*     */     
/* 254 */     List<DataParameter> LdpPG = TransformationFunction.CreateDataParameter(this.LPortGuardVar);
/* 255 */     List<DataParameter> LdpPGV = TransformationFunction.CreateDataParameter(LVarPort);
/*     */     
/* 257 */     PortType porttypeG = TransformationFunction.CreatePortType(LdpPG, "SendBoolType" + porttypeitt++, this.module);
/* 258 */     PortType porttypeGV = TransformationFunction.CreatePortType(LdpPGV, "SendBoolVariableType" + porttypeitt, this.module);
/*     */     
/* 260 */     this.SendPortGuard = TransformationFunction.CreatePort("SendPortGuard", porttypeG, this.DAT);
/* 261 */     this.SendPortGuardVar = TransformationFunction.CreatePort("SendPortGuardVar", porttypeGV, this.DAT);
/*     */ 
/*     */     
/* 264 */     DefinitionBinding dbPG = (DefinitionBinding)this.SendPortGuard.getBinding();
/* 265 */     PortDefinition pdPG = dbPG.getDefinition();
/* 266 */     pdPG.getExposedVariable().addAll(this.LPortGuardVar);
/*     */     
/* 268 */     DefinitionBinding dbPGV = (DefinitionBinding)this.SendPortGuardVar.getBinding();
/* 269 */     PortDefinition pdPGV = dbPGV.getDefinition();
/* 270 */     pdPGV.getExposedVariable().addAll(LVarPort);
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getCAT() {
/* 275 */     return this.CAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getDAT() {
/* 280 */     return this.DAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLPortGuardVar() {
/* 285 */     return this.LPortGuardVar;
/*     */   }
/*     */   
/*     */   public Port getSendPortGuard() {
/* 289 */     return this.SendPortGuard;
/*     */   }
/*     */   
/*     */   public Port getSendPortGuardVar() {
/* 293 */     return this.SendPortGuardVar;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getLStringPort() {
/* 298 */     return this.LStringPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getFinishSendData() {
/* 303 */     return this.FinishSendData;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\DAtomType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */