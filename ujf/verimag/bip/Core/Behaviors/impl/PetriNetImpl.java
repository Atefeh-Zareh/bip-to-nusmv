/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PetriNetImpl
/*     */   extends BehaviorImpl
/*     */   implements PetriNet
/*     */ {
/*     */   protected EList<State> state;
/*     */   protected EList<Transition> transition;
/*     */   protected EList<State> initialState;
/*     */   protected Action initialization;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 107 */     return BehaviorsPackage.Literals.PETRI_NET;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<State> getState() {
/* 117 */     if (this.state == null)
/*     */     {
/* 119 */       this.state = (EList<State>)new EObjectContainmentEList(State.class, (InternalEObject)this, 1);
/*     */     }
/* 121 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Transition> getTransition() {
/* 131 */     if (this.transition == null)
/*     */     {
/* 133 */       this.transition = (EList<Transition>)new EObjectContainmentEList(Transition.class, (InternalEObject)this, 2);
/*     */     }
/* 135 */     return this.transition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<State> getInitialState() {
/* 145 */     if (this.initialState == null)
/*     */     {
/* 147 */       this.initialState = (EList<State>)new EObjectResolvingEList(State.class, (InternalEObject)this, 3);
/*     */     }
/* 149 */     return this.initialState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getInitialization() {
/* 159 */     return this.initialization;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInitialization(Action newInitialization, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 169 */     Action oldInitialization = this.initialization;
/* 170 */     this.initialization = newInitialization;
/* 171 */     if (eNotificationRequired()) {
/*     */       
/* 173 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 4, oldInitialization, newInitialization);
/* 174 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 176 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitialization(Action newInitialization) {
/* 186 */     if (newInitialization != this.initialization) {
/*     */       
/* 188 */       NotificationChain msgs = null;
/* 189 */       if (this.initialization != null)
/* 190 */         msgs = ((InternalEObject)this.initialization).eInverseRemove((InternalEObject)this, -5, null, msgs); 
/* 191 */       if (newInitialization != null)
/* 192 */         msgs = ((InternalEObject)newInitialization).eInverseAdd((InternalEObject)this, -5, null, msgs); 
/* 193 */       msgs = basicSetInitialization(newInitialization, msgs);
/* 194 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 196 */     } else if (eNotificationRequired()) {
/* 197 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newInitialization, newInitialization));
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
/* 208 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 211 */         return ((InternalEList)getState()).basicRemove(otherEnd, msgs);
/*     */       case 2:
/* 213 */         return ((InternalEList)getTransition()).basicRemove(otherEnd, msgs);
/*     */       case 4:
/* 215 */         return basicSetInitialization((Action)null, msgs);
/*     */     } 
/* 217 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 228 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 231 */         return getState();
/*     */       case 2:
/* 233 */         return getTransition();
/*     */       case 3:
/* 235 */         return getInitialState();
/*     */       case 4:
/* 237 */         return getInitialization();
/*     */     } 
/* 239 */     return super.eGet(featureID, resolve, coreType);
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
/* 251 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 254 */         getState().clear();
/* 255 */         getState().addAll((Collection)newValue);
/*     */         return;
/*     */       case 2:
/* 258 */         getTransition().clear();
/* 259 */         getTransition().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 262 */         getInitialState().clear();
/* 263 */         getInitialState().addAll((Collection)newValue);
/*     */         return;
/*     */       case 4:
/* 266 */         setInitialization((Action)newValue);
/*     */         return;
/*     */     } 
/* 269 */     super.eSet(featureID, newValue);
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
/* 280 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 283 */         getState().clear();
/*     */         return;
/*     */       case 2:
/* 286 */         getTransition().clear();
/*     */         return;
/*     */       case 3:
/* 289 */         getInitialState().clear();
/*     */         return;
/*     */       case 4:
/* 292 */         setInitialization((Action)null);
/*     */         return;
/*     */     } 
/* 295 */     super.eUnset(featureID);
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
/* 306 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 309 */         return (this.state != null && !this.state.isEmpty());
/*     */       case 2:
/* 311 */         return (this.transition != null && !this.transition.isEmpty());
/*     */       case 3:
/* 313 */         return (this.initialState != null && !this.initialState.isEmpty());
/*     */       case 4:
/* 315 */         return (this.initialization != null);
/*     */     } 
/* 317 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\PetriNetImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */