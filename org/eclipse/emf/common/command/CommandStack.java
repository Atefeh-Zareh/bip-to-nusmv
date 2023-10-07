package org.eclipse.emf.common.command;

public interface CommandStack {
  void execute(Command paramCommand);
  
  boolean canUndo();
  
  void undo();
  
  boolean canRedo();
  
  Command getUndoCommand();
  
  Command getRedoCommand();
  
  Command getMostRecentCommand();
  
  void redo();
  
  void flush();
  
  void addCommandStackListener(CommandStackListener paramCommandStackListener);
  
  void removeCommandStackListener(CommandStackListener paramCommandStackListener);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\CommandStack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */