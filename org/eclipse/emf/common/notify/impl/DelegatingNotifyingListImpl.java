/*      */ package org.eclipse.emf.common.notify.impl;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.ListIterator;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.Notifier;
/*      */ import org.eclipse.emf.common.notify.NotifyingList;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.DelegatingEList;
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
/*      */ public abstract class DelegatingNotifyingListImpl<E>
/*      */   extends DelegatingEList<E>
/*      */   implements NotifyingList<E>
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public DelegatingNotifyingListImpl() {}
/*      */   
/*      */   public DelegatingNotifyingListImpl(Collection<? extends E> collection) {
/*   55 */     super(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getNotifier() {
/*   64 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getFeature() {
/*   73 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeatureID() {
/*   82 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getFeatureID(Class<?> expectedClass) {
/*   92 */     return getFeatureID();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSet() {
/*  102 */     return !isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasInverse() {
/*  111 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canContainNull() {
/*  121 */     return !hasInverse();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isNotificationRequired() {
/*  130 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasShadow() {
/*  139 */     return false;
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
/*      */   protected NotificationChain shadowAdd(E object, NotificationChain notifications) {
/*  151 */     return notifications;
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
/*      */   protected NotificationChain shadowRemove(E object, NotificationChain notifications) {
/*  163 */     return notifications;
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
/*      */   protected NotificationChain shadowSet(E oldObject, E newObject, NotificationChain notifications) {
/*  176 */     return notifications;
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
/*      */   protected NotificationChain inverseAdd(E object, NotificationChain notifications) {
/*  188 */     return notifications;
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
/*      */   protected NotificationChain inverseRemove(E object, NotificationChain notifications) {
/*  200 */     return notifications;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index) {
/*  208 */     throw new UnsupportedOperationException("Please change your code to call new five argument version of this method");
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
/*      */   protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet) {
/*  221 */     return 
/*  222 */       new NotificationImpl(eventType, oldObject, newObject, index, wasSet)
/*      */       {
/*      */         
/*      */         public Object getNotifier()
/*      */         {
/*  227 */           return DelegatingNotifyingListImpl.this.getNotifier();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object getFeature() {
/*  233 */           return DelegatingNotifyingListImpl.this.getFeature();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public int getFeatureID(Class<?> expectedClass) {
/*  239 */           return DelegatingNotifyingListImpl.this.getFeatureID(expectedClass);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NotificationChain createNotificationChain(int capacity) {
/*  251 */     return (capacity < 100) ? null : new NotificationChainImpl(capacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dispatchNotification(Notification notification) {
/*  260 */     ((Notifier)getNotifier()).eNotify(notification);
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
/*      */   public void addUnique(E object) {
/*  277 */     if (isNotificationRequired()) {
/*      */       
/*  279 */       int index = size();
/*  280 */       boolean oldIsSet = isSet();
/*  281 */       doAddUnique(index, object);
/*  282 */       NotificationImpl notification = createNotification(3, (Object)null, object, index, oldIsSet);
/*  283 */       if (hasInverse()) {
/*      */         
/*  285 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  286 */         if (hasShadow())
/*      */         {
/*  288 */           notifications = shadowAdd(object, notifications);
/*      */         }
/*  290 */         if (notifications == null)
/*      */         {
/*  292 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  296 */           notifications.add(notification);
/*  297 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  302 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  307 */       doAddUnique(object);
/*  308 */       if (hasInverse()) {
/*      */         
/*  310 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  311 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doAddUnique(E object) {
/*  323 */     super.addUnique(object);
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
/*      */   public void addUnique(int index, E object) {
/*  340 */     if (isNotificationRequired()) {
/*      */       
/*  342 */       boolean oldIsSet = isSet();
/*  343 */       doAddUnique(index, object);
/*  344 */       NotificationImpl notification = createNotification(3, (Object)null, object, index, oldIsSet);
/*  345 */       if (hasInverse()) {
/*      */         
/*  347 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  348 */         if (hasShadow())
/*      */         {
/*  350 */           notifications = shadowAdd(object, notifications);
/*      */         }
/*  352 */         if (notifications == null)
/*      */         {
/*  354 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  358 */           notifications.add(notification);
/*  359 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  364 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  369 */       doAddUnique(index, object);
/*  370 */       if (hasInverse()) {
/*      */         
/*  372 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  373 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doAddUnique(int index, E object) {
/*  385 */     super.addUnique(index, object);
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
/*      */   public boolean addAllUnique(Collection<? extends E> collection) {
/*  398 */     return addAllUnique(size(), collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doAddAllUnique(Collection<? extends E> collection) {
/*  408 */     return super.addAllUnique(collection);
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
/*      */   public boolean addAllUnique(int index, Collection<? extends E> collection) {
/*  428 */     int collectionSize = collection.size();
/*  429 */     if (collectionSize == 0)
/*      */     {
/*  431 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  435 */     if (isNotificationRequired()) {
/*      */       
/*  437 */       boolean oldIsSet = isSet();
/*  438 */       doAddAllUnique(index, collection);
/*  439 */       NotificationImpl notification = 
/*  440 */         (collectionSize == 1) ? 
/*  441 */         createNotification(3, (Object)null, collection.iterator().next(), index, oldIsSet) : 
/*  442 */         createNotification(5, (Object)null, collection, index, oldIsSet);
/*  443 */       if (hasInverse()) {
/*      */         
/*  445 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  446 */         int lastIndex = index + collectionSize;
/*  447 */         for (int i = index; i < lastIndex; i++) {
/*      */           
/*  449 */           E value = (E)delegateGet(i);
/*  450 */           notifications = inverseAdd(value, notifications);
/*  451 */           notifications = shadowAdd(value, notifications);
/*      */         } 
/*  453 */         if (notifications == null)
/*      */         {
/*  455 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  459 */           notifications.add(notification);
/*  460 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  465 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  470 */       doAddAllUnique(index, collection);
/*  471 */       if (hasInverse()) {
/*      */         
/*  473 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  474 */         int lastIndex = index + collectionSize;
/*  475 */         for (int i = index; i < lastIndex; i++)
/*      */         {
/*  477 */           notifications = inverseAdd((E)delegateGet(i), notifications);
/*      */         }
/*  479 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } 
/*      */     } 
/*  483 */     return true;
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
/*      */   protected boolean doAddAllUnique(int index, Collection<? extends E> collection) {
/*  497 */     return super.addAllUnique(index, collection);
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
/*      */   public boolean addAllUnique(Object[] objects, int start, int end) {
/*  512 */     return addAllUnique(size(), objects, start, end);
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
/*      */   protected boolean doAddAllUnique(Object[] objects, int start, int end) {
/*  526 */     return super.addAllUnique(objects, start, end);
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
/*      */   public boolean addAllUnique(int index, Object[] objects, int start, int end) {
/*  549 */     int collectionSize = end - start;
/*  550 */     if (collectionSize == 0)
/*      */     {
/*  552 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  556 */     if (isNotificationRequired()) {
/*      */       NotificationImpl notification;
/*  558 */       boolean oldIsSet = isSet();
/*  559 */       doAddAllUnique(index, objects, start, end);
/*      */       
/*  561 */       if (collectionSize == 1) {
/*      */         
/*  563 */         notification = createNotification(3, (Object)null, objects[0], index, oldIsSet);
/*      */ 
/*      */       
/*      */       }
/*  567 */       else if (start != 0 || end != objects.length) {
/*      */         
/*  569 */         Object[] actualObjects = new Object[collectionSize];
/*  570 */         for (int i = 0, j = start; j < end; i++, j++)
/*      */         {
/*  572 */           actualObjects[i] = objects[j];
/*      */         }
/*  574 */         notification = createNotification(5, (Object)null, Arrays.asList(actualObjects), index, oldIsSet);
/*      */       }
/*      */       else {
/*      */         
/*  578 */         notification = createNotification(5, (Object)null, Arrays.asList(objects), index, oldIsSet);
/*      */       } 
/*      */       
/*  581 */       if (hasInverse()) {
/*      */         
/*  583 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  584 */         int lastIndex = index + collectionSize;
/*  585 */         for (int i = index; i < lastIndex; i++) {
/*      */           
/*  587 */           E value = (E)delegateGet(i);
/*  588 */           notifications = inverseAdd(value, notifications);
/*  589 */           notifications = shadowAdd(value, notifications);
/*      */         } 
/*  591 */         if (notifications == null)
/*      */         {
/*  593 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  597 */           notifications.add(notification);
/*  598 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  603 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  608 */       doAddAllUnique(index, objects, start, end);
/*  609 */       if (hasInverse()) {
/*      */         
/*  611 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  612 */         int lastIndex = index + collectionSize;
/*  613 */         for (int i = index; i < lastIndex; i++)
/*      */         {
/*  615 */           notifications = inverseAdd((E)delegateGet(i), notifications);
/*      */         }
/*  617 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } 
/*      */     } 
/*  621 */     return true;
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
/*      */   protected boolean doAddAllUnique(int index, Object[] objects, int start, int end) {
/*  637 */     return super.addAllUnique(index, objects, start, end);
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
/*      */   public NotificationChain basicAdd(E object, NotificationChain notifications) {
/*  652 */     if (isNotificationRequired()) {
/*      */       
/*  654 */       int index = size();
/*  655 */       boolean oldIsSet = isSet();
/*  656 */       doAddUnique(index, object);
/*  657 */       NotificationImpl notification = createNotification(3, (Object)null, object, index, oldIsSet);
/*  658 */       if (notifications == null)
/*      */       {
/*  660 */         notifications = notification;
/*      */       }
/*      */       else
/*      */       {
/*  664 */         notifications.add(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  669 */       doAddUnique(size(), object);
/*      */     } 
/*  671 */     return notifications;
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
/*      */   public E remove(int index) {
/*  689 */     if (isNotificationRequired()) {
/*      */       
/*  691 */       NotificationChain notifications = null;
/*  692 */       boolean oldIsSet = isSet();
/*  693 */       if (hasShadow())
/*      */       {
/*  695 */         notifications = shadowRemove((E)basicGet(index), (NotificationChain)null);
/*      */       }
/*      */       E e;
/*  698 */       NotificationImpl notification = createNotification(4, e = doRemove(index), (Object)null, index, oldIsSet);
/*  699 */       if (hasInverse() && e != null) {
/*      */         
/*  701 */         notifications = inverseRemove(e, notifications);
/*  702 */         if (notifications == null)
/*      */         {
/*  704 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  708 */           notifications.add(notification);
/*  709 */           notifications.dispatch();
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  714 */       else if (notifications == null) {
/*      */         
/*  716 */         dispatchNotification(notification);
/*      */       }
/*      */       else {
/*      */         
/*  720 */         notifications.add(notification);
/*  721 */         notifications.dispatch();
/*      */       } 
/*      */       
/*  724 */       return e;
/*      */     } 
/*      */ 
/*      */     
/*  728 */     E oldObject = doRemove(index);
/*  729 */     if (hasInverse() && oldObject != null) {
/*      */       
/*  731 */       NotificationChain notifications = inverseRemove(oldObject, (NotificationChain)null);
/*  732 */       if (notifications != null) notifications.dispatch(); 
/*      */     } 
/*  734 */     return oldObject;
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
/*      */   protected E doRemove(int index) {
/*  747 */     return (E)super.remove(index);
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
/*      */   public boolean removeAll(Collection<?> collection) {
/*      */     BasicEList<Object> basicEList;
/*      */     Collection<?> collection1;
/*  764 */     boolean oldIsSet = isSet();
/*      */     
/*  766 */     boolean result = false;
/*  767 */     int[] positions = (int[])null;
/*  768 */     if (isNotificationRequired()) {
/*      */       
/*  770 */       int listSize = collection.size();
/*  771 */       if (listSize > 0) {
/*      */         
/*  773 */         NotificationChain notifications = createNotificationChain(listSize);
/*      */ 
/*      */ 
/*      */         
/*  777 */         BasicEList<Object> list = new BasicEList(collection);
/*  778 */         Object[] objects = list.data();
/*  779 */         positions = new int[listSize];
/*  780 */         int count = 0;
/*      */         
/*  782 */         if (isUnique()) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  787 */           for (ListIterator<E> i = delegateListIterator(); i.hasNext(); )
/*      */           {
/*  789 */             E initialObject = i.next();
/*  790 */             E object = initialObject;
/*      */             int repeat;
/*  792 */             label113: for (repeat = 0; repeat < 2; repeat++)
/*      */             {
/*  794 */               for (int j = listSize; --j >= 0;) {
/*      */                 
/*  796 */                 if (equalObjects(object, objects[j])) {
/*      */                   
/*  798 */                   if (count != j) {
/*      */                     
/*  800 */                     Object x = objects[count];
/*  801 */                     objects[count] = objects[j];
/*  802 */                     objects[j] = x;
/*      */                   } 
/*  804 */                   positions[count++] = i.previousIndex();
/*      */                   break label113;
/*      */                 } 
/*      */               } 
/*  808 */               object = resolve(object);
/*  809 */               if (object == initialObject) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  818 */           BasicEList<Object> resultList = new BasicEList(listSize);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  823 */           for (ListIterator<E> i = delegateListIterator(); i.hasNext(); ) {
/*      */             
/*  825 */             E initialObject = i.next();
/*  826 */             E object = initialObject;
/*      */             int repeat;
/*  828 */             label114: for (repeat = 0; repeat < 2; repeat++) {
/*      */               
/*  830 */               for (int j = listSize; --j >= 0;) {
/*      */                 
/*  832 */                 if (equalObjects(object, objects[j])) {
/*      */                   
/*  834 */                   if (positions.length <= count) {
/*      */                     
/*  836 */                     int[] oldPositions = positions;
/*  837 */                     positions = new int[2 * positions.length];
/*  838 */                     System.arraycopy(oldPositions, 0, positions, 0, count);
/*      */                   } 
/*  840 */                   positions[count++] = i.previousIndex();
/*  841 */                   resultList.add(objects[j]);
/*      */                   break label114;
/*      */                 } 
/*      */               } 
/*  845 */               object = resolve(object);
/*  846 */               if (object == initialObject) {
/*      */                 break;
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  853 */           list = resultList;
/*  854 */           objects = resultList.data();
/*  855 */           listSize = count;
/*      */           
/*  857 */           if (count > positions.length) {
/*      */             
/*  859 */             int[] oldPositions = positions;
/*  860 */             positions = new int[count];
/*  861 */             System.arraycopy(oldPositions, 0, positions, 0, count);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  867 */         if (count > 0)
/*      */         {
/*  869 */           result = true;
/*      */           
/*  871 */           if (hasShadow())
/*      */           {
/*      */ 
/*      */             
/*  875 */             for (int j = 0; j < count; j++) {
/*      */               
/*  877 */               E object = (E)objects[j];
/*  878 */               notifications = shadowRemove(object, notifications);
/*      */             } 
/*      */           }
/*      */           
/*      */           int i;
/*      */           
/*  884 */           for (i = count; --i >= 0;)
/*      */           {
/*  886 */             doRemove(positions[i]);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  891 */           if (count != listSize) {
/*      */             
/*  893 */             for (i = listSize; --i >= count;)
/*      */             {
/*  895 */               list.remove(i);
/*      */             }
/*  897 */             int[] oldPositions = positions;
/*  898 */             positions = new int[count];
/*  899 */             System.arraycopy(oldPositions, 0, positions, 0, count);
/*      */           } 
/*      */           
/*  902 */           basicEList = list;
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  908 */       collection1 = getDuplicates((Collection)basicEList);
/*      */       
/*  910 */       for (int i = delegateSize(); --i >= 0;) {
/*      */         
/*  912 */         if (collection1.contains(delegateGet(i))) {
/*      */           
/*  914 */           doRemove(i);
/*  915 */           result = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  920 */     if (result) {
/*      */       
/*  922 */       if (positions != null) {
/*      */         
/*  924 */         int collectionSize = collection1.size();
/*  925 */         NotificationImpl notification = 
/*  926 */           (collectionSize == 1) ? 
/*  927 */           createNotification(4, collection1.iterator().next(), (Object)null, positions[0], oldIsSet) : 
/*  928 */           createNotification(6, collection1, positions, positions[0], oldIsSet);
/*      */         
/*  930 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  931 */         if (hasInverse())
/*      */         {
/*  933 */           for (Iterator<?> i = collection1.iterator(); i.hasNext(); ) {
/*      */             
/*  935 */             E object = (E)i.next();
/*  936 */             notifications = inverseRemove(object, notifications);
/*      */           } 
/*  938 */           if (notifications == null)
/*      */           {
/*  940 */             dispatchNotification(notification);
/*      */           }
/*      */           else
/*      */           {
/*  944 */             notifications.add(notification);
/*  945 */             notifications.dispatch();
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  950 */         else if (notifications == null)
/*      */         {
/*  952 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  956 */           notifications.add(notification);
/*  957 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       }
/*  961 */       else if (hasInverse()) {
/*      */         
/*  963 */         NotificationChain notifications = createNotificationChain(collection1.size());
/*  964 */         for (Iterator<?> i = collection1.iterator(); i.hasNext(); ) {
/*      */           
/*  966 */           E object = (E)i.next();
/*  967 */           notifications = inverseRemove(object, notifications);
/*      */         } 
/*  969 */         if (notifications != null) notifications.dispatch(); 
/*      */       } 
/*  971 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  975 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected E resolve(E object) {
/*  986 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doRemoveAll(Collection<?> collection) {
/*  997 */     return super.removeAll(collection);
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
/*      */   public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/* 1012 */     int index = indexOf(object);
/* 1013 */     if (index != -1)
/*      */     {
/* 1015 */       if (isNotificationRequired()) {
/*      */         
/* 1017 */         boolean oldIsSet = isSet();
/* 1018 */         Object oldObject = doRemove(index);
/* 1019 */         NotificationImpl notification = createNotification(4, oldObject, (Object)null, index, oldIsSet);
/* 1020 */         if (notifications == null)
/*      */         {
/* 1022 */           notifications = notification;
/*      */         }
/*      */         else
/*      */         {
/* 1026 */           notifications.add(notification);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1031 */         doRemove(index);
/*      */       } 
/*      */     }
/* 1034 */     return notifications;
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
/*      */   public void clear() {
/* 1049 */     if (isNotificationRequired()) {
/*      */       
/* 1051 */       int size = size();
/* 1052 */       boolean oldIsSet = isSet();
/* 1053 */       if (size > 0)
/*      */       {
/* 1055 */         BasicEList<E> collection = new BasicEList(basicList());
/* 1056 */         int collectionSize = size;
/*      */         
/* 1058 */         NotificationChain notifications = createNotificationChain(collectionSize);
/* 1059 */         if (hasShadow())
/*      */         {
/* 1061 */           for (int i = 0; i < size; i++)
/*      */           {
/* 1063 */             notifications = shadowRemove((E)collection.get(i), notifications);
/*      */           }
/*      */         }
/*      */         
/* 1067 */         doClear(collectionSize, collection.data());
/* 1068 */         Notification notification = 
/* 1069 */           (collectionSize == 1) ? 
/* 1070 */           createNotification(4, collection.get(0), (Object)null, 0, oldIsSet) : 
/* 1071 */           createNotification(6, collection, (Object)null, -1, oldIsSet);
/*      */         
/* 1073 */         if (hasInverse())
/*      */         {
/* 1075 */           for (Iterator<E> i = collection.iterator(); i.hasNext();)
/*      */           {
/* 1077 */             notifications = inverseRemove(i.next(), notifications);
/*      */           }
/* 1079 */           if (notifications == null)
/*      */           {
/* 1081 */             dispatchNotification(notification);
/*      */           }
/*      */           else
/*      */           {
/* 1085 */             notifications.add(notification);
/* 1086 */             notifications.dispatch();
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1091 */         else if (notifications == null)
/*      */         {
/* 1093 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/* 1097 */           notifications.add(notification);
/* 1098 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       }
/*      */       else
/*      */       {
/* 1104 */         doClear();
/* 1105 */         dispatchNotification(createNotification(6, Collections.EMPTY_LIST, (Object)null, -1, oldIsSet));
/*      */       }
/*      */     
/* 1108 */     } else if (hasInverse()) {
/*      */       
/* 1110 */       int size = size();
/* 1111 */       if (size > 0) {
/*      */         
/* 1113 */         Object[] oldData = delegateToArray();
/* 1114 */         int oldSize = size;
/* 1115 */         doClear(size, oldData);
/* 1116 */         NotificationChain notifications = createNotificationChain(oldSize);
/* 1117 */         for (int i = 0; i < oldSize; i++) {
/*      */           
/* 1119 */           E object = (E)oldData[i];
/* 1120 */           notifications = inverseRemove(object, notifications);
/*      */         } 
/* 1122 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } else {
/*      */         
/* 1126 */         doClear();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1131 */       doClear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doClear() {
/* 1141 */     super.clear();
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
/*      */   public E setUnique(int index, E object) {
/* 1162 */     if (isNotificationRequired()) {
/*      */       
/* 1164 */       NotificationChain notifications = null;
/* 1165 */       boolean oldIsSet = isSet();
/*      */       E e;
/* 1167 */       Notification notification = createNotification(1, e = doSetUnique(index, object), object, index, oldIsSet);
/* 1168 */       if (hasInverse() && !equalObjects(e, object)) {
/*      */         
/* 1170 */         if (e != null)
/*      */         {
/* 1172 */           notifications = inverseRemove(e, notifications);
/*      */         }
/* 1174 */         notifications = inverseAdd(object, notifications);
/*      */         
/* 1176 */         if (hasShadow())
/*      */         {
/* 1178 */           notifications = shadowSet(e, object, notifications);
/*      */         }
/*      */         
/* 1181 */         if (notifications == null)
/*      */         {
/* 1183 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/* 1187 */           notifications.add(notification);
/* 1188 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1193 */         if (hasShadow())
/*      */         {
/* 1195 */           notifications = shadowSet(e, object, notifications);
/*      */         }
/*      */         
/* 1198 */         if (notifications == null) {
/*      */           
/* 1200 */           dispatchNotification(notification);
/*      */         }
/*      */         else {
/*      */           
/* 1204 */           notifications.add(notification);
/* 1205 */           notifications.dispatch();
/*      */         } 
/*      */       } 
/* 1208 */       return e;
/*      */     } 
/*      */ 
/*      */     
/* 1212 */     E oldObject = doSetUnique(index, object);
/* 1213 */     if (hasInverse() && !equalObjects(oldObject, object)) {
/*      */       
/* 1215 */       NotificationChain notifications = null;
/* 1216 */       if (oldObject != null)
/*      */       {
/* 1218 */         notifications = inverseRemove(oldObject, (NotificationChain)null);
/*      */       }
/* 1220 */       notifications = inverseAdd(object, notifications);
/* 1221 */       if (notifications != null) notifications.dispatch(); 
/*      */     } 
/* 1223 */     return oldObject;
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
/*      */   protected E doSetUnique(int index, E object) {
/* 1237 */     return (E)super.setUnique(index, object);
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
/*      */   public NotificationChain basicSet(int index, E object, NotificationChain notifications) {
/* 1255 */     if (isNotificationRequired()) {
/*      */       
/* 1257 */       boolean oldIsSet = isSet();
/* 1258 */       NotificationImpl notification = 
/* 1259 */         createNotification(1, doSetUnique(index, object), object, index, oldIsSet);
/* 1260 */       if (notifications == null)
/*      */       {
/* 1262 */         notifications = notification;
/*      */       }
/*      */       else
/*      */       {
/* 1266 */         notifications.add(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1271 */       doSetUnique(index, object);
/*      */     } 
/* 1273 */     return notifications;
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
/*      */   public E move(int targetIndex, int sourceIndex) {
/* 1290 */     if (isNotificationRequired()) {
/*      */       
/* 1292 */       boolean oldIsSet = isSet();
/* 1293 */       E object = doMove(targetIndex, sourceIndex);
/* 1294 */       dispatchNotification(
/* 1295 */           createNotification(
/* 1296 */             7, 
/* 1297 */             Integer.valueOf(sourceIndex), 
/* 1298 */             object, 
/* 1299 */             targetIndex, 
/* 1300 */             oldIsSet));
/* 1301 */       return object;
/*      */     } 
/*      */ 
/*      */     
/* 1305 */     return doMove(targetIndex, sourceIndex);
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
/*      */   protected E doMove(int targetIndex, int sourceIndex) {
/* 1320 */     return (E)super.move(targetIndex, sourceIndex);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\DelegatingNotifyingListImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */