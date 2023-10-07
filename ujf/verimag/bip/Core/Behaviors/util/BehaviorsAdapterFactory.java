/*     */ package ujf.verimag.bip.Core.Behaviors.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
/*     */ public class BehaviorsAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static BehaviorsPackage modelPackage;
/*     */   
/*     */   public BehaviorsAdapterFactory() {
/*  51 */     if (modelPackage == null)
/*     */     {
/*  53 */       modelPackage = BehaviorsPackage.eINSTANCE;
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
/*  68 */     if (object == modelPackage)
/*     */     {
/*  70 */       return true;
/*     */     }
/*  72 */     if (object instanceof EObject)
/*     */     {
/*  74 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   protected BehaviorsSwitch<Adapter> modelSwitch = new BehaviorsSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter casePortDefinition(PortDefinition object)
/*     */       {
/*  91 */         return BehaviorsAdapterFactory.this.createPortDefinitionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAtomType(AtomType object) {
/*  96 */         return BehaviorsAdapterFactory.this.createAtomTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseComponentType(ComponentType object) {
/* 101 */         return BehaviorsAdapterFactory.this.createComponentTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePartType(PartType object) {
/* 106 */         return BehaviorsAdapterFactory.this.createPartTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBipType(BipType object) {
/* 111 */         return BehaviorsAdapterFactory.this.createBipTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseParameterizedElement(ParameterizedElement object) {
/* 116 */         return BehaviorsAdapterFactory.this.createParameterizedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataParameter(DataParameter object) {
/* 121 */         return BehaviorsAdapterFactory.this.createDataParameterAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataTypedElement(DataTypedElement object) {
/* 126 */         return BehaviorsAdapterFactory.this.createDataTypedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataType(DataType object) {
/* 131 */         return BehaviorsAdapterFactory.this.createDataTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariable(Variable object) {
/* 136 */         return BehaviorsAdapterFactory.this.createVariableAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseExpression(Expression object) {
/* 141 */         return BehaviorsAdapterFactory.this.createExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAction(Action object) {
/* 146 */         return BehaviorsAdapterFactory.this.createActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePort(Port object) {
/* 151 */         return BehaviorsAdapterFactory.this.createPortAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBinding(Binding object) {
/* 156 */         return BehaviorsAdapterFactory.this.createBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortType(PortType object) {
/* 161 */         return BehaviorsAdapterFactory.this.createPortTypeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariableBinding(VariableBinding object) {
/* 166 */         return BehaviorsAdapterFactory.this.createVariableBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInterfaceVariable(InterfaceVariable object) {
/* 171 */         return BehaviorsAdapterFactory.this.createInterfaceVariableAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAbstractTransition(AbstractTransition object) {
/* 176 */         return BehaviorsAdapterFactory.this.createAbstractTransitionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseState(State object) {
/* 181 */         return BehaviorsAdapterFactory.this.createStateAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTransition(Transition object) {
/* 186 */         return BehaviorsAdapterFactory.this.createTransitionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTransitionAlternative(TransitionAlternative object) {
/* 191 */         return BehaviorsAdapterFactory.this.createTransitionAlternativeAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseConstant(Constant object) {
/* 196 */         return BehaviorsAdapterFactory.this.createConstantAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBehavior(Behavior object) {
/* 201 */         return BehaviorsAdapterFactory.this.createBehaviorAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePetriNet(PetriNet object) {
/* 206 */         return BehaviorsAdapterFactory.this.createPetriNetAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDefinitionBinding(DefinitionBinding object) {
/* 211 */         return BehaviorsAdapterFactory.this.createDefinitionBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortDefinitionReference(PortDefinitionReference object) {
/* 216 */         return BehaviorsAdapterFactory.this.createPortDefinitionReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseMultiTransition(MultiTransition object) {
/* 221 */         return BehaviorsAdapterFactory.this.createMultiTransitionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariableDefinitionBinding(VariableDefinitionBinding object) {
/* 226 */         return BehaviorsAdapterFactory.this.createVariableDefinitionBindingAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseNamedElement(NamedElement object) {
/* 231 */         return BehaviorsAdapterFactory.this.createNamedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTraceableElement(TraceableElement object) {
/* 236 */         return BehaviorsAdapterFactory.this.createTraceableElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortExpression(PortExpression object) {
/* 241 */         return BehaviorsAdapterFactory.this.createPortExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseACExpression(ACExpression object) {
/* 246 */         return BehaviorsAdapterFactory.this.createACExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAIExpression(AIExpression object) {
/* 251 */         return BehaviorsAdapterFactory.this.createAIExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePortReference(PortReference object) {
/* 256 */         return BehaviorsAdapterFactory.this.createPortReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 261 */         return BehaviorsAdapterFactory.this.createEObjectAdapter();
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
/* 276 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createPortDefinitionAdapter() {
/* 292 */     return null;
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
/*     */   public Adapter createAtomTypeAdapter() {
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
/*     */   public Adapter createComponentTypeAdapter() {
/* 322 */     return null;
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
/* 337 */     return null;
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
/* 352 */     return null;
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
/* 367 */     return null;
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
/*     */   public Adapter createDataParameterAdapter() {
/* 382 */     return null;
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
/*     */   public Adapter createDataTypedElementAdapter() {
/* 397 */     return null;
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
/*     */   public Adapter createDataTypeAdapter() {
/* 412 */     return null;
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
/*     */   public Adapter createVariableAdapter() {
/* 427 */     return null;
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
/*     */   public Adapter createExpressionAdapter() {
/* 442 */     return null;
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
/*     */   public Adapter createActionAdapter() {
/* 457 */     return null;
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
/*     */   public Adapter createPortAdapter() {
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
/*     */   public Adapter createBindingAdapter() {
/* 487 */     return null;
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
/*     */   public Adapter createPortTypeAdapter() {
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
/*     */   public Adapter createVariableBindingAdapter() {
/* 517 */     return null;
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
/*     */   public Adapter createInterfaceVariableAdapter() {
/* 532 */     return null;
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
/*     */   public Adapter createAbstractTransitionAdapter() {
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
/*     */   public Adapter createStateAdapter() {
/* 562 */     return null;
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
/*     */   public Adapter createTransitionAdapter() {
/* 577 */     return null;
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
/*     */   public Adapter createTransitionAlternativeAdapter() {
/* 592 */     return null;
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
/*     */   public Adapter createConstantAdapter() {
/* 607 */     return null;
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
/*     */   public Adapter createBehaviorAdapter() {
/* 622 */     return null;
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
/*     */   public Adapter createPetriNetAdapter() {
/* 637 */     return null;
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
/*     */   public Adapter createDefinitionBindingAdapter() {
/* 652 */     return null;
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
/*     */   public Adapter createPortDefinitionReferenceAdapter() {
/* 667 */     return null;
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
/*     */   public Adapter createMultiTransitionAdapter() {
/* 682 */     return null;
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
/*     */   public Adapter createVariableDefinitionBindingAdapter() {
/* 697 */     return null;
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
/*     */   public Adapter createTraceableElementAdapter() {
/* 727 */     return null;
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
/*     */   public Adapter createACExpressionAdapter() {
/* 757 */     return null;
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
/* 772 */     return null;
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
/* 787 */     return null;
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
/* 800 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behavior\\util\BehaviorsAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */