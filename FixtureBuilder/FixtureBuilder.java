import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FixtureBuilder {
    List<String> teamList = new LinkedList<>();

    public void leagueFixturePrint() {
        if (teamList.size() % 2 != 0) {
            teamList.add("Bay");
        }
        if (teamList.size() != 0) {
            System.out.println("Takımlar: ");
            for (String team : teamList) {
                System.out.println("-" + team);
            }
        } else {
            System.out.println("Listede Takım Bulunamadı.");
        }
        round();
    }

    private void round() {
        List<String> teamListWeek = new LinkedList<>();
        int round = 2 * (teamList.size() - 1);
        List<String> teamListTemp = new LinkedList<>(teamList);
        int value = (teamList.size() / 2);

        for (int i = 1; i < round / 2 + 1; i++) {
            for (int j = 0; j <= value + 2; j += 2) {
                teamListWeek.add(teamListTemp.get(j) + " vs " + teamListTemp.get(j + 1));
            }
            teamListTemp.add(1, teamListTemp.get(value));
            teamListTemp.add(value, teamListTemp.get(teamListTemp.size() - 1));
            teamListTemp.remove(teamListTemp.size() - 1);
            teamListTemp.remove(teamListTemp.size() - 2);
        }
        for (int i = round / 2 + 2; i <= round + 1; i++) {
            for (int j = 0; j <= value + 2; j += 2) {
                teamListWeek.add(teamListTemp.get(j + 1) + " vs " + teamListTemp.get(j));
            }
            teamListTemp.add(1, teamListTemp.get(value));
            teamListTemp.add(value, teamListTemp.get(teamListTemp.size() - 1));
            teamListTemp.remove(teamListTemp.size() - 1);
            teamListTemp.remove(teamListTemp.size() - 2);
        }
        int count = 0;
        int num;
        for (int i = 1; i <= round; i++) {
            System.out.println("-----------------------------------\nRound " + i);
            List<String> temp = new LinkedList<>();
            for (int j = count; j < count + value; j++) {
                temp.add(teamListWeek.get(j));
            }
            for (int k = 0; k < value; k++) {
                num = new Random().nextInt(temp.size());
                System.out.println(temp.get(num));
                temp.remove(num);
            }
            count += value;
        }
    }

}
