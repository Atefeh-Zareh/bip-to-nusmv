/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.ActionImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AssignmentActionImpl
/*     */   extends ActionImpl
/*     */   implements AssignmentAction
/*     */ {
/*     */   protected DataReference assignedTarget;
/*     */   protected Expression assignedValue;
/*  72 */   protected static final AssignType TYPE_EDEFAULT = AssignType.ASSIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   protected AssignType type = TYPE_EDEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 102 */     return ActionsPackage.Literals.ASSIGNMENT_ACTION;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataReference getAssignedTarget() {
/* 112 */     return this.assignedTarget;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetAssignedTarget(DataReference newAssignedTarget, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 122 */     DataReference oldAssignedTarget = this.assignedTarget;
/* 123 */     this.assignedTarget = newAssignedTarget;
/* 124 */     if (eNotificationRequired()) {
/*     */       
/* 126 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 0, oldAssignedTarget, newAssignedTarget);
/* 127 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 129 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAssignedTarget(DataReference newAssignedTarget) {
/* 139 */     if (newAssignedTarget != this.assignedTarget) {
/*     */       
/* 141 */       NotificationChain msgs = null;
/* 142 */       if (this.assignedTarget != null)
/* 143 */         msgs = ((InternalEObject)this.assignedTarget).eInverseRemove((InternalEObject)this, -1, null, msgs); 
/* 144 */       if (newAssignedTarget != null)
/* 145 */         msgs = ((InternalEObject)newAssignedTarget).eInverseAdd((InternalEObject)this, -1, null, msgs); 
/* 146 */       msgs = basicSetAssignedTarget(newAssignedTarget, msgs);
/* 147 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 149 */     } else if (eNotificationRequired()) {
/* 150 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, newAssignedTarget, newAssignedTarget));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getAssignedValue() {
/* 160 */     return this.assignedValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetAssignedValue(Expression newAssignedValue, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 170 */     Expression oldAssignedValue = this.assignedValue;
/* 171 */     this.assignedValue = newAssignedValue;
/* 172 */     if (eNotificationRequired()) {
/*     */       
/* 174 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldAssignedValue, newAssignedValue);
/* 175 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 177 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAssignedValue(Expression newAssignedValue) {
/* 187 */     if (newAssignedValue != this.assignedValue) {
/*     */       
/* 189 */       NotificationChain msgs = null;
/* 190 */       if (this.assignedValue != null)
/* 191 */         msgs = ((InternalEObject)this.assignedValue).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 192 */       if (newAssignedValue != null)
/* 193 */         msgs = ((InternalEObject)newAssignedValue).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 194 */       msgs = basicSetAssignedValue(newAssignedValue, msgs);
/* 195 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 197 */     } else if (eNotificationRequired()) {
/* 198 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newAssignedValue, newAssignedValue));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AssignType getType() {
/* 208 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(AssignType newType) {
/* 218 */     AssignType oldType = this.type;
/* 219 */     this.type = (newType == null) ? TYPE_EDEFAULT : newType;
/* 220 */     if (eNotificationRequired()) {
/* 221 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, oldType, this.type));
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
/* 232 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 235 */         return basicSetAssignedTarget((DataReference)null, msgs);
/*     */       case 1:
/* 237 */         return basicSetAssignedValue((Expression)null, msgs);
/*     */     } 
/* 239 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 250 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 253 */         return getAssignedTarget();
/*     */       case 1:
/* 255 */         return getAssignedValue();
/*     */       case 2:
/* 257 */         return getType();
/*     */     } 
/* 259 */     return super.eGet(featureID, resolve, coreType);
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
/* 270 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 273 */         setAssignedTarget((DataReference)newValue);
/*     */         return;
/*     */       case 1:
/* 276 */         setAssignedValue((Expression)newValue);
/*     */         return;
/*     */       case 2:
/* 279 */         setType((AssignType)newValue);
/*     */         return;
/*     */     } 
/* 282 */     super.eSet(featureID, newValue);
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
/* 293 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 296 */         setAssignedTarget((DataReference)null);
/*     */         return;
/*     */       case 1:
/* 299 */         setAssignedValue((Expression)null);
/*     */         return;
/*     */       case 2:
/* 302 */         setType(TYPE_EDEFAULT);
/*     */         return;
/*     */     } 
/* 305 */     super.eUnset(featureID);
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
/* 316 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 319 */         return (this.assignedTarget != null);
/*     */       case 1:
/* 321 */         return (this.assignedValue != null);
/*     */       case 2:
/* 323 */         return (this.type != TYPE_EDEFAULT);
/*     */     } 
/* 325 */     return super.eIsSet(featureID);
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
/* 336 */     if (eIsProxy()) return super.toString();
/*     */     
/* 338 */     StringBuffer result = new StringBuffer(super.toString());
/* 339 */     result.append(" (type: ");
/* 340 */     result.append(this.type);
/* 341 */     result.append(')');
/* 342 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\impl\AssignmentActionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */