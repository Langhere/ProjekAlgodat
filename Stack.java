public class Stack {
    private FolderNode top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(String newFolder) {
        FolderNode newNode = new FolderNode(newFolder, top);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String poppednewFolder = top.getName();
        top = top.getNext();
        size--;
        return poppednewFolder;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}