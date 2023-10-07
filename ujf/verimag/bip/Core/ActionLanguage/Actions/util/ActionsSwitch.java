/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionsSwitch<T>
/*     */ {
/*     */   protected static ActionsPackage modelPackage;
/*     */   
/*     */   public ActionsSwitch() {
/*  49 */     if (modelPackage == null)
/*     */     {
/*  51 */       modelPackage = ActionsPackage.eINSTANCE;
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
/*  64 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  76 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  78 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  82 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  83 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject);
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
/*     */     CompositeAction compositeAction;
/*     */     IfAction ifAction;
/*     */     AssignmentAction assignmentAction;
/*     */     T result;
/*  99 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 103 */         compositeAction = (CompositeAction)theEObject;
/* 104 */         result = caseCompositeAction(compositeAction);
/* 105 */         if (result == null) result = caseAction((Action)compositeAction); 
/* 106 */         if (result == null) result = defaultCase(theEObject); 
/* 107 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 111 */         ifAction = (IfAction)theEObject;
/* 112 */         result = caseIfAction(ifAction);
/* 113 */         if (result == null) result = caseAction((Action)ifAction); 
/* 114 */         if (result == null) result = defaultCase(theEObject); 
/* 115 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 119 */         assignmentAction = (AssignmentAction)theEObject;
/* 120 */         result = caseAssignmentAction(assignmentAction);
/* 121 */         if (result == null) result = caseAction((Action)assignmentAction); 
/* 122 */         if (result == null) result = defaultCase(theEObject); 
/* 123 */         return result;
/*     */     } 
/* 125 */     return defaultCase(theEObject);
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
/*     */   public T caseCompositeAction(CompositeAction object) {
/* 142 */     return null;
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
/*     */   public T caseIfAction(IfAction object) {
/* 158 */     return null;
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
/*     */   public T caseAssignmentAction(AssignmentAction object) {
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
/*     */   
/*     */   public T caseAction(Action object) {
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
/*     */   
/*     */   public T defaultCase(EObject object) {
/* 206 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Action\\util\ActionsSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */