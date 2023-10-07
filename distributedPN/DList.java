/*     */ package distributedPN;
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
/*     */ public class DList
/*     */ {
/*     */   public static void AddUniqueListToList(List L1, List<Object> L) {
/*  15 */     for (Object o : L1) {
/*     */       
/*  17 */       if (!L.contains(o)) {
/*  18 */         L.add(o);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void AddUnique(Object o, List<Object> L) {
/*  25 */     if (!L.contains(o)) {
/*  26 */       L.add(o);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void AddListUnique(List L1, List L2) {
/*  32 */     for (Object o : L1) {
/*  33 */       AddUnique(o, L2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean IsListIntersect(List L1, List L2) {
/*  43 */     for (Object o : L1) {
/*     */       
/*  45 */       if (L2.contains(o))
/*  46 */         return true; 
/*     */     } 
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List Intersect(List L1, List L2) {
/*  58 */     List<Object> L = new LinkedList();
/*  59 */     for (Object o : L1) {
/*     */       
/*  61 */       if (L2.contains(o))
/*  62 */         L.add(o); 
/*     */     } 
/*  64 */     return L;
/*     */   }
/*     */   
/*     */   public static List<String> ChangeNameAddString(List<String> stringPort, String ToAdd) {
/*  68 */     List<String> L = new LinkedList<String>();
/*  69 */     for (String o : stringPort) {
/*     */       
/*  71 */       String s = o;
/*  72 */       s = String.valueOf(s) + ToAdd;
/*  73 */       L.add(s);
/*     */     } 
/*  75 */     return L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void MakeListofListUnique(List<List<String>> L) {
/*  81 */     for (int i = 0; i < L.size() - 1; i++) {
/*     */       
/*  83 */       List<String> l = L.get(i);
/*  84 */       for (int j = i + 1; j < L.size(); j++) {
/*     */         
/*  86 */         List<String> lj = L.get(j);
/*  87 */         if (lj.contains(l.get(0))) {
/*     */           
/*  89 */           L.remove(lj);
/*  90 */           j--;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List MakeListofListUnique1(List L) {
/* 100 */     List<Object> LUnique = new LinkedList();
/* 101 */     for (Object o : L) {
/*     */       
/* 103 */       if (LUnique.indexOf(o) < 0)
/* 104 */         LUnique.add(o); 
/*     */     } 
/* 106 */     return LUnique;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> SplitListofList(List<List<String>> setConflictPort) {
/* 112 */     List<Object> splitList = new LinkedList();
/* 113 */     for (Object<String> o : setConflictPort) {
/*     */       
/* 115 */       List l = (List)o;
/* 116 */       for (Object o1 : l)
/*     */       {
/* 118 */         splitList.add(o1);
/*     */       }
/*     */     } 
/* 121 */     return (List)splitList;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\distributedPN\DList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */