/*     */ package ujf.verimag.bip.Extra.Contracts.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Extra.Contracts.Contract;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractBinding;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractState;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsFactory;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContractsFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements ContractsFactory
/*     */ {
/*     */   public static ContractsFactory init() {
/*     */     try {
/*  37 */       ContractsFactory theContractsFactory = (ContractsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Extra/Contracts.ecore");
/*  38 */       if (theContractsFactory != null)
/*     */       {
/*  40 */         return theContractsFactory;
/*     */       }
/*     */     }
/*  43 */     catch (Exception exception) {
/*     */       
/*  45 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  47 */     return new ContractsFactoryImpl();
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
/*  71 */         return (EObject)createContract();
/*  72 */       case 1: return (EObject)createContractState();
/*  73 */       case 2: return (EObject)createContractBinding();
/*     */     } 
/*  75 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Contract createContract() {
/*  86 */     ContractImpl contract = new ContractImpl();
/*  87 */     return contract;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractState createContractState() {
/*  97 */     ContractStateImpl contractState = new ContractStateImpl();
/*  98 */     return contractState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractBinding createContractBinding() {
/* 108 */     ContractBindingImpl contractBinding = new ContractBindingImpl();
/* 109 */     return contractBinding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContractsPackage getContractsPackage() {
/* 119 */     return (ContractsPackage)getEPackage();
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
/*     */   public static ContractsPackage getPackage() {
/* 131 */     return ContractsPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\impl\ContractsFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */