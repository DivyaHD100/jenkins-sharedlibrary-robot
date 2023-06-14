def lintchecks(COMPONENT) {
    sh''' 
        echo lint checks for ${COMPONENT}
        # mvn checkstyle:check
        echo PERFORMING LINT CHECKS for ${COMPONENT}  
        echo PERFORMING LINT CHECKS COMPLETED for ${COMPONENT}  
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