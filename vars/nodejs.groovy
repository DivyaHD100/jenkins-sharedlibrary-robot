def lintchecks(COMPONENT) {
    sh''' 
        echo lint checks for ${COMPONENT}
        echo installing jslint
        #npm install jslint
        #ls -ltr node_modules/jslint/bin
        #node_modules/jslint/bin/jslint.js server.js
        echo PERFORMING LINT CHECKS for ${COMPONENT}  
        echo PERFORMING LINT CHECKS COMPLETED for ${COMPONENT}  
    '''                
}

def sonarChecks(){
    sh '''
        sonar-scanner -Dsonar.host.url=https://172.31.93.227:9000 -Dsonar.sources=. -Dsonar.projectKey==$(COMPONENT)

    '''
}

// Call is the default function which will be called when you call the fileName
def call() {
    pipeline {
        agent any
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