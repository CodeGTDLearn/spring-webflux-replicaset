package com.mongo.rs.core.config;


import com.mongo.rs.core.YamlProcessor;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@PropertySource(value = "classpath:application.yml", factory = YamlProcessor.class)
@ConfigurationProperties(prefix = "db.mongodb.replicaset")
@Setter
@Getter
@Profile("dev-three-nodes-rs")
@Import({DbTransactionManagerConfig.class})
@Slf4j
@Configuration
@EnableReactiveMongoRepositories(basePackages = {"com.mongo.rs.modules.user"})
public class DevThreeNodesReplicasetNoAuthConfig extends AbstractReactiveMongoConfiguration {

  private String rootUri;
  private String database;
  private String replicasetName;
  private String authDb;
  private String username;
  private String password;

  @Override
  public MongoClient reactiveMongoClient() {
    /*╔═════════════════════════════════════════════════════════════════╗
      ║ REPLICASET-3-NODES-MONGO-DB PRODUCTION URL (NO USER + PASSWORD) ║
      ╠═════════════════════════════════════════════════════════════════╣
      ║ mongodb://mongo1:9042,mongo2:9142,mongo3:9242/api-db            ║
      ║           ?replicaSet=docker-rs&authSource=db_name.txt                ║
      ╚═════════════════════════════════════════════════════════════════╝*/
    final String
         connection =
         "mongodb://" + rootUri +
              "/" + database +
              "?replicaSet=" + replicasetName +
              "&authSource=" + authDb;

    System.out.println("DevThreeNodesReplicasetNoAuth ---> " + connection);

    return MongoClients.create(connection);
  }


  @Override
  protected String getDatabaseName() {

    return database;
  }
}