/*     */ package ujf.verimag.bip.Core.Modules.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ActionImpl;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpaqueElementImpl
/*     */   extends ActionImpl
/*     */   implements OpaqueElement
/*     */ {
/*  44 */   protected static final String BODY_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected String body = BODY_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean IS_HEADER_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isHeader = false;
/*     */ 
/*     */ 
/*     */ 
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
/*  94 */     return ModulesPackage.Literals.OPAQUE_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBody() {
/* 104 */     return this.body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBody(String newBody) {
/* 114 */     String oldBody = this.body;
/* 115 */     this.body = newBody;
/* 116 */     if (eNotificationRequired()) {
/* 117 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldBody, this.body));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsHeader() {
/* 127 */     return this.isHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsHeader(boolean newIsHeader) {
/* 137 */     boolean oldIsHeader = this.isHeader;
/* 138 */     this.isHeader = newIsHeader;
/* 139 */     if (eNotificationRequired()) {
/* 140 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldIsHeader, this.isHeader));
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
/* 151 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 154 */         return getBody();
/*     */       case 1:
/* 156 */         return Boolean.valueOf(isIsHeader());
/*     */     } 
/* 158 */     return super.eGet(featureID, resolve, coreType);
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
/* 169 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 172 */         setBody((String)newValue);
/*     */         return;
/*     */       case 1:
/* 175 */         setIsHeader(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */     } 
/* 178 */     super.eSet(featureID, newValue);
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
/* 189 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 192 */         setBody(BODY_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 195 */         setIsHeader(false);
/*     */         return;
/*     */     } 
/* 198 */     super.eUnset(featureID);
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
/* 209 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 212 */         return (BODY_EDEFAULT == null) ? ((this.body != null)) : (!BODY_EDEFAULT.equals(this.body));
/*     */       case 1:
/* 214 */         return this.isHeader;
/*     */     } 
/* 216 */     return super.eIsSet(featureID);
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
/* 227 */     if (eIsProxy()) return super.toString();
/*     */     
/* 229 */     StringBuffer result = new StringBuffer(super.toString());
/* 230 */     result.append(" (body: ");
/* 231 */     result.append(this.body);
/* 232 */     result.append(", isHeader: ");
/* 233 */     result.append(this.isHeader);
/* 234 */     result.append(')');
/* 235 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\impl\OpaqueElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */