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
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceableElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BipTypeImpl
/*     */   extends NamedElementImpl
/*     */   implements BipType
/*     */ {
/*     */   protected EList<DataParameter> dataParameter;
/*     */   protected static final int START_SOURCE_LINE_EDEFAULT = 0;
/*  81 */   protected int startSourceLine = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 101 */     return BehaviorsPackage.Literals.BIP_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<DataParameter> getDataParameter() {
/* 111 */     if (this.dataParameter == null)
/*     */     {
/* 113 */       this.dataParameter = (EList<DataParameter>)new EObjectContainmentWithInverseEList(DataParameter.class, (InternalEObject)this, 2, 5);
/*     */     }
/* 115 */     return this.dataParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartSourceLine() {
/* 125 */     return this.startSourceLine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartSourceLine(int newStartSourceLine) {
/* 135 */     int oldStartSourceLine = this.startSourceLine;
/* 136 */     this.startSourceLine = newStartSourceLine;
/* 137 */     if (eNotificationRequired()) {
/* 138 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldStartSourceLine, this.startSourceLine));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Module getModule() {
/* 148 */     if (eContainerFeatureID() != 4) return null; 
/* 149 */     return (Module)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetModule(Module newModule, NotificationChain msgs) {
/* 159 */     msgs = eBasicSetContainer((InternalEObject)newModule, 4, msgs);
/* 160 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModule(Module newModule) {
/* 170 */     if (newModule != eInternalContainer() || (eContainerFeatureID() != 4 && newModule != null)) {
/*     */       
/* 172 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newModule))
/* 173 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 174 */       NotificationChain msgs = null;
/* 175 */       if (eInternalContainer() != null)
/* 176 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 177 */       if (newModule != null)
/* 178 */         msgs = ((InternalEObject)newModule).eInverseAdd((InternalEObject)this, 2, Module.class, msgs); 
/* 179 */       msgs = basicSetModule(newModule, msgs);
/* 180 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 182 */     } else if (eNotificationRequired()) {
/* 183 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, newModule, newModule));
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
/* 195 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 198 */         return ((InternalEList)getDataParameter()).basicAdd(otherEnd, msgs);
/*     */       case 4:
/* 200 */         if (eInternalContainer() != null)
/* 201 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 202 */         return basicSetModule((Module)otherEnd, msgs);
/*     */     } 
/* 204 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 215 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 218 */         return ((InternalEList)getDataParameter()).basicRemove(otherEnd, msgs);
/*     */       case 4:
/* 220 */         return basicSetModule((Module)null, msgs);
/*     */     } 
/* 222 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 233 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 4:
/* 236 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 2, Module.class, msgs);
/*     */     } 
/* 238 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 249 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 252 */         return getDataParameter();
/*     */       case 3:
/* 254 */         return Integer.valueOf(getStartSourceLine());
/*     */       case 4:
/* 256 */         return getModule();
/*     */     } 
/* 258 */     return super.eGet(featureID, resolve, coreType);
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
/* 270 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 273 */         getDataParameter().clear();
/* 274 */         getDataParameter().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 277 */         setStartSourceLine(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 4:
/* 280 */         setModule((Module)newValue);
/*     */         return;
/*     */     } 
/* 283 */     super.eSet(featureID, newValue);
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
/* 294 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 297 */         getDataParameter().clear();
/*     */         return;
/*     */       case 3:
/* 300 */         setStartSourceLine(0);
/*     */         return;
/*     */       case 4:
/* 303 */         setModule((Module)null);
/*     */         return;
/*     */     } 
/* 306 */     super.eUnset(featureID);
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
/* 317 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 320 */         return (this.dataParameter != null && !this.dataParameter.isEmpty());
/*     */       case 3:
/* 322 */         return (this.startSourceLine != 0);
/*     */       case 4:
/* 324 */         return (getModule() != null);
/*     */     } 
/* 326 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 337 */     if (baseClass == ParameterizedElement.class) {
/*     */       
/* 339 */       switch (derivedFeatureID) {
/*     */         case 2:
/* 341 */           return 0;
/* 342 */       }  return -1;
/*     */     } 
/*     */     
/* 345 */     if (baseClass == TraceableElement.class) {
/*     */       
/* 347 */       switch (derivedFeatureID) {
/*     */         case 3:
/* 349 */           return 0;
/* 350 */       }  return -1;
/*     */     } 
/*     */     
/* 353 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 364 */     if (baseClass == ParameterizedElement.class) {
/*     */       
/* 366 */       switch (baseFeatureID) {
/*     */         case 0:
/* 368 */           return 2;
/* 369 */       }  return -1;
/*     */     } 
/*     */     
/* 372 */     if (baseClass == TraceableElement.class) {
/*     */       
/* 374 */       switch (baseFeatureID) {
/*     */         case 0:
/* 376 */           return 3;
/* 377 */       }  return -1;
/*     */     } 
/*     */     
/* 380 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
/* 391 */     if (eIsProxy()) return super.toString();
/*     */     
/* 393 */     StringBuffer result = new StringBuffer(super.toString());
/* 394 */     result.append(" (startSourceLine: ");
/* 395 */     result.append(this.startSourceLine);
/* 396 */     result.append(')');
/* 397 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\BipTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */