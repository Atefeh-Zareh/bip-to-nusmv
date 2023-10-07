/*     */ package ujf.verimag.bip.cmdline;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.bip2src.Reverse;
/*     */ import ujf.verimag.bip.cxxcodegen.CxxModuleGenerator;
/*     */ import ujf.verimag.bip.graphviz.GraphvizAutomaton;
/*     */ import ujf.verimag.bip.parser.actions.Parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CmdLine
/*     */ {
/*     */   public static void main(String[] args) throws IOException {
/*     */     Module[] bipModel;
/*  25 */     String inFile = null;
/*  26 */     String modelFile = null;
/*  27 */     String modelRootName = null;
/*  28 */     String reverseName = null;
/*  29 */     String top = null;
/*  30 */     ArrayList<String> includeDirectories = new ArrayList();
/*  31 */     Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/*  32 */     boolean help = false;
/*  33 */     boolean forTest = false;
/*  34 */     boolean debug = false;
/*  35 */     boolean genC = false;
/*  36 */     boolean verif = false;
/*  37 */     boolean error = false;
/*  38 */     boolean multiThread = false;
/*  39 */     String genGraphviz = null;
/*  40 */     String genGraphvizScript = null;
/*  41 */     int sysRes = 0;
/*  42 */     CmdLineError err = new CmdLineError();
/*  43 */     int i = 0;
/*     */ 
/*     */ 
/*     */     
/*  47 */     System.out.println("BIP Compiler. Version: d20101124");
/*     */     
/*  49 */     while (i < args.length) {
/*  50 */       if (args[i].charAt(0) == '-') {
/*     */         
/*  52 */         if (args[i].equals("-f") || args[i].equals("--file")) {
/*  53 */           i++;
/*  54 */           if (i < args.length) {
/*  55 */             inFile = args[i];
/*     */           }
/*  57 */         } else if (args[i].equals("-r") || args[i].equals("--reverse")) {
/*  58 */           i++;
/*  59 */           if (i < args.length) {
/*  60 */             reverseName = args[i];
/*     */           }
/*  62 */         } else if (args[i].equals("-m") || args[i].equals("--model")) {
/*  63 */           i++;
/*  64 */           if (i < args.length) {
/*  65 */             modelFile = args[i];
/*  66 */             modelRootName = (new File(modelFile)).getName();
/*     */           } 
/*  68 */         } else if (args[i].equals("--genC-top")) {
/*  69 */           i++;
/*  70 */           if (i < args.length) {
/*  71 */             top = args[i];
/*     */           }
/*  73 */         } else if (args[i].equals("-I")) {
/*  74 */           i++;
/*  75 */           if (i < args.length) {
/*  76 */             String includeDir = args[i];
/*  77 */             includeDirectories.add(includeDir);
/*     */           } 
/*  79 */         } else if (args[i].equals("-t")) {
/*  80 */           forTest = true;
/*  81 */         } else if (args[i].equals("-g")) {
/*  82 */           debug = true;
/*  83 */         } else if (args[i].equals("--genC-multi")) {
/*  84 */           genC = true;
/*  85 */           multiThread = true;
/*  86 */         } else if (args[i].equals("--genC-explore")) {
/*  87 */           genC = true; verif = true;
/*     */         }
/*  89 */         else if (args[i].equals("--genC-execute")) {
/*  90 */           genC = true; verif = false;
/*  91 */         } else if (args[i].equals("--gen-graphviz")) {
/*  92 */           i++;
/*  93 */           if (i < args.length) {
/*  94 */             genGraphviz = args[i];
/*     */           }
/*  96 */         } else if (args[i].equals("--gen-graphviz-script")) {
/*  97 */           i++;
/*  98 */           if (i < args.length) {
/*  99 */             genGraphvizScript = args[i];
/*     */           }
/* 101 */         } else if (args[i].equals("--help")) {
/* 102 */           help = true;
/*     */         } else {
/*     */           
/* 105 */           error = true;
/*     */         } 
/*     */       } else {
/*     */         
/* 109 */         error = true;
/*     */         break;
/*     */       } 
/* 112 */       i++;
/*     */     } 
/* 114 */     if (inFile == null && modelFile == null) {
/* 115 */       if (!help) System.out.println("Error: No input file specified"); 
/* 116 */       error = true;
/*     */     } 
/* 118 */     if (help || error) {
/* 119 */       System.out.println("Usage: " + args[0] + " -f <input> options \n" + "    or " + args[0] + " -m <model> options \n" + "   -I <include directory>\n" + "\t--genC-execute : generate code for execution\n" + "\t--genC-multi : generate code for multithread execution\n" + "    -g : generate debug functions\n" + "\t--genC-explore : generate code for state exploration \n" + "   --gen-graphviz <PREFIX> : generate graphiv file for automaton. Files prefixed by PREFIX\n" + "   --gen-graphviz-script <SCRIPT> : generate a script for dot->png conversion in <SCRIPT>\n");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (error) System.exit(1);
/*     */ 
/*     */     
/* 133 */     if (inFile != null) {
/* 134 */       bipModel = Parser.parse(inFile, includeDirectories, libFullNames, err);
/*     */     } else {
/* 136 */       bipModel = new Module[1];
/* 137 */       bipModel[0] = Parser.readModel(modelFile, includeDirectories, libFullNames);
/* 138 */       if (bipModel[0] == null) {
/* 139 */         System.err.println("no model read");
/* 140 */         System.exit(1);
/*     */       } else {
/* 142 */         System.out.println("model " + modelFile + " read");
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     if (bipModel != null) {
/* 147 */       for (int m = 0; m < bipModel.length; m++) {
/* 148 */         if (bipModel[m] == null) {
/* 149 */           System.out.println("\nParser exiting with " + err.getErrorNumber() + " error(s).\n");
/* 150 */           sysRes = 2;
/*     */         } else {
/*     */           
/* 153 */           if (inFile != null) {
/* 154 */             System.out.println("\nParser exiting with no errors.\n");
/*     */           }
/* 156 */           if (genC) {
/*     */             
/* 158 */             CxxModuleGenerator visitor = new CxxModuleGenerator(bipModel[m].getName(), libFullNames, includeDirectories);
/* 159 */             visitor.setDebug(debug);
/* 160 */             if (modelRootName != null) {
/* 161 */               visitor.visit(bipModel[m], top, verif, multiThread, forTest, modelRootName + ".bip");
/*     */             } else {
/* 163 */               visitor.visit(bipModel[m], top, verif, multiThread, forTest);
/*     */             } 
/* 165 */             System.out.println("C code generated");
/*     */           } 
/*     */           
/* 168 */           if (genGraphviz != null) {
/* 169 */             Map<String, String> graphviz = GraphvizAutomaton.fromBipModule(bipModel[m]);
/*     */             
/* 171 */             FileOutputStream script = null;
/* 172 */             if (genGraphvizScript != null) {
/* 173 */               script = new FileOutputStream(genGraphvizScript);
/* 174 */               script.write("#!/bin/bash\n".getBytes());
/*     */             } 
/*     */             
/* 177 */             for (Map.Entry<String, String> entry : graphviz.entrySet()) {
/* 178 */               String ofilename = genGraphviz + (String)entry.getKey() + ".dot";
/* 179 */               FileOutputStream outgraph = new FileOutputStream(ofilename);
/* 180 */               outgraph.write(((String)entry.getValue()).getBytes());
/* 181 */               outgraph.close();
/*     */               
/* 183 */               if (genGraphvizScript != null) {
/* 184 */                 script.write((new String("dot -Tpng " + ofilename + " > " + "$(dirname " + ofilename + ")/" + "$(basename " + ofilename + " .dot).png\n")).getBytes());
/*     */               }
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 190 */             if (genGraphvizScript != null) {
/* 191 */               script.close();
/*     */             }
/*     */           } 
/*     */           
/* 195 */           if (reverseName != null) {
/* 196 */             FileOutputStream outF = new FileOutputStream(reverseName);
/* 197 */             PrintStream outS = new PrintStream(outF);
/* 198 */             Reverse rev = new Reverse(outS);
/* 199 */             rev.decompile(bipModel[m]);
/* 200 */             outS.close();
/* 201 */             outF.close();
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 208 */       System.exit(2);
/*     */     } 
/* 210 */     System.exit(sysRes);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmdline\CmdLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */