package org.stve.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

@ApplicationScoped
public class ModelMapperConfig {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }

}
