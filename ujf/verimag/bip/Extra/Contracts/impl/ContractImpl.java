/*     */ package ujf.verimag.bip.Extra.Contracts.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.impl.CompoundTypeImpl;
/*     */ import ujf.verimag.bip.Extra.Contracts.Contract;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContractImpl
/*     */   extends CompoundTypeImpl
/*     */   implements Contract
/*     */ {
/*     */   protected Component promise;
/*     */   protected Component assume;
/*     */   protected ComponentType contracted;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  92 */     return ContractsPackage.Literals.CONTRACT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getPromise() {
/* 102 */     if (this.promise != null && this.promise.eIsProxy()) {
/*     */       
/* 104 */       InternalEObject oldPromise = (InternalEObject)this.promise;
/* 105 */       this.promise = (Component)eResolveProxy(oldPromise);
/* 106 */       if (this.promise != oldPromise)
/*     */       {
/* 108 */         if (eNotificationRequired())
/* 109 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 14, oldPromise, this.promise)); 
/*     */       }
/*     */     } 
/* 112 */     return this.promise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component basicGetPromise() {
/* 122 */     return this.promise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromise(Component newPromise) {
/* 132 */     Component oldPromise = this.promise;
/* 133 */     this.promise = newPromise;
/* 134 */     if (eNotificationRequired()) {
/* 135 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 14, oldPromise, this.promise));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getAssume() {
/* 145 */     if (this.assume != null && this.assume.eIsProxy()) {
/*     */       
/* 147 */       InternalEObject oldAssume = (InternalEObject)this.assume;
/* 148 */       this.assume = (Component)eResolveProxy(oldAssume);
/* 149 */       if (this.assume != oldAssume)
/*     */       {
/* 151 */         if (eNotificationRequired())
/* 152 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 15, oldAssume, this.assume)); 
/*     */       }
/*     */     } 
/* 155 */     return this.assume;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component basicGetAssume() {
/* 165 */     return this.assume;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAssume(Component newAssume) {
/* 175 */     Component oldAssume = this.assume;
/* 176 */     this.assume = newAssume;
/* 177 */     if (eNotificationRequired()) {
/* 178 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 15, oldAssume, this.assume));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType getContracted() {
/* 188 */     if (this.contracted != null && this.contracted.eIsProxy()) {
/*     */       
/* 190 */       InternalEObject oldContracted = (InternalEObject)this.contracted;
/* 191 */       this.contracted = (ComponentType)eResolveProxy(oldContracted);
/* 192 */       if (this.contracted != oldContracted)
/*     */       {
/* 194 */         if (eNotificationRequired())
/* 195 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 16, oldContracted, this.contracted)); 
/*     */       }
/*     */     } 
/* 198 */     return this.contracted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentType basicGetContracted() {
/* 208 */     return this.contracted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetContracted(ComponentType newContracted, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 218 */     ComponentType oldContracted = this.contracted;
/* 219 */     this.contracted = newContracted;
/* 220 */     if (eNotificationRequired()) {
/*     */       
/* 222 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 16, oldContracted, newContracted);
/* 223 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 225 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContracted(ComponentType newContracted) {
/* 235 */     if (newContracted != this.contracted) {
/*     */       
/* 237 */       NotificationChain msgs = null;
/* 238 */       if (this.contracted != null)
/* 239 */         msgs = ((InternalEObject)this.contracted).eInverseRemove((InternalEObject)this, 10, ComponentType.class, msgs); 
/* 240 */       if (newContracted != null)
/* 241 */         msgs = ((InternalEObject)newContracted).eInverseAdd((InternalEObject)this, 10, ComponentType.class, msgs); 
/* 242 */       msgs = basicSetContracted(newContracted, msgs);
/* 243 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 245 */     } else if (eNotificationRequired()) {
/* 246 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 16, newContracted, newContracted));
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
/* 257 */     switch (featureID) {
/*     */       
/*     */       case 16:
/* 260 */         if (this.contracted != null)
/* 261 */           msgs = ((InternalEObject)this.contracted).eInverseRemove((InternalEObject)this, 10, ComponentType.class, msgs); 
/* 262 */         return basicSetContracted((ComponentType)otherEnd, msgs);
/*     */     } 
/* 264 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 275 */     switch (featureID) {
/*     */       
/*     */       case 16:
/* 278 */         return basicSetContracted((ComponentType)null, msgs);
/*     */     } 
/* 280 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 291 */     switch (featureID) {
/*     */       
/*     */       case 14:
/* 294 */         if (resolve) return getPromise(); 
/* 295 */         return basicGetPromise();
/*     */       case 15:
/* 297 */         if (resolve) return getAssume(); 
/* 298 */         return basicGetAssume();
/*     */       case 16:
/* 300 */         if (resolve) return getContracted(); 
/* 301 */         return basicGetContracted();
/*     */     } 
/* 303 */     return super.eGet(featureID, resolve, coreType);
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
/* 314 */     switch (featureID) {
/*     */       
/*     */       case 14:
/* 317 */         setPromise((Component)newValue);
/*     */         return;
/*     */       case 15:
/* 320 */         setAssume((Component)newValue);
/*     */         return;
/*     */       case 16:
/* 323 */         setContracted((ComponentType)newValue);
/*     */         return;
/*     */     } 
/* 326 */     super.eSet(featureID, newValue);
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
/* 337 */     switch (featureID) {
/*     */       
/*     */       case 14:
/* 340 */         setPromise((Component)null);
/*     */         return;
/*     */       case 15:
/* 343 */         setAssume((Component)null);
/*     */         return;
/*     */       case 16:
/* 346 */         setContracted((ComponentType)null);
/*     */         return;
/*     */     } 
/* 349 */     super.eUnset(featureID);
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
/* 360 */     switch (featureID) {
/*     */       
/*     */       case 14:
/* 363 */         return (this.promise != null);
/*     */       case 15:
/* 365 */         return (this.assume != null);
/*     */       case 16:
/* 367 */         return (this.contracted != null);
/*     */     } 
/* 369 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\impl\ContractImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */