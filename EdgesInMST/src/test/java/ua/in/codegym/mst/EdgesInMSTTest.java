package ua.in.codegym.mst;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.*;

@RunWith(Parameterized.class)
public class EdgesInMSTTest {

    private String fileName;
    private boolean expected;

    public EdgesInMSTTest(String fileName, boolean expected) {
        this.fileName = fileName;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        File dir = new File("src/test/java/ua/in/codegym/mst");
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".in");
            }
        });

        Object[][] arr = new Object[files.length][2];

        for (int i = 0; i < files.length; i++) {
            arr[i][0] = files[i].getPath();
            arr[i][1] = true;
        }

        return Arrays.asList(arr);
    }

    @Test(timeout = 30000)
    public void test() {
        System.out.println(fileName);
        String fileNameOut = fileName.replace(".in", ".out");

        try {
            Scanner sc_in = new Scanner(new BufferedReader(new FileReader(fileName)));
            List<String> solveResult = new EdgesInMST().solve(sc_in);

            List<String> result = new ArrayList<>();
            Scanner sc_out = new Scanner(new BufferedReader(new FileReader(fileNameOut)));
            while (sc_out.hasNextLine()) {
                result.add(sc_out.nextLine());
            }

            Assert.assertEquals(result, solveResult);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
