/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.Enumerator;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EEnumLiteral;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
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
/*     */ public class EEnumLiteralImpl
/*     */   extends ENamedElementImpl
/*     */   implements EEnumLiteral
/*     */ {
/*     */   protected static final int VALUE_EDEFAULT = 0;
/*  70 */   protected int value = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected static final Enumerator INSTANCE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   protected Enumerator instance = INSTANCE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   protected static final String LITERAL_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected String literal = LITERAL_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Enumerator generatedInstance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/* 130 */     return EcorePackage.Literals.EENUM_LITERAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/* 140 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(int newValue) {
/* 150 */     int oldValue = this.value;
/* 151 */     this.value = newValue;
/* 152 */     if (eNotificationRequired()) {
/* 153 */       eNotify((Notification)new ENotificationImpl(this, 1, 2, oldValue, this.value));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumerator getInstance() {
/* 163 */     return (this.instance != null) ? this.instance : this.generatedInstance;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EEnumLiteralImpl() {
/* 169 */     this.generatedInstance = (Enumerator)this;
/*     */   }
/*     */   
/*     */   public void setGeneratedInstance(boolean isGenerated) {
/* 173 */     if (isGenerated) {
/*     */       
/* 175 */       if (this.generatedInstance == this)
/*     */       {
/* 177 */         this.generatedInstance = this.instance;
/* 178 */         this.instance = null;
/*     */       }
/*     */     
/* 181 */     } else if (this.generatedInstance != this) {
/*     */       
/* 183 */       this.instance = this.generatedInstance;
/* 184 */       this.generatedInstance = (Enumerator)this;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstanceGen(Enumerator newInstance) {
/* 195 */     Enumerator oldInstance = this.instance;
/* 196 */     this.instance = newInstance;
/* 197 */     if (eNotificationRequired()) {
/* 198 */       eNotify((Notification)new ENotificationImpl(this, 1, 3, oldInstance, this.instance));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setInstance(Enumerator newInstance) {
/* 203 */     setInstanceGen(newInstance);
/* 204 */     if (newInstance == null) {
/*     */       
/* 206 */       setName((String)null);
/* 207 */       setValue(0);
/* 208 */       setLiteral((String)null);
/*     */     }
/* 210 */     else if (newInstance != this) {
/*     */       
/* 212 */       setName(newInstance.getName());
/* 213 */       setValue(newInstance.getValue());
/* 214 */       String literal = newInstance.getLiteral();
/* 215 */       setLiteral((literal == null || literal.equals(newInstance.getName())) ? null : literal);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLiteralGen() {
/* 226 */     return this.literal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLiteral() {
/* 231 */     String result = getLiteralGen();
/* 232 */     return (result == null) ? getName() : result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLiteral(String newLiteral) {
/* 242 */     String oldLiteral = this.literal;
/* 243 */     this.literal = newLiteral;
/* 244 */     if (eNotificationRequired()) {
/* 245 */       eNotify((Notification)new ENotificationImpl(this, 1, 4, oldLiteral, this.literal));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EEnum getEEnum() {
/* 255 */     return (eContainerFeatureID() == 5) ? (EEnum)this.eContainer : null;
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
/* 267 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 270 */         return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
/*     */       case 5:
/* 272 */         if (eInternalContainer() != null)
/* 273 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 274 */         return eBasicSetContainer(otherEnd, 5, msgs);
/*     */     } 
/* 276 */     return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
/* 287 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 290 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 5:
/* 292 */         return eBasicSetContainer((InternalEObject)null, 5, msgs);
/*     */     } 
/* 294 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
/* 305 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 5:
/* 308 */         return eInternalContainer().eInverseRemove(this, 9, EEnum.class, msgs);
/*     */     } 
/* 310 */     return eDynamicBasicRemoveFromContainer(msgs);
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
/* 321 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 324 */         return getEAnnotations();
/*     */       case 1:
/* 326 */         return getName();
/*     */       case 2:
/* 328 */         return Integer.valueOf(getValue());
/*     */       case 3:
/* 330 */         return getInstance();
/*     */       case 4:
/* 332 */         return getLiteral();
/*     */       case 5:
/* 334 */         return getEEnum();
/*     */     } 
/* 336 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 348 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 351 */         getEAnnotations().clear();
/* 352 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 355 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 358 */         setValue(((Integer)newValue).intValue());
/*     */         return;
/*     */       case 3:
/* 361 */         setInstance((Enumerator)newValue);
/*     */         return;
/*     */       case 4:
/* 364 */         setLiteral((String)newValue);
/*     */         return;
/*     */     } 
/* 367 */     eDynamicSet(featureID, newValue);
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
/* 378 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 381 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 384 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 387 */         setValue(0);
/*     */         return;
/*     */       case 3:
/* 390 */         setInstance(INSTANCE_EDEFAULT);
/*     */         return;
/*     */       case 4:
/* 393 */         setLiteral(LITERAL_EDEFAULT);
/*     */         return;
/*     */     } 
/* 396 */     eDynamicUnset(featureID);
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
/* 407 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 410 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 412 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 414 */         return (this.value != 0);
/*     */       case 3:
/* 416 */         return (INSTANCE_EDEFAULT == null) ? ((this.instance != null)) : (!INSTANCE_EDEFAULT.equals(this.instance));
/*     */       case 4:
/* 418 */         return (LITERAL_EDEFAULT == null) ? ((this.literal != null)) : (!LITERAL_EDEFAULT.equals(this.literal));
/*     */       case 5:
/* 420 */         return (getEEnum() != null);
/*     */     } 
/* 422 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 428 */     return getLiteral();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringGen() {
/* 438 */     if (eIsProxy()) return super.toString();
/*     */     
/* 440 */     StringBuffer result = new StringBuffer(super.toString());
/* 441 */     result.append(" (value: ");
/* 442 */     result.append(this.value);
/* 443 */     result.append(", instance: ");
/* 444 */     result.append(this.instance);
/* 445 */     result.append(", literal: ");
/* 446 */     result.append(this.literal);
/* 447 */     result.append(')');
/* 448 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EEnumLiteralImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */