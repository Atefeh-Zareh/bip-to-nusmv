/*     */ package org.eclipse.emf.common.notify;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NotificationWrapper
/*     */   implements Notification
/*     */ {
/*     */   protected Notification notification;
/*     */   protected Object notifier;
/*     */   
/*     */   public NotificationWrapper(Notification notification) {
/*  41 */     this.notification = notification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationWrapper(Object notifier, Notification notification) {
/*  49 */     this.notifier = notifier;
/*  50 */     this.notification = notification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNotifier() {
/*  59 */     return (this.notifier == null) ? this.notification.getNotifier() : this.notifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEventType() {
/*  68 */     return this.notification.getEventType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureID(Class<?> expectedClass) {
/*  77 */     return this.notification.getFeatureID(expectedClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFeature() {
/*  86 */     return this.notification.getFeature();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getOldValue() {
/*  95 */     return this.notification.getOldValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNewValue() {
/* 104 */     return this.notification.getNewValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasSet() {
/* 113 */     return this.notification.wasSet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTouch() {
/* 122 */     return this.notification.isTouch();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReset() {
/* 131 */     return this.notification.isReset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 140 */     return this.notification.getPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean merge(Notification notification) {
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getOldBooleanValue() {
/* 158 */     return this.notification.getOldBooleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNewBooleanValue() {
/* 167 */     return this.notification.getNewBooleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getOldByteValue() {
/* 176 */     return this.notification.getOldByteValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getNewByteValue() {
/* 185 */     return this.notification.getNewByteValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getOldCharValue() {
/* 194 */     return this.notification.getOldCharValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getNewCharValue() {
/* 203 */     return this.notification.getNewCharValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getOldDoubleValue() {
/* 212 */     return this.notification.getOldDoubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNewDoubleValue() {
/* 221 */     return this.notification.getNewDoubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOldFloatValue() {
/* 230 */     return this.notification.getOldFloatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getNewFloatValue() {
/* 239 */     return this.notification.getNewFloatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOldIntValue() {
/* 248 */     return this.notification.getOldIntValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNewIntValue() {
/* 257 */     return this.notification.getNewIntValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getOldLongValue() {
/* 266 */     return this.notification.getOldLongValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getNewLongValue() {
/* 275 */     return this.notification.getNewLongValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getOldShortValue() {
/* 284 */     return this.notification.getOldShortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNewShortValue() {
/* 293 */     return this.notification.getNewShortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOldStringValue() {
/* 302 */     return this.notification.getOldStringValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNewStringValue() {
/* 311 */     return this.notification.getNewStringValue();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\NotificationWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */