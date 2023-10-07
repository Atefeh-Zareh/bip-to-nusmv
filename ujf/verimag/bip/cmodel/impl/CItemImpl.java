/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.cmodel.CItem;
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
/*     */ public class CItemImpl
/*     */   extends EObjectImpl
/*     */   implements CItem
/*     */ {
/*  42 */   protected static final String VISIBILITY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected String visibility = VISIBILITY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/*  70 */     return CmodelPackage.Literals.CITEM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVisibility() {
/*  79 */     return this.visibility;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibility(String newVisibility) {
/*  88 */     String oldVisibility = this.visibility;
/*  89 */     this.visibility = newVisibility;
/*  90 */     if (eNotificationRequired()) {
/*  91 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldVisibility, this.visibility));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 101 */     switch (featureID) {
/*     */       case 0:
/* 103 */         return getVisibility();
/*     */     } 
/* 105 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 115 */     switch (featureID) {
/*     */       case 0:
/* 117 */         setVisibility((String)newValue);
/*     */         return;
/*     */     } 
/* 120 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 130 */     switch (featureID) {
/*     */       case 0:
/* 132 */         setVisibility(VISIBILITY_EDEFAULT);
/*     */         return;
/*     */     } 
/* 135 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 145 */     switch (featureID) {
/*     */       case 0:
/* 147 */         return (VISIBILITY_EDEFAULT == null) ? ((this.visibility != null)) : (!VISIBILITY_EDEFAULT.equals(this.visibility));
/*     */     } 
/* 149 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 159 */     if (eIsProxy()) return super.toString();
/*     */     
/* 161 */     StringBuffer result = new StringBuffer(super.toString());
/* 162 */     result.append(" (visibility: ");
/* 163 */     result.append(this.visibility);
/* 164 */     result.append(')');
/* 165 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CItemImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */