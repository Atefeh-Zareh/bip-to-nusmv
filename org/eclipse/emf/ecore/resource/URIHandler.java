/*    */ package org.eclipse.emf.ecore.resource;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.resource.impl.ArchiveURIHandlerImpl;
/*    */ import org.eclipse.emf.ecore.resource.impl.EFSURIHandlerImpl;
/*    */ import org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl;
/*    */ import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
/*    */ import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface URIHandler
/*    */ {
/* 53 */   public static final List<URIHandler> DEFAULT_HANDLERS = Collections.unmodifiableList(
/* 54 */       Arrays.asList(
/*    */         
/* 56 */         new URIHandler[] {
/* 57 */           (URIHandler)new PlatformResourceURIHandlerImpl(), 
/* 58 */           (URIHandler)new FileURIHandlerImpl(), 
/* 59 */           (URIHandler)new EFSURIHandlerImpl(), 
/* 60 */           (URIHandler)new ArchiveURIHandlerImpl(), 
/* 61 */           (URIHandler)new URIHandlerImpl()
/*    */         }));
/*    */   
/*    */   boolean canHandle(URI paramURI);
/*    */   
/*    */   InputStream createInputStream(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*    */   
/*    */   OutputStream createOutputStream(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*    */   
/*    */   void delete(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*    */   
/*    */   Map<String, ?> contentDescription(URI paramURI, Map<?, ?> paramMap) throws IOException;
/*    */   
/*    */   boolean exists(URI paramURI, Map<?, ?> paramMap);
/*    */   
/*    */   Map<String, ?> getAttributes(URI paramURI, Map<?, ?> paramMap);
/*    */   
/*    */   void setAttributes(URI paramURI, Map<String, ?> paramMap, Map<?, ?> paramMap1) throws IOException;
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\resource\URIHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */