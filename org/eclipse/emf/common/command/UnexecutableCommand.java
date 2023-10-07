/*    */ package org.eclipse.emf.common.command;
/*    */ 
/*    */ import org.eclipse.emf.common.CommonPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnexecutableCommand
/*    */   extends AbstractCommand
/*    */ {
/* 31 */   public static final UnexecutableCommand INSTANCE = new UnexecutableCommand();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnexecutableCommand() {
/* 40 */     super(CommonPlugin.INSTANCE.getString("_UI_UnexecutableCommand_label"), CommonPlugin.INSTANCE.getString("_UI_UnexecutableCommand_description"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canExecute() {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 60 */     throw new UnsupportedOperationException(
/* 61 */         CommonPlugin.INSTANCE.getString("_EXC_Method_not_implemented", new String[] { String.valueOf(getClass().getName()) + ".execute()" }));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUndo() {
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void redo() {
/* 81 */     throw new UnsupportedOperationException(
/* 82 */         CommonPlugin.INSTANCE.getString("_EXC_Method_not_implemented", new String[] { String.valueOf(getClass().getName()) + ".redo()" }));
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\UnexecutableCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */