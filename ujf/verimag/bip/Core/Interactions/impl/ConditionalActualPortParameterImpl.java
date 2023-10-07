/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
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
/*     */ public class ConditionalActualPortParameterImpl
/*     */   extends ActualPortParameterImpl
/*     */   implements ConditionalActualPortParameter
/*     */ {
/*     */   protected Expression expression;
/*     */   protected ActualPortParameter trueCase;
/*     */   protected ActualPortParameter falseCase;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  88 */     return InteractionsPackage.Literals.CONDITIONAL_ACTUAL_PORT_PARAMETER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getExpression() {
/*  98 */     return this.expression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 108 */     Expression oldExpression = this.expression;
/* 109 */     this.expression = newExpression;
/* 110 */     if (eNotificationRequired()) {
/*     */       
/* 112 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldExpression, newExpression);
/* 113 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 115 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(Expression newExpression) {
/* 125 */     if (newExpression != this.expression) {
/*     */       
/* 127 */       NotificationChain msgs = null;
/* 128 */       if (this.expression != null)
/* 129 */         msgs = ((InternalEObject)this.expression).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 130 */       if (newExpression != null)
/* 131 */         msgs = ((InternalEObject)newExpression).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 132 */       msgs = basicSetExpression(newExpression, msgs);
/* 133 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 135 */     } else if (eNotificationRequired()) {
/* 136 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newExpression, newExpression));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActualPortParameter getTrueCase() {
/* 146 */     return this.trueCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTrueCase(ActualPortParameter newTrueCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 156 */     ActualPortParameter oldTrueCase = this.trueCase;
/* 157 */     this.trueCase = newTrueCase;
/* 158 */     if (eNotificationRequired()) {
/*     */       
/* 160 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldTrueCase, newTrueCase);
/* 161 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 163 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrueCase(ActualPortParameter newTrueCase) {
/* 173 */     if (newTrueCase != this.trueCase) {
/*     */       
/* 175 */       NotificationChain msgs = null;
/* 176 */       if (this.trueCase != null)
/* 177 */         msgs = ((InternalEObject)this.trueCase).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 178 */       if (newTrueCase != null)
/* 179 */         msgs = ((InternalEObject)newTrueCase).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 180 */       msgs = basicSetTrueCase(newTrueCase, msgs);
/* 181 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 183 */     } else if (eNotificationRequired()) {
/* 184 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newTrueCase, newTrueCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActualPortParameter getFalseCase() {
/* 194 */     return this.falseCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetFalseCase(ActualPortParameter newFalseCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 204 */     ActualPortParameter oldFalseCase = this.falseCase;
/* 205 */     this.falseCase = newFalseCase;
/* 206 */     if (eNotificationRequired()) {
/*     */       
/* 208 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldFalseCase, newFalseCase);
/* 209 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 211 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFalseCase(ActualPortParameter newFalseCase) {
/* 221 */     if (newFalseCase != this.falseCase) {
/*     */       
/* 223 */       NotificationChain msgs = null;
/* 224 */       if (this.falseCase != null)
/* 225 */         msgs = ((InternalEObject)this.falseCase).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 226 */       if (newFalseCase != null)
/* 227 */         msgs = ((InternalEObject)newFalseCase).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 228 */       msgs = basicSetFalseCase(newFalseCase, msgs);
/* 229 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 231 */     } else if (eNotificationRequired()) {
/* 232 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newFalseCase, newFalseCase));
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
/* 243 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 246 */         return basicSetExpression((Expression)null, msgs);
/*     */       case 1:
/* 248 */         return basicSetTrueCase((ActualPortParameter)null, msgs);
/*     */       case 2:
/* 250 */         return basicSetFalseCase((ActualPortParameter)null, msgs);
/*     */     } 
/* 252 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 263 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 266 */         return getExpression();
/*     */       case 1:
/* 268 */         return getTrueCase();
/*     */       case 2:
/* 270 */         return getFalseCase();
/*     */     } 
/* 272 */     return super.eGet(featureID, resolve, coreType);
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
/* 283 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 286 */         setExpression((Expression)newValue);
/*     */         return;
/*     */       case 1:
/* 289 */         setTrueCase((ActualPortParameter)newValue);
/*     */         return;
/*     */       case 2:
/* 292 */         setFalseCase((ActualPortParameter)newValue);
/*     */         return;
/*     */     } 
/* 295 */     super.eSet(featureID, newValue);
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
/* 306 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 309 */         setExpression((Expression)null);
/*     */         return;
/*     */       case 1:
/* 312 */         setTrueCase((ActualPortParameter)null);
/*     */         return;
/*     */       case 2:
/* 315 */         setFalseCase((ActualPortParameter)null);
/*     */         return;
/*     */     } 
/* 318 */     super.eUnset(featureID);
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
/* 329 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 332 */         return (this.expression != null);
/*     */       case 1:
/* 334 */         return (this.trueCase != null);
/*     */       case 2:
/* 336 */         return (this.falseCase != null);
/*     */     } 
/* 338 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\ConditionalActualPortParameterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */