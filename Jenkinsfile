pigit peline{
    agent any
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
            steps {sshagent(['Tomcat']) {
    // copy war file to tomcat
    sh "scp -o StrictHostKeyChecking=no target/reddy.war nihkilthera@http://192.168.217.135:/opt/tomcat8/webapps/"
    sh "ssh "ssh nihkilthera@192.168.217.135 /opt/tomcat8/bin/shutdown.sh"
    sh "ssh "ssh nihkilthera@192.168.217.135 /opt/tomcat8/bin/startup.sh"

}}
            
        }
    }
}
