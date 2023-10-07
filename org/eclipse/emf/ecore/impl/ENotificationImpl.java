/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
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
/*     */ public class ENotificationImpl
/*     */   extends NotificationImpl
/*     */ {
/*     */   protected InternalEObject notifier;
/*  32 */   protected int featureID = -1;
/*  33 */   protected EStructuralFeature feature = null;
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, EStructuralFeature feature, Object oldValue, Object newValue, boolean isSetChange) {
/*  37 */     this(notifier, eventType, feature, oldValue, newValue, isSetChange ? -2 : -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, EStructuralFeature feature, Object oldValue, Object newValue) {
/*  42 */     this(notifier, eventType, feature, oldValue, newValue, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, EStructuralFeature feature, Object oldValue, Object newValue, int position) {
/*  47 */     super(eventType, oldValue, newValue, position);
/*  48 */     this.notifier = notifier;
/*  49 */     this.feature = feature;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, EStructuralFeature feature, Object oldValue, Object newValue, int position, boolean wasSet) {
/*  54 */     super(eventType, oldValue, newValue, position, wasSet);
/*  55 */     this.notifier = notifier;
/*  56 */     this.feature = feature;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, Object oldValue, Object newValue, boolean isSetChange) {
/*  61 */     this(notifier, eventType, featureID, oldValue, newValue, isSetChange ? -2 : -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, Object oldValue, Object newValue, int position, boolean wasSet) {
/*  66 */     super(eventType, oldValue, newValue, position, wasSet);
/*  67 */     this.notifier = notifier;
/*  68 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, Object oldValue, Object newValue) {
/*  73 */     this(notifier, eventType, featureID, oldValue, newValue, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, Object oldValue, Object newValue, int position) {
/*  78 */     super(eventType, oldValue, newValue, position);
/*  79 */     this.notifier = notifier;
/*  80 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, boolean oldBooleanValue, boolean newBooleanValue, boolean isSetChange) {
/*  86 */     this(notifier, eventType, featureID, oldBooleanValue, newBooleanValue);
/*  87 */     if (isSetChange)
/*     */     {
/*  89 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, boolean oldBooleanValue, boolean newBooleanValue) {
/*  95 */     super(eventType, oldBooleanValue, newBooleanValue);
/*  96 */     this.notifier = notifier;
/*  97 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, byte oldByteValue, byte newByteValue, boolean isSetChange) {
/* 102 */     this(notifier, eventType, featureID, oldByteValue, newByteValue);
/* 103 */     if (isSetChange)
/*     */     {
/* 105 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, byte oldByteValue, byte newByteValue) {
/* 111 */     super(eventType, oldByteValue, newByteValue);
/* 112 */     this.notifier = notifier;
/* 113 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, char oldCharValue, char newCharValue, boolean isSetChange) {
/* 118 */     this(notifier, eventType, featureID, oldCharValue, newCharValue);
/* 119 */     if (isSetChange)
/*     */     {
/* 121 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, char oldCharValue, char newCharValue) {
/* 127 */     super(eventType, oldCharValue, newCharValue);
/* 128 */     this.notifier = notifier;
/* 129 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, double oldDoubleValue, double newDoubleValue, boolean isSetChange) {
/* 134 */     this(notifier, eventType, featureID, oldDoubleValue, newDoubleValue);
/* 135 */     if (isSetChange)
/*     */     {
/* 137 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, double oldDoubleValue, double newDoubleValue) {
/* 143 */     super(eventType, oldDoubleValue, newDoubleValue);
/* 144 */     this.notifier = notifier;
/* 145 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, float oldFloatValue, float newFloatValue, boolean isSetChange) {
/* 150 */     this(notifier, eventType, featureID, oldFloatValue, newFloatValue);
/* 151 */     if (isSetChange)
/*     */     {
/* 153 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, float oldFloatValue, float newFloatValue) {
/* 159 */     super(eventType, oldFloatValue, newFloatValue);
/* 160 */     this.notifier = notifier;
/* 161 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, int oldIntValue, int newIntValue, boolean isSetChange) {
/* 166 */     this(notifier, eventType, featureID, oldIntValue, newIntValue);
/* 167 */     if (isSetChange)
/*     */     {
/* 169 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, int oldIntValue, int newIntValue) {
/* 175 */     super(eventType, oldIntValue, newIntValue);
/* 176 */     this.notifier = notifier;
/* 177 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, long oldLongValue, long newLongValue, boolean isSetChange) {
/* 182 */     this(notifier, eventType, featureID, oldLongValue, newLongValue);
/* 183 */     if (isSetChange)
/*     */     {
/* 185 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, long oldLongValue, long newLongValue) {
/* 191 */     super(eventType, oldLongValue, newLongValue);
/* 192 */     this.notifier = notifier;
/* 193 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, short oldShortValue, short newShortValue, boolean isSetChange) {
/* 198 */     this(notifier, eventType, featureID, oldShortValue, newShortValue);
/* 199 */     if (isSetChange)
/*     */     {
/* 201 */       this.position = -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ENotificationImpl(InternalEObject notifier, int eventType, int featureID, short oldShortValue, short newShortValue) {
/* 207 */     super(eventType, oldShortValue, newShortValue);
/* 208 */     this.notifier = notifier;
/* 209 */     this.featureID = featureID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNotifier() {
/* 215 */     return this.notifier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFeature() {
/* 221 */     if (this.feature == null && this.featureID != -1) {
/*     */       
/* 223 */       EClass eClass = this.notifier.eClass();
/* 224 */       this.feature = eClass.getEStructuralFeature(this.featureID);
/*     */     } 
/* 226 */     return this.feature;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureID(Class<?> expectedClass) {
/* 232 */     if (this.featureID == -1 && this.feature != null)
/*     */     {
/* 234 */       this.featureID = this.notifier.eDerivedStructuralFeatureID(this.feature.getFeatureID(), this.feature.getContainerClass());
/*     */     }
/* 236 */     return this.notifier.eBaseStructuralFeatureID(this.featureID, expectedClass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getFeatureDefaultValue() {
/* 242 */     Object feature = getFeature();
/* 243 */     if (feature instanceof EStructuralFeature)
/*     */     {
/* 245 */       return ((EStructuralFeature)feature).getDefaultValue();
/*     */     }
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isFeatureUnsettable() {
/* 253 */     Object feature = getFeature();
/* 254 */     if (feature instanceof EStructuralFeature)
/*     */     {
/* 256 */       return ((EStructuralFeature)feature).isUnsettable();
/*     */     }
/* 258 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\ENotificationImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */