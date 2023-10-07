/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.cmodel.CInclude;
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
/*     */ public class CIncludeImpl
/*     */   extends EObjectImpl
/*     */   implements CInclude
/*     */ {
/*  42 */   protected static final String FILE_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected String fileName = FILE_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  70 */     return CmodelPackage.Literals.CINCLUDE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileName() {
/*  79 */     return this.fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileName(String newFileName) {
/*  88 */     String oldFileName = this.fileName;
/*  89 */     this.fileName = newFileName;
/*  90 */     if (eNotificationRequired()) {
/*  91 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldFileName, this.fileName));
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
/* 103 */         return getFileName();
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
/* 117 */         setFileName((String)newValue);
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
/* 132 */         setFileName(FILE_NAME_EDEFAULT);
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
/* 147 */         return (FILE_NAME_EDEFAULT == null) ? ((this.fileName != null)) : (!FILE_NAME_EDEFAULT.equals(this.fileName));
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
/* 162 */     result.append(" (fileName: ");
/* 163 */     result.append(this.fileName);
/* 164 */     result.append(')');
/* 165 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CIncludeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */