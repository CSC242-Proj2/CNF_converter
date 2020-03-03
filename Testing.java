import java.util.ArrayList;
import java.util.Scanner;

public class Testing {

    public static void main(String[] args) {

        //Part two
        //Testing P, P => Q =| Q
        CNF q = new CNF(1,1);
        CNF modes = CNF.p_q();
        System.out.println("Modes Pollens KB: ");
        CNF.print_CNF(modes);
        int[] q_val = {2};
        q.addClause(q_val);
        System.out.println("Testing P, P => Q |= Q");
        System.out.println("Result: " + Model.tt_Entails(modes,q));
        System.out.println();
        //Testing Wumpus world
        System.out.println("Testing Wumpus world with KB: ");
        System.out.println("R1: ¬P1,1\n" +
                "R2: B1,1 ⇔ P1,2 ∨ P2,1\n" +
                "R3: B2,1 ⇔ P1,1 ∨ P2,2 ∨ P3,1\n" +
                "R7: B1,2 ⇔ P1,1 ∨ P2,2 ∨ P1,3\n");
        CNF w_KB = CNF.wumpus();
        System.out.println("Clauses before precept R4: ¬B1,1 is added");
        CNF.print_CNF(w_KB);
        int[] B11_not = {-7};
        w_KB.addClause(B11_not);
        System.out.println("Clauses after precept R4: ¬B1,1 is added");
        CNF.print_CNF(w_KB);

        System.out.println("Testing whether knowledge base entails ¬P1,2");
        CNF p12_C_not = new CNF(1,1);
        int[] p12_not = {-2};
        p12_C_not.addClause(p12_not);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p12_C_not));

        System.out.println("Testing whether knowledge base entails ¬P2,1");
        CNF p21_C_not = new CNF(1,1);
        int[] p21_not = {-4};
        p21_C_not.addClause(p21_not);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p21_C_not));

        System.out.println("Testing whether knowledge base does not entail P2,2");
        CNF p22_C = new CNF(1,1);
        int[] p22 = {5};
        p22_C.addClause(p22);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p22_C));

        System.out.println("Testing whether knowledge base does not entail ¬P2,2");
        CNF p22_C_not = new CNF(1,1);
        int[] p22_not = {-5};
        p22_C_not.addClause(p22_not);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p22_C_not));

        System.out.println("Clauses before precept R5: B2,1 is added");
        CNF.print_CNF(w_KB);
        int[] B21 = {9};
        w_KB.addClause(B21);
        System.out.println("Clauses after precept R5: B2,1 is added");
        CNF.print_CNF(w_KB);

        System.out.println("Testing whether knowledge base entails P2,2 ∨ P3,1");
        CNF p22_or_p31_C = new CNF(1,1);
        int[] p22_or_p31 = {5,6};
        p22_or_p31_C.addClause(p22_or_p31);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p22_or_p31_C));

        System.out.println("Testing whether knowledge base does not entail P2,2");
        System.out.println("Result: " + Model.tt_Entails(w_KB,p22_C));

        System.out.println("Testing whether knowledge base does not entail ¬P2,2");
        System.out.println("Result: " + Model.tt_Entails(w_KB,p22_C_not));

        System.out.println("Testing whether knowledge base does not entail P3,1");
        CNF p31_C = new CNF(1,1);
        int[] p31 = {6};
        p31_C.addClause(p31);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p31_C));

        System.out.println("Testing whether knowledge base does not entail ¬P3,1");
        CNF p31_C_not = new CNF(1,1);
        int[] p31_not = {-6};
        p31_C_not.addClause(p31_not);
        System.out.println("Result: " + Model.tt_Entails(w_KB,p31_C_not));

        System.out.println("Clauses before precept R6: ¬B1,2 is added");
        CNF.print_CNF(w_KB);
        int[] B12_not = {-8};
        w_KB.addClause(B12_not);
        System.out.println("Clauses after precept R6: ¬B1,2 is added");
        CNF.print_CNF(w_KB);

        System.out.println("Testing whether knowledge base entails ¬P2,2");
        System.out.println("Result: " + Model.tt_Entails(w_KB,p22_C_not));

        System.out.println("Testing whether knowledge base entails P3,1");
        System.out.println("Result: " + Model.tt_Entails(w_KB,p31_C));

        //Testing unicorn Queries
        CNF unicorn_KB = CNF.unicorn();
        System.out.println("\nUnicorn KB: ");
        CNF.print_CNF(unicorn_KB);
        System.out.println("\nTesting unicorn Queries");
        System.out.println("(a) Can we prove that the unicorn is mythical?");
        CNF mythical = new CNF(1,1);
        int[] myth = {1};
        mythical.addClause(myth);
        System.out.println("Result: " + Model.tt_Entails(unicorn_KB,mythical));

        System.out.println("(b) Can we prove that the unicorn is magical?");
        CNF magical = new CNF(1,1);
        int[] magic = {5};
        magical.addClause(magic);
        System.out.println("Result: " + Model.tt_Entails(unicorn_KB,magical));

        System.out.println("(c) Can we prove that the unicorn is horned?");
        CNF horned = new CNF(1,1);
        int[] horn = {4};
        horned.addClause(horn);
        System.out.println("Result: " + Model.tt_Entails(unicorn_KB,horned));


        //Part three
        System.out.println("Testing for part three");
        Scanner sc = new Scanner(System.in);

        int maxFlips;
        int maxTries;

        System.out.println("\nRunning Satisfiability checker for (x1 ∨ x3 ∨ ¬x4) ∧ (x4) ∧ (x2 ∨ ¬x3)");
        System.out.println("Enter reasonable MaxFlip value:  ");
        maxFlips = sc.nextInt();
        System.out.println("Enter reasonable MaxTries value:  ");
        maxTries = sc.nextInt();
        Model model = Model.gsat(CNF.dimacs_1(), maxFlips, maxTries, true);
        if(model == null){
            System.out.println("Sorry we could't find a satisfying model this time around." +
                    "\nBetter luck next time");
        }
        else {
            System.out.println("We found a satisfying Model: ");
            Model.printModel(model);
        }


        System.out.println("\nRunning Satisfiability checker for n-queens problems");
        System.out.println("N = 4 n-queen problem");
        CNF nqueens_4 = new CNF(1,1);
        nqueens_4.dimacs_read("nqueens_4");
        System.out.println("Enter reasonable MaxFlip value:  ");
        maxFlips = sc.nextInt();
        System.out.println("Enter reasonable MaxTries value:  ");
        maxTries = sc.nextInt();
        model = Model.gsat(nqueens_4,maxFlips,maxTries,true);
        if(model == null){
            System.out.println("Sorry we could't find a satisfying model this time around." +
                    "\nBetter luck next time");
        }
        else {
            System.out.println("We found a satisfying Model: ");
            Model.printModel(model);
        }

    }

}
