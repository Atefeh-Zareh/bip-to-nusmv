/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ActionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IfActionImpl
/*     */   extends ActionImpl
/*     */   implements IfAction
/*     */ {
/*     */   protected Action ifCase;
/*     */   protected Action elseCase;
/*     */   protected Expression condition;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  90 */     return ActionsPackage.Literals.IF_ACTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getIfCase() {
/* 100 */     return this.ifCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetIfCase(Action newIfCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 110 */     Action oldIfCase = this.ifCase;
/* 111 */     this.ifCase = newIfCase;
/* 112 */     if (eNotificationRequired()) {
/*     */       
/* 114 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldIfCase, newIfCase);
/* 115 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 117 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIfCase(Action newIfCase) {
/* 127 */     if (newIfCase != this.ifCase) {
/*     */       
/* 129 */       NotificationChain msgs = null;
/* 130 */       if (this.ifCase != null)
/* 131 */         msgs = ((InternalEObject)this.ifCase).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 132 */       if (newIfCase != null)
/* 133 */         msgs = ((InternalEObject)newIfCase).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 134 */       msgs = basicSetIfCase(newIfCase, msgs);
/* 135 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 137 */     } else if (eNotificationRequired()) {
/* 138 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newIfCase, newIfCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getElseCase() {
/* 148 */     return this.elseCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetElseCase(Action newElseCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 158 */     Action oldElseCase = this.elseCase;
/* 159 */     this.elseCase = newElseCase;
/* 160 */     if (eNotificationRequired()) {
/*     */       
/* 162 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldElseCase, newElseCase);
/* 163 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 165 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElseCase(Action newElseCase) {
/* 175 */     if (newElseCase != this.elseCase) {
/*     */       
/* 177 */       NotificationChain msgs = null;
/* 178 */       if (this.elseCase != null)
/* 179 */         msgs = ((InternalEObject)this.elseCase).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 180 */       if (newElseCase != null)
/* 181 */         msgs = ((InternalEObject)newElseCase).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 182 */       msgs = basicSetElseCase(newElseCase, msgs);
/* 183 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 185 */     } else if (eNotificationRequired()) {
/* 186 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newElseCase, newElseCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getCondition() {
/* 196 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCondition(Expression newCondition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 206 */     Expression oldCondition = this.condition;
/* 207 */     this.condition = newCondition;
/* 208 */     if (eNotificationRequired()) {
/*     */       
/* 210 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldCondition, newCondition);
/* 211 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 213 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCondition(Expression newCondition) {
/* 223 */     if (newCondition != this.condition) {
/*     */       
/* 225 */       NotificationChain msgs = null;
/* 226 */       if (this.condition != null)
/* 227 */         msgs = ((InternalEObject)this.condition).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 228 */       if (newCondition != null)
/* 229 */         msgs = ((InternalEObject)newCondition).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 230 */       msgs = basicSetCondition(newCondition, msgs);
/* 231 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 233 */     } else if (eNotificationRequired()) {
/* 234 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newCondition, newCondition));
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
/* 245 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 248 */         return basicSetIfCase((Action)null, msgs);
/*     */       case 1:
/* 250 */         return basicSetElseCase((Action)null, msgs);
/*     */       case 2:
/* 252 */         return basicSetCondition((Expression)null, msgs);
/*     */     } 
/* 254 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 265 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 268 */         return getIfCase();
/*     */       case 1:
/* 270 */         return getElseCase();
/*     */       case 2:
/* 272 */         return getCondition();
/*     */     } 
/* 274 */     return super.eGet(featureID, resolve, coreType);
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
/* 285 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 288 */         setIfCase((Action)newValue);
/*     */         return;
/*     */       case 1:
/* 291 */         setElseCase((Action)newValue);
/*     */         return;
/*     */       case 2:
/* 294 */         setCondition((Expression)newValue);
/*     */         return;
/*     */     } 
/* 297 */     super.eSet(featureID, newValue);
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
/* 308 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 311 */         setIfCase((Action)null);
/*     */         return;
/*     */       case 1:
/* 314 */         setElseCase((Action)null);
/*     */         return;
/*     */       case 2:
/* 317 */         setCondition((Expression)null);
/*     */         return;
/*     */     } 
/* 320 */     super.eUnset(featureID);
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
/* 331 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 334 */         return (this.ifCase != null);
/*     */       case 1:
/* 336 */         return (this.elseCase != null);
/*     */       case 2:
/* 338 */         return (this.condition != null);
/*     */     } 
/* 340 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\impl\IfActionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */