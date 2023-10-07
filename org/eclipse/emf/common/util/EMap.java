package org.eclipse.emf.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface EMap<K, V> extends EList<Map.Entry<K, V>> {
  V get(Object paramObject);
  
  V put(K paramK, V paramV);
  
  void putAll(Map<? extends K, ? extends V> paramMap);
  
  void putAll(EMap<? extends K, ? extends V> paramEMap);
  
  int indexOfKey(Object paramObject);
  
  boolean containsKey(Object paramObject);
  
  boolean containsValue(Object paramObject);
  
  V removeKey(Object paramObject);
  
  Map<K, V> map();
  
  Set<Map.Entry<K, V>> entrySet();
  
  Set<K> keySet();
  
  Collection<V> values();
  
  public static interface InternalMapView<K, V> extends Map<K, V> {
    EMap<K, V> eMap();
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\EMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */