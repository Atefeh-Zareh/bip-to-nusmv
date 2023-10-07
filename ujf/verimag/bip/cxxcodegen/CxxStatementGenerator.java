/*    */ package ujf.verimag.bip.cxxcodegen;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*    */ import ujf.verimag.bip.Core.Behaviors.Action;
/*    */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*    */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*    */ import ujf.verimag.bip.cgeneration.CConstruct;
/*    */ import ujf.verimag.bip.cmodel.CAssignStm;
/*    */ import ujf.verimag.bip.cmodel.CAssignType;
/*    */ import ujf.verimag.bip.cmodel.CBlockStm;
/*    */ import ujf.verimag.bip.cmodel.CIfStm;
/*    */ import ujf.verimag.bip.cmodel.CStm;
/*    */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*    */ 
/*    */ public class CxxStatementGenerator
/*    */ {
/*    */   private CConstruct cBuilder;
/*    */   CxxExpressionGenerator expGen;
/* 25 */   protected CmodelFactory cFactory = CmodelFactory.eINSTANCE;
/*    */ 
/*    */   
/*    */   public CxxStatementGenerator(CConstruct builder, CxxExpressionGenerator expGen) {
/* 29 */     this.cBuilder = builder;
/* 30 */     this.expGen = expGen;
/*    */   }
/*    */   
/*    */   public CStm generateStm(Action a) {
/* 34 */     if (a == null)
/* 35 */       return null; 
/* 36 */     if (a instanceof CompositeAction) {
/* 37 */       CompositeAction ca = (CompositeAction)a;
/* 38 */       CBlockStm b = this.cFactory.createCBlockStm();
/* 39 */       for (Iterator<Action> i = ca.getContent().iterator(); i.hasNext(); ) {
/* 40 */         Action containedA = i.next();
/* 41 */         CStm s = generateStm(containedA);
/* 42 */         if (s != null) b.getContent().add(s); 
/*    */       } 
/* 44 */       return (CStm)b;
/*    */     } 
/* 46 */     if (a instanceof IfAction) {
/* 47 */       IfAction ia = (IfAction)a;
/* 48 */       CIfStm ci = this.cBuilder.createIf(this.expGen.generateExpr(ia.getCondition()), generateStm(ia.getIfCase()), generateStm(ia.getElseCase()));
/*    */       
/* 50 */       return (CStm)ci;
/*    */     } 
/*    */     
/* 53 */     if (a instanceof AssignmentAction) {
/* 54 */       AssignmentAction aa = (AssignmentAction)a;
/* 55 */       CAssignStm ca = this.cBuilder.createAssign(this.expGen.generateExpr((Expression)aa.getAssignedTarget()), this.expGen.generateExpr(aa.getAssignedValue()), BIP2CModelAssignType(aa.getType()));
/*    */ 
/*    */       
/* 58 */       return (CStm)ca;
/*    */     } 
/*    */     
/* 61 */     if (a instanceof UnaryExpression) {
/* 62 */       UnaryExpression unaryExpr = (UnaryExpression)a;
/* 63 */       return (CStm)this.expGen.generateExpr((Expression)unaryExpr);
/*    */     } 
/* 65 */     if (a instanceof FunctionCallExpression) {
/* 66 */       FunctionCallExpression fce = (FunctionCallExpression)a;
/* 67 */       return (CStm)this.expGen.generateExpr((Expression)fce);
/*    */     } 
/* 69 */     if (a instanceof OpaqueElement) {
/* 70 */       OpaqueElement oe = (OpaqueElement)a;
/* 71 */       return (CStm)this.cBuilder.createCCode(oe.getBody());
/*    */     } 
/*    */     
/* 74 */     System.out.println("should not append");
/*    */ 
/*    */     
/* 77 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CAssignType BIP2CModelAssignType(AssignType type) {
/* 86 */     CAssignType cAssigntype = null;
/* 87 */     switch (type.getValue()) { case 0:
/* 88 */         cAssigntype = CAssignType.ASSIGN; break;
/* 89 */       case 1: cAssigntype = CAssignType.PLUS_ASSIGN; break;
/* 90 */       case 2: cAssigntype = CAssignType.MINUS_ASSIGN; break;
/* 91 */       case 3: cAssigntype = CAssignType.MULT_ASSIGN; break;
/* 92 */       case 4: cAssigntype = CAssignType.DIV_ASSIGN; break;
/* 93 */       case 5: cAssigntype = CAssignType.MOD_ASSIGN; break; }
/*    */     
/* 95 */     return cAssigntype;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxStatementGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */