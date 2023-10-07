/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Behaviors.VariableDefinitionBinding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VariableDefinitionBindingImpl
/*     */   extends VariableBindingImpl
/*     */   implements VariableDefinitionBinding
/*     */ {
/*     */   protected Variable variable;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  63 */     return BehaviorsPackage.Literals.VARIABLE_DEFINITION_BINDING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getVariable() {
/*  73 */     if (this.variable != null && this.variable.eIsProxy()) {
/*     */       
/*  75 */       InternalEObject oldVariable = (InternalEObject)this.variable;
/*  76 */       this.variable = (Variable)eResolveProxy(oldVariable);
/*  77 */       if (this.variable != oldVariable)
/*     */       {
/*  79 */         if (eNotificationRequired())
/*  80 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 1, oldVariable, this.variable)); 
/*     */       }
/*     */     } 
/*  83 */     return this.variable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable basicGetVariable() {
/*  93 */     return this.variable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVariable(Variable newVariable) {
/* 103 */     Variable oldVariable = this.variable;
/* 104 */     this.variable = newVariable;
/* 105 */     if (eNotificationRequired()) {
/* 106 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldVariable, this.variable));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 117 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 120 */         if (resolve) return getVariable(); 
/* 121 */         return basicGetVariable();
/*     */     } 
/* 123 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 134 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 137 */         setVariable((Variable)newValue);
/*     */         return;
/*     */     } 
/* 140 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 151 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 154 */         setVariable((Variable)null);
/*     */         return;
/*     */     } 
/* 157 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 168 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 171 */         return (this.variable != null);
/*     */     } 
/* 173 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\VariableDefinitionBindingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */