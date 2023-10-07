/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.AbstractTransition;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.TransitionAlternative;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateImpl
/*     */   extends NamedElementImpl
/*     */   implements State
/*     */ {
/*     */   protected EList<Transition> incoming;
/*     */   protected EList<TransitionAlternative> alternativeIncoming;
/*     */   protected EList<AbstractTransition> outgoing;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  92 */     return BehaviorsPackage.Literals.STATE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Transition> getIncoming() {
/* 102 */     if (this.incoming == null)
/*     */     {
/* 104 */       this.incoming = (EList<Transition>)new EObjectWithInverseResolvingEList.ManyInverse(Transition.class, (InternalEObject)this, 2, 8);
/*     */     }
/* 106 */     return this.incoming;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<TransitionAlternative> getAlternativeIncoming() {
/* 116 */     if (this.alternativeIncoming == null)
/*     */     {
/* 118 */       this.alternativeIncoming = (EList<TransitionAlternative>)new EObjectWithInverseResolvingEList.ManyInverse(TransitionAlternative.class, (InternalEObject)this, 3, 1);
/*     */     }
/* 120 */     return this.alternativeIncoming;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<AbstractTransition> getOutgoing() {
/* 130 */     if (this.outgoing == null)
/*     */     {
/* 132 */       this.outgoing = (EList<AbstractTransition>)new EObjectWithInverseResolvingEList.ManyInverse(AbstractTransition.class, (InternalEObject)this, 4, 2);
/*     */     }
/* 134 */     return this.outgoing;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 146 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 149 */         return ((InternalEList)getIncoming()).basicAdd(otherEnd, msgs);
/*     */       case 3:
/* 151 */         return ((InternalEList)getAlternativeIncoming()).basicAdd(otherEnd, msgs);
/*     */       case 4:
/* 153 */         return ((InternalEList)getOutgoing()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 155 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 166 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 169 */         return ((InternalEList)getIncoming()).basicRemove(otherEnd, msgs);
/*     */       case 3:
/* 171 */         return ((InternalEList)getAlternativeIncoming()).basicRemove(otherEnd, msgs);
/*     */       case 4:
/* 173 */         return ((InternalEList)getOutgoing()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 175 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 186 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 189 */         return getIncoming();
/*     */       case 3:
/* 191 */         return getAlternativeIncoming();
/*     */       case 4:
/* 193 */         return getOutgoing();
/*     */     } 
/* 195 */     return super.eGet(featureID, resolve, coreType);
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
/* 207 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 210 */         getIncoming().clear();
/* 211 */         getIncoming().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 214 */         getAlternativeIncoming().clear();
/* 215 */         getAlternativeIncoming().addAll((Collection)newValue);
/*     */         return;
/*     */       case 4:
/* 218 */         getOutgoing().clear();
/* 219 */         getOutgoing().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 222 */     super.eSet(featureID, newValue);
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
/* 233 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 236 */         getIncoming().clear();
/*     */         return;
/*     */       case 3:
/* 239 */         getAlternativeIncoming().clear();
/*     */         return;
/*     */       case 4:
/* 242 */         getOutgoing().clear();
/*     */         return;
/*     */     } 
/* 245 */     super.eUnset(featureID);
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
/* 256 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 259 */         return (this.incoming != null && !this.incoming.isEmpty());
/*     */       case 3:
/* 261 */         return (this.alternativeIncoming != null && !this.alternativeIncoming.isEmpty());
/*     */       case 4:
/* 263 */         return (this.outgoing != null && !this.outgoing.isEmpty());
/*     */     } 
/* 265 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\StateImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */