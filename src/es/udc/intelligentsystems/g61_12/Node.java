package es.udc.intelligentsystems.g61_12;



import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
    private State state;
    private Node parentNode;
    private Action action;
    public float cost;
    public float f;

    public Node(State state, Node parentNode, Action action){
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
    }

    public Node(State state, Node parentNode, Action action, float cost){
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
        this.cost = cost;
        this.f = cost;
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

    public float getCost() {
        return cost;
    }

    public List<Node> succesors(SearchProblem problem){
        List<Node> s = new ArrayList<>();
        Action[] actions = problem.actions(state);
        if(actions==null){
            return new ArrayList<>();
        }
        s.add(new Node(problem.result(state, actions[0]), this, actions[0]));
        for(int i = 1;i<actions.length;i++){
            s.add(new Node(problem.result(state, actions[i]), this, actions[i]));
        }
        return s;
    }

    @Override
    public int compareTo(Node o) {
        float thisValue = this.getCost();
        float otherValue = o.getCost();
        float v = thisValue - otherValue;
        if(v<0){
            return -1;
        }else if(v>0){
            return 1;
        }else{
            return 0;
        }
    }
}
