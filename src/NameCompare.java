import java.util.Comparator;

public class NameCompare implements Comparator<StorageItem> {

    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
