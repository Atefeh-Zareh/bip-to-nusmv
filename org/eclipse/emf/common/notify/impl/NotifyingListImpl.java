/*      */ package org.eclipse.emf.common.notify.impl;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.Notifier;
/*      */ import org.eclipse.emf.common.notify.NotifyingList;
/*      */ import org.eclipse.emf.common.util.BasicEList;
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
/*      */ public class NotifyingListImpl<E>
/*      */   extends BasicEList<E>
/*      */   implements NotifyingList<E>
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public NotifyingListImpl() {}
/*      */   
/*      */   public NotifyingListImpl(int initialCapacity) {
/*   54 */     super(initialCapacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotifyingListImpl(Collection<? extends E> collection) {
/*   63 */     super(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getNotifier() {
/*   72 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getFeature() {
/*   81 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeatureID() {
/*   90 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getFeatureID(Class<?> expectedClass) {
/*  100 */     return getFeatureID();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSet() {
/*  110 */     return !isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasInverse() {
/*  119 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canContainNull() {
/*  129 */     return !hasInverse();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isNotificationRequired() {
/*  138 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasShadow() {
/*  147 */     return false;
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
/*  159 */     return notifications;
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
/*  171 */     return notifications;
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
/*  184 */     return notifications;
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
/*  196 */     return notifications;
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
/*  208 */     return notifications;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index) {
/*  216 */     throw new UnsupportedOperationException("Please change your code to call new five argument version of this method");
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
/*  229 */     return 
/*  230 */       new NotificationImpl(eventType, oldObject, newObject, index, wasSet)
/*      */       {
/*      */         
/*      */         public Object getNotifier()
/*      */         {
/*  235 */           return NotifyingListImpl.this.getNotifier();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object getFeature() {
/*  241 */           return NotifyingListImpl.this.getFeature();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public int getFeatureID(Class<?> expectedClass) {
/*  247 */           return NotifyingListImpl.this.getFeatureID(expectedClass);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NotificationChain createNotificationChain(int capacity) {
/*  258 */     return (capacity < 100) ? null : new NotificationChainImpl(capacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dispatchNotification(Notification notification) {
/*  267 */     ((Notifier)getNotifier()).eNotify(notification);
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
/*  284 */     if (isNotificationRequired()) {
/*      */       
/*  286 */       int index = this.size;
/*  287 */       boolean oldIsSet = isSet();
/*  288 */       doAddUnique(object);
/*  289 */       NotificationImpl notification = createNotification(3, (Object)null, object, index, oldIsSet);
/*  290 */       if (hasInverse()) {
/*      */         
/*  292 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  293 */         if (hasShadow())
/*      */         {
/*  295 */           notifications = shadowAdd(object, notifications);
/*      */         }
/*      */         
/*  298 */         if (notifications == null)
/*      */         {
/*  300 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  304 */           notifications.add(notification);
/*  305 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  310 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  315 */       doAddUnique(object);
/*  316 */       if (hasInverse()) {
/*      */         
/*  318 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  319 */         if (notifications != null) notifications.dispatch();
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
/*  331 */     super.addUnique(object);
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
/*  348 */     if (isNotificationRequired()) {
/*      */       
/*  350 */       boolean oldIsSet = isSet();
/*  351 */       doAddUnique(index, object);
/*  352 */       NotificationImpl notification = createNotification(3, (Object)null, object, index, oldIsSet);
/*  353 */       if (hasInverse()) {
/*      */         
/*  355 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  356 */         if (hasShadow())
/*      */         {
/*  358 */           notifications = shadowAdd(object, notifications);
/*      */         }
/*  360 */         if (notifications == null)
/*      */         {
/*  362 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  366 */           notifications.add(notification);
/*  367 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  372 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  377 */       doAddUnique(index, object);
/*  378 */       if (hasInverse()) {
/*      */         
/*  380 */         NotificationChain notifications = inverseAdd(object, (NotificationChain)null);
/*  381 */         if (notifications != null) notifications.dispatch();
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
/*  393 */     super.addUnique(index, object);
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
/*  406 */     return addAllUnique(this.size, collection);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doAddAllUnique(Collection<? extends E> collection) {
/*  416 */     return super.addAllUnique(collection);
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
/*  436 */     int collectionSize = collection.size();
/*  437 */     if (collectionSize == 0)
/*      */     {
/*  439 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  443 */     if (isNotificationRequired()) {
/*      */       
/*  445 */       boolean oldIsSet = isSet();
/*  446 */       doAddAllUnique(index, collection);
/*  447 */       NotificationImpl notification = 
/*  448 */         (collectionSize == 1) ? 
/*  449 */         createNotification(3, (Object)null, collection.iterator().next(), index, oldIsSet) : 
/*  450 */         createNotification(5, (Object)null, collection, index, oldIsSet);
/*  451 */       if (hasInverse()) {
/*      */         
/*  453 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  454 */         int lastIndex = index + collectionSize;
/*  455 */         for (int i = index; i < lastIndex; i++) {
/*      */           
/*  457 */           E value = (E)this.data[i];
/*  458 */           notifications = inverseAdd(value, notifications);
/*  459 */           notifications = shadowAdd(value, notifications);
/*      */         } 
/*  461 */         if (notifications == null)
/*      */         {
/*  463 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  467 */           notifications.add(notification);
/*  468 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  473 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  478 */       doAddAllUnique(index, collection);
/*  479 */       if (hasInverse()) {
/*      */         
/*  481 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  482 */         int lastIndex = index + collectionSize;
/*  483 */         for (int i = index; i < lastIndex; i++) {
/*      */           
/*  485 */           E object = (E)this.data[i];
/*  486 */           notifications = inverseAdd(object, notifications);
/*      */         } 
/*  488 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } 
/*      */     } 
/*  492 */     return true;
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
/*  506 */     return super.addAllUnique(index, collection);
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
/*      */   public boolean addAllUnique(Object[] objects, int start, int end) {
/*  523 */     return addAllUnique(this.size, objects, start, end);
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
/*  537 */     return super.addAllUnique(objects, start, end);
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
/*      */   public boolean addAllUnique(int index, Object[] objects, int start, int end) {
/*  563 */     int collectionSize = end - start;
/*  564 */     if (collectionSize == 0)
/*      */     {
/*  566 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  570 */     if (isNotificationRequired()) {
/*      */       NotificationImpl notification;
/*  572 */       boolean oldIsSet = isSet();
/*  573 */       doAddAllUnique(index, objects, start, end);
/*      */       
/*  575 */       if (collectionSize == 1) {
/*      */         
/*  577 */         notification = createNotification(3, (Object)null, objects[0], index, oldIsSet);
/*      */ 
/*      */       
/*      */       }
/*  581 */       else if (start != 0 || end != objects.length) {
/*      */         
/*  583 */         Object[] actualObjects = new Object[collectionSize];
/*  584 */         for (int i = 0, j = start; j < end; i++, j++)
/*      */         {
/*  586 */           actualObjects[i] = objects[j];
/*      */         }
/*  588 */         notification = createNotification(5, (Object)null, Arrays.asList(actualObjects), index, oldIsSet);
/*      */       }
/*      */       else {
/*      */         
/*  592 */         notification = createNotification(5, (Object)null, Arrays.asList(objects), index, oldIsSet);
/*      */       } 
/*      */       
/*  595 */       if (hasInverse()) {
/*      */         
/*  597 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  598 */         int lastIndex = index + collectionSize;
/*  599 */         for (int i = index; i < lastIndex; i++) {
/*      */           
/*  601 */           E value = (E)this.data[i];
/*  602 */           notifications = inverseAdd(value, notifications);
/*  603 */           notifications = shadowAdd(value, notifications);
/*      */         } 
/*  605 */         if (notifications == null)
/*      */         {
/*  607 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  611 */           notifications.add(notification);
/*  612 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  617 */         dispatchNotification(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  622 */       doAddAllUnique(index, objects, start, end);
/*  623 */       if (hasInverse()) {
/*      */         
/*  625 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  626 */         int lastIndex = index + collectionSize;
/*  627 */         for (int i = index; i < lastIndex; i++) {
/*      */           
/*  629 */           E object = (E)this.data[i];
/*  630 */           notifications = inverseAdd(object, notifications);
/*      */         } 
/*  632 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } 
/*      */     } 
/*  636 */     return true;
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
/*  652 */     return super.addAllUnique(index, objects, start, end);
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
/*  667 */     if (isNotificationRequired()) {
/*      */       
/*  669 */       int index = this.size;
/*  670 */       boolean oldIsSet = isSet();
/*  671 */       doAddUnique(index, object);
/*  672 */       NotificationImpl notification = createNotification(3, (Object)null, object, index, oldIsSet);
/*  673 */       if (notifications == null)
/*      */       {
/*  675 */         notifications = notification;
/*      */       }
/*      */       else
/*      */       {
/*  679 */         notifications.add(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  684 */       doAddUnique(this.size, object);
/*      */     } 
/*  686 */     return notifications;
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
/*  704 */     if (isNotificationRequired()) {
/*      */       
/*  706 */       NotificationChain notifications = null;
/*  707 */       boolean oldIsSet = isSet();
/*  708 */       if (hasShadow())
/*      */       {
/*  710 */         notifications = shadowRemove((E)basicGet(index), (NotificationChain)null);
/*      */       }
/*      */       E e;
/*  713 */       NotificationImpl notification = createNotification(4, e = doRemove(index), (Object)null, index, oldIsSet);
/*  714 */       if (hasInverse() && e != null) {
/*      */         
/*  716 */         notifications = inverseRemove(e, notifications);
/*  717 */         if (notifications == null)
/*      */         {
/*  719 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  723 */           notifications.add(notification);
/*  724 */           notifications.dispatch();
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  729 */       else if (notifications == null) {
/*      */         
/*  731 */         dispatchNotification(notification);
/*      */       }
/*      */       else {
/*      */         
/*  735 */         notifications.add(notification);
/*  736 */         notifications.dispatch();
/*      */       } 
/*      */       
/*  739 */       return e;
/*      */     } 
/*      */ 
/*      */     
/*  743 */     E oldObject = doRemove(index);
/*  744 */     if (hasInverse() && oldObject != null) {
/*      */       
/*  746 */       NotificationChain notifications = inverseRemove(oldObject, (NotificationChain)null);
/*  747 */       if (notifications != null) notifications.dispatch(); 
/*      */     } 
/*  749 */     return oldObject;
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
/*  762 */     return (E)super.remove(index);
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
/*  779 */     boolean oldIsSet = isSet();
/*      */     
/*  781 */     boolean result = false;
/*  782 */     int[] positions = (int[])null;
/*  783 */     if (isNotificationRequired()) {
/*      */       
/*  785 */       int listSize = collection.size();
/*  786 */       if (listSize > 0) {
/*      */         
/*  788 */         NotificationChain notifications = createNotificationChain(listSize);
/*      */ 
/*      */ 
/*      */         
/*  792 */         BasicEList<Object> list = new BasicEList(collection);
/*  793 */         Object[] objects = list.data();
/*  794 */         positions = new int[listSize];
/*  795 */         int count = 0;
/*      */         
/*  797 */         if (isUnique()) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  802 */           for (int i = 0; i < this.size; i++)
/*      */           {
/*  804 */             E initialObject = (E)this.data[i];
/*  805 */             E object = initialObject;
/*      */             int repeat;
/*  807 */             label115: for (repeat = 0; repeat < 2; repeat++)
/*      */             {
/*  809 */               for (int j = listSize; --j >= 0;) {
/*      */                 
/*  811 */                 if (equalObjects(object, objects[j])) {
/*      */                   
/*  813 */                   if (count != j) {
/*      */                     
/*  815 */                     Object x = objects[count];
/*  816 */                     objects[count] = objects[j];
/*  817 */                     objects[j] = x;
/*      */                   } 
/*  819 */                   positions[count++] = i;
/*      */                   break label115;
/*      */                 } 
/*      */               } 
/*  823 */               object = resolve(object);
/*  824 */               if (object == initialObject) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  833 */           BasicEList<Object> resultList = new BasicEList(listSize);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  838 */           for (int i = 0; i < this.size; i++) {
/*      */             
/*  840 */             E initialObject = (E)this.data[i];
/*  841 */             E object = initialObject;
/*      */             int repeat;
/*  843 */             label116: for (repeat = 0; repeat < 2; repeat++) {
/*      */               
/*  845 */               for (int j = listSize; --j >= 0;) {
/*      */                 
/*  847 */                 if (equalObjects(object, objects[j])) {
/*      */                   
/*  849 */                   if (positions.length <= count) {
/*      */                     
/*  851 */                     int[] oldPositions = positions;
/*  852 */                     positions = new int[2 * positions.length];
/*  853 */                     System.arraycopy(oldPositions, 0, positions, 0, count);
/*      */                   } 
/*  855 */                   positions[count++] = i;
/*  856 */                   resultList.add(objects[j]);
/*      */                   break label116;
/*      */                 } 
/*      */               } 
/*  860 */               object = resolve(object);
/*  861 */               if (object == initialObject) {
/*      */                 break;
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  868 */           list = resultList;
/*  869 */           objects = resultList.data();
/*  870 */           listSize = count;
/*      */           
/*  872 */           if (count > positions.length) {
/*      */             
/*  874 */             int[] oldPositions = positions;
/*  875 */             positions = new int[count];
/*  876 */             System.arraycopy(oldPositions, 0, positions, 0, count);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  882 */         if (count > 0)
/*      */         {
/*  884 */           result = true;
/*      */           
/*  886 */           if (hasShadow())
/*      */           {
/*      */ 
/*      */             
/*  890 */             for (int j = 0; j < count; j++) {
/*      */               
/*  892 */               E object = (E)objects[j];
/*  893 */               notifications = shadowRemove(object, notifications);
/*      */             } 
/*      */           }
/*      */           
/*      */           int i;
/*      */           
/*  899 */           for (i = count; --i >= 0;)
/*      */           {
/*  901 */             doRemove(positions[i]);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  906 */           if (count != listSize) {
/*      */             
/*  908 */             for (i = listSize; --i >= count;)
/*      */             {
/*  910 */               list.remove(i);
/*      */             }
/*  912 */             int[] oldPositions = positions;
/*  913 */             positions = new int[count];
/*  914 */             System.arraycopy(oldPositions, 0, positions, 0, count);
/*      */           } 
/*      */           
/*  917 */           basicEList = list;
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  923 */       collection1 = getDuplicates((Collection)basicEList);
/*      */       
/*  925 */       for (int i = this.size; --i >= 0;) {
/*      */         
/*  927 */         if (collection1.contains(this.data[i])) {
/*      */           
/*  929 */           doRemove(i);
/*  930 */           result = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  935 */     if (result) {
/*      */       
/*  937 */       if (positions != null) {
/*      */         
/*  939 */         int collectionSize = collection1.size();
/*  940 */         NotificationImpl notification = 
/*  941 */           (collectionSize == 1) ? 
/*  942 */           createNotification(4, collection1.iterator().next(), (Object)null, positions[0], oldIsSet) : 
/*  943 */           createNotification(6, collection1, positions, positions[0], oldIsSet);
/*      */         
/*  945 */         NotificationChain notifications = createNotificationChain(collectionSize);
/*  946 */         if (hasInverse())
/*      */         {
/*  948 */           for (Iterator<?> i = collection1.iterator(); i.hasNext(); ) {
/*      */             
/*  950 */             E object = (E)i.next();
/*  951 */             notifications = inverseRemove(object, notifications);
/*      */           } 
/*  953 */           if (notifications == null)
/*      */           {
/*  955 */             dispatchNotification(notification);
/*      */           }
/*      */           else
/*      */           {
/*  959 */             notifications.add(notification);
/*  960 */             notifications.dispatch();
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  965 */         else if (notifications == null)
/*      */         {
/*  967 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/*  971 */           notifications.add(notification);
/*  972 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       }
/*  976 */       else if (hasInverse()) {
/*      */         
/*  978 */         NotificationChain notifications = createNotificationChain(collection1.size());
/*  979 */         for (Iterator<?> i = collection1.iterator(); i.hasNext(); ) {
/*      */           
/*  981 */           E object = (E)i.next();
/*  982 */           notifications = inverseRemove(object, notifications);
/*      */         } 
/*  984 */         if (notifications != null) notifications.dispatch(); 
/*      */       } 
/*  986 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  990 */     return false;
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
/* 1001 */     return object;
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
/* 1012 */     return super.removeAll(collection);
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
/* 1027 */     int index = indexOf(object);
/* 1028 */     if (index != -1)
/*      */     {
/* 1030 */       if (isNotificationRequired()) {
/*      */         
/* 1032 */         boolean oldIsSet = isSet();
/* 1033 */         Object oldObject = doRemove(index);
/* 1034 */         NotificationImpl notification = createNotification(4, oldObject, (Object)null, index, oldIsSet);
/* 1035 */         if (notifications == null)
/*      */         {
/* 1037 */           notifications = notification;
/*      */         }
/*      */         else
/*      */         {
/* 1041 */           notifications.add(notification);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1046 */         doRemove(index);
/*      */       } 
/*      */     }
/* 1049 */     return notifications;
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
/* 1064 */     if (isNotificationRequired()) {
/*      */       
/* 1066 */       boolean oldIsSet = isSet();
/* 1067 */       if (this.size > 0)
/*      */       {
/* 1069 */         BasicEList.UnmodifiableEList<E> unmodifiableEList = new BasicEList.UnmodifiableEList(this.size, this.data);
/* 1070 */         int collectionSize = this.size;
/*      */         
/* 1072 */         NotificationChain notifications = createNotificationChain(collectionSize);
/* 1073 */         if (hasShadow())
/*      */         {
/* 1075 */           for (int i = 0; i < this.size; i++) {
/*      */             
/* 1077 */             E object = (E)this.data[i];
/* 1078 */             notifications = shadowRemove(object, notifications);
/*      */           } 
/*      */         }
/*      */         
/* 1082 */         doClear();
/* 1083 */         Notification notification = 
/* 1084 */           (collectionSize == 1) ? 
/* 1085 */           createNotification(4, unmodifiableEList.get(0), (Object)null, 0, oldIsSet) : 
/* 1086 */           createNotification(6, unmodifiableEList, (Object)null, -1, oldIsSet);
/*      */         
/* 1088 */         if (hasInverse())
/*      */         {
/* 1090 */           for (Iterator<E> i = unmodifiableEList.iterator(); i.hasNext();)
/*      */           {
/* 1092 */             notifications = inverseRemove(i.next(), notifications);
/*      */           }
/* 1094 */           if (notifications == null)
/*      */           {
/* 1096 */             dispatchNotification(notification);
/*      */           }
/*      */           else
/*      */           {
/* 1100 */             notifications.add(notification);
/* 1101 */             notifications.dispatch();
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1106 */         else if (notifications == null)
/*      */         {
/* 1108 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/* 1112 */           notifications.add(notification);
/* 1113 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       }
/*      */       else
/*      */       {
/* 1119 */         doClear();
/* 1120 */         dispatchNotification(createNotification(6, Collections.EMPTY_LIST, (Object)null, -1, oldIsSet));
/*      */       }
/*      */     
/* 1123 */     } else if (hasInverse()) {
/*      */       
/* 1125 */       if (this.size > 0) {
/*      */         
/* 1127 */         Object[] oldData = this.data;
/* 1128 */         int oldSize = this.size;
/* 1129 */         doClear();
/* 1130 */         NotificationChain notifications = createNotificationChain(oldSize);
/* 1131 */         for (int i = 0; i < oldSize; i++) {
/*      */           
/* 1133 */           E object = (E)oldData[i];
/* 1134 */           notifications = inverseRemove(object, notifications);
/*      */         } 
/* 1136 */         if (notifications != null) notifications.dispatch();
/*      */       
/*      */       } else {
/*      */         
/* 1140 */         doClear();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1145 */       doClear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doClear() {
/* 1155 */     super.clear();
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
/* 1176 */     if (isNotificationRequired()) {
/*      */       
/* 1178 */       NotificationChain notifications = null;
/* 1179 */       boolean oldIsSet = isSet();
/*      */       E e;
/* 1181 */       Notification notification = createNotification(1, e = doSetUnique(index, object), object, index, oldIsSet);
/* 1182 */       if (hasInverse() && !equalObjects(e, object)) {
/*      */         
/* 1184 */         if (e != null)
/*      */         {
/* 1186 */           notifications = inverseRemove(e, notifications);
/*      */         }
/*      */         
/* 1189 */         notifications = inverseAdd(object, notifications);
/*      */         
/* 1191 */         if (hasShadow())
/*      */         {
/* 1193 */           notifications = shadowSet(e, object, notifications);
/*      */         }
/*      */         
/* 1196 */         if (notifications == null)
/*      */         {
/* 1198 */           dispatchNotification(notification);
/*      */         }
/*      */         else
/*      */         {
/* 1202 */           notifications.add(notification);
/* 1203 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1208 */         if (hasShadow())
/*      */         {
/* 1210 */           notifications = shadowSet(e, object, notifications);
/*      */         }
/*      */         
/* 1213 */         if (notifications == null) {
/*      */           
/* 1215 */           dispatchNotification(notification);
/*      */         }
/*      */         else {
/*      */           
/* 1219 */           notifications.add(notification);
/* 1220 */           notifications.dispatch();
/*      */         } 
/*      */       } 
/*      */       
/* 1224 */       return e;
/*      */     } 
/*      */ 
/*      */     
/* 1228 */     E oldObject = doSetUnique(index, object);
/* 1229 */     if (hasInverse() && !equalObjects(oldObject, object)) {
/*      */       
/* 1231 */       NotificationChain notifications = null;
/* 1232 */       if (oldObject != null)
/*      */       {
/* 1234 */         notifications = inverseRemove(oldObject, (NotificationChain)null);
/*      */       }
/* 1236 */       notifications = inverseAdd(object, notifications);
/* 1237 */       if (notifications != null) notifications.dispatch(); 
/*      */     } 
/* 1239 */     return oldObject;
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
/* 1253 */     return (E)super.setUnique(index, object);
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
/* 1271 */     if (isNotificationRequired()) {
/*      */       
/* 1273 */       boolean oldIsSet = isSet();
/* 1274 */       NotificationImpl notification = createNotification(1, doSetUnique(index, object), object, index, oldIsSet);
/* 1275 */       if (notifications == null)
/*      */       {
/* 1277 */         notifications = notification;
/*      */       }
/*      */       else
/*      */       {
/* 1281 */         notifications.add(notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1286 */       doSetUnique(index, object);
/*      */     } 
/* 1288 */     return notifications;
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
/* 1305 */     if (isNotificationRequired()) {
/*      */       
/* 1307 */       boolean oldIsSet = isSet();
/* 1308 */       E object = doMove(targetIndex, sourceIndex);
/* 1309 */       dispatchNotification(
/* 1310 */           createNotification(
/* 1311 */             7, 
/* 1312 */             Integer.valueOf(sourceIndex), 
/* 1313 */             object, 
/* 1314 */             targetIndex, 
/* 1315 */             oldIsSet));
/* 1316 */       return object;
/*      */     } 
/*      */ 
/*      */     
/* 1320 */     return doMove(targetIndex, sourceIndex);
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
/* 1335 */     return (E)super.move(targetIndex, sourceIndex);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\NotifyingListImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */