package dk.sdu.mmmi.cbse.osgiboss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AStarFinder {

    private PathFinderOptions defaultOptions;
    BHeap openList;
    public int jobId;

    public List<GridCell> findPath(GridCell startNode, GridCell endNode, NavigationTiledMapLayer graph) {

        validateNotNull(startNode, "Start node cannot be null");
        validateNotNull(endNode, "End node cannot be null");

        if (jobId == Integer.MAX_VALUE)
            jobId = 0;
        int job = ++jobId;

        GridCell node, neighbor;
        List<GridCell> neighbors = new ArrayList<GridCell>();
        float ng;

        startNode.setG(0);
        startNode.setF(0);

        // push the start node into the open list
        openList.clear();
        openList.add(startNode);
        startNode.setParent(null);
        startNode.setOpenedOnJob(job, this.getClass());

        while (openList.size > 0) {

            // pop the position of node which has the minimum 'f' value.
            node = openList.pop();
            node.setClosedOnJob(job, this.getClass());


            // if reached the end position, construct the path and return it
            if (node == endNode) {
                return Util.backtrace(endNode);
            }

            // get neighbors of the current node
            neighbors.clear();
            neighbors.addAll(graph.getNeighbors(node, defaultOptions));
            for (int i = 0, l = neighbors.size(); i < l; ++i) {
                neighbor = neighbors.get(i);

                if (neighbor.getClosedOnJob(this.getClass()) == job || !graph.isWalkable(neighbor)) {
                    continue;
                }

                // get the distance between current node and the neighbor and calculate the next g score
                ng = node.getG() + graph.getMovementCost(node, neighbor, defaultOptions);

                // check if the neighbor has not been inspected yet, or can be reached with smaller cost from the current node
                if (neighbor.getOpenedOnJob(this.getClass()) != job || ng < neighbor.getG()) {
                    float prevf = neighbor.getF();
                    neighbor.setG(ng);

                    neighbor.setH(defaultOptions.heuristic.calculate(neighbor, endNode));
                    neighbor.setF(neighbor.getG() + neighbor.getH());
                    neighbor.setParent(node);

                    if (neighbor.getOpenedOnJob(this.getClass()) != job) {
                        openList.add(neighbor);
                        neighbor.setOpenedOnJob(job, this.getClass());
                    } else {
                        // the neighbor can be reached with smaller cost.
                        // Since its f value has been updated, we have to update its position in the open list
                        openList.updateNode(neighbor, neighbor.getF() - prevf);
                    }
                }
            }
        }

        // fail to find the path
        return null;
    }

    public AStarFinder(Class<GridCell> clazz) {

        openList = new BHeap<GridCell>(new Comparator<GridCell>() {
            @Override
            public int compare(GridCell o1, GridCell o2) {
                if (o1 == null || o2 == null) {
                    if (o1 == o2)
                        return 0;
                    if (o1 == null)
                        return -1;
                    else
                        return 1;

                }
                return (int) (o1.getF() - o2.getF());
            }
        });
    }

    public static List<GridCell> backtrace(GridCell node){
        path.clear();
        path.add(node);
        T node1 = node;
        while (node1.getParent() != null && node1 != node1.getParent()){
            node1 = (T)node1.getParent();
            path.add(0, node1);
        }
        path.remove(0);
        return (List<T>)path;
    }

    public static void validateNotNull(GridCell node, String msg) {
        if (node == null) {
            System.out.println(msg + new NullPointerException());
        }
    }