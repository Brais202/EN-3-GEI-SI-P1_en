package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.VacuumCleanerProblem;

import java.util.Arrays;

public class  MagicSquareProblem extends SearchProblem{
    public static class MagicSquareProblemState extends State{

        int[] a;
        int n;

        public MagicSquareProblemState(int[] a, int n){
            this.a = a;
            this.n = n;
        }

        @Override
        public String toString() {
            StringBuilder s = null;
            for(int i=0;i<n*n;i++){
                assert false;
                s.append(a[i]);
            }
            assert false;
            return s.toString();
        }

        @Override
        public boolean equals(Object o) {
            boolean b = true;
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MagicSquareProblemState that = (MagicSquareProblemState) o;

            for(int i = 0;i<n;i++){
                b = b && (a[i] == that.a[i]);
            }
            return b;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }
    }

    public static class MagicSquareProblemAction extends Action{
        int position;
        int amount;

        public MagicSquareProblemAction(int position, int amount) {
            this.position = position;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return ""+position + amount;
        }

        @Override
        public boolean isApplicable(State st) {
            MagicSquareProblemState state = (MagicSquareProblemState) st;
            return (position<=(state.n * state.n) && amount < (state.n * state.n) && state.a[position] == 0 && Arrays.asList(state.a).contains(amount));
        }

        @Override
        public State applyTo(State st) {
            MagicSquareProblemState state = (MagicSquareProblemState) st;
            int[] b = state.a;
            b[position] = amount;

            return new MagicSquareProblemState(b, state.n);
        }
    }

    public MagicSquareProblem(State initialState) {
        super(initialState);
    }




    @Override
    public boolean isGoal(State state){
        MagicSquareProblemState stat = (MagicSquareProblemState) state;
        int result = (stat.n+((stat.n*stat.n)+1))/2;
        int amount=0;
        int x = 0;
        //Fila
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            for(int j = 0;j<i;j++){
                amount = amount + stat.a[j+stat.n];
            }
            if (amount!=result){
                return false;
            }
            amount = 0;
        }
        //Columna
        for(int j = 0;j<stat.n;j++){
            for(int i = 0;i<stat.n * stat.n;i+=stat.n){
                amount = amount + stat.a[i];
            }
            if (amount!=result){
                return false;
            }
            amount = 0;
        }
        //Diagonal
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            amount = amount + stat.a[i+x];
            x++;
        }
        if (amount!=result){
            return false;
        }
        amount = 0;
        x=stat.n;
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            amount = amount + stat.a[i+x];
            x--;
        }
        return amount == result;
    }

    @Override
    public Action[] actions(State st) {
        Action[] act = new Action[1024];
        int x = 0;
        MagicSquareProblemState state = (MagicSquareProblemState) st;
        MagicSquareProblemAction action = new MagicSquareProblemAction(0, 0);
        for(int i = 0;i<state.n*state.n;i++){
            for(int j = 0;j<state.n*state.n;j++){
                action = new MagicSquareProblemAction(i, j);
                if(action.isApplicable(state)){
                    act[x] = action;
                    x++;
                }
            }
        }
        return act;
    }


}
