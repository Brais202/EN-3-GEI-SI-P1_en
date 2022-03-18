package es.udc.intelligentsystems.g61_12.example;

import es.udc.intelligentsystems.g61_12.SearchStrategy;
import es.udc.intelligentsystems.g61_12.SearchProblem;

public class Main {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new Strategy4();
        System.out.println(buscador.solve(aspiradora));
    }
}
