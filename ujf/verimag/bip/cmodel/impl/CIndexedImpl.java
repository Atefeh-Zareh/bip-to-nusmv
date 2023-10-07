/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CIndexed;
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
/*     */ public class CIndexedImpl
/*     */   extends CNavigationImpl
/*     */   implements CIndexed
/*     */ {
/*     */   protected CExpression index;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  62 */     return CmodelPackage.Literals.CINDEXED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getIndex() {
/*  71 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetIndex(CExpression newIndex, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  80 */     CExpression oldIndex = this.index;
/*  81 */     this.index = newIndex;
/*  82 */     if (eNotificationRequired()) {
/*  83 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldIndex, newIndex);
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
/*     */   public void setIndex(CExpression newIndex) {
/*  95 */     if (newIndex != this.index) {
/*  96 */       NotificationChain msgs = null;
/*  97 */       if (this.index != null)
/*  98 */         msgs = ((InternalEObject)this.index).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/*  99 */       if (newIndex != null)
/* 100 */         msgs = ((InternalEObject)newIndex).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 101 */       msgs = basicSetIndex(newIndex, msgs);
/* 102 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 104 */     } else if (eNotificationRequired()) {
/* 105 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newIndex, newIndex));
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
/*     */       case 1:
/* 117 */         return basicSetIndex((CExpression)null, msgs);
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
/*     */       case 1:
/* 131 */         return getIndex();
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
/*     */       case 1:
/* 145 */         setIndex((CExpression)newValue);
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
/*     */       case 1:
/* 160 */         setIndex((CExpression)null);
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
/*     */       case 1:
/* 175 */         return (this.index != null);
/*     */     } 
/* 177 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CIndexedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */