/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Behaviors.VariableBinding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class VariableBindingImpl
/*     */   extends EObjectImpl
/*     */   implements VariableBinding
/*     */ {
/*     */   protected EClass eStaticClass() {
/*  57 */     return BehaviorsPackage.Literals.VARIABLE_BINDING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariable getInterfaceVariable() {
/*  67 */     if (eContainerFeatureID() != 0) return null; 
/*  68 */     return (InterfaceVariable)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInterfaceVariable(InterfaceVariable newInterfaceVariable, NotificationChain msgs) {
/*  78 */     msgs = eBasicSetContainer((InternalEObject)newInterfaceVariable, 0, msgs);
/*  79 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterfaceVariable(InterfaceVariable newInterfaceVariable) {
/*  89 */     if (newInterfaceVariable != eInternalContainer() || (eContainerFeatureID() != 0 && newInterfaceVariable != null)) {
/*     */       
/*  91 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newInterfaceVariable))
/*  92 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/*  93 */       NotificationChain msgs = null;
/*  94 */       if (eInternalContainer() != null)
/*  95 */         msgs = eBasicRemoveFromContainer(msgs); 
/*  96 */       if (newInterfaceVariable != null)
/*  97 */         msgs = ((InternalEObject)newInterfaceVariable).eInverseAdd((InternalEObject)this, 5, InterfaceVariable.class, msgs); 
/*  98 */       msgs = basicSetInterfaceVariable(newInterfaceVariable, msgs);
/*  99 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 101 */     } else if (eNotificationRequired()) {
/* 102 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newInterfaceVariable, newInterfaceVariable));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 113 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 116 */         if (eInternalContainer() != null)
/* 117 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 118 */         return basicSetInterfaceVariable((InterfaceVariable)otherEnd, msgs);
/*     */     } 
/* 120 */     return super.eInverseAdd(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 131 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 134 */         return basicSetInterfaceVariable((InterfaceVariable)null, msgs);
/*     */     } 
/* 136 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 147 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 0:
/* 150 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 5, InterfaceVariable.class, msgs);
/*     */     } 
/* 152 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 163 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 166 */         return getInterfaceVariable();
/*     */     } 
/* 168 */     return super.eGet(featureID, resolve, coreType);
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
/* 179 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 182 */         setInterfaceVariable((InterfaceVariable)newValue);
/*     */         return;
/*     */     } 
/* 185 */     super.eSet(featureID, newValue);
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
/* 196 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 199 */         setInterfaceVariable((InterfaceVariable)null);
/*     */         return;
/*     */     } 
/* 202 */     super.eUnset(featureID);
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
/* 213 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 216 */         return (getInterfaceVariable() != null);
/*     */     } 
/* 218 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\VariableBindingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */