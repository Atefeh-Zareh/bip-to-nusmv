/*     */ package ujf.verimag.bip.Core.Modules.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesFactory;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
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
/*     */ public class ModulesFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements ModulesFactory
/*     */ {
/*     */   public static ModulesFactory init() {
/*     */     try {
/*  40 */       ModulesFactory theModulesFactory = (ModulesFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/Modules.ecore");
/*  41 */       if (theModulesFactory != null)
/*     */       {
/*  43 */         return theModulesFactory;
/*     */       }
/*     */     }
/*  46 */     catch (Exception exception) {
/*     */       
/*  48 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  50 */     return new ModulesFactoryImpl();
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
/*     */   public EObject create(EClass eClass) {
/*  72 */     switch (eClass.getClassifierID()) {
/*     */       case 2:
/*  74 */         return (EObject)createPackage();
/*  75 */       case 3: return (EObject)createSystem();
/*  76 */       case 4: return (EObject)createRoot();
/*  77 */       case 5: return (EObject)createOpaqueElement();
/*     */     } 
/*  79 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Package createPackage() {
/*  90 */     PackageImpl package_ = new PackageImpl();
/*  91 */     return package_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public System createSystem() {
/* 101 */     SystemImpl system = new SystemImpl();
/* 102 */     return system;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Root createRoot() {
/* 112 */     RootImpl root = new RootImpl();
/* 113 */     return root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OpaqueElement createOpaqueElement() {
/* 123 */     OpaqueElementImpl opaqueElement = new OpaqueElementImpl();
/* 124 */     return opaqueElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModulesPackage getModulesPackage() {
/* 134 */     return (ModulesPackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static ModulesPackage getPackage() {
/* 146 */     return ModulesPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\impl\ModulesFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */