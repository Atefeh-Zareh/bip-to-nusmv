/*     */ package org.eclipse.emf.ecore.plugin;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.eclipse.core.resources.IFile;
/*     */ import org.eclipse.core.resources.IProject;
/*     */ import org.eclipse.core.resources.IWorkspaceRoot;
/*     */ import org.eclipse.core.resources.ResourcesPlugin;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.core.runtime.IConfigurationElement;
/*     */ import org.eclipse.core.runtime.IExtensionRegistry;
/*     */ import org.eclipse.core.runtime.Platform;
/*     */ import org.eclipse.emf.common.EMFPlugin;
/*     */ import org.eclipse.emf.common.util.ResourceLocator;
/*     */ import org.eclipse.emf.common.util.URI;
/*     */ import org.eclipse.emf.common.util.WrappedException;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.osgi.framework.BundleContext;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EcorePlugin
/*     */   extends EMFPlugin
/*     */ {
/*     */   public static class Implementation
/*     */     extends EMFPlugin.EclipsePlugin
/*     */   {
/*     */     public Implementation() {
/* 453 */       EcorePlugin.plugin = this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void start(BundleContext context) throws Exception {
/* 516 */       super.start(context);
/*     */       
/* 518 */       if (EcorePlugin.IS_RESOURCES_BUNDLE_AVAILABLE && System.getProperty("org.eclipse.emf.ecore.plugin.EcorePlugin.doNotLoadResourcesPlugin") == null)
/*     */       {
/* 520 */         EcorePlugin.workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
/*     */       }
/*     */       
/* 523 */       (new RegistryReader(
/* 524 */           Platform.getExtensionRegistry(), 
/* 525 */           EcorePlugin.getPlugin().getBundle().getSymbolicName(), 
/* 526 */           "package_registry_implementation")
/*     */         {
/*     */           IConfigurationElement previous;
/*     */ 
/*     */ 
/*     */           
/*     */           protected boolean readElement(IConfigurationElement element) {
/* 533 */             if (element.getName().equals("registry")) {
/*     */               
/* 535 */               String implementationClass = element.getAttribute("class");
/* 536 */               if (implementationClass == null) {
/*     */                 
/* 538 */                 logMissingAttribute(element, "class");
/*     */               }
/*     */               else {
/*     */                 
/* 542 */                 if (EcorePlugin.defaultRegistryImplementation != null) {
/*     */                   
/* 544 */                   if (this.previous != null)
/*     */                   {
/* 546 */                     EcorePlugin.Implementation.this.log("Both '" + this.previous.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package registry implementation");
/*     */                   }
/* 548 */                   if (EcorePlugin.defaultRegistryImplementation instanceof org.eclipse.emf.ecore.impl.EPackageRegistryImpl.Delegator)
/*     */                   {
/* 550 */                     return false;
/*     */                   }
/*     */                 } 
/*     */                 
/*     */                 try {
/* 555 */                   EcorePlugin.defaultRegistryImplementation = (EPackage.Registry)element.createExecutableExtension("class");
/* 556 */                   this.previous = element;
/*     */                 }
/* 558 */                 catch (CoreException exception) {
/*     */                   
/* 560 */                   EcorePlugin.Implementation.this.log(exception);
/*     */                 } 
/* 562 */                 return true;
/*     */               } 
/*     */             } 
/* 565 */             return false;
/*     */           }
/* 568 */         }).readRegistry();
/*     */       
/* 570 */       (new GeneratedPackageRegistryReader(EcorePlugin.getEPackageNsURIToGenModelLocationMap())).readRegistry();
/* 571 */       (new DynamicPackageRegistryReader()).readRegistry();
/* 572 */       (new FactoryOverrideRegistryReader()).readRegistry();
/* 573 */       (new ExtensionParserRegistryReader()).readRegistry();
/* 574 */       (new ProtocolParserRegistryReader()).readRegistry();
/* 575 */       (new ContentParserRegistryReader()).readRegistry();
/* 576 */       (new ContentHandlerRegistryReader()).readRegistry();
/* 577 */       (new URIMappingRegistryReader()).readRegistry();
/* 578 */       (new ValidationDelegateRegistryReader()).readRegistry();
/* 579 */       (new SettingDelegateFactoryRegistryReader()).readRegistry();
/* 580 */       (new InvocationDelegateFactoryRegistryReader()).readRegistry();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final EcorePlugin INSTANCE = new EcorePlugin();
/*     */   
/*     */   private static Pattern bundleSymbolNamePattern;
/*     */ 
/*     */   
/*     */   private EcorePlugin() {
/*     */     super(new ResourceLocator[0]);
/*     */   }
/*     */   
/*     */   public static EPackage.Registry getDefaultRegistryImplementation() {
/* 595 */     return defaultRegistryImplementation;
/*     */   }
/*     */   public ResourceLocator getPluginResourceLocator() { return (ResourceLocator)plugin; }
/*     */   public static Map<String, URI> getPlatformResourceMap() { if (platformResourceMap == null) platformResourceMap = new HashMap<String, URI>();  return platformResourceMap; }
/*     */   public static URI resolvePlatformResourcePath(String platformResourcePath) { if (platformResourceMap != null) { int index = platformResourcePath.indexOf("/", 1); String rootContainerName = platformResourcePath.substring(1, index); String relativeName = platformResourcePath.substring(index + 1); URI rootContainerLocation = getPlatformResourceMap().get(rootContainerName); if (rootContainerLocation != null) return URI.createURI(relativeName).resolve(rootContainerLocation);  }  return null; }
/*     */   public static String[] handlePlatformResourceOptions(String[] arguments) { getPlatformResourceMap(); for (int i = 0; i < arguments.length; i++) { if (arguments[i].equalsIgnoreCase("-platformResource")) { int start = i; while (++i < arguments.length && !arguments[i].startsWith("-")) { String rootContainerName = arguments[i]; if (++i < arguments.length) { URI uri; String rootContainerLocation = arguments[i]; File file = new File(rootContainerLocation); if (file.isDirectory() || (!file.exists() && file.getParent() != null && file.getParentFile().isDirectory())) { try { file = file.getCanonicalFile(); } catch (IOException exception) { throw new WrappedException(exception); }  uri = URI.createFileURI(String.valueOf(file.toString()) + "/"); } else { uri = URI.createURI(rootContainerLocation); }  platformResourceMap.put(rootContainerName, uri); }  }  String[] remainingArguments = new String[arguments.length - i - start]; System.arraycopy(arguments, 0, remainingArguments, 0, start); System.arraycopy(arguments, i, remainingArguments, start, arguments.length - i); return remainingArguments; }  }  return arguments; }
/*     */   public static Map<String, URI> getEPackageNsURIToGenModelLocationMap() { if (ePackageNsURIToGenModelLocationMap == null)
/*     */       ePackageNsURIToGenModelLocationMap = new HashMap<String, URI>();  return ePackageNsURIToGenModelLocationMap; } public static Map<URI, URI> computePlatformResourceToPlatformPluginMap(Collection<URI> uris) { Map<URI, URI> result = new HashMap<URI, URI>(); IWorkspaceRoot root = getWorkspaceRoot(); if (root != null)
/*     */       for (URI uri : uris) { if (uri.isPlatformPlugin()) { String pluginID = uri.segment(1); if (!root.getProject(pluginID).isOpen())
/* 604 */             result.put(URI.createPlatformResourceURI(String.valueOf(pluginID) + "/", false), URI.createPlatformPluginURI(String.valueOf(pluginID) + "/", false));  }  }   return result; } private static byte[] NO_BYTES = new byte[0]; private static Map<String, URI> platformResourceMap; private static Map<String, URI> ePackageNsURIToGenModelLocationMap; private static EPackage.Registry defaultRegistryImplementation; private static Implementation plugin; private static IWorkspaceRoot workspaceRoot; public static final String DYNAMIC_PACKAGE_PPID = "dynamic_package"; public static final String GENERATED_PACKAGE_PPID = "generated_package"; public static final String FACTORY_OVERRIDE_PPID = "factory_override"; public static final String EXTENSION_PARSER_PPID = "extension_parser"; public static final String PROTOCOL_PARSER_PPID = "protocol_parser"; public static final String CONTENT_PARSER_PPID = "content_parser"; public static final String CONTENT_HANDLER_PPID = "content_handler"; public static Implementation getPlugin() { return plugin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SCHEME_PARSER_PPID = "scheme_parser";
/*     */ 
/*     */   
/*     */   public static final String URI_MAPPING_PPID = "uri_mapping";
/*     */ 
/*     */   
/*     */   public static final String PACKAGE_REGISTRY_IMPLEMENTATION_PPID = "package_registry_implementation";
/*     */ 
/*     */   
/*     */   public static final String VALIDATION_DELEGATE_PPID = "validation_delegate";
/*     */ 
/*     */ 
/*     */   
/*     */   public static IWorkspaceRoot getWorkspaceRoot() {
/* 624 */     return workspaceRoot;
/*     */   }
/*     */   
/*     */   public static final String SETTING_DELEGATE_PPID = "setting_delegate";
/*     */   public static final String INVOCATION_DELEGATE_PPID = "invocation_delegate";
/*     */   
/*     */   public static Map<URI, URI> computePlatformPluginToPlatformResourceMap() {
/*     */     Map<URI, URI> result = new HashMap<URI, URI>();
/*     */     IWorkspaceRoot root = getWorkspaceRoot();
/*     */     if (root != null) {
/*     */       IProject[] projects = root.getProjects();
/*     */       if (projects != null) {
/*     */         String pluginID = null;
/*     */         Handler handler = new Handler();
/*     */         SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/*     */         parserFactory.setNamespaceAware(true);
/*     */         SAXParser parser = null;
/*     */         try {
/*     */           parser = parserFactory.newSAXParser();
/*     */         } catch (Exception exception) {
/*     */           INSTANCE.log(exception);
/*     */         } 
/*     */         if (bundleSymbolNamePattern == null)
/*     */           bundleSymbolNamePattern = Pattern.compile("^\\s*Bundle-SymbolicName\\s*:\\s*([^\\s;]*)\\s*(;.*)?$", 8); 
/*     */         byte[] bytes = NO_BYTES;
/*     */         for (int i = 0, size = projects.length; i < size; i++) {
/*     */           IProject project = projects[i];
/*     */           if (project.isOpen()) {
/*     */             pluginID = null;
/*     */             IFile manifest = project.getFile("META-INF/MANIFEST.MF");
/*     */             if (manifest.exists()) {
/*     */               InputStream inputStream = null;
/*     */               try {
/*     */                 inputStream = manifest.getContents();
/*     */                 int available = inputStream.available();
/*     */                 if (bytes.length < available)
/*     */                   bytes = new byte[available]; 
/*     */                 inputStream.read(bytes);
/*     */                 String contents = new String(bytes, "UTF-8");
/*     */                 Matcher matcher = bundleSymbolNamePattern.matcher(contents);
/*     */                 if (matcher.find())
/*     */                   pluginID = matcher.group(1); 
/*     */               } catch (Exception exception) {
/*     */                 INSTANCE.log(exception);
/*     */               } finally {
/*     */                 if (inputStream != null)
/*     */                   try {
/*     */                     inputStream.close();
/*     */                   } catch (IOException exception) {
/*     */                     INSTANCE.log(exception);
/*     */                   }  
/*     */               } 
/*     */             } else if (parser != null) {
/*     */               IFile plugin = project.getFile("plugin.xml");
/*     */               if (plugin.exists())
/*     */                 try {
/*     */                   parser.parse(new InputSource(plugin.getContents()), handler);
/*     */                 } catch (Exception exception) {
/*     */                   if (handler.pluginID != null) {
/*     */                     pluginID = handler.pluginID;
/*     */                   } else {
/*     */                     INSTANCE.log(exception);
/*     */                   } 
/*     */                 }  
/*     */             } 
/*     */             if (pluginID != null) {
/*     */               URI platformPluginURI = URI.createPlatformPluginURI(String.valueOf(pluginID) + "/", false);
/*     */               URI platformResourceURI = URI.createPlatformResourceURI(String.valueOf(project.getName()) + "/", true);
/*     */               result.put(platformPluginURI, platformResourceURI);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     class Handler extends DefaultHandler {
/*     */       public String pluginID;
/*     */       
/*     */       public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/*     */         if ("".equals(uri) && "plugin".equals(localName))
/*     */           this.pluginID = attributes.getValue("id"); 
/*     */         throw new SAXException("Done");
/*     */       }
/*     */     };
/*     */     return result;
/*     */   }
/*     */   
/*     */   public static Map<URI, URI> computePlatformURIMap() {
/*     */     Map<URI, URI> result = new HashMap<URI, URI>();
/*     */     result.putAll(computePlatformPluginToPlatformResourceMap());
/*     */     result.putAll(computePlatformResourceToPlatformPluginMap(new HashSet<URI>(getEPackageNsURIToGenModelLocationMap().values())));
/*     */     return result;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\plugin\EcorePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */