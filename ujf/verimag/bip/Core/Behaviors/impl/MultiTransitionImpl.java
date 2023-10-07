/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.MultiTransition;
/*     */ import ujf.verimag.bip.Core.Behaviors.TransitionAlternative;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiTransitionImpl
/*     */   extends AbstractTransitionImpl
/*     */   implements MultiTransition
/*     */ {
/*     */   protected EList<TransitionAlternative> alternative;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  68 */     return BehaviorsPackage.Literals.MULTI_TRANSITION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<TransitionAlternative> getAlternative() {
/*  78 */     if (this.alternative == null)
/*     */     {
/*  80 */       this.alternative = (EList<TransitionAlternative>)new EObjectContainmentEList(TransitionAlternative.class, (InternalEObject)this, 8);
/*     */     }
/*  82 */     return this.alternative;
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
/*  93 */     switch (featureID) {
/*     */       
/*     */       case 8:
/*  96 */         return ((InternalEList)getAlternative()).basicRemove(otherEnd, msgs);
/*     */     } 
/*  98 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 109 */     switch (featureID) {
/*     */       
/*     */       case 8:
/* 112 */         return getAlternative();
/*     */     } 
/* 114 */     return super.eGet(featureID, resolve, coreType);
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
/* 126 */     switch (featureID) {
/*     */       
/*     */       case 8:
/* 129 */         getAlternative().clear();
/* 130 */         getAlternative().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 133 */     super.eSet(featureID, newValue);
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
/* 144 */     switch (featureID) {
/*     */       
/*     */       case 8:
/* 147 */         getAlternative().clear();
/*     */         return;
/*     */     } 
/* 150 */     super.eUnset(featureID);
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
/* 161 */     switch (featureID) {
/*     */       
/*     */       case 8:
/* 164 */         return (this.alternative != null && !this.alternative.isEmpty());
/*     */     } 
/* 166 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\MultiTransitionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */