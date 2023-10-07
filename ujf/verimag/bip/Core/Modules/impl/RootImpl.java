/*     */ package ujf.verimag.bip.Core.Modules.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.NamedElementImpl;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RootImpl
/*     */   extends NamedElementImpl
/*     */   implements Root
/*     */ {
/*     */   protected ComponentType type;
/*     */   protected EList<Expression> actualData;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  88 */     return ModulesPackage.Literals.ROOT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType getType() {
/*  98 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/* 100 */       InternalEObject oldType = (InternalEObject)this.type;
/* 101 */       this.type = (ComponentType)eResolveProxy(oldType);
/* 102 */       if (this.type != oldType)
/*     */       {
/* 104 */         if (eNotificationRequired())
/* 105 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 2, oldType, this.type)); 
/*     */       }
/*     */     } 
/* 108 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType basicGetType() {
/* 118 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ComponentType newType) {
/* 128 */     ComponentType oldType = this.type;
/* 129 */     this.type = newType;
/* 130 */     if (eNotificationRequired()) {
/* 131 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Expression> getActualData() {
/* 141 */     if (this.actualData == null)
/*     */     {
/* 143 */       this.actualData = (EList<Expression>)new EObjectContainmentEList(Expression.class, (InternalEObject)this, 3);
/*     */     }
/* 145 */     return this.actualData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public System getSystem() {
/* 155 */     if (eContainerFeatureID() != 4) return null; 
/* 156 */     return (System)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetSystem(System newSystem, NotificationChain msgs) {
/* 166 */     msgs = eBasicSetContainer((InternalEObject)newSystem, 4, msgs);
/* 167 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystem(System newSystem) {
/* 177 */     if (newSystem != eInternalContainer() || (eContainerFeatureID() != 4 && newSystem != null)) {
/*     */       
/* 179 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newSystem))
/* 180 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 181 */       NotificationChain msgs = null;
/* 182 */       if (eInternalContainer() != null)
/* 183 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 184 */       if (newSystem != null)
/* 185 */         msgs = ((InternalEObject)newSystem).eInverseAdd((InternalEObject)this, 7, System.class, msgs); 
/* 186 */       msgs = basicSetSystem(newSystem, msgs);
/* 187 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 189 */     } else if (eNotificationRequired()) {
/* 190 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newSystem, newSystem));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 201 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 204 */         if (eInternalContainer() != null)
/* 205 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 206 */         return basicSetSystem((System)otherEnd, msgs);
/*     */     } 
/* 208 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 219 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 222 */         return ((InternalEList)getActualData()).basicRemove(otherEnd, msgs);
/*     */       case 4:
/* 224 */         return basicSetSystem((System)null, msgs);
/*     */     } 
/* 226 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 237 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 4:
/* 240 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 7, System.class, msgs);
/*     */     } 
/* 242 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 253 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 256 */         if (resolve) return getType(); 
/* 257 */         return basicGetType();
/*     */       case 3:
/* 259 */         return getActualData();
/*     */       case 4:
/* 261 */         return getSystem();
/*     */     } 
/* 263 */     return super.eGet(featureID, resolve, coreType);
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
/* 275 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 278 */         setType((ComponentType)newValue);
/*     */         return;
/*     */       case 3:
/* 281 */         getActualData().clear();
/* 282 */         getActualData().addAll((Collection)newValue);
/*     */         return;
/*     */       case 4:
/* 285 */         setSystem((System)newValue);
/*     */         return;
/*     */     } 
/* 288 */     super.eSet(featureID, newValue);
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
/* 299 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 302 */         setType((ComponentType)null);
/*     */         return;
/*     */       case 3:
/* 305 */         getActualData().clear();
/*     */         return;
/*     */       case 4:
/* 308 */         setSystem((System)null);
/*     */         return;
/*     */     } 
/* 311 */     super.eUnset(featureID);
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
/* 322 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 325 */         return (this.type != null);
/*     */       case 3:
/* 327 */         return (this.actualData != null && !this.actualData.isEmpty());
/*     */       case 4:
/* 329 */         return (getSystem() != null);
/*     */     } 
/* 331 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\impl\RootImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */