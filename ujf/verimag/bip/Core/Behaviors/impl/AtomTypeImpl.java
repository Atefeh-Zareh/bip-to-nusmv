/*     */ package ujf.verimag.bip.Core.Behaviors.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AtomTypeImpl
/*     */   extends ComponentTypeImpl
/*     */   implements AtomType
/*     */ {
/*     */   protected Behavior behavior;
/*     */   protected EList<Variable> variable;
/*     */   protected EList<PortDefinition> portDefinition;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  96 */     return BehaviorsPackage.Literals.ATOM_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Behavior getBehavior() {
/* 106 */     return this.behavior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetBehavior(Behavior newBehavior, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 116 */     Behavior oldBehavior = this.behavior;
/* 117 */     this.behavior = newBehavior;
/* 118 */     if (eNotificationRequired()) {
/*     */       
/* 120 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 12, oldBehavior, newBehavior);
/* 121 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 123 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBehavior(Behavior newBehavior) {
/* 133 */     if (newBehavior != this.behavior) {
/*     */       
/* 135 */       NotificationChain msgs = null;
/* 136 */       if (this.behavior != null)
/* 137 */         msgs = ((InternalEObject)this.behavior).eInverseRemove((InternalEObject)this, 0, Behavior.class, msgs); 
/* 138 */       if (newBehavior != null)
/* 139 */         msgs = ((InternalEObject)newBehavior).eInverseAdd((InternalEObject)this, 0, Behavior.class, msgs); 
/* 140 */       msgs = basicSetBehavior(newBehavior, msgs);
/* 141 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 143 */     } else if (eNotificationRequired()) {
/* 144 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 12, newBehavior, newBehavior));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Variable> getVariable() {
/* 154 */     if (this.variable == null)
/*     */     {
/* 156 */       this.variable = (EList<Variable>)new EObjectContainmentEList(Variable.class, (InternalEObject)this, 13);
/*     */     }
/* 158 */     return this.variable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<PortDefinition> getPortDefinition() {
/* 168 */     if (this.portDefinition == null)
/*     */     {
/* 170 */       this.portDefinition = (EList<PortDefinition>)new EObjectContainmentWithInverseEList(PortDefinition.class, (InternalEObject)this, 14, 2);
/*     */     }
/* 172 */     return this.portDefinition;
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
/*     */   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 184 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 187 */         if (this.behavior != null)
/* 188 */           msgs = ((InternalEObject)this.behavior).eInverseRemove((InternalEObject)this, -13, null, msgs); 
/* 189 */         return basicSetBehavior((Behavior)otherEnd, msgs);
/*     */       case 14:
/* 191 */         return ((InternalEList)getPortDefinition()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 193 */     return super.eInverseAdd(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 204 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 207 */         return basicSetBehavior((Behavior)null, msgs);
/*     */       case 13:
/* 209 */         return ((InternalEList)getVariable()).basicRemove(otherEnd, msgs);
/*     */       case 14:
/* 211 */         return ((InternalEList)getPortDefinition()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 213 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 224 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 227 */         return getBehavior();
/*     */       case 13:
/* 229 */         return getVariable();
/*     */       case 14:
/* 231 */         return getPortDefinition();
/*     */     } 
/* 233 */     return super.eGet(featureID, resolve, coreType);
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
/* 245 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 248 */         setBehavior((Behavior)newValue);
/*     */         return;
/*     */       case 13:
/* 251 */         getVariable().clear();
/* 252 */         getVariable().addAll((Collection)newValue);
/*     */         return;
/*     */       case 14:
/* 255 */         getPortDefinition().clear();
/* 256 */         getPortDefinition().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 259 */     super.eSet(featureID, newValue);
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
/* 270 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 273 */         setBehavior((Behavior)null);
/*     */         return;
/*     */       case 13:
/* 276 */         getVariable().clear();
/*     */         return;
/*     */       case 14:
/* 279 */         getPortDefinition().clear();
/*     */         return;
/*     */     } 
/* 282 */     super.eUnset(featureID);
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
/* 293 */     switch (featureID) {
/*     */       
/*     */       case 12:
/* 296 */         return (this.behavior != null);
/*     */       case 13:
/* 298 */         return (this.variable != null && !this.variable.isEmpty());
/*     */       case 14:
/* 300 */         return (this.portDefinition != null && !this.portDefinition.isEmpty());
/*     */     } 
/* 302 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\AtomTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */