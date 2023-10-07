/*     */ package ujf.verimag.bip.Extra.Time.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.AbstractTransition;
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*     */ import ujf.verimag.bip.Extra.Time.UrgencyKind;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeSpecificationImpl
/*     */   extends EObjectImpl
/*     */   implements TimeSpecification
/*     */ {
/*  59 */   protected static final UrgencyKind URGENCY_EDEFAULT = UrgencyKind.EAGER;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected UrgencyKind urgency = URGENCY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<TimedConstraint> timedConstraint;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  99 */     return TimePackage.Literals.TIME_SPECIFICATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UrgencyKind getUrgency() {
/* 109 */     return this.urgency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUrgency(UrgencyKind newUrgency) {
/* 119 */     UrgencyKind oldUrgency = this.urgency;
/* 120 */     this.urgency = (newUrgency == null) ? URGENCY_EDEFAULT : newUrgency;
/* 121 */     if (eNotificationRequired()) {
/* 122 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldUrgency, this.urgency));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractTransition getTransition() {
/* 132 */     if (eContainerFeatureID() != 1) return null; 
/* 133 */     return (AbstractTransition)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTransition(AbstractTransition newTransition, NotificationChain msgs) {
/* 143 */     msgs = eBasicSetContainer((InternalEObject)newTransition, 1, msgs);
/* 144 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransition(AbstractTransition newTransition) {
/* 154 */     if (newTransition != eInternalContainer() || (eContainerFeatureID() != 1 && newTransition != null)) {
/*     */       
/* 156 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newTransition))
/* 157 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 158 */       NotificationChain msgs = null;
/* 159 */       if (eInternalContainer() != null)
/* 160 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 161 */       if (newTransition != null)
/* 162 */         msgs = ((InternalEObject)newTransition).eInverseAdd((InternalEObject)this, 7, AbstractTransition.class, msgs); 
/* 163 */       msgs = basicSetTransition(newTransition, msgs);
/* 164 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 166 */     } else if (eNotificationRequired()) {
/* 167 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newTransition, newTransition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<TimedConstraint> getTimedConstraint() {
/* 177 */     if (this.timedConstraint == null)
/*     */     {
/* 179 */       this.timedConstraint = (EList<TimedConstraint>)new EObjectContainmentWithInverseEList(TimedConstraint.class, (InternalEObject)this, 2, 3);
/*     */     }
/* 181 */     return this.timedConstraint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 193 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 196 */         if (eInternalContainer() != null)
/* 197 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 198 */         return basicSetTransition((AbstractTransition)otherEnd, msgs);
/*     */       case 2:
/* 200 */         return ((InternalEList)getTimedConstraint()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 202 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 213 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 216 */         return basicSetTransition((AbstractTransition)null, msgs);
/*     */       case 2:
/* 218 */         return ((InternalEList)getTimedConstraint()).basicRemove(otherEnd, msgs);
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
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 231 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 1:
/* 234 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 7, AbstractTransition.class, msgs);
/*     */     } 
/* 236 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 247 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 250 */         return getUrgency();
/*     */       case 1:
/* 252 */         return getTransition();
/*     */       case 2:
/* 254 */         return getTimedConstraint();
/*     */     } 
/* 256 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
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
/* 271 */         setUrgency((UrgencyKind)newValue);
/*     */         return;
/*     */       case 1:
/* 274 */         setTransition((AbstractTransition)newValue);
/*     */         return;
/*     */       case 2:
/* 277 */         getTimedConstraint().clear();
/* 278 */         getTimedConstraint().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 281 */     super.eSet(featureID, newValue);
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
/* 292 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 295 */         setUrgency(URGENCY_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 298 */         setTransition((AbstractTransition)null);
/*     */         return;
/*     */       case 2:
/* 301 */         getTimedConstraint().clear();
/*     */         return;
/*     */     } 
/* 304 */     super.eUnset(featureID);
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
/* 315 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 318 */         return (this.urgency != URGENCY_EDEFAULT);
/*     */       case 1:
/* 320 */         return (getTransition() != null);
/*     */       case 2:
/* 322 */         return (this.timedConstraint != null && !this.timedConstraint.isEmpty());
/*     */     } 
/* 324 */     return super.eIsSet(featureID);
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
/* 335 */     if (eIsProxy()) return super.toString();
/*     */     
/* 337 */     StringBuffer result = new StringBuffer(super.toString());
/* 338 */     result.append(" (urgency: ");
/* 339 */     result.append(this.urgency);
/* 340 */     result.append(')');
/* 341 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\impl\TimeSpecificationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */