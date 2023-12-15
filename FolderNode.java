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

    public void setFileChild(FileNode files){
        this.files = files;
    }

    public void setChild(FolderNode child) {
        this.child = child;
    }

    public void setNext(FolderNode next) {
        this.next = next;
    }


    public void addFile(String fileName){
        FileNode newFile = new FileNode(fileName);

        if (files == null) {
            files = newFile;
        } else {
            if (cekNameFile(fileName)){
                int countFile = 1;
                String nameFile = getFileName(fileName);
                String formatFile = getFormatFile(fileName);

                while (cekNameFile(fileName)) {
                    fileName = nameFile + "(" + countFile + ")." + formatFile;
                    countFile++;
                }

                newFile = new FileNode(fileName);
            }

            FileNode currentFile = files;
            while (currentFile.getNext() != null) {
                currentFile = currentFile.getNext();
            }
            currentFile.setNext(newFile);
        }
    }

    private boolean cekNameFile(String fileName){
        FileNode currentFile = files;
        while (currentFile != null) {
            if (currentFile.getName().equals(fileName)) {
                return true;
            }
            currentFile = currentFile.getNext();
        }
        return false;
    }
    
    private String getFileName(String fileName){
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }
    
    private String getFormatFile(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    public void rmFile(String fileName){
        FileNode temp = null;
        FileNode currentFile = files;

        while (currentFile != null) {
            if (currentFile.getName().equals(fileName)) {
                if (temp != null) {
                    temp.setNext(currentFile.getNext());
                } else {
                    files = currentFile.getNext();
                }
                System.out.println("File '" + fileName + "' deleted");
                return;
            }
            temp = currentFile;
            currentFile = currentFile.getNext();
        }
        System.out.println("File '" + fileName + "' not found");
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
