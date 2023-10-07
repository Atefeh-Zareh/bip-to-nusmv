/*     */ package ujf.verimag.bip.Core.Priorities.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrioritiesSwitch<T>
/*     */ {
/*     */   protected static PrioritiesPackage modelPackage;
/*     */   
/*     */   public PrioritiesSwitch() {
/*  51 */     if (modelPackage == null)
/*     */     {
/*  53 */       modelPackage = PrioritiesPackage.eINSTANCE;
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
/*  66 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  78 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  80 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  84 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  85 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject);
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
/*     */     PriorityRule priorityRule;
/*     */     ConnectorTypeReference connectorTypeReference;
/*     */     PriorityElement priorityElement;
/*     */     T result;
/* 101 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 105 */         priorityRule = (PriorityRule)theEObject;
/* 106 */         result = casePriorityRule(priorityRule);
/* 107 */         if (result == null) result = caseMultiplicityElement((MultiplicityElement)priorityRule); 
/* 108 */         if (result == null) result = caseNamedElement((NamedElement)priorityRule); 
/* 109 */         if (result == null) result = defaultCase(theEObject); 
/* 110 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 114 */         connectorTypeReference = (ConnectorTypeReference)theEObject;
/* 115 */         result = caseConnectorTypeReference(connectorTypeReference);
/* 116 */         if (result == null) result = casePriorityElement((PriorityElement)connectorTypeReference); 
/* 117 */         if (result == null) result = defaultCase(theEObject); 
/* 118 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 122 */         priorityElement = (PriorityElement)theEObject;
/* 123 */         result = casePriorityElement(priorityElement);
/* 124 */         if (result == null) result = defaultCase(theEObject); 
/* 125 */         return result;
/*     */     } 
/* 127 */     return defaultCase(theEObject);
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
/*     */   public T casePriorityRule(PriorityRule object) {
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
/*     */   
/*     */   public T caseConnectorTypeReference(ConnectorTypeReference object) {
/* 160 */     return null;
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
/*     */   public T casePriorityElement(PriorityElement object) {
/* 176 */     return null;
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
/* 192 */     return null;
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
/*     */   public T caseMultiplicityElement(MultiplicityElement object) {
/* 208 */     return null;
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
/* 224 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Prioritie\\util\PrioritiesSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */