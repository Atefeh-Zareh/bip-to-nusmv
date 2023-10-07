/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortDefinitionImpl
/*     */   extends NamedElementImpl
/*     */   implements PortDefinition
/*     */ {
/*     */   protected PortType type;
/*     */   protected EList<Variable> exposedVariable;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  89 */     return BehaviorsPackage.Literals.PORT_DEFINITION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtomType getAtomType() {
/*  99 */     if (eContainerFeatureID() != 2) return null; 
/* 100 */     return (AtomType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetAtomType(AtomType newAtomType, NotificationChain msgs) {
/* 110 */     msgs = eBasicSetContainer((InternalEObject)newAtomType, 2, msgs);
/* 111 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAtomType(AtomType newAtomType) {
/* 121 */     if (newAtomType != eInternalContainer() || (eContainerFeatureID() != 2 && newAtomType != null)) {
/*     */       
/* 123 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newAtomType))
/* 124 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 125 */       NotificationChain msgs = null;
/* 126 */       if (eInternalContainer() != null)
/* 127 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 128 */       if (newAtomType != null)
/* 129 */         msgs = ((InternalEObject)newAtomType).eInverseAdd((InternalEObject)this, 14, AtomType.class, msgs); 
/* 130 */       msgs = basicSetAtomType(newAtomType, msgs);
/* 131 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 133 */     } else if (eNotificationRequired()) {
/* 134 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newAtomType, newAtomType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectorType getConnectorType() {
/* 144 */     if (eContainerFeatureID() != 3) return null; 
/* 145 */     return (ConnectorType)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConnectorType(ConnectorType newConnectorType, NotificationChain msgs) {
/* 155 */     msgs = eBasicSetContainer((InternalEObject)newConnectorType, 3, msgs);
/* 156 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectorType(ConnectorType newConnectorType) {
/* 166 */     if (newConnectorType != eInternalContainer() || (eContainerFeatureID() != 3 && newConnectorType != null)) {
/*     */       
/* 168 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newConnectorType))
/* 169 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 170 */       NotificationChain msgs = null;
/* 171 */       if (eInternalContainer() != null)
/* 172 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 173 */       if (newConnectorType != null)
/* 174 */         msgs = ((InternalEObject)newConnectorType).eInverseAdd((InternalEObject)this, 10, ConnectorType.class, msgs); 
/* 175 */       msgs = basicSetConnectorType(newConnectorType, msgs);
/* 176 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 178 */     } else if (eNotificationRequired()) {
/* 179 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newConnectorType, newConnectorType));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType getType() {
/* 189 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/* 191 */       InternalEObject oldType = (InternalEObject)this.type;
/* 192 */       this.type = (PortType)eResolveProxy(oldType);
/* 193 */       if (this.type != oldType)
/*     */       {
/* 195 */         if (eNotificationRequired())
/* 196 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 4, oldType, this.type)); 
/*     */       }
/*     */     } 
/* 199 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortType basicGetType() {
/* 209 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(PortType newType) {
/* 219 */     PortType oldType = this.type;
/* 220 */     this.type = newType;
/* 221 */     if (eNotificationRequired()) {
/* 222 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Variable> getExposedVariable() {
/* 232 */     if (this.exposedVariable == null)
/*     */     {
/* 234 */       this.exposedVariable = (EList<Variable>)new EObjectResolvingEList(Variable.class, (InternalEObject)this, 5);
/*     */     }
/* 236 */     return this.exposedVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 247 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 250 */         if (eInternalContainer() != null)
/* 251 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 252 */         return basicSetAtomType((AtomType)otherEnd, msgs);
/*     */       case 3:
/* 254 */         if (eInternalContainer() != null)
/* 255 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 256 */         return basicSetConnectorType((ConnectorType)otherEnd, msgs);
/*     */     } 
/* 258 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 269 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 272 */         return basicSetAtomType((AtomType)null, msgs);
/*     */       case 3:
/* 274 */         return basicSetConnectorType((ConnectorType)null, msgs);
/*     */     } 
/* 276 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 287 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 2:
/* 290 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 14, AtomType.class, msgs);
/*     */       case 3:
/* 292 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 10, ConnectorType.class, msgs);
/*     */     } 
/* 294 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 305 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 308 */         return getAtomType();
/*     */       case 3:
/* 310 */         return getConnectorType();
/*     */       case 4:
/* 312 */         if (resolve) return getType(); 
/* 313 */         return basicGetType();
/*     */       case 5:
/* 315 */         return getExposedVariable();
/*     */     } 
/* 317 */     return super.eGet(featureID, resolve, coreType);
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
/* 329 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 332 */         setAtomType((AtomType)newValue);
/*     */         return;
/*     */       case 3:
/* 335 */         setConnectorType((ConnectorType)newValue);
/*     */         return;
/*     */       case 4:
/* 338 */         setType((PortType)newValue);
/*     */         return;
/*     */       case 5:
/* 341 */         getExposedVariable().clear();
/* 342 */         getExposedVariable().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 345 */     super.eSet(featureID, newValue);
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
/* 356 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 359 */         setAtomType((AtomType)null);
/*     */         return;
/*     */       case 3:
/* 362 */         setConnectorType((ConnectorType)null);
/*     */         return;
/*     */       case 4:
/* 365 */         setType((PortType)null);
/*     */         return;
/*     */       case 5:
/* 368 */         getExposedVariable().clear();
/*     */         return;
/*     */     } 
/* 371 */     super.eUnset(featureID);
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
/* 382 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 385 */         return (getAtomType() != null);
/*     */       case 3:
/* 387 */         return (getConnectorType() != null);
/*     */       case 4:
/* 389 */         return (this.type != null);
/*     */       case 5:
/* 391 */         return (this.exposedVariable != null && !this.exposedVariable.isEmpty());
/*     */     } 
/* 393 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\PortDefinitionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */