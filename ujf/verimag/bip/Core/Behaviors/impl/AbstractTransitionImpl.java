/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.AbstractTransition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTransitionImpl
/*     */   extends NamedElementImpl
/*     */   implements AbstractTransition
/*     */ {
/*     */   protected EList<State> origin;
/*     */   protected Expression guard;
/*     */   protected Action action;
/*     */   protected PortExpression trigger;
/*     */   protected TimeReset timeReset;
/*     */   protected TimeSpecification timeSpecification;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 134 */     return BehaviorsPackage.Literals.ABSTRACT_TRANSITION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<State> getOrigin() {
/* 144 */     if (this.origin == null)
/*     */     {
/* 146 */       this.origin = (EList<State>)new EObjectWithInverseResolvingEList.ManyInverse(State.class, (InternalEObject)this, 2, 4);
/*     */     }
/* 148 */     return this.origin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getGuard() {
/* 158 */     return this.guard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetGuard(Expression newGuard, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 168 */     Expression oldGuard = this.guard;
/* 169 */     this.guard = newGuard;
/* 170 */     if (eNotificationRequired()) {
/*     */       
/* 172 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 3, oldGuard, newGuard);
/* 173 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 175 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGuard(Expression newGuard) {
/* 185 */     if (newGuard != this.guard) {
/*     */       
/* 187 */       NotificationChain msgs = null;
/* 188 */       if (this.guard != null)
/* 189 */         msgs = ((InternalEObject)this.guard).eInverseRemove((InternalEObject)this, -4, null, msgs); 
/* 190 */       if (newGuard != null)
/* 191 */         msgs = ((InternalEObject)newGuard).eInverseAdd((InternalEObject)this, -4, null, msgs); 
/* 192 */       msgs = basicSetGuard(newGuard, msgs);
/* 193 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 195 */     } else if (eNotificationRequired()) {
/* 196 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newGuard, newGuard));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getAction() {
/* 206 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetAction(Action newAction, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 216 */     Action oldAction = this.action;
/* 217 */     this.action = newAction;
/* 218 */     if (eNotificationRequired()) {
/*     */       
/* 220 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 4, oldAction, newAction);
/* 221 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 223 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(Action newAction) {
/* 233 */     if (newAction != this.action) {
/*     */       
/* 235 */       NotificationChain msgs = null;
/* 236 */       if (this.action != null)
/* 237 */         msgs = ((InternalEObject)this.action).eInverseRemove((InternalEObject)this, -5, null, msgs); 
/* 238 */       if (newAction != null)
/* 239 */         msgs = ((InternalEObject)newAction).eInverseAdd((InternalEObject)this, -5, null, msgs); 
/* 240 */       msgs = basicSetAction(newAction, msgs);
/* 241 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 243 */     } else if (eNotificationRequired()) {
/* 244 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newAction, newAction));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortExpression getTrigger() {
/* 254 */     return this.trigger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTrigger(PortExpression newTrigger, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 264 */     PortExpression oldTrigger = this.trigger;
/* 265 */     this.trigger = newTrigger;
/* 266 */     if (eNotificationRequired()) {
/*     */       
/* 268 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 5, oldTrigger, newTrigger);
/* 269 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 271 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrigger(PortExpression newTrigger) {
/* 281 */     if (newTrigger != this.trigger) {
/*     */       
/* 283 */       NotificationChain msgs = null;
/* 284 */       if (this.trigger != null)
/* 285 */         msgs = ((InternalEObject)this.trigger).eInverseRemove((InternalEObject)this, -6, null, msgs); 
/* 286 */       if (newTrigger != null)
/* 287 */         msgs = ((InternalEObject)newTrigger).eInverseAdd((InternalEObject)this, -6, null, msgs); 
/* 288 */       msgs = basicSetTrigger(newTrigger, msgs);
/* 289 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 291 */     } else if (eNotificationRequired()) {
/* 292 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, newTrigger, newTrigger));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeReset getTimeReset() {
/* 302 */     return this.timeReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTimeReset(TimeReset newTimeReset, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 312 */     TimeReset oldTimeReset = this.timeReset;
/* 313 */     this.timeReset = newTimeReset;
/* 314 */     if (eNotificationRequired()) {
/*     */       
/* 316 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 6, oldTimeReset, newTimeReset);
/* 317 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 319 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeReset(TimeReset newTimeReset) {
/* 329 */     if (newTimeReset != this.timeReset) {
/*     */       
/* 331 */       NotificationChain msgs = null;
/* 332 */       if (this.timeReset != null)
/* 333 */         msgs = ((InternalEObject)this.timeReset).eInverseRemove((InternalEObject)this, -7, null, msgs); 
/* 334 */       if (newTimeReset != null)
/* 335 */         msgs = ((InternalEObject)newTimeReset).eInverseAdd((InternalEObject)this, -7, null, msgs); 
/* 336 */       msgs = basicSetTimeReset(newTimeReset, msgs);
/* 337 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 339 */     } else if (eNotificationRequired()) {
/* 340 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, newTimeReset, newTimeReset));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeSpecification getTimeSpecification() {
/* 350 */     return this.timeSpecification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTimeSpecification(TimeSpecification newTimeSpecification, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 360 */     TimeSpecification oldTimeSpecification = this.timeSpecification;
/* 361 */     this.timeSpecification = newTimeSpecification;
/* 362 */     if (eNotificationRequired()) {
/*     */       
/* 364 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 7, oldTimeSpecification, newTimeSpecification);
/* 365 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 367 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSpecification(TimeSpecification newTimeSpecification) {
/* 377 */     if (newTimeSpecification != this.timeSpecification) {
/*     */       
/* 379 */       NotificationChain msgs = null;
/* 380 */       if (this.timeSpecification != null)
/* 381 */         msgs = ((InternalEObject)this.timeSpecification).eInverseRemove((InternalEObject)this, 1, TimeSpecification.class, msgs); 
/* 382 */       if (newTimeSpecification != null)
/* 383 */         msgs = ((InternalEObject)newTimeSpecification).eInverseAdd((InternalEObject)this, 1, TimeSpecification.class, msgs); 
/* 384 */       msgs = basicSetTimeSpecification(newTimeSpecification, msgs);
/* 385 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 387 */     } else if (eNotificationRequired()) {
/* 388 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 7, newTimeSpecification, newTimeSpecification));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 400 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 403 */         return ((InternalEList)getOrigin()).basicAdd(otherEnd, msgs);
/*     */       case 7:
/* 405 */         if (this.timeSpecification != null)
/* 406 */           msgs = ((InternalEObject)this.timeSpecification).eInverseRemove((InternalEObject)this, -8, null, msgs); 
/* 407 */         return basicSetTimeSpecification((TimeSpecification)otherEnd, msgs);
/*     */     } 
/* 409 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 420 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 423 */         return ((InternalEList)getOrigin()).basicRemove(otherEnd, msgs);
/*     */       case 3:
/* 425 */         return basicSetGuard((Expression)null, msgs);
/*     */       case 4:
/* 427 */         return basicSetAction((Action)null, msgs);
/*     */       case 5:
/* 429 */         return basicSetTrigger((PortExpression)null, msgs);
/*     */       case 6:
/* 431 */         return basicSetTimeReset((TimeReset)null, msgs);
/*     */       case 7:
/* 433 */         return basicSetTimeSpecification((TimeSpecification)null, msgs);
/*     */     } 
/* 435 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 446 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 449 */         return getOrigin();
/*     */       case 3:
/* 451 */         return getGuard();
/*     */       case 4:
/* 453 */         return getAction();
/*     */       case 5:
/* 455 */         return getTrigger();
/*     */       case 6:
/* 457 */         return getTimeReset();
/*     */       case 7:
/* 459 */         return getTimeSpecification();
/*     */     } 
/* 461 */     return super.eGet(featureID, resolve, coreType);
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
/* 473 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 476 */         getOrigin().clear();
/* 477 */         getOrigin().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 480 */         setGuard((Expression)newValue);
/*     */         return;
/*     */       case 4:
/* 483 */         setAction((Action)newValue);
/*     */         return;
/*     */       case 5:
/* 486 */         setTrigger((PortExpression)newValue);
/*     */         return;
/*     */       case 6:
/* 489 */         setTimeReset((TimeReset)newValue);
/*     */         return;
/*     */       case 7:
/* 492 */         setTimeSpecification((TimeSpecification)newValue);
/*     */         return;
/*     */     } 
/* 495 */     super.eSet(featureID, newValue);
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
/* 506 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 509 */         getOrigin().clear();
/*     */         return;
/*     */       case 3:
/* 512 */         setGuard((Expression)null);
/*     */         return;
/*     */       case 4:
/* 515 */         setAction((Action)null);
/*     */         return;
/*     */       case 5:
/* 518 */         setTrigger((PortExpression)null);
/*     */         return;
/*     */       case 6:
/* 521 */         setTimeReset((TimeReset)null);
/*     */         return;
/*     */       case 7:
/* 524 */         setTimeSpecification((TimeSpecification)null);
/*     */         return;
/*     */     } 
/* 527 */     super.eUnset(featureID);
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
/* 538 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 541 */         return (this.origin != null && !this.origin.isEmpty());
/*     */       case 3:
/* 543 */         return (this.guard != null);
/*     */       case 4:
/* 545 */         return (this.action != null);
/*     */       case 5:
/* 547 */         return (this.trigger != null);
/*     */       case 6:
/* 549 */         return (this.timeReset != null);
/*     */       case 7:
/* 551 */         return (this.timeSpecification != null);
/*     */     } 
/* 553 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\AbstractTransitionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */