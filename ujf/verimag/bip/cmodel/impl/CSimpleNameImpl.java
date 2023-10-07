/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CSimpleName;
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
/*     */ public class CSimpleNameImpl
/*     */   extends CExpressionImpl
/*     */   implements CSimpleName
/*     */ {
/*  41 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected String name = NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  69 */     return CmodelPackage.Literals.CSIMPLE_NAME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  78 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/*  87 */     String oldName = this.name;
/*  88 */     this.name = newName;
/*  89 */     if (eNotificationRequired()) {
/*  90 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldName, this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 100 */     switch (featureID) {
/*     */       case 0:
/* 102 */         return getName();
/*     */     } 
/* 104 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 114 */     switch (featureID) {
/*     */       case 0:
/* 116 */         setName((String)newValue);
/*     */         return;
/*     */     } 
/* 119 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 129 */     switch (featureID) {
/*     */       case 0:
/* 131 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 134 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 144 */     switch (featureID) {
/*     */       case 0:
/* 146 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */     } 
/* 148 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 158 */     if (eIsProxy()) return super.toString();
/*     */     
/* 160 */     StringBuffer result = new StringBuffer(super.toString());
/* 161 */     result.append(" (name: ");
/* 162 */     result.append(this.name);
/* 163 */     result.append(')');
/* 164 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CSimpleNameImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */