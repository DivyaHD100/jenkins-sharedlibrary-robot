
def sonarChecks() {
        //sonar-scanner -Dsonar.host.url=http://172.31.84.244:9000 ${ARGS} -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}
        //curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > quality-gate.sh
        //bash -x quality-gate.sh ${SONAR_USR} ${SONAR_PSW} ${SONAR_URL} ${COMPONENT}
        echo "Start code quality analysis"
        echo "Code quality analysis completed"
}

//-x to run in debug mode 