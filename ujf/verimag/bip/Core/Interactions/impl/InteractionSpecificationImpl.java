/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InteractionSpecificationImpl
/*     */   extends EObjectImpl
/*     */   implements InteractionSpecification
/*     */ {
/*     */   protected Interaction interaction;
/*     */   protected Expression guard;
/*     */   protected Action downAction;
/*     */   protected Action upAction;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 105 */     return InteractionsPackage.Literals.INTERACTION_SPECIFICATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getConnectorType() {
/* 115 */     if (eContainerFeatureID() != 0) return null; 
/* 116 */     return (ConnectorType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConnectorType(ConnectorType newConnectorType, NotificationChain msgs) {
/* 126 */     msgs = eBasicSetContainer((InternalEObject)newConnectorType, 0, msgs);
/* 127 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectorType(ConnectorType newConnectorType) {
/* 137 */     if (newConnectorType != eInternalContainer() || (eContainerFeatureID() != 0 && newConnectorType != null)) {
/*     */       
/* 139 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newConnectorType))
/* 140 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 141 */       NotificationChain msgs = null;
/* 142 */       if (eInternalContainer() != null)
/* 143 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 144 */       if (newConnectorType != null)
/* 145 */         msgs = ((InternalEObject)newConnectorType).eInverseAdd((InternalEObject)this, 8, ConnectorType.class, msgs); 
/* 146 */       msgs = basicSetConnectorType(newConnectorType, msgs);
/* 147 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 149 */     } else if (eNotificationRequired()) {
/* 150 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newConnectorType, newConnectorType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Interaction getInteraction() {
/* 160 */     return this.interaction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInteraction(Interaction newInteraction, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 170 */     Interaction oldInteraction = this.interaction;
/* 171 */     this.interaction = newInteraction;
/* 172 */     if (eNotificationRequired()) {
/*     */       
/* 174 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldInteraction, newInteraction);
/* 175 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 177 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteraction(Interaction newInteraction) {
/* 187 */     if (newInteraction != this.interaction) {
/*     */       
/* 189 */       NotificationChain msgs = null;
/* 190 */       if (this.interaction != null)
/* 191 */         msgs = ((InternalEObject)this.interaction).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 192 */       if (newInteraction != null)
/* 193 */         msgs = ((InternalEObject)newInteraction).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 194 */       msgs = basicSetInteraction(newInteraction, msgs);
/* 195 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 197 */     } else if (eNotificationRequired()) {
/* 198 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newInteraction, newInteraction));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getGuard() {
/* 208 */     return this.guard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetGuard(Expression newGuard, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 218 */     Expression oldGuard = this.guard;
/* 219 */     this.guard = newGuard;
/* 220 */     if (eNotificationRequired()) {
/*     */       
/* 222 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldGuard, newGuard);
/* 223 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 225 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGuard(Expression newGuard) {
/* 235 */     if (newGuard != this.guard) {
/*     */       
/* 237 */       NotificationChain msgs = null;
/* 238 */       if (this.guard != null)
/* 239 */         msgs = ((InternalEObject)this.guard).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 240 */       if (newGuard != null)
/* 241 */         msgs = ((InternalEObject)newGuard).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 242 */       msgs = basicSetGuard(newGuard, msgs);
/* 243 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 245 */     } else if (eNotificationRequired()) {
/* 246 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newGuard, newGuard));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getDownAction() {
/* 256 */     return this.downAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetDownAction(Action newDownAction, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 266 */     Action oldDownAction = this.downAction;
/* 267 */     this.downAction = newDownAction;
/* 268 */     if (eNotificationRequired()) {
/*     */       
/* 270 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 3, oldDownAction, newDownAction);
/* 271 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 273 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDownAction(Action newDownAction) {
/* 283 */     if (newDownAction != this.downAction) {
/*     */       
/* 285 */       NotificationChain msgs = null;
/* 286 */       if (this.downAction != null)
/* 287 */         msgs = ((InternalEObject)this.downAction).eInverseRemove((InternalEObject)this, -4, null, msgs); 
/* 288 */       if (newDownAction != null)
/* 289 */         msgs = ((InternalEObject)newDownAction).eInverseAdd((InternalEObject)this, -4, null, msgs); 
/* 290 */       msgs = basicSetDownAction(newDownAction, msgs);
/* 291 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 293 */     } else if (eNotificationRequired()) {
/* 294 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newDownAction, newDownAction));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getUpAction() {
/* 304 */     return this.upAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetUpAction(Action newUpAction, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 314 */     Action oldUpAction = this.upAction;
/* 315 */     this.upAction = newUpAction;
/* 316 */     if (eNotificationRequired()) {
/*     */       
/* 318 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 4, oldUpAction, newUpAction);
/* 319 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 321 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpAction(Action newUpAction) {
/* 331 */     if (newUpAction != this.upAction) {
/*     */       
/* 333 */       NotificationChain msgs = null;
/* 334 */       if (this.upAction != null)
/* 335 */         msgs = ((InternalEObject)this.upAction).eInverseRemove((InternalEObject)this, -5, null, msgs); 
/* 336 */       if (newUpAction != null)
/* 337 */         msgs = ((InternalEObject)newUpAction).eInverseAdd((InternalEObject)this, -5, null, msgs); 
/* 338 */       msgs = basicSetUpAction(newUpAction, msgs);
/* 339 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 341 */     } else if (eNotificationRequired()) {
/* 342 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newUpAction, newUpAction));
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
/* 353 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 356 */         if (eInternalContainer() != null)
/* 357 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 358 */         return basicSetConnectorType((ConnectorType)otherEnd, msgs);
/*     */     } 
/* 360 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 371 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 374 */         return basicSetConnectorType((ConnectorType)null, msgs);
/*     */       case 1:
/* 376 */         return basicSetInteraction((Interaction)null, msgs);
/*     */       case 2:
/* 378 */         return basicSetGuard((Expression)null, msgs);
/*     */       case 3:
/* 380 */         return basicSetDownAction((Action)null, msgs);
/*     */       case 4:
/* 382 */         return basicSetUpAction((Action)null, msgs);
/*     */     } 
/* 384 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 395 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 0:
/* 398 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 8, ConnectorType.class, msgs);
/*     */     } 
/* 400 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 411 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 414 */         return getConnectorType();
/*     */       case 1:
/* 416 */         return getInteraction();
/*     */       case 2:
/* 418 */         return getGuard();
/*     */       case 3:
/* 420 */         return getDownAction();
/*     */       case 4:
/* 422 */         return getUpAction();
/*     */     } 
/* 424 */     return super.eGet(featureID, resolve, coreType);
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
/* 435 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 438 */         setConnectorType((ConnectorType)newValue);
/*     */         return;
/*     */       case 1:
/* 441 */         setInteraction((Interaction)newValue);
/*     */         return;
/*     */       case 2:
/* 444 */         setGuard((Expression)newValue);
/*     */         return;
/*     */       case 3:
/* 447 */         setDownAction((Action)newValue);
/*     */         return;
/*     */       case 4:
/* 450 */         setUpAction((Action)newValue);
/*     */         return;
/*     */     } 
/* 453 */     super.eSet(featureID, newValue);
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
/* 464 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 467 */         setConnectorType((ConnectorType)null);
/*     */         return;
/*     */       case 1:
/* 470 */         setInteraction((Interaction)null);
/*     */         return;
/*     */       case 2:
/* 473 */         setGuard((Expression)null);
/*     */         return;
/*     */       case 3:
/* 476 */         setDownAction((Action)null);
/*     */         return;
/*     */       case 4:
/* 479 */         setUpAction((Action)null);
/*     */         return;
/*     */     } 
/* 482 */     super.eUnset(featureID);
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
/* 493 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 496 */         return (getConnectorType() != null);
/*     */       case 1:
/* 498 */         return (this.interaction != null);
/*     */       case 2:
/* 500 */         return (this.guard != null);
/*     */       case 3:
/* 502 */         return (this.downAction != null);
/*     */       case 4:
/* 504 */         return (this.upAction != null);
/*     */     } 
/* 506 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\InteractionSpecificationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */