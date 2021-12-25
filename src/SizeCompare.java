import java.util.Comparator;

public class SizeCompare implements Comparator<StorageItem> {
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        return o1.getSize() - o2.getSize();
    }
}
