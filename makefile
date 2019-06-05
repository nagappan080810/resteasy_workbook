all:
	sudo apt-get update
	sudo apt-get install default-jre
	sudo apt-get install default-jdk
	sudo apt policy maven
	sudo apt install maven	
	mvn clean install cargo:run
