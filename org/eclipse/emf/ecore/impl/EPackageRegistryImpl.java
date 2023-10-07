/*     */ package org.eclipse.emf.ecore.impl;
/*     */ 
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
/*     */ import org.eclipse.emf.common.EMFPlugin;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EPackageRegistryImpl
/*     */   extends HashMap<String, Object>
/*     */   implements EPackage.Registry
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected EPackage.Registry delegateRegistry;
/*     */   
/*     */   public static EPackage.Registry createGlobalRegistry() {
/*     */     try {
/*  54 */       String className = System.getProperty("org.eclipse.emf.ecore.EPackage.Registry.INSTANCE");
/*  55 */       if (className == null) {
/*     */         
/*  57 */         if (EcorePlugin.getDefaultRegistryImplementation() != null)
/*     */         {
/*  59 */           return EcorePlugin.getDefaultRegistryImplementation();
/*     */         }
/*  61 */         if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
/*     */           
/*     */           try {
/*     */             
/*  65 */             SecurityManager securityManager = System.getSecurityManager();
/*  66 */             if (securityManager != null)
/*     */             {
/*  68 */               securityManager.checkPermission(new RuntimePermission("classLoader"));
/*     */             }
/*  70 */             return new Delegator();
/*     */           }
/*  72 */           catch (Throwable throwable) {
/*     */             
/*  74 */             return new SecureDelegator(null);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*  79 */         return new EPackageRegistryImpl();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  84 */       return (EPackage.Registry)Class.forName(className).newInstance();
/*     */     
/*     */     }
/*  87 */     catch (Exception exception) {
/*     */       
/*  89 */       EcorePlugin.INSTANCE.log(exception);
/*  90 */       return new EPackageRegistryImpl();
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
/*     */   public EPackageRegistryImpl() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackageRegistryImpl(EPackage.Registry delegateRegistry) {
/* 112 */     this.delegateRegistry = delegateRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EPackage getEPackage(String nsURI) {
/* 120 */     Object ePackage = get(nsURI);
/* 121 */     if (ePackage instanceof EPackage) {
/*     */       
/* 123 */       EPackage result = (EPackage)ePackage;
/* 124 */       if (result.getNsURI() == null)
/*     */       {
/* 126 */         initialize(result);
/*     */       }
/* 128 */       return result;
/*     */     } 
/* 130 */     if (ePackage instanceof EPackage.Descriptor) {
/*     */       
/* 132 */       EPackage.Descriptor ePackageDescriptor = (EPackage.Descriptor)ePackage;
/* 133 */       EPackage result = ePackageDescriptor.getEPackage();
/* 134 */       if (result != null)
/*     */       {
/* 136 */         if (result.getNsURI() == null) {
/*     */           
/* 138 */           initialize(result);
/*     */         }
/*     */         else {
/*     */           
/* 142 */           put(nsURI, result);
/*     */         } 
/*     */       }
/* 145 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 149 */     return delegatedGetEPackage(nsURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EFactory getEFactory(String nsURI) {
/* 158 */     Object ePackage = get(nsURI);
/* 159 */     if (ePackage instanceof EPackage) {
/*     */       
/* 161 */       EPackage result = (EPackage)ePackage;
/* 162 */       if (result.getNsURI() == null)
/*     */       {
/* 164 */         initialize(result);
/*     */       }
/* 166 */       return result.getEFactoryInstance();
/*     */     } 
/* 168 */     if (ePackage instanceof EPackage.Descriptor) {
/*     */       
/* 170 */       EPackage.Descriptor ePackageDescriptor = (EPackage.Descriptor)ePackage;
/* 171 */       EFactory result = ePackageDescriptor.getEFactory();
/* 172 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 176 */     return delegatedGetEFactory(nsURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(EPackage ePackage) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EPackage delegatedGetEPackage(String nsURI) {
/* 194 */     if (this.delegateRegistry != null)
/*     */     {
/* 196 */       return this.delegateRegistry.getEPackage(nsURI);
/*     */     }
/*     */     
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EFactory delegatedGetEFactory(String nsURI) {
/* 208 */     if (this.delegateRegistry != null)
/*     */     {
/* 210 */       return this.delegateRegistry.getEFactory(nsURI);
/*     */     }
/*     */     
/* 213 */     return null;
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
/*     */   public boolean containsKey(Object key) {
/* 226 */     return !(!super.containsKey(key) && (this.delegateRegistry == null || !this.delegateRegistry.containsKey(key)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   protected static Map<ClassLoader, EPackage.Registry> classLoaderToRegistryMap = new WeakHashMap<ClassLoader, EPackage.Registry>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized EPackage.Registry getRegistry(ClassLoader classLoader) {
/* 241 */     EPackage.Registry result = classLoaderToRegistryMap.get(classLoader);
/* 242 */     if (result == null)
/*     */     {
/* 244 */       if (classLoader != null) {
/*     */         
/* 246 */         result = new EPackageRegistryImpl(getRegistry(classLoader.getParent()));
/* 247 */         classLoaderToRegistryMap.put(classLoader, result);
/*     */       } 
/*     */     }
/* 250 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Delegator
/*     */     implements EPackage.Registry
/*     */   {
/*     */     protected EPackage.Registry delegateRegistry(ClassLoader classLoader) {
/* 260 */       return EPackageRegistryImpl.getRegistry(classLoader);
/*     */     }
/*     */ 
/*     */     
/*     */     protected EPackage.Registry delegateRegistry() {
/* 265 */       return delegateRegistry(getContextClassLoader());
/*     */     }
/*     */ 
/*     */     
/*     */     protected ClassLoader getContextClassLoader() {
/* 270 */       return Thread.currentThread().getContextClassLoader();
/*     */     }
/*     */ 
/*     */     
/*     */     protected ClassLoader getParent(ClassLoader classLoader) {
/* 275 */       return (classLoader == null) ? null : classLoader.getParent();
/*     */     }
/*     */ 
/*     */     
/*     */     public EPackage getEPackage(String key) {
/* 280 */       return delegateRegistry().getEPackage(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public EFactory getEFactory(String key) {
/* 285 */       return delegateRegistry().getEFactory(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 290 */       return delegateRegistry().size();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 295 */       return delegateRegistry().isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsKey(Object key) {
/* 300 */       return delegateRegistry().containsKey(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsValue(Object value) {
/* 305 */       return delegateRegistry().containsValue(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object get(Object key) {
/* 310 */       return delegateRegistry().get(key);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object put(String key, Object value) {
/* 316 */       Class<?> valueClass = value.getClass();
/* 317 */       if (valueClass == EPackageImpl.class)
/*     */       {
/* 319 */         return delegateRegistry().put(key, value);
/*     */       }
/*     */ 
/*     */       
/* 323 */       String valueClassName = valueClass.getName();
/*     */ 
/*     */ 
/*     */       
/* 327 */       ClassLoader result = getContextClassLoader();
/* 328 */       for (ClassLoader classLoader = getParent(result); classLoader != null; ) {
/*     */ 
/*     */         
/*     */         try {
/* 332 */           Class<?> loadedClass = classLoader.loadClass(valueClassName);
/* 333 */           if (loadedClass == valueClass) {
/*     */             
/* 335 */             result = classLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             classLoader = getParent(classLoader);
/*     */           } 
/* 345 */         } catch (ClassNotFoundException exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 356 */       return delegateRegistry(result).put(key, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object remove(Object key) {
/* 362 */       return delegateRegistry().remove(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putAll(Map<? extends String, ? extends Object> map) {
/* 367 */       for (Map.Entry<? extends String, ? extends Object> entry : map.entrySet())
/*     */       {
/* 369 */         put(entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void clear() {
/* 375 */       delegateRegistry().clear();
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<String> keySet() {
/* 380 */       return delegateRegistry().keySet();
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<Object> values() {
/* 385 */       return delegateRegistry().values();
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<Map.Entry<String, Object>> entrySet() {
/* 390 */       return delegateRegistry().entrySet();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ParentClassLoaderGetter implements PrivilegedAction<ClassLoader> {
/*     */     private ClassLoader classLoader;
/*     */     
/*     */     private ParentClassLoaderGetter() {}
/*     */     
/*     */     public ClassLoader run() {
/* 400 */       if (this.classLoader != null)
/*     */       {
/* 402 */         this.classLoader = this.classLoader.getParent();
/*     */       }
/* 404 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public ClassLoader getParent(ClassLoader classLoader) {
/* 409 */       this.classLoader = classLoader;
/* 410 */       AccessController.doPrivileged(this);
/* 411 */       return this.classLoader;
/*     */     }
/*     */   }
/*     */   
/* 415 */   private static final ParentClassLoaderGetter PARENT_CLASS_LOADER_GETTER = new ParentClassLoaderGetter(null);
/*     */ 
/*     */   
/* 418 */   private static final PrivilegedAction<ClassLoader> CONTEXT_CLASS_LOADER_ACTION = new PrivilegedAction<ClassLoader>()
/*     */     {
/*     */       public ClassLoader run()
/*     */       {
/* 422 */         return Thread.currentThread().getContextClassLoader();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private static ClassLoader getContextClassLoaderSecurely() {
/* 428 */     return AccessController.<ClassLoader>doPrivileged(CONTEXT_CLASS_LOADER_ACTION);
/*     */   }
/*     */   
/* 431 */   private static final Map<ClassLoader, EPackage.Registry> secureClassLoaderToRegistryMap = new WeakHashMap<ClassLoader, EPackage.Registry>();
/*     */ 
/*     */   
/*     */   private static synchronized EPackage.Registry getRegistrySecurely(ClassLoader classLoader) {
/* 435 */     EPackage.Registry result = secureClassLoaderToRegistryMap.get(classLoader);
/* 436 */     if (result == null)
/*     */     {
/* 438 */       if (classLoader != null) {
/*     */         
/* 440 */         result = new EPackageRegistryImpl(getRegistrySecurely(PARENT_CLASS_LOADER_GETTER.getParent(classLoader)));
/* 441 */         secureClassLoaderToRegistryMap.put(classLoader, result);
/*     */       } 
/*     */     }
/* 444 */     return result;
/*     */   }
/*     */   
/*     */   private static final class SecureDelegator extends Delegator {
/*     */     private SecureDelegator() {
/* 449 */       this.parentClassLoaderGetter = new EPackageRegistryImpl.ParentClassLoaderGetter(null);
/*     */     }
/*     */ 
/*     */     
/*     */     protected EPackage.Registry delegateRegistry(ClassLoader classLoader) {
/* 454 */       return EPackageRegistryImpl.getRegistrySecurely(classLoader);
/*     */     }
/*     */     
/*     */     private final EPackageRegistryImpl.ParentClassLoaderGetter parentClassLoaderGetter;
/*     */     
/*     */     protected ClassLoader getContextClassLoader() {
/* 460 */       return EPackageRegistryImpl.getContextClassLoaderSecurely();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected ClassLoader getParent(ClassLoader classLoader) {
/* 466 */       return this.parentClassLoaderGetter.getParent(classLoader);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EPackageRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */