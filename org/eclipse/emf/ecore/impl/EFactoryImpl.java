/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.DateFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
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
/*     */ public class EFactoryImpl
/*     */   extends EModelElementImpl
/*     */   implements EFactory
/*     */ {
/*     */   protected EPackage ePackage;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 100 */     return EcorePackage.Literals.EFACTORY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage getEPackage() {
/* 110 */     return this.ePackage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEPackage(EPackage newEPackage) {
/* 120 */     if (newEPackage != this.ePackage) {
/*     */       
/* 122 */       NotificationChain msgs = null;
/* 123 */       if (this.ePackage != null)
/* 124 */         msgs = ((InternalEObject)this.ePackage).eInverseRemove(this, 4, EPackage.class, msgs); 
/* 125 */       if (newEPackage != null)
/* 126 */         msgs = ((InternalEObject)newEPackage).eInverseAdd(this, 4, EPackage.class, msgs); 
/* 127 */       msgs = basicSetEPackage(newEPackage, msgs);
/* 128 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 130 */     } else if (eNotificationRequired()) {
/* 131 */       eNotify((Notification)new ENotificationImpl(this, 1, 1, newEPackage, newEPackage));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetEPackage(EPackage newEPackage, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 141 */     EPackage oldEPackage = this.ePackage;
/* 142 */     this.ePackage = newEPackage;
/* 143 */     if (eNotificationRequired()) {
/*     */       
/* 145 */       ENotificationImpl notification = new ENotificationImpl(this, 1, 1, oldEPackage, newEPackage);
/* 146 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 148 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 159 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 162 */         return getEAnnotations();
/*     */       case 1:
/* 164 */         return getEPackage();
/*     */     } 
/* 166 */     return eDynamicGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 178 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 181 */         getEAnnotations().clear();
/* 182 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 185 */         setEPackage((EPackage)newValue);
/*     */         return;
/*     */     } 
/* 188 */     eDynamicSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 199 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 202 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 205 */         setEPackage((EPackage)null);
/*     */         return;
/*     */     } 
/* 208 */     eDynamicUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 219 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 222 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 224 */         return (this.ePackage != null);
/*     */     } 
/* 226 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
/* 237 */     switch (operationID) {
/*     */       
/*     */       case 0:
/* 240 */         return getEAnnotation((String)arguments.get(0));
/*     */       case 1:
/* 242 */         return create((EClass)arguments.get(0));
/*     */       case 2:
/* 244 */         return createFromString((EDataType)arguments.get(0), (String)arguments.get(1));
/*     */       case 3:
/* 246 */         return convertToString((EDataType)arguments.get(0), arguments.get(1));
/*     */     } 
/* 248 */     return eDynamicInvoke(operationID, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/* 256 */     if (getEPackage() != eClass.getEPackage() || eClass.isAbstract())
/*     */     {
/* 258 */       throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */     }
/*     */     
/* 261 */     for (EList<EClass> eList = eClass.getESuperTypes(); !eList.isEmpty(); ) {
/*     */       
/* 263 */       EClass eSuperType = eList.get(0);
/* 264 */       if (eSuperType.getInstanceClass() != null) {
/*     */         
/* 266 */         EObject result = eSuperType.getEPackage().getEFactoryInstance().create(eSuperType);
/* 267 */         ((InternalEObject)result).eSetClass(eClass);
/* 268 */         return result;
/*     */       } 
/* 270 */       eList = eSuperType.getESuperTypes();
/*     */     } 
/*     */     
/* 273 */     return basicCreate(eClass);
/*     */   }
/*     */ 
/*     */   
/*     */   protected EObject basicCreate(EClass eClass) {
/* 278 */     return 
/* 279 */       (eClass.getInstanceClassName() == "java.util.Map$Entry") ? 
/* 280 */       new DynamicEObjectImpl.BasicEMapEntry<Object, Object>(eClass) : 
/* 281 */       new DynamicEObjectImpl(eClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object createFromString(EDataType eDataType, String stringValue) {
/* 289 */     if (stringValue == null)
/*     */     {
/* 291 */       return null;
/*     */     }
/*     */     
/* 294 */     if (getEPackage() != eDataType.getEPackage())
/*     */     {
/* 296 */       throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */     }
/*     */     
/* 299 */     if (eDataType instanceof EEnum) {
/*     */       
/* 301 */       Object result = ((EEnum)eDataType).getEEnumLiteralByLiteral(stringValue);
/* 302 */       if (result == null)
/*     */       {
/* 304 */         throw new IllegalArgumentException("The value '" + stringValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
/*     */       }
/* 306 */       return result;
/*     */     } 
/*     */     
/* 309 */     switch (ExtendedMetaData.INSTANCE.getWhiteSpaceFacet(eDataType)) {
/*     */ 
/*     */       
/*     */       case 2:
/* 313 */         stringValue = replaceWhiteSpace(stringValue);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 318 */         stringValue = collapseWhiteSpace(stringValue);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 323 */     EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(eDataType);
/* 324 */     if (baseType != null)
/*     */     {
/* 326 */       return EcoreUtil.createFromString(baseType, stringValue);
/*     */     }
/*     */     
/* 329 */     EDataType itemType = ExtendedMetaData.INSTANCE.getItemType(eDataType);
/* 330 */     if (itemType != null) {
/*     */       
/* 332 */       List<Object> result = new ArrayList();
/* 333 */       for (StringTokenizer stringTokenizer = new StringTokenizer(stringValue); stringTokenizer.hasMoreTokens(); ) {
/*     */         
/* 335 */         String item = stringTokenizer.nextToken();
/* 336 */         result.add(EcoreUtil.createFromString(itemType, item));
/*     */       } 
/* 338 */       return result;
/*     */     } 
/*     */     
/* 341 */     List<EDataType> memberTypes = ExtendedMetaData.INSTANCE.getMemberTypes(eDataType);
/* 342 */     if (!memberTypes.isEmpty()) {
/*     */       
/* 344 */       for (EDataType memberType : memberTypes) {
/*     */ 
/*     */         
/*     */         try {
/* 348 */           Object result = EcoreUtil.createFromString(memberType, stringValue);
/* 349 */           if (result != null)
/*     */           {
/* 351 */             return result;
/*     */           }
/*     */         }
/* 354 */         catch (RuntimeException runtimeException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 359 */       throw new IllegalArgumentException("The value '" + stringValue + "' does not match any member types of the union datatype '" + eDataType.getName() + "'");
/*     */     } 
/*     */     
/* 362 */     Class<?> c = EcoreUtil.wrapperClassFor(eDataType.getInstanceClass());
/* 363 */     if (c == null) return null;
/*     */     
/* 365 */     if (c == Character.class) {
/*     */       
/* 367 */       char charValue = Character.MIN_VALUE;
/*     */       
/*     */       try {
/* 370 */         charValue = (char)Integer.parseInt(stringValue);
/*     */       }
/* 372 */       catch (NumberFormatException e) {
/*     */         
/* 374 */         char[] carray = stringValue.toCharArray();
/* 375 */         charValue = carray[0];
/*     */       } 
/*     */       
/* 378 */       return Character.valueOf(charValue);
/*     */     } 
/*     */     
/* 381 */     if (c == Date.class) {
/*     */       
/* 383 */       for (int i = 0; i < EDATE_FORMATS.length; i++) {
/*     */ 
/*     */         
/*     */         try {
/* 387 */           return EDATE_FORMATS[i].parse(stringValue);
/*     */         }
/* 389 */         catch (ParseException parseException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 394 */       throw new IllegalArgumentException("The value '" + stringValue + "' is not a date formatted string of the form yyyy-MM-dd'T'HH:mm:ss'.'SSSZ or a valid subset thereof");
/*     */     } 
/*     */     
/* 397 */     Class<String> stringClass = String.class;
/* 398 */     Class[] signature = { stringClass };
/*     */     
/* 400 */     Constructor<?> ctor = null;
/*     */     
/*     */     try {
/* 403 */       ctor = c.getConstructor(signature);
/*     */     }
/* 405 */     catch (NoSuchMethodException noSuchMethodException) {}
/*     */ 
/*     */ 
/*     */     
/* 409 */     Throwable formatException = null;
/*     */     
/*     */     try {
/* 412 */       if (ctor != null)
/*     */       {
/* 414 */         Object[] ctorArgs = { stringValue };
/* 415 */         return ctor.newInstance(ctorArgs);
/*     */       }
/*     */     
/* 418 */     } catch (InstantiationException e) {
/*     */       
/* 420 */       formatException = e;
/*     */     }
/* 422 */     catch (InvocationTargetException e) {
/*     */       
/* 424 */       formatException = e.getCause();
/*     */     }
/* 426 */     catch (IllegalAccessException e) {
/*     */       
/* 428 */       formatException = e;
/*     */     } 
/*     */     
/* 431 */     Method valueOf = null;
/*     */     
/*     */     try {
/* 434 */       valueOf = c.getMethod("valueOf", signature);
/*     */     }
/* 436 */     catch (NoSuchMethodException noSuchMethodException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 443 */       if (valueOf != null)
/*     */       {
/* 445 */         Object[] valueOfArgs = { stringValue };
/* 446 */         return valueOf.invoke(null, valueOfArgs);
/*     */       }
/*     */     
/* 449 */     } catch (IllegalArgumentException e) {
/*     */       
/* 451 */       formatException = e;
/*     */     }
/* 453 */     catch (InvocationTargetException e) {
/*     */       
/* 455 */       formatException = e.getCause();
/*     */     }
/* 457 */     catch (IllegalAccessException e) {
/*     */       
/* 459 */       formatException = e;
/*     */     } 
/* 461 */     String exceptionString = (formatException != null) ? formatException.toString() : "";
/* 462 */     throw new IllegalArgumentException("The value '" + stringValue + "' is invalid. " + exceptionString, formatException);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertToString(EDataType eDataType, Object objectValue) {
/* 470 */     if (getEPackage() != eDataType.getEPackage())
/*     */     {
/* 472 */       throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */     }
/*     */     
/* 475 */     EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(eDataType);
/* 476 */     if (baseType != null)
/*     */     {
/* 478 */       return EcoreUtil.convertToString(baseType, objectValue);
/*     */     }
/*     */     
/* 481 */     EDataType itemType = ExtendedMetaData.INSTANCE.getItemType(eDataType);
/* 482 */     if (itemType != null) {
/*     */       
/* 484 */       if (objectValue == null)
/*     */       {
/* 486 */         return null;
/*     */       }
/* 488 */       List<?> list = (List)objectValue;
/* 489 */       if (list.isEmpty())
/*     */       {
/* 491 */         return "";
/*     */       }
/* 493 */       StringBuffer result = new StringBuffer();
/* 494 */       for (Object item : list) {
/*     */         
/* 496 */         result.append(EcoreUtil.convertToString(itemType, item));
/* 497 */         result.append(' ');
/*     */       } 
/* 499 */       return result.substring(0, result.length() - 1);
/*     */     } 
/*     */     
/* 502 */     List<EDataType> memberTypes = ExtendedMetaData.INSTANCE.getMemberTypes(eDataType);
/* 503 */     if (!memberTypes.isEmpty()) {
/*     */       
/* 505 */       for (EDataType memberType : memberTypes) {
/*     */         
/* 507 */         if (memberType.isInstance(objectValue)) {
/*     */           
/*     */           try {
/*     */             
/* 511 */             String result = EcoreUtil.convertToString(memberType, objectValue);
/* 512 */             if (result != null)
/*     */             {
/* 514 */               return result;
/*     */             }
/*     */           }
/* 517 */           catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 523 */       throw new IllegalArgumentException("Invalid value: '" + objectValue + "' for datatype :" + eDataType.getName());
/*     */     } 
/*     */     
/* 526 */     if (objectValue == null)
/*     */     {
/* 528 */       return null;
/*     */     }
/* 530 */     if (objectValue instanceof Character)
/*     */     {
/* 532 */       return Integer.toString(((Character)objectValue).charValue());
/*     */     }
/* 534 */     if (objectValue.getClass() == Date.class)
/*     */     {
/* 536 */       return EDATE_FORMATS[0].format((Date)objectValue);
/*     */     }
/*     */ 
/*     */     
/* 540 */     return objectValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] hexStringToBytes(String initialValue) {
/* 546 */     if (initialValue == null)
/*     */     {
/* 548 */       return null;
/*     */     }
/*     */     
/* 551 */     int size = initialValue.length();
/* 552 */     int limit = (size + 1) / 2;
/* 553 */     byte[] result = new byte[limit];
/* 554 */     if (size % 2 != 0)
/*     */     {
/* 556 */       result[--limit] = hexCharToByte(initialValue.charAt(size - 1));
/*     */     }
/*     */     
/* 559 */     for (int i = 0, j = 0; i < limit; i++) {
/*     */       
/* 561 */       byte high = hexCharToByte(initialValue.charAt(j++));
/* 562 */       byte low = hexCharToByte(initialValue.charAt(j++));
/* 563 */       result[i] = (byte)(high << 4 | low);
/*     */     } 
/* 565 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   static byte hexCharToByte(char character) {
/* 570 */     switch (character) {
/*     */ 
/*     */       
/*     */       case '0':
/*     */       case '1':
/*     */       case '2':
/*     */       case '3':
/*     */       case '4':
/*     */       case '5':
/*     */       case '6':
/*     */       case '7':
/*     */       case '8':
/*     */       case '9':
/* 583 */         return (byte)(character - 48);
/*     */ 
/*     */       
/*     */       case 'a':
/*     */       case 'b':
/*     */       case 'c':
/*     */       case 'd':
/*     */       case 'e':
/*     */       case 'f':
/* 592 */         return (byte)(character - 97 + 10);
/*     */ 
/*     */       
/*     */       case 'A':
/*     */       case 'B':
/*     */       case 'C':
/*     */       case 'D':
/*     */       case 'E':
/*     */       case 'F':
/* 601 */         return (byte)(character - 65 + 10);
/*     */     } 
/*     */ 
/*     */     
/* 605 */     throw new NumberFormatException("Invalid hexadecimal");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 611 */   static final char[] HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */   
/*     */   String bytesToHexString(byte[] bytes, int count) {
/* 615 */     if (bytes == null)
/*     */     {
/* 617 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 621 */     char[] result = new char[2 * count];
/* 622 */     for (int i = 0, j = 0; i < count; i++) {
/*     */       
/* 624 */       int high = bytes[i] >> 4 & 0xF;
/* 625 */       int low = bytes[i] & 0xF;
/* 626 */       result[j++] = HEX_DIGITS[high];
/* 627 */       result[j++] = HEX_DIGITS[low];
/*     */     } 
/* 629 */     return new String(result);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object createFromString(String hexString) {
/* 635 */     if (hexString == null)
/*     */     {
/* 637 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 641 */     byte[] byteValue = hexStringToBytes(hexString);
/* 642 */     ByteArrayInputStream bytes = new ByteArrayInputStream(byteValue);
/*     */     
/*     */     try {
/* 645 */       ObjectInputStream in = new ObjectInputStream(bytes);
/* 646 */       return in.readObject();
/*     */     }
/* 648 */     catch (IOException exception) {
/*     */       
/* 650 */       throw new RuntimeException(exception);
/*     */     }
/* 652 */     catch (ClassNotFoundException exception) {
/*     */       
/* 654 */       throw new RuntimeException(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String convertToString(Object instanceValue) {
/* 661 */     ByteArrayOutputStream bytes = 
/* 662 */       new ByteArrayOutputStream()
/*     */       {
/*     */         
/*     */         public String toString()
/*     */         {
/* 667 */           return EFactoryImpl.this.bytesToHexString(this.buf, this.count);
/*     */         }
/*     */       };
/*     */     
/*     */     try {
/* 672 */       ObjectOutputStream out = new ObjectOutputStream(bytes);
/* 673 */       out.writeObject(instanceValue);
/* 674 */       out.close();
/*     */     }
/* 676 */     catch (IOException exception) {
/*     */       
/* 678 */       throw new RuntimeException(exception);
/*     */     } 
/* 680 */     return bytes.toString();
/*     */   }
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 693 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 696 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */       case 1:
/* 698 */         if (this.ePackage != null)
/* 699 */           msgs = ((InternalEObject)this.ePackage).eInverseRemove(this, 4, EPackage.class, msgs); 
/* 700 */         return basicSetEPackage((EPackage)otherEnd, msgs);
/*     */     } 
/* 702 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 713 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 716 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 1:
/* 718 */         return basicSetEPackage((EPackage)null, msgs);
/*     */     } 
/* 720 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String replaceWhiteSpace(String value) {
/* 725 */     return XMLTypeUtil.normalize(value, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String collapseWhiteSpace(String value) {
/* 730 */     return XMLTypeUtil.normalize(value, true);
/*     */   }
/*     */   
/*     */   private static class SafeSimpleDateFormat
/*     */     extends SimpleDateFormat
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     public SafeSimpleDateFormat(String pattern) {
/* 739 */       super(pattern, Locale.ENGLISH);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized Date parse(String source) throws ParseException {
/* 745 */       return super.parse(source);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
/* 751 */       return super.format(date, toAppendTo, fieldPosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 756 */   protected static final DateFormat[] EDATE_FORMATS = new DateFormat[] {
/* 757 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"), 
/* 758 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS"), 
/* 759 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), 
/* 760 */       new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm"), 
/* 761 */       new SafeSimpleDateFormat("yyyy-MM-dd")
/*     */     };
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */