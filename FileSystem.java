import java.util.Scanner;

public class FileSystem {
    private FolderNode root;
    private FolderNode currentFolder;
    private input userInput;
    public FileSystem() {
        this.root = new FolderNode("home", null);
        this.currentFolder = root;
        this.userInput = new input();
    }

    public void aptGetInstall(String toolName) {
        System.out.println("Downloading " + toolName + "...");
        for (int i = 0; i < 10; i++) {
            System.out.print(">");
            try {
                Thread.sleep(200);  // Pause selama 200 milidetik (0.2 detik)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\ndownloaded");
        String apkName = toolName + ".apk";

        // Simulasi instalasi tool
        createFile(apkName);
        System.out.println("Installation complete.");
    }

    //fungsi mkdir
    public void mkdir(String folderName){
        if(cekFolderName(folderName)){
            System.out.println("Folder with the name '" + folderName + "' already exists.");
            return;
        }
        FolderNode newFolder = new FolderNode(folderName, currentFolder);
        if (currentFolder.getChild() == null) {
            currentFolder.setChild(newFolder);
        } else {
            FolderNode currentSubFolder = currentFolder.getChild();
            while (currentSubFolder.getNext() != null) {
                currentSubFolder = currentSubFolder.getNext();
            }
            currentSubFolder.setNext(newFolder);
        }
    }

    private boolean cekFolderName(String folderName){
        FolderNode currentSubFolder = currentFolder.getChild();
        while (currentSubFolder != null) {
            if (currentSubFolder.getName().equals(folderName)) {
                return true;
            }
            currentSubFolder = currentSubFolder.getNext();
        }
        return false;
    }

    //fungsi clear
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    //fungsi create
    public void createFile(String fileName) {
        currentFolder.addFile(fileName);   
    }

    //fungsi cd
    public void cd(String destination) {
        if (destination.equals("..")) {
            FolderNode parent = currentFolder.getParent();
            if (parent != null) {
                currentFolder = parent;
            } else {
                System.out.println("Already at the root directory.");
            }
        } else {
            // Pengecekan apakah destination adalah sebuah file
            if (destination.contains(".")) {
                System.out.println("cd: " + destination + " is not a directory");
            } else {
                // Pemindahan ke direktori anak
                FolderNode targetFolder = linearSearch(currentFolder, destination);
                if (targetFolder != null) {
                    currentFolder = targetFolder;
                } else {
                    System.out.println("cd: " + destination + " not found");
                }
            }
        }
    }

    //fungsi delete
    public void rm(String folderFileName){
        if (folderFileName.contains(".")) {
            currentFolder.rmFile(folderFileName);
        } else {
            rmFolder(folderFileName);
        }
    }

    private void rmFolder(String folderName){
        FolderNode temp = null;
        FolderNode currentSubFolder = currentFolder.getChild();

        while (currentSubFolder != null) {
            if (currentSubFolder.getName().equals(folderName)) {
                if(temp != null){
                    temp.setNext(currentSubFolder.getNext());
                } else {
                    currentFolder.setChild(currentSubFolder.getNext());
                }
                System.out.println("Folder '" + folderName + "' deleted");
                return;
            }
            temp = currentSubFolder;
            currentSubFolder = currentSubFolder.getNext();
        }
        System.out.println("Folder '" + folderName + "' not found");
    }
    
    //sorting
    public void sort() {
        System.out.println("Sorting contents...");
        bubbleSort(currentFolder);
        System.out.println("Sort complete.");
    }
    
    private void bubbleSort(FolderNode folder) {
        if (folder == null || folder.getChild() == null) {
            return;
        }
    
        boolean swapped;
        do {
            swapped = false;
            FolderNode current = folder.getChild();
            FolderNode previous = null;
    
            while (current != null && current.getNext() != null) {
                FolderNode nextNode = current.getNext();
                if (current.getName().compareToIgnoreCase(nextNode.getName()) > 0) {
                    // Swap nodes
                    if (previous != null) {
                        previous.setNext(nextNode);
                    } else {
                        folder.setChild(nextNode);
                    }
    
                    current.setNext(nextNode.getNext());
                    nextNode.setNext(current);
    
                    current = nextNode;
                    swapped = true;
                }
    
                previous = current;
                current = current.getNext();
            }
        } while (swapped);
    }
    //end sorting

    //fungsi ls
    public void ls() {
        currentFolder.displayContents();
        System.out.println();
    }
    //fungsi folder saat ini
    public String getCurrentPath() {
        String path = "";
        FolderNode current = currentFolder;
    
        while (current != null) {
            // Menambahkan nama folder saat ini ke awal path
            path = "/" + current.getName() + path;
            current = current.getParent();
        }
        // Jika path masih kosong, berarti kita berada di root
        return path.isEmpty() ? "/" : path;
    }

    public void pwd() {
        Stack stackFolder = new Stack();
        FolderNode current = currentFolder;

        while (current != null) {
            stackFolder.push(current.getName());
            current = current.getParent();
        }

        System.out.print("You are in the directory: ");
        while (!stackFolder.isEmpty()) {
            System.out.print("/" + stackFolder.pop());
        }
        System.out.println();
    }
    
    //fungsi searching
    public void search(String path) {
        String[] folders = path.split("/");
        FolderNode current = root;
        StringBuilder fullPath = new StringBuilder();
        fullPath.append("/");
        for (String folderName : folders) {
            if (folderName.equals("~")) {
                current = root;
                fullPath.append(current.getName());
            } else {
                current = linearSearch(current, folderName);
                if (current == null) {
                    System.out.println(path + " is not found.");
                    return;
                }
                fullPath.append("/").append(current.getName());
            }
        }
        System.out.println(path + " is found in " + fullPath.toString());
    }
    private FolderNode linearSearch(FolderNode current, String folderName) {
        FolderNode currentSubFolder = current.getChild();
        while (currentSubFolder != null) {
            if (currentSubFolder.getName().equals(folderName)) {
                return currentSubFolder;
            }
            currentSubFolder = currentSubFolder.getNext();
        }
        return null;
    }
    //end search

    //all command process
    public void processCommand(String command, Scanner scanner) {
        switch (command) {
            case "mkdir":
                String folderName = scanner.next();
                mkdir(folderName);
                break;
            case "create":
                String fileName = scanner.next();
                if (fileName.contains(".")) {
                    createFile(fileName);
                } else {
                    System.out.println("Is Not File");
                }
                break;
            case "rm":
                String folderFile = scanner.next();
                rm(folderFile);
                break;
            case "cd":
                String folderNameToCd = scanner.next();
                cd(folderNameToCd);
                break;
            case "ls":
                ls();
                break;
            case "search":
                String searchPath = scanner.next();
                search(searchPath);
                break;
            case "clear":
                clearScreen();
                break;
            case "apt-get-install":
                String toolName = scanner.next();
                aptGetInstall(toolName);
                break;
            case "sort":
                sort();
                break;
            case "pwd":
                pwd();
                break;
            default:
                System.out.println("Invalid command.");
        }
    }
}
