/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VariableImpl
/*     */   extends DataTypedElementImpl
/*     */   implements Variable
/*     */ {
/*     */   protected Expression initialValue;
/*     */   protected static final boolean IS_EXTERNAL_EDEFAULT = false;
/*     */   protected boolean isExternal = false;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  91 */     return BehaviorsPackage.Literals.VARIABLE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getConnectorType() {
/* 101 */     if (eContainerFeatureID() != 4) return null; 
/* 102 */     return (ConnectorType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConnectorType(ConnectorType newConnectorType, NotificationChain msgs) {
/* 112 */     msgs = eBasicSetContainer((InternalEObject)newConnectorType, 4, msgs);
/* 113 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectorType(ConnectorType newConnectorType) {
/* 123 */     if (newConnectorType != eInternalContainer() || (eContainerFeatureID() != 4 && newConnectorType != null)) {
/*     */       
/* 125 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newConnectorType))
/* 126 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 127 */       NotificationChain msgs = null;
/* 128 */       if (eInternalContainer() != null)
/* 129 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 130 */       if (newConnectorType != null)
/* 131 */         msgs = ((InternalEObject)newConnectorType).eInverseAdd((InternalEObject)this, 12, ConnectorType.class, msgs); 
/* 132 */       msgs = basicSetConnectorType(newConnectorType, msgs);
/* 133 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 135 */     } else if (eNotificationRequired()) {
/* 136 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newConnectorType, newConnectorType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getInitialValue() {
/* 146 */     return this.initialValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInitialValue(Expression newInitialValue, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 156 */     Expression oldInitialValue = this.initialValue;
/* 157 */     this.initialValue = newInitialValue;
/* 158 */     if (eNotificationRequired()) {
/*     */       
/* 160 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 5, oldInitialValue, newInitialValue);
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
/*     */   public void setInitialValue(Expression newInitialValue) {
/* 173 */     if (newInitialValue != this.initialValue) {
/*     */       
/* 175 */       NotificationChain msgs = null;
/* 176 */       if (this.initialValue != null)
/* 177 */         msgs = ((InternalEObject)this.initialValue).eInverseRemove((InternalEObject)this, -6, null, msgs); 
/* 178 */       if (newInitialValue != null)
/* 179 */         msgs = ((InternalEObject)newInitialValue).eInverseAdd((InternalEObject)this, -6, null, msgs); 
/* 180 */       msgs = basicSetInitialValue(newInitialValue, msgs);
/* 181 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 183 */     } else if (eNotificationRequired()) {
/* 184 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, newInitialValue, newInitialValue));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsExternal() {
/* 194 */     return this.isExternal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsExternal(boolean newIsExternal) {
/* 204 */     boolean oldIsExternal = this.isExternal;
/* 205 */     this.isExternal = newIsExternal;
/* 206 */     if (eNotificationRequired()) {
/* 207 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, oldIsExternal, this.isExternal));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 218 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 221 */         if (eInternalContainer() != null)
/* 222 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 223 */         return basicSetConnectorType((ConnectorType)otherEnd, msgs);
/*     */     } 
/* 225 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 236 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 239 */         return basicSetConnectorType((ConnectorType)null, msgs);
/*     */       case 5:
/* 241 */         return basicSetInitialValue((Expression)null, msgs);
/*     */     } 
/* 243 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 254 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 4:
/* 257 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 12, ConnectorType.class, msgs);
/*     */     } 
/* 259 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 270 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 273 */         return getConnectorType();
/*     */       case 5:
/* 275 */         return getInitialValue();
/*     */       case 6:
/* 277 */         return Boolean.valueOf(isIsExternal());
/*     */     } 
/* 279 */     return super.eGet(featureID, resolve, coreType);
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
/* 290 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 293 */         setConnectorType((ConnectorType)newValue);
/*     */         return;
/*     */       case 5:
/* 296 */         setInitialValue((Expression)newValue);
/*     */         return;
/*     */       case 6:
/* 299 */         setIsExternal(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 302 */     super.eSet(featureID, newValue);
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
/* 313 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 316 */         setConnectorType((ConnectorType)null);
/*     */         return;
/*     */       case 5:
/* 319 */         setInitialValue((Expression)null);
/*     */         return;
/*     */       case 6:
/* 322 */         setIsExternal(false);
/*     */         return;
/*     */     } 
/* 325 */     super.eUnset(featureID);
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
/* 336 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 339 */         return (getConnectorType() != null);
/*     */       case 5:
/* 341 */         return (this.initialValue != null);
/*     */       case 6:
/* 343 */         return this.isExternal;
/*     */     } 
/* 345 */     return super.eIsSet(featureID);
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
/* 356 */     if (eIsProxy()) return super.toString();
/*     */     
/* 358 */     StringBuffer result = new StringBuffer(super.toString());
/* 359 */     result.append(" (isExternal: ");
/* 360 */     result.append(this.isExternal);
/* 361 */     result.append(')');
/* 362 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\VariableImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */