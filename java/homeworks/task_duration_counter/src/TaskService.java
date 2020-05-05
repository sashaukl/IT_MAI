import impls.MyFileReader;
import models.Node;
import models.NodeReader;
import models.NothingToFollowFromException;

import java.util.*;

public class TaskService {

    private volatile List<Node> nodeList;
    private List<Integer> tasks;
    private List<Integer> task_resources;

    //Map<Day, ResourcesUsed>
    private Map<Integer, int[]> mapResources = new HashMap<Integer, int[]>();

    //Map<task, day>
    private Map<Integer, Integer> mapDayStart = new HashMap<Integer, Integer>();

    public TaskService(List<Node> nodeList, List<Integer> tasks, List<Integer> task_resources) {
        this.nodeList = nodeList;
        this.tasks = tasks;
        this.task_resources = task_resources;
    }

    public void init(){


            try {
                for (int i: tasks) {
                    arrayWork(i);
                }

                System.out.println(mapResources.size() + " : " + tasks.toString());

            } catch (NothingToFollowFromException e) {
                System.out.println(e.getMessage());
                //break;
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    private void arrayWork(int currentIndex) throws Exception {

        if (currentIndex != 0){
            int followFrom = -1;
            for (int nodeIndex: tasks){
                if (nodeList.get(nodeIndex).getFollowers().contains(currentIndex)){
                    followFrom = nodeIndex;
                }
                if (followFrom != -1) break;
            }
            if (followFrom == -1) throw new NothingToFollowFromException("Haven't task to follow for task " + currentIndex);

            if (mapDayStart.get(followFrom) == null){
                checkAndInsert( nodeList.get(followFrom).getDuration(), nodeList.get(currentIndex));
            }else {
                checkAndInsert(mapDayStart.get(followFrom) + nodeList.get(followFrom).getDuration(), nodeList.get(currentIndex));
            }
        }else{
            checkAndInsert(0, nodeList.get(currentIndex));
        }
    }



    private void createNewValue(int fromDay){

        int[] data = new int[task_resources.size()];
        Arrays.fill(data, 0);
        mapResources.put(fromDay, data);

    }

    private void checkAndInsert(int fromDay, Node node){
        for (int day = fromDay; day<fromDay+node.getDuration(); day++){
            boolean okFlag = true;
            if (mapResources.get(fromDay) != null){
                for (int j=0; j<task_resources.size(); j++){
                    if (mapResources.get(day) == null) createNewValue(day);
                    if ( mapResources.get(day)[j]
                            + node.getNeededResources().get(j) >
                            task_resources.get(j) ){
                        okFlag = false;
                        break;
                    }
                }
            }else{
                createNewValue(day);
            }
            if (!okFlag){
                checkAndInsert(++day, node);
                return;
            }
        }
        fillValue(fromDay, node);
    }

    private void fillValue(int fromDay, Node node){
        for (int day = fromDay; day<fromDay+node.getDuration();day++){
            for (int j=0; j<task_resources.size(); j++){
                mapResources.get(day)[j] += node.getNeededResources().get(j);
            }
        }
        mapDayStart.put(nodeList.indexOf(node), fromDay);
    }



}
