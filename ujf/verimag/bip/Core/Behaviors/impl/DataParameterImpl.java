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
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterDirectionKind;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataParameterImpl
/*     */   extends DataTypedElementImpl
/*     */   implements DataParameter
/*     */ {
/*  48 */   protected static final ParameterDirectionKind DIRECTION_EDEFAULT = ParameterDirectionKind.INOUT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected ParameterDirectionKind direction = DIRECTION_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  78 */     return BehaviorsPackage.Literals.DATA_PARAMETER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParameterDirectionKind getDirection() {
/*  88 */     return this.direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(ParameterDirectionKind newDirection) {
/*  98 */     ParameterDirectionKind oldDirection = this.direction;
/*  99 */     this.direction = (newDirection == null) ? DIRECTION_EDEFAULT : newDirection;
/* 100 */     if (eNotificationRequired()) {
/* 101 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, oldDirection, this.direction));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParameterizedElement getParameterizedElement() {
/* 111 */     if (eContainerFeatureID() != 5) return null; 
/* 112 */     return (ParameterizedElement)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetParameterizedElement(ParameterizedElement newParameterizedElement, NotificationChain msgs) {
/* 122 */     msgs = eBasicSetContainer((InternalEObject)newParameterizedElement, 5, msgs);
/* 123 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameterizedElement(ParameterizedElement newParameterizedElement) {
/* 133 */     if (newParameterizedElement != eInternalContainer() || (eContainerFeatureID() != 5 && newParameterizedElement != null)) {
/*     */       
/* 135 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newParameterizedElement))
/* 136 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 137 */       NotificationChain msgs = null;
/* 138 */       if (eInternalContainer() != null)
/* 139 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 140 */       if (newParameterizedElement != null)
/* 141 */         msgs = ((InternalEObject)newParameterizedElement).eInverseAdd((InternalEObject)this, 0, ParameterizedElement.class, msgs); 
/* 142 */       msgs = basicSetParameterizedElement(newParameterizedElement, msgs);
/* 143 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 145 */     } else if (eNotificationRequired()) {
/* 146 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, newParameterizedElement, newParameterizedElement));
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
/* 157 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 160 */         if (eInternalContainer() != null)
/* 161 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 162 */         return basicSetParameterizedElement((ParameterizedElement)otherEnd, msgs);
/*     */     } 
/* 164 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 175 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 178 */         return basicSetParameterizedElement((ParameterizedElement)null, msgs);
/*     */     } 
/* 180 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 191 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 5:
/* 194 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 0, ParameterizedElement.class, msgs);
/*     */     } 
/* 196 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 207 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 210 */         return getDirection();
/*     */       case 5:
/* 212 */         return getParameterizedElement();
/*     */     } 
/* 214 */     return super.eGet(featureID, resolve, coreType);
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
/* 225 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 228 */         setDirection((ParameterDirectionKind)newValue);
/*     */         return;
/*     */       case 5:
/* 231 */         setParameterizedElement((ParameterizedElement)newValue);
/*     */         return;
/*     */     } 
/* 234 */     super.eSet(featureID, newValue);
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
/* 245 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 248 */         setDirection(DIRECTION_EDEFAULT);
/*     */         return;
/*     */       case 5:
/* 251 */         setParameterizedElement((ParameterizedElement)null);
/*     */         return;
/*     */     } 
/* 254 */     super.eUnset(featureID);
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
/* 265 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 268 */         return (this.direction != DIRECTION_EDEFAULT);
/*     */       case 5:
/* 270 */         return (getParameterizedElement() != null);
/*     */     } 
/* 272 */     return super.eIsSet(featureID);
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
/* 283 */     if (eIsProxy()) return super.toString();
/*     */     
/* 285 */     StringBuffer result = new StringBuffer(super.toString());
/* 286 */     result.append(" (direction: ");
/* 287 */     result.append(this.direction);
/* 288 */     result.append(')');
/* 289 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\DataParameterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */