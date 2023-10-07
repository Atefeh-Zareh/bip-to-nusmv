/*      */ package org.eclipse.emf.ecore.util;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import org.eclipse.emf.common.notify.Notification;
/*      */ import org.eclipse.emf.common.notify.NotificationChain;
/*      */ import org.eclipse.emf.common.notify.impl.NotificationImpl;
/*      */ import org.eclipse.emf.common.util.AbstractEList;
/*      */ import org.eclipse.emf.common.util.BasicEList;
/*      */ import org.eclipse.emf.common.util.EList;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EObject;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.EStructuralFeature;
/*      */ import org.eclipse.emf.ecore.EcorePackage;
/*      */ import org.eclipse.emf.ecore.InternalEObject;
/*      */ import org.eclipse.emf.ecore.impl.ENotificationImpl;
/*      */ import org.eclipse.emf.ecore.impl.EObjectImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DelegatingFeatureMap
/*      */   extends DelegatingEcoreEList<FeatureMap.Entry>
/*      */   implements FeatureMap.Internal, FeatureMap.Internal.Wrapper
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public static class FeatureMapEObjectImpl
/*      */     extends EObjectImpl
/*      */   {
/* 2247 */     protected DelegatingFeatureMap featureMap = new DelegatingFeatureMap((InternalEObject)this, -1)
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */         
/* 2251 */         protected List<FeatureMap.Entry> theList = new ArrayList<FeatureMap.Entry>();
/*      */ 
/*      */         
/*      */         protected List<FeatureMap.Entry> delegateList() {
/* 2255 */           return this.theList;
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object eDynamicGet(EStructuralFeature eFeature, boolean resolve) {
/* 2268 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
/*      */       {
/* 2270 */         return eSettingDelegate(eFeature).dynamicGet((InternalEObject)this, null, -1, true, true);
/*      */       }
/*      */ 
/*      */       
/* 2274 */       return this.featureMap.setting(eFeature).get(resolve);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void eDynamicSet(EStructuralFeature eFeature, Object newValue) {
/* 2281 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer()) {
/*      */         
/* 2283 */         eSettingDelegate(eFeature).dynamicSet((InternalEObject)this, null, -1, newValue);
/*      */       }
/*      */       else {
/*      */         
/* 2287 */         if (!eFeature.isUnsettable()) {
/*      */           
/* 2289 */           Object defaultValue = eFeature.getDefaultValue();
/* 2290 */           if ((defaultValue == null) ? (newValue == null) : defaultValue.equals(newValue)) {
/*      */             
/* 2292 */             this.featureMap.setting(eFeature).unset();
/*      */             return;
/*      */           } 
/*      */         } 
/* 2296 */         this.featureMap.setting(eFeature).set(newValue);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void eDynamicUnset(EStructuralFeature eFeature) {
/* 2303 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer()) {
/*      */         
/* 2305 */         eSettingDelegate(eFeature).dynamicUnset((InternalEObject)this, null, -1);
/*      */       }
/*      */       else {
/*      */         
/* 2309 */         this.featureMap.setting(eFeature).unset();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean eDynamicIsSet(EStructuralFeature eFeature) {
/* 2316 */       if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
/*      */       {
/* 2318 */         return eSettingDelegate(eFeature).dynamicIsSet((InternalEObject)this, null, -1);
/*      */       }
/*      */ 
/*      */       
/* 2322 */       return this.featureMap.setting(eFeature).isSet();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain eDynamicInverseAdd(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain notifications) {
/* 2329 */       EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEStructuralFeature(featureID);
/* 2330 */       if (feature.isMany())
/*      */       {
/* 2332 */         return this.featureMap.basicAdd((EStructuralFeature)feature, otherEnd, notifications);
/*      */       }
/* 2334 */       if (feature instanceof EReference && ((EReference)feature).isContainer())
/*      */       {
/* 2336 */         return eSettingDelegate((EStructuralFeature)feature).dynamicInverseAdd((InternalEObject)this, null, -1, otherEnd, notifications);
/*      */       }
/*      */ 
/*      */       
/* 2340 */       InternalEObject oldValue = (InternalEObject)eDynamicGet((EStructuralFeature)feature, false);
/* 2341 */       if (oldValue != null) {
/*      */         
/* 2343 */         notifications = oldValue.eInverseRemove(
/* 2344 */             (InternalEObject)this, oldValue.eClass().getFeatureID((EStructuralFeature)((EReference)feature).getEOpposite()), null, notifications);
/* 2345 */         notifications = this.featureMap.basicRemove((EStructuralFeature)feature, oldValue, notifications);
/*      */       } 
/*      */       
/* 2348 */       return this.featureMap.basicAdd((EStructuralFeature)feature, otherEnd, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NotificationChain eDynamicInverseRemove(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain notifications) {
/* 2355 */       EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEStructuralFeature(featureID);
/* 2356 */       if (feature instanceof EReference && ((EReference)feature).isContainer())
/*      */       {
/* 2358 */         return eSettingDelegate((EStructuralFeature)feature).dynamicInverseRemove((InternalEObject)this, null, -1, otherEnd, notifications);
/*      */       }
/*      */ 
/*      */       
/* 2362 */       return this.featureMap.basicRemove((EStructuralFeature)feature, otherEnd, notifications);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FeatureMap featureMap() {
/* 2368 */       return this.featureMap;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void eNotify(Notification notification) {
/* 2374 */       if (notification.getFeatureID(null) != -1)
/*      */       {
/* 2376 */         super.eNotify(notification);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2383 */       String result = super.toString();
/* 2384 */       result = "org.eclipse.emf.ecore.impl.EObjectImpl" + result.substring(result.indexOf("@"));
/* 2385 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   protected FeatureMap.Internal.Wrapper wrapper = this;
/*      */   
/*      */   public void set(Object newValue) {
/* 2392 */     super.set((newValue instanceof FeatureMap) ? newValue : ((FeatureMap.Internal.Wrapper)newValue).featureMap());
/*      */   }
/*      */   protected final FeatureMapUtil.Validator featureMapValidator;
/*      */   protected final EStructuralFeature eStructuralFeature;
/*      */   
/*      */   protected FeatureMap.Entry resolve(int index, FeatureMap.Entry entry) {
/* 2398 */     EStructuralFeature feature = entry.getEStructuralFeature();
/* 2399 */     if (isResolveProxies(feature)) {
/*      */       
/* 2401 */       InternalEObject object = (InternalEObject)entry.getValue();
/* 2402 */       EObject resolved = resolveProxy((EObject)object);
/* 2403 */       if (resolved != object) {
/*      */         NotificationImpl notificationImpl; NotificationChain notificationChain1;
/* 2405 */         FeatureMap.Entry newEntry = createEntry(feature, resolved);
/* 2406 */         delegateSet(index, validate(index, newEntry));
/* 2407 */         didSet(index, newEntry, entry);
/*      */         
/* 2409 */         NotificationChain notifications = null;
/*      */ 
/*      */ 
/*      */         
/* 2413 */         if (isNotificationRequired()) {
/*      */           
/* 2415 */           EStructuralFeature affiliatedFeature = ExtendedMetaData.INSTANCE.getAffiliation(this.owner.eClass(), feature);
/* 2416 */           if (affiliatedFeature != getEStructuralFeature()) {
/*      */             
/* 2418 */             FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/* 2419 */             int featureIndex = 0;
/* 2420 */             for (int i = 0; i < index; i++) {
/*      */               
/* 2422 */               FeatureMap.Entry affliatedEntry = (FeatureMap.Entry)delegateGet(i);
/* 2423 */               if (validator.isValid(affliatedEntry.getEStructuralFeature()))
/*      */               {
/* 2425 */                 featureIndex++;
/*      */               }
/*      */             } 
/*      */             
/* 2429 */             notificationImpl = 
/* 2430 */               createNotification(
/* 2431 */                 9, 
/* 2432 */                 affiliatedFeature, 
/* 2433 */                 object, 
/* 2434 */                 resolved, 
/* 2435 */                 featureIndex, 
/* 2436 */                 false);
/*      */             
/* 2438 */             notificationImpl.add((Notification)createNotification(9, entry, newEntry, index, false));
/*      */           } 
/*      */         } 
/*      */         
/* 2442 */         EReference reference = (EReference)feature;
/* 2443 */         EReference opposite = reference.getEOpposite();
/* 2444 */         if (opposite != null) {
/*      */           
/* 2446 */           notificationChain1 = object.eInverseRemove(this.owner, object.eClass().getFeatureID((EStructuralFeature)opposite), null, (NotificationChain)notificationImpl);
/* 2447 */           notificationChain1 = ((InternalEObject)resolved).eInverseAdd(this.owner, resolved.eClass().getFeatureID((EStructuralFeature)opposite), null, notificationChain1);
/*      */         }
/* 2449 */         else if (reference.isContainment()) {
/*      */           
/* 2451 */           int inverseFeatureID = -1 - this.owner.eClass().getFeatureID((EStructuralFeature)reference);
/* 2452 */           notificationChain1 = object.eInverseRemove(this.owner, inverseFeatureID, null, null);
/* 2453 */           if (((InternalEObject)resolved).eInternalContainer() == null)
/*      */           {
/* 2455 */             notificationChain1 = ((InternalEObject)resolved).eInverseAdd(this.owner, inverseFeatureID, null, notificationChain1);
/*      */           }
/*      */         } 
/* 2458 */         if (notificationChain1 != null)
/*      */         {
/* 2460 */           notificationChain1.dispatch();
/*      */         }
/*      */         
/* 2463 */         return newEntry;
/*      */       } 
/*      */     } 
/* 2466 */     return entry;
/*      */   }
/*      */   
/*      */   public DelegatingFeatureMap(InternalEObject owner, int featureID) {
/*      */     super(owner);
/*      */     this.eStructuralFeature = owner.eClass().getEStructuralFeature(featureID);
/*      */     this.featureMapValidator = FeatureMapUtil.getValidator(owner.eClass(), getEStructuralFeature());
/*      */   }
/*      */   
/*      */   public DelegatingFeatureMap(InternalEObject owner, EStructuralFeature eStructuralFeature) {
/*      */     super(owner);
/*      */     this.eStructuralFeature = eStructuralFeature;
/*      */     this.featureMapValidator = FeatureMapUtil.getValidator(owner.eClass(), getEStructuralFeature());
/*      */   }
/*      */   
/*      */   public FeatureMap.Internal.Wrapper getWrapper() {
/*      */     return this.wrapper;
/*      */   }
/*      */   
/*      */   public void setWrapper(FeatureMap.Internal.Wrapper wrapper) {
/*      */     this.wrapper = wrapper;
/*      */   }
/*      */   
/*      */   public FeatureMap featureMap() {
/*      */     return this;
/*      */   }
/*      */   
/*      */   protected FeatureMap.Entry validate(int index, FeatureMap.Entry object) {
/*      */     if (this.modCount == 0)
/*      */       return object; 
/*      */     FeatureMap.Entry result = super.validate(index, object);
/*      */     EStructuralFeature eStructuralFeature = object.getEStructuralFeature();
/*      */     if (!eStructuralFeature.isChangeable() || !this.featureMapValidator.isValid(eStructuralFeature))
/*      */       throw new RuntimeException("Invalid entry feature '" + eStructuralFeature.getEContainingClass().getName() + "." + eStructuralFeature.getName() + "'"); 
/*      */     return result;
/*      */   }
/*      */   
/*      */   protected boolean isEObject() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   protected boolean isUnique() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   protected boolean canContainNull() {
/*      */     return false;
/*      */   }
/*      */   
/*      */   protected EClassifier getFeatureType() {
/*      */     return (EClassifier)EcorePackage.Literals.EJAVA_OBJECT;
/*      */   }
/*      */   
/*      */   public EStructuralFeature getEStructuralFeature() {
/*      */     return this.eStructuralFeature;
/*      */   }
/*      */   
/*      */   protected FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value) {
/*      */     return FeatureMapUtil.createEntry(eStructuralFeature, value);
/*      */   }
/*      */   
/*      */   protected FeatureMap.Entry.Internal createRawEntry(EStructuralFeature eStructuralFeature, Object value) {
/*      */     return FeatureMapUtil.createRawEntry(eStructuralFeature, value);
/*      */   }
/*      */   
/*      */   protected NotificationImpl createNotification(int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index, boolean wasSet) {
/*      */     return (NotificationImpl)new FeatureMapUtil.FeatureENotificationImpl(this.owner, eventType, feature, oldObject, newObject, index, wasSet);
/*      */   }
/*      */   
/*      */   protected boolean isMany(EStructuralFeature feature) {
/*      */     return FeatureMapUtil.isMany((EObject)this.owner, feature);
/*      */   }
/*      */   
/*      */   protected boolean hasInverse() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   protected boolean hasShadow() {
/*      */     return true;
/*      */   }
/*      */   
/*      */   protected int entryIndex(EStructuralFeature feature, int index) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int count = 0;
/*      */     int size = delegateSize();
/*      */     int result = size;
/*      */     for (int i = 0; i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         if (index == count)
/*      */           return i; 
/*      */         count++;
/*      */         result = i + 1;
/*      */       } 
/*      */     } 
/*      */     if (index == count)
/*      */       return result; 
/*      */     throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */   }
/*      */   
/*      */   protected boolean isResolveProxies(EStructuralFeature feature) {
/*      */     return (feature instanceof EReference && ((EReference)feature).isResolveProxies());
/*      */   }
/*      */   
/*      */   public Object resolveProxy(EStructuralFeature feature, int entryIndex, int index, Object object) {
/*      */     EObject resolved = resolveProxy((EObject)object);
/*      */     if (resolved != object) {
/*      */       FeatureMap.Entry oldObject = (FeatureMap.Entry)delegateGet(entryIndex);
/*      */       FeatureMap.Entry entry = createEntry(feature, resolved);
/*      */       delegateSet(entryIndex, validate(entryIndex, entry));
/*      */       didSet(entryIndex, entry, oldObject);
/*      */       if (isNotificationRequired()) {
/*      */         NotificationImpl notifications = createNotification(9, entry.getEStructuralFeature(), object, resolved, index, false);
/*      */         notifications.add((Notification)createNotification(9, oldObject, entry, index, false));
/*      */         notifications.dispatch();
/*      */       } 
/*      */       return resolved;
/*      */     } 
/*      */     return object;
/*      */   }
/*      */   
/*      */   protected EObject resolveProxy(EObject eObject) {
/*      */     return this.owner.eResolveProxy((InternalEObject)eObject);
/*      */   }
/*      */   
/*      */   public int getModCount() {
/*      */     return this.modCount;
/*      */   }
/*      */   
/*      */   public EStructuralFeature getEStructuralFeature(int index) {
/*      */     return ((FeatureMap.Entry)get(index)).getEStructuralFeature();
/*      */   }
/*      */   
/*      */   public Object getValue(int index) {
/*      */     return ((FeatureMap.Entry)get(index)).getValue();
/*      */   }
/*      */   
/*      */   public Object setValue(int index, Object value) {
/*      */     return set(index, createEntry(getEStructuralFeature(index), value)).getValue();
/*      */   }
/*      */   
/*      */   public NotificationChain shadowAdd(FeatureMap.Entry object, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*      */     if (isNotificationRequired()) {
/*      */       EStructuralFeature feature = object.getEStructuralFeature();
/*      */       Object value = object.getValue();
/*      */       NotificationImpl notification = feature.isMany() ? createNotification(3, feature, (Object)null, value, indexOf(feature, value), true) : createNotification(1, feature, feature.getDefaultValue(), value, -1, true);
/*      */       if (notifications != null) {
/*      */         notifications.add((Notification)notification);
/*      */       } else {
/*      */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*      */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */   
/*      */   public NotificationChain inverseAdd(FeatureMap.Entry object, NotificationChain notifications) {
/*      */     FeatureMap.Entry.Internal entry = (FeatureMap.Entry.Internal)object;
/*      */     return entry.inverseAdd(this.owner, getFeatureID(), notifications);
/*      */   }
/*      */   
/*      */   public NotificationChain shadowRemove(FeatureMap.Entry object, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*      */     if (isNotificationRequired()) {
/*      */       EStructuralFeature feature = object.getEStructuralFeature();
/*      */       Object value = object.getValue();
/*      */       NotificationImpl notification = feature.isMany() ? createNotification(4, feature, value, (Object)null, indexOf(feature, value), true) : createNotification(feature.isUnsettable() ? 2 : 1, feature, value, feature.getDefaultValue(), -1, true);
/*      */       if (notifications != null) {
/*      */         notifications.add((Notification)notification);
/*      */       } else {
/*      */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*      */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */   
/*      */   public NotificationChain inverseRemove(FeatureMap.Entry object, NotificationChain notifications) {
/*      */     FeatureMap.Entry.Internal entry = (FeatureMap.Entry.Internal)object;
/*      */     return entry.inverseRemove(this.owner, getFeatureID(), notifications);
/*      */   }
/*      */   
/*      */   public NotificationChain shadowSet(FeatureMap.Entry oldObject, FeatureMap.Entry newObject, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*      */     if (isNotificationRequired()) {
/*      */       EStructuralFeature feature = oldObject.getEStructuralFeature();
/*      */       Object oldValue = oldObject.getValue();
/*      */       Object newValue = newObject.getValue();
/*      */       NotificationImpl notification = createNotification(1, feature, oldValue, newValue, feature.isMany() ? indexOf(feature, newValue) : -1, true);
/*      */       if (notifications != null) {
/*      */         notifications.add((Notification)notification);
/*      */       } else {
/*      */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*      */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */   
/*      */   public NotificationChain inverseTouch(Object object, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*      */     if (isNotificationRequired()) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)object;
/*      */       EStructuralFeature feature = entry.getEStructuralFeature();
/*      */       Object value = entry.getValue();
/*      */       NotificationImpl notification = createNotification(1, feature, value, value, feature.isMany() ? indexOf(feature, value) : -1, true);
/*      */       if (notifications != null) {
/*      */         notifications.add((Notification)notification);
/*      */       } else {
/*      */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*      */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */   
/*      */   public FeatureMap.Entry move(int targetIndex, int sourceIndex) {
/*      */     if (!isNotificationRequired())
/*      */       return (FeatureMap.Entry)doMove(targetIndex, sourceIndex); 
/*      */     if (targetIndex != sourceIndex) {
/*      */       FeatureMap.Entry sourceEntry = (FeatureMap.Entry)delegateGet(sourceIndex);
/*      */       EStructuralFeature feature = sourceEntry.getEStructuralFeature();
/*      */       if (isMany(feature)) {
/*      */         FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */         int featureTargetIndex = -1;
/*      */         int featureSourceIndex = -1;
/*      */         int count = 0;
/*      */         for (int i = 0, maxIndex = (targetIndex > sourceIndex) ? targetIndex : sourceIndex; i <= maxIndex; i++) {
/*      */           if (i == sourceIndex) {
/*      */             featureSourceIndex = count++;
/*      */           } else {
/*      */             FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */             boolean isValid = validator.isValid(entry.getEStructuralFeature());
/*      */             if (i == targetIndex)
/*      */               featureTargetIndex = (i == maxIndex && !isValid) ? (count - 1) : count; 
/*      */             if (isValid)
/*      */               count++; 
/*      */           } 
/*      */         } 
/*      */         FeatureMap.Entry result = (FeatureMap.Entry)super.move(targetIndex, sourceIndex);
/*      */         if (featureSourceIndex != featureTargetIndex)
/*      */           dispatchNotification((Notification)new ENotificationImpl(this.owner, 7, feature, Integer.valueOf(featureSourceIndex), sourceEntry.getValue(), featureTargetIndex)); 
/*      */         return result;
/*      */       } 
/*      */     } 
/*      */     return (FeatureMap.Entry)super.move(targetIndex, sourceIndex);
/*      */   }
/*      */   
/*      */   public FeatureMap.Entry set(int index, FeatureMap.Entry object) {
/*      */     EStructuralFeature entryFeature = object.getEStructuralFeature();
/*      */     if (isMany(entryFeature)) {
/*      */       if (entryFeature.isUnique())
/*      */         for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */           FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(i);
/*      */           if (otherEntry.equals(object) && i != index)
/*      */             throw new IllegalArgumentException("The 'no duplicates' constraint is violated"); 
/*      */         }  
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(otherEntry.getEStructuralFeature()) && i != index)
/*      */           throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       } 
/*      */     } 
/*      */     return doSet(index, object);
/*      */   }
/*      */   
/*      */   public FeatureMap.Entry doSet(int index, FeatureMap.Entry object) {
/*      */     return (FeatureMap.Entry)super.set(index, object);
/*      */   }
/*      */   
/*      */   public boolean add(FeatureMap.Entry object) {
/*      */     EStructuralFeature entryFeature = object.getEStructuralFeature();
/*      */     if (isMany(entryFeature)) {
/*      */       if (entryFeature.isUnique() && contains(entryFeature, object.getValue()))
/*      */         return false; 
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(otherEntry.getEStructuralFeature())) {
/*      */           if (otherEntry.equals(object))
/*      */             return false; 
/*      */           doSet(i, object);
/*      */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return doAdd(object);
/*      */   }
/*      */   
/*      */   protected boolean doAdd(FeatureMap.Entry object) {
/*      */     return super.add(object);
/*      */   }
/*      */   
/*      */   public void add(int index, FeatureMap.Entry object) {
/*      */     EStructuralFeature entryFeature = object.getEStructuralFeature();
/*      */     if (isMany(entryFeature)) {
/*      */       if (entryFeature.isUnique())
/*      */         for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */           FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(i);
/*      */           if (otherEntry.equals(object) && i != index)
/*      */             throw new IllegalArgumentException("The 'no duplicates' constraint is violated"); 
/*      */         }  
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(otherEntry.getEStructuralFeature()))
/*      */           throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       } 
/*      */     } 
/*      */     doAdd(index, object);
/*      */   }
/*      */   
/*      */   public void doAdd(int index, FeatureMap.Entry object) {
/*      */     super.add(index, object);
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection<? extends FeatureMap.Entry> collection) {
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/*      */     for (FeatureMap.Entry entry : collection) {
/*      */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*      */       if (isMany(entryFeature)) {
/*      */         if (!entryFeature.isUnique() || (!contains(entryFeature, entry.getValue()) && !basicEList.contains(entry)))
/*      */           basicEList.add(entry); 
/*      */         continue;
/*      */       } 
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*      */       boolean include = true;
/*      */       for (int j = 0, size = delegateSize(); j < size; j++) {
/*      */         FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(j);
/*      */         if (validator.isValid(otherEntry.getEStructuralFeature())) {
/*      */           doSet(j, entry);
/*      */           include = false;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       if (include)
/*      */         basicEList.add(entry); 
/*      */     } 
/*      */     return doAddAll((Collection<? extends FeatureMap.Entry>)basicEList);
/*      */   }
/*      */   
/*      */   public boolean doAddAll(Collection<? extends FeatureMap.Entry> collection) {
/*      */     return super.addAll(collection);
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection<? extends FeatureMap.Entry> collection) {
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/*      */     for (FeatureMap.Entry entry : collection) {
/*      */       EStructuralFeature entryFeature = entry.getEStructuralFeature();
/*      */       if (isMany(entryFeature)) {
/*      */         if (!entryFeature.isUnique() || (!contains(entryFeature, entry.getValue()) && !basicEList.contains(entry)))
/*      */           basicEList.add(entry); 
/*      */         continue;
/*      */       } 
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), entryFeature);
/*      */       boolean include = true;
/*      */       for (int j = 0, size = delegateSize(); j < size; j++) {
/*      */         FeatureMap.Entry otherEntry = (FeatureMap.Entry)delegateGet(j);
/*      */         if (validator.isValid(otherEntry.getEStructuralFeature())) {
/*      */           doSet(j, entry);
/*      */           include = false;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       if (include)
/*      */         basicEList.add(entry); 
/*      */     } 
/*      */     return doAddAll(index, (Collection<? extends FeatureMap.Entry>)basicEList);
/*      */   }
/*      */   
/*      */   public boolean doAddAll(int index, Collection<? extends FeatureMap.Entry> collection) {
/*      */     return super.addAll(index, collection);
/*      */   }
/*      */   
/*      */   public int size(EStructuralFeature feature) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int result = 0;
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         result++; 
/*      */     } 
/*      */     return result;
/*      */   }
/*      */   
/*      */   public boolean isEmpty(EStructuralFeature feature) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         return false; 
/*      */     } 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean contains(EStructuralFeature feature, Object object) {
/*      */     return contains(feature, object, isResolveProxies(feature));
/*      */   }
/*      */   
/*      */   public boolean basicContains(EStructuralFeature feature, Object object) {
/*      */     return contains(feature, object, false);
/*      */   }
/*      */   
/*      */   protected boolean contains(EStructuralFeature feature, Object object, boolean resolve) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()) && entry.equals(object))
/*      */           return true; 
/*      */       } 
/*      */     } else if (object != null) {
/*      */       int size = delegateSize();
/*      */       int i;
/*      */       for (i = 0; i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()) && object.equals(entry.getValue()))
/*      */           return true; 
/*      */       } 
/*      */       if (resolve)
/*      */         for (i = 0; i < size; i++) {
/*      */           FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */           if (validator.isValid(entry.getEStructuralFeature()) && object == resolveProxy((EObject)entry.getValue()))
/*      */             return true; 
/*      */         }  
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()) && entry.getValue() == null)
/*      */           return false; 
/*      */       } 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsAll(EStructuralFeature feature, Collection<?> collection) {
/*      */     for (Object object : collection) {
/*      */       if (!contains(feature, object))
/*      */         return false; 
/*      */     } 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean basicContainsAll(EStructuralFeature feature, Collection<?> collection) {
/*      */     for (Object object : collection) {
/*      */       if (!basicContains(feature, object))
/*      */         return false; 
/*      */     } 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public int indexOf(EStructuralFeature feature, Object object) {
/*      */     return indexOf(feature, object, isResolveProxies(feature));
/*      */   }
/*      */   
/*      */   public int basicIndexOf(EStructuralFeature feature, Object object) {
/*      */     return indexOf(feature, object, false);
/*      */   }
/*      */   
/*      */   protected int indexOf(EStructuralFeature feature, Object object, boolean resolve) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int result = 0;
/*      */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (entry.equals(object))
/*      */             return result; 
/*      */           result++;
/*      */         } 
/*      */       } 
/*      */     } else if (object != null) {
/*      */       int size = delegateSize();
/*      */       int i;
/*      */       for (i = 0; i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (object.equals(entry.getValue()))
/*      */             return result; 
/*      */           result++;
/*      */         } 
/*      */       } 
/*      */       if (resolve) {
/*      */         result = 0;
/*      */         for (i = 0; i < size; i++) {
/*      */           FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */           if (validator.isValid(entry.getEStructuralFeature())) {
/*      */             if (object == resolveProxy((EObject)entry.getValue()))
/*      */               return result; 
/*      */             result++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (entry.getValue() == null)
/*      */             return result; 
/*      */           result++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(EStructuralFeature feature, Object object) {
/*      */     return lastIndexOf(feature, object, isResolveProxies(feature));
/*      */   }
/*      */   
/*      */   public int basicLastIndexOf(EStructuralFeature feature, Object object) {
/*      */     return lastIndexOf(feature, object, false);
/*      */   }
/*      */   
/*      */   protected int lastIndexOf(EStructuralFeature feature, Object object, boolean resolve) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int result = -1;
/*      */     int count = 0;
/*      */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (entry.equals(object))
/*      */             result = count; 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */     } else if (object != null) {
/*      */       int size = delegateSize();
/*      */       int i;
/*      */       for (i = 0; i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (object.equals(entry.getValue()))
/*      */             result = count; 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */       if (resolve) {
/*      */         result = -1;
/*      */         count = 0;
/*      */         for (i = 0; i < size; i++) {
/*      */           FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */           if (validator.isValid(entry.getEStructuralFeature())) {
/*      */             if (object == resolveProxy((EObject)entry.getValue()))
/*      */               result = count; 
/*      */             count++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (entry.getValue() == null)
/*      */             result = count; 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return result;
/*      */   }
/*      */   
/*      */   public Iterator<Object> iterator(EStructuralFeature feature) {
/*      */     return (feature instanceof EReference && ((EReference)feature).isResolveProxies()) ? new ResolvingFeatureEIterator(feature, this) : new FeatureEIterator(feature, this);
/*      */   }
/*      */   
/*      */   public ListIterator<Object> listIterator(EStructuralFeature feature) {
/*      */     return (feature instanceof EReference && ((EReference)feature).isResolveProxies()) ? new ResolvingFeatureEIterator(feature, this) : new FeatureEIterator(feature, this);
/*      */   }
/*      */   
/*      */   public ListIterator<Object> listIterator(EStructuralFeature feature, int index) {
/*      */     ListIterator<Object> result = (feature instanceof EReference && ((EReference)feature).isResolveProxies()) ? new ResolvingFeatureEIterator(feature, this) : new FeatureEIterator(feature, this);
/*      */     for (int i = 0; i < index; i++)
/*      */       result.next(); 
/*      */     return result;
/*      */   }
/*      */   
/*      */   public FeatureMap.ValueListIterator<Object> valueListIterator() {
/*      */     return new ValueListIteratorImpl();
/*      */   }
/*      */   
/*      */   public FeatureMap.ValueListIterator<Object> valueListIterator(int index) {
/*      */     return new ValueListIteratorImpl(index);
/*      */   }
/*      */   
/*      */   protected class ValueListIteratorImpl<E> extends AbstractEList<FeatureMap.Entry>.EListIterator<E> implements FeatureMap.ValueListIterator<E> {
/*      */     public ValueListIteratorImpl() {
/*      */       super((AbstractEList)DelegatingFeatureMap.this);
/*      */     }
/*      */     
/*      */     public ValueListIteratorImpl(int index) {
/*      */       super((AbstractEList)DelegatingFeatureMap.this, index);
/*      */     }
/*      */     
/*      */     public EStructuralFeature feature() {
/*      */       if (this.lastCursor == -1)
/*      */         throw new IllegalStateException(); 
/*      */       return DelegatingFeatureMap.this.getEStructuralFeature(this.lastCursor);
/*      */     }
/*      */     
/*      */     public E next() {
/*      */       return (E)((FeatureMap.Entry)doNext()).getValue();
/*      */     }
/*      */     
/*      */     public E previous() {
/*      */       return (E)((FeatureMap.Entry)doPrevious()).getValue();
/*      */     }
/*      */     
/*      */     public void add(E value) {
/*      */       doAdd(FeatureMapUtil.createEntry(feature(), value));
/*      */     }
/*      */     
/*      */     public void add(EStructuralFeature eStructuralFeature, Object value) {
/*      */       doAdd(FeatureMapUtil.createEntry(eStructuralFeature, value));
/*      */     }
/*      */   }
/*      */   
/*      */   public <T> EList<T> list(EStructuralFeature feature) {
/*      */     return FeatureMapUtil.isFeatureMap(feature) ? new FeatureMapUtil.FeatureFeatureMap(feature, this) : new FeatureMapUtil.FeatureEList<T>(feature, this);
/*      */   }
/*      */   
/*      */   public EStructuralFeature.Setting setting(EStructuralFeature feature) {
/*      */     return isMany(feature) ? (EStructuralFeature.Setting)list(feature) : new FeatureMapUtil.FeatureValue(feature, this);
/*      */   }
/*      */   
/*      */   public List<Object> basicList(EStructuralFeature feature) {
/*      */     return new FeatureMapUtil.FeatureEList.Basic(feature, this);
/*      */   }
/*      */   
/*      */   public Iterator<Object> basicIterator(EStructuralFeature feature) {
/*      */     return new FeatureEIterator(feature, this);
/*      */   }
/*      */   
/*      */   public ListIterator<Object> basicListIterator(EStructuralFeature feature) {
/*      */     return new FeatureEIterator(feature, this);
/*      */   }
/*      */   
/*      */   public ListIterator<Object> basicListIterator(EStructuralFeature feature, int index) {
/*      */     ListIterator<Object> result = new FeatureEIterator(feature, this);
/*      */     for (int i = 0; i < index; i++)
/*      */       result.next(); 
/*      */     return result;
/*      */   }
/*      */   
/*      */   public Object[] toArray(EStructuralFeature feature) {
/*      */     return toArray(feature, isResolveProxies(feature));
/*      */   }
/*      */   
/*      */   public Object[] basicToArray(EStructuralFeature feature) {
/*      */     return toArray(feature, false);
/*      */   }
/*      */   
/*      */   protected Object[] toArray(EStructuralFeature feature, boolean resolve) {
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList();
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           basicEList.add(entry); 
/*      */       } 
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           Object value = entry.getValue();
/*      */           basicEList.add(resolve ? resolveProxy(feature, i, basicEList.size(), value) : value);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return basicEList.toArray();
/*      */   }
/*      */   
/*      */   public <T> T[] toArray(EStructuralFeature feature, Object[] array) {
/*      */     return toArray(feature, (T[])array, isResolveProxies(feature));
/*      */   }
/*      */   
/*      */   public <T> T[] basicToArray(EStructuralFeature feature, Object[] array) {
/*      */     return toArray(feature, (T[])array, false);
/*      */   }
/*      */   
/*      */   protected <T> T[] toArray(EStructuralFeature feature, Object[] array, boolean resolve) {
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList();
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           basicEList.add(entry); 
/*      */       } 
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           Object value = entry.getValue();
/*      */           basicEList.add(resolve ? resolveProxy(feature, i, basicEList.size(), value) : value);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return basicEList.toArray((T[])array);
/*      */   }
/*      */   
/*      */   public void set(EStructuralFeature feature, Object object) {
/*      */     if (isMany(feature)) {
/*      */       EList<?> eList = list(feature);
/*      */       eList.clear();
/*      */       eList.addAll((Collection)object);
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (shouldUnset(feature, object)) {
/*      */             remove(i);
/*      */           } else {
/*      */             doSet(i, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */           } 
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       if (!shouldUnset(feature, object))
/*      */         doAdd(FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean shouldUnset(EStructuralFeature feature, Object value) {
/*      */     if (feature.getUpperBound() != -2 && !feature.isUnsettable()) {
/*      */       Object defaultValue = feature.getDefaultValue();
/*      */       return (defaultValue == null) ? ((value == null)) : defaultValue.equals(value);
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public void add(int index, EStructuralFeature feature, Object object) {
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique() && contains(feature, object))
/*      */         throw new IllegalArgumentException("The 'no duplicates' constraint is violated"); 
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           if (isFeatureMap ? entry.equals(object) : ((object == null) ? (entry.getValue() == null) : object.equals(entry.getValue())))
/*      */             throw new IllegalArgumentException("The 'no duplicates' constraint is violated");  
/*      */       } 
/*      */     } 
/*      */     doAdd(index, isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */   }
/*      */   
/*      */   public boolean add(EStructuralFeature feature, Object object) {
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique() && contains(feature, object))
/*      */         return false; 
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (isFeatureMap ? entry.equals(object) : ((object == null) ? (entry.getValue() == null) : object.equals(entry.getValue())))
/*      */             return false; 
/*      */           doSet(i, isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return doAdd(isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */   }
/*      */   
/*      */   public void add(EStructuralFeature feature, int index, Object object) {
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique() && contains(feature, object))
/*      */         throw new IllegalArgumentException("The 'no duplicates' constraint is violated"); 
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       } 
/*      */     } 
/*      */     doAdd(entryIndex(feature, index), isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object));
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, EStructuralFeature feature, Collection<?> collection) {
/*      */     if (collection.size() == 0)
/*      */       return false; 
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     Collection<FeatureMap.Entry> entryCollection = isFeatureMap ? (Collection)collection : (Collection<FeatureMap.Entry>)new BasicEList(collection.size());
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique()) {
/*      */         for (Object object : collection) {
/*      */           if (!contains(feature, object)) {
/*      */             FeatureMap.Entry entry = createEntry(feature, object);
/*      */             if (!entryCollection.contains(entry))
/*      */               entryCollection.add(entry); 
/*      */           } 
/*      */         } 
/*      */       } else if (!isFeatureMap) {
/*      */         for (Object object : collection) {
/*      */           FeatureMap.Entry entry = createEntry(feature, object);
/*      */           entryCollection.add(entry);
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       if (collection.size() > 1)
/*      */         throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       if (isFeatureMap) {
/*      */         if (contains(feature, collection.iterator().next()))
/*      */           return false; 
/*      */       } else {
/*      */         FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */         for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */           FeatureMap.Entry entry1 = (FeatureMap.Entry)delegateGet(i);
/*      */           if (validator.isValid(entry1.getEStructuralFeature())) {
/*      */             if (collection.contains(entry1.getValue()))
/*      */               return false; 
/*      */             throw new IllegalArgumentException("The multiplicity constraint is violated");
/*      */           } 
/*      */         } 
/*      */         FeatureMap.Entry entry = createEntry(feature, collection.iterator().next());
/*      */         entryCollection.add(entry);
/*      */       } 
/*      */     } 
/*      */     return doAddAll(index, entryCollection);
/*      */   }
/*      */   
/*      */   public boolean addAll(EStructuralFeature feature, Collection<?> collection) {
/*      */     if (collection.size() == 0)
/*      */       return false; 
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     Collection<FeatureMap.Entry> entryCollection = isFeatureMap ? (Collection)collection : (Collection<FeatureMap.Entry>)new BasicEList(collection.size());
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique()) {
/*      */         for (Object object : collection) {
/*      */           if (!contains(feature, object)) {
/*      */             FeatureMap.Entry entry = createEntry(feature, object);
/*      */             if (!entryCollection.contains(entry))
/*      */               entryCollection.add(entry); 
/*      */           } 
/*      */         } 
/*      */       } else if (!isFeatureMap) {
/*      */         for (Object object : collection) {
/*      */           FeatureMap.Entry entry = createEntry(feature, object);
/*      */           entryCollection.add(entry);
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       if (collection.size() > 1)
/*      */         throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (collection.contains(isFeatureMap ? entry : entry.getValue()))
/*      */             return false; 
/*      */           for (Object object : collection)
/*      */             doSet(i, isFeatureMap ? (FeatureMap.Entry)object : createEntry(feature, object)); 
/*      */           return true;
/*      */         } 
/*      */       } 
/*      */       if (!isFeatureMap) {
/*      */         FeatureMap.Entry entry = createEntry(feature, collection.iterator().next());
/*      */         entryCollection.add(entry);
/*      */       } 
/*      */     } 
/*      */     return doAddAll(entryCollection);
/*      */   }
/*      */   
/*      */   public boolean addAll(EStructuralFeature feature, int index, Collection<?> collection) {
/*      */     if (collection.size() == 0)
/*      */       return false; 
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     Collection<FeatureMap.Entry> entryCollection = isFeatureMap ? (Collection)collection : (Collection<FeatureMap.Entry>)new BasicEList(collection.size());
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique()) {
/*      */         for (Object object : collection) {
/*      */           if (!contains(feature, object)) {
/*      */             FeatureMap.Entry entry = createEntry(feature, object);
/*      */             entryCollection.add(entry);
/*      */           } 
/*      */         } 
/*      */       } else if (!isFeatureMap) {
/*      */         for (Object object : collection) {
/*      */           FeatureMap.Entry entry = createEntry(feature, object);
/*      */           entryCollection.add(entry);
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       } 
/*      */       if (collection.size() > 1)
/*      */         throw new IllegalArgumentException("The multiplicity constraint is violated"); 
/*      */       if (!isFeatureMap) {
/*      */         FeatureMap.Entry entry = createEntry(feature, collection.iterator().next());
/*      */         entryCollection.add(entry);
/*      */       } 
/*      */     } 
/*      */     return doAddAll(entryIndex(feature, index), entryCollection);
/*      */   }
/*      */   
/*      */   public void addUnique(EStructuralFeature feature, Object object) {
/*      */     this.modCount = -1;
/*      */     addUnique(createRawEntry(feature, object));
/*      */   }
/*      */   
/*      */   public void addUnique(EStructuralFeature feature, int index, Object object) {
/*      */     this.modCount = -1;
/*      */     addUnique(entryIndex(feature, index), createRawEntry(feature, object));
/*      */   }
/*      */   
/*      */   public void addUnique(FeatureMap.Entry object) {
/*      */     this.modCount++;
/*      */     validate(delegateSize(), object);
/*      */     super.addUnique(object);
/*      */   }
/*      */   
/*      */   public void addUnique(FeatureMap.Entry.Internal entry) {
/*      */     this.modCount = -1;
/*      */     super.addUnique(entry);
/*      */   }
/*      */   
/*      */   public boolean addAllUnique(Collection<? extends FeatureMap.Entry> collection) {
/*      */     this.modCount = -1;
/*      */     return super.addAllUnique(collection);
/*      */   }
/*      */   
/*      */   public boolean addAllUnique(FeatureMap.Entry.Internal[] entries, int start, int end) {
/*      */     this.modCount = -1;
/*      */     return addAllUnique(size(), (Object[])entries, start, end);
/*      */   }
/*      */   
/*      */   public boolean addAllUnique(int index, FeatureMap.Entry.Internal[] entries, int start, int end) {
/*      */     this.modCount = -1;
/*      */     return addAllUnique(index, (Object[])entries, start, end);
/*      */   }
/*      */   
/*      */   public NotificationChain basicAdd(EStructuralFeature feature, Object object, NotificationChain notifications) {
/*      */     NotificationImpl notificationImpl;
/*      */     if (object == null)
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry1 = (FeatureMap.Entry)delegateGet(i);
/*      */         if (entry1.getEStructuralFeature() == feature)
/*      */           return super.basicRemove(entry1, notifications); 
/*      */       }  
/*      */     FeatureMap.Entry entry = FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object);
/*      */     notifications = basicAdd(entry, notifications);
/*      */     if (isNotificationRequired()) {
/*      */       boolean oldIsSet = !isEmpty(feature);
/*      */       NotificationImpl notification = feature.isMany() ? createNotification(3, feature, (Object)null, object, indexOf(feature, object), oldIsSet) : createNotification(1, feature, feature.getDefaultValue(), object, -1, oldIsSet);
/*      */       if (notifications != null) {
/*      */         notifications.add((Notification)notification);
/*      */       } else {
/*      */         notificationImpl = notification;
/*      */       } 
/*      */     } 
/*      */     return (NotificationChain)notificationImpl;
/*      */   }
/*      */   
/*      */   public boolean remove(EStructuralFeature feature, Object object) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (FeatureMapUtil.isFeatureMap(feature)) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           if (entry.equals(object)) {
/*      */             remove(i);
/*      */             return true;
/*      */           }  
/*      */       } 
/*      */     } else if (object != null) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           if (object.equals(entry.getValue())) {
/*      */             remove(i);
/*      */             return true;
/*      */           }  
/*      */       } 
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature()))
/*      */           if (entry.getValue() == null) {
/*      */             remove(i);
/*      */             return true;
/*      */           }  
/*      */       } 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   public Object remove(EStructuralFeature feature, int index) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int count = 0;
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         if (count == index) {
/*      */           remove(i);
/*      */           return FeatureMapUtil.isFeatureMap(feature) ? entry : entry.getValue();
/*      */         } 
/*      */         count++;
/*      */       } 
/*      */     } 
/*      */     throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */   }
/*      */   
/*      */   public boolean removeAll(EStructuralFeature feature, Collection<?> collection) {
/*      */     if (FeatureMapUtil.isFeatureMap(feature))
/*      */       return removeAll(collection); 
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/*      */     for (int i = delegateSize(); --i >= 0; ) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         if (collection.contains(entry.getValue()))
/*      */           basicEList.add(entry);  
/*      */     } 
/*      */     return removeAll((Collection)basicEList);
/*      */   }
/*      */   
/*      */   public NotificationChain basicRemove(EStructuralFeature feature, Object object, NotificationChain notifications) {
/*      */     NotificationChain notificationChain;
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int count = 0;
/*      */     FeatureMap.Entry match = null;
/*      */     if (FeatureMapUtil.isFeatureMap(feature))
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (entry.equals(object)) {
/*      */             match = entry;
/*      */             break;
/*      */           } 
/*      */           count++;
/*      */         } 
/*      */       }  
/*      */     if (object != null) {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (object.equals(entry.getValue())) {
/*      */             match = entry;
/*      */             break;
/*      */           } 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (entry.getValue() == null) {
/*      */             match = entry;
/*      */             break;
/*      */           } 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     if (match != null) {
/*      */       NotificationImpl notificationImpl;
/*      */       if (isNotificationRequired()) {
/*      */         NotificationImpl notification = feature.isMany() ? createNotification(4, feature, object, (Object)null, count, true) : createNotification(feature.isUnsettable() ? 2 : 1, feature, object, feature.getDefaultValue(), -1, true);
/*      */         if (notifications != null) {
/*      */           notifications.add((Notification)notification);
/*      */         } else {
/*      */           notificationImpl = notification;
/*      */         } 
/*      */       } 
/*      */       notificationChain = basicRemove(match, (NotificationChain)notificationImpl);
/*      */     } 
/*      */     return notificationChain;
/*      */   }
/*      */   
/*      */   public boolean retainAll(EStructuralFeature feature, Collection<?> collection) {
/*      */     boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList(collection.size());
/*      */     for (int i = delegateSize(); --i >= 0; ) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         if (!collection.contains(isFeatureMap ? entry : entry.getValue()))
/*      */           basicEList.add(entry);  
/*      */     } 
/*      */     return removeAll((Collection)basicEList);
/*      */   }
/*      */   
/*      */   public void clear(EStructuralFeature feature) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     BasicEList<FeatureMap.Entry> basicEList = new BasicEList();
/*      */     for (int i = delegateSize(); --i >= 0; ) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         basicEList.add(entry); 
/*      */     } 
/*      */     if (!removeAll((Collection)basicEList))
/*      */       dispatchNotification(feature.isMany() ? (Notification)createNotification(6, feature, Collections.EMPTY_LIST, (Object)null, -1, false) : (Notification)createNotification(feature.isUnsettable() ? 2 : 1, feature, (Object)null, (Object)null, -1, false)); 
/*      */   }
/*      */   
/*      */   public void move(EStructuralFeature feature, int index, Object object) {
/*      */     move(feature, index, indexOf(feature, object));
/*      */   }
/*      */   
/*      */   public Object move(EStructuralFeature feature, int targetIndex, int sourceIndex) {
/*      */     if (isMany(feature)) {
/*      */       FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */       Object result = null;
/*      */       int entryTargetIndex = -1;
/*      */       int entrySourceIndex = -1;
/*      */       int count = 0;
/*      */       for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (count == targetIndex)
/*      */             entryTargetIndex = i; 
/*      */           if (count == sourceIndex) {
/*      */             entrySourceIndex = i;
/*      */             result = entry.getValue();
/*      */           } 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */       if (entryTargetIndex == -1)
/*      */         throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + count); 
/*      */       if (entrySourceIndex == -1)
/*      */         throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + count); 
/*      */       super.move(entryTargetIndex, entrySourceIndex);
/*      */       if (isNotificationRequired())
/*      */         dispatchNotification((Notification)createNotification(7, feature, Integer.valueOf(sourceIndex), result, targetIndex, true)); 
/*      */       return result;
/*      */     } 
/*      */     throw new IllegalArgumentException("The feature must be many-valued to support move");
/*      */   }
/*      */   
/*      */   public Object get(EStructuralFeature feature, boolean resolve) {
/*      */     if (isMany(feature))
/*      */       return list(feature); 
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     int count = 0;
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         if (FeatureMapUtil.isFeatureMap(feature))
/*      */           return entry; 
/*      */         Object value = entry.getValue();
/*      */         if (value != null && resolve && isResolveProxies(feature))
/*      */           value = resolveProxy(feature, i, count, value); 
/*      */         return value;
/*      */       } 
/*      */       count++;
/*      */     } 
/*      */     return feature.getDefaultValue();
/*      */   }
/*      */   
/*      */   public Object get(EStructuralFeature feature, int index, boolean resolve) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (isMany(feature)) {
/*      */       int j = 0;
/*      */       for (int k = 0, m = delegateSize(); k < m; k++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(k);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (j == index) {
/*      */             if (FeatureMapUtil.isFeatureMap(feature))
/*      */               return entry; 
/*      */             Object value = entry.getValue();
/*      */             if (value != null && resolve && isResolveProxies(feature))
/*      */               value = resolveProxy(feature, k, j, entry.getValue()); 
/*      */             return value;
/*      */           } 
/*      */           j++;
/*      */         } 
/*      */       } 
/*      */       throw new IndexOutOfBoundsException("index=" + index + ", size=" + j);
/*      */     } 
/*      */     int count = 0;
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         if (FeatureMapUtil.isFeatureMap(feature))
/*      */           return entry; 
/*      */         Object value = entry.getValue();
/*      */         if (value != null && resolve && isResolveProxies(feature))
/*      */           value = resolveProxy(feature, i, count, value); 
/*      */         return value;
/*      */       } 
/*      */       count++;
/*      */     } 
/*      */     return feature.getDefaultValue();
/*      */   }
/*      */   
/*      */   public Object set(EStructuralFeature feature, int index, Object object) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (isMany(feature)) {
/*      */       if (feature.isUnique()) {
/*      */         int currentIndex = indexOf(feature, object);
/*      */         if (currentIndex >= 0 && currentIndex != index)
/*      */           throw new IllegalArgumentException("The 'no duplicates' constraint is violated"); 
/*      */       } 
/*      */       int count = 0;
/*      */       for (int j = 0, k = delegateSize(); j < k; j++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(j);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (count == index)
/*      */             return doSet(j, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object)); 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */       throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */     } 
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         return FeatureMapUtil.isFeatureMap(feature) ? entry : entry.getValue(); 
/*      */     } 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public Object setUnique(EStructuralFeature feature, int index, Object object) {
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     if (isMany(feature)) {
/*      */       int count = 0;
/*      */       for (int j = 0, k = delegateSize(); j < k; j++) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(j);
/*      */         if (validator.isValid(entry.getEStructuralFeature())) {
/*      */           if (count == index)
/*      */             return setUnique(j, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object)); 
/*      */           count++;
/*      */         } 
/*      */       } 
/*      */       throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
/*      */     } 
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature()))
/*      */         return setUnique(i, FeatureMapUtil.isFeatureMap(feature) ? (FeatureMap.Entry)object : createEntry(feature, object)); 
/*      */     } 
/*      */     return feature.getDefaultValue();
/*      */   }
/*      */   
/*      */   public boolean isSet(EStructuralFeature feature) {
/*      */     return !isEmpty(feature);
/*      */   }
/*      */   
/*      */   public void unset(EStructuralFeature feature) {
/*      */     BasicEList<FeatureMap.Entry> basicEList;
/*      */     FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(this.owner.eClass(), feature);
/*      */     List<FeatureMap.Entry> removals = null;
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (validator.isValid(entry.getEStructuralFeature())) {
/*      */         if (removals == null)
/*      */           basicEList = new BasicEList(); 
/*      */         basicEList.add(entry);
/*      */       } 
/*      */     } 
/*      */     if (basicEList != null)
/*      */       removeAll((Collection)basicEList); 
/*      */   }
/*      */   
/*      */   public NotificationChain basicRemove(Object object, NotificationChain notifications) {
/*      */     NotificationChain notificationChain;
/*      */     if (object instanceof FeatureMap.Entry)
/*      */       return super.basicRemove(object, notifications); 
/*      */     FeatureMap.Entry match = null;
/*      */     EStructuralFeature feature = null;
/*      */     for (int i = 0, size = delegateSize(); i < size; i++) {
/*      */       FeatureMap.Entry entry = (FeatureMap.Entry)delegateGet(i);
/*      */       if (object.equals(entry.getValue())) {
/*      */         feature = entry.getEStructuralFeature();
/*      */         if (feature instanceof EReference && ((EReference)feature).isContainment()) {
/*      */           match = entry;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     if (match != null) {
/*      */       NotificationImpl notificationImpl;
/*      */       if (isNotificationRequired()) {
/*      */         NotificationImpl notification = feature.isMany() ? createNotification(4, feature, object, (Object)null, indexOf(feature, object), true) : createNotification(feature.isUnsettable() ? 2 : 1, feature, object, feature.getDefaultValue(), -1, true);
/*      */         if (notifications != null) {
/*      */           notifications.add((Notification)notification);
/*      */         } else {
/*      */           notificationImpl = notification;
/*      */         } 
/*      */       } 
/*      */       notificationChain = basicRemove(match, (NotificationChain)notificationImpl);
/*      */     } 
/*      */     return notificationChain;
/*      */   }
/*      */   
/*      */   public static class FeatureEIterator<E> extends FeatureMapUtil.BasicFeatureEIterator<E> {
/*      */     public FeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap) {
/*      */       super(eStructuralFeature, featureMap);
/*      */     }
/*      */     
/*      */     protected boolean scanNext() {
/*      */       int size = this.featureMap.size();
/*      */       while (this.entryCursor < size) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)this.featureMap.get(this.entryCursor);
/*      */         if (this.validator.isValid(entry.getEStructuralFeature())) {
/*      */           this.preparedResult = extractValue(entry);
/*      */           this.prepared = 2;
/*      */           return true;
/*      */         } 
/*      */         this.entryCursor++;
/*      */       } 
/*      */       this.prepared = 1;
/*      */       this.lastCursor = -1;
/*      */       return false;
/*      */     }
/*      */     
/*      */     protected boolean scanPrevious() {
/*      */       while (--this.entryCursor >= 0) {
/*      */         FeatureMap.Entry entry = (FeatureMap.Entry)this.featureMap.get(this.entryCursor);
/*      */         if (this.validator.isValid(entry.getEStructuralFeature())) {
/*      */           this.preparedResult = extractValue(entry);
/*      */           this.prepared = -2;
/*      */           return true;
/*      */         } 
/*      */       } 
/*      */       this.prepared = -1;
/*      */       this.lastCursor = -1;
/*      */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ResolvingFeatureEIterator<E> extends FeatureEIterator<E> {
/*      */     public ResolvingFeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap) {
/*      */       super(eStructuralFeature, featureMap);
/*      */     }
/*      */     
/*      */     protected boolean resolve() {
/*      */       return true;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar!\org\eclipse\emf\ecor\\util\DelegatingFeatureMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */