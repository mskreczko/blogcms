package pl.mskreczko.blogcms.infrastructure.config.uuid;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UUIDProvider {
    UUID getUUID();

    @Profile("test")
    class MockUUID implements UUIDProvider {

        @Override
        public UUID getUUID() {
            return UUID.fromString("0000-00-00-00-000000");
        }
    }
}
