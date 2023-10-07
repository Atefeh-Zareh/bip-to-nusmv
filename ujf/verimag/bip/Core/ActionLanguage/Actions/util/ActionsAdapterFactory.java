/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
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
/*     */ public class ActionsAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static ActionsPackage modelPackage;
/*     */   
/*     */   public ActionsAdapterFactory() {
/*  46 */     if (modelPackage == null)
/*     */     {
/*  48 */       modelPackage = ActionsPackage.eINSTANCE;
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
/*  63 */     if (object == modelPackage)
/*     */     {
/*  65 */       return true;
/*     */     }
/*  67 */     if (object instanceof EObject)
/*     */     {
/*  69 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected ActionsSwitch<Adapter> modelSwitch = new ActionsSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseCompositeAction(CompositeAction object)
/*     */       {
/*  86 */         return ActionsAdapterFactory.this.createCompositeActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseIfAction(IfAction object) {
/*  91 */         return ActionsAdapterFactory.this.createIfActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAssignmentAction(AssignmentAction object) {
/*  96 */         return ActionsAdapterFactory.this.createAssignmentActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAction(Action object) {
/* 101 */         return ActionsAdapterFactory.this.createActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 106 */         return ActionsAdapterFactory.this.createEObjectAdapter();
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
/* 121 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createCompositeActionAdapter() {
/* 137 */     return null;
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
/*     */   public Adapter createIfActionAdapter() {
/* 152 */     return null;
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
/*     */   public Adapter createAssignmentActionAdapter() {
/* 167 */     return null;
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
/* 182 */     return null;
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
/* 195 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Action\\util\ActionsAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */