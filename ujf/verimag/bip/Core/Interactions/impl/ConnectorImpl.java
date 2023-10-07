/*     */ package ujf.verimag.bip.Core.Interactions.impl;
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
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
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
/*     */ public class ConnectorImpl
/*     */   extends PartImpl
/*     */   implements Connector
/*     */ {
/*     */   protected EList<ActualPortParameter> actualPort;
/*     */   protected ConnectorType type;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  86 */     return InteractionsPackage.Literals.CONNECTOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<ActualPortParameter> getActualPort() {
/*  96 */     if (this.actualPort == null)
/*     */     {
/*  98 */       this.actualPort = (EList<ActualPortParameter>)new EObjectContainmentEList(ActualPortParameter.class, (InternalEObject)this, 4);
/*     */     }
/* 100 */     return this.actualPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getType() {
/* 110 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/* 112 */       InternalEObject oldType = (InternalEObject)this.type;
/* 113 */       this.type = (ConnectorType)eResolveProxy(oldType);
/* 114 */       if (this.type != oldType)
/*     */       {
/* 116 */         if (eNotificationRequired())
/* 117 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 5, oldType, this.type)); 
/*     */       }
/*     */     } 
/* 120 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType basicGetType() {
/* 130 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(ConnectorType newType) {
/* 140 */     ConnectorType oldType = this.type;
/* 141 */     this.type = newType;
/* 142 */     if (eNotificationRequired()) {
/* 143 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundType getCompoundType() {
/* 153 */     if (eContainerFeatureID() != 6) return null; 
/* 154 */     return (CompoundType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCompoundType(CompoundType newCompoundType, NotificationChain msgs) {
/* 164 */     msgs = eBasicSetContainer((InternalEObject)newCompoundType, 6, msgs);
/* 165 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompoundType(CompoundType newCompoundType) {
/* 175 */     if (newCompoundType != eInternalContainer() || (eContainerFeatureID() != 6 && newCompoundType != null)) {
/*     */       
/* 177 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newCompoundType))
/* 178 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 179 */       NotificationChain msgs = null;
/* 180 */       if (eInternalContainer() != null)
/* 181 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 182 */       if (newCompoundType != null)
/* 183 */         msgs = ((InternalEObject)newCompoundType).eInverseAdd((InternalEObject)this, 12, CompoundType.class, msgs); 
/* 184 */       msgs = basicSetCompoundType(newCompoundType, msgs);
/* 185 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 187 */     } else if (eNotificationRequired()) {
/* 188 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, newCompoundType, newCompoundType));
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
/* 199 */     switch (featureID) {
/*     */       
/*     */       case 6:
/* 202 */         if (eInternalContainer() != null)
/* 203 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 204 */         return basicSetCompoundType((CompoundType)otherEnd, msgs);
/*     */     } 
/* 206 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 217 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 220 */         return ((InternalEList)getActualPort()).basicRemove(otherEnd, msgs);
/*     */       case 6:
/* 222 */         return basicSetCompoundType((CompoundType)null, msgs);
/*     */     } 
/* 224 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 235 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 6:
/* 238 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 12, CompoundType.class, msgs);
/*     */     } 
/* 240 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 251 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 254 */         return getActualPort();
/*     */       case 5:
/* 256 */         if (resolve) return getType(); 
/* 257 */         return basicGetType();
/*     */       case 6:
/* 259 */         return getCompoundType();
/*     */     } 
/* 261 */     return super.eGet(featureID, resolve, coreType);
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
/* 273 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 276 */         getActualPort().clear();
/* 277 */         getActualPort().addAll((Collection)newValue);
/*     */         return;
/*     */       case 5:
/* 280 */         setType((ConnectorType)newValue);
/*     */         return;
/*     */       case 6:
/* 283 */         setCompoundType((CompoundType)newValue);
/*     */         return;
/*     */     } 
/* 286 */     super.eSet(featureID, newValue);
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
/* 297 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 300 */         getActualPort().clear();
/*     */         return;
/*     */       case 5:
/* 303 */         setType((ConnectorType)null);
/*     */         return;
/*     */       case 6:
/* 306 */         setCompoundType((CompoundType)null);
/*     */         return;
/*     */     } 
/* 309 */     super.eUnset(featureID);
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
/* 320 */     switch (featureID) {
/*     */       
/*     */       case 4:
/* 323 */         return (this.actualPort != null && !this.actualPort.isEmpty());
/*     */       case 5:
/* 325 */         return (this.type != null);
/*     */       case 6:
/* 327 */         return (getCompoundType() != null);
/*     */     } 
/* 329 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\ConnectorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */