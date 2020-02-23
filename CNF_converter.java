import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CNF_converter {

    //have a cons...
    //read the file
    //determine each line prop
    //set of int as a clause
    //add set to a list  == converted cnf
    //have const and meth for adding sets to cnf, getting sets from cnf, num of variables, get sign of an elem of a clause,
    //variables for get var and get num of clause

    //TODO Ob Orie,...
    //use cnf file
    public static int varNum;
    public static int clauseNum;
    public static String type;
    public static ArrayList<ArrayList<Integer>> clauses = new ArrayList<>();

    public CNF_converter(){

    }

    public static void printArray(ArrayList<Integer> arr){
        int n = arr.size();
        System.out.print("{");
        for (Integer i: arr) {
            System.out.print(i + ", ");
        }
        System.out.print("}");
        System.out.println();
    }

    public static void printClauses(ArrayList<ArrayList<Integer>> cl){
        System.out.println("[");
        for (ArrayList<Integer> a: cl) {
            printArray(a);
        }
    }

    public static void readFile(File file){
        try{
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String nextLine = sc.nextLine();
                Scanner inner = new Scanner(nextLine);
                String first = inner.next();
                if(first.equals("c")){
                    System.out.println("The comments are: ");
                    System.out.println(nextLine);
                }
                else if(first.equals("p")){
                    System.out.println("Problem: ");
                    System.out.println(nextLine);
                    type = inner.next();
                    System.out.println("The type is " + type);
                    varNum = inner.nextInt();
                    clauseNum = inner.nextInt();
                    System.out.println("The number of vars is " + varNum + " clauses " + clauseNum);
                }
                else {
                    ArrayList<Integer> cnfClauses = new ArrayList<>();
                    ArrayList<String> el = nextLine.
                    
                    System.out.println("size is: " + cnfClauses.size());
                    printArray(cnfClauses);
                    clauses.add(cnfClauses);
//                    printClauses(clauses);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int getNumClauses(){
        return 0;
    }

    public static void main(String[] args) {
        File file = new File("quinn.cnf.txt");
        readFile(file);
    }
}
