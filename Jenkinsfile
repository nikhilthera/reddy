displayName = "${projectname}${buildnumber}" 
pipeline{
    agent any
    environment {
  TOMCAT_HOST = "nikhilthera@192.168.217.135"
    TOMCAT_WEBAPPS_PATH = "/opt/tomcat8/bin/"
}
    stages{
        stage('git checkout'){
            steps{
                git credentialsId: 'git', url: 'https://github.com/nikhilthera/reddy'
            }
        }
        stage('mvn package and deploy '){
            steps{
               sh 'mvn clean deploy'
               }
        }
        stage('tomcat'){
            steps {sshagent(['ssh']){
    // copy war file to tomcat
    sh "scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Reddy_pipeline/target/reddy.war nikhilthera@192.168.217.135:/opt/tomcat8/webapps"
    sh "ssh ${TOMCAT_HOST} ${TOMCAT_WEBAPPS_PATH}shutdown.sh"
    sh "ssh ${TOMCAT_HOST} ${TOMCAT_WEBAPPS_PATH}startup.sh"

}
                  }
            
        }
    }
    post {
  failure {
    mail bcc: '', body: "job ${JOB_NAME} is failed. Please find the usrl to access ${BUILD_URL} ", cc: '', from: '', replyTo: '', subject: "Jenkins job ${JOB_NAME} failure", to: 'nikhilkumar.thera@gmail.com'
  }
}
}
