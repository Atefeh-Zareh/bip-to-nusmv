package org.eclipse.emf.common.notify;

public interface NotificationChain {
  boolean add(Notification paramNotification);
  
  void dispatch();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\NotificationChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */