/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*     */ import ujf.verimag.bip.cmodel.CTypedElement;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
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
/*     */ public class CTypeConvertionImpl
/*     */   extends CExpressionImpl
/*     */   implements CTypeConvertion
/*     */ {
/*  46 */   protected static final String TYPE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   protected String type = TYPE_EDEFAULT;
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
/*     */   protected CExpression convertedExpression;
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
/*     */   protected EClass eStaticClass() {
/*  84 */     return CmodelPackage.Literals.CTYPE_CONVERTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  93 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String newType) {
/* 102 */     String oldType = this.type;
/* 103 */     this.type = newType;
/* 104 */     if (eNotificationRequired()) {
/* 105 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getConvertedExpression() {
/* 114 */     return this.convertedExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetConvertedExpression(CExpression newConvertedExpression, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 123 */     CExpression oldConvertedExpression = this.convertedExpression;
/* 124 */     this.convertedExpression = newConvertedExpression;
/* 125 */     if (eNotificationRequired()) {
/* 126 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldConvertedExpression, newConvertedExpression);
/* 127 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 129 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConvertedExpression(CExpression newConvertedExpression) {
/* 138 */     if (newConvertedExpression != this.convertedExpression) {
/* 139 */       NotificationChain msgs = null;
/* 140 */       if (this.convertedExpression != null)
/* 141 */         msgs = ((InternalEObject)this.convertedExpression).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 142 */       if (newConvertedExpression != null)
/* 143 */         msgs = ((InternalEObject)newConvertedExpression).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 144 */       msgs = basicSetConvertedExpression(newConvertedExpression, msgs);
/* 145 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 147 */     } else if (eNotificationRequired()) {
/* 148 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newConvertedExpression, newConvertedExpression));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 158 */     switch (featureID) {
/*     */       case 1:
/* 160 */         return basicSetConvertedExpression((CExpression)null, msgs);
/*     */     } 
/* 162 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 172 */     switch (featureID) {
/*     */       case 0:
/* 174 */         return getType();
/*     */       case 1:
/* 176 */         return getConvertedExpression();
/*     */     } 
/* 178 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 188 */     switch (featureID) {
/*     */       case 0:
/* 190 */         setType((String)newValue);
/*     */         return;
/*     */       case 1:
/* 193 */         setConvertedExpression((CExpression)newValue);
/*     */         return;
/*     */     } 
/* 196 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 206 */     switch (featureID) {
/*     */       case 0:
/* 208 */         setType(TYPE_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 211 */         setConvertedExpression((CExpression)null);
/*     */         return;
/*     */     } 
/* 214 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 224 */     switch (featureID) {
/*     */       case 0:
/* 226 */         return (TYPE_EDEFAULT == null) ? ((this.type != null)) : (!TYPE_EDEFAULT.equals(this.type));
/*     */       case 1:
/* 228 */         return (this.convertedExpression != null);
/*     */     } 
/* 230 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
/* 240 */     if (baseClass == CTypedElement.class) {
/* 241 */       switch (derivedFeatureID) { case 0:
/* 242 */           return 0; }
/* 243 */        return -1;
/*     */     } 
/*     */     
/* 246 */     return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
/* 256 */     if (baseClass == CTypedElement.class) {
/* 257 */       switch (baseFeatureID) { case 0:
/* 258 */           return 0; }
/* 259 */        return -1;
/*     */     } 
/*     */     
/* 262 */     return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 272 */     if (eIsProxy()) return super.toString();
/*     */     
/* 274 */     StringBuffer result = new StringBuffer(super.toString());
/* 275 */     result.append(" (type: ");
/* 276 */     result.append(this.type);
/* 277 */     result.append(')');
/* 278 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CTypeConvertionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */