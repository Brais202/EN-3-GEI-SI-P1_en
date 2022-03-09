package es.udc.intelligentsystems;



import java.util.ArrayList;
import java.util.List;

public class Node{
    private MagicSquareProblem.State state;
    private Node parentNode;
    private MagicSquareProblem.Action action;

    public Node(MagicSquareProblem.State state, Node parentNode, MagicSquareProblem.Action action) {
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
    }

    public MagicSquareProblem.State getState() {
        return state;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public MagicSquareProblem.Action getAction() {
        return action;
    }

    public List<Node> succesors(SearchProblem problem){
        List<Node> s = new ArrayList<>();
        MagicSquareProblem.Action[] actions = problem.actions(state);
        s.add(new Node(problem.result(state, actions[0]), this, actions[0]));
        for(int i = 1;i<actions.length;i++){
            s.add(new Node(problem.result(state, actions[i]), this, actions[i]));
        }
        return s;
    }

}
