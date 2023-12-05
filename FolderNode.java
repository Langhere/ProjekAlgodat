class FolderNode {
    private String name;
    private FolderNode parent;
    private FolderNode child;
    private FolderNode next;
    private FileNode files;

    public FolderNode(String name, FolderNode parent) {
        this.name = name;
        this.parent = parent;
        this.child = null;
        this.next = null;
        this.files = null;
    }

    public String getName() {
        return name;
    }

    public FolderNode getParent() {
        return parent;
    }

    public FolderNode getChild() {
        return child;
    }

    public FolderNode getNext() {
        return next;
    }

    public FileNode getFiles() {
        return files;
    }

    public void setChild(FolderNode child) {
        this.child = child;
    }

    public void setNext(FolderNode next) {
        this.next = next;
    }

    public void addFile(String fileName) {
        FileNode newFile = new FileNode(fileName);
        if (files == null) {
            files = newFile;
        } else {
            FileNode currentFile = files;
            while (currentFile.getNext() != null) {
                currentFile = currentFile.getNext();
            }
            currentFile.setNext(newFile);
        }
    }
    
    public void displayContents() {
        FileNode currentFile = files;
        while (currentFile != null) {
            System.out.print(currentFile.getName() + " ");
            currentFile = currentFile.getNext();
        }

        FolderNode currentSubFolder = child;
        while (currentSubFolder != null) {
            System.out.print(currentSubFolder.getName() + " ");
            currentSubFolder.displayContents(); // Rekursif ke anak-anak
            currentSubFolder = currentSubFolder.getNext();
        }
    }
}
