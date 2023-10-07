/*     */ package trans;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ 
/*     */ public class Reconstruction {
/*  41 */   private static List<List> Partition = new LinkedList<List>();
/*  42 */   private static List<List> Tree = new LinkedList<List>();
/*     */   private CompoundType CompType;
/*  44 */   static int CounterComName = 0;
/*     */ 
/*     */   
/*     */   public Reconstruction(CompoundType ComType) {
/*  48 */     this.CompType = ComType;
/*     */   }
/*     */ 
/*     */   
/*     */   static void CreatePartition() {
/*  53 */     List<String> Partition1 = new LinkedList<String>();
/*  54 */     List<String> Partition2 = new LinkedList<String>();
/*  55 */     List<String> Partition3 = new LinkedList<String>();
/*  56 */     List<String> Partition4 = new LinkedList<String>();
/*     */ 
/*     */     
/*  59 */     Partition1.add("dggd_dist");
/*  60 */     Partition1.add("dggg_dist");
/*  61 */     Partition1.add("dgdd_dist");
/*  62 */     Partition1.add("dgdg_dist");
/*  63 */     Partition2.add("ddgd_dist");
/*  64 */     Partition2.add("ddgg_dist");
/*  65 */     Partition2.add("dddd_dist");
/*  66 */     Partition2.add("dddg_dist");
/*  67 */     Partition3.add("gggd_dist");
/*  68 */     Partition3.add("gggg_dist");
/*  69 */     Partition3.add("ggdd_dist");
/*  70 */     Partition3.add("ggdg_dist");
/*  71 */     Partition4.add("gdgd_dist");
/*  72 */     Partition4.add("gdgg_dist");
/*  73 */     Partition4.add("gddd_dist");
/*  74 */     Partition4.add("gddg_dist");
/*  75 */     Partition4.add("Engine0_I");
/*     */ 
/*     */     
/*  78 */     Partition.add(Partition1);
/*  79 */     Partition.add(Partition2);
/*  80 */     Partition.add(Partition3);
/*  81 */     Partition.add(Partition4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void Construct(Module module) {
/* 127 */     CreateConnectorType(module);
/* 128 */     for (List o : Partition) {
/*     */       
/* 130 */       List<String> LPartition = o;
/*     */       
/* 132 */       if (LPartition.size() > 1) {
/*     */         
/* 134 */         CompoundType CT = InteractionsFactory.eINSTANCE.createCompoundType();
/*     */ 
/*     */         
/* 137 */         CT.setName(GetCompoundName(LPartition));
/*     */         
/* 139 */         addCompoundTypetType(module, CT);
/*     */         
/* 141 */         for (String o1 : LPartition) {
/*     */           
/* 143 */           String s = o1;
/* 144 */           Component C = GetComponent(s);
/* 145 */           C.setCompoundType(CT);
/*     */         } 
/* 147 */         AddConnectorType(CT, module);
/* 148 */         AddComponent(CT);
/*     */       } 
/*     */     } 
/* 151 */     ChangeConnectorPort();
/*     */   }
/*     */ 
/*     */   
/*     */   String GetCompoundName(List<String> L) {
/* 156 */     String CompName = new String();
/* 157 */     for (String o : L) {
/*     */       
/* 159 */       String S = o;
/* 160 */       CompName = String.valueOf(CompName) + S;
/*     */     } 
/* 162 */     return CompName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCompoundTypetType(Module m, CompoundType CT) {
/* 168 */     int size = m.getBipType().size();
/* 169 */     m.getBipType().add(size - 2, CT);
/*     */   }
/*     */   
/*     */   private void AddComponent(CompoundType CT) {
/* 173 */     Component Comp = InteractionsFactory.eINSTANCE.createComponent();
/* 174 */     Comp.setName(String.valueOf(CT.getName()) + "_I");
/* 175 */     Comp.setType((ComponentType)CT);
/* 176 */     Comp.setCompoundType(this.CompType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Component GetComponent(String name) {
/* 182 */     EList LComp = this.CompType.getSubcomponent();
/* 183 */     for (Object o : LComp) {
/*     */       
/* 185 */       Component C = (Component)o;
/* 186 */       if (C.getName().equals(name))
/* 187 */         return C; 
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void CreateConnectorType(Module module) {
/* 199 */     List LBipType = new LinkedList((Collection<?>)module.getBipType());
/* 200 */     for (Object o : LBipType) {
/*     */       
/* 202 */       if (o instanceof PortType) {
/*     */         
/* 204 */         PortType PT = (PortType)o;
/* 205 */         if (!PT.getName().equals("Port")) {
/*     */           
/* 207 */           ConnectorType ConnT = InteractionsFactory.eINSTANCE.createConnectorType();
/* 208 */           ConnT.setName("ConnTransfer_" + PT.getName());
/* 209 */           PortParameter PortPara = InteractionsFactory.eINSTANCE.createPortParameter();
/* 210 */           PortPara.setType(PT);
/* 211 */           PortPara.setName("p");
/* 212 */           ConnT.getPortParameter().add(PortPara);
/* 213 */           PortParameterReference PortParaRef = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 214 */           PortParaRef.setTarget(PortPara);
/* 215 */           ACFusion portdef = PortExpressionsFactory.eINSTANCE.createACFusion();
/* 216 */           portdef.getOperand().add(PortParaRef);
/* 217 */           ConnT.setDefinition((PortExpression)portdef);
/*     */ 
/*     */           
/* 220 */           List LDP = new LinkedList((Collection<?>)PT.getDataParameter());
/*     */           
/* 222 */           InteractionSpecification Ispec = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/* 223 */           Interaction Inter = InteractionsFactory.eINSTANCE.createInteraction();
/* 224 */           Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRef));
/* 225 */           Ispec.setInteraction(Inter);
/* 226 */           ConnT.getInteractionSpecification().add(Ispec);
/*     */           
/* 228 */           if (LDP.size() != 0) {
/*     */             
/* 230 */             CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/* 231 */             CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/* 232 */             int i = 1;
/* 233 */             for (Object o1 : LDP) {
/*     */               
/* 235 */               DataParameter DP = (DataParameter)o1;
/* 236 */               Variable V = BehaviorsFactory.eINSTANCE.createVariable();
/* 237 */               V.setType(DP.getType());
/* 238 */               V.setName("val" + i);
/* 239 */               i++;
/*     */               
/* 241 */               VariableReference Vref = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 242 */               Vref.setTargetVariable(V);
/* 243 */               ConnT.getVariable().add(V);
/*     */               
/* 245 */               RequiredDataParameterReference rdpr = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/* 246 */               PortParameterReference ppref = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 247 */               ppref.setTarget(PortPara);
/* 248 */               rdpr.setTargetParameter(DP);
/* 249 */               rdpr.setPortReference(ppref);
/* 250 */               AssignmentAction AAU = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 251 */               AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 252 */               AAU.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)Vref));
/* 253 */               AAD.setAssignedValue((Expression)EcoreUtil.copy((EObject)Vref));
/* 254 */               AAU.setAssignedValue((Expression)EcoreUtil.copy((EObject)rdpr));
/* 255 */               AAD.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)rdpr));
/* 256 */               CAU.getContent().add(AAU);
/* 257 */               CAD.getContent().add(AAD);
/*     */             } 
/* 259 */             Ispec.setUpAction((Action)CAU);
/* 260 */             Ispec.setDownAction((Action)CAD);
/*     */           } 
/* 262 */           Port p = BehaviorsFactory.eINSTANCE.createPort();
/* 263 */           p.setName("port_" + PT.getName());
/* 264 */           p.setType(PT);
/* 265 */           EList eList = ConnT.getVariable();
/* 266 */           DefinitionBinding DB = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 267 */           PortDefinition PD = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 268 */           DB.setOuterPort(p);
/* 269 */           for (Object o2 : eList) {
/*     */             
/* 271 */             Variable Vtmp = (Variable)o2;
/* 272 */             PD.getExposedVariable().add(EcoreUtil.copy((EObject)Vtmp));
/*     */           } 
/* 274 */           p.setBinding((Binding)DB);
/* 275 */           PD.setName("port_" + PT.getName());
/* 276 */           PD.setConnectorType(ConnT);
/* 277 */           PD.setType(PT);
/* 278 */           DB.setDefinition(PD);
/* 279 */           ConnT.setPort(p);
/*     */           
/* 281 */           addConnectorType(module, ConnT);
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
/*     */ 
/*     */ 
/*     */   
/*     */   void AddConnectorType(CompoundType CT, Module module) {
/* 296 */     ChangeConnectorPort1();
/* 297 */     EList eList = CT.getSubcomponent();
/* 298 */     int J = 1;
/* 299 */     for (Object o : eList) {
/*     */       
/* 301 */       Component C = (Component)o;
/* 302 */       EList eList1 = C.getType().getPort();
/* 303 */       for (Object o1 : eList1) {
/*     */         
/* 305 */         Port p = (Port)o1;
/* 306 */         ConnectorType ConnT = PortConnectorType(p.getType().getName(), module);
/* 307 */         Connector Conn = InteractionsFactory.eINSTANCE.createConnector();
/* 308 */         Conn.setName("Conn" + J);
/* 309 */         J++;
/* 310 */         Conn.setType(ConnT);
/* 311 */         Conn.setCompoundType(CT);
/* 312 */         InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 313 */         ipr.setTargetPort(p);
/* 314 */         PartElementReference PE = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 315 */         PE.setTargetPart((Part)C);
/* 316 */         ipr.setTargetInstance(PE);
/* 317 */         Conn.getActualPort().add(ipr);
/*     */       } 
/*     */     } 
/* 320 */     AddExportPortComp(CT);
/*     */   }
/*     */ 
/*     */   
/*     */   void AddExportPortComp(CompoundType CT) {
/* 325 */     int J = 1;
/* 326 */     EList eList = CT.getConnector();
/* 327 */     EList<Port> eList1 = CT.getPort();
/* 328 */     for (Object o : eList) {
/*     */       
/* 330 */       Connector C = (Connector)o;
/* 331 */       Port p = BehaviorsFactory.eINSTANCE.createPort();
/* 332 */       if (C.getType().getPort() != null) {
/* 333 */         p.setType(C.getType().getPort().getType());
/* 334 */         p.setName("port_" + J);
/* 335 */         p.setComponentType((ComponentType)CT);
/*     */         
/* 337 */         ExportBinding EB = InteractionsFactory.eINSTANCE.createExportBinding();
/* 338 */         PartElementReference PER = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 339 */         PER.setTargetPart((Part)C);
/* 340 */         EB.setTargetInstance(PER);
/* 341 */         EB.setTargetPort(C.getType().getPort());
/* 342 */         EB.setOuterPort(p);
/* 343 */         p.setBinding((Binding)EB);
/* 344 */         eList1.add(p);
/* 345 */         J++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   ConnectorType PortConnectorType(String porttype, Module module) {
/* 352 */     List LBipType = new LinkedList((Collection<?>)module.getBipType());
/* 353 */     String S = "ConnTransfer_" + porttype;
/* 354 */     for (Object o : LBipType) {
/*     */       
/* 356 */       if (o instanceof ConnectorType) {
/*     */         
/* 358 */         ConnectorType CT = (ConnectorType)o;
/* 359 */         if (CT.getName().equals(S))
/* 360 */           return CT; 
/*     */       } 
/*     */     } 
/* 363 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   void ChangeConnectorPort() {
/* 368 */     List LConn = new LinkedList((Collection<?>)this.CompType.getConnector());
/* 369 */     for (Object o : LConn) {
/*     */       
/* 371 */       Connector C = (Connector)o;
/* 372 */       if (!VerifyPortConnector(C)) {
/* 373 */         for (Object o1 : C.getActualPort()) {
/*     */           
/* 375 */           InnerPortReference ipr = (InnerPortReference)o1;
/* 376 */           if (ipr.getTargetInstance().getTargetPart() instanceof Component) {
/*     */             
/* 378 */             Component Comp = CompPartition((Component)ipr.getTargetInstance().getTargetPart());
/* 379 */             if (Comp != null) {
/*     */               
/* 381 */               Port P = CorrespondingPort(ipr.getTargetPort(), (Component)ipr.getTargetInstance().getTargetPart());
/* 382 */               ipr.setTargetPort(P);
/* 383 */               ipr.getTargetInstance().setTargetPart((Part)Comp);
/*     */             }  continue;
/*     */           } 
/* 386 */           if (ipr.getTargetInstance().getTargetPart() instanceof Connector) {
/*     */             
/* 388 */             Connector Conn = (Connector)ipr.getTargetInstance().getTargetPart();
/* 389 */             Component Ctmp = CompConn(Conn);
/* 390 */             if (Ctmp != null) {
/*     */               
/* 392 */               ipr.setTargetPort(CorrespondingPort1(Conn, (CompoundType)Ctmp.getType()));
/* 393 */               ipr.getTargetInstance().setTargetPart((Part)Ctmp);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Port CorrespondingPort1(Connector c, CompoundType CT) {
/* 412 */     EList eList = CT.getPort();
/* 413 */     for (Object o : eList) {
/*     */       
/* 415 */       Port p = (Port)o;
/* 416 */       ExportBinding EB = (ExportBinding)p.getBinding();
/* 417 */       if (EB.getTargetInstance().getTargetPart().equals(c)) {
/* 418 */         return p;
/*     */       }
/*     */     } 
/* 421 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Component CompConn(Connector C) {
/* 431 */     List LComp = new LinkedList((Collection<?>)this.CompType.getSubcomponent());
/* 432 */     for (Object o : LComp) {
/*     */       
/* 434 */       Component Comp = (Component)o;
/* 435 */       if (Comp.getType() instanceof CompoundType) {
/*     */         
/* 437 */         List LConn = new LinkedList((Collection<?>)((CompoundType)Comp.getType()).getConnector());
/* 438 */         for (Object o1 : LConn) {
/*     */           
/* 440 */           Connector c = (Connector)o1;
/* 441 */           if (c.equals(C))
/* 442 */             return Comp; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 446 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void ChangeConnectorPort1() {
/* 452 */     List LConn = new LinkedList((Collection<?>)this.CompType.getConnector());
/* 453 */     for (Object o : LConn) {
/*     */       
/* 455 */       Connector C = (Connector)o;
/* 456 */       if (VerifyPortConnector(C)) {
/*     */         
/* 458 */         InnerPortReference ipr = (InnerPortReference)C.getActualPort().get(0);
/* 459 */         Component Comp = (Component)ipr.getTargetInstance().getTargetPart();
/* 460 */         C.setCompoundType(Comp.getCompoundType());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Component CompPartition(Component C) {
/* 470 */     CompoundType CT = C.getCompoundType();
/* 471 */     EList eList = this.CompType.getSubcomponent();
/* 472 */     for (Object o : eList) {
/*     */       
/* 474 */       Component c = (Component)o;
/* 475 */       if (c.getType().equals(CT))
/* 476 */         return c; 
/*     */     } 
/* 478 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Port CorrespondingPort(Port P, Component C) {
/* 487 */     Component C1 = CompPartition(C);
/* 488 */     EList eList = ((CompoundType)C1.getType()).getConnector();
/* 489 */     for (Object o : eList) {
/*     */       
/* 491 */       Connector c = (Connector)o;
/* 492 */       InnerPortReference ipr = (InnerPortReference)c.getActualPort().get(0);
/* 493 */       if ((ipr.getTargetInstance().getTargetPart().equals(C) & ipr.getTargetPort().equals(P)) != 0) {
/*     */         
/* 495 */         EList eList1 = C1.getType().getPort();
/* 496 */         for (Object o1 : eList1) {
/*     */           
/* 498 */           Port p = (Port)o1;
/* 499 */           ExportBinding EB = (ExportBinding)p.getBinding();
/* 500 */           if (EB.getTargetInstance().getTargetPart().equals(c))
/* 501 */             return p; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 505 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean VerifyPortConnector(Connector C) {
/* 510 */     EList<InnerPortReference> eList = C.getActualPort();
/* 511 */     if (((InnerPortReference)eList.get(0)).getTargetInstance().getTargetPart() instanceof Connector) return false; 
/* 512 */     String S = GetPartition((Component)((InnerPortReference)eList.get(0)).getTargetInstance().getTargetPart());
/* 513 */     for (InnerPortReference o : eList) {
/*     */       
/* 515 */       InnerPortReference ipr = o;
/* 516 */       if (ipr.getTargetInstance().getTargetPart() instanceof Connector) return false; 
/* 517 */       if (!GetPartition((Component)ipr.getTargetInstance().getTargetPart()).equals(S))
/* 518 */         return false; 
/*     */     } 
/* 520 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   String GetPartition(Component C) {
/* 525 */     String S = C.getName();
/* 526 */     for (List o : Partition) {
/*     */       
/* 528 */       List L = o;
/* 529 */       for (Object o1 : L) {
/*     */         
/* 531 */         String s = (String)o1;
/* 532 */         if (s.equals(S))
/* 533 */           return o.toString(); 
/*     */       } 
/*     */     } 
/* 536 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getIndexPortType(Module M) {
/* 544 */     List Lbiptype = new LinkedList();
/* 545 */     EList eList = M.getBipType();
/* 546 */     int i = 0;
/* 547 */     for (Object o : eList) {
/*     */       
/* 549 */       if (!(o instanceof PortType))
/* 550 */         return i; 
/* 551 */       i++;
/*     */     } 
/* 553 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addConnectorType(Module m, ConnectorType CT) {
/* 559 */     int i = getIndexPortType(m);
/* 560 */     m.getBipType().add(i, CT);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void CreateTreePartition() {
/* 606 */     List<String> Partition1 = new LinkedList<String>();
/* 607 */     List<String> Partition2 = new LinkedList<String>();
/* 608 */     List<String> Partition3 = new LinkedList<String>();
/* 609 */     List<String> Partition4 = new LinkedList<String>();
/*     */     
/* 611 */     Partition1.add("dggd_dist");
/* 612 */     Partition1.add("dggg_dist");
/* 613 */     Partition1.add("dgdd_dist");
/* 614 */     Partition1.add("dgdg_dist");
/* 615 */     Partition2.add("ddgd_dist");
/* 616 */     Partition2.add("ddgg_dist");
/* 617 */     Partition2.add("dddd_dist");
/* 618 */     Partition2.add("dddg_dist");
/* 619 */     Partition3.add("gggd_dist");
/* 620 */     Partition3.add("gggg_dist");
/* 621 */     Partition3.add("ggdd_dist");
/* 622 */     Partition3.add("ggdg_dist");
/* 623 */     Partition4.add("gdgd_dist");
/* 624 */     Partition4.add("gdgg_dist");
/* 625 */     Partition4.add("gddd_dist");
/* 626 */     Partition4.add("gddg_dist");
/* 627 */     Partition4.add("Engine0_I");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 635 */     Tree.add(Partition1);
/* 636 */     Tree.add(Partition2);
/* 637 */     Tree.add(Partition3);
/* 638 */     Tree.add(Partition4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 647 */   static int test = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   void ConstructNew(Module module) {
/* 652 */     Partition.clear();
/* 653 */     GetPartition(Tree);
/* 654 */     test = 1;
/* 655 */     for (List o : Partition) {
/*     */       
/* 657 */       List<String> LPartition = o;
/*     */       
/* 659 */       if (LPartition.size() > 1) {
/*     */         
/* 661 */         CompoundType CT = InteractionsFactory.eINSTANCE.createCompoundType();
/*     */ 
/*     */         
/* 664 */         CT.setName(GetCompoundName(LPartition));
/*     */         
/* 666 */         addCompoundTypetType(module, CT);
/*     */         
/* 668 */         for (String o1 : LPartition) {
/*     */           
/* 670 */           String s = o1;
/* 671 */           Component C = GetComponent(s);
/* 672 */           C.setCompoundType(CT);
/*     */         } 
/* 674 */         if (test == 1) ChangeConnectorPort1(); 
/* 675 */         test++;
/* 676 */         AddConnectorType(CT, module);
/* 677 */         AddComponent(CT);
/*     */       } 
/*     */     } 
/* 680 */     ChangeConnectorPort();
/* 681 */     if (!IsListofStringList(Tree)) {
/*     */       
/* 683 */       SetPartition(Tree);
/* 684 */       ConstructNew(module);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void GetPartition(List T) {
/* 695 */     for (Object o : T) {
/*     */       
/* 697 */       if (IsStringList(o)) {
/* 698 */         Partition.add((List)o); continue;
/*     */       } 
/* 700 */       GetPartition((List)o);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void SetPartition(List<List> T) {
/* 709 */     for (List o : T) {
/*     */       
/* 711 */       if (!IsStringList(o)) {
/*     */         
/* 713 */         if (IsListofStringList(o)) {
/*     */           
/* 715 */           int i = T.indexOf(o);
/* 716 */           T.set(i, GetNewListofList(o));
/*     */           continue;
/*     */         } 
/* 719 */         SetPartition(o);
/*     */         
/*     */         continue;
/*     */       } 
/* 723 */       int index = T.indexOf(o);
/* 724 */       List<String> L = new LinkedList<String>();
/* 725 */       L.add(GetNewNameList(o));
/* 726 */       T.set(index, L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List GetNewListofList(List L) {
/* 736 */     List<String> LNew = new LinkedList();
/* 737 */     for (Object o : L) {
/*     */       
/* 739 */       String S = GetNewNameList((List)o);
/* 740 */       LNew.add(S);
/*     */     } 
/* 742 */     return LNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String GetNewNameList(List L) {
/* 750 */     String S = new String();
/* 751 */     for (Object o : L) {
/*     */       
/* 753 */       String s = (String)o;
/* 754 */       S = String.valueOf(S) + s;
/*     */     } 
/* 756 */     if (L.size() > 1) S = String.valueOf(S) + "_I"; 
/* 757 */     return S;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean IsListofStringList(List L) {
/* 764 */     for (Object o : L) {
/*     */       
/* 766 */       if (!IsStringList(o))
/* 767 */         return false; 
/*     */     } 
/* 769 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean IsStringList(Object O) {
/* 778 */     List L = (List)O;
/* 779 */     for (Object o : L) {
/* 780 */       if (o instanceof String) return true; 
/* 781 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void Reconstruct(Module module) {
/* 788 */     CreateConnectorType(module);
/* 789 */     ConstructNew(module);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\Reconstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */