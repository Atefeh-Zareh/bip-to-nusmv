/*     */ package ujf.verimag.bip.metamodelAPI;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BipUtil
/*     */ {
/*     */   public static List<Transition> getTransitionFrom(PetriNet behavior, State from) {
/*  35 */     List<Transition> trans = new ArrayList<Transition>();
/*  36 */     for (Transition t : behavior.getTransition()) {
/*  37 */       if (t.getOrigin().contains(from)) {
/*  38 */         trans.add(t);
/*     */       }
/*     */     } 
/*  41 */     return trans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Transition> getTransitionTo(PetriNet behavior, State to) {
/*  52 */     List<Transition> trans = new ArrayList<Transition>();
/*  53 */     for (Transition t : behavior.getTransition()) {
/*  54 */       if (t.getDestination().contains(to)) {
/*  55 */         trans.add(t);
/*     */       }
/*     */     } 
/*  58 */     return trans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static State getState(PetriNet behavior, String stateName) {
/*  68 */     for (State state : behavior.getState()) {
/*  69 */       if (state.getName().contains(stateName)) {
/*  70 */         return state;
/*     */       }
/*     */     } 
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortDefinition getPortDefinition(AtomType atom, String portName) {
/*  83 */     for (PortDefinition pd : atom.getPortDefinition()) {
/*  84 */       if (pd.getName().equals(portName)) {
/*  85 */         return pd;
/*     */       }
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Port getPort(ComponentType componentType, String portName) {
/*  98 */     for (Port p : componentType.getPort()) {
/*  99 */       if (p.getName().equals(portName)) {
/* 100 */         return p;
/*     */       }
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AtomType getAtomTypeDefinition(String definitionName, Module module) {
/* 113 */     for (BipType bt : module.getBipType()) {
/* 114 */       if (bt instanceof AtomType && bt.getName().equals(definitionName)) {
/* 115 */         return (AtomType)bt;
/*     */       }
/*     */     } 
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompoundType getCompoundTypeDefinition(String definitionName, Module module) {
/* 128 */     for (BipType bt : module.getBipType()) {
/* 129 */       if (bt instanceof CompoundType && bt.getName().equals(definitionName)) {
/* 130 */         return (CompoundType)bt;
/*     */       }
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortType getPortType(Module module, String portTypeName) {
/* 143 */     for (BipType bt : module.getBipType()) {
/* 144 */       if (bt instanceof PortType && bt.getName().equals(portTypeName)) {
/* 145 */         return (PortType)bt;
/*     */       }
/*     */     } 
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConnectorType getConnectorType(Module module, String connectorTypeName) {
/* 158 */     for (BipType bt : module.getBipType()) {
/* 159 */       if (bt instanceof ConnectorType && bt.getName().equals(connectorTypeName)) {
/* 160 */         return (ConnectorType)bt;
/*     */       }
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Variable getVariable(AtomType atom, String varname) {
/* 173 */     for (Variable var : atom.getVariable()) {
/* 174 */       if (var.getName().equals(varname)) {
/* 175 */         return var;
/*     */       }
/*     */     } 
/* 178 */     return null;
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
/*     */   public static Variable[] getVariablesStartWith(AtomType atom, String prefix) {
/* 190 */     List<Variable> vars = new ArrayList<Variable>();
/*     */     
/* 192 */     for (Variable var : atom.getVariable()) {
/* 193 */       if (var.getName().startsWith(prefix)) {
/* 194 */         vars.add(var);
/*     */       }
/*     */     } 
/* 197 */     return vars.<Variable>toArray(new Variable[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataType getDataType(String name, Module module) {
/* 208 */     for (DataType dt : module.getDataType()) {
/* 209 */       assert dt instanceof OpaqueElement : "Unsupported DataType object";
/* 210 */       OpaqueElement oe = (OpaqueElement)dt;
/* 211 */       if (oe.getBody().equals(name))
/* 212 */         return dt; 
/*     */     } 
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addDeclarationToModule(Module module, Declaration declaration) {
/* 223 */     module.getDeclaration().add(declaration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addDeclarationToAtom(AtomType atom, Declaration declaration) {
/* 233 */     atom.getDeclaration().add(declaration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Component getComponentInstance(CompoundType scope, String instName) {
/* 243 */     for (Component instance : scope.getSubcomponent()) {
/* 244 */       if (instance.getName().equals(instName)) {
/* 245 */         return instance;
/*     */       }
/*     */     } 
/* 248 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Connector getConnectorInstance(CompoundType scope, String instName) {
/* 258 */     for (Connector instance : scope.getConnector()) {
/* 259 */       if (instance.getName().equals(instName)) {
/* 260 */         return instance;
/*     */       }
/*     */     } 
/* 263 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\metamodelAPI\BipUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */