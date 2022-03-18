package es.udc.intelligentsystems.g61_12;

import es.udc.intelligentsystems.g61_12.example.Strategy4;
import es.udc.intelligentsystems.g61_12.example.VacuumCleanerProblem;

public class MainEx1 {
    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new Strategy4();
        SearchStrategy buscador2 = new GraphSearchStrategy();
        for(Node node : buscador.solve(aspiradora)){
            if(node == null){
                break;
            }
            System.out.println(node.getState());
        }
        System.out.println("\n");
        for(Node node : buscador2.solve(aspiradora)){
            if(node == null){
                break;
            }
            System.out.println(node.getState());
        }
    }
}
