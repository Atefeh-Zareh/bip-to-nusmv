package org.eclipse.emf.common.notify;

public interface Adapter {
  void notifyChanged(Notification paramNotification);
  
  Notifier getTarget();
  
  void setTarget(Notifier paramNotifier);
  
  boolean isAdapterForType(Object paramObject);
  
  public static interface Internal extends Adapter {
    void unsetTarget(Notifier param1Notifier);
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\Adapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */