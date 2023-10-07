/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CItem;
/*     */ import ujf.verimag.bip.cmodel.CText;
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
/*     */ public class CTextImpl
/*     */   extends CStmImpl
/*     */   implements CText
/*     */ {
/*  45 */   protected static final String VISIBILITY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected String visibility = VISIBILITY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected static final String CCODE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   protected String cCode = CCODE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   protected static final String PRAGMA_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   protected String pragma = PRAGMA_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean IN_BODY_FILE_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean inBodyFile = false;
/*     */ 
/*     */ 
/*     */ 
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
/* 133 */     return CmodelPackage.Literals.CTEXT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVisibility() {
/* 142 */     return this.visibility;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibility(String newVisibility) {
/* 151 */     String oldVisibility = this.visibility;
/* 152 */     this.visibility = newVisibility;
/* 153 */     if (eNotificationRequired()) {
/* 154 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldVisibility, this.visibility));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCCode() {
/* 163 */     return this.cCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCCode(String newCCode) {
/* 172 */     String oldCCode = this.cCode;
/* 173 */     this.cCode = newCCode;
/* 174 */     if (eNotificationRequired()) {
/* 175 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldCCode, this.cCode));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPragma() {
/* 184 */     return this.pragma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPragma(String newPragma) {
/* 193 */     String oldPragma = this.pragma;
/* 194 */     this.pragma = newPragma;
/* 195 */     if (eNotificationRequired()) {
/* 196 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldPragma, this.pragma));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInBodyFile() {
/* 205 */     return this.inBodyFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInBodyFile(boolean newInBodyFile) {
/* 214 */     boolean oldInBodyFile = this.inBodyFile;
/* 215 */     this.inBodyFile = newInBodyFile;
/* 216 */     if (eNotificationRequired()) {
/* 217 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldInBodyFile, this.inBodyFile));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 227 */     switch (featureID) {
/*     */       case 0:
/* 229 */         return getVisibility();
/*     */       case 1:
/* 231 */         return getCCode();
/*     */       case 2:
/* 233 */         return getPragma();
/*     */       case 3:
/* 235 */         return Boolean.valueOf(isInBodyFile());
/*     */     } 
/* 237 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 247 */     switch (featureID) {
/*     */       case 0:
/* 249 */         setVisibility((String)newValue);
/*     */         return;
/*     */       case 1:
/* 252 */         setCCode((String)newValue);
/*     */         return;
/*     */       case 2:
/* 255 */         setPragma((String)newValue);
/*     */         return;
/*     */       case 3:
/* 258 */         setInBodyFile(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 261 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 271 */     switch (featureID) {
/*     */       case 0:
/* 273 */         setVisibility(VISIBILITY_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 276 */         setCCode(CCODE_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 279 */         setPragma(PRAGMA_EDEFAULT);
/*     */         return;
/*     */       case 3:
/* 282 */         setInBodyFile(false);
/*     */         return;
/*     */     } 
/* 285 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 295 */     switch (featureID) {
/*     */       case 0:
/* 297 */         return (VISIBILITY_EDEFAULT == null) ? ((this.visibility != null)) : (!VISIBILITY_EDEFAULT.equals(this.visibility));
/*     */       case 1:
/* 299 */         return (CCODE_EDEFAULT == null) ? ((this.cCode != null)) : (!CCODE_EDEFAULT.equals(this.cCode));
/*     */       case 2:
/* 301 */         return (PRAGMA_EDEFAULT == null) ? ((this.pragma != null)) : (!PRAGMA_EDEFAULT.equals(this.pragma));
/*     */       case 3:
/* 303 */         return this.inBodyFile;
/*     */     } 
/* 305 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 315 */     if (baseClass == CItem.class) {
/* 316 */       switch (derivedFeatureID) { case 0:
/* 317 */           return 0; }
/* 318 */        return -1;
/*     */     } 
/*     */     
/* 321 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 331 */     if (baseClass == CItem.class) {
/* 332 */       switch (baseFeatureID) { case 0:
/* 333 */           return 0; }
/* 334 */        return -1;
/*     */     } 
/*     */     
/* 337 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 347 */     if (eIsProxy()) return super.toString();
/*     */     
/* 349 */     StringBuffer result = new StringBuffer(super.toString());
/* 350 */     result.append(" (visibility: ");
/* 351 */     result.append(this.visibility);
/* 352 */     result.append(", cCode: ");
/* 353 */     result.append(this.cCode);
/* 354 */     result.append(", pragma: ");
/* 355 */     result.append(this.pragma);
/* 356 */     result.append(", inBodyFile: ");
/* 357 */     result.append(this.inBodyFile);
/* 358 */     result.append(')');
/* 359 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CTextImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */