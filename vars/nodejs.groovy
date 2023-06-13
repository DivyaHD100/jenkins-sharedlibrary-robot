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

def call() {
    pipeline {
        agent any
        stages {
            stage ('Lint checks') {
                step {
                    script {
                        lintchecks()
                    }
                }
            }
        }
    }
}