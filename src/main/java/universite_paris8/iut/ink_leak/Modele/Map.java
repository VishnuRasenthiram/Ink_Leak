package universite_paris8.iut.ink_leak.Modele;

public class Map {

    private int[][] map;
    private int numMap;

    public Map(){
        this.map=mapDeDepart();
        this.numMap = 0;

    }
    public int getLargeur() {
        return this.map.length;
    }

    public int getHauteur() {
        return map[0].length;
    }
    public int[][] getMap(){
        return this.map;
    }
    public int getNumMap(){
        return this.numMap;
    }
    public int[][] getNontraversable(){
        return this.map;
    }
    public void setMap(int numMap){
        switch (numMap){
            case 1:
                this.map=map1();
                this.numMap = 1;
                break;
            case 2:
                this.map=map2();
                this.numMap = 2;
                break;

            case 3:
                this.map=map3();
                this.numMap = 3;
                break;
            case 4:
                this.map=map4();
                this.numMap = 4;
                break;
            default:
                this.map=mapDeDepart();
                this.numMap = 0;

                break;
        }
    }

    public int getMap(int x, int y){
        return this.map[y][x];
    }
    public void setMap(int x, int y,int val){
        this.map[y][x]=val;
    }
    private int[][] mapDeDepart(){

        return new int[][]{{17,17,17,20,0,0,0,0,0,8,11,11,11,11,17,17,9,0,0,0},
                {17,17,17,20,0,0,0,0,0,0,0,0,0,0,21,20,0,0,15,11},
                {18,23,23,14,0,0,0,0,0,0,0,0,0,0,21,20,0,0,12,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,20,0,0,12,0},
                {0,0,0,0,8,11,11,11,11,11,11,11,11,11,18,14,0,0,12,0},
                {10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0},
                {12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {17,16,0,0,0,0,8,11,11,11,11,11,11,11,11,16,0,0,0,0},
                {17,20,0,0,0,0,0,0,0,0,0,0,0,0,0,13,11,16,0,0},
                {17,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0},
                {17,14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0},
                {14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0},
                {0,0,0,15,16,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0},
                {11,11,11,17,17,11,11,11,11,11,11,11,11,11,11,11,11,14,0,0},
                {0,0,0,13,14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,11,16,0,10,0},
                {11,11,16,0,0,0,0,0,0,0,0,10,0,0,0,0,12,0,12,0},
                {0,0,12,0,0,0,0,0,0,0,0,12,0,0,10,0,7,0,7,0},
                {0,0,12,0,0,0,10,0,0,15,19,20,0,0,12,0,0,0,0,0},
                {0,0,12,0,0,0,12,6,6,21,17,20,0,0,12,0,10,0,0,0}};
    }
    private int[][] map1(){

        return new int[][]{{0,0,0,0,0,0,17,17,17,17,17,17,17,17,0,0,0,0,0,0},
                {0,0,0,17,17,17,0,0,0,0,0,0,0,0,17,17,17,0,0,0},
                {0,0,17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,17,0,0},
                {0,0,17,0,0,0,0,0,0,1,1,0,0,0,0,0,0,17,0,0},
                {0,17,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,17,0},
                {0,17,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,17,0},
                {0,17,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,17,0},
                {17,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,17},
                {17,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,17},
                {17,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,17},
                {17,1,1,0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,1,17},
                {17,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,17},
                {17,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,17},
                {0,17,17,0,0,0,0,1,0,0,0,0,1,0,0,0,0,17,17,0},
                {0,0,17,0,0,0,0,1,0,0,0,0,1,0,0,0,0,17,0,0},
                {0,0,17,0,0,0,0,1,0,0,0,0,1,0,0,0,0,17,0,0},
                {0,0,0,17,0,0,0,0,0,0,0,0,0,0,0,0,17,0,0,0},
                {0,0,0,17,0,0,0,0,0,0,0,0,0,0,0,0,17,0,0,0},
                {0,0,0,0,17,0,0,0,0,0,0,0,0,0,0,17,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6}};
    }

    private int[][] map2(){

        return new int[][]{
                {18,18,18,18,18,17,18,18,18,18,18,18,18,18,18,18,17,17,17,17},
                {6,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,12,3,0,21},
                {6,0,0,0,0,12,0,10,0,15,11,11,11,11,9,0,21,9,0,21},
                {20,0,0,10,0,12,0,12,0,12,0,0,0,0,0,3,12,3,0,21},
                {20,0,0,13,11,14,0,12,0,12,0,15,11,11,19,11,17,9,0,21},
                {20,0,0,0,0,0,0,12,0,12,0,7,0,0,12,0,7,0,0,21},
                {20,0,0,8,11,19,11,14,0,12,0,0,0,8,20,0,0,0,0,21},
                {20,0,0,0,0,12,0,0,0,0,0,0,10,0,12,0,8,11,11,17},
                {17,11,11,9,0,12,0,15,11,11,11,11,14,0,7,0,0,0,3,21},
                {20,0,0,0,0,12,0,12,0,0,0,0,0,0,0,0,10,0,0,21},
                {20,0,8,11,11,20,0,12,0,17,9,0,17,17,17,0,12,3,0,21},
                {20,0,0,0,3,12,0,12,0,12,0,0,0,12,0,0,13,11,11,17},
                {20,0,15,18,18,14,0,12,0,12,0,15,19,20,0,0,0,0,0,21},
                {20,3,12,0,0,0,0,12,0,12,3,12,3,7,0,8,16,0,0,21},
                {17,11,14,0,15,11,11,14,0,17,17,17,0,0,0,3,12,0,0,21},
                {20,0,0,0,12,3,0,0,0,0,0,0,8,11,19,11,14,0,0,21},
                {20,0,10,0,21,11,9,0,15,9,0,0,0,0,12,0,0,0,0,21},
                {20,0,13,11,14,0,0,0,7,0,8,19,9,0,12,0,8,11,11,18},
                {20,0,0,0,0,0,0,0,0,0,0,12,3,0,12,0,0,0,0,6},
                {17,19,19,19,19,19,19,19,19,19,19,17,19,19,17,19,19,19,0,0}
        };
    }
    private int[][] map3(){

        return new int[][]{
                {2,1,1,2,2,1,1,1,2,1,1,1,4,1,1,2,2,2,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,2,1,1,1},
                {1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1},
                {1,1,1,2,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {3,1,1,1,1,1,1,2,1,1,3,1,1,1,1,1,1,1,1,1},
                {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1},
                {1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1},
                {1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {2,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1},
                {1,1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1},
                {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1}


        };
    }
    private int[][] map4(){

        return new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
                {0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1}
        };
    }


}
