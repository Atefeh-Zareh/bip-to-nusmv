/*     */ package ujf.verimag.bip.parser.actions;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.antlr.runtime.ANTLRFileStream;
/*     */ import org.antlr.runtime.CharStream;
/*     */ import org.antlr.runtime.CommonTokenStream;
/*     */ import org.antlr.runtime.TokenSource;
/*     */ import org.antlr.runtime.TokenStream;
/*     */ import org.antlr.runtime.tree.Tree;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResourceFactoryImpl;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.parser.BipCheckType;
/*     */ import ujf.verimag.bip.parser.BipLibraryReader;
/*     */ import ujf.verimag.bip.parser.BipScannSyntaxe;
/*     */ import ujf.verimag.bip.parser.BipTreeError;
/*     */ import ujf.verimag.bip.parser.ErrorMessage;
/*     */ import ujf.verimag.bip.parser.ParserException;
/*     */ import ujf.verimag.bip.parser.bipLexer;
/*     */ import ujf.verimag.bip.parser.bipParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Parser
/*     */ {
/*  36 */   static String currentLocale = null;
/*     */   
/*     */   static Module[] BIP_Model;
/*     */   
/*     */   public static Module[] parse(String args, List<String> includeDirectories, Map<String, String> libFullNames, ErrorMessage msg) {
/*  41 */     BIP_Model = null;
/*     */     
/*  43 */     if (args.length() > 0) {
/*     */ 
/*     */       
/*     */       try {
/*  47 */         BIP_Model = doFile(new File(args), args, includeDirectories, libFullNames, msg);
/*     */       }
/*  49 */       catch (FileNotFoundException e) {
/*  50 */         msg.sendErrorMessage(4, "file " + args + " not found", 0, 0, args);
/*     */       
/*     */       }
/*  53 */       catch (Exception e) {
/*  54 */         System.err.println("exception: " + e);
/*  55 */         e.printStackTrace(System.err);
/*     */       } 
/*     */     } else {
/*     */       
/*  59 */       System.err.println("Usage: java BipParser <directory name>");
/*     */     } 
/*     */     
/*  62 */     return BIP_Model;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Module getModel() {
/*  68 */     return BIP_Model[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Module[] doFile(File f, String args, List<String> includeDirectories, Map<String, String> libFullNames, ErrorMessage msg) throws Exception {
/*  76 */     Module[] models = null;
/*  77 */     if (f.isDirectory()) {
/*  78 */       int nbModel = 0;
/*  79 */       String[] files = f.list();
/*  80 */       Module[] localModel = new Module[files.length]; int i;
/*  81 */       for (i = 0; i < files.length; i++) {
/*     */         
/*  83 */         File inFile = new File(f, files[i]);
/*  84 */         Module[] dirModel = doFile(inFile, inFile.getAbsolutePath(), includeDirectories, libFullNames, msg);
/*  85 */         if (dirModel != null && dirModel.length > 0) {
/*  86 */           localModel[nbModel] = dirModel[0];
/*  87 */           nbModel++;
/*     */         } 
/*     */       } 
/*  90 */       if (nbModel > 0) models = new Module[nbModel]; 
/*  91 */       for (i = 0; i < nbModel; ) { models[i] = localModel[i]; i++; }
/*     */ 
/*     */     
/*  94 */     } else if ((f.getName().length() > 5 && f.getName().substring(f.getName().length() - 5).equals(".eBip")) || (f.getName().length() > 4 && f.getName().substring(f.getName().length() - 4).equals(".bip"))) {
/*     */ 
/*     */ 
/*     */       
/*  98 */       models = new Module[1];
/*  99 */       msg.sendErrorMessage(1, "Parsing file : " + f.getAbsolutePath(), 0, 0, f.getAbsolutePath());
/* 100 */       File parent = f.getParentFile();
/* 101 */       if (parent != null) {
/* 102 */         includeDirectories.add(0, parent.getAbsolutePath());
/*     */       }
/*     */       
/* 105 */       models[0] = parseFile((CharStream)new ANTLRFileStream(args), args, includeDirectories, libFullNames, msg);
/*     */     } 
/* 107 */     return models;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Module parseFile(CharStream s, String args, List<String> includeDirectories, Map<String, String> libFullNames, ErrorMessage msg) {
/* 115 */     URI fileURI = null;
/*     */     try {
/* 117 */       Module result = null;
/*     */ 
/*     */       
/* 120 */       bipLexer lexer = new bipLexer(s);
/*     */       
/* 122 */       CommonTokenStream tokens = new CommonTokenStream((TokenSource)lexer);
/*     */ 
/*     */       
/* 125 */       bipParser parser = new bipParser((TokenStream)tokens);
/*     */       
/* 127 */       parser.setErrorMessage(msg);
/* 128 */       parser.setFileName(args);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       bipParser.translation_unit_return r = parser.translation_unit();
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (parser.getNbErr() == 0) {
/* 138 */         Tree tree = (Tree)r.getTree();
/* 139 */         BipTreeError error = new BipTreeError(msg, tokens);
/* 140 */         BipLibraryReader libReader = new BipLibraryReader();
/* 141 */         libReader.setIncludeDirectories(includeDirectories);
/* 142 */         libReader.setLibFullName(libFullNames);
/* 143 */         BipScannSyntaxe scanner = new BipScannSyntaxe(error, libReader);
/* 144 */         result = scanner.scannModuleDefinition(tree, args);
/* 145 */         BipCheckType check = new BipCheckType(error);
/*     */ 
/*     */         
/* 148 */         if (msg.getErrorNumber() <= 0) {
/* 149 */           check.checkModel(result);
/*     */         }
/*     */ 
/*     */         
/* 153 */         if (msg.getErrorNumber() <= 0) {
/* 154 */           fileURI = URI.createFileURI(timedModelFileName(args));
/*     */           
/* 156 */           msg.sendErrorMessage(1, "Generating model : " + fileURI, 0, 0, args);
/* 157 */           Ecore2XMLResourceFactoryImpl ecore2XMLResourceFactoryImpl = new Ecore2XMLResourceFactoryImpl();
/* 158 */           Resource poResource = ecore2XMLResourceFactoryImpl.createResource(fileURI);
/*     */           
/* 160 */           poResource.getContents().add(result);
/*     */           
/* 162 */           poResource.save(null);
/*     */ 
/*     */ 
/*     */           
/* 166 */           fileURI = URI.createFileURI(modelFileName(args));
/*     */           
/* 168 */           msg.sendErrorMessage(1, "Generating model : " + fileURI, 0, 0, args);
/* 169 */           ecore2XMLResourceFactoryImpl = new Ecore2XMLResourceFactoryImpl();
/* 170 */           poResource = ecore2XMLResourceFactoryImpl.createResource(fileURI);
/*     */           
/* 172 */           poResource.getContents().add(result);
/*     */           
/* 174 */           poResource.save(null);
/* 175 */           return result;
/*     */         } 
/*     */         
/* 178 */         return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 193 */     catch (FileNotFoundException e) {
/* 194 */       msg.sendErrorMessage(4, "no write access on file " + fileURI, 0, 0, args);
/* 195 */       return null;
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       if (!ParserException.class.isInstance(e)) {
/* 199 */         System.err.println(e);
/* 200 */         e.printStackTrace(System.err);
/*     */       } 
/*     */       
/* 203 */       return null;
/*     */     } 
/* 205 */     return null;
/*     */   }
/*     */   
/*     */   public static String modelFileName(String sourceFileName) throws IOException {
/* 209 */     String name = (new File(sourceFileName)).getName();
/* 210 */     return name.substring(0, name.length() - 4) + ".model";
/*     */   }
/*     */   public static String timedModelFileName(String sourceFileName) throws IOException {
/* 213 */     String name = (new File(sourceFileName)).getName();
/* 214 */     return name.substring(0, name.length() - 4) + ".timod";
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
/*     */ 
/*     */   
/*     */   public static Module readModel(String modelName, List<String> includeDirectories, Map<String, String> libFullNames) {
/* 236 */     BipLibraryReader libReader = new BipLibraryReader();
/* 237 */     libReader.setIncludeDirectories(includeDirectories);
/* 238 */     libReader.setLibFullName(libFullNames);
/*     */     try {
/* 240 */       return libReader.readModel(modelName);
/*     */     }
/* 242 */     catch (Exception e) {
/* 243 */       e.printStackTrace();
/*     */       
/* 245 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\actions\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */