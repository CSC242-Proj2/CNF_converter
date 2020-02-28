public class Testing {
    //This class is supposed to have boolean operators
    //Negation
    //Or
    //AND
    //implication
    //biipmpleacn

    public static Boolean implication(Boolean a, Boolean b){
        if((a && !b)){
            return false;
        }
        return true;

        //NOT a OR B
        //return or(negation(a), b);
    }

    public static boolean biImpliaction(Boolean a, Boolean b){
        if(a != b){
            return false;
        }
        return true;

//        NOT a OR B
        //return and(or(negation(a), b), or(negation(a), b));
    }

    public static Boolean negation(Boolean input) {
        if(input) {
            return false;
        } else if(!input) {
            return true;
        }
        return null;
    }

    public static Boolean or(Boolean[] inputs) {
        Boolean returnVal = false;

        for(int i=0; i<inputs.length; i++) {
            if(inputs[i]) {
                returnVal = true;
                break;
            }
        }
        return returnVal;
    }

    public static Boolean and(Boolean[] inputs) {
        Boolean returnVal = true;

        for(int i=0; i<inputs.length; i++) {
            if(!inputs[i]) {
                returnVal = false;
                break;
            }
        }

        return returnVal;

    }

    public static void main(String[] args) {
        //Testing P, P => Q =| Q
        CNF q = new CNF(1,1);
        int[] q_val = {2};
        q.addClause(q_val);

        System.out.println("Testing P, P => Q =| Q");
        System.out.println("Result: " + Model.tt_Entails(CNF.p_q(),q));
    }

}
