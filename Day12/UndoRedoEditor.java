
class TextState {
    String text;
    TextState prev, next;

    public TextState(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState head;
    private TextState current;
    private int size = 0;
    private final int MAX_HISTORY = 10;

    // Add a new text state
    public void addText(String newText) {
        TextState newState = new TextState(newText);

        // Clear redo history if any
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
        }

        if (head == null) {
            head = newState;
            current = newState;
        } else {
            current.next = newState;
            newState.prev = current;
            current = newState;
        }

        size++;

        // Maintain history limit
        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    // Undo: go back to previous state
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Redo: go forward to next state
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    // Show current state
    public void showCurrentText() {
        if (current != null) {
            System.out.println("Current Text: \"" + current.text + "\"");
        } else {
            System.out.println("Editor is empty.");
        }
    }
}

public class UndoRedoEditor {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.addText("Hello");
        editor.addText("Hello, World");
        editor.addText("Hello, World!");
        editor.showCurrentText(); // Hello, World!

        editor.undo();
        editor.showCurrentText(); // Hello, World

        editor.undo();
        editor.showCurrentText(); // Hello

        editor.redo();
        editor.showCurrentText(); // Hello, World

        editor.addText("Hello again!");
        editor.showCurrentText(); // Hello again!

        editor.redo(); // Nothing to redo
        editor.undo();
        editor.undo();
        editor.undo(); // Nothing to undo
    }
}



