/*     */ package trans;
/*     */ 
/*     */ import BIP2BIP.FlatBip2;
/*     */ import BIP2BIP.Init;
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ 
/*     */ 
/*     */ public class TestMainReconstruction
/*     */ {
/*     */   public static void main(String[] arg) throws FileNotFoundException {
/*  16 */     String inputfile = "/home/jaber/work/projects/kalray/examples/nsa/tri_flat_sr.bip";
/*  17 */     String outputfile = "/home/jaber/work/projects/kalray/examples/nsa/16/tri16.bip";
/*     */     
/*  19 */     CompoundType CT = TransformationFunction.ParseBIPFile(inputfile);
/*     */ 
/*     */ 
/*     */     
/*  23 */     OptimizedReconstruction OR = new OptimizedReconstruction(CT, CreateTreePartition());
/*     */     
/*  25 */     TransformationFunction.Initialize(CT.getModule());
/*  26 */     Init.Initialize(CT.getModule(), TransformationFunction.PTSyn);
/*     */ 
/*     */ 
/*     */     
/*  30 */     for (Object o : CT.getSubcomponent()) {
/*     */       
/*  32 */       Component component = (Component)o;
/*  33 */       FlatBip2.Flat(component.getName(), CT);
/*     */     } 
/*     */ 
/*     */     
/*  37 */     TransformationFunction.CreateBIPFile(outputfile, CT.getModule());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List<List> CreateTreePartition() {
/*  44 */     List<List> Tree = new LinkedList<List>();
/*  45 */     List<String> Partition1 = new LinkedList<String>();
/*  46 */     List<String> Partition2 = new LinkedList<String>();
/*  47 */     List<String> Partition3 = new LinkedList<String>();
/*  48 */     List<String> Partition4 = new LinkedList<String>();
/*  49 */     List<String> Partition5 = new LinkedList<String>();
/*  50 */     List<String> Partition6 = new LinkedList<String>();
/*  51 */     List<String> Partition7 = new LinkedList<String>();
/*  52 */     List<String> Partition8 = new LinkedList<String>();
/*  53 */     List<String> Partition9 = new LinkedList<String>();
/*  54 */     List<String> Partition10 = new LinkedList<String>();
/*  55 */     List<String> Partition11 = new LinkedList<String>();
/*  56 */     List<String> Partition12 = new LinkedList<String>();
/*  57 */     List<String> Partition13 = new LinkedList<String>();
/*  58 */     List<String> Partition14 = new LinkedList<String>();
/*  59 */     List<String> Partition15 = new LinkedList<String>();
/*  60 */     List<String> Partition16 = new LinkedList<String>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     Partition1.add("dggd_dist");
/*  66 */     Partition1.add("dggg_dist");
/*  67 */     Partition2.add("dgdd_dist");
/*  68 */     Partition3.add("dgdg_dist");
/*  69 */     Partition4.add("ddgd_dist");
/*  70 */     Partition5.add("ddgg_dist");
/*  71 */     Partition6.add("dddd_dist");
/*  72 */     Partition7.add("dddg_dist");
/*  73 */     Partition8.add("gggd_dist");
/*  74 */     Partition9.add("gggg_dist");
/*  75 */     Partition10.add("ggdd_dist");
/*  76 */     Partition11.add("ggdg_dist");
/*  77 */     Partition12.add("gdgd_dist");
/*  78 */     Partition13.add("gdgg_dist");
/*  79 */     Partition14.add("gddd_dist");
/*  80 */     Partition15.add("gddg_dist");
/*  81 */     Partition16.add("Engine0_I");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     Tree.add(Partition1);
/*  92 */     Tree.add(Partition2);
/*  93 */     Tree.add(Partition3);
/*  94 */     Tree.add(Partition4);
/*  95 */     Tree.add(Partition5);
/*  96 */     Tree.add(Partition6);
/*  97 */     Tree.add(Partition7);
/*  98 */     Tree.add(Partition8);
/*  99 */     Tree.add(Partition9);
/* 100 */     Tree.add(Partition10);
/* 101 */     Tree.add(Partition11);
/* 102 */     Tree.add(Partition12);
/* 103 */     Tree.add(Partition13);
/* 104 */     Tree.add(Partition14);
/* 105 */     Tree.add(Partition15);
/* 106 */     Tree.add(Partition16);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     return Tree;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\TestMainReconstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */