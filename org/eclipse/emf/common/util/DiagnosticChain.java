package org.eclipse.emf.common.util;

public interface DiagnosticChain {
  void add(Diagnostic paramDiagnostic);
  
  void addAll(Diagnostic paramDiagnostic);
  
  void merge(Diagnostic paramDiagnostic);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\DiagnosticChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */