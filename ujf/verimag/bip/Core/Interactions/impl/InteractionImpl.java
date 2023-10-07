/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
/*     */ import ujf.verimag.bip.Core.Priorities.impl.PriorityElementImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InteractionImpl
/*     */   extends PriorityElementImpl
/*     */   implements Interaction
/*     */ {
/*     */   protected EList<PortReference> port;
/*     */   protected PartElementReference connector;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  86 */     return InteractionsPackage.Literals.INTERACTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<PortReference> getPort() {
/*  96 */     if (this.port == null)
/*     */     {
/*  98 */       this.port = (EList<PortReference>)new EObjectContainmentEList(PortReference.class, (InternalEObject)this, 0);
/*     */     }
/* 100 */     return this.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartElementReference getConnector() {
/* 110 */     return this.connector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConnector(PartElementReference newConnector, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 120 */     PartElementReference oldConnector = this.connector;
/* 121 */     this.connector = newConnector;
/* 122 */     if (eNotificationRequired()) {
/*     */       
/* 124 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldConnector, newConnector);
/* 125 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 127 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnector(PartElementReference newConnector) {
/* 137 */     if (newConnector != this.connector) {
/*     */       
/* 139 */       NotificationChain msgs = null;
/* 140 */       if (this.connector != null)
/* 141 */         msgs = ((InternalEObject)this.connector).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 142 */       if (newConnector != null)
/* 143 */         msgs = ((InternalEObject)newConnector).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 144 */       msgs = basicSetConnector(newConnector, msgs);
/* 145 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 147 */     } else if (eNotificationRequired()) {
/* 148 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newConnector, newConnector));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 159 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 162 */         return ((InternalEList)getPort()).basicRemove(otherEnd, msgs);
/*     */       case 1:
/* 164 */         return basicSetConnector((PartElementReference)null, msgs);
/*     */     } 
/* 166 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 177 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 180 */         return getPort();
/*     */       case 1:
/* 182 */         return getConnector();
/*     */     } 
/* 184 */     return super.eGet(featureID, resolve, coreType);
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
/* 196 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 199 */         getPort().clear();
/* 200 */         getPort().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 203 */         setConnector((PartElementReference)newValue);
/*     */         return;
/*     */     } 
/* 206 */     super.eSet(featureID, newValue);
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
/* 217 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 220 */         getPort().clear();
/*     */         return;
/*     */       case 1:
/* 223 */         setConnector((PartElementReference)null);
/*     */         return;
/*     */     } 
/* 226 */     super.eUnset(featureID);
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
/* 237 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 240 */         return (this.port != null && !this.port.isEmpty());
/*     */       case 1:
/* 242 */         return (this.connector != null);
/*     */     } 
/* 244 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\InteractionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */