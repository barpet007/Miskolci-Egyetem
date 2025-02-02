package hu.uni.miskolc.ai.searchalgorithms.model;

import java.util.Comparator;
import java.util.List;

public class PathComparator implements Comparator<Path> {

    @Override
    public int compare(Path path1, Path path2) {
    	if(path1 == null || path2 == null) {
    		return -1;
    	}
    	
        List<Node> nodes1 = path1.getNodes();
        List<Node> nodes2 = path2.getNodes();

        int sizeComparison = Integer.compare(nodes1.size(), nodes2.size());
        if (sizeComparison != 0) {
            return sizeComparison;
        }

        for (int i = 0; i < nodes1.size(); i++) {
            int nodeComparison = nodes1.get(i).getName().compareTo(nodes2.get(i).getName());
            if (nodeComparison != 0) {
                return nodeComparison;
            }
        }

        return 0;
    }
}
