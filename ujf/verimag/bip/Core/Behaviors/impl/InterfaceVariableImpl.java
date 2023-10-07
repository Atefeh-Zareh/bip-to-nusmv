/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InterfaceVariableImpl
/*     */   extends DataTypedElementImpl
/*     */   implements InterfaceVariable
/*     */ {
/*     */   protected EList<VariableBinding> variableBinding;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  74 */     return BehaviorsPackage.Literals.INTERFACE_VARIABLE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType getComponentType() {
/*  84 */     if (eContainerFeatureID() != 4) return null; 
/*  85 */     return (ComponentType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetComponentType(ComponentType newComponentType, NotificationChain msgs) {
/*  95 */     msgs = eBasicSetContainer((InternalEObject)newComponentType, 4, msgs);
/*  96 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentType(ComponentType newComponentType) {
/* 106 */     if (newComponentType != eInternalContainer() || (eContainerFeatureID() != 4 && newComponentType != null)) {
/*     */       
/* 108 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newComponentType))
/* 109 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 110 */       NotificationChain msgs = null;
/* 111 */       if (eInternalContainer() != null)
/* 112 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 113 */       if (newComponentType != null)
/* 114 */         msgs = ((InternalEObject)newComponentType).eInverseAdd((InternalEObject)this, 9, ComponentType.class, msgs); 
/* 115 */       msgs = basicSetComponentType(newComponentType, msgs);
/* 116 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 118 */     } else if (eNotificationRequired()) {
/* 119 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newComponentType, newComponentType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<VariableBinding> getVariableBinding() {
/* 129 */     if (this.variableBinding == null)
/*     */     {
/* 131 */       this.variableBinding = (EList<VariableBinding>)new EObjectContainmentWithInverseEList(VariableBinding.class, (InternalEObject)this, 5, 0);
/*     */     }
/* 133 */     return this.variableBinding;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 145 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 148 */         if (eInternalContainer() != null)
/* 149 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 150 */         return basicSetComponentType((ComponentType)otherEnd, msgs);
/*     */       case 5:
/* 152 */         return ((InternalEList)getVariableBinding()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 154 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 165 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 168 */         return basicSetComponentType((ComponentType)null, msgs);
/*     */       case 5:
/* 170 */         return ((InternalEList)getVariableBinding()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 172 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 183 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 4:
/* 186 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 9, ComponentType.class, msgs);
/*     */     } 
/* 188 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 199 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 202 */         return getComponentType();
/*     */       case 5:
/* 204 */         return getVariableBinding();
/*     */     } 
/* 206 */     return super.eGet(featureID, resolve, coreType);
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
/*     */   public void eSet(int featureID, Object newValue) {
/* 218 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 221 */         setComponentType((ComponentType)newValue);
/*     */         return;
/*     */       case 5:
/* 224 */         getVariableBinding().clear();
/* 225 */         getVariableBinding().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 228 */     super.eSet(featureID, newValue);
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
/* 239 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 242 */         setComponentType((ComponentType)null);
/*     */         return;
/*     */       case 5:
/* 245 */         getVariableBinding().clear();
/*     */         return;
/*     */     } 
/* 248 */     super.eUnset(featureID);
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
/* 259 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 262 */         return (getComponentType() != null);
/*     */       case 5:
/* 264 */         return (this.variableBinding != null && !this.variableBinding.isEmpty());
/*     */     } 
/* 266 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\InterfaceVariableImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */