/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CAssignType;
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
/*     */ public class CAssignStmImpl
/*     */   extends CStmImpl
/*     */   implements CAssignStm
/*     */ {
/*     */   protected CExpression source;
/*     */   protected CExpression target;
/*  67 */   protected static final CAssignType TYPE_EDEFAULT = CAssignType.ASSIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   protected CAssignType type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  95 */     return CmodelPackage.Literals.CASSIGN_STM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getSource() {
/* 104 */     return this.source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetSource(CExpression newSource, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 113 */     CExpression oldSource = this.source;
/* 114 */     this.source = newSource;
/* 115 */     if (eNotificationRequired()) {
/* 116 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldSource, newSource);
/* 117 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 119 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(CExpression newSource) {
/* 128 */     if (newSource != this.source) {
/* 129 */       NotificationChain msgs = null;
/* 130 */       if (this.source != null)
/* 131 */         msgs = ((InternalEObject)this.source).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 132 */       if (newSource != null)
/* 133 */         msgs = ((InternalEObject)newSource).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 134 */       msgs = basicSetSource(newSource, msgs);
/* 135 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 137 */     } else if (eNotificationRequired()) {
/* 138 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newSource, newSource));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getTarget() {
/* 147 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTarget(CExpression newTarget, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 156 */     CExpression oldTarget = this.target;
/* 157 */     this.target = newTarget;
/* 158 */     if (eNotificationRequired()) {
/* 159 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldTarget, newTarget);
/* 160 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 162 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(CExpression newTarget) {
/* 171 */     if (newTarget != this.target) {
/* 172 */       NotificationChain msgs = null;
/* 173 */       if (this.target != null)
/* 174 */         msgs = ((InternalEObject)this.target).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 175 */       if (newTarget != null)
/* 176 */         msgs = ((InternalEObject)newTarget).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 177 */       msgs = basicSetTarget(newTarget, msgs);
/* 178 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 180 */     } else if (eNotificationRequired()) {
/* 181 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newTarget, newTarget));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CAssignType getType() {
/* 190 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(CAssignType newType) {
/* 199 */     CAssignType oldType = this.type;
/* 200 */     this.type = (newType == null) ? TYPE_EDEFAULT : newType;
/* 201 */     if (eNotificationRequired()) {
/* 202 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 212 */     switch (featureID) {
/*     */       case 0:
/* 214 */         return basicSetSource((CExpression)null, msgs);
/*     */       case 1:
/* 216 */         return basicSetTarget((CExpression)null, msgs);
/*     */     } 
/* 218 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 228 */     switch (featureID) {
/*     */       case 0:
/* 230 */         return getSource();
/*     */       case 1:
/* 232 */         return getTarget();
/*     */       case 2:
/* 234 */         return getType();
/*     */     } 
/* 236 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 246 */     switch (featureID) {
/*     */       case 0:
/* 248 */         setSource((CExpression)newValue);
/*     */         return;
/*     */       case 1:
/* 251 */         setTarget((CExpression)newValue);
/*     */         return;
/*     */       case 2:
/* 254 */         setType((CAssignType)newValue);
/*     */         return;
/*     */     } 
/* 257 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 267 */     switch (featureID) {
/*     */       case 0:
/* 269 */         setSource((CExpression)null);
/*     */         return;
/*     */       case 1:
/* 272 */         setTarget((CExpression)null);
/*     */         return;
/*     */       case 2:
/* 275 */         setType(TYPE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 278 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 288 */     switch (featureID) {
/*     */       case 0:
/* 290 */         return (this.source != null);
/*     */       case 1:
/* 292 */         return (this.target != null);
/*     */       case 2:
/* 294 */         return (this.type != TYPE_EDEFAULT);
/*     */     } 
/* 296 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 306 */     if (eIsProxy()) return super.toString();
/*     */     
/* 308 */     StringBuffer result = new StringBuffer(super.toString());
/* 309 */     result.append(" (type: ");
/* 310 */     result.append(this.type);
/* 311 */     result.append(')');
/* 312 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CAssignStmImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */