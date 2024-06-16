package universite_paris8.iut.ink_leak.Modele;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {
    private String question;
    private List<DialogueNode> réponse;
    private List<String> TexteRéponse;

    public DialogueNode(String question) {
        this.question = question;
        this.réponse = new ArrayList<>();
        this.TexteRéponse = new ArrayList<>();
    }

    public void addResponse(DialogueNode NodeRéponse, String TexteRéponse) {
        this.réponse.add(NodeRéponse);
        this.TexteRéponse.add(TexteRéponse);
    }

    public String getQuestion() {
        return question;
    }

    public List<DialogueNode> getRéponse() {
        return réponse;
    }

    public List<String> getTexteRéponse() {
        return TexteRéponse;
    }
}
