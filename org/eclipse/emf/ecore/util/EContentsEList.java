/*     */ package org.eclipse.emf.ecore.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.impl.EClassImpl;
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
/*     */ public class EContentsEList<E>
/*     */   extends AbstractSequentialInternalEList<E>
/*     */   implements EList<E>, InternalEList<E>
/*     */ {
/*  37 */   public static final EContentsEList<?> EMPTY_CONTENTS_ELIST = new EContentsEList<Object>(null, null)
/*     */     {
/*     */       
/*     */       public List<Object> basicList()
/*     */       {
/*  42 */         return this;
/*     */       }
/*     */     };
/*     */   
/*     */   protected final EObject eObject;
/*     */   
/*     */   public static <T> EContentsEList<T> emptyContentsEList() {
/*  49 */     return (EContentsEList)EMPTY_CONTENTS_ELIST;
/*     */   }
/*     */   protected final EStructuralFeature[] eStructuralFeatures;
/*     */   
/*     */   public static <T> EContentsEList<T> createEContentsEList(EObject eObject) {
/*  54 */     EStructuralFeature[] eStructuralFeatures = (
/*  55 */       (EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).containments();
/*     */     
/*  57 */     return 
/*  58 */       (eStructuralFeatures == null) ? 
/*  59 */       emptyContentsEList() : 
/*  60 */       new EContentsEList<T>(eObject, eStructuralFeatures);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EContentsEList(EObject eObject) {
/*  68 */     this.eObject = eObject;
/*  69 */     this.eStructuralFeatures = (
/*  70 */       (EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).containments();
/*     */   }
/*     */ 
/*     */   
/*     */   public EContentsEList(EObject eObject, List<? extends EStructuralFeature> eStructuralFeatures) {
/*  75 */     this.eObject = eObject;
/*  76 */     this.eStructuralFeatures = new EStructuralFeature[eStructuralFeatures.size()];
/*  77 */     eStructuralFeatures.toArray(this.eStructuralFeatures);
/*     */   }
/*     */ 
/*     */   
/*     */   public EContentsEList(EObject eObject, EStructuralFeature[] eStructuralFeatures) {
/*  82 */     this.eObject = eObject;
/*  83 */     this.eStructuralFeatures = eStructuralFeatures;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ListIterator<E> newListIterator() {
/*  88 */     return resolve() ? newResolvingListIterator() : newNonResolvingListIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ListIterator<E> newResolvingListIterator() {
/*  93 */     return new ResolvingFeatureIteratorImpl<E>(this.eObject, this.eStructuralFeatures);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ListIterator<E> newNonResolvingListIterator() {
/*  98 */     return new FeatureIteratorImpl<E>(this.eObject, this.eStructuralFeatures);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Iterator<E> newIterator() {
/* 103 */     return newListIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean useIsSet() {
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean resolve() {
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isIncluded(EStructuralFeature eStructuralFeature) {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature) {
/* 123 */     return (eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isContainment());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/* 129 */     if (this.eStructuralFeatures == null) {
/*     */       
/* 131 */       if (index != 0)
/*     */       {
/* 133 */         throw new IndexOutOfBoundsException("index=" + index + ", size=0");
/*     */       }
/*     */       
/* 136 */       return FeatureIteratorImpl.emptyIterator();
/*     */     } 
/*     */     
/* 139 */     ListIterator<E> result = newListIterator();
/* 140 */     for (int i = 0; i < index; i++)
/*     */     {
/* 142 */       result.next();
/*     */     }
/* 144 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/* 150 */     if (this.eStructuralFeatures == null)
/*     */     {
/* 152 */       return FeatureIteratorImpl.emptyIterator();
/*     */     }
/*     */     
/* 155 */     Iterator<E> result = newIterator();
/* 156 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 162 */     int result = 0;
/* 163 */     if (this.eStructuralFeatures != null)
/*     */     {
/* 165 */       for (int i = 0; i < this.eStructuralFeatures.length; i++) {
/*     */         
/* 167 */         EStructuralFeature feature = this.eStructuralFeatures[i];
/* 168 */         if (isIncluded(feature) && (!useIsSet() || this.eObject.eIsSet(feature))) {
/*     */           
/* 170 */           Object value = this.eObject.eGet(feature, false);
/* 171 */           if (FeatureMapUtil.isFeatureMap(feature)) {
/*     */             
/* 173 */             FeatureMap featureMap = (FeatureMap)value;
/* 174 */             for (int j = 0, size = featureMap.size(); j < size; j++)
/*     */             {
/* 176 */               if (isIncludedEntry(featureMap.getEStructuralFeature(j)) && featureMap.getValue(j) != null)
/*     */               {
/* 178 */                 result++;
/*     */               }
/*     */             }
/*     */           
/* 182 */           } else if (feature.isMany()) {
/*     */             
/* 184 */             result += ((Collection)value).size();
/*     */           }
/* 186 */           else if (value != null) {
/*     */             
/* 188 */             result++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 193 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 199 */     if (this.eStructuralFeatures != null)
/*     */     {
/* 201 */       for (int i = 0; i < this.eStructuralFeatures.length; i++) {
/*     */         
/* 203 */         EStructuralFeature feature = this.eStructuralFeatures[i];
/* 204 */         if (isIncluded(feature) && (!useIsSet() || this.eObject.eIsSet(feature))) {
/*     */           
/* 206 */           Object value = this.eObject.eGet(feature, false);
/* 207 */           if (FeatureMapUtil.isFeatureMap(feature)) {
/*     */             
/* 209 */             FeatureMap featureMap = (FeatureMap)value;
/* 210 */             for (int j = 0, size = featureMap.size(); j < size; j++)
/*     */             {
/* 212 */               if (isIncludedEntry(featureMap.getEStructuralFeature(j)) && featureMap.getValue(j) != null)
/*     */               {
/* 214 */                 return false;
/*     */               }
/*     */             }
/*     */           
/* 218 */           } else if (feature.isMany()) {
/*     */             
/* 220 */             if (!((Collection)value).isEmpty())
/*     */             {
/* 222 */               return false;
/*     */             }
/*     */           }
/* 225 */           else if (value != null) {
/*     */             
/* 227 */             return false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 232 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void move(int newPosition, Object o) {
/* 238 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public E move(int newPosition, int oldPosition) {
/* 244 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public E basicGet(int index) {
/* 250 */     return basicList().get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<E> basicList() {
/* 256 */     return 
/* 257 */       new EContentsEList<E>(this.eObject, this.eStructuralFeatures)
/*     */       {
/*     */         
/*     */         protected boolean resolve()
/*     */         {
/* 262 */           return false;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<E> basicIterator() {
/* 270 */     if (this.eStructuralFeatures == null)
/*     */     {
/* 272 */       return FeatureIteratorImpl.emptyIterator();
/*     */     }
/*     */     
/* 275 */     return newNonResolvingListIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator() {
/* 281 */     if (this.eStructuralFeatures == null)
/*     */     {
/* 283 */       return FeatureIteratorImpl.emptyIterator();
/*     */     }
/*     */     
/* 286 */     return newNonResolvingListIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<E> basicListIterator(int index) {
/* 292 */     if (this.eStructuralFeatures == null) {
/*     */       
/* 294 */       if (index < 0 || index > 1)
/*     */       {
/* 296 */         throw new IndexOutOfBoundsException("index=" + index + ", size=0");
/*     */       }
/*     */       
/* 299 */       return FeatureIteratorImpl.emptyIterator();
/*     */     } 
/*     */     
/* 302 */     ListIterator<E> result = newNonResolvingListIterator();
/* 303 */     for (int i = 0; i < index; i++)
/*     */     {
/* 305 */       result.next();
/*     */     }
/* 307 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class FeatureIteratorImpl<E>
/*     */     implements FeatureListIterator<E>
/*     */   {
/*     */     protected final EObject eObject;
/*     */     
/*     */     protected final EStructuralFeature[] eStructuralFeatures;
/*     */     
/*     */     protected int featureCursor;
/*     */     
/*     */     protected int cursor;
/*     */     
/*     */     protected int prepared;
/*     */     
/*     */     protected E preparedResult;
/*     */     
/*     */     protected EStructuralFeature preparedFeature;
/*     */     
/*     */     protected EStructuralFeature feature;
/*     */     
/*     */     protected boolean isHandlingFeatureMap;
/*     */     
/*     */     protected ListIterator<E> values;
/*     */     protected InternalEList<E> valueInternalEList;
/*     */     protected List<E> valueList;
/*     */     protected int valueListSize;
/*     */     protected int valueListIndex;
/*     */     
/*     */     public FeatureIteratorImpl(EObject eObject, List<? extends EStructuralFeature> eStructuralFeatures) {
/* 339 */       this.eObject = eObject;
/* 340 */       this.eStructuralFeatures = new EStructuralFeature[eStructuralFeatures.size()];
/* 341 */       eStructuralFeatures.toArray(this.eStructuralFeatures);
/*     */     }
/*     */ 
/*     */     
/*     */     public FeatureIteratorImpl(EObject eObject, EStructuralFeature[] eStructuralFeatures) {
/* 346 */       this.eObject = eObject;
/* 347 */       this.eStructuralFeatures = eStructuralFeatures;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean resolve() {
/* 352 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean useIsSet() {
/* 357 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isIncluded(EStructuralFeature eStructuralFeature) {
/* 362 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature) {
/* 367 */       return (eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isContainment());
/*     */     }
/*     */ 
/*     */     
/*     */     public EStructuralFeature feature() {
/* 372 */       return this.feature;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 377 */       switch (this.prepared) {
/*     */ 
/*     */         
/*     */         case 2:
/*     */         case 3:
/* 382 */           return true;
/*     */ 
/*     */         
/*     */         case 1:
/* 386 */           return false;
/*     */ 
/*     */ 
/*     */         
/*     */         case -3:
/* 391 */           if (this.values == null) {
/*     */             
/* 393 */             this.valueListIndex++;
/*     */             
/*     */             break;
/*     */           } 
/* 397 */           this.values.next();
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 402 */       if (this.valueList == null || ((this.values == null) ? !scanNext() : !scanNext(this.values))) {
/*     */         
/* 404 */         while (this.featureCursor < this.eStructuralFeatures.length) {
/*     */           
/* 406 */           EStructuralFeature feature = this.eStructuralFeatures[this.featureCursor++];
/* 407 */           if (isIncluded(feature) && (!useIsSet() || this.eObject.eIsSet(feature))) {
/*     */             
/* 409 */             Object value = this.eObject.eGet(feature, resolve());
/* 410 */             this.isHandlingFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 411 */             if (this.isHandlingFeatureMap || feature.isMany()) {
/*     */               
/* 413 */               if (resolve()) {
/*     */                 
/* 415 */                 List<E> newValueList = (List<E>)value;
/* 416 */                 this.valueList = newValueList;
/*     */               }
/*     */               else {
/*     */                 
/* 420 */                 InternalEList<E> newValueList = (InternalEList<E>)value;
/* 421 */                 this.valueList = (List<E>)(this.valueInternalEList = newValueList);
/*     */               } 
/* 423 */               if (this.valueList instanceof java.util.RandomAccess) {
/*     */                 
/* 425 */                 this.values = null;
/* 426 */                 this.valueListSize = this.valueList.size();
/* 427 */                 this.valueListIndex = 0;
/*     */               }
/*     */               else {
/*     */                 
/* 431 */                 this.values = 
/* 432 */                   (this.valueInternalEList == null) ? 
/* 433 */                   this.valueList.listIterator() : 
/* 434 */                   this.valueInternalEList.basicListIterator();
/*     */               } 
/* 436 */               if ((this.values == null) ? scanNext() : scanNext(this.values)) {
/*     */                 
/* 438 */                 Object object = 
/* 439 */                   (this.values == null) ? (
/* 440 */                   (this.valueInternalEList == null) ? 
/* 441 */                   this.valueList.get(this.valueListIndex++) : 
/* 442 */                   this.valueInternalEList.basicGet(this.valueListIndex++)) : 
/* 443 */                   this.values.next();
/* 444 */                 if (this.isHandlingFeatureMap) {
/*     */                   
/* 446 */                   FeatureMap.Entry entry = (FeatureMap.Entry)object;
/* 447 */                   this.preparedFeature = entry.getEStructuralFeature();
/* 448 */                   E newPreparedResult = (E)entry.getValue();
/* 449 */                   this.preparedResult = newPreparedResult;
/*     */                 }
/*     */                 else {
/*     */                   
/* 453 */                   E newPreparedResult = (E)object;
/* 454 */                   this.preparedResult = newPreparedResult;
/* 455 */                   this.preparedFeature = feature;
/*     */                 } 
/* 457 */                 this.prepared = 3;
/* 458 */                 return true;
/*     */               }  continue;
/*     */             } 
/* 461 */             if (value != null) {
/*     */               
/* 463 */               this.valueList = null;
/* 464 */               this.values = null;
/* 465 */               E newPreparedResult = (E)value;
/* 466 */               this.preparedResult = newPreparedResult;
/* 467 */               this.preparedFeature = feature;
/* 468 */               this.prepared = 2;
/* 469 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/* 473 */         this.valueList = null;
/* 474 */         this.values = null;
/* 475 */         this.isHandlingFeatureMap = false;
/* 476 */         this.prepared = 1;
/* 477 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 481 */       Object result = 
/* 482 */         (this.values == null) ? (
/* 483 */         (this.valueInternalEList == null) ? 
/* 484 */         this.valueList.get(this.valueListIndex++) : 
/* 485 */         this.valueInternalEList.basicGet(this.valueListIndex++)) : 
/* 486 */         this.values.next();
/* 487 */       if (this.isHandlingFeatureMap) {
/*     */         
/* 489 */         FeatureMap.Entry entry = (FeatureMap.Entry)result;
/* 490 */         this.preparedFeature = entry.getEStructuralFeature();
/* 491 */         E newPreparedResult = (E)entry.getValue();
/* 492 */         this.preparedResult = newPreparedResult;
/*     */       }
/*     */       else {
/*     */         
/* 496 */         E newPreparedResult = (E)result;
/* 497 */         this.preparedResult = newPreparedResult;
/*     */       } 
/* 499 */       this.prepared = 3;
/* 500 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean scanNext(ListIterator<E> values) {
/* 508 */       if (this.isHandlingFeatureMap) {
/*     */         
/* 510 */         while (values.hasNext()) {
/*     */           
/* 512 */           FeatureMap.Entry entry = (FeatureMap.Entry)values.next();
/* 513 */           EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 514 */           if (isIncludedEntry(entryFeature) && entry.getValue() != null) {
/*     */             
/* 516 */             values.previous();
/* 517 */             return true;
/*     */           } 
/*     */         } 
/* 520 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 524 */       return values.hasNext();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean scanNext() {
/* 530 */       if (this.isHandlingFeatureMap) {
/*     */         
/* 532 */         while (this.valueListIndex < this.valueListSize) {
/*     */           
/* 534 */           FeatureMap.Entry entry = 
/*     */             
/* 536 */             (this.valueInternalEList == null) ? 
/* 537 */             (FeatureMap.Entry)this.valueList.get(this.valueListIndex) : 
/* 538 */             (FeatureMap.Entry)this.valueInternalEList.basicGet(this.valueListIndex);
/* 539 */           EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 540 */           if (isIncludedEntry(entryFeature) && entry.getValue() != null)
/*     */           {
/* 542 */             return true;
/*     */           }
/*     */ 
/*     */           
/* 546 */           this.valueListIndex++;
/*     */         } 
/*     */         
/* 549 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 553 */       return (this.valueListIndex < this.valueListSize);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public E next() {
/* 559 */       if (this.prepared > 1 || hasNext()) {
/*     */         
/* 561 */         this.cursor++;
/* 562 */         this.prepared = 0;
/* 563 */         this.feature = this.preparedFeature;
/* 564 */         E result = this.preparedResult;
/* 565 */         hasNext();
/* 566 */         return result;
/*     */       } 
/*     */ 
/*     */       
/* 570 */       throw new NoSuchElementException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int nextIndex() {
/* 576 */       return this.cursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasPrevious() {
/* 581 */       switch (this.prepared) {
/*     */ 
/*     */         
/*     */         case -3:
/*     */         case -2:
/* 586 */           return true;
/*     */ 
/*     */         
/*     */         case -1:
/* 590 */           return false;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 595 */           if (this.values == null) {
/*     */             
/* 597 */             this.valueListIndex--;
/*     */             
/*     */             break;
/*     */           } 
/* 601 */           this.values.previous();
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 606 */       if (this.valueList == null || ((this.values == null) ? !scanPrevious() : !scanPrevious(this.values))) {
/*     */         
/* 608 */         while (this.featureCursor > 0) {
/*     */           
/* 610 */           EStructuralFeature feature = this.eStructuralFeatures[--this.featureCursor];
/* 611 */           if (isIncluded(feature) && (!useIsSet() || this.eObject.eIsSet(feature))) {
/*     */             
/* 613 */             Object value = this.eObject.eGet(feature, resolve());
/* 614 */             this.isHandlingFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 615 */             if (this.isHandlingFeatureMap || feature.isMany()) {
/*     */               
/* 617 */               if (resolve()) {
/*     */                 
/* 619 */                 List<E> newValueList = (List<E>)value;
/* 620 */                 this.valueList = newValueList;
/*     */               }
/*     */               else {
/*     */                 
/* 624 */                 InternalEList<E> newValueList = (InternalEList<E>)value;
/* 625 */                 this.valueList = (List<E>)(this.valueInternalEList = newValueList);
/*     */               } 
/* 627 */               if (this.valueList instanceof java.util.RandomAccess) {
/*     */                 
/* 629 */                 this.valueListSize = this.valueList.size();
/* 630 */                 this.valueListIndex = this.valueListSize;
/*     */               }
/*     */               else {
/*     */                 
/* 634 */                 this.values = 
/* 635 */                   (this.valueInternalEList == null) ? 
/* 636 */                   this.valueList.listIterator(this.valueList.size()) : 
/* 637 */                   this.valueInternalEList.basicListIterator(this.valueList.size());
/*     */               } 
/* 639 */               if ((this.values == null) ? scanPrevious() : scanPrevious(this.values)) {
/*     */                 
/* 641 */                 Object object = 
/* 642 */                   (this.values == null) ? (
/* 643 */                   (this.valueInternalEList == null) ? 
/* 644 */                   this.valueList.get(--this.valueListIndex) : 
/* 645 */                   this.valueInternalEList.basicGet(--this.valueListIndex)) : 
/* 646 */                   this.values.previous();
/* 647 */                 if (this.isHandlingFeatureMap) {
/*     */                   
/* 649 */                   FeatureMap.Entry entry = (FeatureMap.Entry)object;
/* 650 */                   this.preparedFeature = entry.getEStructuralFeature();
/* 651 */                   E newPreparedResult = (E)entry.getValue();
/* 652 */                   this.preparedResult = newPreparedResult;
/*     */                 }
/*     */                 else {
/*     */                   
/* 656 */                   E newPreparedResult = (E)object;
/* 657 */                   this.preparedResult = newPreparedResult;
/* 658 */                   this.preparedFeature = feature;
/*     */                 } 
/* 660 */                 this.prepared = -3;
/* 661 */                 return true;
/*     */               }  continue;
/*     */             } 
/* 664 */             if (value != null) {
/*     */               
/* 666 */               this.valueList = null;
/* 667 */               this.values = null;
/* 668 */               E newPreparedResult = (E)value;
/* 669 */               this.preparedResult = newPreparedResult;
/* 670 */               this.preparedFeature = feature;
/* 671 */               this.prepared = -2;
/* 672 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/* 676 */         this.valueList = null;
/* 677 */         this.values = null;
/* 678 */         this.prepared = -1;
/* 679 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 683 */       Object result = 
/* 684 */         (this.values == null) ? (
/* 685 */         (this.valueInternalEList == null) ? 
/* 686 */         this.valueList.get(--this.valueListIndex) : 
/* 687 */         this.valueInternalEList.basicGet(--this.valueListIndex)) : 
/* 688 */         this.values.previous();
/* 689 */       if (this.isHandlingFeatureMap) {
/*     */         
/* 691 */         FeatureMap.Entry entry = (FeatureMap.Entry)result;
/* 692 */         this.preparedFeature = entry.getEStructuralFeature();
/* 693 */         E newPreparedResult = (E)entry.getValue();
/* 694 */         this.preparedResult = newPreparedResult;
/*     */       }
/*     */       else {
/*     */         
/* 698 */         E newPreparedResult = (E)result;
/* 699 */         this.preparedResult = newPreparedResult;
/*     */       } 
/* 701 */       this.prepared = -3;
/* 702 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean scanPrevious(ListIterator<E> values) {
/* 710 */       if (this.isHandlingFeatureMap) {
/*     */         
/* 712 */         while (values.hasPrevious()) {
/*     */           
/* 714 */           FeatureMap.Entry entry = (FeatureMap.Entry)values.previous();
/* 715 */           EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 716 */           if (isIncludedEntry(entryFeature) && entry.getValue() != null) {
/*     */             
/* 718 */             values.next();
/* 719 */             return true;
/*     */           } 
/*     */         } 
/* 722 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 726 */       return values.hasPrevious();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean scanPrevious() {
/* 732 */       if (this.isHandlingFeatureMap) {
/*     */         
/* 734 */         while (this.valueListIndex > 0) {
/*     */           
/* 736 */           FeatureMap.Entry entry = (FeatureMap.Entry)this.valueList.get(this.valueListIndex - 1);
/* 737 */           EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 738 */           if (isIncludedEntry(entryFeature) && entry.getValue() != null)
/*     */           {
/* 740 */             return true;
/*     */           }
/*     */ 
/*     */           
/* 744 */           this.valueListIndex--;
/*     */         } 
/*     */         
/* 747 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 751 */       return (this.valueListIndex > 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public E previous() {
/* 757 */       if (this.prepared < -1 || hasPrevious()) {
/*     */         
/* 759 */         this.cursor--;
/* 760 */         this.prepared = 0;
/* 761 */         this.feature = this.preparedFeature;
/* 762 */         E result = this.preparedResult;
/* 763 */         hasPrevious();
/* 764 */         return result;
/*     */       } 
/*     */ 
/*     */       
/* 768 */       throw new NoSuchElementException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int previousIndex() {
/* 774 */       return this.cursor - 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public void add(Object o) {
/* 779 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 784 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void set(Object o) {
/* 789 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/* 793 */     public static final ListIterator<?> EMPTY_ITERATOR = new FeatureIteratorImpl<Object>(null, null)
/*     */       {
/*     */         
/*     */         public boolean hasNext()
/*     */         {
/* 798 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public boolean hasPrevious() {
/* 804 */           return false;
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/*     */     public static <T> ListIterator<T> emptyIterator() {
/* 811 */       return (ListIterator)EMPTY_ITERATOR;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ResolvingFeatureIteratorImpl<E>
/*     */     extends FeatureIteratorImpl<E>
/*     */   {
/*     */     public ResolvingFeatureIteratorImpl(EObject eObject, List<? extends EStructuralFeature> eStructuralFeatures) {
/* 819 */       super(eObject, eStructuralFeatures);
/*     */     }
/*     */ 
/*     */     
/*     */     public ResolvingFeatureIteratorImpl(EObject eObject, EStructuralFeature[] eStructuralFeatures) {
/* 824 */       super(eObject, eStructuralFeatures);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean resolve() {
/* 830 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface FeatureIterator<E> extends Iterator<E> {
/*     */     EStructuralFeature feature();
/*     */   }
/*     */   
/*     */   public static interface FeatureListIterator<E> extends FeatureIterator<E>, ListIterator<E> {}
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\EContentsEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */