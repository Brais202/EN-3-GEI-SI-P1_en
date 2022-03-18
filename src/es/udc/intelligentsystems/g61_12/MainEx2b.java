package es.udc.intelligentsystems.g61_12;

public class MainEx2b {
    public static void main(String[] args) throws Exception {
        int n=3;
        int []a= {2,0,0,0,0,0,0,0,0};

        MagicSquareProblem.MagicSquareProblemState initialState = new MagicSquareProblem.MagicSquareProblemState(a,n);
        MagicSquareHeuristic mH=new MagicSquareHeuristic();
        InformedSearchStrategy MagicSquare = new InformedSearchStrategySquare();
        SearchProblem squareProblem = new MagicSquareProblem(initialState);
        MagicSquare.solve(squareProblem, mH);
    }
}
