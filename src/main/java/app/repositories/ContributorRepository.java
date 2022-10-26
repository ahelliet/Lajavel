package app.repositories;

import app.entities.Contributor;
import java.util.List;

public class ContributorRepository {
    private static ContributorRepository instance;
    private List<Contributor> data;

    private ContributorRepository() {
        Contributor contributor = new Contributor("https://avatars.dicebear.com/api/adventurer/damien.svg", "Damien");
        Contributor contributor1 = new Contributor("https://avatars.dicebear.com/api/micah/stephane.svg", "Stephane");
        Contributor contributor2 = new Contributor("https://avatars.dicebear.com/api/adventurer/jeremy.svg", "Jeremy");
        Contributor contributor3 = new Contributor("https://avatars.dicebear.com/api/adventurer/bruno.svg", "Bruno");
        Contributor contributor4 = new Contributor("https://avatars.dicebear.com/api/adventurer/henri.svg", "Henri");
        Contributor contributor5 = new Contributor("https://avatars.dicebear.com/api/adventurer/benjamin.svg", "Benjamin");

        this.data = List.of(contributor, contributor1, contributor2, contributor3, contributor4, contributor5);
    }

    public static ContributorRepository getInstance() {
        if (instance == null) {
            instance = new ContributorRepository();
        }
        return instance;
    }

    public static List<Contributor> findAll() {
        return ContributorRepository.getInstance().data;
    }

    public static Contributor findOneByName(String query) {
        return ContributorRepository.getInstance().data.stream()
                .filter(contributor -> contributor.name.equals(query))
                .findFirst()
                .orElse(null);
    }
}
