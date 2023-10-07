/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.xmi.XMIResource;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLLoad;
/*     */ import org.eclipse.emf.ecore.xmi.XMLSave;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EMOFResourceImpl
/*     */   extends XMIResourceImpl
/*     */   implements XMIResource
/*     */ {
/*     */   public EMOFResourceImpl() {}
/*     */   
/*     */   public EMOFResourceImpl(URI uri) {
/*  47 */     super(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLHelper createXMLHelper() {
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLLoad createXMLLoad() {
/*  59 */     return new EMOFLoadImpl(new EMOFHelperImpl(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLSave createXMLSave() {
/*  65 */     return new EMOFSaveImpl(new EMOFHelperImpl(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getID(EObject eObject) {
/*  71 */     String id = super.getID(eObject);
/*  72 */     if (id == null) {
/*     */       
/*  74 */       EClass eClass = eObject.eClass();
/*  75 */       if ((eClass != EcorePackage.Literals.EANNOTATION || eObject.eContainer() == null) && 
/*  76 */         eClass != EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY) {
/*     */         
/*  78 */         id = makeID(eObject);
/*  79 */         getEObjectToIDMap().put(eObject, id);
/*     */       } 
/*     */     } 
/*  82 */     return id;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String makeID(EObject eObject) {
/*  87 */     List<String> uriFragmentPath = new ArrayList<String>();
/*  88 */     for (EObject container = eObject.eContainer(); container != null; container = eObject.eContainer()) {
/*     */       
/*  90 */       uriFragmentPath.add(((InternalEObject)container).eURIFragmentSegment((EStructuralFeature)eObject.eContainmentFeature(), eObject));
/*  91 */       eObject = container;
/*     */     } 
/*     */     
/*  94 */     StringBuffer result = new StringBuffer((eObject instanceof ENamedElement) ? (
/*  95 */         (ENamedElement)eObject).getName() : (
/*  96 */         "_" + Integer.toString(getContents().indexOf(eObject))));
/*  97 */     for (ListIterator<String> i = uriFragmentPath.listIterator(uriFragmentPath.size()); i.hasPrevious(); ) {
/*     */       
/*  99 */       result.append('.');
/* 100 */       result.append(i.previous());
/*     */     } 
/*     */     
/* 103 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFResourceImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */