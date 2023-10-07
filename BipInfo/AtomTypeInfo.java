/*     */ package BipInfo;
/*     */ 
/*     */ import distributed.DList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
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
/*     */ public class AtomTypeInfo
/*     */ {
/*     */   protected AtomType atomType;
/*  31 */   protected List<Port> LExportPort = new LinkedList<Port>();
/*  32 */   protected Map<PortDefinition, Port> Map_PD_ExportPort = new HashMap<PortDefinition, Port>();
/*  33 */   protected List<List<Port>> ConflictPort = new LinkedList<List<Port>>();
/*     */   
/*     */   protected boolean[][] ConflictPortbyPort;
/*     */   protected int NumExpPort;
/*     */   protected Module module;
/*     */   
/*     */   public AtomTypeInfo(AtomType atomType) {
/*  40 */     this.atomType = atomType;
/*  41 */     this.module = atomType.getModule();
/*  42 */     this.NumExpPort = atomType.getPort().size();
/*  43 */     this.LExportPort = (List<Port>)atomType.getPort();
/*  44 */     setMap_PD_ExportPort();
/*  45 */     this.ConflictPortbyPort = new boolean[this.NumExpPort][this.NumExpPort];
/*  46 */     resetConflictPortbyPort();
/*  47 */     setConflictPortbyPort();
/*  48 */     TransitiveClosureConflictPort();
/*  49 */     setConflictPort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_PD_ExportPort() {
/*  55 */     for (Object o : this.atomType.getPort()) {
/*     */       
/*  57 */       Port exportport = (Port)o;
/*  58 */       DefinitionBinding db = (DefinitionBinding)exportport.getBinding();
/*  59 */       PortDefinition portdefinition = db.getDefinition();
/*  60 */       if (!this.Map_PD_ExportPort.containsKey(portdefinition)) {
/*  61 */         this.Map_PD_ExportPort.put(portdefinition, exportport);
/*     */       }
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
/*     */   private void setConflictPortbyPort() {
/*  74 */     PetriNet petri_net = (PetriNet)this.atomType.getBehavior();
/*  75 */     EList eList = petri_net.getState();
/*  76 */     for (Object state_i : eList) {
/*     */       
/*  78 */       State state = (State)state_i;
/*  79 */       EList LTransition = state.getOutgoing();
/*  80 */       if (LTransition.size() != 0)
/*     */       {
/*  82 */         for (int i = 0; i < LTransition.size() - 1; i++) {
/*     */           
/*  84 */           Transition transition1 = (Transition)LTransition.get(i);
/*  85 */           PortDefinitionReference PDR1 = (PortDefinitionReference)transition1.getTrigger();
/*  86 */           PortDefinition PD1 = PDR1.getTarget();
/*  87 */           Port ExportPort = this.Map_PD_ExportPort.get(PD1);
/*  88 */           int indexofPort1 = this.LExportPort.indexOf(ExportPort);
/*  89 */           if (indexofPort1 != -1)
/*     */           {
/*  91 */             for (int j = i + 1; j < LTransition.size(); j++) {
/*     */               
/*  93 */               Transition transition2 = (Transition)LTransition.get(j);
/*  94 */               PortDefinitionReference PDR2 = (PortDefinitionReference)transition2.getTrigger();
/*  95 */               PortDefinition PD2 = PDR2.getTarget();
/*  96 */               Port ExportPort1 = this.Map_PD_ExportPort.get(PD2);
/*  97 */               int indexofPort2 = this.LExportPort.indexOf(ExportPort1);
/*  98 */               if (indexofPort2 != -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 104 */                 this.ConflictPortbyPort[indexofPort1][indexofPort2] = true;
/* 105 */                 this.ConflictPortbyPort[indexofPort2][indexofPort1] = true;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void TransitiveClosureConflictPort() {
/* 120 */     for (int k = 0; k < this.NumExpPort; k++) {
/* 121 */       for (int i = 0; i < this.NumExpPort; i++) {
/* 122 */         for (int j = 0; j < this.NumExpPort; j++)
/* 123 */           this.ConflictPortbyPort[i][j] = this.ConflictPortbyPort[i][j] | this.ConflictPortbyPort[i][k] & this.ConflictPortbyPort[k][j]; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void resetConflictPortbyPort() {
/* 128 */     for (int i = 0; i < this.NumExpPort; i++) {
/* 129 */       this.ConflictPortbyPort[i][i] = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setConflictPort() {
/* 135 */     for (int i = 0; i < this.NumExpPort; i++) {
/*     */       
/* 137 */       List<Port> LConflictPort = new LinkedList<Port>();
/* 138 */       for (int j = 0; j < this.NumExpPort; j++) {
/* 139 */         if (this.ConflictPortbyPort[i][j])
/* 140 */           LConflictPort.add(this.LExportPort.get(j)); 
/* 141 */       }  this.ConflictPort.add(LConflictPort);
/*     */     } 
/* 143 */     this.ConflictPort = DList.MakeListofListUnique1(this.ConflictPort);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean IsConflict(Port p1, Port p2) {
/* 150 */     for (Object<Port> o : this.ConflictPort) {
/*     */       
/* 152 */       List<Port> l = (List<Port>)o;
/* 153 */       if (l.contains(p1) && l.contains(p2))
/* 154 */         return true; 
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AtomType getAtomType() {
/* 161 */     return this.atomType;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BipInfo\AtomTypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */