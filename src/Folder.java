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
    }

    /**
     * getter for Folder contents
     * @return content
     */
    public ArrayList<StorageItem> getContents() {
        return contents;
    }

    /**
     * getter for folder size, which is the sum of its contents
     * @return size
     */
    public int getSize(){
        for (StorageItem item: contents) {
            size+= item.getSize();
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
     * @param path
     * @return
     */
    public File findFile(String path){ // on the TDL

        return new File("nothing" ,"nothing"); //for now

    }
}
