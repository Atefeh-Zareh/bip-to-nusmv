package org.eclipse.emf.common.notify;

public interface Notification {
  @Deprecated
  public static final int CREATE = 0;
  
  public static final int SET = 1;
  
  public static final int UNSET = 2;
  
  public static final int ADD = 3;
  
  public static final int REMOVE = 4;
  
  public static final int ADD_MANY = 5;
  
  public static final int REMOVE_MANY = 6;
  
  public static final int MOVE = 7;
  
  public static final int REMOVING_ADAPTER = 8;
  
  public static final int RESOLVE = 9;
  
  public static final int EVENT_TYPE_COUNT = 10;
  
  public static final int NO_FEATURE_ID = -1;
  
  public static final int NO_INDEX = -1;
  
  Object getNotifier();
  
  int getEventType();
  
  int getFeatureID(Class<?> paramClass);
  
  Object getFeature();
  
  Object getOldValue();
  
  Object getNewValue();
  
  boolean wasSet();
  
  boolean isTouch();
  
  boolean isReset();
  
  int getPosition();
  
  boolean merge(Notification paramNotification);
  
  boolean getOldBooleanValue();
  
  boolean getNewBooleanValue();
  
  byte getOldByteValue();
  
  byte getNewByteValue();
  
  char getOldCharValue();
  
  char getNewCharValue();
  
  double getOldDoubleValue();
  
  double getNewDoubleValue();
  
  float getOldFloatValue();
  
  float getNewFloatValue();
  
  int getOldIntValue();
  
  int getNewIntValue();
  
  long getOldLongValue();
  
  long getNewLongValue();
  
  short getOldShortValue();
  
  short getNewShortValue();
  
  String getOldStringValue();
  
  String getNewStringValue();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\notify\Notification.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */