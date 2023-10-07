/*     */ package trans;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TConnectorType
/*     */ {
/*     */   private ConnectorType CT;
/*     */   public boolean flat = true;
/*     */   
/*     */   public TConnectorType(ConnectorType CT) {
/*  44 */     this.CT = (ConnectorType)EcoreUtil.copy((EObject)CT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Variable> GetNewVariable(Connector C) {
/*  52 */     ConnectorType CTtmp = this.CT;
/*  53 */     List<Variable> LVariable = new LinkedList<Variable>((Collection<? extends Variable>)CTtmp.getVariable());
/*  54 */     for (Variable o : LVariable) {
/*     */       
/*  56 */       Variable V = o;
/*  57 */       V.setName(String.valueOf(C.getName()) + V.getName());
/*     */     } 
/*  59 */     return LVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression GetGuard(Connector C, AtomType AT) {
/*  67 */     EList eList = this.CT.getInteractionSpecification();
/*     */     
/*  69 */     for (Object o : eList) {
/*     */       
/*  71 */       InteractionSpecification ISpec = (InteractionSpecification)o;
/*     */ 
/*     */       
/*  74 */       Expression E = ISpec.getGuard();
/*  75 */       if (E != null) {
/*     */         
/*  77 */         ExpressionReplace(E, C, AT);
/*  78 */         return (Expression)EcoreUtil.copy((EObject)E);
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ExpressionReplace(Expression E, Connector c, AtomType AT) {
/*  89 */     if (E instanceof RequiredDataParameterReference) {
/*     */       
/*  91 */       RequiredDataParameterReference RDPR = (RequiredDataParameterReference)E;
/*  92 */       String PortName = RDPR.getPortReference().getTarget().getName();
/*  93 */       String VariableName = RDPR.getTargetParameter().getName();
/*  94 */       int PortIndex = IndexPort(PortName);
/*  95 */       int VariableIndex = IndexVariable(RDPR.getPortReference().getTarget(), VariableName);
/*  96 */       InnerPortReference ipr = (InnerPortReference)c.getActualPort().get(PortIndex);
/*  97 */       DefinitionBinding db = (DefinitionBinding)ipr.getTargetPort().getBinding();
/*  98 */       Variable v = (Variable)db.getDefinition().getExposedVariable().get(VariableIndex);
/*  99 */       String Newv = String.valueOf(ipr.getTargetInstance().getTargetPart().getName()) + v.getName();
/* 100 */       Variable V = getVariable(Newv, AT);
/* 101 */       VariableReference VR = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 102 */       VR.setTargetVariable(V);
/*     */       
/* 104 */       RequiredDataParameterReference requiredDataParameterReference1 = RDPR;
/* 105 */       if (requiredDataParameterReference1.eContainer() instanceof FunctionCallExpression) {
/*     */         
/* 107 */         FunctionCallExpression fcall = (FunctionCallExpression)requiredDataParameterReference1.eContainer();
/* 108 */         fcall.getActualData().set(fcall.getActualData().indexOf(RDPR), VR);
/*     */       }
/* 110 */       else if (requiredDataParameterReference1.eContainer() instanceof BinaryExpression) {
/*     */         
/* 112 */         BinaryExpression bexp = (BinaryExpression)requiredDataParameterReference1.eContainer();
/* 113 */         if (bexp.getLeftOperand().equals(RDPR)) {
/* 114 */           bexp.setLeftOperand((Expression)VR);
/*     */         } else {
/* 116 */           bexp.setRightOperand((Expression)VR);
/*     */         } 
/* 118 */       } else if (requiredDataParameterReference1.eContainer() instanceof AssignmentAction) {
/*     */         
/* 120 */         AssignmentAction aa = (AssignmentAction)requiredDataParameterReference1.eContainer();
/* 121 */         if (aa.getAssignedValue().equals(E)) {
/* 122 */           aa.setAssignedValue((Expression)VR);
/*     */         }
/* 124 */       } else if (requiredDataParameterReference1.eContainer() instanceof UnaryExpression) {
/*     */         
/* 126 */         UnaryExpression UE = (UnaryExpression)requiredDataParameterReference1.eContainer();
/* 127 */         UE.setOperand((Expression)VR);
/*     */       } else {
/* 129 */         requiredDataParameterReference1.eContainer() instanceof InteractionSpecification;
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 136 */     else if (E instanceof FunctionCallExpression) {
/*     */       
/* 138 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 139 */       for (Object o : Fcall.getActualData())
/*     */       {
/* 141 */         Expression E1 = (Expression)o;
/* 142 */         ExpressionReplace(E1, c, AT);
/*     */       }
/*     */     
/* 145 */     } else if (E instanceof BinaryExpression) {
/*     */       
/* 147 */       BinaryExpression BE = (BinaryExpression)E;
/* 148 */       ExpressionReplace(BE.getLeftOperand(), c, AT);
/* 149 */       ExpressionReplace(BE.getRightOperand(), c, AT);
/*     */     }
/* 151 */     else if (E instanceof UnaryExpression) {
/*     */       
/* 153 */       UnaryExpression UE = (UnaryExpression)E;
/* 154 */       ExpressionReplace(UE.getOperand(), c, AT);
/*     */     } else {
/* 156 */       E instanceof IfAction;
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
/*     */   private void ExpressionReplace1(Expression E, Connector c, AtomType AT) {
/* 170 */     if (E instanceof RequiredDataParameterReference) {
/*     */       
/* 172 */       RequiredDataParameterReference RDPR = (RequiredDataParameterReference)E;
/* 173 */       String PortName = RDPR.getPortReference().getTarget().getName();
/* 174 */       String VariableName = RDPR.getTargetParameter().getName();
/* 175 */       int PortIndex = IndexPort(PortName);
/* 176 */       int VariableIndex = IndexVariable(RDPR.getPortReference().getTarget(), VariableName);
/* 177 */       InnerPortReference ipr = (InnerPortReference)c.getActualPort().get(PortIndex);
/* 178 */       DefinitionBinding db = (DefinitionBinding)ipr.getTargetPort().getBinding();
/* 179 */       Variable v = (Variable)db.getDefinition().getExposedVariable().get(VariableIndex);
/* 180 */       String Newv = String.valueOf(ipr.getTargetInstance().getTargetPart().getName()) + v.getName();
/* 181 */       Variable V = getVariable(Newv, AT);
/* 182 */       VariableReference VR = ExpressionsFactory.eINSTANCE.createVariableReference();
/* 183 */       VR.setTargetVariable(V);
/*     */       
/* 185 */       RequiredDataParameterReference requiredDataParameterReference1 = RDPR;
/* 186 */       if (requiredDataParameterReference1.eContainer() instanceof FunctionCallExpression) {
/*     */         
/* 188 */         FunctionCallExpression fcall = (FunctionCallExpression)requiredDataParameterReference1.eContainer();
/* 189 */         fcall.getActualData().set(fcall.getActualData().indexOf(RDPR), VR);
/*     */       }
/* 191 */       else if (requiredDataParameterReference1.eContainer() instanceof BinaryExpression) {
/*     */         
/* 193 */         BinaryExpression bexp = (BinaryExpression)requiredDataParameterReference1.eContainer();
/* 194 */         if (bexp.getLeftOperand().equals(RDPR)) {
/* 195 */           bexp.setLeftOperand((Expression)VR);
/*     */         } else {
/* 197 */           bexp.setRightOperand((Expression)VR);
/*     */         } 
/* 199 */       } else if (requiredDataParameterReference1.eContainer() instanceof AssignmentAction) {
/*     */         
/* 201 */         AssignmentAction aa = (AssignmentAction)requiredDataParameterReference1.eContainer();
/* 202 */         if (aa.getAssignedTarget().equals(E)) {
/* 203 */           aa.setAssignedTarget((DataReference)VR);
/*     */         }
/* 205 */       } else if (requiredDataParameterReference1.eContainer() instanceof UnaryExpression) {
/*     */         
/* 207 */         UnaryExpression UE = (UnaryExpression)requiredDataParameterReference1.eContainer();
/* 208 */         UE.setOperand((Expression)VR);
/*     */       } else {
/* 210 */         requiredDataParameterReference1.eContainer() instanceof InteractionSpecification;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 216 */     else if (E instanceof FunctionCallExpression) {
/*     */       
/* 218 */       FunctionCallExpression Fcall = (FunctionCallExpression)E;
/* 219 */       for (Object o : Fcall.getActualData())
/*     */       {
/* 221 */         Expression E1 = (Expression)o;
/* 222 */         ExpressionReplace1(E1, c, AT);
/*     */       }
/*     */     
/* 225 */     } else if (E instanceof BinaryExpression) {
/*     */       
/* 227 */       BinaryExpression BE = (BinaryExpression)E;
/* 228 */       ExpressionReplace1(BE.getLeftOperand(), c, AT);
/* 229 */       ExpressionReplace1(BE.getRightOperand(), c, AT);
/*     */     }
/* 231 */     else if (E instanceof UnaryExpression) {
/*     */       
/* 233 */       UnaryExpression UE = (UnaryExpression)E;
/* 234 */       ExpressionReplace1(UE.getOperand(), c, AT);
/*     */     } else {
/* 236 */       E instanceof IfAction;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int IndexVariable(PortParameter PP, String variableName) {
/* 245 */     PortType PT = PP.getType();
/* 246 */     EList eList = PT.getDataParameter();
/* 247 */     for (Object o : eList) {
/*     */       
/* 249 */       DataParameter DP = (DataParameter)o;
/* 250 */       if (DP.getName() == variableName)
/* 251 */         return eList.indexOf(o); 
/*     */     } 
/* 253 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int IndexPort(String PortName) {
/* 258 */     EList eList = this.CT.getPortParameter();
/* 259 */     for (Object o : eList) {
/*     */       
/* 261 */       PortParameter PP = (PortParameter)o;
/* 262 */       if (PP.getName().equals(PortName))
/* 263 */         return eList.indexOf(o); 
/*     */     } 
/* 265 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeAction GetUp(Connector C, AtomType AT) {
/* 272 */     EList eList = this.CT.getInteractionSpecification();
/* 273 */     for (Object o : eList) {
/*     */       
/* 275 */       InteractionSpecification ISpec = (InteractionSpecification)o;
/* 276 */       if (ISpec.getUpAction() instanceof CompositeAction) {
/* 277 */         CompositeAction CA = (CompositeAction)ISpec.getUpAction();
/* 278 */         if (CA != null) {
/*     */           
/* 280 */           EList LAction = CA.getContent();
/* 281 */           for (Object o1 : LAction) {
/*     */             
/* 283 */             if (o1 instanceof AssignmentAction) {
/*     */               
/* 285 */               AssignmentAction AA = (AssignmentAction)o1;
/* 286 */               ExpressionReplace(AA.getAssignedValue(), C, AT);
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 291 */             System.out.println("Up Action should be Assignment");
/*     */           } 
/*     */           
/* 294 */           return CA;
/*     */         } 
/*     */       } 
/*     */     } 
/* 298 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeAction GetDown(Connector C, AtomType AT) {
/* 305 */     EList eList = this.CT.getInteractionSpecification();
/* 306 */     for (Object o : eList) {
/*     */       
/* 308 */       InteractionSpecification ISpec = (InteractionSpecification)o;
/* 309 */       if (ISpec.getDownAction() instanceof CompositeAction) {
/* 310 */         CompositeAction CA = (CompositeAction)ISpec.getDownAction();
/* 311 */         if (CA != null) {
/*     */           
/* 313 */           EList LAction = CA.getContent();
/* 314 */           for (Object o1 : LAction) {
/*     */             
/* 316 */             if (o1 instanceof AssignmentAction) {
/*     */               
/* 318 */               AssignmentAction AA = (AssignmentAction)o1;
/* 319 */               ExpressionReplace1((Expression)AA.getAssignedTarget(), C, AT);
/* 320 */               ExpressionReplace(AA.getAssignedValue(), C, AT); continue;
/*     */             } 
/* 322 */             if (o1 instanceof FunctionCallExpression) {
/*     */               
/* 324 */               FunctionCallExpression fce = (FunctionCallExpression)o1;
/* 325 */               ExpressionReplace((Expression)fce, C, AT); continue;
/*     */             } 
/* 327 */             if (o1 instanceof IfAction) {
/* 328 */               IfAction Ifaction = (IfAction)o1;
/* 329 */               ExpressionReplace(Ifaction.getCondition(), C, AT);
/* 330 */               ExpressionReplace((Expression)Ifaction.getIfCase(), C, AT);
/* 331 */               ExpressionReplace((Expression)Ifaction.getElseCase(), C, AT);
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 336 */             System.out.println(String.valueOf(C.getType().getName()) + " Down Action should be Assignment Or Function Call Or If action");
/*     */           } 
/*     */           
/* 339 */           return CA;
/*     */         } 
/*     */       } 
/*     */     } 
/* 343 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getVariable(String VariableName, AtomType AT) {
/* 349 */     EList eList = AT.getVariable();
/* 350 */     for (Object o : eList) {
/*     */       
/* 352 */       Variable V = (Variable)o;
/*     */       
/* 354 */       if (V.getName().equals(VariableName))
/* 355 */         return V; 
/*     */     } 
/* 357 */     return null;
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
/*     */   public List<Transition> GetTransition(String PortName, AtomType at) {
/* 377 */     PetriNet PN = (PetriNet)at.getBehavior();
/* 378 */     List<Transition> LnewT = new LinkedList<Transition>();
/* 379 */     EList eList = PN.getTransition();
/* 380 */     for (Object o : eList) {
/*     */       
/* 382 */       Transition T = (Transition)o;
/* 383 */       PortDefinitionReference pdr = (PortDefinitionReference)T.getTrigger();
/* 384 */       if (pdr.getTarget().getName().equals(PortName)) {
/*     */         
/* 386 */         Transition TT = (Transition)EcoreUtil.copy((EObject)T);
/* 387 */         TT.getDestination().addAll(new LinkedList((Collection<?>)T.getDestination()));
/* 388 */         TT.getOrigin().addAll(new LinkedList((Collection<?>)T.getOrigin()));
/* 389 */         LnewT.add(TT);
/*     */       } 
/*     */     } 
/* 392 */     return LnewT;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\TConnectorType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */