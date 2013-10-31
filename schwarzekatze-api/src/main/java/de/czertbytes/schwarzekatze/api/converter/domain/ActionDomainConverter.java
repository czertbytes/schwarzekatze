package de.czertbytes.schwarzekatze.api.converter.domain;

import com.google.common.collect.Lists;
import de.czertbytes.schwarzekatze.api.domain.Action;
import de.czertbytes.schwarzekatze.core.domain.pet.Pet;
import de.czertbytes.schwarzekatze.core.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionDomainConverter {

    public Action toApiAction(final de.czertbytes.schwarzekatze.core.domain.action.Action coreAction) {
        final Action apiAction = new Action();

        apiAction.setType(coreAction.getType());
        apiAction.setDescription(coreAction.getDescription());

        return apiAction;
    }

    public List<Action> toApiActions(final List<de.czertbytes.schwarzekatze.core.domain.action.Action> coreActions) {
        final List<Action> apiActions = Lists.newArrayList();

        for (de.czertbytes.schwarzekatze.core.domain.action.Action coreAction : coreActions) {
            apiActions.add(toApiAction(coreAction));
        }

        return apiActions;
    }

    public de.czertbytes.schwarzekatze.core.domain.action.Action toCoreAction(final Action apiAction, final Pet corePet, final User coreUser) {
        final de.czertbytes.schwarzekatze.core.domain.action.Action coreAction = new de.czertbytes.schwarzekatze.core.domain.action.Action();

        coreAction.setPet(corePet);
        coreAction.setDateTime(apiAction.getDateTime());
        coreAction.setType(apiAction.getType());
        coreAction.setGeohash(apiAction.getLocation());             //  TODO: encode me to GEOHASH!
        coreAction.setDescription(apiAction.getDescription());
        coreAction.setUser(coreUser);

        return coreAction;
    }
}
