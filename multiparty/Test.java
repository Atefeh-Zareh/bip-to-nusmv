/*     */ package multiparty;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import trans.CmdLineError;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ import ujf.verimag.bip.parser.ErrorMessage;
/*     */ import ujf.verimag.bip.parser.actions.Parser;
/*     */ 
/*     */ public class Test {
/*  24 */   private static Root top = null;
/*     */ 
/*     */   
/*  27 */   private static List<String> LComponent = new LinkedList<String>();
/*  28 */   private static List<List<String>> LLPort = new LinkedList<List<String>>();
/*  29 */   private static List<List<Integer>> LLTypePort = new LinkedList<List<Integer>>();
/*     */ 
/*     */   
/*     */   private static void InitializeTypePort(CompoundType CT) {
/*  33 */     for (Object o : CT.getSubcomponent()) {
/*     */       
/*  35 */       Component comp = (Component)o;
/*  36 */       LComponent.add(comp.getName());
/*  37 */       AtomType at = (AtomType)comp.getType();
/*  38 */       List<Integer> LTypePortDefault = new LinkedList<Integer>();
/*  39 */       List<String> LPort = new LinkedList<String>();
/*  40 */       for (Object o1 : at.getPortDefinition()) {
/*     */         
/*  42 */         PortDefinition PD = (PortDefinition)o1;
/*  43 */         LTypePortDefault.add(Integer.valueOf(0));
/*  44 */         LPort.add(PD.getName());
/*     */       } 
/*  46 */       LLPort.add(LPort);
/*  47 */       LLTypePort.add(LTypePortDefault);
/*     */     } 
/*     */     
/*  50 */     for (Object o : CT.getConnector()) {
/*     */       
/*  52 */       Connector conn = (Connector)o;
/*  53 */       InnerPortReference IPRSend = (InnerPortReference)conn.getActualPort().get(0);
/*  54 */       InnerPortReference IPRRec = (InnerPortReference)conn.getActualPort().get(1);
/*     */       
/*  56 */       String compName1 = IPRSend.getTargetInstance().getTargetPart().getName();
/*  57 */       String compName2 = IPRRec.getTargetInstance().getTargetPart().getName();
/*     */       
/*  59 */       String PortName1 = ((DefinitionBinding)IPRSend.getTargetPort().getBinding()).getDefinition().getName();
/*  60 */       String PortName2 = ((DefinitionBinding)IPRRec.getTargetPort().getBinding()).getDefinition().getName();
/*     */       
/*  62 */       int indexcompName1 = LComponent.indexOf(compName1);
/*  63 */       int indexcompName2 = LComponent.indexOf(compName2);
/*     */       
/*  65 */       int indexPort1 = ((List)LLPort.get(indexcompName1)).indexOf(PortName1);
/*  66 */       int indexPort2 = ((List)LLPort.get(indexcompName2)).indexOf(PortName2);
/*     */       
/*  68 */       ((List<Integer>)LLTypePort.get(indexcompName1)).set(indexPort1, Integer.valueOf(2));
/*  69 */       ((List<Integer>)LLTypePort.get(indexcompName2)).set(indexPort2, Integer.valueOf(1));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Integer getTypePort(String CompName, String PortName) {
/*     */     try {
/*  79 */       int index1 = LComponent.indexOf(CompName);
/*  80 */       int index2 = ((List)LLPort.get(index1)).indexOf(PortName);
/*  81 */       return ((List<Integer>)LLTypePort.get(index1)).get(index2);
/*     */     }
/*  83 */     catch (Exception e) {
/*     */       
/*  85 */       System.out.println("Error Port does not exit");
/*  86 */       return Integer.valueOf(-1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] arg) throws IOException {
/*  93 */     CmdLineError err = new CmdLineError();
/*     */     
/*  95 */     ArrayList<String> includeDirectories = new ArrayList();
/*  96 */     Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/*  97 */     String includeDir = "";
/*  98 */     includeDirectories.add(String.valueOf(includeDir) + "/");
/*  99 */     Module[] bipModel = Parser.parse("/Users/mohamad/Documents/workspace/Transformations/src/tmp/adist.bip", includeDirectories, libFullNames, (ErrorMessage)err);
/*     */     
/* 101 */     TransformationFunction.Initialize(bipModel[0]);
/*     */     
/* 103 */     System sys = (System)bipModel[0];
/* 104 */     top = sys.getRoot();
/* 105 */     CompoundType CT = (CompoundType)top.getType();
/* 106 */     Connector c = (Connector)CT.getConnector().get(0);
/* 107 */     System.out.println(c.getType().getName());
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
/* 119 */     InitializeTypePort(CT);
/* 120 */     System.out.println(getTypePort("producer_SR", "a"));
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\multiparty\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */