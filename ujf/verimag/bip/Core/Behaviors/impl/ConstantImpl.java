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
/*     */ import ujf.verimag.bip.Core.Behaviors.Constant;
/*     */ import ujf.verimag.bip.Core.Behaviors.PartType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstantImpl
/*     */   extends VariableImpl
/*     */   implements Constant
/*     */ {
/*     */   protected EClass eStaticClass() {
/*  56 */     return BehaviorsPackage.Literals.CONSTANT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartType getPartType() {
/*  66 */     if (eContainerFeatureID() != 7) return null; 
/*  67 */     return (PartType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetPartType(PartType newPartType, NotificationChain msgs) {
/*  77 */     msgs = eBasicSetContainer((InternalEObject)newPartType, 7, msgs);
/*  78 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartType(PartType newPartType) {
/*  88 */     if (newPartType != eInternalContainer() || (eContainerFeatureID() != 7 && newPartType != null)) {
/*     */       
/*  90 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newPartType))
/*  91 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/*  92 */       NotificationChain msgs = null;
/*  93 */       if (eInternalContainer() != null)
/*  94 */         msgs = eBasicRemoveFromContainer(msgs); 
/*  95 */       if (newPartType != null)
/*  96 */         msgs = ((InternalEObject)newPartType).eInverseAdd((InternalEObject)this, 5, PartType.class, msgs); 
/*  97 */       msgs = basicSetPartType(newPartType, msgs);
/*  98 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 100 */     } else if (eNotificationRequired()) {
/* 101 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 7, newPartType, newPartType));
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
/* 112 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 115 */         if (eInternalContainer() != null)
/* 116 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 117 */         return basicSetPartType((PartType)otherEnd, msgs);
/*     */     } 
/* 119 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 130 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 133 */         return basicSetPartType((PartType)null, msgs);
/*     */     } 
/* 135 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 146 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 7:
/* 149 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 5, PartType.class, msgs);
/*     */     } 
/* 151 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 162 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 165 */         return getPartType();
/*     */     } 
/* 167 */     return super.eGet(featureID, resolve, coreType);
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
/* 178 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 181 */         setPartType((PartType)newValue);
/*     */         return;
/*     */     } 
/* 184 */     super.eSet(featureID, newValue);
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
/* 195 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 198 */         setPartType((PartType)null);
/*     */         return;
/*     */     } 
/* 201 */     super.eUnset(featureID);
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
/* 212 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 215 */         return (getPartType() != null);
/*     */     } 
/* 217 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\ConstantImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */