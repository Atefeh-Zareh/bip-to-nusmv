package org.eclipse.emf.ecore.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

public interface InternalEList<E> extends EList<E> {
  E basicGet(int paramInt);
  
  List<E> basicList();
  
  Iterator<E> basicIterator();
  
  ListIterator<E> basicListIterator();
  
  ListIterator<E> basicListIterator(int paramInt);
  
  Object[] basicToArray();
  
  <T> T[] basicToArray(T[] paramArrayOfT);
  
  int basicIndexOf(Object paramObject);
  
  int basicLastIndexOf(Object paramObject);
  
  boolean basicContains(Object paramObject);
  
  boolean basicContainsAll(Collection<?> paramCollection);
  
  NotificationChain basicRemove(Object paramObject, NotificationChain paramNotificationChain);
  
  NotificationChain basicAdd(E paramE, NotificationChain paramNotificationChain);
  
  void addUnique(E paramE);
  
  void addUnique(int paramInt, E paramE);
  
  boolean addAllUnique(Collection<? extends E> paramCollection);
  
  boolean addAllUnique(int paramInt, Collection<? extends E> paramCollection);
  
  E setUnique(int paramInt, E paramE);
  
  public static interface Unsettable<E> extends InternalEList<E> {
    boolean isSet();
    
    void unset();
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\InternalEList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */