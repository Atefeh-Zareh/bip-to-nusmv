/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ import ujf.verimag.bip.Core.Interactions.VariableExportBinding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InteractionsFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements InteractionsFactory
/*     */ {
/*     */   public static InteractionsFactory init() {
/*     */     try {
/*  37 */       InteractionsFactory theInteractionsFactory = (InteractionsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/Interactions.ecore");
/*  38 */       if (theInteractionsFactory != null)
/*     */       {
/*  40 */         return theInteractionsFactory;
/*     */       }
/*     */     }
/*  43 */     catch (Exception exception) {
/*     */       
/*  45 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  47 */     return new InteractionsFactoryImpl();
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
/*  69 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  71 */         return (EObject)createComponent();
/*  72 */       case 3: return (EObject)createCompoundType();
/*  73 */       case 4: return (EObject)createConnector();
/*  74 */       case 6: return (EObject)createPartElementReference();
/*  75 */       case 9: return (EObject)createInteractionSpecification();
/*  76 */       case 10: return (EObject)createInteraction();
/*  77 */       case 11: return (EObject)createPortParameter();
/*  78 */       case 12: return (EObject)createExportBinding();
/*  79 */       case 13: return (EObject)createPortParameterReference();
/*  80 */       case 14: return (EObject)createInnerPortReference();
/*  81 */       case 15: return (EObject)createConditionalActualPortParameter();
/*  82 */       case 16: return (EObject)createVariableExportBinding();
/*  83 */       case 17: return (EObject)createConnectorType();
/*     */     } 
/*  85 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component createComponent() {
/*  96 */     ComponentImpl component = new ComponentImpl();
/*  97 */     return component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType createCompoundType() {
/* 107 */     CompoundTypeImpl compoundType = new CompoundTypeImpl();
/* 108 */     return compoundType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Connector createConnector() {
/* 118 */     ConnectorImpl connector = new ConnectorImpl();
/* 119 */     return connector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartElementReference createPartElementReference() {
/* 129 */     PartElementReferenceImpl partElementReference = new PartElementReferenceImpl();
/* 130 */     return partElementReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionSpecification createInteractionSpecification() {
/* 140 */     InteractionSpecificationImpl interactionSpecification = new InteractionSpecificationImpl();
/* 141 */     return interactionSpecification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Interaction createInteraction() {
/* 151 */     InteractionImpl interaction = new InteractionImpl();
/* 152 */     return interaction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortParameter createPortParameter() {
/* 162 */     PortParameterImpl portParameter = new PortParameterImpl();
/* 163 */     return portParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExportBinding createExportBinding() {
/* 173 */     ExportBindingImpl exportBinding = new ExportBindingImpl();
/* 174 */     return exportBinding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortParameterReference createPortParameterReference() {
/* 184 */     PortParameterReferenceImpl portParameterReference = new PortParameterReferenceImpl();
/* 185 */     return portParameterReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InnerPortReference createInnerPortReference() {
/* 195 */     InnerPortReferenceImpl innerPortReference = new InnerPortReferenceImpl();
/* 196 */     return innerPortReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionalActualPortParameter createConditionalActualPortParameter() {
/* 206 */     ConditionalActualPortParameterImpl conditionalActualPortParameter = new ConditionalActualPortParameterImpl();
/* 207 */     return conditionalActualPortParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableExportBinding createVariableExportBinding() {
/* 217 */     VariableExportBindingImpl variableExportBinding = new VariableExportBindingImpl();
/* 218 */     return variableExportBinding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType createConnectorType() {
/* 228 */     ConnectorTypeImpl connectorType = new ConnectorTypeImpl();
/* 229 */     return connectorType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionsPackage getInteractionsPackage() {
/* 239 */     return (InteractionsPackage)getEPackage();
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
/*     */   public static InteractionsPackage getPackage() {
/* 251 */     return InteractionsPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\InteractionsFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */