import java.util.ArrayList;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> contents;

    /**
     * constructor
     * @param name name of Folder
     */
    public Folder(String name) {
        super(name);
        this.contents = new ArrayList<>();
    }

    /**
     * getter for Folder contents
     * @return content
     */
    public ArrayList<StorageItem> getContents() {
        return contents;
    }

    /**
     *
     * @return itemName name of said storageItem
     */
    private int searchContent(String itemName) {
        for (int i = 0; i < contents.size(); i++) {
            if (itemName.equals(contents.get(i).getName()))
                return i;
        }
        return -1;
    }

    /**
     * getter for folder size, which is the sum of its contents
     * @return size
     */
    public int getSize(){
        int size = 0; //The folder itself
        for (StorageItem i:contents) { //Everything it contains
            size += i.getSize();
        }
        return size;
    }

    /**
     * adds an item if it the name of said item doesn't already exist
     * @param item item to be added
     * @return true if item was added' false if the name already existed
     */
    public boolean addItem(StorageItem item){
        for (StorageItem i: contents) {
            if (item.getName().equals(i.getName()))
                return false;
        }
        contents.add(item);
        return true;
    }

    /**
     * gets file based on path
     * @param path a path leading to a certain file, might exist. will be determined in the function
     * @return the file at the end of the path, and null if it doesn't exist
     */
    public File findFile(String path) {
        String[] allItems = path.split("/"); // First of all, let's determine all our storageitems
        boolean exist = true; // a flag to determine if an item actually exists and if we find out later on it doesn't we'll return null
        int index;
        StorageItem currItem = null; // a pointer to the storageitem we are looking at, helps us find if any file exists
        for (int i = 0; i < allItems.length; i++) {
            if (!exist)
                return null;
            if(allItems[i] == null)
                return null;
            else { //We want to check if the file exists. However, manager is not a folder, so we'll divide it into 2 possible outcomes
                if (i == 0) {
                    index = super.isInManager(allItems[0]);
                    if (index == -1) //no match found
                        exist = false;
                    else {
                        currItem = differentiateTypeItem(manager.get(index)); //help function to determine File/Folder/ShortCut
                        if (currItem instanceof File) {
                            return (File) currItem;
                        }
                    }
                }
                else { //we're in folders, no need to look at any other scenario
                    Folder currItemFolder = (Folder) currItem;
                    index = currItemFolder.searchContent(allItems[i]); //searching if the item exists in the currentfolder
                    if (index == -1) //file doesn't exist
                        exist = false;
                    else { //we have found the item, need to determine if file or folder
                        currItem = differentiateTypeItem(currItemFolder.contents.get(index)); //help function to determine File/Folder/ShortCut
                        if (currItem instanceof File)  {return (File) currItem;}
                    }
                }
            }
        }
        return null; //if still hasn't been found after going through all storage items then the file doesn't exist
    }

    }
