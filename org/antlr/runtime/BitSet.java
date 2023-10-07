/*     */ package org.antlr.runtime;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BitSet
/*     */   implements Cloneable
/*     */ {
/*     */   protected static final int BITS = 64;
/*     */   protected static final int LOG_BITS = 6;
/*     */   protected static final int MOD_MASK = 63;
/*     */   protected long[] bits;
/*     */   
/*     */   public BitSet() {
/*  52 */     this(64);
/*     */   }
/*     */ 
/*     */   
/*     */   public BitSet(long[] bits_) {
/*  57 */     this.bits = bits_;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitSet(List items) {
/*  62 */     this();
/*  63 */     for (int i = 0; i < items.size(); i++) {
/*  64 */       Integer v = items.get(i);
/*  65 */       add(v.intValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitSet(int nbits) {
/*  73 */     this.bits = new long[(nbits - 1 >> 6) + 1];
/*     */   }
/*     */   
/*     */   public static BitSet of(int el) {
/*  77 */     BitSet s = new BitSet(el + 1);
/*  78 */     s.add(el);
/*  79 */     return s;
/*     */   }
/*     */   
/*     */   public static BitSet of(int a, int b) {
/*  83 */     BitSet s = new BitSet(Math.max(a, b) + 1);
/*  84 */     s.add(a);
/*  85 */     s.add(b);
/*  86 */     return s;
/*     */   }
/*     */   
/*     */   public static BitSet of(int a, int b, int c) {
/*  90 */     BitSet s = new BitSet();
/*  91 */     s.add(a);
/*  92 */     s.add(b);
/*  93 */     s.add(c);
/*  94 */     return s;
/*     */   }
/*     */   
/*     */   public static BitSet of(int a, int b, int c, int d) {
/*  98 */     BitSet s = new BitSet();
/*  99 */     s.add(a);
/* 100 */     s.add(b);
/* 101 */     s.add(c);
/* 102 */     s.add(d);
/* 103 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitSet or(BitSet a) {
/* 108 */     if (a == null) {
/* 109 */       return this;
/*     */     }
/* 111 */     BitSet s = (BitSet)clone();
/* 112 */     s.orInPlace(a);
/* 113 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int el) {
/* 118 */     int n = wordNumber(el);
/* 119 */     if (n >= this.bits.length) {
/* 120 */       growToInclude(el);
/*     */     }
/* 122 */     this.bits[n] = this.bits[n] | bitMask(el);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void growToInclude(int bit) {
/* 130 */     int newSize = Math.max(this.bits.length << 1, numWordsToHold(bit));
/* 131 */     long[] newbits = new long[newSize];
/* 132 */     System.arraycopy(this.bits, 0, newbits, 0, this.bits.length);
/* 133 */     this.bits = newbits;
/*     */   }
/*     */   
/*     */   public void orInPlace(BitSet a) {
/* 137 */     if (a == null) {
/*     */       return;
/*     */     }
/*     */     
/* 141 */     if (a.bits.length > this.bits.length) {
/* 142 */       setSize(a.bits.length);
/*     */     }
/* 144 */     int min = Math.min(this.bits.length, a.bits.length);
/* 145 */     for (int i = min - 1; i >= 0; i--) {
/* 146 */       this.bits[i] = this.bits[i] | a.bits[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSize(int nwords) {
/* 155 */     long[] newbits = new long[nwords];
/* 156 */     int n = Math.min(nwords, this.bits.length);
/* 157 */     System.arraycopy(this.bits, 0, newbits, 0, n);
/* 158 */     this.bits = newbits;
/*     */   }
/*     */   
/*     */   private static final long bitMask(int bitNumber) {
/* 162 */     int bitPosition = bitNumber & 0x3F;
/* 163 */     return 1L << bitPosition;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     BitSet s;
/*     */     try {
/* 169 */       s = (BitSet)super.clone();
/* 170 */       s.bits = new long[this.bits.length];
/* 171 */       System.arraycopy(this.bits, 0, s.bits, 0, this.bits.length);
/*     */     }
/* 173 */     catch (CloneNotSupportedException e) {
/* 174 */       throw new InternalError();
/*     */     } 
/* 176 */     return s;
/*     */   }
/*     */   
/*     */   public int size() {
/* 180 */     int deg = 0;
/* 181 */     for (int i = this.bits.length - 1; i >= 0; i--) {
/* 182 */       long word = this.bits[i];
/* 183 */       if (word != 0L) {
/* 184 */         for (int bit = 63; bit >= 0; bit--) {
/* 185 */           if ((word & 1L << bit) != 0L) {
/* 186 */             deg++;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 191 */     return deg;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 195 */     if (other == null || !(other instanceof BitSet)) {
/* 196 */       return false;
/*     */     }
/*     */     
/* 199 */     BitSet otherSet = (BitSet)other;
/*     */     
/* 201 */     int n = Math.min(this.bits.length, otherSet.bits.length);
/*     */     
/*     */     int i;
/* 204 */     for (i = 0; i < n; i++) {
/* 205 */       if (this.bits[i] != otherSet.bits[i]) {
/* 206 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 212 */     if (this.bits.length > n) {
/* 213 */       for (i = n + 1; i < this.bits.length; i++) {
/* 214 */         if (this.bits[i] != 0L) {
/* 215 */           return false;
/*     */         }
/*     */       }
/*     */     
/* 219 */     } else if (otherSet.bits.length > n) {
/* 220 */       for (i = n + 1; i < otherSet.bits.length; i++) {
/* 221 */         if (otherSet.bits[i] != 0L) {
/* 222 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     return true;
/*     */   }
/*     */   
/*     */   public boolean member(int el) {
/* 231 */     if (el < 0) {
/* 232 */       return false;
/*     */     }
/* 234 */     int n = wordNumber(el);
/* 235 */     if (n >= this.bits.length) return false; 
/* 236 */     return ((this.bits[n] & bitMask(el)) != 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(int el) {
/* 241 */     int n = wordNumber(el);
/* 242 */     if (n < this.bits.length) {
/* 243 */       this.bits[n] = this.bits[n] & (bitMask(el) ^ 0xFFFFFFFFFFFFFFFFL);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isNil() {
/* 248 */     for (int i = this.bits.length - 1; i >= 0; i--) {
/* 249 */       if (this.bits[i] != 0L) return false; 
/*     */     } 
/* 251 */     return true;
/*     */   }
/*     */   
/*     */   private final int numWordsToHold(int el) {
/* 255 */     return (el >> 6) + 1;
/*     */   }
/*     */   
/*     */   public int numBits() {
/* 259 */     return this.bits.length << 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int lengthInLongWords() {
/* 266 */     return this.bits.length;
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
/*     */   public int[] toArray() {
/* 278 */     int[] elems = new int[size()];
/* 279 */     int en = 0;
/* 280 */     for (int i = 0; i < this.bits.length << 6; i++) {
/* 281 */       if (member(i)) {
/* 282 */         elems[en++] = i;
/*     */       }
/*     */     } 
/* 285 */     return elems;
/*     */   }
/*     */   
/*     */   public long[] toPackedArray() {
/* 289 */     return this.bits;
/*     */   }
/*     */   
/*     */   private static final int wordNumber(int bit) {
/* 293 */     return bit >> 6;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 297 */     return toString(null);
/*     */   }
/*     */   
/*     */   public String toString(String[] tokenNames) {
/* 301 */     StringBuffer buf = new StringBuffer();
/* 302 */     String separator = ",";
/* 303 */     boolean havePrintedAnElement = false;
/* 304 */     buf.append('{');
/*     */     
/* 306 */     for (int i = 0; i < this.bits.length << 6; i++) {
/* 307 */       if (member(i)) {
/* 308 */         if (i > 0 && havePrintedAnElement) {
/* 309 */           buf.append(separator);
/*     */         }
/* 311 */         if (tokenNames != null) {
/* 312 */           buf.append(tokenNames[i]);
/*     */         } else {
/*     */           
/* 315 */           buf.append(i);
/*     */         } 
/* 317 */         havePrintedAnElement = true;
/*     */       } 
/*     */     } 
/* 320 */     buf.append('}');
/* 321 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\antlr\runtime\BitSet.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */