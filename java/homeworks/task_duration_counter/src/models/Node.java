package models;


import java.util.List;

public class Node {
    private int duration;

    private List<Integer> neededResources;
    private List<Integer> followers;

    public Node(int duration, List<Integer> neededResources, List<Integer> followers) {
        this.duration = duration;

        this.neededResources = neededResources;
        this.followers = followers;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public List<Integer> getNeededResources() {
        return neededResources;
    }

    public int getDuration() {
        return duration;
    }
}
