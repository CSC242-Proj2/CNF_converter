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

   
}
