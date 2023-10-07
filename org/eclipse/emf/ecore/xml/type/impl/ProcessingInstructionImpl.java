/*     */ package org.eclipse.emf.ecore.xml.type.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*     */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
/*     */ public class ProcessingInstructionImpl
/*     */   extends EObjectImpl
/*     */   implements ProcessingInstruction
/*     */ {
/*  53 */   protected static final String DATA_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected String data = DATA_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected static final String TARGET_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   protected String target = TARGET_EDEFAULT;
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
/*     */   protected EClass eStaticClass() {
/* 103 */     return XMLTypePackage.Literals.PROCESSING_INSTRUCTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getData() {
/* 113 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String newData) {
/* 123 */     String oldData = this.data;
/* 124 */     this.data = newData;
/* 125 */     if (eNotificationRequired()) {
/* 126 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldData, this.data));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTarget() {
/* 136 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(String newTarget) {
/* 146 */     String oldTarget = this.target;
/* 147 */     this.target = newTarget;
/* 148 */     if (eNotificationRequired()) {
/* 149 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldTarget, this.target));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 160 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 163 */         return getData();
/*     */       case 1:
/* 165 */         return getTarget();
/*     */     } 
/* 167 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 178 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 181 */         setData((String)newValue);
/*     */         return;
/*     */       case 1:
/* 184 */         setTarget((String)newValue);
/*     */         return;
/*     */     } 
/* 187 */     eDynamicSet(featureID, newValue);
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
/* 198 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 201 */         setData(DATA_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 204 */         setTarget(TARGET_EDEFAULT);
/*     */         return;
/*     */     } 
/* 207 */     eDynamicUnset(featureID);
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
/* 218 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 221 */         return (DATA_EDEFAULT == null) ? ((this.data != null)) : (!DATA_EDEFAULT.equals(this.data));
/*     */       case 1:
/* 223 */         return (TARGET_EDEFAULT == null) ? ((this.target != null)) : (!TARGET_EDEFAULT.equals(this.target));
/*     */     } 
/* 225 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 236 */     if (eIsProxy()) return super.toString();
/*     */     
/* 238 */     StringBuffer result = new StringBuffer(super.toString());
/* 239 */     result.append(" (data: ");
/* 240 */     result.append(this.data);
/* 241 */     result.append(", target: ");
/* 242 */     result.append(this.target);
/* 243 */     result.append(')');
/* 244 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\impl\ProcessingInstructionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */