/*     */ package ujf.verimag.bip.cxxcodegen;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.cgeneration.CConstruct;
/*     */ import ujf.verimag.bip.cmodel.CAssignStm;
/*     */ import ujf.verimag.bip.cmodel.CBlockStm;
/*     */ import ujf.verimag.bip.cmodel.CClass;
/*     */ import ujf.verimag.bip.cmodel.CConstructor;
/*     */ import ujf.verimag.bip.cmodel.CCreator;
/*     */ import ujf.verimag.bip.cmodel.CData;
/*     */ import ujf.verimag.bip.cmodel.CExpression;
/*     */ import ujf.verimag.bip.cmodel.CFunction;
/*     */ import ujf.verimag.bip.cmodel.CFunctionCall;
/*     */ import ujf.verimag.bip.cmodel.CIfStm;
/*     */ import ujf.verimag.bip.cmodel.CIndexed;
/*     */ import ujf.verimag.bip.cmodel.CInitialization;
/*     */ import ujf.verimag.bip.cmodel.CModule;
/*     */ import ujf.verimag.bip.cmodel.CReturn;
/*     */ import ujf.verimag.bip.cmodel.CStm;
/*     */ import ujf.verimag.bip.cmodel.CmodelFactory;
/*     */ 
/*     */ public class CxxPortTypeGenerator
/*     */ {
/*     */   protected CConstruct cBuilder;
/*  29 */   protected CmodelFactory cFactory = CmodelFactory.eINSTANCE;
/*     */   boolean bip_debug;
/*     */   
/*     */   public void setDebug(boolean debug) {
/*  33 */     this.bip_debug = debug;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CxxPortTypeGenerator(CConstruct builder) {
/*  39 */     this.cBuilder = builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateType(PortType pt, CModule cmodule) {
/*  47 */     if (pt.getName().equals("Port"))
/*     */       return; 
/*  49 */     CClass portTypeClass = this.cBuilder.createClass(CxxNames.portTypeClassName(pt.getName()), "public", "Port");
/*     */ 
/*     */     
/*  52 */     if (this.bip_debug) {
/*  53 */       portTypeClass.getSuperClasses().add("BipDebugElmt");
/*  54 */       generateDebugFunctions(pt, portTypeClass);
/*     */     } 
/*  56 */     cmodule.getContent().add(portTypeClass);
/*     */ 
/*     */     
/*  59 */     for (Iterator<DataParameter> i = pt.getDataParameter().iterator(); i.hasNext(); ) {
/*  60 */       DataParameter dp = i.next();
/*  61 */       String name = dp.getName();
/*  62 */       OpaqueElement dt = (OpaqueElement)dp.getType();
/*  63 */       String typeName = dt.getBody() + "&";
/*  64 */       CData d = this.cBuilder.createData(name, typeName, "public", null);
/*  65 */       portTypeClass.getContent().add(d);
/*     */     } 
/*     */     
/*  68 */     portTypeClass.getContent().add(portTypeConstructor(pt, "Atom"));
/*  69 */     portTypeClass.getContent().add(portTypeConstructor(pt, "Connector"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateDebugFunctions(PortType pt, CClass elementClass) {
/*     */     CIfStm cIfStm;
/*  76 */     int nbValuedElements = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     CFunction f = this.cFactory.createCFunction();
/*  86 */     elementClass.getContent().add(f);
/*  87 */     f.setName("getSubElement");
/*  88 */     f.setType("BipDebugElmt *");
/*  89 */     f.setVisibility("public");
/*     */     
/*  91 */     f.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL")));
/*  92 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/*     */     
/*  94 */     CFunction f2 = this.cFactory.createCFunction();
/*  95 */     elementClass.getContent().add(f2);
/*  96 */     f2.setName("getSubElementsNames");
/*  97 */     f2.setType("char **");
/*  98 */     f2.setVisibility("public");
/*  99 */     f2.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     f = this.cFactory.createCFunction();
/* 111 */     elementClass.getContent().add(f);
/* 112 */     f.setName("getValue");
/* 113 */     f.setType("char *");
/* 114 */     f.setVisibility("public");
/* 115 */     f.getArgument().add(this.cBuilder.createArgument("name", "char *"));
/* 116 */     f.getContent().add(this.cBuilder.createCCode("char* _numericData= new char[100];"));
/*     */     
/* 118 */     f2 = this.cFactory.createCFunction();
/* 119 */     elementClass.getContent().add(f2);
/* 120 */     f2.setName("getValuedElementNames");
/* 121 */     f2.setType("char **");
/* 122 */     f2.setVisibility("public");
/* 123 */     CCreator newExp = this.cFactory.createCCreator();
/* 124 */     newExp.setArrayAllocator(true);
/* 125 */     newExp.setType("char *");
/* 126 */     f2.getContent().add(this.cBuilder.createData("res", "char **", null, (CExpression)newExp));
/* 127 */     f2.getContent().add(this.cBuilder.createData("i", "int", null, this.cBuilder.createLiteral(0)));
/*     */ 
/*     */     
/* 130 */     CReturn cReturn = this.cBuilder.createReturn((CExpression)this.cBuilder.createLiteral("NULL"));
/* 131 */     for (Iterator<DataParameter> i = pt.getDataParameter().iterator(); i.hasNext(); ) {
/* 132 */       DataParameter dp = i.next();
/* 133 */       CFunctionCall strcmp = this.cBuilder.createFuncCall("strcmp");
/* 134 */       strcmp.getArgument().add(this.cBuilder.createSimpleName("name"));
/* 135 */       strcmp.getArgument().add(this.cBuilder.createLiteral("\"" + dp.getName() + "\""));
/* 136 */       DataType dt = dp.getType();
/* 137 */       CBlockStm b = this.cFactory.createCBlockStm();
/* 138 */       String dataType = "";
/* 139 */       if (dt instanceof OpaqueElement) {
/* 140 */         OpaqueElement oe = (OpaqueElement)dt;
/* 141 */         dataType = oe.getBody();
/*     */       } 
/* 143 */       if (dataType.equals("int")) {
/* 144 */         CFunctionCall sprintf = this.cBuilder.createFuncCall("sprintf");
/* 145 */         sprintf.getArgument().add(this.cBuilder.createSimpleName("_numericData"));
/* 146 */         sprintf.getArgument().add(this.cBuilder.createLiteral("\"%d\""));
/* 147 */         sprintf.getArgument().add(this.cBuilder.createSimpleName(dp.getName()));
/* 148 */         b.getContent().add(sprintf);
/* 149 */         b.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("_numericData")));
/*     */       } 
/*     */       
/* 152 */       CExpression cond = this.cBuilder.createOperation((CExpression)strcmp, "==", this.cBuilder.createLiteral(0));
/*     */       
/* 154 */       CIfStm is = this.cBuilder.createIf(cond, (CStm)b, (CStm)cReturn);
/* 155 */       cIfStm = is;
/*     */ 
/*     */       
/* 158 */       nbValuedElements++;
/* 159 */       CIndexed cIndexed = this.cFactory.createCIndexed();
/* 160 */       cIndexed.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/* 161 */       cIndexed.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/* 162 */       CAssignStm cAssignStm = this.cBuilder.createAssign((CExpression)cIndexed, (CExpression)this.cBuilder.createLiteral("\"" + dp.getName() + "\""));
/* 163 */       f2.getContent().add(cAssignStm);
/* 164 */       cAssignStm = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*     */ 
/*     */       
/* 167 */       f2.getContent().add(cAssignStm);
/*     */     } 
/*     */     
/* 170 */     f.getContent().add(cIfStm);
/* 171 */     nbValuedElements++;
/* 172 */     CIndexed ires = this.cFactory.createCIndexed();
/* 173 */     ires.setIndex((CExpression)this.cBuilder.createSimpleName("i"));
/* 174 */     ires.setPrefix((CExpression)this.cBuilder.createSimpleName("res"));
/* 175 */     CAssignStm ass = this.cBuilder.createAssign((CExpression)ires, (CExpression)this.cBuilder.createLiteral("NULL"));
/* 176 */     f2.getContent().add(ass);
/* 177 */     ass = this.cBuilder.createAssign((CExpression)this.cBuilder.createSimpleName("i"), this.cBuilder.createOperation((CExpression)this.cBuilder.createSimpleName("i"), "+", this.cBuilder.createLiteral(1)));
/*     */     
/* 179 */     newExp.getArgument().add(this.cBuilder.createLiteral(nbValuedElements));
/* 180 */     f2.getContent().add(this.cBuilder.createReturn((CExpression)this.cBuilder.createSimpleName("res")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     f = this.cFactory.createCFunction();
/* 189 */     elementClass.getContent().add(f);
/* 190 */     f.setName("getParent");
/* 191 */     f.setType("BipDebugElmt *");
/* 192 */     f.setVisibility("public");
/* 193 */     f.getContent().add(this.cBuilder.createCCode("return dynamic_cast<BipDebugElmt*>( mAtomHolder);"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CConstructor portTypeConstructor(PortType pt, String container) {
/* 202 */     CConstructor cons = this.cFactory.createCConstructor();
/* 203 */     cons.setBodyInDecl(true);
/* 204 */     cons.setVisibility("public");
/*     */     
/* 206 */     String name = "name";
/* 207 */     String at = "at";
/* 208 */     cons.getArgument().add(this.cBuilder.createArgument(name, "const char*"));
/* 209 */     cons.getArgument().add(this.cBuilder.createArgument(at, container + "*"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     CInitialization init = this.cFactory.createCInitialization();
/* 215 */     cons.getInit().add(init);
/* 216 */     init.setField("Port");
/* 217 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(name)));
/*     */     
/* 219 */     init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(at)));
/*     */ 
/*     */     
/* 222 */     for (Iterator<DataParameter> i = pt.getDataParameter().iterator(); i.hasNext(); ) {
/* 223 */       DataParameter dp = i.next();
/* 224 */       String dataName = dp.getName();
/* 225 */       String paramName = "BIPParam" + dp.getName();
/* 226 */       OpaqueElement dt = (OpaqueElement)dp.getType();
/* 227 */       String typeName = dt.getBody() + "&";
/* 228 */       cons.getArgument().add(this.cBuilder.createArgument(paramName, typeName));
/* 229 */       init = this.cFactory.createCInitialization();
/* 230 */       cons.getInit().add(init);
/* 231 */       init.setField(dataName);
/* 232 */       init.getParameter().add(this.cBuilder.createInitParameter(null, (CExpression)this.cBuilder.createSimpleName(paramName)));
/*     */     } 
/*     */ 
/*     */     
/* 236 */     return cons;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\cxxcodegen\CxxPortTypeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */