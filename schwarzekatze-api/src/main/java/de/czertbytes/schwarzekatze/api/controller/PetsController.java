package de.czertbytes.schwarzekatze.api.controller;

import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Pets;
import de.czertbytes.schwarzekatze.api.domain.parameters.PetSearchParameters;
import de.czertbytes.schwarzekatze.api.service.PetsResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/v1/pets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PetsController extends BaseController {

    public static final Logger log = LoggerFactory.getLogger(PetsController.class);

    @Autowired
    @Qualifier("globalValidator")
    Validator globalValidator;

    @Autowired
    PetsResourceService petsService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(globalValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Pets getPets(@Validated PetSearchParameters petSearchParameters, Pageable pageable) {
        return petsService.findAll(petSearchParameters, pageable);
    }

    @RequestMapping(value = "/{petId}", method = RequestMethod.GET)
    @ResponseBody
    public Pet getPet(@PathVariable Long petId) {
        return petsService.find(petId);
    }
}
