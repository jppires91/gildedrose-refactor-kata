compile:
	mvn compile

package:
	mvn clean install -Ppackage

run: package
	java -jar gilded-rose-kata.jar

#using clean install to generate jacoco report
test:
	mvn clean install
