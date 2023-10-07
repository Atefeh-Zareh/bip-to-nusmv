/*      */ package org.eclipse.emf.ecore.xml.type.impl;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.List;
/*      */ import javax.xml.datatype.Duration;
/*      */ import javax.xml.datatype.XMLGregorianCalendar;
/*      */ import javax.xml.namespace.QName;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.ENamedElement;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EValidator;
/*      */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*      */ import org.eclipse.emf.ecore.xml.type.AnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
/*      */ import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XMLTypePackageImpl
/*      */   extends EPackageImpl
/*      */   implements XMLTypePackage
/*      */ {
/*   58 */   private EClass anyTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   private EClass processingInstructionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   72 */   private EClass simpleAnyTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   79 */   private EClass xmlTypeDocumentRootEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   86 */   private EDataType anySimpleTypeEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   private EDataType anyURIEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  100 */   private EDataType base64BinaryEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   private EDataType booleanEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  114 */   private EDataType booleanObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  121 */   private EDataType decimalEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   private EDataType integerEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  135 */   private EDataType intObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  142 */   private EDataType longEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  149 */   private EDataType longObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  156 */   private EDataType intEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  163 */   private EDataType shortEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   private EDataType shortObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  177 */   private EDataType byteEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  184 */   private EDataType byteObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  191 */   private EDataType dateEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  198 */   private EDataType dateTimeEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  205 */   private EDataType stringEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  212 */   private EDataType doubleEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  219 */   private EDataType doubleObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  226 */   private EDataType durationEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  233 */   private EDataType entitiesBaseEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  240 */   private EDataType normalizedStringEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  247 */   private EDataType tokenEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  254 */   private EDataType nameEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  261 */   private EDataType ncNameEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  268 */   private EDataType entityEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  275 */   private EDataType entitiesEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   private EDataType floatEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  289 */   private EDataType floatObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  296 */   private EDataType gDayEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  303 */   private EDataType gMonthEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   private EDataType gMonthDayEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  317 */   private EDataType gYearEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  324 */   private EDataType gYearMonthEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  331 */   private EDataType hexBinaryEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  338 */   private EDataType idEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  345 */   private EDataType idrefEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  352 */   private EDataType idrefsBaseEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  359 */   private EDataType idrefsEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  366 */   private EDataType languageEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  373 */   private EDataType nonPositiveIntegerEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  380 */   private EDataType negativeIntegerEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  387 */   private EDataType nmtokenEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  394 */   private EDataType nmtokensBaseEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  401 */   private EDataType nmtokensEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  408 */   private EDataType nonNegativeIntegerEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  415 */   private EDataType notationEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  422 */   private EDataType positiveIntegerEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  429 */   private EDataType qNameEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  436 */   private EDataType timeEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  443 */   private EDataType unsignedLongEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  450 */   private EDataType unsignedIntEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  457 */   private EDataType unsignedIntObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  464 */   private EDataType unsignedShortEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  471 */   private EDataType unsignedShortObjectEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  478 */   private EDataType unsignedByteEDataType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  485 */   private EDataType unsignedByteObjectEDataType = null; private static boolean isInited = false; private boolean isCreated; private boolean isInitialized; public static XMLTypePackage init() { if (isInited) return (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2003/XMLType");  XMLTypePackageImpl theXMLTypePackage = (EPackage.Registry.INSTANCE.get("http://www.eclipse.org/emf/2003/XMLType") instanceof XMLTypePackageImpl) ? (XMLTypePackageImpl)EPackage.Registry.INSTANCE.get("http://www.eclipse.org/emf/2003/XMLType") : new XMLTypePackageImpl(); isInited = true; theXMLTypePackage.createPackageContents(); theXMLTypePackage.initializePackageContents(); EValidator.Registry.INSTANCE.put(theXMLTypePackage, new EValidator.Descriptor() { public EValidator getEValidator() { return (EValidator)XMLTypeValidator.INSTANCE; } }
/*      */       ); theXMLTypePackage.freeze(); EPackage.Registry.INSTANCE.put("http://www.eclipse.org/emf/2003/XMLType", theXMLTypePackage); return theXMLTypePackage; }
/*      */   public EClass getAnyType() { return this.anyTypeEClass; }
/*      */   public EAttribute getAnyType_Mixed() { return (EAttribute)this.anyTypeEClass.getEStructuralFeatures().get(0); }
/*      */   public EAttribute getAnyType_Any() { return (EAttribute)this.anyTypeEClass.getEStructuralFeatures().get(1); }
/*      */   public EAttribute getAnyType_AnyAttribute() { return (EAttribute)this.anyTypeEClass.getEStructuralFeatures().get(2); }
/*      */   public EClass getProcessingInstruction() { return this.processingInstructionEClass; }
/*      */   public EAttribute getProcessingInstruction_Data() { return (EAttribute)this.processingInstructionEClass.getEStructuralFeatures().get(0); }
/*      */   public EAttribute getProcessingInstruction_Target() { return (EAttribute)this.processingInstructionEClass.getEStructuralFeatures().get(1); }
/*      */   public EClass getSimpleAnyType() { return this.simpleAnyTypeEClass; }
/*      */   public EAttribute getSimpleAnyType_RawValue() { return (EAttribute)this.simpleAnyTypeEClass.getEStructuralFeatures().get(0); }
/*      */   public EAttribute getSimpleAnyType_Value() { return (EAttribute)this.simpleAnyTypeEClass.getEStructuralFeatures().get(1); }
/*      */   public EReference getSimpleAnyType_InstanceType() { return (EReference)this.simpleAnyTypeEClass.getEStructuralFeatures().get(2); }
/*      */   public EClass getXMLTypeDocumentRoot() { return this.xmlTypeDocumentRootEClass; }
/*      */   public EAttribute getXMLTypeDocumentRoot_Mixed() { return (EAttribute)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(0); }
/*      */   public EReference getXMLTypeDocumentRoot_XMLNSPrefixMap() { return (EReference)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(1); }
/*      */   public EReference getXMLTypeDocumentRoot_XSISchemaLocation() { return (EReference)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(2); }
/*      */   public EAttribute getXMLTypeDocumentRoot_CDATA() { return (EAttribute)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(3); }
/*      */   public EAttribute getXMLTypeDocumentRoot_Comment() { return (EAttribute)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(4); }
/*  504 */   private XMLTypePackageImpl() { super("http://www.eclipse.org/emf/2003/XMLType", (EFactory)XMLTypeFactory.eINSTANCE);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1346 */     this.isCreated = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1450 */     this.isInitialized = false; }
/*      */   public EAttribute getXMLTypeDocumentRoot_Text() { return (EAttribute)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(6); }
/*      */   public EReference getXMLTypeDocumentRoot_ProcessingInstruction() { return (EReference)this.xmlTypeDocumentRootEClass.getEStructuralFeatures().get(5); }
/*      */   public EDataType getAnySimpleType() { return this.anySimpleTypeEDataType; }
/*      */   public EDataType getAnyURI() { return this.anyURIEDataType; }
/*      */   public EDataType getBase64Binary() { return this.base64BinaryEDataType; }
/*      */   public EDataType getBoolean() { return this.booleanEDataType; }
/*      */   public EDataType getBooleanObject() { return this.booleanObjectEDataType; }
/*      */   public EDataType getDecimal() { return this.decimalEDataType; }
/*      */   public EDataType getInteger() { return this.integerEDataType; }
/*      */   public EDataType getIntObject() { return this.intObjectEDataType; }
/* 1461 */   public void initializePackageContents() { if (this.isInitialized)
/* 1462 */       return;  this.isInitialized = true;
/*      */ 
/*      */     
/* 1465 */     setName("type");
/* 1466 */     setNsPrefix("ecore.xml.type");
/* 1467 */     setNsURI("http://www.eclipse.org/emf/2003/XMLType");
/*      */ 
/*      */     
/* 1470 */     XMLTypePackage theXMLTypePackage_1 = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2003/XMLType");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1477 */     this.simpleAnyTypeEClass.getESuperTypes().add(getAnyType());
/*      */ 
/*      */     
/* 1480 */     initEClass(this.anyTypeEClass, AnyType.class, "AnyType", false, false, true);
/* 1481 */     initEAttribute(getAnyType_Mixed(), (EClassifier)this.ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, AnyType.class, false, false, true, false, false, false, false, true);
/* 1482 */     initEAttribute(getAnyType_Any(), (EClassifier)this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, AnyType.class, true, true, true, false, false, false, true, true);
/* 1483 */     initEAttribute(getAnyType_AnyAttribute(), (EClassifier)this.ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, AnyType.class, false, false, true, false, false, false, false, true);
/*      */     
/* 1485 */     initEClass(this.processingInstructionEClass, ProcessingInstruction.class, "ProcessingInstruction", false, false, true);
/* 1486 */     initEAttribute(getProcessingInstruction_Data(), (EClassifier)getString(), "data", null, 0, 1, ProcessingInstruction.class, false, false, true, false, false, true, false, true);
/* 1487 */     initEAttribute(getProcessingInstruction_Target(), (EClassifier)getString(), "target", null, 1, 1, ProcessingInstruction.class, false, false, true, false, false, true, false, true);
/*      */     
/* 1489 */     initEClass(this.simpleAnyTypeEClass, SimpleAnyType.class, "SimpleAnyType", false, false, true);
/* 1490 */     initEAttribute(getSimpleAnyType_RawValue(), (EClassifier)theXMLTypePackage_1.getString(), "rawValue", null, 0, 1, SimpleAnyType.class, true, true, true, false, false, true, true, true);
/* 1491 */     initEAttribute(getSimpleAnyType_Value(), (EClassifier)theXMLTypePackage_1.getAnySimpleType(), "value", null, 0, 1, SimpleAnyType.class, true, true, true, false, false, true, true, true);
/* 1492 */     initEReference(getSimpleAnyType_InstanceType(), (EClassifier)this.ecorePackage.getEDataType(), null, "instanceType", null, 1, 1, SimpleAnyType.class, false, false, true, false, false, false, true, false, true);
/*      */     
/* 1494 */     initEClass(this.xmlTypeDocumentRootEClass, XMLTypeDocumentRoot.class, "XMLTypeDocumentRoot", false, false, true);
/* 1495 */     initEAttribute(getXMLTypeDocumentRoot_Mixed(), (EClassifier)this.ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, false, false, true, false, false, false, false, true);
/* 1496 */     initEReference(getXMLTypeDocumentRoot_XMLNSPrefixMap(), (EClassifier)this.ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, true, false, true, true, false, false, true, false, true);
/* 1497 */     initEReference(getXMLTypeDocumentRoot_XSISchemaLocation(), (EClassifier)this.ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, true, false, true, true, false, false, true, false, true);
/* 1498 */     initEAttribute(getXMLTypeDocumentRoot_CDATA(), (EClassifier)getString(), "cDATA", null, 0, -2, null, true, true, true, false, false, false, true, true);
/* 1499 */     initEAttribute(getXMLTypeDocumentRoot_Comment(), (EClassifier)getString(), "comment", null, 0, -2, null, true, true, true, false, false, false, true, true);
/* 1500 */     initEReference(getXMLTypeDocumentRoot_ProcessingInstruction(), (EClassifier)getProcessingInstruction(), null, "processingInstruction", null, 0, -2, null, true, true, true, true, false, false, true, true, true);
/* 1501 */     initEAttribute(getXMLTypeDocumentRoot_Text(), (EClassifier)getString(), "text", null, 0, -2, null, true, true, true, false, false, false, true, true);
/*      */ 
/*      */     
/* 1504 */     initEDataType(this.anySimpleTypeEDataType, Object.class, "AnySimpleType", true, false);
/* 1505 */     initEDataType(this.anyURIEDataType, String.class, "AnyURI", true, false);
/* 1506 */     initEDataType(this.base64BinaryEDataType, byte[].class, "Base64Binary", true, false);
/* 1507 */     initEDataType(this.booleanEDataType, boolean.class, "Boolean", true, false);
/* 1508 */     initEDataType(this.booleanObjectEDataType, Boolean.class, "BooleanObject", true, false);
/* 1509 */     initEDataType(this.byteEDataType, byte.class, "Byte", true, false);
/* 1510 */     initEDataType(this.byteObjectEDataType, Byte.class, "ByteObject", true, false);
/* 1511 */     initEDataType(this.dateEDataType, XMLGregorianCalendar.class, "Date", true, false);
/* 1512 */     initEDataType(this.dateTimeEDataType, XMLGregorianCalendar.class, "DateTime", true, false);
/* 1513 */     initEDataType(this.decimalEDataType, BigDecimal.class, "Decimal", true, false);
/* 1514 */     initEDataType(this.doubleEDataType, double.class, "Double", true, false);
/* 1515 */     initEDataType(this.doubleObjectEDataType, Double.class, "DoubleObject", true, false);
/* 1516 */     initEDataType(this.durationEDataType, Duration.class, "Duration", true, false);
/* 1517 */     initEDataType(this.entitiesEDataType, List.class, "ENTITIES", true, false);
/* 1518 */     initEDataType(this.entitiesBaseEDataType, List.class, "ENTITIESBase", true, false);
/* 1519 */     initEDataType(this.entityEDataType, String.class, "ENTITY", true, false);
/* 1520 */     initEDataType(this.floatEDataType, float.class, "Float", true, false);
/* 1521 */     initEDataType(this.floatObjectEDataType, Float.class, "FloatObject", true, false);
/* 1522 */     initEDataType(this.gDayEDataType, XMLGregorianCalendar.class, "GDay", true, false);
/* 1523 */     initEDataType(this.gMonthEDataType, XMLGregorianCalendar.class, "GMonth", true, false);
/* 1524 */     initEDataType(this.gMonthDayEDataType, XMLGregorianCalendar.class, "GMonthDay", true, false);
/* 1525 */     initEDataType(this.gYearEDataType, XMLGregorianCalendar.class, "GYear", true, false);
/* 1526 */     initEDataType(this.gYearMonthEDataType, XMLGregorianCalendar.class, "GYearMonth", true, false);
/* 1527 */     initEDataType(this.hexBinaryEDataType, byte[].class, "HexBinary", true, false);
/* 1528 */     initEDataType(this.idEDataType, String.class, "ID", true, false);
/* 1529 */     initEDataType(this.idrefEDataType, String.class, "IDREF", true, false);
/* 1530 */     initEDataType(this.idrefsEDataType, List.class, "IDREFS", true, false);
/* 1531 */     initEDataType(this.idrefsBaseEDataType, List.class, "IDREFSBase", true, false);
/* 1532 */     initEDataType(this.intEDataType, int.class, "Int", true, false);
/* 1533 */     initEDataType(this.integerEDataType, BigInteger.class, "Integer", true, false);
/* 1534 */     initEDataType(this.intObjectEDataType, Integer.class, "IntObject", true, false);
/* 1535 */     initEDataType(this.languageEDataType, String.class, "Language", true, false);
/* 1536 */     initEDataType(this.longEDataType, long.class, "Long", true, false);
/* 1537 */     initEDataType(this.longObjectEDataType, Long.class, "LongObject", true, false);
/* 1538 */     initEDataType(this.nameEDataType, String.class, "Name", true, false);
/* 1539 */     initEDataType(this.ncNameEDataType, String.class, "NCName", true, false);
/* 1540 */     initEDataType(this.negativeIntegerEDataType, BigInteger.class, "NegativeInteger", true, false);
/* 1541 */     initEDataType(this.nmtokenEDataType, String.class, "NMTOKEN", true, false);
/* 1542 */     initEDataType(this.nmtokensEDataType, List.class, "NMTOKENS", true, false);
/* 1543 */     initEDataType(this.nmtokensBaseEDataType, List.class, "NMTOKENSBase", true, false);
/* 1544 */     initEDataType(this.nonNegativeIntegerEDataType, BigInteger.class, "NonNegativeInteger", true, false);
/* 1545 */     initEDataType(this.nonPositiveIntegerEDataType, BigInteger.class, "NonPositiveInteger", true, false);
/* 1546 */     initEDataType(this.normalizedStringEDataType, String.class, "NormalizedString", true, false);
/* 1547 */     initEDataType(this.notationEDataType, QName.class, "NOTATION", true, false);
/* 1548 */     initEDataType(this.positiveIntegerEDataType, BigInteger.class, "PositiveInteger", true, false);
/* 1549 */     initEDataType(this.qNameEDataType, QName.class, "QName", true, false);
/* 1550 */     initEDataType(this.shortEDataType, short.class, "Short", true, false);
/* 1551 */     initEDataType(this.shortObjectEDataType, Short.class, "ShortObject", true, false);
/* 1552 */     initEDataType(this.stringEDataType, String.class, "String", true, false);
/* 1553 */     initEDataType(this.timeEDataType, XMLGregorianCalendar.class, "Time", true, false);
/* 1554 */     initEDataType(this.tokenEDataType, String.class, "Token", true, false);
/* 1555 */     initEDataType(this.unsignedByteEDataType, short.class, "UnsignedByte", true, false);
/* 1556 */     initEDataType(this.unsignedByteObjectEDataType, Short.class, "UnsignedByteObject", true, false);
/* 1557 */     initEDataType(this.unsignedIntEDataType, long.class, "UnsignedInt", true, false);
/* 1558 */     initEDataType(this.unsignedIntObjectEDataType, Long.class, "UnsignedIntObject", true, false);
/* 1559 */     initEDataType(this.unsignedLongEDataType, BigInteger.class, "UnsignedLong", true, false);
/* 1560 */     initEDataType(this.unsignedShortEDataType, int.class, "UnsignedShort", true, false);
/* 1561 */     initEDataType(this.unsignedShortObjectEDataType, Integer.class, "UnsignedShortObject", true, false);
/*      */ 
/*      */     
/* 1564 */     createResource("http://www.eclipse.org/emf/2003/XMLType");
/*      */ 
/*      */ 
/*      */     
/* 1568 */     createExtendedMetaDataAnnotations(); }
/*      */   public EDataType getLong() { return this.longEDataType; }
/*      */   public EDataType getLongObject() { return this.longObjectEDataType; }
/*      */   public EDataType getInt() { return this.intEDataType; }
/*      */   public EDataType getShort() { return this.shortEDataType; }
/*      */   public EDataType getShortObject() { return this.shortObjectEDataType; }
/*      */   public EDataType getByte() { return this.byteEDataType; }
/*      */   public EDataType getByteObject() { return this.byteObjectEDataType; }
/*      */   public EDataType getDate() { return this.dateEDataType; }
/*      */   public EDataType getDateTime() { return this.dateTimeEDataType; }
/*      */   public EDataType getString() { return this.stringEDataType; }
/* 1579 */   public EDataType getDouble() { return this.doubleEDataType; } public EDataType getDoubleObject() { return this.doubleObjectEDataType; } public EDataType getDuration() { return this.durationEDataType; } public EDataType getENTITIESBase() { return this.entitiesBaseEDataType; } public EDataType getNormalizedString() { return this.normalizedStringEDataType; } public EDataType getToken() { return this.tokenEDataType; } public EDataType getName_() { return this.nameEDataType; } public EDataType getNCName() { return this.ncNameEDataType; } public EDataType getENTITY() { return this.entityEDataType; } public EDataType getENTITIES() { return this.entitiesEDataType; } protected void createExtendedMetaDataAnnotations() { String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
/* 1580 */     addAnnotation(
/* 1581 */         (ENamedElement)this.anySimpleTypeEDataType, 
/* 1582 */         source, 
/*      */         
/* 1584 */         new String[] {
/* 1585 */           "name", "anySimpleType"
/*      */         });
/* 1587 */     addAnnotation(
/* 1588 */         (ENamedElement)this.anyTypeEClass, 
/* 1589 */         source, 
/*      */         
/* 1591 */         new String[] {
/* 1592 */           "name", "anyType", 
/* 1593 */           "kind", "mixed"
/*      */         });
/* 1595 */     addAnnotation(
/* 1596 */         (ENamedElement)getAnyType_Mixed(), 
/* 1597 */         source, 
/*      */         
/* 1599 */         new String[] {
/* 1600 */           "kind", "elementWildcard", 
/* 1601 */           "name", ":mixed"
/*      */         });
/* 1603 */     addAnnotation(
/* 1604 */         (ENamedElement)getAnyType_Any(), 
/* 1605 */         source, 
/*      */         
/* 1607 */         new String[] {
/* 1608 */           "kind", "elementWildcard", 
/* 1609 */           "wildcards", "##any", 
/* 1610 */           "name", ":1", 
/* 1611 */           "processing", "lax"
/*      */         });
/* 1613 */     addAnnotation(
/* 1614 */         (ENamedElement)getAnyType_AnyAttribute(), 
/* 1615 */         source, 
/*      */         
/* 1617 */         new String[] {
/* 1618 */           "kind", "attributeWildcard", 
/* 1619 */           "wildcards", "##any", 
/* 1620 */           "name", ":2", 
/* 1621 */           "processing", "lax"
/*      */         });
/* 1623 */     addAnnotation(
/* 1624 */         (ENamedElement)this.anyURIEDataType, 
/* 1625 */         source, 
/*      */         
/* 1627 */         new String[] {
/* 1628 */           "name", "anyURI", 
/* 1629 */           "whiteSpace", "collapse"
/*      */         });
/* 1631 */     addAnnotation(
/* 1632 */         (ENamedElement)this.base64BinaryEDataType, 
/* 1633 */         source, 
/*      */         
/* 1635 */         new String[] {
/* 1636 */           "name", "base64Binary", 
/* 1637 */           "whiteSpace", "collapse"
/*      */         });
/* 1639 */     addAnnotation(
/* 1640 */         (ENamedElement)this.booleanEDataType, 
/* 1641 */         source, 
/*      */         
/* 1643 */         new String[] {
/* 1644 */           "name", "boolean", 
/* 1645 */           "whiteSpace", "collapse"
/*      */         });
/* 1647 */     addAnnotation(
/* 1648 */         (ENamedElement)this.booleanObjectEDataType, 
/* 1649 */         source, 
/*      */         
/* 1651 */         new String[] {
/* 1652 */           "name", "boolean:Object", 
/* 1653 */           "baseType", "boolean"
/*      */         });
/* 1655 */     addAnnotation(
/* 1656 */         (ENamedElement)this.byteEDataType, 
/* 1657 */         source, 
/*      */         
/* 1659 */         new String[] {
/* 1660 */           "name", "byte"
/*      */         });
/* 1662 */     addAnnotation(
/* 1663 */         (ENamedElement)this.byteObjectEDataType, 
/* 1664 */         source, 
/*      */         
/* 1666 */         new String[] {
/* 1667 */           "name", "byte:Object", 
/* 1668 */           "baseType", "byte"
/*      */         });
/* 1670 */     addAnnotation(
/* 1671 */         (ENamedElement)this.dateEDataType, 
/* 1672 */         source, 
/*      */         
/* 1674 */         new String[] {
/* 1675 */           "name", "date", 
/* 1676 */           "whiteSpace", "collapse"
/*      */         });
/* 1678 */     addAnnotation(
/* 1679 */         (ENamedElement)this.dateTimeEDataType, 
/* 1680 */         source, 
/*      */         
/* 1682 */         new String[] {
/* 1683 */           "name", "dateTime", 
/* 1684 */           "whiteSpace", "collapse"
/*      */         });
/* 1686 */     addAnnotation(
/* 1687 */         (ENamedElement)this.decimalEDataType, 
/* 1688 */         source, 
/*      */         
/* 1690 */         new String[] {
/* 1691 */           "name", "decimal", 
/* 1692 */           "whiteSpace", "collapse"
/*      */         });
/* 1694 */     addAnnotation(
/* 1695 */         (ENamedElement)this.doubleEDataType, 
/* 1696 */         source, 
/*      */         
/* 1698 */         new String[] {
/* 1699 */           "name", "double", 
/* 1700 */           "whiteSpace", "collapse"
/*      */         });
/* 1702 */     addAnnotation(
/* 1703 */         (ENamedElement)this.doubleObjectEDataType, 
/* 1704 */         source, 
/*      */         
/* 1706 */         new String[] {
/* 1707 */           "name", "double:Object", 
/* 1708 */           "baseType", "double"
/*      */         });
/* 1710 */     addAnnotation(
/* 1711 */         (ENamedElement)this.durationEDataType, 
/* 1712 */         source, 
/*      */         
/* 1714 */         new String[] {
/* 1715 */           "name", "duration", 
/* 1716 */           "whiteSpace", "collapse"
/*      */         });
/* 1718 */     addAnnotation(
/* 1719 */         (ENamedElement)this.entitiesEDataType, 
/* 1720 */         source, 
/*      */         
/* 1722 */         new String[] {
/* 1723 */           "name", "ENTITIES", 
/* 1724 */           "baseType", "ENTITIES_._base", 
/* 1725 */           "minLength", "1"
/*      */         });
/* 1727 */     addAnnotation(
/* 1728 */         (ENamedElement)this.entitiesBaseEDataType, 
/* 1729 */         source, 
/*      */         
/* 1731 */         new String[] {
/* 1732 */           "name", "ENTITIES_._base", 
/* 1733 */           "itemType", "ENTITY"
/*      */         });
/* 1735 */     addAnnotation(
/* 1736 */         (ENamedElement)this.entityEDataType, 
/* 1737 */         source, 
/*      */         
/* 1739 */         new String[] {
/* 1740 */           "name", "ENTITY", 
/* 1741 */           "baseType", "NCName"
/*      */         });
/* 1743 */     addAnnotation(
/* 1744 */         (ENamedElement)this.floatEDataType, 
/* 1745 */         source, 
/*      */         
/* 1747 */         new String[] {
/* 1748 */           "name", "float", 
/* 1749 */           "whiteSpace", "collapse"
/*      */         });
/* 1751 */     addAnnotation(
/* 1752 */         (ENamedElement)this.floatObjectEDataType, 
/* 1753 */         source, 
/*      */         
/* 1755 */         new String[] {
/* 1756 */           "name", "float:Object", 
/* 1757 */           "baseType", "float"
/*      */         });
/* 1759 */     addAnnotation(
/* 1760 */         (ENamedElement)this.gDayEDataType, 
/* 1761 */         source, 
/*      */         
/* 1763 */         new String[] {
/* 1764 */           "name", "gDay", 
/* 1765 */           "whiteSpace", "collapse"
/*      */         });
/* 1767 */     addAnnotation(
/* 1768 */         (ENamedElement)this.gMonthEDataType, 
/* 1769 */         source, 
/*      */         
/* 1771 */         new String[] {
/* 1772 */           "name", "gMonth", 
/* 1773 */           "whiteSpace", "collapse"
/*      */         });
/* 1775 */     addAnnotation(
/* 1776 */         (ENamedElement)this.gMonthDayEDataType, 
/* 1777 */         source, 
/*      */         
/* 1779 */         new String[] {
/* 1780 */           "name", "gMonthDay", 
/* 1781 */           "whiteSpace", "collapse"
/*      */         });
/* 1783 */     addAnnotation(
/* 1784 */         (ENamedElement)this.gYearEDataType, 
/* 1785 */         source, 
/*      */         
/* 1787 */         new String[] {
/* 1788 */           "name", "gYear", 
/* 1789 */           "whiteSpace", "collapse"
/*      */         });
/* 1791 */     addAnnotation(
/* 1792 */         (ENamedElement)this.gYearMonthEDataType, 
/* 1793 */         source, 
/*      */         
/* 1795 */         new String[] {
/* 1796 */           "name", "gYearMonth", 
/* 1797 */           "whiteSpace", "collapse"
/*      */         });
/* 1799 */     addAnnotation(
/* 1800 */         (ENamedElement)this.hexBinaryEDataType, 
/* 1801 */         source, 
/*      */         
/* 1803 */         new String[] {
/* 1804 */           "name", "hexBinary", 
/* 1805 */           "whiteSpace", "collapse"
/*      */         });
/* 1807 */     addAnnotation(
/* 1808 */         (ENamedElement)this.idEDataType, 
/* 1809 */         source, 
/*      */         
/* 1811 */         new String[] {
/* 1812 */           "name", "ID", 
/* 1813 */           "baseType", "NCName"
/*      */         });
/* 1815 */     addAnnotation(
/* 1816 */         (ENamedElement)this.idrefEDataType, 
/* 1817 */         source, 
/*      */         
/* 1819 */         new String[] {
/* 1820 */           "name", "IDREF", 
/* 1821 */           "baseType", "NCName"
/*      */         });
/* 1823 */     addAnnotation(
/* 1824 */         (ENamedElement)this.idrefsEDataType, 
/* 1825 */         source, 
/*      */         
/* 1827 */         new String[] {
/* 1828 */           "name", "IDREFS", 
/* 1829 */           "baseType", "IDREFS_._base", 
/* 1830 */           "minLength", "1"
/*      */         });
/* 1832 */     addAnnotation(
/* 1833 */         (ENamedElement)this.idrefsBaseEDataType, 
/* 1834 */         source, 
/*      */         
/* 1836 */         new String[] {
/* 1837 */           "name", "IDREFS_._base", 
/* 1838 */           "itemType", "IDREF"
/*      */         });
/* 1840 */     addAnnotation(
/* 1841 */         (ENamedElement)this.intEDataType, 
/* 1842 */         source, 
/*      */         
/* 1844 */         new String[] {
/* 1845 */           "name", "int"
/*      */         });
/* 1847 */     addAnnotation(
/* 1848 */         (ENamedElement)this.integerEDataType, 
/* 1849 */         source, 
/*      */         
/* 1851 */         new String[] {
/* 1852 */           "name", "integer"
/*      */         });
/* 1854 */     addAnnotation(
/* 1855 */         (ENamedElement)this.intObjectEDataType, 
/* 1856 */         source, 
/*      */         
/* 1858 */         new String[] {
/* 1859 */           "name", "int:Object", 
/* 1860 */           "baseType", "int"
/*      */         });
/* 1862 */     addAnnotation(
/* 1863 */         (ENamedElement)this.languageEDataType, 
/* 1864 */         source, 
/*      */         
/* 1866 */         new String[] {
/* 1867 */           "name", "language", 
/* 1868 */           "baseType", "token", 
/* 1869 */           "pattern", "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*"
/*      */         });
/* 1871 */     addAnnotation(
/* 1872 */         (ENamedElement)this.longEDataType, 
/* 1873 */         source, 
/*      */         
/* 1875 */         new String[] {
/* 1876 */           "name", "long"
/*      */         });
/* 1878 */     addAnnotation(
/* 1879 */         (ENamedElement)this.longObjectEDataType, 
/* 1880 */         source, 
/*      */         
/* 1882 */         new String[] {
/* 1883 */           "name", "long:Object", 
/* 1884 */           "baseType", "long"
/*      */         });
/* 1886 */     addAnnotation(
/* 1887 */         (ENamedElement)this.nameEDataType, 
/* 1888 */         source, 
/*      */         
/* 1890 */         new String[] {
/* 1891 */           "name", "Name", 
/* 1892 */           "baseType", "token", 
/* 1893 */           "pattern", "\\i\\c*"
/*      */         });
/* 1895 */     addAnnotation(
/* 1896 */         (ENamedElement)this.ncNameEDataType, 
/* 1897 */         source, 
/*      */         
/* 1899 */         new String[] {
/* 1900 */           "name", "NCName", 
/* 1901 */           "baseType", "Name", 
/* 1902 */           "pattern", "[\\i-[:]][\\c-[:]]*"
/*      */         });
/* 1904 */     addAnnotation(
/* 1905 */         (ENamedElement)this.negativeIntegerEDataType, 
/* 1906 */         source, 
/*      */         
/* 1908 */         new String[] {
/* 1909 */           "name", "negativeInteger", 
/* 1910 */           "baseType", "nonPositiveInteger", 
/* 1911 */           "maxInclusive", "-1"
/*      */         });
/* 1913 */     addAnnotation(
/* 1914 */         (ENamedElement)this.nmtokenEDataType, 
/* 1915 */         source, 
/*      */         
/* 1917 */         new String[] {
/* 1918 */           "name", "NMTOKEN", 
/* 1919 */           "baseType", "token", 
/* 1920 */           "pattern", "\\c+"
/*      */         });
/* 1922 */     addAnnotation(
/* 1923 */         (ENamedElement)this.nmtokensEDataType, 
/* 1924 */         source, 
/*      */         
/* 1926 */         new String[] {
/* 1927 */           "name", "NMTOKENS", 
/* 1928 */           "baseType", "NMTOKENS_._base", 
/* 1929 */           "minLength", "1"
/*      */         });
/* 1931 */     addAnnotation(
/* 1932 */         (ENamedElement)this.nmtokensBaseEDataType, 
/* 1933 */         source, 
/*      */         
/* 1935 */         new String[] {
/* 1936 */           "name", "NMTOKENS_._base", 
/* 1937 */           "itemType", "NMTOKEN"
/*      */         });
/* 1939 */     addAnnotation(
/* 1940 */         (ENamedElement)this.nonNegativeIntegerEDataType, 
/* 1941 */         source, 
/*      */         
/* 1943 */         new String[] {
/* 1944 */           "name", "nonNegativeInteger", 
/* 1945 */           "baseType", "integer", 
/* 1946 */           "minInclusive", "0"
/*      */         });
/* 1948 */     addAnnotation(
/* 1949 */         (ENamedElement)this.nonPositiveIntegerEDataType, 
/* 1950 */         source, 
/*      */         
/* 1952 */         new String[] {
/* 1953 */           "name", "nonPositiveInteger", 
/* 1954 */           "baseType", "integer", 
/* 1955 */           "maxInclusive", "0"
/*      */         });
/* 1957 */     addAnnotation(
/* 1958 */         (ENamedElement)this.normalizedStringEDataType, 
/* 1959 */         source, 
/*      */         
/* 1961 */         new String[] {
/* 1962 */           "name", "normalizedString", 
/* 1963 */           "baseType", "string", 
/* 1964 */           "whiteSpace", "replace"
/*      */         });
/* 1966 */     addAnnotation(
/* 1967 */         (ENamedElement)this.notationEDataType, 
/* 1968 */         source, 
/*      */         
/* 1970 */         new String[] {
/* 1971 */           "name", "NOTATION", 
/* 1972 */           "whiteSpace", "collapse"
/*      */         });
/* 1974 */     addAnnotation(
/* 1975 */         (ENamedElement)this.positiveIntegerEDataType, 
/* 1976 */         source, 
/*      */         
/* 1978 */         new String[] {
/* 1979 */           "name", "positiveInteger", 
/* 1980 */           "baseType", "nonNegativeInteger", 
/* 1981 */           "minInclusive", "1"
/*      */         });
/* 1983 */     addAnnotation(
/* 1984 */         (ENamedElement)this.processingInstructionEClass, 
/* 1985 */         source, 
/*      */         
/* 1987 */         new String[] {
/* 1988 */           "name", "processingInstruction_._type", 
/* 1989 */           "kind", "empty"
/*      */         });
/* 1991 */     addAnnotation(
/* 1992 */         (ENamedElement)getProcessingInstruction_Data(), 
/* 1993 */         source, 
/*      */         
/* 1995 */         new String[] {
/* 1996 */           "kind", "attribute", 
/* 1997 */           "name", "data"
/*      */         });
/* 1999 */     addAnnotation(
/* 2000 */         (ENamedElement)getProcessingInstruction_Target(), 
/* 2001 */         source, 
/*      */         
/* 2003 */         new String[] {
/* 2004 */           "kind", "attribute", 
/* 2005 */           "name", "target"
/*      */         });
/* 2007 */     addAnnotation(
/* 2008 */         (ENamedElement)this.qNameEDataType, 
/* 2009 */         source, 
/*      */         
/* 2011 */         new String[] {
/* 2012 */           "name", "QName", 
/* 2013 */           "whiteSpace", "collapse"
/*      */         });
/* 2015 */     addAnnotation(
/* 2016 */         (ENamedElement)this.shortEDataType, 
/* 2017 */         source, 
/*      */         
/* 2019 */         new String[] {
/* 2020 */           "name", "short"
/*      */         });
/* 2022 */     addAnnotation(
/* 2023 */         (ENamedElement)this.shortObjectEDataType, 
/* 2024 */         source, 
/*      */         
/* 2026 */         new String[] {
/* 2027 */           "name", "short:Object", 
/* 2028 */           "baseType", "short"
/*      */         });
/* 2030 */     addAnnotation(
/* 2031 */         (ENamedElement)this.simpleAnyTypeEClass, 
/* 2032 */         source, 
/*      */         
/* 2034 */         new String[] {
/* 2035 */           "name", "simpleAnyType", 
/* 2036 */           "kind", "simple"
/*      */         });
/* 2038 */     addAnnotation(
/* 2039 */         (ENamedElement)getSimpleAnyType_RawValue(), 
/* 2040 */         source, 
/*      */         
/* 2042 */         new String[] {
/* 2043 */           "name", ":3", 
/* 2044 */           "kind", "simple"
/*      */         });
/* 2046 */     addAnnotation(
/* 2047 */         (ENamedElement)getSimpleAnyType_Value(), 
/* 2048 */         source, 
/*      */         
/* 2050 */         new String[] {
/* 2051 */           "name", ":4", 
/* 2052 */           "kind", "simple"
/*      */         });
/* 2054 */     addAnnotation(
/* 2055 */         (ENamedElement)getSimpleAnyType_InstanceType(), 
/* 2056 */         source, 
/*      */         
/* 2058 */         new String[] {
/* 2059 */           "name", ":5", 
/* 2060 */           "kind", "simple"
/*      */         });
/* 2062 */     addAnnotation(
/* 2063 */         (ENamedElement)this.stringEDataType, 
/* 2064 */         source, 
/*      */         
/* 2066 */         new String[] {
/* 2067 */           "name", "string", 
/* 2068 */           "whiteSpace", "preserve"
/*      */         });
/* 2070 */     addAnnotation(
/* 2071 */         (ENamedElement)this.timeEDataType, 
/* 2072 */         source, 
/*      */         
/* 2074 */         new String[] {
/* 2075 */           "name", "time", 
/* 2076 */           "whiteSpace", "collapse"
/*      */         });
/* 2078 */     addAnnotation(
/* 2079 */         (ENamedElement)this.tokenEDataType, 
/* 2080 */         source, 
/*      */         
/* 2082 */         new String[] {
/* 2083 */           "name", "token", 
/* 2084 */           "baseType", "normalizedString", 
/* 2085 */           "whiteSpace", "collapse"
/*      */         });
/* 2087 */     addAnnotation(
/* 2088 */         (ENamedElement)this.unsignedByteEDataType, 
/* 2089 */         source, 
/*      */         
/* 2091 */         new String[] {
/* 2092 */           "name", "unsignedByte", 
/* 2093 */           "maxInclusive", "255", 
/* 2094 */           "minInclusive", "0"
/*      */         });
/* 2096 */     addAnnotation(
/* 2097 */         (ENamedElement)this.unsignedByteObjectEDataType, 
/* 2098 */         source, 
/*      */         
/* 2100 */         new String[] {
/* 2101 */           "name", "unsignedByte:Object", 
/* 2102 */           "baseType", "unsignedByte"
/*      */         });
/* 2104 */     addAnnotation(
/* 2105 */         (ENamedElement)this.unsignedIntEDataType, 
/* 2106 */         source, 
/*      */         
/* 2108 */         new String[] {
/* 2109 */           "name", "unsignedInt", 
/* 2110 */           "maxInclusive", "4294967295", 
/* 2111 */           "minInclusive", "0"
/*      */         });
/* 2113 */     addAnnotation(
/* 2114 */         (ENamedElement)this.unsignedIntObjectEDataType, 
/* 2115 */         source, 
/*      */         
/* 2117 */         new String[] {
/* 2118 */           "name", "unsignedInt:Object", 
/* 2119 */           "baseType", "unsignedInt"
/*      */         });
/* 2121 */     addAnnotation(
/* 2122 */         (ENamedElement)this.unsignedLongEDataType, 
/* 2123 */         source, 
/*      */         
/* 2125 */         new String[] {
/* 2126 */           "name", "unsignedLong", 
/* 2127 */           "baseType", "nonNegativeInteger", 
/* 2128 */           "maxInclusive", "18446744073709551615", 
/* 2129 */           "minInclusive", "0"
/*      */         });
/* 2131 */     addAnnotation(
/* 2132 */         (ENamedElement)this.unsignedShortEDataType, 
/* 2133 */         source, 
/*      */         
/* 2135 */         new String[] {
/* 2136 */           "name", "unsignedShort", 
/* 2137 */           "maxInclusive", "65535", 
/* 2138 */           "minInclusive", "0"
/*      */         });
/* 2140 */     addAnnotation(
/* 2141 */         (ENamedElement)this.unsignedShortObjectEDataType, 
/* 2142 */         source, 
/*      */         
/* 2144 */         new String[] {
/* 2145 */           "name", "unsignedShort:Object", 
/* 2146 */           "baseType", "unsignedShort"
/*      */         });
/* 2148 */     addAnnotation(
/* 2149 */         (ENamedElement)this.xmlTypeDocumentRootEClass, 
/* 2150 */         source, 
/*      */         
/* 2152 */         new String[] {
/* 2153 */           "name", "", 
/* 2154 */           "kind", "mixed"
/*      */         });
/* 2156 */     addAnnotation(
/* 2157 */         (ENamedElement)getXMLTypeDocumentRoot_Mixed(), 
/* 2158 */         source, 
/*      */         
/* 2160 */         new String[] {
/* 2161 */           "kind", "elementWildcard", 
/* 2162 */           "name", ":mixed"
/*      */         });
/* 2164 */     addAnnotation(
/* 2165 */         (ENamedElement)getXMLTypeDocumentRoot_XMLNSPrefixMap(), 
/* 2166 */         source, 
/*      */         
/* 2168 */         new String[] {
/* 2169 */           "kind", "attribute", 
/* 2170 */           "name", "xmlns:prefix"
/*      */         });
/* 2172 */     addAnnotation(
/* 2173 */         (ENamedElement)getXMLTypeDocumentRoot_XSISchemaLocation(), 
/* 2174 */         source, 
/*      */         
/* 2176 */         new String[] {
/* 2177 */           "kind", "attribute", 
/* 2178 */           "name", "xsi:schemaLocation"
/*      */         });
/* 2180 */     addAnnotation(
/* 2181 */         (ENamedElement)getXMLTypeDocumentRoot_CDATA(), 
/* 2182 */         source, 
/*      */         
/* 2184 */         new String[] {
/* 2185 */           "kind", "element", 
/* 2186 */           "name", "cDATA", 
/* 2187 */           "namespace", "##targetNamespace"
/*      */         });
/* 2189 */     addAnnotation(
/* 2190 */         (ENamedElement)getXMLTypeDocumentRoot_Comment(), 
/* 2191 */         source, 
/*      */         
/* 2193 */         new String[] {
/* 2194 */           "kind", "element", 
/* 2195 */           "name", "comment", 
/* 2196 */           "namespace", "##targetNamespace"
/*      */         });
/* 2198 */     addAnnotation(
/* 2199 */         (ENamedElement)getXMLTypeDocumentRoot_ProcessingInstruction(), 
/* 2200 */         source, 
/*      */         
/* 2202 */         new String[] {
/* 2203 */           "kind", "element", 
/* 2204 */           "name", "processingInstruction", 
/* 2205 */           "namespace", "##targetNamespace"
/*      */         });
/* 2207 */     addAnnotation(
/* 2208 */         (ENamedElement)getXMLTypeDocumentRoot_Text(), 
/* 2209 */         source, 
/*      */         
/* 2211 */         new String[] {
/* 2212 */           "kind", "element", 
/* 2213 */           "name", "text", 
/* 2214 */           "namespace", "##targetNamespace" }); }
/*      */ 
/*      */   
/*      */   public EDataType getFloat() {
/*      */     return this.floatEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getFloatObject() {
/*      */     return this.floatObjectEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getGDay() {
/*      */     return this.gDayEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getGMonth() {
/*      */     return this.gMonthEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getGMonthDay() {
/*      */     return this.gMonthDayEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getGYear() {
/*      */     return this.gYearEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getGYearMonth() {
/*      */     return this.gYearMonthEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getHexBinary() {
/*      */     return this.hexBinaryEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getID() {
/*      */     return this.idEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getIDREF() {
/*      */     return this.idrefEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getIDREFSBase() {
/*      */     return this.idrefsBaseEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getIDREFS() {
/*      */     return this.idrefsEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getLanguage() {
/*      */     return this.languageEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNonPositiveInteger() {
/*      */     return this.nonPositiveIntegerEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNegativeInteger() {
/*      */     return this.negativeIntegerEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNMTOKEN() {
/*      */     return this.nmtokenEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNMTOKENSBase() {
/*      */     return this.nmtokensBaseEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNMTOKENS() {
/*      */     return this.nmtokensEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNonNegativeInteger() {
/*      */     return this.nonNegativeIntegerEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getNOTATION() {
/*      */     return this.notationEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getPositiveInteger() {
/*      */     return this.positiveIntegerEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getQName() {
/*      */     return this.qNameEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getTime() {
/*      */     return this.timeEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedLong() {
/*      */     return this.unsignedLongEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedInt() {
/*      */     return this.unsignedIntEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedIntObject() {
/*      */     return this.unsignedIntObjectEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedShort() {
/*      */     return this.unsignedShortEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedShortObject() {
/*      */     return this.unsignedShortObjectEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedByte() {
/*      */     return this.unsignedByteEDataType;
/*      */   }
/*      */   
/*      */   public EDataType getUnsignedByteObject() {
/*      */     return this.unsignedByteObjectEDataType;
/*      */   }
/*      */   
/*      */   public XMLTypeFactory getXMLTypeFactory() {
/*      */     return (XMLTypeFactory)getEFactoryInstance();
/*      */   }
/*      */   
/*      */   public void createPackageContents() {
/*      */     if (this.isCreated)
/*      */       return; 
/*      */     this.isCreated = true;
/*      */     this.anyTypeEClass = createEClass(0);
/*      */     createEAttribute(this.anyTypeEClass, 0);
/*      */     createEAttribute(this.anyTypeEClass, 1);
/*      */     createEAttribute(this.anyTypeEClass, 2);
/*      */     this.processingInstructionEClass = createEClass(1);
/*      */     createEAttribute(this.processingInstructionEClass, 0);
/*      */     createEAttribute(this.processingInstructionEClass, 1);
/*      */     this.simpleAnyTypeEClass = createEClass(2);
/*      */     createEAttribute(this.simpleAnyTypeEClass, 3);
/*      */     createEAttribute(this.simpleAnyTypeEClass, 4);
/*      */     createEReference(this.simpleAnyTypeEClass, 5);
/*      */     this.xmlTypeDocumentRootEClass = createEClass(3);
/*      */     createEAttribute(this.xmlTypeDocumentRootEClass, 0);
/*      */     createEReference(this.xmlTypeDocumentRootEClass, 1);
/*      */     createEReference(this.xmlTypeDocumentRootEClass, 2);
/*      */     createEAttribute(this.xmlTypeDocumentRootEClass, 3);
/*      */     createEAttribute(this.xmlTypeDocumentRootEClass, 4);
/*      */     createEReference(this.xmlTypeDocumentRootEClass, 5);
/*      */     createEAttribute(this.xmlTypeDocumentRootEClass, 6);
/*      */     this.anySimpleTypeEDataType = createEDataType(4);
/*      */     this.anyURIEDataType = createEDataType(5);
/*      */     this.base64BinaryEDataType = createEDataType(6);
/*      */     this.booleanEDataType = createEDataType(7);
/*      */     this.booleanObjectEDataType = createEDataType(8);
/*      */     this.byteEDataType = createEDataType(9);
/*      */     this.byteObjectEDataType = createEDataType(10);
/*      */     this.dateEDataType = createEDataType(11);
/*      */     this.dateTimeEDataType = createEDataType(12);
/*      */     this.decimalEDataType = createEDataType(13);
/*      */     this.doubleEDataType = createEDataType(14);
/*      */     this.doubleObjectEDataType = createEDataType(15);
/*      */     this.durationEDataType = createEDataType(16);
/*      */     this.entitiesEDataType = createEDataType(17);
/*      */     this.entitiesBaseEDataType = createEDataType(18);
/*      */     this.entityEDataType = createEDataType(19);
/*      */     this.floatEDataType = createEDataType(20);
/*      */     this.floatObjectEDataType = createEDataType(21);
/*      */     this.gDayEDataType = createEDataType(22);
/*      */     this.gMonthEDataType = createEDataType(23);
/*      */     this.gMonthDayEDataType = createEDataType(24);
/*      */     this.gYearEDataType = createEDataType(25);
/*      */     this.gYearMonthEDataType = createEDataType(26);
/*      */     this.hexBinaryEDataType = createEDataType(27);
/*      */     this.idEDataType = createEDataType(28);
/*      */     this.idrefEDataType = createEDataType(29);
/*      */     this.idrefsEDataType = createEDataType(30);
/*      */     this.idrefsBaseEDataType = createEDataType(31);
/*      */     this.intEDataType = createEDataType(32);
/*      */     this.integerEDataType = createEDataType(33);
/*      */     this.intObjectEDataType = createEDataType(34);
/*      */     this.languageEDataType = createEDataType(35);
/*      */     this.longEDataType = createEDataType(36);
/*      */     this.longObjectEDataType = createEDataType(37);
/*      */     this.nameEDataType = createEDataType(38);
/*      */     this.ncNameEDataType = createEDataType(39);
/*      */     this.negativeIntegerEDataType = createEDataType(40);
/*      */     this.nmtokenEDataType = createEDataType(41);
/*      */     this.nmtokensEDataType = createEDataType(42);
/*      */     this.nmtokensBaseEDataType = createEDataType(43);
/*      */     this.nonNegativeIntegerEDataType = createEDataType(44);
/*      */     this.nonPositiveIntegerEDataType = createEDataType(45);
/*      */     this.normalizedStringEDataType = createEDataType(46);
/*      */     this.notationEDataType = createEDataType(47);
/*      */     this.positiveIntegerEDataType = createEDataType(48);
/*      */     this.qNameEDataType = createEDataType(49);
/*      */     this.shortEDataType = createEDataType(50);
/*      */     this.shortObjectEDataType = createEDataType(51);
/*      */     this.stringEDataType = createEDataType(52);
/*      */     this.timeEDataType = createEDataType(53);
/*      */     this.tokenEDataType = createEDataType(54);
/*      */     this.unsignedByteEDataType = createEDataType(55);
/*      */     this.unsignedByteObjectEDataType = createEDataType(56);
/*      */     this.unsignedIntEDataType = createEDataType(57);
/*      */     this.unsignedIntObjectEDataType = createEDataType(58);
/*      */     this.unsignedLongEDataType = createEDataType(59);
/*      */     this.unsignedShortEDataType = createEDataType(60);
/*      */     this.unsignedShortObjectEDataType = createEDataType(61);
/*      */   }
/*      */   
/*      */   protected void createNullAnnotations() {}
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\xml\type\impl\XMLTypePackageImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */