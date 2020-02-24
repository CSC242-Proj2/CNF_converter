import java.io.*;
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

    public int getClauseNum(){
        return clauseNum;
    }

    public int getVarNum(){
        return varNum;
    }
    public String getType(){
        return type;
    }

    public static ArrayList<ArrayList<Integer>> getClauses() {
        return clauses;
    }

    public static void printClauses(ArrayList<ArrayList<Integer>> cl){
        for (ArrayList<Integer> a: cl) {
            System.out.println(a);
        }
    }

    public static void readFile(File file){
        try{
            int x = 0;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                System.out.println("x " + x++);
                String nextLine = sc.nextLine();
                Scanner inner = new Scanner(nextLine);
                String first = inner.next();
                if(first.equals("c")){
                    System.out.println("The comments are: ");
                    System.out.println(nextLine);
                }
                else if(first.equals("p")){
                    System.out.println("Problem: ");
                    System.out.print(nextLine);
                    type = inner.next();
                    System.out.println("The type is " + type);
                    varNum = inner.nextInt();
                    clauseNum = inner.nextInt();
                    System.out.println("The number of vars is " + varNum + " clauses " + clauseNum);
                }
                else  {
                    ArrayList<Integer> cnfClauses = new ArrayList<>();
                    cnfClauses.add(Integer.parseInt(first));
                    int cl = inner.nextInt();
                    while (inner.hasNextInt()){
                        if(cl != 0){
                            cnfClauses.add(cl);
                        }
                        cl = inner.nextInt();
                    }
                    clauses.add(cnfClauses);
                }
            }
            printClauses(clauses);
        }catch (Exception e){
            printClauses(clauses);
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        File file = new File("nqueens_8.cnf");
        readFile(file);
    }
}
