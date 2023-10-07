/*     */ package trans;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ 
/*     */ public class OptimizedReconstruction
/*     */ {
/*  26 */   private static List<List> Partition = new LinkedList<List>();
/*  27 */   private static List<List> Tree = new LinkedList<List>();
/*     */   private CompoundType CompType;
/*  29 */   static int CounterComName = 0;
/*     */ 
/*     */   
/*     */   Module module;
/*     */ 
/*     */   
/*     */   public OptimizedReconstruction(CompoundType ComType, List<List> Tree) {
/*  36 */     this.CompType = ComType;
/*  37 */     this.module = ComType.getModule();
/*  38 */     OptimizedReconstruction.Tree = Tree;
/*  39 */     Reconstruct();
/*  40 */     CleanUnnecessaryExportPorts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CleanUnnecessaryExportPorts() {
/*  47 */     EList eList = this.CompType.getConnector();
/*  48 */     for (Component component : this.CompType.getSubcomponent()) {
/*  49 */       if (component.getType() instanceof CompoundType) {
/*  50 */         CompoundType compoundType = (CompoundType)component.getType();
/*  51 */         List<Port> LPort = new LinkedList<Port>((Collection<? extends Port>)compoundType.getPort());
/*  52 */         for (Port port : LPort) {
/*  53 */           if (!IsPortConnected(port, component, (List<Connector>)eList)) {
/*  54 */             compoundType.getPort().remove(port);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean IsPortConnected(Port port, Component component, List<Connector> LConnector) {
/*  62 */     for (Connector connector : LConnector) {
/*  63 */       for (ActualPortParameter app : connector.getActualPort()) {
/*  64 */         assert app instanceof InnerPortReference;
/*  65 */         InnerPortReference ipr = (InnerPortReference)app;
/*  66 */         if (ipr.getTargetPort().getName().equals(port.getName()) && 
/*  67 */           ipr.getTargetInstance().getTargetPart().getName().equals(component.getName()))
/*  68 */           return true; 
/*     */       } 
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String GetCompoundName(List<String> L) {
/*  78 */     String CompName = new String();
/*  79 */     for (String o : L) {
/*     */       
/*  81 */       String S = o;
/*  82 */       CompName = String.valueOf(CompName) + S;
/*     */     } 
/*  84 */     return CompName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCompoundTypetType(CompoundType CT) {
/*  90 */     int size = this.module.getBipType().size();
/*  91 */     this.module.getBipType().add(size - 2, CT);
/*     */   }
/*     */   
/*     */   private void AddComponent(CompoundType CT) {
/*  95 */     Component Comp = InteractionsFactory.eINSTANCE.createComponent();
/*  96 */     Comp.setName(String.valueOf(CT.getName()) + "_I");
/*  97 */     Comp.setType((ComponentType)CT);
/*  98 */     Comp.setCompoundType(this.CompType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Component GetComponent(String name) {
/* 104 */     EList LComp = this.CompType.getSubcomponent();
/* 105 */     for (Object o : LComp) {
/*     */       
/* 107 */       Component C = (Component)o;
/* 108 */       if (C.getName().equals(name))
/* 109 */         return C; 
/*     */     } 
/* 111 */     return null;
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
/*     */   void AddExportPort(CompoundType CT) {
/* 123 */     ChangeConnectorPort1();
/* 124 */     EList eList = CT.getSubcomponent();
/* 125 */     int J = 1;
/* 126 */     for (Object o : eList) {
/* 127 */       Component C = (Component)o;
/* 128 */       List LP = new LinkedList((Collection<?>)C.getType().getPort());
/* 129 */       for (Object o1 : LP) {
/*     */         
/* 131 */         Port p = (Port)o1;
/* 132 */         Port Exportport = BehaviorsFactory.eINSTANCE.createPort();
/* 133 */         Exportport.setType(p.getType());
/* 134 */         Exportport.setName("port_" + J++);
/* 135 */         Exportport.setComponentType((ComponentType)CT);
/* 136 */         ExportBinding EB = InteractionsFactory.eINSTANCE.createExportBinding();
/* 137 */         PartElementReference PER = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 138 */         PER.setTargetPart((Part)C);
/* 139 */         EB.setTargetInstance(PER);
/* 140 */         EB.setTargetPort(p);
/* 141 */         EB.setOuterPort(Exportport);
/* 142 */         Exportport.setBinding((Binding)EB);
/* 143 */         CT.getPort().add(Exportport);
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
/*     */   ConnectorType PortConnectorType(String porttype) {
/* 156 */     List LBipType = new LinkedList((Collection<?>)this.module.getBipType());
/* 157 */     String S = "ConnTransfer_" + porttype;
/* 158 */     for (Object o : LBipType) {
/*     */       
/* 160 */       if (o instanceof ConnectorType) {
/* 161 */         ConnectorType CT = (ConnectorType)o;
/* 162 */         if (CT.getName().equals(S))
/* 163 */           return CT; 
/*     */       } 
/*     */     } 
/* 166 */     return null;
/*     */   }
/*     */   
/*     */   void ChangeConnectorPort() {
/* 170 */     List LConn = new LinkedList((Collection<?>)this.CompType.getConnector());
/* 171 */     for (Object o : LConn) {
/*     */       
/* 173 */       Connector C = (Connector)o;
/* 174 */       if (!VerifyPortConnector(C))
/*     */       {
/* 176 */         for (Object o1 : C.getActualPort()) {
/*     */           
/* 178 */           InnerPortReference ipr = (InnerPortReference)o1;
/* 179 */           if (ipr.getTargetInstance().getTargetPart() instanceof Component) {
/*     */             
/* 181 */             Component Comp = CompPartition((Component)ipr.getTargetInstance().getTargetPart());
/* 182 */             if (Comp != null) {
/*     */               
/* 184 */               Port P = CorrespondingPort(ipr.getTargetPort(), (Component)ipr.getTargetInstance().getTargetPart());
/* 185 */               ipr.setTargetPort(P);
/* 186 */               ipr.getTargetInstance().setTargetPart((Part)Comp);
/*     */             }  continue;
/*     */           } 
/* 189 */           if (ipr.getTargetInstance().getTargetPart() instanceof Connector) {
/*     */             
/* 191 */             Connector Conn = (Connector)ipr.getTargetInstance().getTargetPart();
/* 192 */             Component Ctmp = CompConn(Conn);
/* 193 */             if (Ctmp != null) {
/*     */               
/* 195 */               ipr.setTargetPort(CorrespondingPort1(Conn, (CompoundType)Ctmp.getType()));
/* 196 */               ipr.getTargetInstance().setTargetPart((Part)Ctmp);
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
/*     */   Port CorrespondingPort1(Connector c, CompoundType CT) {
/* 209 */     EList eList = CT.getPort();
/* 210 */     for (Object o : eList) {
/*     */       
/* 212 */       Port p = (Port)o;
/* 213 */       ExportBinding EB = (ExportBinding)p.getBinding();
/* 214 */       if (EB.getTargetInstance().getTargetPart().equals(c)) {
/* 215 */         return p;
/*     */       }
/*     */     } 
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Component CompConn(Connector C) {
/* 228 */     List LComp = new LinkedList((Collection<?>)this.CompType.getSubcomponent());
/* 229 */     for (Object o : LComp) {
/*     */       
/* 231 */       Component Comp = (Component)o;
/* 232 */       if (Comp.getType() instanceof CompoundType) {
/*     */         
/* 234 */         List LConn = new LinkedList((Collection<?>)((CompoundType)Comp.getType()).getConnector());
/* 235 */         for (Object o1 : LConn) {
/*     */           
/* 237 */           Connector c = (Connector)o1;
/* 238 */           if (c.equals(C))
/* 239 */             return Comp; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 243 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void ChangeConnectorPort1() {
/* 249 */     List LConn = new LinkedList((Collection<?>)this.CompType.getConnector());
/* 250 */     for (Object o : LConn) {
/*     */       
/* 252 */       Connector C = (Connector)o;
/* 253 */       if (VerifyPortConnector(C)) {
/*     */         
/* 255 */         InnerPortReference ipr = (InnerPortReference)C.getActualPort().get(0);
/* 256 */         Component Comp = (Component)ipr.getTargetInstance().getTargetPart();
/* 257 */         C.setCompoundType(Comp.getCompoundType());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Component CompPartition(Component C) {
/* 268 */     CompoundType CT = C.getCompoundType();
/* 269 */     EList eList = this.CompType.getSubcomponent();
/* 270 */     for (Object o : eList) {
/*     */       
/* 272 */       Component c = (Component)o;
/* 273 */       if (c.getType().equals(CT))
/* 274 */         return c; 
/*     */     } 
/* 276 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Port CorrespondingPort(Port P, Component C) {
/* 285 */     Component C1 = CompPartition(C);
/* 286 */     EList eList = C1.getType().getPort();
/* 287 */     for (Object o1 : eList) {
/*     */       
/* 289 */       Port p = (Port)o1;
/* 290 */       ExportBinding EB = (ExportBinding)p.getBinding();
/* 291 */       if (EB.getTargetInstance().getTargetPart().equals(C) && EB.getTargetPort().equals(P))
/* 292 */         return p; 
/*     */     } 
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean VerifyPortConnector(Connector C) {
/* 299 */     EList<InnerPortReference> eList = C.getActualPort();
/* 300 */     if (((InnerPortReference)eList.get(0)).getTargetInstance().getTargetPart() instanceof Connector) return false; 
/* 301 */     String S = GetPartition((Component)((InnerPortReference)eList.get(0)).getTargetInstance().getTargetPart());
/* 302 */     for (InnerPortReference o : eList) {
/* 303 */       InnerPortReference ipr = o;
/* 304 */       if (ipr.getTargetInstance().getTargetPart() instanceof Connector) return false; 
/* 305 */       if (!GetPartition((Component)ipr.getTargetInstance().getTargetPart()).equals(S))
/* 306 */         return false; 
/*     */     } 
/* 308 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   String GetPartition(Component C) {
/* 313 */     String S = C.getName();
/* 314 */     for (List o : Partition) {
/*     */       
/* 316 */       List L = o;
/* 317 */       for (Object o1 : L) {
/*     */         
/* 319 */         String s = (String)o1;
/* 320 */         if (s.equals(S))
/* 321 */           return o.toString(); 
/*     */       } 
/*     */     } 
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   static int test = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void Reconstruct() {
/* 337 */     Partition.clear();
/* 338 */     GetPartition(Tree);
/* 339 */     test = 1;
/* 340 */     for (List o : Partition) {
/*     */       
/* 342 */       List<String> LPartition = o;
/*     */       
/* 344 */       if (LPartition.size() > 1) {
/* 345 */         CompoundType CT = TransformationFunction.CreateCompoundType(GetCompoundName(LPartition));
/* 346 */         addCompoundTypetType(CT);
/*     */         
/* 348 */         for (String o1 : LPartition) {
/* 349 */           String s = o1;
/* 350 */           Component C = GetComponent(s);
/* 351 */           C.setCompoundType(CT);
/*     */         } 
/* 353 */         if (test == 1) ChangeConnectorPort1(); 
/* 354 */         test++;
/* 355 */         AddExportPort(CT);
/* 356 */         AddComponent(CT);
/*     */       } 
/*     */     } 
/* 359 */     ChangeConnectorPort();
/* 360 */     if (!IsListofStringList(Tree)) {
/* 361 */       SetPartition(Tree);
/* 362 */       Reconstruct();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void GetPartition(List T) {
/* 372 */     for (Object o : T) {
/*     */       
/* 374 */       if (IsStringList(o)) {
/* 375 */         Partition.add((List)o); continue;
/*     */       } 
/* 377 */       GetPartition((List)o);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void SetPartition(List<List> T) {
/* 385 */     for (List o : T) {
/*     */       
/* 387 */       if (!IsStringList(o)) {
/*     */         
/* 389 */         if (IsListofStringList(o)) {
/*     */           
/* 391 */           int i = T.indexOf(o);
/* 392 */           T.set(i, GetNewListofList(o));
/*     */           continue;
/*     */         } 
/* 395 */         SetPartition(o);
/*     */         
/*     */         continue;
/*     */       } 
/* 399 */       int index = T.indexOf(o);
/* 400 */       List<String> L = new LinkedList<String>();
/* 401 */       L.add(GetNewNameList(o));
/* 402 */       T.set(index, L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List GetNewListofList(List L) {
/* 411 */     List<String> LNew = new LinkedList();
/* 412 */     for (Object o : L) {
/*     */       
/* 414 */       String S = GetNewNameList((List)o);
/* 415 */       LNew.add(S);
/*     */     } 
/* 417 */     return LNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String GetNewNameList(List L) {
/* 427 */     String S = new String();
/* 428 */     for (Object o : L) {
/* 429 */       String s = (String)o;
/* 430 */       S = String.valueOf(S) + s;
/*     */     } 
/* 432 */     if (L.size() > 1) S = String.valueOf(S) + "_I"; 
/* 433 */     return S;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean IsListofStringList(List L) {
/* 439 */     for (Object o : L) {
/*     */       
/* 441 */       if (!IsStringList(o))
/* 442 */         return false; 
/*     */     } 
/* 444 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean IsStringList(Object O) {
/* 450 */     List L = (List)O;
/* 451 */     for (Object o : L) {
/* 452 */       if (o instanceof String) return true; 
/* 453 */     }  return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\OptimizedReconstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */