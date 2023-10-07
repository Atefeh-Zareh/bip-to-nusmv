/*     */ package org.eclipse.emf.common.notify.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NotificationChainImpl
/*     */   extends BasicEList<Notification>
/*     */   implements NotificationChain
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public NotificationChainImpl() {}
/*     */   
/*     */   public NotificationChainImpl(int initialCapacity) {
/*  47 */     super(initialCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] newData(int capacity) {
/*  57 */     return (Object[])new Notification[capacity];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(Notification newNotification) {
/*  68 */     if (newNotification == null)
/*     */     {
/*  70 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  74 */     for (int i = 0; i < this.size; i++) {
/*     */       
/*  76 */       Notification notification = (Notification)this.data[i];
/*  77 */       if (notification.merge(newNotification))
/*     */       {
/*  79 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  83 */     return super.add(newNotification);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatch() {
/*  89 */     for (int i = 0; i < this.size; i++) {
/*     */       
/*  91 */       Notification notification = (Notification)this.data[i];
/*  92 */       dispatch(notification);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatch(Notification notification) {
/* 101 */     Object notifier = notification.getNotifier();
/* 102 */     if (notifier != null && notification.getEventType() != -1)
/*     */     {
/* 104 */       ((Notifier)notifier).eNotify(notification);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\NotificationChainImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */