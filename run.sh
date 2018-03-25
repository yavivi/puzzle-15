APP_JAR=build/libs/puzzle15-spring-boot-0.1.0.jar
if [ ! -f $APP_JAR ]; then
    ./gradlew build
fi
java -jar build/libs/puzzle15-spring-boot-0.1.0.jar $1
