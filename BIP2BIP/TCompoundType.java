/*      */ package BIP2BIP;
/*      */ 
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
/*      */ public class TCompoundType
/*      */ {
/*      */   private CompoundType TCT;
/*   45 */   private static Integer J = Integer.valueOf(0);
/*   46 */   private static Integer K = Integer.valueOf(0);
/*      */ 
/*      */   
/*      */   public static boolean TEST = true;
/*      */   
/*      */   int LL;
/*      */ 
/*      */   
/*      */   public TCompoundType(CompoundType TCT) {
/*   55 */     this.LL = 0;
/*      */     this.TCT = TCT;
/*      */   } private Component getComponent() {
/*   58 */     CompoundType ct = this.TCT;
/*   59 */     EList sc = ct.getSubcomponent();
/*   60 */     List sc1 = new LinkedList((Collection<?>)sc);
/*   61 */     for (Object o : sc1) {
/*      */       
/*   63 */       Component c = (Component)o;
/*   64 */       if (!(c.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType))
/*   65 */         return c; 
/*      */     } 
/*   67 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setPorts(Component C) {
/*   75 */     EList ConnAll = this.TCT.getConnector();
/*   76 */     ExportBinding p1 = null;
/*   77 */     ComponentType ct = C.getType();
/*   78 */     for (Object o : ConnAll) {
/*      */       
/*   80 */       Connector c = (Connector)o;
/*   81 */       EList LPort1 = c.getActualPort();
/*   82 */       List LPort = new LinkedList((Collection<?>)LPort1);
/*      */       
/*   84 */       for (Object o1 : LPort) {
/*      */         
/*   86 */         InnerPortReference p = (InnerPortReference)o1;
/*   87 */         if (p.getTargetInstance().getTargetPart() == C) {
/*      */           
/*   89 */           p.setTargetPort(GetPort(ct, p.getTargetPort()));
/*   90 */           p1 = (ExportBinding)EcoreUtil.copy((EObject)p.getTargetPort().getBinding());
/*   91 */           p.setTargetPort(p1.getTargetPort());
/*   92 */           p.getTargetInstance().setTargetPart(p1.getTargetInstance().getTargetPart());
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
/*  103 */     EList EPort = CT.getPort();
/*  104 */     for (Object o : EPort) {
/*      */       
/*  106 */       Port p1 = (Port)o;
/*  107 */       if (p1.getName() == p.getName())
/*  108 */         return p1; 
/*      */     } 
/*  110 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setComponent(Component C) {
/*  118 */     CompoundType CT = (CompoundType)C.getType();
/*  119 */     EList LComp1 = CT.getSubcomponent();
/*      */     
/*  121 */     List LComp = new LinkedList((Collection<?>)LComp1);
/*  122 */     List LDataParamCT = new LinkedList((Collection<?>)CT.getDataParameter());
/*  123 */     List<EObject> LActualParamC = new LinkedList((Collection<?>)C.getActualData());
/*      */ 
/*      */     
/*  126 */     for (Object o : LComp) {
/*      */       
/*  128 */       Component c = (Component)o;
/*  129 */       c.setName(String.valueOf(C.getName()) + c.getName());
/*      */ 
/*      */       
/*  132 */       EList<EObject> eList = c.getActualData();
/*  133 */       int i = 0;
/*  134 */       for (EObject o1 : eList) {
/*      */         
/*  136 */         if (o1 instanceof DataParameterReference) {
/*      */           
/*  138 */           DataParameterReference DPR = (DataParameterReference)o1;
/*  139 */           int index = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  140 */           EObject EO = EcoreUtil.copy(LActualParamC.get(index));
/*  141 */           eList.set(eList.indexOf(DPR), EO);
/*      */         
/*      */         }
/*  144 */         else if (o1 instanceof BinaryExpression) {
/*      */           
/*  146 */           BinaryExpression BE = (BinaryExpression)EcoreUtil.copy(o1);
/*  147 */           ReplaceDataParameterExpression((Expression)BE, (List)eList, LDataParamCT, LActualParamC);
/*  148 */           eList.set(eList.indexOf(o1), BE);
/*      */         } else {
/*      */           
/*  151 */           o1 instanceof FunctionCallExpression;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  156 */         i++;
/*      */       } 
/*      */       
/*  159 */       EList ctmp = this.TCT.getSubcomponent();
/*  160 */       ctmp.add(0, c);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void ReplaceDataParameterExpression(Expression e, List LDataParamSC, List LDataParamCT, List<EObject> LActualParamC) {
/*  166 */     if (e instanceof BinaryExpression) {
/*      */       
/*  168 */       BinaryExpression BE = (BinaryExpression)e;
/*  169 */       if (BE.getRightOperand() instanceof DataParameterReference) {
/*      */         
/*  171 */         DataParameterReference DPR = (DataParameterReference)BE.getRightOperand();
/*  172 */         int index = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  173 */         EObject EO = EcoreUtil.copy(LActualParamC.get(index));
/*  174 */         BE.setRightOperand((Expression)EO);
/*      */       }
/*  176 */       else if (BE.getRightOperand() instanceof BinaryExpression) {
/*  177 */         ReplaceDataParameterExpression(BE.getRightOperand(), LDataParamSC, LDataParamCT, LActualParamC);
/*  178 */       }  if (BE.getLeftOperand() instanceof DataParameterReference) {
/*      */         
/*  180 */         DataParameterReference DPR = (DataParameterReference)BE.getLeftOperand();
/*  181 */         int index = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  182 */         EObject EO = EcoreUtil.copy(LActualParamC.get(index));
/*  183 */         BE.setLeftOperand((Expression)EO);
/*      */       }
/*  185 */       else if (BE.getLeftOperand() instanceof BinaryExpression) {
/*  186 */         ReplaceDataParameterExpression(BE.getLeftOperand(), LDataParamSC, LDataParamCT, LActualParamC);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setConnector(Component C) {
/*  195 */     CompoundType CT = (CompoundType)C.getType();
/*  196 */     EList LConn1 = CT.getConnector();
/*  197 */     List<Connector> LConn = new LinkedList((Collection<?>)LConn1);
/*  198 */     int size = LConn.size();
/*      */     
/*  200 */     for (int i = --size; i >= 0; i--) {
/*      */       
/*  202 */       Connector c = LConn.get(i);
/*  203 */       c.setName(String.valueOf(C.getName()) + c.getName());
/*      */       
/*  205 */       EList ctmp = this.TCT.getConnector();
/*  206 */       ctmp.add(0, c);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isflat() {
/*  214 */     CompoundType ct = this.TCT;
/*  215 */     EList sc = ct.getSubcomponent();
/*  216 */     List sc1 = new LinkedList((Collection<?>)sc);
/*  217 */     for (Object o : sc1) {
/*      */       
/*  219 */       Component c = (Component)o;
/*  220 */       if (!(c.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType))
/*  221 */         return false; 
/*      */     } 
/*  223 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flatCompoundType() {
/*  231 */     boolean flat = isflat();
/*  232 */     while (!flat) {
/*      */       
/*  234 */       Component C = getComponent();
/*  235 */       CompoundType ct = InteractionsFactory.eINSTANCE.createCompoundType();
/*  236 */       ct = (CompoundType)EcoreUtil.copy((EObject)C.getType());
/*  237 */       C.setType((ComponentType)ct);
/*  238 */       setComponent(C);
/*  239 */       setConnector(C);
/*  240 */       setPorts(C);
/*  241 */       EcoreUtil.remove((EObject)C);
/*  242 */       flat = isflat();
/*      */     } 
/*  244 */     System.out.println("1 -- flat compound components done !");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConnectorType flatConnectors(Module module) {
/*  253 */     List<Connector> ll = getConnector();
/*  254 */     ConnectorType ctype = InteractionsFactory.eINSTANCE.createConnectorType();
/*  255 */     if (ll.size() != 0) {
/*      */       
/*  257 */       List portvar = new LinkedList();
/*  258 */       List LExpC1Up = new LinkedList();
/*  259 */       List lportnew = new LinkedList();
/*  260 */       List LExpC2Down = new LinkedList();
/*  261 */       List LC1Down = new LinkedList();
/*  262 */       List C1var = new LinkedList();
/*      */ 
/*      */       
/*  265 */       Connector c2 = ll.get(0);
/*  266 */       Connector c1 = ll.get(1);
/*  267 */       Integer position = (Integer)ll.get(2);
/*  268 */       int portposition = position.intValue();
/*      */ 
/*      */       
/*  271 */       ctype = (ConnectorType)EcoreUtil.copy((EObject)c2.getType());
/*      */       
/*  273 */       ctype.setName(String.valueOf(c2.getType().getName()) + J);
/*  274 */       J = Integer.valueOf(J.intValue() + 1);
/*  275 */       PortParameter pp = (PortParameter)EcoreUtil.copy((EObject)ctype.getPortParameter().get(portposition));
/*  276 */       ConnectorType c1typetmp = InteractionsFactory.eINSTANCE.createConnectorType();
/*  277 */       c1typetmp = (ConnectorType)EcoreUtil.copy((EObject)c1.getType());
/*      */       
/*  279 */       lportnew = ChangePortParameter(c1typetmp, portposition, ctype);
/*      */ 
/*      */       
/*  282 */       getVarPortCon(portposition, ctype, portvar);
/*  283 */       getExpVarUp(c1typetmp, LExpC1Up);
/*  284 */       for (Object o : LExpC1Up) {
/*      */         
/*  286 */         Expression E = (Expression)o;
/*  287 */         ExpressionReplaceRef(E, c1typetmp, lportnew);
/*      */       } 
/*  289 */       ChangeUpAction(ctype, portvar, pp, LExpC1Up);
/*      */ 
/*      */       
/*  292 */       ChangeDownActionC2(ctype, portvar, pp, LExpC1Up);
/*      */ 
/*      */ 
/*      */       
/*  296 */       getExpVarDownC2(ctype, LExpC2Down, pp);
/*  297 */       getDownC1(c1typetmp, LC1Down);
/*      */       
/*  299 */       getVarCon(c1typetmp, C1var);
/*      */       
/*  301 */       setDown(LC1Down, lportnew, LExpC2Down, C1var, c1typetmp);
/*      */       
/*  303 */       ChangeDownAction(ctype, LC1Down);
/*  304 */       Connector C = InteractionsFactory.eINSTANCE.createConnector();
/*  305 */       C.setType(ctype);
/*  306 */       setNewPort(C, portposition, c1, c2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  311 */       ChangeGuardAction(ctype, portvar, pp, LExpC1Up, c1);
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
/*  327 */       InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  328 */       BinaryExpression BE = ExpressionsFactory.eINSTANCE.createBinaryExpression();
/*  329 */       InteractionSpecification ispec1 = (InteractionSpecification)c1typetmp.getInteractionSpecification().get(0);
/*  330 */       if (ispec.getGuard() != null && ispec1.getGuard() != null) {
/*  331 */         BE.setLeftOperand(ispec.getGuard());
/*  332 */         BE.setRightOperand(ispec1.getGuard());
/*  333 */         BinaryOperator BO = BinaryOperator.LOGICAL_AND;
/*  334 */         BE.setOperator(BO);
/*  335 */         ispec.setGuard((Expression)EcoreUtil.copy((EObject)BE));
/*      */       }
/*  337 */       else if (ispec1.getGuard() != null) {
/*      */         
/*  339 */         ispec.setGuard(ispec1.getGuard());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  347 */       addConnectorType(module, ctype);
/*  348 */       C.setName(c2.getName());
/*      */       
/*  350 */       List LActualDataC2 = new LinkedList((Collection<?>)c2.getActualData());
/*  351 */       C.getActualData().addAll(LActualDataC2);
/*      */       
/*  353 */       this.TCT.getConnector().add(C);
/*  354 */       this.TCT.getConnector().remove(c2);
/*  355 */       if (!UseC1(c1))
/*  356 */         this.TCT.getConnector().remove(c1); 
/*  357 */       flatConnectors(module);
/*      */     }
/*  359 */     else if (TEST) {
/*      */ 
/*      */ 
/*      */       
/*  363 */       System.out.println("2 -- flat connectors done !");
/*      */     } 
/*  365 */     return null;
/*      */   }
/*      */   
/*      */   private void ChangeDownActionC2(ConnectorType ctype, List portvar, PortParameter PP, List NewExpRef) {
/*  369 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  370 */     if (ispec.getDownAction() instanceof CompositeAction) {
/*  371 */       CompositeAction caa = (CompositeAction)ispec.getDownAction();
/*  372 */       if (caa != null) {
/*  373 */         List LAssAction = new LinkedList((Collection<?>)caa.getContent());
/*  374 */         for (Object o : LAssAction) {
/*      */           
/*  376 */           if (o instanceof AssignmentAction) {
/*      */             
/*  378 */             AssignmentAction AA = (AssignmentAction)o;
/*  379 */             ExpressionReplaceExpRef(AA.getAssignedValue(), portvar, PP, NewExpRef);
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*  384 */           System.out.println("TO DO");
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  389 */       ispec.getUpAction();
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
/*  470 */     List Lbiptype = new LinkedList();
/*  471 */     EList eList = M.getBipType();
/*  472 */     int i = 0;
/*  473 */     for (Object o : eList) {
/*      */       
/*  475 */       if (!(o instanceof ujf.verimag.bip.Core.Behaviors.PortType))
/*  476 */         return i; 
/*  477 */       i++;
/*      */     } 
/*  479 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConnectorType(Module m, ConnectorType CT) {
/*  487 */     int i = getIndexPortType(m);
/*  488 */     m.getBipType().add(i, CT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setNewPort(Connector C, int portposition, Connector c1, Connector c2) {
/*  496 */     int i = 0;
/*  497 */     EList lpc1 = c1.getActualPort();
/*  498 */     EList lpc2 = c2.getActualPort();
/*  499 */     int size1 = lpc1.size();
/*  500 */     int size2 = lpc2.size();
/*  501 */     int size = size1 + size2 - 1;
/*  502 */     for (i = 0; i < size; i++) {
/*      */       
/*  504 */       if (i < portposition) {
/*      */         
/*  506 */         InnerPortReference pr = (InnerPortReference)EcoreUtil.copy((EObject)lpc2.get(i));
/*  507 */         C.getActualPort().add(pr);
/*      */       }
/*  509 */       else if (i >= portposition && i < portposition + size1) {
/*      */         
/*  511 */         InnerPortReference pr = (InnerPortReference)EcoreUtil.copy((EObject)lpc1.get(i - portposition));
/*  512 */         C.getActualPort().add(pr);
/*      */       }
/*      */       else {
/*      */         
/*  516 */         InnerPortReference pr = (InnerPortReference)EcoreUtil.copy((EObject)lpc2.get(i - size1 + 1));
/*  517 */         C.getActualPort().add(pr);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ChangeDownAction(ConnectorType ctype, List down) {
/*  526 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  527 */     CompositeAction CA = (CompositeAction)ispec.getDownAction();
/*  528 */     if (CA != null)
/*      */     {
/*  530 */       CA.getContent().addAll(down);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setDown(List<?> LC1Down, List<PortParameter> lportnew, List expC2Down, List c1var, ConnectorType c1type) {
/*  538 */     List newdown = new LinkedList(LC1Down);
/*      */     
/*  540 */     for (Object o : newdown) {
/*      */       
/*  542 */       AssignmentAction AA = (AssignmentAction)o;
/*  543 */       RequiredDataParameterReference rdp = (RequiredDataParameterReference)AA.getAssignedTarget();
/*  544 */       PortParameter PP = (PortParameter)EcoreUtil.copy((EObject)lportnew.get(c1type.getPortParameter().indexOf(rdp.getPortReference().getTarget())));
/*      */       
/*  546 */       if (AA.getAssignedValue() instanceof RequiredDataParameterReference) {
/*      */         
/*  548 */         RequiredDataParameterReference rdp1 = (RequiredDataParameterReference)AA.getAssignedValue();
/*  549 */         if (rdp1.getPortReference().getTarget().getName() == rdp.getPortReference().getTarget().getName()) {
/*  550 */           rdp1.getPortReference().setTarget(PP);
/*      */         }
/*      */       } 
/*  553 */       rdp.getPortReference().setTarget(PP);
/*  554 */       Expression E = AA.getAssignedValue();
/*  555 */       if (expC2Down.size() != 0) {
/*  556 */         ExpressionReplaceExpVar(E, c1var, expC2Down);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ChangeUpAction(ConnectorType ctype, List portvar, PortParameter PP, List NewExpRef) {
/*  565 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  566 */     if (ispec.getUpAction() instanceof CompositeAction) {
/*  567 */       CompositeAction caa = (CompositeAction)ispec.getUpAction();
/*  568 */       if (caa != null) {
/*  569 */         List LAssAction = new LinkedList((Collection<?>)caa.getContent());
/*  570 */         for (Object o : LAssAction) {
/*      */           
/*  572 */           if (o instanceof AssignmentAction) {
/*      */             
/*  574 */             AssignmentAction AA = (AssignmentAction)o;
/*  575 */             ExpressionReplaceExpRef(AA.getAssignedValue(), portvar, PP, NewExpRef);
/*      */             
/*      */             continue;
/*      */           } 
/*  579 */           System.out.println("TO DO");
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  584 */       ispec.getUpAction();
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
/*  596 */     InteractionSpecification ispec = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  597 */     Expression E = ispec.getGuard();
/*  598 */     if (NewExpRef.size() != 0) {
/*  599 */       ExpressionReplaceExpRef(E, portvar, PP, NewExpRef);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List ChangePortParameter(ConnectorType c1type, int portposition, ConnectorType ctype) {
/*  608 */     EList lport = c1type.getPortParameter();
/*  609 */     List<PortParameter> lportparacp = new LinkedList();
/*  610 */     List<PortParameterReference> lportpararef = new LinkedList();
/*  611 */     List<PortParameterReference> lportinter = new LinkedList();
/*  612 */     for (Object o : lport) {
/*      */       
/*  614 */       PortParameter portparam = (PortParameter)o;
/*  615 */       PortParameter portparamcp = InteractionsFactory.eINSTANCE.createPortParameter();
/*  616 */       portparamcp = (PortParameter)EcoreUtil.copy((EObject)portparam);
/*  617 */       portparamcp.setName(String.valueOf(portparamcp.getName()) + K);
/*  618 */       portparam.setName(String.valueOf(portparam.getName()) + K);
/*      */ 
/*      */       
/*  621 */       K = Integer.valueOf(K.intValue() + 1);
/*  622 */       PortParameterReference portref = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  623 */       PortParameterReference portinter = InteractionsFactory.eINSTANCE.createPortParameterReference();
/*  624 */       portref.setTarget(portparamcp);
/*  625 */       portinter.setTarget(portparamcp);
/*  626 */       lportparacp.add(portparamcp);
/*  627 */       lportpararef.add(portref);
/*  628 */       lportinter.add(portinter);
/*      */     } 
/*      */     
/*  631 */     ctype.getPortParameter().remove(portposition);
/*  632 */     ctype.getPortParameter().addAll(portposition, lportparacp);
/*  633 */     if (ctype.getDefinition() instanceof ACFusion) {
/*  634 */       ACFusion portdef = (ACFusion)ctype.getDefinition();
/*  635 */       portdef.getOperand().remove(portposition);
/*  636 */       portdef.getOperand().addAll(portposition, lportpararef);
/*  637 */       InteractionSpecification ispe = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  638 */       ispe.getInteraction().getPort().remove(portposition);
/*  639 */       ispe.getInteraction().getPort().addAll(portposition, lportinter);
/*      */     }
/*  641 */     else if (ctype.getDefinition() instanceof PortParameterReference) {
/*      */ 
/*      */       
/*  644 */       ACFusion pordef = PortExpressionsFactory.eINSTANCE.createACFusion();
/*  645 */       pordef.getOperand().addAll(lportpararef);
/*  646 */       ctype.setDefinition((PortExpression)pordef);
/*  647 */       InteractionSpecification ispe = (InteractionSpecification)ctype.getInteractionSpecification().get(0);
/*  648 */       ispe.getInteraction().getPort().remove(portposition);
/*  649 */       ispe.getInteraction().getPort().addAll(portposition, lportinter);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  656 */     return lportparacp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List getConnector() {
/*  664 */     List<Connector> L = new LinkedList();
/*  665 */     CompoundType CT = this.TCT;
/*  666 */     EList Lconnector = CT.getConnector();
/*  667 */     int size = Lconnector.size();
/*      */     
/*  669 */     for (int i = --size; i >= 0; i--) {
/*  670 */       Connector con = (Connector)Lconnector.get(i);
/*  671 */       EList lport = con.getActualPort();
/*  672 */       for (Object o : lport) {
/*      */         
/*  674 */         InnerPortReference p = (InnerPortReference)o;
/*  675 */         for (int j = 0; j < i; j++) {
/*  676 */           Connector con1 = (Connector)Lconnector.get(j);
/*  677 */           if (p.getTargetInstance().getTargetPart().getName() == con1.getName()) {
/*      */             
/*  679 */             L.add(con);
/*  680 */             L.add(con1);
/*  681 */             L.add(Integer.valueOf(lport.indexOf(p)));
/*  682 */             return L;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  687 */     return L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean UseC1(Connector c1) {
/*  695 */     CompoundType CT = this.TCT;
/*  696 */     EList Lconnector = CT.getConnector();
/*  697 */     int size = Lconnector.size();
/*      */     
/*  699 */     for (int i = --size; i >= 0; i--) {
/*  700 */       Connector con = (Connector)Lconnector.get(i);
/*  701 */       EList lport = con.getActualPort();
/*  702 */       for (Object o : lport) {
/*      */         
/*  704 */         InnerPortReference p = (InnerPortReference)o;
/*  705 */         if (p.getTargetInstance().getTargetPart().getName() == c1.getName())
/*  706 */           return true; 
/*      */       } 
/*      */     } 
/*  709 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getExpVarUp(ConnectorType c1type, List<Expression> ExpC1Up) {
/*  718 */     if (c1type.getInteractionSpecification().size() != 0) {
/*      */       
/*  720 */       InteractionSpecification ispec = (InteractionSpecification)c1type.getInteractionSpecification().get(0);
/*  721 */       if (ispec.getUpAction() instanceof CompositeAction) {
/*  722 */         CompositeAction CA = (CompositeAction)ispec.getUpAction();
/*  723 */         if (CA != null) {
/*  724 */           EList LAction = CA.getContent();
/*  725 */           DefinitionBinding db = (DefinitionBinding)c1type.getPort().getBinding();
/*  726 */           EList eList1 = db.getDefinition().getExposedVariable();
/*  727 */           for (Object o1 : eList1) {
/*      */             
/*  729 */             Variable V = (Variable)o1;
/*  730 */             for (Object o : LAction) {
/*      */               
/*  732 */               if (o instanceof AssignmentAction) {
/*      */                 
/*  734 */                 AssignmentAction E = (AssignmentAction)o;
/*  735 */                 VariableReference vrtmp = (VariableReference)E.getAssignedTarget();
/*  736 */                 if (vrtmp.getTargetVariable().getName() == V.getName()) {
/*      */                   
/*  738 */                   Expression E1 = (Expression)EcoreUtil.copy((EObject)E.getAssignedValue());
/*  739 */                   ExpC1Up.add(E1);
/*      */                 }  continue;
/*      */               } 
/*  742 */               if (o instanceof FunctionCallExpression)
/*      */               {
/*      */                 
/*  745 */                 System.out.println("To add FCE Up Action should be Assignment");
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } else {
/*  751 */         ispec.getUpAction();
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
/*  766 */     PortParameter portpara = (PortParameter)ctype.getPortParameter().get(portposition);
/*  767 */     for (Object o : portpara.getType().getDataParameter()) {
/*      */       
/*  769 */       DataParameter dp = (DataParameter)o;
/*  770 */       portvar.add(dp.getName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getVarCon(ConnectorType ctype, List<String> convar) {
/*  779 */     for (Object o : ctype.getVariable()) {
/*      */       
/*  781 */       Variable dp = (Variable)o;
/*  782 */       convar.add(dp.getName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ExpressionReplaceRef(Expression E, ConnectorType c1, List<PortParameter> NewRef) {
/*  791 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/*  793 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/*  794 */       PortParameter PP = (PortParameter)EcoreUtil.copy((EObject)NewRef.get(c1.getPortParameter().indexOf(rdr.getPortReference().getTarget())));
/*  795 */       rdr.getPortReference().setTarget(PP);
/*      */     }
/*  797 */     else if (E instanceof FunctionCallExpression) {
/*      */       
/*  799 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/*  800 */       for (Object o : Fcall.getActualData())
/*      */       {
/*  802 */         Expression E1 = (Expression)o;
/*  803 */         ExpressionReplaceRef(E1, c1, NewRef);
/*      */       }
/*      */     
/*  806 */     } else if (E instanceof BinaryExpression) {
/*      */       
/*  808 */       BinaryExpression BE = (BinaryExpression)E;
/*  809 */       ExpressionReplaceRef(BE.getRightOperand(), c1, NewRef);
/*  810 */       ExpressionReplaceRef(BE.getLeftOperand(), c1, NewRef);
/*      */     
/*      */     }
/*  813 */     else if (E instanceof UnaryExpression) {
/*      */       
/*  815 */       UnaryExpression UE = (UnaryExpression)E;
/*  816 */       ExpressionReplaceRef(UE.getOperand(), c1, NewRef);
/*      */     } else {
/*      */       
/*  819 */       E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
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
/*  832 */     if (E instanceof RequiredDataParameterReference) {
/*      */       
/*  834 */       RequiredDataParameterReference rdr = (RequiredDataParameterReference)E;
/*  835 */       String VarName = rdr.getTargetParameter().getName();
/*  836 */       if (portvar.contains(VarName) && rdr.getPortReference().getTarget().getName().equals(PP.getName()))
/*      */       {
/*  838 */         RequiredDataParameterReference requiredDataParameterReference = rdr;
/*  839 */         if (NewExpRef.size() > portvar.indexOf(VarName))
/*      */         {
/*  841 */           Expression E1 = (Expression)EcoreUtil.copy((EObject)NewExpRef.get(portvar.indexOf(VarName)));
/*  842 */           if (E1 != null) {
/*  843 */             if (requiredDataParameterReference.eContainer() instanceof FunctionCallExpression) {
/*      */               
/*  845 */               FunctionCallExpression fcall = (FunctionCallExpression)requiredDataParameterReference.eContainer();
/*  846 */               fcall.getActualData().set(fcall.getActualData().indexOf(rdr), EcoreUtil.copy((EObject)E1));
/*      */             }
/*  848 */             else if (requiredDataParameterReference.eContainer() instanceof BinaryExpression) {
/*      */               
/*  850 */               BinaryExpression bexp = (BinaryExpression)requiredDataParameterReference.eContainer();
/*  851 */               if (bexp.getLeftOperand().equals(rdr))
/*  852 */                 bexp.setLeftOperand((Expression)EcoreUtil.copy((EObject)E1)); 
/*  853 */               if (bexp.getRightOperand().equals(rdr)) {
/*  854 */                 bexp.setRightOperand((Expression)EcoreUtil.copy((EObject)E1));
/*      */               }
/*  856 */             } else if (requiredDataParameterReference.eContainer() instanceof AssignmentAction) {
/*      */               
/*  858 */               AssignmentAction aa = (AssignmentAction)requiredDataParameterReference.eContainer();
/*  859 */               aa.setAssignedValue((Expression)EcoreUtil.copy((EObject)E1));
/*      */             }
/*  861 */             else if (requiredDataParameterReference.eContainer() instanceof UnaryExpression) {
/*      */               
/*  863 */               UnaryExpression UE = (UnaryExpression)requiredDataParameterReference.eContainer();
/*  864 */               UE.setOperand((Expression)EcoreUtil.copy((EObject)E1));
/*      */             }
/*  866 */             else if (requiredDataParameterReference.eContainer() instanceof InteractionSpecification) {
/*      */               
/*  868 */               InteractionSpecification ispectmp = (InteractionSpecification)requiredDataParameterReference.eContainer();
/*  869 */               ispectmp.setGuard(E1);
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
/*  881 */     } else if (E instanceof FunctionCallExpression) {
/*      */       
/*  883 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/*  884 */       for (Object o : Fcall.getActualData())
/*      */       {
/*  886 */         Expression E1 = (Expression)o;
/*  887 */         ExpressionReplaceExpRef(E1, portvar, PP, NewExpRef);
/*      */       }
/*      */     
/*  890 */     } else if (E instanceof BinaryExpression) {
/*      */       
/*  892 */       BinaryExpression BE = (BinaryExpression)E;
/*  893 */       ExpressionReplaceExpRef(BE.getRightOperand(), portvar, PP, NewExpRef);
/*  894 */       ExpressionReplaceExpRef(BE.getLeftOperand(), portvar, PP, NewExpRef);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  899 */     else if (E instanceof UnaryExpression) {
/*      */       
/*  901 */       UnaryExpression UE = (UnaryExpression)E;
/*  902 */       ExpressionReplaceExpRef(UE.getOperand(), portvar, PP, NewExpRef);
/*      */     }
/*  904 */     else if (!(E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction)) {
/*      */ 
/*      */ 
/*      */       
/*  908 */       E instanceof VariableReference;
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
/*  934 */     InteractionSpecification ispec = (InteractionSpecification)c2type.getInteractionSpecification().get(0);
/*  935 */     if (ispec.getDownAction() instanceof CompositeAction) {
/*  936 */       CompositeAction CA = (CompositeAction)ispec.getDownAction();
/*  937 */       if (CA != null) {
/*  938 */         EList LAction1 = CA.getContent();
/*  939 */         List LAction = new LinkedList((Collection<?>)LAction1);
/*  940 */         EList eList1 = PP.getType().getDataParameter();
/*  941 */         for (Object o1 : eList1) {
/*  942 */           DataParameter dptmp = (DataParameter)o1;
/*      */           
/*  944 */           for (Object o : LAction) {
/*      */             
/*  946 */             if (o instanceof AssignmentAction) {
/*      */               
/*  948 */               AssignmentAction E = (AssignmentAction)o;
/*  949 */               if (E.getAssignedTarget() instanceof RequiredDataParameterReference) {
/*      */                 
/*  951 */                 RequiredDataParameterReference E1 = (RequiredDataParameterReference)E.getAssignedTarget();
/*  952 */                 if (E1.getPortReference().getTarget().getName() == PP.getName() && E1.getTargetParameter().getName() == dptmp.getName()) {
/*      */                   
/*  954 */                   ExpC2Down.add((Expression)EcoreUtil.copy((EObject)E.getAssignedValue()));
/*  955 */                   CA.getContent().remove(E);
/*      */                 } 
/*      */               } 
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*  963 */             System.out.println("Down Action should be Assignment");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  968 */       ispec.getDownAction();
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
/*  981 */     InteractionSpecification ispec = (InteractionSpecification)c1type.getInteractionSpecification().get(0);
/*  982 */     if (ispec.getDownAction() instanceof CompositeAction) {
/*  983 */       CompositeAction CA = (CompositeAction)ispec.getDownAction();
/*  984 */       if (CA != null) {
/*  985 */         EList LAction1 = CA.getContent();
/*  986 */         List LAction = new LinkedList((Collection<?>)LAction1);
/*  987 */         for (Object o : LAction) {
/*      */           
/*  989 */           if (o instanceof AssignmentAction) {
/*      */             
/*  991 */             AssignmentAction E = (AssignmentAction)o;
/*  992 */             ExpC1Down.add((AssignmentAction)EcoreUtil.copy((EObject)E));
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*  998 */           System.out.println("Down Action should be Assignment");
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1003 */       ispec.getDownAction();
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
/* 1025 */     if (E instanceof VariableReference) {
/*      */       
/* 1027 */       VariableReference vr = (VariableReference)E;
/* 1028 */       VariableReference variableReference1 = vr;
/* 1029 */       if (NewExp.size() > var.indexOf(vr.getTargetVariable().getName()))
/*      */       {
/* 1031 */         Expression E1 = (Expression)EcoreUtil.copy((EObject)NewExp.get(var.indexOf(vr.getTargetVariable().getName())));
/* 1032 */         if (variableReference1.eContainer() instanceof FunctionCallExpression) {
/*      */           
/* 1034 */           FunctionCallExpression fcall = (FunctionCallExpression)variableReference1.eContainer();
/* 1035 */           fcall.getActualData().set(fcall.getActualData().indexOf(vr), E1);
/*      */         }
/* 1037 */         else if (variableReference1.eContainer() instanceof BinaryExpression) {
/*      */           
/* 1039 */           BinaryExpression bexp = (BinaryExpression)variableReference1.eContainer();
/* 1040 */           if (bexp.getLeftOperand().equals(vr)) {
/* 1041 */             bexp.setLeftOperand(E1);
/*      */           } else {
/* 1043 */             bexp.setRightOperand(E1);
/*      */           }
/*      */         
/* 1046 */         } else if (variableReference1.eContainer() instanceof AssignmentAction) {
/*      */           
/* 1048 */           AssignmentAction aa = (AssignmentAction)variableReference1.eContainer();
/* 1049 */           aa.setAssignedValue(E1);
/*      */         }
/* 1051 */         else if (variableReference1.eContainer() instanceof UnaryExpression) {
/*      */           
/* 1053 */           UnaryExpression UE = (UnaryExpression)variableReference1.eContainer();
/* 1054 */           UE.setOperand(E1);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1063 */     else if (E instanceof FunctionCallExpression) {
/*      */       
/* 1065 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 1066 */       for (Object o : Fcall.getActualData())
/*      */       {
/* 1068 */         Expression E1 = (Expression)o;
/* 1069 */         ExpressionReplaceExpVar(E1, var, NewExp);
/*      */       }
/*      */     
/* 1072 */     } else if (E instanceof BinaryExpression) {
/*      */       
/* 1074 */       BinaryExpression BE = (BinaryExpression)E;
/* 1075 */       ExpressionReplaceExpVar(BE.getLeftOperand(), var, NewExp);
/* 1076 */       ExpressionReplaceExpVar(BE.getRightOperand(), var, NewExp);
/*      */     }
/* 1078 */     else if (E instanceof UnaryExpression) {
/*      */       
/* 1080 */       UnaryExpression UE = (UnaryExpression)E;
/* 1081 */       ExpressionReplaceExpVar(UE.getOperand(), var, NewExp);
/*      */     }
/* 1083 */     else if (!(E instanceof ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction)) {
/*      */ 
/*      */ 
/*      */       
/* 1087 */       E instanceof RequiredDataParameterReference;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\TCompoundType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */