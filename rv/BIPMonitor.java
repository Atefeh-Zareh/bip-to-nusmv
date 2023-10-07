/*     */ package rv;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ import ujf.verimag.bip.metamodelAPI.BipCreator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BIPMonitor
/*     */ {
/*     */   private CompoundType BIP;
/*     */   private CompoundType MBIP;
/*  36 */   private Map<String, Component> Map_CompName_Component = new HashMap<String, Component>();
/*  37 */   private List<PortType> LPortType = new LinkedList<PortType>();
/*  38 */   private List<Component> LComponent = new LinkedList<Component>();
/*  39 */   private List<Port> LPort = new LinkedList<Port>();
/*     */ 
/*     */ 
/*     */   
/*  43 */   private List<String> LComponentNameToMonitor = new LinkedList<String>();
/*  44 */   private List<List<String>> LLVariableNameToMonitor = new LinkedList<List<String>>();
/*  45 */   private Map<Component, List<Variable>> Map_Component_VariableToMonitor = new HashMap<Component, List<Variable>>();
/*  46 */   private Map<Component, MAtomType> Map_Component_ATMonitor = new HashMap<Component, MAtomType>();
/*     */   
/*     */   private ConnectorType MultipleTrigger;
/*     */   
/*     */   private ConnectorType MultipleSync;
/*     */   private Connector ConnectorMultipleSync;
/*     */   private Connector ConnectorSyncTOP;
/*     */   private Connector InitialConnectorMultipleSync;
/*     */   private Connector InitialConnectorSyncTOP;
/*     */   private ConnectorType StrongSynchro;
/*     */   public static Module BIPmodule;
/*     */   Monitor monitor;
/*     */   private Component componentMonitor;
/*     */   
/*     */   public BIPMonitor(CompoundType BIP, String MonitorXMLPath, String Map_Event_Guard, String GuideMonitor) throws FileNotFoundException {
/*  61 */     BIPmodule = BIP.getModule();
/*  62 */     setComponentsVariablesToMonitor(GuideMonitor);
/*  63 */     this.BIP = BIP;
/*  64 */     setMaps();
/*  65 */     this.monitor = new Monitor(MonitorXMLPath, Map_Event_Guard, this.Map_Component_VariableToMonitor, this.LComponent);
/*  66 */     this.MultipleTrigger = TransformationFunction.CreateConnectorTypeMultipleTrigger(this.LPortType, this.monitor.getPM().getType());
/*  67 */     this.MultipleSync = TransformationFunction.CreateConnectorTypeMultipleSync(this.LPortType, this.monitor.getPM().getType());
/*  68 */     this.StrongSynchro = TransformationFunction.CreateConnectorTypeSendReceive(this.monitor.getPM().getType());
/*  69 */     BIPmodule.getBipType().add(this.MultipleTrigger);
/*  70 */     BIPmodule.getBipType().add(this.MultipleSync);
/*     */     
/*  72 */     BIPmodule.getBipType().add(this.StrongSynchro);
/*  73 */     setMBIP();
/*     */   }
/*     */   
/*     */   private void setMaps() {
/*  77 */     setMap_CompName_Component();
/*  78 */     setMap_Component_ATMonitor();
/*     */   }
/*     */   
/*     */   private void setMBIP() {
/*  82 */     this.MBIP = TransformationFunction.getCopy(this.BIP);
/*  83 */     this.MBIP.setName(String.valueOf(this.MBIP.getName()) + "_Monitor");
/*  84 */     this.componentMonitor = TransformationFunction.CreateComponent("Monitor_Instance", (ComponentType)this.monitor.getMAT(), this.MBIP, null);
/*  85 */     UpdateComponents();
/*  86 */     UpdateConnectors();
/*  87 */     AddPriority();
/*  88 */     BIPmodule.getBipType().add(this.MBIP);
/*  89 */     System sys = (System)BIPmodule;
/*  90 */     sys.getRoot().setType((ComponentType)this.MBIP);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void AddPriority() {
/*  96 */     String name = "priority_Monitor";
/*  97 */     int index = 0;
/*  98 */     for (Connector c : this.MBIP.getConnector()) {
/*     */       
/* 100 */       if (c != this.ConnectorMultipleSync && c != this.ConnectorSyncTOP && c != this.InitialConnectorMultipleSync && c != this.InitialConnectorSyncTOP) {
/*     */         
/* 102 */         BipCreator.createPriorityRule(String.valueOf(name) + index, c, this.ConnectorSyncTOP, this.MBIP);
/* 103 */         index++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void UpdateComponents() {
/* 109 */     for (Object o : this.BIP.getSubcomponent()) {
/* 110 */       Component component = (Component)o;
/* 111 */       if (this.Map_Component_ATMonitor.containsKey(component)) {
/* 112 */         Component newcomonent = (Component)this.MBIP.getSubcomponent().get(this.BIP.getSubcomponent().indexOf(component));
/* 113 */         newcomonent.setType((ComponentType)((MAtomType)this.Map_Component_ATMonitor.get(component)).getMAT());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void UpdateConnectors() {
/* 120 */     this.ConnectorMultipleSync = TransformationFunction.CreateConnector("conn_sync_monitor", this.MultipleTrigger, this.MBIP, this.LComponent, this.LPort);
/* 121 */     List<Part> LPart = new LinkedList<Part>();
/* 122 */     LPart.add(this.ConnectorMultipleSync);
/* 123 */     LPart.add(this.componentMonitor);
/* 124 */     List<Port> LPort2 = new LinkedList<Port>();
/* 125 */     LPort2.add(this.MultipleTrigger.getPort());
/* 126 */     LPort2.add(this.monitor.getPM());
/* 127 */     this.ConnectorSyncTOP = TransformationFunction.createConnector("conn_syncTOP", this.StrongSynchro, this.MBIP, LPart, LPort2);
/*     */   }
/*     */   
/*     */   private void setMap_Component_ATMonitor() {
/* 131 */     for (String o : this.LComponentNameToMonitor) {
/* 132 */       String CompName = o;
/* 133 */       Component component = this.Map_CompName_Component.get(CompName);
/* 134 */       AtomType atomType = (AtomType)component.getType();
/* 135 */       List<String> LVariableName = this.LLVariableNameToMonitor.get(this.LComponentNameToMonitor.indexOf(CompName));
/* 136 */       List<Variable> LVariable = TransformationFunction.getLVariable(atomType, LVariableName);
/* 137 */       Variable PortNameVar = TransformationFunction.CreateStringVariable("PortName", "\"\"");
/* 138 */       Variable StateNameVar = TransformationFunction.CreateStringVariable("StateName", "\"\"");
/* 139 */       LVariable.add(PortNameVar);
/* 140 */       LVariable.add(StateNameVar);
/* 141 */       this.Map_Component_VariableToMonitor.put(component, LVariable);
/* 142 */       MAtomType MAT = new MAtomType(component, LVariable);
/* 143 */       this.Map_Component_ATMonitor.put(component, MAT);
/* 144 */       this.LPortType.add(MAT.getMPort().getType());
/* 145 */       this.LPort.add(MAT.getMPort());
/* 146 */       this.LComponent.add(component);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMap_CompName_Component() {
/* 151 */     for (Object o : this.BIP.getSubcomponent()) {
/* 152 */       Component component = (Component)o;
/* 153 */       this.Map_CompName_Component.put(component.getName(), component);
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
/*     */   private void setComponentsVariablesToMonitor(String GuideMonitor) {
/*     */     try {
/* 166 */       File file = new File(GuideMonitor);
/* 167 */       FileInputStream fis = new FileInputStream(file);
/*     */       
/* 169 */       BufferedInputStream bis = new BufferedInputStream(fis);
/* 170 */       DataInputStream dis = new DataInputStream(bis);
/* 171 */       String Line = new String();
/* 172 */       String[] ComponentsVariables = (String[])null;
/* 173 */       while ((Line = dis.readLine()) != null) {
/* 174 */         if (!Line.equals(" ") && !Line.equals("")) {
/* 175 */           ComponentsVariables = Line.split(":");
/* 176 */           this.LComponentNameToMonitor.add(ComponentsVariables[0].trim());
/* 177 */           List<String> LVariableNameToMonitorForComp = new LinkedList<String>();
/* 178 */           for (int i = 1; i < ComponentsVariables.length; i++) {
/* 179 */             LVariableNameToMonitorForComp.add(ComponentsVariables[i].trim());
/*     */           }
/* 181 */           this.LLVariableNameToMonitor.add(LVariableNameToMonitorForComp);
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 187 */     catch (Exception E) {
/* 188 */       System.out.println("Error Reading File for Guide Monitor : " + E);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType getBIP() {
/* 198 */     return this.BIP;
/*     */   }
/*     */   
/*     */   public CompoundType getMBIP() {
/* 202 */     return this.MBIP;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\rv\BIPMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */