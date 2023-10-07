/*     */ package ujf.verimag.bip.cmodel.impl;
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
/*     */ import ujf.verimag.bip.cmodel.CArgument;
/*     */ import ujf.verimag.bip.cmodel.CBlock;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
/*     */ import ujf.verimag.bip.cmodel.CCallable;
/*     */ import ujf.verimag.bip.cmodel.CTypedElement;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCallableImpl
/*     */   extends CItemImpl
/*     */   implements CCallable
/*     */ {
/*  59 */   protected static final String TYPE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected String type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<CBodyItem> content;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<CArgument> argument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected static final String SPECIFIER_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected String specifier = SPECIFIER_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   protected static final String QUALIFIER_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   protected String qualifier = QUALIFIER_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean BODY_IN_DECL_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean bodyInDecl = false;
/*     */ 
/*     */ 
/*     */ 
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
/* 167 */     return CmodelPackage.Literals.CCALLABLE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 176 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String newType) {
/* 185 */     String oldType = this.type;
/* 186 */     this.type = newType;
/* 187 */     if (eNotificationRequired()) {
/* 188 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CBodyItem> getContent() {
/* 197 */     if (this.content == null) {
/* 198 */       this.content = (EList<CBodyItem>)new EObjectContainmentEList(CBodyItem.class, (InternalEObject)this, 2);
/*     */     }
/* 200 */     return this.content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CArgument> getArgument() {
/* 209 */     if (this.argument == null) {
/* 210 */       this.argument = (EList<CArgument>)new EObjectContainmentEList(CArgument.class, (InternalEObject)this, 3);
/*     */     }
/* 212 */     return this.argument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSpecifier() {
/* 221 */     return this.specifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpecifier(String newSpecifier) {
/* 230 */     String oldSpecifier = this.specifier;
/* 231 */     this.specifier = newSpecifier;
/* 232 */     if (eNotificationRequired()) {
/* 233 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, oldSpecifier, this.specifier));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQualifier() {
/* 242 */     return this.qualifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQualifier(String newQualifier) {
/* 251 */     String oldQualifier = this.qualifier;
/* 252 */     this.qualifier = newQualifier;
/* 253 */     if (eNotificationRequired()) {
/* 254 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, oldQualifier, this.qualifier));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBodyInDecl() {
/* 263 */     return this.bodyInDecl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBodyInDecl(boolean newBodyInDecl) {
/* 272 */     boolean oldBodyInDecl = this.bodyInDecl;
/* 273 */     this.bodyInDecl = newBodyInDecl;
/* 274 */     if (eNotificationRequired()) {
/* 275 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, oldBodyInDecl, this.bodyInDecl));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 285 */     switch (featureID) {
/*     */       case 2:
/* 287 */         return ((InternalEList)getContent()).basicRemove(otherEnd, msgs);
/*     */       case 3:
/* 289 */         return ((InternalEList)getArgument()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 291 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 301 */     switch (featureID) {
/*     */       case 1:
/* 303 */         return getType();
/*     */       case 2:
/* 305 */         return getContent();
/*     */       case 3:
/* 307 */         return getArgument();
/*     */       case 4:
/* 309 */         return getSpecifier();
/*     */       case 5:
/* 311 */         return getQualifier();
/*     */       case 6:
/* 313 */         return Boolean.valueOf(isBodyInDecl());
/*     */     } 
/* 315 */     return super.eGet(featureID, resolve, coreType);
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
/* 326 */     switch (featureID) {
/*     */       case 1:
/* 328 */         setType((String)newValue);
/*     */         return;
/*     */       case 2:
/* 331 */         getContent().clear();
/* 332 */         getContent().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 335 */         getArgument().clear();
/* 336 */         getArgument().addAll((Collection)newValue);
/*     */         return;
/*     */       case 4:
/* 339 */         setSpecifier((String)newValue);
/*     */         return;
/*     */       case 5:
/* 342 */         setQualifier((String)newValue);
/*     */         return;
/*     */       case 6:
/* 345 */         setBodyInDecl(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 348 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 358 */     switch (featureID) {
/*     */       case 1:
/* 360 */         setType(TYPE_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 363 */         getContent().clear();
/*     */         return;
/*     */       case 3:
/* 366 */         getArgument().clear();
/*     */         return;
/*     */       case 4:
/* 369 */         setSpecifier(SPECIFIER_EDEFAULT);
/*     */         return;
/*     */       case 5:
/* 372 */         setQualifier(QUALIFIER_EDEFAULT);
/*     */         return;
/*     */       case 6:
/* 375 */         setBodyInDecl(false);
/*     */         return;
/*     */     } 
/* 378 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 388 */     switch (featureID) {
/*     */       case 1:
/* 390 */         return (TYPE_EDEFAULT == null) ? ((this.type != null)) : (!TYPE_EDEFAULT.equals(this.type));
/*     */       case 2:
/* 392 */         return (this.content != null && !this.content.isEmpty());
/*     */       case 3:
/* 394 */         return (this.argument != null && !this.argument.isEmpty());
/*     */       case 4:
/* 396 */         return (SPECIFIER_EDEFAULT == null) ? ((this.specifier != null)) : (!SPECIFIER_EDEFAULT.equals(this.specifier));
/*     */       case 5:
/* 398 */         return (QUALIFIER_EDEFAULT == null) ? ((this.qualifier != null)) : (!QUALIFIER_EDEFAULT.equals(this.qualifier));
/*     */       case 6:
/* 400 */         return this.bodyInDecl;
/*     */     } 
/* 402 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 412 */     if (baseClass == CTypedElement.class) {
/* 413 */       switch (derivedFeatureID) { case 1:
/* 414 */           return 0; }
/* 415 */        return -1;
/*     */     } 
/*     */     
/* 418 */     if (baseClass == CBlock.class) {
/* 419 */       switch (derivedFeatureID) { case 2:
/* 420 */           return 0; }
/* 421 */        return -1;
/*     */     } 
/*     */     
/* 424 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 434 */     if (baseClass == CTypedElement.class) {
/* 435 */       switch (baseFeatureID) { case 0:
/* 436 */           return 1; }
/* 437 */        return -1;
/*     */     } 
/*     */     
/* 440 */     if (baseClass == CBlock.class) {
/* 441 */       switch (baseFeatureID) { case 0:
/* 442 */           return 2; }
/* 443 */        return -1;
/*     */     } 
/*     */     
/* 446 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 456 */     if (eIsProxy()) return super.toString();
/*     */     
/* 458 */     StringBuffer result = new StringBuffer(super.toString());
/* 459 */     result.append(" (type: ");
/* 460 */     result.append(this.type);
/* 461 */     result.append(", specifier: ");
/* 462 */     result.append(this.specifier);
/* 463 */     result.append(", qualifier: ");
/* 464 */     result.append(this.qualifier);
/* 465 */     result.append(", bodyInDecl: ");
/* 466 */     result.append(this.bodyInDecl);
/* 467 */     result.append(')');
/* 468 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CCallableImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */