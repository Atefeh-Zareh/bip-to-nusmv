/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CIfStm;
/*     */ import ujf.verimag.bip.cmodel.CStm;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CIfStmImpl
/*     */   extends CConditionalStmImpl
/*     */   implements CIfStm
/*     */ {
/*     */   protected CStm ifCase;
/*     */   protected CStm elseCase;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  74 */     return CmodelPackage.Literals.CIF_STM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CStm getIfCase() {
/*  83 */     return this.ifCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetIfCase(CStm newIfCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  92 */     CStm oldIfCase = this.ifCase;
/*  93 */     this.ifCase = newIfCase;
/*  94 */     if (eNotificationRequired()) {
/*  95 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldIfCase, newIfCase);
/*  96 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  98 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIfCase(CStm newIfCase) {
/* 107 */     if (newIfCase != this.ifCase) {
/* 108 */       NotificationChain msgs = null;
/* 109 */       if (this.ifCase != null)
/* 110 */         msgs = ((InternalEObject)this.ifCase).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 111 */       if (newIfCase != null)
/* 112 */         msgs = ((InternalEObject)newIfCase).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 113 */       msgs = basicSetIfCase(newIfCase, msgs);
/* 114 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 116 */     } else if (eNotificationRequired()) {
/* 117 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newIfCase, newIfCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CStm getElseCase() {
/* 126 */     return this.elseCase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetElseCase(CStm newElseCase, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 135 */     CStm oldElseCase = this.elseCase;
/* 136 */     this.elseCase = newElseCase;
/* 137 */     if (eNotificationRequired()) {
/* 138 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldElseCase, newElseCase);
/* 139 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 141 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElseCase(CStm newElseCase) {
/* 150 */     if (newElseCase != this.elseCase) {
/* 151 */       NotificationChain msgs = null;
/* 152 */       if (this.elseCase != null)
/* 153 */         msgs = ((InternalEObject)this.elseCase).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 154 */       if (newElseCase != null)
/* 155 */         msgs = ((InternalEObject)newElseCase).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 156 */       msgs = basicSetElseCase(newElseCase, msgs);
/* 157 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 159 */     } else if (eNotificationRequired()) {
/* 160 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newElseCase, newElseCase));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 170 */     switch (featureID) {
/*     */       case 1:
/* 172 */         return basicSetIfCase((CStm)null, msgs);
/*     */       case 2:
/* 174 */         return basicSetElseCase((CStm)null, msgs);
/*     */     } 
/* 176 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 186 */     switch (featureID) {
/*     */       case 1:
/* 188 */         return getIfCase();
/*     */       case 2:
/* 190 */         return getElseCase();
/*     */     } 
/* 192 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 202 */     switch (featureID) {
/*     */       case 1:
/* 204 */         setIfCase((CStm)newValue);
/*     */         return;
/*     */       case 2:
/* 207 */         setElseCase((CStm)newValue);
/*     */         return;
/*     */     } 
/* 210 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 220 */     switch (featureID) {
/*     */       case 1:
/* 222 */         setIfCase((CStm)null);
/*     */         return;
/*     */       case 2:
/* 225 */         setElseCase((CStm)null);
/*     */         return;
/*     */     } 
/* 228 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 238 */     switch (featureID) {
/*     */       case 1:
/* 240 */         return (this.ifCase != null);
/*     */       case 2:
/* 242 */         return (this.elseCase != null);
/*     */     } 
/* 244 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CIfStmImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */