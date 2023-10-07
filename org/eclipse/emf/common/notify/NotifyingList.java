package org.eclipse.emf.common.notify;

import org.eclipse.emf.common.util.EList;

public interface NotifyingList<E> extends EList<E> {
  Object getNotifier();
  
  Object getFeature();
  
  int getFeatureID();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\NotifyingList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */