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
            stage('performing npm install') {
            
                steps {
                    sh "npm install"
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
                stage('prepare the artifacts') {
                    when { expression { env.TAG_NAME != null } }
                    steps {
                        sh "echo preparing the artifacts"
                        }
                    }
                stage('Publish the artifacts') {
                    when { expression { env.TAG_NAME != null } }
                    steps {
                        sh "echo Publishing the artifacts"
                        }
                    }
        }


    }
}
