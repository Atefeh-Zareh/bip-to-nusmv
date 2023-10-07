/*     */ package BIP2BIP;
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
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
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
/*  42 */   private static List<List> Partition = new LinkedList<List>();
/*  43 */   private static List<List> Tree = new LinkedList<List>();
/*     */   private CompoundType CompType;
/*  45 */   static int CounterComName = 0;
/*     */ 
/*     */   
/*     */   public Reconstruction(CompoundType ComType) {
/*  49 */     this.CompType = ComType;
/*     */   }
/*     */ 
/*     */   
/*     */   static void CreatePartition() {
/*  54 */     List<String> Partition1 = new LinkedList<String>();
/*  55 */     List<String> Partition2 = new LinkedList<String>();
/*  56 */     List<String> Partition3 = new LinkedList<String>();
/*     */     
/*  58 */     Partition1.add("A");
/*  59 */     Partition1.add("D");
/*  60 */     Partition1.add("E");
/*     */     
/*  62 */     Partition2.add("B");
/*  63 */     Partition2.add("C");
/*  64 */     Partition3.add("F");
/*  65 */     Partition3.add("G");
/*     */     
/*  67 */     Partition.add(Partition1);
/*  68 */     Partition.add(Partition2);
/*  69 */     Partition.add(Partition3);
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
/*     */   void Construct(Module module) {
/*  82 */     CreateConnectorType(module);
/*  83 */     for (List o : Partition) {
/*     */       
/*  85 */       List<String> LPartition = o;
/*     */       
/*  87 */       if (LPartition.size() > 1) {
/*     */         
/*  89 */         CompoundType CT = InteractionsFactory.eINSTANCE.createCompoundType();
/*     */ 
/*     */         
/*  92 */         CT.setName(GetCompoundName(LPartition));
/*     */         
/*  94 */         addCompoundTypetType(module, CT);
/*     */         
/*  96 */         for (String o1 : LPartition) {
/*     */           
/*  98 */           String s = o1;
/*  99 */           Component C = GetComponent(s);
/* 100 */           C.setCompoundType(CT);
/*     */         } 
/* 102 */         AddConnectorType(CT, module);
/* 103 */         AddComponent(CT);
/*     */       } 
/*     */     } 
/* 106 */     ChangeConnectorPort();
/*     */   }
/*     */ 
/*     */   
/*     */   String GetCompoundName(List<String> L) {
/* 111 */     String CompName = new String();
/* 112 */     for (String o : L) {
/*     */       
/* 114 */       String S = o;
/* 115 */       CompName = String.valueOf(CompName) + S;
/*     */     } 
/* 117 */     return CompName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCompoundTypetType(Module m, CompoundType CT) {
/* 123 */     int size = m.getBipType().size();
/* 124 */     m.getBipType().add(size - 2, CT);
/*     */   }
/*     */   
/*     */   private void AddComponent(CompoundType CT) {
/* 128 */     Component Comp = InteractionsFactory.eINSTANCE.createComponent();
/* 129 */     Comp.setName(String.valueOf(CT.getName()) + "_I");
/* 130 */     Comp.setType((ComponentType)CT);
/* 131 */     Comp.setCompoundType(this.CompType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Component GetComponent(String name) {
/* 137 */     EList LComp = this.CompType.getSubcomponent();
/* 138 */     for (Object o : LComp) {
/*     */       
/* 140 */       Component C = (Component)o;
/* 141 */       if (C.getName().equals(name))
/* 142 */         return C; 
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void CreateConnectorType(Module module) {
/* 154 */     List LBipType = new LinkedList((Collection<?>)module.getBipType());
/* 155 */     for (Object o : LBipType) {
/*     */       
/* 157 */       if (o instanceof PortType) {
/*     */         
/* 159 */         PortType PT = (PortType)o;
/* 160 */         if (!PT.getName().equals("Port")) {
/*     */           
/* 162 */           ConnectorType ConnT = InteractionsFactory.eINSTANCE.createConnectorType();
/* 163 */           ConnT.setName("ConnTransfer_" + PT.getName());
/* 164 */           PortParameter PortPara = InteractionsFactory.eINSTANCE.createPortParameter();
/* 165 */           PortPara.setType(PT);
/* 166 */           PortPara.setName("p");
/* 167 */           ConnT.getPortParameter().add(PortPara);
/* 168 */           PortParameterReference PortParaRef = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 169 */           PortParaRef.setTarget(PortPara);
/* 170 */           ACFusion portdef = PortExpressionsFactory.eINSTANCE.createACFusion();
/* 171 */           portdef.getOperand().add(PortParaRef);
/* 172 */           ConnT.setDefinition((PortExpression)portdef);
/*     */ 
/*     */           
/* 175 */           List LDP = new LinkedList((Collection<?>)PT.getDataParameter());
/*     */           
/* 177 */           InteractionSpecification Ispec = InteractionsFactory.eINSTANCE.createInteractionSpecification();
/* 178 */           Interaction Inter = InteractionsFactory.eINSTANCE.createInteraction();
/* 179 */           Inter.getPort().add(EcoreUtil.copy((EObject)PortParaRef));
/* 180 */           Ispec.setInteraction(Inter);
/* 181 */           ConnT.getInteractionSpecification().add(Ispec);
/*     */           
/* 183 */           if (LDP.size() != 0) {
/*     */             
/* 185 */             CompositeAction CAU = ActionsFactory.eINSTANCE.createCompositeAction();
/* 186 */             CompositeAction CAD = ActionsFactory.eINSTANCE.createCompositeAction();
/* 187 */             int i = 1;
/* 188 */             for (Object o1 : LDP) {
/*     */               
/* 190 */               DataParameter DP = (DataParameter)o1;
/* 191 */               Variable V = BehaviorsFactory.eINSTANCE.createVariable();
/* 192 */               V.setType(DP.getType());
/* 193 */               V.setName("val" + i);
/* 194 */               i++;
/*     */               
/* 196 */               VariableReference Vref = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 197 */               Vref.setTargetVariable(V);
/* 198 */               ConnT.getVariable().add(V);
/*     */               
/* 200 */               RequiredDataParameterReference rdpr = ExpressionsFactory.eINSTANCE.createRequiredDataParameterReference();
/* 201 */               PortParameterReference ppref = InteractionsFactory.eINSTANCE.createPortParameterReference();
/* 202 */               ppref.setTarget(PortPara);
/* 203 */               rdpr.setTargetParameter(DP);
/* 204 */               rdpr.setPortReference(ppref);
/* 205 */               AssignmentAction AAU = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 206 */               AssignmentAction AAD = ActionsFactory.eINSTANCE.createAssignmentAction();
/* 207 */               AAU.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)Vref));
/* 208 */               AAD.setAssignedValue((Expression)EcoreUtil.copy((EObject)Vref));
/* 209 */               AAU.setAssignedValue((Expression)EcoreUtil.copy((EObject)rdpr));
/* 210 */               AAD.setAssignedTarget((DataReference)EcoreUtil.copy((EObject)rdpr));
/* 211 */               CAU.getContent().add(AAU);
/* 212 */               CAD.getContent().add(AAD);
/*     */             } 
/* 214 */             Ispec.setUpAction((Action)CAU);
/* 215 */             Ispec.setDownAction((Action)CAD);
/*     */           } 
/* 217 */           Port p = BehaviorsFactory.eINSTANCE.createPort();
/* 218 */           p.setName("port_" + PT.getName());
/* 219 */           p.setType(PT);
/* 220 */           EList eList = ConnT.getVariable();
/* 221 */           DefinitionBinding DB = BehaviorsFactory.eINSTANCE.createDefinitionBinding();
/* 222 */           PortDefinition PD = BehaviorsFactory.eINSTANCE.createPortDefinition();
/* 223 */           DB.setOuterPort(p);
/* 224 */           for (Object o2 : eList) {
/* 225 */             Variable Vtmp = (Variable)o2;
/* 226 */             PD.getExposedVariable().add(EcoreUtil.copy((EObject)Vtmp));
/*     */           } 
/* 228 */           p.setBinding((Binding)DB);
/* 229 */           PD.setName("port_" + PT.getName());
/* 230 */           PD.setConnectorType(ConnT);
/* 231 */           PD.setType(PT);
/* 232 */           DB.setDefinition(PD);
/* 233 */           ConnT.setPort(p);
/*     */           
/* 235 */           addConnectorType(module, ConnT);
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
/* 250 */     ChangeConnectorPort1();
/* 251 */     EList eList = CT.getSubcomponent();
/* 252 */     int J = 1;
/* 253 */     for (Object o : eList) {
/*     */       
/* 255 */       Component C = (Component)o;
/* 256 */       EList eList1 = C.getType().getPort();
/* 257 */       for (Object o1 : eList1) {
/*     */         
/* 259 */         Port p = (Port)o1;
/* 260 */         ConnectorType ConnT = PortConnectorType(p.getType().getName(), module);
/* 261 */         Connector Conn = InteractionsFactory.eINSTANCE.createConnector();
/* 262 */         Conn.setName("Conn" + J);
/* 263 */         J++;
/* 264 */         Conn.setType(ConnT);
/* 265 */         Conn.setCompoundType(CT);
/* 266 */         InnerPortReference ipr = InteractionsFactory.eINSTANCE.createInnerPortReference();
/* 267 */         ipr.setTargetPort(p);
/* 268 */         PartElementReference PE = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 269 */         PE.setTargetPart((Part)C);
/* 270 */         ipr.setTargetInstance(PE);
/* 271 */         Conn.getActualPort().add(ipr);
/*     */       } 
/*     */     } 
/* 274 */     AddExportPortComp(CT);
/*     */   }
/*     */ 
/*     */   
/*     */   void AddExportPortComp(CompoundType CT) {
/* 279 */     int J = 1;
/* 280 */     EList eList = CT.getConnector();
/* 281 */     EList<Port> eList1 = CT.getPort();
/* 282 */     for (Object o : eList) {
/*     */       
/* 284 */       Connector C = (Connector)o;
/* 285 */       Port p = BehaviorsFactory.eINSTANCE.createPort();
/* 286 */       if (C.getType().getPort() != null) {
/* 287 */         p.setType(C.getType().getPort().getType());
/* 288 */         p.setName("port_" + J);
/* 289 */         p.setComponentType((ComponentType)CT);
/*     */         
/* 291 */         ExportBinding EB = InteractionsFactory.eINSTANCE.createExportBinding();
/* 292 */         PartElementReference PER = InteractionsFactory.eINSTANCE.createPartElementReference();
/* 293 */         PER.setTargetPart((Part)C);
/* 294 */         EB.setTargetInstance(PER);
/* 295 */         EB.setTargetPort(C.getType().getPort());
/* 296 */         EB.setOuterPort(p);
/* 297 */         p.setBinding((Binding)EB);
/* 298 */         eList1.add(p);
/* 299 */         J++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   ConnectorType PortConnectorType(String porttype, Module module) {
/* 306 */     List LBipType = new LinkedList((Collection<?>)module.getBipType());
/* 307 */     String S = "ConnTransfer_" + porttype;
/* 308 */     for (Object o : LBipType) {
/*     */       
/* 310 */       if (o instanceof ConnectorType) {
/*     */         
/* 312 */         ConnectorType CT = (ConnectorType)o;
/* 313 */         if (CT.getName().equals(S))
/* 314 */           return CT; 
/*     */       } 
/*     */     } 
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   void ChangeConnectorPort() {
/* 322 */     List LConn = new LinkedList((Collection<?>)this.CompType.getConnector());
/* 323 */     for (Object o : LConn) {
/*     */       
/* 325 */       Connector C = (Connector)o;
/* 326 */       if (!VerifyPortConnector(C)) {
/* 327 */         for (Object o1 : C.getActualPort()) {
/*     */           
/* 329 */           InnerPortReference ipr = (InnerPortReference)o1;
/* 330 */           if (ipr.getTargetInstance().getTargetPart() instanceof Component) {
/*     */             
/* 332 */             Component Comp = CompPartition((Component)ipr.getTargetInstance().getTargetPart());
/* 333 */             if (Comp != null) {
/*     */               
/* 335 */               Port P = CorrespondingPort(ipr.getTargetPort(), (Component)ipr.getTargetInstance().getTargetPart());
/* 336 */               ipr.setTargetPort(P);
/* 337 */               ipr.getTargetInstance().setTargetPart((Part)Comp);
/*     */             }  continue;
/*     */           } 
/* 340 */           if (ipr.getTargetInstance().getTargetPart() instanceof Connector) {
/*     */             
/* 342 */             Connector Conn = (Connector)ipr.getTargetInstance().getTargetPart();
/* 343 */             Component Ctmp = CompConn(Conn);
/* 344 */             if (Ctmp != null) {
/*     */               
/* 346 */               ipr.setTargetPort(CorrespondingPort1(Conn, (CompoundType)Ctmp.getType()));
/* 347 */               ipr.getTargetInstance().setTargetPart((Part)Ctmp);
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
/* 366 */     EList eList = CT.getPort();
/* 367 */     for (Object o : eList) {
/*     */       
/* 369 */       Port p = (Port)o;
/* 370 */       ExportBinding EB = (ExportBinding)p.getBinding();
/* 371 */       if (EB.getTargetInstance().getTargetPart().equals(c)) {
/* 372 */         return p;
/*     */       }
/*     */     } 
/* 375 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Component CompConn(Connector C) {
/* 385 */     List LComp = new LinkedList((Collection<?>)this.CompType.getSubcomponent());
/* 386 */     for (Object o : LComp) {
/*     */       
/* 388 */       Component Comp = (Component)o;
/* 389 */       if (Comp.getType() instanceof CompoundType) {
/*     */         
/* 391 */         List LConn = new LinkedList((Collection<?>)((CompoundType)Comp.getType()).getConnector());
/* 392 */         for (Object o1 : LConn) {
/*     */           
/* 394 */           Connector c = (Connector)o1;
/* 395 */           if (c.equals(C))
/* 396 */             return Comp; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 400 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void ChangeConnectorPort1() {
/* 406 */     List LConn = new LinkedList((Collection<?>)this.CompType.getConnector());
/* 407 */     for (Object o : LConn) {
/*     */       
/* 409 */       Connector C = (Connector)o;
/* 410 */       if (VerifyPortConnector(C)) {
/*     */         
/* 412 */         InnerPortReference ipr = (InnerPortReference)C.getActualPort().get(0);
/* 413 */         Component Comp = (Component)ipr.getTargetInstance().getTargetPart();
/* 414 */         C.setCompoundType(Comp.getCompoundType());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Component CompPartition(Component C) {
/* 424 */     CompoundType CT = C.getCompoundType();
/* 425 */     EList eList = this.CompType.getSubcomponent();
/* 426 */     for (Object o : eList) {
/*     */       
/* 428 */       Component c = (Component)o;
/* 429 */       if (c.getType().equals(CT))
/* 430 */         return c; 
/*     */     } 
/* 432 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Port CorrespondingPort(Port P, Component C) {
/* 441 */     Component C1 = CompPartition(C);
/* 442 */     EList eList = ((CompoundType)C1.getType()).getConnector();
/* 443 */     for (Object o : eList) {
/*     */       
/* 445 */       Connector c = (Connector)o;
/* 446 */       InnerPortReference ipr = (InnerPortReference)c.getActualPort().get(0);
/* 447 */       if ((ipr.getTargetInstance().getTargetPart().equals(C) & ipr.getTargetPort().equals(P)) != 0) {
/*     */         
/* 449 */         EList eList1 = C1.getType().getPort();
/* 450 */         for (Object o1 : eList1) {
/*     */           
/* 452 */           Port p = (Port)o1;
/* 453 */           ExportBinding EB = (ExportBinding)p.getBinding();
/* 454 */           if (EB.getTargetInstance().getTargetPart().equals(c))
/* 455 */             return p; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 459 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean VerifyPortConnector(Connector C) {
/* 464 */     EList<InnerPortReference> eList = C.getActualPort();
/* 465 */     if (((InnerPortReference)eList.get(0)).getTargetInstance().getTargetPart() instanceof Connector) return false; 
/* 466 */     String S = GetPartition((Component)((InnerPortReference)eList.get(0)).getTargetInstance().getTargetPart());
/* 467 */     for (InnerPortReference o : eList) {
/*     */       
/* 469 */       InnerPortReference ipr = o;
/* 470 */       if (ipr.getTargetInstance().getTargetPart() instanceof Connector) return false; 
/* 471 */       if (!GetPartition((Component)ipr.getTargetInstance().getTargetPart()).equals(S))
/* 472 */         return false; 
/*     */     } 
/* 474 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   String GetPartition(Component C) {
/* 479 */     String S = C.getName();
/* 480 */     for (List o : Partition) {
/*     */       
/* 482 */       List L = o;
/* 483 */       for (Object o1 : L) {
/*     */         
/* 485 */         String s = (String)o1;
/* 486 */         if (s.equals(S))
/* 487 */           return o.toString(); 
/*     */       } 
/*     */     } 
/* 490 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getIndexPortType(Module M) {
/* 498 */     List Lbiptype = new LinkedList();
/* 499 */     EList eList = M.getBipType();
/* 500 */     int i = 0;
/* 501 */     for (Object o : eList) {
/*     */       
/* 503 */       if (!(o instanceof PortType))
/* 504 */         return i; 
/* 505 */       i++;
/*     */     } 
/* 507 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addConnectorType(Module m, ConnectorType CT) {
/* 513 */     int i = getIndexPortType(m);
/* 514 */     m.getBipType().add(i, CT);
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
/* 560 */     List<String> Partition1 = new LinkedList<String>();
/* 561 */     List<String> Partition2 = new LinkedList<String>();
/* 562 */     List<String> Partition3 = new LinkedList<String>();
/* 563 */     List<String> Partition4 = new LinkedList<String>();
/*     */     
/* 565 */     List<String> Partition5 = new LinkedList<String>();
/*     */     
/* 567 */     List<List> Partition12 = new LinkedList<List>();
/* 568 */     List<List> Partition34 = new LinkedList<List>();
/*     */     
/* 570 */     Partition1.add("ggg");
/* 571 */     Partition1.add("ggd");
/*     */     
/* 573 */     Partition2.add("gdg");
/* 574 */     Partition3.add("gdd");
/*     */     
/* 576 */     Partition3.add("dgg");
/* 577 */     Partition3.add("dgd");
/*     */     
/* 579 */     Partition3.add("ddg");
/* 580 */     Partition4.add("ddd");
/*     */ 
/*     */ 
/*     */     
/* 584 */     Partition12.add(Partition1);
/* 585 */     Partition12.add(Partition2);
/*     */     
/* 587 */     Partition34.add(Partition3);
/* 588 */     Partition34.add(Partition4);
/*     */     
/* 590 */     Tree.add(Partition12);
/* 591 */     Tree.add(Partition34);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 600 */   static int test = 1;
/*     */ 
/*     */   
/*     */   void ConstructNew(Module module) {
/* 604 */     Partition.clear();
/* 605 */     GetPartition(Tree);
/* 606 */     test = 1;
/* 607 */     for (List o : Partition) {
/*     */       
/* 609 */       List<String> LPartition = o;
/*     */       
/* 611 */       if (LPartition.size() > 1) {
/*     */         
/* 613 */         CompoundType CT = InteractionsFactory.eINSTANCE.createCompoundType();
/*     */ 
/*     */         
/* 616 */         CT.setName(GetCompoundName(LPartition));
/*     */         
/* 618 */         addCompoundTypetType(module, CT);
/*     */         
/* 620 */         for (String o1 : LPartition) {
/*     */           
/* 622 */           String s = o1;
/* 623 */           Component C = GetComponent(s);
/* 624 */           C.setCompoundType(CT);
/*     */         } 
/* 626 */         if (test == 1) ChangeConnectorPort1(); 
/* 627 */         test++;
/* 628 */         AddConnectorType(CT, module);
/* 629 */         AddComponent(CT);
/*     */       } 
/*     */     } 
/* 632 */     ChangeConnectorPort();
/* 633 */     if (!IsListofStringList(Tree)) {
/*     */       
/* 635 */       SetPartition(Tree);
/* 636 */       ConstructNew(module);
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
/* 647 */     for (Object o : T) {
/*     */       
/* 649 */       if (IsStringList(o)) {
/* 650 */         Partition.add((List)o); continue;
/*     */       } 
/* 652 */       GetPartition((List)o);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void SetPartition(List<List> T) {
/* 661 */     for (List o : T) {
/*     */       
/* 663 */       if (!IsStringList(o)) {
/*     */         
/* 665 */         if (IsListofStringList(o)) {
/*     */           
/* 667 */           int i = T.indexOf(o);
/* 668 */           T.set(i, GetNewListofList(o));
/*     */           continue;
/*     */         } 
/* 671 */         SetPartition(o);
/*     */         
/*     */         continue;
/*     */       } 
/* 675 */       int index = T.indexOf(o);
/* 676 */       List<String> L = new LinkedList<String>();
/* 677 */       L.add(GetNewNameList(o));
/* 678 */       T.set(index, L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List GetNewListofList(List L) {
/* 688 */     List<String> LNew = new LinkedList();
/* 689 */     for (Object o : L) {
/*     */       
/* 691 */       String S = GetNewNameList((List)o);
/* 692 */       LNew.add(S);
/*     */     } 
/* 694 */     return LNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String GetNewNameList(List L) {
/* 702 */     String S = new String();
/* 703 */     for (Object o : L) {
/*     */       
/* 705 */       String s = (String)o;
/* 706 */       S = String.valueOf(S) + s;
/*     */     } 
/* 708 */     if (L.size() > 1) S = String.valueOf(S) + "_I"; 
/* 709 */     return S;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean IsListofStringList(List L) {
/* 716 */     for (Object o : L) {
/*     */       
/* 718 */       if (!IsStringList(o))
/* 719 */         return false; 
/*     */     } 
/* 721 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean IsStringList(Object O) {
/* 730 */     List L = (List)O;
/* 731 */     for (Object o : L) {
/* 732 */       if (o instanceof String) return true; 
/* 733 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void Reconstruct(Module module) {
/* 740 */     CreateConnectorType(module);
/* 741 */     ConstructNew(module);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\Reconstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */