package mk.trkalo.dnp.dnpshop.web.rest;

import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/sizes", produces = MediaType.APPLICATION_JSON_VALUE)
public class SizeResource {
    private final SizeService sizeService;

    @Autowired
    public SizeResource(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public List<Size> findAll(){
        return sizeService.findAll();
    }


}
