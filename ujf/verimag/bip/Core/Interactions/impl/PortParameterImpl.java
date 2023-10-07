/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.NamedElementImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortParameterImpl
/*     */   extends NamedElementImpl
/*     */   implements PortParameter
/*     */ {
/*     */   protected PortType type;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  71 */     return InteractionsPackage.Literals.PORT_PARAMETER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType getType() {
/*  81 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/*  83 */       InternalEObject oldType = (InternalEObject)this.type;
/*  84 */       this.type = (PortType)eResolveProxy(oldType);
/*  85 */       if (this.type != oldType)
/*     */       {
/*  87 */         if (eNotificationRequired())
/*  88 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 2, oldType, this.type)); 
/*     */       }
/*     */     } 
/*  91 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType basicGetType() {
/* 101 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(PortType newType) {
/* 111 */     PortType oldType = this.type;
/* 112 */     this.type = newType;
/* 113 */     if (eNotificationRequired()) {
/* 114 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getConnectorType() {
/* 124 */     if (eContainerFeatureID() != 3) return null; 
/* 125 */     return (ConnectorType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConnectorType(ConnectorType newConnectorType, NotificationChain msgs) {
/* 135 */     msgs = eBasicSetContainer((InternalEObject)newConnectorType, 3, msgs);
/* 136 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectorType(ConnectorType newConnectorType) {
/* 146 */     if (newConnectorType != eInternalContainer() || (eContainerFeatureID() != 3 && newConnectorType != null)) {
/*     */       
/* 148 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newConnectorType))
/* 149 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 150 */       NotificationChain msgs = null;
/* 151 */       if (eInternalContainer() != null)
/* 152 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 153 */       if (newConnectorType != null)
/* 154 */         msgs = ((InternalEObject)newConnectorType).eInverseAdd((InternalEObject)this, 9, ConnectorType.class, msgs); 
/* 155 */       msgs = basicSetConnectorType(newConnectorType, msgs);
/* 156 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 158 */     } else if (eNotificationRequired()) {
/* 159 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newConnectorType, newConnectorType));
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
/* 170 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 173 */         if (eInternalContainer() != null)
/* 174 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 175 */         return basicSetConnectorType((ConnectorType)otherEnd, msgs);
/*     */     } 
/* 177 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 188 */     switch (featureID) {
/*     */       
/*     */       case 3:
/* 191 */         return basicSetConnectorType((ConnectorType)null, msgs);
/*     */     } 
/* 193 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 204 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 3:
/* 207 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 9, ConnectorType.class, msgs);
/*     */     } 
/* 209 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 220 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 223 */         if (resolve) return getType(); 
/* 224 */         return basicGetType();
/*     */       case 3:
/* 226 */         return getConnectorType();
/*     */     } 
/* 228 */     return super.eGet(featureID, resolve, coreType);
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
/* 239 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 242 */         setType((PortType)newValue);
/*     */         return;
/*     */       case 3:
/* 245 */         setConnectorType((ConnectorType)newValue);
/*     */         return;
/*     */     } 
/* 248 */     super.eSet(featureID, newValue);
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
/* 259 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 262 */         setType((PortType)null);
/*     */         return;
/*     */       case 3:
/* 265 */         setConnectorType((ConnectorType)null);
/*     */         return;
/*     */     } 
/* 268 */     super.eUnset(featureID);
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
/* 279 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 282 */         return (this.type != null);
/*     */       case 3:
/* 284 */         return (getConnectorType() != null);
/*     */     } 
/* 286 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\PortParameterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */