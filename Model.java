import java.util.ArrayList;
import java.util.HashSet;

public class Model {

    ArrayList<Boolean> models;

    public Model(){
        models = new ArrayList<>();
    }
//    • Given knowledge α and query β
//      • For every possible world w
//          • If α is satisfied by w
//              • If β is not satisfied by w
//                  • Conclude that α ⊭ β
//      • Conclude that α ⊨ β

    public static boolean  tt_Entails(CNF KB, CNF a){
        //    function TT-ENTAILS?(KB,α) returns true or false
//      inputs: KB, the knowledge base, a sentence in propositional logic
//          α, the query, a sentence in propositional logic
//      symbols ← a list of the proposition symbols in KB and α
//      return TT-CHECK-ALL(KB,α,symbols,{ })
        Model m = new Model();
        ArrayList<Integer> symbols = new ArrayList<>();
        for(int[] arr : KB.clauses){
            for (int i = 0; i < arr.length; i++) {
                if(!symbols.contains(Math.abs(arr[i]))){
                    symbols.add(Math.abs(arr[i]));
                }
            }
        }
        System.out.println(symbols);
        return tt_check(KB,a, symbols, m);
    }

    public static boolean tt_check(CNF KB, CNF a, ArrayList<Integer> symbols, Model m){
        //    function TT-CHECK-ALL(KB,α,symbols,model) returns true or false
//       if EMPTY?(symbols) then
//            if PL-TRUE?(KB,model) then return PL-TRUE?(α,model)
//            else return true // when KB is false, always return true
//       else do
//            P ← FIRST(symbols)
//            rest ← REST(symbols)
//            return (TT-CHECK-ALL(KB,α,rest,model ∪ {P = true})
//              and
//              TT-CHECK-ALL(KB,α,rest,model ∪ {P = false }))
        if(symbols.isEmpty()){
            System.out.println("Here");
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
//            Model rest = new Model();
//            for (int i = 2; i < symbols.models.size(); i++) {
//                rest_models.add(symbols.models.get(i));
//            }
//            rest.models = rest_models;

            for (int i = 1; i < symbols.size(); i++) {
                rest.add(symbols.get(i));
            }
            return (tt_check(KB,a,rest,union(m,true)))
                    && (tt_check(KB,a,rest,union(m,false)));
        }
    }

    public static boolean pl_true(CNF KB, Model m){
        HashSet<int[]> cnf = KB.clauses;
        for(int[] arr : cnf){
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] > 0){
                    if(!m.models.get(Math.abs(arr[i]))){
                        return false;
                    }
                }
                else if(arr[i] < 0){
                    if(m.models.get(Math.abs(arr[i]))){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Model union(Model m, Boolean val){
        m.models.add(val);
        return m;
    }
}
