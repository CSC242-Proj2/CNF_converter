import java.util.ArrayList;
import java.util.HashSet;

public class Model {

    ArrayList<Boolean> models;

    public Model(int size){
        models = new ArrayList<>();
        for (int i = 0; i < size+1; i++) {
            models.add(i,null);
        }
    }


    public static void printModel(Model m){
        ArrayList<Boolean> a = m.models;
        System.out.print("[");
        for (int i = 1; i < a.size(); i++) {
            System.out.print(a.get(i) + " ");
        }
        System.out.print("]");
        System.out.println();
    }
    public static boolean  tt_Entails(CNF KB, CNF a){

        Model m = new Model(KB.varNum);
        ArrayList<Integer> symbols = new ArrayList<>();
//        for(int[] arr : KB.clauses){
//            for (int i = 0; i < arr.length; i++) {
//                if(!symbols.contains(Math.abs(arr[i]))){
//                    symbols.add(Math.abs(arr[i]));
//                }
//            }
//        }
        for (int i = 1; i < KB.varNum+1; i++) {
            symbols.add(i);
        }
        System.out.println("Symbols: " + symbols);
        System.out.println("TT-ENTAILS execution: ");
        return tt_check(KB,a, symbols, m);
    }

    public static boolean tt_check(CNF KB, CNF a, ArrayList<Integer> symbols, Model m){
        if(symbols.isEmpty()){
//            printModel(m);
            if(pl_true(KB,m)){
                return pl_true(a,m);
            }
            else {
                return true;
            }
        }
        else {
            Integer p = symbols.get(0);
//            System.out.println("P is: " + p);
            ArrayList<Integer> rest = new ArrayList<>();
            for (int i = 1; i < symbols.size(); i++) {
                rest.add(symbols.get(i));
            }
//            System.out.println("Rest is " + rest);
            return (tt_check(KB,a,rest,union(m,p,true)))
                    && (tt_check(KB,a,rest,union(m,p,false)));
        }
    }

    public static boolean pl_true(CNF KB, Model m){
        HashSet<int[]> cnf = KB.clauses;
//        for(int[] arr : cnf){
//            for (int i = 0; i < arr.length; i++) {
//                if(arr[i] > 0){
//                    if(!m.models.get(Math.abs(arr[i]))){
//                        return false;
//                    }
//                }
//                else if(arr[i] < 0){
//                    if(m.models.get(Math.abs(arr[i]))){
//                        return false;
//                    }
//                }
//            }
//        }

        for(int[] cl : cnf){
            if(!clause_true(cl,m)){
                return false;
            }
        }
        return true;
    }

    public static boolean clause_true(int[] cl, Model m){
        for(int x : cl){
            boolean check = x > 0;
            int index = Math.abs(x);
            if(m.models.get(index) == check){
                return true;
            }
        }
        return false;
    }


    public static Model union(Model m, Integer p, Boolean val){
        m.models.set(p,val);
        return m;
    }
}
