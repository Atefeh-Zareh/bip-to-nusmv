/*      */ package org.eclipse.emf.ecore.impl;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.AbstractSequentialList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EGenericType;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EOperation;
/*      */ import org.eclipse.emf.ecore.EParameter;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.ETypeParameter;
/*      */ import org.eclipse.emf.ecore.EcoreFactory;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.util.BasicInvocationDelegate;
/*      */ import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EOperationImpl
/*      */   extends ETypedElementImpl
/*      */   implements EOperation, EOperation.Internal
/*      */ {
/*   72 */   protected int operationID = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EList<ETypeParameter> eTypeParameters;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EList<EParameter> eParameters;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EList<EClassifier> eExceptions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EList<EGenericType> eGenericExceptions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EOperation.Internal.InvocationDelegate invocationDelegate;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void freeze() {
/*  127 */     if (this.eParameters != null)
/*      */     {
/*  129 */       for (int i = 0, size = this.eParameters.size(); i < size; i++)
/*      */       {
/*  131 */         freeze(this.eParameters.get(i));
/*      */       }
/*      */     }
/*  134 */     super.freeze();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EClass eStaticClass() {
/*  145 */     return EcorePackage.Literals.EOPERATION;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EClass getEContainingClass() {
/*  155 */     return (eContainerFeatureID() == 10) ? (EClass)this.eContainer : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<EParameter> getEParameters() {
/*  165 */     if (this.eParameters == null)
/*      */     {
/*  167 */       this.eParameters = (EList<EParameter>)new EObjectContainmentWithInverseEList(EParameter.class, this, 12, 10);
/*      */     }
/*  169 */     return this.eParameters;
/*      */   }
/*      */ 
/*      */   
/*      */   public EList<EClassifier> getEExceptions() {
/*  174 */     if (this.eExceptions == null)
/*      */     {
/*  176 */       this.eExceptions = 
/*  177 */         (EList<EClassifier>)new DelegatingEcoreEList<EClassifier>(this)
/*      */         {
/*      */           private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */           
/*      */           protected List<EClassifier> delegateList() {
/*  184 */             return null;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected List<EClassifier> delegateBasicList() {
/*  190 */             return 
/*  191 */               new AbstractSequentialList<EClassifier>()
/*      */               {
/*      */                 
/*      */                 public ListIterator<EClassifier> listIterator(int index)
/*      */                 {
/*  196 */                   return EOperationImpl.null.this.basicListIterator();
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*      */                 public int size() {
/*  202 */                   return EOperationImpl.null.this.delegateSize();
/*      */                 }
/*      */               };
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected Iterator<EClassifier> delegateIterator() {
/*  210 */             return iterator();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected ListIterator<EClassifier> delegateListIterator() {
/*  216 */             return listIterator();
/*      */           }
/*      */ 
/*      */           
/*      */           protected EGenericType wrap(EClassifier eClassifier) {
/*  221 */             EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
/*  222 */             eGenericType.setEClassifier(eClassifier);
/*  223 */             return eGenericType;
/*      */           }
/*      */ 
/*      */           
/*      */           protected EClassifier unwrap(EGenericType eGenericType) {
/*  228 */             EClassifier result = ((EGenericTypeImpl)eGenericType).basicGetERawType();
/*  229 */             if (result != null)
/*      */             {
/*  231 */               return result;
/*      */             }
/*      */ 
/*      */             
/*  235 */             return (EClassifier)EcorePackage.Literals.EJAVA_OBJECT;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           protected void delegateAdd(int index, EClassifier eClassifier) {
/*  242 */             EOperationImpl.this.getEGenericExceptions().add(index, wrap(eClassifier));
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected void delegateClear() {
/*  248 */             EOperationImpl.this.getEGenericExceptions().clear();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected void delegateAdd(EClassifier eClassifier) {
/*  254 */             EOperationImpl.this.getEGenericExceptions().add(wrap(eClassifier));
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean delegateContains(Object object) {
/*  260 */             for (EClassifier eClassifier : this) {
/*      */               
/*  262 */               if (object == eClassifier)
/*      */               {
/*  264 */                 return true;
/*      */               }
/*      */             } 
/*  267 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean delegateContainsAll(Collection<?> collection) {
/*  273 */             for (Object object : collection) {
/*      */               
/*  275 */               if (!delegateContains(object))
/*      */               {
/*  277 */                 return false;
/*      */               }
/*      */             } 
/*  280 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean delegateEquals(Object object) {
/*  286 */             if (object instanceof List) {
/*      */               
/*  288 */               List<?> list = (List)object;
/*  289 */               if (list.size() == delegateSize()) {
/*      */                 
/*  291 */                 for (Iterator<?> i = list.iterator(), j = iterator(); i.hasNext();) {
/*      */                   
/*  293 */                   if (i.next() != j.next())
/*      */                   {
/*  295 */                     return false;
/*      */                   }
/*      */                 } 
/*  298 */                 return true;
/*      */               } 
/*      */             } 
/*  301 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected EClassifier delegateGet(int index) {
/*  307 */             EGenericType eGenericType = (EGenericType)EOperationImpl.this.getEGenericExceptions().get(index);
/*  308 */             return unwrap(eGenericType);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected int delegateHashCode() {
/*  314 */             int hashCode = 1;
/*  315 */             for (EGenericType eGenericType : EOperationImpl.this.getEGenericExceptions()) {
/*      */               
/*  317 */               Object object = unwrap(eGenericType);
/*  318 */               hashCode = 31 * hashCode + ((object == null) ? 0 : object.hashCode());
/*      */             } 
/*  320 */             return hashCode;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected int delegateIndexOf(Object object) {
/*  326 */             int index = 0;
/*  327 */             for (EGenericType eGenericType : EOperationImpl.this.getEGenericExceptions()) {
/*      */               
/*  329 */               if (object == unwrap(eGenericType))
/*      */               {
/*  331 */                 return index;
/*      */               }
/*  333 */               index++;
/*      */             } 
/*  335 */             return -1;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean delegateIsEmpty() {
/*  341 */             return EOperationImpl.this.getEGenericExceptions().isEmpty();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected int delegateLastIndexOf(Object object) {
/*  347 */             EList<EGenericType> eGenericExceptions = EOperationImpl.this.getEGenericExceptions();
/*  348 */             for (int i = eGenericExceptions.size() - 1; i >= 0; i--) {
/*      */               
/*  350 */               if (unwrap((EGenericType)eGenericExceptions.get(i)) == object)
/*      */               {
/*  352 */                 return i;
/*      */               }
/*      */             } 
/*  355 */             return -1;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected EClassifier delegateRemove(int index) {
/*  361 */             EGenericType eGenericType = (EGenericType)EOperationImpl.this.getEGenericExceptions().remove(index);
/*  362 */             return unwrap(eGenericType);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected EClassifier delegateSet(int index, EClassifier eClassifier) {
/*  368 */             EGenericType eGenericType = (EGenericType)EOperationImpl.this.getEGenericExceptions().get(index);
/*  369 */             EClassifier result = unwrap(eGenericType);
/*      */ 
/*      */ 
/*      */             
/*  373 */             if (resolveProxy((EObject)result) == eClassifier) {
/*      */ 
/*      */ 
/*      */               
/*  377 */               eGenericType.getERawType();
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */               
/*  383 */               eGenericType.setEClassifier(eClassifier);
/*      */             } 
/*  385 */             return result;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected int delegateSize() {
/*  391 */             return EOperationImpl.this.getEGenericExceptions().size();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected Object[] delegateToArray() {
/*  397 */             int size = delegateSize();
/*  398 */             Object[] result = new Object[size];
/*      */             
/*  400 */             int index = 0;
/*  401 */             for (EGenericType eGenericType : EOperationImpl.this.getEGenericExceptions())
/*      */             {
/*  403 */               result[index++] = unwrap(eGenericType);
/*      */             }
/*  405 */             return result;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected <T> T[] delegateToArray(Object[] array) {
/*  411 */             int size = delegateSize();
/*  412 */             if (array.length < size) {
/*      */               
/*  414 */               Object[] newArray = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*  415 */               array = newArray;
/*      */             } 
/*      */             
/*  418 */             if (array.length > size)
/*      */             {
/*  420 */               array[size] = null;
/*      */             }
/*      */             
/*  423 */             int index = 0;
/*  424 */             for (EGenericType eGenericType : EOperationImpl.this.getEGenericExceptions()) {
/*      */               
/*  426 */               EClassifier eClassifier = unwrap(eGenericType);
/*  427 */               array[index++] = eClassifier;
/*      */             } 
/*      */             
/*  430 */             return (T[])array;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected String delegateToString() {
/*  436 */             StringBuffer stringBuffer = new StringBuffer();
/*  437 */             stringBuffer.append("[");
/*  438 */             EList<EGenericType> eGenericExceptions = EOperationImpl.this.getEGenericExceptions();
/*  439 */             for (int i = 0, size = delegateSize(); i < size; ) {
/*      */               
/*  441 */               stringBuffer.append(String.valueOf(unwrap((EGenericType)eGenericExceptions.get(i))));
/*  442 */               if (++i < size)
/*      */               {
/*  444 */                 stringBuffer.append(", ");
/*      */               }
/*      */             } 
/*  447 */             stringBuffer.append("]");
/*  448 */             return stringBuffer.toString();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean isInstance(Object object) {
/*  454 */             return object instanceof EClassifier;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public int getFeatureID() {
/*  460 */             return 13;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean useEquals() {
/*  466 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean canContainNull() {
/*  472 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean isUnique() {
/*  478 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean hasInverse() {
/*  484 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean hasManyInverse() {
/*  490 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean hasNavigableInverse() {
/*  496 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean isEObject() {
/*  502 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean isContainment() {
/*  508 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean hasProxies() {
/*  514 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean hasInstanceClass() {
/*  520 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean isSet() {
/*  526 */             return EOperationImpl.this.isSetEExceptions();
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet) {
/*  535 */             return null;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           protected void dispatchNotification(Notification notification) {}
/*      */         };
/*      */     }
/*  545 */     return this.eExceptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetEExceptions() {
/*  555 */     if (this.eExceptions != null) ((InternalEList.Unsettable)this.eExceptions).unset();
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSetEExceptions() {
/*  565 */     return (this.eExceptions != null && !this.eExceptions.isEmpty() && !isSetEGenericExceptions());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<EGenericType> getEGenericExceptions() {
/*  575 */     if (this.eGenericExceptions == null) {
/*      */       
/*  577 */       this.eGenericExceptions = 
/*  578 */         (EList<EGenericType>)new EObjectContainmentEList.Unsettable<EGenericType>(EGenericType.class, this, 14)
/*      */         {
/*      */           private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean isSet() {
/*  585 */             for (EGenericType eGenericType : this) {
/*      */               
/*  587 */               if (eGenericType.getETypeParameter() != null || !eGenericType.getETypeArguments().isEmpty())
/*      */               {
/*  589 */                 return true;
/*      */               }
/*      */             } 
/*  592 */             return false;
/*      */           }
/*      */ 
/*      */           
/*      */           protected EClassifier unwrap(EGenericType eGenericType) {
/*  597 */             EClassifier result = eGenericType.getERawType();
/*  598 */             if (result != null)
/*      */             {
/*  600 */               return result;
/*      */             }
/*      */ 
/*      */             
/*  604 */             return (EClassifier)EcorePackage.Literals.EJAVA_OBJECT;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           protected boolean hasShadow() {
/*  611 */             return true;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected NotificationChain shadowAdd(EGenericType eGenericType, NotificationChain notifications) {
/*  617 */             ENotificationImpl eNotificationImpl1, notification = 
/*  618 */               new ENotificationImpl(
/*  619 */                 this.owner, 3, 13, null, unwrap(eGenericType), indexOf(eGenericType), false);
/*  620 */             if (notifications == null) {
/*      */               
/*  622 */               eNotificationImpl1 = notification;
/*      */             }
/*      */             else {
/*      */               
/*  626 */               eNotificationImpl1.add((Notification)notification);
/*      */             } 
/*  628 */             return (NotificationChain)eNotificationImpl1;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected NotificationChain shadowRemove(EGenericType eGenericType, NotificationChain notifications) {
/*  634 */             ENotificationImpl eNotificationImpl1, notification = 
/*  635 */               new ENotificationImpl(
/*  636 */                 this.owner, 4, 13, unwrap(eGenericType), null, indexOf(eGenericType), false);
/*  637 */             if (notifications == null) {
/*      */               
/*  639 */               eNotificationImpl1 = notification;
/*      */             }
/*      */             else {
/*      */               
/*  643 */               eNotificationImpl1.add((Notification)notification);
/*      */             } 
/*  645 */             return (NotificationChain)eNotificationImpl1;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected NotificationChain shadowSet(EGenericType oldEGenericType, EGenericType newEGenericType, NotificationChain notifications) {
/*  651 */             ENotificationImpl eNotificationImpl1, notification = 
/*  652 */               new ENotificationImpl(
/*  653 */                 this.owner, 
/*  654 */                 1, 
/*  655 */                 13, 
/*  656 */                 unwrap(oldEGenericType), 
/*  657 */                 unwrap(newEGenericType), 
/*  658 */                 indexOf(oldEGenericType), 
/*  659 */                 false);
/*  660 */             if (notifications == null) {
/*      */               
/*  662 */               eNotificationImpl1 = notification;
/*      */             }
/*      */             else {
/*      */               
/*  666 */               eNotificationImpl1.add((Notification)notification);
/*      */             } 
/*  668 */             return (NotificationChain)eNotificationImpl1;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public EGenericType move(int targetIndex, int sourceIndex) {
/*  674 */             EGenericType result = (EGenericType)super.move(targetIndex, sourceIndex);
/*  675 */             if (isNotificationRequired())
/*      */             {
/*  677 */               dispatchNotification(
/*  678 */                   (Notification)new ENotificationImpl(
/*  679 */                     EOperationImpl.this, 
/*  680 */                     7, 
/*  681 */                     (EStructuralFeature)EcorePackage.Literals.EOPERATION__EEXCEPTIONS, 
/*  682 */                     Integer.valueOf(sourceIndex), 
/*  683 */                     unwrap(result), 
/*  684 */                     targetIndex));
/*      */             }
/*  686 */             return result;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void unset() {
/*  693 */             clear();
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet) {
/*  699 */             switch (eventType) {
/*      */ 
/*      */               
/*      */               case 3:
/*  703 */                 return super.createNotification(eventType, oldObject, newObject, index, (this.size > 1));
/*      */ 
/*      */               
/*      */               case 5:
/*  707 */                 return super.createNotification(eventType, oldObject, newObject, index, (this.size - ((List)newObject).size() > 0));
/*      */             } 
/*      */ 
/*      */             
/*  711 */             return super.createNotification(eventType, oldObject, newObject, index, true);
/*      */           }
/*      */         };
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  718 */       getEExceptions();
/*      */     } 
/*  720 */     return this.eGenericExceptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetEGenericExceptions() {
/*  730 */     if (this.eGenericExceptions != null) ((InternalEList.Unsettable)this.eGenericExceptions).unset();
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSetEGenericExceptions() {
/*  740 */     return (this.eGenericExceptions != null && ((InternalEList.Unsettable)this.eGenericExceptions).isSet());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOperationID() {
/*  750 */     return this.operationID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOverrideOf(EOperation someOperation) {
/*  760 */     if (someOperation.getEContainingClass().isSuperTypeOf(getEContainingClass()) && someOperation.getName().equals(getName())) {
/*      */       
/*  762 */       EList<EParameter> parameters = getEParameters();
/*  763 */       EList<EParameter> otherParameters = someOperation.getEParameters();
/*  764 */       if (parameters.size() == otherParameters.size()) {
/*      */         
/*  766 */         for (Iterator<EParameter> i = parameters.iterator(), j = otherParameters.iterator(); i.hasNext(); ) {
/*      */           
/*  768 */           EParameter parameter = i.next();
/*  769 */           EParameter otherParameter = j.next();
/*  770 */           if (!parameter.getEType().getInstanceTypeName().equals(otherParameter.getEType().getInstanceTypeName()))
/*      */           {
/*  772 */             return false;
/*      */           }
/*      */         } 
/*  775 */         return true;
/*      */       } 
/*      */     } 
/*  778 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOperationID(int operationID) {
/*  783 */     this.operationID = operationID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EList<ETypeParameter> getETypeParameters() {
/*  793 */     if (this.eTypeParameters == null)
/*      */     {
/*  795 */       this.eTypeParameters = (EList<ETypeParameter>)new EObjectContainmentEList.Resolving(ETypeParameter.class, this, 11);
/*      */     }
/*  797 */     return this.eTypeParameters;
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
/*      */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  809 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  812 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*      */       case 10:
/*  814 */         if (eInternalContainer() != null)
/*  815 */           msgs = eBasicRemoveFromContainer(msgs); 
/*  816 */         return eBasicSetContainer(otherEnd, 10, msgs);
/*      */       case 12:
/*  818 */         return ((InternalEList)getEParameters()).basicAdd(otherEnd, msgs);
/*      */     } 
/*  820 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/*  831 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  834 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*      */       case 9:
/*  836 */         return basicUnsetEGenericType(msgs);
/*      */       case 10:
/*  838 */         return eBasicSetContainer(null, 10, msgs);
/*      */       case 11:
/*  840 */         return ((InternalEList)getETypeParameters()).basicRemove(otherEnd, msgs);
/*      */       case 12:
/*  842 */         return ((InternalEList)getEParameters()).basicRemove(otherEnd, msgs);
/*      */       case 14:
/*  844 */         return ((InternalEList)getEGenericExceptions()).basicRemove(otherEnd, msgs);
/*      */     } 
/*  846 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/*  857 */     switch (eContainerFeatureID()) {
/*      */       
/*      */       case 10:
/*  860 */         return eInternalContainer().eInverseRemove(this, 11, EClass.class, msgs);
/*      */     } 
/*  862 */     return eDynamicBasicRemoveFromContainer(msgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/*  873 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  876 */         return getEAnnotations();
/*      */       case 1:
/*  878 */         return getName();
/*      */       case 2:
/*  880 */         return Boolean.valueOf(isOrdered());
/*      */       case 3:
/*  882 */         return Boolean.valueOf(isUnique());
/*      */       case 4:
/*  884 */         return Integer.valueOf(getLowerBound());
/*      */       case 5:
/*  886 */         return Integer.valueOf(getUpperBound());
/*      */       case 6:
/*  888 */         return Boolean.valueOf(isMany());
/*      */       case 7:
/*  890 */         return Boolean.valueOf(isRequired());
/*      */       case 8:
/*  892 */         if (resolve) return getEType(); 
/*  893 */         return basicGetEType();
/*      */       case 9:
/*  895 */         return getEGenericType();
/*      */       case 10:
/*  897 */         return getEContainingClass();
/*      */       case 11:
/*  899 */         return getETypeParameters();
/*      */       case 12:
/*  901 */         return getEParameters();
/*      */       case 13:
/*  903 */         return getEExceptions();
/*      */       case 14:
/*  905 */         return getEGenericExceptions();
/*      */     } 
/*  907 */     return eDynamicGet(featureID, resolve, coreType);
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
/*      */   public void eSet(int featureID, Object newValue) {
/*  919 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  922 */         getEAnnotations().clear();
/*  923 */         getEAnnotations().addAll((Collection)newValue);
/*      */         return;
/*      */       case 1:
/*  926 */         setName((String)newValue);
/*      */         return;
/*      */       case 2:
/*  929 */         setOrdered(((Boolean)newValue).booleanValue());
/*      */         return;
/*      */       case 3:
/*  932 */         setUnique(((Boolean)newValue).booleanValue());
/*      */         return;
/*      */       case 4:
/*  935 */         setLowerBound(((Integer)newValue).intValue());
/*      */         return;
/*      */       case 5:
/*  938 */         setUpperBound(((Integer)newValue).intValue());
/*      */         return;
/*      */       case 8:
/*  941 */         setEType((EClassifier)newValue);
/*      */         return;
/*      */       case 9:
/*  944 */         setEGenericType((EGenericType)newValue);
/*      */         return;
/*      */       case 11:
/*  947 */         getETypeParameters().clear();
/*  948 */         getETypeParameters().addAll((Collection)newValue);
/*      */         return;
/*      */       case 12:
/*  951 */         getEParameters().clear();
/*  952 */         getEParameters().addAll((Collection)newValue);
/*      */         return;
/*      */       case 13:
/*  955 */         getEExceptions().clear();
/*  956 */         getEExceptions().addAll((Collection)newValue);
/*      */         return;
/*      */       case 14:
/*  959 */         getEGenericExceptions().clear();
/*  960 */         getEGenericExceptions().addAll((Collection)newValue);
/*      */         return;
/*      */     } 
/*  963 */     eDynamicSet(featureID, newValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void eUnset(int featureID) {
/*  974 */     switch (featureID) {
/*      */       
/*      */       case 0:
/*  977 */         getEAnnotations().clear();
/*      */         return;
/*      */       case 1:
/*  980 */         setName(NAME_EDEFAULT);
/*      */         return;
/*      */       case 2:
/*  983 */         setOrdered(true);
/*      */         return;
/*      */       case 3:
/*  986 */         setUnique(true);
/*      */         return;
/*      */       case 4:
/*  989 */         setLowerBound(0);
/*      */         return;
/*      */       case 5:
/*  992 */         setUpperBound(1);
/*      */         return;
/*      */       case 8:
/*  995 */         unsetEType();
/*      */         return;
/*      */       case 9:
/*  998 */         unsetEGenericType();
/*      */         return;
/*      */       case 11:
/* 1001 */         getETypeParameters().clear();
/*      */         return;
/*      */       case 12:
/* 1004 */         getEParameters().clear();
/*      */         return;
/*      */       case 13:
/* 1007 */         unsetEExceptions();
/*      */         return;
/*      */       case 14:
/* 1010 */         unsetEGenericExceptions();
/*      */         return;
/*      */     } 
/* 1013 */     eDynamicUnset(featureID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eIsSet(int featureID) {
/* 1024 */     switch (featureID) {
/*      */       
/*      */       case 0:
/* 1027 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*      */       case 1:
/* 1029 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*      */       case 2:
/* 1031 */         return !((this.eFlags & 0x100) != 0);
/*      */       case 3:
/* 1033 */         return !((this.eFlags & 0x200) != 0);
/*      */       case 4:
/* 1035 */         return (this.lowerBound != 0);
/*      */       case 5:
/* 1037 */         return (this.upperBound != 1);
/*      */       case 6:
/* 1039 */         return isMany();
/*      */       case 7:
/* 1041 */         return isRequired();
/*      */       case 8:
/* 1043 */         return isSetEType();
/*      */       case 9:
/* 1045 */         return isSetEGenericType();
/*      */       case 10:
/* 1047 */         return (getEContainingClass() != null);
/*      */       case 11:
/* 1049 */         return (this.eTypeParameters != null && !this.eTypeParameters.isEmpty());
/*      */       case 12:
/* 1051 */         return (this.eParameters != null && !this.eParameters.isEmpty());
/*      */       case 13:
/* 1053 */         return isSetEExceptions();
/*      */       case 14:
/* 1055 */         return isSetEGenericExceptions();
/*      */     } 
/* 1057 */     return eDynamicIsSet(featureID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 1068 */     switch (operationID) {
/*      */       
/*      */       case 0:
/* 1071 */         return getEAnnotation((String)arguments.get(0));
/*      */       case 1:
/* 1073 */         return Integer.valueOf(getOperationID());
/*      */       case 2:
/* 1075 */         return Boolean.valueOf(isOverrideOf((EOperation)arguments.get(0)));
/*      */     } 
/* 1077 */     return eDynamicInvoke(operationID, arguments);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EOperation.Internal.InvocationDelegate getInvocationDelegate() {
/* 1084 */     if (this.invocationDelegate == null) {
/*      */       
/* 1086 */       EOperation.Internal.InvocationDelegate.Factory factory = EcoreUtil.getInvocationDelegateFactory(this);
/* 1087 */       if (factory != null)
/*      */       {
/* 1089 */         this.invocationDelegate = factory.createInvocationDelegate(this);
/*      */       }
/* 1091 */       if (this.invocationDelegate == null)
/*      */       {
/* 1093 */         this.invocationDelegate = (EOperation.Internal.InvocationDelegate)new BasicInvocationDelegate(this);
/*      */       }
/*      */     } 
/*      */     
/* 1097 */     return this.invocationDelegate;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInvocationDelegate(EOperation.Internal.InvocationDelegate invocationDelegate) {
/* 1102 */     this.invocationDelegate = invocationDelegate;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EOperationImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */