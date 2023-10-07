/*     */ package ujf.verimag.bip.cmodel.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFor;
/*     */ import ujf.verimag.bip.cmodel.CStm;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CForImpl
/*     */   extends CBlockImpl
/*     */   implements CFor
/*     */ {
/*     */   protected CBodyItem initialization;
/*     */   protected CExpression codition;
/*     */   protected CStm iteration;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  90 */     return CmodelPackage.Literals.CFOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CBodyItem getInitialization() {
/*  99 */     return this.initialization;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetInitialization(CBodyItem newInitialization, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 108 */     CBodyItem oldInitialization = this.initialization;
/* 109 */     this.initialization = newInitialization;
/* 110 */     if (eNotificationRequired()) {
/* 111 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 1, oldInitialization, newInitialization);
/* 112 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 114 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitialization(CBodyItem newInitialization) {
/* 123 */     if (newInitialization != this.initialization) {
/* 124 */       NotificationChain msgs = null;
/* 125 */       if (this.initialization != null)
/* 126 */         msgs = ((InternalEObject)this.initialization).eInverseRemove((InternalEObject)this, -2, null, msgs); 
/* 127 */       if (newInitialization != null)
/* 128 */         msgs = ((InternalEObject)newInitialization).eInverseAdd((InternalEObject)this, -2, null, msgs); 
/* 129 */       msgs = basicSetInitialization(newInitialization, msgs);
/* 130 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 132 */     } else if (eNotificationRequired()) {
/* 133 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, newInitialization, newInitialization));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CExpression getCodition() {
/* 142 */     return this.codition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetCodition(CExpression newCodition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 151 */     CExpression oldCodition = this.codition;
/* 152 */     this.codition = newCodition;
/* 153 */     if (eNotificationRequired()) {
/* 154 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 2, oldCodition, newCodition);
/* 155 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 157 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodition(CExpression newCodition) {
/* 166 */     if (newCodition != this.codition) {
/* 167 */       NotificationChain msgs = null;
/* 168 */       if (this.codition != null)
/* 169 */         msgs = ((InternalEObject)this.codition).eInverseRemove((InternalEObject)this, -3, null, msgs); 
/* 170 */       if (newCodition != null)
/* 171 */         msgs = ((InternalEObject)newCodition).eInverseAdd((InternalEObject)this, -3, null, msgs); 
/* 172 */       msgs = basicSetCodition(newCodition, msgs);
/* 173 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 175 */     } else if (eNotificationRequired()) {
/* 176 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, newCodition, newCodition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CStm getIteration() {
/* 185 */     if (this.iteration != null && this.iteration.eIsProxy()) {
/* 186 */       InternalEObject oldIteration = (InternalEObject)this.iteration;
/* 187 */       this.iteration = (CStm)eResolveProxy(oldIteration);
/* 188 */       if (this.iteration != oldIteration && 
/* 189 */         eNotificationRequired()) {
/* 190 */         eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 3, oldIteration, this.iteration));
/*     */       }
/*     */     } 
/* 193 */     return this.iteration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CStm basicGetIteration() {
/* 202 */     return this.iteration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIteration(CStm newIteration) {
/* 211 */     CStm oldIteration = this.iteration;
/* 212 */     this.iteration = newIteration;
/* 213 */     if (eNotificationRequired()) {
/* 214 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, oldIteration, this.iteration));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
/* 224 */     switch (featureID) {
/*     */       case 1:
/* 226 */         return basicSetInitialization((CBodyItem)null, msgs);
/*     */       case 2:
/* 228 */         return basicSetCodition((CExpression)null, msgs);
/*     */     } 
/* 230 */     return super.eInverseRemove(otherEnd, featureID, msgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object eGet(int featureID, boolean resolve, boolean coreType) {
/* 240 */     switch (featureID) {
/*     */       case 1:
/* 242 */         return getInitialization();
/*     */       case 2:
/* 244 */         return getCodition();
/*     */       case 3:
/* 246 */         if (resolve) return getIteration(); 
/* 247 */         return basicGetIteration();
/*     */     } 
/* 249 */     return super.eGet(featureID, resolve, coreType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eSet(int featureID, Object newValue) {
/* 259 */     switch (featureID) {
/*     */       case 1:
/* 261 */         setInitialization((CBodyItem)newValue);
/*     */         return;
/*     */       case 2:
/* 264 */         setCodition((CExpression)newValue);
/*     */         return;
/*     */       case 3:
/* 267 */         setIteration((CStm)newValue);
/*     */         return;
/*     */     } 
/* 270 */     super.eSet(featureID, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eUnset(int featureID) {
/* 280 */     switch (featureID) {
/*     */       case 1:
/* 282 */         setInitialization((CBodyItem)null);
/*     */         return;
/*     */       case 2:
/* 285 */         setCodition((CExpression)null);
/*     */         return;
/*     */       case 3:
/* 288 */         setIteration((CStm)null);
/*     */         return;
/*     */     } 
/* 291 */     super.eUnset(featureID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eIsSet(int featureID) {
/* 301 */     switch (featureID) {
/*     */       case 1:
/* 303 */         return (this.initialization != null);
/*     */       case 2:
/* 305 */         return (this.codition != null);
/*     */       case 3:
/* 307 */         return (this.iteration != null);
/*     */     } 
/* 309 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmodel\impl\CForImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */