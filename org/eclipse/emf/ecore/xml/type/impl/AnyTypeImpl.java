/*     */ package org.eclipse.emf.ecore.xml.type.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.BasicFeatureMap;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnyTypeImpl
/*     */   extends EObjectImpl
/*     */   implements AnyType
/*     */ {
/*     */   protected FeatureMap mixed;
/*     */   protected FeatureMap anyAttribute;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  84 */     return XMLTypePackage.Literals.ANY_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FeatureMap getMixed() {
/*  94 */     if (this.mixed == null)
/*     */     {
/*  96 */       this.mixed = (FeatureMap)new BasicFeatureMap((InternalEObject)this, 0);
/*     */     }
/*  98 */     return this.mixed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FeatureMap getAny() {
/* 108 */     return (FeatureMap)getMixed().list((EStructuralFeature)XMLTypePackage.Literals.ANY_TYPE__ANY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FeatureMap getAnyAttribute() {
/* 118 */     if (this.anyAttribute == null)
/*     */     {
/* 120 */       this.anyAttribute = (FeatureMap)new BasicFeatureMap((InternalEObject)this, 2);
/*     */     }
/* 122 */     return this.anyAttribute;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 133 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 136 */         return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
/*     */       case 1:
/* 138 */         return ((InternalEList)getAny()).basicRemove(otherEnd, msgs);
/*     */       case 2:
/* 140 */         return ((InternalEList)getAnyAttribute()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 142 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 153 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 156 */         if (coreType) return getMixed(); 
/* 157 */         return ((FeatureMap.Internal)getMixed()).getWrapper();
/*     */       case 1:
/* 159 */         if (coreType) return getAny(); 
/* 160 */         return ((FeatureMap.Internal)getAny()).getWrapper();
/*     */       case 2:
/* 162 */         if (coreType) return getAnyAttribute(); 
/* 163 */         return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
/*     */     } 
/* 165 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 176 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 179 */         ((FeatureMap.Internal)getMixed()).set(newValue);
/*     */         return;
/*     */       case 1:
/* 182 */         ((FeatureMap.Internal)getAny()).set(newValue);
/*     */         return;
/*     */       case 2:
/* 185 */         ((FeatureMap.Internal)getAnyAttribute()).set(newValue);
/*     */         return;
/*     */     } 
/* 188 */     eDynamicSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 199 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 202 */         getMixed().clear();
/*     */         return;
/*     */       case 1:
/* 205 */         getAny().clear();
/*     */         return;
/*     */       case 2:
/* 208 */         getAnyAttribute().clear();
/*     */         return;
/*     */     } 
/* 211 */     eDynamicUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 222 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 225 */         return (this.mixed != null && !this.mixed.isEmpty());
/*     */       case 1:
/* 227 */         return !getAny().isEmpty();
/*     */       case 2:
/* 229 */         return (this.anyAttribute != null && !this.anyAttribute.isEmpty());
/*     */     } 
/* 231 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 242 */     if (eIsProxy()) return super.toString();
/*     */     
/* 244 */     StringBuffer result = new StringBuffer(super.toString());
/* 245 */     result.append(" (mixed: ");
/* 246 */     result.append(this.mixed);
/* 247 */     result.append(", anyAttribute: ");
/* 248 */     result.append(this.anyAttribute);
/* 249 */     result.append(')');
/* 250 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\impl\AnyTypeImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */