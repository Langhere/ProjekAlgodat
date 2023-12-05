public class DirectoryNode {
    private String name;
    private DirectoryNode parent;
    private DirectoryNode child;
    private DirectoryNode next;
    private FileNode files;
    private DirectoryNode subDirectories;

    public DirectoryNode(String name, DirectoryNode parent) {
        this.name = name;
        this.parent = parent;
        this.child = null;
        this.next = null;
        this.files = null;
        this.subDirectories = null;
    }

    public String getName() {
        return name;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    public DirectoryNode getChild() {
        return child;
    }

    public DirectoryNode getNext() {
        return next;
    }

    public FileNode getFiles() {
        return files;
    }

    public DirectoryNode getSubDirectories() {
        return subDirectories;
    }

    public void setChild(DirectoryNode child) {
        this.child = child;
    }

    public void setNext(DirectoryNode next) {
        this.next = next;
    }

    public void setParent(DirectoryNode parent) {
        this.parent = parent;
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

    public void addSubDirectory(String folderName) {
        DirectoryNode newDirectory = new DirectoryNode(folderName, this);
        if (subDirectories == null) {
            subDirectories = newDirectory;
        } else {
            DirectoryNode currentDirectory = subDirectories;
            while (currentDirectory.getNext() != null) {
                currentDirectory = currentDirectory.getNext();
            }
            currentDirectory.setNext(newDirectory);
        }
    }

    public void removeFile(String fileName) {
        if (files != null) {
            if (files.getName().equals(fileName)) {
                files = files.getNext();
            } else {
                FileNode currentFile = files;
                while (currentFile.getNext() != null && !currentFile.getNext().getName().equals(fileName)) {
                    currentFile = currentFile.getNext();
                }
                if (currentFile.getNext() != null) {
                    currentFile.setNext(currentFile.getNext().getNext());
                }
            }
        }
    }

    public void removeSubDirectory(String folderName) {
        if (subDirectories != null) {
            if (subDirectories.getName().equals(folderName)) {
                subDirectories = subDirectories.getNext();
            } else {
                DirectoryNode currentDirectory = subDirectories;
                while (currentDirectory.getNext() != null && !currentDirectory.getNext().getName().equals(folderName)) {
                    currentDirectory = currentDirectory.getNext();
                }
                if (currentDirectory.getNext() != null) {
                    currentDirectory.setNext(currentDirectory.getNext().getNext());
                }
            }
        }
    }
    

    public DirectoryNode getParentDirectory() {
        return parent;
    }

    public void displayContents() {
        FileNode currentFile = files;
        while (currentFile != null) {
            System.out.println(currentFile.getName() + " ");
            currentFile = currentFile.getNext();
        }

        DirectoryNode currentDirectory = subDirectories;
        while (currentDirectory != null) {
            System.out.println(currentDirectory.getName() + " ");
            currentDirectory = currentDirectory.getNext();
        }
        System.out.println();
    }
}
