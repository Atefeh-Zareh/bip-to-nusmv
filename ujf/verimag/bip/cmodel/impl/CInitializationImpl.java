/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CInitParameter;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CInitializationImpl
/*     */   extends EObjectImpl
/*     */   implements CInitialization
/*     */ {
/*  53 */   protected static final String FIELD_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected String field = FIELD_EDEFAULT;
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
/*     */   protected EList<CInitParameter> parameter;
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
/*  91 */     return CmodelPackage.Literals.CINITIALIZATION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getField() {
/* 100 */     return this.field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setField(String newField) {
/* 109 */     String oldField = this.field;
/* 110 */     this.field = newField;
/* 111 */     if (eNotificationRequired()) {
/* 112 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldField, this.field));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CInitParameter> getParameter() {
/* 121 */     if (this.parameter == null) {
/* 122 */       this.parameter = (EList<CInitParameter>)new EObjectContainmentEList(CInitParameter.class, (InternalEObject)this, 1);
/*     */     }
/* 124 */     return this.parameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 134 */     switch (featureID) {
/*     */       case 1:
/* 136 */         return ((InternalEList)getParameter()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 138 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 148 */     switch (featureID) {
/*     */       case 0:
/* 150 */         return getField();
/*     */       case 1:
/* 152 */         return getParameter();
/*     */     } 
/* 154 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 165 */     switch (featureID) {
/*     */       case 0:
/* 167 */         setField((String)newValue);
/*     */         return;
/*     */       case 1:
/* 170 */         getParameter().clear();
/* 171 */         getParameter().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 174 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 184 */     switch (featureID) {
/*     */       case 0:
/* 186 */         setField(FIELD_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 189 */         getParameter().clear();
/*     */         return;
/*     */     } 
/* 192 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 202 */     switch (featureID) {
/*     */       case 0:
/* 204 */         return (FIELD_EDEFAULT == null) ? ((this.field != null)) : (!FIELD_EDEFAULT.equals(this.field));
/*     */       case 1:
/* 206 */         return (this.parameter != null && !this.parameter.isEmpty());
/*     */     } 
/* 208 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 218 */     if (eIsProxy()) return super.toString();
/*     */     
/* 220 */     StringBuffer result = new StringBuffer(super.toString());
/* 221 */     result.append(" (Field: ");
/* 222 */     result.append(this.field);
/* 223 */     result.append(')');
/* 224 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CInitializationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */