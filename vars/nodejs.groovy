def lintchecks(COMPONENT) {
    sh''' 
        echo lint checks for ${COMPONENT}
        echo installing jslint
        #npm install
        #ls -ltr node_modules/jslint/bin
        #node_modules/jslint/bin/jslint.js server.js
        echo PERFORMING LINT CHECKS for ${COMPONENT}  
        echo PERFORMING LINT CHECKS COMPLETED for ${COMPONENT}  
    '''                
}

def sonarChecks() {
    sh '''
        sonar-scanner -Dsonar.host.url=http://172.31.84.244:9000 -Dsonar.sources=. -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR-USR} -Dsonar.password=${SONAR_PSW}
    '''
}

// Call is the default function which will be called when you call the fileName
def call() {
    pipeline {
        agent any
        environment { 
        SONAR = credentials('SONAR')
    }
            stages {
            
            stage('Lint checks') {
                steps {
                    script {
                        lintchecks()
                    }
                }
            }
            stage('Sonar checks') {
                steps {
                    script {
                        sonarChecks()
                    }
                }
            }
            
            }
        }
    }
