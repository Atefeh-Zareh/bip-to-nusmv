/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*      */ import org.eclipse.emf.common.util.AbstractEList;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*      */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BasicFeatureMap
/*      */   extends EDataTypeEList<FeatureMap.Entry>
/*      */   implements FeatureMap.Internal, FeatureMap.Internal.Wrapper
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*   47 */   protected FeatureMap.Internal.Wrapper wrapper = this;
/*      */   
/*      */   protected final FeatureMapUtil.Validator featureMapValidator;
/*      */   
/*      */   public BasicFeatureMap(InternalEObject owner, int featureID) {
/*   52 */     super(FeatureMap.Entry.Internal.class, owner, featureID);
/*      */     
/*   54 */     this.featureMapValidator = FeatureMapUtil.getValidator(owner.eClass(), getEStructuralFeature());
/*      */   }
/*      */ 
/*      */   
/*      */   public BasicFeatureMap(InternalEObject owner, int featureID, EStructuralFeature eStructuralFeature) {
/*   59 */     super(FeatureMap.Entry.Internal.class, owner, featureID);
/*      */     
/*   61 */     this.featureMapValidator = FeatureMapUtil.getValidator(owner.eClass(), eStructuralFeature);
/*      */   }
/*      */ 
/*      */   
/*      */   public FeatureMap.Internal.Wrapper getWrapper() {
/*   66 */     return this.wrapper;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWrapper(FeatureMap.Internal.Wrapper wrapper) {
/*   71 */     this.wrapper = wrapper;
/*      */   }
/*      */ 
/*      */   
/*      */   public FeatureMap featureMap() {
/*   76 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object[] newData(int capacity) {
/*   82 */     return (Object[])new FeatureMap.Entry.Internal[capacity];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected FeatureMap.Entry validate(int index, FeatureMap.Entry object) {
/*   88 */     if (this.modCount == 0) return object;
/*      */     
/*   90 */     FeatureMap.Entry result = super.validate(index, object);
/*   91 */     EStructuralFeature eStructuralFeature = object.getEStructuralFeature();
/*   92 */     if (!eStructuralFeature.isChangeable() || !this.featureMapValidator.isValid(eStructuralFeature))
/*      */     {
/*      */       
/*   95 */       throw new RuntimeException(
/*   96 */           "Invalid entry feature '" + eStructuralFeature.getEContainingClass().getName() + "." + eStructuralFeature.getName() + "'");
/*      */     }
/*   98 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   protected FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value) {
/*  103 */     return FeatureMapUtil.createEntry(eStructuralFeature, value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected FeatureMap.Entry.Internal createRawEntry(EStructuralFeature eStructuralFeature, Object value) {
/*  108 */     return FeatureMapUtil.createRawEntry(eStructuralFeature, value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected NotificationImpl createNotification(int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index, boolean wasSet) {
/*  114 */     return (NotificationImpl)new FeatureMapUtil.FeatureENotificationImpl(this.owner, eventType, feature, oldObject, newObject, index, wasSet);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isMany(EStructuralFeature feature) {
/*  119 */     return FeatureMapUtil.isMany((EObject)this.owner, feature);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasInverse() {
/*  125 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasShadow() {
/*  131 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int entryIndex(EStructuralFeature feature, int index) {
/*  136 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  137 */     int count = 0;
/*  138 */     int result = this.size;
/*  139 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  140 */     for (int i = 0; i < this.size; i++) {
/*      */       
/*  142 */       FeatureMap.Entry entry = entries[i];
/*  143 */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         
/*  145 */         if (index == count)
/*      */         {
/*  147 */           return i;
/*      */         }
/*  149 */         count++;
/*  150 */         result = i + 1;
/*      */       } 
/*      */     } 
/*      */     
/*  154 */     if (index == count)
/*      */     {
/*  156 */       return result;
/*      */     }
/*      */ 
/*      */     
/*  160 */     throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isResolveProxies(EStructuralFeature feature) {
/*  166 */     return (feature instanceof EReference && ((EReference)feature).isResolveProxies());
/*      */   }
/*      */ 
/*      */   
/*      */   public Object resolveProxy(EStructuralFeature feature, int entryIndex, int index, Object object) {
/*  171 */     EObject resolved = resolveProxy((EObject)object);
/*  172 */     if (resolved != object) {
/*      */       
/*  174 */       FeatureMap.Entry oldObject = (FeatureMap.Entry)this.data[entryIndex];
/*  175 */       FeatureMap.Entry entry = createEntry(feature, resolved);
/*  176 */       assign(entryIndex, validate(entryIndex, entry));
/*  177 */       didSet(entryIndex, entry, oldObject);
/*      */       
/*  179 */       if (isNotificationRequired()) {
/*      */         
/*  181 */         NotificationImpl notifications = 
/*  182 */           createNotification(
/*  183 */             9, 
/*  184 */             entry.getEStructuralFeature(), 
/*  185 */             object, 
/*  186 */             resolved, 
/*  187 */             index, 
/*  188 */             false);
/*      */         
/*  190 */         notifications.add((Notification)createNotification(9, oldObject, entry, index, false));
/*  191 */         notifications.dispatch();
/*      */       } 
/*      */       
/*  194 */       return resolved;
/*      */     } 
/*      */     
/*  197 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EObject resolveProxy(EObject eObject) {
/*  203 */     return this.owner.eResolveProxy((InternalEObject)eObject);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getModCount() {
/*  208 */     return this.modCount;
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature getEStructuralFeature(int index) {
/*  213 */     return ((FeatureMap.Entry)get(index)).getEStructuralFeature();
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getValue(int index) {
/*  218 */     return ((FeatureMap.Entry)get(index)).getValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public Object setValue(int index, Object value) {
/*  223 */     return set(index, createEntry(getEStructuralFeature(index), value)).getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain shadowAdd(FeatureMap.Entry object, NotificationChain notifications) {
/*  229 */     return shadowAdd((FeatureMap.Entry.Internal)object, notifications);
/*      */   }
/*      */   
/*      */   public NotificationChain shadowAdd(FeatureMap.Entry.Internal entry, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl1;
/*  234 */     EStructuralFeature feature = entry.getEStructuralFeature();
/*  235 */     Object value = entry.getValue();
/*      */     
/*  237 */     NotificationImpl notification = 
/*  238 */       feature.isMany() ? 
/*  239 */       createNotification(
/*  240 */         3, 
/*  241 */         feature, 
/*  242 */         (Object)null, 
/*  243 */         value, 
/*  244 */         indexOf(feature, value), 
/*  245 */         true) : 
/*  246 */       createNotification(
/*  247 */         1, 
/*  248 */         feature, 
/*  249 */         feature.getDefaultValue(), 
/*  250 */         value, 
/*  251 */         -1, 
/*  252 */         true);
/*      */     
/*  254 */     if (notifications != null) {
/*      */       
/*  256 */       notifications.add((Notification)notification);
/*      */     }
/*      */     else {
/*      */       
/*  260 */       notificationImpl1 = notification;
/*      */     } 
/*  262 */     return (NotificationChain)notificationImpl1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain inverseAdd(FeatureMap.Entry object, NotificationChain notifications) {
/*  268 */     return inverseAdd((FeatureMap.Entry.Internal)object, notifications);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain inverseAdd(FeatureMap.Entry.Internal entry, NotificationChain notifications) {
/*  273 */     return entry.inverseAdd(this.owner, this.featureID, notifications);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain shadowRemove(FeatureMap.Entry object, NotificationChain notifications) {
/*  279 */     return shadowRemove((FeatureMap.Entry.Internal)object, notifications);
/*      */   }
/*      */   
/*      */   public NotificationChain shadowRemove(FeatureMap.Entry.Internal entry, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl1;
/*  284 */     EStructuralFeature feature = entry.getEStructuralFeature();
/*  285 */     Object value = entry.getValue();
/*  286 */     NotificationImpl notification = 
/*  287 */       feature.isMany() ? 
/*  288 */       createNotification(
/*  289 */         4, 
/*  290 */         feature, 
/*  291 */         value, 
/*  292 */         (Object)null, 
/*  293 */         indexOf(feature, value), 
/*  294 */         true) : 
/*  295 */       createNotification(
/*  296 */         feature.isUnsettable() ? 2 : 1, 
/*  297 */         feature, 
/*  298 */         value, 
/*  299 */         feature.getDefaultValue(), 
/*  300 */         -1, 
/*  301 */         true);
/*      */     
/*  303 */     if (notifications != null) {
/*      */       
/*  305 */       notifications.add((Notification)notification);
/*      */     }
/*      */     else {
/*      */       
/*  309 */       notificationImpl1 = notification;
/*      */     } 
/*      */     
/*  312 */     return (NotificationChain)notificationImpl1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain inverseRemove(FeatureMap.Entry object, NotificationChain notifications) {
/*  318 */     return inverseRemove((FeatureMap.Entry.Internal)object, notifications);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain inverseRemove(FeatureMap.Entry.Internal entry, NotificationChain notifications) {
/*  323 */     return entry.inverseRemove(this.owner, this.featureID, notifications);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain shadowSet(FeatureMap.Entry oldObject, FeatureMap.Entry newObject, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*  329 */     if (isNotificationRequired()) {
/*      */       
/*  331 */       EStructuralFeature feature = oldObject.getEStructuralFeature();
/*  332 */       Object oldValue = oldObject.getValue();
/*  333 */       Object newValue = newObject.getValue();
/*  334 */       NotificationImpl notification = 
/*  335 */         createNotification(
/*  336 */           1, 
/*  337 */           feature, 
/*  338 */           oldValue, 
/*  339 */           newValue, 
/*  340 */           feature.isMany() ? indexOf(feature, newValue) : -1, 
/*  341 */           true);
/*      */       
/*  343 */       if (notifications != null) {
/*      */         
/*  345 */         notifications.add((Notification)notification);
/*      */       }
/*      */       else {
/*      */         
/*  349 */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*  352 */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */   
/*      */   public NotificationChain inverseTouch(Object object, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*  357 */     if (isNotificationRequired()) {
/*      */       
/*  359 */       FeatureMap.Entry entry = (FeatureMap.Entry)object;
/*  360 */       EStructuralFeature feature = entry.getEStructuralFeature();
/*  361 */       Object value = entry.getValue();
/*  362 */       NotificationImpl notification = 
/*  363 */         createNotification(
/*  364 */           1, 
/*  365 */           feature, 
/*  366 */           value, 
/*  367 */           value, 
/*  368 */           feature.isMany() ? indexOf(feature, value) : -1, 
/*  369 */           true);
/*      */       
/*  371 */       if (notifications != null) {
/*      */         
/*  373 */         notifications.add((Notification)notification);
/*      */       }
/*      */       else {
/*      */         
/*  377 */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*      */     
/*  381 */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public FeatureMap.Entry move(int targetIndex, int sourceIndex) {
/*  387 */     if (!isNotificationRequired())
/*      */     {
/*  389 */       return (FeatureMap.Entry)doMove(targetIndex, sourceIndex);
/*      */     }
/*  391 */     if (targetIndex != sourceIndex) {
/*      */       
/*  393 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  394 */       FeatureMap.Entry sourceEntry = entries[sourceIndex];
/*  395 */       EStructuralFeature feature = sourceEntry.getEStructuralFeature();
/*  396 */       if (isMany(feature)) {
/*      */         
/*  398 */         FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  399 */         int featureTargetIndex = -1;
/*  400 */         int featureSourceIndex = -1;
/*  401 */         int count = 0;
/*  402 */         for (int i = 0, maxIndex = (targetIndex > sourceIndex) ? targetIndex : sourceIndex; i <= maxIndex; i++) {
/*      */           
/*  404 */           if (i == sourceIndex) {
/*      */             
/*  406 */             featureSourceIndex = count++;
/*      */           }
/*      */           else {
/*      */             
/*  410 */             FeatureMap.Entry entry = entries[i];
/*  411 */             boolean isValid = validator.isValid(entry.getEStructuralFeature());
/*  412 */             if (i == targetIndex)
/*      */             {
/*  414 */               featureTargetIndex = (i == maxIndex && !isValid) ? (count - 1) : count;
/*      */             }
/*      */             
/*  417 */             if (isValid)
/*      */             {
/*  419 */               count++;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  424 */         FeatureMap.Entry result = (FeatureMap.Entry)super.move(targetIndex, sourceIndex);
/*      */         
/*  426 */         if (featureSourceIndex != featureTargetIndex)
/*      */         {
/*  428 */           dispatchNotification(
/*  429 */               (Notification)new ENotificationImpl(
/*  430 */                 this.owner, 
/*  431 */                 7, 
/*  432 */                 feature, 
/*  433 */                 Integer.valueOf(featureSourceIndex), 
/*  434 */                 sourceEntry.getValue(), 
/*  435 */                 featureTargetIndex));
/*      */         }
/*  437 */         return result;
/*      */       } 
/*      */     } 
/*  440 */     return (FeatureMap.Entry)super.move(targetIndex, sourceIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public FeatureMap.Entry set(int index, FeatureMap.Entry object) {
/*  446 */     FeatureMap.Entry entry = object;
/*  447 */     EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*  448 */     if (isMany(entryFeature)) {
/*      */       
/*  450 */       if (entryFeature.isUnique()) {
/*      */         
/*  452 */         FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  453 */         for (int i = 0; i < this.size; i++)
/*      */         {
/*  455 */           FeatureMap.Entry otherEntry = entries[i];
/*  456 */           if (otherEntry.equals(entry) && i != index)
/*      */           {
/*  458 */             throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  465 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*  466 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  467 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  469 */         FeatureMap.Entry otherEntry = entries[i];
/*  470 */         if (validator.isValid(otherEntry.getEStructuralFeature()) && i != index)
/*      */         {
/*  472 */           throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  477 */     return doSet(index, object);
/*      */   }
/*      */ 
/*      */   
/*      */   public FeatureMap.Entry doSet(int index, FeatureMap.Entry object) {
/*  482 */     return (FeatureMap.Entry)super.set(index, object);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean add(FeatureMap.Entry object) {
/*  488 */     FeatureMap.Entry entry = object;
/*  489 */     EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*  490 */     if (isMany(entryFeature)) {
/*      */       
/*  492 */       if (entryFeature.isUnique() && contains(entryFeature, entry.getValue()))
/*      */       {
/*  494 */         return false;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  499 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*  500 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  501 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  503 */         FeatureMap.Entry otherEntry = entries[i];
/*  504 */         if (validator.isValid(otherEntry.getEStructuralFeature())) {
/*      */           
/*  506 */           if (otherEntry.equals(entry))
/*      */           {
/*  508 */             return false;
/*      */           }
/*      */ 
/*      */           
/*  512 */           doSet(i, object);
/*  513 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  519 */     return doAdd(object);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean doAdd(FeatureMap.Entry object) {
/*  524 */     return super.add(object);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int index, FeatureMap.Entry object) {
/*  530 */     FeatureMap.Entry entry = object;
/*  531 */     EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*  532 */     if (isMany(entryFeature)) {
/*      */       
/*  534 */       if (entryFeature.isUnique()) {
/*      */         
/*  536 */         FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  537 */         for (int i = 0; i < this.size; i++)
/*      */         {
/*  539 */           FeatureMap.Entry otherEntry = entries[i];
/*  540 */           if (otherEntry.equals(entry) && i != index)
/*      */           {
/*  542 */             throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  549 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*  550 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  551 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  553 */         FeatureMap.Entry otherEntry = entries[i];
/*  554 */         if (validator.isValid(otherEntry.getEStructuralFeature()))
/*      */         {
/*  556 */           throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  561 */     doAdd(index, object);
/*      */   }
/*      */ 
/*      */   
/*      */   public void doAdd(int index, FeatureMap.Entry object) {
/*  566 */     super.add(index, object);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(Collection<? extends FeatureMap.Entry> collection) {
/*  572 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/*  573 */     for (FeatureMap.Entry entry : collection) {
/*      */       
/*  575 */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*  576 */       if (isMany(entryFeature)) {
/*      */         
/*  578 */         if (!entryFeature.isUnique() || (!contains(entryFeature, entry.getValue()) && !basicEList.contains(entry)))
/*      */         {
/*  580 */           basicEList.add(entry);
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*  585 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*  586 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  587 */       boolean include = true;
/*  588 */       for (int j = 0; j < this.size; j++) {
/*      */         
/*  590 */         FeatureMap.Entry otherEntry = entries[j];
/*  591 */         if (validator.isValid(otherEntry.getEStructuralFeature())) {
/*      */           
/*  593 */           doSet(j, entry);
/*  594 */           include = false;
/*      */           break;
/*      */         } 
/*      */       } 
/*  598 */       if (include)
/*      */       {
/*  600 */         basicEList.add(entry);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  605 */     return doAddAll((Collection<? extends FeatureMap.Entry>)basicEList);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean doAddAll(Collection<? extends FeatureMap.Entry> collection) {
/*  610 */     return super.addAll(collection);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(int index, Collection<? extends FeatureMap.Entry> collection) {
/*  616 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/*  617 */     for (FeatureMap.Entry entry : collection) {
/*      */       
/*  619 */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*  620 */       if (isMany(entryFeature)) {
/*      */         
/*  622 */         if (!entryFeature.isUnique() || (!contains(entryFeature, entry.getValue()) && !basicEList.contains(entry)))
/*      */         {
/*  624 */           basicEList.add(entry);
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*  629 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*  630 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  631 */       boolean include = true;
/*  632 */       for (int j = 0; j < this.size; j++) {
/*      */         
/*  634 */         FeatureMap.Entry otherEntry = entries[j];
/*  635 */         if (validator.isValid(otherEntry.getEStructuralFeature())) {
/*      */           
/*  637 */           doSet(j, entry);
/*  638 */           include = false;
/*      */           break;
/*      */         } 
/*      */       } 
/*  642 */       if (include)
/*      */       {
/*  644 */         basicEList.add(entry);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  649 */     return doAddAll(index, (Collection<? extends FeatureMap.Entry>)basicEList);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean doAddAll(int index, Collection<? extends FeatureMap.Entry> collection) {
/*  654 */     return super.addAll(index, collection);
/*      */   }
/*      */ 
/*      */   
/*      */   public int size(EStructuralFeature feature) {
/*  659 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  660 */     int result = 0;
/*  661 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  662 */     for (int i = 0; i < this.size; i++) {
/*      */       
/*  664 */       FeatureMap.Entry entry = entries[i];
/*  665 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/*  667 */         result++;
/*      */       }
/*      */     } 
/*  670 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEmpty(EStructuralFeature feature) {
/*  675 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  676 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  677 */     for (int i = 0; i < this.size; i++) {
/*      */       
/*  679 */       FeatureMap.Entry entry = entries[i];
/*  680 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/*  682 */         return false;
/*      */       }
/*      */     } 
/*  685 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean contains(EStructuralFeature feature, Object object) {
/*  690 */     return contains(feature, object, isResolveProxies(feature));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean basicContains(EStructuralFeature feature, Object object) {
/*  695 */     return contains(feature, object, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean contains(EStructuralFeature feature, Object object, boolean resolve) {
/*  700 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  701 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  702 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/*  704 */       for (int i = 0; i < this.size; i++)
/*      */       {
/*  706 */         FeatureMap.Entry entry = entries[i];
/*  707 */         if (validator.isValid(entry.getEStructuralFeature()) && entry.equals(object))
/*      */         {
/*  709 */           return true;
/*      */         }
/*      */       }
/*      */     
/*  713 */     } else if (object != null) {
/*      */       int i;
/*  715 */       for (i = 0; i < this.size; i++) {
/*      */         
/*  717 */         FeatureMap.Entry entry = entries[i];
/*  718 */         if (validator.isValid(entry.getEStructuralFeature()) && object.equals(entry.getValue()))
/*      */         {
/*  720 */           return true;
/*      */         }
/*      */       } 
/*  723 */       if (resolve)
/*      */       {
/*  725 */         for (i = 0; i < this.size; i++)
/*      */         {
/*  727 */           FeatureMap.Entry entry = entries[i];
/*  728 */           if (validator.isValid(entry.getEStructuralFeature()) && object == resolveProxy((EObject)entry.getValue()))
/*      */           {
/*  730 */             return true;
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */     } else {
/*      */       
/*  737 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  739 */         FeatureMap.Entry entry = entries[i];
/*  740 */         if (validator.isValid(entry.getEStructuralFeature()) && entry.getValue() == null)
/*      */         {
/*  742 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  747 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean containsAll(EStructuralFeature feature, Collection<?> collection) {
/*  752 */     for (Iterator<?> i = collection.iterator(); i.hasNext();) {
/*      */       
/*  754 */       if (!contains(feature, i.next()))
/*      */       {
/*  756 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  760 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean basicContainsAll(EStructuralFeature feature, Collection<?> collection) {
/*  765 */     for (Iterator<?> i = collection.iterator(); i.hasNext();) {
/*      */       
/*  767 */       if (!basicContains(feature, i.next()))
/*      */       {
/*  769 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  773 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int indexOf(EStructuralFeature feature, Object object) {
/*  778 */     return indexOf(feature, object, isResolveProxies(feature));
/*      */   }
/*      */ 
/*      */   
/*      */   public int basicIndexOf(EStructuralFeature feature, Object object) {
/*  783 */     return indexOf(feature, object, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int indexOf(EStructuralFeature feature, Object object, boolean resolve) {
/*  788 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  789 */     int result = 0;
/*  790 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  791 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/*  793 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  795 */         FeatureMap.Entry entry = entries[i];
/*  796 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/*  798 */           if (entry.equals(object))
/*      */           {
/*  800 */             return result;
/*      */           }
/*  802 */           result++;
/*      */         }
/*      */       
/*      */       } 
/*  806 */     } else if (object != null) {
/*      */       int i;
/*  808 */       for (i = 0; i < this.size; i++) {
/*      */         
/*  810 */         FeatureMap.Entry entry = entries[i];
/*  811 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/*  813 */           if (object.equals(entry.getValue()))
/*      */           {
/*  815 */             return result;
/*      */           }
/*  817 */           result++;
/*      */         } 
/*      */       } 
/*  820 */       if (resolve) {
/*      */         
/*  822 */         result = 0;
/*  823 */         for (i = 0; i < this.size; i++) {
/*      */           
/*  825 */           FeatureMap.Entry entry = entries[i];
/*  826 */           if (validator.isValid(entry.getEStructuralFeature()))
/*      */           {
/*  828 */             if (object == resolveProxy((EObject)entry.getValue()))
/*      */             {
/*  830 */               return result;
/*      */             }
/*  832 */             result++;
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  839 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  841 */         FeatureMap.Entry entry = entries[i];
/*  842 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/*  844 */           if (entry.getValue() == null)
/*      */           {
/*  846 */             return result;
/*      */           }
/*  848 */           result++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  853 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int lastIndexOf(EStructuralFeature feature, Object object) {
/*  858 */     return lastIndexOf(feature, object, isResolveProxies(feature));
/*      */   }
/*      */ 
/*      */   
/*      */   public int basicLastIndexOf(EStructuralFeature feature, Object object) {
/*  863 */     return lastIndexOf(feature, object, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int lastIndexOf(EStructuralFeature feature, Object object, boolean resolve) {
/*  868 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*  869 */     int result = -1;
/*  870 */     int count = 0;
/*  871 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/*  872 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/*  874 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  876 */         FeatureMap.Entry entry = entries[i];
/*  877 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/*  879 */           if (entry.equals(object))
/*      */           {
/*  881 */             result = count;
/*      */           }
/*  883 */           count++;
/*      */         }
/*      */       
/*      */       } 
/*  887 */     } else if (object != null) {
/*      */       int i;
/*  889 */       for (i = 0; i < this.size; i++) {
/*      */         
/*  891 */         FeatureMap.Entry entry = entries[i];
/*  892 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/*  894 */           if (object.equals(entry.getValue()))
/*      */           {
/*  896 */             result = count;
/*      */           }
/*  898 */           count++;
/*      */         } 
/*      */       } 
/*  901 */       if (resolve) {
/*      */         
/*  903 */         result = -1;
/*  904 */         count = 0;
/*  905 */         for (i = 0; i < this.size; i++) {
/*      */           
/*  907 */           FeatureMap.Entry entry = entries[i];
/*  908 */           if (validator.isValid(entry.getEStructuralFeature()))
/*      */           {
/*  910 */             if (object == resolveProxy((EObject)entry.getValue()))
/*      */             {
/*  912 */               result = count;
/*      */             }
/*  914 */             count++;
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  921 */       for (int i = 0; i < this.size; i++) {
/*      */         
/*  923 */         FeatureMap.Entry entry = entries[i];
/*  924 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/*  926 */           if (entry.getValue() == null)
/*      */           {
/*  928 */             result = count;
/*      */           }
/*  930 */           count++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  935 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterator<Object> iterator(EStructuralFeature feature) {
/*  940 */     return 
/*  941 */       (feature instanceof EReference && ((EReference)feature).isResolveProxies()) ? 
/*  942 */       new ResolvingFeatureEIterator(feature, this) : 
/*  943 */       new FeatureEIterator(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public ListIterator<Object> listIterator(EStructuralFeature feature) {
/*  948 */     return 
/*  949 */       (feature instanceof EReference && ((EReference)feature).isResolveProxies()) ? 
/*  950 */       new ResolvingFeatureEIterator(feature, this) : 
/*  951 */       new FeatureEIterator(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public ListIterator<Object> listIterator(EStructuralFeature feature, int index) {
/*  956 */     ListIterator<Object> result = 
/*  957 */       (feature instanceof EReference && ((EReference)feature).isResolveProxies()) ? 
/*  958 */       new ResolvingFeatureEIterator(feature, this) : 
/*  959 */       new FeatureEIterator(feature, this);
/*  960 */     for (int i = 0; i < index; i++)
/*      */     {
/*  962 */       result.next();
/*      */     }
/*  964 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public FeatureMap.ValueListIterator<Object> valueListIterator() {
/*  969 */     return new ValueListIteratorImpl();
/*      */   }
/*      */ 
/*      */   
/*      */   public FeatureMap.ValueListIterator<Object> valueListIterator(int index) {
/*  974 */     return new ValueListIteratorImpl(index);
/*      */   }
/*      */   
/*      */   protected class ValueListIteratorImpl<E1>
/*      */     extends AbstractEList<FeatureMap.Entry>.EListIterator<E1>
/*      */     implements FeatureMap.ValueListIterator<E1> {
/*      */     public ValueListIteratorImpl() {
/*  981 */       super((AbstractEList)BasicFeatureMap.this);
/*      */     }
/*      */ 
/*      */     
/*      */     public ValueListIteratorImpl(int index) {
/*  986 */       super((AbstractEList)BasicFeatureMap.this, index);
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature feature() {
/*  991 */       if (this.lastCursor == -1)
/*      */       {
/*  993 */         throw new IllegalStateException();
/*      */       }
/*  995 */       return BasicFeatureMap.this.getEStructuralFeature(this.lastCursor);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E1 next() {
/* 1002 */       return (E1)((FeatureMap.Entry)doNext()).getValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public E1 previous() {
/* 1009 */       return (E1)((FeatureMap.Entry)doPrevious()).getValue();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void add(E1 value) {
/* 1015 */       doAdd(FeatureMapUtil.createEntry(feature(), value));
/*      */     }
/*      */ 
/*      */     
/*      */     public void add(EStructuralFeature eStructuralFeature, Object value) {
/* 1020 */       doAdd(FeatureMapUtil.createEntry(eStructuralFeature, value));
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
/*      */   public <T> EList<T> list(EStructuralFeature feature) {
/* 1034 */     return 
/* 1035 */       FeatureMapUtil.isFeatureMap(feature) ? 
/* 1036 */       new FeatureMapUtil.FeatureFeatureMap(feature, this) : 
/* 1037 */       new FeatureMapUtil.FeatureEList<T>(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public EStructuralFeature.Setting setting(EStructuralFeature feature) {
/* 1042 */     return 
/* 1043 */       isMany(feature) ? 
/* 1044 */       (EStructuralFeature.Setting)list(feature) : 
/* 1045 */       new FeatureMapUtil.FeatureValue(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Object> basicList(EStructuralFeature feature) {
/* 1050 */     return new FeatureMapUtil.FeatureEList.Basic(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterator<Object> basicIterator(EStructuralFeature feature) {
/* 1055 */     return new FeatureEIterator(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public ListIterator<Object> basicListIterator(EStructuralFeature feature) {
/* 1060 */     return new FeatureEIterator(feature, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public ListIterator<Object> basicListIterator(EStructuralFeature feature, int index) {
/* 1065 */     ListIterator<Object> result = new FeatureEIterator(feature, this);
/* 1066 */     for (int i = 0; i < index; i++)
/*      */     {
/* 1068 */       result.next();
/*      */     }
/* 1070 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object[] toArray(EStructuralFeature feature) {
/* 1075 */     return toArray(feature, isResolveProxies(feature));
/*      */   }
/*      */ 
/*      */   
/*      */   public Object[] basicToArray(EStructuralFeature feature) {
/* 1080 */     return toArray(feature, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object[] toArray(EStructuralFeature feature, boolean resolve) {
/* 1085 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList();
/* 1086 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1087 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1088 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/* 1090 */       for (int i = 0; i < this.size; i++)
/*      */       {
/* 1092 */         FeatureMap.Entry entry = entries[i];
/* 1093 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1095 */           basicEList.add(entry);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1101 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1103 */         FeatureMap.Entry entry = entries[i];
/* 1104 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 1106 */           Object value = entry.getValue();
/* 1107 */           basicEList.add(resolve ? resolveProxy(feature, i, basicEList.size(), value) : value);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1111 */     return basicEList.toArray();
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> T[] toArray(EStructuralFeature feature, Object[] array) {
/* 1116 */     return toArray(feature, (T[])array, isResolveProxies(feature));
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> T[] basicToArray(EStructuralFeature feature, Object[] array) {
/* 1121 */     return toArray(feature, (T[])array, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected <T> T[] toArray(EStructuralFeature feature, Object[] array, boolean resolve) {
/* 1126 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList();
/* 1127 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1128 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1129 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/* 1131 */       for (int i = 0; i < this.size; i++)
/*      */       {
/* 1133 */         FeatureMap.Entry entry = entries[i];
/* 1134 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1136 */           basicEList.add(entry);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1142 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1144 */         FeatureMap.Entry entry = entries[i];
/* 1145 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 1147 */           Object value = entry.getValue();
/* 1148 */           basicEList.add(resolve ? resolveProxy(feature, i, basicEList.size(), value) : value);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1152 */     return basicEList.toArray((T[])array);
/*      */   }
/*      */ 
/*      */   
/*      */   public void set(EStructuralFeature feature, Object object) {
/* 1157 */     if (isMany(feature)) {
/*      */       
/* 1159 */       EList<?> eList = list(feature);
/* 1160 */       eList.clear();
/* 1161 */       eList.addAll((Collection)object);
/*      */     }
/*      */     else {
/*      */       
/* 1165 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1166 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1167 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1169 */         FeatureMap.Entry entry = entries[i];
/* 1170 */         EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 1171 */         if (validator.isValid(entryFeature)) {
/*      */           
/* 1173 */           if (entryFeature == XMLTypeFeatures.TEXT || entryFeature == XMLTypeFeatures.CDATA) {
/*      */             
/* 1175 */             boolean shouldUnset = shouldUnset(feature, object);
/* 1176 */             int index = i;
/* 1177 */             if (shouldUnset) {
/*      */               
/* 1179 */               remove(i);
/*      */             }
/*      */             else {
/*      */               
/* 1183 */               i++;
/*      */             } 
/* 1185 */             while (i < this.size) {
/*      */               
/* 1187 */               entry = entries[i];
/* 1188 */               entryFeature = entry.getEStructuralFeature();
/* 1189 */               if (entryFeature == XMLTypeFeatures.TEXT || entryFeature == XMLTypeFeatures.CDATA) {
/*      */                 
/* 1191 */                 remove(i);
/*      */                 
/*      */                 continue;
/*      */               } 
/* 1195 */               i++;
/*      */             } 
/*      */             
/* 1198 */             if (!shouldUnset)
/*      */             {
/* 1200 */               doSet(index, createEntry(feature, object));
/*      */             }
/*      */           }
/* 1203 */           else if (shouldUnset(feature, object)) {
/*      */             
/* 1205 */             remove(i);
/*      */           }
/*      */           else {
/*      */             
/* 1209 */             doSet(i, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */           } 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 1215 */       if (!shouldUnset(feature, object))
/*      */       {
/* 1217 */         doAdd(FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean shouldUnset(EStructuralFeature feature, Object value) {
/* 1226 */     if (feature.isUnsettable())
/*      */     {
/* 1228 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1232 */     if (feature.getUpperBound() != -2) {
/*      */       
/* 1234 */       Object defaultValue = feature.getDefaultValue();
/* 1235 */       return (defaultValue == null) ? ((value == null)) : defaultValue.equals(value);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1240 */     if (feature.getEContainingClass() == this.owner.eClass())
/*      */     {
/* 1242 */       return (value == null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1248 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int index, EStructuralFeature feature, Object object) {
/* 1254 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1255 */     if (isMany(feature)) {
/*      */       
/* 1257 */       if (feature.isUnique() && contains(feature, object))
/*      */       {
/* 1259 */         throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1264 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1265 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1266 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1268 */         FeatureMap.Entry entry = entries[i];
/* 1269 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1271 */           if (isFeatureMap ? entry.equals(object) : ((object == null) ? (entry.getValue() == null) : object.equals(entry.getValue())))
/*      */           {
/* 1273 */             throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1279 */     doAdd(index, isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean add(EStructuralFeature feature, Object object) {
/* 1284 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1285 */     if (isMany(feature)) {
/*      */       
/* 1287 */       if (feature.isUnique() && contains(feature, object))
/*      */       {
/* 1289 */         return false;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1294 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1295 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1296 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1298 */         FeatureMap.Entry entry = entries[i];
/* 1299 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 1301 */           if (isFeatureMap ? entry.equals(object) : ((object == null) ? (entry.getValue() == null) : object.equals(entry.getValue())))
/*      */           {
/* 1303 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1307 */           doSet(i, isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/* 1308 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1314 */     return doAdd(isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */   }
/*      */ 
/*      */   
/*      */   public void add(EStructuralFeature feature, int index, Object object) {
/* 1319 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1320 */     if (isMany(feature)) {
/*      */       
/* 1322 */       if (feature.isUnique() && contains(feature, object))
/*      */       {
/* 1324 */         throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1329 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1330 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1331 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1333 */         FeatureMap.Entry entry = entries[i];
/* 1334 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1336 */           throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1341 */     doAdd(entryIndex(feature, index), isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAll(int index, EStructuralFeature feature, Collection<?> collection) {
/* 1346 */     if (collection.size() == 0)
/*      */     {
/* 1348 */       return false;
/*      */     }
/* 1350 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1351 */     Collection<FeatureMap.Entry> entryCollection = 
/* 1352 */       isFeatureMap ? 
/* 1353 */       (Collection)collection : 
/* 1354 */       (Collection<FeatureMap.Entry>)new BasicEList(collection.size());
/* 1355 */     if (isMany(feature)) {
/*      */       
/* 1357 */       if (feature.isUnique()) {
/*      */         
/* 1359 */         for (Object object : collection) {
/*      */           
/* 1361 */           if (!contains(feature, object))
/*      */           {
/* 1363 */             FeatureMap.Entry entry = createEntry(feature, object);
/* 1364 */             if (!entryCollection.contains(entry))
/*      */             {
/* 1366 */               entryCollection.add(entry);
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/* 1371 */       } else if (!isFeatureMap) {
/*      */         
/* 1373 */         for (Object object : collection)
/*      */         {
/* 1375 */           FeatureMap.Entry entry = createEntry(feature, object);
/* 1376 */           entryCollection.add(entry);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1382 */       if (collection.size() > 1)
/*      */       {
/* 1384 */         throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */       }
/*      */       
/* 1387 */       if (isFeatureMap) {
/*      */         
/* 1389 */         if (contains(feature, collection.iterator().next()))
/*      */         {
/* 1391 */           return false;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1396 */         FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1397 */         FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1398 */         for (int i = 0; i < this.size; i++) {
/*      */           
/* 1400 */           FeatureMap.Entry entry1 = entries[i];
/* 1401 */           if (validator.isValid(entry1.getEStructuralFeature())) {
/*      */             
/* 1403 */             if (collection.contains(entry1.getValue()))
/*      */             {
/* 1405 */               return false;
/*      */             }
/*      */ 
/*      */             
/* 1409 */             throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */           } 
/*      */         } 
/*      */         
/* 1413 */         FeatureMap.Entry entry = createEntry(feature, collection.iterator().next());
/* 1414 */         entryCollection.add(entry);
/*      */       } 
/*      */     } 
/*      */     
/* 1418 */     return doAddAll(index, entryCollection);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAll(EStructuralFeature feature, Collection<?> collection) {
/* 1423 */     if (collection.size() == 0)
/*      */     {
/* 1425 */       return false;
/*      */     }
/* 1427 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1428 */     Collection<FeatureMap.Entry> entryCollection = 
/* 1429 */       isFeatureMap ? 
/* 1430 */       (Collection)collection : 
/* 1431 */       (Collection<FeatureMap.Entry>)new BasicEList(collection.size());
/* 1432 */     if (isMany(feature)) {
/*      */       
/* 1434 */       if (feature.isUnique()) {
/*      */         
/* 1436 */         for (Object object : collection) {
/*      */           
/* 1438 */           if (!contains(feature, object))
/*      */           {
/* 1440 */             FeatureMap.Entry entry = createEntry(feature, object);
/* 1441 */             if (!entryCollection.contains(entry))
/*      */             {
/* 1443 */               entryCollection.add(entry);
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/* 1448 */       } else if (!isFeatureMap) {
/*      */         
/* 1450 */         for (Object object : collection)
/*      */         {
/* 1452 */           FeatureMap.Entry entry = createEntry(feature, object);
/* 1453 */           entryCollection.add(entry);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1459 */       if (collection.size() > 1)
/*      */       {
/* 1461 */         throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */       }
/*      */       
/* 1464 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1465 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1466 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1468 */         FeatureMap.Entry entry = entries[i];
/* 1469 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 1471 */           if (collection.contains(isFeatureMap ? entry : entry.getValue()))
/*      */           {
/* 1473 */             return false;
/*      */           }
/*      */ 
/*      */           
/* 1477 */           for (Object object : collection)
/*      */           {
/* 1479 */             doSet(i, isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */           }
/* 1481 */           return true;
/*      */         } 
/*      */       } 
/*      */       
/* 1485 */       if (!isFeatureMap) {
/*      */         
/* 1487 */         FeatureMap.Entry entry = createEntry(feature, collection.iterator().next());
/* 1488 */         entryCollection.add(entry);
/*      */       } 
/*      */     } 
/*      */     
/* 1492 */     return doAddAll(entryCollection);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAll(EStructuralFeature feature, int index, Collection<?> collection) {
/* 1497 */     if (collection.size() == 0)
/*      */     {
/* 1499 */       return false;
/*      */     }
/* 1501 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1502 */     Collection<FeatureMap.Entry> entryCollection = 
/* 1503 */       isFeatureMap ? 
/* 1504 */       (Collection)collection : 
/* 1505 */       (Collection<FeatureMap.Entry>)new BasicEList(collection.size());
/* 1506 */     if (isMany(feature)) {
/*      */       
/* 1508 */       if (feature.isUnique()) {
/*      */         
/* 1510 */         for (Object object : collection) {
/*      */           
/* 1512 */           if (!contains(feature, object))
/*      */           {
/* 1514 */             FeatureMap.Entry entry = createEntry(feature, object);
/* 1515 */             entryCollection.add(entry);
/*      */           }
/*      */         
/*      */         } 
/* 1519 */       } else if (!isFeatureMap) {
/*      */         
/* 1521 */         for (Object object : collection)
/*      */         {
/* 1523 */           FeatureMap.Entry entry = createEntry(feature, object);
/* 1524 */           entryCollection.add(entry);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1530 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1531 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1532 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1534 */         FeatureMap.Entry entry = entries[i];
/* 1535 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1537 */           throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */         }
/*      */       } 
/*      */       
/* 1541 */       if (collection.size() > 1)
/*      */       {
/* 1543 */         throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */       }
/*      */       
/* 1546 */       if (!isFeatureMap) {
/*      */         
/* 1548 */         FeatureMap.Entry entry = createEntry(feature, collection.iterator().next());
/* 1549 */         entryCollection.add(entry);
/*      */       } 
/*      */     } 
/*      */     
/* 1553 */     return doAddAll(entryIndex(feature, index), entryCollection);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addUnique(EStructuralFeature feature, Object object) {
/* 1558 */     this.modCount = -1;
/* 1559 */     addUnique(createRawEntry(feature, object));
/*      */   }
/*      */ 
/*      */   
/*      */   public void addUnique(EStructuralFeature feature, int index, Object object) {
/* 1564 */     this.modCount = -1;
/* 1565 */     addUnique(entryIndex(feature, index), createRawEntry(feature, object));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUnique(FeatureMap.Entry object) {
/* 1572 */     this.modCount++;
/* 1573 */     validate(this.size, object);
/*      */     
/* 1575 */     addUnique((FeatureMap.Entry.Internal)object);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addUnique(FeatureMap.Entry.Internal entry) {
/* 1580 */     this.modCount = -1;
/* 1581 */     if (isNotificationRequired()) {
/*      */       
/* 1583 */       int index = this.size;
/* 1584 */       boolean oldIsSet = isSet();
/* 1585 */       doAddUnique(entry);
/* 1586 */       NotificationImpl notification = createNotification(3, (Object)null, entry, index, oldIsSet);
/* 1587 */       if (hasInverse()) {
/*      */         
/* 1589 */         NotificationChain notifications = inverseAdd(entry, (NotificationChain)null);
/* 1590 */         notifications = shadowAdd(entry, notifications);
/*      */         
/* 1592 */         if (notifications == null)
/*      */         {
/* 1594 */           dispatchNotification((Notification)notification);
/*      */         }
/*      */         else
/*      */         {
/* 1598 */           notifications.add((Notification)notification);
/* 1599 */           notifications.dispatch();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1604 */         dispatchNotification((Notification)notification);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1609 */       doAddUnique(entry);
/* 1610 */       NotificationChain notifications = inverseAdd(entry, (NotificationChain)null);
/* 1611 */       if (notifications != null) notifications.dispatch();
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(Collection<? extends FeatureMap.Entry> collection) {
/* 1618 */     this.modCount = -1;
/* 1619 */     return super.addAllUnique(collection);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(FeatureMap.Entry.Internal[] entries, int start, int end) {
/* 1624 */     return addAllUnique(this.size, entries, start, end);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAllUnique(int index, FeatureMap.Entry.Internal[] entries, int start, int end) {
/* 1629 */     this.modCount = -1;
/*      */     
/* 1631 */     int collectionSize = end - start;
/* 1632 */     if (collectionSize == 0)
/*      */     {
/* 1634 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1638 */     if (isNotificationRequired()) {
/*      */       NotificationImpl notification;
/* 1640 */       boolean oldIsSet = isSet();
/* 1641 */       doAddAllUnique(index, (Object[])entries, start, end);
/*      */       
/* 1643 */       if (collectionSize == 0) {
/*      */         
/* 1645 */         notification = createNotification(3, (Object)null, entries[0], index, oldIsSet);
/*      */ 
/*      */       
/*      */       }
/* 1649 */       else if (start != 0 || end != entries.length) {
/*      */         
/* 1651 */         Object[] actualObjects = new Object[collectionSize];
/* 1652 */         for (int k = 0, j = start; j < end; k++, j++)
/*      */         {
/* 1654 */           actualObjects[k] = entries[j];
/*      */         }
/* 1656 */         notification = createNotification(5, (Object)null, actualObjects, index, oldIsSet);
/*      */       }
/*      */       else {
/*      */         
/* 1660 */         notification = createNotification(5, (Object)null, entries, index, oldIsSet);
/*      */       } 
/*      */       
/* 1663 */       NotificationChain notifications = null;
/* 1664 */       for (int i = start; i < end; i++) {
/*      */         
/* 1666 */         FeatureMap.Entry.Internal value = entries[i];
/* 1667 */         notifications = inverseAdd(value, notifications);
/* 1668 */         notifications = shadowAdd(value, notifications);
/*      */       } 
/* 1670 */       if (notifications == null)
/*      */       {
/* 1672 */         dispatchNotification((Notification)notification);
/*      */       }
/*      */       else
/*      */       {
/* 1676 */         notifications.add((Notification)notification);
/* 1677 */         notifications.dispatch();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1682 */       doAddAllUnique(index, (Object[])entries, start, end);
/* 1683 */       NotificationChain notifications = null;
/* 1684 */       for (int i = start; i < end; i++)
/*      */       {
/* 1686 */         notifications = inverseAdd(entries[i], notifications);
/*      */       }
/* 1688 */       if (notifications != null) notifications.dispatch();
/*      */     
/*      */     } 
/* 1691 */     return true;
/*      */   }
/*      */   
/*      */   public NotificationChain basicAdd(EStructuralFeature feature, Object object, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*      */     NotificationChain notificationChain;
/* 1697 */     if (object == null) {
/*      */       
/* 1699 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1700 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1702 */         FeatureMap.Entry entry1 = entries[i];
/* 1703 */         if (entry1.getEStructuralFeature() == feature)
/*      */         {
/* 1705 */           return super.basicRemove(entry1, notifications);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1710 */     FeatureMap.Entry entry = FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object);
/*      */     
/* 1712 */     if (isNotificationRequired()) {
/*      */       
/* 1714 */       boolean oldIsSet = !isEmpty(feature);
/* 1715 */       notifications = basicAdd(entry, notifications);
/* 1716 */       NotificationImpl notification = 
/* 1717 */         feature.isMany() ? 
/* 1718 */         createNotification(
/* 1719 */           3, 
/* 1720 */           feature, 
/* 1721 */           (Object)null, 
/* 1722 */           object, 
/* 1723 */           indexOf(feature, object), 
/* 1724 */           oldIsSet) : 
/* 1725 */         createNotification(
/* 1726 */           1, 
/* 1727 */           feature, 
/* 1728 */           feature.getDefaultValue(), 
/* 1729 */           object, 
/* 1730 */           -1, 
/* 1731 */           oldIsSet);
/*      */       
/* 1733 */       if (notifications != null)
/*      */       {
/* 1735 */         notifications.add((Notification)notification);
/*      */       }
/*      */       else
/*      */       {
/* 1739 */         notificationImpl = notification;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1744 */       notificationChain = basicAdd(entry, (NotificationChain)notificationImpl);
/*      */     } 
/* 1746 */     return notificationChain;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean remove(EStructuralFeature feature, Object object) {
/* 1751 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1752 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1753 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/* 1755 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1757 */         FeatureMap.Entry entry = entries[i];
/* 1758 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1760 */           if (entry.equals(object))
/*      */           {
/* 1762 */             remove(i);
/* 1763 */             return true;
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/* 1768 */     } else if (object != null) {
/*      */       
/* 1770 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1772 */         FeatureMap.Entry entry = entries[i];
/* 1773 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1775 */           if (object.equals(entry.getValue()))
/*      */           {
/* 1777 */             remove(i);
/* 1778 */             return true;
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 1785 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1787 */         FeatureMap.Entry entry = entries[i];
/* 1788 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1790 */           if (entry.getValue() == null) {
/*      */             
/* 1792 */             remove(i);
/* 1793 */             return true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1799 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object remove(EStructuralFeature feature, int index) {
/* 1804 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1805 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1806 */     int count = 0;
/* 1807 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 1809 */       FeatureMap.Entry entry = entries[i];
/* 1810 */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         
/* 1812 */         if (count == index) {
/*      */           
/* 1814 */           remove(i);
/* 1815 */           return FeatureMapUtil.isFeatureMap(feature) ? entry : entry.getValue();
/*      */         } 
/* 1817 */         count++;
/*      */       } 
/*      */     } 
/*      */     
/* 1821 */     throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeAll(EStructuralFeature feature, Collection<?> collection) {
/* 1826 */     if (FeatureMapUtil.isFeatureMap(feature))
/*      */     {
/* 1828 */       return removeAll(collection);
/*      */     }
/*      */ 
/*      */     
/* 1832 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1833 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/* 1834 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1835 */     for (int i = this.size; --i >= 0; ) {
/*      */       
/* 1837 */       FeatureMap.Entry entry = entries[i];
/* 1838 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/* 1840 */         if (collection.contains(entry.getValue()))
/*      */         {
/* 1842 */           basicEList.add(entry);
/*      */         }
/*      */       }
/*      */     } 
/*      */     
/* 1847 */     return removeAll((Collection)basicEList);
/*      */   }
/*      */ 
/*      */   
/*      */   public NotificationChain basicRemove(EStructuralFeature feature, Object object, NotificationChain notifications) {
/*      */     NotificationChain notificationChain;
/* 1853 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1854 */     int count = 0;
/* 1855 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1856 */     FeatureMap.Entry match = null;
/* 1857 */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       
/* 1859 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1861 */         FeatureMap.Entry entry = entries[i];
/* 1862 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1864 */           if (entry.equals(object)) {
/*      */             
/* 1866 */             match = entry;
/*      */             break;
/*      */           } 
/* 1869 */           count++;
/*      */         }
/*      */       
/*      */       } 
/* 1873 */     } else if (object != null) {
/*      */       
/* 1875 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1877 */         FeatureMap.Entry entry = entries[i];
/* 1878 */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */         {
/* 1880 */           if (object.equals(entry.getValue())) {
/*      */             
/* 1882 */             match = entry;
/*      */             break;
/*      */           } 
/* 1885 */           count++;
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1891 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 1893 */         FeatureMap.Entry entry = entries[i];
/* 1894 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 1896 */           if (entry.getValue() == null) {
/*      */             
/* 1898 */             match = entry;
/*      */             break;
/*      */           } 
/* 1901 */           count++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1906 */     if (match != null) {
/*      */       NotificationImpl notificationImpl;
/* 1908 */       if (isNotificationRequired()) {
/*      */         
/* 1910 */         NotificationImpl notification = 
/* 1911 */           feature.isMany() ? 
/* 1912 */           createNotification(
/* 1913 */             4, 
/* 1914 */             feature, 
/* 1915 */             object, 
/* 1916 */             (Object)null, 
/* 1917 */             count, 
/* 1918 */             true) : 
/* 1919 */           createNotification(
/* 1920 */             feature.isUnsettable() ? 2 : 1, 
/* 1921 */             feature, 
/* 1922 */             object, 
/* 1923 */             feature.getDefaultValue(), 
/* 1924 */             -1, 
/* 1925 */             true);
/*      */         
/* 1927 */         if (notifications != null) {
/*      */           
/* 1929 */           notifications.add((Notification)notification);
/*      */         }
/*      */         else {
/*      */           
/* 1933 */           notificationImpl = notification;
/*      */         } 
/*      */       } 
/* 1936 */       notificationChain = basicRemove(match, (NotificationChain)notificationImpl);
/*      */     } 
/*      */     
/* 1939 */     return notificationChain;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean retainAll(EStructuralFeature feature, Collection<?> collection) {
/* 1944 */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/* 1945 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1946 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/* 1947 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1948 */     for (int i = this.size; --i >= 0; ) {
/*      */       
/* 1950 */       FeatureMap.Entry entry = entries[i];
/* 1951 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/* 1953 */         if (!collection.contains(isFeatureMap ? entry : entry.getValue()))
/*      */         {
/* 1955 */           basicEList.add(entry);
/*      */         }
/*      */       }
/*      */     } 
/*      */     
/* 1960 */     return removeAll((Collection)basicEList);
/*      */   }
/*      */ 
/*      */   
/*      */   public void clear(EStructuralFeature feature) {
/* 1965 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 1966 */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList();
/* 1967 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 1968 */     for (int i = this.size; --i >= 0; ) {
/*      */       
/* 1970 */       FeatureMap.Entry entry = entries[i];
/* 1971 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/* 1973 */         basicEList.add(entry);
/*      */       }
/*      */     } 
/*      */     
/* 1977 */     if (!removeAll((Collection)basicEList) && this.owner.eNotificationRequired())
/*      */     {
/* 1979 */       dispatchNotification(
/* 1980 */           feature.isMany() ? 
/* 1981 */           (Notification)createNotification(
/* 1982 */             6, 
/* 1983 */             feature, 
/* 1984 */             Collections.EMPTY_LIST, 
/* 1985 */             (Object)null, 
/* 1986 */             -1, 
/* 1987 */             false) : 
/* 1988 */           (Notification)createNotification(
/* 1989 */             feature.isUnsettable() ? 2 : 1, 
/* 1990 */             feature, 
/* 1991 */             (Object)null, 
/* 1992 */             (Object)null, 
/* 1993 */             -1, 
/* 1994 */             false));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void move(EStructuralFeature feature, int index, Object object) {
/* 2000 */     move(feature, index, indexOf(feature, object));
/*      */   }
/*      */ 
/*      */   
/*      */   public Object move(EStructuralFeature feature, int targetIndex, int sourceIndex) {
/* 2005 */     if (isMany(feature)) {
/*      */       
/* 2007 */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2008 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2009 */       Object result = null;
/* 2010 */       int entryTargetIndex = -1;
/* 2011 */       int entrySourceIndex = -1;
/* 2012 */       int count = 0;
/* 2013 */       for (int i = 0; i < this.size; i++) {
/*      */         
/* 2015 */         FeatureMap.Entry entry = entries[i];
/* 2016 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 2018 */           if (count == targetIndex)
/*      */           {
/* 2020 */             entryTargetIndex = i;
/*      */           }
/* 2022 */           if (count == sourceIndex) {
/*      */             
/* 2024 */             entrySourceIndex = i;
/* 2025 */             result = entry.getValue();
/*      */           } 
/* 2027 */           count++;
/*      */         } 
/*      */       } 
/* 2030 */       if (entryTargetIndex == -1)
/*      */       {
/* 2032 */         throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + count);
/*      */       }
/* 2034 */       if (entrySourceIndex == -1)
/*      */       {
/* 2036 */         throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + count);
/*      */       }
/*      */       
/* 2039 */       super.move(entryTargetIndex, entrySourceIndex);
/*      */       
/* 2041 */       if (isNotificationRequired())
/*      */       {
/* 2043 */         dispatchNotification(
/* 2044 */             (Notification)createNotification(
/* 2045 */               7, 
/* 2046 */               feature, 
/* 2047 */               Integer.valueOf(sourceIndex), 
/* 2048 */               result, 
/* 2049 */               targetIndex, 
/* 2050 */               true));
/*      */       }
/*      */       
/* 2053 */       return result;
/*      */     } 
/*      */ 
/*      */     
/* 2057 */     throw new IllegalArgumentException("The feature must be many-valued to support move");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(EStructuralFeature feature, boolean resolve) {
/* 2063 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2064 */     if (isMany(feature))
/*      */     {
/* 2066 */       return list(feature);
/*      */     }
/*      */ 
/*      */     
/* 2070 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2071 */     int count = 0;
/* 2072 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 2074 */       FeatureMap.Entry entry = entries[i];
/* 2075 */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/* 2076 */       if (validator.isValid(entryFeature)) {
/*      */         
/* 2078 */         if (FeatureMapUtil.isFeatureMap(feature))
/*      */         {
/* 2080 */           return entry;
/*      */         }
/* 2082 */         if (entryFeature == XMLTypeFeatures.TEXT || entryFeature == XMLTypeFeatures.CDATA) {
/*      */           
/* 2084 */           StringBuilder result = new StringBuilder(entry.getValue().toString());
/* 2085 */           while (++i < this.size) {
/*      */             
/* 2087 */             entry = entries[i];
/* 2088 */             entryFeature = entry.getEStructuralFeature();
/* 2089 */             if (entryFeature == XMLTypeFeatures.TEXT || entryFeature == XMLTypeFeatures.CDATA)
/*      */             {
/* 2091 */               result.append(entry.getValue().toString());
/*      */             }
/*      */           } 
/* 2094 */           return EcoreUtil.createFromString((EDataType)feature.getEType(), result.toString());
/*      */         } 
/*      */ 
/*      */         
/* 2098 */         Object value = entry.getValue();
/* 2099 */         if (value != null && resolve && isResolveProxies(feature))
/*      */         {
/* 2101 */           value = resolveProxy(feature, i, count, value);
/*      */         }
/* 2103 */         return value;
/*      */       } 
/*      */       
/* 2106 */       count++;
/*      */     } 
/*      */     
/* 2109 */     return feature.getDefaultValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(EStructuralFeature feature, int index, boolean resolve) {
/* 2115 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2116 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2117 */     if (isMany(feature)) {
/*      */       
/* 2119 */       int j = 0;
/* 2120 */       for (int k = 0; k < this.size; k++) {
/*      */         
/* 2122 */         FeatureMap.Entry entry = entries[k];
/* 2123 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 2125 */           if (j == index) {
/*      */             
/* 2127 */             if (FeatureMapUtil.isFeatureMap(feature))
/*      */             {
/* 2129 */               return entry;
/*      */             }
/*      */ 
/*      */             
/* 2133 */             Object value = entry.getValue();
/* 2134 */             if (value != null && resolve && isResolveProxies(feature))
/*      */             {
/* 2136 */               value = resolveProxy(feature, k, j, value);
/*      */             }
/* 2138 */             return value;
/*      */           } 
/*      */           
/* 2141 */           j++;
/*      */         } 
/*      */       } 
/* 2144 */       throw new IndexOutOfBoundsException("index=" + index + ", size=" + j);
/*      */     } 
/*      */ 
/*      */     
/* 2148 */     int count = 0;
/* 2149 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 2151 */       FeatureMap.Entry entry = entries[i];
/* 2152 */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         
/* 2154 */         if (FeatureMapUtil.isFeatureMap(feature))
/*      */         {
/* 2156 */           return entry;
/*      */         }
/*      */ 
/*      */         
/* 2160 */         Object value = entry.getValue();
/* 2161 */         if (value != null && resolve && isResolveProxies(feature))
/*      */         {
/* 2163 */           value = resolveProxy(feature, i, count, value);
/*      */         }
/* 2165 */         return value;
/*      */       } 
/*      */       
/* 2168 */       count++;
/*      */     } 
/*      */     
/* 2171 */     return feature.getDefaultValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object set(EStructuralFeature feature, int index, Object object) {
/* 2177 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2178 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2179 */     if (isMany(feature)) {
/*      */       
/* 2181 */       if (feature.isUnique()) {
/*      */         
/* 2183 */         int currentIndex = indexOf(feature, object);
/* 2184 */         if (currentIndex >= 0 && currentIndex != index)
/*      */         {
/* 2186 */           throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
/*      */         }
/*      */       } 
/*      */       
/* 2190 */       int count = 0;
/* 2191 */       for (int j = 0; j < this.size; j++) {
/*      */         
/* 2193 */         FeatureMap.Entry entry = entries[j];
/* 2194 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 2196 */           if (count == index)
/*      */           {
/* 2198 */             return doSet(j, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */           }
/* 2200 */           count++;
/*      */         } 
/*      */       } 
/* 2203 */       throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2209 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 2211 */       FeatureMap.Entry entry = entries[i];
/* 2212 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/* 2214 */         return FeatureMapUtil.isFeatureMap(feature) ? entry : entry.getValue();
/*      */       }
/*      */     } 
/*      */     
/* 2218 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object setUnique(EStructuralFeature feature, int index, Object object) {
/* 2224 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2225 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2226 */     if (isMany(feature)) {
/*      */       
/* 2228 */       int count = 0;
/* 2229 */       for (int j = 0; j < this.size; j++) {
/*      */         
/* 2231 */         FeatureMap.Entry entry = entries[j];
/* 2232 */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 2234 */           if (count == index)
/*      */           {
/* 2236 */             return setUnique(j, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */           }
/* 2238 */           count++;
/*      */         } 
/*      */       } 
/* 2241 */       throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2247 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 2249 */       FeatureMap.Entry entry = entries[i];
/* 2250 */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */       {
/* 2252 */         return setUnique(i, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */       }
/*      */     } 
/*      */     
/* 2256 */     return feature.getDefaultValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSet(EStructuralFeature feature) {
/* 2262 */     return !isEmpty(feature);
/*      */   }
/*      */   
/*      */   public void unset(EStructuralFeature feature) {
/*      */     BasicEList<FeatureMap.Entry> basicEList;
/* 2267 */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2268 */     List<FeatureMap.Entry> removals = null;
/* 2269 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2270 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 2272 */       FeatureMap.Entry entry = entries[i];
/* 2273 */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         
/* 2275 */         if (removals == null)
/*      */         {
/* 2277 */           basicEList = new BasicEList();
/*      */         }
/* 2279 */         basicEList.add(entry);
/*      */       } 
/*      */     } 
/*      */     
/* 2283 */     if (basicEList != null)
/*      */     {
/* 2285 */       removeAll((Collection)basicEList);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/*      */     NotificationChain notificationChain;
/* 2294 */     if (object instanceof FeatureMap.Entry)
/*      */     {
/* 2296 */       return super.basicRemove(object, notifications);
/*      */     }
/*      */ 
/*      */     
/* 2300 */     FeatureMap.Entry match = null;
/* 2301 */     EStructuralFeature feature = null;
/* 2302 */     FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2303 */     for (int i = 0; i < this.size; i++) {
/*      */       
/* 2305 */       FeatureMap.Entry entry = entries[i];
/* 2306 */       if (object.equals(entry.getValue())) {
/*      */         
/* 2308 */         feature = entry.getEStructuralFeature();
/* 2309 */         if (feature instanceof EReference && ((EReference)feature).isContainment()) {
/*      */           
/* 2311 */           match = entry;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2317 */     if (match != null) {
/*      */       NotificationImpl notificationImpl;
/* 2319 */       if (isNotificationRequired()) {
/*      */ 
/*      */         
/* 2322 */         NotificationImpl notification = 
/* 2323 */           feature.isMany() ? 
/* 2324 */           createNotification(
/* 2325 */             4, 
/* 2326 */             feature, 
/* 2327 */             object, 
/* 2328 */             (Object)null, 
/* 2329 */             indexOf(feature, object), 
/* 2330 */             true) : 
/* 2331 */           createNotification(
/* 2332 */             feature.isUnsettable() ? 2 : 1, 
/* 2333 */             feature, 
/* 2334 */             object, 
/* 2335 */             feature.getDefaultValue(), 
/* 2336 */             -1, 
/* 2337 */             true);
/*      */         
/* 2339 */         if (notifications != null) {
/*      */           
/* 2341 */           notifications.add((Notification)notification);
/*      */         }
/*      */         else {
/*      */           
/* 2345 */           notificationImpl = notification;
/*      */         } 
/*      */       } 
/* 2348 */       notificationChain = basicRemove(match, (NotificationChain)notificationImpl);
/*      */     } 
/*      */     
/* 2351 */     return notificationChain;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class FeatureEIterator<E>
/*      */     extends FeatureMapUtil.BasicFeatureEIterator<E>
/*      */   {
/*      */     public FeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap) {
/* 2362 */       super(eStructuralFeature, featureMap);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean scanNext() {
/* 2368 */       int size = this.featureMap.size();
/* 2369 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])((BasicEList)this.featureMap).data();
/* 2370 */       while (this.entryCursor < size) {
/*      */         
/* 2372 */         FeatureMap.Entry entry = entries[this.entryCursor];
/* 2373 */         if (this.validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 2375 */           this.preparedResult = extractValue(entry);
/* 2376 */           this.prepared = 2;
/* 2377 */           return true;
/*      */         } 
/* 2379 */         this.entryCursor++;
/*      */       } 
/*      */       
/* 2382 */       this.prepared = 1;
/* 2383 */       this.lastCursor = -1;
/* 2384 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean scanPrevious() {
/* 2390 */       FeatureMap.Entry[] entries = (FeatureMap.Entry[])((BasicEList)this.featureMap).data();
/* 2391 */       while (--this.entryCursor >= 0) {
/*      */         
/* 2393 */         FeatureMap.Entry entry = entries[this.entryCursor];
/* 2394 */         if (this.validator.isValid(entry.getEStructuralFeature())) {
/*      */           
/* 2396 */           this.preparedResult = extractValue(entry);
/* 2397 */           this.prepared = -2;
/* 2398 */           return true;
/*      */         } 
/*      */       } 
/*      */       
/* 2402 */       this.prepared = -1;
/* 2403 */       this.lastCursor = -1;
/* 2404 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ResolvingFeatureEIterator<E>
/*      */     extends FeatureEIterator<E>
/*      */   {
/*      */     public ResolvingFeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap) {
/* 2415 */       super(eStructuralFeature, featureMap);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean resolve() {
/* 2421 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class FeatureMapEObjectImpl
/*      */     extends EObjectImpl
/*      */   {
/* 2430 */     protected BasicFeatureMap featureMap = new BasicFeatureMap((InternalEObject)this, -1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object eDynamicGet(EStructuralFeature eFeature, boolean resolve) {
/* 2440 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
/*      */       {
/* 2442 */         return eSettingDelegate(eFeature).dynamicGet((InternalEObject)this, null, -1, true, true);
/*      */       }
/*      */ 
/*      */       
/* 2446 */       return this.featureMap.setting(eFeature).get(resolve);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void eDynamicSet(EStructuralFeature eFeature, Object newValue) {
/* 2453 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer()) {
/*      */         
/* 2455 */         eSettingDelegate(eFeature).dynamicSet((InternalEObject)this, null, -1, newValue);
/*      */       }
/*      */       else {
/*      */         
/* 2459 */         if (!eFeature.isUnsettable()) {
/*      */           
/* 2461 */           Object defaultValue = eFeature.getDefaultValue();
/* 2462 */           if ((defaultValue == null) ? (newValue == null) : defaultValue.equals(newValue)) {
/*      */             
/* 2464 */             this.featureMap.setting(eFeature).unset();
/*      */             return;
/*      */           } 
/*      */         } 
/* 2468 */         this.featureMap.setting(eFeature).set(newValue);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void eDynamicUnset(EStructuralFeature eFeature) {
/* 2475 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer()) {
/*      */         
/* 2477 */         eSettingDelegate(eFeature).dynamicUnset((InternalEObject)this, null, -1);
/*      */       }
/*      */       else {
/*      */         
/* 2481 */         this.featureMap.setting(eFeature).unset();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean eDynamicIsSet(EStructuralFeature eFeature) {
/* 2488 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
/*      */       {
/* 2490 */         return eSettingDelegate(eFeature).dynamicIsSet((InternalEObject)this, null, -1);
/*      */       }
/*      */ 
/*      */       
/* 2494 */       return this.featureMap.setting(eFeature).isSet();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain eDynamicInverseAdd(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain notifications) {
/* 2501 */       EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEStructuralFeature(featureID);
/* 2502 */       if (feature.isMany())
/*      */       {
/* 2504 */         return this.featureMap.basicAdd((EStructuralFeature)feature, otherEnd, notifications);
/*      */       }
/* 2506 */       if (feature instanceof EReference && ((EReference)feature).isContainer())
/*      */       {
/* 2508 */         return eSettingDelegate((EStructuralFeature)feature).dynamicInverseAdd((InternalEObject)this, null, -1, otherEnd, notifications);
/*      */       }
/*      */ 
/*      */       
/* 2512 */       InternalEObject oldValue = (InternalEObject)eDynamicGet((EStructuralFeature)feature, false);
/* 2513 */       if (oldValue != null) {
/*      */         
/* 2515 */         notifications = oldValue.eInverseRemove(
/* 2516 */             (InternalEObject)this, oldValue.eClass().getFeatureID((EStructuralFeature)((EReference)feature).getEOpposite()), null, notifications);
/* 2517 */         notifications = this.featureMap.basicRemove((EStructuralFeature)feature, oldValue, notifications);
/*      */       } 
/*      */       
/* 2520 */       return this.featureMap.basicAdd((EStructuralFeature)feature, otherEnd, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain eDynamicInverseRemove(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain notifications) {
/* 2527 */       EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEStructuralFeature(featureID);
/* 2528 */       if (feature instanceof EReference && ((EReference)feature).isContainer())
/*      */       {
/* 2530 */         return eSettingDelegate((EStructuralFeature)feature).dynamicInverseRemove((InternalEObject)this, null, -1, otherEnd, notifications);
/*      */       }
/*      */ 
/*      */       
/* 2534 */       return this.featureMap.basicRemove((EStructuralFeature)feature, otherEnd, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureMap featureMap() {
/* 2540 */       return this.featureMap;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void eNotify(Notification notification) {
/* 2546 */       if (notification.getFeatureID(null) != -1)
/*      */       {
/* 2548 */         super.eNotify(notification);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2555 */       String result = super.toString();
/* 2556 */       result = "org.eclipse.emf.ecore.impl.EObjectImpl" + result.substring(result.indexOf("@"));
/* 2557 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void set(Object newValue) {
/* 2564 */     super.set((newValue instanceof FeatureMap) ? newValue : ((FeatureMap.Internal.Wrapper)newValue).featureMap());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected FeatureMap.Entry resolve(int index, FeatureMap.Entry entry) {
/* 2570 */     EStructuralFeature feature = entry.getEStructuralFeature();
/* 2571 */     if (isResolveProxies(feature)) {
/*      */       
/* 2573 */       InternalEObject object = (InternalEObject)entry.getValue();
/* 2574 */       EObject resolved = resolveProxy((EObject)object);
/* 2575 */       if (resolved != object) {
/*      */         NotificationImpl notificationImpl; NotificationChain notificationChain1;
/* 2577 */         FeatureMap.Entry newEntry = createEntry(feature, resolved);
/* 2578 */         assign(index, validate(index, newEntry));
/* 2579 */         didSet(index, newEntry, entry);
/*      */         
/* 2581 */         NotificationChain notifications = null;
/*      */ 
/*      */ 
/*      */         
/* 2585 */         if (isNotificationRequired()) {
/*      */           
/* 2587 */           EStructuralFeature affiliatedFeature = ExtendedMetaData.INSTANCE.getAffiliation(this.owner.eClass(), feature);
/* 2588 */           if (affiliatedFeature != getEStructuralFeature()) {
/*      */             
/* 2590 */             FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2591 */             int featureIndex = 0;
/* 2592 */             FeatureMap.Entry[] entries = (FeatureMap.Entry[])this.data;
/* 2593 */             for (int i = 0; i < index; i++) {
/*      */               
/* 2595 */               FeatureMap.Entry affliatedEntry = entries[i];
/* 2596 */               if (validator.isValid(affliatedEntry.getEStructuralFeature()))
/*      */               {
/* 2598 */                 featureIndex++;
/*      */               }
/*      */             } 
/*      */             
/* 2602 */             notificationImpl = 
/* 2603 */               createNotification(
/* 2604 */                 9, 
/* 2605 */                 affiliatedFeature, 
/* 2606 */                 object, 
/* 2607 */                 resolved, 
/* 2608 */                 featureIndex, 
/* 2609 */                 false);
/*      */             
/* 2611 */             notificationImpl.add((Notification)createNotification(9, entry, newEntry, index, false));
/*      */           } 
/*      */         } 
/*      */         
/* 2615 */         EReference reference = (EReference)feature;
/* 2616 */         EReference opposite = reference.getEOpposite();
/* 2617 */         if (opposite != null) {
/*      */           
/* 2619 */           notificationChain1 = object.eInverseRemove(this.owner, object.eClass().getFeatureID((EStructuralFeature)opposite), null, (NotificationChain)notificationImpl);
/* 2620 */           notificationChain1 = ((InternalEObject)resolved).eInverseAdd(this.owner, resolved.eClass().getFeatureID((EStructuralFeature)opposite), null, notificationChain1);
/*      */         }
/* 2622 */         else if (reference.isContainment()) {
/*      */           
/* 2624 */           int inverseFeatureID = -1 - this.owner.eClass().getFeatureID((EStructuralFeature)reference);
/* 2625 */           notificationChain1 = object.eInverseRemove(this.owner, inverseFeatureID, null, null);
/* 2626 */           if (((InternalEObject)resolved).eInternalContainer() == null)
/*      */           {
/* 2628 */             notificationChain1 = ((InternalEObject)resolved).eInverseAdd(this.owner, inverseFeatureID, null, notificationChain1);
/*      */           }
/*      */         } 
/* 2631 */         if (notificationChain1 != null)
/*      */         {
/* 2633 */           notificationChain1.dispatch();
/*      */         }
/*      */         
/* 2636 */         return newEntry;
/*      */       } 
/*      */     } 
/* 2639 */     return entry;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\BasicFeatureMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */