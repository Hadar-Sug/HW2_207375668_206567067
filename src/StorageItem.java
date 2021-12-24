import java.sql.Timestamp;
import java.util.Date;

public class StorageItem {
    private String name;
    private final Date dateCreated;
    private int size;

    public StorageItem(String name, int size) {
        this.name = name;
        this.dateCreated = generateDate();
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private Date generateDate(){
        long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime(); //start
        long end = Timestamp.valueOf("2022-12-31 00:00:00").getTime(); // end
        long diff = end - offset; // difference
        Timestamp rand = new Timestamp(offset + Main.rnd.nextLong()%diff); // second part of equation ensures were in bounds
        return new Date(rand.getTime());
    }
}
