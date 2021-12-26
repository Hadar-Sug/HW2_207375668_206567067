import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class StorageItem{
    private String name;
    private final Date dateCreated;
    private static ArrayList<StorageItem> manager; // list of all Storage Items

    @SuppressWarnings("uncecked")
    private static Comparator<StorageItem>[] sorting = new Comparator[3]; //lets hold our comparators in an array
    //initialization below

    /**
     * constructor
     * @param name item name
     */
    public StorageItem(String name) {
        this.name = name;
        this.dateCreated = generateDate();
        manager.add(this);
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
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * generates a random date between 01.01.2017 and 31.12.2022
     * @return random date
     */
    private Date generateDate(){
        long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime(); //start (US style date?)
        long end = Timestamp.valueOf("2022-12-31 00:00:00").getTime(); // end
        long diff = end - offset; // difference
        Timestamp rand = new Timestamp(offset + Main.rnd.nextLong()%diff); // second part of equation ensures were in bounds
        return new Date(rand.getTime());
    }

    /**
     * prints storage tree, starts at current Item
     * @param sortBy field were sorting by
     */
    public void printTree(SortingField sortBy){
        ArrayList<StorageItem> sortedTree = getSortedTree(sortBy); // lets get a sorted list
        for (StorageItem item:sortedTree) { //iterate through it
            if (item instanceof Folder){ // check if its a folder
                System.out.println(item.getName()); // if so we print the name of it
                item.printTree(sortBy);// and then we go on to print the contents of the folder
                // probably causes problems, gotta think it through
            }else {
                System.out.println("|    " + item.getName()); // it's not a folder so just print the item
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
            return o1.getDateCreated().compareTo(o2.getDateCreated());
        }
    }


}
