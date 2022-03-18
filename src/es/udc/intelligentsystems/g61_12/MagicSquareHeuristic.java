package es.udc.intelligentsystems.g61_12;

public class MagicSquareHeuristic extends Heuristic{
    public float evaluate(State e){
        MagicSquareProblem.MagicSquareProblemState stat = (MagicSquareProblem.MagicSquareProblemState) e;
        int result = (stat.n*((stat.n*stat.n)+1))/2;
        float h = 0;
        int amount=0;
        int x = 0;
        //Fila
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            for(int j = 0;j<stat.n;j++){
                amount = amount + stat.a[j+i];
            }
            h+= result-amount;
            amount = 0;
        }
        //Columna
        for(int j = 0;j<stat.n;j++){
            for(int i = 0;i<stat.n * stat.n;i+=stat.n){
                amount = amount + stat.a[i+j];
            }
            h+= result-amount;
            amount = 0;
        }
        //Diagonal
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            amount = amount + stat.a[i+x];
            x++;
        }
        h+= result-amount;
        amount = 0;
        x--;
        for(int i = 0;i<stat.n * stat.n;i+=stat.n){
            amount = amount + stat.a[i+x];
            x--;
        }
        h+= result-amount;
        return h;
    }
}
