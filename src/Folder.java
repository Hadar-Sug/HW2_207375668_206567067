import java.util.ArrayList;

public class Folder extends StorageItem {
    private int size;
    private ArrayList<StorageItem> contents;

    /**
     * constructor
     * @param name name of Folder
     */
    public Folder(String name) {
        super(name);
        this.contents = new ArrayList<>();
        size = 0;
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
    public int getSize(){ return size; }

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
        size += item.getSize();
        contents.add(item);
        return true;
    }

    /**
     * gets file based on path
     * @param path
     * @return
     */
    public File findFile(String path) { // on the TDL
        String[] allFiles = path.split("/");
        boolean exist = true;
        int index;
        StorageItem currItem = null;
        for (int i = 0; i < allFiles.length; i++) {
            if (!exist)
                return null;
            if(allFiles[i] == null)
                return null;
            else { //We want to check if the file exists

                if (i == 0) { // manager is not a folder, always need to check this first
                    index = super.isInManager(allFiles[0]);
                    if (index == -1) //no match found
                        exist = false;
                    else {
                        ArrayList<StorageItem> managerItem = manager;
                        if (managerItem.get(i) instanceof File) {
                            return (File) managerItem.get(i);
                        }
                        else if (managerItem.get(i) instanceof Folder) {
                            currItem = (Folder) managerItem.get(i);
                        }
                        else {
                            // it's a shortcut, so we'll determine the item
                            ShortCut sc1 = (ShortCut) managerItem.get(i);
                            currItem = sc1.getItem();
                            if (currItem instanceof File)
                                return (File) currItem;
                        }
                    }
                }
                else { //we're in folders, no need to look another scenario
                    Folder currItemFolder = (Folder) currItem;
                    index = currItemFolder.searchContent(allFiles[i]); //searching if the item exists in the currentfolder
                    if (index == -1) //file doesn't exist
                        exist = false;
                    else { //we have found the item, need to determine if file or folder
                        ArrayList<StorageItem> contentItem = currItemFolder.contents;
                        if (contentItem.get(i) instanceof File)  {return (File) contentItem.get(i);}
                        else if (contentItem.get(i) instanceof Folder) {currItem = (Folder) contentItem.get(i);}

                        else { //it's a shortcut
                            ShortCut sc1 = (ShortCut) contentItem.get(i);
                            currItem = sc1.getItem();
                            if (currItem instanceof File) {
                                return (File) currItem;
                            } else if (currItem instanceof Folder) {
                                currItem = (Folder) currItem;
                            }
                        }
                    }
                }
            }
        }
        return null; //forever
    }
}
