spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.maxActive=10
spring.datasource.maxIdle=5
spring.datasource.minIdle=2
spring.datasource.initialSize=5
spring.datasource.removeAbandoned=true

#spring.datasource.url=${SPRING_DATASOURCE_URL}
#spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
#spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

#spring.datasource.url=jdbc:postgresql://${server.address}/${server.port}?user=${database.user}&password=${database.password}
spring.datasource.url=jdbc:postgresql://ec2-34-249-161-200.eu-west-1.compute.amazonaws.com/delbktiu1v7luj?user=tljubsczygffzx&password=b464562e3c7882ea38a42bdcc4350b66a55d1a3896a4e5ec79ae2f132753310e
spring.datasource.username=tljubsczygffzx
spring.datasource.password=b464562e3c7882ea38a42bdcc4350b66a55d1a3896a4e5ec79ae2f132753310e

server.port=8080
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.initialization-mode=always
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true