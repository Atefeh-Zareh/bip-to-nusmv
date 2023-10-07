package ujf.verimag.bip.graphviz;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ujf.verimag.bip.Core.Behaviors.Action;
import ujf.verimag.bip.Core.Behaviors.AtomType;
import ujf.verimag.bip.Core.Behaviors.Behavior;
import ujf.verimag.bip.Core.Behaviors.BipType;
import ujf.verimag.bip.Core.Behaviors.Expression;
import ujf.verimag.bip.Core.Behaviors.PetriNet;
import ujf.verimag.bip.Core.Behaviors.State;
import ujf.verimag.bip.Core.Behaviors.Transition;
import ujf.verimag.bip.Core.Modules.Module;
import ujf.verimag.bip.Core.PortExpressions.PortExpression;
import ujf.verimag.bip.bip2src.Reverse;
import ujf.verimag.bip.metamodelAPI.BipUtil;

public class GraphvizAutomaton {
  public static Map<String, String> fromBipModule(Module paramModule) throws UnsupportedEncodingException {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (BipType bipType : paramModule.getBipType()) {
      if (bipType instanceof AtomType) {
        AtomType atomType = (AtomType)bipType;
        Behavior behavior = atomType.getBehavior();
        assert behavior instanceof PetriNet;
        PetriNet petriNet = (PetriNet)behavior;
        String str = genGraphviz(petriNet);
        hashMap.put(atomType.getName(), str);
      } 
    } 
    return (Map)hashMap;
  }
  
  private static String localDecompile(Action paramAction) throws UnsupportedEncodingException {
    if (paramAction == null)
      return ""; 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    Reverse reverse = new Reverse(printStream);
    reverse.decompile(paramAction);
    return byteArrayOutputStream.toString("UTF-8");
  }
  
  private static String localDecompile(Expression paramExpression) throws UnsupportedEncodingException {
    if (paramExpression == null)
      return ""; 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    Reverse reverse = new Reverse(printStream);
    reverse.decompile((Action)paramExpression);
    return byteArrayOutputStream.toString("UTF-8");
  }
  
  private static String localDecompile(PortExpression paramPortExpression) throws UnsupportedEncodingException {
    if (paramPortExpression == null)
      return ""; 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);
    Reverse reverse = new Reverse(printStream);
    reverse.decompile(paramPortExpression, true);
    return byteArrayOutputStream.toString("UTF-8");
  }
  
  public static String genGraphviz(PetriNet paramPetriNet) throws UnsupportedEncodingException {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("digraph finite_state_machine {\n");
    stringBuffer.append("rankdir=LR;\n");
    stringBuffer.append("node [shape = circle];\n");
    ArrayList<State> arrayList = new ArrayList();
    ArrayDeque<State> arrayDeque = new ArrayDeque();
    arrayDeque.addAll((Collection)paramPetriNet.getInitialState());
    while (true) {
      State state = arrayDeque.pop();
      arrayList.add(state);
      List list = BipUtil.getTransitionFrom(paramPetriNet, state);
      for (Transition transition : list) {
        String str1 = localDecompile(transition.getTrigger());
        String str2 = localDecompile(transition.getAction());
        String str3 = localDecompile(transition.getGuard());
        String str4 = "";
        str4 = str4 + "p: " + str1 + "\\n";
        if (!str2.equals(""))
          str4 = str4 + "f: " + str2 + "\\n"; 
        if (!str3.equals(""))
          str4 = str4 + "g: " + str3; 
        str4 = str4.replaceAll("\n", "\\\\n").replaceAll("\"", "\\\\\"");
        for (State state1 : transition.getDestination()) {
          stringBuffer.append(state.getName() + " -> " + state1.getName() + "[label=\"" + str4 + "\"];\n");
          if (!arrayList.contains(state1))
            arrayDeque.push(state1); 
        } 
      } 
      if (arrayDeque.isEmpty()) {
        stringBuffer.append("\n}\n");
        return stringBuffer.toString();
      } 
    } 
  }
}


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\graphviz\GraphvizAutomaton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */