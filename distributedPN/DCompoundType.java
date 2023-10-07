/*     */ package distributedPN;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesFactory;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ 
/*     */ public class DCompoundType {
/*     */   private CompoundType CompType;
/*     */   private CompoundType CompTypeNew;
/*  29 */   private List<AtomType> LAtomType = new LinkedList<AtomType>();
/*  30 */   private List<Component> LCompNew = new LinkedList<Component>();
/*  31 */   private List<String> LStringConnector = new LinkedList<String>();
/*  32 */   private List<Connector> LConnector = new LinkedList<Connector>();
/*     */   private boolean[][] ConflictConnbyConn;
/*  34 */   private List<List<Connector>> ConflictConnector = new LinkedList<List<Connector>>();
/*  35 */   private List<List<String>> ConflictStringConnector = new LinkedList<List<String>>();
/*  36 */   private List<List<DConnector>> LDConnector = new LinkedList<List<DConnector>>();
/*  37 */   private List<DEngine> LEngine = new LinkedList<DEngine>();
/*  38 */   private static List<Component> LEngineComponent = new LinkedList<Component>();
/*     */   private int NumConn;
/*  40 */   static List<DAtomType> LDAtomType = new LinkedList<DAtomType>();
/*  41 */   static List<PortType> LPortType = new LinkedList<PortType>();
/*  42 */   static List<ConnectorType> LConnectorType = new LinkedList<ConnectorType>();
/*  43 */   static int connitt = 0;
/*  44 */   static int connVerifJ = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DCompoundType(CompoundType CompType) {
/*  50 */     this.CompType = CompType;
/*  51 */     this.NumConn = CompType.getConnector().size();
/*  52 */     setLConnector();
/*  53 */     setLDAtomType();
/*  54 */     VerifyPetriNetsConflict();
/*  55 */     this.ConflictConnbyConn = new boolean[this.NumConn][this.NumConn];
/*     */     
/*  57 */     resetConflictConnbyConn();
/*  58 */     setConflictConnbyConn();
/*  59 */     TransitiveClosureConflictConnector();
/*  60 */     setConflictConnector();
/*  61 */     setConflictStringConnector();
/*  62 */     setLDConnector();
/*     */     
/*  64 */     setLPortConnType();
/*  65 */     setLEngine();
/*  66 */     InitCompTypeNew();
/*  67 */     setLCompNew();
/*  68 */     setLEngineComponent();
/*  69 */     CreateConnectorPort();
/*  70 */     CreateConnectorClassPort();
/*  71 */     CreateConnectorForVerif();
/*  72 */     System sys = (System)CompType.getModule();
/*  73 */     Root r = ModulesFactory.eINSTANCE.createRoot();
/*  74 */     r.setType((ComponentType)this.CompTypeNew);
/*  75 */     r.setName("top");
/*  76 */     sys.setRoot(r);
/*     */   }
/*     */   
/*     */   private void VerifyPetriNetsConflict() {
/*  80 */     for (AtomType o : this.LAtomType) {
/*     */       
/*  82 */       AtomType at = o;
/*  83 */       if (VerifyPetriNetConflict(at))
/*  84 */         System.exit(0); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean VerifyPetriNetConflict(AtomType at) {
/*  89 */     PetriNet pn = (PetriNet)at.getBehavior();
/*  90 */     if (TransformationFunction.IsConflictPetriNet(pn))
/*  91 */       return true; 
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateConnectorForVerif() {
/*  98 */     for (DEngine o : this.LEngine) {
/*     */       
/* 100 */       DEngine Deng = o;
/* 101 */       List<Port> LPort = Deng.getLPortInter();
/* 102 */       Component comp = getCorrespondEngineComp(Deng);
/* 103 */       for (Port o1 : LPort) {
/*     */         
/* 105 */         Port port = o1;
/* 106 */         Connector Conn = InteractionsFactory.eINSTANCE.createConnector();
/* 107 */         Conn.setName("PrintInteraction" + connVerifJ);
/* 108 */         connVerifJ++;
/* 109 */         Conn.setType(TransformationFunction.ConnSyn);
/* 110 */         Conn.setCompoundType(this.CompTypeNew);
/* 111 */         InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 112 */         ipr.setTargetPort(port);
/* 113 */         PartElementReference PE = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 114 */         PE.setTargetPart((Part)comp);
/* 115 */         ipr.setTargetInstance(PE);
/* 116 */         Conn.getActualPort().add(ipr);
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
/*     */   private void CreateConnectorClassPort() {
/* 129 */     for (DEngine o : this.LEngine) {
/*     */       
/* 131 */       DEngine engine = o;
/* 132 */       for (DCompPort o1 : engine.getLDCompPort()) {
/*     */         
/* 134 */         DCompPort dcompport = o1;
/* 135 */         for (Port o2 : dcompport.getLConflictPortDist()) {
/*     */           
/* 137 */           Port pdistcomp = o2;
/* 138 */           Port pengine = dcompport.getLConflictPortEng().get(dcompport.getLConflictPortDist().indexOf(o2));
/* 139 */           Component c = dcompport.getComponent();
/* 140 */           Component cdist = getLCompNew().get(this.CompType.getSubcomponent().indexOf(c));
/* 141 */           Component compengine = LEngineComponent.get(this.LEngine.indexOf(engine));
/*     */           
/* 143 */           ConnectorType conntype = LConnectorType.get(LPortType.indexOf(pdistcomp.getType()));
/* 144 */           Connector connector = InteractionsFactory.eINSTANCE.createConnector();
/* 145 */           connector.setType(conntype);
/*     */           
/* 147 */           connector.setName("conn_" + connitt);
/* 148 */           connitt++;
/*     */           
/* 150 */           InnerPortReference iprdistcom = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 151 */           PartElementReference PEdistcom = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 152 */           PEdistcom.setTargetPart((Part)cdist);
/* 153 */           iprdistcom.setTargetInstance(PEdistcom);
/* 154 */           iprdistcom.setTargetPort(pdistcomp);
/*     */           
/* 156 */           InnerPortReference ipreng = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 157 */           PartElementReference PEeng = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 158 */           PEeng.setTargetPart((Part)compengine);
/* 159 */           ipreng.setTargetInstance(PEeng);
/* 160 */           ipreng.setTargetPort(pengine);
/*     */           
/* 162 */           connector.getActualPort().add(iprdistcom);
/* 163 */           connector.getActualPort().add(ipreng);
/* 164 */           connector.setCompoundType(this.CompTypeNew);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateConnectorPort() {
/* 174 */     for (Object o : this.CompType.getSubcomponent()) {
/*     */       
/* 176 */       Component c = (Component)o;
/* 177 */       AtomType at = (AtomType)c.getType();
/* 178 */       DAtomType dat = getDAtomType(at);
/* 179 */       for (Object o1 : at.getPort()) {
/*     */         
/* 181 */         Port p = (Port)o1;
/* 182 */         Port pdistcomp = dat.getDPort(p.getName());
/* 183 */         List<Port> LPortCompeng = getPortEngine(c, p);
/* 184 */         Port pengine = LPortCompeng.get(0);
/* 185 */         Component compengine = (Component)LPortCompeng.get(1);
/*     */ 
/*     */         
/* 188 */         ConnectorType conntype = LConnectorType.get(LPortType.indexOf(p.getType()));
/* 189 */         Connector connector = InteractionsFactory.eINSTANCE.createConnector();
/* 190 */         connector.setType(conntype);
/*     */         
/* 192 */         connector.setName("conn_" + connitt);
/* 193 */         connitt++;
/* 194 */         InnerPortReference iprdistcom = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 195 */         PartElementReference PEdistcom = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 196 */         PEdistcom.setTargetPart((Part)this.LCompNew.get(this.CompType.getSubcomponent().indexOf(c)));
/* 197 */         iprdistcom.setTargetInstance(PEdistcom);
/* 198 */         iprdistcom.setTargetPort(pdistcomp);
/*     */ 
/*     */         
/* 201 */         InnerPortReference ipreng = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 202 */         PartElementReference PEeng = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 203 */         PEeng.setTargetPart((Part)compengine);
/* 204 */         ipreng.setTargetInstance(PEeng);
/* 205 */         ipreng.setTargetPort(pengine);
/*     */         
/* 207 */         connector.getActualPort().add(ipreng);
/* 208 */         connector.getActualPort().add(iprdistcom);
/* 209 */         connector.setCompoundType(this.CompTypeNew);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List getPortEngine(Component c, Port p) {
/* 217 */     List<Port> LPortCompeng = new LinkedList();
/* 218 */     for (DEngine o : this.LEngine) {
/*     */       
/* 220 */       DEngine dengine = o;
/* 221 */       for (DCompPort o1 : dengine.getLDCompPort()) {
/*     */         
/* 223 */         DCompPort dcompport = o1;
/* 224 */         if (dcompport.getComponent().equals(c)) {
/*     */           
/* 226 */           Port peng = dcompport.getCorrespondPortEng(p);
/* 227 */           if (peng != null) {
/*     */             
/* 229 */             LPortCompeng.add(peng);
/* 230 */             LPortCompeng.add((Port)LEngineComponent.get(this.LEngine.indexOf(dengine)));
/* 231 */             return LPortCompeng;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLEngineComponent() {
/* 241 */     for (DEngine o : this.LEngine) {
/*     */       
/* 243 */       DEngine deng = o;
/* 244 */       AtomType atengine = deng.getEngine();
/* 245 */       Component compengine = InteractionsFactory.eINSTANCE.createComponent();
/* 246 */       compengine.setType((ComponentType)atengine);
/* 247 */       compengine.setName(String.valueOf(atengine.getName()) + "_I");
/* 248 */       compengine.setCompoundType(this.CompTypeNew);
/* 249 */       LEngineComponent.add(compengine);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void InitCompTypeNew() {
/* 254 */     this.CompTypeNew = InteractionsFactory.eINSTANCE.createCompoundType();
/* 255 */     this.CompTypeNew.setName("TOP_Distributed");
/* 256 */     this.CompType.getModule().getBipType().add(this.CompTypeNew);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLCompNew() {
/* 261 */     for (Object o : this.CompType.getSubcomponent()) {
/*     */       
/* 263 */       Component comp = (Component)o;
/* 264 */       AtomType comptype = (AtomType)comp.getType();
/* 265 */       DAtomType DAT = getDAtomType(comptype);
/* 266 */       Component compnew = InteractionsFactory.eINSTANCE.createComponent();
/* 267 */       compnew.setType((ComponentType)DAT.getDAT());
/* 268 */       compnew.setName(String.valueOf(comp.getName()) + "_dist");
/* 269 */       List LActualData = new LinkedList((Collection<?>)comp.getActualData());
/* 270 */       for (Object o1 : LActualData)
/*     */       {
/* 272 */         compnew.getActualData().add(EcoreUtil.copy((EObject)o1));
/*     */       }
/* 274 */       this.LCompNew.add(compnew);
/* 275 */       compnew.setCompoundType(this.CompTypeNew);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private DAtomType getDAtomType(AtomType at) {
/* 281 */     for (DAtomType o : LDAtomType) {
/*     */       
/* 283 */       DAtomType DAT = o;
/* 284 */       if (DAT.getCAT().equals(at))
/* 285 */         return DAT; 
/*     */     } 
/* 287 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLEngine() {
/* 292 */     for (Object<DConnector> o : this.LDConnector) {
/*     */       
/* 294 */       List<DConnector> LDConn = (List<DConnector>)o;
/* 295 */       DEngine engine = new DEngine(LDConn);
/* 296 */       this.CompType.getModule().getBipType().add(engine.getEngine());
/* 297 */       this.LEngine.add(engine);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLPortConnType() {
/* 303 */     List LBIPType = new LinkedList((Collection<?>)this.CompType.getModule().getBipType());
/* 304 */     for (Object o : LBIPType) {
/*     */       
/* 306 */       if (o instanceof PortType) {
/*     */         
/* 308 */         PortType PT = (PortType)o;
/*     */ 
/*     */         
/* 311 */         LPortType.add(PT);
/* 312 */         LConnectorType.add(TransformationFunction.CreateConnectorTypeSend(PT));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLDConnector() {
/* 320 */     for (Object<Connector> o : this.ConflictConnector) {
/*     */       
/* 322 */       List<Connector> LConnector = (List<Connector>)o;
/* 323 */       List<DConnector> LDConn = new LinkedList<DConnector>();
/* 324 */       for (Connector o1 : LConnector) {
/*     */         
/* 326 */         Connector c = o1;
/* 327 */         DConnector dc = new DConnector(c);
/* 328 */         LDConn.add(dc);
/*     */       } 
/* 330 */       this.LDConnector.add(LDConn);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setLDAtomType() {
/* 335 */     this.LAtomType = TransformationFunction.getAtomType();
/* 336 */     for (AtomType o : this.LAtomType) {
/*     */       
/* 338 */       DAtomType dat = new DAtomType(o);
/* 339 */       LDAtomType.add(dat);
/* 340 */       TransformationFunction.module.getBipType().add(o);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConflictStringConnector() {
/* 350 */     for (Object<Connector> o : this.ConflictConnector) {
/*     */       
/* 352 */       List l = (List)o;
/* 353 */       List<String> L = new LinkedList();
/* 354 */       for (Object o1 : l) {
/*     */         
/* 356 */         Connector c = (Connector)o1;
/* 357 */         L.add(c.getName());
/*     */       } 
/* 359 */       this.ConflictStringConnector.add(L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetConflictConnbyConn() {
/* 365 */     for (int i = 0; i < this.NumConn; i++) {
/* 366 */       for (int j = 0; j < this.NumConn; j++) {
/*     */         
/* 368 */         if (i != j) {
/* 369 */           this.ConflictConnbyConn[i][j] = false;
/*     */         } else {
/* 371 */           this.ConflictConnbyConn[i][j] = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void setConflictConnbyConn() {
/* 377 */     EList<Connector> eList = this.CompType.getConnector();
/* 378 */     for (int i = 0; i < this.NumConn - 1; i++) {
/* 379 */       for (int j = i + 1; j < this.NumConn; j++) {
/* 380 */         if (IsConflictConn(eList.get(i), eList.get(j))) {
/*     */           
/* 382 */           this.ConflictConnbyConn[i][j] = true;
/* 383 */           this.ConflictConnbyConn[j][i] = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean IsConflictConn(Connector C1, Connector C2) {
/* 390 */     DConnector DConn1 = new DConnector(C1);
/* 391 */     DConnector DConn2 = new DConnector(C2);
/* 392 */     List<Component> LComponent1 = DConn1.getLCompenent();
/* 393 */     List<Component> LComponent2 = DConn2.getLCompenent();
/* 394 */     List<String> LStringPort1 = DConn1.getStringPort();
/* 395 */     List<String> LStringPort2 = DConn2.getStringPort();
/* 396 */     List<Component> LComponentIntersect = DList.Intersect(LComponent1, LComponent2);
/* 397 */     for (Component component_i : LComponentIntersect) {
/*     */       
/* 399 */       Component componentintersect = component_i;
/* 400 */       int index1 = LComponent1.indexOf(componentintersect);
/* 401 */       int index2 = LComponent2.indexOf(componentintersect);
/* 402 */       String port1 = LStringPort1.get(index1);
/* 403 */       String port2 = LStringPort2.get(index2);
/* 404 */       if (port1.equals(port2))
/* 405 */         return true; 
/* 406 */       if (componentintersect.getType() instanceof AtomType) {
/*     */ 
/*     */         
/* 409 */         DAtomType DAtom = getDAtomType((AtomType)componentintersect.getType());
/* 410 */         boolean test = DAtom.IsConflict(port1, port2);
/* 411 */         if (test) {
/* 412 */           return true;
/*     */         }
/*     */         continue;
/*     */       } 
/* 416 */       System.out.println("Error in the Example:");
/* 417 */       System.out.println("Component should be atomic");
/* 418 */       System.exit(0);
/*     */     } 
/*     */     
/* 421 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLConnector() {
/* 427 */     this.LConnector = (List<Connector>)this.CompType.getConnector();
/* 428 */     for (Connector connector_i : this.LConnector) {
/* 429 */       this.LStringConnector.add(connector_i.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void TransitiveClosureConflictConnector() {
/* 439 */     for (int k = 0; k < this.NumConn; k++) {
/* 440 */       for (int i = 0; i < this.NumConn; i++) {
/* 441 */         for (int j = 0; j < this.NumConn; j++) {
/* 442 */           this.ConflictConnbyConn[i][j] = this.ConflictConnbyConn[i][j] | this.ConflictConnbyConn[i][k] & this.ConflictConnbyConn[k][j];
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setConflictConnector() {
/* 449 */     for (int i = 0; i < this.NumConn; i++) {
/*     */       
/* 451 */       List<Connector> LConflictConn = new LinkedList<Connector>();
/* 452 */       for (int j = 0; j < this.NumConn; j++) {
/* 453 */         if (this.ConflictConnbyConn[i][j])
/* 454 */           LConflictConn.add(this.LConnector.get(j)); 
/* 455 */       }  this.ConflictConnector.add(LConflictConn);
/*     */     } 
/* 457 */     this.ConflictConnector = DList.MakeListofListUnique1(this.ConflictConnector);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType getCompoundType() {
/* 463 */     return this.CompType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getLStringConnector() {
/* 468 */     return this.LStringConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[][] getConflictConnbyConn() {
/* 473 */     return this.ConflictConnbyConn;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Connector>> getConflictConnector() {
/* 478 */     return this.ConflictConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<String>> getConflictStringConnector() {
/* 483 */     return this.ConflictStringConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DAtomType> getLDAtomType() {
/* 488 */     return LDAtomType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<DConnector>> getLDConnector() {
/* 493 */     return this.LDConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DEngine> getLEngine() {
/* 498 */     return this.LEngine;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundType getCompTypeNew() {
/* 503 */     return this.CompTypeNew;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Component> getLCompNew() {
/* 508 */     return this.LCompNew;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Component> getLEngineComponent() {
/* 513 */     return LEngineComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   private Component getCorrespondEngineComp(DEngine CompCent) {
/* 518 */     return LEngineComponent.get(this.LEngine.indexOf(CompCent));
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\DCompoundType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */