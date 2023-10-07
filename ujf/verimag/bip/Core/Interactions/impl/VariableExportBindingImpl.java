/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.VariableBindingImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.VariableExportBinding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VariableExportBindingImpl
/*     */   extends VariableBindingImpl
/*     */   implements VariableExportBinding
/*     */ {
/*     */   protected PartElementReference targetInstance;
/*     */   protected InterfaceVariable targetInterfaceVariable;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  79 */     return InteractionsPackage.Literals.VARIABLE_EXPORT_BINDING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartElementReference getTargetInstance() {
/*  89 */     return this.targetInstance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTargetInstance(PartElementReference newTargetInstance, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  99 */     PartElementReference oldTargetInstance = this.targetInstance;
/* 100 */     this.targetInstance = newTargetInstance;
/* 101 */     if (eNotificationRequired()) {
/*     */       
/* 103 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldTargetInstance, newTargetInstance);
/* 104 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 106 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetInstance(PartElementReference newTargetInstance) {
/* 116 */     if (newTargetInstance != this.targetInstance) {
/*     */       
/* 118 */       NotificationChain msgs = null;
/* 119 */       if (this.targetInstance != null)
/* 120 */         msgs = ((InternalEObject)this.targetInstance).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 121 */       if (newTargetInstance != null)
/* 122 */         msgs = ((InternalEObject)newTargetInstance).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 123 */       msgs = basicSetTargetInstance(newTargetInstance, msgs);
/* 124 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 126 */     } else if (eNotificationRequired()) {
/* 127 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newTargetInstance, newTargetInstance));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable getTargetInterfaceVariable() {
/* 137 */     if (this.targetInterfaceVariable != null && this.targetInterfaceVariable.eIsProxy()) {
/*     */       
/* 139 */       InternalEObject oldTargetInterfaceVariable = (InternalEObject)this.targetInterfaceVariable;
/* 140 */       this.targetInterfaceVariable = (InterfaceVariable)eResolveProxy(oldTargetInterfaceVariable);
/* 141 */       if (this.targetInterfaceVariable != oldTargetInterfaceVariable)
/*     */       {
/* 143 */         if (eNotificationRequired())
/* 144 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 2, oldTargetInterfaceVariable, this.targetInterfaceVariable)); 
/*     */       }
/*     */     } 
/* 147 */     return this.targetInterfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable basicGetTargetInterfaceVariable() {
/* 157 */     return this.targetInterfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetInterfaceVariable(InterfaceVariable newTargetInterfaceVariable) {
/* 167 */     InterfaceVariable oldTargetInterfaceVariable = this.targetInterfaceVariable;
/* 168 */     this.targetInterfaceVariable = newTargetInterfaceVariable;
/* 169 */     if (eNotificationRequired()) {
/* 170 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldTargetInterfaceVariable, this.targetInterfaceVariable));
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
/* 181 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 184 */         return basicSetTargetInstance((PartElementReference)null, msgs);
/*     */     } 
/* 186 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 197 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 200 */         return getTargetInstance();
/*     */       case 2:
/* 202 */         if (resolve) return getTargetInterfaceVariable(); 
/* 203 */         return basicGetTargetInterfaceVariable();
/*     */     } 
/* 205 */     return super.eGet(featureID, resolve, coreType);
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
/* 216 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 219 */         setTargetInstance((PartElementReference)newValue);
/*     */         return;
/*     */       case 2:
/* 222 */         setTargetInterfaceVariable((InterfaceVariable)newValue);
/*     */         return;
/*     */     } 
/* 225 */     super.eSet(featureID, newValue);
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
/* 236 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 239 */         setTargetInstance((PartElementReference)null);
/*     */         return;
/*     */       case 2:
/* 242 */         setTargetInterfaceVariable((InterfaceVariable)null);
/*     */         return;
/*     */     } 
/* 245 */     super.eUnset(featureID);
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
/* 256 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 259 */         return (this.targetInstance != null);
/*     */       case 2:
/* 261 */         return (this.targetInterfaceVariable != null);
/*     */     } 
/* 263 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\VariableExportBindingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */