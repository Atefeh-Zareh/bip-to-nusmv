/*     */ package epfl.risd.bip.nusmv;
/*     */ 
/*     */ import BIPTransformation.TransformationFunction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Behaviors.State;
/*     */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ 
/*     */ public class Test {
/*     */   public static void main(String[] args) {
/*  40 */     String testFile = "/home/saabw/bip/BIP.linux.x86/testing/arrays.bip";
/*  41 */     CompoundType ct = TransformationFunction.ParseBIPFile(testFile);
/*     */ 
/*     */     
/*  44 */     System.out.println("\nThe Module name is " + ct.getModule().getName());
/*     */ 
/*     */ 
/*     */     
/*  48 */     System.out.println("\nThe compund type name is " + ct.getName());
/*     */ 
/*     */ 
/*     */     
/*  52 */     System.out.println("\nThe connector types and names are: ");
/*  53 */     int numOfConnectors = ct.getConnector().size();
/*     */     
/*  55 */     for (int i = 0; i < numOfConnectors; i++) {
/*     */       
/*  57 */       Connector cnct = (Connector)ct.getConnector().get(i);
/*  58 */       System.out.println("Type: " + cnct.getType().getName() + ".\tName: " + cnct.getName());
/*     */       
/*  60 */       for (ActualPortParameter app : cnct.getActualPort()) {
/*     */         
/*  62 */         InnerPortReference ipr = (InnerPortReference)app;
/*     */         
/*  64 */         PartElementReference per = ipr.getTargetInstance();
/*     */         
/*  66 */         Part part = per.getTargetPart();
/*  67 */         System.out.print(String.valueOf(part.getName()) + ".");
/*     */         
/*  69 */         Port p = ipr.getTargetPort();
/*  70 */         System.out.println(p.getName());
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     System.out.println("\nThe atomic types and names are: ");
/*  78 */     int numOfAtom = ct.getSubcomponent().size();
/*     */     
/*  80 */     for (int j = 0; j < numOfAtom; j++) {
/*     */       
/*  82 */       Component comp = (Component)ct.getSubcomponent().get(j);
/*  83 */       AtomType at = (AtomType)comp.getType();
/*  84 */       System.out.print("Type: " + at.getName());
/*  85 */       System.out.println(".\tName: " + comp.getName());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  90 */     AtomType FirstLayer = (AtomType)((Component)ct.getSubcomponent().get(0)).getType();
/*  91 */     AtomType MidLayers = (AtomType)((Component)ct.getSubcomponent().get(8)).getType();
/*  92 */     AtomType LastLayer = (AtomType)((Component)ct.getSubcomponent().get(14)).getType();
/*     */     
/*  94 */     System.out.println("\nThe ports of the FirstLayer are: ");
/*  95 */     int numOfPortsFirst = FirstLayer.getPortDefinition().size();
/*  96 */     for (int k = 0; k < numOfPortsFirst; k++) {
/*     */       
/*  98 */       PortDefinition port = (PortDefinition)FirstLayer.getPortDefinition().get(k);
/*  99 */       System.out.println("Type: " + port.getType().getName() + ".    \tName: " + port.getName());
/*     */     } 
/*     */     
/* 102 */     System.out.println("\nThe ports of the MidLayers are: ");
/* 103 */     int numOfPortsMid = MidLayers.getPortDefinition().size();
/* 104 */     for (int m = 0; m < numOfPortsMid; m++) {
/*     */       
/* 106 */       PortDefinition port = (PortDefinition)MidLayers.getPortDefinition().get(m);
/* 107 */       System.out.println("Type: " + port.getType().getName() + ".    \tName: " + port.getName());
/*     */     } 
/*     */     
/* 110 */     System.out.println("\nThe ports of the LastLayer are: ");
/* 111 */     int numOfPortsLast = LastLayer.getPortDefinition().size();
/* 112 */     for (int n = 0; n < numOfPortsLast; n++) {
/*     */       
/* 114 */       PortDefinition port = (PortDefinition)LastLayer.getPortDefinition().get(n);
/* 115 */       System.out.println("Type: " + port.getType().getName() + ".    \tName: " + port.getName());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 120 */     PortType arrayPort = ((Port)FirstLayer.getPort().get(0)).getType();
/* 121 */     System.out.println("\nThe variables of the arrayPort port are: ");
/* 122 */     int numOfVarArray = arrayPort.getDataParameter().size();
/* 123 */     for (int i1 = 0; i1 < numOfVarArray; i1++) {
/*     */       
/* 125 */       OpaqueElement oe = (OpaqueElement)((DataParameter)arrayPort.getDataParameter().get(i1)).getType();
/* 126 */       System.out.println(String.valueOf(oe.getBody()) + " " + ((DataParameter)arrayPort.getDataParameter().get(i1)).getName());
/*     */     } 
/*     */     
/* 129 */     System.out.println(((Variable)((PortDefinition)FirstLayer.getPortDefinition().get(0)).getExposedVariable().get(0)).getName());
/*     */     
/* 131 */     PortType intPort = ((Port)MidLayers.getPort().get(0)).getType();
/* 132 */     System.out.println("\nThe variables of the intPort port are: ");
/* 133 */     int numOfVarInt = intPort.getDataParameter().size();
/* 134 */     for (int i2 = 0; i2 < numOfVarInt; i2++) {
/*     */       
/* 136 */       OpaqueElement oe = (OpaqueElement)((DataParameter)intPort.getDataParameter().get(i2)).getType();
/* 137 */       System.out.println(String.valueOf(oe.getBody()) + " " + ((DataParameter)intPort.getDataParameter().get(i2)).getName());
/*     */     } 
/*     */     
/* 140 */     System.out.println(((Variable)((PortDefinition)MidLayers.getPortDefinition().get(0)).getExposedVariable().get(0)).getName());
/*     */ 
/*     */ 
/*     */     
/* 144 */     System.out.println("\nThe data parameters of the FirstLayer are: ");
/* 145 */     int numOfDataParamsFirst = FirstLayer.getDataParameter().size();
/* 146 */     for (int i3 = 0; i3 < numOfDataParamsFirst; i3++) {
/*     */       
/* 148 */       OpaqueElement oe = (OpaqueElement)((DataParameter)FirstLayer.getDataParameter().get(i3)).getType();
/* 149 */       System.out.print(String.valueOf(oe.getBody()) + " ");
/* 150 */       System.out.println(((DataParameter)FirstLayer.getDataParameter().get(i3)).getName());
/*     */     } 
/*     */     
/* 153 */     System.out.println("\nThe data parameters of the MidLayers are: ");
/* 154 */     int numOfDataParamsMid = MidLayers.getDataParameter().size();
/* 155 */     for (int i4 = 0; i4 < numOfDataParamsMid; i4++) {
/*     */       
/* 157 */       OpaqueElement oe = (OpaqueElement)((DataParameter)MidLayers.getDataParameter().get(i4)).getType();
/* 158 */       System.out.print(String.valueOf(oe.getBody()) + " ");
/* 159 */       System.out.println(((DataParameter)MidLayers.getDataParameter().get(i4)).getName());
/*     */     } 
/*     */     
/* 162 */     System.out.println("\nThe data parameters of the LastLayer are: ");
/* 163 */     int numOfDataParamsLast = LastLayer.getDataParameter().size();
/* 164 */     for (int i5 = 0; i5 < numOfDataParamsLast; i5++) {
/*     */       
/* 166 */       OpaqueElement oe = (OpaqueElement)((DataParameter)LastLayer.getDataParameter().get(i5)).getType();
/* 167 */       System.out.print(String.valueOf(oe.getBody()) + " ");
/* 168 */       System.out.println(((DataParameter)LastLayer.getDataParameter().get(i5)).getName());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 173 */     System.out.println("\nThe data variables in the FirstLayer are: ");
/* 174 */     int numOfVarsFirst = FirstLayer.getVariable().size();
/* 175 */     for (int i6 = 0; i6 < numOfVarsFirst; i6++) {
/*     */       
/* 177 */       OpaqueElement oe = (OpaqueElement)((Variable)FirstLayer.getVariable().get(i6)).getType();
/* 178 */       System.out.print(String.valueOf(oe.getBody()) + " ");
/* 179 */       System.out.print(((Variable)FirstLayer.getVariable().get(i6)).getName());
/* 180 */       if (((Variable)FirstLayer.getVariable().get(i6)).getInitialValue() != null) System.out.print(" = "); 
/* 181 */       print_expression(((Variable)FirstLayer.getVariable().get(i6)).getInitialValue());
/* 182 */       System.out.println();
/*     */     } 
/*     */     
/* 185 */     System.out.println("\nThe data variables in the MidLayers are: ");
/* 186 */     int numOfVarsMid = MidLayers.getVariable().size();
/* 187 */     for (int i7 = 0; i7 < numOfVarsMid; i7++) {
/*     */       
/* 189 */       OpaqueElement oe = (OpaqueElement)((Variable)MidLayers.getVariable().get(i7)).getType();
/* 190 */       System.out.print(String.valueOf(oe.getBody()) + " ");
/* 191 */       System.out.print(((Variable)MidLayers.getVariable().get(i7)).getName());
/* 192 */       if (((Variable)MidLayers.getVariable().get(i7)).getInitialValue() != null) System.out.print(" = "); 
/* 193 */       print_expression(((Variable)MidLayers.getVariable().get(i7)).getInitialValue());
/* 194 */       System.out.println();
/*     */     } 
/*     */     
/* 197 */     System.out.println("\nThe data variables in the LastLayer are: ");
/* 198 */     int numOfVarsLast = LastLayer.getVariable().size();
/* 199 */     for (int i8 = 0; i8 < numOfVarsLast; i8++) {
/*     */       
/* 201 */       OpaqueElement oe = (OpaqueElement)((Variable)LastLayer.getVariable().get(i8)).getType();
/* 202 */       System.out.print(String.valueOf(oe.getBody()) + " ");
/* 203 */       System.out.print(((Variable)LastLayer.getVariable().get(i8)).getName());
/* 204 */       if (((Variable)LastLayer.getVariable().get(i8)).getInitialValue() != null) System.out.print(" = "); 
/* 205 */       print_expression(((Variable)LastLayer.getVariable().get(i8)).getInitialValue());
/* 206 */       System.out.println();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 211 */     System.out.println("\n--> The transitions in FirstLayer are: ");
/* 212 */     PetriNet pnFirst = (PetriNet)FirstLayer.getBehavior();
/*     */     
/* 214 */     System.out.println("initial to " + ((State)pnFirst.getInitialState().get(0)).getName());
/* 215 */     if (pnFirst.getInitialization() instanceof CompositeAction) {
/*     */       
/* 217 */       System.out.println("do {");
/* 218 */       CompositeAction ca = (CompositeAction)pnFirst.getInitialization();
/* 219 */       int numOfActions = ca.getContent().size();
/* 220 */       for (int i12 = 0; i12 < numOfActions; i12++) {
/*     */         
/* 222 */         Action act = (Action)ca.getContent().get(i12);
/* 223 */         print_action(act);
/*     */       } 
/* 225 */       System.out.println("}");
/*     */     } 
/*     */     
/* 228 */     int numOfTransitionsFirst = pnFirst.getTransition().size();
/* 229 */     for (int i9 = 0; i9 < numOfTransitionsFirst; i9++) {
/*     */       
/* 231 */       Transition t = (Transition)pnFirst.getTransition().get(i9);
/* 232 */       PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
/* 233 */       System.out.print("\non " + portDefRef.getTarget().getName());
/* 234 */       System.out.println(" from " + ((State)t.getOrigin().get(0)).getName() + " to " + ((State)t.getDestination().get(0)).getName());
/*     */       
/* 236 */       Expression exp = t.getGuard();
/* 237 */       System.out.print("provided ");
/* 238 */       print_expression(exp);
/* 239 */       System.out.println();
/*     */       
/* 241 */       if (t.getAction() != null && t.getAction() instanceof CompositeAction) {
/*     */         
/* 243 */         System.out.println("do {");
/* 244 */         CompositeAction ca = (CompositeAction)t.getAction();
/* 245 */         int numOfActions = ca.getContent().size();
/* 246 */         for (int i12 = 0; i12 < numOfActions; i12++) {
/*     */           
/* 248 */           Action act = (Action)ca.getContent().get(i12);
/* 249 */           print_action(act);
/*     */         } 
/* 251 */         System.out.println("}");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 257 */     System.out.println("\n--> The transitions in MidLayers are: ");
/* 258 */     PetriNet pnMid = (PetriNet)MidLayers.getBehavior();
/*     */     
/* 260 */     System.out.println("initial to " + ((State)pnMid.getInitialState().get(0)).getName());
/* 261 */     if (pnMid.getInitialization() instanceof CompositeAction) {
/*     */       
/* 263 */       System.out.println("do {");
/* 264 */       CompositeAction ca = (CompositeAction)pnMid.getInitialization();
/* 265 */       int numOfActions = ca.getContent().size();
/* 266 */       for (int i12 = 0; i12 < numOfActions; i12++) {
/*     */         
/* 268 */         Action act = (Action)ca.getContent().get(i12);
/* 269 */         print_action(act);
/*     */       } 
/* 271 */       System.out.println("}");
/*     */     } 
/*     */     
/* 274 */     int numOfTransitionsMid = pnMid.getTransition().size();
/* 275 */     for (int i10 = 0; i10 < numOfTransitionsMid; i10++) {
/*     */       
/* 277 */       Transition t = (Transition)pnMid.getTransition().get(i10);
/* 278 */       PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
/* 279 */       System.out.print("\non " + portDefRef.getTarget().getName());
/* 280 */       System.out.println(" from " + ((State)t.getOrigin().get(0)).getName() + " to " + ((State)t.getDestination().get(0)).getName());
/*     */       
/* 282 */       Expression exp = t.getGuard();
/* 283 */       System.out.print("provided ");
/* 284 */       print_expression(exp);
/* 285 */       System.out.println();
/*     */       
/* 287 */       if (t.getAction() != null && t.getAction() instanceof CompositeAction) {
/*     */         
/* 289 */         System.out.println("do {");
/* 290 */         CompositeAction ca = (CompositeAction)t.getAction();
/* 291 */         int numOfActions = ca.getContent().size();
/* 292 */         for (int i12 = 0; i12 < numOfActions; i12++) {
/*     */           
/* 294 */           Action act = (Action)ca.getContent().get(i12);
/* 295 */           print_action(act);
/*     */         } 
/* 297 */         System.out.println("}");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 303 */     System.out.println("\n--> The transitions in LastLayer are: ");
/* 304 */     PetriNet pnLast = (PetriNet)LastLayer.getBehavior();
/*     */     
/* 306 */     System.out.println("initial to " + ((State)pnLast.getInitialState().get(0)).getName());
/* 307 */     if (pnLast.getInitialization() instanceof CompositeAction) {
/*     */       
/* 309 */       System.out.println("do {");
/* 310 */       CompositeAction ca = (CompositeAction)pnLast.getInitialization();
/* 311 */       int numOfActions = ca.getContent().size();
/* 312 */       for (int i12 = 0; i12 < numOfActions; i12++) {
/*     */         
/* 314 */         Action act = (Action)ca.getContent().get(i12);
/* 315 */         print_action(act);
/*     */       } 
/* 317 */       System.out.println("}");
/*     */     } 
/*     */     
/* 320 */     int numOfTransitionsLast = pnLast.getTransition().size();
/* 321 */     for (int i11 = 0; i11 < numOfTransitionsLast; i11++) {
/*     */       
/* 323 */       Transition t = (Transition)pnLast.getTransition().get(i11);
/* 324 */       PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
/* 325 */       System.out.print("\non " + portDefRef.getTarget().getName());
/* 326 */       System.out.println(" from " + ((State)t.getOrigin().get(0)).getName() + " to " + ((State)t.getDestination().get(0)).getName());
/*     */       
/* 328 */       Expression exp = t.getGuard();
/* 329 */       System.out.print("provided ");
/* 330 */       print_expression(exp);
/* 331 */       System.out.println();
/*     */       
/* 333 */       if (t.getAction() != null && t.getAction() instanceof CompositeAction) {
/*     */         
/* 335 */         System.out.println("do {");
/* 336 */         CompositeAction ca = (CompositeAction)t.getAction();
/* 337 */         int numOfActions = ca.getContent().size();
/* 338 */         for (int i12 = 0; i12 < numOfActions; i12++) {
/*     */           
/* 340 */           Action act = (Action)ca.getContent().get(i12);
/* 341 */           print_action(act);
/*     */         } 
/* 343 */         System.out.println("}");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void print_action(Action act) {
/* 350 */     if (act instanceof AssignmentAction) {
/*     */       
/* 352 */       AssignmentAction assignAct = (AssignmentAction)act;
/* 353 */       DataReference dataRef = assignAct.getAssignedTarget();
/* 354 */       if (dataRef instanceof VariableReference) {
/*     */         
/* 356 */         VariableReference varRef = (VariableReference)dataRef;
/* 357 */         Variable var = varRef.getTargetVariable();
/* 358 */         System.out.print(var.getName());
/*     */       }
/* 360 */       else if (dataRef instanceof ArrayNavigationExpression) {
/*     */         
/* 362 */         ArrayNavigationExpression arrayNavExp = (ArrayNavigationExpression)dataRef;
/* 363 */         Expression index = arrayNavExp.getIndex();
/* 364 */         VariableReference navigated = (VariableReference)arrayNavExp.getNavigated();
/* 365 */         Variable arrayName = navigated.getTargetVariable();
/* 366 */         System.out.print(String.valueOf(arrayName.getName()) + "[");
/* 367 */         print_expression(index);
/* 368 */         System.out.print("]");
/*     */       }
/*     */       else {
/*     */         
/* 372 */         System.out.println("ERROR: " + dataRef.toString());
/*     */       } 
/*     */       
/* 375 */       System.out.print(" = ");
/*     */       
/* 377 */       Expression exp = assignAct.getAssignedValue();
/* 378 */       print_expression(exp);
/* 379 */       System.out.println(";");
/*     */     }
/* 381 */     else if (act instanceof FunctionCallExpression) {
/*     */       
/* 383 */       FunctionCallExpression funCallExp = (FunctionCallExpression)act;
/* 384 */       System.out.print(String.valueOf(funCallExp.getFunctionName()) + "(");
/*     */       
/* 386 */       int numOfParams = funCallExp.getActualData().size();
/* 387 */       for (int i = 0; i < numOfParams; i++) {
/*     */         
/* 389 */         Expression param = (Expression)funCallExp.getActualData().get(i);
/* 390 */         print_expression(param);
/* 391 */         if (i != numOfParams - 1) System.out.print(", "); 
/*     */       } 
/* 393 */       System.out.println(");");
/*     */     }
/*     */     else {
/*     */       
/* 397 */       System.out.print("ERROR: " + act.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void print_operator(String operator) {
/* 402 */     if (operator.equals("addition")) { System.out.print("+"); }
/* 403 */     else if (operator.equals("equality")) { System.out.print("=="); }
/* 404 */     else if (operator.equals("less_than")) { System.out.print("<"); }
/* 405 */     else if (operator.equals("logical_not")) { System.out.print("!"); }
/* 406 */     else if (operator.equals("logical_and")) { System.out.print("&&"); }
/* 407 */     else if (operator.equals("modulus")) { System.out.print("%"); }
/* 408 */     else if (operator.equals("inequality")) { System.out.print("!="); }
/* 409 */     else if (operator.equals("negative")) { System.out.print("-"); }
/* 410 */     else if (operator.equals("increment")) { System.out.print("++"); }
/* 411 */     else { System.out.print("ERROR: " + operator); }
/*     */   
/*     */   }
/*     */   public static void print_expression(Expression exp) {
/* 415 */     if (exp == null)
/* 416 */       return;  if (exp instanceof BinaryExpression) {
/*     */       
/* 418 */       BinaryExpression binaryExp = (BinaryExpression)exp;
/* 419 */       Expression leftExp = binaryExp.getLeftOperand();
/* 420 */       Expression rightExp = binaryExp.getRightOperand();
/* 421 */       BinaryOperator operator = binaryExp.getOperator();
/* 422 */       System.out.print("(");
/* 423 */       print_expression(leftExp);
/* 424 */       System.out.print(") ");
/* 425 */       print_operator(operator.toString());
/* 426 */       System.out.print(" (");
/* 427 */       print_expression(rightExp);
/* 428 */       System.out.print(")");
/*     */     }
/* 430 */     else if (exp instanceof UnaryExpression) {
/*     */       
/* 432 */       UnaryExpression unaryExp = (UnaryExpression)exp;
/* 433 */       Expression operand = unaryExp.getOperand();
/* 434 */       UnaryOperator operator = unaryExp.getOperator();
/* 435 */       print_operator(operator.toString());
/* 436 */       System.out.print(" (");
/* 437 */       print_expression(operand);
/* 438 */       System.out.print(")");
/*     */     }
/* 440 */     else if (exp instanceof VariableReference) {
/*     */       
/* 442 */       VariableReference varRef = (VariableReference)exp;
/* 443 */       Variable var = varRef.getTargetVariable();
/* 444 */       System.out.print(var.getName());
/*     */     }
/* 446 */     else if (exp instanceof DataParameterReference) {
/*     */       
/* 448 */       DataParameterReference dataParamRef = (DataParameterReference)exp;
/* 449 */       DataParameter dataParam = dataParamRef.getTargetParameter();
/* 450 */       System.out.print(dataParam.getName());
/*     */     }
/* 452 */     else if (exp instanceof IntegerLiteral) {
/*     */       
/* 454 */       IntegerLiteral intLit = (IntegerLiteral)exp;
/* 455 */       System.out.print(intLit.getIValue());
/*     */     }
/* 457 */     else if (exp instanceof BooleanLiteral) {
/*     */       
/* 459 */       BooleanLiteral boolLit = (BooleanLiteral)exp;
/* 460 */       System.out.print(boolLit.isBValue());
/*     */     }
/* 462 */     else if (exp instanceof ArrayNavigationExpression) {
/*     */       
/* 464 */       ArrayNavigationExpression arrayNavExp = (ArrayNavigationExpression)exp;
/* 465 */       Expression index = arrayNavExp.getIndex();
/* 466 */       VariableReference navigated = (VariableReference)arrayNavExp.getNavigated();
/* 467 */       Variable arrayName = navigated.getTargetVariable();
/* 468 */       System.out.print(String.valueOf(arrayName.getName()) + "[");
/* 469 */       print_expression(index);
/* 470 */       System.out.print("]");
/*     */     }
/* 472 */     else if (exp instanceof FunctionCallExpression) {
/*     */       
/* 474 */       FunctionCallExpression funCallExp = (FunctionCallExpression)exp;
/* 475 */       System.out.print(String.valueOf(funCallExp.getFunctionName()) + "(");
/*     */       
/* 477 */       int numOfParams = funCallExp.getActualData().size();
/* 478 */       for (int i = 0; i < numOfParams; i++) {
/*     */         
/* 480 */         Expression param = (Expression)funCallExp.getActualData().get(i);
/* 481 */         print_expression(param);
/* 482 */         if (i != numOfParams - 1) System.out.print(", "); 
/*     */       } 
/* 484 */       System.out.print(")");
/*     */     }
/*     */     else {
/*     */       
/* 488 */       System.out.println("ERROR: " + exp.toString());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */