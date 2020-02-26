import java.util.ArrayList;

public class Model {

    ArrayList<Boolean> model;

    public Model(){

    }
//    • Given knowledge α and query β
//      • For every possible world w
//          • If α is satisfied by w
//              • If β is not satisfied by w
//                  • Conclude that α ⊭ β
//      • Conclude that α ⊨ β

//    function TT-ENTAILS?(KB,α) returns true or false
//      inputs: KB, the knowledge base, a sentence in propositional logic
//          α, the query, a sentence in propositional logic
//      symbols ← a list of the proposition symbols in KB and α
//      return TT-CHECK-ALL(KB,α,symbols,{ })

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

}
