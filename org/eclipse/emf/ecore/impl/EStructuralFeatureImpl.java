/*      */ package org.eclipse.emf.ecore.impl;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.Collection;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
/*      */ import org.eclipse.emf.common.util.BasicEMap;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.common.util.EMap;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EDataType;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EGenericType;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.BasicFeatureMap;
/*      */ import org.eclipse.emf.ecore.util.EDataTypeEList;
/*      */ import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectContainmentEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectResolvingEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
/*      */ import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
/*      */ import org.eclipse.emf.ecore.util.EcoreEList;
/*      */ import org.eclipse.emf.ecore.util.EcoreEMap;
/*      */ import org.eclipse.emf.ecore.util.EcoreUtil;
/*      */ import org.eclipse.emf.ecore.util.ExtendedMetaData;
/*      */ import org.eclipse.emf.ecore.util.FeatureMap;
/*      */ import org.eclipse.emf.ecore.util.InternalEList;
/*      */ import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class EStructuralFeatureImpl
/*      */   extends ETypedElementImpl
/*      */   implements EStructuralFeature, EStructuralFeature.Internal, BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder
/*      */ {
/*      */   public static abstract class InternalSettingDelegateSingle
/*      */     implements EStructuralFeature.Internal.SettingDelegate
/*      */   {
/* 1786 */     public static final Object NIL = EStructuralFeature.Internal.DynamicValueHolder.NIL;
/*      */     
/*      */     protected EStructuralFeature feature;
/*      */ 
/*      */     
/*      */     public InternalSettingDelegateSingle(EStructuralFeature feature) {
/* 1792 */       this.feature = feature;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature.Setting dynamicSetting(final InternalEObject owner, final EStructuralFeature.Internal.DynamicValueHolder settings, final int index) {
/* 1797 */       return 
/* 1798 */         new EStructuralFeature.Setting()
/*      */         {
/*      */           public EObject getEObject()
/*      */           {
/* 1802 */             return (EObject)owner;
/*      */           }
/*      */ 
/*      */           
/*      */           public EStructuralFeature getEStructuralFeature() {
/* 1807 */             return EStructuralFeatureImpl.InternalSettingDelegateSingle.this.feature;
/*      */           }
/*      */ 
/*      */           
/*      */           public Object get(boolean resolve) {
/* 1812 */             return EStructuralFeatureImpl.InternalSettingDelegateSingle.this.dynamicGet(owner, settings, index, resolve, true);
/*      */           }
/*      */ 
/*      */           
/*      */           public void set(Object newValue) {
/* 1817 */             EStructuralFeatureImpl.InternalSettingDelegateSingle.this.dynamicSet(owner, settings, index, newValue);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean isSet() {
/* 1822 */             return EStructuralFeatureImpl.InternalSettingDelegateSingle.this.dynamicIsSet(owner, settings, index);
/*      */           }
/*      */ 
/*      */           
/*      */           public void unset() {
/* 1827 */             EStructuralFeatureImpl.InternalSettingDelegateSingle.this.dynamicUnset(owner, settings, index);
/*      */           }
/*      */         };
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) {
/* 1835 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) {
/* 1841 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   protected int featureID = -1;
/*      */   protected Class<?> containerClass;
/*      */   protected Object defaultValue;
/*      */   protected EFactory defaultValueFactory;
/*      */   protected static final boolean CHANGEABLE_EDEFAULT = true; protected static final int CHANGEABLE_EFLAG = 1024; protected static final boolean VOLATILE_EDEFAULT = false; protected static final int VOLATILE_EFLAG = 2048; protected static final boolean TRANSIENT_EDEFAULT = false; protected static final int TRANSIENT_EFLAG = 4096; protected EClass eStaticClass() { return EcorePackage.Literals.ESTRUCTURAL_FEATURE; } protected EStructuralFeatureImpl() { this.defaultValue = null; this.defaultValueFactory = null; this.defaultValueLiteral = DEFAULT_VALUE_LITERAL_EDEFAULT; this.eFlags |= 0x400; } public Object getDefaultValue() { EClassifier eType = getEType(); String literal = getDefaultValueLiteral(); if (literal == null && eType != null) return isMany() ? null : eType.getDefaultValue();  if (eType instanceof EDataType) { EFactory factory = eType.getEPackage().getEFactoryInstance(); if (factory != this.defaultValueFactory) { EDataType eDataType = (EDataType)eType; if (eDataType.isSerializable()) try { this.defaultValue = factory.createFromString(eDataType, literal); } catch (Throwable e) { this.defaultValue = null; }   this.defaultValueFactory = factory; }  return this.defaultValue; }  return null; } public void setDefaultValue(Object newDefaultValue) { EClassifier eType = getEType(); if (eType instanceof EDataType) { EFactory factory = eType.getEPackage().getEFactoryInstance(); String literal = factory.convertToString((EDataType)eType, newDefaultValue); this.defaultValueFactory = null; setDefaultValueLiteralGen(literal); return; }  throw new IllegalStateException("Cannot serialize value to object without an EDataType eType"); } public void setDefaultValueLiteral(String newDefaultValueLiteral) { this.defaultValueFactory = null; setDefaultValueLiteralGen(newDefaultValueLiteral); } public void setDefaultValueLiteralGen(String newDefaultValueLiteral) { String oldDefaultValueLiteral = this.defaultValueLiteral; this.defaultValueLiteral = newDefaultValueLiteral; if (eNotificationRequired()) eNotify((Notification)new ENotificationImpl(this, 1, 13, oldDefaultValueLiteral, this.defaultValueLiteral));  } public boolean isUnsettable() { return ((this.eFlags & 0x2000) != 0); } public void setUnsettable(boolean newUnsettable) { boolean oldUnsettable = ((this.eFlags & 0x2000) != 0); if (newUnsettable) { this.eFlags |= 0x2000; } else { this.eFlags &= 0xFFFFDFFF; }  if (eNotificationRequired()) eNotify((Notification)new ENotificationImpl(this, 1, 15, oldUnsettable, newUnsettable));  } public boolean isDerived() { return ((this.eFlags & 0x4000) != 0); } public void setDerived(boolean newDerived) { boolean oldDerived = ((this.eFlags & 0x4000) != 0); if (newDerived) { this.eFlags |= 0x4000; } else { this.eFlags &= 0xFFFFBFFF; }  if (eNotificationRequired()) eNotify((Notification)new ENotificationImpl(this, 1, 16, oldDerived, newDerived));  } public EClass getEContainingClass() { if (eContainerFeatureID() != 17) return null;  return (EClass)eContainer(); } protected static final String DEFAULT_VALUE_LITERAL_EDEFAULT = null; protected String defaultValueLiteral; protected static final Object DEFAULT_VALUE_EDEFAULT = null; protected static final boolean UNSETTABLE_EDEFAULT = false; protected static final int UNSETTABLE_EFLAG = 8192; protected static final boolean DERIVED_EDEFAULT = false; protected static final int DERIVED_EFLAG = 16384; protected EStructuralFeature.Internal.SettingDelegate settingDelegate; protected EClassifier cachedEType; protected boolean cachedIsFeatureMap; protected FeatureMap.Entry.Internal prototypeFeatureMapEntry; protected BasicExtendedMetaData.EStructuralFeatureExtendedMetaData eStructuralFeatureExtendedMetaData; public boolean isTransient() { return ((this.eFlags & 0x1000) != 0); } public void setTransient(boolean newTransient) { boolean oldTransient = ((this.eFlags & 0x1000) != 0); if (newTransient) { this.eFlags |= 0x1000; } else { this.eFlags &= 0xFFFFEFFF; }  if (eNotificationRequired()) eNotify((Notification)new ENotificationImpl(this, 1, 12, oldTransient, newTransient));  } public boolean isVolatile() { return ((this.eFlags & 0x800) != 0); } public void setVolatile(boolean newVolatile) { boolean oldVolatile = ((this.eFlags & 0x800) != 0); if (newVolatile) { this.eFlags |= 0x800; } else { this.eFlags &= 0xFFFFF7FF; }  if (eNotificationRequired()) eNotify((Notification)new ENotificationImpl(this, 1, 11, oldVolatile, newVolatile));  } public boolean isChangeable() { return ((this.eFlags & 0x400) != 0); } public void setChangeable(boolean newChangeable) { boolean oldChangeable = ((this.eFlags & 0x400) != 0); if (newChangeable) { this.eFlags |= 0x400; } else { this.eFlags &= 0xFFFFFBFF; }  if (eNotificationRequired()) eNotify((Notification)new ENotificationImpl(this, 1, 10, oldChangeable, newChangeable));  } public String getDefaultValueLiteral() { return this.defaultValueLiteral; } public String toString() { if (eIsProxy()) return super.toString();  StringBuffer result = new StringBuffer(super.toString()); result.append(" (changeable: "); result.append(((this.eFlags & 0x400) != 0)); result.append(", volatile: "); result.append(((this.eFlags & 0x800) != 0)); result.append(", transient: "); result.append(((this.eFlags & 0x1000) != 0)); result.append(", defaultValueLiteral: "); result.append(this.defaultValueLiteral); result.append(", unsettable: "); result.append(((this.eFlags & 0x2000) != 0)); result.append(", derived: "); result.append(((this.eFlags & 0x4000) != 0)); result.append(')'); return result.toString(); } public int getFeatureID() { return this.featureID; } public void setFeatureID(int featureID) { this.featureID = featureID; } public Class<?> getContainerClass() { return this.containerClass; } public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) { switch (featureID) { case 0: return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);case 17: if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);  return eBasicSetContainer(otherEnd, 17, msgs); }  return eDynamicInverseAdd(otherEnd, featureID, msgs); } public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) { switch (featureID) { case 0: return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);case 9: return basicUnsetEGenericType(msgs);case 17: return eBasicSetContainer((InternalEObject)null, 17, msgs); }  return eDynamicInverseRemove(otherEnd, featureID, msgs); } public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) { switch (eContainerFeatureID()) { case 17: return eInternalContainer().eInverseRemove(this, 21, EClass.class, msgs); }  return eDynamicBasicRemoveFromContainer(msgs); } public Object eGet(int featureID, boolean resolve, boolean coreType) { switch (featureID) { case 0: return getEAnnotations();case 1: return getName();case 2: return Boolean.valueOf(isOrdered());case 3: return Boolean.valueOf(isUnique());case 4: return Integer.valueOf(getLowerBound());case 5: return Integer.valueOf(getUpperBound());case 6: return Boolean.valueOf(isMany());case 7: return Boolean.valueOf(isRequired());case 8: if (resolve) return getEType();  return basicGetEType();case 9: return getEGenericType();case 10: return Boolean.valueOf(isChangeable());case 11: return Boolean.valueOf(isVolatile());case 12: return Boolean.valueOf(isTransient());case 13: return getDefaultValueLiteral();case 14: return getDefaultValue();case 15: return Boolean.valueOf(isUnsettable());case 16: return Boolean.valueOf(isDerived());case 17: return getEContainingClass(); }  return eDynamicGet(featureID, resolve, coreType); } public void eSet(int featureID, Object newValue) { switch (featureID) { case 0: getEAnnotations().clear(); getEAnnotations().addAll((Collection)newValue); return;case 1: setName((String)newValue); return;case 2: setOrdered(((Boolean)newValue).booleanValue()); return;case 3: setUnique(((Boolean)newValue).booleanValue()); return;case 4: setLowerBound(((Integer)newValue).intValue()); return;case 5: setUpperBound(((Integer)newValue).intValue()); return;case 8: setEType((EClassifier)newValue); return;case 9: setEGenericType((EGenericType)newValue); return;case 10: setChangeable(((Boolean)newValue).booleanValue()); return;case 11: setVolatile(((Boolean)newValue).booleanValue()); return;case 12: setTransient(((Boolean)newValue).booleanValue()); return;case 13: setDefaultValueLiteral((String)newValue); return;case 15: setUnsettable(((Boolean)newValue).booleanValue()); return;case 16: setDerived(((Boolean)newValue).booleanValue()); return; }  eDynamicSet(featureID, newValue); } public void eUnset(int featureID) { switch (featureID) { case 0: getEAnnotations().clear(); return;case 1: setName(NAME_EDEFAULT); return;case 2: setOrdered(true); return;case 3: setUnique(true); return;case 4: setLowerBound(0); return;case 5: setUpperBound(1); return;case 8: unsetEType(); return;case 9: unsetEGenericType(); return;case 10: setChangeable(true); return;case 11: setVolatile(false); return;case 12: setTransient(false); return;case 13: setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT); return;case 15: setUnsettable(false); return;case 16: setDerived(false); return; }  eDynamicUnset(featureID); } public boolean eIsSet(int featureID) { switch (featureID) { case 0: return (this.eAnnotations != null && !this.eAnnotations.isEmpty());case 1: return (NAME_EDEFAULT == null) ? ((this.name != null)) : (!NAME_EDEFAULT.equals(this.name));case 2: return !((this.eFlags & 0x100) != 0);case 3: return !((this.eFlags & 0x200) != 0);case 4: return (this.lowerBound != 0);case 5: return (this.upperBound != 1);case 6: return isMany();case 7: return isRequired();case 8: return isSetEType();case 9: return isSetEGenericType();case 10: return !((this.eFlags & 0x400) != 0);case 11: return ((this.eFlags & 0x800) != 0);case 12: return ((this.eFlags & 0x1000) != 0);case 13: return (DEFAULT_VALUE_LITERAL_EDEFAULT == null) ? ((this.defaultValueLiteral != null)) : (!DEFAULT_VALUE_LITERAL_EDEFAULT.equals(this.defaultValueLiteral));case 14: return (DEFAULT_VALUE_EDEFAULT == null) ? ((getDefaultValue() != null)) : (!DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue()));case 15: return ((this.eFlags & 0x2000) != 0);case 16: return ((this.eFlags & 0x4000) != 0);case 17: return (getEContainingClass() != null); }  return eDynamicIsSet(featureID); } public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException { switch (operationID) { case 0: return getEAnnotation((String)arguments.get(0));case 1: return Integer.valueOf(getFeatureID());case 2: return getContainerClass(); }  return eDynamicInvoke(operationID, arguments); } public void setContainerClass(Class<?> containerClass) { this.containerClass = containerClass; } public boolean isResolveProxies() { return false; } public boolean isContainer() { return false; } public boolean isContainment() { return false; } public EReference getEOpposite() { return null; } public boolean isID() { return false; } public EStructuralFeature.Internal.SettingDelegate getSettingDelegate() { if (this.settingDelegate == null) { EClass eClass = getEContainingClass(); eClass.getFeatureCount(); EReference eOpposite = getEOpposite(); if (eOpposite != null) eOpposite.getEContainingClass().getFeatureCount();  EClassifier eType = getEType(); Class<?> dataClass = EcoreUtil.wrapperClassFor(eType.getInstanceClass()); Object defaultValue = getDefaultValue(); Object intrinsicDefaultValue = eType.getDefaultValue(); EStructuralFeature.Internal.SettingDelegate.Factory settingDelegateFactory; if ((settingDelegateFactory = EcoreUtil.getSettingDelegateFactory(this)) != null) { this.settingDelegate = settingDelegateFactory.createSettingDelegate(this); } else { EAttribute eAttribute; EStructuralFeature eStructuralFeature; if (isDerived() && (((eAttribute = ExtendedMetaData.INSTANCE.getMixedFeature(eClass)) != null && eAttribute != this) || (eStructuralFeature = ExtendedMetaData.INSTANCE.getGroup(this)) != null)) { this.settingDelegate = new InternalSettingDelegateFeatureMapDelegator(this, eStructuralFeature); } else if (isMany()) { if (isContainment()) { if (eOpposite == null) { if (isUnsettable()) { if (dataClass == null) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(42, this); } else { this.settingDelegate = new InternalSettingDelegateMany(0, this); }  } else if (dataClass == Map.Entry.class) { this.settingDelegate = new InternalSettingDelegateMany(50, BasicEMap.Entry.class, this); } else if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(43, dataClass, this); } else { this.settingDelegate = new InternalSettingDelegateMany(1, dataClass, this); }  } else if (dataClass == null) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(44, this); } else { this.settingDelegate = new InternalSettingDelegateMany(2, this); }  } else if (dataClass == Map.Entry.class) { this.settingDelegate = new InternalSettingDelegateMany(41, BasicEMap.Entry.class, this); } else if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(45, dataClass, this); } else { this.settingDelegate = new InternalSettingDelegateMany(3, dataClass, this); }  } else if (isUnsettable()) { if (dataClass == null) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(46, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(4, this, eOpposite); }  } else if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(47, dataClass, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(5, dataClass, this, eOpposite); }  } else if (dataClass == null) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(48, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(6, this, eOpposite); }  } else if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateMany(49, dataClass, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(7, dataClass, this, eOpposite); }  } else if (eType instanceof EDataType) { if (dataClass == FeatureMap.Entry.class) { this.settingDelegate = createFeatureMapSettingDelegate(); } else if (isUnique()) { if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(8, this); } else { this.settingDelegate = new InternalSettingDelegateMany(9, dataClass, this); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(10, this); } else { this.settingDelegate = new InternalSettingDelegateMany(11, dataClass, this); }  } else if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(12, this); } else { this.settingDelegate = new InternalSettingDelegateMany(13, dataClass, this); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(14, this); } else { this.settingDelegate = new InternalSettingDelegateMany(15, dataClass, this); }  } else if (eOpposite == null) { if (isResolveProxies()) { if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(16, this); } else { this.settingDelegate = new InternalSettingDelegateMany(17, dataClass, this); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(18, this); } else { this.settingDelegate = new InternalSettingDelegateMany(19, dataClass, this); }  } else if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(20, this); } else { this.settingDelegate = new InternalSettingDelegateMany(21, dataClass, this); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(22, this); } else { this.settingDelegate = new InternalSettingDelegateMany(23, dataClass, this); }  } else if (eOpposite.isMany()) { if (isResolveProxies()) { if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(24, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(25, dataClass, this, eOpposite); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(26, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(27, dataClass, this, eOpposite); }  } else if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(28, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(29, dataClass, this, eOpposite); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(30, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(31, dataClass, this, eOpposite); }  } else if (isResolveProxies()) { if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(32, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(33, dataClass, this, eOpposite); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(34, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(35, dataClass, this, eOpposite); }  } else if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(36, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(37, dataClass, this, eOpposite); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateMany(38, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateMany(39, dataClass, this, eOpposite); }  } else if (isContainer()) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateSingleContainerResolving((EClass)eType, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateSingleContainer((EClass)eType, this, eOpposite); }  } else if (eType instanceof EDataType) { if (dataClass == FeatureMap.Entry.class) { this.settingDelegate = createFeatureMapSettingDelegate(); } else if (isUnsettable()) { if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateSingleDataUnsettableDynamic((EDataType)eType, defaultValue, intrinsicDefaultValue, this); } else { this.settingDelegate = new InternalSettingDelegateSingleDataUnsettableStatic(dataClass, defaultValue, intrinsicDefaultValue, this); }  } else if (dataClass == null) { this.settingDelegate = new InternalSettingDelegateSingleDataDynamic((EDataType)eType, defaultValue, intrinsicDefaultValue, this); } else { this.settingDelegate = new InternalSettingDelegateSingleDataStatic(dataClass, defaultValue, intrinsicDefaultValue, this); }  } else if (isContainment()) { if (eOpposite == null) { if (isUnsettable()) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentUnsettableResolving((EClass)eType, this); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentUnsettable((EClass)eType, this); }  } else if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentResolving((EClass)eType, this); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainment((EClass)eType, this); }  } else if (isUnsettable()) { if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettableResolving((EClass)eType, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettable((EClass)eType, this, eOpposite); }  } else if (isResolveProxies()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentWithInverseResolving((EClass)eType, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectContainmentWithInverse((EClass)eType, this, eOpposite); }  } else if (isResolveProxies()) { if (eOpposite == null) { if (isUnsettable()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectResolvingUnsettable((EClass)eType, this); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectResolving((EClass)eType, this); }  } else if (isUnsettable()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectResolvingWithInverseUnsettable((EClass)eType, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectResolvingWithInverse((EClass)eType, this, eOpposite); }  } else if (eOpposite == null) { if (isUnsettable()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectUnsettable((EClass)eType, this); } else { this.settingDelegate = new InternalSettingDelegateSingleEObject((EClass)eType, this); }  } else if (isUnsettable()) { this.settingDelegate = new InternalSettingDelegateSingleEObjectWithInverseUnsettable((EClass)eType, this, eOpposite); } else { this.settingDelegate = new InternalSettingDelegateSingleEObjectWithInverse((EClass)eType, this, eOpposite); }  }  }  return this.settingDelegate; } protected EStructuralFeature.Internal.SettingDelegate createFeatureMapSettingDelegate() { return new InternalSettingDelegateMany(40, this); } public void setSettingDelegate(EStructuralFeature.Internal.SettingDelegate settingDelegate) { this.settingDelegate = settingDelegate; } public static class InternalSettingDelegateFeatureMapDelegator implements EStructuralFeature.Internal.SettingDelegate {
/*      */     protected EStructuralFeature feature; protected EStructuralFeature featureMapFeature; public InternalSettingDelegateFeatureMapDelegator(EStructuralFeature feature, EStructuralFeature featureMapFeature) { this.feature = feature; this.featureMapFeature = featureMapFeature; } protected EStructuralFeature.Setting createDynamicSetting(InternalEObject owner) { return ((FeatureMap.Internal)owner.eGet(this.featureMapFeature)).setting(this.feature); } public EStructuralFeature.Setting dynamicSetting(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) { return createDynamicSetting(owner); } public Object dynamicGet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, boolean resolve, boolean coreType) { FeatureMap.Internal featureMap = (FeatureMap.Internal)owner.eGet(this.featureMapFeature); return featureMap.setting(this.feature).get(resolve); } public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, Object newValue) { FeatureMap.Internal featureMap = (FeatureMap.Internal)owner.eGet(this.featureMapFeature); featureMap.setting(this.feature).set(newValue); } public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) { FeatureMap.Internal featureMap = (FeatureMap.Internal)owner.eGet(this.featureMapFeature); featureMap.setting(this.feature).unset(); } public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) { FeatureMap.Internal featureMap = (FeatureMap.Internal)owner.eGet(this.featureMapFeature); return featureMap.setting(this.feature).isSet(); } public NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) { FeatureMap.Internal featureMap = (FeatureMap.Internal)owner.eGet(this.featureMapFeature); return featureMap.basicAdd(this.feature, otherEnd, notifications); } public NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) { FeatureMap.Internal featureMap = (FeatureMap.Internal)owner.eGet(this.featureMapFeature); return featureMap.basicRemove(this.feature, otherEnd, notifications); }
/*      */   } public static class InternalSettingDelegateMany implements EStructuralFeature.Internal.SettingDelegate {
/*      */     public static final int CONTAINMENT_UNSETTABLE_DYNAMIC = 0; public static final int CONTAINMENT_UNSETTABLE = 1; public static final int CONTAINMENT_DYNAMIC = 2; public static final int CONTAINMENT = 3; public static final int CONTAINMENT_INVERSE_UNSETTABLE_DYNAMIC = 4; public static final int CONTAINMENT_INVERSE_UNSETTABLE = 5; public static final int CONTAINMENT_INVERSE_DYNAMIC = 6; public static final int CONTAINMENT_INVERSE = 7; public static final int DATA_UNIQUE_UNSETTABLE_DYNAMIC = 8; public static final int DATA_UNIQUE_UNSETTABLE = 9; public static final int DATA_UNIQUE_DYNAMIC = 10; public static final int DATA_UNIQUE = 11; public static final int DATA_UNSETTABLE_DYNAMIC = 12; public static final int DATA_UNSETTABLE = 13; public static final int DATA_DYNAMIC = 14; public static final int DATA = 15; public static final int EOBJECT_RESOLVE_UNSETTABLE_DYNAMIC = 16; public static final int EOBJECT_RESOLVE_UNSETTABLE = 17; public static final int EOBJECT_RESOLVE_DYNAMIC = 18; public static final int EOBJECT_RESOLVE = 19; public static final int EOBJECT_UNSETTABLE_DYNAMIC = 20; public static final int EOBJECT_UNSETTABLE = 21; public static final int EOBJECT_DYNAMIC = 22; public static final int EOBJECT = 23; public static final int MANY_INVERSE_RESOLVE_UNSETTABLE_DYNAMIC = 24; public static final int MANY_INVERSE_RESOLVE_UNSETTABLE = 25; public static final int MANY_INVERSE_RESOLVE_DYNAMIC = 26; public static final int MANY_INVERSE_RESOLVE = 27; public static final int MANY_INVERSE_UNSETTABLE_DYNAMIC = 28; public static final int MANY_INVERSE_UNSETTABLE = 29; public static final int MANY_INVERSE_DYNAMIC = 30; public static final int MANY_INVERSE = 31; public static final int INVERSE_RESOLVE_UNSETTABLE_DYNAMIC = 32; public static final int INVERSE_RESOLVE_UNSETTABLE = 33; public static final int INVERSE_RESOLVE_DYNAMIC = 34; public static final int INVERSE_RESOLVE = 35; public static final int INVERSE_UNSETTABLE_DYNAMIC = 36; public static final int INVERSE_UNSETTABLE = 37; public static final int INVERSE_DYNAMIC = 38; public static final int INVERSE = 39; public static final int FEATURE_MAP = 40; public static final int EMAP = 41; public static final int CONTAINMENT_UNSETTABLE_DYNAMIC_RESOLVE = 42; public static final int CONTAINMENT_UNSETTABLE_RESOLVE = 43; public static final int CONTAINMENT_DYNAMIC_RESOLVE = 44; public static final int CONTAINMENT_RESOLVE = 45; public static final int CONTAINMENT_INVERSE_UNSETTABLE_DYNAMIC_RESOLVE = 46; public static final int CONTAINMENT_INVERSE_UNSETTABLE_RESOLVE = 47; public static final int CONTAINMENT_INVERSE_DYNAMIC_RESOLVE = 48; public static final int CONTAINMENT_INVERSE_RESOLVE = 49; public static final int EMAP_UNSETTABLE = 50; protected int style; protected int dynamicKind; protected Class<?> dataClass; protected EStructuralFeature feature; protected EReference inverseFeature; public InternalSettingDelegateMany(int style, Class<?> dataClass, EStructuralFeature feature) { this.style = style; this.dataClass = dataClass; this.feature = feature; } public InternalSettingDelegateMany(int style, EStructuralFeature feature) { this.style = style; this.dataClass = Object.class; this.dynamicKind = EcoreEList.Generic.kind(feature); this.feature = feature; } public InternalSettingDelegateMany(int style, Class<?> dataClass, EStructuralFeature feature, EReference inverseFeature) { this.style = style; this.dataClass = dataClass; this.feature = feature; this.inverseFeature = inverseFeature; } public InternalSettingDelegateMany(int style, EStructuralFeature feature, EReference inverseFeature) { this.style = style; this.dataClass = Object.class; this.dynamicKind = EcoreEList.Generic.kind(feature); this.feature = feature; this.inverseFeature = inverseFeature; } protected EStructuralFeature.Setting createDynamicSetting(InternalEObject owner) { switch (this.style) { case 0: case 2: case 4: case 6: case 8: case 10: case 12: case 14: case 16: case 18: case 20: case 22: case 24: case 26: case 28: case 30: case 32: case 34: case 36: case 38: case 42: case 44: case 46: case 48: return (EStructuralFeature.Setting)new EcoreEList.Dynamic(this.dynamicKind, this.dataClass, owner, this.feature);case 1: return (EStructuralFeature.Setting)new EObjectContainmentEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 43: return (EStructuralFeature.Setting)new EObjectContainmentEList.Unsettable.Resolving(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 3: return (EStructuralFeature.Setting)new EObjectContainmentEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 45: return (EStructuralFeature.Setting)new EObjectContainmentEList.Resolving(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 41: return (EStructuralFeature.Setting)new EcoreEMap((EClass)this.feature.getEType(), this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 50: return (EStructuralFeature.Setting)new EcoreEMap.Unsettable((EClass)this.feature.getEType(), this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 5: return (EStructuralFeature.Setting)new EObjectContainmentWithInverseEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 47: return (EStructuralFeature.Setting)new EObjectContainmentWithInverseEList.Unsettable.Resolving(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 7: return (EStructuralFeature.Setting)new EObjectContainmentWithInverseEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 49: return (EStructuralFeature.Setting)new EObjectContainmentWithInverseEList.Resolving(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 9: return (EStructuralFeature.Setting)new EDataTypeUniqueEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 11: return (EStructuralFeature.Setting)new EDataTypeUniqueEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 13: return (EStructuralFeature.Setting)new EDataTypeEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 15: return (EStructuralFeature.Setting)new EDataTypeEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 17: return (EStructuralFeature.Setting)new EObjectResolvingEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 19: return (EStructuralFeature.Setting)new EObjectResolvingEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 21: return (EStructuralFeature.Setting)new EObjectEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 23: return (EStructuralFeature.Setting)new EObjectEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature));case 25: return (EStructuralFeature.Setting)new EObjectWithInverseResolvingEList.Unsettable.ManyInverse(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 27: return (EStructuralFeature.Setting)new EObjectWithInverseResolvingEList.ManyInverse(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 29: return (EStructuralFeature.Setting)new EObjectWithInverseEList.Unsettable.ManyInverse(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 31: return (EStructuralFeature.Setting)new EObjectWithInverseEList.ManyInverse(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 33: return (EStructuralFeature.Setting)new EObjectWithInverseResolvingEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 35: return (EStructuralFeature.Setting)new EObjectWithInverseResolvingEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 37: return (EStructuralFeature.Setting)new EObjectWithInverseEList.Unsettable(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 39: return (EStructuralFeature.Setting)new EObjectWithInverseEList(this.dataClass, owner, owner.eClass().getFeatureID(this.feature), this.inverseFeature.getFeatureID());case 40: return (EStructuralFeature.Setting)new BasicFeatureMap(owner, owner.eClass().getFeatureID(this.feature)); }  throw new RuntimeException("Unknown feature style: " + this.style); } public EStructuralFeature.Setting dynamicSetting(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) { Object setting = settings.dynamicGet(index); if (setting == null) settings.dynamicSet(index, setting = createDynamicSetting(owner));  if (setting instanceof EStructuralFeature.Setting) return (EStructuralFeature.Setting)setting;  List<Object> result = (List<Object>)settings.dynamicGet(index); return new EStructuralFeatureImpl.SettingMany((EObject)owner, this.feature, result); } public Object dynamicGet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, boolean resolve, boolean coreType) { Object result = settings.dynamicGet(index); if (result == null) settings.dynamicSet(index, result = createDynamicSetting(owner));  if (!coreType) switch (this.style) { case 41: case 50: return ((EMap)result).map();case 40: return ((FeatureMap.Internal)result).getWrapper(); }   return result; } public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, Object newValue) { EStructuralFeature.Setting setting = (EStructuralFeature.Setting)settings.dynamicGet(index); if (setting == null) settings.dynamicSet(index, setting = createDynamicSetting(owner));  setting.set(newValue); } public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) { EStructuralFeature.Setting setting = (EStructuralFeature.Setting)settings.dynamicGet(index); if (setting == null) settings.dynamicSet(index, setting = createDynamicSetting(owner));  setting.unset(); } public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) { Object setting = settings.dynamicGet(index); if (setting == null) return false;  return ((EStructuralFeature.Setting)setting).isSet(); } public NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) { Object setting = settings.dynamicGet(index); if (setting == null) settings.dynamicSet(index, setting = createDynamicSetting(owner));  NotificationChain result = ((InternalEList)setting).basicAdd(otherEnd, notifications); return result; } public NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) { Object setting = settings.dynamicGet(index); if (setting != null) notifications = ((InternalEList)setting).basicRemove(otherEnd, notifications);  return notifications; }
/* 1852 */   } public static class InternalSettingDelegateSingleContainer extends InternalSettingDelegateSingle { protected EClass eClass; public InternalSettingDelegateSingleContainer(EClass eClass, EStructuralFeature feature, EReference inverseFeature) { super(feature);
/* 1853 */       this.eClass = eClass;
/* 1854 */       this.inverseFeature = inverseFeature; }
/*      */     
/*      */     protected EReference inverseFeature;
/*      */     
/*      */     public Object dynamicGet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, boolean resolve, boolean coreType) {
/* 1859 */       return (owner.eContainmentFeature() == this.inverseFeature) ? ((isResolveProxies() && resolve) ? owner.eContainer() : owner.eInternalContainer()) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 1864 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, Object newValue) {
/* 1869 */       if (newValue != null && !this.eClass.isInstance(newValue))
/*      */       {
/*      */         
/* 1872 */         throw new ClassCastException(
/* 1873 */             "The value of type '" + (
/* 1874 */             (newValue instanceof EObject) ? ((EObject)newValue).eClass().toString() : newValue.getClass().toString()) + 
/* 1875 */             "' must be of type '" + this.eClass + "'");
/*      */       }
/*      */       
/* 1878 */       InternalEObject internalEObject = owner.eInternalContainer();
/* 1879 */       int featureID = owner.eClass().getFeatureID(this.feature);
/* 1880 */       if (newValue != internalEObject || (owner.eContainerFeatureID() != featureID && newValue != null)) {
/*      */         
/* 1882 */         if (EcoreUtil.isAncestor((EObject)owner, (EObject)newValue)) {
/* 1883 */           throw new IllegalArgumentException("Recursive containment not allowed for " + owner.toString());
/*      */         }
/* 1885 */         NotificationChain notifications = null;
/* 1886 */         if (internalEObject != null)
/*      */         {
/* 1888 */           notifications = owner.eBasicRemoveFromContainer(notifications);
/*      */         }
/*      */         
/* 1891 */         InternalEObject internalEObject1 = (InternalEObject)newValue;
/* 1892 */         if (newValue != null)
/*      */         {
/* 1894 */           notifications = 
/* 1895 */             internalEObject1.eInverseAdd(
/* 1896 */               owner, internalEObject1.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), null, notifications);
/*      */         }
/*      */         
/* 1899 */         notifications = owner.eBasicSetContainer(internalEObject1, featureID, notifications);
/* 1900 */         if (notifications != null) notifications.dispatch();
/*      */         
/*      */       
/*      */       }
/* 1904 */       else if (owner.eNotificationRequired()) {
/* 1905 */         owner.eNotify((Notification)new ENotificationImpl(owner, 1, featureID, newValue, newValue));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 1911 */       InternalEObject internalEObject = owner.eInternalContainer();
/* 1912 */       if (internalEObject != null) {
/*      */         
/* 1914 */         NotificationChain notifications = owner.eBasicRemoveFromContainer(null);
/* 1915 */         int featureID = owner.eClass().getFeatureID(this.feature);
/* 1916 */         notifications = owner.eBasicSetContainer(null, featureID, notifications);
/* 1917 */         if (notifications != null) notifications.dispatch();
/*      */         
/*      */       
/*      */       }
/* 1921 */       else if (owner.eNotificationRequired()) {
/* 1922 */         owner.eNotify((Notification)new ENotificationImpl(owner, 1, this.feature, null, null));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 1928 */       int featureID = owner.eClass().getFeatureID(this.feature);
/* 1929 */       return (owner.eInternalContainer() != null && owner.eContainerFeatureID() == featureID);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) {
/* 1936 */       if (owner.eInternalContainer() != null)
/*      */       {
/* 1938 */         notifications = owner.eBasicRemoveFromContainer(notifications);
/*      */       }
/* 1940 */       int featureID = owner.eClass().getFeatureID(this.feature);
/* 1941 */       return owner.eBasicSetContainer(otherEnd, featureID, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) {
/* 1948 */       int featureID = owner.eClass().getFeatureID(this.feature);
/* 1949 */       return owner.eBasicSetContainer(null, featureID, notifications);
/*      */     } }
/*      */ 
/*      */   
/*      */   public static class InternalSettingDelegateSingleContainerResolving
/*      */     extends InternalSettingDelegateSingleContainer
/*      */   {
/*      */     public InternalSettingDelegateSingleContainerResolving(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 1957 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 1963 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleData
/*      */     extends InternalSettingDelegateSingle
/*      */   {
/*      */     protected Object defaultValue;
/*      */     protected Object intrinsicDefaultValue;
/*      */     
/*      */     public InternalSettingDelegateSingleData(Object defaultValue, Object intrinsicDefaultValue, EStructuralFeature feature) {
/* 1974 */       super(feature);
/* 1975 */       this.defaultValue = defaultValue;
/* 1976 */       this.intrinsicDefaultValue = intrinsicDefaultValue;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void validate(Object object) {
/* 1981 */       throw new ClassCastException();
/*      */     }
/*      */ 
/*      */     
/*      */     public Object dynamicGet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, boolean resolve, boolean coreType) {
/* 1986 */       Object result = settings.dynamicGet(index);
/* 1987 */       if (result == null)
/*      */       {
/* 1989 */         return this.defaultValue;
/*      */       }
/* 1991 */       if (result == NIL)
/*      */       {
/* 1993 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1997 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, Object newValue) {
/* 2003 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2005 */         Object oldValue = dynamicGet(owner, settings, index, false, true);
/*      */         
/* 2007 */         if (newValue == null) {
/*      */           
/* 2009 */           if (this.intrinsicDefaultValue != null)
/*      */           {
/* 2011 */             settings.dynamicSet(index, null);
/* 2012 */             newValue = this.defaultValue;
/*      */           }
/* 2014 */           else if (this.defaultValue != null)
/*      */           {
/* 2016 */             settings.dynamicSet(index, NIL);
/*      */           }
/*      */           else
/*      */           {
/* 2020 */             settings.dynamicSet(index, null);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2025 */           validate(newValue);
/* 2026 */           settings.dynamicSet(index, newValue);
/*      */         } 
/*      */         
/* 2029 */         owner.eNotify(
/* 2030 */             (Notification)new ENotificationImpl(
/* 2031 */               owner, 
/* 2032 */               1, 
/* 2033 */               this.feature, 
/* 2034 */               oldValue, 
/* 2035 */               newValue));
/*      */ 
/*      */       
/*      */       }
/* 2039 */       else if (newValue == null) {
/*      */         
/* 2041 */         if (this.intrinsicDefaultValue != null)
/*      */         {
/* 2043 */           settings.dynamicSet(index, null);
/*      */         }
/* 2045 */         else if (this.defaultValue != null)
/*      */         {
/* 2047 */           settings.dynamicSet(index, NIL);
/*      */         }
/*      */         else
/*      */         {
/* 2051 */           settings.dynamicSet(index, null);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2056 */         validate(newValue);
/* 2057 */         settings.dynamicSet(index, newValue);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 2064 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2066 */         Object oldValue = dynamicGet(owner, settings, index, false, true);
/* 2067 */         settings.dynamicUnset(index);
/* 2068 */         owner.eNotify(
/* 2069 */             (Notification)new ENotificationImpl(
/* 2070 */               owner, 1, this.feature, oldValue, this.defaultValue));
/*      */       }
/*      */       else {
/*      */         
/* 2074 */         settings.dynamicUnset(index);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 2080 */       Object setting = settings.dynamicGet(index);
/* 2081 */       if (setting == null)
/*      */       {
/* 2083 */         return false;
/*      */       }
/* 2085 */       if (setting == NIL)
/*      */       {
/* 2087 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 2091 */       return !setting.equals(this.defaultValue);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class InternalSettingDelegateSingleDataDynamic
/*      */     extends InternalSettingDelegateSingleData
/*      */   {
/*      */     protected EDataType eDataType;
/*      */     
/*      */     public InternalSettingDelegateSingleDataDynamic(EDataType eDataType, Object defaultValue, Object intrinsicDefaultValue, EStructuralFeature feature) {
/* 2102 */       super(defaultValue, intrinsicDefaultValue, feature);
/* 2103 */       this.eDataType = eDataType;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void validate(Object object) {
/* 2109 */       if (!this.eDataType.isInstance(object))
/*      */       {
/* 2111 */         throw new ClassCastException("The value of type '" + object.getClass() + "' must be of type '" + this.eDataType + "'");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleDataStatic
/*      */     extends InternalSettingDelegateSingleData
/*      */   {
/*      */     protected Class<?> dataClass;
/*      */     
/*      */     public InternalSettingDelegateSingleDataStatic(Class<?> dataClass, Object defaultValue, Object intrinsicDefaultValue, EStructuralFeature feature) {
/* 2122 */       super(defaultValue, intrinsicDefaultValue, feature);
/* 2123 */       this.dataClass = dataClass;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void validate(Object object) {
/* 2129 */       if (!this.dataClass.isInstance(object))
/*      */       {
/* 2131 */         throw new ClassCastException("The value of type '" + object.getClass() + "' must be of type '" + this.dataClass + "'");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleDataUnsettable
/*      */     extends InternalSettingDelegateSingleData
/*      */   {
/*      */     public InternalSettingDelegateSingleDataUnsettable(Object defaultValue, Object intrinsicDefaultValue, EStructuralFeature feature) {
/* 2140 */       super(defaultValue, intrinsicDefaultValue, feature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, Object newValue) {
/* 2146 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2148 */         boolean oldIsSet = true;
/* 2149 */         Object oldValue = settings.dynamicGet(index);
/* 2150 */         if (oldValue == null) {
/*      */           
/* 2152 */           oldIsSet = false;
/* 2153 */           oldValue = this.defaultValue;
/*      */         }
/* 2155 */         else if (oldValue == NIL) {
/*      */           
/* 2157 */           oldValue = null;
/*      */         } 
/*      */         
/* 2160 */         if (newValue == null) {
/*      */           
/* 2162 */           if (this.intrinsicDefaultValue != null)
/*      */           {
/* 2164 */             settings.dynamicSet(index, null);
/* 2165 */             newValue = this.defaultValue;
/*      */           }
/*      */           else
/*      */           {
/* 2169 */             settings.dynamicSet(index, NIL);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2174 */           validate(newValue);
/* 2175 */           settings.dynamicSet(index, newValue);
/*      */         } 
/*      */         
/* 2178 */         owner.eNotify(
/* 2179 */             (Notification)new ENotificationImpl(
/* 2180 */               owner, 
/* 2181 */               1, 
/* 2182 */               this.feature, 
/* 2183 */               oldValue, 
/* 2184 */               newValue, 
/* 2185 */               !oldIsSet));
/*      */ 
/*      */       
/*      */       }
/* 2189 */       else if (newValue == null) {
/*      */         
/* 2191 */         if (this.intrinsicDefaultValue != null)
/*      */         {
/* 2193 */           settings.dynamicSet(index, null);
/*      */         }
/*      */         else
/*      */         {
/* 2197 */           settings.dynamicSet(index, NIL);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2202 */         validate(newValue);
/* 2203 */         settings.dynamicSet(index, newValue);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 2211 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2213 */         boolean oldIsSet = true;
/* 2214 */         Object oldValue = settings.dynamicGet(index);
/* 2215 */         if (oldValue == null) {
/*      */           
/* 2217 */           oldIsSet = false;
/* 2218 */           oldValue = this.defaultValue;
/*      */         }
/* 2220 */         else if (oldValue == NIL) {
/*      */           
/* 2222 */           oldValue = null;
/*      */         } 
/* 2224 */         settings.dynamicUnset(index);
/* 2225 */         owner.eNotify(
/* 2226 */             (Notification)new ENotificationImpl(
/* 2227 */               owner, 2, this.feature, oldValue, this.defaultValue, oldIsSet));
/*      */       }
/*      */       else {
/*      */         
/* 2231 */         settings.dynamicUnset(index);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 2238 */       Object setting = settings.dynamicGet(index);
/* 2239 */       if (setting == null)
/*      */       {
/* 2241 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2245 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class InternalSettingDelegateSingleDataUnsettableDynamic
/*      */     extends InternalSettingDelegateSingleDataUnsettable
/*      */   {
/*      */     protected EDataType eDataType;
/*      */     
/*      */     public InternalSettingDelegateSingleDataUnsettableDynamic(EDataType eDataType, Object defaultValue, Object intrinsicDefaultValue, EStructuralFeature feature) {
/* 2256 */       super(defaultValue, intrinsicDefaultValue, feature);
/* 2257 */       this.eDataType = eDataType;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void validate(Object object) {
/* 2263 */       if (!this.eDataType.isInstance(object))
/*      */       {
/* 2265 */         throw new ClassCastException("The value of type '" + object.getClass() + "' must be of type '" + this.eDataType + "'");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleDataUnsettableStatic
/*      */     extends InternalSettingDelegateSingleDataUnsettable
/*      */   {
/*      */     protected Class<?> dataClass;
/*      */     
/*      */     public InternalSettingDelegateSingleDataUnsettableStatic(Class<?> dataClass, Object defaultValue, Object intrinsicDefaultValue, EStructuralFeature feature) {
/* 2276 */       super(defaultValue, intrinsicDefaultValue, feature);
/* 2277 */       this.dataClass = dataClass;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void validate(Object object) {
/* 2283 */       if (!this.dataClass.isInstance(object))
/*      */       {
/* 2285 */         throw new ClassCastException("The value of type '" + object.getClass() + "' must be of type '" + this.dataClass + "'");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObject
/*      */     extends InternalSettingDelegateSingle
/*      */   {
/*      */     protected EClass eClass;
/*      */     protected EReference inverseFeature;
/*      */     
/*      */     public InternalSettingDelegateSingleEObject(EClass eClass, EStructuralFeature feature) {
/* 2297 */       super(feature);
/* 2298 */       this.eClass = eClass;
/*      */     }
/*      */ 
/*      */     
/*      */     public InternalSettingDelegateSingleEObject(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2303 */       super(feature);
/* 2304 */       this.eClass = eClass;
/* 2305 */       this.inverseFeature = inverseFeature;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2310 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean hasInverse() {
/* 2315 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isContainment() {
/* 2320 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 2325 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object dynamicGet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, boolean resolve, boolean coreType) {
/* 2330 */       Object result = settings.dynamicGet(index);
/* 2331 */       if (isUnsettable() && result == NIL)
/*      */       {
/* 2333 */         return null;
/*      */       }
/* 2335 */       if (isResolveProxies() && resolve && result != null) {
/*      */         
/* 2337 */         InternalEObject oldEObject = (InternalEObject)result;
/* 2338 */         if (oldEObject.eIsProxy()) {
/*      */           
/* 2340 */           EObject resolvedEObject = owner.eResolveProxy(oldEObject);
/* 2341 */           if (oldEObject != resolvedEObject) {
/*      */             
/* 2343 */             if (!this.eClass.isInstance(resolvedEObject))
/*      */             {
/* 2345 */               throw new ClassCastException("The value of type '" + resolvedEObject.getClass() + "' must be of type '" + this.eClass + "'");
/*      */             }
/*      */             
/* 2348 */             settings.dynamicSet(index, result = resolvedEObject);
/*      */             
/* 2350 */             if (isContainment()) {
/*      */               
/* 2352 */               InternalEObject newEObject = (InternalEObject)resolvedEObject;
/* 2353 */               NotificationChain notificationChain = 
/* 2354 */                 oldEObject.eInverseRemove(
/* 2355 */                   owner, 
/* 2356 */                   (this.inverseFeature == null) ? (
/* 2357 */                   -1 - owner.eClass().getFeatureID(this.feature)) : 
/* 2358 */                   oldEObject.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), 
/* 2359 */                   null, 
/* 2360 */                   null);
/* 2361 */               if (newEObject.eInternalContainer() == null)
/*      */               {
/* 2363 */                 notificationChain = 
/* 2364 */                   newEObject.eInverseAdd(
/* 2365 */                     owner, 
/* 2366 */                     (this.inverseFeature == null) ? (
/* 2367 */                     -1 - owner.eClass().getFeatureID(this.feature)) : 
/* 2368 */                     newEObject.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), 
/* 2369 */                     null, 
/* 2370 */                     notificationChain);
/*      */               }
/* 2372 */               if (notificationChain != null)
/*      */               {
/* 2374 */                 notificationChain.dispatch();
/*      */               }
/*      */             } 
/* 2377 */             if (owner.eNotificationRequired())
/*      */             {
/* 2379 */               owner.eNotify(
/* 2380 */                   (Notification)new ENotificationImpl(owner, 9, this.feature, oldEObject, resolvedEObject));
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 2385 */         return result;
/*      */       } 
/*      */ 
/*      */       
/* 2389 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dynamicSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, Object newValue) {
/* 2396 */       Object oldValue = settings.dynamicGet(index);
/* 2397 */       boolean oldIsSet = (oldValue != null);
/* 2398 */       if (isUnsettable() && oldValue == NIL)
/*      */       {
/* 2400 */         oldValue = null;
/*      */       }
/*      */       
/* 2403 */       NotificationChain notifications = null;
/* 2404 */       if (hasInverse()) {
/*      */         
/* 2406 */         if (oldValue != newValue) {
/*      */           
/* 2408 */           if (oldValue != null) {
/*      */             
/* 2410 */             InternalEObject internalEObject = (InternalEObject)oldValue;
/* 2411 */             notifications = 
/* 2412 */               internalEObject.eInverseRemove(
/* 2413 */                 owner, 
/* 2414 */                 internalEObject.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), 
/* 2415 */                 null, 
/* 2416 */                 notifications);
/*      */           } 
/* 2418 */           if (newValue != null)
/*      */           {
/* 2420 */             InternalEObject internalEObject = (InternalEObject)newValue;
/* 2421 */             notifications = 
/* 2422 */               internalEObject.eInverseAdd(
/* 2423 */                 owner, 
/* 2424 */                 internalEObject.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), 
/* 2425 */                 null, 
/* 2426 */                 notifications);
/*      */           }
/*      */         
/*      */         } 
/* 2430 */       } else if (isContainment()) {
/*      */         
/* 2432 */         if (oldValue != newValue) {
/*      */           
/* 2434 */           if (oldValue != null)
/*      */           {
/* 2436 */             notifications = (
/* 2437 */               (InternalEObject)oldValue).eInverseRemove(
/* 2438 */                 owner, 
/* 2439 */                 -1 - owner.eClass().getFeatureID(this.feature), 
/* 2440 */                 null, 
/* 2441 */                 notifications);
/*      */           }
/* 2443 */           if (newValue != null)
/*      */           {
/* 2445 */             notifications = (
/* 2446 */               (InternalEObject)newValue).eInverseAdd(
/* 2447 */                 owner, 
/* 2448 */                 -1 - owner.eClass().getFeatureID(this.feature), 
/* 2449 */                 null, 
/* 2450 */                 notifications);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 2455 */       if (newValue == null && isUnsettable()) {
/*      */         
/* 2457 */         settings.dynamicSet(index, NIL);
/*      */       }
/*      */       else {
/*      */         
/* 2461 */         settings.dynamicSet(index, newValue);
/*      */       } 
/*      */       
/* 2464 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2466 */         ENotificationImpl eNotificationImpl = 
/* 2467 */           new ENotificationImpl(
/* 2468 */             owner, 
/* 2469 */             1, 
/* 2470 */             this.feature, 
/* 2471 */             oldValue, 
/* 2472 */             newValue, (
/* 2473 */             isUnsettable() && !oldIsSet));
/* 2474 */         if (notifications == null)
/*      */         {
/* 2476 */           owner.eNotify((Notification)eNotificationImpl);
/*      */         }
/*      */         else
/*      */         {
/* 2480 */           notifications.add((Notification)eNotificationImpl);
/* 2481 */           notifications.dispatch();
/*      */         }
/*      */       
/* 2484 */       } else if (notifications != null) {
/*      */         
/* 2486 */         notifications.dispatch();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 2492 */       Object oldValue = settings.dynamicGet(index);
/* 2493 */       if (isUnsettable() && oldValue == NIL)
/*      */       {
/* 2495 */         oldValue = null;
/*      */       }
/*      */       
/* 2498 */       NotificationChain notifications = null;
/* 2499 */       if (oldValue != null)
/*      */       {
/* 2501 */         if (hasInverse()) {
/*      */           
/* 2503 */           InternalEObject internalEObject = (InternalEObject)oldValue;
/* 2504 */           notifications = 
/* 2505 */             internalEObject.eInverseRemove(
/* 2506 */               owner, 
/* 2507 */               internalEObject.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), 
/* 2508 */               null, 
/* 2509 */               notifications);
/*      */         }
/* 2511 */         else if (isContainment()) {
/*      */           
/* 2513 */           notifications = (
/* 2514 */             (InternalEObject)oldValue).eInverseRemove(
/* 2515 */               owner, 
/* 2516 */               -1 - owner.eClass().getFeatureID(this.feature), 
/* 2517 */               null, 
/* 2518 */               notifications);
/*      */         } 
/*      */       }
/*      */       
/* 2522 */       settings.dynamicUnset(index);
/*      */       
/* 2524 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2526 */         ENotificationImpl eNotificationImpl = 
/* 2527 */           new ENotificationImpl(
/* 2528 */             owner, 
/* 2529 */             isUnsettable() ? 2 : 1, 
/* 2530 */             this.feature, 
/* 2531 */             oldValue, 
/* 2532 */             null);
/* 2533 */         if (notifications == null)
/*      */         {
/* 2535 */           owner.eNotify((Notification)eNotificationImpl);
/*      */         }
/*      */         else
/*      */         {
/* 2539 */           notifications.add((Notification)eNotificationImpl);
/* 2540 */           notifications.dispatch();
/*      */         }
/*      */       
/* 2543 */       } else if (notifications != null) {
/*      */         
/* 2545 */         notifications.dispatch();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index) {
/* 2551 */       Object setting = settings.dynamicGet(index);
/* 2552 */       return (setting != null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain dynamicInverseAdd(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) {
/*      */       NotificationChainImpl notificationChainImpl;
/* 2559 */       Object oldValue = settings.dynamicGet(index);
/* 2560 */       if (oldValue == NIL)
/*      */       {
/* 2562 */         oldValue = null;
/*      */       }
/*      */       
/* 2565 */       settings.dynamicSet(index, otherEnd);
/*      */       
/* 2567 */       if (hasInverse()) {
/*      */         
/* 2569 */         if (oldValue != otherEnd && oldValue != null)
/*      */         {
/* 2571 */           InternalEObject internalEObject = (InternalEObject)oldValue;
/* 2572 */           notifications = 
/* 2573 */             internalEObject.eInverseRemove(
/* 2574 */               owner, 
/* 2575 */               internalEObject.eClass().getFeatureID((EStructuralFeature)this.inverseFeature), 
/* 2576 */               null, 
/* 2577 */               notifications);
/*      */         }
/*      */       
/* 2580 */       } else if (isContainment()) {
/*      */         
/* 2582 */         if (oldValue != null)
/*      */         {
/* 2584 */           notifications = (
/* 2585 */             (InternalEObject)oldValue).eInverseRemove(
/* 2586 */               owner, 
/* 2587 */               -1 - owner.eClass().getFeatureID(this.feature), 
/* 2588 */               null, 
/* 2589 */               notifications);
/*      */         }
/*      */       } 
/*      */       
/* 2593 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2595 */         if (notifications == null) notificationChainImpl = new NotificationChainImpl(4); 
/* 2596 */         notificationChainImpl.add(
/* 2597 */             (Notification)new ENotificationImpl(
/* 2598 */               owner, 
/* 2599 */               1, 
/* 2600 */               this.feature, 
/* 2601 */               oldValue, 
/* 2602 */               otherEnd));
/*      */       } 
/*      */       
/* 2605 */       return (NotificationChain)notificationChainImpl;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain dynamicInverseRemove(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int index, InternalEObject otherEnd, NotificationChain notifications) {
/*      */       NotificationChainImpl notificationChainImpl;
/* 2612 */       Object oldValue = settings.dynamicGet(index);
/* 2613 */       if (oldValue == NIL)
/*      */       {
/* 2615 */         oldValue = null;
/*      */       }
/*      */       
/* 2618 */       settings.dynamicUnset(index);
/*      */       
/* 2620 */       if (owner.eNotificationRequired()) {
/*      */         
/* 2622 */         if (notifications == null) notificationChainImpl = new NotificationChainImpl(4); 
/* 2623 */         if (isUnsettable()) {
/*      */           
/* 2625 */           notificationChainImpl.add((Notification)new ENotificationImpl(owner, 2, this.feature, oldValue, null));
/*      */         }
/*      */         else {
/*      */           
/* 2629 */           notificationChainImpl.add((Notification)new ENotificationImpl(owner, 1, this.feature, oldValue, null));
/*      */         } 
/*      */       } 
/*      */       
/* 2633 */       return (NotificationChain)notificationChainImpl;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectUnsettable
/*      */     extends InternalSettingDelegateSingleEObject
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectUnsettable(EClass eClass, EStructuralFeature feature) {
/* 2641 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2647 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectWithInverse
/*      */     extends InternalSettingDelegateSingleEObject
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectWithInverse(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2655 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean hasInverse() {
/* 2661 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectWithInverseUnsettable
/*      */     extends InternalSettingDelegateSingleEObjectWithInverse
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectWithInverseUnsettable(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2669 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2675 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainment
/*      */     extends InternalSettingDelegateSingleEObject
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainment(EClass eClass, EStructuralFeature feature) {
/* 2683 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public InternalSettingDelegateSingleEObjectContainment(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2688 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isContainment() {
/* 2694 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentResolving
/*      */     extends InternalSettingDelegateSingleEObjectContainment
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentResolving(EClass eClass, EStructuralFeature feature) {
/* 2702 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 2708 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentWithInverse
/*      */     extends InternalSettingDelegateSingleEObjectContainment
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentWithInverse(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2716 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean hasInverse() {
/* 2722 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentWithInverseResolving
/*      */     extends InternalSettingDelegateSingleEObjectContainmentWithInverse
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentWithInverseResolving(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2730 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 2736 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentUnsettable
/*      */     extends InternalSettingDelegateSingleEObjectContainment
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentUnsettable(EClass eClass, EStructuralFeature feature) {
/* 2744 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2750 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentUnsettableResolving
/*      */     extends InternalSettingDelegateSingleEObjectContainmentUnsettable
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentUnsettableResolving(EClass eClass, EStructuralFeature feature) {
/* 2758 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 2764 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettable
/*      */     extends InternalSettingDelegateSingleEObjectContainmentWithInverse
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettable(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2772 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2778 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettableResolving
/*      */     extends InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettable
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectContainmentWithInverseUnsettableResolving(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2786 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 2792 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectResolving
/*      */     extends InternalSettingDelegateSingleEObject
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectResolving(EClass eClass, EStructuralFeature feature) {
/* 2800 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */     
/*      */     public InternalSettingDelegateSingleEObjectResolving(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2805 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isResolveProxies() {
/* 2811 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectResolvingUnsettable
/*      */     extends InternalSettingDelegateSingleEObject
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectResolvingUnsettable(EClass eClass, EStructuralFeature feature) {
/* 2819 */       super(eClass, feature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2825 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectResolvingWithInverse
/*      */     extends InternalSettingDelegateSingleEObjectResolving
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectResolvingWithInverse(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2833 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean hasInverse() {
/* 2839 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class InternalSettingDelegateSingleEObjectResolvingWithInverseUnsettable
/*      */     extends InternalSettingDelegateSingleEObjectResolvingWithInverse
/*      */   {
/*      */     public InternalSettingDelegateSingleEObjectResolvingWithInverseUnsettable(EClass eClass, EStructuralFeature feature, EReference inverseFeature) {
/* 2847 */       super(eClass, feature, inverseFeature);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isUnsettable() {
/* 2853 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class SettingMany
/*      */     implements EStructuralFeature.Setting {
/*      */     protected EObject owner;
/*      */     protected EStructuralFeature eStructuralFeature;
/*      */     protected List<Object> list;
/*      */     
/*      */     public SettingMany(EObject owner, EStructuralFeature eStructuralFeature, List<Object> list) {
/* 2864 */       this.list = list;
/*      */     }
/*      */ 
/*      */     
/*      */     public EObject getEObject() {
/* 2869 */       return this.owner;
/*      */     }
/*      */ 
/*      */     
/*      */     public EStructuralFeature getEStructuralFeature() {
/* 2874 */       return this.eStructuralFeature;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(boolean resolve) {
/* 2879 */       return this.list;
/*      */     }
/*      */ 
/*      */     
/*      */     public void set(Object newValue) {
/* 2884 */       this.list.clear();
/* 2885 */       this.list.addAll((List)newValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isSet() {
/* 2890 */       return 
/* 2891 */         (this.list instanceof InternalEList.Unsettable) ? (
/* 2892 */         (InternalEList.Unsettable)this.list).isSet() : (
/* 2893 */         !this.list.isEmpty());
/*      */     }
/*      */ 
/*      */     
/*      */     public void unset() {
/* 2898 */       if (this.list instanceof InternalEList.Unsettable) {
/*      */         
/* 2900 */         ((InternalEList.Unsettable)this.list).unset();
/*      */       }
/*      */       else {
/*      */         
/* 2904 */         this.list.clear();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFeatureMap() {
/* 2914 */     if (this.cachedEType != this.eType) {
/*      */       
/* 2916 */       this.cachedEType = getEType();
/* 2917 */       this.cachedIsFeatureMap = (this.eType != null && this.eType.getInstanceClassName() == "org.eclipse.emf.ecore.util.FeatureMap$Entry");
/*      */     } 
/* 2919 */     return this.cachedIsFeatureMap;
/*      */   }
/*      */   
/*      */   public static abstract class BasicFeatureMapEntry
/*      */     implements FeatureMap.Entry.Internal
/*      */   {
/*      */     protected final EStructuralFeature.Internal eStructuralFeature;
/*      */     
/*      */     BasicFeatureMapEntry(EStructuralFeature.Internal eStructuralFeature) {
/* 2928 */       this.eStructuralFeature = eStructuralFeature;
/*      */     }
/*      */ 
/*      */     
/*      */     public final EStructuralFeature getEStructuralFeature() {
/* 2933 */       return (EStructuralFeature)this.eStructuralFeature;
/*      */     }
/*      */ 
/*      */     
/*      */     public void validate(Object value) {
/* 2938 */       if (value != null && !this.eStructuralFeature.getEType().isInstance(value)) {
/*      */         
/* 2940 */         String valueClass = (value instanceof EObject) ? ((EObject)value).eClass().getName() : value.getClass().getName();
/*      */         
/* 2942 */         throw new ClassCastException(
/* 2943 */             "The feature '" + this.eStructuralFeature.getName() + "'s type '" + 
/* 2944 */             this.eStructuralFeature.getEType().getName() + "' does not permit a value of type '" + valueClass + "'");
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public FeatureMap.Entry.Internal createEntry(Object value) {
/* 2950 */       return createEntry((InternalEObject)value);
/*      */     }
/*      */ 
/*      */     
/*      */     public FeatureMap.Entry.Internal createEntry(InternalEObject value) {
/* 2955 */       return createEntry(value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object that) {
/* 2961 */       if (this == that)
/*      */       {
/* 2963 */         return true;
/*      */       }
/* 2965 */       if (!(that instanceof FeatureMap.Entry))
/*      */       {
/* 2967 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2971 */       FeatureMap.Entry entry = (FeatureMap.Entry)that;
/* 2972 */       if (entry.getEStructuralFeature() == getEStructuralFeature()) {
/*      */         
/* 2974 */         Object value = getValue();
/* 2975 */         return (value == null) ? ((entry.getValue() == null)) : value.equals(entry.getValue());
/*      */       } 
/*      */ 
/*      */       
/* 2979 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 2987 */       Object value = getValue();
/* 2988 */       return getEStructuralFeature().hashCode() ^ ((value == null) ? 0 : value.hashCode());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2994 */       EStructuralFeature eStructuralFeature = getEStructuralFeature();
/* 2995 */       String prefix = eStructuralFeature.getEContainingClass().getEPackage().getNsPrefix();
/* 2996 */       eStructuralFeature.getName();
/* 2997 */       return 
/*      */ 
/*      */         
/* 3000 */         String.valueOf((prefix != null && prefix.length() != 0) ? (String.valueOf(prefix) + ":" + eStructuralFeature.getName()) : eStructuralFeature.getName()) + 
/* 3001 */         "=" + getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class SimpleFeatureMapEntry
/*      */     extends BasicFeatureMapEntry
/*      */   {
/*      */     protected final Object value;
/*      */     
/*      */     public SimpleFeatureMapEntry(EStructuralFeature.Internal eStructuralFeature, Object value) {
/* 3011 */       super(eStructuralFeature);
/* 3012 */       this.value = value;
/*      */     }
/*      */ 
/*      */     
/*      */     public final Object getValue() {
/* 3017 */       return this.value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureMap.Entry.Internal createEntry(Object value) {
/* 3023 */       return new SimpleFeatureMapEntry(this.eStructuralFeature, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3028 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3033 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3038 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3043 */       return notifications;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class SimpleContentFeatureMapEntry
/*      */     extends BasicFeatureMapEntry
/*      */   {
/*      */     protected EFactory eFactory;
/*      */     protected EDataType eDataType;
/*      */     
/*      */     public SimpleContentFeatureMapEntry(EStructuralFeature.Internal eStructuralFeature) {
/* 3054 */       super(eStructuralFeature);
/* 3055 */       this.eDataType = (EDataType)eStructuralFeature.getEType();
/* 3056 */       this.eFactory = this.eDataType.getEPackage().getEFactoryInstance();
/*      */     }
/*      */ 
/*      */     
/*      */     public final Object getValue() {
/* 3061 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureMap.Entry.Internal createEntry(Object value) {
/* 3067 */       return 
/* 3068 */         new EStructuralFeatureImpl.SimpleFeatureMapEntry(
/* 3069 */           (EStructuralFeature.Internal)XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, 
/* 3070 */           this.eFactory.convertToString(this.eDataType, value));
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3075 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3080 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3085 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3090 */       return notifications;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final class InverseUpdatingFeatureMapEntry
/*      */     extends BasicFeatureMapEntry
/*      */   {
/*      */     protected final InternalEObject value;
/*      */     
/*      */     public InverseUpdatingFeatureMapEntry(EStructuralFeature.Internal eStructuralFeature, InternalEObject value) {
/* 3101 */       super(eStructuralFeature);
/* 3102 */       this.value = value;
/*      */     }
/*      */ 
/*      */     
/*      */     public final Object getValue() {
/* 3107 */       return this.value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureMap.Entry.Internal createEntry(InternalEObject value) {
/* 3113 */       return new InverseUpdatingFeatureMapEntry(this.eStructuralFeature, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3118 */       return inverseAdd(owner, this.value, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3123 */       return inverseRemove(owner, this.value, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3128 */       return inverseAdd(owner, this.value, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3133 */       return inverseRemove(owner, this.value, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     protected final NotificationChain inverseAdd(InternalEObject owner, InternalEObject otherEnd, int featureID, NotificationChain notifications) {
/* 3138 */       if (otherEnd != null)
/*      */       {
/* 3140 */         notifications = 
/* 3141 */           otherEnd.eInverseAdd(
/* 3142 */             owner, 
/* 3143 */             otherEnd.eClass().getFeatureID((EStructuralFeature)this.eStructuralFeature.getEOpposite()), 
/* 3144 */             null, 
/* 3145 */             notifications);
/*      */       }
/*      */       
/* 3148 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     protected final NotificationChain inverseRemove(InternalEObject owner, InternalEObject otherEnd, int featureID, NotificationChain notifications) {
/* 3153 */       if (otherEnd != null)
/*      */       {
/* 3155 */         notifications = 
/* 3156 */           otherEnd.eInverseRemove(
/* 3157 */             owner, 
/* 3158 */             otherEnd.eClass().getFeatureID((EStructuralFeature)this.eStructuralFeature.getEOpposite()), 
/* 3159 */             null, 
/* 3160 */             notifications);
/*      */       }
/* 3162 */       return notifications;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class ContainmentUpdatingFeatureMapEntry
/*      */     extends BasicFeatureMapEntry
/*      */   {
/*      */     protected final InternalEObject value;
/*      */     
/*      */     public ContainmentUpdatingFeatureMapEntry(EStructuralFeature.Internal eStructuralFeature, InternalEObject value) {
/* 3172 */       super(eStructuralFeature);
/* 3173 */       this.value = value;
/*      */     }
/*      */ 
/*      */     
/*      */     public final Object getValue() {
/* 3178 */       return this.value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final FeatureMap.Entry.Internal createEntry(InternalEObject value) {
/* 3184 */       return new ContainmentUpdatingFeatureMapEntry(this.eStructuralFeature, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3189 */       return inverseAdd(owner, this.value, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications) {
/* 3194 */       return inverseRemove(owner, this.value, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3199 */       return inverseAdd(owner, (InternalEObject)otherEnd, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     public final NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications) {
/* 3204 */       return inverseRemove(owner, (InternalEObject)otherEnd, featureID, notifications);
/*      */     }
/*      */ 
/*      */     
/*      */     protected final NotificationChain inverseAdd(InternalEObject owner, InternalEObject otherEnd, int featureID, NotificationChain notifications) {
/* 3209 */       if (otherEnd != null) {
/*      */         
/* 3211 */         int containmentFeatureID = owner.eClass().getFeatureID((EStructuralFeature)this.eStructuralFeature);
/* 3212 */         notifications = 
/* 3213 */           otherEnd.eInverseAdd(
/* 3214 */             owner, 
/* 3215 */             -1 - ((containmentFeatureID == -1) ? featureID : containmentFeatureID), 
/* 3216 */             null, 
/* 3217 */             notifications);
/*      */       } 
/*      */       
/* 3220 */       return notifications;
/*      */     }
/*      */ 
/*      */     
/*      */     protected final NotificationChain inverseRemove(InternalEObject owner, InternalEObject otherEnd, int featureID, NotificationChain notifications) {
/* 3225 */       if (otherEnd != null) {
/*      */         
/* 3227 */         int containmentFeatureID = owner.eClass().getFeatureID((EStructuralFeature)this.eStructuralFeature);
/* 3228 */         notifications = 
/* 3229 */           otherEnd.eInverseRemove(
/* 3230 */             owner, 
/* 3231 */             -1 - ((containmentFeatureID == -1) ? featureID : containmentFeatureID), 
/* 3232 */             null, 
/* 3233 */             notifications);
/*      */       } 
/*      */       
/* 3236 */       return notifications;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FeatureMap.Entry.Internal getFeatureMapEntryPrototype() {
/* 3244 */     if (this.prototypeFeatureMapEntry == null) {
/*      */       
/* 3246 */       EReference eOpposite = getEOpposite();
/* 3247 */       if (eOpposite != null) {
/*      */         
/* 3249 */         this.prototypeFeatureMapEntry = new InverseUpdatingFeatureMapEntry(this, null);
/*      */       }
/* 3251 */       else if (isContainment()) {
/*      */ 
/*      */         
/* 3254 */         this.prototypeFeatureMapEntry = new ContainmentUpdatingFeatureMapEntry(this, null);
/*      */       }
/* 3256 */       else if (ExtendedMetaData.INSTANCE.getFeatureKind(this) == 1) {
/*      */         
/* 3258 */         this.prototypeFeatureMapEntry = new SimpleContentFeatureMapEntry(this);
/*      */       }
/*      */       else {
/*      */         
/* 3262 */         this.prototypeFeatureMapEntry = new SimpleFeatureMapEntry(this, null);
/*      */       } 
/*      */     } 
/* 3265 */     return this.prototypeFeatureMapEntry;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFeatureMapEntryPrototype(FeatureMap.Entry.Internal prototype) {
/* 3270 */     this.prototypeFeatureMapEntry = prototype;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BasicExtendedMetaData.EStructuralFeatureExtendedMetaData getExtendedMetaData() {
/* 3277 */     return this.eStructuralFeatureExtendedMetaData;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setExtendedMetaData(BasicExtendedMetaData.EStructuralFeatureExtendedMetaData eStructuralFeatureExtendedMetaData) {
/* 3282 */     this.eStructuralFeatureExtendedMetaData = eStructuralFeatureExtendedMetaData;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setName(String newName) {
/* 3288 */     if (this.eContainer instanceof EClassImpl)
/*      */     {
/* 3290 */       ((EClassImpl)this.eContainer).getESuperAdapter().setFlags(4);
/*      */     }
/* 3292 */     super.setName(newName);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecore\impl\EStructuralFeatureImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */