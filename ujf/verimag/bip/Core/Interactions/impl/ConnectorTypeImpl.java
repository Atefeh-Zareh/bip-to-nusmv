/*     */ package ujf.verimag.bip.Core.Interactions.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.eclipse.emf.common.notify.Notification;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.PartTypeImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConnectorTypeImpl
/*     */   extends PartTypeImpl
/*     */   implements ConnectorType
/*     */ {
/*     */   protected Port port;
/*     */   protected EList<InteractionSpecification> interactionSpecification;
/*     */   protected EList<PortParameter> portParameter;
/*     */   protected PortDefinition portDefinition;
/*     */   protected PortExpression definition;
/*     */   protected EList<Variable> variable;
/*     */   
/*     */   protected EClass eStaticClass() {
/* 136 */     return InteractionsPackage.Literals.CONNECTOR_TYPE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Port getPort() {
/* 146 */     return this.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetPort(Port newPort, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 156 */     Port oldPort = this.port;
/* 157 */     this.port = newPort;
/* 158 */     if (eNotificationRequired()) {
/*     */       
/* 160 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 7, oldPort, newPort);
/* 161 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 163 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPort(Port newPort) {
/* 173 */     if (newPort != this.port) {
/*     */       
/* 175 */       NotificationChain msgs = null;
/* 176 */       if (this.port != null)
/* 177 */         msgs = ((InternalEObject)this.port).eInverseRemove((InternalEObject)this, 5, Port.class, msgs); 
/* 178 */       if (newPort != null)
/* 179 */         msgs = ((InternalEObject)newPort).eInverseAdd((InternalEObject)this, 5, Port.class, msgs); 
/* 180 */       msgs = basicSetPort(newPort, msgs);
/* 181 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 183 */     } else if (eNotificationRequired()) {
/* 184 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 7, newPort, newPort));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<InteractionSpecification> getInteractionSpecification() {
/* 194 */     if (this.interactionSpecification == null)
/*     */     {
/* 196 */       this.interactionSpecification = (EList<InteractionSpecification>)new EObjectContainmentWithInverseEList(InteractionSpecification.class, (InternalEObject)this, 8, 0);
/*     */     }
/* 198 */     return this.interactionSpecification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<PortParameter> getPortParameter() {
/* 208 */     if (this.portParameter == null)
/*     */     {
/* 210 */       this.portParameter = (EList<PortParameter>)new EObjectContainmentWithInverseEList(PortParameter.class, (InternalEObject)this, 9, 3);
/*     */     }
/* 212 */     return this.portParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortDefinition getPortDefinition() {
/* 222 */     return this.portDefinition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetPortDefinition(PortDefinition newPortDefinition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 232 */     PortDefinition oldPortDefinition = this.portDefinition;
/* 233 */     this.portDefinition = newPortDefinition;
/* 234 */     if (eNotificationRequired()) {
/*     */       
/* 236 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 10, oldPortDefinition, newPortDefinition);
/* 237 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 239 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPortDefinition(PortDefinition newPortDefinition) {
/* 249 */     if (newPortDefinition != this.portDefinition) {
/*     */       
/* 251 */       NotificationChain msgs = null;
/* 252 */       if (this.portDefinition != null)
/* 253 */         msgs = ((InternalEObject)this.portDefinition).eInverseRemove((InternalEObject)this, 3, PortDefinition.class, msgs); 
/* 254 */       if (newPortDefinition != null)
/* 255 */         msgs = ((InternalEObject)newPortDefinition).eInverseAdd((InternalEObject)this, 3, PortDefinition.class, msgs); 
/* 256 */       msgs = basicSetPortDefinition(newPortDefinition, msgs);
/* 257 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 259 */     } else if (eNotificationRequired()) {
/* 260 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 10, newPortDefinition, newPortDefinition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortExpression getDefinition() {
/* 270 */     return this.definition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NotificationChain basicSetDefinition(PortExpression newDefinition, NotificationChain msgs) {
/*     */     ENotificationImpl eNotificationImpl;
/* 280 */     PortExpression oldDefinition = this.definition;
/* 281 */     this.definition = newDefinition;
/* 282 */     if (eNotificationRequired()) {
/*     */       
/* 284 */       ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 11, oldDefinition, newDefinition);
/* 285 */       if (msgs == null) { eNotificationImpl = notification; } else { eNotificationImpl.add((Notification)notification); }
/*     */     
/* 287 */     }  return (NotificationChain)eNotificationImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefinition(PortExpression newDefinition) {
/* 297 */     if (newDefinition != this.definition) {
/*     */       
/* 299 */       NotificationChain msgs = null;
/* 300 */       if (this.definition != null)
/* 301 */         msgs = ((InternalEObject)this.definition).eInverseRemove((InternalEObject)this, -12, null, msgs); 
/* 302 */       if (newDefinition != null)
/* 303 */         msgs = ((InternalEObject)newDefinition).eInverseAdd((InternalEObject)this, -12, null, msgs); 
/* 304 */       msgs = basicSetDefinition(newDefinition, msgs);
/* 305 */       if (msgs != null) msgs.dispatch();
/*     */     
/* 307 */     } else if (eNotificationRequired()) {
/* 308 */       eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 11, newDefinition, newDefinition));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<Variable> getVariable() {
/* 318 */     if (this.variable == null)
/*     */     {
/* 320 */       this.variable = (EList<Variable>)new EObjectContainmentWithInverseEList(Variable.class, (InternalEObject)this, 12, 4);
/*     */     }
/* 322 */     return this.variable;
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
/* 334 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 337 */         if (this.port != null)
/* 338 */           msgs = ((InternalEObject)this.port).eInverseRemove((InternalEObject)this, -8, null, msgs); 
/* 339 */         return basicSetPort((Port)otherEnd, msgs);
/*     */       case 8:
/* 341 */         return ((InternalEList)getInteractionSpecification()).basicAdd(otherEnd, msgs);
/*     */       case 9:
/* 343 */         return ((InternalEList)getPortParameter()).basicAdd(otherEnd, msgs);
/*     */       case 10:
/* 345 */         if (this.portDefinition != null)
/* 346 */           msgs = ((InternalEObject)this.portDefinition).eInverseRemove((InternalEObject)this, -11, null, msgs); 
/* 347 */         return basicSetPortDefinition((PortDefinition)otherEnd, msgs);
/*     */       case 12:
/* 349 */         return ((InternalEList)getVariable()).basicAdd(otherEnd, msgs);
/*     */     } 
/* 351 */     return super.eInverseAdd(otherEnd, featureID, msgs);
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
/* 362 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 365 */         return basicSetPort((Port)null, msgs);
/*     */       case 8:
/* 367 */         return ((InternalEList)getInteractionSpecification()).basicRemove(otherEnd, msgs);
/*     */       case 9:
/* 369 */         return ((InternalEList)getPortParameter()).basicRemove(otherEnd, msgs);
/*     */       case 10:
/* 371 */         return basicSetPortDefinition((PortDefinition)null, msgs);
/*     */       case 11:
/* 373 */         return basicSetDefinition((PortExpression)null, msgs);
/*     */       case 12:
/* 375 */         return ((InternalEList)getVariable()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 377 */     return super.eInverseRemove(otherEnd, featureID, msgs);
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
/* 388 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 391 */         return getPort();
/*     */       case 8:
/* 393 */         return getInteractionSpecification();
/*     */       case 9:
/* 395 */         return getPortParameter();
/*     */       case 10:
/* 397 */         return getPortDefinition();
/*     */       case 11:
/* 399 */         return getDefinition();
/*     */       case 12:
/* 401 */         return getVariable();
/*     */     } 
/* 403 */     return super.eGet(featureID, resolve, coreType);
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
/* 415 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 418 */         setPort((Port)newValue);
/*     */         return;
/*     */       case 8:
/* 421 */         getInteractionSpecification().clear();
/* 422 */         getInteractionSpecification().addAll((Collection)newValue);
/*     */         return;
/*     */       case 9:
/* 425 */         getPortParameter().clear();
/* 426 */         getPortParameter().addAll((Collection)newValue);
/*     */         return;
/*     */       case 10:
/* 429 */         setPortDefinition((PortDefinition)newValue);
/*     */         return;
/*     */       case 11:
/* 432 */         setDefinition((PortExpression)newValue);
/*     */         return;
/*     */       case 12:
/* 435 */         getVariable().clear();
/* 436 */         getVariable().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 439 */     super.eSet(featureID, newValue);
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
/* 450 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 453 */         setPort((Port)null);
/*     */         return;
/*     */       case 8:
/* 456 */         getInteractionSpecification().clear();
/*     */         return;
/*     */       case 9:
/* 459 */         getPortParameter().clear();
/*     */         return;
/*     */       case 10:
/* 462 */         setPortDefinition((PortDefinition)null);
/*     */         return;
/*     */       case 11:
/* 465 */         setDefinition((PortExpression)null);
/*     */         return;
/*     */       case 12:
/* 468 */         getVariable().clear();
/*     */         return;
/*     */     } 
/* 471 */     super.eUnset(featureID);
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
/* 482 */     switch (featureID) {
/*     */       
/*     */       case 7:
/* 485 */         return (this.port != null);
/*     */       case 8:
/* 487 */         return (this.interactionSpecification != null && !this.interactionSpecification.isEmpty());
/*     */       case 9:
/* 489 */         return (this.portParameter != null && !this.portParameter.isEmpty());
/*     */       case 10:
/* 491 */         return (this.portDefinition != null);
/*     */       case 11:
/* 493 */         return (this.definition != null);
/*     */       case 12:
/* 495 */         return (this.variable != null && !this.variable.isEmpty());
/*     */     } 
/* 497 */     return super.eIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\ConnectorTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */