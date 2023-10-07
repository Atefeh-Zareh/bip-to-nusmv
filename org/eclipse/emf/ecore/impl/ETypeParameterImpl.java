/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
/*     */ import org.eclipse.emf.common.notify.NotificationChain;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EGenericType;
/*     */ import org.eclipse.emf.ecore.ETypeParameter;
/*     */ import org.eclipse.emf.ecore.EcorePackage;
/*     */ import org.eclipse.emf.ecore.InternalEObject;
/*     */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*     */ import org.eclipse.emf.ecore.util.InternalEList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ETypeParameterImpl
/*     */   extends ENamedElementImpl
/*     */   implements ETypeParameter
/*     */ {
/*     */   protected EList<EGenericType> eBounds;
/*     */   protected Set<EGenericType> eGenericTypes;
/*     */   
/*     */   protected EClass eStaticClass() {
/*  90 */     return EcorePackage.Literals.ETYPE_PARAMETER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EList<EGenericType> getEBounds() {
/* 100 */     if (this.eBounds == null)
/*     */     {
/* 102 */       this.eBounds = 
/* 103 */         (EList<EGenericType>)new EObjectContainmentEList<EGenericType>(EGenericType.class, this, 2)
/*     */         {
/*     */           private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */           
/*     */           public NotificationChain inverseAdd(EGenericType object, NotificationChain notifications) {
/* 110 */             notifications = super.inverseAdd(object, notifications);
/* 111 */             synchronized (ETypeParameterImpl.this) {
/*     */ 
/*     */               
/* 114 */               Set<EGenericTypeImpl> eGenericTypes = (Set)ETypeParameterImpl.this.getEGenericTypes();
/* 115 */               for (EGenericTypeImpl eGenericType : eGenericTypes)
/*     */               {
/* 117 */                 notifications = eGenericType.setERawType(eGenericType.getErasure(ETypeParameterImpl.this), notifications);
/*     */               }
/*     */             } 
/* 120 */             return notifications;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public NotificationChain inverseRemove(EGenericType object, NotificationChain notifications) {
/* 126 */             notifications = super.inverseRemove(object, notifications);
/* 127 */             synchronized (ETypeParameterImpl.this) {
/*     */               
/* 129 */               Set<EGenericTypeImpl> eGenericTypes = (Set)ETypeParameterImpl.this.getEGenericTypes();
/* 130 */               for (EGenericTypeImpl eGenericType : eGenericTypes)
/*     */               {
/* 132 */                 notifications = eGenericType.setERawType(eGenericType.getErasure(ETypeParameterImpl.this), notifications);
/*     */               }
/*     */             } 
/* 135 */             return notifications;
/*     */           }
/*     */         };
/*     */     }
/* 139 */     return this.eBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Set<EGenericType> getEGenericTypes() {
/* 146 */     if (this.eGenericTypes == null)
/*     */     {
/* 148 */       this.eGenericTypes = (
/* 149 */         new WeakHashMap<EGenericType, Object>()
/*     */         {
/*     */           private WeakHashMap<EGenericType, Object> map()
/*     */           {
/* 153 */             return this;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public Set<EGenericType> keySet() {
/* 161 */             return 
/* 162 */               new AbstractSet<EGenericType>()
/*     */               {
/*     */                 
/*     */                 public Iterator<EGenericType> iterator()
/*     */                 {
/* 167 */                   final Iterator<Map.Entry<EGenericType, Object>> delegateIterator = ETypeParameterImpl.null.this.map().entrySet().iterator();
/* 168 */                   return 
/* 169 */                     new Iterator<EGenericType>()
/*     */                     {
/*     */                       public boolean hasNext()
/*     */                       {
/* 173 */                         return delegateIterator.hasNext();
/*     */                       }
/*     */ 
/*     */                       
/*     */                       public EGenericType next() {
/* 178 */                         return (EGenericType)((Map.Entry)delegateIterator.next()).getKey();
/*     */                       }
/*     */ 
/*     */                       
/*     */                       public void remove() {
/* 183 */                         delegateIterator.remove();
/*     */                       }
/*     */                     };
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public int size() {
/* 191 */                   return ETypeParameterImpl.null.this.map().size();
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public boolean contains(Object object) {
/* 197 */                   return ETypeParameterImpl.null.this.containsKey(object);
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public boolean add(EGenericType eGenericType) {
/* 203 */                   return (ETypeParameterImpl.null.this.map().put(eGenericType, "") == null);
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public boolean addAll(Collection<? extends EGenericType> eGenericTypes) {
/* 209 */                   boolean result = false;
/* 210 */                   for (EGenericType eGenericType : eGenericTypes) {
/*     */                     
/* 212 */                     if (ETypeParameterImpl.null.this.map().put(eGenericType, "") == null)
/*     */                     {
/* 214 */                       result = true;
/*     */                     }
/*     */                   } 
/* 217 */                   return result;
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public boolean remove(Object object) {
/* 223 */                   if (ETypeParameterImpl.null.this.containsKey(object)) {
/*     */                     
/* 225 */                     ETypeParameterImpl.null.this.map().remove(object);
/* 226 */                     return true;
/*     */                   } 
/*     */ 
/*     */                   
/* 230 */                   return false;
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void clear() {
/* 237 */                   ETypeParameterImpl.null.this.map().clear();
/*     */                 }
/*     */               };
/*     */           }
/* 241 */         }).keySet();
/*     */     }
/* 243 */     return this.eGenericTypes;
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
/* 254 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 257 */         return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
/*     */       case 2:
/* 259 */         return ((InternalEList)getEBounds()).basicRemove(otherEnd, msgs);
/*     */     } 
/* 261 */     return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
/* 272 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 275 */         return getEAnnotations();
/*     */       case 1:
/* 277 */         return getName();
/*     */       case 2:
/* 279 */         return getEBounds();
/*     */     } 
/* 281 */     return eDynamicGet(featureID, resolve, coreType);
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
/* 293 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 296 */         getEAnnotations().clear();
/* 297 */         getEAnnotations().addAll((Collection)newValue);
/*     */         return;
/*     */       case 1:
/* 300 */         setName((String)newValue);
/*     */         return;
/*     */       case 2:
/* 303 */         getEBounds().clear();
/* 304 */         getEBounds().addAll((Collection)newValue);
/*     */         return;
/*     */     } 
/* 307 */     eDynamicSet(featureID, newValue);
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
/* 318 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 321 */         getEAnnotations().clear();
/*     */         return;
/*     */       case 1:
/* 324 */         setName(NAME_EDEFAULT);
/*     */         return;
/*     */       case 2:
/* 327 */         getEBounds().clear();
/*     */         return;
/*     */     } 
/* 330 */     eDynamicUnset(featureID);
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
/* 341 */     switch (featureID) {
/*     */       
/*     */       case 0:
/* 344 */         return (this.eAnnotations != null && !this.eAnnotations.isEmpty());
/*     */       case 1:
/* 346 */         return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));
/*     */       case 2:
/* 348 */         return (this.eBounds != null && !this.eBounds.isEmpty());
/*     */     } 
/* 350 */     return eDynamicIsSet(featureID);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\ETypeParameterImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */