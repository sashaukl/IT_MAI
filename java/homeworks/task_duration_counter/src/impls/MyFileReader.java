package impls;

import models.Node;

import java.util.List;

public interface MyFileReader {
    List<Node> read(String filename);
    List<Integer> getLastResources();
}
