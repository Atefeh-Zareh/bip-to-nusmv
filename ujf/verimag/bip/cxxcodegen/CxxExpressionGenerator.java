/*     */ package ujf.verimag.bip.cxxcodegen;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IndexLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerInterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ import ujf.verimag.bip.cgeneration.CConstruct;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CxxExpressionGenerator
/*     */ {
/*     */   private CConstruct cBuilder;
/*     */   private boolean isPetriNet = false;
/*     */   
/*     */   public CxxExpressionGenerator(CConstruct builder) {
/*  47 */     this.cBuilder = builder;
/*     */   }
/*     */   
/*     */   public void setIsPetriNet(boolean isPetriNet) {
/*  51 */     this.isPetriNet = isPetriNet;
/*     */   }
/*     */   
/*     */   public CExpression generateExpr(Expression expr) {
/*  55 */     if (expr instanceof BooleanLiteral) {
/*  56 */       BooleanLiteral bl = (BooleanLiteral)expr;
/*  57 */       if (bl.isBValue()) {
/*  58 */         return (CExpression)this.cBuilder.createLiteral("true");
/*     */       }
/*  60 */       return (CExpression)this.cBuilder.createLiteral("false");
/*     */     } 
/*  62 */     if (expr instanceof ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral)
/*  63 */       return (CExpression)this.cBuilder.createLiteral("NULL"); 
/*  64 */     if (expr instanceof IntegerLiteral) {
/*  65 */       IntegerLiteral il = (IntegerLiteral)expr;
/*  66 */       return this.cBuilder.createLiteral(il.getIValue());
/*  67 */     }  if (expr instanceof RealLiteral) {
/*  68 */       RealLiteral rl = (RealLiteral)expr;
/*  69 */       return (CExpression)this.cBuilder.createLiteral(rl.getRValue());
/*  70 */     }  if (expr instanceof StringLiteral) {
/*  71 */       StringLiteral sl = (StringLiteral)expr;
/*  72 */       return (CExpression)this.cBuilder.createLiteral(sl.getSValue());
/*  73 */     }  if (expr instanceof DataParameterReference) {
/*  74 */       DataParameterReference dpr = (DataParameterReference)expr;
/*  75 */       return (CExpression)this.cBuilder.createSimpleName(dpr.getTargetParameter().getName());
/*  76 */     }  if (expr instanceof VariableReference) {
/*  77 */       VariableReference vr = (VariableReference)expr;
/*  78 */       return (CExpression)this.cBuilder.createSimpleName(vr.getTargetVariable().getName());
/*  79 */     }  if (expr instanceof InterfaceVariableReference) {
/*  80 */       InterfaceVariableReference ivr = (InterfaceVariableReference)expr;
/*  81 */       return (CExpression)this.cBuilder.createFuncCall("get_" + ivr.getTargetInterfaceVariable().getName());
/*  82 */     }  if (expr instanceof InnerInterfaceVariableReference) {
/*  83 */       InnerInterfaceVariableReference iivr = (InnerInterfaceVariableReference)expr;
/*  84 */       InterfaceVariable iv = iivr.getTargetInterfaceVariable();
/*  85 */       CExpression partExp = generatePartExp(iivr.getPartElementReference());
/*  86 */       return this.cBuilder.createPointed(partExp, (CExpression)this.cBuilder.createFuncCall("get_" + iv.getName()));
/*     */     } 
/*  88 */     if (expr instanceof StateReference) {
/*  89 */       StateReference sr = (StateReference)expr;
/*     */       
/*  91 */       if (this.isPetriNet) {
/*  92 */         return this.cBuilder.createStructured((CExpression)this.cBuilder.createSimpleName(sr.getTargetState().getName()), (CExpression)this.cBuilder.createFuncCall("token"));
/*     */       }
/*     */       
/*  95 */       return this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("BIP_STATE"), "==", (CExpression)this.cBuilder.createLiteral(sr.getTargetState().getName()));
/*     */     } 
/*     */     
/*  98 */     if (expr instanceof UnaryExpression) {
/*  99 */       UnaryExpression ue = (UnaryExpression)expr;
/* 100 */       String op = "";
/* 101 */       switch (ue.getOperator().getValue()) {
/*     */         case 0:
/* 103 */           op = "+";
/*     */           break;
/*     */         case 1:
/* 106 */           op = "-";
/*     */           break;
/*     */         case 2:
/* 109 */           op = "!";
/*     */           break;
/*     */         case 5:
/* 112 */           op = "~";
/*     */           break;
/*     */         case 3:
/* 115 */           op = "*";
/*     */           break;
/*     */         case 4:
/* 118 */           op = "&";
/*     */           break;
/*     */         case 6:
/* 121 */           op = "++";
/*     */           break;
/*     */         case 7:
/* 124 */           op = "--";
/*     */           break;
/*     */         default:
/* 127 */           op = "???";
/*     */           break;
/*     */       } 
/* 130 */       return ue.isPostfix() ? this.cBuilder.createOperation(generateExpr(ue.getOperand()), op, null) : this.cBuilder.createOperation(null, op, generateExpr(ue.getOperand()));
/*     */     } 
/* 132 */     if (expr instanceof BinaryExpression) {
/* 133 */       BinaryExpression be = (BinaryExpression)expr;
/* 134 */       String op = "";
/* 135 */       switch (be.getOperator().getValue())
/*     */       { case 0:
/* 137 */           op = "+";
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
/* 194 */           return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 1: op = "-"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 2: op = "*"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 3: op = "/"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 4: op = "%"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 5: op = "=="; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 6: op = "!="; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 7: op = "<"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 8: op = ">"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 9: op = "<="; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 10: op = ">="; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 11: op = "||"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 12: op = "&&"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 13: op = "|"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 14: op = "^"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 15: op = "&"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 16: op = "<<"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));case 17: op = ">>"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand())); }  op = "???"; return this.cBuilder.createOperation(generateExpr(be.getLeftOperand()), op, generateExpr(be.getRightOperand()));
/* 195 */     }  if (expr instanceof FunctionCallExpression) {
/* 196 */       FunctionCallExpression fce = (FunctionCallExpression)expr;
/* 197 */       CFunctionCall cfc = this.cBuilder.createFuncCall(fce.getFunctionName());
/* 198 */       for (Iterator<Expression> i = fce.getActualData().iterator(); i.hasNext(); ) {
/* 199 */         Expression param = i.next();
/* 200 */         cfc.getArgument().add(generateExpr(param));
/*     */       } 
/* 202 */       Expression prefix = fce.getNavigated();
/* 203 */       if (prefix != null) {
/* 204 */         if (fce.isIsOnRef()) {
/* 205 */           return this.cBuilder.createPointed(generateExpr(prefix), (CExpression)cfc);
/*     */         }
/* 207 */         return this.cBuilder.createStructured(generateExpr(prefix), (CExpression)cfc);
/*     */       } 
/*     */       
/* 210 */       return (CExpression)cfc;
/*     */     } 
/* 212 */     if (expr instanceof FieldNavigationExpression) {
/* 213 */       FieldNavigationExpression fne = (FieldNavigationExpression)expr;
/* 214 */       if (fne.isIsOnRef()) {
/* 215 */         return this.cBuilder.createPointed(generateExpr((Expression)fne.getNavigated()), (CExpression)this.cBuilder.createSimpleName(fne.getFieldName()));
/*     */       }
/*     */       
/* 218 */       return this.cBuilder.createStructured(generateExpr((Expression)fne.getNavigated()), (CExpression)this.cBuilder.createSimpleName(fne.getFieldName()));
/*     */     } 
/*     */     
/* 221 */     if (expr instanceof RequiredDataParameterReference) {
/* 222 */       RequiredDataParameterReference rdpr = (RequiredDataParameterReference)expr;
/* 223 */       PortParameterReference portParameterReference = rdpr.getPortReference();
/* 224 */       DataParameter dp = rdpr.getTargetParameter();
/* 225 */       String portName = "";
/* 226 */       if (portParameterReference instanceof PortDefinitionReference) {
/* 227 */         PortDefinitionReference pdr = (PortDefinitionReference)portParameterReference;
/* 228 */         portName = pdr.getTarget().getName();
/* 229 */       } else if (portParameterReference instanceof PortParameterReference) {
/* 230 */         PortParameterReference ppr = portParameterReference;
/* 231 */         portName = ppr.getTarget().getName();
/*     */       } 
/*     */       
/* 234 */       return this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName(portName), (CExpression)this.cBuilder.createSimpleName(dp.getName()));
/*     */     } 
/*     */     
/* 237 */     if (expr instanceof ArrayNavigationExpression) {
/* 238 */       ArrayNavigationExpression ane = (ArrayNavigationExpression)expr;
/* 239 */       DataReference dataReference = ane.getNavigated();
/* 240 */       Expression index = ane.getIndex();
/* 241 */       return this.cBuilder.createIndexed(generateExpr((Expression)dataReference), generateExpr(index));
/* 242 */     }  if (expr instanceof IndexLiteral) {
/* 243 */       IndexLiteral indL = (IndexLiteral)expr;
/* 244 */       return (CExpression)this.cBuilder.createSimpleName("BIPind" + indL.getId());
/*     */     } 
/* 246 */     if (expr instanceof InnerDataParameterReference) {
/* 247 */       InnerDataParameterReference idpr = (InnerDataParameterReference)expr;
/* 248 */       DataParameter dp = idpr.getTargetParameter();
/* 249 */       InnerPortReference ipr = idpr.getPortReference();
/* 250 */       CExpression partExp = generatePartExp(ipr.getTargetInstance());
/* 251 */       CExpression portExp = this.cBuilder.createPointed(partExp, (CExpression)this.cBuilder.createFuncCall("get_" + ipr.getTargetPort().getName()));
/*     */       
/* 253 */       return this.cBuilder.createPointed(portExp, (CExpression)this.cBuilder.createSimpleName(dp.getName()));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     return null;
/*     */   }
/*     */   
/*     */   CExpression generatePartExp(PartElementReference per) {
/* 263 */     CExpression exp = null;
/* 264 */     Part p = per.getTargetPart();
/* 265 */     if (p instanceof Component) {
/* 266 */       Component comp = (Component)p;
/* 267 */       exp = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("mHolder"), (CExpression)this.cBuilder.createSimpleName(comp.getName()));
/*     */     }
/* 269 */     else if (p instanceof Connector) {
/* 270 */       Connector cnx = (Connector)p;
/* 271 */       exp = this.cBuilder.createPointed((CExpression)this.cBuilder.createSimpleName("mHolder"), (CExpression)this.cBuilder.createSimpleName(cnx.getName()));
/*     */     } 
/*     */     
/* 274 */     for (Iterator<Expression> i = per.getIndex().iterator(); i.hasNext(); ) {
/* 275 */       Expression iExp = i.next();
/* 276 */       exp = this.cBuilder.createIndexed(exp, generateExpr(iExp));
/*     */     } 
/*     */     
/* 279 */     return exp;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxExpressionGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */