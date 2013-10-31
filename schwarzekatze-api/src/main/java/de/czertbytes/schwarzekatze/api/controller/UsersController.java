package de.czertbytes.schwarzekatze.api.controller;

import de.czertbytes.schwarzekatze.api.domain.Pet;
import de.czertbytes.schwarzekatze.api.domain.Pets;
import de.czertbytes.schwarzekatze.api.domain.User;
import de.czertbytes.schwarzekatze.api.domain.Users;
import de.czertbytes.schwarzekatze.api.domain.parameters.UserSearchParameters;
import de.czertbytes.schwarzekatze.api.service.UsersResourceService;
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
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController extends BaseController {

    @Autowired
    @Qualifier("globalValidator")
    Validator globalValidator;

    @Autowired
    UsersResourceService usersService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(globalValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Users getUsers(UserSearchParameters userSearchParameters, Pageable pageable) {
        return usersService.find(userSearchParameters, pageable);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postUsers(@RequestBody @Validated User user, HttpServletResponse response) {
        response.addHeader("location", "/v1/users/" + usersService.create(user));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable Long userId) {
        return usersService.find(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable Long userId, @RequestBody @Validated User user) {
        usersService.update(userId, user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        usersService.delete(userId);
    }

    @RequestMapping(value = "/{userId}/token", method = RequestMethod.GET)
    @ResponseBody
    public String getToken(@PathVariable Long userId) {
        return null;
    }

    @RequestMapping(value = "/{userId}/token", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String postToken(@PathVariable Long userId) {
        return null;
    }

    @RequestMapping(value = "/{userId}/pets", method = RequestMethod.GET)
    @ResponseBody
    public Pets getPets(@PathVariable Long userId, Pageable pageable) {
        return usersService.findPets(userId, pageable);
    }

    @RequestMapping(value = "/{userId}/pets", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postPets(@PathVariable Long userId, @RequestBody @Validated Pet pet, HttpServletResponse response) {
        response.addHeader("location", "/v1/pets/" + usersService.createPet(userId, pet));
    }

    @RequestMapping(value = "/{userId}/pets/{petId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putPet(@PathVariable Long userId, @PathVariable Long petId, @RequestBody @Validated Pet pet) {
        usersService.updatePet(userId, pet);
    }

    @RequestMapping(value = "/{userId}/pets/{petId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Long userId, @PathVariable Long petId) {
        usersService.deletePet(userId, petId);
    }

    @RequestMapping(value = "/{userId}/pets/{petId}/pictures", method = RequestMethod.GET)
    @ResponseBody
    public String getPictures(@PathVariable Long userId, @PathVariable Long petId) {
        return null;
    }

    @RequestMapping(value = "/{userId}/pets/{petId}/pictures", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String postPictures(@PathVariable Long userId, @PathVariable Long petId) {
        return null;
    }

    @RequestMapping(value = "/{userId}/pets/{petId}/pictures/{pictureId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putPicture(@PathVariable Long userId, @PathVariable Long petId, @PathVariable Long pictureId, @RequestBody @Validated Pet pet) {
        //usersService.update(userId, user);
    }

    @RequestMapping(value = "/{userId}/pets/{petId}/pictures/{pictureId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePicture(@PathVariable Long userId, @PathVariable Long petId, @PathVariable Long pictureId) {
        //usersService.delete(userId);
    }
}
