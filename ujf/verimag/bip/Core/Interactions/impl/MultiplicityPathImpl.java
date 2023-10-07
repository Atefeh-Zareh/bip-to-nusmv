/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.MultiplicityPath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MultiplicityPathImpl
/*     */   extends EObjectImpl
/*     */   implements MultiplicityPath
/*     */ {
/*     */   protected EList<Expression> index;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  71 */     return InteractionsPackage.Literals.MULTIPLICITY_PATH;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Expression> getIndex() {
/*  81 */     if (this.index == null)
/*     */     {
/*  83 */       this.index = (EList<Expression>)new EObjectContainmentEList(Expression.class, (InternalEObject)this, 0);
/*     */     }
/*  85 */     return this.index;
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
/*  99 */         return ((InternalEList)getIndex()).basicRemove(otherEnd, msgs);
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
/* 115 */         return getIndex();
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
/* 132 */         getIndex().clear();
/* 133 */         getIndex().addAll((Collection)newValue);
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
/* 150 */         getIndex().clear();
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
/* 167 */         return (this.index != null && !this.index.isEmpty());
/*     */     } 
/* 169 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\MultiplicityPathImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */