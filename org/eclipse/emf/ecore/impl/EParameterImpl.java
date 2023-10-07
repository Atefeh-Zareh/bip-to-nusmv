/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.EParameter;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EParameterImpl
/*     */   extends ETypedElementImpl
/*     */   implements EParameter
/*     */ {
/*     */   protected EClass eStaticClass() {
/*  62 */     return EcorePackage.Literals.EPARAMETER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EOperation getEOperation() {
/*  72 */     return (eContainerFeatureID() == 10) ? (EOperation)this.eContainer : null;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  84 */     switch (featureID) {
/*     */       
/*     */       case 0:
/*  87 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */       case 10:
/*  89 */         if (eInternalContainer() != null)
/*  90 */           msgs = eBasicRemoveFromContainer(msgs); 
/*  91 */         return eBasicSetContainer(otherEnd, 10, msgs);
/*     */     } 
/*  93 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
/* 104 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 107 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 9:
/* 109 */         return basicUnsetEGenericType(msgs);
/*     */       case 10:
/* 111 */         return eBasicSetContainer(null, 10, msgs);
/*     */     } 
/* 113 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 124 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 10:
/* 127 */         return eInternalContainer().eInverseRemove(this, 12, EOperation.class, msgs);
/*     */     } 
/* 129 */     return eDynamicBasicRemoveFromContainer(msgs);
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
/* 140 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 143 */         return getEAnnotations();
/*     */       case 1:
/* 145 */         return getName();
/*     */       case 2:
/* 147 */         return Boolean.valueOf(isOrdered());
/*     */       case 3:
/* 149 */         return Boolean.valueOf(isUnique());
/*     */       case 4:
/* 151 */         return Integer.valueOf(getLowerBound());
/*     */       case 5:
/* 153 */         return Integer.valueOf(getUpperBound());
/*     */       case 6:
/* 155 */         return Boolean.valueOf(isMany());
/*     */       case 7:
/* 157 */         return Boolean.valueOf(isRequired());
/*     */       case 8:
/* 159 */         if (resolve) return getEType(); 
/* 160 */         return basicGetEType();
/*     */       case 9:
/* 162 */         return getEGenericType();
/*     */       case 10:
/* 164 */         return getEOperation();
/*     */     } 
/* 166 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 177 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 180 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 182 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 184 */         return !((this.eFlags & 0x100) != 0);
/*     */       case 3:
/* 186 */         return !((this.eFlags & 0x200) != 0);
/*     */       case 4:
/* 188 */         return (this.lowerBound != 0);
/*     */       case 5:
/* 190 */         return (this.upperBound != 1);
/*     */       case 6:
/* 192 */         return isMany();
/*     */       case 7:
/* 194 */         return isRequired();
/*     */       case 8:
/* 196 */         return isSetEType();
/*     */       case 9:
/* 198 */         return isSetEGenericType();
/*     */       case 10:
/* 200 */         return (getEOperation() != null);
/*     */     } 
/* 202 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EParameterImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */