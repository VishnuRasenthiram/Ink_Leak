package universite_paris8.iut.ink_leak.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import universite_paris8.iut.ink_leak.Modele.DialogueNode;

import java.util.List;

public class DialogueController {
    /*






     */
    private Label dialogueLabel;
    private Button optionButton1;
    private Button optionButton2;
    private Button optionButton3;
    private SimpleObjectProperty<DialogueNode> currentDialogueNodeProperty;
    private DialogueNode rootNode;
    private boolean targetDialogueReached;

    public DialogueController(Label dialogueLabel, Button optionButton1, Button optionButton2, Button optionButton3) {
        this.dialogueLabel = dialogueLabel;
        this.optionButton1 = optionButton1;
        this.optionButton2 = optionButton2;
        this.optionButton3 = optionButton3;
        this.currentDialogueNodeProperty = new SimpleObjectProperty<>();
        this.targetDialogueReached = false;

        currentDialogueNodeProperty.addListener((obs, oldNode, newNode) -> {
            updateDialogueUI(newNode);
            checkTargetDialogue(newNode);
        });
    }

    public void initDialogueTree() {
        rootNode = new DialogueNode("Fraise ou chocolat ?");

        DialogueNode option1 = new DialogueNode("après la Fraise il y a...?");
        DialogueNode option2 = new DialogueNode("Homps");

        rootNode.addResponse(option1, "Fraise");
        rootNode.addResponse(option2, "Un chocolat avec Homps");

        DialogueNode option1Response1 = new DialogueNode("Pas d'entrée j'imagine ?");
        DialogueNode option1Response2 = new DialogueNode("Délicieux choix de dessert !");

        option1.addResponse(option1Response1, "Un beau Flan");
        option1.addResponse(option1Response2, "Tarte aux fraises mathématiquement coupée");

        DialogueNode option2Response1 = new DialogueNode("Une douceur chocolatée, parfait !");
        DialogueNode option2Response2 = new DialogueNode("Homps, un choix intéressant !");

        option2.addResponse(option2Response1, "Gâteau au chocolat");
        option2.addResponse(option2Response2, "Mousse au chocolat");

        DialogueNode option1Response1Step2 = new DialogueNode("Merci pour votre commande !");
        DialogueNode option1Response1Step3 = new DialogueNode("Votre menu n'est pas disponible.");
        option1Response1.addResponse(option1Response1Step2, "Un thé vert, s'il vous plaît");
        option1Response1.addResponse(option1Response1Step3, "Un café, s'il vous plaît");

        DialogueNode option1Response2Step2 = new DialogueNode("Votre menu n'est pas disponible.");
        option1Response2.addResponse(option1Response2Step2, "Oui, avec plaisir");

        DialogueNode option2Response1Step2 = new DialogueNode("Votre menu n'est pas disponible.");
        option2Response1.addResponse(option2Response1Step2, "Bien sûr, ce serait délicieux");

        DialogueNode option2Response2Step2 = new DialogueNode("Votre menu n'est pas disponible.");
        option2Response2.addResponse(option2Response2Step2, "Oui, c'est parfait");
    }

    public void setInitialDialogueNode() {
        currentDialogueNodeProperty.set(rootNode);
    }

    public void handleOptionSelection(int optionIndex) {
        DialogueNode currentNode = currentDialogueNodeProperty.get();
        List<DialogueNode> réponse = currentNode.getRéponse();
        if (optionIndex >= 0 && optionIndex < réponse.size()) {
            currentDialogueNodeProperty.set(réponse.get(optionIndex));
        }
    }

    private void updateDialogueUI(DialogueNode node) {
        dialogueLabel.setText(node.getQuestion());
        List<String> responseTexts = node.getTexteRéponse();

        optionButton1.setVisible(false);
        optionButton2.setVisible(false);
        optionButton3.setVisible(false);

        if (responseTexts.size() > 0) {
            optionButton1.setText(responseTexts.get(0));
            optionButton1.setVisible(true);
        }
        if (responseTexts.size() > 1) {
            optionButton2.setText(responseTexts.get(1));
            optionButton2.setVisible(true);
        }
        if (responseTexts.size() > 2) {
            optionButton3.setText(responseTexts.get(2));
            optionButton3.setVisible(true);
        }
    }

    private void checkTargetDialogue(DialogueNode node) {
        if (node.getQuestion().equals("Merci pour votre commande !")) {
            this.targetDialogueReached = true;
        }
    }

    public boolean onTargetDialogueReached() {
        return targetDialogueReached;
    }
}
