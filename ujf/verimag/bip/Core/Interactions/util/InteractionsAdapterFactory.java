/*     */ package ujf.verimag.bip.Core.Interactions.util;
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
/*     */ public class InteractionsAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static InteractionsPackage modelPackage;
/*     */   
/*     */   public InteractionsAdapterFactory() {
/*  61 */     if (modelPackage == null)
/*     */     {
/*  63 */       modelPackage = InteractionsPackage.eINSTANCE;
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
/*  78 */     if (object == modelPackage)
/*     */     {
/*  80 */       return true;
/*     */     }
/*  82 */     if (object instanceof EObject)
/*     */     {
/*  84 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   protected InteractionsSwitch<Adapter> modelSwitch = new InteractionsSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseComponent(Component object)
/*     */       {
/* 101 */         return InteractionsAdapterFactory.this.createComponentAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePart(Part object) {
/* 106 */         return InteractionsAdapterFactory.this.createPartAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseMultiplicityElement(MultiplicityElement object) {
/* 111 */         return InteractionsAdapterFactory.this.createMultiplicityElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseCompoundType(CompoundType object) {
/* 116 */         return InteractionsAdapterFactory.this.createCompoundTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseConnector(Connector object) {
/* 121 */         return InteractionsAdapterFactory.this.createConnectorAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseActualPortParameter(ActualPortParameter object) {
/* 126 */         return InteractionsAdapterFactory.this.createActualPortParameterAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePartElementReference(PartElementReference object) {
/* 131 */         return InteractionsAdapterFactory.this.createPartElementReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseMultiplicityPath(MultiplicityPath object) {
/* 136 */         return InteractionsAdapterFactory.this.createMultiplicityPathAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInnerPortSpecification(InnerPortSpecification object) {
/* 141 */         return InteractionsAdapterFactory.this.createInnerPortSpecificationAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInteractionSpecification(InteractionSpecification object) {
/* 146 */         return InteractionsAdapterFactory.this.createInteractionSpecificationAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInteraction(Interaction object) {
/* 151 */         return InteractionsAdapterFactory.this.createInteractionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortParameter(PortParameter object) {
/* 156 */         return InteractionsAdapterFactory.this.createPortParameterAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseExportBinding(ExportBinding object) {
/* 161 */         return InteractionsAdapterFactory.this.createExportBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortParameterReference(PortParameterReference object) {
/* 166 */         return InteractionsAdapterFactory.this.createPortParameterReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInnerPortReference(InnerPortReference object) {
/* 171 */         return InteractionsAdapterFactory.this.createInnerPortReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseConditionalActualPortParameter(ConditionalActualPortParameter object) {
/* 176 */         return InteractionsAdapterFactory.this.createConditionalActualPortParameterAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariableExportBinding(VariableExportBinding object) {
/* 181 */         return InteractionsAdapterFactory.this.createVariableExportBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseConnectorType(ConnectorType object) {
/* 186 */         return InteractionsAdapterFactory.this.createConnectorTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseNamedElement(NamedElement object) {
/* 191 */         return InteractionsAdapterFactory.this.createNamedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseParameterizedElement(ParameterizedElement object) {
/* 196 */         return InteractionsAdapterFactory.this.createParameterizedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTraceableElement(TraceableElement object) {
/* 201 */         return InteractionsAdapterFactory.this.createTraceableElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBipType(BipType object) {
/* 206 */         return InteractionsAdapterFactory.this.createBipTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePartType(PartType object) {
/* 211 */         return InteractionsAdapterFactory.this.createPartTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseComponentType(ComponentType object) {
/* 216 */         return InteractionsAdapterFactory.this.createComponentTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePriorityElement(PriorityElement object) {
/* 221 */         return InteractionsAdapterFactory.this.createPriorityElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBinding(Binding object) {
/* 226 */         return InteractionsAdapterFactory.this.createBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortExpression(PortExpression object) {
/* 231 */         return InteractionsAdapterFactory.this.createPortExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACExpression(ACExpression object) {
/* 236 */         return InteractionsAdapterFactory.this.createACExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAIExpression(AIExpression object) {
/* 241 */         return InteractionsAdapterFactory.this.createAIExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortReference(PortReference object) {
/* 246 */         return InteractionsAdapterFactory.this.createPortReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariableBinding(VariableBinding object) {
/* 251 */         return InteractionsAdapterFactory.this.createVariableBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 256 */         return InteractionsAdapterFactory.this.createEObjectAdapter();
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
/* 271 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createComponentAdapter() {
/* 287 */     return null;
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
/*     */   public Adapter createPartAdapter() {
/* 302 */     return null;
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
/*     */   public Adapter createMultiplicityElementAdapter() {
/* 317 */     return null;
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
/* 332 */     return null;
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
/*     */   public Adapter createConnectorAdapter() {
/* 347 */     return null;
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
/*     */   public Adapter createActualPortParameterAdapter() {
/* 362 */     return null;
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
/*     */   public Adapter createPartElementReferenceAdapter() {
/* 377 */     return null;
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
/*     */   public Adapter createMultiplicityPathAdapter() {
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
/*     */   public Adapter createInnerPortSpecificationAdapter() {
/* 407 */     return null;
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
/*     */   public Adapter createInteractionSpecificationAdapter() {
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
/*     */   public Adapter createInteractionAdapter() {
/* 437 */     return null;
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
/*     */   public Adapter createPortParameterAdapter() {
/* 452 */     return null;
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
/*     */   public Adapter createPortParameterReferenceAdapter() {
/* 482 */     return null;
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
/*     */   public Adapter createInnerPortReferenceAdapter() {
/* 497 */     return null;
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
/*     */   public Adapter createConditionalActualPortParameterAdapter() {
/* 512 */     return null;
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
/*     */   public Adapter createVariableExportBindingAdapter() {
/* 527 */     return null;
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
/*     */   public Adapter createConnectorTypeAdapter() {
/* 542 */     return null;
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
/* 557 */     return null;
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
/* 572 */     return null;
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
/* 587 */     return null;
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
/* 602 */     return null;
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
/* 617 */     return null;
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
/*     */   public Adapter createPriorityElementAdapter() {
/* 647 */     return null;
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
/*     */   public Adapter createPortExpressionAdapter() {
/* 677 */     return null;
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
/*     */   public Adapter createACExpressionAdapter() {
/* 692 */     return null;
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
/*     */   public Adapter createAIExpressionAdapter() {
/* 707 */     return null;
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
/*     */   public Adapter createPortReferenceAdapter() {
/* 722 */     return null;
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
/*     */   public Adapter createVariableBindingAdapter() {
/* 737 */     return null;
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
/* 750 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interaction\\util\InteractionsAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */