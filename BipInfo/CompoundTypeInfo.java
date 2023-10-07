/*     */ package BipInfo;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import distributed.DList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompoundTypeInfo
/*     */ {
/*     */   protected CompoundType compoundType;
/*  24 */   protected List<List<Connector>> ConflictConnector = new LinkedList<List<Connector>>();
/*  25 */   protected List<Connector> LConnector = new LinkedList<Connector>();
/*     */   protected boolean[][] ConflictConnbyConn;
/*     */   protected boolean[][] ConflictConnbyConnWithoutTransitive;
/*     */   protected int NumConnector;
/*  29 */   protected Map<AtomType, AtomTypeInfo> Map_AT_ATInfo = new HashMap<AtomType, AtomTypeInfo>();
/*  30 */   protected Map<Connector, ConnectorInfo> Map_Conn_ConnInfo = new HashMap<Connector, ConnectorInfo>();
/*     */ 
/*     */   
/*     */   protected Module module;
/*     */ 
/*     */   
/*     */   public CompoundTypeInfo(CompoundType compoundType) {
/*  37 */     this.compoundType = compoundType;
/*  38 */     this.module = compoundType.getModule();
/*  39 */     this.LConnector = (List<Connector>)compoundType.getConnector();
/*  40 */     setMap_AT_ATInfo();
/*  41 */     setMap_Conn_ConnInfo();
/*  42 */     this.NumConnector = this.LConnector.size();
/*  43 */     this.ConflictConnbyConn = new boolean[this.NumConnector][this.NumConnector];
/*  44 */     this.ConflictConnbyConnWithoutTransitive = new boolean[this.NumConnector][this.NumConnector];
/*  45 */     resetConflictConnbyConn();
/*  46 */     setConflictConnbyConn();
/*  47 */     TransitiveClosureConflictConnector();
/*  48 */     setConflictConnector();
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetConflictConnbyConn() {
/*  53 */     for (int i = 0; i < this.NumConnector; i++)
/*  54 */       this.ConflictConnbyConn[i][i] = true; 
/*     */   }
/*     */   
/*     */   private void setMap_Conn_ConnInfo() {
/*  58 */     for (Connector o : this.LConnector) {
/*     */       
/*  60 */       Connector connector = o;
/*  61 */       ConnectorInfo connectorInfo = new ConnectorInfo(connector);
/*  62 */       this.Map_Conn_ConnInfo.put(connector, connectorInfo);
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
/*     */   private void TransitiveClosureConflictConnector() {
/*  74 */     for (int k = 0; k < this.NumConnector; k++) {
/*  75 */       for (int i = 0; i < this.NumConnector; i++) {
/*  76 */         for (int j = 0; j < this.NumConnector; j++)
/*  77 */           this.ConflictConnbyConn[i][j] = this.ConflictConnbyConn[i][j] | this.ConflictConnbyConn[i][k] & this.ConflictConnbyConn[k][j]; 
/*     */       } 
/*     */     } 
/*     */   } private void setConflictConnbyConn() {
/*  81 */     EList<Connector> eList = this.compoundType.getConnector();
/*  82 */     for (int i = 0; i < this.NumConnector - 1; i++) {
/*  83 */       for (int j = i + 1; j < this.NumConnector; j++) {
/*  84 */         if (IsConflictConn(eList.get(i), eList.get(j)).size() > 0) {
/*  85 */           this.ConflictConnbyConn[i][j] = true;
/*  86 */           this.ConflictConnbyConn[j][i] = true;
/*  87 */           this.ConflictConnbyConnWithoutTransitive[i][j] = true;
/*  88 */           this.ConflictConnbyConnWithoutTransitive[j][i] = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConflictConnector() {
/*  98 */     for (int i = 0; i < this.NumConnector; i++) {
/*     */       
/* 100 */       List<Connector> LConflictConn = new LinkedList<Connector>();
/* 101 */       for (int j = 0; j < this.NumConnector; j++) {
/* 102 */         if (this.ConflictConnbyConn[i][j])
/* 103 */           LConflictConn.add(this.LConnector.get(j)); 
/* 104 */       }  this.ConflictConnector.add(LConflictConn);
/*     */     } 
/* 106 */     this.ConflictConnector = DList.MakeListofListUnique1(this.ConflictConnector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Component> IsConflictConn(Connector C1, Connector C2) {
/* 113 */     List<Component> LConflictComponent = new LinkedList<Component>();
/* 114 */     ConnectorInfo DConn1 = this.Map_Conn_ConnInfo.get(C1);
/* 115 */     ConnectorInfo DConn2 = this.Map_Conn_ConnInfo.get(C2);
/* 116 */     List<Component> LComponent1 = DConn1.getLCompenent();
/* 117 */     List<Component> LComponent2 = DConn2.getLCompenent();
/* 118 */     List<Port> LPort1 = DConn1.getPort();
/* 119 */     List<Port> LPort2 = DConn2.getPort();
/* 120 */     List<Component> LComponentIntersect = DList.Intersect(LComponent1, LComponent2);
/* 121 */     for (Object component_i : this.compoundType.getSubcomponent()) {
/* 122 */       if (LComponentIntersect.contains(component_i)) {
/* 123 */         Component componentintersect = (Component)component_i;
/* 124 */         int index1 = LComponent1.indexOf(componentintersect);
/* 125 */         int index2 = LComponent2.indexOf(componentintersect);
/* 126 */         Port port1 = LPort1.get(index1);
/* 127 */         Port port2 = LPort2.get(index2);
/* 128 */         if (port1.equals(port2))
/* 129 */           DList.AddUnique(componentintersect, LConflictComponent); 
/* 130 */         if (componentintersect.getType() instanceof AtomType) {
/*     */           
/* 132 */           AtomTypeInfo atomTypeInfo = this.Map_AT_ATInfo.get(componentintersect.getType());
/* 133 */           if (atomTypeInfo.IsConflict(port1, port2)) {
/* 134 */             DList.AddUnique(componentintersect, LConflictComponent);
/*     */           }
/*     */           continue;
/*     */         } 
/* 138 */         System.out.println("Error in the Example:");
/* 139 */         System.out.println("Component should be atomic");
/* 140 */         System.exit(0);
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     return LConflictComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMap_AT_ATInfo() {
/* 150 */     List<AtomType> LAtomType = TransformationFunction.getAtomType(this.module);
/* 151 */     for (AtomType o : LAtomType) {
/*     */       
/* 153 */       AtomType at = o;
/* 154 */       AtomTypeInfo atInfo = new AtomTypeInfo(at);
/* 155 */       this.Map_AT_ATInfo.put(at, atInfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Component> getIntersectConflictComponent(Connector c1, Connector c2) {
/* 162 */     return IsConflictConn(c1, c2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Component> getConnectorExternalConflict(Connector connector, List<Connector> LGroupConnector) {
/* 172 */     List<Component> LConflictComponent = new LinkedList<Component>();
/* 173 */     int indexofconnector = this.LConnector.indexOf(connector);
/* 174 */     for (int i = 0; i < this.NumConnector; i++) {
/* 175 */       if (i != indexofconnector) {
/* 176 */         Connector ConnectorToCompare = this.LConnector.get(i);
/* 177 */         if (!LGroupConnector.contains(ConnectorToCompare)) {
/* 178 */           DList.AddListUnique(IsConflictConn(connector, ConnectorToCompare), LConflictComponent);
/*     */         }
/*     */       } 
/*     */     } 
/* 182 */     return LConflictComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Connector> getConnectorDirectConflict(Connector connector) {
/* 192 */     List<Connector> LDirectConflictConnector = new LinkedList<Connector>();
/* 193 */     int indexofconnector = this.LConnector.indexOf(connector);
/* 194 */     for (int i = 0; i < this.NumConnector; i++) {
/* 195 */       if (i != indexofconnector && 
/* 196 */         this.ConflictConnbyConnWithoutTransitive[i][indexofconnector]) {
/* 197 */         Connector ConnectorToCompare = this.LConnector.get(i);
/* 198 */         LDirectConflictConnector.add(ConnectorToCompare);
/*     */       } 
/*     */     } 
/*     */     
/* 202 */     return LDirectConflictConnector;
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
/*     */   public List<Component> getComponent(List<Connector> LConnector) {
/* 214 */     List<Component> LComponent = new LinkedList<Component>();
/* 215 */     for (Connector o : LConnector) {
/* 216 */       Connector connector = o;
/* 217 */       ConnectorInfo connectorInfo = this.Map_Conn_ConnInfo.get(connector);
/* 218 */       DList.AddListUnique(connectorInfo.getLCompenent(), LComponent);
/*     */     } 
/* 220 */     return LComponent;
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
/*     */   public List<InnerPortReference> getLIPR(List<Connector> LConnector) {
/* 232 */     List<InnerPortReference> LIPR = new LinkedList<InnerPortReference>();
/* 233 */     for (Connector o : LConnector) {
/*     */       
/* 235 */       Connector connector = o;
/* 236 */       for (Object o1 : connector.getActualPort()) {
/*     */         
/* 238 */         InnerPortReference iprtoadd = (InnerPortReference)o1;
/* 239 */         Component comptoadd = (Component)iprtoadd.getTargetInstance().getTargetPart();
/* 240 */         Port porttoadd = iprtoadd.getTargetPort();
/* 241 */         String compName = comptoadd.getName();
/* 242 */         String portName = porttoadd.getName();
/* 243 */         for (InnerPortReference o2 : LIPR) {
/*     */           
/* 245 */           InnerPortReference ipr = o2;
/* 246 */           Component comp = (Component)ipr.getTargetInstance().getTargetPart();
/* 247 */           Port port = ipr.getTargetPort();
/* 248 */           if (comp.getName().equals(compName) && port.getName().equals(portName))
/*     */             break; 
/*     */         } 
/* 251 */         LIPR.add(iprtoadd);
/*     */       } 
/*     */     } 
/* 254 */     return LIPR;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundType getCompoundType() {
/* 259 */     return this.compoundType;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConnectorInfo getConnectorInfo(Connector conn) {
/* 264 */     return this.Map_Conn_ConnInfo.get(conn);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BipInfo\CompoundTypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */