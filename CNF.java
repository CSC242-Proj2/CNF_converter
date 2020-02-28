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

    //Part two CNF definitions
    //{P, P ⇒ Q} |= Q
    public static CNF p_q(){
        CNF p_q = new CNF(2,2);
        int[] p = {1};
        int[] p_imp_q = {-1,2};
        p_q.addClause(p);
        p_q.addClause(p_imp_q);
        return p_q;
    }

    //Wumpus World problem without percepts
    public static CNF wumpus(){
        CNF wumpus = new CNF(9,12);
        int[] r1 = {-1};
        int[] r2_1 = {-7,2,4};
        int[] r2_2 = {-2,7};
        int[] r2_3 = {-4,7};
        int[] r3_1 = {-9,1,5,6};
        int[] r3_2 = {-1,9};
        int[] r3_3 = {-5,9};
        int[] r3_4 = {-6,9};
        int[] r7_1 = {-8,1,5,3};
        int[] r7_2 = {-1,8};
        int[] r7_3 = {-5,8};
        int[] r7_4 = {-3,8};

        wumpus.addClause(r1);
        wumpus.addClause(r2_1);
        wumpus.addClause(r2_2);
        wumpus.addClause(r2_3);
        wumpus.addClause(r3_1);
        wumpus.addClause(r3_2);
        wumpus.addClause(r3_3);
        wumpus.addClause(r3_4);
        wumpus.addClause(r7_1);
        wumpus.addClause(r7_2);
        wumpus.addClause(r7_3);
        wumpus.addClause(r7_4);

        return wumpus;

    }

    //(Russell & Norvig) Unicorn problem
    public static CNF unicorn(){
        CNF unicorn = new CNF(5,6);
        int[] r1 = {-1,2};
        int[] r2_1 = {1,-2};
        int[] r2_2 = {1,3};
        int[] r3_1 =  {-2,4};
        int[] r3_2 = {-3,4};
        int[] r4 = {-4,5};

        unicorn.addClause(r1);
        unicorn.addClause(r2_1);
        unicorn.addClause(r2_2);
        unicorn.addClause(r3_1);
        unicorn.addClause(r3_2);
        unicorn.addClause(r4);

        return unicorn;
    }


    public static void main(String[] args) {
        CNF c = new CNF(0,0);
        File file = new File("nqueens_8.cnf");
        c.readFile(file);
    }
}
