/*     */ package ujf.verimag.bip.Core.PortExpressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ACTypingImpl
/*     */   extends ACExpressionImpl
/*     */   implements ACTyping
/*     */ {
/*  46 */   protected static final ACTypingKind TYPE_EDEFAULT = ACTypingKind.SYNC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   protected ACTypingKind type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ACExpression operand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  86 */     return PortExpressionsPackage.Literals.AC_TYPING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACTypingKind getType() {
/*  96 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ACTypingKind newType) {
/* 106 */     ACTypingKind oldType = this.type;
/* 107 */     this.type = (newType == null) ? TYPE_EDEFAULT : newType;
/* 108 */     if (eNotificationRequired()) {
/* 109 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACExpression getOperand() {
/* 119 */     return this.operand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetOperand(ACExpression newOperand, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 129 */     ACExpression oldOperand = this.operand;
/* 130 */     this.operand = newOperand;
/* 131 */     if (eNotificationRequired()) {
/*     */       
/* 133 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldOperand, newOperand);
/* 134 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 136 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperand(ACExpression newOperand) {
/* 146 */     if (newOperand != this.operand) {
/*     */       
/* 148 */       NotificationChain msgs = null;
/* 149 */       if (this.operand != null)
/* 150 */         msgs = ((InternalEObject)this.operand).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 151 */       if (newOperand != null)
/* 152 */         msgs = ((InternalEObject)newOperand).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 153 */       msgs = basicSetOperand(newOperand, msgs);
/* 154 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 156 */     } else if (eNotificationRequired()) {
/* 157 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newOperand, newOperand));
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
/* 168 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 171 */         return basicSetOperand((ACExpression)null, msgs);
/*     */     } 
/* 173 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 184 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 187 */         return getType();
/*     */       case 1:
/* 189 */         return getOperand();
/*     */     } 
/* 191 */     return super.eGet(featureID, resolve, coreType);
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
/* 202 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 205 */         setType((ACTypingKind)newValue);
/*     */         return;
/*     */       case 1:
/* 208 */         setOperand((ACExpression)newValue);
/*     */         return;
/*     */     } 
/* 211 */     super.eSet(featureID, newValue);
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
/* 222 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 225 */         setType(TYPE_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 228 */         setOperand((ACExpression)null);
/*     */         return;
/*     */     } 
/* 231 */     super.eUnset(featureID);
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
/* 242 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 245 */         return (this.type != TYPE_EDEFAULT);
/*     */       case 1:
/* 247 */         return (this.operand != null);
/*     */     } 
/* 249 */     return super.eIsSet(featureID);
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
/* 260 */     if (eIsProxy()) return super.toString();
/*     */     
/* 262 */     StringBuffer result = new StringBuffer(super.toString());
/* 263 */     result.append(" (type: ");
/* 264 */     result.append(this.type);
/* 265 */     result.append(')');
/* 266 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\impl\ACTypingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */