/*     */ package org.eclipse.emf.common.notify.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.util.BasicEList;
/*     */ import org.eclipse.emf.common.util.ECollections;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicNotifierImpl
/*     */   implements Notifier
/*     */ {
/*     */   public static class EAdapterList<E extends Adapter>
/*     */     extends BasicEList<E>
/*     */     implements EObservableAdapterList
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     protected Notifier notifier;
/*     */     protected BasicNotifierImpl.EObservableAdapterList.Listener[] listeners;
/*     */     protected boolean safe;
/*     */     
/*     */     public EAdapterList(Notifier notifier) {
/*  94 */       this.notifier = notifier;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean canContainNull() {
/* 102 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean useEquals() {
/* 108 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object[] newData(int capacity) {
/* 114 */       return (Object[])new Adapter[capacity];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didAdd(int index, E newObject) {
/* 120 */       if (this.listeners != null) {
/*     */         byte b; int i; BasicNotifierImpl.EObservableAdapterList.Listener[] arrayOfListener;
/* 122 */         for (i = (arrayOfListener = this.listeners).length, b = 0; b < i; ) { BasicNotifierImpl.EObservableAdapterList.Listener listener = arrayOfListener[b];
/*     */           
/* 124 */           listener.added(this.notifier, (Adapter)newObject); b++; }
/*     */       
/*     */       } 
/* 127 */       ((Adapter)newObject).setTarget(this.notifier);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void didRemove(int index, E oldObject) {
/* 133 */       if (this.listeners != null) {
/*     */         byte b; int i; BasicNotifierImpl.EObservableAdapterList.Listener[] arrayOfListener;
/* 135 */         for (i = (arrayOfListener = this.listeners).length, b = 0; b < i; ) { BasicNotifierImpl.EObservableAdapterList.Listener listener = arrayOfListener[b];
/*     */           
/* 137 */           listener.removed(this.notifier, (Adapter)oldObject); b++; }
/*     */       
/*     */       } 
/* 140 */       E adapter = oldObject;
/* 141 */       if (this.notifier.eDeliver()) {
/*     */         
/* 143 */         Notification notification = 
/* 144 */           new NotificationImpl(8, oldObject, null, index)
/*     */           {
/*     */             
/*     */             public Object getNotifier()
/*     */             {
/* 149 */               return BasicNotifierImpl.EAdapterList.this.notifier;
/*     */             }
/*     */           };
/* 152 */         ((Adapter)adapter).notifyChanged(notification);
/*     */       } 
/* 154 */       if (adapter instanceof Adapter.Internal) {
/*     */         
/* 156 */         ((Adapter.Internal)adapter).unsetTarget(this.notifier);
/*     */       }
/* 158 */       else if (((Adapter)adapter).getTarget() == this.notifier) {
/*     */         
/* 160 */         ((Adapter)adapter).setTarget(null);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object[] data() {
/* 167 */       this.safe = true;
/* 168 */       if (this.data != null && this.data.length != this.size)
/*     */       {
/* 170 */         if (this.size == 0) {
/*     */           
/* 172 */           this.data = null;
/*     */         }
/*     */         else {
/*     */           
/* 176 */           Object[] oldData = this.data;
/* 177 */           this.data = newData(this.size);
/* 178 */           System.arraycopy(oldData, 0, this.data, 0, this.size);
/*     */         } 
/*     */       }
/* 181 */       return this.data;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void ensureSafety() {
/* 186 */       if (this.safe && this.data != null) {
/*     */         
/* 188 */         Object[] oldData = this.data;
/* 189 */         this.data = newData(this.data.length);
/* 190 */         System.arraycopy(oldData, 0, this.data, 0, this.size);
/* 191 */         this.safe = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean add(E object) {
/* 198 */       ensureSafety();
/* 199 */       return super.add(object);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void add(int index, E object) {
/* 205 */       ensureSafety();
/* 206 */       super.add(index, object);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean addAll(Collection<? extends E> collection) {
/* 212 */       ensureSafety();
/* 213 */       return super.addAll(collection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean remove(Object object) {
/* 219 */       ensureSafety();
/* 220 */       return super.remove(object);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public E remove(int index) {
/* 226 */       ensureSafety();
/* 227 */       return (E)super.remove(index);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean removeAll(Collection<?> collection) {
/* 233 */       ensureSafety();
/* 234 */       return super.removeAll(collection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() {
/* 240 */       ensureSafety();
/* 241 */       super.clear();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean retainAll(Collection<?> collection) {
/* 247 */       ensureSafety();
/* 248 */       return super.retainAll(collection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public E set(int index, E object) {
/* 254 */       ensureSafety();
/* 255 */       return (E)super.set(index, object);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void move(int newPosition, E object) {
/* 261 */       ensureSafety();
/* 262 */       super.move(newPosition, object);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public E move(int newPosition, int oldPosition) {
/* 268 */       ensureSafety();
/* 269 */       return (E)super.move(newPosition, oldPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public void addListener(BasicNotifierImpl.EObservableAdapterList.Listener listener) {
/* 274 */       if (this.listeners == null) {
/*     */         
/* 276 */         this.listeners = new BasicNotifierImpl.EObservableAdapterList.Listener[] { listener };
/*     */       }
/*     */       else {
/*     */         
/* 280 */         BasicNotifierImpl.EObservableAdapterList.Listener[] newListeners = new BasicNotifierImpl.EObservableAdapterList.Listener[this.listeners.length + 1];
/* 281 */         System.arraycopy(this.listeners, 0, newListeners, 0, this.listeners.length);
/* 282 */         newListeners[this.listeners.length] = listener;
/* 283 */         this.listeners = newListeners;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void removeListener(BasicNotifierImpl.EObservableAdapterList.Listener listener) {
/* 289 */       if (this.listeners != null)
/*     */       {
/* 291 */         for (int i = 0; i < this.listeners.length; i++) {
/*     */           
/* 293 */           if (this.listeners[i] == listener) {
/*     */             
/* 295 */             if (this.listeners.length == 1) {
/*     */               
/* 297 */               this.listeners = null;
/*     */               
/*     */               break;
/*     */             } 
/* 301 */             BasicNotifierImpl.EObservableAdapterList.Listener[] newListeners = new BasicNotifierImpl.EObservableAdapterList.Listener[this.listeners.length - 1];
/* 302 */             System.arraycopy(this.listeners, 0, newListeners, 0, i);
/* 303 */             if (i != newListeners.length)
/*     */             {
/* 305 */               System.arraycopy(this.listeners, i + 1, newListeners, i, newListeners.length - i);
/*     */             }
/* 307 */             this.listeners = newListeners;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Adapter> eAdapters() {
/* 318 */     return ECollections.emptyEList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicEList<Adapter> eBasicAdapters() {
/* 327 */     return null;
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
/*     */   protected Adapter[] eBasicAdapterArray() {
/* 340 */     BasicEList<Adapter> eBasicAdapters = eBasicAdapters();
/* 341 */     return (eBasicAdapters == null) ? null : (Adapter[])eBasicAdapters.data();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean eBasicHasAdapters() {
/* 350 */     BasicEList<Adapter> eBasicAdapters = eBasicAdapters();
/* 351 */     return (eBasicAdapters != null && eBasicAdapters.size() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eDeliver() {
/* 359 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSetDeliver(boolean deliver) {
/* 367 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eNotify(Notification notification) {
/* 375 */     Adapter[] eAdapters = eBasicAdapterArray();
/* 376 */     if (eAdapters != null && eDeliver())
/*     */     {
/* 378 */       for (int i = 0, size = eAdapters.length; i < size; i++)
/*     */       {
/* 380 */         eAdapters[i].notifyChanged(notification);
/*     */       }
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
/*     */   public boolean eNotificationRequired() {
/* 393 */     return (eBasicHasAdapters() && eDeliver());
/*     */   }
/*     */   
/*     */   public static interface EObservableAdapterList {
/*     */     void addListener(Listener param1Listener);
/*     */     
/*     */     void removeListener(Listener param1Listener);
/*     */     
/*     */     public static interface Listener {
/*     */       void added(Notifier param2Notifier, Adapter param2Adapter);
/*     */       
/*     */       void removed(Notifier param2Notifier, Adapter param2Adapter);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\impl\BasicNotifierImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */