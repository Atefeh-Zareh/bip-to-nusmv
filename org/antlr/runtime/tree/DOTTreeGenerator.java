/*     */ package org.antlr.runtime.tree;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.antlr.stringtemplate.StringTemplate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOTTreeGenerator
/*     */ {
/*  55 */   public static StringTemplate _treeST = new StringTemplate("digraph {\n\n\tordering=out;\n\tranksep=.4;\n\tbgcolor=\"lightgrey\"; node [shape=box, fixedsize=false, fontsize=12, fontname=\"Helvetica-bold\", fontcolor=\"blue\"\n\t\twidth=.25, height=.25, color=\"black\", fillcolor=\"white\", style=\"filled, solid, bold\"];\n\tedge [arrowsize=.5, color=\"black\", style=\"bold\"]\n\n  $nodes$\n  $edges$\n}\n");
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
/*  67 */   public static StringTemplate _nodeST = new StringTemplate("$name$ [label=\"$text$\"];\n");
/*     */ 
/*     */   
/*  70 */   public static StringTemplate _edgeST = new StringTemplate("$parent$ -> $child$ // \"$parentText$\" -> \"$childText$\"\n");
/*     */ 
/*     */ 
/*     */   
/*  74 */   HashMap nodeToNumberMap = new HashMap();
/*     */ 
/*     */   
/*  77 */   int nodeNumber = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringTemplate toDOT(Object tree, TreeAdaptor adaptor, StringTemplate _treeST, StringTemplate _edgeST) {
/*  84 */     StringTemplate treeST = _treeST.getInstanceOf();
/*  85 */     this.nodeNumber = 0;
/*  86 */     toDOTDefineNodes(tree, adaptor, treeST);
/*  87 */     this.nodeNumber = 0;
/*  88 */     toDOTDefineEdges(tree, adaptor, treeST);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     return treeST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StringTemplate toDOT(Object tree, TreeAdaptor adaptor) {
/* 101 */     return toDOT(tree, adaptor, _treeST, _edgeST);
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
/*     */   public StringTemplate toDOT(Tree tree) {
/* 122 */     return toDOT(tree, new CommonTreeAdaptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toDOTDefineNodes(Object tree, TreeAdaptor adaptor, StringTemplate treeST) {
/* 129 */     if (tree == null) {
/*     */       return;
/*     */     }
/* 132 */     int n = adaptor.getChildCount(tree);
/* 133 */     if (n == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     StringTemplate parentNodeST = getNodeST(adaptor, tree);
/* 141 */     treeST.setAttribute("nodes", parentNodeST);
/*     */ 
/*     */     
/* 144 */     for (int i = 0; i < n; i++) {
/* 145 */       Object child = adaptor.getChild(tree, i);
/* 146 */       StringTemplate nodeST = getNodeST(adaptor, child);
/* 147 */       treeST.setAttribute("nodes", nodeST);
/* 148 */       toDOTDefineNodes(child, adaptor, treeST);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toDOTDefineEdges(Object tree, TreeAdaptor adaptor, StringTemplate treeST) {
/* 156 */     if (tree == null) {
/*     */       return;
/*     */     }
/* 159 */     int n = adaptor.getChildCount(tree);
/* 160 */     if (n == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 166 */     String parentName = "n" + getNodeNumber(tree);
/*     */ 
/*     */     
/* 169 */     String parentText = adaptor.getText(tree);
/* 170 */     for (int i = 0; i < n; i++) {
/* 171 */       Object child = adaptor.getChild(tree, i);
/* 172 */       String childText = adaptor.getText(child);
/* 173 */       String childName = "n" + getNodeNumber(child);
/* 174 */       StringTemplate edgeST = _edgeST.getInstanceOf();
/* 175 */       edgeST.setAttribute("parent", parentName);
/* 176 */       edgeST.setAttribute("child", childName);
/* 177 */       edgeST.setAttribute("parentText", fixString(parentText));
/* 178 */       edgeST.setAttribute("childText", fixString(childText));
/* 179 */       treeST.setAttribute("edges", edgeST);
/* 180 */       toDOTDefineEdges(child, adaptor, treeST);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected StringTemplate getNodeST(TreeAdaptor adaptor, Object t) {
/* 185 */     String text = adaptor.getText(t);
/* 186 */     StringTemplate nodeST = _nodeST.getInstanceOf();
/* 187 */     String uniqueName = "n" + getNodeNumber(t);
/* 188 */     nodeST.setAttribute("name", uniqueName);
/*     */     
/* 190 */     nodeST.setAttribute("text", fixString(text));
/* 191 */     return nodeST;
/*     */   }
/*     */   
/*     */   protected int getNodeNumber(Object t) {
/* 195 */     Integer nI = (Integer)this.nodeToNumberMap.get(t);
/* 196 */     if (nI != null) {
/* 197 */       return nI.intValue();
/*     */     }
/*     */     
/* 200 */     this.nodeToNumberMap.put(t, new Integer(this.nodeNumber));
/* 201 */     this.nodeNumber++;
/* 202 */     return this.nodeNumber - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String fixString(String in) {
/* 208 */     String text = in;
/*     */     
/* 210 */     if (text != null) {
/*     */       
/* 212 */       text = text.replaceAll("\"", "\\\\\"");
/* 213 */       text = text.replaceAll("\\t", "    ");
/* 214 */       text = text.replaceAll("\\n", "\\\\n");
/* 215 */       text = text.replaceAll("\\r", "\\\\r");
/* 216 */       if (text.length() > 20) {
/* 217 */         text = text.substring(0, 8) + "..." + text.substring(text.length() - 8);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 222 */     return text;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\tree\DOTTreeGenerator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */