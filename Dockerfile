FROM airhacks/payara:4.1.2.173
MAINTAINER Kleber da Silva Xavier

ADD target/skip-api.war ${DEPLOYMENT_DIR}


