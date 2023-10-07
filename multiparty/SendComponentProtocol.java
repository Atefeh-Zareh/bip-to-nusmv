/*     */ package multiparty;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ 
/*     */ 
/*     */ public class SendComponentProtocol
/*     */ {
/*     */   private Component component;
/*     */   private DAtomType DAT;
/*     */   private SendDataComponentProtocol SDCP;
/*     */   private AtomType SCPType;
/*  29 */   private List<Port> LPort = new LinkedList<Port>();
/*  30 */   private List<PortDefinition> LPortDefinition = new LinkedList<PortDefinition>();
/*  31 */   private List<Connector> LConnector = new LinkedList<Connector>();
/*     */ 
/*     */   
/*     */   private Port recMSG;
/*     */ 
/*     */ 
/*     */   
/*     */   public SendComponentProtocol(DAtomType DAT, Component component, SendDataComponentProtocol SDCP) {
/*  39 */     this.DAT = DAT;
/*  40 */     this.component = component;
/*  41 */     this.SDCP = SDCP;
/*  42 */     setSCPType();
/*  43 */     setPorts();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSCPType() {
/*  48 */     this.SCPType = (AtomType)EcoreUtil.copy((EObject)Initialize.SendComponentProtocol);
/*  49 */     this.SCPType.setName("Send_" + this.component.getName() + "_Protocol");
/*     */     
/*  51 */     PetriNet PN = (PetriNet)this.SCPType.getBehavior();
/*  52 */     State state_b = TransformationFunction.getState(PN, "b");
/*     */     
/*  54 */     Transition from_b_to_b = TransformationFunction.getLTransition(PN, state_b, state_b);
/*     */     
/*  56 */     this.LConnector = new LinkedList<Connector>(this.SDCP.getLConnector());
/*  57 */     PortDefinition pd = (PortDefinition)this.SCPType.getPortDefinition().get(0);
/*  58 */     for (Connector o : this.LConnector) {
/*     */ 
/*     */       
/*  61 */       Connector conn = o;
/*  62 */       PortDefinition pdcopy = (PortDefinition)EcoreUtil.copy((EObject)pd);
/*  63 */       pdcopy.setName("Send" + conn.getName());
/*  64 */       Port port = TransformationFunction.CreatePort(pdcopy);
/*  65 */       this.SCPType.getPortDefinition().add(pdcopy);
/*  66 */       this.SCPType.getPort().add(port);
/*  67 */       this.LPort.add(port);
/*  68 */       this.LPortDefinition.add(pdcopy);
/*     */ 
/*     */ 
/*     */       
/*  72 */       Transition b_to_b = (Transition)EcoreUtil.copy((EObject)from_b_to_b);
/*  73 */       b_to_b.getOrigin().add(state_b);
/*  74 */       b_to_b.getDestination().add(state_b);
/*  75 */       PortDefinitionReference pdr = (PortDefinitionReference)b_to_b.getTrigger();
/*  76 */       pdr.setTarget(pdcopy);
/*  77 */       BinaryExpression BE = (BinaryExpression)b_to_b.getGuard();
/*  78 */       IntegerLiteral IL = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/*  79 */       IL.setIValue(Initialize.getID(conn));
/*  80 */       BE.setRightOperand((Expression)IL);
/*  81 */       PN.getTransition().add(b_to_b);
/*     */     } 
/*     */     
/*  84 */     PN.getTransition().remove(from_b_to_b);
/*  85 */     this.SCPType.getPort().remove(0);
/*  86 */     this.SCPType.getPortDefinition().remove(0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPorts() {
/*  91 */     for (Object o : this.SCPType.getPort()) {
/*     */       
/*  93 */       Port p = (Port)o;
/*  94 */       if (p.getName().equals("recMSG")) {
/*  95 */         this.recMSG = p;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 104 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public DAtomType getDAT() {
/* 109 */     return this.DAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getSCPType() {
/* 114 */     return this.SCPType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Connector> getLConnector() {
/* 119 */     return this.LConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLPort() {
/* 124 */     return this.LPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public SendDataComponentProtocol getSDCP() {
/* 129 */     return this.SDCP;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PortDefinition> getLPortDefinition() {
/* 134 */     return this.LPortDefinition;
/*     */   }
/*     */ 
/*     */   
/*     */   public Port getRecMSG() {
/* 139 */     return this.recMSG;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\SendComponentProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */