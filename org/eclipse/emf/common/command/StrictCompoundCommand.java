/*     */ package org.eclipse.emf.common.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StrictCompoundCommand
/*     */   extends CompoundCommand
/*     */ {
/*     */   protected boolean isUndoable;
/*     */   protected boolean isPessimistic;
/*  79 */   protected int rightMostExecutedCommandIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StrictCompoundCommand() {
/*  87 */     this.resultIndex = Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StrictCompoundCommand(String label) {
/*  96 */     super(label);
/*  97 */     this.resultIndex = Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StrictCompoundCommand(String label, String description) {
/* 107 */     super(label, description);
/* 108 */     this.resultIndex = Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StrictCompoundCommand(List<Command> commandList) {
/* 117 */     super(commandList);
/* 118 */     this.resultIndex = Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StrictCompoundCommand(String label, List<Command> commandList) {
/* 128 */     super(label, commandList);
/* 129 */     this.resultIndex = Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StrictCompoundCommand(String label, String description, List<Command> commandList) {
/* 140 */     super(label, description, commandList);
/* 141 */     this.resultIndex = Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean prepare() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield commandList : Ljava/util/List;
/*     */     //   4: invokeinterface listIterator : ()Ljava/util/ListIterator;
/*     */     //   9: astore_1
/*     */     //   10: aload_1
/*     */     //   11: invokeinterface hasNext : ()Z
/*     */     //   16: ifeq -> 196
/*     */     //   19: iconst_1
/*     */     //   20: istore_2
/*     */     //   21: aload_1
/*     */     //   22: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   27: checkcast org/eclipse/emf/common/command/Command
/*     */     //   30: astore_3
/*     */     //   31: aload_3
/*     */     //   32: invokeinterface canExecute : ()Z
/*     */     //   37: ifeq -> 150
/*     */     //   40: aload_1
/*     */     //   41: invokeinterface hasNext : ()Z
/*     */     //   46: ifeq -> 137
/*     */     //   49: aload_3
/*     */     //   50: invokeinterface canUndo : ()Z
/*     */     //   55: ifeq -> 132
/*     */     //   58: aload_1
/*     */     //   59: invokeinterface previousIndex : ()I
/*     */     //   64: aload_0
/*     */     //   65: getfield rightMostExecutedCommandIndex : I
/*     */     //   68: if_icmpgt -> 80
/*     */     //   71: aload_3
/*     */     //   72: invokeinterface redo : ()V
/*     */     //   77: goto -> 21
/*     */     //   80: aload_0
/*     */     //   81: dup
/*     */     //   82: getfield rightMostExecutedCommandIndex : I
/*     */     //   85: iconst_1
/*     */     //   86: iadd
/*     */     //   87: putfield rightMostExecutedCommandIndex : I
/*     */     //   90: aload_3
/*     */     //   91: invokeinterface execute : ()V
/*     */     //   96: goto -> 21
/*     */     //   99: astore #4
/*     */     //   101: getstatic org/eclipse/emf/common/CommonPlugin.INSTANCE : Lorg/eclipse/emf/common/CommonPlugin;
/*     */     //   104: new org/eclipse/emf/common/util/WrappedException
/*     */     //   107: dup
/*     */     //   108: getstatic org/eclipse/emf/common/CommonPlugin.INSTANCE : Lorg/eclipse/emf/common/CommonPlugin;
/*     */     //   111: ldc '_UI_IgnoreException_exception'
/*     */     //   113: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   116: aload #4
/*     */     //   118: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Exception;)V
/*     */     //   121: invokevirtual fillInStackTrace : ()Ljava/lang/Throwable;
/*     */     //   124: invokevirtual log : (Ljava/lang/Object;)V
/*     */     //   127: iconst_0
/*     */     //   128: istore_2
/*     */     //   129: goto -> 152
/*     */     //   132: iconst_0
/*     */     //   133: istore_2
/*     */     //   134: goto -> 152
/*     */     //   137: aload_0
/*     */     //   138: aload_3
/*     */     //   139: invokeinterface canUndo : ()Z
/*     */     //   144: putfield isUndoable : Z
/*     */     //   147: goto -> 152
/*     */     //   150: iconst_0
/*     */     //   151: istore_2
/*     */     //   152: aload_0
/*     */     //   153: getfield isPessimistic : Z
/*     */     //   156: ifeq -> 194
/*     */     //   159: aload_1
/*     */     //   160: invokeinterface previous : ()Ljava/lang/Object;
/*     */     //   165: pop
/*     */     //   166: goto -> 185
/*     */     //   169: aload_1
/*     */     //   170: invokeinterface previous : ()Ljava/lang/Object;
/*     */     //   175: checkcast org/eclipse/emf/common/command/Command
/*     */     //   178: astore_3
/*     */     //   179: aload_3
/*     */     //   180: invokeinterface undo : ()V
/*     */     //   185: aload_1
/*     */     //   186: invokeinterface hasPrevious : ()Z
/*     */     //   191: ifne -> 169
/*     */     //   194: iload_2
/*     */     //   195: ireturn
/*     */     //   196: aload_0
/*     */     //   197: iconst_0
/*     */     //   198: putfield isUndoable : Z
/*     */     //   201: iconst_0
/*     */     //   202: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #154	-> 0
/*     */     //   #158	-> 10
/*     */     //   #160	-> 19
/*     */     //   #166	-> 21
/*     */     //   #167	-> 31
/*     */     //   #169	-> 40
/*     */     //   #171	-> 49
/*     */     //   #175	-> 58
/*     */     //   #177	-> 71
/*     */     //   #181	-> 80
/*     */     //   #182	-> 90
/*     */     //   #185	-> 99
/*     */     //   #187	-> 101
/*     */     //   #188	-> 104
/*     */     //   #189	-> 108
/*     */     //   #188	-> 118
/*     */     //   #189	-> 121
/*     */     //   #187	-> 124
/*     */     //   #191	-> 127
/*     */     //   #192	-> 129
/*     */     //   #199	-> 132
/*     */     //   #200	-> 134
/*     */     //   #208	-> 137
/*     */     //   #209	-> 147
/*     */     //   #216	-> 150
/*     */     //   #223	-> 152
/*     */     //   #227	-> 159
/*     */     //   #231	-> 166
/*     */     //   #233	-> 169
/*     */     //   #234	-> 179
/*     */     //   #231	-> 185
/*     */     //   #238	-> 194
/*     */     //   #242	-> 196
/*     */     //   #243	-> 201
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	203	0	this	Lorg/eclipse/emf/common/command/StrictCompoundCommand;
/*     */     //   10	193	1	commands	Ljava/util/ListIterator;
/*     */     //   21	175	2	result	Z
/*     */     //   31	121	3	command	Lorg/eclipse/emf/common/command/Command;
/*     */     //   101	31	4	exception	Ljava/lang/RuntimeException;
/*     */     //   179	6	3	command	Lorg/eclipse/emf/common/command/Command;
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   10	193	1	commands	Ljava/util/ListIterator<Lorg/eclipse/emf/common/command/Command;>;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   58	96	99	java/lang/RuntimeException
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() {
/* 256 */     if (this.isPessimistic) {
/*     */       
/* 258 */       for (ListIterator<Command> commands = this.commandList.listIterator(); commands.hasNext();) {
/*     */         
/*     */         try
/*     */         {
/*     */ 
/*     */           
/* 264 */           Command command = commands.next();
/* 265 */           if (commands.previousIndex() <= this.rightMostExecutedCommandIndex) {
/*     */             
/* 267 */             command.redo();
/*     */             
/*     */             continue;
/*     */           } 
/* 271 */           command.execute();
/*     */         
/*     */         }
/* 274 */         catch (RuntimeException exception)
/*     */         {
/*     */ 
/*     */           
/* 278 */           commands.previous();
/*     */ 
/*     */ 
/*     */           
/* 282 */           while (commands.hasPrevious()) {
/*     */             
/* 284 */             commands.previous();
/* 285 */             Command command = commands.previous();
/* 286 */             if (command.canUndo()) {
/*     */               
/* 288 */               command.undo();
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 296 */           throw exception;
/*     */         }
/*     */       
/*     */       } 
/* 300 */     } else if (!this.commandList.isEmpty()) {
/*     */       
/* 302 */       Command command = this.commandList.get(this.commandList.size() - 1);
/* 303 */       command.execute();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undo() {
/* 315 */     if (this.isPessimistic) {
/*     */       
/* 317 */       super.undo();
/*     */     }
/* 319 */     else if (!this.commandList.isEmpty()) {
/*     */       
/* 321 */       Command command = this.commandList.get(this.commandList.size() - 1);
/* 322 */       command.undo();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void redo() {
/* 334 */     if (this.isPessimistic) {
/*     */       
/* 336 */       super.redo();
/*     */     }
/* 338 */     else if (!this.commandList.isEmpty()) {
/*     */       
/* 340 */       Command command = this.commandList.get(this.commandList.size() - 1);
/* 341 */       command.redo();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean appendAndExecute(Command command) {
/* 393 */     if (command != null) {
/*     */       
/* 395 */       if (!this.isPrepared)
/*     */       {
/* 397 */         if (this.commandList.isEmpty()) {
/*     */           
/* 399 */           this.isPrepared = true;
/* 400 */           this.isExecutable = true;
/*     */         }
/*     */         else {
/*     */           
/* 404 */           this.isExecutable = prepare();
/* 405 */           this.isPrepared = true;
/* 406 */           this.isPessimistic = true;
/* 407 */           if (this.isExecutable)
/*     */           {
/* 409 */             execute();
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 414 */       if (command.canExecute()) {
/*     */         
/*     */         try {
/*     */           
/* 418 */           command.execute();
/* 419 */           this.commandList.add(command);
/* 420 */           this.rightMostExecutedCommandIndex++;
/* 421 */           this.isUndoable = command.canUndo();
/* 422 */           return true;
/*     */         }
/* 424 */         catch (RuntimeException exception) {
/*     */           
/* 426 */           CommonPlugin.INSTANCE.log((
/* 427 */               new WrappedException(
/* 428 */                 CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), exception)).fillInStackTrace());
/*     */         } 
/*     */       }
/*     */       
/* 432 */       command.dispose();
/*     */     } 
/*     */     
/* 435 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 444 */     StringBuffer result = new StringBuffer(super.toString());
/* 445 */     result.append(" (isUndoable: " + this.isUndoable + ")");
/* 446 */     result.append(" (isPessimistic: " + this.isPessimistic + ")");
/* 447 */     result.append(" (rightMostExecutedCommandIndex: " + this.rightMostExecutedCommandIndex + ")");
/*     */     
/* 449 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\StrictCompoundCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */