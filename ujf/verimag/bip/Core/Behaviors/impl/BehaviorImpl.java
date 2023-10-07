/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BehaviorImpl
/*     */   extends EObjectImpl
/*     */   implements Behavior
/*     */ {
/*     */   protected EClass eStaticClass() {
/*  57 */     return BehaviorsPackage.Literals.BEHAVIOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType getAtomType() {
/*  67 */     if (eContainerFeatureID() != 0) return null; 
/*  68 */     return (AtomType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetAtomType(AtomType newAtomType, NotificationChain msgs) {
/*  78 */     msgs = eBasicSetContainer((InternalEObject)newAtomType, 0, msgs);
/*  79 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAtomType(AtomType newAtomType) {
/*  89 */     if (newAtomType != eInternalContainer() || (eContainerFeatureID() != 0 && newAtomType != null)) {
/*     */       
/*  91 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newAtomType))
/*  92 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/*  93 */       NotificationChain msgs = null;
/*  94 */       if (eInternalContainer() != null)
/*  95 */         msgs = eBasicRemoveFromContainer(msgs); 
/*  96 */       if (newAtomType != null)
/*  97 */         msgs = ((InternalEObject)newAtomType).eInverseAdd((InternalEObject)this, 12, AtomType.class, msgs); 
/*  98 */       msgs = basicSetAtomType(newAtomType, msgs);
/*  99 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 101 */     } else if (eNotificationRequired()) {
/* 102 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newAtomType, newAtomType));
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
/* 113 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 116 */         if (eInternalContainer() != null)
/* 117 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 118 */         return basicSetAtomType((AtomType)otherEnd, msgs);
/*     */     } 
/* 120 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 131 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 134 */         return basicSetAtomType((AtomType)null, msgs);
/*     */     } 
/* 136 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 147 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 0:
/* 150 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 12, AtomType.class, msgs);
/*     */     } 
/* 152 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 163 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 166 */         return getAtomType();
/*     */     } 
/* 168 */     return super.eGet(featureID, resolve, coreType);
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
/* 179 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 182 */         setAtomType((AtomType)newValue);
/*     */         return;
/*     */     } 
/* 185 */     super.eSet(featureID, newValue);
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
/* 196 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 199 */         setAtomType((AtomType)null);
/*     */         return;
/*     */     } 
/* 202 */     super.eUnset(featureID);
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
/* 213 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 216 */         return (getAtomType() != null);
/*     */     } 
/* 218 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\BehaviorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */