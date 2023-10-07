/*     */ package ujf.verimag.bip.cgeneration;
/*     */ 
/*     */ import ujf.verimag.bip.cmodel.CArgument;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CAssignType;
/*     */ import ujf.verimag.bip.cmodel.CClass;
/*     */ import ujf.verimag.bip.cmodel.CData;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CHeaderText;
/*     */ import ujf.verimag.bip.cmodel.CIfStm;
/*     */ import ujf.verimag.bip.cmodel.CInclude;
/*     */ import ujf.verimag.bip.cmodel.CIndexed;
/*     */ import ujf.verimag.bip.cmodel.CInitParameter;
/*     */ import ujf.verimag.bip.cmodel.CLiteral;
/*     */ import ujf.verimag.bip.cmodel.COperation;
/*     */ import ujf.verimag.bip.cmodel.CPointed;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CSimpleName;
/*     */ import ujf.verimag.bip.cmodel.CStm;
/*     */ import ujf.verimag.bip.cmodel.CStructured;
/*     */ import ujf.verimag.bip.cmodel.CSwitchStm;
/*     */ import ujf.verimag.bip.cmodel.CText;
/*     */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CConstruct
/*     */ {
/*     */   protected CmodelFactory cFactory;
/*     */   public static final String PUBLIC = "public";
/*     */   public static final String PRIVATE = "private";
/*     */   public static final String NULL = "NULL";
/*     */   public static final String VOID = "void";
/*     */   
/*     */   public CConstruct(CmodelFactory factory) {
/*  45 */     this.cFactory = factory;
/*     */   }
/*     */   
/*     */   public CSimpleName createSimpleName(String name) {
/*  49 */     CSimpleName cName = this.cFactory.createCSimpleName();
/*  50 */     cName.setName(name);
/*  51 */     return cName;
/*     */   }
/*     */   
/*     */   public CArgument createArgument(String name, String type) {
/*  55 */     CArgument arg = this.cFactory.createCArgument();
/*  56 */     arg.setName(name);
/*  57 */     arg.setType(type);
/*  58 */     return arg;
/*     */   }
/*     */   
/*     */   public CLiteral createLiteral(String literalValue) {
/*  62 */     CLiteral lit = this.cFactory.createCLiteral();
/*  63 */     lit.setValue(literalValue);
/*  64 */     return lit;
/*     */   }
/*     */   
/*     */   public CExpression createLiteral(int val) {
/*  68 */     CLiteral lit = this.cFactory.createCLiteral();
/*  69 */     lit.setValue((new Integer(val)).toString());
/*  70 */     return (CExpression)lit;
/*     */   }
/*     */   
/*     */   public CInitParameter createInitParameter(String field, CExpression value) {
/*  74 */     CInitParameter init = this.cFactory.createCInitParameter();
/*  75 */     init.setFieldName(field);
/*  76 */     init.setValue(value);
/*  77 */     return init;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CData createData(String name, String type, String visibility, CExpression initExp) {
/*  83 */     CData data = this.cFactory.createCData();
/*  84 */     data.setName(name);
/*  85 */     data.setType(type);
/*  86 */     data.setVisibility(visibility);
/*  87 */     data.setInitialValue(initExp);
/*  88 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public CText createComment(String comment) {
/*  93 */     CText cTxt = this.cFactory.createCText();
/*  94 */     cTxt.setCCode(comment);
/*  95 */     return cTxt;
/*     */   }
/*     */   
/*     */   public CText createCCode(String comment) {
/*  99 */     CText cTxt = this.cFactory.createCText();
/* 100 */     cTxt.setCCode(comment);
/* 101 */     return cTxt;
/*     */   }
/*     */   
/*     */   public CHeaderText createCHeaderText(String txt) {
/* 105 */     CHeaderText cText = this.cFactory.createCHeaderText();
/* 106 */     cText.setCCode(txt);
/* 107 */     return cText;
/*     */   }
/*     */   
/*     */   public CIfStm createIf(CExpression cond, CStm thenCase, CStm elseCase) {
/* 111 */     CIfStm res = this.cFactory.createCIfStm();
/* 112 */     res.setCondition(cond);
/* 113 */     res.setIfCase(thenCase);
/* 114 */     if (elseCase != null) res.setElseCase(elseCase); 
/* 115 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public CExpression createOperation(CExpression left, String op, CExpression right) {
/* 120 */     COperation oper = this.cFactory.createCOperation();
/* 121 */     oper.setOperator(op);
/* 122 */     oper.setRightOperand(right);
/* 123 */     if (left != null) oper.setLeftOperand(left); 
/* 124 */     return (CExpression)oper;
/*     */   }
/*     */   
/*     */   public CExpression createPointed(CExpression prefix, CExpression field) {
/* 128 */     CPointed pointed = this.cFactory.createCPointed();
/* 129 */     pointed.setField(field);
/* 130 */     pointed.setPrefix(prefix);
/* 131 */     return (CExpression)pointed;
/*     */   }
/*     */   
/*     */   public CExpression createStructured(CExpression prefix, CExpression field) {
/* 135 */     CStructured structured = this.cFactory.createCStructured();
/* 136 */     structured.setField(field);
/* 137 */     structured.setPrefix(prefix);
/* 138 */     return (CExpression)structured;
/*     */   }
/*     */   
/*     */   public CExpression createIndexed(CExpression prefix, CExpression index) {
/* 142 */     CIndexed indexed = this.cFactory.createCIndexed();
/* 143 */     indexed.setIndex(index);
/* 144 */     indexed.setPrefix(prefix);
/* 145 */     return (CExpression)indexed;
/*     */   }
/*     */ 
/*     */   
/*     */   public CAssignStm createAssign(CExpression target, CExpression source) {
/* 150 */     CAssignStm ass = this.cFactory.createCAssignStm();
/* 151 */     ass.setTarget(target);
/* 152 */     ass.setSource(source);
/* 153 */     return ass;
/*     */   }
/*     */   
/*     */   public CAssignStm createAssign(CExpression target, CExpression source, CAssignType type) {
/* 157 */     CAssignStm ass = this.cFactory.createCAssignStm();
/* 158 */     ass.setTarget(target);
/* 159 */     ass.setSource(source);
/* 160 */     ass.setType(type);
/* 161 */     return ass;
/*     */   }
/*     */   
/*     */   public CFunctionCall createFuncCall(String funcName) {
/* 165 */     CFunctionCall call = this.cFactory.createCFunctionCall();
/* 166 */     call.setFunctionName(funcName);
/* 167 */     return call;
/*     */   }
/*     */   
/*     */   public CInclude createInclude(String incFileName) {
/* 171 */     CInclude inc = this.cFactory.createCInclude();
/* 172 */     inc.setFileName(incFileName);
/* 173 */     return inc;
/*     */   }
/*     */   
/*     */   public CReturn createReturn(CExpression exp) {
/* 177 */     CReturn ret = this.cFactory.createCReturn();
/* 178 */     ret.setReturnExpression(exp);
/* 179 */     return ret;
/*     */   }
/*     */   
/*     */   public CClass createClass(String className, String visibility, String superClass) {
/* 183 */     CClass cl = this.cFactory.createCClass();
/* 184 */     cl.setName(className);
/* 185 */     cl.setVisibility(visibility);
/* 186 */     if (superClass != null) {
/* 187 */       cl.setType(superClass);
/* 188 */       cl.getSuperClasses().add(superClass);
/*     */     } 
/* 190 */     return cl;
/*     */   }
/*     */ 
/*     */   
/*     */   public CSwitchStm createSwitch(String selector) {
/* 195 */     CSwitchStm s = this.cFactory.createCSwitchStm();
/* 196 */     s.setSelector(selector);
/* 197 */     return s;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cgeneration\CConstruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */