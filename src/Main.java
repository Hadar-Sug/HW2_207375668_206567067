import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static Random rnd;

    public static void main(String[] args) {
       testPartA();
       System.out.println();
       testPartB();
    }

    private static void testPartA() {
        System.out.println("---------- Tests for part A ----------");
        Clock c1 = new Clock(10, 30);
        Object o1 = c1;
        AccurateClock ac1 = new AccurateClock(10, 30, 20);
        Clock c2 = ac1;
        Object o2 = c2;
        System.out.println("c1 with o1: " + c1.equals(o1));
        System.out.println("o1 with c1: " + o1.equals(c1));

        System.out.println("c1 with ac1: " + c1.equals(ac1));
        System.out.println("ac1 with c1: " + ac1.equals(c1));
        System.out.println("c1 with c2: " + c1.equals(c2));
        System.out.println("c2 with c1: " + c2.equals(c1));
        System.out.println("c1 with o2: " + c1.equals(o2));
        System.out.println("o2 with c1: " + o2.equals(c1));

        System.out.println("o1 with ac1: " + o1.equals(ac1));
        System.out.println("ac1 with o1: " + ac1.equals(o1));
        System.out.println("o1 with c2: " + o1.equals(c2));
        System.out.println("c2 with o1: " + c2.equals(o1));
        System.out.println("o1 with o2: " + o1.equals(o2));
        System.out.println("o2 with o1: " + o2.equals(o1));

        System.out.println("ac1 with c2: " + ac1.equals(c2));
        System.out.println("c2 with ac1: " + c2.equals(ac1));
        System.out.println("ac1 with o2: " + ac1.equals(o2));
        System.out.println("o2 with ac1: " + o2.equals(ac1));
        System.out.println("c2 with o2: " + c2.equals(o2));
        System.out.println("o2 with c2: " + o2.equals(c2));
        System.out.println("c1 with null: " + c1.equals(null));
        System.out.println("o1 with null: " + o1.equals(null));
        System.out.println("ac1 with null: " + ac1.equals(null));

        System.out.println("c1: " + c1);
        System.out.println("o1: " + o1);
        System.out.println("ac1: " + ac1);
        System.out.println("c2: " + c2);
        System.out.println("o2: " + o2);
        System.out.println("new: " + new AccurateClock(5, 6, 3));

        Clock c3 = new Clock(10, 30);
        System.out.println("c1 with c3: " + c1.equals(c3));
        System.out.println("c1 hash c3: " + (c1.hashCode() == c3.hashCode()));

        Clock c4 = new AccurateClock(10, 30, 20);
        AccurateClock ac2 = new AccurateClock(10, 30, 21);
        System.out.println("c4 with ac2: " + c4.equals(ac2));
        System.out.println("ac2 with c4: " + ac2.equals(c4));
        System.out.println("c4 with ac1: " + c4.equals(ac1));
        System.out.println("o1 with c4: " + o2.equals(c4));
        System.out.println("c4 hash o2: " + (c4.hashCode() == o2.hashCode()));

        Object o3 = new Clock(25, -99);
        Clock c5 = new Clock(0, 0);
        System.out.println("o3 with c5: " + o3.equals(c5));
        Object o4 = new AccurateClock(5, 0, 30);
        AccurateClock ac3 = new AccurateClock(5, -21, 30);
        System.out.println("o4 with ac3: " + o4.equals(ac3));

    }

    private static void testPartB() {
        System.out.println("---------- Tests for part B ----------");
        rnd = new Random(42);

        Folder fo1 = new Folder("B");
        File fi1 = new File("bye", "log");
        fo1.addItem(fi1);
        Folder fo2 = new Folder("A");
        fo2.addItem(fo1);
        File fi2 = new File("Aa", "txt");
        fi2.addContent("Hello\nWorld!");
        fo2.addItem(fi2);
        File fi3 = new File("aa", "py");
        fi3.addContent("print(\"Hello World\")");
        fo2.addItem(fi3);
        Folder fo3 = new Folder("C");
        File fi4 = new File("code", "java");
        fi4.addContent("class A {\n    public A() {\n        System.out.println(\"Hey there\");\n    }\n}");
        fo3.addItem(fi4);
        fo1.addItem(fo3);
        ShortCut sc1 = new ShortCut(fo1);
        ShortCut sc2 = new ShortCut(fi2);
        fo1.addItem(sc1);
        fo3.addItem(sc2);
        ShortCut sc3 = new ShortCut(sc1);
        fo3.addItem(sc3);
        System.out.println("sc3 getItem(): " + (sc3.getItem() == sc1));
        System.out.println("sc1 getItem(): " + (sc1.getItem() == fo1));
        System.out.println("fo1 size: " + fo1.getSize());
        System.out.println("fo1 size: " + ((StorageItem) fo1).getSize());
        System.out.println("fo1 creation date: " + fo1.getCreationDate());
        System.out.println("fo2 creation date: " + fo2.getCreationDate());
        System.out.println("fi1 creation date: " + fi1.getCreationDate());
        System.out.println("fi2 creation date: " + fi2.getCreationDate());
        System.out.println("sc1 creation date: " + sc1.getCreationDate());
        System.out.println("sc3 name: " + sc3.getName());
        System.out.println();

        System.out.println("Sorting by size:");
        fo2.printTree(SortingField.SIZE);
        System.out.println("\n");

        System.out.println("Sorting by name:");
        fo2.printTree(SortingField.NAME);
        System.out.println("\n");

        System.out.println("Sorting by date:");
        fo2.printTree(SortingField.DATE);
        System.out.println("\n");

        String[] paths = {"aa.py", "B/code.java", "B/C/code.java", "AA.txt"};

        for (String path : paths) {
            File f = fo2.findFile(path);
            if (f == null) {
                System.out.println("File does not exist: " + path);
            } else {
                System.out.println("File has been found: " + path);
                f.printContent();
            }
            System.out.println("\n");
        }

        Folder f = new Folder("Testing");
        Folder temp1 = f;
        for (int i = 1; i <= 15; i++) {
            Folder temp2 = new Folder("temp" + i);
            temp1.addItem(temp2);
            temp1 = temp2;
        }
        boolean add1 = temp1.addItem(new File("test2", "cs"));
        boolean add2 = temp1.addItem(new File("Test1", "cs"));
        boolean add3 = temp1.addItem(new File("test2", "cs"));
        System.out.println("add1: " + add1);
        System.out.println("add2: " + add2);
        System.out.println("add3: " + add3);
        System.out.println();

        f.printTree(SortingField.NAME);
    }
}

