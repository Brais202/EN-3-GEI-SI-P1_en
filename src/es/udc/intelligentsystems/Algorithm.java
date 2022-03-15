package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Algorithm {
    public static class BreadthFirstSearch implements  SearchStrategy {
        Node startNode;


        public BreadthFirstSearch(Node start){
            this.startNode = start;

        }

        public Node[] solve(SearchProblem p){

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
                if(p.isGoal(current.getState())) {
                    System.out.println(explored);
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

    public class DepthFirstSearch implements  SearchStrategy {
        Node startNode;
        public DepthFirstSearch(Node start){
            this.startNode = start;

        }
        @Override
        public Node[] solve(SearchProblem p){
            if(p.isGoal(startNode.getState())){
                System.out.println("Goal Node Found at 0 depth");
                System.out.println(startNode);
            }
            Stack<Node> nodeStack = new Stack<>();
            ArrayList<Node> visitedNodes = new ArrayList<>();

            nodeStack.add(startNode);

            while(!nodeStack.isEmpty()){
                Node current = nodeStack.pop();

                if(p.isGoal(current.getState())){
                    System.out.print(visitedNodes);
                    System.out.println("Goal node found");
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

