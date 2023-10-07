/*     */ package ujf.verimag.bip.Core.PortExpressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchro;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchroNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortExpressionsFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements PortExpressionsFactory
/*     */ {
/*     */   public static PortExpressionsFactory init() {
/*     */     try {
/*  38 */       PortExpressionsFactory thePortExpressionsFactory = (PortExpressionsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/PortExpressions.ecore");
/*  39 */       if (thePortExpressionsFactory != null)
/*     */       {
/*  41 */         return thePortExpressionsFactory;
/*     */       }
/*     */     }
/*  44 */     catch (Exception exception) {
/*     */       
/*  46 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  48 */     return new PortExpressionsFactoryImpl();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/*  70 */     switch (eClass.getClassifierID()) {
/*     */       case 3:
/*  72 */         return (EObject)createACFusionNeutral();
/*  73 */       case 4: return (EObject)createACUnionNeutral();
/*  74 */       case 7: return (EObject)createACFusion();
/*  75 */       case 8: return (EObject)createAIUnionNeutral();
/*  76 */       case 9: return (EObject)createAISynchroNeutral();
/*  77 */       case 10: return (EObject)createAIUnion();
/*  78 */       case 11: return (EObject)createACTyping();
/*  79 */       case 12: return (EObject)createAISynchro();
/*  80 */       case 13: return (EObject)createACUnion();
/*     */     } 
/*  82 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/*  94 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 15:
/*  97 */         return createACTypingKindFromString(eDataType, initialValue);
/*     */     } 
/*  99 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 111 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 15:
/* 114 */         return convertACTypingKindToString(eDataType, instanceValue);
/*     */     } 
/* 116 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACFusionNeutral createACFusionNeutral() {
/* 127 */     ACFusionNeutralImpl acFusionNeutral = new ACFusionNeutralImpl();
/* 128 */     return acFusionNeutral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACUnionNeutral createACUnionNeutral() {
/* 138 */     ACUnionNeutralImpl acUnionNeutral = new ACUnionNeutralImpl();
/* 139 */     return acUnionNeutral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACFusion createACFusion() {
/* 149 */     ACFusionImpl acFusion = new ACFusionImpl();
/* 150 */     return acFusion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AIUnionNeutral createAIUnionNeutral() {
/* 160 */     AIUnionNeutralImpl aiUnionNeutral = new AIUnionNeutralImpl();
/* 161 */     return aiUnionNeutral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AISynchroNeutral createAISynchroNeutral() {
/* 171 */     AISynchroNeutralImpl aiSynchroNeutral = new AISynchroNeutralImpl();
/* 172 */     return aiSynchroNeutral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AIUnion createAIUnion() {
/* 182 */     AIUnionImpl aiUnion = new AIUnionImpl();
/* 183 */     return aiUnion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACTyping createACTyping() {
/* 193 */     ACTypingImpl acTyping = new ACTypingImpl();
/* 194 */     return acTyping;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AISynchro createAISynchro() {
/* 204 */     AISynchroImpl aiSynchro = new AISynchroImpl();
/* 205 */     return aiSynchro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACUnion createACUnion() {
/* 215 */     ACUnionImpl acUnion = new ACUnionImpl();
/* 216 */     return acUnion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ACTypingKind createACTypingKindFromString(EDataType eDataType, String initialValue) {
/* 226 */     ACTypingKind result = ACTypingKind.get(initialValue);
/* 227 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 228 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertACTypingKindToString(EDataType eDataType, Object instanceValue) {
/* 238 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PortExpressionsPackage getPortExpressionsPackage() {
/* 248 */     return (PortExpressionsPackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static PortExpressionsPackage getPackage() {
/* 260 */     return PortExpressionsPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\impl\PortExpressionsFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */