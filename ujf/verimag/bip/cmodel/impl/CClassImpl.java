/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CClass;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CClassImpl
/*     */   extends CTypedElementImpl
/*     */   implements CClass
/*     */ {
/*  55 */   protected static final String VISIBILITY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected String visibility = VISIBILITY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<CItem> content;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   protected String name = NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<String> superClasses;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 123 */     return CmodelPackage.Literals.CCLASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVisibility() {
/* 132 */     return this.visibility;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibility(String newVisibility) {
/* 141 */     String oldVisibility = this.visibility;
/* 142 */     this.visibility = newVisibility;
/* 143 */     if (eNotificationRequired()) {
/* 144 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldVisibility, this.visibility));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CItem> getContent() {
/* 153 */     if (this.content == null) {
/* 154 */       this.content = (EList<CItem>)new EObjectContainmentEList(CItem.class, (InternalEObject)this, 2);
/*     */     }
/* 156 */     return this.content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 165 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/* 174 */     String oldName = this.name;
/* 175 */     this.name = newName;
/* 176 */     if (eNotificationRequired()) {
/* 177 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldName, this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<String> getSuperClasses() {
/* 186 */     if (this.superClasses == null) {
/* 187 */       this.superClasses = (EList<String>)new EDataTypeUniqueEList(String.class, (InternalEObject)this, 4);
/*     */     }
/* 189 */     return this.superClasses;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 199 */     switch (featureID) {
/*     */       case 2:
/* 201 */         return ((InternalEList)getContent()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 203 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 213 */     switch (featureID) {
/*     */       case 1:
/* 215 */         return getVisibility();
/*     */       case 2:
/* 217 */         return getContent();
/*     */       case 3:
/* 219 */         return getName();
/*     */       case 4:
/* 221 */         return getSuperClasses();
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
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 234 */     switch (featureID) {
/*     */       case 1:
/* 236 */         setVisibility((String)newValue);
/*     */         return;
/*     */       case 2:
/* 239 */         getContent().clear();
/* 240 */         getContent().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 243 */         setName((String)newValue);
/*     */         return;
/*     */       case 4:
/* 246 */         getSuperClasses().clear();
/* 247 */         getSuperClasses().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 250 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 260 */     switch (featureID) {
/*     */       case 1:
/* 262 */         setVisibility(VISIBILITY_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 265 */         getContent().clear();
/*     */         return;
/*     */       case 3:
/* 268 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 4:
/* 271 */         getSuperClasses().clear();
/*     */         return;
/*     */     } 
/* 274 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 284 */     switch (featureID) {
/*     */       case 1:
/* 286 */         return (VISIBILITY_EDEFAULT == null) ? ((this.visibility != null)) : (!VISIBILITY_EDEFAULT.equals(this.visibility));
/*     */       case 2:
/* 288 */         return (this.content != null && !this.content.isEmpty());
/*     */       case 3:
/* 290 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 4:
/* 292 */         return (this.superClasses != null && !this.superClasses.isEmpty());
/*     */     } 
/* 294 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 304 */     if (baseClass == CItem.class) {
/* 305 */       switch (derivedFeatureID) { case 1:
/* 306 */           return 0; }
/* 307 */        return -1;
/*     */     } 
/*     */     
/* 310 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 320 */     if (baseClass == CItem.class) {
/* 321 */       switch (baseFeatureID) { case 0:
/* 322 */           return 1; }
/* 323 */        return -1;
/*     */     } 
/*     */     
/* 326 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 336 */     if (eIsProxy()) return super.toString();
/*     */     
/* 338 */     StringBuffer result = new StringBuffer(super.toString());
/* 339 */     result.append(" (visibility: ");
/* 340 */     result.append(this.visibility);
/* 341 */     result.append(", name: ");
/* 342 */     result.append(this.name);
/* 343 */     result.append(", superClasses: ");
/* 344 */     result.append(this.superClasses);
/* 345 */     result.append(')');
/* 346 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CClassImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */