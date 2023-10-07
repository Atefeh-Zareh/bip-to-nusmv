/*      */ package org.eclipse.emf.common.util;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class URI
/*      */ {
/*      */   private final int hashCode;
/*      */   private static final int HIERARICHICAL_FLAG = 256;
/*      */   private final String scheme;
/*      */   private final String authority;
/*      */   private final String fragment;
/*      */   private URI cachedTrimFragment;
/*      */   private String cachedToString;
/*      */   private final String device;
/*      */   private static final int ABSOLUTE_PATH_FLAG = 16;
/*      */   private final String[] segments;
/*      */   private final String query;
/*      */   private static final Set<String> archiveSchemes;
/*      */   private static final String SCHEME_FILE = "file";
/*      */   private static final String SCHEME_JAR = "jar";
/*      */   private static final String SCHEME_ZIP = "zip";
/*      */   private static final String SCHEME_ARCHIVE = "archive";
/*  161 */   private static final URICache uriCache = new URICache(null);
/*      */   private static final String SCHEME_PLATFORM = "platform";
/*      */   private static final String SEGMENT_EMPTY = "";
/*      */   private static final String SEGMENT_SELF = ".";
/*      */   private static final String SEGMENT_PARENT = "..";
/*      */   
/*      */   private static class URICache extends HashMap<String, WeakReference<URI>> {
/*      */     private static final long serialVersionUID = 1L;
/*  169 */     int limit = 1000;
/*      */     static final int MIN_LIMIT = 1000;
/*      */     
/*      */     public synchronized URI get(String key) {
/*  173 */       WeakReference<URI> reference = (WeakReference<URI>)get(key);
/*  174 */       return (reference == null) ? null : reference.get();
/*      */     }
/*      */     int count;
/*      */     
/*      */     public synchronized void put(String key, URI value) {
/*  179 */       put((K)key, (V)new WeakReference<URI>(value));
/*  180 */       if (++this.count > this.limit)
/*      */       {
/*  182 */         cleanGCedValues();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void cleanGCedValues() {
/*  188 */       for (Iterator<Map.Entry<String, WeakReference<URI>>> i = entrySet().iterator(); i.hasNext(); ) {
/*      */         
/*  190 */         Map.Entry<String, WeakReference<URI>> entry = i.next();
/*  191 */         WeakReference<URI> reference = entry.getValue();
/*  192 */         if (reference.get() == null)
/*      */         {
/*  194 */           i.remove();
/*      */         }
/*      */       } 
/*  197 */       this.count = 0;
/*  198 */       this.limit = Math.max(1000, size() / 2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private URICache() {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   private static final String[] NO_SEGMENTS = new String[0];
/*      */   
/*      */   private static final char SCHEME_SEPARATOR = ':';
/*      */   
/*      */   private static final String AUTHORITY_SEPARATOR = "//";
/*      */   
/*      */   private static final char DEVICE_IDENTIFIER = ':';
/*      */   
/*      */   private static final char SEGMENT_SEPARATOR = '/';
/*      */   private static final char QUERY_SEPARATOR = '?';
/*      */   private static final char FRAGMENT_SEPARATOR = '#';
/*      */   private static final char USER_INFO_SEPARATOR = '@';
/*      */   private static final char PORT_SEPARATOR = ':';
/*      */   private static final char FILE_EXTENSION_SEPARATOR = '.';
/*      */   private static final char ARCHIVE_IDENTIFIER = '!';
/*      */   private static final String ARCHIVE_SEPARATOR = "!/";
/*      */   private static final char ESCAPE = '%';
/*  233 */   private static final char[] HEX_DIGITS = new char[] { 
/*  234 */       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  242 */   private static final long ALPHA_HI = highBitmask('a', 'z') | highBitmask('A', 'Z');
/*  243 */   private static final long ALPHA_LO = lowBitmask('a', 'z') | lowBitmask('A', 'Z');
/*  244 */   private static final long DIGIT_HI = highBitmask('0', '9');
/*  245 */   private static final long DIGIT_LO = lowBitmask('0', '9');
/*  246 */   private static final long ALPHANUM_HI = ALPHA_HI | DIGIT_HI;
/*  247 */   private static final long ALPHANUM_LO = ALPHA_LO | DIGIT_LO;
/*  248 */   private static final long HEX_HI = DIGIT_HI | highBitmask('A', 'F') | highBitmask('a', 'f');
/*  249 */   private static final long HEX_LO = DIGIT_LO | lowBitmask('A', 'F') | lowBitmask('a', 'f');
/*  250 */   private static final long UNRESERVED_HI = ALPHANUM_HI | highBitmask("-_.!~*'()");
/*  251 */   private static final long UNRESERVED_LO = ALPHANUM_LO | lowBitmask("-_.!~*'()");
/*  252 */   private static final long RESERVED_HI = highBitmask(";/?:@&=+$,");
/*  253 */   private static final long RESERVED_LO = lowBitmask(";/?:@&=+$,");
/*  254 */   private static final long URIC_HI = RESERVED_HI | UNRESERVED_HI;
/*  255 */   private static final long URIC_LO = RESERVED_LO | UNRESERVED_LO;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  260 */   private static final long SEGMENT_CHAR_HI = UNRESERVED_HI | highBitmask(";:@&=+$,");
/*  261 */   private static final long SEGMENT_CHAR_LO = UNRESERVED_LO | lowBitmask(";:@&=+$,");
/*  262 */   private static final long PATH_CHAR_HI = SEGMENT_CHAR_HI | highBitmask('/');
/*  263 */   private static final long PATH_CHAR_LO = SEGMENT_CHAR_LO | lowBitmask('/');
/*      */ 
/*      */   
/*  266 */   private static final long MAJOR_SEPARATOR_HI = highBitmask(":/?#");
/*  267 */   private static final long MAJOR_SEPARATOR_LO = lowBitmask(":/?#");
/*  268 */   private static final long SEGMENT_END_HI = highBitmask("/?#");
/*  269 */   private static final long SEGMENT_END_LO = lowBitmask("/?#");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  278 */   private static final boolean ENCODE_PLATFORM_RESOURCE_URIS = (System.getProperty("org.eclipse.emf.common.util.URI.encodePlatformResourceURIs") != null && 
/*  279 */     !"false".equalsIgnoreCase(System.getProperty("org.eclipse.emf.common.util.URI.encodePlatformResourceURIs"))); public static final int FRAGMENT_NONE = 0;
/*      */   public static final int FRAGMENT_FIRST_SEPARATOR = 1;
/*      */   public static final int FRAGMENT_LAST_SEPARATOR = 2;
/*      */   
/*      */   static {
/*  284 */     Set<String> set = new HashSet<String>();
/*  285 */     String propertyValue = System.getProperty("org.eclipse.emf.common.util.URI.archiveSchemes");
/*      */     
/*  287 */     if (propertyValue == null) {
/*      */       
/*  289 */       set.add("jar");
/*  290 */       set.add("zip");
/*  291 */       set.add("archive");
/*      */     }
/*      */     else {
/*      */       
/*  295 */       for (StringTokenizer t = new StringTokenizer(propertyValue); t.hasMoreTokens();)
/*      */       {
/*  297 */         set.add(t.nextToken().toLowerCase());
/*      */       }
/*      */     } 
/*      */     
/*  301 */     archiveSchemes = Collections.unmodifiableSet(set);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static long lowBitmask(char c) {
/*  307 */     return (c < '@') ? (1L << c) : 0L;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static long highBitmask(char c) {
/*  313 */     return (c >= '@' && c < '') ? (1L << c - 64) : 0L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long lowBitmask(char from, char to) {
/*  320 */     long result = 0L;
/*  321 */     if (from < '@' && from <= to) {
/*      */       
/*  323 */       to = (to < '@') ? to : '?';
/*  324 */       for (char c = from; c <= to; c = (char)(c + 1))
/*      */       {
/*  326 */         result |= 1L << c;
/*      */       }
/*      */     } 
/*  329 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long highBitmask(char from, char to) {
/*  336 */     return (to < '@') ? 0L : lowBitmask((char)((from < '@') ? Character.MIN_VALUE : (from - 64)), (char)(to - 64));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long lowBitmask(String chars) {
/*  343 */     long result = 0L;
/*  344 */     for (int i = 0, len = chars.length(); i < len; i++) {
/*      */       
/*  346 */       char c = chars.charAt(i);
/*  347 */       if (c < '@') result |= 1L << c; 
/*      */     } 
/*  349 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static long highBitmask(String chars) {
/*  356 */     long result = 0L;
/*  357 */     for (int i = 0, len = chars.length(); i < len; i++) {
/*      */       
/*  359 */       char c = chars.charAt(i);
/*  360 */       if (c >= '@' && c < '') result |= 1L << c - 64; 
/*      */     } 
/*  362 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean matches(char c, long highBitmask, long lowBitmask) {
/*  369 */     if (c >= '') return false; 
/*  370 */     return (c < '@') ? (
/*  371 */       ((1L << c & lowBitmask) != 0L)) : (
/*  372 */       ((1L << c - 64 & highBitmask) != 0L));
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
/*      */   public static URI createGenericURI(String scheme, String opaquePart, String fragment) {
/*  405 */     if (scheme == null)
/*      */     {
/*  407 */       throw new IllegalArgumentException("relative non-hierarchical URI");
/*      */     }
/*      */     
/*  410 */     if (isArchiveScheme(scheme))
/*      */     {
/*  412 */       throw new IllegalArgumentException("non-hierarchical archive URI");
/*      */     }
/*      */     
/*  415 */     validateURI(false, scheme, opaquePart, null, false, NO_SEGMENTS, null, fragment);
/*  416 */     return new URI(false, scheme, opaquePart, null, false, NO_SEGMENTS, null, fragment);
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
/*      */   public static URI createHierarchicalURI(String scheme, String authority, String device, String query, String fragment) {
/*  439 */     if (scheme != null && authority == null && device == null)
/*      */     {
/*  441 */       throw new IllegalArgumentException(
/*  442 */           "absolute hierarchical URI without authority, device, path");
/*      */     }
/*      */     
/*  445 */     if (isArchiveScheme(scheme))
/*      */     {
/*  447 */       throw new IllegalArgumentException("archive URI with no path");
/*      */     }
/*      */     
/*  450 */     validateURI(true, scheme, authority, device, false, NO_SEGMENTS, query, fragment);
/*  451 */     return new URI(true, scheme, authority, device, false, NO_SEGMENTS, query, fragment);
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
/*      */   public static URI createHierarchicalURI(String scheme, String authority, String device, String[] segments, String query, String fragment) {
/*  480 */     if (isArchiveScheme(scheme) && device != null)
/*      */     {
/*  482 */       throw new IllegalArgumentException("archive URI with device");
/*      */     }
/*      */     
/*  485 */     segments = fix(segments);
/*  486 */     validateURI(true, scheme, authority, device, true, segments, query, fragment);
/*  487 */     return new URI(true, scheme, authority, device, true, segments, query, fragment);
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
/*      */   public static URI createHierarchicalURI(String[] segments, String query, String fragment) {
/*  506 */     segments = fix(segments);
/*  507 */     validateURI(true, null, null, null, false, segments, query, fragment);
/*  508 */     return new URI(true, null, null, null, false, segments, query, fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] fix(String[] segments) {
/*  515 */     return (segments == null) ? NO_SEGMENTS : (String[])segments.clone();
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
/*      */   public static URI createURI(String uri) {
/*  541 */     return createURIWithCache(uri);
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
/*      */   public static URI createURI(String uri, boolean ignoreEscaped) {
/*  568 */     return createURIWithCache(encodeURI(uri, ignoreEscaped, 2));
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
/*      */   public static URI createURI(String uri, boolean ignoreEscaped, int fragmentLocationStyle) {
/*  626 */     return createURIWithCache(encodeURI(uri, ignoreEscaped, fragmentLocationStyle));
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
/*      */   @Deprecated
/*      */   public static URI createDeviceURI(String uri) {
/*  651 */     return createURIWithCache(uri);
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
/*      */   @Deprecated
/*      */   public static URI createURIWithCache(String uri) {
/*  669 */     int i = uri.indexOf('#');
/*  670 */     String base = (i == -1) ? uri : uri.substring(0, i);
/*  671 */     String fragment = (i == -1) ? null : uri.substring(i + 1);
/*      */     
/*  673 */     URI result = uriCache.get(base);
/*      */     
/*  675 */     if (result == null) {
/*      */       
/*  677 */       result = parseIntoURI(base);
/*  678 */       uriCache.put(base, result);
/*      */     } 
/*      */     
/*  681 */     if (fragment != null)
/*      */     {
/*  683 */       result = result.appendFragment(fragment);
/*      */     }
/*  685 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static URI parseIntoURI(String uri) {
/*  691 */     boolean hierarchical = true;
/*  692 */     String scheme = null;
/*  693 */     String authority = null;
/*  694 */     String device = null;
/*  695 */     boolean absolutePath = false;
/*  696 */     String[] segments = NO_SEGMENTS;
/*  697 */     String query = null;
/*  698 */     String fragment = null;
/*      */     
/*  700 */     int i = 0;
/*  701 */     int j = find(uri, i, MAJOR_SEPARATOR_HI, MAJOR_SEPARATOR_LO);
/*      */     
/*  703 */     if (j < uri.length() && uri.charAt(j) == ':') {
/*      */       
/*  705 */       scheme = uri.substring(i, j);
/*  706 */       i = j + 1;
/*      */     } 
/*      */     
/*  709 */     boolean archiveScheme = isArchiveScheme(scheme);
/*  710 */     if (archiveScheme) {
/*      */       
/*  712 */       j = uri.lastIndexOf("!/");
/*  713 */       if (j == -1)
/*      */       {
/*  715 */         throw new IllegalArgumentException("no archive separator");
/*      */       }
/*  717 */       hierarchical = true;
/*  718 */       authority = uri.substring(i, ++j);
/*  719 */       i = j;
/*      */     }
/*  721 */     else if (uri.startsWith("//", i)) {
/*      */       
/*  723 */       i += "//".length();
/*  724 */       j = find(uri, i, SEGMENT_END_HI, SEGMENT_END_LO);
/*  725 */       authority = uri.substring(i, j);
/*  726 */       i = j;
/*      */     }
/*  728 */     else if (scheme != null && (
/*  729 */       i == uri.length() || uri.charAt(i) != '/')) {
/*      */       
/*  731 */       hierarchical = false;
/*  732 */       j = uri.indexOf('#', i);
/*  733 */       if (j == -1) j = uri.length(); 
/*  734 */       authority = uri.substring(i, j);
/*  735 */       i = j;
/*      */     } 
/*      */     
/*  738 */     if (!archiveScheme && i < uri.length() && uri.charAt(i) == '/') {
/*      */       
/*  740 */       j = find(uri, i + 1, SEGMENT_END_HI, SEGMENT_END_LO);
/*  741 */       String s = uri.substring(i + 1, j);
/*      */       
/*  743 */       if (s.length() > 0 && s.charAt(s.length() - 1) == ':') {
/*      */         
/*  745 */         device = s;
/*  746 */         i = j;
/*      */       } 
/*      */     } 
/*      */     
/*  750 */     if (i < uri.length() && uri.charAt(i) == '/') {
/*      */       
/*  752 */       i++;
/*  753 */       absolutePath = true;
/*      */     } 
/*      */     
/*  756 */     if (segmentsRemain(uri, i)) {
/*      */       
/*  758 */       List<String> segmentList = new ArrayList<String>();
/*      */       
/*  760 */       while (segmentsRemain(uri, i)) {
/*      */         
/*  762 */         j = find(uri, i, SEGMENT_END_HI, SEGMENT_END_LO);
/*  763 */         segmentList.add(uri.substring(i, j));
/*  764 */         i = j;
/*      */         
/*  766 */         if (i < uri.length() && uri.charAt(i) == '/')
/*      */         {
/*  768 */           if (!segmentsRemain(uri, ++i)) segmentList.add(""); 
/*      */         }
/*      */       } 
/*  771 */       segments = new String[segmentList.size()];
/*  772 */       segmentList.toArray(segments);
/*      */     } 
/*      */     
/*  775 */     if (i < uri.length() && uri.charAt(i) == '?') {
/*      */       
/*  777 */       j = uri.indexOf('#', ++i);
/*  778 */       if (j == -1) j = uri.length(); 
/*  779 */       query = uri.substring(i, j);
/*  780 */       i = j;
/*      */     } 
/*      */     
/*  783 */     if (i < uri.length())
/*      */     {
/*  785 */       fragment = uri.substring(++i);
/*      */     }
/*      */     
/*  788 */     validateURI(hierarchical, scheme, authority, device, absolutePath, segments, query, fragment);
/*  789 */     return new URI(hierarchical, scheme, authority, device, absolutePath, segments, query, fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean segmentsRemain(String uri, int i) {
/*  796 */     if (i < uri.length() && uri.charAt(i) != '?' && 
/*  797 */       uri.charAt(i) != '#') return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int find(String s, int i, long highBitmask, long lowBitmask) {
/*  807 */     int len = s.length();
/*  808 */     if (i >= len) return len;
/*      */     
/*  810 */     for (i = (i > 0) ? i : 0; i < len; i++) {
/*      */       
/*  812 */       if (matches(s.charAt(i), highBitmask, lowBitmask))
/*      */         break; 
/*  814 */     }  return i;
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
/*      */   public static URI createFileURI(String pathName) {
/*  843 */     File file = new File(pathName);
/*  844 */     String uri = (File.separatorChar != '/') ? pathName.replace(File.separatorChar, '/') : pathName;
/*  845 */     uri = encode(uri, PATH_CHAR_HI, PATH_CHAR_LO, false);
/*  846 */     if (file.isAbsolute()) {
/*      */       
/*  848 */       URI uRI = createURI(String.valueOf((uri.charAt(0) == '/') ? "file:" : "file:/") + uri);
/*  849 */       return uRI;
/*      */     } 
/*      */ 
/*      */     
/*  853 */     URI result = createURI(uri);
/*  854 */     if (result.scheme() != null)
/*      */     {
/*  856 */       throw new IllegalArgumentException("invalid relative pathName: " + pathName);
/*      */     }
/*  858 */     return result;
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
/*      */   @Deprecated
/*      */   public static URI createPlatformResourceURI(String pathName) {
/*  896 */     return createPlatformResourceURI(pathName, ENCODE_PLATFORM_RESOURCE_URIS);
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
/*      */   public static URI createPlatformResourceURI(String pathName, boolean encode) {
/*  934 */     return createPlatformURI("platform:/resource", "platform:/resource/", pathName, encode);
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
/*      */   public static URI createPlatformPluginURI(String pathName, boolean encode) {
/*  972 */     return createPlatformURI("platform:/plugin", "platform:/plugin/", pathName, encode);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static URI createPlatformURI(String unrootedBase, String rootedBase, String pathName, boolean encode) {
/*  978 */     if (File.separatorChar != '/')
/*      */     {
/*  980 */       pathName = pathName.replace(File.separatorChar, '/');
/*      */     }
/*      */     
/*  983 */     if (encode)
/*      */     {
/*  985 */       pathName = encode(pathName, PATH_CHAR_HI, PATH_CHAR_LO, false);
/*      */     }
/*  987 */     URI result = createURI(String.valueOf((pathName.charAt(0) == '/') ? unrootedBase : rootedBase) + pathName);
/*  988 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private URI(boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query, String fragment) {
/*  996 */     int hashCode = 0;
/*      */ 
/*      */     
/*  999 */     if (scheme != null)
/*      */     {
/* 1001 */       hashCode ^= scheme.toLowerCase().hashCode();
/*      */     }
/* 1003 */     if (authority != null)
/*      */     {
/* 1005 */       hashCode ^= authority.hashCode();
/*      */     }
/*      */     
/* 1008 */     if (device != null)
/*      */     {
/* 1010 */       hashCode ^= device.hashCode();
/*      */     }
/*      */     
/* 1013 */     if (query != null)
/*      */     {
/* 1015 */       hashCode ^= query.hashCode();
/*      */     }
/*      */     
/* 1018 */     if (fragment != null)
/*      */     {
/* 1020 */       hashCode ^= fragment.hashCode();
/*      */     }
/*      */ 
/*      */     
/* 1024 */     for (int i = 0, len = segments.length; i < len; i++)
/*      */     {
/* 1026 */       hashCode ^= segments[i].hashCode();
/*      */     }
/*      */ 
/*      */     
/* 1030 */     if (hierarchical) {
/*      */       
/* 1032 */       hashCode |= 0x100;
/*      */     }
/*      */     else {
/*      */       
/* 1036 */       hashCode &= 0xFFFFFEFF;
/*      */     } 
/* 1038 */     if (absolutePath) {
/*      */       
/* 1040 */       hashCode |= 0x10;
/*      */     }
/*      */     else {
/*      */       
/* 1044 */       hashCode &= 0xFFFFFFEF;
/*      */     } 
/* 1046 */     this.hashCode = hashCode;
/*      */     
/* 1048 */     this.scheme = (scheme == null) ? null : scheme.intern();
/* 1049 */     this.authority = authority;
/* 1050 */     this.device = device;
/* 1051 */     this.segments = segments;
/* 1052 */     this.query = query;
/* 1053 */     this.fragment = fragment;
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
/*      */   private static void validateURI(boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query, String fragment) {
/* 1067 */     if (!validScheme(scheme))
/*      */     {
/* 1069 */       throw new IllegalArgumentException("invalid scheme: " + scheme);
/*      */     }
/* 1071 */     if (!hierarchical && !validOpaquePart(authority))
/*      */     {
/* 1073 */       throw new IllegalArgumentException("invalid opaquePart: " + authority);
/*      */     }
/* 1075 */     if (hierarchical && !isArchiveScheme(scheme) && !validAuthority(authority))
/*      */     {
/* 1077 */       throw new IllegalArgumentException("invalid authority: " + authority);
/*      */     }
/* 1079 */     if (hierarchical && isArchiveScheme(scheme) && !validArchiveAuthority(authority))
/*      */     {
/* 1081 */       throw new IllegalArgumentException("invalid authority: " + authority);
/*      */     }
/* 1083 */     if (!validDevice(device))
/*      */     {
/* 1085 */       throw new IllegalArgumentException("invalid device: " + device);
/*      */     }
/* 1087 */     if (!validSegments(segments)) {
/*      */       
/* 1089 */       String s = (segments == null) ? "invalid segments: null" : (
/* 1090 */         "invalid segment: " + firstInvalidSegment(segments));
/* 1091 */       throw new IllegalArgumentException(s);
/*      */     } 
/* 1093 */     if (!validQuery(query))
/*      */     {
/* 1095 */       throw new IllegalArgumentException("invalid query: " + query);
/*      */     }
/* 1097 */     if (!validFragment(fragment))
/*      */     {
/* 1099 */       throw new IllegalArgumentException("invalid fragment: " + fragment);
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
/*      */   public static boolean validScheme(String value) {
/* 1115 */     return !(value != null && contains(value, MAJOR_SEPARATOR_HI, MAJOR_SEPARATOR_LO));
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
/*      */   public static boolean validOpaquePart(String value) {
/* 1138 */     if (value != null && value.indexOf('#') == -1 && 
/* 1139 */       value.length() > 0 && value.charAt(0) != '/') return true;
/*      */     
/*      */     return false;
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
/*      */   public static boolean validAuthority(String value) {
/* 1159 */     return !(value != null && contains(value, SEGMENT_END_HI, SEGMENT_END_LO));
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
/*      */   public static boolean validArchiveAuthority(String value) {
/* 1178 */     if (value != null && value.length() > 0 && 
/* 1179 */       value.charAt(value.length() - 1) == '!') {
/*      */       
/*      */       try {
/*      */         
/* 1183 */         URI archiveURI = createURI(value.substring(0, value.length() - 1));
/* 1184 */         return !archiveURI.hasFragment();
/*      */       }
/* 1186 */       catch (IllegalArgumentException illegalArgumentException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1191 */     return false;
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
/*      */   @Deprecated
/*      */   public static boolean validJarAuthority(String value) {
/* 1207 */     return validArchiveAuthority(value);
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
/*      */   public static boolean validDevice(String value) {
/* 1220 */     if (value == null) return true; 
/* 1221 */     int len = value.length();
/* 1222 */     if (len > 0 && value.charAt(len - 1) == ':' && 
/* 1223 */       !contains(value, SEGMENT_END_HI, SEGMENT_END_LO)) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean validSegment(String value) {
/* 1235 */     return (value != null && !contains(value, SEGMENT_END_HI, SEGMENT_END_LO));
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
/*      */   public static boolean validSegments(String[] value) {
/* 1252 */     if (value == null) return false; 
/* 1253 */     for (int i = 0, len = value.length; i < len; i++) {
/*      */       
/* 1255 */       if (!validSegment(value[i])) return false; 
/*      */     } 
/* 1257 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String firstInvalidSegment(String[] value) {
/* 1265 */     if (value == null) return null; 
/* 1266 */     for (int i = 0, len = value.length; i < len; i++) {
/*      */       
/* 1268 */       if (!validSegment(value[i])) return value[i]; 
/*      */     } 
/* 1270 */     return null;
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
/*      */   public static boolean validQuery(String value) {
/* 1282 */     return !(value != null && value.indexOf('#') != -1);
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
/*      */   public static boolean validFragment(String value) {
/* 1297 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean contains(String s, long highBitmask, long lowBitmask) {
/* 1308 */     for (int i = 0, len = s.length(); i < len; i++) {
/*      */       
/* 1310 */       if (matches(s.charAt(i), highBitmask, lowBitmask)) return true; 
/*      */     } 
/* 1312 */     return false;
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
/*      */   public boolean isRelative() {
/* 1348 */     return (this.scheme == null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHierarchical() {
/* 1357 */     return ((this.hashCode & 0x100) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAuthority() {
/* 1366 */     return (isHierarchical() && this.authority != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasOpaquePart() {
/* 1376 */     return !isHierarchical();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDevice() {
/* 1386 */     return (this.device != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasPath() {
/* 1397 */     return !(!hasAbsolutePath() && (this.authority != null || this.device != null));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAbsolutePath() {
/* 1408 */     return ((this.hashCode & 0x10) != 0);
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
/*      */   public boolean hasRelativePath() {
/* 1420 */     return (this.authority == null && this.device == null && !hasAbsolutePath());
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
/*      */   public boolean hasEmptyPath() {
/* 1435 */     if (this.authority == null && this.device == null && !hasAbsolutePath() && 
/* 1436 */       this.segments.length == 0) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasQuery() {
/* 1446 */     return (this.query != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFragment() {
/* 1455 */     return (this.fragment != null);
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
/*      */   public boolean isCurrentDocumentReference() {
/* 1468 */     if (this.authority == null && this.device == null && !hasAbsolutePath() && 
/* 1469 */       this.segments.length == 0 && this.query == null) return true;
/*      */     
/*      */     return false;
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
/*      */   public boolean isEmpty() {
/* 1483 */     if (this.authority == null && this.device == null && !hasAbsolutePath() && 
/* 1484 */       this.segments.length == 0 && this.query == null && this.fragment == null) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFile() {
/* 1495 */     if (isHierarchical() && ((
/* 1496 */       isRelative() && !hasQuery()) || "file".equalsIgnoreCase(this.scheme))) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlatform() {
/* 1507 */     if (isHierarchical() && !hasAuthority() && segmentCount() >= 2 && 
/* 1508 */       "platform".equalsIgnoreCase(this.scheme)) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlatformResource() {
/* 1520 */     return (isPlatform() && "resource".equals(this.segments[0]));
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
/*      */   public boolean isPlatformPlugin() {
/* 1532 */     return (isPlatform() && "plugin".equals(this.segments[0]));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isArchive() {
/* 1542 */     return isArchiveScheme(this.scheme);
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
/*      */   public static boolean isArchiveScheme(String value) {
/* 1556 */     return (value != null && archiveSchemes.contains(value.toLowerCase()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1565 */     return this.hashCode;
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
/*      */   public boolean equals(Object object) {
/* 1579 */     if (this == object) return true; 
/* 1580 */     if (!(object instanceof URI)) return false; 
/* 1581 */     URI uri = (URI)object;
/*      */     
/* 1583 */     if (this.hashCode == uri.hashCode() && 
/* 1584 */       equals(this.scheme, uri.scheme(), true) && 
/* 1585 */       equals(this.authority, isHierarchical() ? uri.authority() : uri.opaquePart()) && 
/* 1586 */       equals(this.device, uri.device()) && 
/* 1587 */       equals(this.query, uri.query()) && 
/* 1588 */       equals(this.fragment, uri.fragment()) && 
/* 1589 */       segmentsEqual(uri)) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean segmentsEqual(URI uri) {
/* 1596 */     if (this.segments.length != uri.segmentCount()) return false; 
/* 1597 */     for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */       
/* 1599 */       if (!this.segments[i].equals(uri.segment(i))) return false; 
/*      */     } 
/* 1601 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean equals(Object o1, Object o2) {
/* 1608 */     return (o1 == null) ? ((o2 == null)) : o1.equals(o2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean equals(String s1, String s2, boolean ignoreCase) {
/* 1615 */     return (s1 == null) ? ((s2 == null)) : (
/* 1616 */       ignoreCase ? s1.equalsIgnoreCase(s2) : s1.equals(s2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String scheme() {
/* 1625 */     return this.scheme;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String opaquePart() {
/* 1634 */     return isHierarchical() ? null : this.authority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String authority() {
/* 1643 */     return isHierarchical() ? this.authority : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String userInfo() {
/* 1652 */     if (!hasAuthority()) return null;
/*      */     
/* 1654 */     int i = this.authority.indexOf('@');
/* 1655 */     return (i < 0) ? null : this.authority.substring(0, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String host() {
/* 1664 */     if (!hasAuthority()) return null;
/*      */     
/* 1666 */     int i = this.authority.indexOf('@');
/* 1667 */     int j = this.authority.indexOf(':');
/* 1668 */     return (j < 0) ? this.authority.substring(i + 1) : this.authority.substring(i + 1, j);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String port() {
/* 1677 */     if (!hasAuthority()) return null;
/*      */     
/* 1679 */     int i = this.authority.indexOf(':');
/* 1680 */     return (i < 0) ? null : this.authority.substring(i + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String device() {
/* 1689 */     return this.device;
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
/*      */   public String[] segments() {
/* 1701 */     return (String[])this.segments.clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> segmentsList() {
/* 1710 */     return Collections.unmodifiableList(Arrays.asList(this.segments));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int segmentCount() {
/* 1719 */     return this.segments.length;
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
/*      */   public String segment(int i) {
/* 1731 */     return this.segments[i];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String lastSegment() {
/* 1739 */     int len = this.segments.length;
/* 1740 */     if (len == 0) return null; 
/* 1741 */     return this.segments[len - 1];
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
/*      */   public String path() {
/* 1754 */     if (!hasPath()) return null;
/*      */     
/* 1756 */     StringBuffer result = new StringBuffer();
/* 1757 */     if (hasAbsolutePath()) result.append('/');
/*      */     
/* 1759 */     for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */       
/* 1761 */       if (i != 0) result.append('/'); 
/* 1762 */       result.append(this.segments[i]);
/*      */     } 
/* 1764 */     return result.toString();
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
/*      */   public String devicePath() {
/* 1787 */     if (!hasPath()) return null;
/*      */     
/* 1789 */     StringBuffer result = new StringBuffer();
/*      */     
/* 1791 */     if (hasAuthority()) {
/*      */       
/* 1793 */       if (!isArchive()) result.append("//"); 
/* 1794 */       result.append(this.authority);
/*      */       
/* 1796 */       if (hasDevice()) result.append('/');
/*      */     
/*      */     } 
/* 1799 */     if (hasDevice()) result.append(this.device); 
/* 1800 */     if (hasAbsolutePath()) result.append('/');
/*      */     
/* 1802 */     for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */       
/* 1804 */       if (i != 0) result.append('/'); 
/* 1805 */       result.append(this.segments[i]);
/*      */     } 
/* 1807 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String query() {
/* 1816 */     return this.query;
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
/*      */   public URI appendQuery(String query) {
/* 1829 */     if (!validQuery(query))
/*      */     {
/* 1831 */       throw new IllegalArgumentException(
/* 1832 */           "invalid query portion: " + query);
/*      */     }
/* 1834 */     return new URI(isHierarchical(), this.scheme, this.authority, this.device, hasAbsolutePath(), this.segments, query, this.fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URI trimQuery() {
/* 1843 */     if (this.query == null)
/*      */     {
/* 1845 */       return this;
/*      */     }
/*      */ 
/*      */     
/* 1849 */     return new URI(isHierarchical(), this.scheme, this.authority, this.device, hasAbsolutePath(), this.segments, null, this.fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String fragment() {
/* 1859 */     return this.fragment;
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
/*      */   public URI appendFragment(String fragment) {
/* 1871 */     if (!validFragment(fragment))
/*      */     {
/* 1873 */       throw new IllegalArgumentException(
/* 1874 */           "invalid fragment portion: " + fragment);
/*      */     }
/* 1876 */     URI result = new URI(isHierarchical(), this.scheme, this.authority, this.device, hasAbsolutePath(), this.segments, this.query, fragment);
/*      */     
/* 1878 */     if (!hasFragment())
/*      */     {
/* 1880 */       result.cachedTrimFragment = this;
/*      */     }
/* 1882 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URI trimFragment() {
/* 1891 */     if (this.fragment == null)
/*      */     {
/* 1893 */       return this;
/*      */     }
/* 1895 */     if (this.cachedTrimFragment == null)
/*      */     {
/* 1897 */       this.cachedTrimFragment = new URI(isHierarchical(), this.scheme, this.authority, this.device, hasAbsolutePath(), this.segments, this.query, null);
/*      */     }
/*      */     
/* 1900 */     return this.cachedTrimFragment;
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
/*      */   public URI resolve(URI base) {
/* 1923 */     return resolve(base, true);
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
/*      */   public URI resolve(URI base, boolean preserveRootParents) {
/* 1948 */     if (!base.isHierarchical() || base.isRelative())
/*      */     {
/* 1950 */       throw new IllegalArgumentException(
/* 1951 */           "resolve against non-hierarchical or relative base");
/*      */     }
/*      */ 
/*      */     
/* 1955 */     if (!isRelative()) return this;
/*      */ 
/*      */ 
/*      */     
/* 1959 */     String newAuthority = this.authority;
/* 1960 */     String newDevice = this.device;
/* 1961 */     boolean newAbsolutePath = hasAbsolutePath();
/* 1962 */     String[] newSegments = this.segments;
/* 1963 */     String newQuery = this.query;
/*      */ 
/*      */ 
/*      */     
/* 1967 */     if (this.authority == null) {
/*      */ 
/*      */       
/* 1970 */       newAuthority = base.authority();
/*      */       
/* 1972 */       if (this.device == null) {
/*      */ 
/*      */         
/* 1975 */         newDevice = base.device();
/*      */         
/* 1977 */         if (hasEmptyPath() && this.query == null) {
/*      */ 
/*      */           
/* 1980 */           newAbsolutePath = base.hasAbsolutePath();
/* 1981 */           newSegments = base.segments();
/* 1982 */           newQuery = base.query();
/*      */         }
/* 1984 */         else if (hasRelativePath()) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1989 */           newAbsolutePath = !(!base.hasAbsolutePath() && hasEmptyPath());
/* 1990 */           newSegments = newAbsolutePath ? mergePath(base, preserveRootParents) : 
/* 1991 */             NO_SEGMENTS;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2001 */     return new URI(true, base.scheme(), newAuthority, newDevice, 
/* 2002 */         newAbsolutePath, newSegments, newQuery, this.fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String[] mergePath(URI base, boolean preserveRootParents) {
/* 2010 */     if (base.hasRelativePath())
/*      */     {
/* 2012 */       throw new IllegalArgumentException("merge against relative path");
/*      */     }
/* 2014 */     if (!hasRelativePath())
/*      */     {
/* 2016 */       throw new IllegalStateException("merge non-relative path");
/*      */     }
/*      */     
/* 2019 */     int baseSegmentCount = base.segmentCount();
/* 2020 */     int segmentCount = this.segments.length;
/* 2021 */     String[] stack = new String[baseSegmentCount + segmentCount];
/* 2022 */     int sp = 0;
/*      */ 
/*      */     
/*      */     int i;
/*      */     
/* 2027 */     for (i = 0; i < baseSegmentCount - 1; i++)
/*      */     {
/* 2029 */       sp = accumulate(stack, sp, base.segment(i), preserveRootParents);
/*      */     }
/*      */     
/* 2032 */     for (i = 0; i < segmentCount; i++)
/*      */     {
/* 2034 */       sp = accumulate(stack, sp, this.segments[i], preserveRootParents);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2040 */     if (sp > 0 && (segmentCount == 0 || 
/* 2041 */       "".equals(this.segments[segmentCount - 1]) || 
/* 2042 */       "..".equals(this.segments[segmentCount - 1]) || 
/* 2043 */       ".".equals(this.segments[segmentCount - 1])))
/*      */     {
/* 2045 */       stack[sp++] = "";
/*      */     }
/*      */ 
/*      */     
/* 2049 */     String[] result = new String[sp];
/* 2050 */     System.arraycopy(stack, 0, result, 0, sp);
/* 2051 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int accumulate(String[] stack, int sp, String segment, boolean preserveRootParents) {
/* 2059 */     if ("..".equals(segment)) {
/*      */       
/* 2061 */       if (sp == 0)
/*      */       
/*      */       { 
/*      */         
/* 2065 */         if (preserveRootParents) stack[sp++] = segment;
/*      */         
/*      */         
/*      */         
/*      */          }
/*      */       
/* 2071 */       else if ("..".equals(stack[sp - 1])) { stack[sp++] = segment; }
/* 2072 */       else { sp--; }
/*      */ 
/*      */     
/* 2075 */     } else if (!"".equals(segment) && !".".equals(segment)) {
/*      */ 
/*      */       
/* 2078 */       stack[sp++] = segment;
/*      */     } 
/* 2080 */     return sp;
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
/*      */   public URI deresolve(URI base) {
/* 2093 */     return deresolve(base, true, false, true);
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
/*      */   public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath) {
/* 2118 */     if (!base.isHierarchical() || base.isRelative()) return this;
/*      */     
/* 2120 */     if (isRelative()) return this;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2126 */     if (!this.scheme.equalsIgnoreCase(base.scheme())) return this;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2131 */     if (!isHierarchical()) return this;
/*      */     
/* 2133 */     String newAuthority = this.authority;
/* 2134 */     String newDevice = this.device;
/* 2135 */     boolean newAbsolutePath = hasAbsolutePath();
/* 2136 */     String[] newSegments = this.segments;
/* 2137 */     String newQuery = this.query;
/*      */     
/* 2139 */     if (equals(this.authority, base.authority()) && (
/* 2140 */       hasDevice() || hasPath() || (!base.hasDevice() && !base.hasPath()))) {
/*      */ 
/*      */       
/* 2143 */       newAuthority = null;
/*      */       
/* 2145 */       if (equals(this.device, base.device()) && (hasPath() || !base.hasPath())) {
/*      */ 
/*      */         
/* 2148 */         newDevice = null;
/*      */ 
/*      */ 
/*      */         
/* 2152 */         if (anyRelPath || shorterRelPath)
/*      */         {
/*      */ 
/*      */           
/* 2156 */           if (hasPath() == base.hasPath() && segmentsEqual(base) && 
/* 2157 */             equals(this.query, base.query())) {
/*      */ 
/*      */             
/* 2160 */             newAbsolutePath = false;
/* 2161 */             newSegments = NO_SEGMENTS;
/* 2162 */             newQuery = null;
/*      */           }
/* 2164 */           else if (!hasPath() && !base.hasPath()) {
/*      */ 
/*      */             
/* 2167 */             newAbsolutePath = false;
/* 2168 */             newSegments = NO_SEGMENTS;
/*      */           
/*      */           }
/* 2171 */           else if (!hasCollapsableSegments(preserveRootParents)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2178 */             String[] rel = findRelativePath(base, preserveRootParents);
/* 2179 */             if (anyRelPath || this.segments.length > rel.length) {
/*      */ 
/*      */               
/* 2182 */               newAbsolutePath = false;
/* 2183 */               newSegments = rel;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2194 */     return new URI(true, null, newAuthority, newDevice, newAbsolutePath, 
/* 2195 */         newSegments, newQuery, this.fragment);
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
/*      */   private boolean hasCollapsableSegments(boolean preserveRootParents) {
/* 2207 */     if (hasRelativePath())
/*      */     {
/* 2209 */       throw new IllegalStateException("test collapsability of relative path");
/*      */     }
/*      */     
/* 2212 */     for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */       
/* 2214 */       String segment = this.segments[i];
/* 2215 */       if ((i < len - 1 && "".equals(segment)) || 
/* 2216 */         ".".equals(segment) || (
/* 2217 */         "..".equals(segment) && (
/* 2218 */         !preserveRootParents || (
/* 2219 */         i != 0 && !"..".equals(this.segments[i - 1])))))
/*      */       {
/* 2221 */         return true;
/*      */       }
/*      */     } 
/* 2224 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String[] findRelativePath(URI base, boolean preserveRootParents) {
/* 2232 */     if (base.hasRelativePath())
/*      */     {
/* 2234 */       throw new IllegalArgumentException(
/* 2235 */           "find relative path against base with relative path");
/*      */     }
/* 2237 */     if (!hasAbsolutePath())
/*      */     {
/* 2239 */       throw new IllegalArgumentException(
/* 2240 */           "find relative path of non-absolute path");
/*      */     }
/*      */ 
/*      */     
/* 2244 */     String[] startPath = base.collapseSegments(preserveRootParents);
/* 2245 */     String[] endPath = this.segments;
/*      */ 
/*      */     
/* 2248 */     int startCount = (startPath.length > 0) ? (startPath.length - 1) : 0;
/* 2249 */     int endCount = endPath.length;
/*      */ 
/*      */     
/* 2252 */     int diff = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2259 */     int count = (startCount < endCount) ? startCount : (endCount - 1);
/* 2260 */     for (; diff < count && startPath[diff].equals(endPath[diff]); diff++);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2265 */     int upCount = startCount - diff;
/* 2266 */     int downCount = endCount - diff;
/*      */ 
/*      */ 
/*      */     
/* 2270 */     if (downCount == 1 && "".equals(endPath[endCount - 1]))
/*      */     {
/* 2272 */       downCount = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2277 */     if (upCount + downCount == 0) {
/*      */       
/* 2279 */       if (this.query == null) return new String[] { "." }; 
/* 2280 */       return NO_SEGMENTS;
/*      */     } 
/*      */ 
/*      */     
/* 2284 */     String[] result = new String[upCount + downCount];
/* 2285 */     Arrays.fill((Object[])result, 0, upCount, "..");
/* 2286 */     System.arraycopy(endPath, diff, result, upCount, downCount);
/* 2287 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String[] collapseSegments(boolean preserveRootParents) {
/* 2295 */     if (hasRelativePath())
/*      */     {
/* 2297 */       throw new IllegalStateException("collapse relative path");
/*      */     }
/*      */     
/* 2300 */     if (!hasCollapsableSegments(preserveRootParents)) return segments();
/*      */ 
/*      */     
/* 2303 */     int segmentCount = this.segments.length;
/* 2304 */     String[] stack = new String[segmentCount];
/* 2305 */     int sp = 0;
/*      */     
/* 2307 */     for (int i = 0; i < segmentCount; i++)
/*      */     {
/* 2309 */       sp = accumulate(stack, sp, this.segments[i], preserveRootParents);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2314 */     if (sp > 0 && ("".equals(this.segments[segmentCount - 1]) || 
/* 2315 */       "..".equals(this.segments[segmentCount - 1]) || 
/* 2316 */       ".".equals(this.segments[segmentCount - 1])))
/*      */     {
/* 2318 */       stack[sp++] = "";
/*      */     }
/*      */ 
/*      */     
/* 2322 */     String[] result = new String[sp];
/* 2323 */     System.arraycopy(stack, 0, result, 0, sp);
/* 2324 */     return result;
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
/*      */   public String toString() {
/* 2345 */     if (this.cachedToString == null) {
/*      */       
/* 2347 */       StringBuffer result = new StringBuffer();
/* 2348 */       if (!isRelative()) {
/*      */         
/* 2350 */         result.append(this.scheme);
/* 2351 */         result.append(':');
/*      */       } 
/*      */       
/* 2354 */       if (isHierarchical()) {
/*      */         
/* 2356 */         if (hasAuthority()) {
/*      */           
/* 2358 */           if (!isArchive()) result.append("//"); 
/* 2359 */           result.append(this.authority);
/*      */         } 
/*      */         
/* 2362 */         if (hasDevice()) {
/*      */           
/* 2364 */           result.append('/');
/* 2365 */           result.append(this.device);
/*      */         } 
/*      */         
/* 2368 */         if (hasAbsolutePath()) result.append('/');
/*      */         
/* 2370 */         for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */           
/* 2372 */           if (i != 0) result.append('/'); 
/* 2373 */           result.append(this.segments[i]);
/*      */         } 
/*      */         
/* 2376 */         if (hasQuery())
/*      */         {
/* 2378 */           result.append('?');
/* 2379 */           result.append(this.query);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2384 */         result.append(this.authority);
/*      */       } 
/*      */       
/* 2387 */       if (hasFragment()) {
/*      */         
/* 2389 */         result.append('#');
/* 2390 */         result.append(this.fragment);
/*      */       } 
/* 2392 */       this.cachedToString = result.toString();
/*      */     } 
/* 2394 */     return this.cachedToString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String toString(boolean includeSimpleForm) {
/* 2401 */     StringBuffer result = new StringBuffer();
/* 2402 */     if (includeSimpleForm) result.append(toString()); 
/* 2403 */     result.append("\n hierarchical: ");
/* 2404 */     result.append(isHierarchical());
/* 2405 */     result.append("\n       scheme: ");
/* 2406 */     result.append(this.scheme);
/* 2407 */     result.append("\n    authority: ");
/* 2408 */     result.append(this.authority);
/* 2409 */     result.append("\n       device: ");
/* 2410 */     result.append(this.device);
/* 2411 */     result.append("\n absolutePath: ");
/* 2412 */     result.append(hasAbsolutePath());
/* 2413 */     result.append("\n     segments: ");
/* 2414 */     if (this.segments.length == 0) result.append("<empty>"); 
/* 2415 */     for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */       
/* 2417 */       if (i > 0) result.append("\n               "); 
/* 2418 */       result.append(this.segments[i]);
/*      */     } 
/* 2420 */     result.append("\n        query: ");
/* 2421 */     result.append(this.query);
/* 2422 */     result.append("\n     fragment: ");
/* 2423 */     result.append(this.fragment);
/* 2424 */     return result.toString();
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
/*      */   public String toFileString() {
/* 2445 */     if (!isFile()) return null;
/*      */     
/* 2447 */     StringBuffer result = new StringBuffer();
/* 2448 */     char separator = File.separatorChar;
/*      */     
/* 2450 */     if (hasAuthority()) {
/*      */       
/* 2452 */       result.append(separator);
/* 2453 */       result.append(separator);
/* 2454 */       result.append(this.authority);
/*      */       
/* 2456 */       if (hasDevice()) result.append(separator);
/*      */     
/*      */     } 
/* 2459 */     if (hasDevice()) result.append(this.device); 
/* 2460 */     if (hasAbsolutePath()) result.append(separator);
/*      */     
/* 2462 */     for (int i = 0, len = this.segments.length; i < len; i++) {
/*      */       
/* 2464 */       if (i != 0) result.append(separator); 
/* 2465 */       result.append(this.segments[i]);
/*      */     } 
/*      */     
/* 2468 */     return decode(result.toString());
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
/*      */   public String toPlatformString(boolean decode) {
/* 2481 */     if (isPlatform()) {
/*      */       
/* 2483 */       StringBuffer result = new StringBuffer();
/* 2484 */       for (int i = 1, len = this.segments.length; i < len; i++)
/*      */       {
/* 2486 */         result.append('/').append(decode ? decode(this.segments[i]) : this.segments[i]);
/*      */       }
/* 2488 */       return result.toString();
/*      */     } 
/* 2490 */     return null;
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
/*      */   public URI appendSegment(String segment) {
/* 2504 */     if (!validSegment(segment))
/*      */     {
/* 2506 */       throw new IllegalArgumentException("invalid segment: " + segment);
/*      */     }
/*      */     
/* 2509 */     if (!isHierarchical()) return this;
/*      */ 
/*      */     
/* 2512 */     boolean newAbsolutePath = !hasRelativePath();
/*      */     
/* 2514 */     int len = this.segments.length;
/* 2515 */     String[] newSegments = new String[len + 1];
/* 2516 */     System.arraycopy(this.segments, 0, newSegments, 0, len);
/* 2517 */     newSegments[len] = segment;
/*      */     
/* 2519 */     return new URI(true, this.scheme, this.authority, this.device, newAbsolutePath, 
/* 2520 */         newSegments, this.query, this.fragment);
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
/*      */   public URI appendSegments(String[] segments) {
/* 2539 */     if (!validSegments(segments)) {
/*      */       
/* 2541 */       String s = (segments == null) ? "invalid segments: null" : (
/* 2542 */         "invalid segment: " + firstInvalidSegment(segments));
/* 2543 */       throw new IllegalArgumentException(s);
/*      */     } 
/*      */     
/* 2546 */     if (!isHierarchical()) return this;
/*      */ 
/*      */     
/* 2549 */     boolean newAbsolutePath = !hasRelativePath();
/*      */     
/* 2551 */     int len = this.segments.length;
/* 2552 */     int segmentsCount = segments.length;
/* 2553 */     String[] newSegments = new String[len + segmentsCount];
/* 2554 */     System.arraycopy(this.segments, 0, newSegments, 0, len);
/* 2555 */     System.arraycopy(segments, 0, newSegments, len, segmentsCount);
/*      */     
/* 2557 */     return new URI(true, this.scheme, this.authority, this.device, newAbsolutePath, 
/* 2558 */         newSegments, this.query, this.fragment);
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
/*      */   public URI trimSegments(int i) {
/* 2577 */     if (!isHierarchical() || i < 1) return this;
/*      */     
/* 2579 */     String[] newSegments = NO_SEGMENTS;
/* 2580 */     int len = this.segments.length - i;
/* 2581 */     if (len > 0) {
/*      */       
/* 2583 */       newSegments = new String[len];
/* 2584 */       System.arraycopy(this.segments, 0, newSegments, 0, len);
/*      */     } 
/* 2586 */     return new URI(true, this.scheme, this.authority, this.device, hasAbsolutePath(), 
/* 2587 */         newSegments, this.query, this.fragment);
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
/*      */   public boolean hasTrailingPathSeparator() {
/* 2600 */     if (this.segments.length > 0 && 
/* 2601 */       "".equals(this.segments[this.segments.length - 1])) return true;
/*      */     
/*      */     return false;
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
/*      */   public String fileExtension() {
/* 2615 */     int len = this.segments.length;
/* 2616 */     if (len == 0) return null;
/*      */     
/* 2618 */     String lastSegment = this.segments[len - 1];
/* 2619 */     int i = lastSegment.lastIndexOf('.');
/* 2620 */     return (i < 0) ? null : lastSegment.substring(i + 1);
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
/*      */   public URI appendFileExtension(String fileExtension) {
/* 2638 */     if (!validSegment(fileExtension))
/*      */     {
/* 2640 */       throw new IllegalArgumentException(
/* 2641 */           "invalid segment portion: " + fileExtension);
/*      */     }
/*      */     
/* 2644 */     int len = this.segments.length;
/* 2645 */     if (len == 0) return this;
/*      */     
/* 2647 */     String lastSegment = this.segments[len - 1];
/* 2648 */     if ("".equals(lastSegment)) return this; 
/* 2649 */     StringBuffer newLastSegment = new StringBuffer(lastSegment);
/* 2650 */     newLastSegment.append('.');
/* 2651 */     newLastSegment.append(fileExtension);
/*      */     
/* 2653 */     String[] newSegments = new String[len];
/* 2654 */     System.arraycopy(this.segments, 0, newSegments, 0, len - 1);
/* 2655 */     newSegments[len - 1] = newLastSegment.toString();
/*      */ 
/*      */     
/* 2658 */     return new URI(true, this.scheme, this.authority, this.device, hasAbsolutePath(), 
/* 2659 */         newSegments, this.query, this.fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URI trimFileExtension() {
/* 2668 */     int len = this.segments.length;
/* 2669 */     if (len == 0) return this;
/*      */     
/* 2671 */     String lastSegment = this.segments[len - 1];
/* 2672 */     int i = lastSegment.lastIndexOf('.');
/* 2673 */     if (i < 0) return this;
/*      */     
/* 2675 */     String newLastSegment = lastSegment.substring(0, i);
/* 2676 */     String[] newSegments = new String[len];
/* 2677 */     System.arraycopy(this.segments, 0, newSegments, 0, len - 1);
/* 2678 */     newSegments[len - 1] = newLastSegment;
/*      */ 
/*      */     
/* 2681 */     return new URI(true, this.scheme, this.authority, this.device, hasAbsolutePath(), 
/* 2682 */         newSegments, this.query, this.fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPrefix() {
/* 2693 */     if (isHierarchical() && this.query == null && this.fragment == null && (
/* 2694 */       hasTrailingPathSeparator() || (hasAbsolutePath() && this.segments.length == 0))) return true;
/*      */     
/*      */     return false;
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
/*      */   public URI replacePrefix(URI oldPrefix, URI newPrefix) {
/* 2713 */     if (!oldPrefix.isPrefix() || !newPrefix.isPrefix()) {
/*      */       
/* 2715 */       String which = oldPrefix.isPrefix() ? "new" : "old";
/* 2716 */       throw new IllegalArgumentException("non-prefix " + which + " value");
/*      */     } 
/*      */ 
/*      */     
/* 2720 */     String[] tailSegments = getTailSegments(oldPrefix);
/* 2721 */     if (tailSegments == null) return null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2726 */     String[] mergedSegments = tailSegments;
/* 2727 */     if (newPrefix.segmentCount() != 0) {
/*      */       
/* 2729 */       int segmentsToKeep = newPrefix.segmentCount() - 1;
/* 2730 */       mergedSegments = new String[segmentsToKeep + tailSegments.length];
/* 2731 */       System.arraycopy(newPrefix.segments(), 0, mergedSegments, 0, 
/* 2732 */           segmentsToKeep);
/*      */       
/* 2734 */       if (tailSegments.length != 0)
/*      */       {
/* 2736 */         System.arraycopy(tailSegments, 0, mergedSegments, segmentsToKeep, 
/* 2737 */             tailSegments.length);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2742 */     return new URI(true, newPrefix.scheme(), newPrefix.authority(), 
/* 2743 */         newPrefix.device(), newPrefix.hasAbsolutePath(), 
/* 2744 */         mergedSegments, this.query, this.fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String[] getTailSegments(URI prefix) {
/* 2752 */     if (!prefix.isPrefix())
/*      */     {
/* 2754 */       throw new IllegalArgumentException("non-prefix trim");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2759 */     if (!isHierarchical() || 
/* 2760 */       !equals(this.scheme, prefix.scheme(), true) || 
/* 2761 */       !equals(this.authority, prefix.authority()) || 
/* 2762 */       !equals(this.device, prefix.device()) || 
/* 2763 */       hasAbsolutePath() != prefix.hasAbsolutePath())
/*      */     {
/* 2765 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2770 */     if (prefix.segmentCount() == 0) return this.segments;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2775 */     int i = 0;
/* 2776 */     int segmentsToCompare = prefix.segmentCount() - 1;
/* 2777 */     if (this.segments.length <= segmentsToCompare) return null;
/*      */     
/* 2779 */     for (; i < segmentsToCompare; i++) {
/*      */       
/* 2781 */       if (!this.segments[i].equals(prefix.segment(i))) return null;
/*      */     
/*      */     } 
/*      */ 
/*      */     
/* 2786 */     if (i == this.segments.length - 1 && "".equals(this.segments[i]))
/*      */     {
/* 2788 */       return NO_SEGMENTS;
/*      */     }
/*      */ 
/*      */     
/* 2792 */     String[] newSegments = new String[this.segments.length - i];
/* 2793 */     System.arraycopy(this.segments, i, newSegments, 0, newSegments.length);
/* 2794 */     return newSegments;
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
/*      */   public static String encodeOpaquePart(String value, boolean ignoreEscaped) {
/* 2810 */     String result = encode(value, URIC_HI, URIC_LO, ignoreEscaped);
/* 2811 */     return (result != null && result.length() > 0 && result.charAt(0) == '/') ? (
/* 2812 */       "%2F" + result.substring(1)) : 
/* 2813 */       result;
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
/*      */   public static String encodeAuthority(String value, boolean ignoreEscaped) {
/* 2829 */     return encode(value, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO, ignoreEscaped);
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
/*      */   public static String encodeSegment(String value, boolean ignoreEscaped) {
/* 2845 */     return encode(value, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO, ignoreEscaped);
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
/*      */   public static String encodeQuery(String value, boolean ignoreEscaped) {
/* 2860 */     return encode(value, URIC_HI, URIC_LO, ignoreEscaped);
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
/*      */   public static String encodeFragment(String value, boolean ignoreEscaped) {
/* 2876 */     return encode(value, URIC_HI, URIC_LO, ignoreEscaped);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String encodeURI(String uri, boolean ignoreEscaped, int fragmentLocationStyle) {
/* 2884 */     if (uri == null) return null;
/*      */     
/* 2886 */     StringBuffer result = new StringBuffer();
/*      */     
/* 2888 */     int i = uri.indexOf(':');
/* 2889 */     if (i != -1) {
/*      */       
/* 2891 */       String scheme = uri.substring(0, i);
/* 2892 */       result.append(scheme);
/* 2893 */       result.append(':');
/*      */     } 
/*      */     
/* 2896 */     int j = 
/* 2897 */       (fragmentLocationStyle == 1) ? uri.indexOf('#') : (
/* 2898 */       (fragmentLocationStyle == 2) ? uri.lastIndexOf('#') : -1);
/*      */     
/* 2900 */     if (j != -1) {
/*      */       
/* 2902 */       String sspart = uri.substring(++i, j);
/* 2903 */       result.append(encode(sspart, URIC_HI, URIC_LO, ignoreEscaped));
/* 2904 */       result.append('#');
/*      */       
/* 2906 */       String fragment = uri.substring(++j);
/* 2907 */       result.append(encode(fragment, URIC_HI, URIC_LO, ignoreEscaped));
/*      */     }
/*      */     else {
/*      */       
/* 2911 */       String sspart = uri.substring(++i);
/* 2912 */       result.append(encode(sspart, URIC_HI, URIC_LO, ignoreEscaped));
/*      */     } 
/*      */     
/* 2915 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String encode(String value, long highBitmask, long lowBitmask, boolean ignoreEscaped) {
/* 2925 */     if (value == null) return null;
/*      */     
/* 2927 */     StringBuffer result = null;
/*      */     
/* 2929 */     for (int i = 0, len = value.length(); i < len; i++) {
/*      */       
/* 2931 */       char c = value.charAt(i);
/*      */       
/* 2933 */       if (!matches(c, highBitmask, lowBitmask) && c < ' ' && (
/* 2934 */         !ignoreEscaped || !isEscaped(value, i))) {
/*      */         
/* 2936 */         if (result == null)
/*      */         {
/* 2938 */           result = new StringBuffer(value.substring(0, i));
/*      */         }
/* 2940 */         appendEscaped(result, (byte)c);
/*      */       }
/* 2942 */       else if (result != null) {
/*      */         
/* 2944 */         result.append(c);
/*      */       } 
/*      */     } 
/* 2947 */     return (result == null) ? value : result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isEscaped(String s, int i) {
/* 2954 */     if (s.charAt(i) == '%' && s.length() > i + 2 && 
/* 2955 */       matches(s.charAt(i + 1), HEX_HI, HEX_LO) && 
/* 2956 */       matches(s.charAt(i + 2), HEX_HI, HEX_LO)) return true;
/*      */     
/*      */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void appendEscaped(StringBuffer result, byte b) {
/* 2964 */     result.append('%');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2970 */     result.append(HEX_DIGITS[b >> 4 & 0xF]);
/* 2971 */     result.append(HEX_DIGITS[b & 0xF]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String decode(String value) {
/* 2981 */     if (value == null) return null;
/*      */     
/* 2983 */     int i = value.indexOf('%');
/* 2984 */     if (i < 0)
/*      */     {
/* 2986 */       return value;
/*      */     }
/*      */ 
/*      */     
/* 2990 */     StringBuilder result = new StringBuilder(value.substring(0, i));
/* 2991 */     byte[] bytes = new byte[4];
/* 2992 */     int receivedBytes = 0;
/* 2993 */     int expectedBytes = 0;
/* 2994 */     for (int len = value.length(); i < len; i++) {
/*      */       
/* 2996 */       if (isEscaped(value, i)) {
/*      */         
/* 2998 */         char character = unescape(value.charAt(i + 1), value.charAt(i + 2));
/* 2999 */         i += 2;
/*      */         
/* 3001 */         if (expectedBytes > 0) {
/*      */           
/* 3003 */           if ((character & 0xC0) == 128)
/*      */           {
/* 3005 */             bytes[receivedBytes++] = (byte)character;
/*      */           }
/*      */           else
/*      */           {
/* 3009 */             expectedBytes = 0;
/*      */           }
/*      */         
/* 3012 */         } else if (character >= '') {
/*      */           
/* 3014 */           if ((character & 0xE0) == 192) {
/*      */             
/* 3016 */             bytes[receivedBytes++] = (byte)character;
/* 3017 */             expectedBytes = 2;
/*      */           }
/* 3019 */           else if ((character & 0xF0) == 224) {
/*      */             
/* 3021 */             bytes[receivedBytes++] = (byte)character;
/* 3022 */             expectedBytes = 3;
/*      */           }
/* 3024 */           else if ((character & 0xF8) == 240) {
/*      */             
/* 3026 */             bytes[receivedBytes++] = (byte)character;
/* 3027 */             expectedBytes = 4;
/*      */           } 
/*      */         } 
/*      */         
/* 3031 */         if (expectedBytes > 0) {
/*      */           
/* 3033 */           if (receivedBytes == expectedBytes)
/*      */           {
/* 3035 */             switch (receivedBytes) {
/*      */ 
/*      */               
/*      */               case 2:
/* 3039 */                 result.append((char)((bytes[0] & 0x1F) << 6 | bytes[1] & 0x3F));
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 3:
/* 3044 */                 result.append((char)((bytes[0] & 0xF) << 12 | (bytes[1] & 0x3F) << 6 | bytes[2] & 0x3F));
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 4:
/* 3049 */                 result.appendCodePoint((bytes[0] & 0x7) << 18 | (bytes[1] & 0x3F) << 12 | (bytes[2] & 0x3F) << 6 | bytes[3] & 0x3F);
/*      */                 break;
/*      */             } 
/*      */             
/* 3053 */             receivedBytes = 0;
/* 3054 */             expectedBytes = 0;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 3059 */           for (int j = 0; j < receivedBytes; j++)
/*      */           {
/* 3061 */             result.append((char)bytes[j]);
/*      */           }
/* 3063 */           receivedBytes = 0;
/* 3064 */           result.append(character);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 3069 */         for (int j = 0; j < receivedBytes; j++)
/*      */         {
/* 3071 */           result.append((char)bytes[j]);
/*      */         }
/* 3073 */         receivedBytes = 0;
/* 3074 */         result.append(value.charAt(i));
/*      */       } 
/*      */     } 
/* 3077 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static char unescape(char highHexDigit, char lowHexDigit) {
/* 3086 */     return (char)(valueOf(highHexDigit) << 4 | valueOf(lowHexDigit));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int valueOf(char hexDigit) {
/* 3092 */     if (hexDigit >= 'A' && hexDigit <= 'F')
/*      */     {
/* 3094 */       return hexDigit - 65 + 10;
/*      */     }
/* 3096 */     if (hexDigit >= 'a' && hexDigit <= 'f')
/*      */     {
/* 3098 */       return hexDigit - 97 + 10;
/*      */     }
/* 3100 */     if (hexDigit >= '0' && hexDigit <= '9')
/*      */     {
/* 3102 */       return hexDigit - 48;
/*      */     }
/* 3104 */     return 0;
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\commo\\util\URI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */