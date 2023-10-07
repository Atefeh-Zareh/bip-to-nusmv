/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.eclipse.core.runtime.IProgressMonitor;
/*     */ import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
/*     */ import org.eclipse.core.runtime.IStatus;
/*     */ import org.eclipse.core.runtime.SubProgressMonitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicMonitor
/*     */   implements Monitor
/*     */ {
/*     */   private boolean isCanceled;
/*     */   private Diagnostic blockedReason;
/*     */   
/*     */   public boolean isCanceled() {
/*  43 */     return this.isCanceled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanceled(boolean isCanceled) {
/*  48 */     this.isCanceled = isCanceled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Diagnostic getBlockedReason() {
/*  56 */     return this.blockedReason;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlocked(Diagnostic reason) {
/*  61 */     this.blockedReason = reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearBlocked() {
/*  66 */     this.blockedReason = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginTask(String name, int totalWork) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTaskName(String name) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void subTask(String name) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void worked(int work) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void internalWorked(double work) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void done() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Delegating
/*     */   {
/*     */     protected Monitor monitor;
/*     */ 
/*     */ 
/*     */     
/*     */     public Delegating(Monitor monitor) {
/* 108 */       this.monitor = monitor;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isCanceled() {
/* 113 */       return this.monitor.isCanceled();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setCanceled(boolean value) {
/* 118 */       this.monitor.setCanceled(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBlocked(Diagnostic reason) {
/* 123 */       this.monitor.setBlocked(reason);
/*     */     }
/*     */ 
/*     */     
/*     */     public void clearBlocked() {
/* 128 */       this.monitor.clearBlocked();
/*     */     }
/*     */ 
/*     */     
/*     */     public void beginTask(String name, int totalWork) {
/* 133 */       this.monitor.beginTask(name, totalWork);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTaskName(String name) {
/* 138 */       this.monitor.setTaskName(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public void subTask(String name) {
/* 143 */       this.monitor.subTask(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public void worked(int work) {
/* 148 */       this.monitor.worked(work);
/*     */     }
/*     */ 
/*     */     
/*     */     public void internalWorked(double work) {
/* 153 */       this.monitor.internalWorked(work);
/*     */     }
/*     */ 
/*     */     
/*     */     public void done() {
/* 158 */       this.monitor.done();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private static class Eclipse
/*     */       extends Delegating
/*     */       implements IProgressMonitorWithBlocking
/*     */     {
/*     */       public Eclipse(Monitor monitor) {
/* 168 */         super(monitor);
/*     */       }
/*     */ 
/*     */       
/*     */       public void setBlocked(IStatus reason) {
/* 173 */         setBlocked(
/* 174 */             new BasicDiagnostic(
/* 175 */               reason.getSeverity(), 
/* 176 */               reason.getPlugin(), 
/* 177 */               reason.getCode(), 
/* 178 */               reason.getMessage(), 
/* 179 */               null));
/*     */       }
/*     */ 
/*     */       
/*     */       public static IProgressMonitorWithBlocking createIProgressMonitorWithBlocking(Monitor monitor) {
/* 184 */         if (monitor instanceof IProgressMonitorWithBlocking)
/*     */         {
/* 186 */           return (IProgressMonitorWithBlocking)monitor;
/*     */         }
/*     */ 
/*     */         
/* 190 */         return new Eclipse(monitor);
/*     */       }
/*     */ 
/*     */       
/*     */       public static IProgressMonitor createIProgressMonitor(Monitor monitor)
/*     */       {
/* 196 */         if (monitor instanceof IProgressMonitor)
/*     */         {
/* 198 */           return (IProgressMonitor)monitor;
/*     */         }
/*     */ 
/*     */         
/* 202 */         return (IProgressMonitor)new Eclipse(monitor); } } } private static class Eclipse extends Delegating implements IProgressMonitorWithBlocking { public static IProgressMonitor createIProgressMonitor(Monitor monitor) { if (monitor instanceof IProgressMonitor) return (IProgressMonitor)monitor;  return (IProgressMonitor)new Eclipse(monitor); }
/*     */      public Eclipse(Monitor monitor) {
/*     */       super(monitor);
/*     */     } public void setBlocked(IStatus reason) {
/*     */       setBlocked(new BasicDiagnostic(reason.getSeverity(), reason.getPlugin(), reason.getCode(), reason.getMessage(), null));
/*     */     }
/*     */     public static IProgressMonitorWithBlocking createIProgressMonitorWithBlocking(Monitor monitor) {
/*     */       if (monitor instanceof IProgressMonitorWithBlocking)
/*     */         return (IProgressMonitorWithBlocking)monitor; 
/*     */       return new Eclipse(monitor);
/*     */     } }
/*     */   public static IProgressMonitor toIProgressMonitor(Monitor monitor) {
/* 214 */     return Delegating.Eclipse.createIProgressMonitor(monitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IProgressMonitorWithBlocking toIProgressMonitorWithBlocking(Monitor monitor) {
/* 223 */     return Delegating.Eclipse.createIProgressMonitorWithBlocking(monitor);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class EclipseDelegating
/*     */     implements Monitor
/*     */   {
/*     */     protected IProgressMonitor progressMonitor;
/*     */     
/*     */     protected IProgressMonitorWithBlocking progressMonitorWithBlocking;
/*     */ 
/*     */     
/*     */     public EclipseDelegating(IProgressMonitor progressMonitor) {
/* 236 */       this.progressMonitor = progressMonitor;
/* 237 */       if (progressMonitor instanceof IProgressMonitorWithBlocking)
/*     */       {
/* 239 */         this.progressMonitorWithBlocking = (IProgressMonitorWithBlocking)progressMonitor;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public EclipseDelegating(IProgressMonitorWithBlocking progressMonitorWithBlocking) {
/* 245 */       this.progressMonitor = (IProgressMonitor)progressMonitorWithBlocking;
/* 246 */       this.progressMonitorWithBlocking = progressMonitorWithBlocking;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isCanceled() {
/* 251 */       return this.progressMonitor.isCanceled();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setCanceled(boolean value) {
/* 256 */       this.progressMonitor.setCanceled(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBlocked(Diagnostic reason) {
/* 261 */       if (this.progressMonitorWithBlocking != null)
/*     */       {
/* 263 */         this.progressMonitorWithBlocking.setBlocked(BasicDiagnostic.toIStatus(reason));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void clearBlocked() {
/* 269 */       if (this.progressMonitorWithBlocking != null)
/*     */       {
/* 271 */         this.progressMonitorWithBlocking.clearBlocked();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void beginTask(String name, int totalWork) {
/* 277 */       this.progressMonitor.beginTask(name, totalWork);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setTaskName(String name) {
/* 282 */       this.progressMonitor.setTaskName(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public void subTask(String name) {
/* 287 */       this.progressMonitor.subTask(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public void worked(int work) {
/* 292 */       this.progressMonitor.worked(work);
/*     */     }
/*     */ 
/*     */     
/*     */     public void internalWorked(double work) {
/* 297 */       this.progressMonitor.internalWorked(work);
/*     */     }
/*     */ 
/*     */     
/*     */     public void done() {
/* 302 */       this.progressMonitor.done();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Monitor toMonitor(IProgressMonitorWithBlocking progressMonitor) {
/* 312 */     return new EclipseDelegating(progressMonitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Monitor toMonitor(IProgressMonitor progressMonitor) {
/* 321 */     return new EclipseDelegating(progressMonitor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class EclipseSubProgress
/*     */     extends SubProgressMonitor
/*     */     implements Monitor
/*     */   {
/*     */     public EclipseSubProgress(IProgressMonitor monitor, int ticks) {
/* 331 */       super(monitor, ticks);
/*     */     }
/*     */ 
/*     */     
/*     */     public EclipseSubProgress(IProgressMonitor monitor, int ticks, int style) {
/* 336 */       super(monitor, ticks, style);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBlocked(Diagnostic reason) {
/* 341 */       setBlocked(BasicDiagnostic.toIStatus(reason));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Printing
/*     */     extends BasicMonitor
/*     */   {
/*     */     protected PrintStream printStream;
/*     */ 
/*     */     
/*     */     public Printing(PrintStream printStream) {
/* 354 */       this.printStream = printStream;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void beginTask(String name, int totalWork) {
/* 360 */       if (name != null && name.length() != 0)
/*     */       {
/* 362 */         this.printStream.println(">>> " + name);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setTaskName(String name) {
/* 369 */       if (name != null && name.length() != 0)
/*     */       {
/* 371 */         this.printStream.println("<>> " + name);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void subTask(String name) {
/* 378 */       if (name != null && name.length() != 0)
/*     */       {
/* 380 */         this.printStream.println(">>  " + name);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setBlocked(Diagnostic reason) {
/* 387 */       super.setBlocked(reason);
/* 388 */       this.printStream.println("#>  " + reason.getMessage());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void clearBlocked() {
/* 394 */       this.printStream.println("=>  " + getBlockedReason().getMessage());
/* 395 */       super.clearBlocked();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\BasicMonitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */