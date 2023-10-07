/*     */ package ujf.verimag.bip.Extra.Traceability.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceabilityPackage;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceableElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TraceableElementImpl
/*     */   extends EObjectImpl
/*     */   implements TraceableElement
/*     */ {
/*     */   protected static final int START_SOURCE_LINE_EDEFAULT = 0;
/*  52 */   protected int startSourceLine = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EClass eStaticClass() {
/*  72 */     return TraceabilityPackage.Literals.TRACEABLE_ELEMENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartSourceLine() {
/*  82 */     return this.startSourceLine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartSourceLine(int newStartSourceLine) {
/*  92 */     int oldStartSourceLine = this.startSourceLine;
/*  93 */     this.startSourceLine = newStartSourceLine;
/*  94 */     if (eNotificationRequired()) {
/*  95 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldStartSourceLine, this.startSourceLine));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 106 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 109 */         return Integer.valueOf(getStartSourceLine());
/*     */     } 
/* 111 */     return super.eGet(featureID, resolve, coreType);
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
/* 122 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 125 */         setStartSourceLine(((Integer)newValue).intValue());
/*     */         return;
/*     */     } 
/* 128 */     super.eSet(featureID, newValue);
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
/* 139 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 142 */         setStartSourceLine(0);
/*     */         return;
/*     */     } 
/* 145 */     super.eUnset(featureID);
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
/* 156 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 159 */         return (this.startSourceLine != 0);
/*     */     } 
/* 161 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 172 */     if (eIsProxy()) return super.toString();
/*     */     
/* 174 */     StringBuffer result = new StringBuffer(super.toString());
/* 175 */     result.append(" (startSourceLine: ");
/* 176 */     result.append(this.startSourceLine);
/* 177 */     result.append(')');
/* 178 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Traceability\impl\TraceableElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */