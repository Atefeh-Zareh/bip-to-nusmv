/*     */ package ujf.verimag.bip.Core.Priorities.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ import ujf.verimag.bip.Core.Interactions.MultiplicityElement;
/*     */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrioritiesAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static PrioritiesPackage modelPackage;
/*     */   
/*     */   public PrioritiesAdapterFactory() {
/*  48 */     if (modelPackage == null)
/*     */     {
/*  50 */       modelPackage = PrioritiesPackage.eINSTANCE;
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
/*  65 */     if (object == modelPackage)
/*     */     {
/*  67 */       return true;
/*     */     }
/*  69 */     if (object instanceof EObject)
/*     */     {
/*  71 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   protected PrioritiesSwitch<Adapter> modelSwitch = new PrioritiesSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter casePriorityRule(PriorityRule object)
/*     */       {
/*  88 */         return PrioritiesAdapterFactory.this.createPriorityRuleAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseConnectorTypeReference(ConnectorTypeReference object) {
/*  93 */         return PrioritiesAdapterFactory.this.createConnectorTypeReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePriorityElement(PriorityElement object) {
/*  98 */         return PrioritiesAdapterFactory.this.createPriorityElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseNamedElement(NamedElement object) {
/* 103 */         return PrioritiesAdapterFactory.this.createNamedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseMultiplicityElement(MultiplicityElement object) {
/* 108 */         return PrioritiesAdapterFactory.this.createMultiplicityElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 113 */         return PrioritiesAdapterFactory.this.createEObjectAdapter();
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
/* 128 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createPriorityRuleAdapter() {
/* 144 */     return null;
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
/*     */   public Adapter createConnectorTypeReferenceAdapter() {
/* 159 */     return null;
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
/*     */   public Adapter createPriorityElementAdapter() {
/* 174 */     return null;
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
/* 189 */     return null;
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
/*     */   public Adapter createMultiplicityElementAdapter() {
/* 204 */     return null;
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
/* 217 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Prioritie\\util\PrioritiesAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */