/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.COperation;
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
/*     */ public class COperationImpl
/*     */   extends CExpressionImpl
/*     */   implements COperation
/*     */ {
/*     */   protected CExpression leftOperand;
/*     */   protected CExpression rightOperand;
/*  66 */   protected static final String OPERATOR_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   protected String operator = OPERATOR_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  94 */     return CmodelPackage.Literals.COPERATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getLeftOperand() {
/* 103 */     return this.leftOperand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetLeftOperand(CExpression newLeftOperand, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 112 */     CExpression oldLeftOperand = this.leftOperand;
/* 113 */     this.leftOperand = newLeftOperand;
/* 114 */     if (eNotificationRequired()) {
/* 115 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldLeftOperand, newLeftOperand);
/* 116 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 118 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftOperand(CExpression newLeftOperand) {
/* 127 */     if (newLeftOperand != this.leftOperand) {
/* 128 */       NotificationChain msgs = null;
/* 129 */       if (this.leftOperand != null)
/* 130 */         msgs = ((InternalEObject)this.leftOperand).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 131 */       if (newLeftOperand != null)
/* 132 */         msgs = ((InternalEObject)newLeftOperand).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 133 */       msgs = basicSetLeftOperand(newLeftOperand, msgs);
/* 134 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 136 */     } else if (eNotificationRequired()) {
/* 137 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newLeftOperand, newLeftOperand));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getRightOperand() {
/* 146 */     return this.rightOperand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetRightOperand(CExpression newRightOperand, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 155 */     CExpression oldRightOperand = this.rightOperand;
/* 156 */     this.rightOperand = newRightOperand;
/* 157 */     if (eNotificationRequired()) {
/* 158 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldRightOperand, newRightOperand);
/* 159 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 161 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightOperand(CExpression newRightOperand) {
/* 170 */     if (newRightOperand != this.rightOperand) {
/* 171 */       NotificationChain msgs = null;
/* 172 */       if (this.rightOperand != null)
/* 173 */         msgs = ((InternalEObject)this.rightOperand).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 174 */       if (newRightOperand != null)
/* 175 */         msgs = ((InternalEObject)newRightOperand).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 176 */       msgs = basicSetRightOperand(newRightOperand, msgs);
/* 177 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 179 */     } else if (eNotificationRequired()) {
/* 180 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newRightOperand, newRightOperand));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOperator() {
/* 189 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(String newOperator) {
/* 198 */     String oldOperator = this.operator;
/* 199 */     this.operator = newOperator;
/* 200 */     if (eNotificationRequired()) {
/* 201 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldOperator, this.operator));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 211 */     switch (featureID) {
/*     */       case 0:
/* 213 */         return basicSetLeftOperand((CExpression)null, msgs);
/*     */       case 1:
/* 215 */         return basicSetRightOperand((CExpression)null, msgs);
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
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 227 */     switch (featureID) {
/*     */       case 0:
/* 229 */         return getLeftOperand();
/*     */       case 1:
/* 231 */         return getRightOperand();
/*     */       case 2:
/* 233 */         return getOperator();
/*     */     } 
/* 235 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 245 */     switch (featureID) {
/*     */       case 0:
/* 247 */         setLeftOperand((CExpression)newValue);
/*     */         return;
/*     */       case 1:
/* 250 */         setRightOperand((CExpression)newValue);
/*     */         return;
/*     */       case 2:
/* 253 */         setOperator((String)newValue);
/*     */         return;
/*     */     } 
/* 256 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 266 */     switch (featureID) {
/*     */       case 0:
/* 268 */         setLeftOperand((CExpression)null);
/*     */         return;
/*     */       case 1:
/* 271 */         setRightOperand((CExpression)null);
/*     */         return;
/*     */       case 2:
/* 274 */         setOperator(OPERATOR_EDEFAULT);
/*     */         return;
/*     */     } 
/* 277 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 287 */     switch (featureID) {
/*     */       case 0:
/* 289 */         return (this.leftOperand != null);
/*     */       case 1:
/* 291 */         return (this.rightOperand != null);
/*     */       case 2:
/* 293 */         return (OPERATOR_EDEFAULT == null) ? ((this.operator != null)) : (!OPERATOR_EDEFAULT.equals(this.operator));
/*     */     } 
/* 295 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 305 */     if (eIsProxy()) return super.toString();
/*     */     
/* 307 */     StringBuffer result = new StringBuffer(super.toString());
/* 308 */     result.append(" (operator: ");
/* 309 */     result.append(this.operator);
/* 310 */     result.append(')');
/* 311 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\COperationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */