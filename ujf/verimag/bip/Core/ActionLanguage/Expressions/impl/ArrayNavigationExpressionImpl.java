/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayNavigationExpressionImpl
/*     */   extends DataNavigationExpressionImpl
/*     */   implements ArrayNavigationExpression
/*     */ {
/*     */   protected Expression index;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  65 */     return ExpressionsPackage.Literals.ARRAY_NAVIGATION_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getIndex() {
/*  75 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetIndex(Expression newIndex, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  85 */     Expression oldIndex = this.index;
/*  86 */     this.index = newIndex;
/*  87 */     if (eNotificationRequired()) {
/*     */       
/*  89 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldIndex, newIndex);
/*  90 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  92 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndex(Expression newIndex) {
/* 102 */     if (newIndex != this.index) {
/*     */       
/* 104 */       NotificationChain msgs = null;
/* 105 */       if (this.index != null)
/* 106 */         msgs = ((InternalEObject)this.index).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 107 */       if (newIndex != null)
/* 108 */         msgs = ((InternalEObject)newIndex).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 109 */       msgs = basicSetIndex(newIndex, msgs);
/* 110 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 112 */     } else if (eNotificationRequired()) {
/* 113 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newIndex, newIndex));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 124 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 127 */         return basicSetIndex((Expression)null, msgs);
/*     */     } 
/* 129 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 140 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 143 */         return getIndex();
/*     */     } 
/* 145 */     return super.eGet(featureID, resolve, coreType);
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
/* 156 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 159 */         setIndex((Expression)newValue);
/*     */         return;
/*     */     } 
/* 162 */     super.eSet(featureID, newValue);
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
/* 173 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 176 */         setIndex((Expression)null);
/*     */         return;
/*     */     } 
/* 179 */     super.eUnset(featureID);
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
/* 190 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 193 */         return (this.index != null);
/*     */     } 
/* 195 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\ArrayNavigationExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */