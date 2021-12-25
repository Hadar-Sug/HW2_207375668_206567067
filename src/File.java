public class File extends StorageItem{
    private String fileType;
    private String contents;
    private int size;

    public File(String name, String type) {
        super(name);
        this.fileType = type;
        this.contents = null;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


    @Override//will this be implemented when iterating over *StorageItems* and using getName? or the father one?
    public String getName() {
        return super.getName() + "." + fileType;
    }

    public int getSize() {
        return contents.length();
    }

    public void addContent(String contentToAdd){
        contents = contents.concat(contentToAdd);
    }
    public void printContent(){
        System.out.println(this.getName() + " Size: " + getSize() + "MB" + " Created: " + super.getDateCreated());
        System.out.println(getContents());
    }
}
