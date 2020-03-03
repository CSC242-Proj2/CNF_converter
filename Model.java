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

    //TT-ENTAILS method described in the boook
    public static boolean  tt_Entails(CNF KB, CNF a){

        Model m = new Model(KB.varNum);
        ArrayList<Integer> symbols = new ArrayList<>();
        for (int i = 1; i < KB.varNum+1; i++) {
            symbols.add(i);
        }
        System.out.println("Symbols: " + symbols);
        System.out.println("TT-ENTAILS execution: ");
        return tt_check(KB,a, symbols, m);
    }

    //TT_CHECK method as decribed in book
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
            ArrayList<Integer> rest = new ArrayList<>();
            for (int i = 1; i < symbols.size(); i++) {
                rest.add(symbols.get(i));
            }
            return (tt_check(KB,a,rest,union(m,p,true)))
                    && (tt_check(KB,a,rest,union(m,p,false)));
        }
    }

    //Tests if model satisfies knowledge base
    public static boolean pl_true(CNF KB, Model m){
        HashSet<int[]> cnf = KB.clauses;
        for(int[] cl : cnf){
            if(!clause_true(cl,m)){
                return false;
            }
        }
        return true;
    }

    //Helper function for pl_true
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

    //Union function to be used in entilment methods
    public static Model union(Model m, Integer p, Boolean val){
        m.models.set(p,val);
        return m;
    }

    //GSAT method for part three
    public static Model gsat(CNF alpha, int maxFlips, int maxTries, boolean print){
        Model assignments = new Model(alpha.varNum);
        for (int i = 1; i <= maxFlips; i++) {
            ArrayList<Boolean> list = randomTruth(assignments.models);
            assignments.models = list;
            for (int j = 1; j <= maxTries; j++) {
                if(pl_true(alpha,assignments)){
                    return assignments;
                }
                int p = maxHeuristic(alpha, list);
                list = flip(list,p);
                if(print){
                    System.out.println("Using heuristic Flip at index " + p);
                    System.out.println("Model: ");
                    printModel(assignments);
                }

            }
        }
        System.out.println("UNSATISFIABLE");
        return null;
    }

    //Returns the index with max utility
    public static int maxHeuristic(CNF clauses, ArrayList<Boolean> assignments){
        int max = 0;
        int result = 0;
        for (int i = 1; i < assignments.size(); i++) {
            Boolean b = assignments.get(i);
            Model newModel = new Model(assignments.size());
            ArrayList<Boolean> newList = flip(assignments,assignments.indexOf(b));
            newModel.models = newList;
            int num = getNumTrueClauses(clauses,newModel);
            System.out.println("NUmber is : " + num);
            if(num > max){
                max = num;
                result = assignments.indexOf(b);
            }
        }
        System.out.println("result is: " + result);
        return result;
        
    }

    //Flips Boolean value at index
    public static ArrayList<Boolean> flip(ArrayList<Boolean> assignment, int index){
        Boolean x = assignment.get(index);
        assignment.set(index, !x);
        return assignment;
    }

    //Gets the number of clauses for which the model is true
    public static int getNumTrueClauses(CNF clauses, Model model){
        int num = 0;
        for(int[] cl : clauses.clauses){
            if(clause_true(cl,model)){
                num += 1;
            }
        }
        return num;
    }


    //Given an Model generates random truth assignments
    private static ArrayList<Boolean> randomTruth(ArrayList<Boolean> assignments) {
        for (int i = 1; i < assignments.size(); i++) {
            if (Math.random() > 0.5) {
                assignments.set(i, Boolean.TRUE);
            } else {
                assignments.set(i, Boolean.FALSE);
            }
        }

        return assignments;
    }
}
