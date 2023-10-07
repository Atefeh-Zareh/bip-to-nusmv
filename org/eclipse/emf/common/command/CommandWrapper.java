/*     */ package org.eclipse.emf.common.command;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import org.eclipse.emf.common.CommonPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandWrapper
/*     */   extends AbstractCommand
/*     */ {
/*     */   protected Command command;
/*     */   
/*     */   public CommandWrapper(Command command) {
/*  74 */     super(command.getLabel(), command.getDescription());
/*  75 */     this.command = command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CommandWrapper(String label, Command command) {
/*  85 */     super(label, command.getDescription());
/*  86 */     this.command = command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandWrapper(String label, String description, Command command) {
/*  97 */     super(label, description);
/*  98 */     this.command = command;
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
/*     */   protected CommandWrapper() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CommandWrapper(String label) {
/* 119 */     super(label);
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
/*     */   protected CommandWrapper(String label, String description) {
/* 131 */     super(label, description);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Command getCommand() {
/* 141 */     return this.command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Command createCommand() {
/* 152 */     return null;
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
/*     */   protected boolean prepare() {
/* 164 */     if (this.command == null)
/*     */     {
/* 166 */       this.command = createCommand();
/*     */     }
/*     */     
/* 169 */     boolean result = this.command.canExecute();
/* 170 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() {
/* 178 */     if (this.command != null)
/*     */     {
/* 180 */       this.command.execute();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUndo() {
/* 190 */     return !(this.command != null && !this.command.canUndo());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undo() {
/* 199 */     if (this.command != null)
/*     */     {
/* 201 */       this.command.undo();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void redo() {
/* 210 */     if (this.command != null)
/*     */     {
/* 212 */       this.command.redo();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> getResult() {
/* 223 */     return 
/* 224 */       (this.command == null) ? 
/* 225 */       Collections.EMPTY_LIST : 
/* 226 */       this.command.getResult();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> getAffectedObjects() {
/* 236 */     return 
/* 237 */       (this.command == null) ? 
/* 238 */       Collections.EMPTY_LIST : 
/* 239 */       this.command.getAffectedObjects();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 249 */     return 
/* 250 */       (this.label == null) ? (
/* 251 */       (this.command == null) ? 
/* 252 */       CommonPlugin.INSTANCE.getString("_UI_CommandWrapper_label") : 
/* 253 */       this.command.getLabel()) : 
/* 254 */       this.label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 264 */     return 
/* 265 */       (this.description == null) ? (
/* 266 */       (this.command == null) ? 
/* 267 */       CommonPlugin.INSTANCE.getString("_UI_CommandWrapper_description") : 
/* 268 */       this.command.getDescription()) : 
/* 269 */       this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 278 */     if (this.command != null)
/*     */     {
/* 280 */       this.command.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 290 */     StringBuffer result = new StringBuffer(super.toString());
/* 291 */     result.append(" (command: " + this.command + ")");
/*     */     
/* 293 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\CommandWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */