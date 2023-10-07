/*     */ package org.eclipse.emf.ecore.xml.namespace.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
/*     */ import org.eclipse.emf.ecore.util.BasicFeatureMap;
/*     */ import org.eclipse.emf.ecore.util.EcoreEMap;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xml.namespace.SpaceType;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespaceDocumentRoot;
/*     */ import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLNamespaceDocumentRootImpl
/*     */   extends EObjectImpl
/*     */   implements XMLNamespaceDocumentRoot
/*     */ {
/*     */   protected FeatureMap mixed;
/*     */   protected EMap<String, String> xMLNSPrefixMap;
/*     */   protected EMap<String, String> xSISchemaLocation;
/*  98 */   protected static final String BASE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   protected String base = BASE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   protected static final String ID_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   protected String id = ID_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   protected static final String LANG_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   protected String lang = LANG_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   protected static final SpaceType SPACE_EDEFAULT = SpaceType.DEFAULT_LITERAL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   protected SpaceType space = SPACE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean spaceESet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 197 */     return XMLNamespacePackage.Literals.XML_NAMESPACE_DOCUMENT_ROOT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FeatureMap getMixed() {
/* 207 */     if (this.mixed == null)
/*     */     {
/* 209 */       this.mixed = (FeatureMap)new BasicFeatureMap((InternalEObject)this, 0);
/*     */     }
/* 211 */     return this.mixed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<String, String> getXMLNSPrefixMap() {
/* 221 */     if (this.xMLNSPrefixMap == null)
/*     */     {
/* 223 */       this.xMLNSPrefixMap = (EMap<String, String>)new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, (InternalEObject)this, 1);
/*     */     }
/* 225 */     return this.xMLNSPrefixMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<String, String> getXSISchemaLocation() {
/* 235 */     if (this.xSISchemaLocation == null)
/*     */     {
/* 237 */       this.xSISchemaLocation = (EMap<String, String>)new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, (InternalEObject)this, 2);
/*     */     }
/* 239 */     return this.xSISchemaLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBase() {
/* 249 */     return this.base;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBase(String newBase) {
/* 259 */     String oldBase = this.base;
/* 260 */     this.base = newBase;
/* 261 */     if (eNotificationRequired()) {
/* 262 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldBase, this.base));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 272 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String newId) {
/* 282 */     String oldId = this.id;
/* 283 */     this.id = newId;
/* 284 */     if (eNotificationRequired()) {
/* 285 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, oldId, this.id));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLang() {
/* 295 */     return this.lang;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLang(String newLang) {
/* 305 */     String oldLang = this.lang;
/* 306 */     this.lang = newLang;
/* 307 */     if (eNotificationRequired()) {
/* 308 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, oldLang, this.lang));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpaceType getSpace() {
/* 318 */     return this.space;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpace(SpaceType newSpace) {
/* 328 */     SpaceType oldSpace = this.space;
/* 329 */     this.space = (newSpace == null) ? SPACE_EDEFAULT : newSpace;
/* 330 */     boolean oldSpaceESet = this.spaceESet;
/* 331 */     this.spaceESet = true;
/* 332 */     if (eNotificationRequired()) {
/* 333 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, oldSpace, this.space, !oldSpaceESet));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetSpace() {
/* 343 */     SpaceType oldSpace = this.space;
/* 344 */     boolean oldSpaceESet = this.spaceESet;
/* 345 */     this.space = SPACE_EDEFAULT;
/* 346 */     this.spaceESet = false;
/* 347 */     if (eNotificationRequired()) {
/* 348 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 2, 6, oldSpace, SPACE_EDEFAULT, oldSpaceESet));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSetSpace() {
/* 358 */     return this.spaceESet;
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
/* 369 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 372 */         return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
/*     */       case 1:
/* 374 */         return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
/*     */       case 2:
/* 376 */         return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 378 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
/* 389 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 392 */         if (coreType) return getMixed(); 
/* 393 */         return ((FeatureMap.Internal)getMixed()).getWrapper();
/*     */       case 1:
/* 395 */         if (coreType) return getXMLNSPrefixMap(); 
/* 396 */         return getXMLNSPrefixMap().map();
/*     */       case 2:
/* 398 */         if (coreType) return getXSISchemaLocation(); 
/* 399 */         return getXSISchemaLocation().map();
/*     */       case 3:
/* 401 */         return getBase();
/*     */       case 4:
/* 403 */         return getId();
/*     */       case 5:
/* 405 */         return getLang();
/*     */       case 6:
/* 407 */         return getSpace();
/*     */     } 
/* 409 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 420 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 423 */         ((FeatureMap.Internal)getMixed()).set(newValue);
/*     */         return;
/*     */       case 1:
/* 426 */         ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
/*     */         return;
/*     */       case 2:
/* 429 */         ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
/*     */         return;
/*     */       case 3:
/* 432 */         setBase((String)newValue);
/*     */         return;
/*     */       case 4:
/* 435 */         setId((String)newValue);
/*     */         return;
/*     */       case 5:
/* 438 */         setLang((String)newValue);
/*     */         return;
/*     */       case 6:
/* 441 */         setSpace((SpaceType)newValue);
/*     */         return;
/*     */     } 
/* 444 */     eDynamicSet(featureID, newValue);
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
/* 455 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 458 */         getMixed().clear();
/*     */         return;
/*     */       case 1:
/* 461 */         getXMLNSPrefixMap().clear();
/*     */         return;
/*     */       case 2:
/* 464 */         getXSISchemaLocation().clear();
/*     */         return;
/*     */       case 3:
/* 467 */         setBase(BASE_EDEFAULT);
/*     */         return;
/*     */       case 4:
/* 470 */         setId(ID_EDEFAULT);
/*     */         return;
/*     */       case 5:
/* 473 */         setLang(LANG_EDEFAULT);
/*     */         return;
/*     */       case 6:
/* 476 */         unsetSpace();
/*     */         return;
/*     */     } 
/* 479 */     eDynamicUnset(featureID);
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
/* 490 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 493 */         return (this.mixed != null && !this.mixed.isEmpty());
/*     */       case 1:
/* 495 */         return (this.xMLNSPrefixMap != null && !this.xMLNSPrefixMap.isEmpty());
/*     */       case 2:
/* 497 */         return (this.xSISchemaLocation != null && !this.xSISchemaLocation.isEmpty());
/*     */       case 3:
/* 499 */         return (BASE_EDEFAULT == null) ? ((this.base != null)) : (!BASE_EDEFAULT.equals(this.base));
/*     */       case 4:
/* 501 */         return (ID_EDEFAULT == null) ? ((this.id != null)) : (!ID_EDEFAULT.equals(this.id));
/*     */       case 5:
/* 503 */         return (LANG_EDEFAULT == null) ? ((this.lang != null)) : (!LANG_EDEFAULT.equals(this.lang));
/*     */       case 6:
/* 505 */         return isSetSpace();
/*     */     } 
/* 507 */     return eDynamicIsSet(featureID);
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
/* 518 */     if (eIsProxy()) return super.toString();
/*     */     
/* 520 */     StringBuffer result = new StringBuffer(super.toString());
/* 521 */     result.append(" (mixed: ");
/* 522 */     result.append(this.mixed);
/* 523 */     result.append(", base: ");
/* 524 */     result.append(this.base);
/* 525 */     result.append(", id: ");
/* 526 */     result.append(this.id);
/* 527 */     result.append(", lang: ");
/* 528 */     result.append(this.lang);
/* 529 */     result.append(", space: ");
/* 530 */     if (this.spaceESet) { result.append(this.space); } else { result.append("<unset>"); }
/* 531 */      result.append(')');
/* 532 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\namespace\impl\XMLNamespaceDocumentRootImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */