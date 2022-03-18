package es.udc.intelligentsystems.g61_12;

public class MainEx2a {
    public static void main(String[] args) throws Exception {
        int n=3;
        int []a= {4,9,2,3,5,0,0,1,0};

        MagicSquareProblem.MagicSquareProblemState initialState = new MagicSquareProblem.MagicSquareProblemState(a,n);
        SearchProblem MagicSquare = new MagicSquareProblem(initialState);

        SearchStrategy buscador = new Algorithm.DepthFirstSearch();
        for(Node node : Algorithm.reconstruct_sol(buscador.solve(MagicSquare))){
            if(node == null){
                break;
            }
            System.out.println(node.getState());
        }
        buscador =new Algorithm.BreadthFirstSearch();
        System.out.println("\n");
        for(Node node : Algorithm.reconstruct_sol(buscador.solve(MagicSquare))){
            if(node == null){
                break;
            }
            System.out.println(node.getState());
        }
    }

}
