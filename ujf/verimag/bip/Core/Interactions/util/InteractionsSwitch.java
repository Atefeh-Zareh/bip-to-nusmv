/*     */ package ujf.verimag.bip.Core.Interactions.util;
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
/*     */ import ujf.verimag.bip.Core.Behaviors.VariableBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.MultiplicityElement;
/*     */ import ujf.verimag.bip.Core.Interactions.MultiplicityPath;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ import ujf.verimag.bip.Core.Interactions.VariableExportBinding;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
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
/*     */ public class InteractionsSwitch<T>
/*     */ {
/*     */   protected static InteractionsPackage modelPackage;
/*     */   
/*     */   public InteractionsSwitch() {
/*  64 */     if (modelPackage == null)
/*     */     {
/*  66 */       modelPackage = InteractionsPackage.eINSTANCE;
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
/*  79 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  91 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  93 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  97 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  98 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject); } protected T doSwitch(int classifierID, EObject theEObject) { Component component; Part part; MultiplicityElement multiplicityElement; CompoundType compoundType;
/*     */     Connector connector;
/*     */     ActualPortParameter actualPortParameter;
/*     */     PartElementReference partElementReference;
/*     */     MultiplicityPath multiplicityPath;
/*     */     InnerPortSpecification innerPortSpecification;
/*     */     InteractionSpecification interactionSpecification;
/*     */     Interaction interaction;
/*     */     PortParameter portParameter;
/*     */     ExportBinding exportBinding;
/*     */     PortParameterReference portParameterReference;
/*     */     InnerPortReference innerPortReference;
/*     */     ConditionalActualPortParameter conditionalActualPortParameter;
/*     */     VariableExportBinding variableExportBinding;
/*     */     ConnectorType connectorType;
/*     */     T result;
/* 114 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 118 */         component = (Component)theEObject;
/* 119 */         result = caseComponent(component);
/* 120 */         if (result == null) result = casePart((Part)component); 
/* 121 */         if (result == null) result = caseMultiplicityElement((MultiplicityElement)component); 
/* 122 */         if (result == null) result = caseNamedElement((NamedElement)component); 
/* 123 */         if (result == null) result = defaultCase(theEObject); 
/* 124 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 128 */         part = (Part)theEObject;
/* 129 */         result = casePart(part);
/* 130 */         if (result == null) result = caseMultiplicityElement((MultiplicityElement)part); 
/* 131 */         if (result == null) result = caseNamedElement((NamedElement)part); 
/* 132 */         if (result == null) result = defaultCase(theEObject); 
/* 133 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 137 */         multiplicityElement = (MultiplicityElement)theEObject;
/* 138 */         result = caseMultiplicityElement(multiplicityElement);
/* 139 */         if (result == null) result = caseNamedElement((NamedElement)multiplicityElement); 
/* 140 */         if (result == null) result = defaultCase(theEObject); 
/* 141 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 145 */         compoundType = (CompoundType)theEObject;
/* 146 */         result = caseCompoundType(compoundType);
/* 147 */         if (result == null) result = caseComponentType((ComponentType)compoundType); 
/* 148 */         if (result == null) result = casePartType((PartType)compoundType); 
/* 149 */         if (result == null) result = caseBipType((BipType)compoundType); 
/* 150 */         if (result == null) result = caseNamedElement((NamedElement)compoundType); 
/* 151 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)compoundType); 
/* 152 */         if (result == null) result = caseTraceableElement((TraceableElement)compoundType); 
/* 153 */         if (result == null) result = defaultCase(theEObject); 
/* 154 */         return result;
/*     */ 
/*     */       
/*     */       case 4:
/* 158 */         connector = (Connector)theEObject;
/* 159 */         result = caseConnector(connector);
/* 160 */         if (result == null) result = casePart((Part)connector); 
/* 161 */         if (result == null) result = caseMultiplicityElement((MultiplicityElement)connector); 
/* 162 */         if (result == null) result = caseNamedElement((NamedElement)connector); 
/* 163 */         if (result == null) result = defaultCase(theEObject); 
/* 164 */         return result;
/*     */ 
/*     */       
/*     */       case 5:
/* 168 */         actualPortParameter = (ActualPortParameter)theEObject;
/* 169 */         result = caseActualPortParameter(actualPortParameter);
/* 170 */         if (result == null) result = defaultCase(theEObject); 
/* 171 */         return result;
/*     */ 
/*     */       
/*     */       case 6:
/* 175 */         partElementReference = (PartElementReference)theEObject;
/* 176 */         result = casePartElementReference(partElementReference);
/* 177 */         if (result == null) result = caseMultiplicityPath((MultiplicityPath)partElementReference); 
/* 178 */         if (result == null) result = defaultCase(theEObject); 
/* 179 */         return result;
/*     */ 
/*     */       
/*     */       case 7:
/* 183 */         multiplicityPath = (MultiplicityPath)theEObject;
/* 184 */         result = caseMultiplicityPath(multiplicityPath);
/* 185 */         if (result == null) result = defaultCase(theEObject); 
/* 186 */         return result;
/*     */ 
/*     */       
/*     */       case 8:
/* 190 */         innerPortSpecification = (InnerPortSpecification)theEObject;
/* 191 */         result = caseInnerPortSpecification(innerPortSpecification);
/* 192 */         if (result == null) result = defaultCase(theEObject); 
/* 193 */         return result;
/*     */ 
/*     */       
/*     */       case 9:
/* 197 */         interactionSpecification = (InteractionSpecification)theEObject;
/* 198 */         result = caseInteractionSpecification(interactionSpecification);
/* 199 */         if (result == null) result = defaultCase(theEObject); 
/* 200 */         return result;
/*     */ 
/*     */       
/*     */       case 10:
/* 204 */         interaction = (Interaction)theEObject;
/* 205 */         result = caseInteraction(interaction);
/* 206 */         if (result == null) result = casePriorityElement((PriorityElement)interaction); 
/* 207 */         if (result == null) result = defaultCase(theEObject); 
/* 208 */         return result;
/*     */ 
/*     */       
/*     */       case 11:
/* 212 */         portParameter = (PortParameter)theEObject;
/* 213 */         result = casePortParameter(portParameter);
/* 214 */         if (result == null) result = caseNamedElement((NamedElement)portParameter); 
/* 215 */         if (result == null) result = defaultCase(theEObject); 
/* 216 */         return result;
/*     */ 
/*     */       
/*     */       case 12:
/* 220 */         exportBinding = (ExportBinding)theEObject;
/* 221 */         result = caseExportBinding(exportBinding);
/* 222 */         if (result == null) result = caseInnerPortSpecification((InnerPortSpecification)exportBinding); 
/* 223 */         if (result == null) result = caseBinding((Binding)exportBinding); 
/* 224 */         if (result == null) result = defaultCase(theEObject); 
/* 225 */         return result;
/*     */ 
/*     */       
/*     */       case 13:
/* 229 */         portParameterReference = (PortParameterReference)theEObject;
/* 230 */         result = casePortParameterReference(portParameterReference);
/* 231 */         if (result == null) result = casePortReference((PortReference)portParameterReference); 
/* 232 */         if (result == null) result = caseACExpression((ACExpression)portParameterReference); 
/* 233 */         if (result == null) result = caseAIExpression((AIExpression)portParameterReference); 
/* 234 */         if (result == null) result = casePortExpression((PortExpression)portParameterReference); 
/* 235 */         if (result == null) result = defaultCase(theEObject); 
/* 236 */         return result;
/*     */ 
/*     */       
/*     */       case 14:
/* 240 */         innerPortReference = (InnerPortReference)theEObject;
/* 241 */         result = caseInnerPortReference(innerPortReference);
/* 242 */         if (result == null) result = caseInnerPortSpecification((InnerPortSpecification)innerPortReference); 
/* 243 */         if (result == null) result = caseActualPortParameter((ActualPortParameter)innerPortReference); 
/* 244 */         if (result == null) result = casePortReference((PortReference)innerPortReference); 
/* 245 */         if (result == null) result = caseACExpression((ACExpression)innerPortReference); 
/* 246 */         if (result == null) result = caseAIExpression((AIExpression)innerPortReference); 
/* 247 */         if (result == null) result = casePortExpression((PortExpression)innerPortReference); 
/* 248 */         if (result == null) result = defaultCase(theEObject); 
/* 249 */         return result;
/*     */ 
/*     */       
/*     */       case 15:
/* 253 */         conditionalActualPortParameter = (ConditionalActualPortParameter)theEObject;
/* 254 */         result = caseConditionalActualPortParameter(conditionalActualPortParameter);
/* 255 */         if (result == null) result = caseActualPortParameter((ActualPortParameter)conditionalActualPortParameter); 
/* 256 */         if (result == null) result = defaultCase(theEObject); 
/* 257 */         return result;
/*     */ 
/*     */       
/*     */       case 16:
/* 261 */         variableExportBinding = (VariableExportBinding)theEObject;
/* 262 */         result = caseVariableExportBinding(variableExportBinding);
/* 263 */         if (result == null) result = caseVariableBinding((VariableBinding)variableExportBinding); 
/* 264 */         if (result == null) result = defaultCase(theEObject); 
/* 265 */         return result;
/*     */ 
/*     */       
/*     */       case 17:
/* 269 */         connectorType = (ConnectorType)theEObject;
/* 270 */         result = caseConnectorType(connectorType);
/* 271 */         if (result == null) result = casePartType((PartType)connectorType); 
/* 272 */         if (result == null) result = caseBipType((BipType)connectorType); 
/* 273 */         if (result == null) result = caseNamedElement((NamedElement)connectorType); 
/* 274 */         if (result == null) result = caseParameterizedElement((ParameterizedElement)connectorType); 
/* 275 */         if (result == null) result = caseTraceableElement((TraceableElement)connectorType); 
/* 276 */         if (result == null) result = defaultCase(theEObject); 
/* 277 */         return result;
/*     */     } 
/* 279 */     return defaultCase(theEObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T caseComponent(Component object) {
/* 296 */     return null;
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
/*     */   public T casePart(Part object) {
/* 312 */     return null;
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
/* 328 */     return null;
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
/* 344 */     return null;
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
/*     */   public T caseConnector(Connector object) {
/* 360 */     return null;
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
/*     */   public T caseActualPortParameter(ActualPortParameter object) {
/* 376 */     return null;
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
/*     */   public T casePartElementReference(PartElementReference object) {
/* 392 */     return null;
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
/*     */   public T caseMultiplicityPath(MultiplicityPath object) {
/* 408 */     return null;
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
/* 424 */     return null;
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
/*     */   public T caseInteractionSpecification(InteractionSpecification object) {
/* 440 */     return null;
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
/*     */   public T caseInteraction(Interaction object) {
/* 456 */     return null;
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
/*     */   public T casePortParameter(PortParameter object) {
/* 472 */     return null;
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
/* 488 */     return null;
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
/*     */   public T casePortParameterReference(PortParameterReference object) {
/* 504 */     return null;
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
/*     */   public T caseInnerPortReference(InnerPortReference object) {
/* 520 */     return null;
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
/*     */   public T caseConditionalActualPortParameter(ConditionalActualPortParameter object) {
/* 536 */     return null;
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
/*     */   public T caseVariableExportBinding(VariableExportBinding object) {
/* 552 */     return null;
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
/*     */   public T caseConnectorType(ConnectorType object) {
/* 568 */     return null;
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
/* 584 */     return null;
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
/* 600 */     return null;
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
/* 616 */     return null;
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
/* 632 */     return null;
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
/* 648 */     return null;
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
/* 664 */     return null;
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
/* 680 */     return null;
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
/* 696 */     return null;
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
/* 712 */     return null;
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
/* 728 */     return null;
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
/* 744 */     return null;
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
/* 760 */     return null;
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
/* 776 */     return null;
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
/* 792 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interaction\\util\InteractionsSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */