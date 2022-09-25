import java.nio.file.LinkPermission;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private String charName;
    private int damage;
    private int health;
    private int orijinalHealth;
    private int money;
    private Inventory inventory;
    private Location location = null;
    private Scanner input = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        Character[] characters = {new Samurai(), new Archer(),new Knight()};
        for (Character character:characters) {
            System.out.println("ID: " + character.getId()
                    + "\t Karakter: " + character.getName()
                    + "\t Hasar: " + character.getDamage()
                    + "\t Sağlık: " + character.getHealth()
                    + "\t Para: " + character.getMoney());
        }

        System.out.print("Hangi karakter ile oynamak istersin: ");
        int selectedChar = input.nextInt();
        switch (selectedChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
                break;
        }
        System.out.println("Karakter: " + this.getCharName() + " Hasar: " + this.getDamage()
                + " Sağlık: " + this.getHealth() + " Para: " + this.getMoney());
    }

    private void initPlayer(Character character) {
        this.setCharName(character.getName());
        this.setDamage(character.getDamage() + this.getDamage());
        this.setHealth(character.getHealth());
        this.setOrijinalHealth(character.getHealth());
        this.setMoney(character.getMoney());
    }

    public void selectLocation(){
        while(true) {
            System.out.println();
            System.out.println("----------------------------------------------------------");
            System.out.println("Şimdi de bir bölge seç! ");
            System.out.println("Bölgeler: ");
            Location[] locations = {new SafeHouse(this), new ToolsStore(this),
                    new Cave(this), new Forest(this),
                    new River(this), new Quarry(this), new Exit(this)};
            for (Location locationn : locations) {
                System.out.println(locationn.getId() + ". bölge: " + locationn.getName());
            }
            System.out.print("Lütfen seçimi yapınız: ");
            int noLoc = input.nextInt();

            switch (noLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(this);
                    break;
                case 2:
                    location = new ToolsStore(this);
                    break;
                case 3:
                    location = new Cave(this);
                    break;
                case 4:
                    location = new Forest(this);
                    break;
                case 5:
                    location = new River(this);
                    break;
                case 6:
                    Quarry quarry = new Quarry(this);
                    location = quarry;
                    break;
                default:
                    System.out.println("Geçerli bölge değil! Güvenli bölgeye gidiliyor!");
                    location = new SafeHouse(this);
                    break;
            }
            if(location == null){
                System.out.println("Oyundan çıkılıyor ...");
                break;
            }
            else if(!location.onLocation()){
                System.out.println("GAME OVER");
                break;
            }
        }
    }
    public void isDelete(int choose){
        if(location.isSelectCombatValue()){
            System.out.println("Bu bölgedeki tüm yaratıklar öldü lütfen başka bir bölge seçin!");

        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int gender) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }
    public Armor getArmor(){
        return this.getInventory().getArmor();
    }

    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }

    public void awardForQarry(int possib){
        Weapon[] weapons =
                {

                        Weapon.getWeaponById(3),
                        Weapon.getWeaponById(3),
                        Weapon.getWeaponById(2),
                        Weapon.getWeaponById(2),
                        Weapon.getWeaponById(2),
                        Weapon.getWeaponById(1),
                        Weapon.getWeaponById(1),
                        Weapon.getWeaponById(1),
                        Weapon.getWeaponById(1),
                        Weapon.getWeaponById(1)
                };
        Armor[] armors =
                {
                        Armor.getArmorById(3),
                        Armor.getArmorById(3),
                        Armor.getArmorById(2),
                        Armor.getArmorById(2),
                        Armor.getArmorById(2),
                        Armor.getArmorById(1),
                        Armor.getArmorById(1),
                        Armor.getArmorById(1),
                        Armor.getArmorById(1),
                        Armor.getArmorById(1)
                };

        int money[] = {1,1,1,1,1,5,5,5,10,10};

        Random random = new Random();
        int possRn = possib;
        int percent;
        if(possRn < 3){
            percent = random.nextInt(10);
            Weapon weapon = weapons[percent];
            System.out.println("Tbrikler! Silah kazandınız.");
            System.out.println("Önceki silahınız: " + this.getWeapon().getName());
            this.getInventory().setWeapon(weapon);
            System.out.println("Yeni silahınız: " + this.getWeapon().getName());

        }
        else if(2<possRn  || possRn < 6){
            percent = random.nextInt(10);
            Armor armor = armors[percent];
            System.out.println("Terikler! Zırh kazandınız.");
            System.out.println("Önceki silahınız: " + this.getArmor().getName());
            this.getInventory().setArmor(armor);
            System.out.println("Yeni silahınız: " + this.getArmor().getName());
        }
        else if (5 < possRn || possRn<11){
            percent = random.nextInt(10);
            int gain = money[percent];
            System.out.println("Tebrikler! Altın kazandınız.");
            System.out.println("Önceki paranız: " + this.getMoney() + " altın");
            this.setMoney(this.getMoney() + gain);
            System.out.println("yeni paranız: " + getMoney() + " altın");
        }
        else{
            System.out.println("Malesef hiçbir şey kazanamadınız!");
        }
    }
}