/*     */ package org.eclipse.emf.common.notify.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingletonAdapterImpl
/*     */   implements Adapter.Internal
/*     */ {
/*     */   protected List<Notifier> targets;
/*     */   
/*     */   public boolean isAdapterForType(Object type) {
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyChanged(Notification msg) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Notifier getTarget() {
/*  69 */     return (this.targets == null || this.targets.isEmpty()) ? null : this.targets.get(this.targets.size() - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(Notifier target) {
/*  74 */     if (this.targets == null)
/*     */     {
/*  76 */       this.targets = new ArrayList<Notifier>();
/*     */     }
/*  78 */     this.targets.add(target);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unsetTarget(Notifier target) {
/*  83 */     if (this.targets != null)
/*     */     {
/*  85 */       this.targets.remove(target);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/*  94 */     List<Notifier> oldTargets = this.targets;
/*  95 */     this.targets = null;
/*     */     
/*  97 */     if (oldTargets != null)
/*     */     {
/*  99 */       for (Notifier notifier : oldTargets)
/*     */       {
/* 101 */         notifier.eAdapters().remove(this);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\SingletonAdapterImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */