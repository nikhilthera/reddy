pipeline{
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
            steps {sshPublisher(publishers: [sshPublisherDesc(configName: 'Tomcat', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''/opt/tomcat8/bin/shutdown.sh
/opt/tomcat8/bin/startup.sh''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/opt/tomcat8/webapps/', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/reddy.war')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])}
            
        }
    }
}
