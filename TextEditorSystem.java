public class TextEditorSystem {
    public static void main(String[] args) {
            TextEditorSystem editor = new TextEditorSystem();
            editor.addText("Hello, world!");
            System.out.print("Current State: ");
            editor.displayCurrentState();
            editor.addText("Welcome to the text editor.");
            System.out.print("Current State: ");
            editor.displayCurrentState();
            System.out.print("After undo: ");
            editor.undo();
            editor.displayCurrentState();
            System.out.print("After redo: ");
            editor.redo();
            editor.displayCurrentState();
        }
        class State {
            String text;
            State prev, next;

            public State(String text) {
                this.text = text;
                this.prev = null;
                this.next = null;
            }
        }

        public State head = null, tail = null, current = null;
        public int size;
        private final int MAX_HISTORY = 10;

        public void addText(String newText){
            String fullText = (current == null) ? newText : current.text + " " + newText;
            State state = new State(fullText);
            if(head == null) {
                head = tail = current = state;
            } else {
                current.next = state;
                state.prev = current;
                current = state;
                tail = state;
            }
            size++;
            if (size > MAX_HISTORY) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
                size--;
            }
        }

        public void undo() {
            if (current != null && current.prev != null) {
                current = current.prev;
            }
        }

        public void redo() {
            if (current != null && current.next != null) {
                current = current.next;
            }
        }

        public void displayCurrentState() {
            if (current != null) {
                System.out.println(current.text);
            } else {
                System.out.println("No text available.");
            }
        }
}
//output
// Current State: Hello, world!
//Current State: Hello, world! Welcome to the text editor.
//After undo: Hello, world!
//After redo: Hello, world! Welcome to the text editor.


