/*      */ package org.eclipse.emf.ecore.xml.type.internal;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.text.CharacterIterator;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Locale;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Vector;
/*      */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class RegEx
/*      */ {
/*      */   static class BMPattern
/*      */   {
/*      */     char[] pattern;
/*      */     int[] shiftTable;
/*      */     boolean ignoreCase;
/*      */     
/*      */     public BMPattern(String pat, boolean ignoreCase) {
/*  101 */       this(pat, 256, ignoreCase);
/*      */     }
/*      */ 
/*      */     
/*      */     public BMPattern(String pat, int tableSize, boolean ignoreCase) {
/*  106 */       this.pattern = pat.toCharArray();
/*  107 */       this.shiftTable = new int[tableSize];
/*  108 */       this.ignoreCase = ignoreCase;
/*  109 */       int length = this.pattern.length; int i;
/*  110 */       for (i = 0; i < this.shiftTable.length; i++)
/*  111 */         this.shiftTable[i] = length; 
/*  112 */       for (i = 0; i < length; i++) {
/*      */         
/*  114 */         char ch = this.pattern[i];
/*  115 */         int diff = length - i - 1;
/*  116 */         int index = ch % this.shiftTable.length;
/*  117 */         if (diff < this.shiftTable[index])
/*  118 */           this.shiftTable[index] = diff; 
/*  119 */         if (this.ignoreCase) {
/*      */           
/*  121 */           ch = Character.toUpperCase(ch);
/*  122 */           index = ch % this.shiftTable.length;
/*  123 */           if (diff < this.shiftTable[index])
/*  124 */             this.shiftTable[index] = diff; 
/*  125 */           ch = Character.toLowerCase(ch);
/*  126 */           index = ch % this.shiftTable.length;
/*  127 */           if (diff < this.shiftTable[index]) {
/*  128 */             this.shiftTable[index] = diff;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int matches(CharacterIterator iterator, int start, int limit) {
/*  139 */       if (this.ignoreCase)
/*  140 */         return matchesIgnoreCase(iterator, start, limit); 
/*  141 */       int plength = this.pattern.length;
/*  142 */       if (plength == 0)
/*  143 */         return start; 
/*  144 */       int index = start + plength;
/*  145 */       while (index <= limit) {
/*      */         
/*  147 */         int pindex = plength;
/*  148 */         int nindex = index + 1;
/*      */         
/*      */         char ch;
/*      */         
/*  152 */         while ((ch = iterator.setIndex(--index)) == this.pattern[--pindex])
/*      */         
/*  154 */         { if (pindex == 0) {
/*  155 */             return index;
/*      */           }
/*  157 */           if (pindex <= 0)
/*  158 */             break;  }  index += this.shiftTable[ch % this.shiftTable.length] + 1;
/*  159 */         if (index < nindex)
/*  160 */           index = nindex; 
/*      */       } 
/*  162 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int matches(String str, int start, int limit) {
/*  171 */       if (this.ignoreCase)
/*  172 */         return matchesIgnoreCase(str, start, limit); 
/*  173 */       int plength = this.pattern.length;
/*  174 */       if (plength == 0)
/*  175 */         return start; 
/*  176 */       int index = start + plength;
/*  177 */       while (index <= limit) {
/*      */ 
/*      */         
/*  180 */         int pindex = plength;
/*  181 */         int nindex = index + 1;
/*      */         
/*      */         char ch;
/*      */         
/*  185 */         while ((ch = str.charAt(--index)) == this.pattern[--pindex])
/*      */         
/*  187 */         { if (pindex == 0) {
/*  188 */             return index;
/*      */           }
/*  190 */           if (pindex <= 0)
/*  191 */             break;  }  index += this.shiftTable[ch % this.shiftTable.length] + 1;
/*  192 */         if (index < nindex)
/*  193 */           index = nindex; 
/*      */       } 
/*  195 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int matches(char[] chars, int start, int limit) {
/*  204 */       if (this.ignoreCase)
/*  205 */         return matchesIgnoreCase(chars, start, limit); 
/*  206 */       int plength = this.pattern.length;
/*  207 */       if (plength == 0)
/*  208 */         return start; 
/*  209 */       int index = start + plength;
/*  210 */       while (index <= limit) {
/*      */ 
/*      */         
/*  213 */         int pindex = plength;
/*  214 */         int nindex = index + 1;
/*      */         
/*      */         char ch;
/*      */         
/*  218 */         while ((ch = chars[--index]) == this.pattern[--pindex])
/*      */         
/*  220 */         { if (pindex == 0) {
/*  221 */             return index;
/*      */           }
/*  223 */           if (pindex <= 0)
/*  224 */             break;  }  index += this.shiftTable[ch % this.shiftTable.length] + 1;
/*  225 */         if (index < nindex)
/*  226 */           index = nindex; 
/*      */       } 
/*  228 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     int matchesIgnoreCase(CharacterIterator iterator, int start, int limit) {
/*  233 */       int plength = this.pattern.length;
/*  234 */       if (plength == 0)
/*  235 */         return start; 
/*  236 */       int index = start + plength;
/*  237 */       while (index <= limit) {
/*      */         char ch;
/*  239 */         int pindex = plength;
/*  240 */         int nindex = index + 1;
/*      */ 
/*      */         
/*      */         do {
/*  244 */           char ch1 = ch = iterator.setIndex(--index);
/*  245 */           char ch2 = this.pattern[--pindex];
/*  246 */           if (ch1 != ch2) {
/*      */             
/*  248 */             ch1 = Character.toUpperCase(ch1);
/*  249 */             ch2 = Character.toUpperCase(ch2);
/*  250 */             if (ch1 != ch2 && Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
/*      */               break; 
/*      */           } 
/*  253 */           if (pindex == 0) {
/*  254 */             return index;
/*      */           }
/*  256 */         } while (pindex > 0);
/*  257 */         index += this.shiftTable[ch % this.shiftTable.length] + 1;
/*  258 */         if (index < nindex)
/*  259 */           index = nindex; 
/*      */       } 
/*  261 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     int matchesIgnoreCase(String text, int start, int limit) {
/*  266 */       int plength = this.pattern.length;
/*  267 */       if (plength == 0)
/*  268 */         return start; 
/*  269 */       int index = start + plength;
/*  270 */       while (index <= limit) {
/*      */         char ch;
/*  272 */         int pindex = plength;
/*  273 */         int nindex = index + 1;
/*      */ 
/*      */         
/*      */         do {
/*  277 */           char ch1 = ch = text.charAt(--index);
/*  278 */           char ch2 = this.pattern[--pindex];
/*  279 */           if (ch1 != ch2) {
/*      */             
/*  281 */             ch1 = Character.toUpperCase(ch1);
/*  282 */             ch2 = Character.toUpperCase(ch2);
/*  283 */             if (ch1 != ch2 && Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
/*      */               break; 
/*      */           } 
/*  286 */           if (pindex == 0) {
/*  287 */             return index;
/*      */           }
/*  289 */         } while (pindex > 0);
/*  290 */         index += this.shiftTable[ch % this.shiftTable.length] + 1;
/*  291 */         if (index < nindex)
/*  292 */           index = nindex; 
/*      */       } 
/*  294 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*      */     int matchesIgnoreCase(char[] chars, int start, int limit) {
/*  299 */       int plength = this.pattern.length;
/*  300 */       if (plength == 0)
/*  301 */         return start; 
/*  302 */       int index = start + plength;
/*  303 */       while (index <= limit) {
/*      */         char ch;
/*  305 */         int pindex = plength;
/*  306 */         int nindex = index + 1;
/*      */ 
/*      */         
/*      */         do {
/*  310 */           char ch1 = ch = chars[--index];
/*  311 */           char ch2 = this.pattern[--pindex];
/*  312 */           if (ch1 != ch2) {
/*      */             
/*  314 */             ch1 = Character.toUpperCase(ch1);
/*  315 */             ch2 = Character.toUpperCase(ch2);
/*  316 */             if (ch1 != ch2 && Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
/*      */               break; 
/*      */           } 
/*  319 */           if (pindex == 0) {
/*  320 */             return index;
/*      */           }
/*  322 */         } while (pindex > 0);
/*  323 */         index += this.shiftTable[ch % this.shiftTable.length] + 1;
/*  324 */         if (index < nindex)
/*  325 */           index = nindex; 
/*      */       } 
/*  327 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Match
/*      */     implements Cloneable
/*      */   {
/*  348 */     int[] beginpos = null;
/*  349 */     int[] endpos = null;
/*  350 */     int nofgroups = 0;
/*      */     
/*  352 */     CharacterIterator ciSource = null;
/*  353 */     String strSource = null;
/*  354 */     char[] charSource = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized Object clone() {
/*  368 */       Match ma = new Match();
/*  369 */       if (this.nofgroups > 0) {
/*  370 */         ma.setNumberOfGroups(this.nofgroups);
/*  371 */         if (this.ciSource != null) ma.setSource(this.ciSource); 
/*  372 */         if (this.strSource != null) ma.setSource(this.strSource); 
/*  373 */         for (int i = 0; i < this.nofgroups; i++) {
/*  374 */           ma.setBeginning(i, getBeginning(i));
/*  375 */           ma.setEnd(i, getEnd(i));
/*      */         } 
/*      */       } 
/*  378 */       return ma;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setNumberOfGroups(int n) {
/*  385 */       int oldn = this.nofgroups;
/*  386 */       this.nofgroups = n;
/*  387 */       if (oldn <= 0 || 
/*  388 */         oldn < n || n * 2 < oldn) {
/*  389 */         this.beginpos = new int[n];
/*  390 */         this.endpos = new int[n];
/*      */       } 
/*  392 */       for (int i = 0; i < n; i++) {
/*  393 */         this.beginpos[i] = -1;
/*  394 */         this.endpos[i] = -1;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setSource(CharacterIterator ci) {
/*  402 */       this.ciSource = ci;
/*  403 */       this.strSource = null;
/*  404 */       this.charSource = null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setSource(String str) {
/*  410 */       this.ciSource = null;
/*  411 */       this.strSource = str;
/*  412 */       this.charSource = null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setSource(char[] chars) {
/*  418 */       this.ciSource = null;
/*  419 */       this.strSource = null;
/*  420 */       this.charSource = chars;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setBeginning(int index, int v) {
/*  427 */       this.beginpos[index] = v;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setEnd(int index, int v) {
/*  434 */       this.endpos[index] = v;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumberOfGroups() {
/*  442 */       if (this.nofgroups <= 0)
/*  443 */         throw new IllegalStateException("A result is not set."); 
/*  444 */       return this.nofgroups;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getBeginning(int index) {
/*  453 */       if (this.beginpos == null)
/*  454 */         throw new IllegalStateException("A result is not set."); 
/*  455 */       if (index < 0 || this.nofgroups <= index)
/*  456 */         throw new IllegalArgumentException("The parameter must be less than " + 
/*  457 */             this.nofgroups + ": " + index); 
/*  458 */       return this.beginpos[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getEnd(int index) {
/*  467 */       if (this.endpos == null)
/*  468 */         throw new IllegalStateException("A result is not set."); 
/*  469 */       if (index < 0 || this.nofgroups <= index)
/*  470 */         throw new IllegalArgumentException("The parameter must be less than " + 
/*  471 */             this.nofgroups + ": " + index); 
/*  472 */       return this.endpos[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getCapturedText(int index) {
/*      */       String ret;
/*  481 */       if (this.beginpos == null)
/*  482 */         throw new IllegalStateException("match() has never been called."); 
/*  483 */       if (index < 0 || this.nofgroups <= index) {
/*  484 */         throw new IllegalArgumentException("The parameter must be less than " + 
/*  485 */             this.nofgroups + ": " + index);
/*      */       }
/*  487 */       int begin = this.beginpos[index], end = this.endpos[index];
/*  488 */       if (begin < 0 || end < 0) return null; 
/*  489 */       if (this.ciSource != null) {
/*  490 */         ret = RegEx.REUtil.substring(this.ciSource, begin, end);
/*  491 */       } else if (this.strSource != null) {
/*  492 */         ret = this.strSource.substring(begin, end);
/*      */       } else {
/*  494 */         ret = new String(this.charSource, begin, end - begin);
/*      */       } 
/*  496 */       return ret;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static final class REUtil
/*      */   {
/*      */     static final int CACHESIZE = 20;
/*      */     
/*      */     static final int composeFromSurrogates(int high, int low) {
/*  506 */       return 65536 + (high - 55296 << 10) + low - 56320;
/*      */     }
/*      */     
/*      */     static final boolean isLowSurrogate(int ch) {
/*  510 */       return ((ch & 0xFC00) == 56320);
/*      */     }
/*      */     
/*      */     static final boolean isHighSurrogate(int ch) {
/*  514 */       return ((ch & 0xFC00) == 55296);
/*      */     }
/*      */     
/*      */     static final String decomposeToSurrogates(int ch) {
/*  518 */       char[] chs = new char[2];
/*  519 */       ch -= 65536;
/*  520 */       chs[0] = (char)((ch >> 10) + 55296);
/*  521 */       chs[1] = (char)((ch & 0x3FF) + 56320);
/*  522 */       return new String(chs);
/*      */     }
/*      */     
/*      */     static final String substring(CharacterIterator iterator, int begin, int end) {
/*  526 */       char[] src = new char[end - begin];
/*  527 */       for (int i = 0; i < src.length; i++)
/*  528 */         src[i] = iterator.setIndex(i + begin); 
/*  529 */       return new String(src);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static final int getOptionValue(int ch) {
/*  535 */       int ret = 0;
/*  536 */       switch (ch) {
/*      */         case 105:
/*  538 */           ret = 2;
/*      */           break;
/*      */         case 109:
/*  541 */           ret = 8;
/*      */           break;
/*      */         case 115:
/*  544 */           ret = 4;
/*      */           break;
/*      */         case 120:
/*  547 */           ret = 16;
/*      */           break;
/*      */         case 117:
/*  550 */           ret = 32;
/*      */           break;
/*      */         case 119:
/*  553 */           ret = 64;
/*      */           break;
/*      */         case 70:
/*  556 */           ret = 256;
/*      */           break;
/*      */         case 72:
/*  559 */           ret = 128;
/*      */           break;
/*      */         case 88:
/*  562 */           ret = 512;
/*      */           break;
/*      */         case 44:
/*  565 */           ret = 1024;
/*      */           break;
/*      */       } 
/*      */       
/*  569 */       return ret;
/*      */     }
/*      */     
/*      */     static final int parseOptions(String opts) throws RegEx.ParseException {
/*  573 */       if (opts == null) return 0; 
/*  574 */       int options = 0;
/*  575 */       for (int i = 0; i < opts.length(); i++) {
/*  576 */         int v = getOptionValue(opts.charAt(i));
/*  577 */         if (v == 0)
/*  578 */           throw new RegEx.ParseException("Unknown Option: " + opts.substring(i), -1); 
/*  579 */         options |= v;
/*      */       } 
/*  581 */       return options;
/*      */     }
/*      */     
/*      */     static final String createOptionString(int options) {
/*  585 */       StringBuffer sb = new StringBuffer(9);
/*  586 */       if ((options & 0x100) != 0)
/*  587 */         sb.append('F'); 
/*  588 */       if ((options & 0x80) != 0)
/*  589 */         sb.append('H'); 
/*  590 */       if ((options & 0x200) != 0)
/*  591 */         sb.append('X'); 
/*  592 */       if ((options & 0x2) != 0)
/*  593 */         sb.append('i'); 
/*  594 */       if ((options & 0x8) != 0)
/*  595 */         sb.append('m'); 
/*  596 */       if ((options & 0x4) != 0)
/*  597 */         sb.append('s'); 
/*  598 */       if ((options & 0x20) != 0)
/*  599 */         sb.append('u'); 
/*  600 */       if ((options & 0x40) != 0)
/*  601 */         sb.append('w'); 
/*  602 */       if ((options & 0x10) != 0)
/*  603 */         sb.append('x'); 
/*  604 */       if ((options & 0x400) != 0)
/*  605 */         sb.append(','); 
/*  606 */       return sb.toString().intern();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static String stripExtendedComment(String regex) {
/*  612 */       int len = regex.length();
/*  613 */       StringBuffer buffer = new StringBuffer(len);
/*  614 */       int offset = 0;
/*  615 */       while (offset < len) {
/*  616 */         int ch = regex.charAt(offset++);
/*      */         
/*  618 */         if (ch == 9 || ch == 10 || ch == 12 || ch == 13 || ch == 32) {
/*      */           continue;
/*      */         }
/*  621 */         if (ch == 35) {
/*  622 */           while (offset < len) {
/*  623 */             ch = regex.charAt(offset++);
/*  624 */             if (ch == 13 || ch == 10) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/*  631 */         if (ch == 92 && offset < len) {
/*  632 */           int next; if ((next = regex.charAt(offset)) == 35 || 
/*  633 */             next == 9 || next == 10 || next == 12 || 
/*  634 */             next == 13 || next == 32) {
/*  635 */             buffer.append((char)next);
/*  636 */             offset++; continue;
/*      */           } 
/*  638 */           buffer.append('\\');
/*  639 */           buffer.append((char)next);
/*  640 */           offset++;
/*      */           continue;
/*      */         } 
/*  643 */         buffer.append((char)ch);
/*      */       } 
/*  645 */       return buffer.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static void main(String[] argv) {
/*  655 */       String pattern = null;
/*      */       try {
/*  657 */         String options = "";
/*  658 */         String target = null;
/*  659 */         if (argv.length == 0) {
/*  660 */           System.out.println("Error:Usage: java REUtil -i|-m|-s|-u|-w|-X regularExpression String");
/*  661 */           System.exit(0);
/*      */         } 
/*  663 */         for (int i = 0; i < argv.length; i++) {
/*  664 */           if (argv[i].length() == 0 || argv[i].charAt(0) != '-') {
/*  665 */             if (pattern == null)
/*  666 */             { pattern = argv[i]; }
/*  667 */             else if (target == null)
/*  668 */             { target = argv[i]; }
/*      */             else
/*  670 */             { System.err.println("Unnecessary: " + argv[i]); } 
/*  671 */           } else if (argv[i].equals("-i")) {
/*  672 */             options = String.valueOf(options) + "i";
/*  673 */           } else if (argv[i].equals("-m")) {
/*  674 */             options = String.valueOf(options) + "m";
/*  675 */           } else if (argv[i].equals("-s")) {
/*  676 */             options = String.valueOf(options) + "s";
/*  677 */           } else if (argv[i].equals("-u")) {
/*  678 */             options = String.valueOf(options) + "u";
/*  679 */           } else if (argv[i].equals("-w")) {
/*  680 */             options = String.valueOf(options) + "w";
/*  681 */           } else if (argv[i].equals("-X")) {
/*  682 */             options = String.valueOf(options) + "X";
/*      */           } else {
/*  684 */             System.err.println("Unknown option: " + argv[i]);
/*      */           } 
/*      */         } 
/*  687 */         RegEx.RegularExpression reg = new RegEx.RegularExpression(pattern, options);
/*  688 */         System.out.println("RegularExpression: " + reg);
/*  689 */         RegEx.Match match = new RegEx.Match();
/*  690 */         reg.matches(target, match);
/*  691 */         for (int j = 0; j < match.getNumberOfGroups(); j++) {
/*  692 */           if (j == 0) { System.out.print("Matched range for the whole pattern: "); }
/*  693 */           else { System.out.print("[" + j + "]: "); }
/*  694 */            if (match.getBeginning(j) < 0) {
/*  695 */             System.out.println("-1");
/*      */           } else {
/*  697 */             System.out.print(String.valueOf(match.getBeginning(j)) + ", " + match.getEnd(j) + ", ");
/*  698 */             System.out.println("\"" + match.getCapturedText(j) + "\"");
/*      */           } 
/*      */         } 
/*  701 */       } catch (ParseException pe) {
/*  702 */         if (pattern == null) {
/*  703 */           pe.printStackTrace();
/*      */         } else {
/*  705 */           System.err.println("org.apache.xerces.utils.regex.ParseException: " + pe.getMessage());
/*  706 */           String indent = "        ";
/*  707 */           System.err.println(String.valueOf(indent) + pattern);
/*  708 */           int loc = pe.getLocation();
/*  709 */           if (loc >= 0) {
/*  710 */             System.err.print(indent);
/*  711 */             for (int i = 0; i < loc; ) { System.err.print("-"); i++; }
/*  712 */              System.err.println("^");
/*      */           } 
/*      */         } 
/*  715 */       } catch (Exception e) {
/*  716 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  721 */     static final RegEx.RegularExpression[] regexCache = new RegEx.RegularExpression[20];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static RegEx.RegularExpression createRegex(String pattern, String options) throws RegEx.ParseException {
/*  730 */       RegEx.RegularExpression re = null;
/*  731 */       int intOptions = parseOptions(options);
/*  732 */       synchronized (regexCache) {
/*      */         int i;
/*  734 */         for (i = 0; i < 20; i++) {
/*  735 */           RegEx.RegularExpression cached = regexCache[i];
/*  736 */           if (cached == null) {
/*  737 */             i = -1;
/*      */             break;
/*      */           } 
/*  740 */           if (cached.equals(pattern, intOptions)) {
/*  741 */             re = cached;
/*      */             break;
/*      */           } 
/*      */         } 
/*  745 */         if (re != null) {
/*  746 */           if (i != 0) {
/*  747 */             System.arraycopy(regexCache, 0, regexCache, 1, i);
/*  748 */             regexCache[0] = re;
/*      */           } 
/*      */         } else {
/*  751 */           re = new RegEx.RegularExpression(pattern, options);
/*  752 */           System.arraycopy(regexCache, 0, regexCache, 1, 19);
/*  753 */           regexCache[0] = re;
/*      */         } 
/*      */       } 
/*  756 */       return re;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean matches(String regex, String target) throws RegEx.ParseException {
/*  764 */       return createRegex(regex, null).matches(target);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static boolean matches(String regex, String options, String target) throws RegEx.ParseException {
/*  772 */       return createRegex(regex, options).matches(target);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static String quoteMeta(String literal) {
/*  781 */       int len = literal.length();
/*  782 */       StringBuffer buffer = null;
/*  783 */       for (int i = 0; i < len; i++) {
/*  784 */         int ch = literal.charAt(i);
/*  785 */         if (".*+?{[()|\\^$".indexOf(ch) >= 0) {
/*  786 */           if (buffer == null) {
/*  787 */             buffer = new StringBuffer(i + (len - i) * 2);
/*  788 */             if (i > 0) buffer.append(literal.substring(0, i)); 
/*      */           } 
/*  790 */           buffer.append('\\');
/*  791 */           buffer.append((char)ch);
/*  792 */         } else if (buffer != null) {
/*  793 */           buffer.append((char)ch);
/*      */         } 
/*  795 */       }  return (buffer != null) ? buffer.toString() : literal;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void dumpString(String v) {
/*  801 */       for (int i = 0; i < v.length(); i++) {
/*  802 */         System.out.print(Integer.toHexString(v.charAt(i)));
/*  803 */         System.out.print(" ");
/*      */       } 
/*  805 */       System.out.println();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RegularExpression
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static final boolean DEBUG = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String regex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int options;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int nofparen;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token tokentree;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private synchronized void compile(RegEx.Token tok)
/*      */     {
/* 1278 */       if (this.operations != null)
/*      */         return; 
/* 1280 */       this.numberOfClosures = 0;
/* 1281 */       this.operations = compile(tok, null, false); } private RegEx.Op compile(RegEx.Token tok, RegEx.Op next, boolean reverse) { RegEx.Op ret; RegEx.Op.UnionOp uni; int i; RegEx.Token child;
/*      */       int min;
/*      */       int max;
/*      */       RegEx.Token.ConditionToken ctok;
/*      */       int ref;
/*      */       RegEx.Op condition;
/*      */       RegEx.Op yes;
/*      */       RegEx.Op no;
/* 1289 */       switch (tok.type) {
/*      */         case 11:
/* 1291 */           ret = RegEx.Op.createDot();
/* 1292 */           ret.next = next;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1439 */           return ret;case 0: ret = RegEx.Op.createChar(tok.getChar()); ret.next = next; return ret;case 8: ret = RegEx.Op.createAnchor(tok.getChar()); ret.next = next; return ret;case 4: case 5: ret = RegEx.Op.createRange(tok); ret.next = next; return ret;case 1: ret = next; if (!reverse) { for (int j = tok.size() - 1; j >= 0; j--) ret = compile(tok.getChild(j), ret, false);  } else { for (int j = 0; j < tok.size(); j++) ret = compile(tok.getChild(j), ret, true);  }  return ret;case 2: uni = RegEx.Op.createUnion(tok.size()); for (i = 0; i < tok.size(); i++) uni.addElement(compile(tok.getChild(i), next, reverse));  ret = uni; return ret;case 3: case 9: child = tok.getChild(0); min = tok.getMin(); max = tok.getMax(); if (min >= 0 && min == max) { ret = next; for (int j = 0; j < min; j++) ret = compile(child, ret, reverse);  } else { if (min > 0 && max > 0) max -= min;  if (max > 0) { ret = next; for (int j = 0; j < max; j++) { RegEx.Op.ChildOp q = RegEx.Op.createQuestion((tok.type == 9)); q.next = next; q.setChild(compile(child, ret, reverse)); ret = q; }  } else { RegEx.Op.ChildOp op; if (tok.type == 9) { op = RegEx.Op.createNonGreedyClosure(); } else if (child.getMinLength() == 0) { op = RegEx.Op.createClosure(this.numberOfClosures++); } else { op = RegEx.Op.createClosure(-1); }  op.next = next; op.setChild(compile(child, op, reverse)); ret = op; }  if (min > 0) for (int j = 0; j < min; j++) ret = compile(child, ret, reverse);   }  return ret;case 7: ret = next; return ret;case 10: ret = RegEx.Op.createString(tok.getString()); ret.next = next; return ret;case 12: ret = RegEx.Op.createBackReference(tok.getReferenceNumber()); ret.next = next; return ret;case 6: if (tok.getParenNumber() == 0) { ret = compile(tok.getChild(0), next, reverse); } else if (reverse) { next = RegEx.Op.createCapture(tok.getParenNumber(), next); next = compile(tok.getChild(0), next, reverse); ret = RegEx.Op.createCapture(-tok.getParenNumber(), next); } else { next = RegEx.Op.createCapture(-tok.getParenNumber(), next); next = compile(tok.getChild(0), next, reverse); ret = RegEx.Op.createCapture(tok.getParenNumber(), next); }  return ret;case 20: ret = RegEx.Op.createLook(20, next, compile(tok.getChild(0), null, false)); return ret;case 21: ret = RegEx.Op.createLook(21, next, compile(tok.getChild(0), null, false)); return ret;case 22: ret = RegEx.Op.createLook(22, next, compile(tok.getChild(0), null, true)); return ret;case 23: ret = RegEx.Op.createLook(23, next, compile(tok.getChild(0), null, true)); return ret;case 24: ret = RegEx.Op.createIndependent(next, compile(tok.getChild(0), null, reverse)); return ret;case 25: ret = RegEx.Op.createModifier(next, compile(tok.getChild(0), null, reverse), ((RegEx.Token.ModifierToken)tok).getOptions(), ((RegEx.Token.ModifierToken)tok).getOptionsMask()); return ret;case 26: ctok = (RegEx.Token.ConditionToken)tok; ref = ctok.refNumber; condition = (ctok.condition == null) ? null : compile(ctok.condition, null, reverse); yes = compile(ctok.yes, next, reverse); no = (ctok.no == null) ? null : compile(ctok.no, next, reverse); ret = RegEx.Op.createCondition(next, ref, condition, yes, no); return ret;
/*      */       } 
/*      */       throw new RuntimeException("Unknown token type: " + tok.type); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(char[] target) {
/* 1451 */       return matches(target, 0, target.length, (RegEx.Match)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(char[] target, int start, int end) {
/* 1463 */       return matches(target, start, end, (RegEx.Match)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(char[] target, RegEx.Match match) {
/* 1473 */       return matches(target, 0, target.length, match);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(char[] target, int start, int end, RegEx.Match match) {
/*      */       int matchStart;
/* 1488 */       synchronized (this) {
/* 1489 */         if (this.operations == null)
/* 1490 */           prepare(); 
/* 1491 */         if (this.context == null)
/* 1492 */           this.context = new Context(); 
/*      */       } 
/* 1494 */       Context con = null;
/* 1495 */       synchronized (this.context) {
/* 1496 */         con = this.context.inuse ? new Context() : this.context;
/* 1497 */         con.reset(target, start, end, this.numberOfClosures);
/*      */       } 
/* 1499 */       if (match != null) {
/* 1500 */         match.setNumberOfGroups(this.nofparen);
/* 1501 */         match.setSource(target);
/* 1502 */       } else if (this.hasBackReferences) {
/* 1503 */         match = new RegEx.Match();
/* 1504 */         match.setNumberOfGroups(this.nofparen);
/*      */       } 
/*      */ 
/*      */       
/* 1508 */       con.match = match;
/*      */       
/* 1510 */       if (isSet(this.options, 512)) {
/* 1511 */         int i = matchCharArray(con, this.operations, con.start, 1, this.options);
/*      */         
/* 1513 */         if (i == con.limit) {
/* 1514 */           if (con.match != null) {
/* 1515 */             con.match.setBeginning(0, con.start);
/* 1516 */             con.match.setEnd(0, i);
/*      */           } 
/* 1518 */           con.inuse = false;
/* 1519 */           return true;
/*      */         } 
/* 1521 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1528 */       if (this.fixedStringOnly) {
/*      */         
/* 1530 */         int o = this.fixedStringTable.matches(target, con.start, con.limit);
/* 1531 */         if (o >= 0) {
/* 1532 */           if (con.match != null) {
/* 1533 */             con.match.setBeginning(0, o);
/* 1534 */             con.match.setEnd(0, o + this.fixedString.length());
/*      */           } 
/* 1536 */           con.inuse = false;
/* 1537 */           return true;
/*      */         } 
/* 1539 */         con.inuse = false;
/* 1540 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1548 */       if (this.fixedString != null) {
/* 1549 */         int o = this.fixedStringTable.matches(target, con.start, con.limit);
/* 1550 */         if (o < 0) {
/*      */           
/* 1552 */           con.inuse = false;
/* 1553 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 1557 */       int limit = con.limit - this.minlength;
/*      */       
/* 1559 */       int matchEnd = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1564 */       if (this.operations != null && 
/* 1565 */         this.operations.type == 7 && (this.operations.getChild()).type == 0) {
/* 1566 */         if (isSet(this.options, 4)) {
/* 1567 */           matchStart = con.start;
/* 1568 */           matchEnd = matchCharArray(con, this.operations, con.start, 1, this.options);
/*      */         } else {
/* 1570 */           boolean previousIsEOL = true;
/* 1571 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 1572 */             int ch = target[matchStart];
/* 1573 */             if (isEOLChar(ch)) {
/* 1574 */               previousIsEOL = true;
/*      */             } else {
/* 1576 */               if (previousIsEOL)
/* 1577 */                 if ((matchEnd = matchCharArray(con, this.operations, 
/* 1578 */                     matchStart, 1, this.options)) >= 0) {
/*      */                   break;
/*      */                 } 
/* 1581 */               previousIsEOL = false;
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1590 */       else if (this.firstChar != null) {
/*      */         
/* 1592 */         RegEx.RangeToken range = this.firstChar;
/* 1593 */         if (isSet(this.options, 2)) {
/* 1594 */           range = this.firstChar.getCaseInsensitiveToken();
/* 1595 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 1596 */             int ch = target[matchStart];
/* 1597 */             if (RegEx.REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
/* 1598 */               ch = RegEx.REUtil.composeFromSurrogates(ch, target[matchStart + 1]);
/* 1599 */               if (!range.match(ch))
/*      */                 continue; 
/* 1601 */             } else if (!range.match(ch)) {
/* 1602 */               char ch1 = Character.toUpperCase((char)ch);
/* 1603 */               if (!range.match(ch1) && 
/* 1604 */                 !range.match(Character.toLowerCase(ch1))) {
/*      */                 continue;
/*      */               }
/*      */             } 
/* 1608 */             if ((matchEnd = matchCharArray(con, this.operations, 
/* 1609 */                 matchStart, 1, this.options)) >= 0)
/*      */               break;  continue;
/*      */           } 
/*      */         } else {
/* 1613 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 1614 */             int ch = target[matchStart];
/* 1615 */             if (RegEx.REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit)
/* 1616 */               ch = RegEx.REUtil.composeFromSurrogates(ch, target[matchStart + 1]); 
/* 1617 */             if (range.match(ch)) {
/* 1618 */               if ((matchEnd = matchCharArray(con, this.operations, 
/* 1619 */                   matchStart, 1, this.options)) >= 0) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1629 */         for (matchStart = con.start; matchStart <= limit && (
/* 1630 */           matchEnd = matchCharArray(con, this.operations, matchStart, 1, this.options)) < 0; matchStart++);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1635 */       if (matchEnd >= 0) {
/* 1636 */         if (con.match != null) {
/* 1637 */           con.match.setBeginning(0, matchStart);
/* 1638 */           con.match.setEnd(0, matchEnd);
/*      */         } 
/* 1640 */         con.inuse = false;
/* 1641 */         return true;
/*      */       } 
/* 1643 */       con.inuse = false;
/* 1644 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int matchCharArray(Context con, RegEx.Op op, int offset, int dx, int opts) {
/* 1653 */       char[] target = con.charTarget; while (true) {
/*      */         boolean go; int after; int j; String literal; int id; int ret; int i; int refno; int before; int o2; int literallen; int k; int localopts; RegEx.Op.ConditionOp cop; int n;
/*      */         int m;
/*      */         boolean matchp;
/* 1657 */         if (op == null)
/* 1658 */           return (isSet(opts, 512) && offset != con.limit) ? -1 : offset; 
/* 1659 */         if (offset > con.limit || offset < con.start)
/* 1660 */           return -1; 
/* 1661 */         switch (op.type) {
/*      */           case 1:
/* 1663 */             if (isSet(opts, 2)) {
/* 1664 */               int ch = op.getData();
/* 1665 */               if (dx > 0) {
/* 1666 */                 if (offset >= con.limit || !matchIgnoreCase(ch, target[offset]))
/* 1667 */                   return -1; 
/* 1668 */                 offset++;
/*      */               } else {
/* 1670 */                 int o1 = offset - 1;
/* 1671 */                 if (o1 >= con.limit || o1 < 0 || !matchIgnoreCase(ch, target[o1]))
/* 1672 */                   return -1; 
/* 1673 */                 offset = o1;
/*      */               } 
/*      */             } else {
/* 1676 */               int ch = op.getData();
/* 1677 */               if (dx > 0) {
/* 1678 */                 if (offset >= con.limit || ch != target[offset])
/* 1679 */                   return -1; 
/* 1680 */                 offset++;
/*      */               } else {
/* 1682 */                 int o1 = offset - 1;
/* 1683 */                 if (o1 >= con.limit || o1 < 0 || ch != target[o1])
/* 1684 */                   return -1; 
/* 1685 */                 offset = o1;
/*      */               } 
/*      */             } 
/* 1688 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 0:
/* 1692 */             if (dx > 0) {
/* 1693 */               if (offset >= con.limit)
/* 1694 */                 return -1; 
/* 1695 */               int ch = target[offset];
/* 1696 */               if (isSet(opts, 4)) {
/* 1697 */                 if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 1698 */                   offset++; 
/*      */               } else {
/* 1700 */                 if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 1701 */                   ch = RegEx.REUtil.composeFromSurrogates(ch, target[++offset]); 
/* 1702 */                 if (isEOLChar(ch))
/* 1703 */                   return -1; 
/*      */               } 
/* 1705 */               offset++;
/*      */             } else {
/* 1707 */               int o1 = offset - 1;
/* 1708 */               if (o1 >= con.limit || o1 < 0)
/* 1709 */                 return -1; 
/* 1710 */               int ch = target[o1];
/* 1711 */               if (isSet(opts, 4)) {
/* 1712 */                 if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 1713 */                   o1--; 
/*      */               } else {
/* 1715 */                 if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 1716 */                   ch = RegEx.REUtil.composeFromSurrogates(target[--o1], ch); 
/* 1717 */                 if (!isEOLChar(ch))
/* 1718 */                   return -1; 
/*      */               } 
/* 1720 */               offset = o1;
/*      */             } 
/* 1722 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 3:
/*      */           case 4:
/* 1727 */             if (dx > 0) {
/* 1728 */               if (offset >= con.limit)
/* 1729 */                 return -1; 
/* 1730 */               int ch = target[offset];
/* 1731 */               if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 1732 */                 ch = RegEx.REUtil.composeFromSurrogates(ch, target[++offset]); 
/* 1733 */               RegEx.RangeToken tok = op.getToken();
/* 1734 */               if (isSet(opts, 2))
/* 1735 */               { tok = tok.getCaseInsensitiveToken();
/* 1736 */                 if (!tok.match(ch)) {
/* 1737 */                   if (ch >= 65536) return -1; 
/*      */                   char uch;
/* 1739 */                   if (!tok.match(uch = Character.toUpperCase((char)ch)) && 
/* 1740 */                     !tok.match(Character.toLowerCase(uch))) {
/* 1741 */                     return -1;
/*      */                   }
/*      */                 }  }
/* 1744 */               else if (!tok.match(ch)) { return -1; }
/*      */               
/* 1746 */               offset++;
/*      */             } else {
/* 1748 */               int o1 = offset - 1;
/* 1749 */               if (o1 >= con.limit || o1 < 0)
/* 1750 */                 return -1; 
/* 1751 */               int ch = target[o1];
/* 1752 */               if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 1753 */                 ch = RegEx.REUtil.composeFromSurrogates(target[--o1], ch); 
/* 1754 */               RegEx.RangeToken tok = op.getToken();
/* 1755 */               if (isSet(opts, 2))
/* 1756 */               { tok = tok.getCaseInsensitiveToken();
/* 1757 */                 if (!tok.match(ch)) {
/* 1758 */                   if (ch >= 65536) return -1; 
/*      */                   char uch;
/* 1760 */                   if (!tok.match(uch = Character.toUpperCase((char)ch)) && 
/* 1761 */                     !tok.match(Character.toLowerCase(uch))) {
/* 1762 */                     return -1;
/*      */                   }
/*      */                 }  }
/* 1765 */               else if (!tok.match(ch)) { return -1; }
/*      */               
/* 1767 */               offset = o1;
/*      */             } 
/* 1769 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 5:
/* 1773 */             go = false;
/* 1774 */             switch (op.getData()) {
/*      */               case 94:
/* 1776 */                 if (isSet(opts, 8)) {
/* 1777 */                   if (offset != con.start && (
/* 1778 */                     offset <= con.start || !isEOLChar(target[offset - 1])))
/* 1779 */                     return -1;  break;
/*      */                 } 
/* 1781 */                 if (offset != con.start) {
/* 1782 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 64:
/* 1788 */                 if (offset != con.start && (
/* 1789 */                   offset <= con.start || !isEOLChar(target[offset - 1]))) {
/* 1790 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 36:
/* 1794 */                 if (isSet(opts, 8)) {
/* 1795 */                   if (offset != con.limit && (
/* 1796 */                     offset >= con.limit || !isEOLChar(target[offset])))
/* 1797 */                     return -1;  break;
/*      */                 } 
/* 1799 */                 if (offset != con.limit && (
/* 1800 */                   offset + 1 != con.limit || !isEOLChar(target[offset])) && (
/* 1801 */                   offset + 2 != con.limit || target[offset] != '\r' || 
/* 1802 */                   target[offset + 1] != '\n')) {
/* 1803 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 65:
/* 1808 */                 if (offset != con.start) return -1;
/*      */                 
/*      */                 break;
/*      */               case 90:
/* 1812 */                 if (offset != con.limit && (
/* 1813 */                   offset + 1 != con.limit || !isEOLChar(target[offset])) && (
/* 1814 */                   offset + 2 != con.limit || target[offset] != '\r' || 
/* 1815 */                   target[offset + 1] != '\n')) {
/* 1816 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 122:
/* 1820 */                 if (offset != con.limit) return -1;
/*      */                 
/*      */                 break;
/*      */               case 98:
/* 1824 */                 if (con.length == 0) return -1;
/*      */                 
/* 1826 */                 after = getWordType(target, con.start, con.limit, offset, opts);
/* 1827 */                 if (after == 0) return -1; 
/* 1828 */                 before = getPreviousWordType(target, con.start, con.limit, offset, opts);
/* 1829 */                 if (after == before) return -1;
/*      */                 
/*      */                 break;
/*      */               
/*      */               case 66:
/* 1834 */                 if (con.length == 0) {
/* 1835 */                   go = true;
/*      */                 } else {
/* 1837 */                   after = getWordType(target, con.start, con.limit, offset, opts);
/* 1838 */                   go = !(after != 0 && 
/* 1839 */                     after != getPreviousWordType(target, con.start, con.limit, offset, opts));
/*      */                 } 
/* 1841 */                 if (!go) return -1;
/*      */                 
/*      */                 break;
/*      */               case 60:
/* 1845 */                 if (con.length == 0 || offset == con.limit) return -1; 
/* 1846 */                 if (getWordType(target, con.start, con.limit, offset, opts) != 1 || 
/* 1847 */                   getPreviousWordType(target, con.start, con.limit, offset, opts) != 2) {
/* 1848 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 62:
/* 1852 */                 if (con.length == 0 || offset == con.start) return -1; 
/* 1853 */                 if (getWordType(target, con.start, con.limit, offset, opts) != 2 || 
/* 1854 */                   getPreviousWordType(target, con.start, con.limit, offset, opts) != 1)
/* 1855 */                   return -1; 
/*      */                 break;
/*      */             } 
/* 1858 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 16:
/* 1863 */             j = op.getData();
/* 1864 */             if (j <= 0 || j >= this.nofparen)
/* 1865 */               throw new RuntimeException("Internal Error: Reference number must be more than zero: " + j); 
/* 1866 */             if (con.match.getBeginning(j) < 0 || 
/* 1867 */               con.match.getEnd(j) < 0)
/* 1868 */               return -1; 
/* 1869 */             o2 = con.match.getBeginning(j);
/* 1870 */             n = con.match.getEnd(j) - o2;
/* 1871 */             if (!isSet(opts, 2)) {
/* 1872 */               if (dx > 0) {
/* 1873 */                 if (!regionMatches(target, offset, con.limit, o2, n))
/* 1874 */                   return -1; 
/* 1875 */                 offset += n;
/*      */               } else {
/* 1877 */                 if (!regionMatches(target, offset - n, con.limit, o2, n))
/* 1878 */                   return -1; 
/* 1879 */                 offset -= n;
/*      */               }
/*      */             
/* 1882 */             } else if (dx > 0) {
/* 1883 */               if (!regionMatchesIgnoreCase(target, offset, con.limit, o2, n))
/* 1884 */                 return -1; 
/* 1885 */               offset += n;
/*      */             } else {
/* 1887 */               if (!regionMatchesIgnoreCase(target, offset - n, con.limit, 
/* 1888 */                   o2, n))
/* 1889 */                 return -1; 
/* 1890 */               offset -= n;
/*      */             } 
/*      */ 
/*      */             
/* 1894 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 6:
/* 1898 */             literal = op.getString();
/* 1899 */             literallen = literal.length();
/* 1900 */             if (!isSet(opts, 2)) {
/* 1901 */               if (dx > 0) {
/* 1902 */                 if (!regionMatches(target, offset, con.limit, literal, literallen))
/* 1903 */                   return -1; 
/* 1904 */                 offset += literallen;
/*      */               } else {
/* 1906 */                 if (!regionMatches(target, offset - literallen, con.limit, literal, literallen))
/* 1907 */                   return -1; 
/* 1908 */                 offset -= literallen;
/*      */               }
/*      */             
/* 1911 */             } else if (dx > 0) {
/* 1912 */               if (!regionMatchesIgnoreCase(target, offset, con.limit, literal, literallen))
/* 1913 */                 return -1; 
/* 1914 */               offset += literallen;
/*      */             } else {
/* 1916 */               if (!regionMatchesIgnoreCase(target, offset - literallen, con.limit, 
/* 1917 */                   literal, literallen))
/* 1918 */                 return -1; 
/* 1919 */               offset -= literallen;
/*      */             } 
/*      */ 
/*      */             
/* 1923 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 7:
/* 1932 */             id = op.getData();
/* 1933 */             if (id >= 0) {
/* 1934 */               int previousOffset = con.offsets[id];
/* 1935 */               if (previousOffset < 0 || previousOffset != offset) {
/* 1936 */                 con.offsets[id] = offset;
/*      */               } else {
/* 1938 */                 con.offsets[id] = -1;
/* 1939 */                 op = op.next;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */             } 
/* 1944 */             k = matchCharArray(con, op.getChild(), offset, dx, opts);
/* 1945 */             if (id >= 0) con.offsets[id] = -1; 
/* 1946 */             if (k >= 0) return k; 
/* 1947 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 9:
/* 1953 */             ret = matchCharArray(con, op.getChild(), offset, dx, opts);
/* 1954 */             if (ret >= 0) return ret; 
/* 1955 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 8:
/*      */           case 10:
/* 1962 */             ret = matchCharArray(con, op.next, offset, dx, opts);
/* 1963 */             if (ret >= 0) return ret; 
/* 1964 */             op = op.getChild();
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 11:
/* 1969 */             for (i = 0; i < op.size(); i++) {
/* 1970 */               k = matchCharArray(con, op.elementAt(i), offset, dx, opts);
/*      */ 
/*      */ 
/*      */               
/* 1974 */               if (k >= 0) return k; 
/*      */             } 
/* 1976 */             return -1;
/*      */           
/*      */           case 15:
/* 1979 */             refno = op.getData();
/* 1980 */             if (con.match != null && refno > 0) {
/* 1981 */               int save = con.match.getBeginning(refno);
/* 1982 */               con.match.setBeginning(refno, offset);
/* 1983 */               int i1 = matchCharArray(con, op.next, offset, dx, opts);
/* 1984 */               if (i1 < 0) con.match.setBeginning(refno, save); 
/* 1985 */               return i1;
/* 1986 */             }  if (con.match != null && refno < 0) {
/* 1987 */               int index = -refno;
/* 1988 */               int save = con.match.getEnd(index);
/* 1989 */               con.match.setEnd(index, offset);
/* 1990 */               int i1 = matchCharArray(con, op.next, offset, dx, opts);
/* 1991 */               if (i1 < 0) con.match.setEnd(index, save); 
/* 1992 */               return i1;
/*      */             } 
/* 1994 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 20:
/* 1998 */             if (matchCharArray(con, op.getChild(), offset, 1, opts) < 0) return -1; 
/* 1999 */             op = op.next;
/*      */             continue;
/*      */           case 21:
/* 2002 */             if (matchCharArray(con, op.getChild(), offset, 1, opts) >= 0) return -1; 
/* 2003 */             op = op.next;
/*      */             continue;
/*      */           case 22:
/* 2006 */             if (matchCharArray(con, op.getChild(), offset, -1, opts) < 0) return -1; 
/* 2007 */             op = op.next;
/*      */             continue;
/*      */           case 23:
/* 2010 */             if (matchCharArray(con, op.getChild(), offset, -1, opts) >= 0) return -1; 
/* 2011 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 24:
/* 2016 */             k = matchCharArray(con, op.getChild(), offset, dx, opts);
/* 2017 */             if (k < 0) return k; 
/* 2018 */             offset = k;
/* 2019 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 25:
/* 2025 */             localopts = opts;
/* 2026 */             localopts |= op.getData();
/* 2027 */             localopts &= op.getData2() ^ 0xFFFFFFFF;
/*      */             
/* 2029 */             m = matchCharArray(con, op.getChild(), offset, dx, localopts);
/* 2030 */             if (m < 0) return m; 
/* 2031 */             offset = m;
/* 2032 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 26:
/* 2038 */             cop = (RegEx.Op.ConditionOp)op;
/* 2039 */             matchp = false;
/* 2040 */             if (cop.refNumber > 0) {
/* 2041 */               if (cop.refNumber >= this.nofparen)
/* 2042 */                 throw new RuntimeException("Internal Error: Reference number must be more than zero: " + cop.refNumber); 
/* 2043 */               matchp = (con.match.getBeginning(cop.refNumber) >= 0 && 
/* 2044 */                 con.match.getEnd(cop.refNumber) >= 0);
/*      */             } else {
/* 2046 */               matchp = (matchCharArray(con, cop.condition, offset, dx, opts) >= 0);
/*      */             } 
/*      */             
/* 2049 */             if (matchp) {
/* 2050 */               op = cop.yes; continue;
/* 2051 */             }  if (cop.no != null) {
/* 2052 */               op = cop.no; continue;
/*      */             } 
/* 2054 */             op = cop.next;
/*      */             continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/* 2060 */       throw new RuntimeException("Unknown operation type: " + op.type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int getPreviousWordType(char[] target, int begin, int end, int offset, int opts) {
/* 2067 */       int ret = getWordType(target, begin, end, --offset, opts);
/* 2068 */       while (ret == 0)
/* 2069 */         ret = getWordType(target, begin, end, --offset, opts); 
/* 2070 */       return ret;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final int getWordType(char[] target, int begin, int end, int offset, int opts) {
/* 2075 */       if (offset < begin || offset >= end) return 2; 
/* 2076 */       return getWordType0(target[offset], opts);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean regionMatches(char[] target, int offset, int limit, String part, int partlen) {
/* 2083 */       if (offset < 0) return false; 
/* 2084 */       if (limit - offset < partlen)
/* 2085 */         return false; 
/* 2086 */       int i = 0;
/* 2087 */       while (partlen-- > 0) {
/* 2088 */         if (target[offset++] != part.charAt(i++))
/* 2089 */           return false; 
/*      */       } 
/* 2091 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatches(char[] target, int offset, int limit, int offset2, int partlen) {
/* 2096 */       if (offset < 0) return false; 
/* 2097 */       if (limit - offset < partlen)
/* 2098 */         return false; 
/* 2099 */       int i = offset2;
/* 2100 */       while (partlen-- > 0) {
/* 2101 */         if (target[offset++] != target[i++])
/* 2102 */           return false; 
/*      */       } 
/* 2104 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean regionMatchesIgnoreCase(char[] target, int offset, int limit, String part, int partlen) {
/* 2112 */       if (offset < 0) return false; 
/* 2113 */       if (limit - offset < partlen)
/* 2114 */         return false; 
/* 2115 */       int i = 0;
/* 2116 */       while (partlen-- > 0) {
/* 2117 */         char ch1 = target[offset++];
/* 2118 */         char ch2 = part.charAt(i++);
/* 2119 */         if (ch1 == ch2)
/*      */           continue; 
/* 2121 */         char uch1 = Character.toUpperCase(ch1);
/* 2122 */         char uch2 = Character.toUpperCase(ch2);
/* 2123 */         if (uch1 == uch2)
/*      */           continue; 
/* 2125 */         if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2))
/* 2126 */           return false; 
/*      */       } 
/* 2128 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatchesIgnoreCase(char[] target, int offset, int limit, int offset2, int partlen) {
/* 2133 */       if (offset < 0) return false; 
/* 2134 */       if (limit - offset < partlen)
/* 2135 */         return false; 
/* 2136 */       int i = offset2;
/* 2137 */       while (partlen-- > 0) {
/* 2138 */         char ch1 = target[offset++];
/* 2139 */         char ch2 = target[i++];
/* 2140 */         if (ch1 == ch2)
/*      */           continue; 
/* 2142 */         char uch1 = Character.toUpperCase(ch1);
/* 2143 */         char uch2 = Character.toUpperCase(ch2);
/* 2144 */         if (uch1 == uch2)
/*      */           continue; 
/* 2146 */         if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2))
/* 2147 */           return false; 
/*      */       } 
/* 2149 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(String target) {
/* 2161 */       return matches(target, 0, target.length(), (RegEx.Match)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(String target, int start, int end) {
/* 2173 */       return matches(target, start, end, (RegEx.Match)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(String target, RegEx.Match match) {
/* 2183 */       return matches(target, 0, target.length(), match);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(String target, int start, int end, RegEx.Match match) {
/*      */       int matchStart;
/* 2197 */       synchronized (this) {
/* 2198 */         if (this.operations == null)
/* 2199 */           prepare(); 
/* 2200 */         if (this.context == null)
/* 2201 */           this.context = new Context(); 
/*      */       } 
/* 2203 */       Context con = null;
/* 2204 */       synchronized (this.context) {
/* 2205 */         con = this.context.inuse ? new Context() : this.context;
/* 2206 */         con.reset(target, start, end, this.numberOfClosures);
/*      */       } 
/* 2208 */       if (match != null) {
/* 2209 */         match.setNumberOfGroups(this.nofparen);
/* 2210 */         match.setSource(target);
/* 2211 */       } else if (this.hasBackReferences) {
/* 2212 */         match = new RegEx.Match();
/* 2213 */         match.setNumberOfGroups(this.nofparen);
/*      */       } 
/*      */ 
/*      */       
/* 2217 */       con.match = match;
/*      */       
/* 2219 */       if (isSet(this.options, 512)) {
/*      */ 
/*      */ 
/*      */         
/* 2223 */         int i = matchString(con, this.operations, con.start, 1, this.options);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2228 */         if (i == con.limit) {
/* 2229 */           if (con.match != null) {
/* 2230 */             con.match.setBeginning(0, con.start);
/* 2231 */             con.match.setEnd(0, i);
/*      */           } 
/* 2233 */           con.inuse = false;
/* 2234 */           return true;
/*      */         } 
/* 2236 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2243 */       if (this.fixedStringOnly) {
/*      */         
/* 2245 */         int o = this.fixedStringTable.matches(target, con.start, con.limit);
/* 2246 */         if (o >= 0) {
/* 2247 */           if (con.match != null) {
/* 2248 */             con.match.setBeginning(0, o);
/* 2249 */             con.match.setEnd(0, o + this.fixedString.length());
/*      */           } 
/* 2251 */           con.inuse = false;
/* 2252 */           return true;
/*      */         } 
/* 2254 */         con.inuse = false;
/* 2255 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2263 */       if (this.fixedString != null) {
/* 2264 */         int o = this.fixedStringTable.matches(target, con.start, con.limit);
/* 2265 */         if (o < 0) {
/*      */           
/* 2267 */           con.inuse = false;
/* 2268 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 2272 */       int limit = con.limit - this.minlength;
/*      */       
/* 2274 */       int matchEnd = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2279 */       if (this.operations != null && 
/* 2280 */         this.operations.type == 7 && (this.operations.getChild()).type == 0) {
/* 2281 */         if (isSet(this.options, 4)) {
/* 2282 */           matchStart = con.start;
/* 2283 */           matchEnd = matchString(con, this.operations, con.start, 1, this.options);
/*      */         } else {
/* 2285 */           boolean previousIsEOL = true;
/* 2286 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 2287 */             int ch = target.charAt(matchStart);
/* 2288 */             if (isEOLChar(ch)) {
/* 2289 */               previousIsEOL = true;
/*      */             } else {
/* 2291 */               if (previousIsEOL)
/* 2292 */                 if ((matchEnd = matchString(con, this.operations, 
/* 2293 */                     matchStart, 1, this.options)) >= 0) {
/*      */                   break;
/*      */                 } 
/* 2296 */               previousIsEOL = false;
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2305 */       else if (this.firstChar != null) {
/*      */         
/* 2307 */         RegEx.RangeToken range = this.firstChar;
/* 2308 */         if (isSet(this.options, 2)) {
/* 2309 */           range = this.firstChar.getCaseInsensitiveToken();
/* 2310 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 2311 */             int ch = target.charAt(matchStart);
/* 2312 */             if (RegEx.REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
/* 2313 */               ch = RegEx.REUtil.composeFromSurrogates(ch, target.charAt(matchStart + 1));
/* 2314 */               if (!range.match(ch))
/*      */                 continue; 
/* 2316 */             } else if (!range.match(ch)) {
/* 2317 */               char ch1 = Character.toUpperCase((char)ch);
/* 2318 */               if (!range.match(ch1) && 
/* 2319 */                 !range.match(Character.toLowerCase(ch1))) {
/*      */                 continue;
/*      */               }
/*      */             } 
/* 2323 */             if ((matchEnd = matchString(con, this.operations, 
/* 2324 */                 matchStart, 1, this.options)) >= 0)
/*      */               break;  continue;
/*      */           } 
/*      */         } else {
/* 2328 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 2329 */             int ch = target.charAt(matchStart);
/* 2330 */             if (RegEx.REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit)
/* 2331 */               ch = RegEx.REUtil.composeFromSurrogates(ch, target.charAt(matchStart + 1)); 
/* 2332 */             if (range.match(ch)) {
/* 2333 */               if ((matchEnd = matchString(con, this.operations, 
/* 2334 */                   matchStart, 1, this.options)) >= 0) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2344 */         for (matchStart = con.start; matchStart <= limit && (
/* 2345 */           matchEnd = matchString(con, this.operations, matchStart, 1, this.options)) < 0; matchStart++);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2350 */       if (matchEnd >= 0) {
/* 2351 */         if (con.match != null) {
/* 2352 */           con.match.setBeginning(0, matchStart);
/* 2353 */           con.match.setEnd(0, matchEnd);
/*      */         } 
/* 2355 */         con.inuse = false;
/* 2356 */         return true;
/*      */       } 
/* 2358 */       con.inuse = false;
/* 2359 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int matchString(Context con, RegEx.Op op, int offset, int dx, int opts) {
/* 2371 */       String target = con.strTarget; while (true) {
/*      */         boolean go; int after; int j; String literal; int id; int ret; int i; int refno; int before; int o2; int literallen; int k; int localopts;
/*      */         RegEx.Op.ConditionOp cop;
/*      */         int n;
/*      */         int m;
/*      */         boolean matchp;
/* 2377 */         if (op == null)
/* 2378 */           return (isSet(opts, 512) && offset != con.limit) ? -1 : offset; 
/* 2379 */         if (offset > con.limit || offset < con.start)
/* 2380 */           return -1; 
/* 2381 */         switch (op.type) {
/*      */           case 1:
/* 2383 */             if (isSet(opts, 2)) {
/* 2384 */               int ch = op.getData();
/* 2385 */               if (dx > 0) {
/* 2386 */                 if (offset >= con.limit || !matchIgnoreCase(ch, target.charAt(offset)))
/* 2387 */                   return -1; 
/* 2388 */                 offset++;
/*      */               } else {
/* 2390 */                 int o1 = offset - 1;
/* 2391 */                 if (o1 >= con.limit || o1 < 0 || !matchIgnoreCase(ch, target.charAt(o1)))
/* 2392 */                   return -1; 
/* 2393 */                 offset = o1;
/*      */               } 
/*      */             } else {
/* 2396 */               int ch = op.getData();
/* 2397 */               if (dx > 0) {
/* 2398 */                 if (offset >= con.limit || ch != target.charAt(offset))
/* 2399 */                   return -1; 
/* 2400 */                 offset++;
/*      */               } else {
/* 2402 */                 int o1 = offset - 1;
/* 2403 */                 if (o1 >= con.limit || o1 < 0 || ch != target.charAt(o1))
/* 2404 */                   return -1; 
/* 2405 */                 offset = o1;
/*      */               } 
/*      */             } 
/* 2408 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 0:
/* 2412 */             if (dx > 0) {
/* 2413 */               if (offset >= con.limit)
/* 2414 */                 return -1; 
/* 2415 */               int ch = target.charAt(offset);
/* 2416 */               if (isSet(opts, 4)) {
/* 2417 */                 if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 2418 */                   offset++; 
/*      */               } else {
/* 2420 */                 if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 2421 */                   ch = RegEx.REUtil.composeFromSurrogates(ch, target.charAt(++offset)); 
/* 2422 */                 if (isEOLChar(ch))
/* 2423 */                   return -1; 
/*      */               } 
/* 2425 */               offset++;
/*      */             } else {
/* 2427 */               int o1 = offset - 1;
/* 2428 */               if (o1 >= con.limit || o1 < 0)
/* 2429 */                 return -1; 
/* 2430 */               int ch = target.charAt(o1);
/* 2431 */               if (isSet(opts, 4)) {
/* 2432 */                 if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 2433 */                   o1--; 
/*      */               } else {
/* 2435 */                 if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 2436 */                   ch = RegEx.REUtil.composeFromSurrogates(target.charAt(--o1), ch); 
/* 2437 */                 if (!isEOLChar(ch))
/* 2438 */                   return -1; 
/*      */               } 
/* 2440 */               offset = o1;
/*      */             } 
/* 2442 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 3:
/*      */           case 4:
/* 2447 */             if (dx > 0) {
/* 2448 */               if (offset >= con.limit)
/* 2449 */                 return -1; 
/* 2450 */               int ch = target.charAt(offset);
/* 2451 */               if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 2452 */                 ch = RegEx.REUtil.composeFromSurrogates(ch, target.charAt(++offset)); 
/* 2453 */               RegEx.RangeToken tok = op.getToken();
/* 2454 */               if (isSet(opts, 2))
/* 2455 */               { tok = tok.getCaseInsensitiveToken();
/* 2456 */                 if (!tok.match(ch)) {
/* 2457 */                   if (ch >= 65536) return -1; 
/*      */                   char uch;
/* 2459 */                   if (!tok.match(uch = Character.toUpperCase((char)ch)) && 
/* 2460 */                     !tok.match(Character.toLowerCase(uch))) {
/* 2461 */                     return -1;
/*      */                   }
/*      */                 }  }
/* 2464 */               else if (!tok.match(ch)) { return -1; }
/*      */               
/* 2466 */               offset++;
/*      */             } else {
/* 2468 */               int o1 = offset - 1;
/* 2469 */               if (o1 >= con.limit || o1 < 0)
/* 2470 */                 return -1; 
/* 2471 */               int ch = target.charAt(o1);
/* 2472 */               if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 2473 */                 ch = RegEx.REUtil.composeFromSurrogates(target.charAt(--o1), ch); 
/* 2474 */               RegEx.RangeToken tok = op.getToken();
/* 2475 */               if (isSet(opts, 2))
/* 2476 */               { tok = tok.getCaseInsensitiveToken();
/* 2477 */                 if (!tok.match(ch)) {
/* 2478 */                   if (ch >= 65536) return -1; 
/*      */                   char uch;
/* 2480 */                   if (!tok.match(uch = Character.toUpperCase((char)ch)) && 
/* 2481 */                     !tok.match(Character.toLowerCase(uch))) {
/* 2482 */                     return -1;
/*      */                   }
/*      */                 }  }
/* 2485 */               else if (!tok.match(ch)) { return -1; }
/*      */               
/* 2487 */               offset = o1;
/*      */             } 
/* 2489 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 5:
/* 2493 */             go = false;
/* 2494 */             switch (op.getData()) {
/*      */               case 94:
/* 2496 */                 if (isSet(opts, 8)) {
/* 2497 */                   if (offset != con.start && (
/* 2498 */                     offset <= con.start || !isEOLChar(target.charAt(offset - 1))))
/* 2499 */                     return -1;  break;
/*      */                 } 
/* 2501 */                 if (offset != con.start) {
/* 2502 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 64:
/* 2508 */                 if (offset != con.start && (
/* 2509 */                   offset <= con.start || !isEOLChar(target.charAt(offset - 1)))) {
/* 2510 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 36:
/* 2514 */                 if (isSet(opts, 8)) {
/* 2515 */                   if (offset != con.limit && (
/* 2516 */                     offset >= con.limit || !isEOLChar(target.charAt(offset))))
/* 2517 */                     return -1;  break;
/*      */                 } 
/* 2519 */                 if (offset != con.limit && (
/* 2520 */                   offset + 1 != con.limit || !isEOLChar(target.charAt(offset))) && (
/* 2521 */                   offset + 2 != con.limit || target.charAt(offset) != '\r' || 
/* 2522 */                   target.charAt(offset + 1) != '\n')) {
/* 2523 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 65:
/* 2528 */                 if (offset != con.start) return -1;
/*      */                 
/*      */                 break;
/*      */               case 90:
/* 2532 */                 if (offset != con.limit && (
/* 2533 */                   offset + 1 != con.limit || !isEOLChar(target.charAt(offset))) && (
/* 2534 */                   offset + 2 != con.limit || target.charAt(offset) != '\r' || 
/* 2535 */                   target.charAt(offset + 1) != '\n')) {
/* 2536 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 122:
/* 2540 */                 if (offset != con.limit) return -1;
/*      */                 
/*      */                 break;
/*      */               case 98:
/* 2544 */                 if (con.length == 0) return -1;
/*      */                 
/* 2546 */                 after = getWordType(target, con.start, con.limit, offset, opts);
/* 2547 */                 if (after == 0) return -1; 
/* 2548 */                 before = getPreviousWordType(target, con.start, con.limit, offset, opts);
/* 2549 */                 if (after == before) return -1;
/*      */                 
/*      */                 break;
/*      */               
/*      */               case 66:
/* 2554 */                 if (con.length == 0) {
/* 2555 */                   go = true;
/*      */                 } else {
/* 2557 */                   after = getWordType(target, con.start, con.limit, offset, opts);
/* 2558 */                   go = !(after != 0 && 
/* 2559 */                     after != getPreviousWordType(target, con.start, con.limit, offset, opts));
/*      */                 } 
/* 2561 */                 if (!go) return -1;
/*      */                 
/*      */                 break;
/*      */               case 60:
/* 2565 */                 if (con.length == 0 || offset == con.limit) return -1; 
/* 2566 */                 if (getWordType(target, con.start, con.limit, offset, opts) != 1 || 
/* 2567 */                   getPreviousWordType(target, con.start, con.limit, offset, opts) != 2) {
/* 2568 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 62:
/* 2572 */                 if (con.length == 0 || offset == con.start) return -1; 
/* 2573 */                 if (getWordType(target, con.start, con.limit, offset, opts) != 2 || 
/* 2574 */                   getPreviousWordType(target, con.start, con.limit, offset, opts) != 1)
/* 2575 */                   return -1; 
/*      */                 break;
/*      */             } 
/* 2578 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 16:
/* 2583 */             j = op.getData();
/* 2584 */             if (j <= 0 || j >= this.nofparen)
/* 2585 */               throw new RuntimeException("Internal Error: Reference number must be more than zero: " + j); 
/* 2586 */             if (con.match.getBeginning(j) < 0 || 
/* 2587 */               con.match.getEnd(j) < 0)
/* 2588 */               return -1; 
/* 2589 */             o2 = con.match.getBeginning(j);
/* 2590 */             n = con.match.getEnd(j) - o2;
/* 2591 */             if (!isSet(opts, 2)) {
/* 2592 */               if (dx > 0) {
/* 2593 */                 if (!regionMatches(target, offset, con.limit, o2, n))
/* 2594 */                   return -1; 
/* 2595 */                 offset += n;
/*      */               } else {
/* 2597 */                 if (!regionMatches(target, offset - n, con.limit, o2, n))
/* 2598 */                   return -1; 
/* 2599 */                 offset -= n;
/*      */               }
/*      */             
/* 2602 */             } else if (dx > 0) {
/* 2603 */               if (!regionMatchesIgnoreCase(target, offset, con.limit, o2, n))
/* 2604 */                 return -1; 
/* 2605 */               offset += n;
/*      */             } else {
/* 2607 */               if (!regionMatchesIgnoreCase(target, offset - n, con.limit, 
/* 2608 */                   o2, n))
/* 2609 */                 return -1; 
/* 2610 */               offset -= n;
/*      */             } 
/*      */ 
/*      */             
/* 2614 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 6:
/* 2618 */             literal = op.getString();
/* 2619 */             literallen = literal.length();
/* 2620 */             if (!isSet(opts, 2)) {
/* 2621 */               if (dx > 0) {
/* 2622 */                 if (!regionMatches(target, offset, con.limit, literal, literallen))
/* 2623 */                   return -1; 
/* 2624 */                 offset += literallen;
/*      */               } else {
/* 2626 */                 if (!regionMatches(target, offset - literallen, con.limit, literal, literallen))
/* 2627 */                   return -1; 
/* 2628 */                 offset -= literallen;
/*      */               }
/*      */             
/* 2631 */             } else if (dx > 0) {
/* 2632 */               if (!regionMatchesIgnoreCase(target, offset, con.limit, literal, literallen))
/* 2633 */                 return -1; 
/* 2634 */               offset += literallen;
/*      */             } else {
/* 2636 */               if (!regionMatchesIgnoreCase(target, offset - literallen, con.limit, 
/* 2637 */                   literal, literallen))
/* 2638 */                 return -1; 
/* 2639 */               offset -= literallen;
/*      */             } 
/*      */ 
/*      */             
/* 2643 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 7:
/* 2652 */             id = op.getData();
/* 2653 */             if (id >= 0) {
/* 2654 */               int previousOffset = con.offsets[id];
/* 2655 */               if (previousOffset < 0 || previousOffset != offset) {
/* 2656 */                 con.offsets[id] = offset;
/*      */               } else {
/* 2658 */                 con.offsets[id] = -1;
/* 2659 */                 op = op.next;
/*      */                 continue;
/*      */               } 
/*      */             } 
/* 2663 */             k = matchString(con, op.getChild(), offset, dx, opts);
/* 2664 */             if (id >= 0) con.offsets[id] = -1; 
/* 2665 */             if (k >= 0) return k; 
/* 2666 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 9:
/* 2672 */             ret = matchString(con, op.getChild(), offset, dx, opts);
/* 2673 */             if (ret >= 0) return ret; 
/* 2674 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 8:
/*      */           case 10:
/* 2681 */             ret = matchString(con, op.next, offset, dx, opts);
/* 2682 */             if (ret >= 0) return ret; 
/* 2683 */             op = op.getChild();
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 11:
/* 2688 */             for (i = 0; i < op.size(); i++) {
/* 2689 */               k = matchString(con, op.elementAt(i), offset, dx, opts);
/*      */ 
/*      */ 
/*      */               
/* 2693 */               if (k >= 0) return k; 
/*      */             } 
/* 2695 */             return -1;
/*      */           
/*      */           case 15:
/* 2698 */             refno = op.getData();
/* 2699 */             if (con.match != null && refno > 0) {
/* 2700 */               int save = con.match.getBeginning(refno);
/* 2701 */               con.match.setBeginning(refno, offset);
/* 2702 */               int i1 = matchString(con, op.next, offset, dx, opts);
/* 2703 */               if (i1 < 0) con.match.setBeginning(refno, save); 
/* 2704 */               return i1;
/* 2705 */             }  if (con.match != null && refno < 0) {
/* 2706 */               int index = -refno;
/* 2707 */               int save = con.match.getEnd(index);
/* 2708 */               con.match.setEnd(index, offset);
/* 2709 */               int i1 = matchString(con, op.next, offset, dx, opts);
/* 2710 */               if (i1 < 0) con.match.setEnd(index, save); 
/* 2711 */               return i1;
/*      */             } 
/* 2713 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 20:
/* 2717 */             if (matchString(con, op.getChild(), offset, 1, opts) < 0) return -1; 
/* 2718 */             op = op.next;
/*      */             continue;
/*      */           case 21:
/* 2721 */             if (matchString(con, op.getChild(), offset, 1, opts) >= 0) return -1; 
/* 2722 */             op = op.next;
/*      */             continue;
/*      */           case 22:
/* 2725 */             if (matchString(con, op.getChild(), offset, -1, opts) < 0) return -1; 
/* 2726 */             op = op.next;
/*      */             continue;
/*      */           case 23:
/* 2729 */             if (matchString(con, op.getChild(), offset, -1, opts) >= 0) return -1; 
/* 2730 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 24:
/* 2735 */             k = matchString(con, op.getChild(), offset, dx, opts);
/* 2736 */             if (k < 0) return k; 
/* 2737 */             offset = k;
/* 2738 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 25:
/* 2744 */             localopts = opts;
/* 2745 */             localopts |= op.getData();
/* 2746 */             localopts &= op.getData2() ^ 0xFFFFFFFF;
/*      */             
/* 2748 */             m = matchString(con, op.getChild(), offset, dx, localopts);
/* 2749 */             if (m < 0) return m; 
/* 2750 */             offset = m;
/* 2751 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 26:
/* 2757 */             cop = (RegEx.Op.ConditionOp)op;
/* 2758 */             matchp = false;
/* 2759 */             if (cop.refNumber > 0) {
/* 2760 */               if (cop.refNumber >= this.nofparen)
/* 2761 */                 throw new RuntimeException("Internal Error: Reference number must be more than zero: " + cop.refNumber); 
/* 2762 */               matchp = (con.match.getBeginning(cop.refNumber) >= 0 && 
/* 2763 */                 con.match.getEnd(cop.refNumber) >= 0);
/*      */             } else {
/* 2765 */               matchp = (matchString(con, cop.condition, offset, dx, opts) >= 0);
/*      */             } 
/*      */             
/* 2768 */             if (matchp) {
/* 2769 */               op = cop.yes; continue;
/* 2770 */             }  if (cop.no != null) {
/* 2771 */               op = cop.no; continue;
/*      */             } 
/* 2773 */             op = cop.next;
/*      */             continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/* 2779 */       throw new RuntimeException("Unknown operation type: " + op.type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int getPreviousWordType(String target, int begin, int end, int offset, int opts) {
/* 2786 */       int ret = getWordType(target, begin, end, --offset, opts);
/* 2787 */       while (ret == 0)
/* 2788 */         ret = getWordType(target, begin, end, --offset, opts); 
/* 2789 */       return ret;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final int getWordType(String target, int begin, int end, int offset, int opts) {
/* 2794 */       if (offset < begin || offset >= end) return 2; 
/* 2795 */       return getWordType0(target.charAt(offset), opts);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean regionMatches(String text, int offset, int limit, String part, int partlen) {
/* 2801 */       if (limit - offset < partlen) return false; 
/* 2802 */       return text.regionMatches(offset, part, 0, partlen);
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatches(String text, int offset, int limit, int offset2, int partlen) {
/* 2807 */       if (limit - offset < partlen) return false; 
/* 2808 */       return text.regionMatches(offset, text, offset2, partlen);
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatchesIgnoreCase(String text, int offset, int limit, String part, int partlen) {
/* 2813 */       return text.regionMatches(true, offset, part, 0, partlen);
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatchesIgnoreCase(String text, int offset, int limit, int offset2, int partlen) {
/* 2818 */       if (limit - offset < partlen) return false; 
/* 2819 */       return text.regionMatches(true, offset, text, offset2, partlen);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(CharacterIterator target) {
/* 2834 */       return matches(target, (RegEx.Match)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean matches(CharacterIterator target, RegEx.Match match) {
/* 2845 */       int matchStart, start = target.getBeginIndex();
/* 2846 */       int end = target.getEndIndex();
/*      */ 
/*      */ 
/*      */       
/* 2850 */       synchronized (this) {
/* 2851 */         if (this.operations == null)
/* 2852 */           prepare(); 
/* 2853 */         if (this.context == null)
/* 2854 */           this.context = new Context(); 
/*      */       } 
/* 2856 */       Context con = null;
/* 2857 */       synchronized (this.context) {
/* 2858 */         con = this.context.inuse ? new Context() : this.context;
/* 2859 */         con.reset(target, start, end, this.numberOfClosures);
/*      */       } 
/* 2861 */       if (match != null) {
/* 2862 */         match.setNumberOfGroups(this.nofparen);
/* 2863 */         match.setSource(target);
/* 2864 */       } else if (this.hasBackReferences) {
/* 2865 */         match = new RegEx.Match();
/* 2866 */         match.setNumberOfGroups(this.nofparen);
/*      */       } 
/*      */ 
/*      */       
/* 2870 */       con.match = match;
/*      */       
/* 2872 */       if (isSet(this.options, 512)) {
/* 2873 */         int i = matchCharacterIterator(con, this.operations, con.start, 1, this.options);
/*      */         
/* 2875 */         if (i == con.limit) {
/* 2876 */           if (con.match != null) {
/* 2877 */             con.match.setBeginning(0, con.start);
/* 2878 */             con.match.setEnd(0, i);
/*      */           } 
/* 2880 */           con.inuse = false;
/* 2881 */           return true;
/*      */         } 
/* 2883 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2890 */       if (this.fixedStringOnly) {
/*      */         
/* 2892 */         int o = this.fixedStringTable.matches(target, con.start, con.limit);
/* 2893 */         if (o >= 0) {
/* 2894 */           if (con.match != null) {
/* 2895 */             con.match.setBeginning(0, o);
/* 2896 */             con.match.setEnd(0, o + this.fixedString.length());
/*      */           } 
/* 2898 */           con.inuse = false;
/* 2899 */           return true;
/*      */         } 
/* 2901 */         con.inuse = false;
/* 2902 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2910 */       if (this.fixedString != null) {
/* 2911 */         int o = this.fixedStringTable.matches(target, con.start, con.limit);
/* 2912 */         if (o < 0) {
/*      */           
/* 2914 */           con.inuse = false;
/* 2915 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 2919 */       int limit = con.limit - this.minlength;
/*      */       
/* 2921 */       int matchEnd = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2926 */       if (this.operations != null && 
/* 2927 */         this.operations.type == 7 && (this.operations.getChild()).type == 0) {
/* 2928 */         if (isSet(this.options, 4)) {
/* 2929 */           matchStart = con.start;
/* 2930 */           matchEnd = matchCharacterIterator(con, this.operations, con.start, 1, this.options);
/*      */         } else {
/* 2932 */           boolean previousIsEOL = true;
/* 2933 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 2934 */             int ch = target.setIndex(matchStart);
/* 2935 */             if (isEOLChar(ch)) {
/* 2936 */               previousIsEOL = true;
/*      */             } else {
/* 2938 */               if (previousIsEOL)
/* 2939 */                 if ((matchEnd = matchCharacterIterator(con, this.operations, 
/* 2940 */                     matchStart, 1, this.options)) >= 0) {
/*      */                   break;
/*      */                 } 
/* 2943 */               previousIsEOL = false;
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2952 */       else if (this.firstChar != null) {
/*      */         
/* 2954 */         RegEx.RangeToken range = this.firstChar;
/* 2955 */         if (isSet(this.options, 2)) {
/* 2956 */           range = this.firstChar.getCaseInsensitiveToken();
/* 2957 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 2958 */             int ch = target.setIndex(matchStart);
/* 2959 */             if (RegEx.REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
/* 2960 */               ch = RegEx.REUtil.composeFromSurrogates(ch, target.setIndex(matchStart + 1));
/* 2961 */               if (!range.match(ch))
/*      */                 continue; 
/* 2963 */             } else if (!range.match(ch)) {
/* 2964 */               char ch1 = Character.toUpperCase((char)ch);
/* 2965 */               if (!range.match(ch1) && 
/* 2966 */                 !range.match(Character.toLowerCase(ch1))) {
/*      */                 continue;
/*      */               }
/*      */             } 
/* 2970 */             if ((matchEnd = matchCharacterIterator(con, this.operations, 
/* 2971 */                 matchStart, 1, this.options)) >= 0)
/*      */               break;  continue;
/*      */           } 
/*      */         } else {
/* 2975 */           for (matchStart = con.start; matchStart <= limit; matchStart++) {
/* 2976 */             int ch = target.setIndex(matchStart);
/* 2977 */             if (RegEx.REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit)
/* 2978 */               ch = RegEx.REUtil.composeFromSurrogates(ch, target.setIndex(matchStart + 1)); 
/* 2979 */             if (range.match(ch)) {
/* 2980 */               if ((matchEnd = matchCharacterIterator(con, this.operations, 
/* 2981 */                   matchStart, 1, this.options)) >= 0) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2991 */         for (matchStart = con.start; matchStart <= limit && (
/* 2992 */           matchEnd = matchCharacterIterator(con, this.operations, matchStart, 1, this.options)) < 0; matchStart++);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2997 */       if (matchEnd >= 0) {
/* 2998 */         if (con.match != null) {
/* 2999 */           con.match.setBeginning(0, matchStart);
/* 3000 */           con.match.setEnd(0, matchEnd);
/*      */         } 
/* 3002 */         con.inuse = false;
/* 3003 */         return true;
/*      */       } 
/* 3005 */       con.inuse = false;
/* 3006 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int matchCharacterIterator(Context con, RegEx.Op op, int offset, int dx, int opts) {
/* 3016 */       CharacterIterator target = con.ciTarget; while (true) {
/*      */         boolean go; int after; int j; String literal; int id; int ret; int i; int refno; int before; int o2; int literallen;
/*      */         int k;
/*      */         int localopts;
/*      */         RegEx.Op.ConditionOp cop;
/*      */         int n;
/*      */         int m;
/*      */         boolean matchp;
/* 3024 */         if (op == null)
/* 3025 */           return (isSet(opts, 512) && offset != con.limit) ? -1 : offset; 
/* 3026 */         if (offset > con.limit || offset < con.start)
/* 3027 */           return -1; 
/* 3028 */         switch (op.type) {
/*      */           case 1:
/* 3030 */             if (isSet(opts, 2)) {
/* 3031 */               int ch = op.getData();
/* 3032 */               if (dx > 0) {
/* 3033 */                 if (offset >= con.limit || !matchIgnoreCase(ch, target.setIndex(offset)))
/* 3034 */                   return -1; 
/* 3035 */                 offset++;
/*      */               } else {
/* 3037 */                 int o1 = offset - 1;
/* 3038 */                 if (o1 >= con.limit || o1 < 0 || !matchIgnoreCase(ch, target.setIndex(o1)))
/* 3039 */                   return -1; 
/* 3040 */                 offset = o1;
/*      */               } 
/*      */             } else {
/* 3043 */               int ch = op.getData();
/* 3044 */               if (dx > 0) {
/* 3045 */                 if (offset >= con.limit || ch != target.setIndex(offset))
/* 3046 */                   return -1; 
/* 3047 */                 offset++;
/*      */               } else {
/* 3049 */                 int o1 = offset - 1;
/* 3050 */                 if (o1 >= con.limit || o1 < 0 || ch != target.setIndex(o1))
/* 3051 */                   return -1; 
/* 3052 */                 offset = o1;
/*      */               } 
/*      */             } 
/* 3055 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 0:
/* 3059 */             if (dx > 0) {
/* 3060 */               if (offset >= con.limit)
/* 3061 */                 return -1; 
/* 3062 */               int ch = target.setIndex(offset);
/* 3063 */               if (isSet(opts, 4)) {
/* 3064 */                 if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 3065 */                   offset++; 
/*      */               } else {
/* 3067 */                 if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 3068 */                   ch = RegEx.REUtil.composeFromSurrogates(ch, target.setIndex(++offset)); 
/* 3069 */                 if (isEOLChar(ch))
/* 3070 */                   return -1; 
/*      */               } 
/* 3072 */               offset++;
/*      */             } else {
/* 3074 */               int o1 = offset - 1;
/* 3075 */               if (o1 >= con.limit || o1 < 0)
/* 3076 */                 return -1; 
/* 3077 */               int ch = target.setIndex(o1);
/* 3078 */               if (isSet(opts, 4)) {
/* 3079 */                 if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 3080 */                   o1--; 
/*      */               } else {
/* 3082 */                 if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 3083 */                   ch = RegEx.REUtil.composeFromSurrogates(target.setIndex(--o1), ch); 
/* 3084 */                 if (!isEOLChar(ch))
/* 3085 */                   return -1; 
/*      */               } 
/* 3087 */               offset = o1;
/*      */             } 
/* 3089 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 3:
/*      */           case 4:
/* 3094 */             if (dx > 0) {
/* 3095 */               if (offset >= con.limit)
/* 3096 */                 return -1; 
/* 3097 */               int ch = target.setIndex(offset);
/* 3098 */               if (RegEx.REUtil.isHighSurrogate(ch) && offset + 1 < con.limit)
/* 3099 */                 ch = RegEx.REUtil.composeFromSurrogates(ch, target.setIndex(++offset)); 
/* 3100 */               RegEx.RangeToken tok = op.getToken();
/* 3101 */               if (isSet(opts, 2))
/* 3102 */               { tok = tok.getCaseInsensitiveToken();
/* 3103 */                 if (!tok.match(ch)) {
/* 3104 */                   if (ch >= 65536) return -1; 
/*      */                   char uch;
/* 3106 */                   if (!tok.match(uch = Character.toUpperCase((char)ch)) && 
/* 3107 */                     !tok.match(Character.toLowerCase(uch))) {
/* 3108 */                     return -1;
/*      */                   }
/*      */                 }  }
/* 3111 */               else if (!tok.match(ch)) { return -1; }
/*      */               
/* 3113 */               offset++;
/*      */             } else {
/* 3115 */               int o1 = offset - 1;
/* 3116 */               if (o1 >= con.limit || o1 < 0)
/* 3117 */                 return -1; 
/* 3118 */               int ch = target.setIndex(o1);
/* 3119 */               if (RegEx.REUtil.isLowSurrogate(ch) && o1 - 1 >= 0)
/* 3120 */                 ch = RegEx.REUtil.composeFromSurrogates(target.setIndex(--o1), ch); 
/* 3121 */               RegEx.RangeToken tok = op.getToken();
/* 3122 */               if (isSet(opts, 2))
/* 3123 */               { tok = tok.getCaseInsensitiveToken();
/* 3124 */                 if (!tok.match(ch)) {
/* 3125 */                   if (ch >= 65536) return -1; 
/*      */                   char uch;
/* 3127 */                   if (!tok.match(uch = Character.toUpperCase((char)ch)) && 
/* 3128 */                     !tok.match(Character.toLowerCase(uch))) {
/* 3129 */                     return -1;
/*      */                   }
/*      */                 }  }
/* 3132 */               else if (!tok.match(ch)) { return -1; }
/*      */               
/* 3134 */               offset = o1;
/*      */             } 
/* 3136 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 5:
/* 3140 */             go = false;
/* 3141 */             switch (op.getData()) {
/*      */               case 94:
/* 3143 */                 if (isSet(opts, 8)) {
/* 3144 */                   if (offset != con.start && (
/* 3145 */                     offset <= con.start || !isEOLChar(target.setIndex(offset - 1))))
/* 3146 */                     return -1;  break;
/*      */                 } 
/* 3148 */                 if (offset != con.start) {
/* 3149 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 64:
/* 3155 */                 if (offset != con.start && (
/* 3156 */                   offset <= con.start || !isEOLChar(target.setIndex(offset - 1)))) {
/* 3157 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 36:
/* 3161 */                 if (isSet(opts, 8)) {
/* 3162 */                   if (offset != con.limit && (
/* 3163 */                     offset >= con.limit || !isEOLChar(target.setIndex(offset))))
/* 3164 */                     return -1;  break;
/*      */                 } 
/* 3166 */                 if (offset != con.limit && (
/* 3167 */                   offset + 1 != con.limit || !isEOLChar(target.setIndex(offset))) && (
/* 3168 */                   offset + 2 != con.limit || target.setIndex(offset) != '\r' || 
/* 3169 */                   target.setIndex(offset + 1) != '\n')) {
/* 3170 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 65:
/* 3175 */                 if (offset != con.start) return -1;
/*      */                 
/*      */                 break;
/*      */               case 90:
/* 3179 */                 if (offset != con.limit && (
/* 3180 */                   offset + 1 != con.limit || !isEOLChar(target.setIndex(offset))) && (
/* 3181 */                   offset + 2 != con.limit || target.setIndex(offset) != '\r' || 
/* 3182 */                   target.setIndex(offset + 1) != '\n')) {
/* 3183 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 122:
/* 3187 */                 if (offset != con.limit) return -1;
/*      */                 
/*      */                 break;
/*      */               case 98:
/* 3191 */                 if (con.length == 0) return -1;
/*      */                 
/* 3193 */                 after = getWordType(target, con.start, con.limit, offset, opts);
/* 3194 */                 if (after == 0) return -1; 
/* 3195 */                 before = getPreviousWordType(target, con.start, con.limit, offset, opts);
/* 3196 */                 if (after == before) return -1;
/*      */                 
/*      */                 break;
/*      */               
/*      */               case 66:
/* 3201 */                 if (con.length == 0) {
/* 3202 */                   go = true;
/*      */                 } else {
/* 3204 */                   after = getWordType(target, con.start, con.limit, offset, opts);
/* 3205 */                   go = !(after != 0 && 
/* 3206 */                     after != getPreviousWordType(target, con.start, con.limit, offset, opts));
/*      */                 } 
/* 3208 */                 if (!go) return -1;
/*      */                 
/*      */                 break;
/*      */               case 60:
/* 3212 */                 if (con.length == 0 || offset == con.limit) return -1; 
/* 3213 */                 if (getWordType(target, con.start, con.limit, offset, opts) != 1 || 
/* 3214 */                   getPreviousWordType(target, con.start, con.limit, offset, opts) != 2) {
/* 3215 */                   return -1;
/*      */                 }
/*      */                 break;
/*      */               case 62:
/* 3219 */                 if (con.length == 0 || offset == con.start) return -1; 
/* 3220 */                 if (getWordType(target, con.start, con.limit, offset, opts) != 2 || 
/* 3221 */                   getPreviousWordType(target, con.start, con.limit, offset, opts) != 1)
/* 3222 */                   return -1; 
/*      */                 break;
/*      */             } 
/* 3225 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 16:
/* 3230 */             j = op.getData();
/* 3231 */             if (j <= 0 || j >= this.nofparen)
/* 3232 */               throw new RuntimeException("Internal Error: Reference number must be more than zero: " + j); 
/* 3233 */             if (con.match.getBeginning(j) < 0 || 
/* 3234 */               con.match.getEnd(j) < 0)
/* 3235 */               return -1; 
/* 3236 */             o2 = con.match.getBeginning(j);
/* 3237 */             n = con.match.getEnd(j) - o2;
/* 3238 */             if (!isSet(opts, 2)) {
/* 3239 */               if (dx > 0) {
/* 3240 */                 if (!regionMatches(target, offset, con.limit, o2, n))
/* 3241 */                   return -1; 
/* 3242 */                 offset += n;
/*      */               } else {
/* 3244 */                 if (!regionMatches(target, offset - n, con.limit, o2, n))
/* 3245 */                   return -1; 
/* 3246 */                 offset -= n;
/*      */               }
/*      */             
/* 3249 */             } else if (dx > 0) {
/* 3250 */               if (!regionMatchesIgnoreCase(target, offset, con.limit, o2, n))
/* 3251 */                 return -1; 
/* 3252 */               offset += n;
/*      */             } else {
/* 3254 */               if (!regionMatchesIgnoreCase(target, offset - n, con.limit, 
/* 3255 */                   o2, n))
/* 3256 */                 return -1; 
/* 3257 */               offset -= n;
/*      */             } 
/*      */ 
/*      */             
/* 3261 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 6:
/* 3265 */             literal = op.getString();
/* 3266 */             literallen = literal.length();
/* 3267 */             if (!isSet(opts, 2)) {
/* 3268 */               if (dx > 0) {
/* 3269 */                 if (!regionMatches(target, offset, con.limit, literal, literallen))
/* 3270 */                   return -1; 
/* 3271 */                 offset += literallen;
/*      */               } else {
/* 3273 */                 if (!regionMatches(target, offset - literallen, con.limit, literal, literallen))
/* 3274 */                   return -1; 
/* 3275 */                 offset -= literallen;
/*      */               }
/*      */             
/* 3278 */             } else if (dx > 0) {
/* 3279 */               if (!regionMatchesIgnoreCase(target, offset, con.limit, literal, literallen))
/* 3280 */                 return -1; 
/* 3281 */               offset += literallen;
/*      */             } else {
/* 3283 */               if (!regionMatchesIgnoreCase(target, offset - literallen, con.limit, 
/* 3284 */                   literal, literallen))
/* 3285 */                 return -1; 
/* 3286 */               offset -= literallen;
/*      */             } 
/*      */ 
/*      */             
/* 3290 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 7:
/* 3299 */             id = op.getData();
/* 3300 */             if (id >= 0) {
/* 3301 */               int previousOffset = con.offsets[id];
/* 3302 */               if (previousOffset < 0 || previousOffset != offset) {
/* 3303 */                 con.offsets[id] = offset;
/*      */               } else {
/* 3305 */                 con.offsets[id] = -1;
/* 3306 */                 op = op.next;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */             } 
/* 3311 */             k = matchCharacterIterator(con, op.getChild(), offset, dx, opts);
/* 3312 */             if (id >= 0) con.offsets[id] = -1; 
/* 3313 */             if (k >= 0) return k; 
/* 3314 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 9:
/* 3320 */             ret = matchCharacterIterator(con, op.getChild(), offset, dx, opts);
/* 3321 */             if (ret >= 0) return ret; 
/* 3322 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 8:
/*      */           case 10:
/* 3329 */             ret = matchCharacterIterator(con, op.next, offset, dx, opts);
/* 3330 */             if (ret >= 0) return ret; 
/* 3331 */             op = op.getChild();
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 11:
/* 3336 */             for (i = 0; i < op.size(); i++) {
/* 3337 */               k = matchCharacterIterator(con, op.elementAt(i), offset, dx, opts);
/*      */ 
/*      */ 
/*      */               
/* 3341 */               if (k >= 0) return k; 
/*      */             } 
/* 3343 */             return -1;
/*      */           
/*      */           case 15:
/* 3346 */             refno = op.getData();
/* 3347 */             if (con.match != null && refno > 0) {
/* 3348 */               int save = con.match.getBeginning(refno);
/* 3349 */               con.match.setBeginning(refno, offset);
/* 3350 */               int i1 = matchCharacterIterator(con, op.next, offset, dx, opts);
/* 3351 */               if (i1 < 0) con.match.setBeginning(refno, save); 
/* 3352 */               return i1;
/* 3353 */             }  if (con.match != null && refno < 0) {
/* 3354 */               int index = -refno;
/* 3355 */               int save = con.match.getEnd(index);
/* 3356 */               con.match.setEnd(index, offset);
/* 3357 */               int i1 = matchCharacterIterator(con, op.next, offset, dx, opts);
/* 3358 */               if (i1 < 0) con.match.setEnd(index, save); 
/* 3359 */               return i1;
/*      */             } 
/* 3361 */             op = op.next;
/*      */             continue;
/*      */           
/*      */           case 20:
/* 3365 */             if (matchCharacterIterator(con, op.getChild(), offset, 1, opts) < 0) return -1; 
/* 3366 */             op = op.next;
/*      */             continue;
/*      */           case 21:
/* 3369 */             if (matchCharacterIterator(con, op.getChild(), offset, 1, opts) >= 0) return -1; 
/* 3370 */             op = op.next;
/*      */             continue;
/*      */           case 22:
/* 3373 */             if (matchCharacterIterator(con, op.getChild(), offset, -1, opts) < 0) return -1; 
/* 3374 */             op = op.next;
/*      */             continue;
/*      */           case 23:
/* 3377 */             if (matchCharacterIterator(con, op.getChild(), offset, -1, opts) >= 0) return -1; 
/* 3378 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 24:
/* 3383 */             k = matchCharacterIterator(con, op.getChild(), offset, dx, opts);
/* 3384 */             if (k < 0) return k; 
/* 3385 */             offset = k;
/* 3386 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 25:
/* 3392 */             localopts = opts;
/* 3393 */             localopts |= op.getData();
/* 3394 */             localopts &= op.getData2() ^ 0xFFFFFFFF;
/*      */             
/* 3396 */             m = matchCharacterIterator(con, op.getChild(), offset, dx, localopts);
/* 3397 */             if (m < 0) return m; 
/* 3398 */             offset = m;
/* 3399 */             op = op.next;
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 26:
/* 3405 */             cop = (RegEx.Op.ConditionOp)op;
/* 3406 */             matchp = false;
/* 3407 */             if (cop.refNumber > 0) {
/* 3408 */               if (cop.refNumber >= this.nofparen)
/* 3409 */                 throw new RuntimeException("Internal Error: Reference number must be more than zero: " + cop.refNumber); 
/* 3410 */               matchp = (con.match.getBeginning(cop.refNumber) >= 0 && 
/* 3411 */                 con.match.getEnd(cop.refNumber) >= 0);
/*      */             } else {
/* 3413 */               matchp = (matchCharacterIterator(con, cop.condition, offset, dx, opts) >= 0);
/*      */             } 
/*      */             
/* 3416 */             if (matchp) {
/* 3417 */               op = cop.yes; continue;
/* 3418 */             }  if (cop.no != null) {
/* 3419 */               op = cop.no; continue;
/*      */             } 
/* 3421 */             op = cop.next;
/*      */             continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/* 3427 */       throw new RuntimeException("Unknown operation type: " + op.type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int getPreviousWordType(CharacterIterator target, int begin, int end, int offset, int opts) {
/* 3434 */       int ret = getWordType(target, begin, end, --offset, opts);
/* 3435 */       while (ret == 0)
/* 3436 */         ret = getWordType(target, begin, end, --offset, opts); 
/* 3437 */       return ret;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final int getWordType(CharacterIterator target, int begin, int end, int offset, int opts) {
/* 3442 */       if (offset < begin || offset >= end) return 2; 
/* 3443 */       return getWordType0(target.setIndex(offset), opts);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean regionMatches(CharacterIterator target, int offset, int limit, String part, int partlen) {
/* 3450 */       if (offset < 0) return false; 
/* 3451 */       if (limit - offset < partlen)
/* 3452 */         return false; 
/* 3453 */       int i = 0;
/* 3454 */       while (partlen-- > 0) {
/* 3455 */         if (target.setIndex(offset++) != part.charAt(i++))
/* 3456 */           return false; 
/*      */       } 
/* 3458 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatches(CharacterIterator target, int offset, int limit, int offset2, int partlen) {
/* 3463 */       if (offset < 0) return false; 
/* 3464 */       if (limit - offset < partlen)
/* 3465 */         return false; 
/* 3466 */       int i = offset2;
/* 3467 */       while (partlen-- > 0) {
/* 3468 */         if (target.setIndex(offset++) != target.setIndex(i++))
/* 3469 */           return false; 
/*      */       } 
/* 3471 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean regionMatchesIgnoreCase(CharacterIterator target, int offset, int limit, String part, int partlen) {
/* 3479 */       if (offset < 0) return false; 
/* 3480 */       if (limit - offset < partlen)
/* 3481 */         return false; 
/* 3482 */       int i = 0;
/* 3483 */       while (partlen-- > 0) {
/* 3484 */         char ch1 = target.setIndex(offset++);
/* 3485 */         char ch2 = part.charAt(i++);
/* 3486 */         if (ch1 == ch2)
/*      */           continue; 
/* 3488 */         char uch1 = Character.toUpperCase(ch1);
/* 3489 */         char uch2 = Character.toUpperCase(ch2);
/* 3490 */         if (uch1 == uch2)
/*      */           continue; 
/* 3492 */         if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2))
/* 3493 */           return false; 
/*      */       } 
/* 3495 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private static final boolean regionMatchesIgnoreCase(CharacterIterator target, int offset, int limit, int offset2, int partlen) {
/* 3500 */       if (offset < 0) return false; 
/* 3501 */       if (limit - offset < partlen)
/* 3502 */         return false; 
/* 3503 */       int i = offset2;
/* 3504 */       while (partlen-- > 0) {
/* 3505 */         char ch1 = target.setIndex(offset++);
/* 3506 */         char ch2 = target.setIndex(i++);
/* 3507 */         if (ch1 == ch2)
/*      */           continue; 
/* 3509 */         char uch1 = Character.toUpperCase(ch1);
/* 3510 */         char uch2 = Character.toUpperCase(ch2);
/* 3511 */         if (uch1 == uch2)
/*      */           continue; 
/* 3513 */         if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2))
/* 3514 */           return false; 
/*      */       } 
/* 3516 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasBackReferences = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     transient int minlength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3548 */     transient RegEx.Op operations = null;
/*      */     transient int numberOfClosures;
/* 3550 */     transient Context context = null;
/* 3551 */     transient RegEx.RangeToken firstChar = null;
/*      */     
/* 3553 */     transient String fixedString = null;
/*      */     transient int fixedStringOptions;
/* 3555 */     transient RegEx.BMPattern fixedStringTable = null; transient boolean fixedStringOnly = false; static final int IGNORE_CASE = 2; static final int SINGLE_LINE = 4; static final int MULTIPLE_LINES = 8; static final int EXTENDED_COMMENT = 16; static final int USE_UNICODE_CATEGORY = 32; static final int UNICODE_WORD_BOUNDARY = 64; static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128; static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256; static final int XMLSCHEMA_MODE = 512; static final int SPECIAL_COMMA = 1024;
/*      */     private static final int WT_IGNORE = 0;
/*      */     private static final int WT_LETTER = 1;
/*      */     private static final int WT_OTHER = 2;
/*      */     static final int LINE_FEED = 10;
/*      */     static final int CARRIAGE_RETURN = 13;
/*      */     static final int LINE_SEPARATOR = 8232;
/*      */     static final int PARAGRAPH_SEPARATOR = 8233;
/*      */     
/*      */     static final class Context { CharacterIterator ciTarget;
/*      */       String strTarget;
/*      */       char[] charTarget;
/*      */       int start;
/*      */       int limit;
/*      */       int length;
/*      */       RegEx.Match match;
/*      */       boolean inuse = false;
/*      */       int[] offsets;
/*      */       
/*      */       private void resetCommon(int nofclosures) {
/* 3575 */         this.length = this.limit - this.start;
/* 3576 */         this.inuse = true;
/* 3577 */         this.match = null;
/* 3578 */         if (this.offsets == null || this.offsets.length != nofclosures)
/* 3579 */           this.offsets = new int[nofclosures]; 
/* 3580 */         for (int i = 0; i < nofclosures; ) { this.offsets[i] = -1; i++; }
/*      */       
/*      */       } void reset(CharacterIterator target, int start, int limit, int nofclosures) {
/* 3583 */         this.ciTarget = target;
/* 3584 */         this.start = start;
/* 3585 */         this.limit = limit;
/* 3586 */         resetCommon(nofclosures);
/*      */       }
/*      */       void reset(String target, int start, int limit, int nofclosures) {
/* 3589 */         this.strTarget = target;
/* 3590 */         this.start = start;
/* 3591 */         this.limit = limit;
/* 3592 */         resetCommon(nofclosures);
/*      */       }
/*      */       void reset(char[] target, int start, int limit, int nofclosures) {
/* 3595 */         this.charTarget = target;
/* 3596 */         this.start = start;
/* 3597 */         this.limit = limit;
/* 3598 */         resetCommon(nofclosures);
/*      */       } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void prepare() {
/* 3607 */       compile(this.tokentree);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3617 */       this.minlength = this.tokentree.getMinLength();
/*      */       
/* 3619 */       this.firstChar = null;
/* 3620 */       if (!isSet(this.options, 128) && 
/* 3621 */         !isSet(this.options, 512)) {
/* 3622 */         RegEx.RangeToken firstChar = RegEx.Token.createRange();
/* 3623 */         int fresult = this.tokentree.analyzeFirstCharacter(firstChar, this.options);
/* 3624 */         if (fresult == 1) {
/* 3625 */           firstChar.compactRanges();
/* 3626 */           this.firstChar = firstChar;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3632 */       if (this.operations != null && (
/* 3633 */         this.operations.type == 6 || this.operations.type == 1) && 
/* 3634 */         this.operations.next == null) {
/*      */ 
/*      */         
/* 3637 */         this.fixedStringOnly = true;
/* 3638 */         if (this.operations.type == 6) {
/* 3639 */           this.fixedString = this.operations.getString();
/* 3640 */         } else if (this.operations.getData() >= 65536) {
/* 3641 */           this.fixedString = RegEx.REUtil.decomposeToSurrogates(this.operations.getData());
/*      */         } else {
/* 3643 */           char[] ac = new char[1];
/* 3644 */           ac[0] = (char)this.operations.getData();
/* 3645 */           this.fixedString = new String(ac);
/*      */         } 
/* 3647 */         this.fixedStringOptions = this.options;
/* 3648 */         this.fixedStringTable = new RegEx.BMPattern(this.fixedString, 256, 
/* 3649 */             isSet(this.fixedStringOptions, 2));
/* 3650 */       } else if (!isSet(this.options, 256) && 
/* 3651 */         !isSet(this.options, 512)) {
/* 3652 */         RegEx.Token.FixedStringContainer container = new RegEx.Token.FixedStringContainer();
/* 3653 */         this.tokentree.findFixedString(container, this.options);
/* 3654 */         this.fixedString = (container.token == null) ? null : container.token.getString();
/* 3655 */         this.fixedStringOptions = container.options;
/* 3656 */         if (this.fixedString != null && this.fixedString.length() < 2) {
/* 3657 */           this.fixedString = null;
/*      */         }
/* 3659 */         if (this.fixedString != null) {
/* 3660 */           this.fixedStringTable = new RegEx.BMPattern(this.fixedString, 256, 
/* 3661 */               isSet(this.fixedStringOptions, 2));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean isSet(int options, int flag) {
/* 3746 */       return ((options & flag) == flag);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RegularExpression(String regex) throws RegEx.ParseException {
/* 3756 */       setPattern(regex, (String)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RegularExpression(String regex, String options) throws RegEx.ParseException {
/* 3767 */       setPattern(regex, options);
/*      */     }
/*      */     
/*      */     RegularExpression(String regex, RegEx.Token tok, int parens, boolean hasBackReferences, int options) {
/* 3771 */       this.regex = regex;
/* 3772 */       this.tokentree = tok;
/* 3773 */       this.nofparen = parens;
/* 3774 */       this.options = options;
/* 3775 */       this.hasBackReferences = hasBackReferences;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setPattern(String newPattern) throws RegEx.ParseException {
/* 3782 */       setPattern(newPattern, this.options);
/*      */     }
/*      */     
/*      */     private void setPattern(String newPattern, int options) throws RegEx.ParseException {
/* 3786 */       this.regex = newPattern;
/* 3787 */       this.options = options;
/* 3788 */       RegEx.RegexParser rp = isSet(this.options, 512) ? 
/* 3789 */         new RegEx.ParserForXMLSchema() : new RegEx.RegexParser();
/* 3790 */       this.tokentree = rp.parse(this.regex, this.options);
/* 3791 */       this.nofparen = rp.parennumber;
/* 3792 */       this.hasBackReferences = rp.hasBackReferences;
/*      */       
/* 3794 */       this.operations = null;
/* 3795 */       this.context = null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setPattern(String newPattern, String options) throws RegEx.ParseException {
/* 3801 */       setPattern(newPattern, RegEx.REUtil.parseOptions(options));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPattern() {
/* 3808 */       return this.regex;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 3816 */       return this.tokentree.toString(this.options);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getOptions() {
/* 3828 */       return RegEx.REUtil.createOptionString(this.options);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 3836 */       if (obj == null) return false; 
/* 3837 */       if (!(obj instanceof RegularExpression))
/* 3838 */         return false; 
/* 3839 */       RegularExpression r = (RegularExpression)obj;
/* 3840 */       return (this.regex.equals(r.regex) && this.options == r.options);
/*      */     }
/*      */     
/*      */     boolean equals(String pattern, int options) {
/* 3844 */       return (this.regex.equals(pattern) && this.options == options);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 3852 */       return (String.valueOf(this.regex) + "/" + getOptions()).hashCode();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumberOfGroups() {
/* 3861 */       return this.nofparen;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int getWordType0(char ch, int opts) {
/* 3870 */       if (!isSet(opts, 64)) {
/* 3871 */         if (isSet(opts, 32)) {
/* 3872 */           return RegEx.Token.getRange("IsWord", true).match(ch) ? 1 : 2;
/*      */         }
/* 3874 */         return isWordChar(ch) ? 1 : 2;
/*      */       } 
/*      */       
/* 3877 */       switch (Character.getType(ch)) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/* 3887 */           return 1;
/*      */         
/*      */         case 6:
/*      */         case 7:
/*      */         case 16:
/* 3892 */           return 0;
/*      */         
/*      */         case 15:
/* 3895 */           switch (ch) {
/*      */             case '\t':
/*      */             case '\n':
/*      */             case '\013':
/*      */             case '\f':
/*      */             case '\r':
/* 3901 */               return 2;
/*      */           } 
/* 3903 */           return 0;
/*      */       } 
/*      */ 
/*      */       
/* 3907 */       return 2;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final boolean isEOLChar(int ch) {
/* 3919 */       if (ch != 10 && ch != 13 && ch != 8232 && 
/* 3920 */         ch != 8233) return false; 
/*      */       return true;
/*      */     }
/*      */     private static final boolean isWordChar(int ch) {
/* 3924 */       if (ch == 95) return true; 
/* 3925 */       if (ch < 48) return false; 
/* 3926 */       if (ch > 122) return false; 
/* 3927 */       if (ch <= 57) return true; 
/* 3928 */       if (ch < 65) return false; 
/* 3929 */       if (ch <= 90) return true; 
/* 3930 */       if (ch < 97) return false; 
/* 3931 */       return true;
/*      */     }
/*      */     
/*      */     private static final boolean matchIgnoreCase(int chardata, int ch) {
/* 3935 */       if (chardata == ch) return true; 
/* 3936 */       if (chardata > 65535 || ch > 65535) return false; 
/* 3937 */       char uch1 = Character.toUpperCase((char)chardata);
/* 3938 */       char uch2 = Character.toUpperCase((char)ch);
/* 3939 */       if (uch1 == uch2) return true; 
/* 3940 */       return (Character.toLowerCase(uch1) == Character.toLowerCase(uch2));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ParseException
/*      */     extends RuntimeException
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     
/*      */     int location;
/*      */ 
/*      */ 
/*      */     
/*      */     public ParseException(String mes, int location) {
/* 3958 */       super(mes);
/* 3959 */       this.location = location;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLocation() {
/* 3967 */       return this.location;
/*      */     }
/*      */   }
/*      */   
/*      */   static class Op
/*      */   {
/*      */     static final int DOT = 0;
/*      */     static final int CHAR = 1;
/*      */     static final int RANGE = 3;
/*      */     static final int NRANGE = 4;
/*      */     static final int ANCHOR = 5;
/*      */     static final int STRING = 6;
/*      */     static final int CLOSURE = 7;
/*      */     static final int NONGREEDYCLOSURE = 8;
/*      */     static final int QUESTION = 9;
/*      */     static final int NONGREEDYQUESTION = 10;
/*      */     static final int UNION = 11;
/*      */     static final int CAPTURE = 15;
/*      */     static final int BACKREFERENCE = 16;
/*      */     static final int LOOKAHEAD = 20;
/*      */     static final int NEGATIVELOOKAHEAD = 21;
/*      */     static final int LOOKBEHIND = 22;
/*      */     static final int NEGATIVELOOKBEHIND = 23;
/*      */     static final int INDEPENDENT = 24;
/*      */     static final int MODIFIER = 25;
/*      */     static final int CONDITION = 26;
/* 3993 */     static int nofinstances = 0;
/*      */     static final boolean COUNT = false;
/*      */     int type;
/*      */     
/*      */     static Op createDot() {
/* 3998 */       return new Op(0);
/*      */     }
/*      */     
/*      */     static CharOp createChar(int data) {
/* 4002 */       return new CharOp(1, data);
/*      */     }
/*      */     
/*      */     static CharOp createAnchor(int data) {
/* 4006 */       return new CharOp(5, data);
/*      */     }
/*      */     
/*      */     static CharOp createCapture(int number, Op next) {
/* 4010 */       CharOp op = new CharOp(15, number);
/* 4011 */       op.next = next;
/* 4012 */       return op;
/*      */     }
/*      */ 
/*      */     
/*      */     static UnionOp createUnion(int size) {
/* 4017 */       return new UnionOp(11, size);
/*      */     }
/*      */     
/*      */     static ChildOp createClosure(int id) {
/* 4021 */       return new ModifierOp(7, id, -1);
/*      */     }
/*      */     
/*      */     static ChildOp createNonGreedyClosure() {
/* 4025 */       return new ChildOp(8);
/*      */     }
/*      */     
/*      */     static ChildOp createQuestion(boolean nongreedy) {
/* 4029 */       return new ChildOp(nongreedy ? 10 : 9);
/*      */     }
/*      */     
/*      */     static RangeOp createRange(RegEx.Token tok) {
/* 4033 */       return new RangeOp(3, tok);
/*      */     }
/*      */     
/*      */     static ChildOp createLook(int type, Op next, Op branch) {
/* 4037 */       ChildOp op = new ChildOp(type);
/* 4038 */       op.setChild(branch);
/* 4039 */       op.next = next;
/* 4040 */       return op;
/*      */     }
/*      */     
/*      */     static CharOp createBackReference(int refno) {
/* 4044 */       return new CharOp(16, refno);
/*      */     }
/*      */     
/*      */     static StringOp createString(String literal) {
/* 4048 */       return new StringOp(6, literal);
/*      */     }
/*      */     
/*      */     static ChildOp createIndependent(Op next, Op branch) {
/* 4052 */       ChildOp op = new ChildOp(24);
/* 4053 */       op.setChild(branch);
/* 4054 */       op.next = next;
/* 4055 */       return op;
/*      */     }
/*      */     
/*      */     static ModifierOp createModifier(Op next, Op branch, int add, int mask) {
/* 4059 */       ModifierOp op = new ModifierOp(25, add, mask);
/* 4060 */       op.setChild(branch);
/* 4061 */       op.next = next;
/* 4062 */       return op;
/*      */     }
/*      */     
/*      */     static ConditionOp createCondition(Op next, int ref, Op conditionflow, Op yesflow, Op noflow) {
/* 4066 */       ConditionOp op = new ConditionOp(26, ref, conditionflow, yesflow, noflow);
/* 4067 */       op.next = next;
/* 4068 */       return op;
/*      */     }
/*      */ 
/*      */     
/* 4072 */     Op next = null;
/*      */     
/*      */     protected Op(int type) {
/* 4075 */       this.type = type;
/*      */     }
/*      */     
/*      */     int size() {
/* 4079 */       return 0;
/*      */     }
/*      */     Op elementAt(int index) {
/* 4082 */       throw new RuntimeException("Internal Error: type=" + this.type);
/*      */     }
/*      */     Op getChild() {
/* 4085 */       throw new RuntimeException("Internal Error: type=" + this.type);
/*      */     }
/*      */     
/*      */     int getData() {
/* 4089 */       throw new RuntimeException("Internal Error: type=" + this.type);
/*      */     }
/*      */     int getData2() {
/* 4092 */       throw new RuntimeException("Internal Error: type=" + this.type);
/*      */     }
/*      */     RegEx.RangeToken getToken() {
/* 4095 */       throw new RuntimeException("Internal Error: type=" + this.type);
/*      */     }
/*      */     String getString() {
/* 4098 */       throw new RuntimeException("Internal Error: type=" + this.type);
/*      */     }
/*      */     
/*      */     static class CharOp extends Op {
/*      */       int charData;
/*      */       
/*      */       CharOp(int type, int data) {
/* 4105 */         super(type);
/* 4106 */         this.charData = data;
/*      */       }
/*      */       
/*      */       int getData() {
/* 4110 */         return this.charData;
/*      */       }
/*      */     }
/*      */     
/*      */     static class UnionOp extends Op {
/*      */       Vector<RegEx.Op> branches;
/*      */       
/*      */       UnionOp(int type, int size) {
/* 4118 */         super(type);
/* 4119 */         this.branches = new Vector<RegEx.Op>(size);
/*      */       }
/*      */       void addElement(RegEx.Op op) {
/* 4122 */         this.branches.addElement(op);
/*      */       }
/*      */       
/*      */       int size() {
/* 4126 */         return this.branches.size();
/*      */       }
/*      */       
/*      */       RegEx.Op elementAt(int index) {
/* 4130 */         return this.branches.elementAt(index);
/*      */       }
/*      */     }
/*      */     
/*      */     static class ChildOp extends Op {
/*      */       RegEx.Op child;
/*      */       
/*      */       ChildOp(int type) {
/* 4138 */         super(type);
/*      */       }
/*      */       void setChild(RegEx.Op child) {
/* 4141 */         this.child = child;
/*      */       }
/*      */       
/*      */       RegEx.Op getChild() {
/* 4145 */         return this.child;
/*      */       }
/*      */     }
/*      */     
/*      */     static class ModifierOp extends ChildOp { int v1;
/*      */       int v2;
/*      */       
/*      */       ModifierOp(int type, int v1, int v2) {
/* 4153 */         super(type);
/* 4154 */         this.v1 = v1;
/* 4155 */         this.v2 = v2;
/*      */       }
/*      */       
/*      */       int getData() {
/* 4159 */         return this.v1;
/*      */       }
/*      */       
/*      */       int getData2() {
/* 4163 */         return this.v2;
/*      */       } }
/*      */     
/*      */     static class RangeOp extends Op {
/*      */       RegEx.Token tok;
/*      */       
/*      */       RangeOp(int type, RegEx.Token tok) {
/* 4170 */         super(type);
/* 4171 */         this.tok = tok;
/*      */       }
/*      */       
/*      */       RegEx.RangeToken getToken() {
/* 4175 */         return (RegEx.RangeToken)this.tok;
/*      */       }
/*      */     }
/*      */     
/*      */     static class StringOp extends Op { String string;
/*      */       
/*      */       StringOp(int type, String literal) {
/* 4182 */         super(type);
/* 4183 */         this.string = literal;
/*      */       }
/*      */       
/*      */       String getString() {
/* 4187 */         return this.string;
/*      */       } }
/*      */     
/*      */     static class ConditionOp extends Op {
/*      */       int refNumber;
/*      */       RegEx.Op condition;
/*      */       RegEx.Op yes;
/*      */       RegEx.Op no;
/*      */       
/*      */       ConditionOp(int type, int refno, RegEx.Op conditionflow, RegEx.Op yesflow, RegEx.Op noflow) {
/* 4197 */         super(type);
/* 4198 */         this.refNumber = refno;
/* 4199 */         this.condition = conditionflow;
/* 4200 */         this.yes = yesflow;
/* 4201 */         this.no = noflow;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static final class RangeToken
/*      */     extends Token implements Serializable {
/*      */     private static final long serialVersionUID = 1L;
/*      */     int[] ranges;
/*      */     boolean sorted;
/*      */     boolean compacted;
/* 4212 */     RangeToken icaseCache = null;
/* 4213 */     int[] map = null; int nonMapIndex;
/*      */     private static final int MAPSIZE = 256;
/*      */     
/*      */     RangeToken(int type) {
/* 4217 */       super(type);
/* 4218 */       setSorted(false);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void addRange(int start, int end) {
/*      */       int r1, r2;
/* 4224 */       this.icaseCache = null;
/*      */ 
/*      */       
/* 4227 */       if (start <= end) {
/* 4228 */         r1 = start;
/* 4229 */         r2 = end;
/*      */       } else {
/* 4231 */         r1 = end;
/* 4232 */         r2 = start;
/*      */       } 
/*      */       
/* 4235 */       int pos = 0;
/* 4236 */       if (this.ranges == null) {
/* 4237 */         this.ranges = new int[2];
/* 4238 */         this.ranges[0] = r1;
/* 4239 */         this.ranges[1] = r2;
/* 4240 */         setSorted(true);
/*      */       } else {
/* 4242 */         pos = this.ranges.length;
/* 4243 */         if (this.ranges[pos - 1] + 1 == r1) {
/* 4244 */           this.ranges[pos - 1] = r2;
/*      */           return;
/*      */         } 
/* 4247 */         int[] temp = new int[pos + 2];
/* 4248 */         System.arraycopy(this.ranges, 0, temp, 0, pos);
/* 4249 */         this.ranges = temp;
/* 4250 */         if (this.ranges[pos - 1] >= r1)
/* 4251 */           setSorted(false); 
/* 4252 */         this.ranges[pos++] = r1;
/* 4253 */         this.ranges[pos] = r2;
/* 4254 */         if (!this.sorted)
/* 4255 */           sortRanges(); 
/*      */       } 
/*      */     }
/*      */     
/*      */     private final boolean isSorted() {
/* 4260 */       return this.sorted;
/*      */     }
/*      */     private final void setSorted(boolean sort) {
/* 4263 */       this.sorted = sort;
/* 4264 */       if (!sort) this.compacted = false; 
/*      */     }
/*      */     private final boolean isCompacted() {
/* 4267 */       return this.compacted;
/*      */     }
/*      */     private final void setCompacted() {
/* 4270 */       this.compacted = true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void sortRanges() {
/* 4275 */       if (isSorted())
/*      */         return; 
/* 4277 */       if (this.ranges == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4284 */       for (int i = this.ranges.length - 4; i >= 0; i -= 2) {
/* 4285 */         for (int j = 0; j <= i; j += 2) {
/* 4286 */           if (this.ranges[j] > this.ranges[j + 2] || (
/* 4287 */             this.ranges[j] == this.ranges[j + 2] && this.ranges[j + 1] > this.ranges[j + 3])) {
/*      */             
/* 4289 */             int tmp = this.ranges[j + 2];
/* 4290 */             this.ranges[j + 2] = this.ranges[j];
/* 4291 */             this.ranges[j] = tmp;
/* 4292 */             tmp = this.ranges[j + 3];
/* 4293 */             this.ranges[j + 3] = this.ranges[j + 1];
/* 4294 */             this.ranges[j + 1] = tmp;
/*      */           } 
/*      */         } 
/*      */       } 
/* 4298 */       setSorted(true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void compactRanges() {
/* 4306 */       boolean DEBUG = false;
/* 4307 */       if (this.ranges == null || this.ranges.length <= 2)
/*      */         return; 
/* 4309 */       if (isCompacted())
/*      */         return; 
/* 4311 */       int base = 0;
/* 4312 */       int target = 0;
/*      */       
/* 4314 */       while (target < this.ranges.length) {
/* 4315 */         if (base != target) {
/* 4316 */           this.ranges[base] = this.ranges[target++];
/* 4317 */           this.ranges[base + 1] = this.ranges[target++];
/*      */         } else {
/* 4319 */           target += 2;
/* 4320 */         }  int baseend = this.ranges[base + 1];
/* 4321 */         while (target < this.ranges.length && 
/* 4322 */           baseend + 1 >= this.ranges[target]) {
/*      */           
/* 4324 */           if (baseend + 1 == this.ranges[target]) {
/* 4325 */             if (DEBUG)
/* 4326 */               System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + 
/* 4327 */                   ", " + this.ranges[base + 1] + 
/* 4328 */                   "], [" + this.ranges[target] + 
/* 4329 */                   ", " + this.ranges[target + 1] + 
/* 4330 */                   "] -> [" + this.ranges[base] + 
/* 4331 */                   ", " + this.ranges[target + 1] + 
/* 4332 */                   "]"); 
/* 4333 */             this.ranges[base + 1] = this.ranges[target + 1];
/* 4334 */             baseend = this.ranges[base + 1];
/* 4335 */             target += 2; continue;
/* 4336 */           }  if (baseend >= this.ranges[target + 1]) {
/* 4337 */             if (DEBUG)
/* 4338 */               System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + 
/* 4339 */                   ", " + this.ranges[base + 1] + 
/* 4340 */                   "], [" + this.ranges[target] + 
/* 4341 */                   ", " + this.ranges[target + 1] + 
/* 4342 */                   "] -> [" + this.ranges[base] + 
/* 4343 */                   ", " + this.ranges[base + 1] + 
/* 4344 */                   "]"); 
/* 4345 */             target += 2; continue;
/* 4346 */           }  if (baseend < this.ranges[target + 1]) {
/* 4347 */             if (DEBUG)
/* 4348 */               System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + 
/* 4349 */                   ", " + this.ranges[base + 1] + 
/* 4350 */                   "], [" + this.ranges[target] + 
/* 4351 */                   ", " + this.ranges[target + 1] + 
/* 4352 */                   "] -> [" + this.ranges[base] + 
/* 4353 */                   ", " + this.ranges[target + 1] + 
/* 4354 */                   "]"); 
/* 4355 */             this.ranges[base + 1] = this.ranges[target + 1];
/* 4356 */             baseend = this.ranges[base + 1];
/* 4357 */             target += 2; continue;
/*      */           } 
/* 4359 */           throw new RuntimeException("Token#compactRanges(): Internel Error: [" + 
/* 4360 */               this.ranges[base] + 
/* 4361 */               "," + this.ranges[base + 1] + 
/* 4362 */               "] [" + this.ranges[target] + 
/* 4363 */               "," + this.ranges[target + 1] + "]");
/*      */         } 
/*      */         
/* 4366 */         base += 2;
/*      */       } 
/*      */       
/* 4369 */       if (base != this.ranges.length) {
/* 4370 */         int[] result = new int[base];
/* 4371 */         System.arraycopy(this.ranges, 0, result, 0, base);
/* 4372 */         this.ranges = result;
/*      */       } 
/* 4374 */       setCompacted();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void mergeRanges(RegEx.Token token) {
/* 4379 */       RangeToken tok = (RangeToken)token;
/* 4380 */       sortRanges();
/* 4381 */       tok.sortRanges();
/* 4382 */       if (tok.ranges == null)
/*      */         return; 
/* 4384 */       this.icaseCache = null;
/* 4385 */       setSorted(true);
/* 4386 */       if (this.ranges == null) {
/* 4387 */         this.ranges = new int[tok.ranges.length];
/* 4388 */         System.arraycopy(tok.ranges, 0, this.ranges, 0, tok.ranges.length);
/*      */         return;
/*      */       } 
/* 4391 */       int[] result = new int[this.ranges.length + tok.ranges.length];
/* 4392 */       for (int i = 0, j = 0, k = 0; i < this.ranges.length || j < tok.ranges.length; ) {
/* 4393 */         if (i >= this.ranges.length) {
/* 4394 */           result[k++] = tok.ranges[j++];
/* 4395 */           result[k++] = tok.ranges[j++]; continue;
/* 4396 */         }  if (j >= tok.ranges.length) {
/* 4397 */           result[k++] = this.ranges[i++];
/* 4398 */           result[k++] = this.ranges[i++]; continue;
/* 4399 */         }  if (tok.ranges[j] < this.ranges[i] || (
/* 4400 */           tok.ranges[j] == this.ranges[i] && tok.ranges[j + 1] < this.ranges[i + 1])) {
/* 4401 */           result[k++] = tok.ranges[j++];
/* 4402 */           result[k++] = tok.ranges[j++]; continue;
/*      */         } 
/* 4404 */         result[k++] = this.ranges[i++];
/* 4405 */         result[k++] = this.ranges[i++];
/*      */       } 
/*      */       
/* 4408 */       this.ranges = result;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void subtractRanges(RegEx.Token token) {
/* 4413 */       if (token.type == 5) {
/* 4414 */         intersectRanges(token);
/*      */         return;
/*      */       } 
/* 4417 */       RangeToken tok = (RangeToken)token;
/* 4418 */       if (tok.ranges == null || this.ranges == null)
/*      */         return; 
/* 4420 */       this.icaseCache = null;
/* 4421 */       sortRanges();
/* 4422 */       compactRanges();
/* 4423 */       tok.sortRanges();
/* 4424 */       tok.compactRanges();
/*      */ 
/*      */ 
/*      */       
/* 4428 */       int[] result = new int[this.ranges.length + tok.ranges.length];
/* 4429 */       int wp = 0, src = 0, sub = 0;
/* 4430 */       while (src < this.ranges.length && sub < tok.ranges.length) {
/* 4431 */         int srcbegin = this.ranges[src];
/* 4432 */         int srcend = this.ranges[src + 1];
/* 4433 */         int subbegin = tok.ranges[sub];
/* 4434 */         int subend = tok.ranges[sub + 1];
/* 4435 */         if (srcend < subbegin) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4440 */           result[wp++] = this.ranges[src++];
/* 4441 */           result[wp++] = this.ranges[src++]; continue;
/* 4442 */         }  if (srcend >= subbegin && 
/* 4443 */           srcbegin <= subend) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4449 */           if (subbegin <= srcbegin && srcend <= subend) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4454 */             src += 2; continue;
/* 4455 */           }  if (subbegin <= srcbegin) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4460 */             this.ranges[src] = subend + 1;
/* 4461 */             sub += 2; continue;
/* 4462 */           }  if (srcend <= subend) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4467 */             result[wp++] = srcbegin;
/* 4468 */             result[wp++] = subbegin - 1;
/* 4469 */             src += 2;
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/* 4475 */           result[wp++] = srcbegin;
/* 4476 */           result[wp++] = subbegin - 1;
/* 4477 */           this.ranges[src] = subend + 1;
/* 4478 */           sub += 2; continue;
/*      */         } 
/* 4480 */         if (subend < srcbegin) {
/*      */ 
/*      */ 
/*      */           
/* 4484 */           sub += 2; continue;
/*      */         } 
/* 4486 */         throw new RuntimeException("Token#subtractRanges(): Internal Error: [" + this.ranges[src] + 
/* 4487 */             "," + this.ranges[src + 1] + 
/* 4488 */             "] - [" + tok.ranges[sub] + 
/* 4489 */             "," + tok.ranges[sub + 1] + 
/* 4490 */             "]");
/*      */       } 
/*      */       
/* 4493 */       while (src < this.ranges.length) {
/* 4494 */         result[wp++] = this.ranges[src++];
/* 4495 */         result[wp++] = this.ranges[src++];
/*      */       } 
/* 4497 */       this.ranges = new int[wp];
/* 4498 */       System.arraycopy(result, 0, this.ranges, 0, wp);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void intersectRanges(RegEx.Token token) {
/* 4507 */       RangeToken tok = (RangeToken)token;
/* 4508 */       if (tok.ranges == null || this.ranges == null)
/*      */         return; 
/* 4510 */       this.icaseCache = null;
/* 4511 */       sortRanges();
/* 4512 */       compactRanges();
/* 4513 */       tok.sortRanges();
/* 4514 */       tok.compactRanges();
/*      */       
/* 4516 */       int[] result = new int[this.ranges.length + tok.ranges.length];
/* 4517 */       int wp = 0, src1 = 0, src2 = 0;
/* 4518 */       while (src1 < this.ranges.length && src2 < tok.ranges.length) {
/* 4519 */         int src1begin = this.ranges[src1];
/* 4520 */         int src1end = this.ranges[src1 + 1];
/* 4521 */         int src2begin = tok.ranges[src2];
/* 4522 */         int src2end = tok.ranges[src2 + 1];
/* 4523 */         if (src1end < src2begin) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4528 */           src1 += 2; continue;
/* 4529 */         }  if (src1end >= src2begin && 
/* 4530 */           src1begin <= src2end) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4536 */           if (src2begin <= src1begin && src1end <= src2end) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4541 */             result[wp++] = src1begin;
/* 4542 */             result[wp++] = src1end;
/* 4543 */             src1 += 2; continue;
/* 4544 */           }  if (src2begin <= src1begin) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4549 */             result[wp++] = src1begin;
/* 4550 */             result[wp++] = src2end;
/* 4551 */             this.ranges[src1] = src2end + 1;
/* 4552 */             src2 += 2; continue;
/* 4553 */           }  if (src1end <= src2end) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4558 */             result[wp++] = src2begin;
/* 4559 */             result[wp++] = src1end;
/* 4560 */             src1 += 2;
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/* 4566 */           result[wp++] = src2begin;
/* 4567 */           result[wp++] = src2end;
/* 4568 */           this.ranges[src1] = src2end + 1; continue;
/*      */         } 
/* 4570 */         if (src2end < src1begin) {
/*      */ 
/*      */ 
/*      */           
/* 4574 */           src2 += 2; continue;
/*      */         } 
/* 4576 */         throw new RuntimeException("Token#intersectRanges(): Internal Error: [" + 
/* 4577 */             this.ranges[src1] + 
/* 4578 */             "," + this.ranges[src1 + 1] + 
/* 4579 */             "] & [" + tok.ranges[src2] + 
/* 4580 */             "," + tok.ranges[src2 + 1] + 
/* 4581 */             "]");
/*      */       } 
/*      */       
/* 4584 */       while (src1 < this.ranges.length) {
/* 4585 */         result[wp++] = this.ranges[src1++];
/* 4586 */         result[wp++] = this.ranges[src1++];
/*      */       } 
/* 4588 */       this.ranges = new int[wp];
/* 4589 */       System.arraycopy(result, 0, this.ranges, 0, wp);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static RegEx.Token complementRanges(RegEx.Token token) {
/* 4598 */       if (token.type != 4 && token.type != 5)
/* 4599 */         throw new IllegalArgumentException("Token#complementRanges(): must be RANGE: " + token.type); 
/* 4600 */       RangeToken tok = (RangeToken)token;
/* 4601 */       tok.sortRanges();
/* 4602 */       tok.compactRanges();
/* 4603 */       int len = tok.ranges.length + 2;
/* 4604 */       if (tok.ranges[0] == 0)
/* 4605 */         len -= 2; 
/* 4606 */       int last = tok.ranges[tok.ranges.length - 1];
/* 4607 */       if (last == 1114111)
/* 4608 */         len -= 2; 
/* 4609 */       RangeToken ret = RegEx.Token.createRange();
/* 4610 */       ret.ranges = new int[len];
/* 4611 */       int wp = 0;
/* 4612 */       if (tok.ranges[0] > 0) {
/* 4613 */         ret.ranges[wp++] = 0;
/* 4614 */         ret.ranges[wp++] = tok.ranges[0] - 1;
/*      */       } 
/* 4616 */       for (int i = 1; i < tok.ranges.length - 2; i += 2) {
/* 4617 */         ret.ranges[wp++] = tok.ranges[i] + 1;
/* 4618 */         ret.ranges[wp++] = tok.ranges[i + 1] - 1;
/*      */       } 
/* 4620 */       if (last != 1114111) {
/* 4621 */         ret.ranges[wp++] = last + 1;
/* 4622 */         ret.ranges[wp] = 1114111;
/*      */       } 
/* 4624 */       ret.setCompacted();
/* 4625 */       return ret;
/*      */     }
/*      */     
/*      */     synchronized RangeToken getCaseInsensitiveToken() {
/* 4629 */       if (this.icaseCache != null) {
/* 4630 */         return this.icaseCache;
/*      */       }
/* 4632 */       RangeToken uppers = (this.type == 4) ? RegEx.Token.createRange() : RegEx.Token.createNRange();
/* 4633 */       for (int i = 0; i < this.ranges.length; i += 2) {
/* 4634 */         for (int ch = this.ranges[i]; ch <= this.ranges[i + 1]; ch++) {
/* 4635 */           if (ch > 65535) {
/* 4636 */             uppers.addRange(ch, ch);
/*      */           } else {
/* 4638 */             char uch = Character.toUpperCase((char)ch);
/* 4639 */             uppers.addRange(uch, uch);
/*      */           } 
/*      */         } 
/*      */       } 
/* 4643 */       RangeToken lowers = (this.type == 4) ? RegEx.Token.createRange() : RegEx.Token.createNRange();
/* 4644 */       for (int j = 0; j < uppers.ranges.length; j += 2) {
/* 4645 */         for (int ch = uppers.ranges[j]; ch <= uppers.ranges[j + 1]; ch++) {
/* 4646 */           if (ch > 65535) {
/* 4647 */             lowers.addRange(ch, ch);
/*      */           } else {
/* 4649 */             char uch = Character.toUpperCase((char)ch);
/* 4650 */             lowers.addRange(uch, uch);
/*      */           } 
/*      */         } 
/*      */       } 
/* 4654 */       lowers.mergeRanges(uppers);
/* 4655 */       lowers.mergeRanges(this);
/* 4656 */       lowers.compactRanges();
/*      */       
/* 4658 */       this.icaseCache = lowers;
/* 4659 */       return lowers;
/*      */     }
/*      */     
/*      */     void dumpRanges() {
/* 4663 */       System.err.print("RANGE: ");
/* 4664 */       if (this.ranges == null) {
/* 4665 */         System.err.println(" NULL");
/*      */         
/*      */         return;
/*      */       } 
/* 4669 */       for (int i = 0; i < this.ranges.length; i += 2) {
/* 4670 */         System.err.print("[" + this.ranges[i] + "," + this.ranges[i + 1] + "] ");
/*      */       }
/* 4672 */       System.err.println("");
/*      */     }
/*      */     
/*      */     boolean match(int ch) {
/*      */       boolean ret;
/* 4677 */       if (this.map == null) createMap();
/*      */       
/* 4679 */       if (this.type == 4) {
/* 4680 */         if (ch < 256)
/* 4681 */           return ((this.map[ch / 32] & 1 << (ch & 0x1F)) != 0); 
/* 4682 */         ret = false;
/* 4683 */         for (int i = this.nonMapIndex; i < this.ranges.length; i += 2) {
/* 4684 */           if (this.ranges[i] <= ch && ch <= this.ranges[i + 1])
/* 4685 */             return true; 
/*      */         } 
/*      */       } else {
/* 4688 */         if (ch < 256)
/* 4689 */           return ((this.map[ch / 32] & 1 << (ch & 0x1F)) == 0); 
/* 4690 */         ret = true;
/* 4691 */         for (int i = this.nonMapIndex; i < this.ranges.length; i += 2) {
/* 4692 */           if (this.ranges[i] <= ch && ch <= this.ranges[i + 1])
/* 4693 */             return false; 
/*      */         } 
/*      */       } 
/* 4696 */       return ret;
/*      */     }
/*      */ 
/*      */     
/*      */     private void createMap() {
/* 4701 */       int asize = 8;
/* 4702 */       this.map = new int[asize];
/* 4703 */       this.nonMapIndex = this.ranges.length; int i;
/* 4704 */       for (i = 0; i < asize; ) { this.map[i] = 0; i++; }
/* 4705 */        for (i = 0; i < this.ranges.length; i += 2) {
/* 4706 */         int s = this.ranges[i];
/* 4707 */         int e = this.ranges[i + 1];
/* 4708 */         if (s < 256) {
/* 4709 */           for (int j = s; j <= e && j < 256; j++)
/* 4710 */             this.map[j / 32] = this.map[j / 32] | 1 << (j & 0x1F); 
/*      */         } else {
/* 4712 */           this.nonMapIndex = i;
/*      */           break;
/*      */         } 
/* 4715 */         if (e >= 256) {
/* 4716 */           this.nonMapIndex = i;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString(int options) {
/*      */       String ret;
/* 4726 */       if (this.type == 4) {
/* 4727 */         if (this == RegEx.Token.token_dot) {
/* 4728 */           ret = ".";
/* 4729 */         } else if (this == RegEx.Token.token_0to9) {
/* 4730 */           ret = "\\d";
/* 4731 */         } else if (this == RegEx.Token.token_wordchars) {
/* 4732 */           ret = "\\w";
/* 4733 */         } else if (this == RegEx.Token.token_spaces) {
/* 4734 */           ret = "\\s";
/*      */         } else {
/* 4736 */           StringBuffer sb = new StringBuffer();
/* 4737 */           sb.append("[");
/* 4738 */           for (int i = 0; i < this.ranges.length; i += 2) {
/* 4739 */             if ((options & 0x400) != 0 && i > 0) sb.append(","); 
/* 4740 */             if (this.ranges[i] == this.ranges[i + 1]) {
/* 4741 */               sb.append(escapeCharInCharClass(this.ranges[i]));
/*      */             } else {
/* 4743 */               sb.append(escapeCharInCharClass(this.ranges[i]));
/* 4744 */               sb.append('-');
/* 4745 */               sb.append(escapeCharInCharClass(this.ranges[i + 1]));
/*      */             } 
/*      */           } 
/* 4748 */           sb.append("]");
/* 4749 */           ret = sb.toString();
/*      */         }
/*      */       
/* 4752 */       } else if (this == RegEx.Token.token_not_0to9) {
/* 4753 */         ret = "\\D";
/* 4754 */       } else if (this == RegEx.Token.token_not_wordchars) {
/* 4755 */         ret = "\\W";
/* 4756 */       } else if (this == RegEx.Token.token_not_spaces) {
/* 4757 */         ret = "\\S";
/*      */       } else {
/* 4759 */         StringBuffer sb = new StringBuffer();
/* 4760 */         sb.append("[^");
/* 4761 */         for (int i = 0; i < this.ranges.length; i += 2) {
/* 4762 */           if ((options & 0x400) != 0 && i > 0) sb.append(","); 
/* 4763 */           if (this.ranges[i] == this.ranges[i + 1]) {
/* 4764 */             sb.append(escapeCharInCharClass(this.ranges[i]));
/*      */           } else {
/* 4766 */             sb.append(escapeCharInCharClass(this.ranges[i]));
/* 4767 */             sb.append('-');
/* 4768 */             sb.append(escapeCharInCharClass(this.ranges[i + 1]));
/*      */           } 
/*      */         } 
/* 4771 */         sb.append("]");
/* 4772 */         ret = sb.toString();
/*      */       } 
/*      */       
/* 4775 */       return ret;
/*      */     }
/*      */     private static String escapeCharInCharClass(int ch) {
/*      */       String ret;
/*      */       char c;
/* 4780 */       switch (ch) { case 44: case 45: case 91: case 92:
/*      */         case 93:
/*      */         case 94:
/* 4783 */           ret = "\\" + (char)ch;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4801 */           return ret;case 12: ret = "\\f"; return ret;case 10: ret = "\\n"; return ret;case 13: ret = "\\r"; return ret;case 9: ret = "\\t"; return ret;case 27: ret = "\\e"; return ret; }  if (ch < 32) { String pre = "0" + Integer.toHexString(ch); ret = "\\x" + pre.substring(pre.length() - 2, pre.length()); } else if (ch >= 65536) { String pre = "0" + Integer.toHexString(ch); ret = "\\v" + pre.substring(pre.length() - 6, pre.length()); } else { c = (char)ch; }  return c;
/*      */     } }
/*      */   static class RegexParser { static final int T_CHAR = 0; static final int T_EOF = 1; static final int T_OR = 2; static final int T_STAR = 3; static final int T_PLUS = 4; static final int T_QUESTION = 5; static final int T_LPAREN = 6;
/*      */     static final int T_RPAREN = 7;
/*      */     static final int T_DOT = 8;
/*      */     static final int T_LBRACKET = 9;
/*      */     static final int T_BACKSOLIDUS = 10;
/*      */     static final int T_CARET = 11;
/*      */     static final int T_DOLLAR = 12;
/*      */     static final int T_LPAREN2 = 13;
/*      */     static final int T_LOOKAHEAD = 14;
/*      */     static final int T_NEGATIVELOOKAHEAD = 15;
/*      */     static final int T_LOOKBEHIND = 16;
/*      */     static final int T_NEGATIVELOOKBEHIND = 17;
/*      */     static final int T_INDEPENDENT = 18;
/*      */     static final int T_SET_OPERATIONS = 19;
/*      */     static final int T_POSIX_CHARCLASS_START = 20;
/*      */     static final int T_COMMENT = 21;
/*      */     static final int T_MODIFIERS = 22;
/*      */     static final int T_CONDITION = 23;
/*      */     static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
/*      */     int offset;
/*      */     String regex;
/*      */     int regexlen;
/*      */     int options;
/*      */     ResourceBundle resources;
/*      */     int chardata;
/*      */     int nexttoken;
/*      */     protected static final int S_NORMAL = 0;
/*      */     protected static final int S_INBRACKETS = 1;
/*      */     protected static final int S_INXBRACKETS = 2;
/*      */     
/*      */     static class ReferencePosition { int refNumber;
/*      */       int position;
/*      */       
/*      */       ReferencePosition(int n, int pos) {
/* 4837 */         this.refNumber = n;
/* 4838 */         this.position = pos;
/*      */       } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4852 */     int context = 0;
/* 4853 */     int parennumber = 1;
/*      */     boolean hasBackReferences;
/* 4855 */     Vector<ReferencePosition> references = null;
/*      */ 
/*      */ 
/*      */     
/*      */     public RegexParser() {}
/*      */ 
/*      */     
/*      */     public RegexParser(Locale locale) {}
/*      */ 
/*      */     
/*      */     public void setLocale(Locale locale) {}
/*      */ 
/*      */     
/*      */     final RegEx.ParseException ex(String key, int loc) {
/* 4869 */       return new RegEx.ParseException(EcorePlugin.INSTANCE.getString(key), loc);
/*      */     }
/*      */     
/*      */     private final boolean isSet(int flag) {
/* 4873 */       return ((this.options & flag) == flag);
/*      */     }
/*      */     
/*      */     synchronized RegEx.Token parse(String regex, int options) throws RegEx.ParseException {
/* 4877 */       this.options = options;
/* 4878 */       this.offset = 0;
/* 4879 */       setContext(0);
/* 4880 */       this.parennumber = 1;
/* 4881 */       this.hasBackReferences = false;
/* 4882 */       this.regex = regex;
/* 4883 */       if (isSet(16))
/* 4884 */         this.regex = RegEx.REUtil.stripExtendedComment(this.regex); 
/* 4885 */       this.regexlen = this.regex.length();
/*      */ 
/*      */       
/* 4888 */       next();
/* 4889 */       RegEx.Token ret = parseRegex();
/* 4890 */       if (this.offset != this.regexlen)
/* 4891 */         throw ex("parser.parse.1", this.offset); 
/* 4892 */       if (this.references != null) {
/* 4893 */         for (int i = 0; i < this.references.size(); i++) {
/* 4894 */           ReferencePosition position = this.references.elementAt(i);
/* 4895 */           if (this.parennumber <= position.refNumber)
/* 4896 */             throw ex("parser.parse.2", position.position); 
/*      */         } 
/* 4898 */         this.references.removeAllElements();
/*      */       } 
/* 4900 */       return ret;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected final void setContext(int con) {
/* 4911 */       this.context = con;
/*      */     }
/*      */     
/*      */     final int read() {
/* 4915 */       return this.nexttoken;
/*      */     }
/*      */     final void next() {
/*      */       int ret;
/* 4919 */       if (this.offset >= this.regexlen) {
/* 4920 */         this.chardata = -1;
/* 4921 */         this.nexttoken = 1;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 4926 */       int ch = this.regex.charAt(this.offset++);
/* 4927 */       this.chardata = ch;
/*      */       
/* 4929 */       if (this.context == 1) {
/*      */         int i;
/*      */         
/* 4932 */         switch (ch) {
/*      */           case 92:
/* 4934 */             i = 10;
/* 4935 */             if (this.offset >= this.regexlen)
/* 4936 */               throw ex("parser.next.1", this.offset - 1); 
/* 4937 */             this.chardata = this.regex.charAt(this.offset++);
/*      */             break;
/*      */           
/*      */           case 45:
/* 4941 */             if (isSet(512) && 
/* 4942 */               this.offset < this.regexlen && this.regex.charAt(this.offset) == '[') {
/* 4943 */               this.offset++;
/* 4944 */               i = 24; break;
/*      */             } 
/* 4946 */             i = 0;
/*      */             break;
/*      */           
/*      */           case 91:
/* 4950 */             if (!isSet(512) && 
/* 4951 */               this.offset < this.regexlen && this.regex.charAt(this.offset) == ':') {
/* 4952 */               this.offset++;
/* 4953 */               i = 20;
/*      */               break;
/*      */             } 
/*      */           default:
/* 4957 */             if (RegEx.REUtil.isHighSurrogate(ch) && this.offset < this.regexlen) {
/* 4958 */               int low = this.regex.charAt(this.offset);
/* 4959 */               if (RegEx.REUtil.isLowSurrogate(low)) {
/* 4960 */                 this.chardata = RegEx.REUtil.composeFromSurrogates(ch, low);
/* 4961 */                 this.offset++;
/*      */               } 
/*      */             } 
/* 4964 */             i = 0; break;
/*      */         } 
/* 4966 */         this.nexttoken = i;
/*      */         
/*      */         return;
/*      */       } 
/* 4970 */       switch (ch) { case 124:
/* 4971 */           ret = 2; break;
/* 4972 */         case 42: ret = 3; break;
/* 4973 */         case 43: ret = 4; break;
/* 4974 */         case 63: ret = 5; break;
/* 4975 */         case 41: ret = 7; break;
/* 4976 */         case 46: ret = 8; break;
/* 4977 */         case 91: ret = 9; break;
/* 4978 */         case 94: ret = 11; break;
/* 4979 */         case 36: ret = 12; break;
/*      */         case 40:
/* 4981 */           ret = 6;
/* 4982 */           if (this.offset >= this.regexlen)
/*      */             break; 
/* 4984 */           if (this.regex.charAt(this.offset) != '?')
/*      */             break; 
/* 4986 */           if (++this.offset >= this.regexlen)
/* 4987 */             throw ex("parser.next.2", this.offset - 1); 
/* 4988 */           ch = this.regex.charAt(this.offset++);
/* 4989 */           switch (ch) { case 58:
/* 4990 */               ret = 13; break;
/* 4991 */             case 61: ret = 14; break;
/* 4992 */             case 33: ret = 15; break;
/* 4993 */             case 91: ret = 19; break;
/* 4994 */             case 62: ret = 18; break;
/*      */             case 60:
/* 4996 */               if (this.offset >= this.regexlen)
/* 4997 */                 throw ex("parser.next.2", this.offset - 3); 
/* 4998 */               ch = this.regex.charAt(this.offset++);
/* 4999 */               if (ch == 61) {
/* 5000 */                 ret = 16; break;
/* 5001 */               }  if (ch == 33) {
/* 5002 */                 ret = 17; break;
/*      */               } 
/* 5004 */               throw ex("parser.next.3", this.offset - 3);
/*      */             
/*      */             case 35:
/* 5007 */               while (this.offset < this.regexlen) {
/* 5008 */                 ch = this.regex.charAt(this.offset++);
/* 5009 */                 if (ch == 41)
/*      */                   break; 
/* 5011 */               }  if (ch != 41)
/* 5012 */                 throw ex("parser.next.4", this.offset - 1); 
/* 5013 */               ret = 21;
/*      */               break; }
/*      */           
/* 5016 */           if (ch == 45 || (97 <= ch && ch <= 122) || (65 <= ch && ch <= 90)) {
/* 5017 */             this.offset--;
/* 5018 */             ret = 22; break;
/*      */           } 
/* 5020 */           if (ch == 40) {
/* 5021 */             ret = 23;
/*      */             break;
/*      */           } 
/* 5024 */           throw ex("parser.next.2", this.offset - 2);
/*      */ 
/*      */ 
/*      */         
/*      */         case 92:
/* 5029 */           ret = 10;
/* 5030 */           if (this.offset >= this.regexlen)
/* 5031 */             throw ex("parser.next.1", this.offset - 1); 
/* 5032 */           this.chardata = this.regex.charAt(this.offset++);
/*      */           break;
/*      */         
/*      */         default:
/* 5036 */           ret = 0; break; }
/*      */       
/* 5038 */       this.nexttoken = ret;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token parseRegex() throws RegEx.ParseException {
/* 5051 */       RegEx.Token tok = parseTerm();
/* 5052 */       RegEx.Token parent = null;
/* 5053 */       while (read() == 2) {
/* 5054 */         next();
/* 5055 */         if (parent == null) {
/* 5056 */           parent = RegEx.Token.createUnion();
/* 5057 */           parent.addChild(tok);
/* 5058 */           tok = parent;
/*      */         } 
/* 5060 */         tok.addChild(parseTerm());
/*      */       } 
/* 5062 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token parseTerm() throws RegEx.ParseException {
/* 5069 */       int ch = read();
/* 5070 */       if (ch == 2 || ch == 7 || ch == 1) {
/* 5071 */         return RegEx.Token.createEmpty();
/*      */       }
/* 5073 */       RegEx.Token tok = parseFactor();
/* 5074 */       RegEx.Token concat = null;
/* 5075 */       while ((ch = read()) != 2 && ch != 7 && ch != 1) {
/* 5076 */         if (concat == null) {
/* 5077 */           concat = RegEx.Token.createConcat();
/* 5078 */           concat.addChild(tok);
/* 5079 */           tok = concat;
/*      */         } 
/* 5081 */         concat.addChild(parseFactor());
/*      */       } 
/*      */       
/* 5084 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processCaret() throws RegEx.ParseException {
/* 5091 */       next();
/* 5092 */       return RegEx.Token.token_linebeginning;
/*      */     }
/*      */     RegEx.Token processDollar() throws RegEx.ParseException {
/* 5095 */       next();
/* 5096 */       return RegEx.Token.token_lineend;
/*      */     }
/*      */     RegEx.Token processLookahead() throws RegEx.ParseException {
/* 5099 */       next();
/* 5100 */       RegEx.Token tok = RegEx.Token.createLook(20, parseRegex());
/* 5101 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5102 */       next();
/* 5103 */       return tok;
/*      */     }
/*      */     RegEx.Token processNegativelookahead() throws RegEx.ParseException {
/* 5106 */       next();
/* 5107 */       RegEx.Token tok = RegEx.Token.createLook(21, parseRegex());
/* 5108 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5109 */       next();
/* 5110 */       return tok;
/*      */     }
/*      */     RegEx.Token processLookbehind() throws RegEx.ParseException {
/* 5113 */       next();
/* 5114 */       RegEx.Token tok = RegEx.Token.createLook(22, parseRegex());
/* 5115 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5116 */       next();
/* 5117 */       return tok;
/*      */     }
/*      */     RegEx.Token processNegativelookbehind() throws RegEx.ParseException {
/* 5120 */       next();
/* 5121 */       RegEx.Token tok = RegEx.Token.createLook(23, parseRegex());
/* 5122 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5123 */       next();
/* 5124 */       return tok;
/*      */     }
/*      */     RegEx.Token processBacksolidus_A() throws RegEx.ParseException {
/* 5127 */       next();
/* 5128 */       return RegEx.Token.token_stringbeginning;
/*      */     }
/*      */     RegEx.Token processBacksolidus_Z() throws RegEx.ParseException {
/* 5131 */       next();
/* 5132 */       return RegEx.Token.token_stringend2;
/*      */     }
/*      */     RegEx.Token processBacksolidus_z() throws RegEx.ParseException {
/* 5135 */       next();
/* 5136 */       return RegEx.Token.token_stringend;
/*      */     }
/*      */     RegEx.Token processBacksolidus_b() throws RegEx.ParseException {
/* 5139 */       next();
/* 5140 */       return RegEx.Token.token_wordedge;
/*      */     }
/*      */     RegEx.Token processBacksolidus_B() throws RegEx.ParseException {
/* 5143 */       next();
/* 5144 */       return RegEx.Token.token_not_wordedge;
/*      */     }
/*      */     RegEx.Token processBacksolidus_lt() throws RegEx.ParseException {
/* 5147 */       next();
/* 5148 */       return RegEx.Token.token_wordbeginning;
/*      */     }
/*      */     RegEx.Token processBacksolidus_gt() throws RegEx.ParseException {
/* 5151 */       next();
/* 5152 */       return RegEx.Token.token_wordend;
/*      */     }
/*      */     RegEx.Token processStar(RegEx.Token tok) throws RegEx.ParseException {
/* 5155 */       next();
/* 5156 */       if (read() == 5) {
/* 5157 */         next();
/* 5158 */         return RegEx.Token.createNGClosure(tok);
/*      */       } 
/* 5160 */       return RegEx.Token.createClosure(tok);
/*      */     }
/*      */     
/*      */     RegEx.Token processPlus(RegEx.Token tok) throws RegEx.ParseException {
/* 5164 */       next();
/* 5165 */       if (read() == 5) {
/* 5166 */         next();
/* 5167 */         return RegEx.Token.createConcat(tok, RegEx.Token.createNGClosure(tok));
/*      */       } 
/* 5169 */       return RegEx.Token.createConcat(tok, RegEx.Token.createClosure(tok));
/*      */     }
/*      */     
/*      */     RegEx.Token processQuestion(RegEx.Token tok) throws RegEx.ParseException {
/* 5173 */       next();
/* 5174 */       RegEx.Token par = RegEx.Token.createUnion();
/* 5175 */       if (read() == 5) {
/* 5176 */         next();
/* 5177 */         par.addChild(RegEx.Token.createEmpty());
/* 5178 */         par.addChild(tok);
/*      */       } else {
/* 5180 */         par.addChild(tok);
/* 5181 */         par.addChild(RegEx.Token.createEmpty());
/*      */       } 
/* 5183 */       return par;
/*      */     }
/*      */     boolean checkQuestion(int off) {
/* 5186 */       return (off < this.regexlen && this.regex.charAt(off) == '?');
/*      */     }
/*      */     RegEx.Token processParen() throws RegEx.ParseException {
/* 5189 */       next();
/* 5190 */       int p = this.parennumber++;
/* 5191 */       RegEx.Token tok = RegEx.Token.createParen(parseRegex(), p);
/* 5192 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5193 */       next();
/* 5194 */       return tok;
/*      */     }
/*      */     RegEx.Token processParen2() throws RegEx.ParseException {
/* 5197 */       next();
/* 5198 */       RegEx.Token tok = RegEx.Token.createParen(parseRegex(), 0);
/* 5199 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5200 */       next();
/* 5201 */       return tok;
/*      */     }
/*      */     
/*      */     RegEx.Token processCondition() throws RegEx.ParseException {
/* 5205 */       if (this.offset + 1 >= this.regexlen) throw ex("parser.factor.4", this.offset);
/*      */       
/* 5207 */       int refno = -1;
/* 5208 */       RegEx.Token condition = null;
/* 5209 */       int ch = this.regex.charAt(this.offset);
/* 5210 */       if (49 <= ch && ch <= 57) {
/* 5211 */         refno = ch - 48;
/* 5212 */         this.hasBackReferences = true;
/* 5213 */         if (this.references == null) this.references = new Vector<ReferencePosition>(); 
/* 5214 */         this.references.addElement(new ReferencePosition(refno, this.offset));
/* 5215 */         this.offset++;
/* 5216 */         if (this.regex.charAt(this.offset) != ')') throw ex("parser.factor.1", this.offset); 
/* 5217 */         this.offset++;
/*      */       } else {
/* 5219 */         if (ch == 63) this.offset--; 
/* 5220 */         next();
/* 5221 */         condition = parseFactor();
/* 5222 */         switch (condition.type) {
/*      */           case 20:
/*      */           case 21:
/*      */           case 22:
/*      */           case 23:
/*      */             break;
/*      */           case 8:
/* 5229 */             if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/*      */             break;
/*      */           default:
/* 5232 */             throw ex("parser.factor.5", this.offset);
/*      */         } 
/*      */       
/*      */       } 
/* 5236 */       next();
/* 5237 */       RegEx.Token yesPattern = parseRegex();
/* 5238 */       RegEx.Token noPattern = null;
/* 5239 */       if (yesPattern.type == 2) {
/* 5240 */         if (yesPattern.size() != 2) throw ex("parser.factor.6", this.offset); 
/* 5241 */         noPattern = yesPattern.getChild(1);
/* 5242 */         yesPattern = yesPattern.getChild(0);
/*      */       } 
/* 5244 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5245 */       next();
/* 5246 */       return RegEx.Token.createCondition(refno, condition, yesPattern, noPattern);
/*      */     }
/*      */     
/*      */     RegEx.Token processModifiers() throws RegEx.ParseException {
/*      */       RegEx.Token tok;
/* 5251 */       int add = 0, mask = 0, ch = -1;
/* 5252 */       while (this.offset < this.regexlen) {
/* 5253 */         ch = this.regex.charAt(this.offset);
/* 5254 */         int v = RegEx.REUtil.getOptionValue(ch);
/* 5255 */         if (v == 0)
/* 5256 */           break;  add |= v;
/* 5257 */         this.offset++;
/*      */       } 
/* 5259 */       if (this.offset >= this.regexlen) throw ex("parser.factor.2", this.offset - 1); 
/* 5260 */       if (ch == 45) {
/* 5261 */         this.offset++;
/* 5262 */         while (this.offset < this.regexlen) {
/* 5263 */           ch = this.regex.charAt(this.offset);
/* 5264 */           int v = RegEx.REUtil.getOptionValue(ch);
/* 5265 */           if (v == 0)
/* 5266 */             break;  mask |= v;
/* 5267 */           this.offset++;
/*      */         } 
/* 5269 */         if (this.offset >= this.regexlen) throw ex("parser.factor.2", this.offset - 1);
/*      */       
/*      */       } 
/* 5272 */       if (ch == 58) {
/* 5273 */         this.offset++;
/* 5274 */         next();
/* 5275 */         tok = RegEx.Token.createModifierGroup(parseRegex(), add, mask);
/* 5276 */         if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5277 */         next();
/* 5278 */       } else if (ch == 41) {
/* 5279 */         this.offset++;
/* 5280 */         next();
/* 5281 */         tok = RegEx.Token.createModifierGroup(parseRegex(), add, mask);
/*      */       } else {
/* 5283 */         throw ex("parser.factor.3", this.offset);
/*      */       } 
/* 5285 */       return tok;
/*      */     }
/*      */     RegEx.Token processIndependent() throws RegEx.ParseException {
/* 5288 */       next();
/* 5289 */       RegEx.Token tok = RegEx.Token.createLook(24, parseRegex());
/* 5290 */       if (read() != 7) throw ex("parser.factor.1", this.offset - 1); 
/* 5291 */       next();
/* 5292 */       return tok;
/*      */     }
/*      */     RegEx.Token processBacksolidus_c() throws RegEx.ParseException {
/*      */       int ch2;
/* 5296 */       if (this.offset >= this.regexlen || ((
/* 5297 */         ch2 = this.regex.charAt(this.offset++)) & 0xFFE0) != 64)
/* 5298 */         throw ex("parser.atom.1", this.offset - 1); 
/* 5299 */       next();
/* 5300 */       return RegEx.Token.createChar(ch2 - 64);
/*      */     }
/*      */     RegEx.Token processBacksolidus_C() throws RegEx.ParseException {
/* 5303 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */     RegEx.Token processBacksolidus_i() throws RegEx.ParseException {
/* 5306 */       RegEx.Token tok = RegEx.Token.createChar(105);
/* 5307 */       next();
/* 5308 */       return tok;
/*      */     }
/*      */     RegEx.Token processBacksolidus_I() throws RegEx.ParseException {
/* 5311 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */     RegEx.Token processBacksolidus_g() throws RegEx.ParseException {
/* 5314 */       next();
/* 5315 */       return RegEx.Token.getGraphemePattern();
/*      */     }
/*      */     RegEx.Token processBacksolidus_X() throws RegEx.ParseException {
/* 5318 */       next();
/* 5319 */       return RegEx.Token.getCombiningCharacterSequence();
/*      */     }
/*      */     RegEx.Token processBackreference() throws RegEx.ParseException {
/* 5322 */       int refnum = this.chardata - 48;
/* 5323 */       RegEx.Token tok = RegEx.Token.createBackReference(refnum);
/* 5324 */       this.hasBackReferences = true;
/* 5325 */       if (this.references == null) this.references = new Vector<ReferencePosition>(); 
/* 5326 */       this.references.addElement(new ReferencePosition(refnum, this.offset - 2));
/* 5327 */       next();
/* 5328 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token parseFactor() throws RegEx.ParseException {
/* 5343 */       int ch = read();
/*      */       
/* 5345 */       switch (ch) { case 11:
/* 5346 */           return processCaret();
/* 5347 */         case 12: return processDollar();
/* 5348 */         case 14: return processLookahead();
/* 5349 */         case 15: return processNegativelookahead();
/* 5350 */         case 16: return processLookbehind();
/* 5351 */         case 17: return processNegativelookbehind();
/*      */         
/*      */         case 21:
/* 5354 */           next();
/* 5355 */           return RegEx.Token.createEmpty();
/*      */         
/*      */         case 10:
/* 5358 */           switch (this.chardata) { case 65:
/* 5359 */               return processBacksolidus_A();
/* 5360 */             case 90: return processBacksolidus_Z();
/* 5361 */             case 122: return processBacksolidus_z();
/* 5362 */             case 98: return processBacksolidus_b();
/* 5363 */             case 66: return processBacksolidus_B();
/* 5364 */             case 60: return processBacksolidus_lt();
/* 5365 */             case 62: return processBacksolidus_gt(); }
/*      */           
/*      */           break; }
/*      */       
/* 5369 */       RegEx.Token tok = parseAtom();
/* 5370 */       ch = read();
/* 5371 */       switch (ch) { case 3:
/* 5372 */           return processStar(tok);
/* 5373 */         case 4: return processPlus(tok);
/* 5374 */         case 5: return processQuestion(tok);
/*      */         case 0:
/* 5376 */           if (this.chardata == 123 && this.offset < this.regexlen) {
/*      */             
/* 5378 */             int off = this.offset;
/* 5379 */             int min = 0, max = -1;
/*      */             
/* 5381 */             if ((ch = this.regex.charAt(off++)) >= 48 && ch <= 57) {
/*      */               
/* 5383 */               min = ch - 48;
/* 5384 */               while (off < this.regexlen && (
/* 5385 */                 ch = this.regex.charAt(off++)) >= 48 && ch <= 57) {
/* 5386 */                 min = min * 10 + ch - 48;
/* 5387 */                 if (min < 0) {
/* 5388 */                   throw ex("parser.quantifier.5", this.offset);
/*      */                 }
/*      */               } 
/*      */             } else {
/* 5392 */               throw ex("parser.quantifier.1", this.offset);
/*      */             } 
/*      */             
/* 5395 */             max = min;
/* 5396 */             if (ch == 44) {
/*      */               
/* 5398 */               if (off >= this.regexlen) {
/* 5399 */                 throw ex("parser.quantifier.3", this.offset);
/*      */               }
/* 5401 */               if ((ch = this.regex.charAt(off++)) >= 48 && ch <= 57) {
/*      */                 
/* 5403 */                 max = ch - 48;
/* 5404 */                 while (off < this.regexlen && (
/* 5405 */                   ch = this.regex.charAt(off++)) >= 48 && 
/* 5406 */                   ch <= 57) {
/* 5407 */                   max = max * 10 + ch - 48;
/* 5408 */                   if (max < 0) {
/* 5409 */                     throw ex("parser.quantifier.5", this.offset);
/*      */                   }
/*      */                 } 
/* 5412 */                 if (min > max) {
/* 5413 */                   throw ex("parser.quantifier.4", this.offset);
/*      */                 }
/*      */               } else {
/* 5416 */                 max = -1;
/*      */               } 
/*      */             } 
/*      */             
/* 5420 */             if (ch != 125) {
/* 5421 */               throw ex("parser.quantifier.2", this.offset);
/*      */             }
/* 5423 */             if (checkQuestion(off)) {
/* 5424 */               tok = RegEx.Token.createNGClosure(tok);
/* 5425 */               this.offset = off + 1;
/*      */             } else {
/* 5427 */               tok = RegEx.Token.createClosure(tok);
/* 5428 */               this.offset = off;
/*      */             } 
/*      */             
/* 5431 */             tok.setMin(min);
/* 5432 */             tok.setMax(max);
/*      */             
/* 5434 */             next();
/*      */           }  break; }
/*      */       
/* 5437 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token parseAtom() throws RegEx.ParseException {
/* 5447 */       int ch2, pstart, high, ch = read();
/* 5448 */       RegEx.Token tok = null;
/* 5449 */       switch (ch) { case 6:
/* 5450 */           return processParen();
/* 5451 */         case 13: return processParen2();
/* 5452 */         case 23: return processCondition();
/* 5453 */         case 22: return processModifiers();
/* 5454 */         case 18: return processIndependent();
/*      */         case 8:
/* 5456 */           next();
/* 5457 */           tok = RegEx.Token.token_dot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5533 */           return tok;case 9: return parseCharacterClass(true);case 19: return parseSetOperations();case 10: switch (this.chardata) { case 68: case 83: case 87: case 100: case 115: case 119: tok = getTokenForShorthand(this.chardata); next(); return tok;case 101: case 102: case 110: case 114: case 116: case 117: case 118: case 120: ch2 = decodeEscaped(); if (ch2 < 65536) { tok = RegEx.Token.createChar(ch2); break; }  tok = RegEx.Token.createString(RegEx.REUtil.decomposeToSurrogates(ch2)); break;case 99: return processBacksolidus_c();case 67: return processBacksolidus_C();case 105: return processBacksolidus_i();case 73: return processBacksolidus_I();case 103: return processBacksolidus_g();case 88: return processBacksolidus_X();case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: return processBackreference();case 80: case 112: pstart = this.offset; tok = processBacksolidus_pP(this.chardata); if (tok == null) throw ex("parser.atom.5", pstart);  break;default: tok = RegEx.Token.createChar(this.chardata); break; }  next(); return tok;case 0: if (this.chardata == 93 || this.chardata == 123 || this.chardata == 125) throw ex("parser.atom.4", this.offset - 1);  tok = RegEx.Token.createChar(this.chardata); high = this.chardata; next(); if (RegEx.REUtil.isHighSurrogate(high) && read() == 0 && RegEx.REUtil.isLowSurrogate(this.chardata)) { char[] sur = new char[2]; sur[0] = (char)high; sur[1] = (char)this.chardata; tok = RegEx.Token.createParen(RegEx.Token.createString(new String(sur)), 0); next(); }  return tok; }
/*      */       
/*      */       throw ex("parser.atom.4", this.offset - 1);
/*      */     }
/*      */     protected RegEx.RangeToken processBacksolidus_pP(int c) throws RegEx.ParseException {
/* 5538 */       next();
/* 5539 */       if (read() != 0 || this.chardata != 123) {
/* 5540 */         throw ex("parser.atom.2", this.offset - 1);
/*      */       }
/*      */       
/* 5543 */       boolean positive = (c == 112);
/* 5544 */       int namestart = this.offset;
/* 5545 */       int nameend = this.regex.indexOf('}', namestart);
/*      */       
/* 5547 */       if (nameend < 0) {
/* 5548 */         throw ex("parser.atom.3", this.offset);
/*      */       }
/* 5550 */       String pname = this.regex.substring(namestart, nameend);
/* 5551 */       this.offset = nameend + 1;
/*      */       
/* 5553 */       return RegEx.Token.getRange(pname, positive, isSet(512));
/*      */     }
/*      */     
/*      */     int processCIinCharacterClass(RegEx.RangeToken tok, int c) {
/* 5557 */       return decodeEscaped();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected RegEx.RangeToken parseCharacterClass(boolean useNrange) throws RegEx.ParseException {
/*      */       RegEx.RangeToken tok;
/* 5568 */       setContext(1);
/* 5569 */       next();
/* 5570 */       RegEx.RangeToken base = null;
/*      */       
/* 5572 */       if (read() == 0 && this.chardata == 94) {
/* 5573 */         next();
/* 5574 */         if (useNrange) {
/* 5575 */           tok = RegEx.Token.createNRange();
/*      */         } else {
/* 5577 */           base = RegEx.Token.createRange();
/* 5578 */           base.addRange(0, 1114111);
/* 5579 */           tok = RegEx.Token.createRange();
/*      */         } 
/*      */       } else {
/* 5582 */         tok = RegEx.Token.createRange();
/*      */       } 
/*      */       
/* 5585 */       boolean firstloop = true; int type;
/* 5586 */       while ((type = read()) != 1 && (
/* 5587 */         type != 0 || this.chardata != 93 || firstloop)) {
/*      */         
/* 5589 */         firstloop = false;
/* 5590 */         int c = this.chardata;
/* 5591 */         boolean end = false;
/* 5592 */         if (type == 10) {
/* 5593 */           int pstart; RegEx.RangeToken tok2; switch (c) { case 68: case 83: case 87:
/*      */             case 100:
/*      */             case 115:
/*      */             case 119:
/* 5597 */               tok.mergeRanges(getTokenForShorthand(c));
/* 5598 */               end = true; break;
/*      */             case 67:
/*      */             case 73:
/*      */             case 99:
/*      */             case 105:
/* 5603 */               c = processCIinCharacterClass(tok, c);
/* 5604 */               if (c < 0) end = true;
/*      */               
/*      */               break;
/*      */             case 80:
/*      */             case 112:
/* 5609 */               pstart = this.offset;
/* 5610 */               tok2 = processBacksolidus_pP(c);
/* 5611 */               if (tok2 == null) throw ex("parser.atom.5", pstart); 
/* 5612 */               tok.mergeRanges(tok2);
/* 5613 */               end = true;
/*      */               break;
/*      */             
/*      */             default:
/* 5617 */               c = decodeEscaped();
/*      */               break; }
/*      */ 
/*      */         
/* 5621 */         } else if (type == 20) {
/* 5622 */           int nameend = this.regex.indexOf(':', this.offset);
/* 5623 */           if (nameend < 0) throw ex("parser.cc.1", this.offset); 
/* 5624 */           boolean positive = true;
/* 5625 */           if (this.regex.charAt(this.offset) == '^') {
/* 5626 */             this.offset++;
/* 5627 */             positive = false;
/*      */           } 
/* 5629 */           String name = this.regex.substring(this.offset, nameend);
/* 5630 */           RegEx.RangeToken range = RegEx.Token.getRange(name, positive, 
/* 5631 */               isSet(512));
/* 5632 */           if (range == null) throw ex("parser.cc.3", this.offset); 
/* 5633 */           tok.mergeRanges(range);
/* 5634 */           end = true;
/* 5635 */           if (nameend + 1 >= this.regexlen || this.regex.charAt(nameend + 1) != ']')
/* 5636 */             throw ex("parser.cc.1", nameend); 
/* 5637 */           this.offset = nameend + 2;
/*      */         } 
/* 5639 */         next();
/* 5640 */         if (!end) {
/* 5641 */           if (read() != 0 || this.chardata != 45) {
/* 5642 */             tok.addRange(c, c);
/*      */           } else {
/* 5644 */             next();
/* 5645 */             if ((type = read()) == 1) throw ex("parser.cc.2", this.offset); 
/* 5646 */             if (type == 0 && this.chardata == 93) {
/* 5647 */               tok.addRange(c, c);
/* 5648 */               tok.addRange(45, 45);
/*      */             } else {
/* 5650 */               int rangeend = this.chardata;
/* 5651 */               if (type == 10)
/* 5652 */                 rangeend = decodeEscaped(); 
/* 5653 */               next();
/* 5654 */               tok.addRange(c, rangeend);
/*      */             } 
/*      */           } 
/*      */         }
/* 5658 */         if (isSet(1024) && 
/* 5659 */           read() == 0 && this.chardata == 44)
/* 5660 */           next(); 
/*      */       } 
/* 5662 */       if (read() == 1)
/* 5663 */         throw ex("parser.cc.2", this.offset); 
/* 5664 */       if (base != null) {
/* 5665 */         base.subtractRanges(tok);
/* 5666 */         tok = base;
/*      */       } 
/* 5668 */       tok.sortRanges();
/* 5669 */       tok.compactRanges();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5675 */       setContext(0);
/* 5676 */       next();
/*      */       
/* 5678 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected RegEx.RangeToken parseSetOperations() throws RegEx.ParseException {
/* 5685 */       RegEx.RangeToken tok = parseCharacterClass(false);
/*      */       int type;
/* 5687 */       while ((type = read()) != 7) {
/* 5688 */         int ch = this.chardata;
/* 5689 */         if ((type == 0 && (ch == 45 || ch == 38)) || 
/* 5690 */           type == 4) {
/* 5691 */           next();
/* 5692 */           if (read() != 9) throw ex("parser.ope.1", this.offset - 1); 
/* 5693 */           RegEx.RangeToken t2 = parseCharacterClass(false);
/* 5694 */           if (type == 4) {
/* 5695 */             tok.mergeRanges(t2); continue;
/* 5696 */           }  if (ch == 45) {
/* 5697 */             tok.subtractRanges(t2); continue;
/* 5698 */           }  if (ch == 38) {
/* 5699 */             tok.intersectRanges(t2); continue;
/*      */           } 
/* 5701 */           throw new RuntimeException("ASSERT");
/*      */         } 
/* 5703 */         throw ex("parser.ope.2", this.offset - 1);
/*      */       } 
/*      */       
/* 5706 */       next();
/* 5707 */       return tok;
/*      */     }
/*      */     
/*      */     RegEx.Token getTokenForShorthand(int ch) {
/*      */       RegEx.Token tok;
/* 5712 */       switch (ch) {
/*      */         case 100:
/* 5714 */           tok = isSet(32) ? 
/* 5715 */             RegEx.Token.getRange("Nd", true) : RegEx.Token.token_0to9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5741 */           return tok;case 68: tok = isSet(32) ? RegEx.Token.getRange("Nd", false) : RegEx.Token.token_not_0to9; return tok;case 119: tok = isSet(32) ? RegEx.Token.getRange("IsWord", true) : RegEx.Token.token_wordchars; return tok;case 87: tok = isSet(32) ? RegEx.Token.getRange("IsWord", false) : RegEx.Token.token_not_wordchars; return tok;case 115: tok = isSet(32) ? RegEx.Token.getRange("IsSpace", true) : RegEx.Token.token_spaces; return tok;case 83: tok = isSet(32) ? RegEx.Token.getRange("IsSpace", false) : RegEx.Token.token_not_spaces; return tok;
/*      */       } 
/*      */       throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
/*      */     }
/*      */     int decodeEscaped() throws RegEx.ParseException {
/*      */       int v1, uv;
/* 5747 */       if (read() != 10) throw ex("parser.next.1", this.offset - 1); 
/* 5748 */       int c = this.chardata;
/* 5749 */       switch (c) { case 101:
/* 5750 */           c = 27; break;
/* 5751 */         case 102: c = 12; break;
/* 5752 */         case 110: c = 10; break;
/* 5753 */         case 114: c = 13; break;
/* 5754 */         case 116: c = 9;
/*      */           break;
/*      */         case 120:
/* 5757 */           next();
/* 5758 */           if (read() != 0) throw ex("parser.descape.1", this.offset - 1); 
/* 5759 */           if (this.chardata == 123) {
/* 5760 */             int i = 0;
/* 5761 */             int j = 0;
/*      */             while (true) {
/* 5763 */               next();
/* 5764 */               if (read() != 0) throw ex("parser.descape.1", this.offset - 1); 
/* 5765 */               if ((i = hexChar(this.chardata)) < 0)
/*      */                 break; 
/* 5767 */               if (j > j * 16) throw ex("parser.descape.2", this.offset - 1); 
/* 5768 */               j = j * 16 + i;
/*      */             } 
/* 5770 */             if (this.chardata != 125) throw ex("parser.descape.3", this.offset - 1); 
/* 5771 */             if (j > 1114111) throw ex("parser.descape.4", this.offset - 1); 
/* 5772 */             c = j; break;
/*      */           } 
/* 5774 */           v1 = 0;
/* 5775 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5776 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5777 */           uv = v1;
/* 5778 */           next();
/* 5779 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5780 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5781 */           uv = uv * 16 + v1;
/* 5782 */           c = uv;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 117:
/* 5787 */           v1 = 0;
/* 5788 */           next();
/* 5789 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5790 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5791 */           uv = v1;
/* 5792 */           next();
/* 5793 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5794 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5795 */           uv = uv * 16 + v1;
/* 5796 */           next();
/* 5797 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5798 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5799 */           uv = uv * 16 + v1;
/* 5800 */           next();
/* 5801 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5802 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5803 */           uv = uv * 16 + v1;
/* 5804 */           c = uv;
/*      */           break;
/*      */         
/*      */         case 118:
/* 5808 */           next();
/* 5809 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5810 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5811 */           uv = v1;
/* 5812 */           next();
/* 5813 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5814 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5815 */           uv = uv * 16 + v1;
/* 5816 */           next();
/* 5817 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5818 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5819 */           uv = uv * 16 + v1;
/* 5820 */           next();
/* 5821 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5822 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5823 */           uv = uv * 16 + v1;
/* 5824 */           next();
/* 5825 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5826 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5827 */           uv = uv * 16 + v1;
/* 5828 */           next();
/* 5829 */           if (read() != 0 || (v1 = hexChar(this.chardata)) < 0)
/* 5830 */             throw ex("parser.descape.1", this.offset - 1); 
/* 5831 */           uv = uv * 16 + v1;
/* 5832 */           if (uv > 1114111) throw ex("parser.descappe.4", this.offset - 1); 
/* 5833 */           c = uv;
/*      */           break;
/*      */         case 65:
/*      */         case 90:
/*      */         case 122:
/* 5838 */           throw ex("parser.descape.5", this.offset - 2); }
/*      */ 
/*      */       
/* 5841 */       return c;
/*      */     }
/*      */     
/*      */     private static final int hexChar(int ch) {
/* 5845 */       if (ch < 48) return -1; 
/* 5846 */       if (ch > 102) return -1; 
/* 5847 */       if (ch <= 57) return ch - 48; 
/* 5848 */       if (ch < 65) return -1; 
/* 5849 */       if (ch <= 70) return ch - 65 + 10; 
/* 5850 */       if (ch < 97) return -1; 
/* 5851 */       return ch - 97 + 10;
/*      */     } }
/*      */ 
/*      */   
/*      */   static class Token
/*      */     implements Serializable {
/*      */     private static final long serialVersionUID = 1L;
/*      */     static final boolean COUNTTOKENS = true;
/* 5859 */     static int tokens = 0;
/*      */ 
/*      */     
/*      */     static final int CHAR = 0;
/*      */ 
/*      */     
/*      */     static final int DOT = 11;
/*      */ 
/*      */     
/*      */     static final int CONCAT = 1;
/*      */ 
/*      */     
/*      */     static final int UNION = 2;
/*      */ 
/*      */     
/*      */     static final int CLOSURE = 3;
/*      */ 
/*      */     
/*      */     static final int RANGE = 4;
/*      */ 
/*      */     
/*      */     static final int NRANGE = 5;
/*      */ 
/*      */     
/*      */     static final int PAREN = 6;
/*      */ 
/*      */     
/*      */     static final int EMPTY = 7;
/*      */ 
/*      */     
/*      */     static final int ANCHOR = 8;
/*      */ 
/*      */     
/*      */     static final int NONGREEDYCLOSURE = 9;
/*      */ 
/*      */     
/*      */     static final int STRING = 10;
/*      */ 
/*      */     
/*      */     static final int BACKREFERENCE = 12;
/*      */     
/*      */     static final int LOOKAHEAD = 20;
/*      */     
/*      */     static final int NEGATIVELOOKAHEAD = 21;
/*      */     
/*      */     static final int LOOKBEHIND = 22;
/*      */     
/*      */     static final int NEGATIVELOOKBEHIND = 23;
/*      */     
/*      */     static final int INDEPENDENT = 24;
/*      */     
/*      */     static final int MODIFIERGROUP = 25;
/*      */     
/*      */     static final int CONDITION = 26;
/*      */     
/*      */     static final int UTF16_MAX = 1114111;
/*      */     
/*      */     int type;
/*      */     
/* 5918 */     static Token token_dot = new Token(11);
/*      */     
/* 5920 */     static Token token_0to9 = createRange();
/*      */     
/* 5922 */     static Token token_wordchars = createRange();
/*      */     
/*      */     static Token token_not_0to9;
/*      */     
/*      */     static Token token_not_wordchars;
/* 5927 */     static Token token_spaces = createRange(); static Token token_not_spaces; static Token token_empty = new Token(7); static Token token_linebeginning = createAnchor(94); static Token token_linebeginning2 = createAnchor(64); static Token token_lineend = createAnchor(36); static Token token_stringbeginning = createAnchor(65); static Token token_stringend = createAnchor(122); static Token token_stringend2 = createAnchor(90); static Token token_wordedge = createAnchor(98); static Token token_not_wordedge = createAnchor(66); static Token token_wordbeginning = createAnchor(60); static Token token_wordend = createAnchor(62); static final int FC_CONTINUE = 0; static final int FC_TERMINAL = 1; static final int FC_ANY = 2; private static final Hashtable<String, Token> categories; private static final Hashtable<String, Token> categories2; private static final String[] categoryNames; static final int CHAR_INIT_QUOTE = 29; static final int CHAR_FINAL_QUOTE = 30; static final int CHAR_LETTER = 31; static final int CHAR_MARK = 32; static final int CHAR_NUMBER = 33; static final int CHAR_SEPARATOR = 34; static final int CHAR_OTHER = 35; static final int CHAR_PUNCTUATION = 36; static final int CHAR_SYMBOL = 37; private static final String[] blockNames; static final String blockRanges = "\000ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ԰֏֐׿؀ۿ܀ݏހ޿ऀॿঀ৿਀੿઀૿଀୿஀௿ఀ౿ಀ೿ഀൿ඀෿฀๿຀໿ༀ࿿က႟Ⴀჿᄀᇿሀ፿Ꭰ᏿᐀ᙿ ᚟ᚠ᛿ក៿᠀᢯Ḁỿἀ῿ ⁯⁰₟₠⃏⃐⃿℀⅏⅐↏←⇿∀⋿⌀⏿␀␿⑀⑟①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀⻿⼀⿟⿰⿿　〿぀ゟ゠ヿ㄀ㄯ㄰㆏㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一鿿ꀀ꒏꒐꓏가힣豈﫿ﬀﭏﭐ﷿︠︯︰﹏﹐﹯ﹰ﻾﻿﻿＀￯"; static final int[] nonBMPBlockRanges; private static final int NONBMP_BLOCK_START = 84; static Hashtable<String, String> nonxs; static final String viramaString = "्্੍્୍்్್്ฺ྄"; private static Token token_grapheme; private static Token token_ccs;
/* 5928 */     static { token_spaces.addRange(9, 9);
/* 5929 */       token_spaces.addRange(10, 10);
/* 5930 */       token_spaces.addRange(12, 12);
/* 5931 */       token_spaces.addRange(13, 13);
/* 5932 */       token_spaces.addRange(32, 32);
/*      */       
/* 5934 */       token_not_0to9 = complementRanges(token_0to9);
/* 5935 */       token_not_wordchars = complementRanges(token_wordchars);
/* 5936 */       token_not_spaces = complementRanges(token_spaces);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6429 */       categories = new Hashtable<String, Token>();
/* 6430 */       categories2 = new Hashtable<String, Token>();
/* 6431 */       categoryNames = new String[] { 
/* 6432 */           "Cn", "Lu", "Ll", "Lt", "Lm", "Lo", "Mn", "Me", "Mc", "Nd", 
/* 6433 */           "Nl", "No", "Zs", "Zl", "Zp", "Cc", "Cf", "Co", "Cs", 
/* 6434 */           "Pd", "Ps", "Pe", "Pc", "Po", "Sm", "Sc", "Sk", "So", 
/* 6435 */           "Pi", "Pf", 
/* 6436 */           "L", "M", "N", "Z", "C", "P", "S" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6451 */       blockNames = new String[] { 
/* 6452 */           "Basic Latin", 
/* 6453 */           "Latin-1 Supplement", 
/* 6454 */           "Latin Extended-A", 
/* 6455 */           "Latin Extended-B", 
/* 6456 */           "IPA Extensions", 
/* 6457 */           "Spacing Modifier Letters", 
/* 6458 */           "Combining Diacritical Marks", 
/* 6459 */           "Greek", 
/* 6460 */           "Cyrillic", 
/* 6461 */           "Armenian", 
/* 6462 */           "Hebrew", 
/* 6463 */           "Arabic", 
/* 6464 */           "Syriac", 
/* 6465 */           "Thaana", 
/* 6466 */           "Devanagari", 
/* 6467 */           "Bengali", 
/* 6468 */           "Gurmukhi", 
/* 6469 */           "Gujarati", 
/* 6470 */           "Oriya", 
/* 6471 */           "Tamil", 
/* 6472 */           "Telugu", 
/* 6473 */           "Kannada", 
/* 6474 */           "Malayalam", 
/* 6475 */           "Sinhala", 
/* 6476 */           "Thai", 
/* 6477 */           "Lao", 
/* 6478 */           "Tibetan", 
/* 6479 */           "Myanmar", 
/* 6480 */           "Georgian", 
/* 6481 */           "Hangul Jamo", 
/* 6482 */           "Ethiopic", 
/* 6483 */           "Cherokee", 
/* 6484 */           "Unified Canadian Aboriginal Syllabics", 
/* 6485 */           "Ogham", 
/* 6486 */           "Runic", 
/* 6487 */           "Khmer", 
/* 6488 */           "Mongolian", 
/* 6489 */           "Latin Extended Additional", 
/* 6490 */           "Greek Extended", 
/* 6491 */           "General Punctuation", 
/* 6492 */           "Superscripts and Subscripts", 
/* 6493 */           "Currency Symbols", 
/* 6494 */           "Combining Marks for Symbols", 
/* 6495 */           "Letterlike Symbols", 
/* 6496 */           "Number Forms", 
/* 6497 */           "Arrows", 
/* 6498 */           "Mathematical Operators", 
/* 6499 */           "Miscellaneous Technical", 
/* 6500 */           "Control Pictures", 
/* 6501 */           "Optical Character Recognition", 
/* 6502 */           "Enclosed Alphanumerics", 
/* 6503 */           "Box Drawing", 
/* 6504 */           "Block Elements", 
/* 6505 */           "Geometric Shapes", 
/* 6506 */           "Miscellaneous Symbols", 
/* 6507 */           "Dingbats", 
/* 6508 */           "Braille Patterns", 
/* 6509 */           "CJK Radicals Supplement", 
/* 6510 */           "Kangxi Radicals", 
/* 6511 */           "Ideographic Description Characters", 
/* 6512 */           "CJK Symbols and Punctuation", 
/* 6513 */           "Hiragana", 
/* 6514 */           "Katakana", 
/* 6515 */           "Bopomofo", 
/* 6516 */           "Hangul Compatibility Jamo", 
/* 6517 */           "Kanbun", 
/* 6518 */           "Bopomofo Extended", 
/* 6519 */           "Enclosed CJK Letters and Months", 
/* 6520 */           "CJK Compatibility", 
/* 6521 */           "CJK Unified Ideographs Extension A", 
/* 6522 */           "CJK Unified Ideographs", 
/* 6523 */           "Yi Syllables", 
/* 6524 */           "Yi Radicals", 
/* 6525 */           "Hangul Syllables", 
/* 6526 */           "Private Use", 
/* 6527 */           "CJK Compatibility Ideographs", 
/* 6528 */           "Alphabetic Presentation Forms", 
/* 6529 */           "Arabic Presentation Forms-A", 
/* 6530 */           "Combining Half Marks", 
/* 6531 */           "CJK Compatibility Forms", 
/* 6532 */           "Small Form Variants", 
/* 6533 */           "Arabic Presentation Forms-B", 
/* 6534 */           "Specials", 
/* 6535 */           "Halfwidth and Fullwidth Forms", 
/*      */           
/* 6537 */           "Old Italic", 
/* 6538 */           "Gothic", 
/* 6539 */           "Deseret", 
/* 6540 */           "Byzantine Musical Symbols", 
/* 6541 */           "Musical Symbols", 
/* 6542 */           "Mathematical Alphanumeric Symbols", 
/* 6543 */           "CJK Unified Ideographs Extension B", 
/* 6544 */           "CJK Compatibility Ideographs Supplement", 
/* 6545 */           "Tags" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6565 */       nonBMPBlockRanges = new int[] { 
/* 6566 */           66304, 66351, 
/* 6567 */           66352, 66383, 
/* 6568 */           66560, 66639, 
/* 6569 */           118784, 119039, 
/* 6570 */           119040, 119295, 
/* 6571 */           119808, 120831, 
/* 6572 */           131072, 173782, 
/* 6573 */           194560, 195103, 
/* 6574 */           917504, 917631 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6818 */       nonxs = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6863 */       token_grapheme = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6902 */       token_ccs = null; } static ParenToken createLook(int type, Token child) { tokens++; return new ParenToken(type, child, 0); } static ParenToken createParen(Token child, int pnumber) { tokens++; return new ParenToken(6, child, pnumber); } static ClosureToken createClosure(Token tok) { tokens++; return new ClosureToken(3, tok); } static ClosureToken createNGClosure(Token tok) { tokens++; return new ClosureToken(9, tok); } static ConcatToken createConcat(Token tok1, Token tok2) { tokens++; return new ConcatToken(tok1, tok2); } static UnionToken createConcat() { tokens++; return new UnionToken(1); } static UnionToken createUnion() { tokens++; return new UnionToken(2); } static Token createEmpty() { return token_empty; } static RegEx.RangeToken createRange() { tokens++; return new RegEx.RangeToken(4); } static RegEx.RangeToken createNRange() { tokens++; return new RegEx.RangeToken(5); } static CharToken createChar(int ch) { tokens++; return new CharToken(0, ch); } private static CharToken createAnchor(int ch) { tokens++; return new CharToken(8, ch); } static StringToken createBackReference(int refno) { tokens++; return new StringToken(12, null, refno); } static StringToken createString(String str) { tokens++; return new StringToken(10, str, 0); } static ModifierToken createModifierGroup(Token child, int add, int mask) { tokens++; return new ModifierToken(child, add, mask); } static ConditionToken createCondition(int refno, Token condition, Token yespat, Token nopat) { tokens++; return new ConditionToken(refno, condition, yespat, nopat); } protected Token(int type) { this.type = type; } int size() { return 0; } Token getChild(int index) { return null; } void addChild(Token tok) { throw new RuntimeException("Not supported."); } protected void addRange(int start, int end) { throw new RuntimeException("Not supported."); } protected void sortRanges() { throw new RuntimeException("Not supported."); } protected void compactRanges() { throw new RuntimeException("Not supported."); } protected void mergeRanges(Token tok) { throw new RuntimeException("Not supported."); }
/*      */     protected void subtractRanges(Token tok) { throw new RuntimeException("Not supported."); }
/* 6904 */     static synchronized Token getCombiningCharacterSequence() { if (token_ccs != null) {
/* 6905 */         return token_ccs;
/*      */       }
/* 6907 */       Token foo = createClosure(getRange("M", true));
/* 6908 */       foo = createConcat(getRange("M", false), foo);
/* 6909 */       token_ccs = foo;
/* 6910 */       return token_ccs; }
/*      */     protected void intersectRanges(Token tok) { throw new RuntimeException("Not supported."); }
/*      */     static Token complementRanges(Token tok) { return RegEx.RangeToken.complementRanges(tok); }
/*      */     void setMin(int min) {}
/*      */     void setMax(int max) {}
/*      */     int getMin() { return -1; }
/*      */     int getMax() { return -1; }
/*      */     int getReferenceNumber() { return 0; }
/*      */     String getString() { return null; }
/*      */     int getParenNumber() { return 0; }
/*      */     int getChar() { return -1; }
/*      */     public String toString() { return toString(0); }
/*      */     public String toString(int options) { return (this.type == 11) ? "." : ""; } final int getMinLength() { int sum; int i; int ret; int j; switch (this.type) { case 1: sum = 0; for (i = 0; i < size(); i++) sum += getChild(i).getMinLength();  return sum;case 2: case 26: if (size() == 0) return 0;  ret = getChild(0).getMinLength(); for (j = 1; j < size(); j++) { int min = getChild(j).getMinLength(); if (min < ret) ret = min;  }  return ret;case 3: case 9: if (getMin() >= 0) return getMin() * getChild(0).getMinLength();  return 0;case 7: case 8: return 0;case 0: case 4: case 5: case 11: return 1;case 6: case 24: case 25: return getChild(0).getMinLength();case 12: return 0;case 10: return getString().length();case 20: case 21: case 22: case 23: return 0; }  throw new RuntimeException("Token#getMinLength(): Invalid Type: " + this.type); } final int getMaxLength() { int sum; int i; int ret; int j; switch (this.type) { case 1: sum = 0; for (i = 0; i < size(); i++) { int d = getChild(i).getMaxLength(); if (d < 0) return -1;  sum += d; }  return sum;case 2: case 26: if (size() == 0) return 0;  ret = getChild(0).getMaxLength(); for (j = 1; ret >= 0 && j < size(); j++) { int max = getChild(j).getMaxLength(); if (max < 0) { ret = -1; break; }  if (max > ret) ret = max;  }  return ret;case 3: case 9: if (getMax() >= 0) return getMax() * getChild(0).getMaxLength();  return -1;case 7: case 8: return 0;case 0: return 1;case 4: case 5: case 11: return 2;case 6: case 24: case 25: return getChild(0).getMaxLength();case 12: return -1;case 10: return getString().length();case 20: case 21: case 22: case 23: return 0; }  throw new RuntimeException("Token#getMaxLength(): Invalid Type: " + this.type); } private static final boolean isSet(int options, int flag) { return ((options & flag) == flag); } final int analyzeFirstCharacter(RegEx.RangeToken result, int options) { int ret; int i; int ret2; boolean hasEmpty; int j; int ret3; int ret4; int ch; int cha; int ch2; switch (this.type) { case 1: ret = 0; for (i = 0; i < size() && (ret = getChild(i).analyzeFirstCharacter(result, options)) == 0; i++); return ret;case 2: if (size() == 0) return 0;  ret2 = 0; hasEmpty = false; for (j = 0; j < size(); j++) { ret2 = getChild(j).analyzeFirstCharacter(result, options); if (ret2 == 2) break;  if (ret2 == 0) hasEmpty = true;  }  return hasEmpty ? 0 : ret2;case 26: ret3 = getChild(0).analyzeFirstCharacter(result, options); if (size() == 1) return 0;  if (ret3 == 2) return ret3;  ret4 = getChild(1).analyzeFirstCharacter(result, options); if (ret4 == 2) return ret4;  return (ret3 == 0 || ret4 == 0) ? 0 : 1;case 3: case 9: getChild(0).analyzeFirstCharacter(result, options); return 0;case 7: case 8: return 0;case 0: ch = getChar(); result.addRange(ch, ch); if (ch < 65536 && isSet(options, 2)) { ch = Character.toUpperCase((char)ch); result.addRange(ch, ch); ch = Character.toLowerCase((char)ch); result.addRange(ch, ch); }  return 1;case 11: if (isSet(options, 4)) return 0;  return 0;case 4: if (isSet(options, 2)) { result.mergeRanges(((RegEx.RangeToken)this).getCaseInsensitiveToken()); } else { result.mergeRanges(this); }  return 1;case 5: if (isSet(options, 2)) { result.mergeRanges(complementRanges(((RegEx.RangeToken)this).getCaseInsensitiveToken())); } else { result.mergeRanges(complementRanges(this)); }  return 1;case 6: case 24: return getChild(0).analyzeFirstCharacter(result, options);case 25: options |= ((ModifierToken)this).getOptions(); options &= ((ModifierToken)this).getOptionsMask() ^ 0xFFFFFFFF; return getChild(0).analyzeFirstCharacter(result, options);case 12: result.addRange(0, 1114111); return 2;case 10: cha = getString().charAt(0); if (RegEx.REUtil.isHighSurrogate(cha) && getString().length() >= 2 && RegEx.REUtil.isLowSurrogate(ch2 = getString().charAt(1))) cha = RegEx.REUtil.composeFromSurrogates(cha, ch2);  result.addRange(cha, cha); if (cha < 65536 && isSet(options, 2)) { cha = Character.toUpperCase((char)cha); result.addRange(cha, cha); cha = Character.toLowerCase((char)cha); result.addRange(cha, cha); }  return 1;case 20: case 21: case 22: case 23: return 0; }  throw new RuntimeException("Token#analyzeHeadCharacter(): Invalid Type: " + this.type); } private final boolean isShorterThan(Token tok) { int mylength; int otherlength; if (tok == null) return false;  if (this.type == 10) { mylength = getString().length(); } else { throw new RuntimeException("Internal Error: Illegal type: " + this.type); }  if (tok.type == 10) { otherlength = tok.getString().length(); } else { throw new RuntimeException("Internal Error: Illegal type: " + tok.type); }  return (mylength < otherlength); } static class FixedStringContainer {
/*      */       RegEx.Token token = null; int options = 0;
/*      */     } final void findFixedString(FixedStringContainer container, int options) { Token prevToken; int prevOptions; int i; switch (this.type) { case 1: prevToken = null; prevOptions = 0; for (i = 0; i < size(); i++) { getChild(i).findFixedString(container, options); if (prevToken == null || prevToken.isShorterThan(container.token)) { prevToken = container.token; prevOptions = container.options; }  }  container.token = prevToken; container.options = prevOptions; return;case 2: case 3: case 4: case 5: case 7: case 8: case 9: case 11: case 12: case 20: case 21: case 22: case 23: case 26: container.token = null; return;case 0: container.token = null; return;case 10: container.token = this; container.options = options; return;case 6: case 24: getChild(0).findFixedString(container, options); return;case 25: options |= ((ModifierToken)this).getOptions(); options &= ((ModifierToken)this).getOptionsMask() ^ 0xFFFFFFFF; getChild(0).findFixedString(container, options); return; }  throw new RuntimeException("Token#findFixedString(): Invalid Type: " + this.type); } boolean match(int ch) { throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type); } protected static RegEx.RangeToken getRange(String name, boolean positive) { if (categories.size() == 0) synchronized (categories) { Token[] ranges = new Token[categoryNames.length]; for (int i = 0; i < ranges.length; i++) ranges[i] = createRange();  int j; for (j = 0; j < 65536; j++) { int type = Character.getType((char)j); if (type == 21 || type == 22) { if (j == 171 || j == 8216 || j == 8219 || j == 8220 || j == 8223 || j == 8249) type = 29;  if (j == 187 || j == 8217 || j == 8221 || j == 8250) type = 30;  }  ranges[type].addRange(j, j); switch (type) { case 1: case 2: case 3: case 4: case 5: type = 31; break;case 6: case 7: case 8: type = 32; break;case 9: case 10: case 11: type = 33; break;case 12: case 13: case 14: type = 34; break;case 0: case 15: case 16: case 18: case 19: type = 35; break;case 20: case 21: case 22: case 23: case 24: case 29: case 30: type = 36; break;case 25: case 26: case 27: case 28: type = 37; break;default: throw new RuntimeException("org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: " + type); }  ranges[type].addRange(j, j); }  ranges[0].addRange(65536, 1114111); for (j = 0; j < ranges.length; j++) { if (categoryNames[j] != null) { if (j == 0) ranges[j].addRange(65536, 1114111);  categories.put(categoryNames[j], ranges[j]); categories2.put(categoryNames[j], complementRanges(ranges[j])); }  }  StringBuffer buffer = new StringBuffer(50); for (int k = 0; k < blockNames.length; k++) { Token r1 = createRange(); if (k < 84) { int location = k * 2; int rstart = "\000ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ԰֏֐׿؀ۿ܀ݏހ޿ऀॿঀ৿਀੿઀૿଀୿஀௿ఀ౿ಀ೿ഀൿ඀෿฀๿຀໿ༀ࿿က႟Ⴀჿᄀᇿሀ፿Ꭰ᏿᐀ᙿ ᚟ᚠ᛿ក៿᠀᢯Ḁỿἀ῿ ⁯⁰₟₠⃏⃐⃿℀⅏⅐↏←⇿∀⋿⌀⏿␀␿⑀⑟①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀⻿⼀⿟⿰⿿　〿぀ゟ゠ヿ㄀ㄯ㄰㆏㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一鿿ꀀ꒏꒐꓏가힣豈﫿ﬀﭏﭐ﷿︠︯︰﹏﹐﹯ﹰ﻾﻿﻿＀￯".charAt(location); int rend = "\000ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ԰֏֐׿؀ۿ܀ݏހ޿ऀॿঀ৿਀੿઀૿଀୿஀௿ఀ౿ಀ೿ഀൿ඀෿฀๿຀໿ༀ࿿က႟Ⴀჿᄀᇿሀ፿Ꭰ᏿᐀ᙿ ᚟ᚠ᛿ក៿᠀᢯Ḁỿἀ῿ ⁯⁰₟₠⃏⃐⃿℀⅏⅐↏←⇿∀⋿⌀⏿␀␿⑀⑟①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀⻿⼀⿟⿰⿿　〿぀ゟ゠ヿ㄀ㄯ㄰㆏㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一鿿ꀀ꒏꒐꓏가힣豈﫿ﬀﭏﭐ﷿︠︯︰﹏﹐﹯ﹰ﻾﻿﻿＀￯".charAt(location + 1); r1.addRange(rstart, rend); } else { int location = (k - 84) * 2; r1.addRange(nonBMPBlockRanges[location], nonBMPBlockRanges[location + 1]); }  String n = blockNames[k]; if (n.equals("Specials")) r1.addRange(65520, 65533);  if (n.equals("Private Use")) { r1.addRange(983040, 1048573); r1.addRange(1048576, 1114109); }  categories.put(n, r1); categories2.put(n, complementRanges(r1)); buffer.setLength(0); buffer.append("Is"); if (n.indexOf(' ') >= 0) { for (int ci = 0; ci < n.length(); ci++) { if (n.charAt(ci) != ' ') buffer.append(n.charAt(ci));  }  } else { buffer.append(n); }  setAlias(buffer.toString(), n, true); }  setAlias("ASSIGNED", "Cn", false); setAlias("UNASSIGNED", "Cn", true); Token all = createRange(); all.addRange(0, 1114111); categories.put("ALL", all); categories2.put("ALL", complementRanges(all)); registerNonXS("ASSIGNED"); registerNonXS("UNASSIGNED"); registerNonXS("ALL"); Token isalpha = createRange(); isalpha.mergeRanges(ranges[1]); isalpha.mergeRanges(ranges[2]); isalpha.mergeRanges(ranges[5]); categories.put("IsAlpha", isalpha); categories2.put("IsAlpha", complementRanges(isalpha)); registerNonXS("IsAlpha"); Token isalnum = createRange(); isalnum.mergeRanges(isalpha); isalnum.mergeRanges(ranges[9]); categories.put("IsAlnum", isalnum); categories2.put("IsAlnum", complementRanges(isalnum)); registerNonXS("IsAlnum"); Token isspace = createRange(); isspace.mergeRanges(token_spaces); isspace.mergeRanges(ranges[34]); categories.put("IsSpace", isspace); categories2.put("IsSpace", complementRanges(isspace)); registerNonXS("IsSpace"); Token isword = createRange(); isword.mergeRanges(isalnum); isword.addRange(95, 95); categories.put("IsWord", isword); categories2.put("IsWord", complementRanges(isword)); registerNonXS("IsWord"); Token isascii = createRange(); isascii.addRange(0, 127); categories.put("IsASCII", isascii); categories2.put("IsASCII", complementRanges(isascii)); registerNonXS("IsASCII"); Token isnotgraph = createRange(); isnotgraph.mergeRanges(ranges[35]); isnotgraph.addRange(32, 32); categories.put("IsGraph", complementRanges(isnotgraph)); categories2.put("IsGraph", isnotgraph); registerNonXS("IsGraph"); Token isxdigit = createRange(); isxdigit.addRange(48, 57); isxdigit.addRange(65, 70); isxdigit.addRange(97, 102); categories.put("IsXDigit", complementRanges(isxdigit)); categories2.put("IsXDigit", isxdigit); registerNonXS("IsXDigit"); setAlias("IsDigit", "Nd", true); setAlias("IsUpper", "Lu", true); setAlias("IsLower", "Ll", true); setAlias("IsCntrl", "C", true); setAlias("IsPrint", "C", false); setAlias("IsPunct", "P", true); registerNonXS("IsDigit"); registerNonXS("IsUpper"); registerNonXS("IsLower"); registerNonXS("IsCntrl"); registerNonXS("IsPrint"); registerNonXS("IsPunct"); setAlias("alpha", "IsAlpha", true); setAlias("alnum", "IsAlnum", true); setAlias("ascii", "IsASCII", true); setAlias("cntrl", "IsCntrl", true); setAlias("digit", "IsDigit", true); setAlias("graph", "IsGraph", true); setAlias("lower", "IsLower", true); setAlias("print", "IsPrint", true); setAlias("punct", "IsPunct", true); setAlias("space", "IsSpace", true); setAlias("upper", "IsUpper", true); setAlias("word", "IsWord", true); setAlias("xdigit", "IsXDigit", true); registerNonXS("alpha"); registerNonXS("alnum"); registerNonXS("ascii"); registerNonXS("cntrl"); registerNonXS("digit"); registerNonXS("graph"); registerNonXS("lower"); registerNonXS("print"); registerNonXS("punct"); registerNonXS("space"); registerNonXS("upper"); registerNonXS("word"); registerNonXS("xdigit"); }   RegEx.RangeToken tok = positive ? (RegEx.RangeToken)categories.get(name) : (RegEx.RangeToken)categories2.get(name); return tok; } protected static RegEx.RangeToken getRange(String name, boolean positive, boolean xs) { RegEx.RangeToken range = getRange(name, positive); if (xs && range != null && isRegisterNonXS(name)) range = null;  return range; } protected static void registerNonXS(String name) { if (nonxs == null) nonxs = new Hashtable<String, String>();  nonxs.put(name, name); } protected static boolean isRegisterNonXS(String name) { if (nonxs == null) return false;  return nonxs.containsKey(name); } private static void setAlias(String newName, String name, boolean positive) { Token t1 = categories.get(name); Token t2 = categories2.get(name); if (positive) { categories.put(newName, t1); categories2.put(newName, t2); } else { categories2.put(newName, t1); categories.put(newName, t2); }  } static synchronized Token getGraphemePattern() { if (token_grapheme != null) return token_grapheme;  Token base_char = createRange(); base_char.mergeRanges(getRange("ASSIGNED", true)); base_char.subtractRanges(getRange("M", true)); base_char.subtractRanges(getRange("C", true)); Token virama = createRange(); for (int i = 0; i < "्্੍્୍்్್്ฺ྄".length(); i++) virama.addRange(i, i);  Token combiner_wo_virama = createRange(); combiner_wo_virama.mergeRanges(getRange("M", true)); combiner_wo_virama.addRange(4448, 4607); combiner_wo_virama.addRange(65438, 65439); Token left = createUnion(); left.addChild(base_char); left.addChild(token_empty); Token foo = createUnion(); foo.addChild(createConcat(virama, getRange("L", true))); foo.addChild(combiner_wo_virama); foo = createClosure(foo); foo = createConcat(left, foo); token_grapheme = foo; return token_grapheme; } static class StringToken extends Token implements Serializable {
/* 6925 */       private static final long serialVersionUID = 1L; StringToken(int type, String str, int n) { super(type);
/* 6926 */         this.string = str;
/* 6927 */         this.refNumber = n; }
/*      */       
/*      */       String string; int refNumber;
/*      */       
/*      */       int getReferenceNumber() {
/* 6932 */         return this.refNumber;
/*      */       }
/*      */       
/*      */       String getString() {
/* 6936 */         return this.string;
/*      */       }
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/* 6941 */         if (this.type == 12) {
/* 6942 */           return "\\" + this.refNumber;
/*      */         }
/* 6944 */         return RegEx.REUtil.quoteMeta(this.string);
/*      */       }
/*      */     }
/*      */     
/*      */     static class ConcatToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       RegEx.Token child;
/*      */       RegEx.Token child2;
/*      */       
/*      */       ConcatToken(RegEx.Token t1, RegEx.Token t2) {
/* 6957 */         super(1);
/* 6958 */         this.child = t1;
/* 6959 */         this.child2 = t2;
/*      */       }
/*      */ 
/*      */       
/*      */       int size() {
/* 6964 */         return 2;
/*      */       }
/*      */       
/*      */       RegEx.Token getChild(int index) {
/* 6968 */         return (index == 0) ? this.child : this.child2;
/*      */       }
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/*      */         String ret;
/* 6974 */         if (this.child2.type == 3 && this.child2.getChild(0) == this.child) {
/* 6975 */           ret = String.valueOf(this.child.toString(options)) + "+";
/* 6976 */         } else if (this.child2.type == 9 && this.child2.getChild(0) == this.child) {
/* 6977 */           ret = String.valueOf(this.child.toString(options)) + "+?";
/*      */         } else {
/* 6979 */           ret = String.valueOf(this.child.toString(options)) + this.child2.toString(options);
/* 6980 */         }  return ret;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static class CharToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       int chardata;
/*      */       
/*      */       CharToken(int type, int ch) {
/* 6993 */         super(type);
/* 6994 */         this.chardata = ch;
/*      */       }
/*      */ 
/*      */       
/*      */       int getChar() {
/* 6999 */         return this.chardata;
/*      */       }
/*      */       
/*      */       public String toString(int options) {
/*      */         String str1;
/*      */         char c;
/* 7005 */         switch (this.type)
/*      */         { case 0:
/* 7007 */             switch (this.chardata) { case 40: case 41: case 42: case 43: case 46: case 63: case 91:
/*      */               case 92:
/*      */               case 123:
/*      */               case 124:
/* 7011 */                 str1 = "\\" + (char)this.chardata;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 7038 */                 return str1;case 12: str1 = "\\f"; return str1;case 10: str1 = "\\n"; return str1;case 13: str1 = "\\r"; return str1;case 9: str1 = "\\t"; return str1;case 27: str1 = "\\e"; return str1; }  if (this.chardata >= 65536) { String pre = "0" + Integer.toHexString(this.chardata); str1 = "\\v" + pre.substring(pre.length() - 6, pre.length()); } else { c = (char)this.chardata; }  return c;case 8: if (this == RegEx.Token.token_linebeginning || this == RegEx.Token.token_lineend) { char c1 = (char)this.chardata; } else { ret = "\\" + (char)this.chardata; }  return ret; }  String ret = null; return ret;
/*      */       }
/*      */ 
/*      */       
/*      */       boolean match(int ch) {
/* 7043 */         if (this.type == 0) {
/* 7044 */           return (ch == this.chardata);
/*      */         }
/* 7046 */         throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
/*      */       }
/*      */     }
/*      */     
/*      */     static class ClosureToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       int min;
/*      */       int max;
/*      */       RegEx.Token child;
/*      */       
/*      */       ClosureToken(int type, RegEx.Token tok) {
/* 7060 */         super(type);
/* 7061 */         this.child = tok;
/* 7062 */         setMin(-1);
/* 7063 */         setMax(-1);
/*      */       }
/*      */ 
/*      */       
/*      */       int size() {
/* 7068 */         return 1;
/*      */       }
/*      */       
/*      */       RegEx.Token getChild(int index) {
/* 7072 */         return this.child;
/*      */       }
/*      */ 
/*      */       
/*      */       final void setMin(int min) {
/* 7077 */         this.min = min;
/*      */       }
/*      */       
/*      */       final void setMax(int max) {
/* 7081 */         this.max = max;
/*      */       }
/*      */       
/*      */       final int getMin() {
/* 7085 */         return this.min;
/*      */       }
/*      */       
/*      */       final int getMax() {
/* 7089 */         return this.max;
/*      */       }
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/*      */         String ret;
/* 7095 */         if (this.type == 3) {
/* 7096 */           if (getMin() < 0 && getMax() < 0) {
/* 7097 */             ret = String.valueOf(this.child.toString(options)) + "*";
/* 7098 */           } else if (getMin() == getMax()) {
/* 7099 */             ret = String.valueOf(this.child.toString(options)) + "{" + getMin() + "}";
/* 7100 */           } else if (getMin() >= 0 && getMax() >= 0) {
/* 7101 */             ret = String.valueOf(this.child.toString(options)) + "{" + getMin() + "," + getMax() + "}";
/* 7102 */           } else if (getMin() >= 0 && getMax() < 0) {
/* 7103 */             ret = String.valueOf(this.child.toString(options)) + "{" + getMin() + ",}";
/*      */           } else {
/* 7105 */             throw new RuntimeException("Token#toString(): CLOSURE " + 
/* 7106 */                 getMin() + ", " + getMax());
/*      */           } 
/* 7108 */         } else if (getMin() < 0 && getMax() < 0) {
/* 7109 */           ret = String.valueOf(this.child.toString(options)) + "*?";
/* 7110 */         } else if (getMin() == getMax()) {
/* 7111 */           ret = String.valueOf(this.child.toString(options)) + "{" + getMin() + "}?";
/* 7112 */         } else if (getMin() >= 0 && getMax() >= 0) {
/* 7113 */           ret = String.valueOf(this.child.toString(options)) + "{" + getMin() + "," + getMax() + "}?";
/* 7114 */         } else if (getMin() >= 0 && getMax() < 0) {
/* 7115 */           ret = String.valueOf(this.child.toString(options)) + "{" + getMin() + ",}?";
/*      */         } else {
/* 7117 */           throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + 
/* 7118 */               getMin() + ", " + getMax());
/*      */         } 
/* 7120 */         return ret;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static class ParenToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       
/*      */       RegEx.Token child;
/*      */       
/*      */       int parennumber;
/*      */ 
/*      */       
/*      */       ParenToken(int type, RegEx.Token tok, int paren) {
/* 7137 */         super(type);
/* 7138 */         this.child = tok;
/* 7139 */         this.parennumber = paren;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       int size() {
/* 7145 */         return 1;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       RegEx.Token getChild(int index) {
/* 7151 */         return this.child;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       int getParenNumber() {
/* 7157 */         return this.parennumber;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/* 7163 */         String ret = null;
/* 7164 */         switch (this.type) {
/*      */           
/*      */           case 6:
/* 7167 */             if (this.parennumber == 0) {
/*      */               
/* 7169 */               ret = "(?:" + this.child.toString(options) + ")";
/*      */               
/*      */               break;
/*      */             } 
/* 7173 */             ret = "(" + this.child.toString(options) + ")";
/*      */             break;
/*      */ 
/*      */           
/*      */           case 20:
/* 7178 */             ret = "(?=" + this.child.toString(options) + ")";
/*      */             break;
/*      */           case 21:
/* 7181 */             ret = "(?!" + this.child.toString(options) + ")";
/*      */             break;
/*      */           case 22:
/* 7184 */             ret = "(?<=" + this.child.toString(options) + ")";
/*      */             break;
/*      */           case 23:
/* 7187 */             ret = "(?<!" + this.child.toString(options) + ")";
/*      */             break;
/*      */           case 24:
/* 7190 */             ret = "(?>" + this.child.toString(options) + ")";
/*      */             break;
/*      */         } 
/* 7193 */         return ret;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static class ConditionToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       
/*      */       int refNumber;
/*      */       
/*      */       RegEx.Token condition;
/*      */       
/*      */       RegEx.Token yes;
/*      */       
/*      */       RegEx.Token no;
/*      */ 
/*      */       
/*      */       ConditionToken(int refno, RegEx.Token cond, RegEx.Token yespat, RegEx.Token nopat) {
/* 7214 */         super(26);
/* 7215 */         this.refNumber = refno;
/* 7216 */         this.condition = cond;
/* 7217 */         this.yes = yespat;
/* 7218 */         this.no = nopat;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       int size() {
/* 7224 */         return (this.no == null) ? 1 : 2;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       RegEx.Token getChild(int index) {
/* 7230 */         if (index == 0)
/* 7231 */           return this.yes; 
/* 7232 */         if (index == 1)
/* 7233 */           return this.no; 
/* 7234 */         throw new RuntimeException("Internal Error: " + index);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/*      */         String ret;
/* 7241 */         if (this.refNumber > 0) {
/*      */           
/* 7243 */           ret = "(?(" + this.refNumber + ")";
/*      */         }
/* 7245 */         else if (this.condition.type == 8) {
/*      */           
/* 7247 */           ret = "(?(" + this.condition + ")";
/*      */         }
/*      */         else {
/*      */           
/* 7251 */           ret = "(?" + this.condition;
/*      */         } 
/*      */         
/* 7254 */         if (this.no == null) {
/*      */           
/* 7256 */           ret = String.valueOf(ret) + this.yes + ")";
/*      */         }
/*      */         else {
/*      */           
/* 7260 */           ret = String.valueOf(ret) + this.yes + "|" + this.no + ")";
/*      */         } 
/* 7262 */         return ret;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static class ModifierToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       
/*      */       RegEx.Token child;
/*      */       
/*      */       int add;
/*      */       
/*      */       int mask;
/*      */ 
/*      */       
/*      */       ModifierToken(RegEx.Token tok, int add, int mask) {
/* 7281 */         super(25);
/* 7282 */         this.child = tok;
/* 7283 */         this.add = add;
/* 7284 */         this.mask = mask;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       int size() {
/* 7290 */         return 1;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       RegEx.Token getChild(int index) {
/* 7296 */         return this.child;
/*      */       }
/*      */ 
/*      */       
/*      */       int getOptions() {
/* 7301 */         return this.add;
/*      */       }
/*      */ 
/*      */       
/*      */       int getOptionsMask() {
/* 7306 */         return this.mask;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/* 7312 */         return "(?" + ((this.add == 0) ? "" : RegEx.REUtil.createOptionString(this.add)) + (
/* 7313 */           (this.mask == 0) ? "" : RegEx.REUtil.createOptionString(this.mask)) + ":" + this.child.toString(options) + ")";
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static class UnionToken
/*      */       extends Token
/*      */       implements Serializable
/*      */     {
/*      */       private static final long serialVersionUID = 1L;
/*      */       
/*      */       Vector<RegEx.Token> children;
/*      */ 
/*      */       
/*      */       UnionToken(int type) {
/* 7329 */         super(type);
/*      */       }
/*      */ 
/*      */       
/*      */       void addChild(RegEx.Token tok) {
/*      */         StringBuffer buffer;
/* 7335 */         if (tok == null)
/*      */           return; 
/* 7337 */         if (this.children == null)
/* 7338 */           this.children = new Vector<RegEx.Token>(); 
/* 7339 */         if (this.type == 2) {
/*      */           
/* 7341 */           this.children.addElement(tok);
/*      */           
/*      */           return;
/*      */         } 
/* 7345 */         if (tok.type == 1) {
/*      */           
/* 7347 */           for (int i = 0; i < tok.size(); i++)
/* 7348 */             addChild(tok.getChild(i)); 
/*      */           return;
/*      */         } 
/* 7351 */         int size = this.children.size();
/* 7352 */         if (size == 0) {
/*      */           
/* 7354 */           this.children.addElement(tok);
/*      */           return;
/*      */         } 
/* 7357 */         RegEx.Token previous = this.children.elementAt(size - 1);
/* 7358 */         if ((previous.type != 0 && previous.type != 10) || (tok.type != 0 && tok.type != 10)) {
/*      */           
/* 7360 */           this.children.addElement(tok);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 7367 */         int nextMaxLength = (tok.type == 0) ? 2 : tok.getString().length();
/* 7368 */         if (previous.type == 0) {
/*      */           
/* 7370 */           buffer = new StringBuffer(2 + nextMaxLength);
/* 7371 */           int ch = previous.getChar();
/* 7372 */           if (ch >= 65536) {
/* 7373 */             buffer.append(RegEx.REUtil.decomposeToSurrogates(ch));
/*      */           } else {
/* 7375 */             buffer.append((char)ch);
/* 7376 */           }  previous = RegEx.Token.createString(null);
/* 7377 */           this.children.setElementAt(previous, size - 1);
/*      */         }
/*      */         else {
/*      */           
/* 7381 */           buffer = new StringBuffer(previous.getString().length() + nextMaxLength);
/* 7382 */           buffer.append(previous.getString());
/*      */         } 
/*      */         
/* 7385 */         if (tok.type == 0) {
/*      */           
/* 7387 */           int ch = tok.getChar();
/* 7388 */           if (ch >= 65536) {
/* 7389 */             buffer.append(RegEx.REUtil.decomposeToSurrogates(ch));
/*      */           } else {
/* 7391 */             buffer.append((char)ch);
/*      */           } 
/*      */         } else {
/*      */           
/* 7395 */           buffer.append(tok.getString());
/*      */         } 
/*      */         
/* 7398 */         ((RegEx.Token.StringToken)previous).string = new String(buffer);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       int size() {
/* 7404 */         return (this.children == null) ? 0 : this.children.size();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       RegEx.Token getChild(int index) {
/* 7410 */         return this.children.elementAt(index);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public String toString(int options) {
/*      */         String ret;
/* 7417 */         if (this.type == 1) {
/*      */           
/* 7419 */           if (this.children.size() == 2) {
/*      */             
/* 7421 */             RegEx.Token ch = getChild(0);
/* 7422 */             RegEx.Token ch2 = getChild(1);
/* 7423 */             if (ch2.type == 3 && ch2.getChild(0) == ch) {
/*      */               
/* 7425 */               ret = String.valueOf(ch.toString(options)) + "+";
/*      */             }
/* 7427 */             else if (ch2.type == 9 && ch2.getChild(0) == ch) {
/*      */               
/* 7429 */               ret = String.valueOf(ch.toString(options)) + "+?";
/*      */             } else {
/*      */               
/* 7432 */               ret = String.valueOf(ch.toString(options)) + ch2.toString(options);
/*      */             } 
/*      */           } else {
/*      */             
/* 7436 */             StringBuffer sb = new StringBuffer();
/* 7437 */             for (int i = 0; i < this.children.size(); i++)
/*      */             {
/* 7439 */               sb.append(((RegEx.Token)this.children.elementAt(i)).toString(options));
/*      */             }
/* 7441 */             ret = new String(sb);
/*      */           } 
/* 7443 */           return ret;
/*      */         } 
/* 7445 */         if (this.children.size() == 2 && (getChild(1)).type == 7) {
/*      */           
/* 7447 */           ret = String.valueOf(getChild(0).toString(options)) + "?";
/*      */         }
/* 7449 */         else if (this.children.size() == 2 && (getChild(0)).type == 7) {
/*      */           
/* 7451 */           ret = String.valueOf(getChild(1).toString(options)) + "??";
/*      */         }
/*      */         else {
/*      */           
/* 7455 */           StringBuffer sb = new StringBuffer();
/* 7456 */           sb.append(((RegEx.Token)this.children.elementAt(0)).toString(options));
/* 7457 */           for (int i = 1; i < this.children.size(); i++) {
/*      */             
/* 7459 */             sb.append('|');
/* 7460 */             sb.append(((RegEx.Token)this.children.elementAt(i)).toString(options));
/*      */           } 
/* 7462 */           ret = new String(sb);
/*      */         } 
/* 7464 */         return ret;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ParserForXMLSchema
/*      */     extends RegexParser
/*      */   {
/*      */     public ParserForXMLSchema() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ParserForXMLSchema(Locale locale) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processCaret() throws RegEx.ParseException {
/* 7491 */       next();
/* 7492 */       return RegEx.Token.createChar(94);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processDollar() throws RegEx.ParseException {
/* 7498 */       next();
/* 7499 */       return RegEx.Token.createChar(36);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processLookahead() throws RegEx.ParseException {
/* 7505 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processNegativelookahead() throws RegEx.ParseException {
/* 7511 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processLookbehind() throws RegEx.ParseException {
/* 7517 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processNegativelookbehind() throws RegEx.ParseException {
/* 7523 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_A() throws RegEx.ParseException {
/* 7529 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_Z() throws RegEx.ParseException {
/* 7535 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_z() throws RegEx.ParseException {
/* 7541 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_b() throws RegEx.ParseException {
/* 7547 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_B() throws RegEx.ParseException {
/* 7553 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_lt() throws RegEx.ParseException {
/* 7559 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_gt() throws RegEx.ParseException {
/* 7565 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processStar(RegEx.Token tok) throws RegEx.ParseException {
/* 7571 */       next();
/* 7572 */       return RegEx.Token.createClosure(tok);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processPlus(RegEx.Token tok) throws RegEx.ParseException {
/* 7579 */       next();
/* 7580 */       return RegEx.Token.createConcat(tok, RegEx.Token.createClosure(tok));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processQuestion(RegEx.Token tok) throws RegEx.ParseException {
/* 7587 */       next();
/* 7588 */       RegEx.Token par = RegEx.Token.createUnion();
/* 7589 */       par.addChild(tok);
/* 7590 */       par.addChild(RegEx.Token.createEmpty());
/* 7591 */       return par;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     boolean checkQuestion(int off) {
/* 7597 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processParen() throws RegEx.ParseException {
/* 7603 */       next();
/* 7604 */       RegEx.Token tok = RegEx.Token.createParen(parseRegex(), 0);
/* 7605 */       if (read() != 7)
/* 7606 */         throw ex("parser.factor.1", this.offset - 1); 
/* 7607 */       next();
/* 7608 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processParen2() throws RegEx.ParseException {
/* 7614 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processCondition() throws RegEx.ParseException {
/* 7620 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processModifiers() throws RegEx.ParseException {
/* 7626 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processIndependent() throws RegEx.ParseException {
/* 7632 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_c() throws RegEx.ParseException {
/* 7638 */       next();
/* 7639 */       return getTokenForShorthand(99);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_C() throws RegEx.ParseException {
/* 7645 */       next();
/* 7646 */       return getTokenForShorthand(67);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_i() throws RegEx.ParseException {
/* 7652 */       next();
/* 7653 */       return getTokenForShorthand(105);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_I() throws RegEx.ParseException {
/* 7659 */       next();
/* 7660 */       return getTokenForShorthand(73);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_g() throws RegEx.ParseException {
/* 7666 */       throw ex("parser.process.1", this.offset - 2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBacksolidus_X() throws RegEx.ParseException {
/* 7672 */       throw ex("parser.process.1", this.offset - 2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token processBackreference() throws RegEx.ParseException {
/* 7678 */       throw ex("parser.process.1", this.offset - 4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int processCIinCharacterClass(RegEx.RangeToken tok, int c) {
/* 7684 */       tok.mergeRanges(getTokenForShorthand(c));
/* 7685 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected RegEx.RangeToken parseCharacterClass(boolean useNrange) throws RegEx.ParseException {
/*      */       RegEx.RangeToken tok;
/* 7708 */       setContext(1);
/* 7709 */       next();
/* 7710 */       RegEx.RangeToken base = null;
/*      */       
/* 7712 */       if (read() == 0 && this.chardata == 94) {
/*      */         
/* 7714 */         next();
/* 7715 */         base = RegEx.Token.createRange();
/* 7716 */         base.addRange(0, 1114111);
/* 7717 */         tok = RegEx.Token.createRange();
/*      */       }
/*      */       else {
/*      */         
/* 7721 */         tok = RegEx.Token.createRange();
/*      */       } 
/*      */       
/* 7724 */       boolean firstloop = true; int type;
/* 7725 */       while ((type = read()) != 1) {
/*      */ 
/*      */         
/* 7728 */         if (type == 0 && this.chardata == 93 && !firstloop) {
/*      */           
/* 7730 */           if (base != null) {
/*      */             
/* 7732 */             base.subtractRanges(tok);
/* 7733 */             tok = base;
/*      */           } 
/*      */           break;
/*      */         } 
/* 7737 */         int c = this.chardata;
/* 7738 */         boolean end = false;
/* 7739 */         if (type == 10) {
/*      */           int pstart; RegEx.RangeToken tok2;
/* 7741 */           switch (c) {
/*      */             
/*      */             case 68:
/*      */             case 83:
/*      */             case 87:
/*      */             case 100:
/*      */             case 115:
/*      */             case 119:
/* 7749 */               tok.mergeRanges(getTokenForShorthand(c));
/* 7750 */               end = true;
/*      */               break;
/*      */             
/*      */             case 67:
/*      */             case 73:
/*      */             case 99:
/*      */             case 105:
/* 7757 */               c = processCIinCharacterClass(tok, c);
/* 7758 */               if (c < 0) {
/* 7759 */                 end = true;
/*      */               }
/*      */               break;
/*      */             case 80:
/*      */             case 112:
/* 7764 */               pstart = this.offset;
/* 7765 */               tok2 = processBacksolidus_pP(c);
/* 7766 */               if (tok2 == null)
/* 7767 */                 throw ex("parser.atom.5", pstart); 
/* 7768 */               tok.mergeRanges(tok2);
/* 7769 */               end = true;
/*      */               break;
/*      */             
/*      */             default:
/* 7773 */               c = decodeEscaped();
/*      */               break;
/*      */           } 
/* 7776 */         } else if (type == 24 && !firstloop) {
/*      */ 
/*      */           
/* 7779 */           if (base != null) {
/*      */             
/* 7781 */             base.subtractRanges(tok);
/* 7782 */             tok = base;
/*      */           } 
/* 7784 */           RegEx.RangeToken range2 = parseCharacterClass(false);
/* 7785 */           tok.subtractRanges(range2);
/* 7786 */           if (read() != 0 || this.chardata != 93)
/* 7787 */             throw ex("parser.cc.5", this.offset); 
/*      */           break;
/*      */         } 
/* 7790 */         next();
/* 7791 */         if (!end) {
/*      */           
/* 7793 */           if (type == 0) {
/*      */             
/* 7795 */             if (c == 91)
/* 7796 */               throw ex("parser.cc.6", this.offset - 2); 
/* 7797 */             if (c == 93)
/* 7798 */               throw ex("parser.cc.7", this.offset - 2); 
/* 7799 */             if (c == 45 && !firstloop && this.chardata != 93)
/* 7800 */               throw ex("parser.cc.8", this.offset - 2); 
/*      */           } 
/* 7802 */           if (read() != 0 || this.chardata != 45 || (c == 45 && firstloop)) {
/*      */             
/* 7804 */             tok.addRange(c, c);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 7809 */             next();
/* 7810 */             if ((type = read()) == 1) {
/* 7811 */               throw ex("parser.cc.2", this.offset);
/*      */             }
/* 7813 */             if (type == 0 && this.chardata == 93) {
/*      */               
/* 7815 */               tok.addRange(c, c);
/* 7816 */               tok.addRange(45, 45);
/*      */             } else {
/* 7818 */               if ((type == 0 && this.chardata == 93) || type == 24)
/*      */               {
/* 7820 */                 throw ex("parser.cc.8", this.offset - 1);
/*      */               }
/*      */ 
/*      */               
/* 7824 */               int rangeend = this.chardata;
/* 7825 */               if (type == 0) {
/*      */                 
/* 7827 */                 if (rangeend == 91)
/* 7828 */                   throw ex("parser.cc.6", this.offset - 1); 
/* 7829 */                 if (rangeend == 93)
/* 7830 */                   throw ex("parser.cc.7", this.offset - 1); 
/* 7831 */                 if (rangeend == 45) {
/* 7832 */                   throw ex("parser.cc.8", this.offset - 1);
/*      */                 }
/* 7834 */               } else if (type == 10) {
/* 7835 */                 rangeend = decodeEscaped();
/* 7836 */               }  next();
/*      */               
/* 7838 */               if (c > rangeend)
/* 7839 */                 throw ex("parser.ope.3", this.offset - 1); 
/* 7840 */               tok.addRange(c, rangeend);
/*      */             } 
/*      */           } 
/*      */         } 
/* 7844 */         firstloop = false;
/*      */       } 
/* 7846 */       if (read() == 1)
/* 7847 */         throw ex("parser.cc.2", this.offset); 
/* 7848 */       tok.sortRanges();
/* 7849 */       tok.compactRanges();
/*      */       
/* 7851 */       setContext(0);
/* 7852 */       next();
/*      */       
/* 7854 */       return tok;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected RegEx.RangeToken parseSetOperations() throws RegEx.ParseException {
/* 7860 */       throw ex("parser.process.1", this.offset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RegEx.Token getTokenForShorthand(int ch) {
/* 7866 */       switch (ch) {
/*      */         
/*      */         case 100:
/* 7869 */           return getRange("xml:isDigit", true);
/*      */         case 68:
/* 7871 */           return getRange("xml:isDigit", false);
/*      */         case 119:
/* 7873 */           return getRange("xml:isWord", true);
/*      */         case 87:
/* 7875 */           return getRange("xml:isWord", false);
/*      */         case 115:
/* 7877 */           return getRange("xml:isSpace", true);
/*      */         case 83:
/* 7879 */           return getRange("xml:isSpace", false);
/*      */         case 99:
/* 7881 */           return getRange("xml:isNameChar", true);
/*      */         case 67:
/* 7883 */           return getRange("xml:isNameChar", false);
/*      */         case 105:
/* 7885 */           return getRange("xml:isInitialNameChar", true);
/*      */         case 73:
/* 7887 */           return getRange("xml:isInitialNameChar", false);
/*      */       } 
/* 7889 */       throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int decodeEscaped() throws RegEx.ParseException {
/* 7896 */       if (read() != 10)
/* 7897 */         throw ex("parser.next.1", this.offset - 1); 
/* 7898 */       int c = this.chardata;
/* 7899 */       switch (c) {
/*      */         
/*      */         case 110:
/* 7902 */           c = 10;
/*      */         
/*      */         case 114:
/* 7905 */           c = 13;
/*      */         
/*      */         case 116:
/* 7908 */           c = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 45:
/*      */         case 46:
/*      */         case 63:
/*      */         case 91:
/*      */         case 92:
/*      */         case 93:
/*      */         case 94:
/*      */         case 123:
/*      */         case 124:
/*      */         case 125:
/* 7928 */           return c;
/*      */       } 
/*      */       throw ex("parser.process.1", this.offset - 2);
/* 7931 */     } private static Hashtable<String, RegEx.Token> ranges = null;
/*      */     
/* 7933 */     private static Hashtable<String, RegEx.Token> ranges2 = null; private static final String SPACES = "\t\n\r\r  "; private static final String NAMECHARS = "-.0:AZ__az··ÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁːˑ̀͠͡ͅΆΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁ҃҆ҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆֹֻֽֿֿׁׂ֑֣֡ׄׄאתװײءغـْ٠٩ٰڷںھۀێېۓە۪ۭۨ۰۹ँःअह़्॑॔क़ॣ०९ঁঃঅঌএঐওনপরললশহ়়াৄেৈো্ৗৗড়ঢ়য়ৣ০ৱਂਂਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹ਼਼ਾੂੇੈੋ੍ਖ਼ੜਫ਼ਫ਼੦ੴઁઃઅઋઍઍએઑઓનપરલળવહ઼ૅેૉો્ૠૠ૦૯ଁଃଅଌଏଐଓନପରଲଳଶହ଼ୃେୈୋ୍ୖୗଡ଼ଢ଼ୟୡ୦୯ஂஃஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹாூெைொ்ௗௗ௧௯ఁఃఅఌఎఐఒనపళవహాౄెైొ్ౕౖౠౡ౦౯ಂಃಅಌಎಐಒನಪಳವಹಾೄೆೈೊ್ೕೖೞೞೠೡ೦೯ംഃഅഌഎഐഒനപഹാൃെൈൊ്ൗൗൠൡ൦൯กฮะฺเ๎๐๙ກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະູົຽເໄໆໆ່ໍ໐໙༘༙༠༩༹༹༵༵༷༷༾ཇཉཀྵ྄ཱ྆ྋྐྕྗྗྙྭྱྷྐྵྐྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼ⃐⃜⃡⃡ΩΩKÅ℮℮ↀↂ々々〇〇〡〯〱〵ぁゔ゙゚ゝゞァヺーヾㄅㄬ一龥가힣"; private static final String LETTERS = "AZazÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁΆΆΈΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆאתװײءغفيٱڷںھۀێېۓەەۥۦअहऽऽक़ॡঅঌএঐওনপরললশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜਫ਼ਫ਼ੲੴઅઋઍઍએઑઓનપરલળવહઽઽૠૠଅଌଏଐଓନପରଲଳଶହଽଽଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೞೞೠೡഅഌഎഐഒനപഹൠൡกฮะะาำเๅກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະະາຳຽຽເໄཀཇཉཀྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼΩΩKÅ℮℮ↀↂ〇〇〡〩ぁゔァヺㄅㄬ一龥가힣";
/*      */     private static final String DIGITS = "09٠٩۰۹०९০৯੦੯૦૯୦୯௧௯౦౯೦೯൦൯๐๙໐໙༠༩";
/*      */     
/*      */     protected static synchronized RegEx.RangeToken getRange(String name, boolean positive) {
/* 7937 */       if (ranges == null) {
/*      */         
/* 7939 */         ranges = new Hashtable<String, RegEx.Token>();
/* 7940 */         ranges2 = new Hashtable<String, RegEx.Token>();
/*      */         
/* 7942 */         RegEx.Token token = RegEx.Token.createRange();
/* 7943 */         setupRange(token, "\t\n\r\r  ");
/* 7944 */         ranges.put("xml:isSpace", token);
/* 7945 */         ranges2.put("xml:isSpace", RegEx.Token.complementRanges(token));
/*      */         
/* 7947 */         token = RegEx.Token.createRange();
/* 7948 */         setupRange(token, "09٠٩۰۹०९০৯੦੯૦૯୦୯௧௯౦౯೦೯൦൯๐๙໐໙༠༩");
/* 7949 */         ranges.put("xml:isDigit", token);
/* 7950 */         ranges2.put("xml:isDigit", RegEx.Token.complementRanges(token));
/*      */         
/* 7952 */         token = RegEx.Token.createRange();
/* 7953 */         setupRange(token, "09٠٩۰۹०९০৯੦੯૦૯୦୯௧௯౦౯೦೯൦൯๐๙໐໙༠༩");
/* 7954 */         ranges.put("xml:isDigit", token);
/* 7955 */         ranges2.put("xml:isDigit", RegEx.Token.complementRanges(token));
/*      */         
/* 7957 */         token = RegEx.Token.createRange();
/* 7958 */         setupRange(token, "AZazÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁΆΆΈΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆאתװײءغفيٱڷںھۀێېۓەەۥۦअहऽऽक़ॡঅঌএঐওনপরললশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜਫ਼ਫ਼ੲੴઅઋઍઍએઑઓનપરલળવહઽઽૠૠଅଌଏଐଓନପରଲଳଶହଽଽଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೞೞೠೡഅഌഎഐഒനപഹൠൡกฮะะาำเๅກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະະາຳຽຽເໄཀཇཉཀྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼΩΩKÅ℮℮ↀↂ〇〇〡〩ぁゔァヺㄅㄬ一龥가힣");
/* 7959 */         token.mergeRanges(ranges.get("xml:isDigit"));
/* 7960 */         ranges.put("xml:isWord", token);
/* 7961 */         ranges2.put("xml:isWord", RegEx.Token.complementRanges(token));
/*      */         
/* 7963 */         token = RegEx.Token.createRange();
/* 7964 */         setupRange(token, "-.0:AZ__az··ÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁːˑ̀͠͡ͅΆΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁ҃҆ҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆֹֻֽֿֿׁׂ֑֣֡ׄׄאתװײءغـْ٠٩ٰڷںھۀێېۓە۪ۭۨ۰۹ँःअह़्॑॔क़ॣ०९ঁঃঅঌএঐওনপরললশহ়়াৄেৈো্ৗৗড়ঢ়য়ৣ০ৱਂਂਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹ਼਼ਾੂੇੈੋ੍ਖ਼ੜਫ਼ਫ਼੦ੴઁઃઅઋઍઍએઑઓનપરલળવહ઼ૅેૉો્ૠૠ૦૯ଁଃଅଌଏଐଓନପରଲଳଶହ଼ୃେୈୋ୍ୖୗଡ଼ଢ଼ୟୡ୦୯ஂஃஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹாூெைொ்ௗௗ௧௯ఁఃఅఌఎఐఒనపళవహాౄెైొ్ౕౖౠౡ౦౯ಂಃಅಌಎಐಒನಪಳವಹಾೄೆೈೊ್ೕೖೞೞೠೡ೦೯ംഃഅഌഎഐഒനപഹാൃെൈൊ്ൗൗൠൡ൦൯กฮะฺเ๎๐๙ກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະູົຽເໄໆໆ່ໍ໐໙༘༙༠༩༹༹༵༵༷༷༾ཇཉཀྵ྄ཱ྆ྋྐྕྗྗྙྭྱྷྐྵྐྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼ⃐⃜⃡⃡ΩΩKÅ℮℮ↀↂ々々〇〇〡〯〱〵ぁゔ゙゚ゝゞァヺーヾㄅㄬ一龥가힣");
/* 7965 */         ranges.put("xml:isNameChar", token);
/* 7966 */         ranges2.put("xml:isNameChar", RegEx.Token.complementRanges(token));
/*      */         
/* 7968 */         token = RegEx.Token.createRange();
/* 7969 */         setupRange(token, "AZazÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁΆΆΈΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆאתװײءغفيٱڷںھۀێېۓەەۥۦअहऽऽक़ॡঅঌএঐওনপরললশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜਫ਼ਫ਼ੲੴઅઋઍઍએઑઓનપરલળવહઽઽૠૠଅଌଏଐଓନପରଲଳଶହଽଽଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೞೞೠೡഅഌഎഐഒനപഹൠൡกฮะะาำเๅກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະະາຳຽຽເໄཀཇཉཀྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼΩΩKÅ℮℮ↀↂ〇〇〡〩ぁゔァヺㄅㄬ一龥가힣");
/* 7970 */         token.addRange(95, 95);
/* 7971 */         token.addRange(58, 58);
/* 7972 */         ranges.put("xml:isInitialNameChar", token);
/* 7973 */         ranges2.put("xml:isInitialNameChar", RegEx.Token.complementRanges(token));
/*      */       } 
/* 7975 */       RegEx.RangeToken tok = positive ? (RegEx.RangeToken)ranges.get(name) : (RegEx.RangeToken)ranges2.get(name);
/* 7976 */       return tok;
/*      */     }
/*      */ 
/*      */     
/*      */     static void setupRange(RegEx.Token range, String src) {
/* 7981 */       int len = src.length();
/* 7982 */       for (int i = 0; i < len; i += 2)
/* 7983 */         range.addRange(src.charAt(i), src.charAt(i + 1)); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\internal\RegEx.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */