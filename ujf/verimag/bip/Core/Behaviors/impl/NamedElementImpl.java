/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NamedElementImpl
/*     */   extends EObjectImpl
/*     */   implements NamedElement
/*     */ {
/*  43 */   protected static final String NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected String name = NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected static final String SCOPE_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected String scope = SCOPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  93 */     return BehaviorsPackage.Literals.NAMED_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 103 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/* 113 */     String oldName = this.name;
/* 114 */     this.name = newName;
/* 115 */     if (eNotificationRequired()) {
/* 116 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldName, this.name));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScope() {
/* 126 */     return this.scope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScope(String newScope) {
/* 136 */     String oldScope = this.scope;
/* 137 */     this.scope = newScope;
/* 138 */     if (eNotificationRequired()) {
/* 139 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldScope, this.scope));
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
/* 150 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 153 */         return getName();
/*     */       case 1:
/* 155 */         return getScope();
/*     */     } 
/* 157 */     return super.eGet(featureID, resolve, coreType);
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
/* 168 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 171 */         setName((String)newValue);
/*     */         return;
/*     */       case 1:
/* 174 */         setScope((String)newValue);
/*     */         return;
/*     */     } 
/* 177 */     super.eSet(featureID, newValue);
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
/* 188 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 191 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 194 */         setScope(SCOPE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 197 */     super.eUnset(featureID);
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
/* 208 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 211 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 1:
/* 213 */         return (SCOPE_EDEFAULT == null) ? ((this.scope != null)) : (!SCOPE_EDEFAULT.equals(this.scope));
/*     */     } 
/* 215 */     return super.eIsSet(featureID);
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
/* 226 */     if (eIsProxy()) return super.toString();
/*     */     
/* 228 */     StringBuffer result = new StringBuffer(super.toString());
/* 229 */     result.append(" (name: ");
/* 230 */     result.append(this.name);
/* 231 */     result.append(", scope: ");
/* 232 */     result.append(this.scope);
/* 233 */     result.append(')');
/* 234 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\NamedElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */