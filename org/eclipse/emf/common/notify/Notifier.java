package org.eclipse.emf.common.notify;

import org.eclipse.emf.common.util.EList;

public interface Notifier {
  EList<Adapter> eAdapters();
  
  boolean eDeliver();
  
  void eSetDeliver(boolean paramBoolean);
  
  void eNotify(Notification paramNotification);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\Notifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */