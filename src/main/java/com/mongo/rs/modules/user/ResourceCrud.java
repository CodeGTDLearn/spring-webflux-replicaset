package com.mongo.rs.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.mongo.rs.core.Routes.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

// ==> EXCEPTIONS IN CONTROLLER:
// *** REASON: IN WEBFLUX, EXCEPTIONS MUST BE IN CONTROLLER - WHY?
//     - "Como stream pode ser manipulado por diferentes grupos de thread,
//     - caso um erro aconteça em uma thread que não é a que operou a controller,
//     - o ControllerAdvice não vai ser notificado "
//     - https://medium.com/nstech/programa%C3%A7%C3%A3o-reativa-com-spring-boot-webflux-e-mongodb-chega-de-sofrer-f92fb64517c3
@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
public class ResourceCrud {

  private final ServiceCrud serviceCrud;

  @PostMapping(CRUD_SAVE)
  @ResponseStatus(CREATED)
  public Mono<User> save(@RequestBody User user) {

    return serviceCrud.save(user);
  }

  @GetMapping(CRUD_FINDALL)
  @ResponseStatus(OK)
  public Flux<User> findAll() {

    return serviceCrud.findAll();
  }

  @PostMapping(CRUD_SAVE_TRANSACT)
  @ResponseStatus(CREATED)
  public Flux<User> saveTransact(@RequestBody List<User> userList) {

    return serviceCrud.saveTransact(userList);
  }
}