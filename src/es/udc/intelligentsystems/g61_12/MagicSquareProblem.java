package es.udc.intelligentsystems.g61_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
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

        private boolean check(int a, Stack<Integer> stack, int spaces, int amount){
            for(int i = 0;i<spaces;i++){
                a+=stack.pop();
            }
            if(a<amount){
                return false;
            }
            return true;
        }

        @Override
        public boolean isApplicable(State st) {
            MagicSquareProblemState state = (MagicSquareProblemState) st;
            if(position<(state.n * state.n) && amount <= (state.n * state.n) && state.a[position] == 0 && IntStream.of(state.a).noneMatch(x->x == amount)){
                Stack<Integer> stack = new Stack<>();;
                for(int i = 1;i<=state.n*state.n;i++){
                    stack.add(i);
                }
                int[] fila = new int[state.n];
                for(int i = 0;i< state.n;i++){
                    fila[i]=0;
                }
                int[] columna = new int[state.n];
                for(int i = 0;i< state.n;i++){
                    columna[i]=0;
                }
                int diagonal1 = 0;
                int diagonal2 = 0;
                int result = (state.n*((state.n*state.n)+1))/2;
                int amount=0;
                int x = 0;
                int a = 0;
                int[] t = state.a.clone();
                t[position] = this.amount;
                for(int l : t){
                    if(stack.contains(l)){
                        for(int i = 0;i<stack.size();i++){
                            if(stack.get(i)==l){
                                stack.remove(i);
                            }
                        }
                    }
                }
                //Fila
                for(int i = 0;i<state.n * state.n;i+=state.n){
                    for(int j = 0;j< state.n;j++){
                        if(t[j+i]==0){
                            fila[a]++;
                        }
                        if (amount>=result){
                            return false;
                        }
                        amount = amount + t[j+i];
                    }
                    if(!check(amount, (Stack<Integer>) stack.clone(), fila[a], result)){
                        return false;
                    }
                    a++;
                    if (amount>result){
                        return false;
                    }
                    amount = 0;
                }
                a=0;
                //Columna
                for(int j = 0;j<state.n;j++){
                    for(int i = 0;i<state.n * state.n;i+=state.n){
                        if(t[i+j]==0){
                            columna[a]++;
                        }
                        if (amount>=result){
                            return false;
                        }
                        amount = amount + t[i+j];
                    }
                    if(!check(amount, (Stack<Integer>) stack.clone(), columna[a], result)){
                        return false;
                    }
                    a++;
                    if (amount>result){
                        return false;
                    }
                    amount = 0;
                }
                a=0;
                //Diagonal
                for(int i = 0;i<state.n * state.n;i+=state.n){
                    if(t[i+x]==0){
                        diagonal1++;
                    }
                    a++;
                    if (amount>=result){
                        return false;
                    }
                    amount = amount + t[i+x];
                    x++;
                }
                if(!check(amount, (Stack<Integer>) stack.clone(), diagonal1, result)){
                    return false;
                }
                a=0;
                if (amount>result){
                    return false;
                }
                amount = 0;
                x--;
                for(int i = 0;i<state.n * state.n;i+=state.n){
                    if(t[i+x]==0){
                        diagonal2++;
                    }
                    a++;
                    if (amount>=result){
                        return false;
                    }
                    amount = amount + t[i+x];
                    x--;
                }
                if (amount>result){
                    return false;
                }
                if(!check(amount, (Stack<Integer>) stack.clone(), diagonal2, result)){
                    return false;
                }
                return true;
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
