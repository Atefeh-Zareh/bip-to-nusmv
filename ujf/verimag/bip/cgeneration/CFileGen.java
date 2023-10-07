/*     */ package ujf.verimag.bip.cgeneration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.cmodel.CArgument;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CAssignType;
/*     */ import ujf.verimag.bip.cmodel.CBlockStm;
/*     */ import ujf.verimag.bip.cmodel.CBodyItem;
/*     */ import ujf.verimag.bip.cmodel.CCall;
/*     */ import ujf.verimag.bip.cmodel.CCallable;
/*     */ import ujf.verimag.bip.cmodel.CCaseItem;
/*     */ import ujf.verimag.bip.cmodel.CClass;
/*     */ import ujf.verimag.bip.cmodel.CConditionalExpression;
/*     */ import ujf.verimag.bip.cmodel.CConstructor;
/*     */ import ujf.verimag.bip.cmodel.CCreator;
/*     */ import ujf.verimag.bip.cmodel.CData;
/*     */ import ujf.verimag.bip.cmodel.CEnumType;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFunction;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CHeaderText;
/*     */ import ujf.verimag.bip.cmodel.CIfStm;
/*     */ import ujf.verimag.bip.cmodel.CInclude;
/*     */ import ujf.verimag.bip.cmodel.CIndexed;
/*     */ import ujf.verimag.bip.cmodel.CInitParameter;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
/*     */ import ujf.verimag.bip.cmodel.CItem;
/*     */ import ujf.verimag.bip.cmodel.CJump;
/*     */ import ujf.verimag.bip.cmodel.CLiteral;
/*     */ import ujf.verimag.bip.cmodel.CModule;
/*     */ import ujf.verimag.bip.cmodel.COperation;
/*     */ import ujf.verimag.bip.cmodel.CPointed;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CSimpleName;
/*     */ import ujf.verimag.bip.cmodel.CStm;
/*     */ import ujf.verimag.bip.cmodel.CStructured;
/*     */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*     */ import ujf.verimag.bip.cmodel.CText;
/*     */ import ujf.verimag.bip.cmodel.CTypeConvertion;
/*     */ import ujf.verimag.bip.cmodel.CWhileStm;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CFileGen
/*     */ {
/*     */   FileOutputStream outC;
/*     */   FileOutputStream outH;
/*     */   PrintStream cFile;
/*     */   PrintStream hFile;
/*     */   String hFileName;
/*  57 */   String indent = "";
/*     */   static final String INDENTATION = "  ";
/*  59 */   String header = "";
/*  60 */   String defineName = null;
/*     */   
/*     */   public String getHeader() {
/*  63 */     return this.header;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(String header) {
/*  68 */     this.header = header;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFileGen(String hFileName, String cFileName) throws FileNotFoundException {
/*  79 */     this.outC = new FileOutputStream(cFileName);
/*  80 */     this.outH = new FileOutputStream(hFileName);
/*  81 */     this.cFile = new PrintStream(this.outC);
/*  82 */     this.hFile = new PrintStream(this.outH);
/*     */     
/*  84 */     int pos = hFileName.lastIndexOf('/');
/*  85 */     if (pos >= 0) {
/*  86 */       String localName = hFileName.substring(pos + 1, hFileName.length());
/*  87 */       this.defineName = localName.replaceAll("\\.", "_");
/*  88 */       String hier = hFileName.substring(pos);
/*  89 */       if (cFileName.startsWith(hier)) {
/*  90 */         this.hFileName = localName;
/*     */       } else {
/*  92 */         this.hFileName = hFileName;
/*     */       } 
/*     */     } else {
/*  95 */       this.hFileName = hFileName;
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
/*     */   public CFileGen(String rootName) throws FileNotFoundException, IOException {
/* 108 */     String localName = (new File(rootName)).getName();
/* 109 */     this.hFileName = localName + ".h";
/* 110 */     this.outC = new FileOutputStream(localName + ".C");
/* 111 */     this.outH = new FileOutputStream(this.hFileName);
/* 112 */     this.cFile = new PrintStream(this.outC);
/* 113 */     this.hFile = new PrintStream(this.outH);
/* 114 */     this.defineName = this.hFileName.replaceAll("\\.", "_");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void terminate() {
/* 122 */     this.hFile.println("#endif");
/* 123 */     this.cFile.close();
/* 124 */     this.hFile.close();
/*     */     try {
/* 126 */       this.outC.close();
/* 127 */       this.outH.close();
/* 128 */     } catch (IOException e) {
/*     */       
/* 130 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateSource(CModule module) {
/* 140 */     this.hFile.println(this.header);
/* 141 */     this.cFile.println(this.header);
/* 142 */     this.hFile.println("#ifndef " + this.defineName);
/* 143 */     this.hFile.println("#define " + this.defineName);
/*     */ 
/*     */     
/* 146 */     for (Iterator<CInclude> iIncl = module.getCImport().iterator(); iIncl.hasNext(); ) {
/* 147 */       CInclude incl = iIncl.next();
/* 148 */       this.hFile.println("#include " + incl.getFileName());
/*     */     } 
/*     */ 
/*     */     
/* 152 */     this.cFile.println("#include \"" + this.hFileName + "\"");
/*     */ 
/*     */     
/* 155 */     for (Iterator<CItem> iItem = module.getContent().iterator(); iItem.hasNext(); ) {
/* 156 */       CItem item = iItem.next();
/* 157 */       if (CHeaderText.class.isInstance(item)) {
/* 158 */         CHeaderText source = (CHeaderText)item;
/* 159 */         this.hFile.println(this.indent + source.getCCode());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 164 */     String namespace = module.getNamespace();
/* 165 */     if (namespace != "") {
/* 166 */       this.hFile.println("namespace " + namespace + " {\n");
/* 167 */       this.cFile.println("using namespace " + namespace + ";\n");
/*     */     } 
/*     */ 
/*     */     
/* 171 */     for (Iterator<CItem> iterator2 = module.getContent().iterator(); iterator2.hasNext(); ) {
/* 172 */       CItem item = iterator2.next();
/* 173 */       if (CClass.class.isInstance(item)) {
/* 174 */         CClass cl = (CClass)item;
/* 175 */         this.hFile.println("class " + cl.getName() + ";");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     for (Iterator<CItem> iterator1 = module.getContent().iterator(); iterator1.hasNext(); ) {
/* 182 */       CItem item = iterator1.next();
/* 183 */       generateSource(item, null);
/*     */     } 
/*     */ 
/*     */     
/* 187 */     if (namespace != "") {
/* 188 */       this.hFile.println("\n} // end of namespace " + namespace + "\n");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateSource(CItem item, CClass includingClass) {
/* 209 */     if (CEnumType.class.isInstance(item)) {
/* 210 */       declareEnum((CEnumType)item, this.hFile);
/* 211 */     } else if (CClass.class.isInstance(item)) {
/* 212 */       generateClass((CClass)item);
/* 213 */     } else if (CData.class.isInstance(item)) {
/* 214 */       declareData((CData)item, this.hFile);
/* 215 */     } else if (CFunction.class.isInstance(item)) {
/* 216 */       declareFunction((CFunction)item, includingClass);
/* 217 */     } else if (CConstructor.class.isInstance(item)) {
/* 218 */       declareConstructor((CConstructor)item, includingClass);
/* 219 */     } else if (CText.class.isInstance(item)) {
/*     */       PrintStream selStream;
/* 221 */       CText source = (CText)item;
/*     */       
/* 223 */       if (source.isInBodyFile()) {
/* 224 */         selStream = this.cFile;
/*     */       } else {
/* 226 */         selStream = this.hFile;
/*     */       } 
/*     */       
/* 229 */       if (source.getPragma() != null) {
/* 230 */         selStream.println();
/* 231 */         selStream.println(source.getPragma());
/*     */       } 
/*     */       
/* 234 */       selStream.println(this.indent + source.getCCode());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateClass(CClass class1) {
/* 251 */     String oldIndent = this.indent;
/* 252 */     String classType = class1.getType();
/* 253 */     if (classType == null) { classType = " "; }
/* 254 */     else { classType = " " + classType; }
/* 255 */      if (class1.getVisibility() != null) {
/* 256 */       this.hFile.print(this.indent + "class " + class1.getName() + " : " + class1.getVisibility());
/* 257 */       if (class1.getSuperClasses().isEmpty()) {
/* 258 */         this.hFile.print(classType);
/*     */       } else {
/* 260 */         boolean first = true;
/* 261 */         for (Iterator<String> i = class1.getSuperClasses().iterator(); i.hasNext(); ) {
/* 262 */           String sup = i.next();
/* 263 */           if (first) {
/* 264 */             first = false;
/*     */           } else {
/* 266 */             this.hFile.print(" ," + class1.getVisibility());
/*     */           } 
/* 268 */           this.hFile.print(" " + sup);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 273 */       this.hFile.print(this.indent + "class " + class1.getName());
/* 274 */     }  this.hFile.println(" {");
/* 275 */     this.indent += "  ";
/* 276 */     for (Iterator<CItem> iItem = class1.getContent().iterator(); iItem.hasNext(); ) {
/* 277 */       CItem item = iItem.next();
/* 278 */       generateSource(item, class1);
/*     */     } 
/*     */     
/* 281 */     this.hFile.println("};");
/* 282 */     this.indent = oldIndent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void declareData(CData data, PrintStream file) {
/* 293 */     String visibility = data.getVisibility();
/* 294 */     CExpression init = data.getInitialValue();
/* 295 */     if (visibility != null) file.println(this.indent + visibility + ":"); 
/* 296 */     file.print(this.indent + "  " + data.getType() + " " + data.getName());
/* 297 */     if (init != null) {
/* 298 */       file.print(" = ");
/* 299 */       generateExpression(init, file, false);
/*     */     } 
/* 301 */     file.println(";");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void declareEnum(CEnumType type, PrintStream file) {
/* 311 */     String visibility = type.getVisibility();
/* 312 */     if (visibility == null) {
/* 313 */       visibility = "";
/*     */     } else {
/* 315 */       visibility = visibility + " : ";
/*     */     } 
/* 317 */     file.print(this.indent + visibility + "\n" + this.indent + "  enum " + type.getType() + " { ");
/* 318 */     boolean first = true;
/* 319 */     for (Iterator<CLiteral> iEnum = type.getEnumeration().iterator(); iEnum.hasNext(); ) {
/* 320 */       CLiteral lit = iEnum.next();
/* 321 */       if (first) { first = false; }
/* 322 */       else { file.print(", "); }
/* 323 */        file.print(lit.getValue());
/*     */     } 
/* 325 */     file.println("};");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateArguments(CCallable subp, PrintStream declFile, PrintStream implemFile) {
/* 336 */     boolean first = true;
/* 337 */     for (Iterator<CArgument> iArg = subp.getArgument().iterator(); iArg.hasNext(); ) {
/* 338 */       CArgument arg = iArg.next();
/* 339 */       String argdecl = "";
/* 340 */       if (first) { first = false; }
/* 341 */       else { argdecl = argdecl + ", "; }
/* 342 */        argdecl = argdecl + arg.getType() + " " + arg.getName();
/* 343 */       if (declFile != null) declFile.print(argdecl); 
/* 344 */       if (implemFile != null) implemFile.print(argdecl);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateBody(CCallable implem, PrintStream bodyFile) {
/* 355 */     String oldIndent = this.indent;
/* 356 */     bodyFile.println(this.indent + "{");
/* 357 */     this.indent += "  ";
/* 358 */     for (Iterator<CBodyItem> iFct = implem.getContent().iterator(); iFct.hasNext(); ) {
/* 359 */       CBodyItem item = iFct.next();
/* 360 */       if (CData.class.isInstance(item)) {
/* 361 */         declareData((CData)item, bodyFile); continue;
/* 362 */       }  if (CStm.class.isInstance(item)) {
/* 363 */         generateStm((CStm)item, bodyFile);
/*     */       }
/*     */     } 
/*     */     
/* 367 */     this.indent = oldIndent;
/* 368 */     bodyFile.println(this.indent + "}");
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
/*     */   
/*     */   private void declareConstructor(CConstructor constructor, CClass includingClass) {
/* 391 */     boolean bodyInDecl = constructor.isBodyInDecl();
/* 392 */     PrintStream bodyFile = this.cFile;
/* 393 */     String oldIndent = this.indent;
/*     */     
/* 395 */     this.hFile.print(this.indent);
/* 396 */     if (constructor.getVisibility() != null) {
/* 397 */       this.hFile.print(constructor.getVisibility() + " : ");
/*     */     }
/* 399 */     this.hFile.print(includingClass.getName() + "(");
/* 400 */     if (bodyInDecl) {
/* 401 */       generateArguments((CCallable)constructor, this.hFile, null);
/* 402 */       this.hFile.println(") ");
/* 403 */       bodyFile = this.hFile;
/*     */     } else {
/*     */       
/* 406 */       bodyFile = this.cFile;
/* 407 */       this.cFile.print(includingClass.getName() + "::" + includingClass.getName() + "(");
/* 408 */       generateArguments((CCallable)constructor, this.hFile, this.cFile);
/* 409 */       this.hFile.println(") ; ");
/* 410 */       this.cFile.print(") ");
/*     */     } 
/*     */ 
/*     */     
/* 414 */     EList eList = constructor.getInit();
/* 415 */     boolean first = true;
/* 416 */     if (eList != null && !eList.isEmpty()) {
/* 417 */       bodyFile.print(": ");
/* 418 */       for (Iterator<CInitialization> iInit = eList.iterator(); iInit.hasNext(); ) {
/* 419 */         CInitialization init = iInit.next();
/* 420 */         boolean first2 = true;
/* 421 */         if (first) { first = false; }
/* 422 */         else { bodyFile.print(", "); }
/* 423 */          String initField = init.getField();
/* 424 */         if (initField != null) { bodyFile.print(initField); }
/* 425 */         else { bodyFile.print(includingClass.getType()); }
/* 426 */          bodyFile.print("(");
/* 427 */         for (Iterator<CInitParameter> iParam = init.getParameter().iterator(); iParam.hasNext(); ) {
/* 428 */           CInitParameter param = iParam.next();
/* 429 */           String name = param.getFieldName();
/* 430 */           if (first2) { first2 = false; }
/* 431 */           else { bodyFile.print(", "); }
/* 432 */            if (name != null) {
/* 433 */             bodyFile.println(name + "=");
/*     */           }
/* 435 */           generateExpression(param.getValue(), bodyFile, false);
/*     */         } 
/* 437 */         bodyFile.print(")");
/*     */       } 
/*     */     } else {
/*     */       
/* 441 */       bodyFile.println();
/*     */     } 
/*     */     
/* 444 */     generateBody((CCallable)constructor, bodyFile);
/*     */     
/* 446 */     this.indent = oldIndent;
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
/*     */   private void declareFunction(CFunction function, CClass includingClass) {
/* 459 */     boolean bodyInDecl = function.isBodyInDecl();
/* 460 */     PrintStream bodyFile = this.cFile;
/* 461 */     String oldIndent = this.indent;
/* 462 */     String specifier = function.getSpecifier();
/* 463 */     String qualifier = function.getQualifier();
/*     */     
/* 465 */     if (includingClass != null) {
/* 466 */       this.hFile.println(this.indent + function.getVisibility() + ":");
/* 467 */       this.hFile.print(this.indent);
/*     */     } 
/*     */     
/* 470 */     if (specifier != null) {
/* 471 */       this.hFile.print(specifier + " ");
/*     */     }
/* 473 */     if (bodyInDecl) {
/* 474 */       bodyFile = this.hFile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 480 */     if (includingClass != null) {
/* 481 */       this.hFile.print(function.getType() + " " + function.getName() + "(");
/*     */     }
/* 483 */     if (bodyInDecl) {
/*     */       
/* 485 */       generateArguments((CCallable)function, this.hFile, null);
/*     */     } else {
/* 487 */       this.cFile.print(function.getType() + " ");
/* 488 */       if (includingClass != null) {
/* 489 */         this.cFile.print(includingClass.getName() + "::");
/*     */       }
/*     */       
/* 492 */       this.cFile.print(function.getName() + "(");
/*     */       
/* 494 */       if (includingClass == null) {
/* 495 */         generateArguments((CCallable)function, null, this.cFile);
/*     */       } else {
/* 497 */         generateArguments((CCallable)function, this.hFile, this.cFile);
/*     */       } 
/* 499 */       this.cFile.print(") ");
/* 500 */       if (qualifier != null) {
/* 501 */         this.cFile.print(qualifier);
/*     */       }
/* 503 */       this.cFile.println();
/*     */     } 
/* 505 */     if (includingClass != null) {
/* 506 */       this.hFile.print(") ");
/* 507 */       if (qualifier != null) this.hFile.print(qualifier); 
/* 508 */       if (!bodyInDecl) this.hFile.print(";"); 
/* 509 */       this.hFile.println();
/*     */     } 
/*     */ 
/*     */     
/* 513 */     generateBody((CCallable)function, bodyFile);
/* 514 */     this.indent = oldIndent;
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
/*     */   private void generateStm(CStm stm, PrintStream file) {
/* 532 */     String oldIndent = this.indent;
/* 533 */     if (CAssignStm.class.isInstance(stm)) {
/* 534 */       file.print(this.indent);
/* 535 */       boolean parenth = false;
/* 536 */       CAssignStm assign = (CAssignStm)stm;
/* 537 */       generateExpression(assign.getTarget(), file, false);
/* 538 */       if (assign.getType() == CAssignType.ASSIGN) {
/* 539 */         file.print(" = ");
/*     */       }
/* 541 */       else if (assign.getType() == CAssignType.PLUS_ASSIGN) {
/* 542 */         file.print(" += ");
/*     */       }
/* 544 */       else if (assign.getType() == CAssignType.MINUS_ASSIGN) {
/* 545 */         file.print(" -= ");
/*     */       }
/* 547 */       else if (assign.getType() == CAssignType.MULT_ASSIGN) {
/* 548 */         file.print(" *= ");
/*     */       }
/* 550 */       else if (assign.getType() == CAssignType.DIV_ASSIGN) {
/* 551 */         file.print(" /= ");
/*     */       }
/* 553 */       else if (assign.getType() == CAssignType.MOD_ASSIGN) {
/* 554 */         file.print(" %= ");
/*     */       } 
/* 556 */       CExpression source = assign.getSource();
/* 557 */       if (COperation.class.isInstance(source) && ((COperation)source).getOperator().equals("=="))
/*     */       {
/* 559 */         parenth = true;
/*     */       }
/* 561 */       if (parenth) file.print("("); 
/* 562 */       generateExpression(source, file, false);
/* 563 */       if (parenth) file.print(")"); 
/* 564 */       file.println(";");
/* 565 */     } else if (CIfStm.class.isInstance(stm)) {
/* 566 */       CIfStm ifStm = (CIfStm)stm;
/* 567 */       CStm ifCase = ifStm.getIfCase();
/* 568 */       CStm elseCase = ifStm.getElseCase();
/* 569 */       file.print(this.indent + "if (");
/* 570 */       generateExpression(ifStm.getCondition(), file, false);
/* 571 */       file.print(") ");
/*     */ 
/*     */       
/* 574 */       if (!CBlockStm.class.isInstance(ifCase)) {
/* 575 */         file.println("{ ");
/* 576 */         this.indent += "  ";
/*     */       } 
/* 578 */       generateStm(ifCase, file);
/* 579 */       if (!CBlockStm.class.isInstance(ifCase)) {
/* 580 */         file.print(oldIndent + "}");
/*     */       }
/* 582 */       if (elseCase != null) {
/* 583 */         file.print(" else ");
/*     */ 
/*     */         
/* 586 */         if (!CBlockStm.class.isInstance(elseCase) && !CIfStm.class.isInstance(elseCase)) {
/* 587 */           file.println("{ ");
/*     */         }
/* 589 */         generateStm(elseCase, file);
/* 590 */         if (!CBlockStm.class.isInstance(elseCase) && !CIfStm.class.isInstance(elseCase)) {
/* 591 */           file.print(oldIndent + "}");
/*     */         }
/*     */       } 
/* 594 */       file.println();
/* 595 */     } else if (CBlockStm.class.isInstance(stm)) {
/* 596 */       file.println("{");
/* 597 */       this.indent += "  ";
/* 598 */       for (Iterator iStm = ((CBlockStm)stm).getContent().iterator(); iStm.hasNext(); ) {
/* 599 */         Object o = iStm.next();
/* 600 */         if (o instanceof CStm) {
/* 601 */           generateStm((CStm)o, file); continue;
/* 602 */         }  if (o instanceof CData) {
/* 603 */           declareData((CData)o, file);
/*     */         }
/*     */       } 
/* 606 */       file.println(oldIndent + "}");
/* 607 */     } else if (CWhileStm.class.isInstance(stm)) {
/* 608 */       file.print(this.indent + "while (");
/* 609 */       generateExpression(((CWhileStm)stm).getCondition(), file, false);
/* 610 */       file.println(this.indent + ") {");
/* 611 */       this.indent += "  ";
/* 612 */       for (Iterator iStm = ((CWhileStm)stm).getContent().iterator(); iStm.hasNext(); ) {
/* 613 */         Object o = iStm.next();
/* 614 */         if (o instanceof CStm) {
/* 615 */           generateStm((CStm)o, file); continue;
/* 616 */         }  if (o instanceof CData) {
/* 617 */           declareData((CData)o, file);
/*     */         }
/*     */       } 
/* 620 */       file.println(oldIndent + "}");
/* 621 */     } else if (CSwitchStm.class.isInstance(stm)) {
/* 622 */       CSwitchStm switchStm = (CSwitchStm)stm;
/* 623 */       file.println(this.indent + "switch (" + switchStm.getSelector() + ") {");
/* 624 */       String caseIndent = this.indent + "  ";
/* 625 */       this.indent = caseIndent + "  ";
/* 626 */       for (Iterator<CCaseItem> iCase = switchStm.getCaseAction().iterator(); iCase.hasNext(); ) {
/* 627 */         CCaseItem item = iCase.next();
/* 628 */         if (item.getCaseValue() == null) {
/*     */           
/* 630 */           file.println(caseIndent + "default: {");
/*     */         } else {
/* 632 */           file.println(caseIndent + "case " + item.getCaseValue() + ": {");
/*     */         } 
/* 634 */         for (Iterator iStm = item.getContent().iterator(); iStm.hasNext(); ) {
/* 635 */           Object o = iStm.next();
/* 636 */           if (o instanceof CStm) {
/* 637 */             generateStm((CStm)o, file); continue;
/* 638 */           }  if (o instanceof CData) {
/* 639 */             declareData((CData)o, file);
/*     */           }
/*     */         } 
/* 642 */         file.println(caseIndent + "}");
/*     */       } 
/*     */       
/* 645 */       file.println(oldIndent + "}");
/* 646 */     } else if (CJump.class.isInstance(stm)) {
/* 647 */       switch (((CJump)stm).getType().getValue()) {
/*     */         case 0:
/* 649 */           file.println(this.indent + "break ;");
/*     */           break;
/*     */         case 1:
/* 652 */           file.println(this.indent + "continue ;");
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/* 657 */     } else if (CReturn.class.isInstance(stm)) {
/* 658 */       file.print(this.indent + "return ");
/* 659 */       generateExpression(((CReturn)stm).getReturnExpression(), file, false);
/* 660 */       file.println(";");
/* 661 */     } else if (CText.class.isInstance(stm)) {
/* 662 */       CText source = (CText)stm;
/* 663 */       if (source.getPragma() != null) {
/* 664 */         file.println();
/* 665 */         file.println(source.getPragma());
/*     */       } 
/* 667 */       file.println(this.indent + source.getCCode());
/*     */     }
/* 669 */     else if (CExpression.class.isInstance(stm)) {
/* 670 */       file.print(this.indent);
/* 671 */       generateExpression((CExpression)stm, file, false);
/* 672 */       file.println(";");
/*     */     } 
/*     */ 
/*     */     
/* 676 */     this.indent = oldIndent;
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
/*     */   private void generateExpression(CExpression exp, PrintStream file, boolean inExpression) {
/* 690 */     if (CSimpleName.class.isInstance(exp)) {
/* 691 */       file.print(((CSimpleName)exp).getName());
/* 692 */     } else if (CIndexed.class.isInstance(exp)) {
/* 693 */       CIndexed ind = (CIndexed)exp;
/* 694 */       generateExpression(ind.getPrefix(), file, true);
/* 695 */       file.print("[");
/* 696 */       generateExpression(ind.getIndex(), file, false);
/* 697 */       file.print("]");
/* 698 */     } else if (CStructured.class.isInstance(exp)) {
/* 699 */       CStructured structured = (CStructured)exp;
/* 700 */       generateExpression(structured.getPrefix(), file, true);
/* 701 */       file.print(".");
/* 702 */       generateExpression(structured.getField(), file, true);
/* 703 */     } else if (CPointed.class.isInstance(exp)) {
/* 704 */       CPointed pointed = (CPointed)exp;
/* 705 */       generateExpression(pointed.getPrefix(), file, true);
/* 706 */       file.print("->");
/* 707 */       generateExpression(pointed.getField(), file, true);
/* 708 */     } else if (CTypeConvertion.class.isInstance(exp)) {
/* 709 */       CTypeConvertion conv = (CTypeConvertion)exp;
/* 710 */       if (inExpression) file.print("("); 
/* 711 */       file.print("(" + conv.getType() + ")");
/* 712 */       generateExpression(conv.getConvertedExpression(), file, true);
/* 713 */       if (inExpression) file.print(")"); 
/* 714 */     } else if (COperation.class.isInstance(exp)) {
/* 715 */       COperation oper = (COperation)exp;
/* 716 */       CExpression left = oper.getLeftOperand();
/* 717 */       if (inExpression) file.print(" ("); 
/* 718 */       if (left != null) generateExpression(left, file, true); 
/* 719 */       file.print(" " + oper.getOperator() + " ");
/* 720 */       generateExpression(oper.getRightOperand(), file, true);
/* 721 */       if (inExpression) file.print(") "); 
/* 722 */     } else if (CFunctionCall.class.isInstance(exp)) {
/* 723 */       CFunctionCall call = (CFunctionCall)exp;
/* 724 */       file.print(call.getFunctionName() + "(");
/* 725 */       generateArgVal((CCall)call, file);
/* 726 */       file.print(")");
/* 727 */     } else if (CCreator.class.isInstance(exp)) {
/* 728 */       CCreator call = (CCreator)exp;
/* 729 */       file.print(" new " + call.getType());
/* 730 */       if (call.isArrayAllocator()) { file.print("["); }
/* 731 */       else { file.print("("); }
/* 732 */        generateArgVal((CCall)call, file);
/* 733 */       if (call.isArrayAllocator()) { file.print("]"); }
/* 734 */       else { file.print(")"); } 
/* 735 */     } else if (CLiteral.class.isInstance(exp)) {
/* 736 */       file.print(((CLiteral)exp).getValue());
/* 737 */     } else if (CConditionalExpression.class.isInstance(exp)) {
/* 738 */       CConditionalExpression ce = (CConditionalExpression)exp;
/* 739 */       file.print("(");
/* 740 */       file.print("(");
/* 741 */       generateExpression(ce.getCondition(), file, false);
/* 742 */       file.print(")?");
/* 743 */       generateExpression(ce.getTrueCase(), file, true);
/*     */       
/* 745 */       file.print(":");
/* 746 */       generateExpression(ce.getFalseCase(), file, true);
/*     */       
/* 748 */       file.print(")");
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
/*     */   
/*     */   private void generateArgVal(CCall call, PrintStream file) {
/* 762 */     boolean first = true;
/* 763 */     for (Iterator<CExpression> iArg = call.getArgument().iterator(); iArg.hasNext(); ) {
/* 764 */       CExpression exp = iArg.next();
/* 765 */       if (first) { first = false; }
/* 766 */       else { file.print(", "); }
/* 767 */        generateExpression(exp, file, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cgeneration\CFileGen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */