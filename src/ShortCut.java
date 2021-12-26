public class ShortCut extends StorageItem{
    private StorageItem wantedItem;
    public ShortCut(StorageItem wantedFile) {
        super(wantedFile.getName());
        this.wantedItem = wantedFile;
    }

    private StorageItem getWantedItem() {
        return wantedItem;
    }

    @Override
    public String getName() {
        return super.getName() + " [shortcut]";
    }

    @Override
    public int getSize() {
        return 1;
    }

    public StorageItem getItem(){
        return getWantedItem();
    }
}
