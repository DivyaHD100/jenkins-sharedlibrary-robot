def lintchecks(COMPONENT) {
    sh''' 
        echo lint checks for ${COMPONENT}
        # pylint *.py
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
            stage('Sonar checks') {
                steps {
                    script {
                        common.sonarChecks()
                    }
                }
            }
        }
    }
}