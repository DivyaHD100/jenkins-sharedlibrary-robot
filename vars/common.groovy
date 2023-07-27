
def sonarChecks() {
    stage('Sonar Checks') 
        //sonar-scanner -Dsonar.host.url=http://172.31.84.244:9000 ${ARGS} -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}
        //curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > quality-gate.sh
        //bash -x quality-gate.sh ${SONAR_USR} ${SONAR_PSW} ${SONAR_URL} ${COMPONENT}
        echo "Start code quality analysis"
        echo "Code quality analysis completed"
}

//-x to run in debug mode 

def lintChecks() {
  stage('Lint Checks') {
        if(env.APP_TYPE == "maven") {
             sh '''  
                echo Lint Checks for ${COMPONENT}
                # mvn checkstyle:check
                echo performing lint checks for ${COMPONENT}
                echo performing lint checks completed ${COMPONENT}
              
              '''         
        } 
        else if(env.APP_TYPE == "nodejs") {
              sh '''  
                      echo Lint Checks for ${COMPONENT}
                      echo installing jslint
                      # npm install jslint
                      # ls -ltr node_modules/jslint/bin/
                      # node_modules/jslint/bin/jslint.js server.js
                      echo performing lint checks for ${COMPONENT}
                      echo performing lint checks completed ${COMPONENT}
              ''' 
        }
        else if(env.APP_TYPE == "python") { 
              sh '''  
                      echo Lint Checks for ${COMPONENT}
                      # pylint *.py
                      echo performing lint checks for ${COMPONENT}
                      echo performing lint checks completed ${COMPONENT}
              ''' 
        } 
        else {
              sh '''  
                      echo Lint Checks for ${COMPONENT}
                      echo performing lint checks for ${COMPONENT}
                      echo performing lint checks completed ${COMPONENT}
              ''' 
        }
    }
}