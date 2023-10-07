/*     */ package ujf.verimag.bip.parser;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*     */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*     */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*     */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*     */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*     */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ import ujf.verimag.bip.Core.Interactions.Part;
/*     */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*     */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BipCheckType
/*     */ {
/*     */   private BipTreeError error;
/*     */   
/*     */   public BipCheckType(BipTreeError error) {
/*  54 */     this.error = error;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void completeBindings(Module m) {
/*  64 */     for (BipType bt : m.getBipType()) {
/*  65 */       if (bt instanceof ComponentType) {
/*     */         
/*  67 */         ComponentType ct = (ComponentType)bt;
/*  68 */         for (Port p : ct.getPort()) {
/*  69 */           if (p.getType() == null) {
/*  70 */             Binding b = p.getBinding();
/*     */             
/*  72 */             if (b instanceof ExportBinding) {
/*  73 */               ExportBinding eb = (ExportBinding)b;
/*  74 */               if (eb.getTargetPort() == null) {
/*     */ 
/*     */                 
/*  77 */                 Part part = eb.getTargetInstance().getTargetPart();
/*  78 */                 if (part instanceof Connector) {
/*  79 */                   Connector cnx = (Connector)part;
/*  80 */                   Port port = cnx.getType().getPort();
/*  81 */                   if (port == null) {
/*     */                     
/*  83 */                     String[] params = { cnx.getName(), cnx.getType().getName() };
/*  84 */                     this.error.sendError(9, params, (EObject)b);
/*     */                     continue;
/*     */                   } 
/*  87 */                   eb.setTargetPort(port);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*  97 */       if (bt instanceof ConnectorType) {
/*  98 */         ConnectorType cnxt = (ConnectorType)bt;
/*     */         
/* 100 */         Port p = cnxt.getPort();
/* 101 */         if (p != null && p.getType() == null) {
/* 102 */           Binding b = p.getBinding();
/*     */           
/* 104 */           if (b instanceof DefinitionBinding) {
/* 105 */             DefinitionBinding db = (DefinitionBinding)b;
/* 106 */             p.setType(db.getDefinition().getType()); continue;
/* 107 */           }  if (b instanceof ExportBinding) {
/* 108 */             ExportBinding eb = (ExportBinding)b;
/* 109 */             p.setType(eb.getTargetPort().getType());
/*     */           } 
/*     */         } 
/*     */       } 
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
/*     */   
/*     */   private void checkTypes(Module m) {
/* 126 */     for (BipType bt : m.getBipType()) {
/* 127 */       if (bt instanceof AtomType) {
/* 128 */         AtomType at = (AtomType)bt;
/*     */         
/* 130 */         for (PortDefinition pd : at.getPortDefinition()) {
/* 131 */           CheckPortDef(pd);
/*     */         }
/* 133 */         PetriNet pn = (PetriNet)at.getBehavior();
/* 134 */         if (pn.getInitialState().isEmpty()) {
/* 135 */           String[] params = { at.getName() };
/* 136 */           this.error.sendError(22, params, (EObject)at);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 140 */       if (bt instanceof CompoundType) {
/* 141 */         List<InnerPortReference> connectedPorts = new ArrayList<InnerPortReference>();
/* 142 */         CompoundType ct = (CompoundType)bt;
/*     */         
/* 144 */         for (Connector c : ct.getConnector()) {
/* 145 */           checkConnector(c, connectedPorts);
/*     */         }
/*     */         
/* 148 */         for (Component c : ct.getSubcomponent()) {
/* 149 */           checkComponent(c, connectedPorts);
/*     */         }
/*     */         
/* 152 */         for (Port p : ct.getPort())
/* 153 */           checkPort(p); 
/*     */         continue;
/*     */       } 
/* 156 */       if (bt instanceof ConnectorType) {
/* 157 */         ConnectorType ct = (ConnectorType)bt;
/*     */         
/* 159 */         Port p = ct.getPort();
/* 160 */         if (p != null) checkCnxPort(p);
/*     */       
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 166 */     if (m instanceof System) {
/* 167 */       System sys = (System)m;
/* 168 */       checkComponent(sys.getRoot());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void CheckPortDef(PortDefinition pd) {
/* 176 */     PortType pt = pd.getType();
/* 177 */     int nbFormal = pt.getDataParameter().size();
/* 178 */     int nbActual = pd.getExposedVariable().size();
/* 179 */     if (nbFormal != nbActual) {
/* 180 */       String[] params = { pd.getName(), pt.getName(), "" + nbFormal };
/* 181 */       this.error.sendError(15, params, (EObject)pd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkPort(Port p) {
/* 189 */     ExportBinding eb = (ExportBinding)p.getBinding();
/*     */     
/* 191 */     if (eb.getTargetPort() == null) {
/* 192 */       String[] params = { p.getName(), eb.getTargetInstance().getTargetPart().getName() };
/* 193 */       this.error.sendError(19, params, (EObject)p);
/*     */       return;
/*     */     } 
/* 196 */     PortType actualType = p.getType();
/* 197 */     PortType formalType = eb.getTargetPort().getType();
/* 198 */     if (actualType != formalType)
/*     */     {
/* 200 */       if (!actualType.getName().equals("Port") || !formalType.getName().equals("Port")) {
/*     */ 
/*     */         
/* 203 */         String[] params = { p.getName(), formalType.getName() };
/* 204 */         this.error.sendError(16, params, (EObject)p);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkCnxPort(Port p) {
/* 213 */     DefinitionBinding b = (DefinitionBinding)p.getBinding();
/* 214 */     PortDefinition pd = b.getDefinition();
/* 215 */     CheckPortDef(pd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkParameters(Part ppe, ParameterizedElement pe) {
/* 222 */     int nbActual = ppe.getActualData().size();
/* 223 */     int nbFormal = pe.getDataParameter().size();
/* 224 */     if (nbActual < nbFormal) {
/* 225 */       Part part = ppe;
/* 226 */       BipType bt = (BipType)pe;
/* 227 */       String[] params = { part.getName(), bt.getName() };
/* 228 */       this.error.sendError(13, params, (EObject)ppe);
/* 229 */     } else if (nbActual > nbFormal) {
/* 230 */       Part part = ppe;
/* 231 */       BipType bt = (BipType)pe;
/* 232 */       String[] params = { part.getName(), bt.getName() };
/* 233 */       this.error.sendError(14, params, (EObject)ppe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkComponent(Component c, List<InnerPortReference> connectedPorts) {
/* 241 */     checkParameters((Part)c, (ParameterizedElement)c.getType());
/* 242 */     ComponentType ct = c.getType();
/* 243 */     for (Port p : ct.getPort()) {
/* 244 */       if (!findPortInst(c, p, connectedPorts))
/*     */       {
/*     */         
/* 247 */         if (!findPortExport(c.getCompoundType(), c, p)) {
/* 248 */           String[] params = { p.getName(), c.getName() };
/* 249 */           this.error.sendWarning(25, params, (EObject)c);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean findPortExport(CompoundType comp, Component inst, Port internalPort) {
/* 257 */     for (Port p : comp.getPort()) {
/* 258 */       ExportBinding eb = (ExportBinding)p.getBinding();
/* 259 */       if (eb.getTargetInstance().getTargetPart() == inst && eb.getTargetPort() == internalPort) return true; 
/*     */     } 
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkComponent(Root r) {
/* 267 */     int nbActual = r.getActualData().size();
/* 268 */     ComponentType rt = r.getType();
/* 269 */     if (rt instanceof AtomType) {
/* 270 */       String[] params = new String[0];
/* 271 */       this.error.sendError(23, params, (EObject)r);
/*     */     } 
/*     */     
/* 274 */     int nbFormal = rt.getDataParameter().size();
/* 275 */     if (nbActual < nbFormal) {
/* 276 */       Root root = r;
/* 277 */       ComponentType componentType = rt;
/* 278 */       String[] params = { root.getName(), componentType.getName() };
/* 279 */       this.error.sendError(13, params, (EObject)r);
/* 280 */     } else if (nbActual > nbFormal) {
/* 281 */       Root root = r;
/* 282 */       ComponentType componentType = rt;
/* 283 */       String[] params = { root.getName(), componentType.getName() };
/* 284 */       this.error.sendError(14, params, (EObject)r);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean findPortInst(Component inst, Port p, List<InnerPortReference> l) {
/* 290 */     for (InnerPortReference ipr : l) {
/* 291 */       PartElementReference per = ipr.getTargetInstance();
/* 292 */       if (per.getTargetPart() == inst && ipr.getTargetPort() == p) {
/* 293 */         return true;
/*     */       }
/*     */     } 
/* 296 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkConnector(Connector c, List<InnerPortReference> connectedPorts) {
/* 301 */     Iterator<PortParameter> j = c.getType().getPortParameter().iterator();
/* 302 */     for (ActualPortParameter app : c.getActualPort()) {
/*     */       
/* 304 */       if (j.hasNext()) {
/*     */         
/* 306 */         PortParameter pp = j.next();
/* 307 */         checkPortParameterType(c, pp, app);
/*     */       }
/*     */       else {
/*     */         
/* 311 */         String[] params = { c.getName(), c.getType().getName() };
/* 312 */         this.error.sendError(11, params, (EObject)c);
/*     */         break;
/*     */       } 
/* 315 */       addPort(app, connectedPorts);
/*     */     } 
/*     */     
/* 318 */     if (j.hasNext()) {
/*     */ 
/*     */       
/* 321 */       String[] params = { c.getName(), c.getType().getName() };
/* 322 */       this.error.sendError(12, params, (EObject)c);
/*     */     } 
/*     */ 
/*     */     
/* 326 */     List<InnerPortReference> l = new ArrayList<InnerPortReference>();
/* 327 */     checkPortUnicity(c, l);
/*     */     
/* 329 */     checkParameters((Part)c, (ParameterizedElement)c.getType());
/*     */   }
/*     */   
/*     */   private void addPort(ActualPortParameter app, List<InnerPortReference> l) {
/* 333 */     if (app instanceof ConditionalActualPortParameter) {
/* 334 */       ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/* 335 */       addPort(capp.getTrueCase(), l);
/* 336 */       addPort(capp.getFalseCase(), l);
/*     */     }
/* 338 */     else if (app instanceof InnerPortReference) {
/* 339 */       InnerPortReference ipr = (InnerPortReference)app;
/* 340 */       l.add(ipr);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean samePort(InnerPortReference ipr1, InnerPortReference ipr2) {
/* 345 */     if (ipr1.getTargetPort() == ipr2.getTargetPort()) {
/* 346 */       PartElementReference per1 = ipr1.getTargetInstance();
/* 347 */       PartElementReference per2 = ipr2.getTargetInstance();
/* 348 */       if (!per1.getIndex().isEmpty() || !per2.getIndex().isEmpty())
/*     */       {
/* 350 */         return false;
/*     */       }
/* 352 */       if (per1.getTargetPart() == per2.getTargetPart()) {
/* 353 */         return true;
/*     */       }
/*     */     } 
/* 356 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean portInList(List<InnerPortReference> l, InnerPortReference ipr) {
/* 361 */     for (InnerPortReference ipr2 : l) {
/* 362 */       if (samePort(ipr, ipr2)) {
/* 363 */         return true;
/*     */       }
/*     */     } 
/* 366 */     return false;
/*     */   }
/*     */   
/*     */   private String innerPortName(InnerPortReference ipr) {
/* 370 */     return ipr.getTargetInstance().getTargetPart().getName() + "." + ipr.getTargetPort().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkPortNotInList(List<InnerPortReference> l, ActualPortParameter app, Connector c) {
/* 375 */     if (app instanceof InnerPortReference) {
/* 376 */       InnerPortReference ipr = (InnerPortReference)app;
/*     */       
/* 378 */       if (ipr.getTargetInstance().getIndex() != null)
/* 379 */         return;  if (portInList(l, ipr)) {
/*     */         
/* 381 */         String[] params = { innerPortName(ipr), c.getName() };
/* 382 */         this.error.sendWarning(24, params, (EObject)c);
/*     */       } else {
/* 384 */         l.add(ipr);
/* 385 */         Part part = ipr.getTargetInstance().getTargetPart();
/* 386 */         if (part instanceof Connector) {
/* 387 */           Connector cnx = (Connector)part;
/* 388 */           for (ActualPortParameter app2 : cnx.getActualPort())
/*     */           {
/* 390 */             checkPortNotInList(l, app2, c);
/*     */           }
/*     */         } 
/*     */       } 
/* 394 */     } else if (app instanceof ConditionalActualPortParameter) {
/* 395 */       ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/* 396 */       checkPortNotInList(l, capp.getFalseCase(), c);
/* 397 */       checkPortNotInList(l, capp.getTrueCase(), c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkPortUnicity(Connector c, List<InnerPortReference> l) {
/* 403 */     for (ActualPortParameter app : c.getActualPort()) {
/* 404 */       checkPortNotInList(l, app, c);
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
/*     */   private void checkPortParameterType(Connector c, PortParameter pp, ActualPortParameter app) {
/* 417 */     if (app instanceof InnerPortReference) {
/* 418 */       InnerPortReference ipr = (InnerPortReference)app;
/* 419 */       Port actualPort = ipr.getTargetPort();
/* 420 */       PortType actualType = actualPort.getType();
/* 421 */       PortType formalType = pp.getType();
/*     */       
/* 423 */       if (actualType != formalType)
/*     */       {
/* 425 */         if (!actualType.getName().equals("Port") || !formalType.getName().equals("Port"))
/*     */         {
/*     */           
/* 428 */           String[] params = { actualPort.getName(), c.getName(), formalType.getName() };
/* 429 */           this.error.sendError(10, params, (EObject)c);
/*     */         }
/*     */       
/*     */       }
/* 433 */     } else if (app instanceof ConditionalActualPortParameter) {
/* 434 */       ConditionalActualPortParameter capp = (ConditionalActualPortParameter)app;
/* 435 */       checkPortParameterType(c, pp, capp.getFalseCase());
/* 436 */       checkPortParameterType(c, pp, capp.getTrueCase());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkModel(Module m) {
/* 454 */     completeBindings(m);
/*     */     
/* 456 */     checkTypes(m);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\parser\BipCheckType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */