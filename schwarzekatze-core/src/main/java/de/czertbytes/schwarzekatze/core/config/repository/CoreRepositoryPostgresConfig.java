package de.czertbytes.schwarzekatze.core.config.repository;

import de.czertbytes.schwarzekatze.core.domain.action.Action;
import de.czertbytes.schwarzekatze.core.domain.flyer.Flyer;
import de.czertbytes.schwarzekatze.core.domain.pet.Pet;
import de.czertbytes.schwarzekatze.core.domain.picture.Picture;
import de.czertbytes.schwarzekatze.core.domain.shelter.Shelter;
import de.czertbytes.schwarzekatze.core.domain.user.User;
import de.czertbytes.schwarzekatze.core.infrastructure.*;
import de.czertbytes.schwarzekatze.core.infrastructure.impl.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class CoreRepositoryPostgresConfig {

    @PersistenceContext(unitName = "schwarzekatzePU")
    EntityManager entityManager;

    @Autowired
    BeanFactory beanFactory;

    @Bean
    public ActionRepository actionRepository() {
        final JpaRepositoryFactoryBean<ActionRepository, Action, Long> factory = new JpaRepositoryFactoryBean<ActionRepository, Action, Long>();

        factory.setBeanFactory(beanFactory);
        factory.setEntityManager(entityManager);
        factory.setRepositoryInterface(ActionRepository.class);
        factory.setCustomImplementation(ActionRepositoryCustomImpl.class);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PetRepository petRepository() {
        final JpaRepositoryFactoryBean<PetRepository, Pet, Long> factory = new JpaRepositoryFactoryBean<PetRepository, Pet, Long>();

        factory.setBeanFactory(beanFactory);
        factory.setEntityManager(entityManager);
        factory.setRepositoryInterface(PetRepository.class);
        factory.setCustomImplementation(PetRepositoryCustomImpl.class);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public UserRepository userRepository() {
        final JpaRepositoryFactoryBean<UserRepository, User, Long> factory = new JpaRepositoryFactoryBean<UserRepository, User, Long>();

        factory.setBeanFactory(beanFactory);
        factory.setEntityManager(entityManager);
        factory.setRepositoryInterface(UserRepository.class);
        factory.setCustomImplementation(UserRepositoryCustomImpl.class);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public ShelterRepository shelterRepository() {
        final JpaRepositoryFactoryBean<ShelterRepository, Shelter, Long> factory = new JpaRepositoryFactoryBean<ShelterRepository, Shelter, Long>();

        factory.setBeanFactory(beanFactory);
        factory.setEntityManager(entityManager);
        factory.setRepositoryInterface(ShelterRepository.class);
        factory.setCustomImplementation(ShelterRepositoryCustomImpl.class);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public FlyerRepository flyerRepository() {
        final JpaRepositoryFactoryBean<FlyerRepository, Flyer, Long> factory = new JpaRepositoryFactoryBean<FlyerRepository, Flyer, Long>();

        factory.setBeanFactory(beanFactory);
        factory.setEntityManager(entityManager);
        factory.setRepositoryInterface(FlyerRepository.class);
        factory.setCustomImplementation(FlyerRepositoryCustomImpl.class);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PictureRepository pictureRepository() {
        final JpaRepositoryFactoryBean<PictureRepository, Picture, Long> factory = new JpaRepositoryFactoryBean<PictureRepository, Picture, Long>();

        factory.setBeanFactory(beanFactory);
        factory.setEntityManager(entityManager);
        factory.setRepositoryInterface(PictureRepository.class);
        factory.setCustomImplementation(PictureRepositoryCustomImpl.class);
        factory.afterPropertiesSet();

        return factory.getObject();
    }
}
