package test;

import javax.print.attribute.standard.MediaSize.Other;
import java.util.Random;


public class Tile {
    public final char letter;
    public final int score;
    public int Amount;


    private Tile (char L , int V,int Amount){
        this.Value = V;
        this.score = L;
        this.Amount=Amount;
    }

    public bool equals (Object OtherTile){
        if(this == null){
            return false;
        }
        if (this == OtherTile) {
            return true;
        }
        if(this.getClass() != OtherTile.getClass()){
            return false;
        }
        Tile OT= (Tile) OtherTile;
        if(this.letter == OT.letter && this.score==OT.score){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Object.hashCode(letter,score);
    }

    
/*
 * Bag
 */
    public static class Bag {

        private static final int[] SCORES = {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
            1, 1, 1, 1, 4, 4, 8, 4, 10
    };

    private  Tile[] tiles = new Tile[26];
    private  int[] Letters = new int[26];
    private static Bag SingleBag =null;


        private Bag() {
            Letters[0]=9; Letters[1]=2;
            Letters[2]=2; Letters[3]=4;
            Letters[4]=12; Letters[5]=2;
            Letters[6]=3; Letters[7]=2;
            Letters[8]=9; Letters[9]=1;
            Letters[10]=1; Letters[11]=4;
            Letters[12]=2;Letters[13]=6; 
            Letters[14]=8; Letters[15]=2; 
            Letters[16]=1;
            Letters[17]=6; Letters[18]=4;
            Letters[19]=6; Letters[20]=4;
            Letters[21]=2; Letters[22]=2;
            Letters[23]=1; 
            Letters[24]=2; Letters[25]=1;
            
            for (int i = 0; i < 26; i++) {
                tiles[i] = new Tile((char) ('A' + i), SCORES[i],Letters[i]);
            }
        }

        
        public Tile getRand(){
            Random rand = new Random();

            int RandLetter = rand.nextInt(26);
            if(!CheckZeros()){
                return null;
            }
            while (tiles[RandLetter].Amount==0) {
                RandLetter = rand.nextInt(26);
            }
            tiles[RandLetter].Amount = tiles[RandLetter].Amount-1;
            return tiles[RandLetter]; 
        }

        private boolean CheckZeros(){
            for(Tile x : tiles){
                if(x.Amount>0){
                    return true;
                }
            }
            return false;
        }

        public Tile getTile(char TileC){
            if(TileC>'Z' || TileC<'A'){
                return null;
            }
            int index = TileC - 'A' ;
            if(tiles[index].Amount>0){
                tiles[index].Amount = tiles[index].Amount -1 ;
                return tiles[index];  
            }
            return null;
        }

        
        public Tile put(Tile TileC){

            int index = TileC.letter - 'A' ;
            if(tiles[index].Amount<Letters[index]){
                tiles[index].Amount = tiles[index].Amount+1;
                return tiles[index];  
            }
            return null;
        }

        public int size(){
            int totalTiles =0;
            for(Tile T:tiles){
                totalTiles = totalTiles+ T.Amount;
            }
            return totalTiles;
        }

        
        public int[] getQuantities(){
            int [] quantities = new int [26];
            int i=0;
            for(Tile T:tiles){
                quantities[i++] = T.Amount;
            }
            return quantities;
        }

        public static Bag getBag(){
            if(SingleBag==null){
                SingleBag = new Bag();
            }
            return SingleBag;
        }

}

}
