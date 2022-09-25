public class ToolsStore extends NormalLocation{
    public ToolsStore(Player player) {
        super(2, player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("---- Mağazaya Hoşgeldiniz ----");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("0 - Çıkış Yap");
            System.out.println();
            System.out.print("Hangi kategoriyi seçmek istersiniz? : ");
            int selectCase = Location.scanner.nextInt();
            while(selectCase < 1 || selectCase >3){
                System.out.print("Geçersiz değer' Tekrar giriniz: ");
                selectCase = scanner.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    showMenu =false;
                    break;
                case 2:
                    printArmor();
                    showMenu =false;
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz");
                    showMenu =false;
                    break;
                default:
                    System.out.println("Silahlar seçildi");
                    printWeapon();
                    showMenu =false;
                    break;
            }
        }
        return true;
    }

    private void printWeapon() {
        System.out.println("--- Silahlar ---");
        for (Weapon weapon:Weapon.weapons()) {
            System.out.println(weapon.getId() + " - " + weapon.getName() + " : "
                    + " Fiyat: " + weapon.getPrice()
                    + " Hasar: " + weapon.getDamage());
        }
        System.out.println("0 - Çıkış Yap");
    }
    private void buyWeapon(){
        System.out.print("Silah seçiniz: ");
        int selectWeapon = scanner.nextInt();
        while(selectWeapon < 0 || selectWeapon > Weapon.weapons().length){
            System.out.print("Geçersiz değer. Tekrar giriniz: ");
            selectWeapon = scanner.nextInt();
        }


        if(selectWeapon != 0){
            Weapon selectedWeaponId = Weapon.getWeaponById(selectWeapon);

            if( selectedWeaponId != null) {
                if (selectedWeaponId.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız yok!");
                } else {
                    // satın alma işlemi gerçekleştiği alan
                    System.out.println(selectedWeaponId.getName() + " satın aldınız");
                    int balance = this.getPlayer().getMoney() - selectedWeaponId.getPrice();
                    System.out.println("Kalan paranız: " + balance);
                    System.out.println("Önceki silahınız: "
                            + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeaponId);
                    System.out.println("Yeni silahınız: "
                            + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().setMoney(balance);
                }
            }
        }
    }
    private void printArmor() {
        System.out.println("--- Zırhlar ---");
        for (Armor armor:Armor.armors()) {
            System.out.println(armor.getId() + " - " + armor.getName()
                    + " Blok: " + armor.getBlock()
                    + " Fiyat: " + armor.getPrice());
        }
        System.out.println("0 - Çıkış Yap");
        System.out.println();
        System.out.println(" Zırh seçiniz: ");
        int selectArmor = scanner.nextInt();
        while (selectArmor< 0  || selectArmor > Armor.armors().length){
            System.out.print("Geçersiz değer. Tekrar giriniz: ");
            selectArmor = scanner.nextInt();
        }
        Armor selectedArmor = Armor.getArmorById(selectArmor);

        if(selectedArmor != null){
            if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız yok!");
            } else{
                System.out.println(selectedArmor.getName() + " satın aldınız");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                System.out.println("Kalan paranız: " + balance);
                this.getPlayer().setMoney(balance);
                //19.12
            }
        }
    }
}