/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.util.TreeIterator;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EOperation;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EParameter;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.ETypedElement;
/*     */ import org.eclipse.emf.ecore.EcoreFactory;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.util.ECrossReferenceEList;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EMOFHelperImpl
/*     */   extends XMLHelperImpl
/*     */   implements EMOFHandler.Helper
/*     */ {
/*  51 */   protected EClass propertyClass = null;
/*  52 */   protected List<EObject> propertyFeatureList = null;
/*  53 */   protected Set<EObject> objectsWithGenericTypeList = new HashSet<EObject>();
/*     */ 
/*     */   
/*     */   public EMOFHelperImpl(XMLResource resource) {
/*  57 */     super(resource);
/*  58 */     this.packages.put(EcorePackage.eINSTANCE, "emof");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(EObject object, EStructuralFeature feature) {
/*  64 */     if (feature == EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE)
/*     */     {
/*  66 */       return ((EStructuralFeature)object).isChangeable() ? Boolean.FALSE : Boolean.TRUE;
/*     */     }
/*     */ 
/*     */     
/*  70 */     return super.getValue(object, feature);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(EObject object, EStructuralFeature feature, Object value, int position) {
/*  77 */     if (feature == EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE) {
/*     */       
/*  79 */       ((EStructuralFeature)object).setChangeable(Boolean.FALSE.toString().equals(value));
/*     */     }
/*  81 */     else if (feature == EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND && "*".equals(value)) {
/*     */       
/*  83 */       ((ETypedElement)object).setUpperBound(-1);
/*     */ 
/*     */     
/*     */     }
/*  87 */     else if (feature == EcorePackage.Literals.ETYPED_ELEMENT__ETYPE) {
/*     */       
/*  89 */       if (((ETypedElement)object).getEType() == null)
/*     */       {
/*  91 */         super.setValue(object, feature, value, position);
/*     */       }
/*     */     }
/*  94 */     else if (feature == EcorePackage.Literals.ECLASS__ESUPER_TYPES || feature == EcorePackage.Literals.EOPERATION__EEXCEPTIONS) {
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (!this.objectsWithGenericTypeList.contains(object))
/*     */       {
/* 100 */         super.setValue(object, feature, value, position);
/*     */       }
/*     */     }
/* 103 */     else if (feature == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES || feature == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS) {
/*     */ 
/*     */ 
/*     */       
/* 107 */       if (this.objectsWithGenericTypeList.add(object))
/*     */       {
/* 109 */         ((InternalEList)object.eGet(feature)).clear();
/*     */       }
/* 111 */       super.setValue(object, feature, value, position);
/*     */     }
/*     */     else {
/*     */       
/* 115 */       super.setValue(object, feature, value, position);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHREF(EObject obj) {
/* 122 */     String href = super.getHREF(obj);
/* 123 */     if (href != null && href.startsWith("http://www.eclipse.org/emf/2002/Ecore#//")) {
/*     */       
/* 125 */       String dataType = href.substring("http://www.eclipse.org/emf/2002/Ecore#//".length());
/* 126 */       for (int i = 0; i < EMOFExtendedMetaData.MAPPED_ECORE_EDATATYPES.length; i++) {
/*     */         
/* 128 */         if (dataType.equals(EMOFExtendedMetaData.MAPPED_ECORE_EDATATYPES[i]))
/*     */         {
/* 130 */           return "http://schema.omg.org/spec/MOF/2.0/emof.xml#" + EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPES[i];
/*     */         }
/*     */       } 
/* 133 */       return "http://www.eclipse.org/emf/2002/Ecore.emof#ecore." + dataType;
/*     */     } 
/*     */     
/* 136 */     return href;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name, boolean isElement) {
/* 142 */     if (eClass == EcorePackage.Literals.EANNOTATION && "element".equals(name))
/*     */     {
/* 144 */       return (EStructuralFeature)EcorePackage.Literals.EANNOTATION__REFERENCES;
/*     */     }
/* 146 */     if (EcorePackage.Literals.EMODEL_ELEMENT.isSuperTypeOf(eClass) && "ownedComment".equals(name))
/*     */     {
/* 148 */       return (EStructuralFeature)EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS;
/*     */     }
/*     */     
/* 151 */     for (int i = 0, size = eClass.getFeatureCount(); i < size; i++) {
/*     */       
/* 153 */       EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
/* 154 */       if (name.equals(this.extendedMetaData.getName(eStructuralFeature)) && (
/* 155 */         (namespaceURI == null) ? (
/* 156 */         this.extendedMetaData.getNamespace(eStructuralFeature) == null) : 
/* 157 */         namespaceURI.equals(this.extendedMetaData.getNamespace(eStructuralFeature)))) {
/*     */         
/* 159 */         computeFeatureKind(eStructuralFeature);
/* 160 */         return eStructuralFeature;
/*     */       } 
/*     */     } 
/*     */     
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EClassifier getType(EFactory eFactory, String typeName) {
/* 170 */     EPackage ePackage = eFactory.getEPackage();
/* 171 */     if (ePackage == EcorePackage.eINSTANCE) {
/*     */       
/* 173 */       if (EcorePackage.Literals.ESTRUCTURAL_FEATURE.getName().equals(typeName) || "Property".equals(typeName)) {
/*     */         
/* 175 */         if (this.propertyClass == null) {
/*     */           
/* 177 */           this.propertyClass = EcoreFactory.eINSTANCE.createEClass();
/* 178 */           this.propertyClass.getESuperTypes().add(EcorePackage.Literals.EREFERENCE);
/* 179 */           this.propertyClass.getESuperTypes().add(EcorePackage.Literals.EATTRIBUTE);
/* 180 */           this.propertyClass.setName("EMOFProperty");
/*     */           
/* 182 */           EPackage propertyPackage = EcoreFactory.eINSTANCE.createEPackage();
/* 183 */           propertyPackage.getEClassifiers().add(this.propertyClass);
/*     */           
/* 185 */           this.propertyFeatureList = new ArrayList<EObject>();
/*     */         } 
/* 187 */         return (EClassifier)this.propertyClass;
/*     */       } 
/* 189 */       if ("Tag".equals(typeName))
/*     */       {
/* 191 */         return (EClassifier)EcorePackage.Literals.EANNOTATION;
/*     */       }
/*     */     } 
/* 194 */     return super.getType(eFactory, typeName);
/*     */   }
/*     */   
/* 197 */   private static final Integer ONE = Integer.valueOf(1);
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject createObject(EFactory eFactory, EClassifier type) {
/* 202 */     if (type == this.propertyClass && this.propertyClass != null) {
/*     */       
/* 204 */       EObject property = this.propertyClass.getEPackage().getEFactoryInstance().create(this.propertyClass);
/* 205 */       property.eSet((EStructuralFeature)EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND, ONE);
/* 206 */       property.eSet((EStructuralFeature)EcorePackage.Literals.ETYPED_ELEMENT__ORDERED, Boolean.FALSE);
/* 207 */       this.propertyFeatureList.add(property);
/* 208 */       return property;
/*     */     } 
/* 210 */     if (EcorePackage.Literals.EANNOTATION == type) {
/*     */       
/* 212 */       EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
/* 213 */       annotation.setSource("http://schema.omg.org/spec/MOF/2.0/emof.xml");
/* 214 */       return (EObject)annotation;
/*     */     } 
/* 216 */     if (EcorePackage.Literals.EOPERATION == type) {
/*     */       
/* 218 */       EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
/* 219 */       eOperation.setLowerBound(1);
/* 220 */       eOperation.setOrdered(false);
/* 221 */       return (EObject)eOperation;
/*     */     } 
/* 223 */     if (EcorePackage.Literals.EPARAMETER == type) {
/*     */       
/* 225 */       EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
/* 226 */       eParameter.setLowerBound(1);
/* 227 */       eParameter.setOrdered(false);
/* 228 */       return (EObject)eParameter;
/*     */     } 
/* 230 */     return super.createObject(eFactory, type);
/*     */   }
/*     */ 
/*     */   
/*     */   public void convertPropertyFeatures() {
/* 235 */     if (this.propertyFeatureList != null) {
/*     */       
/* 237 */       EcoreUtil.Copier copier = 
/* 238 */         new EcoreUtil.Copier(false)
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           protected EObject createCopy(EObject eObject) {
/* 245 */             EClass eClass = 
/* 246 */               (((EStructuralFeature)eObject).getEType() instanceof org.eclipse.emf.ecore.EDataType) ? 
/* 247 */               EcorePackage.Literals.EATTRIBUTE : 
/* 248 */               EcorePackage.Literals.EREFERENCE;
/* 249 */             return EcoreUtil.create(eClass);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
/* 257 */             if (eReference == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE) {
/*     */               
/* 259 */               ((ETypedElement)copyEObject).setEGenericType(((ETypedElement)eObject).getEGenericType());
/*     */             }
/*     */             else {
/*     */               
/* 263 */               ((EStructuralFeature)copyEObject).getEAnnotations().addAll((Collection)((EStructuralFeature)eObject).getEAnnotations());
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject) {
/* 269 */             if (copyEObject.eClass().getEAllStructuralFeatures().contains(eAttribute))
/*     */             {
/* 271 */               super.copyAttribute(eAttribute, eObject, copyEObject);
/*     */             }
/*     */           }
/*     */         };
/* 275 */       copier.copyAll(this.propertyFeatureList);
/* 276 */       copier.copyReferences();
/*     */       
/* 278 */       for (Map.Entry<EObject, EObject> entry : (Iterable<Map.Entry<EObject, EObject>>)copier.entrySet()) {
/*     */         
/* 280 */         EStructuralFeature emofFeature = (EStructuralFeature)entry.getKey();
/* 281 */         EStructuralFeature ecoreFeature = (EStructuralFeature)entry.getValue();
/* 282 */         this.resource.setID((EObject)ecoreFeature, this.resource.getID((EObject)emofFeature));
/* 283 */         EObject eObject = emofFeature.eContainer();
/*     */         
/* 285 */         List<EObject> list = (List<EObject>)eObject.eGet((EStructuralFeature)emofFeature.eContainmentFeature());
/* 286 */         list.set(list.indexOf(emofFeature), ecoreFeature);
/*     */       } 
/*     */       
/* 289 */       for (TreeIterator<Notifier> contents = EcoreUtil.getAllContents((Collection)this.resource.getContents(), false); contents.hasNext(); ) {
/*     */         
/* 291 */         EObject eObject = (EObject)contents.next();
/* 292 */         ECrossReferenceEList.FeatureIteratorImpl<EObject> featureIteratorImpl = 
/* 293 */           new ECrossReferenceEList.FeatureIteratorImpl<EObject>(eObject)
/*     */           {
/*     */             
/*     */             protected boolean isIncluded(EStructuralFeature eStructuralFeature)
/*     */             {
/* 298 */               return (!eStructuralFeature.isDerived() && super.isIncluded(eStructuralFeature));
/*     */             }
/*     */           };
/* 301 */         while (featureIteratorImpl.hasNext()) {
/*     */           
/* 303 */           EObject targetEObject = (EObject)featureIteratorImpl.next();
/* 304 */           EObject copyEObject = (EObject)copier.get(targetEObject);
/* 305 */           if (copyEObject != null) {
/*     */             
/* 307 */             EReference eReference = (EReference)featureIteratorImpl.feature();
/* 308 */             if (eReference.isMany()) {
/*     */               
/* 310 */               List<EObject> list = (List<EObject>)eObject.eGet((EStructuralFeature)eReference);
/* 311 */               list.set(list.indexOf(targetEObject), copyEObject);
/*     */               
/*     */               continue;
/*     */             } 
/* 315 */             eObject.eSet((EStructuralFeature)eReference, copyEObject);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFHelperImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */