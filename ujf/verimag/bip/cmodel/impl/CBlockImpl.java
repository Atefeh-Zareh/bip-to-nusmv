/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CBlock;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CBlockImpl
/*     */   extends EObjectImpl
/*     */   implements CBlock
/*     */ {
/*     */   protected EList<CBodyItem> content;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  68 */     return CmodelPackage.Literals.CBLOCK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CBodyItem> getContent() {
/*  77 */     if (this.content == null) {
/*  78 */       this.content = (EList<CBodyItem>)new EObjectContainmentEList(CBodyItem.class, (InternalEObject)this, 0);
/*     */     }
/*  80 */     return this.content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  90 */     switch (featureID) {
/*     */       case 0:
/*  92 */         return ((InternalEList)getContent()).basicRemove(otherEnd, msgs);
/*     */     } 
/*  94 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 104 */     switch (featureID) {
/*     */       case 0:
/* 106 */         return getContent();
/*     */     } 
/* 108 */     return super.eGet(featureID, resolve, coreType);
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
/* 119 */     switch (featureID) {
/*     */       case 0:
/* 121 */         getContent().clear();
/* 122 */         getContent().addAll((Collection)newValue);
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
/*     */       case 0:
/* 137 */         getContent().clear();
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
/*     */       case 0:
/* 152 */         return (this.content != null && !this.content.isEmpty());
/*     */     } 
/* 154 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CBlockImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */