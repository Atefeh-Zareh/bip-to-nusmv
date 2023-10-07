/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.Constant;
/*     */ import ujf.verimag.bip.Core.Behaviors.PartType;
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PartTypeImpl
/*     */   extends BipTypeImpl
/*     */   implements PartType
/*     */ {
/*     */   protected EList<Constant> constant;
/*     */   protected EList<Declaration> declaration;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  82 */     return BehaviorsPackage.Literals.PART_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Constant> getConstant() {
/*  92 */     if (this.constant == null)
/*     */     {
/*  94 */       this.constant = (EList<Constant>)new EObjectContainmentWithInverseEList(Constant.class, (InternalEObject)this, 5, 7);
/*     */     }
/*  96 */     return this.constant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Declaration> getDeclaration() {
/* 106 */     if (this.declaration == null)
/*     */     {
/* 108 */       this.declaration = (EList<Declaration>)new EObjectContainmentEList(Declaration.class, (InternalEObject)this, 6);
/*     */     }
/* 110 */     return this.declaration;
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
/* 122 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 125 */         return ((InternalEList)getConstant()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 127 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 138 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 141 */         return ((InternalEList)getConstant()).basicRemove(otherEnd, msgs);
/*     */       case 6:
/* 143 */         return ((InternalEList)getDeclaration()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 145 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 156 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 159 */         return getConstant();
/*     */       case 6:
/* 161 */         return getDeclaration();
/*     */     } 
/* 163 */     return super.eGet(featureID, resolve, coreType);
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
/*     */       case 5:
/* 178 */         getConstant().clear();
/* 179 */         getConstant().addAll((Collection)newValue);
/*     */         return;
/*     */       case 6:
/* 182 */         getDeclaration().clear();
/* 183 */         getDeclaration().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 186 */     super.eSet(featureID, newValue);
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
/* 197 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 200 */         getConstant().clear();
/*     */         return;
/*     */       case 6:
/* 203 */         getDeclaration().clear();
/*     */         return;
/*     */     } 
/* 206 */     super.eUnset(featureID);
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
/* 217 */     switch (featureID) {
/*     */       
/*     */       case 5:
/* 220 */         return (this.constant != null && !this.constant.isEmpty());
/*     */       case 6:
/* 222 */         return (this.declaration != null && !this.declaration.isEmpty());
/*     */     } 
/* 224 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\PartTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */