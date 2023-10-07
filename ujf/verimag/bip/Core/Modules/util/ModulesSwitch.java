/*     */ package ujf.verimag.bip.Core.Modules.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModulesSwitch<T>
/*     */ {
/*     */   protected static ModulesPackage modelPackage;
/*     */   
/*     */   public ModulesSwitch() {
/*  60 */     if (modelPackage == null)
/*     */     {
/*  62 */       modelPackage = ModulesPackage.eINSTANCE;
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
/*  75 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  87 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  89 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  93 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  94 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected T doSwitch(int classifierID, EObject theEObject) {
/*     */     Module module;
/*     */     Declaration declaration;
/*     */     Package package_;
/*     */     System system;
/*     */     Root root;
/*     */     OpaqueElement opaqueElement;
/*     */     T result;
/* 110 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 114 */         module = (Module)theEObject;
/* 115 */         result = caseModule(module);
/* 116 */         if (result == null) result = caseNamedElement((NamedElement)module); 
/* 117 */         if (result == null) result = defaultCase(theEObject); 
/* 118 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 122 */         declaration = (Declaration)theEObject;
/* 123 */         result = caseDeclaration(declaration);
/* 124 */         if (result == null) result = defaultCase(theEObject); 
/* 125 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 129 */         package_ = (Package)theEObject;
/* 130 */         result = casePackage(package_);
/* 131 */         if (result == null) result = caseModule((Module)package_); 
/* 132 */         if (result == null) result = caseNamedElement((NamedElement)package_); 
/* 133 */         if (result == null) result = defaultCase(theEObject); 
/* 134 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 138 */         system = (System)theEObject;
/* 139 */         result = caseSystem(system);
/* 140 */         if (result == null) result = caseModule((Module)system); 
/* 141 */         if (result == null) result = caseNamedElement((NamedElement)system); 
/* 142 */         if (result == null) result = defaultCase(theEObject); 
/* 143 */         return result;
/*     */ 
/*     */       
/*     */       case 4:
/* 147 */         root = (Root)theEObject;
/* 148 */         result = caseRoot(root);
/* 149 */         if (result == null) result = caseNamedElement((NamedElement)root); 
/* 150 */         if (result == null) result = defaultCase(theEObject); 
/* 151 */         return result;
/*     */ 
/*     */       
/*     */       case 5:
/* 155 */         opaqueElement = (OpaqueElement)theEObject;
/* 156 */         result = caseOpaqueElement(opaqueElement);
/* 157 */         if (result == null) result = caseDataReference((DataReference)opaqueElement); 
/* 158 */         if (result == null) result = caseDataType((DataType)opaqueElement); 
/* 159 */         if (result == null) result = caseDeclaration((Declaration)opaqueElement); 
/* 160 */         if (result == null) result = casePortExpression((PortExpression)opaqueElement); 
/* 161 */         if (result == null) result = caseExpression((Expression)opaqueElement); 
/* 162 */         if (result == null) result = caseAction((Action)opaqueElement); 
/* 163 */         if (result == null) result = defaultCase(theEObject); 
/* 164 */         return result;
/*     */     } 
/* 166 */     return defaultCase(theEObject);
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
/*     */   public T caseModule(Module object) {
/* 183 */     return null;
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
/*     */   public T caseDeclaration(Declaration object) {
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
/*     */   
/*     */   public T casePackage(Package object) {
/* 215 */     return null;
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
/*     */   public T caseSystem(System object) {
/* 231 */     return null;
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
/*     */   public T caseRoot(Root object) {
/* 247 */     return null;
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
/*     */   public T caseOpaqueElement(OpaqueElement object) {
/* 263 */     return null;
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
/* 279 */     return null;
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
/*     */   
/*     */   public T caseExpression(Expression object) {
/* 311 */     return null;
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
/*     */   public T caseDataReference(DataReference object) {
/* 327 */     return null;
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
/*     */   public T caseDataType(DataType object) {
/* 343 */     return null;
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
/*     */   public T casePortExpression(PortExpression object) {
/* 359 */     return null;
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
/* 375 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Module\\util\ModulesSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */