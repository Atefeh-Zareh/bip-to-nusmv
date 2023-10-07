/*      */ package trans;
/*      */ 
/*      */ import BIPTransformation.TransformationFunction;
/*      */ import java.util.Collection;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*      */ import ujf.verimag.bip.Core.Behaviors.Port;
/*      */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*      */ import ujf.verimag.bip.Core.Modules.Module;
/*      */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TCompoundType
/*      */ {
/*      */   private CompoundType TCT;
/*   48 */   private static Integer J = Integer.valueOf(0);
/*   49 */   private static Integer K = Integer.valueOf(0);
/*      */ 
/*      */   
/*      */   public static boolean TEST = true;
/*      */   
/*      */   int LL;
/*      */ 
/*      */   
/*      */   public TCompoundType(CompoundType TCT) {
/*   58 */     this.LL = 0;
/*      */     this.TCT = TCT;
/*      */   } private Component getComponent() {
/*   61 */     CompoundType ct = this.TCT;
/*   62 */     EList sc = ct.getSubcomponent();
/*   63 */     List sc1 = new LinkedList((Collection<?>)sc);
/*   64 */     for (Object o : sc1) {
/*      */       
/*   66 */       Component c = (Component)o;
/*   67 */       if (!(c.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType))
/*   68 */         return c; 
/*      */     } 
/*   70 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setPorts(Component C) {
/*   78 */     EList ConnAll = this.TCT.getConnector();
/*   79 */     ExportBinding p1 = null;
/*   80 */     ComponentType ct = C.getType();
/*   81 */     for (Object o : ConnAll) {
/*      */       
/*   83 */       Connector c = (Connector)o;
/*   84 */       EList LPort1 = c.getActualPort();
/*   85 */       List LPort = new LinkedList((Collection<?>)LPort1);
/*      */       
/*   87 */       for (Object o1 : LPort) {
/*      */         
/*   89 */         InnerPortReference p = (InnerPortReference)o1;
/*   90 */         if (p.getTargetInstance().getTargetPart() == C) {
/*      */           
/*   92 */           p.setTargetPort(GetPort(ct, p.getTargetPort()));
/*   93 */           p1 = (ExportBinding)EcoreUtil.copy((EObject)p.getTargetPort().getBinding());
/*   94 */           p.setTargetPort(p1.getTargetPort());
/*   95 */           p.getTargetInstance().setTargetPart(p1.getTargetInstance().getTargetPart());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Port GetPort(ComponentType CT, Port p) {
/*  106 */     EList EPort = CT.getPort();
/*  107 */     for (Object o : EPort) {
/*      */       
/*  109 */       Port p1 = (Port)o;
/*  110 */       if (p1.getName() == p.getName())
/*  111 */         return p1; 
/*      */     } 
/*  113 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setComponent(Component C) {
/*  121 */     CompoundType CT = (CompoundType)C.getType();
/*  122 */     EList LComp1 = CT.getSubcomponent();
/*      */     
/*  124 */     List LComp = new LinkedList((Collection<?>)LComp1);
/*  125 */     List LDataParamCT = new LinkedList((Collection<?>)CT.getDataParameter());
/*  126 */     List<EObject> LActualParamC = new LinkedList((Collection<?>)C.getActualData());
/*      */     
/*  128 */     for (Object o : LComp) {
/*      */       
/*  130 */       Component c = (Component)o;
/*  131 */       c.setName(String.valueOf(C.getName()) + c.getName());
/*      */ 
/*      */       
/*  134 */       EList<EObject> eList = c.getActualData();
/*  135 */       int i = 0;
/*  136 */       for (EObject o1 : eList) {
/*      */         
/*  138 */         if (o1 instanceof DataParameterReference) {
/*      */ 
/*      */           
/*  141 */           DataParameterReference DPR = (DataParameterReference)o1;
/*  142 */           int index = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  143 */           EObject EO = EcoreUtil.copy(LActualParamC.get(index));
/*  144 */           eList.set(eList.indexOf(DPR), EO);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  168 */         else if (o1 instanceof BinaryExpression) {
/*      */           
/*  170 */           BinaryExpression BE = (BinaryExpression)EcoreUtil.copy(o1);
/*  171 */           ReplaceDataParameterExpression((Expression)BE, (List)eList, LDataParamCT, LActualParamC);
/*  172 */           eList.set(eList.indexOf(o1), BE);
/*      */         } else {
/*  174 */           o1 instanceof FunctionCallExpression;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  179 */         i++;
/*      */       } 
/*      */       
/*  182 */       EList ctmp = this.TCT.getSubcomponent();
/*  183 */       ctmp.add(0, c);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void ReplaceDataParameterExpression(Expression e, List LDataParamSC, List LDataParamCT, List<EObject> LActualParamC) {
/*  189 */     if (e instanceof BinaryExpression) {
/*      */       
/*  191 */       BinaryExpression BE = (BinaryExpression)e;
/*  192 */       if (BE.getRightOperand() instanceof DataParameterReference) {
/*      */         
/*  194 */         DataParameterReference DPR = (DataParameterReference)BE.getRightOperand();
/*  195 */         int index = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  196 */         EObject EO = EcoreUtil.copy(LActualParamC.get(index));
/*  197 */         BE.setRightOperand((Expression)EO);
/*      */       }
/*  199 */       else if (BE.getRightOperand() instanceof BinaryExpression) {
/*  200 */         ReplaceDataParameterExpression(BE.getRightOperand(), LDataParamSC, LDataParamCT, LActualParamC);
/*  201 */       }  if (BE.getLeftOperand() instanceof DataParameterReference) {
/*      */         
/*  203 */         DataParameterReference DPR = (DataParameterReference)BE.getLeftOperand();
/*  204 */         int index = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  205 */         EObject EO = EcoreUtil.copy(LActualParamC.get(index));
/*  206 */         BE.setLeftOperand((Expression)EO);
/*      */       }
/*  208 */       else if (BE.getLeftOperand() instanceof BinaryExpression) {
/*  209 */         ReplaceDataParameterExpression(BE.getLeftOperand(), LDataParamSC, LDataParamCT, LActualParamC);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setConnector(Component C) {
/*  218 */     CompoundType CT = (CompoundType)C.getType();
/*  219 */     EList LConn1 = CT.getConnector();
/*  220 */     List<Connector> LConn = new LinkedList((Collection<?>)LConn1);
/*  221 */     int size = LConn.size();
/*      */     
/*  223 */     for (int i = --size; i >= 0; i--) {
/*      */       
/*  225 */       Connector c = LConn.get(i);
/*  226 */       c.setName(String.valueOf(C.getName()) + c.getName());
/*      */       
/*  228 */       EList ctmp = this.TCT.getConnector();
/*  229 */       ctmp.add(0, c);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isflat() {
/*  237 */     CompoundType ct = this.TCT;
/*  238 */     EList sc = ct.getSubcomponent();
/*  239 */     List sc1 = new LinkedList((Collection<?>)sc);
/*  240 */     for (Object o : sc1) {
/*      */       
/*  242 */       Component c = (Component)o;
/*  243 */       if (!(c.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType))
/*  244 */         return false; 
/*      */     } 
/*  246 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flatCompoundType() {
/*  254 */     boolean flat = isflat();
/*  255 */     while (!flat) {
/*      */       
/*  257 */       Component C = getComponent();
/*  258 */       CompoundType ct = InteractionsFactory.eINSTANCE.createCompoundType();
/*  259 */       ct = (CompoundType)EcoreUtil.copy((EObject)C.getType());
/*  260 */       C.setType((ComponentType)ct);
/*  261 */       setComponent(C);
/*  262 */       setConnector(C);
/*  263 */       setPorts(C);
/*  264 */       EcoreUtil.remove((EObject)C);
/*  265 */       flat = isflat();
/*      */     } 
/*  267 */     System.out.println("1 -- flat compound components done !");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConnectorType flatConnectors(Module module) {
/*  276 */     List<Connector> ll = getConnector();
/*  277 */     ConnectorType ctype = InteractionsFactory.eINSTANCE.createConnectorType();
/*  278 */     if (ll.size() != 0) {
/*      */       
/*  280 */       List portvar = new LinkedList();
/*  281 */       List LExpC1Up = new LinkedList();
/*  282 */       List lportnew = new LinkedList();
/*  283 */       List LExpC2Down = new LinkedList();
/*  284 */       List LC1Down = new LinkedList();
/*  285 */       List C1var = new LinkedList();
/*      */ 
/*      */       
/*  288 */       Connector c2 = ll.get(0);
/*  289 */       Connector c1 = ll.get(1);
/*  290 */       Integer position = (Integer)ll.get(2);
/*  291 */       int portposition = position.intValue();
/*      */ 
/*      */       
/*  294 */       ctype = (ConnectorType)EcoreUtil.copy((EObject)c2.getType());
/*      */       
/*  296 */       ctype.setName(String.valueOf(c2.getType().getName()) + J);
/*  297 */       J = Integer.valueOf(J.intValue() + 1);
/*  298 */       PortParameter pp = (PortParameter)EcoreUtil.copy((EObject)ctype.getPortParameter().get(portposition));
/*  299 */       ConnectorType c1typetmp = InteractionsFactory.eINSTANCE.createConnectorType();
/*  300 */       c1typetmp = (ConnectorType)EcoreUtil.copy((EObject)c1.getType());
/*      */       
/*  302 */       lportnew = ChangePortParameter(c1typetmp, portposition, ctype);
/*      */ 
/*      */       
/*  305 */       getVarPortCon(portposition, ctype, portvar);
/*  306 */       getExpVarUp(c1typetmp, LExpC1Up);
/*  307 */       for (Object o : LExpC1Up) {
/*      */         
/*  309 */         Expression E = (Expression)o;
/*  310 */         ExpressionReplaceRef(E, c1typetmp, lportnew);
/*      */       } 
/*  312 */       ChangeUpAction(ctype, portvar, pp, LExpC1Up);
/*      */ 
/*      */       
/*  315 */       ChangeDownActionC2(ctype, portvar, pp, LExpC1Up);
/*      */ 
/*      */ 
/*      */       
/*  319 */       getExpVarDownC2(ctype, LExpC2Down, pp);
/*  320 */       getDownC1(c1typetmp, LC1Down);
/*      */       
/*  322 */       getVarCon(c1typetmp, C1var);
/*      */       
/*  324 */       setDown(LC1Down, lportnew, LExpC2Down, C1var, c1typetmp);
/*      */       
/*  326 */       ChangeDownAction(ctype, LC1Down);
/*  327 */       Connector C = InteractionsFactory.eINSTANCE.createConnector();
/*  328 */       C.setType(ctype);
/*  329 */       setNewPort(C, portposition, c1, c2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  334 */       ChangeGuardAction(ctype, portvar, pp, LExpC1Up, c1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  350 */       InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  351 */       BinaryExpression BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/*      */       
/*  353 */       if (c1typetmp.getInteractionSpecification().size() == 0)
/*  354 */         c1typetmp.getInteractionSpecification().add(TransformationFunction.CreateInteractionSpecification(c1typetmp)); 
/*  355 */       InteractionSpecification ispec1 = (InteractionSpecification)c1typetmp.getInteractionSpecification().get(0);
/*  356 */       if (ispec.getGuard() != null && ispec1.getGuard() != null) {
/*  357 */         BE.setLeftOperand(ispec.getGuard());
/*  358 */         BE.setRightOperand(ispec1.getGuard());
/*  359 */         BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/*  360 */         BE.setOperator(BO);
/*  361 */         ispec.setGuard((Expression)EcoreUtil.copy((EObject)BE));
/*      */       }
/*  363 */       else if (ispec1.getGuard() != null) {
/*      */         
/*  365 */         ispec.setGuard(ispec1.getGuard());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  373 */       addConnectorType(module, ctype);
/*  374 */       C.setName(c2.getName());
/*      */       
/*  376 */       List LActualDataC2 = new LinkedList((Collection<?>)c2.getActualData());
/*  377 */       C.getActualData().addAll(LActualDataC2);
/*      */       
/*  379 */       this.TCT.getConnector().add(C);
/*  380 */       this.TCT.getConnector().remove(c2);
/*  381 */       if (!UseC1(c1))
/*  382 */         this.TCT.getConnector().remove(c1); 
/*  383 */       flatConnectors(module);
/*      */     }
/*  385 */     else if (TEST) {
/*      */ 
/*      */ 
/*      */       
/*  389 */       System.out.println("2 -- flat connectors done !");
/*      */     } 
/*  391 */     return null;
/*      */   }
/*      */   
/*      */   private void ChangeDownActionC2(ConnectorType ctype, List portvar, PortParameter PP, List NewExpRef) {
/*  395 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  396 */     if (ispec.getDownAction() instanceof CompositeAction) {
/*  397 */       CompositeAction caa = (CompositeAction)ispec.getDownAction();
/*  398 */       if (caa != null) {
/*  399 */         List LAssAction = new LinkedList((Collection<?>)caa.getContent());
/*  400 */         for (Object o : LAssAction) {
/*      */           
/*  402 */           if (o instanceof AssignmentAction) {
/*      */             
/*  404 */             AssignmentAction AA = (AssignmentAction)o;
/*  405 */             ExpressionReplaceExpRef(AA.getAssignedValue(), portvar, PP, NewExpRef);
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*  410 */           System.out.println("TO DO");
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  415 */       ispec.getUpAction();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getIndexPortType(Module M) {
/*  496 */     List Lbiptype = new LinkedList();
/*  497 */     EList eList = M.getBipType();
/*  498 */     int i = 0;
/*  499 */     for (Object o : eList) {
/*      */       
/*  501 */       if (!(o instanceof ujf.verimag.bip.Core.Behaviors.PortType))
/*  502 */         return i; 
/*  503 */       i++;
/*      */     } 
/*  505 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConnectorType(Module m, ConnectorType CT) {
/*  513 */     int i = getIndexPortType(m);
/*  514 */     m.getBipType().add(i, CT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setNewPort(Connector C, int portposition, Connector c1, Connector c2) {
/*  522 */     int i = 0;
/*  523 */     EList lpc1 = c1.getActualPort();
/*  524 */     EList lpc2 = c2.getActualPort();
/*  525 */     int size1 = lpc1.size();
/*  526 */     int size2 = lpc2.size();
/*  527 */     int size = size1 + size2 - 1;
/*  528 */     for (i = 0; i < size; i++) {
/*      */       
/*  530 */       if (i < portposition) {
/*      */         
/*  532 */         InnerPortReference pr = (InnerPortReference)EcoreUtil.copy((EObject)lpc2.get(i));
/*  533 */         C.getActualPort().add(pr);
/*      */       }
/*  535 */       else if (i >= portposition && i < portposition + size1) {
/*      */         
/*  537 */         InnerPortReference pr = (InnerPortReference)EcoreUtil.copy((EObject)lpc1.get(i - portposition));
/*  538 */         C.getActualPort().add(pr);
/*      */       }
/*      */       else {
/*      */         
/*  542 */         InnerPortReference pr = (InnerPortReference)EcoreUtil.copy((EObject)lpc2.get(i - size1 + 1));
/*  543 */         C.getActualPort().add(pr);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ChangeDownAction(ConnectorType ctype, List down) {
/*  552 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  553 */     CompositeAction CA = (CompositeAction)ispec.getDownAction();
/*  554 */     if (CA != null)
/*      */     {
/*  556 */       CA.getContent().addAll(down);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setDown(List<?> LC1Down, List<PortParameter> lportnew, List expC2Down, List c1var, ConnectorType c1type) {
/*  564 */     List newdown = new LinkedList(LC1Down);
/*      */     
/*  566 */     for (Object o : newdown) {
/*      */       
/*  568 */       AssignmentAction AA = (AssignmentAction)o;
/*  569 */       RequiredDataParameterReference rdp = (RequiredDataParameterReference)AA.getAssignedTarget();
/*  570 */       PortParameter PP = (PortParameter)EcoreUtil.copy((EObject)lportnew.get(c1type.getPortParameter().indexOf(rdp.getPortReference().getTarget())));
/*      */       
/*  572 */       if (AA.getAssignedValue() instanceof RequiredDataParameterReference) {
/*      */         
/*  574 */         RequiredDataParameterReference rdp1 = (RequiredDataParameterReference)AA.getAssignedValue();
/*  575 */         if (rdp1.getPortReference().getTarget().getName() == rdp.getPortReference().getTarget().getName()) {
/*  576 */           rdp1.getPortReference().setTarget(PP);
/*      */         }
/*      */       } 
/*  579 */       rdp.getPortReference().setTarget(PP);
/*  580 */       Expression E = AA.getAssignedValue();
/*  581 */       if (expC2Down.size() != 0) {
/*  582 */         ExpressionReplaceExpVar(E, c1var, expC2Down);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ChangeUpAction(ConnectorType ctype, List portvar, PortParameter PP, List NewExpRef) {
/*  591 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  592 */     if (ispec.getUpAction() instanceof CompositeAction) {
/*  593 */       CompositeAction caa = (CompositeAction)ispec.getUpAction();
/*  594 */       if (caa != null) {
/*  595 */         List LAssAction = new LinkedList((Collection<?>)caa.getContent());
/*  596 */         for (Object o : LAssAction) {
/*      */           
/*  598 */           if (o instanceof AssignmentAction) {
/*      */             
/*  600 */             AssignmentAction AA = (AssignmentAction)o;
/*  601 */             ExpressionReplaceExpRef(AA.getAssignedValue(), portvar, PP, NewExpRef);
/*      */             
/*      */             continue;
/*      */           } 
/*  605 */           System.out.println("TO DO");
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  610 */       ispec.getUpAction();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ChangeGuardAction(ConnectorType ctype, List portvar, PortParameter PP, List NewExpRef, Connector c1) {
/*  622 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  623 */     Expression E = ispec.getGuard();
/*  624 */     if (NewExpRef.size() != 0) {
/*  625 */       ExpressionReplaceExpRef(E, portvar, PP, NewExpRef);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List ChangePortParameter(ConnectorType c1type, int portposition, ConnectorType ctype) {
/*  634 */     EList lport = c1type.getPortParameter();
/*  635 */     List<PortParameter> lportparacp = new LinkedList();
/*  636 */     List<PortParameterReference> lportpararef = new LinkedList();
/*  637 */     List<PortParameterReference> lportinter = new LinkedList();
/*  638 */     for (Object o : lport) {
/*      */       
/*  640 */       PortParameter portparam = (PortParameter)o;
/*  641 */       PortParameter portparamcp = InteractionsFactory.eINSTANCE.createPortParameter();
/*  642 */       portparamcp = (PortParameter)EcoreUtil.copy((EObject)portparam);
/*  643 */       portparamcp.setName(String.valueOf(portparamcp.getName()) + K);
/*  644 */       portparam.setName(String.valueOf(portparam.getName()) + K);
/*      */ 
/*      */       
/*  647 */       K = Integer.valueOf(K.intValue() + 1);
/*  648 */       PortParameterReference portref = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  649 */       PortParameterReference portinter = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  650 */       portref.setTarget(portparamcp);
/*  651 */       portinter.setTarget(portparamcp);
/*  652 */       lportparacp.add(portparamcp);
/*  653 */       lportpararef.add(portref);
/*  654 */       lportinter.add(portinter);
/*      */     } 
/*      */     
/*  657 */     ctype.getPortParameter().remove(portposition);
/*  658 */     ctype.getPortParameter().addAll(portposition, lportparacp);
/*  659 */     if (ctype.getDefinition() instanceof ACFusion) {
/*  660 */       ACFusion portdef = (ACFusion)ctype.getDefinition();
/*  661 */       portdef.getOperand().remove(portposition);
/*  662 */       portdef.getOperand().addAll(portposition, lportpararef);
/*  663 */       InteractionSpecification ispe = null;
/*      */       
/*  665 */       if (ctype.getInteractionSpecification().size() == 0) {
/*  666 */         ctype.getInteractionSpecification().add(TransformationFunction.CreateInteractionSpecification(ctype));
/*      */       }
/*  668 */       ispe = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*      */ 
/*      */       
/*  671 */       ispe.getInteraction().getPort().remove(portposition);
/*  672 */       ispe.getInteraction().getPort().addAll(portposition, lportinter);
/*      */     }
/*  674 */     else if (ctype.getDefinition() instanceof PortParameterReference) {
/*      */ 
/*      */       
/*  677 */       ACFusion pordef = PortExpressionsFactory.eINSTANCE.createACFusion();
/*  678 */       pordef.getOperand().addAll(lportpararef);
/*  679 */       ctype.setDefinition((PortExpression)pordef);
/*  680 */       InteractionSpecification ispe = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  681 */       ispe.getInteraction().getPort().remove(portposition);
/*  682 */       ispe.getInteraction().getPort().addAll(portposition, lportinter);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  689 */     return lportparacp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List getConnector() {
/*  697 */     List<Connector> L = new LinkedList();
/*  698 */     CompoundType CT = this.TCT;
/*  699 */     EList Lconnector = CT.getConnector();
/*  700 */     int size = Lconnector.size();
/*      */     
/*  702 */     for (int i = --size; i >= 0; i--) {
/*  703 */       Connector con = (Connector)Lconnector.get(i);
/*  704 */       EList lport = con.getActualPort();
/*  705 */       for (Object o : lport) {
/*      */         
/*  707 */         InnerPortReference p = (InnerPortReference)o;
/*  708 */         for (int j = 0; j < i; j++) {
/*  709 */           Connector con1 = (Connector)Lconnector.get(j);
/*  710 */           if (p.getTargetInstance().getTargetPart().getName() == con1.getName()) {
/*      */             
/*  712 */             L.add(con);
/*  713 */             L.add(con1);
/*  714 */             L.add(Integer.valueOf(lport.indexOf(p)));
/*  715 */             return L;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  720 */     return L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean UseC1(Connector c1) {
/*  728 */     CompoundType CT = this.TCT;
/*  729 */     EList Lconnector = CT.getConnector();
/*  730 */     int size = Lconnector.size();
/*      */     
/*  732 */     for (int i = --size; i >= 0; i--) {
/*  733 */       Connector con = (Connector)Lconnector.get(i);
/*  734 */       EList lport = con.getActualPort();
/*  735 */       for (Object o : lport) {
/*      */         
/*  737 */         InnerPortReference p = (InnerPortReference)o;
/*  738 */         if (p.getTargetInstance().getTargetPart().getName() == c1.getName())
/*  739 */           return true; 
/*      */       } 
/*      */     } 
/*  742 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getExpVarUp(ConnectorType c1type, List<Expression> ExpC1Up) {
/*  751 */     if (c1type.getInteractionSpecification().size() != 0) {
/*      */       
/*  753 */       InteractionSpecification ispec = (InteractionSpecification)c1type.getInteractionSpecification().get(0);
/*  754 */       if (ispec.getUpAction() instanceof CompositeAction) {
/*  755 */         CompositeAction CA = (CompositeAction)ispec.getUpAction();
/*  756 */         if (CA != null) {
/*  757 */           EList LAction = CA.getContent();
/*  758 */           DefinitionBinding db = (DefinitionBinding)c1type.getPort().getBinding();
/*  759 */           EList eList1 = db.getDefinition().getExposedVariable();
/*  760 */           for (Object o1 : eList1) {
/*      */             
/*  762 */             Variable V = (Variable)o1;
/*  763 */             for (Object o : LAction) {
/*      */               
/*  765 */               if (o instanceof AssignmentAction) {
/*      */                 
/*  767 */                 AssignmentAction E = (AssignmentAction)o;
/*  768 */                 VariableReference vrtmp = (VariableReference)E.getAssignedTarget();
/*  769 */                 if (vrtmp.getTargetVariable().getName() == V.getName()) {
/*      */                   
/*  771 */                   Expression E1 = (Expression)EcoreUtil.copy((EObject)E.getAssignedValue());
/*  772 */                   ExpC1Up.add(E1);
/*      */                 } 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/*  778 */               System.out.println("Up Action should be Assignment");
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  784 */         ispec.getUpAction();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getVarPortCon(int portposition, ConnectorType ctype, List<String> portvar) {
/*  799 */     PortParameter portpara = (PortParameter)ctype.getPortParameter().get(portposition);
/*  800 */     for (Object o : portpara.getType().getDataParameter()) {
/*      */       
/*  802 */       DataParameter dp = (DataParameter)o;
/*  803 */       portvar.add(dp.getName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getVarCon(ConnectorType ctype, List<String> convar) {
/*  812 */     for (Object o : ctype.getVariable()) {
/*      */       
/*  814 */       Variable dp = (Variable)o;
/*  815 */       convar.add(dp.getName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ExpressionReplaceRef(Expression E, ConnectorType c1, List<PortParameter> NewRef) {
/*  824 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/*  826 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/*  827 */       PortParameter PP = (PortParameter)EcoreUtil.copy((EObject)NewRef.get(c1.getPortParameter().indexOf(rdr.getPortReference().getTarget())));
/*  828 */       rdr.getPortReference().setTarget(PP);
/*      */     }
/*  830 */     else if (E instanceof FunctionCallExpression) {
/*      */       
/*  832 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/*  833 */       for (Object o : Fcall.getActualData())
/*      */       {
/*  835 */         Expression E1 = (Expression)o;
/*  836 */         ExpressionReplaceRef(E1, c1, NewRef);
/*      */       }
/*      */     
/*  839 */     } else if (E instanceof BinaryExpression) {
/*      */       
/*  841 */       BinaryExpression BE = (BinaryExpression)E;
/*  842 */       ExpressionReplaceRef(BE.getRightOperand(), c1, NewRef);
/*  843 */       ExpressionReplaceRef(BE.getLeftOperand(), c1, NewRef);
/*      */     
/*      */     }
/*  846 */     else if (E instanceof UnaryExpression) {
/*      */       
/*  848 */       UnaryExpression UE = (UnaryExpression)E;
/*  849 */       ExpressionReplaceRef(UE.getOperand(), c1, NewRef);
/*      */     } else {
/*      */       
/*  852 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ExpressionReplaceExpRef(Expression E, List portvar, PortParameter PP, List<Expression> NewExpRef) {
/*  865 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/*  867 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/*  868 */       String VarName = rdr.getTargetParameter().getName();
/*  869 */       if (portvar.contains(VarName) && rdr.getPortReference().getTarget().getName().equals(PP.getName()))
/*      */       {
/*  871 */         RequiredDataParameterReference requiredDataParameterReference = rdr;
/*  872 */         if (NewExpRef.size() > portvar.indexOf(VarName))
/*      */         {
/*  874 */           Expression E1 = (Expression)EcoreUtil.copy((EObject)NewExpRef.get(portvar.indexOf(VarName)));
/*  875 */           if (E1 != null) {
/*  876 */             if (requiredDataParameterReference.eContainer() instanceof FunctionCallExpression) {
/*      */               
/*  878 */               FunctionCallExpression fcall = (FunctionCallExpression)requiredDataParameterReference.eContainer();
/*  879 */               fcall.getActualData().set(fcall.getActualData().indexOf(rdr), EcoreUtil.copy((EObject)E1));
/*      */             }
/*  881 */             else if (requiredDataParameterReference.eContainer() instanceof BinaryExpression) {
/*      */               
/*  883 */               BinaryExpression bexp = (BinaryExpression)requiredDataParameterReference.eContainer();
/*  884 */               if (bexp.getLeftOperand().equals(rdr))
/*  885 */                 bexp.setLeftOperand((Expression)EcoreUtil.copy((EObject)E1)); 
/*  886 */               if (bexp.getRightOperand().equals(rdr)) {
/*  887 */                 bexp.setRightOperand((Expression)EcoreUtil.copy((EObject)E1));
/*      */               }
/*  889 */             } else if (requiredDataParameterReference.eContainer() instanceof AssignmentAction) {
/*      */               
/*  891 */               AssignmentAction aa = (AssignmentAction)requiredDataParameterReference.eContainer();
/*  892 */               aa.setAssignedValue((Expression)EcoreUtil.copy((EObject)E1));
/*      */             }
/*  894 */             else if (requiredDataParameterReference.eContainer() instanceof UnaryExpression) {
/*      */               
/*  896 */               UnaryExpression UE = (UnaryExpression)requiredDataParameterReference.eContainer();
/*  897 */               UE.setOperand((Expression)EcoreUtil.copy((EObject)E1));
/*      */             }
/*  899 */             else if (requiredDataParameterReference.eContainer() instanceof InteractionSpecification) {
/*      */               
/*  901 */               InteractionSpecification ispectmp = (InteractionSpecification)requiredDataParameterReference.eContainer();
/*  902 */               ispectmp.setGuard(E1);
/*      */             }
/*      */           
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*      */           return;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  914 */     } else if (E instanceof FunctionCallExpression) {
/*      */       
/*  916 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/*  917 */       for (Object o : Fcall.getActualData())
/*      */       {
/*  919 */         Expression E1 = (Expression)o;
/*  920 */         ExpressionReplaceExpRef(E1, portvar, PP, NewExpRef);
/*      */       }
/*      */     
/*  923 */     } else if (E instanceof BinaryExpression) {
/*      */       
/*  925 */       BinaryExpression BE = (BinaryExpression)E;
/*  926 */       ExpressionReplaceExpRef(BE.getRightOperand(), portvar, PP, NewExpRef);
/*  927 */       ExpressionReplaceExpRef(BE.getLeftOperand(), portvar, PP, NewExpRef);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  932 */     else if (E instanceof UnaryExpression) {
/*      */       
/*  934 */       UnaryExpression UE = (UnaryExpression)E;
/*  935 */       ExpressionReplaceExpRef(UE.getOperand(), portvar, PP, NewExpRef);
/*      */     }
/*  937 */     else if (!(E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction)) {
/*      */ 
/*      */ 
/*      */       
/*  941 */       E instanceof VariableReference;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getExpVarDownC2(ConnectorType c2type, List<Expression> ExpC2Down, PortParameter PP) {
/*  967 */     if (c2type.getInteractionSpecification().size() > 0) {
/*      */       
/*  969 */       InteractionSpecification ispec = (InteractionSpecification)c2type.getInteractionSpecification().get(0);
/*  970 */       if (ispec.getDownAction() instanceof CompositeAction) {
/*  971 */         CompositeAction CA = (CompositeAction)ispec.getDownAction();
/*  972 */         if (CA != null) {
/*  973 */           EList LAction1 = CA.getContent();
/*  974 */           List LAction = new LinkedList((Collection<?>)LAction1);
/*  975 */           EList eList1 = PP.getType().getDataParameter();
/*  976 */           for (Object o1 : eList1) {
/*  977 */             DataParameter dptmp = (DataParameter)o1;
/*      */             
/*  979 */             for (Object o : LAction) {
/*      */               
/*  981 */               if (o instanceof AssignmentAction) {
/*      */                 
/*  983 */                 AssignmentAction E = (AssignmentAction)o;
/*  984 */                 if (E.getAssignedTarget() instanceof RequiredDataParameterReference) {
/*      */                   
/*  986 */                   RequiredDataParameterReference E1 = (RequiredDataParameterReference)E.getAssignedTarget();
/*  987 */                   if (E1.getPortReference().getTarget().getName() == PP.getName() && E1.getTargetParameter().getName() == dptmp.getName()) {
/*      */                     
/*  989 */                     ExpC2Down.add((Expression)EcoreUtil.copy((EObject)E.getAssignedValue()));
/*  990 */                     CA.getContent().remove(E);
/*      */                   } 
/*      */                 } 
/*      */ 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/*  998 */               System.out.println("Down Action should be Assignment");
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1004 */         ispec.getDownAction();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getDownC1(ConnectorType c1type, List<AssignmentAction> ExpC1Down) {
/* 1018 */     if (c1type.getInteractionSpecification().size() > 0) {
/* 1019 */       InteractionSpecification ispec = (InteractionSpecification)c1type.getInteractionSpecification().get(0);
/* 1020 */       if (ispec.getDownAction() instanceof CompositeAction) {
/* 1021 */         CompositeAction CA = (CompositeAction)ispec.getDownAction();
/* 1022 */         if (CA != null) {
/* 1023 */           EList LAction1 = CA.getContent();
/* 1024 */           List LAction = new LinkedList((Collection<?>)LAction1);
/* 1025 */           for (Object o : LAction) {
/*      */             
/* 1027 */             if (o instanceof AssignmentAction) {
/*      */               
/* 1029 */               AssignmentAction E = (AssignmentAction)o;
/* 1030 */               ExpC1Down.add((AssignmentAction)EcoreUtil.copy((EObject)E));
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/* 1036 */             System.out.println("Down Action should be Assignment");
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1041 */         ispec.getDownAction();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ExpressionReplaceExpVar(Expression E, List var, List<Expression> NewExp) {
/* 1064 */     if (E instanceof VariableReference) {
/*      */       
/* 1066 */       VariableReference vr = (VariableReference)E;
/* 1067 */       VariableReference variableReference1 = vr;
/* 1068 */       if (NewExp.size() > var.indexOf(vr.getTargetVariable().getName()))
/*      */       {
/* 1070 */         Expression E1 = (Expression)EcoreUtil.copy((EObject)NewExp.get(var.indexOf(vr.getTargetVariable().getName())));
/* 1071 */         if (variableReference1.eContainer() instanceof FunctionCallExpression) {
/*      */           
/* 1073 */           FunctionCallExpression fcall = (FunctionCallExpression)variableReference1.eContainer();
/* 1074 */           fcall.getActualData().set(fcall.getActualData().indexOf(vr), E1);
/*      */         }
/* 1076 */         else if (variableReference1.eContainer() instanceof BinaryExpression) {
/*      */           
/* 1078 */           BinaryExpression bexp = (BinaryExpression)variableReference1.eContainer();
/* 1079 */           if (bexp.getLeftOperand().equals(vr)) {
/* 1080 */             bexp.setLeftOperand(E1);
/*      */           } else {
/* 1082 */             bexp.setRightOperand(E1);
/*      */           }
/*      */         
/* 1085 */         } else if (variableReference1.eContainer() instanceof AssignmentAction) {
/*      */           
/* 1087 */           AssignmentAction aa = (AssignmentAction)variableReference1.eContainer();
/* 1088 */           aa.setAssignedValue(E1);
/*      */         }
/* 1090 */         else if (variableReference1.eContainer() instanceof UnaryExpression) {
/*      */           
/* 1092 */           UnaryExpression UE = (UnaryExpression)variableReference1.eContainer();
/* 1093 */           UE.setOperand(E1);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1102 */     else if (E instanceof FunctionCallExpression) {
/*      */       
/* 1104 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 1105 */       for (Object o : Fcall.getActualData())
/*      */       {
/* 1107 */         Expression E1 = (Expression)o;
/* 1108 */         ExpressionReplaceExpVar(E1, var, NewExp);
/*      */       }
/*      */     
/* 1111 */     } else if (E instanceof BinaryExpression) {
/*      */       
/* 1113 */       BinaryExpression BE = (BinaryExpression)E;
/* 1114 */       ExpressionReplaceExpVar(BE.getLeftOperand(), var, NewExp);
/* 1115 */       ExpressionReplaceExpVar(BE.getRightOperand(), var, NewExp);
/*      */     }
/* 1117 */     else if (E instanceof UnaryExpression) {
/*      */       
/* 1119 */       UnaryExpression UE = (UnaryExpression)E;
/* 1120 */       ExpressionReplaceExpVar(UE.getOperand(), var, NewExp);
/*      */     }
/* 1122 */     else if (!(E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction)) {
/*      */ 
/*      */ 
/*      */       
/* 1126 */       E instanceof RequiredDataParameterReference;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\TCompoundType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */