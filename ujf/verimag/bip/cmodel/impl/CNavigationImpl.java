/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CNavigation;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CNavigationImpl
/*     */   extends CExpressionImpl
/*     */   implements CNavigation
/*     */ {
/*     */   protected CExpression prefix;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  62 */     return CmodelPackage.Literals.CNAVIGATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getPrefix() {
/*  71 */     return this.prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetPrefix(CExpression newPrefix, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  80 */     CExpression oldPrefix = this.prefix;
/*  81 */     this.prefix = newPrefix;
/*  82 */     if (eNotificationRequired()) {
/*  83 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldPrefix, newPrefix);
/*  84 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  86 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(CExpression newPrefix) {
/*  95 */     if (newPrefix != this.prefix) {
/*  96 */       NotificationChain msgs = null;
/*  97 */       if (this.prefix != null)
/*  98 */         msgs = ((InternalEObject)this.prefix).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/*  99 */       if (newPrefix != null)
/* 100 */         msgs = ((InternalEObject)newPrefix).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 101 */       msgs = basicSetPrefix(newPrefix, msgs);
/* 102 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 104 */     } else if (eNotificationRequired()) {
/* 105 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newPrefix, newPrefix));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 115 */     switch (featureID) {
/*     */       case 0:
/* 117 */         return basicSetPrefix((CExpression)null, msgs);
/*     */     } 
/* 119 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 129 */     switch (featureID) {
/*     */       case 0:
/* 131 */         return getPrefix();
/*     */     } 
/* 133 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 143 */     switch (featureID) {
/*     */       case 0:
/* 145 */         setPrefix((CExpression)newValue);
/*     */         return;
/*     */     } 
/* 148 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 158 */     switch (featureID) {
/*     */       case 0:
/* 160 */         setPrefix((CExpression)null);
/*     */         return;
/*     */     } 
/* 163 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 173 */     switch (featureID) {
/*     */       case 0:
/* 175 */         return (this.prefix != null);
/*     */     } 
/* 177 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CNavigationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */