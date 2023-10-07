/*     */ package org.eclipse.emf.ecore.xml.type.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.EMap;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
/*     */ import org.eclipse.emf.ecore.util.BasicFeatureMap;
/*     */ import org.eclipse.emf.ecore.util.EcoreEMap;
/*     */ import org.eclipse.emf.ecore.util.FeatureMap;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLTypeDocumentRootImpl
/*     */   extends EObjectImpl
/*     */   implements XMLTypeDocumentRoot
/*     */ {
/*     */   protected FeatureMap mixed;
/*     */   protected EMap<String, String> xMLNSPrefixMap;
/*     */   protected EMap<String, String> xSISchemaLocation;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 108 */     return XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FeatureMap getMixed() {
/* 118 */     if (this.mixed == null)
/*     */     {
/* 120 */       this.mixed = (FeatureMap)new BasicFeatureMap((InternalEObject)this, 0);
/*     */     }
/* 122 */     return this.mixed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<String, String> getXMLNSPrefixMap() {
/* 132 */     if (this.xMLNSPrefixMap == null)
/*     */     {
/* 134 */       this.xMLNSPrefixMap = (EMap<String, String>)new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, (InternalEObject)this, 1);
/*     */     }
/* 136 */     return this.xMLNSPrefixMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EMap<String, String> getXSISchemaLocation() {
/* 146 */     if (this.xSISchemaLocation == null)
/*     */     {
/* 148 */       this.xSISchemaLocation = (EMap<String, String>)new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, (InternalEObject)this, 2);
/*     */     }
/* 150 */     return this.xSISchemaLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<String> getText() {
/* 160 */     return getMixed().list((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<ProcessingInstruction> getProcessingInstruction() {
/* 170 */     return getMixed().list((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION);
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
/* 181 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 184 */         return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
/*     */       case 1:
/* 186 */         return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
/*     */       case 2:
/* 188 */         return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
/*     */       case 5:
/* 190 */         return ((InternalEList)getProcessingInstruction()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 192 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
/* 203 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 206 */         if (coreType) return getMixed(); 
/* 207 */         return ((FeatureMap.Internal)getMixed()).getWrapper();
/*     */       case 1:
/* 209 */         if (coreType) return getXMLNSPrefixMap(); 
/* 210 */         return getXMLNSPrefixMap().map();
/*     */       case 2:
/* 212 */         if (coreType) return getXSISchemaLocation(); 
/* 213 */         return getXSISchemaLocation().map();
/*     */       case 3:
/* 215 */         return getCDATA();
/*     */       case 4:
/* 217 */         return getComment();
/*     */       case 5:
/* 219 */         return getProcessingInstruction();
/*     */       case 6:
/* 221 */         return getText();
/*     */     } 
/* 223 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 235 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 238 */         ((FeatureMap.Internal)getMixed()).set(newValue);
/*     */         return;
/*     */       case 1:
/* 241 */         ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
/*     */         return;
/*     */       case 2:
/* 244 */         ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
/*     */         return;
/*     */       case 3:
/* 247 */         getCDATA().clear();
/* 248 */         getCDATA().addAll((Collection)newValue);
/*     */         return;
/*     */       case 4:
/* 251 */         getComment().clear();
/* 252 */         getComment().addAll((Collection)newValue);
/*     */         return;
/*     */       case 5:
/* 255 */         getProcessingInstruction().clear();
/* 256 */         getProcessingInstruction().addAll((Collection)newValue);
/*     */         return;
/*     */       case 6:
/* 259 */         getText().clear();
/* 260 */         getText().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 263 */     eDynamicSet(featureID, newValue);
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
/* 274 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 277 */         getMixed().clear();
/*     */         return;
/*     */       case 1:
/* 280 */         getXMLNSPrefixMap().clear();
/*     */         return;
/*     */       case 2:
/* 283 */         getXSISchemaLocation().clear();
/*     */         return;
/*     */       case 3:
/* 286 */         getCDATA().clear();
/*     */         return;
/*     */       case 4:
/* 289 */         getComment().clear();
/*     */         return;
/*     */       case 5:
/* 292 */         getProcessingInstruction().clear();
/*     */         return;
/*     */       case 6:
/* 295 */         getText().clear();
/*     */         return;
/*     */     } 
/* 298 */     eDynamicUnset(featureID);
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
/* 309 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 312 */         return (this.mixed != null && !this.mixed.isEmpty());
/*     */       case 1:
/* 314 */         return (this.xMLNSPrefixMap != null && !this.xMLNSPrefixMap.isEmpty());
/*     */       case 2:
/* 316 */         return (this.xSISchemaLocation != null && !this.xSISchemaLocation.isEmpty());
/*     */       case 3:
/* 318 */         return !getCDATA().isEmpty();
/*     */       case 4:
/* 320 */         return !getComment().isEmpty();
/*     */       case 5:
/* 322 */         return !getProcessingInstruction().isEmpty();
/*     */       case 6:
/* 324 */         return !getText().isEmpty();
/*     */     } 
/* 326 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<String> getCDATA() {
/* 336 */     return getMixed().list((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<String> getComment() {
/* 346 */     return getMixed().list((EStructuralFeature)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT);
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
/* 357 */     if (eIsProxy()) return super.toString();
/*     */     
/* 359 */     StringBuffer result = new StringBuffer(super.toString());
/* 360 */     result.append(" (mixed: ");
/* 361 */     result.append(this.mixed);
/* 362 */     result.append(')');
/* 363 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\impl\XMLTypeDocumentRootImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */