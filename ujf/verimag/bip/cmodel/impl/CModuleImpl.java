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
/*     */ import ujf.verimag.bip.cmodel.CInclude;
/*     */ import ujf.verimag.bip.cmodel.CItem;
/*     */ import ujf.verimag.bip.cmodel.CModule;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CModuleImpl
/*     */   extends EObjectImpl
/*     */   implements CModule
/*     */ {
/*     */   protected EList<CInclude> cImport;
/*     */   protected EList<CItem> content;
/*     */   protected static final String NAMESPACE_EDEFAULT = "";
/*  85 */   protected String namespace = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 103 */     return CmodelPackage.Literals.CMODULE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CInclude> getCImport() {
/* 112 */     if (this.cImport == null) {
/* 113 */       this.cImport = (EList<CInclude>)new EObjectContainmentEList(CInclude.class, (InternalEObject)this, 0);
/*     */     }
/* 115 */     return this.cImport;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CItem> getContent() {
/* 124 */     if (this.content == null) {
/* 125 */       this.content = (EList<CItem>)new EObjectContainmentEList(CItem.class, (InternalEObject)this, 1);
/*     */     }
/* 127 */     return this.content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespace() {
/* 136 */     return this.namespace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNamespace(String newNamespace) {
/* 145 */     String oldNamespace = this.namespace;
/* 146 */     this.namespace = newNamespace;
/* 147 */     if (eNotificationRequired()) {
/* 148 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldNamespace, this.namespace));
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
/*     */       case 0:
/* 160 */         return ((InternalEList)getCImport()).basicRemove(otherEnd, msgs);
/*     */       case 1:
/* 162 */         return ((InternalEList)getContent()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 164 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 174 */     switch (featureID) {
/*     */       case 0:
/* 176 */         return getCImport();
/*     */       case 1:
/* 178 */         return getContent();
/*     */       case 2:
/* 180 */         return getNamespace();
/*     */     } 
/* 182 */     return super.eGet(featureID, resolve, coreType);
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
/* 193 */     switch (featureID) {
/*     */       case 0:
/* 195 */         getCImport().clear();
/* 196 */         getCImport().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 199 */         getContent().clear();
/* 200 */         getContent().addAll((Collection)newValue);
/*     */         return;
/*     */       case 2:
/* 203 */         setNamespace((String)newValue);
/*     */         return;
/*     */     } 
/* 206 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 216 */     switch (featureID) {
/*     */       case 0:
/* 218 */         getCImport().clear();
/*     */         return;
/*     */       case 1:
/* 221 */         getContent().clear();
/*     */         return;
/*     */       case 2:
/* 224 */         setNamespace("");
/*     */         return;
/*     */     } 
/* 227 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 237 */     switch (featureID) {
/*     */       case 0:
/* 239 */         return (this.cImport != null && !this.cImport.isEmpty());
/*     */       case 1:
/* 241 */         return (this.content != null && !this.content.isEmpty());
/*     */       case 2:
/* 243 */         return ("" == null) ? ((this.namespace != null)) : (!"".equals(this.namespace));
/*     */     } 
/* 245 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 255 */     if (eIsProxy()) return super.toString();
/*     */     
/* 257 */     StringBuffer result = new StringBuffer(super.toString());
/* 258 */     result.append(" (namespace: ");
/* 259 */     result.append(this.namespace);
/* 260 */     result.append(')');
/* 261 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CModuleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */