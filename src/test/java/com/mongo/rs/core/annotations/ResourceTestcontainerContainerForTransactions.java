package com.mongo.rs.core.annotations;

import com.mongo.rs.core.testcontainer.container.TestcontainerContainer;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

/*
     ╔═══════════════════════════════╗
     ║ USING CONTAINERS IN THE TESTS ║
     ╠═══════════════════════════════╩════════════════╗
     ║ CONFLICT: TEST-CONTAINERS X DOCKER-CONTAINERS  ║
     ║           THEY DO NOT WORKS TOGETHER           ║
     ╠════════════════════════════════════════════════╩═════════╗
     ║A) TEST-CONTAINERS:                                       ║
     ║   A.1) STOP+CLEAN DOCKER-CONTAINERS  (DOCKER-BAT-SCRIPT) ║
     ║   A.2) "UNCOMMENT" THE TEST-CONTAINERS-ANNOTATION BELOW  ║
     ║   A.3) RUN THE TESTS                                     ║
     ║                                                          ║
     ║B) DOCKER-CONTAINERS:                                     ║
     ║   B.1) SET PROFILE-ACTIVE IN SRC/MAIN/APPLICATION.YML    ║
     ║   B.2) "COMMENT" TEST-CONTAINER-ANNOTATION (TcContainer) ║
     ║   B.3) START DOCKER-CONTAINER (DOCKER-BAT-SCRIPT-PROFILE)║
     ║   B.4) RUN THE TESTS                                     ║
     ║   B.5) UNCOMMENT THE APPLICATION-TC-CONTAINER.YML        ║
     ╚══════════════════════════════════════════════════════════╝
*/
@Retention(RUNTIME)
@Target(TYPE)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_CLASS)
@TestPropertySource("classpath:application.yml")
@ActiveProfiles("tcont") // TEST-PROFILE FOR TRANSACTIONS(test-container-container)
@TestcontainerContainer // ALLOW TEST-CONTAINER TEST TRANSACTIONS
public @interface ResourceTestcontainerContainerForTransactions {
}