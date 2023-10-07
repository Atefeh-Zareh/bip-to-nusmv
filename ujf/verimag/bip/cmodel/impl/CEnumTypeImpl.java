/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CEnumType;
/*     */ import ujf.verimag.bip.cmodel.CItem;
/*     */ import ujf.verimag.bip.cmodel.CLiteral;
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
/*     */ public class CEnumTypeImpl
/*     */   extends CTypedElementImpl
/*     */   implements CEnumType
/*     */ {
/*  53 */   protected static final String VISIBILITY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected String visibility = VISIBILITY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<CLiteral> enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  91 */     return CmodelPackage.Literals.CENUM_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVisibility() {
/* 100 */     return this.visibility;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibility(String newVisibility) {
/* 109 */     String oldVisibility = this.visibility;
/* 110 */     this.visibility = newVisibility;
/* 111 */     if (eNotificationRequired()) {
/* 112 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldVisibility, this.visibility));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CLiteral> getEnumeration() {
/* 121 */     if (this.enumeration == null) {
/* 122 */       this.enumeration = (EList<CLiteral>)new EObjectContainmentEList(CLiteral.class, (InternalEObject)this, 2);
/*     */     }
/* 124 */     return this.enumeration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 134 */     switch (featureID) {
/*     */       case 2:
/* 136 */         return ((InternalEList)getEnumeration()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 138 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 148 */     switch (featureID) {
/*     */       case 1:
/* 150 */         return getVisibility();
/*     */       case 2:
/* 152 */         return getEnumeration();
/*     */     } 
/* 154 */     return super.eGet(featureID, resolve, coreType);
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
/* 165 */     switch (featureID) {
/*     */       case 1:
/* 167 */         setVisibility((String)newValue);
/*     */         return;
/*     */       case 2:
/* 170 */         getEnumeration().clear();
/* 171 */         getEnumeration().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 174 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 184 */     switch (featureID) {
/*     */       case 1:
/* 186 */         setVisibility(VISIBILITY_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 189 */         getEnumeration().clear();
/*     */         return;
/*     */     } 
/* 192 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 202 */     switch (featureID) {
/*     */       case 1:
/* 204 */         return (VISIBILITY_EDEFAULT == null) ? ((this.visibility != null)) : (!VISIBILITY_EDEFAULT.equals(this.visibility));
/*     */       case 2:
/* 206 */         return (this.enumeration != null && !this.enumeration.isEmpty());
/*     */     } 
/* 208 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 218 */     if (baseClass == CItem.class) {
/* 219 */       switch (derivedFeatureID) { case 1:
/* 220 */           return 0; }
/* 221 */        return -1;
/*     */     } 
/*     */     
/* 224 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 234 */     if (baseClass == CItem.class) {
/* 235 */       switch (baseFeatureID) { case 0:
/* 236 */           return 1; }
/* 237 */        return -1;
/*     */     } 
/*     */     
/* 240 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 250 */     if (eIsProxy()) return super.toString();
/*     */     
/* 252 */     StringBuffer result = new StringBuffer(super.toString());
/* 253 */     result.append(" (visibility: ");
/* 254 */     result.append(this.visibility);
/* 255 */     result.append(')');
/* 256 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CEnumTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */