/*    */ package rv;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.util.List;
/*    */ import jveto.model.automata.Automaton;
/*    */ import jveto.model.automata.AutomatonHelper;
/*    */ import jveto.model.automata.State;
/*    */ import jveto.model.automata.VerificationMonitor;
/*    */ 
/*    */ public class MonitorLoader
/*    */ {
/*    */   public static void main(String[] args) {
/* 14 */     File file = new File("safety1.xml");
/* 15 */     File file2 = new File("safety-out.xml");
/*    */     try {
/* 17 */       VerificationMonitor monitor1 = (VerificationMonitor)AutomatonHelper.load(file);
/* 18 */       System.out.println(monitor1.getAlphabet());
/* 19 */       System.out.println(monitor1.getInitialState());
/* 20 */       System.out.println(monitor1.getStates());
/* 21 */       List<State> stateList = monitor1.getStates();
/* 22 */       for (State s : stateList) {
/* 23 */         System.out.println("Transition of state " + s + " : " + s.getTransitions());
/*    */       }
/*    */ 
/*    */       
/* 27 */       AutomatonHelper.save((Automaton)monitor1, file2);
/* 28 */     } catch (FileNotFoundException e) {
/* 29 */       System.err.println("File not found");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\rv\MonitorLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */