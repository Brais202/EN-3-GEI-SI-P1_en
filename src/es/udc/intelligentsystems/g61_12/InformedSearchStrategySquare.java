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
        father.cost = 0;
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
                    n.cost = current.cost + 1;
                    n.f = n.cost + h.evaluate(n.getState());
                    if(!explored.contains(n)){
                        if(!queue.contains(n)){
                            queue.add(n);
                            continue;
                        }
                        for(Node n2 : queue){
                            if(n.equals(n2)){
                                if(n.f<n2.cost){
                                    queue.remove(n);
                                    queue.add(n);
                                }
                                break;
                            }
                        }
                    }
                }
                System.out.println(""+current.getState()+"  "+current.f);
            }
            explored.add(current);
        }
        return null;
    }
}
