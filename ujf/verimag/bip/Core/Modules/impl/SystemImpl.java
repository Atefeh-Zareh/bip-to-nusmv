/*     */ package ujf.verimag.bip.Core.Modules.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SystemImpl
/*     */   extends ModuleImpl
/*     */   implements System
/*     */ {
/*     */   protected Root root;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  63 */     return ModulesPackage.Literals.SYSTEM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Root getRoot() {
/*  73 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetRoot(Root newRoot, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  83 */     Root oldRoot = this.root;
/*  84 */     this.root = newRoot;
/*  85 */     if (eNotificationRequired()) {
/*     */       
/*  87 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 7, oldRoot, newRoot);
/*  88 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/*  90 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoot(Root newRoot) {
/* 100 */     if (newRoot != this.root) {
/*     */       
/* 102 */       NotificationChain msgs = null;
/* 103 */       if (this.root != null)
/* 104 */         msgs = ((InternalEObject)this.root).eInverseRemove((InternalEObject)this, 4, Root.class, msgs); 
/* 105 */       if (newRoot != null)
/* 106 */         msgs = ((InternalEObject)newRoot).eInverseAdd((InternalEObject)this, 4, Root.class, msgs); 
/* 107 */       msgs = basicSetRoot(newRoot, msgs);
/* 108 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 110 */     } else if (eNotificationRequired()) {
/* 111 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 7, newRoot, newRoot));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 122 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 125 */         if (this.root != null)
/* 126 */           msgs = ((InternalEObject)this.root).eInverseRemove((InternalEObject)this, -8, null, msgs); 
/* 127 */         return basicSetRoot((Root)otherEnd, msgs);
/*     */     } 
/* 129 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 140 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 143 */         return basicSetRoot((Root)null, msgs);
/*     */     } 
/* 145 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 156 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 159 */         return getRoot();
/*     */     } 
/* 161 */     return super.eGet(featureID, resolve, coreType);
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
/* 172 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 175 */         setRoot((Root)newValue);
/*     */         return;
/*     */     } 
/* 178 */     super.eSet(featureID, newValue);
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
/* 189 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 192 */         setRoot((Root)null);
/*     */         return;
/*     */     } 
/* 195 */     super.eUnset(featureID);
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
/* 206 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 209 */         return (this.root != null);
/*     */     } 
/* 211 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\impl\SystemImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */