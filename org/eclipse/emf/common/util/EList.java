package org.eclipse.emf.common.util;

import java.util.List;

public interface EList<E> extends List<E> {
  void move(int paramInt, E paramE);
  
  E move(int paramInt1, int paramInt2);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\EList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */