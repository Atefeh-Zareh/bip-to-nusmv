/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PartImpl
/*     */   extends MultiplicityElementImpl
/*     */   implements Part
/*     */ {
/*     */   protected EList<Expression> actualData;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  69 */     return InteractionsPackage.Literals.PART;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Expression> getActualData() {
/*  79 */     if (this.actualData == null)
/*     */     {
/*  81 */       this.actualData = (EList<Expression>)new EObjectContainmentEList(Expression.class, (InternalEObject)this, 3);
/*     */     }
/*  83 */     return this.actualData;
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
/*  94 */     switch (featureID) {
/*     */       
/*     */       case 3:
/*  97 */         return ((InternalEList)getActualData()).basicRemove(otherEnd, msgs);
/*     */     } 
/*  99 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 110 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 113 */         return getActualData();
/*     */     } 
/* 115 */     return super.eGet(featureID, resolve, coreType);
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
/* 127 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 130 */         getActualData().clear();
/* 131 */         getActualData().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 134 */     super.eSet(featureID, newValue);
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
/* 145 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 148 */         getActualData().clear();
/*     */         return;
/*     */     } 
/* 151 */     super.eUnset(featureID);
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
/* 162 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 165 */         return (this.actualData != null && !this.actualData.isEmpty());
/*     */     } 
/* 167 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\PartImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */