package de.czertbytes.schwarzekatze.api.controller;

import de.czertbytes.schwarzekatze.api.domain.Shelter;
import de.czertbytes.schwarzekatze.api.domain.Shelters;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.Users;
import de.czertbytes.schwarzekatze.api.domain.parameters.ShelterSearchParameters;
import de.czertbytes.schwarzekatze.api.service.SheltersResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/v1/shelters", produces = MediaType.APPLICATION_JSON_VALUE)
public class SheltersController extends BaseController {

    @Autowired
    @Qualifier("globalValidator")
    Validator globalValidator;

    @Autowired
    SheltersResourceService shelterService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(globalValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Shelters getShelters(ShelterSearchParameters shelterSearchParameters, Pageable pageable) {
        return shelterService.find(shelterSearchParameters, pageable);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postShelters(@RequestBody @Validated Shelter shelter, HttpServletResponse response) {
        response.addHeader("location", "/v1/shelters/" + shelterService.create(shelter));
    }

    @RequestMapping(value = "/{shelterId}", method = RequestMethod.GET)
    @ResponseBody
    public Shelter getShelter(@PathVariable Long shelterId) {
        return shelterService.find(shelterId);
    }

    @RequestMapping(value = "/{shelterId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putShelter(@PathVariable Long shelterId, @RequestBody @Validated Shelter shelter) {
        shelterService.update(shelterId, shelter);
    }

    @RequestMapping(value = "/{shelterId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteShelter(@PathVariable Long shelterId) {
        shelterService.delete(shelterId);
    }

    @RequestMapping(value = "/{shelterId}/users", method = RequestMethod.GET)
    @ResponseBody
    public Users getUsers(@PathVariable Long shelterId, Pageable pageable) {
        return shelterService.findUsers(shelterId, pageable);
    }

    @RequestMapping(value = "/{shelterId}/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postUsers(@PathVariable Long shelterId, @RequestBody @Validated User user, HttpServletResponse response) {
        response.addHeader("location", "/v1/users/" + shelterService.createUser(shelterId, user));
    }

    @RequestMapping(value = "/{shelterId}/users/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long shelterId, @PathVariable Long userId) {
        shelterService.deleteUser(shelterId, userId);
    }
}
