public class FileNode {
    private String name;
    private FileNode next;

    public FileNode(String name) {
        this.name = name;
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public FileNode getNext() {
        return next;
    }

    public void setNext(FileNode next) {
        this.next = next;
    }
}
