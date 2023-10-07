package org.eclipse.emf.ecore.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

public interface FeatureMap extends EList<FeatureMap.Entry> {
  ValueListIterator<Object> valueListIterator();
  
  ValueListIterator<Object> valueListIterator(int paramInt);
  
  <T> EList<T> list(EStructuralFeature paramEStructuralFeature);
  
  EStructuralFeature getEStructuralFeature(int paramInt);
  
  Object getValue(int paramInt);
  
  Object setValue(int paramInt, Object paramObject);
  
  Object get(EStructuralFeature paramEStructuralFeature, boolean paramBoolean);
  
  void set(EStructuralFeature paramEStructuralFeature, Object paramObject);
  
  boolean isSet(EStructuralFeature paramEStructuralFeature);
  
  void unset(EStructuralFeature paramEStructuralFeature);
  
  boolean add(EStructuralFeature paramEStructuralFeature, Object paramObject);
  
  void add(int paramInt, EStructuralFeature paramEStructuralFeature, Object paramObject);
  
  boolean addAll(EStructuralFeature paramEStructuralFeature, Collection<?> paramCollection);
  
  boolean addAll(int paramInt, EStructuralFeature paramEStructuralFeature, Collection<?> paramCollection);
  
  public static interface Entry {
    EStructuralFeature getEStructuralFeature();
    
    Object getValue();
    
    public static interface Internal extends Entry {
      NotificationChain inverseAdd(InternalEObject param2InternalEObject, int param2Int, NotificationChain param2NotificationChain);
      
      NotificationChain inverseRemove(InternalEObject param2InternalEObject, int param2Int, NotificationChain param2NotificationChain);
      
      NotificationChain inverseAdd(InternalEObject param2InternalEObject, Object param2Object, int param2Int, NotificationChain param2NotificationChain);
      
      NotificationChain inverseRemove(InternalEObject param2InternalEObject, Object param2Object, int param2Int, NotificationChain param2NotificationChain);
      
      void validate(Object param2Object);
      
      Internal createEntry(Object param2Object);
      
      Internal createEntry(InternalEObject param2InternalEObject);
    }
  }
  
  public static interface Internal extends FeatureMap, InternalEList<Entry>, EStructuralFeature.Setting {
    int getModCount();
    
    EObject getEObject();
    
    Object resolveProxy(EStructuralFeature param1EStructuralFeature, int param1Int1, int param1Int2, Object param1Object);
    
    int size(EStructuralFeature param1EStructuralFeature);
    
    boolean isEmpty(EStructuralFeature param1EStructuralFeature);
    
    boolean contains(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    boolean containsAll(EStructuralFeature param1EStructuralFeature, Collection<?> param1Collection);
    
    int indexOf(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    int lastIndexOf(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    Iterator<Object> iterator(EStructuralFeature param1EStructuralFeature);
    
    ListIterator<Object> listIterator(EStructuralFeature param1EStructuralFeature);
    
    ListIterator<Object> listIterator(EStructuralFeature param1EStructuralFeature, int param1Int);
    
    EStructuralFeature.Setting setting(EStructuralFeature param1EStructuralFeature);
    
    List<Object> basicList(EStructuralFeature param1EStructuralFeature);
    
    Iterator<Object> basicIterator(EStructuralFeature param1EStructuralFeature);
    
    ListIterator<Object> basicListIterator(EStructuralFeature param1EStructuralFeature);
    
    ListIterator<Object> basicListIterator(EStructuralFeature param1EStructuralFeature, int param1Int);
    
    Object[] basicToArray(EStructuralFeature param1EStructuralFeature);
    
    <T> T[] basicToArray(EStructuralFeature param1EStructuralFeature, T[] param1ArrayOfT);
    
    int basicIndexOf(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    int basicLastIndexOf(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    boolean basicContains(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    boolean basicContainsAll(EStructuralFeature param1EStructuralFeature, Collection<?> param1Collection);
    
    Object[] toArray(EStructuralFeature param1EStructuralFeature);
    
    <T> T[] toArray(EStructuralFeature param1EStructuralFeature, T[] param1ArrayOfT);
    
    void add(EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    boolean addAll(EStructuralFeature param1EStructuralFeature, int param1Int, Collection<?> param1Collection);
    
    void addUnique(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    void addUnique(EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    boolean addAllUnique(Collection<? extends FeatureMap.Entry> param1Collection);
    
    void addUnique(FeatureMap.Entry.Internal param1Internal);
    
    boolean addAllUnique(FeatureMap.Entry.Internal[] param1ArrayOfInternal, int param1Int1, int param1Int2);
    
    boolean addAllUnique(int param1Int1, FeatureMap.Entry.Internal[] param1ArrayOfInternal, int param1Int2, int param1Int3);
    
    NotificationChain basicAdd(EStructuralFeature param1EStructuralFeature, Object param1Object, NotificationChain param1NotificationChain);
    
    boolean remove(EStructuralFeature param1EStructuralFeature, Object param1Object);
    
    Object remove(EStructuralFeature param1EStructuralFeature, int param1Int);
    
    boolean removeAll(EStructuralFeature param1EStructuralFeature, Collection<?> param1Collection);
    
    NotificationChain basicRemove(EStructuralFeature param1EStructuralFeature, Object param1Object, NotificationChain param1NotificationChain);
    
    boolean retainAll(EStructuralFeature param1EStructuralFeature, Collection<?> param1Collection);
    
    void clear(EStructuralFeature param1EStructuralFeature);
    
    void move(EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    Object move(EStructuralFeature param1EStructuralFeature, int param1Int1, int param1Int2);
    
    Object get(EStructuralFeature param1EStructuralFeature, int param1Int, boolean param1Boolean);
    
    Object set(EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    Object setUnique(EStructuralFeature param1EStructuralFeature, int param1Int, Object param1Object);
    
    Wrapper getWrapper();
    
    void setWrapper(Wrapper param1Wrapper);
    
    public static interface Wrapper {
      FeatureMap featureMap();
    }
  }
  
  public static interface ValueListIterator<E> extends EContentsEList.FeatureListIterator<E> {
    void add(EStructuralFeature param1EStructuralFeature, Object param1Object);
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\FeatureMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */