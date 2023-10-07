/*     */ package org.eclipse.emf.ecore.xmi.impl;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EStructuralFeature;
/*     */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*     */ import org.eclipse.emf.ecore.xmi.DOMHandler;
/*     */ import org.eclipse.emf.ecore.xmi.DOMHelper;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultDOMHandlerImpl
/*     */   implements DOMHandler, DOMHelper
/*     */ {
/*  38 */   protected final HashMap<Node, Object> nodeToObject = new HashMap<Node, Object>();
/*     */ 
/*     */   
/*  41 */   protected final HashMap<Node, EStructuralFeature> nodeToFeature = new HashMap<Node, EStructuralFeature>();
/*     */ 
/*     */   
/*  44 */   protected final HashMap<Node, EObject> nodeToContainer = new HashMap<Node, EObject>();
/*     */   
/*     */   protected ExtendedMetaData extendedMetaData;
/*     */   static final boolean DEBUG = false;
/*     */   
/*     */   void setExtendedMetaData(ExtendedMetaData extendedMetaData) {
/*  50 */     this.extendedMetaData = extendedMetaData;
/*     */   }
/*     */   
/*     */   public EObject getContainer(Node node) {
/*     */     Object o;
/*  55 */     short type = node.getNodeType();
/*  56 */     switch (type) {
/*     */ 
/*     */       
/*     */       case 1:
/*  60 */         o = this.nodeToObject.get(node);
/*  61 */         if (o != null && o instanceof EObject)
/*     */         {
/*  63 */           return ((EObject)o).eContainer();
/*     */         }
/*  65 */         return (EObject)this.nodeToObject.get(node.getParentNode());
/*     */ 
/*     */       
/*     */       case 3:
/*     */       case 4:
/*  70 */         o = this.nodeToContainer.get(node);
/*  71 */         if (o != null)
/*     */         {
/*  73 */           return (EObject)o;
/*     */         }
/*  75 */         return (EObject)this.nodeToObject.get(node.getParentNode().getParentNode());
/*     */       
/*     */       case 2:
/*  78 */         return (EObject)this.nodeToObject.get(((Attr)node).getOwnerElement());
/*     */     } 
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Node node) {
/*  89 */     Object value = this.nodeToObject.get(node);
/*  90 */     if (value == null)
/*     */     {
/*  92 */       if (node.getNodeType() == 3)
/*     */       {
/*  94 */         value = this.nodeToObject.get(node.getParentNode());
/*     */       }
/*     */     }
/*  97 */     return value;
/*     */   }
/*     */   public EStructuralFeature getEStructuralFeature(Node node) {
/*     */     EObject obj;
/*     */     EStructuralFeature feature;
/* 102 */     short type = node.getNodeType();
/* 103 */     switch (type) {
/*     */       
/*     */       case 1:
/* 106 */         return this.nodeToFeature.get(node);
/*     */       
/*     */       case 2:
/* 109 */         obj = (EObject)this.nodeToObject.get(((Attr)node).getOwnerElement());
/* 110 */         if (this.extendedMetaData == null)
/*     */         {
/* 112 */           return obj.eClass().getEStructuralFeature(node.getLocalName());
/*     */         }
/* 114 */         if (obj != null)
/*     */         {
/* 116 */           return this.extendedMetaData.getAttribute(obj.eClass(), node.getNamespaceURI(), node.getLocalName());
/*     */         }
/*     */ 
/*     */       
/*     */       case 3:
/*     */       case 4:
/* 122 */         feature = this.nodeToFeature.get(node);
/* 123 */         if (feature == null)
/*     */         {
/* 125 */           feature = this.nodeToFeature.get(node.getParentNode());
/*     */         }
/* 127 */         return feature;
/*     */     } 
/*     */     
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordValues(Node node, EObject container, EStructuralFeature feature, Object value) {
/* 136 */     debug(node, container, feature, value);
/*     */     
/* 138 */     short type = node.getNodeType();
/* 139 */     switch (type) {
/*     */ 
/*     */       
/*     */       case 1:
/* 143 */         this.nodeToFeature.put(node, feature);
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 148 */         if (value != null)
/*     */         {
/* 150 */           this.nodeToObject.put(node, value);
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 156 */         if (this.nodeToObject.get(node.getParentNode()) == value) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 164 */         this.nodeToFeature.put(node, feature);
/* 165 */         this.nodeToContainer.put(node, container);
/* 166 */         this.nodeToObject.put(node, value);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DOMHelper getDOMHelper() {
/* 173 */     return this;
/*     */   }
/*     */   
/*     */   private static final void debug(Node node, EObject container, EStructuralFeature feature, Object value) {}
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xmi\impl\DefaultDOMHandlerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */