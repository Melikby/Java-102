import java.util.ArrayList;

public class Notebook extends Product{
    private static int nId=1;
    private static ArrayList<Notebook> notebooks=new ArrayList<>();


    public Notebook(String name, double price, int discountRate, int amount, Brand brand, double screenSize, int ram, int memory) {

        super(nId,name, price, discountRate, amount, brand, screenSize, ram, memory);
        nId++;
    }

    public Notebook(){

    }

    @Override
    public void menu() {

        System.out.println("1- Yeni Notebook ekle\n" +
                "2- Notebook listesini görüntüle\n" +
                "3- Notebook sil\n" +
                "4- Notebookları id numaralarına göre sırala\n" +
                "5- Notebookları markaya göre filtrele");
        System.out.print("Yapmak istediğiniz işlemi seçiniz : ");
        int value=scanner.nextInt();
        if(value==1) addItem();
        if(value==2 || value==4) getProducts();
        if(value==3) deleteItem();
        if(value==5) brandFilter();

    }

    @Override
    public void addItem() {
        System.out.print("Ürünün fiyatı : ");
        double price= scanner.nextDouble();
        System.out.print("Ürünün adı : ");
        scanner.nextLine();
        String name=scanner.nextLine();
        System.out.print("Ürünün indirim oranı : ");
        int discountRate= scanner.nextInt();
        System.out.print("Ürünün stoğu : ");
        int amount= scanner.nextInt();
        System.out.print("Ürünün rami : ");
        int ram= scanner.nextInt();
        System.out.print("Ürünün ekranı : ");
        double screenSize= scanner.nextDouble();
        System.out.print("Ürünün hafızası :");
        int memory= scanner.nextInt();
        //System.out.println("Ekran Listesi");
        Brand.printBrands();
        System.out.print("Marka Seçiniz : ");
        Brand brand=Brand.getBrand(scanner.nextInt());
        Notebook notebook=new Notebook(name,price,discountRate,amount,brand,screenSize,ram,memory);
        this.notebooks.add(notebook);
        System.out.println("Eklenen notebook'un id'si :"+notebook.getId());
    }

    @Override
    public void getProducts() {
        print(null);

    }

    public void print(ArrayList<Notebook> notebookList){
        if(notebookList==null) notebookList=this.notebooks;
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID | Ürün Adı                  | Fiyatı          | Markası         | Stoğu        | İndirim Oranı      | RAM    | Ekran Boyutu      | Hafızası   |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Notebook n:notebookList){
            System.out.printf("| %-2s | %-25s | %-15s | %-15s | %-12s | %-18s | %-6s | %-17s | %-10s | \n",
                    n.getId(),n.getName(),n.getPrice(),n.getBrand().getName(),n.getAmount(),
                    n.getDiscountRate(),n.getRam(),n.getScreenSize(),n.getMemory());
        }
    }

    @Override
    public void deleteItem(){
        getProducts();
        System.out.print("Silinmesini istediğiniz notebook'un Id numarasını giriniz : ");
        int id=scanner.nextInt();
        notebooks.remove(id-1);
        System.out.println("Güncel notebook listesi ");
        getProducts();
    }

    private void brandFilter() {
        scanner.nextLine();
        System.out.print("Filtrelemek istediğiniz ürün markasını giriniz :");
        String filter= scanner.nextLine();
        ArrayList<Notebook> filterNotebooks=new ArrayList<>();
        for (Notebook n:notebooks){
            if(filter.equals(n.getBrand().getName())){
                filterNotebooks.add(n);
            }
        }
        print(filterNotebooks);
    }

    public static int getnId() {
        return nId;
    }
}