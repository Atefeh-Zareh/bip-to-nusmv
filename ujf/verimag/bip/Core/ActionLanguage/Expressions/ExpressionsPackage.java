/*      */ package ujf.verimag.bip.Core.ActionLanguage.Expressions;
/*      */ 
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EEnum;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.impl.ExpressionsPackageImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface ExpressionsPackage
/*      */   extends EPackage
/*      */ {
/*      */   public static final String eNAME = "Expressions";
/*      */   public static final String eNS_URI = "http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore";
/*      */   public static final String eNS_PREFIX = "ujf.verimag.bip.Core.ActionLanguage.Expressions";
/*   64 */   public static final ExpressionsPackage eINSTANCE = ExpressionsPackageImpl.init();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_REFERENCE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_REFERENCE_FEATURE_COUNT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_PARAMETER_SPECIFICATION = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_PARAMETER_SPECIFICATION__TARGET_PARAMETER = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_PARAMETER_SPECIFICATION_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_DATA_PARAMETER_REFERENCE = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_DATA_PARAMETER_REFERENCE__TARGET_PARAMETER = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_DATA_PARAMETER_REFERENCE__PORT_REFERENCE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_DATA_PARAMETER_REFERENCE_FEATURE_COUNT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_PARAMETER_REFERENCE = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_PARAMETER_REFERENCE__TARGET_PARAMETER = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_PARAMETER_REFERENCE_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BOOLEAN_LITERAL = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BOOLEAN_LITERAL__BVALUE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BOOLEAN_LITERAL_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTEGER_LITERAL = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTEGER_LITERAL__IVALUE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTEGER_LITERAL_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REAL_LITERAL = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REAL_LITERAL__RVALUE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REAL_LITERAL_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STRING_LITERAL = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STRING_LITERAL__SVALUE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STRING_LITERAL_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNARY_EXPRESSION = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNARY_EXPRESSION__OPERATOR = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNARY_EXPRESSION__OPERAND = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNARY_EXPRESSION__POSTFIX = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNARY_EXPRESSION_FEATURE_COUNT = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BINARY_EXPRESSION = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BINARY_EXPRESSION__OPERATOR = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BINARY_EXPRESSION__LEFT_OPERAND = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BINARY_EXPRESSION__RIGHT_OPERAND = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BINARY_EXPRESSION_FEATURE_COUNT = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INDEX_LITERAL = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INDEX_LITERAL__ID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INDEX_LITERAL_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FUNCTION_CALL_EXPRESSION = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FUNCTION_CALL_EXPRESSION__FUNCTION_NAME = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FUNCTION_CALL_EXPRESSION__IS_ON_REF = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FUNCTION_CALL_EXPRESSION__ACTUAL_DATA = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FUNCTION_CALL_EXPRESSION__NAVIGATED = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FUNCTION_CALL_EXPRESSION_FEATURE_COUNT = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_NAVIGATION_EXPRESSION = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_NAVIGATION_EXPRESSION__NAVIGATED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DATA_NAVIGATION_EXPRESSION_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FIELD_NAVIGATION_EXPRESSION = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FIELD_NAVIGATION_EXPRESSION__NAVIGATED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FIELD_NAVIGATION_EXPRESSION__IS_ON_REF = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FIELD_NAVIGATION_EXPRESSION__FIELD_NAME = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FIELD_NAVIGATION_EXPRESSION_FEATURE_COUNT = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ARRAY_NAVIGATION_EXPRESSION = 14;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ARRAY_NAVIGATION_EXPRESSION__NAVIGATED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ARRAY_NAVIGATION_EXPRESSION__INDEX = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ARRAY_NAVIGATION_EXPRESSION_FEATURE_COUNT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REQUIRED_DATA_PARAMETER_REFERENCE = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REQUIRED_DATA_PARAMETER_REFERENCE__TARGET_PARAMETER = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REQUIRED_DATA_PARAMETER_REFERENCE__PORT_REFERENCE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REQUIRED_DATA_PARAMETER_REFERENCE_FEATURE_COUNT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STATE_REFERENCE = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STATE_REFERENCE__TARGET_STATE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int STATE_REFERENCE_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTERFACE_VARIABLE_REFERENCE = 17;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTERFACE_VARIABLE_REFERENCE__TARGET_INTERFACE_VARIABLE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INTERFACE_VARIABLE_REFERENCE_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int POINTER_LITERAL = 18;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int POINTER_LITERAL_FEATURE_COUNT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_INTERFACE_VARIABLE_REFERENCE = 19;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_INTERFACE_VARIABLE_REFERENCE__PART_ELEMENT_REFERENCE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_INTERFACE_VARIABLE_REFERENCE__TARGET_INTERFACE_VARIABLE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INNER_INTERFACE_VARIABLE_REFERENCE_FEATURE_COUNT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VARIABLE_REFERENCE = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VARIABLE_REFERENCE__TARGET_VARIABLE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int VARIABLE_REFERENCE_FEATURE_COUNT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNARY_OPERATOR = 21;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int BINARY_OPERATOR = 22;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getDataReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getDataParameterSpecification();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getDataParameterSpecification_TargetParameter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getInnerDataParameterReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getInnerDataParameterReference_PortReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getDataParameterReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getBooleanLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getBooleanLiteral_BValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getIntegerLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getIntegerLiteral_IValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getRealLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getRealLiteral_RValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getStringLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getStringLiteral_SValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getUnaryExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getUnaryExpression_Operator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getUnaryExpression_Operand();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getUnaryExpression_Postfix();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getBinaryExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getBinaryExpression_Operator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getBinaryExpression_LeftOperand();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getBinaryExpression_RightOperand();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getIndexLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getIndexLiteral_Id();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getFunctionCallExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getFunctionCallExpression_FunctionName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getFunctionCallExpression_IsOnRef();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getFunctionCallExpression_ActualData();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getFunctionCallExpression_Navigated();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getFieldNavigationExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getFieldNavigationExpression_IsOnRef();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EAttribute getFieldNavigationExpression_FieldName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getDataNavigationExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getDataNavigationExpression_Navigated();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getArrayNavigationExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getArrayNavigationExpression_Index();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getRequiredDataParameterReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getRequiredDataParameterReference_PortReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getStateReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getStateReference_TargetState();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getInterfaceVariableReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getInterfaceVariableReference_TargetInterfaceVariable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getPointerLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getInnerInterfaceVariableReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getInnerInterfaceVariableReference_PartElementReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getInnerInterfaceVariableReference_TargetInterfaceVariable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EClass getVariableReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EReference getVariableReference_TargetVariable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EEnum getUnaryOperator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EEnum getBinaryOperator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ExpressionsFactory getExpressionsFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface Literals
/*      */   {
/* 1332 */     public static final EClass DATA_REFERENCE = ExpressionsPackage.eINSTANCE.getDataReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1342 */     public static final EClass DATA_PARAMETER_SPECIFICATION = ExpressionsPackage.eINSTANCE.getDataParameterSpecification();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1350 */     public static final EReference DATA_PARAMETER_SPECIFICATION__TARGET_PARAMETER = ExpressionsPackage.eINSTANCE.getDataParameterSpecification_TargetParameter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1360 */     public static final EClass INNER_DATA_PARAMETER_REFERENCE = ExpressionsPackage.eINSTANCE.getInnerDataParameterReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1368 */     public static final EReference INNER_DATA_PARAMETER_REFERENCE__PORT_REFERENCE = ExpressionsPackage.eINSTANCE.getInnerDataParameterReference_PortReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1378 */     public static final EClass DATA_PARAMETER_REFERENCE = ExpressionsPackage.eINSTANCE.getDataParameterReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1388 */     public static final EClass BOOLEAN_LITERAL = ExpressionsPackage.eINSTANCE.getBooleanLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1396 */     public static final EAttribute BOOLEAN_LITERAL__BVALUE = ExpressionsPackage.eINSTANCE.getBooleanLiteral_BValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1406 */     public static final EClass INTEGER_LITERAL = ExpressionsPackage.eINSTANCE.getIntegerLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1414 */     public static final EAttribute INTEGER_LITERAL__IVALUE = ExpressionsPackage.eINSTANCE.getIntegerLiteral_IValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1424 */     public static final EClass REAL_LITERAL = ExpressionsPackage.eINSTANCE.getRealLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1432 */     public static final EAttribute REAL_LITERAL__RVALUE = ExpressionsPackage.eINSTANCE.getRealLiteral_RValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1442 */     public static final EClass STRING_LITERAL = ExpressionsPackage.eINSTANCE.getStringLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1450 */     public static final EAttribute STRING_LITERAL__SVALUE = ExpressionsPackage.eINSTANCE.getStringLiteral_SValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1460 */     public static final EClass UNARY_EXPRESSION = ExpressionsPackage.eINSTANCE.getUnaryExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1468 */     public static final EAttribute UNARY_EXPRESSION__OPERATOR = ExpressionsPackage.eINSTANCE.getUnaryExpression_Operator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1476 */     public static final EReference UNARY_EXPRESSION__OPERAND = ExpressionsPackage.eINSTANCE.getUnaryExpression_Operand();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     public static final EAttribute UNARY_EXPRESSION__POSTFIX = ExpressionsPackage.eINSTANCE.getUnaryExpression_Postfix();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1494 */     public static final EClass BINARY_EXPRESSION = ExpressionsPackage.eINSTANCE.getBinaryExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1502 */     public static final EAttribute BINARY_EXPRESSION__OPERATOR = ExpressionsPackage.eINSTANCE.getBinaryExpression_Operator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1510 */     public static final EReference BINARY_EXPRESSION__LEFT_OPERAND = ExpressionsPackage.eINSTANCE.getBinaryExpression_LeftOperand();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1518 */     public static final EReference BINARY_EXPRESSION__RIGHT_OPERAND = ExpressionsPackage.eINSTANCE.getBinaryExpression_RightOperand();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1528 */     public static final EClass INDEX_LITERAL = ExpressionsPackage.eINSTANCE.getIndexLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1536 */     public static final EAttribute INDEX_LITERAL__ID = ExpressionsPackage.eINSTANCE.getIndexLiteral_Id();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1546 */     public static final EClass FUNCTION_CALL_EXPRESSION = ExpressionsPackage.eINSTANCE.getFunctionCallExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1554 */     public static final EAttribute FUNCTION_CALL_EXPRESSION__FUNCTION_NAME = ExpressionsPackage.eINSTANCE.getFunctionCallExpression_FunctionName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1562 */     public static final EAttribute FUNCTION_CALL_EXPRESSION__IS_ON_REF = ExpressionsPackage.eINSTANCE.getFunctionCallExpression_IsOnRef();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1570 */     public static final EReference FUNCTION_CALL_EXPRESSION__ACTUAL_DATA = ExpressionsPackage.eINSTANCE.getFunctionCallExpression_ActualData();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1578 */     public static final EReference FUNCTION_CALL_EXPRESSION__NAVIGATED = ExpressionsPackage.eINSTANCE.getFunctionCallExpression_Navigated();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1588 */     public static final EClass FIELD_NAVIGATION_EXPRESSION = ExpressionsPackage.eINSTANCE.getFieldNavigationExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1596 */     public static final EAttribute FIELD_NAVIGATION_EXPRESSION__IS_ON_REF = ExpressionsPackage.eINSTANCE.getFieldNavigationExpression_IsOnRef();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1604 */     public static final EAttribute FIELD_NAVIGATION_EXPRESSION__FIELD_NAME = ExpressionsPackage.eINSTANCE.getFieldNavigationExpression_FieldName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1614 */     public static final EClass DATA_NAVIGATION_EXPRESSION = ExpressionsPackage.eINSTANCE.getDataNavigationExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1622 */     public static final EReference DATA_NAVIGATION_EXPRESSION__NAVIGATED = ExpressionsPackage.eINSTANCE.getDataNavigationExpression_Navigated();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1632 */     public static final EClass ARRAY_NAVIGATION_EXPRESSION = ExpressionsPackage.eINSTANCE.getArrayNavigationExpression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1640 */     public static final EReference ARRAY_NAVIGATION_EXPRESSION__INDEX = ExpressionsPackage.eINSTANCE.getArrayNavigationExpression_Index();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1650 */     public static final EClass REQUIRED_DATA_PARAMETER_REFERENCE = ExpressionsPackage.eINSTANCE.getRequiredDataParameterReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1658 */     public static final EReference REQUIRED_DATA_PARAMETER_REFERENCE__PORT_REFERENCE = ExpressionsPackage.eINSTANCE.getRequiredDataParameterReference_PortReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1668 */     public static final EClass STATE_REFERENCE = ExpressionsPackage.eINSTANCE.getStateReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1676 */     public static final EReference STATE_REFERENCE__TARGET_STATE = ExpressionsPackage.eINSTANCE.getStateReference_TargetState();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1686 */     public static final EClass INTERFACE_VARIABLE_REFERENCE = ExpressionsPackage.eINSTANCE.getInterfaceVariableReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1694 */     public static final EReference INTERFACE_VARIABLE_REFERENCE__TARGET_INTERFACE_VARIABLE = ExpressionsPackage.eINSTANCE.getInterfaceVariableReference_TargetInterfaceVariable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1704 */     public static final EClass POINTER_LITERAL = ExpressionsPackage.eINSTANCE.getPointerLiteral();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1714 */     public static final EClass INNER_INTERFACE_VARIABLE_REFERENCE = ExpressionsPackage.eINSTANCE.getInnerInterfaceVariableReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1722 */     public static final EReference INNER_INTERFACE_VARIABLE_REFERENCE__PART_ELEMENT_REFERENCE = ExpressionsPackage.eINSTANCE.getInnerInterfaceVariableReference_PartElementReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1730 */     public static final EReference INNER_INTERFACE_VARIABLE_REFERENCE__TARGET_INTERFACE_VARIABLE = ExpressionsPackage.eINSTANCE.getInnerInterfaceVariableReference_TargetInterfaceVariable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1740 */     public static final EClass VARIABLE_REFERENCE = ExpressionsPackage.eINSTANCE.getVariableReference();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1748 */     public static final EReference VARIABLE_REFERENCE__TARGET_VARIABLE = ExpressionsPackage.eINSTANCE.getVariableReference_TargetVariable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1758 */     public static final EEnum UNARY_OPERATOR = ExpressionsPackage.eINSTANCE.getUnaryOperator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1768 */     public static final EEnum BINARY_OPERATOR = ExpressionsPackage.eINSTANCE.getBinaryOperator();
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\ExpressionsPackage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */