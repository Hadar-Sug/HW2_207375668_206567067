import java.util.ArrayList;

public class Folder extends StorageItem {
    private int size;
    private ArrayList<StorageItem> items;

    public Folder(String name) {
        super(name);
        this.items = new ArrayList<>();
    }

    public int getSize(){
        for (StorageItem item:items) {
            size+= item.getSize();
        }
        return size;
    }

    public boolean addItem(StorageItem item){
        for (StorageItem i:items) {
            if (item.getName().equals(i.getName()))
                return false;
        }
        items.add(item);
        return true;
    }

    public File findFile(String path){ // on the TDL

        return new File("nothing" ,"nothing"); //for now

    }










}
