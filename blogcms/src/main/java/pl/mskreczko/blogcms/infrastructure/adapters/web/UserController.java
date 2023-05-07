package pl.mskreczko.blogcms.infrastructure.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mskreczko.blogcms.application.exceptions.EntityAlreadyExistsException;
import pl.mskreczko.blogcms.application.services.user.UserService;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewUserDto;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<?> createUser(@RequestBody NewUserDto newUserDto) {
        try {
            userService.createUser(newUserDto.username());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
