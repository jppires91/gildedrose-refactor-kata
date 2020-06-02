compile:
	mvn clean install -DskipTests

package:
	mvn package -Ppackage

run: package
	java -jar gilded-rose-kata.jar

test:
	mvn test
