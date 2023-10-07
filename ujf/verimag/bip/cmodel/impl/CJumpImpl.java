/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CJump;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*     */ import ujf.verimag.bip.cmodel.JumpType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CJumpImpl
/*     */   extends CStmImpl
/*     */   implements CJump
/*     */ {
/*  42 */   protected static final JumpType TYPE_EDEFAULT = JumpType.CBREAK;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected JumpType type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  70 */     return CmodelPackage.Literals.CJUMP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JumpType getType() {
/*  79 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(JumpType newType) {
/*  88 */     JumpType oldType = this.type;
/*  89 */     this.type = (newType == null) ? TYPE_EDEFAULT : newType;
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
/* 117 */         setType((JumpType)newValue);
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
/* 147 */         return (this.type != TYPE_EDEFAULT);
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


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CJumpImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */