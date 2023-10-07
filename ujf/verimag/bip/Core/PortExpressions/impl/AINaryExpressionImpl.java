/*     */ package ujf.verimag.bip.Core.PortExpressions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AINaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AINaryExpressionImpl
/*     */   extends AIExpressionImpl
/*     */   implements AINaryExpression
/*     */ {
/*     */   protected EList<AIExpression> operand;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  68 */     return PortExpressionsPackage.Literals.AI_NARY_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<AIExpression> getOperand() {
/*  78 */     if (this.operand == null)
/*     */     {
/*  80 */       this.operand = (EList<AIExpression>)new EObjectContainmentEList(AIExpression.class, (InternalEObject)this, 0);
/*     */     }
/*  82 */     return this.operand;
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
/*     */       case 0:
/*  96 */         return ((InternalEList)getOperand()).basicRemove(otherEnd, msgs);
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
/*     */       case 0:
/* 112 */         return getOperand();
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
/*     */       case 0:
/* 129 */         getOperand().clear();
/* 130 */         getOperand().addAll((Collection)newValue);
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
/*     */       case 0:
/* 147 */         getOperand().clear();
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
/*     */       case 0:
/* 164 */         return (this.operand != null && !this.operand.isEmpty());
/*     */     } 
/* 166 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\impl\AINaryExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */