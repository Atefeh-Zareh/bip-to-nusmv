package epfl.risd.bip.nusmv;

import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.AtomType;
import ujf.verimag.bip.Core.Behaviors.DataParameter;
import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.PetriNet;
import ujf.verimag.bip.Core.Behaviors.Port;
import ujf.verimag.bip.Core.Behaviors.PortDefinition;
import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
import ujf.verimag.bip.Core.Behaviors.PortType;
import ujf.verimag.bip.Core.Behaviors.Transition;
import ujf.verimag.bip.Core.Behaviors.Variable;
import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
import ujf.verimag.bip.Core.Interactions.Component;
import ujf.verimag.bip.Core.Interactions.CompoundType;
import ujf.verimag.bip.Core.Interactions.Connector;
import ujf.verimag.bip.Core.Interactions.InnerPortReference;
import ujf.verimag.bip.Core.Interactions.Part;
import ujf.verimag.bip.Core.Interactions.PartElementReference;
import ujf.verimag.bip.Core.Modules.OpaqueElement;
import BIPTransformation.TransformationFunction;

public class Test {
	
	public static void main(String[] args) {
		String testFile = "/home/saabw/bip/BIP.linux.x86/testing/arrays.bip";
		CompoundType ct = TransformationFunction.ParseBIPFile(testFile);

		// Module
		System.out.println("\nThe Module name is " + ct.getModule().getName());
		
		
		// Compound
		System.out.println("\nThe compund type name is " + ct.getName());

		
		// Connectors
		System.out.println("\nThe connector types and names are: ");
		int numOfConnectors = ct.getConnector().size();

		for(int i = 0; i < numOfConnectors; i++)
		{
			Connector cnct = ct.getConnector().get(i);
			System.out.println("Type: " + cnct.getType().getName() + ".\tName: " + cnct.getName());
			
			for(ActualPortParameter app : cnct.getActualPort()) {
				
				InnerPortReference ipr = (InnerPortReference) app;
				
				PartElementReference per = ipr.getTargetInstance();
				
				Part part = per.getTargetPart();
				System.out.print(part.getName() + ".");
				
				Port p = ipr.getTargetPort();			
				System.out.println(p.getName());
			}
			
		}

		
		// Atomic
		System.out.println("\nThe atomic types and names are: ");
		int numOfAtom = ct.getSubcomponent().size();

		for(int i = 0; i < numOfAtom; i++)
		{
			Component comp = ct.getSubcomponent().get(i);
			AtomType at = (AtomType) comp.getType();
			System.out.print("Type: " + at.getName());
			System.out.println(".\tName: " + comp.getName());
		}
		
		
		// Ports
		AtomType FirstLayer = (AtomType) ct.getSubcomponent().get(0).getType();
		AtomType MidLayers = (AtomType) ct.getSubcomponent().get(8).getType();
		AtomType LastLayer = (AtomType) ct.getSubcomponent().get(14).getType();

		System.out.println("\nThe ports of the FirstLayer are: ");
		int numOfPortsFirst = FirstLayer.getPortDefinition().size();
		for(int i = 0; i < numOfPortsFirst; i++)
		{
			PortDefinition port = FirstLayer.getPortDefinition().get(i);
			System.out.println("Type: " + port.getType().getName() + ".    \tName: " + port.getName());
		}

		System.out.println("\nThe ports of the MidLayers are: ");
		int numOfPortsMid = MidLayers.getPortDefinition().size();
		for(int i = 0; i < numOfPortsMid; i++)
		{
			PortDefinition port = MidLayers.getPortDefinition().get(i);
			System.out.println("Type: " + port.getType().getName() + ".    \tName: " + port.getName());
		}

		System.out.println("\nThe ports of the LastLayer are: ");
		int numOfPortsLast = LastLayer.getPortDefinition().size();
		for(int i = 0; i < numOfPortsLast; i++)
		{
			PortDefinition port = LastLayer.getPortDefinition().get(i);
			System.out.println("Type: " + port.getType().getName() + ".    \tName: " + port.getName());
		}

		
		// Port Variables
		PortType arrayPort = FirstLayer.getPort().get(0).getType();
		System.out.println("\nThe variables of the arrayPort port are: ");
		int numOfVarArray = arrayPort.getDataParameter().size();
		for(int i = 0; i < numOfVarArray; i++)
		{
			OpaqueElement oe = (OpaqueElement)(arrayPort.getDataParameter().get(i).getType());
			System.out.println(oe.getBody() + " " + arrayPort.getDataParameter().get(i).getName());
		}
		
		System.out.println(FirstLayer.getPortDefinition().get(0).getExposedVariable().get(0).getName());

		PortType intPort = MidLayers.getPort().get(0).getType();
		System.out.println("\nThe variables of the intPort port are: ");
		int numOfVarInt = intPort.getDataParameter().size();
		for(int i = 0; i < numOfVarInt; i++)
		{
			OpaqueElement oe = (OpaqueElement)(intPort.getDataParameter().get(i).getType());
			System.out.println(oe.getBody() + " " + intPort.getDataParameter().get(i).getName());
		}
		
		System.out.println(MidLayers.getPortDefinition().get(0).getExposedVariable().get(0).getName());

		
		// Data Parameters of Atomic Types
		System.out.println("\nThe data parameters of the FirstLayer are: ");
		int numOfDataParamsFirst = FirstLayer.getDataParameter().size();
		for(int i = 0; i < numOfDataParamsFirst; i++)
		{
			OpaqueElement oe = (OpaqueElement)FirstLayer.getDataParameter().get(i).getType();
			System.out.print(oe.getBody() + " ");
			System.out.println(FirstLayer.getDataParameter().get(i).getName());
		}
		
		System.out.println("\nThe data parameters of the MidLayers are: ");
		int numOfDataParamsMid = MidLayers.getDataParameter().size();
		for(int i = 0; i < numOfDataParamsMid; i++)
		{
			OpaqueElement oe = (OpaqueElement)MidLayers.getDataParameter().get(i).getType();
			System.out.print(oe.getBody() + " ");
			System.out.println(MidLayers.getDataParameter().get(i).getName());
		}
		
		System.out.println("\nThe data parameters of the LastLayer are: ");
		int numOfDataParamsLast = LastLayer.getDataParameter().size();
		for(int i = 0; i < numOfDataParamsLast; i++)
		{
			OpaqueElement oe = (OpaqueElement)LastLayer.getDataParameter().get(i).getType();
			System.out.print(oe.getBody() + " ");
			System.out.println(LastLayer.getDataParameter().get(i).getName());
		}
		
		
		// Data Variables of Atomic Types
		System.out.println("\nThe data variables in the FirstLayer are: ");
		int numOfVarsFirst = FirstLayer.getVariable().size();
		for(int i = 0; i < numOfVarsFirst; i++)
		{
			OpaqueElement oe = (OpaqueElement)FirstLayer.getVariable().get(i).getType();
			System.out.print(oe.getBody() + " ");
			System.out.print(FirstLayer.getVariable().get(i).getName());
			if(FirstLayer.getVariable().get(i).getInitialValue() != null) System.out.print(" = ");
			print_expression(FirstLayer.getVariable().get(i).getInitialValue());
			System.out.println();
		}
		
		System.out.println("\nThe data variables in the MidLayers are: ");
		int numOfVarsMid = MidLayers.getVariable().size();
		for(int i = 0; i < numOfVarsMid; i++)
		{
			OpaqueElement oe = (OpaqueElement)MidLayers.getVariable().get(i).getType();
			System.out.print(oe.getBody() + " ");
			System.out.print(MidLayers.getVariable().get(i).getName());
			if(MidLayers.getVariable().get(i).getInitialValue() != null) System.out.print(" = ");
			print_expression(MidLayers.getVariable().get(i).getInitialValue());
			System.out.println();
		}
		
		System.out.println("\nThe data variables in the LastLayer are: ");
		int numOfVarsLast = LastLayer.getVariable().size();
		for(int i = 0; i < numOfVarsLast; i++)
		{
			OpaqueElement oe = (OpaqueElement)LastLayer.getVariable().get(i).getType();
			System.out.print(oe.getBody() + " ");
			System.out.print(LastLayer.getVariable().get(i).getName());
			if(LastLayer.getVariable().get(i).getInitialValue() != null) System.out.print(" = ");
			print_expression(LastLayer.getVariable().get(i).getInitialValue());
			System.out.println();
		}
		
		
		// Behavior of FirstLayer
		System.out.println("\n--> The transitions in FirstLayer are: ");
		PetriNet pnFirst = (PetriNet) FirstLayer.getBehavior();
		
		System.out.println("initial to " + pnFirst.getInitialState().get(0).getName());
		if(pnFirst.getInitialization() instanceof CompositeAction)
		{
			System.out.println("do {");
			CompositeAction ca = (CompositeAction) pnFirst.getInitialization();
			int numOfActions = ca.getContent().size();
			for(int j = 0; j < numOfActions; j++)
			{
				Action act = ca.getContent().get(j);
				print_action(act);
			}
			System.out.println("}");
		}
		
		int numOfTransitionsFirst = pnFirst.getTransition().size();
		for(int i = 0; i < numOfTransitionsFirst; i++)
		{
			Transition t = pnFirst.getTransition().get(i);
			PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
			System.out.print("\non " + portDefRef.getTarget().getName());
			System.out.println(" from " + t.getOrigin().get(0).getName() + " to " + t.getDestination().get(0).getName());
			
			Expression exp = t.getGuard();
			System.out.print("provided ");
			print_expression(exp);
			System.out.println();
			
			if(t.getAction() != null && t.getAction() instanceof CompositeAction)
			{
				System.out.println("do {");
				CompositeAction ca = (CompositeAction) t.getAction();
				int numOfActions = ca.getContent().size();
				for(int j = 0; j < numOfActions; j++)
				{
					Action act = ca.getContent().get(j);
					print_action(act);
				}
				System.out.println("}");
			}
		}
		
		
		// Behavior of MidLayers
		System.out.println("\n--> The transitions in MidLayers are: ");
		PetriNet pnMid = (PetriNet) MidLayers.getBehavior();

		System.out.println("initial to " + pnMid.getInitialState().get(0).getName());
		if(pnMid.getInitialization() instanceof CompositeAction)
		{
			System.out.println("do {");
			CompositeAction ca = (CompositeAction) pnMid.getInitialization();
			int numOfActions = ca.getContent().size();
			for(int j = 0; j < numOfActions; j++)
			{
				Action act = ca.getContent().get(j);
				print_action(act);
			}
			System.out.println("}");
		}
		
		int numOfTransitionsMid = pnMid.getTransition().size();
		for(int i = 0; i < numOfTransitionsMid; i++)
		{
			Transition t = pnMid.getTransition().get(i);
			PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
			System.out.print("\non " + portDefRef.getTarget().getName());
			System.out.println(" from " + t.getOrigin().get(0).getName() + " to " + t.getDestination().get(0).getName());
			
			Expression exp = t.getGuard();
			System.out.print("provided ");
			print_expression(exp);
			System.out.println();
			
			if(t.getAction() != null && t.getAction() instanceof CompositeAction)
			{
				System.out.println("do {");
				CompositeAction ca = (CompositeAction) t.getAction();
				int numOfActions = ca.getContent().size();
				for(int j = 0; j < numOfActions; j++)
				{
					Action act = ca.getContent().get(j);
					print_action(act);
				}
				System.out.println("}");
			}
		}
		
		
		// Behavior of LastLayer
		System.out.println("\n--> The transitions in LastLayer are: ");
		PetriNet pnLast = (PetriNet) LastLayer.getBehavior();

		System.out.println("initial to " + pnLast.getInitialState().get(0).getName());
		if(pnLast.getInitialization() instanceof CompositeAction)
		{
			System.out.println("do {");
			CompositeAction ca = (CompositeAction) pnLast.getInitialization();
			int numOfActions = ca.getContent().size();
			for(int j = 0; j < numOfActions; j++)
			{
				Action act = ca.getContent().get(j);
				print_action(act);
			}
			System.out.println("}");
		}
		
		int numOfTransitionsLast = pnLast.getTransition().size();
		for(int i = 0; i < numOfTransitionsLast; i++)
		{
			Transition t = pnLast.getTransition().get(i);
			PortDefinitionReference portDefRef = (PortDefinitionReference)t.getTrigger();
			System.out.print("\non " + portDefRef.getTarget().getName());
			System.out.println(" from " + t.getOrigin().get(0).getName() + " to " + t.getDestination().get(0).getName());
			
			Expression exp = t.getGuard();
			System.out.print("provided ");
			print_expression(exp);
			System.out.println();
			
			if(t.getAction() != null && t.getAction() instanceof CompositeAction)
			{
				System.out.println("do {");
				CompositeAction ca = (CompositeAction) t.getAction();
				int numOfActions = ca.getContent().size();
				for(int j = 0; j < numOfActions; j++)
				{
					Action act = ca.getContent().get(j);
					print_action(act);
				}
				System.out.println("}");
			}
		}
		
	}
	
	public static void print_action(Action act) {
		if(act instanceof AssignmentAction)
		{
			AssignmentAction assignAct = (AssignmentAction) act;
			DataReference dataRef = assignAct.getAssignedTarget();
			if(dataRef instanceof VariableReference)
			{
				VariableReference varRef = (VariableReference) dataRef;
				Variable var = varRef.getTargetVariable();
				System.out.print(var.getName());
			}
			else if(dataRef instanceof ArrayNavigationExpression)
			{
				ArrayNavigationExpression arrayNavExp = (ArrayNavigationExpression) dataRef;
				Expression index = arrayNavExp.getIndex();
				VariableReference navigated = (VariableReference) arrayNavExp.getNavigated();
				Variable arrayName = navigated.getTargetVariable();
				System.out.print(arrayName.getName() + "[");
				print_expression(index);
				System.out.print("]");
			}
			else
			{
				System.out.println("ERROR: " + dataRef.toString());
			}
			
			System.out.print(" = ");
			
			Expression exp = assignAct.getAssignedValue();
			print_expression(exp);
			System.out.println(";");
		}
		else if(act instanceof FunctionCallExpression)
		{
			FunctionCallExpression funCallExp = (FunctionCallExpression) act;
			System.out.print(funCallExp.getFunctionName() + "(");
			
			int numOfParams = funCallExp.getActualData().size();
			for(int i = 0; i < numOfParams; i++)
			{
				Expression param = funCallExp.getActualData().get(i);
				print_expression(param);
				if(i != numOfParams - 1) System.out.print(", ");
			}
			System.out.println(");");
		}
		else
		{
			System.out.print("ERROR: " + act.toString());
		}
	}
	
	public static void print_operator(String operator) {
		if(operator.equals("addition")) System.out.print("+");
		else if(operator.equals("equality")) System.out.print("==");
		else if(operator.equals("less_than")) System.out.print("<");
		else if(operator.equals("logical_not")) System.out.print("!");
		else if(operator.equals("logical_and")) System.out.print("&&");
		else if(operator.equals("modulus")) System.out.print("%");
		else if(operator.equals("inequality")) System.out.print("!=");
		else if(operator.equals("negative")) System.out.print("-");
		else if(operator.equals("increment")) System.out.print("++");
		else System.out.print("ERROR: " + operator);
	}
	
	public static void print_expression(Expression exp) {
		if(exp == null) return;
		else if(exp instanceof BinaryExpression)
		{
			BinaryExpression binaryExp = (BinaryExpression) exp;
			Expression leftExp = binaryExp.getLeftOperand();
			Expression rightExp = binaryExp.getRightOperand();
			BinaryOperator operator = binaryExp.getOperator();
			System.out.print("(");
			print_expression(leftExp);
			System.out.print(") ");
			print_operator(operator.toString());
			System.out.print(" (");
			print_expression(rightExp);
			System.out.print(")");
		}
		else if(exp instanceof UnaryExpression)
		{
			UnaryExpression unaryExp = (UnaryExpression) exp;
			Expression operand = unaryExp.getOperand();
			UnaryOperator operator = unaryExp.getOperator();
			print_operator(operator.toString());
			System.out.print(" (");
			print_expression(operand);
			System.out.print(")");
		}
		else if(exp instanceof VariableReference)
		{
			VariableReference varRef = (VariableReference) exp;
			Variable var = varRef.getTargetVariable();
			System.out.print(var.getName());
		}
		else if(exp instanceof DataParameterReference)
		{
			DataParameterReference dataParamRef = (DataParameterReference) exp;
			DataParameter dataParam = dataParamRef.getTargetParameter();
			System.out.print(dataParam.getName());
		}
		else if(exp instanceof IntegerLiteral)
		{
			IntegerLiteral intLit = (IntegerLiteral) exp;
			System.out.print(intLit.getIValue());
		}
		else if(exp instanceof BooleanLiteral)
		{
			BooleanLiteral boolLit = (BooleanLiteral) exp;
			System.out.print(boolLit.isBValue());
		}
		else if(exp instanceof ArrayNavigationExpression)
		{
			ArrayNavigationExpression arrayNavExp = (ArrayNavigationExpression) exp;
			Expression index = arrayNavExp.getIndex();
			VariableReference navigated = (VariableReference) arrayNavExp.getNavigated();
			Variable arrayName = navigated.getTargetVariable();
			System.out.print(arrayName.getName() + "[");
			print_expression(index);
			System.out.print("]");
		}
		else if(exp instanceof FunctionCallExpression)
		{
			FunctionCallExpression funCallExp = (FunctionCallExpression) exp;
			System.out.print(funCallExp.getFunctionName() + "(");
			
			int numOfParams = funCallExp.getActualData().size();
			for(int i = 0; i < numOfParams; i++)
			{
				Expression param = funCallExp.getActualData().get(i);
				print_expression(param);
				if(i != numOfParams - 1) System.out.print(", ");
			}
			System.out.print(")");
		}
		else
		{
			System.out.println("ERROR: " + exp.toString());
		}
	}

}
