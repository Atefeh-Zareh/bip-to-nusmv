/*     */ package ujf.verimag.bip.Core.Modules.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
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
/*     */ public class ModulesAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static ModulesPackage modelPackage;
/*     */   
/*     */   public ModulesAdapterFactory() {
/*  57 */     if (modelPackage == null)
/*     */     {
/*  59 */       modelPackage = ModulesPackage.eINSTANCE;
/*     */     }
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
/*     */   public boolean isFactoryForType(Object object) {
/*  74 */     if (object == modelPackage)
/*     */     {
/*  76 */       return true;
/*     */     }
/*  78 */     if (object instanceof EObject)
/*     */     {
/*  80 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   protected ModulesSwitch<Adapter> modelSwitch = new ModulesSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseModule(Module object)
/*     */       {
/*  97 */         return ModulesAdapterFactory.this.createModuleAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDeclaration(Declaration object) {
/* 102 */         return ModulesAdapterFactory.this.createDeclarationAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePackage(Package object) {
/* 107 */         return ModulesAdapterFactory.this.createPackageAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseSystem(System object) {
/* 112 */         return ModulesAdapterFactory.this.createSystemAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseRoot(Root object) {
/* 117 */         return ModulesAdapterFactory.this.createRootAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseOpaqueElement(OpaqueElement object) {
/* 122 */         return ModulesAdapterFactory.this.createOpaqueElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseNamedElement(NamedElement object) {
/* 127 */         return ModulesAdapterFactory.this.createNamedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAction(Action object) {
/* 132 */         return ModulesAdapterFactory.this.createActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseExpression(Expression object) {
/* 137 */         return ModulesAdapterFactory.this.createExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataReference(DataReference object) {
/* 142 */         return ModulesAdapterFactory.this.createDataReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataType(DataType object) {
/* 147 */         return ModulesAdapterFactory.this.createDataTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortExpression(PortExpression object) {
/* 152 */         return ModulesAdapterFactory.this.createPortExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 157 */         return ModulesAdapterFactory.this.createEObjectAdapter();
/*     */       }
/*     */     };
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
/*     */   public Adapter createAdapter(Notifier target) {
/* 172 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createModuleAdapter() {
/* 188 */     return null;
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
/*     */   public Adapter createDeclarationAdapter() {
/* 203 */     return null;
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
/*     */   public Adapter createPackageAdapter() {
/* 218 */     return null;
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
/*     */   public Adapter createSystemAdapter() {
/* 233 */     return null;
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
/*     */   public Adapter createRootAdapter() {
/* 248 */     return null;
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
/*     */   public Adapter createOpaqueElementAdapter() {
/* 263 */     return null;
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
/*     */   public Adapter createNamedElementAdapter() {
/* 278 */     return null;
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
/*     */   public Adapter createActionAdapter() {
/* 293 */     return null;
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
/*     */   public Adapter createExpressionAdapter() {
/* 308 */     return null;
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
/*     */   public Adapter createDataReferenceAdapter() {
/* 323 */     return null;
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
/*     */   public Adapter createDataTypeAdapter() {
/* 338 */     return null;
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
/*     */   public Adapter createPortExpressionAdapter() {
/* 353 */     return null;
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
/*     */   public Adapter createEObjectAdapter() {
/* 366 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Module\\util\ModulesAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */