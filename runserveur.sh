mvn install
mvn "-Dexec.args=-classpath %classpath Server.ServerStockage" -Dexec.executable=java org.codehaus.mojo:exec-maven-plugin:1.2.1:exec

