/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterSpecification;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IndexLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerInterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExpressionsSwitch<T>
/*     */ {
/*     */   protected static ExpressionsPackage modelPackage;
/*     */   
/*     */   public ExpressionsSwitch() {
/*  50 */     if (modelPackage == null)
/*     */     {
/*  52 */       modelPackage = ExpressionsPackage.eINSTANCE;
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
/*  65 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  77 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  79 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  83 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  84 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject); } protected T doSwitch(int classifierID, EObject theEObject) { DataReference dataReference; DataParameterSpecification dataParameterSpecification; InnerDataParameterReference innerDataParameterReference; DataParameterReference dataParameterReference; BooleanLiteral booleanLiteral; IntegerLiteral integerLiteral; RealLiteral realLiteral;
/*     */     StringLiteral stringLiteral;
/*     */     UnaryExpression unaryExpression;
/*     */     BinaryExpression binaryExpression;
/*     */     IndexLiteral indexLiteral;
/*     */     FunctionCallExpression functionCallExpression;
/*     */     FieldNavigationExpression fieldNavigationExpression;
/*     */     DataNavigationExpression dataNavigationExpression;
/*     */     ArrayNavigationExpression arrayNavigationExpression;
/*     */     RequiredDataParameterReference requiredDataParameterReference;
/*     */     StateReference stateReference;
/*     */     InterfaceVariableReference interfaceVariableReference;
/*     */     PointerLiteral pointerLiteral;
/*     */     InnerInterfaceVariableReference innerInterfaceVariableReference;
/*     */     VariableReference variableReference;
/*     */     T result;
/* 100 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 104 */         dataReference = (DataReference)theEObject;
/* 105 */         result = caseDataReference(dataReference);
/* 106 */         if (result == null) result = caseExpression((Expression)dataReference); 
/* 107 */         if (result == null) result = caseAction((Action)dataReference); 
/* 108 */         if (result == null) result = defaultCase(theEObject); 
/* 109 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 113 */         dataParameterSpecification = (DataParameterSpecification)theEObject;
/* 114 */         result = caseDataParameterSpecification(dataParameterSpecification);
/* 115 */         if (result == null) result = caseDataReference((DataReference)dataParameterSpecification); 
/* 116 */         if (result == null) result = caseExpression((Expression)dataParameterSpecification); 
/* 117 */         if (result == null) result = caseAction((Action)dataParameterSpecification); 
/* 118 */         if (result == null) result = defaultCase(theEObject); 
/* 119 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 123 */         innerDataParameterReference = (InnerDataParameterReference)theEObject;
/* 124 */         result = caseInnerDataParameterReference(innerDataParameterReference);
/* 125 */         if (result == null) result = caseDataParameterSpecification((DataParameterSpecification)innerDataParameterReference); 
/* 126 */         if (result == null) result = caseDataReference((DataReference)innerDataParameterReference); 
/* 127 */         if (result == null) result = caseExpression((Expression)innerDataParameterReference); 
/* 128 */         if (result == null) result = caseAction((Action)innerDataParameterReference); 
/* 129 */         if (result == null) result = defaultCase(theEObject); 
/* 130 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 134 */         dataParameterReference = (DataParameterReference)theEObject;
/* 135 */         result = caseDataParameterReference(dataParameterReference);
/* 136 */         if (result == null) result = caseDataParameterSpecification((DataParameterSpecification)dataParameterReference); 
/* 137 */         if (result == null) result = caseDataReference((DataReference)dataParameterReference); 
/* 138 */         if (result == null) result = caseExpression((Expression)dataParameterReference); 
/* 139 */         if (result == null) result = caseAction((Action)dataParameterReference); 
/* 140 */         if (result == null) result = defaultCase(theEObject); 
/* 141 */         return result;
/*     */ 
/*     */       
/*     */       case 4:
/* 145 */         booleanLiteral = (BooleanLiteral)theEObject;
/* 146 */         result = caseBooleanLiteral(booleanLiteral);
/* 147 */         if (result == null) result = caseExpression((Expression)booleanLiteral); 
/* 148 */         if (result == null) result = caseAction((Action)booleanLiteral); 
/* 149 */         if (result == null) result = defaultCase(theEObject); 
/* 150 */         return result;
/*     */ 
/*     */       
/*     */       case 5:
/* 154 */         integerLiteral = (IntegerLiteral)theEObject;
/* 155 */         result = caseIntegerLiteral(integerLiteral);
/* 156 */         if (result == null) result = caseExpression((Expression)integerLiteral); 
/* 157 */         if (result == null) result = caseAction((Action)integerLiteral); 
/* 158 */         if (result == null) result = defaultCase(theEObject); 
/* 159 */         return result;
/*     */ 
/*     */       
/*     */       case 6:
/* 163 */         realLiteral = (RealLiteral)theEObject;
/* 164 */         result = caseRealLiteral(realLiteral);
/* 165 */         if (result == null) result = caseExpression((Expression)realLiteral); 
/* 166 */         if (result == null) result = caseAction((Action)realLiteral); 
/* 167 */         if (result == null) result = defaultCase(theEObject); 
/* 168 */         return result;
/*     */ 
/*     */       
/*     */       case 7:
/* 172 */         stringLiteral = (StringLiteral)theEObject;
/* 173 */         result = caseStringLiteral(stringLiteral);
/* 174 */         if (result == null) result = caseExpression((Expression)stringLiteral); 
/* 175 */         if (result == null) result = caseAction((Action)stringLiteral); 
/* 176 */         if (result == null) result = defaultCase(theEObject); 
/* 177 */         return result;
/*     */ 
/*     */       
/*     */       case 8:
/* 181 */         unaryExpression = (UnaryExpression)theEObject;
/* 182 */         result = caseUnaryExpression(unaryExpression);
/* 183 */         if (result == null) result = caseExpression((Expression)unaryExpression); 
/* 184 */         if (result == null) result = caseAction((Action)unaryExpression); 
/* 185 */         if (result == null) result = defaultCase(theEObject); 
/* 186 */         return result;
/*     */ 
/*     */       
/*     */       case 9:
/* 190 */         binaryExpression = (BinaryExpression)theEObject;
/* 191 */         result = caseBinaryExpression(binaryExpression);
/* 192 */         if (result == null) result = caseExpression((Expression)binaryExpression); 
/* 193 */         if (result == null) result = caseAction((Action)binaryExpression); 
/* 194 */         if (result == null) result = defaultCase(theEObject); 
/* 195 */         return result;
/*     */ 
/*     */       
/*     */       case 10:
/* 199 */         indexLiteral = (IndexLiteral)theEObject;
/* 200 */         result = caseIndexLiteral(indexLiteral);
/* 201 */         if (result == null) result = caseExpression((Expression)indexLiteral); 
/* 202 */         if (result == null) result = caseAction((Action)indexLiteral); 
/* 203 */         if (result == null) result = defaultCase(theEObject); 
/* 204 */         return result;
/*     */ 
/*     */       
/*     */       case 11:
/* 208 */         functionCallExpression = (FunctionCallExpression)theEObject;
/* 209 */         result = caseFunctionCallExpression(functionCallExpression);
/* 210 */         if (result == null) result = caseExpression((Expression)functionCallExpression); 
/* 211 */         if (result == null) result = caseAction((Action)functionCallExpression); 
/* 212 */         if (result == null) result = defaultCase(theEObject); 
/* 213 */         return result;
/*     */ 
/*     */       
/*     */       case 12:
/* 217 */         fieldNavigationExpression = (FieldNavigationExpression)theEObject;
/* 218 */         result = caseFieldNavigationExpression(fieldNavigationExpression);
/* 219 */         if (result == null) result = caseDataNavigationExpression((DataNavigationExpression)fieldNavigationExpression); 
/* 220 */         if (result == null) result = caseDataReference((DataReference)fieldNavigationExpression); 
/* 221 */         if (result == null) result = caseExpression((Expression)fieldNavigationExpression); 
/* 222 */         if (result == null) result = caseAction((Action)fieldNavigationExpression); 
/* 223 */         if (result == null) result = defaultCase(theEObject); 
/* 224 */         return result;
/*     */ 
/*     */       
/*     */       case 13:
/* 228 */         dataNavigationExpression = (DataNavigationExpression)theEObject;
/* 229 */         result = caseDataNavigationExpression(dataNavigationExpression);
/* 230 */         if (result == null) result = caseDataReference((DataReference)dataNavigationExpression); 
/* 231 */         if (result == null) result = caseExpression((Expression)dataNavigationExpression); 
/* 232 */         if (result == null) result = caseAction((Action)dataNavigationExpression); 
/* 233 */         if (result == null) result = defaultCase(theEObject); 
/* 234 */         return result;
/*     */ 
/*     */       
/*     */       case 14:
/* 238 */         arrayNavigationExpression = (ArrayNavigationExpression)theEObject;
/* 239 */         result = caseArrayNavigationExpression(arrayNavigationExpression);
/* 240 */         if (result == null) result = caseDataNavigationExpression((DataNavigationExpression)arrayNavigationExpression); 
/* 241 */         if (result == null) result = caseDataReference((DataReference)arrayNavigationExpression); 
/* 242 */         if (result == null) result = caseExpression((Expression)arrayNavigationExpression); 
/* 243 */         if (result == null) result = caseAction((Action)arrayNavigationExpression); 
/* 244 */         if (result == null) result = defaultCase(theEObject); 
/* 245 */         return result;
/*     */ 
/*     */       
/*     */       case 15:
/* 249 */         requiredDataParameterReference = (RequiredDataParameterReference)theEObject;
/* 250 */         result = caseRequiredDataParameterReference(requiredDataParameterReference);
/* 251 */         if (result == null) result = caseDataParameterSpecification((DataParameterSpecification)requiredDataParameterReference); 
/* 252 */         if (result == null) result = caseDataReference((DataReference)requiredDataParameterReference); 
/* 253 */         if (result == null) result = caseExpression((Expression)requiredDataParameterReference); 
/* 254 */         if (result == null) result = caseAction((Action)requiredDataParameterReference); 
/* 255 */         if (result == null) result = defaultCase(theEObject); 
/* 256 */         return result;
/*     */ 
/*     */       
/*     */       case 16:
/* 260 */         stateReference = (StateReference)theEObject;
/* 261 */         result = caseStateReference(stateReference);
/* 262 */         if (result == null) result = caseExpression((Expression)stateReference); 
/* 263 */         if (result == null) result = caseAction((Action)stateReference); 
/* 264 */         if (result == null) result = defaultCase(theEObject); 
/* 265 */         return result;
/*     */ 
/*     */       
/*     */       case 17:
/* 269 */         interfaceVariableReference = (InterfaceVariableReference)theEObject;
/* 270 */         result = caseInterfaceVariableReference(interfaceVariableReference);
/* 271 */         if (result == null) result = caseDataReference((DataReference)interfaceVariableReference); 
/* 272 */         if (result == null) result = caseExpression((Expression)interfaceVariableReference); 
/* 273 */         if (result == null) result = caseAction((Action)interfaceVariableReference); 
/* 274 */         if (result == null) result = defaultCase(theEObject); 
/* 275 */         return result;
/*     */ 
/*     */       
/*     */       case 18:
/* 279 */         pointerLiteral = (PointerLiteral)theEObject;
/* 280 */         result = casePointerLiteral(pointerLiteral);
/* 281 */         if (result == null) result = caseExpression((Expression)pointerLiteral); 
/* 282 */         if (result == null) result = caseAction((Action)pointerLiteral); 
/* 283 */         if (result == null) result = defaultCase(theEObject); 
/* 284 */         return result;
/*     */ 
/*     */       
/*     */       case 19:
/* 288 */         innerInterfaceVariableReference = (InnerInterfaceVariableReference)theEObject;
/* 289 */         result = caseInnerInterfaceVariableReference(innerInterfaceVariableReference);
/* 290 */         if (result == null) result = caseDataReference((DataReference)innerInterfaceVariableReference); 
/* 291 */         if (result == null) result = caseExpression((Expression)innerInterfaceVariableReference); 
/* 292 */         if (result == null) result = caseAction((Action)innerInterfaceVariableReference); 
/* 293 */         if (result == null) result = defaultCase(theEObject); 
/* 294 */         return result;
/*     */ 
/*     */       
/*     */       case 20:
/* 298 */         variableReference = (VariableReference)theEObject;
/* 299 */         result = caseVariableReference(variableReference);
/* 300 */         if (result == null) result = caseDataReference((DataReference)variableReference); 
/* 301 */         if (result == null) result = caseExpression((Expression)variableReference); 
/* 302 */         if (result == null) result = caseAction((Action)variableReference); 
/* 303 */         if (result == null) result = defaultCase(theEObject); 
/* 304 */         return result;
/*     */     } 
/* 306 */     return defaultCase(theEObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public T caseDataParameterSpecification(DataParameterSpecification object) {
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
/*     */   public T caseInnerDataParameterReference(InnerDataParameterReference object) {
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
/*     */   public T caseDataParameterReference(DataParameterReference object) {
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
/*     */   public T caseBooleanLiteral(BooleanLiteral object) {
/* 387 */     return null;
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
/*     */   public T caseIntegerLiteral(IntegerLiteral object) {
/* 403 */     return null;
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
/*     */   public T caseRealLiteral(RealLiteral object) {
/* 419 */     return null;
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
/*     */   public T caseStringLiteral(StringLiteral object) {
/* 435 */     return null;
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
/*     */   public T caseUnaryExpression(UnaryExpression object) {
/* 451 */     return null;
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
/*     */   public T caseBinaryExpression(BinaryExpression object) {
/* 467 */     return null;
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
/*     */   public T caseIndexLiteral(IndexLiteral object) {
/* 483 */     return null;
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
/*     */   public T caseFunctionCallExpression(FunctionCallExpression object) {
/* 499 */     return null;
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
/*     */   public T caseFieldNavigationExpression(FieldNavigationExpression object) {
/* 515 */     return null;
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
/*     */   public T caseDataNavigationExpression(DataNavigationExpression object) {
/* 531 */     return null;
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
/*     */   public T caseArrayNavigationExpression(ArrayNavigationExpression object) {
/* 547 */     return null;
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
/*     */   public T caseRequiredDataParameterReference(RequiredDataParameterReference object) {
/* 563 */     return null;
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
/*     */   public T caseStateReference(StateReference object) {
/* 579 */     return null;
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
/*     */   public T caseInterfaceVariableReference(InterfaceVariableReference object) {
/* 595 */     return null;
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
/*     */   public T casePointerLiteral(PointerLiteral object) {
/* 611 */     return null;
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
/*     */   public T caseInnerInterfaceVariableReference(InnerInterfaceVariableReference object) {
/* 627 */     return null;
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
/*     */   public T caseVariableReference(VariableReference object) {
/* 643 */     return null;
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
/* 659 */     return null;
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
/* 675 */     return null;
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
/* 691 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expression\\util\ExpressionsSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */