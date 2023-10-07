/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
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
/*     */ public class ComponentImpl
/*     */   extends PartImpl
/*     */   implements Component
/*     */ {
/*     */   protected ComponentType type;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  69 */     return InteractionsPackage.Literals.COMPONENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType getCompoundType() {
/*  79 */     if (eContainerFeatureID() != 4) return null; 
/*  80 */     return (CompoundType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCompoundType(CompoundType newCompoundType, NotificationChain msgs) {
/*  90 */     msgs = eBasicSetContainer((InternalEObject)newCompoundType, 4, msgs);
/*  91 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompoundType(CompoundType newCompoundType) {
/* 101 */     if (newCompoundType != eInternalContainer() || (eContainerFeatureID() != 4 && newCompoundType != null)) {
/*     */       
/* 103 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newCompoundType))
/* 104 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 105 */       NotificationChain msgs = null;
/* 106 */       if (eInternalContainer() != null)
/* 107 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 108 */       if (newCompoundType != null)
/* 109 */         msgs = ((InternalEObject)newCompoundType).eInverseAdd((InternalEObject)this, 13, CompoundType.class, msgs); 
/* 110 */       msgs = basicSetCompoundType(newCompoundType, msgs);
/* 111 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 113 */     } else if (eNotificationRequired()) {
/* 114 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newCompoundType, newCompoundType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType getType() {
/* 124 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/* 126 */       InternalEObject oldType = (InternalEObject)this.type;
/* 127 */       this.type = (ComponentType)eResolveProxy(oldType);
/* 128 */       if (this.type != oldType)
/*     */       {
/* 130 */         if (eNotificationRequired())
/* 131 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 5, oldType, this.type)); 
/*     */       }
/*     */     } 
/* 134 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType basicGetType() {
/* 144 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ComponentType newType) {
/* 154 */     ComponentType oldType = this.type;
/* 155 */     this.type = newType;
/* 156 */     if (eNotificationRequired()) {
/* 157 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, oldType, this.type));
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
/* 168 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 171 */         if (eInternalContainer() != null)
/* 172 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 173 */         return basicSetCompoundType((CompoundType)otherEnd, msgs);
/*     */     } 
/* 175 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 186 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 189 */         return basicSetCompoundType((CompoundType)null, msgs);
/*     */     } 
/* 191 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 202 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 4:
/* 205 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 13, CompoundType.class, msgs);
/*     */     } 
/* 207 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 218 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 221 */         return getCompoundType();
/*     */       case 5:
/* 223 */         if (resolve) return getType(); 
/* 224 */         return basicGetType();
/*     */     } 
/* 226 */     return super.eGet(featureID, resolve, coreType);
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
/* 237 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 240 */         setCompoundType((CompoundType)newValue);
/*     */         return;
/*     */       case 5:
/* 243 */         setType((ComponentType)newValue);
/*     */         return;
/*     */     } 
/* 246 */     super.eSet(featureID, newValue);
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
/* 257 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 260 */         setCompoundType((CompoundType)null);
/*     */         return;
/*     */       case 5:
/* 263 */         setType((ComponentType)null);
/*     */         return;
/*     */     } 
/* 266 */     super.eUnset(featureID);
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
/* 277 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 280 */         return (getCompoundType() != null);
/*     */       case 5:
/* 282 */         return (this.type != null);
/*     */     } 
/* 284 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\ComponentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */