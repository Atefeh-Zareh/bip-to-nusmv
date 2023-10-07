/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.cmodel.CTypedElement;
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
/*     */ public class CTypedElementImpl
/*     */   extends EObjectImpl
/*     */   implements CTypedElement
/*     */ {
/*  42 */   protected static final String TYPE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected String type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  70 */     return CmodelPackage.Literals.CTYPED_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  79 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String newType) {
/*  88 */     String oldType = this.type;
/*  89 */     this.type = newType;
/*  90 */     if (eNotificationRequired()) {
/*  91 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldType, this.type));
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
/* 103 */         return getType();
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
/* 117 */         setType((String)newValue);
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
/* 132 */         setType(TYPE_EDEFAULT);
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
/* 147 */         return (TYPE_EDEFAULT == null) ? ((this.type != null)) : (!TYPE_EDEFAULT.equals(this.type));
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
/* 162 */     result.append(" (type: ");
/* 163 */     result.append(this.type);
/* 164 */     result.append(')');
/* 165 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CTypedElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */