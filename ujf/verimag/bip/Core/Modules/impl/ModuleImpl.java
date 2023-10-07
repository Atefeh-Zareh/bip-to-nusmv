/*     */ package ujf.verimag.bip.Core.Modules.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.NamedElementImpl;
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ModuleImpl
/*     */   extends NamedElementImpl
/*     */   implements Module
/*     */ {
/*     */   protected EList<BipType> bipType;
/*     */   protected EList<Package> usedPackage;
/*     */   protected EList<Declaration> declaration;
/*     */   protected EList<DataType> dataType;
/* 103 */   protected static final String SRC_FILE_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   protected String srcFileName = SRC_FILE_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 133 */     return ModulesPackage.Literals.MODULE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<BipType> getBipType() {
/* 143 */     if (this.bipType == null)
/*     */     {
/* 145 */       this.bipType = (EList<BipType>)new EObjectContainmentWithInverseEList(BipType.class, (InternalEObject)this, 2, 4);
/*     */     }
/* 147 */     return this.bipType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Package> getUsedPackage() {
/* 157 */     if (this.usedPackage == null)
/*     */     {
/* 159 */       this.usedPackage = (EList<Package>)new EObjectResolvingEList(Package.class, (InternalEObject)this, 3);
/*     */     }
/* 161 */     return this.usedPackage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Declaration> getDeclaration() {
/* 171 */     if (this.declaration == null)
/*     */     {
/* 173 */       this.declaration = (EList<Declaration>)new EObjectContainmentEList(Declaration.class, (InternalEObject)this, 4);
/*     */     }
/* 175 */     return this.declaration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<DataType> getDataType() {
/* 185 */     if (this.dataType == null)
/*     */     {
/* 187 */       this.dataType = (EList<DataType>)new EObjectContainmentEList(DataType.class, (InternalEObject)this, 5);
/*     */     }
/* 189 */     return this.dataType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSrcFileName() {
/* 199 */     return this.srcFileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSrcFileName(String newSrcFileName) {
/* 209 */     String oldSrcFileName = this.srcFileName;
/* 210 */     this.srcFileName = newSrcFileName;
/* 211 */     if (eNotificationRequired()) {
/* 212 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, oldSrcFileName, this.srcFileName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 224 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 227 */         return ((InternalEList)getBipType()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 229 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 240 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 243 */         return ((InternalEList)getBipType()).basicRemove(otherEnd, msgs);
/*     */       case 4:
/* 245 */         return ((InternalEList)getDeclaration()).basicRemove(otherEnd, msgs);
/*     */       case 5:
/* 247 */         return ((InternalEList)getDataType()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 249 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 260 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 263 */         return getBipType();
/*     */       case 3:
/* 265 */         return getUsedPackage();
/*     */       case 4:
/* 267 */         return getDeclaration();
/*     */       case 5:
/* 269 */         return getDataType();
/*     */       case 6:
/* 271 */         return getSrcFileName();
/*     */     } 
/* 273 */     return super.eGet(featureID, resolve, coreType);
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
/* 285 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 288 */         getBipType().clear();
/* 289 */         getBipType().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 292 */         getUsedPackage().clear();
/* 293 */         getUsedPackage().addAll((Collection)newValue);
/*     */         return;
/*     */       case 4:
/* 296 */         getDeclaration().clear();
/* 297 */         getDeclaration().addAll((Collection)newValue);
/*     */         return;
/*     */       case 5:
/* 300 */         getDataType().clear();
/* 301 */         getDataType().addAll((Collection)newValue);
/*     */         return;
/*     */       case 6:
/* 304 */         setSrcFileName((String)newValue);
/*     */         return;
/*     */     } 
/* 307 */     super.eSet(featureID, newValue);
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
/* 318 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 321 */         getBipType().clear();
/*     */         return;
/*     */       case 3:
/* 324 */         getUsedPackage().clear();
/*     */         return;
/*     */       case 4:
/* 327 */         getDeclaration().clear();
/*     */         return;
/*     */       case 5:
/* 330 */         getDataType().clear();
/*     */         return;
/*     */       case 6:
/* 333 */         setSrcFileName(SRC_FILE_NAME_EDEFAULT);
/*     */         return;
/*     */     } 
/* 336 */     super.eUnset(featureID);
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
/* 347 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 350 */         return (this.bipType != null && !this.bipType.isEmpty());
/*     */       case 3:
/* 352 */         return (this.usedPackage != null && !this.usedPackage.isEmpty());
/*     */       case 4:
/* 354 */         return (this.declaration != null && !this.declaration.isEmpty());
/*     */       case 5:
/* 356 */         return (this.dataType != null && !this.dataType.isEmpty());
/*     */       case 6:
/* 358 */         return (SRC_FILE_NAME_EDEFAULT == null) ? ((this.srcFileName != null)) : (!SRC_FILE_NAME_EDEFAULT.equals(this.srcFileName));
/*     */     } 
/* 360 */     return super.eIsSet(featureID);
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
/* 371 */     if (eIsProxy()) return super.toString();
/*     */     
/* 373 */     StringBuffer result = new StringBuffer(super.toString());
/* 374 */     result.append(" (srcFileName: ");
/* 375 */     result.append(this.srcFileName);
/* 376 */     result.append(')');
/* 377 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\impl\ModuleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */