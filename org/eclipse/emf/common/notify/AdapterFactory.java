package org.eclipse.emf.common.notify;

public interface AdapterFactory {
  boolean isFactoryForType(Object paramObject);
  
  Object adapt(Object paramObject1, Object paramObject2);
  
  Adapter adapt(Notifier paramNotifier, Object paramObject);
  
  Adapter adaptNew(Notifier paramNotifier, Object paramObject);
  
  void adaptAllNew(Notifier paramNotifier);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\AdapterFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */