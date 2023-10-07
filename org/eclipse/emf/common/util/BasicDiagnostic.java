/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.core.runtime.IStatus;
/*     */ import org.eclipse.emf.common.EMFPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicDiagnostic
/*     */   implements Diagnostic, DiagnosticChain
/*     */ {
/*     */   protected int severity;
/*     */   protected String message;
/*     */   protected List<Diagnostic> children;
/*     */   protected List<?> data;
/*     */   protected String source;
/*     */   protected int code;
/*     */   
/*     */   public BasicDiagnostic() {}
/*     */   
/*     */   public BasicDiagnostic(String source, int code, String message, Object[] data) {
/*  79 */     this.source = source;
/*  80 */     this.code = code;
/*  81 */     this.message = message;
/*  82 */     this.data = dataAsList(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicDiagnostic(int severity, String source, int code, String message, Object[] data) {
/*  87 */     this(source, code, message, data);
/*  88 */     this.severity = severity;
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicDiagnostic(String source, int code, List<? extends Diagnostic> children, String message, Object[] data) {
/*  93 */     this(source, code, message, data);
/*  94 */     if (children != null)
/*     */     {
/*  96 */       for (Diagnostic diagnostic : children)
/*     */       {
/*  98 */         add(diagnostic);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<?> dataAsList(Object[] data) {
/* 105 */     if (data == null)
/*     */     {
/* 107 */       return Collections.EMPTY_LIST;
/*     */     }
/*     */ 
/*     */     
/* 111 */     Object[] copy = new Object[data.length];
/* 112 */     System.arraycopy(data, 0, copy, 0, data.length);
/* 113 */     return new BasicEList.UnmodifiableEList(copy.length, copy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setSeverity(int severity) {
/* 119 */     this.severity = severity;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeverity() {
/* 124 */     return this.severity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 129 */     return this.message;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<?> getData() {
/* 134 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Diagnostic> getChildren() {
/* 139 */     if (this.children == null)
/*     */     {
/* 141 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */     
/* 145 */     return Collections.unmodifiableList(this.children);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setSource(String source) {
/* 151 */     this.source = source;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSource() {
/* 156 */     return this.source;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setCode(int code) {
/* 161 */     this.code = code;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCode() {
/* 166 */     return this.code;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(Diagnostic diagnostic) {
/* 171 */     if (this.children == null)
/*     */     {
/* 173 */       this.children = new BasicEList<Diagnostic>();
/*     */     }
/*     */     
/* 176 */     this.children.add(diagnostic);
/* 177 */     int childSeverity = diagnostic.getSeverity();
/* 178 */     if (childSeverity > getSeverity())
/*     */     {
/* 180 */       this.severity = childSeverity;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAll(Diagnostic diagnostic) {
/* 186 */     for (Diagnostic child : diagnostic.getChildren())
/*     */     {
/* 188 */       add(child);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void merge(Diagnostic diagnostic) {
/* 194 */     if (diagnostic.getChildren().isEmpty()) {
/*     */       
/* 196 */       add(diagnostic);
/*     */     }
/*     */     else {
/*     */       
/* 200 */       addAll(diagnostic);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int recomputeSeverity() {
/* 206 */     if (this.children != null) {
/*     */       
/* 208 */       this.severity = 0;
/* 209 */       for (Diagnostic child : this.children) {
/*     */         
/* 211 */         int childSeverity = (child instanceof BasicDiagnostic) ? ((BasicDiagnostic)child).recomputeSeverity() : child.getSeverity();
/* 212 */         if (childSeverity > this.severity)
/*     */         {
/* 214 */           this.severity = childSeverity;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 219 */     return this.severity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getException() {
/* 228 */     List<?> data = getData();
/* 229 */     if (data != null)
/*     */     {
/* 231 */       for (Object datum : data) {
/*     */         
/* 233 */         if (datum instanceof Throwable)
/*     */         {
/* 235 */           return (Throwable)datum;
/*     */         }
/*     */       } 
/*     */     }
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 245 */     StringBuilder result = new StringBuilder();
/* 246 */     result.append("Diagnostic ");
/* 247 */     switch (this.severity) {
/*     */ 
/*     */       
/*     */       case 0:
/* 251 */         result.append("OK");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 256 */         result.append("INFO");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 261 */         result.append("WARNING");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 266 */         result.append("ERROR");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 8:
/* 271 */         result.append("CANCEL");
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 276 */         result.append(Integer.toHexString(this.severity));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 281 */     result.append(" source=");
/* 282 */     result.append(this.source);
/*     */     
/* 284 */     result.append(" code=");
/* 285 */     result.append(this.code);
/*     */     
/* 287 */     result.append(' ');
/* 288 */     result.append(this.message);
/*     */     
/* 290 */     if (this.data != null) {
/*     */       
/* 292 */       result.append(" data=");
/* 293 */       result.append(this.data);
/*     */     } 
/* 295 */     if (this.children != null) {
/*     */       
/* 297 */       result.append(' ');
/* 298 */       result.append(this.children);
/*     */     } 
/*     */     
/* 301 */     return result.toString();
/*     */   }
/*     */   
/*     */   private static class StatusWrapper
/*     */     implements IStatus {
/* 306 */     protected static final IStatus[] EMPTY_CHILDREN = new IStatus[0];
/*     */     
/*     */     protected Throwable throwable;
/*     */     
/*     */     protected Diagnostic diagnostic;
/*     */     protected IStatus[] wrappedChildren;
/*     */     
/*     */     public StatusWrapper(Diagnostic diagnostic) {
/* 314 */       this.diagnostic = diagnostic;
/*     */     }
/*     */ 
/*     */     
/*     */     public StatusWrapper(DiagnosticException diagnosticException) {
/* 319 */       this.throwable = diagnosticException;
/* 320 */       this.diagnostic = diagnosticException.getDiagnostic();
/*     */     }
/*     */ 
/*     */     
/*     */     public IStatus[] getChildren() {
/* 325 */       if (this.wrappedChildren == null) {
/*     */         
/* 327 */         List<Diagnostic> children = this.diagnostic.getChildren();
/* 328 */         if (children.isEmpty()) {
/*     */           
/* 330 */           this.wrappedChildren = EMPTY_CHILDREN;
/*     */         }
/*     */         else {
/*     */           
/* 334 */           this.wrappedChildren = new IStatus[children.size()];
/* 335 */           for (int i = 0; i < this.wrappedChildren.length; i++)
/*     */           {
/* 337 */             this.wrappedChildren[i] = BasicDiagnostic.toIStatus(children.get(i));
/*     */           }
/*     */         } 
/*     */       } 
/* 341 */       return this.wrappedChildren;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getCode() {
/* 346 */       return this.diagnostic.getCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public Throwable getException() {
/* 351 */       return (this.throwable != null) ? this.throwable : this.diagnostic.getException();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getMessage() {
/* 356 */       return this.diagnostic.getMessage();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPlugin() {
/* 361 */       return this.diagnostic.getSource();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSeverity() {
/* 366 */       return this.diagnostic.getSeverity();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isMultiStatus() {
/* 371 */       return !this.diagnostic.getChildren().isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isOK() {
/* 376 */       return (this.diagnostic.getSeverity() == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean matches(int severityMask) {
/* 381 */       return ((this.diagnostic.getSeverity() & severityMask) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 387 */       return this.diagnostic.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public static IStatus convert(Diagnostic diagnostic) {
/* 392 */       return 
/* 393 */         (diagnostic instanceof BasicDiagnostic.DiagnosticWrapper) ? 
/* 394 */         ((BasicDiagnostic.DiagnosticWrapper)diagnostic).status : 
/* 395 */         new StatusWrapper(diagnostic);
/*     */     }
/*     */ 
/*     */     
/*     */     public static IStatus create(DiagnosticException diagnosticException) {
/* 400 */       return new StatusWrapper(diagnosticException);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IStatus toIStatus(Diagnostic diagnostic) {
/* 409 */     return StatusWrapper.convert(diagnostic);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IStatus toIStatus(DiagnosticException diagnosticException) {
/* 417 */     return StatusWrapper.create(diagnosticException);
/*     */   }
/*     */   
/*     */   private static class DiagnosticWrapper
/*     */     implements Diagnostic
/*     */   {
/*     */     protected IStatus status;
/*     */     protected List<Diagnostic> wrappedChildren;
/*     */     protected List<Diagnostic> unmodifiableWrappedChildren;
/*     */     protected List<Object> data;
/*     */     
/*     */     public DiagnosticWrapper(IStatus status) {
/* 429 */       this.status = status;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getCode() {
/* 434 */       return this.status.getCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getMessage() {
/* 439 */       return this.status.getMessage();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSeverity() {
/* 444 */       return this.status.getSeverity();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getSource() {
/* 449 */       return this.status.getPlugin();
/*     */     }
/*     */ 
/*     */     
/*     */     public Throwable getException() {
/* 454 */       return this.status.getException();
/*     */     }
/*     */ 
/*     */     
/*     */     public List<Diagnostic> basicGetChildren() {
/* 459 */       if (this.wrappedChildren == null) {
/*     */         
/* 461 */         IStatus[] children = this.status.getChildren();
/* 462 */         if (children.length == 0) {
/*     */           
/* 464 */           this.wrappedChildren = new ArrayList<Diagnostic>();
/*     */         }
/*     */         else {
/*     */           
/* 468 */           this.wrappedChildren = new ArrayList<Diagnostic>(children.length); byte b; int i; IStatus[] arrayOfIStatus;
/* 469 */           for (i = (arrayOfIStatus = children).length, b = 0; b < i; ) { IStatus child = arrayOfIStatus[b];
/*     */             
/* 471 */             this.wrappedChildren.add(BasicDiagnostic.toDiagnostic(child)); b++; }
/*     */         
/*     */         } 
/*     */       } 
/* 475 */       return this.wrappedChildren;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<Diagnostic> getChildren() {
/* 480 */       if (this.unmodifiableWrappedChildren == null)
/*     */       {
/* 482 */         this.unmodifiableWrappedChildren = Collections.unmodifiableList(basicGetChildren());
/*     */       }
/* 484 */       return this.unmodifiableWrappedChildren;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<?> getData() {
/* 489 */       if (this.data == null) {
/*     */         
/* 491 */         List<Object> list = new ArrayList(2);
/* 492 */         Throwable exception = getException();
/* 493 */         if (exception != null)
/*     */         {
/* 495 */           list.add(exception);
/*     */         }
/* 497 */         list.add(this.status);
/* 498 */         this.data = Collections.unmodifiableList(list);
/*     */       } 
/* 500 */       return this.data;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Diagnostic convert(IStatus status) {
/* 505 */       return 
/* 506 */         (status instanceof BasicDiagnostic.StatusWrapper) ? 
/* 507 */         ((BasicDiagnostic.StatusWrapper)status).diagnostic : 
/* 508 */         new DiagnosticWrapper(status);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static Diagnostic toDiagnostic(IStatus status) {
/* 514 */     return DiagnosticWrapper.convert(status);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Diagnostic toDiagnostic(Throwable throwable) {
/* 525 */     if (throwable instanceof DiagnosticException)
/*     */     {
/* 527 */       return ((DiagnosticException)throwable).getDiagnostic();
/*     */     }
/* 529 */     if (throwable instanceof WrappedException)
/*     */     {
/* 531 */       return toDiagnostic(throwable.getCause());
/*     */     }
/*     */     
/* 534 */     if (EMFPlugin.IS_ECLIPSE_RUNNING) {
/*     */       
/* 536 */       Diagnostic diagnostic = EclipseHelper.toDiagnostic(throwable);
/* 537 */       if (diagnostic != null)
/*     */       {
/* 539 */         return diagnostic;
/*     */       }
/*     */     } 
/*     */     
/* 543 */     String message = throwable.getClass().getName();
/* 544 */     int index = message.lastIndexOf('.');
/* 545 */     if (index >= 0)
/*     */     {
/* 547 */       message = message.substring(index + 1);
/*     */     }
/* 549 */     if (throwable.getLocalizedMessage() != null)
/*     */     {
/* 551 */       message = String.valueOf(message) + ": " + throwable.getLocalizedMessage();
/*     */     }
/*     */     
/* 554 */     BasicDiagnostic basicDiagnostic = 
/* 555 */       new BasicDiagnostic(
/* 556 */         4, 
/* 557 */         "org.eclipse.emf.common", 
/* 558 */         0, 
/* 559 */         message, 
/* 560 */         new Object[] { throwable });
/*     */     
/* 562 */     if (throwable.getCause() != null && throwable.getCause() != throwable) {
/*     */       
/* 564 */       throwable = throwable.getCause();
/* 565 */       basicDiagnostic.add(toDiagnostic(throwable));
/*     */     } 
/*     */     
/* 568 */     return basicDiagnostic;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class EclipseHelper
/*     */   {
/*     */     public static Diagnostic toDiagnostic(Throwable throwable) {
/* 575 */       if (throwable instanceof CoreException) {
/*     */         
/* 577 */         IStatus status = ((CoreException)throwable).getStatus();
/* 578 */         BasicDiagnostic.DiagnosticWrapper wrapperDiagnostic = new BasicDiagnostic.DiagnosticWrapper(status);
/* 579 */         Throwable cause = throwable.getCause();
/* 580 */         if (cause != null && cause != throwable)
/*     */         {
/* 582 */           wrapperDiagnostic.basicGetChildren().add(BasicDiagnostic.toDiagnostic(cause));
/*     */         }
/* 584 */         return wrapperDiagnostic;
/*     */       } 
/* 586 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\BasicDiagnostic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */