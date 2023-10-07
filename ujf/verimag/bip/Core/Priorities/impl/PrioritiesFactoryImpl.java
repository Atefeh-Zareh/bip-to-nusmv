/*     */ package ujf.verimag.bip.Core.Priorities.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesFactory;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
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
/*     */ public class PrioritiesFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements PrioritiesFactory
/*     */ {
/*     */   public static PrioritiesFactory init() {
/*     */     try {
/*  37 */       PrioritiesFactory thePrioritiesFactory = (PrioritiesFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/Priorities.ecore");
/*  38 */       if (thePrioritiesFactory != null)
/*     */       {
/*  40 */         return thePrioritiesFactory;
/*     */       }
/*     */     }
/*  43 */     catch (Exception exception) {
/*     */       
/*  45 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  47 */     return new PrioritiesFactoryImpl();
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
/*  69 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  71 */         return (EObject)createPriorityRule();
/*  72 */       case 1: return (EObject)createConnectorTypeReference();
/*     */     } 
/*  74 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PriorityRule createPriorityRule() {
/*  85 */     PriorityRuleImpl priorityRule = new PriorityRuleImpl();
/*  86 */     return priorityRule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorTypeReference createConnectorTypeReference() {
/*  96 */     ConnectorTypeReferenceImpl connectorTypeReference = new ConnectorTypeReferenceImpl();
/*  97 */     return connectorTypeReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrioritiesPackage getPrioritiesPackage() {
/* 107 */     return (PrioritiesPackage)getEPackage();
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
/*     */   public static PrioritiesPackage getPackage() {
/* 119 */     return PrioritiesPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\impl\PrioritiesFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */