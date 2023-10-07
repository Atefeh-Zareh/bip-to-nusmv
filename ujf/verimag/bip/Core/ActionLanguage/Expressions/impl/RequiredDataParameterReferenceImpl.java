/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RequiredDataParameterReferenceImpl
/*     */   extends DataParameterSpecificationImpl
/*     */   implements RequiredDataParameterReference
/*     */ {
/*     */   protected PortParameterReference portReference;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  65 */     return ExpressionsPackage.Literals.REQUIRED_DATA_PARAMETER_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortParameterReference getPortReference() {
/*  75 */     return this.portReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetPortReference(PortParameterReference newPortReference, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/*  85 */     PortParameterReference oldPortReference = this.portReference;
/*  86 */     this.portReference = newPortReference;
/*  87 */     if (eNotificationRequired()) {
/*     */       
/*  89 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldPortReference, newPortReference);
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
/*     */   public void setPortReference(PortParameterReference newPortReference) {
/* 102 */     if (newPortReference != this.portReference) {
/*     */       
/* 104 */       NotificationChain msgs = null;
/* 105 */       if (this.portReference != null)
/* 106 */         msgs = ((InternalEObject)this.portReference).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 107 */       if (newPortReference != null)
/* 108 */         msgs = ((InternalEObject)newPortReference).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 109 */       msgs = basicSetPortReference(newPortReference, msgs);
/* 110 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 112 */     } else if (eNotificationRequired()) {
/* 113 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newPortReference, newPortReference));
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
/* 127 */         return basicSetPortReference((PortParameterReference)null, msgs);
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
/* 143 */         return getPortReference();
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
/* 159 */         setPortReference((PortParameterReference)newValue);
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
/* 176 */         setPortReference((PortParameterReference)null);
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
/* 193 */         return (this.portReference != null);
/*     */     } 
/* 195 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\RequiredDataParameterReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */