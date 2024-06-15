package universite_paris8.iut.ink_leak.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArbreDialogue {

    public static void main(String[] args) {

        dialogue root = new dialogue("Bienvenue dans le jeu. Que voulez-vous faire ?");
        dialogue option1 = new dialogue("Explorer la forêt");
        dialogue option2 = new dialogue("Visiter le village");
        dialogue option3 = new dialogue("Quitter le jeu");

        // Ajouter des réponses à la racine
        root.addResponse(option1);
        root.addResponse(option2);
        root.addResponse(option3);

        // Ajouter des sous-dialogues
        option1.addResponse(new dialogue("Vous trouvez un trésor caché !"));
        option1.addResponse(new dialogue("Un loup apparaît !"));

        option2.addResponse(new dialogue("Vous rencontrez un marchand."));
        option2.addResponse(new dialogue("Vous trouvez une auberge."));

        // Naviguer dans l'arbre
        navigateDialogue(root);

        // Scanner closing (moved outside the loop)
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }

    public static void navigateDialogue(dialogue node) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(node.getMessage());

            List<dialogue> responses = node.getResponses();
            if (responses.isEmpty()) {
                break;
            }

            for (int i = 0; i < responses.size(); i++) {
                System.out.println((i + 1) + ". " + responses.get(i).getMessage());
            }

            int choice = scanner.nextInt();
            if (choice < 1 || choice > responses.size()) {
                System.out.println("Choix invalide, veuillez réessayer.");
            } else {
                node = responses.get(choice - 1);
            }
        }

        System.out.println("Fin du dialogue.");
    }
}

class dialogue {
    private String message;
    private List<dialogue> responses;

    public dialogue(String message) {
        this.message = message;
        this.responses = new ArrayList<>();
    }

    public void addResponse(dialogue response) {
        responses.add(response);
    }

    public String getMessage() {
        return message;
    }

    public List<dialogue> getResponses() {
        return responses;
    }
}
