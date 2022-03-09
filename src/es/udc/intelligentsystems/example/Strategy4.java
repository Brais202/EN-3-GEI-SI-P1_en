package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.List;

public class Strategy4 implements SearchStrategy {

    public Strategy4() {
    }

    public Node[] reconstruct_sol(Node[] nodes){
        Node[] finalNodes = new Node[1024];
        int i = -1, j=0;
        int a = finalNodes.length;
        for(Node n : nodes){
            if(n == null){
                break;
            }
            i++;
        }
        Node nd = nodes[i];
        while(nd.getParentNode() != null){
            finalNodes[j] = nd;
            nd = nd.getParentNode();
            j++;
        }
        finalNodes[j] = nd;
        return finalNodes;
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception{
        List<MagicSquareProblem.State> explored = new ArrayList<>();
        MagicSquareProblem.State currentState = p.getInitialState();
        Node init = new Node(currentState, null, null);
        Node[] nodeList = new Node[1024];
        int j=0;
        nodeList[j] = init;
        explored.add(currentState);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (!p.isGoal(currentState)){
            System.out.println((i++) + " - " + currentState + " is not a goal");
            MagicSquareProblem.Action[] availableActions = p.actions(currentState);
            boolean modified = false;
            for (MagicSquareProblem.Action acc: availableActions) {
                MagicSquareProblem.State sc = p.result(currentState, acc);
                System.out.println((i++) + " - RESULT(" + currentState + ","+ acc + ")=" + sc);
                if (!explored.contains(sc)) {
                    Node newNode = new Node(sc,init,acc);
                    currentState = sc;
                    System.out.println((i++) + " - " + sc + " NOT explored");
                    explored.add(currentState);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentState);
                    j++;
                    nodeList[j] = newNode;
                    init = newNode;
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentState);
        return reconstruct_sol(nodeList);
    }
}