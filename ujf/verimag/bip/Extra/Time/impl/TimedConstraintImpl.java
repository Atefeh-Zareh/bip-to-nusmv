/*     */ package ujf.verimag.bip.Extra.Time.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimedConstraintImpl
/*     */   extends EObjectImpl
/*     */   implements TimedConstraint
/*     */ {
/*     */   protected VariableReference clock;
/*     */   protected Expression lowBound;
/*     */   protected Expression highBound;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  94 */     return TimePackage.Literals.TIMED_CONSTRAINT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableReference getClock() {
/* 104 */     return this.clock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetClock(VariableReference newClock, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 114 */     VariableReference oldClock = this.clock;
/* 115 */     this.clock = newClock;
/* 116 */     if (eNotificationRequired()) {
/*     */       
/* 118 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldClock, newClock);
/* 119 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 121 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClock(VariableReference newClock) {
/* 131 */     if (newClock != this.clock) {
/*     */       
/* 133 */       NotificationChain msgs = null;
/* 134 */       if (this.clock != null)
/* 135 */         msgs = ((InternalEObject)this.clock).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 136 */       if (newClock != null)
/* 137 */         msgs = ((InternalEObject)newClock).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 138 */       msgs = basicSetClock(newClock, msgs);
/* 139 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 141 */     } else if (eNotificationRequired()) {
/* 142 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newClock, newClock));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getLowBound() {
/* 152 */     return this.lowBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetLowBound(Expression newLowBound, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 162 */     Expression oldLowBound = this.lowBound;
/* 163 */     this.lowBound = newLowBound;
/* 164 */     if (eNotificationRequired()) {
/*     */       
/* 166 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldLowBound, newLowBound);
/* 167 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 169 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLowBound(Expression newLowBound) {
/* 179 */     if (newLowBound != this.lowBound) {
/*     */       
/* 181 */       NotificationChain msgs = null;
/* 182 */       if (this.lowBound != null)
/* 183 */         msgs = ((InternalEObject)this.lowBound).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 184 */       if (newLowBound != null)
/* 185 */         msgs = ((InternalEObject)newLowBound).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 186 */       msgs = basicSetLowBound(newLowBound, msgs);
/* 187 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 189 */     } else if (eNotificationRequired()) {
/* 190 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newLowBound, newLowBound));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getHighBound() {
/* 200 */     return this.highBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetHighBound(Expression newHighBound, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 210 */     Expression oldHighBound = this.highBound;
/* 211 */     this.highBound = newHighBound;
/* 212 */     if (eNotificationRequired()) {
/*     */       
/* 214 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldHighBound, newHighBound);
/* 215 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 217 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHighBound(Expression newHighBound) {
/* 227 */     if (newHighBound != this.highBound) {
/*     */       
/* 229 */       NotificationChain msgs = null;
/* 230 */       if (this.highBound != null)
/* 231 */         msgs = ((InternalEObject)this.highBound).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 232 */       if (newHighBound != null)
/* 233 */         msgs = ((InternalEObject)newHighBound).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 234 */       msgs = basicSetHighBound(newHighBound, msgs);
/* 235 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 237 */     } else if (eNotificationRequired()) {
/* 238 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newHighBound, newHighBound));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeSpecification getTimeSpecification() {
/* 248 */     if (eContainerFeatureID() != 3) return null; 
/* 249 */     return (TimeSpecification)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTimeSpecification(TimeSpecification newTimeSpecification, NotificationChain msgs) {
/* 259 */     msgs = eBasicSetContainer((InternalEObject)newTimeSpecification, 3, msgs);
/* 260 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSpecification(TimeSpecification newTimeSpecification) {
/* 270 */     if (newTimeSpecification != eInternalContainer() || (eContainerFeatureID() != 3 && newTimeSpecification != null)) {
/*     */       
/* 272 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newTimeSpecification))
/* 273 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 274 */       NotificationChain msgs = null;
/* 275 */       if (eInternalContainer() != null)
/* 276 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 277 */       if (newTimeSpecification != null)
/* 278 */         msgs = ((InternalEObject)newTimeSpecification).eInverseAdd((InternalEObject)this, 2, TimeSpecification.class, msgs); 
/* 279 */       msgs = basicSetTimeSpecification(newTimeSpecification, msgs);
/* 280 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 282 */     } else if (eNotificationRequired()) {
/* 283 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newTimeSpecification, newTimeSpecification));
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
/* 294 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 297 */         if (eInternalContainer() != null)
/* 298 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 299 */         return basicSetTimeSpecification((TimeSpecification)otherEnd, msgs);
/*     */     } 
/* 301 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 312 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 315 */         return basicSetClock((VariableReference)null, msgs);
/*     */       case 1:
/* 317 */         return basicSetLowBound((Expression)null, msgs);
/*     */       case 2:
/* 319 */         return basicSetHighBound((Expression)null, msgs);
/*     */       case 3:
/* 321 */         return basicSetTimeSpecification((TimeSpecification)null, msgs);
/*     */     } 
/* 323 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 334 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 3:
/* 337 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 2, TimeSpecification.class, msgs);
/*     */     } 
/* 339 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 350 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 353 */         return getClock();
/*     */       case 1:
/* 355 */         return getLowBound();
/*     */       case 2:
/* 357 */         return getHighBound();
/*     */       case 3:
/* 359 */         return getTimeSpecification();
/*     */     } 
/* 361 */     return super.eGet(featureID, resolve, coreType);
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
/* 372 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 375 */         setClock((VariableReference)newValue);
/*     */         return;
/*     */       case 1:
/* 378 */         setLowBound((Expression)newValue);
/*     */         return;
/*     */       case 2:
/* 381 */         setHighBound((Expression)newValue);
/*     */         return;
/*     */       case 3:
/* 384 */         setTimeSpecification((TimeSpecification)newValue);
/*     */         return;
/*     */     } 
/* 387 */     super.eSet(featureID, newValue);
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
/* 398 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 401 */         setClock((VariableReference)null);
/*     */         return;
/*     */       case 1:
/* 404 */         setLowBound((Expression)null);
/*     */         return;
/*     */       case 2:
/* 407 */         setHighBound((Expression)null);
/*     */         return;
/*     */       case 3:
/* 410 */         setTimeSpecification((TimeSpecification)null);
/*     */         return;
/*     */     } 
/* 413 */     super.eUnset(featureID);
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
/* 424 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 427 */         return (this.clock != null);
/*     */       case 1:
/* 429 */         return (this.lowBound != null);
/*     */       case 2:
/* 431 */         return (this.highBound != null);
/*     */       case 3:
/* 433 */         return (getTimeSpecification() != null);
/*     */     } 
/* 435 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\impl\TimedConstraintImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */