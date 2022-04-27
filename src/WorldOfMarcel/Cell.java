package WorldOfMarcel;

public class Cell {

    int ox = 0;
    int oy = 0;

    enum Type{
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }

    CellElement element;
    boolean visited = false;

    public Cell(CellElement element, int ox, int oy){
        this.element = element;
        this.ox = ox;
        this.oy = oy;
    }
}
