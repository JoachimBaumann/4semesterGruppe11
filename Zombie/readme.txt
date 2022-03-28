mvn org.ops4j:maven-pax-plugin:create-project -DgroupId=dk.sdu.mmmi -DartifactId=osgi-project -Dversion=1.0-SNAPSHOT

mvn pax:create-bundle -Dpackage=dk.sdu.mmmi.cbse -Dname=core-bundle -Dversion=1.0-SNAPSHOT

mvn pax:wrap-jar -g groupId -a artifactId -v version

