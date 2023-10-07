/*    */ package trans;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import org.eclipse.emf.common.util.EList;
/*    */ import org.eclipse.emf.ecore.EObject;
/*    */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*    */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*    */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*    */ import ujf.verimag.bip.Core.Interactions.Component;
/*    */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*    */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ import ujf.verimag.bip.Core.Modules.Root;
/*    */ import ujf.verimag.bip.Core.Modules.System;
/*    */ 
/*    */ public class FlatBip2 {
/* 17 */   private static Root top = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static void Flat(Module M, int i) {
/* 24 */     Init ini = null;
/* 25 */     if (i == 2) {
/* 26 */       ini = new Init(M, false);
/*    */     } else {
/* 28 */       ini = new Init(M, true);
/*    */     } 
/* 30 */     System sys = (System)Init.M;
/* 31 */     top = sys.getRoot();
/* 32 */     CompoundType CT = (CompoundType)top.getType();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     TCompoundType TcT = new TCompoundType(CT);
/*    */     
/* 39 */     if (i == 1)
/*    */     {
/* 41 */       TcT.flatCompoundType();
/*    */     }
/* 43 */     if (i == 2) {
/*    */       
/* 45 */       TcT.flatCompoundType();
/* 46 */       TcT.flatConnectors(Init.M);
/* 47 */       if (!TCompoundType.TEST) {
/* 48 */         System.out.println("Error Data Transfer");
/*    */       }
/*    */     } 
/*    */     
/* 52 */     if (i == 3) {
/*    */       
/* 54 */       TcT.flatCompoundType();
/* 55 */       TcT.flatConnectors(Init.M);
/* 56 */       if (!TCompoundType.TEST) {
/* 57 */         System.out.println("Error Data Transfer");
/*    */       }
/*    */       else {
/*    */         
/* 61 */         List<DataParameter> DP = new LinkedList();
/* 62 */         Component ComponentTop = InteractionsFactory.eINSTANCE.createComponent();
/* 63 */         for (Object o : CT.getSubcomponent()) {
/*    */           
/* 65 */           Component comp = (Component)o;
/* 66 */           EList eList = comp.getType().getDataParameter();
/* 67 */           for (Object o1 : eList) {
/*    */             
/* 69 */             DataParameter dp = (DataParameter)EcoreUtil.copy((EObject)o1);
/* 70 */             dp.setName(String.valueOf(comp.getName()) + "_" + dp.getName());
/* 71 */             DP.add(dp);
/* 72 */             int index = eList.indexOf(o1);
/* 73 */             ComponentTop.getActualData().add(EcoreUtil.copy((EObject)comp.getActualData().get(index)));
/*    */           } 
/*    */         } 
/* 76 */         CompoundType CompTypeTOP = InteractionsFactory.eINSTANCE.createCompoundType();
/* 77 */         ComponentTop.setName("top");
/* 78 */         CompTypeTOP.getSubcomponent().add(ComponentTop);
/* 79 */         CompTypeTOP.setName("FLATBIP2TOP");
/* 80 */         top.setType((ComponentType)CompTypeTOP);
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 85 */         TAtomType TAT = new TAtomType(CT, DP);
/* 86 */         ComponentTop.setType((ComponentType)TAT.AT);
/* 87 */         M.getBipType().add(TAT.AT);
/* 88 */         M.getBipType().add(CompTypeTOP);
/*    */ 
/*    */ 
/*    */         
/* 92 */         System.out.println("3 -- flat atomic components done !");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\FlatBip2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */