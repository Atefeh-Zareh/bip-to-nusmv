/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ActionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeActionImpl
/*     */   extends ActionImpl
/*     */   implements CompositeAction
/*     */ {
/*     */   protected EList<Action> content;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  71 */     return ActionsPackage.Literals.COMPOSITE_ACTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Action> getContent() {
/*  81 */     if (this.content == null)
/*     */     {
/*  83 */       this.content = (EList<Action>)new EObjectContainmentEList(Action.class, (InternalEObject)this, 0);
/*     */     }
/*  85 */     return this.content;
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
/*  96 */     switch (featureID) {
/*     */       
/*     */       case 0:
/*  99 */         return ((InternalEList)getContent()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 101 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 112 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 115 */         return getContent();
/*     */     } 
/* 117 */     return super.eGet(featureID, resolve, coreType);
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
/* 129 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 132 */         getContent().clear();
/* 133 */         getContent().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 136 */     super.eSet(featureID, newValue);
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
/* 147 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 150 */         getContent().clear();
/*     */         return;
/*     */     } 
/* 153 */     super.eUnset(featureID);
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
/* 164 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 167 */         return (this.content != null && !this.content.isEmpty());
/*     */     } 
/* 169 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\impl\CompositeActionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */