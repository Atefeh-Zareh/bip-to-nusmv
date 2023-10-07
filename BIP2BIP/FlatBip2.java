/*     */ package BIP2BIP;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ import ujf.verimag.bip.bip2src.Reverse;
/*     */ 
/*     */ public class FlatBip2 {
/*  23 */   private static Root top = null;
/*     */ 
/*     */   
/*     */   public static void Flat(String componentName, CompoundType CT) {
/*  27 */     System.out.println("---------------------------------------");
/*  28 */     Component component = getComponent(componentName, CT);
/*  29 */     if (component == null) {
/*     */       
/*  31 */       System.out.println("Component to flat does not exit");
/*  32 */       System.exit(0);
/*     */     }
/*  34 */     else if (component.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType) {
/*     */       
/*  36 */       System.out.println("Nothing to flat");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  41 */       CompoundType compoundType = (CompoundType)component.getType();
/*  42 */       CompoundType compoundTypeCopy = (CompoundType)EcoreUtil.copy((EObject)compoundType);
/*  43 */       compoundTypeCopy.setName(String.valueOf(compoundTypeCopy.getName()) + "_Flat_" + component.getName());
/*     */       
/*  45 */       List<DataParameter> LDataParamCT = new LinkedList<DataParameter>((Collection<? extends DataParameter>)compoundTypeCopy.getDataParameter());
/*  46 */       EList<EObject> eList = component.getActualData();
/*     */ 
/*     */       
/*  49 */       for (Object o : compoundTypeCopy.getSubcomponent()) {
/*     */         
/*  51 */         Component component_i = (Component)o;
/*  52 */         EList<EObject> eList1 = component_i.getActualData();
/*  53 */         for (EObject o1 : eList1) {
/*     */           
/*  55 */           if (o1 instanceof DataParameterReference) {
/*     */             
/*  57 */             DataParameterReference DPR = (DataParameterReference)o1;
/*  58 */             int indexi = LDataParamCT.indexOf(DPR.getTargetParameter());
/*  59 */             EObject EO = EcoreUtil.copy(eList.get(indexi));
/*  60 */             eList1.set(eList1.indexOf(DPR), EO); continue;
/*     */           } 
/*  62 */           if (o1 instanceof BinaryExpression) {
/*     */             
/*  64 */             BinaryExpression BE = (BinaryExpression)EcoreUtil.copy(o1);
/*  65 */             TCompoundType.ReplaceDataParameterExpression((Expression)BE, (List)eList1, LDataParamCT, (List)eList);
/*  66 */             eList1.set(eList1.indexOf(o1), BE);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  71 */       TCompoundType TcT = new TCompoundType(compoundTypeCopy);
/*     */       
/*  73 */       TcT.flatCompoundType();
/*  74 */       TcT.flatConnectors(Init.M);
/*     */ 
/*     */ 
/*     */       
/*  78 */       if (!TCompoundType.TEST) {
/*  79 */         System.out.println("Error Data Transfer");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  84 */         List<DataParameter> DP = new LinkedList<DataParameter>();
/*  85 */         component.getActualData().clear();
/*  86 */         for (Object o : compoundTypeCopy.getSubcomponent()) {
/*     */           
/*  88 */           Component comp = (Component)o;
/*  89 */           EList eList1 = comp.getType().getDataParameter();
/*  90 */           for (Object o1 : eList1) {
/*     */             
/*  92 */             DataParameter dp = (DataParameter)EcoreUtil.copy((EObject)o1);
/*  93 */             dp.setName(String.valueOf(comp.getName()) + "_" + dp.getName());
/*  94 */             DP.add(dp);
/*  95 */             int index = eList1.indexOf(o1);
/*     */             
/*  97 */             component.getActualData().add(EcoreUtil.copy((EObject)comp.getActualData().get(index)));
/*     */           } 
/*     */         } 
/*     */         
/* 101 */         TAtomType TAT = new TAtomType(compoundTypeCopy, DP);
/* 102 */         TAT.AT.setName(String.valueOf(component.getType().getName()) + "_Flat_" + component.getName());
/*     */         
/* 104 */         CT.getModule().getBipType().add(CT.getModule().getBipType().size() - 1, TAT.AT);
/* 105 */         component.setType((ComponentType)TAT.AT);
/* 106 */         component.setCompoundType(CT);
/*     */         
/* 108 */         System.out.println("3 -- flat atomic components done !");
/*     */       } 
/* 110 */       System.out.println("---------------------------------------");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void Flat(String componentName, String Path) {
/* 119 */     System sys = (System)Init.M;
/* 120 */     top = sys.getRoot();
/* 121 */     CompoundType CT = (CompoundType)top.getType();
/*     */     
/* 123 */     Component component = getComponent(componentName, CT);
/* 124 */     if (component == null) {
/*     */       
/* 126 */       System.out.println("Component to flat does not exit");
/* 127 */       System.exit(0);
/*     */     }
/* 129 */     else if (component.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType) {
/*     */       
/* 131 */       System.out.println("Nothing to flat");
/* 132 */       System.exit(0);
/*     */     }
/*     */     else {
/*     */       
/* 136 */       CompoundType compoundType = (CompoundType)component.getType();
/* 137 */       CompoundType compoundTypeCopy = (CompoundType)EcoreUtil.copy((EObject)compoundType);
/* 138 */       compoundTypeCopy.setName(String.valueOf(compoundTypeCopy.getName()) + "_Flat_" + component.getName());
/*     */       
/* 140 */       List<DataParameter> LDataParamCT = new LinkedList<DataParameter>((Collection<? extends DataParameter>)compoundTypeCopy.getDataParameter());
/* 141 */       EList<EObject> eList = component.getActualData();
/*     */ 
/*     */       
/* 144 */       for (Object o : compoundTypeCopy.getSubcomponent()) {
/*     */         
/* 146 */         Component component_i = (Component)o;
/* 147 */         EList<EObject> eList1 = component_i.getActualData();
/* 148 */         for (EObject o1 : eList1) {
/*     */           
/* 150 */           if (o1 instanceof DataParameterReference) {
/*     */             
/* 152 */             DataParameterReference DPR = (DataParameterReference)o1;
/* 153 */             int indexi = LDataParamCT.indexOf(DPR.getTargetParameter());
/* 154 */             EObject EO = EcoreUtil.copy(eList.get(indexi));
/* 155 */             eList1.set(eList1.indexOf(DPR), EO); continue;
/*     */           } 
/* 157 */           if (o1 instanceof BinaryExpression) {
/*     */             
/* 159 */             BinaryExpression BE = (BinaryExpression)EcoreUtil.copy(o1);
/* 160 */             TCompoundType.ReplaceDataParameterExpression((Expression)BE, (List)eList1, LDataParamCT, (List)eList);
/* 161 */             eList1.set(eList1.indexOf(o1), BE);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 166 */       TCompoundType TcT = new TCompoundType(compoundTypeCopy);
/*     */       
/* 168 */       TcT.flatCompoundType();
/* 169 */       TcT.flatConnectors(Init.M);
/*     */ 
/*     */ 
/*     */       
/* 173 */       if (!TCompoundType.TEST) {
/* 174 */         System.out.println("Error Data Transfer");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 179 */         List<DataParameter> DP = new LinkedList<DataParameter>();
/* 180 */         component.getActualData().clear();
/* 181 */         for (Object o : compoundTypeCopy.getSubcomponent()) {
/*     */           
/* 183 */           Component comp = (Component)o;
/* 184 */           EList eList1 = comp.getType().getDataParameter();
/* 185 */           for (Object o1 : eList1) {
/*     */             
/* 187 */             DataParameter dp = (DataParameter)EcoreUtil.copy((EObject)o1);
/* 188 */             dp.setName(String.valueOf(comp.getName()) + "_" + dp.getName());
/* 189 */             DP.add(dp);
/* 190 */             int index = eList1.indexOf(o1);
/*     */             
/* 192 */             component.getActualData().add(EcoreUtil.copy((EObject)comp.getActualData().get(index)));
/*     */           } 
/*     */         } 
/*     */         
/* 196 */         TAtomType TAT = new TAtomType(compoundTypeCopy, DP);
/* 197 */         TAT.AT.setName(String.valueOf(component.getType().getName()) + "_Flat_" + component.getName());
/*     */         
/* 199 */         Init.M.getBipType().add(Init.M.getBipType().size() - 2, TAT.AT);
/* 200 */         component.setType((ComponentType)TAT.AT);
/* 201 */         component.setCompoundType(CT);
/*     */         
/* 203 */         System.out.println("3 -- flat atomic components done !");
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 208 */         FileOutputStream out = new FileOutputStream(Path);
/* 209 */         PrintStream X = new PrintStream(out);
/* 210 */         Reverse a = new Reverse(X);
/* 211 */         a.decompile(Init.M);
/*     */       }
/* 213 */       catch (Exception e) {
/*     */         
/* 215 */         System.out.println("error decompile Model  " + e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Component getComponent(String componentName, CompoundType CT) {
/* 223 */     for (Object o : CT.getSubcomponent()) {
/*     */       
/* 225 */       Component comp = (Component)o;
/* 226 */       if (comp.getName().equals(componentName))
/* 227 */         return comp; 
/*     */     } 
/* 229 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIP2BIP\FlatBip2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */