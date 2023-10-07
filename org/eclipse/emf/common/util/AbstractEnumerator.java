/*     */ package org.eclipse.emf.common.util;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractEnumerator
/*     */   implements Enumerator, Serializable
/*     */ {
/*     */   private final String name;
/*     */   private final int value;
/*     */   private final String literal;
/*     */   
/*     */   protected AbstractEnumerator(int value, String name) {
/*  53 */     this.name = this.literal = name;
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractEnumerator(int value, String name, String literal) {
/*  65 */     this.name = name;
/*  66 */     this.value = value;
/*  67 */     this.literal = literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getName() {
/*  76 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getValue() {
/*  85 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getLiteral() {
/*  94 */     return this.literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 104 */     return this.literal;
/*     */   }
/*     */   
/*     */   private static class AbstractEnumeratorExternalizeable
/*     */     implements Externalizable
/*     */   {
/*     */     protected AbstractEnumerator enumerator;
/*     */     
/*     */     public AbstractEnumeratorExternalizeable(AbstractEnumerator enumerator) {
/* 113 */       this.enumerator = enumerator;
/*     */     }
/*     */ 
/*     */     
/*     */     public void writeExternal(ObjectOutput objectOutput) throws IOException {
/* 118 */       objectOutput.writeObject(this.enumerator.getClass());
/* 119 */       objectOutput.writeUTF(this.enumerator.getName());
/*     */     }
/*     */     
/* 122 */     private static final Class<?>[] SIGNATURE = new Class[] { String.class };
/*     */ 
/*     */     
/*     */     public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
/* 126 */       Class<?> _class = (Class)objectInput.readObject();
/* 127 */       String name = objectInput.readUTF();
/*     */       
/*     */       try {
/* 130 */         this.enumerator = (AbstractEnumerator)_class.getMethod("get", SIGNATURE).invoke(null, new Object[] { name });
/*     */       }
/* 132 */       catch (Exception exception) {
/*     */         
/* 134 */         IOException ioException = new IOException();
/* 135 */         ioException.initCause(exception);
/* 136 */         throw ioException;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected Object readResolve() {
/* 142 */       return this.enumerator;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object writeReplace() throws ObjectStreamException {
/* 148 */     return new AbstractEnumeratorExternalizeable(this);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\AbstractEnumerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */