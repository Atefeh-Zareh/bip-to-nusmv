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
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.NamedElementImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.MultiplicityElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MultiplicityElementImpl
/*     */   extends NamedElementImpl
/*     */   implements MultiplicityElement
/*     */ {
/*     */   protected EList<Expression> multiplicitySpecification;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  71 */     return InteractionsPackage.Literals.MULTIPLICITY_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Expression> getMultiplicitySpecification() {
/*  81 */     if (this.multiplicitySpecification == null)
/*     */     {
/*  83 */       this.multiplicitySpecification = (EList<Expression>)new EObjectContainmentEList(Expression.class, (InternalEObject)this, 2);
/*     */     }
/*  85 */     return this.multiplicitySpecification;
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
/*     */       case 2:
/*  99 */         return ((InternalEList)getMultiplicitySpecification()).basicRemove(otherEnd, msgs);
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
/*     */       case 2:
/* 115 */         return getMultiplicitySpecification();
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
/*     */       case 2:
/* 132 */         getMultiplicitySpecification().clear();
/* 133 */         getMultiplicitySpecification().addAll((Collection)newValue);
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
/*     */       case 2:
/* 150 */         getMultiplicitySpecification().clear();
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
/*     */       case 2:
/* 167 */         return (this.multiplicitySpecification != null && !this.multiplicitySpecification.isEmpty());
/*     */     } 
/* 169 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\MultiplicityElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */