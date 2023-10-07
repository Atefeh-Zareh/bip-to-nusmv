/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
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
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ExpressionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctionCallExpressionImpl
/*     */   extends ExpressionImpl
/*     */   implements FunctionCallExpression
/*     */ {
/*  57 */   protected static final String FUNCTION_NAME_EDEFAULT = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   protected String functionName = FUNCTION_NAME_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final boolean IS_ON_REF_EDEFAULT = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isOnRef = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EList<Expression> actualData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Expression navigated;
/*     */ 
/*     */ 
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
/* 127 */     return ExpressionsPackage.Literals.FUNCTION_CALL_EXPRESSION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFunctionName() {
/* 137 */     return this.functionName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionName(String newFunctionName) {
/* 147 */     String oldFunctionName = this.functionName;
/* 148 */     this.functionName = newFunctionName;
/* 149 */     if (eNotificationRequired()) {
/* 150 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, oldFunctionName, this.functionName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsOnRef() {
/* 160 */     return this.isOnRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsOnRef(boolean newIsOnRef) {
/* 170 */     boolean oldIsOnRef = this.isOnRef;
/* 171 */     this.isOnRef = newIsOnRef;
/* 172 */     if (eNotificationRequired()) {
/* 173 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, oldIsOnRef, this.isOnRef));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Expression> getActualData() {
/* 183 */     if (this.actualData == null)
/*     */     {
/* 185 */       this.actualData = (EList<Expression>)new EObjectContainmentEList(Expression.class, (InternalEObject)this, 2);
/*     */     }
/* 187 */     return this.actualData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getNavigated() {
/* 197 */     return this.navigated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetNavigated(Expression newNavigated, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 207 */     Expression oldNavigated = this.navigated;
/* 208 */     this.navigated = newNavigated;
/* 209 */     if (eNotificationRequired()) {
/*     */       
/* 211 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 3, oldNavigated, newNavigated);
/* 212 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 214 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNavigated(Expression newNavigated) {
/* 224 */     if (newNavigated != this.navigated) {
/*     */       
/* 226 */       NotificationChain msgs = null;
/* 227 */       if (this.navigated != null)
/* 228 */         msgs = ((InternalEObject)this.navigated).eInverseRemove((InternalEObject)this, -4, null, msgs); 
/* 229 */       if (newNavigated != null)
/* 230 */         msgs = ((InternalEObject)newNavigated).eInverseAdd((InternalEObject)this, -4, null, msgs); 
/* 231 */       msgs = basicSetNavigated(newNavigated, msgs);
/* 232 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 234 */     } else if (eNotificationRequired()) {
/* 235 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, newNavigated, newNavigated));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 246 */     switch (featureID) {
/*     */       
/*     */       case 2:
/* 249 */         return ((InternalEList)getActualData()).basicRemove(otherEnd, msgs);
/*     */       case 3:
/* 251 */         return basicSetNavigated((Expression)null, msgs);
/*     */     } 
/* 253 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 264 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 267 */         return getFunctionName();
/*     */       case 1:
/* 269 */         return Boolean.valueOf(isIsOnRef());
/*     */       case 2:
/* 271 */         return getActualData();
/*     */       case 3:
/* 273 */         return getNavigated();
/*     */     } 
/* 275 */     return super.eGet(featureID, resolve, coreType);
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
/* 287 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 290 */         setFunctionName((String)newValue);
/*     */         return;
/*     */       case 1:
/* 293 */         setIsOnRef(((Boolean)newValue).booleanValue());
/*     */         return;
/*     */       case 2:
/* 296 */         getActualData().clear();
/* 297 */         getActualData().addAll((Collection)newValue);
/*     */         return;
/*     */       case 3:
/* 300 */         setNavigated((Expression)newValue);
/*     */         return;
/*     */     } 
/* 303 */     super.eSet(featureID, newValue);
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
/* 314 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 317 */         setFunctionName(FUNCTION_NAME_EDEFAULT);
/*     */         return;
/*     */       case 1:
/* 320 */         setIsOnRef(false);
/*     */         return;
/*     */       case 2:
/* 323 */         getActualData().clear();
/*     */         return;
/*     */       case 3:
/* 326 */         setNavigated((Expression)null);
/*     */         return;
/*     */     } 
/* 329 */     super.eUnset(featureID);
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
/* 340 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 343 */         return (FUNCTION_NAME_EDEFAULT == null) ? ((this.functionName != null)) : (!FUNCTION_NAME_EDEFAULT.equals(this.functionName));
/*     */       case 1:
/* 345 */         return this.isOnRef;
/*     */       case 2:
/* 347 */         return (this.actualData != null && !this.actualData.isEmpty());
/*     */       case 3:
/* 349 */         return (this.navigated != null);
/*     */     } 
/* 351 */     return super.eIsSet(featureID);
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
/* 362 */     if (eIsProxy()) return super.toString();
/*     */     
/* 364 */     StringBuffer result = new StringBuffer(super.toString());
/* 365 */     result.append(" (functionName: ");
/* 366 */     result.append(this.functionName);
/* 367 */     result.append(", isOnRef: ");
/* 368 */     result.append(this.isOnRef);
/* 369 */     result.append(')');
/* 370 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\FunctionCallExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */