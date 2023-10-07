/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VariableReferenceImpl
/*     */   extends DataReferenceImpl
/*     */   implements VariableReference
/*     */ {
/*     */   protected Variable targetVariable;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  64 */     return ExpressionsPackage.Literals.VARIABLE_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getTargetVariable() {
/*  74 */     if (this.targetVariable != null && this.targetVariable.eIsProxy()) {
/*     */       
/*  76 */       InternalEObject oldTargetVariable = (InternalEObject)this.targetVariable;
/*  77 */       this.targetVariable = (Variable)eResolveProxy(oldTargetVariable);
/*  78 */       if (this.targetVariable != oldTargetVariable)
/*     */       {
/*  80 */         if (eNotificationRequired())
/*  81 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTargetVariable, this.targetVariable)); 
/*     */       }
/*     */     } 
/*  84 */     return this.targetVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable basicGetTargetVariable() {
/*  94 */     return this.targetVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetVariable(Variable newTargetVariable) {
/* 104 */     Variable oldTargetVariable = this.targetVariable;
/* 105 */     this.targetVariable = newTargetVariable;
/* 106 */     if (eNotificationRequired()) {
/* 107 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTargetVariable, this.targetVariable));
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
/* 118 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 121 */         if (resolve) return getTargetVariable(); 
/* 122 */         return basicGetTargetVariable();
/*     */     } 
/* 124 */     return super.eGet(featureID, resolve, coreType);
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
/* 135 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 138 */         setTargetVariable((Variable)newValue);
/*     */         return;
/*     */     } 
/* 141 */     super.eSet(featureID, newValue);
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
/* 152 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 155 */         setTargetVariable((Variable)null);
/*     */         return;
/*     */     } 
/* 158 */     super.eUnset(featureID);
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
/* 169 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 172 */         return (this.targetVariable != null);
/*     */     } 
/* 174 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\VariableReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */