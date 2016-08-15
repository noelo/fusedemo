#---> MAVEN PROPERTIES


    #---> NAMEPSPACE/PROJECT WHERE IMAGE WILL BE PUSHED 
	<fabric8.dockerUser>fabric8/</fabric8.dockerUser>
	
	#---> DOCKER IMAGE NAME
	<docker.image>${fabric8.dockerUser}${project.artifactId}:${project.version}</docker.image>
	
	#---> PORT EXPOSED FROM THE CONTAINER -- ENSURE THAT THE APP IS LISTENING ON 0.0.0.0
	<docker.port.container.http>8080</docker.port.container.http>
	<docker.port.container.jolokia>8778</docker.port.container.jolokia>
	
	#---> FIS BASE IMAGE
	<docker.from>registry.access.redhat.com/jboss-fuse-6/fis-java-openshift:1.0</docker.from>
	
	#---> KUBERNETES SERVICE NAME
	<fabric8.service.name>${project.artifactId}</fabric8.service.name>
	
	#---> PORT THAT SERVICE WILL BE LISTENING ON
	<fabric8.service.port>8000</fabric8.service.port>
	
	#---> MAPS TO docker.port.container.http
	<fabric8.service.containerPort>http</fabric8.service.containerPort>
	
	#---> LABEL ALL THE THINGS
	<fabric8.label.component>${project.artifactId}</fabric8.label.component>
	<fabric8.label.container>java</fabric8.label.container>
	<fabric8.label.group>quickstarts</fabric8.label.group>
	<fabric8.iconRef>camel</fabric8.iconRef>



#---> BUILD AND CREATE KUBERNETES CONFIG
mvn clean package fabric8:json fabric8:apply -Dfabric8.dockerUser=$(oc get svc docker-registry -n default -o 'jsonpath={.spec.clusterIP}:{.spec.ports[0].port}')/$(oc project -q)/

#---> LOGIN TO REGISTRY
docker login -u noelo -e noconnor@redhat.com  -p `oc whoami -t`  docker-registry.cloudapps.nocosetest.com

#---> PUSH TO THE REGISTRY
mvn docker:push -Ddocker.registry=docker-registry.cloudapps.nocosetest.com -Ddocker.username=$(oc whoami) -Ddocker.password=$(oc whoami -t)


#---> Endpoints 
http://restemailgateway-restsmtp.cloudapps.nocosetest.com/cxf/rest/messages