/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.TransitionAlternative;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransitionAlternativeImpl
/*     */   extends EObjectImpl
/*     */   implements TransitionAlternative
/*     */ {
/*     */   protected Expression condition;
/*     */   protected EList<State> state;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  84 */     return BehaviorsPackage.Literals.TRANSITION_ALTERNATIVE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getCondition() {
/*  94 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCondition(Expression newCondition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 104 */     Expression oldCondition = this.condition;
/* 105 */     this.condition = newCondition;
/* 106 */     if (eNotificationRequired()) {
/*     */       
/* 108 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldCondition, newCondition);
/* 109 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 111 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCondition(Expression newCondition) {
/* 121 */     if (newCondition != this.condition) {
/*     */       
/* 123 */       NotificationChain msgs = null;
/* 124 */       if (this.condition != null)
/* 125 */         msgs = ((InternalEObject)this.condition).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 126 */       if (newCondition != null)
/* 127 */         msgs = ((InternalEObject)newCondition).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 128 */       msgs = basicSetCondition(newCondition, msgs);
/* 129 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 131 */     } else if (eNotificationRequired()) {
/* 132 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newCondition, newCondition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<State> getState() {
/* 142 */     if (this.state == null)
/*     */     {
/* 144 */       this.state = (EList<State>)new EObjectWithInverseResolvingEList.ManyInverse(State.class, (InternalEObject)this, 1, 3);
/*     */     }
/* 146 */     return this.state;
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
/* 158 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 161 */         return ((InternalEList)getState()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 163 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 174 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 177 */         return basicSetCondition((Expression)null, msgs);
/*     */       case 1:
/* 179 */         return ((InternalEList)getState()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 181 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 192 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 195 */         return getCondition();
/*     */       case 1:
/* 197 */         return getState();
/*     */     } 
/* 199 */     return super.eGet(featureID, resolve, coreType);
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
/* 211 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 214 */         setCondition((Expression)newValue);
/*     */         return;
/*     */       case 1:
/* 217 */         getState().clear();
/* 218 */         getState().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 221 */     super.eSet(featureID, newValue);
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
/* 232 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 235 */         setCondition((Expression)null);
/*     */         return;
/*     */       case 1:
/* 238 */         getState().clear();
/*     */         return;
/*     */     } 
/* 241 */     super.eUnset(featureID);
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
/* 252 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 255 */         return (this.condition != null);
/*     */       case 1:
/* 257 */         return (this.state != null && !this.state.isEmpty());
/*     */     } 
/* 259 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\TransitionAlternativeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */