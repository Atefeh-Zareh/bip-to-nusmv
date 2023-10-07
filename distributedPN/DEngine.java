/*     */ package distributedPN;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ 
/*     */ public class DEngine {
/*  27 */   private List<List<Variable>> LVariableConnector = new LinkedList<List<Variable>>(); private List<DConnector> LDConnector;
/*  28 */   private List<List<Variable>> LVariableConnectorEng = new LinkedList<List<Variable>>();
/*  29 */   private List<DCompPort> LDCompPort = new LinkedList<DCompPort>();
/*  30 */   private List<Component> LComponent = new LinkedList<Component>();
/*     */   private AtomType Engine;
/*     */   private PortDefinition PDIntern;
/*  33 */   static int engineitt = 0;
/*  34 */   private List<Port> LPortInter = new LinkedList<Port>();
/*     */ 
/*     */ 
/*     */   
/*     */   public DEngine(List<DConnector> LDConnector) {
/*  39 */     this.LDConnector = LDConnector;
/*  40 */     InitEngine();
/*  41 */     setLVariableConnectorEng();
/*  42 */     setLComponent();
/*  43 */     setLDCompPort();
/*  44 */     setEngine();
/*     */   }
/*     */ 
/*     */   
/*     */   private void InitEngine() {
/*  49 */     this.Engine = BehaviorsFactory.eINSTANCE.createAtomType();
/*     */     
/*  51 */     this.Engine.setName("Engine" + engineitt);
/*  52 */     engineitt++;
/*  53 */     this.PDIntern = TransformationFunction.CreatePortDefinition("Intern_Initialize", TransformationFunction.PTSyn, this.Engine);
/*  54 */     PetriNet PN = BehaviorsFactory.eINSTANCE.createPetriNet();
/*  55 */     this.Engine.setBehavior((Behavior)PN);
/*     */   }
/*     */   
/*     */   private void setLVariableConnectorEng() {
/*  59 */     for (DConnector o : this.LDConnector) {
/*     */       
/*  61 */       DConnector DConn = o;
/*  62 */       Connector Conn = DConn.getConnector();
/*  63 */       List<Variable> LVarEng = new LinkedList<Variable>();
/*  64 */       List<Variable> LVar = new LinkedList<Variable>();
/*  65 */       for (Object o1 : Conn.getType().getVariable()) {
/*     */         
/*  67 */         Variable Vconn = (Variable)o1;
/*  68 */         Variable Veng = (Variable)EcoreUtil.copy((EObject)Vconn);
/*  69 */         Veng.setName(String.valueOf(Vconn.getName()) + "_" + Conn.getName());
/*  70 */         this.Engine.getVariable().add(Veng);
/*  71 */         LVar.add(Vconn);
/*  72 */         LVarEng.add(Veng);
/*     */       } 
/*  74 */       this.LVariableConnector.add(LVar);
/*  75 */       this.LVariableConnectorEng.add(LVarEng);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setEngine() {
/*  85 */     Expression EguardValPort = null;
/*  86 */     PetriNet PN = (PetriNet)this.Engine.getBehavior();
/*  87 */     for (DConnector o : this.LDConnector) {
/*     */       
/*  89 */       DConnector DConn = o;
/*  90 */       List<Port> LPortconn = DConn.getPort();
/*  91 */       List<Component> LComponent = DConn.getLCompenent();
/*  92 */       List<State> FromState = new LinkedList<State>();
/*  93 */       List<State> ToState = new LinkedList<State>();
/*  94 */       List<Variable> LBoolVar = new LinkedList<Variable>();
/*  95 */       int ittport = 0;
/*  96 */       for (Port o1 : LPortconn) {
/*     */         
/*  98 */         Port Pconn = o1;
/*  99 */         Component Comp = LComponent.get(ittport);
/* 100 */         ittport++;
/* 101 */         DCompPort CompPort = getCompPort(Pconn, Comp);
/* 102 */         State s1 = CompPort.getCorrespondClassStateEng(Pconn);
/* 103 */         State s2 = CompPort.getCorrespondStateEng(Pconn);
/* 104 */         Variable v = CompPort.getBooleanVar(Pconn);
/* 105 */         FromState.add(s1);
/* 106 */         ToState.add(s2);
/* 107 */         LBoolVar.add(v);
/* 108 */         EguardValPort = TransformationFunction.CreateAndExpression(LBoolVar);
/*     */       } 
/* 110 */       Expression connguard = getGuardConnector(DConn);
/* 111 */       Expression transconguard = TransformationFunction.AndOfToExpression(EguardValPort, connguard);
/* 112 */       Action connactionget = FunctionConnector(DConn);
/* 113 */       TransformationFunction.CreateTransitionLState(this.PDIntern, transconguard, connactionget, FromState, ToState, PN);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Expression getGuardConnector(DConnector conn) {
/* 131 */     Connector c = conn.getConnector();
/* 132 */     int indexDconn = this.LDConnector.indexOf(conn);
/* 133 */     List<Variable> VarConn = this.LVariableConnector.get(indexDconn);
/* 134 */     List<Variable> VarConnEng = this.LVariableConnectorEng.get(indexDconn);
/* 135 */     EList<InteractionSpecification> eList = c.getType().getInteractionSpecification();
/* 136 */     if (eList.size() != 0) {
/*     */       
/* 138 */       InteractionSpecification IS = eList.get(0);
/* 139 */       Expression guardbefore = IS.getGuard();
/* 140 */       if (guardbefore != null) {
/*     */         
/* 142 */         Expression guardafter = (Expression)EcoreUtil.copy((EObject)guardbefore);
/* 143 */         TransformationFunction.ReplaceExpression(c, guardafter, VarConn, VarConnEng, this.LDCompPort);
/* 144 */         return guardafter;
/*     */       } 
/*     */     } 
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private Action FunctionConnector(DConnector conn) {
/* 152 */     CompositeAction CAUP = ActionsFactory.eINSTANCE.createCompositeAction();
/* 153 */     Connector c = conn.getConnector();
/* 154 */     int indexDconn = this.LDConnector.indexOf(conn);
/* 155 */     List<Variable> VarConn = this.LVariableConnector.get(indexDconn);
/* 156 */     List<Variable> VarConnEng = this.LVariableConnectorEng.get(indexDconn);
/* 157 */     EList<InteractionSpecification> eList = c.getType().getInteractionSpecification();
/* 158 */     if (eList.size() != 0) {
/*     */ 
/*     */       
/* 161 */       InteractionSpecification IS = eList.get(0);
/* 162 */       if (IS.getUpAction() instanceof CompositeAction) {
/*     */         
/* 164 */         CompositeAction UPA = (CompositeAction)EcoreUtil.copy((EObject)IS.getUpAction());
/* 165 */         if (UPA.getContent().size() > 0) {
/*     */           
/* 167 */           List LAction = new LinkedList((Collection<?>)UPA.getContent());
/* 168 */           for (Object o1 : LAction) {
/*     */             
/* 170 */             if (o1 instanceof AssignmentAction) {
/*     */               
/* 172 */               AssignmentAction AA = (AssignmentAction)o1;
/* 173 */               TransformationFunction.ReplaceExpression(c, AA.getAssignedValue(), VarConn, VarConnEng, this.LDCompPort);
/* 174 */               TransformationFunction.ReplaceExpression1(c, (Expression)AA.getAssignedTarget(), VarConn, VarConnEng, this.LDCompPort);
/* 175 */               CAUP.getContent().add(AA); continue;
/*     */             } 
/* 177 */             if (o1 instanceof FunctionCallExpression) {
/*     */               
/* 179 */               FunctionCallExpression Fcall = (FunctionCallExpression)EcoreUtil.copy((EObject)o1);
/* 180 */               for (Object o : Fcall.getActualData()) {
/*     */                 
/* 182 */                 Expression E1 = (Expression)o;
/* 183 */                 TransformationFunction.ReplaceExpression1(c, E1, VarConn, VarConnEng, this.LDCompPort);
/*     */               } 
/* 185 */               CAUP.getContent().add(Fcall);
/*     */               
/*     */               continue;
/*     */             } 
/* 189 */             System.out.println("To do Up");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 194 */       if (IS.getDownAction() instanceof CompositeAction) {
/*     */         
/* 196 */         Action DOA = (Action)EcoreUtil.copy((EObject)IS.getDownAction());
/* 197 */         CompositeAction CADO = (CompositeAction)DOA;
/* 198 */         if (CADO.getContent().size() > 0) {
/*     */           
/* 200 */           List LAction = new LinkedList((Collection<?>)CADO.getContent());
/* 201 */           for (Object o1 : LAction) {
/*     */ 
/*     */             
/* 204 */             if (o1 instanceof AssignmentAction) {
/*     */               
/* 206 */               AssignmentAction AA = (AssignmentAction)o1;
/* 207 */               TransformationFunction.ReplaceExpression(c, AA.getAssignedValue(), VarConn, VarConnEng, this.LDCompPort);
/* 208 */               TransformationFunction.ReplaceExpression1(c, (Expression)AA.getAssignedTarget(), VarConn, VarConnEng, this.LDCompPort);
/* 209 */               CAUP.getContent().add(AA); continue;
/*     */             } 
/* 211 */             if (o1 instanceof FunctionCallExpression) {
/*     */ 
/*     */               
/* 214 */               FunctionCallExpression Fcall = (FunctionCallExpression)EcoreUtil.copy((EObject)o1);
/* 215 */               for (Object o : Fcall.getActualData()) {
/*     */                 
/* 217 */                 Expression E1 = (Expression)o;
/* 218 */                 TransformationFunction.ReplaceExpression1(c, E1, VarConn, VarConnEng, this.LDCompPort);
/*     */               } 
/* 220 */               CAUP.getContent().add(Fcall);
/*     */               
/*     */               continue;
/*     */             } 
/* 224 */             System.out.println("To do Down");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 230 */     String s = "Execution of Interaction : " + c.getName() + "\\n";
/* 231 */     CAUP.getContent().add(TransformationFunction.CreateFucntionPrint(s));
/* 232 */     return (Action)CAUP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DCompPort getCompPort(Port pconn, Component comp) {
/* 239 */     for (DCompPort o : this.LDCompPort) {
/*     */       
/* 241 */       DCompPort CompPort = o;
/* 242 */       Component Comp = CompPort.getComponent();
/* 243 */       List<List<Port>> LPort = CompPort.getLPortCent();
/* 244 */       for (Object<Port> o1 : LPort) {
/*     */         
/* 246 */         List<Port> LP = (List<Port>)o1;
/* 247 */         for (Port o2 : LP) {
/*     */           
/* 249 */           Port p = o2;
/* 250 */           if (p.equals(pconn) && Comp.equals(comp)) {
/* 251 */             return CompPort;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getEngineName() {
/* 261 */     String engineName = new String("Engine");
/* 262 */     for (DConnector o : this.LDConnector) {
/*     */       
/* 264 */       DConnector DConn = o;
/* 265 */       engineName = String.valueOf(engineName) + "_" + DConn.getConnector().getName();
/*     */     } 
/* 267 */     return engineName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLComponent() {
/* 273 */     for (DConnector o : this.LDConnector) {
/*     */       
/* 275 */       DConnector DConn = o;
/* 276 */       DList.AddListUnique(DConn.getLCompenent(), this.LComponent);
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
/*     */ 
/*     */   
/*     */   private List<List<Port>> getPortComp(Component C) {
/* 290 */     List<Port> LPort = new LinkedList<Port>();
/* 291 */     for (DConnector o : this.LDConnector) {
/*     */       
/* 293 */       DConnector DConn = o;
/* 294 */       int index = DConn.getLCompenent().indexOf(C);
/* 295 */       if (index != -1) {
/*     */         
/* 297 */         Port port = DConn.getPort().get(index);
/* 298 */         DList.AddUnique(port, LPort);
/*     */       } 
/*     */     } 
/* 301 */     AtomType ATCent = (AtomType)C.getType();
/* 302 */     DAtomType DAT = TransformationFunction.getDAtomType(DCompoundType.LDAtomType, ATCent);
/* 303 */     return DAT.getSetClassofPorts(LPort);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLDCompPort() {
/* 314 */     for (Component o : this.LComponent) {
/*     */       
/* 316 */       Component comp = o;
/* 317 */       List<List<Port>> LConfPort = getPortComp(comp);
/* 318 */       AtomType ATCent = (AtomType)comp.getType();
/* 319 */       DAtomType DAT = TransformationFunction.getDAtomType(DCompoundType.LDAtomType, ATCent);
/* 320 */       List<Port> LConflictPort = DAT.getClassSetPort(LConfPort);
/* 321 */       List<Variable> LVariable = DAT.getVariable(LConfPort);
/* 322 */       DCompPort compport = new DCompPort(comp, LConfPort, LConflictPort, LVariable, this.Engine);
/* 323 */       this.LDCompPort.add(compport);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DConnector> getLDConnector() {
/* 329 */     return this.LDConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DCompPort> getLDCompPort() {
/* 334 */     return this.LDCompPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getEngine() {
/* 339 */     return this.Engine;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Component> getLComponent() {
/* 344 */     return this.LComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLVariableConnector() {
/* 349 */     return this.LVariableConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLVariableConnectorEng() {
/* 354 */     return this.LVariableConnectorEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public PortDefinition getPortInter() {
/* 359 */     return this.PDIntern;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLPortInter() {
/* 364 */     return this.LPortInter;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\DEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */