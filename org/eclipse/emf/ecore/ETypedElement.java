package org.eclipse.emf.ecore;

public interface ETypedElement extends ENamedElement {
  public static final int UNBOUNDED_MULTIPLICITY = -1;
  
  public static final int UNSPECIFIED_MULTIPLICITY = -2;
  
  boolean isOrdered();
  
  void setOrdered(boolean paramBoolean);
  
  boolean isUnique();
  
  void setUnique(boolean paramBoolean);
  
  int getLowerBound();
  
  void setLowerBound(int paramInt);
  
  int getUpperBound();
  
  void setUpperBound(int paramInt);
  
  boolean isMany();
  
  boolean isRequired();
  
  EClassifier getEType();
  
  void setEType(EClassifier paramEClassifier);
  
  EGenericType getEGenericType();
  
  void setEGenericType(EGenericType paramEGenericType);
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\ETypedElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */