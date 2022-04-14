public class Main {
    public static void main(String[] args) {

        Pilha<String> pilha = new Pilha<String> ();

        try
        {
            pilha.guardeUmItem("PYTHON");
            pilha.guardeUmItem("C");
            pilha.guardeUmItem("JAVA");
            pilha.guardeUmItem("C++");
            pilha.guardeUmItem("HTML");

            System.out.println (pilha.recupereUmItem());
            pilha.removaUmItem   ();
            System.out.println (pilha.recupereUmItem());
            pilha.removaUmItem   ();
            System.out.println (pilha.recupereUmItem());
            pilha.removaUmItem   ();
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
        }
    }
}
