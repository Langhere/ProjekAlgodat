import java.util.Scanner;

public class FileSystem {
    private FolderNode root;
    private FolderNode currentFolder;

    public FileSystem() {
        this.root = new FolderNode("home", null);
        this.currentFolder = root;
    }

    public void mkdir(String folderName) {
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

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    
    
    public void createFile(String fileName) {
        currentFolder.addFile(fileName);
    }
    
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
    
    
    public void ls() {
        currentFolder.displayContents();
        System.out.println();
    }

    public String getCurrentPath() {
        StringBuilder pathBuilder = new StringBuilder();
        FolderNode current = currentFolder;

        while (current != null) {
            pathBuilder.insert(0, "/" + current.getName());
            current = current.getParent();
        }

        return pathBuilder.length() == 0 ? "/" : pathBuilder.toString();
    }
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
    
    public void rm(String targetName) {
        if (targetName.contains(".")) {
            // Remove file
            currentFolder.removeFile(targetName);
            System.out.println(targetName + " removed.");
        } else {
            // Remove folder
            currentFolder.removeSubDirectory(targetName);
            System.out.println(targetName + " removed.");
        }
    }
    
    

    public void processCommand(String command, Scanner scanner) {
        switch (command) {
            case "mkdir":
                String folderName = scanner.next();
                mkdir(folderName);
                break;
            case "create":
                String fileName = scanner.next();
                createFile(fileName);
                break;
            case "cd":
                String folderNameToCd = scanner.next();
                cd(folderNameToCd);
                break;
            case "ls":
                ls();
                break;
            case "exit":
                scanner.close();
                System.exit(0);
                break;
            case "search":
                String searchPath = scanner.next();
                search(searchPath);
                break;
            case "clear":
                clearScreen();
                break;
            default:
                System.out.println("Invalid command.");
        }
    }
}
