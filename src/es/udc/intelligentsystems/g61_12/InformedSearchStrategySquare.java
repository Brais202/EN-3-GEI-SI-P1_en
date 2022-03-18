package es.udc.intelligentsystems.g61_12;

import java.util.*;

public class InformedSearchStrategySquare implements InformedSearchStrategy {
    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {
        if(p.isGoal(p.getInitialState())){
            System.out.println("Goal Node Found!");
            System.out.println(p.getInitialState());
        }

        AbstractQueue<Node> queue = new PriorityQueue<>();
        ArrayList<Node> explored = new ArrayList<>();
        List<Node> succesors = new ArrayList<>();
        Node father=  new Node(p.getInitialState(), null,null, h.evaluate(p.getInitialState()));
        queue.add(father);
        explored.add(father);

        while(!queue.isEmpty()){
            Node current = queue.remove();
            if(p.isGoal(current.getState())) {
                System.out.println("Goal node found\n"+current.getState());
                int numnodes=explored.size();
                System.out.println("Number of nodes explored:"+numnodes);
                return current.getState();
            }
            else{
                succesors = current.succesors(p);
                for(Node n : succesors){
                    n.cost = n.cost +;
                    if(){

                    }
                }
                System.out.println(""+current.getState()+"  "+current.getCost());
            }
            explored.add(current);
        }
        return null;
    }
}
