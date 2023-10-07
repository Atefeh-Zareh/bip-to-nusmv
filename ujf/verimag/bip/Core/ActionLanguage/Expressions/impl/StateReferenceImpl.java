/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ExpressionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateReferenceImpl
/*     */   extends ExpressionImpl
/*     */   implements StateReference
/*     */ {
/*     */   protected State targetState;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  66 */     return ExpressionsPackage.Literals.STATE_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public State getTargetState() {
/*  76 */     if (this.targetState != null && this.targetState.eIsProxy()) {
/*     */       
/*  78 */       InternalEObject oldTargetState = (InternalEObject)this.targetState;
/*  79 */       this.targetState = (State)eResolveProxy(oldTargetState);
/*  80 */       if (this.targetState != oldTargetState)
/*     */       {
/*  82 */         if (eNotificationRequired())
/*  83 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTargetState, this.targetState)); 
/*     */       }
/*     */     } 
/*  86 */     return this.targetState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public State basicGetTargetState() {
/*  96 */     return this.targetState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetState(State newTargetState) {
/* 106 */     State oldTargetState = this.targetState;
/* 107 */     this.targetState = newTargetState;
/* 108 */     if (eNotificationRequired()) {
/* 109 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTargetState, this.targetState));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 120 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 123 */         if (resolve) return getTargetState(); 
/* 124 */         return basicGetTargetState();
/*     */     } 
/* 126 */     return super.eGet(featureID, resolve, coreType);
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
/* 137 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 140 */         setTargetState((State)newValue);
/*     */         return;
/*     */     } 
/* 143 */     super.eSet(featureID, newValue);
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
/* 154 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 157 */         setTargetState((State)null);
/*     */         return;
/*     */     } 
/* 160 */     super.eUnset(featureID);
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
/* 171 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 174 */         return (this.targetState != null);
/*     */     } 
/* 176 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\StateReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */