package es.udc.intelligentsystems.gA_61;
import java.util.*;

public class Algorithm {
    public static Node[] reconstruct_sol(Node[] nodes){
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
    public static class BreadthFirstSearch implements  SearchStrategy {

        public Node[] solve(SearchProblem p){

            if(p.isGoal(p.getInitialState())){
                System.out.println("Goal Node Found!");
                System.out.println(p.getInitialState());
            }

            Queue<Node> queue = new LinkedList<>();
            ArrayList<Node> explored = new ArrayList<>();
            Node father=  new Node(p.getInitialState(), null,null);
            queue.add(father);
            explored.add(father);

            while(!queue.isEmpty()){
                Node current = queue.remove();
                if(p.isGoal(current.getState())) {
                    System.out.println("Goal node found\n"+current.getState());
                    int numnodes=explored.size();
                    System.out.println("Number of nodes explored:"+numnodes);
                    Node[] arrayNode = new Node[explored.size()]; int j=0;
                    for(Node n :explored){
                        arrayNode[j]= n;
                        j++;
                    }
                    return arrayNode;
                }
                else{
                    if(!current.succesors(p).isEmpty())
                        queue.addAll(current.succesors(p));
                }
                explored.add(current);
            }

            return new Node[0];

        }

    }

    public static  class  DepthFirstSearch implements  SearchStrategy {

        @Override
        public Node[] solve(SearchProblem p){
            if(p.isGoal(p.getInitialState())){
                System.out.println("Goal Node Found at 0 depth");
                System.out.println(p.getInitialState());
                return new Node[]{new Node(p.getInitialState(), null,null)};
            }
            Stack<Node> nodeStack = new Stack<>();
            ArrayList<Node> visitedNodes = new ArrayList<>();
            List<Node> succesors = new ArrayList<>();

            Node father=  new Node(p.getInitialState(), null,null);
            nodeStack.add(father);

            while(!nodeStack.isEmpty()){
                Node current = nodeStack.pop();

                if(p.isGoal(current.getState())){
                    System.out.println("Goal node found");
                    System.out.println(""+current.getState());
                    int numnodes=visitedNodes.size();
                    System.out.println("Number of nodes explored:"+numnodes);
                    Node[] arrayNode = new Node[visitedNodes.size()]; int j=0;
                    for(Node n :visitedNodes){
                        arrayNode[j]= n;
                        j++;
                    }
                    return arrayNode;
                }
                else {
                    visitedNodes.add(current);
                    succesors = current.succesors(p);
                    if(!succesors.isEmpty()){
                        for(Node n : succesors){
                            if(!visitedNodes.contains(n) && !nodeStack.contains(n)){
                                nodeStack.add(n);
                            }
                        }
                    }
                }
            }

            return new Node[0];
        }

    }

    }

