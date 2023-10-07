/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.util.AbstractList;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.WeakHashMap;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.UniqueEList;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*      */ import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
/*      */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
/*      */ public final class FeatureMapUtil
/*      */ {
/*   55 */   protected static final Class<Validator> VALIDATOR_CLASS = Validator.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addText(FeatureMap featureMap, String text) {
/*   64 */     featureMap.add(XMLTypeFeatures.TEXT, text);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addText(FeatureMap featureMap, int index, String text) {
/*   69 */     featureMap.add(index, XMLTypeFeatures.TEXT, text);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isText(FeatureMap.Entry entry) {
/*   74 */     return (entry.getEStructuralFeature() == XMLTypeFeatures.TEXT);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isText(EStructuralFeature eStructuralFeature) {
/*   79 */     return (eStructuralFeature == XMLTypeFeatures.TEXT);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addCDATA(FeatureMap featureMap, String cdata) {
/*   84 */     featureMap.add(XMLTypeFeatures.CDATA, cdata);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addCDATA(FeatureMap featureMap, int index, String cdata) {
/*   89 */     featureMap.add(index, XMLTypeFeatures.CDATA, cdata);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCDATA(FeatureMap.Entry entry) {
/*   94 */     return (entry.getEStructuralFeature() == XMLTypeFeatures.CDATA);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCDATA(EStructuralFeature eStructuralFeature) {
/*   99 */     return (eStructuralFeature == XMLTypeFeatures.CDATA);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addComment(FeatureMap featureMap, String comment) {
/*  104 */     featureMap.add(XMLTypeFeatures.COMMENT, comment);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addComment(FeatureMap featureMap, int index, String comment) {
/*  109 */     featureMap.add(index, XMLTypeFeatures.COMMENT, comment);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isComment(FeatureMap.Entry entry) {
/*  114 */     return (entry.getEStructuralFeature() == XMLTypeFeatures.COMMENT);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isComment(EStructuralFeature eStructuralFeature) {
/*  119 */     return (eStructuralFeature == XMLTypeFeatures.COMMENT);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addProcessingInstruction(FeatureMap featureMap, String target, String data) {
/*  124 */     ProcessingInstruction processingInstruction = XMLTypeFactory.eINSTANCE.createProcessingInstruction();
/*  125 */     processingInstruction.setTarget(target);
/*  126 */     processingInstruction.setData(data);
/*  127 */     featureMap.add(XMLTypeFeatures.PROCESSING_INSTRUCTION, processingInstruction);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addProcessingInstruction(FeatureMap featureMap, int index, String target, String data) {
/*  132 */     ProcessingInstruction processingInstruction = XMLTypeFactory.eINSTANCE.createProcessingInstruction();
/*  133 */     processingInstruction.setTarget(target);
/*  134 */     processingInstruction.setData(data);
/*  135 */     featureMap.add(index, XMLTypeFeatures.PROCESSING_INSTRUCTION, processingInstruction);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isProcessingInstruction(FeatureMap.Entry entry) {
/*  140 */     return (entry.getEStructuralFeature() == XMLTypeFeatures.PROCESSING_INSTRUCTION);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isProcessingInstruction(EStructuralFeature eStructuralFeature) {
/*  145 */     return (eStructuralFeature == XMLTypeFeatures.PROCESSING_INSTRUCTION);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFeatureMap(EStructuralFeature eStructuralFeature) {
/*  150 */     return ((EStructuralFeatureImpl)eStructuralFeature).isFeatureMap();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFeatureMapEntry(EClassifier eClassifier) {
/*  155 */     return (eClassifier.getInstanceClassName() == "org.eclipse.emf.ecore.util.FeatureMap$Entry");
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry createTextEntry(String value) {
/*  160 */     return XMLTypeFeatures.TEXT_PROTOTYPE.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry createCDATAEntry(String value) {
/*  165 */     return XMLTypeFeatures.CDATA_PROTOTYPE.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry createCommentEntry(String value) {
/*  170 */     return XMLTypeFeatures.COMMENT_PROTOTYPE.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry createProcessingInstructionEntry(String target, String data) {
/*  175 */     return createRawProcessingInstructionEntry(target, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value) {
/*  180 */     FeatureMap.Entry.Internal prototype = ((EStructuralFeature.Internal)eStructuralFeature).getFeatureMapEntryPrototype();
/*  181 */     prototype.validate(value);
/*  182 */     return prototype.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry.Internal createRawEntry(EStructuralFeature eStructuralFeature, Object value) {
/*  187 */     FeatureMap.Entry.Internal prototype = ((EStructuralFeature.Internal)eStructuralFeature).getFeatureMapEntryPrototype();
/*  188 */     return prototype.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry.Internal createRawTextEntry(String value) {
/*  193 */     return XMLTypeFeatures.TEXT_PROTOTYPE.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry.Internal createRawCDATAEntry(String value) {
/*  198 */     return XMLTypeFeatures.CDATA_PROTOTYPE.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry.Internal createRawCommentEntry(String value) {
/*  203 */     return XMLTypeFeatures.COMMENT_PROTOTYPE.createEntry(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public static FeatureMap.Entry.Internal createRawProcessingInstructionEntry(String target, String data) {
/*  208 */     ProcessingInstruction processingInstruction = XMLTypeFactory.eINSTANCE.createProcessingInstruction();
/*  209 */     processingInstruction.setTarget(target);
/*  210 */     processingInstruction.setData(data);
/*  211 */     return XMLTypeFeatures.PROCESSING_INSTRUCTION_PROTOTYPE.createEntry(processingInstruction);
/*      */   }
/*      */   
/*      */   public static class EntryImpl
/*      */     implements FeatureMap.Entry
/*      */   {
/*      */     protected final EStructuralFeature eStructuralFeature;
/*      */     protected final Object value;
/*      */     
/*      */     public EntryImpl(EStructuralFeature eStructuralFeature, Object value) {
/*  221 */       this.eStructuralFeature = eStructuralFeature;
/*  222 */       this.value = value;
/*  223 */       validate();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void validate() {
/*  228 */       if (this.value != null && !this.eStructuralFeature.getEType().isInstance(this.value)) {
/*      */         
/*  230 */         String valueClass = (this.value instanceof EObject) ? ((EObject)this.value).eClass().getName() : this.value.getClass().getName();
/*      */         
/*  232 */         throw new ClassCastException(
/*  233 */             "The feature '" + this.eStructuralFeature.getName() + "'s type '" + 
/*  234 */             this.eStructuralFeature.getEType().getName() + "' does not permit a value of type '" + valueClass + "'");
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature() {
/*  240 */       return this.eStructuralFeature;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getValue() {
/*  245 */       return this.value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object that) {
/*  251 */       if (this == that)
/*      */       {
/*  253 */         return true;
/*      */       }
/*  255 */       if (!(that instanceof FeatureMap.Entry))
/*      */       {
/*  257 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  261 */       FeatureMap.Entry entry = (FeatureMap.Entry)that;
/*      */       
/*  263 */       if (entry.getEStructuralFeature() == this.eStructuralFeature && (
/*  264 */         (this.value == null) ? (entry.getValue() == null) : this.value.equals(entry.getValue()))) return true;
/*      */       
/*      */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  271 */       return this.eStructuralFeature.hashCode() ^ ((this.value == null) ? 0 : this.value.hashCode());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  277 */       String prefix = this.eStructuralFeature.getEContainingClass().getEPackage().getNsPrefix();
/*  278 */       this.eStructuralFeature.getName();
/*  279 */       return 
/*      */ 
/*      */         
/*  282 */         String.valueOf((prefix != null && prefix.length() != 0) ? (String.valueOf(prefix) + ":" + this.eStructuralFeature.getName()) : this.eStructuralFeature.getName()) + 
/*  283 */         "=" + this.value;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class BasicFeatureEIterator<E>
/*      */     implements ListIterator<E>
/*      */   {
/*      */     protected final EStructuralFeature eStructuralFeature;
/*      */     
/*      */     protected final FeatureMap.Internal featureMap;
/*      */     
/*      */     protected int entryCursor;
/*      */     
/*      */     protected int cursor;
/*      */     
/*      */     protected int prepared;
/*      */     
/*      */     protected E preparedResult;
/*      */     
/*      */     protected int expectedModCount;
/*      */     
/*      */     protected int lastCursor;
/*      */     
/*      */     protected boolean isFeatureMap;
/*      */     
/*      */     protected FeatureMapUtil.Validator validator;
/*      */     
/*      */     public BasicFeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap) {
/*  312 */       this.eStructuralFeature = eStructuralFeature;
/*  313 */       this.featureMap = featureMap;
/*  314 */       this.expectedModCount = featureMap.getModCount();
/*  315 */       this.isFeatureMap = FeatureMapUtil.isFeatureMap(eStructuralFeature);
/*  316 */       this.validator = FeatureMapUtil.getValidator(featureMap.getEObject().eClass(), eStructuralFeature);
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean resolve() {
/*  321 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected E extractValue(FeatureMap.Entry entry) {
/*  327 */       return this.isFeatureMap ? (E)entry : (E)entry.getValue();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/*  332 */       switch (this.prepared) {
/*      */ 
/*      */         
/*      */         case 2:
/*  336 */           return true;
/*      */ 
/*      */         
/*      */         case 1:
/*  340 */           return false;
/*      */ 
/*      */         
/*      */         case -1:
/*  344 */           this.entryCursor++;
/*      */           break;
/*      */       } 
/*      */       
/*  348 */       return scanNext();
/*      */     }
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
/*      */     protected abstract boolean scanNext();
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
/*      */     public E next() {
/*  378 */       if (hasNext()) {
/*      */         
/*  380 */         checkModCount();
/*      */         
/*  382 */         if (resolve()) {
/*      */           
/*  384 */           E newPreparedResult = (E)this.featureMap.resolveProxy(this.eStructuralFeature, this.entryCursor, this.cursor, this.preparedResult);
/*  385 */           this.preparedResult = newPreparedResult;
/*      */         } 
/*      */         
/*  388 */         this.lastCursor = this.cursor;
/*  389 */         this.cursor++;
/*      */         
/*  391 */         this.entryCursor++;
/*  392 */         this.prepared = 0;
/*  393 */         return this.preparedResult;
/*      */       } 
/*      */ 
/*      */       
/*  397 */       throw new NoSuchElementException();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int nextIndex() {
/*  403 */       return this.cursor;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasPrevious() {
/*  408 */       switch (this.prepared) {
/*      */ 
/*      */         
/*      */         case -2:
/*  412 */           return true;
/*      */ 
/*      */         
/*      */         case -1:
/*  416 */           return false;
/*      */ 
/*      */         
/*      */         case 1:
/*  420 */           this.entryCursor--;
/*      */           break;
/*      */       } 
/*      */       
/*  424 */       return scanPrevious();
/*      */     }
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
/*      */     protected abstract boolean scanPrevious();
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
/*      */     public E previous() {
/*  452 */       if (hasPrevious()) {
/*      */         
/*  454 */         checkModCount();
/*  455 */         this.lastCursor = --this.cursor;
/*  456 */         if (resolve()) {
/*      */           
/*  458 */           E newPreparedResult = (E)this.featureMap.resolveProxy(this.eStructuralFeature, this.entryCursor, this.cursor, this.preparedResult);
/*  459 */           this.preparedResult = newPreparedResult;
/*      */         } 
/*      */         
/*  462 */         this.prepared = 0;
/*  463 */         return this.preparedResult;
/*      */       } 
/*      */ 
/*      */       
/*  467 */       throw new NoSuchElementException();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int previousIndex() {
/*  473 */       return this.cursor - 1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void add(Object o) {
/*  478 */       if (this.lastCursor == -1)
/*      */       {
/*  480 */         throw new IllegalStateException();
/*      */       }
/*  482 */       checkModCount();
/*      */ 
/*      */       
/*      */       try {
/*  486 */         this.featureMap.add(this.eStructuralFeature, this.cursor, o);
/*  487 */         this.expectedModCount = this.featureMap.getModCount();
/*  488 */         next();
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
/*      */       }
/*  501 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  503 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void remove() {
/*  509 */       if (this.lastCursor == -1)
/*      */       {
/*  511 */         throw new IllegalStateException();
/*      */       }
/*  513 */       checkModCount();
/*      */ 
/*      */       
/*      */       try {
/*  517 */         this.featureMap.remove(this.eStructuralFeature, this.lastCursor);
/*  518 */         this.expectedModCount = this.featureMap.getModCount();
/*  519 */         if (this.lastCursor < this.cursor) {
/*      */           
/*  521 */           this.cursor--;
/*  522 */           this.entryCursor--;
/*      */         } 
/*      */         
/*  525 */         this.lastCursor--;
/*      */ 
/*      */       
/*      */       }
/*  529 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  531 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void set(Object o) {
/*  537 */       if (this.lastCursor == -1)
/*      */       {
/*  539 */         throw new IllegalStateException();
/*      */       }
/*  541 */       checkModCount();
/*      */ 
/*      */       
/*      */       try {
/*  545 */         this.featureMap.set(this.eStructuralFeature, this.lastCursor, o);
/*  546 */         this.expectedModCount = this.featureMap.getModCount();
/*      */       }
/*  548 */       catch (IndexOutOfBoundsException exception) {
/*      */         
/*  550 */         throw new ConcurrentModificationException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void checkModCount() {
/*  560 */       if (this.featureMap.getModCount() != this.expectedModCount)
/*      */       {
/*  562 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class FeatureEList<E>
/*      */     extends AbstractList<E>
/*      */     implements InternalEList.Unsettable<E>, EStructuralFeature.Setting {
/*      */     protected EStructuralFeature feature;
/*      */     protected FeatureMap.Internal featureMap;
/*      */     
/*      */     public static class Basic<E> extends FeatureEList<E> {
/*      */       public Basic(EStructuralFeature feature, FeatureMap.Internal featureMap) {
/*  575 */         super(feature, featureMap);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Iterator<E> iterator() {
/*  581 */         return basicIterator();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public ListIterator<E> listIterator() {
/*  587 */         return basicListIterator();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public ListIterator<E> listIterator(int index) {
/*  593 */         return basicListIterator(index);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public List<E> basicList() {
/*  599 */         return this;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureEList(EStructuralFeature feature, FeatureMap.Internal featureMap) {
/*  608 */       this.feature = feature;
/*  609 */       this.featureMap = featureMap;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int size() {
/*  615 */       return this.featureMap.size(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isEmpty() {
/*  621 */       return this.featureMap.isEmpty(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean contains(Object object) {
/*  627 */       return this.featureMap.contains(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int indexOf(Object object) {
/*  633 */       return this.featureMap.indexOf(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int lastIndexOf(Object object) {
/*  639 */       return this.featureMap.lastIndexOf(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  645 */       return this.featureMap.containsAll(getEStructuralFeature(), collection);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<E> iterator() {
/*  652 */       return this.featureMap.iterator(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> listIterator() {
/*  659 */       return this.featureMap.listIterator(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> listIterator(int index) {
/*  666 */       return this.featureMap.listIterator(getEStructuralFeature(), index);
/*      */     }
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
/*      */     public E basicGet(int index) {
/*  679 */       return (E)this.featureMap.get(getEStructuralFeature(), index, false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public List<E> basicList() {
/*  685 */       return this.featureMap.basicList(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<E> basicIterator() {
/*  691 */       return this.featureMap.basicIterator(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> basicListIterator() {
/*  697 */       return this.featureMap.basicListIterator(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public ListIterator<E> basicListIterator(int index) {
/*  703 */       return this.featureMap.basicListIterator(getEStructuralFeature(), index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Object[] toArray() {
/*  709 */       return this.featureMap.toArray(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public <T> T[] toArray(Object[] array) {
/*  715 */       return this.featureMap.toArray(getEStructuralFeature(), (T[])array);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean basicContains(Object object) {
/*  720 */       return this.featureMap.basicContains(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean basicContainsAll(Collection<?> collection) {
/*  725 */       return this.featureMap.basicContainsAll(getEStructuralFeature(), collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public int basicIndexOf(Object object) {
/*  730 */       return this.featureMap.basicIndexOf(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */     
/*      */     public int basicLastIndexOf(Object object) {
/*  735 */       return this.featureMap.basicLastIndexOf(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] basicToArray() {
/*  740 */       return this.featureMap.basicToArray(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */     
/*      */     public <T> T[] basicToArray(Object[] array) {
/*  745 */       return this.featureMap.basicToArray(getEStructuralFeature(), (T[])array);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean add(Object object) {
/*  751 */       return this.featureMap.add(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(int index, Object object) {
/*  757 */       this.featureMap.add(getEStructuralFeature(), index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(Collection<? extends E> collection) {
/*  763 */       return this.featureMap.addAll(getEStructuralFeature(), collection);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(int index, Collection<? extends E> collection) {
/*  769 */       return this.featureMap.addAll(getEStructuralFeature(), index, collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public void addUnique(Object object) {
/*  774 */       this.featureMap.addUnique(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */     
/*      */     public void addUnique(int index, Object object) {
/*  779 */       this.featureMap.addUnique(getEStructuralFeature(), index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAllUnique(Collection<? extends E> collection) {
/*  785 */       this.modCount = -1;
/*  786 */       return this.featureMap.addAllUnique((Collection)collection);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAllUnique(int index, Collection<? extends E> collection) {
/*  792 */       this.modCount = -1;
/*  793 */       return this.featureMap.addAllUnique(index, (Collection)collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public void addUnique(FeatureMap.Entry.Internal entry) {
/*  798 */       this.modCount = -1;
/*  799 */       this.featureMap.addUnique(entry);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addAllUnique(FeatureMap.Entry.Internal[] entries, int start, int end) {
/*  804 */       return addAllUnique(size(), entries, start, end);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addAllUnique(int index, FeatureMap.Entry.Internal[] entries, int start, int end) {
/*  809 */       this.modCount = -1;
/*  810 */       BasicEList<FeatureMap.Entry.Internal> collection = new BasicEList();
/*  811 */       if (start == 0) {
/*      */         
/*  813 */         collection.setData(end, (Object[])entries);
/*      */       }
/*      */       else {
/*      */         
/*  817 */         collection.grow(end - start);
/*  818 */         for (int i = start; i < end; i++)
/*      */         {
/*  820 */           collection.add(entries[i]);
/*      */         }
/*      */       } 
/*  823 */       return this.featureMap.addAllUnique(index, (Collection)collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public NotificationChain basicAdd(E object, NotificationChain notifications) {
/*  828 */       return this.featureMap.basicAdd(getEStructuralFeature(), object, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean remove(Object object) {
/*  834 */       return this.featureMap.remove(getEStructuralFeature(), object);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E remove(int index) {
/*  841 */       return (E)this.featureMap.remove(getEStructuralFeature(), index);
/*      */     }
/*      */ 
/*      */     
/*      */     public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/*  846 */       return this.featureMap.basicRemove(getEStructuralFeature(), object, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  852 */       return this.featureMap.removeAll(getEStructuralFeature(), collection);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  858 */       return this.featureMap.retainAll(getEStructuralFeature(), collection);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void clear() {
/*  864 */       this.featureMap.clear(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */     
/*      */     public void move(int index, Object object) {
/*  869 */       this.featureMap.move(getEStructuralFeature(), index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public E move(int targetIndex, int sourceIndex) {
/*  875 */       return (E)this.featureMap.move(getEStructuralFeature(), targetIndex, sourceIndex);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E get(int index) {
/*  882 */       return (E)this.featureMap.get(getEStructuralFeature(), index, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E set(int index, E object) {
/*  889 */       return (E)this.featureMap.set(getEStructuralFeature(), index, object);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public E setUnique(int index, E object) {
/*  895 */       return (E)this.featureMap.setUnique(getEStructuralFeature(), index, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(boolean resolve) {
/*  900 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void set(Object newValue) {
/*  906 */       clear();
/*  907 */       addAll((List)newValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isSet() {
/*  912 */       return !isEmpty();
/*      */     }
/*      */ 
/*      */     
/*      */     public void unset() {
/*  917 */       clear();
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getFeature() {
/*  922 */       return getEStructuralFeature();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getFeatureID() {
/*  927 */       return this.featureMap.getEObject().eClass().getFeatureID(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature() {
/*  932 */       return this.feature;
/*      */     }
/*      */ 
/*      */     
/*      */     public EObject getEObject() {
/*  937 */       return this.featureMap.getEObject();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  943 */       StringBuffer stringBuffer = new StringBuffer();
/*  944 */       stringBuffer.append("[");
/*  945 */       for (Iterator<E> i = basicIterator(); i.hasNext(); ) {
/*      */         
/*  947 */         stringBuffer.append(String.valueOf(i.next()));
/*  948 */         if (i.hasNext())
/*      */         {
/*  950 */           stringBuffer.append(", ");
/*      */         }
/*      */       } 
/*  953 */       stringBuffer.append("]");
/*  954 */       return stringBuffer.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class FeatureFeatureMap
/*      */     extends FeatureEList<FeatureMap.Entry> implements FeatureMap.Internal, FeatureMap.Internal.Wrapper {
/*  960 */     protected FeatureMap.Internal.Wrapper wrapper = this;
/*      */ 
/*      */     
/*      */     public FeatureFeatureMap(EStructuralFeature feature, FeatureMap.Internal featureMap) {
/*  964 */       super(feature, featureMap);
/*      */     }
/*      */ 
/*      */     
/*      */     public FeatureMap.ValueListIterator<Object> valueListIterator() {
/*  969 */       return this.featureMap.valueListIterator();
/*      */     }
/*      */ 
/*      */     
/*      */     public FeatureMap.ValueListIterator<Object> valueListIterator(int index) {
/*  974 */       return this.featureMap.valueListIterator(index);
/*      */     }
/*      */ 
/*      */     
/*      */     public <T> EList<T> list(EStructuralFeature feature) {
/*  979 */       return this.featureMap.list(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature(int index) {
/*  984 */       return ((FeatureMap.Entry)this.featureMap.get(getEStructuralFeature(), index, false)).getEStructuralFeature();
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getValue(int index) {
/*  989 */       return ((FeatureMap.Entry)this.featureMap.get(getEStructuralFeature(), index, false)).getValue();
/*      */     }
/*      */ 
/*      */     
/*      */     public Object setValue(int index, Object value) {
/*  994 */       FeatureMap.Entry entry = (FeatureMap.Entry)this.featureMap.get(getEStructuralFeature(), index, false);
/*  995 */       set(index, FeatureMapUtil.createEntry(entry.getEStructuralFeature(), value));
/*  996 */       return entry.getValue();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean add(EStructuralFeature feature, Object value) {
/* 1001 */       return this.featureMap.add(feature, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void add(int index, EStructuralFeature feature, Object value) {
/* 1006 */       add(index, FeatureMapUtil.isFeatureMap(feature) ? value : FeatureMapUtil.createEntry(feature, value));
/*      */     }
/*      */ 
/*      */     
/*      */     public void add(EStructuralFeature feature, int index, Object value) {
/* 1011 */       this.featureMap.add(feature, index, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addAll(EStructuralFeature feature, Collection<?> values) {
/* 1016 */       return this.featureMap.addAll(feature, values);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addAll(int index, EStructuralFeature feature, Collection<?> values) {
/* 1021 */       if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */         
/* 1023 */         Collection<? extends FeatureMap.Entry> entryValues = (Collection)values;
/* 1024 */         return addAll(index, entryValues);
/*      */       } 
/*      */ 
/*      */       
/* 1028 */       Collection<FeatureMap.Entry> entries = new ArrayList<FeatureMap.Entry>(values.size());
/* 1029 */       for (Object value : values)
/*      */       {
/* 1031 */         entries.add(FeatureMapUtil.createEntry(feature, value));
/*      */       }
/* 1033 */       return addAll(index, entries);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addAll(EStructuralFeature feature, int index, Collection<?> values) {
/* 1039 */       return this.featureMap.addAll(feature, index, values);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getModCount() {
/* 1044 */       return this.featureMap.getModCount();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EObject getEObject() {
/* 1050 */       return this.featureMap.getEObject();
/*      */     }
/*      */ 
/*      */     
/*      */     public Object resolveProxy(EStructuralFeature feature, int entryIndex, int index, Object object) {
/* 1055 */       return this.featureMap.resolveProxy(feature, entryIndex, index, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public int size(EStructuralFeature feature) {
/* 1060 */       return this.featureMap.size(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isEmpty(EStructuralFeature feature) {
/* 1065 */       return this.featureMap.isEmpty(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean contains(EStructuralFeature feature, Object object) {
/* 1070 */       return this.featureMap.contains(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean containsAll(EStructuralFeature feature, Collection<?> collection) {
/* 1075 */       return this.featureMap.containsAll(feature, collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public int indexOf(EStructuralFeature feature, Object object) {
/* 1080 */       return this.featureMap.indexOf(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public int lastIndexOf(EStructuralFeature feature, Object object) {
/* 1085 */       return this.featureMap.lastIndexOf(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Iterator<Object> iterator(EStructuralFeature feature) {
/* 1090 */       return this.featureMap.iterator(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public ListIterator<Object> listIterator(EStructuralFeature feature) {
/* 1095 */       return this.featureMap.listIterator(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public ListIterator<Object> listIterator(EStructuralFeature feature, int index) {
/* 1100 */       return this.featureMap.listIterator(feature, index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EStructuralFeature.Setting setting(EStructuralFeature feature) {
/* 1107 */       return this.featureMap.setting(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public List<Object> basicList(EStructuralFeature feature) {
/* 1112 */       return this.featureMap.basicList(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public Iterator<Object> basicIterator(EStructuralFeature feature) {
/* 1117 */       return this.featureMap.basicIterator(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public ListIterator<Object> basicListIterator(EStructuralFeature feature) {
/* 1122 */       return this.featureMap.basicListIterator(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public ListIterator<Object> basicListIterator(EStructuralFeature feature, int index) {
/* 1127 */       return this.featureMap.basicListIterator(feature, index);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] toArray(EStructuralFeature feature) {
/* 1132 */       return this.featureMap.toArray(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public <T> T[] toArray(EStructuralFeature feature, Object[] array) {
/* 1137 */       return this.featureMap.toArray(feature, (T[])array);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean basicContains(EStructuralFeature feature, Object object) {
/* 1142 */       return this.featureMap.basicContains(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean basicContainsAll(EStructuralFeature feature, Collection<?> collection) {
/* 1147 */       return this.featureMap.basicContainsAll(feature, collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public int basicIndexOf(EStructuralFeature feature, Object object) {
/* 1152 */       return this.featureMap.basicIndexOf(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public int basicLastIndexOf(EStructuralFeature feature, Object object) {
/* 1157 */       return this.featureMap.basicLastIndexOf(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] basicToArray(EStructuralFeature feature) {
/* 1162 */       return this.featureMap.basicToArray(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public <T> T[] basicToArray(EStructuralFeature feature, Object[] array) {
/* 1167 */       return this.featureMap.basicToArray(feature, (T[])array);
/*      */     }
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
/*      */     public void addUnique(EStructuralFeature feature, Object object) {
/* 1194 */       this.featureMap.addUnique(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public void addUnique(EStructuralFeature feature, int index, Object object) {
/* 1199 */       this.featureMap.addUnique(feature, index, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public NotificationChain basicAdd(EStructuralFeature feature, Object object, NotificationChain notifications) {
/* 1204 */       return this.featureMap.basicAdd(feature, object, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean remove(EStructuralFeature feature, Object object) {
/* 1209 */       return this.featureMap.remove(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object remove(EStructuralFeature feature, int index) {
/* 1214 */       return this.featureMap.remove(feature, index);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean removeAll(EStructuralFeature feature, Collection<?> collection) {
/* 1219 */       return this.featureMap.removeAll(feature, collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public NotificationChain basicRemove(EStructuralFeature feature, Object object, NotificationChain notifications) {
/* 1224 */       return this.featureMap.basicRemove(feature, object, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean retainAll(EStructuralFeature feature, Collection<?> collection) {
/* 1229 */       return this.featureMap.retainAll(feature, collection);
/*      */     }
/*      */ 
/*      */     
/*      */     public void clear(EStructuralFeature feature) {
/* 1234 */       this.featureMap.clear(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public void move(EStructuralFeature feature, int index, Object object) {
/* 1239 */       this.featureMap.move(feature, index, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object move(EStructuralFeature feature, int targetIndex, int sourceIndex) {
/* 1244 */       return this.featureMap.move(feature, targetIndex, sourceIndex);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(EStructuralFeature feature, boolean resolve) {
/* 1249 */       return this.featureMap.get(feature, resolve);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(EStructuralFeature feature, int index, boolean resolve) {
/* 1254 */       return this.featureMap.get(feature, index, resolve);
/*      */     }
/*      */ 
/*      */     
/*      */     public void set(EStructuralFeature feature, Object object) {
/* 1259 */       this.featureMap.set(feature, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object set(EStructuralFeature feature, int index, Object object) {
/* 1264 */       return this.featureMap.set(feature, index, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object setUnique(EStructuralFeature feature, int index, Object object) {
/* 1269 */       return this.featureMap.setUnique(feature, index, object);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isSet(EStructuralFeature feature) {
/* 1274 */       return this.featureMap.isSet(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public void unset(EStructuralFeature feature) {
/* 1279 */       this.featureMap.unset(feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public FeatureMap.Internal.Wrapper getWrapper() {
/* 1284 */       return this.wrapper;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setWrapper(FeatureMap.Internal.Wrapper wrapper) {
/* 1289 */       this.wrapper = wrapper;
/*      */     }
/*      */ 
/*      */     
/*      */     public FeatureMap featureMap() {
/* 1294 */       return this;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class FeatureValue
/*      */     implements EStructuralFeature.Setting
/*      */   {
/*      */     protected EStructuralFeature feature;
/*      */     protected FeatureMap.Internal featureMap;
/*      */     
/*      */     public FeatureValue(EStructuralFeature feature, FeatureMap.Internal featureMap) {
/* 1305 */       this.feature = feature;
/* 1306 */       this.featureMap = featureMap;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(boolean resolve) {
/* 1311 */       return this.featureMap.get(getEStructuralFeature(), -1, resolve);
/*      */     }
/*      */ 
/*      */     
/*      */     public void set(Object newValue) {
/* 1316 */       this.featureMap.set(getEStructuralFeature(), newValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isSet() {
/* 1321 */       return !this.featureMap.isEmpty(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */     
/*      */     public void unset() {
/* 1326 */       this.featureMap.clear(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getFeature() {
/* 1331 */       return getEStructuralFeature();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getFeatureID() {
/* 1336 */       return this.featureMap.getEObject().eClass().getFeatureID(getEStructuralFeature());
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature() {
/* 1341 */       return this.feature;
/*      */     }
/*      */ 
/*      */     
/*      */     public EObject getEObject() {
/* 1346 */       return this.featureMap.getEObject();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class FeatureENotificationImpl
/*      */     extends ENotificationImpl
/*      */   {
/*      */     public FeatureENotificationImpl(InternalEObject owner, int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index) {
/* 1355 */       super(owner, eventType, feature, oldObject, newObject, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureENotificationImpl(InternalEObject owner, int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index, boolean wasSet) {
/* 1361 */       super(owner, eventType, feature, oldObject, newObject, index, wasSet);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getFeatureID(Class<?> expectedClass) {
/* 1367 */       if (this.featureID == -1 && this.feature != null) {
/*      */         
/* 1369 */         Class<?> containerClass = this.feature.getContainerClass();
/* 1370 */         this.featureID = (containerClass == null) ? 
/* 1371 */           this.notifier.eClass().getFeatureID(this.feature) : 
/* 1372 */           this.notifier.eDerivedStructuralFeatureID(this.feature.getFeatureID(), containerClass);
/*      */       } 
/* 1374 */       return this.notifier.eBaseStructuralFeatureID(this.featureID, expectedClass);
/*      */     }
/*      */     public boolean merge(Notification notification) {
/*      */       Object notificationNotifier;
/*      */       int notificationEventType;
/*      */       Object object1;
/* 1380 */       switch (this.eventType) {
/*      */ 
/*      */         
/*      */         case 1:
/*      */         case 2:
/* 1385 */           notificationNotifier = notification.getNotifier();
/* 1386 */           if (notificationNotifier == getNotifier() && getFeatureID((Class<?>)null) == notification.getFeatureID(null)) {
/*      */             
/* 1388 */             this.newValue = notification.getNewValue();
/* 1389 */             if (notification.getEventType() == 1)
/*      */             {
/* 1391 */               this.eventType = 1;
/*      */             }
/* 1393 */             return true;
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/* 1399 */           notificationEventType = notification.getEventType();
/* 1400 */           switch (notificationEventType) {
/*      */ 
/*      */             
/*      */             case 3:
/* 1404 */               object1 = notification.getNotifier();
/* 1405 */               if (object1 == getNotifier() && getFeatureID((Class<?>)null) == notification.getFeatureID(null)) {
/*      */                 
/* 1407 */                 this.eventType = 5;
/* 1408 */                 BasicEList<Object> addedValues = new BasicEList(2);
/* 1409 */                 addedValues.add(this.newValue);
/* 1410 */                 addedValues.add(notification.getNewValue());
/* 1411 */                 this.newValue = addedValues;
/* 1412 */                 return true;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */           
/*      */           break;
/*      */         
/*      */         case 5:
/* 1421 */           notificationEventType = notification.getEventType();
/* 1422 */           switch (notificationEventType) {
/*      */ 
/*      */             
/*      */             case 3:
/* 1426 */               object1 = notification.getNotifier();
/* 1427 */               if (object1 == getNotifier() && getFeatureID((Class<?>)null) == notification.getFeatureID(null)) {
/*      */                 
/* 1429 */                 Collection<Object> collection = (Collection<Object>)this.newValue;
/* 1430 */                 collection.add(notification.getNewValue());
/* 1431 */                 return true;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */           
/*      */           break;
/*      */         
/*      */         case 4:
/* 1440 */           notificationEventType = notification.getEventType();
/* 1441 */           switch (notificationEventType) {
/*      */ 
/*      */             
/*      */             case 3:
/* 1445 */               object1 = notification.getNotifier();
/* 1446 */               if (object1 == getNotifier() && getFeatureID((Class<?>)null) == notification.getFeatureID(null)) {
/*      */                 
/* 1448 */                 this.eventType = 1;
/* 1449 */                 this.newValue = notification.getNewValue();
/* 1450 */                 return true;
/*      */               } 
/*      */               break;
/*      */ 
/*      */             
/*      */             case 4:
/* 1456 */               object1 = notification.getNotifier();
/* 1457 */               if (object1 == getNotifier() && getFeatureID((Class<?>)null) == notification.getFeatureID(null)) {
/*      */                 
/* 1459 */                 this.eventType = 6;
/* 1460 */                 BasicEList<Object> removedValues = new BasicEList(2);
/* 1461 */                 removedValues.add(this.oldValue);
/* 1462 */                 removedValues.add(notification.getOldValue());
/* 1463 */                 this.oldValue = removedValues;
/*      */                 
/* 1465 */                 int[] positions = { this.position, notification.getPosition() };
/* 1466 */                 this.newValue = positions;
/* 1467 */                 return true;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */           
/*      */           break;
/*      */         
/*      */         case 6:
/* 1476 */           notificationEventType = notification.getEventType();
/* 1477 */           switch (notificationEventType) {
/*      */ 
/*      */             
/*      */             case 4:
/* 1481 */               object1 = notification.getNotifier();
/* 1482 */               if (object1 == getNotifier() && getFeatureID((Class<?>)null) == notification.getFeatureID(null)) {
/*      */                 
/* 1484 */                 Collection<Object> collection = (Collection<Object>)this.oldValue;
/* 1485 */                 collection.add(notification.getOldValue());
/*      */                 
/* 1487 */                 int[] positions = (int[])this.newValue;
/* 1488 */                 int[] newPositions = new int[positions.length + 1];
/*      */                 
/* 1490 */                 System.arraycopy(positions, 0, newPositions, 0, positions.length);
/* 1491 */                 newPositions[positions.length] = notification.getPosition();
/* 1492 */                 this.newValue = newPositions;
/*      */                 
/* 1494 */                 return true;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */           
/*      */           break;
/*      */       } 
/*      */       
/* 1503 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BasicValidator
/*      */     implements Validator
/*      */   {
/* 1514 */     protected static final List<String> ANY_WILDCARD = Collections.singletonList("##any");
/*      */     
/*      */     protected EClass containingClass;
/*      */     protected EStructuralFeature eStructuralFeature;
/*      */     protected List<EStructuralFeature> groupMembers;
/*      */     protected List<String> wildcards;
/*      */     protected String name;
/*      */     protected boolean isElement;
/*      */     
/*      */     protected class Cache
/*      */       extends WeakHashMap<Object, Object>
/*      */     {
/*      */       public Boolean get(EStructuralFeature eStructuralFeature) {
/* 1527 */         return (Boolean)get(eStructuralFeature);
/*      */       }
/*      */ 
/*      */       
/*      */       public void put(EStructuralFeature eStructuralFeature, Boolean isValid) {
/* 1532 */         Cache newCache = new Cache();
/* 1533 */         newCache.putAll(FeatureMapUtil.BasicValidator.this.cache);
/* 1534 */         newCache.put(eStructuralFeature, isValid);
/* 1535 */         FeatureMapUtil.BasicValidator.this.cache = newCache;
/*      */       }
/*      */     }
/*      */     
/* 1539 */     protected Cache cache = new Cache();
/*      */ 
/*      */     
/*      */     public BasicValidator(EClass containingClass, EStructuralFeature eStructuralFeature) {
/* 1543 */       this.containingClass = containingClass;
/* 1544 */       this.eStructuralFeature = eStructuralFeature;
/*      */       
/* 1546 */       this.wildcards = ExtendedMetaData.INSTANCE.getWildcards(eStructuralFeature);
/*      */       
/* 1548 */       if (!this.wildcards.isEmpty()) {
/*      */         
/* 1550 */         this.isElement = (ExtendedMetaData.INSTANCE.getFeatureKind(eStructuralFeature) == 5);
/* 1551 */         if (this.wildcards.equals(ANY_WILDCARD))
/*      */         {
/* 1553 */           this.wildcards = ANY_WILDCARD; } 
/*      */       } else {
/*      */         EAttribute eAttribute;
/* 1556 */         if ((eAttribute = ExtendedMetaData.INSTANCE.getMixedFeature(containingClass)) == eStructuralFeature) {
/*      */           
/* 1558 */           this.isElement = true;
/* 1559 */           this.groupMembers = new ArrayList<EStructuralFeature>();
/* 1560 */           this.wildcards = (List<String>)new UniqueEList();
/* 1561 */           this.wildcards.add("http://www.eclipse.org/emf/2003/XMLType");
/* 1562 */           if (ExtendedMetaData.INSTANCE.getDocumentRoot(containingClass.getEPackage()) == containingClass)
/*      */           {
/* 1564 */             this.wildcards.add(ExtendedMetaData.INSTANCE.getNamespace((EClassifier)containingClass));
/*      */           }
/* 1566 */           for (EStructuralFeature feature : ExtendedMetaData.INSTANCE.getAllElements(containingClass)) {
/*      */             
/* 1568 */             switch (ExtendedMetaData.INSTANCE.getFeatureKind(feature)) {
/*      */ 
/*      */               
/*      */               case 4:
/* 1572 */                 this.groupMembers.add(feature);
/*      */ 
/*      */ 
/*      */               
/*      */               case 5:
/* 1577 */                 this.wildcards.addAll(ExtendedMetaData.INSTANCE.getWildcards(feature));
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           } 
/* 1583 */         } else if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
/*      */           
/* 1585 */           this.isElement = true;
/* 1586 */           this.wildcards = null;
/* 1587 */           this.groupMembers = new ArrayList<EStructuralFeature>();
/* 1588 */           for (int i = 0, size = containingClass.getFeatureCount(); i < size; i++) {
/*      */             
/* 1590 */             EStructuralFeature feature = containingClass.getEStructuralFeature(i);
/* 1591 */             EStructuralFeature group = ExtendedMetaData.INSTANCE.getGroup(feature);
/* 1592 */             for (; group != null; 
/* 1593 */               group = ExtendedMetaData.INSTANCE.getGroup(group))
/*      */             {
/* 1595 */               if (group == eStructuralFeature)
/*      */               {
/* 1597 */                 this.groupMembers.add(feature);
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/* 1602 */         } else if (ExtendedMetaData.INSTANCE.getFeatureKind(eStructuralFeature) == 1 && 
/* 1603 */           eAttribute != null) {
/*      */           
/* 1605 */           this.wildcards = null;
/* 1606 */           this.groupMembers = XMLTypeFeatures.TEXTUAL_FEATURES;
/*      */         }
/*      */         else {
/*      */           
/* 1610 */           this.wildcards = null;
/* 1611 */           this.isElement = true;
/* 1612 */           this.groupMembers = Collections.singletonList(eStructuralFeature);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean isValid(EStructuralFeature feature) {
/* 1618 */       if (this.eStructuralFeature == feature) return true;
/*      */       
/* 1620 */       Boolean result = this.cache.get(feature);
/* 1621 */       if (result == null) {
/*      */         
/* 1623 */         if (isIncluded(feature)) {
/*      */           
/* 1625 */           this.cache.put(feature, Boolean.TRUE);
/* 1626 */           return true;
/*      */         } 
/*      */ 
/*      */         
/* 1630 */         this.cache.put(feature, Boolean.FALSE);
/* 1631 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1636 */       return (result == Boolean.TRUE);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isIncluded(EStructuralFeature feature) {
/* 1642 */       if (this.wildcards == ANY_WILDCARD) {
/*      */         
/* 1644 */         int featureKind = ExtendedMetaData.INSTANCE.getFeatureKind(feature);
/* 1645 */         return 
/* 1646 */           this.isElement ? (
/* 1647 */           (featureKind == 4 && 
/* 1648 */           feature != XMLTypeFeatures.TEXT && feature != XMLTypeFeatures.CDATA && feature != XMLTypeFeatures.COMMENT && feature != XMLTypeFeatures.PROCESSING_INSTRUCTION)) : (
/* 1649 */           (featureKind == 2));
/*      */       } 
/*      */       
/* 1652 */       if (this.groupMembers != null && (
/* 1653 */         this.groupMembers.contains(feature) || 
/* 1654 */         this.groupMembers.contains(ExtendedMetaData.INSTANCE.getGroup(feature)) || 
/* 1655 */         this.groupMembers.contains(ExtendedMetaData.INSTANCE.getAffiliation(this.containingClass, feature))))
/*      */       {
/* 1657 */         return true;
/*      */       }
/*      */       
/* 1660 */       if (this.wildcards != null)
/*      */       {
/* 1662 */         if (ExtendedMetaData.INSTANCE.matches(this.wildcards, ExtendedMetaData.INSTANCE.getNamespace(feature))) {
/*      */           
/* 1664 */           int featureKind = ExtendedMetaData.INSTANCE.getFeatureKind(feature);
/* 1665 */           return this.isElement ? ((featureKind == 4)) : ((featureKind == 2));
/*      */         } 
/*      */       }
/*      */       
/* 1669 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/* 1674 */   protected static Validator NULL_VALIDATOR = new Validator()
/*      */     {
/*      */       public boolean isValid(EStructuralFeature eStructuralFeature)
/*      */       {
/* 1678 */         return true;
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*      */   public static Validator getValidator(EClass containingClass, EStructuralFeature eStructuralFeature) {
/* 1684 */     if (eStructuralFeature == null)
/*      */     {
/* 1686 */       return NULL_VALIDATOR;
/*      */     }
/* 1688 */     if (eStructuralFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__MIXED || ((
/* 1689 */       eStructuralFeature == XMLTypePackage.Literals.ANY_TYPE__MIXED || 
/* 1690 */       eStructuralFeature == XMLTypePackage.Literals.ANY_TYPE__ANY || 
/* 1691 */       eStructuralFeature == XMLTypePackage.Literals.ANY_TYPE__ANY_ATTRIBUTE) && containingClass != XMLTypePackage.Literals.ANY_TYPE))
/*      */     {
/*      */ 
/*      */       
/* 1695 */       return new BasicValidator(containingClass, eStructuralFeature);
/*      */     }
/*      */ 
/*      */     
/* 1699 */     BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder holder = 
/* 1700 */       (BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder)eStructuralFeature;
/* 1701 */     BasicExtendedMetaData.EStructuralFeatureExtendedMetaData extendedMetaData = holder.getExtendedMetaData();
/* 1702 */     if (extendedMetaData == null) {
/*      */ 
/*      */ 
/*      */       
/* 1706 */       ExtendedMetaData.INSTANCE.getName(eStructuralFeature);
/* 1707 */       extendedMetaData = holder.getExtendedMetaData();
/*      */     } 
/* 1709 */     Map<EClass, Validator> validatorMap = extendedMetaData.getValidatorMap();
/* 1710 */     Validator result = validatorMap.get(containingClass);
/* 1711 */     if (result == null)
/*      */     {
/* 1713 */       validatorMap.put(containingClass, result = new BasicValidator(containingClass, eStructuralFeature));
/*      */     }
/* 1715 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isMany(EObject owner, EStructuralFeature feature) {
/* 1721 */     if (feature.isMany())
/*      */     {
/* 1723 */       return true;
/*      */     }
/* 1725 */     if (feature.getUpperBound() == -2) {
/*      */       
/* 1727 */       if (feature == XMLTypeFeatures.TEXT || 
/* 1728 */         feature == XMLTypeFeatures.CDATA || 
/* 1729 */         feature == XMLTypeFeatures.COMMENT || 
/* 1730 */         feature == XMLTypeFeatures.PROCESSING_INSTRUCTION)
/*      */       {
/* 1732 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 1736 */       EClass eClass = owner.eClass();
/* 1737 */       if (eClass.getFeatureID(feature) >= 0)
/*      */       {
/* 1739 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1743 */       EStructuralFeature affiliation = ExtendedMetaData.INSTANCE.getAffiliation(eClass, feature);
/* 1744 */       if (affiliation == null)
/*      */       {
/* 1746 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 1750 */       int affiliationUpperBound = affiliation.getUpperBound();
/*      */       
/* 1752 */       if ((affiliationUpperBound > 1 || affiliationUpperBound == -1) && 
/* 1753 */         ExtendedMetaData.INSTANCE.getFeatureKind(affiliation) != 3) return true;
/*      */ 
/*      */ 
/*      */       
/*      */       return false;
/*      */     } 
/*      */     
/* 1760 */     return false;
/*      */   }
/*      */   
/*      */   public static interface Validator {
/*      */     boolean isValid(EStructuralFeature param1EStructuralFeature);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\FeatureMapUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */