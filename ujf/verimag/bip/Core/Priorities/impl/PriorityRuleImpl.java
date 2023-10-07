/*     */ package ujf.verimag.bip.Core.Priorities.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Interactions.impl.MultiplicityElementImpl;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PriorityRuleImpl
/*     */   extends MultiplicityElementImpl
/*     */   implements PriorityRule
/*     */ {
/*     */   protected Expression guard;
/*     */   protected PriorityElement lower;
/*     */   protected PriorityElement greater;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  95 */     return PrioritiesPackage.Literals.PRIORITY_RULE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType getCompoundType() {
/* 105 */     if (eContainerFeatureID() != 3) return null; 
/* 106 */     return (ComponentType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCompoundType(ComponentType newCompoundType, NotificationChain msgs) {
/* 116 */     msgs = eBasicSetContainer((InternalEObject)newCompoundType, 3, msgs);
/* 117 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompoundType(ComponentType newCompoundType) {
/* 127 */     if (newCompoundType != eInternalContainer() || (eContainerFeatureID() != 3 && newCompoundType != null)) {
/*     */       
/* 129 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newCompoundType))
/* 130 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 131 */       NotificationChain msgs = null;
/* 132 */       if (eInternalContainer() != null)
/* 133 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 134 */       if (newCompoundType != null)
/* 135 */         msgs = ((InternalEObject)newCompoundType).eInverseAdd((InternalEObject)this, 8, ComponentType.class, msgs); 
/* 136 */       msgs = basicSetCompoundType(newCompoundType, msgs);
/* 137 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 139 */     } else if (eNotificationRequired()) {
/* 140 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newCompoundType, newCompoundType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getGuard() {
/* 150 */     return this.guard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetGuard(Expression newGuard, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 160 */     Expression oldGuard = this.guard;
/* 161 */     this.guard = newGuard;
/* 162 */     if (eNotificationRequired()) {
/*     */       
/* 164 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 4, oldGuard, newGuard);
/* 165 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 167 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGuard(Expression newGuard) {
/* 177 */     if (newGuard != this.guard) {
/*     */       
/* 179 */       NotificationChain msgs = null;
/* 180 */       if (this.guard != null)
/* 181 */         msgs = ((InternalEObject)this.guard).eInverseRemove((InternalEObject)this, -5, null, msgs); 
/* 182 */       if (newGuard != null)
/* 183 */         msgs = ((InternalEObject)newGuard).eInverseAdd((InternalEObject)this, -5, null, msgs); 
/* 184 */       msgs = basicSetGuard(newGuard, msgs);
/* 185 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 187 */     } else if (eNotificationRequired()) {
/* 188 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newGuard, newGuard));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PriorityElement getLower() {
/* 198 */     return this.lower;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetLower(PriorityElement newLower, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 208 */     PriorityElement oldLower = this.lower;
/* 209 */     this.lower = newLower;
/* 210 */     if (eNotificationRequired()) {
/*     */       
/* 212 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 5, oldLower, newLower);
/* 213 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 215 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLower(PriorityElement newLower) {
/* 225 */     if (newLower != this.lower) {
/*     */       
/* 227 */       NotificationChain msgs = null;
/* 228 */       if (this.lower != null)
/* 229 */         msgs = ((InternalEObject)this.lower).eInverseRemove((InternalEObject)this, -6, null, msgs); 
/* 230 */       if (newLower != null)
/* 231 */         msgs = ((InternalEObject)newLower).eInverseAdd((InternalEObject)this, -6, null, msgs); 
/* 232 */       msgs = basicSetLower(newLower, msgs);
/* 233 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 235 */     } else if (eNotificationRequired()) {
/* 236 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, newLower, newLower));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PriorityElement getGreater() {
/* 246 */     return this.greater;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetGreater(PriorityElement newGreater, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 256 */     PriorityElement oldGreater = this.greater;
/* 257 */     this.greater = newGreater;
/* 258 */     if (eNotificationRequired()) {
/*     */       
/* 260 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 6, oldGreater, newGreater);
/* 261 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 263 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGreater(PriorityElement newGreater) {
/* 273 */     if (newGreater != this.greater) {
/*     */       
/* 275 */       NotificationChain msgs = null;
/* 276 */       if (this.greater != null)
/* 277 */         msgs = ((InternalEObject)this.greater).eInverseRemove((InternalEObject)this, -7, null, msgs); 
/* 278 */       if (newGreater != null)
/* 279 */         msgs = ((InternalEObject)newGreater).eInverseAdd((InternalEObject)this, -7, null, msgs); 
/* 280 */       msgs = basicSetGreater(newGreater, msgs);
/* 281 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 283 */     } else if (eNotificationRequired()) {
/* 284 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, newGreater, newGreater));
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
/* 295 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 298 */         if (eInternalContainer() != null)
/* 299 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 300 */         return basicSetCompoundType((ComponentType)otherEnd, msgs);
/*     */     } 
/* 302 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 313 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 316 */         return basicSetCompoundType((ComponentType)null, msgs);
/*     */       case 4:
/* 318 */         return basicSetGuard((Expression)null, msgs);
/*     */       case 5:
/* 320 */         return basicSetLower((PriorityElement)null, msgs);
/*     */       case 6:
/* 322 */         return basicSetGreater((PriorityElement)null, msgs);
/*     */     } 
/* 324 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 335 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 3:
/* 338 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 8, ComponentType.class, msgs);
/*     */     } 
/* 340 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 351 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 354 */         return getCompoundType();
/*     */       case 4:
/* 356 */         return getGuard();
/*     */       case 5:
/* 358 */         return getLower();
/*     */       case 6:
/* 360 */         return getGreater();
/*     */     } 
/* 362 */     return super.eGet(featureID, resolve, coreType);
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
/* 373 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 376 */         setCompoundType((ComponentType)newValue);
/*     */         return;
/*     */       case 4:
/* 379 */         setGuard((Expression)newValue);
/*     */         return;
/*     */       case 5:
/* 382 */         setLower((PriorityElement)newValue);
/*     */         return;
/*     */       case 6:
/* 385 */         setGreater((PriorityElement)newValue);
/*     */         return;
/*     */     } 
/* 388 */     super.eSet(featureID, newValue);
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
/* 399 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 402 */         setCompoundType((ComponentType)null);
/*     */         return;
/*     */       case 4:
/* 405 */         setGuard((Expression)null);
/*     */         return;
/*     */       case 5:
/* 408 */         setLower((PriorityElement)null);
/*     */         return;
/*     */       case 6:
/* 411 */         setGreater((PriorityElement)null);
/*     */         return;
/*     */     } 
/* 414 */     super.eUnset(featureID);
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
/* 425 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 428 */         return (getCompoundType() != null);
/*     */       case 4:
/* 430 */         return (this.guard != null);
/*     */       case 5:
/* 432 */         return (this.lower != null);
/*     */       case 6:
/* 434 */         return (this.greater != null);
/*     */     } 
/* 436 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\impl\PriorityRuleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */