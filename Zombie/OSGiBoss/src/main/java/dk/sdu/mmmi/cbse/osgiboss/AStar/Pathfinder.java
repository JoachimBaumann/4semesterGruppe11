package dk.sdu.mmmi.cbse.osgiboss.AStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Pathfinder {

    public Pathfinder(NavigationTiledMapLayer navLayer) {
        this.navLayer = navLayer;
    }

    NavigationTiledMapLayer navLayer;

    PriorityQueue<GridCell> open = new PriorityQueue<>(); // Priority queue is like a heap q. Using normal ArrayList will mean that every pop, we have to scan the WHOLE list. See https://stackoverflow.com/questions/60853524/astar-pathfinding-visualization-is-incredibly-slow-python
    PriorityQueue<GridCell> closed = new PriorityQueue<>();


    public List<GridCell> findPath(GridCell startloc, GridCell endLoc) {
        open.clear();



        startloc.setF(0f); // start Location is 0 cost as we're already there
        open.add(startloc);

        while (!open.isEmpty()) {
            GridCell currentNode = open.poll();

            List<GridCell> children = navLayer.getNeighbors(currentNode);

            for (GridCell child : children) {

                if(child.getParent() == null) {
                    child.setParent(currentNode);
                }


                // If the child is the goal then stop the search
                if (child.getX() == endLoc.getX() && child.getY() == endLoc.getY()) {
                    List<GridCell> path = new ArrayList<>();
                    startloc.setParent(null);
                    path.add(child); // Add end location too. Can be ommitted
                    GridCell current = currentNode;
                    while (current != null) {
                        startloc.setParent(null);
                        path.add(current);
                        current = current.getParent();
                    }
                    Collections.reverse(path); // We have to reverse the path

                    return path;
                } else {


                    // Manhanttendistance to calculate Heuristic
                    child.setH(navLayer.calculate(child,endLoc));
                    child.setG(navLayer.getMovementCost(child,currentNode));
                    child.setF(child.getG() + child.getH());



                    if (open.contains(child)) {
                        continue;
                    }
                    if (closed.contains(child)) {
                        continue;
                    } else {
                        open.offer(child);
                    }
                }
            }
            closed.offer(currentNode);
        }
        return null;
    }

    // If a node with position already exists in the open list, and it has a lower f score, then there is no reason to expand that node
    private boolean skipChild(GridCell node, PriorityQueue<GridCell> open) {
        double nodeF = node.getF();
        for (GridCell openNode : open) {
            if (node.getX() == openNode.getX() && node.getY() == openNode.getY()) {
                if (openNode.getF() < node.getF()) {
                    return true;
                }
            }
        }
        return false;
    }
}
