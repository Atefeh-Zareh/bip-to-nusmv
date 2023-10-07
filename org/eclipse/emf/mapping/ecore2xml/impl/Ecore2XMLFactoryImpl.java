/*     */ package org.eclipse.emf.mapping.ecore2xml.impl;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLFactory;
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
/*     */ public class Ecore2XMLFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements Ecore2XMLFactory
/*     */ {
/*     */   public static Ecore2XMLFactory init() {
/*     */     try {
/*  52 */       Ecore2XMLFactory theEcore2XMLFactory = (Ecore2XMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2005/Ecore2XML");
/*  53 */       if (theEcore2XMLFactory != null)
/*     */       {
/*  55 */         return theEcore2XMLFactory;
/*     */       }
/*     */     }
/*  58 */     catch (Exception exception) {
/*     */       
/*  60 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  62 */     return new Ecore2XMLFactoryImpl();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/*  84 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  86 */         return (EObject)createXMLInfo();
/*  87 */       case 1: return (EObject)createXMLMap();
/*  88 */       case 2: return (EObject)createENamedElementToXMLInfoMapEntry();
/*     */     } 
/*  90 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLInfo createXMLInfo() {
/* 101 */     XMLInfoImpl xmlInfo = new XMLInfoImpl();
/* 102 */     return xmlInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLMap createXMLMap() {
/* 112 */     XMLMapImpl xmlMap = new XMLMapImpl();
/* 113 */     return xmlMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map.Entry<ENamedElement, XMLInfo> createENamedElementToXMLInfoMapEntry() {
/* 123 */     ENamedElementToXMLInfoMapEntryImpl eNamedElementToXMLInfoMapEntry = new ENamedElementToXMLInfoMapEntryImpl();
/* 124 */     return (Map.Entry<ENamedElement, XMLInfo>)eNamedElementToXMLInfoMapEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Ecore2XMLPackage getEcore2XMLPackage() {
/* 134 */     return (Ecore2XMLPackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Ecore2XMLPackage getPackage() {
/* 146 */     return Ecore2XMLPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xml\impl\Ecore2XMLFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */