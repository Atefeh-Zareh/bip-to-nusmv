/*      */ package org.eclipse.emf.common.notify.impl;
/*      */ 
/*      */ import java.util.List;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.Notifier;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NotificationImpl
/*      */   implements Notification, NotificationChain
/*      */ {
/*      */   public static final int PRIMITIVE_TYPE_OBJECT = -1;
/*      */   public static final int PRIMITIVE_TYPE_BOOLEAN = 0;
/*      */   public static final int PRIMITIVE_TYPE_BYTE = 1;
/*      */   public static final int PRIMITIVE_TYPE_CHAR = 2;
/*      */   public static final int PRIMITIVE_TYPE_DOUBLE = 3;
/*      */   public static final int PRIMITIVE_TYPE_FLOAT = 4;
/*      */   public static final int PRIMITIVE_TYPE_INT = 5;
/*      */   public static final int PRIMITIVE_TYPE_LONG = 6;
/*      */   public static final int PRIMITIVE_TYPE_SHORT = 7;
/*      */   protected static final int IS_SET_CHANGE_INDEX = -2;
/*      */   protected int primitiveType;
/*      */   protected int eventType;
/*      */   protected Object oldValue;
/*      */   protected Object newValue;
/*      */   protected long oldSimplePrimitiveValue;
/*      */   protected long newSimplePrimitiveValue;
/*      */   protected double oldIEEEPrimitiveValue;
/*      */   protected double newIEEEPrimitiveValue;
/*      */   protected int position;
/*      */   protected NotificationChain next;
/*      */   
/*      */   public NotificationImpl(int eventType, Object oldValue, Object newValue) {
/*  200 */     this(eventType, oldValue, newValue, -1);
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
/*      */   public NotificationImpl(int eventType, Object oldValue, Object newValue, boolean isSetChange) {
/*  212 */     this(eventType, oldValue, newValue, isSetChange ? -2 : -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NotificationImpl(int eventType, Object oldValue, Object newValue, int position) {
/*  223 */     this.eventType = eventType;
/*  224 */     this.oldValue = oldValue;
/*  225 */     this.newValue = newValue;
/*  226 */     this.position = position;
/*  227 */     this.primitiveType = -1;
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
/*      */   public NotificationImpl(int eventType, Object oldValue, Object newValue, int position, boolean wasSet) {
/*  239 */     this.eventType = eventType;
/*  240 */     this.oldValue = oldValue;
/*  241 */     this.newValue = newValue;
/*  242 */     this.position = position;
/*  243 */     this.primitiveType = -1;
/*  244 */     if (!wasSet)
/*      */     {
/*  246 */       this.position = -2 - position - 1;
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
/*      */   public NotificationImpl(int eventType, boolean oldBooleanValue, boolean newBooleanValue, boolean isSetChange) {
/*  259 */     this(eventType, oldBooleanValue, newBooleanValue);
/*  260 */     if (isSetChange)
/*      */     {
/*  262 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, boolean oldBooleanValue, boolean newBooleanValue) {
/*  274 */     this.eventType = eventType;
/*  275 */     this.oldSimplePrimitiveValue = (oldBooleanValue ? 1L : 0L);
/*  276 */     this.newSimplePrimitiveValue = (newBooleanValue ? 1L : 0L);
/*  277 */     this.position = -1;
/*  278 */     this.primitiveType = 0;
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
/*      */   public NotificationImpl(int eventType, byte oldByteValue, byte newByteValue, boolean isSetChange) {
/*  290 */     this(eventType, oldByteValue, newByteValue);
/*  291 */     if (isSetChange)
/*      */     {
/*  293 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, byte oldByteValue, byte newByteValue) {
/*  305 */     this.eventType = eventType;
/*  306 */     this.oldSimplePrimitiveValue = oldByteValue;
/*  307 */     this.newSimplePrimitiveValue = newByteValue;
/*  308 */     this.position = -1;
/*  309 */     this.primitiveType = 1;
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
/*      */   public NotificationImpl(int eventType, char oldCharValue, char newCharValue, boolean isSetChange) {
/*  321 */     this(eventType, oldCharValue, newCharValue);
/*  322 */     if (isSetChange)
/*      */     {
/*  324 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, char oldCharValue, char newCharValue) {
/*  336 */     this.eventType = eventType;
/*  337 */     this.oldSimplePrimitiveValue = oldCharValue;
/*  338 */     this.newSimplePrimitiveValue = newCharValue;
/*  339 */     this.position = -1;
/*  340 */     this.primitiveType = 2;
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
/*      */   public NotificationImpl(int eventType, double oldDoubleValue, double newDoubleValue, boolean isSetChange) {
/*  352 */     this(eventType, oldDoubleValue, newDoubleValue);
/*  353 */     if (isSetChange)
/*      */     {
/*  355 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, double oldDoubleValue, double newDoubleValue) {
/*  367 */     this.eventType = eventType;
/*  368 */     this.oldIEEEPrimitiveValue = oldDoubleValue;
/*  369 */     this.newIEEEPrimitiveValue = newDoubleValue;
/*  370 */     this.position = -1;
/*  371 */     this.primitiveType = 3;
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
/*      */   public NotificationImpl(int eventType, float oldFloatValue, float newFloatValue, boolean isSetChange) {
/*  383 */     this(eventType, oldFloatValue, newFloatValue);
/*  384 */     if (isSetChange)
/*      */     {
/*  386 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, float oldFloatValue, float newFloatValue) {
/*  398 */     this.eventType = eventType;
/*  399 */     this.oldIEEEPrimitiveValue = oldFloatValue;
/*  400 */     this.newIEEEPrimitiveValue = newFloatValue;
/*  401 */     this.position = -1;
/*  402 */     this.primitiveType = 4;
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
/*      */   public NotificationImpl(int eventType, int oldIntValue, int newIntValue, boolean isSetChange) {
/*  414 */     this(eventType, oldIntValue, newIntValue);
/*  415 */     if (isSetChange)
/*      */     {
/*  417 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, int oldIntValue, int newIntValue) {
/*  429 */     this.eventType = eventType;
/*  430 */     this.oldSimplePrimitiveValue = oldIntValue;
/*  431 */     this.newSimplePrimitiveValue = newIntValue;
/*  432 */     this.position = -1;
/*  433 */     this.primitiveType = 5;
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
/*      */   public NotificationImpl(int eventType, long oldLongValue, long newLongValue, boolean isSetChange) {
/*  445 */     this(eventType, oldLongValue, newLongValue);
/*  446 */     if (isSetChange)
/*      */     {
/*  448 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, long oldLongValue, long newLongValue) {
/*  460 */     this.eventType = eventType;
/*  461 */     this.oldSimplePrimitiveValue = oldLongValue;
/*  462 */     this.newSimplePrimitiveValue = newLongValue;
/*  463 */     this.position = -1;
/*  464 */     this.primitiveType = 6;
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
/*      */   public NotificationImpl(int eventType, short oldShortValue, short newShortValue, boolean isSetChange) {
/*  476 */     this(eventType, oldShortValue, newShortValue);
/*  477 */     if (isSetChange)
/*      */     {
/*  479 */       this.position = -2;
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
/*      */   public NotificationImpl(int eventType, short oldShortValue, short newShortValue) {
/*  491 */     this.eventType = eventType;
/*  492 */     this.oldSimplePrimitiveValue = oldShortValue;
/*  493 */     this.newSimplePrimitiveValue = newShortValue;
/*  494 */     this.position = -1;
/*  495 */     this.primitiveType = 7;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getNotifier() {
/*  500 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEventType() {
/*  505 */     return this.eventType;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getFeature() {
/*  510 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFeatureID(Class<?> expectedClass) {
/*  515 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getOldValue() {
/*  520 */     if (this.oldValue == null)
/*      */     {
/*  522 */       switch (this.primitiveType) {
/*      */         
/*      */         case 0:
/*  525 */           this.oldValue = getOldBooleanValue() ? Boolean.TRUE : Boolean.FALSE;
/*      */           break;
/*      */         case 1:
/*  528 */           this.oldValue = Byte.valueOf(getOldByteValue());
/*      */           break;
/*      */         case 2:
/*  531 */           this.oldValue = Character.valueOf(getOldCharValue());
/*      */           break;
/*      */         case 3:
/*  534 */           this.oldValue = Double.valueOf(getOldDoubleValue());
/*      */           break;
/*      */         case 4:
/*  537 */           this.oldValue = Float.valueOf(getOldFloatValue());
/*      */           break;
/*      */         case 6:
/*  540 */           this.oldValue = Long.valueOf(getOldLongValue());
/*      */           break;
/*      */         case 5:
/*  543 */           this.oldValue = Integer.valueOf(getOldIntValue());
/*      */           break;
/*      */         case 7:
/*  546 */           this.oldValue = Short.valueOf(getOldShortValue());
/*      */           break;
/*      */       } 
/*      */     }
/*  550 */     return this.oldValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getNewValue() {
/*  555 */     if (this.newValue == null)
/*      */     {
/*  557 */       switch (this.primitiveType) {
/*      */         
/*      */         case 0:
/*  560 */           this.newValue = getNewBooleanValue() ? Boolean.TRUE : Boolean.FALSE;
/*      */           break;
/*      */         case 1:
/*  563 */           this.newValue = Byte.valueOf(getNewByteValue());
/*      */           break;
/*      */         case 2:
/*  566 */           this.newValue = Character.valueOf(getNewCharValue());
/*      */           break;
/*      */         case 3:
/*  569 */           this.newValue = Double.valueOf(getNewDoubleValue());
/*      */           break;
/*      */         case 4:
/*  572 */           this.newValue = Float.valueOf(getNewFloatValue());
/*      */           break;
/*      */         case 6:
/*  575 */           this.newValue = Long.valueOf(getNewLongValue());
/*      */           break;
/*      */         case 5:
/*  578 */           this.newValue = Integer.valueOf(getNewIntValue());
/*      */           break;
/*      */         case 7:
/*  581 */           this.newValue = Short.valueOf(getNewShortValue());
/*      */           break;
/*      */       } 
/*      */     }
/*  585 */     return this.newValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTouch() {
/*  590 */     switch (this.eventType) {
/*      */ 
/*      */       
/*      */       case 8:
/*      */       case 9:
/*  595 */         return true;
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*  602 */         return false;
/*      */ 
/*      */       
/*      */       case 7:
/*  606 */         return (((Integer)getOldValue()).intValue() == this.position);
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 2:
/*  611 */         if (this.position == -2)
/*      */         {
/*  613 */           return false;
/*      */         }
/*      */ 
/*      */         
/*  617 */         switch (this.primitiveType) {
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 1:
/*      */           case 2:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/*  626 */             return (this.oldSimplePrimitiveValue == this.newSimplePrimitiveValue);
/*      */ 
/*      */           
/*      */           case 3:
/*      */           case 4:
/*  631 */             return (this.oldIEEEPrimitiveValue == this.newIEEEPrimitiveValue);
/*      */         } 
/*      */ 
/*      */         
/*  635 */         return (this.oldValue == null) ? ((this.newValue == null)) : this.oldValue.equals(this.newValue);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  642 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReset() {
/*      */     Object defaultValue;
/*  649 */     switch (this.eventType) {
/*      */       
/*      */       case 1:
/*  652 */         defaultValue = getFeatureDefaultValue();
/*  653 */         switch (this.primitiveType) {
/*      */           
/*      */           case 0:
/*  656 */             return (defaultValue != null && ((Boolean)defaultValue).booleanValue() == ((this.newSimplePrimitiveValue != 0L)));
/*      */           case 1:
/*  658 */             return (defaultValue != null && ((Byte)defaultValue).byteValue() == (byte)(int)this.newSimplePrimitiveValue);
/*      */           case 2:
/*  660 */             return (defaultValue != null && ((Character)defaultValue).charValue() == (char)(int)this.newSimplePrimitiveValue);
/*      */           case 6:
/*  662 */             return (defaultValue != null && ((Long)defaultValue).longValue() == this.newSimplePrimitiveValue);
/*      */           case 5:
/*  664 */             return (defaultValue != null && ((Integer)defaultValue).intValue() == (int)this.newSimplePrimitiveValue);
/*      */           case 7:
/*  666 */             return (defaultValue != null && ((Short)defaultValue).shortValue() == (short)(int)this.newSimplePrimitiveValue);
/*      */           case 3:
/*  668 */             return (defaultValue != null && ((Double)defaultValue).doubleValue() == this.newIEEEPrimitiveValue);
/*      */           case 4:
/*  670 */             return (defaultValue != null && ((Float)defaultValue).floatValue() == (float)this.newIEEEPrimitiveValue);
/*      */         } 
/*  672 */         return (defaultValue == null) ? ((this.newValue == null)) : defaultValue.equals(this.newValue);
/*      */       
/*      */       case 2:
/*  675 */         return true;
/*      */     } 
/*  677 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean wasSet() {
/*  683 */     switch (this.eventType) {
/*      */ 
/*      */       
/*      */       case 1:
/*  687 */         if (isFeatureUnsettable())
/*      */         {
/*  689 */           return (this.position != -2);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  695 */         if (isFeatureUnsettable())
/*      */         {
/*  697 */           return (this.position == -2);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*  707 */         return (this.position > -2);
/*      */ 
/*      */       
/*      */       default:
/*  711 */         return false;
/*      */     } 
/*      */ 
/*      */     
/*  715 */     Object defaultValue = getFeatureDefaultValue();
/*  716 */     switch (this.primitiveType) {
/*      */       
/*      */       case 0:
/*  719 */         return (defaultValue != null && ((Boolean)defaultValue).booleanValue() != ((this.oldSimplePrimitiveValue != 0L)));
/*      */       case 1:
/*  721 */         return (defaultValue != null && ((Byte)defaultValue).byteValue() != (byte)(int)this.oldSimplePrimitiveValue);
/*      */       case 2:
/*  723 */         return (defaultValue != null && ((Character)defaultValue).charValue() != (char)(int)this.oldSimplePrimitiveValue);
/*      */       case 6:
/*  725 */         return (defaultValue != null && ((Long)defaultValue).longValue() != this.oldSimplePrimitiveValue);
/*      */       case 5:
/*  727 */         return (defaultValue != null && ((Integer)defaultValue).intValue() != (int)this.oldSimplePrimitiveValue);
/*      */       case 7:
/*  729 */         return (defaultValue != null && ((Short)defaultValue).shortValue() != (short)(int)this.oldSimplePrimitiveValue);
/*      */       case 3:
/*  731 */         return (defaultValue != null && ((Double)defaultValue).doubleValue() != this.oldIEEEPrimitiveValue);
/*      */       case 4:
/*  733 */         return (defaultValue != null && ((Float)defaultValue).floatValue() != (float)this.oldIEEEPrimitiveValue);
/*      */     } 
/*  735 */     return (defaultValue == null) ? ((this.oldValue != null)) : (!defaultValue.equals(this.oldValue));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isFeatureUnsettable() {
/*  741 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object getFeatureDefaultValue() {
/*  746 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPosition() {
/*  751 */     return (this.position < 0) ? ((this.position < -2) ? (-2 - this.position - 1) : -1) : this.position;
/*      */   }
/*      */   public boolean merge(Notification notification) {
/*      */     int notificationEventType;
/*      */     Object notificationNotifier;
/*  756 */     switch (this.eventType) {
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 2:
/*  761 */         notificationEventType = notification.getEventType();
/*  762 */         switch (notificationEventType) {
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 2:
/*  767 */             notificationNotifier = notification.getNotifier();
/*  768 */             if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null)) {
/*      */               
/*  770 */               this.newValue = notification.getNewValue();
/*  771 */               if (notification.getEventType() == 1)
/*      */               {
/*  773 */                 this.eventType = 1;
/*      */               }
/*  775 */               return true;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */       
/*      */       
/*      */       case 4:
/*  782 */         notificationEventType = notification.getEventType();
/*  783 */         switch (notificationEventType) {
/*      */ 
/*      */           
/*      */           case 4:
/*  787 */             notificationNotifier = notification.getNotifier();
/*  788 */             if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null)) {
/*      */               
/*  790 */               boolean originalWasSet = wasSet();
/*  791 */               int originalPosition = getPosition();
/*  792 */               int notificationPosition = notification.getPosition();
/*      */               
/*  794 */               this.eventType = 6;
/*  795 */               BasicEList<Object> removedValues = new BasicEList(2);
/*  796 */               if (originalPosition <= notificationPosition) {
/*      */                 
/*  798 */                 removedValues.add(this.oldValue);
/*  799 */                 removedValues.add(notification.getOldValue());
/*  800 */                 this.newValue = new int[] { this.position = originalPosition, notificationPosition + 1 };
/*      */               }
/*      */               else {
/*      */                 
/*  804 */                 removedValues.add(notification.getOldValue());
/*  805 */                 removedValues.add(this.oldValue);
/*  806 */                 this.newValue = new int[] { this.position = notificationPosition, originalPosition };
/*      */               } 
/*  808 */               this.oldValue = removedValues;
/*      */               
/*  810 */               if (!originalWasSet)
/*      */               {
/*  812 */                 this.position = -2 - this.position - 1;
/*      */               }
/*      */               
/*  815 */               return true;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 6:
/*  824 */         notificationEventType = notification.getEventType();
/*  825 */         switch (notificationEventType) {
/*      */ 
/*      */           
/*      */           case 4:
/*  829 */             notificationNotifier = notification.getNotifier();
/*  830 */             if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null)) {
/*      */               
/*  832 */               boolean originalWasSet = wasSet();
/*  833 */               int notificationPosition = notification.getPosition();
/*      */               
/*  835 */               int[] positions = (int[])this.newValue;
/*  836 */               int[] newPositions = new int[positions.length + 1];
/*      */               
/*  838 */               int index = 0;
/*  839 */               while (index < positions.length) {
/*      */                 
/*  841 */                 int oldPosition = positions[index];
/*  842 */                 if (oldPosition <= notificationPosition) {
/*      */                   
/*  844 */                   newPositions[index++] = oldPosition;
/*  845 */                   notificationPosition++;
/*      */                   
/*      */                   continue;
/*      */                 } 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*  853 */               List<Object> list = (List<Object>)this.oldValue;
/*  854 */               list.add(index, notification.getOldValue());
/*  855 */               newPositions[index] = notificationPosition;
/*      */               
/*  857 */               while (++index < newPositions.length)
/*      */               {
/*  859 */                 newPositions[index] = positions[index - 1];
/*      */               }
/*      */               
/*  862 */               this.newValue = newPositions;
/*      */               
/*  864 */               if (!originalWasSet)
/*      */               {
/*  866 */                 this.position = -2 - newPositions[0];
/*      */               }
/*      */               
/*  869 */               return true;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */     } 
/*      */     
/*  878 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getOldBooleanValue() {
/*  883 */     if (this.primitiveType != 0) throw new IllegalStateException(); 
/*  884 */     return (this.oldSimplePrimitiveValue != 0L);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getNewBooleanValue() {
/*  889 */     if (this.primitiveType != 0) throw new IllegalStateException(); 
/*  890 */     return (this.newSimplePrimitiveValue != 0L);
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getOldByteValue() {
/*  895 */     if (this.primitiveType != 1) throw new IllegalStateException(); 
/*  896 */     return (byte)(int)this.oldSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getNewByteValue() {
/*  901 */     if (this.primitiveType != 1) throw new IllegalStateException(); 
/*  902 */     return (byte)(int)this.newSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public char getOldCharValue() {
/*  907 */     if (this.primitiveType != 2) throw new IllegalStateException(); 
/*  908 */     return (char)(int)this.oldSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public char getNewCharValue() {
/*  913 */     if (this.primitiveType != 2) throw new IllegalStateException(); 
/*  914 */     return (char)(int)this.newSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getOldDoubleValue() {
/*  919 */     if (this.primitiveType != 3) throw new IllegalStateException(); 
/*  920 */     return this.oldIEEEPrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getNewDoubleValue() {
/*  925 */     if (this.primitiveType != 3) throw new IllegalStateException(); 
/*  926 */     return this.newIEEEPrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getOldFloatValue() {
/*  931 */     if (this.primitiveType != 4) throw new IllegalStateException(); 
/*  932 */     return (float)this.oldIEEEPrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getNewFloatValue() {
/*  937 */     if (this.primitiveType != 4) throw new IllegalStateException(); 
/*  938 */     return (float)this.newIEEEPrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getOldIntValue() {
/*  943 */     if (this.primitiveType != 5) throw new IllegalStateException(); 
/*  944 */     return (int)this.oldSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNewIntValue() {
/*  949 */     if (this.primitiveType != 5) throw new IllegalStateException(); 
/*  950 */     return (int)this.newSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getOldLongValue() {
/*  955 */     if (this.primitiveType != 6) throw new IllegalStateException(); 
/*  956 */     return this.oldSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getNewLongValue() {
/*  961 */     if (this.primitiveType != 6) throw new IllegalStateException(); 
/*  962 */     return this.newSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public short getOldShortValue() {
/*  967 */     if (this.primitiveType != 7) throw new IllegalStateException(); 
/*  968 */     return (short)(int)this.oldSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public short getNewShortValue() {
/*  973 */     if (this.primitiveType != 7) throw new IllegalStateException(); 
/*  974 */     return (short)(int)this.newSimplePrimitiveValue;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getOldStringValue() {
/*  979 */     return (this.oldValue == null) ? null : this.oldValue.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNewStringValue() {
/*  984 */     return (this.newValue == null) ? null : this.newValue.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean add(Notification newNotification) {
/*  994 */     if (newNotification == null)
/*      */     {
/*  996 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1000 */     if (merge(newNotification))
/*      */     {
/* 1002 */       return false;
/*      */     }
/*      */     
/* 1005 */     if (this.next == null) {
/*      */       
/* 1007 */       if (newNotification instanceof NotificationImpl) {
/*      */         
/* 1009 */         this.next = (NotificationImpl)newNotification;
/* 1010 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1014 */       this.next = new NotificationChainImpl();
/* 1015 */       return this.next.add(newNotification);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1020 */     return this.next.add(newNotification);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispatch() {
/* 1030 */     Object notifier = getNotifier();
/* 1031 */     if (notifier != null && getEventType() != -1)
/*      */     {
/* 1033 */       ((Notifier)notifier).eNotify(this);
/*      */     }
/*      */     
/* 1036 */     if (this.next != null)
/*      */     {
/* 1038 */       this.next.dispatch();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1045 */     StringBuffer result = new StringBuffer(super.toString());
/* 1046 */     result.append(" (eventType: ");
/* 1047 */     switch (this.eventType) {
/*      */ 
/*      */       
/*      */       case 1:
/* 1051 */         result.append("SET");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/* 1056 */         result.append("UNSET");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/* 1061 */         result.append("ADD");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 5:
/* 1066 */         result.append("ADD_MANY");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/* 1071 */         result.append("REMOVE");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/* 1076 */         result.append("REMOVE_MANY");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 7:
/* 1081 */         result.append("MOVE");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 8:
/* 1086 */         result.append("REMOVING_ADAPTER");
/*      */         break;
/*      */ 
/*      */       
/*      */       case 9:
/* 1091 */         result.append("RESOLVE");
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 1096 */         result.append(this.eventType);
/*      */         break;
/*      */     } 
/*      */     
/* 1100 */     if (isTouch())
/*      */     {
/* 1102 */       result.append(", touch: true");
/*      */     }
/* 1104 */     result.append(", position: ");
/* 1105 */     result.append(getPosition());
/* 1106 */     result.append(", notifier: ");
/* 1107 */     result.append(getNotifier());
/* 1108 */     result.append(", feature: ");
/* 1109 */     result.append(getFeature());
/* 1110 */     result.append(", oldValue: ");
/* 1111 */     result.append(getOldValue());
/* 1112 */     result.append(", newValue: ");
/* 1113 */     if (this.eventType == 6 && this.newValue instanceof int[]) {
/*      */       
/* 1115 */       int[] positions = (int[])this.newValue;
/* 1116 */       result.append("[");
/* 1117 */       for (int i = 0; i < positions.length; ) {
/*      */         
/* 1119 */         result.append(positions[i]);
/* 1120 */         if (++i < positions.length)
/*      */         {
/* 1122 */           result.append(", ");
/*      */         }
/*      */       } 
/* 1125 */       result.append("]");
/*      */     }
/*      */     else {
/*      */       
/* 1129 */       result.append(getNewValue());
/*      */     } 
/*      */     
/* 1132 */     result.append(", isTouch: ");
/* 1133 */     result.append(isTouch());
/* 1134 */     result.append(", wasSet: ");
/* 1135 */     result.append(wasSet());
/* 1136 */     result.append(")");
/*      */     
/* 1138 */     return result.toString();
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\NotificationImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */