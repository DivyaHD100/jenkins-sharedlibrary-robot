def call() {
    node {
        git branch: 'main', url: "https://github.com/DivyaHD100/${COMPONENT}.git"
        env.APP_TYPE = "angular"
        common.lintChecks()
        env.ARGS = "-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()
        if(env.TAG_NAME != null) {
            common.artifacts()
        }
    }

}

// Call is the default function which will be called when you call the fileName
//************ UNCOMMENT IT ONLY EHEN YOU PREFER DECLARATIVE PIPELINE*************// 
// def call() {
//     pipeline {
//         agent any
//          environment { 
//         SONAR = credentials('SONAR')
//         SONAR_URL = "172.31.84.244"
//     }
//             stages {
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
//               stage('Test Cases') {
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
//         }
//     }
// }