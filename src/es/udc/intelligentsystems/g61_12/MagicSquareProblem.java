package es.udc.intelligentsystems.g61_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
            String s = "";
            for(int i=0;i<n*n;i++){
                assert false;
                s = s + a[i];
            }
            return s;
        }

        @Override
        public boolean equals(Object o) {
            boolean b = true;
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MagicSquareProblemState that = (MagicSquareProblemState) o;

            for(int i = 0;i<n*n;i++){
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
            if(position<(state.n * state.n) && amount <= (state.n * state.n) && state.a[position] == 0 && IntStream.of(state.a).noneMatch(x->x == amount)){
                int result = (state.n*((state.n*state.n)+1))/2;
                int amount=0;
                int x = 0;
                int[] t = state.a.clone();
                t[position] = this.amount;
                //Fila
                for(int i = 0;i<state.n * state.n;i+=state.n){
                    for(int j = 0;j< state.n;j++){
                        if (amount>=result){
                            return false;
                        }
                        amount = amount + t[j+i];
                    }
                    if (amount>result){
                        return false;
                    }
                    amount = 0;
                }
                //Columna
                for(int j = 0;j<state.n;j++){
                    for(int i = 0;i<state.n * state.n;i+=state.n){
                        if (amount>=result){
                            return false;
                        }
                        amount = amount + t[i+j];
                    }
                    if (amount>result){
                        return false;
                    }
                    amount = 0;
                }
                //Diagonal
                for(int i = 0;i<state.n * state.n;i+=state.n){
                    if (amount>=result){
                        return false;
                    }
                    amount = amount + t[i+x];
                    x++;
                }
                if (amount>result){
                    return false;
                }
                amount = 0;
                x--;
                for(int i = 0;i<state.n * state.n;i+=state.n){
                    if (amount>=result){
                        return false;
                    }
                    amount = amount + t[i+x];
                    x--;
                }
                return amount <= result;
            }
            return false;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareProblemState state = (MagicSquareProblemState) st;
            int[] b = state.a.clone();
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
        int result = (stat.n*((stat.n*stat.n)+1))/2;
        int amount=0;
        int x = 0;
        //Fila
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            for(int j = 0;j<stat.n;j++){
                amount = amount + stat.a[j+i];
            }
            if (amount!=result){
                return false;
            }
            amount = 0;
        }
        //Columna
        for(int j = 0;j<stat.n;j++){
            for(int i = 0;i<stat.n * stat.n;i+=stat.n){
                amount = amount + stat.a[i+j];
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
        x--;
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            amount = amount + stat.a[i+x];
            x--;
        }
        return amount == result;
    }

    @Override
    public Action[] actions(State st) {
        List<Action> acts = new ArrayList<>();
        int x = 0;
        MagicSquareProblemState state = (MagicSquareProblemState) st;
        MagicSquareProblemAction action;
        for(int i = 0;i<state.n*state.n;i++){
            for(int j = 1;j<=state.n*state.n;j++){
                action = new MagicSquareProblemAction(i, j);
                if(action.isApplicable(state)){
                    acts.add(action);
                    x++;
                }
            }
        }
        if(acts.isEmpty()){
            return null;
        }
        Action[] act = new Action[acts.size()];
        act = acts.toArray(act);
        return act;
    }


}
