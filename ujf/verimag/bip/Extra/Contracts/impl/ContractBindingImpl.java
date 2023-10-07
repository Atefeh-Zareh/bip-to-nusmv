/*     */ package ujf.verimag.bip.Extra.Contracts.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.impl.ExportBindingImpl;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractBinding;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContractBindingImpl
/*     */   extends ExportBindingImpl
/*     */   implements ContractBinding
/*     */ {
/*     */   protected Port contractedPort;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  66 */     return ContractsPackage.Literals.CONTRACT_BINDING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getContractedPort() {
/*  76 */     if (this.contractedPort != null && this.contractedPort.eIsProxy()) {
/*     */       
/*  78 */       InternalEObject oldContractedPort = (InternalEObject)this.contractedPort;
/*  79 */       this.contractedPort = (Port)eResolveProxy(oldContractedPort);
/*  80 */       if (this.contractedPort != oldContractedPort)
/*     */       {
/*  82 */         if (eNotificationRequired())
/*  83 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 3, oldContractedPort, this.contractedPort)); 
/*     */       }
/*     */     } 
/*  86 */     return this.contractedPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port basicGetContractedPort() {
/*  96 */     return this.contractedPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContractedPort(Port newContractedPort) {
/* 106 */     Port oldContractedPort = this.contractedPort;
/* 107 */     this.contractedPort = newContractedPort;
/* 108 */     if (eNotificationRequired()) {
/* 109 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldContractedPort, this.contractedPort));
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
/*     */       case 3:
/* 123 */         if (resolve) return getContractedPort(); 
/* 124 */         return basicGetContractedPort();
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
/*     */       case 3:
/* 140 */         setContractedPort((Port)newValue);
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
/*     */       case 3:
/* 157 */         setContractedPort((Port)null);
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
/*     */       case 3:
/* 174 */         return (this.contractedPort != null);
/*     */     } 
/* 176 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\impl\ContractBindingImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */