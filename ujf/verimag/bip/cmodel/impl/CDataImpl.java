/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
/*     */ import ujf.verimag.bip.cmodel.CData;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CDataImpl
/*     */   extends CTypedElementImpl
/*     */   implements CData
/*     */ {
/*  48 */   protected static final String VISIBILITY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected String visibility = VISIBILITY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected String name = NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CExpression initialValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 106 */     return CmodelPackage.Literals.CDATA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVisibility() {
/* 115 */     return this.visibility;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibility(String newVisibility) {
/* 124 */     String oldVisibility = this.visibility;
/* 125 */     this.visibility = newVisibility;
/* 126 */     if (eNotificationRequired()) {
/* 127 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldVisibility, this.visibility));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 136 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/* 145 */     String oldName = this.name;
/* 146 */     this.name = newName;
/* 147 */     if (eNotificationRequired()) {
/* 148 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldName, this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getInitialValue() {
/* 157 */     return this.initialValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInitialValue(CExpression newInitialValue, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 166 */     CExpression oldInitialValue = this.initialValue;
/* 167 */     this.initialValue = newInitialValue;
/* 168 */     if (eNotificationRequired()) {
/* 169 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 3, oldInitialValue, newInitialValue);
/* 170 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 172 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitialValue(CExpression newInitialValue) {
/* 181 */     if (newInitialValue != this.initialValue) {
/* 182 */       NotificationChain msgs = null;
/* 183 */       if (this.initialValue != null)
/* 184 */         msgs = ((InternalEObject)this.initialValue).eInverseRemove((InternalEObject)this, -4, null, msgs); 
/* 185 */       if (newInitialValue != null)
/* 186 */         msgs = ((InternalEObject)newInitialValue).eInverseAdd((InternalEObject)this, -4, null, msgs); 
/* 187 */       msgs = basicSetInitialValue(newInitialValue, msgs);
/* 188 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 190 */     } else if (eNotificationRequired()) {
/* 191 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newInitialValue, newInitialValue));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 201 */     switch (featureID) {
/*     */       case 3:
/* 203 */         return basicSetInitialValue((CExpression)null, msgs);
/*     */     } 
/* 205 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 215 */     switch (featureID) {
/*     */       case 1:
/* 217 */         return getVisibility();
/*     */       case 2:
/* 219 */         return getName();
/*     */       case 3:
/* 221 */         return getInitialValue();
/*     */     } 
/* 223 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 233 */     switch (featureID) {
/*     */       case 1:
/* 235 */         setVisibility((String)newValue);
/*     */         return;
/*     */       case 2:
/* 238 */         setName((String)newValue);
/*     */         return;
/*     */       case 3:
/* 241 */         setInitialValue((CExpression)newValue);
/*     */         return;
/*     */     } 
/* 244 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 254 */     switch (featureID) {
/*     */       case 1:
/* 256 */         setVisibility(VISIBILITY_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 259 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 3:
/* 262 */         setInitialValue((CExpression)null);
/*     */         return;
/*     */     } 
/* 265 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 275 */     switch (featureID) {
/*     */       case 1:
/* 277 */         return (VISIBILITY_EDEFAULT == null) ? ((this.visibility != null)) : (!VISIBILITY_EDEFAULT.equals(this.visibility));
/*     */       case 2:
/* 279 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 3:
/* 281 */         return (this.initialValue != null);
/*     */     } 
/* 283 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 293 */     if (baseClass == CItem.class) {
/* 294 */       switch (derivedFeatureID) { case 1:
/* 295 */           return 0; }
/* 296 */        return -1;
/*     */     } 
/*     */     
/* 299 */     if (baseClass == CBodyItem.class) {
/* 300 */       switch (derivedFeatureID) {  }
/* 301 */        return -1;
/*     */     } 
/*     */     
/* 304 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 314 */     if (baseClass == CItem.class) {
/* 315 */       switch (baseFeatureID) { case 0:
/* 316 */           return 1; }
/* 317 */        return -1;
/*     */     } 
/*     */     
/* 320 */     if (baseClass == CBodyItem.class) {
/* 321 */       switch (baseFeatureID) {  }
/* 322 */        return -1;
/*     */     } 
/*     */     
/* 325 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 335 */     if (eIsProxy()) return super.toString();
/*     */     
/* 337 */     StringBuffer result = new StringBuffer(super.toString());
/* 338 */     result.append(" (visibility: ");
/* 339 */     result.append(this.visibility);
/* 340 */     result.append(", name: ");
/* 341 */     result.append(this.name);
/* 342 */     result.append(')');
/* 343 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CDataImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */