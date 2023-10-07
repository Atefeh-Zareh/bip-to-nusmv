/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.ecore.EAnnotation;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EModelElement;
/*     */ import org.eclipse.emf.ecore.ENamedElement;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EModelElementImpl
/*     */   extends MinimalEObjectImpl.Container
/*     */   implements EModelElement
/*     */ {
/*     */   protected EList<EAnnotation> eAnnotations;
/*     */   protected int eFlags;
/*     */   protected static final int EFROZEN = 1;
/*     */   protected static final int ELAST_EMODEL_ELEMENT_FLAG = 1;
/*     */   
/*     */   protected void freeze() {
/*  93 */     setFrozen();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void freeze(Object eModelElement) {
/*  98 */     if (eModelElement instanceof EModelElementImpl)
/*     */     {
/* 100 */       ((EModelElementImpl)eModelElement).freeze();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setFrozen() {
/* 106 */     this.eFlags |= 0x1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFrozen() {
/* 111 */     return ((this.eFlags & 0x1) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void eSetDirectResource(Resource.Internal resource) {
/* 117 */     assert !isFrozen() : "A frozen model should not be modified";
/* 118 */     super.eSetDirectResource(resource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 129 */     return EcorePackage.Literals.EMODEL_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EAnnotation> getEAnnotations() {
/* 139 */     if (this.eAnnotations == null)
/*     */     {
/* 141 */       this.eAnnotations = (EList<EAnnotation>)new EObjectContainmentWithInverseEList(EAnnotation.class, this, 0, 3);
/*     */     }
/* 143 */     return this.eAnnotations;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EAnnotation getEAnnotation(String source) {
/* 153 */     if (this.eAnnotations != null)
/*     */     {
/* 155 */       if (this.eAnnotations instanceof BasicEList) {
/*     */         
/* 157 */         int size = this.eAnnotations.size();
/* 158 */         if (size > 0)
/*     */         {
/* 160 */           EAnnotation[] eAnnotationArray = (EAnnotation[])((BasicEList)this.eAnnotations).data();
/* 161 */           if (source == null) {
/*     */             
/* 163 */             for (int i = 0; i < size; i++)
/*     */             {
/* 165 */               EAnnotation eAnnotation = eAnnotationArray[i];
/* 166 */               if (eAnnotation.getSource() == null)
/*     */               {
/* 168 */                 return eAnnotation;
/*     */               }
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 174 */             for (int i = 0; i < size; i++)
/*     */             {
/* 176 */               EAnnotation eAnnotation = eAnnotationArray[i];
/* 177 */               if (source.equals(eAnnotation.getSource()))
/*     */               {
/* 179 */                 return eAnnotation;
/*     */               }
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 187 */       } else if (source == null) {
/*     */         
/* 189 */         for (EAnnotation eAnnotation : this.eAnnotations)
/*     */         {
/* 191 */           if (eAnnotation.getSource() == null)
/*     */           {
/* 193 */             return eAnnotation;
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 199 */         for (EAnnotation eAnnotation : this.eAnnotations) {
/*     */           
/* 201 */           if (source.equals(eAnnotation.getSource()))
/*     */           {
/* 203 */             return eAnnotation;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 210 */     return null;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 222 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 225 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 227 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
/* 238 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 241 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 243 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
/* 254 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 257 */         return getEAnnotations();
/*     */     } 
/* 259 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 271 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 274 */         getEAnnotations().clear();
/* 275 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 278 */     eDynamicSet(featureID, newValue);
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
/* 289 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 292 */         getEAnnotations().clear();
/*     */         return;
/*     */     } 
/* 295 */     eDynamicUnset(featureID);
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
/* 306 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 309 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */     } 
/* 311 */     return eDynamicIsSet(featureID);
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
/* 322 */     switch (operationID) {
/*     */       
/*     */       case 0:
/* 325 */         return getEAnnotation((String)arguments.get(0));
/*     */     } 
/* 327 */     return eDynamicInvoke(operationID, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject) {
/* 333 */     if (eObject instanceof ENamedElement) {
/*     */       
/* 335 */       ENamedElement eNamedElement = (ENamedElement)eObject;
/* 336 */       String name = eNamedElement.getName();
/* 337 */       if (name != null)
/*     */       {
/* 339 */         int count = 0;
/* 340 */         for (EObject otherEObject : eContents()) {
/*     */           
/* 342 */           if (otherEObject == eObject) {
/*     */             break;
/*     */           }
/*     */           
/* 346 */           if (otherEObject instanceof ENamedElement) {
/*     */             
/* 348 */             ENamedElement otherENamedElement = (ENamedElement)otherEObject;
/* 349 */             if (name.equals(otherENamedElement.getName()))
/*     */             {
/* 351 */               count++;
/*     */             }
/*     */           } 
/*     */         } 
/* 355 */         name = eEncodeValue(name);
/* 356 */         return 
/* 357 */           (count > 0) ? (
/* 358 */           String.valueOf(name) + "." + count) : 
/* 359 */           name;
/*     */       }
/*     */     
/* 362 */     } else if (eObject instanceof EAnnotation) {
/*     */       
/* 364 */       EAnnotation eAnnotation = (EAnnotation)eObject;
/* 365 */       String source = eAnnotation.getSource();
/* 366 */       if (source != null) {
/*     */         
/* 368 */         int count = 0;
/* 369 */         for (EObject otherEObject : eContents()) {
/*     */           
/* 371 */           if (otherEObject == eObject) {
/*     */             break;
/*     */           }
/*     */           
/* 375 */           if (otherEObject instanceof EAnnotation) {
/*     */             
/* 377 */             EAnnotation otherEAnnotation = (EAnnotation)otherEObject;
/* 378 */             if (source.equals(otherEAnnotation.getSource()))
/*     */             {
/* 380 */               count++;
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 385 */         StringBuffer result = new StringBuffer(source.length() + 5);
/* 386 */         result.append('%');
/* 387 */         result.append(URI.encodeSegment(source, false));
/* 388 */         result.append('%');
/* 389 */         if (count > 0) {
/*     */           
/* 391 */           result.append('.');
/* 392 */           result.append(count);
/*     */         } 
/* 394 */         return result.toString();
/*     */       } 
/*     */     } 
/* 397 */     return super.eURIFragmentSegment(eStructuralFeature, eObject);
/*     */   }
/*     */   
/*     */   public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual length : ()I
/*     */     //   4: istore_2
/*     */     //   5: iload_2
/*     */     //   6: ifle -> 335
/*     */     //   9: aload_1
/*     */     //   10: iconst_0
/*     */     //   11: invokevirtual charAt : (I)C
/*     */     //   14: istore_3
/*     */     //   15: iload_3
/*     */     //   16: bipush #64
/*     */     //   18: if_icmpeq -> 335
/*     */     //   21: iload_3
/*     */     //   22: bipush #37
/*     */     //   24: if_icmpne -> 195
/*     */     //   27: aload_1
/*     */     //   28: ldc_w '%'
/*     */     //   31: invokevirtual lastIndexOf : (Ljava/lang/String;)I
/*     */     //   34: istore #4
/*     */     //   36: iconst_0
/*     */     //   37: istore #5
/*     */     //   39: iload #4
/*     */     //   41: iload_2
/*     */     //   42: iconst_1
/*     */     //   43: isub
/*     */     //   44: if_icmpeq -> 71
/*     */     //   47: aload_1
/*     */     //   48: iload #4
/*     */     //   50: iconst_1
/*     */     //   51: iadd
/*     */     //   52: invokevirtual charAt : (I)C
/*     */     //   55: bipush #46
/*     */     //   57: if_icmpne -> 64
/*     */     //   60: iconst_1
/*     */     //   61: goto -> 65
/*     */     //   64: iconst_0
/*     */     //   65: dup
/*     */     //   66: istore #5
/*     */     //   68: ifeq -> 195
/*     */     //   71: aload_1
/*     */     //   72: iconst_1
/*     */     //   73: iload #4
/*     */     //   75: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   78: invokestatic decode : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   81: astore #6
/*     */     //   83: iconst_0
/*     */     //   84: istore #7
/*     */     //   86: iload #5
/*     */     //   88: ifeq -> 119
/*     */     //   91: aload_1
/*     */     //   92: iload #4
/*     */     //   94: iconst_2
/*     */     //   95: iadd
/*     */     //   96: invokevirtual substring : (I)Ljava/lang/String;
/*     */     //   99: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   102: istore #7
/*     */     //   104: goto -> 119
/*     */     //   107: astore #8
/*     */     //   109: new org/eclipse/emf/common/util/WrappedException
/*     */     //   112: dup
/*     */     //   113: aload #8
/*     */     //   115: invokespecial <init> : (Ljava/lang/Exception;)V
/*     */     //   118: athrow
/*     */     //   119: aload_0
/*     */     //   120: invokevirtual eContents : ()Lorg/eclipse/emf/common/util/EList;
/*     */     //   123: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   128: astore #9
/*     */     //   130: goto -> 183
/*     */     //   133: aload #9
/*     */     //   135: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   140: astore #8
/*     */     //   142: aload #8
/*     */     //   144: instanceof org/eclipse/emf/ecore/EAnnotation
/*     */     //   147: ifeq -> 183
/*     */     //   150: aload #8
/*     */     //   152: checkcast org/eclipse/emf/ecore/EAnnotation
/*     */     //   155: astore #10
/*     */     //   157: aload #6
/*     */     //   159: aload #10
/*     */     //   161: invokeinterface getSource : ()Ljava/lang/String;
/*     */     //   166: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   169: ifeq -> 183
/*     */     //   172: iload #7
/*     */     //   174: iinc #7, -1
/*     */     //   177: ifne -> 183
/*     */     //   180: aload #10
/*     */     //   182: areturn
/*     */     //   183: aload #9
/*     */     //   185: invokeinterface hasNext : ()Z
/*     */     //   190: ifne -> 133
/*     */     //   193: aconst_null
/*     */     //   194: areturn
/*     */     //   195: aload_1
/*     */     //   196: ldc '.'
/*     */     //   198: invokevirtual lastIndexOf : (Ljava/lang/String;)I
/*     */     //   201: istore #4
/*     */     //   203: iload #4
/*     */     //   205: iconst_m1
/*     */     //   206: if_icmpne -> 213
/*     */     //   209: aload_1
/*     */     //   210: goto -> 220
/*     */     //   213: aload_1
/*     */     //   214: iconst_0
/*     */     //   215: iload #4
/*     */     //   217: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   220: astore #5
/*     */     //   222: iconst_0
/*     */     //   223: istore #6
/*     */     //   225: iload #4
/*     */     //   227: iconst_m1
/*     */     //   228: if_icmpeq -> 252
/*     */     //   231: aload_1
/*     */     //   232: iload #4
/*     */     //   234: iconst_1
/*     */     //   235: iadd
/*     */     //   236: invokevirtual substring : (I)Ljava/lang/String;
/*     */     //   239: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   242: istore #6
/*     */     //   244: goto -> 252
/*     */     //   247: astore #7
/*     */     //   249: aload_1
/*     */     //   250: astore #5
/*     */     //   252: aload #5
/*     */     //   254: invokestatic decode : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   257: astore #5
/*     */     //   259: aload_0
/*     */     //   260: invokevirtual eContents : ()Lorg/eclipse/emf/common/util/EList;
/*     */     //   263: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   268: astore #8
/*     */     //   270: goto -> 323
/*     */     //   273: aload #8
/*     */     //   275: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   280: astore #7
/*     */     //   282: aload #7
/*     */     //   284: instanceof org/eclipse/emf/ecore/ENamedElement
/*     */     //   287: ifeq -> 323
/*     */     //   290: aload #7
/*     */     //   292: checkcast org/eclipse/emf/ecore/ENamedElement
/*     */     //   295: astore #9
/*     */     //   297: aload #5
/*     */     //   299: aload #9
/*     */     //   301: invokeinterface getName : ()Ljava/lang/String;
/*     */     //   306: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   309: ifeq -> 323
/*     */     //   312: iload #6
/*     */     //   314: iinc #6, -1
/*     */     //   317: ifne -> 323
/*     */     //   320: aload #9
/*     */     //   322: areturn
/*     */     //   323: aload #8
/*     */     //   325: invokeinterface hasNext : ()Z
/*     */     //   330: ifne -> 273
/*     */     //   333: aconst_null
/*     */     //   334: areturn
/*     */     //   335: aload_0
/*     */     //   336: aload_1
/*     */     //   337: invokespecial eObjectForURIFragmentSegment : (Ljava/lang/String;)Lorg/eclipse/emf/ecore/EObject;
/*     */     //   340: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #403	-> 0
/*     */     //   #404	-> 5
/*     */     //   #408	-> 9
/*     */     //   #409	-> 15
/*     */     //   #413	-> 21
/*     */     //   #417	-> 27
/*     */     //   #418	-> 36
/*     */     //   #419	-> 39
/*     */     //   #423	-> 71
/*     */     //   #427	-> 83
/*     */     //   #428	-> 86
/*     */     //   #432	-> 91
/*     */     //   #434	-> 107
/*     */     //   #436	-> 109
/*     */     //   #442	-> 119
/*     */     //   #444	-> 142
/*     */     //   #446	-> 150
/*     */     //   #447	-> 157
/*     */     //   #449	-> 180
/*     */     //   #442	-> 183
/*     */     //   #453	-> 193
/*     */     //   #459	-> 195
/*     */     //   #460	-> 203
/*     */     //   #461	-> 222
/*     */     //   #462	-> 225
/*     */     //   #466	-> 231
/*     */     //   #468	-> 247
/*     */     //   #472	-> 249
/*     */     //   #476	-> 252
/*     */     //   #480	-> 259
/*     */     //   #482	-> 282
/*     */     //   #484	-> 290
/*     */     //   #485	-> 297
/*     */     //   #487	-> 320
/*     */     //   #480	-> 323
/*     */     //   #492	-> 333
/*     */     //   #496	-> 335
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	341	0	this	Lorg/eclipse/emf/ecore/impl/EModelElementImpl;
/*     */     //   0	341	1	uriFragmentSegment	Ljava/lang/String;
/*     */     //   5	336	2	length	I
/*     */     //   15	320	3	firstCharacter	C
/*     */     //   36	159	4	index	I
/*     */     //   39	156	5	hasCount	Z
/*     */     //   83	112	6	source	Ljava/lang/String;
/*     */     //   86	109	7	count	I
/*     */     //   109	10	8	exception	Ljava/lang/NumberFormatException;
/*     */     //   142	41	8	object	Ljava/lang/Object;
/*     */     //   157	26	10	eAnnotation	Lorg/eclipse/emf/ecore/EAnnotation;
/*     */     //   203	132	4	index	I
/*     */     //   222	113	5	name	Ljava/lang/String;
/*     */     //   225	110	6	count	I
/*     */     //   249	3	7	exception	Ljava/lang/NumberFormatException;
/*     */     //   282	41	7	object	Ljava/lang/Object;
/*     */     //   297	26	9	eNamedElement	Lorg/eclipse/emf/ecore/ENamedElement;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   91	104	107	java/lang/NumberFormatException
/*     */     //   231	244	247	java/lang/NumberFormatException
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EModelElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */