/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
/*     */ import ujf.verimag.bip.Extra.Contracts.Contract;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ComponentTypeImpl
/*     */   extends PartTypeImpl
/*     */   implements ComponentType
/*     */ {
/*     */   protected EList<Port> port;
/*     */   protected EList<PriorityRule> priorityRule;
/*     */   protected EList<InterfaceVariable> interfaceVariable;
/*     */   protected EList<Contract> contract;
/*     */   protected static final boolean IS_MULTISHOT_EDEFAULT = false;
/*     */   protected boolean isMultishot = false;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 133 */     return BehaviorsPackage.Literals.COMPONENT_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Port> getPort() {
/* 143 */     if (this.port == null)
/*     */     {
/* 145 */       this.port = (EList<Port>)new EObjectContainmentWithInverseEList(Port.class, (InternalEObject)this, 7, 2);
/*     */     }
/* 147 */     return this.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<PriorityRule> getPriorityRule() {
/* 157 */     if (this.priorityRule == null)
/*     */     {
/* 159 */       this.priorityRule = (EList<PriorityRule>)new EObjectContainmentWithInverseEList(PriorityRule.class, (InternalEObject)this, 8, 3);
/*     */     }
/* 161 */     return this.priorityRule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<InterfaceVariable> getInterfaceVariable() {
/* 171 */     if (this.interfaceVariable == null)
/*     */     {
/* 173 */       this.interfaceVariable = (EList<InterfaceVariable>)new EObjectContainmentWithInverseEList(InterfaceVariable.class, (InternalEObject)this, 9, 4);
/*     */     }
/* 175 */     return this.interfaceVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Contract> getContract() {
/* 185 */     if (this.contract == null)
/*     */     {
/* 187 */       this.contract = (EList<Contract>)new EObjectWithInverseResolvingEList(Contract.class, (InternalEObject)this, 10, 16);
/*     */     }
/* 189 */     return this.contract;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsMultishot() {
/* 199 */     return this.isMultishot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsMultishot(boolean newIsMultishot) {
/* 209 */     boolean oldIsMultishot = this.isMultishot;
/* 210 */     this.isMultishot = newIsMultishot;
/* 211 */     if (eNotificationRequired()) {
/* 212 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 11, oldIsMultishot, this.isMultishot));
/*     */     }
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
/* 224 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 227 */         return ((InternalEList)getPort()).basicAdd(otherEnd, msgs);
/*     */       case 8:
/* 229 */         return ((InternalEList)getPriorityRule()).basicAdd(otherEnd, msgs);
/*     */       case 9:
/* 231 */         return ((InternalEList)getInterfaceVariable()).basicAdd(otherEnd, msgs);
/*     */       case 10:
/* 233 */         return ((InternalEList)getContract()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 235 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 246 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 249 */         return ((InternalEList)getPort()).basicRemove(otherEnd, msgs);
/*     */       case 8:
/* 251 */         return ((InternalEList)getPriorityRule()).basicRemove(otherEnd, msgs);
/*     */       case 9:
/* 253 */         return ((InternalEList)getInterfaceVariable()).basicRemove(otherEnd, msgs);
/*     */       case 10:
/* 255 */         return ((InternalEList)getContract()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 257 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 268 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 271 */         return getPort();
/*     */       case 8:
/* 273 */         return getPriorityRule();
/*     */       case 9:
/* 275 */         return getInterfaceVariable();
/*     */       case 10:
/* 277 */         return getContract();
/*     */       case 11:
/* 279 */         return Boolean.valueOf(isIsMultishot());
/*     */     } 
/* 281 */     return super.eGet(featureID, resolve, coreType);
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
/* 293 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 296 */         getPort().clear();
/* 297 */         getPort().addAll((Collection)newValue);
/*     */         return;
/*     */       case 8:
/* 300 */         getPriorityRule().clear();
/* 301 */         getPriorityRule().addAll((Collection)newValue);
/*     */         return;
/*     */       case 9:
/* 304 */         getInterfaceVariable().clear();
/* 305 */         getInterfaceVariable().addAll((Collection)newValue);
/*     */         return;
/*     */       case 10:
/* 308 */         getContract().clear();
/* 309 */         getContract().addAll((Collection)newValue);
/*     */         return;
/*     */       case 11:
/* 312 */         setIsMultishot(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 315 */     super.eSet(featureID, newValue);
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
/* 326 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 329 */         getPort().clear();
/*     */         return;
/*     */       case 8:
/* 332 */         getPriorityRule().clear();
/*     */         return;
/*     */       case 9:
/* 335 */         getInterfaceVariable().clear();
/*     */         return;
/*     */       case 10:
/* 338 */         getContract().clear();
/*     */         return;
/*     */       case 11:
/* 341 */         setIsMultishot(false);
/*     */         return;
/*     */     } 
/* 344 */     super.eUnset(featureID);
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
/* 355 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 358 */         return (this.port != null && !this.port.isEmpty());
/*     */       case 8:
/* 360 */         return (this.priorityRule != null && !this.priorityRule.isEmpty());
/*     */       case 9:
/* 362 */         return (this.interfaceVariable != null && !this.interfaceVariable.isEmpty());
/*     */       case 10:
/* 364 */         return (this.contract != null && !this.contract.isEmpty());
/*     */       case 11:
/* 366 */         return this.isMultishot;
/*     */     } 
/* 368 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 379 */     if (eIsProxy()) return super.toString();
/*     */     
/* 381 */     StringBuffer result = new StringBuffer(super.toString());
/* 382 */     result.append(" (isMultishot: ");
/* 383 */     result.append(this.isMultishot);
/* 384 */     result.append(')');
/* 385 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\ComponentTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */