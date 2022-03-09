package es.udc.intelligentsystems;


public abstract class SearchProblem {
    private MagicSquareProblem.State initialState;
    public MagicSquareProblem.State getInitialState() {
        return initialState;
    }

    public SearchProblem(MagicSquareProblem.State initialState) {
        this.initialState = initialState;
    }

    /**
     * Indicates whether "st" is a solution to the problem
     * @param st State to check
     * @return True if the state is a goal or false otherwise
     */
    public abstract boolean isGoal(MagicSquareProblem.State st);

    /**
     * Returns a list of available actions for a given state
     * @param st State to which the actions can be applied
     * @return Lista de acciones aplicables
     */
    public abstract MagicSquareProblem.Action[] actions(MagicSquareProblem.State st);

    /**
     * Returns the result of applying a given action to a given state
     * @param st State to which the action will be applied
     * @param act Action to use
     * @return Resulting state
     */
    public MagicSquareProblem.State result(MagicSquareProblem.State st, MagicSquareProblem.Action act){
        return act.applyTo(st);
    }
}