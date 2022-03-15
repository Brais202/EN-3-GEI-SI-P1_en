package es.udc.intelligentsystems;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Algorithm {
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
                    System.out.println(explored + "\n Found!");
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
                    if(current.succesors(p).isEmpty())
                        return new Node[0];
                    else
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
            }
            Stack<Node> nodeStack = new Stack<>();
            ArrayList<Node> visitedNodes = new ArrayList<>();

            Node father=  new Node(p.getInitialState(), null,null);
            nodeStack.add(father);

            while(!nodeStack.isEmpty()){
                Node current = nodeStack.pop();

                if(p.isGoal(current.getState())){
                    System.out.print(visitedNodes);
                    System.out.println("Goal node found");
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
                    nodeStack.addAll(current.succesors(p));
                }
            }

            return new Node[0];
        }

    }

    }

