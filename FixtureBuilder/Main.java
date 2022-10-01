public class Main {
    public static void main(String[] args) {
        FixtureBuilder fixtureBuilder = new FixtureBuilder();

        fixtureBuilder.teamList.add("Fenerbahçe");
        fixtureBuilder.teamList.add("Galatasaray");
        fixtureBuilder.teamList.add("Beşiktaş");
        fixtureBuilder.teamList.add("Trabzonspor");
        fixtureBuilder.teamList.add("Konyaspor");
        fixtureBuilder.teamList.add("Adana Demirspor ");
        fixtureBuilder.teamList.add("Başakşehir");
        fixtureBuilder.teamList.add("Kayserispor");

        fixtureBuilder.leagueFixturePrint();
    }
}
