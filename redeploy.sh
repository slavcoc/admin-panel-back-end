#!/bin/bash

update_git() {
    git checkout master
    git fetch origin
    git merge origin/master 
}

rebuild() {
    mvn clean package
}

redeploy() {
    mkdir "./run"
    cp "./target/bestisoft-admin-0.0.1-SNAPSHOT.jar" "./run/bestisoft-admin-0.0.1-SNAPSHOT.jar"
    sudo systemctl restart bestisoft-admin
}

update_git
rebuild
if [[ "$?" -eq 0 ]] ; then
    redeploy
fi


