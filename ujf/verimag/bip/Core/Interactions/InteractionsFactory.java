/*    */ package ujf.verimag.bip.Core.Interactions;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.Interactions.impl.InteractionsFactoryImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface InteractionsFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final InteractionsFactory eINSTANCE = InteractionsFactoryImpl.init();
/*    */   
/*    */   Component createComponent();
/*    */   
/*    */   CompoundType createCompoundType();
/*    */   
/*    */   Connector createConnector();
/*    */   
/*    */   PartElementReference createPartElementReference();
/*    */   
/*    */   InteractionSpecification createInteractionSpecification();
/*    */   
/*    */   Interaction createInteraction();
/*    */   
/*    */   PortParameter createPortParameter();
/*    */   
/*    */   ExportBinding createExportBinding();
/*    */   
/*    */   PortParameterReference createPortParameterReference();
/*    */   
/*    */   InnerPortReference createInnerPortReference();
/*    */   
/*    */   ConditionalActualPortParameter createConditionalActualPortParameter();
/*    */   
/*    */   VariableExportBinding createVariableExportBinding();
/*    */   
/*    */   ConnectorType createConnectorType();
/*    */   
/*    */   InteractionsPackage getInteractionsPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\InteractionsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */