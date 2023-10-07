/*     */ package BipInfo;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentInfo
/*     */ {
/*     */   private Component component;
/*  22 */   private Map<Port, List<Connector>> Map_Port_LInteraction = new HashMap<Port, List<Connector>>();
/*     */ 
/*     */   
/*     */   public ComponentInfo(Component component) {
/*  26 */     this.component = component;
/*  27 */     setMap_PortLInteraction();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_PortLInteraction() {
/*  35 */     CompoundType compoundType = this.component.getCompoundType();
/*  36 */     for (Object o : compoundType.getConnector()) {
/*     */       
/*  38 */       Connector connector = (Connector)o;
/*  39 */       for (Object o1 : connector.getActualPort()) {
/*     */         
/*  41 */         InnerPortReference IPR = (InnerPortReference)o1;
/*  42 */         if (IPR.getTargetInstance().getTargetPart() instanceof Component) {
/*     */           
/*  44 */           Component componentToCompare = (Component)IPR.getTargetInstance().getTargetPart();
/*  45 */           Port portToCompare = IPR.getTargetPort();
/*  46 */           if (componentToCompare.equals(this.component)) {
/*     */             
/*  48 */             if (this.Map_Port_LInteraction.containsKey(portToCompare)) {
/*  49 */               List<Connector> list = this.Map_Port_LInteraction.get(portToCompare);
/*  50 */               list.add(connector);
/*     */               break;
/*     */             } 
/*  53 */             List<Connector> LConnector = new LinkedList<Connector>();
/*  54 */             LConnector.add(connector);
/*  55 */             this.Map_Port_LInteraction.put(portToCompare, LConnector);
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*  62 */         System.out.println("System should be flat. \n Connector " + 
/*  63 */             connector.getName() + " in the compound type " + compoundType + " it is not connected to a component");
/*  64 */         System.exit(0);
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
/*     */   
/*     */   public Port getPort(String namePort) {
/*  78 */     for (Object o : this.component.getType().getPort()) {
/*     */       
/*  80 */       Port p = (Port)o;
/*  81 */       if (p.getName().equals(namePort))
/*  82 */         return p; 
/*     */     } 
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Connector> getLInteractionOfPort(Port p) {
/*  94 */     return this.Map_Port_LInteraction.get(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Connector> getLInteractionOfComponent() {
/* 103 */     List<Connector> LConnector = new LinkedList<Connector>();
/* 104 */     for (Port o : this.Map_Port_LInteraction.keySet())
/*     */     {
/* 106 */       LConnector.addAll(this.Map_Port_LInteraction.get(o));
/*     */     }
/* 108 */     return LConnector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getComponent() {
/* 115 */     return this.component;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BipInfo\ComponentInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */