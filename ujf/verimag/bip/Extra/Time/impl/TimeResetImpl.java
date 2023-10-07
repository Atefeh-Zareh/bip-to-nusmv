/*     */ package ujf.verimag.bip.Extra.Time.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeResetImpl
/*     */   extends EObjectImpl
/*     */   implements TimeReset
/*     */ {
/*     */   protected EList<VariableReference> clock;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  71 */     return TimePackage.Literals.TIME_RESET;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<VariableReference> getClock() {
/*  81 */     if (this.clock == null)
/*     */     {
/*  83 */       this.clock = (EList<VariableReference>)new EObjectContainmentEList(VariableReference.class, (InternalEObject)this, 0);
/*     */     }
/*  85 */     return this.clock;
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
/*  96 */     switch (featureID) {
/*     */       
/*     */       case 0:
/*  99 */         return ((InternalEList)getClock()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 101 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 112 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 115 */         return getClock();
/*     */     } 
/* 117 */     return super.eGet(featureID, resolve, coreType);
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
/* 129 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 132 */         getClock().clear();
/* 133 */         getClock().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 136 */     super.eSet(featureID, newValue);
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
/* 147 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 150 */         getClock().clear();
/*     */         return;
/*     */     } 
/* 153 */     super.eUnset(featureID);
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
/* 164 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 167 */         return (this.clock != null && !this.clock.isEmpty());
/*     */     } 
/* 169 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\impl\TimeResetImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */