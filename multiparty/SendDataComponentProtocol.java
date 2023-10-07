/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
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
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SendDataComponentProtocol
/*     */ {
/*     */   private Component component;
/*     */   private DAtomType DAT;
/*     */   private AtomType SDCPType;
/*  36 */   private List<Port> LPort = new LinkedList<Port>();
/*  37 */   private List<Connector> LConnector = new LinkedList<Connector>();
/*  38 */   private List<Variable> LVariable = new LinkedList<Variable>();
/*  39 */   private List<Variable> LPortGuardVar = new LinkedList<Variable>();
/*     */   
/*     */   private Port ReceiveGuardVar;
/*     */   private PortDefinition PDReceiveGuardVar;
/*     */   private Port FinishSendData;
/*  44 */   private List<List<Connector>> LLConnector = new LinkedList<List<Connector>>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SendDataComponentProtocol(DAtomType DAT, Component component) {
/*  50 */     this.DAT = DAT;
/*  51 */     this.component = component;
/*  52 */     this.SDCPType = (AtomType)EcoreUtil.copy((EObject)Initialize.SendDataComponentProtocol);
/*  53 */     this.SDCPType.setName("SendData_" + component.getName() + "_Protocol");
/*  54 */     this.FinishSendData = getFinishSenddata();
/*  55 */     setSDCPType();
/*     */     
/*  57 */     CreateGetConnectorOfComponent();
/*     */   }
/*     */ 
/*     */   
/*     */   private Port getFinishSenddata() {
/*  62 */     return (Port)this.SDCPType.getPort().get(0);
/*     */   }
/*     */   private void CreateGetConnectorOfComponent() {
/*  65 */     String function = new String();
/*  66 */     String header = new String();
/*  67 */     String queue = new String();
/*  68 */     header = "buffer getConnectorOf_" + this.component.getName() + " ( ";
/*  69 */     queue = "buffer f ;\n\tCreateBuffer( &f );\n";
/*     */     
/*  71 */     int i = 0;
/*  72 */     for (Variable o : this.LPortGuardVar) {
/*     */       
/*  74 */       if (i == 0) {
/*  75 */         header = String.valueOf(header) + "bool b" + i;
/*     */       } else {
/*  77 */         header = String.valueOf(header) + " , bool b" + i;
/*  78 */       }  queue = String.valueOf(queue) + "\tif ( b" + i + " == true ) \n\t{\n\t";
/*  79 */       for (Connector o1 : this.LLConnector.get(this.LPortGuardVar.indexOf(o))) {
/*     */         
/*  81 */         Connector connector = o1;
/*  82 */         queue = String.valueOf(queue) + "\tAddUniqueElement( &f, " + Initialize.getID(connector) + " );\n\t";
/*     */       } 
/*  84 */       queue = String.valueOf(queue) + "}\n";
/*  85 */       i++;
/*     */     } 
/*  87 */     header = String.valueOf(header) + " ) \n{\n\t";
/*  88 */     queue = String.valueOf(queue) + "\treturn f ; \n}";
/*  89 */     function = String.valueOf(header) + queue + "\n\n";
/*  90 */     Initialize.Function_C = String.valueOf(Initialize.Function_C) + function;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setReceiveGuardVar() {
/*  95 */     this.PDReceiveGuardVar = (PortDefinition)EcoreUtil.copy((EObject)((DefinitionBinding)this.DAT.getSendPortGuardVar().getBinding()).getDefinition());
/*  96 */     this.PDReceiveGuardVar.setName("ReceiveGuardVar");
/*     */     
/*  98 */     this.ReceiveGuardVar = BehaviorsFactory.eINSTANCE.createPort();
/*  99 */     this.ReceiveGuardVar.setName(this.PDReceiveGuardVar.getName());
/*     */     
/* 101 */     DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 102 */     db.setDefinition(this.PDReceiveGuardVar);
/* 103 */     this.ReceiveGuardVar.setBinding((Binding)db);
/*     */ 
/*     */     
/* 106 */     this.SDCPType.getPortDefinition().add(this.PDReceiveGuardVar);
/* 107 */     this.SDCPType.getPort().add(this.ReceiveGuardVar);
/*     */   }
/*     */   
/*     */   private void setSDCPType() {
/* 111 */     setLVariable();
/* 112 */     setLPort();
/* 113 */     setReceiveGuardVar();
/* 114 */     setBehavior();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBehavior() {
/* 119 */     PetriNet PN = (PetriNet)this.SDCPType.getBehavior();
/* 120 */     State state_a = TransformationFunction.getState(PN, "a");
/* 121 */     State state_b = TransformationFunction.getState(PN, "b");
/*     */     
/* 123 */     Transition from_a_to_b = TransformationFunction.getLTransition(PN, state_a, state_b);
/* 124 */     PortDefinitionReference pdr = (PortDefinitionReference)from_a_to_b.getTrigger();
/* 125 */     pdr.setTarget(this.PDReceiveGuardVar);
/* 126 */     CompositeAction ca = (CompositeAction)from_a_to_b.getAction();
/* 127 */     AssignmentAction aa = (AssignmentAction)ca.getContent().get(0);
/* 128 */     aa.setAssignedValue((Expression)TransformationFunction.CreateFunctionCall("getConnectorOf_" + this.component.getName(), this.LPortGuardVar));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     Transition from_b_to_b = TransformationFunction.getLTransition(PN, state_b, state_b);
/* 134 */     for (Port o : this.LPort) {
/*     */       
/* 136 */       Port p = o;
/* 137 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 138 */       PortDefinition pd = db.getDefinition();
/* 139 */       Transition b_to_b = (Transition)EcoreUtil.copy((EObject)from_b_to_b);
/* 140 */       b_to_b.getOrigin().add(state_b);
/* 141 */       b_to_b.getDestination().add(state_b);
/* 142 */       PortDefinitionReference pdr1 = (PortDefinitionReference)b_to_b.getTrigger();
/* 143 */       pdr1.setTarget(pd);
/* 144 */       BinaryExpression BE = (BinaryExpression)b_to_b.getGuard();
/* 145 */       IntegerLiteral IL = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/* 146 */       IL.setIValue(Initialize.getID(this.LConnector.get(this.LPort.indexOf(o))));
/* 147 */       BE.setRightOperand((Expression)IL);
/* 148 */       PN.getTransition().add(b_to_b);
/*     */     } 
/* 150 */     PN.getTransition().remove(from_b_to_b);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLPort() {
/* 155 */     for (Object o : this.DAT.getCAT().getPort()) {
/*     */       
/* 157 */       Port p = (Port)o;
/* 158 */       List<Connector> LSetConnector = TransformationFunction.getConnectorfromPortComp(p, this.component, Initialize.CompType);
/* 159 */       this.LLConnector.add(LSetConnector);
/* 160 */       for (Connector o1 : LSetConnector) {
/*     */         
/* 162 */         Connector conn = o1;
/*     */         
/* 164 */         PortDefinition PD = ((DefinitionBinding)p.getBinding()).getDefinition();
/* 165 */         PortDefinition PDCopy = (PortDefinition)EcoreUtil.copy((EObject)PD);
/* 166 */         PDCopy.setName("Send_" + conn.getName());
/*     */         
/* 168 */         Port expport = BehaviorsFactory.eINSTANCE.createPort();
/* 169 */         expport.setName(PDCopy.getName());
/* 170 */         expport.setType(PDCopy.getType());
/*     */         
/* 172 */         DefinitionBinding db = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 173 */         db.setDefinition(PDCopy);
/* 174 */         expport.setBinding((Binding)db);
/*     */         
/* 176 */         this.SDCPType.getPortDefinition().add(PDCopy);
/* 177 */         this.SDCPType.getPort().add(expport);
/* 178 */         this.LConnector.add(conn);
/* 179 */         this.LPort.add(expport);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLVariable() {
/* 186 */     this.LVariable = TransformationFunction.getCopyLVariable((List)this.DAT.getCAT().getVariable());
/* 187 */     this.SDCPType.getVariable().addAll(this.LVariable);
/* 188 */     this.LPortGuardVar = TransformationFunction.getCopyLVariable(this.DAT.getLPortGuardVar());
/* 189 */     this.SDCPType.getVariable().addAll(this.LPortGuardVar);
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 194 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public DAtomType getDAT() {
/* 199 */     return this.DAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getSDCPType() {
/* 204 */     return this.SDCPType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Connector> getLConnector() {
/* 209 */     return this.LConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLPort() {
/* 214 */     return this.LPort;
/*     */   }
/*     */   
/*     */   public PortDefinition getPDReceiveGuardVar() {
/* 218 */     return this.PDReceiveGuardVar;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLVariable() {
/* 223 */     return this.LVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLPortGuardVar() {
/* 228 */     return this.LPortGuardVar;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getReceiveGuardVar() {
/* 233 */     return this.ReceiveGuardVar;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Connector>> getLLConnector() {
/* 238 */     return this.LLConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Connector> getLConnectorfromPort(Port p) {
/* 243 */     return this.LLConnector.get(this.DAT.getCAT().getPort().indexOf(p));
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getFinishSendData() {
/* 248 */     return this.FinishSendData;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\SendDataComponentProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */