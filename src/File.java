public class File extends StorageItem{
    private String fileType;
    private String contents;

    /**
     * constructor
     * @param name name of File
     * @param type type of file - ".txt" example
     */
    public File(String name, String type) {
        super(name);
        this.fileType = type;
        this.contents = "";
    }

    /**
     * getter for file type
     * @return file type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * setter for file type
     * @param fileType file type
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * getter for contents of a file
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * setter for file content
     * @param contents the content
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * getter for File name in specified format
     * @return name of file
     */
    @Override//will this be implemented when iterating over *StorageItems* and using getName? or the father one?
    public String getName() {
        return super.getName() + "." + fileType;
    }

    /**
     * getter for size of the file
     * @return size, which is number of characters in contents
     */
    public int getSize() {
        return contents.length();
    }

    /**
     * adds content to the end of the file
     * @param contentToAdd content being added
     */
    public void addContent(String contentToAdd){
        contents = contents.concat(contentToAdd);
    }

    /**
     * prints the name of the file in specified format and the content itself
     */
    public void printContent(){
        System.out.println(this.getName() + " Size: " + getSize() + "MB" + " Created: " + super.getCreationDate());
        System.out.println(getContents());
    }
}
