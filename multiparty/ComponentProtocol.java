/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ 
/*     */ public class ComponentProtocol {
/*     */   private Component component;
/*     */   private DAtomType DAT;
/*     */   private AtomType CPType;
/*     */   private Port RecPortGuard;
/*     */   private PortDefinition PDRecPortGuard;
/*  32 */   private List<Variable> LPortGuardVar = new LinkedList<Variable>();
/*     */   
/*     */   private Port Lock;
/*     */   
/*     */   private Port Start;
/*     */   
/*     */   private Port Unlock;
/*     */   
/*     */   private Port Ackref;
/*     */   
/*     */   private Port sendMSG;
/*     */ 
/*     */   
/*     */   public ComponentProtocol(DAtomType DAT, Component component) {
/*  46 */     this.component = component;
/*  47 */     this.DAT = DAT;
/*  48 */     setCPType();
/*  49 */     setPorts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCPType() {
/*  57 */     this.CPType = (AtomType)EcoreUtil.copy((EObject)Initialize.ComponentProtocol);
/*  58 */     this.CPType.setName(String.valueOf(this.component.getName()) + "_ComponentProtocol");
/*  59 */     this.LPortGuardVar = TransformationFunction.getCopyLVariable(this.DAT.getLPortGuardVar());
/*  60 */     this.CPType.getVariable().addAll(this.LPortGuardVar);
/*     */ 
/*     */     
/*  63 */     this.PDRecPortGuard = (PortDefinition)EcoreUtil.copy((EObject)((DefinitionBinding)this.DAT.getSendPortGuard().getBinding()).getDefinition());
/*  64 */     this.PDRecPortGuard.getExposedVariable().clear();
/*  65 */     this.PDRecPortGuard.getExposedVariable().addAll(this.LPortGuardVar);
/*  66 */     this.PDRecPortGuard.setName("RecPortGuar");
/*  67 */     this.RecPortGuard = BehaviorsFactory.eINSTANCE.createPort();
/*  68 */     this.RecPortGuard.setName(this.PDRecPortGuard.getName());
/*     */ 
/*     */     
/*  71 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/*  72 */     db.setDefinition(this.PDRecPortGuard);
/*  73 */     this.RecPortGuard.setBinding((Binding)db);
/*     */     
/*  75 */     this.CPType.getPortDefinition().add(this.PDRecPortGuard);
/*  76 */     this.CPType.getPort().add(this.RecPortGuard);
/*  77 */     setBehavior();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBehavior() {
/*  82 */     PetriNet PN = (PetriNet)this.CPType.getBehavior();
/*  83 */     State state_Active = TransformationFunction.getState(PN, "ACTIVE");
/*  84 */     State state_OffPart = TransformationFunction.getState(PN, "OffPart");
/*     */     
/*  86 */     Transition from_Active_to_OffPart = TransformationFunction.getLTransition(PN, state_Active, state_OffPart);
/*  87 */     PortDefinitionReference pdr = (PortDefinitionReference)from_Active_to_OffPart.getTrigger();
/*  88 */     pdr.setTarget(this.PDRecPortGuard);
/*  89 */     CompositeAction ca = (CompositeAction)from_Active_to_OffPart.getAction();
/*  90 */     AssignmentAction aa = (AssignmentAction)ca.getContent().get(0);
/*  91 */     aa.setAssignedValue((Expression)TransformationFunction.CreateFunctionCall("getConnectorOf_" + this.component.getName(), this.LPortGuardVar));
/*     */ 
/*     */ 
/*     */     
/*  95 */     Variable v = (Variable)this.CPType.getVariable().get(0);
/*  96 */     IntegerLiteral IL = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/*  97 */     IL.setIValue(Initialize.getID(this.component));
/*  98 */     v.setInitialValue((Expression)IL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPorts() {
/* 104 */     for (Object o : this.CPType.getPort()) {
/*     */       
/* 106 */       Port p = (Port)o;
/* 107 */       if (p.getName().equals("LOCK")) {
/* 108 */         this.Lock = p; continue;
/* 109 */       }  if (p.getName().equals("UNLOCK")) {
/* 110 */         this.Unlock = p; continue;
/* 111 */       }  if (p.getName().equals("START")) {
/* 112 */         this.Start = p; continue;
/* 113 */       }  if (p.getName().equals("ACKREF")) {
/* 114 */         this.Ackref = p; continue;
/* 115 */       }  if (p.getName().equals("sendMSG")) {
/* 116 */         this.sendMSG = p;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 123 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public DAtomType getDAT() {
/* 128 */     return this.DAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getCPType() {
/* 133 */     return this.CPType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getRecPortGuard() {
/* 138 */     return this.RecPortGuard;
/*     */   }
/*     */ 
/*     */   
/*     */   public PortDefinition getPDRecPortGuard() {
/* 143 */     return this.PDRecPortGuard;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLPortGuardVar() {
/* 148 */     return this.LPortGuardVar;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getLock() {
/* 153 */     return this.Lock;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getStart() {
/* 158 */     return this.Start;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getUnlock() {
/* 163 */     return this.Unlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getAckref() {
/* 168 */     return this.Ackref;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getSendMSG() {
/* 173 */     return this.sendMSG;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\ComponentProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */