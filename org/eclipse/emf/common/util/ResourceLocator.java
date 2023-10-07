package org.eclipse.emf.common.util;

import java.net.URL;

public interface ResourceLocator {
  URL getBaseURL();
  
  Object getImage(String paramString);
  
  String getString(String paramString);
  
  String getString(String paramString, boolean paramBoolean);
  
  String getString(String paramString, Object[] paramArrayOfObject);
  
  String getString(String paramString, Object[] paramArrayOfObject, boolean paramBoolean);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\ResourceLocator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */