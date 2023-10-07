/*     */ package ujf.verimag.bip.parser;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.load.LoadModel;
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
/*     */ public class BipLibraryReader
/*     */ {
/*  28 */   private static String extension = ".model";
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
/*  39 */   private List<String> prefix = new ArrayList<String>();
/*  40 */   private LoadModel loader = new LoadModel();
/*  41 */   private Map<String, Package> loadedLibs = new HashMap<String, Package>();
/*     */ 
/*     */   
/*     */   private Map<String, String> loadedLibsLocation;
/*     */ 
/*     */ 
/*     */   
/*     */   public void addIncludeDirectory(String dirName) {
/*  49 */     this.prefix.add(dirName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIncludeDirectories(List<String> includes) {
/*  57 */     this.prefix = includes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Package SearchForImportedLibrary(String libName) {
/*  66 */     Package p = null;
/*     */     
/*  68 */     p = this.loadedLibs.get(libName);
/*  69 */     if (p == null)
/*     */     {
/*  71 */       for (String lprefix : this.prefix) {
/*  72 */         if (lprefix.charAt(lprefix.length() - 1) != File.separatorChar) {
/*  73 */           lprefix = lprefix + File.separator;
/*     */         }
/*  75 */         String libFullName = lprefix + libName + extension;
/*  76 */         File f = new File(libFullName);
/*     */         
/*     */         try {
/*  79 */           libFullName = f.getCanonicalPath();
/*  80 */           Module m = this.loader.readModel(libFullName);
/*  81 */           if (m instanceof Package) {
/*  82 */             p = (Package)m;
/*     */             
/*  84 */             this.loadedLibsLocation.put(libName, lprefix + libName);
/*     */             
/*  86 */             this.loadedLibs.put(libName, p);
/*     */             
/*  88 */             addUsedLibs(p);
/*     */             
/*  90 */             return p;
/*     */           }
/*     */         
/*  93 */         } catch (Exception e) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  98 */     return p;
/*     */   }
/*     */   
/*     */   public Module readModel(String modelName) {
/*     */     try {
/* 103 */       Module m = this.loader.readModel(modelName);
/* 104 */       return m;
/*     */     }
/* 106 */     catch (Exception e) {
/*     */ 
/*     */       
/* 109 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addUsedLibs(Package p) {
/* 118 */     for (Package up : p.getUsedPackage())
/*     */     {
/*     */       
/* 121 */       SearchForImportedLibrary(up.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   public void addLibrary(String libName, Package p) {
/* 126 */     this.loadedLibs.put(libName, p);
/*     */   }
/*     */   
/*     */   public void setLibFullName(Map<String, String> libFullNames) {
/* 130 */     this.loadedLibsLocation = libFullNames;
/*     */   }
/*     */   
/*     */   public Map<String, String> getLibFullName() {
/* 134 */     return this.loadedLibsLocation;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\BipLibraryReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */