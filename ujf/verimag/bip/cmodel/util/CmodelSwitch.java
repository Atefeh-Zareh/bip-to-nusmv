/*      */ package ujf.verimag.bip.cmodel.util;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import ujf.verimag.bip.cmodel.CAssignStm;
/*      */ import ujf.verimag.bip.cmodel.CBlock;
/*      */ import ujf.verimag.bip.cmodel.CBlockStm;
/*      */ import ujf.verimag.bip.cmodel.CBodyItem;
/*      */ import ujf.verimag.bip.cmodel.CCall;
/*      */ import ujf.verimag.bip.cmodel.CCallable;
/*      */ import ujf.verimag.bip.cmodel.CCaseItem;
/*      */ import ujf.verimag.bip.cmodel.CClass;
/*      */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*      */ import ujf.verimag.bip.cmodel.CConditionalStm;
/*      */ import ujf.verimag.bip.cmodel.CConstructor;
/*      */ import ujf.verimag.bip.cmodel.CCreator;
/*      */ import ujf.verimag.bip.cmodel.CData;
/*      */ import ujf.verimag.bip.cmodel.CEnumType;
/*      */ import ujf.verimag.bip.cmodel.CExpression;
/*      */ import ujf.verimag.bip.cmodel.CFor;
/*      */ import ujf.verimag.bip.cmodel.CFunction;
/*      */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*      */ import ujf.verimag.bip.cmodel.CHierarchy;
/*      */ import ujf.verimag.bip.cmodel.CIfStm;
/*      */ import ujf.verimag.bip.cmodel.CIndexed;
/*      */ import ujf.verimag.bip.cmodel.CItem;
/*      */ import ujf.verimag.bip.cmodel.CJump;
/*      */ import ujf.verimag.bip.cmodel.CLiteral;
/*      */ import ujf.verimag.bip.cmodel.CNavigation;
/*      */ import ujf.verimag.bip.cmodel.COperation;
/*      */ import ujf.verimag.bip.cmodel.CPointed;
/*      */ import ujf.verimag.bip.cmodel.CReturn;
/*      */ import ujf.verimag.bip.cmodel.CSimpleName;
/*      */ import ujf.verimag.bip.cmodel.CStm;
/*      */ import ujf.verimag.bip.cmodel.CStructured;
/*      */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*      */ import ujf.verimag.bip.cmodel.CText;
/*      */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*      */ import ujf.verimag.bip.cmodel.CTypedElement;
/*      */ import ujf.verimag.bip.cmodel.CWhileStm;
/*      */ 
/*      */ public class CmodelSwitch<T> {
/*      */   protected static CmodelPackage modelPackage;
/*      */   
/*      */   public CmodelSwitch() {
/*   45 */     if (modelPackage == null) {
/*   46 */       modelPackage = CmodelPackage.eINSTANCE;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T doSwitch(EObject theEObject) {
/*   58 */     return doSwitch(theEObject.eClass(), theEObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected T doSwitch(EClass theEClass, EObject theEObject) {
/*   69 */     if (theEClass.eContainer() == modelPackage) {
/*   70 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*      */     }
/*      */     
/*   73 */     EList<EClass> eList = theEClass.getESuperTypes();
/*   74 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject); } protected T doSwitch(int classifierID, EObject theEObject) { CStm cStm; CBlock cBlock; CConditionalStm cConditionalStm; CSwitchStm cSwitchStm; CWhileStm cWhileStm; CIfStm cIfStm; CExpression cExpression; CCaseItem cCaseItem; CJump cJump; CCall cCall; CAssignStm cAssignStm; CFunctionCall cFunctionCall; CCreator cCreator; CTypedElement cTypedElement; COperation cOperation; CTypeConvertion cTypeConvertion; CHierarchy cHierarchy; CSimpleName cSimpleName; CIndexed cIndexed; CStructured cStructured; CPointed cPointed; CLiteral cLiteral; CModule cModule; CInclude cInclude; CEnumType cEnumType; CClass cClass; CCallable cCallable; CData cData; CArgument cArgument;
/*      */     CInitParameter cInitParameter;
/*      */     CFunction cFunction;
/*      */     CConstructor cConstructor;
/*      */     CItem cItem;
/*      */     CText cText;
/*      */     CBodyItem cBodyItem;
/*      */     CBlockStm cBlockStm;
/*      */     CNavigation cNavigation;
/*      */     CReturn cReturn;
/*      */     CInitialization cInitialization;
/*      */     CFor cFor;
/*      */     CConditionalExpression cConditionalExpression;
/*      */     CHeaderText cHeaderText;
/*      */     T result;
/*   89 */     switch (classifierID) {
/*      */       case 0:
/*   91 */         cStm = (CStm)theEObject;
/*   92 */         result = caseCStm(cStm);
/*   93 */         if (result == null) result = caseCBodyItem((CBodyItem)cStm); 
/*   94 */         if (result == null) result = defaultCase(theEObject); 
/*   95 */         return result;
/*      */       
/*      */       case 1:
/*   98 */         cBlock = (CBlock)theEObject;
/*   99 */         result = caseCBlock(cBlock);
/*  100 */         if (result == null) result = defaultCase(theEObject); 
/*  101 */         return result;
/*      */       
/*      */       case 2:
/*  104 */         cConditionalStm = (CConditionalStm)theEObject;
/*  105 */         result = caseCConditionalStm(cConditionalStm);
/*  106 */         if (result == null) result = caseCStm((CStm)cConditionalStm); 
/*  107 */         if (result == null) result = caseCBodyItem((CBodyItem)cConditionalStm); 
/*  108 */         if (result == null) result = defaultCase(theEObject); 
/*  109 */         return result;
/*      */       
/*      */       case 3:
/*  112 */         cSwitchStm = (CSwitchStm)theEObject;
/*  113 */         result = caseCSwitchStm(cSwitchStm);
/*  114 */         if (result == null) result = caseCStm((CStm)cSwitchStm); 
/*  115 */         if (result == null) result = caseCBodyItem((CBodyItem)cSwitchStm); 
/*  116 */         if (result == null) result = defaultCase(theEObject); 
/*  117 */         return result;
/*      */       
/*      */       case 4:
/*  120 */         cWhileStm = (CWhileStm)theEObject;
/*  121 */         result = caseCWhileStm(cWhileStm);
/*  122 */         if (result == null) result = caseCBlock((CBlock)cWhileStm); 
/*  123 */         if (result == null) result = caseCConditionalStm((CConditionalStm)cWhileStm); 
/*  124 */         if (result == null) result = caseCStm((CStm)cWhileStm); 
/*  125 */         if (result == null) result = caseCBodyItem((CBodyItem)cWhileStm); 
/*  126 */         if (result == null) result = defaultCase(theEObject); 
/*  127 */         return result;
/*      */       
/*      */       case 5:
/*  130 */         cIfStm = (CIfStm)theEObject;
/*  131 */         result = caseCIfStm(cIfStm);
/*  132 */         if (result == null) result = caseCConditionalStm((CConditionalStm)cIfStm); 
/*  133 */         if (result == null) result = caseCStm((CStm)cIfStm); 
/*  134 */         if (result == null) result = caseCBodyItem((CBodyItem)cIfStm); 
/*  135 */         if (result == null) result = defaultCase(theEObject); 
/*  136 */         return result;
/*      */       
/*      */       case 6:
/*  139 */         cExpression = (CExpression)theEObject;
/*  140 */         result = caseCExpression(cExpression);
/*  141 */         if (result == null) result = caseCStm((CStm)cExpression); 
/*  142 */         if (result == null) result = caseCBodyItem((CBodyItem)cExpression); 
/*  143 */         if (result == null) result = defaultCase(theEObject); 
/*  144 */         return result;
/*      */       
/*      */       case 7:
/*  147 */         cCaseItem = (CCaseItem)theEObject;
/*  148 */         result = caseCCaseItem(cCaseItem);
/*  149 */         if (result == null) result = caseCBlock((CBlock)cCaseItem); 
/*  150 */         if (result == null) result = defaultCase(theEObject); 
/*  151 */         return result;
/*      */       
/*      */       case 8:
/*  154 */         cJump = (CJump)theEObject;
/*  155 */         result = caseCJump(cJump);
/*  156 */         if (result == null) result = caseCStm((CStm)cJump); 
/*  157 */         if (result == null) result = caseCBodyItem((CBodyItem)cJump); 
/*  158 */         if (result == null) result = defaultCase(theEObject); 
/*  159 */         return result;
/*      */       
/*      */       case 9:
/*  162 */         cCall = (CCall)theEObject;
/*  163 */         result = caseCCall(cCall);
/*  164 */         if (result == null) result = caseCExpression((CExpression)cCall); 
/*  165 */         if (result == null) result = caseCStm((CStm)cCall); 
/*  166 */         if (result == null) result = caseCBodyItem((CBodyItem)cCall); 
/*  167 */         if (result == null) result = defaultCase(theEObject); 
/*  168 */         return result;
/*      */       
/*      */       case 10:
/*  171 */         cAssignStm = (CAssignStm)theEObject;
/*  172 */         result = caseCAssignStm(cAssignStm);
/*  173 */         if (result == null) result = caseCStm((CStm)cAssignStm); 
/*  174 */         if (result == null) result = caseCBodyItem((CBodyItem)cAssignStm); 
/*  175 */         if (result == null) result = defaultCase(theEObject); 
/*  176 */         return result;
/*      */       
/*      */       case 11:
/*  179 */         cFunctionCall = (CFunctionCall)theEObject;
/*  180 */         result = caseCFunctionCall(cFunctionCall);
/*  181 */         if (result == null) result = caseCCall((CCall)cFunctionCall); 
/*  182 */         if (result == null) result = caseCTypedElement((CTypedElement)cFunctionCall); 
/*  183 */         if (result == null) result = caseCExpression((CExpression)cFunctionCall); 
/*  184 */         if (result == null) result = caseCStm((CStm)cFunctionCall); 
/*  185 */         if (result == null) result = caseCBodyItem((CBodyItem)cFunctionCall); 
/*  186 */         if (result == null) result = defaultCase(theEObject); 
/*  187 */         return result;
/*      */       
/*      */       case 12:
/*  190 */         cCreator = (CCreator)theEObject;
/*  191 */         result = caseCCreator(cCreator);
/*  192 */         if (result == null) result = caseCCall((CCall)cCreator); 
/*  193 */         if (result == null) result = caseCTypedElement((CTypedElement)cCreator); 
/*  194 */         if (result == null) result = caseCExpression((CExpression)cCreator); 
/*  195 */         if (result == null) result = caseCStm((CStm)cCreator); 
/*  196 */         if (result == null) result = caseCBodyItem((CBodyItem)cCreator); 
/*  197 */         if (result == null) result = defaultCase(theEObject); 
/*  198 */         return result;
/*      */       
/*      */       case 13:
/*  201 */         cTypedElement = (CTypedElement)theEObject;
/*  202 */         result = caseCTypedElement(cTypedElement);
/*  203 */         if (result == null) result = defaultCase(theEObject); 
/*  204 */         return result;
/*      */       
/*      */       case 14:
/*  207 */         cOperation = (COperation)theEObject;
/*  208 */         result = caseCOperation(cOperation);
/*  209 */         if (result == null) result = caseCExpression((CExpression)cOperation); 
/*  210 */         if (result == null) result = caseCStm((CStm)cOperation); 
/*  211 */         if (result == null) result = caseCBodyItem((CBodyItem)cOperation); 
/*  212 */         if (result == null) result = defaultCase(theEObject); 
/*  213 */         return result;
/*      */       
/*      */       case 15:
/*  216 */         cTypeConvertion = (CTypeConvertion)theEObject;
/*  217 */         result = caseCTypeConvertion(cTypeConvertion);
/*  218 */         if (result == null) result = caseCExpression((CExpression)cTypeConvertion); 
/*  219 */         if (result == null) result = caseCTypedElement((CTypedElement)cTypeConvertion); 
/*  220 */         if (result == null) result = caseCStm((CStm)cTypeConvertion); 
/*  221 */         if (result == null) result = caseCBodyItem((CBodyItem)cTypeConvertion); 
/*  222 */         if (result == null) result = defaultCase(theEObject); 
/*  223 */         return result;
/*      */       
/*      */       case 16:
/*  226 */         cHierarchy = (CHierarchy)theEObject;
/*  227 */         result = caseCHierarchy(cHierarchy);
/*  228 */         if (result == null) result = caseCNavigation((CNavigation)cHierarchy); 
/*  229 */         if (result == null) result = caseCExpression((CExpression)cHierarchy); 
/*  230 */         if (result == null) result = caseCStm((CStm)cHierarchy); 
/*  231 */         if (result == null) result = caseCBodyItem((CBodyItem)cHierarchy); 
/*  232 */         if (result == null) result = defaultCase(theEObject); 
/*  233 */         return result;
/*      */       
/*      */       case 17:
/*  236 */         cSimpleName = (CSimpleName)theEObject;
/*  237 */         result = caseCSimpleName(cSimpleName);
/*  238 */         if (result == null) result = caseCExpression((CExpression)cSimpleName); 
/*  239 */         if (result == null) result = caseCStm((CStm)cSimpleName); 
/*  240 */         if (result == null) result = caseCBodyItem((CBodyItem)cSimpleName); 
/*  241 */         if (result == null) result = defaultCase(theEObject); 
/*  242 */         return result;
/*      */       
/*      */       case 18:
/*  245 */         cIndexed = (CIndexed)theEObject;
/*  246 */         result = caseCIndexed(cIndexed);
/*  247 */         if (result == null) result = caseCNavigation((CNavigation)cIndexed); 
/*  248 */         if (result == null) result = caseCExpression((CExpression)cIndexed); 
/*  249 */         if (result == null) result = caseCStm((CStm)cIndexed); 
/*  250 */         if (result == null) result = caseCBodyItem((CBodyItem)cIndexed); 
/*  251 */         if (result == null) result = defaultCase(theEObject); 
/*  252 */         return result;
/*      */       
/*      */       case 19:
/*  255 */         cStructured = (CStructured)theEObject;
/*  256 */         result = caseCStructured(cStructured);
/*  257 */         if (result == null) result = caseCHierarchy((CHierarchy)cStructured); 
/*  258 */         if (result == null) result = caseCNavigation((CNavigation)cStructured); 
/*  259 */         if (result == null) result = caseCExpression((CExpression)cStructured); 
/*  260 */         if (result == null) result = caseCStm((CStm)cStructured); 
/*  261 */         if (result == null) result = caseCBodyItem((CBodyItem)cStructured); 
/*  262 */         if (result == null) result = defaultCase(theEObject); 
/*  263 */         return result;
/*      */       
/*      */       case 20:
/*  266 */         cPointed = (CPointed)theEObject;
/*  267 */         result = caseCPointed(cPointed);
/*  268 */         if (result == null) result = caseCHierarchy((CHierarchy)cPointed); 
/*  269 */         if (result == null) result = caseCNavigation((CNavigation)cPointed); 
/*  270 */         if (result == null) result = caseCExpression((CExpression)cPointed); 
/*  271 */         if (result == null) result = caseCStm((CStm)cPointed); 
/*  272 */         if (result == null) result = caseCBodyItem((CBodyItem)cPointed); 
/*  273 */         if (result == null) result = defaultCase(theEObject); 
/*  274 */         return result;
/*      */       
/*      */       case 21:
/*  277 */         cLiteral = (CLiteral)theEObject;
/*  278 */         result = caseCLiteral(cLiteral);
/*  279 */         if (result == null) result = caseCExpression((CExpression)cLiteral); 
/*  280 */         if (result == null) result = caseCStm((CStm)cLiteral); 
/*  281 */         if (result == null) result = caseCBodyItem((CBodyItem)cLiteral); 
/*  282 */         if (result == null) result = defaultCase(theEObject); 
/*  283 */         return result;
/*      */       
/*      */       case 22:
/*  286 */         cModule = (CModule)theEObject;
/*  287 */         result = caseCModule(cModule);
/*  288 */         if (result == null) result = defaultCase(theEObject); 
/*  289 */         return result;
/*      */       
/*      */       case 23:
/*  292 */         cInclude = (CInclude)theEObject;
/*  293 */         result = caseCInclude(cInclude);
/*  294 */         if (result == null) result = defaultCase(theEObject); 
/*  295 */         return result;
/*      */       
/*      */       case 24:
/*  298 */         cEnumType = (CEnumType)theEObject;
/*  299 */         result = caseCEnumType(cEnumType);
/*  300 */         if (result == null) result = caseCTypedElement((CTypedElement)cEnumType); 
/*  301 */         if (result == null) result = caseCItem((CItem)cEnumType); 
/*  302 */         if (result == null) result = defaultCase(theEObject); 
/*  303 */         return result;
/*      */       
/*      */       case 25:
/*  306 */         cClass = (CClass)theEObject;
/*  307 */         result = caseCClass(cClass);
/*  308 */         if (result == null) result = caseCTypedElement((CTypedElement)cClass); 
/*  309 */         if (result == null) result = caseCItem((CItem)cClass); 
/*  310 */         if (result == null) result = defaultCase(theEObject); 
/*  311 */         return result;
/*      */       
/*      */       case 26:
/*  314 */         cCallable = (CCallable)theEObject;
/*  315 */         result = caseCCallable(cCallable);
/*  316 */         if (result == null) result = caseCItem((CItem)cCallable); 
/*  317 */         if (result == null) result = caseCTypedElement((CTypedElement)cCallable); 
/*  318 */         if (result == null) result = caseCBlock((CBlock)cCallable); 
/*  319 */         if (result == null) result = defaultCase(theEObject); 
/*  320 */         return result;
/*      */       
/*      */       case 27:
/*  323 */         cData = (CData)theEObject;
/*  324 */         result = caseCData(cData);
/*  325 */         if (result == null) result = caseCTypedElement((CTypedElement)cData); 
/*  326 */         if (result == null) result = caseCItem((CItem)cData); 
/*  327 */         if (result == null) result = caseCBodyItem((CBodyItem)cData); 
/*  328 */         if (result == null) result = defaultCase(theEObject); 
/*  329 */         return result;
/*      */       
/*      */       case 28:
/*  332 */         cArgument = (CArgument)theEObject;
/*  333 */         result = caseCArgument(cArgument);
/*  334 */         if (result == null) result = caseCTypedElement((CTypedElement)cArgument); 
/*  335 */         if (result == null) result = defaultCase(theEObject); 
/*  336 */         return result;
/*      */       
/*      */       case 29:
/*  339 */         cInitParameter = (CInitParameter)theEObject;
/*  340 */         result = caseCInitParameter(cInitParameter);
/*  341 */         if (result == null) result = defaultCase(theEObject); 
/*  342 */         return result;
/*      */       
/*      */       case 30:
/*  345 */         cFunction = (CFunction)theEObject;
/*  346 */         result = caseCFunction(cFunction);
/*  347 */         if (result == null) result = caseCCallable((CCallable)cFunction); 
/*  348 */         if (result == null) result = caseCItem((CItem)cFunction); 
/*  349 */         if (result == null) result = caseCTypedElement((CTypedElement)cFunction); 
/*  350 */         if (result == null) result = caseCBlock((CBlock)cFunction); 
/*  351 */         if (result == null) result = defaultCase(theEObject); 
/*  352 */         return result;
/*      */       
/*      */       case 31:
/*  355 */         cConstructor = (CConstructor)theEObject;
/*  356 */         result = caseCConstructor(cConstructor);
/*  357 */         if (result == null) result = caseCCallable((CCallable)cConstructor); 
/*  358 */         if (result == null) result = caseCItem((CItem)cConstructor); 
/*  359 */         if (result == null) result = caseCTypedElement((CTypedElement)cConstructor); 
/*  360 */         if (result == null) result = caseCBlock((CBlock)cConstructor); 
/*  361 */         if (result == null) result = defaultCase(theEObject); 
/*  362 */         return result;
/*      */       
/*      */       case 32:
/*  365 */         cItem = (CItem)theEObject;
/*  366 */         result = caseCItem(cItem);
/*  367 */         if (result == null) result = defaultCase(theEObject); 
/*  368 */         return result;
/*      */       
/*      */       case 33:
/*  371 */         cText = (CText)theEObject;
/*  372 */         result = caseCText(cText);
/*  373 */         if (result == null) result = caseCStm((CStm)cText); 
/*  374 */         if (result == null) result = caseCItem((CItem)cText); 
/*  375 */         if (result == null) result = caseCBodyItem((CBodyItem)cText); 
/*  376 */         if (result == null) result = defaultCase(theEObject); 
/*  377 */         return result;
/*      */       
/*      */       case 34:
/*  380 */         cBodyItem = (CBodyItem)theEObject;
/*  381 */         result = caseCBodyItem(cBodyItem);
/*  382 */         if (result == null) result = defaultCase(theEObject); 
/*  383 */         return result;
/*      */       
/*      */       case 35:
/*  386 */         cBlockStm = (CBlockStm)theEObject;
/*  387 */         result = caseCBlockStm(cBlockStm);
/*  388 */         if (result == null) result = caseCBlock((CBlock)cBlockStm); 
/*  389 */         if (result == null) result = caseCStm((CStm)cBlockStm); 
/*  390 */         if (result == null) result = caseCBodyItem((CBodyItem)cBlockStm); 
/*  391 */         if (result == null) result = defaultCase(theEObject); 
/*  392 */         return result;
/*      */       
/*      */       case 36:
/*  395 */         cNavigation = (CNavigation)theEObject;
/*  396 */         result = caseCNavigation(cNavigation);
/*  397 */         if (result == null) result = caseCExpression((CExpression)cNavigation); 
/*  398 */         if (result == null) result = caseCStm((CStm)cNavigation); 
/*  399 */         if (result == null) result = caseCBodyItem((CBodyItem)cNavigation); 
/*  400 */         if (result == null) result = defaultCase(theEObject); 
/*  401 */         return result;
/*      */       
/*      */       case 37:
/*  404 */         cReturn = (CReturn)theEObject;
/*  405 */         result = caseCReturn(cReturn);
/*  406 */         if (result == null) result = caseCStm((CStm)cReturn); 
/*  407 */         if (result == null) result = caseCBodyItem((CBodyItem)cReturn); 
/*  408 */         if (result == null) result = defaultCase(theEObject); 
/*  409 */         return result;
/*      */       
/*      */       case 38:
/*  412 */         cInitialization = (CInitialization)theEObject;
/*  413 */         result = caseCInitialization(cInitialization);
/*  414 */         if (result == null) result = defaultCase(theEObject); 
/*  415 */         return result;
/*      */       
/*      */       case 39:
/*  418 */         cFor = (CFor)theEObject;
/*  419 */         result = caseCFor(cFor);
/*  420 */         if (result == null) result = caseCBlock((CBlock)cFor); 
/*  421 */         if (result == null) result = caseCStm((CStm)cFor); 
/*  422 */         if (result == null) result = caseCBodyItem((CBodyItem)cFor); 
/*  423 */         if (result == null) result = defaultCase(theEObject); 
/*  424 */         return result;
/*      */       
/*      */       case 40:
/*  427 */         cConditionalExpression = (CConditionalExpression)theEObject;
/*  428 */         result = caseCConditionalExpression(cConditionalExpression);
/*  429 */         if (result == null) result = caseCExpression((CExpression)cConditionalExpression); 
/*  430 */         if (result == null) result = caseCConditionalStm((CConditionalStm)cConditionalExpression); 
/*  431 */         if (result == null) result = caseCStm((CStm)cConditionalExpression); 
/*  432 */         if (result == null) result = caseCBodyItem((CBodyItem)cConditionalExpression); 
/*  433 */         if (result == null) result = defaultCase(theEObject); 
/*  434 */         return result;
/*      */       
/*      */       case 41:
/*  437 */         cHeaderText = (CHeaderText)theEObject;
/*  438 */         result = caseCHeaderText(cHeaderText);
/*  439 */         if (result == null) result = caseCItem((CItem)cHeaderText); 
/*  440 */         if (result == null) result = defaultCase(theEObject); 
/*  441 */         return result;
/*      */     } 
/*  443 */     return defaultCase(theEObject); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCStm(CStm object) {
/*  459 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCBlock(CBlock object) {
/*  474 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCConditionalStm(CConditionalStm object) {
/*  489 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCSwitchStm(CSwitchStm object) {
/*  504 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCWhileStm(CWhileStm object) {
/*  519 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCIfStm(CIfStm object) {
/*  534 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCExpression(CExpression object) {
/*  549 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCCaseItem(CCaseItem object) {
/*  564 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCJump(CJump object) {
/*  579 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCCall(CCall object) {
/*  594 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCAssignStm(CAssignStm object) {
/*  609 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCFunctionCall(CFunctionCall object) {
/*  624 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCCreator(CCreator object) {
/*  639 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCTypedElement(CTypedElement object) {
/*  654 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCOperation(COperation object) {
/*  669 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCTypeConvertion(CTypeConvertion object) {
/*  684 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCHierarchy(CHierarchy object) {
/*  699 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCSimpleName(CSimpleName object) {
/*  714 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCIndexed(CIndexed object) {
/*  729 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCStructured(CStructured object) {
/*  744 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCPointed(CPointed object) {
/*  759 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCLiteral(CLiteral object) {
/*  774 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCModule(CModule object) {
/*  789 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCInclude(CInclude object) {
/*  804 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCEnumType(CEnumType object) {
/*  819 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCClass(CClass object) {
/*  834 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCCallable(CCallable object) {
/*  849 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCData(CData object) {
/*  864 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCArgument(CArgument object) {
/*  879 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCInitParameter(CInitParameter object) {
/*  894 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCFunction(CFunction object) {
/*  909 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCConstructor(CConstructor object) {
/*  924 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCItem(CItem object) {
/*  939 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCText(CText object) {
/*  954 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCBodyItem(CBodyItem object) {
/*  969 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCBlockStm(CBlockStm object) {
/*  984 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCNavigation(CNavigation object) {
/*  999 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCReturn(CReturn object) {
/* 1014 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCInitialization(CInitialization object) {
/* 1029 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCFor(CFor object) {
/* 1044 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCConditionalExpression(CConditionalExpression object) {
/* 1059 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T caseCHeaderText(CHeaderText object) {
/* 1074 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T defaultCase(EObject object) {
/* 1089 */     return null;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cmode\\util\CmodelSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */