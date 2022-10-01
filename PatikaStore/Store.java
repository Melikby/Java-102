import java.util.Scanner;

public class Store {
    Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("PatikaStore Ürün Yönetim Paneli !");
            System.out.println(" 1 - Notebook İşlemleri\n" +
                    " 2 - Cep Telefonu İşlemleri\n" +
                    " 3 - Marka Listele\n" +
                    " 4 - Çıkış Yap");
            System.out.print("Yapmak istediğiniz işlem numarasını seçiniz : ");

            switch (scanner.nextInt()) {
                case 1:
                    Notebook notebook=new Notebook();
                    notebook.menu();
                    break;
                case 2:
                    MobilePhone mobilePhone=new MobilePhone();
                    mobilePhone.menu();
                    //cep teledonu
                    break;
                case 3:
                    Brand.printBrands();
                    break;
                case 4:
                    System.out.println("Çıkış yapılıyor.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Yanlış değer girdiniz.");
                    break;
            }
        }
    }
}