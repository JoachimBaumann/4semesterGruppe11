package dk.sdu.mmmi.cbse.osgiboss.AStar;

public class GridCell implements Comparable<GridCell>{
    public int x;
    public int y;


    // Pathfinding stuff
    private float f, g, h;
    private boolean isWalkable;
    private GridCell parent;

    public void setParent(GridCell parent) {
        this.parent = parent;
    }

    public GridCell() {
    }

    public GridCell(int x, int y) {
        this(x, y, true);
    }

    public GridCell(int x, int y, boolean isWalkable) {
        this.y = y;
        this.x = x;
        this.isWalkable = isWalkable;
    }

    public GridCell(boolean isWalkable) {
        this.isWalkable = isWalkable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getF() {
        return f;
    }

    public float getG() {
        return g;
    }

    public float getH() {
        return h;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public GridCell getParent() {
        return parent;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setF(float f) {
        this.f = f;
    }

    public void setG(float g) {
        this.g = g;
    }

    public void setH(float h) {
        this.h = h;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    @Override
    public int compareTo(GridCell node) {
        return (int) Math.round((this.g + this.h) - (node.g + node.h));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridCell node = (GridCell) o;
        return this.getX() == node.getX() && this.getY() == node.getY() && node.f < this.f;
    }
}
