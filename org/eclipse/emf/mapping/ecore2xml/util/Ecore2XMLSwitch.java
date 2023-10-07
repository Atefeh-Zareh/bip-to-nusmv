/*     */ package org.eclipse.emf.mapping.ecore2xml.util;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
/*     */ import org.eclipse.emf.mapping.ecore2xml.XMLMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ecore2XMLSwitch<T>
/*     */ {
/*     */   protected static Ecore2XMLPackage modelPackage;
/*     */   
/*     */   public Ecore2XMLSwitch() {
/*  58 */     if (modelPackage == null)
/*     */     {
/*  60 */       modelPackage = Ecore2XMLPackage.eINSTANCE;
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
/*     */   
/*     */   public T doSwitch(EObject theEObject) {
/*  73 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*     */   protected T doSwitch(EClass theEClass, EObject theEObject) {
/*  85 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  87 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  91 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  92 */     return 
/*  93 */       eList.isEmpty() ? 
/*  94 */       defaultCase(theEObject) : 
/*  95 */       doSwitch(eList.get(0), theEObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected T doSwitch(int classifierID, EObject theEObject) {
/*     */     XMLInfo xmlInfo;
/*     */     XMLMap xmlMap;
/*     */     Map.Entry<ENamedElement, XMLInfo> eNamedElementToXMLInfoMapEntry;
/*     */     T result;
/* 108 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 112 */         xmlInfo = (XMLInfo)theEObject;
/* 113 */         result = caseXMLInfo(xmlInfo);
/* 114 */         if (result == null) result = defaultCase(theEObject); 
/* 115 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 119 */         xmlMap = (XMLMap)theEObject;
/* 120 */         result = caseXMLMap(xmlMap);
/* 121 */         if (result == null) result = defaultCase(theEObject); 
/* 122 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 126 */         eNamedElementToXMLInfoMapEntry = (Map.Entry<ENamedElement, XMLInfo>)theEObject;
/* 127 */         result = caseENamedElementToXMLInfoMapEntry(eNamedElementToXMLInfoMapEntry);
/* 128 */         if (result == null) result = defaultCase(theEObject); 
/* 129 */         return result;
/*     */     } 
/* 131 */     return defaultCase(theEObject);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T caseXMLInfo(XMLInfo object) {
/* 148 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T caseXMLMap(XMLMap object) {
/* 164 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T caseENamedElementToXMLInfoMapEntry(Map.Entry<ENamedElement, XMLInfo> object) {
/* 180 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T defaultCase(EObject object) {
/* 196 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xm\\util\Ecore2XMLSwitch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */