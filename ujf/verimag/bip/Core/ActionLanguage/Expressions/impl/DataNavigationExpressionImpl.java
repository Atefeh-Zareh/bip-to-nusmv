/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DataNavigationExpressionImpl
/*     */   extends DataReferenceImpl
/*     */   implements DataNavigationExpression
/*     */ {
/*     */   protected DataReference navigated;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  64 */     return ExpressionsPackage.Literals.DATA_NAVIGATION_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataReference getNavigated() {
/*  74 */     return this.navigated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetNavigated(DataReference newNavigated, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  84 */     DataReference oldNavigated = this.navigated;
/*  85 */     this.navigated = newNavigated;
/*  86 */     if (eNotificationRequired()) {
/*     */       
/*  88 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldNavigated, newNavigated);
/*  89 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  91 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNavigated(DataReference newNavigated) {
/* 101 */     if (newNavigated != this.navigated) {
/*     */       
/* 103 */       NotificationChain msgs = null;
/* 104 */       if (this.navigated != null)
/* 105 */         msgs = ((InternalEObject)this.navigated).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 106 */       if (newNavigated != null)
/* 107 */         msgs = ((InternalEObject)newNavigated).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 108 */       msgs = basicSetNavigated(newNavigated, msgs);
/* 109 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 111 */     } else if (eNotificationRequired()) {
/* 112 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newNavigated, newNavigated));
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
/* 123 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 126 */         return basicSetNavigated((DataReference)null, msgs);
/*     */     } 
/* 128 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 139 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 142 */         return getNavigated();
/*     */     } 
/* 144 */     return super.eGet(featureID, resolve, coreType);
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
/* 155 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 158 */         setNavigated((DataReference)newValue);
/*     */         return;
/*     */     } 
/* 161 */     super.eSet(featureID, newValue);
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
/* 172 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 175 */         setNavigated((DataReference)null);
/*     */         return;
/*     */     } 
/* 178 */     super.eUnset(featureID);
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
/* 189 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 192 */         return (this.navigated != null);
/*     */     } 
/* 194 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\DataNavigationExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */