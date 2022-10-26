package app.controllers;

import app.entities.Partner;
import app.repositories.PartnerRepository;
import lajavel.Controller;
import lajavel.facades.Response;
import lajavel.View;

import java.util.List;
import java.util.Map;

public class indexController extends Controller {
    public static void index (Response response) {
        List<Partner> partners = PartnerRepository.findAll();

        response.html(View.render("index", Map.entry("partner1", partners.get(1))));
    }
}
