package models;

import java.util.ArrayList;
import java.util.List;

public class NodeConverter {
    Node convert(String string, int resourcesCount){
        String[] vals = string.trim().split(" +");
        for (String val: vals){
            val.trim();
        }
        int duration = Integer.parseInt(vals[0]);
        List<Integer> resources = new ArrayList();
        int i=1;
        for(; i<=resourcesCount; i++){
            resources.add(Integer.parseInt(vals[i]));
        }
        i++;
        List<Integer> followers  = new ArrayList();
        for(; i<vals.length; i++){
            followers.add(Integer.parseInt(vals[i]));
        }
        return new Node(duration, resources, followers);
    }



}
