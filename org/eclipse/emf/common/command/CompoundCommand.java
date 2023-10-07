/*     */ package org.eclipse.emf.common.command;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
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
/*     */ public class CompoundCommand
/*     */   extends AbstractCommand
/*     */ {
/*     */   protected List<Command> commandList;
/*     */   public static final int LAST_COMMAND_ALL = -2147483648;
/*     */   public static final int MERGE_COMMAND_ALL = 2147483647;
/*  61 */   protected int resultIndex = Integer.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand() {
/*  69 */     this.commandList = new ArrayList<Command>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(String label) {
/*  78 */     super(label);
/*  79 */     this.commandList = new ArrayList<Command>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(String label, String description) {
/*  89 */     super(label, description);
/*  90 */     this.commandList = new ArrayList<Command>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(List<Command> commandList) {
/* 100 */     this.commandList = commandList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(String label, List<Command> commandList) {
/* 110 */     super(label);
/* 111 */     this.commandList = commandList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(String label, String description, List<Command> commandList) {
/* 122 */     super(label, description);
/* 123 */     this.commandList = commandList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(int resultIndex) {
/* 133 */     this.resultIndex = resultIndex;
/* 134 */     this.commandList = new ArrayList<Command>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(int resultIndex, String label) {
/* 144 */     super(label);
/* 145 */     this.resultIndex = resultIndex;
/* 146 */     this.commandList = new ArrayList<Command>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(int resultIndex, String label, String description) {
/* 157 */     super(label, description);
/* 158 */     this.resultIndex = resultIndex;
/* 159 */     this.commandList = new ArrayList<Command>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(int resultIndex, List<Command> commandList) {
/* 170 */     this.resultIndex = resultIndex;
/* 171 */     this.commandList = commandList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundCommand(int resultIndex, String label, List<Command> commandList) {
/* 182 */     super(label);
/* 183 */     this.resultIndex = resultIndex;
/* 184 */     this.commandList = commandList;
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
/*     */   public CompoundCommand(int resultIndex, String label, String description, List<Command> commandList) {
/* 196 */     super(label, description);
/* 197 */     this.resultIndex = resultIndex;
/* 198 */     this.commandList = commandList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 207 */     return this.commandList.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Command> getCommandList() {
/* 216 */     return Collections.unmodifiableList(this.commandList);
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
/*     */   public int getResultIndex() {
/* 228 */     return this.resultIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean prepare() {
/* 239 */     if (this.commandList.isEmpty())
/*     */     {
/* 241 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 245 */     for (Command command : this.commandList) {
/*     */       
/* 247 */       if (!command.canExecute())
/*     */       {
/* 249 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 253 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() {
/* 262 */     for (ListIterator<Command> commands = this.commandList.listIterator(); commands.hasNext();) {
/*     */ 
/*     */       
/*     */       try {
/* 266 */         Command command = commands.next();
/* 267 */         command.execute();
/*     */       }
/* 269 */       catch (RuntimeException exception) {
/*     */ 
/*     */ 
/*     */         
/* 273 */         commands.previous();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 279 */           while (commands.hasPrevious()) {
/*     */             
/* 281 */             Command command = commands.previous();
/* 282 */             if (command.canUndo()) {
/*     */               
/* 284 */               command.undo();
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/*     */             break;
/*     */           } 
/* 292 */         } catch (RuntimeException nestedException) {
/*     */           
/* 294 */           CommonPlugin.INSTANCE.log((
/* 295 */               new WrappedException(
/* 296 */                 CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), nestedException)).fillInStackTrace());
/*     */         } 
/*     */         
/* 299 */         throw exception;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUndo() {
/* 311 */     for (Command command : this.commandList) {
/*     */       
/* 313 */       if (!command.canUndo())
/*     */       {
/* 315 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undo() {
/* 328 */     for (ListIterator<Command> commands = this.commandList.listIterator(this.commandList.size()); commands.hasPrevious();) {
/*     */ 
/*     */       
/*     */       try {
/* 332 */         Command command = commands.previous();
/* 333 */         command.undo();
/*     */       }
/* 335 */       catch (RuntimeException exception) {
/*     */ 
/*     */ 
/*     */         
/* 339 */         commands.next();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 345 */           while (commands.hasNext())
/*     */           {
/* 347 */             Command command = commands.next();
/* 348 */             command.redo();
/*     */           }
/*     */         
/* 351 */         } catch (RuntimeException nestedException) {
/*     */           
/* 353 */           CommonPlugin.INSTANCE.log((
/* 354 */               new WrappedException(
/* 355 */                 CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), nestedException)).fillInStackTrace());
/*     */         } 
/*     */ 
/*     */         
/* 359 */         throw exception;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void redo() {
/* 369 */     for (ListIterator<Command> commands = this.commandList.listIterator(); commands.hasNext();) {
/*     */ 
/*     */       
/*     */       try {
/* 373 */         Command command = commands.next();
/* 374 */         command.redo();
/*     */       }
/* 376 */       catch (RuntimeException exception) {
/*     */ 
/*     */ 
/*     */         
/* 380 */         commands.previous();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 386 */           while (commands.hasPrevious())
/*     */           {
/* 388 */             Command command = commands.previous();
/* 389 */             command.undo();
/*     */           }
/*     */         
/* 392 */         } catch (RuntimeException nestedException) {
/*     */           
/* 394 */           CommonPlugin.INSTANCE.log((
/* 395 */               new WrappedException(
/* 396 */                 CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), nestedException)).fillInStackTrace());
/*     */         } 
/*     */         
/* 399 */         throw exception;
/*     */       } 
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
/*     */   public Collection<?> getResult() {
/* 412 */     if (this.commandList.isEmpty())
/*     */     {
/* 414 */       return Collections.EMPTY_LIST;
/*     */     }
/* 416 */     if (this.resultIndex == Integer.MIN_VALUE)
/*     */     {
/* 418 */       return ((Command)this.commandList.get(this.commandList.size() - 1)).getResult();
/*     */     }
/* 420 */     if (this.resultIndex == Integer.MAX_VALUE)
/*     */     {
/* 422 */       return getMergedResultCollection();
/*     */     }
/* 424 */     if (this.resultIndex < this.commandList.size())
/*     */     {
/* 426 */       return ((Command)this.commandList.get(this.resultIndex)).getResult();
/*     */     }
/*     */ 
/*     */     
/* 430 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Collection<?> getMergedResultCollection() {
/* 440 */     Collection<Object> result = new ArrayList();
/*     */     
/* 442 */     for (Command command : this.commandList)
/*     */     {
/* 444 */       result.addAll(command.getResult());
/*     */     }
/*     */     
/* 447 */     return result;
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
/*     */   public Collection<?> getAffectedObjects() {
/* 459 */     if (this.commandList.isEmpty())
/*     */     {
/* 461 */       return Collections.EMPTY_LIST;
/*     */     }
/* 463 */     if (this.resultIndex == Integer.MIN_VALUE)
/*     */     {
/* 465 */       return ((Command)this.commandList.get(this.commandList.size() - 1)).getAffectedObjects();
/*     */     }
/* 467 */     if (this.resultIndex == Integer.MAX_VALUE)
/*     */     {
/* 469 */       return getMergedAffectedObjectsCollection();
/*     */     }
/* 471 */     if (this.resultIndex < this.commandList.size())
/*     */     {
/* 473 */       return ((Command)this.commandList.get(this.resultIndex)).getAffectedObjects();
/*     */     }
/*     */ 
/*     */     
/* 477 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Collection<?> getMergedAffectedObjectsCollection() {
/* 487 */     Collection<Object> result = new ArrayList();
/*     */     
/* 489 */     for (Command command : this.commandList)
/*     */     {
/* 491 */       result.addAll(command.getAffectedObjects());
/*     */     }
/*     */     
/* 494 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 505 */     if (this.label != null)
/*     */     {
/* 507 */       return this.label;
/*     */     }
/* 509 */     if (this.commandList.isEmpty())
/*     */     {
/* 511 */       return CommonPlugin.INSTANCE.getString("_UI_CompoundCommand_label");
/*     */     }
/* 513 */     if (this.resultIndex == Integer.MIN_VALUE || this.resultIndex == Integer.MAX_VALUE)
/*     */     {
/* 515 */       return ((Command)this.commandList.get(this.commandList.size() - 1)).getLabel();
/*     */     }
/* 517 */     if (this.resultIndex < this.commandList.size())
/*     */     {
/* 519 */       return ((Command)this.commandList.get(this.resultIndex)).getLabel();
/*     */     }
/*     */ 
/*     */     
/* 523 */     return CommonPlugin.INSTANCE.getString("_UI_CompoundCommand_label");
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
/*     */   public String getDescription() {
/* 535 */     if (this.description != null)
/*     */     {
/* 537 */       return this.description;
/*     */     }
/* 539 */     if (this.commandList.isEmpty())
/*     */     {
/* 541 */       return CommonPlugin.INSTANCE.getString("_UI_CompoundCommand_description");
/*     */     }
/* 543 */     if (this.resultIndex == Integer.MIN_VALUE || this.resultIndex == Integer.MAX_VALUE)
/*     */     {
/* 545 */       return ((Command)this.commandList.get(this.commandList.size() - 1)).getDescription();
/*     */     }
/* 547 */     if (this.resultIndex < this.commandList.size())
/*     */     {
/* 549 */       return ((Command)this.commandList.get(this.resultIndex)).getDescription();
/*     */     }
/*     */ 
/*     */     
/* 553 */     return CommonPlugin.INSTANCE.getString("_UI_CompoundCommand_description");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Command command) {
/* 563 */     if (this.isPrepared)
/*     */     {
/* 565 */       throw new IllegalStateException("The command is already prepared");
/*     */     }
/*     */     
/* 568 */     if (command != null)
/*     */     {
/* 570 */       this.commandList.add(command);
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
/* 636 */     if (command != null) {
/*     */       
/* 638 */       if (!this.isPrepared)
/*     */       {
/* 640 */         if (this.commandList.isEmpty()) {
/*     */           
/* 642 */           this.isPrepared = true;
/* 643 */           this.isExecutable = true;
/*     */         }
/*     */         else {
/*     */           
/* 647 */           this.isExecutable = prepare();
/* 648 */           this.isPrepared = true;
/* 649 */           if (this.isExecutable)
/*     */           {
/* 651 */             execute();
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 656 */       if (command.canExecute()) {
/*     */         
/*     */         try {
/*     */           
/* 660 */           command.execute();
/* 661 */           this.commandList.add(command);
/* 662 */           return true;
/*     */         }
/* 664 */         catch (RuntimeException exception) {
/*     */           
/* 666 */           CommonPlugin.INSTANCE.log((
/* 667 */               new WrappedException(
/* 668 */                 CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), exception)).fillInStackTrace());
/*     */         } 
/*     */       }
/*     */       
/* 672 */       command.dispose();
/*     */     } 
/*     */     
/* 675 */     return false;
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
/*     */   public boolean appendIfCanExecute(Command command) {
/* 688 */     if (command == null)
/*     */     {
/* 690 */       return false;
/*     */     }
/* 692 */     if (command.canExecute()) {
/*     */       
/* 694 */       this.commandList.add(command);
/* 695 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 699 */     command.dispose();
/* 700 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 710 */     for (Command command : this.commandList)
/*     */     {
/* 712 */       command.dispose();
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
/*     */   public Command unwrap() {
/*     */     Command result;
/* 734 */     switch (this.commandList.size()) {
/*     */ 
/*     */       
/*     */       case 0:
/* 738 */         dispose();
/* 739 */         return UnexecutableCommand.INSTANCE;
/*     */ 
/*     */       
/*     */       case 1:
/* 743 */         result = this.commandList.remove(0);
/* 744 */         dispose();
/* 745 */         return result;
/*     */     } 
/*     */ 
/*     */     
/* 749 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 757 */     StringBuffer result = new StringBuffer(super.toString());
/* 758 */     result.append(" (commandList: #" + this.commandList.size() + ")");
/* 759 */     result.append(" (resultIndex: " + this.resultIndex + ")");
/*     */     
/* 761 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\CompoundCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */