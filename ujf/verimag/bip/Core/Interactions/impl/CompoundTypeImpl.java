/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ComponentTypeImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompoundTypeImpl
/*     */   extends ComponentTypeImpl
/*     */   implements CompoundType
/*     */ {
/*     */   protected EList<Connector> connector;
/*     */   protected EList<Component> subcomponent;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  82 */     return InteractionsPackage.Literals.COMPOUND_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Connector> getConnector() {
/*  92 */     if (this.connector == null)
/*     */     {
/*  94 */       this.connector = (EList<Connector>)new EObjectContainmentWithInverseEList(Connector.class, (InternalEObject)this, 12, 6);
/*     */     }
/*  96 */     return this.connector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Component> getSubcomponent() {
/* 106 */     if (this.subcomponent == null)
/*     */     {
/* 108 */       this.subcomponent = (EList<Component>)new EObjectContainmentWithInverseEList(Component.class, (InternalEObject)this, 13, 4);
/*     */     }
/* 110 */     return this.subcomponent;
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
/*     */       case 12:
/* 125 */         return ((InternalEList)getConnector()).basicAdd(otherEnd, msgs);
/*     */       case 13:
/* 127 */         return ((InternalEList)getSubcomponent()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 129 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 140 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 143 */         return ((InternalEList)getConnector()).basicRemove(otherEnd, msgs);
/*     */       case 13:
/* 145 */         return ((InternalEList)getSubcomponent()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 147 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 158 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 161 */         return getConnector();
/*     */       case 13:
/* 163 */         return getSubcomponent();
/*     */     } 
/* 165 */     return super.eGet(featureID, resolve, coreType);
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
/* 177 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 180 */         getConnector().clear();
/* 181 */         getConnector().addAll((Collection)newValue);
/*     */         return;
/*     */       case 13:
/* 184 */         getSubcomponent().clear();
/* 185 */         getSubcomponent().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 188 */     super.eSet(featureID, newValue);
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
/*     */       case 12:
/* 202 */         getConnector().clear();
/*     */         return;
/*     */       case 13:
/* 205 */         getSubcomponent().clear();
/*     */         return;
/*     */     } 
/* 208 */     super.eUnset(featureID);
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
/* 219 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 222 */         return (this.connector != null && !this.connector.isEmpty());
/*     */       case 13:
/* 224 */         return (this.subcomponent != null && !this.subcomponent.isEmpty());
/*     */     } 
/* 226 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\CompoundTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */