/*     */ package distributed;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
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
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DCompoundType
/*     */ {
/*     */   private CompoundType CompType;
/*     */   private CompoundType CompTypeNew;
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
/*     */   private Module module;
/*  41 */   static List<DAtomType> LDAtomType = new LinkedList<DAtomType>();
/*  42 */   static List<PortType> LPortType = new LinkedList<PortType>();
/*  43 */   static List<ConnectorType> LConnectorType = new LinkedList<ConnectorType>();
/*  44 */   static int connitt = 0;
/*  45 */   static int connVerifJ = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static PortType InternPortType;
/*     */ 
/*     */ 
/*     */   
/*     */   public DCompoundType(CompoundType CompType) {
/*  54 */     this.CompType = CompType;
/*  55 */     this.module = CompType.getModule();
/*  56 */     setInternPortType();
/*  57 */     this.NumConn = CompType.getConnector().size();
/*  58 */     setLConnector();
/*  59 */     setLDAtomType();
/*  60 */     this.ConflictConnbyConn = new boolean[this.NumConn][this.NumConn];
/*  61 */     resetConflictConnbyConn();
/*  62 */     setConflictConnbyConn();
/*  63 */     TransitiveClosureConflictConnector();
/*  64 */     setConflictConnector();
/*  65 */     setConflictStringConnector();
/*  66 */     setLDConnector();
/*  67 */     setLPortConnType();
/*  68 */     setLEngine();
/*  69 */     InitCompTypeNew();
/*  70 */     setLCompNew();
/*  71 */     setLEngineComponent();
/*  72 */     CreateConnectorPort();
/*  73 */     CreateConnectorClassPort();
/*  74 */     CreateConnectorForVerif();
/*     */   }
/*     */   
/*     */   private void setInternPortType() {
/*  78 */     for (Object o : this.module.getBipType()) {
/*  79 */       if (o instanceof PortType) {
/*  80 */         PortType pt = (PortType)o;
/*  81 */         if (pt.getName().equals("Port")) {
/*  82 */           InternPortType = pt;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateConnectorForVerif() {
/*  92 */     for (DEngine o : this.LEngine) {
/*     */       
/*  94 */       DEngine Deng = o;
/*  95 */       List<Port> LPort = Deng.getLPortInter();
/*  96 */       Component comp = getCorrespondEngineComp(Deng);
/*  97 */       for (Port o1 : LPort) {
/*     */         
/*  99 */         Port port = o1;
/* 100 */         Connector Conn = InteractionsFactory.eINSTANCE.createConnector();
/* 101 */         Conn.setName("PrintInteraction" + connVerifJ);
/* 102 */         connVerifJ++;
/* 103 */         Conn.setType(TransformationFunction.ConnSyn);
/* 104 */         Conn.setCompoundType(this.CompTypeNew);
/* 105 */         InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 106 */         ipr.setTargetPort(port);
/* 107 */         PartElementReference PE = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 108 */         PE.setTargetPart((Part)comp);
/* 109 */         ipr.setTargetInstance(PE);
/* 110 */         Conn.getActualPort().add(ipr);
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
/* 123 */     for (DEngine o : this.LEngine) {
/*     */       
/* 125 */       DEngine engine = o;
/* 126 */       for (DCompPort o1 : engine.getLDCompPort()) {
/*     */         
/* 128 */         DCompPort dcompport = o1;
/* 129 */         for (Port o2 : dcompport.getLConflictPortDist()) {
/*     */           
/* 131 */           Port pdistcomp = o2;
/* 132 */           Port pengine = dcompport.getLConflictPortEng().get(dcompport.getLConflictPortDist().indexOf(o2));
/* 133 */           Component c = dcompport.getComponent();
/* 134 */           Component cdist = getLCompNew().get(this.CompType.getSubcomponent().indexOf(c));
/* 135 */           Component compengine = LEngineComponent.get(this.LEngine.indexOf(engine));
/*     */           
/* 137 */           ConnectorType conntype = LConnectorType.get(LPortType.indexOf(pdistcomp.getType()));
/* 138 */           Connector connector = InteractionsFactory.eINSTANCE.createConnector();
/* 139 */           connector.setType(conntype);
/*     */           
/* 141 */           connector.setName("conn_" + connitt);
/* 142 */           connitt++;
/*     */           
/* 144 */           InnerPortReference iprdistcom = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 145 */           PartElementReference PEdistcom = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 146 */           PEdistcom.setTargetPart((Part)cdist);
/* 147 */           iprdistcom.setTargetInstance(PEdistcom);
/* 148 */           iprdistcom.setTargetPort(pdistcomp);
/*     */           
/* 150 */           InnerPortReference ipreng = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 151 */           PartElementReference PEeng = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 152 */           PEeng.setTargetPart((Part)compengine);
/* 153 */           ipreng.setTargetInstance(PEeng);
/* 154 */           ipreng.setTargetPort(pengine);
/*     */           
/* 156 */           connector.getActualPort().add(iprdistcom);
/* 157 */           connector.getActualPort().add(ipreng);
/* 158 */           connector.setCompoundType(this.CompTypeNew);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CreateConnectorPort() {
/* 168 */     for (Object o : this.CompType.getSubcomponent()) {
/*     */       
/* 170 */       Component c = (Component)o;
/* 171 */       AtomType at = (AtomType)c.getType();
/* 172 */       DAtomType dat = getDAtomType(at);
/* 173 */       for (Object o1 : at.getPort()) {
/*     */         
/* 175 */         Port p = (Port)o1;
/* 176 */         Port pdistcomp = dat.getDPort(p.getName());
/* 177 */         List<Port> LPortCompeng = getPortEngine(c, p);
/* 178 */         Port pengine = LPortCompeng.get(0);
/* 179 */         Component compengine = (Component)LPortCompeng.get(1);
/*     */ 
/*     */         
/* 182 */         ConnectorType conntype = LConnectorType.get(LPortType.indexOf(p.getType()));
/* 183 */         Connector connector = InteractionsFactory.eINSTANCE.createConnector();
/* 184 */         connector.setType(conntype);
/*     */         
/* 186 */         connector.setName("conn_" + connitt);
/* 187 */         connitt++;
/* 188 */         InnerPortReference iprdistcom = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 189 */         PartElementReference PEdistcom = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 190 */         PEdistcom.setTargetPart((Part)this.LCompNew.get(this.CompType.getSubcomponent().indexOf(c)));
/* 191 */         iprdistcom.setTargetInstance(PEdistcom);
/* 192 */         iprdistcom.setTargetPort(pdistcomp);
/*     */ 
/*     */         
/* 195 */         InnerPortReference ipreng = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 196 */         PartElementReference PEeng = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 197 */         PEeng.setTargetPart((Part)compengine);
/* 198 */         ipreng.setTargetInstance(PEeng);
/* 199 */         ipreng.setTargetPort(pengine);
/*     */         
/* 201 */         connector.getActualPort().add(ipreng);
/* 202 */         connector.getActualPort().add(iprdistcom);
/* 203 */         connector.setCompoundType(this.CompTypeNew);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List getPortEngine(Component c, Port p) {
/* 211 */     List<Port> LPortCompeng = new LinkedList();
/* 212 */     for (DEngine o : this.LEngine) {
/*     */       
/* 214 */       DEngine dengine = o;
/* 215 */       for (DCompPort o1 : dengine.getLDCompPort()) {
/*     */         
/* 217 */         DCompPort dcompport = o1;
/* 218 */         if (dcompport.getComponent().equals(c)) {
/*     */           
/* 220 */           Port peng = dcompport.getCorrespondPortEng(p);
/* 221 */           if (peng != null) {
/*     */             
/* 223 */             LPortCompeng.add(peng);
/* 224 */             LPortCompeng.add((Port)LEngineComponent.get(this.LEngine.indexOf(dengine)));
/* 225 */             return LPortCompeng;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLEngineComponent() {
/* 235 */     for (DEngine o : this.LEngine) {
/*     */       
/* 237 */       DEngine deng = o;
/* 238 */       AtomType atengine = deng.getEngine();
/* 239 */       Component compengine = InteractionsFactory.eINSTANCE.createComponent();
/* 240 */       compengine.setType((ComponentType)atengine);
/* 241 */       compengine.setName(String.valueOf(atengine.getName()) + "_I");
/* 242 */       compengine.setCompoundType(this.CompTypeNew);
/* 243 */       LEngineComponent.add(compengine);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void InitCompTypeNew() {
/* 248 */     this.CompTypeNew = InteractionsFactory.eINSTANCE.createCompoundType();
/* 249 */     this.CompTypeNew.setName("TOP_Distributed");
/* 250 */     this.CompType.getModule().getBipType().add(this.CompTypeNew);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLCompNew() {
/* 255 */     for (Object o : this.CompType.getSubcomponent()) {
/*     */       
/* 257 */       Component comp = (Component)o;
/* 258 */       if (!(comp.getType() instanceof AtomType)) {
/* 259 */         System.out.println("Component " + comp.getName() + " is not atomic");
/* 260 */         System.out.println("You should to flat system before go to distributed implemetation");
/* 261 */         System.exit(0);
/*     */       } 
/* 263 */       AtomType comptype = (AtomType)comp.getType();
/* 264 */       DAtomType DAT = getDAtomType(comptype);
/* 265 */       Component compnew = InteractionsFactory.eINSTANCE.createComponent();
/* 266 */       compnew.setType((ComponentType)DAT.getDAT());
/* 267 */       compnew.setName(String.valueOf(comp.getName()) + "_dist");
/* 268 */       List LActualData = new LinkedList((Collection<?>)comp.getActualData());
/* 269 */       for (Object o1 : LActualData)
/*     */       {
/* 271 */         compnew.getActualData().add(EcoreUtil.copy((EObject)o1));
/*     */       }
/* 273 */       this.LCompNew.add(compnew);
/* 274 */       compnew.setCompoundType(this.CompTypeNew);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private DAtomType getDAtomType(AtomType at) {
/* 280 */     for (DAtomType o : LDAtomType) {
/*     */       
/* 282 */       DAtomType DAT = o;
/* 283 */       if (DAT.getCAT().equals(at))
/* 284 */         return DAT; 
/*     */     } 
/* 286 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLEngine() {
/* 291 */     for (Object<DConnector> o : this.LDConnector) {
/*     */       
/* 293 */       List<DConnector> LDConn = (List<DConnector>)o;
/* 294 */       DEngine engine = new DEngine(LDConn);
/* 295 */       this.CompType.getModule().getBipType().add(engine.getEngine());
/* 296 */       this.LEngine.add(engine);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLPortConnType() {
/* 302 */     List LBIPType = new LinkedList((Collection<?>)this.CompType.getModule().getBipType());
/* 303 */     for (Object o : LBIPType) {
/*     */       
/* 305 */       if (o instanceof PortType) {
/*     */         
/* 307 */         PortType PT = (PortType)o;
/*     */ 
/*     */         
/* 310 */         LPortType.add(PT);
/* 311 */         LConnectorType.add(TransformationFunction.CreateConnectorTypeSendReceive(PT));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLDConnector() {
/* 319 */     for (Object<Connector> o : this.ConflictConnector) {
/*     */       
/* 321 */       List<Connector> LConnector = (List<Connector>)o;
/* 322 */       List<DConnector> LDConn = new LinkedList<DConnector>();
/* 323 */       for (Connector o1 : LConnector) {
/*     */         
/* 325 */         Connector c = o1;
/* 326 */         DConnector dc = new DConnector(c);
/* 327 */         LDConn.add(dc);
/*     */       } 
/* 329 */       this.LDConnector.add(LDConn);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setLDAtomType() {
/* 334 */     List<AtomType> LAtomType = TransformationFunction.getAtomType(this.module);
/* 335 */     for (AtomType o : LAtomType) {
/*     */       
/* 337 */       DAtomType dat = new DAtomType(o);
/* 338 */       LDAtomType.add(dat);
/* 339 */       this.module.getBipType().add(o);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setConflictStringConnector() {
/* 348 */     for (Object<Connector> o : this.ConflictConnector) {
/*     */       
/* 350 */       List l = (List)o;
/* 351 */       List<String> L = new LinkedList();
/* 352 */       for (Object o1 : l) {
/*     */         
/* 354 */         Connector c = (Connector)o1;
/* 355 */         L.add(c.getName());
/*     */       } 
/* 357 */       this.ConflictStringConnector.add(L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetConflictConnbyConn() {
/* 363 */     for (int i = 0; i < this.NumConn; i++) {
/* 364 */       this.ConflictConnbyConn[i][i] = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void setConflictConnbyConn() {
/* 369 */     EList<Connector> eList = this.CompType.getConnector();
/* 370 */     for (int i = 0; i < this.NumConn - 1; i++) {
/* 371 */       for (int j = i + 1; j < this.NumConn; j++) {
/* 372 */         if (IsConflictConn(eList.get(i), eList.get(j))) {
/*     */           
/* 374 */           this.ConflictConnbyConn[i][j] = true;
/* 375 */           this.ConflictConnbyConn[j][i] = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean IsConflictConn(Connector C1, Connector C2) {
/* 382 */     DConnector DConn1 = new DConnector(C1);
/* 383 */     DConnector DConn2 = new DConnector(C2);
/* 384 */     List<Component> LComponent1 = DConn1.getLCompenent();
/* 385 */     List<Component> LComponent2 = DConn2.getLCompenent();
/* 386 */     List<String> LStringPort1 = DConn1.getStringPort();
/* 387 */     List<String> LStringPort2 = DConn2.getStringPort();
/* 388 */     List<Component> LComponentIntersect = DList.Intersect(LComponent1, LComponent2);
/* 389 */     for (Component component_i : LComponentIntersect) {
/*     */       
/* 391 */       Component componentintersect = component_i;
/* 392 */       int index1 = LComponent1.indexOf(componentintersect);
/* 393 */       int index2 = LComponent2.indexOf(componentintersect);
/* 394 */       String port1 = LStringPort1.get(index1);
/* 395 */       String port2 = LStringPort2.get(index2);
/* 396 */       if (port1.equals(port2))
/* 397 */         return true; 
/* 398 */       if (componentintersect.getType() instanceof AtomType) {
/*     */ 
/*     */         
/* 401 */         DAtomType DAtom = getDAtomType((AtomType)componentintersect.getType());
/* 402 */         boolean test = DAtom.IsConflict(port1, port2);
/* 403 */         if (test) {
/* 404 */           return true;
/*     */         }
/*     */         continue;
/*     */       } 
/* 408 */       System.out.println("Error in the Example:");
/* 409 */       System.out.println("Component should be atomic");
/* 410 */       System.exit(0);
/*     */     } 
/*     */     
/* 413 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLConnector() {
/* 419 */     this.LConnector = (List<Connector>)this.CompType.getConnector();
/* 420 */     for (Connector connector_i : this.LConnector) {
/*     */       
/* 422 */       Connector connector = connector_i;
/* 423 */       this.LStringConnector.add(connector.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void TransitiveClosureConflictConnector() {
/* 433 */     for (int k = 0; k < this.NumConn; k++) {
/* 434 */       for (int i = 0; i < this.NumConn; i++) {
/* 435 */         for (int j = 0; j < this.NumConn; j++) {
/* 436 */           this.ConflictConnbyConn[i][j] = this.ConflictConnbyConn[i][j] | this.ConflictConnbyConn[i][k] & this.ConflictConnbyConn[k][j];
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setConflictConnector() {
/* 443 */     for (int i = 0; i < this.NumConn; i++) {
/*     */       
/* 445 */       List<Connector> LConflictConn = new LinkedList<Connector>();
/* 446 */       for (int j = 0; j < this.NumConn; j++) {
/* 447 */         if (this.ConflictConnbyConn[i][j])
/* 448 */           LConflictConn.add(this.LConnector.get(j)); 
/* 449 */       }  this.ConflictConnector.add(LConflictConn);
/*     */     } 
/* 451 */     this.ConflictConnector = DList.MakeListofListUnique1(this.ConflictConnector);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType getCompoundType() {
/* 457 */     return this.CompType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getLStringConnector() {
/* 462 */     return this.LStringConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[][] getConflictConnbyConn() {
/* 467 */     return this.ConflictConnbyConn;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<Connector>> getConflictConnector() {
/* 472 */     return this.ConflictConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<String>> getConflictStringConnector() {
/* 477 */     return this.ConflictStringConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DAtomType> getLDAtomType() {
/* 482 */     return LDAtomType;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<List<DConnector>> getLDConnector() {
/* 487 */     return this.LDConnector;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DEngine> getLEngine() {
/* 492 */     return this.LEngine;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundType getCompTypeNew() {
/* 497 */     return this.CompTypeNew;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Component> getLCompNew() {
/* 502 */     return this.LCompNew;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Component> getLEngineComponent() {
/* 507 */     return LEngineComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   private Component getCorrespondEngineComp(DEngine CompCent) {
/* 512 */     return LEngineComponent.get(this.LEngine.indexOf(CompCent));
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\DCompoundType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */