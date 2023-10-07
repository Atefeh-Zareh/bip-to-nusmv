/*     */ package BIPTransformation;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ import ujf.verimag.bip.Core.Behaviors.Port;
/*     */ import ujf.verimag.bip.Core.Interactions.Component;
/*     */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*     */ import ujf.verimag.bip.Core.Interactions.Connector;
/*     */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*     */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatHierarchy
/*     */ {
/*     */   public static void ReplaceComponentsItsContents(CompoundType TOP) {
/*  31 */     while (!TransformationFunction.IsAllAtomic(TOP)) {
/*  32 */       Component component = TransformationFunction.getNonAtomicComponent(TOP);
/*  33 */       ReplaceComponentItsContents(TOP, component);
/*     */     } 
/*  35 */     System.out.println("1 -- flat compound components done !");
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
/*     */   public static void ReplaceComponentItsContents(CompoundType TOP, Component component) {
/*  47 */     if (component.getType() instanceof ujf.verimag.bip.Core.Behaviors.AtomType)
/*     */       return; 
/*  49 */     CompoundType componentType = (CompoundType)component.getType();
/*  50 */     CompoundType componentTypeCopy = TransformationFunction.getCopy(componentType);
/*  51 */     component.setType((ComponentType)componentTypeCopy);
/*  52 */     ReplaceComponentsContent(TOP, component);
/*  53 */     ReplaceConnectorsContent(TOP, component);
/*  54 */     UpdateConnectors(TOP, component);
/*  55 */     TOP.getSubcomponent().remove(component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void UpdateConnectors(CompoundType top, Component component) {
/*  62 */     EList eList = top.getConnector();
/*  63 */     ComponentType ct = component.getType();
/*  64 */     for (Object o : eList) {
/*  65 */       Connector connector = (Connector)o;
/*  66 */       List<Port> LPort = new LinkedList<Port>((Collection<? extends Port>)connector.getActualPort());
/*  67 */       for (Port o1 : LPort) {
/*  68 */         InnerPortReference p = (InnerPortReference)o1;
/*  69 */         if (p.getTargetInstance().getTargetPart().equals(component)) {
/*  70 */           Port port = TransformationFunction.getPort(ct, p.getTargetPort().getName());
/*  71 */           ExportBinding EB = (ExportBinding)port.getBinding();
/*  72 */           p.setTargetPort(EB.getTargetPort());
/*  73 */           p.getTargetInstance().setTargetPart(EB.getTargetInstance().getTargetPart());
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
/*     */   private static void ReplaceConnectorsContent(CompoundType compoundType, Component component) {
/*  87 */     CompoundType componentType = (CompoundType)component.getType();
/*  88 */     List<Connector> LConnector = new LinkedList<Connector>((Collection<? extends Connector>)componentType.getConnector());
/*  89 */     for (Connector o : LConnector) {
/*  90 */       Connector connector = o;
/*  91 */       connector.setName(String.valueOf(component.getName()) + connector.getName());
/*  92 */       compoundType.getConnector().add(connector);
/*  93 */       connector.setCompoundType(compoundType);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void ReplaceComponentsContent(CompoundType compoundType, Component component) {
/* 104 */     CompoundType componentType = (CompoundType)component.getType();
/* 105 */     List<Component> LComponent = new LinkedList<Component>((Collection<? extends Component>)componentType.getSubcomponent());
/* 106 */     for (Component o : LComponent) {
/* 107 */       Component componentin = o;
/* 108 */       componentin.setName(String.valueOf(component.getName()) + componentin.getName());
/* 109 */       ReplaceActualDataInComponent(component, componentin);
/* 110 */       componentin.setCompoundType(compoundType);
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
/*     */   private static void ReplaceActualDataInComponent(Component component, Component componentin) {
/* 123 */     EList eList = componentin.getCompoundType().getDataParameter();
/* 124 */     EList<Expression> eList1 = component.getActualData();
/* 125 */     List<Expression> LActualDataToChange = new LinkedList<Expression>((Collection<? extends Expression>)componentin.getActualData());
/*     */     
/* 127 */     for (Expression o : LActualDataToChange) {
/* 128 */       if (o instanceof DataParameterReference) {
/* 129 */         DataParameterReference DPR = (DataParameterReference)o;
/* 130 */         int index = eList.indexOf(DPR.getTargetParameter());
/* 131 */         Expression ExpCopy = TransformationFunction.getCopy(eList1.get(index));
/* 132 */         componentin.getActualData().set(index, ExpCopy); continue;
/*     */       } 
/* 134 */       if (o instanceof BinaryExpression) {
/* 135 */         BinaryExpression BE = (BinaryExpression)TransformationFunction.getCopy(o);
/* 136 */         ReplaceDataParameterExpression((Expression)BE, (List<DataParameter>)eList, (List<Expression>)eList1);
/* 137 */         componentin.getActualData().set(LActualDataToChange.indexOf(o), BE);
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
/*     */   private static void ReplaceDataParameterExpression(Expression e, List<DataParameter> LDataParameter, List<Expression> LActualDataCorrepond) {
/* 150 */     if (e instanceof BinaryExpression) {
/*     */       
/* 152 */       BinaryExpression BE = (BinaryExpression)e;
/* 153 */       if (BE.getRightOperand() instanceof DataParameterReference) {
/* 154 */         DataParameterReference DPR = (DataParameterReference)BE.getRightOperand();
/* 155 */         int index = LDataParameter.indexOf(DPR.getTargetParameter());
/* 156 */         Expression EO = TransformationFunction.getCopy(LActualDataCorrepond.get(index));
/* 157 */         BE.setRightOperand(EO);
/*     */       }
/* 159 */       else if (BE.getRightOperand() instanceof BinaryExpression) {
/* 160 */         ReplaceDataParameterExpression(BE.getRightOperand(), LDataParameter, LActualDataCorrepond);
/*     */       } 
/* 162 */       if (BE.getLeftOperand() instanceof DataParameterReference) {
/* 163 */         DataParameterReference DPR = (DataParameterReference)BE.getLeftOperand();
/* 164 */         int index = LDataParameter.indexOf(DPR.getTargetParameter());
/* 165 */         Expression EO = TransformationFunction.getCopy(LActualDataCorrepond.get(index));
/* 166 */         BE.setLeftOperand(EO);
/*     */       }
/* 168 */       else if (BE.getLeftOperand() instanceof BinaryExpression) {
/* 169 */         ReplaceDataParameterExpression(BE.getLeftOperand(), LDataParameter, LActualDataCorrepond);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\BIPTransformation\FlatHierarchy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */