/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionsFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements ActionsFactory
/*     */ {
/*     */   public static ActionsFactory init() {
/*     */     try {
/*  38 */       ActionsFactory theActionsFactory = (ActionsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore");
/*  39 */       if (theActionsFactory != null)
/*     */       {
/*  41 */         return theActionsFactory;
/*     */       }
/*     */     }
/*  44 */     catch (Exception exception) {
/*     */       
/*  46 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  48 */     return new ActionsFactoryImpl();
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
/*  70 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  72 */         return (EObject)createCompositeAction();
/*  73 */       case 1: return (EObject)createIfAction();
/*  74 */       case 2: return (EObject)createAssignmentAction();
/*     */     } 
/*  76 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/*  88 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 3:
/*  91 */         return createAssignTypeFromString(eDataType, initialValue);
/*     */     } 
/*  93 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 105 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 3:
/* 108 */         return convertAssignTypeToString(eDataType, instanceValue);
/*     */     } 
/* 110 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeAction createCompositeAction() {
/* 121 */     CompositeActionImpl compositeAction = new CompositeActionImpl();
/* 122 */     return compositeAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IfAction createIfAction() {
/* 132 */     IfActionImpl ifAction = new IfActionImpl();
/* 133 */     return ifAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AssignmentAction createAssignmentAction() {
/* 143 */     AssignmentActionImpl assignmentAction = new AssignmentActionImpl();
/* 144 */     return assignmentAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AssignType createAssignTypeFromString(EDataType eDataType, String initialValue) {
/* 154 */     AssignType result = AssignType.get(initialValue);
/* 155 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 156 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertAssignTypeToString(EDataType eDataType, Object instanceValue) {
/* 166 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionsPackage getActionsPackage() {
/* 176 */     return (ActionsPackage)getEPackage();
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
/*     */   public static ActionsPackage getPackage() {
/* 188 */     return ActionsPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\impl\ActionsFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */