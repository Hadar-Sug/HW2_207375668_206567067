public class ShortCut extends StorageItem{
    //attributes
    private StorageItem wantedItem;

    //methods
    /**
     * constructor
     * @param wantedFile the file/folder/shortcut we want to create a shortcut for
     */
    public ShortCut(StorageItem wantedFile) {
        super(wantedFile.getName());
        this.wantedItem = wantedFile;
    }

    /**
     * returns a toString of the shortcut, like it'll be printed onto the screen
     * @return string of "item"+[shortcut]
     */
    @Override
    public String getName() {
        return super.getName() + " [shortcut]";
    }

    /**
     * @return the size of a shortcut, being a constant 1.
     */
    @Override
    public int getSize() { return 1; }

    /**
     * @return returns our item we're refrencing to
     */
    public StorageItem getItem(){
        return wantedItem;
    }
}
