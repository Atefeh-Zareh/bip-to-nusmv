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
/*     */ public class SendConnectorProtocol
/*     */ {
/*     */   private Connector connector;
/*  25 */   private List<Component> LComponent = new LinkedList<Component>();
/*  26 */   private List<Port> LPort = new LinkedList<Port>();
/*  27 */   private List<PortDefinition> LPortDefinition = new LinkedList<PortDefinition>();
/*     */   
/*     */   private Port RecMSG;
/*     */   
/*     */   private AtomType SConnPType;
/*     */   
/*     */   public SendConnectorProtocol(Connector connector) {
/*  34 */     this.connector = connector;
/*  35 */     this.LComponent = TransformationFunction.getLComponent(connector);
/*  36 */     setSConnP();
/*  37 */     setReceivePort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setReceivePort() {
/*  43 */     for (Object o : this.SConnPType.getPort()) {
/*     */       
/*  45 */       Port p = (Port)o;
/*  46 */       if (p.getName().equals("recMSG")) {
/*     */         
/*  48 */         this.RecMSG = p;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSConnP() {
/*  56 */     this.SConnPType = (AtomType)EcoreUtil.copy((EObject)Initialize.SendConnectorProtocol);
/*  57 */     this.SConnPType.setName("Send_" + this.connector.getName() + "_Protocol");
/*     */     
/*  59 */     PetriNet PN = (PetriNet)this.SConnPType.getBehavior();
/*  60 */     State state_b = TransformationFunction.getState(PN, "b");
/*     */     
/*  62 */     Transition from_b_to_b = TransformationFunction.getLTransition(PN, state_b, state_b);
/*  63 */     PortDefinition pd = (PortDefinition)this.SConnPType.getPortDefinition().get(0);
/*     */     
/*  65 */     for (Component o : this.LComponent) {
/*     */       
/*  67 */       Component comp = o;
/*  68 */       PortDefinition pdcopy = (PortDefinition)EcoreUtil.copy((EObject)pd);
/*  69 */       pdcopy.setName("Send_" + comp.getName());
/*  70 */       Port port = TransformationFunction.CreatePort(pdcopy);
/*     */ 
/*     */       
/*  73 */       this.SConnPType.getPortDefinition().add(pdcopy);
/*  74 */       this.SConnPType.getPort().add(port);
/*  75 */       this.LPort.add(port);
/*  76 */       this.LPortDefinition.add(pdcopy);
/*     */ 
/*     */ 
/*     */       
/*  80 */       Transition b_to_b = (Transition)EcoreUtil.copy((EObject)from_b_to_b);
/*  81 */       b_to_b.getOrigin().add(state_b);
/*  82 */       b_to_b.getDestination().add(state_b);
/*  83 */       PortDefinitionReference pdr = (PortDefinitionReference)b_to_b.getTrigger();
/*  84 */       pdr.setTarget(pdcopy);
/*  85 */       BinaryExpression BE1 = (BinaryExpression)b_to_b.getGuard();
/*  86 */       BinaryExpression BE = (BinaryExpression)BE1.getRightOperand();
/*  87 */       IntegerLiteral IL = ExpressionsFactory.eINSTANCE.createIntegerLiteral();
/*  88 */       IL.setIValue(Initialize.getID(comp));
/*  89 */       BE.setRightOperand((Expression)IL);
/*  90 */       PN.getTransition().add(b_to_b);
/*     */       
/*  92 */       PN.getTransition().remove(from_b_to_b);
/*     */     } 
/*  94 */     this.SConnPType.getPortDefinition().remove(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Connector getConnector() {
/* 102 */     return this.connector;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType getSConnP() {
/* 108 */     return this.SConnPType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Component> getLComponent() {
/* 114 */     return this.LComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Port> getLPort() {
/* 120 */     return this.LPort;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PortDefinition> getLPortDefinition() {
/* 126 */     return this.LPortDefinition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getRecMSG() {
/* 132 */     return this.RecMSG;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\SendConnectorProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */