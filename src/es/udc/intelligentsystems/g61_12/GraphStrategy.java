package es.udc.intelligentsystems.g61_12;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class GraphSearchStrategy implements SearchStrategy {

    public GraphSearchStrategy() {
    }

    public Node[] reconstruct_sol(Node[] nodes){
        Node[] finalNodes = new Node[1024];
        int i = -1, j=0;
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
        List<State> explored = new ArrayList<>();
        Queue<Node> frontier = new LinkedList<>();
        Node init = new Node(p.getInitialState(), null, null);
        frontier.add(init);
        Node[] nodeList = new Node[1024];
        int j=0;
        nodeList[j] = init;

        int i = 1;

        System.out.println((i++) + " - Starting search at " + init.getState());

        while (true){
            if (frontier.isEmpty()) throw new Exception("No solution could be found");
            Node newNode = frontier.remove();
            explored.add(newNode.getState());
            j++;
            nodeList[j] = newNode;
            if(p.isGoal(newNode.getState())){
                break;
            }
            System.out.println((i++) + " - " + newNode.getState() + " is not a goal");
            for(Node n : newNode.succesors(p)){
                if(!frontier.contains(n) && !explored.contains(n.getState())){
                    frontier.add(n);
                }
            }
        }
        return reconstruct_sol(nodeList);
    }
}