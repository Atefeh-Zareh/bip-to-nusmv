/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.eclipse.emf.common.util.BasicEMap;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DynamicEObjectImpl
/*     */   extends EObjectImpl
/*     */   implements EStructuralFeature.Internal.DynamicValueHolder
/*     */ {
/*     */   protected EClass eClass;
/*     */   protected Object[] eSettings;
/*     */   
/*     */   public static final class BasicEMapEntry<K, V>
/*     */     extends DynamicEObjectImpl
/*     */     implements BasicEMap.Entry<K, V>
/*     */   {
/*  39 */     protected int hash = -1;
/*     */ 
/*     */ 
/*     */     
/*     */     protected EStructuralFeature keyFeature;
/*     */ 
/*     */ 
/*     */     
/*     */     protected EStructuralFeature valueFeature;
/*     */ 
/*     */ 
/*     */     
/*     */     public BasicEMapEntry() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public BasicEMapEntry(EClass eClass) {
/*  56 */       super(eClass);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public K getKey() {
/*  62 */       return (K)eGet(this.keyFeature);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setKey(Object key) {
/*  67 */       eSet(this.keyFeature, key);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHash() {
/*  72 */       if (this.hash == -1) {
/*     */         
/*  74 */         Object theKey = getKey();
/*  75 */         this.hash = (theKey == null) ? 0 : theKey.hashCode();
/*     */       } 
/*  77 */       return this.hash;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setHash(int hash) {
/*  82 */       this.hash = hash;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public V getValue() {
/*  88 */       return (V)eGet(this.valueFeature);
/*     */     }
/*     */ 
/*     */     
/*     */     public V setValue(V value) {
/*  93 */       V result = (V)eGet(this.valueFeature);
/*  94 */       eSet(this.valueFeature, value);
/*  95 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void eSetClass(EClass eClass) {
/* 101 */       super.eSetClass(eClass);
/* 102 */       this.keyFeature = eClass.getEStructuralFeature("key");
/* 103 */       this.valueFeature = eClass.getEStructuralFeature("value");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class DynamicEPropertiesHolderImpl
/*     */     implements BasicEObjectImpl.EPropertiesHolder
/*     */   {
/*     */     protected URI eProxyURI;
/*     */     
/*     */     protected Resource.Internal eResource;
/*     */     
/*     */     protected EList<EObject> eContents;
/*     */     protected EList<EObject> eCrossReferences;
/*     */     
/*     */     public EClass getEClass() {
/* 119 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setEClass(EClass eClass) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public URI getEProxyURI() {
/* 129 */       return this.eProxyURI;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setEProxyURI(URI eProxyURI) {
/* 134 */       this.eProxyURI = eProxyURI;
/*     */     }
/*     */ 
/*     */     
/*     */     public Resource.Internal getEResource() {
/* 139 */       return this.eResource;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setEResource(Resource.Internal eResource) {
/* 144 */       this.eResource = eResource;
/*     */     }
/*     */ 
/*     */     
/*     */     public EList<EObject> getEContents() {
/* 149 */       return this.eContents;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setEContents(EList<EObject> eContents) {
/* 154 */       this.eContents = eContents;
/*     */     }
/*     */ 
/*     */     
/*     */     public EList<EObject> getECrossReferences() {
/* 159 */       return this.eCrossReferences;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setECrossReferences(EList<EObject> eCrossReferences) {
/* 164 */       this.eCrossReferences = eCrossReferences;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasSettings() {
/* 169 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void allocateSettings(int maximumDynamicFeatureID) {
/* 174 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object dynamicGet(int dynamicFeatureID) {
/* 179 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void dynamicSet(int dynamicFeatureID, Object value) {
/* 184 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void dynamicUnset(int dynamicFeatureID) {
/* 189 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   protected static final Object[] ENO_SETTINGS = new Object[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicEObjectImpl() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicEObjectImpl(EClass eClass) {
/* 212 */     eSetClass(eClass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int eStaticFeatureCount() {
/* 218 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature) {
/* 224 */     return eClass().getFeatureID(eStructuralFeature);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEObjectImpl.EPropertiesHolder eProperties() {
/* 230 */     if (this.eProperties == null)
/*     */     {
/* 232 */       this.eProperties = new DynamicEPropertiesHolderImpl();
/*     */     }
/* 234 */     return this.eProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean eHasSettings() {
/* 240 */     return (this.eSettings != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EStructuralFeature.Internal.DynamicValueHolder eSettings() {
/* 246 */     if (this.eSettings == null) {
/*     */       
/* 248 */       int size = eClass().getFeatureCount() - eStaticFeatureCount();
/* 249 */       this.eSettings = (size == 0) ? ENO_SETTINGS : new Object[size];
/*     */     } 
/*     */     
/* 252 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int eStaticOperationCount() {
/* 258 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedOperationID(EOperation eOperation) {
/* 264 */     EClass eClass = eClass();
/* 265 */     EOperation override = eClass.getOverride(eOperation);
/* 266 */     return eClass.getOperationID((override != null) ? override : eOperation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 272 */     EOperation eOperation = eClass().getEOperation(operationID);
/* 273 */     assert eOperation != null : "Invalid operationID: " + operationID;
/*     */     
/* 275 */     return eInvocationDelegate(eOperation).dynamicInvoke(this, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eDynamicClass() {
/* 281 */     return this.eClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EClass eClass() {
/* 287 */     return this.eClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetClass(EClass eClass) {
/* 293 */     this.eClass = eClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object dynamicGet(int dynamicFeatureID) {
/* 298 */     return this.eSettings[dynamicFeatureID];
/*     */   }
/*     */ 
/*     */   
/*     */   public void dynamicSet(int dynamicFeatureID, Object value) {
/* 303 */     this.eSettings[dynamicFeatureID] = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dynamicUnset(int dynamicFeatureID) {
/* 308 */     this.eSettings[dynamicFeatureID] = null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\DynamicEObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */