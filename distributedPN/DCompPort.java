/*     */ package distributedPN;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ 
/*     */ public class DCompPort {
/*     */   private Component Comp;
/*  17 */   private List<List<Port>> LPortCent = new LinkedList<List<Port>>();
/*  18 */   private List<List<Port>> LPortEng = new LinkedList<List<Port>>();
/*  19 */   private List<List<State>> LStatePort = new LinkedList<List<State>>();
/*  20 */   private List<Port> LConflictPortDist = new LinkedList<Port>();
/*  21 */   private List<Port> LConflictPortEng = new LinkedList<Port>();
/*  22 */   private List<State> LStateInitBefore = new LinkedList<State>();
/*  23 */   private List<State> LStateInitAfter = new LinkedList<State>();
/*  24 */   private List<List<Variable>> LGuardVariable = new LinkedList<List<Variable>>();
/*  25 */   private List<Variable> LVariableCent = new LinkedList<Variable>();
/*  26 */   private List<Variable> LVariableEng = new LinkedList<Variable>();
/*     */   private AtomType Engine;
/*  28 */   static int portitt = 0;
/*     */ 
/*     */   
/*     */   public DCompPort(Component Comp, List<List<Port>> LPortCent, List<Port> LConflictPortDist, List<Variable> LVariableCent, AtomType Engine) {
/*  32 */     this.Comp = Comp;
/*  33 */     this.LPortCent = LPortCent;
/*  34 */     this.LConflictPortDist = LConflictPortDist;
/*  35 */     this.LVariableCent = LVariableCent;
/*  36 */     this.Engine = Engine;
/*  37 */     setLVariableEng();
/*  38 */     setLPortEng();
/*  39 */     setLGuardVariable();
/*  40 */     setLConflictPortEng();
/*  41 */     setLStateInitBeforeAfter();
/*  42 */     setLStatePort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getCorrespondPortEng(Port p) {
/*  52 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/*  54 */       List<Port> LPort = (List<Port>)o;
/*  55 */       for (Port o1 : LPort) {
/*     */         
/*  57 */         Port ptmp = o1;
/*  58 */         if (ptmp.equals(p)) {
/*     */           
/*  60 */           int indexClass = this.LPortCent.indexOf(o);
/*  61 */           int indexPort = LPort.indexOf(o1);
/*  62 */           List<Port> LPortTmp = this.LPortEng.get(indexClass);
/*  63 */           return LPortTmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public State getCorrespondStateEng(Port p) {
/*  74 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/*  76 */       List<Port> LPort = (List<Port>)o;
/*  77 */       for (Port o1 : LPort) {
/*     */         
/*  79 */         Port ptmp = o1;
/*  80 */         if (ptmp.equals(p)) {
/*     */           
/*  82 */           int indexClass = this.LPortCent.indexOf(o);
/*  83 */           int indexPort = LPort.indexOf(o1);
/*  84 */           List<State> LStateTmp = this.LStatePort.get(indexClass);
/*  85 */           return LStateTmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getBooleanVar(Port pcent) {
/*  95 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/*  97 */       List<Port> LPort = (List<Port>)o;
/*  98 */       for (Port o1 : LPort) {
/*     */         
/* 100 */         Port ptmp = o1;
/* 101 */         if (ptmp.equals(pcent)) {
/*     */           
/* 103 */           int indexClass = this.LPortCent.indexOf(o);
/* 104 */           int indexPort = LPort.indexOf(o1);
/* 105 */           List<Variable> LGuardTmp = this.LGuardVariable.get(indexClass);
/* 106 */           return LGuardTmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public State getCorrespondClassStateEng(Port p) {
/* 116 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/* 118 */       List<Port> LPort = (List<Port>)o;
/* 119 */       for (Port o1 : LPort) {
/*     */         
/* 121 */         Port ptmp = o1;
/* 122 */         if (ptmp.equals(p)) {
/*     */           
/* 124 */           int indexClass = this.LPortCent.indexOf(o);
/* 125 */           return this.LStateInitAfter.get(indexClass);
/*     */         } 
/*     */       } 
/*     */     } 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getCorrespondClassPortEng(Port p) {
/* 135 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/* 137 */       List<Port> LPort = (List<Port>)o;
/* 138 */       for (Port o1 : LPort) {
/*     */         
/* 140 */         Port ptmp = o1;
/* 141 */         if (ptmp.equals(p)) {
/*     */           
/* 143 */           int indexClass = this.LPortCent.indexOf(o);
/* 144 */           return this.LConflictPortEng.get(indexClass);
/*     */         } 
/*     */       } 
/*     */     } 
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCorrespondClassPortStateEngDist(Port p) {
/* 154 */     List LPortState = new LinkedList();
/* 155 */     for (Port o : this.LConflictPortDist) {
/*     */       
/* 157 */       if (p.equals(o)) {
/*     */         
/* 159 */         LPortState.add(this.LConflictPortEng.get(this.LConflictPortDist.indexOf(o)));
/* 160 */         LPortState.add(this.LStateInitAfter.get(this.LConflictPortDist.indexOf(o)));
/*     */       } 
/*     */     } 
/* 163 */     return LPortState;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLStatePort() {
/* 168 */     PetriNet PN = (PetriNet)this.Engine.getBehavior();
/* 169 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 171 */       List<Port> lport = (List<Port>)o;
/* 172 */       List<State> LState = new LinkedList<State>();
/* 173 */       for (Port o1 : lport) {
/*     */         
/* 175 */         Port p = o1;
/* 176 */         State s = TransformationFunction.CreateState("S_" + p.getName(), PN);
/* 177 */         int indexStateInit = this.LPortEng.indexOf(o);
/* 178 */         State StateInit = this.LStateInitBefore.get(indexStateInit);
/* 179 */         DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 180 */         PortDefinition pd = db.getDefinition();
/* 181 */         TransformationFunction.CreateTransition(pd, null, null, s, StateInit, PN);
/* 182 */         LState.add(s);
/*     */       } 
/* 184 */       this.LStatePort.add(LState);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setLStateInitBeforeAfter() {
/* 189 */     PetriNet PN = (PetriNet)this.Engine.getBehavior();
/* 190 */     for (Port o : this.LConflictPortEng) {
/*     */       
/* 192 */       Port p = o;
/* 193 */       State s1 = TransformationFunction.CreateState("S_" + p.getName() + "Init", PN);
/* 194 */       State s2 = TransformationFunction.CreateState("S_" + p.getName(), PN);
/* 195 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 196 */       PortDefinition pd = db.getDefinition();
/* 197 */       TransformationFunction.CreateTransition(pd, null, null, s1, s2, PN);
/* 198 */       PN.getInitialState().add(s1);
/* 199 */       this.LStateInitBefore.add(s1);
/* 200 */       this.LStateInitAfter.add(s2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLVariableEng() {
/* 206 */     List<String> LVariableName = TransformationFunction.getLVariableName(this.LVariableCent);
/* 207 */     List<String> LVariableNameDist = DList.ChangeNameAddString(LVariableName, "_" + this.Comp.getName());
/* 208 */     for (String o : LVariableNameDist) {
/*     */       
/* 210 */       String varnamedist = o;
/* 211 */       Variable vcent = this.LVariableCent.get(LVariableNameDist.indexOf(varnamedist));
/* 212 */       DataType dtvar = vcent.getType();
/* 213 */       this.LVariableEng.add(TransformationFunction.CreateVariable(varnamedist, dtvar));
/*     */     } 
/* 215 */     this.Engine.getVariable().addAll(this.LVariableEng);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLPortEng() {
/* 223 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/* 225 */       List<Port> ClassPort = (List<Port>)o;
/* 226 */       List<Port> LSetPortEng = new LinkedList<Port>();
/* 227 */       for (Port o1 : ClassPort) {
/*     */         
/* 229 */         Port PCent = o1;
/* 230 */         String PortNameEng = String.valueOf(PCent.getName()) + "_" + this.Comp.getName();
/* 231 */         List<Variable> LVarPortEng = TransformationFunction.getVarPortEng(PCent, this.LVariableCent, this.LVariableEng);
/* 232 */         LSetPortEng.add(TransformationFunction.CreatePort(PortNameEng, PCent.getType(), this.Engine, LVarPortEng));
/*     */       } 
/* 234 */       this.LPortEng.add(LSetPortEng);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLGuardVariable() {
/* 241 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 243 */       List<Port> lporteng = (List<Port>)o;
/* 244 */       List<Variable> LVarTmp = new LinkedList<Variable>();
/* 245 */       for (Port o1 : lporteng) {
/*     */         
/* 247 */         Port PEng = o1;
/* 248 */         String VarGuardName = String.valueOf(PEng.getName()) + "_" + "GuarVar";
/* 249 */         Variable v = TransformationFunction.CreateGuardVariable(VarGuardName);
/* 250 */         this.Engine.getVariable().add(v);
/* 251 */         LVarTmp.add(v);
/*     */       } 
/* 253 */       this.LGuardVariable.add(LVarTmp);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getBooleanVarPort(Port p) {
/* 260 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 262 */       List<Port> LPort = (List<Port>)o;
/* 263 */       for (Port o1 : LPort) {
/*     */         
/* 265 */         if (o1.equals(p)) {
/*     */           
/* 267 */           int indexClass = this.LPortEng.indexOf(o);
/* 268 */           int indexPort = LPort.indexOf(o1);
/* 269 */           List<Variable> LGuardtmp = this.LGuardVariable.get(indexClass);
/* 270 */           return LGuardtmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/* 274 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLConflictPortEng() {
/* 280 */     String nameport = new String();
/* 281 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 283 */       List<Variable> LVarPort = new LinkedList<Variable>();
/* 284 */       List<Port> LPort = (List<Port>)o;
/* 285 */       for (Port o1 : LPort)
/*     */       {
/* 287 */         LVarPort.add(getBooleanVarPort(o1));
/*     */       }
/* 289 */       nameport = "";
/* 290 */       for (Port o1 : LPort) {
/*     */         
/* 292 */         Port p = o1;
/* 293 */         DefinitionBinding definitionBinding = (DefinitionBinding)p.getBinding();
/* 294 */         PortDefinition portDefinition = definitionBinding.getDefinition();
/* 295 */         for (Object o3 : portDefinition.getExposedVariable()) {
/*     */           
/* 297 */           Variable v = (Variable)o3;
/* 298 */           DList.AddUnique(v, LVarPort);
/*     */         } 
/* 300 */         nameport = String.valueOf(nameport) + p.getName();
/*     */       } 
/* 302 */       int indexClassPort = this.LPortEng.indexOf(o);
/* 303 */       Port ConfPort = this.LConflictPortDist.get(indexClassPort);
/*     */       
/* 305 */       Port port = TransformationFunction.CreatePort("SPort_" + portitt, ConfPort.getType(), this.Engine);
/* 306 */       portitt++;
/* 307 */       DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 308 */       PortDefinition pd = db.getDefinition();
/* 309 */       pd.getExposedVariable().addAll(LVarPort);
/* 310 */       this.LConflictPortEng.add(port);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<List<Port>> getLPortDefEng() {
/* 317 */     return this.LPortEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLConflictPortEng() {
/* 322 */     return this.LConflictPortEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLGuardVariable() {
/* 327 */     return this.LGuardVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLConflictPortDist() {
/* 332 */     return this.LConflictPortDist;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLVariableCent() {
/* 337 */     return this.LVariableCent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLVariableDist() {
/* 342 */     return this.LVariableEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 347 */     return this.Comp;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Port>> getLPortCent() {
/* 352 */     return this.LPortCent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<State>> getLStatePort() {
/* 357 */     return this.LStatePort;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<State> getLStateInitBefore() {
/* 362 */     return this.LStateInitBefore;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<State> getLStateInitAfter() {
/* 367 */     return this.LStateInitAfter;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\DCompPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */