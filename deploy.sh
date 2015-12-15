mvn clean package -P openshift
rm -rf ~/Java/apache-tomcat-8.0.28/webapps/ROOT.war
rm -rf ~/Java/apache-tomcat-8.0.28/webapps/ROOT
mv webapps/ROOT.war ~/Java/apache-tomcat-8.0.28/webapps/ROOT.war
sh ~/Java/apache-tomcat-8.0.28/bin/catalina.sh run
