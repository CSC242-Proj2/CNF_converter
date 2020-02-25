public class Operators {
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
    }

    public static boolean biImpliaction(Boolean a, Boolean b){
        if(a != b){
            return false;
        }
        return true;
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

}
