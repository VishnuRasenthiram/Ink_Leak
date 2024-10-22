package universite_paris8.iut.ink_leak;

// Interface de base
interface Notifier {
    void send(String message);
}

// Classe de base
class SimpleNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println(message);
    }


    public static void main(String[] args) {
        Notifier notifier = new SimpleNotifier();
        notifier.send("Hello, world!");
    }
}
// À vous de compléter avec :
// 1. Un décorateur abstrait
// 2. Trois décorateurs concrets au choix
// 3. Une classe Main pour tester