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
/*     */ public abstract class AbstractCommand
/*     */   implements Command
/*     */ {
/*     */   protected boolean isPrepared;
/*     */   protected boolean isExecutable;
/*     */   protected String description;
/*     */   protected String label;
/*     */   
/*     */   protected AbstractCommand() {}
/*     */   
/*     */   protected AbstractCommand(String label) {
/*  77 */     this.label = label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractCommand(String label, String description) {
/*  87 */     this.label = label;
/*  88 */     this.description = description;
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
/* 100 */     return false;
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
/*     */   public boolean canExecute() {
/* 112 */     if (!this.isPrepared) {
/*     */       
/* 114 */       this.isExecutable = prepare();
/* 115 */       this.isPrepared = true;
/*     */     } 
/*     */     
/* 118 */     return this.isExecutable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUndo() {
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undo() {
/* 137 */     throw new UnsupportedOperationException(
/* 138 */         CommonPlugin.INSTANCE.getString(
/* 139 */           "_EXC_Method_not_implemented", new String[] { String.valueOf(getClass().getName()) + ".undo()" }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> getResult() {
/* 148 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> getAffectedObjects() {
/* 157 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 165 */     return (this.label == null) ? CommonPlugin.INSTANCE.getString("_UI_AbstractCommand_label") : this.label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabel(String label) {
/* 174 */     this.label = label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 182 */     return (this.description == null) ? CommonPlugin.INSTANCE.getString("_UI_AbstractCommand_description") : this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/* 191 */     this.description = description;
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
/*     */   public Command chain(Command command) {
/*     */     class ChainedCompoundCommand
/*     */       extends CompoundCommand
/*     */     {
/*     */       public Command chain(Command c) {
/* 212 */         append(c);
/* 213 */         return this;
/*     */       }
/*     */     };
/*     */     
/* 217 */     CompoundCommand result = new ChainedCompoundCommand();
/* 218 */     result.append(this);
/* 219 */     result.append(command);
/* 220 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 239 */     String className = getClass().getName();
/* 240 */     int lastDotIndex = className.lastIndexOf('.');
/* 241 */     StringBuffer result = new StringBuffer((lastDotIndex == -1) ? className : className.substring(lastDotIndex + 1));
/* 242 */     result.append(" (label: " + this.label + ")");
/* 243 */     result.append(" (description: " + this.description + ")");
/* 244 */     result.append(" (isPrepared: " + this.isPrepared + ")");
/* 245 */     result.append(" (isExecutable: " + this.isExecutable + ")");
/*     */     
/* 247 */     return result.toString();
/*     */   }
/*     */   
/*     */   public static interface NonDirtying {}
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\common\command\AbstractCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */