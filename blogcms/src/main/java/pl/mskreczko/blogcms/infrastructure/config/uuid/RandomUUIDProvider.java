package pl.mskreczko.blogcms.infrastructure.config.uuid;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomUUIDProvider implements UUIDProvider{
    @Override
    public UUID getUUID() {
        return UUID.randomUUID();
    }
}
