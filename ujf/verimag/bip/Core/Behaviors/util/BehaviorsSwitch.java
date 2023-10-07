/*     */ package ujf.verimag.bip.Core.Behaviors.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.Behaviors.AbstractTransition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Constant;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataTypedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Behaviors.MultiTransition;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.PartType;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.TransitionAlternative;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Behaviors.VariableBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.VariableDefinitionBinding;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceableElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorsSwitch<T>
/*     */ {
/*     */   protected static BehaviorsPackage modelPackage;
/*     */   
/*     */   public BehaviorsSwitch() {
/*  54 */     if (modelPackage == null)
/*     */     {
/*  56 */       modelPackage = BehaviorsPackage.eINSTANCE;
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
/*  69 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  81 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  83 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  87 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  88 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject); } protected T doSwitch(int classifierID, EObject theEObject) { PortDefinition portDefinition; AtomType atomType; ComponentType componentType; PartType partType; BipType bipType; ParameterizedElement parameterizedElement; DataParameter dataParameter; DataTypedElement dataTypedElement; DataType dataType; Variable variable; Expression expression; Action action; Port port; Binding binding; PortType portType;
/*     */     VariableBinding variableBinding;
/*     */     InterfaceVariable interfaceVariable;
/*     */     AbstractTransition abstractTransition;
/*     */     State state;
/*     */     Transition transition;
/*     */     TransitionAlternative transitionAlternative;
/*     */     Constant constant;
/*     */     Behavior behavior;
/*     */     PetriNet petriNet;
/*     */     DefinitionBinding definitionBinding;
/*     */     PortDefinitionReference portDefinitionReference;
/*     */     MultiTransition multiTransition;
/*     */     VariableDefinitionBinding variableDefinitionBinding;
/*     */     NamedElement namedElement;
/*     */     T result;
/* 104 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 108 */         portDefinition = (PortDefinition)theEObject;
/* 109 */         result = casePortDefinition(portDefinition);
/* 110 */         if (result == null) result = caseNamedElement((NamedElement)portDefinition); 
/* 111 */         if (result == null) result = defaultCase(theEObject); 
/* 112 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 116 */         atomType = (AtomType)theEObject;
/* 117 */         result = caseAtomType(atomType);
/* 118 */         if (result == null) result = caseComponentType((ComponentType)atomType); 
/* 119 */         if (result == null) result = casePartType((PartType)atomType); 
/* 120 */         if (result == null) result = caseBipType((BipType)atomType); 
/* 121 */         if (result == null) result = caseNamedElement((NamedElement)atomType); 
/* 122 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)atomType); 
/* 123 */         if (result == null) result = caseTraceableElement((TraceableElement)atomType); 
/* 124 */         if (result == null) result = defaultCase(theEObject); 
/* 125 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 129 */         componentType = (ComponentType)theEObject;
/* 130 */         result = caseComponentType(componentType);
/* 131 */         if (result == null) result = casePartType((PartType)componentType); 
/* 132 */         if (result == null) result = caseBipType((BipType)componentType); 
/* 133 */         if (result == null) result = caseNamedElement((NamedElement)componentType); 
/* 134 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)componentType); 
/* 135 */         if (result == null) result = caseTraceableElement((TraceableElement)componentType); 
/* 136 */         if (result == null) result = defaultCase(theEObject); 
/* 137 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 141 */         partType = (PartType)theEObject;
/* 142 */         result = casePartType(partType);
/* 143 */         if (result == null) result = caseBipType((BipType)partType); 
/* 144 */         if (result == null) result = caseNamedElement((NamedElement)partType); 
/* 145 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)partType); 
/* 146 */         if (result == null) result = caseTraceableElement((TraceableElement)partType); 
/* 147 */         if (result == null) result = defaultCase(theEObject); 
/* 148 */         return result;
/*     */ 
/*     */       
/*     */       case 4:
/* 152 */         bipType = (BipType)theEObject;
/* 153 */         result = caseBipType(bipType);
/* 154 */         if (result == null) result = caseNamedElement((NamedElement)bipType); 
/* 155 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)bipType); 
/* 156 */         if (result == null) result = caseTraceableElement((TraceableElement)bipType); 
/* 157 */         if (result == null) result = defaultCase(theEObject); 
/* 158 */         return result;
/*     */ 
/*     */       
/*     */       case 5:
/* 162 */         parameterizedElement = (ParameterizedElement)theEObject;
/* 163 */         result = caseParameterizedElement(parameterizedElement);
/* 164 */         if (result == null) result = defaultCase(theEObject); 
/* 165 */         return result;
/*     */ 
/*     */       
/*     */       case 6:
/* 169 */         dataParameter = (DataParameter)theEObject;
/* 170 */         result = caseDataParameter(dataParameter);
/* 171 */         if (result == null) result = caseDataTypedElement((DataTypedElement)dataParameter); 
/* 172 */         if (result == null) result = caseNamedElement((NamedElement)dataParameter); 
/* 173 */         if (result == null) result = defaultCase(theEObject); 
/* 174 */         return result;
/*     */ 
/*     */       
/*     */       case 7:
/* 178 */         dataTypedElement = (DataTypedElement)theEObject;
/* 179 */         result = caseDataTypedElement(dataTypedElement);
/* 180 */         if (result == null) result = caseNamedElement((NamedElement)dataTypedElement); 
/* 181 */         if (result == null) result = defaultCase(theEObject); 
/* 182 */         return result;
/*     */ 
/*     */       
/*     */       case 8:
/* 186 */         dataType = (DataType)theEObject;
/* 187 */         result = caseDataType(dataType);
/* 188 */         if (result == null) result = defaultCase(theEObject); 
/* 189 */         return result;
/*     */ 
/*     */       
/*     */       case 9:
/* 193 */         variable = (Variable)theEObject;
/* 194 */         result = caseVariable(variable);
/* 195 */         if (result == null) result = caseDataTypedElement((DataTypedElement)variable); 
/* 196 */         if (result == null) result = caseNamedElement((NamedElement)variable); 
/* 197 */         if (result == null) result = defaultCase(theEObject); 
/* 198 */         return result;
/*     */ 
/*     */       
/*     */       case 10:
/* 202 */         expression = (Expression)theEObject;
/* 203 */         result = caseExpression(expression);
/* 204 */         if (result == null) result = caseAction((Action)expression); 
/* 205 */         if (result == null) result = defaultCase(theEObject); 
/* 206 */         return result;
/*     */ 
/*     */       
/*     */       case 11:
/* 210 */         action = (Action)theEObject;
/* 211 */         result = caseAction(action);
/* 212 */         if (result == null) result = defaultCase(theEObject); 
/* 213 */         return result;
/*     */ 
/*     */       
/*     */       case 12:
/* 217 */         port = (Port)theEObject;
/* 218 */         result = casePort(port);
/* 219 */         if (result == null) result = caseNamedElement((NamedElement)port); 
/* 220 */         if (result == null) result = defaultCase(theEObject); 
/* 221 */         return result;
/*     */ 
/*     */       
/*     */       case 13:
/* 225 */         binding = (Binding)theEObject;
/* 226 */         result = caseBinding(binding);
/* 227 */         if (result == null) result = defaultCase(theEObject); 
/* 228 */         return result;
/*     */ 
/*     */       
/*     */       case 14:
/* 232 */         portType = (PortType)theEObject;
/* 233 */         result = casePortType(portType);
/* 234 */         if (result == null) result = caseBipType((BipType)portType); 
/* 235 */         if (result == null) result = caseNamedElement((NamedElement)portType); 
/* 236 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)portType); 
/* 237 */         if (result == null) result = caseTraceableElement((TraceableElement)portType); 
/* 238 */         if (result == null) result = defaultCase(theEObject); 
/* 239 */         return result;
/*     */ 
/*     */       
/*     */       case 15:
/* 243 */         variableBinding = (VariableBinding)theEObject;
/* 244 */         result = caseVariableBinding(variableBinding);
/* 245 */         if (result == null) result = defaultCase(theEObject); 
/* 246 */         return result;
/*     */ 
/*     */       
/*     */       case 16:
/* 250 */         interfaceVariable = (InterfaceVariable)theEObject;
/* 251 */         result = caseInterfaceVariable(interfaceVariable);
/* 252 */         if (result == null) result = caseDataTypedElement((DataTypedElement)interfaceVariable); 
/* 253 */         if (result == null) result = caseNamedElement((NamedElement)interfaceVariable); 
/* 254 */         if (result == null) result = defaultCase(theEObject); 
/* 255 */         return result;
/*     */ 
/*     */       
/*     */       case 17:
/* 259 */         abstractTransition = (AbstractTransition)theEObject;
/* 260 */         result = caseAbstractTransition(abstractTransition);
/* 261 */         if (result == null) result = caseNamedElement((NamedElement)abstractTransition); 
/* 262 */         if (result == null) result = defaultCase(theEObject); 
/* 263 */         return result;
/*     */ 
/*     */       
/*     */       case 18:
/* 267 */         state = (State)theEObject;
/* 268 */         result = caseState(state);
/* 269 */         if (result == null) result = caseNamedElement((NamedElement)state); 
/* 270 */         if (result == null) result = defaultCase(theEObject); 
/* 271 */         return result;
/*     */ 
/*     */       
/*     */       case 19:
/* 275 */         transition = (Transition)theEObject;
/* 276 */         result = caseTransition(transition);
/* 277 */         if (result == null) result = caseAbstractTransition((AbstractTransition)transition); 
/* 278 */         if (result == null) result = caseNamedElement((NamedElement)transition); 
/* 279 */         if (result == null) result = defaultCase(theEObject); 
/* 280 */         return result;
/*     */ 
/*     */       
/*     */       case 20:
/* 284 */         transitionAlternative = (TransitionAlternative)theEObject;
/* 285 */         result = caseTransitionAlternative(transitionAlternative);
/* 286 */         if (result == null) result = defaultCase(theEObject); 
/* 287 */         return result;
/*     */ 
/*     */       
/*     */       case 21:
/* 291 */         constant = (Constant)theEObject;
/* 292 */         result = caseConstant(constant);
/* 293 */         if (result == null) result = caseVariable((Variable)constant); 
/* 294 */         if (result == null) result = caseDataTypedElement((DataTypedElement)constant); 
/* 295 */         if (result == null) result = caseNamedElement((NamedElement)constant); 
/* 296 */         if (result == null) result = defaultCase(theEObject); 
/* 297 */         return result;
/*     */ 
/*     */       
/*     */       case 22:
/* 301 */         behavior = (Behavior)theEObject;
/* 302 */         result = caseBehavior(behavior);
/* 303 */         if (result == null) result = defaultCase(theEObject); 
/* 304 */         return result;
/*     */ 
/*     */       
/*     */       case 23:
/* 308 */         petriNet = (PetriNet)theEObject;
/* 309 */         result = casePetriNet(petriNet);
/* 310 */         if (result == null) result = caseBehavior((Behavior)petriNet); 
/* 311 */         if (result == null) result = defaultCase(theEObject); 
/* 312 */         return result;
/*     */ 
/*     */       
/*     */       case 24:
/* 316 */         definitionBinding = (DefinitionBinding)theEObject;
/* 317 */         result = caseDefinitionBinding(definitionBinding);
/* 318 */         if (result == null) result = caseBinding((Binding)definitionBinding); 
/* 319 */         if (result == null) result = defaultCase(theEObject); 
/* 320 */         return result;
/*     */ 
/*     */       
/*     */       case 25:
/* 324 */         portDefinitionReference = (PortDefinitionReference)theEObject;
/* 325 */         result = casePortDefinitionReference(portDefinitionReference);
/* 326 */         if (result == null) result = casePortReference((PortReference)portDefinitionReference); 
/* 327 */         if (result == null) result = caseACExpression((ACExpression)portDefinitionReference); 
/* 328 */         if (result == null) result = caseAIExpression((AIExpression)portDefinitionReference); 
/* 329 */         if (result == null) result = casePortExpression((PortExpression)portDefinitionReference); 
/* 330 */         if (result == null) result = defaultCase(theEObject); 
/* 331 */         return result;
/*     */ 
/*     */       
/*     */       case 26:
/* 335 */         multiTransition = (MultiTransition)theEObject;
/* 336 */         result = caseMultiTransition(multiTransition);
/* 337 */         if (result == null) result = caseAbstractTransition((AbstractTransition)multiTransition); 
/* 338 */         if (result == null) result = caseNamedElement((NamedElement)multiTransition); 
/* 339 */         if (result == null) result = defaultCase(theEObject); 
/* 340 */         return result;
/*     */ 
/*     */       
/*     */       case 27:
/* 344 */         variableDefinitionBinding = (VariableDefinitionBinding)theEObject;
/* 345 */         result = caseVariableDefinitionBinding(variableDefinitionBinding);
/* 346 */         if (result == null) result = caseVariableBinding((VariableBinding)variableDefinitionBinding); 
/* 347 */         if (result == null) result = defaultCase(theEObject); 
/* 348 */         return result;
/*     */ 
/*     */       
/*     */       case 28:
/* 352 */         namedElement = (NamedElement)theEObject;
/* 353 */         result = caseNamedElement(namedElement);
/* 354 */         if (result == null) result = defaultCase(theEObject); 
/* 355 */         return result;
/*     */     } 
/* 357 */     return defaultCase(theEObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T casePortDefinition(PortDefinition object) {
/* 374 */     return null;
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
/*     */   public T caseAtomType(AtomType object) {
/* 390 */     return null;
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
/* 406 */     return null;
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
/* 422 */     return null;
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
/* 438 */     return null;
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
/* 454 */     return null;
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
/*     */   public T caseDataParameter(DataParameter object) {
/* 470 */     return null;
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
/*     */   public T caseDataTypedElement(DataTypedElement object) {
/* 486 */     return null;
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
/* 502 */     return null;
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
/*     */   public T caseVariable(Variable object) {
/* 518 */     return null;
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
/* 534 */     return null;
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
/* 550 */     return null;
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
/*     */   public T casePort(Port object) {
/* 566 */     return null;
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
/* 582 */     return null;
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
/*     */   public T casePortType(PortType object) {
/* 598 */     return null;
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
/*     */   public T caseVariableBinding(VariableBinding object) {
/* 614 */     return null;
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
/*     */   public T caseInterfaceVariable(InterfaceVariable object) {
/* 630 */     return null;
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
/*     */   public T caseAbstractTransition(AbstractTransition object) {
/* 646 */     return null;
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
/* 662 */     return null;
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
/*     */   public T caseTransition(Transition object) {
/* 678 */     return null;
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
/*     */   public T caseTransitionAlternative(TransitionAlternative object) {
/* 694 */     return null;
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
/*     */   public T caseConstant(Constant object) {
/* 710 */     return null;
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
/*     */   public T caseBehavior(Behavior object) {
/* 726 */     return null;
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
/*     */   public T casePetriNet(PetriNet object) {
/* 742 */     return null;
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
/*     */   public T caseDefinitionBinding(DefinitionBinding object) {
/* 758 */     return null;
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
/*     */   public T casePortDefinitionReference(PortDefinitionReference object) {
/* 774 */     return null;
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
/*     */   public T caseMultiTransition(MultiTransition object) {
/* 790 */     return null;
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
/*     */   public T caseVariableDefinitionBinding(VariableDefinitionBinding object) {
/* 806 */     return null;
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
/* 822 */     return null;
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
/* 838 */     return null;
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
/* 854 */     return null;
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
/*     */   public T caseACExpression(ACExpression object) {
/* 870 */     return null;
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
/*     */   public T caseAIExpression(AIExpression object) {
/* 886 */     return null;
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
/*     */   public T casePortReference(PortReference object) {
/* 902 */     return null;
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
/* 918 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behavior\\util\BehaviorsSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */