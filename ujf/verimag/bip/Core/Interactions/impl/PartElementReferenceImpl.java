/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PartElementReferenceImpl
/*     */   extends MultiplicityPathImpl
/*     */   implements PartElementReference
/*     */ {
/*     */   protected Part targetPart;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  68 */     return InteractionsPackage.Literals.PART_ELEMENT_REFERENCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InnerPortSpecification getExportBinding() {
/*  78 */     if (eContainerFeatureID() != 1) return null; 
/*  79 */     return (InnerPortSpecification)eContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetExportBinding(InnerPortSpecification newExportBinding, NotificationChain msgs) {
/*  89 */     msgs = eBasicSetContainer((InternalEObject)newExportBinding, 1, msgs);
/*  90 */     return msgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExportBinding(InnerPortSpecification newExportBinding) {
/* 100 */     if (newExportBinding != eInternalContainer() || (eContainerFeatureID() != 1 && newExportBinding != null)) {
/*     */       
/* 102 */       if (EcoreUtil.isAncestor((EObject)this, (EObject)newExportBinding))
/* 103 */         throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); 
/* 104 */       NotificationChain msgs = null;
/* 105 */       if (eInternalContainer() != null)
/* 106 */         msgs = eBasicRemoveFromContainer(msgs); 
/* 107 */       if (newExportBinding != null)
/* 108 */         msgs = ((InternalEObject)newExportBinding).eInverseAdd((InternalEObject)this, 1, InnerPortSpecification.class, msgs); 
/* 109 */       msgs = basicSetExportBinding(newExportBinding, msgs);
/* 110 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 112 */     } else if (eNotificationRequired()) {
/* 113 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newExportBinding, newExportBinding));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Part getTargetPart() {
/* 123 */     if (this.targetPart != null && this.targetPart.eIsProxy()) {
/*     */       
/* 125 */       InternalEObject oldTargetPart = (InternalEObject)this.targetPart;
/* 126 */       this.targetPart = (Part)eResolveProxy(oldTargetPart);
/* 127 */       if (this.targetPart != oldTargetPart)
/*     */       {
/* 129 */         if (eNotificationRequired())
/* 130 */           eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 2, oldTargetPart, this.targetPart)); 
/*     */       }
/*     */     } 
/* 133 */     return this.targetPart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Part basicGetTargetPart() {
/* 143 */     return this.targetPart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetPart(Part newTargetPart) {
/* 153 */     Part oldTargetPart = this.targetPart;
/* 154 */     this.targetPart = newTargetPart;
/* 155 */     if (eNotificationRequired()) {
/* 156 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldTargetPart, this.targetPart));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 167 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 170 */         if (eInternalContainer() != null)
/* 171 */           msgs = eBasicRemoveFromContainer(msgs); 
/* 172 */         return basicSetExportBinding((InnerPortSpecification)otherEnd, msgs);
/*     */     } 
/* 174 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 185 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 188 */         return basicSetExportBinding((InnerPortSpecification)null, msgs);
/*     */     } 
/* 190 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 201 */     switch (eContainerFeatureID()) {
/*     */       
/*     */       case 1:
/* 204 */         return eInternalContainer().eInverseRemove((InternalEObject)this, 1, InnerPortSpecification.class, msgs);
/*     */     } 
/* 206 */     return super.eBasicRemoveFromContainerFeature(msgs);
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
/* 217 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 220 */         return getExportBinding();
/*     */       case 2:
/* 222 */         if (resolve) return getTargetPart(); 
/* 223 */         return basicGetTargetPart();
/*     */     } 
/* 225 */     return super.eGet(featureID, resolve, coreType);
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
/* 236 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 239 */         setExportBinding((InnerPortSpecification)newValue);
/*     */         return;
/*     */       case 2:
/* 242 */         setTargetPart((Part)newValue);
/*     */         return;
/*     */     } 
/* 245 */     super.eSet(featureID, newValue);
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
/* 256 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 259 */         setExportBinding((InnerPortSpecification)null);
/*     */         return;
/*     */       case 2:
/* 262 */         setTargetPart((Part)null);
/*     */         return;
/*     */     } 
/* 265 */     super.eUnset(featureID);
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
/* 276 */     switch (featureID) {
/*     */       
/*     */       case 1:
/* 279 */         return (getExportBinding() != null);
/*     */       case 2:
/* 281 */         return (this.targetPart != null);
/*     */     } 
/* 283 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\PartElementReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */