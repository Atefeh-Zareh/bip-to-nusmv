/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortImpl
/*     */   extends NamedElementImpl
/*     */   implements Port
/*     */ {
/*     */   protected Binding binding;
/*     */   protected PortType type;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  84 */     return BehaviorsPackage.Literals.PORT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType getComponentType() {
/*  94 */     if (eContainerFeatureID() != 2) return null; 
/*  95 */     return (ComponentType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetComponentType(ComponentType newComponentType, NotificationChain msgs) {
/* 105 */     msgs = eBasicSetContainer((InternalEObject)newComponentType, 2, msgs);
/* 106 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentType(ComponentType newComponentType) {
/* 116 */     if (newComponentType != eInternalContainer() || (eContainerFeatureID() != 2 && newComponentType != null)) {
/*     */       
/* 118 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newComponentType))
/* 119 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 120 */       NotificationChain msgs = null;
/* 121 */       if (eInternalContainer() != null)
/* 122 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 123 */       if (newComponentType != null)
/* 124 */         msgs = ((InternalEObject)newComponentType).eInverseAdd((InternalEObject)this, 7, ComponentType.class, msgs); 
/* 125 */       msgs = basicSetComponentType(newComponentType, msgs);
/* 126 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 128 */     } else if (eNotificationRequired()) {
/* 129 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newComponentType, newComponentType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Binding getBinding() {
/* 139 */     return this.binding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetBinding(Binding newBinding, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 149 */     Binding oldBinding = this.binding;
/* 150 */     this.binding = newBinding;
/* 151 */     if (eNotificationRequired()) {
/*     */       
/* 153 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 3, oldBinding, newBinding);
/* 154 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 156 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBinding(Binding newBinding) {
/* 166 */     if (newBinding != this.binding) {
/*     */       
/* 168 */       NotificationChain msgs = null;
/* 169 */       if (this.binding != null)
/* 170 */         msgs = ((InternalEObject)this.binding).eInverseRemove((InternalEObject)this, 0, Binding.class, msgs); 
/* 171 */       if (newBinding != null)
/* 172 */         msgs = ((InternalEObject)newBinding).eInverseAdd((InternalEObject)this, 0, Binding.class, msgs); 
/* 173 */       msgs = basicSetBinding(newBinding, msgs);
/* 174 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 176 */     } else if (eNotificationRequired()) {
/* 177 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newBinding, newBinding));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType getType() {
/* 187 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/* 189 */       InternalEObject oldType = (InternalEObject)this.type;
/* 190 */       this.type = (PortType)eResolveProxy(oldType);
/* 191 */       if (this.type != oldType)
/*     */       {
/* 193 */         if (eNotificationRequired())
/* 194 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 4, oldType, this.type)); 
/*     */       }
/*     */     } 
/* 197 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType basicGetType() {
/* 207 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(PortType newType) {
/* 217 */     PortType oldType = this.type;
/* 218 */     this.type = newType;
/* 219 */     if (eNotificationRequired()) {
/* 220 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getConnectorType() {
/* 230 */     if (eContainerFeatureID() != 5) return null; 
/* 231 */     return (ConnectorType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConnectorType(ConnectorType newConnectorType, NotificationChain msgs) {
/* 241 */     msgs = eBasicSetContainer((InternalEObject)newConnectorType, 5, msgs);
/* 242 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectorType(ConnectorType newConnectorType) {
/* 252 */     if (newConnectorType != eInternalContainer() || (eContainerFeatureID() != 5 && newConnectorType != null)) {
/*     */       
/* 254 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newConnectorType))
/* 255 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 256 */       NotificationChain msgs = null;
/* 257 */       if (eInternalContainer() != null)
/* 258 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 259 */       if (newConnectorType != null)
/* 260 */         msgs = ((InternalEObject)newConnectorType).eInverseAdd((InternalEObject)this, 7, ConnectorType.class, msgs); 
/* 261 */       msgs = basicSetConnectorType(newConnectorType, msgs);
/* 262 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 264 */     } else if (eNotificationRequired()) {
/* 265 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, newConnectorType, newConnectorType));
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
/* 276 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 279 */         if (eInternalContainer() != null)
/* 280 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 281 */         return basicSetComponentType((ComponentType)otherEnd, msgs);
/*     */       case 3:
/* 283 */         if (this.binding != null)
/* 284 */           msgs = ((InternalEObject)this.binding).eInverseRemove((InternalEObject)this, -4, null, msgs); 
/* 285 */         return basicSetBinding((Binding)otherEnd, msgs);
/*     */       case 5:
/* 287 */         if (eInternalContainer() != null)
/* 288 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 289 */         return basicSetConnectorType((ConnectorType)otherEnd, msgs);
/*     */     } 
/* 291 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 302 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 305 */         return basicSetComponentType((ComponentType)null, msgs);
/*     */       case 3:
/* 307 */         return basicSetBinding((Binding)null, msgs);
/*     */       case 5:
/* 309 */         return basicSetConnectorType((ConnectorType)null, msgs);
/*     */     } 
/* 311 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 322 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 2:
/* 325 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 7, ComponentType.class, msgs);
/*     */       case 5:
/* 327 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 7, ConnectorType.class, msgs);
/*     */     } 
/* 329 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 340 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 343 */         return getComponentType();
/*     */       case 3:
/* 345 */         return getBinding();
/*     */       case 4:
/* 347 */         if (resolve) return getType(); 
/* 348 */         return basicGetType();
/*     */       case 5:
/* 350 */         return getConnectorType();
/*     */     } 
/* 352 */     return super.eGet(featureID, resolve, coreType);
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
/* 363 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 366 */         setComponentType((ComponentType)newValue);
/*     */         return;
/*     */       case 3:
/* 369 */         setBinding((Binding)newValue);
/*     */         return;
/*     */       case 4:
/* 372 */         setType((PortType)newValue);
/*     */         return;
/*     */       case 5:
/* 375 */         setConnectorType((ConnectorType)newValue);
/*     */         return;
/*     */     } 
/* 378 */     super.eSet(featureID, newValue);
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
/* 389 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 392 */         setComponentType((ComponentType)null);
/*     */         return;
/*     */       case 3:
/* 395 */         setBinding((Binding)null);
/*     */         return;
/*     */       case 4:
/* 398 */         setType((PortType)null);
/*     */         return;
/*     */       case 5:
/* 401 */         setConnectorType((ConnectorType)null);
/*     */         return;
/*     */     } 
/* 404 */     super.eUnset(featureID);
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
/* 415 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 418 */         return (getComponentType() != null);
/*     */       case 3:
/* 420 */         return (this.binding != null);
/*     */       case 4:
/* 422 */         return (this.type != null);
/*     */       case 5:
/* 424 */         return (getConnectorType() != null);
/*     */     } 
/* 426 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\PortImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */