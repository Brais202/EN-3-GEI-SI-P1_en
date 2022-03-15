package es.udc.intelligentsystems.example;
import es.udc.intelligentsystems.Algorithm;
import es.udc.intelligentsystems.MagicSquareProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;

import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws Exception {
        int n=6;
        int []a= new int[n];

       MagicSquareProblem.MagicSquareProblemState initialState = new MagicSquareProblem.MagicSquareProblemState(a,n);
        SearchProblem MagicSquare = new MagicSquareProblem(initialState);

        SearchStrategy buscador = new Algorithm.DepthFirstSearch();
        System.out.println(Arrays.toString(buscador.solve(MagicSquare)));
    }

}
