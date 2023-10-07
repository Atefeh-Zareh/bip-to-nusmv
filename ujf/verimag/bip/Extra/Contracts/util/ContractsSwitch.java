/*     */ package ujf.verimag.bip.Extra.Contracts.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContractsSwitch<T>
/*     */ {
/*     */   protected static ContractsPackage modelPackage;
/*     */   
/*     */   public ContractsSwitch() {
/*  61 */     if (modelPackage == null)
/*     */     {
/*  63 */       modelPackage = ContractsPackage.eINSTANCE;
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
/*     */   public T doSwitch(EObject theEObject) {
/*  76 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*     */   protected T doSwitch(EClass theEClass, EObject theEObject) {
/*  88 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  90 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  94 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  95 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject);
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
/*     */   protected T doSwitch(int classifierID, EObject theEObject) {
/*     */     Contract contract;
/*     */     ContractState contractState;
/*     */     ContractBinding contractBinding;
/*     */     T result;
/* 111 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 115 */         contract = (Contract)theEObject;
/* 116 */         result = caseContract(contract);
/* 117 */         if (result == null) result = caseCompoundType((CompoundType)contract); 
/* 118 */         if (result == null) result = caseComponentType((ComponentType)contract); 
/* 119 */         if (result == null) result = casePartType((PartType)contract); 
/* 120 */         if (result == null) result = caseBipType((BipType)contract); 
/* 121 */         if (result == null) result = caseNamedElement((NamedElement)contract); 
/* 122 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)contract); 
/* 123 */         if (result == null) result = caseTraceableElement((TraceableElement)contract); 
/* 124 */         if (result == null) result = defaultCase(theEObject); 
/* 125 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 129 */         contractState = (ContractState)theEObject;
/* 130 */         result = caseContractState(contractState);
/* 131 */         if (result == null) result = caseState((State)contractState); 
/* 132 */         if (result == null) result = caseNamedElement((NamedElement)contractState); 
/* 133 */         if (result == null) result = defaultCase(theEObject); 
/* 134 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 138 */         contractBinding = (ContractBinding)theEObject;
/* 139 */         result = caseContractBinding(contractBinding);
/* 140 */         if (result == null) result = caseExportBinding((ExportBinding)contractBinding); 
/* 141 */         if (result == null) result = caseInnerPortSpecification((InnerPortSpecification)contractBinding); 
/* 142 */         if (result == null) result = caseBinding((Binding)contractBinding); 
/* 143 */         if (result == null) result = defaultCase(theEObject); 
/* 144 */         return result;
/*     */     } 
/* 146 */     return defaultCase(theEObject);
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
/*     */   public T caseContract(Contract object) {
/* 163 */     return null;
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
/*     */   public T caseContractState(ContractState object) {
/* 179 */     return null;
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
/*     */   public T caseContractBinding(ContractBinding object) {
/* 195 */     return null;
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
/*     */   public T caseNamedElement(NamedElement object) {
/* 211 */     return null;
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
/*     */   public T caseParameterizedElement(ParameterizedElement object) {
/* 227 */     return null;
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
/*     */   public T caseTraceableElement(TraceableElement object) {
/* 243 */     return null;
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
/*     */   public T caseBipType(BipType object) {
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
/*     */   
/*     */   public T casePartType(PartType object) {
/* 275 */     return null;
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
/*     */   public T caseComponentType(ComponentType object) {
/* 291 */     return null;
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
/*     */   public T caseCompoundType(CompoundType object) {
/* 307 */     return null;
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
/*     */   public T caseState(State object) {
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
/*     */   
/*     */   public T caseInnerPortSpecification(InnerPortSpecification object) {
/* 339 */     return null;
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
/*     */   public T caseBinding(Binding object) {
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
/*     */   
/*     */   public T caseExportBinding(ExportBinding object) {
/* 371 */     return null;
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
/*     */   public T defaultCase(EObject object) {
/* 387 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contract\\util\ContractsSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */