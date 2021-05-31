serversMap = [dev: "1.1.1.1", test: "1.1.1.2", uat: "1.1.1.3"]

pipeline{
    agent any
    parameters {
  choice choices: ['dev', 'uat', 'test'], description: 'choose an app to deploy', name: 'servers'
  choice choices: ['finaltest', 'master', 'tomcat'], description: 'choose th ebranch to deploy', name: 'branchname'
}
    stages{
        stage ('maven'){
            steps{
                        echo "we are building ${params.branchname}"
         } 
         }
         stage ('deploy1'){
             when{
                 epression {params.servers == 'dev' }
             }
            steps{
                echo "we are deploying to ${params.servers} on ${serversMap[params.servers]}" }
         } 
        stage ('deploy2'){
            {
                when{
                    expression {params.servers == 'uat' }
                }
            }
            steps{
                echo "we are deploying to ${params.servers} on ${serversMap[params.servers]}" }
         } 
        stage ('deploy3'){
             when{
                 epression {params.servers == 'dev' }
             }
        {
            steps{
                echo "we are deploying to ${params.servers} on ${serversMap[params.servers]}" }
         } 
         }

    }
    

