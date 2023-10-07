/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataTypedElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DataTypedElementImpl
/*     */   extends NamedElementImpl
/*     */   implements DataTypedElement
/*     */ {
/*     */   protected DataType type;
/*  54 */   protected static final String OPAQUE_TYPE_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected String opaqueTypeName = OPAQUE_TYPE_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  84 */     return BehaviorsPackage.Literals.DATA_TYPED_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataType getType() {
/*  94 */     if (this.type != null && this.type.eIsProxy()) {
/*     */       
/*  96 */       InternalEObject oldType = (InternalEObject)this.type;
/*  97 */       this.type = (DataType)eResolveProxy(oldType);
/*  98 */       if (this.type != oldType)
/*     */       {
/* 100 */         if (eNotificationRequired())
/* 101 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 2, oldType, this.type)); 
/*     */       }
/*     */     } 
/* 104 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataType basicGetType() {
/* 114 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(DataType newType) {
/* 124 */     DataType oldType = this.type;
/* 125 */     this.type = newType;
/* 126 */     if (eNotificationRequired()) {
/* 127 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldType, this.type));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOpaqueTypeName() {
/* 137 */     return this.opaqueTypeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpaqueTypeName(String newOpaqueTypeName) {
/* 147 */     String oldOpaqueTypeName = this.opaqueTypeName;
/* 148 */     this.opaqueTypeName = newOpaqueTypeName;
/* 149 */     if (eNotificationRequired()) {
/* 150 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldOpaqueTypeName, this.opaqueTypeName));
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
/* 161 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 164 */         if (resolve) return getType(); 
/* 165 */         return basicGetType();
/*     */       case 3:
/* 167 */         return getOpaqueTypeName();
/*     */     } 
/* 169 */     return super.eGet(featureID, resolve, coreType);
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
/* 180 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 183 */         setType((DataType)newValue);
/*     */         return;
/*     */       case 3:
/* 186 */         setOpaqueTypeName((String)newValue);
/*     */         return;
/*     */     } 
/* 189 */     super.eSet(featureID, newValue);
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
/* 200 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 203 */         setType((DataType)null);
/*     */         return;
/*     */       case 3:
/* 206 */         setOpaqueTypeName(OPAQUE_TYPE_NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 209 */     super.eUnset(featureID);
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
/* 220 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 223 */         return (this.type != null);
/*     */       case 3:
/* 225 */         return (OPAQUE_TYPE_NAME_EDEFAULT == null) ? ((this.opaqueTypeName != null)) : (!OPAQUE_TYPE_NAME_EDEFAULT.equals(this.opaqueTypeName));
/*     */     } 
/* 227 */     return super.eIsSet(featureID);
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
/* 238 */     if (eIsProxy()) return super.toString();
/*     */     
/* 240 */     StringBuffer result = new StringBuffer(super.toString());
/* 241 */     result.append(" (OpaqueTypeName: ");
/* 242 */     result.append(this.opaqueTypeName);
/* 243 */     result.append(')');
/* 244 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\DataTypedElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */