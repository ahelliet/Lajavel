package app.repositories;

import app.entities.Partner;

import java.util.List;

public class PartnerRepository {
    private static PartnerRepository instance;
    private List<Partner> data;

    private PartnerRepository() {
        Partner partner = new Partner("Github", "Github");
        Partner partner1 = new Partner("Mr Propre", "Mr Propre");
        Partner partner2 = new Partner("Canard WC", "Canard WC");

        this.data = List.of(partner, partner1, partner2);
    }

    public static PartnerRepository getInstance() {
        if (instance == null) {
            instance = new PartnerRepository();
        }
        return instance;
    }

    public static List<Partner> findAll() {
        return PartnerRepository.getInstance().data;
    }

    public static Partner findOneByName(String query) {
        return PartnerRepository.getInstance().data.stream()
                .filter(partner -> partner.name.equals(query))
                .findFirst()
                .orElse(null);
    }
}
