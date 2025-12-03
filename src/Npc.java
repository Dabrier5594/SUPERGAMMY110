public class Npc {
    private String name;
    private String profession;
    private int hp;
    private int armor;


    public Npc(String name, String profession, int hp, int armor){
        this.name = name;
        this.profession = profession;
        this.hp = hp;
        this.armor = armor;

    }


    public static void main(String[] args) {

    }
    public String getName(){return name;}

    public String getProfession(){return profession;}

    public int getHp(){return hp;}

    public int getAmor(){return armor;}

    public void setName(String name){this.name = name;}

    public static void jobs(){

    }

}
