package es.udc.intelligentsystems;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.MagicSquareProblem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
    public class BreadthFirstSearch implements  SearchStrategy {
        Node startNode;
        Node goalNode;

        public BreadthFirstSearch(Node start, Node goalNode){
            this.startNode = start;
            this.goalNode = goalNode;
        }

        public boolean compute(SearchProblem p){

            if(p.isGoal(startNode.getState())){
                System.out.println("Goal Node Found!");
                System.out.println(startNode);
            }

            Queue<Node> queue = new LinkedList<>();
            ArrayList<Node> explored = new ArrayList<>();
            queue.add(this.startNode);
            explored.add(startNode);

            while(!queue.isEmpty()){
                Node current = queue.remove();
                if(current.equals(this.goalNode)) {
                    System.out.println(explored);
                    return true;
                }
                else{
                    if(current.succesors(p).isEmpty())
                        return false;
                    else
                        queue.addAll(current.succesors(p));
                }
                explored.add(current);
            }

            return false;

        }
        @Override
        public Node[] solve (SearchProblem p) throws Exception {
            return new Node[0];

        }
    }
    public class depth_first implements  SearchStrategy {

        @Override
        public Node[] solve (SearchProblem p) throws Exception {
            return new Node[0];

        }
    }
}
