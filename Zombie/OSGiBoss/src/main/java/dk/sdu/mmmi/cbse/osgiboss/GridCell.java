package dk.sdu.mmmi.cbse.osgiboss;

public class GridCell {
    public int x;
    public int y;


    // Pathfinding stuff
    private float f, g, h;
    private boolean isWalkable;
    private GridCell parent;

    // Binary Tree
    private int index;

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

    public int getIndex() {
        return index;
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

    public void setIndex(int index) {
        this.index = index;
    }
}
