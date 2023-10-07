/*     */ package trans;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.bip2src.Reverse;
/*     */ import ujf.verimag.bip.parser.actions.Parser;
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
/*     */ public class Test
/*     */ {
/*     */   public static void main(String[] arg) throws FileNotFoundException {
/*  32 */     Date date = new Date();
/*  33 */     long startTime = date.getTime();
/*     */     
/*  35 */     CmdLineError err = new CmdLineError();
/*     */     
/*  37 */     ArrayList<String> includeDirectories = new ArrayList();
/*  38 */     Map<Object, Object> libFullNames = new HashMap<Object, Object>();
/*  39 */     String includeDir = "/";
/*  40 */     includeDirectories.add(String.valueOf(includeDir) + "/");
/*  41 */     Module[] bipModel = Parser.parse("/Users/mohamad/Documents/workspace/Transformations/src/tmp/a.bip", includeDirectories, libFullNames, err);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     FlatBip2.Flat(bipModel[0], 2);
/*  51 */     CreateBIPFile("/Users/mohamad/Documents/workspace/Transformations/src/tmp/a.bip", bipModel[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     date = new Date();
/*  57 */     long endTime = date.getTime();
/*  58 */     long diff = endTime - startTime;
/*  59 */     int stepmin = (int)Math.floor((diff / 60000L));
/*  60 */     int stepsec = (int)Math.floor(Math.floor((diff / 1000L)) % 60.0D);
/*  61 */     int stepmil = (int)(diff - ((stepmin * 60 + stepsec) * 1000));
/*  62 */     if (stepmil >= 500) stepsec++; 
/*  63 */     System.out.println("Time = " + stepmin + ":" + stepsec);
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
/*     */ 
/*     */ 
/*     */   
/*     */   private static void CreateBIPFile(String FileName, Module BIPSystem) throws FileNotFoundException {
/* 144 */     FileOutputStream out = new FileOutputStream(FileName);
/* 145 */     PrintStream X = new PrintStream(out);
/* 146 */     Reverse a = new Reverse(X);
/* 147 */     a.decompile(BIPSystem);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\trans\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */