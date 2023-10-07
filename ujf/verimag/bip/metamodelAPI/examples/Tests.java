/*    */ package ujf.verimag.bip.metamodelAPI.examples;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*    */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*    */ import ujf.verimag.bip.Core.Interactions.Component;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*    */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.Core.Modules.Package;
/*    */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*    */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*    */ import ujf.verimag.bip.bip2src.Reverse;
/*    */ import ujf.verimag.bip.metamodelAPI.BipCreator;
/*    */ 
/*    */ public class Tests {
/*    */   public static Package createCompoundConnectorType() {
/* 23 */     Package model = BipCreator.createPackage("test");
/* 24 */     CompoundType ct = BipCreator.createCompoundType("comptype", (Module)model);
/*    */     
/* 26 */     CompoundType ctsub = BipCreator.createCompoundType("compsubtype", (Module)model);
/*    */     
/* 28 */     Component c1 = BipCreator.createComponentInstance("instance1", ct, (ComponentType)ctsub);
/* 29 */     Component c2 = BipCreator.createComponentInstance("instance2", ct, (ComponentType)ctsub);
/*    */     
/* 31 */     PortType pt = BipCreator.createPortType("porttype", (Module)model);
/*    */ 
/*    */     
/* 34 */     ConnectorType conntype = BipCreator.createConnectorType((Module)model, "conntype");
/* 35 */     PortParameter pp1 = BipCreator.createPortParameter("pouet1", pt, conntype);
/* 36 */     PortParameter pp2 = BipCreator.createPortParameter("pouet2", pt, conntype);
/*    */     
/* 38 */     List<PortParameter> trigs = new ArrayList<PortParameter>();
/* 39 */     List<PortParameter> synchs = new ArrayList<PortParameter>();
/* 40 */     trigs.add(pp1);
/* 41 */     synchs.add(pp2);
/*    */     
/* 43 */     ACFusion aCFusion = BipCreator.createPortExpressionFusion(synchs, trigs);
/* 44 */     conntype.setDefinition((PortExpression)aCFusion);
/*    */     
/* 46 */     return model;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws UnsupportedEncodingException {
/* 54 */     Package model = createCompoundConnectorType();
/* 55 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 56 */     PrintStream ps = new PrintStream(baos);
/* 57 */     Reverse r = new Reverse(ps);
/* 58 */     r.decompile((Module)model);
/* 59 */     System.out.println(baos.toString("UTF-8"));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\metamodelAPI\examples\Tests.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */