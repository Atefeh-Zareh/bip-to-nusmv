/*     */ package org.eclipse.emf.mapping.ecore2xml.util;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ecore2XMLAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static Ecore2XMLPackage modelPackage;
/*     */   protected Ecore2XMLSwitch<Adapter> modelSwitch;
/*     */   
/*     */   public Ecore2XMLAdapterFactory() {
/*  91 */     this
/*  92 */       .modelSwitch = new Ecore2XMLSwitch<Adapter>()
/*     */       {
/*     */         
/*     */         public Adapter caseXMLInfo(XMLInfo object)
/*     */         {
/*  97 */           return Ecore2XMLAdapterFactory.this.createXMLInfoAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseXMLMap(XMLMap object) {
/* 102 */           return Ecore2XMLAdapterFactory.this.createXMLMapAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter caseENamedElementToXMLInfoMapEntry(Map.Entry<ENamedElement, XMLInfo> object) {
/* 107 */           return Ecore2XMLAdapterFactory.this.createENamedElementToXMLInfoMapEntryAdapter();
/*     */         }
/*     */ 
/*     */         
/*     */         public Adapter defaultCase(EObject object) {
/* 112 */           return Ecore2XMLAdapterFactory.this.createEObjectAdapter();
/*     */         }
/*     */       };
/*     */     if (modelPackage == null) {
/*     */       modelPackage = Ecore2XMLPackage.eINSTANCE;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Adapter createAdapter(Notifier target) {
/* 127 */     return this.modelSwitch.doSwitch((EObject)target);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFactoryForType(Object object) {
/*     */     if (object == modelPackage) {
/*     */       return true;
/*     */     }
/*     */     if (object instanceof EObject) {
/*     */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*     */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Adapter createXMLInfoAdapter() {
/* 143 */     return null;
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
/*     */   public Adapter createXMLMapAdapter() {
/* 158 */     return null;
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
/*     */   public Adapter createENamedElementToXMLInfoMapEntryAdapter() {
/* 173 */     return null;
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
/*     */   public Adapter createEObjectAdapter() {
/* 186 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\mapping\ecore2xm\\util\Ecore2XMLAdapterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */