package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.allegro_api.AllegroApiResponeAuction;
import pl.krzysiek.api.allegro_api.domain.Regular;
import pl.krzysiek.domain.AllegroForApp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllegroForAppService {

    private AllegroServices allegroServices;

    private static final String NO_IMAGE_AVAILABLE_PNG = "https://www.nocowboys.co.nz/images/v3/no-image-available.png";

    @Autowired
    public AllegroForAppService(AllegroServices allegroServices) {
        this.allegroServices = allegroServices;
    }

    public List<AllegroForApp> allegroAuctionsForApp(String positionName) throws IOException, URISyntaxException {
        List<AllegroForApp> allegroForAppList = new ArrayList<>();
        AllegroApiResponeAuction allegroApiResponeAuction = allegroServices.allegroAuctionRespone(positionName);

        for (Regular auction : allegroApiResponeAuction.getItems().getRegular()) {
            AllegroForApp allegroForApp = new AllegroForApp();

            allegroForApp.setAuctionName(auction.getName());
            allegroForApp.setAuctionNumber(auction.getId());
            allegroForApp.setProductPrice(auction.getSellingMode().getPrice().getAmount());
            allegroForApp.setAuctionImage(verifyPictureIsNotNull(auction));
            allegroForApp.setLowestPriceDelivery(auction.getDelivery().getLowestPrice().getAmount());

            allegroForAppList.add(allegroForApp);
        }

        return allegroForAppList;
    }

    private String verifyPictureIsNotNull(Regular regular) {
        return (regular.getImages().size() != 0) ? regular.getImages().get(0).getUrl() : NO_IMAGE_AVAILABLE_PNG;
    }
}
