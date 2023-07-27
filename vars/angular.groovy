env.APP_TYPE = "angular"
def call {
    node {
        common.lintChecks()
        env.ARGS = "-Dsonar.sources=."
        common.sonarChecks()
    }

}

// Call is the default function which will be called when you call the fileName
def call() {
    pipeline {
        agent any
         environment { 
        SONAR = credentials('SONAR')
        SONAR_URL = "172.31.84.244"
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
                        env.ARGS = "-Dsonar.sources=."
                        common.sonarChecks()
                    }
                }
            }
              stage('Test Cases') {
            parallel {
                stage('Unit Testing') {
                    steps {
                        // sh "npm test"
                        sh "echo Performing Unit Testing"
                    }
                }
                stage('Integration Testing') {
                    steps {
                        // sh "npm verify"
                        sh "echo Integration Unit Testing"
                    }
                }
                stage('Functional Testing') {
                    steps {
                        sh "echo Integration Unit Testing"
                        }
                    }
                }
            }
        }
    }
}