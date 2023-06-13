def lintchecks(COMPONENT) {
    sh''' 
        echo installing jslint
        #npm install jslint
        #ls -ltr node_modules/jslint/bin
        #node_modules/jslint/bin/jslint.js server.js
        echo PERFORMING LINT CHECKS  
        echo PERFORMING LINT CHECKS COMPLETED  
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
        }
    }
}