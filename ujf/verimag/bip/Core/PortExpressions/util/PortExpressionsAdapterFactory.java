/*     */ package ujf.verimag.bip.Core.PortExpressions.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACNaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AINaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchro;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchroNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
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
/*     */ public class PortExpressionsAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static PortExpressionsPackage modelPackage;
/*     */   
/*     */   public PortExpressionsAdapterFactory() {
/*  44 */     if (modelPackage == null)
/*     */     {
/*  46 */       modelPackage = PortExpressionsPackage.eINSTANCE;
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
/*  61 */     if (object == modelPackage)
/*     */     {
/*  63 */       return true;
/*     */     }
/*  65 */     if (object instanceof EObject)
/*     */     {
/*  67 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected PortExpressionsSwitch<Adapter> modelSwitch = new PortExpressionsSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseACNaryExpression(ACNaryExpression object)
/*     */       {
/*  84 */         return PortExpressionsAdapterFactory.this.createACNaryExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACExpression(ACExpression object) {
/*  89 */         return PortExpressionsAdapterFactory.this.createACExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortExpression(PortExpression object) {
/*  94 */         return PortExpressionsAdapterFactory.this.createPortExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACFusionNeutral(ACFusionNeutral object) {
/*  99 */         return PortExpressionsAdapterFactory.this.createACFusionNeutralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACUnionNeutral(ACUnionNeutral object) {
/* 104 */         return PortExpressionsAdapterFactory.this.createACUnionNeutralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAINaryExpression(AINaryExpression object) {
/* 109 */         return PortExpressionsAdapterFactory.this.createAINaryExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAIExpression(AIExpression object) {
/* 114 */         return PortExpressionsAdapterFactory.this.createAIExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACFusion(ACFusion object) {
/* 119 */         return PortExpressionsAdapterFactory.this.createACFusionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAIUnionNeutral(AIUnionNeutral object) {
/* 124 */         return PortExpressionsAdapterFactory.this.createAIUnionNeutralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAISynchroNeutral(AISynchroNeutral object) {
/* 129 */         return PortExpressionsAdapterFactory.this.createAISynchroNeutralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAIUnion(AIUnion object) {
/* 134 */         return PortExpressionsAdapterFactory.this.createAIUnionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACTyping(ACTyping object) {
/* 139 */         return PortExpressionsAdapterFactory.this.createACTypingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAISynchro(AISynchro object) {
/* 144 */         return PortExpressionsAdapterFactory.this.createAISynchroAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACUnion(ACUnion object) {
/* 149 */         return PortExpressionsAdapterFactory.this.createACUnionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortReference(PortReference object) {
/* 154 */         return PortExpressionsAdapterFactory.this.createPortReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 159 */         return PortExpressionsAdapterFactory.this.createEObjectAdapter();
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
/* 174 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createACNaryExpressionAdapter() {
/* 190 */     return null;
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
/*     */   public Adapter createACExpressionAdapter() {
/* 205 */     return null;
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
/* 220 */     return null;
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
/*     */   public Adapter createACFusionNeutralAdapter() {
/* 235 */     return null;
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
/*     */   public Adapter createACUnionNeutralAdapter() {
/* 250 */     return null;
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
/*     */   public Adapter createAINaryExpressionAdapter() {
/* 265 */     return null;
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
/*     */   public Adapter createAIExpressionAdapter() {
/* 280 */     return null;
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
/*     */   public Adapter createACFusionAdapter() {
/* 295 */     return null;
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
/*     */   public Adapter createAIUnionNeutralAdapter() {
/* 310 */     return null;
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
/*     */   public Adapter createAISynchroNeutralAdapter() {
/* 325 */     return null;
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
/*     */   public Adapter createAIUnionAdapter() {
/* 340 */     return null;
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
/*     */   public Adapter createACTypingAdapter() {
/* 355 */     return null;
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
/*     */   public Adapter createAISynchroAdapter() {
/* 370 */     return null;
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
/*     */   public Adapter createACUnionAdapter() {
/* 385 */     return null;
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
/*     */   public Adapter createPortReferenceAdapter() {
/* 400 */     return null;
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
/* 413 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpression\\util\PortExpressionsAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */