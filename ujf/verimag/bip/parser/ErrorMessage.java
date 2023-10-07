package ujf.verimag.bip.parser;

public interface ErrorMessage {
  public static final int BIP_OK = 0;
  
  public static final int BIP_NOTE = 1;
  
  public static final int BIP_WARNING = 2;
  
  public static final int BIP_ERROR = 3;
  
  public static final int BIP_FATAL = 4;
  
  Exception sendErrorMessage(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2);
  
  int getErrorNumber();
  
  int getWarningNumber();
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\ErrorMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */