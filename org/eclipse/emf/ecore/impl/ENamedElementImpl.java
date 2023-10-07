/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.util.EContentsEList;
/*     */ import org.eclipse.emf.ecore.util.ECrossReferenceEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ENamedElementImpl
/*     */   extends EModelElementImpl
/*     */   implements ENamedElement
/*     */ {
/*  58 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected String name = NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  88 */     return EcorePackage.Literals.ENAMED_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  96 */     return getNameGen();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     if (eIsProxy()) return super.toString();
/*     */     
/* 118 */     StringBuffer result = new StringBuffer(super.toString());
/* 119 */     result.append(" (name: ");
/* 120 */     result.append(this.name);
/* 121 */     result.append(')');
/* 122 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameGen() {
/* 132 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/* 142 */     String oldName = this.name;
/* 143 */     this.name = newName;
/* 144 */     if (eNotificationRequired()) {
/* 145 */       eNotify((Notification)new ENotificationImpl(this, 1, 1, oldName, this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 156 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 159 */         return getEAnnotations();
/*     */       case 1:
/* 161 */         return getName();
/*     */     } 
/* 163 */     return eDynamicGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 175 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 178 */         getEAnnotations().clear();
/* 179 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 182 */         setName((String)newValue);
/*     */         return;
/*     */     } 
/* 185 */     eDynamicSet(featureID, newValue);
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
/* 196 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 199 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 202 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 205 */     eDynamicUnset(featureID);
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
/* 216 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 219 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 221 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */     } 
/* 223 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EObject> eContents() {
/* 229 */     EStructuralFeature[] eStructuralFeatures = (
/* 230 */       (EClassImpl.FeatureSubsetSupplier)eClass().getEAllStructuralFeatures()).containments();
/*     */     
/* 232 */     return 
/* 233 */       (eStructuralFeatures == null) ? 
/* 234 */       (EList<EObject>)EContentsEList.emptyContentsEList() : 
/* 235 */       (EList<EObject>)new EContentsEList<EObject>(this, eStructuralFeatures)
/*     */       {
/*     */         
/*     */         protected boolean useIsSet()
/*     */         {
/* 240 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         protected ListIterator<EObject> newResolvingListIterator() {
/* 246 */           return 
/* 247 */             (ListIterator<EObject>)new EContentsEList.ResolvingFeatureIteratorImpl<EObject>(this.eObject, this.eStructuralFeatures)
/*     */             {
/*     */               
/*     */               protected boolean useIsSet()
/*     */               {
/* 252 */                 return false;
/*     */               }
/*     */             };
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         protected ListIterator<EObject> newNonResolvingListIterator() {
/* 260 */           return 
/* 261 */             (ListIterator<EObject>)new EContentsEList.FeatureIteratorImpl<EObject>(this.eObject, this.eStructuralFeatures)
/*     */             {
/*     */               
/*     */               protected boolean useIsSet()
/*     */               {
/* 266 */                 return false;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EObject> eCrossReferences() {
/* 277 */     EStructuralFeature[] eStructuralFeatures = (
/* 278 */       (EClassImpl.FeatureSubsetSupplier)eClass().getEAllStructuralFeatures()).crossReferences();
/*     */     
/* 280 */     return 
/* 281 */       (eStructuralFeatures == null) ? 
/* 282 */       (EList<EObject>)ECrossReferenceEList.emptyCrossReferenceEList() : 
/* 283 */       (EList<EObject>)new ECrossReferenceEList<EObject>(this, eStructuralFeatures)
/*     */       {
/*     */         
/*     */         protected boolean useIsSet()
/*     */         {
/* 288 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         protected ListIterator<EObject> newResolvingListIterator() {
/* 294 */           return 
/* 295 */             (ListIterator<EObject>)new ECrossReferenceEList.ResolvingFeatureIteratorImpl<EObject>(this.eObject, this.eStructuralFeatures)
/*     */             {
/*     */               
/*     */               protected boolean useIsSet()
/*     */               {
/* 300 */                 return false;
/*     */               }
/*     */             };
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         protected ListIterator<EObject> newNonResolvingListIterator() {
/* 308 */           return 
/* 309 */             (ListIterator<EObject>)new ECrossReferenceEList.FeatureIteratorImpl<EObject>(this.eObject, this.eStructuralFeatures)
/*     */             {
/*     */               
/*     */               protected boolean useIsSet()
/*     */               {
/* 314 */                 return false;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\ENamedElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */