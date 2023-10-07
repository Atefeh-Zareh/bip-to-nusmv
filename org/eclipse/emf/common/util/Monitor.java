package org.eclipse.emf.common.util;

public interface Monitor {
  public static final int UNKNOWN = -1;
  
  boolean isCanceled();
  
  void setCanceled(boolean paramBoolean);
  
  void setBlocked(Diagnostic paramDiagnostic);
  
  void clearBlocked();
  
  void beginTask(String paramString, int paramInt);
  
  void setTaskName(String paramString);
  
  void subTask(String paramString);
  
  void worked(int paramInt);
  
  void internalWorked(double paramDouble);
  
  void done();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\Monitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */