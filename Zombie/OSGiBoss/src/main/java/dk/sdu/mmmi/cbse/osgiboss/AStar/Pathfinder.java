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
        if (!navLayer.isWalkable(startloc)) {
            System.out.println("Error in startlocation, not walkable");
        }
        if (!navLayer.isWalkable(endLoc)) {
            System.out.println("Error in endlocation, not walkable");
        }



        open.clear();
        closed.clear();


        startloc.setF(0f); // start Location is 0 cost as we're already there
        open.add(startloc);

        while(!open.isEmpty())

    {
        GridCell currentNode = open.poll();
        closed.offer(currentNode);

        if (currentNode == endLoc) {
            return null;
        }

        List<GridCell> children = navLayer.getNeighbors(currentNode);

        for (GridCell child : children) {

            // If the child is the goal then stop the search
            if (child.getX() == endLoc.getX() && child.getY() == endLoc.getY()) {
                List<GridCell> path = new ArrayList<>();
                startloc.setParent(null);
                path.add(child); // Add end location too. Can be ommitted
                GridCell current = currentNode;
                while (current != null) {
                    //                    startloc.setParent(null);
                    path.add(current);
                    current = current.getParent();
                }
                Collections.reverse(path); // We have to reverse the path

                return path;
            }

            if (!closed.contains(child)) {
                child.setG(child.getG() + navLayer.calculate(child, currentNode));
                child.setH(navLayer.calculate(child, endLoc));


                float tempGscore = currentNode.getG() + child.getG();

                if (!open.contains(child)) {
                    child.setG(tempGscore);
                    child.setF(child.getH() + child.getG());
                    child.setParent(currentNode);
                    open.offer(child);
                } else {
                    if (tempGscore < child.getG()) {
                        child.setG(tempGscore);
                        child.setF(navLayer.calculate(child, endLoc) + child.getG());
                        child.setParent(currentNode);
                    }
                }
            }

        }
    }
        return null;
}
}
