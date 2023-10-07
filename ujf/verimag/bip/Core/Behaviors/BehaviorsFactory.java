/*    */ package ujf.verimag.bip.Core.Behaviors;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.Behaviors.impl.BehaviorsFactoryImpl;
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
/*    */ public interface BehaviorsFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final BehaviorsFactory eINSTANCE = BehaviorsFactoryImpl.init();
/*    */   
/*    */   PortDefinition createPortDefinition();
/*    */   
/*    */   AtomType createAtomType();
/*    */   
/*    */   DataParameter createDataParameter();
/*    */   
/*    */   Variable createVariable();
/*    */   
/*    */   Port createPort();
/*    */   
/*    */   PortType createPortType();
/*    */   
/*    */   InterfaceVariable createInterfaceVariable();
/*    */   
/*    */   State createState();
/*    */   
/*    */   Transition createTransition();
/*    */   
/*    */   TransitionAlternative createTransitionAlternative();
/*    */   
/*    */   Constant createConstant();
/*    */   
/*    */   PetriNet createPetriNet();
/*    */   
/*    */   DefinitionBinding createDefinitionBinding();
/*    */   
/*    */   PortDefinitionReference createPortDefinitionReference();
/*    */   
/*    */   MultiTransition createMultiTransition();
/*    */   
/*    */   VariableDefinitionBinding createVariableDefinitionBinding();
/*    */   
/*    */   BehaviorsPackage getBehaviorsPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\BehaviorsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */