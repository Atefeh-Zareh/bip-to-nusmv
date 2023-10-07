/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.cmodel.CCaseItem;
/*     */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*     */ import ujf.verimag.bip.cmodel.CmodelPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSwitchStmImpl
/*     */   extends CStmImpl
/*     */   implements CSwitchStm
/*     */ {
/*     */   protected EList<CCaseItem> caseAction;
/*  62 */   protected static final String SELECTOR_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected String selector = SELECTOR_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  90 */     return CmodelPackage.Literals.CSWITCH_STM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<CCaseItem> getCaseAction() {
/*  99 */     if (this.caseAction == null) {
/* 100 */       this.caseAction = (EList<CCaseItem>)new EObjectContainmentEList(CCaseItem.class, (InternalEObject)this, 0);
/*     */     }
/* 102 */     return this.caseAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSelector() {
/* 111 */     return this.selector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelector(String newSelector) {
/* 120 */     String oldSelector = this.selector;
/* 121 */     this.selector = newSelector;
/* 122 */     if (eNotificationRequired()) {
/* 123 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldSelector, this.selector));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 133 */     switch (featureID) {
/*     */       case 0:
/* 135 */         return ((InternalEList)getCaseAction()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 137 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 147 */     switch (featureID) {
/*     */       case 0:
/* 149 */         return getCaseAction();
/*     */       case 1:
/* 151 */         return getSelector();
/*     */     } 
/* 153 */     return super.eGet(featureID, resolve, coreType);
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
/* 164 */     switch (featureID) {
/*     */       case 0:
/* 166 */         getCaseAction().clear();
/* 167 */         getCaseAction().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 170 */         setSelector((String)newValue);
/*     */         return;
/*     */     } 
/* 173 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 183 */     switch (featureID) {
/*     */       case 0:
/* 185 */         getCaseAction().clear();
/*     */         return;
/*     */       case 1:
/* 188 */         setSelector(SELECTOR_EDEFAULT);
/*     */         return;
/*     */     } 
/* 191 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 201 */     switch (featureID) {
/*     */       case 0:
/* 203 */         return (this.caseAction != null && !this.caseAction.isEmpty());
/*     */       case 1:
/* 205 */         return (SELECTOR_EDEFAULT == null) ? ((this.selector != null)) : (!SELECTOR_EDEFAULT.equals(this.selector));
/*     */     } 
/* 207 */     return super.eIsSet(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 217 */     if (eIsProxy()) return super.toString();
/*     */     
/* 219 */     StringBuffer result = new StringBuffer(super.toString());
/* 220 */     result.append(" (selector: ");
/* 221 */     result.append(this.selector);
/* 222 */     result.append(')');
/* 223 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CSwitchStmImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */