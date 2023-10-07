/*     */ package distributed;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DList
/*     */ {
/*     */   public static void AddUniqueListToList(List L1, List<Object> L) {
/*  16 */     for (Object o : L1) {
/*     */       
/*  18 */       if (!L.contains(o)) {
/*  19 */         L.add(o);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void AddUnique(Object o, List<Object> L) {
/*  26 */     if (!L.contains(o)) {
/*  27 */       L.add(o);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void AddListUnique(List L1, List L2) {
/*  33 */     for (Object o : L1) {
/*  34 */       AddUnique(o, L2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean IsListIntersect(List L1, List L2) {
/*  44 */     for (Object o : L1) {
/*     */       
/*  46 */       if (L2.contains(o))
/*  47 */         return true; 
/*     */     } 
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List Intersect(List L1, List L2) {
/*  59 */     List<Object> L = new LinkedList();
/*  60 */     for (Object o : L1) {
/*     */       
/*  62 */       if (L2.contains(o))
/*  63 */         L.add(o); 
/*     */     } 
/*  65 */     return L;
/*     */   }
/*     */   
/*     */   public static List<String> ChangeNameAddString(List<String> stringPort, String ToAdd) {
/*  69 */     List<String> L = new LinkedList<String>();
/*  70 */     for (String o : stringPort) {
/*     */       
/*  72 */       String s = o;
/*  73 */       s = String.valueOf(s) + ToAdd;
/*  74 */       L.add(s);
/*     */     } 
/*  76 */     return L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void MakeListofListUnique(List<List<String>> L) {
/*  82 */     for (int i = 0; i < L.size() - 1; i++) {
/*     */       
/*  84 */       List<String> l = L.get(i);
/*  85 */       for (int j = i + 1; j < L.size(); j++) {
/*     */         
/*  87 */         List<String> lj = L.get(j);
/*  88 */         if (lj.contains(l.get(0))) {
/*     */           
/*  90 */           L.remove(lj);
/*  91 */           j--;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List MakeListofListUnique1(List L) {
/* 101 */     List<Object> LUnique = new LinkedList();
/* 102 */     for (Object o : L) {
/*     */       
/* 104 */       if (LUnique.indexOf(o) < 0)
/* 105 */         LUnique.add(o); 
/*     */     } 
/* 107 */     return LUnique;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> SplitListofList(List<List<String>> setConflictPort) {
/* 113 */     List<Object> splitList = new LinkedList();
/* 114 */     for (Object<String> o : setConflictPort) {
/*     */       
/* 116 */       List l = (List)o;
/* 117 */       for (Object o1 : l)
/*     */       {
/* 119 */         splitList.add(o1);
/*     */       }
/*     */     } 
/* 122 */     return (List)splitList;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> void combinaisons(List<T> initialset, List<T> combinaison, List<List<T>> Lcombinaison, int n, int p, int i, int t) {
/* 127 */     if (i < p) {
/*     */       
/* 129 */       for (int k = t; k < n; k++) {
/* 130 */         combinaison.add(i, initialset.get(k));
/* 131 */         combinaisons(initialset, combinaison, Lcombinaison, n, p, i + 1, k + 1);
/*     */       } 
/*     */     } else {
/*     */       
/* 135 */       List<T> findcombinaison = new LinkedList<T>(combinaison.subList(0, p));
/* 136 */       Lcombinaison.add(findcombinaison);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T> List<List<T>> getAllCombinaison(List<T> initialset) {
/* 141 */     List<List<T>> LAllCombinaison = new LinkedList<List<T>>();
/* 142 */     int n = initialset.size();
/* 143 */     for (int p = 1; p <= n; p++) {
/* 144 */       List<T> combinaison = new LinkedList<T>();
/* 145 */       combinaisons(initialset, combinaison, LAllCombinaison, n, p, 0, 0);
/*     */     } 
/* 147 */     return LAllCombinaison;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributed\DList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */