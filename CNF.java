import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CNF {

    //have a cons...
    //read the file
    //determine each line prop
    //set of int as a clause
    //add set to a list  == converted cnf
    //have const and meth for adding sets to cnf, getting sets from cnf, num of variables, get sign of an elem of a clause,
    //variables for get var and get num of clause

    //TODO Ob Orie,...
    //use cnf file
    public int varNum;
    public int clauseNum;
    public static String type;
    public HashSet<int[]> clauses;

    public CNF(int varNum, int clauseNum){
        this.varNum = varNum;
        this.clauseNum = clauseNum;
        this.clauses = new HashSet<>();
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

    public  HashSet<int[]> getClauses() {
        return clauses;
    }

    public static void printClauses(HashSet<int[]> cl){
        for (int[] a: cl) {
            System.out.print("[");
            for(int i = 0; i < a.length; i++){
                System.out.print(a[i] + ", ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public void readFile(File file){
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
                    System.out.println("Problem: " + nextLine);
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

                    int[] add_clause = cnfClauses.stream().mapToInt(Integer::intValue).toArray();
                    clauses.add(add_clause);
                }
            }
        }catch (Exception e){
        }
    }

    public void addClause(int[] cl){
        this.clauses.add(cl);
    }


    public static void main(String[] args) {
        CNF c = new CNF(0,0);
        File file = new File("nqueens_8.cnf");
        c.readFile(file);
    }
}
