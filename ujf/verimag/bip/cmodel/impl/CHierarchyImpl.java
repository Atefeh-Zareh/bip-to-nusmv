/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CHierarchy;
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
/*     */ public abstract class CHierarchyImpl
/*     */   extends CNavigationImpl
/*     */   implements CHierarchy
/*     */ {
/*     */   protected CExpression field;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  62 */     return CmodelPackage.Literals.CHIERARCHY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getField() {
/*  71 */     return this.field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetField(CExpression newField, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  80 */     CExpression oldField = this.field;
/*  81 */     this.field = newField;
/*  82 */     if (eNotificationRequired()) {
/*  83 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldField, newField);
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
/*     */   public void setField(CExpression newField) {
/*  95 */     if (newField != this.field) {
/*  96 */       NotificationChain msgs = null;
/*  97 */       if (this.field != null)
/*  98 */         msgs = ((InternalEObject)this.field).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/*  99 */       if (newField != null)
/* 100 */         msgs = ((InternalEObject)newField).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 101 */       msgs = basicSetField(newField, msgs);
/* 102 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 104 */     } else if (eNotificationRequired()) {
/* 105 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newField, newField));
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
/* 117 */         return basicSetField((CExpression)null, msgs);
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
/* 131 */         return getField();
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
/* 145 */         setField((CExpression)newValue);
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
/* 160 */         setField((CExpression)null);
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
/* 175 */         return (this.field != null);
/*     */     } 
/* 177 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CHierarchyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */