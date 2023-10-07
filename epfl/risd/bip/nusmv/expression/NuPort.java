/*     */ package epfl.risd.bip.nusmv.expression;
/*     */ 
/*     */ import epfl.risd.bip.nusmv.api.NuBinaryOperator;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NuPort
/*     */ {
/*     */   private String component;
/*     */   private String port;
/*     */   private NuExpression enablement;
/*     */   private List<String> interactions;
/*     */   private Map<String, String> mapVars;
/*     */   
/*     */   public NuPort() {
/*  20 */     this.enablement = null;
/*  21 */     this.interactions = new LinkedList<String>();
/*  22 */     this.mapVars = new HashMap<String, String>();
/*     */   }
/*     */ 
/*     */   
/*     */   public NuPort(String c, String p, List<String> i, NuExpression e, Map<String, String> m) {
/*  27 */     this.component = c;
/*  28 */     this.port = p;
/*  29 */     this.enablement = e;
/*  30 */     this.interactions = new LinkedList<String>();
/*  31 */     this.mapVars = new HashMap<String, String>();
/*  32 */     if (i != null) this.interactions.addAll(i); 
/*  33 */     if (m != null) this.mapVars.putAll(m);
/*     */   
/*     */   }
/*     */   
/*     */   public NuPort(NuPort n) {
/*  38 */     this.component = n.component;
/*  39 */     this.port = n.port;
/*  40 */     this.enablement = n.enablement;
/*  41 */     this.interactions = new LinkedList<String>();
/*  42 */     this.mapVars = new HashMap<String, String>();
/*  43 */     if (n.interactions != null) this.interactions.addAll(n.interactions); 
/*  44 */     if (n.mapVars != null) this.mapVars.putAll(n.mapVars);
/*     */   
/*     */   }
/*     */   
/*     */   public void setComponent(String c) {
/*  49 */     this.component = c;
/*     */   }
/*     */   
/*     */   public void setPort(String p) {
/*  53 */     this.port = p;
/*     */   }
/*     */   
/*     */   public void setInteractions(List<String> i) {
/*  57 */     this.interactions = i;
/*     */   }
/*     */   
/*     */   public void setEnablement(NuExpression e) {
/*  61 */     this.enablement = e;
/*     */   }
/*     */   
/*     */   public void setMapVars(Map<String, String> m) {
/*  65 */     this.mapVars = m;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComponent() {
/*  70 */     return this.component;
/*     */   }
/*     */   
/*     */   public String getPort() {
/*  74 */     return this.port;
/*     */   }
/*     */   
/*     */   public List<String> getInteractions() {
/*  78 */     return this.interactions;
/*     */   }
/*     */   
/*     */   public NuExpression getEnablement() {
/*  82 */     return this.enablement;
/*     */   }
/*     */   
/*     */   public Map<String, String> getMapVars() {
/*  86 */     return this.mapVars;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInteraction(String i) {
/*  91 */     if (i != null) this.interactions.add(i);
/*     */   
/*     */   }
/*     */   
/*     */   public void addInteractions(List<String> i) {
/*  96 */     if (i != null) this.interactions.addAll(i);
/*     */   
/*     */   }
/*     */   
/*     */   public void addMapping(String key, String val) {
/* 101 */     this.mapVars.put(key, val);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMappings(Map<String, String> m) {
/* 106 */     if (m != null) this.mapVars.putAll(m);
/*     */   
/*     */   }
/*     */   
/*     */   public void addEnablement(NuExpression e) {
/* 111 */     if (this.enablement == null) { this.enablement = e; }
/* 112 */     else { this.enablement = new NuBinaryExpression(NuBinaryOperator.LOGICAL_OR, this.enablement, e); }
/*     */   
/*     */   }
/*     */   
/*     */   public String toString() {
/* 117 */     return String.valueOf(this.component) + "." + this.port;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\epfl\risd\bip\nusmv\expression\NuPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */