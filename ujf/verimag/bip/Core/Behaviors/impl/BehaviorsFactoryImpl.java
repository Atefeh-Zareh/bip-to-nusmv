/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Constant;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Behaviors.MultiTransition;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterDirectionKind;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.TransitionAlternative;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Behaviors.VariableDefinitionBinding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorsFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements BehaviorsFactory
/*     */ {
/*     */   public static BehaviorsFactory init() {
/*     */     try {
/*  38 */       BehaviorsFactory theBehaviorsFactory = (BehaviorsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*  39 */       if (theBehaviorsFactory != null)
/*     */       {
/*  41 */         return theBehaviorsFactory;
/*     */       }
/*     */     }
/*  44 */     catch (Exception exception) {
/*     */       
/*  46 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  48 */     return new BehaviorsFactoryImpl();
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
/*  72 */         return (EObject)createPortDefinition();
/*  73 */       case 1: return (EObject)createAtomType();
/*  74 */       case 6: return (EObject)createDataParameter();
/*  75 */       case 9: return (EObject)createVariable();
/*  76 */       case 12: return (EObject)createPort();
/*  77 */       case 14: return (EObject)createPortType();
/*  78 */       case 16: return (EObject)createInterfaceVariable();
/*  79 */       case 18: return (EObject)createState();
/*  80 */       case 19: return (EObject)createTransition();
/*  81 */       case 20: return (EObject)createTransitionAlternative();
/*  82 */       case 21: return (EObject)createConstant();
/*  83 */       case 23: return (EObject)createPetriNet();
/*  84 */       case 24: return (EObject)createDefinitionBinding();
/*  85 */       case 25: return (EObject)createPortDefinitionReference();
/*  86 */       case 26: return (EObject)createMultiTransition();
/*  87 */       case 27: return (EObject)createVariableDefinitionBinding();
/*     */     } 
/*  89 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
/* 101 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 29:
/* 104 */         return createParameterDirectionKindFromString(eDataType, initialValue);
/*     */     } 
/* 106 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
/* 118 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 29:
/* 121 */         return convertParameterDirectionKindToString(eDataType, instanceValue);
/*     */     } 
/* 123 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinition createPortDefinition() {
/* 134 */     PortDefinitionImpl portDefinition = new PortDefinitionImpl();
/* 135 */     return portDefinition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType createAtomType() {
/* 145 */     AtomTypeImpl atomType = new AtomTypeImpl();
/* 146 */     return atomType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataParameter createDataParameter() {
/* 156 */     DataParameterImpl dataParameter = new DataParameterImpl();
/* 157 */     return dataParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable createVariable() {
/* 167 */     VariableImpl variable = new VariableImpl();
/* 168 */     return variable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port createPort() {
/* 178 */     PortImpl port = new PortImpl();
/* 179 */     return port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType createPortType() {
/* 189 */     PortTypeImpl portType = new PortTypeImpl();
/* 190 */     return portType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable createInterfaceVariable() {
/* 200 */     InterfaceVariableImpl interfaceVariable = new InterfaceVariableImpl();
/* 201 */     return interfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public State createState() {
/* 211 */     StateImpl state = new StateImpl();
/* 212 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transition createTransition() {
/* 222 */     TransitionImpl transition = new TransitionImpl();
/* 223 */     return transition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransitionAlternative createTransitionAlternative() {
/* 233 */     TransitionAlternativeImpl transitionAlternative = new TransitionAlternativeImpl();
/* 234 */     return transitionAlternative;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Constant createConstant() {
/* 244 */     ConstantImpl constant = new ConstantImpl();
/* 245 */     return constant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PetriNet createPetriNet() {
/* 255 */     PetriNetImpl petriNet = new PetriNetImpl();
/* 256 */     return petriNet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefinitionBinding createDefinitionBinding() {
/* 266 */     DefinitionBindingImpl definitionBinding = new DefinitionBindingImpl();
/* 267 */     return definitionBinding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinitionReference createPortDefinitionReference() {
/* 277 */     PortDefinitionReferenceImpl portDefinitionReference = new PortDefinitionReferenceImpl();
/* 278 */     return portDefinitionReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiTransition createMultiTransition() {
/* 288 */     MultiTransitionImpl multiTransition = new MultiTransitionImpl();
/* 289 */     return multiTransition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableDefinitionBinding createVariableDefinitionBinding() {
/* 299 */     VariableDefinitionBindingImpl variableDefinitionBinding = new VariableDefinitionBindingImpl();
/* 300 */     return variableDefinitionBinding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParameterDirectionKind createParameterDirectionKindFromString(EDataType eDataType, String initialValue) {
/* 310 */     ParameterDirectionKind result = ParameterDirectionKind.get(initialValue);
/* 311 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 312 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertParameterDirectionKindToString(EDataType eDataType, Object instanceValue) {
/* 322 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BehaviorsPackage getBehaviorsPackage() {
/* 332 */     return (BehaviorsPackage)getEPackage();
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
/*     */   public static BehaviorsPackage getPackage() {
/* 344 */     return BehaviorsPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\BehaviorsFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */