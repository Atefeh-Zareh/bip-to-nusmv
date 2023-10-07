/*     */ package epfl.risd.bip.nusmv.expression;
/*     */ 
/*     */ import epfl.risd.bip.nusmv.api.NuPair;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NuConnector
/*     */ {
/*     */   private String name;
/*     */   private String interaction;
/*     */   private Expression guard;
/*     */   private NuExpression downAction;
/*     */   private List<NuPort> ports;
/*     */   private List<NuPair> modifiedVars;
/*     */   private Map<String, NuPort> mapPorts;
/*     */   
/*     */   public NuConnector() {
/*  24 */     this.guard = null;
/*  25 */     this.downAction = null;
/*  26 */     this.ports = new LinkedList<NuPort>();
/*  27 */     this.modifiedVars = new LinkedList<NuPair>();
/*  28 */     this.mapPorts = new HashMap<String, NuPort>();
/*     */   }
/*     */ 
/*     */   
/*     */   public NuConnector(String n, String i, List<NuPort> p, Expression g, List<NuPair> m, NuExpression d) {
/*  33 */     this.name = n;
/*  34 */     this.interaction = i;
/*  35 */     this.guard = g;
/*  36 */     this.downAction = d;
/*  37 */     this.ports = new LinkedList<NuPort>();
/*  38 */     this.modifiedVars = new LinkedList<NuPair>();
/*  39 */     if (p != null) this.ports.addAll(p); 
/*  40 */     if (m != null) this.modifiedVars.addAll(m);
/*     */   
/*     */   }
/*     */   
/*     */   public NuConnector(NuConnector n) {
/*  45 */     this.name = n.name;
/*  46 */     this.interaction = n.interaction;
/*  47 */     this.guard = n.guard;
/*  48 */     this.downAction = n.downAction;
/*  49 */     this.ports = new LinkedList<NuPort>();
/*  50 */     this.modifiedVars = new LinkedList<NuPair>();
/*  51 */     this.mapPorts = new HashMap<String, NuPort>();
/*  52 */     if (n.ports != null) this.ports.addAll(n.ports); 
/*  53 */     if (n.modifiedVars != null) this.modifiedVars.addAll(n.modifiedVars); 
/*  54 */     if (n.mapPorts != null) this.mapPorts.putAll(n.mapPorts);
/*     */   
/*     */   }
/*     */   
/*     */   public void setName(String n) {
/*  59 */     this.name = n;
/*     */   }
/*     */   
/*     */   public void setInteraction(String i) {
/*  63 */     this.interaction = i;
/*     */   }
/*     */   
/*     */   public void setPorts(List<NuPort> p) {
/*  67 */     this.ports = p;
/*     */   }
/*     */   
/*     */   public void setGuard(Expression g) {
/*  71 */     this.guard = g;
/*     */   }
/*     */   
/*     */   public void setModifiedVars(List<NuPair> m) {
/*  75 */     this.modifiedVars = m;
/*     */   }
/*     */   
/*     */   public void setDownAction(NuExpression d) {
/*  79 */     this.downAction = d;
/*     */   }
/*     */   
/*     */   public void setMapPorts(Map<String, NuPort> m) {
/*  83 */     this.mapPorts = m;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  88 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getInteraction() {
/*  92 */     return this.interaction;
/*     */   }
/*     */   
/*     */   public List<NuPort> getPorts() {
/*  96 */     return this.ports;
/*     */   }
/*     */   
/*     */   public Expression getGuard() {
/* 100 */     return this.guard;
/*     */   }
/*     */   
/*     */   public List<NuPair> getModifiedVars() {
/* 104 */     return this.modifiedVars;
/*     */   }
/*     */   
/*     */   public NuExpression getDownAction() {
/* 108 */     return this.downAction;
/*     */   }
/*     */   
/*     */   public Map<String, NuPort> getMapPorts() {
/* 112 */     return this.mapPorts;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPort(NuPort p) {
/* 117 */     if (p != null) this.ports.add(p);
/*     */   
/*     */   }
/*     */   
/*     */   public void addPorts(List<NuPort> p) {
/* 122 */     if (p != null) this.ports.addAll(p);
/*     */   
/*     */   }
/*     */   
/*     */   public void addModifiedVar(NuPair var) {
/* 127 */     this.modifiedVars.add(var);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addModifiedVars(List<NuPair> vars) {
/* 132 */     if (vars != null) this.modifiedVars.addAll(vars);
/*     */   
/*     */   }
/*     */   
/*     */   public void addMapping(String s, NuPort p) {
/* 137 */     this.mapPorts.put(s, p);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMappings(Map<String, NuPort> m) {
/* 142 */     if (m != null) this.mapPorts.putAll(m);
/*     */   
/*     */   }
/*     */   
/*     */   public String toString() {
/* 147 */     return this.name;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuConnector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */