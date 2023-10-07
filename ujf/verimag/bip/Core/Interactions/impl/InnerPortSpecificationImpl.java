/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class InnerPortSpecificationImpl
/*     */   extends EObjectImpl
/*     */   implements InnerPortSpecification
/*     */ {
/*     */   protected Port targetPort;
/*     */   protected PartElementReference targetInstance;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  78 */     return InteractionsPackage.Literals.INNER_PORT_SPECIFICATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getTargetPort() {
/*  88 */     if (this.targetPort != null && this.targetPort.eIsProxy()) {
/*     */       
/*  90 */       InternalEObject oldTargetPort = (InternalEObject)this.targetPort;
/*  91 */       this.targetPort = (Port)eResolveProxy(oldTargetPort);
/*  92 */       if (this.targetPort != oldTargetPort)
/*     */       {
/*  94 */         if (eNotificationRequired())
/*  95 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, oldTargetPort, this.targetPort)); 
/*     */       }
/*     */     } 
/*  98 */     return this.targetPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port basicGetTargetPort() {
/* 108 */     return this.targetPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetPort(Port newTargetPort) {
/* 118 */     Port oldTargetPort = this.targetPort;
/* 119 */     this.targetPort = newTargetPort;
/* 120 */     if (eNotificationRequired()) {
/* 121 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldTargetPort, this.targetPort));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartElementReference getTargetInstance() {
/* 131 */     return this.targetInstance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetTargetInstance(PartElementReference newTargetInstance, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 141 */     PartElementReference oldTargetInstance = this.targetInstance;
/* 142 */     this.targetInstance = newTargetInstance;
/* 143 */     if (eNotificationRequired()) {
/*     */       
/* 145 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldTargetInstance, newTargetInstance);
/* 146 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 148 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetInstance(PartElementReference newTargetInstance) {
/* 158 */     if (newTargetInstance != this.targetInstance) {
/*     */       
/* 160 */       NotificationChain msgs = null;
/* 161 */       if (this.targetInstance != null)
/* 162 */         msgs = ((InternalEObject)this.targetInstance).eInverseRemove((InternalEObject)this, 1, PartElementReference.class, msgs); 
/* 163 */       if (newTargetInstance != null)
/* 164 */         msgs = ((InternalEObject)newTargetInstance).eInverseAdd((InternalEObject)this, 1, PartElementReference.class, msgs); 
/* 165 */       msgs = basicSetTargetInstance(newTargetInstance, msgs);
/* 166 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 168 */     } else if (eNotificationRequired()) {
/* 169 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newTargetInstance, newTargetInstance));
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
/* 180 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 183 */         if (this.targetInstance != null)
/* 184 */           msgs = ((InternalEObject)this.targetInstance).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 185 */         return basicSetTargetInstance((PartElementReference)otherEnd, msgs);
/*     */     } 
/* 187 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 198 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 201 */         return basicSetTargetInstance((PartElementReference)null, msgs);
/*     */     } 
/* 203 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 214 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 217 */         if (resolve) return getTargetPort(); 
/* 218 */         return basicGetTargetPort();
/*     */       case 1:
/* 220 */         return getTargetInstance();
/*     */     } 
/* 222 */     return super.eGet(featureID, resolve, coreType);
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
/* 233 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 236 */         setTargetPort((Port)newValue);
/*     */         return;
/*     */       case 1:
/* 239 */         setTargetInstance((PartElementReference)newValue);
/*     */         return;
/*     */     } 
/* 242 */     super.eSet(featureID, newValue);
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
/* 253 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 256 */         setTargetPort((Port)null);
/*     */         return;
/*     */       case 1:
/* 259 */         setTargetInstance((PartElementReference)null);
/*     */         return;
/*     */     } 
/* 262 */     super.eUnset(featureID);
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
/* 273 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 276 */         return (this.targetPort != null);
/*     */       case 1:
/* 278 */         return (this.targetInstance != null);
/*     */     } 
/* 280 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\InnerPortSpecificationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */