/*     */ package ujf.verimag.bip.Core.PortExpressions.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACNaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AINaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchro;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchroNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortExpressionsSwitch<T>
/*     */ {
/*     */   protected static PortExpressionsPackage modelPackage;
/*     */   
/*     */   public PortExpressionsSwitch() {
/*  47 */     if (modelPackage == null)
/*     */     {
/*  49 */       modelPackage = PortExpressionsPackage.eINSTANCE;
/*     */     }
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
/*     */   public T doSwitch(EObject theEObject) {
/*  62 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*     */   protected T doSwitch(EClass theEClass, EObject theEObject) {
/*  74 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  76 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  80 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  81 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject); } protected T doSwitch(int classifierID, EObject theEObject) { ACNaryExpression acNaryExpression;
/*     */     ACExpression acExpression;
/*     */     PortExpression portExpression;
/*     */     ACFusionNeutral acFusionNeutral;
/*     */     ACUnionNeutral acUnionNeutral;
/*     */     AINaryExpression aiNaryExpression;
/*     */     AIExpression aiExpression;
/*     */     ACFusion acFusion;
/*     */     AIUnionNeutral aiUnionNeutral;
/*     */     AISynchroNeutral aiSynchroNeutral;
/*     */     AIUnion aiUnion;
/*     */     ACTyping acTyping;
/*     */     AISynchro aiSynchro;
/*     */     ACUnion acUnion;
/*     */     PortReference portReference;
/*     */     T result;
/*  97 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 101 */         acNaryExpression = (ACNaryExpression)theEObject;
/* 102 */         result = caseACNaryExpression(acNaryExpression);
/* 103 */         if (result == null) result = caseACExpression((ACExpression)acNaryExpression); 
/* 104 */         if (result == null) result = casePortExpression((PortExpression)acNaryExpression); 
/* 105 */         if (result == null) result = defaultCase(theEObject); 
/* 106 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 110 */         acExpression = (ACExpression)theEObject;
/* 111 */         result = caseACExpression(acExpression);
/* 112 */         if (result == null) result = casePortExpression((PortExpression)acExpression); 
/* 113 */         if (result == null) result = defaultCase(theEObject); 
/* 114 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 118 */         portExpression = (PortExpression)theEObject;
/* 119 */         result = casePortExpression(portExpression);
/* 120 */         if (result == null) result = defaultCase(theEObject); 
/* 121 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 125 */         acFusionNeutral = (ACFusionNeutral)theEObject;
/* 126 */         result = caseACFusionNeutral(acFusionNeutral);
/* 127 */         if (result == null) result = caseACExpression((ACExpression)acFusionNeutral); 
/* 128 */         if (result == null) result = casePortExpression((PortExpression)acFusionNeutral); 
/* 129 */         if (result == null) result = defaultCase(theEObject); 
/* 130 */         return result;
/*     */ 
/*     */       
/*     */       case 4:
/* 134 */         acUnionNeutral = (ACUnionNeutral)theEObject;
/* 135 */         result = caseACUnionNeutral(acUnionNeutral);
/* 136 */         if (result == null) result = caseACExpression((ACExpression)acUnionNeutral); 
/* 137 */         if (result == null) result = casePortExpression((PortExpression)acUnionNeutral); 
/* 138 */         if (result == null) result = defaultCase(theEObject); 
/* 139 */         return result;
/*     */ 
/*     */       
/*     */       case 5:
/* 143 */         aiNaryExpression = (AINaryExpression)theEObject;
/* 144 */         result = caseAINaryExpression(aiNaryExpression);
/* 145 */         if (result == null) result = caseAIExpression((AIExpression)aiNaryExpression); 
/* 146 */         if (result == null) result = casePortExpression((PortExpression)aiNaryExpression); 
/* 147 */         if (result == null) result = defaultCase(theEObject); 
/* 148 */         return result;
/*     */ 
/*     */       
/*     */       case 6:
/* 152 */         aiExpression = (AIExpression)theEObject;
/* 153 */         result = caseAIExpression(aiExpression);
/* 154 */         if (result == null) result = casePortExpression((PortExpression)aiExpression); 
/* 155 */         if (result == null) result = defaultCase(theEObject); 
/* 156 */         return result;
/*     */ 
/*     */       
/*     */       case 7:
/* 160 */         acFusion = (ACFusion)theEObject;
/* 161 */         result = caseACFusion(acFusion);
/* 162 */         if (result == null) result = caseACNaryExpression((ACNaryExpression)acFusion); 
/* 163 */         if (result == null) result = caseACExpression((ACExpression)acFusion); 
/* 164 */         if (result == null) result = casePortExpression((PortExpression)acFusion); 
/* 165 */         if (result == null) result = defaultCase(theEObject); 
/* 166 */         return result;
/*     */ 
/*     */       
/*     */       case 8:
/* 170 */         aiUnionNeutral = (AIUnionNeutral)theEObject;
/* 171 */         result = caseAIUnionNeutral(aiUnionNeutral);
/* 172 */         if (result == null) result = caseAIExpression((AIExpression)aiUnionNeutral); 
/* 173 */         if (result == null) result = casePortExpression((PortExpression)aiUnionNeutral); 
/* 174 */         if (result == null) result = defaultCase(theEObject); 
/* 175 */         return result;
/*     */ 
/*     */       
/*     */       case 9:
/* 179 */         aiSynchroNeutral = (AISynchroNeutral)theEObject;
/* 180 */         result = caseAISynchroNeutral(aiSynchroNeutral);
/* 181 */         if (result == null) result = caseAIExpression((AIExpression)aiSynchroNeutral); 
/* 182 */         if (result == null) result = casePortExpression((PortExpression)aiSynchroNeutral); 
/* 183 */         if (result == null) result = defaultCase(theEObject); 
/* 184 */         return result;
/*     */ 
/*     */       
/*     */       case 10:
/* 188 */         aiUnion = (AIUnion)theEObject;
/* 189 */         result = caseAIUnion(aiUnion);
/* 190 */         if (result == null) result = caseAINaryExpression((AINaryExpression)aiUnion); 
/* 191 */         if (result == null) result = caseAIExpression((AIExpression)aiUnion); 
/* 192 */         if (result == null) result = casePortExpression((PortExpression)aiUnion); 
/* 193 */         if (result == null) result = defaultCase(theEObject); 
/* 194 */         return result;
/*     */ 
/*     */       
/*     */       case 11:
/* 198 */         acTyping = (ACTyping)theEObject;
/* 199 */         result = caseACTyping(acTyping);
/* 200 */         if (result == null) result = caseACExpression((ACExpression)acTyping); 
/* 201 */         if (result == null) result = casePortExpression((PortExpression)acTyping); 
/* 202 */         if (result == null) result = defaultCase(theEObject); 
/* 203 */         return result;
/*     */ 
/*     */       
/*     */       case 12:
/* 207 */         aiSynchro = (AISynchro)theEObject;
/* 208 */         result = caseAISynchro(aiSynchro);
/* 209 */         if (result == null) result = caseAINaryExpression((AINaryExpression)aiSynchro); 
/* 210 */         if (result == null) result = caseAIExpression((AIExpression)aiSynchro); 
/* 211 */         if (result == null) result = casePortExpression((PortExpression)aiSynchro); 
/* 212 */         if (result == null) result = defaultCase(theEObject); 
/* 213 */         return result;
/*     */ 
/*     */       
/*     */       case 13:
/* 217 */         acUnion = (ACUnion)theEObject;
/* 218 */         result = caseACUnion(acUnion);
/* 219 */         if (result == null) result = caseACNaryExpression((ACNaryExpression)acUnion); 
/* 220 */         if (result == null) result = caseACExpression((ACExpression)acUnion); 
/* 221 */         if (result == null) result = casePortExpression((PortExpression)acUnion); 
/* 222 */         if (result == null) result = defaultCase(theEObject); 
/* 223 */         return result;
/*     */ 
/*     */       
/*     */       case 14:
/* 227 */         portReference = (PortReference)theEObject;
/* 228 */         result = casePortReference(portReference);
/* 229 */         if (result == null) result = caseACExpression((ACExpression)portReference); 
/* 230 */         if (result == null) result = caseAIExpression((AIExpression)portReference); 
/* 231 */         if (result == null) result = casePortExpression((PortExpression)portReference); 
/* 232 */         if (result == null) result = defaultCase(theEObject); 
/* 233 */         return result;
/*     */     } 
/* 235 */     return defaultCase(theEObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T caseACNaryExpression(ACNaryExpression object) {
/* 252 */     return null;
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
/*     */   public T caseACExpression(ACExpression object) {
/* 268 */     return null;
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
/*     */   public T casePortExpression(PortExpression object) {
/* 284 */     return null;
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
/*     */   public T caseACFusionNeutral(ACFusionNeutral object) {
/* 300 */     return null;
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
/*     */   public T caseACUnionNeutral(ACUnionNeutral object) {
/* 316 */     return null;
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
/*     */   public T caseAINaryExpression(AINaryExpression object) {
/* 332 */     return null;
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
/*     */   public T caseAIExpression(AIExpression object) {
/* 348 */     return null;
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
/*     */   public T caseACFusion(ACFusion object) {
/* 364 */     return null;
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
/*     */   public T caseAIUnionNeutral(AIUnionNeutral object) {
/* 380 */     return null;
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
/*     */   public T caseAISynchroNeutral(AISynchroNeutral object) {
/* 396 */     return null;
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
/*     */   public T caseAIUnion(AIUnion object) {
/* 412 */     return null;
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
/*     */   public T caseACTyping(ACTyping object) {
/* 428 */     return null;
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
/*     */   public T caseAISynchro(AISynchro object) {
/* 444 */     return null;
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
/*     */   public T caseACUnion(ACUnion object) {
/* 460 */     return null;
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
/*     */   public T casePortReference(PortReference object) {
/* 476 */     return null;
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
/*     */   public T defaultCase(EObject object) {
/* 492 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpression\\util\PortExpressionsSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */