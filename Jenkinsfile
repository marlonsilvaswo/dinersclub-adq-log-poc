properties([
    parameters([
    ])
])

pipeline {
    agent any
    stages {
        stage('Checkout from Git') {
            steps {
                git branch: 'main', url: 'https://github.com/marlonsilvaswo/dinersclub-adq-log-poc.git'
            }
        }
		
		stage('Maven Build') {
            steps {
                    script {
                    	sh "java -version"
						sh "ls -la"
					    sh "chmod +x mvnw"
                        sh "./mvnw package"
						sh "ls -la ${WORKSPACE}/target/"
                    }
                
            }
        }
		
		stage('Initializing Docker') {

            steps {
                withAWS(credentials: 'aws-key', region: 'us-east-1') {
                        script {
                            sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 066510737035.dkr.ecr.us-east-1.amazonaws.com'

                            sh 'docker version'
						    sh 'docker image list'

                            sh 'docker build -t diners-ecr-repo .'
						    sh 'docker tag diners-ecr-repo:latest 066510737035.dkr.ecr.us-east-1.amazonaws.com/diners-ecr-repo:latest'

                            sh 'docker push 066510737035.dkr.ecr.us-east-1.amazonaws.com/diners-ecr-repo:latest'

                        } 
                }
            }

        }

        stage('Kubernetes Deployment') {


            steps {
                withAWS(credentials: 'aws-key', region: 'us-east-1') {
                        script {
                            sh 'aws eks update-kubeconfig --region us-east-1 --name Diners-EKS-Cluster'

                            sh 'kubectl apply -f k8s-quarkus-deployment.yml'
                    
                        } 
                }
            }
        }

    }
}