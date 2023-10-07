/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcoreFactory;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xmi.XMLHelper;
/*     */ import org.eclipse.emf.ecore.xmi.XMLResource;
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
/*     */ public class EMOFSaveImpl
/*     */   extends XMISaveImpl
/*     */ {
/*     */   public EMOFSaveImpl(XMLHelper helper) {
/*  43 */     super(helper);
/*  44 */     this.idAttributeName = "xmi:id";
/*     */   }
/*     */   
/*  47 */   private static final Integer ONE = Integer.valueOf(1);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(XMLResource resource, Map<?, ?> options) {
/*  52 */     super.init(resource, options);
/*  53 */     this.xmlTypeInfo = new EMOFXMLTypeInfoImpl(this.xmlTypeInfo);
/*  54 */     this.saveTypeInfo = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
/*  60 */     if (f == EcorePackage.Literals.ETYPED_ELEMENT__ETYPE)
/*     */     {
/*  62 */       return (o.eGet(f) != null);
/*     */     }
/*  64 */     if (f == EcorePackage.Literals.EOPERATION__EEXCEPTIONS || 
/*  65 */       f == EcorePackage.Literals.ECLASS__ESUPER_TYPES)
/*     */     {
/*  67 */       return !((EList)o.eGet(f)).isEmpty();
/*     */     }
/*  69 */     if (f == EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND)
/*     */     {
/*  71 */       return !(!this.keepDefaults && ONE.equals(o.eGet(f)));
/*     */     }
/*  73 */     if (f == EcorePackage.Literals.ETYPED_ELEMENT__ORDERED)
/*     */     {
/*  75 */       return !(!this.keepDefaults && Boolean.FALSE.equals(o.eGet(f)));
/*     */     }
/*     */ 
/*     */     
/*  79 */     return super.shouldSaveFeature(o, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveDataTypeElementSingle(EObject o, EStructuralFeature f) {
/*  86 */     if (f == EcorePackage.Literals.EPACKAGE__NS_PREFIX || 
/*  87 */       f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE || 
/*  88 */       f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT || 
/*  89 */       f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE || 
/*  90 */       f == EcorePackage.Literals.EENUM_LITERAL__VALUE || 
/*  91 */       f == EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES || 
/*  92 */       f == EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME || 
/*  93 */       f == EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME || 
/*  94 */       f == EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE || 
/*  95 */       f == EcorePackage.Literals.ECLASS__INTERFACE) {
/*     */       
/*  97 */       if (f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE && 
/*  98 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE))
/*  99 */         return;  if (f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT && (
/* 100 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE) || 
/* 101 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE)))
/* 102 */         return;  if (f == EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES && (
/* 103 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT) || 
/* 104 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE) || 
/* 105 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE)))
/* 106 */         return;  if (f == EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE && (
/* 107 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME) || o.eIsSet((EStructuralFeature)EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME)))
/* 108 */         return;  if (f == EcorePackage.Literals.ECLASS__INTERFACE && (
/* 109 */         o.eIsSet((EStructuralFeature)EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME) || o.eIsSet((EStructuralFeature)EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME)))
/* 110 */         return;  this.doc.startElement("xmi:Extension");
/* 111 */       this.doc.addAttribute("extender", "http://www.eclipse.org/emf/2002/Ecore");
/* 112 */       saveExtensionFeature(o, f);
/* 113 */       if (f == EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME || f == EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME) {
/*     */         
/* 115 */         if (o instanceof EDataType)
/*     */         {
/* 117 */           if (o.eIsSet((EStructuralFeature)EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE))
/*     */           {
/* 119 */             saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE);
/*     */           }
/*     */         }
/* 122 */         else if (o instanceof EClass)
/*     */         {
/* 124 */           if (o.eIsSet((EStructuralFeature)EcorePackage.Literals.ECLASS__INTERFACE))
/*     */           {
/* 126 */             saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.ECLASS__INTERFACE);
/*     */           }
/*     */         }
/*     */       
/* 130 */       } else if (f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT) {
/*     */         
/* 132 */         if (o instanceof org.eclipse.emf.ecore.EReference && o.eIsSet((EStructuralFeature)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES))
/*     */         {
/* 134 */           saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES);
/*     */         }
/*     */       }
/* 137 */       else if (f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE) {
/*     */         
/* 139 */         if (o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT))
/*     */         {
/* 141 */           saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT);
/*     */         }
/* 143 */         if (o instanceof org.eclipse.emf.ecore.EReference && o.eIsSet((EStructuralFeature)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES))
/*     */         {
/* 145 */           saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES);
/*     */         }
/*     */       }
/* 148 */       else if (f == EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE) {
/*     */         
/* 150 */         if (o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE))
/*     */         {
/* 152 */           saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE);
/*     */         }
/* 154 */         if (o.eIsSet((EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT))
/*     */         {
/* 156 */           saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT);
/*     */         }
/* 158 */         if (o instanceof org.eclipse.emf.ecore.EReference && o.eIsSet((EStructuralFeature)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES))
/*     */         {
/* 160 */           saveExtensionFeature(o, (EStructuralFeature)EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES);
/*     */         }
/*     */       } 
/* 163 */       this.doc.endElement();
/*     */     }
/*     */     else {
/*     */       
/* 167 */       super.saveDataTypeElementSingle(o, f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDatatypeValue(Object value, EStructuralFeature f, boolean isAttribute) {
/* 174 */     String result = super.getDatatypeValue(value, f, isAttribute);
/* 175 */     if (f == EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND && "-1".equals(result))
/*     */     {
/* 177 */       result = "*";
/*     */     }
/* 179 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveExtensionFeature(EObject o, EStructuralFeature f) {
/* 184 */     this.doc.startElement(f.getName());
/* 185 */     EDataType eDataType = (EDataType)f.getEType();
/* 186 */     this.doc.endContentElement(EcoreFactory.eINSTANCE.convertToString(eDataType, o.eGet(f)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveContainedSingle(EObject o, EStructuralFeature f) {
/* 192 */     if (f == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE) {
/*     */       
/* 194 */       this.doc.startElement("xmi:Extension");
/* 195 */       this.doc.addAttribute("extender", "http://www.eclipse.org/emf/2002/Ecore");
/* 196 */       super.saveContainedSingle(o, f);
/* 197 */       this.doc.endElement();
/*     */     }
/*     */     else {
/*     */       
/* 201 */       super.saveContainedSingle(o, f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveContainedMany(EObject o, EStructuralFeature f) {
/* 211 */     if (f == EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS) {
/*     */ 
/*     */       
/* 214 */       List<EAnnotation> eAnnotations = ((InternalEList)this.helper.getValue(o, f)).basicList();
/* 215 */       boolean saveExtension = false;
/* 216 */       int size = eAnnotations.size(); int i;
/* 217 */       for (i = 0; i < size; i++) {
/*     */         
/* 219 */         EAnnotation eAnnotation = eAnnotations.get(i);
/* 220 */         String source = eAnnotation.getSource();
/* 221 */         if ("http://schema.omg.org/spec/MOF/2.0/emof.xml#Comment".equals(source)) {
/*     */           
/* 223 */           this.doc.startElement("ownedComment");
/* 224 */           this.doc.addAttribute("body", (String)eAnnotation.getDetails().get("body"));
/* 225 */           this.doc.endElement();
/*     */         }
/* 227 */         else if (source.startsWith("http://schema.omg.org/spec/MOF/2.0/emof.xml")) {
/*     */           
/* 229 */           this.doc.startElement("ownedComment");
/* 230 */           this.doc.addAttribute("body", (String)eAnnotation.getDetails().get("body"));
/* 231 */           this.doc.startElement("ownedComment");
/* 232 */           this.doc.addAttribute("body", source);
/* 233 */           this.doc.endElement();
/* 234 */           this.doc.endElement();
/*     */         }
/*     */         else {
/*     */           
/* 238 */           saveExtension = true;
/*     */         } 
/*     */       } 
/* 241 */       if (saveExtension)
/*     */       {
/* 243 */         this.doc.startElement("xmi:Extension");
/* 244 */         this.doc.addAttribute("extender", "http://www.eclipse.org/emf/2002/Ecore");
/* 245 */         for (i = 0; i < size; i++) {
/*     */           
/* 247 */           EAnnotation eAnnotation = eAnnotations.get(i);
/* 248 */           String source = eAnnotation.getSource();
/* 249 */           if (!source.startsWith("http://schema.omg.org/spec/MOF/2.0/emof.xml"))
/*     */           {
/* 251 */             saveElement((InternalEObject)eAnnotation, f);
/*     */           }
/*     */         } 
/* 254 */         this.doc.endElement();
/*     */       }
/*     */     
/* 257 */     } else if (f == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES || 
/* 258 */       f == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS || 
/* 259 */       f == EcorePackage.Literals.ECLASSIFIER__ETYPE_PARAMETERS || 
/* 260 */       f == EcorePackage.Literals.EOPERATION__ETYPE_PARAMETERS) {
/*     */       
/* 262 */       this.doc.startElement("xmi:Extension");
/* 263 */       this.doc.addAttribute("extender", "http://www.eclipse.org/emf/2002/Ecore");
/* 264 */       super.saveContainedMany(o, f);
/* 265 */       this.doc.endElement();
/*     */     }
/*     */     else {
/*     */       
/* 269 */       super.saveContainedMany(o, f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object writeTopObjects(List<? extends EObject> contents) {
/* 276 */     this.doc.startElement("xmi:XMI");
/* 277 */     Object mark = this.doc.mark();
/*     */     
/* 279 */     for (int i = 0, size = contents.size(); i < size; i++) {
/*     */       
/* 281 */       EObject top = contents.get(i);
/* 282 */       EClass eClass = top.eClass();
/* 283 */       if (eClass == EcorePackage.Literals.EANNOTATION) {
/*     */         
/* 285 */         EAnnotation annotation = (EAnnotation)top;
/* 286 */         String source = annotation.getSource();
/* 287 */         if ("http://schema.omg.org/spec/mof/2.0/emof.xmi".equals(source) || "http://schema.omg.org/spec/MOF/2.0/emof.xml".equals(source)) {
/*     */           
/* 289 */           this.doc.startElement("emof:Tag");
/* 290 */           this.doc.addAttribute(this.idAttributeName, this.helper.getID((EObject)annotation));
/* 291 */           this.doc.addAttribute("name", (String)annotation.getDetails().get("name"));
/* 292 */           this.doc.addAttribute("value", (String)annotation.getDetails().get("value"));
/*     */           
/* 294 */           InternalEList<? extends EObject> values = (InternalEList<? extends EObject>)annotation.getReferences();
/* 295 */           if (!values.isEmpty())
/*     */           {
/* 297 */             if (sameDocMany((EObject)annotation, (EStructuralFeature)EcorePackage.Literals.EANNOTATION__REFERENCES) == 2) {
/*     */               
/* 299 */               for (Iterator<? extends EObject> iter = values.basicIterator(); iter.hasNext(); ) {
/*     */                 
/* 301 */                 EObject value = iter.next();
/* 302 */                 String href = this.helper.getHREF(value);
/* 303 */                 if (href != null)
/*     */                 {
/* 305 */                   this.doc.startElement("element");
/* 306 */                   this.doc.addAttribute("href", href);
/* 307 */                   this.doc.endEmptyElement();
/*     */                 }
/*     */               
/*     */               } 
/*     */             } else {
/*     */               
/* 313 */               StringBuffer ids = new StringBuffer();
/* 314 */               boolean failed = true;
/* 315 */               Iterator<? extends EObject> iter = values.basicIterator();
/*     */               while (true) {
/* 317 */                 EObject value = iter.next();
/* 318 */                 String idref = this.helper.getIDREF(value);
/* 319 */                 if (idref == null) {
/*     */                   
/* 321 */                   failed = true;
/* 322 */                   if (!iter.hasNext())
/*     */                     break; 
/*     */                   continue;
/*     */                 } 
/* 326 */                 ids.append(idref);
/* 327 */                 if (!iter.hasNext())
/* 328 */                   break;  ids.append(' ');
/*     */               } 
/*     */               
/* 331 */               String idsString = ids.toString();
/* 332 */               if (!failed || (idsString = idsString.trim()).length() != 0)
/*     */               {
/* 334 */                 this.doc.addAttribute("element", ids.toString());
/*     */               }
/*     */             } 
/*     */           }
/* 338 */           this.doc.endElement();
/*     */         } 
/*     */       } else {
/*     */         
/* 342 */         String name = this.helper.getQName(eClass);
/* 343 */         this.doc.startElement(name);
/* 344 */         saveElementID(top);
/*     */       } 
/*     */     } 
/*     */     
/* 348 */     this.doc.endElement();
/* 349 */     return mark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class EMOFXMLTypeInfoImpl
/*     */     extends XMLSaveImpl.XMLTypeInfoImpl
/*     */   {
/*     */     protected XMLSave.XMLTypeInfo xmlTypeInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EMOFXMLTypeInfoImpl(XMLSave.XMLTypeInfo xmlTypeInfo) {
/* 365 */       this.xmlTypeInfo = xmlTypeInfo;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean shouldSaveType(EClass objectType, EClassifier featureType, EStructuralFeature feature) {
/* 372 */       if (feature != EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES && (
/* 373 */         (this.xmlTypeInfo == null) ? 
/* 374 */         super.shouldSaveType(objectType, featureType, feature) : 
/* 375 */         this.xmlTypeInfo.shouldSaveType(objectType, featureType, feature))) return true;
/*     */       
/*     */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldSaveType(EClass objectType, EClass featureType, EStructuralFeature feature) {
/* 382 */       if (feature != EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES && (
/* 383 */         (this.xmlTypeInfo == null) ? 
/* 384 */         super.shouldSaveType(objectType, featureType, feature) : 
/* 385 */         this.xmlTypeInfo.shouldSaveType(objectType, featureType, feature))) return true; 
/*     */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\EMOFSaveImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */