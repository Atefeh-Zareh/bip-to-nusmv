/*     */ package org.eclipse.emf.common.command;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.EventObject;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class BasicCommandStack
/*     */   implements CommandStack
/*     */ {
/*     */   protected List<Command> commandList;
/*     */   protected int top;
/*     */   protected Command mostRecentCommand;
/*     */   protected Collection<CommandStackListener> listeners;
/*  59 */   protected int saveIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicCommandStack() {
/*  66 */     this.commandList = new ArrayList<Command>();
/*  67 */     this.top = -1;
/*  68 */     this.listeners = new ArrayList<CommandStackListener>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Command command) {
/*  78 */     if (command != null)
/*     */     {
/*  80 */       if (command.canExecute()) {
/*     */         
/*     */         try
/*     */         {
/*  84 */           command.execute();
/*     */ 
/*     */ 
/*     */           
/*  88 */           for (Iterator<Command> commands = this.commandList.listIterator(this.top + 1); commands.hasNext(); commands.remove()) {
/*     */             
/*  90 */             Command otherCommand = commands.next();
/*  91 */             otherCommand.dispose();
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/*  96 */           this.mostRecentCommand = command;
/*  97 */           this.commandList.add(command);
/*  98 */           this.top++;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 104 */           if (this.saveIndex >= this.top)
/*     */           {
/*     */ 
/*     */             
/* 108 */             this.saveIndex = -2;
/*     */           }
/* 110 */           notifyListeners();
/*     */         }
/* 112 */         catch (AbortExecutionException exception)
/*     */         {
/* 114 */           command.dispose();
/*     */         }
/* 116 */         catch (RuntimeException exception)
/*     */         {
/* 118 */           handleError(exception);
/* 119 */           this.mostRecentCommand = null;
/* 120 */           command.dispose();
/* 121 */           notifyListeners();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 126 */         command.dispose();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUndo() {
/* 136 */     return (this.top != -1 && ((Command)this.commandList.get(this.top)).canUndo());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undo() {
/* 144 */     if (canUndo()) {
/*     */       
/* 146 */       Command command = this.commandList.get(this.top--);
/*     */       
/*     */       try {
/* 149 */         command.undo();
/* 150 */         this.mostRecentCommand = command;
/*     */       }
/* 152 */       catch (RuntimeException exception) {
/*     */         
/* 154 */         handleError(exception);
/*     */         
/* 156 */         this.mostRecentCommand = null;
/* 157 */         flush();
/*     */       } 
/*     */       
/* 160 */       notifyListeners();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRedo() {
/* 169 */     return (this.top < this.commandList.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void redo() {
/* 177 */     if (canRedo()) {
/*     */       
/* 179 */       Command command = this.commandList.get(++this.top);
/*     */       
/*     */       try {
/* 182 */         command.redo();
/* 183 */         this.mostRecentCommand = command;
/*     */       }
/* 185 */       catch (RuntimeException exception) {
/*     */         
/* 187 */         handleError(exception);
/*     */         
/* 189 */         this.mostRecentCommand = null;
/*     */ 
/*     */ 
/*     */         
/* 193 */         for (Iterator<Command> commands = this.commandList.listIterator(this.top--); commands.hasNext(); commands.remove()) {
/*     */           
/* 195 */           Command otherCommand = commands.next();
/* 196 */           otherCommand.dispose();
/*     */         } 
/*     */       } 
/*     */       
/* 200 */       notifyListeners();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/* 211 */     for (Iterator<Command> commands = this.commandList.listIterator(); commands.hasNext(); commands.remove()) {
/*     */       
/* 213 */       Command command = commands.next();
/* 214 */       command.dispose();
/*     */     } 
/* 216 */     this.commandList.clear();
/* 217 */     this.top = -1;
/* 218 */     this.saveIndex = -1;
/* 219 */     notifyListeners();
/* 220 */     this.mostRecentCommand = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Command getUndoCommand() {
/* 228 */     return 
/* 229 */       (this.top == -1 || this.top == this.commandList.size()) ? 
/* 230 */       null : 
/* 231 */       this.commandList.get(this.top);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Command getRedoCommand() {
/* 239 */     return 
/* 240 */       (this.top + 1 >= this.commandList.size()) ? 
/* 241 */       null : 
/* 242 */       this.commandList.get(this.top + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Command getMostRecentCommand() {
/* 250 */     return this.mostRecentCommand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCommandStackListener(CommandStackListener listener) {
/* 258 */     this.listeners.add(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCommandStackListener(CommandStackListener listener) {
/* 266 */     this.listeners.remove(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void notifyListeners() {
/* 274 */     for (CommandStackListener commandStackListener : this.listeners)
/*     */     {
/* 276 */       commandStackListener.commandStackChanged(new EventObject(this));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleError(Exception exception) {
/* 285 */     CommonPlugin.INSTANCE.log((
/* 286 */         new WrappedException(
/* 287 */           CommonPlugin.INSTANCE.getString("_UI_IgnoreException_exception"), exception)).fillInStackTrace());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveIsDone() {
/* 297 */     this.saveIndex = this.top;
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
/*     */   public boolean isSaveNeeded() {
/* 310 */     if (this.saveIndex < -1)
/*     */     {
/* 312 */       return true;
/*     */     }
/*     */     
/* 315 */     if (this.top > this.saveIndex) {
/*     */       
/* 317 */       for (int i = this.top; i > this.saveIndex; i--)
/*     */       {
/* 319 */         if (!(this.commandList.get(i) instanceof AbstractCommand.NonDirtying))
/*     */         {
/* 321 */           return true;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 327 */       for (int i = this.saveIndex; i > this.top; i--) {
/*     */         
/* 329 */         if (!(this.commandList.get(i) instanceof AbstractCommand.NonDirtying))
/*     */         {
/* 331 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 336 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\BasicCommandStack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */