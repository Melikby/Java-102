public class Exit extends NormalLocation{
    public Exit(Player player) {
        super(0, player, "Çıkış");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Çıkış Yapıldı");
        return true;
    }
}