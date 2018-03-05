#!/bin/sh
### 本地仓库初始化脚本
cd ../src/main/lib

mvn install:install-file -Dfile=axiom-api-1.2.5.jar -DgroupId=com.axiom -DartifactId=api -Dversion=2.0 -Dpackaging=jar&&mvn install:install-file -Dfile=axiom-dom-1.2.5.jar -DgroupId=com.axiom -DartifactId=dom -Dversion=2.0 -Dpackaging=jar&&mvn install:install-file -Dfile=axiom-impl-1.2.5.jar -DgroupId=com.axiom -DartifactId=impl -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=beepcore.jar -DgroupId=com.beep -DartifactId=core -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=beepsasl.jar -DgroupId=com.sasl -DartifactId=sasl -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=beepsasl.jar -DgroupId=com.sasl -DartifactId=sasl -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=beeptls-jsse.jar -DgroupId=com.beep -DartifactId=tls-jsse -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=beeptls-ptls.jar -DgroupId=com.beep -DartifactId=tls-ptls -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=braid-base-1.2.jar -DgroupId=com.braid -DartifactId=braid-base-impl -Dversion=1.2 -Dpackaging=jar&&
mvn install:install-file -Dfile=hapi-0.5.1.jar -DgroupId=com.hapi -DartifactId=all -Dversion=2.0 -Dpackaging=jar&&
mvn install:install-file -Dfile=relaxngDatatype.jar -DgroupId=com.relaxng.datetype -DartifactId=relaxngdatetype -Dversion=2.0 -Dpackaging=jar