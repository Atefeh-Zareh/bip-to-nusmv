/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefinitionBindingImpl
/*     */   extends BindingImpl
/*     */   implements DefinitionBinding
/*     */ {
/*     */   protected PortDefinition definition;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  63 */     return BehaviorsPackage.Literals.DEFINITION_BINDING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinition getDefinition() {
/*  73 */     if (this.definition != null && this.definition.eIsProxy()) {
/*     */       
/*  75 */       InternalEObject oldDefinition = (InternalEObject)this.definition;
/*  76 */       this.definition = (PortDefinition)eResolveProxy(oldDefinition);
/*  77 */       if (this.definition != oldDefinition)
/*     */       {
/*  79 */         if (eNotificationRequired())
/*  80 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 1, oldDefinition, this.definition)); 
/*     */       }
/*     */     } 
/*  83 */     return this.definition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinition basicGetDefinition() {
/*  93 */     return this.definition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefinition(PortDefinition newDefinition) {
/* 103 */     PortDefinition oldDefinition = this.definition;
/* 104 */     this.definition = newDefinition;
/* 105 */     if (eNotificationRequired()) {
/* 106 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldDefinition, this.definition));
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
/* 117 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 120 */         if (resolve) return getDefinition(); 
/* 121 */         return basicGetDefinition();
/*     */     } 
/* 123 */     return super.eGet(featureID, resolve, coreType);
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
/* 134 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 137 */         setDefinition((PortDefinition)newValue);
/*     */         return;
/*     */     } 
/* 140 */     super.eSet(featureID, newValue);
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
/* 151 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 154 */         setDefinition((PortDefinition)null);
/*     */         return;
/*     */     } 
/* 157 */     super.eUnset(featureID);
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
/* 168 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 171 */         return (this.definition != null);
/*     */     } 
/* 173 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\DefinitionBindingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */