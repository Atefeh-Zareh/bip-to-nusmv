/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*     */ import ujf.verimag.bip.cmodel.CConditionalStm;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CConditionalExpressionImpl
/*     */   extends CExpressionImpl
/*     */   implements CConditionalExpression
/*     */ {
/*     */   protected CExpression condition;
/*     */   protected CExpression trueCase;
/*     */   protected CExpression falseCase;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  85 */     return CmodelPackage.Literals.CCONDITIONAL_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getCondition() {
/*  94 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCondition(CExpression newCondition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 103 */     CExpression oldCondition = this.condition;
/* 104 */     this.condition = newCondition;
/* 105 */     if (eNotificationRequired()) {
/* 106 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldCondition, newCondition);
/* 107 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 109 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCondition(CExpression newCondition) {
/* 118 */     if (newCondition != this.condition) {
/* 119 */       NotificationChain msgs = null;
/* 120 */       if (this.condition != null)
/* 121 */         msgs = ((InternalEObject)this.condition).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 122 */       if (newCondition != null)
/* 123 */         msgs = ((InternalEObject)newCondition).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 124 */       msgs = basicSetCondition(newCondition, msgs);
/* 125 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 127 */     } else if (eNotificationRequired()) {
/* 128 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newCondition, newCondition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getTrueCase() {
/* 137 */     return this.trueCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTrueCase(CExpression newTrueCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 146 */     CExpression oldTrueCase = this.trueCase;
/* 147 */     this.trueCase = newTrueCase;
/* 148 */     if (eNotificationRequired()) {
/* 149 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldTrueCase, newTrueCase);
/* 150 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 152 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrueCase(CExpression newTrueCase) {
/* 161 */     if (newTrueCase != this.trueCase) {
/* 162 */       NotificationChain msgs = null;
/* 163 */       if (this.trueCase != null)
/* 164 */         msgs = ((InternalEObject)this.trueCase).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 165 */       if (newTrueCase != null)
/* 166 */         msgs = ((InternalEObject)newTrueCase).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 167 */       msgs = basicSetTrueCase(newTrueCase, msgs);
/* 168 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 170 */     } else if (eNotificationRequired()) {
/* 171 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newTrueCase, newTrueCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getFalseCase() {
/* 180 */     return this.falseCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetFalseCase(CExpression newFalseCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 189 */     CExpression oldFalseCase = this.falseCase;
/* 190 */     this.falseCase = newFalseCase;
/* 191 */     if (eNotificationRequired()) {
/* 192 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldFalseCase, newFalseCase);
/* 193 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 195 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFalseCase(CExpression newFalseCase) {
/* 204 */     if (newFalseCase != this.falseCase) {
/* 205 */       NotificationChain msgs = null;
/* 206 */       if (this.falseCase != null)
/* 207 */         msgs = ((InternalEObject)this.falseCase).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 208 */       if (newFalseCase != null)
/* 209 */         msgs = ((InternalEObject)newFalseCase).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 210 */       msgs = basicSetFalseCase(newFalseCase, msgs);
/* 211 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 213 */     } else if (eNotificationRequired()) {
/* 214 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newFalseCase, newFalseCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 224 */     switch (featureID) {
/*     */       case 0:
/* 226 */         return basicSetCondition((CExpression)null, msgs);
/*     */       case 1:
/* 228 */         return basicSetTrueCase((CExpression)null, msgs);
/*     */       case 2:
/* 230 */         return basicSetFalseCase((CExpression)null, msgs);
/*     */     } 
/* 232 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 242 */     switch (featureID) {
/*     */       case 0:
/* 244 */         return getCondition();
/*     */       case 1:
/* 246 */         return getTrueCase();
/*     */       case 2:
/* 248 */         return getFalseCase();
/*     */     } 
/* 250 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 260 */     switch (featureID) {
/*     */       case 0:
/* 262 */         setCondition((CExpression)newValue);
/*     */         return;
/*     */       case 1:
/* 265 */         setTrueCase((CExpression)newValue);
/*     */         return;
/*     */       case 2:
/* 268 */         setFalseCase((CExpression)newValue);
/*     */         return;
/*     */     } 
/* 271 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 281 */     switch (featureID) {
/*     */       case 0:
/* 283 */         setCondition((CExpression)null);
/*     */         return;
/*     */       case 1:
/* 286 */         setTrueCase((CExpression)null);
/*     */         return;
/*     */       case 2:
/* 289 */         setFalseCase((CExpression)null);
/*     */         return;
/*     */     } 
/* 292 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 302 */     switch (featureID) {
/*     */       case 0:
/* 304 */         return (this.condition != null);
/*     */       case 1:
/* 306 */         return (this.trueCase != null);
/*     */       case 2:
/* 308 */         return (this.falseCase != null);
/*     */     } 
/* 310 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 320 */     if (baseClass == CConditionalStm.class) {
/* 321 */       switch (derivedFeatureID) { case 0:
/* 322 */           return 0; }
/* 323 */        return -1;
/*     */     } 
/*     */     
/* 326 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 336 */     if (baseClass == CConditionalStm.class) {
/* 337 */       switch (baseFeatureID) { case 0:
/* 338 */           return 0; }
/* 339 */        return -1;
/*     */     } 
/*     */     
/* 342 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CConditionalExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */