/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterSpecification;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DataParameterSpecificationImpl
/*     */   extends DataReferenceImpl
/*     */   implements DataParameterSpecification
/*     */ {
/*     */   protected DataParameter targetParameter;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  64 */     return ExpressionsPackage.Literals.DATA_PARAMETER_SPECIFICATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataParameter getTargetParameter() {
/*  74 */     if (this.targetParameter != null && this.targetParameter.eIsProxy()) {
/*     */       
/*  76 */       InternalEObject oldTargetParameter = (InternalEObject)this.targetParameter;
/*  77 */       this.targetParameter = (DataParameter)eResolveProxy(oldTargetParameter);
/*  78 */       if (this.targetParameter != oldTargetParameter)
/*     */       {
/*  80 */         if (eNotificationRequired())
/*  81 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTargetParameter, this.targetParameter)); 
/*     */       }
/*     */     } 
/*  84 */     return this.targetParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataParameter basicGetTargetParameter() {
/*  94 */     return this.targetParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetParameter(DataParameter newTargetParameter) {
/* 104 */     DataParameter oldTargetParameter = this.targetParameter;
/* 105 */     this.targetParameter = newTargetParameter;
/* 106 */     if (eNotificationRequired()) {
/* 107 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTargetParameter, this.targetParameter));
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
/* 121 */         if (resolve) return getTargetParameter(); 
/* 122 */         return basicGetTargetParameter();
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
/* 138 */         setTargetParameter((DataParameter)newValue);
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
/* 155 */         setTargetParameter((DataParameter)null);
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
/* 172 */         return (this.targetParameter != null);
/*     */     } 
/* 174 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\DataParameterSpecificationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */