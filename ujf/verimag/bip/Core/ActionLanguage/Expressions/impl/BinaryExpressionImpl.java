/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ExpressionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BinaryExpressionImpl
/*     */   extends ExpressionImpl
/*     */   implements BinaryExpression
/*     */ {
/*  50 */   protected static final BinaryOperator OPERATOR_EDEFAULT = BinaryOperator.ADDITION;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected BinaryOperator operator = OPERATOR_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Expression leftOperand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Expression rightOperand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 100 */     return ExpressionsPackage.Literals.BINARY_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryOperator getOperator() {
/* 110 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(BinaryOperator newOperator) {
/* 120 */     BinaryOperator oldOperator = this.operator;
/* 121 */     this.operator = (newOperator == null) ? OPERATOR_EDEFAULT : newOperator;
/* 122 */     if (eNotificationRequired()) {
/* 123 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldOperator, this.operator));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getLeftOperand() {
/* 133 */     return this.leftOperand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetLeftOperand(Expression newLeftOperand, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 143 */     Expression oldLeftOperand = this.leftOperand;
/* 144 */     this.leftOperand = newLeftOperand;
/* 145 */     if (eNotificationRequired()) {
/*     */       
/* 147 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldLeftOperand, newLeftOperand);
/* 148 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 150 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftOperand(Expression newLeftOperand) {
/* 160 */     if (newLeftOperand != this.leftOperand) {
/*     */       
/* 162 */       NotificationChain msgs = null;
/* 163 */       if (this.leftOperand != null)
/* 164 */         msgs = ((InternalEObject)this.leftOperand).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 165 */       if (newLeftOperand != null)
/* 166 */         msgs = ((InternalEObject)newLeftOperand).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 167 */       msgs = basicSetLeftOperand(newLeftOperand, msgs);
/* 168 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 170 */     } else if (eNotificationRequired()) {
/* 171 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newLeftOperand, newLeftOperand));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getRightOperand() {
/* 181 */     return this.rightOperand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetRightOperand(Expression newRightOperand, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 191 */     Expression oldRightOperand = this.rightOperand;
/* 192 */     this.rightOperand = newRightOperand;
/* 193 */     if (eNotificationRequired()) {
/*     */       
/* 195 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldRightOperand, newRightOperand);
/* 196 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 198 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightOperand(Expression newRightOperand) {
/* 208 */     if (newRightOperand != this.rightOperand) {
/*     */       
/* 210 */       NotificationChain msgs = null;
/* 211 */       if (this.rightOperand != null)
/* 212 */         msgs = ((InternalEObject)this.rightOperand).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 213 */       if (newRightOperand != null)
/* 214 */         msgs = ((InternalEObject)newRightOperand).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 215 */       msgs = basicSetRightOperand(newRightOperand, msgs);
/* 216 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 218 */     } else if (eNotificationRequired()) {
/* 219 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newRightOperand, newRightOperand));
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
/* 230 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 233 */         return basicSetLeftOperand((Expression)null, msgs);
/*     */       case 2:
/* 235 */         return basicSetRightOperand((Expression)null, msgs);
/*     */     } 
/* 237 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 248 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 251 */         return getOperator();
/*     */       case 1:
/* 253 */         return getLeftOperand();
/*     */       case 2:
/* 255 */         return getRightOperand();
/*     */     } 
/* 257 */     return super.eGet(featureID, resolve, coreType);
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
/* 268 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 271 */         setOperator((BinaryOperator)newValue);
/*     */         return;
/*     */       case 1:
/* 274 */         setLeftOperand((Expression)newValue);
/*     */         return;
/*     */       case 2:
/* 277 */         setRightOperand((Expression)newValue);
/*     */         return;
/*     */     } 
/* 280 */     super.eSet(featureID, newValue);
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
/* 291 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 294 */         setOperator(OPERATOR_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 297 */         setLeftOperand((Expression)null);
/*     */         return;
/*     */       case 2:
/* 300 */         setRightOperand((Expression)null);
/*     */         return;
/*     */     } 
/* 303 */     super.eUnset(featureID);
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
/* 314 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 317 */         return (this.operator != OPERATOR_EDEFAULT);
/*     */       case 1:
/* 319 */         return (this.leftOperand != null);
/*     */       case 2:
/* 321 */         return (this.rightOperand != null);
/*     */     } 
/* 323 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 334 */     if (eIsProxy()) return super.toString();
/*     */     
/* 336 */     StringBuffer result = new StringBuffer(super.toString());
/* 337 */     result.append(" (operator: ");
/* 338 */     result.append(this.operator);
/* 339 */     result.append(')');
/* 340 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\BinaryExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */