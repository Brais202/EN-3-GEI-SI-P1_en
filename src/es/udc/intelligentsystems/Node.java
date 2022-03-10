package es.udc.intelligentsystems;



import java.util.ArrayList;
import java.util.List;

public class Node{
    private State state;
    private Node parentNode;
    private Action action;

    public Node(State state, Node parentNode, Action action) {
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
    }

    public State getState() {
        return state;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public Action getAction() {
        return action;
    }

    public List<Node> succesors(SearchProblem problem){
        List<Node> s = new ArrayList<>();
        Action[] actions = problem.actions(state);
        s.add(new Node(problem.result(state, actions[0]), this, actions[0]));
        for(int i = 1;i<actions.length;i++){
            s.add(new Node(problem.result(state, actions[i]), this, actions[i]));
        }
        return s;
    }

}
