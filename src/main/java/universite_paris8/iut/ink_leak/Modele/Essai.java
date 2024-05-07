package universite_paris8.iut.ink_leak.Modele;

public class Essai {
    public static void main(String[] args) {

        int[][] m={
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,2,0,5,0,0,0},
                {0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };

        Map map=new Map(m);

        System.out.println(""+map.getLargeur()+ map.getLongueur());
    }
}
