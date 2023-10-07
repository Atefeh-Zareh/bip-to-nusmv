/*     */ package ujf.verimag.bip.Core.Priorities.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConnectorTypeReferenceImpl
/*     */   extends PriorityElementImpl
/*     */   implements ConnectorTypeReference
/*     */ {
/*     */   protected ConnectorType target;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  64 */     return PrioritiesPackage.Literals.CONNECTOR_TYPE_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getTarget() {
/*  74 */     if (this.target != null && this.target.eIsProxy()) {
/*     */       
/*  76 */       InternalEObject oldTarget = (InternalEObject)this.target;
/*  77 */       this.target = (ConnectorType)eResolveProxy(oldTarget);
/*  78 */       if (this.target != oldTarget)
/*     */       {
/*  80 */         if (eNotificationRequired())
/*  81 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTarget, this.target)); 
/*     */       }
/*     */     } 
/*  84 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType basicGetTarget() {
/*  94 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(ConnectorType newTarget) {
/* 104 */     ConnectorType oldTarget = this.target;
/* 105 */     this.target = newTarget;
/* 106 */     if (eNotificationRequired()) {
/* 107 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTarget, this.target));
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
/* 118 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 121 */         if (resolve) return getTarget(); 
/* 122 */         return basicGetTarget();
/*     */     } 
/* 124 */     return super.eGet(featureID, resolve, coreType);
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
/* 135 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 138 */         setTarget((ConnectorType)newValue);
/*     */         return;
/*     */     } 
/* 141 */     super.eSet(featureID, newValue);
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
/* 152 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 155 */         setTarget((ConnectorType)null);
/*     */         return;
/*     */     } 
/* 158 */     super.eUnset(featureID);
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
/* 169 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 172 */         return (this.target != null);
/*     */     } 
/* 174 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\impl\ConnectorTypeReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */