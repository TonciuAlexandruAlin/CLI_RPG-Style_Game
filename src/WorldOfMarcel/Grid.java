package WorldOfMarcel;

import java.util.*;

public class Grid extends ArrayList<ArrayList<Cell>> {
    Random rand = new Random();
    int tableLength = 5;
    int tableWidth = 5;
    public Character Char;
    public Cell current;
    static Grid table = null;

    private Grid(Character Char, Cell current){
        for(int i = 0; i < tableLength; i++){
            this.add(new ArrayList<Cell>(tableWidth));
        }
        this.Char = Char;
        this.current = current;
    }

    public static Grid generateTable(int tableLength, int tableWidth, Character Char, Cell current){

        Random rand = new Random();
        table = new Grid(Char, current);
        for(int i = 0; i < tableLength; i++){
            for(int j = 0; j < tableWidth; j++){
                table.get(i).add(new Cell(new EmptyCell(),i,j));
            }
        }

        table.get(tableLength - 1).set(tableWidth - 1, new Cell(new FinishCell(), tableLength - 1, tableWidth - 1));

        for(int i = 0; i < 2; i++){
            int a = rand.nextInt(tableLength);
            int b = rand.nextInt(tableWidth);
            if(table.get(a).get(b).element.toChar() == 'N' && (a != 0 || b != 0)){
                table.get(a).set(b,new Cell(new Shop(), a, b));
            }
            else{
                i--;
            }
        }
        for(int i = 0; i < 4; i++){
            int a = rand.nextInt(tableLength);
            int b = rand.nextInt(tableWidth);
            if(table.get(a).get(b).element.toChar() == 'N' && (a != 0 || b != 0)){
                table.get(a).set(b,new Cell(new Enemy(), a, b));
            }
            else{
                i--;
            }
        }
        table.get(0).get(0).visited = true;

        return table;
    }

    public static Grid generateHarcoded(int tableLength, int tableWidth, Character Char, Cell current){
        table = new Grid(Char, current);
        for(int i = 0; i < tableLength; i++){
            for(int j = 0; j < tableWidth; j++){
                table.get(i).add(new Cell(new EmptyCell(),i,j));
            }
        }
        table.get(0).get(0).visited = true;
        table.get(0).set(3,new Cell(new Shop(),0,3));
        table.get(1).set(3,new Cell(new Shop(),1,3));
        table.get(2).set(0,new Cell(new Shop(),2,0));
        table.get(3).set(4,new Cell(new Enemy(),3,4));
        table.get(4).set(4,new Cell(new FinishCell(),4,4));
        return table;
    }

    public void printTable(){

        for(int i = 0; i < tableLength; i++){
            for(int j = 0; j < tableWidth; j++){
                if(table.current.ox == i && table.current.oy == j){
                    System.out.print("P" + table.get(i).get(j).element.toChar() + " ");
                }
                else if(table.get(i).get(j).visited == false){
                    System.out.print(" ? ");
                }
                else{
                    System.out.print(" " + table.get(i).get(j).element.toChar() + " ");
                }
            }
            System.out.println();
        }
    }

    public void goNorth(){
        if(table.current.ox == 0){
            System.out.println("Calea aceea este interzisa!");
        }
        else{
            table.current = table.get(current.ox - 1).get(current.oy);
        }
    }

    public void goSouth(){
        if(table.current.ox == tableLength - 1){
            System.out.println("Acolo zace un dragon care nu trebuie trezit!");
        }
        else{
            table.current = table.get(current.ox + 1).get(current.oy);
        }
    }

    public void goEast(){
        if(table.current.oy == tableWidth - 1){
            System.out.println("Nimeni care a mers intr-acolo nu s-a mai intors.");
        }
        else{
            table.current = table.get(current.ox).get(current.oy + 1);
        }
    }

    public void goWest(){
        if(table.current.oy == 0){
            System.out.println("YOU SHALL NOT PASS!");
        }
        else{
            table.current = table.get(current.ox).get(current.oy - 1);
        }
    }

    public static void main(String[] args) {
        Grid table = generateHarcoded(5,5,new Character("gg",2,2),new Cell(new EmptyCell(),0,0));
        table.printTable();
    }
}
