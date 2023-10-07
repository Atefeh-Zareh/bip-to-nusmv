/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SerializedGrammar {
/*     */   public static final String COOKIE = "$ANTLR";
/*     */   public static final int FORMAT_VERSION = 1;
/*     */   public String name;
/*     */   public char type;
/*     */   public List rules;
/*     */   
/*     */   class Rule {
/*     */     String name;
/*     */     SerializedGrammar.Block block;
/*     */     private final SerializedGrammar this$0;
/*     */     
/*     */     public Rule(String name, SerializedGrammar.Block block) {
/*  23 */       this.name = name;
/*  24 */       this.block = block;
/*     */     }
/*     */     public String toString() {
/*  27 */       return this.name + ":" + this.block;
/*     */     } }
/*     */   
/*     */   class Block { List[] alts;
/*     */     private final SerializedGrammar this$0;
/*     */     
/*     */     public Block(List[] alts) {
/*  34 */       this.alts = alts;
/*     */     }
/*     */     public String toString() {
/*  37 */       StringBuffer buf = new StringBuffer();
/*  38 */       buf.append("(");
/*  39 */       for (int i = 0; i < this.alts.length; i++) {
/*  40 */         List alt = this.alts[i];
/*  41 */         if (i > 0) buf.append("|"); 
/*  42 */         buf.append(alt.toString());
/*     */       } 
/*  44 */       buf.append(")");
/*  45 */       return buf.toString();
/*     */     } }
/*     */   
/*     */   class TokenRef { int ttype;
/*     */     private final SerializedGrammar this$0;
/*     */     
/*  51 */     public TokenRef(int ttype) { this.ttype = ttype; } public String toString() {
/*  52 */       return String.valueOf(this.ttype);
/*     */     } }
/*     */   
/*     */   class RuleRef { int ruleIndex;
/*     */     
/*  57 */     public RuleRef(int ruleIndex) { this.ruleIndex = ruleIndex; } private final SerializedGrammar this$0; public String toString() {
/*  58 */       return String.valueOf(this.ruleIndex);
/*     */     } }
/*     */   
/*     */   public SerializedGrammar(String filename) throws IOException {
/*  62 */     System.out.println("loading " + filename);
/*  63 */     FileInputStream fis = new FileInputStream(filename);
/*  64 */     BufferedInputStream bos = new BufferedInputStream(fis);
/*  65 */     DataInputStream in = new DataInputStream(bos);
/*  66 */     readFile(in);
/*  67 */     in.close();
/*     */   }
/*     */   
/*     */   protected void readFile(DataInputStream in) throws IOException {
/*  71 */     String cookie = readString(in);
/*  72 */     if (!cookie.equals("$ANTLR")) throw new IOException("not a serialized grammar file"); 
/*  73 */     int version = in.readByte();
/*  74 */     char grammarType = (char)in.readByte();
/*  75 */     this.type = grammarType;
/*  76 */     String grammarName = readString(in);
/*  77 */     this.name = grammarName;
/*  78 */     System.out.println(grammarType + " grammar " + grammarName);
/*  79 */     int numRules = in.readShort();
/*  80 */     System.out.println("num rules = " + numRules);
/*  81 */     this.rules = readRules(in, numRules);
/*     */   }
/*     */   
/*     */   protected List readRules(DataInputStream in, int numRules) throws IOException {
/*  85 */     List rules = new ArrayList();
/*  86 */     for (int i = 0; i < numRules; i++) {
/*  87 */       Rule r = readRule(in);
/*  88 */       rules.add(r);
/*     */     } 
/*  90 */     return rules;
/*     */   }
/*     */   
/*     */   protected Rule readRule(DataInputStream in) throws IOException {
/*  94 */     byte R = in.readByte();
/*  95 */     if (R != 82) throw new IOException("missing R on start of rule"); 
/*  96 */     String name = readString(in);
/*  97 */     System.out.println("rule: " + name);
/*  98 */     byte B = in.readByte();
/*  99 */     Block b = readBlock(in);
/* 100 */     byte period = in.readByte();
/* 101 */     if (period != 46) throw new IOException("missing . on end of rule"); 
/* 102 */     return new Rule(name, b);
/*     */   }
/*     */   
/*     */   protected Block readBlock(DataInputStream in) throws IOException {
/* 106 */     int nalts = in.readShort();
/* 107 */     List[] alts = new List[nalts];
/*     */     
/* 109 */     for (int i = 0; i < nalts; i++) {
/* 110 */       List alt = readAlt(in);
/* 111 */       alts[i] = alt;
/*     */     } 
/*     */     
/* 114 */     return new Block(alts);
/*     */   }
/*     */   
/*     */   protected List readAlt(DataInputStream in) throws IOException {
/* 118 */     List alt = new ArrayList();
/* 119 */     byte A = in.readByte();
/* 120 */     if (A != 65) throw new IOException("missing A on start of alt"); 
/* 121 */     byte cmd = in.readByte();
/* 122 */     while (cmd != 59) {
/* 123 */       int ttype, ruleIndex, from, to, notThisTokenType; Block b; switch (cmd) {
/*     */         case 116:
/* 125 */           ttype = in.readShort();
/* 126 */           alt.add(new TokenRef(ttype));
/*     */           break;
/*     */         
/*     */         case 114:
/* 130 */           ruleIndex = in.readShort();
/* 131 */           alt.add(new RuleRef(ruleIndex));
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 45:
/* 137 */           from = in.readChar();
/* 138 */           to = in.readChar();
/*     */           break;
/*     */         case 126:
/* 141 */           notThisTokenType = in.readShort();
/*     */           break;
/*     */         case 66:
/* 144 */           b = readBlock(in);
/* 145 */           alt.add(b);
/*     */           break;
/*     */       } 
/* 148 */       cmd = in.readByte();
/*     */     } 
/*     */     
/* 151 */     return alt;
/*     */   }
/*     */   
/*     */   protected String readString(DataInputStream in) throws IOException {
/* 155 */     byte c = in.readByte();
/* 156 */     StringBuffer buf = new StringBuffer();
/* 157 */     while (c != 59) {
/* 158 */       buf.append((char)c);
/* 159 */       c = in.readByte();
/*     */     } 
/* 161 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 165 */     StringBuffer buf = new StringBuffer();
/* 166 */     buf.append(this.type + " grammar " + this.name);
/* 167 */     buf.append(this.rules);
/* 168 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\SerializedGrammar.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */