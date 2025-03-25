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
                    script {
					    sh 'docker version'
						sh 'docker image list'

                        sh 'docker build -t diners-ecr-repo .'
						sh 'docker tag diners-ecr-repo:latest 066510737035.dkr.ecr.us-east-1.amazonaws.com/diners-ecr-repo:latest'

                        sh 'docker push 066510737035.dkr.ecr.us-east-1.amazonaws.com/diners-ecr-repo:latest'
                    }
                
            }
        }

        stage('Kubernetes Deployment') {
            steps {
                    script {
                        sh 'kubectl apply -f k8s-quarkus-deployment.yml'
                    }
                
            }
        }

    }
}