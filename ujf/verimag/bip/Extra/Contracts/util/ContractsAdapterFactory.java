/*     */ package ujf.verimag.bip.Extra.Contracts.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.PartType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortSpecification;
/*     */ import ujf.verimag.bip.Extra.Contracts.Contract;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractBinding;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractState;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceableElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContractsAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static ContractsPackage modelPackage;
/*     */   
/*     */   public ContractsAdapterFactory() {
/*  58 */     if (modelPackage == null)
/*     */     {
/*  60 */       modelPackage = ContractsPackage.eINSTANCE;
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
/*  75 */     if (object == modelPackage)
/*     */     {
/*  77 */       return true;
/*     */     }
/*  79 */     if (object instanceof EObject)
/*     */     {
/*  81 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   protected ContractsSwitch<Adapter> modelSwitch = new ContractsSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseContract(Contract object)
/*     */       {
/*  98 */         return ContractsAdapterFactory.this.createContractAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseContractState(ContractState object) {
/* 103 */         return ContractsAdapterFactory.this.createContractStateAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseContractBinding(ContractBinding object) {
/* 108 */         return ContractsAdapterFactory.this.createContractBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseNamedElement(NamedElement object) {
/* 113 */         return ContractsAdapterFactory.this.createNamedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseParameterizedElement(ParameterizedElement object) {
/* 118 */         return ContractsAdapterFactory.this.createParameterizedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTraceableElement(TraceableElement object) {
/* 123 */         return ContractsAdapterFactory.this.createTraceableElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBipType(BipType object) {
/* 128 */         return ContractsAdapterFactory.this.createBipTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePartType(PartType object) {
/* 133 */         return ContractsAdapterFactory.this.createPartTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseComponentType(ComponentType object) {
/* 138 */         return ContractsAdapterFactory.this.createComponentTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseCompoundType(CompoundType object) {
/* 143 */         return ContractsAdapterFactory.this.createCompoundTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseState(State object) {
/* 148 */         return ContractsAdapterFactory.this.createStateAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInnerPortSpecification(InnerPortSpecification object) {
/* 153 */         return ContractsAdapterFactory.this.createInnerPortSpecificationAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBinding(Binding object) {
/* 158 */         return ContractsAdapterFactory.this.createBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseExportBinding(ExportBinding object) {
/* 163 */         return ContractsAdapterFactory.this.createExportBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 168 */         return ContractsAdapterFactory.this.createEObjectAdapter();
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
/* 183 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createContractAdapter() {
/* 199 */     return null;
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
/*     */   public Adapter createContractStateAdapter() {
/* 214 */     return null;
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
/*     */   public Adapter createContractBindingAdapter() {
/* 229 */     return null;
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
/* 244 */     return null;
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
/*     */   public Adapter createParameterizedElementAdapter() {
/* 259 */     return null;
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
/*     */   public Adapter createTraceableElementAdapter() {
/* 274 */     return null;
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
/*     */   public Adapter createBipTypeAdapter() {
/* 289 */     return null;
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
/*     */   public Adapter createPartTypeAdapter() {
/* 304 */     return null;
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
/*     */   public Adapter createComponentTypeAdapter() {
/* 319 */     return null;
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
/*     */   public Adapter createCompoundTypeAdapter() {
/* 334 */     return null;
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
/*     */   public Adapter createStateAdapter() {
/* 349 */     return null;
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
/*     */   public Adapter createInnerPortSpecificationAdapter() {
/* 364 */     return null;
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
/*     */   public Adapter createBindingAdapter() {
/* 379 */     return null;
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
/*     */   public Adapter createExportBindingAdapter() {
/* 394 */     return null;
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
/* 407 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contract\\util\ContractsAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */