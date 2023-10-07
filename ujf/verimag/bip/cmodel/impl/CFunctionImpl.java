/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CFunction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CFunctionImpl
/*     */   extends CCallableImpl
/*     */   implements CFunction
/*     */ {
/*  47 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected String name = NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  75 */     return CmodelPackage.Literals.CFUNCTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  84 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/*  93 */     String oldName = this.name;
/*  94 */     this.name = newName;
/*  95 */     if (eNotificationRequired()) {
/*  96 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 7, oldName, this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 106 */     switch (featureID) {
/*     */       case 7:
/* 108 */         return getName();
/*     */     } 
/* 110 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 120 */     switch (featureID) {
/*     */       case 7:
/* 122 */         setName((String)newValue);
/*     */         return;
/*     */     } 
/* 125 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 135 */     switch (featureID) {
/*     */       case 7:
/* 137 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 140 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 150 */     switch (featureID) {
/*     */       case 7:
/* 152 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */     } 
/* 154 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 164 */     if (eIsProxy()) return super.toString();
/*     */     
/* 166 */     StringBuffer result = new StringBuffer(super.toString());
/* 167 */     result.append(" (name: ");
/* 168 */     result.append(this.name);
/* 169 */     result.append(')');
/* 170 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CFunctionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */