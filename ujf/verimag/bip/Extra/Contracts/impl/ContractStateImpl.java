/*     */ package ujf.verimag.bip.Extra.Contracts.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.StateImpl;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractState;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContractStateImpl
/*     */   extends StateImpl
/*     */   implements ContractState
/*     */ {
/*     */   protected static final boolean IS_ACCEPTING_EDEFAULT = false;
/*     */   protected boolean isAccepting = false;
/*     */   protected Expression invariant;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  88 */     return ContractsPackage.Literals.CONTRACT_STATE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsAccepting() {
/*  98 */     return this.isAccepting;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsAccepting(boolean newIsAccepting) {
/* 108 */     boolean oldIsAccepting = this.isAccepting;
/* 109 */     this.isAccepting = newIsAccepting;
/* 110 */     if (eNotificationRequired()) {
/* 111 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, oldIsAccepting, this.isAccepting));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getInvariant() {
/* 121 */     return this.invariant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInvariant(Expression newInvariant, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 131 */     Expression oldInvariant = this.invariant;
/* 132 */     this.invariant = newInvariant;
/* 133 */     if (eNotificationRequired()) {
/*     */       
/* 135 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 6, oldInvariant, newInvariant);
/* 136 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 138 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInvariant(Expression newInvariant) {
/* 148 */     if (newInvariant != this.invariant) {
/*     */       
/* 150 */       NotificationChain msgs = null;
/* 151 */       if (this.invariant != null)
/* 152 */         msgs = ((InternalEObject)this.invariant).eInverseRemove((InternalEObject)this, -7, null, msgs); 
/* 153 */       if (newInvariant != null)
/* 154 */         msgs = ((InternalEObject)newInvariant).eInverseAdd((InternalEObject)this, -7, null, msgs); 
/* 155 */       msgs = basicSetInvariant(newInvariant, msgs);
/* 156 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 158 */     } else if (eNotificationRequired()) {
/* 159 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, newInvariant, newInvariant));
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
/* 170 */     switch (featureID) {
/*     */       
/*     */       case 6:
/* 173 */         return basicSetInvariant((Expression)null, msgs);
/*     */     } 
/* 175 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 186 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 189 */         return Boolean.valueOf(isIsAccepting());
/*     */       case 6:
/* 191 */         return getInvariant();
/*     */     } 
/* 193 */     return super.eGet(featureID, resolve, coreType);
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
/* 204 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 207 */         setIsAccepting(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 6:
/* 210 */         setInvariant((Expression)newValue);
/*     */         return;
/*     */     } 
/* 213 */     super.eSet(featureID, newValue);
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
/* 224 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 227 */         setIsAccepting(false);
/*     */         return;
/*     */       case 6:
/* 230 */         setInvariant((Expression)null);
/*     */         return;
/*     */     } 
/* 233 */     super.eUnset(featureID);
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
/* 244 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 247 */         return this.isAccepting;
/*     */       case 6:
/* 249 */         return (this.invariant != null);
/*     */     } 
/* 251 */     return super.eIsSet(featureID);
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
/* 262 */     if (eIsProxy()) return super.toString();
/*     */     
/* 264 */     StringBuffer result = new StringBuffer(super.toString());
/* 265 */     result.append(" (isAccepting: ");
/* 266 */     result.append(this.isAccepting);
/* 267 */     result.append(')');
/* 268 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\impl\ContractStateImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */