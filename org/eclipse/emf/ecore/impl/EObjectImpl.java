/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EObjectImpl
/*     */   extends BasicEObjectImpl
/*     */   implements EObject
/*     */ {
/*     */   protected static final int EDELIVER = 1;
/*     */   protected static final int EDYNAMIC_CLASS = 2;
/*     */   protected static final int EPROXY = 4;
/*     */   protected static final int ELAST_NOTIFIER_FLAG = 4;
/*     */   public static final int ELAST_EOBJECT_FLAG = 4;
/*  75 */   protected int eFlags = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEList<Adapter> eAdapters;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InternalEObject eContainer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int eContainerFeatureID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEObjectImpl.EPropertiesHolder eProperties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 117 */     return EcorePackage.eINSTANCE.getEObject();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 123 */     switch (operationID) {
/*     */       
/*     */       case 0:
/* 126 */         return eClass();
/*     */       case 1:
/* 128 */         return Boolean.valueOf(eIsProxy());
/*     */       case 2:
/* 130 */         return eResource();
/*     */       case 3:
/* 132 */         return eContainer();
/*     */       case 4:
/* 134 */         return eContainingFeature();
/*     */       case 5:
/* 136 */         return eContainmentFeature();
/*     */       case 6:
/* 138 */         return eContents();
/*     */       case 7:
/* 140 */         return eAllContents();
/*     */       case 8:
/* 142 */         return eCrossReferences();
/*     */       case 9:
/* 144 */         return eGet((EStructuralFeature)arguments.get(0));
/*     */       case 10:
/* 146 */         return eGet((EStructuralFeature)arguments.get(0), ((Boolean)arguments.get(1)).booleanValue());
/*     */       case 11:
/* 148 */         eSet((EStructuralFeature)arguments.get(0), arguments.get(1));
/* 149 */         return null;
/*     */       case 12:
/* 151 */         return Boolean.valueOf(eIsSet((EStructuralFeature)arguments.get(0)));
/*     */       case 13:
/* 153 */         eUnset((EStructuralFeature)arguments.get(0));
/* 154 */         return null;
/*     */       case 14:
/* 156 */         return eInvoke((EOperation)arguments.get(0), (EList)arguments.get(1));
/*     */     } 
/* 158 */     return eDynamicInvoke(operationID, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Adapter> eAdapters() {
/* 167 */     if (this.eAdapters == null)
/*     */     {
/* 169 */       this.eAdapters = (BasicEList<Adapter>)new BasicNotifierImpl.EAdapterList((Notifier)this);
/*     */     }
/* 171 */     return (EList<Adapter>)this.eAdapters;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEList<Adapter> eBasicAdapters() {
/* 177 */     return this.eAdapters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eDeliver() {
/* 186 */     return ((this.eFlags & 0x1) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetDeliver(boolean deliver) {
/* 195 */     if (deliver) {
/*     */       
/* 197 */       this.eFlags |= 0x1;
/*     */     }
/*     */     else {
/*     */       
/* 201 */       this.eFlags &= 0xFFFFFFFE;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsProxy() {
/* 211 */     return ((this.eFlags & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetProxyURI(URI uri) {
/* 220 */     super.eSetProxyURI(uri);
/* 221 */     if (uri != null) {
/*     */       
/* 223 */       this.eFlags |= 0x4;
/*     */     }
/*     */     else {
/*     */       
/* 227 */       this.eFlags &= 0xFFFFFFFB;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEObjectImpl.EPropertiesHolder eProperties() {
/* 234 */     if (this.eProperties == null)
/*     */     {
/* 236 */       this.eProperties = new BasicEObjectImpl.EPropertiesHolderImpl();
/*     */     }
/* 238 */     return this.eProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEObjectImpl.EPropertiesHolder eBasicProperties() {
/* 244 */     return this.eProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalEObject eInternalContainer() {
/* 250 */     return this.eContainer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int eContainerFeatureID() {
/* 256 */     return this.eContainerFeatureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
/* 262 */     this.eContainer = newContainer;
/* 263 */     this.eContainerFeatureID = newContainerFeatureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EClass eClass() {
/* 269 */     return ((this.eFlags & 0x2) == 0) ? eStaticClass() : eProperties().getEClass();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetClass(EClass eClass) {
/* 275 */     super.eSetClass(eClass);
/* 276 */     if (eClass != null) {
/*     */       
/* 278 */       this.eFlags |= 0x2;
/*     */     }
/*     */     else {
/*     */       
/* 282 */       this.eFlags &= 0xFFFFFFFD;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */