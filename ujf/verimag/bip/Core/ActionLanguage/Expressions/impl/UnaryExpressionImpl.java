/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
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
/*     */ public class UnaryExpressionImpl
/*     */   extends ExpressionImpl
/*     */   implements UnaryExpression
/*     */ {
/*  50 */   protected static final UnaryOperator OPERATOR_EDEFAULT = UnaryOperator.POSITIVE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected UnaryOperator operator = OPERATOR_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Expression operand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean POSTFIX_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean postfix = false;
/*     */ 
/*     */ 
/*     */ 
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
/* 110 */     return ExpressionsPackage.Literals.UNARY_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UnaryOperator getOperator() {
/* 120 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(UnaryOperator newOperator) {
/* 130 */     UnaryOperator oldOperator = this.operator;
/* 131 */     this.operator = (newOperator == null) ? OPERATOR_EDEFAULT : newOperator;
/* 132 */     if (eNotificationRequired()) {
/* 133 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldOperator, this.operator));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getOperand() {
/* 143 */     return this.operand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetOperand(Expression newOperand, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 153 */     Expression oldOperand = this.operand;
/* 154 */     this.operand = newOperand;
/* 155 */     if (eNotificationRequired()) {
/*     */       
/* 157 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldOperand, newOperand);
/* 158 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 160 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperand(Expression newOperand) {
/* 170 */     if (newOperand != this.operand) {
/*     */       
/* 172 */       NotificationChain msgs = null;
/* 173 */       if (this.operand != null)
/* 174 */         msgs = ((InternalEObject)this.operand).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 175 */       if (newOperand != null)
/* 176 */         msgs = ((InternalEObject)newOperand).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 177 */       msgs = basicSetOperand(newOperand, msgs);
/* 178 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 180 */     } else if (eNotificationRequired()) {
/* 181 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newOperand, newOperand));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPostfix() {
/* 191 */     return this.postfix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPostfix(boolean newPostfix) {
/* 201 */     boolean oldPostfix = this.postfix;
/* 202 */     this.postfix = newPostfix;
/* 203 */     if (eNotificationRequired()) {
/* 204 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldPostfix, this.postfix));
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
/* 215 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 218 */         return basicSetOperand((Expression)null, msgs);
/*     */     } 
/* 220 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 231 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 234 */         return getOperator();
/*     */       case 1:
/* 236 */         return getOperand();
/*     */       case 2:
/* 238 */         return Boolean.valueOf(isPostfix());
/*     */     } 
/* 240 */     return super.eGet(featureID, resolve, coreType);
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
/* 251 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 254 */         setOperator((UnaryOperator)newValue);
/*     */         return;
/*     */       case 1:
/* 257 */         setOperand((Expression)newValue);
/*     */         return;
/*     */       case 2:
/* 260 */         setPostfix(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 263 */     super.eSet(featureID, newValue);
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
/* 274 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 277 */         setOperator(OPERATOR_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 280 */         setOperand((Expression)null);
/*     */         return;
/*     */       case 2:
/* 283 */         setPostfix(false);
/*     */         return;
/*     */     } 
/* 286 */     super.eUnset(featureID);
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
/* 297 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 300 */         return (this.operator != OPERATOR_EDEFAULT);
/*     */       case 1:
/* 302 */         return (this.operand != null);
/*     */       case 2:
/* 304 */         return this.postfix;
/*     */     } 
/* 306 */     return super.eIsSet(featureID);
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
/* 317 */     if (eIsProxy()) return super.toString();
/*     */     
/* 319 */     StringBuffer result = new StringBuffer(super.toString());
/* 320 */     result.append(" (operator: ");
/* 321 */     result.append(this.operator);
/* 322 */     result.append(", postfix: ");
/* 323 */     result.append(this.postfix);
/* 324 */     result.append(')');
/* 325 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\UnaryExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */