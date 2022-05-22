package dk.sdu.mmmi.cbse.osgiboss.AStar;

import com.badlogic.gdx.maps.MapLayer;

import java.util.ArrayList;
import java.util.List;

public class NavigationTiledMapLayer extends MapLayer {

    private final boolean allowDiagonal = true;
    private boolean dontCrossCorners = true;

    private GridCell[][] nodes;
    private List<GridCell> neighbors = new ArrayList<>();

    protected int width;
    protected int height;

    public NavigationTiledMapLayer(GridCell[][] nodes) {
        this.nodes = nodes;
    }

    public void setCell(int x, int y, GridCell cell) {
        if (this.contains(x, y))
            nodes[x][y] = cell;
    }


    public List<GridCell> getNeighbors(GridCell node) {
        int yDir = 1;
        int x = node.getX(), y = node.getY();
        neighbors.clear();
        boolean s0 = false, d0 = false, s1 = false, d1 = false,
                s2 = false, d2 = false, s3 = false, d3 = false;

        // up
        if (isWalkable(x, y + yDir)) {
            neighbors.add(nodes[x][y + yDir]);
            s0 = true;
        }
        // right
        if (isWalkable(x + 1, y)) {
            neighbors.add(nodes[x + 1][y]);
            s1 = true;
        }
        // down
        if (isWalkable(x, y - yDir)) {
            neighbors.add(nodes[x][y - yDir]);
            s2 = true;
        }
        // left
        if (isWalkable(x - 1, y)) {
            neighbors.add(nodes[x - 1][y]);
            s3 = true;
        }

        if (!allowDiagonal) {
            return neighbors;
        }

        if (dontCrossCorners) {
            d0 = s3 && s0;
            d1 = s0 && s1;
            d2 = s1 && s2;
            d3 = s2 && s3;
        } else {
            d0 = s3 || s0;
            d1 = s0 || s1;
            d2 = s1 || s2;
            d3 = s2 || s3;
        }

        // up left
        if (d0 && this.isWalkable(x - 1, y + yDir)) {
            neighbors.add(nodes[x - 1][y + yDir]);
        }
        // up right
        if (d1 && this.isWalkable(x + 1, y + yDir)) {
            neighbors.add(nodes[x + 1][y + yDir]);
        }
        // down right
        if (d2 && this.isWalkable(x + 1, y - yDir)) {
            neighbors.add(nodes[x + 1][y - yDir]);
        }
        // down left
        if (d3 && this.isWalkable(x - 1, y - yDir)) {
            neighbors.add(nodes[x - 1][y - yDir]);
        }


        return neighbors;
    }


    public float getMovementCost(GridCell node1, GridCell node2) {
        if (node1 == node2)
            return 0;

        GridCell cell1 = (GridCell) node1, cell2 = (GridCell) node2;
        return cell1.x == cell2.x || cell1.y == cell2.y ?
                10 : 14;


    }


    public float calculate(GridCell from, GridCell to) {
        GridCell c1 = (GridCell) from, c2 = (GridCell) to;

        return (Math.abs(c2.x - c1.x) + Math.abs(c2.y - c1.y));
    }


    public boolean isWalkable(GridCell node) {
        GridCell c = (GridCell) node;
        return isWalkable(c.x, c.y);
    }

    public GridCell getCell(int x, int y) {
        return contains(x, y) ? this.nodes[x][y] : null;
    }

    public boolean contains(int x, int y) {
        return (x >= 0 && x < this.width) && (y >= 0 && y < this.height);
    }


    public void setWalkable(int x, int y, boolean walkable) {
        this.nodes[x][y].setWalkable(walkable);
    }


    public boolean isWalkable(int x, int y) {
        return this.nodes[x][y].isWalkable();
    }


    public GridCell[][] getNodes() {
        return this.nodes;
    }


    public void setNodes(GridCell[][] nodes) {
        if (nodes != null) {
            this.width = nodes.length;
            this.height = nodes[0].length;


            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    nodes[x][y].setX(x);
                    nodes[x][y].setY(y);
                }
            }

        } else {
            this.width = 0;
            this.height = 0;
        }

        this.nodes = nodes;
    }


    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}