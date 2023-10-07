/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CInitParameter;
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
/*     */ public class CInitParameterImpl
/*     */   extends EObjectImpl
/*     */   implements CInitParameter
/*     */ {
/*  46 */   protected static final String FIELD_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   protected String fieldName = FIELD_NAME_EDEFAULT;
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
/*     */   protected CExpression value;
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
/*  84 */     return CmodelPackage.Literals.CINIT_PARAMETER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFieldName() {
/*  93 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFieldName(String newFieldName) {
/* 102 */     String oldFieldName = this.fieldName;
/* 103 */     this.fieldName = newFieldName;
/* 104 */     if (eNotificationRequired()) {
/* 105 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldFieldName, this.fieldName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getValue() {
/* 114 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetValue(CExpression newValue, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 123 */     CExpression oldValue = this.value;
/* 124 */     this.value = newValue;
/* 125 */     if (eNotificationRequired()) {
/* 126 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldValue, newValue);
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
/*     */   public void setValue(CExpression newValue) {
/* 138 */     if (newValue != this.value) {
/* 139 */       NotificationChain msgs = null;
/* 140 */       if (this.value != null)
/* 141 */         msgs = ((InternalEObject)this.value).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 142 */       if (newValue != null)
/* 143 */         msgs = ((InternalEObject)newValue).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 144 */       msgs = basicSetValue(newValue, msgs);
/* 145 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 147 */     } else if (eNotificationRequired()) {
/* 148 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newValue, newValue));
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
/* 160 */         return basicSetValue((CExpression)null, msgs);
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
/* 174 */         return getFieldName();
/*     */       case 1:
/* 176 */         return getValue();
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
/* 190 */         setFieldName((String)newValue);
/*     */         return;
/*     */       case 1:
/* 193 */         setValue((CExpression)newValue);
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
/* 208 */         setFieldName(FIELD_NAME_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 211 */         setValue((CExpression)null);
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
/* 226 */         return (FIELD_NAME_EDEFAULT == null) ? ((this.fieldName != null)) : (!FIELD_NAME_EDEFAULT.equals(this.fieldName));
/*     */       case 1:
/* 228 */         return (this.value != null);
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
/*     */   public String toString() {
/* 240 */     if (eIsProxy()) return super.toString();
/*     */     
/* 242 */     StringBuffer result = new StringBuffer(super.toString());
/* 243 */     result.append(" (fieldName: ");
/* 244 */     result.append(this.fieldName);
/* 245 */     result.append(')');
/* 246 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CInitParameterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */