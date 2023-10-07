/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InterfaceVariableReferenceImpl
/*     */   extends DataReferenceImpl
/*     */   implements InterfaceVariableReference
/*     */ {
/*     */   protected InterfaceVariable targetInterfaceVariable;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  64 */     return ExpressionsPackage.Literals.INTERFACE_VARIABLE_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable getTargetInterfaceVariable() {
/*  74 */     if (this.targetInterfaceVariable != null && this.targetInterfaceVariable.eIsProxy()) {
/*     */       
/*  76 */       InternalEObject oldTargetInterfaceVariable = (InternalEObject)this.targetInterfaceVariable;
/*  77 */       this.targetInterfaceVariable = (InterfaceVariable)eResolveProxy(oldTargetInterfaceVariable);
/*  78 */       if (this.targetInterfaceVariable != oldTargetInterfaceVariable)
/*     */       {
/*  80 */         if (eNotificationRequired())
/*  81 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTargetInterfaceVariable, this.targetInterfaceVariable)); 
/*     */       }
/*     */     } 
/*  84 */     return this.targetInterfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable basicGetTargetInterfaceVariable() {
/*  94 */     return this.targetInterfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetInterfaceVariable(InterfaceVariable newTargetInterfaceVariable) {
/* 104 */     InterfaceVariable oldTargetInterfaceVariable = this.targetInterfaceVariable;
/* 105 */     this.targetInterfaceVariable = newTargetInterfaceVariable;
/* 106 */     if (eNotificationRequired()) {
/* 107 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTargetInterfaceVariable, this.targetInterfaceVariable));
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
/* 121 */         if (resolve) return getTargetInterfaceVariable(); 
/* 122 */         return basicGetTargetInterfaceVariable();
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
/* 138 */         setTargetInterfaceVariable((InterfaceVariable)newValue);
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
/* 155 */         setTargetInterfaceVariable((InterfaceVariable)null);
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
/* 172 */         return (this.targetInterfaceVariable != null);
/*     */     } 
/* 174 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\InterfaceVariableReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */