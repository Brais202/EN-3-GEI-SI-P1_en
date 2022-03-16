package es.udc.intelligentsystems.gA_61.example;

import es.udc.intelligentsystems.gA_61.SearchStrategy;
import es.udc.intelligentsystems.gA_61.SearchProblem;

public class Main {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new Strategy4();
        System.out.println(buscador.solve(aspiradora));
    }
}
