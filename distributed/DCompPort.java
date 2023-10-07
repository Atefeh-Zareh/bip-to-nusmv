/*     */ package distributed;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
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
/*     */ public class DCompPort
/*     */ {
/*     */   private Component Comp;
/*  19 */   private List<List<Port>> LPortCent = new LinkedList<List<Port>>();
/*  20 */   private List<List<Port>> LPortEng = new LinkedList<List<Port>>();
/*  21 */   private List<List<State>> LStatePort = new LinkedList<List<State>>();
/*  22 */   private List<Port> LConflictPortDist = new LinkedList<Port>();
/*  23 */   private List<Port> LConflictPortEng = new LinkedList<Port>();
/*  24 */   private List<State> LStateInitBefore = new LinkedList<State>();
/*  25 */   private List<State> LStateInitAfter = new LinkedList<State>();
/*  26 */   private List<List<Variable>> LGuardVariable = new LinkedList<List<Variable>>();
/*  27 */   private List<Variable> LVariableCent = new LinkedList<Variable>();
/*  28 */   private List<Variable> LVariableEng = new LinkedList<Variable>();
/*     */   private AtomType Engine;
/*  30 */   static int portitt = 0;
/*     */ 
/*     */   
/*     */   public DCompPort(Component Comp, List<List<Port>> LPortCent, List<Port> LConflictPortDist, List<Variable> LVariableCent, AtomType Engine) {
/*  34 */     this.Comp = Comp;
/*  35 */     this.LPortCent = LPortCent;
/*  36 */     this.LConflictPortDist = LConflictPortDist;
/*  37 */     this.LVariableCent = LVariableCent;
/*  38 */     this.Engine = Engine;
/*  39 */     setLVariableEng();
/*  40 */     setLPortEng();
/*  41 */     setLGuardVariable();
/*  42 */     setLConflictPortEng();
/*  43 */     setLStateInitBeforeAfter();
/*  44 */     setLStatePort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getCorrespondPortEng(Port p) {
/*  54 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/*  56 */       List<Port> LPort = (List<Port>)o;
/*  57 */       for (Port o1 : LPort) {
/*     */         
/*  59 */         Port ptmp = o1;
/*  60 */         if (ptmp.equals(p)) {
/*     */           
/*  62 */           int indexClass = this.LPortCent.indexOf(o);
/*  63 */           int indexPort = LPort.indexOf(o1);
/*  64 */           List<Port> LPortTmp = this.LPortEng.get(indexClass);
/*  65 */           return LPortTmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public State getCorrespondStateEng(Port p) {
/*  76 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/*  78 */       List<Port> LPort = (List<Port>)o;
/*  79 */       for (Port o1 : LPort) {
/*     */         
/*  81 */         Port ptmp = o1;
/*  82 */         if (ptmp.equals(p)) {
/*     */           
/*  84 */           int indexClass = this.LPortCent.indexOf(o);
/*  85 */           int indexPort = LPort.indexOf(o1);
/*  86 */           List<State> LStateTmp = this.LStatePort.get(indexClass);
/*  87 */           return LStateTmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getBooleanVar(Port pcent) {
/*  97 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/*  99 */       List<Port> LPort = (List<Port>)o;
/* 100 */       for (Port o1 : LPort) {
/*     */         
/* 102 */         Port ptmp = o1;
/* 103 */         if (ptmp.equals(pcent)) {
/*     */           
/* 105 */           int indexClass = this.LPortCent.indexOf(o);
/* 106 */           int indexPort = LPort.indexOf(o1);
/* 107 */           List<Variable> LGuardTmp = this.LGuardVariable.get(indexClass);
/* 108 */           return LGuardTmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public State getCorrespondClassStateEng(Port p) {
/* 118 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/* 120 */       List<Port> LPort = (List<Port>)o;
/* 121 */       for (Port o1 : LPort) {
/*     */         
/* 123 */         Port ptmp = o1;
/* 124 */         if (ptmp.equals(p)) {
/*     */           
/* 126 */           int indexClass = this.LPortCent.indexOf(o);
/* 127 */           return this.LStateInitAfter.get(indexClass);
/*     */         } 
/*     */       } 
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getCorrespondClassPortEng(Port p) {
/* 137 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/* 139 */       List<Port> LPort = (List<Port>)o;
/* 140 */       for (Port o1 : LPort) {
/*     */         
/* 142 */         Port ptmp = o1;
/* 143 */         if (ptmp.equals(p)) {
/*     */           
/* 145 */           int indexClass = this.LPortCent.indexOf(o);
/* 146 */           return this.LConflictPortEng.get(indexClass);
/*     */         } 
/*     */       } 
/*     */     } 
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCorrespondClassPortStateEngDist(Port p) {
/* 156 */     List LPortState = new LinkedList();
/* 157 */     for (Port o : this.LConflictPortDist) {
/*     */       
/* 159 */       if (p.equals(o)) {
/*     */         
/* 161 */         LPortState.add(this.LConflictPortEng.get(this.LConflictPortDist.indexOf(o)));
/* 162 */         LPortState.add(this.LStateInitAfter.get(this.LConflictPortDist.indexOf(o)));
/*     */       } 
/*     */     } 
/* 165 */     return LPortState;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLStatePort() {
/* 170 */     PetriNet PN = (PetriNet)this.Engine.getBehavior();
/* 171 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 173 */       List<Port> lport = (List<Port>)o;
/* 174 */       List<State> LState = new LinkedList<State>();
/* 175 */       for (Port o1 : lport) {
/*     */         
/* 177 */         Port p = o1;
/* 178 */         State s = TransformationFunction.CreateState("S_" + p.getName(), PN);
/* 179 */         int indexStateInit = this.LPortEng.indexOf(o);
/* 180 */         State StateInit = this.LStateInitBefore.get(indexStateInit);
/* 181 */         DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 182 */         PortDefinition pd = db.getDefinition();
/* 183 */         TransformationFunction.CreateTransition(pd, null, null, s, StateInit, PN);
/* 184 */         LState.add(s);
/*     */       } 
/* 186 */       this.LStatePort.add(LState);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setLStateInitBeforeAfter() {
/* 191 */     PetriNet PN = (PetriNet)this.Engine.getBehavior();
/* 192 */     for (Port o : this.LConflictPortEng) {
/*     */       
/* 194 */       Port p = o;
/* 195 */       State s1 = TransformationFunction.CreateState("S_" + p.getName() + "Init", PN);
/* 196 */       State s2 = TransformationFunction.CreateState("S_" + p.getName(), PN);
/* 197 */       DefinitionBinding db = (DefinitionBinding)p.getBinding();
/* 198 */       PortDefinition pd = db.getDefinition();
/* 199 */       TransformationFunction.CreateTransition(pd, null, null, s1, s2, PN);
/* 200 */       PN.getInitialState().add(s1);
/* 201 */       this.LStateInitBefore.add(s1);
/* 202 */       this.LStateInitAfter.add(s2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLVariableEng() {
/* 208 */     List<String> LVariableName = TransformationFunction.getLVariableName(this.LVariableCent);
/* 209 */     List<String> LVariableNameDist = DList.ChangeNameAddString(LVariableName, "_" + this.Comp.getName());
/* 210 */     for (String o : LVariableNameDist) {
/*     */       
/* 212 */       String varnamedist = o;
/* 213 */       Variable vcent = this.LVariableCent.get(LVariableNameDist.indexOf(varnamedist));
/* 214 */       DataType dtvar = vcent.getType();
/* 215 */       this.LVariableEng.add(TransformationFunction.CreateVariable(varnamedist, dtvar));
/*     */     } 
/* 217 */     this.Engine.getVariable().addAll(this.LVariableEng);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLPortEng() {
/* 225 */     for (Object<Port> o : this.LPortCent) {
/*     */       
/* 227 */       List<Port> ClassPort = (List<Port>)o;
/* 228 */       List<Port> LSetPortEng = new LinkedList<Port>();
/* 229 */       for (Port o1 : ClassPort) {
/*     */         
/* 231 */         Port PCent = o1;
/* 232 */         String PortNameEng = String.valueOf(PCent.getName()) + "_" + this.Comp.getName();
/* 233 */         List<Variable> LVarPortEng = TransformationFunction.getVarPortEng(PCent, this.LVariableCent, this.LVariableEng);
/* 234 */         LSetPortEng.add(TransformationFunction.CreatePort(PortNameEng, PCent.getType(), this.Engine, LVarPortEng));
/*     */       } 
/* 236 */       this.LPortEng.add(LSetPortEng);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLGuardVariable() {
/* 243 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 245 */       List<Port> lporteng = (List<Port>)o;
/* 246 */       List<Variable> LVarTmp = new LinkedList<Variable>();
/* 247 */       for (Port o1 : lporteng) {
/*     */         
/* 249 */         Port PEng = o1;
/* 250 */         String VarGuardName = String.valueOf(PEng.getName()) + "_" + "GuarVar";
/* 251 */         Variable v = TransformationFunction.CreateGuardVariable(VarGuardName, false);
/* 252 */         this.Engine.getVariable().add(v);
/* 253 */         LVarTmp.add(v);
/*     */       } 
/* 255 */       this.LGuardVariable.add(LVarTmp);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Variable getBooleanVarPort(Port p) {
/* 262 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 264 */       List<Port> LPort = (List<Port>)o;
/* 265 */       for (Port o1 : LPort) {
/*     */         
/* 267 */         if (o1.equals(p)) {
/*     */           
/* 269 */           int indexClass = this.LPortEng.indexOf(o);
/* 270 */           int indexPort = LPort.indexOf(o1);
/* 271 */           List<Variable> LGuardtmp = this.LGuardVariable.get(indexClass);
/* 272 */           return LGuardtmp.get(indexPort);
/*     */         } 
/*     */       } 
/*     */     } 
/* 276 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLConflictPortEng() {
/* 282 */     String nameport = new String();
/* 283 */     for (Object<Port> o : this.LPortEng) {
/*     */       
/* 285 */       List<Variable> LVarPort = new LinkedList<Variable>();
/* 286 */       List<Port> LPort = (List<Port>)o;
/* 287 */       for (Port o1 : LPort)
/*     */       {
/* 289 */         LVarPort.add(getBooleanVarPort(o1));
/*     */       }
/* 291 */       nameport = "";
/* 292 */       for (Port o1 : LPort) {
/*     */         
/* 294 */         Port p = o1;
/* 295 */         DefinitionBinding definitionBinding = (DefinitionBinding)p.getBinding();
/* 296 */         PortDefinition portDefinition = definitionBinding.getDefinition();
/* 297 */         for (Object o3 : portDefinition.getExposedVariable()) {
/*     */           
/* 299 */           Variable v = (Variable)o3;
/* 300 */           DList.AddUnique(v, LVarPort);
/*     */         } 
/* 302 */         nameport = String.valueOf(nameport) + p.getName();
/*     */       } 
/* 304 */       int indexClassPort = this.LPortEng.indexOf(o);
/* 305 */       Port ConfPort = this.LConflictPortDist.get(indexClassPort);
/*     */       
/* 307 */       Port port = TransformationFunction.CreatePort("SPort_" + portitt, ConfPort.getType(), this.Engine);
/* 308 */       portitt++;
/* 309 */       DefinitionBinding db = (DefinitionBinding)port.getBinding();
/* 310 */       PortDefinition pd = db.getDefinition();
/* 311 */       pd.getExposedVariable().addAll(LVarPort);
/* 312 */       this.LConflictPortEng.add(port);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<List<Port>> getLPortDefEng() {
/* 319 */     return this.LPortEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLConflictPortEng() {
/* 324 */     return this.LConflictPortEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Variable>> getLGuardVariable() {
/* 329 */     return this.LGuardVariable;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Port> getLConflictPortDist() {
/* 334 */     return this.LConflictPortDist;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLVariableCent() {
/* 339 */     return this.LVariableCent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Variable> getLVariableDist() {
/* 344 */     return this.LVariableEng;
/*     */   }
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 349 */     return this.Comp;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Port>> getLPortCent() {
/* 354 */     return this.LPortCent;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<State>> getLStatePort() {
/* 359 */     return this.LStatePort;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<State> getLStateInitBefore() {
/* 364 */     return this.LStateInitBefore;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<State> getLStateInitAfter() {
/* 369 */     return this.LStateInitAfter;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\DCompPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */