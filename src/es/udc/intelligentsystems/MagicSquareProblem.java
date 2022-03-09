package es.udc.intelligentsystems;

public abstract class  MagicSquareProblem extends SearchProblem{
    int[] a = {1,2,3,4,5,6,7,8,9};
    static int[] b;

    public MagicSquareProblem(State initialState) {
        super(initialState);
    }

    void printear(int[] a){
        System.out.println(a[0] +" "+ a[1] +" "+ a[2]);
        System.out.println(a[3] +" "+ a[4] +" "+ a[5]);
        System.out.println(a[6] +" "+ a[7] +" "+ a[8]);
    }

    static boolean judge(int a[]){
        //Fila
        int temp1 = a[0] + a[1] + a[2];
        int temp2 = a[3] + a[4] + a[5];
        int temp3 = a[6] + a[7] + a[8];
        if(temp1!=temp2||temp1!=temp3||temp2!=temp3) return false;

        //Columna
        temp1 = a[0] + a[3] + a[6];
        temp2 = a[1] + a[4] + a[7];
        temp3 = a[2] + a[5] + a[8];
        if(temp1!=temp2||temp1!=temp3||temp2!=temp3) return false;

        //diagonal
        temp1 = a[0] + a[4] + a[8];
        temp2 = a[2] + a[4] + a[6];
        if(temp1!=temp2) return false;
        return true;
    }


    public abstract static class State {
        /* Any State class must override these methods to be correctly shown and allow comparisons. */

        @Override
        public abstract String toString();

        @Override
        public abstract boolean equals(Object obj);

        @Override
        public abstract int hashCode();

    }

    public abstract static class Action {
        @Override
        public abstract String toString();

        /**
         * Checks whether a state meets the preconditions of the action
         * @param st State being checked
         * @return True if "st" meets the preconditions and false otherwise
         */
        public abstract boolean isApplicable(State st);

        /**
         * Returns the resulting state of applying the action to the "st" state
         * @param st State on which the action is applied
         * @return Resulting state
         */
        public abstract State applyTo(State st);

        /**
         * Return the cost of applying the action which, by default, is 1.
         * @return Cost of applying the action
         */
        public float getCost(){ return 1; }
    }
}
