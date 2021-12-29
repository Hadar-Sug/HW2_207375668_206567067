import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class StorageItem{
    private String name;
    private final Date dateCreated;
    protected static ArrayList<StorageItem> manager = new ArrayList<StorageItem>(); // list of all Storage Items

    @SuppressWarnings("uncecked")
    private static Comparator[] sorting = new Comparator[3]; //lets hold our comparators in an array
    //initialization below (line 168)

    /**
     * constructor
     * @param name item name
     */
    public StorageItem(String name) {
        this.name = name;
        this.dateCreated = generateDate();
        manager.add(this);
        initializer();
    }

    /**
     * getter for size of an item
     * @return size of an item
     */
    public abstract int getSize();

    /**
     * getter for name
     * @return item name
     */
    public String getName(){
        return name;
    }

    /**
     * setter for name
     * @param name item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * date created getter
     * @return date created
     */
    public Date getCreationDate() { return dateCreated; }

    /**
     *
     * @param itemName name of the storageItem we want to search for
     * @return its index in manager if it exists, and -1 otherwise
     */
    public int isInManager(String itemName) {
        for (int i = 0; i < manager.size(); i++) {
            if (itemName.equals(manager.get(i).getName()))
                return i;
        }
        return -1;
    }

    /**
     * generates a random date between 01.01.2017 and 31.12.2022
     * @return random date
     */
    private Date generateDate(){
        long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime(); //start
        long end = Timestamp.valueOf("2022-12-31 23:59:59").getTime(); // end
        long diff = end - offset; // difference
        long y =  Math.abs(Main.rnd.nextLong());
        long range = offset + y%diff; // second part of equation ensures were in bounds
        return new Timestamp(range);
    }
    /**
     *
     * @param item StorageItem to be determined (File/Folder/Shortcut)
     * @return casting of the storageItem we want to use
     */
    public StorageItem differentiateTypeItem(StorageItem item) {
        StorageItem currItem = item;
        while(currItem instanceof ShortCut) {
            //If it's a shortcut, we want to go deep and determine if the shortcut is a file or folder
            ShortCut sc1 = (ShortCut) currItem;
            currItem = sc1.getItem();
            if (currItem instanceof File) {
                return (File) currItem;
            } else if (currItem instanceof Folder) {
                return (Folder) currItem;
            }
        }
        if (currItem instanceof File)  {return (File) currItem;}
        return (Folder) currItem;
    }

    /**
     * prints the tree of the storage item and deeper
     * @param sortBy the field we're sorting by
     */
    public void printTree(SortingField sortBy){
        if(differentiateTypeItem(this) instanceof Folder){
            System.out.println(this.getName());//We want to print the folder we're in atm
            printTree(sortBy,"|    "); //Helps us utilize and keep track of the "|" and the folders we're in
        }
        else{
            System.out.println(this.getName());//We want to print the file, and that's it!
        }

    }

    /**
     * prints storage tree, starts at current Item (not included). Helps us control our output onto the screen
     * @param sortBy field were sorting by
     * @param intro the string before each file/folder in order to print
     */
    private void printTree(SortingField sortBy, String intro){
        ArrayList<StorageItem> sortedTree = getSortedTree(sortBy); // lets get a sorted list
        for (StorageItem item:sortedTree) { //iterate through it
            if (item instanceof Folder){
                System.out.println(intro+item.getName()); // if we have found a folder, we print the name of it
                item.printTree(sortBy,intro+"|    ");// and then we go on to print the contents of the folder

            }else {
                System.out.println(intro+ item.getName()); // it's not a folder so just print the file
            }
        }

    }

    /**
     * sorts a list based on specific SortingField
     * @param sortBy field we're sorting by
     * @return the sorted list
     */
    private ArrayList<StorageItem> getSortedTree(SortingField sortBy){
        ArrayList<StorageItem> subList;
        if (this instanceof Folder){ // if it's a Folder we only want to sort the contents of that folder
            subList = new ArrayList<>(((Folder) this).getContents());
        }else { // if it's not a folder we need to sort the whole list starting from the position we're in
            subList = new ArrayList<>(manager.subList(manager.indexOf(this), manager.size()));
        }

        int sortVal = sortBy.getVal();
        switch (sortBy){
            case DATE:
            case SIZE:
                subList.sort(sorting[sortVal].thenComparing(sorting[SortingField.NAME.getVal()]));
                // for Date and Size comparing, secondary comparison is base on name
            break;
            case NAME:subList.sort(sorting[sortVal]);
            break;
        }
        return subList;
    }

    /**
     * initializer for comparator array
     */
    private static void initializer(){
        sorting[0] = new NameCompare();
        sorting[1] = new SizeCompare();
        sorting[2] = new DateCompare();
    }

    /**
     * comparator to compare names
     */
    public static class NameCompare implements Comparator<StorageItem> {

        @Override
        public int compare(StorageItem o1, StorageItem o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
    /**
     * comparator to compare sizes
     */
    public static class SizeCompare implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem o1, StorageItem o2) {
            return o1.getSize() - o2.getSize();
        }
    }
    /**
     * comparator to compare date created
     */
    public static class DateCompare implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem o1, StorageItem o2) {
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    }


}
