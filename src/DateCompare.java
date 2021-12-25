import java.util.Comparator;

public class DateCompare implements Comparator<StorageItem> {
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        return o1.getDateCreated().compareTo(o2.getDateCreated());
    }
}
