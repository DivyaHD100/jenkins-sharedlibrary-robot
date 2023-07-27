def call {
    node {
        env.APP_TYPE = "nodejs"
        common.lintChecks()
        env.ARGS = "-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()
    }

}


// Call is the default function which will be called when you call the fileName
//************ UNCOMMENT IT ONLY EHEN YOU PREFER DECLARATIVE PIPELINE*************// 
// def call() {
//     pipeline {
//         agent any
//         environment { 
//         SONAR = credentials('SONAR')
//         NEXUS = credentials('Nexus')
//         SONAR_URL = "172.31.84.244"
//         NEXUS_URL = "172.31.27.103"
//     }
//         stages {
//             stage('Lint checks') {
//                 steps {
//                     script {
//                         lintchecks()
//                     }
//                 }
//             }
//             stage('Sonar checks') {
//                 steps {
//                     script {
//                         env.ARGS = "-Dsonar.sources=."
//                         common.sonarChecks()
//                     }
//                 }
//             }
            
//             stage('Test Cases') {
//             parallel {
//                 stage('Unit Testing') {
//                     steps {
//                         // sh "npm test"
//                         sh "echo Performing Unit Testing"
//                     }
//                 }
//                 stage('Integration Testing') {
//                     steps {
//                         // sh "npm verify"
//                         sh "echo Integration Unit Testing"
//                     }
//                 }
//                 stage('Functional Testing') {
//                     steps {
//                         sh "echo Integration Unit Testing"
//                         }
//                     }
                
//                 }
//             }
//                 stage('Checking the artifact version') {
//                     when { expression { env.TAG_NAME != null } }
//                     steps {
//                         script{
//                         env.UPLOAD_STATUS=sh(returnStdout: true, script: 'curl -L -s http://${NEXUS_URL}:8081/service/rest/repository/browse/${COMPONENT} | grep ${COMPONENT}-${TAG_NAME}.zip || true')
//                         print UPLOAD_STATUS
//                         }
//                     }
//                 }
//                 stage('prepare the artifacts') {
//                     when { 
//                         expression { env.TAG_NAME != null } 
//                         expression { env.UPLOAD_STATUS == ""}
//                         }
                    
//                     steps {
//                         sh "npm install"
//                         sh "echo Preparing the artifacts"
//                         sh "zip ${COMPONENT}-${TAG_NAME}.zip node_modules server.js"
//                         }
//                     }
//                 stage('Publish the artifacts') {
//                     when { 
//                         expression { env.TAG_NAME != null }
//                         expression { env.UPLOAD_STATUS == ""}
//                         }
//                     steps {
//                         sh "curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${TAG_NAME}.zip http://${NEXUS_URL}:8081/repository/${COMPONENT}/${COMPONENT}-${TAG_NAME}.zip" 
//                         }
//                     }
//         }


//     }
// }
