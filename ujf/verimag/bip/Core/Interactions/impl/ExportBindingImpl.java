/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExportBindingImpl
/*     */   extends InnerPortSpecificationImpl
/*     */   implements ExportBinding
/*     */ {
/*     */   protected EClass eStaticClass() {
/*  59 */     return InteractionsPackage.Literals.EXPORT_BINDING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getOuterPort() {
/*  69 */     if (eContainerFeatureID() != 2) return null; 
/*  70 */     return (Port)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetOuterPort(Port newOuterPort, NotificationChain msgs) {
/*  80 */     msgs = eBasicSetContainer((InternalEObject)newOuterPort, 2, msgs);
/*  81 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOuterPort(Port newOuterPort) {
/*  91 */     if (newOuterPort != eInternalContainer() || (eContainerFeatureID() != 2 && newOuterPort != null)) {
/*     */       
/*  93 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newOuterPort))
/*  94 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/*  95 */       NotificationChain msgs = null;
/*  96 */       if (eInternalContainer() != null)
/*  97 */         msgs = eBasicRemoveFromContainer(msgs); 
/*  98 */       if (newOuterPort != null)
/*  99 */         msgs = ((InternalEObject)newOuterPort).eInverseAdd((InternalEObject)this, 3, Port.class, msgs); 
/* 100 */       msgs = basicSetOuterPort(newOuterPort, msgs);
/* 101 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 103 */     } else if (eNotificationRequired()) {
/* 104 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newOuterPort, newOuterPort));
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
/* 115 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 118 */         if (eInternalContainer() != null)
/* 119 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 120 */         return basicSetOuterPort((Port)otherEnd, msgs);
/*     */     } 
/* 122 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 133 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 136 */         return basicSetOuterPort((Port)null, msgs);
/*     */     } 
/* 138 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 149 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 2:
/* 152 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 3, Port.class, msgs);
/*     */     } 
/* 154 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 165 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 168 */         return getOuterPort();
/*     */     } 
/* 170 */     return super.eGet(featureID, resolve, coreType);
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
/* 181 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 184 */         setOuterPort((Port)newValue);
/*     */         return;
/*     */     } 
/* 187 */     super.eSet(featureID, newValue);
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
/* 198 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 201 */         setOuterPort((Port)null);
/*     */         return;
/*     */     } 
/* 204 */     super.eUnset(featureID);
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
/* 215 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 218 */         return (getOuterPort() != null);
/*     */     } 
/* 220 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 231 */     if (baseClass == Binding.class) {
/*     */       
/* 233 */       switch (derivedFeatureID) {
/*     */         case 2:
/* 235 */           return 0;
/* 236 */       }  return -1;
/*     */     } 
/*     */     
/* 239 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 250 */     if (baseClass == Binding.class) {
/*     */       
/* 252 */       switch (baseFeatureID) {
/*     */         case 0:
/* 254 */           return 2;
/* 255 */       }  return -1;
/*     */     } 
/*     */     
/* 258 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\ExportBindingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */