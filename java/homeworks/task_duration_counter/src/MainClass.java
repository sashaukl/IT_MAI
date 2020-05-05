import impls.MyFileReader;
import models.Node;
import models.NodeReader;
import org.w3c.dom.ls.LSOutput;


import java.util.*;


public class MainClass {
    public static final int ITERATIONS = 100;
    public static final String FILE_NAME = "alwaysSuccess";  /*"data";*/

    public static void main(String... str){
        new MainClass().mainFoo();
    }

    private void mainFoo(){
        MyFileReader fileReader = new NodeReader();
        nodeList = fileReader.read(FILE_NAME);
        //mock case
        task_resources = fileReader.getLastResources();
        for (int i=0; i<ITERATIONS; i++){
            new Solver();
        }
    }

    private List<Node> nodeList;

    private List<Integer> task_resources;

    class Solver implements Runnable {
        private Thread thread;
        Solver() {
            thread = new Thread(this);
            thread.start();
        }

        public void run() {
            Random random = new Random(System.currentTimeMillis());
            HashSet<Integer> values = new HashSet();
            values.add(0);
            for (int j=0; j<10 +random.nextInt(nodeList.size()-10); j++){
                values.add(Math.abs( random.nextInt() % nodeList.size() ));
            }
            new TaskService(nodeList, new ArrayList(values), task_resources).init();
        }
    }
}
