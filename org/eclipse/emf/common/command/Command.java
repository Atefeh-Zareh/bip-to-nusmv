package org.eclipse.emf.common.command;

import java.util.Collection;

public interface Command {
  boolean canExecute();
  
  void execute();
  
  boolean canUndo();
  
  void undo();
  
  void redo();
  
  Collection<?> getResult();
  
  Collection<?> getAffectedObjects();
  
  String getLabel();
  
  String getDescription();
  
  void dispose();
  
  Command chain(Command paramCommand);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\Command.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */