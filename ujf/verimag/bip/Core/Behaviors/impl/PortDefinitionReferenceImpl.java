/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.PortExpressions.impl.PortReferenceImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortDefinitionReferenceImpl
/*     */   extends PortReferenceImpl
/*     */   implements PortDefinitionReference
/*     */ {
/*     */   protected PortDefinition target;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  65 */     return BehaviorsPackage.Literals.PORT_DEFINITION_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinition getTarget() {
/*  75 */     if (this.target != null && this.target.eIsProxy()) {
/*     */       
/*  77 */       InternalEObject oldTarget = (InternalEObject)this.target;
/*  78 */       this.target = (PortDefinition)eResolveProxy(oldTarget);
/*  79 */       if (this.target != oldTarget)
/*     */       {
/*  81 */         if (eNotificationRequired())
/*  82 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTarget, this.target)); 
/*     */       }
/*     */     } 
/*  85 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinition basicGetTarget() {
/*  95 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(PortDefinition newTarget) {
/* 105 */     PortDefinition oldTarget = this.target;
/* 106 */     this.target = newTarget;
/* 107 */     if (eNotificationRequired()) {
/* 108 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTarget, this.target));
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
/* 119 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 122 */         if (resolve) return getTarget(); 
/* 123 */         return basicGetTarget();
/*     */     } 
/* 125 */     return super.eGet(featureID, resolve, coreType);
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
/* 136 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 139 */         setTarget((PortDefinition)newValue);
/*     */         return;
/*     */     } 
/* 142 */     super.eSet(featureID, newValue);
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
/* 153 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 156 */         setTarget((PortDefinition)null);
/*     */         return;
/*     */     } 
/* 159 */     super.eUnset(featureID);
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
/* 170 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 173 */         return (this.target != null);
/*     */     } 
/* 175 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\PortDefinitionReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */