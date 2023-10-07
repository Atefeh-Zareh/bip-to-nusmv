/*      */ package org.eclipse.emf.ecore.impl;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.eclipse.emf.common.notify.Adapter;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
/*      */ import org.eclipse.emf.common.util.AbstractTreeIterator;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.TreeIterator;
/*      */ import org.eclipse.emf.common.util.URI;
/*      */ import org.eclipse.emf.common.util.WrappedException;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EOperation;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.resource.Resource;
/*      */ import org.eclipse.emf.ecore.util.EContentsEList;
/*      */ import org.eclipse.emf.ecore.util.ECrossReferenceEList;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.FeatureMapUtil;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BasicEObjectImpl
/*      */   extends BasicNotifierImpl
/*      */   implements EObject, InternalEObject
/*      */ {
/*      */   protected static interface EPropertiesHolder
/*      */     extends EStructuralFeature.Internal.DynamicValueHolder
/*      */   {
/*      */     EClass getEClass();
/*      */     
/*      */     void setEClass(EClass param1EClass);
/*      */     
/*      */     URI getEProxyURI();
/*      */     
/*      */     void setEProxyURI(URI param1URI);
/*      */     
/*      */     Resource.Internal getEResource();
/*      */     
/*      */     void setEResource(Resource.Internal param1Internal);
/*      */     
/*      */     EList<EObject> getEContents();
/*      */     
/*      */     void setEContents(EList<EObject> param1EList);
/*      */     
/*      */     EList<EObject> getECrossReferences();
/*      */     
/*      */     void setECrossReferences(EList<EObject> param1EList);
/*      */     
/*      */     boolean hasSettings();
/*      */     
/*      */     void allocateSettings(int param1Int);
/*      */   }
/*      */   
/*      */   protected static class EPropertiesHolderBaseImpl
/*      */     implements EPropertiesHolder
/*      */   {
/*      */     protected EClass eClass;
/*      */     protected Resource.Internal eResource;
/*      */     protected Object[] eSettings;
/*   95 */     protected static final Object[] NO_SETTINGS = new Object[0];
/*      */ 
/*      */     
/*      */     public EClass getEClass() {
/*   99 */       return this.eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEClass(EClass eClass) {
/*  104 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public URI getEProxyURI() {
/*  109 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEProxyURI(URI eProxyURI) {
/*  114 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public Resource.Internal getEResource() {
/*  119 */       return this.eResource;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEResource(Resource.Internal eResource) {
/*  124 */       this.eResource = eResource;
/*      */     }
/*      */ 
/*      */     
/*      */     public EList<EObject> getEContents() {
/*  129 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEContents(EList<EObject> eContents) {
/*  134 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public EList<EObject> getECrossReferences() {
/*  139 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setECrossReferences(EList<EObject> eCrossReferences) {
/*  144 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasSettings() {
/*  149 */       return (this.eSettings != null);
/*      */     }
/*      */ 
/*      */     
/*      */     public void allocateSettings(int dynamicFeatureCount) {
/*  154 */       this.eSettings = (dynamicFeatureCount == 0) ? NO_SETTINGS : new Object[dynamicFeatureCount];
/*      */     }
/*      */ 
/*      */     
/*      */     public Object dynamicGet(int dynamicFeatureID) {
/*  159 */       return this.eSettings[dynamicFeatureID];
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicSet(int dynamicFeatureID, Object value) {
/*  164 */       this.eSettings[dynamicFeatureID] = value;
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicUnset(int dynamicFeatureID) {
/*  169 */       this.eSettings[dynamicFeatureID] = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class EPropertiesHolderImpl
/*      */     extends EPropertiesHolderBaseImpl
/*      */   {
/*      */     protected URI eProxyURI;
/*      */     
/*      */     protected EList<EObject> eContents;
/*      */     
/*      */     protected EList<EObject> eCrossReferences;
/*      */ 
/*      */     
/*      */     public URI getEProxyURI() {
/*  185 */       return this.eProxyURI;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setEProxyURI(URI eProxyURI) {
/*  191 */       this.eProxyURI = eProxyURI;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EList<EObject> getEContents() {
/*  197 */       return this.eContents;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setEContents(EList<EObject> eContents) {
/*  203 */       this.eContents = eContents;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EList<EObject> getECrossReferences() {
/*  209 */       return this.eCrossReferences;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setECrossReferences(EList<EObject> eCrossReferences) {
/*  215 */       this.eCrossReferences = eCrossReferences;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int eStaticFeatureCount() {
/*  229 */     return eStaticClass().getFeatureCount();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int eStaticOperationCount() {
/*  234 */     return eStaticClass().getOperationCount();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EPropertiesHolder eProperties() {
/*  239 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EPropertiesHolder eBasicProperties() {
/*  249 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean eHasSettings() {
/*  255 */     EPropertiesHolder eProperties = eBasicProperties();
/*  256 */     return (eProperties != null && eProperties.hasSettings());
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature.Internal.DynamicValueHolder eSettings() {
/*  261 */     if (!eHasSettings()) {
/*      */       
/*  263 */       int size = eClass().getFeatureCount() - eStaticFeatureCount();
/*  264 */       eProperties().allocateSettings(size);
/*      */     } 
/*      */     
/*  267 */     return eBasicProperties();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int eDynamicFeatureID(EStructuralFeature eStructuralFeature) {
/*  272 */     return eClass().getFeatureID(eStructuralFeature) - eStaticFeatureCount();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature eDynamicFeature(int dynamicFeatureID) {
/*  277 */     return eClass().getEStructuralFeature(dynamicFeatureID + eStaticFeatureCount());
/*      */   }
/*      */ 
/*      */   
/*      */   public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject) {
/*  282 */     if (eStructuralFeature == null) {
/*      */       
/*  284 */       EContentsEList.FeatureIterator<EObject> crossReferences = 
/*  285 */         (EContentsEList.FeatureIterator<EObject>)((InternalEList)eCrossReferences()).basicIterator();
/*  286 */       while (crossReferences.hasNext()) {
/*      */         
/*  288 */         EObject crossReference = (EObject)crossReferences.next();
/*  289 */         if (crossReference == eObject)
/*      */         {
/*  291 */           eStructuralFeature = crossReferences.feature();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  296 */     StringBuilder result = new StringBuilder();
/*  297 */     result.append('@');
/*  298 */     result.append(eStructuralFeature.getName());
/*      */     
/*  300 */     if (eStructuralFeature instanceof EAttribute) {
/*      */       
/*  302 */       FeatureMap featureMap = (FeatureMap)eGet(eStructuralFeature, false);
/*  303 */       for (int i = 0, size = featureMap.size(); i < size; i++) {
/*      */         
/*  305 */         if (featureMap.getValue(i) == eObject) {
/*      */           
/*  307 */           EStructuralFeature entryFeature = featureMap.getEStructuralFeature(i);
/*  308 */           if (entryFeature instanceof EReference && ((EReference)entryFeature).isContainment()) {
/*      */             
/*  310 */             result.append('.');
/*  311 */             result.append(i);
/*  312 */             return result.toString();
/*      */           } 
/*      */         } 
/*      */       } 
/*  316 */       result.append(".-1");
/*      */     }
/*  318 */     else if (eStructuralFeature.isMany()) {
/*      */       
/*  320 */       EList<EAttribute> eKeys = ((EReference)eStructuralFeature).getEKeys();
/*  321 */       if (eKeys.isEmpty()) {
/*      */         
/*  323 */         EList<?> eList = (EList)eGet(eStructuralFeature, false);
/*  324 */         int index = eList.indexOf(eObject);
/*  325 */         result.append('.');
/*  326 */         result.append(index);
/*      */       }
/*      */       else {
/*      */         
/*  330 */         EAttribute[] eAttributes = (EAttribute[])((BasicEList)eKeys).data();
/*  331 */         result.append('[');
/*  332 */         for (int i = 0, size = eAttributes.length; i < size; i++) {
/*      */           
/*  334 */           EAttribute eAttribute = eAttributes[i];
/*  335 */           if (eAttribute == null) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  341 */           if (i != 0)
/*      */           {
/*  343 */             result.append(',');
/*      */           }
/*  345 */           result.append(eAttribute.getName());
/*  346 */           result.append('=');
/*  347 */           EDataType eDataType = eAttribute.getEAttributeType();
/*  348 */           EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
/*  349 */           if (eAttribute.isMany()) {
/*      */             
/*  351 */             List<?> values = (List)eObject.eGet((EStructuralFeature)eAttribute);
/*  352 */             result.append('[');
/*  353 */             if (!values.isEmpty()) {
/*      */               
/*  355 */               Iterator<?> j = values.iterator();
/*  356 */               eEncodeValue(result, eFactory, eDataType, j.next());
/*  357 */               while (j.hasNext()) {
/*      */                 
/*  359 */                 result.append(',');
/*  360 */                 eEncodeValue(result, eFactory, eDataType, j.next());
/*      */               } 
/*      */             } 
/*  363 */             result.append(']');
/*      */           }
/*      */           else {
/*      */             
/*  367 */             eEncodeValue(result, eFactory, eDataType, eObject.eGet((EStructuralFeature)eAttribute));
/*      */           } 
/*      */         } 
/*      */         
/*  371 */         result.append(']');
/*      */       } 
/*      */     } 
/*      */     
/*  375 */     return result.toString();
/*      */   }
/*      */ 
/*      */   
/*  379 */   private static final String[] ESCAPE = new String[] { 
/*  380 */       "%00", 
/*  381 */       "%01", 
/*  382 */       "%02", 
/*  383 */       "%03", 
/*  384 */       "%04", 
/*  385 */       "%05", 
/*  386 */       "%06", 
/*  387 */       "%07", 
/*  388 */       "%08", 
/*  389 */       "%09", 
/*  390 */       "%0A", 
/*  391 */       "%0B", 
/*  392 */       "%0C", 
/*  393 */       "%0D", 
/*  394 */       "%0E", 
/*  395 */       "%0F", 
/*  396 */       "%10", 
/*  397 */       "%11", 
/*  398 */       "%12", 
/*  399 */       "%13", 
/*  400 */       "%14", 
/*  401 */       "%15", 
/*  402 */       "%16", 
/*  403 */       "%17", 
/*  404 */       "%18", 
/*  405 */       "%19", 
/*  406 */       "%1A", 
/*  407 */       "%1B", 
/*  408 */       "%1C", 
/*  409 */       "%1D", 
/*  410 */       "%1E", 
/*  411 */       "%1F", 
/*  412 */       "%20", 
/*      */       
/*  414 */       "%22", 
/*  415 */       "%23", 
/*      */       
/*  417 */       "%25", 
/*  418 */       "%26", 
/*  419 */       "%27", 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  424 */       "%2C", 
/*      */ 
/*      */       
/*  427 */       "%2F",
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  438 */       "%3A", 
/*      */       
/*  440 */       "%3C", 
/*      */       
/*  442 */       "%3E" }; protected static final int EVIRTUAL_SET = 0;
/*      */   protected static final int EVIRTUAL_UNSET = 1;
/*      */   protected static final int EVIRTUAL_GET = 2;
/*      */   protected static final int EVIRTUAL_IS_SET = 3;
/*      */   
/*      */   private static void eEncodeValue(StringBuilder result, EFactory eFactory, EDataType eDataType, Object value) {
/*  448 */     String stringValue = eFactory.convertToString(eDataType, value);
/*  449 */     if (stringValue == null) {
/*      */       
/*  451 */       result.append("null");
/*      */     }
/*      */     else {
/*      */       
/*  455 */       int length = stringValue.length();
/*  456 */       result.ensureCapacity(result.length() + length + 2);
/*  457 */       result.append('\'');
/*  458 */       for (int i = 0; i < length; i++) {
/*      */         
/*  460 */         char character = stringValue.charAt(i);
/*  461 */         if (character < ESCAPE.length) {
/*      */           
/*  463 */           String escape = ESCAPE[character];
/*  464 */           if (escape != null) {
/*      */             
/*  466 */             result.append(escape);
/*      */             continue;
/*      */           } 
/*      */         } 
/*  470 */         result.append(character); continue;
/*      */       } 
/*  472 */       result.append('\'');
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static String eEncodeValue(String value) {
/*  484 */     int length = value.length();
/*  485 */     StringBuilder result = null;
/*  486 */     for (int i = 0; i < length; i++) {
/*      */       
/*  488 */       char character = value.charAt(i);
/*  489 */       if (character < ESCAPE.length) {
/*      */         
/*  491 */         String escape = ESCAPE[character];
/*  492 */         if (escape != null) {
/*      */           
/*  494 */           if (result == null) {
/*      */             
/*  496 */             result = new StringBuilder(length + 2);
/*  497 */             result.append(value, 0, i);
/*      */           } 
/*  499 */           result.append(escape);
/*      */           continue;
/*      */         } 
/*      */       } 
/*  503 */       if (result != null)
/*      */       {
/*  505 */         result.append(character); } 
/*      */       continue;
/*      */     } 
/*  508 */     return (result == null) ? value : result.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
/*  513 */     int lastIndex = uriFragmentSegment.length() - 1;
/*  514 */     char lastChar = uriFragmentSegment.charAt(lastIndex);
/*  515 */     if (lastChar == ']') {
/*      */       
/*  517 */       int index = uriFragmentSegment.indexOf('[');
/*  518 */       if (index >= 0)
/*      */       {
/*  520 */         EReference eReference = eReference(uriFragmentSegment.substring(1, index));
/*  521 */         String predicate = uriFragmentSegment.substring(index + 1, lastIndex);
/*  522 */         return eObjectForURIFragmentPredicate(predicate, eReference);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  527 */       int dotIndex = -1;
/*  528 */       if (Character.isDigit(lastChar)) {
/*      */         
/*  530 */         dotIndex = uriFragmentSegment.lastIndexOf('.', lastIndex - 1);
/*  531 */         if (dotIndex >= 0) {
/*      */           
/*  533 */           EList<?> eList = (EList)eGet(eStructuralFeature(uriFragmentSegment.substring(1, dotIndex)), false);
/*  534 */           int position = 0;
/*      */           
/*      */           try {
/*  537 */             position = Integer.parseInt(uriFragmentSegment.substring(dotIndex + 1));
/*      */           }
/*  539 */           catch (NumberFormatException exception) {
/*      */             
/*  541 */             throw new WrappedException(exception);
/*      */           } 
/*  543 */           if (position < eList.size()) {
/*      */             
/*  545 */             Object result = eList.get(position);
/*  546 */             if (result instanceof FeatureMap.Entry)
/*      */             {
/*  548 */               result = ((FeatureMap.Entry)result).getValue();
/*      */             }
/*  550 */             return (EObject)result;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  555 */       if (dotIndex < 0)
/*      */       {
/*  557 */         return (EObject)eGet(eStructuralFeature(uriFragmentSegment.substring(1)), false);
/*      */       }
/*      */     } 
/*      */     
/*  561 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private EObject eObjectForURIFragmentPredicate(String predicate, EReference eReference) {
/*  566 */     ArrayList<FeatureMap.Entry> featureMapEntries = new ArrayList<FeatureMap.Entry>();
/*  567 */     int length = predicate.length();
/*  568 */     EClass eReferenceType = eReference.getEReferenceType();
/*  569 */     for (int i = 0; i < length; ) {
/*      */       int end; ArrayList<Object> values;
/*  571 */       int index = predicate.indexOf('=', i);
/*  572 */       EAttribute eAttribute = eAttribute(eReferenceType, predicate.substring(i, index));
/*  573 */       EDataType eDataType = eAttribute.getEAttributeType();
/*  574 */       EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
/*  575 */       switch (predicate.charAt(++index)) {
/*      */ 
/*      */         
/*      */         case '\'':
/*  579 */           end = predicate.indexOf('\'', ++index);
/*  580 */           addEntry(featureMapEntries, eAttribute, eDecodeValue(predicate.substring(index, end), eFactory, eDataType));
/*  581 */           i = end + 1;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '"':
/*  586 */           end = predicate.indexOf('"', ++index);
/*  587 */           addEntry(featureMapEntries, eAttribute, eDecodeValue(predicate.substring(index, end), eFactory, eDataType));
/*  588 */           i = end + 1;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '[':
/*  593 */           values = new ArrayList();
/*  594 */           addEntry(featureMapEntries, eAttribute, values);
/*      */           
/*      */           while (true) {
/*      */             int j;
/*  598 */             switch (predicate.charAt(++index)) {
/*      */ 
/*      */               
/*      */               case '\'':
/*  602 */                 j = predicate.indexOf('\'', ++index);
/*  603 */                 values.add(eDecodeValue(predicate.substring(index, j), eFactory, eDataType));
/*  604 */                 index = j + 1;
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '"':
/*  609 */                 j = predicate.indexOf('"', ++index);
/*  610 */                 values.add(eDecodeValue(predicate.substring(index, j), eFactory, eDataType));
/*  611 */                 index = j + 1;
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 'n':
/*  616 */                 index++;
/*  617 */                 if (predicate.indexOf("ull", index) == index) {
/*      */                   
/*  619 */                   values.add(null);
/*      */                 }
/*      */                 else {
/*      */                   
/*  623 */                   throw new RuntimeException("Expecting null");
/*      */                 } 
/*  625 */                 index += 3;
/*      */                 break;
/*      */             } 
/*      */ 
/*      */             
/*  630 */             if (index < length) {
/*      */               
/*  632 */               switch (predicate.charAt(index)) {
/*      */                 case ',':
/*      */                   continue;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case ']':
/*      */                   break;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  644 */               throw new RuntimeException("Expecting , or ]");
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */ 
/*      */           
/*  653 */           i = index + 1;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 'n':
/*  658 */           index++;
/*  659 */           if (predicate.indexOf("ull", index) == index) {
/*      */             
/*  661 */             addEntry(featureMapEntries, eAttribute, (Object)null);
/*      */           }
/*      */           else {
/*      */             
/*  665 */             throw new RuntimeException("Expecting null");
/*      */           } 
/*  667 */           i = index + 3;
/*      */           break;
/*      */       } 
/*      */       
/*  671 */       if (i < length) {
/*      */         
/*  673 */         if (predicate.charAt(i) != ',')
/*      */         {
/*  675 */           throw new RuntimeException("Expecting ,");
/*      */         }
/*      */         
/*      */         i++;
/*      */       } 
/*      */       
/*      */       break;
/*      */     } 
/*      */     
/*  684 */     return eObjectForURIFragmentPredicate(featureMapEntries, eReference);
/*      */   }
/*      */ 
/*      */   
/*      */   private static final void addEntry(List<FeatureMap.Entry> featureMapEntries, final EAttribute eAttribute, final Object value) {
/*  689 */     featureMapEntries.add(
/*  690 */         new FeatureMap.Entry()
/*      */         {
/*      */           public EStructuralFeature getEStructuralFeature()
/*      */           {
/*  694 */             return (EStructuralFeature)eAttribute;
/*      */           }
/*      */ 
/*      */           
/*      */           public Object getValue() {
/*  699 */             return value;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   private static Object eDecodeValue(String encodedValue, EFactory eFactory, EDataType eDataType) {
/*  706 */     String literal = URI.decode(encodedValue);
/*  707 */     Object value = eFactory.createFromString(eDataType, literal);
/*  708 */     return value;
/*      */   }
/*      */ 
/*      */   
/*      */   private EObject eObjectForURIFragmentPredicate(List<FeatureMap.Entry> predicate, EReference eReference) {
/*  713 */     int size = predicate.size();
/*  714 */     EList<EObject> list = (EList<EObject>)eGet((EStructuralFeature)eReference, false);
/*      */     
/*  716 */     for (EObject eObject : list) {
/*      */       
/*  718 */       int i = 0; while (true) { if (i >= size)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  729 */           return eObject; }  FeatureMap.Entry entry = predicate.get(i); Object entryValue = entry.getValue(); EStructuralFeature entryFeature = entry.getEStructuralFeature(); Object actualValue = eObject.eGet(entryFeature, false); if ((entryValue == null) ? (actualValue != null) : !entryValue.equals(actualValue))
/*      */           break;  i++; } 
/*  731 */     }  return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private EStructuralFeature eStructuralFeature(String name) throws IllegalArgumentException {
/*  736 */     EStructuralFeature eStructuralFeature = eClass().getEStructuralFeature(name);
/*  737 */     if (eStructuralFeature == null)
/*      */     {
/*  739 */       throw new IllegalArgumentException("The feature '" + name + "' is not a valid feature");
/*      */     }
/*  741 */     return eStructuralFeature;
/*      */   }
/*      */ 
/*      */   
/*      */   private EReference eReference(String name) throws IllegalArgumentException {
/*  746 */     EStructuralFeature eStructuralFeature = eClass().getEStructuralFeature(name);
/*  747 */     if (eStructuralFeature instanceof EReference)
/*      */     {
/*  749 */       return (EReference)eStructuralFeature;
/*      */     }
/*  751 */     throw new IllegalArgumentException("The feature '" + name + "' is not a valid reference");
/*      */   }
/*      */ 
/*      */   
/*      */   private EAttribute eAttribute(EClass eClass, String name) throws IllegalArgumentException {
/*  756 */     EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(name);
/*  757 */     if (eStructuralFeature instanceof EAttribute)
/*      */     {
/*  759 */       return (EAttribute)eStructuralFeature;
/*      */     }
/*  761 */     throw new IllegalArgumentException("The feature '" + name + "' is not a valid attribute");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eContains(EObject eObject) {
/*  766 */     return EcoreUtil.isAncestor(this, eObject);
/*      */   }
/*      */ 
/*      */   
/*      */   public EObject eContainer() {
/*  771 */     InternalEObject result = eInternalContainer();
/*  772 */     if (result != null) {
/*      */       
/*  774 */       int eContainerFeatureID = eContainerFeatureID();
/*  775 */       if (result.eIsProxy()) {
/*      */         
/*  777 */         EObject resolved = eResolveProxy(result);
/*  778 */         if (resolved != result) {
/*      */           
/*  780 */           NotificationChain notificationChain = eBasicRemoveFromContainer((NotificationChain)null);
/*  781 */           eBasicSetContainer((InternalEObject)resolved, eContainerFeatureID);
/*  782 */           if (notificationChain != null)
/*      */           {
/*  784 */             notificationChain.dispatch();
/*      */           }
/*  786 */           if (eNotificationRequired() && eContainerFeatureID > -1)
/*      */           {
/*  788 */             eNotify((Notification)new ENotificationImpl(this, 9, eContainerFeatureID, result, resolved));
/*      */           }
/*  790 */           return resolved;
/*      */         } 
/*      */       } 
/*      */     } 
/*  794 */     return (EObject)result;
/*      */   }
/*      */ 
/*      */   
/*      */   public InternalEObject eInternalContainer() {
/*  799 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int eContainerFeatureID() {
/*  805 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
/*  811 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<EObject> eContents() {
/*      */     EContentsEList eContentsEList;
/*  818 */     EList<EObject> result = eProperties().getEContents();
/*  819 */     if (result == null)
/*      */     {
/*  821 */       eBasicProperties().setEContents((EList<EObject>)(eContentsEList = EContentsEList.createEContentsEList(this)));
/*      */     }
/*      */     
/*  824 */     return (EList<EObject>)eContentsEList;
/*      */   }
/*      */   
/*      */   public EList<EObject> eCrossReferences() {
/*      */     ECrossReferenceEList eCrossReferenceEList;
/*  829 */     EList<EObject> result = eProperties().getECrossReferences();
/*  830 */     if (result == null)
/*      */     {
/*  832 */       eBasicProperties().setECrossReferences((EList<EObject>)(eCrossReferenceEList = ECrossReferenceEList.createECrossReferenceEList(this)));
/*      */     }
/*      */     
/*  835 */     return (EList<EObject>)eCrossReferenceEList;
/*      */   }
/*      */ 
/*      */   
/*      */   public TreeIterator<EObject> eAllContents() {
/*  840 */     return 
/*  841 */       (TreeIterator<EObject>)new AbstractTreeIterator<EObject>(this, false)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */         
/*      */         public Iterator<EObject> getChildren(Object object) {
/*  848 */           return ((EObject)object).eContents().iterator();
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   
/*      */   public EReference eContainmentFeature() {
/*  855 */     return eContainmentFeature(this, (EObject)eInternalContainer(), eContainerFeatureID());
/*      */   }
/*      */ 
/*      */   
/*      */   protected static EReference eContainmentFeature(EObject eObject, EObject eContainer, int eContainerFeatureID) {
/*  860 */     if (eContainer == null)
/*      */     {
/*  862 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  866 */     if (eContainerFeatureID <= -1) {
/*      */       
/*  868 */       EStructuralFeature eFeature = eContainer.eClass().getEStructuralFeature(-1 - eContainerFeatureID);
/*  869 */       if (eFeature instanceof EReference)
/*      */       {
/*  871 */         return (EReference)eFeature;
/*      */       }
/*      */ 
/*      */       
/*  875 */       FeatureMap featureMap = (FeatureMap)eContainer.eGet(eFeature);
/*  876 */       for (int i = 0, size = featureMap.size(); i < size; i++) {
/*      */         
/*  878 */         if (featureMap.getValue(i) == eObject) {
/*      */           
/*  880 */           EStructuralFeature entryFeature = featureMap.getEStructuralFeature(i);
/*  881 */           if (entryFeature instanceof EReference) {
/*      */             
/*  883 */             EReference entryReference = (EReference)entryFeature;
/*  884 */             if (entryReference.isContainment())
/*      */             {
/*  886 */               return entryReference;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*  891 */       throw new IllegalStateException("The containment feature could not be located");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  896 */     return ((EReference)eObject.eClass().getEStructuralFeature(eContainerFeatureID)).getEOpposite();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EStructuralFeature eContainingFeature() {
/*  903 */     InternalEObject internalEObject = eInternalContainer();
/*  904 */     if (internalEObject == null)
/*      */     {
/*  906 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  910 */     int eContainerFeatureID = eContainerFeatureID();
/*  911 */     return 
/*  912 */       (eContainerFeatureID <= -1) ? 
/*  913 */       internalEObject.eClass().getEStructuralFeature(-1 - eContainerFeatureID) : 
/*  914 */       (EStructuralFeature)((EReference)eClass().getEStructuralFeature(eContainerFeatureID)).getEOpposite();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Resource.Internal eDirectResource() {
/*  920 */     EPropertiesHolder eProperties = eBasicProperties();
/*  921 */     return (eProperties == null) ? null : eProperties.getEResource();
/*      */   }
/*      */ 
/*      */   
/*      */   public Resource eResource() {
/*  926 */     return (Resource)eInternalResource();
/*      */   }
/*      */ 
/*      */   
/*      */   public Resource.Internal eInternalResource() {
/*  931 */     Resource.Internal result = eDirectResource();
/*  932 */     if (result == null) {
/*      */       
/*  934 */       int count = 0;
/*  935 */       for (InternalEObject eContainer = eInternalContainer(); eContainer != null; eContainer = eContainer.eInternalContainer()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  940 */         if (++count > 100000)
/*      */         {
/*  942 */           return eContainer.eInternalResource();
/*      */         }
/*  944 */         result = eContainer.eDirectResource();
/*  945 */         if (result != null || eContainer == this) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  951 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eSetResource(Resource.Internal resource, NotificationChain notifications) {
/*  956 */     Resource.Internal oldResource = eDirectResource();
/*  957 */     if (oldResource != null) {
/*      */       
/*  959 */       notifications = ((InternalEList)oldResource.getContents()).basicRemove(this, notifications);
/*      */ 
/*      */ 
/*      */       
/*  963 */       if (resource != null)
/*      */       {
/*  965 */         oldResource.detached(this);
/*      */       }
/*      */     } 
/*  968 */     InternalEObject oldContainer = eInternalContainer();
/*  969 */     if (oldContainer != null)
/*      */     {
/*  971 */       if (eContainmentFeature().isResolveProxies()) {
/*      */         
/*  973 */         Resource.Internal oldContainerResource = oldContainer.eInternalResource();
/*  974 */         if (oldContainerResource != null)
/*      */         {
/*      */           
/*  977 */           if (resource == null)
/*      */           {
/*  979 */             oldContainerResource.attached(this);
/*      */ 
/*      */           
/*      */           }
/*  983 */           else if (oldResource == null)
/*      */           {
/*  985 */             oldContainerResource.detached(this);
/*      */           }
/*      */         
/*      */         }
/*      */       } else {
/*      */         
/*  991 */         notifications = eBasicRemoveFromContainer(notifications);
/*  992 */         notifications = eBasicSetContainer((InternalEObject)null, -1, notifications);
/*      */       } 
/*      */     }
/*      */     
/*  996 */     eSetDirectResource(resource);
/*      */     
/*  998 */     return notifications;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void eSetDirectResource(Resource.Internal resource) {
/* 1003 */     eProperties().setEResource(resource);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eGet(EStructuralFeature eFeature) {
/* 1008 */     return eGet(eFeature, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eGet(EStructuralFeature eFeature, boolean resolve) {
/* 1013 */     return eGet(eFeature, resolve, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eGet(EStructuralFeature eFeature, boolean resolve, boolean coreType) {
/* 1018 */     int featureID = eDerivedStructuralFeatureID(eFeature);
/* 1019 */     if (featureID >= 0)
/*      */     {
/* 1021 */       return eGet(featureID, resolve, coreType);
/*      */     }
/*      */ 
/*      */     
/* 1025 */     return eOpenGet(eFeature, resolve);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 1031 */     EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
/* 1032 */     assert eFeature != null : "Invalid featureID: " + featureID;
/* 1033 */     int dynamicFeatureID = featureID - eStaticFeatureCount();
/*      */     
/* 1035 */     return (dynamicFeatureID < 0) ? 
/* 1036 */       eGet(eFeature, resolve) : 
/* 1037 */       eSettingDelegate(eFeature).dynamicGet(this, eSettings(), dynamicFeatureID, resolve, coreType);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eDynamicGet(EStructuralFeature eFeature, boolean resolve) {
/* 1042 */     return eDynamicGet(eDynamicFeatureID(eFeature), eFeature, resolve, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eDynamicGet(int featureID, boolean resolve, boolean coreType) {
/* 1047 */     return eDynamicGet(featureID - eStaticFeatureCount(), eClass().getEStructuralFeature(featureID), resolve, coreType);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object eDynamicGet(int dynamicFeatureID, EStructuralFeature eFeature, boolean resolve, boolean coreType) {
/* 1052 */     return 
/* 1053 */       (dynamicFeatureID < 0) ? 
/* 1054 */       eOpenGet(eFeature, resolve) : 
/* 1055 */       eSettingDelegate(eFeature).dynamicGet(this, eSettings(), dynamicFeatureID, resolve, coreType);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eOpenGet(EStructuralFeature eFeature, boolean resolve) {
/* 1060 */     EStructuralFeature openFeature = ExtendedMetaData.INSTANCE.getAffiliation(eClass(), eFeature);
/* 1061 */     if (openFeature != null) {
/*      */       
/* 1063 */       if (!FeatureMapUtil.isFeatureMap(openFeature))
/*      */       {
/* 1065 */         openFeature = ExtendedMetaData.INSTANCE.getGroup(openFeature);
/*      */       }
/* 1067 */       FeatureMap featureMap = (FeatureMap)eGet(openFeature);
/* 1068 */       return ((FeatureMap.Internal)featureMap).get(eFeature, resolve);
/*      */     } 
/*      */ 
/*      */     
/* 1072 */     throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid feature");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void eSet(EStructuralFeature eFeature, Object newValue) {
/* 1078 */     int featureID = eDerivedStructuralFeatureID(eFeature);
/* 1079 */     if (featureID >= 0) {
/*      */       
/* 1081 */       eSet(featureID, newValue);
/*      */     }
/*      */     else {
/*      */       
/* 1085 */       eOpenSet(eFeature, newValue);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eSet(int featureID, Object newValue) {
/* 1091 */     EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
/* 1092 */     int dynamicFeatureID = featureID - eStaticFeatureCount();
/* 1093 */     if (dynamicFeatureID < 0) {
/*      */       
/* 1095 */       if (eFeature == null)
/*      */       {
/* 1097 */         throw new IllegalArgumentException("The feature ID" + featureID + " is not a valid feature ID");
/*      */       }
/* 1099 */       if (!eFeature.isChangeable())
/*      */       {
/* 1101 */         throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid changeable feature");
/*      */       }
/*      */ 
/*      */       
/* 1105 */       eSet(eFeature, newValue);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1110 */       assert eFeature != null : "Invalid featureID: " + featureID;
/* 1111 */       eDynamicSet(dynamicFeatureID, eFeature, newValue);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eDynamicSet(EStructuralFeature eFeature, Object newValue) {
/* 1117 */     eDynamicSet(eDynamicFeatureID(eFeature), eFeature, newValue);
/*      */   }
/*      */ 
/*      */   
/*      */   public void eDynamicSet(int featureID, Object newValue) {
/* 1122 */     eDynamicSet(featureID - eStaticFeatureCount(), eClass().getEStructuralFeature(featureID), newValue);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void eDynamicSet(int dynamicFeatureID, EStructuralFeature eFeature, Object newValue) {
/* 1127 */     if (dynamicFeatureID < 0) {
/*      */       
/* 1129 */       eOpenSet(eFeature, newValue);
/*      */     }
/*      */     else {
/*      */       
/* 1133 */       if (!eFeature.isChangeable())
/*      */       {
/* 1135 */         throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid changeable feature");
/*      */       }
/* 1137 */       eSettingDelegate(eFeature).dynamicSet(this, eSettings(), dynamicFeatureID, newValue);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eOpenSet(EStructuralFeature eFeature, Object newValue) {
/* 1143 */     EStructuralFeature openFeature = ExtendedMetaData.INSTANCE.getAffiliation(eClass(), eFeature);
/* 1144 */     if (openFeature != null) {
/*      */       
/* 1146 */       if (!FeatureMapUtil.isFeatureMap(openFeature))
/*      */       {
/* 1148 */         openFeature = ExtendedMetaData.INSTANCE.getGroup(openFeature);
/*      */       }
/* 1150 */       FeatureMap featureMap = (FeatureMap)eGet(openFeature);
/* 1151 */       ((FeatureMap.Internal)featureMap).set(eFeature, newValue);
/*      */     }
/*      */     else {
/*      */       
/* 1155 */       throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid changeable feature");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eUnset(EStructuralFeature eFeature) {
/* 1161 */     int featureID = eDerivedStructuralFeatureID(eFeature);
/* 1162 */     if (featureID >= 0) {
/*      */       
/* 1164 */       eUnset(featureID);
/*      */     }
/*      */     else {
/*      */       
/* 1168 */       eOpenUnset(eFeature);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eUnset(int featureID) {
/* 1174 */     EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
/* 1175 */     int dynamicFeatureID = featureID - eStaticFeatureCount();
/* 1176 */     if (dynamicFeatureID < 0) {
/*      */       
/* 1178 */       if (eFeature == null)
/*      */       {
/* 1180 */         throw new IllegalArgumentException("The feature ID" + featureID + " is not a valid feature ID");
/*      */       }
/* 1182 */       if (!eFeature.isChangeable())
/*      */       {
/* 1184 */         throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid changeable feature");
/*      */       }
/*      */ 
/*      */       
/* 1188 */       eUnset(eFeature);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1193 */       assert eFeature != null : "Invalid featureID: " + featureID;
/* 1194 */       eDynamicUnset(dynamicFeatureID, eFeature);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eDynamicUnset(EStructuralFeature eFeature) {
/* 1200 */     eDynamicUnset(eDynamicFeatureID(eFeature), eFeature);
/*      */   }
/*      */ 
/*      */   
/*      */   public void eDynamicUnset(int featureID) {
/* 1205 */     eDynamicUnset(featureID - eStaticFeatureCount(), eClass().getEStructuralFeature(featureID));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void eDynamicUnset(int dynamicFeatureID, EStructuralFeature eFeature) {
/* 1210 */     if (dynamicFeatureID < 0) {
/*      */       
/* 1212 */       eOpenUnset(eFeature);
/*      */     }
/*      */     else {
/*      */       
/* 1216 */       if (!eFeature.isChangeable())
/*      */       {
/* 1218 */         throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid changeable feature");
/*      */       }
/* 1220 */       eSettingDelegate(eFeature).dynamicUnset(this, eSettings(), dynamicFeatureID);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void eOpenUnset(EStructuralFeature eFeature) {
/* 1226 */     EStructuralFeature openFeature = ExtendedMetaData.INSTANCE.getAffiliation(eClass(), eFeature);
/* 1227 */     if (openFeature != null) {
/*      */       
/* 1229 */       if (!FeatureMapUtil.isFeatureMap(openFeature))
/*      */       {
/* 1231 */         openFeature = ExtendedMetaData.INSTANCE.getGroup(openFeature);
/*      */       }
/* 1233 */       FeatureMap featureMap = (FeatureMap)eGet(openFeature);
/* 1234 */       ((FeatureMap.Internal)featureMap).unset(eFeature);
/*      */     }
/*      */     else {
/*      */       
/* 1238 */       throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid changeable feature");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eIsSet(EStructuralFeature eFeature) {
/* 1244 */     int featureID = eDerivedStructuralFeatureID(eFeature);
/* 1245 */     if (featureID >= 0)
/*      */     {
/* 1247 */       return eIsSet(featureID);
/*      */     }
/*      */ 
/*      */     
/* 1251 */     return eOpenIsSet(eFeature);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eIsSet(int featureID) {
/* 1257 */     EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
/* 1258 */     assert eFeature != null : "Invalid featureID: " + featureID;
/* 1259 */     int dynamicFeatureID = featureID - eStaticFeatureCount();
/*      */     
/* 1261 */     return (dynamicFeatureID < 0) ? 
/* 1262 */       eIsSet(eFeature) : 
/* 1263 */       eDynamicIsSet(dynamicFeatureID, eFeature);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eDynamicIsSet(EStructuralFeature eFeature) {
/* 1268 */     return eDynamicIsSet(eDynamicFeatureID(eFeature), eFeature);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eDynamicIsSet(int featureID) {
/* 1273 */     return eDynamicIsSet(featureID - eStaticFeatureCount(), eClass().getEStructuralFeature(featureID));
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean eDynamicIsSet(int dynamicFeatureID, EStructuralFeature eFeature) {
/* 1278 */     return 
/* 1279 */       (dynamicFeatureID < 0) ? 
/* 1280 */       eOpenIsSet(eFeature) : 
/* 1281 */       eSettingDelegate(eFeature).dynamicIsSet(this, eSettings(), dynamicFeatureID);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eOpenIsSet(EStructuralFeature eFeature) {
/* 1286 */     EStructuralFeature openFeature = ExtendedMetaData.INSTANCE.getAffiliation(eClass(), eFeature);
/* 1287 */     if (openFeature != null) {
/*      */       
/* 1289 */       if (!FeatureMapUtil.isFeatureMap(openFeature))
/*      */       {
/* 1291 */         openFeature = ExtendedMetaData.INSTANCE.getGroup(openFeature);
/*      */       }
/* 1293 */       FeatureMap featureMap = (FeatureMap)eGet(openFeature);
/* 1294 */       return ((FeatureMap.Internal)featureMap).isSet(eFeature);
/*      */     } 
/*      */ 
/*      */     
/* 1298 */     throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid feature");
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain msgs) {
/*      */     ENotificationImpl eNotificationImpl;
/* 1304 */     InternalEObject oldContainer = eInternalContainer();
/* 1305 */     Resource.Internal oldResource = eDirectResource();
/* 1306 */     Resource.Internal newResource = null;
/* 1307 */     if (oldResource != null) {
/*      */       
/* 1309 */       if (newContainer != null && !eContainmentFeature(this, (EObject)newContainer, newContainerFeatureID).isResolveProxies())
/*      */       {
/* 1311 */         msgs = ((InternalEList)oldResource.getContents()).basicRemove(this, msgs);
/* 1312 */         eSetDirectResource((Resource.Internal)null);
/* 1313 */         newResource = newContainer.eInternalResource();
/*      */       }
/*      */       else
/*      */       {
/* 1317 */         oldResource = null;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1322 */       if (oldContainer != null)
/*      */       {
/* 1324 */         oldResource = oldContainer.eInternalResource();
/*      */       }
/* 1326 */       if (newContainer != null)
/*      */       {
/* 1328 */         newResource = newContainer.eInternalResource();
/*      */       }
/*      */     } 
/*      */     
/* 1332 */     if (oldResource != newResource && oldResource != null)
/*      */     {
/* 1334 */       oldResource.detached(this);
/*      */     }
/*      */     
/* 1337 */     int oldContainerFeatureID = eContainerFeatureID();
/* 1338 */     eBasicSetContainer(newContainer, newContainerFeatureID);
/*      */     
/* 1340 */     if (oldResource != newResource && newResource != null)
/*      */     {
/* 1342 */       newResource.attached(this);
/*      */     }
/*      */     
/* 1345 */     if (eNotificationRequired()) {
/*      */       
/* 1347 */       if (oldContainer != null && oldContainerFeatureID >= 0 && oldContainerFeatureID != newContainerFeatureID) {
/*      */         
/* 1349 */         ENotificationImpl notification = 
/* 1350 */           new ENotificationImpl(
/* 1351 */             this, 
/* 1352 */             1, 
/* 1353 */             oldContainerFeatureID, 
/* 1354 */             oldContainer, 
/* 1355 */             null);
/* 1356 */         if (msgs == null) {
/*      */           
/* 1358 */           eNotificationImpl = notification;
/*      */         }
/*      */         else {
/*      */           
/* 1362 */           eNotificationImpl.add((Notification)notification);
/*      */         } 
/*      */       } 
/* 1365 */       if (newContainerFeatureID >= 0) {
/*      */         
/* 1367 */         ENotificationImpl notification = 
/* 1368 */           new ENotificationImpl(
/* 1369 */             this, 
/* 1370 */             1, 
/* 1371 */             newContainerFeatureID, 
/* 1372 */             (oldContainerFeatureID == newContainerFeatureID) ? oldContainer : null, 
/* 1373 */             newContainer);
/* 1374 */         if (eNotificationImpl == null) {
/*      */           
/* 1376 */           eNotificationImpl = notification;
/*      */         }
/*      */         else {
/*      */           
/* 1380 */           eNotificationImpl.add((Notification)notification);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1384 */     return (NotificationChain)eNotificationImpl;
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
/* 1389 */     int eContainerFeatureID = eContainerFeatureID();
/* 1390 */     if (eContainerFeatureID >= 0)
/*      */     {
/* 1392 */       return eBasicRemoveFromContainerFeature(msgs);
/*      */     }
/*      */ 
/*      */     
/* 1396 */     return eInternalContainer().eInverseRemove(this, -1 - eContainerFeatureID, null, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 1402 */     return eDynamicBasicRemoveFromContainer(msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eDynamicBasicRemoveFromContainer(NotificationChain msgs) {
/* 1407 */     EReference inverseFeature = ((EReference)eClass().getEStructuralFeature(eContainerFeatureID())).getEOpposite();
/* 1408 */     return eInternalContainer().eInverseRemove(this, inverseFeature.getFeatureID(), inverseFeature.getContainerClass(), msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain msgs) {
/* 1413 */     if (featureID >= 0)
/*      */     {
/* 1415 */       return eInverseAdd(otherEnd, eDerivedStructuralFeatureID(featureID, baseClass), msgs);
/*      */     }
/*      */ 
/*      */     
/* 1419 */     if (eInternalContainer() != null)
/*      */     {
/* 1421 */       msgs = eBasicRemoveFromContainer(msgs);
/*      */     }
/* 1423 */     return eBasicSetContainer(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 1429 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eDynamicInverseAdd(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain msgs) {
/* 1434 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   protected NotificationChain eDynamicInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 1439 */     EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEStructuralFeature(featureID);
/* 1440 */     return feature.getSettingDelegate().dynamicInverseAdd(this, eSettings(), featureID - eStaticFeatureCount(), otherEnd, msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain msgs) {
/* 1445 */     if (featureID >= 0)
/*      */     {
/* 1447 */       return eInverseRemove(otherEnd, eDerivedStructuralFeatureID(featureID, baseClass), msgs);
/*      */     }
/*      */ 
/*      */     
/* 1451 */     return eBasicSetContainer((InternalEObject)null, featureID, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 1457 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain eDynamicInverseRemove(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain msgs) {
/* 1462 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   protected NotificationChain eDynamicInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 1467 */     EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEStructuralFeature(featureID);
/* 1468 */     return feature.getSettingDelegate().dynamicInverseRemove(this, eSettings(), featureID - eStaticFeatureCount(), otherEnd, msgs);
/*      */   }
/*      */ 
/*      */   
/*      */   public URI eProxyURI() {
/* 1473 */     return (eBasicProperties() == null) ? null : eBasicProperties().getEProxyURI();
/*      */   }
/*      */ 
/*      */   
/*      */   public void eSetProxyURI(URI uri) {
/* 1478 */     eProperties().setEProxyURI(uri);
/*      */   }
/*      */ 
/*      */   
/*      */   public EObject eResolveProxy(InternalEObject proxy) {
/* 1483 */     return EcoreUtil.resolve((EObject)proxy, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eIsProxy() {
/* 1488 */     return (eBasicProperties() != null && eBasicProperties().getEProxyURI() != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 1493 */     return derivedFeatureID;
/*      */   }
/*      */ 
/*      */   
/*      */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 1498 */     return baseFeatureID;
/*      */   }
/*      */ 
/*      */   
/*      */   public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature) {
/* 1503 */     Class<?> containerClass = eStructuralFeature.getContainerClass();
/* 1504 */     if (containerClass == null)
/*      */     {
/* 1506 */       return eClass().getFeatureID(eStructuralFeature);
/*      */     }
/*      */ 
/*      */     
/* 1510 */     assert eClass().getEAllStructuralFeatures().contains(eStructuralFeature) : "The feature '" + eStructuralFeature.getName() + "' is not a valid feature";
/* 1511 */     return eDerivedStructuralFeatureID(eStructuralFeature.getFeatureID(), containerClass);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
/* 1517 */     return baseOperationID;
/*      */   }
/*      */ 
/*      */   
/*      */   public int eDerivedOperationID(EOperation eOperation) {
/* 1522 */     Class<?> containerClass = eOperation.getEContainingClass().getInstanceClass();
/* 1523 */     if (containerClass == null) {
/*      */       
/* 1525 */       EClass eClass = eClass();
/* 1526 */       EOperation override = eClass.getOverride(eOperation);
/* 1527 */       return eClass.getOperationID((override != null) ? override : eOperation);
/*      */     } 
/*      */ 
/*      */     
/* 1531 */     assert eClass().getEAllOperations().contains(eOperation) : "The operation '" + eOperation.getName() + "' is not a valid operation";
/* 1532 */     return eDerivedOperationID(eOperation.getOperationID(), containerClass);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EClass eClass() {
/* 1538 */     if (eBasicProperties() != null) {
/*      */       
/* 1540 */       EClass result = eBasicProperties().getEClass();
/* 1541 */       if (result != null)
/*      */       {
/* 1543 */         return result;
/*      */       }
/*      */     } 
/* 1546 */     return eStaticClass();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EClass eStaticClass() {
/* 1552 */     return EcorePackage.eINSTANCE.getEObject();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EClass eDynamicClass() {
/* 1557 */     return 
/* 1558 */       (eBasicProperties() == null) ? 
/* 1559 */       null : 
/* 1560 */       eBasicProperties().getEClass();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void eSetClass(EClass eClass) {
/* 1566 */     eProperties().setEClass(eClass);
/*      */   }
/*      */ 
/*      */   
/*      */   protected EStructuralFeature.Internal.SettingDelegate eSettingDelegate(EStructuralFeature eFeature) {
/* 1571 */     return ((EStructuralFeature.Internal)eFeature).getSettingDelegate();
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature.Setting eSetting(final EStructuralFeature eFeature) {
/* 1576 */     EClass eClass = eClass();
/* 1577 */     int index = eClass.getFeatureID(eFeature);
/* 1578 */     int dynamicIndex = eStaticFeatureCount();
/* 1579 */     if (index >= dynamicIndex)
/*      */     {
/* 1581 */       return eSettingDelegate(eFeature).dynamicSetting(this, eSettings(), index - dynamicIndex);
/*      */     }
/* 1583 */     if (index <= -1) {
/*      */       
/* 1585 */       EStructuralFeature openFeature = ExtendedMetaData.INSTANCE.getAffiliation(eClass, eFeature);
/* 1586 */       if (openFeature != null)
/*      */       {
/* 1588 */         if (!FeatureMapUtil.isFeatureMap(openFeature))
/*      */         {
/* 1590 */           openFeature = ExtendedMetaData.INSTANCE.getGroup(openFeature);
/*      */         }
/* 1592 */         FeatureMap featureMap = (FeatureMap)eGet(openFeature);
/* 1593 */         int upperBound = openFeature.getUpperBound();
/* 1594 */         if (upperBound > 1 || upperBound == -1)
/*      */         {
/* 1596 */           return (EStructuralFeature.Setting)((FeatureMap.Internal)featureMap).get(eFeature, false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1601 */         throw new IllegalArgumentException("The feature '" + eFeature.getName() + "' is not a valid feature");
/*      */       }
/*      */     
/* 1604 */     } else if (eFeature.isMany()) {
/*      */       
/* 1606 */       return (EStructuralFeature.Setting)eGet(eFeature, false);
/*      */     } 
/*      */     
/* 1609 */     EStructuralFeature.Setting setting = 
/* 1610 */       new EStructuralFeature.Setting()
/*      */       {
/*      */         public EObject getEObject()
/*      */         {
/* 1614 */           return BasicEObjectImpl.this;
/*      */         }
/*      */ 
/*      */         
/*      */         public EStructuralFeature getEStructuralFeature() {
/* 1619 */           return eFeature;
/*      */         }
/*      */ 
/*      */         
/*      */         public Object get(boolean resolve) {
/* 1624 */           return BasicEObjectImpl.this.eGet(eFeature, resolve);
/*      */         }
/*      */ 
/*      */         
/*      */         public void set(Object newValue) {
/* 1629 */           BasicEObjectImpl.this.eSet(eFeature, newValue);
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean isSet() {
/* 1634 */           return BasicEObjectImpl.this.eIsSet(eFeature);
/*      */         }
/*      */ 
/*      */         
/*      */         public void unset() {
/* 1639 */           BasicEObjectImpl.this.eUnset(eFeature);
/*      */         }
/*      */       };
/* 1642 */     return setting;
/*      */   }
/*      */ 
/*      */   
/*      */   protected EOperation.Internal.InvocationDelegate eInvocationDelegate(EOperation eOperation) {
/* 1647 */     return ((EOperation.Internal)eOperation).getInvocationDelegate();
/*      */   }
/*      */ 
/*      */   
/*      */   public InternalEObject.EStore eStore() {
/* 1652 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void eSetStore(InternalEObject.EStore store) {
/* 1657 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Adapter[] eContainerAdapterArray() {
/* 1666 */     InternalEObject eInternalContainer = eInternalContainer();
/* 1667 */     return 
/* 1668 */       (eInternalContainer instanceof BasicEObjectImpl) ? (
/* 1669 */       (BasicEObjectImpl)eInternalContainer).eBasicAdapterArray() : 
/* 1670 */       null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int eVirtualBitCount(int value) {
/* 1682 */     value -= value >>> 1 & 0x55555555;
/* 1683 */     value = (value & 0x33333333) + (value >>> 2 & 0x33333333);
/* 1684 */     value = value + (value >>> 4) & 0xF0F0F0F;
/* 1685 */     value += value >>> 8;
/* 1686 */     value += value >>> 16;
/* 1687 */     return value & 0x3F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int eVirtualIndexBits(int offset) {
/* 1699 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void eSetVirtualIndexBits(int offset, int newIndexBits) {
/* 1711 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int eVirtualIndex(int eDerivedStructuralFeatureID, int action) {
/* 1754 */     int offset = eDerivedStructuralFeatureID >>> 5;
/* 1755 */     int bits = eVirtualIndexBits(offset);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1760 */     int bitIndex = eDerivedStructuralFeatureID & 0x1F;
/* 1761 */     int bit = bits >>> bitIndex & 0x1;
/*      */     
/* 1763 */     switch (action) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/* 1769 */         return bit - 1;
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 2:
/* 1774 */         if (bit == 0)
/*      */         {
/*      */ 
/*      */           
/* 1778 */           return -1;
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1789 */     if (bit == action)
/*      */     {
/* 1791 */       eSetVirtualIndexBits(offset, bits ^ 1 << bitIndex);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1797 */     int result = eVirtualBitCount((bitIndex == 0) ? 0 : (bits << 32 - bitIndex));
/*      */ 
/*      */ 
/*      */     
/* 1801 */     for (int i = offset; --i >= 0;)
/*      */     {
/* 1803 */       result += eVirtualBitCount(eVirtualIndexBits(i));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1809 */     return (bit != 0) ? result : (result ^ 0xFFFFFFFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] eVirtualValues() {
/* 1821 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void eSetVirtualValues(Object[] newValues) {
/* 1831 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object eVirtualValue(int index) {
/* 1841 */     return eVirtualValues()[index];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object eSetVirtualValue(int index, Object value) {
/* 1851 */     Object[] values = eVirtualValues();
/* 1852 */     Object oldValue = values[index];
/* 1853 */     values[index] = value;
/* 1854 */     return oldValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int eComputeVirtualValuesCapacity(int minimumCapacity) {
/* 1867 */     return minimumCapacity + (minimumCapacity >> 3) + 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void eAddVirtualValue(int index, Object value) {
/* 1876 */     Object[] values = eVirtualValues();
/* 1877 */     if (values == null) {
/*      */       
/* 1879 */       int newLength = eComputeVirtualValuesCapacity(1);
/* 1880 */       values = new Object[newLength];
/* 1881 */       values[0] = value;
/* 1882 */       for (int i = 1; i < newLength; i++)
/*      */       {
/* 1884 */         values[i] = EVIRTUAL_NO_VALUE;
/*      */       }
/* 1886 */       eSetVirtualValues(values);
/*      */     }
/*      */     else {
/*      */       
/* 1890 */       int length = values.length;
/* 1891 */       if (values[length - 1] == EVIRTUAL_NO_VALUE) {
/*      */         
/* 1893 */         if (index + 1 < length)
/*      */         {
/* 1895 */           System.arraycopy(values, index, values, index + 1, length - index - 1);
/*      */         }
/*      */         
/* 1898 */         values[index] = value;
/*      */       }
/*      */       else {
/*      */         
/* 1902 */         int newLength = eComputeVirtualValuesCapacity(length + 1);
/* 1903 */         Object[] newValues = new Object[newLength];
/*      */         
/* 1905 */         for (int i = length; ++i < newLength;)
/*      */         {
/* 1907 */           newValues[i] = EVIRTUAL_NO_VALUE;
/*      */         }
/*      */         
/* 1910 */         if (index > 0)
/*      */         {
/* 1912 */           System.arraycopy(values, 0, newValues, 0, index);
/*      */         }
/*      */         
/* 1915 */         if (index < length)
/*      */         {
/* 1917 */           System.arraycopy(values, index, newValues, index + 1, length - index);
/*      */         }
/*      */         
/* 1920 */         newValues[index] = value;
/* 1921 */         eSetVirtualValues(newValues);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object eRemoveVirtualValue(int index) {
/* 1933 */     Object[] values = eVirtualValues();
/* 1934 */     Object oldValue = values[index];
/* 1935 */     int length = values.length - 1;
/*      */     
/* 1937 */     if (index == 0 && (length == 0 || values[1] == EVIRTUAL_NO_VALUE)) {
/*      */       
/* 1939 */       eSetVirtualValues((Object[])null);
/*      */ 
/*      */     
/*      */     }
/* 1943 */     else if (index < length) {
/*      */       
/* 1945 */       System.arraycopy(values, index + 1, values, index, length - index);
/*      */     } 
/*      */     
/* 1948 */     return oldValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eVirtualGet(int eDerivedStructuralFeatureID) {
/* 1958 */     return eVirtualGet(eDerivedStructuralFeatureID, (Object)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eVirtualGet(int eDerivedStructuralFeatureID, Object defaultValue) {
/* 1971 */     int index = eVirtualIndex(eDerivedStructuralFeatureID, 2);
/* 1972 */     return (index < 0) ? defaultValue : eVirtualValue(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eVirtualIsSet(int eDerivedStructuralFeatureID) {
/* 1984 */     return (eVirtualIndex(eDerivedStructuralFeatureID, 3) >= 0);
/*      */   }
/*      */   
/* 1987 */   protected static final Object EVIRTUAL_NO_VALUE = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eVirtualSet(int eDerivedStructuralFeatureID, Object value) {
/* 1998 */     int index = eVirtualIndex(eDerivedStructuralFeatureID, 0);
/* 1999 */     if (index < 0) {
/*      */ 
/*      */ 
/*      */       
/* 2003 */       eAddVirtualValue(index ^ 0xFFFFFFFF, value);
/* 2004 */       return EVIRTUAL_NO_VALUE;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2010 */     return eSetVirtualValue(index, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eVirtualUnset(int eDerivedStructuralFeatureID) {
/* 2021 */     int index = eVirtualIndex(eDerivedStructuralFeatureID, 1);
/* 2022 */     if (index < 0)
/*      */     {
/* 2024 */       return EVIRTUAL_NO_VALUE;
/*      */     }
/*      */ 
/*      */     
/* 2028 */     return eRemoveVirtualValue(index);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eInvoke(EOperation eOperation, EList<?> arguments) throws InvocationTargetException {
/* 2034 */     int operationID = eDerivedOperationID(eOperation);
/* 2035 */     if (operationID >= 0)
/*      */     {
/* 2037 */       return eInvoke(operationID, arguments);
/*      */     }
/*      */ 
/*      */     
/* 2041 */     throw new IllegalArgumentException("The operation '" + eOperation.getName() + "' is not a valid operation");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 2047 */     EOperation eOperation = eClass().getEOperation(operationID);
/* 2048 */     assert eOperation != null : "Invalid operationID: " + operationID;
/*      */     
/* 2050 */     return eInvocationDelegate(eOperation).dynamicInvoke(this, arguments);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object eDynamicInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 2055 */     return eDynamicInvoke(eClass().getEOperation(operationID), arguments);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object eDynamicInvoke(EOperation eOperation, EList<?> arguments) throws InvocationTargetException {
/* 2060 */     return eInvocationDelegate(eOperation).dynamicInvoke(this, arguments);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2066 */     StringBuilder result = new StringBuilder(getClass().getName());
/* 2067 */     result.append('@');
/* 2068 */     result.append(Integer.toHexString(hashCode()));
/*      */     
/* 2070 */     if (eIsProxy()) {
/*      */       
/* 2072 */       result.append(" (eProxyURI: ");
/* 2073 */       result.append(eProxyURI());
/* 2074 */       if (eDynamicClass() != null) {
/*      */         
/* 2076 */         result.append(" eClass: ");
/* 2077 */         result.append(eDynamicClass());
/*      */       } 
/* 2079 */       result.append(')');
/*      */     }
/* 2081 */     else if (eDynamicClass() != null) {
/*      */       
/* 2083 */       result.append(" (eClass: ");
/* 2084 */       result.append(eDynamicClass());
/* 2085 */       result.append(')');
/*      */     } 
/*      */     
/* 2088 */     return result.toString();
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\BasicEObjectImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */