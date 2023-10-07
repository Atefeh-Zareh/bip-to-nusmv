/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerInterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InnerInterfaceVariableReferenceImpl
/*     */   extends DataReferenceImpl
/*     */   implements InnerInterfaceVariableReference
/*     */ {
/*     */   protected PartElementReference partElementReference;
/*     */   protected InterfaceVariable targetInterfaceVariable;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  78 */     return ExpressionsPackage.Literals.INNER_INTERFACE_VARIABLE_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartElementReference getPartElementReference() {
/*  88 */     return this.partElementReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetPartElementReference(PartElementReference newPartElementReference, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  98 */     PartElementReference oldPartElementReference = this.partElementReference;
/*  99 */     this.partElementReference = newPartElementReference;
/* 100 */     if (eNotificationRequired()) {
/*     */       
/* 102 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldPartElementReference, newPartElementReference);
/* 103 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 105 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartElementReference(PartElementReference newPartElementReference) {
/* 115 */     if (newPartElementReference != this.partElementReference) {
/*     */       
/* 117 */       NotificationChain msgs = null;
/* 118 */       if (this.partElementReference != null)
/* 119 */         msgs = ((InternalEObject)this.partElementReference).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 120 */       if (newPartElementReference != null)
/* 121 */         msgs = ((InternalEObject)newPartElementReference).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 122 */       msgs = basicSetPartElementReference(newPartElementReference, msgs);
/* 123 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 125 */     } else if (eNotificationRequired()) {
/* 126 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newPartElementReference, newPartElementReference));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable getTargetInterfaceVariable() {
/* 136 */     if (this.targetInterfaceVariable != null && this.targetInterfaceVariable.eIsProxy()) {
/*     */       
/* 138 */       InternalEObject oldTargetInterfaceVariable = (InternalEObject)this.targetInterfaceVariable;
/* 139 */       this.targetInterfaceVariable = (InterfaceVariable)eResolveProxy(oldTargetInterfaceVariable);
/* 140 */       if (this.targetInterfaceVariable != oldTargetInterfaceVariable)
/*     */       {
/* 142 */         if (eNotificationRequired())
/* 143 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 1, oldTargetInterfaceVariable, this.targetInterfaceVariable)); 
/*     */       }
/*     */     } 
/* 146 */     return this.targetInterfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable basicGetTargetInterfaceVariable() {
/* 156 */     return this.targetInterfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetInterfaceVariable(InterfaceVariable newTargetInterfaceVariable) {
/* 166 */     InterfaceVariable oldTargetInterfaceVariable = this.targetInterfaceVariable;
/* 167 */     this.targetInterfaceVariable = newTargetInterfaceVariable;
/* 168 */     if (eNotificationRequired()) {
/* 169 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldTargetInterfaceVariable, this.targetInterfaceVariable));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 180 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 183 */         return basicSetPartElementReference((PartElementReference)null, msgs);
/*     */     } 
/* 185 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 196 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 199 */         return getPartElementReference();
/*     */       case 1:
/* 201 */         if (resolve) return getTargetInterfaceVariable(); 
/* 202 */         return basicGetTargetInterfaceVariable();
/*     */     } 
/* 204 */     return super.eGet(featureID, resolve, coreType);
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
/* 215 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 218 */         setPartElementReference((PartElementReference)newValue);
/*     */         return;
/*     */       case 1:
/* 221 */         setTargetInterfaceVariable((InterfaceVariable)newValue);
/*     */         return;
/*     */     } 
/* 224 */     super.eSet(featureID, newValue);
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
/* 235 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 238 */         setPartElementReference((PartElementReference)null);
/*     */         return;
/*     */       case 1:
/* 241 */         setTargetInterfaceVariable((InterfaceVariable)null);
/*     */         return;
/*     */     } 
/* 244 */     super.eUnset(featureID);
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
/* 255 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 258 */         return (this.partElementReference != null);
/*     */       case 1:
/* 260 */         return (this.targetInterfaceVariable != null);
/*     */     } 
/* 262 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\InnerInterfaceVariableReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */