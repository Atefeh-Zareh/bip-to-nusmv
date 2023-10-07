/*     */ package org.eclipse.emf.ecore.plugin;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.core.runtime.IConfigurationElement;
/*     */ import org.eclipse.core.runtime.IExtension;
/*     */ import org.eclipse.core.runtime.IExtensionDelta;
/*     */ import org.eclipse.core.runtime.IExtensionPoint;
/*     */ import org.eclipse.core.runtime.IExtensionRegistry;
/*     */ import org.eclipse.core.runtime.IRegistryChangeEvent;
/*     */ import org.eclipse.core.runtime.IRegistryChangeListener;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.resource.Resource;
/*     */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*     */ import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RegistryReader
/*     */ {
/*     */   protected static final String TAG_DESCRIPTION = "description";
/*     */   protected IExtensionRegistry pluginRegistry;
/*     */   String pluginID;
/*     */   String extensionPointID;
/*     */   String qualifiedExtensionPointID;
/*     */   
/*     */   public RegistryReader(IExtensionRegistry pluginRegistry, String pluginID, String extensionPointID) {
/*  55 */     this.pluginRegistry = pluginRegistry;
/*  56 */     this.pluginID = pluginID;
/*  57 */     this.extensionPointID = extensionPointID;
/*  58 */     this.qualifiedExtensionPointID = String.valueOf(pluginID) + "." + extensionPointID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean readElement(IConfigurationElement element) {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean readElement(IConfigurationElement element, boolean add) {
/*  79 */     return (add && readElement(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readRegistry() {
/*  87 */     IExtensionPoint point = this.pluginRegistry.getExtensionPoint(this.pluginID, this.extensionPointID);
/*  88 */     if (point != null) {
/*     */       
/*  90 */       IConfigurationElement[] elements = point.getConfigurationElements();
/*  91 */       for (int i = 0; i < elements.length; i++)
/*     */       {
/*  93 */         internalReadElement(elements[i], true);
/*     */       }
/*     */     } 
/*     */     
/*  97 */     this.pluginRegistry.addRegistryChangeListener(
/*  98 */         new IRegistryChangeListener()
/*     */         {
/*     */           public void registryChanged(IRegistryChangeEvent event)
/*     */           {
/* 102 */             IExtensionDelta[] deltas = event.getExtensionDeltas();
/* 103 */             for (int i = 0; i < deltas.length; i++) {
/*     */               
/* 105 */               IExtensionDelta delta = deltas[i];
/* 106 */               if (delta.getExtensionPoint().getUniqueIdentifier().equals(RegistryReader.this.qualifiedExtensionPointID)) {
/*     */                 
/* 108 */                 boolean add = (delta.getKind() == 1);
/* 109 */                 IExtension extension = delta.getExtension();
/* 110 */                 IConfigurationElement[] configurationElement = extension.getConfigurationElements();
/* 111 */                 for (int j = 0; j < configurationElement.length; j++)
/*     */                 {
/* 113 */                   RegistryReader.this.internalReadElement(configurationElement[j], add);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void internalReadElement(IConfigurationElement element, boolean add) {
/* 123 */     boolean recognized = readElement(element, add);
/* 124 */     if (recognized) {
/*     */       
/* 126 */       IConfigurationElement[] children = element.getChildren();
/* 127 */       for (int i = 0; i < children.length; i++)
/*     */       {
/* 129 */         internalReadElement(children[i], add);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 134 */       logError(element, "Error processing extension: " + element);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void logError(IConfigurationElement element, String text) {
/* 144 */     IExtension extension = element.getDeclaringExtension();
/* 145 */     System.err.println("Plugin " + extension.getContributor().getName() + ", extension " + extension.getExtensionPointUniqueIdentifier());
/* 146 */     System.err.println(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void logMissingAttribute(IConfigurationElement element, String attributeName) {
/* 154 */     logError(element, "The required attribute '" + attributeName + "' not defined");
/*     */   }
/*     */ 
/*     */   
/*     */   public static class PluginClassDescriptor
/*     */   {
/*     */     protected IConfigurationElement element;
/*     */     protected String attributeName;
/*     */     
/*     */     public PluginClassDescriptor(IConfigurationElement element, String attributeName) {
/* 164 */       this.element = element;
/* 165 */       this.attributeName = attributeName;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object createInstance() {
/*     */       try {
/* 172 */         return this.element.createExecutableExtension(this.attributeName);
/*     */       }
/* 174 */       catch (CoreException e) {
/*     */         
/* 176 */         throw new WrappedException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class ResourceFactoryDescriptor
/*     */     extends PluginClassDescriptor
/*     */     implements Resource.Factory.Descriptor {
/*     */     protected Resource.Factory factoryInstance;
/*     */     
/*     */     public ResourceFactoryDescriptor(IConfigurationElement e, String attrName) {
/* 187 */       super(e, attrName);
/*     */     }
/*     */ 
/*     */     
/*     */     public Resource.Factory createFactory() {
/* 192 */       if (this.factoryInstance == null)
/*     */       {
/* 194 */         this.factoryInstance = (Resource.Factory)createInstance();
/*     */       }
/* 196 */       return this.factoryInstance;
/*     */     }
/*     */   }
/*     */   
/*     */   static class EPackageDescriptor
/*     */     extends PluginClassDescriptor
/*     */     implements EPackage.Descriptor {
/*     */     static class Dynamic
/*     */       extends EPackageDescriptor {
/* 205 */       protected static ResourceSet resourceSet = (ResourceSet)new ResourceSetImpl()
/*     */         {
/* 207 */           protected Set<URI> uris = new HashSet<URI>();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           protected Resource delegatedGetResource(URI uri, boolean loadOnDemand) {
/*     */             try {
/* 214 */               return this.uris.add(uri) ? super.delegatedGetResource(uri, loadOnDemand) : null;
/*     */             }
/*     */             finally {
/*     */               
/* 218 */               this.uris.remove(uri);
/*     */             } 
/*     */           }
/*     */         };
/*     */ 
/*     */       
/*     */       public Dynamic(IConfigurationElement element, String attributeName) {
/* 225 */         super(element, attributeName);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public EPackage getEPackage() {
/*     */         try {
/* 235 */           String location = this.element.getAttribute(this.attributeName);
/* 236 */           if (location != null) {
/*     */             
/* 238 */             URI locationURI = URI.createURI(location);
/* 239 */             if (locationURI.isRelative())
/*     */             {
/* 241 */               locationURI = URI.createPlatformPluginURI(String.valueOf(this.element.getDeclaringExtension().getContributor().getName()) + "/" + location, true);
/*     */             }
/* 243 */             if (!locationURI.hasFragment())
/*     */             {
/* 245 */               locationURI = locationURI.appendFragment("/");
/*     */             }
/* 247 */             return (EPackage)resourceSet.getEObject(locationURI, true);
/*     */           } 
/*     */ 
/*     */           
/* 251 */           throw new RuntimeException("No location attribute was specified.");
/*     */         
/*     */         }
/* 254 */         catch (Exception e) {
/*     */           
/* 256 */           throw new WrappedException(e);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public EPackageDescriptor(IConfigurationElement element, String attributeName) {
/* 263 */       super(element, attributeName);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EPackage getEPackage() {
/*     */       try {
/* 272 */         Class<?> javaClass = Platform.getBundle(this.element.getDeclaringExtension().getContributor().getName()).loadClass(this.element.getAttribute(this.attributeName));
/* 273 */         Field field = javaClass.getField("eINSTANCE");
/* 274 */         Object result = field.get(null);
/* 275 */         return (EPackage)result;
/*     */       }
/* 277 */       catch (ClassNotFoundException e) {
/*     */         
/* 279 */         throw new WrappedException(e);
/*     */       }
/* 281 */       catch (IllegalAccessException e) {
/*     */         
/* 283 */         throw new WrappedException(e);
/*     */       }
/* 285 */       catch (NoSuchFieldException e) {
/*     */         
/* 287 */         throw new WrappedException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public EFactory getEFactory() {
/* 293 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class EFactoryDescriptor
/*     */     extends PluginClassDescriptor
/*     */     implements EPackage.Descriptor {
/*     */     protected EPackage.Descriptor overridenDescriptor;
/*     */     
/*     */     public EFactoryDescriptor(IConfigurationElement element, String attributeName, EPackage.Descriptor overridenDescriptor) {
/* 303 */       super(element, attributeName);
/* 304 */       this.overridenDescriptor = overridenDescriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public EPackage getEPackage() {
/* 309 */       return this.overridenDescriptor.getEPackage();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EFactory getEFactory() {
/*     */       try {
/* 318 */         Class<?> javaClass = Platform.getBundle(this.element.getDeclaringExtension().getContributor().getName()).loadClass(this.element.getAttribute(this.attributeName));
/* 319 */         return (EFactory)javaClass.newInstance();
/*     */       }
/* 321 */       catch (ClassNotFoundException e) {
/*     */         
/* 323 */         throw new WrappedException(e);
/*     */       }
/* 325 */       catch (IllegalAccessException e) {
/*     */         
/* 327 */         throw new WrappedException(e);
/*     */       }
/* 329 */       catch (InstantiationException e) {
/*     */         
/* 331 */         throw new WrappedException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public EPackage.Descriptor getOverridenDescriptor() {
/* 337 */       return this.overridenDescriptor;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\RegistryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */