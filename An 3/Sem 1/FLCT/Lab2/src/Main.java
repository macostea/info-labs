import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
//            Grammar grammar = new Grammar();
//
//            grammar.readFromFile("grammar.txt");
//
//            System.out.println(grammar);
//
//            Automaton automaton = new Automaton(grammar);
//            System.out.println(automaton);

            Automaton automaton = new Automaton();
            automaton.readFromFile("automaton.txt");

            System.out.println(automaton);

            Grammar grammar = new Grammar(automaton);
            System.out.println(grammar);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
