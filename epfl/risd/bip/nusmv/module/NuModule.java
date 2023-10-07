/*     */ package epfl.risd.bip.nusmv.module;
/*     */ 
/*     */ import epfl.risd.bip.nusmv.expression.NuDefineAction;
/*     */ import epfl.risd.bip.nusmv.expression.NuInitExpression;
/*     */ import epfl.risd.bip.nusmv.expression.NuInvarExpression;
/*     */ import epfl.risd.bip.nusmv.expression.NuNamedElement;
/*     */ import epfl.risd.bip.nusmv.expression.NuTransExpression;
/*     */ import epfl.risd.bip.nusmv.expression.NuVarAction;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NuModule
/*     */   extends NuNamedElement
/*     */ {
/*     */   private List<String> parameters;
/*     */   private List<NuVarAction> vars;
/*     */   private List<NuInitExpression> inits;
/*     */   private List<NuTransExpression> trans;
/*     */   private List<NuInvarExpression> invars;
/*     */   private List<NuDefineAction> defines;
/*     */   
/*     */   public NuModule() {
/*  25 */     this.parameters = new LinkedList<String>();
/*  26 */     this.vars = new LinkedList<NuVarAction>();
/*  27 */     this.inits = new LinkedList<NuInitExpression>();
/*  28 */     this.trans = new LinkedList<NuTransExpression>();
/*  29 */     this.invars = new LinkedList<NuInvarExpression>();
/*  30 */     this.defines = new LinkedList<NuDefineAction>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NuModule(String name, List<String> p, List<NuVarAction> v, List<NuInitExpression> i, List<NuTransExpression> t, List<NuInvarExpression> inv, List<NuDefineAction> d) {
/*  36 */     super(name);
/*  37 */     this.parameters = new LinkedList<String>();
/*  38 */     this.vars = new LinkedList<NuVarAction>();
/*  39 */     this.inits = new LinkedList<NuInitExpression>();
/*  40 */     this.trans = new LinkedList<NuTransExpression>();
/*  41 */     this.invars = new LinkedList<NuInvarExpression>();
/*  42 */     this.defines = new LinkedList<NuDefineAction>();
/*  43 */     if (p != null) this.parameters.addAll(p); 
/*  44 */     if (v != null) this.vars.addAll(v); 
/*  45 */     if (i != null) this.inits.addAll(i); 
/*  46 */     if (t != null) this.trans.addAll(t); 
/*  47 */     if (inv != null) this.invars.addAll(inv); 
/*  48 */     if (d != null) this.defines.addAll(d);
/*     */   
/*     */   }
/*     */   
/*     */   public NuModule(NuModule n) {
/*  53 */     super(n.name);
/*  54 */     this.parameters = new LinkedList<String>();
/*  55 */     this.vars = new LinkedList<NuVarAction>();
/*  56 */     this.inits = new LinkedList<NuInitExpression>();
/*  57 */     this.trans = new LinkedList<NuTransExpression>();
/*  58 */     this.invars = new LinkedList<NuInvarExpression>();
/*  59 */     this.defines = new LinkedList<NuDefineAction>();
/*  60 */     if (n.parameters != null) this.parameters.addAll(n.parameters); 
/*  61 */     if (n.vars != null) this.vars.addAll(n.vars); 
/*  62 */     if (n.inits != null) this.inits.addAll(n.inits); 
/*  63 */     if (n.trans != null) this.trans.addAll(n.trans); 
/*  64 */     if (n.invars != null) this.invars.addAll(n.invars); 
/*  65 */     if (n.defines != null) this.defines.addAll(n.defines);
/*     */   
/*     */   }
/*     */   
/*     */   public void setParameters(List<String> p) {
/*  70 */     this.parameters = p;
/*     */   }
/*     */   
/*     */   public void setVars(List<NuVarAction> v) {
/*  74 */     this.vars = v;
/*     */   }
/*     */   
/*     */   public void setInits(List<NuInitExpression> i) {
/*  78 */     this.inits = i;
/*     */   }
/*     */   
/*     */   public void setTrans(List<NuTransExpression> t) {
/*  82 */     this.trans = t;
/*     */   }
/*     */   
/*     */   public void setInvars(List<NuInvarExpression> inv) {
/*  86 */     this.invars = inv;
/*     */   }
/*     */   
/*     */   public void setDefines(List<NuDefineAction> d) {
/*  90 */     this.defines = d;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getParameters() {
/*  95 */     return this.parameters;
/*     */   }
/*     */   
/*     */   public List<NuVarAction> getVars() {
/*  99 */     return this.vars;
/*     */   }
/*     */   
/*     */   public List<NuInitExpression> getInits() {
/* 103 */     return this.inits;
/*     */   }
/*     */   
/*     */   public List<NuTransExpression> getTrans() {
/* 107 */     return this.trans;
/*     */   }
/*     */   
/*     */   public List<NuInvarExpression> getInvars() {
/* 111 */     return this.invars;
/*     */   }
/*     */   
/*     */   public List<NuDefineAction> getDefines() {
/* 115 */     return this.defines;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addParameter(String p) {
/* 120 */     if (p != null) this.parameters.add(p);
/*     */   
/*     */   }
/*     */   
/*     */   public void addParameters(List<String> p) {
/* 125 */     if (p != null) this.parameters.addAll(p);
/*     */   
/*     */   }
/*     */   
/*     */   public void addVar(NuVarAction v) {
/* 130 */     if (v != null) this.vars.add(v);
/*     */   
/*     */   }
/*     */   
/*     */   public void addVars(List<NuVarAction> v) {
/* 135 */     if (v != null) this.vars.addAll(v);
/*     */   
/*     */   }
/*     */   
/*     */   public void addInit(NuInitExpression i) {
/* 140 */     if (i != null) this.inits.add(i);
/*     */   
/*     */   }
/*     */   
/*     */   public void addInits(List<NuInitExpression> i) {
/* 145 */     if (i != null) this.inits.addAll(i);
/*     */   
/*     */   }
/*     */   
/*     */   public void addTran(NuTransExpression t) {
/* 150 */     if (t != null) this.trans.add(t);
/*     */   
/*     */   }
/*     */   
/*     */   public void addTrans(List<NuTransExpression> t) {
/* 155 */     if (t != null) this.trans.addAll(t);
/*     */   
/*     */   }
/*     */   
/*     */   public void addInvar(NuInvarExpression inv) {
/* 160 */     if (inv != null) this.invars.add(inv);
/*     */   
/*     */   }
/*     */   
/*     */   public void addInvars(List<NuInvarExpression> inv) {
/* 165 */     if (inv != null) this.invars.addAll(inv);
/*     */   
/*     */   }
/*     */   
/*     */   public void addDefine(NuDefineAction d) {
/* 170 */     if (d != null) this.defines.add(d);
/*     */   
/*     */   }
/*     */   
/*     */   public void addDefines(List<NuDefineAction> d) {
/* 175 */     if (d != null) this.defines.addAll(d);
/*     */   
/*     */   }
/*     */   
/*     */   public String toString(boolean main) {
/* 180 */     String s = "";
/*     */     
/* 182 */     s = String.valueOf(s) + printModuleName();
/* 183 */     s = String.valueOf(s) + printParameters();
/* 184 */     s = String.valueOf(s) + "\n\n";
/* 185 */     s = String.valueOf(s) + printVars();
/* 186 */     s = String.valueOf(s) + "\n";
/* 187 */     s = String.valueOf(s) + printDefines();
/* 188 */     s = String.valueOf(s) + "\n";
/* 189 */     s = String.valueOf(s) + printInits();
/* 190 */     s = String.valueOf(s) + "\n";
/* 191 */     s = String.valueOf(s) + printInvars();
/* 192 */     s = String.valueOf(s) + "\n";
/* 193 */     s = String.valueOf(s) + printTrans(main);
/* 194 */     s = String.valueOf(s) + "\n";
/*     */     
/* 196 */     return s;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 200 */     return toString(false);
/*     */   }
/*     */   
/*     */   public String printModuleName() {
/* 204 */     String s = "";
/*     */     
/* 206 */     s = String.valueOf(s) + "MODULE";
/* 207 */     s = String.valueOf(s) + " ";
/* 208 */     s = String.valueOf(s) + this.name;
/*     */     
/* 210 */     return s;
/*     */   }
/*     */   
/*     */   public String printParameters() {
/* 214 */     String s = "";
/*     */     
/* 216 */     if (!this.parameters.isEmpty()) {
/*     */       
/* 218 */       s = String.valueOf(s) + "(";
/* 219 */       boolean first = true;
/*     */       
/* 221 */       for (String param : this.parameters) {
/*     */         
/* 223 */         if (!first) s = String.valueOf(s) + ", "; 
/* 224 */         s = String.valueOf(s) + param;
/* 225 */         first = false;
/*     */       } 
/*     */       
/* 228 */       s = String.valueOf(s) + ")";
/*     */     } 
/*     */     
/* 231 */     return s;
/*     */   }
/*     */   
/*     */   public String printVars() {
/* 235 */     String s = "";
/*     */     
/* 237 */     for (NuVarAction varAct : this.vars) {
/*     */       
/* 239 */       s = String.valueOf(s) + "\t";
/* 240 */       s = String.valueOf(s) + "VAR";
/* 241 */       s = String.valueOf(s) + "\n";
/* 242 */       s = String.valueOf(s) + varAct.toString();
/*     */     } 
/*     */     
/* 245 */     return s;
/*     */   }
/*     */   
/*     */   public String printDefines() {
/* 249 */     String s = "";
/*     */     
/* 251 */     for (NuDefineAction defAct : this.defines) {
/*     */       
/* 253 */       s = String.valueOf(s) + "\t";
/* 254 */       s = String.valueOf(s) + "DEFINE";
/* 255 */       s = String.valueOf(s) + "\n";
/* 256 */       s = String.valueOf(s) + defAct.toString();
/*     */     } 
/*     */     
/* 259 */     return s;
/*     */   }
/*     */   
/*     */   public String printInits() {
/* 263 */     String s = "";
/*     */     
/* 265 */     for (NuInitExpression exp : this.inits) {
/*     */       
/* 267 */       s = String.valueOf(s) + "\t";
/* 268 */       s = String.valueOf(s) + "INIT";
/* 269 */       s = String.valueOf(s) + "\n";
/* 270 */       s = String.valueOf(s) + exp.toString();
/* 271 */       s = String.valueOf(s) + "\n";
/*     */     } 
/*     */     
/* 274 */     return s;
/*     */   }
/*     */   
/*     */   public String printInvars() {
/* 278 */     String s = "";
/*     */     
/* 280 */     for (NuInvarExpression exp : this.invars) {
/*     */       
/* 282 */       s = String.valueOf(s) + "\t";
/* 283 */       s = String.valueOf(s) + "INVAR";
/* 284 */       s = String.valueOf(s) + "\n";
/* 285 */       s = String.valueOf(s) + exp.toString();
/* 286 */       s = String.valueOf(s) + "\n";
/*     */     } 
/*     */     
/* 289 */     return s;
/*     */   }
/*     */   
/*     */   public String printTrans(boolean main) {
/* 293 */     String s = "";
/*     */     
/* 295 */     for (NuTransExpression exp : this.trans) {
/*     */       
/* 297 */       s = String.valueOf(s) + "\t";
/* 298 */       s = String.valueOf(s) + "TRANS";
/* 299 */       s = String.valueOf(s) + "\n";
/* 300 */       s = String.valueOf(s) + exp.toString(main);
/* 301 */       s = String.valueOf(s) + "\n";
/*     */     } 
/*     */     
/* 304 */     return s;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\module\NuModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */