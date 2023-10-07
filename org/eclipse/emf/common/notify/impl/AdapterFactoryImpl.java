/*     */ package org.eclipse.emf.common.notify.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.AdapterFactory;
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
/*     */ public class AdapterFactoryImpl
/*     */   implements AdapterFactory
/*     */ {
/*     */   public boolean isFactoryForType(Object type) {
/*  45 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object adapt(Object target, Object type) {
/*  61 */     if (target instanceof Notifier)
/*     */     {
/*  63 */       return adapt((Notifier)target, type);
/*     */     }
/*     */ 
/*     */     
/*  67 */     return resolve(target, type);
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
/*     */ 
/*     */   
/*     */   protected Object resolve(Object object, Object type) {
/*  81 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   public Adapter adapt(Notifier target, Object type) {
/*  86 */     for (Adapter adapter : target.eAdapters()) {
/*     */       
/*  88 */       if (adapter.isAdapterForType(type))
/*     */       {
/*  90 */         return adapter;
/*     */       }
/*     */     } 
/*  93 */     return adaptNew(target, type);
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
/*     */ 
/*     */   
/*     */   public Adapter adaptNew(Notifier target, Object type) {
/* 107 */     Adapter adapter = createAdapter(target, type);
/* 108 */     associate(adapter, target);
/* 109 */     return adapter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adaptAllNew(Notifier target) {
/* 120 */     Adapter adapter = createAdapter(target);
/* 121 */     associate(adapter, target);
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
/*     */   protected Adapter createAdapter(Notifier target, Object type) {
/* 133 */     return createAdapter(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Adapter createAdapter(Notifier target) {
/* 144 */     return (Adapter)new AdapterImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void associate(Adapter adapter, Notifier target) {
/* 154 */     if (adapter != null)
/*     */     {
/* 156 */       target.eAdapters().add(adapter);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\AdapterFactoryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */